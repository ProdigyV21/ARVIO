package com.arflix.tv.recording

import java.net.URI

/** A rendition listed by a master playlist. */
internal data class HlsVariant(val uri: String, val bandwidth: Long, val height: Int?)

/** How a segment is encrypted; only AES-128 (whole segment, CBC) can be recorded. */
internal data class HlsKey(val method: String, val uri: String?, val iv: ByteArray?) {
    val isNone: Boolean get() = method.equals("NONE", ignoreCase = true)
    val isAes128: Boolean get() = method.equals("AES-128", ignoreCase = true)

    override fun equals(other: Any?): Boolean =
        other is HlsKey && method == other.method && uri == other.uri &&
            ivEquals(iv, other.iv)

    private fun ivEquals(a: ByteArray?, b: ByteArray?): Boolean =
        if (a == null || b == null) a == b else a.contentEquals(b)

    override fun hashCode(): Int = 31 * (31 * method.hashCode() + (uri?.hashCode() ?: 0)) + (iv?.contentHashCode() ?: 0)
}

internal data class HlsSegment(
    val uri: String,
    val sequence: Long,
    val durationSec: Double,
    val key: HlsKey?,
    /** fMP4 streams: the init section every following fragment needs. */
    val mapUri: String?,
    /** EXT-X-DISCONTINUITY before this segment (ad breaks, encoder restarts). */
    val discontinuity: Boolean,
)

internal sealed class HlsPlaylist {
    data class Master(val variants: List<HlsVariant>) : HlsPlaylist()
    data class Media(
        val segments: List<HlsSegment>,
        val targetDurationSec: Double,
        val ended: Boolean,
    ) : HlsPlaylist()
}

internal fun looksLikeHlsPlaylist(head: String): Boolean = head.trimStart('﻿', ' ', '\r', '\n', '\t').startsWith("#EXTM3U")

/** Resolves [uri] against [base] the way a player does; absolute URIs pass through. */
internal fun resolveHlsUri(base: String, uri: String): String = runCatching {
    URI(base).resolve(URI(uri.trim().replace(" ", "%20"))).toString()
}.getOrDefault(uri.trim())

/** Parses a master or media playlist. All URIs come back resolved against [baseUrl]. */
internal fun parseHlsPlaylist(text: String, baseUrl: String): HlsPlaylist {
    val lines = text.lineSequence().map { it.trim() }.filter { it.isNotEmpty() }.toList()
    if (lines.any { it.startsWith("#EXT-X-STREAM-INF") }) {
        val variants = ArrayList<HlsVariant>()
        lines.forEachIndexed { index, line ->
            if (!line.startsWith("#EXT-X-STREAM-INF")) return@forEachIndexed
            val uri = lines.drop(index + 1).firstOrNull { !it.startsWith("#") } ?: return@forEachIndexed
            val attrs = hlsAttributes(line.substringAfter(':', ""))
            val bandwidth = (attrs["AVERAGE-BANDWIDTH"] ?: attrs["BANDWIDTH"])?.toLongOrNull() ?: 0L
            val height = attrs["RESOLUTION"]?.substringAfter('x', "")?.toIntOrNull()
            variants += HlsVariant(resolveHlsUri(baseUrl, uri), bandwidth, height)
        }
        return HlsPlaylist.Master(variants.distinctBy { it.uri })
    }
    var mediaSequence = 0L
    var targetDuration = 6.0
    var ended = false
    var key: HlsKey? = null
    var mapUri: String? = null
    var pendingDuration = 0.0
    var pendingDiscontinuity = false
    val segments = ArrayList<HlsSegment>()
    for (line in lines) {
        when {
            line.startsWith("#EXT-X-MEDIA-SEQUENCE:") -> mediaSequence = line.substringAfter(':').trim().toLongOrNull() ?: 0L
            line.startsWith("#EXT-X-TARGETDURATION:") -> targetDuration = line.substringAfter(':').trim().toDoubleOrNull() ?: targetDuration
            line.startsWith("#EXT-X-ENDLIST") -> ended = true
            line.startsWith("#EXT-X-DISCONTINUITY") && !line.startsWith("#EXT-X-DISCONTINUITY-SEQUENCE") -> pendingDiscontinuity = true
            line.startsWith("#EXT-X-KEY:") -> {
                val attrs = hlsAttributes(line.substringAfter(':'))
                val method = attrs["METHOD"] ?: "NONE"
                key = if (method.equals("NONE", ignoreCase = true)) null else HlsKey(
                    method = method,
                    uri = attrs["URI"]?.let { resolveHlsUri(baseUrl, it) },
                    iv = attrs["IV"]?.let(::parseHlsIv),
                )
            }
            line.startsWith("#EXT-X-MAP:") -> mapUri = hlsAttributes(line.substringAfter(':'))["URI"]?.let { resolveHlsUri(baseUrl, it) }
            line.startsWith("#EXTINF:") -> pendingDuration = line.substringAfter(':').substringBefore(',').trim().toDoubleOrNull() ?: 0.0
            line.startsWith("#") -> Unit
            else -> {
                segments += HlsSegment(
                    uri = resolveHlsUri(baseUrl, line),
                    sequence = mediaSequence + segments.size,
                    durationSec = pendingDuration,
                    key = key,
                    mapUri = mapUri,
                    discontinuity = pendingDiscontinuity,
                )
                pendingDuration = 0.0
                pendingDiscontinuity = false
            }
        }
    }
    return HlsPlaylist.Media(segments, targetDuration, ended)
}

/**
 * The rendition to record: the best one at or under [maxBandwidth] (a recording should not
 * outgrow the line or the disk), or the lightest when every one is above it.
 */
internal fun chooseRecordingVariant(variants: List<HlsVariant>, maxBandwidth: Long = DEFAULT_RECORDING_MAX_BANDWIDTH): HlsVariant? {
    if (variants.isEmpty()) return null
    val fitting = variants.filter { it.bandwidth in 1..maxBandwidth }
    return fitting.maxByOrNull { it.bandwidth }
        ?: variants.filter { it.bandwidth > 0 }.minByOrNull { it.bandwidth }
        ?: variants.first()
}

internal const val DEFAULT_RECORDING_MAX_BANDWIDTH = 8_000_000L

/** HLS: segments carry no IV of their own, so the media sequence number is the IV. */
internal fun hlsSequenceIv(sequence: Long): ByteArray = ByteArray(16).also { iv ->
    var value = sequence
    for (i in 15 downTo 8) {
        iv[i] = (value and 0xFF).toByte()
        value = value shr 8
    }
}

internal fun parseHlsIv(value: String): ByteArray? {
    val hex = value.trim().removePrefix("0x").removePrefix("0X")
    if (hex.isEmpty() || hex.length > 32 || hex.any { it.digitToIntOrNull(16) == null }) return null
    val padded = hex.padStart(32, '0')
    return ByteArray(16) { i -> padded.substring(i * 2, i * 2 + 2).toInt(16).toByte() }
}

/** Attribute lists: KEY=VALUE pairs, where quoted values may contain commas. */
internal fun hlsAttributes(list: String): Map<String, String> {
    val result = LinkedHashMap<String, String>()
    var i = 0
    while (i < list.length) {
        val eq = list.indexOf('=', i)
        if (eq < 0) break
        val name = list.substring(i, eq).trim().trimStart(',').trim()
        var j = eq + 1
        val value: String
        if (j < list.length && list[j] == '"') {
            val end = list.indexOf('"', j + 1).let { if (it < 0) list.length else it }
            value = list.substring(j + 1, end)
            j = (end + 1).coerceAtMost(list.length)
            val comma = list.indexOf(',', j)
            i = if (comma < 0) list.length else comma + 1
        } else {
            val comma = list.indexOf(',', j)
            value = list.substring(j, if (comma < 0) list.length else comma).trim()
            i = if (comma < 0) list.length else comma + 1
        }
        if (name.isNotEmpty()) result[name.uppercase()] = value
    }
    return result
}

/**
 * Which segments of a fresh playlist are new. A live playlist slides: anything at or below the
 * last written sequence was already saved. When the sequence jumps backwards (the encoder or
 * panel restarted the count) the whole window counts as new rather than recording nothing.
 */
internal fun newHlsSegments(segments: List<HlsSegment>, lastWritten: Long?): List<HlsSegment> {
    if (lastWritten == null) return segments
    val newest = segments.maxOfOrNull { it.sequence } ?: return emptyList()
    if (newest < lastWritten - 2) return segments
    return segments.filter { it.sequence > lastWritten }
}

/**
 * A first look at a live playlist starts near its live edge: recording the full window would
 * add up to minutes from before the recording began (or before kick-off).
 */
internal fun startingHlsSegments(segments: List<HlsSegment>, keep: Int = 3): List<HlsSegment> =
    if (segments.size <= keep) segments else segments.takeLast(keep)
