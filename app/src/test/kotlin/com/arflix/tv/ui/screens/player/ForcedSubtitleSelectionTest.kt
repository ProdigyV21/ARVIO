package com.arflix.tv.ui.screens.player

import android.util.Log
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewModelScope
import com.arflix.tv.data.model.Subtitle
import com.arflix.tv.data.model.StreamSource
import com.arflix.tv.data.model.IptvVodSourceIds
import com.arflix.tv.ui.screens.player.audiosync.ArvioAudioSync
import io.mockk.mockk
import io.mockk.every
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import io.mockk.verify
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

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28], manifest = Config.NONE, application = android.app.Application::class)
@ConscryptMode(ConscryptMode.Mode.OFF)
class ForcedSubtitleSelectionTest {
    private lateinit var model: PlayerViewModel
    private val store = ViewModelStore()
    private val full = Subtitle(id = "full", url = "", lang = "en", label = "English", isEmbedded = true)
    private val forced = full.copy(id = "forced", label = "English forced", isForced = true, hasForcedFlag = true)

    @Before fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        mockkStatic(Log::class)
        every { Log.i(any(), any()) } returns 0
        model = PlayerViewModel(
            mockk(relaxed = true), mockk(relaxed = true), mockk(relaxed = true),
            mockk(relaxed = true), mockk(relaxed = true), mockk(relaxed = true),
            mockk(relaxed = true), mockk(relaxed = true), mockk(relaxed = true),
            mockk(relaxed = true), mockk(relaxed = true), mockk(relaxed = true),
            mockk(relaxed = true), mockk(relaxed = true),
            streamIntegrationRepository = mockk(relaxed = true),
            recentPlayedSourceRepository = mockk(relaxed = true)
        )
        store.put("player", model)
        state().value = PlayerUiState(useForcedSubtitles = true)
    }

    @After fun tearDown() {
        val job = model.viewModelScope.coroutineContext[Job]
        try {
            store.clear()
            runBlocking { withTimeout(5_000) { job?.join() } }
        } finally {
            unmockkStatic(Log::class)
            Dispatchers.resetMain()
        }
    }

    @Test fun automaticFullTrackCanChangeToForcedAndBackWithAudio() {
        selectForAudio("de")
        assertEquals(full, model.uiState.value.selectedSubtitle)
        assertFalse(manualSelection())
        selectForAudio("en")
        assertEquals(forced, model.uiState.value.selectedSubtitle)
        selectForAudio("de")
        assertEquals(full, model.uiState.value.selectedSubtitle)
        assertFalse(manualSelection())
        selectForAudio("en")
        assertEquals(forced, model.uiState.value.selectedSubtitle)
    }

    @Test fun matchingAudioWithoutForcedTrackClearsAutomaticFullTrack() {
        selectForAudio("de", listOf(full))
        assertEquals(full, model.uiState.value.selectedSubtitle)
        assertFalse(manualSelection())
        selectForAudio("en", listOf(full))
        assertNull(model.uiState.value.selectedSubtitle)
    }

    @Test fun manualOffSurvivesAudioChanges() {
        selectForAudio("de")
        model.disableSubtitles()
        selectForAudio("en")
        assertNull(model.uiState.value.selectedSubtitle)
        assertTrue(manualSelection())
    }

    @Test fun manualOffStopsHearingAndCancelsPendingSubtitleDownload() {
        val sync = mockk<ArvioAudioSync>(relaxed = true)
        val pending = Job()
        field("audioSyncInstance").set(model, sync)
        field("audioSyncTarget").set(model, full)
        field("audioSyncRaw").set(model, "old subtitle")
        field("audioSyncWorkJob").set(model, pending)
        state().value = state().value.copy(isHearingSync = true)

        model.disableSubtitles()

        verify(exactly = 1) { sync.stop() }
        assertTrue(pending.isCancelled)
        assertNull(field("audioSyncTarget").get(model))
        assertNull(field("audioSyncRaw").get(model))
        assertFalse(model.uiState.value.isHearingSync)
        assertNull(model.uiState.value.selectedSubtitle)
    }

    @Test fun providerVodDoesNotOpenExtraAudioSamplingConnections() {
        for (provider in IptvVodSourceIds.ALL) verifyAudioSampling(provider, live = false, allowed = false)
    }

    @Test fun livePlaybackDoesNotOpenExtraAudioSamplingConnections() {
        verifyAudioSampling("sports-addon", live = true, allowed = false)
    }

    @Test fun ordinaryMovieSourceKeepsAudioSamplingAvailable() {
        verifyAudioSampling("movie-addon", live = false, allowed = true)
    }

    private fun verifyAudioSampling(provider: String, live: Boolean, allowed: Boolean) {
        val sync = mockk<ArvioAudioSync>(relaxed = true)
        val url = "https://media.example/movie.mkv"
        field("audioSyncInstance").set(model, sync)
        field("currentIsLiveStreamPlayback").setBoolean(model, live)
        state().value = state().value.copy(
            selectedStreamUrl = url,
            selectedStream = StreamSource("test", "test", provider, "1080p", "", url = url)
        )

        PlayerViewModel::class.java.getDeclaredMethod("audioSyncOnStream")
            .apply { isAccessible = true }.invoke(model)

        verify { sync.onStream(url, emptyMap(), allowSpotSampling = allowed) }
    }

    @Test fun explicitTrackPickSurvivesAudioChanges() {
        state().value = state().value.copy(selectedSubtitle = full)
        field("hasManualSubtitleSelection").set(model, true)
        field("userPickedSubtitle").set(model, true)
        selectForAudio("en")
        assertEquals(full, model.uiState.value.selectedSubtitle)
    }

    @Test fun disabledSettingKeepsExistingAutomaticSelectionGuard() {
        state().value = state().value.copy(useForcedSubtitles = false)
        selectForAudio("en")
        assertEquals(full, model.uiState.value.selectedSubtitle)
        assertTrue(manualSelection())
    }

    // Exercise the production selector without network, DataStore, or a running player.
    private fun selectForAudio(language: String, subtitles: List<Subtitle> = listOf(full, forced)) {
        field("currentAudioLanguage").set(model, language)
        PlayerViewModel::class.java.getDeclaredMethod(
            "applyPreferredSubtitle", String::class.java, List::class.java, String::class.java
        ).apply { isAccessible = true }.invoke(model, "en", subtitles, null)
    }

    private fun field(name: String) = PlayerViewModel::class.java.getDeclaredField(name).apply { isAccessible = true }
    private fun manualSelection() = field("hasManualSubtitleSelection").getBoolean(model)

    @Suppress("UNCHECKED_CAST")
    private fun state() = field("_uiState").get(model) as MutableStateFlow<PlayerUiState>
}
