## Overview

In July 2012, Yahoo! had their online publishing service "Voices" compromised via a SQL injection attack. The breach resulted in the disclosure of nearly half a million usernames and passwords stored in plain text. The breach showed that of the compromised accounts, a staggering 59% of people who also had accounts in the Sony breach reused their passwords across both services.

<b>Breach Date</b>: 11 July 2012<br />
<b>Compromised Data</b>: Email Addresses, Passwords<br />

-- haveIbeenPwned


## Description

The Java code converts the Yahoo .txt files into ElasticSearch compliant JSON.

Using the Java code, data to json conversion should take approximately <b>7 mins</b> on Desktop.
  
## Necessary Steps

Change line <b>19</b> to the directory where the .txt files are located

Change line <b>31</b> to where you want the output files to be. Make sure that the directory exists.

## Stats 

The Yahoo dump contains 5,731,225 email/password records. 

A total of 88 JSON files will be generated. Each file contains 65,001 records.

## File Manipulation

I did not include any records that did not have an email.
