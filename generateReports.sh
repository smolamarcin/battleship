#!/bin/bash
#Script used to generate reports.
#Inspired by the scripts by Mateusz Tapa.

function usage(){
	echo "Usage: $0 [-site | -jdepend | -checkstyle | -findbugs | -jacoco | -sonar | -all]"
	exit 1
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
    xargs -a scripts/reports/sonar.txt firefox -new-tab "$line"
}
function jacoco(){
mvn jacoco:report
    cd $(git rev-parse --show-toplevel);
    xargs -a scripts/reports/jacoco.txt firefox -new-tab "$line"
}

function all(){
    mvn clean;
    mvn install site;
    findbugs;
    checkstyle;
    jacoco;

}


