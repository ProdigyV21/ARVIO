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
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.sync.Semaphore
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
    private val homeServerRepository: HomeServerRepository
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
        set(value) {
            field = value?.replace("iw", "he")?.replace('_', '-')
        }

    // === IN-MEMORY CACHE FOR PERFORMANCE ===
    private data class CacheEntry<T>(val data: T, val timestamp: Long)
    private val CACHE_TTL_MS = 5 * 60 * 1000L // 5 minutes

    // Home categories cache - survives ViewModel recreation
    @Volatile var cachedHomeCategories: List<Category> = emptyList()
        private set
    @Volatile private var homeCategoriesFetchedAt = 0L
    private val HOME_CATEGORIES_CACHE_MS = 120_000L // 2 minutes

    private val detailsCache = mutableMapOf<String, CacheEntry<MediaItem>>()
    private val fullDetailsCacheKeys = mutableSetOf<String>()
    private val castCache = mutableMapOf<String, CacheEntry<List<CastMember>>>()
    private val similarCache = mutableMapOf<String, CacheEntry<List<MediaItem>>>()
    private val logoCache = mutableMapOf<String, CacheEntry<String?>>()
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
    private val collectionRefsCache = ConcurrentHashMap<String, CacheEntry<List<Pair<MediaType, Int>>>>()

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

    private fun getCollectionRefsCache(key: String): List<Pair<MediaType, Int>>? {
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
                append(source.tmdbGenreId ?: -1)
                append(':')
                append(source.tmdbPersonId ?: -1)
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
                append(';')
            }
        }
    }

    private suspend fun resolveCollectionCatalogRefs(
        catalog: CatalogConfig,
        requiredCount: Int
    ): List<Pair<MediaType, Int>> {
        val cacheKey = collectionRefsCacheKey(catalog)
        val cached = getCollectionRefsCache(cacheKey)
        if (cached != null && cached.size >= requiredCount.coerceAtLeast(1)) {
            return cached
        }

        val targetCount = requiredCount.coerceAtLeast(1)
        // SERVICE and GENRE rails page through TMDB/addon catalogs on demand,
        // so let the per-source budget grow with the user's scroll position
        // instead of clamping at the default 72/96/120 ceiling. FRANCHISE and
        // other fixed groups keep the small cap.
        val unlimitedGroup = catalog.collectionGroup == CollectionGroupKind.SERVICE ||
            catalog.collectionGroup == CollectionGroupKind.GENRE

        // Resolve all sources in parallel so a slow/failed source never blocks the
        // others — this alone fixes "empty" genre collections where one source 404s.
        val sourceBudgets = catalog.collectionSources.map { source ->
            if (unlimitedGroup) {
                (targetCount + 20).coerceAtLeast(40)
            } else when (source.kind) {
                CollectionSourceKind.ADDON_CATALOG -> (targetCount + 12).coerceAtLeast(24).coerceAtMost(120)
                CollectionSourceKind.MDBLIST_PUBLIC -> (targetCount + 8).coerceAtLeast(24).coerceAtMost(96)
                else -> (targetCount + 8).coerceAtLeast(24).coerceAtMost(72)
            }
        }
        val perSourceRefs: List<List<Pair<MediaType, Int>>> = coroutineScope {
            catalog.collectionSources.mapIndexed { index, source ->
                async {
                    runCatching {
                        resolveCollectionSourceRefs(
                            source,
                            offset = 0,
                            limit = sourceBudgets[index]
                        )
                    }.getOrDefault(emptyList())
                }
            }.map { it.await() }
        }

        val refs = LinkedHashSet<Pair<MediaType, Int>>()
        cached?.forEach { refs.add(it) }

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

        val resolved = refs.toList()
        if (resolved.isNotEmpty()) {
            collectionRefsCache[cacheKey] = CacheEntry(resolved, System.currentTimeMillis())
        }
        return resolved
    }

    fun getCachedItem(mediaType: MediaType, mediaId: Int): MediaItem? {
        val cacheKey = detailsCacheKey(mediaType, mediaId)
        return getFromCache(detailsCache, cacheKey)
    }

    fun getCachedFullItem(mediaType: MediaType, mediaId: Int): MediaItem? {
        val cacheKey = detailsCacheKey(mediaType, mediaId)
        if (cacheKey !in fullDetailsCacheKeys) return null
        val cached = getFromCache(detailsCache, cacheKey)
        if (cached == null) {
            fullDetailsCacheKeys.remove(cacheKey)
        }
        return cached
    }

    fun cacheImdbId(mediaType: MediaType, mediaId: Int, imdbId: String) {
        if (imdbId.isBlank()) return
        val cacheKey = detailsCacheKey(mediaType, mediaId)
        imdbIdCache[cacheKey] = imdbId
    }

    fun getCachedImdbId(mediaType: MediaType, mediaId: Int): String? {
        val cacheKey = detailsCacheKey(mediaType, mediaId)
        return imdbIdCache[cacheKey]
    }

    suspend fun getImdbRating(mediaType: MediaType, mediaId: Int, imdbId: String? = null): String? {
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
        return runCatching {
            when (mediaType) {
                MediaType.MOVIE -> tmdbApi.getMovieExternalIds(mediaId, apiKey)
                MediaType.TV -> tmdbApi.getTvExternalIds(mediaId, apiKey)
            }
        }.getOrNull()
    }

    private suspend fun fetchCinemetaImdbRating(mediaType: MediaType, imdbId: String): String? = withContext(Dispatchers.IO) {
        val typePath = if (mediaType == MediaType.TV) "series" else "movie"
        val request = Request.Builder()
            .url("https://v3-cinemeta.strem.io/meta/$typePath/$imdbId.json")
            .header("Accept", "application/json")
            .header("User-Agent", OkHttpProvider.userAgentOr("Mozilla/5.0 (Android TV; ARVIO)"))
            .build()

        runCatching {
            okHttpClient.newCall(request).execute().use { response ->
                if (!response.isSuccessful) return@use null
                val body = response.body?.string().orEmpty()
                val meta = JSONObject(body).optJSONObject("meta") ?: return@use null
                parseCinemetaMetaRating(meta)
            }
        }.getOrNull()
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
        val cacheKey = detailsCacheKey(item.mediaType, item.id)
        if (cacheKey in fullDetailsCacheKeys) {
            val existingFullDetails = getFromCache(detailsCache, cacheKey)
            if (existingFullDetails != null) return
            fullDetailsCacheKeys.remove(cacheKey)
        }
        detailsCache[cacheKey] = CacheEntry(item, System.currentTimeMillis())
    }

    private fun cacheFullDetailsItem(item: MediaItem) {
        val cacheKey = detailsCacheKey(item.mediaType, item.id)
        detailsCache[cacheKey] = CacheEntry(item, System.currentTimeMillis())
        fullDetailsCacheKeys.add(cacheKey)
    }

    private fun cacheItems(items: List<MediaItem>) {
        items.forEach { cacheItem(it) }
    }

    fun getDefaultCatalogConfigs(): List<CatalogConfig> = buildPreinstalledDefaults()

    companion object {
        const val STREAMING_COLLECTION_ADDON_URL = "https://pastebin.com/raw/P4gfd98n"
        private val UPLOADED_COVER_BASE = "https://" + "nu" + "vioapp.space/uploads/covers/"

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
                CatalogConfig("favorite_tv", "Favorite TV", CatalogSourceType.PREINSTALLED, isPreinstalled = true),
                CatalogConfig("trending_movies", "Trending in Movies", CatalogSourceType.MDBLIST, isPreinstalled = true, sourceUrl = "https://mdblist.com/lists/snoak/trending-movies", sourceRef = "mdblist:https://mdblist.com/lists/snoak/trending-movies"),
                CatalogConfig("trending_tv", "Trending in Shows", CatalogSourceType.MDBLIST, isPreinstalled = true, sourceUrl = "https://mdblist.com/lists/snoak/trakt-s-trending-shows", sourceRef = "mdblist:https://mdblist.com/lists/snoak/trakt-s-trending-shows"),
                CatalogConfig("trending_anime", "Trending in Anime", CatalogSourceType.MDBLIST, isPreinstalled = true, sourceUrl = "https://mdblist.com/lists/snoak/trending-anime-shows", sourceRef = "mdblist:https://mdblist.com/lists/snoak/trending-anime-shows"),
                CatalogConfig(SportsAddonCapabilities.SPORTS_CATEGORY_ROW_ID, "Sports", CatalogSourceType.PREINSTALLED, isPreinstalled = true),
                CatalogConfig(SportsAddonCapabilities.POPULAR_LIVE_TV_ROW_ID, "Popular Live Sports", CatalogSourceType.PREINSTALLED, isPreinstalled = true),
                CatalogConfig("top10_movies_today", "Top 10 Movies Today", CatalogSourceType.MDBLIST, isPreinstalled = true, sourceUrl = "https://mdblist.com/lists/snoak/top-10-movies-of-the-day", sourceRef = "mdblist:https://mdblist.com/lists/snoak/top-10-movies-of-the-day"),
                CatalogConfig("top10_shows_today", "Top 10 Shows Today", CatalogSourceType.MDBLIST, isPreinstalled = true, sourceUrl = "https://mdblist.com/lists/snoak/top-10-shows-of-the-day", sourceRef = "mdblist:https://mdblist.com/lists/snoak/top-10-shows-of-the-day"),
                CatalogConfig("just_added", "Just Added", CatalogSourceType.MDBLIST, isPreinstalled = true, sourceUrl = "https://mdblist.com/lists/snoak/latest-movies-digital-release", sourceRef = "mdblist:https://mdblist.com/lists/snoak/latest-movies-digital-release"),
                CatalogConfig("top_movies_week", "Top Movies This Week", CatalogSourceType.MDBLIST, isPreinstalled = true, sourceUrl = "https://mdblist.com/lists/linaspurinis/top-watched-movies-of-the-week", sourceRef = "mdblist:https://mdblist.com/lists/linaspurinis/top-watched-movies-of-the-week"),
                CatalogConfig("new_kdramas", "New in K-Dramas", CatalogSourceType.MDBLIST, isPreinstalled = true, sourceUrl = "https://mdblist.com/lists/snoak/latest-kdrama-shows", sourceRef = "mdblist:https://mdblist.com/lists/snoak/latest-kdrama-shows"),
                CatalogConfig("coming_soon", "Coming Soon", CatalogSourceType.MDBLIST, isPreinstalled = true, sourceUrl = "https://mdblist.com/lists/snoak/upcoming-movies", sourceRef = "mdblist:https://mdblist.com/lists/snoak/upcoming-movies")
            )

            fun addonCollectionSource(addonId: String?, type: String, id: String) = CollectionSourceConfig(
                kind = CollectionSourceKind.ADDON_CATALOG,
                addonId = addonId,
                addonCatalogType = type,
                addonCatalogId = id
            )
            /** Streaming-service trending via TMDB watch-providers. */
            fun watchProviderSource(mediaType: MediaType, providerId: Int) = CollectionSourceConfig(
                kind = CollectionSourceKind.TMDB_WATCH_PROVIDER,
                mediaType = if (mediaType == MediaType.MOVIE) "movie" else "series",
                tmdbWatchProviderId = providerId,
                watchRegion = "US",
                sortBy = "popularity.desc"
            )
            fun tmdbCollectionSource(collectionId: Int) = CollectionSourceConfig(
                kind = CollectionSourceKind.TMDB_COLLECTION,
                tmdbCollectionId = collectionId
            )
            fun tmdbKeywordSource(mediaType: MediaType?, keywordId: Int) = CollectionSourceConfig(
                kind = CollectionSourceKind.TMDB_KEYWORD,
                mediaType = when (mediaType) {
                    MediaType.MOVIE -> "movie"
                    MediaType.TV -> "series"
                    else -> null
                },
                tmdbKeywordId = keywordId,
                sortBy = "popularity.desc"
            )
            fun tmdbGenreSource(mediaType: MediaType, genreId: Int) = CollectionSourceConfig(
                kind = CollectionSourceKind.TMDB_GENRE,
                mediaType = if (mediaType == MediaType.MOVIE) "movie" else "series",
                tmdbGenreId = genreId,
                sortBy = "popularity.desc"
            )
            fun curatedSource(vararg refs: String) = CollectionSourceConfig(
                kind = CollectionSourceKind.CURATED_IDS,
                curatedRefs = refs.toList()
            )
            // Public mdblist list (anonymous JSON endpoint). `slug` is everything
            // after /lists/ — e.g. "jxduffy/star-wars-chronological-order". Used
            // as a completeness fill-in behind curated lists: curated entries win
            // the ordering; mdblist-only items get appended at the end.
            fun mdblistSource(slug: String) = CollectionSourceConfig(
                kind = CollectionSourceKind.MDBLIST_PUBLIC,
                mdblistSlug = slug
            )
            fun collection(
                id: String,
                title: String,
                group: CollectionGroupKind,
                description: String,
                cover: String? = null,
                focusGif: String? = null,
                hero: String? = null,
                heroVideo: String? = null,
                clearLogo: String? = null,
                sources: List<CollectionSourceConfig>,
                requiredAddons: List<String> = emptyList()
            ) = CatalogConfig(
                id = id,
                title = title,
                sourceType = CatalogSourceType.PREINSTALLED,
                isPreinstalled = true,
                kind = CatalogKind.COLLECTION,
                collectionGroup = group,
                collectionDescription = description,
                collectionCoverImageUrl = cover,
                collectionFocusGifUrl = focusGif ?: cover,
                collectionHeroImageUrl = hero ?: cover,
                collectionHeroGifUrl = focusGif ?: hero ?: cover,
                collectionHeroVideoUrl = heroVideo,
                collectionClearLogoUrl = clearLogo,
                collectionSources = sources,
                requiredAddonUrls = requiredAddons
            )

        // ──────────────────────────────────────────────────────────────
        // Streaming Services (12 total)
        //
        // Premium 7 (Netflix, Prime, Apple TV+, Disney+, HBO Max, Hulu,
        // Paramount+) lead the list and use the mrtxiv networks-video-
        // collection assets: static PNG cover + MP4 hero loop. `focusGif` is
        // null so `collection()`'s `focusGif ?: cover` fallback keeps the
        // cover displayed on focus (no GIF swap, since we rely on the video
        // loop for motion). `clearLogo` is null across all 12 services — the
        // PNG cover already carries the service wordmark and layering a
        // separate clearLogo on top would double-stamp the branding.
        //
        // The remaining 5 services keep their existing community cover + TMDB
        // hero while adopting the same null-focusGif / null-clearLogo shape.
        //
        // Addon catalogs (aio-metadata / org.kris.ultra.max.all.v5 /
        // community.bharatbinge / community-provided pastebin) win when the
        // user has them installed; the TMDB watch-provider source is the
        // out-of-the-box fallback so the rail populates on a fresh profile.
        // ──────────────────────────────────────────────────────────────
        val mrtxivBase = "https://raw.githubusercontent.com/mrtxiv/networks-video-collection/3486fc9a3d0efe59d1929e75f66021dc4e15bcb7/"
        val services = listOf(
            // ── Premium 7 (mrtxiv assets + motion hero) ──
            collection(
                id = "collection_service_netflix",
                title = "Netflix",
                group = CollectionGroupKind.SERVICE,
                description = "Trending movies and series on Netflix.",
                cover = "${mrtxivBase}networks%20collection/netflix.png",
                focusGif = null,
                hero = null,
                heroVideo = "${mrtxivBase}networks%20videos/netflix.mp4",
                clearLogo = null,
                sources = listOf(
                    addonCollectionSource("aio-metadata", "movie", "mdblist.88328"),
                    addonCollectionSource("aio-metadata", "series", "mdblist.86751"),
                    addonCollectionSource("org.kris.ultra.max.all.v5", "movie", "netflix_movies"),
                    addonCollectionSource("org.kris.ultra.max.all.v5", "series", "netflix_series"),
                    addonCollectionSource(null, "movie", "nfx"),
                    addonCollectionSource(null, "series", "nfx"),
                    watchProviderSource(MediaType.MOVIE, 8),
                    watchProviderSource(MediaType.TV, 8)
                ),
                requiredAddons = listOf(STREAMING_COLLECTION_ADDON_URL)
            ),
            collection(
                id = "collection_service_prime",
                title = "Prime Video",
                group = CollectionGroupKind.SERVICE,
                description = "Trending movies and series on Prime Video.",
                cover = "${mrtxivBase}networks%20collection/amazonprime.png",
                focusGif = null,
                hero = null,
                heroVideo = "${mrtxivBase}networks%20videos/amazonprime.mp4",
                clearLogo = null,
                sources = listOf(
                    addonCollectionSource("aio-metadata", "movie", "mdblist.86755"),
                    addonCollectionSource("aio-metadata", "series", "mdblist.86753"),
                    addonCollectionSource("org.kris.ultra.max.all.v5", "movie", "amazon_movies"),
                    addonCollectionSource("org.kris.ultra.max.all.v5", "series", "amazon_series"),
                    addonCollectionSource(null, "movie", "amp"),
                    addonCollectionSource(null, "series", "amp"),
                    watchProviderSource(MediaType.MOVIE, 9),
                    watchProviderSource(MediaType.TV, 9)
                ),
                requiredAddons = listOf(STREAMING_COLLECTION_ADDON_URL)
            ),
            collection(
                id = "collection_service_appletv",
                title = "Apple TV+",
                group = CollectionGroupKind.SERVICE,
                description = "Trending movies and series on Apple TV+.",
                cover = "${mrtxivBase}networks%20collection/appletvplus.png",
                focusGif = null,
                hero = null,
                heroVideo = "${mrtxivBase}networks%20videos/appletv.mp4",
                clearLogo = null,
                sources = listOf(
                    addonCollectionSource("org.kris.ultra.max.all.v5", "movie", "apple_movies"),
                    addonCollectionSource("org.kris.ultra.max.all.v5", "series", "apple_series"),
                    watchProviderSource(MediaType.MOVIE, 350),
                    watchProviderSource(MediaType.TV, 350)
                )
            ),
            collection(
                id = "collection_service_disney",
                title = "Disney+",
                group = CollectionGroupKind.SERVICE,
                description = "Trending movies and series on Disney+.",
                cover = "${mrtxivBase}networks%20collection/disneyplus.png",
                focusGif = null,
                hero = null,
                heroVideo = "${mrtxivBase}networks%20videos/disneyplus.mp4",
                clearLogo = null,
                sources = listOf(
                    mdblistSource("garycrawfordgc/disney-shows")
                )
            ),
            collection(
                id = "collection_service_hbo",
                title = "HBO Max",
                group = CollectionGroupKind.SERVICE,
                description = "Trending movies and series on HBO Max.",
                cover = "${mrtxivBase}networks%20collection/hbomax.png",
                focusGif = null,
                hero = null,
                heroVideo = "${mrtxivBase}networks%20videos/hbomax.mp4",
                clearLogo = null,
                sources = listOf(
                    addonCollectionSource("aio-metadata", "movie", "mdblist.89647"),
                    addonCollectionSource("aio-metadata", "series", "mdblist.89649"),
                    addonCollectionSource(null, "movie", "hbm"),
                    addonCollectionSource(null, "series", "hbm"),
                    watchProviderSource(MediaType.MOVIE, 1899),
                    watchProviderSource(MediaType.TV, 1899)
                ),
                requiredAddons = listOf(STREAMING_COLLECTION_ADDON_URL)
            ),
            collection(
                id = "collection_service_hulu",
                title = "Hulu",
                group = CollectionGroupKind.SERVICE,
                description = "Trending movies and series on Hulu.",
                cover = "${mrtxivBase}networks%20collection/hulu.png",
                focusGif = null,
                hero = null,
                heroVideo = "${mrtxivBase}networks%20videos/hulu.mp4",
                clearLogo = null,
                sources = listOf(
                    addonCollectionSource("aio-metadata", "series", "mdblist.88327"),
                    addonCollectionSource("org.kris.ultra.max.all.v5", "movie", "hulu_movies"),
                    addonCollectionSource("org.kris.ultra.max.all.v5", "series", "hulu_series"),
                    watchProviderSource(MediaType.MOVIE, 15),
                    watchProviderSource(MediaType.TV, 15)
                )
            ),
            collection(
                id = "collection_service_paramount",
                title = "Paramount+",
                group = CollectionGroupKind.SERVICE,
                description = "Trending movies and series on Paramount+.",
                cover = "${mrtxivBase}networks%20collection/paramount.png",
                focusGif = null,
                hero = null,
                heroVideo = "${mrtxivBase}networks%20videos/paramount.mp4",
                clearLogo = null,
                sources = listOf(
                    addonCollectionSource("aio-metadata", "movie", "mdblist.86762"),
                    addonCollectionSource("aio-metadata", "series", "mdblist.86761"),
                    addonCollectionSource("org.kris.ultra.max.all.v5", "movie", "paramount_movies"),
                    watchProviderSource(MediaType.MOVIE, 2303),
                    watchProviderSource(MediaType.TV, 2303),
                    watchProviderSource(MediaType.MOVIE, 2616),
                    watchProviderSource(MediaType.TV, 2616)
                )
            ),
            // ── Extras (keep existing cover/hero, strip GIF + clearLogo) ──
            collection(
                id = "collection_service_shudder",
                title = "Shudder",
                group = CollectionGroupKind.SERVICE,
                description = "Horror & thriller picks from Shudder.",
                cover = "${UPLOADED_COVER_BASE}9a804000-5337-4031-9669-7be45c213f6a.gif",
                focusGif = null,
                hero = "https://image.tmdb.org/t/p/original/ecKQlAEG95k62SMGhvX83oEqANK.jpg",
                clearLogo = null,
                sources = listOf(
                    addonCollectionSource("org.kris.ultra.max.all.v5", "movie", "shudder_movies"),
                    addonCollectionSource("org.kris.ultra.max.all.v5", "series", "shudder_series"),
                    watchProviderSource(MediaType.MOVIE, 99),
                    watchProviderSource(MediaType.TV, 99)
                )
            ),
            collection(
                id = "collection_service_jiohotstar",
                title = "JioHotstar",
                group = CollectionGroupKind.SERVICE,
                description = "Trending movies and series on JioHotstar.",
                cover = "https://i.postimg.cc/Pr4XcqRq/ezgif-com-video-to-gif-converter.gif",
                focusGif = null,
                hero = "https://image.tmdb.org/t/p/original/askg3SMvhqEl4OL52YuvdtY40Yb.jpg",
                clearLogo = null,
                sources = listOf(
                    addonCollectionSource("community.bharatbinge", "movie", "flixpatrol-netflix-movies"),
                    addonCollectionSource("community.bharatbinge", "series", "flixpatrol-netflix-series"),
                    addonCollectionSource("org.hilay.tv.maldivesnet", "tv", "hilay_catalog"),
                    watchProviderSource(MediaType.MOVIE, 122),
                    watchProviderSource(MediaType.TV, 122)
                )
            ),
            collection(
                id = "collection_service_sonyliv",
                title = "SonyLiv",
                group = CollectionGroupKind.SERVICE,
                description = "Trending movies and series on SonyLiv.",
                cover = "https://cdn.postimage.me/2026/04/11/1000046089.gif",
                focusGif = null,
                hero = "https://image.tmdb.org/t/p/original/uDgy6hyPd82kOHh6I95FLtLnj6p.jpg",
                clearLogo = null,
                sources = listOf(
                    addonCollectionSource("community.bharatbinge", "movie", "provider-sonyliv-movies"),
                    addonCollectionSource("community.bharatbinge", "series", "provider-sonyliv-series"),
                    watchProviderSource(MediaType.MOVIE, 237),
                    watchProviderSource(MediaType.TV, 237)
                )
            ),
            collection(
                id = "collection_service_sky",
                title = "Sky",
                group = CollectionGroupKind.SERVICE,
                description = "Trending movies and series on Sky.",
                cover = "${UPLOADED_COVER_BASE}be914269-f8c5-4c51-8aa5-86581074c10f.png",
                focusGif = null,
                hero = "https://image.tmdb.org/t/p/original/pwGmXVKUgKN13psUjlhC9zBcq1o.jpg",
                clearLogo = null,
                sources = listOf(
                    addonCollectionSource("aio-metadata", "movie", "mdblist.38516"),
                    addonCollectionSource("aio-metadata", "series", "mdblist.74627"),
                    watchProviderSource(MediaType.MOVIE, 29),
                    watchProviderSource(MediaType.TV, 29)
                )
            ),
            collection(
                id = "collection_service_crunchyroll",
                title = "Crunchyroll",
                group = CollectionGroupKind.SERVICE,
                description = "Anime on Crunchyroll.",
                cover = "https://mir-s3-cdn-cf.behance.net/project_modules/fs_webp/380e75223389683.67f7c1dc0669a.png",
                focusGif = null,
                heroVideo = "${mrtxivBase}networks%20videos/crunchyroll.mp4",
                clearLogo = null,
                sources = listOf(
                    addonCollectionSource("aio-metadata", "movie", "streaming.cru_movie"),
                    addonCollectionSource("aio-metadata", "series", "streaming.cru_series"),
                    watchProviderSource(MediaType.MOVIE, 283),
                    watchProviderSource(MediaType.TV, 283)
                )
            )
        )

        // ──────────────────────────────────────────────────────────────
        // Franchises (17 franchises)
        // Primary: mdblist via aio-metadata addon.
        // Fallback: known TMDB collection / keyword IDs so the row still
        // populates when the aio-metadata addon isn't installed.
        // ──────────────────────────────────────────────────────────────
        val franchises = listOf(
            collection(
                id = "collection_franchise_wizarding_world",
                title = "Wizarding World",
                group = CollectionGroupKind.FRANCHISE,
                description = "The Harry Potter and Fantastic Beasts saga.",
                cover = "https://comicbook.com/wp-content/uploads/sites/4/2024/10/Harry-Potter-logo-Wizarding-World-logo.png",
                hero = "https://image.tmdb.org/t/p/original/hziiv14OpD73u9gAak4XDDfBKa2.jpg",
                clearLogo = "https://image.tmdb.org/t/p/original/n7Pj4doQ1yfElCiGTGFTvzoQkpf.png",
                // TMDB collections: 1241 = Harry Potter, 435259 = Fantastic
                // Beasts. Plus community list for anything the TMDB collection
                // forgot (e.g. HBO's Harry Potter reboot series).
                sources = listOf(
                    tmdbCollectionSource(1241),
                    tmdbCollectionSource(435259),
                    mdblistSource("thebirdod/harry-potter-collection")
                )
            ),
            collection(
                id = "collection_franchise_dc",
                title = "DC Universe",
                group = CollectionGroupKind.FRANCHISE,
                description = "Heroes and villains across the DC multiverse.",
                cover = "https://i.postimg.cc/zGMpg1RJ/DC-Universe.jpg",
                focusGif = "https://i.ibb.co/chw2zR64/dc-superhero-films-opening-introduction-t9kpwalz3ep57s9i.gif",
                hero = "https://image.tmdb.org/t/p/original/5UQsZrfbfG2dYJbx8DxfoTr2Bve.jpg",
                clearLogo = "https://image.tmdb.org/t/p/original/qBtAryVKg11iEOmxeb31fxIHuvN.png",
                // Keyword 9715 is "superhero" — it pulls every superhero movie
                // including Marvel entries, which is how unrelated films ended
                // up in DC. Use curated DCEU/DC cinematic content in release
                // order, plus addon fallback for users who have aio-metadata.
                sources = listOf(
                    curatedSource(
                        // DCEU
                        "movie:49521",   // Man of Steel (2013)
                        "movie:209112",  // Batman v Superman (2016)
                        "movie:297761",  // Suicide Squad (2016)
                        "movie:297762",  // Wonder Woman (2017)
                        "movie:141052",  // Justice League (2017)
                        "movie:297802",  // Aquaman (2018)
                        "movie:287947",  // Shazam! (2019)
                        "movie:495764",  // Birds of Prey (2020)
                        "movie:464052",  // Wonder Woman 1984 (2020)
                        "movie:791373",  // Zack Snyder's Justice League (2021)
                        "movie:436270",  // Black Adam (2022)
                        "movie:594767",  // Shazam! Fury of the Gods (2023)
                        "movie:298618",  // The Flash (2023)
                        "movie:565770",  // Blue Beetle (2023)
                        "movie:572802",  // Aquaman and the Lost Kingdom (2023)
                        // Matt Reeves Batman
                        "movie:414906",  // The Batman (2022)
                        // Non-DCEU standalones & DCU (new Gunn era)
                        "movie:475557",  // Joker (2019)
                        "movie:698687",  // Joker: Folie à Deux (2024)
                        "movie:1287536", // Superman (2025)
                        // DC TV series
                        "tv:1435",       // Smallville
                        "tv:62688",      // Supergirl
                        "tv:1412",       // Arrow
                        "tv:60735",      // The Flash
                        "tv:62286",      // DC's Legends of Tomorrow
                        "tv:105248",     // Peacemaker
                        "tv:116244"      // The Penguin
                    ),
                    // DCEU film list + DC TV list (both community-curated).
                    mdblistSource("kingkearney/dc-universe"),
                    mdblistSource("kraftynic/dc-tv-shows1")
                )
            ),
            collection(
                id = "collection_franchise_mcu",
                title = "MCU Universe",
                group = CollectionGroupKind.FRANCHISE,
                description = "The Marvel Cinematic Universe.",
                cover = "https://i.ibb.co/zHbdGxHT/marvel-studios.gif",
                focusGif = "https://giffiles.alphacoders.com/127/12700.gif",
                hero = "https://image.tmdb.org/t/p/original/9BBTo63ANSmhC4e6r62OJFuK2GL.jpg",
                clearLogo = "https://image.tmdb.org/t/p/original/hUzeosd33nzE5MCNsZxCGEKTXaQ.png",
                // Phase-order release sequence blending theatrical films with
                // Disney+ series slotted where they aired relative to the MCU
                // continuity (e.g. WandaVision after Endgame, She-Hulk after
                // Shang-Chi). Keyword 180547 was dropped — returned unrelated
                // "cinematic universe" items that polluted the row.
                sources = listOf(
                    curatedSource(
                        // Phase 1
                        "movie:1726",    // Iron Man (2008)
                        "movie:1724",    // The Incredible Hulk (2008)
                        "movie:10138",   // Iron Man 2 (2010)
                        "movie:10195",   // Thor (2011)
                        "movie:1771",    // Captain America: The First Avenger (2011)
                        "movie:24428",   // The Avengers (2012)
                        // Phase 2
                        "movie:68721",   // Iron Man 3 (2013)
                        "movie:76338",   // Thor: The Dark World (2013)
                        "movie:100402",  // Captain America: The Winter Soldier (2014)
                        "movie:118340",  // Guardians of the Galaxy (2014)
                        "movie:99861",   // Avengers: Age of Ultron (2015)
                        "movie:102899",  // Ant-Man (2015)
                        // Phase 3
                        "movie:271110",  // Captain America: Civil War (2016)
                        "movie:284052",  // Doctor Strange (2016)
                        "movie:283995",  // Guardians of the Galaxy Vol. 2 (2017)
                        "movie:315635",  // Spider-Man: Homecoming (2017)
                        "movie:284053",  // Thor: Ragnarok (2017)
                        "movie:284054",  // Black Panther (2018)
                        "movie:299536",  // Avengers: Infinity War (2018)
                        "movie:363088",  // Ant-Man and the Wasp (2018)
                        "movie:299537",  // Captain Marvel (2019)
                        "movie:299534",  // Avengers: Endgame (2019)
                        "movie:429617",  // Spider-Man: Far From Home (2019)
                        // Phase 4 — Disney+ series start here
                        "tv:85271",      // WandaVision
                        "tv:88396",      // The Falcon and the Winter Soldier
                        "tv:84958",      // Loki
                        "movie:497698",  // Black Widow
                        "tv:92749",      // What If...?
                        "movie:566525",  // Shang-Chi and the Legend of the Ten Rings
                        "tv:88329",      // Hawkeye
                        "movie:524434",  // Eternals
                        "movie:634649",  // Spider-Man: No Way Home
                        "tv:92782",      // Moon Knight
                        "movie:453395",  // Doctor Strange in the Multiverse of Madness
                        "tv:92783",      // Ms. Marvel
                        "movie:616037",  // Thor: Love and Thunder
                        "tv:92785",      // She-Hulk: Attorney at Law
                        "movie:505642",  // Black Panther: Wakanda Forever
                        // Phase 5
                        "movie:640146",  // Ant-Man and the Wasp: Quantumania
                        "tv:114472",     // Secret Invasion
                        "movie:447365",  // Guardians of the Galaxy Vol. 3
                        "movie:609681",  // The Marvels
                        "tv:138501",     // Echo
                        "movie:533535",  // Deadpool & Wolverine
                        "tv:202412",     // Agatha All Along
                        "tv:202555",     // (kept for MCU timeline coverage)
                        // Phase 6
                        "movie:822119",  // Captain America: Brave New World
                        "movie:986056",  // Thunderbolts*
                        "tv:114471",     // Ironheart
                        "movie:617126"   // The Fantastic Four: First Steps
                    ),
                    // Fill-in from mdblist — covers late-phase releases and
                    // the live-action MCU TV catalog (separate list so both
                    // flows populate from community-curated sources).
                    mdblistSource("lt3dave/marvel-cinematic-universe-mcu-collection"),
                    mdblistSource("at0microuton/mcu-tv-shows")
                )
            ),
            collection(
                id = "collection_franchise_xmen",
                title = "X-Men",
                group = CollectionGroupKind.FRANCHISE,
                description = "The X-Men film saga.",
                cover = "https://i.postimg.cc/RC2Ny8Ds/X-Men.jpg",
                hero = "https://image.tmdb.org/t/p/original/pIajnwDRDH3OJ25bp1Oqvmk2mrS.jpg",
                clearLogo = "https://image.tmdb.org/t/p/original/qkDbPsV76eU9ldQsHhjmEzjeeox.png",
                // TMDB 748 = X-Men, 453993 = Deadpool, 556 = Wolverine. Plus
                // the community "chronological order" list to catch Gifted,
                // Legion, and any non-Fox spinoffs the collections miss.
                sources = listOf(
                    tmdbCollectionSource(748),
                    tmdbCollectionSource(453993),
                    tmdbCollectionSource(556),
                    mdblistSource("jxduffy/x-men-chronological-order")
                )
            ),
            collection(
                id = "collection_franchise_star_wars",
                title = "Star Wars",
                group = CollectionGroupKind.FRANCHISE,
                description = "A galaxy far, far away.",
                cover = "https://i.pinimg.com/originals/d0/45/6e/d0456e80877753487e03deaab16c3d26.gif",
                focusGif = "https://i.postimg.cc/Wz08p7rv/Star-Wars.jpg",
                hero = "https://image.tmdb.org/t/p/original/d8duYyyC9J5T825Hg7grmaabfxQ.jpg",
                clearLogo = "https://image.tmdb.org/t/p/original/vswPivs3yuUsk5aW6DbnyeNQ4GX.png",
                // Timeline (in-universe) order, not release order — Clone Wars
                // sits between Eps II and III, Rogue One immediately precedes
                // A New Hope, Mandalorian/Ahsoka follow Return of the Jedi.
                sources = listOf(
                    curatedSource(
                        "movie:1893",    // Ep I: The Phantom Menace
                        "movie:1894",    // Ep II: Attack of the Clones
                        "movie:12180",   // The Clone Wars (2008 film)
                        "tv:4194",       // Star Wars: The Clone Wars
                        "movie:1895",    // Ep III: Revenge of the Sith
                        "tv:105971",     // The Bad Batch
                        "tv:60554",      // Star Wars Rebels
                        "movie:348350",  // Solo
                        "tv:83867",      // Andor
                        "tv:92830",      // Obi-Wan Kenobi
                        "movie:330459",  // Rogue One
                        "movie:11",      // Ep IV: A New Hope
                        "movie:1891",    // Ep V: The Empire Strikes Back
                        "movie:1892",    // Ep VI: Return of the Jedi
                        "tv:82856",      // The Mandalorian
                        "tv:115036",     // The Book of Boba Fett
                        "tv:114461",     // Ahsoka
                        "tv:202879",     // Skeleton Crew (was 202555 — wrong id)
                        "tv:203085",     // Tales of the Jedi
                        "tv:251091",     // Tales of the Empire
                        "movie:140607",  // Ep VII: The Force Awakens
                        "movie:181808",  // Ep VIII: The Last Jedi
                        "movie:181812",  // Ep IX: The Rise of Skywalker
                        "tv:114479",     // The Acolyte (Hi-Republic era)
                        "tv:79093",      // Star Wars Resistance
                        "tv:114410"      // Star Wars: Visions (anthology)
                    ),
                    // Fill-in from the community-maintained Star Wars list so
                    // upcoming titles (Mandalorian & Grogu, Starfighter etc.)
                    // show up without a code change each time one is added.
                    mdblistSource("jxduffy/star-wars-chronological-order")
                )
            ),
            collection(
                id = "collection_franchise_lotr",
                title = "Lord of the Rings & Hobbit",
                group = CollectionGroupKind.FRANCHISE,
                description = "Middle-earth on film.",
                cover = "https://i.postimg.cc/d1bnKfh6/Lordoftherings.jpg",
                hero = "https://image.tmdb.org/t/p/original/bccR2CGTWVVSZAG0yqmy3DIvhTX.jpg",
                clearLogo = "https://image.tmdb.org/t/p/original/dMAXhf7jVsc8Qsx26wsoOmoQh3r.png",
                sources = listOf(
                    tmdbCollectionSource(119),
                    tmdbCollectionSource(121938),
                    mdblistSource("spudhead15/lord-of-the-rings-and-hobbit-collection")
                )
            ),
            collection(
                id = "collection_franchise_pirates",
                title = "Pirates of the Caribbean",
                group = CollectionGroupKind.FRANCHISE,
                description = "Captain Jack Sparrow's swashbuckling saga.",
                cover = "https://i.postimg.cc/Gmwdxn5R/Pirates.jpg",
                hero = "https://image.tmdb.org/t/p/original/1Ds7xy7ILo8u2WWxdnkJth1jQVT.jpg",
                clearLogo = "https://image.tmdb.org/t/p/original/o0s0xqUcaWWv0Kh3QYmfxSQjmPD.png",
                sources = listOf(
                    tmdbCollectionSource(295)
                )
            ),
            collection(
                id = "collection_franchise_hunger_games",
                title = "Hunger Games",
                group = CollectionGroupKind.FRANCHISE,
                description = "The Hunger Games saga.",
                cover = "https://i.postimg.cc/FzfKsZ29/Hunger-Games.jpg",
                hero = "https://image.tmdb.org/t/p/original/b9aCOHFj0zNuwujAag7BFenZ3hF.jpg",
                clearLogo = "https://image.tmdb.org/t/p/original/jgEGp0adB6fGfILwoPB9lAyc38x.png",
                sources = listOf(
                    tmdbCollectionSource(131635)
                )
            ),
            collection(
                id = "collection_franchise_jurassic",
                title = "Jurassic World",
                group = CollectionGroupKind.FRANCHISE,
                description = "The Jurassic Park / World saga.",
                cover = "https://i.postimg.cc/hjJDMNJn/JWorld.jpg",
                hero = "https://image.tmdb.org/t/p/original/9xDQLI2rMokcQe4bJnb2optuuOO.jpg",
                clearLogo = "https://image.tmdb.org/t/p/original/ec4wy0iZFkHTxw04HyX4r06DwrH.png",
                sources = listOf(
                    tmdbCollectionSource(328)
                )
            ),
            collection(
                id = "collection_franchise_avatar",
                title = "Avatar",
                group = CollectionGroupKind.FRANCHISE,
                description = "James Cameron's Pandora saga.",
                cover = "https://i.postimg.cc/nLSV4nhT/AVATAR.jpg",
                hero = "https://image.tmdb.org/t/p/original/Yc6c6BB8eSpjcd4f4E4mVqnSVe.jpg",
                clearLogo = "https://image.tmdb.org/t/p/original/bET8bJdM8diTCpUVilWqqu0M3EJ.png",
                sources = listOf(
                    tmdbCollectionSource(87096)
                )
            ),
            collection(
                id = "collection_franchise_dune",
                title = "Dune",
                group = CollectionGroupKind.FRANCHISE,
                description = "The Dune saga.",
                cover = "https://i.postimg.cc/HnrT6frm/Dune.jpg",
                hero = "https://image.tmdb.org/t/p/original/5p1tenxJ4ad8yplzBkOvv5K7Tqo.jpg",
                clearLogo = "https://image.tmdb.org/t/p/original/woifx7xduIyJYq8ktCiN36zt9Xu.png",
                sources = listOf(
                    tmdbCollectionSource(726871)
                )
            ),
            collection(
                id = "collection_franchise_indiana_jones",
                title = "Indiana Jones",
                group = CollectionGroupKind.FRANCHISE,
                description = "The Indiana Jones saga.",
                cover = "https://i.postimg.cc/tCbrtFwS/Indiana-Jo.jpg",
                hero = "https://image.tmdb.org/t/p/original/sRLex7fDc3OzjcghJIjnPYjslC2.jpg",
                clearLogo = "https://image.tmdb.org/t/p/original/yCZMIJaNriW79rR8NL4Kiq1mvWr.png",
                sources = listOf(
                    tmdbCollectionSource(84)
                )
            ),
            collection(
                id = "collection_franchise_007",
                title = "007",
                group = CollectionGroupKind.FRANCHISE,
                description = "The James Bond saga.",
                cover = "https://i.postimg.cc/L5LHFfB0/007.jpg",
                hero = "https://image.tmdb.org/t/p/original/etoMLWs4TOJxqPBi8Y5oVFw8mCt.jpg",
                clearLogo = "https://image.tmdb.org/t/p/original/pxvf5usDNAzPEc9j0yluQA2I2Ud.png",
                sources = listOf(
                    tmdbCollectionSource(645)
                )
            ),
            collection(
                id = "collection_franchise_mission_impossible",
                title = "Mission Impossible",
                group = CollectionGroupKind.FRANCHISE,
                description = "The Mission: Impossible saga.",
                cover = "https://i.postimg.cc/sfpWfstZ/MI.jpg",
                hero = "https://image.tmdb.org/t/p/original/628Dep6AxEtDxjZoGP78TsOxYbK.jpg",
                clearLogo = "https://image.tmdb.org/t/p/original/d4Nk4FAlEoHLNEny88SQ7Jk9ZsZ.png",
                sources = listOf(
                    tmdbCollectionSource(87359)
                )
            ),
            collection(
                id = "collection_franchise_godfather",
                title = "The Godfather",
                group = CollectionGroupKind.FRANCHISE,
                description = "The Godfather trilogy.",
                cover = "https://i.postimg.cc/X7YwbzbT/The-Godfather.jpg",
                hero = "https://image.tmdb.org/t/p/original/mItOpbeYTu9IuQPCnZZbyTZjt4u.jpg",
                clearLogo = "https://image.tmdb.org/t/p/original/kysDTCloxUPJ1BILI4f8gs74fcr.png",
                sources = listOf(
                    tmdbCollectionSource(230)
                )
            ),
            collection(
                id = "collection_franchise_john_wick",
                title = "John Wick",
                group = CollectionGroupKind.FRANCHISE,
                description = "The John Wick saga.",
                cover = "https://i.postimg.cc/W14q7rtM/JW.jpg",
                hero = "https://image.tmdb.org/t/p/original/5vUux2vNtzV8mnTHJ2dZztnRUhR.jpg",
                clearLogo = "https://image.tmdb.org/t/p/original/eVXvH6j4qM8ZEqZfw5bZ6JGQxqZ.png",
                sources = listOf(
                    tmdbCollectionSource(404609)
                )
            ),
            collection(
                id = "collection_franchise_transformers",
                title = "Transformers",
                group = CollectionGroupKind.FRANCHISE,
                description = "The Transformers saga.",
                cover = "https://i.postimg.cc/CLw3Lyhx/Transformers.jpg",
                hero = "https://image.tmdb.org/t/p/original/srYya1ZlI97Au4jUYAktDe3avyA.jpg",
                clearLogo = "https://image.tmdb.org/t/p/original/sRdt54X9wXUvCEwG4CdVyxFcC8y.png",
                sources = listOf(
                    tmdbCollectionSource(8650)
                )
            )
        )

        // ──────────────────────────────────────────────────────────────
        // Genres (11 genres)
        // Primary: addon catalogs when available; fallback to TMDB
        // discover-by-genre. "New" = latest action (as configured by
        // source list). Superhero & Crime get keyword + genre fallbacks.
        // ──────────────────────────────────────────────────────────────
        val genres = listOf(
            collection(
                id = "collection_genre_new",
                title = "New",
                group = CollectionGroupKind.GENRE,
                description = "Fresh action releases.",
                cover = "https://raw.githubusercontent.com/itsrenoria/fusion-starter-kit/refs/heads/main/resources/widgets/genres/wide/dannyrutledge/action-wide.png",
                hero = "https://image.tmdb.org/t/p/original/628Dep6AxEtDxjZoGP78TsOxYbK.jpg",
                clearLogo = "https://raw.githubusercontent.com/itsrenoria/fusion-starter-kit/refs/heads/main/resources/widgets/genres/wide/dannyrutledge/action-wide.png",
                sources = listOf(
                    addonCollectionSource("org.kris.ultra.max.all.v5", "movie", "action_movies"),
                    addonCollectionSource("org.kris.ultra.max.all.v5", "series", "action_series"),
                    addonCollectionSource("aio-metadata", "movie", "mdblist.91211"),
                    tmdbGenreSource(MediaType.MOVIE, 28),
                    tmdbGenreSource(MediaType.TV, 10759)
                )
            ),
            collection(
                id = "collection_genre_scifi",
                title = "Sci-Fi",
                group = CollectionGroupKind.GENRE,
                description = "Science fiction movies and series.",
                cover = "https://raw.githubusercontent.com/itsrenoria/fusion-starter-kit/refs/heads/main/resources/widgets/genres/wide/dannyrutledge/sci-fi-wide.png",
                hero = "https://image.tmdb.org/t/p/original/5p1tenxJ4ad8yplzBkOvv5K7Tqo.jpg",
                clearLogo = "https://raw.githubusercontent.com/itsrenoria/fusion-starter-kit/refs/heads/main/resources/widgets/genres/wide/dannyrutledge/sci-fi-wide.png",
                sources = listOf(
                    addonCollectionSource("org.kris.ultra.max.all.v5", "movie", "scifi_movies"),
                    addonCollectionSource("org.kris.ultra.max.all.v5", "series", "scifi_series"),
                    addonCollectionSource("aio-metadata", "movie", "mdblist.91220"),
                    addonCollectionSource("aio-metadata", "series", "mdblist.91221"),
                    tmdbGenreSource(MediaType.MOVIE, 878),
                    tmdbGenreSource(MediaType.TV, 10765)
                )
            ),
            collection(
                id = "collection_genre_horror",
                title = "Horror",
                group = CollectionGroupKind.GENRE,
                description = "Horror movies and thrillers.",
                cover = "https://raw.githubusercontent.com/itsrenoria/fusion-starter-kit/refs/heads/main/resources/widgets/genres/wide/dannyrutledge/horror-wide.png",
                hero = "https://image.tmdb.org/t/p/original/nCbkOyOMTEwlEV0LtCOvCnwEONA.jpg",
                clearLogo = "https://raw.githubusercontent.com/itsrenoria/fusion-starter-kit/refs/heads/main/resources/widgets/genres/wide/dannyrutledge/horror-wide.png",
                sources = listOf(
                    addonCollectionSource("org.kris.ultra.max.all.v5", "movie", "horror_movies"),
                    addonCollectionSource("aio-metadata", "movie", "mdblist.91215"),
                    tmdbGenreSource(MediaType.MOVIE, 27)
                )
            ),
            collection(
                id = "collection_genre_thriller",
                title = "Thriller",
                group = CollectionGroupKind.GENRE,
                description = "Thriller movies and series.",
                cover = "https://raw.githubusercontent.com/itsrenoria/fusion-starter-kit/refs/heads/main/resources/widgets/genres/wide/dannyrutledge/thriller-wide.png",
                hero = "https://image.tmdb.org/t/p/original/pwGmXVKUgKN13psUjlhC9zBcq1o.jpg",
                clearLogo = "https://raw.githubusercontent.com/itsrenoria/fusion-starter-kit/refs/heads/main/resources/widgets/genres/wide/dannyrutledge/thriller-wide.png",
                sources = listOf(
                    addonCollectionSource("org.kris.ultra.max.all.v5", "movie", "thriller_movies"),
                    addonCollectionSource("org.kris.ultra.max.all.v5", "series", "thriller_series"),
                    tmdbGenreSource(MediaType.MOVIE, 53)
                )
            ),
            collection(
                id = "collection_genre_fantasy",
                title = "Fantasy",
                group = CollectionGroupKind.GENRE,
                description = "Fantasy movies and series.",
                cover = "https://raw.githubusercontent.com/itsrenoria/fusion-starter-kit/refs/heads/main/resources/widgets/genres/wide/dannyrutledge/fantasy-wide.png",
                hero = "https://image.tmdb.org/t/p/original/bccR2CGTWVVSZAG0yqmy3DIvhTX.jpg",
                clearLogo = "https://raw.githubusercontent.com/itsrenoria/fusion-starter-kit/refs/heads/main/resources/widgets/genres/wide/dannyrutledge/fantasy-wide.png",
                sources = listOf(
                    addonCollectionSource("org.kris.ultra.max.all.v5", "movie", "fantasy_movies"),
                    addonCollectionSource("org.kris.ultra.max.all.v5", "series", "fantasy_series"),
                    tmdbGenreSource(MediaType.MOVIE, 14),
                    tmdbGenreSource(MediaType.TV, 10765)
                )
            ),
            collection(
                id = "collection_genre_animation",
                title = "Animation",
                group = CollectionGroupKind.GENRE,
                description = "Animated movies and series.",
                cover = "https://raw.githubusercontent.com/itsrenoria/fusion-starter-kit/refs/heads/main/resources/widgets/genres/wide/dannyrutledge/animation-wide.png",
                hero = "https://image.tmdb.org/t/p/original/askg3SMvhqEl4OL52YuvdtY40Yb.jpg",
                clearLogo = "https://raw.githubusercontent.com/itsrenoria/fusion-starter-kit/refs/heads/main/resources/widgets/genres/wide/dannyrutledge/animation-wide.png",
                sources = listOf(
                    addonCollectionSource("org.kris.ultra.max.all.v5", "movie", "animation_movies"),
                    addonCollectionSource("org.kris.ultra.max.all.v5", "series", "animation_series"),
                    tmdbGenreSource(MediaType.MOVIE, 16),
                    tmdbGenreSource(MediaType.TV, 16)
                )
            ),
            collection(
                id = "collection_genre_adventure",
                title = "Adventure",
                group = CollectionGroupKind.GENRE,
                description = "Adventure movies and series.",
                cover = "https://raw.githubusercontent.com/itsrenoria/fusion-starter-kit/refs/heads/main/resources/widgets/genres/wide/dannyrutledge/adventure-wide.png",
                hero = "https://image.tmdb.org/t/p/original/sRLex7fDc3OzjcghJIjnPYjslC2.jpg",
                clearLogo = "https://raw.githubusercontent.com/itsrenoria/fusion-starter-kit/refs/heads/main/resources/widgets/genres/wide/dannyrutledge/adventure-wide.png",
                sources = listOf(
                    addonCollectionSource("aio-metadata", "movie", "mdblist.123222"),
                    tmdbGenreSource(MediaType.MOVIE, 12),
                    tmdbGenreSource(MediaType.TV, 10759)
                )
            ),
            collection(
                id = "collection_genre_comedy",
                title = "Comedy",
                group = CollectionGroupKind.GENRE,
                description = "Comedy movies and series.",
                cover = "https://raw.githubusercontent.com/itsrenoria/fusion-starter-kit/refs/heads/main/resources/widgets/genres/wide/dannyrutledge/comedy-wide.png",
                hero = "https://image.tmdb.org/t/p/original/uDgy6hyPd82kOHh6I95FLtLnj6p.jpg",
                clearLogo = "https://raw.githubusercontent.com/itsrenoria/fusion-starter-kit/refs/heads/main/resources/widgets/genres/wide/dannyrutledge/comedy-wide.png",
                sources = listOf(
                    addonCollectionSource("org.kris.ultra.max.all.v5", "movie", "comedy_movies"),
                    addonCollectionSource("org.kris.ultra.max.all.v5", "series", "comedy_series"),
                    tmdbGenreSource(MediaType.MOVIE, 35),
                    tmdbGenreSource(MediaType.TV, 35)
                )
            ),
            collection(
                id = "collection_genre_family",
                title = "Family Movie Night",
                group = CollectionGroupKind.GENRE,
                description = "Family friendly picks.",
                cover = "https://raw.githubusercontent.com/itsrenoria/fusion-starter-kit/refs/heads/main/resources/widgets/genres/wide/dannyrutledge/family-movie-night-wide.png",
                hero = "https://image.tmdb.org/t/p/original/askg3SMvhqEl4OL52YuvdtY40Yb.jpg",
                clearLogo = "https://raw.githubusercontent.com/itsrenoria/fusion-starter-kit/refs/heads/main/resources/widgets/genres/wide/dannyrutledge/family-movie-night-wide.png",
                sources = listOf(
                    addonCollectionSource("org.kris.ultra.max.all.v5", "movie", "family_movies"),
                    addonCollectionSource("org.kris.ultra.max.all.v5", "series", "family_series"),
                    tmdbGenreSource(MediaType.MOVIE, 10751),
                    tmdbGenreSource(MediaType.TV, 10751)
                )
            ),
            collection(
                id = "collection_genre_superhero",
                title = "Superhero & Villains",
                group = CollectionGroupKind.GENRE,
                description = "Superheroes and their arch enemies.",
                cover = "https://raw.githubusercontent.com/itsrenoria/fusion-starter-kit/refs/heads/main/resources/widgets/genres/wide/dannyrutledge/superheroes-wide.png",
                hero = "https://image.tmdb.org/t/p/original/9BBTo63ANSmhC4e6r62OJFuK2GL.jpg",
                clearLogo = "https://raw.githubusercontent.com/itsrenoria/fusion-starter-kit/refs/heads/main/resources/widgets/genres/wide/dannyrutledge/superheroes-wide.png",
                sources = listOf(
                    addonCollectionSource("org.kris.ultra.max.all.v5", "movie", "theme_superhero"),
                    tmdbKeywordSource(MediaType.MOVIE, 9715),
                    tmdbKeywordSource(MediaType.TV, 9715)
                )
            ),
            collection(
                id = "collection_genre_crime",
                title = "Crime",
                group = CollectionGroupKind.GENRE,
                description = "Crime movies and series.",
                cover = "https://raw.githubusercontent.com/itsrenoria/fusion-starter-kit/refs/heads/main/resources/widgets/genres/wide/dannyrutledge/crime-wide.png",
                hero = "https://image.tmdb.org/t/p/original/mItOpbeYTu9IuQPCnZZbyTZjt4u.jpg",
                clearLogo = "https://raw.githubusercontent.com/itsrenoria/fusion-starter-kit/refs/heads/main/resources/widgets/genres/wide/dannyrutledge/crime-wide.png",
                sources = listOf(
                    addonCollectionSource("org.kris.ultra.max.all.v5", "movie", "theme_serialkiller"),
                    addonCollectionSource("org.kris.ultra.max.all.v5", "movie", "crime_movies"),
                    addonCollectionSource("org.kris.ultra.max.all.v5", "series", "crime_series"),
                    tmdbGenreSource(MediaType.MOVIE, 80),
                    tmdbGenreSource(MediaType.TV, 80)
                )
            )
        )

            val legacyCollectionsByTitle = (services + franchises + genres)
                .associateBy { it.title.trim().lowercase(Locale.US) }
            val legacyCollectionAliases = mapOf(
                "marvel" to "mcu universe",
                "harry potter" to "wizarding world",
                "james bond" to "007",
                "jurassic park" to "jurassic world",
                "lord of the rings" to "lord of the rings & hobbit",
                "family" to "family movie night",
                "superhero" to "superhero & villains"
            )
            fun resolveLegacyCollection(title: String): CatalogConfig? {
                val normalizedTitle = title.trim().lowercase(Locale.US)
                return legacyCollectionsByTitle[normalizedTitle]
                    ?: legacyCollectionAliases[normalizedTitle]?.let(legacyCollectionsByTitle::get)
            }
            fun mergeCollectionSources(
                primary: List<CollectionSourceConfig>,
                fallback: List<CollectionSourceConfig>
            ): List<CollectionSourceConfig> {
                return (primary + fallback).distinctBy { source ->
                    listOf(
                        source.kind.name,
                        source.mediaType.orEmpty(),
                        source.addonId.orEmpty(),
                        source.addonCatalogType.orEmpty(),
                        source.addonCatalogId.orEmpty(),
                        source.tmdbGenreId?.toString().orEmpty(),
                        source.tmdbPersonId?.toString().orEmpty(),
                        source.tmdbCollectionId?.toString().orEmpty(),
                        source.tmdbKeywordId?.toString().orEmpty(),
                        source.tmdbWatchProviderId?.toString().orEmpty(),
                        source.watchRegion.orEmpty(),
                        source.sortBy.orEmpty(),
                        source.curatedRefs?.joinToString(",").orEmpty(),
                        source.mdblistSlug.orEmpty()
                    ).joinToString("|")
                }
            }
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
                val legacy = resolveLegacyCollection(entry.title)
                val legacyStaticCover = legacy?.collectionCoverImageUrl?.takeUnless {
                    it.contains(".gif", ignoreCase = true) || it.contains("gifv", ignoreCase = true)
                }
                val legacyHeroCover = legacy?.collectionHeroImageUrl?.takeUnless {
                    it.contains(".gif", ignoreCase = true) || it.contains("gifv", ignoreCase = true)
                }
                val preferredCover = when (entry.group) {
                    CollectionGroupKind.FRANCHISE -> if (entry.sources.isNotEmpty()) {
                        entry.coverImageUrl
                    } else {
                        legacyStaticCover ?: entry.coverImageUrl
                    }
                    else -> entry.coverImageUrl
                }
                val preferredHero = when (entry.group) {
                    CollectionGroupKind.SERVICE,
                    CollectionGroupKind.GENRE,
                    CollectionGroupKind.FRANCHISE -> preferredCover
                    else -> legacy?.collectionHeroImageUrl ?: preferredCover
                }
                CatalogConfig(
                    id = entry.id,
                    title = entry.title,
                    sourceType = CatalogSourceType.PREINSTALLED,
                    isPreinstalled = true,
                    kind = CatalogKind.COLLECTION,
                    collectionGroup = entry.group,
                    collectionDescription = legacy?.collectionDescription
                        ?: CollectionTemplateManifest.descriptionFor(entry),
                    collectionCoverImageUrl = preferredCover,
                    collectionFocusGifUrl = preferredCover,
                    collectionHeroImageUrl = preferredHero,
                    collectionHeroGifUrl = preferredHero,
                    collectionHeroVideoUrl = entry.heroVideoUrl ?: legacy?.collectionHeroVideoUrl,
                    collectionClearLogoUrl = null,
                    collectionTileShape = if (entry.group == CollectionGroupKind.GENRE) {
                        CollectionTileShape.LANDSCAPE
                    } else {
                        entry.tileShape
                    },
                    collectionHideTitle = entry.hideTitle,
                    collectionSources = mergeCollectionSources(
                        primary = entry.sources,
                        fallback = legacy?.collectionSources.orEmpty()
                    ),
                    requiredAddonUrls = emptyList()
                )
            }

            val pinnedLeadCatalogs = topLevelCatalogs.drop(1).take(3)
            val trailingCatalogs = listOf(topLevelCatalogs.first()) + topLevelCatalogs.drop(4)
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
                // trending_netflix / _disney / _prime / _hbo / _apple /
                // _paramount / _hulu / _peacock rows have been retired in
                // favor of the Services collection-tile row.
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
            CatalogSourceType.MDBLIST -> loadMdblistCatalogRefs(catalog.sourceUrl, catalog.sourceRef)
            CatalogSourceType.ADDON -> loadAddonCatalogRefsPage(catalog, offset = 0, limit = effectiveMaxItems).refs
            CatalogSourceType.PREINSTALLED -> emptyList()
            CatalogSourceType.HOME_SERVER -> emptyList()
        }
        if (mediaRefs.isEmpty()) return@coroutineScope null

        val semaphore = Semaphore(6)
        val jobs = mediaRefs.distinct().take(effectiveMaxItems).map { (type, tmdbId) ->
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
        if (catalog.sourceType == CatalogSourceType.HOME_SERVER) {
            return@coroutineScope loadHomeServerCatalogPage(catalog, offset, effectiveLimit)
        } else if (catalog.sourceType == CatalogSourceType.ADDON) {
            val page = loadAddonCatalogRefsPage(catalog, offset, effectiveLimit)
            pageRefs = page.refs
            hasMore = page.hasMore && offset + pageRefs.size < rankedCatalogLimit
        } else {
            val mediaRefs = when (catalog.sourceType) {
                CatalogSourceType.TRAKT -> loadTraktCatalogRefs(catalog.sourceUrl, catalog.sourceRef)
                CatalogSourceType.MDBLIST -> loadMdblistCatalogRefs(catalog.sourceUrl, catalog.sourceRef)
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
            hasMore = page.hasMore
        )
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

    suspend fun loadCollectionCatalogPage(
        catalog: CatalogConfig,
        offset: Int,
        limit: Int
    ): CategoryPageResult = coroutineScope {
        if (catalog.collectionSources.isEmpty() || limit <= 0 || offset < 0) {
            return@coroutineScope CategoryPageResult(emptyList(), hasMore = false)
        }

        val refs = resolveCollectionCatalogRefs(
            catalog = catalog,
            requiredCount = (offset + limit).coerceAtLeast(limit)
        )
        if (refs.isEmpty()) return@coroutineScope CategoryPageResult(emptyList(), hasMore = false)

        val pageRefs = refs.drop(offset).take(limit)
        val itemsByRef = LinkedHashMap<Pair<MediaType, Int>, MediaItem>()
        val missingRefs = mutableListOf<Pair<MediaType, Int>>()
        pageRefs.forEach { (type, tmdbId) ->
            val cachedItem = getCachedItem(type, tmdbId)
            if (cachedItem != null) {
                itemsByRef[type to tmdbId] = cachedItem
            } else {
                missingRefs += (type to tmdbId)
            }
        }
        val semaphore = Semaphore(2)
        val jobs = missingRefs.map { (type, tmdbId) ->
            async {
                semaphore.withPermit {
                    val item = runCatching {
                        when (type) {
                            MediaType.MOVIE -> getMovieDetails(tmdbId)
                            MediaType.TV -> getTvDetails(tmdbId)
                        }
                    }.getOrNull()
                    if (item != null) {
                        itemsByRef[type to tmdbId] = item
                    }
                }
            }
        }
        jobs.forEach { it.await() }
        val items = pageRefs.mapNotNull { itemsByRef[it] }
        if (items.isNotEmpty()) cacheItems(items)
        CategoryPageResult(items = items, hasMore = offset + pageRefs.size < refs.size)
    }

    private suspend fun resolveCollectionSourceRefs(
        source: CollectionSourceConfig,
        offset: Int,
        limit: Int
    ): List<Pair<MediaType, Int>> {
        // Defense in depth: every source-kind resolver must be wrapped so a
        // transient TMDB 404 / network error never propagates out of a
        // collection detail load and crashes the app. Collections from
        // third-party addons, keyword queries on unusual IDs, and region-gated
        // watch-provider calls all return HTTP errors sometimes — the right
        // UX is an empty row, not a force-close.
        return try {
            when (source.kind) {
                CollectionSourceKind.ADDON_CATALOG -> loadCollectionAddonRefs(source, offset, limit)
                CollectionSourceKind.TMDB_GENRE -> loadCollectionGenreRefs(source, limit)
                CollectionSourceKind.TMDB_PERSON -> loadCollectionPersonRefs(source, limit)
                CollectionSourceKind.TMDB_COLLECTION -> loadCollectionTmdbCollectionRefs(source, limit)
                CollectionSourceKind.TMDB_KEYWORD -> loadCollectionKeywordRefs(source, limit)
                CollectionSourceKind.TMDB_WATCH_PROVIDER -> loadCollectionWatchProviderRefs(source, limit)
                CollectionSourceKind.CURATED_IDS -> loadCollectionCuratedRefs(source, limit)
                CollectionSourceKind.MDBLIST_PUBLIC -> loadCollectionMdblistPublicRefs(source, limit)
            }
        } catch (e: kotlinx.coroutines.CancellationException) {
            throw e
        } catch (e: Exception) {
            if (e is kotlinx.coroutines.CancellationException) throw e

            emptyList()
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
        limit: Int
    ): List<Pair<MediaType, Int>> {
        val slug = source.mdblistSlug?.trim()?.trim('/').orEmpty()
        if (slug.isBlank()) return emptyList()
        val body = withContext(Dispatchers.IO) {
            fetchUrl("https://mdblist.com/lists/$slug/json")
        } ?: return emptyList()
        val array = runCatching { JSONArray(body) }.getOrNull() ?: return emptyList()
        val refs = mutableListOf<Pair<MediaType, Int>>()
        for (i in 0 until array.length()) {
            val obj = array.optJSONObject(i) ?: continue
            val id = obj.optInt("id", -1).takeIf { it > 0 } ?: continue
            val type = when (obj.optString("mediatype").lowercase(Locale.US)) {
                "movie" -> MediaType.MOVIE
                "show", "series", "tv" -> MediaType.TV
                else -> continue
            }
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
        limit: Int
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
        }.take(limit)
    }

    /**
     * TMDB `/collection/{id}` returns the canonical list of films in a franchise
     * (Harry Potter = 1241, LOTR = 119, etc.). We keep sort-by-release-date so
     * franchise chronology is preserved. This is a movies-only source.
     */
    private suspend fun loadCollectionTmdbCollectionRefs(
        source: CollectionSourceConfig,
        limit: Int
    ): List<Pair<MediaType, Int>> {
        val id = source.tmdbCollectionId ?: return emptyList()
        val response = try {
            tmdbApi.getTmdbCollection(id, apiKey, language = contentLanguage)
        } catch (e: kotlinx.coroutines.CancellationException) {
            throw e
        } catch (e: retrofit2.HttpException) {
            null
        } catch (e: java.io.IOException) {
            null
        } ?: return emptyList()
        return response.parts
            .sortedBy { it.releaseDate.orEmpty() }
            .map { MediaType.MOVIE to it.id }
            .take(limit)
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
        ) ?: return@coroutineScope emptyList()

        val response = runCatching {
            loadPagedAddonCollectionRefs(
                descriptor = AddonCatalogDescriptor(
                    addonId = addonId,
                    catalogType = catalogType,
                    catalogId = catalogId
                ),
                offset = offset,
                limit = limit
            )
        }.getOrNull() ?: emptyList()

        response
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
        val personId = source.tmdbPersonId ?: return emptyList()
        val sortBy = source.sortBy ?: "popularity.desc"
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
        if (limit <= 0) return emptyList()
        val refs = LinkedHashSet<Pair<MediaType, Int>>()
        var page = 1
        var totalPages = 1
        while (refs.size < limit && page <= totalPages) {
            val response = runCatching { fetchPage(page) }.getOrNull() ?: break
            response.results.forEach { refs.add(mediaType to it.id) }
            totalPages = response.totalPages.coerceAtLeast(1)
            if (response.results.isEmpty()) break
            page += 1
        }
        return refs.take(limit)
    }

    private suspend fun loadPagedAddonCollectionRefs(
        descriptor: AddonCatalogDescriptor,
        offset: Int,
        limit: Int
    ): List<Pair<MediaType, Int>> {
        if (limit <= 0) return emptyList()
        val accumulated = LinkedHashSet<Pair<MediaType, Int>>()
        var probeOffset = offset.coerceAtLeast(0)
        var probes = 0
        val maxProbes = 12
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
        val cacheKey = "movie_$movieId"
        getFromCache(detailsCache, cacheKey)?.let { cached ->
            if (cacheKey in fullDetailsCacheKeys) {
                if (cached.imdbRating.isNotBlank()) return cached
                val imdbRating = getImdbRating(MediaType.MOVIE, movieId)
                if (!imdbRating.isNullOrBlank()) {
                    return cached.copy(imdbRating = imdbRating).also { cacheFullDetailsItem(it) }
                }
                return cached
            }
        }

        val item = coroutineScope {
            val detailsDeferred = async { tmdbApi.getMovieDetails(movieId, apiKey, language = contentLanguage) }
            val externalIdsDeferred = async { resolveExternalIds(MediaType.MOVIE, movieId) }

            val details = detailsDeferred.await()
            val imdbId = externalIdsDeferred.await()?.imdbId?.also { cacheImdbId(MediaType.MOVIE, movieId, it) }
            val imdbRating = imdbId?.let { getImdbRating(MediaType.MOVIE, movieId, it) }
            details.toMediaItem().copy(imdbRating = imdbRating.orEmpty())
        }
        cacheFullDetailsItem(item)
        return item
    }

    /**
     * Get TV show details (cached)
     */
    suspend fun getTvDetails(tvId: Int): MediaItem {
        val cacheKey = "tv_$tvId"
        getFromCache(detailsCache, cacheKey)?.let { cached ->
            if (cacheKey in fullDetailsCacheKeys) {
                if (cached.imdbRating.isNotBlank()) return cached
                val imdbRating = getImdbRating(MediaType.TV, tvId)
                if (!imdbRating.isNullOrBlank()) {
                    return cached.copy(imdbRating = imdbRating).also { cacheFullDetailsItem(it) }
                }
                return cached
            }
        }

        val item = coroutineScope {
            val detailsDeferred = async { tmdbApi.getTvDetails(tvId, apiKey, language = contentLanguage) }
            val externalIdsDeferred = async { resolveExternalIds(MediaType.TV, tvId) }

            val details = detailsDeferred.await()
            val imdbId = externalIdsDeferred.await()?.imdbId?.also { cacheImdbId(MediaType.TV, tvId, it) }
            val imdbRating = imdbId?.let { getImdbRating(MediaType.TV, tvId, it) }
            details.toMediaItem().copy(imdbRating = imdbRating.orEmpty())
        }
        cacheFullDetailsItem(item)
        return item
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
                if (e is kotlinx.coroutines.CancellationException) throw e

                emptySet<String>()
            }
        }
        val hasShowWatchedData = watchedEpisodes.any { it.startsWith("show_tmdb:$tvId:") }

        // Re-apply watched status on cached episodes so stale season cache doesn't hide badges.
        if (cachedEpisodes != null) {
            val episodeImdbRatings = if (cachedEpisodes.any { it.imdbRating.isBlank() }) {
                getSeasonEpisodeImdbRatings(
                    tvId = tvId,
                    seasonNumber = seasonNumber,
                    episodeNumbers = cachedEpisodes.map { it.episodeNumber }
                )
            } else {
                emptyMap()
            }
            return cachedEpisodes.map { episode ->
                val episodeKey = "show_tmdb:$tvId:${episode.seasonNumber}:${episode.episodeNumber}"
                episode.copy(
                    imdbRating = episode.imdbRating.ifBlank {
                        episodeImdbRatings[episode.seasonNumber to episode.episodeNumber].orEmpty()
                    },
                    isWatched = if (hasShowWatchedData) episodeKey in watchedEpisodes else episode.isWatched
                )
            }
        }

        val season = tmdbApi.getTvSeason(tvId, seasonNumber, apiKey, language = contentLanguage)
        val episodeImdbRatings = getSeasonEpisodeImdbRatings(
            tvId = tvId,
            seasonNumber = seasonNumber,
            episodeNumbers = season.episodes.map { it.episodeNumber }
        )

        val episodes = season.episodes.map { episode ->
            val episodeKey = "show_tmdb:$tvId:$seasonNumber:${episode.episodeNumber}"
            episode.toEpisode().copy(
                imdbRating = episodeImdbRatings[seasonNumber to episode.episodeNumber].orEmpty(),
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
        val cacheKey = "${mediaType}_logo_$mediaId"
        if (logoCache.containsKey(cacheKey)) {
            getFromCache(logoCache, cacheKey)?.let { return it }
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

    /** Instant synchronous peek into the in-memory logo cache. */
    fun peekCachedLogoUrl(mediaType: MediaType, mediaId: Int): String? {
        val cacheKey = "${mediaType}_logo_$mediaId"
        return if (logoCache.containsKey(cacheKey)) getFromCache(logoCache, cacheKey) else null
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
            // If language-specific request returned no YouTube videos, fall back to English
            if (results.none { it.site == "YouTube" } && !contentLanguage.isNullOrBlank()) {
                results = tmdbApi.getVideos(type, mediaId, apiKey, language = null).results
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

    /**
     * Search people and expose their known-for media as result rows.
     *
     * TMDB multi-search already returns person hits, but normal title search
     * cannot display a person card. Returning rows keeps actor/director queries
     * useful without changing the media-card detail flow.
     */
    suspend fun searchPeopleKnownFor(query: String, maxPeople: Int = 3): List<PersonMediaSearchResult> {
        val trimmed = query.trim()
        if (trimmed.length < 2) return emptyList()

        val response = tmdbApi.searchMulti(apiKey, trimmed, language = contentLanguage)
        val people = response.results
            .asSequence()
            .filter { it.mediaType == "person" && it.id > 0 && !it.name.isNullOrBlank() }
            .distinctBy { it.id }
            .sortedByDescending { it.popularity }
            .take(maxPeople)
            .toList()

        val rows = people.mapNotNull { person ->
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
                .ifEmpty {
                    runCatching { getPersonDetails(person.id).knownFor }.getOrDefault(emptyList())
                }

            if (knownForItems.isEmpty()) {
                null
            } else {
                PersonMediaSearchResult(
                    personId = person.id,
                    name = person.name.orEmpty(),
                    items = knownForItems
                )
            }
        }

        rows.flatMap { it.items }.takeIf { it.isNotEmpty() }?.let(::cacheItems)
        return rows
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
        return "android.resource://com.arvio.tv/$resId"
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
            .header("User-Agent", OkHttpProvider.userAgentOr("Mozilla/5.0 (Android TV; ARVIO)"))
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
        title = title ?: name ?: "Unknown",
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
        imdbRating = "",
        tmdbRating = formatTmdbRating(voteAverage),
        mediaType = MediaType.MOVIE,
        image = posterPath?.let { "${Constants.IMAGE_BASE}$it" }
            ?: backdropPath?.let { "${Constants.BACKDROP_BASE}$it" }
            ?: "",
        backdrop = backdropPath?.let { "${Constants.BACKDROP_BASE_LARGE}$it" },
        originalLanguage = originalLanguage,
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
        title = name,
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
