package com.arflix.tv.data.repository.wetrakr

import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.repository.ContinueWatchingItem
import com.arflix.tv.data.repository.sync.RemoteSyncProvider
import com.arflix.tv.data.repository.sync.RemoteWatchlistResult
import com.arflix.tv.data.repository.sync.SyncProvider
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.time.Instant
import javax.inject.Inject
import javax.inject.Singleton

internal fun weTrakrDate(value: String?): Long = runCatching { Instant.parse(value).toEpochMilli() }.getOrDefault(0)
internal fun weTrakrEpisodeKey(row: JsonObject): String? {
    val id = row.obj("show").tmdb() ?: return null
    val season = row.int("season_number")?.takeIf { it >= 0 } ?: return null
    val episode = row.int("number")?.takeIf { it > 0 } ?: return null
    return "show_tmdb:$id:$season:$episode"
}
internal fun weTrakrBody(type: MediaType, id: Int, status: String, season: Int? = null, episode: Int? = null): Map<String, Any> {
    require(id > 0)
    val item = mutableMapOf<String, Any>("ids" to mapOf("tmdb" to id), "status" to status)
    if (type == MediaType.TV && season != null && episode != null) {
        require(season >= 0 && episode > 0)
        item["seasons"] = listOf(mapOf("number" to season, "episodes" to listOf(mapOf("number" to episode, "status" to status))))
    }
    return mapOf("allow_rewatch" to false, (if (type == MediaType.MOVIE) "movies" else "shows") to listOf(item))
}
internal fun weTrakrPlaybackRef(type: MediaType, id: Int, season: Int?, episode: Int?): Map<String, Any> {
    require(id > 0)
    return if (type == MediaType.MOVIE) mapOf("movie" to mapOf("ids" to mapOf("tmdb" to id))) else {
        require(season != null && season >= 0 && episode != null && episode > 0)
        mapOf("show" to mapOf("ids" to mapOf("tmdb" to id)), "episode" to mapOf("season" to season, "number" to episode))
    }
}
internal fun weTrakrWriteSucceeded(result: JsonElement): Boolean {
    if (!result.isJsonObject) return false
    fun entries(value: JsonElement?): List<JsonElement> = when {
        value == null || value.isJsonNull -> emptyList()
        value.isJsonArray -> value.asJsonArray.toList()
        value.isJsonObject -> value.asJsonObject.entrySet().flatMap { entries(it.value) }
        else -> listOf(value)
    }
    val obj = result.asJsonObject
    if (entries(obj.get("notFound")).isNotEmpty()) return false
    return entries(obj.get("errored")).all { item ->
        // Explicitly idempotent watched writes only; other item errors must surface.
        item.isJsonObject && (item.asJsonObject.text("message") ?: item.asJsonObject.text("error")) == "Already watched!"
    }
}

@Singleton
class WeTrakrRemoteProvider @Inject constructor(private val api: WeTrakrRepository) : RemoteSyncProvider {
    override val provider = SyncProvider.WETRAKR
    override suspend fun isConnected() = api.isConnected()
    private suspend fun change(type: MediaType, id: Int, status: String, remove: Boolean = false, season: Int? = null, episode: Int? = null): Boolean {
        val path = if (!remove) "/sync/tracking" else if (status == "watched") "/sync/tracking/remove/all" else "/sync/tracking/remove"
        val result = api.request(path, "POST", weTrakrBody(type, id, status, season, episode)).first
        api.invalidate()
        return weTrakrWriteSucceeded(result)
    }
    suspend fun markWatched(type: MediaType, id: Int, season: Int? = null, episode: Int? = null, watched: Boolean = true) = change(type, id, "watched", !watched, season, episode)
    override suspend fun addToWatchlist(mediaType: MediaType, tmdbId: Int, isAnime: Boolean) = change(mediaType, tmdbId, "planning")
    override suspend fun removeFromWatchlist(mediaType: MediaType, tmdbId: Int, isAnime: Boolean) = change(mediaType, tmdbId, "planning", true)
    override suspend fun getWatchlist(): RemoteWatchlistResult? {
        if (!isConnected()) return null
        val items = getLibraryItems("planning")
        return RemoteWatchlistResult(true, items, items.size)
    }
    suspend fun getLibraryItems(status: String, forceRefresh: Boolean = false): List<MediaItem> {
        require(status in setOf("planning", "watching", "watched", "hold", "dropped"))
        val rows = api.list(status, if (status in setOf("watching", "hold")) "shows" else "all", forceRefresh)
        return rows.mapNotNull { row ->
            val id = row.tmdb() ?: return@mapNotNull null
            val type = when (row.text("type")) { "movie" -> MediaType.MOVIE; "show" -> MediaType.TV; else -> return@mapNotNull null }
            MediaItem(id = id, title = row.text("title").orEmpty(), mediaType = type,
                image = row.text("poster_path")?.let { "https://image.tmdb.org/t/p/w500$it" }.orEmpty(),
                backdrop = row.text("backdrop_path"), overview = row.text("overview").orEmpty(),
                year = (row.text("release_date") ?: row.text("first_air_date")).orEmpty().take(4),
                addedAt = weTrakrDate(row.text("tracked_at")))
        }
    }
    override suspend fun getWatchedMovies() = api.list("watched", "movies").mapNotNull { it.tmdb() }.toSet()
    override suspend fun getWatchedEpisodes() = api.list("watched", "episodes").mapNotNull(::weTrakrEpisodeKey).toSet()
    override suspend fun getContinueWatching(forceRefresh: Boolean): List<ContinueWatchingItem> {
        val profile = api.profileId()
        val watchedMovies = api.list("watched", "movies", forceRefresh, profile).mapNotNull { it.tmdb() }.toSet()
        val watchedEpisodes = api.list("watched", "episodes", forceRefresh, profile).mapNotNull(::weTrakrEpisodeKey).toSet()
        val playing = api.list("playing", "all", forceRefresh, profile)
        val watching = api.list("watching", "shows", forceRefresh, profile)
        val result = mutableListOf<ContinueWatchingItem>()
        fun add(row: JsonObject, upNext: Boolean) {
            val isMovie = row.text("type") == "movie"
            val show = if (isMovie || upNext) row else row.obj("show")
            val id = show.tmdb() ?: return
            val ep = if (upNext) row.obj("next_watching_episode") else row
            val season = if (isMovie) null else ep.int("season_number") ?: return
            val episode = if (isMovie) null else ep.int("number") ?: return
            if (!isMovie && (season!! < 0 || episode!! < 1)) return
            if (isMovie && id in watchedMovies || !isMovie && "show_tmdb:$id:$season:$episode" in watchedEpisodes) return
            if (upNext) {
                val date = ep.text("air_date") ?: ep.text("release_date") ?: return
                if (date.take(10) > java.time.LocalDate.now(java.time.ZoneOffset.UTC).toString()) return
            }
            val playback = row.obj("playback")
            val progress = if (upNext) 0 else playback.text("progress_percent")?.toFloatOrNull()?.toInt()?.coerceIn(0, 100) ?: return
            if (!upNext && (progress <= 0 || progress >= 90)) return
            val duration = playback.int("runtime_seconds")?.toLong() ?: 0L
            result += ContinueWatchingItem(id = id, title = show.text("title").orEmpty(), mediaType = if (isMovie) MediaType.MOVIE else MediaType.TV,
                progress = progress, season = season, episode = episode, episodeTitle = ep.text("title"),
                posterPath = show.text("poster_path"), backdropPath = show.text("backdrop_path"), episodeStillPath = if (isMovie) null else ep.text("still_path"),
                isUpNext = upNext, durationSeconds = duration, resumePositionSeconds = duration * progress / 100,
                updatedAtMs = weTrakrDate(playback.text("tracked_at") ?: row.text("tracked_at")))
        }
        playing.forEach { add(it, false) }
        watching.forEach { row -> if (result.none { it.mediaType == MediaType.TV && it.id == row.tmdb() }) add(row, true) }
        return result.distinctBy { "${it.mediaType}:${it.id}:${it.season}:${it.episode}" }
    }
    override suspend fun dismissContinueWatching(mediaType: MediaType, tmdbId: Int, season: Int?, episode: Int?): Boolean {
        try { api.request("/scrobble/playing", "DELETE", weTrakrPlaybackRef(mediaType, tmdbId, season, episode)) }
        catch (e: WeTrakrError) { if (e.reason != "NO_SESSION") throw e }
        api.invalidate()
        return true
    }
    private suspend fun scrobble(action: String, type: MediaType, id: Int, progress: Float, season: Int?, episode: Int?) {
        if (id <= 0 || !progress.isFinite() || type == MediaType.TV && (season == null || episode == null)) return
        api.request("/scrobble/$action?min_progress=90", "POST", weTrakrPlaybackRef(type, id, season, episode) + mapOf("progress" to progress.coerceIn(0f, 100f), "app_version" to "2.0"))
        api.invalidate()
    }
    override suspend fun scrobbleStart(mediaType: MediaType, tmdbId: Int, progress: Float, season: Int?, episode: Int?, isAnime: Boolean) = scrobble("start", mediaType, tmdbId, progress, season, episode)
    // Start/pause/stop persist progress without exhausting the provider's daily write quota.
    override suspend fun scrobbleProgress(mediaType: MediaType, tmdbId: Int, progress: Float, season: Int?, episode: Int?, isAnime: Boolean) = Unit
    override suspend fun scrobblePause(mediaType: MediaType, tmdbId: Int, progress: Float, season: Int?, episode: Int?, isAnime: Boolean) = scrobble("pause", mediaType, tmdbId, progress, season, episode)
    override suspend fun scrobbleStop(mediaType: MediaType, tmdbId: Int, progress: Float, season: Int?, episode: Int?, isAnime: Boolean) = scrobble("stop", mediaType, tmdbId, progress, season, episode)
}
