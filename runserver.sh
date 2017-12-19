#!/bin/bash

cd "common";
echo Bulding common jar
mvn package;
cd ".."
cd "battleship-server";
echo Building server jar
mvn package;
echo Please, enter your IPv4 
read ip
java -cp target/battleship-server-1.0.1.jar com.epam.solid.nie.server.Main $ip

