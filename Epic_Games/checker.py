#!/usr/bin/env python
# Author: Amanda Szampias
# Description: Check if all files imported into ElasticSearch.
import requests
import json

def getDocumentById(intCount):
  x = requests.get('http://localhost:9200/breach-epicgames/_doc/' + str(intCount))
  y = json.loads(x.text)
  return y["found"];
 
def main():
  intCount = 0;
  bIfMissing = False
  for i in range(0, 6):
    bFound = getDocumentById(intCount)
    if (bFound == False):
      bIfMissing = True
      print('File is missing: epicgames_' + str(i) + '.json')
    #else:
      #print('File is found: ' + str(i))
    intCount = intCount + 50001
  print('-----------------------------')
  print('Any Files Missing: ' + str(bIfMissing))
 
if __name__ == "__main__":
  main()