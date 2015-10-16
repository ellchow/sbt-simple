import sbt._
import Keys._

object ProjectBuild extends Build {
  /** Settings **/
  val Organization = "ORGANIZATION"
  val Version      = "0.1-SNAPSHOT"
  val ScalaVersion = "2.11.7"

  lazy val scalaStyleTask = taskKey[Unit]("scalaStyleTask")

  val buildSettings = Defaults.defaultSettings ++ org.scalastyle.sbt.ScalastylePlugin.Settings ++ Seq (
    organization := Organization,
    version      := Version,
    scalaVersion := ScalaVersion,
    shellPrompt  := ShellPrompt.buildShellPrompt,
    resolvers := Dependencies.resolvers
  )

  val compilerOptions = Seq(
    "-deprecation",
    "-feature",
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
  lazy val standardProject = Project (
    "STANDARD-PROJECT-NAME",
    file ("STANDARD-PROJECT-ROOT"),
    settings = buildSettings ++
      Seq(
        libraryDependencies ++= Dependencies.common,
        scalacOptions := compilerOptions,
        scalaStyleTask := org.scalastyle.sbt.PluginKeys.scalastyle.toTask("").value,
        (compile in Compile) <<= (compile in Compile) dependsOn scalaStyleTask
      )
  )

}
