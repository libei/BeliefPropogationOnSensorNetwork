package BPSN

class FactorGraphSpec extends SpecBase {


  it should "construct a factor graph" in {

    val factorGraph = FactorGraph.construct((factorNodes, variableNodes, observableFactorNodes) => {

      val labels = Set(0, 1,2,3,4)

      val f1 = new FactorNode(labels)
      val f2 = new FactorNode(labels)
      val f3 = new FactorNode(labels)

      val v1 = new VariableNode(labels)
      val v2 = new VariableNode(labels)
      val v3 = new VariableNode(labels)
      val v4 = new VariableNode(labels)
      val v5 = new VariableNode(labels)
      val v6 = new VariableNode(labels)
      val v7 = new VariableNode(labels)
      val v8 = new VariableNode(labels)

      val o1 = new ObservableFactorNode(2, 0.9, v1)
      val o2 = new ObservableFactorNode(2, 0.9, v2)
      val o3 = new ObservableFactorNode(2, 0.9, v3)
      val o4 = new ObservableFactorNode(2, 0.9, v4)
      val o5 = new ObservableFactorNode(2, 0.9, v5)
      val o6 = new ObservableFactorNode(2, 0.9, v6)
      val o7 = new ObservableFactorNode(4, 0.9, v7)
      val o8 = new ObservableFactorNode(2, 0.9, v8)

      v1.name = "v1"
      v2.name = "v2"
      v3.name = "v3"
      v4.name = "v4"
      v5.name = "v5"
      v6.name = "v6"
      v7.name = "v7"
      v8.name = "v8"

      f1 link (v1, v2, v3, v4)
      f2 link (v3, v4, v5, v6)
      f3 link (v5, v6, v7, v8)

      v1 link (f1, o1)
      v2 link (f1, o2)
      v3 link (f1, f2, o3)
      v4 link (f1, f2, o4)
      v5 link (f2, f3, o5)
      v6 link (f2, f3, o6)
      v7 link (f3, o7)
      v8 link (f3, o8)      

      factorNodes += f1
      factorNodes += f2
      factorNodes += f3

      variableNodes ++= Set(v1, v2, v3, v4, v5, v6, v7, v8)

      observableFactorNodes ++= Set(o1, o2, o3, o4, o5, o6, o7, o8)
      
    })

    factorGraph.infer

    factorGraph.getVariableNodes.foreach(v => {
      System.out.println(v.name + " ")
      System.out.println(v.getBelief(0))
      System.out.println(v.getBelief(1))
      System.out.println(v.getBelief(2))
      System.out.println(v.getBelief(3))
      System.out.println(v.getBelief(4))
      System.out.println("-------------------")

    })

  }

}
