const express = require('express');
const app = express();
const elasticsearch= require('elasticsearch');
const path = require('path');
fs = require('fs');
const port = 5555;

app.use(express.static(path.join(__dirname, 'public')));

const client = new elasticsearch.Client({
    host: 'localhost:9200',
    log: 'error',
    apiVersion: '7.2', // use the same version of your Elasticsearch instance
});

async function queryByEmail(email, searchSize) {
   let answer = '';
   let jsonFile = '';
    const response = await client.search({
        index: 'breach*',
        type: '_doc',
        body: {
          size: searchSize,
          query: {
            match_phrase_prefix: {
              "Email" : email
            }
          }
        }
      })

      for (const document of response.hits.hits) {
        //console.log('Hit: ', document);
       // console.log(document["_source"]["Password"]);
        document["_source"]["Password"] = "Replaced for DEMO";
        jsonFile += JSON.stringify(document) + '\n';
      }
      answer = response.hits.hits;
      console.log("HITS: " + response.hits.hits.length);
      fs.writeFile('JSONResults.txt', jsonFile, function (err) {
        if (err) return console.log(err);
        console.log('JSON > JSONResults.txt');
      });
      return answer;
}

async function queryByUsername(username, searchSize) {
    let answer = '';
     const response = await client.search({
         index: 'breach*',
         type: '_doc',
         body: {
           size: searchSize,
           query: {
             match: {
               "Username" : username
             }
           }
         }
       })

       console.log('RESPONSE: ' + JSON.stringify(response.hits.hits));
 
       for (const document of response.hits.hits) {
         console.log('Hitttttt: ', document);
         //console.log(document["_source"]["Password"]);
         document["_source"]["Password"] = "Replaced for DEMO";
       }
       answer = response.hits.hits;
       console.log("HITS: " + response.hits.hits.length);
       return answer;
 }

 async function queryByIP(ip, searchSize) {
    let answer = '';
     const response = await client.search({
         index: 'breach*',
         type: '_doc',
         body: {
           size: searchSize,
           query: {
             match: {
               "IP Address" : ip
             }
           }
         }
       })
 
       for (const document of response.hits.hits) {
         console.log('Hit: ', document);
         //console.log(document["_source"]["Password"]);
         document["_source"]["Password"] = "Replaced for DEMO";
        // answer += JSON.stringify(document) + "\n";
       }
       answer = response.hits.hits;
       console.log("HITS: " + response.hits.hits.length);
       return answer;
 }

app.get('/queryByEmail', async (req, res) => {
    var email = req.query.email;
    var size = req.query.size;
    console.log('NODE EMAIL: ' + email);
    console.log('NODE SIZE: ' + size);
    let esResponse = await queryByEmail(email, size);
    res.send(esResponse);
});

app.get('/queryByUsername', async (req, res) => {
    var username = req.query.username;
    var size = req.query.size;
    console.log('NODE EMAIL: ' + username);
    console.log('NODE SIZE: ' + size);
    let esResponse = await queryByUsername(username, size);
    res.send(esResponse);
});

app.get('/downloadJSON', (req, res) => {
    console.log(__dirname + "\\" + "JSONResults.txt");
    res.sendFile(path.join(__dirname, "JSONResults.txt"));
});

app.get('/queryByIP', async (req, res) => {
    var ip = req.query.ip;
    var size = req.query.size;
    console.log('NODE EMAIL: ' + ip);
    console.log('NODE SIZE: ' + size);
    let esResponse = await queryByIP(ip, size);
    res.send(esResponse);
});

app.get('/', (req, res) => {
    //ar email = req.query.email;
    //console.log('NODE EMAIL: ' + email);
    //let esResponse = await queryByEmail(email);
    //res.send(esResponse);
    res.send('<h1>HELLO</h1>');
});


app.listen(port, () => {
    console.log(`Example app listening on port ${port}!`);
});