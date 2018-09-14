package scoggle

import org.scalajs.dom
import dom.document
import language.{English, Spanish}
import org.scalajs.dom.raw.{Element, NodeList, Text}
import poggle.Preparation.Game

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}
import scala.scalajs.js.timers.setTimeout

@JSExportTopLevel("Scoggle")
object Scoggle {

  val language = Spanish
  val dim = 5
  val play = Game(language, dim)
  val container: Element = appendPar(document.body)

  def appendPar(body: dom.Node): Element = {
    val container = document.createElement("container")
    body.appendChild(container)
    container

  }

  def addBoxesAndLetters(container: dom.Node, letters: Seq[String], dim: Int): Seq[dom.Node] = {
    letters.zipWithIndex.map { case (letra,idx) =>
      val spanNode = createSpanNode(letra)
      container.appendChild(spanNode)
      if(idx != 0 && idx % dim  == dim-1) {
        val enter = document.createElement("br")
        container.appendChild(enter)
      }
      spanNode
    }
  }

  def createSpanNode(letter: String): dom.Node = {
    val spanNode = document.createElement("span")
    val textNode = document.createTextNode(letter)
    spanNode.appendChild(textNode)
    spanNode.classList.add("letter")
    spanNode
  }

  def replaceLetters(container: dom.Node, letters: Seq[String]): Unit = {
    val children = container.childNodes
    letters.zipWithIndex.map { case (letter, idx) =>
      val node = children.item(idx)
      if(node.nodeName == "SPAN") {
        val newSpanNode = createSpanNode(letter)
        container.replaceChild(newSpanNode, node)
      }
    }
  }

  @JSExportTopLevel("addClickedMessage")
  def addClickedMessage(): Unit = {
    val board = play.shakeTheBox
    replaceLetters(container, board.map(_.toString))
    //addBoxesAndLetters(container, board.map(_.toString), dim)
  }

  @JSExportTopLevel("startTime")
  def startTime(): Unit = {
    setTimeout(1000) { // note the absence of () =>
      val time = document.createElement("p")
      time.classList.add("time")
      val src = document.createTextNode(language.timeIsUp)
      time.appendChild(src)
      container.appendChild(time)
    }
  }

  @JSExport
  def main(args: Array[String]): Unit = {

    val board = play.shakeTheBox

    val nodes = addBoxesAndLetters(container, board.map(_.toString), dim)

  }
}
