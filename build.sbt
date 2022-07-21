import Dependencies._

ThisBuild / scalaVersion     := "2.13.8"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

lazy val root = (project in file("."))
  .settings(
    name := "reactive-tweets",
    libraryDependencies += scalaTest % Test
  )

val AkkaVersion = "2.6.19"
libraryDependencies += "com.typesafe.akka" %% "akka-stream" % AkkaVersion
