package janikdotzel.reactivetweets

import akka.actor.ActorSystem
import akka.stream.scaladsl.Sink
import akka.testkit.TestProbe
import org.scalatest.flatspec.AnyFlatSpec
import StreamComponents.{Author,getAuthors}

import scala.concurrent.duration.DurationInt

class StreamComponentsUnitSpec extends AnyFlatSpec {

  "ReactiveTweets" should "print authors of akka tweets" in {
    implicit val system: ActorSystem = ActorSystem("reactive-tweets")

    import akka.pattern.pipe
    import system.dispatcher

    val probe = TestProbe()
    val sourceUnderTest = StreamComponents.tweets
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
