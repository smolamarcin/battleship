#! /bin/bash

set -e
cd "battleship-client";
echo Running mvn package command
mvn clean install;

echo Starting application
java -cp target/battleship-client-1.0.1.jar com.epam.solid.nie.client.ui.StartScene
