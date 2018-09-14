package poggle

import language.{Language, Spanish}
import poggle.LettersUtil.{getLettersDistribution, distributedRandom, distributionAcc}

import scala.util.Random

object Preparation {

  type Dice = Seq[Die[_]]
  type Board = Seq[_]

  case class Die[T](facesSymbols: Seq[T], sides: Int) {
    def throwDie(): T = facesSymbols(Random.nextInt(sides))
  }

  case class Game(language: Language, dim: Int) {
    val diceSides = 6
    val boardSurface: Int = dim*dim

    val dice: Dice = {
      val (sumAccumTotal, distSumAccum) = distributionAcc(getLettersDistribution(language))
      (0 until boardSurface).foldLeft(Seq.empty[Die[String]]) { (acc, idx) =>
        val randomLetters: Seq[String] = (0 until diceSides).map(_ => distributedRandom[String](new Random, sumAccumTotal, distSumAccum))
        acc :+ Die(facesSymbols = randomLetters, sides = diceSides)
      }
    }

    def shakeTheBox: Board = dice.map(_.throwDie())

    def dibujar(dados: Board): String = {
      dados.zipWithIndex.foreach { case (valorDado, idx) =>
        if(idx % dim == 0) println()
        print(s"$valorDado  ")
      }
      """
        |
        |
        |----------
        |Press 'c' for throwing the dice again!
      """.stripMargin
    }
  }
}
