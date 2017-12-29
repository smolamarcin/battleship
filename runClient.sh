#! /bin/bash

set -e

cd bom
mvn clean install;
cd ..
cd "battleship-client";
echo Running mvn package command
mvn clean install;

echo Starting application
java -jar target/battleship-client*.jar
