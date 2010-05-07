package BPSN

class FactorNodeSpec extends SpecBase {

  it should "connect to multiple variable nodes" in {

    val labels = Set(0, 1)
    val v1 = new VariableNode(labels)
    val v2 = new VariableNode(labels)
    val v3 = new VariableNode(labels)
    val f = new FactorNode(labels)
    f link(v1, v2, v3)

    f.getVariableNodes.length should equal(3)

  }

  it should "compute the probability of a node value given related value nodes and correlation" in {
    val labels = Set(0, 1)
    val f = new FactorNode(labels)
    var res = 0.0

    res = f(0, List(2, 2, 2, 2))
    areEqual(1 / (64 + 1), res)

    res = f(0, List(0, 0, 0, 3))
    areEqual(1 / (16 + 1), res)

  }

  it should "udpate the message for other variable nodes" in {
    val labels = Set(0, 1)

    val f = new FactorNode(labels)
    val v1 = new VariableNode(labels)
    val v2 = new VariableNode(labels)
    v1 link f
    v2 link f
    f link(v1, v2)

    f update

    f getMessageFor (v1, 0) should equal(0.5)
    f getMessageFor (v1, 1) should equal(0.5)
    f getMessageFor (v2, 0) should equal(0.5)
    f getMessageFor (v2, 1) should equal(0.5)

  }

}