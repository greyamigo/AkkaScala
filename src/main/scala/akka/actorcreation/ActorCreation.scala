package akka.actorcreation

import akka.actor.{ActorSystem, Props, Actor}
import akka.actorcreation.MobileDevice._
import akka.actorcreation.ChromecastDevice._



/**
 * Created by georgy on 14/03/17.
 */


object MobileDevice {
  sealed class CastingMessage
  case class Netflix(command: String) extends CastingMessage
  case class Stan (command: String) extends CastingMessage
}

object ChromecastDevice {
  sealed class ChromecastMessage
  case class Start (service : String) extends ChromecastMessage
  case class Stop (service : String) extends ChromecastMessage

}

class ChromecaseController extends Actor {
  def receive = {
    case Start(service) => println("STARTING "+service+" :D")
    case Stop(service)  => println("STOPPING "+service+" !! :(")
  }
}

class MobileDeviceController extends Actor {
  val controller = context.actorOf(Props[ChromecaseController], "ChromeCast-Controller")
  def receive = {
    case Netflix("start") => controller ! Start("NETFLIX")
    case Stan("start")    => controller ! Start("STAN")
    case Netflix("stop")  => controller ! Stop("NETFLIX")
    case Stan("stop")     => controller ! Stop("STAN")
  }
}


object ActorCreation  extends App {

  val system = ActorSystem("actorcreation")

  val phone = system.actorOf(Props[MobileDeviceController], "my-phone")

  phone ! Netflix("start")

}
