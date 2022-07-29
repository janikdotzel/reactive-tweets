package janikdotzel.reactivetweets.streamcomponents

import akka.{Done, NotUsed}
import akka.stream.scaladsl.{Flow, Sink, Source}
import janikdotzel.reactivetweets.twitter.TwitterAPI
import scala.concurrent.Future
import scala.concurrent.duration.DurationInt

object PrintAkkaTweets {

  val akkaQuery = "akka"
  val scalaQuery = "scala"
  val query = "akka%20scala%20lightbend"

  val recentTweets: Source[List[String], NotUsed] =
    Source.repeat(TwitterAPI.search(scalaQuery))

  val rateLimit: Flow[List[String], List[String], NotUsed] =
    Flow[List[String]].throttle(1, 10.second)

  val tweetPrinter: Sink[List[String], Future[Done]] =
    Sink.foreach { tweets =>
      tweets.zipWithIndex.foreach { case (element, index) => println(s"Message $index: $element") }
    }
}
