object nth {

  trait MyList[T] {
    def isEmpty: Boolean
    def head: T
    def tail: MyList[T]
    def nth(i: Int): T
  }

  class Nil[T] extends MyList[T] {
    def isEmpty = true
    def head = throw new NoSuchElementException("Nil.head")
    def tail = throw new NoSuchElementException("Nil.tail")
    def nth(i: Int) = throw new IndexOutOfBoundsException()
  }

  class Cons[T](val head: T, val tail: MyList[T]) extends MyList[T] {
    def isEmpty = false

    def nth(i: Int): T =
      if (i == 0) head
      else tail.nth(i-1)
  }


  var cons = new Cons(1, new Cons(2, new Nil))
  cons.nth(0)
  cons.nth(1)
  cons.nth(-1)
}