const router = require('express').Router();
const db = require('../Datenbankverbindung');
const bcrypt = require('bcryptjs');

const {BenutzerValidierung} = require('../Validierung/BenutzerValidierung');

//Prüfung von Anmeldedaten zur Anmeldung in der App
router.post('/anmeldung', async (req, res) => 
{
    //Eingabe
    const BName = req.body.name;
    const BPasswort = req.body.passwort;
    const BToken = req.body.token;

    //Validierung der Eingabe
    const ValError = BenutzerValidierung(BName, BPasswort, BToken);
    if(!ValError.error)
    {
        //Validierung ist gelungen, kein Fehler in der Eingabe
        //Versuche, in der Datenbank nach Benutzer zu fragen
        try{
            //Frage in Datenbank nach, ob ein Benutzer mit angegebenen Namen und Passwort vorhanden ist
            //Rückgabe ist JSON-Objekt mit den gefundenen Benutzern
            const Resultat = await db.pool.query("SELECT * FROM Benutzer WHERE BName = ? ", [BName]);

            //Falls der erste Eintrag im Resultat leer ist, dann ist kein Benutzer mit den angegebenen Namen vorhanden
            if(!Resultat[0]){
                return res.status(400).json({
                    status: 400,
                    message: 'Die Kombination des eingegebenen Namen und Passworts ist nicht vorhanden.'
                    });
            }else{
                //entnehme das gehashte Passwort und prüfe mittels bcrypt, ob das eingegebene Passwort und das gehashte Password übereinstimmen
                const HashPasswort = Resultat[0].BPasswort;
                const validesPasswort = bcrypt.compareSync(BPasswort, HashPasswort);

                //Prüfe den Vergleich, bei Übereinstimmung Erfolg der Anmeldung, anonsten Fehlschlag
                if(validesPasswort){
                    if(BToken != Resultat[0].BToken){
                        const ResultatUpdate = await db.pool.query("UPDATE Benutzer SET BToken = ? WHERE BName = ?", [BToken, BName]);
                        if(!ResultatUpdate.error)
                        {
                            return res.status(500).json({
                                status: 500,
                                message: 'Ein Fehler ist mit der Datenbank aufgetreten. Versuche es später erneut.'
                            });
                        }
                    }
                    return res.status(200).json({
                        status: 200,
                        message: 'Erfolgreich angemeldet!'
                    });
                }else{
                    return res.status(400).json({
                        status: 400,
                        message: 'Die Kombination des eingegebenen Namen und Passworts ist nicht vorhanden.'
                        });
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

//Registrierung eines neuen Benutzers
router.post('/registrierung', async (req, res) =>{

     //Eingabe
     const BName = req.body.name;
     const BPasswort = req.body.passwort;
     const BToken = req.body.token;

     //Validierung der Eingabe
    const ValError = BenutzerValidierung(BName, BPasswort, BToken)
    if(!ValError.error)
    {
        //Validierung ist gelungen, kein Fehler in der Eingabe
        //Versuche, in der Datenbank nach vorhandenem Benutzernamen zu fragen
        try{
            //Frage in Datenbank nach, ob ein Benutzer mit angegebenen Namen vorhanden ist
            //Rückgabe ist JSON-Objekt mit den gefundenen Benutzern
            const Resultat = await db.pool.query("SELECT * FROM Benutzer WHERE BName = ? ", [BName]);
            
            //Falls der erste Eintrag im Resultat vorhanden ist, dann ist der Benutzername bereits vergeben
            if(Resultat[0]){
                //Benutzername bereits vorhanden, Fehler als Antwort
                return res.status(400).json({
                    status: 400,
                    message: 'Der Benutzername ist bereits vergeben.'
                    });
            }else{
                //Benutzername ist frei, erstelle gehashtes Passwort und füge neuen Benutzer ein
                //Erstelle Hash vom Passwort
                const salt = bcrypt.genSaltSync(10);
                const HashPasswort = bcrypt.hashSync(BPasswort, salt);
                //Füge neuen Benutzer ein
                const ResultatInsert = await db.pool.query("INSERT INTO Benutzer (BName, BPasswort, BToken)"+
                                                    " VALUES(?,?,?)",[BName, HashPasswort, BToken]);

                //Prüfe den Rückgabewert, falls kein Fehler, dann melde Erfolg
                if(!ResultatInsert.error){
                    return res.status(200).json({
                        status: 200,
                        message: 'Benutzer erfolgreich hinzugefügt!'
                    });
                }else{
                    return res.status(500).json({
                        status: 500,
                        message: 'Fehler beim Hinzufügen des Benutzers.'
                        });
                }
            }   
        }catch(err){
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