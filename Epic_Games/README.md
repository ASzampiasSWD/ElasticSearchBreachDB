## Overview

In August 2016, the Epic Games forum suffered a data breach, allegedly due to a SQL injection vulnerability in vBulletin. The attack resulted in the exposure of 252k accounts including usernames, email addresses and salted MD5 hashes of passwords.

<b>Breach date:</b> 11 August 2016<br />
<b>Compromised data:</b> Email addresses, Passwords, Usernames<br />

-- haveIbeenPwned

## Description

The Java code converts the EpicGames.com_August_2016_vB_.txt file into ElasticSearch compliant JSON.

Using the Java code, data to json conversion should take approximately <b>20 seconds</b> on Desktop.

## Necessary Steps

Change line <b>19</b> to the directory where the .txt files are located

Change line <b>31</b> to where you want the output files to be. Make sure that the directory exists.

## Stats 

The Epic Games dump contains 253,228 email/password records. 

A total of 5 JSON files will be generated. Each file contains ~50,001 records.

## File Manipulation

I did not include any records that did not have an email.

