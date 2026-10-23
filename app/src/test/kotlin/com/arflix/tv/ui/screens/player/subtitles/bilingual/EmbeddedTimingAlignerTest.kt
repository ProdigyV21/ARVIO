package com.arflix.tv.ui.screens.player.subtitles.bilingual

import com.arflix.tv.ui.screens.player.subtitles.bilingual.EmbeddedTimingAligner.Interval
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import kotlin.random.Random

class EmbeddedTimingAlignerTest {
    /** Irregular dialogue: gaps of 1.2–6 s, lines of 1–4 s. */
    private fun dialogue(count: Int, seed: Int, startMs: Long = 60_000L): List<Interval> {
        val rnd = Random(seed)
        var t = startMs
        return (0 until count).map {
            t += 1_200L + rnd.nextLong(4_800L)
            val d = 1_000L + rnd.nextLong(3_000L)
            Interval(t, t + d).also { t += d }
        }
    }

    @Test fun findsPlainShiftAcrossLanguages() {
        val ref = dialogue(120, 1)
        // Another language: a few lines split/merged/missing, start jitter ±120 ms, delayed 2.5 s.
        val rnd = Random(7)
        val target = ref.filterIndexed { i, _ -> i % 9 != 4 }
            .map { val j = rnd.nextLong(-120L, 120L); Interval(it.startMs - 2_500L + j, it.endMs - 2_500L + j) }
        val r = EmbeddedTimingAligner.align(ref, target)
        assertNotNull(r)
        assertTrue(r!!.confident)
        assertTrue(r.isPlainShift)
        assertTrue("shift ${r.shiftMs}", kotlin.math.abs(r.shiftMs - 2_500L) <= 150L)
    }

    @Test fun worksWithBufferedSliceOfReference() {
        val ref = dialogue(400, 3)
        val slice = ref.take(40) // only the first minutes were demuxed
        val target = ref.map { Interval(it.startMs + 1_800L, it.endMs + 1_800L) }
        val r = EmbeddedTimingAligner.align(slice, target)!!
        assertTrue(r.confident)
        assertEquals(-1_800L, r.shiftMs)
    }

    @Test fun detectsFrameRateMismatch() {
        val ref = dialogue(150, 5)
        val scale = 25.0 / 23.976
        // Subtitle authored for 25 fps: subtitle time = media / scale.
        val target = ref.map { Interval(Math.round(it.startMs / scale), Math.round(it.endMs / scale)) }
        val r = EmbeddedTimingAligner.align(ref, target)!!
        assertTrue(r.confident)
        assertEquals(scale, r.scale, 1e-9)
        assertTrue(kotlin.math.abs(r.shiftMs) <= 150L)
    }

    @Test fun unrelatedSubtitleIsNotConfident() {
        val ref = dialogue(120, 11)
        val other = dialogue(120, 99)
        val r = EmbeddedTimingAligner.align(ref, other)
        assertFalse(r?.confident ?: false)
    }

    @Test fun tooFewCuesGivesNothing() {
        assertEquals(null, EmbeddedTimingAligner.align(dialogue(5, 1), dialogue(50, 1)))
    }
}
