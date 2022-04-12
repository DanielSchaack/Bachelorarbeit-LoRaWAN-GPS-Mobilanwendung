const router = require('express').Router();
const db = require('../Datenbankverbindung');
const https = require('https');

const PROJECT_ID = '814043362978';
const HOST = 'fcm.googleapis.com';
const PATH = '/v1/projects/' + PROJECT_ID + '/messages:send';

const google = require('googleapis');
const googlekey = require('../daniel-bachelor-firebase-adminsdk-nax4i-1233504736.json');
const firebase = require('firebase-admin');
var MESSAGING_SCOPE = 'https://www.googleapis.com/auth/firebase.messaging';
var SCOPES = [MESSAGING_SCOPE];

firebase.initializeApp({
    credential: firebase.credential.applicationDefault(), 
});

const {NeuValidierung, SucheValidierung} = require('../Validierung/StandortValidierung');

function getMessageFormat(BToken, HWSName, BGrad, LGrad){
    return {
        'validate_only': false,
        'message': {
            'data': {
                'HName': HWSName,
                'BrGrad': ''+BGrad,
                'LaeGrad': ''+LGrad
            },
            'notification': {
                'title': 'FCM Notification',
                'body': 'Notification from FCM'
            },
            'token': BToken
        }
    };
}
/*
Erstellung eines kurzlebigen OAuth 2.0 Access Token, um auf FCM zugreifen zu können
abgeändert aus https://github.com/firebase/quickstart-nodejs/blob/8f1f9241c0b659bda59cf5ef2c08520bf109874d/messaging/index.js
*/
function getAccessToken() {
    return new Promise(function(resolve, reject) {
      
      const jwtClient = new google.Auth.JWT(
        googlekey.client_email,
        null,
        googlekey.private_key,
        SCOPES,
        null
      );
      jwtClient.authorize(function(err, tokens) {
        if (err) {
          reject(err);
          return;
        }
        resolve(tokens.access_token);
      });
    });
}

/*
Senden der FCM-Nachricht, aus https://github.com/firebase/quickstart-nodejs/blob/8f1f9241c0b659bda59cf5ef2c08520bf109874d/messaging/index.js
*/
function sendFcmMessage(fcmMessage) {
    getAccessToken().then(function(accessToken) {
        const options = {
            hostname: HOST,
            path: PATH,
            method: 'POST',
            // [START use_access_token]
            headers: {
                'Authorization': 'Bearer ' + accessToken
            }
            // [END use_access_token]
        };
  
        const request = https.request(options, function(resp) {
            resp.setEncoding('utf8');
            resp.on('data', function(data) {
                console.log('Message sent to Firebase for delivery, response:');
                console.log(data);
            });
        });
  
        request.on('error', function(err) {
            console.log('Unable to send message to Firebase');
            console.log(err);
        });
  
        request.write(JSON.stringify(fcmMessage));
        request.end();
    });
}
  

router.post('/Neu', async (req, res) =>{
    //Eingabe, Format siehe Uplink-Nachricht https://www.thethingsindustries.com/docs/reference/data-formats/
    const HWSName = req.body.end_device_ids.device_id;
    const BGrad = req.body.uplink_message.decoded_payload.Breitengrad;
    const LGrad = req.body.uplink_message.decoded_payload.Laengengrad;

    //Validierung der Eingabe
    const ValError = NeuValidierung(HWSName, BGrad, LGrad);
    if(!ValError.error)
    {
        //Validierung ist gelungen, kein Fehler in der Eingabe
        //Versuche, die Daten als Standort einzufügen
        try{
            //Prüfe, ob Hardwaresystem vorhanden
            const Resultat = await db.pool.query("SELECT BName FROM Hardwaresystem WHERE HName = ?", [HWSName]);

            //Falls der erste Eintrag im Resultat leer ist, dann ist kein Hardwaresystem unter dem angegebenen Namen vorhanden.
            if(!Resultat[0]){
                return res.status(400).json({
                    status: 400,
                    message: 'Es ist kein Hardwaresystem unter dem angegebenen Namen vorhanden.'
                    });
            }else{
                //Es sind Einträge, und somit das Hardwaresystem, vorhanden
                //Füge die Daten als Eintrag ein
                const ResultatInsert = await db.pool.query("INSERT INTO Standort(SBreitengrad, SLaengengrad, HName) "+
                                                    "VALUES (?,?,?)",[BGrad, LGrad, HWSName]);
                                                    
                //Prüfe den Rückgabewert, falls kein Fehler, dann suche nach einem Besitzer des Hardwaresystems und sende beim Vorhandenseins eines eine Benachrichtigung an FCM
                if(!ResultatInsert.error){
                    //Erfolgreich eingefügt
                    //Falls der erste Eintrag im Resultat nicht leer ist, dann ist eine Verknüpfung des Hardwaresystems und eines Benutzers vorhanden.
                    //Verknüpfung vorhanden, frage nach dem Token des Benutzers
                    const BName = Resultat[0].BName;
                    
                    const ResultatBToken = await db.pool.query("SELECT BToken FROM Benutzer WHERE BName = ?", [BName]);
                    //Prüfe den Rückgabewert, falls kein Fehler, dann sende eine Benachrichtigung an Firebase Cloud Messaging
                    
                    if(ResultatBToken[0]){
                           const BToken = ResultatBToken[0].BToken;
                        sendFcmMessage(getMessageFormat(BToken, HWSName, BGrad, LGrad));
                        return res.status(200).json({
                            message: 'Standort erfolgreich hinzugefügt.'
                        });
                    }else{
                        //Benutzertoken nicht vorhanden
                        return res.status(400).json({
                            status: 400,
                            message: 'Der Benutzername ist bereits vergeben.'
                            });
                    }
                }
            }
        }catch(err){
            //keine Verbindung zur Datenbank möglich
            return res.status(500).json({
                status: 500,
                message: 'Ein Fehler ist mit der Datenbank aufgetreten. Versuche es später erneut.'
                });
        }
    }else{
        //Validierung ist fehlgeschlagen, Fehler in der Eingabe
        return res.status(400).json({
            status: 400,
            message: 'Fehlerhafte Eingabe.'
            });
    }
});


router.get('/Suche', async (req, res) =>{
    //Eingabemöglichkeiten
    const HWSName = req.body.hname;
    const ZeitUnten = req.body.zunten;
    const ZeitOben = req.body.zoben;
    const BGradVorhanden = req.body.bvorhanden;
    const BGradUnten = req.body.bgunten;
    const BGradOben = req.body.bgoben;
    const LGradVorhanden = req.body.lvorhanden;
    const LGradUnten = req.body.lgunten;
    const LGradOben = req.body.lgoben;

    //Validierung der Eingabe
    //(HName, ZeitUntergrenze, ZeitObergrenze, BGVorhanden, BGUntergrenze, BGObergrenze, LGVorhanden,  LGUntergrenze, LGObergrenze)
    const ValError = SucheValidierung(HWSName, ZeitUnten, ZeitOben, BGradVorhanden, BGradUnten, BGradOben, LGradVorhanden, LGradUnten, LGradOben);
    if(!ValError.error)
    {
        //Validierung ist gelungen, kein Fehler in der Eingabe
        //Baue den SQL-String für die Abfrage der relevanten Standorte
        var SQLStart = "SELECT HName, SErfassungszeit, SBreitengrad, SLaengengrad FROM Standort WHERE HName = '"+HWSName+"'";

        //Vergleiche die Daten, um den oberen und den unteren Datum zu erkennen
        var SQLDatum;
        DatumEins = new Date(ZeitUnten).valueOf();
        DatumZwei = new Date(ZeitOben).valueOf();
        console.log(DatumEins+" "+ DatumZwei);

        //Je nach dem welches Datum vorher ist, muss dies andersrum eingefügt werden
        if(DatumEins<=DatumZwei)
        {
            SQLDatum = " AND SErfassungszeit between '"+ZeitUnten+"' and '"+ZeitOben+"'";  
        }else{
            SQLDatum = " AND SErfassungszeit between '"+ZeitOben+"' and '"+ZeitUnten+"'";
        }
        SQLStart += SQLDatum;

        //Fall Breitengrad vorhanden ist, vergleiche die Werte und füge diese der Anfrage hinzu
        if(BGradVorhanden)
        {
            var SQLBreitengrad;
            if(BGradUnten<=BGradOben)
            {
                SQLBreitengrad = " AND SBreitengrad between '"+BGradUnten+"' and '"+BGradOben+"'";
            }else{
                SQLBreitengrad = " AND SBreitengrad between '"+BGradOben+"' and '"+BGradUnten+"'";
            }
            SQLStart += SQLBreitengrad;
        }

        //Fall Längengrad vorhanden ist, vergleiche die Werte und füge diese der Anfrage hinzu
        if(LGradVorhanden)
        {
            var SQLLängengrad;
            if(LGradUnten<=LGradOben)
            {
                SQLLängengrad = " AND SLaengengrad between '"+LGradUnten+"' and '"+LGradOben+"'";
            }else{
                SQLLängengrad = " AND SLaengengrad between '"+LGradOben+"' and '"+LGradUnten+"'";
            }
            SQLStart += SQLLängengrad;
        }

        //Versuche, in der Datenbank nach vorhandenem Standorten zu fragen
        try{
            //Frage in Datenbank nach, ob Standorte mit angegebenen Parameter vorhanden sind
            //Rückgabe ist JSON-Objekt mit den gefundenen Standorten
            const Resultat = await db.pool.query(SQLStart,[]);

            //Falls der erste Eintrag im Resultat leer ist, dann sind keine Standorte mit den angegebenen Daten vorhanden
            if(!Resultat[0]){
                return res.status(400).json({
                    status: 400,
                    message: 'Die Kombination des eingegebenen Namen und Passworts ist nicht vorhanden.'
                    });
            }else{
            //Es sind Einträge vorhanden, sende diese in der Antwort
            return res.status(200).json({
                status: 'Erfolg',
                message: 'Standorte erfolgreich abgefragt',
                data: Resultat
                });
            }
        }catch(err){
            //keine Verbindung zur Datenbank möglich
            return res.status(500).json({
                status: 500,
                message: 'Ein Fehler ist mit der Datenbank aufgetreten. Versuche es später erneut.'
                });
        }
    }else{
        //Validierung ist fehlgeschlagen, Fehler in der Eingabe
        return res.status(400).json({
            status: 400,
            message: 'Fehlerhafte Eingabe.'
            });
    }
});
module.exports = router;