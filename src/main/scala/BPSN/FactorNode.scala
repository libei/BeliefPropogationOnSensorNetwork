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

  def apply(subject: Int, neighborsWithFactors: List[Tuple2[Int, Double]]
          ): Double = {

    var res = 1.0

    neighborsWithFactors.foreach(n => {

      val factor =
        if(n._1 == subject) {
          n._2
        } else {
          1 - n._2
        }
      res *= factor
    })
    res
  }

  def update() {

    neighbors.foreach(v => {

      update(v, 0)
      update(v, 1)

    })

  }

  private def update(node: VariableNode, label: Int) {


    var res = 0.0

    val neightborsExceptTheOneToUpdate: List[VariableNode] = neighbors.toList.filter(n => n != node)

    val parms: List[List[Tuple2[Int, Double]]] = generatePermutation(neightborsExceptTheOneToUpdate)

    parms.foreach(p => {

      res += apply(label, p)

    })

    messages((node, label)) = res    

  }

  private def generatePermutation(nodes: List[VariableNode]): List[List[Tuple2[Int, Double]]] = {
    val permutation = new ListBuffer[List[Tuple2[Int, Double]]]

    nodes.foreach(n => {

      

    })
    
    return permutation.toList
  }


  def getMessageFor(variableNode: VariableNode, value: Int): Double = messages((variableNode, value))

}