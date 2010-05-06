package BPSN

import collection.mutable.{HashMap, ListBuffer}
import java.text.MessageFormat

class FactorNode extends FactorMessageSource {

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

  def apply(subject: Int, neighbors: List[Int]): Double = {



    var res = 1.0 // so that there will never be a DividbyZero exception when 1 / res

    neighbors.foreach(n => {
      res += Math.abs(subject - n)
    })

    res = Math.pow(res, 2)
    
    System.out.println(MessageFormat.format("apply({0}, {1}) == {2}", subject.toString, neighbors.toString, (1 / res).toString))
    1 / res
  }

  def update() {
    neighbors.foreach(v => {
      update(v, 0)
      update(v, 1)
    })
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

  private def update(node: VariableNode, label: Int) {

    val permutation = new Permutation

    var res = 0.0

    val neightborsExceptTheOneToUpdate: List[VariableNode] = neighbors.toList.filter(n => n != node)

    val parms: List[List[Int]] = permutation.generate(neightborsExceptTheOneToUpdate.length, Set(0, 1))

    parms.foreach(p => {

      //p: List[Int] 

      val functionPart = apply(label, p)

      var messagePart = 1.0

      for(val i <- 0 until neightborsExceptTheOneToUpdate.length) {
        val term: Double = neightborsExceptTheOneToUpdate(i).getMessageFor(this, p(i))
        messagePart *= term
      }
      res += functionPart * messagePart
    })

    messages((node, label)) = res    

  }

  def getMessageFor(variableNode: VariableNode, value: Int): Double = messages((variableNode, value))

}