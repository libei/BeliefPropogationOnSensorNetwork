package BPSN

class VariableNodeSpec extends SpecBase {

  it should "connect to multiple factor nodes" in {

    val labels = Set(0, 1)

    val f1 = new FactorNode(labels)
    val f2 = new FactorNode(labels)
    val f3 = new FactorNode(labels)
    val v = new VariableNode(labels)
    v link(f1, f2, f3)

    v.getFactorNodes.length should equal(3)

  }

  it should "update messages for factor nodes" in {

    val v = new VariableNode(Set(0, 1))

    val f1 = new FakeFactorNode
    val f2 = new FakeFactorNode
    val f3 = new FakeFactorNode

    f1.putMessageFor(v, 0, 1)
    f2.putMessageFor(v, 0, 1)
    f3.putMessageFor(v, 0, 1)

    f1.putMessageFor(v, 1, 3)
    f2.putMessageFor(v, 1, 3)
    f3.putMessageFor(v, 1, 3)

    v link(f1, f2, f3)
    v update

    v getMessageFor(f1, 0) should equal (0.1)
    areEqual(v getMessageFor(f1, 0),0.1)
    areEqual(v getMessageFor(f2, 0),0.1)
    areEqual(v getMessageFor(f3, 0),0.1)

    v getMessageFor(f1, 1) should equal (0.9)
    areEqual(v getMessageFor(f1, 1),0.9)
    areEqual(v getMessageFor(f2, 1),0.9)
    areEqual(v getMessageFor(f3, 1),0.9)
  }

  it should "get belief for every label" in {
    val labels = Set(0, 1)

    val v = new VariableNode(labels)

    val f1 = new FactorNode(labels)
    f1 link v
    val f2 = new FactorNode(labels)
    f2 link v

    v link(f1, f2)

    v getBelief(0) should equal(0.5)
    v getBelief(1) should equal(0.5)

  }
}