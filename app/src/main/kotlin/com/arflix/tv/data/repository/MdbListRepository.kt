package com.arflix.tv.data.repository

import com.arflix.tv.data.api.MdbListApi
import com.arflix.tv.data.api.MdbIds
import com.arflix.tv.data.api.MdbIdsItem
import com.arflix.tv.data.api.MdbPlaybackItem
import com.arflix.tv.data.api.MdbRating
import com.arflix.tv.data.api.MdbScrobbleBody
import com.arflix.tv.data.api.MdbScrobbleClearBody
import com.arflix.tv.data.api.MdbScrobbleEpisodeNumber
import com.arflix.tv.data.api.MdbScrobbleMovie
import com.arflix.tv.data.api.MdbScrobbleSeason
import com.arflix.tv.data.api.MdbScrobbleShow
import com.arflix.tv.data.api.MdbTmdbRef
import com.arflix.tv.data.api.MdbWatchedBody
import com.arflix.tv.data.api.MdbWatchedEpisodeRef
import com.arflix.tv.data.api.MdbWatchedSeasonRef
import com.arflix.tv.data.api.MdbWatchedShowRef
import com.arflix.tv.data.api.MdbDeviceAuthorizationResponse
import com.arflix.tv.data.api.MdbTokenResponse
import com.arflix.tv.data.api.MdbWatchlistItem
import com.arflix.tv.data.api.MdbWatchlistModifyBody
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.repository.sync.RemoteWatchlistResult
import com.arflix.tv.data.repository.sync.SyncProviderStore
import com.arflix.tv.util.AppLogger
import com.arflix.tv.util.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton
import java.util.concurrent.ConcurrentHashMap
import kotlin.math.roundToInt

/**
 * MDBList data layer. Mirrors the subset of TraktRepository behavior the app
 * needs when a profile uses MDBList instead of Trakt: watchlist, scrobble,
 * watched reads, and Continue Watching (paused sessions).
 *
 * Supabase stays the source of truth for watched state; this repository only
 * talks to the MDBList remote. API contract is verified live — see the
 * project_mdblist_api memory.
 */
/**
 * A show the user is part-way through on MDBList: which episodes are watched (by season) and
 * when they last watched. The next unwatched episode is resolved against TMDB by the caller.
 */
data class MdbShowWatchedProgress(
    val showTmdbId: Int,
    val title: String,
    val year: String,
    val watchedBySeason: Map<Int, Set<Int>>,
    val lastWatchedAtMs: Long
)

data class MdbExternalRating(
    val source: String,
    val label: String,
    val value: String
)

data class MdbWatchedSnapshot(
    val movies: Set<Int>,
    val episodes: Set<String>
)

@Singleton
class MdbListRepository @Inject constructor(
    private val api: MdbListApi,
    private val store: SyncProviderStore
) {
    private val TAG = "MdbListRepository"
    private data class RatingsCacheEntry(val storedAt: Long, val ratings: List<MdbExternalRating>)
    private val ratingsCache = ConcurrentHashMap<String, RatingsCacheEntry>()

    private suspend fun key(): String? = store.getMdbListAuthToken()
    private fun authHeader(k: String): String =
        if (k.startsWith("Bearer ", ignoreCase = true)) k else "Bearer $k"

    suspend fun isConnected(): Boolean = key() != null

    /** Initiates OAuth 2.0 Device Code flow. */
    suspend fun requestDeviceCode(clientId: String): MdbDeviceAuthorizationResponse = withContext(Dispatchers.IO) {
        api.requestDeviceAuthorization(clientId = clientId.trim())
    }

    /** Polls for OAuth token with user-approved device code. */
    suspend fun pollDeviceToken(deviceCode: String, clientId: String): MdbTokenResponse = withContext(Dispatchers.IO) {
        api.pollDeviceToken(deviceCode = deviceCode.trim(), clientId = clientId.trim())
    }

    /** Saves OAuth access & refresh tokens and notifies SyncProviderStore. */
    suspend fun saveTokens(tokenResponse: MdbTokenResponse) {
        store.setMdbListOAuthTokens(
            accessToken = tokenResponse.accessToken,
            refreshToken = tokenResponse.refreshToken,
            expiresInSeconds = tokenResponse.expiresIn
        )
        store.onProviderConnected(com.arflix.tv.data.repository.sync.SyncProvider.MDBLIST)
    }

    /** Disconnects MDBList by clearing OAuth tokens. */
    suspend fun disconnect() {
        store.setMdbListOAuthTokens(null, null, null)
        store.onProviderDisconnected(com.arflix.tv.data.repository.sync.SyncProvider.MDBLIST)
    }

    /**
     * Fetches the MDBList username for the currently connected API key or token.
     * Returns null gracefully if not connected or if the request fails.
     */
    suspend fun fetchUsername(): String? = withContext(Dispatchers.IO) {
        try {
            val k = key() ?: return@withContext null
            api.getUser(authHeader = authHeader(k), apiKey = k).username
        } catch (e: kotlinx.coroutines.CancellationException) {
            throw e
        } catch (_: Exception) {
            null
        }
    }

    /**
     * Extra title ratings shown on details pages. The 12-hour cache keeps this
     * useful without spending the user's MDBList request allowance on every visit.
     */
    suspend fun getExternalRatings(mediaType: MediaType, tmdbId: Int): List<MdbExternalRating> =
        withContext(Dispatchers.IO) {
            val k = key() ?: return@withContext emptyList()
            val cacheKey = "${k.hashCode()}:${mediaType.name}:$tmdbId"
            ratingsCache[cacheKey]?.takeIf { System.currentTimeMillis() - it.storedAt < RATINGS_CACHE_TTL_MS }
                ?.let { return@withContext it.ratings }
            try {
                val apiType = if (mediaType == MediaType.MOVIE) "movie" else "show"
                val ratings = api.getMediaInfo(
                    mediaType = apiType,
                    mediaId = tmdbId,
                    authHeader = authHeader(k),
                    apiKey = k
                ).ratings
                    .orEmpty()
                    .mapNotNull(::normalizeRating)
                    .distinctBy { it.source }
                    .sortedBy { RATING_SOURCE_ORDER.indexOf(it.source).let { index -> if (index < 0) Int.MAX_VALUE else index } }
                    .take(4)
                ratingsCache[cacheKey] = RatingsCacheEntry(System.currentTimeMillis(), ratings)
                ratings
            } catch (e: Exception) {
                if (e is kotlinx.coroutines.CancellationException) throw e
                AppLogger.w(TAG, "MDBList ratings fetch failed: ${e.message}")
                emptyList()
            }
        }

    private fun normalizeRating(rating: MdbRating): MdbExternalRating? {
        val source = rating.source?.lowercase()?.trim().orEmpty()
        if (source == "imdb") return null // IMDb already has its own verified badge.
        val label = RATING_LABELS[source] ?: return null
        val value = when (source) {
            "tomatoes", "popcorn", "metacritic", "metacriticuser" ->
                rating.score.asRatingNumber()?.let { "${it.roundToInt()}%" }
            "letterboxd", "rogerebert" -> rating.value.asRatingNumber()?.let(::formatDecimalRating)
            else -> rating.value.asRatingNumber()?.let(::formatDecimalRating)
                ?: rating.score.asRatingNumber()?.let { formatDecimalRating(it / 10.0) }
        } ?: return null
        return MdbExternalRating(source = source, label = label, value = value)
    }

    private fun formatDecimalRating(value: Double): String =
        if (value % 1.0 == 0.0) value.roundToInt().toString() else String.format(java.util.Locale.US, "%.1f", value)

    private fun com.google.gson.JsonElement?.asRatingNumber(): Double? =
        this?.takeUnless { it.isJsonNull }?.runCatching { asString.toDoubleOrNull() }?.getOrNull()

    private companion object {
        const val RATINGS_CACHE_TTL_MS = 12 * 60 * 60 * 1000L
        val RATING_SOURCE_ORDER = listOf(
            "tomatoes", "popcorn", "metacritic", "letterboxd", "trakt", "tmdb", "myanimelist", "rogerebert", "metacriticuser"
        )
        val RATING_LABELS = mapOf(
            "tomatoes" to "RT Critics",
            "popcorn" to "RT Audience",
            "metacritic" to "Metacritic",
            "metacriticuser" to "Metacritic Users",
            "letterboxd" to "Letterboxd",
            "trakt" to "Trakt",
            "tmdb" to "TMDB",
            "myanimelist" to "MAL",
            "rogerebert" to "Roger Ebert"
        )
    }

    // ===== Watchlist =====

    suspend fun addToWatchlist(mediaType: MediaType, tmdbId: Int): Boolean =
        modifyWatchlist(mediaType, tmdbId, "add")

    suspend fun removeFromWatchlist(mediaType: MediaType, tmdbId: Int): Boolean =
        modifyWatchlist(mediaType, tmdbId, "remove")

    private suspend fun modifyWatchlist(
        mediaType: MediaType,
        tmdbId: Int,
        action: String
    ): Boolean = withContext(Dispatchers.IO) {
        val k = key() ?: return@withContext false
        try {
            val body = if (mediaType == MediaType.MOVIE) {
                MdbWatchlistModifyBody(movies = listOf(MdbTmdbRef(tmdbId)))
            } else {
                MdbWatchlistModifyBody(shows = listOf(MdbTmdbRef(tmdbId)))
            }
            api.modifyWatchlist(action = action, authHeader = authHeader(k), apiKey = k, body = body)
            true
        } catch (e: Exception) {
            if (e is kotlinx.coroutines.CancellationException) throw e
            AppLogger.e(TAG, "watchlist $action failed", e)
            false
        }
    }

    suspend fun getWatchlist(): RemoteWatchlistResult = withContext(Dispatchers.IO) {
        val k = key() ?: return@withContext RemoteWatchlistResult(connected = false, items = null, rawCount = 0)
        try {
            val raw = fetchAllWatchlistItems(k)
            val items = raw
                .mapIndexedNotNull { index, item -> mapWatchlistItem(item, index) }
                .sortedWith(compareBy<MediaItem> { it.sourceOrder }.thenByDescending { it.addedAt })
            RemoteWatchlistResult(connected = true, items = items, rawCount = raw.size)
        } catch (e: Exception) {
            if (e is kotlinx.coroutines.CancellationException) throw e
            AppLogger.e(TAG, "watchlist fetch failed", e)
            RemoteWatchlistResult(connected = true, items = null, rawCount = 0)
        }
    }

    private suspend fun fetchAllWatchlistItems(apiKey: String): List<MdbWatchlistItem> {
        val all = mutableListOf<MdbWatchlistItem>()
        val limit = 1000
        var offset = 0
        while (true) {
            val page = api.getWatchlistItems(
                authHeader = authHeader(apiKey),
                apiKey = apiKey,
                limit = limit,
                offset = offset,
                unified = "true"
            )
            all.addAll(page)
            if (page.size < limit) break
            offset += limit
        }
        return all
    }

    private fun mapWatchlistItem(item: MdbWatchlistItem, sourceOrder: Int): MediaItem? {
        val tmdbId = item.ids?.tmdb ?: item.id ?: return null
        val type = if (item.mediatype.equals("show", ignoreCase = true)) MediaType.TV else MediaType.MOVIE
        val year = item.releaseYear?.toString()
            ?: item.releaseDate?.take(4).orEmpty()
        // No poster in the watchlist payload — WatchlistRepository enriches via TMDB,
        // mirroring the Trakt path (mapWatchlistItemFast).
        return MediaItem(
            id = tmdbId,
            title = item.title.orEmpty(),
            mediaType = type,
            year = year,
            releaseDate = item.releaseDate,
            addedAt = parseIsoMillis(item.watchlistAt),
            sourceOrder = sourceOrder
        )
    }

    // ===== Scrobble =====

    suspend fun scrobble(
        action: String, // start | pause | stop
        mediaType: MediaType,
        tmdbId: Int,
        progress: Float,
        season: Int?,
        episode: Int?
    ) = withContext(Dispatchers.IO) {
        val k = key() ?: return@withContext
        // Two decimals: a whole percent is 13 seconds of a 22-minute episode and
        // over a minute of a feature film, and this is the only precision the
        // resume point ever has, since no tracker stores a position. More than
        // two is rejected outright — the API answers 400 to 26.735 while taking
        // 26.73 — and a rejected scrobble is silently swallowed, which would
        // stop progress reaching MDBList at all.
        val prog = (progress.coerceIn(0f, 100f) * 100f).roundToInt() / 100f
        val body = if (mediaType == MediaType.MOVIE) {
            MdbScrobbleBody(progress = prog, movie = MdbScrobbleMovie(MdbIds(tmdb = tmdbId)))
        } else {
            if (season == null || episode == null) return@withContext
            MdbScrobbleBody(
                progress = prog,
                show = MdbScrobbleShow(
                    ids = MdbIds(tmdb = tmdbId),
                    season = MdbScrobbleSeason(number = season, episode = MdbScrobbleEpisodeNumber(episode))
                )
            )
        }
        try {
            api.scrobble(action = action, authHeader = authHeader(k), apiKey = k, body = body)
        } catch (e: Exception) {
            if (e is kotlinx.coroutines.CancellationException) throw e
            AppLogger.e(TAG, "scrobble $action failed", e)
        }
    }

    /** Clear a paused session so it drops out of Continue Watching. */
    suspend fun clearPlayback(mediaType: MediaType, tmdbId: Int, season: Int?, episode: Int?) =
        withContext(Dispatchers.IO) {
            val k = key() ?: return@withContext
            val body = if (mediaType == MediaType.MOVIE) {
                MdbScrobbleClearBody(movie = MdbScrobbleMovie(MdbIds(tmdb = tmdbId)))
            } else {
                if (season == null || episode == null) return@withContext
                MdbScrobbleClearBody(
                    show = MdbScrobbleShow(
                        ids = MdbIds(tmdb = tmdbId),
                        season = MdbScrobbleSeason(number = season, episode = MdbScrobbleEpisodeNumber(episode))
                    )
                )
            }
            try {
                api.scrobbleClear(authHeader = authHeader(k), apiKey = k, body = body)
            } catch (e: Exception) {
                if (e is kotlinx.coroutines.CancellationException) throw e
                AppLogger.e(TAG, "scrobble clear failed", e)
            }
        }

    // ===== Watched mirror (Trakt-style ids objects) =====

    suspend fun markMovieWatched(tmdbId: Int): Boolean = watchedCall {
        api.addWatched(authHeader = authHeader(it), apiKey = it, body = MdbWatchedBody(movies = listOf(MdbIdsItem(MdbIds(tmdb = tmdbId)))))
    }

    suspend fun markMovieUnwatched(tmdbId: Int): Boolean = watchedCall {
        api.removeWatched(authHeader = authHeader(it), apiKey = it, body = MdbWatchedBody(movies = listOf(MdbIdsItem(MdbIds(tmdb = tmdbId)))))
    }

    suspend fun markEpisodeWatched(showTmdbId: Int, season: Int, episode: Int): Boolean = watchedCall {
        api.addWatched(authHeader = authHeader(it), apiKey = it, body = episodeBody(showTmdbId, season, episode))
    }

    suspend fun markEpisodeUnwatched(showTmdbId: Int, season: Int, episode: Int): Boolean = watchedCall {
        api.removeWatched(authHeader = authHeader(it), apiKey = it, body = episodeBody(showTmdbId, season, episode))
    }

    /** Batch-mark a whole season's episodes watched in one /sync/watched call. */
    suspend fun markSeasonWatched(showTmdbId: Int, season: Int, episodes: List<Int>): Boolean = watchedCall {
        api.addWatched(
            authHeader = authHeader(it),
            apiKey = it,
            body = MdbWatchedBody(
                shows = listOf(
                    MdbWatchedShowRef(
                        ids = MdbIds(tmdb = showTmdbId),
                        seasons = listOf(
                            MdbWatchedSeasonRef(number = season, episodes = episodes.map { n -> MdbWatchedEpisodeRef(n) })
                        )
                    )
                )
            )
        )
    }

    private fun episodeBody(showTmdbId: Int, season: Int, episode: Int) = MdbWatchedBody(
        shows = listOf(
            MdbWatchedShowRef(
                ids = MdbIds(tmdb = showTmdbId),
                seasons = listOf(
                    MdbWatchedSeasonRef(number = season, episodes = listOf(MdbWatchedEpisodeRef(episode)))
                )
            )
        )
    )

    private suspend fun watchedCall(block: suspend (String) -> Any): Boolean = withContext(Dispatchers.IO) {
        val k = key() ?: return@withContext false
        try {
            block(k)
            true
        } catch (e: Exception) {
            if (e is kotlinx.coroutines.CancellationException) throw e
            AppLogger.e(TAG, "watched mirror failed", e)
            false
        }
    }

    // ===== Watched reads =====

    suspend fun getWatchedSnapshot(): Result<MdbWatchedSnapshot> = withContext(Dispatchers.IO) {
        val k = key() ?: return@withContext Result.failure(IllegalStateException("MDBList is not connected"))
        try {
            val movies = mutableSetOf<Int>()
            val episodes = mutableSetOf<String>()
            var offset = 0
            val limit = 1000
            while (true) {
                val response = api.getWatched(authHeader = authHeader(k), apiKey = k, limit = limit, offset = offset)
                response.movies?.forEach { row ->
                    row.movie?.ids?.tmdb?.let(movies::add)
                }
                response.episodes?.forEach { row ->
                    val episode = row.episode ?: return@forEach
                    val showTmdb = episode.show?.ids?.tmdb ?: return@forEach
                    val season = episode.season ?: return@forEach
                    val number = episode.number ?: return@forEach
                    episodes.add("show_tmdb:$showTmdb:$season:$number")
                }
                if (response.pagination?.hasMore != true) break
                offset += limit
            }
            Result.success(MdbWatchedSnapshot(movies, episodes))
        } catch (e: kotlinx.coroutines.CancellationException) {
            throw e
        } catch (e: Exception) {
            AppLogger.e(TAG, "watched snapshot fetch failed", e)
            Result.failure(e)
        }
    }

    suspend fun getWatchedMovies(): Set<Int> = withContext(Dispatchers.IO) {
        val k = key() ?: return@withContext emptySet()
        try {
            val out = mutableSetOf<Int>()
            var offset = 0
            val limit = 1000
            while (true) {
                val resp = api.getWatched(authHeader = authHeader(k), apiKey = k, limit = limit, offset = offset)
                resp.movies?.forEach { row -> row.movie?.ids?.tmdb?.let { out.add(it) } }
                if (resp.pagination?.hasMore != true) break
                offset += limit
            }
            out
        } catch (e: Exception) {
            if (e is kotlinx.coroutines.CancellationException) throw e
            emptySet()
        }
    }

    /**
     * Per-show watched progress from /sync/watched, grouped by show tmdb id. Powers MDBList's
     * "Now Playing" (up-next) — shows with episodes watched but not finished. Unlike Trakt,
     * MDBList has no server-side "next episode" endpoint, so the caller resolves the next
     * episode from this watched set + TMDB.
     */
    suspend fun getWatchedShowsProgress(): List<MdbShowWatchedProgress> = withContext(Dispatchers.IO) {
        val k = key() ?: return@withContext emptyList()
        try {
            class Acc(var title: String, var year: String) {
                val eps = mutableMapOf<Int, MutableSet<Int>>()
                var lastMs = 0L
            }
            val byShow = mutableMapOf<Int, Acc>()
            var offset = 0
            val limit = 1000
            while (true) {
                val resp = api.getWatched(authHeader = authHeader(k), apiKey = k, limit = limit, offset = offset)
                resp.episodes?.forEach { row ->
                    val ep = row.episode ?: return@forEach
                    val showTmdb = ep.show?.ids?.tmdb ?: return@forEach
                    val s = ep.season ?: return@forEach
                    val e = ep.number ?: return@forEach
                    val acc = byShow.getOrPut(showTmdb) {
                        Acc(ep.show?.title.orEmpty(), ep.show?.year?.toString().orEmpty())
                    }
                    if (acc.title.isBlank()) ep.show?.title?.let { acc.title = it }
                    acc.eps.getOrPut(s) { mutableSetOf() }.add(e)
                    val ts = parseIsoMillis(row.lastWatchedAt)
                    if (ts > acc.lastMs) acc.lastMs = ts
                }
                if (resp.pagination?.hasMore != true) break
                offset += limit
            }
            byShow.map { (id, acc) ->
                MdbShowWatchedProgress(
                    showTmdbId = id,
                    title = acc.title,
                    year = acc.year,
                    watchedBySeason = acc.eps.mapValues { it.value.toSet() },
                    lastWatchedAtMs = acc.lastMs
                )
            }
        } catch (e: Exception) {
            if (e is kotlinx.coroutines.CancellationException) throw e
            AppLogger.e(TAG, "watched shows progress fetch failed", e)
            emptyList()
        }
    }

    suspend fun getWatchedEpisodes(): Set<String> = withContext(Dispatchers.IO) {
        val k = key() ?: return@withContext emptySet()
        try {
            val out = mutableSetOf<String>()
            var offset = 0
            val limit = 1000
            while (true) {
                val resp = api.getWatched(authHeader = authHeader(k), apiKey = k, limit = limit, offset = offset)
                resp.episodes?.forEach { row ->
                    val ep = row.episode ?: return@forEach
                    val showTmdb = ep.show?.ids?.tmdb ?: return@forEach
                    val s = ep.season ?: return@forEach
                    val e = ep.number ?: return@forEach
                    out.add("show_tmdb:$showTmdb:$s:$e")
                }
                if (resp.pagination?.hasMore != true) break
                offset += limit
            }
            out
        } catch (e: Exception) {
            if (e is kotlinx.coroutines.CancellationException) throw e
            emptySet()
        }
    }

    // ===== Continue Watching (paused sessions) =====

    suspend fun getContinueWatching(forceRefresh: Boolean = false): List<ContinueWatchingItem> = withContext(Dispatchers.IO) {
        val k = key() ?: return@withContext emptyList()
        try {
            api.getPlayback(
                authHeader = authHeader(k),
                apiKey = k,
                cacheControl = if (forceRefresh) "no-cache" else null
            )
                .mapNotNull { mapPlaybackItem(it) }
                .sortedByDescending { it.updatedAtMs }
                // One card per title. A paused session is kept for every episode
                // ever abandoned, so without this a single show can take a large
                // share of MAX_CONTINUE_WATCHING and push other shows off the row
                // entirely. Newest-first ordering makes the survivor the one to
                // resume.
                .distinctBy { it.mediaType to it.id }
                .take(Constants.MAX_CONTINUE_WATCHING)
        } catch (e: Exception) {
            if (e is kotlinx.coroutines.CancellationException) throw e
            AppLogger.e(TAG, "continue watching fetch failed", e)
            emptyList()
        }
    }

    private fun mapPlaybackItem(item: MdbPlaybackItem): ContinueWatchingItem? {
        val progress = item.progress?.toFloatOrNull()?.roundToInt() ?: return null
        // Only a genuinely unstarted session is dropped. The old
        // MIN_PROGRESS_THRESHOLD floor hid a show's newest episode whenever the
        // user had watched under three percent of it, leaving the row pointing
        // at an older episode they had already moved on from.
        if (progress <= 0 || progress >= Constants.WATCHED_THRESHOLD) return null
        val durationSeconds = (item.runtime ?: 0).toLong() * 60L
        val updatedMs = item.updatedAtTs?.let { it * 1000L } ?: parseIsoMillis(item.updatedAt)

        return if (item.type == "movie") {
            val movie = item.movie ?: return null
            val tmdbId = movie.ids?.tmdb ?: return null
            ContinueWatchingItem(
                id = tmdbId,
                title = movie.title.orEmpty(),
                mediaType = MediaType.MOVIE,
                progress = progress.coerceIn(0, 100),
                durationSeconds = durationSeconds,
                year = movie.year?.toString().orEmpty(),
                updatedAtMs = updatedMs
            )
        } else {
            val show = item.show ?: return null
            val ep = item.episode ?: return null
            val tmdbId = show.ids?.tmdb ?: return null
            val season = ep.season ?: return null
            val number = ep.number ?: return null
            ContinueWatchingItem(
                id = tmdbId,
                title = show.title.orEmpty(),
                mediaType = MediaType.TV,
                progress = progress.coerceIn(0, 100),
                durationSeconds = durationSeconds,
                season = season,
                episode = number,
                episodeTitle = ep.name,
                year = show.year?.toString().orEmpty(),
                updatedAtMs = updatedMs
            )
        }
    }

    private fun parseIsoMillis(iso: String?): Long {
        if (iso.isNullOrBlank()) return 0L
        return try {
            java.time.Instant.parse(iso).toEpochMilli()
        } catch (e: Exception) {
            if (e is kotlinx.coroutines.CancellationException) throw e
            0L
        }
    }
}
