val votes = Seq(("scala", 1), ("java", 4), ("scala", 10), ("scala", 1), ("python", 10))
val orderedVotes = votes
  .groupBy(_._1)
  .map { case (which, counts) =>
    (which, counts.foldLeft(0)(_ + _._2))
  }.toSeq
  .sortBy(_._2)
  .reverse

// Seq[T]
// Set[T]
// Map[T]

// Arrays preserver order, mutable and can contain duplicates
val array = Array(1, 2, 3, 4, 5, 6, 6, 7, 8)
array(0) = 10
array.foreach(println)

// List preserve order, immutable and can contain duplicates
val list = List(1, 2, 3, 3, 4, 5)
list.foreach(println)

// Set doesn't preserve order, immutable and no duplicates
val set = Set(1, 5, 10, 2, 1, 5)
set.foreach(println)

// Seq creates a List by default
val seq = Seq(1, 2, 3, 4, 5, 5, 6)
seq.foreach(println)

// Tuple
val hostport1 = "localhost" -> 80
val hostport = ("localhost", 80)
hostport._1
hostport._2

hostport match {
  case ("localhost", port) => println(port)
  case _ => println("Didn't find")
}

// Map
val numbers = Map(1 -> "one", 2 -> "two", 3 -> "three")
numbers(1)
//numbers(0)
val three = numbers.get(3)
val four = numbers.get(4)

three.isDefined
four.isDefined
three.get
four.getOrElse("None")

val num = scala.collection.mutable.Map(1 -> "one", 2 -> "two", 3 -> "three")
num.getOrElseUpdate(0, "zero")
num += (4 -> "four")

// Functional combinators

array.map(_ * 2)
array.map((i: Int) => i*i)

def timesTwo(i: Int): Int = {
  i * 2
}

array.map(timesTwo)

array.filter(_ % 2 == 0)

array.zip(list)

array.partition(_ % 2 == 0)

array.find(_ < 5)

array.drop(4)

array.dropWhile(_ % 2 == 0)

array.foldLeft(0)(_ + _)

array.foldLeft(10)((accumulator: Int, n: Int) => accumulator + n)

array.foldRight(0)(_ + _)

array.foldRight(List[Int]()){ (n: Int, accumulatorList: List[Int]) =>
  n * 2 :: accumulatorList
}

List(List(1, 2, 3, 4, 5), List(6, 7), List(8, 9, 10)).flatten

// Maps
numbers.filter(numString => numString._1 % 2 == 0)
numbers.filter((numString: (Int, String)) => numString._1 % 2 == 0)
numbers.filter{ case (num, str) => num % 2 == 0}

/*
Seq
  - IndexedSeq
    - Array
    - Vector (Default)
    - Range
    - String
    - StringBuilder
  - LinearSeq
    - List (Default)
    - Queue
    - Stack
    - LinkedList
  - Buffer (scala.collection.mutable)
    - ArrayBuffer (Default mutable IndexedSeq/IndexedBuffer)
    - ListBuffer (Default mutable LinearSeq/LinearBuffer)
 */

val iSeq = IndexedSeq(1, 5, 3, 4, 2, 1)

val ibuff = scala.collection.mutable.IndexedBuffer(1, 3, 2, 1, 10, 4)
val mISeq = scala.collection.mutable.IndexedSeq(1, 3, 2, 1, 10, 4)

val mLSeq = scala.collection.mutable.ListBuffer(1, 3, 2, 1, 10, 4)

// Collection with variable types

val variableType = List[Number](1, 3, 2, 1, 10D, 4L)
val variableType1 = List[AnyVal](1, 3, 2, 1, 10D, 4L)

val v1 = IndexedSeq(1, 3, 2, 1, 10, 4)
v1 :+ 8 // Append, create new seq
3 +: v1 // Prepend, create new seq
v1 // v1 hasn't changed

for (i <- v1) {
  println(i)
}

for (i <- 0 until v1.size) {
  println(v1(i))
}

for {
  i <- v1
  if i % 2 == 0
} println(i)

for ((n, i) <- v1.zipWithIndex) {
  println(s"$i $n")
}

v1.zipWithIndex.foreach {
  case (n, i) => println(s"$i $n")
}

// ** IMP **
// Extract only defined values from a seq
val v2 = Vector(Some(1), None, Some(2), None, None, Some(4))
v2.flatten


// flatmap
// map followed by flatten
val v3 = Vector("1", "three", "2", "1", "abcd", "4", "xyz")
def toInt(i: String): Option[Int] = {
  try {
    Some(Integer.parseInt(i.trim))
  } catch {
    case _: Throwable => None
  }
}
v3.map(toInt)
v3.map(toInt).flatten
v3.flatMap(toInt)

v1.dropRight(2)

v1.groupBy(_ > 1)
votes.groupBy(_._1)

v1.reduceLeft(_ + _)
v1.foldLeft(0)(_ + _)
v1.reduceLeft(_ max _)
v1.foldLeft(-999)(_ max _)
v3.reduceLeft((x, y) => if (x.length > y.length) x else y)
v3.foldLeft("")((x, y) => if (x.length > y.length) x else y)

v1.distinct

val findMax: (Int, Int) => Int = (x, y) => {
  val winner = x max y
  println(s"$x max $y = $winner")
  winner
}

val myAdd = (x: Int, y: Int) => {
  val sum = x + y
  println(s"$x + $y = $sum")
  sum
}

v1.reduceLeft(findMax)
v1.reduceLeft(myAdd)

// ** To use distinct with your class implement equals and hashcode metnod

// Using case class because it has inbuilt equals and hashcode
case class Person(firstName: String, lastName: String)

val p1 = IndexedSeq(Person("Deepak", "Telkar"), Person("Megha", "Telkar"), Person("Deepak", "Telkar"))
p1.distinct

val a1 = IndexedSeq(1, 3, 2, 1, 10, 4)
val a2 = IndexedSeq(4, 11, 2, 5, 12, 6)

var m1 = scala.collection.mutable.IndexedSeq(4, 11, 2, 5, 12, 6)
m1 ++= a1
m1

val a3 = a1 ++ a2
a1.union(a2) // depricated
a1.concat(a2)

a1.intersect(a2)
a1.diff(a2)

// Lazy view on collection
1 to 100000

(1 to 100000).view
v1.view.force

v1.foreach(println)
v1.view.foreach(println) // foreach is not transformer method

v1.map(_ * 2)
v1.view.map(_ * 2)
v1.view.map(_ * 2).foreach(println)

(1 to 20 by 3).toArray
('a' to 'z' by 2).toArray
(1 until 20 by 3).toArray
(1 to 10).toArray
(1 until 10).toArray

// Enumeration

object Margin extends Enumeration {
  type Margin = Value
  val TOP, BOTTOM, LEFT, RIGHT = Value
}

Margin.TOP
println(Margin.BOTTOM)

// tuple as collection
val t1 = (1, "one")
t1.productIterator.foreach(println)
t1.productIterator.toArray

v3.sorted
v3.sorted(Ordering.String.reverse)
v3.sortWith(_.length > _.length)
v1.sortWith(_ > _)

// ** p1.sorted          No implicit ordering defined
// Mixin with Ordered trait

p1.sortWith(_.firstName > _.firstName)
p1.sortBy(_.firstName)
// Sort by lastName and firstName
p1.sortBy(p => (p.lastName, p.firstName))

case class Human(firstName: String, lastName: String) extends Ordered[Human] {
  override def compare(that: Human) = this.firstName.compare(that.firstName)
  override def toString: String = s"$firstName $lastName"
}

val h1 = Vector(Human("Hetansh", "Telkar"), Human("Megha", "Telkar"), Human("Deepak", "Telkar"))
h1.sorted
h1.sorted(Ordering[Human].reverse)
h1.sortBy(_.firstName)(Ordering[String].reverse)

h1.mkString(", ")
v1.mkString("[", ", ", "]")
