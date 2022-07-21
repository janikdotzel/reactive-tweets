package janikdotzel.reactivetweets

import akka.stream.scaladsl.Sink
import akka.testkit.TestProbe
import janikdotzel.reactivetweets.ReactiveTweets.{Author, authors, system, tweets}
import org.scalatest.flatspec.AnyFlatSpec

import scala.concurrent.duration.DurationInt

class ReactiveTweetsUnitSpec extends AnyFlatSpec {

  "ReactiveTweets" should "print authors of akka tweets" in {
    import akka.pattern.pipe
    import system.dispatcher

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


    sourceUnderTest.via(authors).runWith(Sink.seq).pipeTo(probe.ref)
    probe.expectMsg(3.seconds, expectedAuthors)
  }
}
