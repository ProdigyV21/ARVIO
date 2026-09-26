package com.arflix.tv.ui.screens.player.subtitles

/**
 * Reads an embedded subtitle track's cue *timings* straight out of a Matroska container's Cues
 * index, using HTTP range requests — without playing, seeking or decoding anything.
 *
 * Why this exists: the find-best-match scan needs a synced reference timeline, and until now the
 * only sources were the running player's text-renderer buffer (which only ever holds what is
 * decoded *ahead of the playhead* — typically 2–12 cues, and near-empty on a file started at 0:00)
 * or realtime collection as cues render (minutes of wall time, and a systematic render-lag skew).
 * A headless second player was tried and removed: demuxing the interleaved video to reach the muxed
 * subtitle track cost ~260 MB of extra heap.
 *
 * Matroska already carries the answer. Muxers are recommended to index every subtitle frame in the
 * Cues element, so on a normal remux the whole subtitle timeline is readable from a few megabytes
 * of container metadata, before the first frame is shown.
 *
 * Deliberately best-effort: MP4/HLS/DASH, servers without range support, and files whose Cues only
 * index video all return null, and the caller falls back to the existing in-player reference.
 *
 * **Timings only, no text.** The Cues index stores positions and timestamps, not cue content, so
 * this can drive timing comparison but never AI line-pairing (which needs the words). [endMs] is
 * derived from the optional `CueDuration`, and where a muxer omits it (the common case) a nominal
 * duration is used — the same assumption `BufferedCueReader` already makes, for the same reason:
 * the start timestamp is what carries the alignment signal.
 */
internal object MatroskaSubtitleIndex {

    /** Assumed on-screen time for a cue point with no authored `CueDuration`. */
    private const val NOMINAL_CUE_DURATION_MS = 2_000L

    /** First read when fetching a positioned element: big enough that most fit in one request. */
    private const val FIRST_CHUNK_BYTES = 128 * 1024

    /** Below these an index is not worth trusting as a reference — fall back to the player. */
    private const val MIN_INDEXED_CUES = 8
    private const val MIN_INDEXED_SPAN_MS = 30_000L

    // ── EBML / Matroska element ids ─────────────────────────────────────────────
    private const val ID_EBML = 0x1A45DFA3L
    private const val ID_SEGMENT = 0x18538067L
    private const val ID_SEEK_HEAD = 0x114D9B74L
    private const val ID_SEEK = 0x4DBBL
    private const val ID_SEEK_ID = 0x53ABL
    private const val ID_SEEK_POSITION = 0x53ACL
    private const val ID_INFO = 0x1549A966L
    private const val ID_TIMESTAMP_SCALE = 0x2AD7B1L
    private const val ID_TRACKS = 0x1654AE6BL
    private const val ID_TRACK_ENTRY = 0xAEL
    private const val ID_TRACK_NUMBER = 0xD7L
    private const val ID_TRACK_TYPE = 0x83L
    private const val ID_FLAG_DEFAULT = 0x88L
    private const val ID_FLAG_FORCED = 0x55AAL
    private const val ID_FLAG_HEARING_IMPAIRED = 0x55ABL
    private const val ID_NAME = 0x536EL
    private const val ID_LANGUAGE = 0x22B59CL
    private const val ID_LANGUAGE_IETF = 0x22B59DL
    private const val ID_CODEC_ID = 0x86L
    private const val ID_CUES = 0x1C53BB6BL
    private const val ID_CUE_POINT = 0xBBL
    private const val ID_CUE_TIME = 0xB3L
    private const val ID_CUE_TRACK_POSITIONS = 0xB7L
    private const val ID_CUE_TRACK = 0xF7L
    private const val ID_CUE_DURATION = 0xB2L

    private const val TRACK_TYPE_SUBTITLE = 17L
    private const val DEFAULT_TIMESTAMP_SCALE_NS = 1_000_000L

    /** Bounded, cancellable random access to the remote file. */
    internal interface ByteRangeSource {
        /**
         * Reads [length] bytes at [offset], or null when the range is unavailable (no range
         * support, budget exhausted, transport error). May return fewer bytes at end of file.
         */
        suspend fun read(offset: Long, length: Int): ByteArray?
    }

    /** One embedded subtitle track, with the cue times the container's index carries for it. */
    internal data class IndexedTrack(
        val trackNumber: Long,
        val language: String?,
        val codecId: String?,
        val name: String?,
        val isDefault: Boolean,
        val isForced: Boolean,
        val isHearingImpaired: Boolean,
        val cues: List<SubtitleSyncMatcher.TimedCue>,
        /**
         * Starts of the cues whose end is the nominal guess (no `CueDuration` indexed). Only their
         * starts carry timing evidence; the retimer's DP is told so rather than trusting the ends.
         */
        val estimatedEndStartsMs: Set<Long> = emptySet(),
    ) {
        val spanMs: Long
            get() = if (cues.size < 2) 0L else cues.last().endMs - cues.first().startMs
    }

    internal data class IndexedTimeline(
        val tracks: List<IndexedTrack>,
        /** Absolute byte offset of the Segment body — CueClusterPosition is relative to it. */
        val segmentDataStart: Long = 0L,
        val timestampScaleNs: Long = DEFAULT_TIMESTAMP_SCALE_NS,
        /**
         * Subtitle tracks the index DID cover but that are too sparse to be a dialogue timeline
         * (fewer than MIN_INDEXED_CUES cues, or under MIN_INDEXED_SPAN_MS). Reported rather than
         * dropped: "this file's English track has 1 cue" is an answer, not a miss — see
         * PlayerViewModel's not-dialogue exit.
         */
        val sparseTracks: List<IndexedTrack> = emptyList(),
    )

    internal fun sampleCues(cues: List<SubtitleSyncMatcher.TimedCue>, limit: Int): List<SubtitleSyncMatcher.TimedCue> {
        require(limit >= 2)
        if (cues.size <= limit) return cues
        return List(limit) { index -> cues[(index.toLong() * (cues.size - 1) / (limit - 1)).toInt()] }
    }

    /**
     * Parses the container's subtitle index, or returns null when this file cannot supply one.
     *
     * Only the metadata elements are fetched: the EBML head, SeekHead, Info, Tracks and Cues —
     * never a Cluster, so no media data is transferred.
     *
     * [onDiagnostic] receives the one-line outcome. Logging is the caller's, deliberately: this
     * object stays free of `android.util.Log` so the parser is unit-testable on the JVM without
     * relaxing unmocked-Android behaviour for every other test in the module.
     */
    suspend fun load(
        source: ByteRangeSource,
        headProbeBytes: Int = 512 * 1024,
        maxTracksBytes: Int = 4 * 1024 * 1024,
        maxCuesBytes: Int = 8 * 1024 * 1024,
        onDiagnostic: (String) -> Unit = {},
    ): IndexedTimeline? {
        val head = source.read(0L, headProbeBytes) ?: return null
        if (head.size < 8) return null
        // MP4/MOV carry the same whole-file timeline in their moov sample tables, so an MP4 source gets a container reference too.
        if (Mp4SubtitleIndex.looksLikeMp4(head)) return Mp4SubtitleIndex.load(source, head, onDiagnostic)

        val ebml = readHeader(head, 0, 0L) ?: return null
        if (ebml.id != ID_EBML) {
            onDiagnostic("matroska index: not a Matroska/WebM container")
            return null
        }

        val segment = scanForElement(head, ebml.endOffset.toInt(), ID_SEGMENT) ?: run {
            onDiagnostic("matroska index: no Segment element in the head probe")
            return null
        }
        val segmentDataStart = segment.dataOffset

        // Top-level children that already sit inside the head probe. A well-muxed file puts
        // SeekHead first, but Info/Tracks are often right there too, which saves a round trip.
        var timestampScaleNs = DEFAULT_TIMESTAMP_SCALE_NS
        // Whether Info was actually read, which the scale value cannot tell us: almost every file
        // sets TimestampScale to exactly the default 1,000,000 ns, so comparing against the
        // default treated a successful in-head parse as "not read yet" and re-fetched Info over
        // the network — two wasted requests on essentially every stream.
        var infoParsed = false
        var tracksBytes: ByteArray? = null
        // Cues normally sits after every Cluster (a muxer only knows the offsets once the file is
        // written), which is why reading it needs a second request at the far end of the file.
        // Some muxers write it up front instead — then it is already in these bytes and the whole
        // index costs a single request.
        var cuesBytesFromHead: ByteArray? = null
        var cuesPosition: Long? = null
        var tracksPosition: Long? = null
        var infoPosition: Long? = null

        var cursor = segmentDataStart.toInt()
        while (cursor in 0 until head.size) {
            val child = readHeader(head, cursor, 0L) ?: break
            val bodyStart = child.dataOffset.toInt()
            val bodyEnd = if (child.size == UNKNOWN_SIZE) head.size else (bodyStart + child.size).toInt()
            val available = bodyEnd.coerceAtMost(head.size)
            when (child.id) {
                ID_SEEK_HEAD -> if (available > bodyStart) {
                    val seeks = parseSeekHead(head.copyOfRange(bodyStart, available), segmentDataStart)
                    cuesPosition = cuesPosition ?: seeks[ID_CUES]
                    tracksPosition = tracksPosition ?: seeks[ID_TRACKS]
                    infoPosition = infoPosition ?: seeks[ID_INFO]
                }
                ID_INFO -> if (available > bodyStart) {
                    timestampScaleNs = parseTimestampScale(head.copyOfRange(bodyStart, available))
                    infoParsed = true
                }
                ID_TRACKS -> if (available >= bodyEnd && child.size != UNKNOWN_SIZE) {
                    tracksBytes = head.copyOfRange(bodyStart, bodyEnd)
                }
                ID_CUES -> if (child.size != UNKNOWN_SIZE) {
                    cuesPosition = child.offset
                    if (available >= bodyEnd) cuesBytesFromHead = head.copyOfRange(bodyStart, bodyEnd)
                }
            }
            if (child.size == UNKNOWN_SIZE) break // Cluster with unknown size — stop walking.
            cursor = (child.dataOffset + child.size).toInt()
            if (child.dataOffset + child.size > Int.MAX_VALUE) break
        }

        // Info may live past the head probe; the timestamp scale is what converts ticks to ms.
        if (!infoParsed && infoPosition != null) {
            fetchElement(source, infoPosition, ID_INFO, maxBytes = 512 * 1024)?.let {
                timestampScaleNs = parseTimestampScale(it)
            }
        }

        if (tracksBytes == null && tracksPosition != null) {
            tracksBytes = fetchElement(source, tracksPosition, ID_TRACKS, maxTracksBytes)
        }
        val tracks = tracksBytes?.let(::parseSubtitleTracks).orEmpty()
        if (tracks.isEmpty()) {
            onDiagnostic(
                if (tracksBytes == null) "matroska index: Tracks element unreadable"
                else "matroska index: no embedded subtitle track in this file"
            )
            return null
        }

        val cuesBytes = cuesBytesFromHead
            ?: cuesPosition?.let { fetchElement(source, it, ID_CUES, maxCuesBytes) }
            ?: run {
                onDiagnostic(
                    if (cuesPosition == null) "matroska index: file carries no Cues index"
                    else "matroska index: Cues element unreadable or larger than the budget"
                )
                return null
            }

        val estimatedStarts = HashSet<Long>()
        val byTrack = parseCueTimes(
            cuesBytes, timestampScaleNs, tracks.map { it.trackNumber }.toSet(), estimatedStarts,
        )
        val indexed = tracks.mapNotNull { track ->
            val cues = byTrack[track.trackNumber].orEmpty()
            if (cues.isEmpty()) {
                null
            } else {
                track.copy(
                    cues = cues,
                    estimatedEndStartsMs = cues
                        .filter { it.endMs - it.startMs == NOMINAL_CUE_DURATION_MS && it.startMs in estimatedStarts }
                        .mapTo(HashSet()) { it.startMs },
                )
            }
        }.filter { it.cues.size >= MIN_INDEXED_CUES && it.spanMs >= MIN_INDEXED_SPAN_MS }

        if (indexed.isEmpty()) {
            // Very common: the muxer indexed only the video track. Not a failure, just a miss.
            val sparse = tracks.map { it.copy(cues = byTrack[it.trackNumber].orEmpty()) }
            onDiagnostic(
                "matroska index: subtitle tracks=${tracks.size} but none carry usable cue points (" +
                    sparse.joinToString { "track=${it.trackNumber} lang=${it.language ?: "-"} cues=${it.cues.size}" } + ")"
            )
            // Cue points exist for the file (some track is indexed) but these subtitle tracks have
            // next to none: that is evidence about the tracks, so hand it back. With no cue point
            // for any subtitle track, the muxer simply indexed video only — a plain miss.
            if (sparse.none { it.cues.isNotEmpty() }) return null
            return IndexedTimeline(
                tracks = emptyList(),
                segmentDataStart = segmentDataStart,
                timestampScaleNs = timestampScaleNs,
                sparseTracks = sparse,
            )
        }
        onDiagnostic(
            "matroska index: " + indexed.joinToString(" | ") {
                "track=${it.trackNumber} lang=${it.language ?: "-"} cues=${it.cues.size} span=${it.spanMs / 1000}s"
            }
        )
        return IndexedTimeline(indexed, segmentDataStart = segmentDataStart, timestampScaleNs = timestampScaleNs)
    }

    /**
     * Whether an indexed timeline and cues actually observed in the player describe the **same
     * track**.
     *
     * The index is identified by track number and language, which is not proof: a file can carry
     * several tracks in one language (SDH vs dialogue, or two different cuts of the same dub), and
     * scoring candidates against the wrong one would mis-time every verdict while looking
     * perfectly confident. Both sides here are the *authored* start times of the same file, so a
     * true match lines up almost exactly — hence the tight [toleranceMs]; a mismatch scatters.
     *
     * [observed] must be the unsampled cues seen by the player, and [indexCues] the FULL indexed
     * list (checking against a downsampled subset would drop the very cues being compared).
     */
    fun agreesWithObserved(
        indexCues: List<SubtitleSyncMatcher.TimedCue>,
        observed: List<SubtitleSyncMatcher.TimedCue>,
        toleranceMs: Long = 250L,
        minFraction: Double = 0.5,
    ): Boolean {
        if (indexCues.isEmpty() || observed.isEmpty()) return false
        val starts = indexCues.map { it.startMs }
        var hits = 0
        for (cue in observed) {
            var low = 0
            var high = starts.size - 1
            var best = Long.MAX_VALUE
            while (low <= high) {
                val mid = (low + high) ushr 1
                val delta = starts[mid] - cue.startMs
                if (kotlin.math.abs(delta) < best) best = kotlin.math.abs(delta)
                if (delta < 0) low = mid + 1 else high = mid - 1
            }
            if (best <= toleranceMs) hits++
        }
        return hits >= kotlin.math.ceil(observed.size * minFraction).toInt()
    }

    /**
     * The indexed track to use as a reference for [preferredLanguage], or null when this file
     * indexes nothing in that language.
     *
     * **Never falls back to another language.** Matroska language tags are IETF, so an English
     * track is commonly `en-US` while the player reports `en`; comparing them whole fails, and an
     * earlier "first non-forced track" fallback then selected a *Russian* timeline on
     * Reacher S03E02 (Sept 2026, tracks: ru / en-US / en-US). Only [agreesWithObserved] caught it,
     * and that check needs cues the player has already rendered — on the path where the buffer is
     * empty there is nothing to catch it, and every candidate would be scored against the wrong
     * language's timing. No index at all is strictly better than the wrong one.
     *
     * Matching is on the primary subtag, and on the 2- vs 3-letter forms of it (`en`/`eng`,
     * `he`/`heb`/`iw`), which is as much as the container's tags justify.
     */
    /**
     * Reference rule, by TIMING SHAPE rather than by metadata: a track
     * is a dialogue timeline when it has enough cues, spans enough of the film and is dense enough
     * (≥ [minCuesPerMinute]) — whatever its language, and even when it is flagged forced. Returned
     * best first: tracks that are both dense and not forced, then (only if there are none) dense
     * forced ones, ranked like the fork (more cues, then denser, SDH/hearing-impaired last).
     *
     * Why the forced flag is not trusted: web releases routinely flag their only, FULL English
     * subtitle as forced so players show it by default. Peaky Blinders S01E04 (MoviezAddiction
     * "ESub", Sept 2026) had exactly one embedded text track, flagged that way; excluding it left
     * the scan with no reference at all, while the fork used it and matched an OpenSubtitles file.
     * A genuine forced track (signs and foreign lines) fails the density bar on its own.
     *
     * Image codecs are excluded: a PGS/VobSub index lists "clear screen" events as cues too, which
     * doubles the count and would pass the density bar on half of it being noise.
     */
    fun pickReferenceTracks(
        tracks: List<IndexedTrack>,
        minCuesPerMinute: Double,
        minCues: Int = MIN_INDEXED_CUES,
        minSpanMs: Long = 45_000L,
    ): List<IndexedTrack> {
        fun density(track: IndexedTrack) = if (track.spanMs <= 0L) 0.0 else track.cues.size * 60_000.0 / track.spanMs
        fun looksForced(track: IndexedTrack): Boolean {
            val name = track.name.orEmpty().lowercase()
            return track.isForced || name.contains("forced") || name.contains("foreign only") ||
                name.contains("signs") || name.contains("songs only")
        }
        fun looksSdh(track: IndexedTrack): Boolean {
            val name = track.name.orEmpty().lowercase()
            return track.isHearingImpaired || name.contains("sdh") || name.contains("hearing impaired")
        }
        val dense = tracks.filter {
            !isImageTrack(it) && it.cues.size >= minCues && it.spanMs >= minSpanMs &&
                density(it) >= minCuesPerMinute &&
                !it.name.orEmpty().contains("commentary", ignoreCase = true)
        }
        val ranked = dense.sortedWith(
            compareByDescending<IndexedTrack> { it.cues.size * 2.0 + density(it).coerceAtMost(20.0) * 1.5 - if (looksSdh(it)) 5.0 else 0.0 }
                .thenBy { looksSdh(it) }
                .thenBy { it.trackNumber }
        )
        return ranked.filter { !looksForced(it) }.ifEmpty { ranked }
    }

    /** One-line description of a track for diagnostics: every field that decides whether it is a reference. */
    fun describe(track: IndexedTrack): String {
        val density = if (track.spanMs <= 0L) 0.0 else track.cues.size * 60_000.0 / track.spanMs
        val flags = listOfNotNull(
            "forced".takeIf { track.isForced },
            "default".takeIf { track.isDefault },
            "hi".takeIf { track.isHearingImpaired },
        ).joinToString(",").ifEmpty { "-" }
        return "#${track.trackNumber} lang=${track.language ?: "-"} codec=${track.codecId ?: "-"}" +
            " " +
            "name=\"${track.name.orEmpty()}\" flags=$flags cues=${track.cues.size} " +
            "span=${track.spanMs / 1000}s density=${"%.1f".format(java.util.Locale.US, density)}/min " +
            "estimatedEnds=${track.estimatedEndStartsMs.size}"
    }

    fun pickTrackForLanguage(tracks: List<IndexedTrack>, preferredLanguage: String): IndexedTrack? {
        val wanted = languageKeys(preferredLanguage)
        if (wanted.isEmpty()) return null
        return tracks.firstOrNull { track ->
            !track.isForced && !isImageTrack(track) && languageKeys(track.language.orEmpty()).any { it in wanted }
        }
    }

    /**
     * An image subtitle codec (PGS, VobSub, DVB). Its index lists every "show" AND "clear" event
     * with placeholder durations — noise at double the dialogue density, never a timing reference.
     * Applied to every reference path: the preferred-language track ([pickTrackForLanguage]), the
     * alternates ([alternateReferenceTracks]) and the timing-shape fallback ([pickReferenceTracks]).
     */
    fun isImageTrack(track: IndexedTrack): Boolean {
        val codec = track.codecId.orEmpty().uppercase()
        return codec.startsWith("S_HDMV") || codec.startsWith("S_VOBSUB") || codec.startsWith("S_DVBSUB")
    }

    /**
     * The file's other dialogue tracks to score against when the preferred one may be mistimed.
     * Text tracks
     * only; forced ones only when [allowForced] (the timing-shape path, where the forced flag is not
     * trusted); dense enough to be dialogue; the fullest first.
     */
    fun alternateReferenceTracks(
        tracks: List<IndexedTrack>,
        primaryTrackNumber: Long,
        allowForced: Boolean,
        minCues: Int,
        minCuesPerMinute: Double,
        max: Int,
    ): List<IndexedTrack> = tracks
        .filter { it.trackNumber != primaryTrackNumber && (allowForced || !it.isForced) && !isImageTrack(it) }
        .filter { it.cues.size >= minCues && it.spanMs > 0L }
        .filter { it.cues.size * 60_000.0 / it.spanMs >= minCuesPerMinute }
        .sortedByDescending { it.cues.size }
        .take(max)

    /** Primary subtag plus its ISO-639-1/2 aliases, lowercased. */
    private fun languageKeys(language: String): Set<String> {
        val primary = language.trim().lowercase()
            .substringBefore('-')
            .substringBefore('_')
            .takeIf { it.isNotEmpty() }
            ?: return emptySet()
        val aliases = LANGUAGE_ALIASES.entries
            .firstOrNull { (key, values) -> primary == key || primary in values }
        return aliases?.let { it.value + it.key } ?: setOf(primary)
    }

    /** Only the equivalences the container tags actually produce — not a general language map. */
    private val LANGUAGE_ALIASES: Map<String, Set<String>> = mapOf(
        "en" to setOf("eng"),
        "he" to setOf("heb", "iw"),
        "ru" to setOf("rus"),
        "ar" to setOf("ara"),
        "es" to setOf("spa"),
        "fr" to setOf("fre", "fra"),
        "de" to setOf("ger", "deu"),
        "pt" to setOf("por"),
        "it" to setOf("ita"),
        "nl" to setOf("dut", "nld"),
        "pl" to setOf("pol"),
        "tr" to setOf("tur"),
        "cs" to setOf("cze", "ces"),
        "el" to setOf("gre", "ell"),
        "zh" to setOf("chi", "zho"),
        "ja" to setOf("jpn"),
        "ko" to setOf("kor"),
    )

    // ── Element parsing ─────────────────────────────────────────────────────────

    private const val UNKNOWN_SIZE = -1L

    /**
     * An element's id plus where its header ends, in absolute file coordinates.
     * [offset] is the id's own position; [dataOffset] the first content byte.
     */
    private data class ElementHeader(
        val id: Long,
        val offset: Long,
        val dataOffset: Long,
        val size: Long,
    ) {
        val endOffset: Long get() = if (size == UNKNOWN_SIZE) dataOffset else dataOffset + size
    }

    /**
     * Reads an element header at [index] within [buffer], whose first byte is at absolute [base].
     *
     * EBML ids keep their length-marker bits (so 0xAE stays 0xAE), while sizes strip theirs — the
     * two VINT forms differ, and conflating them mis-parses every subsequent element.
     */
    private fun readHeader(buffer: ByteArray, index: Int, base: Long): ElementHeader? {
        if (index < 0 || index >= buffer.size) return null
        val first = buffer[index].toInt() and 0xFF
        if (first == 0) return null
        val idLength = when {
            first and 0x80 != 0 -> 1
            first and 0x40 != 0 -> 2
            first and 0x20 != 0 -> 3
            first and 0x10 != 0 -> 4
            else -> return null
        }
        if (index + idLength > buffer.size) return null
        var id = 0L
        for (i in 0 until idLength) id = (id shl 8) or (buffer[index + i].toLong() and 0xFF)

        val sizeIndex = index + idLength
        if (sizeIndex >= buffer.size) return null
        val sizeFirst = buffer[sizeIndex].toInt() and 0xFF
        if (sizeFirst == 0) return null
        var sizeLength = 1
        var mask = 0x80
        while (sizeLength <= 8 && (sizeFirst and mask) == 0) {
            mask = mask shr 1
            sizeLength++
        }
        if (sizeLength > 8 || sizeIndex + sizeLength > buffer.size) return null
        // Strip the length marker: the value lives in the bits below it.
        var size = (sizeFirst and (mask - 1)).toLong()
        // An all-ones size means "unknown" (a streaming Segment/Cluster), not a huge element.
        var allOnes = size == (mask - 1).toLong()
        for (i in 1 until sizeLength) {
            val b = buffer[sizeIndex + i].toInt() and 0xFF
            size = (size shl 8) or b.toLong()
            if (b != 0xFF) allOnes = false
        }
        val dataOffset = base + sizeIndex + sizeLength
        return ElementHeader(
            id = id,
            offset = base + index,
            dataOffset = dataOffset,
            size = if (allOnes) UNKNOWN_SIZE else size,
        )
    }

    /** Finds the next element with [id] at this nesting level, starting at [from]. */
    private fun scanForElement(buffer: ByteArray, from: Int, id: Long): ElementHeader? {
        var cursor = from
        while (cursor in 0 until buffer.size) {
            val header = readHeader(buffer, cursor, 0L) ?: return null
            if (header.id == id) return header
            if (header.size == UNKNOWN_SIZE) return null
            val next = header.dataOffset + header.size
            if (next <= cursor || next > Int.MAX_VALUE) return null
            cursor = next.toInt()
        }
        return null
    }

    /** Walks the direct children of an element body, calling [onChild] with each id and its bytes. */
    private inline fun forEachChild(body: ByteArray, onChild: (id: Long, bytes: ByteArray) -> Unit) {
        var cursor = 0
        while (cursor in 0 until body.size) {
            val header = readHeader(body, cursor, 0L) ?: return
            if (header.size == UNKNOWN_SIZE) return
            val start = header.dataOffset.toInt()
            val end = (header.dataOffset + header.size).toInt()
            if (start < 0 || end > body.size || end < start) return
            onChild(header.id, body.copyOfRange(start, end))
            if (end <= cursor) return
            cursor = end
        }
    }

    /** SeekHead maps element ids to positions *relative to the Segment's data start*. */
    private fun parseSeekHead(body: ByteArray, segmentDataStart: Long): Map<Long, Long> {
        val positions = HashMap<Long, Long>()
        forEachChild(body) { id, seekBytes ->
            if (id != ID_SEEK) return@forEachChild
            var seekId: Long? = null
            var seekPosition: Long? = null
            forEachChild(seekBytes) { childId, value ->
                when (childId) {
                    ID_SEEK_ID -> seekId = value.fold(0L) { acc, b -> (acc shl 8) or (b.toLong() and 0xFF) }
                    ID_SEEK_POSITION -> seekPosition = readUInt(value)
                }
            }
            val target = seekId
            val position = seekPosition
            // putIfAbsent is API 24; minSdk here is 23. First entry wins, as the spec intends.
            if (target != null && position != null && !positions.containsKey(target)) {
                positions[target] = segmentDataStart + position
            }
        }
        return positions
    }

    private fun parseTimestampScale(infoBody: ByteArray): Long {
        var scale = DEFAULT_TIMESTAMP_SCALE_NS
        forEachChild(infoBody) { id, value ->
            if (id == ID_TIMESTAMP_SCALE) readUInt(value).takeIf { it > 0L }?.let { scale = it }
        }
        return scale
    }

    private fun parseSubtitleTracks(tracksBody: ByteArray): List<IndexedTrack> {
        val tracks = ArrayList<IndexedTrack>()
        forEachChild(tracksBody) { id, entry ->
            if (id != ID_TRACK_ENTRY) return@forEachChild
            var number: Long? = null
            var type: Long? = null
            var language: String? = null
            var languageIetf: String? = null
            var codecId: String? = null
            var name: String? = null
            var isDefault = true // FlagDefault defaults to 1 per spec
            var isForced = false
            var isHearingImpaired = false
            forEachChild(entry) { childId, value ->
                when (childId) {
                    ID_TRACK_NUMBER -> number = readUInt(value)
                    ID_TRACK_TYPE -> type = readUInt(value)
                    ID_LANGUAGE -> language = readAscii(value)
                    ID_LANGUAGE_IETF -> languageIetf = readAscii(value)
                    ID_CODEC_ID -> codecId = readAscii(value)
                    ID_NAME -> name = String(value, Charsets.UTF_8).trim().takeIf { it.isNotEmpty() }
                    ID_FLAG_DEFAULT -> isDefault = readUInt(value) != 0L
                    ID_FLAG_FORCED -> isForced = readUInt(value) != 0L
                    ID_FLAG_HEARING_IMPAIRED -> isHearingImpaired = readUInt(value) != 0L
                }
            }
            val trackNumber = number
            if (trackNumber != null && type == TRACK_TYPE_SUBTITLE) {
                tracks += IndexedTrack(
                    trackNumber = trackNumber,
                    // IETF wins when present: it carries region/script the legacy field cannot.
                    // Absent entirely, Matroska defines the default as "eng" — returning null
                    // instead would make an untagged English track unmatchable.
                    language = languageIetf ?: language ?: "eng",
                    codecId = codecId,
                    name = name,
                    isDefault = isDefault,
                    isForced = isForced,
                    isHearingImpaired = isHearingImpaired,
                    cues = emptyList(),
                )
            }
        }
        return tracks
    }

    /**
     * Cue times per subtitle track, converted from segment ticks to milliseconds.
     *
     * A CuePoint carries one CueTime and one CueTrackPositions per indexed track, so the same
     * timestamp can belong to several tracks; only [wantedTracks] are kept.
     */
    private fun parseCueTimes(
        cuesBody: ByteArray,
        timestampScaleNs: Long,
        wantedTracks: Set<Long>,
        // Receives the start of every cue whose duration was not indexed (see estimatedEndStartsMs).
        estimatedStarts: MutableSet<Long>,
    ): Map<Long, List<SubtitleSyncMatcher.TimedCue>> {
        val result = HashMap<Long, MutableList<SubtitleSyncMatcher.TimedCue>>()
        val tickToMs = timestampScaleNs.toDouble() / 1_000_000.0
        forEachChild(cuesBody) { id, point ->
            if (id != ID_CUE_POINT) return@forEachChild
            var timeTicks: Long? = null
            val positions = ArrayList<Pair<Long, Long?>>() // track number to optional duration ticks
            forEachChild(point) { childId, value ->
                when (childId) {
                    ID_CUE_TIME -> timeTicks = readUInt(value)
                    ID_CUE_TRACK_POSITIONS -> {
                        var track: Long? = null
                        var durationTicks: Long? = null
                        forEachChild(value) { positionId, positionValue ->
                            when (positionId) {
                                ID_CUE_TRACK -> track = readUInt(positionValue)
                                ID_CUE_DURATION -> durationTicks = readUInt(positionValue)
                            }
                        }
                        track?.let { positions += it to durationTicks }
                    }
                }
            }
            val ticks = timeTicks ?: return@forEachChild
            val startMs = (ticks * tickToMs).toLong()
            positions.forEach { (track, durationTicks) ->
                if (track !in wantedTracks) return@forEach
                val durationMs = durationTicks
                    ?.let { (it * tickToMs).toLong() }
                    ?.takeIf { it > 0L }
                    ?: NOMINAL_CUE_DURATION_MS.also { estimatedStarts += startMs }
                result.getOrPut(track) { ArrayList() }
                    .add(SubtitleSyncMatcher.TimedCue(startMs, startMs + durationMs, ""))
            }
        }
        return result.mapValues { (_, cues) -> cues.sortedBy { it.startMs }.distinctBy { it.startMs } }
    }

    private fun readUInt(bytes: ByteArray): Long =
        bytes.fold(0L) { acc, b -> (acc shl 8) or (b.toLong() and 0xFF) }

    private fun readAscii(bytes: ByteArray): String? =
        String(bytes, Charsets.US_ASCII).trimEnd(' ').trim().takeIf { it.isNotEmpty() }

    /**
     * Fetches the element at [offset], verifying its id before pulling the body. The header is read
     * first so only the declared size is transferred — an element larger than [maxBytes] is skipped
     * rather than truncated, since a partial Cues list would silently describe only the start of
     * the film.
     */
    private suspend fun fetchElement(
        source: ByteRangeSource,
        offset: Long,
        expectedId: Long,
        maxBytes: Int,
    ): ByteArray? {
        // One read, not two. The header is at most 12 bytes and the elements this fetches are
        // usually far smaller than the first chunk, so reading a chunk and slicing the body out
        // of it resolves Tracks/Cues in a single request; only an element larger than the chunk
        // costs a second. (Reading a fixed 16-byte header first and then the body always cost
        // two requests per element — half the traffic of a whole index read, for nothing.)
        val firstChunk = source.read(offset, FIRST_CHUNK_BYTES.coerceAtMost(maxBytes)) ?: return null
        val header = readHeader(firstChunk, 0, offset) ?: return null
        if (header.id != expectedId || header.size == UNKNOWN_SIZE) return null
        if (header.size <= 0L || header.size > maxBytes) return null

        val headerLength = (header.dataOffset - offset).toInt()
        val availableBody = firstChunk.size - headerLength
        if (availableBody >= header.size) {
            return firstChunk.copyOfRange(headerLength, headerLength + header.size.toInt())
        }
        val body = source.read(header.dataOffset, header.size.toInt()) ?: return null
        return body.takeIf { it.size.toLong() == header.size }
    }
}
