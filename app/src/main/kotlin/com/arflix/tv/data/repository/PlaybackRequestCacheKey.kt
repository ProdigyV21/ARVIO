package com.arflix.tv.data.repository

import com.arflix.tv.data.model.StreamSource
import com.google.gson.Gson
import java.security.MessageDigest
import java.util.Locale

private val playbackRequestKeyGson = Gson()

internal fun resolvedPlaybackRequestCacheKey(stream: StreamSource): String {
    val url = stream.url?.trim().orEmpty()
    val infoHash = stream.infoHash?.trim()?.lowercase(Locale.US).orEmpty()
    val base = if (infoHash.isNotBlank()) {
        "ih:${stream.addonId}|$infoHash:${stream.fileIdx ?: -1}"
    } else {
        val urlKey = if (url.isNotBlank()) url.substringBefore('|').substringBefore('#') else stream.source
        "${stream.addonId}|${stream.source}|$urlKey"
    }
    val headers = stream.behaviorHints?.proxyHeaders?.request.orEmpty()
    val inlineHeaders = url.substringAfter('|', "")
    if (headers.isEmpty() && inlineHeaders.isEmpty()) return base
    val normalized = headers.entries.associate { (name, value) ->
        name.trim().lowercase(Locale.ROOT) to value.trim()
    }.toSortedMap()
    // Hash access headers instead of including their credential values verbatim in keys.
    val fingerprint = MessageDigest.getInstance("SHA-256").digest(
        playbackRequestKeyGson.toJson(listOf(normalized, inlineHeaders)).toByteArray(Charsets.UTF_8)
    ).joinToString("") { "%02x".format(it) }
    return "$base|headers:$fingerprint"
}
