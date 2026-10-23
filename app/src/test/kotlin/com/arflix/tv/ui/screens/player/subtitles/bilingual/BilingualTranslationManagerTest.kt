package com.arflix.tv.ui.screens.player.subtitles.bilingual

import com.arflix.tv.ui.screens.player.subtitles.SubtitleTranslationManager
import com.arflix.tv.ui.screens.player.subtitles.SubtitleTranslationService
import com.arflix.tv.ui.screens.player.subtitles.TranslationResult
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

/** The bilingual path reuses ARVIO's own queue + cache; these pin the behaviour it relies on. */
class BilingualTranslationManagerTest {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    @After fun tearDown() = scope.cancel()

    private fun manager(service: SubtitleTranslationService) =
        SubtitleTranslationManager(service, "Simplified Chinese", scope).apply { isEnabled = true }

    @Test fun `concurrent requests for the same line hit the API once`() = runBlocking {
        val service = mockk<SubtitleTranslationService>()
        coEvery { service.translateBatch(any(), any(), any(), any()) } coAnswers {
            delay(100)
            TranslationResult(lines = firstArg<List<String>>().map { "译:$it" }, success = true)
        }
        val m = manager(service)
        val results = withTimeout(5_000) {
            (1..5).map { async { m.translateDetailed("Hello.") } }.awaitAll()
        }
        assertTrue(results.all { it == ("译:Hello." to true) })
        coVerify(exactly = 1) { service.translateBatch(any(), any(), any(), any()) }
        assertEquals("译:Hello.", m.getCached("Hello."))
        // Cached now: no further call.
        m.translateDetailed("Hello.")
        coVerify(exactly = 1) { service.translateBatch(any(), any(), any(), any()) }
    }

    @Test fun `api failure reports failed and does not cache`() = runBlocking {
        val service = mockk<SubtitleTranslationService>()
        coEvery { service.translateBatch(any(), any(), any(), any()) } returns
            TranslationResult(lines = emptyList(), success = false, errorMessage = "RATE_LIMITED")
        val m = manager(service)
        val (text, ok) = withTimeout(5_000) { m.translateDetailed("Run!") }
        assertFalse(ok)
        assertEquals("Run!", text)
        assertNull(m.getCached("Run!"))
    }

    @Test fun `original-only mode never pre-translates`() = runBlocking {
        val service = mockk<SubtitleTranslationService>(relaxed = true)
        val m = manager(service)
        m.displayMode = BilingualDisplayMode.ORIGINAL_ONLY
        assertFalse(m.translationNeeded)
        m.preTranslateWindow(listOf("a", "b"))
        m.displayMode = BilingualDisplayMode.OFF
        m.preTranslateWindow(listOf("a", "b"))
        coVerify(exactly = 0) { service.translateBatch(any(), any(), any(), any()) }
    }

    @Test fun `turning translation off or resetting clears the on-screen frame`() {
        val m = manager(mockk(relaxed = true))
        m.bilingualBus.showCue("x", "x", null)
        m.isEnabled = false
        assertNull(m.bilingualBus.frame.value)
        m.isEnabled = true
        m.bilingualBus.showCue("y", "y", null)
        m.reset()
        assertNull(m.bilingualBus.frame.value)
    }

    @Test fun `look-ahead sends earlier lines as context and a reset keeps the cache`() = runBlocking {
        val service = mockk<SubtitleTranslationService>()
        val contexts = mutableListOf<List<Pair<String, String>>>()
        coEvery { service.translateBatch(any(), any(), any(), any()) } coAnswers {
            contexts += thirdArg<List<Pair<String, String>>>()
            TranslationResult(lines = firstArg<List<String>>().map { "译:$it" }, success = true)
        }
        val m = manager(service)
        m.contentTitle = "Show S01E01"
        m.preTranslateWindow(listOf("One.", "Two.", "Three."))
        m.preTranslateWindow(listOf("Four."))
        assertTrue(contexts[0].isEmpty())
        assertEquals(listOf("One." to "译:One.", "Two." to "译:Two.", "Three." to "译:Three."), contexts[1])
        coVerify { service.translateBatch(listOf("Four."), "Simplified Chinese", any(), "Show S01E01") }
        m.reset()
        // Still cached after a reset (new track / episode): never paid for twice.
        assertEquals("译:One.", m.getCached("One."))
        m.preTranslateWindow(listOf("One.", "Two."))
        coVerify(exactly = 2) { service.translateBatch(any(), any(), any(), any()) }
    }

    @Test fun `look-ahead batches are large so the fixed prompt is paid rarely`() = runBlocking {
        val service = mockk<SubtitleTranslationService>()
        coEvery { service.translateBatch(any(), any(), any(), any()) } coAnswers {
            TranslationResult(lines = firstArg<List<String>>().map { "译:$it" }, success = true)
        }
        val m = manager(service)
        m.preTranslateWindow((1..120).map { "Line $it." } + listOf("Line 1.", "Line 2."))
        // 120 distinct lines → 3 requests of ≤ 50, duplicates never sent.
        coVerify(exactly = 3) { service.translateBatch(any(), any(), any(), any()) }
    }
}
