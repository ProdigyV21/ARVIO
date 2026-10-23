package com.arflix.tv.ui.screens.player.subtitles.bilingual

import com.arflix.tv.ui.screens.player.subtitles.SubtitleTranslationManager
import com.arflix.tv.ui.screens.player.subtitles.SubtitleTranslationService
import com.arflix.tv.ui.screens.player.subtitles.TranslationResult
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Acceptance flows of the two smart paths through the real manager, cue bus and publisher —
 * everything below the Media3 renderer — with a mocked Gemini/Groq service that counts calls.
 */
class SmartBilingualFlowTest {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    @After fun tearDown() = scope.cancel()

    private fun countingService(prefix: (String) -> String = { lang -> "[$lang]" }): SubtitleTranslationService {
        val service = mockk<SubtitleTranslationService>()
        coEvery { service.translateBatch(any(), any(), any(), any()) } coAnswers {
            val lang = secondArg<String>()
            TranslationResult(lines = firstArg<List<String>>().map { "${prefix(lang)}$it" }, success = true)
        }
        return service
    }

    /** A file with an English and a Simplified Chinese embedded SRT, as the tap would store it. */
    private fun storeWithEnglishAndChinese(chineseOffsetMs: Long = 0L): EmbeddedSubtitleStore {
        val store = EmbeddedSubtitleStore()
        store.beginSource("movie.mkv")
        store.onTrack("movie.mkv", EmbeddedTextTrack(3, "3", "eng", "English", "application/x-subrip", null))
        store.onTrack("movie.mkv", EmbeddedTextTrack(4, "4", "chi", "简体", "application/x-subrip", null))
        DialogueFixture.english().forEach { store.onCue("movie.mkv", 3, it.startMs, it.endMs, it.text) }
        DialogueFixture.chinese(chineseOffsetMs).forEach { store.onCue("movie.mkv", 4, it.startMs, it.endMs, it.text) }
        return store
    }

    private fun spanOf(i: Int) = DialogueFixture.spans[i]

    @Test fun `MKV with English and Simplified Chinese SRT - merged, zero API calls`() = runBlocking {
        val service = countingService()
        val manager = SubtitleTranslationManager(service, "Simplified Chinese", scope)
        val store = storeWithEnglishAndChinese()
        manager.bilingualSource = SubtitleTranslationManager.BilingualSource.EMBEDDED_TRACK
        manager.secondarySource = DualTrackSecondarySource(store, primaryTrackId = 3, secondaryTrackId = 4)
        manager.isEnabled = true
        val publisher = BilingualCuePublisher(manager, scope)

        for (i in listOf(1, 3, 10, 11)) {
            publisher.publish("EN line $i", spanOf(i).first * 1000)
            val frame = manager.bilingualBus.frame.value!!
            assertEquals("EN line $i", frame.original)
            val expected = when (i) { 3 -> "中文 3 上\n中文 3 下"; 10, 11 -> "中文 10+11"; else -> "中文 $i" }
            assertEquals(expected, frame.translation)
        }
        // Pre-translation is a no-op on this path too.
        manager.preTranslateWindow(listOf("EN line 20", "EN line 21"))
        delay(300)
        coVerify(exactly = 0) { service.translateBatch(any(), any(), any(), any()) }
        assertTrue(!manager.translationNeeded)
    }

    @Test fun `offset Chinese track is aligned, not paired by index`() {
        val manager = SubtitleTranslationManager(countingService(), "Simplified Chinese", scope)
        manager.bilingualSource = SubtitleTranslationManager.BilingualSource.EMBEDDED_TRACK
        manager.secondarySource = DualTrackSecondarySource(storeWithEnglishAndChinese(chineseOffsetMs = 1_500), 3, 4)
        manager.isEnabled = true
        val publisher = BilingualCuePublisher(manager, scope)
        publisher.publish("EN line 2", spanOf(2).first * 1000)
        assertEquals("中文 2", manager.bilingualBus.frame.value!!.translation)
        publisher.publish("EN line 7", spanOf(7).first * 1000)
        assertEquals("中文 7", manager.bilingualBus.frame.value!!.translation)
    }

    @Test fun `unalignable Chinese track yields no wrong pairing and reports UNRELIABLE`() {
        val store = EmbeddedSubtitleStore()
        store.beginSource("m")
        store.onTrack("m", EmbeddedTextTrack(3, "3", "eng", null, "application/x-subrip", null))
        store.onTrack("m", EmbeddedTextTrack(4, "4", "chi", null, "application/x-subrip", null))
        DialogueFixture.english().forEach { store.onCue("m", 3, it.startMs, it.endMs, it.text) }
        (0 until 40).forEach { i ->
            val s = 4_000L + i * 2_350L + (i * 7919 % 1_000)
            store.onCue("m", 4, s, s + 1_000 + (i * 31 % 700), "其他 $i")
        }
        val manager = SubtitleTranslationManager(countingService(), "Simplified Chinese", scope)
        manager.bilingualSource = SubtitleTranslationManager.BilingualSource.EMBEDDED_TRACK
        manager.secondarySource = DualTrackSecondarySource(store, 3, 4)
        manager.isEnabled = true
        var lastStatus: DualTrackSecondarySource.Status? = null
        manager.onSecondaryLookup = { lastStatus = it.status }
        BilingualCuePublisher(manager, scope).publish("EN line 5", spanOf(5).first * 1000)
        val frame = manager.bilingualBus.frame.value!!
        assertEquals("EN line 5", frame.original) // primary never disappears
        assertNull(frame.translation)
        assertEquals(DualTrackSecondarySource.Status.UNRELIABLE, lastStatus)
        // ...and the planner's fallback for that verdict is AI (or primary-only without a key).
        val en = SubtitleCandidate("3", "eng", "English", true, false, false, 3)
        val zh = SubtitleCandidate("4", "chi", "简体", true, false, false, 4)
        val plan = SmartBilingualPlanner.plan(
            SmartBilingualPlanner.Input(listOf(en, zh), "auto", SubtitleLanguage.SIMPLIFIED_CHINESE, "en", null, false, true, setOf("4"))
        )
        assertTrue(plan is SmartPlan.AiTranslate)
    }

    @Test fun `MKV with only English - Gemini is called and both languages shown`() = runBlocking {
        val service = countingService()
        val manager = SubtitleTranslationManager(service, "Simplified Chinese", scope)
        manager.bilingualSource = SubtitleTranslationManager.BilingualSource.AI_TRANSLATION
        manager.isEnabled = true
        val publisher = BilingualCuePublisher(manager, scope)
        publisher.publish("Hello there.", 1_000_000)
        assertEquals("Hello there.", manager.bilingualBus.frame.value!!.original) // shown at once
        withTimeout(5_000) {
            while (manager.bilingualBus.frame.value?.translation == null) delay(20)
        }
        assertEquals("[Simplified Chinese]Hello there.", manager.bilingualBus.frame.value!!.translation)
        coVerify(exactly = 1) { service.translateBatch(listOf("Hello there."), "Simplified Chinese", any(), any()) }
    }

    @Test fun `translation failure keeps the original on screen`() = runBlocking {
        val service = mockk<SubtitleTranslationService>()
        coEvery { service.translateBatch(any(), any(), any(), any()) } returns TranslationResult(emptyList(), false, "RATE_LIMITED")
        val manager = SubtitleTranslationManager(service, "Simplified Chinese", scope).apply { isEnabled = true }
        BilingualCuePublisher(manager, scope).publish("Run!", 0)
        withTimeout(5_000) { while (manager.bilingualBus.frame.value?.translationFailed != true) delay(20) }
        val f = manager.bilingualBus.frame.value!!
        assertEquals("Run!", f.original)
        assertNull(f.translation)
    }

    @Test fun `changing the target language isolates cache and drops in-flight results`() = runBlocking {
        val gate = CompletableDeferred<Unit>()
        val service = mockk<SubtitleTranslationService>()
        coEvery { service.translateBatch(any(), any(), any(), any()) } coAnswers {
            val lang = secondArg<String>()
            if (lang == "Simplified Chinese") gate.await()
            TranslationResult(firstArg<List<String>>().map { "[$lang]$it" }, true)
        }
        val manager = SubtitleTranslationManager(service, "Simplified Chinese", scope).apply { isEnabled = true }
        val publisher = BilingualCuePublisher(manager, scope)
        publisher.publish("Good night.", 0)
        delay(300) // request for Simplified Chinese is now in flight
        manager.targetLanguage = "Japanese"            // user switches the secondary language
        // No flicker: the primary line stays, its old-language translation slot is invalidated.
        assertEquals("Good night.", manager.bilingualBus.frame.value!!.original)
        assertNull(manager.bilingualBus.frame.value!!.translation)
        publisher.publish("Good night.", 0)
        gate.complete(Unit)                            // the old-language answer arrives late
        withTimeout(5_000) { while (manager.bilingualBus.frame.value?.translation == null) delay(20) }
        assertEquals("[Japanese]Good night.", manager.bilingualBus.frame.value!!.translation)
        delay(300)
        assertEquals("[Japanese]Good night.", manager.bilingualBus.frame.value!!.translation)
        // Each language has its own cache entry.
        assertEquals("[Japanese]Good night.", manager.getCached("Good night."))
        manager.targetLanguage = "Simplified Chinese"
        assertEquals("[Simplified Chinese]Good night.", manager.getCached("Good night."))
    }

    @Test fun `switching source or episode mid-translation never shows the old line`() = runBlocking {
        val gate = CompletableDeferred<Unit>()
        val service = mockk<SubtitleTranslationService>()
        coEvery { service.translateBatch(any(), any(), any(), any()) } coAnswers {
            gate.await(); TranslationResult(firstArg<List<String>>().map { "译:$it" }, true)
        }
        val manager = SubtitleTranslationManager(service, "Simplified Chinese", scope).apply { isEnabled = true }
        val publisher = BilingualCuePublisher(manager, scope)
        publisher.publish("Previous episode line", 0)
        delay(200)
        manager.reset() // new source / episode (what the view model does on load)
        // Smart mode switches this video to path A.
        manager.bilingualSource = SubtitleTranslationManager.BilingualSource.EMBEDDED_TRACK
        manager.secondarySource = DualTrackSecondarySource(storeWithEnglishAndChinese(), 3, 4)
        publisher.publish("EN line 1", spanOf(1).first * 1000)
        gate.complete(Unit)
        delay(300)
        val f = manager.bilingualBus.frame.value!!
        assertEquals("EN line 1", f.original)
        assertEquals("中文 1", f.translation)
    }

    @Test fun `swapping the order only changes layout - no new translation request`() = runBlocking {
        val service = countingService()
        val manager = SubtitleTranslationManager(service, "Simplified Chinese", scope).apply { isEnabled = true }
        BilingualCuePublisher(manager, scope).publish("Hello.", 0)
        withTimeout(5_000) { while (manager.bilingualBus.frame.value?.translation == null) delay(20) }
        val frame = manager.bilingualBus.frame.value
        val enFirst = BilingualComposer.compose(frame, BilingualSubtitleSettings(order = BilingualOrder.ORIGINAL_FIRST))
        val zhFirst = BilingualComposer.compose(frame, BilingualSubtitleSettings(order = BilingualOrder.TRANSLATION_FIRST))
        assertEquals(enFirst.reversed(), zhFirst)
        assertEquals(BilingualRole.TRANSLATION, zhFirst.first().role)
        delay(200)
        coVerify(exactly = 1) { service.translateBatch(any(), any(), any(), any()) }
    }

    @Test fun `primary-only and secondary-only modes follow the configured roles`() {
        val frame = BilingualFrame(1, "k", original = "こんにちは", translation = "你好")
        val primaryOnly = BilingualComposer.compose(frame, BilingualSubtitleSettings(mode = BilingualDisplayMode.ORIGINAL_ONLY))
        assertEquals(listOf(BilingualBlock(BilingualRole.ORIGINAL, "こんにちは")), primaryOnly)
        val secondaryOnly = BilingualComposer.compose(frame, BilingualSubtitleSettings(mode = BilingualDisplayMode.TRANSLATION_ONLY))
        assertEquals(listOf(BilingualBlock(BilingualRole.TRANSLATION, "你好")), secondaryOnly)
    }

    @Test fun `bilingual layer off means ARVIO's normal subtitle path - no API calls`() = runBlocking {
        val service = countingService()
        val manager = SubtitleTranslationManager(service, "Simplified Chinese", scope) // isEnabled = false
        manager.preTranslateWindow(listOf("a", "b"))
        delay(200)
        coVerify(exactly = 0) { service.translateBatch(any(), any(), any(), any()) }
        assertTrue(!manager.translationNeeded)
    }

    @Test fun `switching generation path keeps the primary line on screen and drops stale results`() = runBlocking {
        val gate = CompletableDeferred<Unit>()
        val service = mockk<SubtitleTranslationService>()
        coEvery { service.translateBatch(any(), any(), any(), any()) } coAnswers {
            gate.await(); TranslationResult(firstArg<List<String>>().map { "译:$it" }, true)
        }
        val manager = SubtitleTranslationManager(service, "Simplified Chinese", scope).apply { isEnabled = true }
        val publisher = BilingualCuePublisher(manager, scope)
        publisher.publish("EN line 1", spanOf(1).first * 1000)       // Auto → AI (path B) in flight
        delay(200)
        // Auto re-plans to path A (the Chinese track became readable) — or the user picks Merge.
        manager.bilingualSource = SubtitleTranslationManager.BilingualSource.EMBEDDED_TRACK
        manager.secondarySource = DualTrackSecondarySource(storeWithEnglishAndChinese(), 3, 4)
        val mid = manager.bilingualBus.frame.value!!
        assertEquals("EN line 1", mid.original)                    // no blank frame
        gate.complete(Unit)
        delay(300)
        assertNull(manager.bilingualBus.frame.value!!.translation)  // AI result for the old path dropped
        publisher.publish("EN line 2", spanOf(2).first * 1000)
        assertEquals("中文 2", manager.bilingualBus.frame.value!!.translation)
        coVerify(exactly = 1) { service.translateBatch(any(), any(), any(), any()) } // no second request
    }

    @Test fun `AI Translate mode ignores an existing Chinese track and translates`() = runBlocking {
        val service = countingService()
        val en = SubtitleCandidate("3", "eng", "English", true, false, false, 3)
        val zh = SubtitleCandidate("4", "chi", "简体", true, false, false, 4)
        val plan = SmartBilingualPlanner.plan(
            SmartBilingualPlanner.Input(listOf(en, zh), "en", SubtitleLanguage.SIMPLIFIED_CHINESE, "en", null, false, true,
                mode = BilingualGenerationMode.AI_TRANSLATE)
        ) as SmartPlan.AiTranslate
        val manager = SubtitleTranslationManager(service, plan.target.translationTargetName, scope).apply { isEnabled = true }
        BilingualCuePublisher(manager, scope).publish("EN line 1", 0)
        withTimeout(5_000) { while (manager.bilingualBus.frame.value?.translation == null) delay(20) }
        assertEquals("[Simplified Chinese]EN line 1", manager.bilingualBus.frame.value!!.translation)
        assertEquals("EN line 1", manager.bilingualBus.frame.value!!.original)
    }
}
