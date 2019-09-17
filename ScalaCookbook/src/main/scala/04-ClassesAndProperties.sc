"04 Classes and Properties"

class Person(var firstName: String, var lastName: String, val gender: String, income: Int) {
  println("person constructor begins...")

  private val HOME = System.getProperty("user.home")
  var age = 0

  override def toString: String = s"$firstName $lastName is $age years old"
  def printHome { println(s"Home = $HOME") }
  def printFullName { println(this) }
  def printGender { println(s"$gender")}
  def printIncomeCategory: Unit = {
    println(s"Income category: ${if(income > 10000) "High" else "Low"}")
  }

  printHome
  printFullName
  printGender
  printIncomeCategory


  println("person constructor ends...")
}

var person = new Person("Deepak", "Telkar", "male", 10000)

person.age = 20
println(person)

// Same as person.age = 21
person.age_$eq(21)
println(person)

/*
Can only be accessed within the class

person.HOME
person.income
*/

println(s"${person.gender}")


case class Human(firstName: String, lastName: String, age: Int)

object Human {
  def apply(firstName: String, lastName: String): Human = new Human(firstName, lastName, 0)
}

var human = Human("Deepak", "Telkar")

/*
val by default so, no mutator

human.firstName = "Megha"
 */

var megha = Human.apply("Megha", "Telkar")


// Auxiliary constructors

class Pizza(var crustSize: Int, var crustType: String) {

  def this(cSize: Int) {
    this(cSize, Pizza.DEFAULT_CRUST_TYPE)
  }

  def this(crustType: String) {
    this(Pizza.DEFAULT_CRUST_SIZE, crustType)
  }

  def this() {
    this(Pizza.DEFAULT_CRUST_SIZE, Pizza.DEFAULT_CRUST_TYPE)
  }

  override def toString: String = s"$crustSize inch $crustType crust pizza"
}

object Pizza {
  val DEFAULT_CRUST_SIZE = 12
  val DEFAULT_CRUST_TYPE = "THIN"
}

val p1 = new Pizza()
val p2 = new Pizza
val p3 = new Pizza(8)
val p4 = new Pizza("THICK")

// Private ctor (singleton)

class Brain private (name: String) {
  override def toString: String = s"$name brain!"
}

object Brain {
  val brain = new Brain("My")
  def getInstance = brain
}

val myBrain = Brain.getInstance

object FileUtils {

  def readFile(fileName: String): Unit = {
    println(s"Reading file $fileName")
  }

  def writeToFile(fileName: String, contents: String): Unit = {
    println(s"Writing file $fileName")
  }
}

FileUtils.readFile("abc.txt")

class Socket(val timeout: Int = 1000, val linger: Int = 2000) {
  override def toString: String = s"timeout: $timeout, linger: $linger"
}

val s1 = new Socket
val s2 = new Socket(3000)
val s3 = new Socket(3000, 4000)

case class Socket1(timeout: Int = 1000, linger: Int = 2000) {
  override def toString: String = s"timeout: $timeout, linger: $linger"
}

val s11 = Socket1()
val s12 = Socket1(2000)
val s13 = Socket1(2000, 3000)

// Overriding default accessors and mutators
class Private(private var privateName: String) {
  
  def name = privateName // accessor
  def name_=(aName: String) {privateName = aName} // mutator
}

val priv = new Private("private")
priv.name = "Private"
println(priv.name)

class Stock(private val price: Int) {

  // Object private
  private[this] var symbol: String = _
  def setSymbol(s: String) {symbol = s}

  // Can access private members of other instance
  def isHigher(that: Stock): Boolean = this.price > that.price
}

val st1 = new Stock(10)
st1.setSymbol("A")
val st2 = new Stock(20)
st2.setSymbol("B")
st1.isHigher(st2)

class Foo {
  val text = {
    val src = io.Source.fromFile("/etc/passwd")
    src.getLines().foreach(println)
    src.close()
  }
}

val f = new Foo

class LazyFoo {
  lazy val text = {
    val src = io.Source.fromFile("/etc/passwd")
    src.getLines().foreach(println)
    src.close()
  }
}

val lf = new LazyFoo


// Subclass
class Employee(firstName: String, lastName: String, gender: String, income: Int, var address: String)
extends Person(firstName, lastName, gender, income) {

  override def toString: String = s"${super.toString} lives at: $address"
}

val emp = new Employee("Deepak",
                       "Telkar",
                         "Male",
                         10000,
                         "Sydney")


// Using auxiliary base class ctor
class MyPizza(var toppings: String)
extends Pizza() {
  override def toString: String = s"${super.toString} with $toppings toppings"
}

val myPizza = new MyPizza("veggies, cheese")

// Abstract base class
abstract class Pet(name: String) {

  // Concrete variables
  val greeting: String = "Hello"
  // Getter & Setter created
  var concreteVarField = "Concrete var in abstract class"
  // Abstract variables
  var age: Int
  // Don't allow to override
  final val isFriendly: Boolean = true

  // Avoid nulls
  val optionalVal: Option[String]
  val optionalConcreteVal: Option[String] = Some("Optional Concrete Val")
  var optionalConcreteVar: Option[String] = Some("Optional Concrete var")

  override def toString: String = s"$name $greeting $age"

  // Abstract methods
  def sayHello: Unit
}

class Dog(name: String) extends Pet(name) {

  override val greeting = "Woof"
  // override keyword is not necessary for abstract variables
  var age: Int = _
  val optionalVal = Some("Optional Val")
  override val optionalConcreteVal = Some("Overriding")
  optionalConcreteVar = Some("New value")

  concreteVarField = "No need to re declare var field"


  def sayHello: Unit = {
    println(s"$greeting")
  }
}

val dog = new Dog("Candy")
dog.age = 10


// Case class unapply method is automatically created

human match {
  case Human(firstName, lastName, age) => println(s"$firstName $lastName $age")
}

human match {
  case h: Human => println(h)
}

// equals and hashcode is also generated
human == megha

// copy method can be used to clone an object

val hetansh = human.copy("Hetansh", "Telkar")

// Inner classes

class Box {
  case class Thing(name: String)

  private val things = new collection.mutable.ArrayBuffer[Thing]()

  def addThing(name: String) { things += new Thing(name)}
}

val box = new Box
box.addThing("a")
