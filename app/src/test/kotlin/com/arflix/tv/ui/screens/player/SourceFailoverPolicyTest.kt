package com.arflix.tv.ui.screens.player

import com.arflix.tv.data.model.ProxyHeaders
import com.arflix.tv.data.model.StreamBehaviorHints
import com.arflix.tv.data.model.StreamSource
import org.junit.Assert.*
import org.junit.Test

class SourceFailoverPolicyTest {
    private fun source(
        id: String = "a", url: String = "https://cdn.example/video.mp4",
        headers: Map<String, String> = emptyMap()
    ) = StreamSource(id, id, id, "1080p", "2 GB", url = url,
        behaviorHints = StreamBehaviorHints(proxyHeaders = ProxyHeaders(request = headers)))

    @Test fun anAliasForThePlayingRequestIsSkipped() {
        val playing = source()
        assertFalse(canTryFailoverSource(source(id = "other-addon"), playing, playing.url, emptySet()))
    }

    @Test fun sameUrlWithAWorkingRefererRemainsEligible() {
        val playing = source(headers = mapOf("Referer" to "https://wrong.example"))
        val replacement = source(id = "b", headers = mapOf("Referer" to "https://correct.example"))
        assertTrue(canTryFailoverSource(replacement, playing, playing.url, emptySet()))
    }

    @Test fun sameUrlWithDifferentAuthorizationRemainsEligible() {
        val playing = source(headers = mapOf("Authorization" to "Bearer expired"))
        assertTrue(canTryFailoverSource(source(headers = mapOf("Authorization" to "Bearer valid")),
            playing, playing.url, emptySet()))
    }

    @Test fun headerNamesAreCaseInsensitiveButValuesAreNot() {
        assertEquals(playbackRequestIdentity(source(headers = mapOf("Referer" to "A"))),
            playbackRequestIdentity(source(headers = mapOf("referer" to "A"))))
        assertNotEquals(playbackRequestIdentity(source(headers = mapOf("Authorization" to "Token A"))),
            playbackRequestIdentity(source(headers = mapOf("authorization" to "Token a"))))
    }

    @Test fun whitespaceAndHeaderOrderDoNotCreateNewRequests() {
        assertEquals(playbackRequestIdentity(source(headers = linkedMapOf("Referer" to "A", "User-Agent" to "B"))),
            playbackRequestIdentity(source(headers = linkedMapOf(" user-agent " to " B ", " referer " to " A "))))
    }

    @Test fun unsafeHeadersAreIgnoredJustAsTheyAreForPlayback() {
        val headers = mapOf("Bad\r\nName" to "x", "Authorization" to "x\r\ny", "Referer" to "ok")
        assertEquals(mapOf("Referer" to "ok"), headers.safePlaybackHeaders())
        assertEquals(playbackRequestIdentity(source(headers = mapOf("Referer" to "ok"))),
            playbackRequestIdentity(source(headers = headers)))
    }

    @Test fun aPlaceholderAliasIsNotRetriedAfterSwitchingAway() {
        val placeholder = source(url = "https://addon.example/static/downloading.mp4")
        val rejected = setOf(playbackRequestIdentity(placeholder)!!)
        val playing = source(url = "https://other.example/movie.mkv")
        assertFalse(canTryFailoverSource(placeholder.copy(addonId = "alias"), playing, playing.url, rejected))
    }

    @Test fun aRejectedRequestDoesNotBlacklistDifferentAccessHeaders() {
        val placeholder = source(headers = mapOf("Authorization" to "Bearer old"))
        val rejected = setOf(playbackRequestIdentity(placeholder)!!)
        val playing = source(url = "https://other.example/movie.mkv")
        assertTrue(canTryFailoverSource(source(headers = mapOf("Authorization" to "Bearer new")),
            playing, playing.url, rejected))
    }

    @Test fun resolvedPlaybackUrlIsUsedInsteadOfTheOriginalResolver() {
        val listed = source(url = "https://addon.example/resolve/movie")
        val resolved = "https://cdn.example/movie.mkv"
        assertFalse(canTryFailoverSource(source(url = resolved), listed, resolved, emptySet()))
    }

    @Test fun anUnrelatedSourceIsStillAvailable() {
        val playing = source()
        assertTrue(canTryFailoverSource(source(url = "https://different.example/movie.mkv"),
            playing, playing.url, emptySet()))
    }

    @Test fun missingUrlsCannotBeSelected() {
        assertFalse(canTryFailoverSource(source().copy(url = null), null, null, emptySet()))
        assertNull(playbackRequestIdentity(null))
    }

    @Test fun identityDiagnosticsCannotExposeTokens() {
        val identity = playbackRequestIdentity(source(headers = mapOf("Authorization" to "Bearer private-token")))!!
        assertFalse(identity.toString().contains("private-token"))
        assertFalse(identity.toString().contains("cdn.example"))
    }

    @Test fun thePlayingRowIsNotConfusedWithAnEarlierHeaderDistinctAlternative() {
        val playing = source(headers = mapOf("Referer" to "A"))
        val alternative = source(id = "b", headers = mapOf("Referer" to "B"))
        assertEquals(1, playingRequestIndex(listOf(alternative, playing), playing, playing.url))
    }

    @Test fun reorderedListsAnchorOnThePlayingRequestRatherThanTheOldIndex() {
        val playing = source()
        val other = source(url = "https://other.example/movie.mkv")
        assertEquals(0, playingRequestIndex(listOf(playing, other), playing, playing.url))
        assertEquals(1, playingRequestIndex(listOf(other, playing), playing, playing.url))
        assertEquals(-1, playingRequestIndex(listOf(other), playing, playing.url))
    }

    @Test fun finishingAddonsDoesNotFinishPendingHomeServerLookup() {
        assertTrue(playerSourceLookupActive(primaryFinished = true, homeServerActive = true, iptvActive = false))
    }

    @Test fun finishingAddonsDoesNotFinishPendingIptvLookup() {
        assertTrue(playerSourceLookupActive(primaryFinished = true, homeServerActive = false, iptvActive = true))
    }

    @Test fun earlySupplementalResultsStillWaitForAddons() {
        assertTrue(playerSourceLookupActive(primaryFinished = false, homeServerActive = false, iptvActive = false))
    }

    @Test fun lookupOnlyFinishesAfterEveryProducerFinishes() {
        for (primary in listOf(false, true)) for (home in listOf(false, true)) for (iptv in listOf(false, true)) {
            assertEquals(!primary || home || iptv, playerSourceLookupActive(primary, home, iptv))
        }
    }
}
