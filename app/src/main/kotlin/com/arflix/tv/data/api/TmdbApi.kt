package com.arflix.tv.data.api

import com.google.gson.annotations.SerializedName
import com.google.gson.JsonObject
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

/**
 * TMDB API interface
 */
interface TmdbApi {

    @GET("tv/{tv_id}")
    suspend fun getTvSeasons(
        @Path("tv_id") tvId: Int,
        @Query("api_key") apiKey: String,
        @Query("append_to_response") appendToResponse: String,
        @Query("language") language: String? = null
    ): JsonObject

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
        @Query("with_people") people: String? = null,
        @Query("with_original_language") originalLanguage: String? = null,
        @Query("first_air_date_year") year: Int? = null,
        @Query("vote_count.gte") minVoteCount: Int? = null,
        @Query("vote_average.gte") minVoteAverage: Double? = null,
        @Query("vote_average.lte") maxVoteAverage: Double? = null,
        @Query("with_keywords") keywords: String? = null,
        // `air_date` is the broadcast date of an individual episode, so a show with a recent
        // episode passes it no matter when it started. That is what the home screen's anime
        // rows want and what a "shows that started in the nineties" filter must not use — for
        // that, `first_air_date` is the date the card actually prints. Both stay available.
        @Query("air_date.gte") airDateGte: String? = null,
        @Query("air_date.lte") airDateLte: String? = null,
        @Query("first_air_date.gte") firstAirDateGte: String? = null,
        @Query("first_air_date.lte") firstAirDateLte: String? = null,
        @Query("language") language: String? = null,
        @Query("page") page: Int = 1
    ): TmdbListResponse

    @GET("discover/movie")
    suspend fun discoverMovies(
        @Query("api_key") apiKey: String,
        @Query("with_genres") genres: String? = null,
        @Query("with_crew") crew: String? = null,
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("vote_count.gte") minVoteCount: Int? = null,
        @Query("vote_average.gte") minVoteAverage: Double? = null,
        @Query("vote_average.lte") maxVoteAverage: Double? = null,
        // TMDB offers certification filtering for movies only; discover/tv has no equivalent.
        @Query("certification_country") certificationCountry: String? = null,
        @Query("certification.lte") certificationLte: String? = null,
        @Query("with_keywords") keywords: String? = null,
        @Query("with_original_language") originalLanguage: String? = null,
        @Query("primary_release_year") year: Int? = null,
        // `release_date` matches ANY release of a film — every country, a re-run, a digital
        // start — while the card shows the first one. `primary_release_date` is that first
        // one, the same date `primary_release_year` above filters by. Both stay available.
        @Query("release_date.gte") releaseDateGte: String? = null,
        @Query("release_date.lte") releaseDateLte: String? = null,
        @Query("primary_release_date.gte") primaryReleaseDateGte: String? = null,
        @Query("primary_release_date.lte") primaryReleaseDateLte: String? = null,
        @Query("with_watch_providers") watchProviders: Int? = null,
        @Query("watch_region") watchRegion: String? = null,
        @Query("language") language: String? = null,
        @Query("page") page: Int = 1
    ): TmdbListResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("append_to_response") appendToResponse: String = "release_dates",
        @Query("language") language: String? = null
    ): TmdbMovieDetails

    @GET("tv/{tv_id}")
    suspend fun getTvDetails(
        @Path("tv_id") tvId: Int,
        @Query("api_key") apiKey: String,
        @Query("append_to_response") appendToResponse: String = "content_ratings",
        @Query("language") language: String? = null
    ): TmdbTvDetails

    @GET("tv/{tv_id}/season/{season_number}")
    suspend fun getTvSeason(
        @Path("tv_id") tvId: Int,
        @Path("season_number") seasonNumber: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String? = null
    ): TmdbSeasonDetails

    @GET("tv/{tv_id}/season/{season_number}/episode/{episode_number}/external_ids")
    suspend fun getTvEpisodeExternalIds(
        @Path("tv_id") tvId: Int,
        @Path("season_number") seasonNumber: Int,
        @Path("episode_number") episodeNumber: Int,
        @Query("api_key") apiKey: String
    ): TmdbExternalIds

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

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
        @Query("language") language: String? = null,
        @Query("page") page: Int = 1,
        @Query("primary_release_year") primaryReleaseYear: Int? = null,
        @Query("year") year: Int? = null
    ): TmdbListResponse

    @GET("search/tv")
    suspend fun searchTv(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
        @Query("language") language: String? = null,
        @Query("page") page: Int = 1,
        @Query("first_air_date_year") firstAirDateYear: Int? = null
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

    /**
     * TMDB "collection" endpoint. Returns the explicit list of films that belong
     * to a franchise (e.g. Harry Potter = 1241, LOTR = 119, James Bond = 645).
     * Used by the Collections feature to populate franchise rows without
     * relying on external addons.
     */
    @GET("collection/{collection_id}")
    suspend fun getTmdbCollection(
        @Path("collection_id") collectionId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String? = null
    ): TmdbCollectionResponse

    /**
     * Free-form discover query used by imported collections, whose filters are
     * raw TMDB query keys (with_companies, with_networks, *_date.gte, ...).
     */
    @GET("discover/{media_type}")
    suspend fun discoverWithParams(
        @Path("media_type") mediaType: String,
        @Query("api_key") apiKey: String,
        @QueryMap params: Map<String, String>,
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("language") language: String? = null,
        @Query("page") page: Int = 1
    ): TmdbListResponse

    /** Public TMDB v3 list; items keep the list's own order and carry a media_type. */
    @GET("list/{list_id}")
    suspend fun getPublicList(
        @Path("list_id") listId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String? = null,
        @Query("page") page: Int = 1
    ): TmdbPublicListResponse
}

// Response data classes

data class TmdbPublicListResponse(
    val page: Int = 1,
    val items: List<TmdbPublicListItem> = emptyList(),
    @SerializedName("total_pages") val totalPages: Int = 1
)

data class TmdbPublicListItem(
    val id: Int = 0,
    @SerializedName("media_type") val mediaType: String? = null,
    val title: String? = null,
    val name: String? = null,
    @SerializedName("original_title") val originalTitle: String? = null,
    @SerializedName("original_name") val originalName: String? = null,
    val overview: String? = null,
    @SerializedName("release_date") val releaseDate: String? = null,
    @SerializedName("first_air_date") val firstAirDate: String? = null,
    @SerializedName("poster_path") val posterPath: String? = null,
    @SerializedName("backdrop_path") val backdropPath: String? = null,
    @SerializedName("vote_average") val voteAverage: Float = 0f,
    @SerializedName("genre_ids") val genreIds: List<Int> = emptyList(),
    @SerializedName("original_language") val originalLanguage: String? = null
) {
    fun asMediaItem() = TmdbMediaItem(
        id = id, mediaType = mediaType, title = title, name = name,
        originalTitle = originalTitle, originalName = originalName, overview = overview,
        releaseDate = releaseDate, firstAirDate = firstAirDate,
        posterPath = posterPath, backdropPath = backdropPath, voteAverage = voteAverage,
        genreIds = genreIds, originalLanguage = originalLanguage
    )
}

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
    @SerializedName("original_title") val originalTitle: String? = null,
    @SerializedName("original_name") val originalName: String? = null,
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
    val character: String? = null,
    val job: String? = null,
    @SerializedName("known_for") val knownFor: List<TmdbMediaItem> = emptyList()
)

data class TmdbMovieDetails(
    val id: Int = 0,
    val title: String = "",
    @SerializedName("original_title") val originalTitle: String? = null,
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
    val adult: Boolean = false,
    @SerializedName("belongs_to_collection") val belongsToCollection: TmdbCollectionRef? = null,
    // Appended via append_to_response. Nullable so the response stays valid
    // when TMDB omits the block.
    @SerializedName("release_dates") val releaseDates: TmdbReleaseDatesResponse? = null
)

/** Reference to a TMDB collection (franchise) returned inside movie/TV details. */
data class TmdbCollectionRef(
    val id: Int = 0,
    val name: String? = null,
    @SerializedName("poster_path") val posterPath: String? = null,
    @SerializedName("backdrop_path") val backdropPath: String? = null
)

data class TmdbTvDetails(
    val id: Int = 0,
    val name: String = "",
    @SerializedName("original_name") val originalName: String? = null,
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
    val seasons: List<TmdbTvSeason> = emptyList(),
    // Appended via append_to_response. Nullable so the response stays valid
    // when TMDB omits the block.
    @SerializedName("content_ratings") val contentRatings: TmdbContentRatingsResponse? = null,
    @SerializedName("last_episode_to_air") val lastEpisodeToAir: TmdbEpisode? = null,
    @SerializedName("next_episode_to_air") val nextEpisodeToAir: TmdbEpisode? = null
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

/**
 * Age certifications for a movie, appended to /movie/{id} as `release_dates`.
 * A country can list several certifications (theatrical, TV, streaming), so the
 * per-country entry carries a list.
 */
data class TmdbReleaseDatesResponse(val results: List<TmdbReleaseDatesResult> = emptyList())
data class TmdbReleaseDatesResult(
    @SerializedName("iso_3166_1") val iso31661: String = "",
    @SerializedName("release_dates") val releaseDates: List<TmdbReleaseDate> = emptyList()
)
/** `type` 3 is the theatrical release. Unknown types sort last, see ContentRating. */
data class TmdbReleaseDate(
    val certification: String? = null,
    val type: Int = Int.MAX_VALUE,
    // TMDB encodes a calendar date as a timestamp. It is not a confirmed release time.
    @SerializedName("release_date") val releaseDate: String? = null
)

/**
 * Age certifications for a TV show, appended to /tv/{id} as `content_ratings`.
 * Unlike movies there is exactly one value per country.
 */
data class TmdbContentRatingsResponse(val results: List<TmdbContentRatingResult> = emptyList())
data class TmdbContentRatingResult(
    @SerializedName("iso_3166_1") val iso31661: String = "",
    val rating: String? = null
)

data class TmdbGenre(val id: Int = 0, val name: String = "")
data class TmdbCreditsResponse(val id: Int = 0, val cast: List<TmdbCastMember> = emptyList(), val crew: List<TmdbCrewMember> = emptyList())
data class TmdbCastMember(val id: Int = 0, val name: String = "", val character: String? = null, @SerializedName("profile_path") val profilePath: String? = null, val order: Int = 0)
data class TmdbCrewMember(val id: Int = 0, val name: String = "", val job: String = "", @SerializedName("profile_path") val profilePath: String? = null, val department: String = "")
data class TmdbImagesResponse(val id: Int = 0, val logos: List<TmdbImage> = emptyList(), val backdrops: List<TmdbImage> = emptyList())
data class TmdbImage(@SerializedName("file_path") val filePath: String? = null, @SerializedName("iso_639_1") val iso6391: String? = null, val width: Int = 0, val height: Int = 0, @SerializedName("vote_average") val voteAverage: Float = 0f, @SerializedName("vote_count") val voteCount: Int = 0)
data class TmdbVideosResponse(val id: Int = 0, val results: List<TmdbVideo> = emptyList())
data class TmdbVideo(val id: String = "", val key: String = "", val name: String = "", val site: String = "", val type: String = "", val official: Boolean = false)
data class TmdbExternalIds(@SerializedName("imdb_id") val imdbId: String? = null, @SerializedName("tvdb_id") val tvdbId: Int? = null)
data class TmdbWatchProvidersResponse(val id: Int = 0, val results: Map<String, TmdbWatchProviderRegion> = emptyMap())
data class TmdbWatchProviderRegion(val link: String? = null, val flatrate: List<TmdbWatchProvider> = emptyList(), val free: List<TmdbWatchProvider> = emptyList(), val ads: List<TmdbWatchProvider> = emptyList(), val rent: List<TmdbWatchProvider> = emptyList(), val buy: List<TmdbWatchProvider> = emptyList())
data class TmdbWatchProvider(@SerializedName("provider_id") val providerId: Int = 0, @SerializedName("provider_name") val providerName: String = "", @SerializedName("logo_path") val logoPath: String? = null, @SerializedName("display_priority") val displayPriority: Int = 0)
data class TmdbPersonDetails(val id: Int = 0, val name: String = "", val biography: String? = null, @SerializedName("place_of_birth") val placeOfBirth: String? = null, val birthday: String? = null, @SerializedName("profile_path") val profilePath: String? = null, @SerializedName("combined_credits") val combinedCredits: TmdbCombinedCredits? = null)
data class TmdbCombinedCredits(val cast: List<TmdbMediaItem> = emptyList(), val crew: List<TmdbMediaItem> = emptyList())
data class TmdbReviewsResponse(val id: Int = 0, val page: Int = 1, val results: List<TmdbReview> = emptyList(), @SerializedName("total_pages") val totalPages: Int = 1, @SerializedName("total_results") val totalResults: Int = 0)
data class TmdbReview(val id: String = "", val author: String = "", @SerializedName("author_details") val authorDetails: TmdbAuthorDetails? = null, val content: String = "", @SerializedName("created_at") val createdAt: String = "", @SerializedName("updated_at") val updatedAt: String = "", val url: String = "")
data class TmdbAuthorDetails(val name: String = "", val username: String = "", @SerializedName("avatar_path") val avatarPath: String? = null, val rating: Float? = null)
data class TmdbFindResponse(
    @SerializedName("movie_results") val movieResults: List<TmdbFindItem> = emptyList(),
    @SerializedName("tv_results") val tvResults: List<TmdbFindItem> = emptyList(),
    @SerializedName("tv_episode_results") val tvEpisodeResults: List<TmdbFindEpisode> = emptyList()
)
data class TmdbFindItem(val id: Int = 0, val popularity: Float = 0f, val title: String = "", val name: String = "")
data class TmdbFindEpisode(
    @SerializedName("show_id") val showId: Int = 0,
    @SerializedName("season_number") val seasonNumber: Int = -1,
    @SerializedName("episode_number") val episodeNumber: Int = -1
)

/** Response for /collection/{id} — the `parts` array contains the films in a franchise. */
data class TmdbCollectionResponse(
    val id: Int = 0,
    val name: String = "",
    val overview: String? = null,
    @SerializedName("poster_path") val posterPath: String? = null,
    @SerializedName("backdrop_path") val backdropPath: String? = null,
    val parts: List<TmdbMediaItem> = emptyList()
)

data class TmdbTvSeason(
    val id: Int = 0,
    @SerializedName("season_number") val seasonNumber: Int = 1,
    @SerializedName("episode_count") val episodeCount: Int = 0,
    val name: String? = null,
    val overview: String? = null,
    @SerializedName("poster_path") val posterPath: String? = null,
    @SerializedName("air_date") val airDate: String? = null
)
