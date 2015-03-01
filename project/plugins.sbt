resolvers += Resolver.url("artifactory", url("http://scalasbt.artifactoryonline.com/scalasbt/sbt-plugin-releases"))(Resolver.ivyStylePatterns)

addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.10.2")

addSbtPlugin("com.typesafe.sbt" % "sbt-pgp" % "0.8")

// addSbtPlugin("com.cavorite" % "sbt-avro" % "0.3.2")
// addSbtPlugin("com.github.gseitz" % "sbt-protobuf" % "0.3.1")

addSbtPlugin("org.scalastyle" %% "scalastyle-sbt-plugin" % "0.5.0")
