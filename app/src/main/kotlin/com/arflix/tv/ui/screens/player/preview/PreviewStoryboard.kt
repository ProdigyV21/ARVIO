package com.arflix.tv.ui.screens.player.preview

import com.arflix.tv.data.model.StreamPreviewMetadata
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import java.security.MessageDigest

internal const val PREVIEW_MAX_CUES = 20_000
internal const val PREVIEW_MAX_MANIFEST_BYTES = 1024 * 1024
internal const val PREVIEW_MAX_IMAGE_BYTES = 6 * 1024 * 1024
internal const val PREVIEW_MAX_TIME_MS = 7 * 24 * 60 * 60 * 1000L

internal data class PreviewCrop(val x: Int, val y: Int, val width: Int, val height: Int) {
    fun fits(width: Int, height: Int): Boolean = x >= 0 && y >= 0 && this.width > 0 &&
        this.height > 0 && x.toLong() + this.width <= width && y.toLong() + this.height <= height
}

internal data class PreviewCue(
    val startMs: Long,
    val endMs: Long,
    val url: String,
    val crop: PreviewCrop? = null,
    val byteOffset: Long? = null,
    val byteLength: Int? = null
)

internal class PreviewStoryboard(cues: List<PreviewCue>) {
    val cues: List<PreviewCue> = cues.toList()

    init {
        require(cues.size <= PREVIEW_MAX_CUES)
        var end = 0L
        cues.forEach {
            require(it.startMs >= end && it.endMs > it.startMs && it.endMs <= PREVIEW_MAX_TIME_MS)
            end = it.endMs
        }
    }

    fun cueAt(positionMs: Long): PreviewCue? {
        var low = 0
        var high = cues.lastIndex
        while (low <= high) {
            val mid = (low + high) ushr 1
            val cue = cues[mid]
            when {
                positionMs < cue.startMs -> high = mid - 1
                positionMs >= cue.endMs -> low = mid + 1
                else -> return cue
            }
        }
        return null
    }
}

internal fun HttpUrl.samePreviewOrigin(other: HttpUrl): Boolean =
    scheme == other.scheme && host == other.host && port == other.port

internal fun previewResolve(base: String, reference: String): String? {
    val origin = base.toHttpUrlOrNull() ?: return null
    val resolved = origin.resolve(reference) ?: return null
    if (!origin.samePreviewOrigin(resolved) || resolved.username.isNotEmpty() || resolved.password.isNotEmpty()) return null
    return resolved.toString()
}

/** No raw credentials, file paths, or server identifiers are exposed to disk cache names. */
fun nativePreviewCacheIdentity(metadata: StreamPreviewMetadata): String {
    val parts = listOf(
        "native-preview-v1", metadata.kind.name, metadata.serverId, metadata.accountId,
        metadata.userId, metadata.itemId, metadata.mediaSourceId, metadata.mediaVersion, metadata.mediaETag,
        metadata.serverUrl?.toHttpUrlOrNull()?.newBuilder()?.query(null)?.fragment(null)
            ?.username("")?.password("")?.build()?.toString().orEmpty(),
        // Explicit generic URLs can encode a version in their query; do not collapse them.
        metadata.manifestUrl.orEmpty(), metadata.durationMs.toString(), metadata.timelineOffsetMs.toString()
    )
    val bytes = parts.joinToString("") { "${it.length}:$it" }.toByteArray(Charsets.UTF_8)
    return MessageDigest.getInstance("SHA-256").digest(bytes).joinToString("") { "%02x".format(it) }
}

internal object WebVttPreviewParser {
    private val timing = Regex("^(\\S+)\\s+-->\\s+(\\S+)(?:\\s+.*)?$")
    private val stamp = Regex("^(?:(\\d{2,}):)?(\\d{2}):(\\d{2})\\.(\\d{3})$")

    fun parse(text: String, manifestUrl: String): PreviewStoryboard? {
        if (text.length > PREVIEW_MAX_MANIFEST_BYTES) return null
        val lines = text.removePrefix("\uFEFF").replace("\r\n", "\n").replace('\r', '\n').lines()
        if (lines.firstOrNull()?.let { it == "WEBVTT" || it.startsWith("WEBVTT ") || it.startsWith("WEBVTT\t") } != true) return null
        // MPEG-TS mapped cues need the playback period mapping, which is not represented here.
        if (lines.any { it.startsWith("X-TIMESTAMP-MAP") }) return null
        val cues = mutableListOf<PreviewCue>()
        var i = 1
        while (i < lines.size && lines[i].isNotBlank()) i++
        while (i < lines.size) {
            while (i < lines.size && lines[i].isBlank()) i++
            val start = i
            while (i < lines.size && lines[i].isNotBlank()) i++
            val block = lines.subList(start, i)
            if (block.isEmpty() || block[0] == "STYLE" || block[0] == "REGION" ||
                block[0] == "NOTE" || block[0].startsWith("NOTE ")) continue
            val timingIndex = if (block[0].contains("-->")) 0 else 1
            val match = block.getOrNull(timingIndex)?.let { timing.matchEntire(it) } ?: continue
            val cueStart = timestamp(match.groupValues[1]) ?: return null
            val cueEnd = timestamp(match.groupValues[2]) ?: return null
            val payload = block.drop(timingIndex + 1)
            if (payload.size != 1) continue
            val reference = payload[0].trim()
            if (reference.isEmpty() || reference.any { it.isWhitespace() || it == '<' || it == '>' }) continue
            val fragment = reference.substringAfter('#', "")
            val crop = if (fragment.isEmpty()) null else parseCrop(fragment) ?: return null
            val url = previewResolve(manifestUrl, reference.substringBefore('#')) ?: continue
            cues += PreviewCue(cueStart, cueEnd, url, crop)
            if (cues.size > PREVIEW_MAX_CUES) return null
        }
        return try { PreviewStoryboard(cues).takeIf { cues.isNotEmpty() } } catch (_: IllegalArgumentException) { null }
    }

    private fun timestamp(raw: String): Long? {
        val match = stamp.matchEntire(raw) ?: return null
        val hours = match.groupValues[1].ifEmpty { "0" }.toLongOrNull() ?: return null
        val minutes = match.groupValues[2].toInt()
        val seconds = match.groupValues[3].toInt()
        if (minutes > 59 || seconds > 59 || hours > 168) return null
        return (hours * 3_600_000 + minutes * 60_000 + seconds * 1000 + match.groupValues[4].toInt())
            .takeIf { it <= PREVIEW_MAX_TIME_MS }
    }

    private fun parseCrop(raw: String): PreviewCrop? {
        if (!raw.startsWith("xywh=")) return null
        val values = raw.removePrefix("xywh=").removePrefix("pixel:").split(',')
            .map { it.toIntOrNull() ?: return null }
        if (values.size != 4) return null
        return PreviewCrop(values[0], values[1], values[2], values[3])
            .takeIf { it.fits(32_768, 32_768) }
    }
}

/** Finite image-only HLS, including EXT-X-TILES. Video/I-frame streams are never decoded here. */
internal object ImageHlsPreviewParser {
    fun childPlaylist(text: String, url: String): String? {
        if (text.length > PREVIEW_MAX_MANIFEST_BYTES || !text.trimStart().startsWith("#EXTM3U")) return null
        return text.lineSequence().map { it.trim() }.filter { it.startsWith("#EXT-X-IMAGE-STREAM-INF:") }
            .mapNotNull { attributes(it.substringAfter(':'))?.get("URI") }
            .mapNotNull { previewResolve(url, it) }.firstOrNull()
    }

    fun parse(text: String, url: String): PreviewStoryboard? {
        if (text.length > PREVIEW_MAX_MANIFEST_BYTES) return null
        val lines = text.lineSequence().map { it.trim() }.toList()
        if (lines.firstOrNull() != "#EXTM3U" || "#EXT-X-IMAGES-ONLY" !in lines || "#EXT-X-ENDLIST" !in lines) return null
        if (lines.any { it.startsWith("#EXT-X-KEY:") || it.startsWith("#EXT-X-BYTERANGE:") ||
                it.startsWith("#EXT-X-MAP:") || it.startsWith("#EXT-X-DISCONTINUITY") ||
                it.startsWith("#EXT-X-GAP") || it.startsWith("#EXT-X-SKIP:") ||
                (it.startsWith("#EXT-X-MEDIA-SEQUENCE:") && it.substringAfter(':') != "0") }) return null
        val cues = mutableListOf<PreviewCue>()
        var position = 0L
        var duration: Long? = null
        var tiles: Map<String, String>? = null
        for (line in lines) {
            when {
                line.startsWith("#EXTINF:") -> {
                    if (duration != null) return null
                    duration = seconds(line.substringAfter(':').substringBefore(',')) ?: return null
                }
                line.startsWith("#EXT-X-TILES:") -> {
                    tiles = attributes(line.substringAfter(':')) ?: return null
                }
                line.isEmpty() || line.startsWith('#') -> Unit
                else -> {
                    val segmentMs = duration ?: return null
                    val imageUrl = previewResolve(url, line) ?: return null
                    if (position + segmentMs > PREVIEW_MAX_TIME_MS) return null
                    val layout = tiles
                    if (layout == null) {
                        cues += PreviewCue(position, position + segmentMs, imageUrl)
                    } else {
                        val resolution = dimensions(layout["RESOLUTION"]) ?: return null
                        val grid = dimensions(layout["LAYOUT"]) ?: return null
                        val interval = seconds(layout["DURATION"] ?: return null) ?: return null
                        val count = (segmentMs + interval - 1) / interval
                        if (count > grid.first.toLong() * grid.second || count > PREVIEW_MAX_CUES ||
                            resolution.first.toLong() * grid.first > 32_768 ||
                            resolution.second.toLong() * grid.second > 32_768) return null
                        repeat(count.toInt()) { tile ->
                            val start = position + tile * interval
                            cues += PreviewCue(start, minOf(start + interval, position + segmentMs), imageUrl,
                                PreviewCrop(tile % grid.first * resolution.first, tile / grid.first * resolution.second,
                                    resolution.first, resolution.second))
                        }
                    }
                    if (cues.size > PREVIEW_MAX_CUES) return null
                    position += segmentMs
                    duration = null
                    tiles = null
                }
            }
        }
        if (duration != null || cues.isEmpty()) return null
        return PreviewStoryboard(cues)
    }

    private fun seconds(value: String): Long? {
        val number = value.toBigDecimalOrNull() ?: return null
        return try { number.multiply(1000.toBigDecimal()).longValueExact().takeIf { it in 1..PREVIEW_MAX_TIME_MS } }
        catch (_: ArithmeticException) { null }
    }

    private fun dimensions(value: String?): Pair<Int, Int>? {
        val values = value?.split('x') ?: return null
        if (values.size != 2) return null
        val width = values[0].toIntOrNull() ?: return null
        val height = values[1].toIntOrNull() ?: return null
        return (width to height).takeIf { width in 1..32_768 && height in 1..32_768 }
    }

    private fun attributes(value: String): Map<String, String>? {
        val result = mutableMapOf<String, String>()
        var index = 0
        while (index < value.length) {
            val equals = value.indexOf('=', index)
            if (equals < 0) return null
            val key = value.substring(index, equals).trim()
            index = equals + 1
            val end: Int
            val item: String
            if (value.getOrNull(index) == '"') {
                end = value.indexOf('"', index + 1)
                if (end < 0) return null
                item = value.substring(index + 1, end)
                index = end + 1
                if (index < value.length && value[index] != ',') return null
            } else {
                end = value.indexOf(',', index).takeIf { it >= 0 } ?: value.length
                item = value.substring(index, end)
                index = end
            }
            if (key.isEmpty() || result.put(key, item) != null) return null
            if (index < value.length) index++
        }
        return result
    }
}
