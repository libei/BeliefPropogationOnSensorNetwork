package BPSN

class FactorGraphSpec extends SpecBase {


  it should "construct a factor graph" in {

    val factorGraph = FactorGraph.construct((factorNodes, variableNodes, observableFactorNodes) => {
      val f1 = new FactorNode
      val v1 = new VariableNode
      val v2 = new VariableNode
      val v3 = new VariableNode
      val v4 = new VariableNode
      val o1 = new ObservableFactorNode(0, 0.7, v1)
      val o2 = new ObservableFactorNode(1, 0.7, v2)
      val o3 = new ObservableFactorNode(1, 0.7, v3)
      val o4 = new ObservableFactorNode(1, 0.7, v4)

      f1 link (v1, v2, v3, v4)
      v1 link (f1, o1)
      v2 link (f1, o2)
      v3 link (f1, o3)
      v4 link (f1, o4)

      factorNodes += f1
      variableNodes ++= Set(v1, v2, v3, v4)
      observableFactorNodes ++= Set(o1, o2, o3, o4)
      
    })

    factorGraph.infer

    factorGraph.getVariableNodes.foreach(v => {

      System.out.println(v.getBelief(0))
      System.out.println(v.getBelief(1))
      System.out.println("-------------------")

    })

  }

}
