package com.arflix.tv.data.api

import com.google.gson.annotations.SerializedName
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * TMDB API interface
 */
interface TmdbApi {
    
    @GET("trending/movie/day")
    suspend fun getTrendingMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String? = null,
        @Query("page") page: Int = 1
    ): TmdbListResponse

    @GET("trending/tv/day")
    suspend fun getTrendingTv(
        @Query("api_key") apiKey: String,
        @Query("language") language: String? = null,
        @Query("page") page: Int = 1
    ): TmdbListResponse
    
    @GET("discover/tv")
    suspend fun discoverTv(
        @Query("api_key") apiKey: String,
        @Query("with_watch_providers") watchProviders: Int? = null,
        @Query("watch_region") watchRegion: String = "US",
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("with_genres") genres: String? = null,
        @Query("with_original_language") originalLanguage: String? = null,
        @Query("first_air_date_year") year: Int? = null,
        @Query("vote_count.gte") minVoteCount: Int? = null,
        @Query("with_keywords") keywords: String? = null,
        @Query("air_date.gte") airDateGte: String? = null,
        @Query("air_date.lte") airDateLte: String? = null,
        @Query("language") language: String? = null,
        @Query("page") page: Int = 1
    ): TmdbListResponse

    @GET("discover/movie")
    suspend fun discoverMovies(
        @Query("api_key") apiKey: String,
        @Query("with_genres") genres: String? = null,
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("vote_count.gte") minVoteCount: Int? = null,
        @Query("with_keywords") keywords: String? = null,
        @Query("with_original_language") originalLanguage: String? = null,
        @Query("primary_release_year") year: Int? = null,
        @Query("release_date.gte") releaseDateGte: String? = null,
        @Query("release_date.lte") releaseDateLte: String? = null,
        @Query("language") language: String? = null,
        @Query("page") page: Int = 1
    ): TmdbListResponse
    
    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String? = null
    ): TmdbMovieDetails
    
    @GET("tv/{tv_id}")
    suspend fun getTvDetails(
        @Path("tv_id") tvId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String? = null
    ): TmdbTvDetails
    
    @GET("tv/{tv_id}/season/{season_number}")
    suspend fun getTvSeason(
        @Path("tv_id") tvId: Int,
        @Path("season_number") seasonNumber: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String? = null
    ): TmdbSeasonDetails
    
    @GET("{media_type}/{id}/credits")
    suspend fun getCredits(
        @Path("media_type") mediaType: String,
        @Path("id") id: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String? = null
    ): TmdbCreditsResponse
    
    @GET("{media_type}/{id}/similar")
    suspend fun getSimilar(
        @Path("media_type") mediaType: String,
        @Path("id") id: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String? = null
    ): TmdbListResponse

    @GET("{media_type}/{id}/recommendations")
    suspend fun getRecommendations(
        @Path("media_type") mediaType: String,
        @Path("id") id: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String? = null
    ): TmdbListResponse

    @GET("{media_type}/{id}/images")
    suspend fun getImages(
        @Path("media_type") mediaType: String,
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ): TmdbImagesResponse
    
    @GET("{media_type}/{id}/videos")
    suspend fun getVideos(
        @Path("media_type") mediaType: String,
        @Path("id") id: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String? = null
    ): TmdbVideosResponse
    
    @GET("person/{person_id}")
    suspend fun getPersonDetails(
        @Path("person_id") personId: Int,
        @Query("api_key") apiKey: String,
        @Query("append_to_response") appendToResponse: String = "combined_credits",
        @Query("language") language: String? = null
    ): TmdbPersonDetails

    @GET("movie/{movie_id}/external_ids")
    suspend fun getMovieExternalIds(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): TmdbExternalIds

    @GET("tv/{tv_id}/external_ids")
    suspend fun getTvExternalIds(
        @Path("tv_id") tvId: Int,
        @Query("api_key") apiKey: String
    ): TmdbExternalIds

    @GET("movie/{movie_id}/watch/providers")
    suspend fun getMovieWatchProviders(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): TmdbWatchProvidersResponse

    @GET("tv/{tv_id}/watch/providers")
    suspend fun getTvWatchProviders(
        @Path("tv_id") tvId: Int,
        @Query("api_key") apiKey: String
    ): TmdbWatchProvidersResponse
    
    @GET("search/multi")
    suspend fun searchMulti(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
        @Query("language") language: String? = null,
        @Query("page") page: Int = 1
    ): TmdbListResponse

    @GET("find/{external_id}")
    suspend fun findByExternalId(
        @Path("external_id") externalId: String,
        @Query("api_key") apiKey: String,
        @Query("external_source") externalSource: String = "imdb_id"
    ): TmdbFindResponse

    @GET("{media_type}/{id}/reviews")
    suspend fun getReviews(
        @Path("media_type") mediaType: String,
        @Path("id") id: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String? = null
    ): TmdbReviewsResponse
}

// Response data classes

data class TmdbListResponse(
    val page: Int = 1,
    val results: List<TmdbMediaItem> = emptyList(),
    @SerializedName("total_pages") val totalPages: Int = 1,
    @SerializedName("total_results") val totalResults: Int = 0
)

data class TmdbMediaItem(
    val id: Int = 0,
    val title: String? = null,
    val name: String? = null,
    val overview: String? = null,
    @SerializedName("release_date") val releaseDate: String? = null,
    @SerializedName("first_air_date") val firstAirDate: String? = null,
    @SerializedName("poster_path") val posterPath: String? = null,
    @SerializedName("backdrop_path") val backdropPath: String? = null,
    @SerializedName("vote_average") val voteAverage: Float = 0f,
    @SerializedName("vote_count") val voteCount: Int = 0,
    @SerializedName("genre_ids") val genreIds: List<Int> = emptyList(),
    @SerializedName("original_language") val originalLanguage: String? = null,
    @SerializedName("media_type") val mediaType: String? = null,
    val adult: Boolean = false,
    val popularity: Float = 0f,
    val character: String? = null
)

data class TmdbMovieDetails(
    val id: Int = 0,
    val title: String = "",
    val overview: String? = null,
    @SerializedName("release_date") val releaseDate: String? = null,
    @SerializedName("poster_path") val posterPath: String? = null,
    @SerializedName("backdrop_path") val backdropPath: String? = null,
    @SerializedName("original_language") val originalLanguage: String? = null,
    @SerializedName("vote_average") val voteAverage: Float = 0f,
    val runtime: Int? = null,
    val budget: Long = 0,
    val genres: List<TmdbGenre> = emptyList(),
    val status: String? = null,
    val adult: Boolean = false
)

data class TmdbTvDetails(
    val id: Int = 0,
    val name: String = "",
    val overview: String? = null,
    @SerializedName("first_air_date") val firstAirDate: String? = null,
    @SerializedName("poster_path") val posterPath: String? = null,
    @SerializedName("backdrop_path") val backdropPath: String? = null,
    @SerializedName("original_language") val originalLanguage: String? = null,
    @SerializedName("vote_average") val voteAverage: Float = 0f,
    @SerializedName("number_of_seasons") val numberOfSeasons: Int = 1,
    @SerializedName("number_of_episodes") val numberOfEpisodes: Int = 0,
    @SerializedName("episode_run_time") val episodeRunTime: List<Int> = emptyList(),
    val status: String? = null,
    val genres: List<TmdbGenre> = emptyList(),
    val seasons: List<TmdbTvSeason> = emptyList()
)

data class TmdbSeasonDetails(
    val id: Int = 0,
    @SerializedName("season_number") val seasonNumber: Int = 1,
    val name: String? = null,
    val overview: String? = null,
    @SerializedName("poster_path") val posterPath: String? = null,
    val episodes: List<TmdbEpisode> = emptyList()
)

data class TmdbEpisode(
    val id: Int = 0,
    @SerializedName("episode_number") val episodeNumber: Int = 1,
    @SerializedName("season_number") val seasonNumber: Int = 1,
    val name: String = "",
    val overview: String? = null,
    @SerializedName("still_path") val stillPath: String? = null,
    @SerializedName("vote_average") val voteAverage: Float = 0f,
    val runtime: Int? = null,
    @SerializedName("air_date") val airDate: String? = null
)

data class TmdbGenre(val id: Int = 0, val name: String = "")
data class TmdbCreditsResponse(val id: Int = 0, val cast: List<TmdbCastMember> = emptyList())
data class TmdbCastMember(val id: Int = 0, val name: String = "", val character: String? = null, @SerializedName("profile_path") val profilePath: String? = null, val order: Int = 0)
data class TmdbImagesResponse(val id: Int = 0, val logos: List<TmdbImage> = emptyList(), val backdrops: List<TmdbImage> = emptyList())
data class TmdbImage(@SerializedName("file_path") val filePath: String? = null, @SerializedName("iso_639_1") val iso6391: String? = null, val width: Int = 0, val height: Int = 0)
data class TmdbVideosResponse(val id: Int = 0, val results: List<TmdbVideo> = emptyList())
data class TmdbVideo(val id: String = "", val key: String = "", val name: String = "", val site: String = "", val type: String = "", val official: Boolean = false)
data class TmdbExternalIds(@SerializedName("imdb_id") val imdbId: String? = null, @SerializedName("tvdb_id") val tvdbId: Int? = null)
data class TmdbWatchProvidersResponse(val id: Int = 0, val results: Map<String, TmdbWatchProviderRegion> = emptyMap())
data class TmdbWatchProviderRegion(val link: String? = null, val flatrate: List<TmdbWatchProvider> = emptyList(), val free: List<TmdbWatchProvider> = emptyList(), val ads: List<TmdbWatchProvider> = emptyList(), val rent: List<TmdbWatchProvider> = emptyList(), val buy: List<TmdbWatchProvider> = emptyList())
data class TmdbWatchProvider(@SerializedName("provider_id") val providerId: Int = 0, @SerializedName("provider_name") val providerName: String = "", @SerializedName("logo_path") val logoPath: String? = null, @SerializedName("display_priority") val displayPriority: Int = 0)
data class TmdbPersonDetails(val id: Int = 0, val name: String = "", val biography: String? = null, @SerializedName("place_of_birth") val placeOfBirth: String? = null, val birthday: String? = null, @SerializedName("profile_path") val profilePath: String? = null, @SerializedName("combined_credits") val combinedCredits: TmdbCombinedCredits? = null)
data class TmdbCombinedCredits(val cast: List<TmdbMediaItem> = emptyList())
data class TmdbReviewsResponse(val id: Int = 0, val page: Int = 1, val results: List<TmdbReview> = emptyList(), @SerializedName("total_pages") val totalPages: Int = 1, @SerializedName("total_results") val totalResults: Int = 0)
data class TmdbReview(val id: String = "", val author: String = "", @SerializedName("author_details") val authorDetails: TmdbAuthorDetails? = null, val content: String = "", @SerializedName("created_at") val createdAt: String = "", @SerializedName("updated_at") val updatedAt: String = "", val url: String = "")
data class TmdbAuthorDetails(val name: String = "", val username: String = "", @SerializedName("avatar_path") val avatarPath: String? = null, val rating: Float? = null)
data class TmdbFindResponse(@SerializedName("movie_results") val movieResults: List<TmdbFindItem> = emptyList(), @SerializedName("tv_results") val tvResults: List<TmdbFindItem> = emptyList())
data class TmdbFindItem(val id: Int = 0, val popularity: Float = 0f)

data class TmdbTvSeason(
    val id: Int = 0,
    @SerializedName("season_number") val seasonNumber: Int = 1,
    @SerializedName("episode_count") val episodeCount: Int = 0,
    val name: String? = null,
    val overview: String? = null,
    @SerializedName("poster_path") val posterPath: String? = null,
    @SerializedName("air_date") val airDate: String? = null
)
