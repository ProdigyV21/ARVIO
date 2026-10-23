package com.arflix.tv.ui.screens.player.subtitles.bilingual

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import kotlin.math.abs

/** Fixtures shared by the bilingual tests: a dialogue with irregular rhythm. */
object DialogueFixture {
    /** (startMs, endMs) of 30 English lines with uneven gaps and durations. */
    val spans: List<Pair<Long, Long>> = run {
        var t = 5_000L
        val durations = longArrayOf(2_100, 1_400, 3_200, 1_800, 2_600, 900, 2_300, 3_800, 1_500, 2_000)
        val gaps = longArrayOf(400, 1_700, 300, 2_900, 600, 1_100, 250, 4_200, 800, 1_300)
        (0 until 30).map { i ->
            val s = t
            val e = s + durations[i % durations.size]
            t = e + gaps[(i * 3) % gaps.size]
            s to e
        }
    }

    fun english(): List<TimedText> = spans.mapIndexed { i, (s, e) -> TimedText(s, e, "EN line $i") }

    /**
     * Chinese track of the same dialogue, cut differently: every 3rd English line is split into
     * two Chinese cues, lines 10/11 are merged into one Chinese cue, and everything is shifted by
     * [offsetMs] with ±60 ms jitter (as a different subtitler would time it).
     */
    fun chinese(offsetMs: Long = 0L): List<TimedText> {
        val out = mutableListOf<TimedText>()
        var i = 0
        while (i < spans.size) {
            val (s, e) = spans[i]
            val j = if (i % 2 == 0) 60L else -60L
            when {
                i == 10 -> {
                    val e2 = spans[11].second
                    out += TimedText(s + offsetMs + j, e2 + offsetMs, "中文 10+11")
                    i += 2; continue
                }
                i % 3 == 0 -> {
                    val mid = (s + e) / 2
                    out += TimedText(s + offsetMs + j, mid + offsetMs, "中文 $i 上")
                    out += TimedText(mid + offsetMs + 40, e + offsetMs, "中文 $i 下")
                }
                else -> out += TimedText(s + offsetMs + j, e + offsetMs - j, "中文 $i")
            }
            i++
        }
        return out
    }
}

class BilingualAlignerTest {

    @Test fun `identical timing aligns at zero with high coverage`() {
        val a = BilingualAligner.align(DialogueFixture.english(), DialogueFixture.chinese(0))
        assertTrue(a.toString(), a.reliable)
        assertTrue(abs(a.offsetMs) <= 100)
        assertTrue(a.coverage > 0.85)
    }

    @Test fun `a constant offset is found and removed`() {
        for (offset in listOf(1_200L, -2_500L, 4_000L)) {
            val a = BilingualAligner.align(DialogueFixture.english(), DialogueFixture.chinese(offset))
            assertTrue("offset $offset → $a", a.reliable)
            // The aligner returns the shift to ADD to the secondary track.
            assertTrue("offset $offset → ${a.offsetMs}", abs(a.offsetMs + offset) <= 100)
        }
    }

    @Test fun `different segmentation pairs by time, never by index`() {
        val en = DialogueFixture.english()
        val zh = DialogueFixture.chinese(1_200)
        val a = BilingualAligner.align(en, zh)
        val pairs = BilingualAligner.pairAll(en, zh, a.offsetMs).associate { it.first.text to it.second }
        // One English line split into two Chinese cues → both shown with it, in order.
        assertEquals("中文 0 上\n中文 0 下", pairs["EN line 0"])
        assertEquals("中文 3 上\n中文 3 下", pairs["EN line 3"])
        // Plain 1:1 lines.
        assertEquals("中文 1", pairs["EN line 1"])
        assertEquals("中文 4", pairs["EN line 4"])
        // Two English lines merged into one Chinese cue → that cue accompanies both.
        assertEquals("中文 10+11", pairs["EN line 10"])
        assertEquals("中文 10+11", pairs["EN line 11"])
        // Index-based pairing would have put "中文 1" next to line 2 (the split shifts indices).
        assertEquals("中文 2", pairs["EN line 2"])
        // A neighbour that only touches the edge is not attached.
        assertFalse(pairs["EN line 5"]!!.contains("中文 4"))
    }

    @Test fun `unrelated timings are reported unreliable`() {
        val en = DialogueFixture.english()
        // A track with a completely different rhythm (e.g. another episode's subtitle).
        val other = (0 until 40).map { i ->
            val s = 4_000L + i * 2_350L + (i * 7919 % 1_000)
            TimedText(s, s + 1_000 + (i * 31 % 700), "其他 $i")
        }
        val a = BilingualAligner.align(en, other)
        assertFalse(a.toString(), a.reliable)
    }

    @Test fun `too little data is not trusted`() {
        val a = BilingualAligner.align(DialogueFixture.english().take(3), DialogueFixture.chinese().take(3))
        assertFalse(a.reliable)
        assertEquals(BilingualAligner.UnreliableReason.TOO_FEW_CUES, a.reason)
    }

    @Test fun `a gap in the secondary language yields no partner`() {
        val en = DialogueFixture.english()
        val zh = DialogueFixture.chinese(0).filterNot { it.text == "中文 7" }
        val a = BilingualAligner.align(en, zh)
        assertTrue(a.reliable)
        assertNull(BilingualAligner.pairAll(en, zh, a.offsetMs).first { it.first.text == "EN line 7" }.second)
    }
}
