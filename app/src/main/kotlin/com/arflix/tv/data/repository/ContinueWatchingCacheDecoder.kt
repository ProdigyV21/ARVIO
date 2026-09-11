package com.arflix.tv.data.repository

import com.google.gson.Gson
import com.google.gson.JsonParser

/** Gson bypasses Kotlin constructors; old/cloud records can contain null non-null fields. */
internal fun sanitizeContinueWatchingItems(items: List<ContinueWatchingItem?>?): List<ContinueWatchingItem> =
    items.orEmpty().mapNotNull { item ->
        if (item == null || item.id == 0 || item.mediaType == null || item.title.isNullOrBlank()) return@mapNotNull null
        item.copy(
            year = item.year.orEmpty(),
            releaseDate = item.releaseDate.orEmpty(),
            overview = item.overview.orEmpty(),
            imdbRating = item.imdbRating.orEmpty(),
            tmdbRating = item.tmdbRating.orEmpty(),
            duration = item.duration.orEmpty(),
        )
    }.distinctBy { "${it.mediaType}:${it.id}" }

internal fun decodeContinueWatchingCache(json: String, gson: Gson): List<ContinueWatchingItem> {
    if (json.isBlank()) return emptyList()
    val array = runCatching { JsonParser.parseString(json).takeIf { it.isJsonArray }?.asJsonArray }
        .getOrNull() ?: return emptyList()
    return sanitizeContinueWatchingItems(array.map { element ->
        runCatching { gson.fromJson(element, ContinueWatchingItem::class.java) }.getOrNull()
    })
}
