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

(1 to 10) flatMap (x => List(x + 4, x, x + 11))

for (
  x <- (1 to 10);
  y <- (1 to x) toList
) yield (x, y)

var X = 0
var Y = 0

def mystery1(a: Int): Int = {
  X += 1
  if (a == 52) 1
  else mystery1(a + 1)
}

def mystery2(a: Int, b: Int, c: Int): Int = {
  Y += 1
  if (b == 52) c
  else mystery2(a + 1, b - 1, c + 1)
}
mystery2(mystery1(49), 56, 69)
print(X, Y)

List(4, 5, 6, 1) sortWith (_ < _)

((1 to 5) foldRight (5))(_ - _)

for {
  i <- Map[Int, Int]((2 -> 2), (3, 2))
  j <- 1 to i._1
  k <- i._1 to 2 by -1
} yield (j, k)
