package com.arflix.tv.data.repository

import android.content.Context
import com.arflix.tv.R
import com.arflix.tv.data.api.TmdbApi
import com.arflix.tv.data.api.TmdbCastMember
import com.arflix.tv.data.api.TmdbCrewMember
import com.arflix.tv.data.api.TmdbEpisode
import com.arflix.tv.data.api.TmdbExternalIds
import com.arflix.tv.data.api.TmdbImage
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
import com.arflix.tv.data.model.Addon
import com.arflix.tv.data.model.CastMember
import com.arflix.tv.data.model.CatalogConfig
import com.arflix.tv.data.model.CatalogKind
import com.arflix.tv.data.model.CatalogSourceType
import com.arflix.tv.data.model.Category
import com.arflix.tv.data.model.CollectionGroupKind
import com.arflix.tv.data.model.CollectionSourceConfig
import com.arflix.tv.data.model.CollectionSourceKind
import com.arflix.tv.data.model.CollectionTileShape
import com.arflix.tv.data.model.Episode
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.model.PersonDetails
import com.arflix.tv.data.model.Review
import com.arflix.tv.data.model.SportsAddonCapabilities
import com.arflix.tv.util.CatalogUrlParser
import com.arflix.tv.util.Constants
import com.arflix.tv.util.ContentRating
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.sync.withPermit
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.arflix.tv.network.OkHttpProvider
import okhttp3.HttpUrl.Companion.toHttpUrl
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

data class PersonMediaSearchResult(
    val personId: Int,
    val name: String,
    val items: List<MediaItem>
)

data class MediaSearchResults(
    val items: List<MediaItem>,
    val people: List<PersonMediaSearchResult>
)

internal object HomeServerLibraryIdentity {
    fun stableNativeId(sourceRef: String, itemId: String): Int {
        return -("$sourceRef:$itemId".hashCode() and Int.MAX_VALUE).coerceAtLeast(1)
    }
}

/**
 * Repository for media data from TMDB
 * Cross-references with Trakt for watched status
 * Includes in-memory caching for performance
 */
@Singleton
class MediaRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val tmdbApi: TmdbApi,
    private val traktRepository: TraktRepository,
    private val traktApi: TraktApi,
    private val okHttpClient: OkHttpClient,
    private val streamRepository: StreamRepository,
    private val homeServerRepository: HomeServerRepository,
    private val simklListsRepository: com.arflix.tv.data.repository.simkl.SimklListsRepository? = null
) {

    data class CategoryPageResult(
        val items: List<MediaItem>,
        val hasMore: Boolean,
        val nextOffset: Int? = null,
        val totalCount: Int? = null
    )

    private val apiKey = Constants.TMDB_API_KEY
    private val gson = Gson()
    private val repositoryScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    // Lazy so constructing the repository (tests do, with mocks) doesn't touch disk.
    private val addonNative by lazy { AddonNativeCatalog(context, streamRepository, gson) }

    /** TMDB content language (e.g. "en-US", "fr-FR", "nl-NL"). */
    @Volatile
    var contentLanguage: String = "en-US"
        set(value) {
            val normalized = value.ifBlank { "en-US" }.replace("iw", "he").replace('_', '-')
            if (field == normalized) return
            field = normalized
            clearMediaCache()
        }

    // === IN-MEMORY CACHE FOR PERFORMANCE ===
    private data class CacheEntry<T>(val data: T, val timestamp: Long)
    private val CACHE_TTL_MS = 5 * 60 * 1000L // 5 minutes

    // Home categories cache - survives ViewModel recreation
    @Volatile var cachedHomeCategories: List<Category> = emptyList()
        private set
    @Volatile private var homeCategoriesFetchedAt = 0L
    private val HOME_CATEGORIES_CACHE_MS = 120_000L // 2 minutes

    fun clearMediaCache() {
        cachedHomeCategories = emptyList()
        homeCategoriesFetchedAt = 0L
        synchronized(detailsCache) { detailsCache.clear() }
        synchronized(castCache) { castCache.clear() }
        synchronized(similarCache) { similarCache.clear() }
        synchronized(logoCache) { logoCache.clear() }
        homeServerLogoRefCache.clear()
        synchronized(reviewsCache) { reviewsCache.clear() }
        synchronized(seasonEpisodesCache) { seasonEpisodesCache.clear() }
        collectionRefsCache.clear()
        tmdbCatalogRefsCache.clear()
    }

    private data class MediaCacheEntry(val item: MediaItem, val timestamp: Long, val complete: Boolean)
    private val detailsCache = ConcurrentHashMap<String, MediaCacheEntry>()
    private val collectionCardSlots = Semaphore(6)
    private val castCache = mutableMapOf<String, CacheEntry<List<CastMember>>>()
    private val similarCache = mutableMapOf<String, CacheEntry<List<MediaItem>>>()
    private val logoCache = ConcurrentHashMap<String, CacheEntry<String?>>()
    private val reviewsCache = mutableMapOf<String, CacheEntry<List<Review>>>()
    private val watchProvidersCache = mutableMapOf<String, CacheEntry<StreamingServicesResult?>>()
    private val seasonEpisodesCache = mutableMapOf<String, CacheEntry<List<Episode>>>()
    private val imdbRatingCache = ConcurrentHashMap<String, CacheEntry<String>>()
    private val imdbEpisodeRatingsCache = ConcurrentHashMap<String, CacheEntry<Map<Pair<Int, Int>, String>>>()
    private val imdbRatingsByIdCache = ConcurrentHashMap<String, CacheEntry<String>>()
    private val episodeImdbIdCache = ConcurrentHashMap<String, CacheEntry<String>>()
    private val imdbIdCache = ConcurrentHashMap<String, String>()
    private val addonImdbToTmdbCache = ConcurrentHashMap<String, CacheEntry<Pair<MediaType, Int>?>>()
    private val addonTitleToTmdbCache = ConcurrentHashMap<String, CacheEntry<Pair<MediaType, Int>?>>()
    private val homeServerLogoRefCache = ConcurrentHashMap<String, CacheEntry<Pair<MediaType, Int>?>>()
    private val collectionRefsCache = ConcurrentHashMap<String, CacheEntry<CollectionRefs>>()
    private val tmdbCatalogRefsCache = ConcurrentHashMap<String, CacheEntry<List<Pair<MediaType, Int>>>>()
    private val _episodeRatingsUpdated = MutableSharedFlow<Pair<Int, Int>>(extraBufferCapacity = 64)
    val episodeRatingsUpdated = _episodeRatingsUpdated.asSharedFlow()

    private fun <T> getFromCache(cache: Map<String, CacheEntry<T>>, key: String): T? {
        val entry = cache[key] ?: return null
        return if (System.currentTimeMillis() - entry.timestamp < CACHE_TTL_MS) entry.data else null
    }

    private fun detailsCacheKey(mediaType: MediaType, mediaId: Int): String {
        return if (mediaType == MediaType.MOVIE) "movie_$mediaId" else "tv_$mediaId"
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

    /**
     * Resolved refs of a collection. [complete] is true when no source would return more for a
     * larger request (it ran out, or hit its budget ceiling).
     */
    private data class CollectionRefs(val refs: List<Pair<MediaType, Int>>, val complete: Boolean)

    private data class CollectionSourceRefs(
        val refs: List<Pair<MediaType, Int>>,
        val failed: Boolean = false
    )

    private class CollectionSourceLoadException(
        val partialRefs: List<Pair<MediaType, Int>>,
        cause: Exception
    ) : Exception(cause)

    private fun getCollectionRefsCache(key: String): CollectionRefs? {
        val entry = collectionRefsCache[key] ?: return null
        return if (System.currentTimeMillis() - entry.timestamp < CACHE_TTL_MS) {
            entry.data
        } else {
            collectionRefsCache.remove(key)
            null
        }
    }

    private fun collectionRefsCacheKey(catalog: CatalogConfig): String {
        return buildString {
            append(contentLanguage)
            append('|')
            append(catalog.id)
            append('|')
            catalog.collectionSources.forEach { source ->
                append(source.kind.name)
                append(':')
                append(source.mediaType.orEmpty())
                append(':')
                append(source.addonId.orEmpty())
                append(':')
                append(source.addonCatalogType.orEmpty())
                append(':')
                append(source.addonCatalogId.orEmpty())
                append(':')
                append(source.addonGenre.orEmpty())
                append(':')
                append(source.tmdbGenreId ?: -1)
                append(':')
                append(source.tmdbPersonId ?: -1)
                append(':')
                append(source.tmdbCreditRole.orEmpty())
                append(':')
                append(source.tmdbCollectionId ?: -1)
                append(':')
                append(source.tmdbKeywordId ?: -1)
                append(':')
                append(source.tmdbWatchProviderId ?: -1)
                append(':')
                append(source.watchRegion.orEmpty())
                append(':')
                append(source.sortBy.orEmpty())
                append(':')
                append(source.mdblistSlug.orEmpty())
                append(':')
                append(source.curatedRefs?.joinToString(",").orEmpty())
                append(':')
                append(source.discoverParams?.entries?.joinToString(",") { "${it.key}=${it.value}" }.orEmpty())
                append(':')
                append(source.tmdbListId ?: -1)
                append(':')
                append(source.traktListId.orEmpty())
                append(';')
            }
        }
    }

    private suspend fun resolveCollectionCatalogRefs(
        catalog: CatalogConfig,
        requiredCount: Int,
        mediaType: MediaType? = null
    ): CollectionRefs {
        // Each source applies the tab filter before its limit. Keep tab caches separate so a
        // movie-only window cannot be mistaken for the complete contents of a mixed list.
        fun countOf(refs: List<Pair<MediaType, Int>>): Int =
            if (mediaType == null) refs.size else refs.count { it.first == mediaType }

        val cacheKey = "${collectionRefsCacheKey(catalog)}|${catalog.collectionGroup}|${catalog.collectionRailKey}|${mediaType?.name ?: "ALL"}"
        val cached = getCollectionRefsCache(cacheKey)
        if (cached != null && (cached.complete || countOf(cached.refs) >= requiredCount.coerceAtLeast(1))) {
            return cached
        }

        val targetCount = requiredCount.coerceAtLeast(1)
        // SERVICE and GENRE rails page through TMDB/addon catalogs on demand,
        // so let the per-source budget grow with the user's scroll position
        // instead of clamping at the default 72/96/120 ceiling. FRANCHISE and
        // other fixed groups keep the small cap.
        val unlimitedGroup = catalog.collectionGroup == CollectionGroupKind.SERVICE ||
            catalog.collectionGroup == CollectionGroupKind.GENRE ||
            catalog.collectionRailKey != null

        val budgetCeilings: List<Int?> = catalog.collectionSources.map { source ->
            if (unlimitedGroup) {
                null
            } else when (source.kind) {
                CollectionSourceKind.ADDON_CATALOG -> 120
                CollectionSourceKind.MDBLIST_PUBLIC -> 96
                else -> 72
            }
        }
        val sourceBudgets = catalog.collectionSources.mapIndexed { index, source ->
            if (unlimitedGroup) {
                // Start with one upstream page; grow on scroll instead of downloading
                // extra pages before the first eight cards can be displayed.
                ((targetCount + 19) / 20) * 20
            } else when (source.kind) {
                CollectionSourceKind.ADDON_CATALOG -> (targetCount + 12).coerceAtLeast(24)
                CollectionSourceKind.MDBLIST_PUBLIC -> (targetCount + 8).coerceAtLeast(24)
                else -> (targetCount + 8).coerceAtLeast(24)
            }.coerceAtMost(budgetCeilings[index] ?: Int.MAX_VALUE)
        }

        // Resolve all sources in parallel so a slow/failed source never blocks the
        // others — this alone fixes "empty" genre collections where one source 404s.
        val sourceResults = coroutineScope {
            catalog.collectionSources.indices.map { index ->
                async {
                    resolveCollectionSourceRefs(
                        catalog.collectionSources[index],
                        offset = 0,
                        limit = sourceBudgets[index],
                        mediaType = mediaType
                    )
                }
            }.map { it.await() }
        }
        val perSourceRefs = sourceResults.map { it.refs }

        val refs = LinkedHashSet<Pair<MediaType, Int>>()
        cached?.refs?.forEach { refs.add(it) }

        // For GENRE collections, interleave movie and series refs so the
        // first page always shows a mix rather than "all movies, then TV".
        if (catalog.collectionGroup == CollectionGroupKind.GENRE) {
            val movieQueue = ArrayDeque<Pair<MediaType, Int>>()
            val tvQueue = ArrayDeque<Pair<MediaType, Int>>()
            perSourceRefs.flatten().forEach { ref ->
                if (ref.first == MediaType.MOVIE) movieQueue.addLast(ref) else tvQueue.addLast(ref)
            }
            while ((movieQueue.isNotEmpty() || tvQueue.isNotEmpty()) && refs.size < targetCount) {
                if (movieQueue.isNotEmpty()) refs.add(movieQueue.removeFirst())
                if (tvQueue.isNotEmpty() && refs.size < targetCount) refs.add(tvQueue.removeFirst())
            }
            // Drain any remaining so pagination beyond the first page still has items.
            movieQueue.forEach { refs.add(it) }
            tvQueue.forEach { refs.add(it) }
        } else {
            perSourceRefs.forEach { sourceRefs ->
                sourceRefs.forEach { refs.add(it) }
            }
        }

        val complete = perSourceRefs.indices.all { index ->
            val ceiling = budgetCeilings[index]
            !sourceResults[index].failed && (perSourceRefs[index].size < sourceBudgets[index] ||
                (ceiling != null && sourceBudgets[index] >= ceiling))
        }
        val resolved = CollectionRefs(refs.toList(), complete)
        // Empty is a valid result. A failed page is not, even if earlier pages delivered items.
        if (sourceResults.none { it.failed }) {
            collectionRefsCache[cacheKey] = CacheEntry(resolved, System.currentTimeMillis())
        } else {
            collectionRefsCache.remove(cacheKey)
        }
        return resolved
    }

    fun getCachedItem(mediaType: MediaType, mediaId: Int): MediaItem? {
        return cachedMediaEntry(mediaType, mediaId)?.item ?: addonNative.card(mediaType, mediaId)
    }

    private fun cachedMediaEntry(mediaType: MediaType, mediaId: Int): MediaCacheEntry? =
        detailsCache[detailsCacheKey(mediaType, mediaId)]?.takeIf {
            System.currentTimeMillis() - it.timestamp < CACHE_TTL_MS
        }

    suspend fun getCachedItemFromDisk(mediaType: MediaType, mediaId: Int): MediaItem? =
        withContext(Dispatchers.IO) {
            peekItemFromDiskCache(mediaType, mediaId)
        }

    private fun peekItemFromDiskCache(mediaType: MediaType, mediaId: Int): MediaItem? = try {
            val cacheFiles = mutableListOf<java.io.File>()
            context.cacheDir.listFiles { _, name -> name.startsWith("home_categories_cache_") && name.endsWith(".json") }
                ?.let { cacheFiles.addAll(it) }
            context.filesDir.listFiles { _, name -> name.startsWith("home_continue_watching_") && name.endsWith(".json") }
                ?.let { cacheFiles.addAll(it) }

            for (file in cacheFiles) {
                if (!file.exists() || file.length() > 12_000_000L) continue
                val json = file.readText()
                if (json.isBlank()) continue
                if (!json.contains("\"id\":$mediaId") && !json.contains("\"id\": $mediaId")) continue

                val type = com.google.gson.reflect.TypeToken
                    .getParameterized(MutableList::class.java, Category::class.java)
                    .type
                val categories: List<Category>? = runCatching { gson.fromJson<List<Category>>(json, type) }.getOrNull()
                if (categories != null) {
                    for (cat in categories) {
                        for (item in cat.items) {
                            if (item.id == mediaId && item.mediaType == mediaType) {
                                cacheItem(item)
                                return item
                            }
                        }
                    }
                }

                val cwItems = decodeContinueWatchingCache(json, gson)
                if (cwItems != null) {
                    for (cw in cwItems) {
                        if (cw.id == mediaId && cw.mediaType == mediaType) {
                            val item = cw.toMediaItem()
                            cacheItem(item)
                            return item
                        }
                    }
                }
            }
            null
        } catch (_: Throwable) {
            null
        }

    fun getCachedFullItem(mediaType: MediaType, mediaId: Int): MediaItem? {
        return cachedMediaEntry(mediaType, mediaId)?.takeIf { it.complete }?.item
    }

    fun cacheImdbId(mediaType: MediaType, mediaId: Int, imdbId: String) {
        if (imdbId.isBlank()) return
        val cacheKey = detailsCacheKey(mediaType, mediaId)
        imdbIdCache[cacheKey] = imdbId
    }

    fun getCachedImdbId(mediaType: MediaType, mediaId: Int): String? {
        val cacheKey = detailsCacheKey(mediaType, mediaId)
        // A native addon item has no IMDb id; its addon's own id takes that place, which
        // routes stream requests (details, player, next episode) back to that addon.
        return imdbIdCache[cacheKey] ?: if (mediaId < 0) addonNative.streamId(mediaId) else null
    }

    /** True for a native addon item (see [AddonNativeCatalog]). */
    fun isAddonNative(mediaId: Int): Boolean = mediaId < 0 && addonNative.isNative(mediaId)

    /** An addon's live channel, which must be played as live and kept out of Continue Watching. */
    fun isAddonNativeLiveChannel(mediaId: Int): Boolean = mediaId < 0 && addonNative.isLiveChannel(mediaId)

    // Catalog rows show native items as registered cards: loading full addon metadata per
    // card would cost one request per item, and the details screen loads it anyway.
    private suspend fun catalogItemFor(type: MediaType, mediaId: Int): MediaItem? = when {
        mediaId < 0 -> addonNative.card(type, mediaId)
        type == MediaType.MOVIE -> getMovieDetails(mediaId)
        else -> getTvDetails(mediaId)
    }

    suspend fun getImdbRating(mediaType: MediaType, mediaId: Int, imdbId: String? = null): String? {
        // No IMDb rating exists under a negative stand-in id (native addon / home-server items).
        if (mediaId <= 0 && imdbId?.startsWith("tt", ignoreCase = true) != true) return null
        val cacheKey = detailsCacheKey(mediaType, mediaId)
        getFromCache(imdbRatingCache, cacheKey)?.let { return it }

        val resolvedImdbId = resolveImdbId(mediaType, mediaId, imdbId)

        val rating = resolvedImdbId
            ?.let { imdbIdValue ->
                fetchCinemetaImdbRating(mediaType, imdbIdValue)
                    ?: getAgregarrImdbRatings(listOf(imdbIdValue))[imdbIdValue]
            }
            ?.let { normalizeRating(it) }
            ?: return null

        imdbRatingCache[cacheKey] = CacheEntry(rating, System.currentTimeMillis())
        return rating
    }

    private suspend fun resolveImdbId(mediaType: MediaType, mediaId: Int, imdbId: String? = null): String? {
        val direct = imdbId
            ?.trim()
            ?.takeIf { it.startsWith("tt", ignoreCase = true) }
        if (!direct.isNullOrBlank()) {
            cacheImdbId(mediaType, mediaId, direct)
            return direct
        }
        return getCachedImdbId(mediaType, mediaId)
            ?: resolveExternalIds(mediaType, mediaId)?.imdbId?.also { cacheImdbId(mediaType, mediaId, it) }
    }

    private suspend fun resolveExternalIds(mediaType: MediaType, mediaId: Int): TmdbExternalIds? {
        return try {
            when (mediaType) {
                MediaType.MOVIE -> tmdbApi.getMovieExternalIds(mediaId, apiKey)
                MediaType.TV -> tmdbApi.getTvExternalIds(mediaId, apiKey)
            }
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            com.arflix.tv.util.AppLogger.recordException(e, mapOf("error_area" to "MediaRepository", "action" to "resolveExternalIds"))
            null
        }
    }

    private suspend fun fetchCinemetaImdbRating(mediaType: MediaType, imdbId: String): String? = withContext(Dispatchers.IO) {
        val typePath = if (mediaType == MediaType.TV) "series" else "movie"
        val request = Request.Builder()
            .url("https://v3-cinemeta.strem.io/meta/$typePath/$imdbId.json")
            .header("Accept", "application/json")
            .header("User-Agent", OkHttpProvider.userAgentOr("Mozilla/5.0 (Android TV; ARVIO)"))
            .build()

        try {
            okHttpClient.newCall(request).execute().use { response ->
                if (!response.isSuccessful) return@use null
                val body = response.body?.string().orEmpty()
                val meta = JSONObject(body).optJSONObject("meta") ?: return@use null
                parseCinemetaMetaRating(meta)
            }
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            null
        }
    }

    private fun parseCinemetaMetaRating(meta: JSONObject): String? {
        listOf(
            meta.optString("imdbRating"),
            meta.optString("rating")
        ).firstOrNull { it.isNotBlank() && !it.equals("N/A", ignoreCase = true) }?.let { return it }

        val links = meta.optJSONArray("links") ?: return null
        for (index in 0 until links.length()) {
            val link = links.optJSONObject(index) ?: continue
            if (link.optString("category").equals("imdb", ignoreCase = true)) {
                return link.optString("name").takeIf { it.isNotBlank() && !it.equals("N/A", ignoreCase = true) }
            }
        }
        return null
    }

    private suspend fun getSeasonEpisodeImdbRatings(
        tvId: Int,
        seasonNumber: Int,
        episodeNumbers: List<Int>,
        imdbId: String? = null
    ): Map<Pair<Int, Int>, String> {
        if (episodeNumbers.isEmpty()) return emptyMap()
        val result = getSeriesCinemetaEpisodeRatings(tvId, imdbId)
            .filterKeys { (season, episode) -> season == seasonNumber && episode in episodeNumbers }
            .toMutableMap()

        val missingEpisodeNumbers = episodeNumbers
            .distinct()
            .filter { episode -> result[seasonNumber to episode].isNullOrBlank() }
        if (missingEpisodeNumbers.isEmpty()) return result

        val episodeImdbIds = resolveEpisodeImdbIds(tvId, seasonNumber, missingEpisodeNumbers)
        if (episodeImdbIds.isEmpty()) return result

        val ratingsByImdbId = getAgregarrImdbRatings(episodeImdbIds.values.toList())
        episodeImdbIds.forEach { (episodeNumber, episodeImdbId) ->
            val rating = ratingsByImdbId[episodeImdbId]
            if (!rating.isNullOrBlank()) {
                result[seasonNumber to episodeNumber] = rating
            }
        }
        return result
    }

    private suspend fun getSeriesCinemetaEpisodeRatings(tvId: Int, imdbId: String? = null): Map<Pair<Int, Int>, String> {
        val resolvedImdbId = resolveImdbId(MediaType.TV, tvId, imdbId) ?: return emptyMap()
        val cacheKey = "series_$resolvedImdbId"
        getFromCache(imdbEpisodeRatingsCache, cacheKey)?.let { return it }

        val ratings = fetchCinemetaEpisodeRatings(resolvedImdbId)
        imdbEpisodeRatingsCache[cacheKey] = CacheEntry(ratings, System.currentTimeMillis())
        return ratings
    }

    private suspend fun resolveEpisodeImdbIds(
        tvId: Int,
        seasonNumber: Int,
        episodeNumbers: List<Int>
    ): Map<Int, String> = coroutineScope {
        val limiter = Semaphore(8)
        episodeNumbers.distinct().map { episodeNumber ->
            async(Dispatchers.IO) {
                val cacheKey = "tv_${tvId}_s${seasonNumber}_e$episodeNumber"
                getFromCache(episodeImdbIdCache, cacheKey)?.let { return@async episodeNumber to it }
                val episodeImdbId = limiter.withPermit {
                    runCatching {
                        tmdbApi.getTvEpisodeExternalIds(tvId, seasonNumber, episodeNumber, apiKey)
                            .imdbId
                            ?.trim()
                            ?.takeIf { it.startsWith("tt", ignoreCase = true) }
                    }.getOrNull()
                }
                if (!episodeImdbId.isNullOrBlank()) {
                    episodeImdbIdCache[cacheKey] = CacheEntry(episodeImdbId, System.currentTimeMillis())
                    episodeNumber to episodeImdbId
                } else {
                    null
                }
            }
        }.awaitAll().filterNotNull().toMap()
    }

    private suspend fun getAgregarrImdbRatings(imdbIds: List<String>): Map<String, String> = withContext(Dispatchers.IO) {
        val uniqueIds = imdbIds
            .map { it.trim() }
            .filter { it.startsWith("tt", ignoreCase = true) }
            .distinct()
        if (uniqueIds.isEmpty()) return@withContext emptyMap()

        val now = System.currentTimeMillis()
        val result = mutableMapOf<String, String>()
        val idsToFetch = uniqueIds.filter { imdbId ->
            val cached = getFromCache(imdbRatingsByIdCache, imdbId)
            if (!cached.isNullOrBlank()) {
                result[imdbId] = cached
                false
            } else {
                true
            }
        }
        if (idsToFetch.isEmpty()) return@withContext result

        idsToFetch.chunked(100).forEach { chunk ->
            val url = "https://api.agregarr.org/api/ratings".toHttpUrl().newBuilder().apply {
                chunk.forEach { addQueryParameter("id", it) }
            }.build()
            val request = Request.Builder()
                .url(url)
                .header("Accept", "application/json")
                .header("User-Agent", OkHttpProvider.userAgentOr("Mozilla/5.0 (Android TV; ARVIO)"))
                .build()

            val fetched = runCatching {
                okHttpClient.newCall(request).execute().use { response ->
                    if (!response.isSuccessful) return@use emptyMap<String, String>()
                    val body = response.body?.string().orEmpty()
                    val array = JSONArray(body)
                    buildMap {
                        for (index in 0 until array.length()) {
                            val item = array.optJSONObject(index) ?: continue
                            val imdbId = item.optString("imdbId").trim()
                            if (imdbId.isBlank() || item.isNull("rating")) continue
                            val rating = normalizeRating(item.optDouble("rating", 0.0).toString())
                            if (!rating.isNullOrBlank()) put(imdbId, rating)
                        }
                    }
                }
            }.getOrDefault(emptyMap())

            fetched.forEach { (imdbId, rating) ->
                imdbRatingsByIdCache[imdbId] = CacheEntry(rating, now)
                result[imdbId] = rating
            }
        }
        result
    }

    private suspend fun fetchCinemetaEpisodeRatings(imdbId: String): Map<Pair<Int, Int>, String> = withContext(Dispatchers.IO) {
        val request = Request.Builder()
            .url("https://v3-cinemeta.strem.io/meta/series/$imdbId.json")
            .header("Accept", "application/json")
            .header("User-Agent", OkHttpProvider.userAgentOr("Mozilla/5.0 (Android TV; ARVIO)"))
            .build()

        runCatching {
            okHttpClient.newCall(request).execute().use { response ->
                if (!response.isSuccessful) return@use emptyMap()
                val body = response.body?.string().orEmpty()
                val videos = JSONObject(body)
                    .optJSONObject("meta")
                    ?.optJSONArray("videos")
                    ?: return@use emptyMap()
                buildMap {
                    for (index in 0 until videos.length()) {
                        val video = videos.optJSONObject(index) ?: continue
                        val season = video.optInt("season", -1)
                        val episode = video.optInt("episode", -1)
                            .takeIf { it > 0 }
                            ?: video.optInt("number", -1)
                        val rating = normalizeRating(video.optString("rating")).orEmpty()
                        if (season >= 0 && episode > 0 && rating.isNotBlank()) {
                            put(season to episode, rating)
                        }
                    }
                }
            }
        }.getOrDefault(emptyMap())
    }

    fun cacheItem(item: MediaItem) {
        if (item.isAddonNative) addonNative.restore(item)
        val cacheKey = detailsCacheKey(item.mediaType, item.id)
        val now = System.currentTimeMillis()
        detailsCache.compute(cacheKey) { _, existing ->
            if (existing != null && existing.complete && now - existing.timestamp < CACHE_TTL_MS) existing
            else MediaCacheEntry(item, now, complete = false)
        }
    }

    private fun cacheFullDetailsItem(item: MediaItem) {
        val cacheKey = detailsCacheKey(item.mediaType, item.id)
        detailsCache[cacheKey] = MediaCacheEntry(item, System.currentTimeMillis(), complete = true)
    }

    private fun cacheItems(items: List<MediaItem>) {
        items.forEach { cacheItem(it) }
    }

    fun getDefaultCatalogConfigs(): List<CatalogConfig> = buildPreinstalledDefaults()

    companion object {
        const val STREAMING_COLLECTION_ADDON_URL = "https://pastebin.com/raw/P4gfd98n"

        /** Upper bound for a TMDB page used as a catalog row (discover pages are endless). */
        private const val TMDB_CATALOG_MAX_ITEMS = 200

        /**
         * Build the full preinstalled catalog list for a fresh profile:
         * top-level feeds (favorites, trending, mdblist-backed rows) plus the
         * collections rail (streaming services, franchises, genres).
         *
         * Called via `getDefaultCatalogConfigs()` to seed a fresh profile with
         * the bundled preinstalled catalogs. Kept in the companion object so
         * `PreinstalledServicesTest` can invoke it without constructing a full
         * MediaRepository (which would need every injected dependency).
         */
        internal fun buildPreinstalledDefaults(): List<CatalogConfig> {
            val topLevelCatalogs = listOf(
                CatalogConfig("trending_movies", "Trending in Movies", CatalogSourceType.MDBLIST, isPreinstalled = true, sourceUrl = "https://mdblist.com/lists/snoak/trending-movies", sourceRef = "mdblist:https://mdblist.com/lists/snoak/trending-movies"),
                CatalogConfig("trending_tv", "Trending in Shows", CatalogSourceType.MDBLIST, isPreinstalled = true, sourceUrl = "https://mdblist.com/lists/snoak/trakt-s-trending-shows", sourceRef = "mdblist:https://mdblist.com/lists/snoak/trakt-s-trending-shows"),
                CatalogConfig("trending_anime", "Trending in Anime", CatalogSourceType.MDBLIST, isPreinstalled = true, sourceUrl = "https://mdblist.com/lists/snoak/trending-anime-shows", sourceRef = "mdblist:https://mdblist.com/lists/snoak/trending-anime-shows"),
                CatalogConfig("favorite_tv", "Favorite TV", CatalogSourceType.PREINSTALLED, isPreinstalled = true),
                CatalogConfig("top10_movies_today", "Top 10 Movies Today", CatalogSourceType.MDBLIST, isPreinstalled = true, sourceUrl = "https://mdblist.com/lists/snoak/top-10-movies-of-the-day", sourceRef = "mdblist:https://mdblist.com/lists/snoak/top-10-movies-of-the-day"),
                CatalogConfig("top10_shows_today", "Top 10 Shows Today", CatalogSourceType.MDBLIST, isPreinstalled = true, sourceUrl = "https://mdblist.com/lists/snoak/top-10-shows-of-the-day", sourceRef = "mdblist:https://mdblist.com/lists/snoak/top-10-shows-of-the-day"),
                CatalogConfig("just_added", "Just Added", CatalogSourceType.MDBLIST, isPreinstalled = true, sourceUrl = "https://mdblist.com/lists/snoak/latest-movies-digital-release", sourceRef = "mdblist:https://mdblist.com/lists/snoak/latest-movies-digital-release"),
                CatalogConfig("top_movies_week", "Top Movies This Week", CatalogSourceType.MDBLIST, isPreinstalled = true, sourceUrl = "https://mdblist.com/lists/linaspurinis/top-watched-movies-of-the-week", sourceRef = "mdblist:https://mdblist.com/lists/linaspurinis/top-watched-movies-of-the-week"),
                CatalogConfig("new_kdramas", "New in K-Dramas", CatalogSourceType.MDBLIST, isPreinstalled = true, sourceUrl = "https://mdblist.com/lists/snoak/latest-kdrama-shows", sourceRef = "mdblist:https://mdblist.com/lists/snoak/latest-kdrama-shows"),
                CatalogConfig("coming_soon", "Coming Soon", CatalogSourceType.MDBLIST, isPreinstalled = true, sourceUrl = "https://mdblist.com/lists/snoak/upcoming-movies", sourceRef = "mdblist:https://mdblist.com/lists/snoak/upcoming-movies")
            )

            val collectionRails = CollectionTemplateManifest.railOrder.map { group ->
                CatalogConfig(
                    id = CollectionTemplateManifest.railCatalogId(group),
                    title = CollectionTemplateManifest.railTitle(group),
                    sourceType = CatalogSourceType.PREINSTALLED,
                    isPreinstalled = true,
                    kind = CatalogKind.COLLECTION_RAIL,
                    collectionGroup = group
                )
            }
            val templateCollections = CollectionTemplateManifest.entries.map { entry ->
                CatalogConfig(
                    id = entry.id,
                    title = entry.title,
                    sourceType = CatalogSourceType.PREINSTALLED,
                    isPreinstalled = true,
                    kind = CatalogKind.COLLECTION,
                    collectionGroup = entry.group,
                    collectionDescription = CollectionTemplateManifest.descriptionFor(entry),
                    collectionCoverImageUrl = entry.coverImageUrl,
                    collectionFocusGifUrl = entry.coverImageUrl,
                    collectionHeroImageUrl = entry.heroImageUrl,
                    collectionHeroVideoUrl = entry.heroVideoUrl,
                    collectionTileShape = entry.tileShape,
                    collectionHideTitle = entry.hideTitle,
                    collectionSources = entry.sources
                )
            }

            val pinnedLeadCatalogs = topLevelCatalogs.take(3)
            val trailingCatalogs = topLevelCatalogs.drop(3)
            return pinnedLeadCatalogs + collectionRails + templateCollections + trailingCatalogs
        }
    }

    /**
     * Fetch home screen categories
     * Uses improved filters for better quality results:
     * - Trending: Uses daily TMDB trending (updates every day)
     * - Anime: Uses "anime" keyword (210024) for accurate anime content
     * - Provider categories: wider recency window to keep full rows populated
     */
    suspend fun getHomeCategories(): List<Category> = coroutineScope {
        // Return cached categories if still fresh
        val now = System.currentTimeMillis()
        if (cachedHomeCategories.isNotEmpty() && now - homeCategoriesFetchedAt < HOME_CATEGORIES_CACHE_MS) {
            return@coroutineScope cachedHomeCategories
        }
        // First-launch resilience: on cold start the DNS resolver and TLS stack
        // may not be warm yet when HomeViewModel.init{} fires getHomeCategories().
        // If the first attempt returns nothing (all TMDB calls failed silently in
        // safeItems()), retry once after a short backoff so the home screen
        // actually populates on first launch instead of requiring a second app open.
        var result = getHomeCategoriesInternal()
        if (result.isEmpty()) {
            kotlinx.coroutines.delay(1_500L)
            result = getHomeCategoriesInternal()
            if (result.isEmpty()) {
                kotlinx.coroutines.delay(3_000L)
                result = getHomeCategoriesInternal()
            }
        }
        // Only cache non-empty results. Caching an empty list would cause
        // HOME_CATEGORIES_CACHE_MS of "stuck empty home" until the cache expires.
        if (result.isNotEmpty()) {
            cachedHomeCategories = result
            homeCategoriesFetchedAt = System.currentTimeMillis()
        }
        result
    }

    private suspend fun getHomeCategoriesInternal(): List<Category> = coroutineScope {
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

        // Provider-based per-service discover rows intentionally removed —
        // the Services collection-tile row already surfaces Netflix, Disney+,
        // Prime, Max, Apple TV+, Paramount+, Hulu etc. with curated art and
        // feeds from addon catalogs. Having duplicate rows below the tiles
        // was noise per user feedback.

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
                title = context.getString(R.string.trending_movies),
                items = safeItems({ trendingMovies.await() }, MediaType.MOVIE)
            ),
            Category(
                id = "trending_tv",
                title = context.getString(R.string.trending_series),
                items = safeItems({ trendingTv.await() }, MediaType.TV)
            ),
            Category(
                id = "trending_anime",
                title = context.getString(R.string.trending_anime),
                items = safeItems({ trendingAnime.await() }, MediaType.TV)
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

        val response = try {
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
                // trending_netflix / _disney / _prime / _hbo / _apple /
                // _paramount / _hulu / _peacock rows have been retired in
                // favor of the Services collection-tile row.
                else -> null
            }
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            com.arflix.tv.util.AppLogger.recordException(e, mapOf("error_area" to "MediaRepository", "action" to "loadHomeCategoryPage"))
            null
        } ?: return CategoryPageResult(emptyList(), hasMore = false)

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
        if (catalog.kind == CatalogKind.COLLECTION) {
            val page = loadCollectionCatalogPage(catalog = catalog, offset = 0, limit = maxItems)
            return@coroutineScope if (page.items.isEmpty()) null else Category(catalog.id, catalog.title, page.items)
        }
        val effectiveMaxItems = if (catalog.isTop10Catalog()) maxItems.coerceAtMost(10) else maxItems
        if (catalog.sourceType == CatalogSourceType.HOME_SERVER) {
            val page = loadHomeServerCatalogPage(catalog, offset = 0, limit = effectiveMaxItems)
            return@coroutineScope if (page.items.isEmpty()) null else Category(catalog.id, catalog.title, page.items)
        }
        val mediaRefs = when (catalog.sourceType) {
            CatalogSourceType.TRAKT -> loadTraktCatalogRefs(catalog.sourceUrl, catalog.sourceRef)
            CatalogSourceType.SIMKL -> loadSimklCatalogRefs(catalog, effectiveMaxItems)
            CatalogSourceType.MDBLIST -> loadMdblistCatalogRefs(catalog.sourceUrl, catalog.sourceRef)
            CatalogSourceType.TMDB -> loadTmdbCatalogRefs(catalog.sourceUrl, catalog.sourceRef)
            CatalogSourceType.ADDON -> loadAddonCatalogRefsPage(catalog, offset = 0, limit = effectiveMaxItems).refs
            CatalogSourceType.PREINSTALLED -> emptyList()
            CatalogSourceType.HOME_SERVER -> emptyList()
        }
        if (mediaRefs.isEmpty()) return@coroutineScope null

        val semaphore = Semaphore(6)
        val jobs = mediaRefs.distinct().take(effectiveMaxItems).map { (type, tmdbId) ->
            async {
                semaphore.withPermit {
                    runCatching { catalogItemFor(type, tmdbId) }.getOrNull()
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
        if (catalog.kind == CatalogKind.COLLECTION) {
            return@coroutineScope loadCollectionCatalogPage(catalog, offset, limit)
        }
        if (limit <= 0 || offset < 0) return@coroutineScope CategoryPageResult(emptyList(), hasMore = false)
        val rankedCatalogLimit = if (catalog.isTop10Catalog()) 10 else Int.MAX_VALUE
        if (offset >= rankedCatalogLimit) {
            return@coroutineScope CategoryPageResult(emptyList(), hasMore = false)
        }
        val effectiveLimit = limit.coerceAtMost(rankedCatalogLimit - offset)

        val pageRefs: List<Pair<MediaType, Int>>
        val hasMore: Boolean
        var sourceNextOffset: Int? = null
        if (catalog.sourceType == CatalogSourceType.HOME_SERVER) {
            return@coroutineScope loadHomeServerCatalogPage(catalog, offset, effectiveLimit)
        } else if (catalog.sourceType == CatalogSourceType.ADDON) {
            val page = loadAddonCatalogRefsPage(catalog, offset, effectiveLimit)
            pageRefs = page.refs
            sourceNextOffset = page.nextOffset
            hasMore = page.hasMore && page.nextOffset < rankedCatalogLimit
        } else {
            val mediaRefs = when (catalog.sourceType) {
                CatalogSourceType.TRAKT -> loadTraktCatalogRefs(catalog.sourceUrl, catalog.sourceRef)
                CatalogSourceType.SIMKL -> loadSimklCatalogRefs(catalog, offset + effectiveLimit + 1)
                CatalogSourceType.MDBLIST -> loadMdblistCatalogRefs(catalog.sourceUrl, catalog.sourceRef)
                CatalogSourceType.TMDB -> loadTmdbCatalogRefs(catalog.sourceUrl, catalog.sourceRef)
                CatalogSourceType.ADDON -> emptyList()
                CatalogSourceType.PREINSTALLED -> emptyList()
                CatalogSourceType.HOME_SERVER -> emptyList()
            }.distinct()

            if (mediaRefs.isEmpty()) return@coroutineScope CategoryPageResult(emptyList(), hasMore = false)

            val cappedRefs = mediaRefs.take(rankedCatalogLimit)
            pageRefs = cappedRefs.drop(offset).take(effectiveLimit)
            if (pageRefs.isEmpty()) {
                return@coroutineScope CategoryPageResult(emptyList(), hasMore = false)
            }
            hasMore = offset + pageRefs.size < cappedRefs.size
        }

        val semaphore = Semaphore(6)
        val jobs = pageRefs.map { (type, tmdbId) ->
            async {
                semaphore.withPermit {
                    runCatching { catalogItemFor(type, tmdbId) }.getOrNull()
                }
            }
        }
        val items = jobs.mapNotNull { it.await() }
        if (items.isNotEmpty()) {
            cacheItems(items)
        }
        CategoryPageResult(
            items = items,
            hasMore = hasMore,
            nextOffset = sourceNextOffset ?: (offset + pageRefs.size)
        )
    }

    private suspend fun loadHomeServerCatalogPage(
        catalog: CatalogConfig,
        offset: Int,
        limit: Int
    ): CategoryPageResult = coroutineScope {
        val page = homeServerRepository.loadCatalogItems(
            sourceRef = catalog.sourceRef,
            offset = offset,
            limit = limit
        )
        if (page.items.isEmpty()) {
            return@coroutineScope CategoryPageResult(emptyList(), hasMore = false)
        }

        val semaphore = Semaphore(4)
        val orderedItems = page.items.map { serverItem ->
            async {
                semaphore.withPermit {
                    runCatching { resolveHomeServerCatalogItem(serverItem) }.getOrNull()
                }
            }
        }.mapNotNull { it.await() }

        if (orderedItems.isNotEmpty()) {
            cacheItems(orderedItems)
        }
        CategoryPageResult(
            items = orderedItems.distinctBy { "${it.mediaType.name}_${it.id}" },
            hasMore = page.hasMore,
            nextOffset = page.nextOffset
        )
    }

    /**
     * Native-first home-server browsing. Unlike home-screen catalog hydration,
     * this does not wait for one TMDB details request per card. Provider artwork
     * and metadata render immediately while a real TMDB id is retained whenever
     * the server supplies one.
     */
    suspend fun loadHomeServerLibraryPage(
        sourceRef: String,
        offset: Int,
        limit: Int,
        sort: HomeServerLibrarySort = HomeServerLibrarySort.RECENTLY_ADDED,
        mediaType: MediaType? = null,
        searchQuery: String = ""
    ): CategoryPageResult {
        val page = homeServerRepository.loadCatalogItems(
            sourceRef = sourceRef,
            offset = offset,
            limit = limit,
            sort = sort,
            mediaType = mediaType,
            searchQuery = searchQuery,
            propagateErrors = true
        )
        val items = page.items.map { serverItem ->
            val tmdbId = serverItem.providerIds["tmdb"]?.toIntOrNull()?.takeIf { it > 0 }
            val stableNativeId = HomeServerLibraryIdentity.stableNativeId(serverItem.sourceRef, serverItem.id)
            MediaItem(
                id = tmdbId ?: stableNativeId,
                title = serverItem.title,
                subtitle = serverItem.providerName,
                overview = serverItem.overview,
                year = serverItem.year?.toString().orEmpty(),
                rating = serverItem.rating?.let { String.format(Locale.US, "%.1f", it) }.orEmpty(),
                tmdbRating = serverItem.rating?.let { String.format(Locale.US, "%.1f", it) }.orEmpty(),
                mediaType = serverItem.mediaType,
                image = serverItem.imageUrl,
                backdrop = serverItem.backdropUrl,
                addedAt = serverItem.addedAt,
                isHomeServer = true,
                homeServerItemId = serverItem.id,
                homeServerSourceRef = serverItem.sourceRef,
                homeServerProvider = serverItem.providerName,
                homeServerImdbId = serverItem.providerIds["imdb"]
            )
        }
        cacheItems(items)
        return CategoryPageResult(items = items, hasMore = page.hasMore, nextOffset = page.nextOffset, totalCount = page.totalCount)
    }

    private suspend fun resolveHomeServerCatalogItem(item: HomeServerCatalogItem): MediaItem? {
        val providers = item.providerIds.mapKeys { it.key.lowercase(Locale.US) }
        providers["tmdb"]?.toIntOrNull()?.let { tmdbId ->
            return runCatching {
                when (item.mediaType) {
                    MediaType.MOVIE -> getMovieDetails(tmdbId)
                    MediaType.TV -> getTvDetails(tmdbId)
                }
            }.getOrNull()
        }
        providers["imdb"]?.takeIf { it.startsWith("tt", ignoreCase = true) }?.let { imdbId ->
            resolveImdbToTmdbRef(imdbId, item.mediaType)?.let { (type, tmdbId) ->
                return runCatching {
                    when (type) {
                        MediaType.MOVIE -> getMovieDetails(tmdbId)
                        MediaType.TV -> getTvDetails(tmdbId)
                    }
                }.getOrNull()
            }
        }
        return resolveHomeServerCatalogItemByTitle(item)
    }

    private suspend fun resolveHomeServerCatalogItemByTitle(item: HomeServerCatalogItem): MediaItem? {
        val query = item.title.trim()
        if (query.isBlank()) return null
        val response = runCatching {
            when (item.mediaType) {
                MediaType.MOVIE -> tmdbApi.searchMovies(
                    apiKey = apiKey,
                    query = query,
                    language = contentLanguage,
                    primaryReleaseYear = item.year,
                    year = item.year
                )
                MediaType.TV -> tmdbApi.searchTv(
                    apiKey = apiKey,
                    query = query,
                    language = contentLanguage,
                    firstAirDateYear = item.year
                )
            }
        }.getOrNull() ?: return null

        val requestedTitle = HomeServerMatcher.normalizeTitle(query)
        val best = response.results
            .filter { result ->
                val resultType = when (result.mediaType) {
                    "movie" -> MediaType.MOVIE
                    "tv" -> MediaType.TV
                    else -> item.mediaType
                }
                resultType == item.mediaType
            }
            .map { result ->
                val title = result.title ?: result.name ?: result.originalTitle ?: result.originalName.orEmpty()
                val normalized = HomeServerMatcher.normalizeTitle(title)
                val dateYear = (result.releaseDate ?: result.firstAirDate).orEmpty().take(4).toIntOrNull()
                val titleScore = when {
                    requestedTitle.isNotBlank() && requestedTitle == normalized -> 100
                    requestedTitle.isNotBlank() && (requestedTitle in normalized || normalized in requestedTitle) -> 45
                    else -> 0
                }
                val yearScore = when {
                    item.year == null || dateYear == null -> 0
                    item.year == dateYear -> 30
                    kotlin.math.abs(item.year - dateYear) <= 1 -> 12
                    else -> -40
                }
                result to (titleScore + yearScore + result.popularity.toInt().coerceAtMost(30))
            }
            .maxByOrNull { (_, score) -> score }
            ?.takeIf { (_, score) -> score >= 55 }
            ?.first
            ?: return null

        return runCatching {
            when (item.mediaType) {
                MediaType.MOVIE -> getMovieDetails(best.id)
                MediaType.TV -> getTvDetails(best.id)
            }
        }.getOrNull()
    }

    /**
     * One page of a collection. With [mediaType] set, only refs of that type are paged, and
     * [offset] / the returned `nextOffset` count positions within that filtered list.
     */
    suspend fun loadCollectionCatalogPage(
        catalog: CatalogConfig,
        offset: Int,
        limit: Int,
        mediaType: MediaType? = null,
        onItemsAvailable: ((List<MediaItem>) -> Unit)? = null
    ): CategoryPageResult = coroutineScope {
        if (catalog.collectionSources.isEmpty() || limit <= 0 || offset < 0) {
            return@coroutineScope CategoryPageResult(emptyList(), hasMore = false)
        }
        val requestLanguage = contentLanguage

        val resolved = resolveCollectionCatalogRefs(
            catalog = catalog,
            requiredCount = (offset + limit).coerceAtLeast(limit),
            mediaType = mediaType
        )
        if (requestLanguage != contentLanguage) throw CancellationException("Collection language changed")
        val refs = if (mediaType == null) resolved.refs else resolved.refs.filter { it.first == mediaType }
        if (refs.isEmpty()) return@coroutineScope CategoryPageResult(emptyList(), hasMore = false)

        val pageRefs = refs.drop(offset).take(limit)
        val itemsByRef = LinkedHashMap<Pair<MediaType, Int>, MediaItem>()
        val missingRefs = mutableListOf<Pair<MediaType, Int>>()
        val finishedRefs = mutableSetOf<Pair<MediaType, Int>>()
        val progressMutex = Mutex()
        var publishedCount = 0
        // Publish only the resolved prefix: slow cards must not reorder focused TV items.
        fun publishAvailable() {
            val available = pageRefs.takeWhile { it in finishedRefs }.mapNotNull { itemsByRef[it] }
            if (available.size > publishedCount) {
                publishedCount = available.size
                onItemsAvailable?.invoke(available)
            }
        }
        pageRefs.forEach { (type, tmdbId) ->
            // Native addon items have no TMDB record to fall back on; their card is registered.
            val cachedItem = getCachedItem(type, tmdbId)
                ?: if (tmdbId < 0) addonNative.card(type, tmdbId) else null
            if (cachedItem != null) {
                itemsByRef[type to tmdbId] = cachedItem
                finishedRefs += type to tmdbId
            } else {
                missingRefs += (type to tmdbId)
            }
        }
        publishAvailable()
        // Lists/discover usually provided the cards already. ID-only sources need just
        // one metadata request, never external IDs or ratings on the first-render path.
        missingRefs.map { (type, tmdbId) ->
            async {
                val item = collectionCardSlots.withPermit {
                    try {
                        when (type) {
                            MediaType.MOVIE -> tmdbApi.getMovieDetails(tmdbId, apiKey, language = requestLanguage)
                                .let { it.toMediaItem().copy(contentRating = ContentRating.forMovie(it.releaseDates, requestLanguage)) }
                            MediaType.TV -> tmdbApi.getTvDetails(tmdbId, apiKey, language = requestLanguage)
                                .let { it.toMediaItem().copy(contentRating = ContentRating.forTv(it.contentRatings, requestLanguage)) }
                        }
                    } catch (e: CancellationException) {
                        throw e
                    } catch (_: Exception) {
                        null
                    }
                }
                progressMutex.withLock {
                    if (requestLanguage != contentLanguage) throw CancellationException("Collection language changed")
                    if (item != null) {
                        // This fallback fetched complete TMDB details; only ratings remain.
                        synchronized(detailsCache) {
                            if (requestLanguage == contentLanguage) cacheFullDetailsItem(item)
                        }
                        itemsByRef[type to tmdbId] = item
                    }
                    finishedRefs += type to tmdbId
                    publishAvailable()
                }
            }
        }.awaitAll()
        val items = pageRefs.mapNotNull { itemsByRef[it] }
        // Every successful card was cached at its source or lightweight detail boundary.
        // nextOffset counts consumed refs, not returned items: a failed lookup drops an item, and
        // counting items would make the next page start inside this one.
        // Reaching the end of a fetched window is not the end of the source, including
        // callers without a movie/series filter. Grow that window on the next page.
        val hasMore = offset + pageRefs.size < refs.size ||
            (!resolved.complete && pageRefs.isNotEmpty())
        CategoryPageResult(
            items = items,
            hasMore = hasMore,
            nextOffset = offset + pageRefs.size
        )
    }

    /**
     * Add-on catalog sources of [catalog] whose add-on is not installed or is disabled. Those
     * sources silently load nothing, so the UI can name them instead of showing an empty page.
     */
    suspend fun missingCollectionAddons(catalog: CatalogConfig): List<String> {
        return catalog.collectionSources
            .filter { it.kind == CollectionSourceKind.ADDON_CATALOG }
            .filter { source ->
                val catalogType = source.addonCatalogType?.trim().orEmpty()
                val catalogId = source.addonCatalogId?.trim().orEmpty()
                catalogType.isNotBlank() && catalogId.isNotBlank() &&
                    streamRepository.findInstalledAddonIdForCatalog(
                        catalogType = catalogType,
                        catalogId = catalogId,
                        preferredAddonId = source.addonId
                    ) == null
            }
            .map { source ->
                source.addonId?.trim()?.takeIf { it.isNotBlank() }
                    ?: "${source.addonCatalogType?.trim()}/${source.addonCatalogId?.trim()}"
            }
            .distinct()
    }

    private suspend fun resolveCollectionSourceRefs(
        source: CollectionSourceConfig,
        offset: Int,
        limit: Int,
        mediaType: MediaType?
    ): CollectionSourceRefs {
        // Defense in depth: every source-kind resolver must be wrapped so a
        // transient TMDB 404 / network error never propagates out of a
        // collection detail load and crashes the app. Collections from
        // third-party addons, keyword queries on unusual IDs, and region-gated
        // watch-provider calls all return HTTP errors sometimes — the right
        // UX is an empty row, not a force-close.
        return try {
            val refs = when (source.kind) {
                CollectionSourceKind.ADDON_CATALOG -> loadCollectionAddonRefs(source, offset, limit)
                CollectionSourceKind.TMDB_GENRE -> loadCollectionGenreRefs(source, limit)
                CollectionSourceKind.TMDB_PERSON -> loadCollectionPersonRefs(source, limit)
                CollectionSourceKind.TMDB_COLLECTION -> loadCollectionTmdbCollectionRefs(source, limit)
                CollectionSourceKind.TMDB_KEYWORD -> loadCollectionKeywordRefs(
                    if (mediaType != null && source.mediaType.isNullOrBlank()) {
                        source.copy(mediaType = if (mediaType == MediaType.TV) "tv" else "movie")
                    } else source,
                    limit
                )
                CollectionSourceKind.TMDB_WATCH_PROVIDER -> loadCollectionWatchProviderRefs(source, limit)
                CollectionSourceKind.CURATED_IDS -> loadCollectionCuratedRefs(source, limit, mediaType)
                CollectionSourceKind.MDBLIST_PUBLIC -> loadCollectionMdblistPublicRefs(source, limit, mediaType)
                CollectionSourceKind.TMDB_DISCOVER -> loadCollectionDiscoverRefs(source, limit)
                CollectionSourceKind.TMDB_LIST -> loadCollectionTmdbListRefs(source, limit, mediaType)
                CollectionSourceKind.TRAKT_LIST -> source.traktListId?.takeIf { it.isNotBlank() }?.let { listId ->
                    loadTraktCatalogRefs(sourceUrl = "https://trakt.tv/lists/$listId")
                        .filter { mediaType == null || it.first == mediaType }.take(limit)
                }.orEmpty()
            }
            CollectionSourceRefs(refs.filter { mediaType == null || it.first == mediaType })
        } catch (e: kotlinx.coroutines.CancellationException) {
            throw e
        } catch (e: CollectionSourceLoadException) {
            CollectionSourceRefs(e.partialRefs.filter { mediaType == null || it.first == mediaType }, failed = true)
        } catch (e: Exception) {
            CollectionSourceRefs(emptyList(), failed = true)
        }
    }

    /**
     * Fetch an mdblist list via its anonymous JSON endpoint
     * (`mdblist.com/lists/{slug}/json`). Each item carries its TMDB id in
     * `id` and the media type in `mediatype` ("movie" or "show"). We strip
     * anything else and hand back TMDB refs that compose with curated and
     * TMDB-collection sources.
     */
    private suspend fun loadCollectionMdblistPublicRefs(
        source: CollectionSourceConfig,
        limit: Int,
        mediaType: MediaType?
    ): List<Pair<MediaType, Int>> {
        val slug = source.mdblistSlug?.trim()?.trim('/').orEmpty()
        if (slug.isBlank()) return emptyList()
        val body = withContext(Dispatchers.IO) {
            fetchUrl("https://mdblist.com/lists/$slug/json")
        } ?: throw java.io.IOException("Collection list could not be loaded")
        val array = org.json.JSONArray(body)
        val refs = mutableListOf<Pair<MediaType, Int>>()
        for (i in 0 until array.length()) {
            val obj = array.optJSONObject(i) ?: continue
            val id = obj.optInt("id", -1).takeIf { it > 0 } ?: continue
            val type = when (obj.optString("mediatype").lowercase(Locale.US)) {
                "movie" -> MediaType.MOVIE
                "show", "series", "tv" -> MediaType.TV
                else -> continue
            }
            if (mediaType != null && type != mediaType) continue
            refs.add(type to id)
            if (refs.size >= limit) break
        }
        return refs
    }

    /**
     * Curated list of (MediaType, id) pairs. Used for franchises where timeline
     * order matters (Star Wars) or where blending movies + TV shows by release
     * date would break the intended sequence. Items are returned in the exact
     * order the config specifies.
     */
    private fun loadCollectionCuratedRefs(
        source: CollectionSourceConfig,
        limit: Int,
        mediaType: MediaType?
    ): List<Pair<MediaType, Int>> {
        val raw = source.curatedRefs ?: return emptyList()
        return raw.mapNotNull { entry ->
            val parts = entry.split(":", limit = 2)
            if (parts.size != 2) return@mapNotNull null
            val type = when (parts[0].lowercase(Locale.US)) {
                "movie" -> MediaType.MOVIE
                "tv", "series", "show" -> MediaType.TV
                else -> return@mapNotNull null
            }
            val id = parts[1].toIntOrNull() ?: return@mapNotNull null
            type to id
        }.filter { mediaType == null || it.first == mediaType }.take(limit)
    }

    /**
     * TMDB `/collection/{id}` returns the canonical list of films in a franchise
     * (Harry Potter = 1241, LOTR = 119, etc.). We keep sort-by-release-date so
     * franchise chronology is preserved. This is a movies-only source.
     */
    /** Generic TMDB discover for imported collections (raw TMDB query params). */
    private suspend fun loadCollectionDiscoverRefs(
        source: CollectionSourceConfig,
        limit: Int
    ): List<Pair<MediaType, Int>> {
        val isTv = when (source.mediaType?.lowercase(Locale.US)) {
            "series", "tv", "show" -> true
            else -> false
        }
        val params = source.discoverParams.orEmpty()
        // Nuvio stores one date sort for both media types; TMDB names them differently.
        val sortBy = (source.sortBy ?: "popularity.desc").let { sort ->
            when {
                isTv && sort.startsWith("primary_release_date") -> sort.replace("primary_release_date", "first_air_date")
                !isTv && sort.startsWith("first_air_date") -> sort.replace("first_air_date", "primary_release_date")
                else -> sort
            }
        }
        return loadPagedTmdbDiscoverRefs(
            mediaType = if (isTv) MediaType.TV else MediaType.MOVIE,
            limit = limit
        ) { page ->
            tmdbApi.discoverWithParams(
                mediaType = if (isTv) "tv" else "movie",
                apiKey = apiKey,
                params = params,
                sortBy = sortBy,
                language = contentLanguage,
                page = page
            )
        }
    }

    /** Public TMDB list, kept in list order; mixed movie/TV entries. */
    private suspend fun loadCollectionTmdbListRefs(
        source: CollectionSourceConfig,
        limit: Int,
        mediaType: MediaType?
    ): List<Pair<MediaType, Int>> {
        val requestLanguage = contentLanguage
        val listId = source.tmdbListId ?: return emptyList()
        val refs = LinkedHashSet<Pair<MediaType, Int>>()
        var page = 1
        var totalPages = 1
        while (refs.size < limit && page <= totalPages) {
            val response = try {
                tmdbApi.getPublicList(listId, apiKey, language = requestLanguage, page = page)
            } catch (e: kotlinx.coroutines.CancellationException) {
                throw e
            } catch (e: Exception) {
                throw CollectionSourceLoadException(refs.toList(), e)
            }
            response.items.forEach { item ->
                if (item.id <= 0) return@forEach
                val type = if (item.mediaType.equals("tv", ignoreCase = true)) MediaType.TV else MediaType.MOVIE
                if (mediaType == null || type == mediaType) {
                    if (refs.size < limit) cacheCollectionPreview(item.asMediaItem(), type, requestLanguage)
                    refs.add(type to item.id)
                }
            }
            totalPages = response.totalPages.coerceAtLeast(1)
            if (response.items.isEmpty()) break
            page += 1
        }
        return refs.take(limit)
    }

    private suspend fun loadCollectionTmdbCollectionRefs(
        source: CollectionSourceConfig,
        limit: Int
    ): List<Pair<MediaType, Int>> {
        val requestLanguage = contentLanguage
        val id = source.tmdbCollectionId ?: return emptyList()
        val response = tmdbApi.getTmdbCollection(id, apiKey, language = requestLanguage)
        return response.parts
            .sortedBy { it.releaseDate.orEmpty() }
            .take(limit)
            .onEach { cacheCollectionPreview(it, MediaType.MOVIE, requestLanguage) }
            .map { MediaType.MOVIE to it.id }
    }

    /**
     * Keyword-based franchise / studio discovery (Pixar, DreamWorks, broader
     * cinematic universes not modelled as a TMDB collection). Runs movies and
     * series queries in parallel when no `mediaType` is specified so a single
     * source can feed both tabs of the detail screen.
     */
    private suspend fun loadCollectionKeywordRefs(
        source: CollectionSourceConfig,
        limit: Int
    ): List<Pair<MediaType, Int>> = coroutineScope {
        val keyword = source.tmdbKeywordId?.toString() ?: return@coroutineScope emptyList()
        val sortBy = source.sortBy ?: "popularity.desc"
        val mt = source.mediaType?.lowercase(Locale.US)
        when (mt) {
            "movie" -> loadPagedTmdbDiscoverRefs(
                mediaType = MediaType.MOVIE,
                limit = limit
            ) { page ->
                tmdbApi.discoverMovies(
                    apiKey,
                    keywords = keyword,
                    sortBy = sortBy,
                    language = contentLanguage,
                    page = page
                )
            }
            "series", "tv", "show" -> loadPagedTmdbDiscoverRefs(
                mediaType = MediaType.TV,
                limit = limit
            ) { page ->
                tmdbApi.discoverTv(
                    apiKey,
                    keywords = keyword,
                    sortBy = sortBy,
                    language = contentLanguage,
                    page = page
                )
            }
            else -> {
                val moviesJob = async {
                    loadPagedTmdbDiscoverRefs(
                        mediaType = MediaType.MOVIE,
                        limit = limit
                    ) { page ->
                        tmdbApi.discoverMovies(
                            apiKey,
                            keywords = keyword,
                            sortBy = sortBy,
                            language = contentLanguage,
                            page = page
                        )
                    }
                }
                val tvJob = async {
                    loadPagedTmdbDiscoverRefs(
                        mediaType = MediaType.TV,
                        limit = limit
                    ) { page ->
                        tmdbApi.discoverTv(
                            apiKey,
                            keywords = keyword,
                            sortBy = sortBy,
                            language = contentLanguage,
                            page = page
                        )
                    }
                }
                (moviesJob.await() + tvJob.await()).take(limit)
            }
        }
    }

    /**
     * Streaming-service trending via `with_watch_providers`. Used for services
     * that don't have a dedicated addon catalog (Apple TV+, Paramount+, Hulu,
     * Peacock). Region defaults to US if not provided — TMDB requires a region
     * for watch-provider queries.
     */
    private suspend fun loadCollectionWatchProviderRefs(
        source: CollectionSourceConfig,
        limit: Int
    ): List<Pair<MediaType, Int>> {
        val providerId = source.tmdbWatchProviderId ?: return emptyList()
        val region = source.watchRegion?.takeIf { it.isNotBlank() } ?: "US"
        val sortBy = source.sortBy ?: "popularity.desc"
        return when (source.mediaType?.lowercase(Locale.US)) {
            "movie" -> loadPagedTmdbDiscoverRefs(
                mediaType = MediaType.MOVIE,
                limit = limit
            ) { page ->
                tmdbApi.discoverMovies(
                    apiKey,
                    watchProviders = providerId,
                    watchRegion = region,
                    sortBy = sortBy,
                    language = contentLanguage,
                    page = page
                )
            }
            "series", "tv", "show" -> loadPagedTmdbDiscoverRefs(
                mediaType = MediaType.TV,
                limit = limit
            ) { page ->
                tmdbApi.discoverTv(
                    apiKey,
                    watchProviders = providerId,
                    watchRegion = region,
                    sortBy = sortBy,
                    language = contentLanguage,
                    page = page
                )
            }
            else -> emptyList()
        }
    }

    private suspend fun loadCollectionAddonRefs(
        source: CollectionSourceConfig,
        offset: Int,
        limit: Int
    ): List<Pair<MediaType, Int>> = coroutineScope {
        val catalogType = source.addonCatalogType?.trim().orEmpty()
        val catalogId = source.addonCatalogId?.trim().orEmpty()
        if (catalogType.isBlank() || catalogId.isBlank()) return@coroutineScope emptyList()
        val addonId = streamRepository.findInstalledAddonIdForCatalog(
            catalogType = catalogType,
            catalogId = catalogId,
            preferredAddonId = source.addonId
        ) ?: throw java.io.IOException("Collection add-on is unavailable")

        loadPagedAddonCollectionRefs(
            descriptor = AddonCatalogDescriptor(
                addonId = addonId,
                catalogType = catalogType,
                catalogId = catalogId
            ),
            offset = offset,
            limit = limit,
            genre = source.addonGenre
        )
    }

    private suspend fun loadCollectionGenreRefs(
        source: CollectionSourceConfig,
        limit: Int
    ): List<Pair<MediaType, Int>> {
        val genreId = source.tmdbGenreId ?: return emptyList()
        val sortBy = source.sortBy ?: "popularity.desc"
        return when (source.mediaType?.lowercase(Locale.US)) {
            "movie" -> loadPagedTmdbDiscoverRefs(
                mediaType = MediaType.MOVIE,
                limit = limit
            ) { page ->
                tmdbApi.discoverMovies(
                    apiKey,
                    genres = genreId.toString(),
                    sortBy = sortBy,
                    language = contentLanguage,
                    page = page
                )
            }
            "series", "tv", "show" -> loadPagedTmdbDiscoverRefs(
                mediaType = MediaType.TV,
                limit = limit
            ) { page ->
                tmdbApi.discoverTv(
                    apiKey,
                    genres = genreId.toString(),
                    sortBy = sortBy,
                    language = contentLanguage,
                    page = page
                )
            }
            else -> emptyList()
        }
    }

    private suspend fun loadCollectionPersonRefs(
        source: CollectionSourceConfig,
        limit: Int
    ): List<Pair<MediaType, Int>> {
        val requestLanguage = contentLanguage
        val personId = source.tmdbPersonId ?: return emptyList()
        val sortBy = source.sortBy ?: "popularity.desc"
        if (source.tmdbCreditRole != null) {
            val credits = tmdbApi.getPersonDetails(personId, apiKey, language = requestLanguage).combinedCredits
                ?: return emptyList()
            val items = if (source.tmdbCreditRole == "Director") credits.crew.filter { it.job == "Director" } else credits.cast
            val type = if (source.mediaType in setOf("tv", "series", "show")) MediaType.TV else MediaType.MOVIE
            val matching = items.filter { it.id > 0 && !it.adult && it.mediaType == if (type == MediaType.TV) "tv" else "movie" }.distinctBy { it.id }
            val sorted = when (sortBy.substringBefore('.')) {
                "primary_release_date", "first_air_date", "release_date" -> matching.sortedBy { it.releaseDate ?: it.firstAirDate.orEmpty() }
                "vote_average" -> matching.sortedBy { it.voteAverage }
                "vote_count" -> matching.sortedBy { it.voteCount }
                "title", "original_title" -> matching.sortedBy { it.title ?: it.name.orEmpty() }
                else -> matching.sortedBy { it.popularity }
            }
            return (if (sortBy.endsWith(".asc")) sorted else sorted.reversed()).take(limit)
                .onEach { cacheCollectionPreview(it, type, requestLanguage) }
                .map { type to it.id }
        }
        return when (source.mediaType?.lowercase(Locale.US)) {
            "movie" -> loadPagedTmdbDiscoverRefs(
                mediaType = MediaType.MOVIE,
                limit = limit
            ) { page ->
                tmdbApi.discoverMovies(
                    apiKey,
                    crew = personId.toString(),
                    sortBy = sortBy,
                    language = contentLanguage,
                    page = page
                )
            }
            "series", "tv", "show" -> loadPagedTmdbDiscoverRefs(
                mediaType = MediaType.TV,
                limit = limit
            ) { page ->
                tmdbApi.discoverTv(
                    apiKey,
                    people = personId.toString(),
                    sortBy = sortBy,
                    language = contentLanguage,
                    page = page
                )
            }
            else -> emptyList()
        }
    }

    private suspend fun loadPagedTmdbDiscoverRefs(
        mediaType: MediaType,
        limit: Int,
        fetchPage: suspend (Int) -> TmdbListResponse
    ): List<Pair<MediaType, Int>> {
        val requestLanguage = contentLanguage
        if (limit <= 0) return emptyList()
        val refs = LinkedHashSet<Pair<MediaType, Int>>()
        var page = 1
        var totalPages = 1
        while (refs.size < limit && page <= totalPages) {
            val response = try {
                fetchPage(page)
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                throw CollectionSourceLoadException(refs.toList(), e)
            }
            response.results.forEach {
                cacheCollectionPreview(it, mediaType, requestLanguage)
                refs.add(mediaType to it.id)
            }
            totalPages = response.totalPages.coerceAtLeast(1)
            if (response.results.isEmpty()) break
            page += 1
        }
        return refs.take(limit)
    }

    private fun cacheCollectionPreview(item: TmdbMediaItem, type: MediaType, requestLanguage: String) {
        // Some list providers expose IDs only. Keep their lightweight detail fallback.
        if (item.id <= 0 || listOf(item.title, item.name, item.originalTitle, item.originalName).all { it.isNullOrBlank() }) return
        synchronized(detailsCache) {
            if (requestLanguage != contentLanguage || getCachedItem(type, item.id) != null) return
            cacheItem(item.toMediaItem(type))
        }
    }

    private suspend fun loadPagedAddonCollectionRefs(
        descriptor: AddonCatalogDescriptor,
        offset: Int,
        limit: Int,
        genre: String? = null
    ): List<Pair<MediaType, Int>> {
        if (limit <= 0) return emptyList()
        val accumulated = LinkedHashSet<Pair<MediaType, Int>>()
        var probeOffset = offset.coerceAtLeast(0)
        var probes = 0
        val maxProbes = 12
        while (probes < maxProbes && accumulated.size < limit) {
            val response = try {
                streamRepository.getAddonCatalogPage(
                    addonId = descriptor.addonId,
                    catalogType = descriptor.catalogType,
                    catalogId = descriptor.catalogId,
                    skip = probeOffset,
                    genre = genre
                )
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                throw CollectionSourceLoadException(accumulated.toList(), e)
            }
            val metas = response.metas ?: response.items ?: emptyList()
            if (metas.isEmpty()) break
            parseAddonPageRefs(metas = metas, descriptor = descriptor)
                .forEach { accumulated.add(it) }
            probeOffset += metas.size
            probes += 1
            if (metas.size < 20) break
        }
        return accumulated.take(limit)
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
        val hasMore: Boolean,
        val nextOffset: Int
    )

    private suspend fun loadAddonCatalogRefsPage(
        catalog: CatalogConfig,
        offset: Int,
        limit: Int
    ): AddonCatalogRefsPage = coroutineScope {
        val descriptor = resolveAddonCatalogDescriptor(catalog)
            ?: return@coroutineScope AddonCatalogRefsPage(emptyList(), hasMore = false, nextOffset = offset)

        val accumulated = LinkedHashSet<Pair<MediaType, Int>>()
        var probeOffset = offset.coerceAtLeast(0)
        var hasMore = false
        var probes = 0
        val maxProbes = 3

        while (probes < maxProbes && accumulated.size < limit) {
            val response = try {
                streamRepository.getAddonCatalogPage(
                    addonId = descriptor.addonId,
                    catalogType = descriptor.catalogType,
                    catalogId = descriptor.catalogId,
                    skip = probeOffset
                )
            } catch (e: Exception) {
                if (e is CancellationException) throw e
                // A failed request is retryable, not an exhausted catalogue.
                throw e
            }

            val metas = response.metas ?: response.items ?: emptyList()
            if (metas.isEmpty()) {
                hasMore = false
                break
            }

            // Resolve only what this row needs, not a provider's entire 100-item page.
            // Advance by source entries consumed, including entries that cannot be matched.
            val consumed = metas.take(limit - accumulated.size)
            parseAddonPageRefs(
                metas = consumed,
                descriptor = descriptor
            ).forEach { accumulated.add(it) }

            hasMore = true // Providers choose their own page size; only empty means exhausted.
            probeOffset += consumed.size
            probes += 1
        }

        AddonCatalogRefsPage(
            refs = accumulated.take(limit),
            hasMore = hasMore,
            nextOffset = probeOffset
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

        // Items whose addon serves its own metadata are shown as they are instead of being
        // matched to TMDB (a match would drop everything TMDB doesn't list, and streams for
        // a TMDB match would be requested by IMDb id, which that addon doesn't answer).
        val nativeRefs = mutableListOf<Pair<MediaType, Int>>()
        val nativeMetaIds = HashSet<String>()
        val nativeItems = mutableListOf<MediaItem>()
        // Read once per page: each read parses every installed manifest, and per item that
        // starved the heap on a 500-item catalog (ANR).
        val installed = installedAddonsSnapshot()
        val catalogAddon = installed.firstOrNull { it.id == descriptor.addonId }
        metas.forEach { meta ->
            if (parseTmdbRefFromAddonMeta(meta, typeHint) != null || extractImdbId(meta) != null) return@forEach
            val metaId = meta.id?.trim().orEmpty()
            // The addon's own type ("Podcasts", "tv", ...) is what its /meta answers to.
            val addonType = meta.type?.trim()?.takeIf { it.isNotBlank() } ?: descriptor.catalogType
            val mediaType = nativeAddonMediaType(addonType, typeHint)
            if (metaId.isBlank()) return@forEach
            // A metadata addon (AIOMetadata) can re-list another addon's catalog; that addon
            // owns the ids and serves their details and streams.
            val owner = if (catalogAddon != null && addonServesOwnMeta(catalogAddon, addonType, metaId)) {
                descriptor.addonId
            } else {
                findAddonServingOwnMeta(installed, addonType, metaId, excludeAddonId = descriptor.addonId)
            } ?: return@forEach
            val item = addonNative.register(owner, meta, mediaType, addonType) ?: return@forEach
            if (nativeMetaIds.add(metaId)) {
                nativeItems += item
                nativeRefs += mediaType to item.id
            }
        }
        if (nativeItems.isNotEmpty()) {
            cacheItems(nativeItems)
            addonNative.flush()
        }

        metas.forEach { meta ->
            if (meta.id?.trim() in nativeMetaIds) return@forEach
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
            if (meta.id?.trim() in nativeMetaIds) return@forEach
            if (parseTmdbRefFromAddonMeta(meta, typeHint) != null) return@forEach
            if (extractImdbId(meta) != null) return@forEach
            val title = meta.name?.trim().orEmpty()
            if (title.isBlank()) return@forEach
            // A title alone, with no movie/series type to narrow it, matches unrelated TMDB titles.
            val metaHint = typeHint ?: addonCatalogTypeToMediaType(meta.type) ?: return@forEach
            val titleKey = "${metaHint.name}|${title.lowercase(Locale.US)}"
            if (seenTitle.add(titleKey)) {
                titleCandidates += title to metaHint
            }
        }

        val metaSemaphore = Semaphore(2)
        // Don't ask an addon for metadata its manifest says it can't serve (a metadata addon for
        // ids it re-lists from another addon): each miss is several requests, and a page of them
        // gets us rate limited.
        val resolvedFromMeta = unresolvedMetaCandidates
            .filter { addonMayServeMeta(catalogAddon, it.id) }
            .take(8).map { unresolved ->
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

        (nativeRefs + directRefs + resolvedFromMeta + resolvedImdbRefs + resolvedTitleRefs).distinct()
    }

    private suspend fun installedAddonsSnapshot(): List<Addon> = try {
        streamRepository.installedAddons.first()
    } catch (e: CancellationException) {
        throw e
    } catch (e: Exception) {
        // Unknown means "match it to TMDB as before", never a failed row.
        emptyList()
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
            .replace(MediaRegexes.YEAR_SUFFIX_REGEX, "")
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
        return try { URLDecoder.decode(value, "UTF-8") } catch (_: Exception) { value }
    }

    /** Known types are canonicalised; any other addon type keeps its spelling (see CatalogRepository). */
    private fun normalizeAddonCatalogType(rawType: String?): String? {
        val trimmed = rawType?.trim()?.takeIf { it.isNotBlank() } ?: return null
        return when (trimmed.lowercase()) {
            "movie" -> "movie"
            "series" -> "series"
            "tv" -> "tv"
            "show" -> "show"
            "shows" -> "shows"
            else -> trimmed
        }
    }

    /**
     * How a native item is shown. Stremio's "tv" type is a live channel: one stream, no
     * episodes, so it opens like a movie and plays straight away. Other unknown types
     * (podcasts, debrid libraries) are containers of videos, which the series page shows.
     */
    private fun nativeAddonMediaType(rawType: String, typeHint: MediaType?): MediaType {
        if (rawType.equals("tv", ignoreCase = true)) return MediaType.MOVIE
        return typeHint ?: addonCatalogTypeToMediaType(rawType) ?: MediaType.TV
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
        // `moviedbId` would be a useful addon fallback field but it's added
        // by a separate pending change to StremioMetaPreview — stick to the
        // existing `tmdbId` alias until that lands to keep this branch
        // self-contained.
        val rawTmdb = meta.tmdbId?.trim().orEmpty()
        val tmdbFromField = rawTmdb.toIntOrNull()
            ?: MediaRegexes.DIGITS_REGEX.find(rawTmdb)?.value?.toIntOrNull()
        if (tmdbFromField != null && normalizedHint != null) {
            return normalizedHint to tmdbFromField
        }

        val rawId = meta.id?.trim().orEmpty()
        if (rawId.isBlank()) return null

        // Note: do NOT treat plain-numeric `meta.id` as a TMDB id. Some
        // addons use unrelated numeric ids (trakt, internal catalog ids) and
        // those produce phantom TMDB 404s that showed up as empty collection
        // grids. Rely on explicit tmdb_id / moviedb_id or IMDB resolution.

        // IDs like movie:12345 or series:12345
        val typedIdMatch = MediaRegexes.TYPED_ID_REGEX.find(rawId)
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

        val match = MediaRegexes.IMDB_ID_REGEX.find(fromId)
        return match?.value
    }

    /**
     * Get movie details (cached)
     */
    suspend fun getMovieDetails(movieId: Int): MediaItem {
        if (movieId < 0 && addonNative.isNative(movieId)) {
            return addonNative.details(MediaType.MOVIE, movieId)?.also { cacheItem(it) }
                ?: throw IllegalStateException("Native addon item $movieId has no details")
        }
        getCachedItem(MediaType.MOVIE, movieId)?.let { if (movieId < 0 && it.isHomeServer) return it }
        getCachedFullItem(MediaType.MOVIE, movieId)?.let { cached ->
            if (cached.imdbRating.isNotBlank()) return cached
            val imdbRating = getImdbRating(MediaType.MOVIE, movieId)
            if (!imdbRating.isNullOrBlank()) {
                return cached.copy(imdbRating = imdbRating).also { cacheFullDetailsItem(it) }
            }
            return cached
        }

        val item = coroutineScope {
            val detailsDeferred = async { tmdbApi.getMovieDetails(movieId, apiKey, language = contentLanguage) }
            val externalIdsDeferred = async { resolveExternalIds(MediaType.MOVIE, movieId) }

            val details = detailsDeferred.await()
            val imdbId = externalIdsDeferred.await()?.imdbId?.also { cacheImdbId(MediaType.MOVIE, movieId, it) }
            val imdbRating = imdbId?.let { getImdbRating(MediaType.MOVIE, movieId, it) }
            details.toMediaItem().copy(
                imdbRating = imdbRating.orEmpty(),
                contentRating = ContentRating.forMovie(details.releaseDates, contentLanguage)
            )
        }
        cacheFullDetailsItem(item)
        return item
    }

    /**
     * Get TV show details (cached)
     */
    suspend fun getTvDetails(tvId: Int): MediaItem {
        if (tvId < 0 && addonNative.isNative(tvId)) {
            return addonNative.details(MediaType.TV, tvId)?.also { cacheItem(it) }
                ?: throw IllegalStateException("Native addon item $tvId has no details")
        }
        getCachedItem(MediaType.TV, tvId)?.let { if (tvId < 0 && it.isHomeServer) return it }
        getCachedFullItem(MediaType.TV, tvId)?.let { cached ->
            if (cached.imdbRating.isNotBlank()) return cached
            val imdbRating = getImdbRating(MediaType.TV, tvId)
            if (!imdbRating.isNullOrBlank()) {
                return cached.copy(imdbRating = imdbRating).also { cacheFullDetailsItem(it) }
            }
            return cached
        }

        val item = coroutineScope {
            val detailsDeferred = async { tmdbApi.getTvDetails(tvId, apiKey, language = contentLanguage) }
            val externalIdsDeferred = async { resolveExternalIds(MediaType.TV, tvId) }

            val details = detailsDeferred.await()
            val imdbId = externalIdsDeferred.await()?.imdbId?.also { cacheImdbId(MediaType.TV, tvId, it) }
            val imdbRating = imdbId?.let { getImdbRating(MediaType.TV, tvId, it) }
            details.toMediaItem().copy(
                imdbRating = imdbRating.orEmpty(),
                contentRating = ContentRating.forTv(details.contentRatings, contentLanguage)
            )
        }
        cacheFullDetailsItem(item)
        return item
    }

    /**
     * Lightweight calls for LauncherContinueWatchingRepository to avoid heavy IMDb rating/caching tasks.
     */
    suspend fun getLightweightMovieTitle(movieId: Int, language: String = contentLanguage): String? {
        return runCatching {
            tmdbApi.getMovieDetails(movieId, apiKey, language = language).title
        }.getOrNull()
    }

    suspend fun getLightweightTvTitle(tvId: Int, language: String = contentLanguage): String? {
        return runCatching {
            // TMDB uses 'name' for series instead of 'title'
            tmdbApi.getTvDetails(tvId, apiKey, language = language).name
        }.getOrNull()
    }

    suspend fun getLightweightEpisodeTitle(
        tvId: Int,
        seasonNumber: Int,
        episodeNumber: Int,
        language: String = contentLanguage
    ): String? {
        return runCatching {
            tmdbApi.getTvSeason(
                tvId = tvId,
                seasonNumber = seasonNumber,
                apiKey = apiKey,
                language = language
            ).episodes.firstOrNull { it.episodeNumber == episodeNumber }?.name
        }.getOrNull()
    }

    /**
     * Get the TMDB collection (franchise) reference for a movie.
     * Calls /movie/{id} directly to access the `belongs_to_collection` field,
     * which is discarded by getMovieDetails() → toMediaItem().
     * The response is cached by OkHttp, making the redundant call negligible.
     */
    suspend fun getMovieCollectionRef(movieId: Int): com.arflix.tv.data.api.TmdbCollectionRef? {
        return try {
            tmdbApi.getMovieDetails(movieId, apiKey, language = contentLanguage).belongsToCollection
        } catch (e: retrofit2.HttpException) {
            null
        } catch (e: java.io.IOException) {
            null
        }
    }

    /**
     * Fetch all movies in a TMDB collection (franchise).
     * Calls TMDB /collection/{id} and maps the parts array to Movie MediaItems.
     * Used by the Details page to show franchise rows (e.g. "Cars Collection").
     */
    suspend fun getTmdbCollectionItems(collectionId: Int): List<MediaItem> {
        val response = try {
            tmdbApi.getTmdbCollection(collectionId, apiKey, language = contentLanguage)
        } catch (e: retrofit2.HttpException) {
            null
        } catch (e: java.io.IOException) {
            null
        } ?: return emptyList()
        return response.parts
            .sortedBy { it.releaseDate.orEmpty() }
            .map { it.toMediaItem(MediaType.MOVIE) }
    }

    /**
     * Get season episodes with Trakt watched status.
     * Returns immediately upon fetching the TMDB season structure, hydrating missing
     * episode IMDb ratings asynchronously in the background.
     */
    suspend fun getSeasonEpisodes(tvId: Int, seasonNumber: Int): List<Episode> {
        if (tvId < 0 && addonNative.isNative(tvId)) return addonNative.episodes(tvId, seasonNumber)
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
                if (e is kotlinx.coroutines.CancellationException) throw e
                emptySet<String>()
            }
        }
        val hasShowWatchedData = watchedEpisodes.any { it.startsWith("show_tmdb:$tvId:") }

        // Fast-path: return cached episodes immediately
        if (cachedEpisodes != null) {
            val episodes = cachedEpisodes.map { episode ->
                val episodeKey = "show_tmdb:$tvId:${episode.seasonNumber}:${episode.episodeNumber}"
                episode.copy(
                    isWatched = if (hasShowWatchedData) episodeKey in watchedEpisodes else episode.isWatched
                )
            }
            if (episodes.any { it.imdbRating.isBlank() }) {
                repositoryScope.launch {
                    val ratings = getSeasonEpisodeImdbRatings(
                        tvId = tvId,
                        seasonNumber = seasonNumber,
                        episodeNumbers = episodes.map { it.episodeNumber }
                    )
                    if (ratings.isNotEmpty()) {
                        val hydrated = episodes.map { ep ->
                            ep.copy(imdbRating = ep.imdbRating.ifBlank { ratings[ep.seasonNumber to ep.episodeNumber].orEmpty() })
                        }
                        seasonEpisodesCache[cacheKey] = CacheEntry(hydrated, System.currentTimeMillis())
                        _episodeRatingsUpdated.tryEmit(tvId to seasonNumber)
                    }
                }
            }
            return episodes
        }

        val season = tmdbApi.getTvSeason(tvId, seasonNumber, apiKey, language = contentLanguage)
        val cinemetaRatings = getSeriesCinemetaEpisodeRatings(tvId)
        val episodes = season.episodes.map { episode ->
            val episodeKey = "show_tmdb:$tvId:$seasonNumber:${episode.episodeNumber}"
            val rating = cinemetaRatings[seasonNumber to episode.episodeNumber].orEmpty()
            episode.toEpisode().copy(
                imdbRating = rating,
                isWatched = episodeKey in watchedEpisodes
            )
        }
        seasonEpisodesCache[cacheKey] = CacheEntry(episodes, System.currentTimeMillis())

        if (episodes.any { it.imdbRating.isBlank() }) {
            repositoryScope.launch {
                val missingNumbers = episodes.filter { it.imdbRating.isBlank() }.map { it.episodeNumber }
                val ratings = getSeasonEpisodeImdbRatings(
                    tvId = tvId,
                    seasonNumber = seasonNumber,
                    episodeNumbers = missingNumbers
                )
                if (ratings.isNotEmpty()) {
                    val currentCache = getFromCache(seasonEpisodesCache, cacheKey) ?: episodes
                    val hydrated = currentCache.map { ep ->
                        ep.copy(imdbRating = ep.imdbRating.ifBlank { ratings[ep.seasonNumber to ep.episodeNumber].orEmpty() })
                    }
                    seasonEpisodesCache[cacheKey] = CacheEntry(hydrated, System.currentTimeMillis())
                    _episodeRatingsUpdated.tryEmit(tvId to seasonNumber)
                }
            }
        }

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

        // Find the director from crew and prepend as the first cast member
        val director = credits.crew.firstOrNull { it.job == "Director" }

        val castMembers = credits.cast
            .distinctBy { it.id } // TMDB can occasionally return duplicate cast IDs.
            .take(15)
            .map { it.toCastMember() }

        val result = if (director != null) {
            listOf(director.toDirectorCastMember()) + castMembers
        } else {
            castMembers
        }
        castCache[cacheKey] = CacheEntry(result, System.currentTimeMillis())
        return result
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
            if (e is kotlinx.coroutines.CancellationException) throw e

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
        // TMDB knows nothing under a negative stand-in id (native addon / home-server items).
        if (mediaId <= 0) return null
        val cacheKey = "${mediaType}_logo_$mediaId"
        logoCache[cacheKey]?.let { cached ->
            if (System.currentTimeMillis() - cached.timestamp < CACHE_TTL_MS) {
                return cached.data
            }
            logoCache.remove(cacheKey)
        }

        val type = if (mediaType == MediaType.TV) "tv" else "movie"
        return try {
            val images = tmdbApi.getImages(type, mediaId, apiKey)
            // Quality ranking for clearlogos: prefer PNG over SVG (the app has
            // no SVG decoder), English over other locales, and among the
            // survivors pick the highest community-rated logo (vote_average
            // breaks ties, width acts as a secondary for untouched images).
            val logo = images.logos
                .asSequence()
                .filter { !it.filePath.isNullOrBlank() }
                .filterNot { it.filePath!!.endsWith(".svg", ignoreCase = true) }
                .sortedWith(
                    compareByDescending<TmdbImage> { it.iso6391 == "en" }
                        .thenByDescending { it.voteAverage }
                        .thenByDescending { it.width }
                )
                .firstOrNull()
                ?: images.logos.firstOrNull()
            val url = logo?.filePath?.let { "${Constants.LOGO_BASE}$it" }
            logoCache[cacheKey] = CacheEntry(url, System.currentTimeMillis())
            url
        } catch (e: Exception) {
            if (e is kotlinx.coroutines.CancellationException) throw e

            null
        }
    }

    suspend fun getLogoUrl(item: MediaItem): String? {
        item.id.takeIf { it > 0 }?.let { return getLogoUrl(item.mediaType, it) }
        if (!item.isHomeServer) return null

        val resolved = resolveHomeServerLogoRef(item) ?: return null
        return getLogoUrl(resolved.first, resolved.second)
    }

    private suspend fun resolveHomeServerLogoRef(item: MediaItem): Pair<MediaType, Int>? {
        val cacheKey = listOf(
            item.mediaType.name,
            item.homeServerImdbId.orEmpty(),
            HomeServerMatcher.normalizeTitle(item.title),
            item.year
        ).joinToString("|")
        homeServerLogoRefCache[cacheKey]?.let { cached ->
            if (System.currentTimeMillis() - cached.timestamp < CACHE_TTL_MS) return cached.data
            homeServerLogoRefCache.remove(cacheKey)
        }

        val resolvedByImdb = item.homeServerImdbId
            ?.takeIf { it.startsWith("tt", ignoreCase = true) }
            ?.let { resolveImdbToTmdbRef(it, item.mediaType) }
        val resolved = resolvedByImdb ?: resolveHomeServerLogoRefByTitle(item)
        homeServerLogoRefCache[cacheKey] = CacheEntry(resolved, System.currentTimeMillis())
        return resolved
    }

    private suspend fun resolveHomeServerLogoRefByTitle(item: MediaItem): Pair<MediaType, Int>? {
        val title = item.title.trim()
        if (title.isBlank()) return null
        val year = item.year.take(4).toIntOrNull()
        val response = runCatching {
            when (item.mediaType) {
                MediaType.MOVIE -> tmdbApi.searchMovies(
                    apiKey = apiKey,
                    query = title,
                    language = contentLanguage,
                    primaryReleaseYear = year,
                    year = year
                )
                MediaType.TV -> tmdbApi.searchTv(
                    apiKey = apiKey,
                    query = title,
                    language = contentLanguage,
                    firstAirDateYear = year
                )
            }
        }.getOrNull() ?: return null

        val requestedTitle = HomeServerMatcher.normalizeTitle(title)
        val match = response.results
            .map { candidate ->
                val candidateTitle = candidate.title ?: candidate.name ?: candidate.originalTitle
                    ?: candidate.originalName.orEmpty()
                val normalizedTitle = HomeServerMatcher.normalizeTitle(candidateTitle)
                val candidateYear = (candidate.releaseDate ?: candidate.firstAirDate)
                    .orEmpty()
                    .take(4)
                    .toIntOrNull()
                val titleScore = when {
                    requestedTitle == normalizedTitle -> 100
                    requestedTitle.isNotBlank() &&
                        (requestedTitle in normalizedTitle || normalizedTitle in requestedTitle) -> 45
                    else -> 0
                }
                val yearScore = when {
                    year == null || candidateYear == null -> 0
                    year == candidateYear -> 30
                    kotlin.math.abs(year - candidateYear) <= 1 -> 12
                    else -> -40
                }
                candidate to (titleScore + yearScore + candidate.popularity.toInt().coerceAtMost(30))
            }
            .maxByOrNull { it.second }
            ?.takeIf { it.second >= 55 }
            ?.first
            ?: return null
        return item.mediaType to match.id
    }

    /** Instant synchronous peek into the in-memory or persisted logo cache. */
    fun peekCachedLogoUrl(mediaType: MediaType, mediaId: Int): String? {
        val keys = listOf(
            "${mediaType}_logo_$mediaId",
            "${mediaType}_$mediaId",
            "${mediaType.name.lowercase()}_logo_$mediaId",
            "${mediaType.name.lowercase()}_$mediaId",
            "${mediaType.name.uppercase()}_logo_$mediaId",
            "${mediaType.name.uppercase()}_$mediaId"
        )
        for (k in keys) {
            if (logoCache.containsKey(k)) {
                val cached = getFromCache(logoCache, k)
                if (!cached.isNullOrBlank()) return cached
            }
        }
        try {
            val json = context.getSharedPreferences("logo_cache", Context.MODE_PRIVATE).getString("urls", null)
            if (!json.isNullOrBlank()) {
                val jsonObject = org.json.JSONObject(json)
                for (k in keys) {
                    if (jsonObject.has(k)) {
                        val url = jsonObject.optString(k)
                        if (!url.isNullOrBlank()) {
                            logoCache["${mediaType}_logo_$mediaId"] = CacheEntry(url, System.currentTimeMillis())
                            return url
                        }
                    }
                }
            }
        } catch (_: Throwable) {}
        return null
    }

    fun cacheLogoUrl(mediaType: MediaType, mediaId: Int, logoUrl: String) {
        if (logoUrl.isBlank()) return
        val cacheKey = "${mediaType}_logo_$mediaId"
        logoCache[cacheKey] = CacheEntry(logoUrl, System.currentTimeMillis())
    }

    /** Instant synchronous peek into the in-memory season episodes cache. */
    fun peekCachedSeasonEpisodes(tvId: Int, seasonNumber: Int): List<Episode>? {
        val cacheKey = "tv_${tvId}_season_$seasonNumber"
        return getFromCache(seasonEpisodesCache, cacheKey)
    }

    /**
     * Get trailer key (YouTube)
     */
    suspend fun getTrailerKey(mediaType: MediaType, mediaId: Int): String? {
        val type = if (mediaType == MediaType.TV) "tv" else "movie"
        return try {
            val videos = tmdbApi.getVideos(type, mediaId, apiKey, language = contentLanguage)
            var results = videos.results
            // If language-specific request returned no YouTube videos, fall back to English.
            // Explicitly "en-US", not null: TMDB's /videos `language` filters the video records
            // themselves (most titles only ever have English-tagged trailers), and a null here is
            // dropped by Retrofit and then refilled with the user's language by the TMDB
            // interceptor — which made this retry an exact repeat of the call that just failed.
            if (
                results.none { it.site == "YouTube" } &&
                !contentLanguage.equals("en-US", ignoreCase = true)
            ) {
                results = tmdbApi.getVideos(type, mediaId, apiKey, language = "en-US").results
            }
            val trailer = results.find { it.type == "Trailer" && it.site == "YouTube" && it.official }
                ?: results.find { it.type == "Trailer" && it.site == "YouTube" }
                ?: results.find { it.type == "Teaser" && it.site == "YouTube" }
                ?: results.find { it.site == "YouTube" }
            trailer?.key
        } catch (e: Exception) {
            if (e is kotlinx.coroutines.CancellationException) throw e

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

    /** Titles and known-for rows share one request; optional artwork must not delay them. */
    suspend fun searchWithPeople(query: String, maxPeople: Int = 3): MediaSearchResults = coroutineScope {
        val trimmed = query.trim()
        if (trimmed.isEmpty()) return@coroutineScope MediaSearchResults(emptyList(), emptyList())

        val response = tmdbApi.searchMulti(apiKey, trimmed, language = contentLanguage)
        val items = response.results
            .filter { it.mediaType == "movie" || it.mediaType == "tv" }
            .map { it.toMediaItem(if (it.mediaType == "tv") MediaType.TV else MediaType.MOVIE) }
            .distinctBy { it.mediaType to it.id }
        val people = response.results
            .asSequence()
            .filter { it.mediaType == "person" && it.id > 0 && !it.name.isNullOrBlank() }
            .distinctBy { it.id }
            .sortedByDescending { it.popularity }
            .take(maxPeople)
            .toList()

        val rows = people.map { person ->
            val knownForItems = person.knownFor
                .asSequence()
                .filter { it.posterPath != null && (it.mediaType == "movie" || it.mediaType == "tv") }
                .sortedWith(
                    compareByDescending<TmdbMediaItem> { it.voteCount }
                        .thenByDescending { it.popularity }
                )
                .map {
                    it.toMediaItem(
                        if (it.mediaType == "tv") MediaType.TV else MediaType.MOVIE
                    )
                }
                .distinctBy { "${it.mediaType}_${it.id}" }
                .take(20)
                .toList()

            PersonMediaSearchResult(personId = person.id, name = person.name.orEmpty(), items = knownForItems)
        }

        cacheItems(items + rows.flatMap { it.items })
        MediaSearchResults(items, rows)
    }

    /** Native addon items matching [query] (see [StreamRepository.searchNativeAddonCatalogs]). */
    suspend fun searchNativeAddonItems(query: String): List<MediaItem> = try {
        streamRepository.searchNativeAddonCatalogs(query).flatMap { page ->
            val descriptor = AddonCatalogDescriptor(page.addonId, page.catalogType, page.catalogId)
            parseAddonPageRefs(page.metas, descriptor)
                .mapNotNull { (type, id) -> if (id < 0) addonNative.card(type, id) else null }
        }.distinctBy { it.mediaType to it.id }
    } catch (e: CancellationException) {
        throw e
    } catch (_: Exception) {
        emptyList()
    }

    /**
     * Discover movies via TMDB discover API with optional genre/sort/vote filters.
     *
     * Two date windows, deliberately kept apart: `releaseDate*` accepts a film with ANY release
     * in the window (a re-run counts), `primaryReleaseDate*` only one whose FIRST release falls
     * in it — the date the card prints. A filter the user reads off the poster wants the second.
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
        releaseDateGte: String? = null,
        primaryReleaseDateLte: String? = null,
        primaryReleaseDateGte: String? = null,
        minVoteAverage: Double? = null,
        maxVoteAverage: Double? = null,
        certificationCountry: String? = null,
        certificationLte: String? = null
    ): List<MediaItem> {
        val response = tmdbApi.discoverMovies(apiKey, genres = genres, sortBy = sortBy, minVoteCount = minVoteCount, page = page, originalLanguage = language, year = year, keywords = keywords, language = contentLanguage, releaseDateLte = releaseDateLte, releaseDateGte = releaseDateGte, primaryReleaseDateLte = primaryReleaseDateLte, primaryReleaseDateGte = primaryReleaseDateGte, minVoteAverage = minVoteAverage, maxVoteAverage = maxVoteAverage, certificationCountry = certificationCountry, certificationLte = certificationLte)
        val items = response.results.map { it.toMediaItem(MediaType.MOVIE) }
        cacheItems(items)
        return items
    }

    /**
     * Discover TV shows via TMDB discover API with optional genre/sort/vote/language/year filters.
     *
     * Same split as [discoverMovies]: `airDate*` asks when an EPISODE aired (what the home
     * screen's anime rows need), `firstAirDate*` when the show STARTED — the date on the card.
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
        airDateGte: String? = null,
        firstAirDateLte: String? = null,
        firstAirDateGte: String? = null,
        minVoteAverage: Double? = null,
        maxVoteAverage: Double? = null
    ): List<MediaItem> {
        val response = tmdbApi.discoverTv(apiKey, genres = genres, sortBy = sortBy, minVoteCount = minVoteCount, page = page, originalLanguage = language, year = year, keywords = keywords, language = contentLanguage, airDateLte = airDateLte, airDateGte = airDateGte, firstAirDateLte = firstAirDateLte, firstAirDateGte = firstAirDateGte, minVoteAverage = minVoteAverage, maxVoteAverage = maxVoteAverage)
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
            if (e is kotlinx.coroutines.CancellationException) throw e

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
            if (e is kotlinx.coroutines.CancellationException) throw e

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

        val response = try {
            when (mediaType) {
                MediaType.MOVIE -> tmdbApi.getMovieWatchProviders(mediaId, apiKey)
                MediaType.TV -> tmdbApi.getTvWatchProviders(mediaId, apiKey)
            }
        } catch (e: CancellationException) {
            throw e
        } catch (_: Exception) {
            // A failed lookup is not a successful empty response and must not poison the cache.
            return null
        }

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
                    val logoUrl = bundledStreamingLogoUri(canonicalName)
                        ?: provider.logoPath?.let { path ->
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

    private fun CatalogConfig.isTop10Catalog(): Boolean {
        return id.contains("top10", ignoreCase = true) ||
            title.contains("Top 10", ignoreCase = true) ||
            sourceUrl?.contains("top-10", ignoreCase = true) == true ||
            sourceRef?.contains("top-10", ignoreCase = true) == true
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
            normalized.contains("discovery") -> "Discovery+"
            normalized.contains("mgm") -> "MGM+"
            normalized.contains("shudder") -> "Shudder"
            normalized.contains("starz") -> "Starz"
            normalized.contains("youtube") -> "YouTube"
            else -> name
        }
    }

    private fun bundledStreamingLogoUri(canonicalName: String): String? {
        val resId = when (canonicalName.lowercase(Locale.US)) {
            "netflix" -> R.raw.logo_netflix
            "hbo max" -> R.raw.logo_hbo_max
            "hulu" -> R.raw.logo_hulu
            "prime video" -> R.raw.logo_prime_video
            "disney+" -> R.raw.logo_disney_plus
            "paramount+" -> R.raw.logo_paramount_plus
            "peacock" -> R.raw.logo_peacock
            "crunchyroll" -> R.raw.logo_crunchyroll
            "discovery+" -> R.raw.logo_discovery_plus
            "mgm+" -> R.raw.logo_mgm_plus
            "shudder" -> R.raw.logo_shudder
            "starz" -> R.raw.logo_starz
            "apple tv+" -> R.drawable.apple_tv_plus_logo
            else -> null
        } ?: return null
        return "android.resource://${context.packageName}/$resId"
    }

    private fun normalizeWatchRegion(region: String?): String {
        val value = region?.trim()?.uppercase(Locale.US).orEmpty()
        return value.takeIf { it.length == 2 } ?: "US"
    }

    /**
     * Items of a TMDB page added as a catalog (list / collection / company /
     * network / person / keyword / genre). Each kind maps onto the collection
     * source resolvers, so paging, sorting and caching stay in one place.
     * Pages without an explicit movie/tv scope return both, interleaved.
     */
    private suspend fun loadTmdbCatalogRefs(
        sourceUrl: String?,
        sourceRef: String?
    ): List<Pair<MediaType, Int>> = coroutineScope {
        val parsed = parseTmdbCatalogRef(sourceRef)
            ?: sourceUrl?.let { CatalogUrlParser.parseTmdb(it) }
            ?: return@coroutineScope emptyList()
        val requestLanguage = contentLanguage
        val cacheKey = "$requestLanguage:${parsed.kind}:${parsed.id}:${parsed.mediaType.orEmpty()}"
        getFromCache(tmdbCatalogRefsCache, cacheKey)?.let { return@coroutineScope it }
        val limit = TMDB_CATALOG_MAX_ITEMS
        if (parsed.kind == "person") {
            val credits = try {
                tmdbApi.getPersonDetails(parsed.id, apiKey, language = requestLanguage).combinedCredits
            } catch (e: CancellationException) {
                throw e
            } catch (_: Exception) {
                return@coroutineScope emptyList()
            }
            val refs = (credits?.cast.orEmpty() + credits?.crew.orEmpty())
                .filter { it.id > 0 && !it.adult && it.mediaType in tmdbMediaScopes(parsed.mediaType) }
                .sortedByDescending { it.popularity }
                .distinctBy { it.mediaType to it.id }
                .take(limit)
                .map { item ->
                    val type = if (item.mediaType == "tv") MediaType.TV else MediaType.MOVIE
                    cacheCollectionPreview(item, type, requestLanguage)
                    type to item.id
                }
            if (requestLanguage == contentLanguage) {
                tmdbCatalogRefsCache[cacheKey] = CacheEntry(refs, System.currentTimeMillis())
            }
            return@coroutineScope refs
        }
        fun discover(mediaType: String, param: String) = CollectionSourceConfig(
            kind = CollectionSourceKind.TMDB_DISCOVER,
            mediaType = mediaType,
            sortBy = "popularity.desc",
            discoverParams = mapOf(param to parsed.id.toString())
        )
        val sources: List<CollectionSourceConfig> = when (parsed.kind) {
            "list" -> listOf(CollectionSourceConfig(kind = CollectionSourceKind.TMDB_LIST, tmdbListId = parsed.id))
            "collection" -> listOf(
                CollectionSourceConfig(kind = CollectionSourceKind.TMDB_COLLECTION, tmdbCollectionId = parsed.id)
            )
            // TMDB has no with_networks for movies, so a network page is series only.
            "network" -> listOf(discover("tv", "with_networks"))
            "company" -> tmdbMediaScopes(parsed.mediaType).map { discover(it, "with_companies") }
            "keyword" -> tmdbMediaScopes(parsed.mediaType).map { discover(it, "with_keywords") }
            "genre" -> tmdbMediaScopes(parsed.mediaType).map { discover(it, "with_genres") }
            else -> emptyList()
        }
        if (sources.isEmpty()) return@coroutineScope emptyList()
        val perSource = sources.map { source ->
            async {
                val type = when (parsed.mediaType) {
                    "movie" -> MediaType.MOVIE
                    "tv" -> MediaType.TV
                    else -> null
                }
                resolveCollectionSourceRefs(source, offset = 0, limit = limit, mediaType = type)
            }
        }.map { it.await() }
        // Interleave movies and series so a mixed page doesn't start with 100 movies.
        val queues = perSource.map { ArrayDeque(it.refs) }
        val refs = LinkedHashSet<Pair<MediaType, Int>>()
        while (queues.any { it.isNotEmpty() } && refs.size < limit) {
            queues.forEach { queue -> if (queue.isNotEmpty() && refs.size < limit) refs.add(queue.removeFirst()) }
        }
        refs.toList().also { result ->
            if (perSource.none { it.failed } && requestLanguage == contentLanguage) {
                tmdbCatalogRefsCache[cacheKey] = CacheEntry(result, System.currentTimeMillis())
            }
        }
    }

    private fun tmdbMediaScopes(mediaType: String?): List<String> = when (mediaType) {
        "movie" -> listOf("movie")
        "tv" -> listOf("tv")
        else -> listOf("movie", "tv")
    }

    /** Parses the stored "tmdb:{kind}:{id}:{mediaType}" reference. */
    private fun parseTmdbCatalogRef(sourceRef: String?): ParsedCatalogUrl.Tmdb? {
        val raw = sourceRef?.trim()?.takeIf { it.startsWith("tmdb:", ignoreCase = true) } ?: return null
        val parts = raw.split(':')
        if (parts.size < 3) return null
        val kind = parts[1].lowercase(Locale.US).takeIf { it in CatalogUrlParser.TMDB_KINDS } ?: return null
        val id = parts[2].toIntOrNull()?.takeIf { it > 0 } ?: return null
        val mediaType = parts.getOrNull(3)?.takeIf { it.isNotBlank() }?.lowercase(Locale.US)
        if (mediaType != null && mediaType !in setOf("movie", "tv")) return null
        return ParsedCatalogUrl.Tmdb(kind = kind, id = id, mediaType = mediaType, slug = null)
    }

    private suspend fun loadSimklCatalogRefs(catalog: CatalogConfig, limit: Int): List<Pair<MediaType, Int>> = coroutineScope {
        val id = catalog.sourceRef?.removePrefix("simkl_list:")?.toLongOrNull()
            ?: catalog.sourceUrl?.let { CatalogUrlParser.parseSimkl(it)?.id } ?: return@coroutineScope emptyList()
        val list = simklListsRepository?.load(id) ?: return@coroutineScope emptyList()
        val semaphore = Semaphore(6)
        val refs = LinkedHashSet<Pair<MediaType, Int>>()
        for (batch in list.items.chunked(6)) {
            val resolved = batch.map { item -> async {
                semaphore.withPermit {
                    try {
                        val type = if (item.type in setOf("movie", "movies") || item.animeType == "movie") MediaType.MOVIE else MediaType.TV
                        item.tmdb?.takeIf { it > 0 }?.let { type to it }
                            ?: item.imdb?.let { resolveImdbToTmdbRef(it, type) }
                            ?: item.tvdb?.let { tvdb ->
                                tmdbApi.findByExternalId(tvdb.toString(), apiKey, "tvdb_id").tvResults.firstOrNull()?.id?.let { MediaType.TV to it }
                            }
                            ?: resolveTitleToTmdbRef(item.title, type)
                    } catch (e: CancellationException) { throw e }
                    catch (_: Exception) { null }
                }
            } }.awaitAll().filterNotNull()
            refs += resolved
            if (refs.size >= limit) break
        }
        refs.toList()
    }

    private suspend fun loadTraktCatalogRefs(sourceUrl: String?, sourceRef: String? = null): List<Pair<MediaType, Int>> {
        suspend fun loadFromParsed(parsed: ParsedCatalogUrl): List<Pair<MediaType, Int>> {
            suspend fun loadType(type: String): List<TraktPublicListItem> {
                val result = mutableListOf<TraktPublicListItem>()
                var previous: List<TraktPublicListItem>? = null
                var page = 1
                while (true) {
                    val rows = when (parsed) {
                        is ParsedCatalogUrl.TraktUserList -> traktApi.getUserListItems(
                            clientId = Constants.TRAKT_CLIENT_ID, username = parsed.username,
                            listId = parsed.listId, type = type, page = page, limit = 100)
                        is ParsedCatalogUrl.TraktList -> traktApi.getListItems(
                            clientId = Constants.TRAKT_CLIENT_ID, listId = parsed.listId,
                            type = type, page = page, limit = 100)
                        else -> emptyList()
                    }
                    if (rows == previous) break
                    result += rows
                    if (rows.size < 100) break
                    previous = rows
                    page++
                }
                return result
            }
            return mapTraktItemsToTmdbRefs(loadType("movies") + loadType("shows"))
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
        val traktLink = MediaRegexes.TRAKT_URL_REGEX.find(html)?.value
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
        val type = TypeToken.getParameterized(List::class.java, TypeToken.getParameterized(Map::class.java, String::class.java, Any::class.java).type).type
        val rows = try {
            gson.fromJson<List<Map<String, Any?>>>(payload, type)
        } catch (_: Exception) {
            null
        } ?: return emptyList()

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
            .header("User-Agent", OkHttpProvider.userAgentOr("Mozilla/5.0 (Android TV; ARVIO)"))
            .build()
        return try {
            okHttpClient.newCall(request).execute().use { response ->
                if (!response.isSuccessful) return@use null
                response.body?.string()
            }
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            null
        }
    }
}
private fun Any?.toIntSafe(): Int? {
    return when (this) {
        is Number -> this.toInt()
        is String -> this.toIntOrNull()
        else -> null
    }
}

private fun normalizeRating(raw: String): String? {
    val value = raw.trim().replace(',', '.').toFloatOrNull() ?: return null
    if (value <= 0f || value > 10f) return null
    return String.format(Locale.US, "%.1f", value)
}

private fun formatTmdbRating(voteAverage: Float): String =
    normalizeRating(voteAverage.toString()).orEmpty()

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
        title = title?.takeIf { it.isNotBlank() }
            ?: name?.takeIf { it.isNotBlank() }
            ?: originalTitle?.takeIf { it.isNotBlank() }
            ?: originalName?.takeIf { it.isNotBlank() }
            ?: "Unknown",
        subtitle = if (type == MediaType.MOVIE) "Movie" else "TV Series",
        overview = overview ?: "",
        year = year,
        releaseDate = formatDate(dateStr),
        imdbRating = "",
        tmdbRating = formatTmdbRating(voteAverage),
        mediaType = type,
        image = posterPath?.let { "${Constants.IMAGE_BASE}$it" }
            ?: backdropPath?.let { "${Constants.BACKDROP_BASE}$it" }
            ?: "",
        backdrop = backdropPath?.let { "${Constants.BACKDROP_BASE_LARGE}$it" },
        genreIds = genreIds,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle?.takeIf { it.isNotBlank() }
            ?: originalName?.takeIf { it.isNotBlank() },
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
        title = title.takeIf { it.isNotBlank() }
            ?: originalTitle?.takeIf { it.isNotBlank() }
            ?: "Unknown",
        subtitle = "Movie",
        overview = overview ?: "",
        year = year,
        releaseDate = formatDate(releaseDate ?: ""),
        duration = duration,
        rating = if (adult) "R" else "PG-13",
        imdbRating = "",
        tmdbRating = formatTmdbRating(voteAverage),
        mediaType = MediaType.MOVIE,
        image = posterPath?.let { "${Constants.IMAGE_BASE}$it" }
            ?: backdropPath?.let { "${Constants.BACKDROP_BASE}$it" }
            ?: "",
        backdrop = backdropPath?.let { "${Constants.BACKDROP_BASE_LARGE}$it" },
        originalLanguage = originalLanguage,
        originalTitle = originalTitle?.takeIf { it.isNotBlank() },
        budget = budget,
        genreIds = genres.map { it.id }
    )
}

private fun TmdbTvDetails.toMediaItem(): MediaItem {
    val year = firstAirDate?.take(4) ?: ""
    val runtime = episodeRunTime.firstOrNull() ?: 45
    val duration = "${runtime}m"
    val actualSeasonCount = seasons
        .asSequence()
        .filter { it.seasonNumber > 0 && it.episodeCount > 0 }
        .map { it.seasonNumber }
        .distinct()
        .count()
        .takeIf { it > 0 }
        ?: numberOfSeasons.coerceAtLeast(1)

    return MediaItem(
        id = id,
        title = name.takeIf { it.isNotBlank() }
            ?: originalName?.takeIf { it.isNotBlank() }
            ?: "Unknown",
        subtitle = "TV Series",
        overview = overview ?: "",
        year = year,
        releaseDate = formatDate(firstAirDate ?: ""),
        duration = duration,
        imdbRating = "",
        tmdbRating = formatTmdbRating(voteAverage),
        mediaType = MediaType.TV,
        image = posterPath?.let { "${Constants.IMAGE_BASE}$it" }
            ?: backdropPath?.let { "${Constants.BACKDROP_BASE}$it" }
            ?: "",
        backdrop = backdropPath?.let { "${Constants.BACKDROP_BASE_LARGE}$it" },
        originalLanguage = originalLanguage,
        originalTitle = originalName?.takeIf { it.isNotBlank() },
        isOngoing = status == "Returning Series",
        totalEpisodes = actualSeasonCount,
        status = status,
        genreIds = genres.map { it.id }
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

private fun TmdbCrewMember.toDirectorCastMember(): CastMember {
    return CastMember(
        id = id,
        name = name,
        character = job,
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
        if (e is kotlinx.coroutines.CancellationException) throw e

        dateStr
    }
}

private object MediaRegexes {
    val YEAR_SUFFIX_REGEX = Regex("""\s+\(\d{4}\)$""")
    val DIGITS_REGEX = Regex("""\d+""")
    val TYPED_ID_REGEX = Regex("""^(movie|series|tv|show|shows):(\d+)$""", RegexOption.IGNORE_CASE)
    val IMDB_ID_REGEX = Regex("""tt\d{5,}""", RegexOption.IGNORE_CASE)
    val TRAKT_URL_REGEX = Regex("""https?://(?:www\.)?trakt\.tv/users/[^"'\s<]+/lists/[^"'\s<]+""", RegexOption.IGNORE_CASE)
}
