package com.arflix.tv.ui.screens.player

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class SubtitleAutoSyncTest {

    // ── robustMeanOfLongs ───────────────────────────────────────────────────────

    @Test
    fun `robust mean of empty list is zero`() {
        assertThat(SubtitleAutoSync.robustMeanOfLongs(emptyList())).isEqualTo(0L)
    }

    @Test
    fun `one or two values reduce to median`() {
        assertThat(SubtitleAutoSync.robustMeanOfLongs(listOf(1200L))).isEqualTo(1200L)
        assertThat(SubtitleAutoSync.robustMeanOfLongs(listOf(1000L, 2000L))).isEqualTo(1500L)
    }

    @Test
    fun `gross outlier is rejected before averaging`() {
        // 3 agreeing points + one wild mismatch (bad LLM pair) — the outlier must not shift the result.
        val values = listOf(1000L, 1100L, 1050L, 9000L)
        assertThat(SubtitleAutoSync.robustMeanOfLongs(values)).isEqualTo(1050L)
    }

    @Test
    fun `agreeing points are averaged not medianed`() {
        // Mean (1100) is a tighter estimate than the median (1050) on clean data.
        val values = listOf(1000L, 1100L, 1200L)
        assertThat(SubtitleAutoSync.robustMeanOfLongs(values)).isEqualTo(1100L)
    }

    // ── isNonDialogueMusicCue ───────────────────────────────────────────────────

    @Test
    fun `music and sfx annotations are filtered`() {
        assertThat(SubtitleAutoSync.isNonDialogueMusicCue("♪ la la la ♪")).isTrue()
        assertThat(SubtitleAutoSync.isNonDialogueMusicCue("[Music]")).isTrue()
        assertThat(SubtitleAutoSync.isNonDialogueMusicCue("(singing)")).isTrue()
        assertThat(SubtitleAutoSync.isNonDialogueMusicCue("[tense instrumental]")).isTrue()
    }

    @Test
    fun `dialogue is not filtered`() {
        assertThat(SubtitleAutoSync.isNonDialogueMusicCue("Get busy living, or get busy dying.")).isFalse()
        // Bracketed but not music: speaker tags stay (they still carry dialogue identity).
        assertThat(SubtitleAutoSync.isNonDialogueMusicCue("[Red] I hope.")).isFalse()
    }

    // ── targetWindow ────────────────────────────────────────────────────────────

    @Test
    fun `window centers on the nearest addon line and clamps at edges`() {
        val starts = (0 until 100).map { it * 2000L } // a line every 2s
        val window = SubtitleAutoSync.targetWindow(starts, approxTrueTimeMs = 50_000L)
        assertThat(window.first).isEqualTo(25 - SubtitleAutoSync.TARGET_WINDOW_RADIUS)
        assertThat(window.last).isEqualTo(25 + SubtitleAutoSync.TARGET_WINDOW_RADIUS)

        val nearStart = SubtitleAutoSync.targetWindow(starts, approxTrueTimeMs = 0L)
        assertThat(nearStart.first).isEqualTo(0)

        val widened = SubtitleAutoSync.targetWindow(starts, approxTrueTimeMs = 50_000L, radius = 48)
        assertThat(widened.first).isEqualTo(0)
        assertThat(widened.last).isEqualTo(73)
    }

    // ── fitDriftLineRobust ──────────────────────────────────────────────────────

    private fun anchor(posMs: Long, delayMs: Long) = SubtitleAutoSync.DriftAnchorPoint(posMs, delayMs)

    @Test
    fun `two anchors give the exact line`() {
        // 24 vs 25 fps style drift: ~1s more delay per 24s → rate ≈ 0.0417.
        val fit = SubtitleAutoSync.fitDriftLineRobust(
            listOf(anchor(0L, 1000L), anchor(600_000L, 26_000L))
        )
        assertThat(fit).isNotNull()
        assertThat(fit!!.rate).isWithin(1e-9).of(25_000.0 / 600_000.0)
        assertThat(fit.interceptMs).isWithin(1e-6).of(1000.0)
    }

    @Test
    fun `single bad anchor is dropped and the rest refit`() {
        // Perfect line delay = 500 + 0.01·pos, plus one wild anchor.
        val good = listOf(
            anchor(0L, 500L),
            anchor(300_000L, 3_500L),
            anchor(600_000L, 6_500L)
        )
        val fit = SubtitleAutoSync.fitDriftLineRobust(good + anchor(450_000L, 20_000L))
        assertThat(fit).isNotNull()
        assertThat(fit!!.rate).isWithin(1e-6).of(0.01)
        assertThat(fit.interceptMs).isWithin(1e-3).of(500.0)
    }

    @Test
    fun `inconsistent anchors reject the fit`() {
        // No single line fits these within the residual bound, even after dropping one.
        val fit = SubtitleAutoSync.fitDriftLineRobust(
            listOf(
                anchor(0L, 0L),
                anchor(200_000L, 5_000L),
                anchor(400_000L, -4_000L),
                anchor(600_000L, 9_000L)
            )
        )
        assertThat(fit).isNull()
    }

    @Test
    fun `unmeasurable slope snaps to a flat fit`() {
        // Total delay change across the span (200 ms over 10 min) is inside the per-anchor noise
        // bound — a slope this small is noise, not drift, and must not be applied as a rate.
        val fit = SubtitleAutoSync.fitDriftLineRobust(
            listOf(anchor(0L, 1000L), anchor(600_000L, 1200L))
        )
        assertThat(fit).isNotNull()
        assertThat(fit!!.rate).isEqualTo(0.0)
        assertThat(fit.interceptMs).isWithin(1e-6).of(1100.0)
    }

    @Test
    fun `anchor 1 is never the anchor dropped`() {
        // Anchors 2–4 lie on a clean line that misses anchor 1 by 5 s. Dropping anchor 1 (the
        // user-verified foreground measurement) would "fix" the residuals but redefine the sync
        // near the viewing position — the fit must be rejected instead.
        val fit = SubtitleAutoSync.fitDriftLineRobust(
            listOf(
                anchor(0L, 0L),                 // anchor 1 (ground truth)
                anchor(300_000L, 8_000L),       // 5000 + 0.01·pos
                anchor(600_000L, 11_000L),
                anchor(900_000L, 14_000L)
            )
        )
        assertThat(fit).isNull()
    }

    @Test
    fun `fewer than two anchors cannot fit`() {
        assertThat(SubtitleAutoSync.fitDriftLineRobust(listOf(anchor(0L, 100L)))).isNull()
    }

    // ── transformTimestamps (offset + rate baking) ──────────────────────────────

    @Test
    fun `plain shift preserves format and shifts both ends`() {
        val srt = "1\n00:00:10,000 --> 00:00:12,500\nHello\n"
        val shifted = SubtitleSyncMatcher.shiftTimestamps(srt, 1500L)
        assertThat(shifted).contains("00:00:11,500 --> 00:00:14,000")
    }

    @Test
    fun `rate transform stretches later cues more`() {
        // delay(pos) = 1000 + 0.04·pos → t' = (t + 1000) / 0.96
        val srt = "1\n00:00:10,000 --> 00:00:12,000\nA\n\n2\n00:10:00,000 --> 00:10:02,000\nB\n"
        val out = SubtitleSyncMatcher.transformTimestamps(srt, 1000L, 0.04)
        // (10000 + 1000) / 0.96 = 11458.33 → 00:00:11,458
        assertThat(out).contains("00:00:11,458")
        // (600000 + 1000) / 0.96 = 626041.67 → 00:10:26,042
        assertThat(out).contains("00:10:26,042")
    }

    @Test
    fun `zero transform returns input unchanged`() {
        val srt = "1\n00:00:10,000 --> 00:00:12,000\nA\n"
        assertThat(SubtitleSyncMatcher.transformTimestamps(srt, 0L, 0.0)).isSameInstanceAs(srt)
    }
}
