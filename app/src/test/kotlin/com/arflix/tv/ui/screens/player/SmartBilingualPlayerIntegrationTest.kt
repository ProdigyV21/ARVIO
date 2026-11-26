package com.arflix.tv.ui.screens.player

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewModelScope
import com.arflix.tv.data.model.Subtitle
import com.arflix.tv.data.repository.effectiveSettingsDataStore
import com.arflix.tv.ui.screens.player.subtitles.GeminiLiveTranslationService
import com.arflix.tv.ui.screens.player.subtitles.SubtitleSyncMatcher
import com.arflix.tv.ui.screens.player.subtitles.bilingual.*
import com.arflix.tv.util.settingsDataStore
import io.mockk.*
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.withTimeout
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.ConscryptMode

/** Player-level transitions; never connects to a translation provider or a media URL. */
@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28], manifest = Config.NONE, application = android.app.Application::class)
@ConscryptMode(ConscryptMode.Mode.OFF)
class SmartBilingualPlayerIntegrationTest {
    private lateinit var model: PlayerViewModel
    private val models = ViewModelStore()
    private val original = Subtitle(id = "before", url = "", lang = "en", label = "English", isEmbedded = true)
    private val primary = original.copy(id = "smart", lang = "ja", label = "Japanese")
    private val settings = MutableStateFlow(BilingualSubtitleSettings.DEFAULT)
    private val live = mockk<GeminiLiveTranslationService>(relaxed = true)
    private val bilingualStore = mockk<BilingualSubtitleSettingsStore>()

    @Before fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
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
        mockkObject(BilingualSubtitleSettingsStore.Companion)
        every { bilingualStore.settings } returns settings
        every { bilingualStore.isLoadedForCurrentProfile } returns true
        every { BilingualSubtitleSettingsStore.from(any()) } returns bilingualStore
        model = PlayerViewModel(
            mockk(relaxed = true), mockk(relaxed = true), mockk(relaxed = true),
            mockk(relaxed = true), mockk(relaxed = true), mockk(relaxed = true),
            mockk(relaxed = true), mockk(relaxed = true), mockk(relaxed = true),
            mockk(relaxed = true), mockk(relaxed = true), mockk(relaxed = true),
            mockk(relaxed = true), mockk(relaxed = true),
            streamIntegrationRepository = mockk(relaxed = true),
            recentPlayedSourceRepository = mockk(relaxed = true),
            kidsContentFilter = mockk(relaxed = true),
            idleReturnCoordinator = mockk(relaxed = true),
        )
        models.put("player", model)
        field("aiApiKey").set(model, "unit-test-placeholder-not-a-real-key")
        field("aiSubtitleEnabled").setBoolean(model, true)
        field("geminiLiveService").set(model, live)
        state().value = PlayerUiState(
            selectedStreamUrl = "video-1",
            selectedSubtitle = original,
            subtitles = listOf(original, primary),
            isAiAvailable = true,
            aiTargetLanguageName = "Hebrew",
        )
        BilingualPlayerBridge.primaryOverride.value = null
        BilingualPlayerBridge.secondaryOverride.value = null
    }

    @After fun tearDown() {
        val job = model.viewModelScope.coroutineContext[Job]
        try {
            models.clear()
            runBlocking { withTimeout(5_000) { job?.join() } }
        } finally {
            BilingualPlayerBridge.primaryOverride.value = null
            BilingualPlayerBridge.secondaryOverride.value = null
            unmockkObject(SubtitleSyncMatcher)
            unmockkObject(BilingualSubtitleSettingsStore.Companion)
            unmockkStatic("com.arflix.tv.data.repository.PartnerPreferencesKt")
            unmockkStatic("com.arflix.tv.util.DataStoresKt")
            unmockkStatic(Log::class)
            Dispatchers.resetMain()
        }
    }

    @Test fun smartAiOffRestoresNormalSubtitleAndStopsTranslation() {
        model.requestSmartBilingual()
        model.activateSmartBilingualAi(primary, "Simplified Chinese")
        assertTrue(model.uiState.value.smartBilingualActive)
        assertTrue(model.translationManager.isEnabled)
        assertTrue(model.translationManager.bilingualActive)
        BilingualPlayerBridge.primaryOverride.value = primary.id
        BilingualPlayerBridge.secondaryOverride.value = "secondary"
        model.stopSmartBilingualForVideo()
        assertEquals(original, model.uiState.value.selectedSubtitle)
        assertFalse(model.uiState.value.smartBilingualActive)
        assertFalse(model.translationManager.bilingualActive)
        assertFalse(model.uiState.value.isAiTranslating)
        assertFalse(model.translationManager.isEnabled)
        assertFalse(model.uiState.value.smartBilingualRequest.requestedForVideo)
        assertTrue(model.uiState.value.smartBilingualRequest.suppressedForVideo)
        assertNull(BilingualPlayerBridge.primaryOverride.value)
        assertNull(BilingualPlayerBridge.secondaryOverride.value)
    }

    @Test fun replanThenOffRestoresStandaloneAiThatPredatedSmartMode() {
        state().value = state().value.copy(isAiTranslating = true)
        field("aiSourceSubtitle").set(model, original)
        model.translationManager.targetLanguage = "Hebrew"
        model.translationManager.isEnabled = true
        model.activateSmartBilingualAi(primary, "Simplified Chinese")
        model.activateSmartBilingualMerge(primary)
        model.stopSmartBilingualForVideo()
        assertEquals(original, model.uiState.value.selectedSubtitle)
        assertEquals(original, field("aiSourceSubtitle").get(model))
        assertEquals("Hebrew", model.translationManager.targetLanguage)
        assertEquals("Hebrew", model.uiState.value.aiTargetLanguageName)
        assertTrue(model.uiState.value.isAiTranslating)
        assertTrue(model.translationManager.isEnabled)
        assertFalse(model.translationManager.bilingualActive)
    }

    @Test fun liveTakesOwnershipSynchronouslyAndRejectsEveryStaleSmartActivation() {
        model.requestSmartBilingual()
        model.activateSmartBilingualAi(primary, "Simplified Chinese")
        model.toggleLiveAudioTranslation()
        assertTrue(model.uiState.value.isLiveAudioTranslating)
        assertNull(model.uiState.value.selectedSubtitle)
        assertFalse(model.uiState.value.smartBilingualActive)
        assertFalse(model.translationManager.bilingualActive)
        model.activateSmartBilingualMerge(primary)
        model.activateSmartBilingualPrimaryOnly(primary)
        model.activateSmartBilingualAi(primary, "Simplified Chinese")
        model.activateAiTranslation(isUserAction = true)
        assertNull(model.uiState.value.selectedSubtitle)
        assertFalse(model.translationManager.isEnabled)
        assertFalse(model.uiState.value.smartBilingualActive)
        verify(exactly = 1) { live.connect() }
        model.toggleLiveAudioTranslation()
        assertEquals(original, model.uiState.value.selectedSubtitle)
        assertFalse(model.uiState.value.isAiTranslating)
        assertTrue(model.uiState.value.smartBilingualRequest.shouldRun(false, false))
    }

    @Test fun explicitOffDuringLiveClearsThePerVideoRequest() {
        model.requestSmartBilingual()
        model.activateSmartBilingualAi(primary, "Simplified Chinese")
        model.toggleLiveAudioTranslation()
        model.stopSmartBilingualForVideo()
        model.toggleLiveAudioTranslation()
        assertFalse(model.uiState.value.smartBilingualRequest.shouldRun(true, false))
        model.activateSmartBilingualPrimaryOnly(primary)
        assertFalse(model.uiState.value.smartBilingualActive)
        assertEquals(original, model.uiState.value.selectedSubtitle)
    }

    @Test fun manualSubtitleOffNeverRestoresAnEarlierStandaloneAi() {
        state().value = state().value.copy(isAiTranslating = true)
        model.translationManager.isEnabled = true
        model.activateSmartBilingualAi(primary, "Simplified Chinese")
        model.disableSubtitles()
        assertNull(model.uiState.value.selectedSubtitle)
        assertFalse(model.uiState.value.isAiTranslating)
        assertFalse(model.uiState.value.smartBilingualActive)
        assertFalse(model.translationManager.bilingualActive)
        assertFalse(model.translationManager.isEnabled)
        assertFalse(model.uiState.value.smartBilingualRequest.shouldRun(true, false))
    }

    @Test fun aManualTrackChoiceNeverGetsOverwrittenByTheOldSnapshot() {
        model.activateSmartBilingualAi(primary, "Simplified Chinese")
        model.selectSubtitle(primary)
        assertEquals(primary, model.uiState.value.selectedSubtitle)
        assertFalse(model.uiState.value.isAiTranslating)
        assertFalse(model.translationManager.bilingualActive)
        model.leaveSmartBilingual()
        assertEquals(primary, model.uiState.value.selectedSubtitle)
        assertFalse(model.translationManager.isEnabled)
    }

    @Test fun explicitRegularAiIsNotReclaimedByGloballyEnabledSmartMode() {
        settings.value = settings.value.copy(smartEnabled = true)
        model.activateSmartBilingualMerge(primary)
        model.activateAiTranslation(isUserAction = true)
        assertTrue(model.uiState.value.isAiTranslating)
        assertFalse(model.uiState.value.smartBilingualActive)
        assertFalse(model.translationManager.bilingualActive)
        assertEquals(original, model.uiState.value.selectedSubtitle)
        assertFalse(model.uiState.value.smartBilingualRequest.shouldRun(true, false))
        model.activateSmartBilingualPrimaryOnly(primary)
        assertTrue(model.uiState.value.isAiTranslating)
        assertFalse(model.uiState.value.smartBilingualActive)
        assertEquals(original, model.uiState.value.selectedSubtitle)
    }

    @Test fun mergeGenerationCannotAutomaticallyActivateAi() {
        settings.value = settings.value.copy(generationMode = BilingualGenerationMode.MERGE)
        model.activateSmartBilingualAi(primary, "Simplified Chinese")
        model.activateAiTranslation(isUserAction = false)
        assertEquals(original, model.uiState.value.selectedSubtitle)
        assertFalse(model.translationManager.isEnabled)
        assertFalse(model.uiState.value.isAiTranslating)
    }

    @Test fun aNewStreamDiscardsTheOldSnapshotAndTemporaryRequest() {
        model.requestSmartBilingual()
        model.activateSmartBilingualPrimaryOnly(primary)
        state().value = state().value.copy(selectedStreamUrl = "video-2", selectedSubtitle = null)
        model.resetSmartBilingualForStream()
        model.leaveSmartBilingual()
        assertNull(model.uiState.value.selectedSubtitle)
        assertFalse(model.uiState.value.smartBilingualActive)
        assertEquals(SmartBilingualRequest(), model.uiState.value.smartBilingualRequest)
    }

    @Test fun preferredSubtitleDirectAutoAiPathAlsoHonorsMergeAndUnloadedPolicy() {
        field("aiSubtitleAutoSelect").setBoolean(model, true)
        field("aiFindBestMatchFirst").setBoolean(model, false)
        settings.value = settings.value.copy(generationMode = BilingualGenerationMode.MERGE)
        applyPreferred("he")
        assertFalse(model.translationManager.isEnabled)
        assertFalse(model.uiState.value.isAiTranslating)
        settings.value = settings.value.copy(generationMode = BilingualGenerationMode.AUTO)
        every { bilingualStore.isLoadedForCurrentProfile } returns false
        applyPreferred("he")
        model.activateAiTranslation(isUserAction = false)
        assertFalse(model.translationManager.isEnabled)
        assertFalse(model.uiState.value.isAiTranslating)
    }

    @Test fun slowManualPickSuspendsSmartPlanningAndCannotBeStolen() {
        model.requestSmartBilingual()
        model.activateSmartBilingualPrimaryOnly(primary)
        // A remote pick is being localized: smart mode must not cancel it or re-select the old primary.
        state().value = state().value.copy(subtitleSelectionPending = true)
        applyPreferred("en")
        model.selectSubtitle(original, isUserAction = false)
        assertTrue(model.uiState.value.subtitleSelectionPending)
        assertEquals(primary, model.uiState.value.selectedSubtitle)
        // The (tested-in-Controller) plan gate is this same pure predicate.
        assertFalse(
            model.uiState.value.smartBilingualRequest.shouldRun(
                true,
                liveAudio = false,
                selectionPending = model.uiState.value.subtitleSelectionPending,
            )
        )
    }

    @Test fun latePreferredEmbeddedTrackCannotStealLiveOwnership() {
        model.requestSmartBilingual()
        model.activateSmartBilingualAi(primary, "Simplified Chinese")
        model.toggleLiveAudioTranslation()
        applyPreferred("en")
        model.selectSubtitle(original, isUserAction = false)
        assertTrue(model.uiState.value.isLiveAudioTranslating)
        assertNull(model.uiState.value.selectedSubtitle)
        assertFalse(model.translationManager.isEnabled)
    }

    @Test fun manualPickAndDisableBumpPickNonceAndClearBilingualActive() {
        val nonce0 = model.uiState.value.subtitleUserPickNonce
        model.activateSmartBilingualMerge(primary)
        assertTrue(model.translationManager.bilingualActive)

        model.selectSubtitle(original, isUserAction = true)
        assertEquals(nonce0 + 1, model.uiState.value.subtitleUserPickNonce)
        assertFalse(model.translationManager.bilingualActive)
        assertFalse(model.uiState.value.smartBilingualActive)

        model.disableSubtitles()
        assertEquals(nonce0 + 2, model.uiState.value.subtitleUserPickNonce)
        assertFalse(model.translationManager.bilingualActive)
        assertFalse(model.uiState.value.subtitleSelectionPending)
    }

    @Test fun remoteManualPickSetsAndClearsSubtitleSelectionPendingWhileBlockingSmartClaim() {
        mockkObject(SubtitleSyncMatcher)
        val rawGate = CompletableDeferred<String?>()
        coEvery { SubtitleSyncMatcher.loadRaw(any(), any()) } coAnswers { rawGate.await() }

        val remote = Subtitle(
            id = "ext-es",
            url = "https://example.invalid/subs/es.srt",
            lang = "es",
            label = "Spanish",
            isEmbedded = false,
        )
        state().value = state().value.copy(subtitles = listOf(original, primary, remote))

        model.selectSubtitle(remote, isUserAction = true)
        assertTrue(model.uiState.value.subtitleSelectionPending)

        // While localization is in flight, smart mode cannot claim ownership.
        model.activateSmartBilingualMerge(primary)
        assertFalse(model.uiState.value.smartBilingualActive)
        assertTrue(model.uiState.value.subtitleSelectionPending)

        // Completing the download applies the remote track and clears subtitleSelectionPending.
        rawGate.complete(null)
        assertFalse(model.uiState.value.subtitleSelectionPending)
        assertEquals(remote, model.uiState.value.selectedSubtitle)
    }

    private fun applyPreferred(language: String) {
        PlayerViewModel::class.java.getDeclaredMethod(
            "applyPreferredSubtitle",
            String::class.java,
            List::class.java,
            String::class.java,
        ).apply { isAccessible = true }.invoke(model, language, listOf(original, primary), null)
    }

    private fun field(name: String) = PlayerViewModel::class.java.getDeclaredField(name).apply { isAccessible = true }

    @Suppress("UNCHECKED_CAST")
    private fun state() = field("_uiState").get(model) as MutableStateFlow<PlayerUiState>
}
