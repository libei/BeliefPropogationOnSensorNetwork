package BPSN

import collection.mutable.{HashMap, ListBuffer}

class FactorNode {

  private val messages = new HashMap[Tuple2[VariableNode, Int], Double]
  private val neighbors = new ListBuffer[VariableNode]

  def getVariableNodes = neighbors.toList

  def link(variableNodes: VariableNode*) {
    variableNodes.foreach(v => {
      neighbors += v
      messages += (v,0) -> 1.0
      messages += (v,1) -> 1.0
    })
  }

  def apply(subject: Int,
            neighbor1: Int, neighbor2: Int,
            neighbor3: Int, neighbor4: Int,
            correlation1: Double, correlation2: Double,
            correlation3: Double,  correlation4: Double): Double = {

    var (term1, term2, term3, term4) = (0.0, 0.0, 0.0, 0.0)
    term1 = if(subject == neighbor1) correlation1 else 1 - correlation1
    term2 = if(subject == neighbor2) correlation2 else 1 - correlation2
    term3 = if(subject == neighbor3) correlation3 else 1 - correlation3
    term4 = if(subject == neighbor4) correlation4 else 1 - correlation4

    return term1 * term2 * term3 * term4

  }

  def update() {



  }

  def getMessageFor(variableNode: VariableNode, value: Int): Double = messages((variableNode, value))

}