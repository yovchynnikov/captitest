package captify.test.scala

import org.scalatest.FunSuite

import captify.test.scala.TestAssignment._

/**
  * TODO: change the comment )
  * Created by yur on 12.06.2016.
  */
class TestAssignmentTest extends FunSuite {

  test("sampleAfter test") {
    assertResult(Seq[BigInt](2, 3, 4).toIterator.toSeq)(sampleAfter(Seq[BigInt](1, 2, 3, 4, 5).toIterator, 1, 2).toSeq)
  }

  test("valueAt test") {
    assertResult(4)(valueAt(Seq[BigInt](1, 2, 3, 4, 5).toIterator, 3))
  }



}
