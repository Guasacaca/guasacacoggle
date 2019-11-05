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

  def neighborExist(x: Int, dim: Int): Boolean = x >= 0 && x < dim*dim

  def edge(x: Int, dim: Int, addition: Int): Seq[Int] = Seq(x-dim, x-dim+addition, x+addition, x+dim, x+dim+addition)

  def findNeighbors(x:Int, dim: Int): Seq[Int] = {
    x % dim match {
      case 0 => edge(x, dim, 1) //left edge
      case v if v == dim - 1 => edge(x, dim, -1) //right edge
      case _ => neighbors(x, dim)
    }
  }.filter(idx => neighborExist(idx, dim))


  def neighbors(x: Int, dim: Int): Seq[Int] = Seq(
    x-1-dim, x-dim, x+1-dim, x-1, x+1, x-1+dim, x+dim, x+1+dim
  )

  /**
    * Try to find the letter among the validIdx.
    * @param board original board
    * @param letter letter to find
    * @param validIdx valid indices (i.e., neighbors of the last found char)
    * @return index if exists
    */
  def findLetterIdx(board: Seq[(String, Int)], letter: Char, validIdx: Seq[Int]): Option[Int] = {
    board.find { case(l, idx) =>
      l == letter.toString && validIdx.contains(idx)
    }.map(_._2)
  }

  def exists(possibleInits: List[Int], restWord: String, board: Seq[(String, Int)], dim: Int, alreadyUsed: List[Int] = List.empty): Boolean = {
    println(s"Possible inits: $possibleInits , still to check: $restWord, forbidden: $alreadyUsed")
    if(restWord.isEmpty) true
    else {
      possibleInits match {
        case initIdx::t =>
          findLetterIdx(board, restWord.head, findNeighbors(initIdx, dim).filterNot( idx => alreadyUsed.contains(idx))) match {
            case Some(i) => exists(List(i), restWord.tail, board, dim, alreadyUsed:+initIdx) || exists(t, restWord, board, dim, alreadyUsed)
            case None => exists(t, restWord, board, dim, alreadyUsed)
          }
        case Nil => false
      }
    }
  }

  def isItPossibleInBoard(word: String, board: Seq[String], dim: Int): Boolean = {
    println(s"current board: $board")
    val boardWithIdx = board.zipWithIndex
    println("The board with indices")
    boardWithIdx.foreach(println)
    println()
    val firstInitIndices = boardWithIdx.filter { case (letter, _) =>
        letter.head == word.head
    }.map(_._2).toList
    println(s"The first possible: $firstInitIndices for ${word.head}")

    exists(firstInitIndices, word.tail, boardWithIdx, dim)
  }

  def isItCorrect(word: String, board: Seq[String], dim: Int): Boolean = isItLongEnough(word) && isItPossibleInBoard(word.toUpperCase(), board, dim)

}
