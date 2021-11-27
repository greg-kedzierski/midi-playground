package io.loskunos.midi

enum class Octave {
    SubContra,
    Contra,
    Great,
    Small,
    OneLine,
    TwoLine,
    ThreeLine,
    FourLine,
    FiveLine;

    companion object {
        val o0 = SubContra
        val o1 = Contra
        val o2 = Great
        val o3 = Small
        val o4 = OneLine
        val o5 = TwoLine
        val o6 = ThreeLine
        val o7 = FourLine
        val o8 = FiveLine
    }
}
