## Description

The Java code converts the Dropbox .txt files into ElasticSearch compliant JSON.

1. Using the Java code, data to json conversion should take approximately <b>2 hours</b> on Desktop.
  
## Necessary Steps

Change line <b>19</b> to the directory where the .txt files are located

Change line <b>35</b> to where you want the output files to be. Make sure that the directory exists.

## Stats 

The LinkedIn dump contains 68,680,738 email/password records. 

A total of 1320 JSON files will be generated. Each file contains 52,001 records.

## File Manipulation

I did not include any records that did not have an email.

