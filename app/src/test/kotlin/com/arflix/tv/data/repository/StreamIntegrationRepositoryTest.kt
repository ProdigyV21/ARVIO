package com.arflix.tv.data.repository

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.arflix.tv.core.plugin.PluginManager
import com.arflix.tv.data.model.Addon
import com.arflix.tv.data.model.AddonType
import com.arflix.tv.data.model.StreamIntegrationType
import com.arflix.tv.data.model.StreamSearchMode
import com.arflix.tv.data.telegram.TelegramRepository
import com.arflix.tv.domain.model.PluginRepository
import com.arflix.tv.domain.model.RepositoryType
import com.arflix.tv.domain.model.ScraperInfo
import com.arflix.tv.testing.IsolatedSettingsStoreRule
import io.mockk.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import org.robolectric.annotation.ConscryptMode
import java.util.UUID
import androidx.datastore.preferences.core.edit
import com.arflix.tv.util.settingsDataStore
import javax.inject.Provider

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28], application = Application::class)
@ConscryptMode(ConscryptMode.Mode.OFF)
class StreamIntegrationRepositoryTest {

    @get:Rule
    val settingsStore = IsolatedSettingsStoreRule()

    @Before fun isolateEffectiveSettings() {
        mockkStatic("com.arflix.tv.data.repository.PartnerPreferencesKt")
        every { any<Context>().effectiveSettingsDataStore } answers { firstArg<Context>().settingsDataStore }
    }

    @After fun releaseEffectiveSettings() {
        unmockkStatic("com.arflix.tv.data.repository.PartnerPreferencesKt")
    }

    private val profile = "profile-${UUID.randomUUID()}"
    private val profileManager = mockk<ProfileManager> {
        every { activeProfileId } returns MutableStateFlow(profile)
        coEvery { getProfileId() } returns profile
        every { getProfileIdSync() } returns profile
        every { profileStringKeyFor(any(), any()) } answers { stringPreferencesKey("profile_${firstArg<String>()}_${secondArg<String>()}") }
        every { profileBooleanKeyFor(any(), any()) } answers { booleanPreferencesKey("profile_${firstArg<String>()}_${secondArg<String>()}") }
    }

    private val invalidationBus = mockk<CloudSyncInvalidationBus>(relaxed = true)

    private val fakeStremioAddons = MutableStateFlow(
        listOf(
            Addon(
                id = "torrentio",
                name = "Torrentio",
                version = "1.0.0",
                description = "Torrentio streams",
                isInstalled = true,
                type = AddonType.COMMUNITY,
                transportUrl = "https://torrentio.strem.fun/manifest.json",
                isEnabled = true
            ),
            Addon(
                id = "opensubtitles",
                name = "OpenSubtitles",
                version = "1.0.0",
                description = "Subtitles",
                isInstalled = true,
                type = AddonType.SUBTITLE,
                transportUrl = "https://opensubtitles.strem.fun/manifest.json",
                isEnabled = true
            )
        )
    )

    private val streamRepository = mockk<StreamRepository>(relaxed = true) {
        every { installedAddons } returns fakeStremioAddons
    }

    private val fakeScrapers = MutableStateFlow(
        listOf(
            ScraperInfo(
                id = "scraper_superstream",
                name = "superstream",
                description = "Superstream scraper",
                version = "1.0.0",
                filename = "superstream.js",
                supportedTypes = listOf("movie", "tv"),
                enabled = true,
                manifestEnabled = true,
                logo = null,
                contentLanguage = emptyList(),
                repositoryId = "repo1",
                formats = null
            )
        )
    )

    private val fakePluginRepos = MutableStateFlow(
        listOf(
            PluginRepository(
                id = "repo1",
                name = "SoraStream Repo",
                url = "https://example.com/repo.json",
                description = "Community plugins",
                enabled = true,
                scraperCount = 1,
                type = RepositoryType.NUVIO_JS
            )
        )
    )

    private val pluginManager = mockk<PluginManager>(relaxed = true) {
        every { scrapers } returns fakeScrapers
        every { repositories } returns fakePluginRepos
    }

    private val homeServerRepository = mockk<HomeServerRepository> {
        every { connections } returns MutableStateFlow(
            listOf(
                HomeServerConnection(
                    enabled = true,
                    serverUrl = "http://localhost:8096",
                    displayName = "Emby Server",
                    serverName = "Emby",
                    connectionId = "conn_emby_1"
                )
            )
        )
    }

    private val fakeIptvConfig = MutableStateFlow(
        IptvConfig(
            playlists = listOf(
                IptvPlaylistEntry(
                    id = "playlist_1",
                    name = "Premium IPTV",
                    m3uUrl = "http://iptv.example.com/get.php",
                    enabled = true,
                    importVod = true
                )
            )
        )
    )

    private val iptvRepository = mockk<IptvRepository> {
        every { observeConfig() } returns fakeIptvConfig
    }

    private val telegramRepository = mockk<TelegramRepository> {
        every { isAuthenticated() } returns true
    }

    private fun createRepository(
        telegramRepo: TelegramRepository? = telegramRepository
    ): StreamIntegrationRepository {
        return StreamIntegrationRepository(
            context = RuntimeEnvironment.getApplication(),
            profileManager = profileManager,
            streamRepositoryProvider = Provider { streamRepository },
            pluginManagerProvider = Provider { pluginManager },
            homeServerRepositoryProvider = Provider { homeServerRepository },
            iptvRepositoryProvider = Provider { iptvRepository },
            telegramRepositoryProvider = telegramRepo?.let { Provider { it } },
            invalidationBus = invalidationBus
        )
    }

    @Test
    fun defaultStreamIntegrationsOrderMatchesDefaultOrderAndAreAllEnabled() = runBlocking {
        val repo = createRepository()
        val configs = repo.getConfigs()

        assertEquals(5, configs.size)
        assertEquals(StreamIntegrationType.HOME_SERVER, configs[0].type)
        assertEquals(StreamIntegrationType.STREMIO_ADDONS, configs[1].type)
        assertEquals(StreamIntegrationType.PLUGINS, configs[2].type)
        assertEquals(StreamIntegrationType.TELEGRAM, configs[3].type)
        assertEquals(StreamIntegrationType.IPTV_VOD, configs[4].type)

        assertTrue(configs.all { it.isEnabled })
        assertEquals(listOf(0, 1, 2, 3, 4), configs.map { it.priority })
    }

    @Test
    fun movingIntegrationDownSwapsPriorityCorrectly() = runBlocking {
        val repo = createRepository()

        repo.moveIntegrationDown(StreamIntegrationType.HOME_SERVER)

        val configs = repo.getConfigs()
        assertEquals(StreamIntegrationType.STREMIO_ADDONS, configs[0].type)
        assertEquals(StreamIntegrationType.HOME_SERVER, configs[1].type)
        assertEquals(StreamIntegrationType.PLUGINS, configs[2].type)
    }

    @Test
    fun movingIntegrationUpSwapsPriorityCorrectly() = runBlocking {
        val repo = createRepository()

        // Move Stremio up: already at 1, moves to 0
        repo.moveIntegrationUp(StreamIntegrationType.STREMIO_ADDONS)

        val configs = repo.getConfigs()
        assertEquals(StreamIntegrationType.STREMIO_ADDONS, configs[0].type)
        assertEquals(StreamIntegrationType.HOME_SERVER, configs[1].type)
    }

    @Test
    fun togglingIntegrationUpdatesEnabledState() = runBlocking {
        val repo = createRepository()

        assertTrue(repo.isIntegrationEnabled(StreamIntegrationType.TELEGRAM))

        repo.toggleIntegration(StreamIntegrationType.TELEGRAM)
        assertFalse(repo.isIntegrationEnabled(StreamIntegrationType.TELEGRAM))

        val configs = repo.getConfigs()
        val telegramConfig = configs.first { it.type == StreamIntegrationType.TELEGRAM }
        assertFalse(telegramConfig.isEnabled)

        repo.toggleIntegration(StreamIntegrationType.TELEGRAM)
        assertTrue(repo.isIntegrationEnabled(StreamIntegrationType.TELEGRAM))
    }

    @Test
    fun granularProvidersIncludeAllConfiguredItems() = runBlocking {
        val repo = createRepository()
        val items = repo.getProviderItems()

        assertEquals(5, items.size)
        assertEquals("homeserver:conn_emby_1", items[0].id)
        assertEquals("Emby Server", items[0].displayName)
        assertEquals(StreamIntegrationType.HOME_SERVER, items[0].type)

        assertEquals("stremio:torrentio", items[1].id)
        assertEquals("Torrentio", items[1].displayName)
        assertEquals(StreamIntegrationType.STREMIO_ADDONS, items[1].type)

        assertEquals("plugin_repo:repo1", items[2].id)
        assertEquals("SoraStream Repo", items[2].displayName)
        assertEquals(StreamIntegrationType.PLUGINS, items[2].type)

        assertEquals("telegram", items[3].id)
        assertEquals("Telegram", items[3].displayName)
        assertEquals(StreamIntegrationType.TELEGRAM, items[3].type)

        assertEquals("iptv_playlist:playlist_1", items[4].id)
        assertEquals("Premium IPTV", items[4].displayName)
        assertEquals(StreamIntegrationType.IPTV_VOD, items[4].type)
    }

    @Test
    fun reorderingIndividualProviderAcrossTypesUpdatesPriorities() = runBlocking {
        val repo = createRepository()

        // Initial order: Emby Server (#1), Torrentio (#2), SoraStream Repo (#3), Telegram (#4), Premium IPTV (#5)
        val initialItems = repo.getProviderItems()
        assertEquals("homeserver:conn_emby_1", initialItems[0].id)
        assertEquals("stremio:torrentio", initialItems[1].id)

        // Move Torrentio up to #1
        val moved = repo.moveProviderItemUp("stremio:torrentio")
        assertTrue(moved)

        val updatedItems = repo.getProviderItems()
        assertEquals("stremio:torrentio", updatedItems[0].id)
        assertEquals(1, updatedItems[0].priority)
        assertFalse(updatedItems[0].canMoveUp)
        assertTrue(updatedItems[0].canMoveDown)

        assertEquals("homeserver:conn_emby_1", updatedItems[1].id)
        assertEquals(2, updatedItems[1].priority)

        // Verify that getUnifiedSourceOrderedIds places torrentio before home_server
        val orderedSourceIds = repo.getUnifiedSourceOrderedIds().first()
        val torrentioIdx = orderedSourceIds.indexOf("torrentio")
        val homeServerIdx = orderedSourceIds.indexOf("home_server")
        assertTrue("torrentio must exist in orderedSourceIds", torrentioIdx >= 0)
        assertTrue("home_server must exist in orderedSourceIds", homeServerIdx >= 0)
        assertTrue("Torrentio should be prioritized before Home Server", torrentioIdx < homeServerIdx)
    }

    @Test
    fun togglingIndividualProviderDisablesOnlyThatItem() = runBlocking {
        val repo = createRepository()

        val initialItems = repo.getProviderItems()
        val torrentioItem = initialItems.first { it.id == "stremio:torrentio" }
        assertTrue(torrentioItem.isEnabled)

        // Toggle Torrentio off
        val newEnabled = repo.toggleProviderItem("stremio:torrentio")
        assertFalse(newEnabled)

        val afterToggleItems = repo.getProviderItems()
        assertFalse(afterToggleItems.first { it.id == "stremio:torrentio" }.isEnabled)
        // Other providers remain enabled
        assertTrue(afterToggleItems.first { it.id == "homeserver:conn_emby_1" }.isEnabled)
        assertTrue(afterToggleItems.first { it.id == "plugin_repo:repo1" }.isEnabled)

        // getUnifiedSourceOrderedIds should exclude torrentio
        val orderedSourceIds = repo.getUnifiedSourceOrderedIds().first()
        assertFalse(orderedSourceIds.contains("torrentio"))
        assertTrue(orderedSourceIds.contains("home_server"))
        assertTrue(orderedSourceIds.contains("plugin_superstream"))
    }

    @Test
    fun getUnifiedSourceOrderedIdsRespectsUserPriorityAndEnabledState() = runBlocking {
        val repo = createRepository()

        val defaultIds = repo.getUnifiedSourceOrderedIds().first()

        // In default order: HomeServer -> Stremio -> Plugins -> Telegram -> IPTV VOD
        val homeServerPos = defaultIds.indexOf("home_server")
        val torrentioPos = defaultIds.indexOf("torrentio")
        val pluginPos = defaultIds.indexOf("plugin_superstream")
        val telegramPos = defaultIds.indexOf("telegram_native")
        val iptvVodPos = defaultIds.indexOf("iptv_xtream_vod")

        assertTrue("home_server must exist in defaultIds", homeServerPos >= 0)
        assertTrue("torrentio must exist in defaultIds", torrentioPos >= 0)
        assertTrue("plugin_superstream must exist in defaultIds", pluginPos >= 0)
        assertTrue("telegram_native must exist in defaultIds", telegramPos >= 0)
        assertTrue("iptv_xtream_vod must exist in defaultIds", iptvVodPos >= 0)

        assertTrue(homeServerPos < torrentioPos)
        assertTrue(torrentioPos < pluginPos)
        assertTrue(pluginPos < telegramPos)
        assertTrue(telegramPos < iptvVodPos)

        // Subtitle addons should not be included in stream ordered IDs
        assertFalse(defaultIds.contains("opensubtitles"))

        // Move Stremio addons to #1 priority above home server
        repo.moveIntegrationUp(StreamIntegrationType.STREMIO_ADDONS)

        val reorderedIds = repo.getUnifiedSourceOrderedIds().first()
        val newTorrentioPos = reorderedIds.indexOf("torrentio")
        val newHomeServerPos = reorderedIds.indexOf("home_server")
        assertTrue("torrentio must exist in reorderedIds", newTorrentioPos >= 0)
        assertTrue("home_server must exist in reorderedIds", newHomeServerPos >= 0)
        assertTrue(newTorrentioPos < newHomeServerPos)

        // Disable Telegram and check it's excluded from source IDs
        repo.toggleIntegration(StreamIntegrationType.TELEGRAM)
        val withTelegramDisabled = repo.getUnifiedSourceOrderedIds().first()
        assertFalse(withTelegramDisabled.contains("telegram_native"))
    }

    @Test
    fun emptyProviderSubsystemsReturnEmptyListWithoutPlaceholders() = runBlocking {
        val emptyHomeServerRepo = mockk<HomeServerRepository> {
            every { connections } returns MutableStateFlow(emptyList())
        }
        val emptyStreamRepo = mockk<StreamRepository> {
            every { installedAddons } returns MutableStateFlow(emptyList())
        }
        val emptyPluginManager = mockk<PluginManager> {
            every { repositories } returns MutableStateFlow(emptyList())
            every { scrapers } returns MutableStateFlow(emptyList())
        }
        val emptyIptvRepo = mockk<IptvRepository> {
            every { observeConfig() } returns MutableStateFlow(IptvConfig())
            every { activePlaylists(any()) } returns emptyList()
        }
        val unauthTelegramRepo = mockk<TelegramRepository> {
            every { isAuthenticated() } returns false
        }

        val emptyRepo = StreamIntegrationRepository(
            context = RuntimeEnvironment.getApplication(),
            profileManager = profileManager,
            streamRepositoryProvider = Provider { emptyStreamRepo },
            pluginManagerProvider = Provider { emptyPluginManager },
            homeServerRepositoryProvider = Provider { emptyHomeServerRepo },
            iptvRepositoryProvider = Provider { emptyIptvRepo },
            telegramRepositoryProvider = Provider { unauthTelegramRepo },
            invalidationBus = invalidationBus
        )

        val items = emptyRepo.getProviderItems()
        assertTrue("Provider items should be completely empty with no dummy placeholder roots", items.isEmpty())
        val orderedIds = emptyRepo.getUnifiedSourceOrderedIdsSync()
        assertTrue("Unified ordered IDs should be empty", orderedIds.isEmpty())
    }

    @Test
    fun defaultStreamSearchModeIsParallel() = runBlocking {
        val repo = createRepository()
        assertEquals(StreamSearchMode.PARALLEL, repo.getSearchMode())
        assertEquals(StreamSearchMode.PARALLEL, repo.observeSearchMode().first())
    }

    @Test
    fun enabledProviderSelectionExcludesDisabledAndUnrelatedProviders() = runBlocking {
        val repo = createRepository()
        assertEquals(setOf("homeserver:conn_emby_1"), repo.enabledProviderIds(StreamIntegrationType.HOME_SERVER))
        assertTrue(repo.enabledProviderIds(StreamIntegrationType.HOME_SERVER, "homeserver:other").isEmpty())
        repo.toggleProviderItem("homeserver:conn_emby_1")
        assertTrue(repo.enabledProviderIds(StreamIntegrationType.HOME_SERVER).isEmpty())
        assertEquals(setOf("iptv_playlist:playlist_1"), repo.enabledProviderIds(StreamIntegrationType.IPTV_VOD))
        repo.toggleProviderItem("iptv_playlist:playlist_1")
        assertTrue(repo.enabledProviderIds(StreamIntegrationType.IPTV_VOD).isEmpty())
    }

    @Test
    fun selectionAndDisableArePerProviderNotPerCategory() = runBlocking {
        fakeIptvConfig.value = fakeIptvConfig.value.copy(playlists = fakeIptvConfig.value.playlists +
            fakeIptvConfig.value.playlists.first().copy(id = "playlist_2", importVod = false, importSeries = true))
        fakePluginRepos.value = fakePluginRepos.value + fakePluginRepos.value.first().copy(id = "repo2")
        val repo = createRepository()
        assertEquals(setOf("iptv_playlist:playlist_2"), repo.enabledProviderIds(StreamIntegrationType.IPTV_VOD, "iptv_playlist:playlist_2"))
        repo.toggleProviderItem("iptv_playlist:playlist_1")
        assertEquals(setOf("iptv_playlist:playlist_2"), repo.enabledProviderIds(StreamIntegrationType.IPTV_VOD))
        repo.toggleProviderItem("plugin_repo:repo1")
        assertEquals(setOf("plugin_repo:repo2"), repo.enabledProviderIds(StreamIntegrationType.PLUGINS))
    }

    @Test
    fun macroDisableOverridesIndividualProviderAndCloudRestoreKeepsSelection() = runBlocking {
        val repo = createRepository()
        repo.toggleIntegration(StreamIntegrationType.HOME_SERVER)
        assertTrue(repo.enabledProviderIds(StreamIntegrationType.HOME_SERVER, "homeserver:conn_emby_1").isEmpty())
        repo.toggleProviderItem("iptv_playlist:playlist_1")
        val exported = repo.exportCloudSettingsForProfile(RuntimeEnvironment.getApplication().settingsDataStore.data.first(), profile)
        assertEquals(false, exported.providerEnabledMap["iptv_playlist:playlist_1"])
    }

    @Test
    fun setSearchModeUpdatesAndPersistsPreference() = runBlocking {
        val repo = createRepository()

        repo.setSearchMode(StreamSearchMode.SEQUENTIAL)
        assertEquals(StreamSearchMode.SEQUENTIAL, repo.getSearchMode())
        assertEquals(StreamSearchMode.SEQUENTIAL, repo.observeSearchMode().first())

        repo.setSearchMode(StreamSearchMode.PARALLEL)
        assertEquals(StreamSearchMode.PARALLEL, repo.getSearchMode())
        assertEquals(StreamSearchMode.PARALLEL, repo.observeSearchMode().first())
    }

    @Test
    fun exportAndApplyCloudSettingsRoundTrip() = runBlocking {
        val repo = createRepository()
        val context = RuntimeEnvironment.getApplication()

        repo.setSearchMode(StreamSearchMode.SEQUENTIAL)
        val initialItems = repo.getProviderItems()
        if (initialItems.size >= 2) {
            repo.moveProviderItemDown(initialItems[0].id)
            repo.toggleProviderItem(initialItems[1].id)
        }

        val prefsBefore = context.settingsDataStore.data.first()
        val exported = repo.exportCloudSettingsForProfile(prefsBefore, profile)

        assertEquals(StreamSearchMode.SEQUENTIAL.id, exported.searchMode)

        // Apply into new/clean preferences for another profile
        val targetProfile = "profile-target"
        context.settingsDataStore.edit { mutablePrefs ->
            repo.applyCloudSettingsForProfile(mutablePrefs, targetProfile, exported)
        }

        val prefsAfter = context.settingsDataStore.data.first()
        val reExported = repo.exportCloudSettingsForProfile(prefsAfter, targetProfile)

        assertEquals(exported.searchMode, reExported.searchMode)
        assertEquals(exported.customProviderOrder, reExported.customProviderOrder)
        assertEquals(exported.providerEnabledMap, reExported.providerEnabledMap)
        assertEquals(exported.macroCategoryOrder, reExported.macroCategoryOrder)
        assertEquals(exported.macroEnabledMap, reExported.macroEnabledMap)
    }

    @Test
    fun cloudProfileSettingsStreamIntegrationsRoundTrip() {
        val gson = com.google.gson.Gson()
        val original = CloudSyncRepository.CloudProfileSettings(
            streamSearchMode = StreamSearchMode.SEQUENTIAL.id,
            streamProvidersCustomOrder = "stremio:torrentio,plugin_repo:rep1",
            streamIntegrationsMacroOrder = "stremio,homeserver",
            streamMacroEnabledMap = mapOf("telegram" to false),
            streamProviderEnabledMap = mapOf("stremio:torrentio" to true)
        )
        val json = gson.toJson(original)
        val restored = gson.fromJson(json, CloudSyncRepository.CloudProfileSettings::class.java)

        assertEquals(StreamSearchMode.SEQUENTIAL.id, restored.streamSearchMode)
        assertEquals("stremio:torrentio,plugin_repo:rep1", restored.streamProvidersCustomOrder)
        assertEquals("stremio,homeserver", restored.streamIntegrationsMacroOrder)
        assertEquals(false, restored.streamMacroEnabledMap["telegram"])
        assertEquals(true, restored.streamProviderEnabledMap["stremio:torrentio"])
    }
}
