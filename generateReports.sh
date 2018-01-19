#!/bin/bash
#Script used to generate reports.
#Inspired by the scripts by Mateusz Tapa.

set -e

function all(){
    echo "Generating all reports. "
    mvn clean
    mvn install site
    sleep 1
    findbugs
    checkstyle
    jacoco
    sonar
    jdepend
}

function checkstyle(){
    goToRootDirectory
    xargs -a reports/checkstyle.txt firefox "$line"
}

function findbugs(){
    goToRootDirectory
    xargs -a reports/findbugs.txt firefox "$line"
}

function sonar(){
    goToRootDirectory
    mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent package sonar:sonar \
    -Dsonar.host.url=https://sonarcloud.io \
    -Dsonar.organization=smolamarcin-github \
    -Dsonar.login=a7d3b9bd4e1f813bfb10fcf74162522ed2161273
    xargs -a reports/sonar.txt firefox "$line"
}

function jacoco(){
    mvn jacoco:report
    goToRootDirectory;
    xargs -a reports/jacoco.txt firefox "$line"
}


function jdepend(){
    goToRootDirectory
    cd battleship-client
    mvn jdepend:generate
    goToRootDirectory
    cd battleship-server
    mvn jdepend:generate
    goToRootDirectory
    cd common
    mvn jdepend:generate
    xargs -a reports/jdepend.txt firefox "$line"
}

function goToRootDirectory(){
    cd $(git rev-parse --show-toplevel)
}

all
