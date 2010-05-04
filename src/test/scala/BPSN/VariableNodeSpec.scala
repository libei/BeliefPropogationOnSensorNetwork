package BPSN

class VariableNodeSpec extends SpecBase {

  it should "connect to multiple factor nodes" in {

    val f1 = new FactorNode
    val f2 = new FactorNode
    val f3 = new FactorNode
    val v = new VariableNode
    v link(f1, f2, f3)

    v.getFactorNodes.length should equal(3)

  }

  it should "update messages for factor nodes" in {

    val v = new VariableNode

    val f1 = new FactorNode
    f1 link v
    val f2 = new FactorNode
    f2 link v
    val f3 = new FactorNode
    f3 link v

    v link(f1, f2, f3)
    v update

    areEqual(v getMessageFor(f1, 0),0.333333333)
    areEqual(v getMessageFor(f2, 0),0.333333333)
    areEqual(v getMessageFor(f3, 0),0.333333333)

    areEqual(v getMessageFor(f1, 1),0.333333333)
    areEqual(v getMessageFor(f2, 1),0.333333333)
    areEqual(v getMessageFor(f3, 1),0.333333333)
  }

}