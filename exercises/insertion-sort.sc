def iSort(list: List[Int]): List[Int] = {
  def insert(element: Int, list: List[Int]): List[Int] = {
    list match {
      case Nil => List(element)
      case head :: tl =>
        if (element < list.head) element :: list
        else list.head :: insert(element, list.tail)
    }
  }
  list match {
    case head :: tl => insert(head, iSort(tl))
    case Nil        => List()
  }
}

iSort(List(22, 33, 1, 2, 93))
