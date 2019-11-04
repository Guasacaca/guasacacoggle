import language.Spanish
import org.scalatest.{FlatSpec, Matchers}
import poggle.Preparation.Game
import poggle.Checkers

class CheckersSpec extends FlatSpec with Matchers {
  val dim = 5
  val game = Game(Spanish, dim)
  game.dibujar(0 until dim*dim)

  "Checkers" should "get neighbors of corner case" in {
    val pos = 0
    val n = Seq(1,5,6)

    val x=0
    val y = Seq(x-1-dim, x-dim, x+1-dim, x-1, x+1, x-1+dim, x+dim, x+1+dim)
    println(y)

    val checker = Checkers(Spanish)
    checker.findNeighbors(pos, dim) should be(n)
  }

  it should "get neighbors of corner case 2" in {
    val pos = 4
    val n = Seq(3,8,9)

    val x=pos
    val y = Seq(x-1-dim, x-dim, x+1-dim, x-1, x+1, x-1+dim, x+dim, x+1+dim)
    println(y)

    val checker = Checkers(Spanish)
    checker.findNeighbors(pos, dim).sorted should be(n)
  }

  it should "get neighbors of side case" in {
    val pos = 3
    val n = Seq(2,4,7,8,9)

    val checker = Checkers(Spanish)
    checker.findNeighbors(pos, dim).sorted should be(n)
  }

  it should "get neighbors of side case 2" in {
    val pos = 19
    val n = Seq(13,14,18,23,24)

    val checker = Checkers(Spanish)
    checker.findNeighbors(pos, dim).sorted should be(n)
  }

  it should "get neighbors of center case" in {
    val pos = 7
    val n = Seq(1,2,3,6,8,11,12,13)

    val checker = Checkers(Spanish)
    checker.findNeighbors(pos, dim).sorted should be(n)
  }

  it should "sum this for me" in {
    println(
    (0 until 100).map {
      case nr if nr%3==0  =>
        println(nr)
        nr
      case _ => 0
    }.sum)

  }

}
