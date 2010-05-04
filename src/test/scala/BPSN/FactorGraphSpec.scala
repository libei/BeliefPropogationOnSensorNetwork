package BPSN

import org.junit.runner.RunWith
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FlatSpec
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class FactorGraphSpec extends FlatSpec with ShouldMatchers {


  it should "construct a factor graph" in {

    val factorGraph = FactorGraph.construct((factorNodes, variableNodes) => {

//      factorNodes +=

    })

    factorGraph.infer

  }

}
