package io.loskunos.midi

sealed class PitchClass(val id: Int, val symbol: String) {
    // @formatter:off
    object C   : PitchClass(id = 1, symbol  = "C")
    object Cis : PitchClass(id = 2, symbol  = "C♯")
    object Des : PitchClass(id = 2, symbol  = "D♭")
    object D   : PitchClass(id = 3, symbol  = "D")
    object Dis : PitchClass(id = 4, symbol  = "D♯")
    object Es  : PitchClass(id = 4, symbol  = "E♭")
    object E   : PitchClass(id = 5, symbol  = "E")
    object F   : PitchClass(id = 6, symbol  = "F")
    object Fis : PitchClass(id = 7, symbol  = "F♯")
    object Ges : PitchClass(id = 7, symbol  = "G♭")
    object G   : PitchClass(id = 8, symbol  = "G")
    object Gis : PitchClass(id = 9, symbol  = "G♯")
    object As  : PitchClass(id = 9, symbol  = "A♭")
    object A   : PitchClass(id = 10, symbol = "A")
    object Ais : PitchClass(id = 11, symbol = "A♯")
    object Bes : PitchClass(id = 11, symbol = "B♭")
    object B   : PitchClass(id = 12, symbol = "B")
    // @formatter:on

    override fun toString(): String = symbol
}

