package scoggle

import org.scalajs.dom
import dom.{document, html}
import language.Spanish
import poggle.Preparation.Game

object Scoggle {

  //val board = <h1 class = "board"> POOGLE </h1>

  def appendPar(targetNode: dom.Node, letters: Seq[String], dim: Int): Unit = {
    val gridContainer = document.createElement("container")
    gridContainer.classList.add("grid")
    //gridContainer.style.gridTemplateColumns = s"repeat($dim, 1fr)"
    targetNode.appendChild(gridContainer)
    addParNodes(gridContainer, letters, dim)
  }

  def addParNodes(gridContainer: dom.Node, letras: Seq[String], dim: Int): Unit = {
    letras.map { letra =>
      val parNode = document.createElement("span")
      val textNode = document.createTextNode(letra)
      parNode.appendChild(textNode)
      parNode.classList.add("letter")
      gridContainer.appendChild(parNode)

    }
  }

  def main(args: Array[String]): Unit = {
    val dim = 5
    val play = Game(Spanish, dim)
    val dice = play.dice

    val board = play.shakeTheBox
    appendPar(document.body, board.map(_.toString), dim)
  }
}
