package com.arflix.tv.data.api

import com.google.gson.annotations.SerializedName
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * MDBList API (https://api.mdblist.com). Optional per-profile tracking and ratings integration.
 *
 * Auth is supported via OAuth 2.0 Bearer tokens (Device Code flow) or legacy user API keys.
 * Progress is 0-100 and comes back as a String ("30.00") — parse tolerantly.
 */
interface MdbListApi {

    // ===== OAuth Device Code & Token =====

    @POST("oauth/device-authorization/")
    @FormUrlEncoded
    suspend fun requestDeviceAuthorization(
        @Field("client_id") clientId: String,
        @Field("scope") scope: String = "write"
    ): MdbDeviceAuthorizationResponse

    @POST("oauth/token/")
    @FormUrlEncoded
    suspend fun pollDeviceToken(
        @Field("grant_type") grantType: String = "urn:ietf:params:oauth:grant-type:device_code",
        @Field("device_code") deviceCode: String,
        @Field("client_id") clientId: String
    ): MdbTokenResponse

    @POST("oauth/token/")
    @FormUrlEncoded
    suspend fun refreshToken(
        @Field("grant_type") grantType: String = "refresh_token",
        @Field("refresh_token") refreshToken: String,
        @Field("client_id") clientId: String
    ): MdbTokenResponse

    // ===== User & Ratings =====

    @GET("user")
    suspend fun getUser(
        @Header("Authorization") authHeader: String? = null,
        @Query("apikey") apiKey: String? = null
    ): MdbUser

    @GET("sync/last_activities")
    suspend fun getLastActivities(
        @Header("Authorization") authHeader: String? = null,
        @Query("apikey") apiKey: String? = null
    ): MdbLastActivities

    /** Ratings and metadata for one TMDB title. mediaType is "movie" or "show". */
    @GET("tmdb/{mediaType}/{mediaId}/")
    suspend fun getMediaInfo(
        @Path("mediaType") mediaType: String,
        @Path("mediaId") mediaId: Int,
        @Header("Authorization") authHeader: String? = null,
        @Query("apikey") apiKey: String? = null
    ): MdbMediaInfo

    // ===== Watchlist =====

    /** Unified flat array of movies + shows. */
    @GET("watchlist/items")
    suspend fun getWatchlistItems(
        @Header("Authorization") authHeader: String? = null,
        @Query("apikey") apiKey: String? = null,
        @Query("limit") limit: Int = 1000,
        @Query("offset") offset: Int = 0,
        @Query("unified") unified: String = "true"
    ): List<MdbWatchlistItem>

    /** action = "add" | "remove". Body uses flat tmdb refs. */
    @POST("watchlist/items/{action}")
    @Headers("Content-Type: application/json")
    suspend fun modifyWatchlist(
        @Path("action") action: String,
        @Header("Authorization") authHeader: String? = null,
        @Query("apikey") apiKey: String? = null,
        @Body body: MdbWatchlistModifyBody
    ): MdbCountResponse

    // ===== Watched (Trakt-style ids objects) =====

    @GET("sync/watched")
    suspend fun getWatched(
        @Header("Authorization") authHeader: String? = null,
        @Query("apikey") apiKey: String? = null,
        @Query("limit") limit: Int = 1000,
        @Query("offset") offset: Int = 0
    ): MdbWatchedResponse

    @POST("sync/watched")
    @Headers("Content-Type: application/json")
    suspend fun addWatched(
        @Header("Authorization") authHeader: String? = null,
        @Query("apikey") apiKey: String? = null,
        @Body body: MdbWatchedBody
    ): MdbCountResponse

    @POST("sync/watched/remove")
    @Headers("Content-Type: application/json")
    suspend fun removeWatched(
        @Header("Authorization") authHeader: String? = null,
        @Query("apikey") apiKey: String? = null,
        @Body body: MdbWatchedBody
    ): MdbCountResponse

    // ===== Scrobble / Continue Watching =====

    /**
     * Paused sessions that power Continue Watching.
     *
     * The response carries `cache-control: max-age=900`, so OkHttp will answer
     * this from disk for fifteen minutes. That is fine for a routine read and
     * wrong for a deliberate refresh, hence [cacheControl]: a refresh asked for
     * because the app just came back to the foreground has to reach the server,
     * or another device's progress stays invisible for a quarter of an hour.
     */
    @GET("sync/playback")
    suspend fun getPlayback(
        @Header("Authorization") authHeader: String? = null,
        @Query("apikey") apiKey: String? = null,
        @Header("Cache-Control") cacheControl: String? = null
    ): List<MdbPlaybackItem>

    /** action = "start" | "pause" | "stop". */
    @POST("scrobble/{action}")
    @Headers("Content-Type: application/json")
    suspend fun scrobble(
        @Path("action") action: String,
        @Header("Authorization") authHeader: String? = null,
        @Query("apikey") apiKey: String? = null,
        @Body body: MdbScrobbleBody
    ): MdbScrobbleResponse

    /** Clear a paused session (by playback id or by media ids). */
    @POST("scrobble/clear")
    @Headers("Content-Type: application/json")
    suspend fun scrobbleClear(
        @Header("Authorization") authHeader: String? = null,
        @Query("apikey") apiKey: String? = null,
        @Body body: MdbScrobbleClearBody
    ): MdbScrobbleClearResponse
}

// ========== OAuth Response Models ==========

data class MdbDeviceAuthorizationResponse(
    @SerializedName("device_code") val deviceCode: String,
    @SerializedName("user_code") val userCode: String,
    @SerializedName("verification_uri") val verificationUri: String,
    @SerializedName("verification_uri_complete") val verificationUriComplete: String? = null,
    @SerializedName("expires_in") val expiresIn: Int = 300,
    val interval: Int = 5
)

data class MdbTokenResponse(
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("token_type") val tokenType: String = "Bearer",
    @SerializedName("expires_in") val expiresIn: Long = 2592000L,
    @SerializedName("refresh_token") val refreshToken: String? = null,
    val scope: String? = null
)

// ========== Shared ids ==========

data class MdbIds(
    val tmdb: Int? = null,
    val imdb: String? = null,
    val trakt: Int? = null,
    val tvdb: Int? = null,
    val mdblist: String? = null
)

// ========== User / activities ==========

data class MdbUser(
    val username: String? = null,
    @SerializedName("user_id") val userId: Long? = null,
    val name: String? = null
)

data class MdbLastActivities(
    @SerializedName("watchlisted_at") val watchlistedAt: String? = null,
    @SerializedName("watched_at") val watchedAt: String? = null,
    @SerializedName("season_watched_at") val seasonWatchedAt: String? = null,
    @SerializedName("episode_watched_at") val episodeWatchedAt: String? = null,
    @SerializedName("paused_at") val pausedAt: String? = null,
    @SerializedName("episode_paused_at") val episodePausedAt: String? = null,
    @SerializedName("rated_at") val ratedAt: String? = null
)

data class MdbMediaInfo(
    val ratings: List<MdbRating>? = null
)

data class MdbRating(
    val source: String? = null,
    val value: com.google.gson.JsonElement? = null,
    val score: com.google.gson.JsonElement? = null,
    val votes: Long? = null,
    val url: String? = null
)

// ========== Watchlist ==========

data class MdbWatchlistItem(
    val id: Int? = null,
    val mediatype: String? = null, // "movie" | "show"
    val ids: MdbIds? = null,
    val title: String? = null,
    @SerializedName("release_year") val releaseYear: Int? = null,
    @SerializedName("release_date") val releaseDate: String? = null,
    @SerializedName("watchlist_at") val watchlistAt: String? = null
)

data class MdbWatchlistModifyBody(
    val movies: List<MdbTmdbRef>? = null,
    val shows: List<MdbTmdbRef>? = null
)

data class MdbTmdbRef(val tmdb: Int)

// ========== Watched ==========

data class MdbWatchedBody(
    val movies: List<MdbIdsItem>? = null,
    val shows: List<MdbWatchedShowRef>? = null
)

data class MdbIdsItem(val ids: MdbIds)

data class MdbWatchedShowRef(
    val ids: MdbIds,
    val seasons: List<MdbWatchedSeasonRef>? = null
)

data class MdbWatchedSeasonRef(
    val number: Int,
    val episodes: List<MdbWatchedEpisodeRef>? = null
)

data class MdbWatchedEpisodeRef(val number: Int)

data class MdbWatchedResponse(
    val movies: List<MdbWatchedMovieRow>? = null,
    val episodes: List<MdbWatchedEpisodeRow>? = null,
    val pagination: MdbPagination? = null
)

data class MdbWatchedMovieRow(
    @SerializedName("last_watched_at") val lastWatchedAt: String? = null,
    val movie: MdbMovieInfo? = null
)

data class MdbWatchedEpisodeRow(
    @SerializedName("last_watched_at") val lastWatchedAt: String? = null,
    val episode: MdbEpisodeInfo? = null
)

data class MdbPagination(
    val offset: Int = 0,
    val limit: Int = 0,
    @SerializedName("has_more") val hasMore: Boolean = false
)

// ========== Playback / scrobble ==========

data class MdbMovieInfo(
    val title: String? = null,
    val year: Int? = null,
    val ids: MdbIds? = null
)

data class MdbShowInfo(
    val title: String? = null,
    val year: Int? = null,
    val ids: MdbIds? = null
)

data class MdbEpisodeInfo(
    val season: Int? = null,
    val number: Int? = null,
    val name: String? = null,
    val ids: MdbIds? = null,
    val show: MdbShowInfo? = null
)

data class MdbPlaybackItem(
    val id: Long? = null,
    val progress: String? = null, // "30.00"
    @SerializedName("updated_at") val updatedAt: String? = null,
    @SerializedName("updated_at_ts") val updatedAtTs: Long? = null,
    @SerializedName("paused_at") val pausedAt: String? = null,
    val runtime: Int? = null, // minutes
    val type: String? = null, // "movie" | "episode"
    val movie: MdbMovieInfo? = null,
    val show: MdbShowInfo? = null,
    val episode: MdbEpisodeInfo? = null
)

data class MdbScrobbleBody(
    /** 0-100. Fractional values are accepted and stored (verified: 25.5 -> "25.50"). */
    val progress: Float,
    val movie: MdbScrobbleMovie? = null,
    val show: MdbScrobbleShow? = null
)

data class MdbScrobbleMovie(val ids: MdbIds)

data class MdbScrobbleShow(
    val ids: MdbIds,
    val season: MdbScrobbleSeason
)

data class MdbScrobbleSeason(
    val number: Int,
    val episode: MdbScrobbleEpisodeNumber
)

data class MdbScrobbleEpisodeNumber(val number: Int)

data class MdbScrobbleResponse(
    val action: String? = null,
    val progress: String? = null
)

data class MdbScrobbleClearBody(
    val id: Long? = null,
    val movie: MdbScrobbleMovie? = null,
    val show: MdbScrobbleShow? = null
)

data class MdbScrobbleClearResponse(
    val action: String? = null,
    val deleted: Boolean? = null
)

// ========== Generic count response ==========

data class MdbCountResponse(
    val added: MdbCounts? = null,
    val removed: MdbCounts? = null,
    val updated: MdbCounts? = null,
    val existing: MdbCounts? = null
)

data class MdbCounts(
    val movies: Int = 0,
    val shows: Int = 0,
    val seasons: Int = 0,
    val episodes: Int = 0
)
