@file:androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)

package com.arflix.tv.ui.screens.tv.live.aisubs

import android.net.Uri
import androidx.media3.common.C
import androidx.media3.extractor.Extractor
import androidx.media3.extractor.ExtractorInput
import androidx.media3.extractor.ExtractorOutput
import androidx.media3.extractor.ExtractorsFactory
import androidx.media3.extractor.PositionHolder
import androidx.media3.extractor.SeekMap
import androidx.media3.extractor.SniffFailure
import androidx.media3.extractor.TrackOutput
import com.arflix.tv.ui.screens.player.audiosync.AudioSampleSink
import com.arflix.tv.ui.screens.player.audiosync.AudioTapTrackOutput

/** The two copies Live TV AI subtitles take of a channel as it downloads. */
internal class LiveTapSinks(val audio: AudioSampleSink, val captions: AudioSampleSink)

/**
 * Copies audio samples to [sinks].audio and text samples (closed captions carried in the video,
 * subtitle streams) to [sinks].captions, while forwarding everything unchanged to the player.
 * The copy happens at load time, so both arrive as far ahead of playback as the player buffers.
 */
internal class LiveTapExtractorOutput(
    private val delegate: ExtractorOutput,
    private val sinks: LiveTapSinks,
) : ExtractorOutput {
    private val outputs = HashMap<Int, TrackOutput>()

    override fun track(id: Int, type: Int): TrackOutput {
        val output = delegate.track(id, type)
        return when (type) {
            C.TRACK_TYPE_AUDIO -> outputs.getOrPut(id) { AudioTapTrackOutput(output, sinks.audio) }
            C.TRACK_TYPE_TEXT -> outputs.getOrPut(id) { AudioTapTrackOutput(output, sinks.captions) }
            else -> output
        }
    }

    override fun endTracks() = delegate.endTracks()

    override fun seekMap(seekMap: SeekMap) = delegate.seekMap(seekMap)
}

/** Progressive (raw MPEG-TS) streams: every extractor's output goes through [LiveTapExtractorOutput]. */
internal class LiveTapExtractorsFactory(
    private val delegate: ExtractorsFactory,
    private val sinks: LiveTapSinks,
) : ExtractorsFactory {
    override fun createExtractors(): Array<Extractor> = wrap(delegate.createExtractors())

    override fun createExtractors(uri: Uri, responseHeaders: Map<String, List<String>>): Array<Extractor> =
        wrap(delegate.createExtractors(uri, responseHeaders))

    private fun wrap(extractors: Array<Extractor>): Array<Extractor> =
        Array(extractors.size) { LiveTapExtractor(extractors[it], sinks) }
}

private class LiveTapExtractor(
    private val delegate: Extractor,
    private val sinks: LiveTapSinks,
) : Extractor {
    override fun sniff(input: ExtractorInput): Boolean = delegate.sniff(input)

    override fun getSniffFailureDetails(): List<SniffFailure> = delegate.sniffFailureDetails

    override fun init(output: ExtractorOutput) = delegate.init(LiveTapExtractorOutput(output, sinks))

    override fun read(input: ExtractorInput, seekPosition: PositionHolder): Int = delegate.read(input, seekPosition)

    override fun seek(position: Long, timeUs: Long) {
        sinks.audio.onDiscontinuity()
        sinks.captions.onDiscontinuity()
        delegate.seek(position, timeUs)
    }

    override fun release() = delegate.release()

    override fun getUnderlyingImplementation(): Extractor = delegate.underlyingImplementation
}
