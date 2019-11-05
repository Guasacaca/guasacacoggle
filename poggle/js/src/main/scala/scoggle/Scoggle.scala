package scoggle

import java.net.URL

import org.scalajs.dom
import dom.document
import javax.sound.sampled.{AudioInputStream, AudioSystem}
import language.Spanish
import org.scalajs.dom.raw.{Element, HTMLInputElement}
import poggle.{Checkers, Preparation}
import poggle.Preparation.{Board, Game}

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}
import scala.scalajs.js.timers.setTimeout
import scala.util.{Failure, Success, Try}

@JSExportTopLevel("Scoggle")
object Scoggle {

  def currentLanguage = document.getElementById("selectedLan").asInstanceOf[dom.html.Select].value
  def currentDimensions = document.getElementById("selectedDim").asInstanceOf[dom.html.Select].value

  var currentGame: Game = Game(Preparation.getLanguageByName(currentLanguage).right.get, currentDimensions.toInt)
  var currentBoard: Board = Seq.empty[String]
  var container: Element = appendPar(document.body)

  def appendPar(body: dom.Node): Element = {
    val container = document.createElement("container")
    body.appendChild(container)
    container
  }

  def needNewLine(idx: Int, dim: Int): Boolean = idx % dim == dim - 1

  def addBoxesAndLetters(container: dom.Node, letters: Seq[String], dim: Int): Seq[dom.Node] = {
    letters.zipWithIndex.map { case (letra, idx) =>
      val spanNode = createSpanNode(letra)
      container.appendChild(spanNode)
      if (needNewLine(idx, dim)) {
        val newLine = document.createElement("br")
        container.appendChild(newLine)
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
    println(s"Children: ")
    letters.zipWithIndex.map { case (letter, idx) =>
      val node = children.item(idx)
      if (node.nodeName == "SPAN") {
        val newSpanNode = createSpanNode(letter)
        container.replaceChild(newSpanNode, node)
      }
    }
  }

  def deleteChildren(container: Element): Unit = {
    println(s"Delete children")
    val containerCollection = document.getElementsByTagName("container")
    (0 until containerCollection.length).foreach { idx =>
      containerCollection(idx).parentNode.removeChild(containerCollection(idx))
    }
  }

  @JSExportTopLevel("resetGame")
  def resetGame(): Unit = {
    //erase the board
    deleteChildren(container)
    //paint again
    container = appendPar(document.body)
    startGame()
  }

  @JSExportTopLevel("checkWord")
  def checkWord(): Unit = {
    val checker = Checkers(currentGame.language)
    val newWord = document.getElementById("wordToCheck").asInstanceOf[HTMLInputElement].value
    val spanNode = document.createElement("span")
    val textNode = if (checker.isItCorrect(newWord, currentBoard.map(_.toString), currentGame.dim))
        document.createTextNode(newWord+" V")
      else  document.createTextNode(newWord+" I")
    spanNode.appendChild(textNode)
    spanNode.classList.add("words")
    container.appendChild(spanNode)
    val newLine = document.createElement("br")
    container.appendChild(newLine)

  }

  @JSExportTopLevel("shuffle")
  def shuffle(): Unit = {
    println(s"Board preshuffle: $currentBoard")
    currentBoard = currentGame.shakeTheBox
    println(s"Board postshuffle: $currentBoard")
    replaceLetters(container, currentBoard.map(_.toString))
  }

  @JSExportTopLevel("startTime")
  def startTime(): Unit = {
    setTimeout(1000) { // note the absence of () =>
      val time = document.createElement("p")
      time.classList.add("time")
      dom.window.alert(currentGame.language.timeIsUp)
    }
  }

  def playMusic(path: String): Unit = {
    val url = new URL(path)
    val audioIn: AudioInputStream = AudioSystem.getAudioInputStream(url)
    val clip = AudioSystem.getClip
    clip.open(audioIn)
    clip.start
  }

  def startGame(): (Game, Board) = {
    currentGame = Game(Preparation.getLanguageByName(currentLanguage).right.get, currentDimensions.toInt)
    currentBoard = currentGame.shakeTheBox

    currentGame.dice.foreach(println(_))

    addBoxesAndLetters(container, currentBoard.map(_.toString), currentGame.dim)

    (currentGame, currentBoard)
  }

  @JSExport
  def main(args: Array[String]): Unit = {
    startGame()
  }
}
