import scala.util.Random
import collection.mutable.HashMap

object Wordwheel extends App {

  type Counter = Map[Char, Int]
  type WordSet = Set[String]

  class Wheel(val centre: Char, val letters: Counter)

  // initialise own random instance so we can seed during testing
  var rand = new Random()

  def findWords(wordset: Set[String], wheel: Wheel): WordSet =
    (for (w <- wordset if possibleWord(w, wheel)) yield w).toSet

  def possibleWord(word: String, wheel: Wheel): Boolean =
    word.contains(wheel.centre) && validLetterCounts(word, wheel.letters)

  def validLetterCounts(word: String, letters: Counter): Boolean =
    word.groupBy(identity).mapValues(_.size).forall({
      case (k, v) => { v <= letters.getOrElse(k, 0) }
    })

  def buildWheel(n: Int = 9): Wheel = {
    val letters = wheelLetters()
    val centre  = wheelCentre(letters)
    new Wheel(centre=centre, letters=letters)
  }

  def wheelCentre(letters: Counter): Char = {
    val keys = letters.keySet.toArray
    keys(rand.nextInt(keys.size))
  }

  def wheelLetters(n: Int = 9): Counter =
    (nextLowerAlpha.take(n)).groupBy(identity).mapValues(_.size)

  def nextLowerAlpha: Stream[Char] = {
    def nextAlpha: Char = {
      val chars = "abcdefghijklmnopqrstuvwxyz"
      chars.charAt(rand.nextInt(chars.length))
    }
    Stream.continually(nextAlpha)
  }

  def readwords(filename: String): WordSet = {
    Control.using(io.Source.fromFile(filename)) { f =>
        (for (line <- f.getLines if line.length > 2) yield line).toSet
    }
  }

  val wordSet: WordSet = readwords("data/words")
  val wheel: Wheel = buildWheel()
  println(wheel.centre, wheel.letters)
  println(findWords(wordSet, wheel))
}