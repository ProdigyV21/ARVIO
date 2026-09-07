package com.arflix.tv.ui.components

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class YouTubeTrailerUrlTest {
    @Test fun createsOnlyAnOfficialWatchPageUrl() {
        assertEquals("https://www.youtube.com/watch?v=Ab_cd-Ef123", youtubeTrailerUrl("Ab_cd-Ef123"))
    }

    @Test fun rejectsMalformedKeysAndUrlInjection() {
        listOf("", "abc", "https://example.com", "abcdefghijk&x=1", "abcdefghij/", "abcdefghij<")
            .forEach { assertNull(it, youtubeTrailerUrl(it)) }
    }
}
