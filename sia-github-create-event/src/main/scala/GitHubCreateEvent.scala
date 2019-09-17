/**
  * spark-submit --deploy-mode cluster --name "GitHub Create Event" --class GitHubCreateEvent
  * s3://murali.test/siagithubcreateevent_2.11-0.1.jar s3://murali.test/github-archive/ s3://murali.test/github-create-event-1/
  */

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import com.amazonaws.services.s3._

import scala.io.Source

object GitHubCreateEvent {
  def main(args: Array[String]): Unit = {

    if (args.isEmpty || args.length < 2) {
      println("Missing arguments.")
      println("Arg 1: s3://bucket/input-dir, Arg 2: s3://bucket/output-dir")
      sys.exit(1)
    }
    val inputPath = args(0)
    val outputPath = args(1)
    val gitHubEmployeeFile = args(2)

    val spark = SparkSession.builder().getOrCreate()
    val sc = spark.sparkContext

    val githubDf = spark.read.json(inputPath)
    githubDf.printSchema()
    println(s"Total number of events: ${githubDf.count()}")

    val pushesDf = githubDf.filter("type = 'PushEvent'")
    println(s"Total number of push events: ${pushesDf.count()}")
    pushesDf.show(5)

    val pushesPerUserDf = pushesDf.groupBy("actor.login").count()
    pushesPerUserDf.printSchema()

    val orderedPushesPerUserDf = pushesPerUserDf.orderBy(desc("count"))
    orderedPushesPerUserDf.show(5)

    val s3 = AmazonS3ClientBuilder.defaultClient()
    val employees = readS3File(gitHubEmployeeFile, s3).getLines().map(_.trim).toList
    val bcEmployees = sc.broadcast(employees)

    val employeeDf = orderedPushesPerUserDf.filter(orderedPushesPerUserDf("login")
      .isin(bcEmployees.value:_*))  // Convert list to varargs
    employeeDf.write.csv(outputPath)
    spark.stop()
  }

  def readS3File(s3FilePath: String, s3: AmazonS3): Source = {
    val s3Uri = new AmazonS3URI(s3FilePath)
    Source.fromInputStream(s3.getObject(s3Uri.getBucket, s3Uri.getKey).getObjectContent)
  }
}
