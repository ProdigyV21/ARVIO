package com.arflix.tv.data.api

import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.HTTP
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

    @POST("users/settings")
    suspend fun getUserSettings(
        @Header("Authorization") auth: String,
        @Header("simkl-api-key") clientId: String
    ): SimklUserSettingsResponse

    @GET("sync/activities")
    suspend fun getActivities(
        @Header("Authorization") auth: String,
        @Header("simkl-api-key") clientId: String
    ): SimklActivitiesResponse

    @GET("sync/all-items/{type}/{status}")
    suspend fun getAllItems(
        @Header("Authorization") auth: String,
        @Header("simkl-api-key") clientId: String,
        @Path("type") type: String,
        @Path("status") status: String = "all",
        @Query("date_from") dateFrom: String? = null,
        @Query("extended") extended: String = "full",
        @Query("episode_watched_at") episodeWatchedAt: String = "yes",
        @Query("include_all_episodes") includeAllEpisodes: String = "yes",
        @Query("next_watch_info") nextWatchInfo: String = "yes"
    ): JsonElement

    @GET("sync/all-items/{type}")
    suspend fun getAllItemIds(
        @Header("Authorization") auth: String,
        @Header("simkl-api-key") clientId: String,
        @Path("type") type: String,
        @Query("extended") extended: String = "ids_only"
    ): JsonElement

    @GET("sync/all-items")
    suspend fun getAllItemsDelta(
        @Header("Authorization") auth: String,
        @Header("simkl-api-key") clientId: String,
        @Query("date_from") dateFrom: String,
        @Query("extended") extended: String = "full_anime_seasons",
        @Query("episode_watched_at") episodeWatchedAt: String = "yes",
        @Query("include_all_episodes") includeAllEpisodes: String = "yes",
        @Query("next_watch_info") nextWatchInfo: String = "yes"
    ): JsonElement

    @GET("sync/playback")
    suspend fun getPlayback(
        @Header("Authorization") auth: String,
        @Header("simkl-api-key") clientId: String
    ): List<SimklPlaybackItem>

    @HTTP(method = "DELETE", path = "sync/playback/{id}", hasBody = false)
    suspend fun deletePlayback(
        @Header("Authorization") auth: String,
        @Header("simkl-api-key") clientId: String,
        @Path("id") id: Long
    ): Response<ResponseBody>

    @POST("sync/history")
    suspend fun addToHistory(
        @Header("Authorization") auth: String,
        @Header("simkl-api-key") clientId: String,
        @Body body: SimklSyncHistoryBody,
        @Query("allow_rewatch") allowRewatch: String? = null
    ): Response<ResponseBody>

    @POST("sync/history/remove")
    suspend fun removeFromHistory(
        @Header("Authorization") auth: String,
        @Header("simkl-api-key") clientId: String,
        @Body body: SimklSyncHistoryBody
    ): Response<ResponseBody>

    @POST("sync/add-to-list")
    suspend fun addToList(
        @Header("Authorization") auth: String,
        @Header("simkl-api-key") clientId: String,
        @Body body: SimklAddToListBody
    ): Response<ResponseBody>
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
    @SerializedName("expires_in") val expiresIn: Long? = null,
    @SerializedName("device_code") val deviceCode: String? = null
)

data class SimklIds(
    @SerializedName(value = "simkl", alternate = ["simkl_id"]) val simkl: Long? = null,
    @SerializedName("slug") val slug: String? = null,
    @SerializedName("tmdb") val tmdb: Int? = null,
    @SerializedName("imdb") val imdb: String? = null,
    @SerializedName("tvdb") val tvdb: String? = null
)

data class SimklMovieRef(
    @SerializedName("title") val title: String? = null,
    @SerializedName("year") val year: Int? = null,
    @SerializedName("ids") val ids: SimklIds,
    @SerializedName("runtime") val runtime: Int? = null,
    @SerializedName("poster") val poster: String? = null
)

data class SimklEpisodeRef(
    @SerializedName("season") val season: Int? = null,
    @SerializedName(value = "number", alternate = ["episode"]) val number: Int? = null,
    @SerializedName("ids") val ids: SimklIds? = null
)

data class SimklShowRef(
    @SerializedName("title") val title: String? = null,
    @SerializedName("year") val year: Int? = null,
    @SerializedName("ids") val ids: SimklIds,
    @SerializedName("seasons") val seasons: List<SimklSeasonRef>? = null,
    @SerializedName("runtime") val runtime: Int? = null,
    @SerializedName("poster") val poster: String? = null,
    @SerializedName("use_tvdb_anime_seasons") val useTvdbAnimeSeasons: Boolean? = null
)

data class SimklSeasonRef(
    @SerializedName("number") val number: Int,
    @SerializedName("episodes") val episodes: List<SimklEpisodeRef>
)

data class SimklScrobbleBody(
    @SerializedName("movie") val movie: SimklMovieRef? = null,
    @SerializedName("show") val show: SimklShowRef? = null,
    @SerializedName("anime") val anime: SimklShowRef? = null,
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
    @SerializedName(value = "tv_shows", alternate = ["shows"]) val shows: SimklActivityGroup? = null,
    @SerializedName("anime") val anime: SimklActivityGroup? = null
)

data class SimklActivityGroup(
    @SerializedName("all") val all: String? = null,
    @SerializedName("watched_at") val watchedAt: String? = null,
    @SerializedName("rated_at") val ratedAt: String? = null,
    @SerializedName("plantowatch") val planToWatch: String? = null,
    @SerializedName("watching") val watching: String? = null,
    @SerializedName("completed") val completed: String? = null,
    @SerializedName("playback") val playback: String? = null,
    @SerializedName("removed_from_list") val removedFromList: String? = null
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
    @SerializedName("seasons") val seasons: List<SimklHistorySeasonItem>? = null,
    @SerializedName("next_to_watch") val nextToWatch: String? = null,
    @SerializedName("next_to_watch_info") val nextToWatchInfo: SimklNextToWatchInfo? = null,
    @SerializedName("watched_episodes_count") val watchedEpisodesCount: Int? = null,
    @SerializedName("total_episodes_count") val totalEpisodesCount: Int? = null,
    @SerializedName("not_aired_episodes_count") val notAiredEpisodesCount: Int? = null
)

data class SimklNextToWatchInfo(
    @SerializedName("title") val title: String? = null,
    @SerializedName("season") val season: Int? = null,
    @SerializedName("episode") val episode: Int? = null,
    @SerializedName("date") val date: String? = null
)

data class SimklPlaybackItem(
    @SerializedName("id") val id: Long? = null,
    @SerializedName("progress") val progress: Float = 0f,
    @SerializedName("paused_at") val pausedAt: String? = null,
    @SerializedName("type") val type: String? = null,
    @SerializedName("movie") val movie: SimklMovieRef? = null,
    @SerializedName("show") val show: SimklShowRef? = null,
    @SerializedName("anime") val anime: SimklShowRef? = null,
    @SerializedName("episode") val episode: SimklPlaybackEpisode? = null
)

data class SimklPlaybackEpisode(
    @SerializedName("season") val season: Int? = null,
    @SerializedName(value = "number", alternate = ["episode"]) val number: Int? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("ids") val ids: SimklIds? = null
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
    @SerializedName("anime") val anime: List<SimklShowRef>? = null,
    @SerializedName("episodes") val episodes: List<SimklEpisodeRef>? = null
)

data class SimklAddToListMovie(
    @SerializedName("to") val to: String = "plantowatch",
    @SerializedName("ids") val ids: SimklIds
)

data class SimklAddToListShow(
    @SerializedName("to") val to: String = "plantowatch",
    @SerializedName("ids") val ids: SimklIds,
    @SerializedName("use_tvdb_anime_seasons") val useTvdbAnimeSeasons: Boolean? = null
)

data class SimklAddToListBody(
    @SerializedName("movies") val movies: List<SimklAddToListMovie>? = null,
    @SerializedName("shows") val shows: List<SimklAddToListShow>? = null,
    @SerializedName("anime") val anime: List<SimklAddToListShow>? = null
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

data class SimklUserSettingsResponse(
    @SerializedName("user") val user: SimklUser? = null
)

data class SimklUser(
    @SerializedName("name") val name: String? = null,
    @SerializedName("username") val username: String? = null
)

