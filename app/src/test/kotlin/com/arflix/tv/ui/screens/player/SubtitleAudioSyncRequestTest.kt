package com.arflix.tv.ui.screens.player

import com.arflix.tv.data.model.Subtitle
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * The explicit "Sync to Audio" request owns exactly one subtitle on one stream. If the viewer
 * moves on (another stream, another pick, AI/Live taking the screen) the in-flight audio sync must
 * not keep applying its result.
 */
class SubtitleAudioSyncRequestTest {
    private val pick = Subtitle(
        id = "addon-sub-1",
        url = "https://example.test/he.srt",
        lang = "he",
        label = "Hebrew",
        provider = "Addon",
    )

    private fun request() = SubtitleAudioSyncRequest(
        generation = 7L,
        streamUrl = "https://cdn.test/movie.mkv",
        subtitleKey = SubtitleSelectionPolicy.key(pick),
    )

    @Test fun currentWhileTheSameSubtitleIsStillSelected() {
        assertTrue(
            request().isCurrent(
                generation = 7L,
                streamUrl = "https://cdn.test/movie.mkv",
                selectedSubtitle = pick,
                lockedSubtitleKey = SubtitleSelectionPolicy.key(pick),
                isAiTranslating = false,
                isLiveAudioTranslating = false,
            )
        )
    }

    @Test fun staleAfterANewStreamStarts() {
        assertFalse(
            request().isCurrent(
                generation = 7L,
                streamUrl = "https://cdn.test/other.mkv",
                selectedSubtitle = pick,
                lockedSubtitleKey = SubtitleSelectionPolicy.key(pick),
                isAiTranslating = false,
                isLiveAudioTranslating = false,
            )
        )
    }

    @Test fun staleAfterTheViewerPicksAnotherSubtitle() {
        assertFalse(
            request().isCurrent(
                generation = 7L,
                streamUrl = "https://cdn.test/movie.mkv",
                selectedSubtitle = pick,
                lockedSubtitleKey = "addon|someone-else",
                isAiTranslating = false,
                isLiveAudioTranslating = false,
            )
        )
    }

    @Test fun staleWhenAiOrLiveTakesTheScreen() {
        val r = request()
        assertFalse(
            r.isCurrent(
                generation = 7L,
                streamUrl = "https://cdn.test/movie.mkv",
                selectedSubtitle = pick,
                lockedSubtitleKey = SubtitleSelectionPolicy.key(pick),
                isAiTranslating = true,
                isLiveAudioTranslating = false,
            )
        )
        assertFalse(
            r.isCurrent(
                generation = 7L,
                streamUrl = "https://cdn.test/movie.mkv",
                selectedSubtitle = pick,
                lockedSubtitleKey = SubtitleSelectionPolicy.key(pick),
                isAiTranslating = false,
                isLiveAudioTranslating = true,
            )
        )
    }
}
