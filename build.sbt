import Dependencies._

ThisBuild / scalaVersion     := "2.13.8"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

val AkkaVersion = "2.6.19"
libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
  "com.lightbend.akka" %% "akka-stream-alpakka-json-streaming" % "3.0.4",
  "com.typesafe.akka" %% "akka-stream-testkit" % AkkaVersion % Test
)

lazy val root = (project in file("."))
  .settings(
    name := "reactive-tweets",
    libraryDependencies += scalaTest % Test
  )