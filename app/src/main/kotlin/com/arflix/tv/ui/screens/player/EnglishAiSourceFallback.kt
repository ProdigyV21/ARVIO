package com.arflix.tv.ui.screens.player

import com.arflix.tv.data.model.Subtitle
import com.arflix.tv.ui.screens.player.audiosync.SubtitleSyncModel
import kotlin.math.abs

/**
 * AI translation for a file with no text track of its own.
 *
 * [SubtitleSelectionPolicy.aiSource] only accepts embedded text tracks, so a release with no
 * built-in subtitles (many WEB-DL / x265 releases) gets no AI translation at all, even when a
 * good English subtitle is available from an addon. This fallback runs only after the scan found
 * no usable target-language subtitle AND there is no embedded text source: English addon
 * subtitles are synced by the audio one at a time, and the first whose timing the audio confirms
 * and which then holds steady becomes the AI source, as a copy re-timed to that mapping.
 * Nothing confirmed and steady: nothing changes.
 */
internal object EnglishAiSourceFallback {

    /** How many English addon subtitles are tried, best first. */
    const val MAX_CANDIDATES = 6

    /**
     * The English addon subtitles to try, in order: best release-name match first, hearing-impaired
     * (SDH / [HI]) after the others at the same score; forced, bitmap and url-less ones never.
     * Empty when the target language is English itself.
     */
    fun candidates(
        subtitles: List<Subtitle>,
        targetLanguage: String,
        normalizeLanguage: (String) -> String,
        releaseScore: (Subtitle) -> Int,
    ): List<Subtitle> {
        if (normalizeLanguage(targetLanguage) == "en") return emptyList()
        return subtitles
            .filter {
                !it.isEmbedded && !it.isBitmap && !it.isForced && it.url.isNotBlank() &&
                    normalizeLanguage(it.lang) == "en" && !it.label.contains("forced", ignoreCase = true)
            }
            .sortedWith(
                compareByDescending<Subtitle> { releaseScore(it) }
                    .thenBy { if (isHearingImpaired(it)) 1 else 0 }
            )
            .take(MAX_CANDIDATES)
    }

    fun isHearingImpaired(subtitle: Subtitle): Boolean =
        subtitle.label.contains("SDH", ignoreCase = true) || subtitle.label.contains("[HI]", ignoreCase = true)
}

/**
 * The audio's first confirmation is not enough to hand a subtitle to the AI: one cut for another
 * edit can lock on a single scene and drift later. A confirmed mapping is accepted only once it
 * still gives the same delay at the playhead [verifyMs] of playback later (within [steadyMs]; a
 * rate correction that agrees at the playhead is not a move, a new offset is). A seek back
 * restarts the wait from there.
 */
internal class SteadyConfirmation(
    private val verifyMs: Long = VERIFY_MS,
    private val steadyMs: Long = STEADY_MS,
) {
    enum class Verdict { Waiting, Holds, Moved }

    private var first: SubtitleSyncModel? = null
    private var firstAtMs = -1L

    /** [confirmed] is the audio's confirmed mapping right now (null while unconfirmed). */
    fun observe(confirmed: SubtitleSyncModel?, positionMs: Long): Verdict {
        confirmed ?: return Verdict.Waiting
        val held = first
        if (held == null || positionMs < firstAtMs) {
            first = confirmed
            firstAtMs = positionMs
            return Verdict.Waiting
        }
        val movedMs = abs(delayMsAt(confirmed, positionMs) - delayMsAt(held, positionMs))
        if (movedMs > steadyMs) return Verdict.Moved
        return if (positionMs - firstAtMs >= verifyMs) Verdict.Holds else Verdict.Waiting
    }

    private fun delayMsAt(model: SubtitleSyncModel, positionMs: Long): Long =
        model.delayUsAt(positionMs * 1_000L) / 1_000L

    companion object {
        const val VERIFY_MS = 20_000L
        const val STEADY_MS = 400L
    }
}
