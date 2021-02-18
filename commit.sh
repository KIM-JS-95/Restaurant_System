#!/bin/bash

echo ">git add . "

git add .

S=$(date)

echo ">$S"

git commit -m "$S"

echo "> successfully commiting"

exit 0
