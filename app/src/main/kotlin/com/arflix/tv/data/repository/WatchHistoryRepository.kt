package com.arflix.tv.data.repository

import com.arflix.tv.data.model.MediaType
import com.arflix.tv.util.Constants
import kotlinx.serialization.Serializable
import java.time.Instant
import java.util.concurrent.ConcurrentHashMap
import javax.inject.Inject
import javax.inject.Singleton

@Serializable
data class WatchHistoryEntry(
    val id: String? = null,
    val user_id: String,
    val profile_id: String? = null,
    val media_type: String,
    val show_tmdb_id: Int,
    val show_trakt_id: Int? = null,
    val season: Int? = null,
    val episode: Int? = null,
    val trakt_episode_id: Int? = null,
    val tmdb_episode_id: Int? = null,
    val title: String? = null,
    val episode_title: String? = null,
    val progress: Float = 0f,
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

@Singleton
class WatchHistoryRepository @Inject constructor(
    private val profileManager: ProfileManager
) {
    private val historyByProfile = ConcurrentHashMap<String, List<WatchHistoryEntry>>()

    private fun currentProfileId(): String = profileManager.getProfileIdSync().ifBlank { "default" }

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
        val profileId = currentProfileId()
        val now = Instant.now().toString()
        val normalizedType = if (mediaType == MediaType.MOVIE) "movie" else "tv"
        val entry = WatchHistoryEntry(
            id = "$profileId:$normalizedType:$tmdbId:${season ?: -1}:${episode ?: -1}",
            user_id = "local",
            profile_id = profileId,
            media_type = normalizedType,
            show_tmdb_id = tmdbId,
            title = title,
            poster_path = poster,
            backdrop_path = backdrop,
            season = season,
            episode = episode,
            episode_title = episodeTitle,
            progress = (progress / 100f).takeIf { progress > 1f } ?: progress,
            duration_seconds = duration,
            position_seconds = position,
            paused_at = now,
            updated_at = now,
            source = "local",
            stream_key = streamKey,
            stream_addon_id = streamAddonId,
            stream_title = streamTitle
        )
        val existing = historyByProfile[profileId].orEmpty()
        historyByProfile[profileId] = listOf(entry) + existing.filterNot {
            it.media_type == normalizedType &&
                it.show_tmdb_id == tmdbId &&
                it.season == season &&
                it.episode == episode
        }
    }

    suspend fun getWatchHistory(): List<WatchHistoryEntry> {
        return historyByProfile[currentProfileId()].orEmpty()
            .sortedByDescending { parseEpoch(it.updated_at).coerceAtLeast(parseEpoch(it.paused_at)) }
    }

    suspend fun getContinueWatching(): List<WatchHistoryEntry> {
        return getWatchHistory().filter { isEntryInProgress(it) }
    }

    suspend fun getProgress(
        mediaType: MediaType,
        tmdbId: Int,
        season: Int?,
        episode: Int?
    ): WatchHistoryEntry? {
        val normalizedType = if (mediaType == MediaType.MOVIE) "movie" else "tv"
        return historyByProfile[currentProfileId()].orEmpty().firstOrNull {
            it.media_type == normalizedType &&
                it.show_tmdb_id == tmdbId &&
                it.season == season &&
                it.episode == episode
        }
    }

    suspend fun getLatestProgress(
        mediaType: MediaType,
        tmdbId: Int
    ): WatchHistoryEntry? {
        val normalizedType = if (mediaType == MediaType.MOVIE) "movie" else "tv"
        return historyByProfile[currentProfileId()].orEmpty()
            .filter { it.media_type == normalizedType && it.show_tmdb_id == tmdbId && isEntryInProgress(it) }
            .maxByOrNull { parseEpoch(it.updated_at).coerceAtLeast(parseEpoch(it.paused_at)) }
    }

    suspend fun removeFromHistory(
        tmdbId: Int,
        season: Int?,
        episode: Int?
    ) {
        val profileId = currentProfileId()
        historyByProfile[profileId] = historyByProfile[profileId].orEmpty().filterNot {
            it.show_tmdb_id == tmdbId &&
                (season == null || it.season == season) &&
                (episode == null || it.episode == episode)
        }
    }

    suspend fun clearHistory() {
        historyByProfile.remove(currentProfileId())
    }

    fun clearProfileCaches() {
        historyByProfile.clear()
    }

    private fun parseEpoch(value: String?): Long {
        if (value.isNullOrBlank()) return 0L
        return runCatching { Instant.parse(value).toEpochMilli() }.getOrDefault(0L)
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
