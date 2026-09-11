package com.arflix.tv.data.repository

import org.junit.Assert.*
import org.junit.Test

class XtreamLiveStreamUrlTest {
    @Test fun declaredHlsDoesNotBecomeMpegTs() {
        val url = buildXtreamLiveStreamUrl("https://example.com", "user", "pass", 123, " M3U8 ")
        assertEquals("https://example.com/live/user/pass/123.m3u8", url)
        assertTrue(looksLikeHlsPlaybackUrl(url))
    }

    @Test fun missingOrInvalidContainerRetainsLegacyTsFallback() {
        listOf(null, "", "../../evil", "unknown").forEach { format ->
            assertEquals("https://example.com/live/user/pass/123.ts",
                buildXtreamLiveStreamUrl("https://example.com/", "user", "pass", 123, format))
        }
    }

    @Test fun credentialsRemainSinglePathSegments() {
        assertEquals("https://example.com/base/live/u%2Fx/p%3Fq/123.m3u8",
            buildXtreamLiveStreamUrl("https://example.com/base", "u/x", "p?q", 123, "m3u8"))
    }
}
