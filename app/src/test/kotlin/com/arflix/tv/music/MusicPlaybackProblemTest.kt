package com.arflix.tv.music

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class MusicPlaybackProblemTest {

    private fun kindOf(error: Throwable) = MusicPlaybackProblem.classify(error)

    @Test fun streamFailuresAreToldApart() {
        assertEquals(MusicPlaybackProblem(MusicPlaybackProblem.Kind.ADDON_TIMEOUT), kindOf(SocketTimeoutException("timeout")))
        assertEquals(
            MusicPlaybackProblem(MusicPlaybackProblem.Kind.ADDON_ERROR, 503),
            kindOf(IOException("wrapped", MusicServerException("HTTP 503"))),
        )
        assertEquals(MusicPlaybackProblem(MusicPlaybackProblem.Kind.ADDON_ERROR), kindOf(MusicServerException("No stream for this track")))
        assertEquals(MusicPlaybackProblem(MusicPlaybackProblem.Kind.NO_CONNECTION), kindOf(UnknownHostException("addon.example")))
        assertEquals(
            MusicPlaybackProblem(MusicPlaybackProblem.Kind.NOT_IN_SOURCES, detail = "Song: Part 2"),
            kindOf(MusicMatchMissingException("Song: Part 2")),
        )
    }

    @Test fun theMessageCarriesTheProblem() {
        val problem = MusicPlaybackProblem(MusicPlaybackProblem.Kind.NOT_IN_SOURCES, detail = "Song: Part 2")
        assertEquals(problem, MusicPlaybackProblem.decode(MusicStreamException(problem, null).message))
        assertEquals(
            MusicPlaybackProblem(MusicPlaybackProblem.Kind.ADDON_ERROR, 503),
            MusicPlaybackProblem.decode(MusicPlaybackProblem(MusicPlaybackProblem.Kind.ADDON_ERROR, 503).encode()),
        )
        assertNull(MusicPlaybackProblem.decode("Source error"))
        assertNull(MusicPlaybackProblem.decode("arvio-music:NOT_A_KIND::"))
    }
}
