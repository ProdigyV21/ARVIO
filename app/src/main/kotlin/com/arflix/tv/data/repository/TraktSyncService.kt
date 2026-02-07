package com.arflix.tv.data.repository

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.arflix.tv.data.api.*
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.util.Constants
import com.arflix.tv.util.traktDataStore
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.delay
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.sync.withPermit
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.time.Instant
import java.util.concurrent.atomic.AtomicInteger
import javax.inject.Inject
import javax.inject.Singleton

/**
 * TraktSyncService - Manages synchronization between Trakt and Supabase
 *
 * This service ensures Supabase is the source of truth for watched state:
 * 1. Full sync: Imports all watched data from Trakt to Supabase
 * 2. Incremental sync: Uses Trakt's last_activities to sync only changes
 * 3. Two-way sync: Pushes local changes to Trakt
 *
 * Key tables in Supabase:
 * - watched_movies: Movies marked as watched
 * - watched_episodes: Episodes marked as watched
 * - episode_progress: In-progress playback state
 * - sync_state: Tracks sync timestamps and status
 */
@Singleton
class TraktSyncService @Inject constructor(
    @ApplicationContext private val context: Context,
    private val traktApi: TraktApi,
    private val supabaseApi: SupabaseApi,
    private val authRepository: AuthRepository,
    private val outboxRepository: TraktOutboxRepository,
    private val profileManager: ProfileManager
) {
    private val TAG = "TraktSyncService"
    private val gson = Gson()
    private val clientId = Constants.TRAKT_CLIENT_ID
    private val clientSecret = Constants.TRAKT_CLIENT_SECRET

    // Sync progress state
    private val _syncProgress = MutableStateFlow(SyncProgress())
    val syncProgress: StateFlow<SyncProgress> = _syncProgress

    private val _isSyncing = MutableStateFlow(false)
    val isSyncing: StateFlow<Boolean> = _isSyncing

    private val _syncEvents = MutableSharedFlow<SyncStatus>(extraBufferCapacity = 1)
    val syncEvents: SharedFlow<SyncStatus> = _syncEvents.asSharedFlow()

    // Profile-scoped DataStore keys (must match TraktRepository for token sharing)
    private fun accessTokenKey() = profileManager.profileStringKey("trakt_access_token")
    private fun refreshTokenKey() = profileManager.profileStringKey("trakt_refresh_token")
    private fun expiresAtKey() = profileManager.profileLongKey("trakt_expires_at")

    // In-memory cache for current session (fallback if Supabase fails)
    private var cachedWatchedMovies: List<WatchedMovieRecord>? = null
    private var cachedWatchedEpisodes: List<WatchedEpisodeRecord>? = null

    /**
     * Perform a full sync from Trakt to Supabase
     * This imports ALL watched movies and episodes, overwriting existing data
     */

    suspend fun performFullSync(): SyncResult = withContext(Dispatchers.IO) {
        if (_isSyncing.value) {
            return@withContext SyncResult.Error("Sync already in progress")
        }

        _isSyncing.value = true
        _syncProgress.value = SyncProgress(status = SyncStatus.STARTING, message = "Starting full sync...")

        val completionThreshold = Constants.WATCHED_THRESHOLD / 100f

        try {
            val userId = getUserId()
            val localUserId = userId ?: "local"
            // Allow sync even if Supabase auth is missing - we'll use local cache
            val hasSupabase = userId != null && getSupabaseAuth() != null

            if (hasSupabase) {
                try {
                    val supabaseUserId = userId ?: return@withContext SyncResult.Error("Not logged in")
                    updateSyncState(supabaseUserId, syncInProgress = true, lastError = null)
                } catch (e: Exception) {
                }
            }

            var totalMovies = 0
            var totalEpisodes = 0

            _syncProgress.value = SyncProgress(
                status = SyncStatus.SYNCING_MOVIES,
                message = "Fetching watched movies..."
            )

            val watchedMovies = fetchAllWatchedMovies()
            val (movieRecords, filteredMovies) = buildWatchedMoviesFromWatchedList(localUserId, watchedMovies)

            // Update cache
            cachedWatchedMovies = movieRecords

            if (hasSupabase) {
                movieRecords.chunked(100).forEachIndexed { index, chunk ->
                    _syncProgress.value = SyncProgress(
                        status = SyncStatus.SYNCING_MOVIES,
                        message = "Saving movies... (${index * 100 + chunk.size}/${movieRecords.size})",
                        moviesProcessed = index * 100 + chunk.size,
                        totalMovies = movieRecords.size
                    )
                    if (chunk.isNotEmpty()) {
                        try {
                            executeSupabaseCall("bulk upsert watched movies") { auth ->
                                supabaseApi.bulkUpsertWatchedMovies(auth, records = chunk)
                            }
                        } catch (e: Exception) {
                        }
                    }
                }
            }
            totalMovies = movieRecords.size

            _syncProgress.value = SyncProgress(
                status = SyncStatus.SYNCING_EPISODES,
                message = "Fetching watched episodes...",
                moviesProcessed = totalMovies,
                totalMovies = totalMovies
            )

            val watchedShows = fetchAllWatchedShows()
            val totalEpisodeItems = watchedShows.sumOf { show ->
                show.seasons?.sumOf { it.episodes.size } ?: 0
            }
            val totalPlays = watchedShows.sumOf { it.plays }
            val useProgressExpansion = totalEpisodeItems < totalPlays
            val (episodeRecords, filteredEpisodes) = if (useProgressExpansion) {
                // PERFORMANCE: Only expand progress for top 15 most recently watched shows
                // This prevents 30+ API calls on startup while still getting recent watch data
                // Old shows should already have data from previous syncs
                val recentShows = watchedShows.sortedByDescending { it.lastWatchedAt }.take(15)
                buildWatchedEpisodesFromShowProgress(localUserId, recentShows)
            } else {
                buildWatchedEpisodesFromWatchedShows(localUserId, watchedShows)
            }

            // Update cache
            cachedWatchedEpisodes = episodeRecords

            if (hasSupabase) {
                episodeRecords.chunked(100).forEachIndexed { index, chunk ->
                    _syncProgress.value = SyncProgress(
                        status = SyncStatus.SYNCING_EPISODES,
                        message = "Saving episodes... (${index * 100 + chunk.size}/${episodeRecords.size})",
                        moviesProcessed = totalMovies,
                        totalMovies = totalMovies,
                        episodesProcessed = index * 100 + chunk.size,
                        totalEpisodes = episodeRecords.size
                    )
                    if (chunk.isNotEmpty()) {
                        try {
                            executeSupabaseCall("bulk upsert watched episodes") { auth ->
                                supabaseApi.bulkUpsertWatchedEpisodes(auth, records = chunk)
                            }
                        } catch (e: Exception) {
                        }
                    }
                }
            }
            totalEpisodes = episodeRecords.size

            _syncProgress.value = SyncProgress(
                status = SyncStatus.SYNCING_PROGRESS,
                message = "Fetching playback progress...",
                moviesProcessed = totalMovies,
                totalMovies = totalMovies,
                episodesProcessed = totalEpisodes,
                totalEpisodes = totalEpisodes
            )

            val playbackItems = fetchAllPlaybackProgress()
            val progressRecords = buildWatchHistoryFromPlayback(localUserId, playbackItems, completionThreshold, "trakt")

            if (hasSupabase) {
                progressRecords.chunked(100).forEach { chunk ->
                    chunk.forEach { record ->
                        try {
                            executeSupabaseCall("upsert watch history") { auth ->
                                supabaseApi.upsertWatchHistory(auth = auth, item = record)
                            }
                        } catch (e: Exception) {
                        }
                    }
                }

                try {
                    cleanupTraktPlaybackProgress(localUserId, progressRecords)
                } catch (e: Exception) {
                }
            }
            flushOutbox()

            // Non-critical: fetch last activities for incremental sync optimization
            // Don't fail the sync if this call fails - data is already synced
            if (hasSupabase) {
                try {
                    val activities = executeTraktCall("last activities") { auth ->
                        traktApi.getLastActivities(auth, clientId)
                    }
                    val activitiesJson = gson.toJson(activities)
                    val supabaseUserId = userId ?: return@withContext SyncResult.Success(totalMovies, totalEpisodes)
                    updateSyncState(
                        userId = supabaseUserId,
                        lastSyncAt = Instant.now().toString(),
                        lastFullSyncAt = Instant.now().toString(),
                        lastTraktActivitiesJson = activitiesJson,
                        moviesSynced = totalMovies,
                        episodesSynced = totalEpisodes,
                        syncInProgress = false
                    )
                } catch (e: Exception) {
                    // Silently ignore - sync data is already cached locally
                }
            }

            _syncProgress.value = SyncProgress(
                status = SyncStatus.COMPLETED,
                message = "Sync completed!",
                moviesProcessed = totalMovies,
                totalMovies = totalMovies,
                episodesProcessed = totalEpisodes,
                totalEpisodes = totalEpisodes
            )
            _syncEvents.tryEmit(SyncStatus.COMPLETED)

            SyncResult.Success(totalMovies, totalEpisodes)

        } catch (e: Exception) {
            _syncProgress.value = SyncProgress(
                status = SyncStatus.ERROR,
                message = "Sync failed: ${e.message}"
            )

            try {
                val userId = getUserId()
                if (userId != null && getSupabaseAuth() != null) {
                    updateSyncState(
                        userId = userId,
                        syncInProgress = false,
                        lastError = e.message
                    )
                }
            } catch (_: Exception) {
            }

            SyncResult.Error(e.message ?: "Unknown error")
        } finally {
            _isSyncing.value = false
        }
    }

    /**
     * Perform incremental sync using Trakt's last_activities
     * Only syncs data that has changed since last sync
     */

    suspend fun performIncrementalSync(): SyncResult = withContext(Dispatchers.IO) {
        if (_isSyncing.value) {
            return@withContext SyncResult.Error("Sync already in progress")
        }

        _isSyncing.value = true
        _syncProgress.value = SyncProgress(status = SyncStatus.STARTING, message = "Checking for updates...")

        val completionThreshold = Constants.WATCHED_THRESHOLD / 100f

        try {
            val userId = getUserId()
            val hasSupabase = userId != null && getSupabaseAuth() != null
            if (!hasSupabase) {
                _isSyncing.value = false
                return@withContext performFullSync()
            }
            val safeUserId = userId ?: return@withContext SyncResult.Error("Not logged in")

            var syncState: SyncStateRecord? = null
            try {
                val syncStates = executeSupabaseCall("get sync state") { auth ->
                    supabaseApi.getSyncState(auth, userId = "eq.$safeUserId")
                }
                syncState = syncStates.firstOrNull()
            } catch (e: Exception) {
            }

            if (syncState == null || (syncState.lastTraktActivitiesJson == null && syncState.lastTraktActivities == null)) {
                _isSyncing.value = false
                return@withContext performFullSync()
            }

            val currentActivities = executeTraktCall("last activities") { auth ->
                traktApi.getLastActivities(auth, clientId)
            }
            val previousActivitiesJson = syncState.lastTraktActivitiesJson ?: syncState.lastTraktActivities
            val previousActivities = gson.fromJson(previousActivitiesJson, TraktLastActivities::class.java)
            val lastSyncAt = syncState.lastSyncAt

            var moviesUpdated = 0
            var episodesUpdated = 0

            val moviesChanged = hasChanged(
                previousActivities.movies?.watchedAt,
                currentActivities.movies?.watchedAt
            )
            if (moviesChanged) {
                _syncProgress.value = SyncProgress(
                    status = SyncStatus.SYNCING_MOVIES,
                    message = "Syncing movie changes..."
                )
                val historyMovies = fetchAllHistoryMovies(startAt = lastSyncAt)
                val (movieRecords, filteredMovies) = buildWatchedMoviesFromHistory(safeUserId, historyMovies)

                movieRecords.chunked(100).forEach { chunk ->
                    if (chunk.isNotEmpty()) {
                        try {
                            executeSupabaseCall("bulk upsert watched movies") { auth ->
                                supabaseApi.bulkUpsertWatchedMovies(auth, records = chunk)
                            }
                        } catch (e: Exception) {
                        }
                    }
                }
                moviesUpdated = movieRecords.size
            }

            val episodesChanged = hasChanged(
                previousActivities.episodes?.watchedAt,
                currentActivities.episodes?.watchedAt
            )
            if (episodesChanged) {
                _syncProgress.value = SyncProgress(
                    status = SyncStatus.SYNCING_EPISODES,
                    message = "Syncing episode changes..."
                )
                val historyEpisodes = fetchAllHistoryEpisodes(startAt = lastSyncAt)
                val (episodeRecords, filteredEpisodes) = buildWatchedEpisodesFromHistory(safeUserId, historyEpisodes)

                episodeRecords.chunked(100).forEach { chunk ->
                    if (chunk.isNotEmpty()) {
                        try {
                            executeSupabaseCall("bulk upsert watched episodes") { auth ->
                                supabaseApi.bulkUpsertWatchedEpisodes(auth, records = chunk)
                            }
                        } catch (e: Exception) {
                        }
                    }
                }
                episodesUpdated = episodeRecords.size
            }

            val playbackChanged = hasChanged(
                previousActivities.episodes?.pausedAt,
                currentActivities.episodes?.pausedAt
            ) || hasChanged(
                previousActivities.movies?.pausedAt,
                currentActivities.movies?.pausedAt
            )
            if (playbackChanged) {
                _syncProgress.value = SyncProgress(
                    status = SyncStatus.SYNCING_PROGRESS,
                    message = "Syncing playback progress..."
                )
                val playbackItems = fetchAllPlaybackProgress()
                val progressRecords = buildWatchHistoryFromPlayback(safeUserId, playbackItems, completionThreshold, "trakt")

                progressRecords.chunked(100).forEach { chunk ->
                    chunk.forEach { record ->
                        try {
                            executeSupabaseCall("upsert watch history") { auth ->
                                supabaseApi.upsertWatchHistory(auth = auth, item = record)
                            }
                        } catch (e: Exception) {
                        }
                    }
                }

                try {
                    cleanupTraktPlaybackProgress(safeUserId, progressRecords)
                } catch (e: Exception) {
                }
            }

            flushOutbox()

            try {
                updateSyncState(
                    userId = safeUserId,
                    lastSyncAt = Instant.now().toString(),
                    lastTraktActivitiesJson = gson.toJson(currentActivities),
                    moviesSynced = (syncState?.moviesSynced ?: 0) + moviesUpdated,
                    episodesSynced = (syncState?.episodesSynced ?: 0) + episodesUpdated,
                    syncInProgress = false
                )
            } catch (e: Exception) {
            }

            _syncProgress.value = SyncProgress(
                status = SyncStatus.COMPLETED,
                message = if (moviesUpdated == 0 && episodesUpdated == 0) "Already up to date" else "Sync completed!",
                moviesProcessed = moviesUpdated,
                episodesProcessed = episodesUpdated
            )
            _syncEvents.tryEmit(SyncStatus.COMPLETED)

            SyncResult.Success(moviesUpdated, episodesUpdated)

        } catch (e: Exception) {
            _syncProgress.value = SyncProgress(
                status = SyncStatus.ERROR,
                message = "Sync failed: ${e.message}"
            )
            SyncResult.Error(e.message ?: "Unknown error")
        } finally {
            _isSyncing.value = false
        }
    }

    /**
     * Sync a single movie as watched to Supabase and Trakt
     */
    suspend fun markMovieWatched(tmdbId: Int, traktId: Int? = null): Boolean = withContext(Dispatchers.IO) {
        try {
            val userId = getUserId()
            val hasSupabase = userId != null && getSupabaseAuth() != null
            val traktAuth = getAuthHeader()

            val now = Instant.now().toString()

            // 1. Write to Supabase first (source of truth) when available
            if (hasSupabase) {
                val record = WatchedMovieRecord(
                    userId = userId ?: "local",
                    tmdbId = tmdbId,
                    traktId = traktId,
                    watchedAt = now
                )
                executeSupabaseCall("mark movie watched") { auth ->
                    supabaseApi.markMovieWatched(auth, record = record)
                }
            }

            // 2. Sync to Trakt (queue on failure or if offline)
            val traktSyncOk = if (traktAuth != null) {
                try {
                    traktApi.addToHistory(
                        traktAuth, clientId, "2",
                        TraktHistoryBody(movies = listOf(TraktMovieId(TraktIds(tmdb = tmdbId))))
                    )
                    true
                } catch (e: Exception) {
                    false
                }
            } else {
                false
            }

            if (!traktSyncOk && traktAuth != null) {
                outboxRepository.enqueue(
                    TraktOutboxItem(
                        action = TraktOutboxAction.MARK_MOVIE_WATCHED,
                        tmdbId = tmdbId
                    )
                )
            }

            // 3. Remove from Supabase watch_history (no longer in-progress)
            if (hasSupabase) {
                try {
                    executeSupabaseCall("delete watch history (movie)") { auth ->
                        supabaseApi.deleteWatchHistory(
                            auth = auth,
                            userId = "eq.$userId",
                            showTmdbId = "eq.$tmdbId",
                            mediaType = "eq.movie"
                        )
                    }
                } catch (_: Exception) {}
            }

            // 4. Remove playback item from Trakt so it disappears from Continue Watching
            removePlaybackForContent(traktAuth, tmdbId, MediaType.MOVIE)

            traktSyncOk || hasSupabase
        } catch (e: Exception) {
            false
        }
    }

    /**
     * Sync a single episode as watched to Supabase and Trakt
     */
    suspend fun markEpisodeWatched(
        showTmdbId: Int,
        season: Int,
        episode: Int,
        showTraktId: Int? = null
    ): Boolean = withContext(Dispatchers.IO) {
        try {
            val userId = getUserId()
            val hasSupabase = userId != null && getSupabaseAuth() != null
            val traktAuth = getAuthHeader()

            val now = Instant.now().toString()

            // 1. Write to Supabase first (source of truth) when available
            if (hasSupabase) {
                val record = WatchedEpisodeRecord(
                    userId = userId ?: "local",
                    showTmdbId = showTmdbId,
                    season = season,
                    episode = episode,
                    showTraktId = showTraktId,
                    watched = true,
                    watchedAt = now,
                    source = "arvio",
                    updatedAt = now
                )
                executeSupabaseCall("mark episode watched") { auth ->
                    supabaseApi.markEpisodeWatched(auth, record = record)
                }
            }

            // 2. Sync to Trakt (queue on failure or if offline)
            // Use shows format with nested seasons/episodes for proper episode identification
            val traktSyncOk = if (traktAuth != null) {
                try {
                    traktApi.addToHistory(
                        traktAuth, clientId, "2",
                        TraktHistoryBody(
                            shows = listOf(
                                TraktHistoryShowWithSeasons(
                                    ids = TraktIds(tmdb = showTmdbId),
                                    seasons = listOf(
                                        TraktHistorySeason(
                                            number = season,
                                            episodes = listOf(TraktHistoryEpisodeNumber(number = episode))
                                        )
                                    )
                                )
                            )
                        )
                    )
                    true
                } catch (e: Exception) {
                    false
                }
            } else {
                false
            }

            if (!traktSyncOk && traktAuth != null) {
                outboxRepository.enqueue(
                    TraktOutboxItem(
                        action = TraktOutboxAction.MARK_EPISODE_WATCHED,
                        tmdbId = showTmdbId,
                        showTraktId = showTraktId,
                        season = season,
                        episode = episode
                    )
                )
            }

            // 3. Remove from Supabase watch_history (no longer in-progress)
            if (hasSupabase) {
                try {
                    executeSupabaseCall("delete watch history (episode)") { auth ->
                        supabaseApi.deleteWatchHistory(
                            auth = auth,
                            userId = "eq.$userId",
                            showTmdbId = "eq.$showTmdbId",
                            mediaType = "eq.tv",
                            season = "eq.$season",
                            episode = "eq.$episode"
                        )
                    }
                } catch (_: Exception) {}
            }

            // 4. Remove playback item from Trakt so it disappears from Continue Watching
            removePlaybackForContent(traktAuth, showTmdbId, MediaType.TV)

            traktSyncOk || hasSupabase
        } catch (e: Exception) {
            false
        }
    }

    /**
     * Mark movie as unwatched in Supabase and Trakt
     */
    suspend fun markMovieUnwatched(tmdbId: Int): Boolean = withContext(Dispatchers.IO) {
        try {
            val userId = getUserId()
            val hasSupabase = userId != null && getSupabaseAuth() != null
            val traktAuth = getAuthHeader()

            // 1. Delete from Supabase
            if (hasSupabase) {
                executeSupabaseCall("delete watched movie") { auth ->
                    supabaseApi.deleteWatchedMovie(auth, userId = "eq.$userId", tmdbId = "eq.$tmdbId")
                }
            }

            // 2. Remove from Trakt
            if (traktAuth != null) {
                traktApi.removeFromHistory(
                    traktAuth, clientId, "2",
                    TraktHistoryBody(movies = listOf(TraktMovieId(TraktIds(tmdb = tmdbId))))
                )
            }

            traktAuth != null || hasSupabase
        } catch (e: Exception) {
            false
        }
    }

    /**
     * Mark episode as unwatched in Supabase and Trakt
     */
    suspend fun markEpisodeUnwatched(showTmdbId: Int, season: Int, episode: Int): Boolean = withContext(Dispatchers.IO) {
        try {
            val userId = getUserId()
            val hasSupabase = userId != null && getSupabaseAuth() != null
            val traktAuth = getAuthHeader()

            // 1. Delete from Supabase
            if (hasSupabase) {
                executeSupabaseCall("delete watched episode") { auth ->
                    supabaseApi.deleteWatchedEpisode(
                        auth,
                        userId = "eq.$userId",
                        tmdbId = "eq.$showTmdbId",
                        season = "eq.$season",
                        episode = "eq.$episode"
                    )
                }
            }

            // 2. Remove from Trakt
            if (traktAuth != null) {
                traktApi.removeFromHistory(
                    traktAuth, clientId, "2",
                    TraktHistoryBody(
                        shows = listOf(
                            TraktHistoryShowWithSeasons(
                                ids = TraktIds(tmdb = showTmdbId),
                                seasons = listOf(
                                    TraktHistorySeason(
                                        number = season,
                                        episodes = listOf(TraktHistoryEpisodeNumber(number = episode))
                                    )
                                )
                            )
                        )
                    )
                )
            }

            traktAuth != null || hasSupabase
        } catch (e: Exception) {
            false
        }
    }

    /**
     * Save playback progress to Supabase
     */
    suspend fun savePlaybackProgress(
        tmdbId: Int,
        mediaType: String,
        progress: Float,
        positionSeconds: Long,
        durationSeconds: Long,
        season: Int? = null,
        episode: Int? = null,
        showTraktId: Int? = null,
        traktEpisodeId: Int? = null,
        tmdbEpisodeId: Int? = null,
        title: String? = null,
        episodeTitle: String? = null,
        backdropPath: String? = null,
        posterPath: String? = null
    ): Boolean = withContext(Dispatchers.IO) {
        try {
            val userId = getUserId() ?: return@withContext false
            if (getSupabaseAuth() == null) return@withContext false

            val record = WatchHistoryRecord(
                userId = userId,
                mediaType = mediaType,
                showTmdbId = tmdbId,
                showTraktId = showTraktId,
                season = season,
                episode = episode,
                traktEpisodeId = traktEpisodeId,
                tmdbEpisodeId = tmdbEpisodeId,
                progress = progress,
                positionSeconds = positionSeconds,
                durationSeconds = durationSeconds,
                pausedAt = Instant.now().toString(),
                updatedAt = Instant.now().toString(),
                source = "arvio",
                title = title,
                episodeTitle = episodeTitle,
                backdropPath = backdropPath,
                posterPath = posterPath
            )

            executeSupabaseCall("upsert watch history (progress)") { auth ->
                supabaseApi.upsertWatchHistory(auth = auth, item = record)
            }
            true
        } catch (e: Exception) {
            false
        }
    }

    /**
     * Get all watched movies from Supabase
     */
    suspend fun getWatchedMovies(): Set<Int> = withContext(Dispatchers.IO) {
        try {
            val userId = getUserId()
            val hasSupabase = userId != null && getSupabaseAuth() != null
            if (!hasSupabase) {
                return@withContext cachedWatchedMovies?.map { it.tmdbId }?.toSet() ?: emptySet()
            }

            // PostgREST requires "eq." prefix for equality filtering
            val records = executeSupabaseCall("get watched movies") { auth ->
                supabaseApi.getWatchedMovies(auth, userId = "eq.$userId")
            }
            if (records.isEmpty() && cachedWatchedMovies != null) {
                return@withContext cachedWatchedMovies?.map { it.tmdbId }?.toSet() ?: emptySet()
            }
            records.map { it.tmdbId }.toSet()
        } catch (e: Exception) {
            emptySet()
        }
    }

    /**
     * Get all watched episodes from Supabase
     * Returns set of keys in format "tmdbId-season-episode"
     */
    suspend fun getWatchedEpisodes(): Set<String> = withContext(Dispatchers.IO) {
        try {
            val userId = getUserId()
            val hasSupabase = userId != null && getSupabaseAuth() != null
            if (!hasSupabase) {
                val cached = cachedWatchedEpisodes ?: return@withContext emptySet()
                val keys = mutableSetOf<String>()
                cached.forEach { record ->
                    val season = record.season
                    val episode = record.episode
                    if (season == null || episode == null) return@forEach
                    buildEpisodeKey(record.traktEpisodeId, null, null, season, episode)?.let { keys.add(it) }
                    buildEpisodeKey(null, record.showTraktId, null, season, episode)?.let { keys.add(it) }
                    buildEpisodeKey(null, null, record.showTmdbId, season, episode)?.let { keys.add(it) }
                }
                return@withContext keys
            }

            // PostgREST requires "eq." prefix for equality filtering
            val records = executeSupabaseCall("get watched episodes") { auth ->
                supabaseApi.getWatchedEpisodes(auth, userId = "eq.$userId")
            }
            val keys = mutableSetOf<String>()
            records.forEach { record ->
                val season = record.season
                val episode = record.episode
                if (season == null || episode == null) return@forEach

                buildEpisodeKey(record.traktEpisodeId, null, null, season, episode)?.let { keys.add(it) }
                buildEpisodeKey(null, record.showTraktId, null, season, episode)?.let { keys.add(it) }
                buildEpisodeKey(null, null, record.showTmdbId, season, episode)?.let { keys.add(it) }
            }
            if (keys.isEmpty() && cachedWatchedEpisodes != null) {
                val cachedKeys = mutableSetOf<String>()
                cachedWatchedEpisodes?.forEach { record ->
                    val season = record.season
                    val episode = record.episode
                    if (season == null || episode == null) return@forEach
                    buildEpisodeKey(record.traktEpisodeId, null, null, season, episode)?.let { cachedKeys.add(it) }
                    buildEpisodeKey(null, record.showTraktId, null, season, episode)?.let { cachedKeys.add(it) }
                    buildEpisodeKey(null, null, record.showTmdbId, season, episode)?.let { cachedKeys.add(it) }
                }
                return@withContext cachedKeys
            }
            keys
        } catch (e: Exception) {
            emptySet()
        }
    }

    /**
     * Get watched episodes with timestamps for Up Next ordering.
     */
    suspend fun getWatchedEpisodesDetailed(): List<WatchedEpisodeRecord> = withContext(Dispatchers.IO) {
        // Use in-memory cache if available (populated by sync)
        cachedWatchedEpisodes?.let {
             return@withContext it
        }

        try {
            val userId = getUserId() ?: return@withContext emptyList()
            if (getSupabaseAuth() == null) return@withContext emptyList()
            executeSupabaseCall("get watched episodes detailed") { auth ->
                supabaseApi.getWatchedEpisodes(auth, userId = "eq.$userId")
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    /**
     * Get in-progress items from Supabase for Continue Watching
     */
    suspend fun getInProgressItems(): List<WatchHistoryRecord> = withContext(Dispatchers.IO) {
        try {
            val userId = getUserId() ?: return@withContext emptyList()
            if (getSupabaseAuth() == null) return@withContext emptyList()

            // PostgREST requires "eq." prefix for equality filtering
            val records = executeSupabaseCall("get in-progress watch history") { auth ->
                supabaseApi.getWatchHistory(
                    auth,
                    userId = "eq.$userId",
                    order = "updated_at.desc",
                    limit = 500
                )
            }
            val completionThreshold = Constants.WATCHED_THRESHOLD / 100f
            records.filter { it.progress > 0f && it.progress < completionThreshold }
        } catch (e: Exception) {
            emptyList()
        }
    }

    /**
     * Get last sync time
     */
    suspend fun getLastSyncTime(): String? = withContext(Dispatchers.IO) {
        try {
            val userId = getUserId() ?: return@withContext null
            if (getSupabaseAuth() == null) return@withContext null

            // PostgREST requires "eq." prefix for equality filtering
            val syncStates = executeSupabaseCall("get sync state (last sync)") { auth ->
                supabaseApi.getSyncState(auth, userId = "eq.$userId")
            }
            syncStates.firstOrNull()?.lastSyncAt
        } catch (e: Exception) {
            null
        }
    }

    // ========== Private Helpers ==========

    private fun buildEpisodeKey(
        traktEpisodeId: Int?,
        showTraktId: Int?,
        showTmdbId: Int?,
        season: Int?,
        episode: Int?
    ): String? {
        return when {
            traktEpisodeId != null -> "trakt:$traktEpisodeId"
            showTraktId != null && season != null && episode != null -> "show_trakt:$showTraktId:$season:$episode"
            showTmdbId != null && season != null && episode != null -> "show_tmdb:$showTmdbId:$season:$episode"
            else -> null
        }
    }

    private fun buildWatchHistoryKey(record: WatchHistoryRecord): String? {
        return if (record.mediaType == "movie") {
            record.showTmdbId?.let { "movie:$it" }
        } else {
            buildEpisodeKey(record.traktEpisodeId, record.showTraktId, record.showTmdbId, record.season, record.episode)
        }
    }

    private fun isAfter(candidate: String?, existing: String?): Boolean {
        if (candidate == null) return false
        if (existing == null) return true
        return try {
            Instant.parse(candidate).isAfter(Instant.parse(existing))
        } catch (_: Exception) {
            candidate > existing
        }
    }

    private suspend fun fetchAllWatchedMovies(): List<TraktWatchedMovie> {
        return executeTraktCall("watched movies") { auth ->
            traktApi.getWatchedMovies(auth, clientId)
        }
    }

    private suspend fun fetchAllWatchedShows(): List<TraktWatchedShow> {
        return executeTraktCall("watched shows") { auth ->
            traktApi.getWatchedShows(auth, clientId)
        }
    }

    private suspend fun fetchAllHistoryMovies(startAt: String?): List<TraktHistoryItem> {
        val all = mutableListOf<TraktHistoryItem>()
        var page = 1
        val limit = 100
        var consecutiveErrors = 0
        val maxRetries = 5

        while (true) {
            try {
                if (consecutiveErrors > 0) {
                    val backoff = (consecutiveErrors * 1000L).coerceAtMost(10000L)
                    delay(backoff)
                } else {
                    delay(250) // Standard rate limit protection
                }

                val pageItems = executeTraktCall("history movies page $page") { auth ->
                    traktApi.getHistoryMovies(auth, clientId, "2", page, limit, startAt)
                }

                consecutiveErrors = 0 // Reset on success

                if (pageItems.isEmpty()) break
                all.addAll(pageItems)

                if (pageItems.size < limit) break

                page++
            } catch (e: Exception) {
                consecutiveErrors++
                if (consecutiveErrors > maxRetries) {
                    break
                }
            }
        }

        return all
    }

    private suspend fun fetchAllHistoryEpisodes(startAt: String?): List<TraktHistoryItem> {
        val all = mutableListOf<TraktHistoryItem>()
        var page = 1
        val limit = 100
        var consecutiveErrors = 0
        val maxRetries = 5

        while (true) {
            try {
                if (consecutiveErrors > 0) {
                    val backoff = (consecutiveErrors * 1000L).coerceAtMost(10000L)
                    delay(backoff)
                } else {
                    delay(250) // Standard rate limit protection
                }

                val pageItems = executeTraktCall("history episodes page $page") { auth ->
                    traktApi.getHistoryEpisodes(auth, clientId, "2", page, limit, startAt)
                }

                consecutiveErrors = 0 // Reset on success

                if (pageItems.isEmpty()) break
                all.addAll(pageItems)

                if (pageItems.size < limit) break

                page++
            } catch (e: Exception) {
                consecutiveErrors++
                if (consecutiveErrors > maxRetries) {
                    break
                }
            }
        }

        return all
    }

    private suspend fun fetchAllPlaybackProgress(): List<TraktPlaybackItem> {
        val all = mutableListOf<TraktPlaybackItem>()
        var page = 1
        val limit = 100

        while (true) {
            val pageItems = executeTraktCall("playback page $page") { auth ->
                traktApi.getPlaybackProgress(auth, clientId, "2", null, page, limit)
            }
            if (pageItems.isEmpty()) break
            all.addAll(pageItems)
            page++
        }

        return all
    }

    private fun buildWatchedMoviesFromWatchedList(
        userId: String,
        items: List<TraktWatchedMovie>
    ): Pair<List<WatchedMovieRecord>, Int> {
        val byTmdbId = LinkedHashMap<Int, WatchedMovieRecord>()
        var filtered = 0

        for (item in items) {
            val movie = item.movie
            val tmdbId = movie.ids.tmdb ?: continue
            val watchedAt = item.lastWatchedAt ?: item.lastUpdatedAt
            val existing = byTmdbId[tmdbId]
            if (existing == null || isAfter(watchedAt, existing.watchedAt)) {
                byTmdbId[tmdbId] = WatchedMovieRecord(
                    userId = userId,
                    tmdbId = tmdbId,
                    traktId = movie.ids.trakt,
                    watchedAt = watchedAt
                )
            } else {
                filtered++
            }
        }

        return Pair(byTmdbId.values.toList(), filtered)
    }

    private fun buildWatchedMoviesFromHistory(
        userId: String,
        items: List<TraktHistoryItem>
    ): Pair<List<WatchedMovieRecord>, Int> {
        val byTmdbId = LinkedHashMap<Int, WatchedMovieRecord>()
        var filtered = 0

        for (item in items) {
            val movie = item.movie ?: continue
            val tmdbId = movie.ids.tmdb ?: continue
            val watchedAt = item.watchedAt
            val existing = byTmdbId[tmdbId]
            if (existing == null || isAfter(watchedAt, existing.watchedAt)) {
                byTmdbId[tmdbId] = WatchedMovieRecord(
                    userId = userId,
                    tmdbId = tmdbId,
                    traktId = movie.ids.trakt,
                    watchedAt = watchedAt
                )
            } else {
                filtered++
            }
        }

        return Pair(byTmdbId.values.toList(), filtered)
    }

    private fun buildWatchedEpisodesFromWatchedShows(
        userId: String,
        items: List<TraktWatchedShow>
    ): Pair<List<WatchedEpisodeRecord>, Int> {
        val byKey = LinkedHashMap<String, WatchedEpisodeRecord>()
        var filtered = 0
        var skippedShows = 0
        var skippedEpisodes = 0

        for (item in items) {
            val show = item.show
            val showTmdbId = show.ids.tmdb
            if (showTmdbId == null) {
                skippedShows++
                skippedEpisodes += item.seasons?.sumOf { it.episodes.size } ?: 0
                continue
            }
            val showTraktId = show.ids.trakt
            val showWatchedAt = item.lastWatchedAt ?: item.lastUpdatedAt

            item.seasons?.forEach { season ->
                season.episodes.forEach { episode ->
                    val key = buildEpisodeKey(
                        traktEpisodeId = null,
                        showTraktId = showTraktId,
                        showTmdbId = showTmdbId,
                        season = season.number,
                        episode = episode.number
                    ) ?: return@forEach

                    val watchedAt = episode.lastWatchedAt ?: showWatchedAt
                    val existing = byKey[key]
                    if (existing == null || isAfter(watchedAt, existing.watchedAt)) {
                        byKey[key] = WatchedEpisodeRecord(
                            userId = userId,
                            showTmdbId = showTmdbId,
                            season = season.number,
                            episode = episode.number,
                            traktEpisodeId = null,
                            tmdbEpisodeId = null,
                            showTraktId = showTraktId,
                            watched = true,
                            watchedAt = watchedAt,
                            source = "trakt",
                            updatedAt = watchedAt
                        )
                    } else {
                        filtered++
                    }
                }
            }
        }

        return Pair(byKey.values.toList(), filtered)
    }

    private suspend fun buildWatchedEpisodesFromShowProgress(
        userId: String,
        items: List<TraktWatchedShow>
    ): Pair<List<WatchedEpisodeRecord>, Int> = coroutineScope {
        val byKey = LinkedHashMap<String, WatchedEpisodeRecord>()
        val mutex = Mutex()
        var filtered = 0
        val skippedShows = AtomicInteger(0)
        val skippedEpisodes = AtomicInteger(0)

        val semaphore = Semaphore(5)
        suspend fun upsertEpisode(
            showTmdbId: Int,
            showTraktId: Int?,
            seasonNumber: Int,
            episodeNumber: Int,
            watchedAt: String?
        ) {
            val key = buildEpisodeKey(
                traktEpisodeId = null,
                showTraktId = showTraktId,
                showTmdbId = showTmdbId,
                season = seasonNumber,
                episode = episodeNumber
            ) ?: return

            mutex.withLock {
                val existing = byKey[key]
                if (existing == null || isAfter(watchedAt, existing.watchedAt)) {
                    byKey[key] = WatchedEpisodeRecord(
                        userId = userId,
                        showTmdbId = showTmdbId,
                        season = seasonNumber,
                        episode = episodeNumber,
                        traktEpisodeId = null,
                        tmdbEpisodeId = null,
                        showTraktId = showTraktId,
                        watched = true,
                        watchedAt = watchedAt,
                        source = "trakt",
                        updatedAt = watchedAt
                    )
                } else {
                    filtered++
                }
            }
        }

        val tasks = items.map { item ->
            async {
                semaphore.withPermit {
                    val show = item.show
                    val showTmdbId = show.ids.tmdb
                    val showTraktId = show.ids.trakt
                    if (showTmdbId == null) {
                        skippedShows.incrementAndGet()
                        skippedEpisodes.addAndGet(item.seasons?.sumOf { it.episodes.size } ?: 0)
                        return@withPermit
                    }

                    val showWatchedAt = item.lastWatchedAt ?: item.lastUpdatedAt

                    item.seasons?.forEach { season ->
                        season.episodes.forEach { episode ->
                            if (episode.plays <= 0) return@forEach
                            val watchedAt = episode.lastWatchedAt ?: showWatchedAt
                            upsertEpisode(
                                showTmdbId = showTmdbId,
                                showTraktId = showTraktId,
                                seasonNumber = season.number,
                                episodeNumber = episode.number,
                                watchedAt = watchedAt
                            )
                        }
                    }

                    if (showTraktId == null) {
                        return@withPermit
                    }

                    try {
                        val progress = executeTraktCall("show progress $showTraktId") { auth ->
                            traktApi.getShowProgress(
                                auth,
                                clientId,
                                "2",
                                showTraktId.toString(),
                                specials = "false",
                                countSpecials = "false"
                            )
                        }

                        progress.seasons?.forEach { season ->
                            season.episodes?.forEach { episode ->
                                if (!episode.completed) return@forEach
                                val watchedAt = episode.lastWatchedAt ?: showWatchedAt
                                upsertEpisode(
                                    showTmdbId = showTmdbId,
                                    showTraktId = showTraktId,
                                    seasonNumber = season.number,
                                    episodeNumber = episode.number,
                                    watchedAt = watchedAt
                                )
                            }
                        }
                    } catch (e: Exception) {
                    }
                }
            }
        }

        tasks.awaitAll()

        Pair(byKey.values.toList(), filtered)
    }

    private fun buildWatchedEpisodesFromHistory(
        userId: String,
        items: List<TraktHistoryItem>
    ): Pair<List<WatchedEpisodeRecord>, Int> {
        val byKey = LinkedHashMap<String, WatchedEpisodeRecord>()
        var filtered = 0

        for (item in items) {
            val show = item.show ?: continue
            val episode = item.episode ?: continue
            val showTmdbId = show.ids.tmdb ?: continue
            val key = buildEpisodeKey(
                episode.ids.trakt,
                show.ids.trakt,
                showTmdbId,
                episode.season,
                episode.number
            ) ?: continue

            val watchedAt = item.watchedAt
            val existing = byKey[key]
            if (existing == null || isAfter(watchedAt, existing.watchedAt)) {
                byKey[key] = WatchedEpisodeRecord(
                    userId = userId,
                    showTmdbId = showTmdbId,
                    season = episode.season,
                    episode = episode.number,
                    traktEpisodeId = episode.ids.trakt,
                    tmdbEpisodeId = episode.ids.tmdb,
                    showTraktId = show.ids.trakt,
                    watched = true,
                    watchedAt = watchedAt,
                    source = "trakt",
                    updatedAt = watchedAt
                )
            } else {
                filtered++
            }
        }

        return Pair(byKey.values.toList(), filtered)
    }

    private fun buildWatchHistoryFromPlayback(
        userId: String,
        items: List<TraktPlaybackItem>,
        completionThreshold: Float,
        source: String
    ): List<WatchHistoryRecord> {
        val records = mutableListOf<WatchHistoryRecord>()

        for (item in items) {
            val progress = (item.progress / 100f).coerceIn(0f, 1f)
            if (progress <= 0f || progress >= completionThreshold) continue

            when (item.type) {
                "movie" -> {
                    val tmdbId = item.movie?.ids?.tmdb ?: continue
                    val updatedAt = item.pausedAt ?: Instant.now().toString()
                    records.add(
                        WatchHistoryRecord(
                            userId = userId,
                            mediaType = "movie",
                            showTmdbId = tmdbId,
                            progress = progress,
                            positionSeconds = 0,
                            durationSeconds = 0,
                            pausedAt = item.pausedAt,
                            updatedAt = updatedAt,
                            source = source,
                            title = item.movie?.title
                        )
                    )
                }
                "episode" -> {
                    val showTmdbId = item.show?.ids?.tmdb ?: continue
                    val season = item.episode?.season ?: continue
                    val number = item.episode?.number ?: continue
                    val updatedAt = item.pausedAt ?: Instant.now().toString()
                    records.add(
                        WatchHistoryRecord(
                            userId = userId,
                            mediaType = "tv",
                            showTmdbId = showTmdbId,
                            showTraktId = item.show?.ids?.trakt,
                            season = season,
                            episode = number,
                            traktEpisodeId = item.episode?.ids?.trakt,
                            tmdbEpisodeId = item.episode?.ids?.tmdb,
                            progress = progress,
                            positionSeconds = 0,
                            durationSeconds = 0,
                            pausedAt = item.pausedAt,
                            updatedAt = updatedAt,
                            source = source,
                            title = item.show?.title,
                            episodeTitle = item.episode?.title
                        )
                    )
                }
            }
        }

        return records
    }

    private suspend fun cleanupTraktPlaybackProgress(
        userId: String,
        current: List<WatchHistoryRecord>
    ) {
        val currentKeys = current.mapNotNull { buildWatchHistoryKey(it) }.toSet()
        val existing = fetchAllSupabaseWatchHistory(userId, "eq.trakt")
        val stale = existing.filter { record ->
            val key = buildWatchHistoryKey(record)
            key != null && !currentKeys.contains(key)
        }

        if (stale.isEmpty()) return

        stale.forEach { record ->
            try {
                executeSupabaseCall("delete stale playback") { auth ->
                    supabaseApi.deleteWatchHistory(
                        auth = auth,
                        userId = "eq.$userId",
                        showTmdbId = record.showTmdbId?.let { "eq.$it" },
                        mediaType = "eq.${record.mediaType}",
                        season = record.season?.let { "eq.$it" },
                        episode = record.episode?.let { "eq.$it" },
                        source = "eq.trakt"
                    )
                }
            } catch (e: Exception) {
            }
        }

    }

    private suspend fun fetchAllSupabaseWatchHistory(
        userId: String,
        source: String?
    ): List<WatchHistoryRecord> {
        val all = mutableListOf<WatchHistoryRecord>()
        var offset = 0
        val limit = 500

        while (true) {
            val page = executeSupabaseCall("get watch history page ${offset / limit + 1}") { auth ->
                supabaseApi.getWatchHistory(
                    auth = auth,
                    userId = "eq.$userId",
                    source = source,
                    order = "updated_at.desc",
                    limit = limit,
                    offset = offset
                )
            }
            if (page.isEmpty()) break
            all.addAll(page)
            offset += page.size
        }

        return all
    }

    private suspend fun flushOutbox() {
        val items = outboxRepository.loadAll()
        if (items.isEmpty()) return

        val succeeded = mutableSetOf<String>()
        val failed = mutableSetOf<String>()

        items.forEach { item ->
            val ok = try {
                when (item.action) {
                    TraktOutboxAction.MARK_MOVIE_WATCHED -> {
                        val tmdbId = item.tmdbId
                        if (tmdbId == null) {
                            false
                        } else {
                            executeTraktCall("outbox mark movie watched") { auth ->
                                traktApi.addToHistory(
                                    auth, clientId, "2",
                                    TraktHistoryBody(movies = listOf(TraktMovieId(TraktIds(tmdb = tmdbId))))
                                )
                            }
                            true
                        }
                    }
                    TraktOutboxAction.MARK_EPISODE_WATCHED -> {
                        val tmdbId = item.tmdbId
                        val season = item.season
                        val episode = item.episode
                        if (tmdbId == null || season == null || episode == null) {
                            false
                        } else {
                            executeTraktCall("outbox mark episode watched") { auth ->
                                traktApi.addToHistory(
                                    auth, clientId, "2",
                                    TraktHistoryBody(
                                        shows = listOf(
                                            TraktHistoryShowWithSeasons(
                                                ids = TraktIds(tmdb = tmdbId),
                                                seasons = listOf(
                                                    TraktHistorySeason(
                                                        number = season,
                                                        episodes = listOf(TraktHistoryEpisodeNumber(number = episode))
                                                    )
                                                )
                                            )
                                        )
                                    )
                                )
                            }
                            true
                        }
                    }
                    TraktOutboxAction.REMOVE_PLAYBACK_ITEM -> {
                        val playbackId = item.playbackId
                        if (playbackId == null) {
                            false
                        } else {
                            executeTraktCall("outbox remove playback") { auth ->
                                traktApi.removePlaybackItem(auth, clientId, "2", playbackId)
                            }
                            true
                        }
                    }
                }
            } catch (e: Exception) {
                false
            }

            if (ok) {
                succeeded.add(item.id)
            } else {
                failed.add(item.id)
            }
        }

        outboxRepository.remove(succeeded)
        outboxRepository.incrementAttempts(failed)
    }

    private suspend fun removePlaybackForContent(traktAuth: String?, tmdbId: Int, mediaType: MediaType) {
        if (traktAuth.isNullOrBlank()) return

        try {
            val playbackItems = fetchAllPlaybackProgress()
            val item = playbackItems.firstOrNull {
                when (mediaType) {
                    MediaType.MOVIE -> it.movie?.ids?.tmdb == tmdbId
                    MediaType.TV -> it.show?.ids?.tmdb == tmdbId
                }
            }

            if (item == null) return

            try {
                executeTraktCall("remove playback item") { auth ->
                    traktApi.removePlaybackItem(auth, clientId, "2", item.id)
                }
            } catch (e: Exception) {
                outboxRepository.enqueue(
                    TraktOutboxItem(
                        action = TraktOutboxAction.REMOVE_PLAYBACK_ITEM,
                        playbackId = item.id
                    )
                )
            }
        } catch (e: Exception) {
        }
    }

    private suspend fun syncPlaybackProgress(userId: String) {
        val completionThreshold = Constants.WATCHED_THRESHOLD / 100f
        val playbackItems = fetchAllPlaybackProgress()
        val progressRecords = buildWatchHistoryFromPlayback(userId, playbackItems, completionThreshold, "trakt")

        progressRecords.forEach { record ->
            executeSupabaseCall("upsert watch history") { auth ->
                supabaseApi.upsertWatchHistory(auth = auth, item = record)
            }
        }

        cleanupTraktPlaybackProgress(userId, progressRecords)
    }

    private suspend fun updateSyncState(
        userId: String,
        lastSyncAt: String? = null,
        lastFullSyncAt: String? = null,
        lastTraktActivities: String? = null,
        lastTraktActivitiesJson: String? = null,
        moviesSynced: Int? = null,
        episodesSynced: Int? = null,
        syncInProgress: Boolean? = null,
        lastError: String? = null
    ) {
        val record = SyncStateRecord(
            userId = userId,
            lastSyncAt = lastSyncAt,
            lastFullSyncAt = lastFullSyncAt,
            lastTraktActivities = lastTraktActivities,
            lastTraktActivitiesJson = lastTraktActivitiesJson,
            moviesSynced = moviesSynced ?: 0,
            episodesSynced = episodesSynced ?: 0,
            syncInProgress = syncInProgress ?: false,
            lastError = lastError,
            updatedAt = Instant.now().toString()
        )
        executeSupabaseCall("upsert sync state") { auth ->
            supabaseApi.upsertSyncState(auth, record = record)
        }
    }

    private fun hasChanged(previous: String?, current: String?): Boolean {
        if (previous == null || current == null) return true
        return previous != current
    }

    private suspend fun <T> executeTraktCall(
        operation: String,
        block: suspend (String) -> T
    ): T {
        val auth = getAuthHeader() ?: throw IllegalStateException("Not authenticated with Trakt")
        return try {
            block(auth)
        } catch (e: HttpException) {
            if (e.code() == 401) {
                val refreshed = refreshTokenIfNeeded(force = true) ?: throw e
                block("Bearer $refreshed")
            } else {
                throw e
            }
        }
    }

    private suspend fun <T> executeSupabaseCall(
        operation: String,
        block: suspend (String) -> T
    ): T {
        val auth = getSupabaseAuth() ?: throw IllegalStateException("Supabase auth failed")
        return try {
            block(auth)
        } catch (e: HttpException) {
            if (e.code() == 401) {
                val refreshed = authRepository.refreshAccessToken()
                if (!refreshed.isNullOrBlank()) {
                    return block("Bearer $refreshed")
                }
            }
            throw e
        }
    }

    private suspend fun getAuthHeader(): String? {
        val token = refreshTokenIfNeeded(force = false) ?: return null
        return "Bearer $token"
    }

    private fun getUserId(): String? {
        // Get user ID from AuthRepository
        return authRepository.getCurrentUserId()
    }

    private suspend fun getSupabaseAuth(): String? {
        val token = authRepository.getAccessToken()
        if (!token.isNullOrBlank()) return "Bearer $token"
        val refreshed = authRepository.refreshAccessToken()
        return refreshed?.let { "Bearer $it" }
    }

    private suspend fun refreshTokenIfNeeded(force: Boolean): String? {
        val prefs = context.traktDataStore.data.first()
        val accessToken = prefs[accessTokenKey()] ?: return null
        val refreshToken = prefs[refreshTokenKey()]
        val expiresAt = prefs[expiresAtKey()]

        if (refreshToken == null || expiresAt == null) {
            return if (force) null else accessToken
        }

        val now = System.currentTimeMillis() / 1000
        if (!force && now < expiresAt - 3600) {
            return accessToken
        }

        return try {
            val newToken = traktApi.refreshToken(
                RefreshTokenRequest(
                    refreshToken = refreshToken,
                    clientId = clientId,
                    clientSecret = clientSecret
                )
            )
            saveToken(newToken)
            newToken.accessToken
        } catch (e: Exception) {
            null
        }
    }

    private suspend fun saveToken(token: TraktToken) {
        context.traktDataStore.edit { prefs ->
            prefs[accessTokenKey()] = token.accessToken
            prefs[refreshTokenKey()] = token.refreshToken
            prefs[expiresAtKey()] = token.createdAt + token.expiresIn
        }
    }
}

// ========== Data Classes ==========

data class SyncProgress(
    val status: SyncStatus = SyncStatus.IDLE,
    val message: String = "",
    val moviesProcessed: Int = 0,
    val totalMovies: Int = 0,
    val episodesProcessed: Int = 0,
    val totalEpisodes: Int = 0
)

enum class SyncStatus {
    IDLE,
    STARTING,
    SYNCING_MOVIES,
    SYNCING_EPISODES,
    SYNCING_PROGRESS,
    COMPLETED,
    ERROR
}

sealed class SyncResult {
    data class Success(val moviesSynced: Int, val episodesSynced: Int) : SyncResult()
    data class Error(val message: String) : SyncResult()
}
