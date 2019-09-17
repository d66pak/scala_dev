// Anonymous Functions

val l1 = List.range(1, 10)

l1.filter(_ % 2 == 0)
l1.filter((i: Int) => i % 2 == 0)
l1.filter(i => i % 2 == 0)

// Functions as variables

val double1 = (i: Int) => {i * 2}

l1.map(double1)

val m1 = (i: Int) => {i % 2 == 0}

l1.filter(m1)

// Specify return type

val m2: (Int) => Boolean = i => {i % 2 == 0}
val m3: Int => Boolean = i => {i % 2 == 0}
val m4: Int => Boolean = i => i % 2 == 0
val m5: Int => Boolean = _ % 2 == 0

// Implicit approach
val add1 = (x: Int, y: Int) => { x + y }
val add2 = (x: Int, y: Int) => x + y

// Explicit approach
val add3: (Int, Int) => Int = (x, y) => { x + y }
val add4: (Int, Int) => Int = (x, y) => x + y

// Using a method as anonymous function
def m6(i: Int) = {i % 2 == 0}
l1.filter(m6)

// Assigning existing method to variable
val m7 = m6 _
l1.filter(m7)

val m8 = m6(_)
l1.filter(m8)

val m9 = m5
l1.filter(m9)

def add5(x: Int, y: Int) = {x + y}

val add6 = add5 _

add5(1, 2)
add6(1, 2)

val add7 = add5(_, _)
add7(1, 2)

// Partially applied functions
val addOne = add5(1, _)
addOne(2)

val powOf2 = scala.math.pow(_, 2)
powOf2(3)

val pow1 = scala.math.pow _
val pow2 = scala.math.pow(_, _)
pow1(2, 2)
pow2(2, 2)

def finalPrice(vat: Double,
               serviceCharge: Double,
               productPrice: Double): Double = {
  productPrice + productPrice*serviceCharge/100 + productPrice*vat/100
}

val vatApplied = finalPrice(20, _: Double, _: Double)
vatApplied(12.5, 120)
val vatAndServiceChargeApplied = finalPrice(20, 12.5, _: Double)
vatAndServiceChargeApplied(120)


// Function that accepts another function as a parameter
def executeFunction1(callback:() => Unit) = {
  callback()
}
val sayHello = () => println("hello!")
executeFunction1(sayHello)

def executeFunction2(callback: (String) => Unit, str: String): Unit = {
  callback(str)
}
val echo = (str: String) => println(str)
executeFunction2(echo, "Hello!")

// Function that returns another function
def echo1(s: String) = () => {
  println(s)
}
val e1 = echo1("Hi!")
e1()

def echo2(s: String) = (str: String) => {
  s + str
}
val e2 = echo2("Hi, ")
e2("Hello!")

def echo3(s1: String, s2: String): String => String = (s3: String) => {
  s1 + s2 + s3
}
val e3 = echo3("Hi", ", ")
e3("Deepak")