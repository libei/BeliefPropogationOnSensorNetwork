package BPSN

import collection.mutable.{ListBuffer, HashMap}
import java.text.MessageFormat

class VariableNode {

  private val messages = new HashMap[Tuple2[FactorMessageSource, Int], Double]
  private val neighbors = new ListBuffer[FactorMessageSource]

  def getFactorNodes = neighbors.toList

  def link(factorNode: FactorMessageSource*) {
    factorNode.foreach(v => {
      neighbors += v
      messages += (v, 0) -> 1.0
      messages += (v, 1) -> 1.0
    })
  }

  def getMessageFor(factorNode: FactorMessageSource, value: Int): Double = messages((factorNode, value))

  
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
    getFactorNodes.foreach(f => {
      val sum = messages((f, 0)) + messages((f, 1))
      messages((f, 0)) = messages((f, 0)) / sum
      messages((f, 1)) = messages((f, 1)) / sum
    })
  }

  private def updateForLabel(label: Int) {
    
    var multiplication: Double = 1.0

    getFactorNodes.foreach(f => multiplication *= f getMessageFor(this, label))
    getFactorNodes.foreach(f => {
      messages((f, label)) = multiplication / (f getMessageFor(this, label))
      System.out.println(MessageFormat.format("message for factor node {0} label {1} is {2}", f.toString, label.toString, messages((f, label)).toString))
    })


  }
  
}