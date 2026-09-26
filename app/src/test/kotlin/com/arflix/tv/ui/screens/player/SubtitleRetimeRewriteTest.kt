package com.arflix.tv.ui.screens.player

import com.arflix.tv.ui.screens.player.subtitles.AutoSyncTimelineRetimer
import com.arflix.tv.ui.screens.player.subtitles.SubtitleSyncMatcher
import com.google.common.truth.Truth.assertThat
import org.junit.Test

/**
 * The served copy of a subtitle the retimer corrected cue by cue: its timings are rewritten in the
 * raw text, so what the player loads must line up with what the retimer decided.
 */
class SubtitleRetimeRewriteTest {

    private val srt = listOf(
        "1",
        "00:00:01,000 --> 00:00:02,500",
        "<i>First line</i>",
        "",
        "2",
        "00:00:10,000 --> 00:00:12,000 X1:10",
        "Second line",
        "",
    ).joinToString("\r\n")

    @Test
    fun `rewrites every cue through the map and keeps format, tags and cue settings`() {
        val out = SubtitleSyncMatcher.retimeTimestamps(srt) { start, end -> (start + 1_000L) to (end + 2_000L) }

        assertThat(out).contains("00:00:02,000 --> 00:00:04,500\r\n<i>First line</i>")
        assertThat(out).contains("00:00:11,000 --> 00:00:14,000 X1:10\r\nSecond line")
    }

    @Test
    fun `keys match what the parser reads, so retimer output maps straight back`() {
        val cues = SubtitleSyncMatcher.parseCues(srt)
        val moved = cues.associate { (it.startMs to it.endMs) to (it.startMs * 2 to it.endMs * 2) }

        val out = SubtitleSyncMatcher.retimeTimestamps(srt) { start, end -> moved.getValue(start to end) }

        assertThat(SubtitleSyncMatcher.parseCues(out).map { it.startMs to it.endMs })
            .containsExactly(2_000L to 5_000L, 20_000L to 24_000L).inOrder()
    }

    @Test
    fun `a 25 fps subtitle retimed onto a 23_976 reference lands on the reference`() {
        // Reference cues every ~8s over 40 minutes; the subtitle is the same timeline authored for
        // 25 fps playback (4.1% fast) — the PAL case the retimer's frame-rate ratios cover.
        val reference = (0 until 300).map { index ->
            val start = 5_000L + index * 7_919L + (index % 7) * 311L
            SubtitleSyncMatcher.TimedCue(start, start + 1_800L + (index % 3) * 400L, "r$index")
        }
        val ratio = 23.976 / 25.0
        fun ts(ms: Long) = "%02d:%02d:%02d,%03d".format(ms / 3_600_000, ms / 60_000 % 60, ms / 1_000 % 60, ms % 1_000)
        val raw = reference.joinToString("\r\n\r\n") { cue ->
            "1\r\n${ts((cue.startMs * ratio).toLong())} --> ${ts((cue.endMs * ratio).toLong())}\r\nline"
        }
        // Parsed exactly as the scan parses a downloaded subtitle, so the retimer's keys are the
        // parser's own millisecond values.
        val target = SubtitleSyncMatcher.parseCues(raw)

        val result = AutoSyncTimelineRetimer.retime(reference, target, 1.0, 0.0, discoverAlignment = true)!!

        assertThat(result.confident).isTrue()
        val moved = result.cues.associate {
            (it.originalStartTimeMs to it.originalEndTimeMs) to (it.startTimeMs to it.endTimeMs)
        }
        val rewritten = SubtitleSyncMatcher.parseCues(
            SubtitleSyncMatcher.retimeTimestamps(raw) { s, e -> moved.getValue(s to e) }
        )
        val worst = rewritten.zip(reference).maxOf { (got, want) -> kotlin.math.abs(got.startMs - want.startMs) }
        assertThat(worst).isAtMost(100L)
    }
}
