package com.arflix.tv.ui.screens.player

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModelStore
import com.arflix.tv.data.model.Subtitle
import com.arflix.tv.data.repository.ProfileManager
import com.arflix.tv.ui.screens.player.subtitles.SubtitleSyncMatcher
import com.arflix.tv.util.settingsDataStore
import io.mockk.*
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.ConscryptMode
import java.io.File
import java.net.URI

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28], manifest = Config.NONE, application = android.app.Application::class)
@ConscryptMode(ConscryptMode.Mode.OFF)
class UserSubtitleSelectionTest {
    @get:Rule val temporary = TemporaryFolder()
    private val scheduler = TestCoroutineScheduler()
    private val dispatcher = StandardTestDispatcher(scheduler)
    private val store = ViewModelStore()
    private lateinit var model: PlayerViewModel
    private val english = Subtitle("en", "", "en", "English", "embedded", isEmbedded = true)
    private val remote = Subtitle("shared-id", "https://example.test/he.srt", "he", "Hebrew", "addon-a")
    private val raw = "1\n00:00:01,000 --> 00:00:03,000\nשלום\n"

    @Before fun setUp() {
        Dispatchers.setMain(dispatcher)
        mockkStatic(Log::class)
        every { Log.i(any(), any()) } returns 0
        every { Log.w(any(), any<String>()) } returns 0
        mockkStatic("com.arflix.tv.util.DataStoresKt")
        val preferences = MutableStateFlow(emptyPreferences())
        val memoryStore = object : DataStore<Preferences> {
            override val data = preferences
            override suspend fun updateData(transform: suspend (Preferences) -> Preferences): Preferences =
                transform(preferences.value).toPreferences().also { preferences.value = it }
        }
        every { any<Context>().settingsDataStore } returns memoryStore
        val context = mockk<Context>(relaxed = true)
        every { context.cacheDir } returns temporary.root
        val profiles = mockk<ProfileManager>(relaxed = true)
        every { profiles.profileStringKey(any()) } answers { stringPreferencesKey(firstArg()) }
        every { profiles.profileBooleanKey(any()) } answers { booleanPreferencesKey(firstArg()) }
        every { profiles.profileIntKey(any()) } answers { intPreferencesKey(firstArg()) }
        model = PlayerViewModel(
            context, profiles, mockk(relaxed = true), mockk(relaxed = true),
            mockk(relaxed = true), mockk(relaxed = true), mockk(relaxed = true),
            mockk(relaxed = true), mockk(relaxed = true), mockk(relaxed = true),
            mockk(relaxed = true), mockk(relaxed = true), mockk(relaxed = true),
            mockk(relaxed = true), mockk(relaxed = true), mockk(relaxed = true),
            mockk(relaxed = true)
        )
        store.put("player", model)
        state().value = PlayerUiState(subtitles = listOf(english, remote))
        field("aiSubtitleEnabled").setBoolean(model, true)
        field("targetSubtitleLangCode").set(model, "he")
        scheduler.runCurrent()
    }

    @After fun tearDown() {
        try {
            store.clear()
            scheduler.runCurrent()
        } finally {
            unmockkObject(SubtitleSyncMatcher)
            unmockkStatic("com.arflix.tv.util.DataStoresKt")
            unmockkStatic(Log::class)
            Dispatchers.resetMain()
        }
    }

    @Test fun automaticSameIdFromAnotherProviderIsRejectedWithoutSideEffects() = runTest(dispatcher) {
        model.selectSubtitle(english)
        runCurrent()
        val before = model.uiState.value
        val generation = field("subtitleLocalizeGeneration").getLong(model)
        model.selectSubtitle(english.copy(provider = "different-addon"), isUserAction = false)
        assertEquals(before, model.uiState.value)
        assertEquals(generation, field("subtitleLocalizeGeneration").getLong(model))
    }

    @Test fun explicitNewPickAndSameProvidersRetimedCopyAreAllowed() = runTest(dispatcher) {
        val local = remote.copy(url = "file:///cache/he.srt")
        model.selectSubtitle(local)
        val retimed = local.copy(id = local.id + "#retimed")
        model.selectSubtitle(retimed, isUserAction = false)
        assertEquals(retimed, model.uiState.value.selectedSubtitle)
        assertEquals(SubtitleSelectionPolicy.key(local), field("userLockedSubtitleKey").get(model))
        model.selectSubtitle(english)
        assertEquals(english, model.uiState.value.selectedSubtitle)
    }

    @Test fun unrelatedEmissionsAndLateTracksCannotBypassOrCancelManualDecode() = runTest(dispatcher) {
        for (old in listOf(english, null)) {
            state().value = PlayerUiState(subtitles = listOf(english, remote), selectedSubtitle = old, isAiTranslating = old != null)
            field("aiSourceSubtitle").set(model, old)
            model.translationManager.isEnabled = old != null
            val reply = holdDownload()
            model.selectSubtitle(remote)
            runCurrent()
            val pending = field("subtitleLocalizeJob").get(model) as Job
            val generation = field("subtitleLocalizeGeneration").getLong(model)
            val nonce = model.uiState.value.subtitleSelectionNonce
            state().value = model.uiState.value.copy(streamProgress = 0.5f)
            model.updatePlayerTextTracks(listOf(english.copy(id = "new-en"), english.copy(id = "he", lang = "he", label = "Hebrew")))
            runCurrent()
            assertTrue(pending.isActive)
            assertSame(pending, field("subtitleLocalizeJob").get(model))
            assertEquals(generation, field("subtitleLocalizeGeneration").getLong(model))
            assertEquals(old?.lang, model.uiState.value.selectedSubtitle?.lang)
            assertEquals(nonce, model.uiState.value.subtitleSelectionNonce)
            assertEquals(old != null, model.translationManager.isEnabled)
            reply.complete(raw)
            runCurrent()
            val landed = model.uiState.value.selectedSubtitle!!
            assertEquals(SubtitleSelectionPolicy.key(remote), SubtitleSelectionPolicy.key(landed))
            assertTrue(landed.url.startsWith("file:"))
            assertTrue(File(URI(landed.url)).readText().contains("שלום"))
            assertEquals(nonce + 1, model.uiState.value.subtitleSelectionNonce)
            assertFalse(model.uiState.value.isAiTranslating)
        }
    }

    @Test fun newPickOffOrExplicitAiWinsOverAnOldDownload() = runTest(dispatcher) {
        for (action in listOf<() -> Unit>(
            { model.selectSubtitle(english) }, { model.disableSubtitles() }, { model.activateAiTranslation() }
        )) {
            state().value = PlayerUiState(subtitles = listOf(english, remote), selectedSubtitle = english)
            val reply = holdDownload()
            model.selectSubtitle(remote)
            runCurrent()
            val pending = field("subtitleLocalizeJob").get(model) as Job
            action()
            val afterAction = model.uiState.value
            reply.complete(raw)
            runCurrent()
            assertTrue(pending.isCancelled)
            assertEquals(afterAction.selectedSubtitle, model.uiState.value.selectedSubtitle)
            assertEquals(afterAction.subtitleSelectionNonce, model.uiState.value.subtitleSelectionNonce)
        }
    }

    @Test fun manualDownloadTimesOutOnceAndLateReplyCannotReloadIt() = runTest(dispatcher) {
        val reply = holdDownload()
        model.selectSubtitle(remote)
        runCurrent()
        advanceTimeBy(5_999)
        runCurrent()
        assertNull(model.uiState.value.selectedSubtitle)
        advanceTimeBy(1)
        runCurrent()
        assertEquals(remote, model.uiState.value.selectedSubtitle)
        val nonce = model.uiState.value.subtitleSelectionNonce
        reply.complete(raw)
        runCurrent()
        assertEquals(nonce, model.uiState.value.subtitleSelectionNonce)
    }

    @Test fun automaticAiDoesNotReplaceALockButExplicitAiUsesEmbeddedEnglish() = runTest(dispatcher) {
        model.selectSubtitle(remote.copy(url = "file:///cache/he.srt"))
        val before = model.uiState.value
        model.activateAiTranslation(isUserAction = false)
        assertEquals(before, model.uiState.value)
        model.activateAiTranslation()
        assertNull(field("userLockedSubtitleKey").get(model))
        assertEquals(english, model.uiState.value.selectedSubtitle)
        assertTrue(model.uiState.value.isAiTranslating)
        assertTrue(model.translationManager.isEnabled)
        assertFalse(field("userPickedSubtitle").getBoolean(model))
    }

    @Test fun cachedExternalSourceCannotActivateAi() {
        state().value = PlayerUiState(subtitles = listOf(remote))
        field("aiSourceSubtitle").set(model, remote)
        model.activateAiTranslation()
        assertFalse(model.uiState.value.isAiTranslating)
        assertFalse(model.translationManager.isEnabled)
    }

    @Test fun preferenceRestoreAndScanCannotReplaceALockedPick() {
        model.selectSubtitle(english)
        val before = model.uiState.value
        PlayerViewModel::class.java.getDeclaredMethod("applyPreferredSubtitle", String::class.java, List::class.java, String::class.java)
            .apply { isAccessible = true }.invoke(model, "Off", listOf(remote), null)
        PlayerViewModel::class.java.getDeclaredMethod("restoreSubtitle", Subtitle::class.java)
            .apply { isAccessible = true }.invoke(model, remote)
        model.runFindBestMatch(isUserAction = false)
        model.findBestSubtitleMatch()
        assertEquals(before, model.uiState.value)
    }

    @Test fun embeddedReindexMovesTheLockAndKeepsCollidingProvidersSeparate() = runTest(dispatcher) {
        model.selectSubtitle(english)
        val fresh = english.copy(id = "reindexed", groupIndex = 4, trackIndex = 0)
        model.updatePlayerTextTracks(listOf(fresh, fresh.copy(provider = "other")))
        runCurrent()
        assertEquals(fresh, model.uiState.value.selectedSubtitle)
        assertEquals(SubtitleSelectionPolicy.key(fresh), field("userLockedSubtitleKey").get(model))
        assertEquals(2, model.uiState.value.subtitles.count { it.id == fresh.id })
    }

    @Test fun reindexedAiSourceStillReceivesTheRuntimeImageFallback() = runTest(dispatcher) {
        model.activateAiTranslation()
        val fresh = english.copy(id = "reindexed", groupIndex = 2, trackIndex = 0)
        model.updatePlayerTextTracks(listOf(fresh))
        runCurrent()
        assertEquals(fresh, model.uiState.value.selectedSubtitle)
        assertEquals(fresh, field("aiSourceSubtitle").get(model))
        model.translationManager.onUntranslatableSource!!.invoke()
        runCurrent()
        assertFalse(model.uiState.value.isAiTranslating)
    }

    @Test fun queuedImageSourceCallbacksDoNotRejectTheReplacementSource() = runTest(dispatcher) {
        val french = english.copy(id = "fr", lang = "fr", label = "French")
        state().value = PlayerUiState(subtitles = listOf(english, french))
        model.activateAiTranslation()
        repeat(2) { model.translationManager.onUntranslatableSource!!.invoke() }
        runCurrent()
        assertEquals(french, model.uiState.value.selectedSubtitle)
        assertTrue(model.uiState.value.isAiTranslating)
        model.translationManager.onUntranslatableSource!!.invoke()
        runCurrent()
        assertFalse(model.uiState.value.isAiTranslating)
        assertFalse(model.uiState.value.isAiAvailable)
        assertFalse(model.translationManager.isEnabled)
        model.activateAiTranslation()
        assertFalse(model.uiState.value.isAiTranslating)
    }

    private fun holdDownload(): CompletableDeferred<String?> {
        mockkObject(SubtitleSyncMatcher)
        val reply = CompletableDeferred<String?>()
        coEvery { SubtitleSyncMatcher.loadRaw(remote.url, remote.lang) } coAnswers { reply.await() }
        return reply
    }

    private fun field(name: String) = PlayerViewModel::class.java.getDeclaredField(name).apply { isAccessible = true }
    @Suppress("UNCHECKED_CAST")
    private fun state() = field("_uiState").get(model) as MutableStateFlow<PlayerUiState>
}
