package com.arflix.tv.data.repository

import com.arflix.tv.data.api.TmdbApi
import com.arflix.tv.data.api.TmdbCastMember
import com.arflix.tv.data.api.TmdbEpisode
import com.arflix.tv.data.api.TmdbListResponse
import com.arflix.tv.data.api.TmdbMediaItem
import com.arflix.tv.data.api.TmdbMovieDetails
import com.arflix.tv.data.api.TmdbPersonDetails
import com.arflix.tv.data.api.TmdbSeasonDetails
import com.arflix.tv.data.api.TmdbTvDetails
import com.arflix.tv.data.api.TraktApi
import com.arflix.tv.data.api.TraktPublicListItem
import com.arflix.tv.data.model.CastMember
import com.arflix.tv.data.model.CatalogConfig
import com.arflix.tv.data.model.CatalogSourceType
import com.arflix.tv.data.model.Category
import com.arflix.tv.data.model.Episode
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.model.PersonDetails
import com.arflix.tv.data.model.Review
import com.arflix.tv.util.CatalogUrlParser
import com.arflix.tv.util.Constants
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withPermit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.Request
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.concurrent.ConcurrentHashMap
import com.arflix.tv.util.ParsedCatalogUrl
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
    private val traktRepository: TraktRepository,
    private val traktApi: TraktApi,
    private val okHttpClient: OkHttpClient
) {
    private val apiKey = Constants.TMDB_API_KEY
    private val gson = Gson()

    // === IN-MEMORY CACHE FOR PERFORMANCE ===
    private data class CacheEntry<T>(val data: T, val timestamp: Long)
    private val CACHE_TTL_MS = 5 * 60 * 1000L // 5 minutes

    private val detailsCache = mutableMapOf<String, CacheEntry<MediaItem>>()
    private val castCache = mutableMapOf<String, CacheEntry<List<CastMember>>>()
    private val similarCache = mutableMapOf<String, CacheEntry<List<MediaItem>>>()
    private val logoCache = mutableMapOf<String, CacheEntry<String?>>()
    private val reviewsCache = mutableMapOf<String, CacheEntry<List<Review>>>()
    private val seasonEpisodesCache = mutableMapOf<String, CacheEntry<List<Episode>>>()
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

    fun getDefaultCatalogConfigs(): List<CatalogConfig> {
        return listOf(
            CatalogConfig("trending_movies", "Trending Movies", CatalogSourceType.PREINSTALLED, isPreinstalled = true),
            CatalogConfig("trending_tv", "Trending Series", CatalogSourceType.PREINSTALLED, isPreinstalled = true),
            CatalogConfig("trending_anime", "Trending Anime", CatalogSourceType.PREINSTALLED, isPreinstalled = true),
            CatalogConfig("trending_netflix", "Trending on Netflix", CatalogSourceType.PREINSTALLED, isPreinstalled = true),
            CatalogConfig("trending_disney", "Trending on Disney+", CatalogSourceType.PREINSTALLED, isPreinstalled = true),
            CatalogConfig("trending_prime", "Trending on Prime Video", CatalogSourceType.PREINSTALLED, isPreinstalled = true),
            CatalogConfig("trending_hbo", "Trending on Max", CatalogSourceType.PREINSTALLED, isPreinstalled = true),
            CatalogConfig("trending_apple", "Trending on Apple TV+", CatalogSourceType.PREINSTALLED, isPreinstalled = true),
            CatalogConfig("trending_paramount", "Trending on Paramount+", CatalogSourceType.PREINSTALLED, isPreinstalled = true),
            CatalogConfig("trending_hulu", "Trending on Hulu", CatalogSourceType.PREINSTALLED, isPreinstalled = true),
            CatalogConfig("trending_peacock", "Trending on Peacock", CatalogSourceType.PREINSTALLED, isPreinstalled = true)
        )
    }
    
    /**
     * Fetch home screen categories
     * Uses improved filters for better quality results:
     * - Trending: Uses daily TMDB trending (updates every day)
     * - Anime: Uses "anime" keyword (210024) for accurate anime content
     * - Provider categories: wider recency window to keep full rows populated
     */
    suspend fun getHomeCategories(): List<Category> = coroutineScope {
        suspend fun fetchUpTo40(fetchPage: suspend (Int) -> TmdbListResponse): List<TmdbMediaItem> {
            val first = runCatching { fetchPage(1) }.getOrNull() ?: return emptyList()
            val firstItems = first.results
            if (firstItems.size >= 40 || first.totalPages < 2) return firstItems.take(40)
            val secondItems = runCatching { fetchPage(2) }.getOrNull()?.results.orEmpty()
            return (firstItems + secondItems).distinctBy { it.id }.take(40)
        }

        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val calendar = Calendar.getInstance()
        // Wider windows keep rows filled up to 40 items consistently.
        calendar.add(Calendar.MONTH, -12)
        val twelveMonthsAgo = dateFormat.format(calendar.time)
        // Anime needs a wider horizon for slower seasonal cycles.
        calendar.time = Calendar.getInstance().time
        calendar.add(Calendar.MONTH, -18)
        val eighteenMonthsAgo = dateFormat.format(calendar.time)

        // Main trending - TMDB's daily trending for fresh content
        val trendingMovies = async { fetchUpTo40 { page -> tmdbApi.getTrendingMovies(apiKey, page = page) } }
        val trendingTv = async { fetchUpTo40 { page -> tmdbApi.getTrendingTv(apiKey, page = page) } }

        // Anime: popularity.desc tracks current buzz, air_date filter for currently airing
        val trendingAnime = async {
            fetchUpTo40 { page ->
                tmdbApi.discoverTv(
                    apiKey,
                    genres = "16",
                    keywords = "210024",  // "anime" keyword ID
                    sortBy = "popularity.desc",
                    minVoteCount = 10,
                    airDateGte = eighteenMonthsAgo,
                    page = page
                )
            }
        }

        // Provider-based categories
        val netflix = async {
            fetchUpTo40 { page ->
                tmdbApi.discoverTv(
                    apiKey,
                    watchProviders = 8,
                    sortBy = "popularity.desc",
                    minVoteCount = 10,
                    airDateGte = twelveMonthsAgo,
                    page = page
                )
            }
        }
        val disney = async {
            fetchUpTo40 { page ->
                tmdbApi.discoverTv(
                    apiKey,
                    watchProviders = 337,
                    sortBy = "popularity.desc",
                    minVoteCount = 10,
                    airDateGte = twelveMonthsAgo,
                    page = page
                )
            }
        }
        val prime = async {
            fetchUpTo40 { page ->
                tmdbApi.discoverTv(
                    apiKey,
                    watchProviders = 9,
                    sortBy = "popularity.desc",
                    minVoteCount = 10,
                    airDateGte = twelveMonthsAgo,
                    page = page
                )
            }
        }
        val hboMax = async {
            fetchUpTo40 { page ->
                tmdbApi.discoverTv(
                    apiKey,
                    watchProviders = 1899, // Max (formerly HBO Max)
                    sortBy = "popularity.desc",
                    minVoteCount = 10,
                    airDateGte = twelveMonthsAgo,
                    page = page
                )
            }
        }
        val appleTv = async {
            fetchUpTo40 { page ->
                tmdbApi.discoverTv(
                    apiKey,
                    watchProviders = 350, // Apple TV+
                    sortBy = "popularity.desc",
                    minVoteCount = 10,
                    airDateGte = twelveMonthsAgo,
                    page = page
                )
            }
        }
        val paramount = async {
            fetchUpTo40 { page ->
                tmdbApi.discoverTv(
                    apiKey,
                    watchProviders = 2303, // Paramount+ Premium
                    sortBy = "popularity.desc",
                    minVoteCount = 10,
                    airDateGte = twelveMonthsAgo,
                    page = page
                )
            }
        }
        val hulu = async {
            fetchUpTo40 { page ->
                tmdbApi.discoverTv(
                    apiKey,
                    watchProviders = 15, // Hulu
                    sortBy = "popularity.desc",
                    minVoteCount = 10,
                    airDateGte = twelveMonthsAgo,
                    page = page
                )
            }
        }
        val peacock = async {
            fetchUpTo40 { page ->
                tmdbApi.discoverTv(
                    apiKey,
                    watchProviders = 386, // Peacock
                    sortBy = "popularity.desc",
                    minVoteCount = 10,
                    airDateGte = twelveMonthsAgo,
                    page = page
                )
            }
        }

        // Show up to 40 items per category.
        // Keep categories resilient: if a provider call fails, we keep the other rows.
        val maxItemsPerCategory = 40
        suspend fun safeItems(fetch: suspend () -> List<TmdbMediaItem>, mediaType: MediaType): List<MediaItem> {
            return runCatching { fetch() }
                .getOrElse { emptyList() }
                .take(maxItemsPerCategory)
                .map { it.toMediaItem(mediaType) }
        }

        val categories = listOf(
            Category(
                id = "trending_movies",
                title = "Trending Movies",
                items = safeItems({ trendingMovies.await() }, MediaType.MOVIE)
            ),
            Category(
                id = "trending_tv",
                title = "Trending Series",
                items = safeItems({ trendingTv.await() }, MediaType.TV)
            ),
            Category(
                id = "trending_anime",
                title = "Trending Anime",
                items = safeItems({ trendingAnime.await() }, MediaType.TV)
            ),
            Category(
                id = "trending_netflix",
                title = "Trending on Netflix",
                items = safeItems({ netflix.await() }, MediaType.TV)
            ),
            Category(
                id = "trending_disney",
                title = "Trending on Disney+",
                items = safeItems({ disney.await() }, MediaType.TV)
            ),
            Category(
                id = "trending_prime",
                title = "Trending on Prime Video",
                items = safeItems({ prime.await() }, MediaType.TV)
            ),
            Category(
                id = "trending_hbo",
                title = "Trending on Max",
                items = safeItems({ hboMax.await() }, MediaType.TV)
            ),
            Category(
                id = "trending_apple",
                title = "Trending on Apple TV+",
                items = safeItems({ appleTv.await() }, MediaType.TV)
            ),
            Category(
                id = "trending_paramount",
                title = "Trending on Paramount+",
                items = safeItems({ paramount.await() }, MediaType.TV)
            ),
            Category(
                id = "trending_hulu",
                title = "Trending on Hulu",
                items = safeItems({ hulu.await() }, MediaType.TV)
            ),
            Category(
                id = "trending_peacock",
                title = "Trending on Peacock",
                items = safeItems({ peacock.await() }, MediaType.TV)
            )
        )
        val nonEmpty = categories.filter { it.items.isNotEmpty() }
        nonEmpty.forEach { cacheItems(it.items) }
        nonEmpty
    }

    suspend fun loadCustomCatalog(catalog: CatalogConfig, maxItems: Int = 40): Category? = coroutineScope {
        val mediaRefs = when (catalog.sourceType) {
            CatalogSourceType.TRAKT -> loadTraktCatalogRefs(catalog.sourceUrl, catalog.sourceRef)
            CatalogSourceType.MDBLIST -> loadMdblistCatalogRefs(catalog.sourceUrl, catalog.sourceRef)
            CatalogSourceType.PREINSTALLED -> emptyList()
        }
        if (mediaRefs.isEmpty()) return@coroutineScope null

        val semaphore = Semaphore(6)
        val jobs = mediaRefs.distinct().take(maxItems).map { (type, tmdbId) ->
            async {
                semaphore.withPermit {
                    runCatching {
                        when (type) {
                            MediaType.MOVIE -> getMovieDetails(tmdbId)
                            MediaType.TV -> getTvDetails(tmdbId)
                        }
                    }.getOrNull()
                }
            }
        }
        val items = jobs.mapNotNull { it.await() }
        if (items.isEmpty()) return@coroutineScope null
        Category(
            id = catalog.id,
            title = catalog.title,
            items = items
        )
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
        val cacheKey = "tv_${tvId}_season_$seasonNumber"
        getFromCache(seasonEpisodesCache, cacheKey)?.let { return it }

        val season = tmdbApi.getTvSeason(tvId, seasonNumber, apiKey)
        // First ensure the global watched cache is initialized.
        traktRepository.initializeWatchedCache()

        // Get watched episodes - try global cache first (faster, more reliable).
        val watchedEpisodes = if (traktRepository.hasWatchedEpisodes(tvId)) {
            traktRepository.getWatchedEpisodesFromCache()
        } else {
            try {
                traktRepository.getWatchedEpisodesForShow(tvId)
            } catch (e: Exception) {
                emptySet<String>()
            }
        }

        val episodes = season.episodes.map { episode ->
            val episodeKey = "show_tmdb:$tvId:$seasonNumber:${episode.episodeNumber}"
            episode.toEpisode().copy(
                isWatched = episodeKey in watchedEpisodes
            )
        }
        seasonEpisodesCache[cacheKey] = CacheEntry(episodes, System.currentTimeMillis())
        return episodes
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

    private suspend fun loadTraktCatalogRefs(sourceUrl: String?, sourceRef: String? = null): List<Pair<MediaType, Int>> {
        suspend fun loadFromParsed(parsed: ParsedCatalogUrl): List<Pair<MediaType, Int>> {
            val items: List<TraktPublicListItem> = when (parsed) {
                is ParsedCatalogUrl.TraktUserList -> {
                    val movies = runCatching {
                        traktApi.getUserListItems(
                            clientId = Constants.TRAKT_CLIENT_ID,
                            username = parsed.username,
                            listId = parsed.listId,
                            type = "movies",
                            limit = 100
                        )
                    }.getOrElse { emptyList() }
                    val shows = runCatching {
                        traktApi.getUserListItems(
                            clientId = Constants.TRAKT_CLIENT_ID,
                            username = parsed.username,
                            listId = parsed.listId,
                            type = "shows",
                            limit = 100
                        )
                    }.getOrElse { emptyList() }
                    movies + shows
                }
                is ParsedCatalogUrl.TraktList -> {
                    val movies = runCatching {
                        traktApi.getListItems(
                            clientId = Constants.TRAKT_CLIENT_ID,
                            listId = parsed.listId,
                            type = "movies",
                            limit = 100
                        )
                    }.getOrElse { emptyList() }
                    val shows = runCatching {
                        traktApi.getListItems(
                            clientId = Constants.TRAKT_CLIENT_ID,
                            listId = parsed.listId,
                            type = "shows",
                            limit = 100
                        )
                    }.getOrElse { emptyList() }
                    movies + shows
                }
                else -> emptyList()
            }
            return mapTraktItemsToTmdbRefs(items)
        }

        val parsedFromRef = parseTraktRef(sourceRef)
        if (parsedFromRef != null) {
            val fromRef = loadFromParsed(parsedFromRef)
            if (fromRef.isNotEmpty()) return fromRef
        }
        val parsedFromUrl = sourceUrl?.let { CatalogUrlParser.parseTrakt(it) } ?: return emptyList()
        return loadFromParsed(parsedFromUrl)
    }

    private suspend fun mapTraktItemsToTmdbRefs(items: List<TraktPublicListItem>): List<Pair<MediaType, Int>> = coroutineScope {
        if (items.isEmpty()) return@coroutineScope emptyList()

        val direct = mutableListOf<Pair<MediaType, Int>>()
        data class Unresolved(val type: MediaType, val title: String, val year: Int?)
        val unresolved = mutableListOf<Unresolved>()

        items.forEach { item ->
            val movieTmdb = item.movie?.ids?.tmdb
            if (movieTmdb != null) {
                direct += MediaType.MOVIE to movieTmdb
                return@forEach
            }
            val showTmdb = item.show?.ids?.tmdb
            if (showTmdb != null) {
                direct += MediaType.TV to showTmdb
                return@forEach
            }

            val movieTitle = item.movie?.title?.trim().orEmpty()
            if (movieTitle.isNotBlank()) {
                unresolved += Unresolved(MediaType.MOVIE, movieTitle, item.movie?.year)
                return@forEach
            }
            val showTitle = item.show?.title?.trim().orEmpty()
            if (showTitle.isNotBlank()) {
                unresolved += Unresolved(MediaType.TV, showTitle, item.show?.year)
            }
        }

        if (unresolved.isEmpty()) return@coroutineScope direct.distinct()

        val semaphore = Semaphore(5)
        val resolved = unresolved
            .take(40)
            .map { candidate ->
                async {
                    semaphore.withPermit {
                        runCatching {
                            val search = tmdbApi.searchMulti(apiKey, candidate.title).results
                            val typeMatched = search.filter { result ->
                                val resultType = when (result.mediaType) {
                                    "movie" -> MediaType.MOVIE
                                    "tv" -> MediaType.TV
                                    else -> null
                                }
                                resultType == candidate.type
                            }
                            val strictYear = typeMatched.firstOrNull { result ->
                                val yearText = (result.releaseDate ?: result.firstAirDate)
                                    ?.take(4)
                                    ?.toIntOrNull()
                                candidate.year == null || yearText == candidate.year
                            }
                            val fallback = typeMatched.firstOrNull()
                            val picked = strictYear ?: fallback
                            picked?.id?.let { candidate.type to it }
                        }.getOrNull()
                    }
                }
            }
            .mapNotNull { it.await() }

        (direct + resolved).distinct()
    }

    private suspend fun loadMdblistCatalogRefs(sourceUrl: String?, sourceRef: String? = null): List<Pair<MediaType, Int>> {
        if (!sourceRef.isNullOrBlank() && sourceRef.startsWith("mdblist_trakt:")) {
            val traktUrl = sourceRef.removePrefix("mdblist_trakt:").trim()
            if (traktUrl.isNotBlank()) {
                val fromTraktRef = loadTraktCatalogRefs(traktUrl, null)
                if (fromTraktRef.isNotEmpty()) return fromTraktRef
            }
        }
        val url = sourceUrl ?: return emptyList()

        val jsonUrl = "${url.removeSuffix("/")}/json"
        val fromJson = fetchUrl(jsonUrl)?.let { payload ->
            parseMdblistJson(payload)
        } ?: emptyList()
        if (fromJson.isNotEmpty()) return fromJson

        val html = fetchUrl(url) ?: return emptyList()
        val traktLink = Regex(
            """https?://(?:www\.)?trakt\.tv/users/[^"'\s<]+/lists/[^"'\s<]+""",
            RegexOption.IGNORE_CASE
        ).find(html)?.value
        return if (traktLink != null) loadTraktCatalogRefs(traktLink) else emptyList()
    }

    private fun parseTraktRef(sourceRef: String?): ParsedCatalogUrl? {
        if (sourceRef.isNullOrBlank()) return null
        return when {
            sourceRef.startsWith("trakt_user:") -> {
                val parts = sourceRef.removePrefix("trakt_user:").split(":")
                if (parts.size >= 2) {
                    ParsedCatalogUrl.TraktUserList(parts[0], parts[1])
                } else {
                    null
                }
            }
            sourceRef.startsWith("trakt_list:") -> {
                val listId = sourceRef.removePrefix("trakt_list:").trim()
                if (listId.isBlank()) null else ParsedCatalogUrl.TraktList(listId)
            }
            sourceRef.startsWith("mdblist_trakt:") -> {
                val url = sourceRef.removePrefix("mdblist_trakt:").trim()
                if (url.isBlank()) null else CatalogUrlParser.parseTrakt(url)
            }
            else -> null
        }
    }

    private fun parseMdblistJson(payload: String): List<Pair<MediaType, Int>> {
        val type = object : TypeToken<List<Map<String, Any?>>>() {}.type
        val rows = runCatching { gson.fromJson<List<Map<String, Any?>>>(payload, type) }.getOrNull()
            ?: return emptyList()

        return rows.mapNotNull { row ->
            val tmdbId = sequenceOf("tmdb_id", "tmdb", "tmdbId", "id")
                .mapNotNull { key -> row[key].toIntSafe() }
                .firstOrNull()
                ?: return@mapNotNull null
            val mediaTypeRaw = sequenceOf("mediatype", "media_type", "type")
                .mapNotNull { key -> row[key]?.toString()?.lowercase() }
                .firstOrNull()
                ?: "movie"

            val mediaType = if (mediaTypeRaw.contains("tv") || mediaTypeRaw.contains("show") || mediaTypeRaw.contains("series")) {
                MediaType.TV
            } else {
                MediaType.MOVIE
            }
            mediaType to tmdbId
        }
    }

    private fun fetchUrl(url: String): String? {
        val request = Request.Builder()
            .url(url)
            .header("User-Agent", "Mozilla/5.0 (Android TV; ARVIO)")
            .build()
        return runCatching {
            okHttpClient.newCall(request).execute().use { response ->
                if (!response.isSuccessful) return@use null
                response.body?.string()
            }
        }.getOrNull()
    }
}
private fun Any?.toIntSafe(): Int? {
    return when (this) {
        is Number -> this.toInt()
        is String -> this.toIntOrNull()
        else -> null
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
        image = posterPath?.let { "${Constants.IMAGE_BASE}$it" }
            ?: backdropPath?.let { "${Constants.BACKDROP_BASE}$it" }
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
        image = posterPath?.let { "${Constants.IMAGE_BASE}$it" }
            ?: backdropPath?.let { "${Constants.BACKDROP_BASE}$it" }
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
        image = posterPath?.let { "${Constants.IMAGE_BASE}$it" }
            ?: backdropPath?.let { "${Constants.BACKDROP_BASE}$it" }
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
