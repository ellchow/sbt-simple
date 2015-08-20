import sbt._

object Dependencies{
  val resolvers = Seq(
    "sonatype releases"  at "http://oss.sonatype.org/content/repositories/releases",
    "sonatype snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",

    "scala-tools" at "http://scala-tools.org/repo-releases",

    "typesafe releases" at "http://repo.typesafe.com/typesafe/releases",

    "conjars" at "http://conjars.org/repo"
  )

  val common = Seq(
    "org.rogach" %% "scallop" % "0.9.5",

    "ch.qos.logback" % "logback-classic" % "1.0.9",
    "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0",

    "org.scalatest" %% "scalatest" % "2.2.4" % "test",
    "org.scalacheck" %% "scalacheck" % "1.11.3" % "test"
  )
}
