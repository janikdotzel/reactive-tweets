package janikdotzel.reactivetweets

import akka.actor.ActorSystem
import akka.pattern.pipe
import akka.stream.scaladsl.Sink
import akka.testkit.TestProbe
import janikdotzel.reactivetweets.Main.system.executionContext
import janikdotzel.reactivetweets.TweetModel.Author
import janikdotzel.reactivetweets.streamcomponents.PrintRealScalaTweets.{getAuthors, tweets}
import org.scalatest.flatspec.AnyFlatSpec

import scala.concurrent.duration.DurationInt

class PrintAkkaTweetsUnitSpec extends AnyFlatSpec {

  "ReactiveTweets" should "print authors of akka tweets" in {
    implicit val system: ActorSystem = ActorSystem("reactive-tweets")

    val probe = TestProbe()
    val sourceUnderTest = tweets
    val expectedAuthors = Seq(
      Author("rolandkuhn"),
      Author("patriknw"),
      Author("bantonsson"),
      Author("drewhk"),
      Author("ktosopl"),
      Author("mmartynas"),
      Author("akkateam"))

    sourceUnderTest.via(getAuthors).runWith(Sink.seq).pipeTo(probe.ref)
    probe.expectMsg(3.seconds, expectedAuthors)
  }
}
