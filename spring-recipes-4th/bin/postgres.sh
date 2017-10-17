#!/bin/bash
docker run --name sr4-postgres -e POSTGRES_PASSWORD=password -p 5432:5432 -d postgres
