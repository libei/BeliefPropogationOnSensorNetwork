package BPSN

class ObservableFactorNodeSpec extends SpecBase {

  it should "" in {
    val v = new VariableNode
    val node = new ObservableFactorNode(0, 0.7, v)
    
    node.update

    val msg0 = node getMessageFor (v, 0)
    areEqual(msg0, 0.7)
    val msg1 = node getMessageFor (v, 1)
    areEqual(msg1, 0.3)
  }

}

