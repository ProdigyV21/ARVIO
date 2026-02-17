package com.arflix.tv.data.api

import com.arflix.tv.util.Constants
import com.google.gson.annotations.SerializedName
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Supabase REST API interface for watch history and user data
 *
 * Tables used:
 * - watch_history: Playback progress (position, duration, progress%)
 * - watched_movies: Movies marked as watched (source of truth)
 * - watched_episodes: Episodes marked as watched (source of truth)
 * - episode_progress: In-progress episode playback state
 * - sync_state: Tracks last Trakt sync timestamps
 */
interface SupabaseApi {

    // ========== Watch History (Playback Progress) ==========

    @GET("rest/v1/watch_history")
    suspend fun getWatchHistory(
        @Header("Authorization") auth: String,
        @Header("apikey") apiKey: String = Constants.SUPABASE_ANON_KEY,
        @Query("user_id") userId: String,
        @Query("source") source: String? = null,
        @Query("media_type") mediaType: String? = null,
        @Query("select") select: String = "*",
        @Query("order") order: String = "updated_at.desc",
        @Query("limit") limit: Int = 50,
        @Query("offset") offset: Int? = null
    ): List<WatchHistoryRecord>
    
    @POST("rest/v1/watch_history")
    suspend fun upsertWatchHistory(
        @Header("Authorization") auth: String,
        @Header("apikey") apiKey: String = Constants.SUPABASE_ANON_KEY,
        @Header("Prefer") prefer: String = "resolution=merge-duplicates",
        @Body item: WatchHistoryRecord
    )
    
    @GET("rest/v1/watch_history")
    suspend fun getWatchHistoryItem(
        @Header("Authorization") auth: String,
        @Header("apikey") apiKey: String = Constants.SUPABASE_ANON_KEY,
        @Query("user_id") userId: String,
        @Query("show_tmdb_id") showTmdbId: String,
        @Query("media_type") mediaType: String,
        @Query("season") season: String? = null,
        @Query("episode") episode: String? = null,
        @Query("select") select: String = "*",
        @Query("order") order: String? = null,
        @Query("limit") limit: Int? = null
    ): List<WatchHistoryRecord>

    @retrofit2.http.HTTP(method = "DELETE", path = "rest/v1/watch_history", hasBody = false)
    suspend fun deleteWatchHistory(
        @Header("Authorization") auth: String,
        @Header("apikey") apiKey: String = Constants.SUPABASE_ANON_KEY,
        @Query("user_id") userId: String,
        @Query("show_tmdb_id") showTmdbId: String? = null,
        @Query("media_type") mediaType: String? = null,
        @Query("season") season: String? = null,
        @Query("episode") episode: String? = null,
        @Query("source") source: String? = null
    )
    
    // ========== User Profiles ==========
    
    @GET("rest/v1/profiles")
    suspend fun getProfile(
        @Header("Authorization") auth: String,
        @Header("apikey") apiKey: String = Constants.SUPABASE_ANON_KEY,
        @Query("id") userId: String,
        @Query("select") select: String = "*"
    ): List<UserProfile>
    
    @PATCH("rest/v1/profiles")
    suspend fun updateProfile(
        @Header("Authorization") auth: String,
        @Header("apikey") apiKey: String = Constants.SUPABASE_ANON_KEY,
        @Query("id") userId: String,
        @Body profile: UserProfileUpdate
    )
    
    // ========== Watchlist ==========

    @GET("rest/v1/watchlist")
    suspend fun getWatchlist(
        @Header("Authorization") auth: String,
        @Header("apikey") apiKey: String = Constants.SUPABASE_ANON_KEY,
        @Query("user_id") userId: String,
        @Query("media_type") mediaType: String? = null,
        @Query("tmdb_id") tmdbId: String? = null,
        @Query("select") select: String = "*",
        @Query("order") order: String = "added_at.desc"
    ): List<WatchlistRecord>

    @POST("rest/v1/watchlist")
    suspend fun upsertWatchlist(
        @Header("Authorization") auth: String,
        @Header("apikey") apiKey: String = Constants.SUPABASE_ANON_KEY,
        @Header("Prefer") prefer: String = "resolution=merge-duplicates",
        @Body record: WatchlistRecord
    )

    @retrofit2.http.HTTP(method = "DELETE", path = "rest/v1/watchlist", hasBody = false)
    suspend fun deleteWatchlist(
        @Header("Authorization") auth: String,
        @Header("apikey") apiKey: String = Constants.SUPABASE_ANON_KEY,
        @Query("user_id") userId: String,
        @Query("tmdb_id") tmdbId: String,
        @Query("media_type") mediaType: String
    )

    // ========== Watched Status (from Trakt sync) ==========
    
    @GET("rest/v1/watched_movies")
    suspend fun getWatchedMovies(
        @Header("Authorization") auth: String,
        @Header("apikey") apiKey: String = Constants.SUPABASE_ANON_KEY,
        @Query("user_id") userId: String,
        @Query("select") select: String = "tmdb_id,watched_at"
    ): List<WatchedMovieRecord>
    
    @GET("rest/v1/watched_episodes")
    suspend fun getWatchedEpisodes(
        @Header("Authorization") auth: String,
        @Header("apikey") apiKey: String = Constants.SUPABASE_ANON_KEY,
        @Query("user_id") userId: String,
        @Query("select") select: String = "tmdb_id,show_trakt_id,season,episode,trakt_episode_id,tmdb_episode_id,watched_at,updated_at,source"
    ): List<WatchedEpisodeRecord>
    
    @POST("rest/v1/watched_movies")
    suspend fun markMovieWatched(
        @Header("Authorization") auth: String,
        @Header("apikey") apiKey: String = Constants.SUPABASE_ANON_KEY,
        @Header("Prefer") prefer: String = "resolution=merge-duplicates",
        @Body record: WatchedMovieRecord
    )
    
    @POST("rest/v1/watched_episodes")
    suspend fun markEpisodeWatched(
        @Header("Authorization") auth: String,
        @Header("apikey") apiKey: String = Constants.SUPABASE_ANON_KEY,
        @Header("Prefer") prefer: String = "resolution=merge-duplicates",
        @Body record: WatchedEpisodeRecord
    )

    @retrofit2.http.HTTP(method = "DELETE", path = "rest/v1/watched_movies", hasBody = false)
    suspend fun deleteWatchedMovie(
        @Header("Authorization") auth: String,
        @Header("apikey") apiKey: String = Constants.SUPABASE_ANON_KEY,
        @Query("user_id") userId: String,
        @Query("tmdb_id") tmdbId: String
    )

    @retrofit2.http.HTTP(method = "DELETE", path = "rest/v1/watched_episodes", hasBody = false)
    suspend fun deleteWatchedEpisode(
        @Header("Authorization") auth: String,
        @Header("apikey") apiKey: String = Constants.SUPABASE_ANON_KEY,
        @Query("user_id") userId: String,
        @Query("tmdb_id") tmdbId: String,
        @Query("season") season: String,
        @Query("episode") episode: String
    )

    // ========== Episode Progress (In-progress playback) ==========

    @GET("rest/v1/episode_progress")
    suspend fun getEpisodeProgress(
        @Header("Authorization") auth: String,
        @Header("apikey") apiKey: String = Constants.SUPABASE_ANON_KEY,
        @Query("user_id") userId: String,
        @Query("select") select: String = "*",
        @Query("order") order: String = "last_updated_at.desc"
    ): List<EpisodeProgressRecord>

    @POST("rest/v1/episode_progress")
    suspend fun upsertEpisodeProgress(
        @Header("Authorization") auth: String,
        @Header("apikey") apiKey: String = Constants.SUPABASE_ANON_KEY,
        @Header("Prefer") prefer: String = "resolution=merge-duplicates",
        @Body record: EpisodeProgressRecord
    )

    @retrofit2.http.HTTP(method = "DELETE", path = "rest/v1/episode_progress", hasBody = false)
    suspend fun deleteEpisodeProgress(
        @Header("Authorization") auth: String,
        @Header("apikey") apiKey: String = Constants.SUPABASE_ANON_KEY,
        @Query("user_id") userId: String,
        @Query("tmdb_id") tmdbId: String,
        @Query("season") season: String,
        @Query("episode") episode: String
    )

    // ========== Sync State (Trakt sync tracking) ==========

    @GET("rest/v1/sync_state")
    suspend fun getSyncState(
        @Header("Authorization") auth: String,
        @Header("apikey") apiKey: String = Constants.SUPABASE_ANON_KEY,
        @Query("user_id") userId: String,
        @Query("select") select: String = "*"
    ): List<SyncStateRecord>

    @POST("rest/v1/sync_state")
    suspend fun upsertSyncState(
        @Header("Authorization") auth: String,
        @Header("apikey") apiKey: String = Constants.SUPABASE_ANON_KEY,
        @Header("Prefer") prefer: String = "resolution=merge-duplicates",
        @Body record: SyncStateRecord
    )

    // ========== Bulk Operations ==========

    @POST("rest/v1/watched_episodes")
    suspend fun bulkUpsertWatchedEpisodes(
        @Header("Authorization") auth: String,
        @Header("apikey") apiKey: String = Constants.SUPABASE_ANON_KEY,
        @Header("Prefer") prefer: String = "resolution=merge-duplicates",
        @Body records: List<WatchedEpisodeRecord>
    )

    @POST("rest/v1/watched_movies")
    suspend fun bulkUpsertWatchedMovies(
        @Header("Authorization") auth: String,
        @Header("apikey") apiKey: String = Constants.SUPABASE_ANON_KEY,
        @Header("Prefer") prefer: String = "resolution=merge-duplicates",
        @Body records: List<WatchedMovieRecord>
    )
}

// ========== Data Models ==========

data class WatchHistoryRecord(
    val id: String? = null,
    @SerializedName("user_id") val userId: String,
    @SerializedName("media_type") val mediaType: String, // "movie" or "tv"
    @SerializedName("show_tmdb_id") val showTmdbId: Int? = null,
    @SerializedName("show_trakt_id") val showTraktId: Int? = null,
    val season: Int? = null,
    val episode: Int? = null,
    @SerializedName("trakt_episode_id") val traktEpisodeId: Int? = null,
    @SerializedName("tmdb_episode_id") val tmdbEpisodeId: Int? = null,
    val progress: Float, // 0.0 - 1.0
    @SerializedName("position_seconds") val positionSeconds: Long,
    @SerializedName("duration_seconds") val durationSeconds: Long,
    @SerializedName("paused_at") val pausedAt: String? = null,
    @SerializedName("updated_at") val updatedAt: String? = null,
    val source: String? = null, // "trakt" or "arvio"
    val title: String? = null,
    @SerializedName("episode_title") val episodeTitle: String? = null,
    @SerializedName("backdrop_path") val backdropPath: String? = null,
    @SerializedName("poster_path") val posterPath: String? = null,
    @SerializedName("stream_key") val streamKey: String? = null,
    @SerializedName("stream_addon_id") val streamAddonId: String? = null,
    @SerializedName("stream_title") val streamTitle: String? = null,
    @SerializedName("created_at") val createdAt: String? = null
)

data class UserProfile(
    val id: String,
    val email: String?,
    @SerializedName("trakt_token") val traktToken: com.google.gson.JsonObject?,
    @SerializedName("default_subtitle") val defaultSubtitle: String?,
    @SerializedName("auto_play_next") val autoPlayNext: Boolean?,
    val addons: String?, // JSON string of addon configs
    @SerializedName("created_at") val createdAt: String?,
    @SerializedName("updated_at") val updatedAt: String?
)

data class UserProfileUpdate(
    @SerializedName("trakt_token") val traktToken: com.google.gson.JsonObject? = null,
    @SerializedName("default_subtitle") val defaultSubtitle: String? = null,
    @SerializedName("auto_play_next") val autoPlayNext: Boolean? = null,
    val addons: String? = null
)

data class WatchlistRecord(
    @SerializedName("user_id") val userId: String,
    @SerializedName("tmdb_id") val tmdbId: Int,
    @SerializedName("media_type") val mediaType: String,
    @SerializedName("added_at") val addedAt: String? = null
)

data class WatchedMovieRecord(
    @SerializedName("user_id") val userId: String,
    @SerializedName("tmdb_id") val tmdbId: Int,
    @SerializedName("trakt_id") val traktId: Int? = null,
    @SerializedName("watched_at") val watchedAt: String? = null
)

data class WatchedEpisodeRecord(
    @SerializedName("user_id") val userId: String,
    @SerializedName("tmdb_id") val showTmdbId: Int, // Show TMDB ID
    val season: Int,
    val episode: Int,
    @SerializedName("trakt_episode_id") val traktEpisodeId: Int? = null,
    @SerializedName("tmdb_episode_id") val tmdbEpisodeId: Int? = null,
    @SerializedName("show_trakt_id") val showTraktId: Int? = null,
    @SerializedName("watched") val watched: Boolean? = true,
    @SerializedName("watched_at") val watchedAt: String? = null,
    val source: String? = null, // "trakt" or "arvio"
    @SerializedName("updated_at") val updatedAt: String? = null
)

/**
 * Episode progress record - tracks in-progress playback
 * Unique constraint: (user_id, tmdb_id, season, episode)
 */
data class EpisodeProgressRecord(
    @SerializedName("user_id") val userId: String,
    @SerializedName("tmdb_id") val tmdbId: Int, // Show TMDB ID (or movie TMDB ID)
    @SerializedName("media_type") val mediaType: String, // "movie" or "tv"
    val season: Int? = null,
    val episode: Int? = null,
    @SerializedName("trakt_id") val traktId: Int? = null, // Trakt episode ID
    @SerializedName("show_trakt_id") val showTraktId: Int? = null, // Trakt show ID
    val progress: Float, // 0.0-1.0
    @SerializedName("position_seconds") val positionSeconds: Long,
    @SerializedName("duration_seconds") val durationSeconds: Long,
    @SerializedName("paused_at") val pausedAt: String? = null,
    @SerializedName("last_updated_at") val lastUpdatedAt: String? = null,
    val source: String? = null, // "trakt" or "arvio"
    val title: String? = null,
    @SerializedName("episode_title") val episodeTitle: String? = null,
    @SerializedName("backdrop_path") val backdropPath: String? = null,
    @SerializedName("poster_path") val posterPath: String? = null
)

/**
 * Sync state record - tracks Trakt sync status per user
 * Unique constraint: (user_id)
 */
data class SyncStateRecord(
    @SerializedName("user_id") val userId: String,
    @SerializedName("last_sync_at") val lastSyncAt: String? = null,
    @SerializedName("last_full_sync_at") val lastFullSyncAt: String? = null,
    @SerializedName("last_trakt_activities") val lastTraktActivities: String? = null, // JSON string (legacy)
    @SerializedName("last_trakt_activities_json") val lastTraktActivitiesJson: String? = null,
    @SerializedName("movies_synced") val moviesSynced: Int = 0,
    @SerializedName("episodes_synced") val episodesSynced: Int = 0,
    @SerializedName("sync_in_progress") val syncInProgress: Boolean = false,
    @SerializedName("last_error") val lastError: String? = null,
    @SerializedName("updated_at") val updatedAt: String? = null
)


