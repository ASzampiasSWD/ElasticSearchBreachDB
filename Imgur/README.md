## Description

The Java code converts the Imgur .txt files into ElasticSearch compliant JSON.

1. Using the Java code, data to json conversion should take approximately 10 mins on Desktop.

## Necessary Steps

Change line <b>21</b> to the directory where the .txt files are located

Change line <b>38</b> to where you want the output files to be. Make sure that the directory exists.

## Stats 

The Imgur dump contains 1,755,559 email/password records. 

A total of 27 JSON files will be generated. Each file contains ~63,001 records.

## File Manipulation

I did not include any records that did not have an email.

