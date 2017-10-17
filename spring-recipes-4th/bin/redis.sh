#!/bin/bash

docker run --name sr4-redis -p 6379:6379 -d redis:3.2-alpine