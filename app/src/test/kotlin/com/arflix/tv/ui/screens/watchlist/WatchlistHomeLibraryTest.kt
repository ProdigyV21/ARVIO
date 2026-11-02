package com.arflix.tv.ui.screens.watchlist

import android.content.Context
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewModelScope
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.CatalogConfig
import com.arflix.tv.data.model.CatalogSourceType
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
    private val catalogItems = MutableStateFlow<List<CatalogConfig>>(emptyList())
    private val traktAuth = MutableStateFlow(false)
    private val trakt = mockk<TraktRepository>(relaxed = true)
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
        coEvery { media.getLogoUrl(any<MediaItem>()) } returns null
        coEvery { media.loadHomeServerLibraryPage(any(), any(), any(), any(), any(), any()) } coAnswers {
            awaitCancellation()
        }
        every { trakt.isAuthenticated } returns traktAuth
        val profiles = mockk<ProfileManager>(relaxed = true) {
            every { activeProfileId } returns MutableStateFlow("profile")
        }
        val catalogs = mockk<CatalogRepository>(relaxed = true) {
            every { observeCatalogs() } returns catalogItems
        }
        val simkl = mockk<SimklAuthManager>(relaxed = true)
        coEvery { simkl.isConnected() } returns false
        val remote = mockk<RemoteSyncManager>(relaxed = true)
        coEvery { remote.isRemoteConnected(any()) } returns false
        model = WatchlistViewModel(mockk<Context>(relaxed = true), watchlist, cloud, trakt,
            remote, media, homes, catalogs, history, mockk(relaxed = true), simkl, mockk(relaxed = true), profiles,
            personalLists(), mockk(relaxed = true))
        store.put("library", model)
    }

    @After fun tearDown() {
        val job = model.viewModelScope.coroutineContext[kotlinx.coroutines.Job]
        try {
            store.clear()
            // IO children must finish cancellation before Main is removed.
            runBlocking { withTimeout(5_000) { job?.join() } }
        } finally {
            Dispatchers.resetMain()
        }
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

    @Test fun everyServerProviderLoadsPastSixtyAndKeepsLoadedPagesOnReturn() {
        coEvery { media.loadHomeServerLibraryPage(any(), any(), any(), any(), any(), any()) } answers {
            val offset = arg<Int>(1)
            val end = (offset + 60).coerceAtMost(185)
            MediaRepository.CategoryPageResult((offset until end).map { MediaItem(id = it + 1, title = "Movie $it") }, end < 185, end)
        }
        servers.value = listOf(server("JF", HomeServerKind.JELLYFIN), server("Plex", HomeServerKind.PLEX), server("Emby", HomeServerKind.EMBY))
        listOf(HomeServerKind.JELLYFIN, HomeServerKind.PLEX, HomeServerKind.EMBY).forEach { kind ->
            model.selectLibraryProvider(kind)
            repeat(3) { model.loadMoreLibrary() }
            assertEquals(185, model.libraryState.value.items.size)
            assertFalse(model.libraryState.value.hasMore)
        }
        model.selectLibraryProvider(HomeServerKind.JELLYFIN)
        assertEquals(185, model.libraryState.value.items.size)
    }

    @Test fun serverCursorAdvancesEvenWhenPageContainsDuplicates() {
        coEvery { media.loadHomeServerLibraryPage(any(), any(), any(), any(), any(), any()) } answers {
            val offset = arg<Int>(1)
            MediaRepository.CategoryPageResult(listOf(MediaItem(id = if(offset < 120) 1 else 2, title = "Movie")), offset < 120, offset + 60)
        }
        servers.value = listOf(server("JF", HomeServerKind.JELLYFIN))
        model.selectLibraryProvider(HomeServerKind.JELLYFIN)
        model.loadMoreLibrary()
        model.loadMoreLibrary()
        assertEquals(2, model.libraryState.value.items.size)
        assertFalse(model.libraryState.value.hasMore)
        coVerify { media.loadHomeServerLibraryPage(any(), 120, any(), any(), any(), any()) }
    }

    @Test fun customListsPageBeyondTheOld120ItemLimit() {
        val catalog = CatalogConfig("large-list", "Large list", CatalogSourceType.TRAKT)
        coEvery { media.loadCustomCatalogPage(any(), any(), any()) } answers {
            val offset = arg<Int>(1)
            val end = (offset + 60).coerceAtMost(305)
            MediaRepository.CategoryPageResult((offset until end).map { MediaItem(id = it + 1, title = "Movie $it") }, end < 305, end)
        }
        catalogItems.value = listOf(catalog)
        model.selectSource("catalog_large-list")
        repeat(5) { model.loadMoreActiveSource() }
        assertEquals(305, model.uiState.value.allItems.size)
        assertFalse(model.uiState.value.hasMore)
    }

    @Test fun fastLogoIsPublishedWhileAnotherLogoIsStillPending() = runBlocking {
        val release = CompletableDeferred<Unit>()
        val slow = MediaItem(id = 800, title = "Slow")
        val fast = MediaItem(id = 801, title = "Fast")
        coEvery { media.getLogoUrl(any<MediaItem>()) } coAnswers {
            if (firstArg<MediaItem>().id == slow.id) release.await()
            "https://example.invalid/${firstArg<MediaItem>().id}.png"
        }
        model.prefetchLogos(listOf(slow, fast))
        assertTrue(model.logoUrls.value.containsKey(watchlistLogoKey(fast)))
        assertFalse(model.logoUrls.value.containsKey(watchlistLogoKey(slow)))
        release.complete(Unit)
        assertTrue(model.logoUrls.value.containsKey(watchlistLogoKey(slow)))
    }

    @Test fun serverPreviewDoesNotWaitForRatingAndCachesTheRealImdbResult() = runBlocking {
        val item = MediaItem(42, "Movie", overview = "Server description", isHomeServer = true,
            homeServerSourceRef = "jellyfin", homeServerItemId = "native-42", tmdbRating = "7.4")
        coEvery { media.getImdbRating(any<MediaItem>()) } returns "8.1"
        val preview = model.loadLibraryPreview(item)
        assertEquals("Server description", preview.item.overview)
        assertEquals("", preview.item.imdbRating)
        coVerify(exactly = 0) { media.getImdbRating(any<MediaItem>()) }
        assertEquals("8.1", model.loadLibraryImdbRating(item))
        val enriched = model.loadLibraryPreview(item)
        assertEquals("8.1", enriched.item.imdbRating)
        assertEquals("native-42", enriched.item.homeServerItemId)
        assertEquals("Server description", enriched.item.overview)
    }

    @Test fun missingImdbRatingDoesNotOverwriteTheProviderPreview() = runBlocking {
        val item = MediaItem(-42, "Movie", overview = "Server description", isHomeServer = true,
            homeServerSourceRef = "emby", homeServerItemId = "native-42")
        coEvery { media.getImdbRating(any<MediaItem>()) } returns null
        model.loadLibraryPreview(item)
        assertNull(model.loadLibraryImdbRating(item))
        assertEquals(item, model.loadLibraryPreview(item).item)
    }

    @Test fun trackerShowsAllItemsBeforeSlowArtworkHydration() {
        coEvery { trakt.getPersonalLists() } returns emptyList()
        coEvery { trakt.getWatchlist() } returns (1..605).map { MediaItem(id = it, title = "Movie $it") }
        coEvery { media.getMovieDetails(any()) } coAnswers { awaitCancellation() }
        traktAuth.value = true
        model.selectSource("tracker_trakt___watchlist__")
        assertEquals(605, model.uiState.value.allItems.size)
        assertFalse(model.uiState.value.isLoading)
    }

    @Test fun temporaryLogoFailureCanRetryOnTheNextViewportVisit() {
        val item = MediaItem(id = 900, title = "Retry")
        coEvery { media.getLogoUrl(item) } returnsMany listOf(null, "https://example.invalid/logo.png")
        model.prefetchLogos(listOf(item))
        assertFalse(model.logoUrls.value.containsKey(watchlistLogoKey(item)))
        model.prefetchLogos(listOf(item))
        assertTrue(model.logoUrls.value.containsKey(watchlistLogoKey(item)))
    }

    // A relaxed mock's `changes` flow completes at once, which the ViewModel's never-ending
    // collector reports as an uncaught exception; a real (empty) flow behaves like production.
    private fun personalLists() = mockk<com.arflix.tv.data.repository.PersonalListsRepository>(relaxed = true) {
        every { changes } returns kotlinx.coroutines.flow.MutableSharedFlow()
    }
}
