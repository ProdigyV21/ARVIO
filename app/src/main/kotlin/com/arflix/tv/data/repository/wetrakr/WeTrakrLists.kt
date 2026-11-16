package com.arflix.tv.data.repository.wetrakr

import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import javax.inject.Inject
import javax.inject.Singleton

/**
 * WeTrakr custom lists and personal recommendations, through the device's WeTrakr connection.
 *
 * Both are part of WeTrakr's free plan (a free account owns up to 5 lists of 250 titles each).
 * Writes send TMDB ids (`ids.tmdb`), as the sync endpoints accept them; only the "which lists
 * hold this title" lookup needs the WeTrakr id, resolved once per title.
 */
@Singleton
class WeTrakrLists @Inject constructor(private val api: WeTrakrRepository) {

    data class UserList(val id: Long, val name: String, val locked: Boolean)

    fun isConnected(): Boolean = api.isConnected()

    /** The user's own lists, in their order. Lists beyond the plan quota come back locked. */
    suspend fun ownLists(): List<UserList> {
        val (data, _) = api.request("/sync/lists")
        return weTrakrArray(data).mapNotNull { el ->
            val obj = el.takeIf { it.isJsonObject }?.asJsonObject ?: return@mapNotNull null
            val id = obj.text("id")?.toLongOrNull()?.takeIf { it > 0 } ?: return@mapNotNull null
            UserList(id, obj.text("name") ?: obj.text("title") ?: "WeTrakr", obj.text("locked") == "true")
        }
    }

    /** Ids of the lists that already hold the title. */
    suspend fun listsHolding(type: MediaType, tmdbId: Int): Set<Long> {
        val weTrakrId = resolve(type, tmdbId) ?: return emptySet()
        val (data, _) = api.request("/sync/lists/item/${target(type)}/$weTrakrId")
        return weTrakrArray(data).mapNotNull { el ->
            val obj = el.takeIf { it.isJsonObject }?.asJsonObject ?: return@mapNotNull null
            val included = INCLUDED_KEYS.firstNotNullOfOrNull { key -> obj.text(key) }
            if (included != "true") return@mapNotNull null
            obj.text("id")?.toLongOrNull()
        }.toSet()
    }

    /** Adds the title to the list or removes it; true when WeTrakr accepted it. */
    suspend fun setMembership(listId: Long, type: MediaType, tmdbId: Int, add: Boolean): Boolean {
        require(tmdbId > 0)
        val body = mapOf((if (type == MediaType.MOVIE) "movies" else "shows") to listOf(mapOf("ids" to mapOf("tmdb" to tmdbId))))
        val path = if (add) "/sync/lists/$listId/items" else "/sync/lists/$listId/items/remove"
        val (result, _) = api.request(path, "POST", body)
        api.invalidate()
        return weTrakrListWriteSucceeded(result)
    }

    /** The titles of one of the user's lists, in the owner's order. */
    suspend fun listItems(listId: Long): List<MediaItem> {
        val items = mutableListOf<MediaItem>()
        var page = 1
        while (page <= 10) {
            val (data, headers) = api.request("/sync/lists/$listId/items?limit=100&page=$page")
            val rows = weTrakrArray(data)
            rows.mapNotNullTo(items) { el -> el.takeIf { it.isJsonObject }?.asJsonObject?.let(::weTrakrMediaItem) }
            val pages = headers["x-pagination-page-count"]?.toIntOrNull()
            if (pages != null && page >= pages || pages == null && rows.size < 100) break
            page++
        }
        return items
    }

    /**
     * Personalized recommendations (from what the user watched, rated and favorited; titles already
     * tracked are left out), as TMDB refs.
     */
    suspend fun recommended(type: MediaType, limit: Int = 40): List<Int> {
        val filter = if (type == MediaType.MOVIE) "movie" else "show"
        val (data, _) = api.request("/discover/recommended?filter_type=$filter&limit=${limit.coerceIn(1, 100)}")
        val rows = when {
            data.isJsonArray -> data.asJsonArray
            data.isJsonObject -> data.asJsonObject.get(if (type == MediaType.MOVIE) "movies" else "shows")
                ?.takeIf { it.isJsonArray }?.asJsonArray ?: JsonArray()
            else -> JsonArray()
        }
        return rows.mapNotNull { el -> el.takeIf { it.isJsonObject }?.asJsonObject?.tmdb() }.distinct()
    }

    private val resolved = java.util.concurrent.ConcurrentHashMap<String, Long>()

    /** WeTrakr's own id for a TMDB title. */
    private suspend fun resolve(type: MediaType, tmdbId: Int): Long? {
        val key = "${type.name}:$tmdbId"
        resolved[key]?.let { return it }
        val kind = if (type == MediaType.MOVIE) "movie" else "show"
        val (data, _) = api.request("/media/external/tmdb/$tmdbId?type=$kind")
        val id = weTrakrResolvedId(data, kind) ?: return null
        resolved[key] = id
        return id
    }

    private fun target(type: MediaType) = if (type == MediaType.MOVIE) "movie" else "show"

    companion object {
        /** Names WeTrakr may use for "this list already holds the title" in the picker. */
        private val INCLUDED_KEYS = listOf("included", "contains", "has_item", "in_list")

        /** Home rows of WeTrakr recommendations (built in, empty until WeTrakr is connected). */
        const val RECS_SHOWS_ID = "wetrakr_recs_shows"
        const val RECS_MOVIES_ID = "wetrakr_recs_movies"

        fun recommendationType(catalogId: String): MediaType? = when (catalogId) {
            RECS_SHOWS_ID -> MediaType.TV
            RECS_MOVIES_ID -> MediaType.MOVIE
            else -> null
        }

        /** Library keys of the user's own lists: "list:<id>". */
        const val LIST_KEY_PREFIX = "list:"
    }
}

/** A list write went through unless WeTrakr names the title as not found or unresolved. */
internal fun weTrakrListWriteSucceeded(result: JsonElement): Boolean {
    if (!result.isJsonObject) return false
    val obj = result.asJsonObject
    fun hasEntries(value: JsonElement?): Boolean = when {
        value == null || value.isJsonNull -> false
        value.isJsonArray -> value.asJsonArray.size() > 0
        value.isJsonObject -> value.asJsonObject.entrySet().any { hasEntries(it.value) }
        else -> false
    }
    return listOf("notFound", "not_found", "unresolved").none { hasEntries(obj.get(it)) }
}

/** A JSON array, or the array under a common wrapper key. */
internal fun weTrakrArray(data: JsonElement): List<JsonElement> = when {
    data.isJsonArray -> data.asJsonArray.toList()
    data.isJsonObject -> listOf("lists", "items", "results", "data").firstNotNullOfOrNull { key ->
        data.asJsonObject.get(key)?.takeIf { it.isJsonArray }?.asJsonArray?.toList()
    }.orEmpty()
    else -> emptyList()
}

/** The WeTrakr id in an external-id lookup: the media object itself, or the match of [kind]. */
internal fun weTrakrResolvedId(data: JsonElement, kind: String): Long? {
    fun idOf(obj: JsonObject): Long? = obj.text("id")?.toLongOrNull()?.takeIf { it > 0 }
    return when {
        data.isJsonObject -> {
            val obj = data.asJsonObject
            obj.get(kind)?.takeIf { it.isJsonObject }?.asJsonObject?.let(::idOf) ?: idOf(obj)
        }
        data.isJsonArray -> data.asJsonArray.mapNotNull { it.takeIf { e -> e.isJsonObject }?.asJsonObject }
            .let { rows -> rows.firstOrNull { it.text("type") == kind } ?: rows.firstOrNull() }?.let(::idOf)
        else -> null
    }
}

internal fun weTrakrMediaItem(row: JsonObject): MediaItem? {
    val id = row.tmdb() ?: return null
    val type = when (row.text("type")) { "movie" -> MediaType.MOVIE; "show" -> MediaType.TV; else -> return null }
    return MediaItem(
        id = id, title = row.text("title").orEmpty(), mediaType = type,
        image = row.text("poster_path")?.let { "https://image.tmdb.org/t/p/w500$it" }.orEmpty(),
        backdrop = row.text("backdrop_path"), overview = row.text("overview").orEmpty(),
        year = (row.text("release_date") ?: row.text("first_air_date")).orEmpty().take(4),
    )
}
