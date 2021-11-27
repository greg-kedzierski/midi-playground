package io.loskunos.midi

import io.loskunos.midi.Octave.*

data class NotePitch(val pitchClass: PitchClass, val octave: Octave) {
    override fun toString(): String = "${pitchClass}_$octave"
}

fun octave(octave: Octave, vararg notes: PitchClass): List<NotePitch> =
    notes.map { it.octave(octave) }

fun octave(octave: Octave, b: () -> List<PitchClass>): List<NotePitch> =
    b().map { it.octave(octave) }

fun List<PitchClass>.octave(octave: Octave): List<NotePitch> = map { it.octave(octave) }

fun PitchClass.octave(octave: Octave): NotePitch = NotePitch(this, octave)

val PitchClass.o0: NotePitch
    get() = NotePitch(this, ZERO)

val PitchClass.o1: NotePitch
    get() = NotePitch(this, ONE)

val PitchClass.o2: NotePitch
    get() = NotePitch(this, TWO)

val PitchClass.o3: NotePitch
    get() = NotePitch(this, THREE)

val PitchClass.o4: NotePitch
    get() = NotePitch(this, FOUR)

val PitchClass.o5: NotePitch
    get() = NotePitch(this, FIVE)

val PitchClass.o6: NotePitch
    get() = NotePitch(this, SIX)

val PitchClass.o7: NotePitch
    get() = NotePitch(this, SEVEN)

val PitchClass.o8: NotePitch
    get() = NotePitch(this, EIGHT)