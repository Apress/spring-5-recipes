#!/bin/bash

docker run --name sr4-neo4j -e NEO4J_AUTH=none -p 7474:7474 -p 7687:7687 -d neo4j:3.1