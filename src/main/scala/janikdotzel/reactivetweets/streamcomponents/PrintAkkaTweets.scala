package janikdotzel.reactivetweets.streamcomponents

import akka.stream.scaladsl.{Flow, Sink, Source}
import akka.{Done, NotUsed}
import janikdotzel.reactivetweets.TweetModel.{Author, Hashtag, Tweet, akkaTag, tweets}

import scala.concurrent.Future

object PrintAkkaTweets {

  val tweetSource: Source[Tweet, NotUsed] = Source(tweets)

  val filterByAkka: Flow[Tweet, Tweet, NotUsed] = Flow[Tweet].filter( tweet => tweet.hashtags.contains(akkaTag))

  val getBody: Flow[Tweet, String, NotUsed] = Flow[Tweet].map(tweet => tweet.body)

  val printer: Sink[Any, Future[Done]] = Sink.foreach(println)

}
