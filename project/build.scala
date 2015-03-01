import sbt._
import Keys._

import sbtassembly.Plugin._
import AssemblyKeys._

object ProjectBuild extends Build {
  /** Settings **/
  val Organization = "ORGANIZATION"
  val Version      = "0.1-SNAPSHOT"
  val ScalaVersion = "2.11.5"

  lazy val scalaStyleTask = taskKey[Unit]("scalaStyleTask")

  val buildSettings = Defaults.defaultSettings ++ org.scalastyle.sbt.ScalastylePlugin.Settings ++ Seq (
    organization := Organization,
    version      := Version,
    scalaVersion := ScalaVersion,
    shellPrompt  := ShellPrompt.buildShellPrompt,
    resolvers := Dependencies.resolvers
    // unmanagedResourceDirectories in Compile += file("resources")
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
    // "-language:_",
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

  val publishLoc = Some(Resolver.file("local m2", new File( Path.userHome.absolutePath + "/.m2/repository" )))

  /** Projects **/

  lazy val standardProject = Project (
    "STANDARD-PROJECT-NAME",
    file ("STANDARD-PROJECT-ROOT"),
    settings = buildSettings ++ assemblySettings ++ customAssemblySettings ++
      // sbtavro.SbtAvro.avroSettings ++
      // sbtprotobuf.ProtobufPlugin.protobufSettings ++
      Seq(
        libraryDependencies ++= Dependencies.common,
        scalacOptions := compilerOptions,
        // javaSource in sbtavro.SbtAvro.avroConfig <<= (sourceDirectory in Compile)(_ / "java"),
        publishTo := publishLoc,
        scalaStyleTask := org.scalastyle.sbt.PluginKeys.scalastyle.toTask("").value,
        (compile in Compile) <<= (compile in Compile) dependsOn scalaStyleTask
      )
  )// dependsOn ()

}
