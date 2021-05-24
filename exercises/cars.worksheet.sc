class Car(make: String, model: String, horsepower: Int) {
  def makeAndModel(): String = make ++ model
  def >(that: Car): Boolean = (this.horsepower > that.horsepower)
  def horsepower(): Int = horsepower
}

val x = new Car("Honda", "Civic", 129)
x.makeAndModel
val y = new Car("Mercedes-Benz", "CLA", 180)
x > y
// y < x Note that < is not defined!
