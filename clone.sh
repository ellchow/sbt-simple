#!/usr/bin/env bash

git clone -b master https://github.com/ellchow/sbt-simple.git $1 && \
rm -rfv $1/.git/ && \
rm -v $1/clone.sh

