package com.arflix.tv.data.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.arflix.tv.data.api.TmdbApi
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
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
    val addedAt: Long = System.currentTimeMillis()
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
    private val tmdbApi: TmdbApi
) {
    private val gson = Gson()

    // Profile-scoped DataStore key
    private fun watchlistKey() = profileManager.profileStringKey("local_watchlist_v1")

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
        } catch (_: Exception) {}
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
            if (mediaItem != null && itemsCache.none { it.id == tmdbId && it.mediaType == mediaType }) {
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
     * Clear all caches (call on profile switch)
     */
    fun clearWatchlistCache() {
        keyCache.clear()
        itemsCache.clear()
        _watchlistItems.value = emptyList()
        cacheLoaded = false
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
            gson.fromJson(json, type) ?: emptyList()
        } catch (_: Exception) {
            emptyList()
        }
    }

    /**
     * Save watchlist items to DataStore
     */
    private suspend fun saveWatchlist(items: List<LocalWatchlistItem>) {
        try {
            val json = gson.toJson(items)
            context.traktDataStore.edit { prefs ->
                prefs[watchlistKey()] = json
            }
        } catch (_: Exception) {}
    }

    /**
     * Enrich a watchlist item with TMDB data
     */
    private suspend fun enrichWatchlistItem(item: LocalWatchlistItem): MediaItem? {
        val apiKey = Constants.TMDB_API_KEY
        return try {
            if (item.mediaType == "tv") {
                val details = tmdbApi.getTvDetails(item.tmdbId, apiKey)
                MediaItem(
                    id = item.tmdbId,
                    title = details.name,
                    subtitle = "TV Series",
                    overview = details.overview ?: "",
                    year = details.firstAirDate?.take(4) ?: "",
                    releaseDate = details.firstAirDate ?: "",
                    imdbRating = details.voteAverage?.let { String.format("%.1f", it) } ?: "",
                    duration = details.episodeRunTime?.firstOrNull()?.let { "${it}m" } ?: "",
                    mediaType = MediaType.TV,
                    image = details.posterPath?.let { "${Constants.IMAGE_BASE}$it" } ?: "",
                    backdrop = details.backdropPath?.let { "${Constants.BACKDROP_BASE_LARGE}$it" }
                )
            } else {
                val details = tmdbApi.getMovieDetails(item.tmdbId, apiKey)
                MediaItem(
                    id = item.tmdbId,
                    title = details.title,
                    subtitle = "Movie",
                    overview = details.overview ?: "",
                    year = details.releaseDate?.take(4) ?: "",
                    releaseDate = details.releaseDate ?: "",
                    imdbRating = details.voteAverage?.let { String.format("%.1f", it) } ?: "",
                    duration = details.runtime?.let { formatRuntime(it) } ?: "",
                    mediaType = MediaType.MOVIE,
                    image = details.posterPath?.let { "${Constants.IMAGE_BASE}$it" } ?: "",
                    backdrop = details.backdropPath?.let { "${Constants.BACKDROP_BASE_LARGE}$it" }
                )
            }
        } catch (_: Exception) {
            // Fallback to basic item from stored data
            MediaItem(
                id = item.tmdbId,
                title = item.title,
                subtitle = if (item.mediaType == "tv") "TV Series" else "Movie",
                overview = "",
                year = "",
                mediaType = if (item.mediaType == "tv") MediaType.TV else MediaType.MOVIE,
                image = item.posterPath ?: "",
                backdrop = item.backdropPath
            )
        }
    }

    private fun formatRuntime(runtime: Int): String {
        val hours = runtime / 60
        val mins = runtime % 60
        return if (hours > 0) "${hours}h ${mins}m" else "${mins}m"
    }
}
