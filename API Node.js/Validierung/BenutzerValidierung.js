//Validierung der Eingabe über Joi
const Joi = require('joi');
const BenutzerValidierung = (BName, BPasswort, BToken) => {
    const schema = Joi.object({
        name: Joi.string().min(5).required(),
        passwort: Joi.string().min(6).required(),
        token: Joi.string().required()
    });

    return schema.validate({name: BName, passwort: BPasswort, token: BToken});
}

module.exports.BenutzerValidierung = BenutzerValidierung;