package com.arflix.tv.ui.screens.player

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.zip.GZIPInputStream
import java.util.concurrent.TimeUnit

/**
 * "Find best match": scores candidate subtitle tracks by how well their on-screen cue at a given
 * moment matches what the AI hearing transcribed being spoken at that same moment. A well-synced
 * subtitle shows the right words at the right time; an out-of-sync one does not. The comparison
 * is language-agnostic word overlap (normalization additionally strips Hebrew niqqud and bidi
 * marks, which is a no-op for other languages); callers pass candidates in the user's preferred
 * subtitle language.
 */
object SubtitleSyncMatcher {

    private const val TAG = "SubMatch"

    data class TimedCue(val startMs: Long, val endMs: Long, val text: String)

    /** One collected AI-hearing sample: the spoken line and the media position when it arrived. */
    data class SpokenSample(val text: String, val positionMs: Long)

    private val client = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(15, TimeUnit.SECONDS)
        .build()

    /** Downloads a subtitle and returns its decoded (UTF-8, gunzipped) text, or null on failure. */
    suspend fun loadRaw(url: String): String? = withContext(Dispatchers.IO) {
        runCatching {
            val request = Request.Builder().url(url).build()
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) return@use null
                val body = response.body ?: return@use null
                val raw = body.bytes()
                if (looksGzipped(url, raw)) {
                    GZIPInputStream(raw.inputStream()).bufferedReader(Charsets.UTF_8).readText()
                } else {
                    String(raw, Charsets.UTF_8)
                }
            }
        }.onFailure { error ->
            Log.w(TAG, "loadRaw failed url=$url err=${error.message}")
        }.getOrNull()
    }

    suspend fun loadCues(url: String): List<TimedCue> = loadRaw(url)?.let { parseCues(it) } ?: emptyList()

    /**
     * Score a candidate's cues against the collected spoken samples. For each sample we look at
     * the audio's media time (arrival position minus the AI latency), find cues within a tolerance
     * window, and count a hit when the best word-overlap clears [minSimilarity]. Returns the hit
     * ratio in 0..1.
     */
    fun score(
        cues: List<TimedCue>,
        samples: List<SpokenSample>,
        latencyMs: Long,
        toleranceMs: Long,
        minSimilarity: Double
    ): Double {
        if (cues.isEmpty() || samples.isEmpty()) return 0.0
        var hits = 0
        for (sample in samples) {
            val audioTime = sample.positionMs - latencyMs
            val best = cues.asSequence()
                .filter { it.endMs >= audioTime - toleranceMs && it.startMs <= audioTime + toleranceMs }
                .maxOfOrNull { similarity(sample.text, it.text) } ?: 0.0
            if (best >= minSimilarity) hits++
        }
        return hits.toDouble() / samples.size
    }

    /** Reference windows longer than this are merged back-to-back cues, not a single cue. */
    private const val SINGLE_CUE_MAX_MS = 7_000L

    /**
     * Score a candidate against a synced reference (a built-in subtitle's cue-visible intervals)
     * purely by timing, averaging a per-window overlap fraction.
     *
     * Two regimes per reference window:
     * - Normal windows (a single cue, ≤ [SINGLE_CUE_MAX_MS]): best overlap by any *one* candidate
     *   cue. This is what discriminates offsets — in dialogue-dense scenes a shifted sub still has
     *   *chains* of cues touching any window, but no single cue aligns with it.
     * - Long windows (realtime collection merges back-to-back cues): coverage by the union of the
     *   candidate's cues, since even a perfectly synced sub needs several cues to span them.
     *
     * Language-agnostic (no translation needed).
     */
    /**
     * @param toleranceMs each candidate cue is widened by this on both edges before measuring
     * overlap — absorbs sub-second caption pre-roll / display lag and cue-boundary differences so a
     * genuinely-synced but differently-segmented sub isn't docked. **0 (strict) for the offset
     * search** (keeps its peaks sharp); a small positive value for accept/reject scoring.
     */
    fun scoreByTiming(
        cues: List<TimedCue>,
        referenceIntervals: List<Pair<Long, Long>>,
        toleranceMs: Long = 0L
    ): Double {
        if (cues.isEmpty() || referenceIntervals.isEmpty()) return 0.0
        return scoreSortedShifted(cues.sortedBy { it.startMs }, referenceIntervals, 0L, toleranceMs)
    }

    /**
     * [scoreByTiming]'s core, over cues **pre-sorted by start** and with a uniform [offsetMs] added
     * to every cue inline (no list copy). Adding a constant offset preserves start order, so the
     * sort stays valid — this lets the offset search sweep many offsets cheaply.
     */
    private fun scoreSortedShifted(
        sorted: List<TimedCue>,
        referenceIntervals: List<Pair<Long, Long>>,
        offsetMs: Long,
        toleranceMs: Long = 0L
    ): Double {
        var sum = 0.0
        for ((rs, re) in referenceIntervals) {
            val refDur = (re - rs).coerceAtLeast(1L)
            val fraction = if (refDur <= SINGLE_CUE_MAX_MS) {
                var best = 0.0
                for (c in sorted) {
                    val cs = c.startMs + offsetMs - toleranceMs
                    if (cs >= re) break // sorted by start → nothing later can overlap
                    val ce = c.endMs + offsetMs + toleranceMs
                    if (ce <= rs) continue
                    val ov = (minOf(re, ce) - maxOf(rs, cs)).toDouble() / refDur
                    if (ov > best) best = ov
                }
                best
            } else {
                var covered = 0L
                var cursor = rs
                for (c in sorted) {
                    val cs = c.startMs + offsetMs - toleranceMs
                    if (cs >= re) break
                    val ce = c.endMs + offsetMs + toleranceMs
                    if (ce <= cursor) continue
                    val s = maxOf(cs, cursor)
                    val e = minOf(ce, re)
                    if (e > s) {
                        covered += e - s
                        cursor = e
                    }
                }
                covered.toDouble() / refDur
            }
            sum += fraction.coerceIn(0.0, 1.0)
        }
        return sum / referenceIntervals.size
    }

    // ── Constant-offset rescue ──────────────────────────────────────────────────

    /**
     * A candidate that fails at offset 0 but becomes well-synced once shifted by a single uniform
     * delay. [offsetMs] is added to every cue (positive = the sub must be pushed *later*);
     * [correctedScore] is [scoreByTiming] of the shifted cues against the reference; [baseScore] is
     * the offset-0 score (so the caller can require a real improvement).
     */
    data class OffsetMatch(val offsetMs: Long, val correctedScore: Double, val baseScore: Double)

    fun shiftCues(cues: List<TimedCue>, offsetMs: Long): List<TimedCue> =
        if (offsetMs == 0L) cues
        else cues.map { TimedCue(it.startMs + offsetMs, it.endMs + offsetMs, it.text) }

    /**
     * Detect whether [cues] are a perfectly-cut subtitle carrying a UNIFORM delay against the
     * reference (a common addon defect: right words, globally-shifted timing — the base metric
     * punishes it). Returns the delay that maximizes the timing score + that corrected score, or
     * null when the best offset is negligible (< [minOffsetMs]).
     *
     * We **search the offset directly**, maximizing [scoreByTiming] (the same overlap metric that
     * decides acceptance), NOT cue-start clustering. Start-clustering was tried first and FAILED on
     * real data: the reference is usually a finely-split CC track while the candidate merges
     * dialogue into fewer/longer cues, so cue *starts* never line up 1:1 even at the correct global
     * offset (Dutton Ranch S01E01: a flat +1000ms is visually perfect, yet start-deltas scattered
     * +583/−617/−2208 and the cluster never formed). Overlap is segmentation-agnostic. A coarse
     * sweep over ±[maxOffsetMs] locates the peak; a fine sweep around it pins the exact value.
     * Wrong-show/drift subs never reach a high corrected score at any offset, so the caller's score
     * bar is what rejects them — no separate spread test needed.
     */
    fun estimateOffsetMatch(
        cues: List<TimedCue>,
        referenceIntervals: List<Pair<Long, Long>>,
        minOffsetMs: Long,
        maxOffsetMs: Long
    ): OffsetMatch? {
        if (cues.size < 3 || referenceIntervals.size < 4) return null
        val sorted = cues.sortedBy { it.startMs }
        val baseScore = scoreSortedShifted(sorted, referenceIntervals, 0L)

        var bestOff = 0L
        var bestScore = baseScore
        fun consider(off: Long) {
            val s = scoreSortedShifted(sorted, referenceIntervals, off)
            // Strictly better wins; on a tie prefer the offset CLOSEST to zero — real sync errors
            // are small, and a far offset that merely ties a near one is spurious (the sweep starts
            // at −max, so without this the far offset would win every tie).
            if (s > bestScore + 1e-9) {
                bestScore = s
                bestOff = off
            } else if (s > bestScore - 1e-9 && Math.abs(off) < Math.abs(bestOff)) {
                bestOff = off
            }
        }
        // Coarse sweep (250ms): the score-vs-offset curve is smooth with a peak ~cue-duration wide,
        // so this can't step over it. Then refine ±250ms at 25ms for exact placement.
        var off = -maxOffsetMs
        while (off <= maxOffsetMs) { consider(off); off += 250L }
        val lo = bestOff - 250L
        val hi = bestOff + 250L
        off = lo
        while (off <= hi) { consider(off); off += 25L }

        if (Math.abs(bestOff) < minOffsetMs) return null
        return OffsetMatch(bestOff, bestScore, baseScore)
    }

    /**
     * Add [offsetMs] to every timestamp in raw SRT/WEBVTT text, preserving format (comma vs dot)
     * and any trailing cue settings. Used to bake a detected offset into the served local file so
     * the correction survives independently of the player's delay knob.
     */
    fun shiftTimestamps(raw: String, offsetMs: Long): String = transformTimestamps(raw, offsetMs, 0.0)

    /**
     * [shiftTimestamps] generalized with a linear drift [rate] (AI auto-sync drift calibration):
     * a cue authored at `t` should display at true video time `t' = t + offsetMs + rate·t'`
     * (the measured delay grows linearly with the *display* position), i.e.
     * `t' = (t + offsetMs) / (1 − rate)`. `rate = 0` reduces to the plain constant shift.
     */
    fun transformTimestamps(raw: String, offsetMs: Long, rate: Double): String {
        if (offsetMs == 0L && rate == 0.0) return raw
        val scale = 1.0 / (1.0 - rate)
        return TIME_LINE.replace(raw) { m ->
            val start = parseTimestamp(m.groupValues[1]) ?: return@replace m.value
            val end = parseTimestamp(m.groupValues[2]) ?: return@replace m.value
            val useComma = m.groupValues[1].contains(',')
            val newStart = Math.round((start + offsetMs) * scale)
            val newEnd = Math.round((end + offsetMs) * scale)
            "${formatTimestamp(newStart, useComma)} --> ${formatTimestamp(newEnd, useComma)}"
        }
    }

    private fun formatTimestamp(ms: Long, useComma: Boolean): String {
        val v = ms.coerceAtLeast(0L)
        val h = v / 3_600_000
        val m = (v % 3_600_000) / 60_000
        val s = (v % 60_000) / 1_000
        val millis = v % 1_000
        val sep = if (useComma) ',' else '.'
        return String.format(java.util.Locale.US, "%02d:%02d:%02d%c%03d", h, m, s, sep, millis)
    }

    // ── Similarity ────────────────────────────────────────────────────────────

    /** Overlap coefficient of normalized word sets — tolerant of different wording/length. */
    fun similarity(a: String, b: String): Double {
        val setA = tokens(a)
        val setB = tokens(b)
        if (setA.isEmpty() || setB.isEmpty()) return 0.0
        val intersection = setA.count { it in setB }
        return intersection.toDouble() / minOf(setA.size, setB.size)
    }

    private fun tokens(text: String): Set<String> =
        normalize(text).split(' ').filter { it.length >= 2 }.toSet()

    private fun normalize(text: String): String {
        val sb = StringBuilder(text.length)
        for (ch in text) {
            when {
                // Strip Hebrew niqqud/cantillation and bidi control marks.
                ch in '֑'..'ׇ' -> {}
                ch == '‎' || ch == '‏' || ch == '‪' || ch == '‫' ||
                    ch == '‬' || ch == '⁦' || ch == '⁧' || ch == '⁩' -> {}
                ch.isLetterOrDigit() -> sb.append(ch.lowercaseChar())
                else -> sb.append(' ')
            }
        }
        return sb.toString().replace(SubtitleSyncMatcherRegexes.MULTI_SPACE_REGEX, " ").trim()
    }

    fun cueTextAt(cues: List<TimedCue>, timeMs: Long): String? =
        cues.firstOrNull { timeMs in it.startMs..it.endMs }?.text

    // ── Parsing (SRT + WEBVTT) ──────────────────────────────────────────────────

    private fun looksGzipped(url: String, bytes: ByteArray): Boolean =
        url.substringBefore('?').endsWith(".gz", ignoreCase = true) ||
            (bytes.size > 2 && bytes[0] == 0x1f.toByte() && bytes[1] == 0x8b.toByte())

    private val TIME_LINE = Regex(
        """(\d{1,2}:\d{2}:\d{2}[.,]\d{1,3}|\d{1,2}:\d{2}[.,]\d{1,3})\s*-->\s*(\d{1,2}:\d{2}:\d{2}[.,]\d{1,3}|\d{1,2}:\d{2}[.,]\d{1,3})"""
    )
    private val TAG_STRIP = Regex("<[^>]*>")

    fun parseCues(content: String): List<TimedCue> {
        val normalized = content.replace("\r\n", "\n").replace('\r', '\n')
        val blocks = normalized.split(Regex("\n\\s*\n"))
        val cues = ArrayList<TimedCue>()
        for (block in blocks) {
            val lines = block.split('\n').map { it.trim() }.filter { it.isNotEmpty() }
            if (lines.isEmpty()) continue
            val timeLineIndex = lines.indexOfFirst { TIME_LINE.containsMatchIn(it) }
            if (timeLineIndex < 0) continue
            val match = TIME_LINE.find(lines[timeLineIndex]) ?: continue
            val start = parseTimestamp(match.groupValues[1]) ?: continue
            val end = parseTimestamp(match.groupValues[2]) ?: continue
            val text = lines.drop(timeLineIndex + 1)
                .joinToString(" ")
                .replace(TAG_STRIP, "")
                .trim()
            if (text.isNotEmpty() && end > start) cues.add(TimedCue(start, end, text))
        }
        return cues
    }

    private fun parseTimestamp(value: String): Long? {
        val cleaned = value.replace(',', '.')
        val parts = cleaned.split(':')
        return runCatching {
            when (parts.size) {
                3 -> {
                    val h = parts[0].toLong()
                    val m = parts[1].toLong()
                    val s = parts[2].toDouble()
                    (h * 3600 + m * 60) * 1000 + (s * 1000).toLong()
                }
                2 -> {
                    val m = parts[0].toLong()
                    val s = parts[1].toDouble()
                    (m * 60) * 1000 + (s * 1000).toLong()
                }
                else -> null
            }
        }.getOrNull()
    }
}


private object SubtitleSyncMatcherRegexes {
    val MULTI_SPACE_REGEX = Regex("\\s+")
}
