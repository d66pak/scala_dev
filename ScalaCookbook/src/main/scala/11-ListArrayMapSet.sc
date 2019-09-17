// 1
// :: is 'con' method
val l1 = 1 :: 2 :: 3 :: Nil

// 2
val l2 = List(1, 2, 3)

// 3
val l3 = List[Number](1, 2L, 3.0D)

// 4
val l4 = List.fill(3)(1)

// 5
val l5 = "123".toList

// 6
val l6 = List.tabulate(5)(n => n + n)

val ml1 = scala.collection.mutable.ListBuffer(4, 11, 2, 5, 12, 6)
ml1 += 4
ml1 -= 5
ml1 :+ 6
0 +: ml1

val l7 = l1 ++ l2
val l8 = l1 ::: l2
val l9 = l1.concat(l2)

val s1 = (1 to 1000).toStream
s1.head
s1.tail

val s2 = l1.view

val s3 = 1 #:: 2 #:: 3 #:: Stream.empty

val a1 = new Array[Integer](3)
a1(0) = 1
a1(1) = 2
a1(2) = 3
a1
a1(0) = 0
a1

val a2 = Array(1, 2, 3)
a2(0) = 0
a2

var ma1 = scala.collection.mutable.IndexedSeq(1, 2, 3)

var ma2 = scala.collection.mutable.ArrayBuffer[Int]()
ma2 += 1
ma2 += 2
ma2 += 3

var ma3 = scala.collection.mutable.ArrayBuffer(4, 11, 2, 5, 12, 6)
ma3.sorted // Not inplace sorting
ma3

// Inplace sorting
val a3 = Array(4, 11, 2, 5, 12, 6)
scala.util.Sorting.quickSort(a3)
a3

val m1 = Map(1 -> "one", 2 -> "two", 3 -> "three")
val m2 = Map((1, "one"), (2, "two"), (3, "three"))

val mm1 = scala.collection.mutable.Map(1 -> "one", 2 -> "two", 3 -> "three")

// Replacing key, val in mutable map
val m3 = m1 + (1 -> "ONE")

m1(1)
m1.get(1)
m1.getOrElse(1, "one")

for ((k, v) <- m1) println(s"$k -> $v")
m1.foreach{
  case(k, v) => println(s"$k -> $v")
}

// Reverse key value
val m4 = for((k, v) <- m1) yield (v, k)

m4.contains("one")

m1.filterKeys(_ > 1)

mm1.filterInPlace((k, v) => k < 3)

mm1.mapValuesInPlace((k, v) => v.toUpperCase)

val lm1 = scala.collection.mutable.ListMap(m1.toSeq.sortWith(_._1 > _._1):_*)
m1.toSeq.sortWith(_._1 > _._1)

m1.max
m1.keysIterator.max
m1.valuesIterator.max
m1.keysIterator.reduceLeft((x, y) => if (x > y) x else y)
m1.keysIterator.reduceLeft(_ max _)

var ms1 = scala.collection.mutable.Set[Int]()
ms1 += 1
ms1 ++= Vector(3, 4, 5, 6, 7)

ms1.contains(5)

ms1 -= 1
ms1 = ms1 - 5

var se1 = Set(2, 1, 6, 3, 7)

se1 += 0
se1 += 10
se1 -= 1
se1

var sse1 = scala.collection.SortedSet(10,3,6,2,1,0,4,9,7,8)