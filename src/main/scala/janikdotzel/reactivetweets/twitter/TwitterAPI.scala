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

  val bearerToken = System.getenv("BEARER_TOKEN")
  if (bearerToken.isEmpty) println("Bearer Token is missing.") else println("Bearer Token is set correctly.")

  def search(query: String): List[String] = {
    val uri = "https://api.twitter.com/2/tweets/search/recent?query=" + query

    val httpResponse: Future[HttpResponse] = Http()
      .singleRequest(HttpRequest(uri = uri)
        .withHeaders(Authorization(OAuth2BearerToken(bearerToken))))

    val searchResponse: Future[SearchResponse] =
      httpResponse.flatMap { res => Unmarshal(res).to[SearchResponse] }

    val searchResult = Await.result(searchResponse, 5.seconds)

    val tweets = searchResult.data.map(tweet => tweet.text)
    tweets
  }
}
