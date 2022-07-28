package janikdotzel.reactivetweets

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.DefaultJsonProtocol
import scala.concurrent.ExecutionContextExecutor
import StreamComponents._


object Main extends App
  with SprayJsonSupport
  with DefaultJsonProtocol {

  implicit val system: ActorSystem[Nothing] = ActorSystem(Behaviors.empty, "reactive-tweets")
  implicit val executionContext: ExecutionContextExecutor = system.executionContext

  // Print all tweet
  tweets.to(printer).run()

  // Print all authors who wrote akka tweets
  tweets.via(getAuthors).to(printer).run()

  // Prints all messages
  tweets.via(getBody).to(printer).run()

  // Print the tweet hourly tweet count of akka
  tweetCountAkka.via(readJson).to(printer).run()

  // Print the tweet hourly tweet count of scala
  tweetCountScala.via(readJson).to(printer).run()

  // Print one akka tweet every second (via the Twitter API)
//  recentTweets.via(rateLimit).to(tweetPrinter).run()












  //  TwitterAPI.search("scala")


  //  implicit val searchResponseJsonFormat: RootJsonFormat[SearchResponse] = jsonFormat2(SearchResponse)
  //
  //  val bearerToken = System.getenv("BEARER_TOKEN")
  //  val response: Unit = Http()
  //    .singleRequest(HttpRequest(uri = "https://api.twitter.com/2/tweets/search/recent?query=akka")
  //      .withHeaders(Authorization(OAuth2BearerToken(bearerToken))))
  //    .flatMap { res => Unmarshal(res).to[SearchResponse] }
  //    .map(res => res.data).onComplete {
  //    case Failure(exception) => throw exception
  //    case Success(tweets) => tweets.foreach(tweet => println(tweet))
  //  }


  //Add a sleep so the app won't terminate before the future has been finished
  Thread.sleep(60000)
}
