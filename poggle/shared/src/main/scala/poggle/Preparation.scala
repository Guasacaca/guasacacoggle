package poggle

import language.{English, Language, Spanish}
import poggle.LettersUtil.{distributedRandom, distributionAcc, getLettersDistribution}

import scala.util.Random

object Preparation {

  type Dice = Seq[Die[_]]
  type Board = Seq[_]

  case class Die[T](facesSymbols: Seq[T], sides: Int) {
    def throwDie(): T = facesSymbols(Random.nextInt(sides))
  }

  def getLanguageByName(name: String): Either[Exception, Language] =
    name.toLowerCase match {
      case "espanol" => Right(Spanish)
      case "english" => Right(English)
      case lan => Left(new IllegalArgumentException(s"Language $lan is not supported"))
    }


  case class Game(language: Language, dim: Int) {
    assert(dim>1)
    val diceSides = 6
    val boardSurface: Int = dim*dim

    val dice: Dice = {
      val (sumAccumTotal, distSumAccum) = distributionAcc(getLettersDistribution(language))
      (0 until boardSurface).foldLeft(Seq.empty[Die[String]]) { (acc, _) =>
        val randomLetters: Seq[String] =
          (0 until diceSides).map(_ => distributedRandom[String](new Random, sumAccumTotal, distSumAccum))
        acc :+ Die(facesSymbols = randomLetters, sides = diceSides)
      }
    }

    def shakeTheBox[String]: Board = {
      dice.map(_.throwDie())
    }

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
