var express = require('express');
var router = express.Router();
var mongodb = require('mongodb');
// Get a Mongo client to work with the Mongo server
var MClient = mongodb.MongoClient;
// Define where the MongoDB database is
var url = 'mongodb://localhost:27017/TrafficManager';
// Connect to the server

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

module.exports = router;
