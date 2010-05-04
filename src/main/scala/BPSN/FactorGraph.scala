package BPSN

import collection.mutable.{HashSet}

class FactorGraph(factorNodes: HashSet[FactorNode], variableNode: HashSet[VariableNode]) {

  def infer() {

  }

}

object FactorGraph {

  def construct(construct: (HashSet[FactorNode], HashSet[VariableNode]) => Unit): FactorGraph = {

    val factorNodes = new HashSet[FactorNode]
    val variableNodes = new HashSet[VariableNode]

    construct(factorNodes, variableNodes)

    new FactorGraph(factorNodes, variableNodes)
  }

}