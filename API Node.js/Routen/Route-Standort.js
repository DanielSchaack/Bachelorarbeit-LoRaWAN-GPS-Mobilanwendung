const router = require('express').Router();
const { string } = require('joi');
const superagent = require('superagent');
const db = require('../Datenbankverbindung');

const googlekey = require('../daniel-bachelor-firebase-adminsdk-nax4i-1233504736.json');

const firebase = require('firebase-admin');

firebase.initializeApp({
    credential: admin.credential.applicationDefault(),
});

//AccessCode

const MessageBody = require('../FirebaseCloudMessaging');

const {NeuValidierung, SucheValidierung} = require('../Validierung/StandortValidierung');

//to complete
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
            
            const ResultatInsert = await db.pool.query("INSERT INTO standort (HName, SBreitengrad, SLängengrad) "+
                                                "VALUES (?,?,?)", [HWSName, BGrad, LGrad]);
             
            //Prüfe den Rückgabewert, falls kein Fehler, dann melde Erfolg
            if(!ResultatInsert.error){
                //Erfolgreich eingefügt
                //Frage nach dem Besitzer des Hardwaresystems
                const ResultatBName = await db.pool.query("SELECT BName FROM Hardwaresystem WHERE HName = ?", [HWSName]);
                //Falls der erste Eintrag im Resultat nicht leer ist, dann ist eine Verknüpfung des Hardwaresystems und eines Benutzers vorhanden.
                if(ResultatBName[0]){
                    //Verknüpfung vorhanden, frage nach dem Token des Benutzers
                    const BName = ResultatBName[0].BName;
                    
                    const ResultatBToken = await db.pool.query("SELECT BToken FROM Benutzer WHERE BName = ?", [BName]);
                    //Prüfe den Rückgabewert, falls kein Fehler, dann sende eine Benachrichtigung an Firebase Cloud Messaging
                    
                    if(ResultatBToken[0]){
                        const BToken = ResultatBToken[0].BToken;
                        const Resultat = await superagent.post('https://fcm.googleapis.com/v1/projects/814043362978/messages:send').header({'Authorization': 'Bearer ' + accessToken})
                                                         .send(MessageBody(BToken, HWSName, BGrad, LGrad));
                    }else{

                    }
                }else{
                    //Benutzername bereits vergeben
                    return res.status(400).send('Der Benutzername ist bereits vergeben.');
                }
            }

        }catch(err){
            //keine Verbindung zur Datenbank möglich
            return res.status(500).send('Ein Fehler ist mit der Datenbank aufgetreten. Versuche es später erneut.');
            console.log(err);
        }
    }else{
        //Validierung ist fehlgeschlagen, Fehler in der Eingabe
        return res.status(400).send("Fehlerhafte Eingabe.");
    }
});

router.get('/Suche', async (req, res) =>{
    const HWSName = req.body.end_device_ids.device_id;
    const ZeitUnten = req.body.zunten;
    const ZeitOben = req.body.zoben;
    const BGradVorhanden = req.body.bvorhanden;
    const BGradUnten = req.body.bgunten;
    const BGradOben = req.body.bgoben;
    const LGradVorhanden = req.body.lvorhanden;
    const LGradUnten = req.body.lgunten;
    const LGradOben = req.body.lgoben;

    //Validierung der Eingabe
    const ValError = NeuValidierung(HWSName, BGrad, LGrad);
    if(!ValError.error)
    {
        //Validierung ist gelungen, kein Fehler in der Eingabe
        //Baue den SQL-String für die Abfrage der relevanten Standorte
        var SQLStart = "SELECT HName, SErfassungszeit, SBreitengrad, SLängengrad FROM Standort WHERE ";

        //Vergleiche die Daten, um den oberen und den unteren Datum zu erkennen
        var SQLDatum;
        DatumEins = new Date(ZeitUnten).valueOf();
        DatumZwei = new Date(ZeitOben).valueOf();
        if(DatumEins<=DatumZwei)
        {
            SQLDatum = "SErfassungszeit >= "+ZeitUnten+" and SErfassungszeit <= "+ZeitOben;  
        }else{
            SQLDatum = "SErfassungszeit >= "+ZeitOben+" and SErfassungszeit <= "+ZeitUnten;
        }
        SQLStart += SQLDatum;
        

        //Fall Breitengrad vorhanden ist, vergleiche die Werte und füge diese der Anfrage hinzu
        if(BGradVorhanden)
        {
            var SQLBreitengrad;
            if(BGradUnten<=BGradOben)
            {
                SQLBreitengrad = " and SBreitengrad >= "+BGradUnten+" and SBreitengrad <= "+BGradOben;
            }else{
                SQLBreitengrad = " and SBreitengrad >= "+BGradOben+" and SBreitengrad <= "+BGradUnten;
            }
            SQLStart += SQLBreitengrad;
        }

        //Fall Längengrad vorhanden ist, vergleiche die Werte und füge diese der Anfrage hinzu
        if(LGradVorhanden)
        {
            var SQLLängengrad;
            if(LGradUnten<=LGradOben)
            {
                SQLLängengrad = " and SLängengrad >= "+LGradUnten+" and SLängengrad <= "+LGradOben;
            }else{
                SQLLängengrad = " and SLängengrad >= "+LGradOben+" and SLängengrad <= "+LGradUnten;
            }
            SQLStart += SQLLängengrad;
        }

        //Versuche, in der Datenbank nach vorhandenem Standorten zu fragen
        try{
            //Frage in Datenbank nach, ob Standorte mit angegebenen Parameter vorhanden sind
            //Rückgabe ist JSON-Objekt mit den gefundenen Standorten
            const Resultat = await db.pool.query(SQLStart);

            //Falls der erste Eintrag im Resultat leer ist, dann ist kein Benutzer mit mit den angegebenen Namen vorhanden
            res.status(200).json({
                status: 'Erfolg',
                message: 'Standorte erfolgreich abgefragt',
                data: Resultat
            });

        }catch(err){
            //keine Verbindung zur Datenbank möglich
            return res.status(500).send('Ein Fehler ist mit der Datenbank aufgetreten. Versuche es später erneut.');
        }
    }else{
        //Validierung ist fehlgeschlagen, Fehler in der Eingabe
        return res.status(400).send("Fehlerhafte Eingabe.");
    }
});
module.exports = router;