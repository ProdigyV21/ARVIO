package com.arflix.tv.ui.screens.tv.live

import org.junit.Assert.assertEquals
import org.junit.Assert.assertSame
import org.junit.Test

class LiveAiSubtitlesOptionTest {
    private val streamOptions = listOf(
        LiveTrackOption(LIVE_SUBTITLES_OFF, "Off", selected = false),
        LiveTrackOption("en", "English", selected = true),
    )

    @Test fun featureOffLeavesTheStreamChoicesAlone() {
        val options = withAiSubtitlesOption(streamOptions, "Off", "AI subtitles", aiAvailable = false, aiSelected = false)
        assertSame(streamOptions, options)
    }

    @Test fun aiEntryComesAfterTheStreamTracks() {
        val options = withAiSubtitlesOption(streamOptions, "Off", "AI subtitles", aiAvailable = true, aiSelected = false)
        assertEquals(listOf(LIVE_SUBTITLES_OFF, "en", LIVE_SUBTITLES_AI), options.map { it.key })
        assertEquals(listOf(false, true, false), options.map { it.selected })
    }

    @Test fun selectedAiEntryIsTheOnlySelection() {
        val options = withAiSubtitlesOption(streamOptions, "Off", "AI subtitles", aiAvailable = true, aiSelected = true)
        assertEquals(listOf(false, false, true), options.map { it.selected })
    }

    @Test fun channelWithoutSubtitleTracksGetsOffAndAi() {
        val options = withAiSubtitlesOption(emptyList(), "Off", "AI subtitles", aiAvailable = true, aiSelected = false)
        assertEquals(listOf(LIVE_SUBTITLES_OFF, LIVE_SUBTITLES_AI), options.map { it.key })
        assertEquals(listOf(true, false), options.map { it.selected })
    }
}
