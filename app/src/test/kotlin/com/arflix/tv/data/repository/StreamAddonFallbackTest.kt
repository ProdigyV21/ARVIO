package com.arflix.tv.data.repository

import org.junit.Assert.assertEquals
import org.junit.Test

class StreamAddonFallbackTest {

    @Test
    fun constants_verifyPrimaryAndFallbackUrlsAreConfigured() {
        assertEquals(
            "https://raw.githubusercontent.com/ProdigyV21/ARVIO/main/config/streaming_addon.txt",
            MediaRepository.STREAMING_COLLECTION_ADDON_URL
        )
        assertEquals(
            "https://pastebin.com/raw/P4gfd98n",
            MediaRepository.PASTEBIN_STREAMING_ADDON_URL
        )
    }
}
