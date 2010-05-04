package BPSN

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FlatSpec
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith

@RunWith(classOf[JUnitRunner])
class SpecBase extends FlatSpec with ShouldMatchers {


  def areEqual(expected: Double, actual: Double) {
    if(expected - actual < 0.00001)
      return

    assert(false)
  }

}