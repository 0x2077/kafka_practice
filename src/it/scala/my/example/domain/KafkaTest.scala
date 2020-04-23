package my.example.domain

import org.scalatest._
import org.scalatest.matchers.should.Matchers
import my.example.domain.Config._
import kafka.admin.TopicCommand


class KafkaTest extends FlatSpec with Matchers {
  def createTopic(topicName: String, replicationFactor: Int = 1, partitions: Int = 4): Unit =  {

    val topicCommandOptions = Array[String](
      "--bootstrap-server", s"$KafkaHost:$KafkaPort", "--create", "--topic", topicName,
      "--replication-factor", replicationFactor.toString, "--partitions", partitions.toString
    )

    TopicCommand.main(topicCommandOptions)

    val topicCommandOptions2 = Array[String](
      "--bootstrap-server", s"$KafkaHost:$KafkaPort", "--list"
    )
    TopicCommand.main(topicCommandOptions2)
  }

  def writeMessage(topicName: String, message: String) =  {
    ???
  }

  def readMessage(topicName: String, cutOff: Option[String]=None) =  {
    ???
  }

  "Messsage" should "successfully written to Kafka" in {
    val topicName = "myLudicrousTopic"

    createTopic(topicName)

    1 should be (1)
  }

}
