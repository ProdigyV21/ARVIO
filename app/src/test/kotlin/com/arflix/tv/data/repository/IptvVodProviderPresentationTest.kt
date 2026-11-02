package com.arflix.tv.data.repository

import com.arflix.tv.data.model.StreamSource
import com.arflix.tv.data.model.IptvVodSourceIds
import com.arflix.tv.data.model.StreamBehaviorHints
import com.arflix.tv.ui.components.sourceAttributionLabels
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class IptvVodProviderPresentationTest {
    @Test fun `baseline source retains its actual resolution`() {
        assertEquals("1080p", presentationField(source(), "resolutionLabel"))
    }

    @Test fun `provider name must not override actual resolution`() {
        val repository = repository()
        val named = with(repository) { source().withIptvProvider("Provider 4K") }
        assertEquals("1080p", presentationField(named, "resolutionLabel"))
    }

    @Test fun `provider name must not invent a codec`() {
        val repository = repository()
        val named = with(repository) { source().withIptvProvider("HEVC IPTV") }
        assertEquals(null, presentationField(named, "codecLabel"))
    }

    @Test fun `names stay visible without becoming technical metadata for either provider kind`() {
        val repository = repository()
        for (id in IptvVodSourceIds.ALL) {
            val named = with(repository) { source().copy(addonId = id).withIptvProvider("Provider 4K HEVC HDR Atmos") }
            assertEquals(listOf("Provider 4K HEVC HDR Atmos"), sourceAttributionLabels(named, "IPTV VOD"))
            assertEquals("1080p", presentationField(named, "resolutionLabel"))
            assertEquals(null, presentationField(named, "codecLabel"))
            assertEquals(null, presentationField(named, "audioLabel"))
            assertEquals(false, (presentationField(named, "chips") as List<*>).contains("HDR"))
        }
    }

    @Test fun `debrid in playlist name does not change transport or readiness`() {
        val repository = repository()
        for (id in IptvVodSourceIds.ALL) {
            val named = with(repository) { source().copy(addonId = id).withIptvProvider("My Debrid Backup") }
            assertEquals("VOD", presentationField(named, "transportLabel"))
            assertEquals(false, presentationField(named, "sortCached"))
            val check = Class.forName("com.arflix.tv.ui.components.StreamSelectorKt")
                .getDeclaredMethod("isDebridLikeSource", StreamSource::class.java, String::class.java)
                .apply { isAccessible = true }
            assertEquals(false, check.invoke(null, named, ""))
        }
    }

    @Test fun `non IPTV provider metadata keeps existing parsing`() {
        val addon = source().copy(addonId = "example_addon", addonName = "Example Addon",
            behaviorHints = StreamBehaviorHints(provider = "Provider 4K HEVC"))
        assertEquals("4K", presentationField(addon, "resolutionLabel"))
        assertEquals("HEVC", presentationField(addon, "codecLabel"))
        assertEquals(listOf("Provider"), sourceAttributionLabels(addon, "Example Addon"))
    }

    @Test fun `real stream technical metadata still renders for IPTV`() {
        val repository = repository()
        val named = with(repository) {
            source().copy(source = "Example Movie 4K HEVC HDR Atmos", quality = "4K").withIptvProvider("Provider")
        }
        assertEquals("4K", presentationField(named, "resolutionLabel"))
        assertEquals("HEVC", presentationField(named, "codecLabel"))
        assertEquals("Atmos", presentationField(named, "audioLabel"))
        assertEquals(true, (presentationField(named, "chips") as List<*>).contains("HDR"))
    }

    private fun presentationField(source: StreamSource, field: String): Any? {
        val presenter = Class.forName("com.arflix.tv.ui.components.StreamSelectorKt")
            .getDeclaredMethod(
                "presentSource", StreamSource::class.java, String::class.java, String::class.java, String::class.java
            )
            .apply { isAccessible = true }
        val result = presenter.invoke(null, source, "Unknown", null, "")!!
        return result.javaClass.getDeclaredField(field).apply { isAccessible = true }.get(result)
    }

    private fun source() = StreamSource(
        source = "Example Movie 1080p",
        addonName = "IPTV VOD",
        addonId = "iptv_xtream_vod",
        quality = "1080p",
        size = "",
        url = "https://example.invalid/movie/u/p/1.mp4"
    )

    private fun repository() = IptvRepository(
        mockk(relaxed = true), mockk(relaxed = true),
        mockk(relaxed = true), mockk(relaxed = true)
    )
}
