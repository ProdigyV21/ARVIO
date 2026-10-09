package com.arflix.tv.data.repository.sync

import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.repository.ContinueWatchingItem
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class RemoteSyncManagerTest {
    private val store = mockk<SyncProviderStore>()
    private val trakt = mockk<TraktRemoteProvider>()
    private val mdbList = mockk<MdbListRemoteProvider>()
    private val simkl = mockk<SimklRemoteProvider>()
    private lateinit var manager: RemoteSyncManager

    @Before
    fun setUp() {
        manager = RemoteSyncManager(store, trakt, mdbList, simkl)
        coEvery { trakt.isConnected() } returns true
        coEvery { simkl.isConnected() } returns true
        coEvery { mdbList.isConnected() } returns false
    }

    @Test
    fun bothWatchlistsAreMergedAndDeduplicated() = runBlocking {
        coEvery { store.readProviders(TrackingFeature.WATCHLIST) } returns
            setOf(SyncProvider.TRAKT, SyncProvider.SIMKL)
        coEvery { trakt.getWatchlist() } returns RemoteWatchlistResult(
            connected = true,
            items = listOf(MediaItem(id = 1, title = "One", mediaType = MediaType.MOVIE)),
            rawCount = 1
        )
        coEvery { simkl.getWatchlist() } returns RemoteWatchlistResult(
            connected = true,
            items = listOf(
                MediaItem(id = 1, title = "One", mediaType = MediaType.MOVIE),
                MediaItem(id = 2, title = "Two", mediaType = MediaType.TV)
            ),
            rawCount = 2
        )

        val result = manager.getWatchlist()

        assertEquals(listOf(1, 2), result?.items?.map { it.id })
        assertEquals(3, result?.rawCount)
    }

    @Test
    fun writesReachBothProvidersAndOneFailureDoesNotBlockTheOther() = runBlocking {
        coEvery { store.writeProviders() } returns setOf(SyncProvider.TRAKT, SyncProvider.SIMKL)
        coEvery { trakt.addToWatchlist(any(), any(), any()) } throws IllegalStateException("offline")
        coEvery { simkl.addToWatchlist(any(), any(), any()) } returns true

        assertTrue(manager.addToWatchlist(MediaType.TV, 99, isAnime = true))
        coVerify(exactly = 1) { trakt.addToWatchlist(MediaType.TV, 99, true) }
        coVerify(exactly = 1) { simkl.addToWatchlist(MediaType.TV, 99, true) }
    }

    @Test
    fun newestContinueWatchingEntryWinsAcrossProviders() = runBlocking {
        coEvery { store.readProviders(TrackingFeature.CONTINUE_WATCHING) } returns
            setOf(SyncProvider.TRAKT, SyncProvider.SIMKL)
        coEvery { trakt.getContinueWatching(false) } returns listOf(
            ContinueWatchingItem(7, "Show", MediaType.TV, 20, season = 1, episode = 1, updatedAtMs = 10)
        )
        coEvery { simkl.getContinueWatching(false) } returns listOf(
            ContinueWatchingItem(7, "Show", MediaType.TV, 55, season = 1, episode = 1, updatedAtMs = 20)
        )

        val result = manager.getContinueWatching()

        assertEquals(1, result.size)
        assertEquals(55, result.single().progress)
    }

    @Test(expected = IllegalStateException::class)
    fun failedContinueWatchingReadIsNotAnAuthoritativeEmptyResult(): Unit = runBlocking {
        coEvery { store.readProviders(TrackingFeature.CONTINUE_WATCHING) } returns setOf(SyncProvider.SIMKL)
        coEvery { simkl.getContinueWatching(false) } throws IllegalStateException("offline")
        manager.getContinueWatching()
    }

    @Test fun successfulEmptyContinueWatchingIsAuthoritative() = runBlocking {
        coEvery { store.readProviders(TrackingFeature.CONTINUE_WATCHING) } returns setOf(SyncProvider.SIMKL)
        coEvery { simkl.getContinueWatching(false) } returns emptyList()
        assertTrue(manager.getContinueWatching().isEmpty())
    }

    @Test(expected = IllegalStateException::class)
    fun emptyPartialSnapshotCannotClearProgressFromAFailedProvider(): Unit = runBlocking {
        coEvery { store.readProviders(TrackingFeature.CONTINUE_WATCHING) } returns setOf(SyncProvider.SIMKL, SyncProvider.TRAKT)
        coEvery { simkl.getContinueWatching(false) } throws IllegalStateException("offline")
        coEvery { trakt.getContinueWatching(false) } returns emptyList()
        manager.getContinueWatching()
    }

    @Test fun healthyProviderStillReturnsResultsWhenAnotherFails() = runBlocking {
        coEvery { store.readProviders(TrackingFeature.CONTINUE_WATCHING) } returns setOf(SyncProvider.SIMKL, SyncProvider.TRAKT)
        coEvery { simkl.getContinueWatching(false) } throws IllegalStateException("offline")
        coEvery { trakt.getContinueWatching(false) } returns listOf(ContinueWatchingItem(7, "Show", MediaType.TV, 0, season = 1, episode = 3, isUpNext = true))
        assertEquals(3, manager.getContinueWatching().single().episode)
    }

    @Test
    fun traktUpNextKeepsItsActivityOrderingWhenSimklHasNoTimestamp() = runBlocking {
        coEvery { store.readProviders(TrackingFeature.CONTINUE_WATCHING) } returns
            setOf(SyncProvider.TRAKT, SyncProvider.SIMKL)
        coEvery { trakt.getContinueWatching(false) } returns listOf(
            ContinueWatchingItem(
                7, "Show", MediaType.TV, 20,
                season = 1, episode = 10, updatedAtMs = 1_000
            )
        )
        coEvery { simkl.getContinueWatching(false) } returns listOf(
            ContinueWatchingItem(
                7, "Show", MediaType.TV, 55,
                season = 1, episode = 9, updatedAtMs = 0
            )
        )

        val result = manager.getContinueWatching()

        assertEquals(1, result.size)
        assertEquals(10, result.single().episode)
        assertEquals(20, result.single().progress)
    }

    @Test
    fun traktPlaybackKeepsItsActivityOrderingWhenSimklHasAnotherEpisode() = runBlocking {
        coEvery { store.readProviders(TrackingFeature.CONTINUE_WATCHING) } returns
            setOf(SyncProvider.TRAKT, SyncProvider.SIMKL)
        coEvery { trakt.getContinueWatching(false) } returns listOf(
            ContinueWatchingItem(
                8, "Movie", MediaType.MOVIE, 30, updatedAtMs = 2_000
            )
        )
        coEvery { simkl.getContinueWatching(false) } returns listOf(
            ContinueWatchingItem(
                8, "Movie", MediaType.MOVIE, 70, updatedAtMs = 0
            )
        )

        val result = manager.getContinueWatching()

        assertEquals(1, result.size)
        assertEquals(30, result.single().progress)
    }

    @Test
    fun continueWatchingConnectivityUsesItsOwnProviderSelection() = runBlocking {
        coEvery { store.readProviders(TrackingFeature.CONTINUE_WATCHING) } returns setOf(SyncProvider.TRAKT)

        assertTrue(manager.isRemoteConnected(TrackingFeature.CONTINUE_WATCHING))
        coVerify(exactly = 1) { trakt.isConnected() }
        coVerify(exactly = 0) { simkl.isConnected() }
    }

    @Test
    fun dismissContinueWatchingReachesEveryWriteProvider() = runBlocking {
        coEvery { store.writeProviders() } returns setOf(SyncProvider.TRAKT, SyncProvider.SIMKL)
        coEvery { trakt.dismissContinueWatching(any(), any(), any(), any()) } throws IllegalStateException("offline")
        coEvery { simkl.dismissContinueWatching(any(), any(), any(), any()) } returns true

        assertTrue(manager.dismissContinueWatching(MediaType.TV, 99, 2, 4))
        coVerify(exactly = 1) { trakt.dismissContinueWatching(MediaType.TV, 99, 2, 4) }
        coVerify(exactly = 1) { simkl.dismissContinueWatching(MediaType.TV, 99, 2, 4) }
    }
}
