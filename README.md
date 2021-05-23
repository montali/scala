# scala

Yo! Welcome here, I'll use this repo to note down some concepts and maybe a little bit of code to better understand this bad boi.

## Evaluation of expressions

Expressions are evaluated by taking the leftmost operator, evaluating its operands, then apply it. Note that methods with no parameters **can be called without the `()`**. The **infix notation** is what allow you to transform a `1.to(10)` to a `1 to 10`. It's what we do with arithmetic operands, for instance: `1 + 3` would actually be `1.+(3)` in a cruel world. Types in Scala start with an uppercase letter.
Remember that `def` are evaluated each time they're used, while `val`s are only evaluated when instantiated. Functions need an explicit return type when recursive.
For example, a function defined as follows:

```scala
def stringOfSum(x: Int, y: Int): String = (x + y).toString
```

would have arguments of type `Int` and return type `String`.
Scala uses **call-by-value**: this means that when you call a method, first its arguments are evaluated, then the right-hand side substitution is applied. The alternative is **call-by-name**, which evaluates the functions to unreduced arguments. Both strategies reduce to the same final values as long as the reduced expression consists of pure functions, and both of them terminate.

## Lexical scopes

Inner functions are a thing in Scala: for example, if we wanted to define a `sqrt` function, it would be good to only keep the involved functions in its scope:

```scala
def sqrt(x: Double) = {
  def sqrtIter(guess: Double, x: Double): Double =
    if (isGoodEnough(guess, x)) guess
    else sqrtIter(improve(guess, x), x)

  def improve(guess: Double, x: Double) =
    (guess + x / guess) / 2

  def isGoodEnough(guess: Double, x: Double) =
    abs(square(guess) - x) < 0.001

  sqrtIter(1.0, x)
}
```

Note the use of **blocks**: the braces `{}` delimit a block, an element containing a sequence of definitions or expressions, and lastly, the desired value for the block. The block is an example of reduced scope: what you create inside the block stays there, and if you define something having the same name as something outside of it, it will **shadow** it.
Note that semicolons work in Scala, but they're optional if you just go to a new line. If you need to have a multi-line expression, you can use parenthesis `()` or just end the line with an operator.
You can organize your shit in **objects**:

```scala
object Maths {
    def sqrt ...
    val x ...
}
```

and **packages**, referenced through `package/object.scala`:

```scala
package shit
object Maths { ... }
```

Note that definitions in a package are visible from other definitions of the same package. All the members of the classes `scala`, `java.lang`, `scala.Predef` are imported automatically.

## Tail recursion

Tail recursion is a concept crucial for performance of recursion. Basically, when a function is tail recursive, the stack frame is recycled across the several executions. This implies that the function can execute in constant stack space, so it's computationally similar to a loop. When is a function **tail recursive**, though? It is if and only if the last action of the function is the recursive call. For example,

```scala
def gcd(a: Int, b: Int): Int =
  if (b == 0) a else gcd(b, a % b)
```

is tail recursive, as `gcd(b, a % b)` is the last action. Contrarily,

```scala
def factorial(n: Int): Int =
  if (n == 0) 1 else n * factorial(n - 1)
```

is not, as the last action is `n * factorial(n - 1)`, involing a product and not the pure call.
One can require a function to be tail recursive by using the `@tailrec` annotation.
If we wanted to make `factorial` tail recursive, we could introduce an _accumulator_ as follows:

```scala
def factorial(n:Int) : Int = {
    @tailrec
    def factorial(n: Int, accumulator: Int) =
        if (n==0) accumulator else factorial(n-1, accumulator*n)
    factorial(n, 1)
}
```

Remember to import `scala.annotation.tailrec` if you want to use that annotation.

## Structuring informations

**Case classes** allow us to gather together coherent informations. For example, if we wanted to define a `Car` type, we could do so with a `case class`:

```scala
case class Car (
    make: String,
    model: String,
    horsepower: Int
)
val my_car = Car("Honda", "Civic", 129)
```

You can then access the fields with a dot: `my_car.make shouldBe "Honda"`.
If we wanted to make things more abstract, we could want to add a `Vehicle` type. This might represent, for example, a car or a motorbike. To do so, Scala features **sealed traits**, an abstract concept that can be embodied by something more concrete:

```scala
sealed trait Vehicle
case class Car (...) extends Vehicle
case class Motorbike (...) extends Vehicle
```

Now, if we wanted to have a method `goFast(vehicle: Vehicle)` we'd need to specify how to go fast on a motorbike and on a car. Which are different things. **Pattern matching** serves this purpose: it allows us to match the selector with the patterns, than substitutes the right-hand side of it.

```scala
def goFast(vehicle: Vehicle) = vehicle match {
    case Car(make, model, horsepower) => pedalToMetal(horsepower)
    case Motorbike(model, horsepower) => squidAway(horsepower)
}
```

When we're matching against a sealed trait, the compiler will check that the matching is exhaustive. Note that comparing case classes is equal to comparing the single values in them. Note that _case objects_ exist too, and we can use them, for example, as enums:

```scala
sealed trait CarMake
case object Honda extends CarMake
case object Mercedes extends CarMake
case object Audi extends CarMake
…
case object BMW extends CarMake
```

### Difference between traits and case classes

You can imagine this difference to be the one from the _have_ and the _is_ verbs. If something **is** something, it will be a sealed trait. If something **has** something, it will be a case class.

## Higher order functions

Scala is a functional language: a function can be passed as any other value!
Functions that take other functions as parameters or that return functions are called **higher order functions**. If we wanted to define a function that accumulates an array through a function, for example to compute factorials, we could do that as follows:

```scala
def sum(f: Int => Int, a: Int, b: Int): Int =
  if (a > b) 0
  else f(a) + sum(f, a + 1, b)
```

then use it with the above-defined `factorial()`:

```scala
def sumFactorials(a: Int, b: Int) = sum(factorial, a, b)
```

Note that the type of the argument `f` is `Int => Int`, meaning a function that takes `Int` as the only argument and outputs an `Int`. You can have multi-parameter functions with `(Int, Int) => Int`.
Scala provides **anonymous functions** too: sometimes you don't need to name a function only to use it one time.
If we wanted to use the mentioned `sum` function to sum cubes, we could just do:

```scala
def sumCubes(a: Int, b: Int) = sum(x => x*x, a, b)
```

## Standard library

**Lists** are a pretty huge thing for Scala programmers. Nobody knows why. Lists are **immutable**, i.e. the elements cannot be changed, recursive, and homogeneous. With this last term we mean that lists have to be of the same type, though scala's typing allows us to use `Any` as type. A list with elements of type `T` is defined as `List[T]`, for example:

```scala
val names: List[String] = List("Simmy", "Gesù", "Gianni")
```

All lists are construced from `Nil`, the empty list, and the construction operator `::`, followed by the tail.
Therefore, the `names` list is equal to `"Simmy" :: ("Gesù" :: ("Gianni" :: Nil))`.
Note that operators ending in `:` associate to the right, meaning that we may remove the parenthesis from the expression. They are seen as method calls of the right operand, so the above expression is actually equal to `Nil.::("Gianni").::("Gesù").::("Simmy")`.
We can use **pattern matching** to decompose lists! Look at the following example:

```scala
nums match {
  // Lists of `Int` that starts with `1` and then `2`
  case 1 :: 2 :: xs => …
  // Lists of length 1
  case x :: Nil => …
  // Same as `x :: Nil`
  case List(x) => …
  // The empty list, same as `Nil`
  case List() =>
  // A list that contains as only element another list that starts with `2`
  case List(2 :: xs) => …
}
```

We could use this in an insertion sort algorithm, as following:

```scala
def insertionSort(xs: List[Int]): List[Int] = xs match {
  case List() => List()
  case y :: ys => insert(y, insertionSort(ys))
}
```

### List operators

We can **transform** the elements of a list using `map`, which **applies a function to the elements**:

```scala
List(1, 2, 3).map(x => x + 1) == List(2, 3, 4)
```

We could filter elements using `filter`:

```scala
List(1, 2, 3).filter(x => x % 2 == 0) == List(2)
```

And finally, we could apply a joint map+flatten with `flatMap`:

```scala
val xs =
  List(1, 2, 3).flatMap { x =>
    List(x, 2 * x, 3 * x)
  }
xs == List(1, 2, 3, 2, 4, 6, 3, 6, 9)
```

Scala provides the `Option` type, which can either represent a `None` type or `Some`:

```scala
def sqrt(x: Double): Option[Double] =
  if (x < 0) None else Some(…)
```

which we can later use in pattern matching:

```scala
def foo(x: Double): String =
  sqrt(x) match {
    case None => "no result"
    case Some(y) => y.toString
  }
```

Something similar to `Option` is the `Try`, which either results in a `Success[A]` or in a `Failure`. Another useful type is `Either[A,B]`, which is pretty self-explainatory and can either be a `Left` or a `Right`. You can apply maps and flatMaps to `Either`s, but they will only work on the right case.

## Syntactic sugar

### String interpolation

The following things are not technically useful: they are just pretty. For example, **string interpolation** allows you to insert variables inside of strings, like Python's f-strings: `s"Dio $animal"` is able to insert a predefined animal into the curse phrase. You can even use complex expressions through braces: `s"Dio ${animal.toUpperCase}"`.

### Tuples

Scala offers **tuples** too, with the standard notation `(Int, String)`. You can then manipulate these using pattern matching (`case (i,s)`), variable instantiation (`val (i,s) = tuple`) or specific identifiers `_1`, `_2`, `_3` and so on.
What one wouldn't expect is that functions are actually treated as objects: they are just implementing a trait (namely, `Function1` for functions with 1 argument, `Function2` for two arguments and so on) and having a sole method `apply`.
So, in the end, calling a function as `f(a,b)` is actually equal to `f.apply(a,b)`.

### For expressions

**for** expressions are just the syntactically elegant way of using maps, flatMaps and filters. The `for`-version of `xs.map(x => x + 1)` is just

```scala
for (x <- xs) yield x + 1
```

while the `filter` gets translated to

```scala
for (x <- xs if x % 2 == 0) yield x
```

If we now wanted to create a filterMap, we would just need the unification of the two above:

```scala
for (x <- xs if x % 2 == 0) yield (x+1)
```

Finally, to translate the `flatMap` that creates tuples from two lists `xs.flatMap(x => ys.map(y => (x, y)))` we can just do the following:

```scala
for (x <- xs; y <- ys) yield (x, y)
```

### Parameters sugar

Functions' parameters can have names as in Python. For example, for a case class `Car(make: String, horsepower: Int)` we could instantiate a variable with `Car(make="Honda", horsepower=129)`.
Parameters can also have default values: if we, for example, wanted to live in a JDM world, we might rewrite our case class as `Car(make: String = "Honda", horsepower: Int)`.
Finally, functions can receive an arbitrary number of parameters using an asterisk: `def myMethod(x: Int, xs: Int*)`, in which `xs` is a list of the remaining parameters. To do the inverse in a call (i.e. supplying a list as separate parameters) you just add `: _*` in front of the list: `average(1, xs: _*)`.

# Acknowledgements

Most of these informations are based on the [scala-exercises tutorials](https://www.scala-exercises.org/).
