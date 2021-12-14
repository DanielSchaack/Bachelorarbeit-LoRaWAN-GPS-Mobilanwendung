//Validierung der Eingabe über Joi
const Joi = require('joi');

const AnmeldeValidierung = (BName, BPasswort) => {
    const schema = Joi.object({
        name: Joi.string().min(5).required(),
        passwort: Joi.string().min(6).required()
    });

    return schema.validate({name: BName, passwort: BPasswort});
}

const RegistrierValidierung = (BName, BPasswort, BToken) => {
    const schema = Joi.object({
        name: Joi.string().min(5).required(),
        passwort: Joi.string().min(6).required(),
        token: Joi.string().required()
    });

    return schema.validate({name: BName, passwort: BPasswort, token: BToken});
}

module.exports.AnmeldeValidierung = AnmeldeValidierung;
module.exports.RegistrierValidierung = RegistrierValidierung;