package scoggle

import org.scalajs.dom
import dom.document
import language.Spanish
import org.scalajs.dom.raw.{Element, NodeList, Text}
import poggle.Preparation.Game

import scala.scalajs.js.annotation.JSExportTopLevel
import scala.scalajs.js.timers

object Scoggle {

  val dim = 5
  val play = Game(Spanish, dim)
  val container: Element = appendPar(document.body)

  def appendPar(body: dom.Node): Element = {
    val container = document.createElement("container")
    body.appendChild(container)
    container

  }

  def addBoxesAndLetters(gridContainer: dom.Node, letras: Seq[String], dim: Int): Unit = {
    letras.zipWithIndex.map { case (letra,idx) =>
      val spanNode = document.createElement("span")
      val textNode = document.createTextNode(letra)

      spanNode.appendChild(textNode)
      spanNode.classList.add("letter")
      gridContainer.appendChild(spanNode)

      if(idx != 0 && idx % dim  == dim-1) {
        val enter = document.createElement("br")
        gridContainer.appendChild(enter)
      }
    }
  }

  def removeOldBoxesAndLetters(container: dom.Node): Unit = {
    (0 to container.childNodes.length).foreach { idx =>
      val node = container.childNodes.item(idx)
      if (node.hasChildNodes()) container.removeChild(node)
    }
  }

  @JSExportTopLevel("addClickedMessage")
  def addClickedMessage(): Unit = {
    val board = play.shakeTheBox
    removeOldBoxesAndLetters(container)
    addBoxesAndLetters(container, board.map(_.toString), dim)
  }

  @JSExportTopLevel("startTime")
  def startTime(): Unit = {
    ???
  }

  def main(args: Array[String]): Unit = {

    val board = play.shakeTheBox

    addBoxesAndLetters(container, board.map(_.toString), dim)

  }
}
