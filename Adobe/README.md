## Overview

In October 2013, 153 million Adobe accounts were breached with each containing an internal ID, username, email, encrypted password and a password hint in plain text. The password cryptography was poorly done and many were quickly resolved back to plain text. The unencrypted hints also disclosed much about the passwords adding further to the risk that hundreds of millions of Adobe customers already faced.

<b>Breach Date</b>: 4 October 2013<br />
<b>Compromised Data</b>: Email addresses, Password hints, Passwords, Usernames<br />

-- haveIbeenPwned


## Description

The Java code converts the cred file into ElasticSearch compliant JSON.

Using the Java code, data to json conversion should take approximately <b>1 hour 30 mins</b> on Desktop.
  
## Necessary Steps

Change line <b>18</b> to the directory where the .txt files are located

Change line <b>30</b> to where you want the output files to be. Make sure that the directory exists.

## Stats 

The Adobe dump contains XNumber email/password records. 

A total of XNumber JSON files will be generated. Each file contains 55,001 records.

## File Manipulation

I did not include any records that did not have an email.
