package com.arflix.tv.data.api

import com.google.gson.annotations.SerializedName
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Trakt.tv API interface
 */
interface TraktApi {
    
    // ========== Authentication ==========
    
    @POST("oauth/device/code")
    @retrofit2.http.Headers("Content-Type: application/json")
    suspend fun getDeviceCode(
        @Body request: DeviceCodeRequest
    ): TraktDeviceCode
    
    @POST("oauth/device/token")
    @retrofit2.http.Headers("Content-Type: application/json")
    suspend fun pollToken(
        @Body request: TokenPollRequest
    ): TraktToken
    
    @POST("oauth/token")
    @retrofit2.http.Headers("Content-Type: application/json")
    suspend fun refreshToken(
        @Body request: RefreshTokenRequest
    ): TraktToken
    
    // ========== Sync ==========

    @GET("sync/last_activities")
    @retrofit2.http.Headers("Content-Type: application/json")
    suspend fun getLastActivities(
        @Header("Authorization") auth: String,
        @Header("trakt-api-key") clientId: String,
        @Header("trakt-api-version") version: String = "2"
    ): TraktLastActivities

    @GET("sync/watched/movies")
    @retrofit2.http.Headers("Content-Type: application/json")
    suspend fun getWatchedMovies(
        @Header("Authorization") auth: String,
        @Header("trakt-api-key") clientId: String,
        @Header("trakt-api-version") version: String = "2"
    ): List<TraktWatchedMovie>
    
    @GET("sync/watched/shows")
    @retrofit2.http.Headers("Content-Type: application/json")
    suspend fun getWatchedShows(
        @Header("Authorization") auth: String,
        @Header("trakt-api-key") clientId: String,
        @Header("trakt-api-version") version: String = "2"
    ): List<TraktWatchedShow>
    
    @GET("sync/playback")
    @retrofit2.http.Headers("Content-Type: application/json")
    suspend fun getPlaybackProgress(
        @Header("Authorization") auth: String,
        @Header("trakt-api-key") clientId: String,
        @Header("trakt-api-version") version: String = "2",
        @Query("type") type: String? = null,
        @Query("page") page: Int? = null,
        @Query("limit") limit: Int? = null
    ): List<TraktPlaybackItem>
    
    @DELETE("sync/playback/{id}")
    suspend fun removePlaybackItem(
        @Header("Authorization") auth: String,
        @Header("trakt-api-key") clientId: String,
        @Header("trakt-api-version") version: String = "2",
        @Path("id") id: Long
    )
    
    @POST("sync/history")
    @retrofit2.http.Headers("Content-Type: application/json")
    suspend fun addToHistory(
        @Header("Authorization") auth: String,
        @Header("trakt-api-key") clientId: String,
        @Header("trakt-api-version") version: String = "2",
        @Body body: TraktHistoryBody
    ): TraktSyncResponse
    
    @POST("sync/history/remove")
    @retrofit2.http.Headers("Content-Type: application/json")
    suspend fun removeFromHistory(
        @Header("Authorization") auth: String,
        @Header("trakt-api-key") clientId: String,
        @Header("trakt-api-version") version: String = "2",
        @Body body: TraktHistoryBody
    ): TraktSyncResponse
    
    @POST("scrobble/start")
    @retrofit2.http.Headers("Content-Type: application/json")
    suspend fun scrobbleStart(
        @Header("Authorization") auth: String,
        @Header("trakt-api-key") clientId: String,
        @Header("trakt-api-version") version: String = "2",
        @Body body: TraktScrobbleBody
    ): TraktScrobbleResponse

    @POST("scrobble/pause")
    @retrofit2.http.Headers("Content-Type: application/json")
    suspend fun scrobblePause(
        @Header("Authorization") auth: String,
        @Header("trakt-api-key") clientId: String,
        @Header("trakt-api-version") version: String = "2",
        @Body body: TraktScrobbleBody
    ): TraktScrobbleResponse

    @POST("scrobble/stop")
    @retrofit2.http.Headers("Content-Type: application/json")
    suspend fun scrobbleStop(
        @Header("Authorization") auth: String,
        @Header("trakt-api-key") clientId: String,
        @Header("trakt-api-version") version: String = "2",
        @Body body: TraktScrobbleBody
    ): TraktScrobbleResponse

    // ========== Search ==========

    @GET("search/tmdb/{id}")
    suspend fun searchByTmdb(
        @Header("trakt-api-key") clientId: String,
        @Path("id") tmdbId: Int,
        @Query("type") type: String
    ): List<TraktSearchResult>

    // ========== Collection ==========

    @GET("sync/collection/movies")
    suspend fun getCollectionMovies(
        @Header("Authorization") auth: String,
        @Header("trakt-api-key") clientId: String,
        @Header("trakt-api-version") version: String = "2",
        @Query("extended") extended: String = "full"
    ): List<TraktCollectionMovie>

    @GET("sync/collection/shows")
    suspend fun getCollectionShows(
        @Header("Authorization") auth: String,
        @Header("trakt-api-key") clientId: String,
        @Header("trakt-api-version") version: String = "2",
        @Query("extended") extended: String = "full"
    ): List<TraktCollectionShow>

    @POST("sync/collection")
    @retrofit2.http.Headers("Content-Type: application/json")
    suspend fun addToCollection(
        @Header("Authorization") auth: String,
        @Header("trakt-api-key") clientId: String,
        @Header("trakt-api-version") version: String = "2",
        @Body body: TraktCollectionBody
    ): TraktSyncResponse

    @POST("sync/collection/remove")
    @retrofit2.http.Headers("Content-Type: application/json")
    suspend fun removeFromCollection(
        @Header("Authorization") auth: String,
        @Header("trakt-api-key") clientId: String,
        @Header("trakt-api-version") version: String = "2",
        @Body body: TraktCollectionBody
    ): TraktSyncResponse

    // ========== Ratings ==========

    @GET("sync/ratings/movies")
    suspend fun getRatingsMovies(
        @Header("Authorization") auth: String,
        @Header("trakt-api-key") clientId: String,
        @Header("trakt-api-version") version: String = "2"
    ): List<TraktRatingItem>

    @GET("sync/ratings/shows")
    suspend fun getRatingsShows(
        @Header("Authorization") auth: String,
        @Header("trakt-api-key") clientId: String,
        @Header("trakt-api-version") version: String = "2"
    ): List<TraktRatingItem>

    @GET("sync/ratings/episodes")
    suspend fun getRatingsEpisodes(
        @Header("Authorization") auth: String,
        @Header("trakt-api-key") clientId: String,
        @Header("trakt-api-version") version: String = "2"
    ): List<TraktRatingItem>

    @POST("sync/ratings")
    @retrofit2.http.Headers("Content-Type: application/json")
    suspend fun addRating(
        @Header("Authorization") auth: String,
        @Header("trakt-api-key") clientId: String,
        @Header("trakt-api-version") version: String = "2",
        @Body body: TraktRatingBody
    ): TraktSyncResponse

    @POST("sync/ratings/remove")
    @retrofit2.http.Headers("Content-Type: application/json")
    suspend fun removeRating(
        @Header("Authorization") auth: String,
        @Header("trakt-api-key") clientId: String,
        @Header("trakt-api-version") version: String = "2",
        @Body body: TraktRatingBody
    ): TraktSyncResponse

    // ========== Comments ==========

    @GET("movies/{id}/comments")
    suspend fun getMovieComments(
        @Header("trakt-api-key") clientId: String,
        @Header("trakt-api-version") version: String = "2",
        @Path("id") movieId: String,
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 10,
        @Query("sort") sort: String = "newest"
    ): List<TraktComment>

    @GET("shows/{id}/comments")
    suspend fun getShowComments(
        @Header("trakt-api-key") clientId: String,
        @Header("trakt-api-version") version: String = "2",
        @Path("id") showId: String,
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 10,
        @Query("sort") sort: String = "newest"
    ): List<TraktComment>

    @GET("shows/{id}/seasons/{season}/comments")
    suspend fun getSeasonComments(
        @Header("trakt-api-key") clientId: String,
        @Header("trakt-api-version") version: String = "2",
        @Path("id") showId: String,
        @Path("season") season: Int,
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 10,
        @Query("sort") sort: String = "newest"
    ): List<TraktComment>

    @GET("shows/{id}/seasons/{season}/episodes/{episode}/comments")
    suspend fun getEpisodeComments(
        @Header("trakt-api-key") clientId: String,
        @Header("trakt-api-version") version: String = "2",
        @Path("id") showId: String,
        @Path("season") season: Int,
        @Path("episode") episode: Int,
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 10,
        @Query("sort") sort: String = "newest"
    ): List<TraktComment>

    // ========== History ==========

    @GET("users/me/history/movies")
    @retrofit2.http.Headers("Content-Type: application/json")
    suspend fun getHistoryMovies(
        @Header("Authorization") auth: String,
        @Header("trakt-api-key") clientId: String,
        @Header("trakt-api-version") version: String = "2",
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 20,
        @Query("start_at") startAt: String? = null
    ): List<TraktHistoryItem>

    @GET("users/me/history/episodes")
    @retrofit2.http.Headers("Content-Type: application/json")
    suspend fun getHistoryEpisodes(
        @Header("Authorization") auth: String,
        @Header("trakt-api-key") clientId: String,
        @Header("trakt-api-version") version: String = "2",
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 20,
        @Query("start_at") startAt: String? = null
    ): List<TraktHistoryItem>

    @POST("sync/history/remove")
    @retrofit2.http.Headers("Content-Type: application/json")
    suspend fun removeFromHistoryByIds(
        @Header("Authorization") auth: String,
        @Header("trakt-api-key") clientId: String,
        @Header("trakt-api-version") version: String = "2",
        @Body body: TraktHistoryRemoveBody
    ): TraktSyncResponse

    // ========== Watchlist ==========
    
    @GET("users/me/watchlist")
    suspend fun getWatchlist(
        @Header("Authorization") auth: String,
        @Header("trakt-api-key") clientId: String,
        @Header("trakt-api-version") version: String = "2",
        @Query("type") type: String? = null,
        @Query("extended") extended: String = "full"
    ): List<TraktWatchlistItem>
    
    @POST("sync/watchlist")
    @retrofit2.http.Headers("Content-Type: application/json")
    suspend fun addToWatchlist(
        @Header("Authorization") auth: String,
        @Header("trakt-api-key") clientId: String,
        @Header("trakt-api-version") version: String = "2",
        @Body body: TraktWatchlistBody
    ): TraktSyncResponse
    
    @POST("sync/watchlist/remove")
    @retrofit2.http.Headers("Content-Type: application/json")
    suspend fun removeFromWatchlist(
        @Header("Authorization") auth: String,
        @Header("trakt-api-key") clientId: String,
        @Header("trakt-api-version") version: String = "2",
        @Body body: TraktWatchlistBody
    ): TraktSyncResponse
    
    // ========== Up Next ==========

    @GET("shows/{id}/progress/watched")
    suspend fun getShowProgress(
        @Header("Authorization") auth: String,
        @Header("trakt-api-key") clientId: String,
        @Header("trakt-api-version") version: String = "2",
        @Path("id") showId: String,
        @Query("hidden") hidden: String = "false",
        @Query("specials") specials: String = "false",
        @Query("count_specials") countSpecials: String = "false"
    ): TraktShowProgress
    
    // ========== Anime (Custom Lists) ==========
    
    @GET("lists/anime-streaming/anime-trending/items")
    suspend fun getTrendingAnime(
        @Header("trakt-api-key") clientId: String,
        @Header("trakt-api-version") version: String = "2"
    ): List<TraktListItem>
}

// ========== Request Bodies ==========

data class DeviceCodeRequest(
    @SerializedName("client_id") val clientId: String
)

data class TokenPollRequest(
    @SerializedName("code") val code: String,
    @SerializedName("client_id") val clientId: String,
    @SerializedName("client_secret") val clientSecret: String
)

data class RefreshTokenRequest(
    @SerializedName("refresh_token") val refreshToken: String,
    @SerializedName("client_id") val clientId: String,
    @SerializedName("client_secret") val clientSecret: String,
    @SerializedName("grant_type") val grantType: String = "refresh_token"
)

data class TraktHistoryBody(
    val movies: List<TraktMovieId>? = null,
    val shows: List<TraktShowId>? = null,
    val episodes: List<TraktEpisodeId>? = null
)

data class TraktWatchlistBody(
    val movies: List<TraktMovieId>? = null,
    val shows: List<TraktShowId>? = null
)

data class TraktScrobbleBody(
    val movie: TraktMovieId? = null,
    val episode: TraktEpisodeId? = null,
    val show: TraktShowId? = null,
    val progress: Float
)

data class TraktMovieId(
    val ids: TraktIds
)

data class TraktShowId(
    val ids: TraktIds
)

data class TraktEpisodeId(
    val ids: TraktIds? = null,
    val season: Int? = null,
    val number: Int? = null
)

data class TraktIds(
    val trakt: Int? = null,
    val tmdb: Int? = null,
    val imdb: String? = null,
    val slug: String? = null
)

// ========== Response Models ==========

data class TraktDeviceCode(
    @SerializedName("device_code") val deviceCode: String,
    @SerializedName("user_code") val userCode: String,
    @SerializedName("verification_url") val verificationUrl: String,
    @SerializedName("expires_in") val expiresIn: Int,
    val interval: Int
)

data class TraktToken(
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("refresh_token") val refreshToken: String,
    @SerializedName("expires_in") val expiresIn: Int,
    @SerializedName("created_at") val createdAt: Long,
    @SerializedName("token_type") val tokenType: String
)

/**
 * Last activities response for incremental sync
 * Each timestamp indicates when that activity type was last updated
 */
data class TraktLastActivities(
    val all: String?, // Overall last activity
    val movies: TraktActivityTimestamps?,
    val episodes: TraktActivityTimestamps?,
    val shows: TraktShowActivityTimestamps?,
    val seasons: TraktActivityTimestamps?,
    val comments: TraktActivityTimestamps?,
    val lists: TraktActivityTimestamps?,
    val watchlist: TraktActivityTimestamps?,
    val favorites: TraktActivityTimestamps?,
    val recommendations: TraktActivityTimestamps?,
    val collaborations: TraktActivityTimestamps?,
    val account: TraktActivityTimestamps?,
    @SerializedName("saved_filters") val savedFilters: TraktActivityTimestamps?
)

data class TraktActivityTimestamps(
    @SerializedName("watched_at") val watchedAt: String? = null,
    @SerializedName("collected_at") val collectedAt: String? = null,
    @SerializedName("rated_at") val ratedAt: String? = null,
    @SerializedName("watchlisted_at") val watchlistedAt: String? = null,
    @SerializedName("favorited_at") val favoritedAt: String? = null,
    @SerializedName("commented_at") val commentedAt: String? = null,
    @SerializedName("paused_at") val pausedAt: String? = null,
    @SerializedName("hidden_at") val hiddenAt: String? = null,
    @SerializedName("updated_at") val updatedAt: String? = null
)

data class TraktShowActivityTimestamps(
    @SerializedName("watched_at") val watchedAt: String? = null,
    @SerializedName("collected_at") val collectedAt: String? = null,
    @SerializedName("rated_at") val ratedAt: String? = null,
    @SerializedName("watchlisted_at") val watchlistedAt: String? = null,
    @SerializedName("favorited_at") val favoritedAt: String? = null,
    @SerializedName("commented_at") val commentedAt: String? = null,
    @SerializedName("hidden_at") val hiddenAt: String? = null
)

data class TraktWatchedMovie(
    val plays: Int,
    @SerializedName("last_watched_at") val lastWatchedAt: String?,
    @SerializedName("last_updated_at") val lastUpdatedAt: String?,
    val movie: TraktMovieInfo
)

data class TraktWatchedShow(
    val plays: Int,
    @SerializedName("last_watched_at") val lastWatchedAt: String?,
    @SerializedName("last_updated_at") val lastUpdatedAt: String?,
    val show: TraktShowInfo,
    val seasons: List<TraktWatchedSeason>?
)

data class TraktWatchedSeason(
    val number: Int,
    val episodes: List<TraktWatchedEpisode>
)

data class TraktWatchedEpisode(
    val number: Int,
    val plays: Int,
    @SerializedName("last_watched_at") val lastWatchedAt: String?
)

data class TraktPlaybackItem(
    val id: Long,
    val progress: Float,
    @SerializedName("paused_at") val pausedAt: String?,
    val type: String,
    val movie: TraktMovieInfo?,
    val episode: TraktEpisodeInfo?,
    val show: TraktShowInfo?
)

data class TraktMovieInfo(
    val title: String,
    val year: Int?,
    val ids: TraktIds
)

data class TraktShowInfo(
    val title: String,
    val year: Int?,
    val ids: TraktIds
)

data class TraktEpisodeInfo(
    val season: Int,
    val number: Int,
    val title: String?,
    val ids: TraktIds
)

data class TraktWatchlistItem(
    val rank: Int,
    @SerializedName("listed_at") val listedAt: String,
    val type: String,
    val movie: TraktMovieInfo?,
    val show: TraktShowInfo?
)

data class TraktShowProgress(
    val aired: Int,
    val completed: Int,
    @SerializedName("last_watched_at") val lastWatchedAt: String?,
    @SerializedName("next_episode") val nextEpisode: TraktNextEpisode?,
    val seasons: List<TraktProgressSeason>?
)

data class TraktNextEpisode(
    val season: Int,
    val number: Int,
    val title: String?,
    val ids: TraktIds
)

data class TraktProgressSeason(
    val number: Int,
    val aired: Int,
    val completed: Int,
    val episodes: List<TraktProgressEpisode>?
)

data class TraktProgressEpisode(
    val number: Int,
    val completed: Boolean,
    @SerializedName("last_watched_at") val lastWatchedAt: String?
)

data class TraktListItem(
    val rank: Int,
    val type: String,
    val show: TraktShowInfo?
)

data class TraktSyncResponse(
    val added: TraktSyncCounts?,
    val deleted: TraktSyncCounts?,
    val existing: TraktSyncCounts?,
    @SerializedName("not_found") val notFound: TraktSyncNotFound?
)

data class TraktSyncCounts(
    val movies: Int = 0,
    val shows: Int = 0,
    val episodes: Int = 0
)

data class TraktSyncNotFound(
    val movies: List<TraktMovieId>?,
    val shows: List<TraktShowId>?,
    val episodes: List<TraktEpisodeId>?
)

data class TraktScrobbleResponse(
    val id: Long,
    val action: String,
    val progress: Float,
    val movie: TraktMovieInfo?,
    val episode: TraktEpisodeInfo?,
    val show: TraktShowInfo?
)

// ========== Search Models ==========

data class TraktSearchResult(
    val type: String,
    val score: Float?,
    val movie: TraktMovieInfo?,
    val show: TraktShowInfo?
)

// ========== Collection Models ==========

data class TraktCollectionBody(
    val movies: List<TraktMovieId>? = null,
    val shows: List<TraktShowId>? = null
)

data class TraktCollectionMovie(
    @SerializedName("collected_at") val collectedAt: String?,
    @SerializedName("updated_at") val updatedAt: String?,
    val movie: TraktMovieInfo
)

data class TraktCollectionShow(
    @SerializedName("collected_at") val collectedAt: String?,
    @SerializedName("updated_at") val updatedAt: String?,
    val show: TraktShowInfo,
    val seasons: List<TraktCollectionSeason>?
)

data class TraktCollectionSeason(
    val number: Int,
    val episodes: List<TraktCollectionEpisode>
)

data class TraktCollectionEpisode(
    val number: Int,
    @SerializedName("collected_at") val collectedAt: String?
)

// ========== Rating Models ==========

data class TraktRatingBody(
    val movies: List<TraktRatingMovieItem>? = null,
    val shows: List<TraktRatingShowItem>? = null,
    val episodes: List<TraktRatingEpisodeItem>? = null
)

data class TraktRatingMovieItem(
    val rating: Int,
    @SerializedName("rated_at") val ratedAt: String? = null,
    val ids: TraktIds
)

data class TraktRatingShowItem(
    val rating: Int,
    @SerializedName("rated_at") val ratedAt: String? = null,
    val ids: TraktIds
)

data class TraktRatingEpisodeItem(
    val rating: Int,
    @SerializedName("rated_at") val ratedAt: String? = null,
    val ids: TraktIds? = null,
    val season: Int? = null,
    val number: Int? = null
)

data class TraktRatingItem(
    @SerializedName("rated_at") val ratedAt: String?,
    val rating: Int,
    val type: String,
    val movie: TraktMovieInfo?,
    val show: TraktShowInfo?,
    val episode: TraktEpisodeInfo?
)

// ========== Comment Models ==========

data class TraktComment(
    val id: Long,
    @SerializedName("parent_id") val parentId: Long?,
    val comment: String,
    val spoiler: Boolean,
    val review: Boolean,
    val replies: Int,
    val likes: Int,
    @SerializedName("user_stats") val userStats: TraktCommentUserStats?,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String?,
    val user: TraktUser?
)

data class TraktCommentUserStats(
    val rating: Int?,
    @SerializedName("play_count") val playCount: Int?,
    @SerializedName("completed_count") val completedCount: Int?
)

data class TraktUser(
    val username: String,
    val private: Boolean,
    val name: String?,
    val vip: Boolean?,
    @SerializedName("vip_ep") val vipEp: Boolean?,
    val ids: TraktUserIds?
)

data class TraktUserIds(
    val slug: String?
)

// ========== History Models ==========

data class TraktHistoryItem(
    val id: Long,
    @SerializedName("watched_at") val watchedAt: String,
    val action: String,
    val type: String,
    val movie: TraktMovieInfo?,
    val show: TraktShowInfo?,
    val episode: TraktEpisodeInfo?
)

data class TraktHistoryRemoveBody(
    val ids: List<Long>? = null,
    val movies: List<TraktMovieId>? = null,
    val shows: List<TraktShowId>? = null,
    val episodes: List<TraktEpisodeId>? = null,
    val seasons: List<TraktSeasonId>? = null
)

data class TraktSeasonId(
    val ids: TraktIds? = null,
    val seasons: List<TraktSeasonNumber>? = null
)

data class TraktSeasonNumber(
    val number: Int,
    val episodes: List<TraktEpisodeNumber>? = null
)

data class TraktEpisodeNumber(
    val number: Int
)

// ========== Bulk Watch Models ==========

data class TraktBulkShowBody(
    val shows: List<TraktBulkShowItem>
)

data class TraktBulkShowItem(
    val ids: TraktIds,
    val seasons: List<TraktBulkSeasonItem>? = null
)

data class TraktBulkSeasonItem(
    val number: Int,
    val episodes: List<TraktBulkEpisodeItem>? = null
)

data class TraktBulkEpisodeItem(
    val number: Int,
    @SerializedName("watched_at") val watchedAt: String? = null
)


