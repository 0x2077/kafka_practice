FROM openjdk:8-jdk-buster

EXPOSE 9092 29092

WORKDIR opt

ENV KAFKA_LISTENERS PLAINTEXT://172.18.0.3:9092
# value passed back to client
ENV KAFKA_ADVERTISED_LISTENERS PLAINTEXT://172.18.0.3:9092
ENV KAFKA_LISTENER_SECURITY_PROTOCOL_MAP PLAINTEXT:PLAINTEXT

RUN curl https://apache-mirror.rbc.ru/pub/apache/kafka/2.4.1/kafka_2.12-2.4.1.tgz --output kafka_2.12-2.4.1.tgz

RUN tar -xzf kafka_2.12-2.4.1.tgz

WORKDIR kafka_2.12-2.4.1

RUN sed -i -e "s/zookeeper.connect=localhost:2181/zookeeper.connect=172.18.0.2:2181/g" config/server.properties

RUN sed -i -e "s/#listeners=PLAINTEXT:\/\/:9092/listeners=PLAINTEXT:\/\/172.18.0.3:29092,PLAINTEXT_HOST:\/\/localhost:9092/g" config/server.properties

RUN sed -i -e "s/#advertised.listeners=PLAINTEXT:\/\/your.host.name:9092/advertised.listeners=PLAINTEXT:\/\/172.18.0.3:29092,PLAINTEXT_HOST:\/\/localhost:9092/g" config/server.properties

RUN sed -i -e "s/#listener.security.protocol.map=PLAINTEXT:PLAINTEXT,SSL:SSL,SASL_PLAINTEXT:SASL_PLAINTEXT,SASL_SSL:SASL_SSL/listener.security.protocol.map=PLAINTEXT:PLAINTEXT,PLAINTEXT_HOST:PLAINTEXT/g" config/server.properties

CMD bin/kafka-server-start.sh config/server.properties