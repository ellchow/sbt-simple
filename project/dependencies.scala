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

  // val scalatonUtil = ProjectRef(uri("git://github.com/ellchow/scalaton.git#master"), "util")

  val common = Seq(
    "org.scalaz" % "scalaz-core_2.10" % "7.0.4",
    // "org.scalaz" % "scalaz-effect_2.10" % "7.0.4",

    // "org.scalaj" %% "scalaj-http" % "0.3.6",
    // "org.rogach" %% "scallop" % "0.8.1",
    // "com.github.nscala-time" %% "nscala-time" % "0.2.0"

    // "com.github.ellchow" %% "scalaton-util" % "0.1-SNAPSHOT",
    // "com.github.ellchow" %% "scalaton-doo" % "0.1-SNAPSHOT",
    // "com.github.ellchow" %% "scalaton-aggregate" % "0.1-SNAPSHOT",

    // "commons-io" % "commons-io" % "2.1",

    // "org.scala-lang" %% "scala-pickling" % "0.8.0-SNAPSHOT",

    "ch.qos.logback" % "logback-classic" % "1.0.9",
    "com.typesafe" %% "scalalogging-slf4j" % "1.0.1",

    "org.scalatest" % "scalatest_2.10" % "2.0" % "test",
    "org.scalacheck" %% "scalacheck" % "1.10.1" % "test"
  )

  val sprayClient = Seq(
    "io.spray" % "spray-client" % "1.2-M8",
    "io.spray" % "spray-can" % "1.2-M8",
    "io.spray" % "spray-http" % "1.2-M8",
    "io.spray" % "spray-util" % "1.2-M8",
    "io.spray" %% "spray-json" % "1.2.5",
    "com.typesafe.akka" %%"akka-actor" % "2.2.0-RC1",
    "com.typesafe.akka" %%"akka-testkit" % "2.2.0-RC1" % "test"
  )

  val sprayServer = Seq(
    "io.spray" % "spray-can" % "1.2-M8",
    "io.spray" % "spray-routing" % "1.2-M8",
    "io.spray"  % "spray-testkit" % "1.2-M8" % "test",

    "com.typesafe.akka" %%"akka-actor" % "2.2.0-RC1",
    "com.typesafe.akka" %%"akka-testkit" % "2.2.0-RC1" % "test"
  )


}
