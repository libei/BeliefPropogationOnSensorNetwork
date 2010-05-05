package BPSN

abstract class FactorMessageSource {

  def getMessageFor(variableNode: VariableNode, label: Int): Double

}