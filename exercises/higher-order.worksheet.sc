def doSomethingAndSmadonna[T, U](fn: T => U, arg: T): U = {
  print("mò lo faccio porco")
  fn(arg)
}

doSomethingAndSmadonna((parola: String) => { println(parola) }, "culetto")
