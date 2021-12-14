const router = require('express').Router();
const db = require('../Datenbankverbindung');
const bcrypt = require('bcryptjs');

const {AnmeldeValidierung, RegistrierValidierung} = require('../Validierung/BenutzerValidierung');

//Auf Erhalt einer POST-Nachricht in der Route /anmeldung 

// to-do Beschreibung

//Führe Überprüfung aus und sende Antwort.

router.post('/anmeldung', async (req, res) => 
{
    //Eingabe
    const BName = req.body.name;
    const BPasswort = req.body.passwort;

    //Validierung der Eingabe
    const ValError = AnmeldeValidierung(BName, BPasswort)
    if(!ValError.error)
    {
        //Validierung ist gelungen, kein Fehler in der Eingabe
        //Versuche, in der Datenbank nach Benutzer zu fragen
        try{
            //Frage in Datenbank nach, ob ein Benutzer mit angegebenen Namen und Passwort vorhanden ist
            //Rückgabe ist JSON-Objekt mit den gefundenen Benutzern
            const Resultat = await db.pool.query("SELECT * FROM Benutzer WHERE BName = ? ", [BName]);

            //Falls der erste Eintrag im Resultat leer ist, dann ist kein Benutzer mit mit den angegebenen Namen vorhanden
            if(!Resultat[0]){
                return res.status(400).send('Die Kombination des eingegebenen Namen und Passworts ist nicht vorhanden.');
            }else{
                //entnehme das gehashte Passwort und prüfe mittels bcrypt, ob das eingegebene Passwort und das gehashte Password übereinstimmen
                const HashPasswort = Resultat[0].BPasswort;
                const validesPasswort = bcrypt.compareSync(BPasswort, HashPasswort);

                //Prüfe den Vergleich, bei Übereinstimmung Erfolg, bei Verschiedenheit Fehler
                if(validesPasswort){
                    return res.status(200).send('Erfolgreich angemeldet!');
                }else{
                    return res.status(400).send('Die Kombination des eingegebenen Namen und Passworts ist nicht vorhanden.');
                }
            }

        }catch(err){
            //keine Verbindung zur Datenbank möglich
            return res.status(500).send('Keine Verbindung zur Datenbank möglich. Versuche es später erneut.');
            console.log(err);
        }

    }else{
        //Validierung ist fehlgeschlagen, Fehler in der Eingabe
        return res.status(400).send("Fehlerhafte Eingabe.");
    }
});

router.post('/registrierung', async (req, res) =>{

     //Eingabe
     const BName = req.body.name;
     const BPasswort = req.body.passwort;
     const BToken = req.body.token;

     //Validierung der Eingabe
    const ValError = RegistrierValidierung(BName, BPasswort, BToken)
    if(!ValError.error)
    {
        //Validierung ist gelungen, kein Fehler in der Eingabe
        //Versuche, in der Datenbank nach vorhandenem Benutzernamen zu fragen
        try{
            //Erstelle Hash vom Passwort
            const salt = bcrypt.genSaltSync(10);
            const HashPasswort = bcrypt.hashSync(BPasswort, salt);
            //Füge neuen Benutzer ein
            const ResultatInsert = await db.pool.query("INSERT INTO Benutzer (BName, BPasswort, BToken)"+
                                                    " VALUES(?,?,?)",[BName, HashPasswort, BToken]);

            //Prüfe den Rückgabewert, falls kein Fehler, dann melde Erfolg
            if(!ResultatInsert.error){
                return res.status(200).send('Benutzer erfolgreich hinzugefügt!');
            }else{
                return res.status(500).send('Fehler beim Hinzufügen des Benutzers.')
            }
        }catch(err){
            return res.status(500).send('Keine Verbindung zur Datenbank möglich. Versuche es später erneut.');
        }
    }else{
        //Validierung ist fehlgeschlagen, Fehler in der Eingabe
        return res.status(400).send("Fehlerhafte Eingabe.");
    }
});

module.exports = router;