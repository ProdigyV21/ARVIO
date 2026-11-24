package com.arflix.tv.ui.screens.player

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class LocalSubtitleFileNameTest {
    private val episodeOne = "1\n00:00:01,000 --> 00:00:02,000\nEpisode one line\n"
    private val episodeTwo = "1\n00:00:01,000 --> 00:00:02,000\nEpisode two line\n"

    @Test fun `the same provider and id with different text gets a different file`() {
        // A season-pack subtitle keeps one id across episodes while its text differs.
        val first = localSubtitleFileName("SubSource", "pack-1", episodeOne, "srt")
        val second = localSubtitleFileName("SubSource", "pack-1", episodeTwo, "srt")
        assertNotEquals(first, second)
    }

    @Test fun `the same subtitle text keeps one stable file name`() {
        assertEquals(
            localSubtitleFileName("SubSource", "pack-1", episodeOne, "srt"),
            localSubtitleFileName("SubSource", "pack-1", episodeOne, "srt")
        )
    }

    @Test fun `different subtitles with the same text do not share a name`() {
        assertNotEquals(
            localSubtitleFileName("OpenSubtitles", "1", episodeOne, "srt"),
            localSubtitleFileName("SubDL", "1", episodeOne, "srt")
        )
    }

    @Test fun `the extension is kept and the name is a plain file name`() {
        val name = localSubtitleFileName("SubDL", "a/b|c", "WEBVTT\n", "vtt")
        assertTrue(name.endsWith(".vtt"))
        assertTrue("no path separators", name.none { it == java.io.File.separatorChar || it == (47).toChar() })
    }
}
