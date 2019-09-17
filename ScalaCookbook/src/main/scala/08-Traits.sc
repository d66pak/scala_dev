class BaseClass(var name: String) {

}

trait BaseTraitA {
  def name: String
  def greet(text: String)
}

trait BaseTraitB {
  def play
  def sound(tune: String)
}

class A(name: String) extends BaseClass(name) with BaseTraitA {

  def greet(text: String): Unit = {
    println(s"$text")
  }
}

class B extends BaseTraitA with BaseTraitB {

  def name: String = {
    return "B"
  }
  def greet(text: String): Unit = {
    println(s"$text")
  }

  override def play: Unit = {

  }
  override def sound(tune: String): Unit = {
    println(s"$tune")
  }
}

// Traits can have concrete/non concrete variables
trait PizzaTrait {
  val maxNumToppings = 10
  val maxSize = 12
  var numToppings: Int
  var size: Int
  val price: Float
}

class Pizza extends PizzaTrait {
  var numToppings: Int = 5
  var size: Int = 8
  val price: Float = 10.0f
  override val maxSize: Int = 18
}

// Traits can have concrete/non concrete methods
trait Pet {
  def speak { println("Ya")}
  def comeToMaster
}

class Dog extends Pet {
  override def comeToMaster {
    println("I'm coming!")
  }
}

class Cat extends Pet {
  override def speak: Unit = {
    println("meow")
  }

  override def comeToMaster: Unit = {
    println("That's not gonna happen")
  }
}

trait Tail {
  def wagTail { println("tail is wagging") }
  def stopTail { println("tail is stopped")}
}

// Mixin
class Horse extends Pet with Tail {
  override def comeToMaster {
    println("I'm coming!")
  }
}

// Adding trait to object instance

val dog = new Dog with Tail

trait Jump {
  def jump
}

// Not possible as jump is abstract
// val cat = new Cat with Jump