package com.arflix.tv.data.api

import com.arflix.tv.ui.components.forFullHdTrailer
import androidx.media3.common.TrackSelectionParameters
import org.junit.Assert.*
import org.junit.Test

class TrailerSelectionTest {
    @Test fun prefersFullHdWithinOfficialTrailers() {
        val sd = video("sd", 360)
        val hd = video("hd", 1080)
        assertEquals(hd, preferredTrailer(listOf(sd, hd, video("teaser", 2160).copy(type = "Teaser"))))
    }

    @Test fun preservesOfficialAndLanguageResponsePriorityRatherThanPickingUnofficialHd() {
        val official = video("official", 720)
        assertEquals(official, preferredTrailer(listOf(video("unofficial", 1080).copy(official = false), official)))
    }

    @Test fun doesNotLoseTrailersWithoutResolutionMetadata() {
        assertEquals("unknown", preferredTrailer(listOf(video("unknown", 0)))?.key)
        assertNull(preferredTrailer(listOf(video("", 1080), video("other-site", 1080).copy(site = "Vimeo"))))
    }

    @Test fun fullHdAndFourKAreEquivalentSoOriginalOrderIsStable() {
        val hd = video("hd", 1080)
        assertEquals(hd, preferredTrailer(listOf(hd, video("4k", 2160))))
    }

    @Test fun smallViewportDoesNotRestrictNativePreviewButBandwidthCanStillAdapt() {
        val parameters = TrackSelectionParameters.DEFAULT_WITHOUT_CONTEXT.buildUpon()
            .setViewportSize(480, 270, true).build().forFullHdTrailer()
        assertEquals(1920, parameters.maxVideoWidth)
        assertEquals(1080, parameters.maxVideoHeight)
        assertEquals(Int.MAX_VALUE, parameters.viewportWidth)
        assertEquals(0, parameters.minVideoHeight)
        assertFalse(parameters.forceHighestSupportedBitrate)
        assertFalse(parameters.forceLowestBitrate)
    }

    @Test fun probesFullHdFromEveryClientBeforeFallingBackTo720p() {
        val options = listOf(candidate("vr", "vr-high", 1080, score = 9.0),
            candidate("vr", "vr-other", 1080, score = 8.0), candidate("android", "android", 1080),
            candidate("ios", "ios", 1080), candidate("vr", "hd", 720))
        val order = trailerVideoProbeOrder(options, 361)
        assertEquals(listOf("vr", "android", "ios", "vr"), order.map { it.client })
        assertEquals(listOf(1080, 1080, 1080, 720), order.map { it.height })
        assertEquals(4, order.size)
    }

    @Test fun aWorking720pManifestDoesNotBlockTryingFullHd() {
        val order = trailerVideoProbeOrder(listOf(candidate("vr", "full-hd", 1080),
            candidate("vr", "hd", 720)), minimumHeight = 721)
        assertEquals(listOf("full-hd"), order.map { it.url })
    }

    @Test fun avoidsThrottledAndLessCompatibleVariantsWithinTheSameClientAndResolution() {
        val order = trailerVideoProbeOrder(listOf(candidate("vr", "throttled", 1080).copy(hasN = true, score = 99.0),
            candidate("vr", "av1", 1080).copy(mimeType = "video/mp4; codecs=av01"),
            candidate("vr", "avc", 1080)), 720)
        assertEquals(listOf("avc"), order.map { it.url })
    }

    @Test fun neverPicksAVideoCodecTheDeviceCannotDecode() {
        // An unthrottled AV1 track outranks a throttled H.264 one; on a device without an AV1
        // decoder it would play as sound over a black picture, so it is never offered.
        val options = listOf(
            candidate("android", "av1", 1080).copy(mimeType = "video/mp4; codecs=\"av01.0.08M.08\""),
            candidate("android", "avc-throttled", 1080).copy(hasN = true, mimeType = "video/mp4; codecs=\"avc1.640028\"")
        )
        val noAv1 = { mime: String -> mime != "video/av01" }
        assertEquals(listOf("avc-throttled"), trailerVideoProbeOrder(options, 720, noAv1).map { it.url })
        assertEquals(listOf("av1"), trailerVideoProbeOrder(options, 720).map { it.url })
    }

    @Test fun readsTheDecoderAYouTubeFormatNeeds() {
        assertEquals("video/av01", youTubeVideoDecoderMime("video/mp4; codecs=\"av01.0.08M.08\""))
        assertEquals("video/avc", youTubeVideoDecoderMime("video/mp4; codecs=\"avc1.640028\""))
        assertEquals("video/x-vnd.on2.vp9", youTubeVideoDecoderMime("video/webm; codecs=\"vp9\""))
        assertNull(youTubeVideoDecoderMime("video/mp4"))
    }

    @Test fun doesNotProbeDuplicateUrlsSdOrFourKForAFullHdPreview() {
        val hd = candidate("vr", "same", 1080)
        assertEquals(listOf(hd), trailerVideoProbeOrder(listOf(hd, hd.copy(client = "ios"),
            candidate("vr", "sd", 360), candidate("vr", "4k", 2160)), 720))
    }

    private fun candidate(client: String, url: String, height: Int, score: Double = 1.0) = StreamCandidate(
        client, 0, url, score, false, "", height, 30, "mp4", "video/mp4; codecs=avc1"
    )

    private fun video(key: String, height: Int) =
        TmdbVideo(key = key, site = "YouTube", type = "Trailer", official = true, size = height)
}
