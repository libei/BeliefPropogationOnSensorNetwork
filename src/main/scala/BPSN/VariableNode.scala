package BPSN

import collection.mutable.{HashMap}

class VariableNode {

  private val factorNodeMessages = new HashMap[FactorNode, Double]

  def getFactorNodes = factorNodeMessages.keys.toList

  def link(factorNode: FactorNode*) {
    factorNode.foreach(v => {
      factorNodeMessages += v -> 1.0
    })
  }

  def getMessageFor(factorNode: FactorNode): Double = factorNodeMessages(factorNode)

  def getBelief(): Float = {
    0.0f
  }

  def update() {

    var multiplication: Double = 0.0

    getFactorNodes.foreach(f => multiplication *= f getMessageFor this );
    getFactorNodes.foreach(f => factorNodeMessages(f) = multiplication / (f getMessageFor this))
  }
  
}