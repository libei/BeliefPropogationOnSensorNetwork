package BPSN

class PermutationSpec extends SpecBase {

  it should "generate permutation" in {

    val p = new Permutation
    val res = p.generate(2, Set(1, 2, 3, 4, 5))

    res.length should equal(25)

    res.foreach(r => {

      r.length should equal (2)

    })

  }


}