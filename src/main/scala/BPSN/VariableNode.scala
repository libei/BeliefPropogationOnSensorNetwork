package BPSN

import collection.mutable.{ListBuffer, HashMap}

class VariableNode {

  private val messages = new HashMap[Tuple2[FactorNode, Int], Double]
  private val neighbors = new ListBuffer[FactorNode]

  def getFactorNodes = neighbors.toList

  def link(factorNode: FactorNode*) {
    factorNode.foreach(v => {
      neighbors += v
      messages += (v, 0) -> 1.0
      messages += (v, 1) -> 1.0
    })
  }

  def getMessageFor(factorNode: FactorNode, value: Int): Double = messages((factorNode, value))

  
  def getBelief(label: Int): Double = _getBelief(label) / (_getBelief(0) + _getBelief(1))


  private def _getBelief(label: Int): Double = {
    var belief = 1.0
    neighbors.foreach( n=> belief *= n.getMessageFor(this, label))
    belief
  }

  def update() {
    updateForLabel(0)
    updateForLabel(1)

    normalize
  }

  private def normalize() {

    val sumForLabel = new HashMap[Int, Double]
    sumForLabel ++= Set(0 -> 0.0, 1 -> 0.0)

    for((key, value) <- messages) {
      sumForLabel(key._2) += value
    }

    messages.keys.foreach(k => messages(k) = messages(k) / sumForLabel(k._2))
  }

  private def updateForLabel(value: Int) {
    
    var multiplication: Double = 1.0

    getFactorNodes.foreach(f => multiplication *= f getMessageFor(this, value))
    getFactorNodes.foreach(f => messages((f, value)) = multiplication / (f getMessageFor(this, value)))
  }
  
}