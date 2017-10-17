#!/bin/bash


docker run -d --name sr4-ldap -e LDAP_BASEDN="dc=springrecipes,dc=com" -e LDAP_PASSWORD=ldap -v $(pwd)/../ch07/recipe_7_3_v/src/main/resources/ldap:/ldif -p 1389:389 tnguyen1/ldap-opends