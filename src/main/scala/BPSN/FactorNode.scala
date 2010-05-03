package BPSN

import collection.mutable.{HashMap, ListBuffer}

class FactorNode {

  private val messages = new HashMap[VariableNode, Double]

  def getVariableNodes = messages.keys.toList

  def link(variableNodes: VariableNode*) {

    variableNodes.foreach(v => {
      messages += v -> 1.0
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

  def getMessageFor(variableNode: VariableNode): Double = 0.0

}