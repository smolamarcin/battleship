#!/bin/bash

echo "starting pre-push hook"
set -e
mvn clean install -q
$ true
echo "If you see this and push didn't go through, Maven clean install failed"
