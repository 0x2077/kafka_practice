# Hadoop
#### Build image
```
cd docker/hdfs
docker build -t hdfs_test .
```

###### stop all containers with imagename: hdfs_test
```
docker ps -q --filter ancestor="hdfs_test" | xargs -r docker stop
```

###### run container with image hdfs_test
```
docker run -p 8020:8020 -p 50070:50070 hdfs_test
```

##### run container with image hdfs_test (detached)
```
docker run -d -p 8020:8020 -p 50070:50070 --hostname hdfs_test --net kafka_handler_kafka-net --ip 172.18.0.4 hdfs_test 
```

# Zookeeper
#### Build image
```
cd docker/zookeeper
docker build -t zookeeper_test .
```

###### stop all containers with imagename: zookeeper_test
```
docker ps -q --filter ancestor="zookeeper_test" | xargs -r docker stop
```

##### run container with image zookeeper_test (detached)
```
docker run -d -p 2181:2181 --hostname zookeeper_test --net kafka_handler_kafka-net --ip 172.18.0.2 zookeeper_test
```

# Kafka
#### Build image
```
cd docker/kafka
docker build -t kafka_test .
```

###### stop all containers with imagename: zookeeper_test
```
docker ps -q --filter ancestor="kafka_test" | xargs -r docker stop
```

###### run container with image kafka_test
```
docker run -p 9092:9092 --hostname kafka_test --net kafka_handler_kafka-net --ip 172.18.0.3 kafka_test
```

###### run container with image kafka_test (detached)
```
docker run -d -p 9092:9092 --hostname kafka_test --net kafka_handler_kafka-net --ip 172.18.0.3 kafka_test
```

docker-compose build
docker-compose up --force-recreate

