package com.arflix.tv.data.repository

import com.arflix.tv.data.api.SupabaseApi
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.util.Constants
import com.arflix.tv.util.AppLogger
import kotlinx.serialization.Serializable
import retrofit2.HttpException
import java.time.Instant
import java.util.concurrent.ConcurrentHashMap
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

/**
 * Watch history entry for Supabase
 */
@Serializable
data class WatchHistoryEntry(
    val id: String? = null,
    val user_id: String,
    val profile_id: String? = null,
    val media_type: String, // "movie" or "tv"
    val show_tmdb_id: Int,
    val show_trakt_id: Int? = null,
    val season: Int? = null,
    val episode: Int? = null,
    val trakt_episode_id: Int? = null,
    val tmdb_episode_id: Int? = null,
    val title: String? = null,
    val episode_title: String? = null,
    val progress: Float = 0f, // 0.0-1.0
    val duration_seconds: Long = 0,
    val position_seconds: Long = 0,
    val paused_at: String? = null,
    val updated_at: String? = null,
    val source: String? = null,
    val backdrop_path: String? = null,
    val poster_path: String? = null,
    val stream_key: String? = null,
    val stream_addon_id: String? = null,
    val stream_title: String? = null
)

/**
 * Repository for syncing watch history with Supabase
 */
@Singleton
class WatchHistoryRepository @Inject constructor(
    private val authRepositoryProvider: Provider<AuthRepository>,
    private val supabaseApi: SupabaseApi,
    private val profileManager: ProfileManager,
    private val realtimeSyncManagerProvider: Provider<RealtimeSyncManager>
) {
    // In-memory cache of the last successful CW fetch. When the Supabase REST call
    // fails (network timeout, 401, etc.), we return this cached list instead of an
    // empty list — so Continue Watching never silently disappears from the UI.
    // Updated on every successful getContinueWatching() call.
    @Volatile
    private var cachedContinueWatching: List<WatchHistoryEntry> = emptyList()
    private val cachedContinueWatchingByProfile = ConcurrentHashMap<String, List<WatchHistoryEntry>>()
    private val cachedWatchHistoryByProfile = ConcurrentHashMap<String, List<WatchHistoryEntry>>()

    private fun currentProfileId(): String = profileManager.getProfileIdSync().ifBlank { "default" }

    private fun currentProfileQuery(): String? {
        val profileId = currentProfileId()
        return if (profileManager.isDefaultProfile()) null else "eq.$profileId"
    }

    private fun profileHistorySource(base: String): String {
        // Use stable profile ID so rename/case changes do not split history.
        // Profile IDs are synced via cloud profile sync.
        val profileId = currentProfileId()
        return "profile:$profileId:$base"
    }

    private fun profileHistorySourceFilter(): String {
        // PostgREST wildcard for LIKE is '*'
        val profileId = currentProfileId()
        return "like.profile:$profileId:*"
    }

    /**
     * In-memory filter that accepts entries for the current profile only.
     * Matches by profile NAME (cross-device) and profile UUID (same device).
     * Legacy entries (source = "arvio" / "trakt" / null) are only accepted
     * for the default profile to avoid cross-profile leakage.
     */
    private fun filterByProfile(entries: List<WatchHistoryEntry>): List<WatchHistoryEntry> {
        val profileId = currentProfileId()
        val profileName = profileManager.getProfileNameSync()
        val prefixById = "profile:$profileId:"
        val prefixByName = "profile:$profileName:"
        val isDefault = profileManager.isDefaultProfile()

        return entries.filter { entry ->
            if (!entry.profile_id.isNullOrBlank()) {
                return@filter entry.profile_id == profileId
            }
            val src = entry.source
            when {
                // Profile-specific entries: match by UUID or name
                src != null && src.startsWith("profile:") ->
                    src.startsWith(prefixById) || src.startsWith(prefixByName)
                // Legacy entries (null / "arvio" / "trakt"): only show on default profile
                else -> isDefault
            }
        }
    }

    /**
     * Save watch progress to Supabase
     */
    suspend fun saveProgress(
        mediaType: MediaType,
        tmdbId: Int,
        title: String,
        poster: String?,
        backdrop: String?,
        season: Int?,
        episode: Int?,
        episodeTitle: String?,
        progress: Float,
        duration: Long,
        position: Long,
        streamKey: String? = null,
        streamAddonId: String? = null,
        streamTitle: String? = null,
        sessionStartTime: Long = 0L
    ) {
        val userId = authRepositoryProvider.get().getCurrentUserId() ?: return

        if (sessionStartTime > 0L) {
            val existingProgress = getProgress(mediaType, tmdbId, season, episode)
            if (existingProgress != null) {
                val cloudUpdatedAt = parseEpoch(existingProgress.updated_at)
                if (cloudUpdatedAt > sessionStartTime + 10_000L) {
                    com.arflix.tv.util.AppLogger.breadcrumb(
                        tag = "WatchHistory",
                        message = "Rejected stale push for $tmdbId. Cloud updated $cloudUpdatedAt, session start $sessionStartTime",
                        severity = "warning"
                    )
                    return
                }
            }
        }

        val entry = WatchHistoryEntry(
                user_id = userId,
                profile_id = currentProfileId(),
                media_type = if (mediaType == MediaType.MOVIE) "movie" else "tv",
                show_tmdb_id = tmdbId,
                title = title,
                poster_path = poster,
                backdrop_path = backdrop,
                season = season,
                episode = episode,
                episode_title = episodeTitle,
                progress = progress,
                duration_seconds = duration,
                position_seconds = position,
                source = profileHistorySource("arvio"),
                stream_key = streamKey,
                stream_addon_id = streamAddonId,
                stream_title = streamTitle
            )

        if (Constants.USE_NETLIFY_CLOUD_SYNC) {
            val profileId = currentProfileId()
            val nowIso = Instant.now().toString()
            val cachedEntry = entry.copy(
                paused_at = nowIso,
                updated_at = nowIso
            )
            cachedWatchHistory = listOf(cachedEntry) + cachedWatchHistory.filterNot { existing ->
                existing.media_type == cachedEntry.media_type &&
                    existing.show_tmdb_id == cachedEntry.show_tmdb_id &&
                    existing.season == cachedEntry.season &&
                    existing.episode == cachedEntry.episode
            }
            cachedWatchHistoryByProfile[profileId] = cachedWatchHistory
            cachedContinueWatching = if (isEntryInProgress(cachedEntry)) {
                listOf(cachedEntry) + cachedContinueWatchingByProfile[profileId].orEmpty().filterNot { existing ->
                    existing.media_type == cachedEntry.media_type &&
                        existing.show_tmdb_id == cachedEntry.show_tmdb_id
                }
            } else {
                cachedContinueWatchingByProfile[profileId].orEmpty().filterNot { existing ->
                    existing.media_type == cachedEntry.media_type &&
                        existing.show_tmdb_id == cachedEntry.show_tmdb_id
                }
            }
            cachedContinueWatchingByProfile[profileId] = cachedContinueWatching
            runCatching { realtimeSyncManagerProvider.get().markLocalWatchHistoryWrite() }
            return
        }

        // Upsert - update if exists, insert if not.
        // Retry without stream_* fields if the Supabase schema hasn't been migrated yet.
        var saved = false
        try {
            executeSupabaseCall("save watch progress") { auth ->
                supabaseApi.upsertWatchHistory(auth = auth, item = entry.toRecord())
            }
            saved = true
        } catch (e: HttpException) {
            runCatching {
                val fallback = entry.copy(stream_key = null, stream_addon_id = null, stream_title = null)
                executeSupabaseCall("save watch progress fallback") { auth ->
                    supabaseApi.upsertWatchHistory(auth = auth, item = fallback.toRecord())
                }
                saved = true
            }
        } catch (e: Exception) {
            AppLogger.e("WatchHistoryRepository", "Error in watch history operation", e)
        }

        // Mark the local write timestamp on the realtime manager so the incoming
        // watch_history realtime event (which fires back to our own device) doesn't
        // trigger a redundant Home Continue Watching refresh. See issue #91 fix.
        if (saved) {
            val profileId = currentProfileId()
            val nowIso = Instant.now().toString()
            val cachedEntry = entry.copy(
                paused_at = nowIso,
                updated_at = nowIso
            )
            val profileCache = cachedContinueWatchingByProfile[profileId].orEmpty()
            cachedContinueWatching = if (isEntryInProgress(cachedEntry)) {
                listOf(cachedEntry) + profileCache.filterNot { existing ->
                    existing.media_type == cachedEntry.media_type &&
                        existing.show_tmdb_id == cachedEntry.show_tmdb_id
                }
            } else {
                profileCache.filterNot { existing ->
                    existing.media_type == cachedEntry.media_type &&
                        existing.show_tmdb_id == cachedEntry.show_tmdb_id
                }
            }
            cachedContinueWatchingByProfile[profileId] = cachedContinueWatching
            runCatching { realtimeSyncManagerProvider.get().markLocalWatchHistoryWrite() }
        }
    }

    @Volatile
    private var cachedWatchHistory: List<WatchHistoryEntry> = emptyList()

    /**
     * Get watch history for current user.
     * Returns cached data on failure instead of empty list.
     */
    suspend fun getWatchHistory(): List<WatchHistoryEntry> {
        val profileId = currentProfileId()
        if (Constants.USE_NETLIFY_CLOUD_SYNC) {
            return cachedWatchHistoryByProfile[profileId].orEmpty()
        }
        val userId = authRepositoryProvider.get().getCurrentUserId()
            ?: return cachedWatchHistoryByProfile[profileId].orEmpty()

        return try {
            val records = executeSupabaseCall("get watch history") { auth ->
                supabaseApi.getWatchHistory(
                    auth = auth,
                    userId = "eq.$userId",
                    profileId = currentProfileQuery(),
                    source = null,
                    order = "updated_at.desc",
                    limit = 500
                )
            }.map { it.toEntry() }
            val result = filterByProfile(records)
            cachedWatchHistory = result
            cachedWatchHistoryByProfile[profileId] = result
            result
        } catch (e: Exception) {
            AppLogger.e("WatchHistoryRepository", "Error getting watch history, returning cache", e)
            cachedWatchHistoryByProfile[profileId].orEmpty()
        }
    }

    /**
     * Get continue watching items (progress < 90%).
     *
     * On success: updates the in-memory cache and returns fresh data.
     * On failure: returns the last successfully fetched data from cache instead
     * of an empty list — so Continue Watching never silently disappears from the
     * Home screen due to a transient network error or expired token. The cached
     * data may be a few seconds stale, but stale data is infinitely better than
     * an empty row that makes the user think their watch history is lost.
     */
    suspend fun getContinueWatching(): List<WatchHistoryEntry> {
        val profileId = currentProfileId()
        if (Constants.USE_NETLIFY_CLOUD_SYNC) {
            return cachedContinueWatchingByProfile[profileId].orEmpty()
        }
        val userId = authRepositoryProvider.get().getCurrentUserId()
        if (userId == null) return cachedContinueWatchingByProfile[profileId].orEmpty()

        return try {
            val records = executeSupabaseCall("get continue watching history") { auth ->
                supabaseApi.getWatchHistory(
                    auth = auth,
                    userId = "eq.$userId",
                    profileId = currentProfileQuery(),
                    source = null,
                    order = "updated_at.desc",
                    limit = 500
                )
            }
            val allEntries = records.map { it.toEntry() }
            val result = filterByProfile(allEntries)
                .filter { isEntryInProgress(it) }
            // Cache the successful result for offline/error fallback
            cachedContinueWatching = result
            cachedContinueWatchingByProfile[profileId] = result
            result
        } catch (e: Exception) {
            AppLogger.e("WatchHistoryRepository", "Error getting continue watching, returning cache", e)
            cachedContinueWatchingByProfile[profileId].orEmpty()
        }
    }

    /**
     * Get progress for a specific item
     */
    suspend fun getProgress(
        mediaType: MediaType,
        tmdbId: Int,
        season: Int?,
        episode: Int?
    ): WatchHistoryEntry? {
        if (Constants.USE_NETLIFY_CLOUD_SYNC) {
            return cachedWatchHistoryByProfile[currentProfileId()].orEmpty().firstOrNull { entry ->
                entry.show_tmdb_id == tmdbId &&
                    entry.media_type == (if (mediaType == MediaType.MOVIE) "movie" else "tv") &&
                    entry.season == season &&
                    entry.episode == episode
            }
        }
        val userId = authRepositoryProvider.get().getCurrentUserId() ?: return null

        return try {
            val records = executeSupabaseCall("get watch history item") { auth ->
                supabaseApi.getWatchHistoryItem(
                    auth = auth,
                    userId = "eq.$userId",
                    profileId = currentProfileQuery(),
                    showTmdbId = "eq.$tmdbId",
                    mediaType = "eq.${if (mediaType == MediaType.MOVIE) "movie" else "tv"}",
                    source = null,
                    season = season?.let { "eq.$it" },
                    episode = episode?.let { "eq.$it" }
                )
            }
            filterByProfile(records.map { it.toEntry() }).firstOrNull()
        } catch (e: Exception) {
            AppLogger.e("WatchHistoryRepository", "Error returning null fallback", e)
            null
        }
    }

    /**
     * Get the latest in-progress entry for a show/movie.
     */
    suspend fun getLatestProgress(
        mediaType: MediaType,
        tmdbId: Int
    ): WatchHistoryEntry? {
        if (Constants.USE_NETLIFY_CLOUD_SYNC) {
            val mediaTypeKey = if (mediaType == MediaType.MOVIE) "movie" else "tv"
            return cachedWatchHistoryByProfile[currentProfileId()].orEmpty()
                .filter { it.show_tmdb_id == tmdbId && it.media_type == mediaTypeKey }
                .filter { isEntryInProgress(it) }
                .maxByOrNull { entry ->
                    parseEpoch(entry.updated_at).coerceAtLeast(parseEpoch(entry.paused_at))
                }
        }
        val userId = authRepositoryProvider.get().getCurrentUserId() ?: return null
        val mediaTypeKey = if (mediaType == MediaType.MOVIE) "movie" else "tv"

        return try {
            val records = executeSupabaseCall("get watch history by show") { auth ->
                supabaseApi.getWatchHistoryItem(
                    auth = auth,
                    userId = "eq.$userId",
                    profileId = currentProfileQuery(),
                    showTmdbId = "eq.$tmdbId",
                    mediaType = "eq.$mediaTypeKey",
                    source = null,
                    order = "updated_at.desc",
                    limit = 50
                )
            }
            filterByProfile(records.map { it.toEntry() })
                .filter { isEntryInProgress(it) }
                .maxByOrNull { entry ->
                    parseEpoch(entry.updated_at).coerceAtLeast(parseEpoch(entry.paused_at))
                }
        } catch (e: Exception) {
            AppLogger.e("WatchHistoryRepository", "Error returning null fallback", e)
            null
        }
    }

    /**
     * Remove item from watch history
     */
    suspend fun removeFromHistory(
        tmdbId: Int,
        season: Int?,
        episode: Int?
    ) {
        if (Constants.USE_NETLIFY_CLOUD_SYNC) {
            val profileId = currentProfileId()
            cachedWatchHistoryByProfile[profileId] = cachedWatchHistoryByProfile[profileId].orEmpty().filterNot { entry ->
                entry.show_tmdb_id == tmdbId &&
                    (season == null || entry.season == season) &&
                    (episode == null || entry.episode == episode)
            }
            cachedContinueWatchingByProfile[profileId] = cachedContinueWatchingByProfile[profileId].orEmpty().filterNot { entry ->
                entry.show_tmdb_id == tmdbId &&
                    (season == null || entry.season == season) &&
                    (episode == null || entry.episode == episode)
            }
            return
        }
        val userId = authRepositoryProvider.get().getCurrentUserId() ?: return

        try {
            executeSupabaseCall("remove watch history item") { auth ->
                supabaseApi.deleteWatchHistory(
                    auth = auth,
                    userId = "eq.$userId",
                    showTmdbId = "eq.$tmdbId",
                    profileId = currentProfileQuery(),
                    source = profileHistorySourceFilter(),
                    season = season?.let { "eq.$it" },
                    episode = episode?.let { "eq.$it" }
                )
            }
        } catch (e: Exception) {
            AppLogger.e("WatchHistoryRepository", "Silently handled error", e)
        }
    }

    /**
     * Clear all watch history
     */
    suspend fun clearHistory() {
        if (Constants.USE_NETLIFY_CLOUD_SYNC) {
            val profileId = currentProfileId()
            cachedWatchHistoryByProfile.remove(profileId)
            cachedContinueWatchingByProfile.remove(profileId)
            cachedWatchHistory = emptyList()
            cachedContinueWatching = emptyList()
            return
        }
        val userId = authRepositoryProvider.get().getCurrentUserId() ?: return

        try {
            executeSupabaseCall("clear watch history") { auth ->
                supabaseApi.deleteWatchHistory(
                    auth = auth,
                    userId = "eq.$userId",
                    profileId = currentProfileQuery(),
                    source = profileHistorySourceFilter()
                )
            }
        } catch (e: Exception) {
            AppLogger.e("WatchHistoryRepository", "Silently handled error", e)
        }
    }

    fun clearProfileCaches() {
        cachedContinueWatching = emptyList()
        cachedWatchHistory = emptyList()
        cachedContinueWatchingByProfile.clear()
        cachedWatchHistoryByProfile.clear()
    }

    private suspend fun <T> executeSupabaseCall(
        operation: String,
        block: suspend (String) -> T
    ): T {
        if (Constants.USE_NETLIFY_CLOUD_SYNC) {
            throw IllegalStateException("Legacy Supabase watch history is disabled in Netlify mode")
        }
        val auth = getSupabaseAuth() ?: throw IllegalStateException("Supabase auth failed")
        return try {
            block(auth)
        } catch (e: HttpException) {
            if (e.code() == 401) {
                // Unauthorized, refresh session and retry
                val refreshed = authRepositoryProvider.get().refreshAccessToken()
                if (!refreshed.isNullOrBlank()) {
                    return block("Bearer $refreshed")
                }
            }
            throw e
        }
    }

    private suspend fun getSupabaseAuth(): String? {
        val authRepository = authRepositoryProvider.get()
        val token = authRepository.getAccessToken()
        if (!token.isNullOrBlank()) return "Bearer $token"
        val refreshed = authRepository.refreshAccessToken()
        return refreshed?.let { "Bearer $it" }
    }

    private fun parseEpoch(value: String?): Long {
        if (value.isNullOrBlank()) return 0L
        return try {
            Instant.parse(value).toEpochMilli()
        } catch (e: Exception) {
            AppLogger.e("WatchHistoryRepository", "Error parsing date, fallback 0L", e)
            0L
        }
    }

    private fun isEntryInProgress(entry: WatchHistoryEntry): Boolean {
        val threshold = Constants.WATCHED_THRESHOLD / 100f
        val normalizedProgress = entry.progress.coerceIn(0f, 1f)
        val normalizedDuration = normalizeStoredSeconds(entry.duration_seconds)
        val normalizedPosition = normalizeStoredSeconds(entry.position_seconds)
        val derivedProgress = when {
            normalizedProgress > 0f -> normalizedProgress
            normalizedDuration > 0L && normalizedPosition > 0L ->
                (normalizedPosition.toFloat() / normalizedDuration.toFloat()).coerceIn(0f, 1f)
            else -> 0f
        }

        return when {
            derivedProgress > 0f -> derivedProgress < threshold
            else -> normalizedPosition > 0L
        }
    }

    private fun normalizeStoredSeconds(value: Long): Long {
        return if (value > 86_400L) value / 1000L else value
    }
}

private fun WatchHistoryEntry.toRecord(): com.arflix.tv.data.api.WatchHistoryRecord {
    return com.arflix.tv.data.api.WatchHistoryRecord(
        userId = user_id,
        profileId = profile_id,
        mediaType = media_type,
        showTmdbId = show_tmdb_id,
        showTraktId = show_trakt_id,
        season = season,
        episode = episode,
        traktEpisodeId = trakt_episode_id,
        tmdbEpisodeId = tmdb_episode_id,
        progress = progress,
        positionSeconds = position_seconds,
        durationSeconds = duration_seconds,
        pausedAt = paused_at,
        updatedAt = updated_at,
        source = source,
        title = title,
        episodeTitle = episode_title,
        backdropPath = backdrop_path,
        posterPath = poster_path,
        streamKey = stream_key,
        streamAddonId = stream_addon_id,
        streamTitle = stream_title
    )
}

private fun com.arflix.tv.data.api.WatchHistoryRecord.toEntry(): WatchHistoryEntry {
    return WatchHistoryEntry(
        id = id,
        user_id = userId,
        profile_id = profileId,
        media_type = mediaType,
        show_tmdb_id = showTmdbId ?: 0,
        show_trakt_id = showTraktId,
        season = season,
        episode = episode,
        trakt_episode_id = traktEpisodeId,
        tmdb_episode_id = tmdbEpisodeId,
        title = title,
        episode_title = episodeTitle,
        progress = progress,
        duration_seconds = durationSeconds,
        position_seconds = positionSeconds,
        paused_at = pausedAt,
        updated_at = updatedAt,
        source = source,
        backdrop_path = backdropPath,
        poster_path = posterPath,
        stream_key = streamKey,
        stream_addon_id = streamAddonId,
        stream_title = streamTitle
    )
}
