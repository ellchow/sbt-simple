import sbt._
import Keys._

import sbtassembly.Plugin._
import AssemblyKeys._

object BuildSettings {
  val buildOrganization = "ORGANIZATION"
  val buildVersion      = "0.1-SNAPSHOT"
  val buildScalaVersion = "2.10.0"
}

object ProjectBuild extends Build{
  import BuildSettings._

  val buildSettings = Defaults.defaultSettings ++ Seq (
    organization := buildOrganization,
    version      := buildVersion,
    scalaVersion := buildScalaVersion,
    shellPrompt  := ShellPrompt.buildShellPrompt
  )

  val compilerOptions = Seq(
    "-deprecation",
    "-feature",
    "-language:postfixOps",
    "-language:higherKinds",
    "-language:implicitConversions"
  )

  val allResolvers = Seq(
    "sonatype"  at "http://oss.sonatype.org/content/repositories/releases",
    "scala-tools" at "http://scala-tools.org/repo-releases",
    "typesafe" at "http://repo.typesafe.com/typesafe/releases/",
    "conjars" at "http://conjars.org/repo",
    "local m2 repo" at "file://" + Path.userHome.absolutePath + "/.m2/repository"
    // "sonatype snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
    // "cloudera" at "https://repository.cloudera.com/content/repositories/releases/"
  )

  lazy val scalatonProject = RootProject(uri("git://github.com/ellchow/scalaton.git#master"))

  val commonDeps = Seq(
    "org.scalaz" % "scalaz-core_2.10" % "7.0.0-M7",
    "ch.qos.logback" % "logback-classic" % "1.0.9",
    "org.specs2" %% "specs2" % "1.12.3" % "test"
  )

  val projectDeps = commonDeps ++ Seq(
    "commons-io" % "commons-io" % "2.1",
    "com.github.nscala-time" %% "nscala-time" % "0.2.0"
  )

  val publishLoc = Some(Resolver.file("local m2", new File( Path.userHome.absolutePath + "/.m2/repository" )))

  mergeStrategy in assembly <<= (mergeStrategy in assembly) { (old) =>
    {
      case x => {
        val oldstrat = old(x)
        if (oldstrat == MergeStrategy.deduplicate) MergeStrategy.first
        else oldstrat
      }
    }
  }

  lazy val projectSettings = Project (
    "PROJECT-NAME",
    file ("PROJECT-ROOT"),
    settings = buildSettings ++ assemblySettings ++ Seq(
      resolvers := allResolvers,
      libraryDependencies ++= projectDeps,
      scalacOptions := compilerOptions,
      publishTo := publishLoc)
  ) dependsOn (scalatonProject)

}

object ShellPrompt {
  object devnull extends ProcessLogger {
    def info (s: => String) {}
    def error (s: => String) { }
    def buffer[T] (f: => T): T = f
  }
  def currBranch = (
    ("git status -sb" lines_! devnull headOption)
      getOrElse "-" stripPrefix "## "
  )

  val buildShellPrompt = {
    (state: State) => {
      val currProject = Project.extract (state).currentProject.id
      "%s:%s:%s> ".format (
        currProject, currBranch, BuildSettings.buildVersion
      )
    }
  }
}




