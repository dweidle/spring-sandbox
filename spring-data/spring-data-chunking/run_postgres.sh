#!/usr/bin/env bash
docker run -e POSTGRES_PASSWORD=mysecretpassword -p 5433:5432 -d postgres -c log_statement=all