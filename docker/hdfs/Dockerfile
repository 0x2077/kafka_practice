FROM openjdk:8-jdk-buster

EXPOSE 50010 50020 50070 50075 8020 9000

RUN ssh-keygen -t rsa -P '' -f ~/.ssh/id_rsa
RUN cat ~/.ssh/id_rsa.pub >> ~/.ssh/authorized_keys
RUN chmod 0600 ~/.ssh/authorized_keys

RUN apt-get update
RUN apt-get install openssh-server openssh-client -y

WORKDIR opt

RUN curl http://apache-mirror.rbc.ru/pub/apache/hadoop/common/hadoop-2.8.5/hadoop-2.8.5.tar.gz --output hadoop-2.8.5.tar.gz
RUN tar -xzf hadoop-2.8.5.tar.gz

RUN pwd

RUN cd /usr/local && ln -s /opt/hadoop-2.8.5 hadoop

ENV HADOOP_PREFIX /usr/local/hadoop
ENV HADOOP_COMMON_HOME /usr/local/hadoop
ENV HADOOP_HDFS_HOME /usr/local/hadoop
ENV HADOOP_MAPRED_HOME /usr/local/hadoop
ENV HADOOP_YARN_HOME /usr/local/hadoop
ENV HADOOP_CONF_DIR /usr/local/hadoop/etc/hadoop
ENV YARN_CONF_DIR $HADOOP_PREFIX/etc/hadoop

RUN chmod +x $HADOOP_PREFIX/etc/hadoop/*-env.sh

RUN $HADOOP_PREFIX/bin/hdfs namenode -format

RUN mkdir $HADOOP_PREFIX/input
RUN cp $HADOOP_PREFIX/etc/hadoop/*.xml $HADOOP_PREFIX/input

ADD ssh_config /root/.ssh/config
RUN chmod 600 /root/.ssh/config
RUN chown root:root /root/.ssh/config

ADD core-site.xml.template $HADOOP_PREFIX/etc/hadoop/core-site.xml.template
RUN sed s/HOSTNAME/172.18.0.4/ $HADOOP_PREFIX/etc/hadoop/core-site.xml.template > $HADOOP_PREFIX/etc/hadoop/core-site.xml
ADD hdfs-site.xml $HADOOP_PREFIX/etc/hadoop/hdfs-site.xml

RUN echo "Port 2122" > /etc/ssh/sshd_config

RUN sed -i 's@${JAVA_HOME}@'"${JAVA_HOME}"'@' $HADOOP_PREFIX/etc/hadoop/hadoop-env.sh

CMD service ssh start && $HADOOP_PREFIX/etc/hadoop/hadoop-env.sh && $HADOOP_PREFIX/sbin/start-dfs.sh && \
    $HADOOP_PREFIX/bin/hdfs dfs -mkdir -p /user/root && \
    $HADOOP_PREFIX/bin/hdfs dfs -chmod 777 /user/root && \
    $HADOOP_PREFIX/bin/hdfs dfs -put $HADOOP_PREFIX/etc/hadoop input && \
    while true; do sleep 1000; done