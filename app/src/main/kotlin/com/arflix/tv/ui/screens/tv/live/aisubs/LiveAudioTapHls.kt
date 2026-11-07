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

/**
 * HLS counterpart of [LiveTapExtractorsFactory]: HLS segments are not read through an
 * `ExtractorsFactory`, so the audio copy is made here, around whatever [delegate] returns. Hooking
 * the chunk extractor's output covers TS segments (ARVIO's reused TS extractor) and every other
 * segment type (packed audio, fMP4) in one place.
 *
 * Samples reach the sink with the timestamps the extractor wrote, after the HLS timestamp
 * adjuster, i.e. on the period timeline the player's position is measured in.
 */
internal class AudioTapHlsExtractorFactory(
    private val delegate: HlsExtractorFactory,
    private val sinks: LiveTapSinks,
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
        return AudioTapHlsChunkExtractor(extractor, sinks)
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
    private val sinks: LiveTapSinks,
) : HlsMediaChunkExtractor {
    override fun init(extractorOutput: ExtractorOutput) =
        delegate.init(LiveTapExtractorOutput(extractorOutput, sinks))

    override fun read(extractorInput: ExtractorInput): Boolean = delegate.read(extractorInput)

    override fun isPackedAudioExtractor(): Boolean = delegate.isPackedAudioExtractor

    override fun isReusable(): Boolean = delegate.isReusable

    override fun recreate(): HlsMediaChunkExtractor = AudioTapHlsChunkExtractor(delegate.recreate(), sinks)

    override fun onTruncatedSegmentParsed() {
        // The extractor restarts its state; the next samples may not continue the previous ones.
        sinks.audio.onDiscontinuity()
        sinks.captions.onDiscontinuity()
        delegate.onTruncatedSegmentParsed()
    }
}
