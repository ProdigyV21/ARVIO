package com.arflix.tv.ui.screens.player

import com.arflix.tv.ui.screens.player.subtitles.MatroskaSubtitleIndex
import com.arflix.tv.ui.screens.player.subtitles.SubtitleSyncMatcher
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Test

/**
 * Matroska Cues-index reading: the find-best-match reference taken from the container instead of
 * the player's text buffer. Built on synthetic EBML so the parser is exercised without network,
 * player or a real remux.
 */
class MatroskaSubtitleIndexTest {

    // ── EBML writers (test-only; mirror the spec the parser reads) ──────────────

    private fun idBytes(id: Long): ByteArray {
        val length = when {
            id <= 0xFFL -> 1
            id <= 0xFFFFL -> 2
            id <= 0xFFFFFFL -> 3
            else -> 4
        }
        return ByteArray(length) { index -> ((id shr (8 * (length - 1 - index))) and 0xFF).toByte() }
    }

    /** EBML data size: a length marker bit, then the value in the remaining bits. */
    private fun sizeBytes(size: Long): ByteArray {
        for (length in 1..8) {
            val capacity = (1L shl (7 * length)) - 1
            if (size < capacity) {
                val out = ByteArray(length)
                var remaining = size
                for (index in length - 1 downTo 0) {
                    out[index] = (remaining and 0xFF).toByte()
                    remaining = remaining shr 8
                }
                out[0] = (out[0].toInt() or (0x80 shr (length - 1))).toByte()
                return out
            }
        }
        error("size too large: $size")
    }

    private fun uintBytes(value: Long): ByteArray {
        if (value == 0L) return byteArrayOf(0)
        var length = 0
        var probe = value
        while (probe > 0) { length++; probe = probe shr 8 }
        return ByteArray(length) { index -> ((value shr (8 * (length - 1 - index))) and 0xFF).toByte() }
    }

    private fun elem(id: Long, payload: ByteArray): ByteArray = idBytes(id) + sizeBytes(payload.size.toLong()) + payload
    private fun elem(id: Long, value: Long): ByteArray = elem(id, uintBytes(value))
    private fun elem(id: Long, text: String): ByteArray = elem(id, text.toByteArray(Charsets.US_ASCII))
    private fun cat(vararg parts: ByteArray): ByteArray = parts.reduce { a, b -> a + b }

    private val idEbml = 0x1A45DFA3L
    private val idSegment = 0x18538067L
    private val idSeekHead = 0x114D9B74L
    private val idSeek = 0x4DBBL
    private val idSeekId = 0x53ABL
    private val idSeekPosition = 0x53ACL
    private val idInfo = 0x1549A966L
    private val idTimestampScale = 0x2AD7B1L
    private val idTracks = 0x1654AE6BL
    private val idTrackEntry = 0xAEL
    private val idTrackNumber = 0xD7L
    private val idTrackType = 0x83L
    private val idLanguage = 0x22B59CL
    private val idCodecId = 0x86L
    private val idCues = 0x1C53BB6BL
    private val idCuePoint = 0xBBL
    private val idCueTime = 0xB3L
    private val idCueTrackPositions = 0xB7L
    private val idCueTrack = 0xF7L
    private val idCueDuration = 0xB2L

    private fun subtitleTrack(number: Long, language: String) = elem(
        idTrackEntry,
        cat(
            elem(idTrackNumber, number),
            elem(idTrackType, 17L),
            elem(idLanguage, language),
            elem(idCodecId, "S_TEXT/UTF8"),
        )
    )

    private fun videoTrack(number: Long) = elem(
        idTrackEntry,
        cat(elem(idTrackNumber, number), elem(idTrackType, 1L), elem(idCodecId, "V_MPEG4/ISO/AVC"))
    )

    private fun cuePoint(timeTicks: Long, track: Long, durationTicks: Long? = null): ByteArray {
        val positions = if (durationTicks == null) {
            elem(idCueTrack, track)
        } else {
            cat(elem(idCueTrack, track), elem(idCueDuration, durationTicks))
        }
        return elem(idCuePoint, cat(elem(idCueTime, timeTicks), elem(idCueTrackPositions, positions)))
    }

    /** A file whose Segment children sit in document order, all inside the head probe. */
    private fun buildFile(
        tracks: ByteArray,
        cues: ByteArray,
        timestampScaleNs: Long = 1_000_000L,
        withSeekHead: Boolean = false,
        padBeforeCues: Int = 0,
    ): ByteArray {
        val header = elem(idEbml, elem(0x4286L, 1L)) // EBMLVersion — content is not inspected
        val info = elem(idInfo, elem(idTimestampScale, timestampScaleNs))
        val padding = if (padBeforeCues > 0) elem(0xECL, ByteArray(padBeforeCues)) else ByteArray(0) // Void
        val body = if (!withSeekHead) {
            cat(info, tracks, padding, cues)
        } else {
            // SeekHead positions are relative to the Segment's data start, so they can only be
            // computed once the preceding children are sized — build it with a placeholder first.
            fun seekHeadFor(tracksPosition: Long, cuesPosition: Long) = elem(
                idSeekHead,
                cat(
                    elem(idSeek, cat(elem(idSeekId, idBytes(idTracks)), elem(idSeekPosition, tracksPosition))),
                    elem(idSeek, cat(elem(idSeekId, idBytes(idCues)), elem(idSeekPosition, cuesPosition))),
                )
            )
            var seekHead = seekHeadFor(0L, 0L)
            repeat(3) { // converges immediately; sizes only change if the varint width changes
                val tracksPosition = (seekHead.size + info.size).toLong()
                val cuesPosition = tracksPosition + tracks.size + padding.size
                seekHead = seekHeadFor(tracksPosition, cuesPosition)
            }
            val tracksPosition = (seekHead.size + info.size).toLong()
            val cuesPosition = tracksPosition + tracks.size + padding.size
            cat(seekHeadFor(tracksPosition, cuesPosition), info, tracks, padding, cues)
        }
        return cat(header, elem(idSegment, body))
    }

    private class MemorySource(private val bytes: ByteArray) : MatroskaSubtitleIndex.ByteRangeSource {
        var requests = 0
            private set

        override suspend fun read(offset: Long, length: Int): ByteArray? {
            requests++
            if (offset < 0 || offset >= bytes.size) return null
            val end = minOf(bytes.size.toLong(), offset + length).toInt()
            return bytes.copyOfRange(offset.toInt(), end)
        }
    }

    private fun tenCues(track: Long, stepMs: Long = 5_000L, durationTicks: Long? = null): ByteArray =
        elem(idCues, (0 until 10).map { cuePoint(it * stepMs, track, durationTicks) }.reduce { a, b -> a + b })

    // ── Tests ───────────────────────────────────────────────────────────────────

    @Test
    fun `reads subtitle cue times from the cues index`() = runBlocking {
        val file = buildFile(
            tracks = elem(idTracks, cat(videoTrack(1L), subtitleTrack(2L, "eng"))),
            cues = tenCues(track = 2L),
        )

        val timeline = MatroskaSubtitleIndex.load(MemorySource(file))

        assertThat(timeline).isNotNull()
        val track = timeline!!.tracks.single()
        assertThat(track.trackNumber).isEqualTo(2L)
        assertThat(track.language).isEqualTo("eng")
        assertThat(track.cues).hasSize(10)
        assertThat(track.cues.first().startMs).isEqualTo(0L)
        assertThat(track.cues.last().startMs).isEqualTo(45_000L)
        // No CueDuration authored → nominal 2s, matching BufferedCueReader's assumption.
        assertThat(track.cues.first().endMs).isEqualTo(2_000L)
    }

    @Test
    fun `resolves a typical file in a couple of range requests`() = runBlocking {
        // Requests are the cost that lands on the user's stream host while they watch, so the
        // count is part of the contract, not an implementation detail. A layout whose metadata
        // sits in the head probe must not re-fetch what it already parsed.
        val file = buildFile(
            tracks = elem(idTracks, cat(videoTrack(1L), subtitleTrack(2L, "eng"))),
            cues = tenCues(track = 2L),
        )
        val source = MemorySource(file)

        assertThat(MatroskaSubtitleIndex.load(source)).isNotNull()

        // Everything (including Cues) is inside the head read here, so nothing may be re-fetched.
        assertThat(source.requests).isEqualTo(1)
    }

    @Test
    fun `cues stored at the end of the file cost one extra request`() = runBlocking {
        // The normal layout: Cues written after the Clusters, reachable only via SeekHead. Two is
        // the floor — one read at the head, one at the far end — because a range request covers a
        // single contiguous span and everything between them is the video itself.
        val file = buildFile(
            tracks = elem(idTracks, cat(videoTrack(1L), subtitleTrack(2L, "eng"))),
            cues = tenCues(track = 2L),
            withSeekHead = true,
            padBeforeCues = 4096,
        )
        val source = MemorySource(file)

        assertThat(MatroskaSubtitleIndex.load(source, headProbeBytes = 64)).isNotNull()

        assertThat(source.requests).isAtMost(3)
    }

    @Test
    fun `honours authored cue durations`() = runBlocking {
        val file = buildFile(
            tracks = elem(idTracks, subtitleTrack(1L, "heb")),
            cues = tenCues(track = 1L, durationTicks = 3_500L),
        )

        val track = MatroskaSubtitleIndex.load(MemorySource(file))!!.tracks.single()

        assertThat(track.cues.first().endMs).isEqualTo(3_500L)
        assertThat(track.language).isEqualTo("heb")
    }

    @Test
    fun `applies the timestamp scale`() = runBlocking {
        // 500000ns per tick — each tick is half a millisecond.
        val file = buildFile(
            tracks = elem(idTracks, subtitleTrack(2L, "eng")),
            cues = tenCues(track = 2L, stepMs = 10_000L),
            timestampScaleNs = 500_000L,
        )

        val track = MatroskaSubtitleIndex.load(MemorySource(file))!!.tracks.single()

        assertThat(track.cues.last().startMs).isEqualTo(45_000L) // 90_000 ticks * 0.5ms
    }

    @Test
    fun `follows seekhead when tracks and cues sit past the head probe`() = runBlocking {
        val file = buildFile(
            tracks = elem(idTracks, cat(videoTrack(1L), subtitleTrack(2L, "eng"))),
            cues = tenCues(track = 2L),
            withSeekHead = true,
            padBeforeCues = 4096,
        )
        val source = MemorySource(file)

        // Head probe deliberately too small to contain Tracks/Cues: they must be fetched by
        // position from the SeekHead, which is the layout of every real remux.
        val timeline = MatroskaSubtitleIndex.load(source, headProbeBytes = 64)

        assertThat(timeline).isNotNull()
        assertThat(timeline!!.tracks.single().cues).hasSize(10)
        assertThat(source.requests).isAtLeast(2)
    }

    @Test
    fun `returns null when the index only covers video`() = runBlocking {
        val file = buildFile(
            tracks = elem(idTracks, cat(videoTrack(1L), subtitleTrack(2L, "eng"))),
            cues = tenCues(track = 1L), // video track indexed, subtitle track absent
        )

        assertThat(MatroskaSubtitleIndex.load(MemorySource(file))).isNull()
    }

    @Test
    fun `too few cue points is no reference, reported as a sparse track`() = runBlocking {
        val file = buildFile(
            tracks = elem(idTracks, subtitleTrack(2L, "eng")),
            cues = elem(idCues, (0 until 4).map { cuePoint(it * 5_000L, 2L) }.reduce { a, b -> a + b }),
        )

        val timeline = MatroskaSubtitleIndex.load(MemorySource(file))!!

        // Not a usable reference — but "this English track has 4 cues" is itself the answer the
        // scan needs to stop waiting for dialogue that is not there (Selling Sunset GalaxyTV).
        assertThat(timeline.tracks).isEmpty()
        assertThat(timeline.sparseTracks.single().cues).hasSize(4)
    }

    @Test
    fun `a span too short to describe the file is no reference`() = runBlocking {
        // 10 cues, but all inside the first 9 seconds — a title sequence, not a reference.
        val file = buildFile(
            tracks = elem(idTracks, subtitleTrack(2L, "eng")),
            cues = tenCues(track = 2L, stepMs = 1_000L),
        )

        val timeline = MatroskaSubtitleIndex.load(MemorySource(file))!!

        assertThat(timeline.tracks).isEmpty()
        assertThat(timeline.sparseTracks.single().trackNumber).isEqualTo(2L)
    }

    private fun cue(startMs: Long) = SubtitleSyncMatcher.TimedCue(startMs, startMs + 2_000L, "")

    @Test
    fun `agrees when observed cues line up with the index`() {
        val index = (0 until 200).map { cue(it * 3_000L) }
        // What the player's buffer would report for the same track, with parse-level jitter.
        val observed = listOf(cue(30_012L), cue(33_000L), cue(35_990L), cue(39_000L))

        assertThat(MatroskaSubtitleIndex.agreesWithObserved(index, observed)).isTrue()
    }

    @Test
    fun `disagrees when the observed cues belong to another track`() {
        val index = (0 until 200).map { cue(it * 3_000L) }
        // A different track in the same language: same era of the film, unrelated cue starts.
        val observed = listOf(cue(31_400L), cue(34_600L), cue(37_450L), cue(40_800L))

        assertThat(MatroskaSubtitleIndex.agreesWithObserved(index, observed)).isFalse()
    }

    @Test
    fun `disagrees when either side is empty`() {
        val index = (0 until 20).map { cue(it * 3_000L) }

        assertThat(MatroskaSubtitleIndex.agreesWithObserved(index, emptyList())).isFalse()
        assertThat(MatroskaSubtitleIndex.agreesWithObserved(emptyList(), listOf(cue(0L)))).isFalse()
    }

    private fun track(number: Long, language: String?, forced: Boolean = false) =
        MatroskaSubtitleIndex.IndexedTrack(
            trackNumber = number,
            language = language,
            codecId = "S_TEXT/UTF8",
            name = null,
            isDefault = true,
            isForced = forced,
            isHearingImpaired = false,
            cues = listOf(cue(0L)),
        )

    @Test
    fun `matches an ietf regional tag against the plain language`() {
        val tracks = listOf(track(9, "ru"), track(10, "en-US"), track(11, "en-US"))

        val picked = MatroskaSubtitleIndex.pickTrackForLanguage(tracks, "en")

        assertThat(picked?.trackNumber).isEqualTo(10L)
    }

    @Test
    fun `never falls back to a different language`() {
        val tracks = listOf(track(9, "ru"), track(10, "fr"))

        assertThat(MatroskaSubtitleIndex.pickTrackForLanguage(tracks, "en")).isNull()
    }

    @Test
    fun `matches two and three letter forms of the same language`() {
        assertThat(MatroskaSubtitleIndex.pickTrackForLanguage(listOf(track(1, "eng")), "en")?.trackNumber)
            .isEqualTo(1L)
        assertThat(MatroskaSubtitleIndex.pickTrackForLanguage(listOf(track(1, "heb")), "he")?.trackNumber)
            .isEqualTo(1L)
        assertThat(MatroskaSubtitleIndex.pickTrackForLanguage(listOf(track(1, "he")), "iw")?.trackNumber)
            .isEqualTo(1L)
    }

    @Test
    fun `skips forced tracks`() {
        val tracks = listOf(track(1, "en", forced = true), track(2, "en"))

        assertThat(MatroskaSubtitleIndex.pickTrackForLanguage(tracks, "en")?.trackNumber).isEqualTo(2L)
    }

    @Test
    fun `an untagged track defaults to english per the matroska spec`() = runBlocking {
        val untagged = elem(
            idTrackEntry,
            cat(elem(idTrackNumber, 2L), elem(idTrackType, 17L), elem(idCodecId, "S_TEXT/UTF8"))
        )
        val file = buildFile(tracks = elem(idTracks, untagged), cues = tenCues(track = 2L))

        val parsed = MatroskaSubtitleIndex.load(MemorySource(file))!!.tracks.single()

        assertThat(parsed.language).isEqualTo("eng")
        assertThat(MatroskaSubtitleIndex.pickTrackForLanguage(listOf(parsed), "en")).isNotNull()
    }

    // ── Segment-consistent offset ───────────────────────────────────────────────

    /** A reference window every 10s, and cues shifted from it by [offsetMs]. */
    private fun refsEvery10s(count: Int) = (0 until count).map { it * 10_000L to it * 10_000L + 2_000L }

    private fun cuesShiftedBy(count: Int, offsetMs: Long) =
        (0 until count).map { SubtitleSyncMatcher.TimedCue(it * 10_000L - offsetMs, it * 10_000L - offsetMs + 2_000L, "") }

    @Test
    fun `accepts an offset that fits the whole file`() {
        val refs = refsEvery10s(30)
        // Uniformly 2s early everywhere — the House of the Dragon shape.
        val cues = cuesShiftedBy(30, 2_000L)

        val fit = SubtitleSyncMatcher.segmentConsistentOffset(cues, refs, minOffsetMs = 300L, maxOffsetMs = 10_000L)

        assertThat(fit).isNotNull()
        // Truth's isWithin is for floating point; the offset is a Long, so bound it explicitly.
        assertThat(fit!!.offsetMs).isAtLeast(1_850L)
        assertThat(fit.offsetMs).isAtMost(2_150L)
        assertThat(fit.correctedScore).isGreaterThan(fit.baseScore)
    }

    @Test
    fun `rejects an offset that only fits part of the file`() {
        val refs = refsEvery10s(30)
        // First third needs +2s, the rest needs nothing — a different cut, not a delay.
        // (South Park S06E02 read 2025 / 6900 / 6700 ms across its thirds.)
        val cues = (0 until 30).map { index ->
            val shift = if (index < 10) 2_000L else 0L
            SubtitleSyncMatcher.TimedCue(index * 10_000L - shift, index * 10_000L - shift + 2_000L, "")
        }

        assertThat(
            SubtitleSyncMatcher.segmentConsistentOffset(cues, refs, minOffsetMs = 300L, maxOffsetMs = 10_000L)
        ).isNull()
    }

    @Test
    fun `reports nothing when the subtitle is already aligned`() {
        val refs = refsEvery10s(30)
        val cues = cuesShiftedBy(30, 0L)

        assertThat(
            SubtitleSyncMatcher.segmentConsistentOffset(cues, refs, minOffsetMs = 300L, maxOffsetMs = 10_000L)
        ).isNull()
    }

    @Test
    fun `needs enough windows to slice`() {
        val refs = refsEvery10s(9)
        val cues = cuesShiftedBy(9, 2_000L)

        assertThat(
            SubtitleSyncMatcher.segmentConsistentOffset(cues, refs, minOffsetMs = 300L, maxOffsetMs = 10_000L)
        ).isNull()
    }

    /** A track with [count] cues evenly spread over [spanMs]. */
    private fun shapedTrack(
        number: Long,
        count: Int,
        spanMs: Long = 3_000_000L,
        forced: Boolean = false,
        codec: String = "S_TEXT/UTF8",
        name: String? = null,
    ) = MatroskaSubtitleIndex.IndexedTrack(
        trackNumber = number,
        language = "en",
        codecId = codec,
        name = name,
        isDefault = true,
        isForced = forced,
        isHearingImpaired = false,
        cues = (0 until count).map { cue(it * spanMs / (count - 1).coerceAtLeast(1)) },
    )

    @Test
    fun `the preferred-language reference is never an image track`() {
        // An English PGS track listed before the English text track: its index is show/clear
        // noise and must not become the timing reference (PR #756 review).
        val pgs = shapedTrack(3, 1_400, codec = "S_HDMV/PGS")
        val text = shapedTrack(4, 700)

        assertThat(MatroskaSubtitleIndex.pickTrackForLanguage(listOf(pgs, text), "en")?.trackNumber).isEqualTo(4L)
        assertThat(MatroskaSubtitleIndex.pickTrackForLanguage(listOf(pgs), "en")).isNull()
    }

    @Test
    fun `alternate references are text tracks only`() {
        val primary = shapedTrack(2, 700)
        val pgs = shapedTrack(3, 1_400, codec = "S_HDMV/PGS")
        val vobsub = shapedTrack(4, 1_200, codec = "S_VOBSUB")
        val russian = shapedTrack(5, 600)
        val forced = shapedTrack(6, 650, forced = true)

        val alternates = MatroskaSubtitleIndex.alternateReferenceTracks(
            tracks = listOf(primary, pgs, vobsub, russian, forced),
            primaryTrackNumber = 2L,
            allowForced = false,
            minCues = 8,
            minCuesPerMinute = 2.0,
            max = 3
        )
        assertThat(alternates.map { it.trackNumber }).containsExactly(5L)

        val shapePath = MatroskaSubtitleIndex.alternateReferenceTracks(
            tracks = listOf(primary, pgs, vobsub, russian, forced),
            primaryTrackNumber = 2L,
            allowForced = true,
            minCues = 8,
            minCuesPerMinute = 2.0,
            max = 3
        )
        assertThat(shapePath.map { it.trackNumber }).containsExactly(6L, 5L).inOrder()
    }

    @Test
    fun `the timing-shape fallback skips image tracks`() {
        val picked = MatroskaSubtitleIndex.pickReferenceTracks(
            listOf(shapedTrack(3, 1_400, codec = "S_HDMV/PGS"), shapedTrack(4, 700)),
            2.0
        )
        assertThat(picked.map { it.trackNumber }).containsExactly(4L)
    }

    @Test
    fun `a dense track flagged forced is the reference when it is the only one`() {
        // Peaky Blinders S01E04, MoviezAddiction "ESub": the only embedded text track was a full
        // English subtitle flagged forced.
        val picked = MatroskaSubtitleIndex.pickReferenceTracks(listOf(shapedTrack(3, 700, forced = true)), 2.0)

        assertThat(picked.map { it.trackNumber }).containsExactly(3L)
    }

    @Test
    fun `a genuinely forced track is too sparse to be a reference`() {
        // Signs-and-songs tracks carry a few cues over the whole film (The Shards: 17 over 40 min).
        val picked = MatroskaSubtitleIndex.pickReferenceTracks(listOf(shapedTrack(9, 17, forced = true)), 2.0)

        assertThat(picked).isEmpty()
    }

    @Test
    fun `unforced dialogue tracks outrank a dense forced one`() {
        val picked = MatroskaSubtitleIndex.pickReferenceTracks(
            listOf(shapedTrack(3, 900, forced = true), shapedTrack(4, 600), shapedTrack(5, 700)),
            2.0,
        )

        assertThat(picked.map { it.trackNumber }).containsExactly(5L, 4L).inOrder()
    }

    @Test
    fun `image tracks are never a timing-shape reference`() {
        val picked = MatroskaSubtitleIndex.pickReferenceTracks(listOf(shapedTrack(3, 1400, codec = "S_HDMV/PGS")), 2.0)

        assertThat(picked).isEmpty()
    }

    @Test
    fun `a speed mismatch shows as offsets walking across the thirds`() {
        // Reference runs 0.1% fast against the subtitle (24 vs 23.976) over a 40-minute span —
        // The Shards S01E03's built-in English track against a correct Hebrew subtitle.
        val refs = (0 until 60).map { index ->
            val start = index * 40_000L
            val drifted = (start / 1.001).toLong()
            drifted to drifted + 2_000L
        }
        val cues = (0 until 60).map { SubtitleSyncMatcher.TimedCue(it * 40_000L, it * 40_000L + 2_000L, "") }

        val thirds = SubtitleSyncMatcher.segmentOffsets(cues, refs, maxOffsetMs = 10_000L)!!

        // Each later third needs a larger negative shift; no constant offset fits them all.
        assertThat(thirds[0]).isGreaterThan(thirds[1])
        assertThat(thirds[1]).isGreaterThan(thirds[2])
        assertThat(thirds.first() - thirds.last()).isGreaterThan(500L)
        assertThat(
            SubtitleSyncMatcher.segmentConsistentOffset(cues, refs, minOffsetMs = 300L, maxOffsetMs = 10_000L)
        ).isNull()
    }

    @Test
    fun `returns null for a non-matroska file`() = runBlocking {
        val mp4 = byteArrayOf(0, 0, 0, 0x18) + "ftypmp42".toByteArray(Charsets.US_ASCII) + ByteArray(64)

        assertThat(MatroskaSubtitleIndex.load(MemorySource(mp4))).isNull()
    }
}
