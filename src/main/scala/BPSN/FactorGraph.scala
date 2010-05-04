package BPSN

import collection.mutable.{HashSet}

class FactorGraph(factorNodes: HashSet[FactorNode], variableNode: HashSet[VariableNode], observableFactorNodes: HashSet[ObservableFactorNode]) {

  def infer() {
    observableFactorNodes.foreach(n => n.update)

    for(val i <- 1 until 100) {
      factorNodes.foreach(n => n.update)
      variableNode.foreach(n => n.update)
    }
  }
}

object FactorGraph {

  def construct(construct: (HashSet[FactorNode], HashSet[VariableNode], HashSet[ObservableFactorNode]) => Unit): FactorGraph = {

    val factorNodes = new HashSet[FactorNode]
    val variableNodes = new HashSet[VariableNode]
    val observableFactorNodes = new HashSet[ObservableFactorNode]

    construct(factorNodes, variableNodes, observableFactorNodes)

    new FactorGraph(factorNodes, variableNodes, observableFactorNodes)
  }

}