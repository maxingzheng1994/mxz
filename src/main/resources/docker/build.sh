#!/usr/bin/env bash
#移除容器
docker rm -f `docker ps -a| grep mb25|awk '{print $1}'`;
docker build -t mxz/mb25:latest .;
docker-compose up -d;

