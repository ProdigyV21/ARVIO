package com.arflix.tv.ui.screens.player

import androidx.media3.common.text.Cue
import androidx.media3.common.text.CueGroup

/**
 * Reads the cues ExoPlayer's text renderer has already decoded but not yet displayed, by reflecting
 * into its internal cue buffer.
 *
 * Used by [AiSubtitleRenderersFactory]'s offset renderer: for AI pre-translation lookahead, and for
 * the "find best match" timing reference — when AI translation is on screen the track it translates
 * from IS the reference, so these cues are it, at no extra cost.
 *
 * Buffered timestamps are the *authored* cue times, which is what makes them the trustworthy sync
 * reference — cues observed rendering in real time carry a systematic render-lag skew.
 *
 * Media3 picks a cue resolver per the track's cue-replacement behavior, so both the modern
 * `cuesResolver` path and the legacy `subtitle`/`nextSubtitle` fields are probed, with a
 * scan-every-field fallback for unknown implementations (media3 upgrades).
 */
internal object BufferedCueReader {

    private val BRACKET_REGEX = Regex("""\[.*?\]""")
    private val MUSIC_REGEX = Regex("[♪♫]+")

    /** Assumed on-screen time for a cue whose wrapper carries no usable duration. */
    private const val NOMINAL_CUE_DURATION_US = 2_000_000L

    private val FIELD_CACHE = java.util.concurrent.ConcurrentHashMap<String, java.lang.reflect.Field>()
    private val MISSING_FIELDS: MutableSet<String> =
        java.util.Collections.newSetFromMap(java.util.concurrent.ConcurrentHashMap<String, Boolean>())

    /** Buffered cue intervals (startMs, endMs) in 0-based media time, for text-carrying cues. */
    fun intervals(renderer: Any, maxCount: Int): List<Pair<Long, Long>> =
        timedCues(renderer, maxCount).map { it.startMs to it.endMs }

    /**
     * Buffered cues with their text, in 0-based media time. Same walk as [intervals] — the text is
     * what lets AI line-matching pair a reference line with its translation, which pure timing
     * cannot do.
     *
     * [alreadyHave] is asked about a cue's start time *before* its text is built. Callers poll this
     * several times a second while the renderer's buffer barely changes, so almost every cue seen is
     * one they already hold; without the early rejection each of them cost a fresh joined String on
     * every poll.
     */
    fun timedCues(
        renderer: Any,
        maxCount: Int,
        alreadyHave: (Long) -> Boolean = { false },
    ): List<SubtitleSyncMatcher.TimedCue> {
        val intervals = ArrayList<SubtitleSyncMatcher.TimedCue>()
        val offsetMs = readStreamOffsetUs(renderer) / 1000L
        try {
            val resolverField = findField(renderer.javaClass, "cuesResolver")
            val resolver = resolverField?.get(renderer)
            if (resolver != null) {
                // Media3 has multiple resolver impls chosen per track cue-replacement behavior
                // (MergingCuesResolver, ReplacingCuesResolver) — probe the known field names.
                for (candidate in listOf(
                    "cuesWithTimingList", "cuesWithTimings",
                    "cueGroupsByStartTime", "cueGroups", "cueGroupList", "groups"
                )) {
                    val f = findField(resolver.javaClass, candidate) ?: continue
                    val v = f.get(resolver) ?: continue
                    val items: Collection<*> = when (v) {
                        is Map<*, *> -> v.values
                        is Collection<*> -> v
                        else -> continue
                    }
                    for (item in items) {
                        val cue = item?.let { timedCueFromWrapper(it, offsetMs, alreadyHave) } ?: continue
                        intervals.add(cue)
                    }
                    if (intervals.isNotEmpty()) break
                }
                if (intervals.isEmpty()) {
                    // Unknown impl/field name (new media3 version?) — scan every field for a
                    // cue-carrying collection, like the text extractor already does.
                    var cls: Class<*>? = resolver.javaClass
                    outer@ while (cls != null && cls != Any::class.java) {
                        for (f in cls.declaredFields) {
                            try {
                                f.isAccessible = true
                                val v = f.get(resolver) ?: continue
                                val items: Collection<*> = when (v) {
                                    is Map<*, *> -> v.values
                                    is Collection<*> -> v
                                    else -> continue
                                }
                                for (item in items) {
                                    val cue = item?.let { timedCueFromWrapper(it, offsetMs, alreadyHave) } ?: continue
                                    intervals.add(cue)
                                }
                                if (intervals.isNotEmpty()) break@outer
                            } catch (_: Exception) {
                            }
                        }
                        cls = cls.superclass
                    }
                }
            }
        } catch (_: Exception) {
        }
        return intervals.distinct().sortedBy { it.startMs }.take(maxCount)
    }

    /** Text of every cue currently in the renderer's buffer. */
    fun allCueTexts(renderer: Any, removeHI: Boolean): List<String> {
        val texts = mutableSetOf<String>()

        // Modern Media3: TextRenderer holds a MergingCuesResolver (field: cuesResolver)
        try {
            val resolverField = findField(renderer.javaClass, "cuesResolver")
            val resolver = resolverField?.get(renderer)
            if (resolver != null) {
                var extracted = false
                for (candidate in listOf(
                    "cuesWithTimingList", "cueGroupsByStartTime", "cueGroups", "cueGroupList", "groups"
                )) {
                    val f = findField(resolver.javaClass, candidate) ?: continue
                    val v = f.get(resolver) ?: continue
                    val count = extractFromCollectionOrMap(v, texts, removeHI)
                    if (count > 0) {
                        extracted = true
                        break
                    }
                }
                if (!extracted) {
                    // Fall back to scanning all fields on the resolver
                    var cls: Class<*>? = resolver.javaClass
                    while (cls != null && cls != Any::class.java) {
                        for (f in cls.declaredFields) {
                            try {
                                f.isAccessible = true
                                val v = f.get(resolver) ?: continue
                                extractFromCollectionOrMap(v, texts, removeHI)
                            } catch (_: Exception) {}
                        }
                        cls = cls.superclass
                    }
                }
            }
        } catch (_: Exception) {
        }

        // Legacy Media3: subtitle / nextSubtitle fields carrying a Subtitle with event times.
        fun extractFromSubtitleField(fieldName: String) {
            try {
                val field = findField(renderer.javaClass, fieldName) ?: return
                val subtitle = field.get(renderer) ?: return
                val getEventTimeCount = subtitle.javaClass.getMethod("getEventTimeCount")
                val getEventTime = subtitle.javaClass.getMethod("getEventTime", Int::class.java)
                val getCues = subtitle.javaClass.getMethod("getCues", Long::class.java)
                val count = getEventTimeCount.invoke(subtitle) as Int
                for (i in 0 until count) {
                    val timeUs = getEventTime.invoke(subtitle, i) as Long
                    @Suppress("UNCHECKED_CAST")
                    val cues = getCues.invoke(subtitle, timeUs) as? List<Cue> ?: continue
                    val joined = joinCues(cues, removeHI)
                    if (joined.isNotBlank()) texts.add(joined)
                }
            } catch (_: Exception) {
            }
        }
        extractFromSubtitleField("subtitle")
        extractFromSubtitleField("nextSubtitle")
        return texts.toList()
    }

    /** The renderer's stream offset (µs) — the base added to buffer sample timestamps. */
    private fun readStreamOffsetUs(renderer: Any): Long {
        for (name in listOf("streamOffsetUs", "outputStreamOffsetUs")) {
            val v = runCatching { findField(renderer.javaClass, name)?.getLong(renderer) }.getOrNull()
            if (v != null && v > 0L) return v
        }
        return 0L
    }

    /**
     * Timing is read FIRST, and the caller gets to reject the cue on its start time before any text
     * is built. That ordering is the whole point: joining a cue's text allocates a String, the probe
     * re-reads the same renderer buffer several times a second, and on any given poll nearly every
     * cue in it was already collected on the previous one. Building their text again only to drop it
     * on a set insert produced garbage at a rate that was visible as jank next to a 4K decode.
     */
    private fun timedCueFromWrapper(
        obj: Any,
        offsetMs: Long,
        alreadyHave: (Long) -> Boolean,
    ): SubtitleSyncMatcher.TimedCue? {
        val startUs: Long
        val endUs: Long
        if (obj is CueGroup) {
            startUs = obj.presentationTimeUs
            endUs = startUs + NOMINAL_CUE_DURATION_US // CueGroup carries no duration
        } else {
            val start = runCatching { findField(obj.javaClass, "startTimeUs")?.getLong(obj) }.getOrNull() ?: return null
            // REPLACE-behavior tracks (ReplacingCuesResolver, e.g. MKV SubRip) carry
            // durationUs = C.TIME_UNSET (large negative) — each cue lasts until replaced. The
            // negative value made endUs < startUs and every item got rejected, silently killing
            // the buffered fast path for that whole content category. Fall back to a nominal
            // duration: the start timestamp carries the alignment signal.
            val dur = runCatching { findField(obj.javaClass, "durationUs")?.getLong(obj) }.getOrNull()
                ?.takeIf { it > 0L } ?: NOMINAL_CUE_DURATION_US
            startUs = start
            endUs = start + dur
        }
        if (endUs <= startUs) return null
        // Cue buffer timestamps are on ExoPlayer's internal stream timeline, which adds a large
        // base offset; subtract the renderer's stream offset to get 0-based media time.
        val startMs = startUs / 1000L - offsetMs
        if (startMs < 0L) return null
        if (alreadyHave(startMs)) return null

        val cues: List<Cue>? = when (obj) {
            is CueGroup -> obj.cues
            is List<*> -> obj.filterIsInstance<Cue>()
            else -> (findField(obj.javaClass, "cues")?.get(obj) as? List<*>)?.filterIsInstance<Cue>()
        }
        if (cues.isNullOrEmpty()) return null
        val text = cues.mapNotNull { it.text?.toString()?.trim() }.filter { it.isNotBlank() }.joinToString("\n")
        if (text.isBlank()) return null
        return SubtitleSyncMatcher.TimedCue(startMs, endUs / 1000L - offsetMs, text)
    }

    private fun extractFromCollectionOrMap(v: Any, texts: MutableSet<String>, removeHI: Boolean): Int {
        var count = 0
        when (v) {
            is Map<*, *> -> v.values.forEach { if (extractCueGroupTexts(it, texts, removeHI)) count++ }
            is Collection<*> -> v.forEach { if (extractCueGroupTexts(it, texts, removeHI)) count++ }
        }
        return count
    }

    private fun extractCueGroupTexts(obj: Any?, texts: MutableSet<String>, removeHI: Boolean): Boolean {
        if (obj == null) return false
        if (obj is CueGroup) {
            val joined = joinCues(obj.cues, removeHI)
            if (joined.isNotBlank()) texts.add(joined)
            return true
        }
        if (obj is List<*>) {
            val cues = obj.filterIsInstance<Cue>()
            val joined = joinCues(cues, removeHI)
            if (joined.isNotBlank()) texts.add(joined)
            return obj.isNotEmpty()
        }
        // CuesWithTiming or similar wrapper — look for a 'cues' field
        try {
            val cuesField = findField(obj.javaClass, "cues")
            val cues = cuesField?.get(obj)
            if (cues is List<*>) {
                val joined = joinCues(cues.filterIsInstance<Cue>(), removeHI)
                if (joined.isNotBlank()) texts.add(joined)
                return cues.isNotEmpty()
            }
        } catch (_: Exception) {}
        return false
    }

    private fun joinCues(cues: List<Cue>, removeHI: Boolean): String =
        cues.mapNotNull { it.text?.toString()?.trim() }
            .filter { it.isNotBlank() }
            .joinToString("\n")
            .let { if (removeHI) stripHI(it) else it }

    private fun stripHI(text: String): String =
        text.replace(BRACKET_REGEX, "")
            .replace(MUSIC_REGEX, "")
            .trim()

    /**
     * Resolved reflectively, then cached — misses included.
     *
     * Every call previously walked the class hierarchy calling `getDeclaredField`, which allocates a
     * fresh Field on a hit and *throws* on a miss. The lookups run per cue per poll, and the
     * resolver field-name probe above misses several names before it hits, so a probe was
     * constructing NoSuchFieldExceptions (each capturing a stack trace) thousands of times a minute
     * on the main thread. The set of classes here is tiny and fixed for the life of the process.
     */
    private fun findField(startClass: Class<*>, name: String): java.lang.reflect.Field? {
        val key = startClass.name + '#' + name
        FIELD_CACHE[key]?.let { return it }
        if (MISSING_FIELDS.contains(key)) return null
        var cls: Class<*>? = startClass
        while (cls != null && cls != Any::class.java) {
            try {
                val f = cls.getDeclaredField(name)
                f.isAccessible = true
                FIELD_CACHE[key] = f
                return f
            } catch (_: NoSuchFieldException) {}
            cls = cls.superclass
        }
        MISSING_FIELDS.add(key)
        return null
    }
}
