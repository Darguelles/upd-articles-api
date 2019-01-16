#!/usr/bin/env bash

docker build -t "upd-articles-api" .
docker run -p 8080:8080 upd-articles-api
