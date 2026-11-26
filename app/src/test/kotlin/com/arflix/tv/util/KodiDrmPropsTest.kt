package com.arflix.tv.util

import org.junit.Assert.*
import org.junit.Test

class KodiDrmPropsTest {
    private val kid = "e4cce7421f2843fe94915343372ba185"
    private val key = "a55effc1eb5ccf1af1c4f09abbb1cd39"

    @Test fun `legacy clearkey line gives scheme and kid key pair`() {
        assertEquals(mapOf("license_type" to "org.w3.clearkey", "license_key" to "$kid:$key"),
            KodiDrmProps.parse("inputstream.adaptive.drm_legacy=org.w3.clearkey|$kid:$key"))
    }

    @Test fun `legacy widevine line keeps the license url`() {
        assertEquals("https://lic.example/wv",
            KodiDrmProps.parse("inputstream.adaptive.drm_legacy=com.widevine.alpha|https://lic.example/wv")?.get("license_key"))
    }

    @Test fun `json clearkey line gives the first key pair`() {
        val line = """inputstream.adaptive.drm={"org.w3.clearkey":{"license":{"keyids":{"$kid":"$key"}}}}"""
        assertEquals(mapOf("license_type" to "org.w3.clearkey", "license_key" to "$kid:$key"), KodiDrmProps.parse(line))
    }

    @Test fun `json widevine line gives the server url`() {
        val line = """inputstream.adaptive.drm={"com.widevine.alpha":{"license":{"server_url":"https://lic.example/wv"}}}"""
        assertEquals("https://lic.example/wv", KodiDrmProps.parse(line)?.get("license_key"))
    }

    @Test fun `unreadable or unrelated lines are ignored`() {
        assertNull(KodiDrmProps.parse("inputstream.adaptive.drm={not json"))
        assertNull(KodiDrmProps.parse("inputstream.adaptive.license_type=clearkey"))
    }
}
