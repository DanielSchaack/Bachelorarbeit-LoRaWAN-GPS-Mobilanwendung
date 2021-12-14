// Beschreibung Datei to-do
const dotenv = require('dotenv').config();

const express = require('express');
const app = express();

const BodyParser = require('body-parser');


app.use(BodyParser.json());
app.use(BodyParser.urlencoded({ extended: false }));

//Importiere Routen
const RouteBenutzer = require('./Routen/Route-Benutzer');
const RouteHardwaresystem = require('./Routen/Route-Hardwaresystem');
const RouteStandort = require('./Routen/Route-Standort');
//Routen-Middleware
app.use('/api/benutzer', RouteBenutzer);
app.use('/api/hardwaresystem', RouteHardwaresystem);
app.use('/api/standort', RouteStandort);

//Horche auf Verbindung zu Port 3000 mit Callback
app.listen(3000, () => console.log('Server up and running'));