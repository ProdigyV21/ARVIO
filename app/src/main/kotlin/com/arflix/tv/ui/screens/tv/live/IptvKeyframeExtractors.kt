package com.arflix.tv.ui.screens.tv.live

import androidx.media3.exoplayer.hls.DefaultHlsExtractorFactory
import androidx.media3.exoplayer.hls.HlsExtractorFactory
import androidx.media3.extractor.DefaultExtractorsFactory
import androidx.media3.extractor.ExtractorsFactory
import androidx.media3.extractor.ts.DefaultTsPayloadReaderFactory

/*
 * Many broadcast IPTV feeds (re-encoded DVB channels) never send an IDR frame: every keyframe
 * is a plain I-frame. ExoPlayer only starts video on an IDR by default, so such a channel
 * buffers audio until the buffer is full and then stalls ("stuck buffering"). VLC-based apps
 * start on any I-frame, which is why the same channels play there.
 */

/** HLS extractors for IPTV that also start video on a non-IDR I-frame. */
internal fun iptvHlsExtractorFactory(): HlsExtractorFactory =
    DefaultHlsExtractorFactory(
        DefaultTsPayloadReaderFactory.FLAG_ALLOW_NON_IDR_KEYFRAMES,
        /* exposeCea608WhenMissingDeclarations= */ true
    )

/** Progressive (raw MPEG-TS) extractors for IPTV that also start video on a non-IDR I-frame. */
internal fun iptvExtractorsFactory(): ExtractorsFactory =
    DefaultExtractorsFactory()
        .setTsExtractorFlags(DefaultTsPayloadReaderFactory.FLAG_ALLOW_NON_IDR_KEYFRAMES)
