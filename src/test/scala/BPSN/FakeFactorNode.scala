package BPSN

import collection.mutable.HashMap

class FakeFactorNode extends FactorMessageSource {

  private val messages = new HashMap[Tuple2[VariableNode, Int], Double]

  def putMessageFor (variableNode: VariableNode, label: Int, value: Int) {
    messages((variableNode, label)) = value
  }

  def getMessageFor(variableNode: VariableNode, label: Int): Double = messages((variableNode, label))

}