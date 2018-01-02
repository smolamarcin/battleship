#!/bin/bash

echo Creating pre-push hook.
set -e
cp pre-push.sh .git/hooks

cd .git/hooks
rm pre-push.sample

chmod 770 pre-push.sh
echo Hook created.
