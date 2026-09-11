package com.arflix.tv.ui.screens.watchlist

import android.content.Context
import androidx.lifecycle.ViewModelStore
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.repository.*
import com.arflix.tv.data.repository.simkl.SimklAuthManager
import com.arflix.tv.data.repository.sync.RemoteSyncManager
import io.mockk.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class WatchlistHomeLibraryTest {
    private val store = ViewModelStore()
    private val servers = MutableStateFlow<List<HomeServerConnection>>(emptyList())
    private val homes = mockk<HomeServerRepository>(relaxed = true)
    private val media = mockk<MediaRepository>(relaxed = true)
    private val cloud = mockk<CloudSyncRepository>(relaxed = true)
    private val watchlist = mockk<WatchlistRepository>(relaxed = true)
    private val history = mockk<WatchHistoryRepository>(relaxed = true)
    private lateinit var model: WatchlistViewModel

    private fun server(id: String, kind: HomeServerKind) = HomeServerConnection(
        connectionId = id, displayName = id, serverKind = kind,
        serverUrl = "https://example.invalid", userId = "test", accessToken = "test-only",
        collections = listOf(HomeServerCollection("movies", "Movies", "movies"))
    )

    @Before fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        every { homes.connections } returns servers
        every { homes.getSavedCatalogCandidates(any()) } answers {
            firstArg<List<HomeServerConnection>>().filter { it.isUsable }.flatMap { connection ->
                connection.collections.map { library -> HomeServerCatalogCandidate(
                    title = library.name, sourceRef = HomeServerRepository.buildCatalogSourceRef(connection, library),
                    serverName = connection.displayName, collectionName = library.name,
                    collectionType = library.type, serverKind = connection.serverKind,
                    connectionId = connection.connectionId)
                }
            }
        }
        // The old implementation waited here before publishing any provider tabs.
        coEvery { homes.getCatalogCandidates() } coAnswers { awaitCancellation() }
        coEvery { cloud.pullFromCloud() } coAnswers { awaitCancellation() }
        every { watchlist.watchlistItems } returns MutableStateFlow(emptyList())
        coEvery { watchlist.getLocalWatchlistItems() } returns emptyList()
        coEvery { history.getWatchHistory() } returns emptyList()
        coEvery { media.getLogoUrl(any(), any()) } returns null
        coEvery { media.loadHomeServerLibraryPage(any(), any(), any(), any(), any(), any()) } coAnswers {
            awaitCancellation()
        }
        val trakt = mockk<TraktRepository>(relaxed = true) {
            every { isAuthenticated } returns MutableStateFlow(false)
        }
        val profiles = mockk<ProfileManager>(relaxed = true) {
            every { activeProfileId } returns MutableStateFlow("profile")
        }
        val catalogs = mockk<CatalogRepository>(relaxed = true) {
            every { observeCatalogs() } returns MutableStateFlow(emptyList())
        }
        val simkl = mockk<SimklAuthManager>(relaxed = true)
        coEvery { simkl.isConnected() } returns false
        val remote = mockk<RemoteSyncManager>(relaxed = true)
        coEvery { remote.isRemoteConnected(any()) } returns false
        model = WatchlistViewModel(mockk<Context>(relaxed = true), watchlist, cloud, trakt,
            remote, media, homes, catalogs, history, simkl, mockk(relaxed = true), profiles)
        store.put("library", model)
    }

    @After fun tearDown() {
        store.clear()
        Dispatchers.resetMain()
    }

    @Test fun allTabsPublishFromSnapshotWhileNetworkAndCloudAreStalled() {
        servers.value = listOf(server("Jellyfin A", HomeServerKind.JELLYFIN),
            server("Jellyfin B", HomeServerKind.JELLYFIN), server("Emby A", HomeServerKind.EMBY),
            server("Emby B", HomeServerKind.EMBY), server("Plex", HomeServerKind.PLEX))
        assertEquals(listOf(HomeServerKind.PLEX, HomeServerKind.JELLYFIN, HomeServerKind.EMBY),
            model.libraryState.value.providers)
        assertEquals(5, model.libraryState.value.libraries.size)
        assertEquals(5, model.uiState.value.sources.filterIsInstance<WatchlistSourceItem.HomeServer>().size)
        coVerify(exactly = 0) { homes.getCatalogCandidates() }
        coVerify(exactly = 0) { media.loadHomeServerLibraryPage(any(), any(), any(), any(), any(), any()) }
        coVerify(exactly = 1) { cloud.pullFromCloud() }
    }

    @Test fun slowSelectedServerDoesNotBlockOtherProviders() {
        servers.value = listOf(server("JF", HomeServerKind.JELLYFIN), server("Emby", HomeServerKind.EMBY))
        model.selectLibraryProvider(HomeServerKind.JELLYFIN)
        assertTrue(model.libraryState.value.isLoading)
        model.selectLibraryProvider(HomeServerKind.EMBY)
        assertEquals(HomeServerKind.EMBY, model.libraryState.value.selectedProvider)
        assertEquals(2, model.libraryState.value.providers.size)
        assertNull(model.libraryState.value.error)
        model.selectLibraryProvider(null)
        assertFalse(model.libraryState.value.isLoading)
        assertNull(model.libraryState.value.error)
    }

    @Test fun serverWithNoSavedLibrariesStillHasTabAndLoadsWhenLibrariesArrive() {
        val server = server("JF", HomeServerKind.JELLYFIN)
        servers.value = listOf(server.copy(collections = emptyList()))
        assertEquals(listOf(HomeServerKind.JELLYFIN), model.libraryState.value.providers)
        model.selectLibraryProvider(HomeServerKind.JELLYFIN)
        assertFalse(model.libraryState.value.isLoading)
        servers.value = listOf(server)
        assertNotNull(model.libraryState.value.selectedSourceRef)
        assertTrue(model.libraryState.value.isLoading)
        coVerify(exactly = 1) { media.loadHomeServerLibraryPage(any(), any(), any(), any(), any(), any()) }
    }

    @Test fun lateResultCannotRepopulateWatchlistAfterLeavingServer() = runBlocking {
        val release = CompletableDeferred<Unit>()
        val finished = CompletableDeferred<Unit>()
        coEvery { media.loadHomeServerLibraryPage(any(), any(), any(), any(), any(), any()) } coAnswers {
            withContext(NonCancellable) { release.await() }
            finished.complete(Unit)
            MediaRepository.CategoryPageResult(listOf(MediaItem(id = 1, title = "Old result")), false)
        }
        servers.value = listOf(server("JF", HomeServerKind.JELLYFIN))
        model.selectLibraryProvider(HomeServerKind.JELLYFIN)
        model.selectLibraryProvider(null)
        release.complete(Unit)
        withTimeout(5_000) { finished.await() }
        assertNull(model.libraryState.value.selectedProvider)
        assertTrue(model.libraryState.value.items.isEmpty())
        assertFalse(model.libraryState.value.isLoading)
        assertNull(model.libraryState.value.error)
    }

    @Test fun removingSelectedServerCancelsLoadAndClearsOnlyThatSelection() {
        val jf = server("JF", HomeServerKind.JELLYFIN)
        val emby = server("Emby", HomeServerKind.EMBY)
        servers.value = listOf(jf, emby)
        model.selectLibraryProvider(HomeServerKind.JELLYFIN)
        servers.value = listOf(jf.copy(enabled = false), emby)
        assertEquals(listOf(HomeServerKind.EMBY), model.libraryState.value.providers)
        assertNull(model.libraryState.value.selectedProvider)
        assertNull(model.libraryState.value.selectedSourceRef)
        assertTrue(model.libraryState.value.items.isEmpty())
        assertFalse(model.libraryState.value.isLoading)
        assertNull(model.libraryState.value.error)
    }

    @Test fun removedLibraryFallsBackToRemainingLibraryOfSelectedProvider() {
        val first = server("JF A", HomeServerKind.JELLYFIN)
        val second = server("JF B", HomeServerKind.JELLYFIN)
        servers.value = listOf(first, second)
        model.selectLibraryProvider(HomeServerKind.JELLYFIN)
        servers.value = listOf(second)
        assertEquals(HomeServerRepository.buildCatalogSourceRef(second, second.collections.single()),
            model.libraryState.value.selectedSourceRef)
        assertTrue(model.libraryState.value.isLoading)
    }
}
