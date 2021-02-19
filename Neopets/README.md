## Overview

In May 2016, a set of breached data originating from the virtual pet website "Neopets" was found being traded online. Allegedly hacked "several years earlier", the data contains sensitive personal information including birthdates, genders and names as well as almost 27 million unique email addresses. Passwords were stored in plain text and IP addresses were also present in the breach.

<b>Breach Date</b>: 5 May 2013<br />
<b>Compromised Data</b>: Dates of birth, Email addresses, Genders, Geographic locations, IP addresses, Names, Passwords, Usernames<br />

-- haveIbeenPwned

## Description

The Java code converts the Neopets .txt files into ElasticSearch compliant JSON.

Using the Java code, data to json conversion should take approximately <b>1 hour</b> on Desktop.
  
## Necessary Steps

Change line <b>21</b> to the directory where the .txt files are located

Change line <b>36</b> to where you want the output files to be. Make sure that the directory exists.

## Stats 

The Neopets dump contains 35,878,022 email/password records. 

A total of 969 JSON files will be generated. Each file contains 44,001 records.

## File Manipulation

I did not include any records that did not have an email.
