/*val a1 = List(1, 2, 3)
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
def mistery(l: List[Int], f: Int => Boolean) =
  ((l filter f) foldLeft 0)(_ + _)

mistery(List(5, 2, 7, 9, 21, 15), ((x) => (5 < x) && (x < 16)))

println(z.get)
val S = Set(1, 3, 4, 7)
val S2 = S ++ (S map (_ + 1))
(List(3, 9, 12) foldLeft 4)(_ - _)
def mystery(f: Int => Int, x: Int): Int = f(x + 1)

List(5, 1, 3) map (x => x + mystery(_ + 1, x + 1))

def max(x1: Int, x2: Int) = if (x1 > x2) x1 else x2
def mystery3(l: List[Int], max: Int): Int = {
  if (l == Nil) max
  else if (l.head > max) mystery3(l.tail, l.head)
  else mystery3(l.tail, max)
}

mystery3(List(1, 2, 3, 4), 0)

abstract class Animal
class Frog(name: String) extends Animal
def test(a: Animal) = {
  a.toString
}

test(new Frog("Simmi"))

class A(val x: Int) { def ml = x + 1 }
class B(k: Int, val z: Int) extends A(k) {
  override def ml = z - 1
  def m2 = ml
}
val z: A = new B(5, 3)*/

for {
  i <- Set(2, 3)
  j <- 1 to i
  k <- i to 2 by -1
} yield (j, k)

/*2,1,2
2,2,2
3,1,3
3,1,2
3,2,3
3,2,2
3,3,3
3,3,2*/
