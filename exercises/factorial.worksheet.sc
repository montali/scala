import scala.annotation.tailrec

def tailrec_factorial(n: Int): Int = {
  @scala.annotation.tailrec
  def factorial(n: Int, accumulator: Int): Int = {
    if (n == 1) accumulator else factorial(n - 1, n * accumulator)
  }
  factorial(n, 1)
}
tailrec_factorial(3)

@tailrec // Look at how REPL si incazza for this non-tail recursive thing
final def wrong_tailrec_factorial(n: Int): Int = {
  if (n == 1) n else n * wrong_tailrec_factorial(n - 1)
}

wrong_tailrec_factorial(3)
