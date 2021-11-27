package io.loskunos.midi

import io.loskunos.midi.Octave.*

data class NoteWithOctave(val note: Note, val octave: Octave) {
    override fun toString(): String = "${note}_$octave"
}

fun octave(octave: Octave, vararg notes: Note): List<NoteWithOctave> =
    notes.map { it.octave(octave) }

fun octave(octave: Octave, b: () -> List<Note>): List<NoteWithOctave> =
    b().map { it.octave(octave) }

fun List<Note>.octave(octave: Octave): List<NoteWithOctave> = map { it.octave(octave) }

fun Note.octave(octave: Octave): NoteWithOctave = NoteWithOctave(this, octave)

val Note.o0: NoteWithOctave
    get() = NoteWithOctave(this, ZERO)

val Note.o1: NoteWithOctave
    get() = NoteWithOctave(this, ONE)

val Note.o2: NoteWithOctave
    get() = NoteWithOctave(this, TWO)

val Note.o3: NoteWithOctave
    get() = NoteWithOctave(this, THREE)

val Note.o4: NoteWithOctave
    get() = NoteWithOctave(this, FOUR)

val Note.o5: NoteWithOctave
    get() = NoteWithOctave(this, FIVE)

val Note.o6: NoteWithOctave
    get() = NoteWithOctave(this, SIX)

val Note.o7: NoteWithOctave
    get() = NoteWithOctave(this, SEVEN)

val Note.o8: NoteWithOctave
    get() = NoteWithOctave(this, EIGHT)