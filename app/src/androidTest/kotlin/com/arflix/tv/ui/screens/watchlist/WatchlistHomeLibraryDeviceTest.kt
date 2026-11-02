package com.arflix.tv.ui.screens.watchlist

import android.graphics.Bitmap
import android.os.SystemClock
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModelStore
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.arflix.tv.data.repository.*
import com.arflix.tv.data.repository.simkl.SimklAuthManager
import com.arflix.tv.data.repository.sync.RemoteSyncManager
import com.arflix.tv.util.DeviceType
import com.arflix.tv.util.LocalDeviceType
import com.google.gson.Gson
import io.mockk.*
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import org.junit.After
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.File
import java.util.UUID

@OptIn(ExperimentalTestApi::class)
@RunWith(AndroidJUnit4::class)
class WatchlistHomeLibraryDeviceTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private val store = ViewModelStore()
    private val client = mockk<OkHttpClient>()
    private lateinit var model: WatchlistViewModel
    private lateinit var loadSavedLibraries: () -> Unit

    @After fun cleanup() { compose.runOnUiThread { store.clear() } }

    @Test fun tvSavedSourcesRemainNavigableWhileServerIsStalled() {
        show(DeviceType.TV)
        compose.onNodeWithText("Homeserver").performClick()
        choose("Jellyfin A")
        compose.runOnIdle { assertEquals(HomeServerKind.JELLYFIN, model.libraryState.value.selectedProvider); assertTrue(model.libraryState.value.isLoading) }
        choose("Emby B")
        compose.runOnIdle { assertEquals(HomeServerKind.EMBY, model.libraryState.value.selectedProvider); assertNull(model.libraryState.value.error) }
        verify { client wasNot Called }
    }
    @Test fun mobileCanSwitchSavedSourcesDuringLoading() {
        show(DeviceType.PHONE)
        compose.onNodeWithText("Homeserver").performClick()
        choose("Jellyfin A"); choose("Emby A")
        compose.runOnIdle { assertEquals(HomeServerKind.EMBY, model.libraryState.value.selectedProvider); assertTrue(model.libraryState.value.isLoading) }
        verify { client wasNot Called }
    }

    @Test fun librariesArrivingAfterOpeningHomeserverAreSelectedAutomatically() {
        show(DeviceType.PHONE, initiallyConnected = false)
        compose.onNodeWithText("Homeserver").performClick()
        compose.runOnIdle { assertNull(model.libraryState.value.selectedSourceRef) }
        loadSavedLibraries()
        compose.waitUntil(timeoutMillis = 3_000) { model.libraryState.value.selectedSourceRef != null }
        compose.runOnIdle {
            assertEquals(HomeServerKind.JELLYFIN, model.libraryState.value.selectedProvider)
            assertTrue(model.libraryState.value.isLoading)
        }
        choose("Emby B")
        compose.runOnIdle { assertEquals(HomeServerKind.EMBY, model.libraryState.value.selectedProvider) }
        verify { client wasNot Called }
    }
    private fun choose(server: String) {
        val candidate = model.libraryState.value.libraries.first { it.serverName == server }
        if(compose.onAllNodesWithTag("library-source-picker").fetchSemanticsNodes().isNotEmpty()) compose.onNodeWithTag("library-source-picker").performClick()
        val tag = "library-source-server_${candidate.sourceRef}"
        compose.onNodeWithTag("library-sources").performScrollToNode(hasTestTag(tag))
        compose.onNodeWithTag(tag).performClick()
        compose.waitForIdle()
    }

    private fun show(device: DeviceType, initiallyConnected: Boolean = true) {
        val profile = "library-device-${UUID.randomUUID()}"
        val profiles = mockk<ProfileManager>(relaxed = true) {
            every { activeProfileId } returns MutableStateFlow(profile)
            coEvery { getProfileId() } returns profile
            every { profileStringKeyFor(any(), any()) } answers {
                stringPreferencesKey("${firstArg<String>()}_${secondArg<String>()}")
            }
        }
        val homes = HomeServerRepository(compose.activity.applicationContext, client, profiles)
        val connections = listOf(
            server("Jellyfin A", HomeServerKind.JELLYFIN), server("Jellyfin B", HomeServerKind.JELLYFIN),
            server("Emby A", HomeServerKind.EMBY), server("Emby B", HomeServerKind.EMBY),
            server("Plex", HomeServerKind.PLEX))
        // Exercise the real encrypted settings snapshot, not a pre-populated UI state.
        loadSavedLibraries = {
            runBlocking { homes.importCloudConnectionsJsonForProfile(profile, Gson().toJson(connections)) }
        }
        if (initiallyConnected) loadSavedLibraries()
        val watchlist = mockk<WatchlistRepository>(relaxed = true) {
            every { watchlistItems } returns MutableStateFlow(emptyList())
        }
        coEvery { watchlist.getLocalWatchlistItems() } returns emptyList()
        val cloud = mockk<CloudSyncRepository>(relaxed = true)
        coEvery { cloud.pullFromCloud() } coAnswers { awaitCancellation() }
        val trakt = mockk<TraktRepository>(relaxed = true) {
            every { isAuthenticated } returns MutableStateFlow(false)
        }
        val remote = mockk<RemoteSyncManager>(relaxed = true)
        coEvery { remote.isRemoteConnected(any()) } returns false
        val media = mockk<MediaRepository>(relaxed = true)
        coEvery { media.loadHomeServerLibraryPage(any(), any(), any(), any(), any(), any()) } coAnswers {
            awaitCancellation()
        }
        val catalogs = mockk<CatalogRepository>(relaxed = true) {
            every { observeCatalogs() } returns MutableStateFlow(emptyList())
        }
        val history = mockk<WatchHistoryRepository>(relaxed = true)
        coEvery { history.getWatchHistory() } returns emptyList()
        val simkl = mockk<SimklAuthManager>(relaxed = true)
        coEvery { simkl.isConnected() } returns false
        val started = SystemClock.elapsedRealtime()
        compose.runOnUiThread {
            model = WatchlistViewModel(compose.activity.applicationContext, watchlist, cloud, trakt,
                remote, media, homes, catalogs, history, mockk(relaxed = true), simkl, mockk(relaxed = true), profiles,
                personalLists(), mockk(relaxed = true))
            store.put("library", model)
        }
        compose.setContent {
            CompositionLocalProvider(LocalDeviceType provides device) { WatchlistScreen(viewModel = model) }
        }
        compose.waitUntil(timeoutMillis = 3_000) {
            model.libraryState.value.providers.size == if (initiallyConnected) 3 else 0
        }
        assertEquals(if (initiallyConnected) 10 else 0, model.libraryState.value.libraries.size)
        verify { client wasNot Called }
    }

    private fun server(id: String, kind: HomeServerKind) = HomeServerConnection(
        connectionId = id, serverId = id, displayName = id, serverKind = kind,
        serverUrl = "https://example.invalid", userId = "test", accessToken = "test-only",
        collections = listOf(HomeServerCollection("movies", "Movies", "movies"),
            HomeServerCollection("shows", "Series", "tvshows"))
    )

    private fun keys(keys: List<Key>) {
        keys.forEach { key -> compose.onRoot().performKeyInput { pressKey(key) }; compose.waitForIdle() }
    }

    private fun capture(name: String) {
        val bitmap = compose.onRoot().captureToImage().asAndroidBitmap()
        File(compose.activity.getExternalFilesDir(null), "$name.png").outputStream().use {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)
        }
    }

    // A relaxed mock's `changes` flow completes at once, which the ViewModel's never-ending
    // collector reports as an uncaught exception; a real (empty) flow behaves like production.
    private fun personalLists() = mockk<com.arflix.tv.data.repository.PersonalListsRepository>(relaxed = true) {
        every { changes } returns kotlinx.coroutines.flow.MutableSharedFlow()
    }
}
