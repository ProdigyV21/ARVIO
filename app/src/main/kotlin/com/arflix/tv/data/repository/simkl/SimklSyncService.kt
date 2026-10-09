package com.arflix.tv.data.repository.simkl

import android.content.Context
import com.arflix.tv.data.api.SimklAddToListBody
import com.arflix.tv.data.api.SimklAddToListMovie
import com.arflix.tv.data.api.SimklAddToListShow
import com.arflix.tv.data.api.SimklAllItemsResponse
import com.arflix.tv.data.api.SimklApi
import com.arflix.tv.data.api.SimklEpisodeRef
import com.arflix.tv.data.api.SimklHistoryMovieItem
import com.arflix.tv.data.api.SimklHistoryShowItem
import com.arflix.tv.data.api.SimklIds
import com.arflix.tv.data.api.SimklMovieRef
import com.arflix.tv.data.api.SimklSeasonRef
import com.arflix.tv.data.api.SimklShowRef
import com.arflix.tv.data.api.SimklSyncHistoryBody
import com.arflix.tv.data.api.TmdbApi
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.repository.ContinueWatchingItem
import com.arflix.tv.data.repository.sync.SyncProviderStore
import com.arflix.tv.util.AppLogger
import com.arflix.tv.util.Constants
import com.google.gson.Gson
import com.google.gson.JsonElement
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.sync.withPermit
import kotlinx.coroutines.withContext
import java.io.File
import java.security.MessageDigest
import java.text.Normalizer
import javax.inject.Inject
import javax.inject.Singleton
import java.time.Instant
import java.util.Locale
import java.util.concurrent.ConcurrentHashMap
import kotlin.math.abs

@Singleton
class SimklSyncService @Inject constructor(
    private val simklApi: SimklApi,
    private val authManager: SimklAuthManager,
    private val tmdbApi: TmdbApi,
    private val syncProviderStore: SyncProviderStore,
    @ApplicationContext private val context: Context? = null
) {
    companion object {
        private const val SNAPSHOT_TTL_MS = 15 * 60 * 1000L
        private const val FAILED_SYNC_BACKOFF_MS = 60 * 1000L
        private val DIACRITICS_REGEX = Regex("\\p{M}+")
        private val NON_ALPHA_NUM_REGEX = Regex("[^a-z0-9]+")
    }

    private data class PersistedSimklSnapshot(
        val accountScope: String?,
        val watermark: String?,
        val removalTimestamps: Map<String, String?>?,
        val resolvedIds: Map<String, Int>?,
        val movies: SimklAllItemsResponse?,
        val shows: SimklAllItemsResponse?,
        val anime: SimklAllItemsResponse?,
        val playback: List<com.arflix.tv.data.api.SimklPlaybackItem>?
    )

    private val clientId: String get() = authManager.effectiveClientId
    private val gson = Gson()

    private val snapshotCacheFile: File?
        get() = context?.filesDir?.let { File(it, "simkl_snapshot_cache.json") }

    private val syncMutex = Mutex()
    private var activeTokenScope: String? = null
    private var hasInitialSnapshot = false
    private var snapshotComplete = false
    private var playbackComplete = false
    private var lastRemovalTimestamps: Map<String, String?> = emptyMap()
    private var lastActivityTimestamp: String? = null
    private var lastActivityCheckTime: Long = 0L
    private var lastPlaybackCheckTime: Long = 0L
    private var lastSyncAttemptTime: Long = 0L

    private var snapshotMovies: SimklAllItemsResponse? = null
    private var snapshotShows: SimklAllItemsResponse? = null
    private var snapshotAnime: SimklAllItemsResponse? = null
    private var snapshotPlayback: List<com.arflix.tv.data.api.SimklPlaybackItem>? = null

    private val cachedWatchedMovies = ConcurrentHashMap.newKeySet<Int>()
    private val cachedWatchedEpisodes = ConcurrentHashMap.newKeySet<String>()
    private val cachedWatchlist = mutableMapOf<Pair<MediaType, Int>, MediaItem>()
    private val cachedContinueWatching = ConcurrentHashMap<Pair<MediaType, Int>, ContinueWatchingItem>()
    private val cachedUpNext = ConcurrentHashMap<Pair<MediaType, Int>, ContinueWatchingItem>()
    private val completionTimes = mutableMapOf<String, Long>()
    private val completedShows = mutableSetOf<Int>()
    private val recentCompletions = ConcurrentHashMap<String, Long>()
    private val episodeSeasons = ConcurrentHashMap<Pair<Int, Int>, Pair<Long, com.arflix.tv.data.api.TmdbSeasonDetails>>()
    private val episodeLookups = Semaphore(4)
    private val cachedLibraryItems = mutableMapOf<String, LinkedHashMap<Pair<MediaType, Int>, MediaItem>>()
    private val resolvedExternalIds = ConcurrentHashMap<String, Int>()

    private fun episodeKey(tmdbId: Int, season: Int, episode: Int): String =
        "show_tmdb:$tmdbId:$season:$episode"

    private fun episodePrefix(tmdbId: Int): String = "show_tmdb:$tmdbId:"

    private suspend fun persistSnapshot(watermark: String?) = withContext(Dispatchers.IO) {
        val file = snapshotCacheFile ?: return@withContext
        try {
            val data = PersistedSimklSnapshot(
                accountScope = activeTokenScope,
                watermark = watermark,
                removalTimestamps = lastRemovalTimestamps,
                resolvedIds = resolvedExternalIds.toMap(),
                movies = snapshotMovies,
                shows = snapshotShows,
                anime = snapshotAnime,
                playback = snapshotPlayback
            )
            val parent = file.parentFile
            if (parent != null && !parent.exists()) {
                parent.mkdirs()
            }
            val tempFile = File(parent ?: file.parentFile, "${file.name}.tmp")
            tempFile.writeText(gson.toJson(data))
            if (tempFile.exists()) {
                val replaced = try {
                    try {
                        java.nio.file.Files.move(
                            tempFile.toPath(),
                            file.toPath(),
                            java.nio.file.StandardCopyOption.REPLACE_EXISTING,
                            java.nio.file.StandardCopyOption.ATOMIC_MOVE
                        )
                        true
                    } catch (e: java.nio.file.AtomicMoveNotSupportedException) {
                        java.nio.file.Files.move(
                            tempFile.toPath(),
                            file.toPath(),
                            java.nio.file.StandardCopyOption.REPLACE_EXISTING
                        )
                        true
                    }
                } catch (e: Exception) {
                    val fallback = tempFile.renameTo(file)
                    if (!fallback) {
                        AppLogger.w("SimklSyncService", "Atomic snapshot replacement failed: ${e.message}")
                    }
                    fallback
                }
                if (!replaced && tempFile.exists()) {
                    tempFile.delete()
                }
            }
        } catch (e: Exception) {
            AppLogger.w("SimklSyncService", "Failed to persist Simkl snapshot: ${e.message}")
        }
    }

    private suspend fun loadPersistedSnapshot(): PersistedSimklSnapshot? = withContext(Dispatchers.IO) {
        return@withContext try {
            val file = snapshotCacheFile?.takeIf { it.exists() } ?: return@withContext null
            val content = file.readText().takeIf { it.isNotBlank() } ?: return@withContext null
            gson.fromJson(content, PersistedSimklSnapshot::class.java)
        } catch (e: Exception) {
            AppLogger.w("SimklSyncService", "Failed to load persisted Simkl snapshot: ${e.message}")
            null
        }
    }

    /**
     * Follows official Simkl sync guidelines:
     * Phase 1: Fetch libraries separately without date_from on initial load (sequentially).
     * Phase 2: Check /sync/activities first. If timestamp changed, fetch delta using /sync/all-items/?date_from=...
     * Throttles background checks to once every 15 minutes unless forced.
     */
    suspend fun syncIfNeeded(force: Boolean = false): Boolean = syncMutex.withLock {
        val token = authManager.getAccessToken()
        if (token.isNullOrBlank()) {
            clearCachedState()
            return@withLock false
        }
        val tokenScope = MessageDigest.getInstance("SHA-256")
            .digest((if (token.startsWith("simkl_at_")) authManager.cacheIdentity(token) else token).toByteArray(Charsets.UTF_8)).joinToString("") { "%02x".format(it) }
        if (activeTokenScope != tokenScope) {
            clearCachedState(deletePersisted = activeTokenScope != null)
            activeTokenScope = tokenScope
        }
        val authHeader = "Bearer $token"

        if (!hasInitialSnapshot) {
            val persisted = loadPersistedSnapshot()
            if (persisted != null && persisted.accountScope == tokenScope && persisted.movies != null &&
                persisted.shows != null && persisted.anime != null
            ) {
                snapshotMovies = persisted.movies
                snapshotShows = persisted.shows
                snapshotAnime = persisted.anime
                snapshotPlayback = persisted.playback.orEmpty()
                hasInitialSnapshot = true
                snapshotComplete = true
                playbackComplete = persisted.playback != null
                lastActivityTimestamp = persisted.watermark
                lastRemovalTimestamps = persisted.removalTimestamps.orEmpty()
                resolvedExternalIds.putAll(persisted.resolvedIds.orEmpty())
                val stagedMovies = snapshotMovies ?: SimklAllItemsResponse()
                val stagedShows = snapshotShows ?: SimklAllItemsResponse()
                val stagedAnime = snapshotAnime ?: SimklAllItemsResponse()
                val stagedPlayback = snapshotPlayback.orEmpty()
                resolveMissingTmdbIds(stagedMovies, stagedShows, stagedAnime, stagedPlayback)
                rebuildCaches(stagedMovies, stagedShows, stagedAnime, stagedPlayback)
            }
        }

        val now = System.currentTimeMillis()
        if (!force && snapshotComplete && playbackComplete && now - lastActivityCheckTime < SNAPSHOT_TTL_MS) {
            return@withLock true
        }
        if (!force && now - lastSyncAttemptTime < FAILED_SYNC_BACKOFF_MS) {
            return@withLock (snapshotComplete && playbackComplete)
        }
        lastSyncAttemptTime = now

        try {
            val activities = simklApi.getActivities(authHeader, clientId)
            val currentActivityDate = activities.all
                ?: activities.movies?.all
                ?: activities.shows?.all
                ?: activities.anime?.all

            val removalTimestamps = mapOf(
                "movies" to activities.movies?.removedFromList,
                "shows" to activities.shows?.removedFromList,
                "anime" to activities.anime?.removedFromList
            )
            val removedTypes = removalTimestamps.filter { (type, timestamp) ->
                timestamp != null && timestamp != lastRemovalTimestamps[type]
            }.keys

            val libraryChanged = currentActivityDate != lastActivityTimestamp || removedTypes.isNotEmpty()
            val libraryNeedsRefresh = !snapshotComplete || libraryChanged
            val playbackNeedsRefresh = !playbackComplete || force || (now - lastPlaybackCheckTime >= SNAPSHOT_TTL_MS)

            if (libraryNeedsRefresh || playbackNeedsRefresh) {
                AppLogger.d("SimklSyncService", "Refreshing Simkl snapshot (hasInitial=$hasInitialSnapshot, current=$currentActivityDate, last=$lastActivityTimestamp, libNeeds=$libraryNeedsRefresh, pbNeeds=$playbackNeedsRefresh)")
                val outcome = refreshSnapshot(authHeader, removedTypes, refreshLibrary = libraryNeedsRefresh)
                hasInitialSnapshot = outcome.hasUsableSnapshot
                if (outcome.libraryComplete) {
                    snapshotComplete = true
                    lastActivityTimestamp = currentActivityDate
                    lastRemovalTimestamps = removalTimestamps
                    persistSnapshot(currentActivityDate)
                    syncProviderStore.setSimklWatermark(currentActivityDate)
                    lastActivityCheckTime = now
                } else if (libraryNeedsRefresh) {
                    snapshotComplete = false
                    lastActivityCheckTime = 0L
                }

                if (outcome.playbackComplete) {
                    playbackComplete = true
                    lastPlaybackCheckTime = now
                    if (!libraryNeedsRefresh && snapshotComplete) {
                        persistSnapshot(lastActivityTimestamp)
                    }
                } else {
                    playbackComplete = false
                    lastPlaybackCheckTime = 0L
                }

                outcome.libraryComplete && outcome.playbackComplete
            } else {
                AppLogger.d("SimklSyncService", "Simkl activities unchanged ($currentActivityDate). Skipping sync.")
                lastActivityCheckTime = now
                true
            }
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            AppLogger.e("SimklSyncService", "Error during Simkl sync: ${e.message}")
            false
        }
    }

    private data class SnapshotFetch<T>(val value: T?, val succeeded: Boolean)

    private data class SnapshotRefreshOutcome(
        val hasUsableSnapshot: Boolean,
        val libraryComplete: Boolean,
        val playbackComplete: Boolean
    )

    private suspend fun <T> fetchSnapshotPart(label: String, block: suspend () -> T): SnapshotFetch<T> {
        return try {
            SnapshotFetch(block(), true)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            AppLogger.e("SimklSyncService", "$label sync failed: ${e.message}")
            SnapshotFetch(null, false)
        }
    }

    private suspend fun refreshSnapshot(
        authHeader: String,
        removedTypes: Set<String>,
        refreshLibrary: Boolean = true
    ): SnapshotRefreshOutcome = coroutineScope {
        // If library doesn't need refresh, only refresh playback
        if (!refreshLibrary && hasInitialSnapshot && snapshotComplete) {
            AppLogger.d("SimklSyncService", "Retrying Simkl playback without re-fetching library snapshot")
            val playbackFetch = fetchSnapshotPart("Playback") {
                simklApi.getPlayback(authHeader, clientId)
            }
            if (playbackFetch.succeeded) {
                snapshotPlayback = playbackFetch.value
                val stagedMovies = snapshotMovies ?: SimklAllItemsResponse()
                val stagedShows = snapshotShows ?: SimklAllItemsResponse()
                val stagedAnime = snapshotAnime ?: SimklAllItemsResponse()
                val stagedPlayback = snapshotPlayback.orEmpty()
                resolveMissingTmdbIds(stagedMovies, stagedShows, stagedAnime, stagedPlayback)
                rebuildCaches(stagedMovies, stagedShows, stagedAnime, stagedPlayback)
            }
            return@coroutineScope SnapshotRefreshOutcome(
                hasUsableSnapshot = true,
                libraryComplete = true,
                playbackComplete = playbackFetch.succeeded
            )
        }

        // Phase 2: Continuous delta sync if initial snapshot already exists and watermark is present
        if (hasInitialSnapshot && !lastActivityTimestamp.isNullOrBlank()) {
            AppLogger.d("SimklSyncService", "Performing Simkl delta sync from $lastActivityTimestamp")
            val deltaFetch = fetchSnapshotPart("Delta") {
                decodeDeltaItems(simklApi.getAllItemsDelta(authHeader, clientId, dateFrom = lastActivityTimestamp!!))
            }
            // Deltas never contain tombstones. Reconcile only the types whose removal marker moved.
            val removedIds = mutableMapOf<String, Set<String>>()
            for (type in removedTypes) {
                val ids = fetchSnapshotPart("$type deletion reconciliation") {
                    val payload = simklApi.getAllItemIds(authHeader, clientId, type)
                    if (payload.isJsonObject && payload.asJsonObject.size() > 0 && !payload.asJsonObject.has(type)) {
                        throw IllegalStateException("Unexpected Simkl $type IDs response")
                    }
                    val rows = decodeAllItems(type, payload)
                    when (type) {
                        "movies" -> rows.movies.orEmpty().mapNotNull { rowKey(it.movie?.ids) }
                        "shows" -> rows.shows.orEmpty().mapNotNull { rowKey(it.show?.ids) }
                        else -> rows.anime.orEmpty().mapNotNull { rowKey(it.show?.ids) }
                    }.toSet()
                }
                if (!ids.succeeded) {
                    val playbackFetch = fetchSnapshotPart("Playback") {
                        simklApi.getPlayback(authHeader, clientId)
                    }
                    if (playbackFetch.succeeded) {
                        snapshotPlayback = playbackFetch.value
                    }
                    return@coroutineScope SnapshotRefreshOutcome(
                        hasUsableSnapshot = true,
                        libraryComplete = false,
                        playbackComplete = playbackFetch.succeeded
                    )
                }
                removedIds[type] = ids.value.orEmpty()
            }
            val playbackFetch = fetchSnapshotPart("Playback") {
                simklApi.getPlayback(authHeader, clientId)
            }
            if (deltaFetch.succeeded && deltaFetch.value != null) {
                val delta = deltaFetch.value
                val mergedMovies = mergeMovieRows(snapshotMovies?.movies.orEmpty(), delta.movies.orEmpty())
                    .filter { removedIds["movies"]?.contains(rowKey(it.movie?.ids)) != false }
                val mergedShows = mergeShowRows(snapshotShows?.shows.orEmpty(), delta.shows.orEmpty())
                    .filter { removedIds["shows"]?.contains(rowKey(it.show?.ids)) != false }
                val mergedAnime = mergeShowRows(snapshotAnime?.anime.orEmpty(), delta.anime.orEmpty())
                    .filter { removedIds["anime"]?.contains(rowKey(it.show?.ids)) != false }

                snapshotMovies = SimklAllItemsResponse(movies = mergedMovies)
                snapshotShows = SimklAllItemsResponse(shows = mergedShows)
                snapshotAnime = SimklAllItemsResponse(anime = mergedAnime)
                if (playbackFetch.succeeded) {
                    snapshotPlayback = playbackFetch.value
                }

                val stagedMovies = snapshotMovies ?: SimklAllItemsResponse()
                val stagedShows = snapshotShows ?: SimklAllItemsResponse()
                val stagedAnime = snapshotAnime ?: SimklAllItemsResponse()
                val stagedPlayback = snapshotPlayback.orEmpty()

                resolveMissingTmdbIds(stagedMovies, stagedShows, stagedAnime, stagedPlayback)
                rebuildCaches(stagedMovies, stagedShows, stagedAnime, stagedPlayback)
                return@coroutineScope SnapshotRefreshOutcome(
                    hasUsableSnapshot = true,
                    libraryComplete = deltaFetch.succeeded,
                    playbackComplete = playbackFetch.succeeded
                )
            }
            // If delta fetch failed, fall back to preserving usable snapshot or initial sync
            if (snapshotMovies != null || snapshotShows != null || snapshotAnime != null) {
                if (playbackFetch.succeeded) {
                    snapshotPlayback = playbackFetch.value
                }
                return@coroutineScope SnapshotRefreshOutcome(
                    hasUsableSnapshot = true,
                    libraryComplete = false,
                    playbackComplete = playbackFetch.succeeded
                )
            }
        }

        // Phase 1: Full bootstrap sync executed sequentially: movies -> shows -> anime -> playback
        AppLogger.d("SimklSyncService", "Performing full sequential Simkl sync")
        val movies = fetchSnapshotPart("Movies") {
            decodeAllItems("movies", simklApi.getAllItems(authHeader, clientId, "movies"))
        }
        val shows = fetchSnapshotPart("Shows") {
            decodeAllItems("shows", simklApi.getAllItems(authHeader, clientId, "shows"))
        }
        val anime = fetchSnapshotPart("Anime") {
            decodeAllItems("anime", simklApi.getAllItems(authHeader, clientId, "anime", extended = "full_anime_seasons"))
        }
        val playback = fetchSnapshotPart("Playback") {
            simklApi.getPlayback(authHeader, clientId)
        }

        if (movies.succeeded) snapshotMovies = movies.value
        if (shows.succeeded) snapshotShows = shows.value
        if (anime.succeeded) snapshotAnime = anime.value
        if (playback.succeeded) snapshotPlayback = playback.value

        val stagedMovies = snapshotMovies ?: SimklAllItemsResponse()
        val stagedShows = snapshotShows ?: SimklAllItemsResponse()
        val stagedAnime = snapshotAnime ?: SimklAllItemsResponse()
        val stagedPlayback = snapshotPlayback.orEmpty()
        val hasUsableSnapshot = snapshotMovies != null || snapshotShows != null ||
            snapshotAnime != null || snapshotPlayback != null

        if (hasUsableSnapshot) {
            resolveMissingTmdbIds(stagedMovies, stagedShows, stagedAnime, stagedPlayback)
            rebuildCaches(stagedMovies, stagedShows, stagedAnime, stagedPlayback)
        }

        SnapshotRefreshOutcome(
            hasUsableSnapshot = hasUsableSnapshot,
            libraryComplete = movies.succeeded && shows.succeeded && anime.succeeded,
            playbackComplete = playback.succeeded
        )
    }

    private fun decodeDeltaItems(payload: JsonElement): SimklAllItemsResponse {
        if (payload.isJsonObject) {
            return gson.fromJson(payload, SimklAllItemsResponse::class.java)
        }
        throw IllegalStateException("Unexpected Simkl delta response")
    }

    private fun rowKey(ids: SimklIds?): String? = ids?.let {
        it.simkl?.let { id -> "simkl:$id" }
            ?: it.tmdb?.let { id -> "tmdb:$id" }
            ?: it.tvdb?.let { id -> "tvdb:$id" }
            ?: it.imdb?.let { id -> "imdb:$id" }
    }

    private fun mergeMovieRows(
        existing: List<SimklHistoryMovieItem>,
        incoming: List<SimklHistoryMovieItem>
    ): List<SimklHistoryMovieItem> {
        val map = LinkedHashMap<String, SimklHistoryMovieItem>()
        for (item in existing) {
            val key = rowKey(item.movie?.ids)
            if (key != null) map[key] = item
        }
        for (item in incoming) {
            val key = rowKey(item.movie?.ids)
            if (key != null) map[key] = item
        }
        return map.values.toList()
    }

    private fun mergeShowRows(
        existing: List<SimklHistoryShowItem>,
        incoming: List<SimklHistoryShowItem>
    ): List<SimklHistoryShowItem> {
        val map = LinkedHashMap<String, SimklHistoryShowItem>()
        for (item in existing) {
            val key = rowKey(item.show?.ids)
            if (key != null) map[key] = item
        }
        for (item in incoming) {
            val key = rowKey(item.show?.ids)
            if (key != null) {
                val existingItem = map[key]
                val merged = if (existingItem != null && item.seasons == null && !existingItem.seasons.isNullOrEmpty()) {
                    item.copy(seasons = existingItem.seasons)
                } else {
                    item
                }
                map[key] = merged
            }
        }
        return map.values.toList()
    }

    private fun decodeAllItems(type: String, payload: JsonElement): SimklAllItemsResponse {
        if (payload.isJsonObject) {
            return gson.fromJson(payload, SimklAllItemsResponse::class.java)
        }
        if (!payload.isJsonArray) {
            throw IllegalStateException("Unexpected Simkl $type library response")
        }

        return when (type) {
            "movies" -> SimklAllItemsResponse(
                movies = payload.asJsonArray.map { gson.fromJson(it, SimklHistoryMovieItem::class.java) }
            )
            "shows" -> SimklAllItemsResponse(
                shows = payload.asJsonArray.map { gson.fromJson(it, SimklHistoryShowItem::class.java) }
            )
            "anime" -> SimklAllItemsResponse(
                anime = payload.asJsonArray.map { gson.fromJson(it, SimklHistoryShowItem::class.java) }
            )
            else -> throw IllegalArgumentException("Unsupported Simkl library type: $type")
        }
    }

    private fun rebuildCaches(
        movies: SimklAllItemsResponse,
        shows: SimklAllItemsResponse,
        anime: SimklAllItemsResponse,
        playback: List<com.arflix.tv.data.api.SimklPlaybackItem>
    ) {
        cachedWatchedMovies.clear()
        cachedWatchedEpisodes.clear()
        cachedWatchlist.clear()
        cachedContinueWatching.clear()
        cachedUpNext.clear()
        completionTimes.clear()
        completedShows.clear()
        cachedLibraryItems.clear()
        processMoviesResponse(movies)
        processShowsResponse(shows)
        processShowsResponse(anime)
        cachedUpNext.putAll(cachedContinueWatching)
        processPlayback(playback)
    }

    private fun clearCachedState(deletePersisted: Boolean = true) {
        try {
            if (deletePersisted) snapshotCacheFile?.delete()
        } catch (_: Exception) {}
        activeTokenScope = null
        hasInitialSnapshot = false
        snapshotComplete = false
        playbackComplete = false
        lastRemovalTimestamps = emptyMap()
        lastActivityTimestamp = null
        lastActivityCheckTime = 0L
        lastPlaybackCheckTime = 0L
        lastSyncAttemptTime = 0L
        snapshotMovies = null
        snapshotShows = null
        snapshotAnime = null
        snapshotPlayback = null
        resolvedExternalIds.clear()
        recentCompletions.clear()
        cachedUpNext.clear()
        completionTimes.clear()
        completedShows.clear()
        cachedWatchedMovies.clear()
        cachedWatchedEpisodes.clear()
        cachedWatchlist.clear()
        cachedContinueWatching.clear()
        cachedLibraryItems.clear()
    }

    private fun processMoviesResponse(response: SimklAllItemsResponse) {
        response.movies?.forEach { movieItem ->
            val movie = movieItem.movie ?: return@forEach
            val tmdbId = resolvedTmdbId(movie.ids, MediaType.MOVIE, movie.title, movie.year) ?: return@forEach
            val status = movieItem.status
            cacheLibraryItem(
                status = status,
                key = MediaType.MOVIE to tmdbId,
                item = MediaItem(
                    id = tmdbId,
                    title = movie.title.orEmpty(),
                    year = movie.year?.toString().orEmpty(),
                    mediaType = MediaType.MOVIE
                )
            )
            if (status == "completed") {
                cachedWatchedMovies.add(tmdbId)
                completionTimes["movie:$tmdbId"] = parseTimestamp(movieItem.lastWatchedAt)
            }
            if (status == "plantowatch") {
                cachedWatchlist[MediaType.MOVIE to tmdbId] = MediaItem(
                    id = tmdbId,
                    title = movie.title.orEmpty(),
                    mediaType = MediaType.MOVIE
                )
            } else if (status != null) {
                cachedWatchlist.remove(MediaType.MOVIE to tmdbId)
            }
        }
    }

    private fun processShowsResponse(response: SimklAllItemsResponse) {
        val allShows = (response.shows.orEmpty() + response.anime.orEmpty())
        allShows.forEach { showItem ->
            val show = showItem.show ?: return@forEach
            val showTmdb = resolvedTmdbId(show.ids, MediaType.TV, show.title, show.year) ?: return@forEach
            val status = showItem.status
            if (status == "completed") {
                completedShows.add(showTmdb)
                completionTimes["show:$showTmdb"] = parseTimestamp(showItem.lastWatchedAt)
            }
            cacheLibraryItem(
                status = status,
                key = MediaType.TV to showTmdb,
                item = MediaItem(
                    id = showTmdb,
                    title = show.title.orEmpty(),
                    year = show.year?.toString().orEmpty(),
                    mediaType = MediaType.TV
                )
            )
            if (status == "plantowatch") {
                cachedWatchlist[MediaType.TV to showTmdb] = MediaItem(
                    id = showTmdb,
                    title = show.title.orEmpty(),
                    mediaType = MediaType.TV
                )
            } else if (status != null) {
                cachedWatchlist.remove(MediaType.TV to showTmdb)
            }
            showItem.seasons?.forEach { season ->
                season.episodes.forEach { episode ->
                    val effectiveSeason = episode.tvdb?.season ?: season.number
                    val effectiveEpisode = episode.tvdb?.episode ?: episode.number
                    cachedWatchedEpisodes.add(episodeKey(showTmdb, effectiveSeason, effectiveEpisode))
                    completionTimes[episodeKey(showTmdb, effectiveSeason, effectiveEpisode)] = parseTimestamp(episode.watchedAt)
                }
            }
            // The pointer is authoritative. SIMKL may leave decoration behind when caught up.
            val pointer = parseNextToWatch(showItem.nextToWatch)
            val mappedSeasons = (showItem.mappedTvdbSeasons as? List<*>)
                ?.mapNotNull { (it as? Number)?.toInt() }.orEmpty()
            val next = pointer?.copy(
                season = if (showItem.nextToWatch?.startsWith("S", ignoreCase = true) == true) pointer.season
                    else showItem.nextToWatchInfo?.season ?: mappedSeasons.singleOrNull() ?: pointer.season,
                title = showItem.nextToWatchInfo?.title,
                date = showItem.nextToWatchInfo?.date
            )
            if (next != null && status == "watching") {
                val season = next.season ?: 1
                val episode = next.episode ?: return@forEach
                val airedTotal = ((showItem.totalEpisodesCount ?: 0) - (showItem.notAiredEpisodesCount ?: 0))
                    .coerceAtLeast(0)
                cachedContinueWatching[MediaType.TV to showTmdb] = ContinueWatchingItem(
                    id = showTmdb,
                    title = show.title.orEmpty(),
                    mediaType = MediaType.TV,
                    progress = 0,
                    season = season,
                    episode = episode,
                    episodeTitle = next.title?.takeIf { it.isNotBlank() } ?: "Episode $episode",
                    year = show.year?.toString().orEmpty(),
                    durationSeconds = show.runtime?.times(60L) ?: 0L,
                    isUpNext = true,
                    updatedAtMs = parseTimestamp(showItem.lastWatchedAt),
                    watchedEpisodes = showItem.watchedEpisodesCount ?: 0,
                    totalEpisodes = airedTotal
                )
            }
        }
    }

    private fun cacheLibraryItem(
        status: String?,
        key: Pair<MediaType, Int>,
        item: MediaItem
    ) {
        val normalized = status?.trim()?.lowercase()?.takeIf { it.isNotBlank() }?.let { if (it == "notinteresting") "dropped" else it } ?: return
        cachedLibraryItems.getOrPut(normalized) { linkedMapOf() }[key] = item
    }

    private fun parseNextToWatch(value: String?): com.arflix.tv.data.api.SimklNextToWatchInfo? {
        val raw = value?.trim()?.uppercase().orEmpty()
        if (raw.isBlank()) return null
        val match = Regex("^(?:S(\\d+))?E(\\d+)$").matchEntire(raw) ?: return null
        val season = match.groupValues[1].toIntOrNull() ?: 1
        val episode = match.groupValues[2].toIntOrNull() ?: return null
        return com.arflix.tv.data.api.SimklNextToWatchInfo(season = season, episode = episode)
    }

    private fun processPlayback(items: List<com.arflix.tv.data.api.SimklPlaybackItem>) {
        items.forEach { row ->
            val movie = row.movie
            if (movie != null) {
                val tmdbId = resolvedTmdbId(movie.ids, MediaType.MOVIE, movie.title, movie.year) ?: return@forEach
                val progress = row.progress.toInt().coerceIn(0, 100)
                if (progress !in 1..94 || isCompletedPause("movie:$tmdbId", parseTimestamp(row.pausedAt), cachedWatchedMovies.contains(tmdbId))) return@forEach
                val durationSeconds = movie.runtime?.times(60L) ?: 0L
                cachedContinueWatching[MediaType.MOVIE to tmdbId] = ContinueWatchingItem(
                    id = tmdbId,
                    title = movie.title.orEmpty(),
                    mediaType = MediaType.MOVIE,
                    progress = progress,
                    resumePositionSeconds = durationSeconds * progress / 100L,
                    durationSeconds = durationSeconds,
                    year = movie.year?.toString().orEmpty(),
                    updatedAtMs = parseTimestamp(row.pausedAt)
                )
                return@forEach
            }

            val show = row.show ?: row.anime ?: return@forEach
            val tmdbId = resolvedTmdbId(show.ids, MediaType.TV, show.title, show.year) ?: return@forEach
            val season = row.episode?.season ?: return@forEach
            val episode = row.episode.number ?: return@forEach
            val progress = row.progress.toInt().coerceIn(0, 100)
            val key = episodeKey(tmdbId, season, episode)
            val completed = completedShows.contains(tmdbId) || cachedWatchedEpisodes.contains(key)
            val completion = maxOf(completionTimes[key] ?: 0L, completionTimes["show:$tmdbId"] ?: 0L)
            if (season < 0 || episode < 1 || progress !in 1..94 ||
                isCompletedPause(key, parseTimestamp(row.pausedAt), completed, completion)) return@forEach
            val durationSeconds = show.runtime?.times(60L) ?: 0L
            cachedContinueWatching[MediaType.TV to tmdbId] = ContinueWatchingItem(
                id = tmdbId,
                title = show.title.orEmpty(),
                mediaType = MediaType.TV,
                progress = progress,
                resumePositionSeconds = durationSeconds * progress / 100L,
                durationSeconds = durationSeconds,
                season = season,
                episode = episode,
                episodeTitle = row.episode.title ?: "Episode $episode",
                year = show.year?.toString().orEmpty(),
                updatedAtMs = parseTimestamp(row.pausedAt)
            )
        }
    }

    private fun externalKey(ids: SimklIds, mediaType: MediaType): String? = when {
        !ids.imdb.isNullOrBlank() -> "${mediaType.name}:imdb:${ids.imdb}"
        !ids.tvdb.isNullOrBlank() -> "${mediaType.name}:tvdb:${ids.tvdb}"
        else -> null
    }

    private fun resolutionKey(
        ids: SimklIds,
        mediaType: MediaType,
        title: String? = null,
        year: Int? = null
    ): String? = externalKey(ids, mediaType)
        ?: ids.simkl?.let { "${mediaType.name}:simkl:$it" }
        ?: normalizeTitle(title.orEmpty()).takeIf { it.isNotBlank() }
            ?.let { "${mediaType.name}:title:$it:${year ?: 0}" }

    private fun resolvedTmdbId(
        ids: SimklIds,
        mediaType: MediaType,
        title: String? = null,
        year: Int? = null
    ): Int? = ids.tmdb ?: resolutionKey(ids, mediaType, title, year)?.let(resolvedExternalIds::get)

    private data class TmdbResolutionCandidate(
        val mediaType: MediaType,
        val ids: SimklIds,
        val title: String?,
        val year: Int?
    )

    private suspend fun resolveMissingTmdbIds(
        movies: SimklAllItemsResponse,
        shows: SimklAllItemsResponse,
        anime: SimklAllItemsResponse,
        playback: List<com.arflix.tv.data.api.SimklPlaybackItem>
    ) = coroutineScope {
        val candidates = buildList<TmdbResolutionCandidate> {
            movies.movies.orEmpty().mapNotNullTo(this) { row ->
                row.movie?.let { TmdbResolutionCandidate(MediaType.MOVIE, it.ids, it.title, it.year) }
            }
            (shows.shows.orEmpty() + shows.anime.orEmpty() + anime.shows.orEmpty() + anime.anime.orEmpty())
                .mapNotNullTo(this) { row ->
                    row.show?.let { TmdbResolutionCandidate(MediaType.TV, it.ids, it.title, it.year) }
                }
            playback.forEach { row ->
                row.movie?.let { add(TmdbResolutionCandidate(MediaType.MOVIE, it.ids, it.title, it.year)) }
                (row.show ?: row.anime)?.let {
                    add(TmdbResolutionCandidate(MediaType.TV, it.ids, it.title, it.year))
                }
            }
        }.filter { candidate ->
            candidate.ids.tmdb == null && resolutionKey(
                candidate.ids,
                candidate.mediaType,
                candidate.title,
                candidate.year
            )?.let { !resolvedExternalIds.containsKey(it) } == true
        }.distinctBy { candidate ->
            resolutionKey(candidate.ids, candidate.mediaType, candidate.title, candidate.year)
        }

        val permits = Semaphore(6)
        candidates.map { candidate ->
            async {
                permits.withPermit {
                    val key = resolutionKey(
                        candidate.ids,
                        candidate.mediaType,
                        candidate.title,
                        candidate.year
                    ) ?: return@withPermit
                    val tmdbId = resolveCandidate(candidate)
                    if (tmdbId != null) {
                        resolvedExternalIds[key] = tmdbId
                    }
                }
            }
        }.forEach { it.await() }
    }

    private suspend fun resolveCandidate(candidate: TmdbResolutionCandidate): Int? {
        val externalMatch = try {
            val result = when {
                !candidate.ids.imdb.isNullOrBlank() -> tmdbApi.findByExternalId(
                    candidate.ids.imdb,
                    Constants.TMDB_API_KEY,
                    "imdb_id"
                )
                !candidate.ids.tvdb.isNullOrBlank() -> tmdbApi.findByExternalId(
                    candidate.ids.tvdb,
                    Constants.TMDB_API_KEY,
                    "tvdb_id"
                )
                else -> null
            }
            if (candidate.mediaType == MediaType.MOVIE) {
                result?.movieResults?.maxByOrNull { it.popularity }?.id
            } else {
                result?.tvResults?.maxByOrNull { it.popularity }?.id
            }?.takeIf { it > 0 }
        } catch (e: CancellationException) {
            throw e
        } catch (_: Exception) {
            null
        }
        if (externalMatch != null) return externalMatch

        val title = candidate.title?.trim().orEmpty()
        if (title.isBlank()) return null
        return searchTmdbCandidate(candidate, constrainYear = true)
            ?: candidate.year?.let { searchTmdbCandidate(candidate, constrainYear = false) }
    }

    private suspend fun searchTmdbCandidate(
        candidate: TmdbResolutionCandidate,
        constrainYear: Boolean
    ): Int? {
        val title = candidate.title?.trim().orEmpty()
        if (title.isBlank()) return null
        val year = candidate.year.takeIf { constrainYear }
        val results = try {
            when (candidate.mediaType) {
                MediaType.MOVIE -> tmdbApi.searchMovies(
                    apiKey = Constants.TMDB_API_KEY,
                    query = title,
                    page = 1,
                    primaryReleaseYear = year,
                    year = year
                ).results
                MediaType.TV -> tmdbApi.searchTv(
                    apiKey = Constants.TMDB_API_KEY,
                    query = title,
                    page = 1,
                    firstAirDateYear = year
                ).results
            }
        } catch (e: CancellationException) {
            throw e
        } catch (_: Exception) {
            return null
        }

        val requestedTitle = normalizeTitle(title)
        return results.map { result ->
            val titleScore = listOfNotNull(
                result.title,
                result.name,
                result.originalTitle,
                result.originalName
            ).maxOfOrNull { candidateTitle ->
                val normalized = normalizeTitle(candidateTitle)
                when {
                    normalized == requestedTitle -> 120
                    normalized.isNotBlank() && requestedTitle.isNotBlank() &&
                        (normalized in requestedTitle || requestedTitle in normalized) -> 60
                    else -> 0
                }
            } ?: 0
            val resultYear = (result.releaseDate ?: result.firstAirDate)?.take(4)?.toIntOrNull()
            val yearScore = when {
                candidate.year == null || resultYear == null -> 0
                candidate.year == resultYear -> 35
                abs(candidate.year - resultYear) == 1 -> 15
                else -> -70
            }
            result to (titleScore + yearScore + result.popularity.toInt().coerceIn(0, 20))
        }.maxByOrNull { it.second }
            ?.takeIf { it.second >= 85 }
            ?.first
            ?.id
            ?.takeIf { it > 0 }
    }

    private fun normalizeTitle(value: String): String = Normalizer.normalize(value, Normalizer.Form.NFD)
        .replace(DIACRITICS_REGEX, "")
        .lowercase(Locale.US)
        .replace("&", " and ")
        .replace(NON_ALPHA_NUM_REGEX, " ")
        .trim()

    private fun parseTimestamp(value: String?): Long = runCatching {
        value?.let(Instant::parse)?.toEpochMilli()
    }.getOrNull() ?: 0L

    suspend fun getWatchedMovies(): Set<Int> {
        syncIfNeeded()
        return cachedWatchedMovies.toSet()
    }

    suspend fun getWatchedEpisodes(): Set<String> {
        syncIfNeeded()
        return cachedWatchedEpisodes.toSet()
    }

    suspend fun getWatchlistItems(): List<MediaItem> {
        syncIfNeeded()
        return cachedWatchlist.values.toList()
    }

    suspend fun getLibraryItems(status: String, forceRefresh: Boolean = false): List<MediaItem> {
        syncIfNeeded(forceRefresh)
        return cachedLibraryItems[status.trim().lowercase()]?.values?.toList().orEmpty()
    }

    suspend fun getContinueWatching(forceRefresh: Boolean = false): List<ContinueWatchingItem> {
        return getContinueWatching(forceRefresh, emptySet(), emptySet())
    }

    private fun isCompletedPause(key: String, pausedAt: Long, watched: Boolean, completion: Long = completionTimes[key] ?: 0L): Boolean {
        val completedAt = maxOf(completion, recentCompletions[key] ?: 0L)
        if (!watched && completedAt == 0L) return false
        // 1970-01-01T00:00:01Z is SIMKL's unknown-history placeholder, not proof of a rewatch.
        return completedAt <= 1000L || pausedAt <= completedAt
    }

    private data class ContinueWatchingSnapshot(
        val scope: String?,
        val items: List<ContinueWatchingItem>,
        val upNext: Map<Pair<MediaType, Int>, ContinueWatchingItem>,
        val watched: Set<String>
    )

    suspend fun getContinueWatching(
        forceRefresh: Boolean,
        localWatchedMovies: Set<Int>,
        localWatchedEpisodes: Set<String>
    ): List<ContinueWatchingItem> {
        val synced = syncIfNeeded(forceRefresh)
        val snapshot = syncMutex.withLock {
            check(synced || cachedContinueWatching.isNotEmpty()) { "SIMKL Continue Watching is unavailable" }
            recentCompletions.entries.removeIf { System.currentTimeMillis() - it.value > SNAPSHOT_TTL_MS }
            val upNext = cachedUpNext.toMap()
            val candidates = cachedContinueWatching.mapNotNull { (key, item) ->
                val wholeCompletion = recentCompletions["show:${item.id}"].takeIf { item.mediaType == MediaType.TV }
                if (wholeCompletion != null && (item.isUpNext || item.updatedAtMs <= wholeCompletion)) return@mapNotNull null
                val watchedKey = if (item.mediaType == MediaType.MOVIE) "movie:${item.id}"
                    else episodeKey(item.id, item.season ?: -1, item.episode ?: -1)
                val localOnlyWatched = if (item.mediaType == MediaType.MOVIE)
                    item.id in localWatchedMovies && item.id !in cachedWatchedMovies
                    else watchedKey in localWatchedEpisodes && watchedKey !in cachedWatchedEpisodes
                if (!item.isUpNext && (localOnlyWatched || isCompletedPause(watchedKey, item.updatedAtMs, false))) upNext[key]
                    else item
            }
            ContinueWatchingSnapshot(activeTokenScope, candidates, upNext,
                cachedWatchedEpisodes.toSet() + localWatchedEpisodes + recentCompletions.keys.filter { it.startsWith("show_tmdb:") })
        }
        val result = coroutineScope {
            snapshot.items.map { item -> async {
                if (item.mediaType != MediaType.TV) item else episodeLookups.withPermit {
                    validateNextEpisode(item, snapshot.watched) ?: if (!item.isUpNext) {
                        snapshot.upNext[item.mediaType to item.id]?.let { validateNextEpisode(it, snapshot.watched) }
                    } else null
                }
            } }.mapNotNull { it.await() }
        }.sortedByDescending { it.updatedAtMs }
        check(synced || result.isNotEmpty()) { "SIMKL Continue Watching is unavailable" }
        check(snapshot.scope == activeTokenScope) { "SIMKL profile changed" }
        return result
    }

    private suspend fun seasonEpisodes(show: Int, season: Int): com.arflix.tv.data.api.TmdbSeasonDetails {
        val key = show to season
        val cached = episodeSeasons[key]
        if (cached != null && System.currentTimeMillis() - cached.first < SNAPSHOT_TTL_MS) return cached.second
        val data = try {
            tmdbApi.getTvSeason(show, season, Constants.TMDB_API_KEY)
        } catch (e: retrofit2.HttpException) {
            if (e.code() != 404) throw e
            com.arflix.tv.data.api.TmdbSeasonDetails(seasonNumber = season)
        }
        episodeSeasons[key] = System.currentTimeMillis() to data
        return data
    }

    private suspend fun validateNextEpisode(item: ContinueWatchingItem, watched: Set<String>): ContinueWatchingItem? {
        val season = item.season ?: return null
        val number = item.episode ?: return null
        val episodes = seasonEpisodes(item.id, season).episodes.sortedBy { it.episodeNumber }
        // Never manufacture episode N+1 from totals or accept a pointer outside a real season.
        if (episodes.none { it.episodeNumber == number }) return null
        if (!item.isUpNext) return item
        suspend fun firstUnwatched(seasonNumber: Int, rows: List<com.arflix.tv.data.api.TmdbEpisode>, start: Int): ContinueWatchingItem? {
            val episode = rows.sortedBy { it.episodeNumber }.firstOrNull {
                it.episodeNumber >= start && episodeKey(item.id, seasonNumber, it.episodeNumber) !in watched
            } ?: return null
            val airDate = episode.airDate?.let { runCatching { java.time.LocalDate.parse(it) }.getOrNull() } ?: return null
            if (airDate.isAfter(java.time.LocalDate.now())) return null
            return item.copy(season = seasonNumber, episode = episode.episodeNumber,
                episodeTitle = episode.name, durationSeconds = episode.runtime?.times(60L) ?: item.durationSeconds)
        }
        firstUnwatched(season, episodes, number)?.let { return it }
        if (episodes.any { it.episodeNumber >= number && episodeKey(item.id, season, it.episodeNumber) !in watched }) return null
        val seasons = tmdbApi.getTvDetails(item.id, Constants.TMDB_API_KEY).seasons
            .map { it.seasonNumber }.filter { it > season }.sorted()
        for (nextSeason in seasons) {
            val rows = seasonEpisodes(item.id, nextSeason).episodes
            firstUnwatched(nextSeason, rows, 1)?.let { return it }
            if (rows.any { episodeKey(item.id, nextSeason, it.episodeNumber) !in watched }) return null
        }
        return null
    }

    suspend fun addToWatchlist(mediaType: MediaType, tmdbId: Int, isAnime: Boolean = false): Boolean {
        val token = authManager.getAccessToken() ?: return false
        val authHeader = "Bearer $token"
        val body = if (mediaType == MediaType.MOVIE) {
            SimklAddToListBody(
                movies = listOf(SimklAddToListMovie(to = "plantowatch", ids = SimklIds(tmdb = tmdbId)))
            )
        } else {
            SimklAddToListBody(
                shows = listOf(
                    SimklAddToListShow(
                        to = "plantowatch",
                        ids = SimklIds(tmdb = tmdbId),
                        useTvdbAnimeSeasons = true
                    )
                )
            )
        }
        return try {
            val res = simklApi.addToList(authHeader, clientId, body)
            if (res.isSuccessful) {
                cachedWatchlist[mediaType to tmdbId] = MediaItem(id = tmdbId, title = "", mediaType = mediaType)
                lastActivityCheckTime = 0L // Request activity refresh on next check
                true
            } else {
                AppLogger.e("SimklSyncService", "Failed adding to watchlist: code=${res.code()} msg=${res.message()}")
                false
            }
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            AppLogger.e("SimklSyncService", "Error adding to watchlist: ${e.message}")
            false
        }
    }

    suspend fun removeFromWatchlist(mediaType: MediaType, tmdbId: Int, isAnime: Boolean = false): Boolean {
        val token = authManager.getAccessToken() ?: return false
        val authHeader = "Bearer $token"

        val isWatched = if (mediaType == MediaType.MOVIE) {
            cachedWatchedMovies.contains(tmdbId)
        } else {
            cachedWatchedEpisodes.any { it.startsWith(episodePrefix(tmdbId)) }
        }

        return try {
            val res = if (isWatched) {
                // If it was already watched, restore its status to "completed" so watch history is preserved
                val body = if (mediaType == MediaType.MOVIE) {
                    SimklAddToListBody(
                        movies = listOf(SimklAddToListMovie(to = "completed", ids = SimklIds(tmdb = tmdbId)))
                    )
                } else {
                    SimklAddToListBody(
                        shows = listOf(
                            SimklAddToListShow(
                                to = "completed",
                                ids = SimklIds(tmdb = tmdbId),
                                useTvdbAnimeSeasons = true
                            )
                        )
                    )
                }
                simklApi.addToList(authHeader, clientId, body)
            } else {
                // Not watched, remove item completely from Simkl library
                val body = if (mediaType == MediaType.MOVIE) {
                    SimklSyncHistoryBody(movies = listOf(SimklMovieRef(ids = SimklIds(tmdb = tmdbId))))
                } else {
                    SimklSyncHistoryBody(
                        shows = listOf(
                            SimklShowRef(
                                ids = SimklIds(tmdb = tmdbId),
                                useTvdbAnimeSeasons = true
                            )
                        )
                    )
                }
                simklApi.removeFromHistory(authHeader, clientId, body)
            }

            if (res.isSuccessful) {
                cachedWatchlist.remove(mediaType to tmdbId)
                lastActivityCheckTime = 0L
                true
            } else {
                AppLogger.e("SimklSyncService", "Failed removing from watchlist: code=${res.code()} msg=${res.message()}")
                false
            }
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            AppLogger.e("SimklSyncService", "Error removing from watchlist: ${e.message}")
            false
        }
    }

    suspend fun markWatched(
        mediaType: MediaType,
        tmdbId: Int,
        season: Int? = null,
        episode: Int? = null,
        isAnime: Boolean = false
    ): Boolean {
        val token = authManager.getAccessToken() ?: return false
        val authHeader = "Bearer $token"
        val body = if (mediaType == MediaType.MOVIE) {
            SimklSyncHistoryBody(movies = listOf(SimklMovieRef(ids = SimklIds(tmdb = tmdbId))))
        } else {
            SimklSyncHistoryBody(
                shows = listOf(
                    SimklShowRef(
                        ids = SimklIds(tmdb = tmdbId),
                        useTvdbAnimeSeasons = true,
                        seasons = if (season != null && episode != null) {
                            listOf(SimklSeasonRef(number = season, episodes = listOf(SimklEpisodeRef(number = episode))))
                        } else null
                    )
                )
            )
        }
        return try {
            val res = simklApi.addToHistory(authHeader, clientId, body)
            if (res.isSuccessful) {
                if (mediaType == MediaType.MOVIE) {
                    cachedWatchedMovies.add(tmdbId)
                    recentCompletions["movie:$tmdbId"] = System.currentTimeMillis()
                } else if (season != null && episode != null) {
                    cachedWatchedEpisodes.add(episodeKey(tmdbId, season, episode))
                    recentCompletions[episodeKey(tmdbId, season, episode)] = System.currentTimeMillis()
                } else {
                    recentCompletions["show:$tmdbId"] = System.currentTimeMillis()
                }
                lastActivityCheckTime = 0L
                true
            } else {
                AppLogger.e("SimklSyncService", "Failed marking watched: code=${res.code()} msg=${res.message()}")
                false
            }
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            AppLogger.e("SimklSyncService", "Error marking watched: ${e.message}")
            false
        }
    }

    suspend fun markUnwatched(
        mediaType: MediaType,
        tmdbId: Int,
        season: Int? = null,
        episode: Int? = null,
        isAnime: Boolean = false
    ): Boolean {
        val token = authManager.getAccessToken() ?: return false
        val authHeader = "Bearer $token"
        return try {
            val res = if (mediaType == MediaType.MOVIE) {
                // Moving movie to "plantowatch" via addToList preserves the row and user ratings on Simkl
                val body = SimklAddToListBody(
                    movies = listOf(SimklAddToListMovie(to = "plantowatch", ids = SimklIds(tmdb = tmdbId)))
                )
                simklApi.addToList(authHeader, clientId, body)
            } else {
                val body = SimklSyncHistoryBody(
                    shows = listOf(
                        SimklShowRef(
                            ids = SimklIds(tmdb = tmdbId),
                            useTvdbAnimeSeasons = true,
                            seasons = if (season != null && episode != null) {
                                listOf(SimklSeasonRef(number = season, episodes = listOf(SimklEpisodeRef(number = episode))))
                            } else null
                        )
                    )
                )
                simklApi.removeFromHistory(authHeader, clientId, body)
            }
            if (res.isSuccessful) {
                if (mediaType == MediaType.MOVIE) {
                    cachedWatchedMovies.remove(tmdbId)
                    cachedWatchlist[MediaType.MOVIE to tmdbId] = MediaItem(
                        id = tmdbId,
                        title = "",
                        mediaType = MediaType.MOVIE
                    )
                } else if (season != null && episode != null) {
                    cachedWatchedEpisodes.remove(episodeKey(tmdbId, season, episode))
                }
                recentCompletions.remove(if (mediaType == MediaType.MOVIE) "movie:$tmdbId" else episodeKey(tmdbId, season ?: -1, episode ?: -1))
                if (mediaType == MediaType.TV) recentCompletions.remove("show:$tmdbId")
                lastActivityCheckTime = 0L
                true
            } else {
                AppLogger.e("SimklSyncService", "Failed marking unwatched: code=${res.code()} msg=${res.message()}")
                false
            }
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            AppLogger.e("SimklSyncService", "Error marking unwatched: ${e.message}")
            false
        }
    }

    suspend fun markSeasonWatched(
        showTmdbId: Int,
        season: Int,
        episodes: List<Int>,
        watched: Boolean,
        isAnime: Boolean = false
    ): Boolean {
        if (episodes.isEmpty()) return true
        val token = authManager.getAccessToken() ?: return false
        val authHeader = "Bearer $token"
        val body = SimklSyncHistoryBody(
            shows = listOf(
                SimklShowRef(
                    ids = SimklIds(tmdb = showTmdbId),
                    useTvdbAnimeSeasons = true,
                    seasons = listOf(
                        SimklSeasonRef(
                            number = season,
                            episodes = episodes.distinct().map { SimklEpisodeRef(number = it) }
                        )
                    )
                )
            )
        )
        return try {
            val response = if (watched) {
                simklApi.addToHistory(authHeader, clientId, body)
            } else {
                simklApi.removeFromHistory(authHeader, clientId, body)
            }
            if (response.isSuccessful) {
                episodes.forEach { episode ->
                    val key = episodeKey(showTmdbId, season, episode)
                    if (watched) {
                        cachedWatchedEpisodes.add(key)
                        recentCompletions[key] = System.currentTimeMillis()
                    } else {
                        cachedWatchedEpisodes.remove(key)
                        recentCompletions.remove(key)
                    }
                }
                if (!watched) cachedContinueWatching.remove(MediaType.TV to showTmdbId)
                lastActivityCheckTime = 0L
                true
            } else {
                AppLogger.e(
                    "SimklSyncService",
                    "Failed marking season ${if (watched) "watched" else "unwatched"}: code=${response.code()}"
                )
                false
            }
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            AppLogger.e("SimklSyncService", "Error updating season history: ${e.message}")
            false
        }
    }

    suspend fun dismissContinueWatching(
        mediaType: MediaType,
        tmdbId: Int,
        season: Int? = null,
        episode: Int? = null
    ): Boolean {
        val token = authManager.getAccessToken() ?: return false
        val authHeader = "Bearer $token"
        return try {
            val playback = simklApi.getPlayback(authHeader, clientId)
            val matches = playback.filter { row ->
                val resolvedId = if (mediaType == MediaType.MOVIE) {
                    row.movie?.let { resolvedTmdbId(it.ids, mediaType, it.title, it.year) }
                } else {
                    (row.show ?: row.anime)?.let { resolvedTmdbId(it.ids, mediaType, it.title, it.year) }
                }
                if (resolvedId != tmdbId) return@filter false
                if (mediaType == MediaType.MOVIE) return@filter true
                val rowEpisode = row.episode ?: return@filter false
                (season == null || rowEpisode.season == season) &&
                    (episode == null || rowEpisode.number == episode)
            }
            val successful = matches.mapNotNull { it.id }.all { id ->
                simklApi.deletePlayback(authHeader, clientId, id).isSuccessful
            }
            if (successful) {
                cachedContinueWatching.remove(mediaType to tmdbId)
                lastActivityCheckTime = 0L
            }
            successful
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            AppLogger.e("SimklSyncService", "Error deleting playback: ${e.message}")
            false
        }
    }
}
