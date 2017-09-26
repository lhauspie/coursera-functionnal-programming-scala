package recfun

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class SumSuite extends FunSuite {
  import Main.sum
    test("sum: 1,2,3") {
      assert(sum(List(1,2,3)) === 6)
  }

    test("sum: 10,5,6") {
      assert(sum(List(10,5,6)) === 21)
  }

    test("sum: ") {
      assert(sum(List()) === 0)
  }

}
