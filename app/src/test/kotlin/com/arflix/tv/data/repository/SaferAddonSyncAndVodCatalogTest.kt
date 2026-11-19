package com.arflix.tv.data.repository

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.arflix.tv.data.api.StreamApi
import com.arflix.tv.data.api.StremioCatalog
import com.arflix.tv.data.api.StremioCatalogResponse
import com.arflix.tv.data.api.StremioManifestResponse
import com.arflix.tv.data.api.StremioMetaPreview
import com.arflix.tv.data.model.Addon
import com.arflix.tv.data.model.AddonCatalog
import com.arflix.tv.data.model.AddonManifest
import com.arflix.tv.data.model.AddonType
import com.arflix.tv.data.model.CatalogConfig
import com.arflix.tv.data.model.CatalogSourceType
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.util.settingsDataStore
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.spyk
import io.mockk.unmockkStatic
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import org.robolectric.annotation.ConscryptMode

@RunWith(RobolectricTestRunner::class)
@Config(application = Application::class, manifest = Config.NONE, sdk = [28])
@ConscryptMode(ConscryptMode.Mode.OFF)
class SaferAddonSyncAndVodCatalogTest {

    private val prefsState = MutableStateFlow(emptyPreferences())
    private lateinit var streamApi: StreamApi
    private lateinit var streamRepository: StreamRepository
    private lateinit var catalogRepository: CatalogRepository

    private val context: Context get() = RuntimeEnvironment.getApplication()

    @Before
    fun setup() {
        val mutex = Mutex()
        val store = object : DataStore<Preferences> {
            override val data = prefsState
            override suspend fun updateData(transform: suspend (Preferences) -> Preferences): Preferences {
                return mutex.withLock {
                    transform(prefsState.value).toPreferences().also { prefsState.value = it }
                }
            }
        }
        mockkStatic("com.arflix.tv.data.repository.StreamRepositoryKt")
        mockkStatic("com.arflix.tv.util.DataStoresKt")
        every { any<Context>().streamDataStore } returns store
        every { any<Context>().settingsDataStore } returns store

        val profiles = mockk<ProfileManager>(relaxed = true)
        every { profiles.getProfileIdSync() } returns "default"
        every { profiles.currentProfileId } returns MutableStateFlow("default")
        every { profiles.profileStringKey(any()) } answers { stringPreferencesKey("profile_default_${firstArg<String>()}") }
        every { profiles.profileStringKeyFor(any(), any()) } answers {
            stringPreferencesKey("profile_${firstArg<String>()}_${secondArg<String>()}")
        }

        streamApi = mockk(relaxed = true)
        val httpRuntime = mockk<HttpLocalScraperRuntime>(relaxed = true)
        coEvery { httpRuntime.fetchInstallCandidate(any(), any()) } returns null

        streamRepository = StreamRepository(
            context,
            streamApi,
            mockk(relaxed = true),
            profiles,
            mockk(relaxed = true),
            mockk(relaxed = true),
            httpRuntime,
            mockk(relaxed = true),
            CloudSyncInvalidationBus(),
            mockk(relaxed = true),
            mockk(relaxed = true)
        )
        catalogRepository = CatalogRepository(
            context,
            profiles,
            mockk(relaxed = true),
            mockk(relaxed = true),
            CloudSyncInvalidationBus()
        )
    }

    @After
    fun cleanup() {
        if (::streamRepository.isInitialized) {
            val field = StreamRepository::class.java.getDeclaredField("repositoryScope").apply { isAccessible = true }
            (field.get(streamRepository) as CoroutineScope).coroutineContext[Job]?.let { job ->
                runBlocking { job.cancelAndJoin() }
            }
        }
        unmockkStatic("com.arflix.tv.data.repository.StreamRepositoryKt")
        unmockkStatic("com.arflix.tv.util.DataStoresKt")
    }

    @Test
    fun `legacy toggle uses its envelope time without losing configured URL or metadata`() {
        val current = Addon("legacy-test", "Family", "1", "", true, true, AddonType.CUSTOM,
            url = "https://addon.example/p/family/manifest.json", setupUpdatedAt = 200,
            metadataUpdatedAt = 300, manifest = AddonManifest("legacy-test", "Family", "1",
                catalogs = listOf(AddonCatalog("movie", "row", "Row", clientDelegate = "vod:recent"))))
        val legacy = current.copy(name = "Old device name", isEnabled = false, setupUpdatedAt = 0,
            metadataUpdatedAt = 0, url = "https://addon.example/manifest.json", manifest = null)
        val merged = reconcileExplicitAddonState(AddonCloudState(listOf(current), 200, emptyMap()),
            AddonCloudState(listOf(legacy), 500, emptyMap())).addons.single()
        assertFalse(merged.isEnabled)
        assertEquals(500L, merged.setupUpdatedAt)
        assertEquals(current.url, merged.url)
        assertEquals(current.manifest, merged.manifest)
        val stale = reconcileExplicitAddonState(AddonCloudState(listOf(merged), 500, emptyMap()),
            AddonCloudState(listOf(legacy.copy(isEnabled = true)), 400, emptyMap())).addons.single()
        assertFalse(stale.isEnabled)
    }

    @Test
    fun `newer empty catalog and resource declarations survive older manifests in both directions`() {
        val old = AddonManifest("a", "Addon", "1", resources = listOf(com.arflix.tv.data.model.AddonResource("catalog")),
            catalogs = listOf(AddonCatalog("movie", "row", "Row", clientDelegate = "vod:recent")))
        val cleared = old.copy(version = "2", catalogs = emptyList(), resources = emptyList())
        for (reverse in listOf(false, true)) {
            val merged = mergeAddonManifests(if (reverse) cleared else old, if (reverse) old else cleared,
                if (reverse) 200L else 100L, if (reverse) 100L else 200L, true)!!
            assertTrue(merged.catalogs.isEmpty())
            assertTrue(merged.resources.isEmpty())
            assertEquals("2", merged.version)
        }
    }

    @Test
    fun `a timed out addon does not prevent a healthy refresh or fabricate fresh cache metadata`() = runBlocking {
        val slow = Addon("slow", "Slow", "1", "", true, true, AddonType.CUSTOM,
            url = "https://slow.example/manifest.json", configureUrl = "https://slow.example/configure",
            manifest = AddonManifest("slow", "Slow", "1"), metadataUpdatedAt = 1L)
        val good = slow.copy(id = "good", name = "Good", url = "https://good.example/manifest.json",
            configureUrl = "https://good.example/configure", manifest = AddonManifest("good", "Good", "1"))
        streamRepository.applyAddonCloudState(listOf(slow, good), 100L, emptyMap())
        coEvery { streamApi.getAddonManifest(slow.url!!) } coAnswers {
            kotlinx.coroutines.delay(60_000)
            error("The slow request must time out")
        }
        coEvery { streamApi.getAddonManifest(good.url!!) } returns StremioManifestResponse(
            id = "good", name = "Good", version = "2", catalogs = listOf(
                StremioCatalog("movie", "vod", "VOD", clientDelegate = "vod:recent")))
        val report = streamRepository.refreshInstalledAddonsIfNeeded()
        assertEquals(1, report?.refreshed)
        assertEquals(1, report?.failed)
        val state = streamRepository.exportAddonCloudState()
        assertEquals(100L, state.updatedAt)
        assertTrue(state.changes.isEmpty())
        assertEquals("2", state.addons.first { it.id == "good" }.version)
        assertEquals(1L, state.addons.first { it.id == "slow" }.metadataUpdatedAt)
        assertEquals(1L, streamRepository.getAddonMetadataCache()["https://slow.example"]?.updatedAt)
        assertNull(streamRepository.refreshInstalledAddonsIfNeeded())
    }

    @Test
    fun `mergeAddonPreservingSetupAndMetadata protects configured URL, omitted delegation fields, and setup toggle`() {
        val fullManifest = AddonManifest(
            id = "tv.arvio.discover",
            name = "ARVIO Discover",
            version = "1.0.0",
            catalogs = listOf(
                AddonCatalog(
                    type = "movie",
                    id = "vod-recent-movies",
                    name = "Recent VOD Movies",
                    clientDelegate = "vod:recent?type=movie",
                    clientFilter = """{"availableOn":"vod"}"""
                )
            )
        )
        val storedPreTimestamp = Addon(
            id = "tv.arvio.discover_caffa292bf26",
            name = "ARVIO Discover",
            version = "1.0.0",
            description = "",
            isInstalled = true,
            isEnabled = true,
            type = AddonType.CUSTOM,
            url = "https://arvio-addon.netlify.app/p/p1/manifest.json",
            setupUpdatedAt = 0L,
            metadataUpdatedAt = 500L,
            manifest = fullManifest
        )

        // Older client without setupUpdatedAt pushes a generic root URL, custom name/toggle, and stripped delegation fields
        val legacyIncoming = Addon(
            id = "tv.arvio.discover_caffa292bf26",
            name = "ARVIO Discover Custom",
            version = "1.0.0",
            description = "",
            isInstalled = true,
            isEnabled = false,
            type = AddonType.CUSTOM,
            url = "https://arvio-addon.netlify.app/manifest.json",
            setupUpdatedAt = 0L,
            metadataUpdatedAt = 0L,
            manifest = AddonManifest(
                id = "tv.arvio.discover",
                name = "ARVIO Discover",
                version = "1.0.0",
                catalogs = listOf(
                    AddonCatalog(
                        type = "movie",
                        id = "vod-recent-movies",
                        name = "Recent VOD Movies"
                    )
                )
            )
        )

        val mergedLegacy = mergeAddonPreservingSetupAndMetadata(stored = storedPreTimestamp, incoming = legacyIncoming)
        assertFalse(mergedLegacy.isEnabled)
        assertEquals("ARVIO Discover Custom", mergedLegacy.name)
        assertEquals("https://arvio-addon.netlify.app/p/p1/manifest.json", mergedLegacy.url)
        assertEquals("vod:recent?type=movie", mergedLegacy.manifest?.catalogs?.single()?.clientDelegate)
        assertEquals("""{"availableOn":"vod"}""", mergedLegacy.manifest?.catalogs?.single()?.clientFilter)
        assertEquals(500L, mergedLegacy.metadataUpdatedAt)

        // Newer explicit setup toggle retains newer setup while still preserving newer cached metadata
        val storedWithSetupTs = storedPreTimestamp.copy(setupUpdatedAt = 100L)
        val newerSetupToggle = legacyIncoming.copy(
            url = "https://arvio-addon.netlify.app/p/p1/manifest.json",
            setupUpdatedAt = 200L
        )
        val mergedExplicit = mergeAddonPreservingSetupAndMetadata(stored = storedWithSetupTs, incoming = newerSetupToggle)
        assertFalse(mergedExplicit.isEnabled)
        assertEquals("ARVIO Discover Custom", mergedExplicit.name)
        assertEquals("https://arvio-addon.netlify.app/p/p1/manifest.json", mergedExplicit.url)
        assertEquals("vod:recent?type=movie", mergedExplicit.manifest?.catalogs?.single()?.clientDelegate)
        assertEquals("""{"availableOn":"vod"}""", mergedExplicit.manifest?.catalogs?.single()?.clientFilter)
        assertEquals(200L, mergedExplicit.setupUpdatedAt)
        assertEquals(500L, mergedExplicit.metadataUpdatedAt)
    }

    @Test
    fun `refreshInstalledAddonsIfNeeded updates stale manifest metadata without touching addonsUpdatedAt or addonChanges`() = runBlocking {
        val url = "https://arvio-addon.netlify.app/p/p1/manifest.json"
        val staleAddon = Addon(
            id = "tv.arvio.discover_caffa292bf26",
            name = "ARVIO Discover",
            version = "1.0.0",
            description = "",
            isInstalled = true,
            isEnabled = true,
            type = AddonType.CUSTOM,
            url = url,
            configureUrl = "https://arvio-addon.netlify.app/configure?profile=p1",
            setupUpdatedAt = 50L,
            metadataUpdatedAt = 0L,
            manifest = AddonManifest(
                id = "tv.arvio.discover",
                name = "ARVIO Discover",
                version = "1.0.0",
                catalogs = listOf(
                    AddonCatalog(type = "movie", id = "vod-recent-movies", name = "Recent VOD Movies")
                )
            )
        )
        streamRepository.applyAddonCloudState(listOf(staleAddon), 100L, emptyMap())
        assertTrue(streamRepository.needsImmediateAddonManifestRefresh())

        coEvery { streamApi.getAddonManifest(url) } returns StremioManifestResponse(
            id = "tv.arvio.discover",
            name = "ARVIO Discover",
            version = "1.1.0",
            catalogs = listOf(
                StremioCatalog(
                    type = "movie",
                    id = "vod-recent-movies",
                    name = "Recent VOD Movies",
                    clientDelegate = "vod:recent?type=movie",
                    clientFilter = """{"availableOn":"vod"}"""
                )
            )
        )

        val changesBeforeRefresh = prefsState.value[stringPreferencesKey("addon_changes_v1")]
        val report = streamRepository.refreshInstalledAddonsIfNeeded()
        assertEquals(1, report?.refreshed)
        assertFalse(streamRepository.needsImmediateAddonManifestRefresh())

        val refreshed = streamRepository.installedAddons.first().first { it.id == "tv.arvio.discover_caffa292bf26" }
        assertEquals("vod:recent?type=movie", refreshed.manifest?.catalogs?.single()?.clientDelegate)
        assertEquals("""{"availableOn":"vod"}""", refreshed.manifest?.catalogs?.single()?.clientFilter)
        assertTrue(refreshed.metadataUpdatedAt > 0L)
        assertEquals(50L, refreshed.setupUpdatedAt)

        // Refresh must never bump addons_updated_at or write an addon_changes_v1 reinstall record
        assertEquals(100L, prefsState.value[longPreferencesKey("addons_updated_at")])
        assertEquals(changesBeforeRefresh, prefsState.value[stringPreferencesKey("addon_changes_v1")])

        // Separate manifest cache must retain refreshed delegation metadata for future stripped pulls
        val cacheEntry = streamRepository.getAddonMetadataCache()["https://arvio-addon.netlify.app/p/p1"]
        assertEquals("vod:recent?type=movie", cacheEntry?.manifest?.catalogs?.single()?.clientDelegate)

        // Second call within TTL must be a no-op
        assertNull(streamRepository.refreshInstalledAddonsIfNeeded())
    }

    @Test
    fun `syncAddonCatalogs updates existing catalog row with refreshed clientDelegate and clientFilter`() = runBlocking {
        val baseCatalog = AddonCatalog(type = "movie", id = "vod-recent-movies", name = "Recent VOD Movies")
        val staleAddon = Addon(
            id = "tv.arvio.discover_caffa292bf26",
            name = "ARVIO Discover",
            version = "1.0.0",
            description = "",
            isInstalled = true,
            isEnabled = true,
            type = AddonType.CUSTOM,
            url = "https://arvio-addon.netlify.app/p/p1/manifest.json",
            manifest = AddonManifest("tv.arvio.discover", "ARVIO Discover", "1.0.0", catalogs = listOf(baseCatalog))
        )
        assertTrue(catalogRepository.syncAddonCatalogs(listOf(staleAddon)))
        val initialRow = catalogRepository.getCatalogs().single()
        assertNull(initialRow.clientDelegate)
        assertNull(initialRow.clientFilter)

        val upgradedAddon = staleAddon.copy(
            manifest = staleAddon.manifest?.copy(
                catalogs = listOf(
                    baseCatalog.copy(
                        clientDelegate = "vod:recent?type=movie",
                        clientFilter = """{"availableOn":"vod"}"""
                    )
                )
            )
        )
        assertTrue(catalogRepository.syncAddonCatalogs(listOf(upgradedAddon)))
        val upgradedRow = catalogRepository.getCatalogs().single()
        assertEquals(initialRow.id, upgradedRow.id)
        assertEquals("vod:recent?type=movie", upgradedRow.clientDelegate)
        assertEquals("""{"availableOn":"vod"}""", upgradedRow.clientFilter)
    }

    @Test
    fun `MediaRepository resolves vod recent seeds, hybrid availableOn vod, and filtered HTTP homeserver catalog`() = runBlocking {
        val streams = mockk<StreamRepository>(relaxed = true)
        val home = mockk<HomeServerRepository>(relaxed = true)
        val trakt = mockk<TraktRepository>(relaxed = true)
        val iptv = mockk<IptvRepository>(relaxed = true)

        every { streams.installedAddons } returns MutableStateFlow(emptyList())
        coEvery { iptv.getDelegatedVodCatalogSeeds(MediaType.MOVIE, null, any()) } returns listOf(
            IptvRepository.VodCatalogSeed(
                mediaType = MediaType.MOVIE,
                tmdbId = 10,
                imdbId = null,
                title = "VOD Movie",
                year = 2025,
                categoryId = "cat-1",
                addedEpochSec = 1000L
            )
        )
        coEvery { iptv.hasVodMatch(MediaType.MOVIE, 10, any(), any(), any()) } returns true
        coEvery { iptv.hasVodMatch(MediaType.MOVIE, 20, any(), any(), any()) } returns false
        coEvery { home.hasTitleMatch(MediaType.MOVIE, any(), any(), any(), 10) } returns false
        coEvery { home.hasTitleMatch(MediaType.MOVIE, any(), any(), any(), 20) } returns true

        val media = spyk(
            MediaRepository(
                context = mockk(relaxed = true),
                tmdbApi = mockk(),
                traktRepository = trakt,
                traktApi = mockk(),
                okHttpClient = mockk(),
                streamRepository = streams,
                homeServerRepository = home,
                iptvRepository = iptv
            )
        )
        coEvery { media.getMovieDetails(10) } returns MediaItem(10, "VOD Movie", year = "2025", mediaType = MediaType.MOVIE)
        coEvery { media.getMovieDetails(20) } returns MediaItem(20, "HomeServer Movie", year = "2024", mediaType = MediaType.MOVIE)

        val baseCatalog = CatalogConfig(
            id = "discover_row",
            title = "Discover Row",
            sourceType = CatalogSourceType.ADDON,
            addonId = "tv.arvio.discover",
            addonCatalogId = "vod-recent-movies",
            addonCatalogType = "movie"
        )

        // 1. Direct VOD delegation
        val vodPage = media.loadCustomCatalogPage(baseCatalog.copy(clientDelegate = "vod:recent?type=movie"), 0, 10)
        assertEquals(listOf(10), vodPage.items.map { it.id })

        // 2. Hybrid Trakt watchlist filtered by {"availableOn":"vod"}
        coEvery { trakt.getWatchlist() } returns listOf(
            MediaItem(10, "VOD Movie", mediaType = MediaType.MOVIE),
            MediaItem(20, "HomeServer Movie", mediaType = MediaType.MOVIE)
        )
        val hybridVodPage = media.loadCustomCatalogPage(
            baseCatalog.copy(
                addonCatalogId = "hybrid-watchlist-vod",
                clientDelegate = "trakt:watchlist?type=movie",
                clientFilter = """{"availableOn":"vod"}"""
            ),
            0,
            10
        )
        assertEquals(listOf(10), hybridVodPage.items.map { it.id })

        // 3. HTTP addon catalog filtered by {"availableOn":"homeserver"}
        coEvery { streams.getAddonCatalogPage("tv.arvio.discover", "movie", "hybrid-trending-server", 0) } returns StremioCatalogResponse(
            metas = listOf(
                StremioMetaPreview(id = "tmdb:10", name = "VOD Movie"),
                StremioMetaPreview(id = "tmdb:20", name = "HomeServer Movie")
            )
        )
        val httpFilteredPage = media.loadCustomCatalogPage(
            baseCatalog.copy(
                addonCatalogId = "hybrid-trending-server",
                clientDelegate = null,
                clientFilter = """{"availableOn":"homeserver"}"""
            ),
            0,
            10
        )
        assertEquals(listOf(20), httpFilteredPage.items.map { it.id })
        coVerify(exactly = 1) { trakt.getWatchlist() }
    }
}
