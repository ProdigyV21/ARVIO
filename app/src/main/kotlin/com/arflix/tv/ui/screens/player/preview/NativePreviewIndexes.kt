package com.arflix.tv.ui.screens.player.preview

import com.arflix.tv.data.model.StreamPreviewMetadata
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import java.nio.ByteBuffer
import java.nio.ByteOrder

internal object BifPreviewParser {
    private val magic = byteArrayOf(0x89.toByte(), 0x42, 0x49, 0x46, 0x0d, 0x0a, 0x1a, 0x0a)

    fun indexSize(header: ByteArray): Int? {
        if (header.size < 64 || !header.copyOfRange(0, 8).contentEquals(magic)) return null
        val bytes = ByteBuffer.wrap(header).order(ByteOrder.LITTLE_ENDIAN)
        if (bytes.getInt(8) != 0) return null
        val count = bytes.getInt(12).toLong() and 0xffffffffL
        if (count !in 1..PREVIEW_MAX_CUES.toLong()) return null
        return 64 + (count.toInt() + 1) * 8
    }

    fun parse(index: ByteArray, url: String, totalBytes: Long, durationMs: Long): PreviewStoryboard? {
        val size = indexSize(index) ?: return null
        if (index.size < size || totalBytes < size) return null
        val bytes = ByteBuffer.wrap(index).order(ByteOrder.LITTLE_ENDIAN)
        fun uint(offset: Int): Long = bytes.getInt(offset).toLong() and 0xffffffffL
        val count = bytes.getInt(12)
        val multiplier = uint(16).takeIf { it > 0L } ?: 1000L
        if (uint(64 + count * 8) != 0xffffffffL || uint(68 + count * 8) != totalBytes) return null
        val cues = mutableListOf<PreviewCue>()
        for (i in 0 until count) {
            val timestamp = uint(64 + i * 8)
            val nextTimestamp = if (i + 1 < count) uint(72 + i * 8) else null
            if (timestamp > PREVIEW_MAX_TIME_MS / multiplier ||
                (nextTimestamp != null && nextTimestamp > PREVIEW_MAX_TIME_MS / multiplier)) return null
            val start = timestamp * multiplier
            val end = nextTimestamp?.times(multiplier) ?: durationMs
            val offset = uint(68 + i * 8)
            val nextOffset = uint(76 + i * 8)
            if (start !in 0..PREVIEW_MAX_TIME_MS || offset < size || nextOffset <= offset ||
                nextOffset > totalBytes || nextOffset - offset > PREVIEW_MAX_IMAGE_BYTES) return null
            // The sentinel contains a byte offset, not an end time. Do not invent a last interval.
            if (i + 1 == count && durationMs <= 0) continue
            if (end <= start || end > PREVIEW_MAX_TIME_MS) return null
            cues += PreviewCue(start, end, url, byteOffset = offset, byteLength = (nextOffset - offset).toInt())
        }
        return try { PreviewStoryboard(cues).takeIf { cues.isNotEmpty() } } catch (_: IllegalArgumentException) { null }
    }
}

internal object HomeServerPreviewParser {
    fun jellyfin(text: String, metadata: StreamPreviewMetadata, maxWidth: Int): PreviewStoryboard? {
        val item = jsonObject(text) ?: return null
        if (!sameId(item.text("Id"), metadata.itemId)) return null
        val source = item.objects("MediaSources").firstOrNull { sameId(it.text("Id"), metadata.mediaSourceId) }
            ?: return null
        if (metadata.mediaETag.isNotEmpty() && source.text("ETag").ifEmpty { source.text("Etag") } != metadata.mediaETag) return null
        val trickplay = item.obj("Trickplay") ?: return null
        val versions = trickplay.entrySet().firstOrNull { sameId(it.key, metadata.mediaSourceId) }
            ?.value?.takeIf { it.isJsonObject }?.asJsonObject ?: return null
        val options = versions.entrySet().mapNotNull { it.value.takeIf { value -> value.isJsonObject }?.asJsonObject }
            .filter { (it.number("Width") ?: 0) in 1..4096 && (it.number("Height") ?: 0) in 1..4096 }
        val chosen = options.filter { (it.number("Width") ?: 0) <= maxWidth }.maxByOrNull { it.number("Width") ?: 0 }
            ?: options.minByOrNull { it.number("Width") ?: Long.MAX_VALUE } ?: return null
        val width = chosen.number("Width")?.toInt() ?: return null
        val height = chosen.number("Height")?.toInt() ?: return null
        val columns = chosen.number("TileWidth") ?: return null
        val rows = chosen.number("TileHeight") ?: return null
        val interval = chosen.number("Interval") ?: return null
        val count = chosen.number("ThumbnailCount") ?: return null
        if (columns !in 1..100 || rows !in 1..100 || interval !in 1..PREVIEW_MAX_TIME_MS ||
            count !in 1..PREVIEW_MAX_CUES.toLong() || count * interval > PREVIEW_MAX_TIME_MS ||
            columns * width > 32_768 || rows * height > 32_768) return null
        val duration = metadata.durationMs.takeIf { it > 0 }
            ?: source.number("RunTimeTicks")?.div(10_000)?.takeIf { it > 0 }
            ?: item.number("RunTimeTicks")?.div(10_000)?.takeIf { it > 0 }
            ?: count * interval
        val cues = mutableListOf<PreviewCue>()
        repeat(count.toInt()) { index ->
            val start = index * interval
            if (start >= duration) return@repeat
            val sheet = index / (columns * rows)
            val tile = index % (columns * rows)
            val url = serverPreviewUrl(metadata, "Videos", metadata.itemId, "Trickplay", width.toString(), "$sheet.jpg")
                ?.newBuilder()?.addQueryParameter("MediaSourceId", metadata.mediaSourceId)?.build()?.toString() ?: return null
            cues += PreviewCue(start, minOf(start + interval, duration), url,
                PreviewCrop((tile % columns * width).toInt(), (tile / columns * height).toInt(), width, height))
        }
        return PreviewStoryboard(cues).takeIf { cues.isNotEmpty() }
    }

    fun plexBifUrl(text: String, metadata: StreamPreviewMetadata): String? {
        val root = jsonObject(text) ?: return null
        val items = (root.obj("MediaContainer") ?: root).objects("Metadata")
        val item = items.firstOrNull { it.text("ratingKey") == metadata.itemId } ?: return null
        for (media in item.objects("Media")) {
            val parts = media.objects("Part")
            if (parts.size != 1) continue
            val part = parts.single()
            if (part.text("id") == metadata.mediaSourceId &&
                "sd" in part.text("indexes").split(',', ' ')) {
                return serverPreviewUrl(metadata, "library", "parts", metadata.mediaSourceId, "indexes", "sd")?.toString()
            }
        }
        return null
    }

    fun embySingleVersion(text: String, metadata: StreamPreviewMetadata): Boolean {
        val item = jsonObject(text) ?: return false
        val sources = item.objects("MediaSources")
        if (item.text("Id") != metadata.itemId || sources.size != 1 || sources.single().text("Id") != metadata.mediaSourceId) return false
        return metadata.mediaETag.isEmpty() || sources.single().text("ETag").ifEmpty { sources.single().text("Etag") } == metadata.mediaETag
    }

    private fun sameId(a: String, b: String): Boolean =
        a.isNotEmpty() && b.isNotEmpty() && a.replace("-", "").equals(b.replace("-", ""), ignoreCase = true)

    private fun jsonObject(text: String): JsonObject? {
        if (text.length > PREVIEW_MAX_MANIFEST_BYTES) return null
        return try { JsonParser.parseString(text).takeIf { it.isJsonObject }?.asJsonObject }
        catch (_: com.google.gson.JsonParseException) { null }
    }

    private fun JsonObject.text(key: String): String = get(key)?.takeIf { it.isJsonPrimitive }?.asString.orEmpty()
    private fun JsonObject.number(key: String): Long? = text(key).toLongOrNull()
    private fun JsonObject.obj(key: String): JsonObject? = get(key)?.takeIf { it.isJsonObject }?.asJsonObject
    private fun JsonObject.objects(key: String): List<JsonObject> = get(key)?.takeIf { it.isJsonArray }?.asJsonArray
        ?.mapNotNull { it.takeIf(JsonElement::isJsonObject)?.asJsonObject }.orEmpty()
}

internal fun serverPreviewUrl(metadata: StreamPreviewMetadata, vararg segments: String): okhttp3.HttpUrl? {
    val base = metadata.serverUrl?.toHttpUrlOrNull() ?: return null
    if (base.username.isNotEmpty() || base.password.isNotEmpty()) return null
    return base.newBuilder().query(null).fragment(null).apply {
        segments.forEach { addPathSegment(it) }
    }.build()
}
