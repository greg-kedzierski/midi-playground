package io.loskunos.midi

/*
 * It's simplified because e.g. we don't care if denominator is non-zero number or not.
 */
data class SimplifiedRational(val numerator: Int, val denominator: Int) {
    fun normalize(): SimplifiedRational =
        findGCD(numerator, denominator)
            .let { gcd ->
                SimplifiedRational(numerator / gcd, denominator / gcd)
            }

    fun half() = this / 2

    operator fun div(divisor: Int): SimplifiedRational = SimplifiedRational(numerator, denominator * divisor)

    operator fun plus(other: SimplifiedRational): SimplifiedRational =
        if (this.denominator == other.denominator) {
            SimplifiedRational(
                numerator = this.numerator + other.numerator,
                denominator = this.denominator
            )
        } else {
            SimplifiedRational(
                numerator = this.numerator * other.denominator + this.denominator * other.numerator,
                denominator = this.denominator * other.denominator
            )
        }


    override fun equals(other: Any?): Boolean =
        (other is SimplifiedRational) &&
                this.normalize().let { normalizedThis ->
                    other.normalize().let { normalizedOther ->
                        normalizedThis.numerator == normalizedOther.numerator && normalizedThis.denominator == normalizedOther.denominator
                    }
                }

    override fun hashCode(): Int = normalize().let { 31 * it.numerator + it.denominator }

    private tailrec fun findGCD(a: Int, b: Int): Int = if (a == 0) b else findGCD(b % a, a)
}

infix fun Int.rationalDiv(other: Int) = SimplifiedRational(numerator = this, denominator = other)
fun Int.toRational() = SimplifiedRational(numerator = this, denominator = 1)
fun Int.reciprocal() = SimplifiedRational(numerator = 1, denominator = this)
