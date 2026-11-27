package com.arflix.tv.music

import android.app.Application
import androidx.media3.common.PlaybackException
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.ConscryptMode
import java.io.IOException

/** The player's errors as the app's controller receives them (PlaybackException needs Android). */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [33], application = Application::class)
@ConscryptMode(ConscryptMode.Mode.OFF)
class MusicPlaybackErrorTest {

    @Test fun theProblemSurvivesTheTripToTheAppsController() {
        val problem = MusicPlaybackProblem(MusicPlaybackProblem.Kind.NOT_IN_SOURCES, detail = "Song: Part 2")
        val error = PlaybackException("Source error", MusicStreamException(problem, IOException("inner")), PlaybackException.ERROR_CODE_IO_UNSPECIFIED)
        assertEquals(problem, MusicPlaybackProblem.of(error))
        // A controller gets the error through a Bundle, which keeps only the cause's message.
        assertEquals(problem, MusicPlaybackProblem.of(PlaybackException.fromBundle(error.toBundle())))
    }

    @Test fun playerErrorsWithoutAProblemFallBackOnTheirCode() {
        fun of(code: Int) = MusicPlaybackProblem.of(PlaybackException("x", null, code)).kind
        assertEquals(MusicPlaybackProblem.Kind.STREAM_ERROR, of(PlaybackException.ERROR_CODE_IO_BAD_HTTP_STATUS))
        assertEquals(MusicPlaybackProblem.Kind.NO_CONNECTION, of(PlaybackException.ERROR_CODE_IO_NETWORK_CONNECTION_FAILED))
        assertEquals(MusicPlaybackProblem.Kind.FORMAT, of(PlaybackException.ERROR_CODE_DECODING_FAILED))
        assertEquals(MusicPlaybackProblem.Kind.FORMAT, of(PlaybackException.ERROR_CODE_PARSING_CONTAINER_UNSUPPORTED))
        assertEquals(MusicPlaybackProblem.Kind.OTHER, of(PlaybackException.ERROR_CODE_UNSPECIFIED))
    }
}
