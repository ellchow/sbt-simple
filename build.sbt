import AssemblyKeys._

organization := "ORGANZATION"

name := "PROJECTNAME"

version := "0.1"

scalaVersion := "2.10.0"

checksums in update := Nil

libraryDependencies ++= Seq(
  "org.scalaz" % "scalaz-core_2.10" % "7.0.0-M7",
  // "org.scalaz" % "scalaz-iteratee_2.10" % "7.0.0-M7",
  // "io.spray" % "spray-can" % "1.1-M7",
  // "io.spray" % "spray-can" % "1.1-20130129",
  // "com.typesafe.akka" %% "akka-actor" % "2.1.0",
  // "org.apache.commons" % "commons-io" % "1.3.2"
  "ch.qos.logback" % "logback-classic" % "1.0.9",
  "org.specs2" %% "specs2" % "1.12.3" % "test"
)

scalacOptions ++= Seq(
  "-deprecation",
  "-feature",
  "-language:postfixOps",
  "-language:higherKinds",
  "-language:implicitConversions"
)

resolvers ++= Seq(
  "sonatype snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
  "sonatype"  at "http://oss.sonatype.org/content/repositories/releases",
  "scala tools" at "http://scala-tools.org/repo-releases",
  "spray repo" at "http://repo.spray.io",
  // "spray repo nightly" at "http://nightlies.spray.io",
  "typesafe" at "http://repo.typesafe.com/typesafe/releases/",
  "cloudera" at "https://repository.cloudera.com/content/repositories/releases/",
  "local m2 cache" at "file://" + Path.userHome.absolutePath + "/.m2/repository"
)


publishTo := Some(Resolver.file("local m2", new File( Path.userHome.absolutePath + "/.m2/repository" )))

assemblySettings

mergeStrategy in assembly <<= (mergeStrategy in assembly) { (old) =>
  {
    case x => {
      val oldstrat = old(x)
      if (oldstrat == MergeStrategy.deduplicate) MergeStrategy.first
      else oldstrat
    }
  }
}

