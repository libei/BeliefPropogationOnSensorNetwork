package BPSN

import org.junit.runner.RunWith
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FlatSpec
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class VariableNodeSpec extends FlatSpec with ShouldMatchers {

  it should "connect to multiple factor nodes" in {

    val f1 = new FactorNode
    val f2 = new FactorNode
    val f3 = new FactorNode
    val v = new VariableNode
    v link(f1, f2, f3)

    v.getFactorNodes.length should equal(3)

  }

  it should "update messages for factor nodes" in {

    val f1 = new FactorNode
    val f2 = new FactorNode
    val f3 = new FactorNode
    val v = new VariableNode
    v link(f1, f2, f3)
    v update

    v getMessageFor f1 should equal (1.0)
    v getMessageFor f2 should equal (1.0)
    v getMessageFor f3 should equal (1.0)

  }

}