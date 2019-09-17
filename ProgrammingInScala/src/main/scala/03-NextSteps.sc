// + is a method in Int class
1 + 2
(1).+(2)

// Size of array cannot be changed but
// element values can be modified
// If you need immutable values then use List
val numNames = Array("one", "two", "three")
numNames(0) = "zero"
numNames(1) = "one"
numNames(2) = "two"
numNames

val l1 = List(1, 2)
val l2 = List(3, 4)
val l3 = l1 ::: l2

/**
 * Cons operator
 *
 * If a method is used in operator notation,
 * such as a * b, the method is invoked on the left
 * operand, as in a.*(b)
 *
 * If the method name ends in a colon, the
 * method is invoked on the right operand.
 */

val l4 = 0 :: l1
val l5 = l3.::(0)

val l6 = 1 :: 2 :: 3 :: 4 :: Nil
val l7 = Nil.::(4).::(3).::(2).::(1)

val l8 = List()
val l9 = Nil  // Returns a empty list

// tuple
val t1 = (1, 2)
t1._1
t1._2

val t2 = (1, 2, 3)
t2._3


/**
 * Sets
 */

import scala.collection.mutable

val ms1 = mutable.Set("Boeing", "Airbus")
ms1 += "Cessna"

ms1.contains("Boeing")

/**
 * Operator syntax
 */
ms1 contains "Boeing"


