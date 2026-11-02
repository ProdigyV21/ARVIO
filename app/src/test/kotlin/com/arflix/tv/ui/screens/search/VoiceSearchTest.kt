package com.arflix.tv.ui.screens.search

import android.app.Application
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.SpeechRecognizer
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import io.mockk.verify
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import org.robolectric.annotation.ConscryptMode

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28], manifest = Config.NONE, application = Application::class)
@ConscryptMode(ConscryptMode.Mode.OFF)
class VoiceSearchTest {
    private data class Session(val recognizer: SpeechRecognizer, val listener: RecognitionListener)
    private val sessions = mutableListOf<Session>()
    private val partials = mutableListOf<String>()
    private val results = mutableListOf<String>()
    private lateinit var voice: VoiceSearch

    @Before fun setUp() {
        mockkStatic(SpeechRecognizer::class)
        every { SpeechRecognizer.createSpeechRecognizer(any()) } answers {
            val recognizer = mockk<SpeechRecognizer>(relaxed = true)
            every { recognizer.setRecognitionListener(any()) } answers {
                sessions += Session(recognizer, firstArg())
            }
            recognizer
        }
        voice = VoiceSearch(RuntimeEnvironment.getApplication(), isAvailable = true).apply {
            onPartial = { partials += it }
            onResult = { results += it }
        }
    }

    @After fun tearDown() {
        try {
            if (::voice.isInitialized) voice.cancel()
        } finally {
            unmockkStatic(SpeechRecognizer::class)
        }
    }

    private fun text(value: String) = Bundle().apply {
        putStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION, arrayListOf(value))
    }

    private fun start(): Session {
        voice.start()
        assertTrue(voice.isListening)
        return sessions.last()
    }

    @Test fun activePartialResultsUpdateTheQueryWithoutStoppingListening() {
        val session = start()
        session.listener.onPartialResults(text("  The Matrix  "))
        assertEquals(listOf("The Matrix"), partials)
        assertTrue(results.isEmpty())
        assertTrue(voice.isListening)
        verify(exactly = 0) { session.recognizer.destroy() }
    }

    @Test fun activeFinalResultsSubmitOnceAndStopListening() {
        val session = start()
        session.listener.onResults(text("  The Matrix  "))
        assertEquals(listOf("The Matrix"), results)
        assertFalse(voice.isListening)
        verify(exactly = 1) { session.recognizer.destroy() }
    }

    @Test fun partialResultsAfterCancellationAreIgnored() {
        val session = start()
        voice.cancel()
        session.listener.onPartialResults(text("old partial"))
        assertTrue(partials.isEmpty())
        assertFalse(voice.isListening)
    }

    @Test fun finalResultsAfterCancellationCannotSubmitASearch() {
        val session = start()
        voice.cancel()
        session.listener.onResults(text("old result"))
        assertTrue(results.isEmpty())
        verify(exactly = 1) { session.recognizer.destroy() }
    }

    @Test fun duplicateFinalResultsDoNotSubmitTwice() {
        val session = start()
        session.listener.onResults(text("first"))
        session.listener.onResults(text("duplicate"))
        assertEquals(listOf("first"), results)
        verify(exactly = 1) { session.recognizer.destroy() }
    }

    @Test fun aSupersededSessionCannotOverwriteTheNewSessionsQuery() {
        val old = start()
        val current = start()
        old.listener.onPartialResults(text("old partial"))
        current.listener.onPartialResults(text("current partial"))
        assertEquals(listOf("current partial"), partials)
        assertTrue(voice.isListening)
    }

    @Test fun staleFinalResultsCannotDestroyTheReplacementRecognizer() {
        val old = start()
        val current = start()
        old.listener.onResults(text("old result"))
        assertTrue(results.isEmpty())
        assertTrue(voice.isListening)
        verify(exactly = 0) { current.recognizer.destroy() }
        current.listener.onResults(text("current result"))
        assertEquals(listOf("current result"), results)
        assertFalse(voice.isListening)
    }

    @Test fun staleEmptyResultsAreIgnoredWithoutStoppingTheCurrentSession() {
        val old = start()
        val current = start()
        old.listener.onResults(Bundle())
        old.listener.onPartialResults(null)
        assertTrue(voice.isListening)
        verify(exactly = 0) { current.recognizer.destroy() }
    }

    @Test fun oldErrorsCannotStopTheCurrentSession() {
        val old = start()
        val current = start()
        old.listener.onError(SpeechRecognizer.ERROR_NETWORK)
        assertTrue(voice.isListening)
        verify(exactly = 0) { current.recognizer.destroy() }
    }

    @Test fun cancellationInvalidatesEvenCallbacksDeliveredDuringDestroy() {
        val session = start()
        every { session.recognizer.destroy() } answers {
            session.listener.onPartialResults(text("late partial"))
            session.listener.onResults(text("late result"))
        }
        voice.cancel()
        assertTrue(partials.isEmpty())
        assertTrue(results.isEmpty())
        assertFalse(voice.isListening)
        verify(exactly = 1) { session.recognizer.destroy() }
    }

    @Test fun cancelIsIdempotent() {
        val session = start()
        voice.cancel()
        voice.cancel()
        assertFalse(voice.cancelIfListening())
        verify(exactly = 1) { session.recognizer.destroy() }
    }

    @Test fun pressingMicAgainStillStopsEarlyAndAcceptsTheFinalPhrase() {
        val session = start()
        voice.toggle()
        assertTrue(voice.isListening)
        verify(exactly = 1) { session.recognizer.stopListening() }
        session.listener.onResults(text("spoken phrase"))
        assertEquals(listOf("spoken phrase"), results)
    }

    @Test fun systemBackCancelsListeningBeforeNormalNavigation() {
        val session = start()
        var normalBacks = 0
        val dispatcher = OnBackPressedDispatcher()
        dispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (!voice.cancelIfListening()) normalBacks++
            }
        })
        dispatcher.onBackPressed()
        assertFalse(voice.isListening)
        assertEquals(0, normalBacks)
        session.listener.onPartialResults(text("cancelled partial"))
        session.listener.onResults(text("cancelled result"))
        assertTrue(partials.isEmpty())
        assertTrue(results.isEmpty())
        dispatcher.onBackPressed()
        assertEquals(1, normalBacks)
    }

    @Test fun backWithoutAnActiveSessionDoesNotConsumeNavigation() {
        assertFalse(voice.cancelIfListening())
        assertTrue(sessions.isEmpty())
    }
}
