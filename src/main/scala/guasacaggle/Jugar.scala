package guasacaggle

import language.Spanish
import poggle.Preparation.{Board, Game}

object Jugar {

  def continue(writeIn: String): Boolean = {
    writeIn match {
      case "c" => true
      case _ => false
    }
  }

  println(" - - - P O O G L E - - -\n")

  val play = Game(Spanish, 7)
  val dice = play.dice

  println(play.language.welcomeMessage)

  var input = "c"

  while(continue(input)) {
    val shake: Board = play.shakeTheBox
    println(play.dibujar(shake))
    input = scala.io.StdIn.readLine()
  }
}
