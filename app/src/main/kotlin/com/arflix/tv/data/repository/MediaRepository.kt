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
import com.arflix.tv.data.api.TmdbWatchProviderRegion
import com.arflix.tv.data.api.TraktApi
import com.arflix.tv.data.api.TraktPublicListItem
import com.arflix.tv.data.api.StremioMetaPreview
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
import java.net.URLDecoder
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.concurrent.ConcurrentHashMap
import com.arflix.tv.util.ParsedCatalogUrl
import javax.inject.Inject
import javax.inject.Singleton

data class StreamingServiceInfo(
    val id: Int,
    val name: String,
    val logoUrl: String? = null
)

data class StreamingServicesResult(
    val region: String,
    val services: List<StreamingServiceInfo>
)

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
    private val okHttpClient: OkHttpClient,
    private val streamRepository: StreamRepository
) {
    data class CategoryPageResult(
        val items: List<MediaItem>,
        val hasMore: Boolean
    )

    private val apiKey = Constants.TMDB_API_KEY
    private val gson = Gson()

    /** TMDB content language (e.g. "en-US", "fr-FR", "nl-NL"). Null = TMDB default (English). */
    @Volatile
    var contentLanguage: String? = null

    // === IN-MEMORY CACHE FOR PERFORMANCE ===
    private data class CacheEntry<T>(val data: T, val timestamp: Long)
    private val CACHE_TTL_MS = 5 * 60 * 1000L // 5 minutes

    private val detailsCache = mutableMapOf<String, CacheEntry<MediaItem>>()
    private val castCache = mutableMapOf<String, CacheEntry<List<CastMember>>>()
    private val similarCache = mutableMapOf<String, CacheEntry<List<MediaItem>>>()
    private val logoCache = mutableMapOf<String, CacheEntry<String?>>()
    private val reviewsCache = mutableMapOf<String, CacheEntry<List<Review>>>()
    private val watchProvidersCache = mutableMapOf<String, CacheEntry<StreamingServicesResult?>>()
    private val seasonEpisodesCache = mutableMapOf<String, CacheEntry<List<Episode>>>()
    private val imdbIdCache = ConcurrentHashMap<String, String>()
    private val addonImdbToTmdbCache = ConcurrentHashMap<String, CacheEntry<Pair<MediaType, Int>?>>()
    private val addonTitleToTmdbCache = ConcurrentHashMap<String, CacheEntry<Pair<MediaType, Int>?>>()

    private fun <T> getFromCache(cache: Map<String, CacheEntry<T>>, key: String): T? {
        val entry = cache[key] ?: return null
        return if (System.currentTimeMillis() - entry.timestamp < CACHE_TTL_MS) entry.data else null
    }

    private fun getAddonImdbLookupEntry(imdbId: String): CacheEntry<Pair<MediaType, Int>?>? {
        val entry = addonImdbToTmdbCache[imdbId] ?: return null
        return if (System.currentTimeMillis() - entry.timestamp < CACHE_TTL_MS) {
            entry
        } else {
            addonImdbToTmdbCache.remove(imdbId)
            null
        }
    }

    private fun getAddonImdbLookup(imdbId: String): Pair<MediaType, Int>? {
        return getAddonImdbLookupEntry(imdbId)?.data
    }

    private fun cacheAddonImdbLookup(imdbId: String, value: Pair<MediaType, Int>?) {
        addonImdbToTmdbCache[imdbId] = CacheEntry(value, System.currentTimeMillis())
    }

    private fun getAddonTitleLookupEntry(key: String): CacheEntry<Pair<MediaType, Int>?>? {
        val entry = addonTitleToTmdbCache[key] ?: return null
        return if (System.currentTimeMillis() - entry.timestamp < CACHE_TTL_MS) {
            entry
        } else {
            addonTitleToTmdbCache.remove(key)
            null
        }
    }

    private fun cacheAddonTitleLookup(key: String, value: Pair<MediaType, Int>?) {
        addonTitleToTmdbCache[key] = CacheEntry(value, System.currentTimeMillis())
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
            CatalogConfig("favorite_tv", "Favorite TV", CatalogSourceType.PREINSTALLED, isPreinstalled = true),
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
        val trendingMovies = async { fetchUpTo40 { page -> tmdbApi.getTrendingMovies(apiKey, language = contentLanguage, page = page) } }
        val trendingTv = async { fetchUpTo40 { page -> tmdbApi.getTrendingTv(apiKey, language = contentLanguage, page = page) } }

        // Anime: popularity.desc tracks current buzz, air_date filter for currently airing
        val trendingAnime = async {
            fetchUpTo40 { page ->
                tmdbApi.discoverTv(
                    apiKey, language = contentLanguage,
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
                    apiKey, language = contentLanguage,
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
                    apiKey, language = contentLanguage,
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
                    apiKey, language = contentLanguage,
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
                    apiKey, language = contentLanguage,
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
                    apiKey, language = contentLanguage,
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
                    apiKey, language = contentLanguage,
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
                    apiKey, language = contentLanguage,
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
                    apiKey, language = contentLanguage,
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

    suspend fun loadHomeCategoryPage(
        categoryId: String,
        page: Int
    ): CategoryPageResult {
        if (page < 1) return CategoryPageResult(emptyList(), hasMore = false)

        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.MONTH, -12)
        val twelveMonthsAgo = dateFormat.format(calendar.time)
        calendar.time = Calendar.getInstance().time
        calendar.add(Calendar.MONTH, -18)
        val eighteenMonthsAgo = dateFormat.format(calendar.time)

        val response = runCatching {
            when (categoryId) {
                "trending_movies" -> tmdbApi.getTrendingMovies(apiKey, language = contentLanguage, page = page)
                "trending_tv" -> tmdbApi.getTrendingTv(apiKey, language = contentLanguage, page = page)
                "trending_anime" -> tmdbApi.discoverTv(
                    apiKey, language = contentLanguage,
                    genres = "16",
                    keywords = "210024",
                    sortBy = "popularity.desc",
                    minVoteCount = 10,
                    airDateGte = eighteenMonthsAgo,
                    page = page
                )
                "trending_netflix" -> tmdbApi.discoverTv(
                    apiKey, language = contentLanguage,
                    watchProviders = 8,
                    sortBy = "popularity.desc",
                    minVoteCount = 10,
                    airDateGte = twelveMonthsAgo,
                    page = page
                )
                "trending_disney" -> tmdbApi.discoverTv(
                    apiKey, language = contentLanguage,
                    watchProviders = 337,
                    sortBy = "popularity.desc",
                    minVoteCount = 10,
                    airDateGte = twelveMonthsAgo,
                    page = page
                )
                "trending_prime" -> tmdbApi.discoverTv(
                    apiKey, language = contentLanguage,
                    watchProviders = 9,
                    sortBy = "popularity.desc",
                    minVoteCount = 10,
                    airDateGte = twelveMonthsAgo,
                    page = page
                )
                "trending_hbo" -> tmdbApi.discoverTv(
                    apiKey, language = contentLanguage,
                    watchProviders = 1899,
                    sortBy = "popularity.desc",
                    minVoteCount = 10,
                    airDateGte = twelveMonthsAgo,
                    page = page
                )
                "trending_apple" -> tmdbApi.discoverTv(
                    apiKey, language = contentLanguage,
                    watchProviders = 350,
                    sortBy = "popularity.desc",
                    minVoteCount = 10,
                    airDateGte = twelveMonthsAgo,
                    page = page
                )
                "trending_paramount" -> tmdbApi.discoverTv(
                    apiKey, language = contentLanguage,
                    watchProviders = 2303,
                    sortBy = "popularity.desc",
                    minVoteCount = 10,
                    airDateGte = twelveMonthsAgo,
                    page = page
                )
                "trending_hulu" -> tmdbApi.discoverTv(
                    apiKey, language = contentLanguage,
                    watchProviders = 15,
                    sortBy = "popularity.desc",
                    minVoteCount = 10,
                    airDateGte = twelveMonthsAgo,
                    page = page
                )
                "trending_peacock" -> tmdbApi.discoverTv(
                    apiKey, language = contentLanguage,
                    watchProviders = 386,
                    sortBy = "popularity.desc",
                    minVoteCount = 10,
                    airDateGte = twelveMonthsAgo,
                    page = page
                )
                else -> null
            }
        }.getOrNull() ?: return CategoryPageResult(emptyList(), hasMore = false)

        val mediaType = if (categoryId == "trending_movies") MediaType.MOVIE else MediaType.TV
        val items = response.results
            .map { it.toMediaItem(mediaType) }
            .distinctBy { "${it.mediaType.name}_${it.id}" }
        if (items.isNotEmpty()) {
            cacheItems(items)
        }
        return CategoryPageResult(
            items = items,
            hasMore = response.page < response.totalPages
        )
    }

    suspend fun loadCustomCatalog(catalog: CatalogConfig, maxItems: Int = 40): Category? = coroutineScope {
        val mediaRefs = when (catalog.sourceType) {
            CatalogSourceType.TRAKT -> loadTraktCatalogRefs(catalog.sourceUrl, catalog.sourceRef)
            CatalogSourceType.MDBLIST -> loadMdblistCatalogRefs(catalog.sourceUrl, catalog.sourceRef)
            CatalogSourceType.ADDON -> loadAddonCatalogRefsPage(catalog, offset = 0, limit = maxItems).refs
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

    suspend fun loadCustomCatalogPage(
        catalog: CatalogConfig,
        offset: Int,
        limit: Int
    ): CategoryPageResult = coroutineScope {
        if (limit <= 0 || offset < 0) return@coroutineScope CategoryPageResult(emptyList(), hasMore = false)

        val pageRefs: List<Pair<MediaType, Int>>
        val hasMore: Boolean
        if (catalog.sourceType == CatalogSourceType.ADDON) {
            val page = loadAddonCatalogRefsPage(catalog, offset, limit)
            pageRefs = page.refs
            hasMore = page.hasMore
        } else {
            val mediaRefs = when (catalog.sourceType) {
                CatalogSourceType.TRAKT -> loadTraktCatalogRefs(catalog.sourceUrl, catalog.sourceRef)
                CatalogSourceType.MDBLIST -> loadMdblistCatalogRefs(catalog.sourceUrl, catalog.sourceRef)
                CatalogSourceType.ADDON -> emptyList()
                CatalogSourceType.PREINSTALLED -> emptyList()
            }.distinct()

            if (mediaRefs.isEmpty()) return@coroutineScope CategoryPageResult(emptyList(), hasMore = false)

            pageRefs = mediaRefs.drop(offset).take(limit)
            if (pageRefs.isEmpty()) {
                return@coroutineScope CategoryPageResult(emptyList(), hasMore = false)
            }
            hasMore = offset + pageRefs.size < mediaRefs.size
        }

        val semaphore = Semaphore(6)
        val jobs = pageRefs.map { (type, tmdbId) ->
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
        if (items.isNotEmpty()) {
            cacheItems(items)
        }
        CategoryPageResult(
            items = items,
            hasMore = hasMore
        )
    }

    private data class AddonCatalogDescriptor(
        val addonId: String,
        val catalogType: String,
        val catalogId: String
    )

    private data class UnresolvedAddonMeta(
        val id: String,
        val typeHint: MediaType?
    )

    private data class AddonCatalogRefsPage(
        val refs: List<Pair<MediaType, Int>>,
        val hasMore: Boolean
    )

    private suspend fun loadAddonCatalogRefsPage(
        catalog: CatalogConfig,
        offset: Int,
        limit: Int
    ): AddonCatalogRefsPage = coroutineScope {
        val descriptor = resolveAddonCatalogDescriptor(catalog)
            ?: return@coroutineScope AddonCatalogRefsPage(emptyList(), hasMore = false)

        val accumulated = LinkedHashSet<Pair<MediaType, Int>>()
        var probeOffset = offset.coerceAtLeast(0)
        var hasMore = false
        var probes = 0
        val maxProbes = 3

        while (probes < maxProbes && accumulated.size < limit) {
            val response = runCatching {
                streamRepository.getAddonCatalogPage(
                    addonId = descriptor.addonId,
                    catalogType = descriptor.catalogType,
                    catalogId = descriptor.catalogId,
                    skip = probeOffset
                )
            }.getOrNull() ?: break

            val metas = response.metas ?: response.items ?: emptyList()
            if (metas.isEmpty()) {
                hasMore = false
                break
            }

            parseAddonPageRefs(
                metas = metas,
                descriptor = descriptor
            ).forEach { accumulated.add(it) }

            hasMore = metas.size >= limit
            if (!hasMore) break

            probeOffset += metas.size
            probes += 1
        }

        AddonCatalogRefsPage(
            refs = accumulated.take(limit),
            hasMore = hasMore
        )
    }

    private suspend fun parseAddonPageRefs(
        metas: List<StremioMetaPreview>,
        descriptor: AddonCatalogDescriptor
    ): List<Pair<MediaType, Int>> = coroutineScope {
        val typeHint = addonCatalogTypeToMediaType(descriptor.catalogType)
        val directRefs = mutableListOf<Pair<MediaType, Int>>()
        val imdbCandidates = mutableListOf<Pair<String, MediaType?>>()
        val titleCandidates = mutableListOf<Pair<String, MediaType?>>()
        val unresolvedMetaCandidates = mutableListOf<UnresolvedAddonMeta>()
        val seenImdb = HashSet<String>()
        val seenTitle = HashSet<String>()
        val seenMetaId = HashSet<String>()

        metas.forEach { meta ->
            val direct = parseTmdbRefFromAddonMeta(meta, typeHint)
            if (direct != null) {
                directRefs += direct
                return@forEach
            }
            val inferredHint = typeHint ?: addonCatalogTypeToMediaType(meta.type)
            val imdb = extractImdbId(meta)
            if (!imdb.isNullOrBlank() && seenImdb.add(imdb)) {
                imdbCandidates += imdb to inferredHint
                return@forEach
            }
            val metaId = meta.id?.trim().orEmpty()
            if (metaId.isNotBlank() && seenMetaId.add(metaId)) {
                unresolvedMetaCandidates += UnresolvedAddonMeta(
                    id = metaId,
                    typeHint = inferredHint
                )
            }
        }

        metas.forEach { meta ->
            if (parseTmdbRefFromAddonMeta(meta, typeHint) != null) return@forEach
            if (extractImdbId(meta) != null) return@forEach
            val title = meta.name?.trim().orEmpty()
            if (title.isBlank()) return@forEach
            val metaHint = typeHint ?: addonCatalogTypeToMediaType(meta.type)
            val titleKey = "${metaHint?.name ?: "ANY"}|${title.lowercase(Locale.US)}"
            if (seenTitle.add(titleKey)) {
                titleCandidates += title to metaHint
            }
        }

        val metaSemaphore = Semaphore(2)
        val resolvedFromMeta = unresolvedMetaCandidates.take(8).map { unresolved ->
            async {
                metaSemaphore.withPermit {
                    resolveAddonMetaToTmdbRef(
                        descriptor = descriptor,
                        unresolved = unresolved
                    )
                }
            }
        }.mapNotNull { it.await() }

        val imdbSemaphore = Semaphore(4)
        val resolvedImdbRefs = imdbCandidates.map { (imdbId, hint) ->
            async {
                imdbSemaphore.withPermit {
                    resolveImdbToTmdbRef(imdbId, hint)
                }
            }
        }.mapNotNull { it.await() }

        val titleSemaphore = Semaphore(2)
        val resolvedTitleRefs = titleCandidates.take(12).map { (title, hint) ->
            async {
                titleSemaphore.withPermit {
                    resolveTitleToTmdbRef(title, hint)
                }
            }
        }.mapNotNull { it.await() }

        (directRefs + resolvedFromMeta + resolvedImdbRefs + resolvedTitleRefs).distinct()
    }

    private suspend fun resolveAddonMetaToTmdbRef(
        descriptor: AddonCatalogDescriptor,
        unresolved: UnresolvedAddonMeta
    ): Pair<MediaType, Int>? {
        val mediaType = unresolved.typeHint ?: addonCatalogTypeToMediaType(descriptor.catalogType) ?: return null
        val requestedType = when (mediaType) {
            MediaType.MOVIE -> "movie"
            MediaType.TV -> "series"
        }
        val meta = runCatching {
            streamRepository.getAddonMeta(
                addonId = descriptor.addonId,
                mediaType = requestedType,
                mediaId = unresolved.id
            )
        }.getOrNull() ?: return null

        parseTmdbRefFromAddonMeta(meta, mediaType)?.let { return it }
        val imdbId = extractImdbId(meta) ?: return null
        return resolveImdbToTmdbRef(imdbId, mediaType)
    }

    private suspend fun resolveImdbToTmdbRef(
        imdbId: String,
        mediaTypeHint: MediaType?
    ): Pair<MediaType, Int>? {
        val normalizedImdb = imdbId.trim()
        if (normalizedImdb.isBlank()) return null

        getAddonImdbLookupEntry(normalizedImdb)?.let { cached ->
            return cached.data
        }

        val findResponse = runCatching {
            tmdbApi.findByExternalId(
                externalId = normalizedImdb,
                apiKey = apiKey,
                externalSource = "imdb_id"
            )
        }.getOrNull()

        val resolved = findResponse?.let { response ->
            val movies = response.movieResults
            val series = response.tvResults
            when (mediaTypeHint) {
                MediaType.MOVIE -> movies.maxByOrNull { it.popularity }?.id?.let { MediaType.MOVIE to it }
                MediaType.TV -> series.maxByOrNull { it.popularity }?.id?.let { MediaType.TV to it }
                else -> {
                    val movie = movies.maxByOrNull { it.popularity }
                    val tv = series.maxByOrNull { it.popularity }
                    when {
                        movie == null && tv == null -> null
                        movie != null && tv == null -> MediaType.MOVIE to movie.id
                        movie == null && tv != null -> MediaType.TV to tv.id
                        else -> {
                            if ((movie?.popularity ?: 0f) >= (tv?.popularity ?: 0f)) {
                                MediaType.MOVIE to movie!!.id
                            } else {
                                MediaType.TV to tv!!.id
                            }
                        }
                    }
                }
            }
        }

        cacheAddonImdbLookup(normalizedImdb, resolved)
        return resolved
    }

    private suspend fun resolveTitleToTmdbRef(
        rawTitle: String,
        mediaTypeHint: MediaType?
    ): Pair<MediaType, Int>? {
        val title = rawTitle.trim()
        if (title.isBlank()) return null

        val cleanedTitle = title
            .replace(Regex("""\s+\(\d{4}\)$"""), "")
            .trim()
            .ifBlank { title }
        val cacheKey = "${mediaTypeHint?.name ?: "ANY"}|${cleanedTitle.lowercase(Locale.US)}"
        getAddonTitleLookupEntry(cacheKey)?.let { cached ->
            return cached.data
        }

        val response = runCatching {
            tmdbApi.searchMulti(
                apiKey = apiKey,
                query = cleanedTitle,
                language = contentLanguage,
                page = 1
            )
        }.getOrNull()

        val candidates = response?.results
            ?.mapNotNull { item ->
                val type = when (item.mediaType?.lowercase(Locale.US)) {
                    "movie" -> MediaType.MOVIE
                    "tv" -> MediaType.TV
                    else -> null
                } ?: return@mapNotNull null
                Triple(type, item.id, item.popularity)
            }
            .orEmpty()

        val scoped = if (mediaTypeHint != null) {
            candidates.filter { it.first == mediaTypeHint }
        } else {
            candidates
        }
        val best = (if (scoped.isNotEmpty()) scoped else candidates)
            .maxByOrNull { it.third }
            ?.let { it.first to it.second }

        cacheAddonTitleLookup(cacheKey, best)
        return best
    }

    private fun resolveAddonCatalogDescriptor(catalog: CatalogConfig): AddonCatalogDescriptor? {
        val addonId = catalog.addonId?.trim().takeUnless { it.isNullOrBlank() }
        val catalogType = normalizeAddonCatalogType(catalog.addonCatalogType)
        val catalogId = catalog.addonCatalogId?.trim().takeUnless { it.isNullOrBlank() }
        if (addonId != null && catalogType != null && catalogId != null) {
            return AddonCatalogDescriptor(addonId, catalogType, catalogId)
        }

        val sourceRef = catalog.sourceRef?.trim().orEmpty()
        if (!sourceRef.startsWith("addon_catalog|")) return null
        val parts = sourceRef.removePrefix("addon_catalog|").split("|")
        if (parts.size != 3) return null

        val parsedAddonId = decodeCatalogRefPart(parts[0]).trim()
        val parsedType = normalizeAddonCatalogType(decodeCatalogRefPart(parts[1]))
        val parsedCatalogId = decodeCatalogRefPart(parts[2]).trim()
        if (parsedAddonId.isBlank() || parsedType == null || parsedCatalogId.isBlank()) return null

        return AddonCatalogDescriptor(parsedAddonId, parsedType, parsedCatalogId)
    }

    private fun decodeCatalogRefPart(value: String): String {
        return runCatching { URLDecoder.decode(value, "UTF-8") }.getOrDefault(value)
    }

    private fun normalizeAddonCatalogType(rawType: String?): String? {
        return when (rawType?.trim()?.lowercase()) {
            "movie" -> "movie"
            "series" -> "series"
            "tv" -> "tv"
            "show" -> "show"
            "shows" -> "shows"
            else -> null
        }
    }

    private fun addonCatalogTypeToMediaType(rawType: String?): MediaType? {
        return when (normalizeAddonCatalogType(rawType)) {
            "movie" -> MediaType.MOVIE
            "series", "tv", "show", "shows" -> MediaType.TV
            else -> null
        }
    }

    private fun parseTmdbRefFromAddonMeta(
        meta: StremioMetaPreview,
        typeHint: MediaType?
    ): Pair<MediaType, Int>? {
        val normalizedHint = typeHint ?: addonCatalogTypeToMediaType(meta.type)
        val rawTmdb = meta.tmdbId?.trim().orEmpty()
        val tmdbFromField = rawTmdb.toIntOrNull()
            ?: Regex("""\d+""").find(rawTmdb)?.value?.toIntOrNull()
        if (tmdbFromField != null && normalizedHint != null) {
            return normalizedHint to tmdbFromField
        }

        val rawId = meta.id?.trim().orEmpty()
        if (rawId.isBlank()) return null

        // Plain numeric IDs are commonly TMDB IDs in catalog resources.
        if (rawId.all { it.isDigit() }) {
            val tmdbId = rawId.toIntOrNull() ?: return null
            return normalizedHint?.let { mediaType -> mediaType to tmdbId }
        }

        // IDs like movie:12345 or series:12345
        val typedIdMatch = Regex("""^(movie|series|tv|show|shows):(\d+)$""", RegexOption.IGNORE_CASE).find(rawId)
        if (typedIdMatch != null) {
            val token = typedIdMatch.groupValues[1].lowercase()
            val tmdbId = typedIdMatch.groupValues[2].toIntOrNull() ?: return null
            val mediaType = when (token) {
                "movie" -> MediaType.MOVIE
                else -> MediaType.TV
            }
            return mediaType to tmdbId
        }

        if (!rawId.startsWith("tmdb", ignoreCase = true)) return null

        val parts = rawId.split(":").filter { it.isNotBlank() }
        if (parts.isEmpty()) return null

        if (parts.size == 2) {
            val tmdbId = parts[1].toIntOrNull() ?: return null
            return normalizedHint?.let { mediaType -> mediaType to tmdbId }
        }

        val candidateTokens = parts.drop(1)
        val tmdbId = candidateTokens
            .firstOrNull { token -> token.toIntOrNull() != null }
            ?.toIntOrNull()
            ?: return null
        val mediaType = candidateTokens
            .firstOrNull { token ->
                token.equals("movie", ignoreCase = true) ||
                    token.equals("series", ignoreCase = true) ||
                    token.equals("tv", ignoreCase = true) ||
                    token.equals("show", ignoreCase = true) ||
                    token.equals("shows", ignoreCase = true)
            }
            ?.let { token ->
                when (token.lowercase()) {
                    "movie" -> MediaType.MOVIE
                    "series", "tv", "show", "shows" -> MediaType.TV
                    else -> normalizedHint
                }
            }
            ?: normalizedHint
            ?: return null

        return mediaType to tmdbId
    }

    private fun extractImdbId(meta: StremioMetaPreview): String? {
        val direct = meta.imdbId?.trim().takeUnless { it.isNullOrBlank() }
        if (!direct.isNullOrBlank() && direct.startsWith("tt")) {
            return direct
        }

        val fromId = meta.id?.trim().orEmpty()
        if (fromId.startsWith("tt", ignoreCase = true)) return fromId
        if (fromId.startsWith("imdb:", ignoreCase = true)) {
            val candidate = fromId.substringAfter(':').trim()
            if (candidate.startsWith("tt", ignoreCase = true)) return candidate
        }

        val match = Regex("""tt\d{5,}""", RegexOption.IGNORE_CASE).find(fromId)
        return match?.value
    }

    /**
     * Get movie details (cached)
     */
    suspend fun getMovieDetails(movieId: Int): MediaItem {
        val cacheKey = "movie_$movieId"
        getFromCache(detailsCache, cacheKey)?.let { cached ->
            // Only use cache hit if it was populated from the full TMDB details API.
            // Home screen items share the same cache key but lack detail-level fields.
            if (cached.duration.isNotBlank()) return cached
        }

        val details = tmdbApi.getMovieDetails(movieId, apiKey, language = contentLanguage)
        val item = details.toMediaItem()
        detailsCache[cacheKey] = CacheEntry(item, System.currentTimeMillis())
        return item
    }

    /**
     * Get TV show details (cached)
     */
    suspend fun getTvDetails(tvId: Int): MediaItem {
        val cacheKey = "tv_$tvId"
        getFromCache(detailsCache, cacheKey)?.let { cached ->
            // Only use cache hit if it was populated from the full TMDB details API.
            // Home screen items (from trending/discover lists) share the same cache key
            // but lack totalEpisodes (which stores numberOfSeasons). Without this check,
            // the stale list-level item prevents the real details fetch, causing the
            // season selector to never appear (totalSeasons stays at 1).
            if (cached.totalEpisodes != null) return cached
        }

        val details = tmdbApi.getTvDetails(tvId, apiKey, language = contentLanguage)
        val item = details.toMediaItem()
        detailsCache[cacheKey] = CacheEntry(item, System.currentTimeMillis())
        return item
    }
    
    /**
     * Get season episodes with Trakt watched status
     */
    suspend fun getSeasonEpisodes(tvId: Int, seasonNumber: Int): List<Episode> {
        val cacheKey = "tv_${tvId}_season_$seasonNumber"
        val cachedEpisodes = getFromCache(seasonEpisodesCache, cacheKey)

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
        val hasShowWatchedData = watchedEpisodes.any { it.startsWith("show_tmdb:$tvId:") }

        // Re-apply watched status on cached episodes so stale season cache doesn't hide badges.
        if (cachedEpisodes != null) {
            return cachedEpisodes.map { episode ->
                val episodeKey = "show_tmdb:$tvId:${episode.seasonNumber}:${episode.episodeNumber}"
                episode.copy(
                    isWatched = if (hasShowWatchedData) episodeKey in watchedEpisodes else episode.isWatched
                )
            }
        }

        val season = tmdbApi.getTvSeason(tvId, seasonNumber, apiKey, language = contentLanguage)

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
        val credits = tmdbApi.getCredits(type, mediaId, apiKey, language = contentLanguage)
        val cast = credits.cast
            .distinctBy { it.id } // TMDB can occasionally return duplicate cast IDs.
            .take(15)
            .map { it.toCastMember() }
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
            tmdbApi.getRecommendations(type, mediaId, apiKey, language = contentLanguage)
        } catch (e: Exception) {
            null
        }

        val result = if (recommendations != null && recommendations.results.isNotEmpty()) {
            recommendations.results
                .map { it.toMediaItem(mediaType) }
                .distinctBy { it.id }
                .take(12)
        } else {
            val similar = tmdbApi.getSimilar(type, mediaId, apiKey, language = contentLanguage)
            similar.results
                .map { it.toMediaItem(mediaType) }
                .distinctBy { it.id }
                .take(12)
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
            val videos = tmdbApi.getVideos(type, mediaId, apiKey, language = contentLanguage)
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
        val person = tmdbApi.getPersonDetails(personId, apiKey, language = contentLanguage)
        return person.toPersonDetails()
    }
    
    /**
     * Search media
     */
    suspend fun search(query: String): List<MediaItem> {
        val results = tmdbApi.searchMulti(apiKey, query, language = contentLanguage)
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
     * Discover movies via TMDB discover API with optional genre/sort/vote filters.
     */
    suspend fun discoverMovies(
        genres: String? = null,
        sortBy: String = "popularity.desc",
        minVoteCount: Int? = null,
        page: Int = 1,
        language: String? = null,
        year: Int? = null,
        keywords: String? = null,
        releaseDateLte: String? = null,
        releaseDateGte: String? = null
    ): List<MediaItem> {
        val response = tmdbApi.discoverMovies(apiKey, genres = genres, sortBy = sortBy, minVoteCount = minVoteCount, page = page, originalLanguage = language, year = year, keywords = keywords, language = contentLanguage, releaseDateLte = releaseDateLte, releaseDateGte = releaseDateGte)
        val items = response.results.map { it.toMediaItem(MediaType.MOVIE) }
        cacheItems(items)
        return items
    }

    /**
     * Discover TV shows via TMDB discover API with optional genre/sort/vote/language/year filters.
     */
    suspend fun discoverTv(
        genres: String? = null,
        sortBy: String = "popularity.desc",
        minVoteCount: Int? = null,
        page: Int = 1,
        language: String? = null,
        year: Int? = null,
        keywords: String? = null,
        airDateLte: String? = null,
        airDateGte: String? = null
    ): List<MediaItem> {
        val response = tmdbApi.discoverTv(apiKey, genres = genres, sortBy = sortBy, minVoteCount = minVoteCount, page = page, originalLanguage = language, year = year, keywords = keywords, language = contentLanguage, airDateLte = airDateLte, airDateGte = airDateGte)
        val items = response.results.map { it.toMediaItem(MediaType.TV) }
        cacheItems(items)
        return items
    }

    /**
     * Load a single discover category for the search/discover page.
     */
    suspend fun loadDiscoverCategory(categoryId: String, title: String): Category? {
        return try {
            val dateFormat = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.US)
            val items = when (categoryId) {
                "trending_movies" -> {
                    val r1 = tmdbApi.getTrendingMovies(apiKey, language = contentLanguage, page = 1)
                    r1.results.map { it.toMediaItem(MediaType.MOVIE) }
                }
                "popular_movies" -> {
                    val r1 = tmdbApi.discoverMovies(apiKey, sortBy = "popularity.desc", language = contentLanguage, page = 1)
                    r1.results.map { it.toMediaItem(MediaType.MOVIE) }
                }
                "popular_tv" -> {
                    val r1 = tmdbApi.discoverTv(apiKey, sortBy = "popularity.desc", language = contentLanguage, page = 1)
                    r1.results.map { it.toMediaItem(MediaType.TV) }
                }
                "top_rated_movies" -> {
                    val r1 = tmdbApi.discoverMovies(apiKey, sortBy = "vote_average.desc", minVoteCount = 1000, language = contentLanguage, page = 1)
                    r1.results.map { it.toMediaItem(MediaType.MOVIE) }
                }
                "new_releases" -> {
                    val cal = java.util.Calendar.getInstance()
                    val today = dateFormat.format(cal.time)
                    cal.add(java.util.Calendar.DAY_OF_YEAR, -30)
                    val thirtyDaysAgo = dateFormat.format(cal.time)
                    val r1 = tmdbApi.discoverMovies(apiKey, sortBy = "popularity.desc", language = contentLanguage, releaseDateGte = thirtyDaysAgo, releaseDateLte = today, page = 1)
                    r1.results.map { it.toMediaItem(MediaType.MOVIE) }
                }
                else -> emptyList()
            }
            if (items.isEmpty()) null
            else {
                cacheItems(items)
                Category(id = categoryId, title = title, items = items.take(20))
            }
        } catch (e: Exception) {
            null
        }
    }

    /**
     * Get reviews for a movie or TV show from TMDB (cached)
     */
    suspend fun getReviews(mediaType: MediaType, mediaId: Int): List<Review> {
        val cacheKey = "${mediaType}_reviews_$mediaId"
        getFromCache(reviewsCache, cacheKey)?.let { return it }

        val type = if (mediaType == MediaType.TV) "tv" else "movie"
        return try {
            val response = tmdbApi.getReviews(type, mediaId, apiKey, language = contentLanguage)
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

    suspend fun getStreamingServices(
        mediaType: MediaType,
        mediaId: Int,
        preferredRegion: String? = null
    ): StreamingServicesResult? {
        val region = normalizeWatchRegion(preferredRegion)
        val cacheKey = "${mediaType}_watch_providers_${mediaId}_$region"
        val cachedEntry = watchProvidersCache[cacheKey]
        if (cachedEntry != null) {
            if (System.currentTimeMillis() - cachedEntry.timestamp < CACHE_TTL_MS) {
                return cachedEntry.data
            }
            watchProvidersCache.remove(cacheKey)
        }

        val response = runCatching {
            when (mediaType) {
                MediaType.MOVIE -> tmdbApi.getMovieWatchProviders(mediaId, apiKey)
                MediaType.TV -> tmdbApi.getTvWatchProviders(mediaId, apiKey)
            }
        }.getOrNull()

        val results = response?.results.orEmpty()
        if (results.isEmpty()) {
            watchProvidersCache[cacheKey] = CacheEntry(null, System.currentTimeMillis())
            return null
        }

        val requestedRegion = normalizeWatchRegion(preferredRegion)
        val localeRegion = normalizeWatchRegion(Locale.getDefault().country)
        val candidateRegions = listOf(requestedRegion, localeRegion, "US")
            .distinct()

        val resolvedFromPreferred = candidateRegions.firstNotNullOfOrNull { regionKey ->
            val regionData = findRegionProviders(results, regionKey) ?: return@firstNotNullOfOrNull null
            val services = toStreamingServiceList(regionData)
            if (services.isEmpty()) null else StreamingServicesResult(region = regionKey, services = services)
        }

        val resolved = resolvedFromPreferred ?: results.entries.firstNotNullOfOrNull { (regionKey, regionData) ->
            val services = toStreamingServiceList(regionData)
            if (services.isEmpty()) null else StreamingServicesResult(region = normalizeWatchRegion(regionKey), services = services)
        }

        watchProvidersCache[cacheKey] = CacheEntry(resolved, System.currentTimeMillis())
        return resolved
    }

    private fun findRegionProviders(
        allRegions: Map<String, TmdbWatchProviderRegion>,
        region: String
    ): TmdbWatchProviderRegion? {
        return allRegions.entries.firstOrNull { (key, _) ->
            key.equals(region, ignoreCase = true)
        }?.value
    }

    private fun toStreamingServiceList(regionData: TmdbWatchProviderRegion): List<StreamingServiceInfo> {
        val prioritizedLists = listOf(
            regionData.flatrate,
            regionData.free,
            regionData.ads,
            regionData.rent,
            regionData.buy
        )

        val deduped = LinkedHashMap<String, StreamingServiceInfo>()
        prioritizedLists.forEach { providers ->
            providers
                .sortedBy { it.displayPriority }
                .forEach providerLoop@{ provider ->
                    val canonicalName = canonicalStreamingServiceName(provider.providerName)
                    if (canonicalName.isBlank()) return@providerLoop
                    val key = canonicalName.lowercase(Locale.US)
                    if (deduped.containsKey(key)) return@providerLoop

                    val stableId = provider.providerId.takeIf { it > 0 } ?: canonicalName.hashCode()
                    val logoUrl = provider.logoPath?.let { path ->
                        "https://image.tmdb.org/t/p/w92$path"
                    }
                    deduped[key] = StreamingServiceInfo(
                        id = stableId,
                        name = canonicalName,
                        logoUrl = logoUrl
                    )
                }
        }

        return deduped.values.take(10)
    }

    private fun canonicalStreamingServiceName(raw: String?): String {
        val name = raw?.trim().orEmpty()
        if (name.isBlank()) return ""

        val normalized = name.lowercase(Locale.US)
        return when {
            normalized == "max" || normalized.contains("hbo") -> "HBO Max"
            normalized.contains("netflix") -> "Netflix"
            normalized.contains("prime") || normalized.contains("amazon") -> "Prime Video"
            normalized.contains("disney") -> "Disney+"
            normalized.contains("apple tv") -> "Apple TV+"
            normalized.contains("paramount") -> "Paramount+"
            normalized.contains("hulu") -> "Hulu"
            normalized.contains("peacock") -> "Peacock"
            normalized.contains("crunchyroll") -> "Crunchyroll"
            normalized.contains("youtube") -> "YouTube"
            else -> name
        }
    }

    private fun normalizeWatchRegion(region: String?): String {
        val value = region?.trim()?.uppercase(Locale.US).orEmpty()
        return value.takeIf { it.length == 2 } ?: "US"
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
                            val search = tmdbApi.searchMulti(apiKey, candidate.title, language = contentLanguage).results
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
