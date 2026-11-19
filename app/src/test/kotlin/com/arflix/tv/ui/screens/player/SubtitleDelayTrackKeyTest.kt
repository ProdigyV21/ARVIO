package com.arflix.tv.ui.screens.player

import com.arflix.tv.data.model.Subtitle
import com.arflix.tv.ui.screens.player.subtitles.RETIMED_SUBTITLE_ID_SUFFIX
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNull
import org.junit.Test

class SubtitleDelayTrackKeyTest {
    private fun addon(id: String, provider: String = "OpenSubtitles") =
        Subtitle(id = id, url = "https://example.invalid/$id.srt", lang = "en", label = "English", provider = provider)

    private fun embedded(id: String) =
        Subtitle(id = id, url = "", lang = "en", label = "English", isEmbedded = true)

    @Test fun noSubtitleHasNoKey() {
        assertNull(subtitleDelayTrackKey(null))
    }

    @Test fun retimedCopyKeepsTheKeyOfItsSourceTrack() {
        assertEquals(
            subtitleDelayTrackKey(addon("123")),
            subtitleDelayTrackKey(addon("123$RETIMED_SUBTITLE_ID_SUFFIX"))
        )
    }

    @Test fun differentTracksHaveDifferentKeys() {
        assertNotEquals(subtitleDelayTrackKey(addon("123")), subtitleDelayTrackKey(addon("456")))
        assertNotEquals(subtitleDelayTrackKey(addon("123")), subtitleDelayTrackKey(embedded("123")))
    }

    @Test fun sameIdFromAnotherProviderIsAnotherTrack() {
        assertNotEquals(
            subtitleDelayTrackKey(addon("123", provider = "A")),
            subtitleDelayTrackKey(addon("123", provider = "B"))
        )
    }
}
