package datastructures.binarytrees

sealed abstract class Tree[+T]
case class Node[+T](value:T,left:Tree[T],right:Tree[T]) extends Tree[T] {
  override def toString = s"Tree( ${value.toString}, ${left.toString}, ${right.toString}"
}
case object End extends Tree[Nothing] {
  override def toString: String = "."
}
object Node {
  def apply[T](value:T) = Node(value,End,End)
}

