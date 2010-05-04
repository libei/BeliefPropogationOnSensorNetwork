package BPSN

import org.scalatest.matchers.ShouldMatchers
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FlatSpec


@RunWith(classOf[JUnitRunner])
class FactorNodeSpec extends FlatSpec with ShouldMatchers {

  it should "connect to multiple variable nodes" in {

    val v1 = new VariableNode
    val v2 = new VariableNode
    val v3 = new VariableNode
    val f = new FactorNode
    f link(v1, v2, v3)                          

    f.getVariableNodes.length should equal(3)
    
  }

  it should "compute the probability of a node value given related value nodes and correlation" in {

    val f = new FactorNode
    var res = 0.0
    
    res = f(0, List((1, 0.8), (1, 0.8), (1, 0.8), (1, 0.8)))
    areEqual(0.2 * 0.2 * 0.2 * 0.2, res)

    res = f(0, List((1, 0.2), (1, 0.8), (1, 0.2), (1, 0.8)))
    areEqual(0.2 * 0.8 * 0.2 * 0.8, res)

  }

  it should "udpate the message for other variable nodes" in {

    val f = new FactorNode
    val v1 = new VariableNode
    val v2 = new VariableNode
    v1 link f
    v2 link f
    f link(v1, v2)

    f update

    f getMessageFor (v1, 0) should equal(0.25)
    f getMessageFor (v1, 1) should equal(0.25)
    f getMessageFor (v2, 0) should equal(0.25)
    f getMessageFor (v2, 1) should equal(0.25)

  }

  def areEqual(expected: Double, actual: Double) {
    if(expected - actual < 0.00001)
      return

    assert(false)
  }


}