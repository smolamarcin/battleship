#!/bin/bash

echo "starting pre-push hook"
set -e
mvn clean install -q
$ false
echo "If you see this and push didn't go through, Maven clean install failed"
