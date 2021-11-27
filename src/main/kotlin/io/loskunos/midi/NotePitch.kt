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
    get() = NotePitch(this, SubContra)

val PitchClass.o1: NotePitch
    get() = NotePitch(this, Contra)

val PitchClass.o2: NotePitch
    get() = NotePitch(this, Great)

val PitchClass.o3: NotePitch
    get() = NotePitch(this, Small)

val PitchClass.o4: NotePitch
    get() = NotePitch(this, OneLine)

val PitchClass.o5: NotePitch
    get() = NotePitch(this, TwoLine)

val PitchClass.o6: NotePitch
    get() = NotePitch(this, ThreeLine)

val PitchClass.o7: NotePitch
    get() = NotePitch(this, FourLine)

val PitchClass.o8: NotePitch
    get() = NotePitch(this, FiveLine)