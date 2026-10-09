package com.arflix.tv.data.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.arflix.tv.R
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

internal fun normalizeWatchlistArtworkUrl(rawValue: String?, isBackdrop: Boolean): String? {
    val value = rawValue?.trim()?.takeIf { it.isNotEmpty() } ?: return null
    if (
        value.startsWith("http://", ignoreCase = true) ||
        value.startsWith("https://", ignoreCase = true) ||
        value.startsWith("content://", ignoreCase = true) ||
        value.startsWith("file://", ignoreCase = true) ||
        value.startsWith("data:", ignoreCase = true)
    ) {
        return value
    }
    if (value.startsWith("//")) return "https:$value"
    return if (value.startsWith('/')) {
        val base = if (isBackdrop) Constants.BACKDROP_BASE_LARGE else Constants.IMAGE_BASE
        "$base$value"
    } else {
        value
    }
}

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
    private fun watchlistKeyFor(profileId: String) = profileManager.profileStringKeyFor(profileId, "local_watchlist_v1")
    private fun changesKeyFor(profileId: String) = profileManager.profileStringKeyFor(profileId, "local_watchlist_changes_v1")
    private val mutationMutex = Mutex()

    // In-memory cache for quick lookups
    private val keyCache = mutableSetOf<String>()
    private val itemsCache = mutableListOf<MediaItem>()
    private val _watchlistItems = MutableStateFlow<List<MediaItem>>(emptyList())
    val watchlistItems: StateFlow<List<MediaItem>> = _watchlistItems.asStateFlow()

    private var cacheLoaded = false
    @Volatile private var cacheProfileId: String? = null
    private val cacheMutex = Mutex()

    // Limit parallel TMDB requests
    private val tmdbSemaphore = Semaphore(5)

    private fun cacheKey(mediaType: MediaType, tmdbId: Int): String {
        return "${mediaType.name.lowercase()}:$tmdbId"
    }

    /**
     * Get cached watchlist items instantly
     */
    fun getCachedItems(): List<MediaItem> =
        if (cacheProfileId == profileManager.getProfileIdSync()) _watchlistItems.value else emptyList()

    /**
     * Load locally stored watchlist items without waiting for TMDB enrichment.
     * This is the fast path for screens: posters/details can be refined later,
     * but the page should never block on network work just to show saved items.
     */
    suspend fun getLocalWatchlistItems(): List<MediaItem> {
        val profileId = profileManager.getProfileIdSync()
        return withContext(Dispatchers.IO) {
            mutationMutex.withLock {
                publishState(profileId, exportSyncStateForProfile(profileId))
            }
        }
    }

    /**
     * Check if an item is in watchlist
     */
    suspend fun isInWatchlist(mediaType: MediaType, tmdbId: Int): Boolean {
        val profileId = profileManager.getProfileIdSync()
        if (!cacheLoaded || cacheProfileId != profileId) {
            loadKeyCacheQuick(profileId)
        }
        return cacheMutex.withLock {
            cacheProfileId == profileId && profileManager.getProfileIdSync() == profileId &&
                keyCache.contains(cacheKey(mediaType, tmdbId))
        }
    }

    /**
     * Quick cache load - just loads keys for fast lookup
     */
    private suspend fun loadKeyCacheQuick(profileId: String) {
        try {
            mutationMutex.withLock {
                val state = exportSyncStateForProfile(profileId)
                cacheMutex.withLock cache@ {
                    if (profileManager.getProfileIdSync() != profileId) return@cache
                    if (cacheProfileId != profileId) {
                        itemsCache.clear()
                        _watchlistItems.value = emptyList()
                    }
                    keyCache.clear()
                    state.items.forEach { keyCache.add(watchlistItemKey(it)) }
                    cacheProfileId = profileId
                    cacheLoaded = true
                }
            }
        } catch (error: kotlinx.coroutines.CancellationException) {
            throw error
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
        val profileId = profileManager.getProfileIdSync()
        mutationMutex.withLock {
            val localItem = LocalWatchlistItem(
                tmdbId = tmdbId,
                mediaType = if (mediaType == MediaType.TV) "tv" else "movie",
                title = mediaItem?.title ?: "",
                posterPath = mediaItem?.image,
                backdropPath = mediaItem?.backdrop,
                addedAt = System.currentTimeMillis()
            )

            mutateMembership(profileId, localItem, removed = false)
        }
    }

    /**
     * Remove item from watchlist
     */
    suspend fun removeFromWatchlist(mediaType: MediaType, tmdbId: Int) {
        val profileId = profileManager.getProfileIdSync()
        mutationMutex.withLock {
            val typeStr = if (mediaType == MediaType.TV) "tv" else "movie"
            mutateMembership(profileId, LocalWatchlistItem(tmdbId, typeStr, ""), removed = true)
        }
    }

    private suspend fun mutateMembership(profileId: String, item: LocalWatchlistItem, removed: Boolean) {
        val key = watchlistItemKey(item)
        var state = WatchlistSyncState()
        context.traktDataStore.edit { prefs ->
            val current = readSyncState(prefs, profileId)
            val change = nextWatchlistChange(current.changes[key], removed, System.currentTimeMillis())
            val items = current.items.filterNot { watchlistItemKey(it) == key } + if (removed) emptyList() else listOf(item)
            state = mergeWatchlistStates(WatchlistSyncState(items, current.changes + (key to change)), WatchlistSyncState())
            prefs[watchlistKeyFor(profileId)] = gson.toJson(state.items)
            prefs[changesKeyFor(profileId)] = gson.toJson(state.changes)
        }
        // A user edit during a remote restore must still schedule its own upload.
        invalidationBus.markUserDirty(CloudSyncScope.WATCHLIST, profileId, "watchlist membership")
        publishState(profileId, state)
    }

    private suspend fun publishState(
        profileId: String,
        state: WatchlistSyncState,
        enrichedItems: List<MediaItem> = emptyList()
    ): List<MediaItem> = cacheMutex.withLock {
        if (profileManager.getProfileIdSync() != profileId) return@withLock emptyList()
        val cached = if (cacheProfileId == profileId) itemsCache.toList() else emptyList()
        val enriched = (cached + enrichedItems).associateBy { cacheKey(it.mediaType, it.id) }
        val visible = state.items.map { raw ->
            val metadata = enriched[watchlistItemKey(raw)]
            metadata?.copy(
                title = raw.title.ifBlank { metadata.title },
                image = normalizeWatchlistArtworkUrl(raw.posterPath, isBackdrop = false) ?: metadata.image,
                backdrop = normalizeWatchlistArtworkUrl(raw.backdropPath, isBackdrop = true) ?: metadata.backdrop,
                addedAt = raw.addedAt,
                sourceOrder = raw.sourceOrder
            ) ?: raw.toBasicMediaItem()
        }
        itemsCache.clear()
        itemsCache.addAll(visible)
        keyCache.clear()
        state.items.forEach { keyCache.add(watchlistItemKey(it)) }
        _watchlistItems.value = visible
        cacheProfileId = profileId
        cacheLoaded = true
        visible
    }

    /**
     * Get all watchlist items enriched with TMDB data
     */
    suspend fun getWatchlistItems(): List<MediaItem> = getWatchlistItemsForProfile(profileManager.getProfileIdSync())

    private suspend fun getWatchlistItemsForProfile(profileId: String): List<MediaItem> {
        return withContext(Dispatchers.IO) {
            val rawItems = mutationMutex.withLock {
                if (profileManager.getProfileIdSync() != profileId) return@withContext emptyList()
                val cached = cacheMutex.withLock {
                    if (cacheProfileId == profileId && itemsCache.isNotEmpty()) itemsCache.toList() else null
                }
                if (cached != null) return@withContext cached
                val state = exportSyncStateForProfile(profileId)
                publishState(profileId, state)
                state.items
            }
            if (rawItems.isEmpty()) return@withContext emptyList()

            // Network requests do not hold the membership lock. Re-read persisted
            // membership under that lock before applying their metadata or publishing.
            val enrichedItems = coroutineScope {
                rawItems.map { item ->
                    async {
                        tmdbSemaphore.withPermit { enrichWatchlistItem(item) }
                    }
                }.awaitAll().filterNotNull()
            }
            mutationMutex.withLock {
                persistEnrichedArtwork(profileId, enrichedItems)
                publishState(profileId, exportSyncStateForProfile(profileId), enrichedItems)
            }
        }
    }

    /**
     * Force refresh watchlist items
     */
    suspend fun refreshWatchlistItems(): List<MediaItem> {
        val profileId = profileManager.getProfileIdSync()
        return withContext(Dispatchers.IO) {
            mutationMutex.withLock {
                if (profileManager.getProfileIdSync() != profileId) return@withContext emptyList()
                cacheMutex.withLock { itemsCache.clear() }
            }
            getWatchlistItemsForProfile(profileId)
        }
    }

    /**
     * Reorder the local watchlist to match Trakt's newest-first list.
     * Drops stale cache-only matches, while preserving explicit local membership
     * changes that may have happened after the provider request started.
     */
    suspend fun syncFromTraktOrder(
        traktItems: List<MediaItem>,
        profileId: String = profileManager.getProfileIdSync()
    ) = mutationMutex.withLock {
        val existingState = exportSyncStateForProfile(profileId)
        val existing = existingState.items
        val existingByKey = existing.associateBy { "${it.mediaType}:${it.tmdbId}" }
        val changes = existingState.changes

        val ordered = mutableListOf<LocalWatchlistItem>()

        // Trakt items are already newest-first by listed_at.
        val orderedTraktItems = traktItems.toTraktOrder()
        for ((index, item) in orderedTraktItems.withIndex()) {
            val typeStr = if (item.mediaType == MediaType.TV) "tv" else "movie"
            val key = "$typeStr:${item.id}"
            // A provider refresh updates cached metadata, never invents a membership operation.
            if (changes[key]?.removed == true) continue
            val local = existingByKey[key]
            val traktOrderAddedAt = item.addedAt.takeIf { it > 0L } ?: (System.currentTimeMillis() - index)
            ordered.add(
                local?.copy(
                    title = item.title.ifBlank { local.title },
                    posterPath = normalizeWatchlistArtworkUrl(item.image, isBackdrop = false)
                        ?: normalizeWatchlistArtworkUrl(local.posterPath, isBackdrop = false),
                    backdropPath = normalizeWatchlistArtworkUrl(item.backdrop, isBackdrop = true)
                        ?: normalizeWatchlistArtworkUrl(local.backdropPath, isBackdrop = true),
                    addedAt = traktOrderAddedAt,
                    sourceOrder = index
                ) ?: LocalWatchlistItem(
                    tmdbId = item.id,
                    mediaType = typeStr,
                    title = item.title,
                    posterPath = normalizeWatchlistArtworkUrl(item.image, isBackdrop = false),
                    backdropPath = normalizeWatchlistArtworkUrl(item.backdrop, isBackdrop = true),
                    addedAt = traktOrderAddedAt,
                    sourceOrder = index
                )
            )
        }

        // The provider request may have started before a local add completed. Preserve explicit
        // membership; absence in a refresh is not a user removal operation.
        val orderedKeys = ordered.map(::watchlistItemKey).toSet()
        ordered.addAll(existing.filter {
            val key = watchlistItemKey(it)
            key !in orderedKeys && changes[key]?.removed == false
        })
        val state = mergeWatchlistStates(WatchlistSyncState(ordered, changes), WatchlistSyncState())
        context.traktDataStore.edit { prefs ->
            prefs[watchlistKeyFor(profileId)] = gson.toJson(state.items)
            prefs[changesKeyFor(profileId)] = gson.toJson(state.changes)
        }
        invalidationBus.markDirty(CloudSyncScope.WATCHLIST, profileId, "sync watchlist order")
        publishState(profileId, state)
        Unit
    }

    /**
     * Clear all caches (call on profile switch)
     */
    fun clearWatchlistCache() {
        cacheProfileId = null
        keyCache.clear()
        itemsCache.clear()
        _watchlistItems.value = emptyList()
        cacheLoaded = false
    }

    suspend fun exportWatchlistForProfile(profileId: String): List<LocalWatchlistItem> =
        exportSyncStateForProfile(profileId).items

    suspend fun exportSyncStateForProfile(profileId: String): WatchlistSyncState {
        val safeProfileId = profileId.trim().ifBlank { "default" }
        // Read both values from the same DataStore snapshot.
        return readSyncState(context.traktDataStore.data.first(), safeProfileId)
    }

    private fun readSyncState(
        prefs: androidx.datastore.preferences.core.Preferences,
        profileId: String
    ): WatchlistSyncState {
        val changesJson = prefs[changesKeyFor(profileId)]
        val changes: Map<String, WatchlistChange> = if (changesJson.isNullOrBlank()) emptyMap() else
            gson.fromJson(changesJson, WatchlistRepoTypeTokens.changesType) ?: emptyMap()
        return mergeWatchlistStates(
            WatchlistSyncState(parseWatchlistItems(prefs[watchlistKeyFor(profileId)]), changes),
            WatchlistSyncState()
        )
    }

    /** Returns true if this device still has membership operations absent from the cloud. */
    suspend fun importWatchlistForProfile(
        profileId: String,
        cloudItems: List<LocalWatchlistItem>,
        cloudChanges: Map<String, WatchlistChange> = emptyMap()
    ): Boolean = mutationMutex.withLock {
        val safeProfileId = profileId.trim().ifBlank { "default" }
        val cloud = mergeWatchlistStates(WatchlistSyncState(cloudItems, cloudChanges), WatchlistSyncState())
        var merged = cloud
        context.traktDataStore.edit { prefs ->
            // Reconcile at write time: a concurrent removal must never be replaced by an old read.
            merged = mergeWatchlistStates(readSyncState(prefs, safeProfileId), cloud)
            prefs[watchlistKeyFor(safeProfileId)] = gson.toJson(merged.items)
            prefs[changesKeyFor(safeProfileId)] = gson.toJson(merged.changes)
        }
        publishState(safeProfileId, merged)
        merged.changes != cloud.changes ||
            merged.items.any { local -> cloud.items.none { watchlistItemKey(it) == watchlistItemKey(local) } }
    }

    /**
     * Artwork is cache metadata, not a user watchlist change. Persist it locally so
     * cold starts have thumbnails without creating a cloud-sync write on every load.
     */
    private suspend fun persistEnrichedArtwork(profileId: String, enrichedItems: List<MediaItem>) {
        if (enrichedItems.isEmpty()) return

        val enrichedByKey = enrichedItems.associateBy { item ->
            val type = if (item.mediaType == MediaType.TV) "tv" else "movie"
            "$type:${item.id}"
        }
        val key = watchlistKeyFor(profileId)

        context.traktDataStore.edit { prefs ->
            val currentItems = readSyncState(prefs, profileId).items
            val updatedItems = currentItems.map { raw ->
                val enriched = enrichedByKey["${raw.mediaType}:${raw.tmdbId}"]
                if (enriched == null) {
                    raw.copy(
                        posterPath = normalizeWatchlistArtworkUrl(raw.posterPath, isBackdrop = false),
                        backdropPath = normalizeWatchlistArtworkUrl(raw.backdropPath, isBackdrop = true)
                    )
                } else {
                    raw.copy(
                        title = enriched.title.ifBlank { raw.title },
                        posterPath = normalizeWatchlistArtworkUrl(enriched.image, isBackdrop = false)
                            ?: normalizeWatchlistArtworkUrl(raw.posterPath, isBackdrop = false),
                        backdropPath = normalizeWatchlistArtworkUrl(enriched.backdrop, isBackdrop = true)
                            ?: normalizeWatchlistArtworkUrl(raw.backdropPath, isBackdrop = true)
                    )
                }
            }
            if (updatedItems != currentItems) {
                prefs[key] = gson.toJson(updatedItems)
            }
        }
    }

    private fun parseWatchlistItems(json: String?): List<LocalWatchlistItem> {
        if (json.isNullOrBlank()) return emptyList()
        return try {
            gson.fromJson<List<LocalWatchlistItem>>(json, WatchlistRepoTypeTokens.listType) ?: emptyList()
        } catch (e: com.google.gson.JsonSyntaxException) {
            emptyList()
        } catch (e: IllegalStateException) {
            emptyList()
        }
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
        } catch (error: kotlinx.coroutines.CancellationException) {
            throw error
        } catch (error: Exception) {
            AppLogger.breadcrumb(
                tag = "Watchlist",
                message = "enrich_failed media_type=${item.mediaType} error=${error::class.java.simpleName}",
                severity = "warning"
            )
            item.toBasicMediaItem()
        }
    }

    private suspend fun enrichTvShow(tmdbId: Int, apiKey: String, addedAt: Long, sourceOrder: Int): MediaItem {
        val details = tmdbApi.getTvDetails(tmdbId, apiKey)
        return MediaItem(
            id = tmdbId,
            title = details.name,
            subtitle = context.getString(R.string.component_label_tv_series),
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
            subtitle = context.getString(R.string.movie),
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
            subtitle = if (type == MediaType.TV) context.getString(R.string.component_label_tv_series) else context.getString(R.string.movie),
            overview = "",
            year = "",
            mediaType = type,
            image = normalizeWatchlistArtworkUrl(posterPath, isBackdrop = false).orEmpty(),
            backdrop = normalizeWatchlistArtworkUrl(backdropPath, isBackdrop = true),
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

private object WatchlistRepoTypeTokens {
    val changesType = TypeToken.getParameterized(Map::class.java, String::class.java, WatchlistChange::class.java).type
    val listType = TypeToken.getParameterized(MutableList::class.java, LocalWatchlistItem::class.java).type
}
