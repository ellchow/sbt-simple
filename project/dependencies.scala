import sbt._

object Dependencies{
  val resolvers = Seq(
    "sonatype releases"  at "http://oss.sonatype.org/content/repositories/releases",
    "sonatype snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",

    "scala-tools" at "http://scala-tools.org/repo-releases",

    "typesafe releases" at "http://repo.typesafe.com/typesafe/releases",

    "conjars" at "http://conjars.org/repo",

    "spray io" at "http://repo.spray.io/",

    "cloudera" at "https://repository.cloudera.com/content/repositories/releases/"//,

    // "local m2 repo" at "file://" + Path.userHome.absolutePath + "/.m2/repository"
  )

  // val gitproject = ProjectRef(uri("git://github.com/user/repo.git#master"), "projectname")

  val common = Seq(
    "org.scalaz" %% "scalaz-core" % "7.1.0",
    "org.scalaz" %% "scalaz-effect" % "7.1.0",

    "org.rogach" %% "scallop" % "0.9.5",
    "com.github.nscala-time" %% "nscala-time" % "1.8.0",

    "commons-io" % "commons-io" % "2.4",

    // "org.apache.avro" % "avro" % "1.6.3",
    // "com.google.protobuf" % "protobuf-java" % "2.5.0",

    "ch.qos.logback" % "logback-classic" % "1.0.9",
    "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0",

    "org.scalatest" %% "scalatest" % "2.2.4" % "test",
    "org.scalacheck" %% "scalacheck" % "1.11.3" % "test"
  )
}
