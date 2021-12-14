const mariadb = require('mariadb');

//Baue Verbindung zu Datenbank auf

var pool = mariadb.createPool({
    
    host: '144.76.114.183',
    user: 'daniel_db',
    password: '00001111hallo',
    database: 'daniel_bachelor',
    connectionLimit: 5
    
    /*
    host: process.env.NEW_DB_HOST,
    user: process.env.NEW_DB_BENUTZER,
    password: process.env.NEW_DB_PASSWORT,
    database: process.env.NEW_DB_DBNAME,
    connectionLimit: 5
    */
});

module.exports = Object.freeze({
    pool: pool
})