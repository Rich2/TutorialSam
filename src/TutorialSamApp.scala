package tsam

object TutorialSamApp extends App {

  def reverse[A](list: List[A]): List[A] = list.reverse

  /** The original solution. I had to add an extra pair of parenthesis because I'm not using braceless code. In my opinion this is not good clean
   * code */
  def scanv1[A, B >: A](xs: List[A])(z: B)(op: (B, B) => B): List[B] =
    reverse(xs.foldLeft(z, List(z)) {
      case ((acc, accList), curr) => {
        val res = op(acc, curr)
        (res, res :: accList)
      }
    }._2)

  val intList = List(1, 2, 3, 5)
  val test1 = scanv1(intList)(0)(_ + _)
  println(test1)

  /** So here I've tried to make the solution a bit more comprehensible, breaking it down into steps and trying to use more meaningful names. */
  def scanv2[A, B >: A](xs: List[A])(accStartValue: B)(op: (B, B) => B): List[B] = {

    val pairOfAccumulatorAndList: (B, List[B]) = xs.foldLeft(accStartValue, accStartValue :: Nil) { (innerPair, elemA) =>
      val (oldAccumulator, accList) = innerPair
      val newAccumulator = op(oldAccumulator, elemA)
      val newBackwardListOfAccumulatorSteps = newAccumulator :: accList
      (newAccumulator, newBackwardListOfAccumulatorSteps)
    }

    val finalBackwardListOfAccumululatorSteps: List[B] = pairOfAccumulatorAndList._2
    val finalForwardListOfAccumululatorSteps: List[B] = finalBackwardListOfAccumululatorSteps.reverse
    finalForwardListOfAccumululatorSteps
  }

  val test2 = scanv2(intList)(0)(_ + _)
  println(test2)

  /** So if I had to implement this myself I'd probably use mutation. There's nothing wrong with mutation as long as its encapsulated within the
   *  method and doesn't leak out. This should be more efficent and is in my view more comprhensible. */
  def scanv3[A, B >: A](xs: List[A])(init: B)(op: (B, B) => B): List[B] = {
    var acc = init
    var res = init :: Nil
    xs.foreach{a =>
      acc = op(acc, a)
      res ::= acc
    }
    res.reverse
  }

  val test3 = scanv3(intList)(0)(_ + _)
  println(test3)
  //output
  //List(0, 1, 3, 6, 11)
  //List(0, 1, 3, 6, 11)
  //List(0, 1, 3, 6, 11)
}
