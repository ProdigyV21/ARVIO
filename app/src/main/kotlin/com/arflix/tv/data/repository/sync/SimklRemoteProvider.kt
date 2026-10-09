package com.arflix.tv.data.repository.sync

import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.repository.ContinueWatchingItem
import com.arflix.tv.data.repository.TraktRepository
import com.arflix.tv.data.repository.simkl.SimklAuthManager
import com.arflix.tv.data.repository.simkl.SimklScrobbler
import com.arflix.tv.data.repository.simkl.SimklSyncService
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Simkl implementation of [RemoteSyncProvider].
 */
@Singleton
class SimklRemoteProvider @Inject constructor(
    private val authManager: SimklAuthManager,
    private val scrobbler: SimklScrobbler,
    private val syncService: SimklSyncService,
    private val traktRepository: TraktRepository
) : RemoteSyncProvider {

    override val provider: SyncProvider = SyncProvider.SIMKL

    override suspend fun isConnected(): Boolean = authManager.isConnected()

    override suspend fun addToWatchlist(mediaType: MediaType, tmdbId: Int, isAnime: Boolean): Boolean =
        syncService.addToWatchlist(mediaType, tmdbId, isAnime)

    override suspend fun removeFromWatchlist(mediaType: MediaType, tmdbId: Int, isAnime: Boolean): Boolean =
        syncService.removeFromWatchlist(mediaType, tmdbId, isAnime)

    override suspend fun getWatchlist(): RemoteWatchlistResult {
        val connected = isConnected()
        if (!connected) return RemoteWatchlistResult(connected = false, items = emptyList(), rawCount = 0)
        val items = syncService.getWatchlistItems()
        return RemoteWatchlistResult(
            connected = true,
            items = items,
            rawCount = items.size
        )
    }

    override suspend fun scrobbleStart(
        mediaType: MediaType,
        tmdbId: Int,
        progress: Float,
        season: Int?,
        episode: Int?,
        isAnime: Boolean
    ) {
        scrobbler.scrobbleStart(mediaType, tmdbId, progress, season, episode, isAnime)
    }

    override suspend fun scrobblePause(
        mediaType: MediaType,
        tmdbId: Int,
        progress: Float,
        season: Int?,
        episode: Int?,
        isAnime: Boolean
    ) {
        scrobbler.scrobblePause(mediaType, tmdbId, progress, season, episode, isAnime)
    }

    override suspend fun scrobbleProgress(
        mediaType: MediaType,
        tmdbId: Int,
        progress: Float,
        season: Int?,
        episode: Int?,
        isAnime: Boolean
    ) = Unit

    override suspend fun scrobbleStop(
        mediaType: MediaType,
        tmdbId: Int,
        progress: Float,
        season: Int?,
        episode: Int?,
        isAnime: Boolean
    ) {
        scrobbler.scrobbleStop(mediaType, tmdbId, progress, season, episode, isAnime)
    }

    override suspend fun getWatchedMovies(): Set<Int> = syncService.getWatchedMovies()

    override suspend fun getWatchedEpisodes(): Set<String> = syncService.getWatchedEpisodes()

    override suspend fun getContinueWatching(forceRefresh: Boolean): List<ContinueWatchingItem> {
        val (movies, episodes) = traktRepository.getLocalWatchedSnapshot()
        return syncService.getContinueWatching(forceRefresh, movies, episodes)
    }

    override suspend fun dismissContinueWatching(
        mediaType: MediaType,
        tmdbId: Int,
        season: Int?,
        episode: Int?
    ): Boolean = syncService.dismissContinueWatching(mediaType, tmdbId, season, episode)
}
