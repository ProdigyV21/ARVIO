package com.arflix.tv.data.repository

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import com.arflix.tv.data.api.*
import com.arflix.tv.data.model.*
import io.mockk.*
import kotlinx.coroutines.*
import com.arflix.tv.util.settingsDataStore
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import org.robolectric.annotation.ConscryptMode

/** Native items of addons that declare only their own types ("tv", "Podcasts"), no movie/series. */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28], application = Application::class, manifest = Config.NONE)
@ConscryptMode(ConscryptMode.Mode.OFF)
class NativeAddonTypeStreamTest {
    // Robolectric gives every test a new data dir, but the app's DataStores are process-wide;
    // an in-memory store keeps each test independent (as in StreamRepositoryAddonMutationTest).
    @Before fun inMemoryDataStores() {
        val state = MutableStateFlow(emptyPreferences())
        val store = object : DataStore<Preferences> {
            override val data = state
            override suspend fun updateData(transform: suspend (Preferences) -> Preferences): Preferences =
                transform(state.value).also { state.value = it }
        }
        mockkStatic("com.arflix.tv.data.repository.StreamRepositoryKt")
        mockkStatic("com.arflix.tv.util.DataStoresKt")
        every { any<Context>().streamDataStore } returns store
        every { any<Context>().settingsDataStore } returns store
    }

    @After fun restoreDataStores() {
        unmockkStatic("com.arflix.tv.data.repository.StreamRepositoryKt")
        unmockkStatic("com.arflix.tv.util.DataStoresKt")
    }

    private fun repository(api: StreamApi, addon: Addon): StreamRepository {
        val integrations = mockk<StreamIntegrationRepository>(relaxed = true)
        coEvery { integrations.isIntegrationEnabled(any()) } returns true
        coEvery { integrations.enabledProviderIds(StreamIntegrationType.STREMIO_ADDONS, any()) } returns setOf("stremio:${addon.id}")
        coEvery { integrations.getUnifiedSourceOrderedIdsSync() } returns emptyList()
        return StreamRepository(
            RuntimeEnvironment.getApplication(), api,
            mockk(relaxed = true), mockk(relaxed = true), mockk(relaxed = true), mockk(relaxed = true),
            mockk(relaxed = true), mockk(relaxed = true), mockk(relaxed = true), mockk(relaxed = true),
            integrations
        ).also { runBlocking { it.replaceSharedAddonsFromCloud(listOf(addon)) } }
    }

    private fun strictAddon(type: String) = Addon(
        "example.channels", "Channels", "1", "", true, type = AddonType.COMMUNITY,
        url = "https://ch.test/manifest.json",
        manifest = AddonManifest(
            "example.channels", "Channels", "1", types = listOf(type),
            resources = listOf(
                AddonResource("meta", listOf(type), listOf("chx_")),
                AddonResource("stream", listOf(type), listOf("chx_"))
            )
        )
    )

    @Test fun liveChannelIsAskedByItsOwnTypeFromAnAddonWithoutAMovieType() = runBlocking {
        val api = mockk<StreamApi>()
        val repository = repository(api, strictAddon("tv"))
        coEvery { api.getAddonStreams(any()) } returns
            StremioStreamResponse(listOf(StremioStream(url = "https://ch.test/live.m3u8")))

        val result = repository.resolveMovieStreams("chx_live_1", forceRefresh = true, nativeType = "tv")

        assertFalse(result.streams.isEmpty())
        coVerify(exactly = 1) { api.getAddonStreams("https://ch.test/stream/tv/chx_live_1.json") }
        coVerify(exactly = 0) { api.getAddonStreams(match { it.contains("/stream/movie/") }) }
    }

    @Test fun customTypeEpisodeIsFoundAndAskedByItsOwnType() = runBlocking {
        val api = mockk<StreamApi>()
        val repository = repository(api, strictAddon("Podcasts"))
        coEvery { api.getAddonMeta(any()) } returns StremioMetaResponse(StremioMetaPreview(
            id = "chx_pod_1", videos = listOf(StremioMetaVideo(id = "chx_pod_1:1:1", season = 1, episode = 1))))
        coEvery { api.getAddonStreams(any()) } returns
            StremioStreamResponse(listOf(StremioStream(url = "https://ch.test/ep1.mp3")))

        val result = repository.resolveEpisodeStreams("chx_pod_1", 1, 1, forceRefresh = true, nativeType = "Podcasts")

        assertFalse(result.streams.isEmpty())
        coVerify { api.getAddonMeta("https://ch.test/meta/Podcasts/chx_pod_1.json") }
        coVerify(exactly = 1) { api.getAddonStreams("https://ch.test/stream/Podcasts/chx_pod_1%3A1%3A1.json") }
    }

    @Test fun metaUrlsEncodeSpacesInTheTypeLikeCatalogUrls() = runBlocking {
        val api = mockk<StreamApi>()
        val repository = repository(api, strictAddon("Live Docs"))
        coEvery { api.getAddonMeta(any()) } returns StremioMetaResponse(StremioMetaPreview(id = "chx_doc_1", name = "Doc"))

        assertNotNull(repository.getAddonMeta("example.channels", "Live Docs", "chx_doc_1"))

        coVerify(exactly = 1) { api.getAddonMeta("https://ch.test/meta/Live%20Docs/chx_doc_1.json") }
    }

    @Test fun searchUrlsEncodeTheCatalogTypeAndIdLikeCatalogUrls() = runBlocking {
        val api = mockk<StreamApi>()
        val base = strictAddon("Live Docs")
        val addon = base.copy(manifest = base.manifest!!.copy(catalogs = listOf(
            AddonCatalog("Live Docs", "docs/new", extra = listOf(AddonCatalogExtra("search"))))))
        val repository = repository(api, addon)
        coEvery { api.getAddonCatalog(any()) } returns
            StremioCatalogResponse(metas = listOf(StremioMetaPreview(id = "chx_doc_1", name = "Doc")))

        assertEquals(1, repository.searchNativeAddonCatalogs("deep sea").size)

        coVerify(exactly = 1) {
            api.getAddonCatalog("https://ch.test/catalog/Live%20Docs/docs%2Fnew/search=deep%20sea.json")
        }
    }
}
