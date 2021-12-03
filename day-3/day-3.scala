import scala.io.Source

object DayThree {
  def main(args: Array[String]) = {
    // Input
    val nums = Source.fromFile(s"input.txt").getLines().toList

    // Part 1
    var gamma, epsilon = ""
    for (i <- 0 to nums(0).length - 1) {
      gamma += nums.groupBy(_(i)).maxBy(_._2.size)._1
      epsilon += nums.groupBy(_(i)).minBy(_._2.size)._1
    }
    var gammaRate = Integer.parseInt(gamma, 2)
    var epsilonRate = Integer.parseInt(epsilon, 2)
    println(s"Part 1: $gammaRate * $epsilonRate = ${gammaRate * epsilonRate}")

    // Part 2
    var oxFilter, co2Filter = nums 
    for (i <- 0 to nums(0).length - 1) {
      var most = if (oxFilter.count(_(i) == '1') * 2 >= oxFilter.size) '1' else '0'
      var least = if (co2Filter.count(_(i) == '1') * 2 >= co2Filter.size) '0' else '1'
      oxFilter = if (oxFilter.size > 1) oxFilter.filter(_(i) == most) else oxFilter
      co2Filter = if (co2Filter.size > 1) co2Filter.filter(_(i) == least) else co2Filter
    }
    var oxRate = Integer.parseInt(oxFilter(0), 2)
    var co2Rate = Integer.parseInt(co2Filter(0), 2)
    println(s"Part 2: $oxRate * $co2Rate = ${oxRate * co2Rate}")
  }
}
