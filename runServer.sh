#!/bin/bash

set -e

cd bom
mvn clean install;
cd ..
mvn clean install;
cd "battleship-server";
echo Please, enter your IPv4 
read ip
java -jar target/battleship-server*.jar $ip
