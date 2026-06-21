package com.arflix.tv.data.api

import org.junit.Assert.assertEquals
import org.junit.Test

class YouTubeExtractorQualityTest {

    @Test
    fun `selectHighestQualityHlsVariant chooses highest resolution then bandwidth`() {
        val manifest = """
            #EXTM3U
            #EXT-X-STREAM-INF:BANDWIDTH=2500000,RESOLUTION=1280x720
            low/index.m3u8
            #EXT-X-STREAM-INF:BANDWIDTH=9000000,RESOLUTION=3840x2160
            4k-low/index.m3u8
            #EXT-X-STREAM-INF:BANDWIDTH=18000000,RESOLUTION=3840x2160
            4k-high/index.m3u8
            #EXT-X-STREAM-INF:BANDWIDTH=8000000,RESOLUTION=1920x1080
            1080/index.m3u8
        """.trimIndent()

        val selected = selectHighestQualityHlsVariant(
            manifestUrl = "https://example.com/master.m3u8",
            manifestBody = manifest
        )

        assertEquals("https://example.com/4k-high/index.m3u8", selected?.url)
        assertEquals(2160, selected?.height)
        assertEquals(18_000_000L, selected?.bandwidth)
    }
}
