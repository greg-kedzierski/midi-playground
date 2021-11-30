package io.loskunos.midi.math

import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.int
import io.kotest.property.checkAll
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class SimplifiedRationalTest {
    @Test
    fun `create valid rational number from integer number`() {
        runBlocking {
            checkAll(Arb.int()) { number ->
                val rational = number.toRational()

                rational.numerator shouldBe number
                rational.denominator shouldBe 1
            }
        }
    }

    @Test
    fun `create reciprocal for a given integer number`() {
        runBlocking {
            checkAll(Arb.int()) { number ->
                val rational = number.reciprocal()

                rational.numerator shouldBe 1
                rational.denominator shouldBe number
            }
        }
    }

    @Test
    fun `rationalDiv should create ration number from two integers`() {
        runBlocking {
            checkAll(iterations = 20, Arb.int()) { numerator ->
                checkAll(iterations = 20, Arb.int()) { denominator ->
                    (numerator rationalDiv denominator) shouldBe SimplifiedRational(numerator, denominator)
                }
            }
        }

    }

    @Test
    fun `normalization of rational number`() {
        (4 rationalDiv 8).normalize() shouldBe (1 rationalDiv 2)
        (10 rationalDiv 4).normalize() shouldBe (5 rationalDiv 2)
        (6 rationalDiv 15).normalize() shouldBe (2 rationalDiv 5)
    }

    @Test
    fun `div operator should divide rational number by integer and return valid result`() {
        runBlocking {
            checkAll(iterations = 20, Arb.int()) { numerator ->
                checkAll(iterations = 20, Arb.int()) { denominator ->
                    checkAll(iterations = 10, Arb.int()) { divisor ->
                        (numerator rationalDiv denominator) / divisor shouldBe (numerator rationalDiv (divisor * denominator))
                    }
                }
            }
        }
    }

    @Test
    fun `half function should divide rational number by two`() {
        runBlocking {
            checkAll(iterations = 20, Arb.int()) { numerator ->
                checkAll(iterations = 20, Arb.int()) { denominator ->
                    (numerator rationalDiv denominator).half() shouldBe (numerator rationalDiv (2 * denominator))
                }
            }
        }
    }

    @Test
    fun `+ should add two rational numbers and return valid result`() {
        (1 rationalDiv 2) + (3 rationalDiv 5) shouldBe (11 rationalDiv 10)
        (3 rationalDiv 8) + (1 rationalDiv 8) shouldBe (4 rationalDiv 8)
    }

    @Test
    fun `== should return true for normalized and not normalized versions of same rational number`() {
        val number = (15 rationalDiv 6)

        number shouldBe (5 rationalDiv 2)
        number shouldBe (30 rationalDiv 12)
        number shouldNotBe (15 rationalDiv 2)
    }

    @Test
    fun `converting list of rationals to common denominator should return a list of rationals with common denominator`() {
        val rationals = listOf(
            1 rationalDiv 2,
            2 rationalDiv 3,
            6 rationalDiv 4,

        )

        rationals.toCommonDenominator() shouldContainExactly listOf(
            6 rationalDiv 12,
            8 rationalDiv 12,
            18 rationalDiv 12
        )
    }
}
