package com.arflix.tv.data.api

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Jikan v4 is an unofficial REST API for MyAnimeList.net data.
 *
 * Used by Majo Stream to display the MAL community score next to IMDB/TMDB ratings
 * on anime details pages. See issue #45.
 *
 * Base URL: `https://api.jikan.moe/v4/`
 * Rate limit: ~3 req/s, 60 req/min (unofficial, subject to change). Majo Stream
 * caches scores in memory so a typical details load performs at most one
 * Jikan request per unique MAL ID per session.
 *
 * Docs: https://docs.api.jikan.moe/
 */
interface JikanApi {
    @GET("anime/{malId}")
    suspend fun getAnime(@Path("malId") malId: Int): JikanAnimeResponse
}

@Keep
data class JikanAnimeResponse(
    @SerializedName("data") val data: JikanAnimeData?
)

@Keep
data class JikanAnimeData(
    @SerializedName("mal_id") val malId: Int?,
    @SerializedName("title") val title: String?,
    /** Community score 0-10 with 2 decimal places. Null if the entry is too new or unscored. */
    @SerializedName("score") val score: Double?,
    @SerializedName("scored_by") val scoredBy: Int?
)
