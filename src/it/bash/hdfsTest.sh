#!/usr/bin/env bash

docker ps -q --filter ancestor="hdfs_test" | xargs -r docker stop
docker run -d -p 8020:8020 -p 50070:50070 hdfs_test

sbt "it:testOnly my.example.domain.HdfsTest"

docker ps -q --filter ancestor="hdfs_test" | xargs -r docker stop
