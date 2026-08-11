package com.arflix.tv.data.api

import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface SimklApi {

    // ========== Authentication ==========

    @GET("oauth/pin")
    suspend fun getPinCode(
        @Query("client_id") clientId: String
    ): SimklPinResponse

    @GET("oauth/pin/{code}")
    suspend fun pollPinToken(
        @Path("code") code: String,
        @Query("client_id") clientId: String
    ): SimklPinPollResponse

    // ========== Scrobble ==========

    @POST("scrobble/start")
    suspend fun scrobbleStart(
        @Header("Authorization") auth: String,
        @Header("simkl-api-key") clientId: String,
        @Body body: SimklScrobbleBody
    ): Response<SimklScrobbleResponse>

    @POST("scrobble/pause")
    suspend fun scrobblePause(
        @Header("Authorization") auth: String,
        @Header("simkl-api-key") clientId: String,
        @Body body: SimklScrobbleBody
    ): Response<SimklScrobbleResponse>

    @POST("scrobble/stop")
    suspend fun scrobbleStop(
        @Header("Authorization") auth: String,
        @Header("simkl-api-key") clientId: String,
        @Body body: SimklScrobbleBody
    ): Response<SimklScrobbleResponse>

    // ========== Sync & Watch History ==========

    @GET("sync/activities")
    suspend fun getActivities(
        @Header("Authorization") auth: String,
        @Header("simkl-api-key") clientId: String
    ): SimklActivitiesResponse

    @GET("sync/all-items/{type}")
    suspend fun getAllItems(
        @Header("Authorization") auth: String,
        @Header("simkl-api-key") clientId: String,
        @Path("type") type: String, // "movies", "shows", "anime"
        @Query("date_from") dateFrom: String? = null
    ): SimklAllItemsResponse

    @POST("sync/history")
    suspend fun addToHistory(
        @Header("Authorization") auth: String,
        @Header("simkl-api-key") clientId: String,
        @Body body: SimklSyncHistoryBody,
        @Query("allow_rewatch") allowRewatch: String? = null
    ): Response<SimklSyncResponse>

    @POST("sync/history/remove")
    suspend fun removeFromHistory(
        @Header("Authorization") auth: String,
        @Header("simkl-api-key") clientId: String,
        @Body body: SimklSyncHistoryBody
    ): Response<SimklSyncResponse>

    @POST("sync/watchlist")
    suspend fun addToWatchlist(
        @Header("Authorization") auth: String,
        @Header("simkl-api-key") clientId: String,
        @Body body: SimklSyncWatchlistBody
    ): Response<SimklSyncResponse>

    @POST("sync/watchlist/remove")
    suspend fun removeFromWatchlist(
        @Header("Authorization") auth: String,
        @Header("simkl-api-key") clientId: String,
        @Body body: SimklSyncWatchlistBody
    ): Response<SimklSyncResponse>
}

// Data Transfer Objects

data class SimklPinResponse(
    @SerializedName("user_code") val userCode: String,
    @SerializedName("verification_url") val verificationUrl: String,
    @SerializedName("expires_in") val expiresIn: Int = 600,
    @SerializedName("interval") val interval: Int = 5,
    @SerializedName("device_code") val deviceCode: String? = null
)

data class SimklPinPollResponse(
    @SerializedName("result") val result: String, // "KO", "pending", "OK"
    @SerializedName("access_token") val accessToken: String? = null,
    @SerializedName("token_type") val tokenType: String? = null,
    @SerializedName("expires_in") val expiresIn: Long? = null
)

data class SimklIds(
    @SerializedName("simkl") val simkl: Long? = null,
    @SerializedName("tmdb") val tmdb: Int? = null,
    @SerializedName("imdb") val imdb: String? = null,
    @SerializedName("tvdb") val tvdb: String? = null
)

data class SimklMovieRef(
    @SerializedName("title") val title: String? = null,
    @SerializedName("year") val year: Int? = null,
    @SerializedName("ids") val ids: SimklIds
)

data class SimklEpisodeRef(
    @SerializedName("number") val number: Int? = null,
    @SerializedName("ids") val ids: SimklIds? = null
)

data class SimklShowRef(
    @SerializedName("title") val title: String? = null,
    @SerializedName("year") val year: Int? = null,
    @SerializedName("ids") val ids: SimklIds,
    @SerializedName("seasons") val seasons: List<SimklSeasonRef>? = null
)

data class SimklSeasonRef(
    @SerializedName("number") val number: Int,
    @SerializedName("episodes") val episodes: List<SimklEpisodeRef>
)

data class SimklScrobbleBody(
    @SerializedName("movie") val movie: SimklMovieRef? = null,
    @SerializedName("show") val show: SimklShowRef? = null,
    @SerializedName("episode") val episode: SimklEpisodeRef? = null,
    @SerializedName("progress") val progress: Float // 0.0 - 100.0
)

data class SimklScrobbleResponse(
    @SerializedName("action") val action: String? = null,
    @SerializedName("progress") val progress: Float? = null
)

data class SimklActivitiesResponse(
    @SerializedName("all") val all: String? = null,
    @SerializedName("movies") val movies: SimklActivityGroup? = null,
    @SerializedName("shows") val shows: SimklActivityGroup? = null,
    @SerializedName("anime") val anime: SimklActivityGroup? = null
)

data class SimklActivityGroup(
    @SerializedName("all") val all: String? = null,
    @SerializedName("watched_at") val watchedAt: String? = null,
    @SerializedName("rated_at") val ratedAt: String? = null,
    @SerializedName("plantowatch") val planToWatch: String? = null
)

data class SimklAllItemsResponse(
    @SerializedName("movies") val movies: List<SimklHistoryMovieItem>? = null,
    @SerializedName("shows") val shows: List<SimklHistoryShowItem>? = null,
    @SerializedName("anime") val anime: List<SimklHistoryShowItem>? = null
)

data class SimklHistoryMovieItem(
    @SerializedName("last_watched_at") val lastWatchedAt: String? = null,
    @SerializedName("user_rating") val userRating: Int? = null,
    @SerializedName("status") val status: String? = null, // "completed", "watching", "plantowatch", "hold", "dropped"
    @SerializedName("movie") val movie: SimklMovieRef? = null
)

data class SimklHistoryShowItem(
    @SerializedName("last_watched_at") val lastWatchedAt: String? = null,
    @SerializedName("status") val status: String? = null,
    @SerializedName("show") val show: SimklShowRef? = null,
    @SerializedName("seasons") val seasons: List<SimklHistorySeasonItem>? = null
)

data class SimklHistorySeasonItem(
    @SerializedName("number") val number: Int,
    @SerializedName("episodes") val episodes: List<SimklHistoryEpisodeItem>
)

data class SimklHistoryEpisodeItem(
    @SerializedName("number") val number: Int,
    @SerializedName("watched_at") val watchedAt: String? = null
)

data class SimklSyncHistoryBody(
    @SerializedName("movies") val movies: List<SimklMovieRef>? = null,
    @SerializedName("shows") val shows: List<SimklShowRef>? = null,
    @SerializedName("episodes") val episodes: List<SimklEpisodeRef>? = null
)

data class SimklSyncWatchlistBody(
    @SerializedName("movies") val movies: List<SimklMovieRef>? = null,
    @SerializedName("shows") val shows: List<SimklShowRef>? = null
)

data class SimklSyncResponse(
    @SerializedName("added") val added: SimklSyncCount? = null,
    @SerializedName("deleted") val deleted: SimklSyncCount? = null,
    @SerializedName("not_found") val notFound: SimklSyncCount? = null
)

data class SimklSyncCount(
    @SerializedName("movies") val movies: Int = 0,
    @SerializedName("shows") val shows: Int = 0,
    @SerializedName("episodes") val episodes: Int = 0
)
