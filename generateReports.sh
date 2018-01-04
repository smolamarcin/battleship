#!/bin/bash
#Script used to generate reports.
#Inspired by the scripts by Mateusz Tapa.

case $1 in
	"-all") all;;
*) usage
esac

function all(){
    mvn clean;
    mvn install site;
    findbugs;
    checkstyle;
    jacoco;
}

function checkstyle(){
cd $(git rev-parse --show-toplevel);
	xargs -a reports/checkstyle.txt firefox -new-tab "$line"
}

function findbugs(){
cd $(git rev-parse --show-toplevel);
    xargs -a reports/findbugs.txt firefox -new-tab "$line"
}

function sonar(){
cd $(git rev-parse --show-toplevel);
    mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent package sonar:sonar \
    -Dsonar.host.url=https://sonarcloud.io \
    -Dsonar.organization=smolamarcin-github \
    -Dsonar.login=a7d3b9bd4e1f813bfb10fcf74162522ed2161273;
    xargs -a scripts/reports/sonar.txt firefox -new-tab "$line"
}

function jacoco(){
mvn jacoco:report
    cd $(git rev-parse --show-toplevel);
    xargs -a scripts/reports/jacoco.txt firefox -new-tab "$line"
}

function usage(){
	echo "Usage: $0 [-all]"
	exit 1
}


