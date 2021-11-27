package io.loskunos.midi

import io.loskunos.midi.PitchClass.*

fun play(vararg notes: NotePitch): Unit {
    play(notes.toList())
}

fun play(notes: List<NotePitch>): Unit {
    println(notes.joinToString { "$it" })
}


fun main(): Unit {
    play(C.o2, D.o1)
    println(C)

//    val octave: Array<NoteWithOctave> = octave(1, C, D).toTypedArray()
//    play(*octave);
//
//    val octave2 = octave(1) {
//        listOf(C, D)
//    }
//    play(octave2);
//
//    val octave3 = listOf(Cis, D).octave(1)
//    play(octave3);
}