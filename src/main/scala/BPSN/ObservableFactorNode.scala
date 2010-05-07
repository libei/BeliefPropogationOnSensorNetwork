package BPSN

import collection.mutable.{HashMap}

class ObservableFactorNode(observedValue: Int, correctRate: Double, variableNode: VariableNode, labels: Set[Int]) extends FactorMessageSource  {
  
  private val messages = new HashMap[Int, Double]

  def update() {
    labels.foreach(l => messages += l -> (if (observedValue == l) correctRate else 1 - correctRate))
  }

  def getMessageFor(variableNode: VariableNode, value: Int): Double = {
    if(variableNode != this.variableNode)
      return 0.0

    messages(value)
  }

}