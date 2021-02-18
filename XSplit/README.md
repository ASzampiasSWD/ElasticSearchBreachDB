## Overview

In November 2013, the makers of gaming live streaming and recording software XSplit was compromised in an online attack. The data breach leaked almost 3M names, email addresses, usernames and hashed passwords.

Breach date: 7 November 2013<br />
Date added to HIBP: 8 August 2015<br />
Compromised accounts: 2,983,472<br />
Compromised data: Email addresses, Names, Passwords, Usernames<br />

- haveIbeenPwned


## Description

The Java code converts the XSplit .txt files into ElasticSearch compliant JSON.

1. Using the Java code, data to json conversion should take approximately 20 mins on Desktop.
  
## Necessary Steps

Change line <b>18</b> to the directory where the .txt files are located

Change line <b>29</b> to where you want the output files to be. Make sure that the directory exists.

## Stats 

The XSplit dump contains 68,680,738 email/password records. 

A total of 55 JSON files will be generated. Each file contains 45,001 records.

## File Manipulation

I did not include any records that did not have an email.

