package com.arflix.tv.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.navigation.NavOptions
import com.arflix.tv.data.api.TmdbApi
import com.arflix.tv.data.api.TmdbFindResponse
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.util.Constants
import java.net.URI
import java.net.URLDecoder
import javax.inject.Inject
import kotlinx.coroutines.ensureActive
import kotlin.coroutines.coroutineContext

/** Public partner links identify a title only. They never contain a source URL or play action. */
data class PartnerOpenRequest(
    val imdbId: String? = null,
    val mediaType: MediaType? = null,
    val tmdbId: Int? = null,
    val season: Int? = null,
    val episode: Int? = null
) {
    fun canonicalUri(): String = buildString {
        append("arvio://open?")
        if (imdbId != null) append("imdb=$imdbId")
        else append("type=${mediaType?.name?.lowercase()}&id=$tmdbId")
        season?.let { append("&season=$it") }
        episode?.let { append("&episode=$it") }
    }
}

/** A new intent gets its own ticket even when it points at the same title. */
data class PendingPartnerOpenLink(val ticket: String, val request: PartnerOpenRequest)

/** Saved by the Activity. A stale resolver is never allowed to consume a newer intent. */
internal class PartnerOpenLinkInbox(private val newTicket: () -> String = { java.util.UUID.randomUUID().toString() }) {
    var pending by mutableStateOf<PendingPartnerOpenLink?>(null)
        private set

    fun replace(request: PartnerOpenRequest?) {
        pending = request?.let { PendingPartnerOpenLink(newTicket(), it) }
    }

    fun restore(uri: String?, ticket: String?) {
        pending = PartnerOpenLinkParser.parse(uri)?.let { PendingPartnerOpenLink(ticket ?: newTicket(), it) }
    }

    fun consume(expected: PendingPartnerOpenLink): Boolean {
        if (pending?.ticket != expected.ticket) return false
        pending = null
        return true
    }
}

data class PartnerOpenTarget(
    val mediaType: MediaType,
    val tmdbId: Int,
    val season: Int? = null,
    val episode: Int? = null
)

/** Reusing the old details ViewModel could override a newly linked episode with prior playback. */
internal fun partnerOpenNavigationOptions(): NavOptions = NavOptions.Builder()
    .setLaunchSingleTop(false)
    .setRestoreState(false)
    .build()

internal object PartnerOpenLinkParser {
    private val keys = setOf("imdb", "type", "id", "season", "episode")
    private val imdbPattern = Regex("tt[0-9]{5,12}")
    private val numberPattern = Regex("0|[1-9][0-9]*")

    fun parse(value: String?): PartnerOpenRequest? {
        if (value == null || value.length > 512) return null
        val uri = runCatching { URI(value) }.getOrNull() ?: return null
        if (uri.scheme != "arvio" || uri.rawAuthority != "open" ||
            uri.rawPath !in listOf("", "/") || uri.rawFragment != null) return null
        val query = uri.rawQuery?.takeIf { it.isNotEmpty() } ?: return null
        val parameters = linkedMapOf<String, String>()
        for (part in query.split('&')) {
            if ('=' !in part) return null
            val name = decode(part.substringBefore('=')) ?: return null
            val content = decode(part.substringAfter('=')) ?: return null
            if (name !in keys || parameters.containsKey(name)) return null
            parameters[name] = content
        }
        // Partner templates commonly leave optional placeholders blank.
        val seasonValue = parameters["season"]?.takeIf { it.isNotEmpty() }
        val episodeValue = parameters["episode"]?.takeIf { it.isNotEmpty() }
        val season = seasonValue?.let { integer(it, 0, 10_000) ?: return null }
        val episode = episodeValue?.let { integer(it, 1, 10_000) ?: return null }
        if (episode != null && season == null) return null

        val imdb = parameters["imdb"]
        if (imdb != null) {
            if (!imdbPattern.matches(imdb) || "type" in parameters || "id" in parameters) return null
            return PartnerOpenRequest(imdbId = imdb, season = season, episode = episode)
        }
        val type = when (parameters["type"]) {
            "movie" -> MediaType.MOVIE
            "tv" -> MediaType.TV
            else -> return null
        }
        val id = integer(parameters["id"] ?: return null, 1, Int.MAX_VALUE) ?: return null
        if (type == MediaType.MOVIE && (season != null || episode != null)) return null
        return PartnerOpenRequest(mediaType = type, tmdbId = id, season = season, episode = episode)
    }

    private fun decode(value: String): String? =
        runCatching { URLDecoder.decode(value, "UTF-8") }.getOrNull()

    private fun integer(value: String, minimum: Int, maximum: Int): Int? =
        value.takeIf(numberPattern::matches)?.toIntOrNull()?.takeIf { it in minimum..maximum }
}

internal class PartnerOpenLinkResolver @Inject constructor(private val tmdbApi: TmdbApi) {
    suspend fun resolve(request: PartnerOpenRequest): PartnerOpenTarget? =
        resolvePartnerOpenRequest(request) { imdb ->
            tmdbApi.findByExternalId(imdb, Constants.TMDB_API_KEY, "imdb_id")
        }
}

/** Kept independent of Android so ambiguity, episode mapping and cancellation can be tested. */
internal suspend fun resolvePartnerOpenRequest(
    request: PartnerOpenRequest,
    find: suspend (String) -> TmdbFindResponse
): PartnerOpenTarget? {
    // Revalidate restored/programmatically constructed data at the resolution boundary.
    if (PartnerOpenLinkParser.parse(request.canonicalUri()) != request) return null
    val imdb = request.imdbId
    if (imdb == null) return PartnerOpenTarget(
        request.mediaType ?: return null,
        request.tmdbId ?: return null,
        request.season,
        request.episode
    )
    val response = find(imdb)
    coroutineContext.ensureActive()
    val candidates = buildList {
        response.movieResults.filter { it.id > 0 }.forEach {
            add(PartnerOpenTarget(MediaType.MOVIE, it.id))
        }
        response.tvResults.filter { it.id > 0 }.forEach {
            add(PartnerOpenTarget(MediaType.TV, it.id))
        }
        response.tvEpisodeResults.filter {
            it.showId > 0 && it.seasonNumber in 0..10_000 && it.episodeNumber in 1..10_000
        }.forEach {
            add(PartnerOpenTarget(MediaType.TV, it.showId, it.seasonNumber, it.episodeNumber))
        }
    }.distinct()
    // An ambiguous identifier must not silently open the wrong title.
    val target = candidates.singleOrNull() ?: return null
    if (target.mediaType == MediaType.MOVIE) {
        return target.takeIf { request.season == null && request.episode == null }
    }
    // An episode IMDb ID is authoritative; conflicting supplied coordinates are invalid.
    if (target.season != null && request.season != null && target.season != request.season) return null
    if (target.episode != null && request.episode != null && target.episode != request.episode) return null
    return target.copy(season = target.season ?: request.season, episode = target.episode ?: request.episode)
}
