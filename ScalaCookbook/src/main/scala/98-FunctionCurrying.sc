// Normal version

def finalPrice(vat: Double,
               serviceCharge: Double,
               productPrice: Double): Double =
  productPrice + productPrice*serviceCharge/100 + productPrice*vat/100

finalPrice(20, 12.5, 120)

def finalPriceCurried(vat: Double)(serviceCharge: Double)(productPrice: Double): Double =
  productPrice + productPrice*serviceCharge/100 + productPrice*vat/100

val vatApplied = finalPriceCurried(20)_
val serviceChargeApplied = vatApplied(12.5)
serviceChargeApplied(120)
vatApplied(12.5)(120)
finalPriceCurried(20)(12.5)(120)