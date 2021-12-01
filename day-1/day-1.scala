import scala.io.Source

object DayOne {
  def main(args: Array[String]) = {
    // Input
    val measurements = Source.fromFile(s"input.txt").getLines().map(_.toInt).toList
    // Part 1
    println(measurements.sliding(2).count(x => x(1) > x(0)))
    // Part 2
    println(measurements.sliding(3).map(_.sum).sliding(2).count(x => x(1) > x(0)))
  }
}
