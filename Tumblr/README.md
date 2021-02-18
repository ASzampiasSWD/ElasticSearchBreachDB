## Overview

In early 2013, tumblr suffered a data breach which resulted in the exposure of over 65 million accounts. The data was later put up for sale on a dark market website and included email addresses and passwords stored as salted SHA1 hashes.

<b>Breach Date</b>: 28 February 2013<br />
<b>Compromised Data</b>: Email Addresses, Passwords<br />

-- haveIbeenPwned


## Description

The Java code converts the Tumblr_2013_users.txt file into ElasticSearch compliant JSON.

Using the Java code, data to json conversion should take approximately <b>1 hour 30 mins</b> on Desktop.
  
## Necessary Steps

Change line <b>19</b> to the directory where Tumblr_2013_users.txt file is located.

Change line <b>31</b> to where you want the output files to be. Make sure that the directory exists.

## Stats 

The Tumblr dump contains 73,361,399 email/password records. 

A total of 1333 JSON files will be generated. Each file contains 55,001 records.

## File Manipulation

I did not include any records that did not have an email.
