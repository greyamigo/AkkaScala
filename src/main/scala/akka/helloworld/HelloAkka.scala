package akka.helloworld

import akka.actor.{Props, ActorSystem, Actor}

/**
 * Created by georgy on 10/03/17.
 */

case class WhoToYellAt(who:String)

class Yeller extends Actor {
  def receive = {
    case WhoToYellAt(who) => println("Heiii "+who++" Don't you dare do that, you sicko")
  }
}


object HelloAkka  extends App {

  val system = ActorSystem("Akka-Yeller")

  val yeller = system.actorOf(Props[Yeller], "my-yeller")

  yeller ! WhoToYellAt("Fred")

}
