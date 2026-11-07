@file:androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)

package com.arflix.tv.ui.screens.tv.live.aisubs

import androidx.media3.extractor.text.CueDecoder

/**
 * Reads subtitle streams the player already parsed during extraction (WebVTT and other text
 * renditions arrive as Media3's own cue samples, `application/x-media3-cues`) into sentences on
 * the media timeline. Live caption streams usually roll up: each cue repeats the previous line
 * and adds one, so lines seen in the last few cues are dropped and only new ones are emitted.
 *
 * Not thread-safe: one instance per channel source, fed from the loading thread.
 */
internal class CaptionCueReader(private val onSentence: (CaptionSentence) -> Unit) {
    private val decoder = CueDecoder()
    private val recentLines = ArrayDeque<String>()

    fun feed(timeUs: Long, data: ByteArray, offset: Int, size: Int) {
        val cues = runCatching { decoder.decode(timeUs, data, offset, size) }.getOrNull() ?: return
        val lines = cues.cues.flatMap { cue -> cue.text?.toString().orEmpty().split('\n') }
            .map { it.replace(WHITESPACE, " ").trim() }
            .filter { it.isNotEmpty() }
        val fresh = lines.filter { it !in recentLines }
        lines.forEach { line ->
            if (line !in recentLines) recentLines.addLast(line)
            while (recentLines.size > RECENT_LINES) recentLines.removeFirst()
        }
        if (fresh.isEmpty()) return
        val endUs = if (cues.endTimeUs > cues.startTimeUs) cues.endTimeUs else cues.startTimeUs + DEFAULT_DURATION_US
        onSentence(CaptionSentence(cues.startTimeUs, endUs, fresh.joinToString(" ")))
    }

    fun reset() {
        recentLines.clear()
    }

    private companion object {
        const val RECENT_LINES = 6
        const val DEFAULT_DURATION_US = 3_000_000L
        val WHITESPACE = Regex("\\s+")
    }
}
