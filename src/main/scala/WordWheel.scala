import scala.util.Random

object WordWheel extends App {

  var rand = new Random()

  // def buildWheel(n: Int): Map[Char, Int] = {
  //   // val w: Map[Char, Int] = Map()
  //   // (randChar take 9).foreach(e => w.put(e, 1 + w.get(e, 0)))
  //   // w
  // }

  def randChar: Stream[Char] = {
    (for {c <- Stream.from(97 + rand.nextInt(26))} yield c.toChar)
  }

  def readwords(filename: String): Set[String] = {
    Control.using(io.Source.fromFile(filename)) { f =>
        (for (line <- f.getLines if line.length > 2) yield line) toSet
    }
  }
}