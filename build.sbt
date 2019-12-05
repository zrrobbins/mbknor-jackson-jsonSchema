import ReleaseTransformations._
import sbtrelease.ReleasePlugin.autoImport.releaseStepCommand

lazy val commonSettings = Seq(
  organization := "com.github.zrrobbins",
  scalaVersion := "2.12.4",
  crossScalaVersions := Seq("2.10.7", "2.11.12", "2.12.8", "2.13.0-M5"),
  publishMavenStyle := true,
  publishArtifact in Test := false,
  pomIncludeRepository := { _ => false },
  useGpg := true,
  publishTo := Some(Resolver.file("file",  new File(Path.userHome.absolutePath+"/.m2/repository"))), // For local testing
  // publishTo := {
  //   val nexus = "https://oss.sonatype.org/"
  //   if (isSnapshot.value)
  //     Some("snapshots" at nexus + "content/repositories/snapshots")
  //   else
  //     Some("releases" at nexus + "service/local/staging/deploy/maven2")
  // },
  credentials += Credentials(Path.userHome / ".ivy2" / ".credentials_sonatype"),
  homepage := Some(url("https://github.com/zrrobbins/mbknor-jackson-jsonSchema")),
  licenses := Seq("MIT" -> url("https://github.com/zrrobbins/mbknor-jackson-jsonSchema/blob/master/LICENSE.txt")),
  startYear := Some(2016),
  scmInfo := Some(
    ScmInfo(
      url("https://github.com/zrrobbins/mbknor-jackson-jsonSchema"),
      "scm:git@github.com:zrrobbins/mbknor-jackson-jsonSchema.git"
    )
  ),
  developers := List(
    Developer(
      id    = "mbknor",
      name  = "Morten Kjetland",
      email = "example@example.com",
      url   = url("https://github.com/mbknor")
    )
  ),
  compileOrder in Test := CompileOrder.Mixed,
  javacOptions ++= Seq("-source", "1.8", "-target", "1.8"),
  scalacOptions ++= Seq("-unchecked", "-deprecation"),
  releaseCrossBuild := true,
  releasePublishArtifactsAction := PgpKeys.publishSigned.value,
  scalacOptions in(Compile, doc) ++= Seq(scalaVersion.value).flatMap {
    case v if v.startsWith("2.12") =>
      Seq("-no-java-comments") //workaround for scala/scala-dev#249
    case _ =>
      Seq()
  }
)


val jacksonVersion = "2.9.8"
val jacksonModuleScalaVersion = "2.9.8"
val slf4jVersion = "1.7.26"


lazy val deps  = Seq(
  "com.fasterxml.jackson.core" % "jackson-databind" % jacksonVersion,
  "javax.validation" % "validation-api" % "2.0.1.Final",
  "org.slf4j" % "slf4j-api" % slf4jVersion,
  "io.github.classgraph" % "classgraph" % "4.8.22",
  "com.google.guava" % "guava" % "25.0-jre", 
  "org.scalatest" %% "scalatest" % "3.0.7" % "test",
  "ch.qos.logback" % "logback-classic" % "1.2.3" % "test",
  "com.github.java-json-tools" % "json-schema-validator" % "2.2.10" % "test",
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % jacksonModuleScalaVersion % "test",
  "com.fasterxml.jackson.datatype" % "jackson-datatype-jdk8" % jacksonVersion % "test",
  "com.fasterxml.jackson.datatype" % "jackson-datatype-jsr310" % jacksonVersion % "test",
  "joda-time" % "joda-time" % "2.10.1" % "test",
  "com.fasterxml.jackson.datatype" % "jackson-datatype-joda" % jacksonVersion % "test"
)

lazy val root = (project in file("."))
  .settings(name := "mbknor-jackson-jsonSchema")
  .settings(commonSettings: _*)
  .settings(libraryDependencies ++= (deps))


releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  runTest,
  setReleaseVersion,
  commitReleaseVersion,
  tagRelease,
  publishArtifacts,
  setNextVersion,
  commitNextVersion,
  pushChanges,
  releaseStepCommand("sonatypeRelease")
)
