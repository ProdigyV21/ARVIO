package com.arflix.tv.ui.screens.details

import com.arflix.tv.data.model.StreamBehaviorHints
import com.arflix.tv.data.model.StreamSource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class AutoPlaySourcePlannerTest {
    @Test
    fun `best autoplay prefers 4k over small 720p even when sizeBytes hint is wrong`() {
        val small720 = stream(
            source = "Movie 720p WEB-DL",
            quality = "720p",
            size = "780 MB",
            sizeBytes = 100L * 1024L * 1024L * 1024L
        )
        val large4k = stream(
            source = "Movie 2160p 4K WEB-DL",
            quality = "Unknown",
            size = "18.4 GB",
            sizeBytes = 1L
        )

        val selected = bestAutoPlayStream(listOf(small720, large4k), minQualityScore = 0)

        assertEquals(large4k, selected)
    }

    @Test
    fun `best autoplay uses visible size string between same quality sources`() {
        val smaller4k = stream(source = "Movie 2160p", quality = "4K", size = "8 GB")
        val bigger4k = stream(source = "Movie 2160p REMUX", quality = "4K", size = "52 GB")

        val selected = bestAutoPlayStream(listOf(smaller4k, bigger4k), minQualityScore = 0)

        assertEquals(bigger4k, selected)
    }

    @Test
    fun `top-tier 4k source plays after a brief settle without waiting on slow addons`() {
        val top = stream(source = "Movie 2160p REMUX", quality = "4K", size = "52 GB")

        // Still settling: hold briefly so a bigger 4K can arrive, even while addons load.
        assertTrue(
            shouldWaitForAutoPlaySources(
                isLoadingStreams = true,
                selectedStream = top,
                elapsedMs = AUTOPLAY_TOP_TIER_SETTLE_MS - 1
            )
        )
        // Past the settle window: play the 4K now; do not wait on slow addons.
        assertFalse(
            shouldWaitForAutoPlaySources(
                isLoadingStreams = true,
                selectedStream = top,
                elapsedMs = AUTOPLAY_TOP_TIER_SETTLE_MS
            )
        )
    }

    @Test
    fun `sub-4k candidate waits for all addons then plays the best`() {
        val weak = stream(source = "Movie 720p WEB-DL", quality = "720p", size = "780 MB")

        // Addons still loading → keep collecting in case a better source arrives.
        assertTrue(
            shouldWaitForAutoPlaySources(
                isLoadingStreams = true,
                selectedStream = weak,
                elapsedMs = 100
            )
        )
        // All addons reported → play the best found now, don't dawdle.
        assertFalse(
            shouldWaitForAutoPlaySources(
                isLoadingStreams = false,
                selectedStream = weak,
                elapsedMs = 100
            )
        )
    }

    @Test
    fun `autoplay never waits past the 2s ceiling`() {
        val weak = stream(source = "Movie 720p WEB-DL", quality = "720p", size = "780 MB")

        assertFalse(
            shouldWaitForAutoPlaySources(
                isLoadingStreams = true,
                selectedStream = weak,
                elapsedMs = AUTOPLAY_MAX_WAIT_MS
            )
        )
    }

    @Test
    fun `autoplay waits for the first source while addons load, then gives up at ceiling`() {
        assertTrue(
            shouldWaitForAutoPlaySources(isLoadingStreams = true, selectedStream = null, elapsedMs = 100)
        )
        assertFalse(
            shouldWaitForAutoPlaySources(isLoadingStreams = false, selectedStream = null, elapsedMs = 100)
        )
    }

    @Test
    fun `best autoplay accepts a notWebReady 4k over a webReady 1080p`() {
        val webReady1080 = stream(
            source = "Movie 1080p WEB-DL",
            quality = "1080p",
            size = "4 GB",
            notWebReady = false
        )
        val notWebReady4k = stream(
            source = "Movie 2160p MKV",
            quality = "4K",
            size = "20 GB",
            notWebReady = true
        )

        val selected = bestAutoPlayStream(listOf(webReady1080, notWebReady4k), minQualityScore = 0)

        assertEquals(notWebReady4k, selected)
    }

    private fun stream(
        source: String,
        quality: String,
        size: String,
        sizeBytes: Long? = null,
        cached: Boolean = true,
        notWebReady: Boolean = false
    ) = StreamSource(
        source = source,
        addonName = "Torrentio",
        addonId = "torrentio",
        quality = quality,
        size = size,
        sizeBytes = sizeBytes,
        url = "https://example.com/${source.hashCode()}",
        behaviorHints = StreamBehaviorHints(cached = cached, notWebReady = notWebReady)
    )
}
