import sbt._



object Dependencies{
  val resolvers = Seq(
    "sonatype releases"  at "http://oss.sonatype.org/content/repositories/releases",
    "sonatype snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",

    "scala-tools" at "http://scala-tools.org/repo-releases",

    "typesafe" at "http://repo.typesafe.com/typesafe/releases/",

    "conjars" at "http://conjars.org/repo",

    "spray io" at "http://repo.spray.io/",

    "cloudera" at "https://repository.cloudera.com/content/repositories/releases/",

    "scalaton snapshot repo"  at "http://ellchow.github.io/scalaton/snapshots",
    "scalaton release repo"  at "http://ellchow.github.io/scalaton/releases",

    "local m2 repo" at "file://" + Path.userHome.absolutePath + "/.m2/repository"
  )

  val common = Seq(
    "org.scalaz" % "scalaz-core_2.10" % "7.0.2",

    // "org.scalaj" %% "scalaj-http" % "0.3.6",
    // "io.spray"            %   "spray-can"     % "1.1-M8",
    // "io.spray"            %   "spray-routing" % "1.1-M8",
    // "org.rogach" %% "scallop" % "0.8.1",
    // "com.github.nscala-time" %% "nscala-time" % "0.2.0"
    // RootProject(uri("git://github.com/some/project.git#master")),

    "scalaton" %% "util" % "0.1-SNAPSHOT",
    "scalaton" %% "aggregate" % "0.1-SNAPSHOT",
    "scalaton" %% "doo" % "0.1-SNAPSHOT",

    "ch.qos.logback" % "logback-classic" % "1.0.9",
    "com.typesafe" %% "scalalogging-slf4j" % "1.0.1",

    "org.specs2" %% "specs2" % "2.1" % "test"
  )

  val sprayServer = Seq(
    "io.spray"            %   "spray-can"     % "1.2-M8",
    "io.spray"            %   "spray-routing" % "1.2-M8",
    "io.spray"            %   "spray-testkit" % "1.2-M8" % "test",
    "com.typesafe.akka"   %%  "akka-actor"    % "2.2.0-RC1",
    "com.typesafe.akka"   %%  "akka-testkit"  % "2.2.0-RC1" % "test"
  )


}
