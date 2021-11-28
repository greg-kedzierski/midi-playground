package io.loskunos.midi

sealed class NoteDuration(val duration: SimplifiedRational) {
    object Whole : NoteDuration(1.toRational())
    object Half : NoteDuration(2.reciprocal())
    object Quarter : NoteDuration(4.reciprocal())
    object Eight : NoteDuration(8.reciprocal())
    object Sixteenth : NoteDuration(16.reciprocal())

    class Dotted(originalDuration: NoteDuration) :
        NoteDuration(originalDuration.duration.let { it + it.half() })

    class Custom(duration: SimplifiedRational) : NoteDuration(duration)
}



