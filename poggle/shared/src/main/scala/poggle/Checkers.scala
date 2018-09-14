package poggle

import language.{English, Language, Spanish}
import poggle.Preparation.Board

import scala.io.Source

case class Checkers(language: Language) {

  def isInDictionary(word: String): Boolean = {
    val dictionary = language match {
      case x @ Spanish => ???
      case x @ English => Source.fromResource("dict-english.txt")
      case l => throw new IllegalArgumentException(s"language.Language named $l not supported")
    }
    dictionary.getLines().contains(word)
  }

  def isItLongEnough(word: String): Boolean = word.length >= 3

  def isItPossibleInBoard(word: String, board: Board, dim: Int): Boolean = {
    val vecinity: Map[Int, Seq[Int]] = ??? // from which positions which can I reach
    val initPositions = board.seq.zipWithIndex
      .filter(_._1.toString == word.head)
      .map(_._2)

    def nextPosition(word: String, board: String, currentPos: Seq[Int]): Boolean = {
      word match {
        case "" => true
        case w => true
      }
    }
    true
  }



  def isItCorrect(word: String, board: Board, dim: Int): Boolean = isItLongEnough(word) && isItPossibleInBoard(word, board, dim)

}
