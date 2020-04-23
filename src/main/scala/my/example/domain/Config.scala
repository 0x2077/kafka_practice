package my.example.domain

object Config {
  final val DefaultKafkaAppHost = sys.env.getOrElse("DefaultKafkaAppHost", "172.18.0.2")
  final val ZookeeperHost = "172.18.0.2"
  final val ZookeeperPort = 2181
  final val KafkaHost = "172.18.0.3"
  final val KafkaPort = 29092
  final val HdfsHost = "172.18.0.4"
  final val HdfsPort = 8020
}
