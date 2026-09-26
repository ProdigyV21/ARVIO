package com.arflix.tv.ui.screens.player.subtitles

import androidx.annotation.OptIn
import androidx.media3.common.C
import androidx.media3.common.MimeTypes
import androidx.media3.common.util.ParsableByteArray
import androidx.media3.common.util.UnstableApi
import androidx.media3.container.Mp4Box
import androidx.media3.extractor.GaplessInfoHolder
import androidx.media3.extractor.mp4.BoxParser
import androidx.media3.extractor.mp4.TrackSampleTable
import kotlin.math.max

/**
 * The whole-file subtitle timeline of an MP4/MOV, read from the `moov` sample tables over range
 * requests — MP4's counterpart of the Matroska Cues index ([MatroskaSubtitleIndex]).
 *
 * Every text sample (tx3g / MP4 WebVTT) has its presentation time in the sample table, so the full
 * dialogue timeline of each embedded text track is known from the `moov` box alone, without playing
 * or demuxing anything. Without this an MP4 source had no container reference at all and the scan
 * fell back to collecting lines as they rendered: House of the Dragon S02E05 MAX WEB-DL .mp4 (Sept
 * 2026, 25 embedded text tracks) sat through the recap and titles waiting for dialogue.
 *
 * Fragmented MP4 (mvex/moof) is not handled — its samples live in fragments, not in `moov` — and
 * returns null so the caller falls back exactly as before.
 */
@OptIn(UnstableApi::class)
internal object Mp4SubtitleIndex {
    private const val DEFAULT_CUE_DURATION_MS = 5_000L
    private const val MAX_MP4_MOOV_BYTES = 24 * 1024 * 1024
    private const val MAX_MP4_TOP_LEVEL_BOXES = 64
    private const val MP4_BOX_HEADER_BYTES = 16
    private const val MIN_INDEXED_CUES = 8
    private const val MIN_INDEXED_SPAN_MS = 30_000L

    private data class Mp4BoxLocation(val position: Long, val size: Long)

    private data class Mp4BoxHeader(val type: Int, val size: Long, val headerSize: Int) {
        fun dataStart(boxStart: Int): Int = boxStart + headerSize
    }

    private data class Mp4ChildRange(val start: Int, val dataStart: Int, val end: Int, val headerSize: Int)

    /** True when [head] (the file's first bytes) starts like an MP4/MOV. */
    fun looksLikeMp4(head: ByteArray): Boolean {
        val header = readMp4BoxHeader(head, 0, head.size, head.size.toLong()) ?: return false
        return isPlausibleMp4TopLevelType(header.type)
    }

    suspend fun load(
        source: MatroskaSubtitleIndex.ByteRangeSource,
        head: ByteArray,
        onDiagnostic: (String) -> Unit = {},
    ): MatroskaSubtitleIndex.IndexedTimeline? {
        val moovLocation = findMp4Moov(source, head) ?: run {
            onDiagnostic("mp4 index: moov not found")
            return null
        }
        if (moovLocation.size <= 0L || moovLocation.size > MAX_MP4_MOOV_BYTES.toLong()) {
            onDiagnostic("mp4 index: moov size ${moovLocation.size} outside the ${MAX_MP4_MOOV_BYTES}B limit")
            return null
        }
        val moovEnd = moovLocation.position + moovLocation.size
        val moovBytes = if (moovEnd <= head.size.toLong()) {
            head.copyOfRange(moovLocation.position.toInt(), moovEnd.toInt())
        } else {
            source.read(moovLocation.position, moovLocation.size.toInt())
                ?.takeIf { it.size.toLong() == moovLocation.size }
                ?: run {
                    onDiagnostic("mp4 index: moov fetch failed (position=${moovLocation.position} size=${moovLocation.size})")
                    return null
                }
        }

        val moov = parseMp4MoovTextTracks(moovBytes, onDiagnostic) ?: return null
        if (moov.containerChildren.isEmpty()) {
            onDiagnostic("mp4 index: no embedded text track in this file")
            return null
        }

        val sampleTables = try {
            // Media3 1.9 adds a trailing `omitTrackSampleTable` (the fork targets 1.8, which lacks
            // it): false, since the sample table IS the timeline this reads.
            BoxParser.parseTraks(
                moov,
                GaplessInfoHolder(),
                C.TIME_UNSET,
                null,
                false,
                isQuickTimeContainer(head),
                com.google.common.base.Function<androidx.media3.extractor.mp4.Track?, androidx.media3.extractor.mp4.Track?> { track ->
                    track?.takeIf {
                        it.type == C.TRACK_TYPE_TEXT && isSupportedIndexedMp4SubtitleMime(it.format.sampleMimeType)
                    }
                },
                false,
            )
        } catch (error: Exception) {
            onDiagnostic("mp4 index: sample-table parse failed: ${error.message}")
            return null
        }

        val tracks = sampleTables.mapNotNull(::buildMp4Track)
        if (tracks.isEmpty()) {
            onDiagnostic("mp4 index: text tracks=${sampleTables.size} but none carry a usable timeline")
            return null
        }
        onDiagnostic(
            "mp4 index: " + tracks.joinToString(" | ") {
                "track=${it.trackNumber} lang=${it.language ?: "-"} cues=${it.cues.size} span=${it.spanMs / 1000}s"
            }
        )
        return MatroskaSubtitleIndex.IndexedTimeline(tracks)
    }

    /** Jump over top-level boxes by declared size; a huge mdat costs only its header. */
    private suspend fun findMp4Moov(source: MatroskaSubtitleIndex.ByteRangeSource, head: ByteArray): Mp4BoxLocation? {
        var position = 0L
        var boxCount = 0
        var firstBox = true
        while (boxCount++ < MAX_MP4_TOP_LEVEL_BOXES) {
            val inHead = position >= 0L && position + MP4_BOX_HEADER_BYTES <= head.size.toLong()
            val headerBytes: ByteArray
            val headerOffset: Int
            if (inHead) {
                headerBytes = head
                headerOffset = position.toInt()
            } else {
                headerBytes = source.read(position, MP4_BOX_HEADER_BYTES) ?: return null
                headerOffset = 0
            }
            val header = readMp4BoxHeader(
                bytes = headerBytes,
                offset = headerOffset,
                limit = headerBytes.size,
                // Size 0 means "to the end of the file"; the total length is not known here, so a
                // box like that can only be the last one — and moov is never it in practice.
                extendsToEndSize = Long.MAX_VALUE,
            ) ?: return null
            if (firstBox) {
                if (!isPlausibleMp4TopLevelType(header.type)) return null
                firstBox = false
            }
            if (header.type == Mp4Box.TYPE_moov) return Mp4BoxLocation(position, header.size)
            if (header.size <= 0L || header.size == Long.MAX_VALUE || position > Long.MAX_VALUE - header.size) return null
            position += header.size
        }
        return null
    }

    /**
     * Build only Media3's required moov tree and retain only text trak boxes. This avoids copying
     * large video/audio sample tables a second time on memory-constrained TV hardware.
     */
    private fun parseMp4MoovTextTracks(bytes: ByteArray, onDiagnostic: (String) -> Unit): Mp4Box.ContainerBox? {
        val root = readMp4BoxHeader(bytes, 0, bytes.size, bytes.size.toLong()) ?: run {
            onDiagnostic("mp4 index: invalid moov header")
            return null
        }
        if (root.type != Mp4Box.TYPE_moov || root.size != bytes.size.toLong()) {
            onDiagnostic("mp4 index: moov mismatch (declared=${root.size} actual=${bytes.size})")
            return null
        }
        val rootEnd = root.size.toInt()
        if (hasDirectMp4Child(bytes, root.headerSize, rootEnd, Mp4Box.TYPE_mvex)) {
            // Fragmented MP4 needs moof/trun parsing; leave it to the existing fallback.
            onDiagnostic("mp4 index: fragmented MP4 (mvex) — not supported")
            return null
        }
        val moov = Mp4Box.ContainerBox(Mp4Box.TYPE_moov, rootEnd.toLong())
        var position = root.headerSize
        while (position < rootEnd) {
            val child = readMp4BoxHeader(bytes, position, rootEnd, (rootEnd - position).toLong()) ?: return null
            val childEnd = mp4BoxEnd(position, child, rootEnd) ?: return null
            when (child.type) {
                Mp4Box.TYPE_mvhd -> addMp4Leaf(moov, bytes, position, childEnd, child.type)
                Mp4Box.TYPE_trak -> {
                    if (isMp4TextTrack(bytes, child.dataStart(position), childEnd)) {
                        val parsed = parseMp4Container(bytes, position, childEnd, child) ?: run {
                            onDiagnostic("mp4 index: text trak parse failed at $position")
                            return null
                        }
                        moov.add(parsed)
                    }
                }
            }
            position = childEnd
        }
        return moov
    }

    private fun parseMp4Container(bytes: ByteArray, boxStart: Int, boxEnd: Int, header: Mp4BoxHeader): Mp4Box.ContainerBox? {
        val container = Mp4Box.ContainerBox(header.type, boxEnd.toLong())
        var position = header.dataStart(boxStart)
        while (position < boxEnd) {
            val child = readMp4BoxHeader(bytes, position, boxEnd, (boxEnd - position).toLong()) ?: return null
            val childEnd = mp4BoxEnd(position, child, boxEnd) ?: return null
            if (isNeededMp4ContainerType(child.type)) {
                container.add(parseMp4Container(bytes, position, childEnd, child) ?: return null)
            } else if (isNeededMp4LeafType(child.type)) {
                addMp4Leaf(container, bytes, position, childEnd, child.type)
            }
            position = childEnd
        }
        return container
    }

    private fun addMp4Leaf(parent: Mp4Box.ContainerBox, bytes: ByteArray, start: Int, end: Int, type: Int) {
        parent.add(Mp4Box.LeafBox(type, ParsableByteArray(bytes.copyOfRange(start, end))))
    }

    private fun isMp4TextTrack(bytes: ByteArray, trakDataStart: Int, trakEnd: Int): Boolean {
        val mdia = findDirectMp4Child(bytes, trakDataStart, trakEnd, Mp4Box.TYPE_mdia) ?: return false
        val hdlr = findDirectMp4Child(bytes, mdia.dataStart, mdia.end, Mp4Box.TYPE_hdlr) ?: return false
        // hdlr = header + version/flags + pre_defined + handler_type.
        val handlerOffset = hdlr.start + hdlr.headerSize + 8
        if (handlerOffset + 4 > hdlr.end) return false
        val handlerType = readMp4Int(bytes, handlerOffset)
        return handlerType == 0x74657874 || // text
            handlerType == 0x7362746c || // sbtl
            handlerType == 0x73756274 || // subt
            handlerType == 0x636c6370 || // clcp
            handlerType == 0x73756270 // subp
    }

    private fun buildMp4Track(table: TrackSampleTable): MatroskaSubtitleIndex.IndexedTrack? {
        val format = table.track.format
        val mimeType = format.sampleMimeType ?: return null
        if (!isSupportedIndexedMp4SubtitleMime(mimeType)) return null

        val cues = ArrayList<SubtitleSyncMatcher.TimedCue>(table.sampleCount)
        for (index in 0 until table.sampleCount) {
            // Keep gap samples as timing boundaries, but don't emit them as dialogue cues.
            if (isEmptyMp4SubtitleSample(mimeType, table.sizes[index])) continue
            val startUs = table.timestampsUs[index]
            if (startUs < 0L) continue
            val nextUs = if (index + 1 < table.sampleCount) table.timestampsUs[index + 1] else table.durationUs
            val endUs = if (nextUs == C.TIME_UNSET || nextUs <= startUs) {
                startUs + DEFAULT_CUE_DURATION_MS * 1_000L
            } else {
                nextUs
            }
            val startMs = startUs / 1_000L
            cues += SubtitleSyncMatcher.TimedCue(startMs, max(startMs + 1L, endUs / 1_000L), "")
        }
        val normalized = cues.sortedBy { it.startMs }.distinctBy { it.startMs }
        if (normalized.size < MIN_INDEXED_CUES) return null
        if (normalized.last().startMs - normalized.first().startMs < MIN_INDEXED_SPAN_MS) return null

        return MatroskaSubtitleIndex.IndexedTrack(
            trackNumber = table.track.id.toLong(),
            language = format.language?.takeIf { it.isNotBlank() },
            codecId = mimeType,
            name = format.label?.takeIf { it.isNotBlank() },
            isDefault = (format.selectionFlags and C.SELECTION_FLAG_DEFAULT) != 0,
            isForced = (format.selectionFlags and C.SELECTION_FLAG_FORCED) != 0,
            isHearingImpaired = (format.roleFlags and C.ROLE_FLAG_DESCRIBES_MUSIC_AND_SOUND) != 0,
            cues = normalized,
        )
    }

    private fun isSupportedIndexedMp4SubtitleMime(mimeType: String?): Boolean =
        mimeType == MimeTypes.APPLICATION_TX3G || mimeType == MimeTypes.APPLICATION_MP4VTT

    private fun isEmptyMp4SubtitleSample(mimeType: String, sampleSize: Int): Boolean =
        when (mimeType) {
            MimeTypes.APPLICATION_TX3G -> sampleSize <= 2
            MimeTypes.APPLICATION_MP4VTT -> sampleSize <= 8
            else -> true
        }

    private fun isQuickTimeContainer(head: ByteArray): Boolean {
        var position = 0
        while (position + 8 <= head.size && position < 4 * 1024) {
            val header = readMp4BoxHeader(head, position, head.size, (head.size - position).toLong()) ?: break
            val end = mp4BoxEnd(position, header, head.size) ?: break
            if (header.type == 0x66747970) { // ftyp
                var brandOffset = header.dataStart(position)
                while (brandOffset + 4 <= end) {
                    if (readMp4Int(head, brandOffset) == 0x71742020) return true // "qt  "
                    brandOffset += 4
                }
                return false
            }
            position = end
        }
        return false
    }

    private fun hasDirectMp4Child(bytes: ByteArray, start: Int, end: Int, type: Int): Boolean =
        findDirectMp4Child(bytes, start, end, type) != null

    private fun findDirectMp4Child(bytes: ByteArray, start: Int, end: Int, type: Int): Mp4ChildRange? {
        var position = start
        while (position < end) {
            val header = readMp4BoxHeader(bytes, position, end, (end - position).toLong()) ?: return null
            val childEnd = mp4BoxEnd(position, header, end) ?: return null
            if (header.type == type) {
                return Mp4ChildRange(position, header.dataStart(position), childEnd, header.headerSize)
            }
            position = childEnd
        }
        return null
    }

    private fun isNeededMp4ContainerType(type: Int): Boolean =
        type == Mp4Box.TYPE_mdia || type == Mp4Box.TYPE_minf || type == Mp4Box.TYPE_stbl || type == Mp4Box.TYPE_edts

    private fun isNeededMp4LeafType(type: Int): Boolean =
        type == Mp4Box.TYPE_tkhd ||
            type == Mp4Box.TYPE_mdhd ||
            type == Mp4Box.TYPE_hdlr ||
            type == Mp4Box.TYPE_stsd ||
            type == Mp4Box.TYPE_stts ||
            type == Mp4Box.TYPE_ctts ||
            type == Mp4Box.TYPE_stsc ||
            type == Mp4Box.TYPE_stsz ||
            type == Mp4Box.TYPE_stz2 ||
            type == Mp4Box.TYPE_stco ||
            type == Mp4Box.TYPE_co64 ||
            type == Mp4Box.TYPE_stss ||
            type == Mp4Box.TYPE_elst

    private fun isPlausibleMp4TopLevelType(type: Int): Boolean =
        type == 0x66747970 || // ftyp
            type == Mp4Box.TYPE_moov ||
            type == 0x6d646174 || // mdat
            type == 0x66726565 || // free
            type == 0x736b6970 || // skip
            type == 0x77696465 || // wide
            type == 0x706e6f74 || // pnot (older QuickTime)
            type == 0x75756964 || // uuid
            type == 0x7064696e || // pdin
            type == 0x6d6f6f66 || // moof
            type == 0x73696478 || // sidx
            type == 0x73747970 // styp

    private fun readMp4BoxHeader(bytes: ByteArray, offset: Int, limit: Int, extendsToEndSize: Long): Mp4BoxHeader? {
        if (offset < 0 || limit > bytes.size || offset + 8 > limit) return null
        val size32 = readMp4UnsignedInt(bytes, offset)
        val type = readMp4Int(bytes, offset + 4)
        var headerSize = 8
        val size = when (size32) {
            0L -> extendsToEndSize
            1L -> {
                if (offset + 16 > limit) return null
                headerSize = 16
                readMp4UnsignedLong(bytes, offset + 8) ?: return null
            }
            else -> size32
        }
        if (size < headerSize.toLong()) return null
        return Mp4BoxHeader(type, size, headerSize)
    }

    private fun mp4BoxEnd(start: Int, header: Mp4BoxHeader, limit: Int): Int? {
        if (header.size > Int.MAX_VALUE.toLong()) return null
        val end = start.toLong() + header.size
        if (end <= start.toLong() || end > limit.toLong()) return null
        return end.toInt()
    }

    private fun readMp4UnsignedInt(bytes: ByteArray, offset: Int): Long =
        ((bytes[offset].toLong() and 0xFFL) shl 24) or
            ((bytes[offset + 1].toLong() and 0xFFL) shl 16) or
            ((bytes[offset + 2].toLong() and 0xFFL) shl 8) or
            (bytes[offset + 3].toLong() and 0xFFL)

    private fun readMp4Int(bytes: ByteArray, offset: Int): Int =
        ((bytes[offset].toInt() and 0xFF) shl 24) or
            ((bytes[offset + 1].toInt() and 0xFF) shl 16) or
            ((bytes[offset + 2].toInt() and 0xFF) shl 8) or
            (bytes[offset + 3].toInt() and 0xFF)

    private fun readMp4UnsignedLong(bytes: ByteArray, offset: Int): Long? {
        if ((bytes[offset].toInt() and 0x80) != 0) return null
        var value = 0L
        for (index in 0 until 8) value = (value shl 8) or (bytes[offset + index].toLong() and 0xFFL)
        return value
    }
}
