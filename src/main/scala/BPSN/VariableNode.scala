package BPSN

import collection.mutable.{ListBuffer, HashMap}

class VariableNode {

  private val factorNodeMessages = new HashMap[Tuple2[FactorNode, Int], Double]
  private val neighbors = new ListBuffer[FactorNode]

  def getFactorNodes = neighbors.toList

  def link(factorNode: FactorNode*) {
    factorNode.foreach(v => {
      neighbors += v
      factorNodeMessages += (v, 0) -> 1.0
      factorNodeMessages += (v, 1) -> 1.0
    })
  }

  def getMessageFor(factorNode: FactorNode, value: Int): Double = factorNodeMessages((factorNode, value))

  def getBelief(): Float = {
    0.0f
  }

  def update() {
    updateForLabel(0)
    updateForLabel(1)
  }

  private def updateForLabel(value: Int) {
    
    var multiplication: Double = 1.0

    getFactorNodes.foreach(f => multiplication *= f getMessageFor(this, value))
    getFactorNodes.foreach(f => factorNodeMessages((f, value)) = multiplication / (f getMessageFor(this, value)))
  }
  
}