package com.arflix.tv.ui.screens.player

import com.arflix.tv.ui.screens.player.subtitles.SubtitleSyncMatcher
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull
import org.junit.Test
import java.net.ServerSocket
import java.net.Socket
import java.nio.charset.Charset
import kotlin.concurrent.thread
import kotlin.system.measureTimeMillis

/**
 * Subtitle character-encoding detection. The regression: Hebrew subtitles served as windows-1255
 * (still common from Israeli providers) were decoded as UTF-8, turning every Hebrew character into
 * U+FFFD — and the damage was persisted into the local cache copy.
 */
class SubtitleEncodingTest {

    private val hebrewLine = "שמי הוא בארי אלן"
    private val cp1255 = Charset.forName("windows-1255")

    private fun srt(body: String) = "1\r\n00:00:01,769 --> 00:00:03,048\r\n$body\r\n"

    @Test
    fun `utf-8 hebrew decodes unchanged`() {
        val bytes = srt(hebrewLine).toByteArray(Charsets.UTF_8)

        val decoded = SubtitleSyncMatcher.decodeSubtitleBytes(bytes, "heb").text

        assertThat(decoded).contains(hebrewLine)
        assertThat(decoded).doesNotContain("�")
    }

    @Test
    fun `windows-1255 hebrew decodes to real hebrew`() {
        val bytes = srt(hebrewLine).toByteArray(cp1255)
        // Precondition: these bytes really are not valid UTF-8, else the test proves nothing.
        assertThat(String(bytes, Charsets.UTF_8)).contains("�")

        val decoded = SubtitleSyncMatcher.decodeSubtitleBytes(bytes, "heb").text

        assertThat(decoded).contains(hebrewLine)
        assertThat(decoded).doesNotContain("�")
    }

    @Test
    fun `language tag variants all resolve to hebrew`() {
        val bytes = srt(hebrewLine).toByteArray(cp1255)

        for (tag in listOf("he", "heb", "iw", "he-IL", "HE")) {
            assertThat(SubtitleSyncMatcher.decodeSubtitleBytes(bytes, tag).text).contains(hebrewLine)
        }
    }

    @Test
    fun `utf-8 bom is stripped`() {
        val bytes = byteArrayOf(0xEF.toByte(), 0xBB.toByte(), 0xBF.toByte()) +
            srt(hebrewLine).toByteArray(Charsets.UTF_8)

        val decoded = SubtitleSyncMatcher.decodeSubtitleBytes(bytes, "heb").text

        assertThat(decoded.first()).isEqualTo('1')
        assertThat(decoded).contains(hebrewLine)
    }

    @Test
    fun `utf-16 bom is honoured`() {
        val bytes = byteArrayOf(0xFF.toByte(), 0xFE.toByte()) +
            srt(hebrewLine).toByteArray(Charsets.UTF_16LE)

        val decoded = SubtitleSyncMatcher.decodeSubtitleBytes(bytes, "heb").text

        assertThat(decoded).contains(hebrewLine)
    }

    @Test
    fun `valid utf-8 wins even when a legacy code page would also decode`() {
        // windows-1252 accepts these bytes too; UTF-8 must be preferred so the text stays correct.
        val text = srt("Café — naïve")
        val decoded = SubtitleSyncMatcher.decodeSubtitleBytes(text.toByteArray(Charsets.UTF_8), "en").text

        assertThat(decoded).contains("Café — naïve")
    }

    @Test
    fun `unknown language still yields text rather than replacement characters`() {
        val bytes = srt(hebrewLine).toByteArray(cp1255)

        val decoded = SubtitleSyncMatcher.decodeSubtitleBytes(bytes, null).text

        // Not Hebrew (no language to go on) but never the U+FFFD damage that broke playback.
        assertThat(decoded).doesNotContain("�")
    }

    @Test
    fun `cyrillic windows-1251 decodes for russian`() {
        val russian = "Меня зовут Барри Аллен"
        val bytes = srt(russian).toByteArray(Charset.forName("windows-1251"))

        val decoded = SubtitleSyncMatcher.decodeSubtitleBytes(bytes, "rus")

        assertThat(decoded.text).contains(russian)
        assertThat(decoded.charsetName).isEqualTo("windows-1251")
    }

    @Test
    fun `timings survive a legacy decode`() {
        val bytes = srt(hebrewLine).toByteArray(cp1255)

        val cues = SubtitleSyncMatcher.parseCues(SubtitleSyncMatcher.decodeSubtitleBytes(bytes, "he").text)

        assertThat(cues).hasSize(1)
        assertThat(cues[0].startMs).isEqualTo(1_769L)
        assertThat(cues[0].text).contains(hebrewLine)
    }

    // ── Cancellation ────────────────────────────────────────────────────────────

    /**
     * A server that completes the TCP handshake and then never answers — the stall that a slow
     * addon produces. OkHttp's blocking `execute()` would sit here until its 15 s read timeout,
     * ignoring the caller's coroutine timeout entirely.
     */
    private fun stalledServer(): Pair<ServerSocket, Thread> {
        val server = ServerSocket(0)
        val held = mutableListOf<Socket>()
        val acceptor = thread(isDaemon = true) {
            runCatching { while (true) held += server.accept() }
        }
        return server to acceptor
    }

    @Test
    fun `loadRaw aborts on coroutine timeout instead of waiting out the socket`() {
        val (server, _) = stalledServer()
        try {
            var result: String? = "unset"
            val elapsedMs = measureTimeMillis {
                result = runBlocking {
                    withTimeoutOrNull(700) {
                        SubtitleSyncMatcher.loadRaw("http://127.0.0.1:${server.localPort}/s.srt", "he")
                    }
                }
            }

            assertThat(result).isNull()
            // Comfortably under OkHttp's 15 s read timeout, which is what a blocking call would hit.
            assertThat(elapsedMs).isLessThan(5_000L)
        } finally {
            server.close()
        }
    }

    @Test
    fun `loadRaw returns promptly when its job is cancelled`() {
        val (server, _) = stalledServer()
        try {
            val elapsedMs = measureTimeMillis {
                runBlocking {
                    val job = async {
                        SubtitleSyncMatcher.loadRaw("http://127.0.0.1:${server.localPort}/s.srt", "he")
                    }
                    Thread.sleep(200)
                    job.cancel()
                    runCatching { job.await() }
                }
            }

            assertThat(elapsedMs).isLessThan(5_000L)
        } finally {
            server.close()
        }
    }
}
