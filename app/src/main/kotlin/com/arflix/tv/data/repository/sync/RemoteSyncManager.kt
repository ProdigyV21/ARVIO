package com.arflix.tv.data.repository.sync

import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.repository.ContinueWatchingItem
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Single entry point the app uses to talk to whichever remote sync provider the
 * current profile is connected to. ViewModels and the sync service call this
 * instead of reaching for TraktRepository directly.
 *
 * Resolution is per-profile and preserves legacy behavior: profiles that
 * connected to Trakt before this feature existed have no `sync_provider` pref
 * (NONE) but do have a Trakt token, so NONE resolves to Trakt-when-connected.
 * MDBList is only active when explicitly selected AND a key is present.
 */
@Singleton
class RemoteSyncManager @Inject constructor(
    private val store: SyncProviderStore,
    private val traktProvider: TraktRemoteProvider,
    private val mdbListProvider: MdbListRemoteProvider,
    private val simklProvider: SimklRemoteProvider
) {
    /** The provider explicitly selected for this profile (may be NONE). */
    suspend fun selectedProvider(): SyncProvider = store.getProvider()

    /**
     * The active, connected provider for the current profile, or null when the
     * profile has no connected remote (local/Supabase-only).
     */
    suspend fun active(): RemoteSyncProvider? {
        val candidate = when (store.getProvider()) {
            SyncProvider.MDBLIST -> mdbListProvider
            SyncProvider.SIMKL -> simklProvider
            // TRAKT or NONE (legacy: infer Trakt from an existing token).
            SyncProvider.TRAKT, SyncProvider.NONE -> traktProvider
        }
        return candidate.takeIf { it.isConnected() }
    }

    suspend fun isRemoteConnected(): Boolean = active() != null

    // ===== Watchlist =====

    suspend fun addToWatchlist(mediaType: MediaType, tmdbId: Int): Boolean =
        active()?.addToWatchlist(mediaType, tmdbId) ?: false

    suspend fun removeFromWatchlist(mediaType: MediaType, tmdbId: Int): Boolean =
        active()?.removeFromWatchlist(mediaType, tmdbId) ?: false

    suspend fun getWatchlist(): RemoteWatchlistResult? = active()?.getWatchlist()

    // ===== Scrobble (no-op when no remote is connected) =====

    suspend fun scrobbleStart(
        mediaType: MediaType,
        tmdbId: Int,
        progress: Float,
        season: Int? = null,
        episode: Int? = null
    ) {
        active()?.scrobbleStart(mediaType, tmdbId, progress, season, episode)
    }

    suspend fun scrobblePause(
        mediaType: MediaType,
        tmdbId: Int,
        progress: Float,
        season: Int? = null,
        episode: Int? = null
    ) {
        active()?.scrobblePause(mediaType, tmdbId, progress, season, episode)
    }

    suspend fun scrobbleStop(
        mediaType: MediaType,
        tmdbId: Int,
        progress: Float,
        season: Int? = null,
        episode: Int? = null
    ) {
        active()?.scrobbleStop(mediaType, tmdbId, progress, season, episode)
    }

    // ===== Watched reads =====

    suspend fun getWatchedMovies(): Set<Int> = active()?.getWatchedMovies() ?: emptySet()

    suspend fun getWatchedEpisodes(): Set<String> = active()?.getWatchedEpisodes() ?: emptySet()

    // ===== Continue Watching =====

    suspend fun getContinueWatching(forceRefresh: Boolean = false): List<ContinueWatchingItem> =
        active()?.getContinueWatching(forceRefresh) ?: emptyList()
}
