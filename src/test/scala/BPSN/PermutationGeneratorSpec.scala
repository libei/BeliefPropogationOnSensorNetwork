package BPSN

class PermutationGeneratorSpec extends SpecBase {

  it should "generate permutation" in {

    val p = new PermutationGenerator
    val res = p.generate(2, Set(1, 2, 3, 4, 5))

    res.length should equal(25)

    res.foreach(r => {

      r.length should equal (2)

    })

  }

  it should "generate permutation with correlation" in {

    val p = new PermutationGenerator
    val res = p.generatePermutation(2, Set(1, 2, 3, 4, 5))

    res.length should equal(25)

    res.foreach(r => {

      r.length should equal (2)
      r(0)._2 should equal(0.8)
      r(1)._2 should equal(0.8)

    })

  }


}