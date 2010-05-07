package BPSN

import collection.mutable.{HashMap, ListBuffer}

class FactorNode(labels: Set[Int]) extends FactorMessageSource {

  private val messages = new HashMap[Tuple2[VariableNode, Int], Double]
  private val neighbors = new ListBuffer[VariableNode]

  def getVariableNodes = neighbors.toList

  def link(variableNodes: VariableNode*) {
    variableNodes.foreach(v => {
      neighbors += v
      labels.foreach(label => {
        messages += (v,label) -> 1.0
      })
    })
  }

  def apply(subject: Int, neighbors: List[Int]): Double = {
    var res = 1.0 // so that there will never be a DividbyZero exception when 1 / res

    neighbors.foreach(n => {
      res += Math.abs(subject - n)
    })
    res = Math.pow(res, 2)
//    System.out.println(MessageFormat.format("apply({0}, {1}) == {2}", subject.toString, neighbors.toString, (1 / res).toString))
    1 / res
  }

  def update() {
    neighbors.foreach(v => {

      labels.foreach(label => update(v, label))
    })
    normalize
  }

  private def normalize() {
    getVariableNodes.foreach(f => {
      var sum: Double = 0.0
      labels.foreach(label => sum += messages((f, label)))
      labels.foreach(l => messages((f, l)) = messages((f, l)) / sum )
    })
  }

  private def update(node: VariableNode, label: Int) {

    val permutation = new Permutation

    var res = 0.0

    val neightborsExceptTheOneToUpdate: List[VariableNode] = neighbors.toList.filter(n => n != node)

    val parms: List[List[Int]] = permutation.generate(neightborsExceptTheOneToUpdate.length, labels)

    parms.foreach(p => {

      //p: List[Int] 
      val functionPart = apply(label, p)

      var messagePart = 1.0

      for(val i <- 0 until neightborsExceptTheOneToUpdate.length) {
        val term: Double = neightborsExceptTheOneToUpdate(i).getMessageFor(this, p(i))
//        System.out.println(MessageFormat.format("message from variable {0} for label {1} is {2}", i.toString, p(i).toString, term.toString))
        messagePart *= term
      }
      res += functionPart * messagePart
    })

    messages((node, label)) = res
//    System.out.println(MessageFormat.format("message for node {0} label {1} is {2}", node.toString, label.toString, (res).toString))

  }

  def getMessageFor(variableNode: VariableNode, value: Int): Double = messages((variableNode, value))

}