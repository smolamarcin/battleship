#!/bin/bash

mvn clean install;
cd "battleship-server";
echo Please, enter your IPv4 
read ip
java -cp target/battleship-server-1.0.1.jar com.epam.solid.nie.server.Main $ip

