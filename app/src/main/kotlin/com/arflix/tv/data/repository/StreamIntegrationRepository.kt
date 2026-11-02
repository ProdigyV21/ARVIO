package com.arflix.tv.data.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.arflix.tv.core.plugin.PluginManager
import com.arflix.tv.data.model.AddonType
import com.arflix.tv.data.model.IptvVodSourceIds
import com.arflix.tv.data.model.RuntimeKind
import com.arflix.tv.data.model.StreamIntegrationConfig
import com.arflix.tv.data.model.StreamIntegrationType
import com.arflix.tv.data.model.StreamProviderItem
import com.arflix.tv.data.model.StreamSearchMode
import com.arflix.tv.data.telegram.TelegramRepository
import com.arflix.tv.util.settingsDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

private const val STREAM_INTEGRATIONS_ORDER_PREFIX = "stream_integrations_order"
private const val STREAM_INTEGRATION_ENABLED_PREFIX = "stream_integration_enabled_"
private const val STREAM_PROVIDERS_CUSTOM_ORDER_PREFIX = "stream_providers_custom_order"
private const val STREAM_PROVIDER_ENABLED_PREFIX = "stream_provider_enabled_"
private const val STREAM_SEARCH_MODE_PREFIX = "stream_search_mode"

@Singleton
class StreamIntegrationRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val profileManager: ProfileManager,
    private val streamRepositoryProvider: Provider<StreamRepository>,
    private val pluginManagerProvider: Provider<PluginManager>,
    private val homeServerRepositoryProvider: Provider<HomeServerRepository>,
    private val iptvRepositoryProvider: Provider<IptvRepository>,
    private val telegramRepositoryProvider: Provider<TelegramRepository>? = null,
    private val invalidationBus: CloudSyncInvalidationBus
) {

    private fun orderKey(profileId: String) =
        profileManager.profileStringKeyFor(profileId, STREAM_INTEGRATIONS_ORDER_PREFIX)

    private fun enabledKey(profileId: String, type: StreamIntegrationType) =
        profileManager.profileBooleanKeyFor(profileId, "$STREAM_INTEGRATION_ENABLED_PREFIX${type.id}")

    private fun providerOrderKey(profileId: String) =
        profileManager.profileStringKeyFor(profileId, STREAM_PROVIDERS_CUSTOM_ORDER_PREFIX)

    private fun providerEnabledKey(profileId: String, providerId: String) =
        profileManager.profileBooleanKeyFor(profileId, "$STREAM_PROVIDER_ENABLED_PREFIX$providerId")

    private fun searchModeKey(profileId: String) =
        profileManager.profileStringKeyFor(profileId, STREAM_SEARCH_MODE_PREFIX)

    /**
     * Flow of integration configurations (macro categories) for the active profile.
     */
    fun observeConfigs(): Flow<List<StreamIntegrationConfig>> {
        return profileManager.activeProfileId.flatMapLatest { profileId ->
            context.settingsDataStore.data.map { prefs ->
                val savedOrderRaw = prefs[orderKey(profileId)].orEmpty()
                val parsedOrder = if (savedOrderRaw.isNotBlank()) {
                    savedOrderRaw.split(",")
                        .mapNotNull { StreamIntegrationType.fromId(it) }
                        .distinct()
                } else {
                    emptyList()
                }

                val allTypes = (parsedOrder + StreamIntegrationType.DEFAULT_ORDER).distinct()
                allTypes.mapIndexed { index, type ->
                    val isEnabled = prefs[enabledKey(profileId, type)] ?: true
                    StreamIntegrationConfig(
                        type = type,
                        isEnabled = isEnabled,
                        priority = index
                    )
                }
            }
        }
    }

    suspend fun getConfigs(): List<StreamIntegrationConfig> {
        return observeConfigs().first()
    }

    fun observeIntegrationEnabled(type: StreamIntegrationType): Flow<Boolean> {
        return profileManager.activeProfileId.flatMapLatest { profileId ->
            context.settingsDataStore.data.map { prefs ->
                prefs[enabledKey(profileId, type)] ?: true
            }
        }
    }

    suspend fun isIntegrationEnabled(type: StreamIntegrationType): Boolean {
        val profileId = profileManager.getProfileId()
        val prefs = context.settingsDataStore.data.first()
        val macroEnabled = prefs[enabledKey(profileId, type)]
        if (macroEnabled == false) return false

        val items = observeProviderItems().first()
        val typeItems = items.filter { it.type == type }
        if (typeItems.isNotEmpty()) {
            return typeItems.any { it.isEnabled }
        }
        return macroEnabled ?: true
    }

    suspend fun moveIntegrationUp(type: StreamIntegrationType): Boolean {
        return moveIntegration(type, direction = -1)
    }

    suspend fun moveIntegrationDown(type: StreamIntegrationType): Boolean {
        return moveIntegration(type, direction = 1)
    }

    private suspend fun moveIntegration(type: StreamIntegrationType, direction: Int): Boolean {
        if (direction == 0) return false
        val profileId = profileManager.getProfileId()
        val currentConfigs = getConfigs()
        val currentIndex = currentConfigs.indexOfFirst { it.type == type }
        if (currentIndex < 0) return false

        val targetIndex = (currentIndex + direction).coerceIn(0, currentConfigs.lastIndex)
        if (targetIndex == currentIndex) return false

        val mutableList = currentConfigs.map { it.type }.toMutableList()
        val item = mutableList.removeAt(currentIndex)
        mutableList.add(targetIndex, item)

        saveOrder(profileId, mutableList)
        // Reset provider-level custom order so macro category ordering applies
        context.settingsDataStore.edit { prefs ->
            prefs.remove(providerOrderKey(profileId))
        }
        invalidationBus.markDirty(
            scope = CloudSyncScope.PROFILE_SETTINGS,
            profileId = profileId,
            reason = "stream_integrations_order_moved"
        )
        return true
    }

    suspend fun toggleIntegration(type: StreamIntegrationType): Boolean {
        val profileId = profileManager.getProfileId()
        var newEnabled = false
        context.settingsDataStore.edit { prefs ->
            val current = prefs[enabledKey(profileId, type)] ?: true
            newEnabled = !current
            prefs[enabledKey(profileId, type)] = newEnabled
        }
        invalidationBus.markDirty(
            scope = CloudSyncScope.PROFILE_SETTINGS,
            profileId = profileId,
            reason = "stream_integration_toggled_${type.id}"
        )
        return newEnabled
    }

    private suspend fun saveOrder(profileId: String, order: List<StreamIntegrationType>) {
        val serialized = order.joinToString(",") { it.id }
        context.settingsDataStore.edit { prefs ->
            prefs[orderKey(profileId)] = serialized
        }
    }

    /**
     * Raw provider items observed directly from all 5 streaming subsystems.
     */
    private fun observeRawProviderItems(): Flow<List<StreamProviderItem>> {
        val streamRepository = streamRepositoryProvider.get()
        val pluginManager = pluginManagerProvider.get()
        val homeServerRepository = homeServerRepositoryProvider.get()
        val iptvRepository = iptvRepositoryProvider.get()

        return combine(
            homeServerRepository.connections,
            streamRepository.installedAddons,
            pluginManager.repositories,
            pluginManager.scrapers,
            iptvRepository.observeConfig()
        ) { homeServers, installedAddons, pluginRepos, scrapers, iptvConfig ->
            buildList {
                // 1. Home servers
                homeServers.forEach { server ->
                    val label = server.displayName.ifBlank { server.serverName }
                    add(
                        StreamProviderItem(
                            id = "homeserver:${server.connectionId.ifBlank { server.serverId }}",
                            rawProviderKeys = buildList {
                                if (label.isNotBlank()) add("home_server:$label")
                                add("home_server")
                            },
                            displayName = label.ifBlank { "Home Server" },
                            subtitle = "${server.serverKind.name.lowercase().replaceFirstChar { it.uppercase() }}${if (server.serverUrl.isNotBlank()) " • ${server.serverUrl}" else ""}",
                            type = StreamIntegrationType.HOME_SERVER,
                            isEnabled = server.enabled,
                            targetConfigId = "homeserver:${server.connectionId}"
                        )
                    )
                }

                // 2. Stremio Addons (only addons that provide video playback streams)
                val vodAddons = installedAddons.filter { addon ->
                    if (!addon.isInstalled) return@filter false
                    if (addon.type == AddonType.SUBTITLE || addon.type == AddonType.METADATA) return@filter false
                    val manifest = addon.manifest
                    if (manifest != null && manifest.resources.isNotEmpty()) {
                        manifest.resources.any { resource ->
                            resource.name.equals("stream", ignoreCase = true) ||
                                resource.name.equals("streams", ignoreCase = true)
                        }
                    } else {
                        true
                    }
                }
                vodAddons.forEach { addon ->
                    add(
                        StreamProviderItem(
                            id = "stremio:${addon.id}",
                            rawProviderKeys = listOf(addon.id),
                            displayName = addon.name,
                            subtitle = if (!addon.version.isNullOrBlank()) "v${addon.version}" else "Stremio Addon",
                            type = StreamIntegrationType.STREMIO_ADDONS,
                            isEnabled = addon.isEnabled,
                            targetConfigId = "stremio:${addon.id}"
                        )
                    )
                }

                // 3. Plugins (grouped by Plugin Repository to avoid 100+ scrapers cluttering the UI)
                pluginRepos.forEach { repo ->
                    val repoScrapers = scrapers.filter { it.repositoryId == repo.id }
                    val scraperKeys = repoScrapers.map { "plugin_${it.name.lowercase().replace(" ", "_")}" }
                    add(
                        StreamProviderItem(
                            id = "plugin_repo:${repo.id}",
                            rawProviderKeys = scraperKeys.ifEmpty { listOf("plugin_${repo.id}", "plugin_") },
                            displayName = repo.name,
                            subtitle = if (repo.scraperCount > 0) "${repo.scraperCount} scrapers" else (repo.description ?: "Plugin Repository"),
                            type = StreamIntegrationType.PLUGINS,
                            isEnabled = repo.enabled,
                            targetConfigId = "plugin_repo:${repo.id}"
                        )
                    )
                }
                val orphanScrapers = scrapers.filter { it.repositoryId.isNullOrBlank() }
                if (orphanScrapers.isNotEmpty() && pluginRepos.none { it.id == "default" }) {
                    val scraperKeys = orphanScrapers.map { "plugin_${it.name.lowercase().replace(" ", "_")}" }
                    add(
                        StreamProviderItem(
                            id = "plugin_repo:default",
                            rawProviderKeys = scraperKeys,
                            displayName = "Built-in Plugins",
                            subtitle = "${orphanScrapers.size} scrapers",
                            type = StreamIntegrationType.PLUGINS,
                            isEnabled = true,
                            targetConfigId = "plugin_repo:default"
                        )
                    )
                }

                // 4. Telegram
                val isTelegramAuth = runCatching { telegramRepositoryProvider?.get()?.isAuthenticated() == true }.getOrDefault(false)
                if (isTelegramAuth) {
                    add(
                        StreamProviderItem(
                            id = "telegram",
                            rawProviderKeys = listOf("telegram_native", "telegram"),
                            displayName = "Telegram",
                            subtitle = "Direct streaming",
                            type = StreamIntegrationType.TELEGRAM,
                            isEnabled = true,
                            targetConfigId = "telegram"
                        )
                    )
                }

                // 5. IPTV VOD
                val vodPlaylists = iptvConfig.playlists.ifEmpty { iptvRepository.activePlaylists(iptvConfig) }
                    .filter { (it.importVod ?: true) || (it.importSeries ?: true) }
                val vodPortals = iptvConfig.stalkerPortals.filter { (it.importVod ?: true) || (it.importSeries ?: true) }
                vodPlaylists.forEach { playlist ->
                    add(
                        StreamProviderItem(
                            id = "iptv_playlist:${playlist.id}",
                            rawProviderKeys = listOf(IptvVodSourceIds.XTREAM, "iptv_vod:${playlist.id}"),
                            displayName = playlist.name.ifBlank { "IPTV Playlist" },
                            subtitle = "M3U / Xtream VOD",
                            type = StreamIntegrationType.IPTV_VOD,
                            isEnabled = playlist.enabled,
                            targetConfigId = "iptv_playlist:${playlist.id}"
                        )
                    )
                }
                vodPortals.forEach { portal ->
                    add(
                        StreamProviderItem(
                            id = "iptv_stalker:${portal.id}",
                            rawProviderKeys = listOf(IptvVodSourceIds.STALKER, "iptv_vod:${portal.id}"),
                            displayName = portal.name.ifBlank { "Stalker Portal" },
                            subtitle = "Stalker Portal VOD",
                            type = StreamIntegrationType.IPTV_VOD,
                            isEnabled = portal.enabled,
                            targetConfigId = "iptv_stalker:${portal.id}"
                        )
                    )
                }
            }
        }
    }

    /**
     * Observes all individual stream provider items sorted according to the profile's custom order,
     * with per-provider enabled overrides and up/down movement capabilities.
     */
    fun observeProviderItems(): Flow<List<StreamProviderItem>> {
        return combine(
            profileManager.activeProfileId,
            context.settingsDataStore.data,
            observeRawProviderItems()
        ) { profileId, prefs, rawItems ->
            val savedOrderRaw = prefs[providerOrderKey(profileId)].orEmpty()
            val parsedOrderIds = if (savedOrderRaw.isNotBlank()) {
                savedOrderRaw.split(",").map { it.trim() }.filter { it.isNotBlank() }
            } else {
                emptyList()
            }

            val orderedItems = if (parsedOrderIds.isNotEmpty()) {
                val itemMap = rawItems.associateBy { it.id }
                val prioritized = parsedOrderIds.mapNotNull { itemMap[it] }
                val remaining = rawItems.filter { it.id !in parsedOrderIds }
                prioritized + remaining
            } else {
                val macroOrder = prefs[orderKey(profileId)]?.split(",")?.mapNotNull { StreamIntegrationType.fromId(it) } ?: emptyList()
                if (macroOrder.isNotEmpty()) {
                    val fullMacroOrder = (macroOrder + StreamIntegrationType.DEFAULT_ORDER).distinct()
                    rawItems.sortedBy { fullMacroOrder.indexOf(it.type) }
                } else {
                    rawItems
                }
            }

            val total = orderedItems.size
            orderedItems.mapIndexed { index, item ->
                val enabledOverride = prefs[providerEnabledKey(profileId, item.id)]
                val finalEnabled = enabledOverride ?: run {
                    if (item.id.endsWith("_root") || item.id == "telegram") {
                        prefs[enabledKey(profileId, item.type)] ?: item.isEnabled
                    } else {
                        item.isEnabled
                    }
                }

                item.copy(
                    isEnabled = finalEnabled && prefs[enabledKey(profileId, item.type)] != false,
                    priority = index + 1,
                    canMoveUp = index > 0,
                    canMoveDown = index < total - 1
                )
            }
        }
    }

    suspend fun getProviderItems(): List<StreamProviderItem> {
        return observeProviderItems().first()
    }

    suspend fun enabledProviderIds(type: StreamIntegrationType, selectedId: String? = null): Set<String> {
        val profileId = profileManager.getProfileId()
        if (context.settingsDataStore.data.first()[enabledKey(profileId, type)] == false) return emptySet()
        return getProviderItems().filter {
            it.type == type && it.isEnabled && (selectedId == null || it.id == selectedId)
        }.map { it.id }.toSet()
    }

    suspend fun moveProviderItemUp(id: String): Boolean {
        return moveProviderItem(id, direction = -1)
    }

    suspend fun moveProviderItemDown(id: String): Boolean {
        return moveProviderItem(id, direction = 1)
    }

    /** Moves a source several places at once, in one write - for a row dragged on the phone. */
    suspend fun moveProviderItemBy(id: String, steps: Int): Boolean {
        return moveProviderItem(id, direction = steps)
    }

    private suspend fun moveProviderItem(id: String, direction: Int): Boolean {
        if (direction == 0) return false
        val profileId = profileManager.getProfileId()
        val currentItems = observeProviderItems().first()
        val currentIndex = currentItems.indexOfFirst { it.id == id }
        if (currentIndex < 0) return false

        val targetIndex = (currentIndex + direction).coerceIn(0, currentItems.lastIndex)
        if (targetIndex == currentIndex) return false

        val mutableList = currentItems.map { it.id }.toMutableList()
        val item = mutableList.removeAt(currentIndex)
        mutableList.add(targetIndex, item)

        val serialized = mutableList.joinToString(",")
        context.settingsDataStore.edit { prefs ->
            prefs[providerOrderKey(profileId)] = serialized
        }
        invalidationBus.markDirty(
            scope = CloudSyncScope.PROFILE_SETTINGS,
            profileId = profileId,
            reason = "stream_provider_moved_$id"
        )
        return true
    }

    suspend fun toggleProviderItem(id: String): Boolean {
        val profileId = profileManager.getProfileId()
        val currentItems = observeProviderItems().first()
        val targetItem = currentItems.firstOrNull { it.id == id } ?: return false

        val newEnabled = !targetItem.isEnabled

        context.settingsDataStore.edit { prefs ->
            prefs[providerEnabledKey(profileId, id)] = newEnabled
            if (id.endsWith("_root") || id == "telegram") {
                prefs[enabledKey(profileId, targetItem.type)] = newEnabled
            }
        }

        when {
            id.startsWith("stremio:") -> {
                val addonId = id.removePrefix("stremio:")
                runCatching { streamRepositoryProvider.get().toggleAddon(addonId) }
            }
            id.startsWith("plugin_repo:") -> {
                val repoId = id.removePrefix("plugin_repo:")
                runCatching { pluginManagerProvider.get().toggleAllScrapersForRepo(repoId, newEnabled) }
            }
        }

        invalidationBus.markDirty(
            scope = CloudSyncScope.PROFILE_SETTINGS,
            profileId = profileId,
            reason = "stream_provider_toggled_$id"
        )
        return newEnabled
    }

    /**
     * Resolves the unified priority list of provider/addon IDs combining all active integrations.
     * Higher priority integrations/providers place their IDs earlier in this list.
     */
    fun getUnifiedSourceOrderedIds(): Flow<List<String>> {
        return observeProviderItems().map { items ->
            buildList {
                items.forEach { item ->
                    if (item.isEnabled) {
                        item.rawProviderKeys.forEach { key ->
                            if (key.isNotBlank()) add(key)
                        }
                    }
                }
            }.distinct()
        }
    }

    suspend fun getUnifiedSourceOrderedIdsSync(): List<String> {
        return getUnifiedSourceOrderedIds().first()
    }

    /**
     * Observes the active stream search mode (PARALLEL vs. SEQUENTIAL waterfall).
     */
    fun observeSearchMode(): Flow<StreamSearchMode> {
        return profileManager.activeProfileId.flatMapLatest { profileId ->
            context.effectiveSettingsDataStore.data.map { prefs ->
                StreamSearchMode.fromId(prefs[searchModeKey(profileId)])
            }
        }
    }

    suspend fun getSearchMode(): StreamSearchMode {
        val profileId = profileManager.getProfileId()
        val prefs = context.effectiveSettingsDataStore.data.first()
        return StreamSearchMode.fromId(prefs[searchModeKey(profileId)])
    }

    suspend fun setSearchMode(mode: StreamSearchMode) {
        val profileId = profileManager.getProfileId()
        context.effectiveSettingsDataStore.edit { prefs ->
            prefs[searchModeKey(profileId)] = mode.id
        }
        invalidationBus.markDirty(
            scope = CloudSyncScope.PROFILE_SETTINGS,
            profileId = profileId,
            reason = "stream_search_mode_changed_${mode.id}"
        )
    }

    fun exportCloudSettingsForProfile(
        prefs: androidx.datastore.preferences.core.Preferences,
        profileId: String
    ): StreamIntegrationProfileCloudState {
        val searchMode = StreamSearchMode.fromId(prefs[searchModeKey(profileId)]).id
        val customOrder = prefs[providerOrderKey(profileId)].orEmpty()
        val macroOrder = prefs[orderKey(profileId)].orEmpty()

        val macroEnabled = buildMap {
            StreamIntegrationType.entries.forEach { type ->
                prefs[enabledKey(profileId, type)]?.let { put(type.id, it) }
            }
        }

        val providerEnabled = buildMap {
            val prefix = "profile_${profileId}_$STREAM_PROVIDER_ENABLED_PREFIX"
            prefs.asMap().forEach { (key, value) ->
                if (key.name.startsWith(prefix) && value is Boolean) {
                    val providerId = key.name.removePrefix(prefix)
                    put(providerId, value)
                }
            }
        }

        return StreamIntegrationProfileCloudState(
            searchMode = searchMode,
            customProviderOrder = customOrder,
            macroCategoryOrder = macroOrder,
            macroEnabledMap = macroEnabled,
            providerEnabledMap = providerEnabled
        )
    }

    fun applyCloudSettingsForProfile(
        prefs: androidx.datastore.preferences.core.MutablePreferences,
        profileId: String,
        state: StreamIntegrationProfileCloudState
    ) {
        if (state.searchMode.isNotBlank()) {
            prefs[searchModeKey(profileId)] = StreamSearchMode.fromId(state.searchMode).id
        }
        if (state.customProviderOrder.isNotBlank()) {
            prefs[providerOrderKey(profileId)] = state.customProviderOrder
        }
        if (state.macroCategoryOrder.isNotBlank()) {
            prefs[orderKey(profileId)] = state.macroCategoryOrder
        }
        state.macroEnabledMap.forEach { (typeId, isEnabled) ->
            StreamIntegrationType.fromId(typeId)?.let { type ->
                prefs[enabledKey(profileId, type)] = isEnabled
            }
        }
        state.providerEnabledMap.forEach { (providerId, isEnabled) ->
            prefs[providerEnabledKey(profileId, providerId)] = isEnabled
        }
    }
}

data class StreamIntegrationProfileCloudState(
    val searchMode: String = StreamSearchMode.PARALLEL.id,
    val customProviderOrder: String = "",
    val macroCategoryOrder: String = "",
    val macroEnabledMap: Map<String, Boolean> = emptyMap(),
    val providerEnabledMap: Map<String, Boolean> = emptyMap()
)
