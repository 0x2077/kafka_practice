FROM openjdk:8-jdk-buster

EXPOSE 2181

WORKDIR opt

RUN curl https://apache-mirror.rbc.ru/pub/apache/kafka/2.4.1/kafka_2.12-2.4.1.tgz --output kafka_2.12-2.4.1.tgz

RUN tar -xzf kafka_2.12-2.4.1.tgz

WORKDIR kafka_2.12-2.4.1

CMD bin/zookeeper-server-start.sh config/zookeeper.properties