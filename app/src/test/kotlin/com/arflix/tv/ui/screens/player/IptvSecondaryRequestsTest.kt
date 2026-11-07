package com.arflix.tv.ui.screens.player

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.core.emptyPreferences
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewModelScope
import com.arflix.tv.data.model.IptvVodSourceIds
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.model.StreamSource
import com.arflix.tv.data.repository.IptvConnectionLimit
import com.arflix.tv.data.repository.StreamIntegrationRepository
import com.arflix.tv.data.repository.StreamRepository
import com.arflix.tv.data.repository.effectiveSettingsDataStore
import com.arflix.tv.data.repository.iptvStreamPolicy
import com.arflix.tv.network.IptvAccountAvailability
import com.arflix.tv.network.IptvAdmission
import com.arflix.tv.network.IptvProviderAccount
import com.arflix.tv.ui.screens.player.subtitles.MatroskaIndexSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.mockkStatic
import io.mockk.runs
import io.mockk.unmockkObject
import io.mockk.unmockkStatic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.withTimeout
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import org.robolectric.annotation.ConscryptMode

private const val EFFECTIVE_SETTINGS_FILE = "com.arflix.tv.data.repository.PartnerPreferencesKt"

/** The player's IPTV gate: ordinary sources never reach it, and Retry after a full account asks the panel again. */
@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28], manifest = Config.NONE, application = Application::class)
@ConscryptMode(ConscryptMode.Mode.OFF)
class IptvSecondaryRequestsTest {
    private val sources = mockk<StreamRepository>(relaxed = true)
    private val integrations = mockk<StreamIntegrationRepository>(relaxed = true)
    private val store = ViewModelStore()
    private lateinit var model: PlayerViewModel
    private val url = "https://cdn.example/movie/user/pass/1.mkv"
    private val account = IptvProviderAccount.xtream("http://panel.example", "user", "pass")
    private val panelAccount = iptvStreamPolicy(account, IptvConnectionLimit.AUTO, 4)

    @Before fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        mockkObject(MatroskaIndexSource)
        every { MatroskaIndexSource.prefetch(any(), any()) } just runs
        coEvery { MatroskaIndexSource.load(any(), any(), any()) } returns null
        model = PlayerViewModel(
            context = RuntimeEnvironment.getApplication(), profileManager = mockk(relaxed = true),
            mediaRepository = mockk(relaxed = true), streamRepository = sources,
            traktRepository = mockk(relaxed = true), remoteSyncManager = mockk(relaxed = true),
            watchHistoryRepository = mockk(relaxed = true), cloudSyncRepository = mockk(relaxed = true),
            launcherContinueWatchingRepository = mockk(relaxed = true), animeMapper = mockk(relaxed = true),
            tmdbApi = mockk(relaxed = true), skipIntroRepository = mockk(relaxed = true),
            playbackTelemetryRepository = mockk(relaxed = true), pluginManager = mockk(relaxed = true),
            streamIntegrationRepository = integrations, recentPlayedSourceRepository = mockk(relaxed = true),
            kidsContentFilter = mockk(relaxed = true),
            idleReturnCoordinator = com.arflix.tv.data.repository.IdleReturnCoordinator(mockk(relaxed = true), mockk(relaxed = true))
        )
        store.put("player", model)
    }

    @After fun cleanup() {
        val job = model.viewModelScope.coroutineContext[Job]
        try {
            store.clear()
            runBlocking { withTimeout(15_000) { job?.join() } }
        } finally {
            unmockkObject(MatroskaIndexSource)
            Dispatchers.resetMain()
        }
    }

    private suspend fun awaitState(what: (PlayerUiState) -> Boolean) =
        withTimeout(15_000) { model.uiState.first(what) }

    private fun withAdmission(block: suspend () -> Unit) {
        mockkObject(IptvAccountAvailability)
        // The real store reaches Hilt, which this bare application does not have.
        mockkStatic(EFFECTIVE_SETTINGS_FILE)
        try {
            every { any<Context>().effectiveSettingsDataStore } returns mockk {
                every { data } returns MutableStateFlow(emptyPreferences())
            }
            every { sources.installedAddons } returns MutableStateFlow(emptyList())
            every { integrations.getUnifiedSourceOrderedIds() } returns MutableStateFlow(emptyList())
            coEvery { sources.resolveStreamForPlayback(any()) } coAnswers { firstArg() }
            coEvery { sources.iptvStreamPolicyFor(any()) } returns panelAccount
            runBlocking { block() }
        } finally {
            unmockkStatic(EFFECTIVE_SETTINGS_FILE)
            unmockkObject(IptvAccountAvailability)
        }
    }

    @Test fun aSelectedSourceThatIsNotIptvAsksNoPolicyAndNoPanel() = withAdmission {
        model.selectStream(StreamSource("test", "test", "movie-addon", "1080p", "", url = url))
        awaitState { it.selectedStreamUrl == url }
        coVerify(exactly = 0) { sources.iptvStreamPolicyFor(any()) }
        coVerify(exactly = 0) { IptvAccountAvailability.admit(any()) }
    }

    @Test fun retryAfterAFullAccountAsksAgainAndPlayAnywayDoesNot() = withAdmission {
        coEvery { IptvAccountAvailability.admit(any()) } returns IptvAdmission.Full(1, 1)
        model.loadMedia(
            mediaType = MediaType.MOVIE, mediaId = 7, seasonNumber = null, episodeNumber = null,
            providedImdbId = null, providedStreamUrl = url, preferredAddonId = IptvVodSourceIds.XTREAM,
            preferredSourceName = "Movie", preferredBingeGroup = null, startPositionMs = null,
            providedIptvSourceId = "list_a"
        )
        awaitState { it.error != null }
        assertEquals(IPTV_ACCOUNT_BUSY_MESSAGE, model.uiState.value.error)
        assertEquals("a full account is not a failed source", url, model.uiState.value.selectedStream?.url)
        model.retry()
        awaitState { it.error != null }
        coVerify(exactly = 2) { IptvAccountAvailability.admit(any()) }
        assertNull(model.uiState.value.selectedStreamUrl)
        model.playIptvAnyway()
        awaitState { it.selectedStreamUrl == url && it.error == null }
        coVerify(exactly = 2) { IptvAccountAvailability.admit(any()) }
        assertEquals("list_a", model.uiState.value.selectedStream?.iptvSourceId)
    }
}
