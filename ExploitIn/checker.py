#!/usr/bin/env python
# Author: Amanda Szampias
# Description: Check if all files imported into ElasticSearch.
import requests
import json

def getDocumentById(intCount):
  x = requests.get('http://localhost:9200/breach-exploitin/_doc/' + str(intCount))
  y = json.loads(x.text)
  return y["found"];
 
def main():
  intCount = 0;
  arMissing = [];
  bIfMissing = False
  for i in range(0, 11362):
    bFound = getDocumentById(intCount)
    if (bFound == False):
      bIfMissing = True
      arMissing.append(i)
      print('File is missing: exploitin_' + str(i) + '.json')
    #else:
      #print('File is found: ' + str(i))
    intCount = intCount + 70001
  print('-----------------------------')
  print('Any Files Missing: ' + str(bIfMissing))
  print(arMissing);
 
if __name__ == "__main__":
  main()