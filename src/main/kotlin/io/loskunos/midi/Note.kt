package io.loskunos.midi

data class Note(val pitch: NotePitch, val duration: NoteDuration) {
    override fun toString(): String = "$duration|$pitch"
}

fun NotePitch.duration(duration: NoteDuration): Note = Note(this, duration)