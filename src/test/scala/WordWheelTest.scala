import org.scalatest.Assertions._

class WordwheelTest extends org.scalatest.FunSuite {

  Wordwheel.rand = new scala.util.Random(1)

  test("Wordwheel.readwords regression") {
    assert(Wordwheel.readwords("data/words").size == 235674)
  }

  test("Wordwheel.nextLowerAlpha returns varying random streams each time") {
    assert((Wordwheel.nextLowerAlpha.take(8)).toList ===
            List('r', 'a', 'h', 'j', 'm', 'y', 'u', 'w'))
    assert((Wordwheel.nextLowerAlpha.take(8)).toList ===
            List('w', 'k', 'r', 'x', 'n', 'f', 'm', 'q'))
    assert((Wordwheel.nextLowerAlpha.take(8)).size === 8)
  }

  test("Wordwheel.wheelLetters") {
    assert(Wordwheel.wheelLetters(8) ===
           Map('e' -> 1, 's' -> 2, 'm' -> 1,
               'p' -> 1, 'z' -> 2, 'd' -> 1))
    assert(Wordwheel.wheelLetters(9) ===
           Map('x' -> 2, 'j' -> 1, 'y' -> 1, 't' -> 1,
               'q' -> 1, 'g' -> 1, 'c' -> 1, 'd' -> 1))
  }

  test("Wordwheel.wheelCentre") {
      val letters = Wordwheel.wheelLetters(8)
      assert(Wordwheel.wheelCentre(letters) === 'k')
  }

  test("Wordwheel.buildWheel") {
    val wheel = Wordwheel.buildWheel()
    assert(wheel.centre === 'v')
    assert(wheel.letters.values.sum === 9)
  }

  test("Wordwheel.possibleWord") {
    // letters: essmpaad, centre: a
    val letters = Map('e' -> 1, 's' -> 2, 'm' -> 1,
                      'p' -> 1, 'a' -> 2, 'd' -> 1)
    val wheel = new Wordwheel.Wheel('a', letters)
    // first 2 should pass
    assert(Wordwheel.possibleWord("map", wheel) === true)
    assert(Wordwheel.possibleWord("passed", wheel) === true)
    // not all letters exists
    assert(Wordwheel.possibleWord("trains", wheel) === false)
    // center letter is not included
    assert(Wordwheel.possibleWord("mess", wheel) === false)
  }

  test("Wordwheel.findWords") {
    val wordset = Set[String]("map", "passed", "trains", "mess")
    val expected = Set[String]("map", "passed")
    // letters: essmpaad, centre: a
    val letters = Map('e' -> 1, 's' -> 2, 'm' -> 1,
                      'p' -> 1, 'a' -> 2, 'd' -> 1)
    val wheel = new Wordwheel.Wheel('a', letters)
    assert(Wordwheel.findWords(wordset, wheel) === expected)
  }
}
