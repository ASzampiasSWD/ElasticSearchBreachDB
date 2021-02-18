## Overview

In September 2013, the online image sharing community imgur suffered a data breach. A selection of the data containing 1.7 million email addresses and passwords surfaced more than 4 years later in November 2017. Although imgur stored passwords as SHA-256 hashes, the data in the breach contained plain text passwords suggesting that many of the original hashes had been cracked. imgur advises that they rolled over to bcrypt hashes in 2016.

<b>Breach date</b>: 1 September 2013<br />
<b>Compromised data</b>: Email addresses, Passwords<br />

-- haveIbeenPwned

## Description

The Java code converts the imgur.txt file into ElasticSearch compliant JSON.

Using the Java code, data to json conversion should take approximately <b>2 mins</b> on Desktop.

## Necessary Steps

Change line <b>18</b> to the directory where the .txt files are located

Change line <b>30</b> to where you want the output files to be. Make sure that the directory exists.

## Stats 

The Imgur dump contains 1,755,546 email/password records. 

A total of 27 JSON files will be generated. Each file contains ~63,001 records.

## File Manipulation

I did not include any records that did not have an email.

