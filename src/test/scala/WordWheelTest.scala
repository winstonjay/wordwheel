import org.scalatest.Assertions._

class WordWheelTest extends org.scalatest.FunSuite {
  
  WordWheel.rand = new scala.util.Random(1)

  test("Wordwheel.readwords regression") {
    assert(WordWheel.readwords("data/words").size == 235674)
  }

  test("WordWheel.randChar returns varying random streams each time") {
    assert((WordWheel.randChar take 8).toSet === Set('s', 'x', 'y', 't', 'u', 'v', 'r', 'w'))
    assert((WordWheel.randChar take 8).toSet === Set('e', 'f', 'a', 'b', 'g', 'c', 'h', 'd'))
    assert((WordWheel.randChar take 8).toSet === Set('n', 'j', 'm', 'i', 'l', 'h', 'k', 'o'))
  }

  test("WordWheel.buildWheel")
}
