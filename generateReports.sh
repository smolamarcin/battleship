#!/bin/bash
#Script used to generate reports.
#Inspired by the scripts by Mateusz Tapa.



function all(){
    echo "Generating all reports. "
    mvn clean
    mvn install site
    findbugs
    checkstyle
    jacoco
    sonar
    jdepend
}

function checkstyle(){
    goToRootDirectory
	xargs -a reports/checkstyle.txt firefox -new-tab "$line"
}

function findbugs(){
    goToRootDirectory
    xargs -a reports/findbugs.txt firefox -new-tab "$line"
}

function sonar(){
    set -e
    goToRootDirectory
    mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent package sonar:sonar \
    -Dsonar.host.url=https://sonarcloud.io \
    -Dsonar.organization=smolamarcin-github \
    -Dsonar.login=a7d3b9bd4e1f813bfb10fcf74162522ed2161273
    xargs -a reports/sonar.txt firefox -new-tab "$line"
}

function jacoco(){
    set -e
    mvn jacoco:report
    goToRootDirectory;
    xargs -a reports/jacoco.txt firefox -new-tab "$line"
}


function jdepend(){
    set -e
    goToRootDirectory
    cd battleship-client
    mvn jdepend:generate
    goToRootDirectory
    cd battleship-server
    mvn jdepend:generate
    goToRootDirectory
    cd common
    mvn jdepend:generate
    xargs -a reports/jdepend.txt firefox -new-tab "$line"
}

function goToRootDirectory(){
    cd $(git rev-parse --show-toplevel)
}

all