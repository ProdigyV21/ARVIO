package com.arflix.tv.data.repository

import com.arflix.tv.data.model.ProxyHeaders
import com.arflix.tv.data.model.StreamBehaviorHints
import com.arflix.tv.data.model.StreamSource
import org.junit.Assert.*
import org.junit.Test

class PlaybackRequestCacheKeyTest {
    private fun source(headers: Map<String, String> = emptyMap()) = StreamSource(
        source = "Movie", addonName = "Addon", addonId = "addon", quality = "1080p", size = "2 GB",
        url = "https://cdn.example/movie.mkv",
        behaviorHints = StreamBehaviorHints(proxyHeaders = ProxyHeaders(request = headers))
    )

    @Test fun headerFreeRequestsKeepTheirExistingCacheKey() {
        assertEquals("addon|Movie|https://cdn.example/movie.mkv", resolvedPlaybackRequestCacheKey(source()))
    }

    @Test fun differentRefererCannotReuseAnEarlierResolution() {
        assertNotEquals(resolvedPlaybackRequestCacheKey(source(mapOf("Referer" to "A"))),
            resolvedPlaybackRequestCacheKey(source(mapOf("Referer" to "B"))))
    }

    @Test fun renewedAuthorizationCannotReuseExpiredCredentials() {
        assertNotEquals(resolvedPlaybackRequestCacheKey(source(mapOf("Authorization" to "Bearer expired"))),
            resolvedPlaybackRequestCacheKey(source(mapOf("Authorization" to "Bearer renewed"))))
    }

    @Test fun addingOrRemovingHeadersAlsoChangesTheKey() {
        assertNotEquals(resolvedPlaybackRequestCacheKey(source()),
            resolvedPlaybackRequestCacheKey(source(mapOf("User-Agent" to "OtherPlayer"))))
    }

    @Test fun equivalentHeaderNamesWhitespaceAndOrderKeepCacheHits() {
        assertEquals(resolvedPlaybackRequestCacheKey(source(linkedMapOf("Referer" to "A", "User-Agent" to "B"))),
            resolvedPlaybackRequestCacheKey(source(linkedMapOf(" user-agent " to " B ", " referer " to " A "))))
    }

    @Test fun inlineAccessHeadersAreNotStrippedOutOfCacheIdentity() {
        assertNotEquals(resolvedPlaybackRequestCacheKey(source().copy(url = "https://cdn.example/movie.mkv|Referer=A")),
            resolvedPlaybackRequestCacheKey(source().copy(url = "https://cdn.example/movie.mkv|Referer=B")))
    }

    @Test fun torrentResolutionsAlsoDistinguishRequestHeaders() {
        val first = source(mapOf("Authorization" to "old")).copy(infoHash = "ABC", fileIdx = 2)
        val second = source(mapOf("Authorization" to "new")).copy(infoHash = "abc", fileIdx = 2)
        assertNotEquals(resolvedPlaybackRequestCacheKey(first), resolvedPlaybackRequestCacheKey(second))
        assertEquals("ih:addon|abc:2", resolvedPlaybackRequestCacheKey(source().copy(infoHash = "ABC", fileIdx = 2)))
    }

    @Test fun headerValuesDoNotAppearInTheCacheKey() {
        val key = resolvedPlaybackRequestCacheKey(source(mapOf("Authorization" to "Bearer private-secret")))
        assertFalse(key.contains("private-secret"))
        assertEquals(64, key.substringAfter("|headers:").length)
    }
}
