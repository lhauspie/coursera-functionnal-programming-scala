package recfun

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
    * Exercise 1
    */
  def pascal(c: Int, r: Int): Int =
    if (c == 0 || c == r)
      1
    else
      pascal(c - 1, r - 1) + pascal(c, r - 1)

  /**
    * Exercise 2
    */
  def balance(chars: List[Char]): Boolean = {
    def innerFunction(chars: List[Char], nbParentheses: Int): Int =
      if (chars.isEmpty || nbParentheses < 0)
        nbParentheses
      else if (chars.head == '(')
        innerFunction(chars.tail, nbParentheses + 1)
      else if (chars.head == ')')
        innerFunction(chars.tail, nbParentheses - 1)
      else
        innerFunction(chars.tail, nbParentheses)

    innerFunction(chars, 0) == 0
  }

  /**
    * Exercise 3
    */
  def countChange(money: Int, coins: List[Int]): Int = {
    if (money < 0 || coins.isEmpty)
      0
    else if (money == 0)
      1
    else
      countChange(money, coins.tail) + countChange(money - coins.head, coins)
  }


  def sum(xs: List[Int]): Int = {
    def loop(xs: List[Int], prevHead: Int): Int = {
      if (xs.isEmpty) prevHead
      else loop(xs.tail, xs.head + prevHead)
    }
    loop(xs, 0)
  }
}
