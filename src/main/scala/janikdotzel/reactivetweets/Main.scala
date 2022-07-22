package janikdotzel.reactivetweets

import akka.actor.ActorSystem
import janikdotzel.reactivetweets.ReactiveTweets._

object Main extends App {
  implicit val system: ActorSystem = ActorSystem("reactive-tweets")

  // Print all tweet
    tweets.runWith(printer)

  // Print all authors who wrote akka tweets
  //  tweets.via(getAuthors).runWith(printer)

  // Prints all messages
  //  tweets.via(getBody).runWith(printer)

  // Print the tweet hourly tweet count of akka
  //  tweetCountAkka.via(readJson).runWith(printer)

  // Print the tweet hourly tweet count of scala
  //  tweetCountScala.via(readJson).runWith(printer)
}
