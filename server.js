var express = require('express');
var app = express();
var mongoClient = require("mongodb").MongoClient;
var bodyParser = require("body-parser");
app.use(bodyParser.urlencoded({
    extended: true
}));
app.use(bodyParser.json());
var assert = require('assert');


// set the port of our application
// process.env.PORT lets the port be set by Heroku
var port = process.env.PORT || 8080;

// set the view engine to ejs
app.set('view engine', 'ejs');

// make express look in the public directory for assets (css/js/img)
app.use(express.static(__dirname + '/public'));

// set the home page route
app.get('/', function (req, res) {

    // ejs render automatically looks in the views folder
    res.render('index');
});

app.post("/map", function (req, res) {

    var lat = req.body.lat;
    var log = req.body.log;
    var date = req.body.date;

    mongoClient.connect('mongodb://ashwin:ashwin@ds023704.mlab.com:23704/gmapdata', function (err, db) {
        assert.equal(null, err);
        console.log('sucessfully connected');
        db.collection('userdata').insertOne({
            'latitude': lat,
            'logitude': log,
            'date': date
        }, function (err, result) {

            assert.equal(null, err);
            console.log("sucessfully inserted");

        });

    });

});

app.get('/map', function (req, res) {

    mongoClient.connect('mongodb://ashwin:ashwin@ds023704.mlab.com:23704/gmapdata', function (err, db) {
        assert.equal(null, err);
        console.log('sucessfully connected');
        db.collection('userdata').find({}).toArray(function (err, data) {
            if (err || data.length<=0) {
                res.statusCode = 404;
                return res.send('Error 404: No data found');
            }
            res.json({
                "result": data
            });

        });

    });
})


app.listen(port, function () {
    console.log('Our app is running on http://localhost:' + port);
});