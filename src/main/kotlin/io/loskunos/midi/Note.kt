package io.loskunos.midi

sealed class Note(val noteNo: Int, val symbol: String) {
    // @formatter:off
    object C   : Note(noteNo = 1, symbol  = "C")
    object Cis : Note(noteNo = 2, symbol  = "C♯")
    object Des : Note(noteNo = 2, symbol  = "D♭")
    object D   : Note(noteNo = 3, symbol  = "D")
    object Dis : Note(noteNo = 4, symbol  = "D♯")
    object Es  : Note(noteNo = 4, symbol  = "E♭")
    object E   : Note(noteNo = 5, symbol  = "E")
    object F   : Note(noteNo = 6, symbol  = "F")
    object Fis : Note(noteNo = 7, symbol  = "F♯")
    object Ges : Note(noteNo = 7, symbol  = "G♭")
    object G   : Note(noteNo = 8, symbol  = "G")
    object Gis : Note(noteNo = 9, symbol  = "G♯")
    object As  : Note(noteNo = 9, symbol  = "A♭")
    object A   : Note(noteNo = 10, symbol = "A")
    object Ais : Note(noteNo = 11, symbol = "A♯")
    object Bes : Note(noteNo = 11, symbol = "B♭")
    object B   : Note(noteNo = 12, symbol = "B")
    // @formatter:on

    override fun toString(): String = symbol
}

