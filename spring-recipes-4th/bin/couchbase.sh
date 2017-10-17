#!/bin/bash

docker run -d --name sr4-couchbase -p 8091-8094:8091-8094 -p 11210:11210 couchbase