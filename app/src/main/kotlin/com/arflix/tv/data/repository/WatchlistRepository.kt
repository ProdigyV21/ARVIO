package com.arflix.tv.data.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.arflix.tv.data.api.TmdbApi
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.util.AppLogger
import com.arflix.tv.util.Constants
import com.arflix.tv.util.traktDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.sync.withPermit
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Local watchlist item stored in DataStore
 */
data class LocalWatchlistItem(
    val tmdbId: Int,
    val mediaType: String,  // "tv" or "movie"
    val title: String,
    val posterPath: String? = null,
    val backdropPath: String? = null,
    val addedAt: Long = System.currentTimeMillis(),
    val sourceOrder: Int = Int.MAX_VALUE
)

/**
 * Profile-scoped local watchlist repository.
 * Each profile has its own separate watchlist stored in DataStore.
 * No authentication required - works completely offline.
 */
@Singleton
class WatchlistRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val profileManager: ProfileManager,
    private val tmdbApi: TmdbApi,
    private val invalidationBus: CloudSyncInvalidationBus
) {
    private val gson = Gson()

    // Profile-scoped DataStore key
    private fun watchlistKey() = profileManager.profileStringKey("local_watchlist_v1")
    private fun watchlistKeyFor(profileId: String) = profileManager.profileStringKeyFor(profileId, "local_watchlist_v1")

    // In-memory cache for quick lookups
    private val keyCache = mutableSetOf<String>()
    private val itemsCache = mutableListOf<MediaItem>()
    private val _watchlistItems = MutableStateFlow<List<MediaItem>>(emptyList())
    val watchlistItems: StateFlow<List<MediaItem>> = _watchlistItems.asStateFlow()

    private var cacheLoaded = false
    private val cacheMutex = Mutex()

    // Limit parallel TMDB requests
    private val tmdbSemaphore = Semaphore(5)

    private fun cacheKey(mediaType: MediaType, tmdbId: Int): String {
        return "${mediaType.name.lowercase()}:$tmdbId"
    }

    /**
     * Get cached watchlist items instantly
     */
    fun getCachedItems(): List<MediaItem> = itemsCache.toList()

    /**
     * Check if an item is in watchlist
     */
    suspend fun isInWatchlist(mediaType: MediaType, tmdbId: Int): Boolean {
        if (!cacheLoaded) {
            loadKeyCacheQuick()
        }
        return keyCache.contains(cacheKey(mediaType, tmdbId))
    }

    /**
     * Quick cache load - just loads keys for fast lookup
     */
    private suspend fun loadKeyCacheQuick() {
        try {
            val items = loadWatchlistRaw()
            cacheMutex.withLock {
                keyCache.clear()
                items.forEach { item ->
                    val type = if (item.mediaType == "tv") MediaType.TV else MediaType.MOVIE
                    keyCache.add(cacheKey(type, item.tmdbId))
                }
                cacheLoaded = true
            }
        } catch (error: Exception) {
            AppLogger.recordException(
                throwable = error,
                context = mapOf(
                    "error_area" to "WatchlistRepository",
                    "watchlist_phase" to "load_key_cache"
                )
            )
        }
    }

    /**
     * Add item to watchlist
     */
    suspend fun addToWatchlist(mediaType: MediaType, tmdbId: Int, mediaItem: MediaItem? = null) {
        val key = cacheKey(mediaType, tmdbId)

        // Create local item
        val localItem = LocalWatchlistItem(
            tmdbId = tmdbId,
            mediaType = if (mediaType == MediaType.TV) "tv" else "movie",
            title = mediaItem?.title ?: "",
            posterPath = mediaItem?.image,
            backdropPath = mediaItem?.backdrop,
            addedAt = System.currentTimeMillis()
        )

        // Load existing items
        val existingItems = loadWatchlistRaw().toMutableList()

        // Remove if already exists (will re-add at front)
        existingItems.removeAll { it.tmdbId == tmdbId && it.mediaType == localItem.mediaType }

        // Add to front (most recent)
        existingItems.add(0, localItem)

        // Save to DataStore
        saveWatchlist(existingItems)

        // Update in-memory cache
        cacheMutex.withLock {
            keyCache.add(key)
            itemsCache.removeAll { it.id == tmdbId && it.mediaType == mediaType }
            if (mediaItem != null) {
                itemsCache.add(0, mediaItem)
                _watchlistItems.value = itemsCache.toList()
            }
            cacheLoaded = true
        }
    }

    /**
     * Remove item from watchlist
     */
    suspend fun removeFromWatchlist(mediaType: MediaType, tmdbId: Int) {
        val key = cacheKey(mediaType, tmdbId)
        val typeStr = if (mediaType == MediaType.TV) "tv" else "movie"

        // Load existing items
        val existingItems = loadWatchlistRaw().toMutableList()

        // Remove the item
        existingItems.removeAll { it.tmdbId == tmdbId && it.mediaType == typeStr }

        // Save to DataStore
        saveWatchlist(existingItems)

        // Update in-memory cache
        cacheMutex.withLock {
            keyCache.remove(key)
            itemsCache.removeAll { it.id == tmdbId && it.mediaType == mediaType }
            _watchlistItems.value = itemsCache.toList()
        }
    }

    /**
     * Get all watchlist items enriched with TMDB data
     */
    suspend fun getWatchlistItems(): List<MediaItem> = withContext(Dispatchers.IO) {
        // Return cached items if available
        if (itemsCache.isNotEmpty()) {
            return@withContext itemsCache.toList()
        }

        // Load and enrich items
        val rawItems = loadWatchlistRaw()
        if (rawItems.isEmpty()) {
            cacheMutex.withLock {
                itemsCache.clear()
                keyCache.clear()
                _watchlistItems.value = emptyList()
                cacheLoaded = true
            }
            return@withContext emptyList()
        }

        val instantItems = rawItems.map { it.toBasicMediaItem() }
        cacheMutex.withLock {
            keyCache.clear()
            instantItems.forEach { item ->
                keyCache.add(cacheKey(item.mediaType, item.id))
            }
            _watchlistItems.value = instantItems
            cacheLoaded = true
        }

        // Enrich items with TMDB data in parallel
        val enrichedItems = coroutineScope {
            rawItems.map { item ->
                async {
                    tmdbSemaphore.withPermit {
                        enrichWatchlistItem(item)
                    }
                }
            }.awaitAll().filterNotNull()
        }

        // Update cache
        cacheMutex.withLock {
            itemsCache.clear()
            itemsCache.addAll(enrichedItems)
            keyCache.clear()
            enrichedItems.forEach { item ->
                keyCache.add(cacheKey(item.mediaType, item.id))
            }
            _watchlistItems.value = enrichedItems
            cacheLoaded = true
        }

        enrichedItems
    }

    /**
     * Force refresh watchlist items
     */
    suspend fun refreshWatchlistItems(): List<MediaItem> = withContext(Dispatchers.IO) {
        // Clear cache to force reload
        cacheMutex.withLock {
            itemsCache.clear()
        }
        getWatchlistItems()
    }

    /**
     * Reorder the local watchlist to match Trakt's newest-first list.
     * Mirrors Trakt's newest-first order and drops stale local entries. Keeping
     * local-only items here lets old bad title-search matches survive forever
     * after Trakt has the correct IDs.
     */
    suspend fun syncFromTraktOrder(traktItems: List<MediaItem>) = withContext(Dispatchers.IO) {
        val existing = loadWatchlistRaw()
        val existingByKey = existing.associateBy { "${it.mediaType}:${it.tmdbId}" }

        val ordered = mutableListOf<LocalWatchlistItem>()

        // Trakt items are already newest-first by listed_at.
        val orderedTraktItems = traktItems.toTraktOrder()
        for ((index, item) in orderedTraktItems.withIndex()) {
            val typeStr = if (item.mediaType == MediaType.TV) "tv" else "movie"
            val key = "$typeStr:${item.id}"
            val local = existingByKey[key]
            val traktOrderAddedAt = item.addedAt.takeIf { it > 0L } ?: (System.currentTimeMillis() - index)
            ordered.add(
                local?.copy(
                    title = item.title.ifBlank { local.title },
                    posterPath = item.image.ifBlank { local.posterPath },
                    backdropPath = item.backdrop ?: local.backdropPath,
                    addedAt = traktOrderAddedAt,
                    sourceOrder = index
                ) ?: LocalWatchlistItem(
                    tmdbId = item.id,
                    mediaType = typeStr,
                    title = item.title,
                    posterPath = item.image,
                    backdropPath = item.backdrop,
                    addedAt = traktOrderAddedAt,
                    sourceOrder = index
                )
            )
        }

        saveWatchlist(ordered)

        // Invalidate enriched cache so the UI picks up the new order on next refresh.
        cacheMutex.withLock {
            itemsCache.clear()
            keyCache.clear()
            ordered.forEach { raw ->
                val type = if (raw.mediaType == "tv") MediaType.TV else MediaType.MOVIE
                keyCache.add(cacheKey(type, raw.tmdbId))
            }
            _watchlistItems.value = ordered.map { it.toBasicMediaItem() }
            cacheLoaded = true
        }
    }

    /**
     * Clear all caches (call on profile switch)
     */
    fun clearWatchlistCache() {
        keyCache.clear()
        itemsCache.clear()
        _watchlistItems.value = emptyList()
        cacheLoaded = false
    }

    suspend fun exportWatchlistForProfile(profileId: String): List<LocalWatchlistItem> {
        val safeProfileId = profileId.trim().ifBlank { "default" }
        return try {
            val prefs = context.traktDataStore.data.first()
            val json = prefs[watchlistKeyFor(safeProfileId)] ?: return emptyList()
            val type = TypeToken.getParameterized(
                MutableList::class.java,
                LocalWatchlistItem::class.java
            ).type
            gson.fromJson<List<LocalWatchlistItem>>(json, type) ?: emptyList()
        } catch (error: Exception) {
            AppLogger.recordException(
                throwable = error,
                context = mapOf(
                    "error_area" to "WatchlistRepository",
                    "watchlist_phase" to "export_profile"
                )
            )
            emptyList()
        }
    }

    suspend fun importWatchlistForProfile(profileId: String, items: List<LocalWatchlistItem>) {
        val safeProfileId = profileId.trim().ifBlank { "default" }
        val json = runCatching { gson.toJson(items) }.getOrDefault("[]")
        context.traktDataStore.edit { prefs ->
            prefs[watchlistKeyFor(safeProfileId)] = json
        }
        invalidationBus.markDirty(CloudSyncScope.WATCHLIST, safeProfileId, "import watchlist")
        if (profileManager.getProfileIdSync() == safeProfileId) {
            clearWatchlistCache()
        }
    }

    /**
     * Load raw watchlist items from DataStore
     */
    private suspend fun loadWatchlistRaw(): List<LocalWatchlistItem> {
        return try {
            val prefs = context.traktDataStore.data.first()
            val json = prefs[watchlistKey()] ?: return emptyList()
            val type = TypeToken.getParameterized(
                MutableList::class.java,
                LocalWatchlistItem::class.java
            ).type
            (gson.fromJson<List<LocalWatchlistItem>>(json, type) ?: emptyList())
                .sortedWith(compareBy<LocalWatchlistItem> { it.sourceOrder }.thenByDescending { it.addedAt })
        } catch (error: Exception) {
            AppLogger.recordException(
                throwable = error,
                context = mapOf(
                    "error_area" to "WatchlistRepository",
                    "watchlist_phase" to "load_raw"
                )
            )
            emptyList()
        }
    }

    /**
     * Save watchlist items to DataStore
     */
    private suspend fun saveWatchlist(items: List<LocalWatchlistItem>) {
        val json = gson.toJson(items)
        context.traktDataStore.edit { prefs ->
            prefs[watchlistKey()] = json
        }
        invalidationBus.markDirty(CloudSyncScope.WATCHLIST, profileManager.getProfileIdSync(), "save watchlist")
    }

    /**
     * Enrich a watchlist item with TMDB data
     */
    private suspend fun enrichWatchlistItem(item: LocalWatchlistItem): MediaItem? {
        val apiKey = Constants.TMDB_API_KEY
        return try {
            if (item.mediaType == "tv") {
                enrichTvShow(item.tmdbId, apiKey, item.addedAt, item.sourceOrder)
            } else {
                enrichMovie(item.tmdbId, apiKey, item.addedAt, item.sourceOrder)
            }
        } catch (error: Exception) {
            AppLogger.breadcrumb(
                tag = "Watchlist",
                message = "enrich_failed media_type=${item.mediaType} error=${error::class.java.simpleName}",
                severity = "warning"
            )
            // Fallback to basic item from stored data
            MediaItem(
                id = item.tmdbId,
                title = item.title,
                subtitle = if (item.mediaType == "tv") "TV Series" else "Movie",
                overview = "",
                year = "",
                mediaType = if (item.mediaType == "tv") MediaType.TV else MediaType.MOVIE,
                image = item.posterPath ?: "",
                backdrop = item.backdropPath,
                addedAt = item.addedAt,
                sourceOrder = item.sourceOrder
            )
        }
    }

    private suspend fun enrichTvShow(tmdbId: Int, apiKey: String, addedAt: Long, sourceOrder: Int): MediaItem {
        val details = tmdbApi.getTvDetails(tmdbId, apiKey)
        return MediaItem(
            id = tmdbId,
            title = details.name,
            subtitle = "TV Series",
            overview = details.overview ?: "",
            year = details.firstAirDate?.take(4) ?: "",
            releaseDate = details.firstAirDate ?: "",
            tmdbRating = details.voteAverage?.let { String.format(java.util.Locale.US, "%.1f", it) } ?: "",
            duration = details.episodeRunTime?.firstOrNull()?.let { "${it}m" } ?: "",
            mediaType = MediaType.TV,
            image = details.posterPath?.let { "${Constants.IMAGE_BASE}$it" } ?: "",
            backdrop = details.backdropPath?.let { "${Constants.BACKDROP_BASE_LARGE}$it" },
            addedAt = addedAt,
            sourceOrder = sourceOrder
        )
    }

    private suspend fun enrichMovie(tmdbId: Int, apiKey: String, addedAt: Long, sourceOrder: Int): MediaItem {
        val details = tmdbApi.getMovieDetails(tmdbId, apiKey)
        return MediaItem(
            id = tmdbId,
            title = details.title,
            subtitle = "Movie",
            overview = details.overview ?: "",
            year = details.releaseDate?.take(4) ?: "",
            releaseDate = details.releaseDate ?: "",
            tmdbRating = details.voteAverage?.let { String.format(java.util.Locale.US, "%.1f", it) } ?: "",
            duration = details.runtime?.let { formatRuntime(it) } ?: "",
            mediaType = MediaType.MOVIE,
            image = details.posterPath?.let { "${Constants.IMAGE_BASE}$it" } ?: "",
            backdrop = details.backdropPath?.let { "${Constants.BACKDROP_BASE_LARGE}$it" },
            addedAt = addedAt,
            sourceOrder = sourceOrder
        )
    }

    private fun formatRuntime(runtime: Int): String {
        val hours = runtime / 60
        val mins = runtime % 60
        return if (hours > 0) "${hours}h ${mins}m" else "${mins}m"
    }

    private fun LocalWatchlistItem.toBasicMediaItem(): MediaItem {
        val type = if (mediaType == "tv") MediaType.TV else MediaType.MOVIE
        return MediaItem(
            id = tmdbId,
            title = title,
            subtitle = if (type == MediaType.TV) "TV Series" else "Movie",
            overview = "",
            year = "",
            mediaType = type,
            image = posterPath.orEmpty(),
            backdrop = backdropPath,
            addedAt = addedAt,
            sourceOrder = sourceOrder
        )
    }

    private fun List<MediaItem>.toTraktOrder(): List<MediaItem> {
        return sortedWith(
            compareBy<MediaItem> { it.sourceOrder }
                .thenByDescending { it.addedAt }
        )
    }

}
