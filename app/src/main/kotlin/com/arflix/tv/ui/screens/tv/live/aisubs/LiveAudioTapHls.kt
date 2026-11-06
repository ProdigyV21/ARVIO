@file:androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)

package com.arflix.tv.ui.screens.tv.live.aisubs

import android.net.Uri
import androidx.media3.common.Format
import androidx.media3.common.util.TimestampAdjuster
import androidx.media3.exoplayer.analytics.PlayerId
import androidx.media3.exoplayer.hls.HlsExtractorFactory
import androidx.media3.exoplayer.hls.HlsMediaChunkExtractor
import androidx.media3.extractor.ExtractorInput
import androidx.media3.extractor.ExtractorOutput
import androidx.media3.extractor.text.SubtitleParser
import com.arflix.tv.ui.screens.player.audiosync.AudioSampleSink
import com.arflix.tv.ui.screens.player.audiosync.AudioTapExtractorOutput

/**
 * HLS counterpart of `AudioSyncExtractorsFactory`: HLS segments are not read through an
 * `ExtractorsFactory`, so the audio copy is made here, around whatever [delegate] returns. Hooking
 * the chunk extractor's output covers TS segments (ARVIO's reused TS extractor) and every other
 * segment type (packed audio, fMP4) in one place.
 *
 * Samples reach the sink with the timestamps the extractor wrote, after the HLS timestamp
 * adjuster, i.e. on the period timeline the player's position is measured in.
 */
internal class AudioTapHlsExtractorFactory(
    private val delegate: HlsExtractorFactory,
    private val sink: AudioSampleSink,
) : HlsExtractorFactory {
    override fun createExtractor(
        uri: Uri,
        format: Format,
        muxedCaptionFormats: List<Format>?,
        timestampAdjuster: TimestampAdjuster,
        responseHeaders: Map<String, List<String>>,
        sniffingExtractorInput: ExtractorInput,
        playerId: PlayerId,
    ): HlsMediaChunkExtractor {
        val extractor = delegate.createExtractor(
            uri, format, muxedCaptionFormats, timestampAdjuster, responseHeaders, sniffingExtractorInput, playerId,
        )
        if (LiveAiSubsDebug.enabled) {
            LiveAiSubsLog.i(
                "hls chunk extractor ${extractor.javaClass.simpleName} packedAudio=${extractor.isPackedAudioExtractor} " +
                    "codecs=${format.codecs} mime=${format.sampleMimeType ?: format.containerMimeType}",
            )
        }
        return AudioTapHlsChunkExtractor(extractor, sink)
    }

    override fun setSubtitleParserFactory(subtitleParserFactory: SubtitleParser.Factory): HlsExtractorFactory {
        delegate.setSubtitleParserFactory(subtitleParserFactory)
        return this
    }

    override fun experimentalParseSubtitlesDuringExtraction(parseSubtitlesDuringExtraction: Boolean): HlsExtractorFactory {
        delegate.experimentalParseSubtitlesDuringExtraction(parseSubtitlesDuringExtraction)
        return this
    }

    override fun experimentalSetCodecsToParseWithinGopSampleDependencies(
        codecsToParseWithinGopSampleDependencies: Int,
    ): HlsExtractorFactory {
        delegate.experimentalSetCodecsToParseWithinGopSampleDependencies(codecsToParseWithinGopSampleDependencies)
        return this
    }

    override fun getOutputTextFormat(sourceFormat: Format): Format = delegate.getOutputTextFormat(sourceFormat)
}

private class AudioTapHlsChunkExtractor(
    private val delegate: HlsMediaChunkExtractor,
    private val sink: AudioSampleSink,
) : HlsMediaChunkExtractor {
    override fun init(extractorOutput: ExtractorOutput) =
        delegate.init(AudioTapExtractorOutput(TrackLoggingOutput(extractorOutput), sink))

    override fun read(extractorInput: ExtractorInput): Boolean = delegate.read(extractorInput)

    override fun isPackedAudioExtractor(): Boolean = delegate.isPackedAudioExtractor

    override fun isReusable(): Boolean = delegate.isReusable

    override fun recreate(): HlsMediaChunkExtractor = AudioTapHlsChunkExtractor(delegate.recreate(), sink)

    override fun onTruncatedSegmentParsed() {
        // The extractor restarts its state; the next samples may not continue the previous ones.
        sink.onDiscontinuity()
        delegate.onTruncatedSegmentParsed()
    }
}

/** Phase 0 diagnostics: logs which tracks a segment declares, to explain channels with no tapped audio. */
private class TrackLoggingOutput(private val delegate: ExtractorOutput) : ExtractorOutput by delegate {
    override fun track(id: Int, type: Int): androidx.media3.extractor.TrackOutput {
        if (LiveAiSubsDebug.enabled) LiveAiSubsLog.i("hls segment track id=$id type=$type")
        return delegate.track(id, type)
    }
}
