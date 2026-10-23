package com.arflix.tv.ui.screens.player.subtitles.bilingual

import com.arflix.tv.ui.screens.player.subtitles.SubtitleTranslationManager
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Publishes the primary cue on screen to the bilingual overlay and attaches its second language
 * from whichever source is active. Both paths end in the same [BilingualCueBus] frame and hence in
 * the same overlay, layout and styles:
 *
 *  - path A ([SubtitleTranslationManager.BilingualSource.EMBEDDED_TRACK]): the aligned line of the
 *    second embedded track — never a translation request;
 *  - path B ([SubtitleTranslationManager.BilingualSource.AI_TRANSLATION]): ARVIO's cached / queued
 *    Gemini-Groq translation, attached when it arrives if the cue is still showing.
 *
 * Every late attachment is guarded by the frame's generation token, so a seek, a new cue, a
 * language/track/source/episode switch (all of which clear the bus) drops stale results.
 */
class BilingualCuePublisher(
    private val manager: SubtitleTranslationManager,
    private val scope: CoroutineScope,
) {
    /** [text] is the cleaned primary text; [presentationTimeUs] its media time. */
    fun publish(text: String, presentationTimeUs: Long) {
        val bus = manager.bilingualBus
        if (manager.bilingualSource == SubtitleTranslationManager.BilingualSource.EMBEDDED_TRACK) {
            val source = manager.secondarySource
            val positionMs = presentationTimeUs / 1000
            source?.onPrimaryShown(text, positionMs)
            val lookup = source?.lookup(text, positionMs)
            val token = bus.showCue(sourceKey = text, original = text, cachedTranslation = lookup?.text)
            lookup?.let { l -> runCatching { manager.onSecondaryLookup?.invoke(l) } }
            if (source != null && lookup?.text == null &&
                lookup?.status != DualTrackSecondarySource.Status.UNRELIABLE) {
                // The second track may simply not be demuxed this far yet: look once more shortly.
                scope.launch {
                    delay(RETRY_LOOKUP_MS)
                    val again = source.lookup(text, positionMs)
                    again.text?.let { bus.deliverTranslation(token, text, it, failed = false) }
                }
            }
            return
        }

        val cached = manager.getCached(text)
        val token = bus.showCue(sourceKey = text, original = text, cachedTranslation = cached)
        if (cached != null || !manager.translationNeeded) return
        // Not cached yet: the primary line is already on screen; the translation is attached
        // when it arrives — only if this exact cue is still showing. translate() joins an
        // in-flight request for the same text, so this never sends a duplicate API request.
        scope.launch {
            val (translated, ok) = try {
                manager.translateDetailed(text)
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                text to false
            }
            bus.deliverTranslation(token, text, translated, failed = !ok)
        }
    }

    /** The primary cue left the screen (end of cue / SDH-only cue / no text). */
    fun clear(presentationTimeUs: Long) {
        manager.secondarySource?.onPrimaryCleared(presentationTimeUs / 1000)
        manager.bilingualBus.clear()
    }

    private companion object {
        const val RETRY_LOOKUP_MS = 600L
    }
}
