package BPSN

import collection.immutable.Set

class ObservableFactorNodeSpec extends SpecBase {

  it should "" in {
    val labels = Set(0, 1)
    val v = new VariableNode(labels)
    val node = new ObservableFactorNode(0, 0.7, v, labels)
    
    node.update

    val msg0 = node getMessageFor (v, 0)
    areEqual(msg0, 0.7)
    val msg1 = node getMessageFor (v, 1)
    areEqual(msg1, 0.3)
  }

}

