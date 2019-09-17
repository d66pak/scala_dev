name := "scala-repl"
 
version := "0.1"
 
scalaVersion := "2.11.8"

val sparkVersion = "2.3.3"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-sql" % sparkVersion % "provided",
  "org.apache.spark" %% "spark-core" % sparkVersion % "provided",
  "com.amazonaws" % "aws-java-sdk-s3" % "1.11.511" % "provided"
)
 
scalacOptions += "-deprecation"
 
initialCommands in console := """
    |""".stripMargin
