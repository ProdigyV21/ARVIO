package com.arflix.tv.ui.screens.player.subtitles.bilingual

import androidx.annotation.OptIn
import androidx.media3.common.C
import androidx.media3.common.util.UnstableApi
import androidx.media3.extractor.text.CueDecoder

/** One decoded subtitle sample. [endMs] null = the sample did not state its end. */
data class DecodedCue(val startMs: Long, val endMs: Long?, val text: String)

/**
 * Turns one demuxed subtitle sample into text. Handles both shapes Media3 can hand a TrackOutput:
 *  - `application/x-media3-cues`: the extractor already parsed the subtitle (the default since
 *    Media3 1.4) and serialised CuesWithTiming;
 *  - the raw container sample: Matroska SubRip / SSA / WebVTT blocks (with the timing prefix the
 *    Matroska extractor writes) and MP4 tx3g.
 * Image subtitles (PGS/VobSub/DVB) return no decoder.
 */
abstract class SubtitleSampleDecoder {
    abstract fun decode(timeUs: Long, data: ByteArray, offset: Int, size: Int): DecodedCue?

    companion object {
        const val MIME_MEDIA3_CUES = "application/x-media3-cues"
        const val MIME_SUBRIP = "application/x-subrip"
        const val MIME_SSA = "text/x-ssa"
        const val MIME_VTT = "text/vtt"
        const val MIME_TX3G = "application/x-quicktime-tx3g"

        fun forFormat(sampleMimeType: String?, codecs: String?): SubtitleSampleDecoder? {
            val mime = sampleMimeType?.lowercase() ?: return null
            if (isBitmap(mime) || isBitmap(codecs?.lowercase())) return null
            return when (mime) {
                MIME_MEDIA3_CUES -> Media3CuesDecoder()
                MIME_SUBRIP -> SubripSampleDecoder
                MIME_SSA -> SsaSampleDecoder
                MIME_VTT, "text/x-webvtt" -> VttSampleDecoder
                MIME_TX3G -> Tx3gSampleDecoder
                else -> null
            }
        }

        private fun isBitmap(mime: String?): Boolean =
            mime != null && (mime.contains("pgs") || mime.contains("vobsub") || mime.contains("dvbsubs"))

        /** Plain text of a subtitle line: markup removed, line breaks kept. */
        fun cleanText(raw: String): String = raw
            .replace(Regex("\\{[^}]*\\}"), "")          // ASS override blocks {\i1}
            .replace("\\N", "\n").replace("\\n", "\n").replace("\\h", " ")
            .replace(Regex("<[^>]{1,40}>"), "")          // <i>, <font ...>, <c.yellow>
            .replace("&amp;", "&").replace("&lt;", "<").replace("&gt;", ">").replace("&nbsp;", " ")
            .lines().map { it.trim() }.filter { it.isNotEmpty() }
            .joinToString("\n")

        /** "hh:mm:ss,mmm" / "hh:mm:ss.mmm" / "h:mm:ss:cc" / "h:mm:ss.cc" → ms, or null. */
        internal fun parseTimestampMs(raw: String): Long? {
            val t = raw.trim()
            val m = Regex("^(\\d+):(\\d{1,2}):(\\d{1,2})(?:[.,:](\\d{1,3}))?$").matchEntire(t)
                ?: Regex("^(\\d{1,2}):(\\d{1,2})(?:[.,](\\d{1,3}))?$").matchEntire(t)?.let { mm ->
                    // mm:ss.mmm (WebVTT short form)
                    val min = mm.groupValues[1].toLong()
                    val sec = mm.groupValues[2].toLong()
                    val frac = fraction(mm.groupValues[3])
                    return (min * 60 + sec) * 1000 + frac
                }
                ?: return null
            val h = m.groupValues[1].toLong()
            val min = m.groupValues[2].toLong()
            val sec = m.groupValues[3].toLong()
            return ((h * 60 + min) * 60 + sec) * 1000 + fraction(m.groupValues[4])
        }

        private fun fraction(digits: String): Long = when (digits.length) {
            0 -> 0
            1 -> digits.toLong() * 100
            2 -> digits.toLong() * 10
            else -> digits.take(3).toLong()
        }
    }
}

@OptIn(UnstableApi::class)
private class Media3CuesDecoder : SubtitleSampleDecoder() {
    private val decoder = CueDecoder()
    override fun decode(timeUs: Long, data: ByteArray, offset: Int, size: Int): DecodedCue? {
        val cues = decoder.decode(timeUs, data, offset, size)
        val text = cleanText(cues.cues.mapNotNull { it.text?.toString() }.joinToString("\n"))
        // Bitmap cues decode to no text: nothing to store.
        if (text.isEmpty() && cues.cues.any { it.bitmap != null }) return null
        val startMs = cues.startTimeUs / 1000
        val endMs = if (cues.durationUs == C.TIME_UNSET) null else (cues.startTimeUs + cues.durationUs) / 1000
        return DecodedCue(startMs, endMs, text)
    }
}

/** Matroska SubRip sample: "1\n00:00:00,000 --> 00:00:02,500\n<text>" (times relative to the sample). */
internal object SubripSampleDecoder : SubtitleSampleDecoder() {
    override fun decode(timeUs: Long, data: ByteArray, offset: Int, size: Int): DecodedCue? =
        decodeArrowSample(timeUs, String(data, offset, size, Charsets.UTF_8))
}

/** Matroska WebVTT sample: "WEBVTT\n\n00:00:00.000 --> 00:00:02.500\n<text>". */
internal object VttSampleDecoder : SubtitleSampleDecoder() {
    override fun decode(timeUs: Long, data: ByteArray, offset: Int, size: Int): DecodedCue? =
        decodeArrowSample(timeUs, String(data, offset, size, Charsets.UTF_8))
}

private fun decodeArrowSample(timeUs: Long, raw: String): DecodedCue? {
    val startMs = timeUs / 1000
    val lines = raw.replace("\r\n", "\n").split('\n')
    val arrow = lines.indexOfFirst { it.contains("-->") }
    if (arrow < 0) {
        // Bare text sample (no timing prefix): end unknown.
        return DecodedCue(startMs, null, SubtitleSampleDecoder.cleanText(raw.removePrefix("WEBVTT")))
    }
    val parts = lines[arrow].split("-->")
    val relStart = SubtitleSampleDecoder.parseTimestampMs(parts[0]) ?: 0L
    val relEnd = SubtitleSampleDecoder.parseTimestampMs(parts.getOrElse(1) { "" }.trim().substringBefore(' '))
    val text = SubtitleSampleDecoder.cleanText(lines.drop(arrow + 1).joinToString("\n"))
    val endMs = relEnd?.takeIf { it > relStart }?.let { startMs + (it - relStart) }
    return DecodedCue(startMs, endMs, text)
}

/**
 * Matroska SSA/ASS sample: "Dialogue: 0:00:00:00,0:00:02:50,ReadOrder,Layer,Style,Name,MarginL,
 * MarginR,MarginV,Effect,Text" — text is everything after the 10th comma following the prefix.
 */
internal object SsaSampleDecoder : SubtitleSampleDecoder() {
    override fun decode(timeUs: Long, data: ByteArray, offset: Int, size: Int): DecodedCue? {
        val raw = String(data, offset, size, Charsets.UTF_8).trim()
        val body = raw.removePrefix("Dialogue:").trim()
        val fields = body.split(',', limit = 11)
        val startMs = timeUs / 1000
        if (fields.size < 11) return DecodedCue(startMs, null, cleanText(body))
        val relStart = parseTimestampMs(fields[0]) ?: 0L
        val relEnd = parseTimestampMs(fields[1])
        val endMs = relEnd?.takeIf { it > relStart }?.let { startMs + (it - relStart) }
        return DecodedCue(startMs, endMs, cleanText(fields[10]))
    }
}

/** MP4 timed text: 16-bit big-endian length + UTF-8/UTF-16 text (+ style boxes). End = next sample. */
internal object Tx3gSampleDecoder : SubtitleSampleDecoder() {
    override fun decode(timeUs: Long, data: ByteArray, offset: Int, size: Int): DecodedCue? {
        if (size < 2) return DecodedCue(timeUs / 1000, null, "")
        val len = ((data[offset].toInt() and 0xFF) shl 8) or (data[offset + 1].toInt() and 0xFF)
        val n = minOf(len, size - 2)
        val bomUtf16 = n >= 2 && data[offset + 2] == 0xFE.toByte() && data[offset + 3] == 0xFF.toByte()
        val text = if (bomUtf16) String(data, offset + 4, n - 2, Charsets.UTF_16BE) else String(data, offset + 2, n, Charsets.UTF_8)
        return DecodedCue(timeUs / 1000, null, cleanText(text))
    }
}
