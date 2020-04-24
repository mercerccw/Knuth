const MongoClient = require("mongodb").MongoClient;

exports.database = null;

exports.connect = function(url) {
    MongoClient.connect(url, { useNewUrlParser: true }, (error, client) => {
        if(error) throw error;
        database = client.db();
    });
};