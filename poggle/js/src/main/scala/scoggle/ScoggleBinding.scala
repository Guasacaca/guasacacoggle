package scoggle

import com.thoughtworks.binding.Binding.{Var, Vars}
import com.thoughtworks.binding.{Binding, dom}
import org.scalajs.dom.html.{Div, Span}

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("ScoggleBinding")
object ScoggleBinding {

  val board: Vars[String] = Vars("a", "b")

  @dom
  def letterElement(letter: String): Binding[Span] = <span>{letter}</span>

  @dom
  def gameOverview: Binding[Div] = {
    ???
  }

  @JSExport
  def main: Unit = {
    dom.render(org.scalajs.dom.document.body, gameOverview)
  }
}
