package io.loskunos.midi.math

tailrec fun gcd(a: Int, b: Int): Int = if (a == 0) b else gcd(b % a, a)

// lcm is not defined for 0
fun lcm(a: Int, b: Int): Int = a * b / gcd(a, b)

fun List<Int>.allLcm() = this.reduce(::lcm)