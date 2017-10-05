object exercises {
  def product(f: Int => Int)(a: Int, b: Int) : Int =
    if (a > b)
      1
    else
      f(a) * product(f)(a + 1, b)

  product(x => x*x)(1, 3)


  def fact(n: Int) = product(x=>x)(1,n)

  fact(3)
  fact(5)


  def compute(f: Int => Int, combine: (Int, Int) => Int, unit: => Int)(a: Int, b: Int) : Int =
    if (a > b)
      unit
    else
      combine(f(a), compute(f, combine, unit)(a + 1, b))

  compute(x => x, (a,b) => a + b, 0)(1, 3)
  compute(x => x, (a,b) => a + b, 0)(1, 5)

  compute(x => x*x, (a,b) => a * b, 1)(1, 3)
}