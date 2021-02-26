const express = require('express');
const app = express();
const elasticsearch= require('elasticsearch');
const path = require('path');
const port = 5000;

app.use(express.static(path.join(__dirname, 'public')));

const client = new elasticsearch.Client({
    host: 'localhost:9200',
    log: 'error',
    apiVersion: '7.2', // use the same version of your Elasticsearch instance
});

async function queryByEmail(email) {
   let answer = '';
    const response = await client.search({
        index: 'breach*',
        type: '_doc',
        body: {
          size: 100,
          query: {
            match_phrase_prefix: {
              "Email" : email
            }
          }
        }
      })

      for (const document of response.hits.hits) {
        //console.log('Hit: ', document);
        console.log(document["_source"]["Password"]);
        //document["_source"]["Password"] = "Replaced";
        answer += JSON.stringify(document) + "";
      }
      console.log("HITS: " + response.hits.hits.length);
      return answer;
}

app.get('/queryByEmail', async (req, res) => {
    var email = req.query.email;
    console.log('NODE EMAIL: ' + email);
    let esResponse = await queryByEmail(email);
    res.send(esResponse);
});

app.listen(port, () => {
    console.log(`Example app listening on port ${port}!`);
});