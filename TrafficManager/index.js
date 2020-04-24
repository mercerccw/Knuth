let express = require("express");
let router = express.Router();

let database = require("database.js");

router.get('/', function (request, response) {
    console.log(database);
    response.send("Test");
});

router.get('/ais', function (request, response) {
    database.collection("aisMessage").find({}).toArray(function (error, res) {
        if (error) console.log(error);
        response.send(res);
    })
});

router.post('/ais', function (request, response) {
    database.collection("aisMessage").insertOne({"test": "Object"})
});