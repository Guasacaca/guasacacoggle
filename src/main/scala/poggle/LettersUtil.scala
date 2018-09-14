package poggle

import language.{Language, Spanish}

import scala.util.Random

object LettersUtil {

  case class ItemFreq[T](item: T, frequency: Double) {
    val swap: (Double, T) = (frequency, item)
  }

  def distributionAcc[T](distribution: Seq[ItemFreq[T]]): (Double, Seq[ItemFreq[T]]) =
    distribution.foldLeft((0.0, Seq.empty[ItemFreq[T]])) {
      case ((_, l), item) if l==Seq.empty => (item.frequency, Seq(item))
      case (acc, item) =>
        val currentAccum = item.frequency+acc._1
        (currentAccum, acc._2:+ ItemFreq(item.item, currentAccum))
    }

  def getLettersDistribution(language: Language): Seq[ItemFreq[String]] = language match {
    case x @ Spanish => x.distribution
    case l => throw new IllegalArgumentException(s"language.Language named $l not supported")
  }

  def distributedRandom[T](r: Random, total: Double, listDistributions: Seq[ItemFreq[T]]): T = {
    val randomNr = r.nextInt((total*100).toInt)
    listDistributions(findLimit(randomNr, listDistributions.map( f => (f.frequency*100).toInt ).zipWithIndex ) ).item
  }

  //el segundo es el idx
  def findLimit(nr: Double, sortedList: Seq[(Int, Int)]): Int = {
    sortedList match {
      case Nil => 0
      case h :: Nil => h._2
      case h1 :: h2 :: Nil => if(nr <= h1._1 ) h1._2 else h2._2
      case l =>
        val halflistIdx = l.length/2
        val (halfValue, idx) = l(halflistIdx)
        if(nr == halfValue) idx
        else if (nr < halfValue) findLimit(nr, l.splitAt(halflistIdx)._1)
        else findLimit(nr, l.splitAt(halflistIdx)._2)
    }
  }
}
