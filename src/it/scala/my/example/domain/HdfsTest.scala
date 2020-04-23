package my.example.domain

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.scalatest._
import org.scalatest.matchers.should.Matchers
import my.example.domain.Config._


class HdfsTest extends FlatSpec with Matchers {

  "A file" should "successfully be created in HDFS" in {
    val hadoop: FileSystem = {
      val conf = new Configuration()
      conf.set("fs.defaultFS", s"hdfs://$HdfsHost:$HdfsPort]")
      FileSystem.get(conf)
    }
    val path = "/user/root/test_file_1"

    val res =
      try {
        if (hadoop.exists(new Path(path))) hadoop.delete(new Path(path), true)

        hadoop.createNewFile(new Path(path))
        hadoop.exists(new Path(path))
      } catch {
        case _: Exception => false
      } finally {
        hadoop.close()
      }

    res should be (true)
  }




}
