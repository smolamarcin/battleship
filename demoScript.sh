#! /bin/bash
set -e
#
echo Hello Tomasz. It is is our demo. Please enter a directory name:
read foldername
git clone https://github.com/smolamarcin/battleship $foldername
cd "$foldername"

./statisticsScript.sh