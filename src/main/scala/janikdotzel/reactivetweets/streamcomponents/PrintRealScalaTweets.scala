package janikdotzel.reactivetweets.streamcomponents

import akka.{Done, NotUsed}
import akka.stream.scaladsl.{Flow, Sink, Source}
import janikdotzel.reactivetweets.twitter.TwitterAPI

import scala.concurrent.Future
import scala.concurrent.duration.DurationInt

object PrintRealScalaTweets {

  val akkaQuery = "akka"
  val scalaQuery = "scala"
  val query = "akka%20scala%20lightbend"


}
