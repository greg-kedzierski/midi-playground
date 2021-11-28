package io.loskunos.midi

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.int
import io.kotest.property.checkAll

class SimplifiedRationalTest : FreeSpec({
    "`toRational()` should create rational number from integer number" {
        checkAll(Arb.int()) { number ->
            val rational = number.toRational()

            rational.numerator shouldBe number
            rational.denominator shouldBe 1
        }
    }
    "`reciprocal()` should create reciprocal for a given integer number" {
        checkAll(Arb.int()) { number ->
            val rational = number.reciprocal()

            rational.numerator shouldBe 1
            rational.denominator shouldBe number
        }
    }
    "`rationalDiv` should create ration number from two integers" {
        checkAll(iterations = 20, Arb.int()) { numerator ->
            checkAll(iterations = 20, Arb.int()) { denominator ->
                (numerator rationalDiv denominator) shouldBe SimplifiedRational(numerator, denominator)
            }
        }
    }
    "`normalize()` should return value of the normalization of rational number" {
        (4 rationalDiv 8).normalize() shouldBe (1 rationalDiv 2)
        (10 rationalDiv 4).normalize() shouldBe (5 rationalDiv 2)
        (6 rationalDiv 15).normalize() shouldBe (2 rationalDiv 5)
    }
    "`/` should divide rational number by integer and return valid result" {
        checkAll(iterations = 20, Arb.int()) { numerator ->
            checkAll(iterations = 20, Arb.int()) { denominator ->
                checkAll(iterations = 10, Arb.int()) { divisor ->
                    (numerator rationalDiv denominator) / divisor shouldBe (numerator rationalDiv (divisor * denominator))
                }
            }
        }
    }
    "`half()` should divide rational number by two" {
        checkAll(iterations = 20, Arb.int()) { numerator ->
            checkAll(iterations = 20, Arb.int()) { denominator ->
                (numerator rationalDiv denominator).half() shouldBe (numerator rationalDiv (2 * denominator))
            }
        }
    }
    "`+` should add two rational numbers and return valid result" - {
        "1/2 + 3/5 == 11/10" {
            (1 rationalDiv 2) + (3 rationalDiv 5) shouldBe (11 rationalDiv 10)
        }
        "3/8 + 1/8 == 4/8" {
            (3 rationalDiv 8) + (1 rationalDiv 8) shouldBe (4 rationalDiv 8)
        }
    }
    "`==` should return true for normalized and not normalized versions of same rational number" {
        val number = (15 rationalDiv 6)

        number shouldBe (5 rationalDiv 2)
        number shouldBe (30 rationalDiv 12)

        number shouldNotBe (15 rationalDiv 2)
    }
})
