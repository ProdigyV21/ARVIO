package com.arflix.tv.ui.screens.player.subtitles.bilingual

import androidx.media3.common.C
import androidx.media3.common.DataReader
import androidx.media3.common.util.ParsableByteArray
import androidx.media3.extractor.ExtractorOutput
import androidx.media3.extractor.SeekMap
import androidx.media3.extractor.TrackOutput
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * The second-subtitle text tap, fed exactly as the Matroska extractor feeds a TrackOutput:
 * two subtitle tracks (English SRT, Simplified Chinese SRT) and a video track in one file.
 */
@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
class SubtitleTextTapTest {

    /** Records what reaches the player, to prove the tap is transparent. */
    private class RecordingTrackOutput : TrackOutput {
        val bytes = java.io.ByteArrayOutputStream()
        val samples = mutableListOf<Pair<Long, Int>>()
        override fun format(format: androidx.media3.common.Format) {}
        override fun sampleData(input: DataReader, length: Int, allowEndOfInput: Boolean, sampleDataPart: Int): Int {
            val buf = ByteArray(length)
            val n = input.read(buf, 0, length)
            if (n > 0) bytes.write(buf, 0, n)
            return n
        }
        override fun sampleData(data: ParsableByteArray, length: Int, sampleDataPart: Int) {
            bytes.write(data.data, data.position, length)
            data.skipBytes(length)
        }
        override fun sampleMetadata(timeUs: Long, flags: Int, size: Int, offset: Int, cryptoData: TrackOutput.CryptoData?) {
            samples += timeUs to size
        }
    }

    private class RecordingOutput : ExtractorOutput {
        val tracks = HashMap<Int, RecordingTrackOutput>()
        override fun track(id: Int, type: Int): TrackOutput = tracks.getOrPut(id) { RecordingTrackOutput() }
        override fun endTracks() {}
        override fun seekMap(seekMap: SeekMap) {}
    }

    // Media3's Format needs android.* classes, so the test feeds the tap's format hook directly
    // (format() forwards exactly these fields to it).
    private fun TrackOutput.subrip(id: String, lang: String, label: String) =
        (this as SubtitleTapTrackOutput).onFormatInfo(id, "application/x-subrip", null, lang, label, forced = false)

    /** Matroska SubRip sample: timing prefix with the block duration, then the text. */
    private fun srtSample(durationMs: Long, text: String): ByteArray {
        val s = durationMs / 1000
        val ms = durationMs % 1000
        return "1\n00:00:00,000 --> 00:00:%02d,%03d\n%s".format(s, ms, text).toByteArray()
    }

    private fun writeSample(out: TrackOutput, timeMs: Long, data: ByteArray, viaReader: Boolean) {
        if (viaReader) {
            // In two parts through a DataReader, like a real extractor reading the input.
            val half = data.size / 2
            val reader = object : DataReader {
                var pos = 0
                override fun read(buffer: ByteArray, offset: Int, length: Int): Int {
                    val n = minOf(length, data.size - pos)
                    System.arraycopy(data, pos, buffer, offset, n); pos += n
                    return if (n == 0) C.RESULT_END_OF_INPUT else n
                }
            }
            out.sampleData(reader, half, false)
            out.sampleData(reader, data.size - half, false)
        } else {
            out.sampleData(ParsableByteArray(data), data.size)
        }
        out.sampleMetadata(timeMs * 1000, C.BUFFER_FLAG_KEY_FRAME, data.size, 0, null)
    }

    @Test fun `both embedded tracks are captured while the player output is untouched`() {
        val store = EmbeddedSubtitleStore()
        val inner = RecordingOutput()
        store.beginSource("http://x/movie.mkv")
        val output = SubtitleTapExtractorOutput(inner, store, "http://x/movie.mkv")

        val video = output.track(1, C.TRACK_TYPE_VIDEO)
        val enOut = output.track(3, C.TRACK_TYPE_TEXT)
        val zhOut = output.track(4, C.TRACK_TYPE_TEXT)
        enOut.subrip("3", "eng", "English")
        zhOut.subrip("4", "chi", "简体中文")

        val enSample = srtSample(2_500, "<i>Hello there.</i>")
        val zhSample = srtSample(1_200, "你好。")
        writeSample(video, 0, ByteArray(5_000) { 7 }, viaReader = true)
        writeSample(enOut, 10_000, enSample, viaReader = true)
        writeSample(zhOut, 10_100, zhSample, viaReader = false)
        writeSample(zhOut, 11_400, srtSample(1_000, "又见面了。"), viaReader = true)

        // Player side received exactly the original bytes and sample metadata.
        assertTrue(inner.tracks[3]!!.bytes.toByteArray().contentEquals(enSample))
        assertEquals(listOf(10_000_000L to enSample.size), inner.tracks[3]!!.samples)
        assertEquals(5_000, inner.tracks[1]!!.bytes.size())

        // The store holds both tracks, with text and timing.
        val tracks = store.tracks().associateBy { it.trackId }
        assertEquals(setOf(3, 4), tracks.keys)
        assertEquals("zh-Hans", tracks[4]!!.language?.tag)
        assertEquals(listOf(TimedText(10_000, 12_500, "Hello there.")), store.cues(3))
        assertEquals(
            listOf(TimedText(10_100, 11_300, "你好。"), TimedText(11_400, 12_400, "又见面了。")),
            store.cues(4)
        )
    }

    @Test fun `a new source drops the previous file's cues and late writes are ignored`() {
        val store = EmbeddedSubtitleStore()
        store.beginSource("ep1")
        val out1 = SubtitleTapExtractorOutput(RecordingOutput(), store, "ep1")
        val t1 = out1.track(3, C.TRACK_TYPE_TEXT)
        t1.subrip("3", "eng", "English")
        writeSample(t1, 1_000, srtSample(1_000, "Episode one"), viaReader = false)
        assertEquals(1, store.cueCount(3))

        store.beginSource("ep2")
        assertEquals(0, store.tracks().size)
        // The previous extractor is still finishing a sample: must not leak into episode 2.
        writeSample(t1, 2_000, srtSample(1_000, "late"), viaReader = false)
        assertEquals(0, store.cueCount(3))
    }

    @Test fun `a loader that starts before the player switches episodes keeps its cues in its own episode`() {
        val store = EmbeddedSubtitleStore()
        store.setActiveSource("ep1")
        val e1 = SubtitleTapExtractorOutput(RecordingOutput(), store, "ep1").track(3, C.TRACK_TYPE_TEXT)
        e1.subrip("3", "eng", "English")
        writeSample(e1, 1_000, srtSample(1_000, "Episode one"), viaReader = false)
        // Episode 2's extractor is already demuxing while episode 1 is still the active media.
        val e2 = SubtitleTapExtractorOutput(RecordingOutput(), store, "ep2").track(3, C.TRACK_TYPE_TEXT)
        e2.subrip("3", "eng", "English")
        writeSample(e2, 1_000, srtSample(1_000, "Episode two"), viaReader = false)
        assertEquals(listOf("Episode one"), store.cues(3).map { it.text })
        store.setActiveSource("ep2")
        assertEquals(listOf("Episode two"), store.cues(3).map { it.text })
        // A late write from episode 1 never shows in episode 2.
        writeSample(e1, 5_000, srtSample(1_000, "late ep1"), viaReader = false)
        assertEquals(listOf("Episode two"), store.cues(3).map { it.text })
        // Going back to episode 1 shows only its own lines.
        store.setActiveSource("ep1")
        assertEquals(listOf("Episode one", "late ep1"), store.cues(3).map { it.text })
    }

    @Test fun `seek re-demux does not duplicate cues`() {
        val store = EmbeddedSubtitleStore()
        store.beginSource("s")
        val out = SubtitleTapExtractorOutput(RecordingOutput(), store, "s").track(3, C.TRACK_TYPE_TEXT)
        out.subrip("3", "eng", "English")
        repeat(2) { writeSample(out, 5_000, srtSample(1_000, "Same line"), viaReader = false) }
        assertEquals(1, store.cueCount(3))
    }

    @Test fun `ssa and webvtt samples decode to clean text`() {
        val ssa = SubtitleSampleDecoder.forFormat("text/x-ssa", null)!!
        val raw = "Dialogue: 0:00:00:00,0:00:02:50,1,0,Default,,0,0,0,,{\\i1}Hello,\\Nworld".toByteArray()
        val cue = ssa.decode(3_000_000, raw, 0, raw.size)!!
        assertEquals(3_000L, cue.startMs)
        assertEquals(5_500L, cue.endMs)
        assertEquals("Hello,\nworld", cue.text)

        val vtt = SubtitleSampleDecoder.forFormat("text/vtt", null)!!
        val v = "WEBVTT\n\n00:00:00.000 --> 00:00:01.250\n<c.yellow>Hi</c> &amp; bye".toByteArray()
        val vc = vtt.decode(1_000_000, v, 0, v.size)!!
        assertEquals(DecodedCue(1_000, 2_250, "Hi & bye"), vc)
    }

    @Test fun `image subtitles get no text decoder`() {
        assertNull(SubtitleSampleDecoder.forFormat("application/pgs", null))
        assertNull(SubtitleSampleDecoder.forFormat("application/x-media3-cues", "application/pgs"))
        assertNull(SubtitleSampleDecoder.forFormat("application/vobsub", null))
    }

    @Test fun `cues without an end are closed by the next cue`() {
        val store = EmbeddedSubtitleStore()
        store.beginSource("s")
        store.onTrack("s", EmbeddedTextTrack(2, "2", "eng", null, "application/x-quicktime-tx3g", null))
        store.onCue("s", 2, 1_000, null, "First")
        store.onCue("s", 2, 2_200, null, "Second")
        assertEquals(TimedText(1_000, 2_200, "First"), store.cues(2).first())
    }
}
