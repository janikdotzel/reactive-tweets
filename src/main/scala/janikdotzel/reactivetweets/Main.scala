package janikdotzel.reactivetweets

import akka.actor.ActorSystem
import janikdotzel.reactivetweets.ReactiveTweets._

object Main extends App {
  implicit val system: ActorSystem = ActorSystem("reactive-tweets")

  // Print all authors who wrote akka tweets
  tweets.via(getAuthors).runWith(printer)

  // Print all tweets
  tweets.runWith(printer)

  tweets.via(getBody).runWith(printer)

}
