package com.arflix.tv.data.repository

import com.arflix.tv.data.api.TmdbApi
import com.arflix.tv.data.api.TmdbCastMember
import com.arflix.tv.data.api.TmdbEpisode
import com.arflix.tv.data.api.TmdbMediaItem
import com.arflix.tv.data.api.TmdbMovieDetails
import com.arflix.tv.data.api.TmdbPersonDetails
import com.arflix.tv.data.api.TmdbSeasonDetails
import com.arflix.tv.data.api.TmdbTvDetails
import com.arflix.tv.data.model.CastMember
import com.arflix.tv.data.model.Category
import com.arflix.tv.data.model.Episode
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.model.PersonDetails
import com.arflix.tv.data.model.Review
import com.arflix.tv.util.Constants
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.concurrent.ConcurrentHashMap
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository for media data from TMDB
 * Cross-references with Trakt for watched status
 * Includes in-memory caching for performance
 */
@Singleton
class MediaRepository @Inject constructor(
    private val tmdbApi: TmdbApi,
    private val traktRepository: TraktRepository
) {
    private val apiKey = Constants.TMDB_API_KEY

    // === IN-MEMORY CACHE FOR PERFORMANCE ===
    private data class CacheEntry<T>(val data: T, val timestamp: Long)
    private val CACHE_TTL_MS = 5 * 60 * 1000L // 5 minutes

    private val detailsCache = mutableMapOf<String, CacheEntry<MediaItem>>()
    private val castCache = mutableMapOf<String, CacheEntry<List<CastMember>>>()
    private val similarCache = mutableMapOf<String, CacheEntry<List<MediaItem>>>()
    private val logoCache = mutableMapOf<String, CacheEntry<String?>>()
    private val reviewsCache = mutableMapOf<String, CacheEntry<List<Review>>>()
    private val imdbIdCache = ConcurrentHashMap<String, String>()

    private fun <T> getFromCache(cache: Map<String, CacheEntry<T>>, key: String): T? {
        val entry = cache[key] ?: return null
        return if (System.currentTimeMillis() - entry.timestamp < CACHE_TTL_MS) entry.data else null
    }

    fun getCachedItem(mediaType: MediaType, mediaId: Int): MediaItem? {
        val cacheKey = if (mediaType == MediaType.MOVIE) "movie_$mediaId" else "tv_$mediaId"
        return getFromCache(detailsCache, cacheKey)
    }

    fun cacheImdbId(mediaType: MediaType, mediaId: Int, imdbId: String) {
        if (imdbId.isBlank()) return
        val cacheKey = if (mediaType == MediaType.MOVIE) "movie_$mediaId" else "tv_$mediaId"
        imdbIdCache[cacheKey] = imdbId
    }

    fun getCachedImdbId(mediaType: MediaType, mediaId: Int): String? {
        val cacheKey = if (mediaType == MediaType.MOVIE) "movie_$mediaId" else "tv_$mediaId"
        return imdbIdCache[cacheKey]
    }

    fun cacheItem(item: MediaItem) {
        val cacheKey = if (item.mediaType == MediaType.MOVIE) "movie_${item.id}" else "tv_${item.id}"
        detailsCache[cacheKey] = CacheEntry(item, System.currentTimeMillis())
    }

    private fun cacheItems(items: List<MediaItem>) {
        items.forEach { cacheItem(it) }
    }
    
    /**
     * Fetch home screen categories
     */
    suspend fun getHomeCategories(): List<Category> = coroutineScope {
        val year = Calendar.getInstance().get(Calendar.YEAR)

        val trendingMovies = async { tmdbApi.getTrendingMovies(apiKey) }
        val trendingTv = async { tmdbApi.getTrendingTv(apiKey) }
        val trendingAnime = async { tmdbApi.discoverTv(apiKey, genres = "16", language = "ja") }
        val netflix = async { tmdbApi.discoverTv(apiKey, watchProviders = 8) }
        val disney = async { tmdbApi.discoverTv(apiKey, watchProviders = 337) }
        val prime = async { tmdbApi.discoverTv(apiKey, watchProviders = 9) }
        val apple = async { tmdbApi.discoverTv(apiKey, watchProviders = 350) }
        val paramount = async { tmdbApi.discoverTv(apiKey, watchProviders = 531) }
        val yearSeries = async { tmdbApi.discoverTv(apiKey, year = year) }
        val korean = async { tmdbApi.discoverTv(apiKey, language = "ko") }
        val actionMovies = async { tmdbApi.discoverMovies(apiKey, genres = "28") }
        val dramaShows = async { tmdbApi.discoverTv(apiKey, genres = "18") }
        val thrillerMovies = async { tmdbApi.discoverMovies(apiKey, genres = "53") }
        val horrorMovies = async { tmdbApi.discoverMovies(apiKey, genres = "27") }
        val comedyMovies = async { tmdbApi.discoverMovies(apiKey, genres = "35") }
        val scifiMovies = async { tmdbApi.discoverMovies(apiKey, genres = "878") }
        val documentaries = async { tmdbApi.discoverTv(apiKey, genres = "99") }

        val categories = listOf(
            Category(
                id = "trending_movies",
                title = "Trending Movies",
                items = trendingMovies.await().results.map { it.toMediaItem(MediaType.MOVIE) }
            ),
            Category(
                id = "trending_tv",
                title = "Trending Series",
                items = trendingTv.await().results.map { it.toMediaItem(MediaType.TV) }
            ),
            Category(
                id = "trending_anime",
                title = "Trending Anime",
                items = trendingAnime.await().results.map { it.toMediaItem(MediaType.TV) }
            ),
            Category(
                id = "trending_netflix",
                title = "Trending on Netflix",
                items = netflix.await().results.map { it.toMediaItem(MediaType.TV) }
            ),
            Category(
                id = "trending_disney",
                title = "Trending on Disney+",
                items = disney.await().results.map { it.toMediaItem(MediaType.TV) }
            ),
            Category(
                id = "trending_prime",
                title = "Trending on Amazon Prime",
                items = prime.await().results.map { it.toMediaItem(MediaType.TV) }
            ),
            Category(
                id = "trending_paramount",
                title = "Trending on Paramount+",
                items = paramount.await().results.map { it.toMediaItem(MediaType.TV) }
            ),
            Category(
                id = "trending_apple",
                title = "Trending on Apple TV+",
                items = apple.await().results.map { it.toMediaItem(MediaType.TV) }
            ),
            Category(
                id = "most_watched_year",
                title = "Most Watched Series $year",
                items = yearSeries.await().results.map { it.toMediaItem(MediaType.TV) }
            ),
            Category(
                id = "trending_korean",
                title = "Trending Korean Series",
                items = korean.await().results.map { it.toMediaItem(MediaType.TV) }
            ),
            Category(
                id = "genre_action",
                title = "Action Hits",
                items = actionMovies.await().results.map { it.toMediaItem(MediaType.MOVIE) }
            ),
            Category(
                id = "genre_thriller",
                title = "Psychological Thrillers",
                items = thrillerMovies.await().results.map { it.toMediaItem(MediaType.MOVIE) }
            ),
            Category(
                id = "genre_horror",
                title = "Horror",
                items = horrorMovies.await().results.map { it.toMediaItem(MediaType.MOVIE) }
            ),
            Category(
                id = "genre_comedy",
                title = "Comedy",
                items = comedyMovies.await().results.map { it.toMediaItem(MediaType.MOVIE) }
            ),
            Category(
                id = "genre_scifi",
                title = "Sci-Fi Adventures",
                items = scifiMovies.await().results.map { it.toMediaItem(MediaType.MOVIE) }
            ),
            Category(
                id = "genre_drama",
                title = "Drama Series",
                items = dramaShows.await().results.map { it.toMediaItem(MediaType.TV) }
            ),
            Category(
                id = "genre_documentary",
                title = "Documentaries",
                items = documentaries.await().results.map { it.toMediaItem(MediaType.TV) }
            )
        )
        categories.forEach { cacheItems(it.items) }
        categories
    }
    
    /**
     * Get movie details (cached)
     */
    suspend fun getMovieDetails(movieId: Int): MediaItem {
        val cacheKey = "movie_$movieId"
        getFromCache(detailsCache, cacheKey)?.let { return it }

        val details = tmdbApi.getMovieDetails(movieId, apiKey)
        val item = details.toMediaItem()
        detailsCache[cacheKey] = CacheEntry(item, System.currentTimeMillis())
        return item
    }

    /**
     * Get TV show details (cached)
     */
    suspend fun getTvDetails(tvId: Int): MediaItem {
        val cacheKey = "tv_$tvId"
        getFromCache(detailsCache, cacheKey)?.let { return it }

        val details = tmdbApi.getTvDetails(tvId, apiKey)
        val item = details.toMediaItem()
        detailsCache[cacheKey] = CacheEntry(item, System.currentTimeMillis())
        return item
    }
    
    /**
     * Get season episodes with Trakt watched status
     */
    suspend fun getSeasonEpisodes(tvId: Int, seasonNumber: Int): List<Episode> {
        val season = tmdbApi.getTvSeason(tvId, seasonNumber, apiKey)

        // Get watched episodes from Trakt for this show
        val watchedEpisodes = try {
            traktRepository.getWatchedEpisodesForShow(tvId)
        } catch (e: Exception) {
            emptySet<String>()
        }

        return season.episodes.map { episode ->
            val episodeKey = "show_tmdb:$tvId:$seasonNumber:${episode.episodeNumber}"
            episode.toEpisode().copy(
                isWatched = episodeKey in watchedEpisodes
            )
        }
    }
    
    /**
     * Get cast members (cached)
     */
    suspend fun getCast(mediaType: MediaType, mediaId: Int): List<CastMember> {
        val cacheKey = "${mediaType}_cast_$mediaId"
        getFromCache(castCache, cacheKey)?.let { return it }

        val type = if (mediaType == MediaType.TV) "tv" else "movie"
        val credits = tmdbApi.getCredits(type, mediaId, apiKey)
        val cast = credits.cast.take(15).map { it.toCastMember() }
        castCache[cacheKey] = CacheEntry(cast, System.currentTimeMillis())
        return cast
    }

    /**
     * Get recommended content (cached)
     * Falls back to similar if recommendations are empty
     */
    suspend fun getSimilar(mediaType: MediaType, mediaId: Int): List<MediaItem> {
        val cacheKey = "${mediaType}_similar_$mediaId"
        getFromCache(similarCache, cacheKey)?.let { return it }

        val type = if (mediaType == MediaType.TV) "tv" else "movie"
        val recommendations = try {
            tmdbApi.getRecommendations(type, mediaId, apiKey)
        } catch (e: Exception) {
            null
        }

        val result = if (recommendations != null && recommendations.results.isNotEmpty()) {
            recommendations.results.take(12).map { it.toMediaItem(mediaType) }
        } else {
            val similar = tmdbApi.getSimilar(type, mediaId, apiKey)
            similar.results.take(12).map { it.toMediaItem(mediaType) }
        }
        similarCache[cacheKey] = CacheEntry(result, System.currentTimeMillis())
        cacheItems(result)
        return result
    }

    /**
     * Get logo URL for a media item (cached)
     */
    suspend fun getLogoUrl(mediaType: MediaType, mediaId: Int): String? {
        val cacheKey = "${mediaType}_logo_$mediaId"
        if (logoCache.containsKey(cacheKey)) {
            getFromCache(logoCache, cacheKey)?.let { return it }
        }

        val type = if (mediaType == MediaType.TV) "tv" else "movie"
        return try {
            val images = tmdbApi.getImages(type, mediaId, apiKey)
            val logo = images.logos.find { it.iso6391 == "en" } ?: images.logos.firstOrNull()
            val url = logo?.filePath?.let { "${Constants.LOGO_BASE}$it" }
            logoCache[cacheKey] = CacheEntry(url, System.currentTimeMillis())
            url
        } catch (e: Exception) {
            null
        }
    }
    
    /**
     * Get trailer key (YouTube)
     */
    suspend fun getTrailerKey(mediaType: MediaType, mediaId: Int): String? {
        val type = if (mediaType == MediaType.TV) "tv" else "movie"
        return try {
            val videos = tmdbApi.getVideos(type, mediaId, apiKey)
            val trailer = videos.results.find { it.type == "Trailer" && it.site == "YouTube" && it.official }
                ?: videos.results.find { it.type == "Trailer" && it.site == "YouTube" }
                ?: videos.results.find { it.type == "Teaser" && it.site == "YouTube" }
                ?: videos.results.find { it.site == "YouTube" }
            trailer?.key
        } catch (e: Exception) {
            null
        }
    }
    
    /**
     * Get person details
     */
    suspend fun getPersonDetails(personId: Int): PersonDetails {
        val person = tmdbApi.getPersonDetails(personId, apiKey)
        return person.toPersonDetails()
    }
    
    /**
     * Search media
     */
    suspend fun search(query: String): List<MediaItem> {
        val results = tmdbApi.searchMulti(apiKey, query)
        val items = results.results
            .filter { it.mediaType == "movie" || it.mediaType == "tv" }
            .map {
                it.toMediaItem(
                    if (it.mediaType == "tv") MediaType.TV else MediaType.MOVIE
                )
            }
        cacheItems(items)
        return items
    }

    /**
     * Get reviews for a movie or TV show from TMDB (cached)
     */
    suspend fun getReviews(mediaType: MediaType, mediaId: Int): List<Review> {
        val cacheKey = "${mediaType}_reviews_$mediaId"
        getFromCache(reviewsCache, cacheKey)?.let { return it }

        val type = if (mediaType == MediaType.TV) "tv" else "movie"
        return try {
            val response = tmdbApi.getReviews(type, mediaId, apiKey)
            val reviews = response.results.take(10).map { review ->
                Review(
                    id = review.id,
                    author = review.author,
                    authorUsername = review.authorDetails?.username ?: "",
                    authorAvatar = review.authorDetails?.avatarPath?.let { path ->
                        if (path.startsWith("/https://")) {
                            path.substring(1) // Remove leading slash for gravatar URLs
                        } else {
                            "${Constants.IMAGE_BASE}$path"
                        }
                    },
                    content = review.content,
                    rating = review.authorDetails?.rating,
                    createdAt = review.createdAt
                )
            }
            reviewsCache[cacheKey] = CacheEntry(reviews, System.currentTimeMillis())
            reviews
        } catch (e: Exception) {
            emptyList()
        }
    }
}

// Extension functions to convert API responses to domain models

private fun TmdbMediaItem.toMediaItem(defaultType: MediaType): MediaItem {
    val type = when (mediaType) {
        "tv" -> MediaType.TV
        "movie" -> MediaType.MOVIE
        else -> defaultType
    }
    
    val dateStr = releaseDate ?: firstAirDate ?: ""
    val year = dateStr.take(4)
    
    return MediaItem(
        id = id,
        title = title ?: name ?: "Unknown",
        subtitle = if (type == MediaType.MOVIE) "Movie" else "TV Series",
        overview = overview ?: "",
        year = year,
        releaseDate = formatDate(dateStr),
        imdbRating = String.format("%.1f", voteAverage),
        tmdbRating = String.format("%.1f", voteAverage),
        mediaType = type,
        image = backdropPath?.let { "${Constants.BACKDROP_BASE}$it" }
            ?: posterPath?.let { "${Constants.IMAGE_BASE}$it" }
            ?: "",
        backdrop = backdropPath?.let { "${Constants.BACKDROP_BASE_LARGE}$it" },
        genreIds = genreIds,
        originalLanguage = originalLanguage,
        character = character ?: "",
        popularity = popularity
    )
}

private fun TmdbMovieDetails.toMediaItem(): MediaItem {
    val year = releaseDate?.take(4) ?: ""
    val hours = (runtime ?: 0) / 60
    val minutes = (runtime ?: 0) % 60
    val duration = if (hours > 0) "${hours}h ${minutes}m" else "${minutes}m"
    
    return MediaItem(
        id = id,
        title = title,
        subtitle = "Movie",
        overview = overview ?: "",
        year = year,
        releaseDate = formatDate(releaseDate ?: ""),
        duration = duration,
        rating = if (adult) "R" else "PG-13",
        imdbRating = String.format("%.1f", voteAverage),
        tmdbRating = String.format("%.1f", voteAverage),
        mediaType = MediaType.MOVIE,
        image = backdropPath?.let { "${Constants.BACKDROP_BASE}$it" }
            ?: posterPath?.let { "${Constants.IMAGE_BASE}$it" }
            ?: "",
        backdrop = backdropPath?.let { "${Constants.BACKDROP_BASE_LARGE}$it" },
        originalLanguage = originalLanguage,
        budget = budget
    )
}

private fun TmdbTvDetails.toMediaItem(): MediaItem {
    val year = firstAirDate?.take(4) ?: ""
    val runtime = episodeRunTime.firstOrNull() ?: 45
    val duration = "${runtime}m"
    
    return MediaItem(
        id = id,
        title = name,
        subtitle = "TV Series",
        overview = overview ?: "",
        year = year,
        releaseDate = formatDate(firstAirDate ?: ""),
        duration = duration,
        imdbRating = String.format("%.1f", voteAverage),
        tmdbRating = String.format("%.1f", voteAverage),
        mediaType = MediaType.TV,
        image = backdropPath?.let { "${Constants.BACKDROP_BASE}$it" }
            ?: posterPath?.let { "${Constants.IMAGE_BASE}$it" }
            ?: "",
        backdrop = backdropPath?.let { "${Constants.BACKDROP_BASE_LARGE}$it" },
        originalLanguage = originalLanguage,
        isOngoing = status == "Returning Series",
        totalEpisodes = numberOfSeasons,
        status = status
    )
}

private fun TmdbEpisode.toEpisode(): Episode {
    return Episode(
        id = id,
        episodeNumber = episodeNumber,
        seasonNumber = seasonNumber,
        name = name,
        overview = overview ?: "",
        stillPath = stillPath?.let { "${Constants.IMAGE_BASE}$it" },
        voteAverage = voteAverage,
        runtime = runtime ?: 0,
        airDate = airDate ?: ""
    )
}

private fun TmdbCastMember.toCastMember(): CastMember {
    return CastMember(
        id = id,
        name = name,
        character = character ?: "",
        profilePath = profilePath?.let { "${Constants.IMAGE_BASE}$it" }
    )
}

private fun TmdbPersonDetails.toPersonDetails(): PersonDetails {
    val knownFor = combinedCredits?.cast
        ?.filter { it.posterPath != null && (it.mediaType == "movie" || it.mediaType == "tv") }
        ?.sortedByDescending { it.voteCount }
        ?.take(20)
        ?.map { 
            it.toMediaItem(
                if (it.mediaType == "tv") MediaType.TV else MediaType.MOVIE
            )
        } ?: emptyList()
    
    return PersonDetails(
        id = id,
        name = name,
        biography = biography ?: "",
        placeOfBirth = placeOfBirth,
        birthday = birthday,
        profilePath = profilePath?.let { "${Constants.IMAGE_BASE}$it" },
        knownFor = knownFor
    )
}

private fun formatDate(dateStr: String): String {
    if (dateStr.isEmpty()) return ""
    return try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val outputFormat = SimpleDateFormat("d MMM yyyy", Locale.US)  // "12 Jan 2025" format
        val date = inputFormat.parse(dateStr)
        date?.let { outputFormat.format(it) } ?: dateStr
    } catch (e: Exception) {
        dateStr
    }
}
