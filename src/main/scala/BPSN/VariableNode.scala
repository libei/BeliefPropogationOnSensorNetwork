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

    var multiplication_0: Double = 1.0
    var multiplication_1: Double = 1.0

    getFactorNodes.foreach(f => multiplication_0 *= f getMessageFor(this, 0))
    getFactorNodes.foreach(f => factorNodeMessages((f, 0)) = multiplication_0 / (f getMessageFor(this, 0)))

    getFactorNodes.foreach(f => multiplication_1 *= f getMessageFor(this, 1))
    getFactorNodes.foreach(f => factorNodeMessages((f, 1)) = multiplication_1 / (f getMessageFor(this, 1)))
  }
  
}