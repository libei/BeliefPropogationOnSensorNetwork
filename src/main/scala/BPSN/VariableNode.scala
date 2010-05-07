package BPSN

import collection.mutable.{ListBuffer, HashMap}

class VariableNode(labels: Set[Int]) {

  var name = ""

  private val messages = new HashMap[Tuple2[FactorMessageSource, Int], Double]
  private val neighbors = new ListBuffer[FactorMessageSource]

  def getFactorNodes = neighbors.toList

  def link(factorNode: FactorMessageSource*) {
    factorNode.foreach(v => {
      neighbors += v

      labels.foreach(l => {
        messages += (v, l) -> 1.0
      })
    })
  }

  def getMessageFor(factorNode: FactorMessageSource, value: Int): Double = messages((factorNode, value))

  
  def getBelief(label: Int): Double = {
    var totalBelief = 0.0
    labels.foreach(l => totalBelief += _getBelief(l) )
    _getBelief(label) / totalBelief
  }


  private def _getBelief(label: Int): Double = {
    var belief = 1.0
    neighbors.foreach( n=> belief *= n.getMessageFor(this, label))
    belief
  }

  def update() {

    labels.foreach(l => {
      updateForLabel(l)
    })
    normalize
  }

  private def normalize() {
    getFactorNodes.foreach(f => {
      var sum = 0.0
      labels.foreach(l => sum += messages((f, l)))

      labels.foreach(l => messages((f, l)) = messages((f, l)) / sum)
    })
  }

  private def updateForLabel(label: Int) {
    
    var multiplication: Double = 1.0

    getFactorNodes.foreach(f => multiplication *= f getMessageFor(this, label))
    getFactorNodes.foreach(f => {
      messages((f, label)) = multiplication / (f getMessageFor(this, label))
//      System.out.println(MessageFormat.format("message for factor node {0} label {1} is {2}", f.toString, label.toString, messages((f, label)).toString))
    })


  }
  
}