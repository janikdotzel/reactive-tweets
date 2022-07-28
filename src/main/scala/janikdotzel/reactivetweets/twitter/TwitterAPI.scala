package janikdotzel.reactivetweets.twitter

import akka.http.scaladsl.Http
import akka.http.scaladsl.model.headers.{Authorization, OAuth2BearerToken}
import akka.http.scaladsl.model.{HttpRequest, HttpResponse}
import akka.http.scaladsl.unmarshalling.Unmarshal
import janikdotzel.reactivetweets.Main.system
import janikdotzel.reactivetweets.Main.system.executionContext
import janikdotzel.reactivetweets.twitter.JsonFormats._

import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, Future}

object TwitterAPI {

  val bearerToken: String = sys.env.get("BEARER_TOKEN") match {
    case Some(token) => token
    case None => throw new NullPointerException("Environment Variable for BEARER_TOKEN is missing.")
  }

  def search(query: String): List[String] = {
    val uri = "https://api.twitter.com/2/tweets/search/recent?query=" + query

    val httpResponse: Future[HttpResponse] = Http()
      .singleRequest(HttpRequest(uri = uri)
        .withHeaders(Authorization(OAuth2BearerToken(bearerToken))))

    val searchResponse: Future[SearchTweetsResponse] =
      httpResponse.flatMap { res => Unmarshal(res).to[SearchTweetsResponse] }

    val searchResult = Await.result(searchResponse, 5.seconds)

    val tweets = searchResult.data.map(tweet => tweet.text)
    tweets
  }
}
