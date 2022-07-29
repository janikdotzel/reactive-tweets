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

  val recentTweets: Source[List[String], NotUsed] =
    Source.repeat(TwitterAPI.search(scalaQuery))

  val rateLimit: Flow[List[String], String, NotUsed] =
    Flow[List[String]].mapConcat(list => list).throttle(1, 2.second)
}
