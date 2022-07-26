package janikdotzel.reactivetweets.twitter

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

object JsonFormats extends SprayJsonSupport with DefaultJsonProtocol {

  // Domain model
  final case class Data(id: String, text: String)
  final case class Meta(newest_id: String, oldest_id: String, result_count: Int, next_token: String)
  final case class SearchResponse(data: List[Data], meta: Meta)


  implicit val dataJsonFormat: RootJsonFormat[Data] = jsonFormat2(Data)
  implicit val metaJsonFormat: RootJsonFormat[Meta] = jsonFormat4(Meta)
  implicit val searchResponseJsonFormat: RootJsonFormat[SearchResponse] = jsonFormat2(SearchResponse)
}
