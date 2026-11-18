package com.arflix.tv.ui.screens.player.subtitles.bilingual

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * One on-screen AI subtitle moment: the source text of the cue(s) Media3 is currently showing,
 * plus its translation once known. Both halves always belong to the same cue, because the
 * translation is attached to the frame by [generation] — the timeline is the source cue's.
 */
data class BilingualFrame(
    /** Increments on every new cue / clear. A translation is only accepted for its own generation. */
    val generation: Long,
    /** Translation-cache key: the joined (optionally SDH-stripped) source text. */
    val sourceKey: String,
    /** Source text as shown (multiple simultaneous cues are joined with newlines). */
    val original: String,
    /** Translation, or null while pending / when unavailable. */
    val translation: String? = null,
    /** True when the translation request failed (timeout, rate limit, bad key...). */
    val translationFailed: Boolean = false,
)

/**
 * Bridge between the playback-side text output ([com.arflix.tv.ui.screens.player.engine.exoplayer.AiSubtitleRenderersFactory])
 * and the Compose overlay. Thread-safe; all mutations are serialised on this object.
 *
 * Staleness rule: [deliverTranslation] is ignored unless its generation is still the current
 * one, so a slow translation for an old line (after a seek, a new cue, a track/source/episode
 * switch, or [clear]) can never overwrite the subtitle currently on screen.
 */
class BilingualCueBus {
    private val _frame = MutableStateFlow<BilingualFrame?>(null)
    val frame: StateFlow<BilingualFrame?> = _frame.asStateFlow()

    private var generation = 0L
    private val lock = Any()

    /** Publishes a new cue. Returns its generation token for a later [deliverTranslation]. */
    fun showCue(sourceKey: String, original: String, cachedTranslation: String?): Long = synchronized(lock) {
        generation += 1
        _frame.value = BilingualFrame(
            generation = generation,
            sourceKey = sourceKey,
            original = original,
            translation = cachedTranslation,
        )
        generation
    }

    /**
     * Attaches a translation to the frame with [token]. Returns false (and changes nothing)
     * when the frame has moved on. A failed translation keeps the source text on screen.
     */
    fun deliverTranslation(token: Long, sourceKey: String, translation: String?, failed: Boolean): Boolean =
        synchronized(lock) {
            val current = _frame.value ?: return false
            if (current.generation != token || generation != token || current.sourceKey != sourceKey) return false
            _frame.value = current.copy(
                translation = if (failed) null else translation?.takeIf { it.isNotBlank() },
                translationFailed = failed,
            )
            true
        }

    /** Removes the subtitle (end of cue, seek, track switch, new media). Invalidates pending work. */
    fun clear() = synchronized(lock) {
        generation += 1
        _frame.value = null
    }

    /**
     * The SECOND language's source changed (generation mode, target language, secondary track):
     * keep the primary line on screen (no flicker), drop its old second-language text and
     * invalidate every pending result for it. Returns the new generation token.
     */
    fun resetSecondary(): Long = synchronized(lock) {
        generation += 1
        _frame.value = _frame.value?.copy(generation = generation, translation = null, translationFailed = false)
        generation
    }

    /** Current generation, for callers that want to check staleness themselves. */
    fun currentGeneration(): Long = synchronized(lock) { generation }
}

/** Which language a rendered block belongs to. */
enum class BilingualRole { ORIGINAL, TRANSLATION }

data class BilingualBlock(val role: BilingualRole, val text: String)

/**
 * Pure function deciding what to draw for a frame under the user's settings.
 * Returns the blocks top-to-bottom.
 */
object BilingualComposer {

    fun compose(frame: BilingualFrame?, settings: BilingualSubtitleSettings): List<BilingualBlock> {
        if (frame == null) return emptyList()
        val original = frame.original.trim()
        val translation = frame.translation?.trim()?.takeIf { it.isNotEmpty() }
        return when (settings.mode) {
            BilingualDisplayMode.OFF -> emptyList()
            BilingualDisplayMode.ORIGINAL_ONLY ->
                if (original.isNotEmpty()) listOf(BilingualBlock(BilingualRole.ORIGINAL, original)) else emptyList()
            BilingualDisplayMode.TRANSLATION_ONLY -> when {
                translation != null -> listOf(BilingualBlock(BilingualRole.TRANSLATION, translation))
                // Pending or failed: never leave the screen blank — show the source line.
                original.isNotEmpty() -> listOf(BilingualBlock(BilingualRole.ORIGINAL, original))
                else -> emptyList()
            }
            BilingualDisplayMode.BILINGUAL -> {
                val blocks = mutableListOf<BilingualBlock>()
                val showOriginal = original.isNotEmpty() &&
                    (settings.original.visible || translation == null || !settings.translation.visible)
                val showTranslation = translation != null && settings.translation.visible &&
                    !(showOriginal && sameText(original, translation))
                if (showOriginal) blocks += BilingualBlock(BilingualRole.ORIGINAL, original)
                if (showTranslation) blocks += BilingualBlock(BilingualRole.TRANSLATION, translation!!)
                if (settings.order == BilingualOrder.TRANSLATION_FIRST) blocks.reverse()
                blocks
            }
        }
    }

    private fun sameText(a: String, b: String): Boolean =
        a.replace(Regex("\\s+"), " ").equals(b.replace(Regex("\\s+"), " "), ignoreCase = false)
}
