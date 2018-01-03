#!/bin/bash

echo "starting pre-push hook"

mvn clean install -q

set -e 
$ false
echo "If you see this and push didn't go through, Maven clean install failed"
