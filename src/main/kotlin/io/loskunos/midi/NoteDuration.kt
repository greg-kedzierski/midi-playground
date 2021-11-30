package io.loskunos.midi

import io.loskunos.midi.math.SimplifiedRational
import io.loskunos.midi.math.reciprocal
import io.loskunos.midi.math.toRational

sealed class NoteDuration(val duration: SimplifiedRational) {
    object Whole : NoteDuration(1.toRational())
    object Half : NoteDuration(2.reciprocal())
    object Quarter : NoteDuration(4.reciprocal())
    object Eight : NoteDuration(8.reciprocal())
    object Sixteenth : NoteDuration(16.reciprocal())

    class Dotted(private val originalDuration: NoteDuration) :
        NoteDuration(originalDuration.duration.let { (it + it.half()).normalize() }) {
        override fun toString(): String = "$originalDuration•"
    }

    class Custom(duration: SimplifiedRational) : NoteDuration(duration) {
        override fun toString(): String = "$duration"
    }

    fun dotted() = Dotted(this)

    override fun toString(): String = this.javaClass.simpleName
}



