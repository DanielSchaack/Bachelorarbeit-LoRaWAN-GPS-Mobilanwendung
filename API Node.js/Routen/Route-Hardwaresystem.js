const router = require('express').Router();
const db = require('../Datenbankverbindung');
const bcrypt = require('bcryptjs');

const {VerknUebersichtValidierung, VerknuepfungValidierung, NeuValidierung} = require('../Validierung/HardwaresystemValidierung');

router.get('/uebersicht', async (req, res) =>{

    //Versuche, in der Datenbank nach vorhandenem Hardwaresystemen zu fragen
    try{
        //Frage in Datenbank nach allen Hardwaresystem nach
        //Rückgabe ist JSON-Objekt mit allen Hardwaresystemen
        const Resultat = await db.pool.query("SELECT HName, HRegDatum FROM Hardwaresystem");

        res.status(200).json({
            status: 'Erfolg',
            message: 'Hardwaresysteme erfolgreich abgefragt',
            data: Resultat
        });

    }catch(err){
        //keine Anfrage möglich
        return res.status(500).send('Ein Fehler ist mit der Datenbank aufgetreten. Versuche es später erneut.');
    }

});

router.get('/verknuepfunguebersicht', async (req, res) =>{
    //Eingabe
    const BName = req.body.bname;
    const HName = req.body.hname;

    //Validierung der Eingabe
    const ValError = VerknUebersichtValidierung(BName, HName)
    if(!ValError.error)
    {
        //Validierung ist gelungen, kein Fehler in der Eingabe

        try{
            //Frage in Datenbank nach allen Hardwaresystem mit angegebenen BNamen nach
            //Rückgabe ist JSON-Objekt mit allen Hardwaresystemen eines Benutzers
            const Resultat = await db.pool.query("SELECT HName, HRegDatum FROM Hardwaresystem WHERE BName = ?", [BName]);
    
            res.status(200).json({
                status: 'Erfolg',
                message: 'Hardwaresysteme erfolgreich abgefragt',
                data: Resultat
            });
    
        }catch(err){
            //keine Anfrage möglich
            return res.status(500).send('Ein Fehler ist mit der Datenbank aufgetreten. Versuche es später erneut.');
        }
    }else{
        //Validierung ist fehlgeschlagen, Fehler in der Eingabe
        return res.status(400).send("Fehlerhafte Eingabe.");
    }
});

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
            if(Resultat[0].BName == BName){
                return res.status(400).send('Das Hardwaresystem ist bereits mit diesem Benutzer verknüpft.');
            }else{
                const HashPasswort = Resultat[0].HPasswort;
                const validesPasswort = bcrypt.compareSync(BPasswort, HashPasswort);

                //Prüfe den Vergleich, bei Übereinstimmung Erfolg, bei Verschiedenheit Fehler
                if(validesPasswort){
                    try{
                        const ResultatUpdate = await db.pool.query("UPDATE Hardwaresystem SET BName = ? WHERE HName = ?", [BName, HName]);
                        if(!ResultatUpdate.error)
                        {
                            res.status(200).json({
                                status: 'Erfolg',
                                message: 'Hardwaresystem und Benutzer erfolgreich verknüpft.'
                            });
                        }else{
                            return res.status(500).send('Ein Fehler ist mit der Datenbank aufgetreten. Versuche es später erneut.');
                        }
                    }catch(err){
                        return res.status(500).send('Ein Fehler ist mit der Datenbank aufgetreten. Versuche es später erneut.');
                    }
                }else{
                    return res.status(400).send('Die Kombination des eingegebenen Hardwaresystemnamen und Passworts ist nicht vorhanden.');
                }
            }
        }catch(err){
            //keine Anfrage möglich
            return res.status(500).send('Ein Fehler ist mit der Datenbank aufgetreten. Versuche es später erneut.');
        }

    }else{
        //Validierung ist fehlgeschlagen, Fehler in der Eingabe
        return res.status(400).send("Fehlerhafte Eingabe.");
    }
});

router.post('/neu', async (req, res) =>{
    //Eingabe
    const BName = req.body.bname;
    const HName = req.body.hname;
    const BPasswort = req.body.hpasswort;

    //Validierung der Eingabe
    const ValError = NeuValidierung(BName, HName, BPasswort)
    if(!ValError.error)
    {
        try{
            //Erstelle Hash vom Passwort
            const salt = bcrypt.genSaltSync(10);
            const HashPasswort = bcrypt.hashSync(BPasswort, salt);
            //Füge neuen Benutzer ein
            const ResultatInsert = await db.pool.query("INSERT INTO Hardwaresystem (HName, HPasswort, BName)"+
                                            " VALUES(?,?,?)",[HName, HashPasswort, BName]);

            //Prüfe den Rückgabewert, falls kein Fehler, dann melde Erfolg
            if(!ResultatInsert.error){
                return res.status(200).send('Hardwaresystem erfolgreich hinzugefügt!');
            }else{
                return res.status(500).send('Fehler beim Hinzufügen des Hardwaresystems.')
            }
        }catch(err){
            return res.status(500).send('Fehler beim Hinzufügen des Hardwaresystems.')
        }
        
    }else{
        //Validierung ist fehlgeschlagen, Fehler in der Eingabe
        return res.status(400).send("Fehlerhafte Eingabe.");
    }
});
module.exports = router;