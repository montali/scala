sealed trait Breed
case object Bulldog extends Breed
case object Labrador extends Breed

sealed trait Living
sealed trait Animal extends Living
case class Person(name: String, surname: String, age: Int, dog: Option[Dog])
    extends Living
case class Dog(name: String, breed: Breed) extends Animal

class Action(author: Living) {
  def author(): Living = author
}
class PetAction[T <: Animal](author: Living, who: T) extends Action(author) {
  def who(): T = who
  override def toString(): String = author.toString ++ " pet " ++ who.toString
}

val my_dog = Some(Dog("Fuffi", Labrador))
val me = Person("Simone", "Montali", 22, my_dog)
val you = Person("Mario", "Rossi", 38, None)
val no_dog = None

def hasADog(who: Living): Boolean = who match {
  case Dog(name, breed) => false
  case Person(name, surname, age, dog) =>
    dog match {
      case None        => false
      case Some(value) => true
    }
}

hasADog(me)
hasADog(you)

def pet[T <: Animal](who: Option[T], author: Person) = {
  who match {
    case None        => println("No dog was pet")
    case Some(value) => new PetAction[T](author, value).toString()
  }
}

pet(my_dog, me)
pet(no_dog, me)
