package language

import poggle.LettersUtil.ItemFreq

trait Language {
  val name: String
  val welcomeMessage: String
  val distribution: Seq[ItemFreq[String]]
  val timeIsUp: String
}
