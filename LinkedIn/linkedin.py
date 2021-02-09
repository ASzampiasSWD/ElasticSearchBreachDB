#!/usr/bin/env python
 
# Author: Amanda Szampias
# Notes: This file converts the LinkedIn breach database files into JSON format for ElasticSearch.
# Run this file IN the directory with the LinkedIn DB files. 
 
import os
import json
from pathlib import Path

def check_if_email_exists(line):
  if ('@' in line):
    return True
  else:
    return False
    
def build_metadata(id):
  wrapperJSON = {};
  strJSON = {}
  strJSON['_index'] = "breach"
  strJSON['_type'] = "LinkedIn"
  strJSON['_id'] = str(id);
  wrapperJSON['index'] = strJSON
  metadataJSON = json.dumps(wrapperJSON);
  json.loads(metadataJSON)
  #print(metadataJSON)
  #print(wrapperJSON)
  return metadataJSON
    
def create_json_object(line, id):
  arLines = line.split(":")
  if (len(arLines) >= 2):
    strJSON = {}
    strJSON['Email'] = arLines[0].strip()
    strJSON['Password'] = arLines[1].strip()
    strJSON['Username'] = '';
    strJSON['Name'] = "";
    strJSON['First Name'] = "";
    strJSON['Last Name'] = "";
    strJSON['Middle Name'] = "";
    strJSON['Website'] = "LinkedIn";
    strJSON['Phone'] = "";
    strJSON['Hash1'] = "";
    strJSON['Hash2'] = "";
    strJSON['Physical Address'] = "";
    strJSON['Mailing Address'] = "";
    strJSON['Password Hint'] = "";
    metadataJSON = build_metadata(id)
    dataJSON = json.dumps(strJSON);
    json.loads(dataJSON)
    #print(dataJSON)
    return metadataJSON, dataJSON
    
def check_output_directory():
  if not os.path.exists("output"):
    print('output folder does not exist. Creating output directory')
    Path("output").mkdir(parents=True, exist_ok=True)
    
def write_to_file(jsonMetaData, jsonData, fileCount):
  #linkedin_customNumber
  fileName = "output/linkedin_" + str(fileCount) + ".txt"
  f = open(fileName, "a+")
  f.write(jsonMetaData + "\n")
  f.write(jsonData + "\n")
  f.close()
  
def grap_files():
  listOfFiles = []
  for subdir, dirs, files in os.walk('./'):
    for file in sorted(files):
      if ('.txt' in file):
        listOfFiles.append(file.split('.txt')[0])
  listOfFiles.sort(key=int)
  print('Files being converted to JSON: ' + str(listOfFiles));
  return listOfFiles
  
def main():
  check_output_directory()
  breachfiles = grap_files()    
  id = 0
  maxLineSize = 0
  fileCount = 0
  for breachfile in breachfiles:
    fileFormat = str(breachfile) + '.txt'
    print('Currently converting: ' + fileFormat);
    with open(fileFormat) as openfileobject:
      for line in openfileobject:
        bEmail = check_if_email_exists(line)
        if (bEmail):
          metadataJSON, dataJSON = create_json_object(line, id)
          write_to_file(metadataJSON, dataJSON, fileCount);
          id += 1
          maxLineSize += 1
        # Do not go over 10MB for each file. The _bulk API will fail to import. 40,000 lines is a good limit.
        if (maxLineSize >= 29000):
          fileCount += 1
          maxLineSize = 0
           
  print('------------ FINISHED ------------')
 
if __name__ == "__main__":
  main()