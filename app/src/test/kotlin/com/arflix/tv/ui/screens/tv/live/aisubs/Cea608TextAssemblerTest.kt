package com.arflix.tv.ui.screens.tv.live.aisubs

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class Cea608TextAssemblerTest {
    private val sentences = ArrayList<CaptionSentence>()
    private val assembler = Cea608TextAssembler { sentences += it }

    /** cc_data triplets on field 1 (header 0xFC = valid, NTSC field 1). */
    private fun cc(vararg pairs: Pair<Int, Int>, header: Int = 0xFC): ByteArray =
        pairs.flatMap { (a, b) -> listOf(header.toByte(), a.toByte(), b.toByte()) }.toByteArray()

    private fun text(s: String): ByteArray {
        val padded = if (s.length % 2 == 0) s else "$s\u0000"
        return cc(*padded.chunked(2).map { it[0].code to it[1].code }.toTypedArray())
    }

    private fun feed(ms: Long, data: ByteArray) = assembler.feed(ms * 1_000, data, 0, data.size)

    private val carriageReturn = cc(0x14 to 0x2D)

    @Test fun charactersBecomeASentenceEndingAtAFullStop() {
        feed(1_000, text("HELLO "))
        feed(1_500, text("WORLD."))
        assertEquals(listOf(CaptionSentence(1_000_000, 1_500_000, "HELLO WORLD.")), sentences)
    }

    @Test fun repeatedControlCodesActOnce() {
        feed(1_000, text("ONE"))
        // Control codes come twice in a row; the pair must give one word boundary, not two.
        feed(1_100, cc(0x14 to 0x2D, 0x14 to 0x2D))
        feed(1_200, text("TWO."))
        assertEquals("ONE TWO.", sentences.single().text)
    }

    @Test fun carriageReturnSeparatesWords() {
        feed(1_000, text("ROLL"))
        feed(1_100, carriageReturn)
        feed(1_200, text("UP."))
        assertEquals("ROLL UP.", sentences.single().text)
    }

    @Test fun backspaceRemovesTheLastCharacter() {
        feed(1_000, text("CATS"))
        feed(1_100, cc(0x14 to 0x21))
        feed(1_200, text("!"))
        assertEquals("CAT!", sentences.single().text)
    }

    @Test fun paddingAndSecondFieldAreIgnored() {
        feed(1_000, cc(0x80 to 0x80))
        feed(1_100, text("XY").also { it[0] = 0xFD.toByte() })
        feed(1_200, text("OK."))
        assertEquals("OK.", sentences.single().text)
    }

    @Test fun secondCaptionChannelIsIgnored() {
        feed(1_000, cc(0x1C to 0x2D))
        feed(1_100, text("A."))
        assertEquals("A.", sentences.single().text)
    }

    @Test fun aPauseEndsTheSentence() {
        feed(1_000, text("NO STOP HERE"))
        assertTrue(sentences.isEmpty())
        feed(1_000 + Cea608TextAssembler.GAP_US / 1_000 + 100, cc(0x80 to 0x80))
        assertEquals("NO STOP HERE", sentences.single().text)
    }

    @Test fun longRunOnCaptionsAreCutAtAWordAfterFiveSeconds() {
        // Roll-up captions arrive a word at a time, without pauses.
        listOf("THIS ", "GOES ", "ON ", "AND ", "ON ", "AND ").forEachIndexed { second, word ->
            feed(second * 1_000L, text(word))
        }
        assertEquals("THIS GOES ON AND ON AND", sentences.single().text)
        assertEquals(0L, sentences.single().startUs)
        assertEquals(5_000_000L, sentences.single().endUs)
    }

    @Test fun resetDropsWhatWasCollected() {
        feed(1_000, text("HALF"))
        assembler.reset()
        feed(1_100, text("NEW."))
        assertEquals("NEW.", sentences.single().text)
    }

    @Test fun musicNoteIsAWordBoundaryNotText() {
        feed(1_000, cc(0x11 to 0x37))
        assertFalse(assembler.sawText)
        feed(1_100, text("SONG."))
        assertEquals("SONG.", sentences.single().text)
    }
}
