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
import com.arflix.tv.BuildConfig
import com.arflix.tv.R
import com.arflix.tv.data.model.Subtitle
import com.arflix.tv.data.repository.ProfileManager
import com.arflix.tv.data.repository.effectiveSettingsDataStore
import com.arflix.tv.ui.screens.player.audiosync.ArvioAudioSync
import com.arflix.tv.ui.screens.player.audiosync.AudioSyncSettings
import com.arflix.tv.ui.screens.player.audiosync.SubtitleSyncModel
import com.arflix.tv.ui.screens.player.audiosync.SubtitleSyncSegment
import com.arflix.tv.ui.screens.player.subtitles.RETIMED_SUBTITLE_ID_SUFFIX
import com.arflix.tv.ui.screens.player.subtitles.SubtitleAutoSync
import com.arflix.tv.ui.screens.player.subtitles.SubtitleSyncMatcher
import com.arflix.tv.util.settingsDataStore
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.mockkStatic
import io.mockk.unmockkObject
import io.mockk.unmockkStatic
import io.mockk.verify
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.ConscryptMode

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28], manifest = Config.NONE, application = android.app.Application::class)
@ConscryptMode(ConscryptMode.Mode.OFF)
class SubtitleAudioSyncViewModelTest {
    @get:Rule val temporary = TemporaryFolder()
    private val scheduler = TestCoroutineScheduler()
    private val dispatcher = StandardTestDispatcher(scheduler)
    private val store = ViewModelStore()
    private lateinit var model: PlayerViewModel

    private val embedded = Subtitle(
        id = "emb_en",
        url = "",
        lang = "en",
        label = "English",
        provider = "embedded",
        isEmbedded = true,
    )
    private val remote = Subtitle(
        id = "opensub_1",
        url = "https://subs.example/en.srt",
        lang = "en",
        label = "English",
        provider = "opensubtitles",
        isEmbedded = false,
    )
    private val otherRemote = Subtitle(
        id = "opensub_2",
        url = "https://subs.example/other.srt",
        lang = "en",
        label = "English 2",
        provider = "opensubtitles",
        isEmbedded = false,
    )
    private val streamUrl = "https://stream.example/movie.mkv"
    private val sampleRaw = "1\n00:00:01,000 --> 00:00:03,000\nHello world\n"

    @Before
    fun setUp() {
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
        mockkStatic("com.arflix.tv.data.repository.PartnerPreferencesKt")
        every { any<Context>().effectiveSettingsDataStore } returns memoryStore
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
            mockk(relaxed = true),
            com.arflix.tv.data.repository.IdleReturnCoordinator(mockk(relaxed = true), mockk(relaxed = true))
        )
        store.put("player", model)
        field("aiSubtitleEnabled").setBoolean(model, true)
        field("targetSubtitleLangCode").set(model, "he")
        scheduler.runCurrent()
    }

    @After
    fun tearDown() {
        AudioSyncSettings.setFallbackEnabled(true)
        try {
            store.clear()
            scheduler.runCurrent()
        } finally {
            unmockkObject(SubtitleSyncMatcher)
            unmockkStatic("com.arflix.tv.data.repository.PartnerPreferencesKt")
            unmockkStatic("com.arflix.tv.util.DataStoresKt")
            unmockkStatic(Log::class)
            Dispatchers.resetMain()
        }
    }

    @Test
    fun explicitSyncRunsWhenHearingSyncAndFallbackAreDisabled() = runTest(dispatcher) {
        assertTrue("Expected sideload unit tests to enable AUDIO_SYNC_AVAILABLE", BuildConfig.AUDIO_SYNC_AVAILABLE)
        field("hearingSyncEnabled").setBoolean(model, false)
        AudioSyncSettings.setFallbackEnabled(false)
        val sync = mockSync(takeOverResult = true)
        mockkObject(SubtitleSyncMatcher)
        coEvery { SubtitleSyncMatcher.loadRaw(remote.url, remote.lang) } returns sampleRaw

        state().value = PlayerUiState(
            subtitles = listOf(remote),
            selectedSubtitle = remote,
            selectedStreamUrl = streamUrl,
        )

        model.syncCurrentSubtitleToAudio()
        awaitAudioSyncWork()

        assertTrue(model.uiState.value.isSubtitleAudioSyncRequested)
        verify(exactly = 1) {
            sync.takeOver(
                key = remote.url,
                cues = any(),
                onFailed = any(),
                localAnchor = isNull(),
                hasBetterFallback = any(),
                holdUntilConfirmed = false,
                force = true,
                allowSwitch = false,
            )
        }
    }

    @Test
    fun embeddedOrMissingSubtitleShowsPickExternalToast() {
        assertTrue(BuildConfig.AUDIO_SYNC_AVAILABLE)
        state().value = PlayerUiState(subtitles = listOf(embedded), selectedSubtitle = null, selectedStreamUrl = streamUrl)
        model.syncCurrentSubtitleToAudio()
        assertEquals(PlayerMessage.Res(R.string.player_audio_sync_pick_external), model.uiState.value.matchToast)
        assertFalse(model.uiState.value.isSubtitleAudioSyncRequested)

        model.dismissMatchToast()
        state().value = PlayerUiState(subtitles = listOf(embedded), selectedSubtitle = embedded, selectedStreamUrl = streamUrl)
        model.syncCurrentSubtitleToAudio()
        assertEquals(PlayerMessage.Res(R.string.player_audio_sync_pick_external), model.uiState.value.matchToast)
        assertFalse(model.uiState.value.isSubtitleAudioSyncRequested)
    }

    @Test
    fun explicitSyncWorksOnAutoSelectedExternalSubtitleWithoutUserLock() = runTest(dispatcher) {
        assertTrue(BuildConfig.AUDIO_SYNC_AVAILABLE)
        val sync = mockSync(takeOverResult = true)
        mockkObject(SubtitleSyncMatcher)
        coEvery { SubtitleSyncMatcher.loadRaw(remote.url, remote.lang) } returns sampleRaw

        field("userLockedSubtitleKey").set(model, null)
        state().value = PlayerUiState(
            subtitles = listOf(remote),
            selectedSubtitle = remote.copy(url = "file:///cache/auto.srt"),
            selectedStreamUrl = streamUrl,
        )

        model.syncCurrentSubtitleToAudio()
        awaitAudioSyncWork()

        assertTrue(model.uiState.value.isSubtitleAudioSyncRequested)
        assertNotNull(field("subtitleAudioSyncRequest").get(model))
        verify(exactly = 1) {
            sync.takeOver(
                key = remote.url,
                cues = any(),
                onFailed = any(),
                localAnchor = isNull(),
                hasBetterFallback = any(),
                holdUntilConfirmed = false,
                force = true,
                allowSwitch = false,
            )
        }
    }

    @Test
    fun trackPickAiLiveAndDisableCancelInFlightExplicitSync() = runTest(dispatcher) {
        assertTrue(BuildConfig.AUDIO_SYNC_AVAILABLE)
        val actions = listOf<() -> Unit>(
            { model.selectSubtitle(otherRemote) },
            { model.activateAiTranslation() },
            { model.toggleLiveAudioTranslation() },
            { model.disableSubtitles() },
        )
        for (action in actions) {
            val sync = mockSync(takeOverResult = true)
            mockkObject(SubtitleSyncMatcher)
            val reply = CompletableDeferred<String?>()
            coEvery { SubtitleSyncMatcher.loadRaw(any(), any()) } returns null
            coEvery { SubtitleSyncMatcher.loadRaw(remote.url, remote.lang) } coAnswers { reply.await() }
            if (model.uiState.value.isLiveAudioTranslating) model.toggleLiveAudioTranslation()
            model.translationManager.isEnabled = false
            field("userLockedSubtitleKey").set(model, SubtitleSelectionPolicy.key(remote))
            state().value = PlayerUiState(
                subtitles = listOf(embedded, remote, otherRemote),
                selectedSubtitle = remote,
                selectedStreamUrl = streamUrl,
            )

            model.syncCurrentSubtitleToAudio()
            runCurrent()
            val pending = field("audioSyncWorkJob").get(model) as Job
            assertTrue(pending.isActive)
            assertTrue(model.uiState.value.isSubtitleAudioSyncRequested)

            action()

            assertTrue(pending.isCancelled)
            assertFalse(model.uiState.value.isSubtitleAudioSyncRequested)
            assertNull(field("subtitleAudioSyncRequest").get(model))

            reply.complete(sampleRaw)
            runCurrent()

            assertFalse(model.uiState.value.isSubtitleAudioSyncRequested)
            assertNull(field("audioSyncTarget").get(model))
            verify(exactly = 0) {
                sync.takeOver(any(), any(), any(), any(), any(), any(), any(), any())
            }
            unmockkObject(SubtitleSyncMatcher)
        }
    }

    @Test
    fun stopSubtitleAudioSyncClearsRequestRevertsShiftAndRetimeAndShowsStoppedToast() = runTest(dispatcher) {
        assertTrue(BuildConfig.AUDIO_SYNC_AVAILABLE)
        val sync = mockSync(takeOverResult = true)
        val localized = remote.copy(url = "file:///cache/en.srt")

        // Constant shift case
        field("audioSyncTarget").set(model, remote)
        field("audioSyncRaw").set(model, sampleRaw)
        field("subtitleAudioSyncRequest").set(
            model,
            SubtitleAudioSyncRequest(
                generation = field("audioSyncGeneration").getLong(model),
                streamUrl = streamUrl,
                subtitleKey = SubtitleSelectionPolicy.key(remote),
            )
        )
        state().value = PlayerUiState(
            subtitles = listOf(remote),
            selectedSubtitle = localized,
            selectedStreamUrl = streamUrl,
            isSubtitleAudioSyncRequested = true,
            autoSync = SubtitleAutoSync.ofMs(1250L),
            autoSyncSubtitleKey = "${remote.provider}|${remote.id}",
        )

        model.stopSubtitleAudioSync()
        runCurrent()

        verify(exactly = 1) { sync.stop() }
        assertFalse(model.uiState.value.isSubtitleAudioSyncRequested)
        assertNull(field("subtitleAudioSyncRequest").get(model))
        assertNull(model.uiState.value.autoSync)
        assertNull(model.uiState.value.autoSyncSubtitleKey)
        assertEquals(PlayerMessage.Res(R.string.player_audio_sync_stopped), model.uiState.value.matchToast)

        // Retimed copy case
        model.dismissMatchToast()
        val retimed = localized.copy(
            id = localized.id + RETIMED_SUBTITLE_ID_SUFFIX,
            url = "file:///cache/en_retimed.srt",
        )
        field("audioSyncTarget").set(model, remote)
        field("audioSyncRaw").set(model, sampleRaw)
        field("subtitleAudioSyncRequest").set(
            model,
            SubtitleAudioSyncRequest(
                generation = field("audioSyncGeneration").getLong(model),
                streamUrl = streamUrl,
                subtitleKey = SubtitleSelectionPolicy.key(remote),
            )
        )
        state().value = PlayerUiState(
            subtitles = listOf(remote),
            selectedSubtitle = retimed,
            selectedStreamUrl = streamUrl,
            isSubtitleAudioSyncRequested = true,
        )

        model.stopSubtitleAudioSync()
        runCurrent()

        assertFalse(model.uiState.value.isSubtitleAudioSyncRequested)
        assertEquals(remote.id, model.uiState.value.selectedSubtitle?.id)
        assertNull(model.uiState.value.autoSync)
        assertEquals(PlayerMessage.Res(R.string.player_audio_sync_stopped), model.uiState.value.matchToast)
    }

    @Test
    fun staleAudioSyncModelCallbackIsIgnoredAndStopsSession() {
        assertTrue(BuildConfig.AUDIO_SYNC_AVAILABLE)
        val sync = mockSync(takeOverResult = true)
        field("audioSyncTarget").set(model, remote)
        field("audioSyncRaw").set(model, sampleRaw)
        field("subtitleAudioSyncRequest").set(
            model,
            SubtitleAudioSyncRequest(
                generation = field("audioSyncGeneration").getLong(model),
                streamUrl = streamUrl,
                subtitleKey = SubtitleSelectionPolicy.key(remote),
            )
        )
        state().value = PlayerUiState(
            subtitles = listOf(remote),
            selectedSubtitle = remote,
            selectedStreamUrl = "https://stream.example/other.mkv",
            isSubtitleAudioSyncRequested = true,
        )

        val applyMethod = PlayerViewModel::class.java.getDeclaredMethod(
            "applyAudioSyncModel",
            String::class.java,
            SubtitleSyncModel::class.java,
        ).apply { isAccessible = true }
        applyMethod.invoke(model, remote.url, SubtitleSyncModel(listOf(SubtitleSyncSegment(0L, 1.0, 1500.0))))

        assertNull(model.uiState.value.autoSync)
        assertFalse(model.uiState.value.isSubtitleAudioSyncRequested)
        verify(atLeast = 1) { sync.stop() }
    }

    private fun TestScope.awaitAudioSyncWork() {
        runCurrent()
        val job = field("audioSyncWorkJob").get(model) as? Job ?: return
        val deadlineMs = System.currentTimeMillis() + 5_000L
        while (job.isActive && System.currentTimeMillis() < deadlineMs) {
            testScheduler.advanceUntilIdle()
            Thread.sleep(5)
        }
        testScheduler.advanceUntilIdle()
    }

    private fun mockSync(takeOverResult: Boolean): ArvioAudioSync {
        val sync = mockk<ArvioAudioSync>(relaxed = true)
        every {
            sync.takeOver(any(), any(), any(), any(), any(), any(), any(), any())
        } returns takeOverResult
        field("audioSyncInstance").set(model, sync)
        return sync
    }

    private fun field(name: String) = PlayerViewModel::class.java.getDeclaredField(name).apply { isAccessible = true }

    @Suppress("UNCHECKED_CAST")
    private fun state() = field("_uiState").get(model) as MutableStateFlow<PlayerUiState>
}
