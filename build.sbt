import sbt._

name := "kafka_practice"

scalacOptions ++= Seq("-deprecation")

resolvers += Resolver.sonatypeRepo("releases")

lazy val buildSettings = Seq(
  organization := "my.example.domain",
  version := "0.1.0",
  scalaVersion := "2.11.12"
)

val kafka_practice = (project in file("."))
  .configs(IntegrationTest)
  .settings(inConfig(IntegrationTest)(Defaults.testSettings): _*)
  .settings(
    buildSettings,
    libraryDependencies ++= Seq(
      "org.apache.spark" %% "spark-core" % "2.4.0",
      "org.apache.spark" %% "spark-sql" % "2.4.0",

      "org.apache.hadoop" % "hadoop-hdfs" % "2.8.5" % "test, it",
      "org.apache.hadoop" % "hadoop-client" % "2.8.5" % "test, it",
      "org.apache.hadoop" % "hadoop-common" % "2.8.5" % "test, it",

      "org.apache.curator" % "curator-client" % "4.3.0" % "test, it",

      "org.apache.kafka" %% "kafka" % "2.4.1" % "test, it",
      "org.apache.kafka" % "kafka-clients" % "2.4.1" % "test, it",

      "org.scalatest" %% "scalatest" % "3.1.1" % "test, it"
    )
  )