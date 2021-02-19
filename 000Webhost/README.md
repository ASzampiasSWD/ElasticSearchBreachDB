## Overview

In approximately March 2015, the free web hosting provider 000webhost suffered a major data breach that exposed almost 15 million customer records. The data was sold and traded before 000webhost was alerted in October. The breach included names, email addresses and plain text passwords.

<b>Breach Date</b>: 1 March 2015<br />
<b>Compromised Data</b>: Email addresses, IP addresses, Names, Passwords<br />

-- haveIbeenPwned

## Description

The Java code converts the 000webhost.com.txt file into ElasticSearch compliant JSON.

Using the Java code, data to json conversion should take approximately <b>20 mins</b> on Desktop.
  
## Necessary Steps

Change line <b>19</b> to the directory where the .txt files are located

Change line <b>31</b> to where you want the output files to be. Make sure that the directory exists.

## Stats 

The 000Webhost dump contains 15,251,121 email/ip/name/password records. 

A total of 346 JSON files will be generated. Each file contains 44,001 records.

## File Manipulation

I did not include any records that did not have an email.
