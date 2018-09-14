package language
import poggle.LettersUtil

object English extends Language {

  override val name: String = "english"
  override val welcomeMessage: String = "Welcome! You are playing Poogle in English."
  override val distribution: Seq[LettersUtil.ItemFreq[String]] = ???
}
