package janikdotzel.reactivetweets.twitter

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

object JsonFormats extends SprayJsonSupport with DefaultJsonProtocol {

  // Domain model
  final case class Tweet(id: String, text: String)
  final case class Meta(newest_id: String, oldest_id: String, result_count: Int, next_token: String)
  final case class SearchTweetsResponse(data: List[Tweet], meta: Meta)

  implicit val tweetJsonFormat: RootJsonFormat[Tweet] = jsonFormat2(Tweet)
  implicit val metaJsonFormat: RootJsonFormat[Meta] = jsonFormat4(Meta)
  implicit val searchTweetsResponseJsonFormat: RootJsonFormat[SearchTweetsResponse] = jsonFormat2(SearchTweetsResponse)
}
