package janikdotzel.reactivetweets

import akka.stream.scaladsl.{Flow, Sink, Source}
import akka.{Done, NotUsed}
import janikdotzel.reactivetweets.twitter.JsonFormats._
import janikdotzel.reactivetweets.twitter.TwitterAPI._

import scala.concurrent.Future
import scala.concurrent.duration.DurationInt

object ReactiveTweets {

  // Model
  final case class Author(handle: String)

  final case class Hashtag(name: String)

  final case class Tweet(author: Author, timestamp: Long, body: String) {
    def hashtags: Set[Hashtag] =
      body
        .split(" ")
        .collect {
          case t if t.startsWith("#") => Hashtag(t.replaceAll("[^#\\w]", ""))
        }
        .toSet
  }

  val akkaTag = Hashtag("#akka")

  // Version 1
  val tweets: Source[Tweet, NotUsed] = Source(
    Tweet(Author("rolandkuhn"), System.currentTimeMillis, "#akka rocks!") ::
      Tweet(Author("patriknw"), System.currentTimeMillis, "#akka !") ::
      Tweet(Author("bantonsson"), System.currentTimeMillis, "#akka !") ::
      Tweet(Author("drewhk"), System.currentTimeMillis, "#akka !") ::
      Tweet(Author("ktosopl"), System.currentTimeMillis, "#akka on the rocks!") ::
      Tweet(Author("mmartynas"), System.currentTimeMillis, "wow #akka !") ::
      Tweet(Author("akkateam"), System.currentTimeMillis, "#akka rocks!") ::
      Tweet(Author("bananaman"), System.currentTimeMillis, "#bananas rock!") ::
      Tweet(Author("appleman"), System.currentTimeMillis, "#apples rock!") ::
      Tweet(Author("drama"), System.currentTimeMillis, "we compared #apples to #oranges!") ::
      Nil)

  //  val authors: Source[Author, NotUsed] =
  //    tweets.map(_.author)

  //  val getAuthors: Flow[Tweet,Author,NotUsed] =
  //    Flow[Tweet].filter(_.hashtags.contains(akkaTag)).map(_.author)

  //  val getBody: Flow[Tweet, String, NotUsed] =
  //    Flow[Tweet].map( tweet => tweet.body)

  val printer: Sink[Any, Future[Done]] = Sink.foreach(println)



  // Version 2: Json as a Source
  //  val tweetCountAkka: Source[ByteString, NotUsed] = {
  //    val json = scala.io.Source.fromResource("akka-tweet-count.json").mkString
  //    Source.single(ByteString(json))
  //  }
  //
  //  val tweetCountScala: Source[ByteString, NotUsed] = {
  //    val json = scala.io.Source.fromResource("scala-tweet-count.json").mkString
  //    Source.single(ByteString(json))
  //  }
  //
  //  val readJson: Flow[ByteString, String, NotUsed] =
  //    JsonReader.select("$.data[*].tweet_count")
  //      .map(byteString => byteString.utf8String)
  //
  //  val seq: Sink[Nothing, Future[Seq[Nothing]]] = Sink.seq


  // Version 3: REST/Streaming API as a Source
  val akkaQuery = "akka"
  val scalaQuery = "scala"
  val query = "akka%20scala%20lightbend"

  val recentTweets: Source[List[String], NotUsed] =
    Source.repeat(search("malediven"))

  val rateLimit: Flow[List[String], List[String], NotUsed] =
    Flow[List[String]].throttle(1, 10.second)

  val tweetPrinter: Sink[List[String], Future[Done]] =
    Sink.foreach{ tweets =>
      tweets.zipWithIndex.foreach{ case (element, index) => println(s"Message $index: $element") }
    }
}
