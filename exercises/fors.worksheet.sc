val a1 = List(1, 2, 3)
val a2 = List(5, 6, 7)
val a3 = List(8, 9, 10)

for (
  x <- a1;
  y <- a2;
  z <- a3
) yield (x, y, z)

(40 to 1 by -1).toList

val cacca: List[String] = List[Nothing]()

def mistery2(l: List[String]) = (0 :: (l map (_.length))) reduce (_ + _)
