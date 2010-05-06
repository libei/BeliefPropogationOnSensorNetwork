package BPSN

import collection.mutable.{HashMap}

class ObservableFactorNode(observedValue: Int, correctRate: Double, variableNode: VariableNode) extends FactorMessageSource  {
  
  private val messages = new HashMap[Int, Double]

  def update() {
    val errorRate = (1 - correctRate)
    messages += 0 -> (if (observedValue == 0) correctRate else errorRate)
    messages += 1 -> (if (observedValue == 1) correctRate else errorRate)
    messages += 2 -> (if (observedValue == 2) correctRate else errorRate)
    messages += 3 -> (if (observedValue == 3) correctRate else errorRate)
    messages += 4 -> (if (observedValue == 4) correctRate else errorRate)
  }

  def getMessageFor(variableNode: VariableNode, value: Int): Double = {
    if(variableNode != this.variableNode)
      return 0.0

    messages(value)
  }

}