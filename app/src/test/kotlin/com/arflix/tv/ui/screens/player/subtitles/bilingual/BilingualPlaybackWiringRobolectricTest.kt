package com.arflix.tv.ui.screens.player.subtitles.bilingual

import android.content.Context
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import androidx.media3.common.C
import androidx.media3.common.Format
import androidx.media3.common.text.Cue
import androidx.media3.common.text.CueGroup
import androidx.media3.common.util.Clock
import androidx.media3.datasource.DataSpec
import androidx.media3.datasource.FileDataSource
import androidx.media3.decoder.DecoderInputBuffer
import androidx.media3.exoplayer.FormatHolder
import androidx.media3.exoplayer.Renderer
import androidx.media3.exoplayer.RendererConfiguration
import androidx.media3.exoplayer.analytics.PlayerId
import androidx.media3.exoplayer.audio.AudioRendererEventListener
import androidx.media3.exoplayer.metadata.MetadataOutput
import androidx.media3.exoplayer.source.MediaSource
import androidx.media3.exoplayer.source.SampleStream
import androidx.media3.exoplayer.text.TextOutput
import androidx.media3.exoplayer.video.VideoRendererEventListener
import androidx.media3.extractor.DefaultExtractorInput
import androidx.media3.extractor.DefaultExtractorsFactory
import androidx.media3.extractor.Extractor
import androidx.media3.extractor.ExtractorOutput
import androidx.media3.extractor.PositionHolder
import androidx.media3.extractor.SeekMap
import androidx.media3.extractor.TrackOutput
import com.arflix.tv.data.model.Subtitle
import com.arflix.tv.ui.screens.player.engine.exoplayer.AiSubtitleRenderersFactory
import com.arflix.tv.ui.screens.player.subtitles.SubtitleTranslationManager
import com.arflix.tv.ui.screens.player.subtitles.SubtitleTranslationService
import com.arflix.tv.ui.screens.player.subtitles.TranslationResult
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import org.robolectric.annotation.ConscryptMode
import java.io.ByteArrayOutputStream
import java.io.File

/**
 * End-to-end Robolectric wiring tests across:
 * MKV demux + SubtitleTextTap -> EmbeddedSubtitleStore -> SmartBilingualPlanner
 * -> AiSubtitleRenderersFactory + Media3 TextRenderer -> BilingualCueBus / legacy TextOutput.
 */
@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28], manifest = Config.NONE, application = android.app.Application::class)
@ConscryptMode(ConscryptMode.Mode.OFF)
@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
class BilingualPlaybackWiringRobolectricTest {

    @get:Rule val tempDir = TemporaryFolder()

    private lateinit var context: Context
    private lateinit var service: SubtitleTranslationService
    private lateinit var scope: CoroutineScope
    private lateinit var manager: SubtitleTranslationManager

    @Before fun setUp() {
        context = RuntimeEnvironment.getApplication()
        service = mockk()
        coEvery { service.translateBatch(any(), any()) } answers {
            val lines = firstArg<List<String>>()
            TranslationResult(lines.map { "TR:$it" }, success = true)
        }
        coEvery { service.repairSuspectLines(any(), any(), any()) } answers {
            secondArg<TranslationResult>()
        }
        scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
        manager = SubtitleTranslationManager(service, "Simplified Chinese", scope)
    }

    @After fun tearDown() {
        scope.cancel()
    }

    @Test fun dualEmbeddedMkvBindsRedirectMergeToOverlayAndNeverCallsAiTranslation() = runBlocking {
        val mkv = writeDualSubMkv(tempDir.newFile("episode1.mkv"))
        val mkvUri = Uri.fromFile(mkv)
        val store = EmbeddedSubtitleStore()

        // Simulate PlayerScreen opening a proxy/redirect URL before extractor resolves actual URI.
        store.openSource("http://proxy.local/redirect-ep1")
        store.bindLoadSource("http://proxy.local/redirect-ep1")

        val factory = SubtitleTextTapExtractorsFactory(
            delegate = DefaultExtractorsFactory().experimentalSetTextTrackTranscodingEnabled(true),
            store = store,
            currentSourceKey = { store.currentSourceKey() },
            enabledProvider = { true },
        )
        val captured = CaptureOutput()
        demuxFile(mkv, mkvUri, factory, captured)

        // Redirect URI bound into store, with both embedded text tracks populated.
        assertEquals(mkvUri.toString(), store.currentSourceKey())
        val tracks = store.tracks().associateBy { it.trackId }
        assertEquals(setOf(2, 3), tracks.keys)
        assertEquals("en", tracks[2]?.language?.tag)
        assertEquals("zh-Hans", tracks[3]?.language?.tag)
        assertTrue(store.cues(2, 0L, 20_000L).isNotEmpty())
        assertTrue(store.cues(3, 0L, 20_000L).isNotEmpty())

        // Planner picks MergeEmbedded (no AI) when both tracks exist in AUTO mode.
        val playerSubs = listOf(
            Subtitle(id = "2", url = "", lang = "eng", label = "English", isEmbedded = true),
            Subtitle(id = "3", url = "", lang = "chi", label = "简体中文", isEmbedded = true),
        )
        val candidates = buildCandidates(playerSubs, store)
        val plan = SmartBilingualPlanner.plan(
            SmartBilingualPlanner.Input(
                candidates = candidates,
                primaryPreference = "en",
                secondaryLanguage = SubtitleLanguage.SIMPLIFIED_CHINESE,
                audioLanguageTag = "ja",
                selectedId = "2",
                userPinnedSelection = false,
                aiConfigured = true,
                mode = BilingualGenerationMode.AUTO,
            )
        )
        assertTrue("Expected MergeEmbedded, got $plan", plan is SmartPlan.MergeEmbedded)
        val merge = plan as SmartPlan.MergeEmbedded
        assertEquals("2", merge.primary.id)
        assertEquals("3", merge.secondary.id)

        // Wire PlayerViewModel + SmartBilingualController merge state into SubtitleTranslationManager.
        manager.secondarySource = DualTrackSecondarySource(
            store,
            merge.primary.tapTrackId,
            requireNotNull(merge.secondary.tapTrackId),
        )
        manager.bilingualSource = SubtitleTranslationManager.BilingualSource.EMBEDDED_TRACK
        manager.displayMode = BilingualDisplayMode.BILINGUAL
        manager.bilingualActive = true
        manager.isEnabled = true

        assertFalse(manager.translationNeeded)
        // Defensive direct calls must also stay completely offline in merge mode.
        assertEquals("Hello world.", manager.translate("Hello world."))
        manager.preTranslateWindow(listOf("Hello world.", "Second line."))

        // Drive AiSubtitleRenderersFactory + real Media3 TextRenderer with primary track samples.
        val renderersFactory = AiSubtitleRenderersFactory(context, manager, scope)
        val legacyGroups = mutableListOf<CueGroup>()
        val legacyCues = mutableListOf<List<Cue>>()
        val renderer = createTextRenderer(
            renderersFactory,
            object : TextOutput {
                override fun onCues(cueGroup: CueGroup) { legacyGroups += cueGroup }
                @Deprecated("Deprecated in Java")
                override fun onCues(cues: List<Cue>) { legacyCues += cues }
            },
        )

        val primaryTrack = captured.tracks[2]!!
        val stream = InMemorySampleStream(primaryTrack.format!!, primaryTrack.samples)
        renderer.init(0, PlayerId.UNSET, Clock.DEFAULT)
        renderer.enable(
            RendererConfiguration.DEFAULT,
            arrayOf(primaryTrack.format!!),
            stream,
            0L,
            false,
            true,
            0L,
            0L,
            MediaSource.MediaPeriodId("period-0"),
        )
        renderer.start()
        repeat(primaryTrack.samples.size + 2) { renderer.render(0L, 0L) }
        renderer.render(1_200_000L, SystemClock.elapsedRealtime() * 1000L)
        shadowOf(Looper.getMainLooper()).idle()

        // Bilingual overlay received both primary + secondary lines; SubtitleView stayed empty.
        val frame = manager.bilingualBus.frame.value
        assertNotNull(frame)
        assertEquals("Hello world.", frame!!.original)
        assertEquals("你好，世界。", frame.translation)
        assertTrue(legacyGroups.all { it.cues.isEmpty() })
        assertTrue(legacyCues.all { it.isEmpty() })
        coVerify(exactly = 0) { service.translateBatch(any(), any()) }
    }

    @Test fun disabledSubtitleTapStoresNoTracksOrCues() {
        val mkv = writeDualSubMkv(tempDir.newFile("disabled.mkv"))
        val mkvUri = Uri.fromFile(mkv)
        val store = EmbeddedSubtitleStore()
        store.openSource(mkvUri.toString())

        val factory = SubtitleTextTapExtractorsFactory(
            delegate = DefaultExtractorsFactory().experimentalSetTextTrackTranscodingEnabled(true),
            store = store,
            currentSourceKey = { store.currentSourceKey() },
            enabledProvider = { false },
        )
        demuxFile(mkv, mkvUri, factory, CaptureOutput())

        assertTrue(store.tracks().isEmpty())
        assertTrue(store.cues(2, 0L, 60_000L).isEmpty())
        assertTrue(store.cues(3, 0L, 60_000L).isEmpty())
    }

    @Test fun originalOnlyAndOffDisplayModesDoNotSuppressStandaloneAiTranslation() = runBlocking {
        val mkv = writeDualSubMkv(tempDir.newFile("standalone_ai.mkv"))
        val mkvUri = Uri.fromFile(mkv)
        val captured = CaptureOutput()
        demuxFile(
            mkv,
            mkvUri,
            DefaultExtractorsFactory().experimentalSetTextTrackTranscodingEnabled(true),
            captured,
        )

        // Simulate user having ORIGINAL_ONLY (or OFF) in bilingual settings, then leaving smart mode
        // back to regular standalone AI translation.
        manager.bilingualSource = SubtitleTranslationManager.BilingualSource.AI_TRANSLATION
        manager.targetLanguage = "Hebrew"
        manager.isEnabled = true

        manager.displayMode = BilingualDisplayMode.ORIGINAL_ONLY
        manager.bilingualActive = false
        assertTrue(manager.translationNeeded)

        manager.displayMode = BilingualDisplayMode.OFF
        assertTrue(manager.translationNeeded)

        manager.preTranslateWindow(listOf("Hello world."))
        val renderersFactory = AiSubtitleRenderersFactory(context, manager, scope)
        val legacyGroups = mutableListOf<CueGroup>()
        val renderer = createTextRenderer(
            renderersFactory,
            object : TextOutput {
                override fun onCues(cueGroup: CueGroup) { legacyGroups += cueGroup }
                @Deprecated("Deprecated in Java")
                override fun onCues(cues: List<Cue>) = Unit
            },
        )
        val primaryTrack = captured.tracks[2]!!
        renderer.init(0, PlayerId.UNSET, Clock.DEFAULT)
        renderer.enable(
            RendererConfiguration.DEFAULT,
            arrayOf(primaryTrack.format!!),
            InMemorySampleStream(primaryTrack.format!!, primaryTrack.samples),
            0L,
            false,
            true,
            0L,
            0L,
            MediaSource.MediaPeriodId("period-ai"),
        )
        renderer.start()
        repeat(primaryTrack.samples.size + 2) { renderer.render(0L, 0L) }
        renderer.render(1_200_000L, SystemClock.elapsedRealtime() * 1000L)
        shadowOf(Looper.getMainLooper()).idle()

        coVerify(atLeast = 1) { service.translateBatch(any(), eq("Hebrew")) }
        val nonEmpty = legacyGroups.lastOrNull { it.cues.isNotEmpty() }
        assertNotNull(nonEmpty)
        assertTrue(nonEmpty!!.cues.first().text.toString().contains("TR:Hello world."))
        assertNull(manager.bilingualBus.frame.value)
    }

    private fun createTextRenderer(
        renderersFactory: AiSubtitleRenderersFactory,
        output: TextOutput,
    ): Renderer = renderersFactory.createRenderers(
        Handler(Looper.getMainLooper()),
        object : VideoRendererEventListener {},
        object : AudioRendererEventListener {},
        output,
        MetadataOutput { },
    ).first { it.trackType == C.TRACK_TYPE_TEXT }

    // ── Extractor + SampleStream test helpers ──────────────────────────────────

    private data class RecordedSample(val timeUs: Long, val flags: Int, val data: ByteArray)

    private class CapturedTrack : TrackOutput {
        var format: Format? = null
        val samples = mutableListOf<RecordedSample>()
        private val pending = ByteArrayOutputStream()

        override fun format(format: Format) { this.format = format }

        override fun sampleData(
            input: androidx.media3.common.DataReader,
            length: Int,
            allowEndOfInput: Boolean,
            sampleDataPart: Int,
        ): Int {
            val buf = ByteArray(length)
            val read = input.read(buf, 0, length)
            if (read > 0) pending.write(buf, 0, read)
            return read
        }

        override fun sampleData(data: androidx.media3.common.util.ParsableByteArray, length: Int, sampleDataPart: Int) {
            pending.write(data.data, data.position, length)
            data.skipBytes(length)
        }

        override fun sampleMetadata(timeUs: Long, flags: Int, size: Int, offset: Int, cryptoData: TrackOutput.CryptoData?) {
            val bytes = pending.toByteArray()
            val start = (bytes.size - offset - size).coerceAtLeast(0)
            val slice = bytes.copyOfRange(start, (start + size).coerceAtMost(bytes.size))
            samples += RecordedSample(timeUs, flags, slice)
            if (offset == 0) pending.reset()
        }
    }

    private class CaptureOutput : ExtractorOutput {
        val tracks = HashMap<Int, CapturedTrack>()
        override fun track(id: Int, type: Int): TrackOutput = tracks.getOrPut(id) { CapturedTrack() }
        override fun endTracks() = Unit
        override fun seekMap(seekMap: SeekMap) = Unit
    }

    private class InMemorySampleStream(
        private val format: Format,
        private val samples: List<RecordedSample>,
    ) : SampleStream {
        private var formatSent = false
        private var index = 0
        private var eosSent = false

        override fun isReady(): Boolean = true
        override fun maybeThrowError() = Unit
        override fun skipData(positionUs: Long): Int = 0

        override fun readData(formatHolder: FormatHolder, buffer: DecoderInputBuffer, readFlags: Int): Int {
            if (!formatSent || (readFlags and SampleStream.FLAG_REQUIRE_FORMAT) != 0) {
                formatHolder.format = format
                formatSent = true
                return C.RESULT_FORMAT_READ
            }
            if (index >= samples.size) {
                if (eosSent) return C.RESULT_NOTHING_READ
                eosSent = true
                buffer.clear()
                buffer.setFlags(C.BUFFER_FLAG_END_OF_STREAM)
                return C.RESULT_BUFFER_READ
            }
            val s = samples[index]
            if ((readFlags and SampleStream.FLAG_OMIT_SAMPLE_DATA) == 0) {
                buffer.clear()
                buffer.ensureSpaceForWrite(s.data.size)
                buffer.data?.put(s.data)
                buffer.timeUs = s.timeUs
                buffer.setFlags(s.flags)
            }
            if ((readFlags and SampleStream.FLAG_PEEK) == 0) {
                index++
            }
            return C.RESULT_BUFFER_READ
        }
    }

    private fun demuxFile(
        file: File,
        uri: Uri,
        factory: androidx.media3.extractor.ExtractorsFactory,
        output: CaptureOutput,
    ) {
        val candidates = factory.createExtractors(uri, emptyMap())
        val ds = FileDataSource()
        var pos = 0L
        var extractor: Extractor? = null
        try {
            ds.open(DataSpec(uri))
            val sniffInput = DefaultExtractorInput(ds, 0L, file.length())
            extractor = candidates.first { candidate ->
                val ok = runCatching { candidate.sniff(sniffInput) }.getOrDefault(false)
                sniffInput.resetPeekPosition()
                ok
            }
            ds.close()

            extractor.init(output)
            val holder = PositionHolder()
            while (true) {
                ds.open(DataSpec(uri, pos, C.LENGTH_UNSET.toLong()))
                val input = DefaultExtractorInput(ds, pos, file.length())
                while (true) {
                    val res = extractor.read(input, holder)
                    if (res == Extractor.RESULT_END_OF_INPUT) return
                    if (res == Extractor.RESULT_SEEK) {
                        pos = holder.position
                        ds.close()
                        break
                    }
                }
            }
        } finally {
            runCatching { ds.close() }
            extractor?.release()
        }
    }

    // ── Synthetic MKV writer (1 VP8 video track + eng/chi S_TEXT/UTF8 tracks) ──

    private fun writeDualSubMkv(dest: File): File {
        val ebmlHeader = elem(
            0x1A45DFA3L,
            cat(
                elem(0x4286L, 1L),
                elem(0x42F7L, 1L),
                elem(0x42F2L, 4L),
                elem(0x42F3L, 8L),
                elem(0x4282L, "matroska"),
                elem(0x4287L, 2L),
                elem(0x4285L, 2L),
            )
        )
        val info = elem(0x1549A966L, elem(0x2AD7B1L, 1_000_000L))
        val videoTrack = elem(
            0xAEL,
            cat(
                elem(0xD7L, 1L),
                elem(0x73C5L, 1L),
                elem(0x83L, 1L),
                elem(0x86L, "V_VP8"),
                elem(0xE0L, cat(elem(0xB0L, 16L), elem(0xBAL, 16L))),
            )
        )
        val enTrack = elem(
            0xAEL,
            cat(
                elem(0xD7L, 2L),
                elem(0x73C5L, 2L),
                elem(0x83L, 17L),
                elem(0x22B59CL, "eng"),
                elemUtf8(0x536EL, "English"),
                elem(0x86L, "S_TEXT/UTF8"),
            )
        )
        val zhTrack = elem(
            0xAEL,
            cat(
                elem(0xD7L, 3L),
                elem(0x73C5L, 3L),
                elem(0x83L, 17L),
                elem(0x22B59CL, "chi"),
                elemUtf8(0x536EL, "简体中文"),
                elem(0x86L, "S_TEXT/UTF8"),
            )
        )
        val tracks = elem(0x1654AE6BL, cat(videoTrack, enTrack, zhTrack))
        val pairs = listOf(
            1_000 to ("Hello world." to "你好，世界。"),
            4_000 to ("How are you?" to "你好吗？"),
            7_000 to ("See you soon." to "待会儿见。"),
            10_000 to ("Good night." to "晚安。"),
        )
        val blocks = mutableListOf<ByteArray>(elem(0xE7L, 0L))
        for ((relMs, texts) in pairs) {
            blocks += subBlockGroup(track = 2, relMs = relMs, durationMs = 2_000L, text = texts.first)
            blocks += subBlockGroup(track = 3, relMs = relMs, durationMs = 2_000L, text = texts.second)
        }
        val cluster = elem(0x1F43B675L, blocks.reduce { a, b -> a + b })
        val segment = elem(0x18538067L, cat(info, tracks, cluster))
        dest.writeBytes(ebmlHeader + segment)
        return dest
    }

    private fun subBlockGroup(track: Int, relMs: Int, durationMs: Long, text: String): ByteArray {
        val textBytes = text.toByteArray(Charsets.UTF_8)
        val blockPayload = ByteArray(4 + textBytes.size)
        blockPayload[0] = (0x80 or track).toByte()
        blockPayload[1] = ((relMs shr 8) and 0xFF).toByte()
        blockPayload[2] = (relMs and 0xFF).toByte()
        blockPayload[3] = 0x00
        textBytes.copyInto(blockPayload, 4)
        return elem(0xA0L, cat(elem(0xA1L, blockPayload), elem(0x9BL, durationMs)))
    }

    private fun idBytes(id: Long): ByteArray {
        val len = when {
            id <= 0xFFL -> 1
            id <= 0xFFFFL -> 2
            id <= 0xFFFFFFL -> 3
            else -> 4
        }
        return ByteArray(len) { i -> ((id shr (8 * (len - 1 - i))) and 0xFF).toByte() }
    }

    private fun sizeBytes(size: Long): ByteArray {
        for (len in 1..8) {
            val cap = (1L shl (7 * len)) - 1
            if (size < cap) {
                val out = ByteArray(len)
                var rem = size
                for (i in len - 1 downTo 0) {
                    out[i] = (rem and 0xFF).toByte()
                    rem = rem shr 8
                }
                out[0] = (out[0].toInt() or (0x80 shr (len - 1))).toByte()
                return out
            }
        }
        error("size too large: $size")
    }

    private fun uintBytes(value: Long): ByteArray {
        if (value == 0L) return byteArrayOf(0)
        var len = 0
        var p = value
        while (p > 0) { len++; p = p shr 8 }
        return ByteArray(len) { i -> ((value shr (8 * (len - 1 - i))) and 0xFF).toByte() }
    }

    private fun elem(id: Long, payload: ByteArray): ByteArray = idBytes(id) + sizeBytes(payload.size.toLong()) + payload
    private fun elem(id: Long, value: Long): ByteArray = elem(id, uintBytes(value))
    private fun elem(id: Long, ascii: String): ByteArray = elem(id, ascii.toByteArray(Charsets.US_ASCII))
    private fun elemUtf8(id: Long, text: String): ByteArray = elem(id, text.toByteArray(Charsets.UTF_8))
    private fun cat(vararg parts: ByteArray): ByteArray = parts.reduce { a, b -> a + b }
}
