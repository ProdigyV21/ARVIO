package com.arflix.tv.data.repository

import android.app.Application
import com.arflix.tv.data.api.*
import com.arflix.tv.data.model.*
import io.mockk.*
import kotlinx.coroutines.*
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import org.robolectric.annotation.ConscryptMode

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28], application = Application::class, manifest = Config.NONE)
@ConscryptMode(ConscryptMode.Mode.OFF)
class NativeAddonSearchTest {
    private fun repository(api: StreamApi): StreamRepository = StreamRepository(
        RuntimeEnvironment.getApplication(), api,
        mockk(relaxed = true), mockk(relaxed = true), mockk(relaxed = true), mockk(relaxed = true),
        mockk(relaxed = true), mockk(relaxed = true), mockk(relaxed = true), mockk(relaxed = true),
        mockk(relaxed = true))

    @Test fun nativeSearchRetainsFastResultsAndEpisodePlaybackUsesExactVideoId() = runBlocking {
        val api = mockk<StreamApi>()
        val repository = repository(api)
        val manifest = AddonManifest("provider", "Provider", "1", resources = listOf(
            AddonResource("meta", listOf("series"), listOf("provider:")),
            AddonResource("stream", listOf("series"), listOf("provider:"))), catalogs = listOf(
            AddonCatalog("series", "fast", extra = listOf(AddonCatalogExtra("search"))),
            AddonCatalog("series", "slow", extra = listOf(AddonCatalogExtra("search")))))
        val addon = Addon("provider", "Provider", "1", "", true, type = AddonType.COMMUNITY,
            url = "https://provider.test/manifest.json", manifest = manifest)
        repository.replaceSharedAddonsFromCloud(listOf(addon))
        coEvery { api.getAddonCatalog(match { it.contains("/fast/") }) } returns
            StremioCatalogResponse(metas = listOf(StremioMetaPreview(id = "provider:1", name = "Result")))
        coEvery { api.getAddonCatalog(match { it.contains("/slow/") }) } coAnswers { awaitCancellation() }
        val pages = withTimeout(10_000) { repository.searchNativeAddonCatalogs("result", timeoutMs = 1_000) }
        assertEquals(listOf("fast"), pages.map { it.catalogId })
        assertEquals("provider:1", pages.single().metas.single().id)
        assertTrue(repository.addonServesOwnMeta(addon.id, "series", "provider:show"))
        coEvery { api.getAddonMeta(any()) } returns StremioMetaResponse(StremioMetaPreview(
            id = "provider:show", videos = listOf(StremioMetaVideo(
                id = "provider:episode/abc?part=2", season = 2, episode = 3))))
        coEvery { api.getAddonStreams(any()) } returns StremioStreamResponse(listOf(
            StremioStream(url = "https://provider.test/video.m3u8")))
        repository.getAddonMeta(addon.id, "series", "provider:show")
        val streams = repository.resolveAddonStreams(addon, MediaType.TV, "provider:show", season = 2, episode = 3)
        assertFalse(streams.isEmpty())
        coVerify(exactly = 1) { api.getAddonMeta(any()) }
        coVerify(exactly = 1) {
            api.getAddonStreams("https://provider.test/stream/series/provider%3Aepisode%2Fabc%3Fpart%3D2.json")
        }
        coVerify(exactly = 0) { api.getAddonStreams(match { it.contains("show:2:3") }) }
    }
}
