#!/bin/bash
#Script used to generate reports.
#Inspired by the scripts by Mateusz Tapa.
#
function usage(){
	echo "Usage: $0 [-site | -jdepend | -checkstyle | -findbugs | -jacoco | -sonar | -all]"
	exit 1
}

function checkstyle(){
cd $(git rev-parse --show-toplevel)
	if [[ ! $(find . -name 'checkstyle.html') ]]; then
	mvn site checkstyle:checkstyle
	fi
	xargs -a reports/checkstyle.txt firefox -new-tab "$line"
}

case $1 in
#	"-site") site;;
#	"-jdepend") jdepend;;
	"-checkstyle") checkstyle;;
#	"-findbugs") findbugs;;
#	"-jacoco") jacoco;;git s
#	"-sonar") sonar;;
#	"-all") all;;
*) usage
esac


