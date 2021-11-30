package io.loskunos.midi

import io.loskunos.midi.NoteDuration.*
import io.loskunos.midi.PitchClass.C
import io.loskunos.midi.PitchClass.D
import io.loskunos.midi.math.rationalDiv
import io.loskunos.midi.math.toCommonDenominator

fun play(vararg notes: Note) {
    play(notes.toList())
}

fun play(notes: List<Note>) {
    println(notes.map { it.duration.duration })
    println(notes.map { it.duration.duration }.toCommonDenominator())

    val a = notes.map { it.duration.duration }.toCommonDenominator().zip(notes).map { (dur, note) ->
        note.copy(duration = Custom(dur))
    }
    println(a)

    println(notes.joinToString())

    val b = a.map { note ->
        List(note.duration.duration.numerator - 1) { "•" }
            .joinToString(" ")
            .let { "${note.pitch} $it" }

    }
    println(b.joinToString(" ") )
}


fun main() {
    play(
        C.o2.duration(Whole),
        D.o1.duration(Half),
        D.o1.duration(Half.dotted()),
        D.o1.duration(Custom(3 rationalDiv 4)),
        D.o1.duration(Custom(3 rationalDiv 4).dotted())
    )
}