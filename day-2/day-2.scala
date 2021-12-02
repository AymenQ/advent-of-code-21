import scala.io.Source

object DayTwo {
  def main(args: Array[String]) = {
    // Input
    val directions = Source.fromFile(s"input.txt").getLines().map {
      l =>
        val split = l.split(" ")
        (split(0), split(1).toInt)
    }.toList

    // Part 1
    val (depth, horizontal) = directions.foldLeft((0, 0)) {
      case ((d, h), v) => 
        v match {
          case ("down", n) => (d + n, h)
          case ("up", n) => (d - n, h)
          case ("forward", n) => (d, h + n)
        }
    }
    println(s"$horizontal * $depth = ${horizontal * depth}")

    // Part 2
    val (depth_2, horizontal_2, _) = directions.foldLeft((0, 0, 0)) {
      case ((d, h, a), v) => 
        v match {
          case ("down", n) => (d, h, a + n)
          case ("up", n) => (d, h, a - n)
          case ("forward", n) => (d + a * n, h + n, a)
        }
    }
    println(s"$horizontal_2 * $depth_2 = ${horizontal_2 * depth_2}")
  }
}
