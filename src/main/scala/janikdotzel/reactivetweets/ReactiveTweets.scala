package janikdotzel.reactivetweets

import akka.stream.scaladsl.{Flow, Sink, Source}
import akka.{Done, NotUsed}

import scala.concurrent.Future

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

  // Sources
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

  val authors: Source[Author, NotUsed] =
    tweets.map(_.author)



  // Flows
  val getAuthors: Flow[Tweet,Author,NotUsed] =
    Flow[Tweet].filter(_.hashtags.contains(akkaTag)).map(_.author)

  val getBody: Flow[Tweet, String, NotUsed] =
    Flow[Tweet].map( tweet => tweet.body)


  //Sink
  val printer: Sink[Any, Future[Done]] = Sink.foreach(println)
}
