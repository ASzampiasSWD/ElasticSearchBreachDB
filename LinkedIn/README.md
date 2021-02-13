## Description

The Java code converts the LinkedIn .txt files into ElasticSearch compliant JSON.

1. Using the Java code, data to json conversion should take approximately 1 hour 44 mins on Desktop.
2. Using the Python code, it should take ~36 hours. 

<b><u>I highly recommend using the Java code.</u></b> If anyone wants to optimize the Python code, go ahead.  

## Necessary Steps

Change line <b>21</b> to the directory where the .txt files are located

Change line <b>38</b> to where you want the output files to be. Make sure that the directory exists.

## Stats 

<<<<<<< HEAD
The LinkedIn dump contains 82,209,228 email/password records. 

A total of 1644 JSON files will be generated. Each file contains 14,500 records.
=======
The LinkedIn dump contains 82,201,380 email/password records. 

A total of 2834 JSON files will be generated. Each file contains 29,001 records.
>>>>>>> e7b0fce3188c77af1b17ddd5e1ad77eaa7e80975

## File Manipulation

I did not include any records that did not have an email.

