import sbt._
import Keys._

import sbtassembly.Plugin._
import AssemblyKeys._

import spray.revolver.RevolverPlugin.Revolver


object ProjectBuild extends Build{
  /** Settings **/
  val Organization = "ORGANIZATION"
  val Version      = "0.1-SNAPSHOT"
  val ScalaVersion = "2.10.2"

  val buildSettings = Defaults.defaultSettings ++ Seq (
    organization := Organization,
    version      := Version,
    scalaVersion := ScalaVersion,
    shellPrompt  := ShellPrompt.buildShellPrompt,
    resolvers := Dependencies.resolvers
  )

  val customAssemblySettings = Seq(
    mergeStrategy in assembly <<= (mergeStrategy in assembly) { old => {
      case x =>
        val oldstrat = old(x)
        if (oldstrat == MergeStrategy.deduplicate) MergeStrategy.first else oldstrat
    }}
  )

  val compilerOptions = Seq(
    // "-Xlog-implicits",
    "-deprecation",
    "-feature",
    "-language:_", //postfixOps
    "-language:higherKinds",
    "-language:implicitConversions",
    "-encoding",
    "utf8"
  )

  /** Shell Prompt **/
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
          currProject, currBranch, Version
        )
      }
    }
  }

  /** Projects **/

  lazy val standardProjectSettings = Project (
    "STANDARD-PROJECT-NAME",
    file ("STANDARD-PROJECT-ROOT"),
    settings = buildSettings ++ assemblySettings ++ customAssemblySettings ++ Seq(
      libraryDependencies ++= Dependencies.common,
      scalacOptions := compilerOptions,
      publishTo := Some(Resolver.file("local m2", new File( Path.userHome.absolutePath + "/.m2/repository" ))))
  )

  lazy val sprayServerSettings = Project (
    "SPRAYSERVER-PROJECT-NAME",
    file ("SPRAYSERVER-PROJECT-ROOT"),
    settings = buildSettings ++ assemblySettings ++ customAssemblySettings ++ Seq(
      libraryDependencies ++= Dependencies.common ++ Dependencies.sprayserver,
      scalacOptions := compilerOptions,
      publishTo := Some(Resolver.file("local m2", new File( Path.userHome.absolutePath + "/.m2/repository" )))) ++ Revolver.settings
  )

}






