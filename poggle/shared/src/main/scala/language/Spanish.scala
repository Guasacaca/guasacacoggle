package language

import poggle.LettersUtil.ItemFreq

object Spanish extends Language {

  override val name: String = "spanish"

  override val welcomeMessage: String = "¡Bienvenido! Estás jugando Poggle en español."

  override val distribution = Seq(
    ItemFreq("A",	12.53),
    ItemFreq("B",	1.42),
    ItemFreq("C",	4.68),
    ItemFreq("D",	5.86),
    ItemFreq("E",	13.68),
    ItemFreq("F",	0.69),
    ItemFreq("G",	1.01),
    ItemFreq("H",	0.70),
    ItemFreq("I",	6.25),
    ItemFreq("J",	0.44),
    ItemFreq("K",	0.02),
    ItemFreq("L",	4.97),
    ItemFreq("M",	3.15),
    ItemFreq("N",	6.71),
    ItemFreq("Ñ",	0.31),
    ItemFreq("O",	8.68),
    ItemFreq("P",	2.51),
    ItemFreq("Qu",	0.88),
    ItemFreq("R",	6.87),
    ItemFreq("S",	7.98),
    ItemFreq("T",	4.63),
    ItemFreq("U",	3.93),
    ItemFreq("V",	0.90),
    ItemFreq("W",	0.01),
    ItemFreq("X",	0.22),
    ItemFreq("Y",	0.90),
    ItemFreq("Z",	0.52)
  )
  override val timeIsUp: String = "¡¡SE ACABO TU TIEMPO!!"
  override val shuffleButton: String = "Bate la caja"
  override val startTime: String = "Comienza el tiempo"
}
