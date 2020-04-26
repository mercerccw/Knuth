var express = require('express');
var router = express.Router();
var mongodb = require('mongodb');
const dateFNS = require('date-fns')
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

//Get function to grab most recent AIS messages within the last 50 seconds (one per vessel)
router.get('/recentAIS/:timestamp', function (request, response) {
  let timeStamp = request.params.timestamp;
  let formatedTimeStamp = new Date(timeStamp);
  MClient.connect(url, {useUnifiedTopology: true}, async (error, db) => {
    if (error) {
      console.log(error);
    } else {
      console.log("Connection established to ", url);
      let database = db.db('TrafficManager');
      let aisCollection = database.collection('aisMessage');
      await aisCollection.find({}).toArray(function (error, result) {
        if (error) {
          response.send(error);
        } else if (result.length) {
          let filteredResult = [];
          for (let i = 0; i < result.length; i++) {
            let messageTimeStamp = new Date(result[i]['Timestamp']);
            let timeFrame = dateFNS.subSeconds(formatedTimeStamp, 50);
            //Logic to determine if ais message was within the 50 second time frame from the inputted parameter
            if (dateFNS.compareAsc(messageTimeStamp, timeFrame) === 1 && dateFNS.compareAsc(formatedTimeStamp, messageTimeStamp) === 1) {
              filteredResult.push(result[i]);
            }
          }
          response.send(filteredResult);
        } else {
          response.send('No documents found');
        }
        //Close connection
        db.close();
      })
    }
  });
});

//Clear all mongo collections, used to clear database before running ais_transmitter
router.delete('/deleteAll', function (request, response) {
  MClient.connect(url, {useUnifiedTopology: true}, async (error, db) => {
    if (error) {
      console.log(error);
    } else {
      console.log("Connection established to ", url);
      let database = db.db("TrafficManager");
      //Delete documents from collection 'aisMessage'
      let aisCollection = database.collection('aisMessage');
      await aisCollection.deleteMany({});
      //Delete documents from collection 'arrival/departure'
      let arrivalDepartureCollection = database.collection('arrival/departure');
      await arrivalDepartureCollection.deleteMany({});
      response.send("All documents deleted from all collections");
      db.close();
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
      filteredMessages.push(aisMessages[i]);
    }
  }
  if (filteredMessages.length === 0) {
    response.send("No messages to insert.");
    return;
  }
  MClient.connect(url, {useUnifiedTopology: true}, async (error, db) => {
    if (error) {
      console.log("Unable to connect to the Server", error);
    } else {
      console.log("Connection established to", url);
      let database = db.db("TrafficManager");
      //Get the documents collection 'aisMessage'
      let collection = database.collection("aisMessage");
      //Delete old AIS messages that contain the same MMSI from the to-be inserted list
      collection.find().forEach(function (document) {
        for (let i = 0; i < filteredMessages.length; i++) {
          if (document.MMSI === filteredMessages[i].MMSI) {
            console.log("Document deleted: ", document);
            console.log("Document inserted: ", filteredMessages[i]);
            collection.deleteOne(document);
          }
        }
      });
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