package janikdotzel.reactivetweets

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import janikdotzel.reactivetweets.streamcomponents.JsonSource._
import janikdotzel.reactivetweets.streamcomponents.PrintAkkaTweets._
import janikdotzel.reactivetweets.streamcomponents.PrintRealScalaTweets._
import spray.json.DefaultJsonProtocol

import scala.concurrent.ExecutionContextExecutor

object Main extends App
  with SprayJsonSupport
  with DefaultJsonProtocol {

  implicit val system: ActorSystem[Nothing] = ActorSystem(Behaviors.empty, "reactive-tweets")
  implicit val executionContext: ExecutionContextExecutor = system.executionContext











  //Add a sleep so the app won't terminate before the future has been finished
  Thread.sleep(10000)
}
