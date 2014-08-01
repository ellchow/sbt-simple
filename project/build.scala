import sbt._
import Keys._

import sbtassembly.Plugin._
import AssemblyKeys._

import spray.revolver.RevolverPlugin.Revolver


object ProjectBuild extends Build {
  /** Settings **/
  val Organization = "ORGANIZATION"
  val Version      = "0.1-SNAPSHOT"
  val ScalaVersion = "2.10.4"

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
    "-language:_",
    "-encoding",
    // "-optimize",
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
      sbtavro.SbtAvro.avroSettings ++
      // sbtprotobuf.ProtobufPlugin.protobufSettings ++
      Seq(
        libraryDependencies ++= Dependencies.common,
        scalacOptions := compilerOptions,
        javaSource in sbtavro.SbtAvro.avroConfig <<= (sourceDirectory in Compile)(_ / "java"),
        publishTo := publishLoc,
        scalaStyleTask := org.scalastyle.sbt.PluginKeys.scalastyle.toTask("").value,
        (compile in Compile) <<= (compile in Compile) dependsOn scalaStyleTask
      )
  )// dependsOn (Dependencies.scalatonUtil)

  lazy val sprayClientProject = Project (
    "SPRAYCLIENT-PROJECT-NAME",
    file ("SPRAYCLIENT-PROJECT-ROOT"),
    settings = buildSettings ++ assemblySettings ++ customAssemblySettings ++
      Seq(
        libraryDependencies ++= Dependencies.common ++ Dependencies.sprayClient,
        scalacOptions := compilerOptions,
        publishTo := publishLoc) ++ Revolver.settings
  )

  lazy val sprayServerProject = Project (
    "SPRAYSERVER-PROJECT-NAME",
    file ("SPRAYSERVER-PROJECT-ROOT"),
    settings = buildSettings ++ assemblySettings ++ customAssemblySettings ++
      Seq(
        libraryDependencies ++= Dependencies.common ++ Dependencies.sprayServer,
        scalacOptions := compilerOptions,
        publishTo := publishLoc) ++ Revolver.settings
  )

  lazy val aggregatedProject = Some(Project(
    "ROOT",
    file("."),
    settings = buildSettings ++ assemblySettings ++ customAssemblySettings ++ Seq(
      scalacOptions := compilerOptions,
      publishTo := publishLoc
    )
  ) aggregate(standardProject, sprayClientProject, sprayServerProject))


}
/* protobuf
rm -r /tmp/proto241 &&
mkdir -p /tmp/proto241 &&
cd /tmp/proto241 &&
curl -O https://protobuf.googlecode.com/files/protobuf-2.4.1.tar.gz &&
tar -xvzf protobuf-2.4.1.tar.gz &&
cd protobuf-2.4.1 &&
./configure &&
make &&
make check &&
sudo make install
 */
