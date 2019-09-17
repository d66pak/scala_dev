class Foo {

  // public by default
  def publicFoo = true
  // protected scope (available to subclass too but not to any class even in same pkg)
  protected def protectedFoo = true
  // private scope (not available to subclass)
  private def privateFoo = true
  // Object-private scope
  private[this] def isFoo = true

  // package private scope
  // private[pkgname] def packagePrivate = true
}

/**
private[this] The method is available only to the current instance of the class it’s declared in.
private       The method is available to the current instance and other instances of the class it’s declared in.
protected     The method is available only to instances of the current class and subclasses of the current class.
private[model] The method is available to all classes beneath the com.acme.coolapp.model package.
private[coolapp] The method is available to all classes beneath the com.acme.coolapp package.
private[acme]    The method is available to all classes beneath the com.acme package.
(no modifier)    The method is public.
**/

class Bar extends Foo {
  // Call super method using super.
  override protected def protectedFoo: Boolean = super.protectedFoo

  def defaultParams(noDefault: Boolean, intArg: Int = 10, strArg: String = "str"): Unit = {
    println(s"$noDefault $intArg $strArg")
  }
}

trait A {
  def name = "A"
}

trait B extends A {
  override def name: String = "B"
}

trait C extends A {
  override def name: String = "C"
}

class X extends A with B with C {
  def printSuper = super.name
  def printA = super[A].name
  def printB = super[B].name
  def printC = super[C].name

  println(printSuper)
  println(printA)
  println(printB)
  println(printC)
}

val x = new X

val b = new Bar
b.defaultParams(true, 20, "abc")
b.defaultParams(false)
b.defaultParams(true, intArg = 40)
b.defaultParams(false, strArg = "xyz")

class Baz extends Bar {
  override def defaultParams(noDefault: Boolean, intArg: Int = 100, strArg: String = "strstrstr"): Unit = {
    println(s"$noDefault $intArg $strArg")
  }
}

val baz = new Baz
baz.defaultParams(true)

class Bazz extends Bar {
  override def defaultParams(noDefault: Boolean = true, intArg: Int = 100, strArg: String = "strstrstr"): Unit = {
    println(s"$noDefault $intArg $strArg")
  }

  // ** Returns tuple
  // ** Forces callers to drop () while calling
  def returnTuple = (1, "One")
}

val bazz = new Bazz
bazz.defaultParams()

val (n, number) = bazz.returnTuple

// ** () not required while calling
//bazz.returnTuple()

// Variable arguments

def printAll(ints: Int*): Unit = {
  ints.foreach(println)
}

printAll(1, 2, 3, 4)
val v1 = Vector(1, 2, 3, 4)
printAll(v1: _*)

// Declare that method can store exception
// ** Scala doesn't require that the exception are checked.
// ** But it's better to declare them for documentation.
@throws(classOf[java.io.IOException])
def printStrings(strs: String*): Unit = {
  for (s <- strs) println(s)
}
printStrings(('a' to 'z').map(_.toString): _*)

// Fluent style (method chaining)
class Person {
  protected var firstName = ""
  protected var lastName = ""

  def setFirstName(fName: String): this.type = {
    firstName = fName
    this
  }

  def setLastName(lName: String): this.type = {
    lastName = lName
    this
  }

  def getFirstName = firstName
  def getLastName = lastName

  override def toString: String = s"$firstName $lastName"
}

val p = new Person

p.setFirstName("Deepak").setLastName("Telkar")
println(p)