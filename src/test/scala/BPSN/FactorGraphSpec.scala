package BPSN

class FactorGraphSpec extends SpecBase {


  it should "construct a factor graph" in {

    val factorGraph = FactorGraph.construct((factorNodes, variableNodes, observableFactorNodes) => {
      val f1 = new FactorNode
      val v1 = new VariableNode
      val v2 = new VariableNode
      val o1 = new ObservableFactorNode(0, 0.3, v1)
      val o2 = new ObservableFactorNode(1, 0.7, v2)

      f1 link (v1, v2)
      v1 link (f1, o1)
      v2 link (f1, o2)

      factorNodes += f1
      variableNodes ++= Set(v1, v2)
      observableFactorNodes ++= Set(o1, o2)
      
    })

    factorGraph.infer

    factorGraph.getVariableNodes.foreach(v => {

      System.out.println(v.getBelief(0))
      System.out.println(v.getBelief(1))
      System.out.println("-------------------")

    })

  }

}
