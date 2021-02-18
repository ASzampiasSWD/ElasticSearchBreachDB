## Overview

In November 2013, the makers of gaming live streaming and recording software XSplit was compromised in an online attack. The data breach leaked almost 3M names, email addresses, usernames and hashed passwords.

<b>Breach Date</b>: 7 November 2013<br />
<b>Compromised Data</b>: Email addresses, Names, Passwords, Usernames<br />

-- haveIbeenPwned


## Description

The Java code converts the xsplit.txt file into ElasticSearch compliant JSON.

Using the Java code, data to json conversion should take approximately <b>3 mins</b> on Desktop.
  
## Necessary Steps

Change line <b>18</b> to the directory where the .txt files are located

Change line <b>30</b> to where you want the output files to be. Make sure that the directory exists.

## Stats 

The XSplit dump contains 2,499,961 email/password records. 

A total of 55 JSON files will be generated. Each file contains 45,001 records.

## File Manipulation

I did not include any records that did not have an email.

