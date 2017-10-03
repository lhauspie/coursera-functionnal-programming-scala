package funsets

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 *  - run the "test" command in the SBT console
 *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {

  /**
   * Link to the scaladoc - very clear and detailed tutorial of FunSuite
   *
   * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
   *
   * Operators
   *  - test
   *  - ignore
   *  - pending
   */

  /**
   * Tests are written using the "test" operator and the "assert" method.
   */
  // test("string take") {
  //   val message = "hello, world"
  //   assert(message.take(5) == "hello")
  // }

  /**
   * For ScalaTest tests, there exists a special equality operator "===" that
   * can be used inside "assert". If the assertion fails, the two values will
   * be printed in the error message. Otherwise, when using "==", the test
   * error message will only say "assertion failed", without showing the values.
   *
   * Try it out! Change the values so that the assertion fails, and look at the
   * error message.
   */
  // test("adding ints") {
  //   assert(1 + 2 === 3)
  // }


  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }

  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   *
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   *
   *   val s1 = singletonSet(1)
   *
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   *
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   *
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
    val s4 = singletonSet(4)
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet(1) contains 1") {

    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3".
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
    }
  }

  test("union contains all elements of each set") {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }

  test("diff contains only 4") {
    new TestSets {
      val s123 = union(union(s1, s2), s3)
      val s124 = union(union(s1, s2), s4)
      val s = diff(s123, s124);
      assert(!contains(s, 1), "Diff 1")
      assert(!contains(s, 2), "Diff 2")
      assert(contains(s, 3), "Diff 3")
      assert(!contains(s, 4), "Diff 4")
    }
  }

  test("intersect contains only 1 and 2") {
    new TestSets {
      val s123 = union(union(s1, s2), s3)
      val s124 = union(union(s1, s2), s4)
      val s = intersect(s123, s124);
      assert(contains(s, 1), "Intersect 1")
      assert(contains(s, 2), "Intersect 2")
      assert(!contains(s, 3), "Intersect 3")
      assert(!contains(s, 4), "Intersect 4")
    }
  }

  test("forall: {1,3,4,5,7,1000}") {
    new TestSets {
      val forall = union(union(union(union(union(union(singletonSet(1), singletonSet(2)), singletonSet(3)), singletonSet(4)), singletonSet(5)), singletonSet(7)), singletonSet(1000))
      assert(!FunSets.forall(forall, ((x$1: Int) => x$1.<(5))), "all elements are not less than 5")
    }
  }

  test("forall: {2,4,10,50,1000}") {
    new TestSets {
      val forall = union(union(union(union(union(singletonSet(2), singletonSet(4)), singletonSet(10)), singletonSet(20)), singletonSet(50)), singletonSet(1000))
      assert(FunSets.forall(forall, ((x$2: Int) => x$2 % 2 == 0)), "all elements are even")
    }
  }


  test("exists: {2,4,10,50,1000}") {
    new TestSets {
      val exists = union(union(union(union(union(singletonSet(2), singletonSet(4)), singletonSet(10)), singletonSet(20)), singletonSet(50)), singletonSet(1000))
      assert(FunSets.exists(exists, ((x$3: Int) => x$3.==(2))), "2 should exist in the given set.")
    }
  }

  test("bis exists: {2,4,10,50,1000}") {
    new TestSets {
      val exists = union(union(union(union(union(singletonSet(2), singletonSet(4)), singletonSet(10)), singletonSet(20)), singletonSet(50)), singletonSet(1000))
      assert(!FunSets.exists(exists, ((x$4: Int) => x$4.==(5))), "5 shouldn't exist in the given set.")
    }
  }

  test("map: {2,4,10,20,50,1000}") {
    new TestSets {
      val map = union(union(union(union(union(singletonSet(2), singletonSet(4)), singletonSet(10)), singletonSet(20)), singletonSet(50)), singletonSet(1000))
      assertResult("{1,3,9,19,49,999}", "TEST")(FunSets.toString(FunSets.map(map, ((x$5: Int) => x$5 - 1))))
    }
  }

}
