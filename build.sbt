
name := "play-request-tracer"
organization := "com.alexitc"
scalaVersion := "2.12.2"

scalacOptions ++= Seq(
  "-Xfatal-warnings",
  "-unchecked",
  "-deprecation",
  "-feature",
  "-target:jvm-1.8",
  "-encoding", "UTF-8",
  "-Xfuture",
  "-Xlint:missing-interpolator",
  "-Yno-adapted-args",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  "-Ywarn-value-discard",
  "-Ywarn-unused",
  "-Ywarn-unused-import"
)

val playVersion = "2.6.3"

libraryDependencies += "com.typesafe.play" % "play_2.12" % playVersion
libraryDependencies += "com.google.inject" % "guice" % "4.1.0"
