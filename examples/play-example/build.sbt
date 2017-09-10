name := """play-request-tracer-example"""
organization := "com.alexitc"

version := "1.0-SNAPSHOT"

lazy val root = (project in file("."))
    .enablePlugins(PlayScala)

scalaVersion := "2.12.2"

libraryDependencies += guice

libraryDependencies += "com.google.inject" % "guice" % "4.1.0"
libraryDependencies += "com.alexitc" %% "play-request-tracer" % "0.1.0"
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.0" % Test

libraryDependencies += "org.slf4j" % "slf4j-api" % "1.7.25"
libraryDependencies += "ch.qos.logback" % "logback-core" % "1.2.3"
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3"
