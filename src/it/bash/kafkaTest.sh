#!/usr/bin/env bash

docker ps -q --filter ancestor="zookeeper_test" | xargs -r docker stop
docker ps -q --filter ancestor="kafka_test" | xargs -r docker stop

docker run -d -p 2181:2181 --hostname zookeeper_test --net kafka_handler_kafka-net --ip 172.18.0.2 zookeeper_test
docker run -d -p 9092:9092 -p 29092:29092 --hostname kafka_test --net kafka_handler_kafka-net --ip 172.18.0.3 kafka_test

sbt "it:testOnly my.example.domain.KafkaTest"

docker ps -q --filter ancestor="kafka_test" | xargs -r docker stop
