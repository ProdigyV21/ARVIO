package com.arflix.tv.data.repository.sync

import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.repository.ContinueWatchingItem
import com.arflix.tv.data.repository.MdbListRepository
import javax.inject.Inject
import javax.inject.Singleton

/**
 * MDBList implementation of [RemoteSyncProvider]. Thin adapter over
 * [MdbListRepository]; the watched-state mirror (mark watched/unwatched) is
 * driven from the sync service, not here.
 */
@Singleton
class MdbListRemoteProvider @Inject constructor(
    private val repository: MdbListRepository
) : RemoteSyncProvider {

    override val provider: SyncProvider = SyncProvider.MDBLIST

    override suspend fun isConnected(): Boolean = repository.isConnected()

    override suspend fun addToWatchlist(mediaType: MediaType, tmdbId: Int): Boolean =
        repository.addToWatchlist(mediaType, tmdbId)

    override suspend fun removeFromWatchlist(mediaType: MediaType, tmdbId: Int): Boolean =
        repository.removeFromWatchlist(mediaType, tmdbId)

    override suspend fun getWatchlist(): RemoteWatchlistResult = repository.getWatchlist()

    override suspend fun scrobbleStart(
        mediaType: MediaType, tmdbId: Int, progress: Float, season: Int?, episode: Int?
    ) = repository.scrobble("start", mediaType, tmdbId, progress, season, episode)

    override suspend fun scrobblePause(
        mediaType: MediaType, tmdbId: Int, progress: Float, season: Int?, episode: Int?
    ) = repository.scrobble("pause", mediaType, tmdbId, progress, season, episode)

    override suspend fun scrobbleStop(
        mediaType: MediaType, tmdbId: Int, progress: Float, season: Int?, episode: Int?
    ) {
        repository.scrobble("stop", mediaType, tmdbId, progress, season, episode)
        if (progress >= com.arflix.tv.util.Constants.WATCHED_THRESHOLD) {
            if (mediaType == MediaType.MOVIE) {
                repository.markMovieWatched(tmdbId)
            } else if (season != null && episode != null) {
                repository.markEpisodeWatched(tmdbId, season, episode)
            }
            repository.clearPlayback(mediaType, tmdbId, season, episode)
        }
    }

    override suspend fun getWatchedMovies(): Set<Int> = repository.getWatchedMovies()

    override suspend fun getWatchedEpisodes(): Set<String> = repository.getWatchedEpisodes()

    override suspend fun getContinueWatching(forceRefresh: Boolean): List<ContinueWatchingItem> =
        repository.getContinueWatching()
}
