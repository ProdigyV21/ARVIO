package com.arflix.tv.recording

import com.arflix.tv.data.model.IptvChannel
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import okio.Buffer
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.File
import java.nio.file.Files
import java.time.ZoneOffset
import java.util.concurrent.atomic.AtomicInteger
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class LiveRecordingTest {
    private fun channel(id: String) = IptvChannel(id, id, "http://x/$id", "Sports")

    @Test fun masterPlaylistPicksBestRenditionUnderTheCap() {
        val master = parseHlsPlaylist("""
            #EXTM3U
            #EXT-X-STREAM-INF:BANDWIDTH=12000000,RESOLUTION=3840x2160,CODECS="avc1.640033,mp4a.40.2"
            uhd/index.m3u8
            #EXT-X-STREAM-INF:BANDWIDTH=5000000,RESOLUTION=1920x1080
            hd/index.m3u8
            #EXT-X-STREAM-INF:BANDWIDTH=1200000,RESOLUTION=640x360
            sd/index.m3u8
        """.trimIndent(), "http://host/live/master.m3u8") as HlsPlaylist.Master
        assertEquals(3, master.variants.size)
        assertEquals("http://host/live/hd/index.m3u8", chooseRecordingVariant(master.variants)?.uri)
        assertEquals(1080, chooseRecordingVariant(master.variants)?.height)
        assertEquals("http://host/live/sd/index.m3u8", chooseRecordingVariant(master.variants, maxBandwidth = 1_000_000)?.uri)
        assertNull(chooseRecordingVariant(emptyList()))
    }

    @Test fun mediaPlaylistKeepsSequenceKeysAndMap() {
        val media = parseHlsPlaylist("""
            #EXTM3U
            #EXT-X-TARGETDURATION:4
            #EXT-X-MEDIA-SEQUENCE:100
            #EXT-X-MAP:URI="init.mp4"
            #EXT-X-KEY:METHOD=AES-128,URI="https://keys/k1",IV=0x0000000000000000000000000000000A
            #EXTINF:4.0,
            seg100.m4s
            #EXT-X-DISCONTINUITY
            #EXTINF:3.5,
            /abs/seg101.m4s
            #EXT-X-KEY:METHOD=NONE
            #EXTINF:4.0,
            seg102.m4s
            #EXT-X-ENDLIST
        """.trimIndent(), "http://host/a/b/index.m3u8") as HlsPlaylist.Media
        assertEquals(listOf(100L, 101L, 102L), media.segments.map { it.sequence })
        assertEquals("http://host/a/b/seg100.m4s", media.segments[0].uri)
        assertEquals("http://host/abs/seg101.m4s", media.segments[1].uri)
        assertEquals("http://host/a/b/init.mp4", media.segments[0].mapUri)
        assertTrue(media.segments[1].discontinuity)
        assertTrue(media.segments[0].key!!.isAes128)
        assertEquals(10, media.segments[0].key!!.iv!![15].toInt())
        assertNull(media.segments[2].key)
        assertTrue(media.ended)
        assertEquals(4.0, media.targetDurationSec, 0.0)
    }

    @Test fun attributesKeepCommasInsideQuotes() {
        val attrs = hlsAttributes("BANDWIDTH=1,CODECS=\"avc1,mp4a\",RESOLUTION=1x2")
        assertEquals("avc1,mp4a", attrs["CODECS"])
        assertEquals("1x2", attrs["RESOLUTION"])
    }

    @Test fun onlyNewSegmentsAreWritten() {
        fun seg(n: Long) = HlsSegment("s$n", n, 4.0, null, null, false)
        val window = (10L..15L).map(::seg)
        assertEquals(listOf(13L, 14L, 15L), startingHlsSegments(window).map { it.sequence })
        assertEquals(listOf(14L, 15L), newHlsSegments(window, 13).map { it.sequence })
        assertTrue(newHlsSegments(window, 15).isEmpty())
        // A restarted count must not stop the recording.
        assertEquals(6, newHlsSegments((1L..6L).map(::seg), 500).size)
    }

    @Test fun sequenceIvIsBigEndian() {
        val iv = hlsSequenceIv(258)
        assertEquals(1, iv[14].toInt())
        assertEquals(2, iv[15].toInt())
        assertEquals(0, iv[0].toInt())
        assertNull(parseHlsIv("0xZZ"))
    }

    @Test fun windowAddsMarginsAndStaysBounded() {
        val start = 1_800_000_000_000L
        val end = start + 105 * 60_000L
        assertEquals(start - RECORDING_PRE_PADDING_MS, recordingWindowStart(start))
        assertEquals(end + RECORDING_POST_PADDING_MS, recordingWindowEnd(start, end))
        // No end in the guide: two hours plus margin.
        assertEquals(start + RECORDING_DEFAULT_DURATION_MS + RECORDING_POST_PADDING_MS, recordingWindowEnd(start, 0))
        // A broken guide entry never records for days.
        assertEquals(start + RECORDING_MAX_DURATION_MS, recordingWindowEnd(start, start + 48 * 3_600_000L))
        val rec = LiveRecording("r", "A vs B", listOf(channel("c")), start, end)
        assertEquals(RecordingDue.Wait, rec.dueAt(start - 10 * 60_000L))
        assertEquals(RecordingDue.Start, rec.dueAt(start - 60_000L))
        assertEquals(RecordingDue.Missed, rec.dueAt(end + RECORDING_POST_PADDING_MS))
        assertEquals(RecordingDue.Done, rec.copy(status = LiveRecording.STATUS_DONE).dueAt(start))
    }

    @Test fun fileNamesAreSafe() {
        val name = recordingFileName("Atlético Madrid vs. FC Köln / Cup?", 1_800_000_000_000L, "ts", ZoneOffset.UTC)
        assertEquals("2027-01-15 08.00 Atletico Madrid vs. FC Koln Cup.ts", name)
        assertTrue(recordingFileName("???", 0L, "mp4", ZoneOffset.UTC).endsWith("Recording.mp4"))
    }

    @Test fun storedListIsSanitizedTrimmedAndRecoveredAfterRestart() {
        val now = 1_800_000_000_000L
        val old = (1..70).map { LiveRecording("d$it", "g$it", emptyList(), now - it * 3_600_000L, now - it * 3_600_000L + 1, status = LiveRecording.STATUS_DONE) }
        val pending = LiveRecording("p", "next", listOf(channel("c")), now + 3_600_000L, now + 7_200_000L)
        val trimmed = (old + pending).trimmedRecordings(10)
        assertEquals(10, trimmed.size)
        assertEquals("p", trimmed.first().id)
        val broken = LiveRecording("x", "t", emptyList(), now, now + 1)
        assertTrue(listOf(broken).sanitizedRecordings().isEmpty())
        val active = pending.copy(status = LiveRecording.STATUS_RECORDING, programmeStart = now - 60_000L)
        assertEquals(LiveRecording.STATUS_SCHEDULED, listOf(active).afterRestart(now).single().status)
        assertEquals(LiveRecording.STATUS_DONE, listOf(active.copy(bytes = 5)).afterRestart(now + 10 * 3_600_000L).single().status)
        assertEquals(LiveRecording.STATUS_FAILED, listOf(active).afterRestart(now + 10 * 3_600_000L).single().status)
    }

    @Test fun plainStreamIsCopiedAndReopenedAfterADrop() = runBlocking {
        val server = MockWebServer()
        val hits = AtomicInteger()
        server.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                hits.incrementAndGet()
                return MockResponse().setHeader("Content-Type", "video/mp2t").setBody(Buffer().write(ByteArray(188) { 0x47 }))
            }
        }
        server.start()
        try {
            var now = 0L
            val dir = Files.createTempDirectory("rec").toFile()
            val recorder = LiveStreamRecorder(OkHttpClient(), emptyMap(), clock = { now }, freeBytes = { Long.MAX_VALUE },
                pause = { now += it })
            var ext: String? = null
            val outcome = recorder.record(server.url("/live/1.ts").toString(), until = 20_000L, output = { e -> ext = e; File(dir, "out.$e") })
            assertEquals("ts", ext)
            assertTrue(outcome is RecordOutcome.StreamFailed || outcome is RecordOutcome.Completed)
            // Every reopen appended another 188 bytes to the same file.
            assertEquals(outcome.bytes, File(dir, "out.ts").length())
            assertTrue(hits.get() >= 2)
        } finally {
            server.shutdown()
        }
    }

    @Test fun hlsSegmentsAreDecryptedAndAppendedOnce() = runBlocking {
        val key = ByteArray(16) { it.toByte() }
        fun encrypt(data: ByteArray, sequence: Long): ByteArray = Cipher.getInstance("AES/CBC/PKCS5Padding").run {
            init(Cipher.ENCRYPT_MODE, SecretKeySpec(key, "AES"), IvParameterSpec(hlsSequenceIv(sequence)))
            doFinal(data)
        }
        val server = MockWebServer()
        val playlistHits = AtomicInteger()
        server.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse = when (request.path) {
                "/master.m3u8" -> MockResponse().setBody("#EXTM3U\n#EXT-X-STREAM-INF:BANDWIDTH=900000\nlow.m3u8\n")
                "/low.m3u8" -> {
                    val first = playlistHits.incrementAndGet() == 1
                    val body = buildString {
                        append("#EXTM3U\n#EXT-X-TARGETDURATION:2\n#EXT-X-MEDIA-SEQUENCE:${if (first) 5 else 6}\n")
                        append("#EXT-X-KEY:METHOD=AES-128,URI=\"/key\"\n")
                        if (first) append("#EXTINF:2,\nseg5.ts\n")
                        append("#EXTINF:2,\nseg6.ts\n")
                        if (!first) append("#EXTINF:2,\nseg7.ts\n#EXT-X-ENDLIST\n")
                    }
                    MockResponse().setHeader("Content-Type", "application/vnd.apple.mpegurl").setBody(body)
                }
                "/key" -> MockResponse().setBody(Buffer().write(key))
                "/seg5.ts", "/seg6.ts", "/seg7.ts" -> {
                    val n = request.path!!.filter { it.isDigit() }.toLong()
                    MockResponse().setBody(Buffer().write(encrypt(ByteArray(100) { n.toByte() }, n)))
                }
                else -> MockResponse().setResponseCode(404)
            }
        }
        server.start()
        try {
            var now = 0L
            val dir = Files.createTempDirectory("rec").toFile()
            val recorder = LiveStreamRecorder(OkHttpClient(), emptyMap(), clock = { now }, freeBytes = { Long.MAX_VALUE },
                pause = { now += it })
            val outcome = recorder.record(server.url("/master.m3u8").toString(), until = 600_000L, output = { e -> File(dir, "game.$e") })
            assertTrue(outcome is RecordOutcome.Completed)
            val saved = File(dir, "game.ts").readBytes()
            assertEquals(300, saved.size)
            assertArrayEquals(ByteArray(100) { 5 } + ByteArray(100) { 6 } + ByteArray(100) { 7 }, saved)
        } finally {
            server.shutdown()
        }
    }

    @Test fun sampleAesIsRefused() = runBlocking {
        val server = MockWebServer()
        server.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse =
                MockResponse().setBody("#EXTM3U\n#EXT-X-TARGETDURATION:2\n#EXT-X-KEY:METHOD=SAMPLE-AES,URI=\"skd://x\"\n#EXTINF:2,\na.ts\n")
        }
        server.start()
        try {
            val dir = Files.createTempDirectory("rec").toFile()
            val outcome = LiveStreamRecorder(OkHttpClient(), emptyMap(), clock = { 0L }, pause = {})
                .record(server.url("/p.m3u8").toString(), until = 10_000L, output = { e -> File(dir, "x.$e") })
            assertTrue(outcome is RecordOutcome.Unsupported)
        } finally {
            server.shutdown()
        }
    }
}
