package io.loskunos.midi

import io.kotest.core.spec.style.FreeSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.Exhaustive
import io.kotest.property.arbitrary.element
import io.kotest.property.arbitrary.list
import io.kotest.property.checkAll
import io.kotest.property.exhaustive.collection
import io.kotest.property.exhaustive.enum
import io.loskunos.midi.Note.C
import io.loskunos.midi.Octave.Companion.o0
import io.loskunos.midi.Octave.Companion.o1
import io.loskunos.midi.Octave.Companion.o2
import io.loskunos.midi.Octave.Companion.o3
import io.loskunos.midi.Octave.Companion.o4
import io.loskunos.midi.Octave.Companion.o5
import io.loskunos.midi.Octave.Companion.o6
import io.loskunos.midi.Octave.Companion.o7
import io.loskunos.midi.Octave.Companion.o8

class NoteWithOctaveTest : FreeSpec({
    val allNotes = Note::class.sealedSubclasses.map { it.objectInstance!! }
    "note promoted to note with octave " - {
        "should be the same note" {

            checkAll(Exhaustive.collection(allNotes)) { note ->
                note.octave(o1).note shouldBe note
            }
        }

        "should have given octave" {
            checkAll(Exhaustive.enum<Octave>()) { givenOctave ->
                C.octave(givenOctave) shouldBe NoteWithOctave(C, givenOctave)
            }
        }
    }

    "octave function with the list of notes" - {
        "returns empty list when no notes were provided" {
            checkAll(Exhaustive.enum<Octave>()) { givenOctave ->
                forAll(
                    row { octave(givenOctave) },
                    row { octave(givenOctave) { listOf() } },
                    row { listOf<Note>().octave(givenOctave) }
                ) { octaveFun ->
                    octaveFun().shouldBeEmpty()
                }
            }
        }

        "returns list of notes with given octave" {
            checkAll(iterations = 10, Arb.list(Arb.element(allNotes), range = 0..10)) { notes ->
                println(notes)
                checkAll(Exhaustive.enum<Octave>()) { givenOctave ->
                    forAll(
                        row { octave(givenOctave, *notes.toTypedArray()) },
                        row { octave(givenOctave) { notes } },
                        row { notes.octave(givenOctave) }
                    ) { octaveFun ->
                        val result = octaveFun()
                        result.map { it.octave }.forEach { it shouldBe givenOctave }
                        result.map { it.note } shouldContainExactly notes
                    }
                }
            }
        }
    }

    "o0-o8 functions should return note with valid octave" {
        C.o0.octave shouldBe o0
        C.o1.octave shouldBe o1
        C.o2.octave shouldBe o2
        C.o3.octave shouldBe o3
        C.o4.octave shouldBe o4
        C.o5.octave shouldBe o5
        C.o6.octave shouldBe o6
        C.o7.octave shouldBe o7
        C.o8.octave shouldBe o8
    }
})
