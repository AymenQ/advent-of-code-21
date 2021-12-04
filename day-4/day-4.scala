import scala.io.Source

object DayFour {

  type Board = List[List[Int]] 

  def winningScore(board : Board, currentDraw : List[Int]) : Int = {
    board.flatten.filter(!currentDraw.contains(_)).sum * currentDraw.last
  }

  def isWinning(board : Board, currentDraw : List[Int]) : Boolean = {
    var winningRow = board.exists(_.forall(currentDraw.contains(_)))
    var winningCol = board.transpose.exists(_.forall(currentDraw.contains(_)))
    return winningRow || winningCol
  }

  def main(args: Array[String]) = {
    // Input
    val file = Source.fromFile(s"input.txt").getLines().toList
    val draw = file(0).split(",").map(_.toInt).toList

    val boardStrings = file.drop(2).mkString("\n").split("\n\n").toList
    val boards = boardStrings.map {
      _.split("\n").map(_.split(" ").filter(_.nonEmpty).map(_.toInt).toList).toList
    }

    // Compute results
    val results = (0 until (draw.size-1)).map(i => {
     boards.map(isWinning(_, draw.take(i)))
    }).toList

    // Part 1
    val firstWinningState = results.find(_.contains(true)).get
    val firstWinningDraw = draw.take(results.indexOf(firstWinningState))
    val firstWinner = firstWinningState.indexWhere(_ == true)

    println(s"Part 1: ${winningScore(boards(firstWinner), firstWinningDraw)}")

    // Part 2
    val lastLosingState = results.findLast(_.contains(false)).get
    val lastWinningDraw = draw.take(results.lastIndexOf(lastLosingState) + 1)
    val lastWinner = lastLosingState.indexWhere(_ == false)

    println(s"Part 2: ${winningScore(boards(lastWinner), lastWinningDraw)}")
  }
}
