#! /bin/bash

set -e
cd "battleship-client";
echo Running mvn package command
mvn clean install;

echo Starting application
java -jar target/battleship-client-1.0.1.jar
