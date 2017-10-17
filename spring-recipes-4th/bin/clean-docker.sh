#!/bin/bash

docker rm -f $(docker ps -a -q)

docker rmi -f $(docker images | grep "^<none>" | awk '{print $3}')