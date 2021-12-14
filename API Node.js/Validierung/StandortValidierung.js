//Validierung der Eingabe über Joi
const Joi = require('joi');


const NeuValidierung = (HWSName, Breitengrad, Laengengrad) => {
    const schema = Joi.object({
        name: Joi.string().min(5).required(),
        grad: Joi.number().required()
    });

    return schema.validate({name: BName, grad: Breitengrad, grad: Laengengrad});
}

const SucheValidierung = (HName, ZeitUntergrenze, ZeitObergrenze, BGVorhanden, BGUntergrenze, BGObergrenze, LGVorhanden,  LGUntergrenze, LGObergrenze) => {
    const schema = Joi.object({
        name: Joi.string().min(5).required(),
        vorhanden: Joi.boolean().required(),
        zeit: Joi.date().timestamp().iso().required(),
        grad: Joi.number()
    });

    return schema.validate({name: HName, zeit: ZeitUntergrenze, zeit: ZeitObergrenze,
                            vorhanden: BGVorhanden, grad: BGUntergrenze, grad: BGObergrenze,
                            vorhanden: LGVorhanden, grad: LGUntergrenze, grad: LGObergrenze});
}

module.exports.NeuValidierung = NeuValidierung;
module.exports.SucheValidierung = SucheValidierung;