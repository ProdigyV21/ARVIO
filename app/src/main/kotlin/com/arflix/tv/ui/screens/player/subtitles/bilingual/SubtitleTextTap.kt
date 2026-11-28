package com.arflix.tv.ui.screens.player.subtitles.bilingual

import android.net.Uri
import androidx.annotation.OptIn
import androidx.media3.common.C
import androidx.media3.common.DataReader
import androidx.media3.common.Format
import androidx.media3.common.util.ParsableByteArray
import androidx.media3.common.util.UnstableApi
import androidx.media3.extractor.Extractor
import androidx.media3.extractor.ExtractorInput
import androidx.media3.extractor.ExtractorOutput
import androidx.media3.extractor.ExtractorsFactory
import androidx.media3.extractor.PositionHolder
import androidx.media3.extractor.SeekMap
import androidx.media3.extractor.SniffFailure
import androidx.media3.extractor.TrackOutput
import androidx.media3.extractor.text.SubtitleParser
import java.io.EOFException

/**
 * Wraps the player's [ExtractorsFactory] so every subtitle sample the extractor already demuxes is
 * also decoded to text into [store] — including the subtitle tracks the player did not select.
 *
 * Costs: a copy of each subtitle sample (a few hundred bytes) on the loader thread. Video and
 * audio samples pass straight through untouched. Never throws into the extractor.
 */
@OptIn(UnstableApi::class)
class SubtitleTextTapExtractorsFactory(
    private val delegate: ExtractorsFactory,
    private val store: EmbeddedSubtitleStore,
    private val currentSourceKey: () -> String?,
    private val enabledProvider: () -> Boolean = { true },
) : ExtractorsFactory {

    constructor(
        delegate: ExtractorsFactory,
        store: EmbeddedSubtitleStore,
        currentSourceKey: () -> String?,
    ) : this(delegate, store, currentSourceKey, { true })

    override fun createExtractors(): Array<Extractor> {
        val extractors = delegate.createExtractors()
        return if (enabledProvider()) wrap(extractors, null, store.currentGeneration()) else extractors
    }

    // Media3's progressive loader calls this overload with the URI it is actually reading: that
    // URI, not the UI's idea of the current stream, keys the cues (no cross-episode mixing).
    override fun createExtractors(uri: Uri, responseHeaders: Map<String, List<String>>): Array<Extractor> {
        val extractors = delegate.createExtractors(uri, responseHeaders)
        return if (enabledProvider()) wrap(extractors, uri.toString(), store.currentGeneration()) else extractors
    }

    // Forward configuration so the wrapped factory keeps behaving exactly as before.
    override fun experimentalSetTextTrackTranscodingEnabled(textTrackTranscodingEnabled: Boolean): ExtractorsFactory {
        delegate.experimentalSetTextTrackTranscodingEnabled(textTrackTranscodingEnabled)
        return this
    }

    override fun setSubtitleParserFactory(subtitleParserFactory: SubtitleParser.Factory): ExtractorsFactory {
        delegate.setSubtitleParserFactory(subtitleParserFactory)
        return this
    }

    override fun experimentalSetCodecsToParseWithinGopSampleDependencies(codecsToParseWithinGopSampleDependencies: Int): ExtractorsFactory {
        delegate.experimentalSetCodecsToParseWithinGopSampleDependencies(codecsToParseWithinGopSampleDependencies)
        return this
    }

    private fun wrap(extractors: Array<Extractor>, uriKey: String?, generation: Long): Array<Extractor> =
        Array(extractors.size) { i -> SubtitleTapExtractor(extractors[i], store, uriKey, generation, currentSourceKey) }
}

@OptIn(UnstableApi::class)
private class SubtitleTapExtractor(
    private val delegate: Extractor,
    private val store: EmbeddedSubtitleStore,
    private val uriKey: String?,
    private val generation: Long,
    private val currentSourceKey: () -> String?,
) : Extractor {
    override fun sniff(input: ExtractorInput): Boolean = delegate.sniff(input)
    override fun getSniffFailureDetails(): List<SniffFailure> = delegate.sniffFailureDetails
    override fun init(output: ExtractorOutput) {
        // Writes go to this extractor's own media; the player decides which media is read.
        val key = uriKey ?: runCatching { currentSourceKey() }.getOrNull()
        delegate.init(SubtitleTapExtractorOutput(output, store, key, generation))
    }
    override fun read(input: ExtractorInput, seekPosition: PositionHolder): Int = delegate.read(input, seekPosition)
    override fun seek(position: Long, timeUs: Long) = delegate.seek(position, timeUs)
    override fun release() = delegate.release()
    override fun getUnderlyingImplementation(): Extractor = delegate.underlyingImplementation
}

@OptIn(UnstableApi::class)
internal class SubtitleTapExtractorOutput(
    private val delegate: ExtractorOutput,
    private val store: EmbeddedSubtitleStore,
    private val sourceKey: String?,
    private val extractorGeneration: Long = -1L,
) : ExtractorOutput {
    private val outputs = HashMap<Int, TrackOutput>()
    private var hasAVTrack = false
    private var hasTextTrack = false

    override fun track(id: Int, type: Int): TrackOutput {
        val output = delegate.track(id, type)
        if (type == C.TRACK_TYPE_VIDEO || type == C.TRACK_TYPE_AUDIO) {
            hasAVTrack = true
            store.bindExtractorSource(extractorGeneration, sourceKey)
            return output
        }
        if (type != C.TRACK_TYPE_TEXT) return output
        hasTextTrack = true
        return outputs.getOrPut(id) { SubtitleTapTrackOutput(output, id, store, sourceKey) }
    }

    override fun endTracks() {
        if ((hasAVTrack || hasTextTrack) && sourceKey != null &&
            (hasAVTrack || !isLikelySidecarCacheUri(sourceKey))
        ) {
            store.bindExtractorSource(extractorGeneration, sourceKey)
        }
        delegate.endTracks()
    }

    override fun seekMap(seekMap: SeekMap) = delegate.seekMap(seekMap)

    private fun isLikelySidecarCacheUri(uri: String): Boolean {
        val lower = uri.lowercase()
        if ("/matched_subs/" in lower) return true
        val path = lower.substringBefore('?').substringBefore('#')
        return path.endsWith(".srt") || path.endsWith(".vtt") || path.endsWith(".ass") || path.endsWith(".ssa")
    }
}

/**
 * Tee for one text track. Extractors may write a sample in several parts and report its metadata
 * after (with [offset] bytes of later samples already written), so bytes are kept until
 * [sampleMetadata] identifies the sample — the same bookkeeping as the audio-sync tap.
 */
@OptIn(UnstableApi::class)
internal class SubtitleTapTrackOutput(
    private val delegate: TrackOutput,
    private val trackId: Int,
    private val store: EmbeddedSubtitleStore,
    private val sourceKey: String?,
) : TrackOutput {
    private var format: Format? = null
    private var pending = ByteArray(4_096)
    private var start = 0
    private var end = 0
    private var scratch = ByteArray(0)
    private val scratchReader = ParsableByteArray()
    private var decoder: SubtitleSampleDecoder? = null

    override fun format(format: Format) {
        this.format = format
        onFormatInfo(
            formatId = format.id,
            mimeType = format.sampleMimeType,
            codecs = format.codecs,
            language = format.language,
            label = format.label,
            forced = format.selectionFlags and C.SELECTION_FLAG_FORCED != 0,
        )
        delegate.format(format)
    }

    /** The part of [format] the tap needs (separate so it is testable without android.* classes). */
    internal fun onFormatInfo(formatId: String?, mimeType: String?, codecs: String?, language: String?, label: String?, forced: Boolean) {
        decoder = SubtitleSampleDecoder.forFormat(mimeType, codecs)
        runCatching {
            store.onTrack(
                sourceKey,
                EmbeddedTextTrack(
                    trackId = trackId,
                    formatId = formatId,
                    languageTag = language,
                    label = label,
                    mimeType = mimeType,
                    codecs = codecs,
                    isForced = forced,
                )
            )
        }
    }

    override fun durationUs(durationUs: Long) = delegate.durationUs(durationUs)

    override fun sampleData(input: DataReader, length: Int, allowEndOfInput: Boolean, sampleDataPart: Int): Int {
        if (decoder == null) return delegate.sampleData(input, length, allowEndOfInput, sampleDataPart)
        if (scratch.size < length) scratch = ByteArray(maxOf(length, 1_024))
        val read = input.read(scratch, 0, length)
        if (read == C.RESULT_END_OF_INPUT) {
            if (allowEndOfInput) return C.RESULT_END_OF_INPUT
            throw EOFException()
        }
        append(scratch, 0, read)
        scratchReader.reset(scratch, read)
        delegate.sampleData(scratchReader, read, sampleDataPart)
        return read
    }

    override fun sampleData(data: ParsableByteArray, length: Int, sampleDataPart: Int) {
        if (decoder != null) append(data.data, data.position, length)
        delegate.sampleData(data, length, sampleDataPart)
    }

    override fun sampleMetadata(timeUs: Long, flags: Int, size: Int, offset: Int, cryptoData: TrackOutput.CryptoData?) {
        val d = decoder
        if (d != null) {
            val sampleStart = end - offset - size
            if (size > 0 && sampleStart >= start && timeUs != C.TIME_UNSET && cryptoData == null) {
                try {
                    d.decode(timeUs, pending, sampleStart, size)?.let { cue ->
                        store.onCue(sourceKey, trackId, cue.startMs, cue.endMs, cue.text)
                    }
                } catch (_: Throwable) {
                    // A malformed subtitle sample must never disturb playback.
                }
            }
            start = maxOf(start, end - offset)
            if (start == end) { start = 0; end = 0 }
        }
        delegate.sampleMetadata(timeUs, flags, size, offset, cryptoData)
    }

    private fun append(data: ByteArray, offset: Int, length: Int) {
        if (length <= 0) return
        if (pending.size - end < length) {
            if (start > 0) {
                val retained = end - start
                pending.copyInto(pending, 0, start, end)
                start = 0
                end = retained
            }
            if (pending.size - end < length) {
                val required = end + length
                if (required > MAX_PENDING_BYTES) { start = 0; end = 0; return }
                pending = pending.copyOf(maxOf(required, pending.size * 2))
            }
        }
        data.copyInto(pending, end, offset, offset + length)
        end += length
    }

    private companion object {
        const val MAX_PENDING_BYTES = 2 * 1024 * 1024
    }
}
