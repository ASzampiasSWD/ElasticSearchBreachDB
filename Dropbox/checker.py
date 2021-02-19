#!/usr/bin/env python
# Author: Amanda Szampias
# Description: Check if all files imported into ElasticSearch.
import requests
import json

def getDocumentById(intCount):
  x = requests.get('http://localhost:9200/breach-dropbox/_doc/' + str(intCount))
  y = json.loads(x.text)
  return y["found"];
 
def main():
  intCount = 0
  for i in range(0, 1321):
    bFound = getDocumentById(intCount)
    if (bFound == False):
      print('File is missing: dropbox_' + str(i) + '.json')
    else:
      print('File is found: ' + str(i))
    intCount = intCount + 52001;
  print('-----------------------------')
 
if __name__ == "__main__":
  main()