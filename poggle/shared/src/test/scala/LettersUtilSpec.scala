import poggle.LettersUtil.ItemFreq
import org.scalatest.{FlatSpec, Matchers}
import poggle.LettersUtil._

class LettersUtilSpec extends  FlatSpec with Matchers {

  "Letras util" should "create an accum sum of all items " in  {
    val dist = Seq(
      ItemFreq('A, 42),
      ItemFreq('G, 2),
      ItemFreq('H, 2)
    )

    distributionAcc(dist) should be(
      (46.0, Seq(
        ItemFreq('A, 42),
        ItemFreq('G, 44),
        ItemFreq('H, 46)
      )
    ))
  }

  it should "find index of the corresponding nr" in {
    val l = Seq(
      ItemFreq('A, 42.0),
      ItemFreq('B, 44.4),
      ItemFreq('C, 46.7)
    )

    val modelingIt = l.map(f => (f.frequency*100).toInt ).zipWithIndex

    l(findLimitIdx(50, modelingIt)).item should be ('A)

    l(findLimitIdx(4439, modelingIt)).item should be ('B)

    l(findLimitIdx(4440, modelingIt)).item should be ('B)

    l(findLimitIdx(4441, modelingIt)).item should be ('C)

  }
}
