const router = require('express').Router();
const db = require('../Datenbankverbindung');
const bcrypt = require('bcryptjs');

const {VerknUebersichtValidierung, VerknuepfungValidierung, NeuValidierung} = require('../Validierung/HardwaresystemValidierung');

//Gebe eine Übersicht über alle vorhandenen Hardwaresysteme
router.get('/uebersicht', async (req, res) =>{

    //Versuche, in der Datenbank nach vorhandenem Hardwaresystemen zu fragen
    try{
        //Frage in Datenbank nach allen Hardwaresystem nach
        //Rückgabe ist JSON-Objekt mit allen Hardwaresystemen
        const Resultat = await db.pool.query("SELECT HName, HRegDatum FROM Hardwaresystem");

        return res.status(200).json({
            status: 200,
            message: 'Hardwaresysteme erfolgreich abgefragt',
            data: Resultat
        });

    }catch(err){
        //keine Anfrage möglich
        return res.status(500).json({
            status: 500,
            message: 'Ein Fehler ist mit der Datenbank aufgetreten. Versuche es später erneut.'
            });
    }

});

//Gebe Übersicht über alle verknüpften Hardwaresysteme eines Nutzers
router.get('/verknuepfunguebersicht', async (req, res) =>{
    //Eingabe
    const BName = req.body.bname;

    //Validierung der Eingabe
    const ValError = VerknUebersichtValidierung(BName)
    if(!ValError.error)
    {
        //Validierung ist gelungen, kein Fehler in der Eingabe
        try{
            //Frage in Datenbank nach allen Hardwaresystem mit angegebenen Benutzer nach
            //Rückgabe ist JSON-Objekt mit allen Hardwaresystemen eines Benutzers
            const Resultat = await db.pool.query("SELECT HName, HRegDatum FROM Hardwaresystem WHERE BName = ?", [BName]);
    
            //Falls der erste Eintrag im Resultat leer ist, dann sind keine Verknüpfungen vom angegebenen Benutzer vorhanden
            if(!Resultat[0]){
                return res.status(400).json({
                    status: 400,
                    message: 'Es sind keine Verknüpfungen vom angegebenen Benutzer vorhanden.'
                    });
            }else{
            //Es sind Einträge vorhanden, sende diese in der Antwort
            return res.status(200).json({
                status: 'Erfolg',
                message: 'Hardwaresystemverknüpfungen erfolgreich abgefragt.',
                data: Resultat
                });
            }    
        }catch(err){
            //keine Anfrage möglich
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

//Füge eine Verknüpfung zwischen einem Benutzer und einem Hardwaresystem ein
router.post('/verknuepfung', async (req, res) =>{
    //Eingabe
    const BName = req.body.bname;
    const HName = req.body.hname;
    const BPasswort = req.body.hpasswort;

    //Validierung der Eingabe
    const ValError = VerknuepfungValidierung(BName, HName, BPasswort)
    if(!ValError.error)
    {
        //Validierung ist gelungen, kein Fehler in der Eingabe
        try{
            //Frage in Datenbank nach den genannten Hardwaresystem nach
            //Rückgabe ist JSON-Objekt mit dem gesuchten Hardwaresystem 
            const Resultat = await db.pool.query("SELECT HName, HPasswort, BName FROM Hardwaresystem WHERE HName = ?", [HName]);
    
            //Falls der Benutzername des Hardwaresystems der Name des gesuchten Benutzers ist, dann ist die Verknüpfung doppelt und somit überflüßig
            //Ansonsten ist der Benutzername des Hardwaresystems unterschiedlich vom gesuchten Benutzer ist
                //und prüfe, ob das eingegebene Passwort mit dem Passwort des Hardwaresystems übereinstimmt.
                //Falls dem so ist, dann überschreibe die bereits verknüpften Benutzernamen mit dem neuen Namen.
            
            //Hardwaresystem nicht vorhanden
            if(!Resultat[0]){
                return res.status(400).json({
                    status: 400,
                    message: 'Es ist kein Hardwaresystem unter dem angegebenen Namen vorhanden.'
                    });
            }//Hardwaresystem vorhanden, prüfe, ob der Benutzer sich erneut mit den Hardwaresystem verknüpfen möchte
            else if(Resultat[0].BName == BName){
                return res.status(400).json({
                    status: 400,
                    message: 'Das Hardwaresystem ist bereits mit diesem Benutzer verknüpft.'
                    });
            }//Neuer Benutzer möchte sich mit dem Hardwaresystem verknüpfen
            else{
                const HashPasswort = Resultat[0].HPasswort;
                const validesPasswort = bcrypt.compareSync(BPasswort, HashPasswort);

                //Prüfe den Vergleich, bei Übereinstimmung Erfolg, bei Verschiedenheit Fehler
                if(validesPasswort){
                    const ResultatUpdate = await db.pool.query("UPDATE Hardwaresystem SET BName = ? WHERE HName = ?", [BName, HName]);
                    if(!ResultatUpdate.error)
                    {
                        return res.status(200).json({
                            status: 200,
                            message: 'Hardwaresystem und Benutzer erfolgreich verknüpft.'
                        });
                    }else{
                        return res.status(500).json({
                            status: 500,
                            message: 'Ein Fehler ist mit der Datenbank aufgetreten. Versuche es später erneut.'
                            });
                    }
                }else{
                    return res.status(400).json({
                        status: 400,
                        message: 'Die Kombination des eingegebenen Hardwaresystemnamen und Passworts ist nicht vorhanden.'
                        });
                }
            }
        }catch(err){
            //keine Anfrage möglich
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

//Füge ein neues Hardwaresystem ein
router.post('/neu', async (req, res) =>{
    //Eingabe
    const HName = req.body.hname;
    const HPasswort = req.body.hpasswort;

    //Validierung der Eingabe
    const ValError = NeuValidierung(HName, HPasswort)
    if(!ValError.error)
    {
        try{
            const Resultat = await db.pool.query("SELECT * FROM Hardwaresystem WHERE HName = ?",[HName]);

            //Falls der erste Eintrag im Resultat leer ist, dann füge das Hardwaresystem hinzu
            if(!Resultat[0]){
                //Erstelle Hash vom Passwort
                const salt = bcrypt.genSaltSync(10);
                const HashPasswort = bcrypt.hashSync(HPasswort, salt);
                //Füge neuen Benutzer ein
                const ResultatInsert = await db.pool.query("INSERT INTO Hardwaresystem (HName, HPasswort, BName)"+
                                            " VALUES(?,?,'admin')",[HName, HashPasswort]);

                //Prüfe den Rückgabewert, falls kein Fehler, dann melde Erfolg
                if(!ResultatInsert.error){
                    return res.status(200).json({
                        status: 200,
                        message: 'Hardwaresystem erfolgreich hinzugefügt!'
                    });
                }else{
                    return res.status(500).json({
                        status: 500,
                        message: 'Fehler beim Hinzufügen des Hardwaresystems.'
                        });
                }
            }else{
                //Es sind Einträge vorhanden, somit sende Fehler als Antwort, da somit der angegebene Hardwaresystemname bereits vergeben ist
                return res.status(400).json({
                    status: 400,
                    message: 'Der angegebene Hardwaresystemname ist bereits vergeben.'
                    });
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