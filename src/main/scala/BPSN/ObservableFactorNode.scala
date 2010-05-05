package BPSN

import collection.mutable.{HashMap}

class ObservableFactorNode(observedValue: Int, correctRate: Double, variableNode: VariableNode) extends FactorMessageSource  {
  
  private val messages = new HashMap[Int, Double]

  def update() {
    messages += 0 -> (if (observedValue == 0) correctRate else 1 - correctRate)
    messages += 1 -> (if (observedValue == 1) correctRate else 1 - correctRate)
  }

  def getMessageFor(variableNode: VariableNode, value: Int): Double = {
    if(variableNode != this.variableNode)
      return 0.0

    messages(value)
  }

}