val names = List("Simmy", "Marco", "Davide")

def gimmeTheSecondElement(list: List[Any]): String = {
  list match {
    case head :: tl =>
      tl match {
        case head :: tl => "This is the second element: " ++ head.toString
        case Nil        => "The list was single-element"
      }
    case Nil => "Empty list"
  }
}

gimmeTheSecondElement(names)
gimmeTheSecondElement(List("Simone"))
gimmeTheSecondElement(List())
gimmeTheSecondElement("Simone" :: List("Martina"))
gimmeTheSecondElement("Simone" :: "Mariangelo" :: "Claudio" :: Nil)

names.filter(x => x.toLowerCase.contains("s")) // Only print names with S in it

for (
  name <- names
  if name.toLowerCase.contains("s")
) yield ("NameWithS")

def listsPermutation(a: List[Any], b: List[Any]) = {
  for (
    elem_a <- a;
    elem_b <- b
  ) yield (elem_a, elem_b)
}
val surnames = List("Montali", "Di Perna", "Guarnieri")
listsPermutation(names, surnames)
