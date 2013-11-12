package sbtsimple

import org.scalatest._
import org.scalatest.matchers._
import org.scalatest.prop._

import org.scalacheck._

class TestSpec extends FlatSpec with Matchers with GeneratorDrivenPropertyChecks {
  behavior of "scalacheck usage"

  it should "looks like this" in {
    forAll {
      (a: Int, b: Int) =>
      (a + b) should be(b + a)
    }
  }

  behavior of "unit test"

  it should "work simply" in {
    assert(1 == 1)
  }
}

