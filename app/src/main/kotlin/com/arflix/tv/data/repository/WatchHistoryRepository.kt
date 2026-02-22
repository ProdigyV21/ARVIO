package com.arflix.tv.data.repository

import com.arflix.tv.data.api.SupabaseApi
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.util.Constants
import kotlinx.serialization.Serializable
import retrofit2.HttpException
import java.time.Instant
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
    private val profileManager: ProfileManager
) {
    private fun profileHistorySource(base: String): String {
        return "profile:${profileManager.getProfileIdSync()}:$base"
    }

    private fun profileHistorySourceFilter(): String {
        // PostgREST wildcard for LIKE is '*'
        return "like.profile:${profileManager.getProfileIdSync()}:*"
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
        streamTitle: String? = null
    ) {
        val userId = authRepositoryProvider.get().getCurrentUserId() ?: return

        val entry = WatchHistoryEntry(
                user_id = userId,
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

        // Upsert - update if exists, insert if not.
        // Retry without stream_* fields if the Supabase schema hasn't been migrated yet.
        try {
            executeSupabaseCall("save watch progress") { auth ->
                supabaseApi.upsertWatchHistory(auth = auth, item = entry.toRecord())
            }
        } catch (e: HttpException) {
            runCatching {
                val fallback = entry.copy(stream_key = null, stream_addon_id = null, stream_title = null)
                executeSupabaseCall("save watch progress fallback") { auth ->
                    supabaseApi.upsertWatchHistory(auth = auth, item = fallback.toRecord())
                }
            }
        } catch (_: Exception) {
        }
    }

    /**
     * Get watch history for current user
     */
    suspend fun getWatchHistory(): List<WatchHistoryEntry> {
        val userId = authRepositoryProvider.get().getCurrentUserId() ?: return emptyList()

        return try {
            executeSupabaseCall("get watch history") { auth ->
                supabaseApi.getWatchHistory(
                    auth = auth,
                    userId = "eq.$userId",
                    source = profileHistorySourceFilter(),
                    order = "updated_at.desc",
                    limit = 500
                )
            }.map { it.toEntry() }
        } catch (_: Exception) {
            emptyList()
        }
    }

    /**
     * Get continue watching items (progress < 90%)
     */
    suspend fun getContinueWatching(): List<WatchHistoryEntry> {
        val userId = authRepositoryProvider.get().getCurrentUserId() ?: return emptyList()

        return try {
            val records = executeSupabaseCall("get continue watching history") { auth ->
                supabaseApi.getWatchHistory(
                    auth = auth,
                    userId = "eq.$userId",
                    source = profileHistorySourceFilter(),
                    order = "updated_at.desc",
                    limit = 500
                )
            }
            records
                .map { it.toEntry() }
                .filter { isEntryInProgress(it) }
        } catch (_: Exception) {
            emptyList()
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
        val userId = authRepositoryProvider.get().getCurrentUserId() ?: return null

        return try {
            val records = executeSupabaseCall("get watch history item") { auth ->
                supabaseApi.getWatchHistoryItem(
                    auth = auth,
                    userId = "eq.$userId",
                    showTmdbId = "eq.$tmdbId",
                    mediaType = "eq.${if (mediaType == MediaType.MOVIE) "movie" else "tv"}",
                    source = profileHistorySourceFilter(),
                    season = season?.let { "eq.$it" },
                    episode = episode?.let { "eq.$it" }
                )
            }
            records.firstOrNull()?.toEntry()
        } catch (_: Exception) {
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
        val userId = authRepositoryProvider.get().getCurrentUserId() ?: return null
        val mediaTypeKey = if (mediaType == MediaType.MOVIE) "movie" else "tv"

        return try {
            val records = executeSupabaseCall("get watch history by show") { auth ->
                supabaseApi.getWatchHistoryItem(
                    auth = auth,
                    userId = "eq.$userId",
                    showTmdbId = "eq.$tmdbId",
                    mediaType = "eq.$mediaTypeKey",
                    source = profileHistorySourceFilter(),
                    order = "updated_at.desc",
                    limit = 50
                )
            }
            records
                .map { it.toEntry() }
                .filter { isEntryInProgress(it) }
                .maxByOrNull { entry ->
                    parseEpoch(entry.updated_at).coerceAtLeast(parseEpoch(entry.paused_at))
                }
        } catch (_: Exception) {
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
        val userId = authRepositoryProvider.get().getCurrentUserId() ?: return

        try {
            executeSupabaseCall("remove watch history item") { auth ->
                supabaseApi.deleteWatchHistory(
                    auth = auth,
                    userId = "eq.$userId",
                    showTmdbId = "eq.$tmdbId",
                    source = profileHistorySourceFilter(),
                    season = season?.let { "eq.$it" },
                    episode = episode?.let { "eq.$it" }
                )
            }
        } catch (_: Exception) {
            // Silently handle errors
        }
    }

    /**
     * Clear all watch history
     */
    suspend fun clearHistory() {
        val userId = authRepositoryProvider.get().getCurrentUserId() ?: return

        try {
            executeSupabaseCall("clear watch history") { auth ->
                supabaseApi.deleteWatchHistory(
                    auth = auth,
                    userId = "eq.$userId",
                    source = profileHistorySourceFilter()
                )
            }
        } catch (_: Exception) {
            // Silently handle errors
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
        } catch (_: Exception) {
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
