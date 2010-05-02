package BPSN

import collection.mutable.ListBuffer

class Message

class VariableNode {

  private val factorNodes = new ListBuffer[FactorNode]
  


  def getFactorNodes = factorNodes.toList

  def link(factorNodes: FactorNode*) {
    this.factorNodes.appendAll(factorNodes)
  }

  def getMessageFor(j: VariableNode): Message = {
    null
  }

  def getBelief(): Float = {
    0.0f
  }

  def update() {

    factorNodes.foreach(f => {

//      f getMessageFor this

    })

  }
  
}