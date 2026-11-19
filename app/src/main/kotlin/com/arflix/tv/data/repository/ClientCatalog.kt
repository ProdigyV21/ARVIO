package com.arflix.tv.data.repository

import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.google.gson.JsonParser
import java.net.URI
import java.net.URLDecoder
import java.util.Locale

internal data class ClientCatalogRequest(
    val provider: String,
    val action: String,
    val mediaType: MediaType?,
    val source: String?,
    val category: String? = null
) {
    companion object {
        fun parse(value: String, fallbackType: String?): ClientCatalogRequest {
            val uri = URI(value.trim())
            require(uri.rawFragment == null) { "Client delegation does not support fragments" }
            var provider = uri.scheme?.lowercase(Locale.US).orEmpty()
            require(provider in setOf("trakt", "simkl", "homeserver", "hybrid", "vod")) { "Unsupported client delegate" }
            val rest = uri.rawSchemeSpecificPart.removePrefix("//")
            val action = rest.substringBefore('?').trim('/').lowercase(Locale.US)
            val parameters = linkedMapOf<String, String>()
            rest.substringAfter('?', "").split('&').filter { it.isNotEmpty() }.forEach { pair ->
                val key = URLDecoder.decode(pair.substringBefore('='), "UTF-8")
                require(key in setOf("type", "source", "tracker", "category") && key !in parameters) { "Unsupported or repeated delegate parameter" }
                parameters[key] = URLDecoder.decode(pair.substringAfter('=', ""), "UTF-8")
            }
            if (provider == "hybrid") provider = parameters["tracker"]?.lowercase(Locale.US) ?: "trakt"
            require(provider in setOf("trakt", "simkl", "homeserver", "vod")) { "Unsupported hybrid tracker" }
            val normalizedAction = if (action in setOf("plan", "watchlist") && provider == "simkl") "plantowatch" else action
            require(when (provider) {
                "trakt" -> normalizedAction in setOf("watchlist", "watched")
                "simkl" -> normalizedAction in setOf("plantowatch", "completed", "watching", "hold", "dropped")
                "vod" -> normalizedAction in setOf("recent", "categories")
                else -> normalizedAction == "library"
            }) { "Unsupported client delegate action" }
            return ClientCatalogRequest(provider, normalizedAction,
                clientCatalogMediaType(parameters["type"] ?: fallbackType), parameters["source"], parameters["category"])
        }
    }
}

internal fun clientCatalogMediaType(value: String?): MediaType? = when (value?.trim()?.lowercase(Locale.US)) {
    null, "", "all" -> null
    "movie", "movies" -> MediaType.MOVIE
    "tv", "series", "show", "shows" -> MediaType.TV
    else -> throw IllegalArgumentException("Unsupported client catalog media type")
}

internal data class ClientCatalogFilter(
    val mediaType: MediaType? = null,
    val genreIds: Set<Int> = emptySet(),
    val minYear: Int? = null,
    val maxYear: Int? = null,
    val minRating: Double? = null,
    val availableOn: String? = null
) {
    fun matches(item: MediaItem): Boolean {
        if (mediaType != null && item.mediaType != mediaType) return false
        if (genreIds.isNotEmpty() && item.genreIds.none { it in genreIds }) return false
        val year = item.year.take(4).toIntOrNull()
        if (minYear != null && (year == null || year < minYear)) return false
        if (maxYear != null && (year == null || year > maxYear)) return false
        val rating = item.rating.toDoubleOrNull()
        return minRating == null || (rating != null && rating >= minRating)
    }

    companion object {
        fun parse(value: String?): ClientCatalogFilter {
            if (value.isNullOrBlank()) return ClientCatalogFilter()
            val json = JsonParser.parseString(value)
            require(json.isJsonObject) { "clientFilter must be a JSON object" }
            val obj = json.asJsonObject
            require(obj.keySet().all { it in setOf("mediaType", "genreIds", "minYear", "maxYear", "minRating", "availableOn") }) { "Unsupported clientFilter field" }
            fun number(key: String): Double? = obj.get(key)?.let {
                require(it.isJsonPrimitive && it.asJsonPrimitive.isNumber) { "$key must be numeric" }
                it.asDouble.also { number -> require(number.isFinite()) }
            }
            fun year(key: String): Int? = number(key)?.let {
                require(it % 1.0 == 0.0 && it in 1.0..9999.0) { "Invalid year" }
                it.toInt()
            }
            val type = obj.get("mediaType")?.let {
                require(it.isJsonPrimitive && it.asJsonPrimitive.isString)
                clientCatalogMediaType(it.asString)
            }
            val genres = obj.get("genreIds")?.let { array ->
                require(array.isJsonArray) { "genreIds must be an array" }
                array.asJsonArray.map {
                    require(it.isJsonPrimitive && it.asJsonPrimitive.isNumber)
                    val id = it.asDouble
                    require(id % 1.0 == 0.0 && id in 1.0..Int.MAX_VALUE.toDouble())
                    id.toInt()
                }.toSet()
            }.orEmpty()
            val min = year("minYear")
            val max = year("maxYear")
            require(min == null || max == null || min <= max) { "Invalid year range" }
            val rating = number("minRating")
            require(rating == null || rating in 0.0..10.0) { "Invalid rating" }
            val availableOn = obj.get("availableOn")?.let { el ->
                require(el.isJsonPrimitive && el.asJsonPrimitive.isString) { "availableOn must be a string" }
                el.asString.trim().lowercase(Locale.US).also { target ->
                    require(target in setOf("vod", "homeserver")) { "Unsupported availableOn target" }
                }
            }
            return ClientCatalogFilter(type, genres, min, max, rating, availableOn)
        }
    }
}

/** Cursor counts source entries, including non-matches, so filtered pages never repeat or skip matches. */
internal suspend fun clientCatalogPage(
    offset: Int,
    limit: Int,
    mediaType: MediaType?,
    filter: ClientCatalogFilter,
    extraPredicate: suspend (MediaItem) -> Boolean = { true },
    fetch: suspend (Int, Int) -> MediaRepository.CategoryPageResult
): MediaRepository.CategoryPageResult {
    require(offset >= 0 && limit > 0)
    val items = mutableListOf<MediaItem>()
    val seen = mutableSetOf<Pair<MediaType, Int>>()
    var cursor = offset
    var more = true
    while (items.size < limit && more) {
        val page = fetch(cursor, (limit - items.size).coerceAtMost(50))
        for (item in page.items) {
            if (items.size < limit &&
                (mediaType == null || mediaType == item.mediaType) &&
                filter.matches(item) &&
                extraPredicate(item) &&
                seen.add(item.mediaType to item.id)
            ) {
                items += item
            }
        }
        val next = page.nextOffset ?: (cursor + page.items.size)
        more = page.hasMore && next > cursor
        if (next <= cursor) break
        cursor = next
    }
    return MediaRepository.CategoryPageResult(items, more, cursor)
}
