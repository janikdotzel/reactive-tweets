package janikdotzel.reactivetweets.streamcomponents

import akka.{Done, NotUsed}
import akka.stream.alpakka.json.scaladsl.JsonReader
import akka.stream.scaladsl.{Flow, Sink, Source}
import akka.util.ByteString

import scala.concurrent.Future

object JsonSource {

  val tweetCountAkka: Source[ByteString, NotUsed] = {
    val json = scala.io.Source.fromResource("akka-tweet-count.json").mkString
    Source.single(ByteString(json))
  }

  val tweetCountScala: Source[ByteString, NotUsed] = {
    val json = scala.io.Source.fromResource("scala-tweet-count.json").mkString
    Source.single(ByteString(json))
  }

  val readTweetCount: Flow[ByteString, String, NotUsed] =
    JsonReader
      .select("$.data[*].tweet_count")
      .map(byteString => byteString.utf8String)

  val readStartTime: Flow[ByteString, String, NotUsed] =
    JsonReader
      .select("$.data[*].start")
      .map(byteString => byteString.utf8String)

  val akkaPrinter: Sink[String, Future[Done]] = Sink.foreach(count => println(s"Akka Tweet count: $count"))
  val scalaPrinter: Sink[String, Future[Done]] = Sink.foreach(count => println(s"Scala Tweet count: $count"))

}
