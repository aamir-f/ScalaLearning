package old.actors.erotcaer.actor.patterns


/**
  * @author Stephen Samuel https://github.com/sksamuel/akka-patterns
  */

import akka.actor.{Terminated, ActorRef, Actor}
import scala.collection.mutable

/**
  * Holds up further messages until the previous messages have
  * been acknowledged.
  *
  * */
class FlowControlActor(target: ActorRef, windowSize: Int = 1) extends Actor {

  val queue = mutable.Queue.empty[Any]
  var pending = 0

  override def preStart(): Unit = context.watch(target)

  def receive = {
    case Acknowledged =>
      if (pending > 0) pending = pending - 1
      if (queue.nonEmpty) {
        target ! queue.dequeue()
        pending = pending + 1
      }
    case Terminated(targ) => context.stop(self)
    case msg =>
      if (pending == windowSize) {
        queue enqueue msg
      } else {
        pending = pending + 1
        target ! msg
      }
  }
}


case object Acknowledged