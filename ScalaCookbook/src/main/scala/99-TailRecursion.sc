// Non tail recursive example

def mySum(n: Int): Int = {
  if(n == 1) n
  else n + mySum(n - 1)
}

mySum(99)
mySum(999)
mySum(9999)
//mySum(99999)  // Stack overflow

import scala.annotation.tailrec

def mySumImproved(n: Int): Long = {

  @tailrec
  def total(i: Int, sum: Long): Long = {
    if(i <= 1) sum + 1
    else total(i - 1, i + sum)
  }
  total(n, 0)
}

mySumImproved(9999)
mySumImproved(99999) // No stack overflow now

// Already tail recursive
def newSum(n: BigInt): BigInt = (BigInt(1) to n).sum

newSum(99999)

def factorial(n: Long): Long = {
  if (n <= 1) 1
  else n * factorial(n - 1)
}

factorial(10)
//factorial(99999) //

def factorialImproved(n: Int): BigInt = {

  @tailrec
  def fact(n: Int, f: BigInt): BigInt = {
    if (n <= 1) f
    else fact(n - 1, f * n)
  }
  fact(n, 1)
}

factorialImproved(10)
factorialImproved(99999)

// foldLeft is already tail recursive optimized
def newFact1(n: BigInt): BigInt = (BigInt(1) to n).foldLeft(BigInt(1))(_ * _)

newFact1(99999)

// product is also tail recursive
def newFact2(n: BigInt): BigInt = (BigInt(1) to n).product

newFact2(99999)

