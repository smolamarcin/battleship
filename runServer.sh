#!/bin/bash

mvn clean install;
cd "battleship-server";
echo Please, enter your IPv4 
read ip
java -jar target/battleship-server-1.0.1.jar $ip

