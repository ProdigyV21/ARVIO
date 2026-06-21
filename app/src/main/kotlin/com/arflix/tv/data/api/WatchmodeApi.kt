package com.arflix.tv.data.api

import com.google.gson.annotations.SerializedName
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface WatchmodeApi {
    @GET("v1/list-titles/")
    suspend fun listTitles(
        @Header("X-API-Key") apiKey: String,
        @Query("types") types: String,
        @Query("regions") regions: String,
        @Query("source_ids") sourceIds: String,
        @Query("source_types") sourceTypes: String? = null,
        @Query("sort_by") sortBy: String = "popularity_desc",
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 100
    ): WatchmodeListTitlesResponse
}

data class WatchmodeListTitlesResponse(
    val titles: List<WatchmodeTitle> = emptyList(),
    val page: Int = 1,
    @SerializedName("total_pages") val totalPages: Int = 1,
    @SerializedName("total_results") val totalResults: Int = 0
)

data class WatchmodeTitle(
    val id: Int = 0,
    val title: String = "",
    val year: Int? = null,
    @SerializedName("imdb_id") val imdbId: String? = null,
    @SerializedName("tmdb_id") val tmdbId: Int? = null,
    @SerializedName("tmdb_type") val tmdbType: String? = null,
    val type: String? = null
)
