package com.arflix.tv.data.repository

import java.net.URLEncoder

/**
 * One addon URL path segment (a type, an id or an extra). Addon types and ids may hold spaces
 * ("Live Docs"), `:` or non-ASCII text; URLEncoder's "+" for a space would reach the addon as a
 * literal plus, so it is written as %20. Used for catalog, meta and stream URLs alike.
 */
internal fun encodePathSegment(value: String): String =
    URLEncoder.encode(value, "UTF-8").replace("+", "%20")

internal fun buildCatalogRequestUrls(
    baseUrl: String,
    catalogType: String,
    catalogId: String,
    skip: Int,
    queryBase: String?,
    genre: String? = null
): List<String> {
    val type = encodePathSegment(catalogType)
    val id = encodePathSegment(catalogId)
    val base = "$baseUrl/catalog/$type/$id"
    val query = queryBase?.takeIf { it.isNotBlank() }
    val configSuffix = query?.let { "?$it" }.orEmpty()
    if (!genre.isNullOrBlank()) {
        val encodedGenre = encodePathSegment(genre)
        val extras = listOfNotNull("genre=$encodedGenre", "skip=$skip".takeIf { skip > 0 }).joinToString("&")
        return listOf("$base/$extras.json$configSuffix")
    }
    if (skip <= 0) return listOf("$base.json$configSuffix")
    val skipQuery = listOfNotNull(query, "skip=$skip").joinToString("&")
    // Standard add-ons read skip from the path. Query-first can silently repeat page one.
    return listOf("$base/skip=$skip.json$configSuffix", "$base.json?$skipQuery")
}
