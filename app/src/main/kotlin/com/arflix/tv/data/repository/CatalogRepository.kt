package com.arflix.tv.data.repository

import android.content.Context
import androidx.annotation.StringRes
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.edit
import com.arflix.tv.data.api.TraktApi
import com.arflix.tv.data.model.Addon
import com.arflix.tv.data.model.AddonCatalog
import com.arflix.tv.data.model.AddonType
import com.arflix.tv.data.model.CatalogConfig
import com.arflix.tv.data.model.CatalogKind
import com.arflix.tv.data.model.CatalogPackManifest
import com.arflix.tv.data.model.CatalogPackItem
import com.arflix.tv.data.model.CollectionGroupKind
import com.arflix.tv.data.model.CollectionSourceConfig
import com.arflix.tv.data.model.CollectionTileShape
import com.arflix.tv.data.model.CatalogSourceType
import com.arflix.tv.data.model.CatalogValidationResult
import com.arflix.tv.data.model.Category
import com.arflix.tv.data.model.SportsAddonCapabilities
import com.arflix.tv.data.repository.HomeServerCatalogCandidate
import com.arflix.tv.R
import com.arflix.tv.util.CatalogUrlParser
import com.arflix.tv.util.Constants
import com.arflix.tv.util.AppLogger
import com.arflix.tv.util.ParsedCatalogUrl
import com.arflix.tv.util.settingsDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import com.arflix.tv.network.OkHttpProvider
import okhttp3.CacheControl
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.net.URI
import java.net.URLDecoder
import java.net.URLEncoder
import java.security.MessageDigest
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Failure of a catalog operation, carrying the string resource instead of a
 * finished message.
 *
 * The repository only has the application context, whose resources follow the
 * system language rather than the language selected in the app, so the text is
 * resolved at the @Composable display point (see SettingsScreen).
 */
class CatalogException(
    @param:StringRes val messageRes: Int,
    val formatArgs: List<Any> = emptyList()
) : Exception()

@Singleton
class CatalogRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val profileManager: ProfileManager,
    private val traktApi: TraktApi,
    private val okHttpClient: OkHttpClient,
    private val invalidationBus: CloudSyncInvalidationBus,
    private val simklListsRepository: com.arflix.tv.data.repository.simkl.SimklListsRepository? = null
) {
    private val bundledPreinstalledCatalogsById by lazy {
        // Imported collections change at runtime, so they must not be frozen into
        // this snapshot (a stale copy would fight ensurePreinstalledDefaults).
        MediaRepository.buildPreinstalledDefaults()
            .filterNot { it.packId?.startsWith(CustomCollections.PACK_ID_PREFIX) == true }
            .associateBy { it.id }
    }

    // Debounce guard: skip syncAddonCatalogs() when addon list hasn't changed.
    // syncAddonCatalogs() iterates every addon manifest, flatMaps catalogs, and
    // diffs against persisted state — all of which is wasted work when the
    // installed addon list is identical between calls.
    @Volatile private var lastSyncedAddonFingerprint: String? = null

    private val bundledPreinstalledCatalogIds by lazy {
        bundledPreinstalledCatalogsById.keys
    }

    private val gson = Gson()
    private fun catalogsKey(profileId: String) = stringPreferencesKey("profile_${profileId}_catalogs_v1")
    private fun hiddenPreinstalledKey(profileId: String) = stringPreferencesKey("profile_${profileId}_hidden_preinstalled_catalogs_v2")
    private fun hiddenAddonKey(profileId: String) = stringPreferencesKey("profile_${profileId}_hidden_addon_catalogs_v1")
    private fun hiddenHomeServerKey(profileId: String) = stringPreferencesKey("profile_${profileId}_hidden_home_server_catalogs_v1")
    private val legacyDefaultKey = stringPreferencesKey("profile_default_catalogs_v1")
    private val legacyGlobalKey = stringPreferencesKey("catalogs_v1")
    private val listType = TypeToken.getParameterized(List::class.java, CatalogConfig::class.java).type
    private val hiddenListType = TypeToken.getParameterized(List::class.java, String::class.java).type

    private fun writeCatalogField(prefs: MutablePreferences, profileId: String, field: String, json: String, stampChange: Boolean = true) {
        val key = catalogFieldKey(profileId, field)
        if (prefs[key] == json) return
        prefs[key] = json
        if (stampChange) CatalogCloudFields.stamp(prefs, profileId, field, json)
    }

    private fun catalogFieldKey(profileId: String, field: String) = when (field) {
        "catalogsByProfile" -> catalogsKey(profileId)
        "hiddenPreinstalledByProfile" -> hiddenPreinstalledKey(profileId)
        "hiddenAddonByProfile" -> hiddenAddonKey(profileId)
        "hiddenHomeServerByProfile" -> hiddenHomeServerKey(profileId)
        else -> error("Unknown catalog field: $field")
    }

    /** Recheck clocks inside the same transaction as the restore, not before a network request. */
    internal suspend fun applyCloudCatalogs(profileId: String, fields: Map<String, String>, timestamps: JSONObject): Boolean {
        var preservedLocal = false
        context.settingsDataStore.edit { prefs ->
            for ((field, json) in fields) {
                if (CatalogCloudFields.isNewerLocal(prefs, profileId, field, timestamps)) {
                    preservedLocal = true
                    continue
                }
                val normalized = if (field == "catalogsByProfile") {
                    gson.toJson(parseCatalogsJson(json).distinctBy { it.id })
                } else {
                    gson.toJson(gson.fromJson<List<String>>(json, hiddenListType).orEmpty().distinct())
                }
                prefs[catalogFieldKey(profileId, field)] = normalized
                CatalogCloudFields.recordRemote(prefs, profileId, field, normalized, timestamps)
            }
        }
        lastSyncedAddonFingerprint = null
        return preservedLocal
    }

    private fun decodeHiddenPreinstalled(profileId: String, prefs: Preferences): Set<String> {
        val raw = prefs[hiddenPreinstalledKey(profileId)]
        if (raw.isNullOrBlank()) return emptySet()
        return try {
            (gson.fromJson<List<String>>(raw, hiddenListType) ?: emptyList())
                .map { it.trim() }
                .filter { it.isNotBlank() }
                .toSet()
                .let(ArvioCollections::preserveDisabledDefaults)
        } catch (e: Exception) {
            AppLogger.e("CatalogRepository", "Error fetching data, returning empty set", e)
            emptySet()
        }
    }

    private fun decodeHiddenAddon(profileId: String, prefs: Preferences): Set<String> {
        val raw = prefs[hiddenAddonKey(profileId)]
        if (raw.isNullOrBlank()) return emptySet()
        return try {
            (gson.fromJson<List<String>>(raw, hiddenListType) ?: emptyList())
                .map { it.trim() }
                .filter { it.isNotBlank() }
                .toSet()
        } catch (e: Exception) {
            AppLogger.e("CatalogRepository", "Error fetching data, returning empty set", e)
            emptySet()
        }
    }

    private fun decodeHiddenHomeServer(profileId: String, prefs: Preferences): Set<String> {
        val raw = prefs[hiddenHomeServerKey(profileId)]
        if (raw.isNullOrBlank()) return emptySet()
        return try {
            (gson.fromJson<List<String>>(raw, hiddenListType) ?: emptyList())
                .map { it.trim() }
                .filter { it.isNotBlank() }
                .toSet()
        } catch (e: Exception) {
            AppLogger.e("CatalogRepository", "Error fetching data, returning empty set", e)
            emptySet()
        }
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    fun observeCatalogs(): Flow<List<CatalogConfig>> {
        return profileManager.activeProfileId
            .flatMapLatest { profileId ->
                context.settingsDataStore.data
                    // Unrelated settings/sync writes must not reparse the entire catalog JSON.
                    .distinctUntilChanged { previous, current ->
                        catalogSettingsUnchanged(profileId, previous, current)
                    }
                    .map { prefs -> readCatalogsFromPrefs(profileId, prefs) }
            }
            .distinctUntilChanged()
            .flowOn(Dispatchers.Default)
    }

    internal fun catalogSettingsUnchanged(profileId: String, previous: Preferences, current: Preferences): Boolean {
        val keys = listOf(catalogsKey(profileId), hiddenPreinstalledKey(profileId),
            hiddenAddonKey(profileId), hiddenHomeServerKey(profileId), legacyDefaultKey, legacyGlobalKey)
        return keys.all { previous[it] == current[it] }
    }

    private suspend fun activeProfileId(): String {
        return profileManager.getProfileIdSync()
            .ifBlank { profileManager.getProfileId() }
            .ifBlank { "default" }
    }

    private suspend fun readCatalogsForActiveProfile(): List<CatalogConfig> {
        val profileId = activeProfileId()
        val prefs = context.settingsDataStore.data.first()
        val primary = sanitizeCollectionCatalogs(
            parseCatalogsJson(prefs[catalogsKey(profileId)]).distinctBy { it.id }
        )
        val resolved = sanitizeCollectionCatalogs(readCatalogsFromPrefs(profileId, prefs))
        // Migrate legacy keys only while the captured profile state is still current.
        if (resolved.isNotEmpty() && resolved != primary) {
            if (!saveCatalogs(resolved, prefs, profileId)) return getCatalogsForProfile(profileId)
        }
        return resolved
    }

    suspend fun getCatalogs(): List<CatalogConfig> {
        return readCatalogsForActiveProfile()
    }

    suspend fun getCatalogsForProfile(profileId: String): List<CatalogConfig> {
        val safeProfileId = profileId.trim().ifBlank { "default" }
        val prefs = context.settingsDataStore.data.first()
        return sanitizeCollectionCatalogs(readCatalogsFromPrefs(safeProfileId, prefs))
    }

    private fun isBundledPreinstalledCatalogId(catalogId: String): Boolean {
        return bundledPreinstalledCatalogIds.contains(catalogId.trim())
    }

    private fun isPreinstalledCatalog(config: CatalogConfig): Boolean {
        return config.isPreinstalled || isBundledPreinstalledCatalogId(config.id)
    }

    private fun refreshBundledPreinstalledCatalog(config: CatalogConfig): CatalogConfig {
        val bundled = bundledPreinstalledCatalogsById[config.id] ?: return config
        val shouldRefresh = config.isPreinstalled ||
            config.sourceType == CatalogSourceType.PREINSTALLED ||
            config.kind == CatalogKind.COLLECTION ||
            config.kind == CatalogKind.COLLECTION_RAIL
        return if (shouldRefresh) bundled else config
    }

    private fun sanitizeCollectionCatalogs(catalogs: List<CatalogConfig>): List<CatalogConfig> {
        return catalogs.mapNotNull { config ->
            if (!CollectionTemplateManifest.isValidCollectionConfig(config)) {
                null
            } else {
                config
            }
        }
    }

    suspend fun getHiddenPreinstalledCatalogIdsForActiveProfile(): List<String> {
        val profileId = activeProfileId()
        val prefs = context.settingsDataStore.data.first()
        return decodeHiddenPreinstalled(profileId, prefs).toList()
    }

    suspend fun getHiddenPreinstalledCatalogIdsForProfile(profileId: String): List<String> {
        val safeProfileId = profileId.trim().ifBlank { "default" }
        val prefs = context.settingsDataStore.data.first()
        return decodeHiddenPreinstalled(safeProfileId, prefs).toList()
    }

    suspend fun setHiddenPreinstalledCatalogIdsForActiveProfile(ids: List<String>) {
        val profileId = activeProfileId()
        val cleaned = ids.map { it.trim() }.filter { it.isNotBlank() }.distinct()
        context.settingsDataStore.edit { prefs ->
            writeCatalogField(prefs, profileId, "hiddenPreinstalledByProfile", gson.toJson(cleaned))
        }
        invalidationBus.markDirty(CloudSyncScope.CATALOGS, profileId, "set hidden preinstalled catalogs")
    }

    suspend fun setHiddenPreinstalledCatalogIdsForProfile(profileId: String, ids: List<String>) {
        val safeProfileId = profileId.trim().ifBlank { "default" }
        val cleaned = ids.map { it.trim() }.filter { it.isNotBlank() }.distinct()
        context.settingsDataStore.edit { prefs ->
            writeCatalogField(prefs, safeProfileId, "hiddenPreinstalledByProfile", gson.toJson(cleaned))
        }
        invalidationBus.markDirty(CloudSyncScope.CATALOGS, safeProfileId, "set hidden preinstalled catalogs")
    }

    suspend fun getHiddenAddonCatalogIdsForActiveProfile(): List<String> {
        val profileId = activeProfileId()
        val prefs = context.settingsDataStore.data.first()
        return decodeHiddenAddon(profileId, prefs).toList()
    }

    suspend fun getHiddenAddonCatalogIdsForProfile(profileId: String): List<String> {
        val safeProfileId = profileId.trim().ifBlank { "default" }
        val prefs = context.settingsDataStore.data.first()
        return decodeHiddenAddon(safeProfileId, prefs).toList()
    }

    suspend fun setHiddenAddonCatalogIdsForActiveProfile(ids: List<String>) {
        val profileId = activeProfileId()
        val cleaned = ids.map { it.trim() }.filter { it.isNotBlank() }.distinct()
        context.settingsDataStore.edit { prefs ->
            writeCatalogField(prefs, profileId, "hiddenAddonByProfile", gson.toJson(cleaned))
        }
        invalidationBus.markDirty(CloudSyncScope.CATALOGS, profileId, "set hidden addon catalogs")
    }

    suspend fun setHiddenAddonCatalogIdsForProfile(profileId: String, ids: List<String>) {
        val safeProfileId = profileId.trim().ifBlank { "default" }
        val cleaned = ids.map { it.trim() }.filter { it.isNotBlank() }.distinct()
        context.settingsDataStore.edit { prefs ->
            writeCatalogField(prefs, safeProfileId, "hiddenAddonByProfile", gson.toJson(cleaned))
        }
        invalidationBus.markDirty(CloudSyncScope.CATALOGS, safeProfileId, "set hidden addon catalogs")
    }

    suspend fun getHiddenHomeServerCatalogIdsForProfile(profileId: String): List<String> {
        val safeProfileId = profileId.trim().ifBlank { "default" }
        val prefs = context.settingsDataStore.data.first()
        return decodeHiddenHomeServer(safeProfileId, prefs).toList()
    }

    suspend fun setHiddenHomeServerCatalogIdsForProfile(profileId: String, ids: List<String>) {
        val safeProfileId = profileId.trim().ifBlank { "default" }
        val cleaned = ids.map { it.trim() }.filter { it.isNotBlank() }.distinct()
        context.settingsDataStore.edit { prefs ->
            writeCatalogField(prefs, safeProfileId, "hiddenHomeServerByProfile", gson.toJson(cleaned))
        }
        invalidationBus.markDirty(CloudSyncScope.CATALOGS, safeProfileId, "set hidden home server catalogs")
    }

    private suspend fun saveCatalogs(catalogs: List<CatalogConfig>, expected: Preferences? = null, targetProfileId: String? = null): Boolean {
        val profileId = targetProfileId ?: activeProfileId()
        val sanitized = catalogs
            .distinctBy { it.id }
            .mapNotNull { normalizeCatalogConfig(it) }
        var saved = false
        context.settingsDataStore.edit { prefs ->
            // A background refresh must not overwrite edits made since it read the list.
            if (expected != null && CatalogCloudFields.fields.any {
                val key = catalogFieldKey(profileId, it)
                prefs[key] != expected[key]
            }) return@edit
            // Initial automatic setup is not a user edit and must not outrank cloud choices.
            val stampChange = expected == null || CatalogCloudFields.hasBaseline(prefs, profileId, "catalogsByProfile")
            writeCatalogField(prefs, profileId, "catalogsByProfile", gson.toJson(sanitized), stampChange)
            saved = true
        }
        if (saved) invalidationBus.markDirty(CloudSyncScope.CATALOGS, profileId, "save catalogs")
        return saved
    }

    suspend fun replaceCatalogsForProfile(profileId: String, catalogs: List<CatalogConfig>) {
        val safeProfileId = profileId.trim().ifBlank { "default" }
        val sanitized = catalogs
            .distinctBy { it.id }
            .mapNotNull { normalizeCatalogConfig(it) }
        context.settingsDataStore.edit { prefs ->
            writeCatalogField(prefs, safeProfileId, "catalogsByProfile", gson.toJson(sanitized))
        }
        invalidationBus.markDirty(CloudSyncScope.CATALOGS, safeProfileId, "replace catalogs")
    }

    suspend fun ensurePreinstalled(defaultCategories: List<Category>): List<CatalogConfig> {
        val defaultPreinstalled = defaultCategories.map {
            CatalogConfig(
                id = it.id,
                title = it.title,
                sourceType = CatalogSourceType.PREINSTALLED,
                isPreinstalled = true
            )
        }
        return ensurePreinstalledDefaults(defaultPreinstalled)
    }

    suspend fun ensurePreinstalledDefaults(defaultPreinstalled: List<CatalogConfig>): List<CatalogConfig> {
        val profileId = activeProfileId()
        val prefs = context.settingsDataStore.data.first()
        val hidden = decodeHiddenPreinstalled(profileId, prefs)
        val effectiveDefaults = if (hidden.isEmpty()) {
            defaultPreinstalled
        } else {
            defaultPreinstalled.filterNot { it.id in hidden }
        }

        val defaultIds = defaultPreinstalled.map { it.id }.toSet()
        val existing = readCatalogsFromPrefs(profileId, prefs).mapNotNull { cfg ->
            if ((cfg.kind == CatalogKind.COLLECTION || cfg.kind == CatalogKind.COLLECTION_RAIL) &&
                !defaultIds.contains(cfg.id) && !CustomCollections.isCustom(cfg)
            ) {
                return@mapNotNull null
            }
            // Only treat as custom if it's NOT in the preinstalled defaults list
            val looksCustom = !defaultIds.contains(cfg.id) && (
                cfg.id.startsWith("custom_") ||
                !cfg.sourceUrl.isNullOrBlank() ||
                !cfg.sourceRef.isNullOrBlank()
            )
            if (looksCustom) {
                cfg.copy(
                    isPreinstalled = false,
                    sourceType = parseSourceTypeCompat(
                        raw = cfg.sourceType.name,
                        sourceUrl = cfg.sourceUrl,
                        sourceRef = cfg.sourceRef
                    )
                )
            } else {
                cfg
            }
        }
        val defaultMap = effectiveDefaults.associateBy { it.id }

        val merged = if (existing.isEmpty()) {
            effectiveDefaults
        } else {
            val kept = existing.mapNotNull { config ->
                if (config.isPreinstalled) {
                    val defaultCfg = defaultMap[config.id] ?: return@mapNotNull null
                    // Preserve user-renamed titles: if the user changed the title
                    // from a previous default, keep their custom title.
                    if (config.title != defaultCfg.title && config.title.isNotBlank()) {
                        defaultCfg.copy(title = config.title)
                    } else {
                        defaultCfg
                    }
                } else {
                    config
                }
            }.toMutableList()

            // Insert missing preinstalled catalogs at their intended default position
            // so new catalogs (like "Favorite TV") appear where they were defined,
            // but only re-sort when there are actually new catalogs to add.
            val missingPreinstalled = effectiveDefaults.filter { pre ->
                kept.none { it.id == pre.id }
            }
            if (missingPreinstalled.isNotEmpty()) {
                val defaultOrder = effectiveDefaults.mapIndexed { idx, cfg -> cfg.id to idx }.toMap()
                for (missing in missingPreinstalled) {
                    val targetIdx = defaultOrder[missing.id] ?: kept.size
                    // Insert after the last kept entry whose default index < targetIdx
                    var insertAt = 0
                    for (i in kept.indices) {
                        val keptIdx = defaultOrder[kept[i].id]
                        if (keptIdx != null && keptIdx < targetIdx) {
                            insertAt = i + 1
                        }
                    }
                    kept.add(insertAt, missing)
                }
            }
            migrateLegacyCollectionBlockOrder(
                current = kept,
                desiredDefaults = effectiveDefaults
            )
        }

        if (existing != merged) {
            if (!saveCatalogs(merged, prefs, profileId)) return getCatalogsForProfile(profileId)
        }
        return merged
    }

    suspend fun syncAddonCatalogs(addons: List<Addon>): Boolean {
        val profileId = activeProfileId()
        // Debounce: skip if addon manifest state hasn't changed since last sync.
        // Includes profile ID + fields that affect the sync outcome so that
        // switching profiles, toggling addon enable-state, or manifest catalog
        // changes always trigger a fresh sync.
        val fingerprint = buildAddonFingerprint(profileId, addons)
        if (fingerprint == lastSyncedAddonFingerprint) return false
        lastSyncedAddonFingerprint = fingerprint

        val prefs = context.settingsDataStore.data.first()
        val hiddenAddonIds = decodeHiddenAddon(profileId, prefs)
        val supportedCatalogs = addons
            .asSequence()
            .filter { addon ->
                    addon.isInstalled &&
                    addon.isEnabled &&
                    addon.type != AddonType.SUBTITLE &&
                    !SportsAddonCapabilities.isSportsLiveTvAddon(addon) &&
                    !addon.url.isNullOrBlank() &&
                    !addon.manifest?.catalogs.isNullOrEmpty()
            }
            .flatMap { addon ->
                addon.manifest?.catalogs.orEmpty().asSequence()
                    .mapNotNull { catalog -> buildAddonCatalogConfig(addon, catalog) }
            }
            // Drop entries the user has explicitly deleted. Without this, every
            // sync would re-add them from the addon manifest on the very next
            // installedAddons emission, making addon catalog deletion impossible.
            .filterNot { it.id in hiddenAddonIds }
            .distinctBy { it.id }
            .toList()

        val current = readCatalogsFromPrefs(profileId, prefs).toMutableList()
        val desiredById = supportedCatalogs.associateBy { it.id }
        var changed = false

        val beforeRemovalSize = current.size
        current.removeAll { cfg ->
            cfg.sourceType == CatalogSourceType.ADDON && !desiredById.containsKey(cfg.id)
        }
        if (current.size != beforeRemovalSize) {
            changed = true
        }

        current.indices.forEach { index ->
            val existing = current[index]
            val desired = desiredById[existing.id] ?: return@forEach
            val merged = existing.copy(
                title = desired.title,
                sourceType = CatalogSourceType.ADDON,
                sourceRef = desired.sourceRef,
                isPreinstalled = false,
                addonId = desired.addonId,
                addonCatalogType = desired.addonCatalogType,
                addonCatalogId = desired.addonCatalogId,
                addonName = desired.addonName
            )
            if (merged != existing) {
                current[index] = merged
                changed = true
            }
        }

        val existingIds = current.map { it.id }.toHashSet()
        val missing = supportedCatalogs.filterNot { existingIds.contains(it.id) }
        if (missing.isNotEmpty()) {
            // New libraries follow existing catalogs; never displace the trending defaults.
            current.addAll(missing)
            changed = true
        }

        if (changed) {
            if (!saveCatalogs(current, prefs, profileId)) {
                lastSyncedAddonFingerprint = null
                return false
            }
        }
        return changed
    }

    /**
     * Builds a fingerprint for [syncAddonCatalogs] debouncing that captures all fields
     * affecting the sync outcome. Unlike the previous ID-only fingerprint, this includes:
     * - Active profile ID (so profile switches always trigger a fresh sync)
     * - Addon installed/enabled state, type, and URL
     * - Manifest catalog contents (catalog type + ID per addon)
     *
     * This ensures that toggling an addon, switching profiles, or manifest catalog
     * changes never leave stale/missing catalogs.
     */
    private fun buildAddonFingerprint(profileId: String, addons: List<Addon>): String {
        return buildString {
            append(profileId)
            append('|')
            addons.forEach { addon ->
                append(addon.id)
                append(':')
                append(if (addon.isInstalled) '1' else '0')
                append(if (addon.isEnabled) '1' else '0')
                append(addon.type.name)
                append(addon.url ?: "")
                addon.manifest?.catalogs?.forEach { catalog ->
                    append('[')
                    append(catalog.type)
                    append(':')
                    append(catalog.id)
                    append(']')
                }
                append(',')
            }
        }
    }

    suspend fun syncHomeServerCatalogs(candidates: List<HomeServerCatalogCandidate>): Boolean {
        val profileId = activeProfileId()
        val prefs = context.settingsDataStore.data.first()
        val hiddenHomeServerIds = decodeHiddenHomeServer(profileId, prefs)
        val desiredCatalogs = candidates
            .filter { it.sourceRef.isNotBlank() && it.title.isNotBlank() }
            .map { candidate ->
                val stableId = "home_server_${sha256Short(candidate.sourceRef)}"
                stableId to candidate
            }
            .filterNot { (stableId, _) -> stableId in hiddenHomeServerIds }
            .distinctBy { (stableId, _) -> stableId }
            .map { (stableId, candidate) ->
                candidate to CatalogConfig(
                    id = stableId,
                    title = candidate.title,
                    sourceType = CatalogSourceType.HOME_SERVER,
                    sourceRef = candidate.sourceRef,
                    isPreinstalled = false
                )
            }
        val desiredById = desiredCatalogs.associate { (candidate, config) -> config.id to config }
        val candidateById = desiredCatalogs.associate { (candidate, config) -> config.id to candidate }

        val current = readCatalogsFromPrefs(profileId, prefs).toMutableList()
        var changed = false

        val beforeRemovalSize = current.size
        current.removeAll { cfg ->
            cfg.sourceType == CatalogSourceType.HOME_SERVER && !desiredById.containsKey(cfg.id)
        }
        if (current.size != beforeRemovalSize) changed = true

        current.indices.forEach { index ->
            val existing = current[index]
            val desired = desiredById[existing.id] ?: return@forEach
            val candidate = candidateById[existing.id]
            val shouldRefreshTitle = existing.title.isBlank() ||
                existing.title == candidate?.collectionName ||
                existing.title == candidate?.serverName
            val merged = existing.copy(
                title = if (shouldRefreshTitle) desired.title else existing.title,
                sourceType = CatalogSourceType.HOME_SERVER,
                sourceRef = desired.sourceRef,
                sourceUrl = null,
                isPreinstalled = false
            )
            if (merged != existing) {
                current[index] = merged
                changed = true
            }
        }

        val existingIds = current.map { it.id }.toHashSet()
        val missing = desiredCatalogs.map { (_, config) -> config }.filterNot { it.id in existingIds }
        if (missing.isNotEmpty()) {
            current.addAll(0, missing)
            changed = true
        }

        if (changed) return saveCatalogs(current, prefs, profileId)
        return changed
    }

    private fun buildAddonCatalogConfig(
        addon: Addon,
        catalog: AddonCatalog
    ): CatalogConfig? {
        if (com.arflix.tv.data.model.SportsAddonCapabilities.isSportsLiveTvAddon(addon) &&
            com.arflix.tv.data.model.SportsAddonCapabilities.isSportsCatalog(catalog)) return null
        val normalizedType = normalizeAddonCatalogType(catalog.type) ?: return null
        val catalogId = catalog.id.trim().takeIf { it.isNotBlank() } ?: return null
        val hasRequiredExtras = catalog.extra
            ?.any { extra -> extra.isRequired && !extra.name.equals("skip", ignoreCase = true) }
            ?: false
        if (hasRequiredExtras) return null

        val addonId = addon.id.trim().takeIf { it.isNotBlank() } ?: return null
        val title = catalog.name.trim().takeIf { it.isNotBlank() } ?: catalogId.toDisplayTitle()
        val hashInput = "$addonId|$normalizedType|$catalogId"
        val stableId = "addon_${sha256Short(hashInput)}"

        return CatalogConfig(
            id = stableId,
            title = title,
            sourceType = CatalogSourceType.ADDON,
            sourceRef = buildAddonSourceRef(
                addonId = addonId,
                catalogType = normalizedType,
                catalogId = catalogId
            ),
            isPreinstalled = false,
            addonId = addonId,
            addonCatalogType = normalizedType,
            addonCatalogId = catalogId,
            addonName = addon.name
        )
    }

    // Addons may declare any catalog type (AIOMetadata's "anime"/"all"/custom display
    // types, "Podcasts", debrid "other"). Unknown types keep their spelling: the addon
    // routes /catalog/{type}/ by it, case included.
    private fun normalizeAddonCatalogType(rawType: String?): String? {
        val trimmed = rawType?.trim()?.takeIf { it.isNotBlank() } ?: return null
        return when (trimmed.lowercase()) {
            "movie" -> "movie"
            "series" -> "series"
            "tv" -> "tv"
            "show" -> "show"
            "shows" -> "shows"
            else -> trimmed
        }
    }

    private fun buildAddonSourceRef(addonId: String, catalogType: String, catalogId: String): String {
        return "$ADDON_SOURCE_REF_PREFIX" +
            "${urlEncode(addonId)}|" +
            "${urlEncode(catalogType)}|" +
            urlEncode(catalogId)
    }

    private fun parseAddonSourceRef(sourceRef: String?): Triple<String, String, String>? {
        val value = sourceRef?.trim().orEmpty()
        if (!value.startsWith(ADDON_SOURCE_REF_PREFIX)) return null
        val payload = value.removePrefix(ADDON_SOURCE_REF_PREFIX)
        val parts = payload.split("|")
        if (parts.size != 3) return null
        val addonId = urlDecode(parts[0]).trim()
        val catalogType = normalizeAddonCatalogType(urlDecode(parts[1]))
        val catalogId = urlDecode(parts[2]).trim()
        if (addonId.isBlank() || catalogType == null || catalogId.isBlank()) return null
        return Triple(addonId, catalogType, catalogId)
    }

    private fun urlEncode(value: String): String = URLEncoder.encode(value, "UTF-8")
    private fun urlDecode(value: String): String = URLDecoder.decode(value, "UTF-8")

    private fun sha256Short(value: String): String {
        val digest = MessageDigest.getInstance("SHA-256")
            .digest(value.toByteArray())
        return digest.take(8).joinToString("") { b -> "%02x".format(b) }
    }

    private fun migrateLegacyCollectionBlockOrder(
        current: List<CatalogConfig>,
        desiredDefaults: List<CatalogConfig>
    ): List<CatalogConfig> {
        // This migration used to reorder preinstalled catalogs back to their default
        // positions whenever the condition fired. The condition (collections trailing
        // after trending_anime) fired on *every* Settings open after the user had
        // reordered anything, resetting their custom order. Migration is now a no-op.
        return current
    }

    private fun isVisibleCatalogInSettings(config: CatalogConfig): Boolean {
        if (config.kind == CatalogKind.COLLECTION) return false
        if (config.kind == CatalogKind.COLLECTION_RAIL) {
            return CollectionTemplateManifest.isValidCollectionConfig(config)
        }
        return true
    }

    suspend fun fetchCatalogPackManifest(packUrl: String): Result<CatalogPackManifest> {
        val trimmed = packUrl.trim()
        if (!trimmed.startsWith("http://", ignoreCase = true) && !trimmed.startsWith("https://", ignoreCase = true)) {
            return Result.failure(CatalogException(R.string.catalog_pack_invalid_url_scheme))
        }

        val json = fetchUrl(trimmed, fresh = true)
            ?: return Result.failure(CatalogException(R.string.catalog_pack_fetch_failed))
        val manifest = try {
            val type = object : com.google.gson.reflect.TypeToken<CatalogPackManifest>() {}.type
            gson.fromJson<CatalogPackManifest>(json, type)
        } catch (e: Exception) {
            null
        } ?: return Result.failure(CatalogException(R.string.catalog_pack_parse_failed))

        if (manifest.id.isNullOrBlank() || manifest.name.isNullOrBlank()) {
            return Result.failure(CatalogException(R.string.catalog_pack_missing_id_or_name))
        }
        val cats = manifest.catalogs
        if (cats.isNullOrEmpty()) {
            return Result.failure(CatalogException(R.string.catalog_pack_no_catalogs))
        }
        val normalizedUrls = mutableSetOf<String>()
        for (item in cats) {
            if (item.name.isNullOrBlank() || item.url.isNullOrBlank()) {
                return Result.failure(CatalogException(R.string.catalog_pack_item_incomplete))
            }
            val normalized = CatalogUrlParser.normalize(item.url).lowercase()
            if (!normalizedUrls.add(normalized)) {
                return Result.failure(CatalogException(R.string.catalog_pack_duplicate_url, listOf(normalized)))
            }
        }
        return Result.success(manifest)
    }

    suspend fun addCatalogPack(packUrl: String, manifest: CatalogPackManifest? = null): Result<CatalogPackManifest> {
        val finalManifest = if (manifest != null) {
            manifest
        } else {
            val manifestResult = fetchCatalogPackManifest(packUrl)
            if (manifestResult.isFailure) return manifestResult
            manifestResult.getOrThrow()
        }

        val current = getCatalogs().toMutableList()
        val addedConfigs = mutableListOf<CatalogConfig>()

        val manifestId = finalManifest.id ?: return Result.failure(CatalogException(R.string.catalog_pack_manifest_missing_id))
        val manifestName = finalManifest.name ?: return Result.failure(CatalogException(R.string.catalog_pack_manifest_missing_name))
        val manifestCatalogs = finalManifest.catalogs ?: return Result.failure(CatalogException(R.string.catalog_pack_manifest_missing_catalogs))

        val normalizedPackUrl = CatalogUrlParser.normalize(packUrl).lowercase()
        val derivedPackId = "pack_${sha256Short(normalizedPackUrl + "|" + manifestId)}"

        for (item in manifestCatalogs) {
            val itemUrl = item.url ?: continue
            val validation = validateCatalogUrl(itemUrl)
            if (!validation.isValid || validation.normalizedUrl == null || validation.sourceType == null) {
                continue
            }
            val normalizedUrl = validation.normalizedUrl
            val sourceType = validation.sourceType

            // Skip if this URL is already added
            if (current.any { it.sourceUrl.equals(normalizedUrl, ignoreCase = true) || (sourceType == CatalogSourceType.SIMKL && it.sourceRef == "simkl_list:${CatalogUrlParser.parseSimkl(normalizedUrl)?.id}") }) {
                continue
            }

            val resolved = try { resolveMetadata(normalizedUrl, sourceType) } catch (e: Exception) {
                if (e is kotlinx.coroutines.CancellationException) throw e
                return Result.failure(e)
            }
                ?: fallbackMetadata(normalizedUrl, sourceType)
                ?: continue

            val stableId = "custom_${sha256Short(derivedPackId + "|" + normalizedUrl)}"
            val newCatalog = CatalogConfig(
                id = stableId,
                title = item.name?.trim()?.ifBlank { resolved.title } ?: resolved.title,
                sourceType = sourceType,
                sourceUrl = normalizedUrl,
                sourceRef = resolved.sourceRef,
                isPreinstalled = false,
                packId = derivedPackId,
                packName = manifestName
            )
            addedConfigs.add(newCatalog)
        }

        if (addedConfigs.isEmpty()) {
            return Result.failure(CatalogException(R.string.catalog_pack_nothing_to_import))
        }

        current.addAll(0, addedConfigs)
        saveCatalogs(current)
        return Result.success(finalManifest)
    }

    /**
     * Installs a collections document (Nuvio export format or ARVIO wrapper) from a
     * URL or pasted JSON. Returns null when the input is not a collections document,
     * so the caller can fall back to the regular catalog-pack flow.
     */
    suspend fun importCollections(input: String): Result<Pair<String, Int>>? {
        val targetProfileId = activeProfileId()
        val trimmed = input.trim()
        val isRawJson = trimmed.startsWith("[") || trimmed.startsWith("{")
        val url = if (isRawJson) null else CatalogUrlParser.normalize(trimmed)
        val json = if (isRawJson) {
            trimmed
        } else {
            fetchUrl(url ?: return null, fresh = true) ?: return if (url.endsWith(".json", ignoreCase = true)) {
                Result.failure(CatalogException(R.string.catalog_pack_fetch_failed))
            } else {
                null
            }
        }
        if (!CustomCollections.looksLikeCollections(json)) {
            return if (isRawJson) Result.failure(CatalogException(R.string.catalog_collections_invalid)) else null
        }
        val result = CustomCollections.parse(json, url)
        if (result.isFailure) return Result.failure(CatalogException(R.string.catalog_collections_invalid))
        val rails = result.getOrThrow()
        val imported = CustomCollections.catalogs(rails)
        val current = getCatalogsForProfile(targetProfileId)
        replaceCatalogsForProfile(targetProfileId, CustomCollections.merge(current, imported))
        return Result.success(rails.first().packName to rails.size)
    }

    private fun builtInCollectionIds(): Set<String> = MediaRepository.buildPreinstalledDefaults()
        .filter { it.kind == CatalogKind.COLLECTION_RAIL || it.kind == CatalogKind.COLLECTION }.map { it.id }.toSet()

    suspend fun isBuiltInCollectionsEnabled(): Boolean =
        !getHiddenPreinstalledCatalogIdsForActiveProfile().containsAll(builtInCollectionIds())

    /** Preserve the global choice when future updates add built-in collections. */
    suspend fun setBuiltInCollectionsEnabled(enabled: Boolean) {
        val ids = builtInCollectionIds()
        val hidden = getHiddenPreinstalledCatalogIdsForActiveProfile().toSet()
        setHiddenPreinstalledCatalogIdsForActiveProfile(
            (if (enabled) hidden - ids - ArvioCollections.DISABLED_MARKER
             else hidden + ids + ArvioCollections.DISABLED_MARKER).toList()
        )
        ensurePreinstalledDefaults(MediaRepository.buildPreinstalledDefaults())
    }

    suspend fun removeCatalogPack(packId: String): Result<Unit> {
        return removeCatalogsMatching(
            CatalogException(R.string.catalog_pack_none_for_pack, listOf(packId))
        ) { it.packId == packId }
    }

    suspend fun addCustomCatalog(rawUrl: String): Result<CatalogConfig> {
        val targetProfileId = profileManager.getProfileIdSync()
        val validation = validateCatalogUrl(rawUrl)
        if (!validation.isValid || validation.normalizedUrl == null || validation.sourceType == null) {
            return Result.failure(CatalogException(validation.errorRes ?: R.string.catalog_url_invalid))
        }

        val normalizedUrl = validation.normalizedUrl
        val sourceType = validation.sourceType
        val resolved = try { resolveMetadata(normalizedUrl, sourceType) } catch (e: Exception) {
            if (e is kotlinx.coroutines.CancellationException) throw e
            return Result.failure(e)
        }
            ?: fallbackMetadata(normalizedUrl, sourceType)
            ?: return Result.failure(CatalogException(R.string.catalog_failed_read_metadata))

        if (targetProfileId != profileManager.getProfileIdSync()) return Result.failure(IllegalStateException("Profile changed while adding the catalogue. Please try again."))
        val current = getCatalogsForProfile(targetProfileId).toMutableList()
        if (current.any { it.sourceUrl.equals(normalizedUrl, ignoreCase = true) || (sourceType == CatalogSourceType.SIMKL && it.sourceRef == "simkl_list:${CatalogUrlParser.parseSimkl(normalizedUrl)?.id}") }) {
            return Result.failure(CatalogException(R.string.catalog_already_added))
        }

        val newCatalog = CatalogConfig(
            id = "custom_${System.currentTimeMillis()}",
            title = resolved.title,
            sourceType = sourceType,
            sourceUrl = normalizedUrl,
            sourceRef = resolved.sourceRef,
            isPreinstalled = false
        )
        current.add(0, newCatalog)
        saveCatalogs(current, targetProfileId = targetProfileId)
        return Result.success(newCatalog)
    }

    suspend fun updateCustomCatalog(catalogId: String, rawUrl: String): Result<CatalogConfig> {
        val targetProfileId = profileManager.getProfileIdSync()
        val current = getCatalogsForProfile(targetProfileId).toMutableList()
        val index = current.indexOfFirst { it.id == catalogId }
        if (index < 0) return Result.failure(CatalogException(R.string.catalog_not_found))
        val existing = current[index]
        if (existing.isPreinstalled) {
            return Result.failure(CatalogException(R.string.catalog_preinstalled_no_edit))
        }

        val validation = validateCatalogUrl(rawUrl)
        if (!validation.isValid || validation.normalizedUrl == null || validation.sourceType == null) {
            return Result.failure(CatalogException(validation.errorRes ?: R.string.catalog_url_invalid))
        }

        val normalizedUrl = validation.normalizedUrl
        if (current.any { it.id != catalogId && (it.sourceUrl.equals(normalizedUrl, ignoreCase = true) ||
                (validation.sourceType == CatalogSourceType.SIMKL && it.sourceRef == "simkl_list:${CatalogUrlParser.parseSimkl(normalizedUrl)?.id}")) }) {
            return Result.failure(CatalogException(R.string.catalog_already_added))
        }

        val resolved = try { resolveMetadata(normalizedUrl, validation.sourceType) } catch (e: Exception) {
            if (e is kotlinx.coroutines.CancellationException) throw e
            return Result.failure(e)
        }
            ?: fallbackMetadata(normalizedUrl, validation.sourceType)
            ?: return Result.failure(CatalogException(R.string.catalog_failed_read_metadata))
        if (targetProfileId != profileManager.getProfileIdSync()) return Result.failure(IllegalStateException("Profile changed while updating the catalogue. Please try again."))
        val updated = existing.copy(
            title = resolved.title,
            sourceType = validation.sourceType,
            sourceUrl = normalizedUrl,
            sourceRef = resolved.sourceRef
        )
        current[index] = updated
        saveCatalogs(current, targetProfileId = targetProfileId)
        return Result.success(updated)
    }

    suspend fun removeCustomCatalog(catalogId: String): Result<Unit> {
        return removeCatalogsMatching(CatalogException(R.string.catalog_not_found)) { it.id == catalogId }
    }

    private suspend fun removeCatalogsMatching(error: CatalogException, matches: (CatalogConfig) -> Boolean): Result<Unit> {
        val profileId = activeProfileId()
        var removed = false
        context.settingsDataStore.edit { prefs ->
            val current = readCatalogsFromPrefs(profileId, prefs)
            val targets = current.filter(matches)
            if (targets.isEmpty()) return@edit
            val preinstalled = decodeHiddenPreinstalled(profileId, prefs).toMutableSet()
            val addons = decodeHiddenAddon(profileId, prefs).toMutableSet()
            val homeServers = decodeHiddenHomeServer(profileId, prefs).toMutableSet()
            targets.forEach { target ->
                when {
                    isPreinstalledCatalog(target) -> preinstalled.add(target.id)
                    target.sourceType == CatalogSourceType.ADDON -> addons.add(target.id)
                    target.sourceType == CatalogSourceType.HOME_SERVER -> homeServers.add(target.id)
                }
            }
            writeCatalogField(prefs, profileId, "hiddenPreinstalledByProfile", gson.toJson(preinstalled))
            writeCatalogField(prefs, profileId, "hiddenAddonByProfile", gson.toJson(addons))
            writeCatalogField(prefs, profileId, "hiddenHomeServerByProfile", gson.toJson(homeServers))
            writeCatalogField(prefs, profileId, "catalogsByProfile", gson.toJson(current.filterNot(matches)))
            removed = true
        }
        if (!removed) return Result.failure(error)
        lastSyncedAddonFingerprint = null
        invalidationBus.markDirty(CloudSyncScope.CATALOGS, profileId, "remove catalogs")
        return Result.success(Unit)
    }

    suspend fun renameCatalog(catalogId: String, newTitle: String): Boolean {
        val trimmed = newTitle.trim()
        if (trimmed.isBlank()) return false
        val current = getCatalogs().toMutableList()
        val index = current.indexOfFirst { it.id == catalogId }
        if (index < 0) return false
        current[index] = current[index].copy(title = trimmed)
        saveCatalogs(current)
        return true
    }

    suspend fun moveCatalogUp(catalogId: String): Boolean {
        val current = getCatalogs().toMutableList()
        val visible = current.filter { isVisibleCatalogInSettings(it) }
        val visibleIndex = visible.indexOfFirst { it.id == catalogId }
        if (visibleIndex <= 0) return false
        val currentIndex = current.indexOfFirst { it.id == catalogId }
        val previousVisibleId = visible[visibleIndex - 1].id
        val previousIndex = current.indexOfFirst { it.id == previousVisibleId }
        if (currentIndex < 0 || previousIndex < 0) return false
        val moved = current.removeAt(currentIndex)
        val insertAt = if (currentIndex > previousIndex) previousIndex else previousIndex - 1
        current.add(insertAt.coerceAtLeast(0), moved)
        saveCatalogs(current)
        return true
    }

    suspend fun moveCatalogDown(catalogId: String): Boolean {
        val current = getCatalogs().toMutableList()
        val visible = current.filter { isVisibleCatalogInSettings(it) }
        val visibleIndex = visible.indexOfFirst { it.id == catalogId }
        if (visibleIndex < 0 || visibleIndex >= visible.lastIndex) return false
        val currentIndex = current.indexOfFirst { it.id == catalogId }
        val nextVisibleId = visible[visibleIndex + 1].id
        val nextIndex = current.indexOfFirst { it.id == nextVisibleId }
        if (currentIndex < 0 || nextIndex < 0) return false
        val moved = current.removeAt(currentIndex)
        val insertAt = if (currentIndex < nextIndex) nextIndex else nextIndex + 1
        current.add(insertAt.coerceAtMost(current.size), moved)
        saveCatalogs(current)
        return true
    }

    suspend fun replaceCatalogsForActiveProfile(catalogs: List<CatalogConfig>) {
        val sanitized = catalogs
            .distinctBy { it.id }
            .map { cfg ->
                val looksCustom = cfg.id.startsWith("custom_") ||
                    cfg.sourceType == CatalogSourceType.ADDON ||
                    cfg.sourceType == CatalogSourceType.HOME_SERVER ||
                    !cfg.sourceUrl.isNullOrBlank() ||
                    !cfg.sourceRef.isNullOrBlank()
                if (looksCustom) cfg.copy(isPreinstalled = false) else cfg
            }
        saveCatalogs(sanitized)
    }

    fun validateCatalogUrl(rawUrl: String): CatalogValidationResult {
        val normalized = CatalogUrlParser.normalize(rawUrl)
        if (normalized.isBlank()) {
            return CatalogValidationResult(isValid = false, errorRes = R.string.catalog_url_required)
        }
        val uri = try { URI(normalized) } catch (e: Exception) { null }
            ?: return CatalogValidationResult(isValid = false, errorRes = R.string.catalog_url_invalid_format)
        val host = uri.host?.lowercase()
            ?: return CatalogValidationResult(isValid = false, errorRes = R.string.catalog_url_invalid_host)

        return when {
            host == "trakt.tv" || host.endsWith(".trakt.tv") -> {
                val canonical = canonicalizeTraktUrl(normalized)
                val parsed = CatalogUrlParser.parseTrakt(canonical)
                if (parsed == null) {
                    CatalogValidationResult(
                        isValid = false,
                        errorRes = R.string.catalog_url_trakt_hint
                    )
                } else {
                    CatalogValidationResult(
                        isValid = true,
                        normalizedUrl = canonical,
                        sourceType = CatalogSourceType.TRAKT
                    )
                }
            }
            host == "simkl.com" || host == "www.simkl.com" -> {
                val parsed = CatalogUrlParser.parseSimkl(normalized)
                if (parsed == null) CatalogValidationResult(isValid = false, errorRes = R.string.catalog_url_simkl_hint)
                else CatalogValidationResult(isValid = true, normalizedUrl = parsed.canonicalUrl, sourceType = CatalogSourceType.SIMKL)
            }
            host == "mdblist.com" || host.endsWith(".mdblist.com") -> {
                CatalogValidationResult(
                    isValid = true,
                    normalizedUrl = normalized,
                    sourceType = CatalogSourceType.MDBLIST
                )
            }
            host == "themoviedb.org" || host.endsWith(".themoviedb.org") -> {
                if (CatalogUrlParser.parseTmdb(normalized) == null) {
                    CatalogValidationResult(isValid = false, errorRes = R.string.catalog_url_tmdb_hint)
                } else {
                    CatalogValidationResult(
                        isValid = true,
                        normalizedUrl = normalized,
                        sourceType = CatalogSourceType.TMDB
                    )
                }
            }
            else -> CatalogValidationResult(
                isValid = false,
                errorRes = R.string.catalog_url_unsupported
            )
        }
    }

    private suspend fun resolveMetadata(url: String, sourceType: CatalogSourceType): ResolvedCatalog? {
        return when (sourceType) {
            CatalogSourceType.TRAKT -> resolveTraktMetadata(url)
            CatalogSourceType.SIMKL -> {
                val parsed = CatalogUrlParser.parseSimkl(url) ?: return null
                val list = simklListsRepository?.load(parsed.id)
                    ?: throw com.arflix.tv.data.repository.simkl.SimklListsException("user_token_required")
                ResolvedCatalog(list.name, "simkl_list:${list.id}")
            }
            CatalogSourceType.MDBLIST -> resolveMdblistMetadata(url)
            CatalogSourceType.TMDB -> resolveTmdbMetadata(url)
            CatalogSourceType.PREINSTALLED -> null
            CatalogSourceType.ADDON -> null
            CatalogSourceType.HOME_SERVER -> null
        }
    }

    private fun fallbackMetadata(url: String, sourceType: CatalogSourceType): ResolvedCatalog? {
        return when (sourceType) {
            CatalogSourceType.SIMKL -> null
            CatalogSourceType.TRAKT -> {
                when (val parsed = CatalogUrlParser.parseTrakt(url)) {
                    is ParsedCatalogUrl.TraktUserList -> {
                        ResolvedCatalog(
                            title = parsed.listId.toDisplayTitle(),
                            sourceRef = "trakt_user:${parsed.username}:${parsed.listId}"
                        )
                    }
                    is ParsedCatalogUrl.TraktList -> {
                        ResolvedCatalog(
                            title = parsed.listId.toDisplayTitle(),
                            sourceRef = "trakt_list:${parsed.listId}"
                        )
                    }
                    else -> null
                }
            }
            CatalogSourceType.MDBLIST -> ResolvedCatalog(
                title = context.getString(R.string.catalog_mdblist_title),
                sourceRef = "mdblist:$url"
            )
            CatalogSourceType.TMDB -> CatalogUrlParser.parseTmdb(url)?.let { parsed ->
                ResolvedCatalog(
                    title = parsed.slug?.replace('-', ' ')?.toDisplayTitle()
                        ?: context.getString(R.string.catalog_tmdb_title),
                    sourceRef = tmdbSourceRef(parsed)
                )
            }
            CatalogSourceType.PREINSTALLED -> null
            CatalogSourceType.ADDON -> null
            CatalogSourceType.HOME_SERVER -> null
        }
    }

    /** The TMDB page's own name, so the row isn't named after a URL slug. */
    private suspend fun resolveTmdbMetadata(url: String): ResolvedCatalog? {
        val parsed = CatalogUrlParser.parseTmdb(url) ?: return null
        val apiKey = Constants.TMDB_API_KEY
        val endpoint = when (parsed.kind) {
            "list" -> "list/${parsed.id}"
            "collection" -> "collection/${parsed.id}"
            "company" -> "company/${parsed.id}"
            "network" -> "network/${parsed.id}"
            "person" -> "person/${parsed.id}"
            "keyword" -> "keyword/${parsed.id}"
            // Genre pages have no detail endpoint; the slug names them well enough.
            else -> null
        }
        val name = if (endpoint == null || apiKey.isBlank()) {
            null
        } else {
            val body = fetchUrl("https://api.themoviedb.org/3/$endpoint?api_key=$apiKey")
            body?.let {
                runCatching { org.json.JSONObject(it).optString("name").trim().takeIf { n -> n.isNotEmpty() } }
                    .getOrNull()
            }
        }
        val title = name
            ?: parsed.slug?.replace('-', ' ')?.toDisplayTitle()
            ?: context.getString(R.string.catalog_tmdb_title)
        return ResolvedCatalog(title = title, sourceRef = tmdbSourceRef(parsed))
    }

    private fun tmdbSourceRef(parsed: ParsedCatalogUrl.Tmdb): String =
        "tmdb:${parsed.kind}:${parsed.id}:${parsed.mediaType.orEmpty()}"

    private fun canonicalizeTraktUrl(url: String): String {
        val parsed = CatalogUrlParser.parseTrakt(url) ?: return CatalogUrlParser.normalize(url)
        return when (parsed) {
            is ParsedCatalogUrl.TraktUserList -> {
                "https://trakt.tv/users/${parsed.username}/lists/${parsed.listId}"
            }
            is ParsedCatalogUrl.TraktList -> {
                "https://trakt.tv/lists/${parsed.listId}"
            }
            else -> CatalogUrlParser.normalize(url)
        }
    }

    private suspend fun resolveTraktMetadata(url: String): ResolvedCatalog? {
        return when (val parsed = CatalogUrlParser.parseTrakt(url)) {
            is ParsedCatalogUrl.TraktUserList -> {
                try {
                    val summary = traktApi.getUserListSummary(
                        clientId = Constants.TRAKT_CLIENT_ID,
                        username = parsed.username,
                        listId = parsed.listId
                    )
                    ResolvedCatalog(
                        title = summary.name.ifBlank { parsed.listId.replace('-', ' ') },
                        sourceRef = "trakt_user:${parsed.username}:${parsed.listId}"
                    )
                } catch (e: kotlinx.coroutines.CancellationException) {
                    throw e
                } catch (e: Exception) {
                    null
                }
            }
            is ParsedCatalogUrl.TraktList -> {
                try {
                    val summary = traktApi.getListSummary(
                        clientId = Constants.TRAKT_CLIENT_ID,
                        listId = parsed.listId
                    )
                    ResolvedCatalog(
                        title = summary.name.ifBlank { parsed.listId.replace('-', ' ') },
                        sourceRef = "trakt_list:${parsed.listId}"
                    )
                } catch (e: kotlinx.coroutines.CancellationException) {
                    throw e
                } catch (e: Exception) {
                    null
                }
            }
            else -> null
        }
    }

    private suspend fun resolveMdblistMetadata(url: String): ResolvedCatalog? {
        val html = fetchUrl(url) ?: return null
        val discoveredTrakt = extractTraktUrl(html)
        if (discoveredTrakt != null) {
            val traktResolved = resolveTraktMetadata(discoveredTrakt)
            if (traktResolved != null) {
                return traktResolved.copy(sourceRef = "mdblist_trakt:$discoveredTrakt")
            }
        }

        val titleFromMeta = CatalogRepoRegexes.TITLE_FROM_META_REGEX.find(html)?.groupValues?.getOrNull(1)

        val titleFromTag = CatalogRepoRegexes.TITLE_FROM_TAG_REGEX.find(html)?.groupValues?.getOrNull(1)
            ?.replace(" - MDBList", "", ignoreCase = true)

        val titleFromSlug = extractMdblistSlugTitle(url)
        val mdblistFallbackTitle = context.getString(R.string.catalog_mdblist_title)
        val finalTitle = (titleFromMeta ?: titleFromTag ?: titleFromSlug ?: mdblistFallbackTitle).trim()
        return ResolvedCatalog(
            title = finalTitle.ifBlank { mdblistFallbackTitle },
            sourceRef = "mdblist:$url"
        )
    }

    private fun extractMdblistSlugTitle(url: String): String? {
        val pathSegments = try { URI(url).path.trim('/') } catch (e: Exception) { null }
            ?.split('/')
            ?.filter { it.isNotBlank() }
            .orEmpty()
        if (pathSegments.isEmpty()) return null
        val slug = pathSegments.last()
        if (slug.equals("lists", ignoreCase = true)) return null
        return slug.toDisplayTitle()
    }

    private fun extractTraktUrl(html: String): String? {
        return CatalogRepoRegexes.TRAKT_URL_REGEX.find(html)?.value
    }

    /**
     * [fresh] skips the HTTP cache for user-initiated imports: re-importing an edited pack must
     * not get the copy cached by the previous import.
     */
    private suspend fun fetchUrl(url: String, fresh: Boolean = false): String? {
        return withContext(Dispatchers.IO) {
            try {
                val request = Request.Builder()
                    .url(url)
                    .header("User-Agent", OkHttpProvider.userAgentOr("Mozilla/5.0 (Android TV; ARVIO)"))
                    .apply { if (fresh) cacheControl(CacheControl.FORCE_NETWORK) }
                    .build()
                okHttpClient.newCall(request).execute().use { response ->
                    if (!response.isSuccessful) return@use null
                    response.body?.string()
                }
            } catch (e: kotlinx.coroutines.CancellationException) {
                throw e
            } catch (e: Exception) {
                null
            }
        }
    }

    private fun parseCatalogsJson(json: String?): List<CatalogConfig> {
        if (json.isNullOrBlank()) return emptyList()
        val strict = try {
            gson.fromJson<List<CatalogConfig>>(json, listType) ?: emptyList()
        } catch (e: Exception) { emptyList() }
            .mapNotNull { normalizeCatalogConfig(it) }
        if (strict.isNotEmpty()) return strict

        // Legacy/compat parse: recover from older/partial enum values so existing
        // custom catalogs don't disappear after app updates.
        return try {
            val rawType = TypeToken.getParameterized(List::class.java, TypeToken.getParameterized(Map::class.java, String::class.java, Any::class.java).type).type
            val rawList = gson.fromJson<List<Map<String, Any?>>>(json, rawType).orEmpty()
            rawList.mapNotNull { row ->
                val id = (row["id"] as? String)?.trim().orEmpty()
                val title = (row["title"] as? String)?.trim().orEmpty()
                if (id.isBlank() || title.isBlank()) return@mapNotNull null

                val sourceUrl = (row["sourceUrl"] as? String)?.trim().takeUnless { it.isNullOrBlank() }
                val sourceRef = (row["sourceRef"] as? String)?.trim().takeUnless { it.isNullOrBlank() }
                val addonId = asTrimmedString(row["addonId"])
                val addonCatalogType = asTrimmedString(row["addonCatalogType"])
                val addonCatalogId = asTrimmedString(row["addonCatalogId"])
                val addonName = asTrimmedString(row["addonName"])
                val kind = parseCatalogKindCompat(asTrimmedString(row["kind"]))
                val collectionGroup = parseCollectionGroupCompat(asTrimmedString(row["collectionGroup"]))
                val collectionDescription = asTrimmedString(row["collectionDescription"])
                val collectionCoverImageUrl = asTrimmedString(row["collectionCoverImageUrl"])
                val collectionFocusGifUrl = asTrimmedString(row["collectionFocusGifUrl"])
                val collectionHeroImageUrl = asTrimmedString(row["collectionHeroImageUrl"])
                val collectionHeroGifUrl = asTrimmedString(row["collectionHeroGifUrl"])
                val collectionHeroVideoUrl = asTrimmedString(row["collectionHeroVideoUrl"])
                val collectionTileShape = parseCollectionTileShapeCompat(asTrimmedString(row["collectionTileShape"]))
                val collectionHideTitle = (row["collectionHideTitle"] as? Boolean) ?: false
                val collectionRailKey = asTrimmedString(row["collectionRailKey"])
                val packId = asTrimmedString(row["packId"])
                val packName = asTrimmedString(row["packName"])
                val collectionSources = try {
                    val jsonValue = gson.toJson(row["collectionSources"])
                    gson.fromJson<List<CollectionSourceConfig>>(
                        jsonValue,
                        TypeToken.getParameterized(List::class.java, CollectionSourceConfig::class.java).type
                    ) ?: emptyList()
                } catch (e: com.google.gson.JsonSyntaxException) {
                    emptyList()
                } catch (e: Exception) {
                    if (e is kotlinx.coroutines.CancellationException) throw e
                    emptyList()
                }
                val requiredAddonUrls = try {
                    val jsonValue = gson.toJson(row["requiredAddonUrls"])
                    gson.fromJson<List<String>>(
                        jsonValue,
                        TypeToken.getParameterized(List::class.java, String::class.java).type
                    )?.map { it.trim() }?.filter { it.isNotBlank() } ?: emptyList()
                } catch (e: com.google.gson.JsonSyntaxException) {
                    emptyList()
                } catch (e: Exception) {
                    if (e is kotlinx.coroutines.CancellationException) throw e
                    emptyList()
                }
                val sourceTypeRaw = (row["sourceType"] as? String)?.trim().orEmpty()
                val sourceType = parseSourceTypeCompat(sourceTypeRaw, sourceUrl, sourceRef)
                val isPreinstalledRaw = (row["isPreinstalled"] as? Boolean) ?: false
                val isPreinstalled = when {
                    sourceUrl != null -> false
                    sourceType != CatalogSourceType.PREINSTALLED -> false
                    else -> isPreinstalledRaw
                }

                normalizeCatalogConfig(
                    CatalogConfig(
                        id = id,
                        title = title,
                        sourceType = sourceType,
                        sourceUrl = sourceUrl,
                        sourceRef = sourceRef,
                        isPreinstalled = isPreinstalled,
                        addonId = addonId,
                        addonCatalogType = addonCatalogType,
                        addonCatalogId = addonCatalogId,
                        addonName = addonName,
                        kind = kind,
                        collectionGroup = collectionGroup,
                        collectionDescription = collectionDescription,
                        collectionCoverImageUrl = collectionCoverImageUrl,
                        collectionFocusGifUrl = collectionFocusGifUrl,
                        collectionHeroImageUrl = collectionHeroImageUrl,
                        collectionHeroGifUrl = collectionHeroGifUrl,
                        collectionHeroVideoUrl = collectionHeroVideoUrl,
                        collectionTileShape = collectionTileShape,
                        collectionHideTitle = collectionHideTitle,
                        collectionSources = collectionSources,
                        requiredAddonUrls = requiredAddonUrls,
                        packId = packId,
                        packName = packName,
                        collectionRailKey = collectionRailKey
                    )
                )
            }
        } catch (e: Exception) { emptyList() }
    }

    private fun normalizeCatalogConfig(config: CatalogConfig): CatalogConfig? {
        if (config.id.isBlank() || config.title.isBlank()) return null
        // Retire the old Home sports rows, including saved/cloud-restored defaults.
        // Add-on catalogs keep their own source and must remain available.
        if (config.id in setOf(SportsAddonCapabilities.SPORTS_CATEGORY_ROW_ID,
                SportsAddonCapabilities.POPULAR_LIVE_TV_ROW_ID) &&
            config.sourceType == CatalogSourceType.PREINSTALLED &&
            config.sourceUrl.isNullOrBlank() && config.sourceRef.isNullOrBlank() &&
            config.addonId.isNullOrBlank()) return null
        val normalizedUrl = config.sourceUrl?.trim().takeUnless { it.isNullOrBlank() }
        val normalizedRef = config.sourceRef?.trim().takeUnless { it.isNullOrBlank() }
        val bundledPreinstalled = isBundledPreinstalledCatalogId(config.id)
        val sourceRefAddon = parseAddonSourceRef(normalizedRef)
        val sourceTypeName = try {
            config.sourceType.name
        } catch (e: Exception) {
            if (e is kotlinx.coroutines.CancellationException) throw e
            CatalogSourceType.PREINSTALLED.name
        }
        val configKind = try {
            CatalogKind.valueOf(config.kind.name)
        } catch (e: Exception) {
            if (e is kotlinx.coroutines.CancellationException) throw e
            CatalogKind.STANDARD
        }
        val safeCollectionSources = config.collectionSources.orEmpty()
        val safeRequiredAddonUrls = config.requiredAddonUrls.orEmpty()
        val normalizedCollectionTileShape = try {
            CollectionTileShape.valueOf(config.collectionTileShape.name)
        } catch (e: Exception) { CollectionTileShape.LANDSCAPE }
        val normalizedAddonId = config.addonId?.trim().takeUnless { it.isNullOrBlank() }
            ?: sourceRefAddon?.first
        val normalizedAddonType = normalizeAddonCatalogType(config.addonCatalogType)
            ?: sourceRefAddon?.second
        val normalizedAddonCatalogId = config.addonCatalogId?.trim().takeUnless { it.isNullOrBlank() }
            ?: sourceRefAddon?.third
        val normalizedAddonName = config.addonName?.trim().takeUnless { it.isNullOrBlank() }
        val inferredType = parseSourceTypeCompat(
            raw = sourceTypeName,
            sourceUrl = normalizedUrl,
            sourceRef = normalizedRef
        )
        val normalizedKind = when {
            configKind == CatalogKind.COLLECTION_RAIL -> CatalogKind.COLLECTION_RAIL
            safeCollectionSources.isNotEmpty() -> CatalogKind.COLLECTION
            else -> configKind
        }
        val normalizedPreinstalled = when {
            bundledPreinstalled -> true
            normalizedKind == CatalogKind.COLLECTION || normalizedKind == CatalogKind.COLLECTION_RAIL ->
                config.isPreinstalled
            inferredType != CatalogSourceType.PREINSTALLED -> false
            else -> config.isPreinstalled
        }
        return config.copy(
            sourceType = inferredType,
            sourceUrl = normalizedUrl,
            sourceRef = normalizedRef,
            isPreinstalled = normalizedPreinstalled,
            addonId = if (inferredType == CatalogSourceType.ADDON) normalizedAddonId else null,
            addonCatalogType = if (inferredType == CatalogSourceType.ADDON) normalizedAddonType else null,
            addonCatalogId = if (inferredType == CatalogSourceType.ADDON) normalizedAddonCatalogId else null,
            addonName = if (inferredType == CatalogSourceType.ADDON) normalizedAddonName else null,
            kind = normalizedKind,
            collectionDescription = config.collectionDescription?.trim().takeUnless { it.isNullOrBlank() },
            collectionCoverImageUrl = config.collectionCoverImageUrl?.trim().takeUnless { it.isNullOrBlank() },
            collectionFocusGifUrl = config.collectionFocusGifUrl?.trim().takeUnless { it.isNullOrBlank() },
            collectionHeroImageUrl = config.collectionHeroImageUrl?.trim().takeUnless { it.isNullOrBlank() },
            collectionHeroGifUrl = config.collectionHeroGifUrl?.trim().takeUnless { it.isNullOrBlank() },
            collectionHeroVideoUrl = config.collectionHeroVideoUrl?.trim().takeUnless { it.isNullOrBlank() },
            collectionClearLogoUrl = config.collectionClearLogoUrl?.trim().takeUnless { it.isNullOrBlank() },
            collectionTileShape = normalizedCollectionTileShape,
            collectionHideTitle = config.collectionHideTitle,
            collectionSources = safeCollectionSources,
            requiredAddonUrls = safeRequiredAddonUrls.map { it.trim() }.filter { it.isNotBlank() }.distinct()
        )
    }

    private fun parseCatalogKindCompat(raw: String?): CatalogKind {
        return when (raw?.trim()?.uppercase()) {
            CatalogKind.COLLECTION.name -> CatalogKind.COLLECTION
            CatalogKind.COLLECTION_RAIL.name -> CatalogKind.COLLECTION_RAIL
            else -> CatalogKind.STANDARD
        }
    }

    private fun parseCollectionGroupCompat(raw: String?): CollectionGroupKind? {
        return when (raw?.trim()?.uppercase()) {
            CollectionGroupKind.FEATURED.name -> CollectionGroupKind.FEATURED
            CollectionGroupKind.SERVICE.name -> CollectionGroupKind.SERVICE
            CollectionGroupKind.GENRE.name -> CollectionGroupKind.GENRE
            CollectionGroupKind.DECADE.name -> CollectionGroupKind.DECADE
            CollectionGroupKind.FRANCHISE.name -> CollectionGroupKind.FRANCHISE
            CollectionGroupKind.NETWORK.name -> CollectionGroupKind.NETWORK
            else -> null
        }
    }

    private fun parseCollectionTileShapeCompat(raw: String?): CollectionTileShape {
        return when (raw?.trim()?.uppercase()) {
            CollectionTileShape.POSTER.name -> CollectionTileShape.POSTER
            else -> CollectionTileShape.LANDSCAPE
        }
    }

    private fun parseSourceTypeCompat(
        raw: String,
        sourceUrl: String?,
        sourceRef: String?
    ): CatalogSourceType {
        val normalized = raw.trim().uppercase()
        return when {
            // URL/ref evidence always wins over stale enum values from older builds.
            sourceRef?.startsWith(HomeServerRepository.CATALOG_SOURCE_REF_PREFIX, ignoreCase = true) == true -> CatalogSourceType.HOME_SERVER
            sourceRef?.startsWith(ADDON_SOURCE_REF_PREFIX, ignoreCase = true) == true -> CatalogSourceType.ADDON
            sourceRef?.startsWith("simkl_list:", ignoreCase = true) == true -> CatalogSourceType.SIMKL
            sourceUrl?.let { CatalogUrlParser.parseSimkl(it) } != null -> CatalogSourceType.SIMKL
            sourceRef?.startsWith("trakt_", ignoreCase = true) == true -> CatalogSourceType.TRAKT
            sourceRef?.startsWith("mdblist", ignoreCase = true) == true -> CatalogSourceType.MDBLIST
            sourceRef?.startsWith("tmdb:", ignoreCase = true) == true -> CatalogSourceType.TMDB
            sourceUrl?.contains("trakt.tv", ignoreCase = true) == true -> CatalogSourceType.TRAKT
            sourceUrl?.contains("mdblist.com", ignoreCase = true) == true -> CatalogSourceType.MDBLIST
            sourceUrl?.contains("themoviedb.org", ignoreCase = true) == true -> CatalogSourceType.TMDB
            normalized == CatalogSourceType.TRAKT.name -> CatalogSourceType.TRAKT
            normalized == CatalogSourceType.SIMKL.name -> CatalogSourceType.SIMKL
            normalized == CatalogSourceType.MDBLIST.name -> CatalogSourceType.MDBLIST
            normalized == CatalogSourceType.TMDB.name -> CatalogSourceType.TMDB
            normalized == CatalogSourceType.ADDON.name -> CatalogSourceType.ADDON
            normalized == CatalogSourceType.HOME_SERVER.name -> CatalogSourceType.HOME_SERVER
            normalized == CatalogSourceType.PREINSTALLED.name -> CatalogSourceType.PREINSTALLED
            normalized.contains("HOME_SERVER") || normalized.contains("HOME SERVER") -> CatalogSourceType.HOME_SERVER
            normalized.contains("ADDON") -> CatalogSourceType.ADDON
            normalized.contains("TRAKT") -> CatalogSourceType.TRAKT
            normalized.contains("TMDB") -> CatalogSourceType.TMDB
            normalized.contains("MDB") || normalized.contains("MDL") -> CatalogSourceType.MDBLIST
            sourceUrl.isNullOrBlank() -> CatalogSourceType.PREINSTALLED
            else -> CatalogSourceType.TRAKT
        }
    }

    private fun readCatalogsFromPrefs(profileId: String, prefs: Preferences): List<CatalogConfig> {
        val hiddenPreinstalled = decodeHiddenPreinstalled(profileId, prefs)
        val hiddenAddon = decodeHiddenAddon(profileId, prefs)
        val hiddenHomeServer = decodeHiddenHomeServer(profileId, prefs)

        fun CatalogConfig.isHidden(): Boolean {
            if (isPreinstalledCatalog(this) && id in hiddenPreinstalled) return true
            if (sourceType == CatalogSourceType.ADDON && id in hiddenAddon) return true
            if (sourceType == CatalogSourceType.HOME_SERVER && id in hiddenHomeServer) return true
            return false
        }

        // Strict profile-first lookup to avoid leaking or prioritizing
        // catalogs from other profiles.
        val primary = parseCatalogsJson(prefs[catalogsKey(profileId)])
        if (prefs[catalogsKey(profileId)] != null) {
            val base = primary
                .distinctBy { it.id }
                .map { refreshBundledPreinstalledCatalog(it) }
                .filterNot { it.isHidden() }
                .toMutableList()
            // Once a profile has a saved list, even [], legacy data is not authoritative.
            return base
        }

        // Legacy fallback keys (pre profile-scoping).
        val legacyDefault = parseCatalogsJson(prefs[legacyDefaultKey])
        if (legacyDefault.isNotEmpty()) {
            return legacyDefault
                .distinctBy { it.id }
                .map { refreshBundledPreinstalledCatalog(it) }
                .filterNot { it.isHidden() }
        }

        val legacyGlobal = parseCatalogsJson(prefs[legacyGlobalKey])
        if (legacyGlobal.isNotEmpty()) {
            return legacyGlobal
                .distinctBy { it.id }
                .map { refreshBundledPreinstalledCatalog(it) }
                .filterNot { it.isHidden() }
        }

        return emptyList()
    }

    private data class ResolvedCatalog(
        val title: String,
        val sourceRef: String
    )

    private fun asTrimmedString(value: Any?): String? {
        return (value as? String)?.trim().takeUnless { it.isNullOrBlank() }
    }

    private companion object {
        private const val ADDON_SOURCE_REF_PREFIX = "addon_catalog|"

    }
}

private fun String.toDisplayTitle(): String {
    return replace('-', ' ')
        .split(' ')
        .filter { it.isNotBlank() }
        .joinToString(" ") { token ->
            token.replaceFirstChar { ch -> ch.uppercase() }
        }
        .ifBlank { "Custom Catalog" }
}

private object CatalogRepoRegexes {
    val TITLE_FROM_META_REGEX = Regex(
        """<meta\s+property=["']og:title["']\s+content=["']([^"']+)["']""",
        RegexOption.IGNORE_CASE
    )
    val TITLE_FROM_TAG_REGEX = Regex(
        """<title>([^<]+)</title>""",
        RegexOption.IGNORE_CASE
    )
    val TRAKT_URL_REGEX = Regex(
        """https?://(?:www\.)?trakt\.tv/users/[^"'\s<]+/lists/[^"'\s<]+""",
        RegexOption.IGNORE_CASE
    )
}
