package BPSN

import collection.mutable.ListBuffer

class PermutationGenerator {

  private var perm: ListBuffer[List[Int]] = null

  def generate(length: Int, labels: Set[Int]): List[List[Int]] = {
    perm = new ListBuffer[List[Int]]
    generate(length, labels, new ListBuffer[Int])
    perm.toList
  }

  def generatePermutation(length: Int, labels: Set[Int]): List[List[Tuple2[Int, Double]]] = {
    val permutation = new ListBuffer[List[Tuple2[Int, Double]]]

    val listofList: List[List[Int]] = generate(length, labels)
    listofList.foreach(list => {
      permutation.append(list.map(e => (e, 0.8)))
    })

    return permutation.toList
  }


  private def generate(length: Int, labels: Set[Int], currentPath: ListBuffer[Int]) {

    if(length == 0) {
      perm.append(currentPath.toList)
      return
    }

    labels.foreach(l => {
      currentPath.append(l)
      generate(length - 1, labels, currentPath)
      currentPath.remove(currentPath.length - 1)
    })
  }
}