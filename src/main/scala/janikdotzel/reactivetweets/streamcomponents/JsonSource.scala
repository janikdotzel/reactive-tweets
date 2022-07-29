package janikdotzel.reactivetweets.streamcomponents

import akka.NotUsed
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

  val readJson: Flow[ByteString, String, NotUsed] =
    JsonReader.select("$.data[*].tweet_count")
      .map(byteString => byteString.utf8String)

  val seq: Sink[Nothing, Future[Seq[Nothing]]] = Sink.seq
}
