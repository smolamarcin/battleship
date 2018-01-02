#!/bin/bash

echo "starting pre-push hook"

mvn clean install

if [ $? -ne 0 ]; then
    "Error during maven clean install. Push won't be possible."
    exit 1
fi
