package language
import poggle.LettersUtil
import poggle.LettersUtil.ItemFreq

object English extends Language {

  override val name: String = "english"
  override val welcomeMessage: String = "Welcome! You are playing Poogle in English."
  override val distribution: Seq[LettersUtil.ItemFreq[String]] = Seq(
    ItemFreq("A",8.167),
    ItemFreq("B",1.492),
    ItemFreq("C",2.782),
    ItemFreq("D",4.253),
    ItemFreq("E",12.702),
    ItemFreq("F",2.228),
    ItemFreq("G",2.015),
    ItemFreq("H",6.094),
    ItemFreq("I",6.966),
    ItemFreq("J",0.153),
    ItemFreq("K",0.772),
    ItemFreq("L",4.025),
    ItemFreq("M",2.406),
    ItemFreq("N",6.749),
    ItemFreq("O",7.507),
    ItemFreq("P",1.929),
    ItemFreq("Q",0.095),
    ItemFreq("R",5.987),
    ItemFreq("S",6.327),
    ItemFreq("T",9.056),
    ItemFreq("U",2.758),
    ItemFreq("V",0.978),
    ItemFreq("W",2.36),
    ItemFreq("X",0.15),
    ItemFreq("Y",1.974),
    ItemFreq("Z",0.074)
  )
}
