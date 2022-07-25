package janikdotzel.reactivetweets.twitter

import akka.NotUsed
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.headers.{Authorization, OAuth2BearerToken}
import akka.http.scaladsl.model.{HttpRequest, HttpResponse}
import akka.http.scaladsl.unmarshalling.Unmarshal
import akka.stream.scaladsl.Source
import janikdotzel.reactivetweets.Main.system
import janikdotzel.reactivetweets.Main.system.executionContext
import janikdotzel.reactivetweets.twitter.JsonFormats._

object TwitterAPI {

  val bearerToken = System.getenv("BEARER_TOKEN")
  if (bearerToken.isEmpty) println("Bearer Token is missing.") else println("Bearer Token is set correctly.")

  def search(query: String): Source[HttpResponse, NotUsed] = {
    val uri = "https://api.twitter.com/2/tweets/search/recent?query=" + query

    Source.future(
      Http()
        .singleRequest(HttpRequest(uri = uri)
          .withHeaders(Authorization(OAuth2BearerToken(bearerToken))))
        .flatMap{ res => Unmarshal(res).to[SearchResponse] })

  }
}
