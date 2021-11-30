package io.loskunos.midi.math

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class MathTest : FreeSpec({
    "`gcd` should calculate greatest common divisor of two numbers" {
        gcd(10, 5) shouldBe 5
        gcd(5, 10) shouldBe 5
        gcd(12, 8) shouldBe 4
        gcd(8, 12) shouldBe 4
        gcd(16, 1) shouldBe 1
        gcd(1, 16) shouldBe 1
    }

    "`lcm`" - {
        "should calculate least common multiple of two numbers" {
            lcm(10, 15) shouldBe 30
            lcm(15, 10) shouldBe 30
            lcm(7, 8) shouldBe 56
            lcm(8, 7) shouldBe 56
            lcm(8, 10) shouldBe 40
            lcm(10, 8) shouldBe 40
        }
        "should calculate least common multiple of list of numbers" {
            listOf(4, 3).allLcm() shouldBe 12
            listOf(4, 3, 30).allLcm() shouldBe 60
            listOf(10, 15, 40).allLcm() shouldBe 120
            listOf(10, 15, 7, 11, 16, 11, 14, 156).allLcm() shouldBe 240240
        }
    }


})
