package com.arflix.tv.data.repository

import java.net.URLEncoder

internal fun buildCatalogRequestUrls(
    baseUrl: String,
    catalogType: String,
    catalogId: String,
    skip: Int,
    queryBase: String?,
    genre: String? = null
): List<String> {
    // Path segments: URLEncoder's "+" for a space ("Live Docs") would reach the addon as a plus.
    val type = URLEncoder.encode(catalogType, "UTF-8").replace("+", "%20")
    val id = URLEncoder.encode(catalogId, "UTF-8").replace("+", "%20")
    val base = "$baseUrl/catalog/$type/$id"
    val query = queryBase?.takeIf { it.isNotBlank() }
    val configSuffix = query?.let { "?$it" }.orEmpty()
    if (!genre.isNullOrBlank()) {
        val encodedGenre = URLEncoder.encode(genre, "UTF-8").replace("+", "%20")
        val extras = listOfNotNull("genre=$encodedGenre", "skip=$skip".takeIf { skip > 0 }).joinToString("&")
        return listOf("$base/$extras.json$configSuffix")
    }
    if (skip <= 0) return listOf("$base.json$configSuffix")
    val skipQuery = listOfNotNull(query, "skip=$skip").joinToString("&")
    // Standard add-ons read skip from the path. Query-first can silently repeat page one.
    return listOf("$base/skip=$skip.json$configSuffix", "$base.json?$skipQuery")
}
