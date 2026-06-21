package com.arflix.tv.data.stream

import com.arflix.tv.data.model.StreamBehaviorHints
import com.arflix.tv.data.model.StreamSource
import com.arflix.tv.data.model.Subtitle
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class StreamSourceAnalysisTest {
    @Test
    fun `analysis detects 4k dolby vision atmos and cached debrid`() {
        val analysis = analyzeStreamSource(
            stream(
                source = "Movie.2026.2160p.DV.HDR10.Atmos.TrueHD.REMUX.mkv",
                quality = "Unknown",
                size = "52 GB",
                cached = true,
                addonName = "AIOStreams - RealDebrid"
            )
        )

        assertEquals("4K", analysis.resolutionLabel)
        assertEquals(4, analysis.resolutionScore)
        assertEquals("REMUX", analysis.releaseLabel)
        assertEquals("DV", analysis.visualTag)
        assertEquals("Atmos", analysis.audioLabel)
        assertTrue(analysis.isCachedOrDebridReady)
        assertEquals(52L * 1024L * 1024L * 1024L, analysis.sizeBytes)
    }

    @Test
    fun `summary chooses best resolution then larger same tier source`() {
        val summary = summarizeStreamAvailability(
            listOf(
                stream("Movie 1080p WEB-DL 8 GB", "1080p", "8 GB"),
                stream("Movie 2160p WEB-DL 18 GB", "4K", "18 GB"),
                stream("Movie 2160p REMUX Atmos 52 GB", "4K", "52 GB")
            ),
            loadedAtMs = 123L
        )

        assertEquals("4K", summary?.bestResolution)
        assertEquals("Atmos", summary?.bestAudioTag)
        assertEquals("52 GB", summary?.maxSizeLabel)
        assertEquals(3, summary?.sourceCount)
        assertEquals(123L, summary?.loadedAtMs)
    }

    @Test
    fun `summary reports audio tag from the same best stream`() {
        val summary = summarizeStreamAvailability(
            listOf(
                stream("Movie 2160p WEB-DL HDR 18 GB", "4K", "18 GB"),
                stream("Movie 1080p WEB-DL Atmos 8 GB", "1080p", "8 GB")
            ),
            loadedAtMs = 234L
        )

        assertEquals("4K", summary?.bestResolution)
        assertEquals("HDR", summary?.bestVisualTag)
        assertNull(summary?.bestAudioTag)
    }

    @Test
    fun `shared best stream ranking prefers release tier before raw size within same resolution`() {
        val largeWeb = stream("Movie 2160p WEB-DL 52 GB", "4K", "52 GB")
        val remux = stream("Movie 2160p REMUX 35 GB", "4K", "35 GB")

        val best = bestStreamAvailabilityAnalysisForStreams(listOf(largeWeb, remux))

        assertEquals(remux, best?.stream)
        assertEquals("REMUX", best?.releaseLabel)
    }

    @Test
    fun `summary returns null for no streams`() {
        assertNull(summarizeStreamAvailability(emptyList(), loadedAtMs = 1L))
    }

    @Test
    fun `analysis detects hdr10 plus truehd channel and source count`() {
        val analysis = analyzeStreamSource(
            stream(
                source = "Movie.2160p.HDR10+.TrueHD.7.1.BluRay.mkv",
                quality = "4K",
                size = "5,5 GiB",
                sources = listOf("tracker-a", "tracker-b")
            )
        )

        assertEquals("HDR10+", analysis.visualTag)
        assertFalse(analysis.visualTags.contains("HDR10"))
        assertFalse(analysis.visualTags.contains("HDR"))
        assertTrue(analysis.chips.contains("HDR10+"))
        assertFalse(analysis.chips.contains("HDR10"))
        assertFalse(analysis.chips.contains("HDR"))
        assertEquals("TrueHD", analysis.audioLabel)
        assertEquals("2 sources", analysis.multiSourceLabel)
        assertEquals((5.5 * 1024.0 * 1024.0 * 1024.0).toLong(), analysis.sizeBytes)
    }

    @Test
    fun `analysis detects dd plus 5 point 1 and debrid text without cached hint`() {
        val analysis = analyzeStreamSource(
            stream(
                source = "Movie.1080p.WEB-DL.DD+.5.1.mkv [RD+]",
                quality = "1080p",
                size = "8 GB",
                cached = false,
                addonName = "AIOStreams"
            )
        )

        assertEquals("DD+", analysis.audioLabel)
        assertTrue(analysis.audioTags.contains("5.1"))
        assertTrue(analysis.isCachedOrDebridReady)
    }

    @Test
    fun `summary prefers cached source when quality release and size are tied`() {
        val uncached = stream(
            source = "Movie 1080p WEB-DL 8 GB",
            quality = "1080p",
            size = "8 GB",
            cached = false,
            addonName = "AIOStreams"
        )
        val cached = stream(
            source = "Movie 1080p WEB-DL 8 GB cached",
            quality = "1080p",
            size = "8 GB",
            cached = true,
            addonName = "RealDebrid"
        )

        val summary = summarizeStreamAvailability(listOf(uncached, cached), loadedAtMs = 456L)

        assertEquals("RealDebrid", summary?.bestSourceLabel)
        assertTrue(summary?.isCachedOrDebridReady == true)
    }

    @Test
    fun `summary reports readiness from the same best stream`() {
        val summary = summarizeStreamAvailability(
            listOf(
                stream(
                    source = "Movie 2160p WEB-DL 18 GB",
                    quality = "4K",
                    size = "18 GB",
                    cached = false,
                    addonName = "AIOStreams"
                ),
                stream(
                    source = "Movie 1080p WEB-DL 8 GB cached",
                    quality = "1080p",
                    size = "8 GB",
                    cached = true,
                    addonName = "RealDebrid"
                )
            ),
            loadedAtMs = 567L
        )

        assertEquals("4K", summary?.bestResolution)
        assertEquals("AIOStreams", summary?.bestSourceLabel)
        assertFalse(summary?.isCachedOrDebridReady == true)
    }

    @Test
    fun `analysis and summary expose swedish subtitle availability`() {
        val withSwedishSubtitle = stream(
            source = "Movie 1080p WEB-DL 8 GB",
            quality = "1080p",
            size = "8 GB",
            subtitles = listOf(
                Subtitle(
                    id = "sv-se",
                    url = "https://example.com/sub.srt",
                    lang = "sv",
                    label = "Svenska"
                )
            )
        )
        val analysis = analyzeStreamSource(withSwedishSubtitle)
        val summary = summarizeStreamAvailability(
            listOf(
                stream("Movie 2160p WEB-DL 18 GB", "4K", "18 GB"),
                withSwedishSubtitle
            ),
            loadedAtMs = 678L
        )

        assertTrue(analysis.hasSwedishSubtitles)
        assertTrue(analysis.chips.contains("SE subs"))
        assertTrue(summary?.hasSwedishSubtitles == true)
    }

    private fun stream(
        source: String,
        quality: String,
        size: String,
        cached: Boolean = false,
        addonName: String = "AIOStreams",
        sources: List<String> = emptyList(),
        subtitles: List<Subtitle> = emptyList()
    ) = StreamSource(
        source = source,
        addonName = addonName,
        addonId = "aiostreams",
        quality = quality,
        size = size,
        url = "https://example.com/${source.hashCode()}",
        behaviorHints = StreamBehaviorHints(cached = cached, filename = source),
        sources = sources,
        subtitles = subtitles
    )
}
