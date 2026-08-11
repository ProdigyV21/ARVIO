package com.arflix.tv.data.repository.sync

import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.repository.ContinueWatchingItem
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
    private val syncService: SimklSyncService
) : RemoteSyncProvider {

    override val provider: SyncProvider = SyncProvider.SIMKL

    override suspend fun isConnected(): Boolean = authManager.isConnected()

    override suspend fun addToWatchlist(mediaType: MediaType, tmdbId: Int): Boolean =
        syncService.addToWatchlist(mediaType, tmdbId)

    override suspend fun removeFromWatchlist(mediaType: MediaType, tmdbId: Int): Boolean =
        syncService.removeFromWatchlist(mediaType, tmdbId)

    override suspend fun getWatchlist(): RemoteWatchlistResult {
        val connected = isConnected()
        return RemoteWatchlistResult(
            connected = connected,
            items = emptyList(),
            rawCount = 0
        )
    }

    override suspend fun scrobbleStart(
        mediaType: MediaType,
        tmdbId: Int,
        progress: Float,
        season: Int?,
        episode: Int?
    ) {
        scrobbler.scrobbleStart(mediaType, tmdbId, progress, season, episode)
    }

    override suspend fun scrobblePause(
        mediaType: MediaType,
        tmdbId: Int,
        progress: Float,
        season: Int?,
        episode: Int?
    ) {
        scrobbler.scrobblePause(mediaType, tmdbId, progress, season, episode)
    }

    override suspend fun scrobbleStop(
        mediaType: MediaType,
        tmdbId: Int,
        progress: Float,
        season: Int?,
        episode: Int?
    ) {
        scrobbler.scrobbleStop(mediaType, tmdbId, progress, season, episode)
    }

    override suspend fun getWatchedMovies(): Set<Int> = syncService.getWatchedMovies()

    override suspend fun getWatchedEpisodes(): Set<String> = syncService.getWatchedEpisodes()

    override suspend fun getContinueWatching(forceRefresh: Boolean): List<ContinueWatchingItem> =
        emptyList()
}
