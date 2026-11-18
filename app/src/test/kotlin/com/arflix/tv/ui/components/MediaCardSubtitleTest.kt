package com.arflix.tv.ui.components

import java.text.Bidi
import org.junit.Assert.assertEquals
import org.junit.Test

class MediaCardSubtitleTest {
    private val hebrewMovie = "\u05e1\u05e8\u05d8" // "Movie" in Hebrew

    /** The left-to-right runs a right-to-left line breaks [text] into, in logical order. */
    private fun ltrRuns(text: String): List<String> {
        val bidi = Bidi(text, Bidi.DIRECTION_RIGHT_TO_LEFT)
        return (0 until bidi.runCount)
            .filter { bidi.getRunLevel(it) % 2 == 0 }
            .map { text.substring(bidi.getRunStart(it), bidi.getRunLimit(it)).replace("\u200E", "").trim() }
    }

    @Test fun `without the mark a right-to-left line splits the day off the date`() {
        // The bug: the day is its own run, drawn after "Nov 2026" ("Nov 2026 13 · <type>").
        assertEquals(listOf("13", "Nov 2026"), ltrRuns("$hebrewMovie · 13 Nov 2026").filter { it.isNotEmpty() })
    }

    @Test fun `a right-to-left caption keeps the whole date as one run`() {
        assertEquals(listOf("13 Nov 2026"), ltrRuns(mediaCardSubtitle(hebrewMovie, "13 Nov 2026")).filter { it.isNotEmpty() })
    }

    @Test fun `a year alone stays one run`() {
        assertEquals(listOf("2026"), ltrRuns(mediaCardSubtitle(hebrewMovie, "2026")).filter { it.isNotEmpty() })
    }

    @Test fun `left-to-right captions read the same apart from the invisible mark`() {
        assertEquals("Movie · 13 Nov 2026", mediaCardSubtitle("Movie", "13 Nov 2026").replace("\u200E", ""))
        val bidi = Bidi(mediaCardSubtitle("Movie", "13 Nov 2026"), Bidi.DIRECTION_DEFAULT_LEFT_TO_RIGHT)
        assertEquals(true, bidi.isLeftToRight)
    }
}
