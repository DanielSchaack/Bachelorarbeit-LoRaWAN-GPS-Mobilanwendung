//Validierung der Eingabe über Joi
const Joi = require('joi');

const VerknUebersichtValidierung = (BName) => {
    const schema = Joi.object({
        name: Joi.string().min(5).required()
    });

    return schema.validate({name: BName});
}

const VerknuepfungValidierung = (BName, HName, BPasswort) => {
    const schema = Joi.object({
        name: Joi.string().min(5).required(),
        passwort: Joi.string().min(8).required()
    });

    return schema.validate({name: BName, name: HName, passwort: BPasswort});
}

const NeuValidierung = (BName, BPasswort) => {
    const schema = Joi.object({
        name: Joi.string().min(5).required(),
        passwort: Joi.string().min(8).required()
    });

    return schema.validate({name: BName, passwort: BPasswort});
}

module.exports.VerknUebersichtValidierung = VerknUebersichtValidierung;
module.exports.VerknuepfungValidierung = VerknuepfungValidierung;
module.exports.NeuValidierung = NeuValidierung;