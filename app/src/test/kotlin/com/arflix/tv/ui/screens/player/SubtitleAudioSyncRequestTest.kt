package com.arflix.tv.ui.screens.player

import com.arflix.tv.data.model.Subtitle
import com.arflix.tv.ui.screens.player.subtitles.RETIMED_SUBTITLE_ID_SUFFIX
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class SubtitleAudioSyncRequestTest {

    private val external = Subtitle(
        id = "opensub_1",
        url = "https://subs.example/1.srt",
        lang = "en",
        label = "English",
        provider = "opensubtitles",
        isEmbedded = false,
    )

    private val other = external.copy(
        id = "opensub_2",
        url = "https://subs.example/2.srt",
        label = "English 2",
    )

    private val request = SubtitleAudioSyncRequest(
        generation = 4L,
        streamUrl = "https://stream.example/movie.mkv",
        subtitleKey = SubtitleSelectionPolicy.key(external),
    )

    @Test
    fun currentWhileTheSameSubtitleIsStillSelected() {
        assertTrue(
            request.isCurrent(
                generation = 4L,
                streamUrl = "https://stream.example/movie.mkv",
                selectedSubtitle = external.copy(url = "file:///cache/1.srt"),
                lockedSubtitleKey = SubtitleSelectionPolicy.key(external),
                isAiTranslating = false,
                isLiveAudioTranslating = false,
            )
        )
        assertTrue(
            request.isCurrent(
                generation = 4L,
                streamUrl = "https://stream.example/movie.mkv",
                selectedSubtitle = external.copy(
                    id = external.id + RETIMED_SUBTITLE_ID_SUFFIX,
                    url = "file:///cache/1_retimed.srt",
                ),
                lockedSubtitleKey = SubtitleSelectionPolicy.key(external),
                isAiTranslating = false,
                isLiveAudioTranslating = false,
            )
        )
    }

    @Test
    fun currentWhenExternalSubtitleWasAutoSelectedWithoutUserLock() {
        assertTrue(
            request.isCurrent(
                generation = 4L,
                streamUrl = "https://stream.example/movie.mkv",
                selectedSubtitle = external,
                lockedSubtitleKey = null,
                isAiTranslating = false,
                isLiveAudioTranslating = false,
            )
        )
    }

    @Test
    fun staleAfterANewStreamStarts() {
        assertFalse(
            request.isCurrent(
                generation = 5L,
                streamUrl = "https://stream.example/movie.mkv",
                selectedSubtitle = external,
                lockedSubtitleKey = SubtitleSelectionPolicy.key(external),
                isAiTranslating = false,
                isLiveAudioTranslating = false,
            )
        )
        assertFalse(
            request.isCurrent(
                generation = 4L,
                streamUrl = "https://stream.example/other.mkv",
                selectedSubtitle = external,
                lockedSubtitleKey = SubtitleSelectionPolicy.key(external),
                isAiTranslating = false,
                isLiveAudioTranslating = false,
            )
        )
    }

    @Test
    fun staleAfterTheViewerPicksAnotherSubtitle() {
        assertFalse(
            request.isCurrent(
                generation = 4L,
                streamUrl = "https://stream.example/movie.mkv",
                selectedSubtitle = other,
                lockedSubtitleKey = SubtitleSelectionPolicy.key(other),
                isAiTranslating = false,
                isLiveAudioTranslating = false,
            )
        )
        assertFalse(
            request.isCurrent(
                generation = 4L,
                streamUrl = "https://stream.example/movie.mkv",
                selectedSubtitle = external.copy(isEmbedded = true),
                lockedSubtitleKey = null,
                isAiTranslating = false,
                isLiveAudioTranslating = false,
            )
        )
        assertFalse(
            request.isCurrent(
                generation = 4L,
                streamUrl = "https://stream.example/movie.mkv",
                selectedSubtitle = null,
                lockedSubtitleKey = null,
                isAiTranslating = false,
                isLiveAudioTranslating = false,
            )
        )
    }

    @Test
    fun staleWhenAiOrLiveTakesTheScreen() {
        assertFalse(
            request.isCurrent(
                generation = 4L,
                streamUrl = "https://stream.example/movie.mkv",
                selectedSubtitle = external,
                lockedSubtitleKey = SubtitleSelectionPolicy.key(external),
                isAiTranslating = true,
                isLiveAudioTranslating = false,
            )
        )
        assertFalse(
            request.isCurrent(
                generation = 4L,
                streamUrl = "https://stream.example/movie.mkv",
                selectedSubtitle = external,
                lockedSubtitleKey = SubtitleSelectionPolicy.key(external),
                isAiTranslating = false,
                isLiveAudioTranslating = true,
            )
        )
    }
}
