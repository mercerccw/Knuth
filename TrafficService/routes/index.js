var express = require('express');
var router = express.Router();
var mongodb = require('mongodb');
// Get a Mongo client to work with the Mongo server
var MClient = mongodb.MongoClient;
// Define where the MongoDB database is
var url = 'mongodb://localhost:27017/TrafficManager';
// Connect to the server


//Get function to grab all AIS messages in collection aisMessage
router.get('/', function(req, res){
  MClient.connect(url,{useUnifiedTopology: true}, (err, db) => {
    if (err) {
      console.log('Unable to connect to the Server', err);
    } else {
      console.log('Connection established to', url);
      let database = db.db("TrafficManager");
      // Get the documents collection 'aisMessage'
      let collection = database.collection('aisMessage');
      // Find all messages
      collection.find({}).toArray(function (err, result) {
        if (err) {
          res.send(err);
        } else if (result.length) {
          res.send(result);
        } else {
          res.send('No documents found');
        }
        //Close connection
        db.close();
      });
    }
  });
});

//Get all vessel AIS messages from MMSI identification
router.get('/MMSI/:mmsi', function (request, response) {
  const mmsiInput = parseInt(request.params.mmsi);
  MClient.connect(url, {useUnifiedTopology: true}, async (error, db) => {
    if (error) {
      console.log("Unable to connect to the Server", error);
    } else {
      console.log("Connection established to", url);
      let database = db.db("TrafficManager");
      //Get the documents collection 'aisMessage'
      let collection = database.collection("aisMessage");
      await collection.find({"MMSI": mmsiInput}).toArray(function (error, result) {
        if (error) {
          response.send(error);
        } else if (result.length) {
          response.send(result);
        } else {
          response.send('No documents found');
        }
        db.close();
      });
    }
  });
});

//Post function to upload AIS messages to the mongoDB
router.post('/TrafficService/:timestamp', function (request, response) {
  let aisMessages = request.body;
  let filteredMessages = [];
  for (let i = 0; i < aisMessages.length; i++) {
    //Filter out all non Class-A vessels
    if (aisMessages[i].Class === "Class A") {
      //Format out $ to prevent Mongo from thinking the data is an attack
      if (aisMessages[i].hasOwnProperty("Timestamp")) {
        aisMessages[i].Timestamp['date'] = aisMessages[i].Timestamp['$date'];
        delete aisMessages[i].Timestamp['$date'];
        aisMessages[i].Timestamp.date['numberLong'] = aisMessages[i].Timestamp.date['$numberLong'];
        delete aisMessages[i].Timestamp.date['$numberLong'];
      }
      filteredMessages.push(aisMessages[i]);
    }
  }
  MClient.connect(url, {useUnifiedTopology: true}, async (error, db) => {
    if (error) {
      console.log("Unable to connect to the Server", error);
    } else {
      console.log("Connection established to", url);
      let database = db.db("TrafficManager");
      //Get the documents collection 'aisMessage'
      let collection = database.collection("aisMessage");
      await collection.insertMany(filteredMessages);
      //Return newly updated collection, it doesn't require any null checks
      collection.find({}).toArray(function (error, result) {
        if (error) {
          console.log(error);
        }
        response.send(result);
        db.close();
      });
    }
  });
});



module.exports = router;