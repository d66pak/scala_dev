import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object WordCount {
  def main(args: Array[String]) {
    if (args.isEmpty || args.length < 2) {
      println("Missing arguments.")
      println("Arg 1: s3://bucket/input-file, Arg 2: s3://bucket/output-dir")
      sys.exit(1)
    }
    val inputFile = args(0)
    val outputPath = args(1)
    val spark = SparkSession.builder.appName("Word Count").getOrCreate()
    val sparkContext = spark.sparkContext
    val counts = sparkContext.textFile(inputFile)
      .flatMap(line => line.split(" "))
      .map(word => (word, 1))
      .reduceByKey(_+_)
    counts.saveAsTextFile(args(1))

    import spark.implicits._
    val df = counts.toDF("word", "count").orderBy(desc("count") )
    df.write.csv(outputPath + "/df")
    spark.stop()
  }
}