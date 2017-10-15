package actors

import akka.actor.{Actor, ActorSystem, Props}

/**
  * Source :==> https://alvinalexander.com/scala/akka-actors-switch-between-states-with-become
  */
case object ActNormalMessage
case object TryToFindSolution
case object BadGuysMakeMeAngry

class EdNorton extends Actor {

  def angryState:Receive = {
    case ActNormalMessage => {
      println("Phew, I'm back to being Norton")
    }
  }
  def normalState:Receive ={
    case TryToFindSolution => {
      println("Looking for solution to my problem...")
    }
    case BadGuysMakeMeAngry => {
      println("I'm getting angry.....")
      context.become(angryState)
    }
  }
  override def receive = {
    case BadGuysMakeMeAngry => context.become(angryState)
    case  ActNormalMessage  => context.become(normalState)
  }
}
object Becomeunbec extends App {

  val system = ActorSystem("BecomeHulkExample")

  val davidBanner = system.actorOf(Props[EdNorton], name = "DavidBanner")

  davidBanner ! ActNormalMessage // init to normalState



}