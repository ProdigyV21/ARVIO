package com.arflix.tv.ui.screens.player.subtitles

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RetimeConsistencyTest {

    /** 600 paired lines over 45 minutes, with [residualAt] giving each line's error. */
    private fun pairs(residualAt: (Long) -> Long) = (0 until 600).map { index ->
        val t = index * 4_500L
        t to residualAt(t)
    }

    @Test
    fun `a map that fits everywhere passes`() {
        // Small jitter everywhere — Peaky Blinders' −47.8s map left ≤100ms per window.
        val verdict = RetimeConsistency.check(pairs { t -> (t / 4_500L % 7) * 30 - 90 })

        assertThat(verdict.fits).isTrue()
    }

    @Test
    fun `a map that is right early and seconds out late is refused`() {
        // The Shards S01E04 shape: fine for the first half, ~4s off after a cut / uneven drift.
        val verdict = RetimeConsistency.check(pairs { t -> if (t < 1_350_000L) 100L else -4_000L })

        assertThat(verdict.fits).isFalse()
        assertThat(verdict.spreadMs).isGreaterThan(RetimeConsistency.MAX_WINDOW_SPREAD_MS)
    }

    @Test
    fun `too little evidence never refuses`() {
        val few = (0 until 10).map { it * 60_000L to if (it < 5) 0L else 5_000L }

        assertThat(RetimeConsistency.check(few).fits).isTrue()
    }
}
