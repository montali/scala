def sum(xs: List[Int]) = {
  (xs) reduceLeft ((x, y) => x + y)
}

sum(List(1))

def sum_nofail(xs: List[Int]) = {
  (0 :: xs) reduceLeft ((x, y) => x + y)
}
sum_nofail(Nil)

def sum_with_fold(xs: List[Int]) = {
  (xs foldLeft 0)((x, y) => x + y)
}
sum_with_fold(Nil)
