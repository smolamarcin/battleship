#!/bin/bash

set -e

cd bom
mvn clean install;
cd ..
mvn clean install;
cd "battleship-server";
echo Please, enter port
read ip
java -jar target/battleship-server*.jar $ip
