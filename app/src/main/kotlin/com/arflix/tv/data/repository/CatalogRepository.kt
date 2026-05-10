package com.arflix.tv.data.repository

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.edit
import com.arflix.tv.data.api.TraktApi
import com.arflix.tv.data.model.Addon
import com.arflix.tv.data.model.AddonCatalog
import com.arflix.tv.data.model.AddonType
import com.arflix.tv.data.model.CatalogConfig
import com.arflix.tv.data.model.CatalogKind
import com.arflix.tv.data.model.CollectionGroupKind
import com.arflix.tv.data.model.CollectionSourceConfig
import com.arflix.tv.data.model.CollectionTileShape
import com.arflix.tv.data.model.CatalogSourceType
import com.arflix.tv.data.model.CatalogValidationResult
import com.arflix.tv.data.model.Category
import com.arflix.tv.data.repository.HomeServerCatalogCandidate
import com.arflix.tv.util.CatalogUrlParser
import com.arflix.tv.util.Constants
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
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.net.URI
import java.net.URLDecoder
import java.net.URLEncoder
import java.security.MessageDigest
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CatalogRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val profileManager: ProfileManager,
    private val traktApi: TraktApi,
    private val okHttpClient: OkHttpClient,
    private val invalidationBus: CloudSyncInvalidationBus
) {
    private companion object {
        private const val ADDON_SOURCE_REF_PREFIX = "addon_catalog|"

        private val TITLE_FROM_META_REGEX = Regex(
            """<meta\s+property=["']og:title["']\s+content=["']([^"']+)["']""",
            RegexOption.IGNORE_CASE
        )
        private val TITLE_FROM_TAG_REGEX = Regex(
            """<title>([^<]+)</title>""",
            RegexOption.IGNORE_CASE
        )
        private val TRAKT_URL_REGEX = Regex(
            """https?://(?:www\.)?trakt\.tv/users/[^"'\s<]+/lists/[^"'\s<]+""",
            RegexOption.IGNORE_CASE
        )
    }

    private val bundledPreinstalledCatalogsById by lazy(LazyThreadSafetyMode.NONE) {
        MediaRepository.buildPreinstalledDefaults().associateBy { it.id }
    }

    private val bundledPreinstalledCatalogIds by lazy(LazyThreadSafetyMode.NONE) {
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

    private fun decodeHiddenPreinstalled(profileId: String, prefs: Preferences): Set<String> {
        val raw = prefs[hiddenPreinstalledKey(profileId)]
        if (raw.isNullOrBlank()) return emptySet()
        return try {
            (gson.fromJson<List<String>>(raw, hiddenListType) ?: emptyList())
                .map { it.trim() }
                .filter { it.isNotBlank() }
                .toSet()
        } catch (_: Exception) {
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
        } catch (_: Exception) {
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
        } catch (_: Exception) {
            emptySet()
        }
    }

    private suspend fun hidePreinstalledCatalog(profileId: String, catalogId: String) {
        val trimmed = catalogId.trim()
        if (trimmed.isBlank()) return
        context.settingsDataStore.edit { prefs ->
            val hidden = decodeHiddenPreinstalled(profileId, prefs).toMutableSet()
            hidden.add(trimmed)
            prefs[hiddenPreinstalledKey(profileId)] = gson.toJson(hidden.toList())
        }
        invalidationBus.markDirty(CloudSyncScope.CATALOGS, profileId, "hide preinstalled catalog")
    }

    private suspend fun hideAddonCatalog(profileId: String, catalogId: String) {
        val trimmed = catalogId.trim()
        if (trimmed.isBlank()) return
        context.settingsDataStore.edit { prefs ->
            val hidden = decodeHiddenAddon(profileId, prefs).toMutableSet()
            hidden.add(trimmed)
            prefs[hiddenAddonKey(profileId)] = gson.toJson(hidden.toList())
        }
        invalidationBus.markDirty(CloudSyncScope.CATALOGS, profileId, "hide addon catalog")
    }

    private suspend fun hideHomeServerCatalog(profileId: String, catalogId: String) {
        val trimmed = catalogId.trim()
        if (trimmed.isBlank()) return
        context.settingsDataStore.edit { prefs ->
            val hidden = decodeHiddenHomeServer(profileId, prefs).toMutableSet()
            hidden.add(trimmed)
            prefs[hiddenHomeServerKey(profileId)] = gson.toJson(hidden.toList())
        }
        invalidationBus.markDirty(CloudSyncScope.CATALOGS, profileId, "hide home server catalog")
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun observeCatalogs(): Flow<List<CatalogConfig>> {
        return profileManager.activeProfileId
            .flatMapLatest { profileId ->
                context.settingsDataStore.data.map { prefs ->
                    readCatalogsFromPrefs(profileId, prefs)
                }
            }
            .distinctUntilChanged()
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
        // One-time migration/sync for old keys and merged legacy custom entries.
        if (resolved.isNotEmpty() && resolved != primary) {
            saveCatalogs(resolved)
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
            if (cleaned.isEmpty()) {
                prefs[hiddenPreinstalledKey(profileId)] = ""
            } else {
                prefs[hiddenPreinstalledKey(profileId)] = gson.toJson(cleaned)
            }
        }
        invalidationBus.markDirty(CloudSyncScope.CATALOGS, profileId, "set hidden preinstalled catalogs")
    }

    suspend fun setHiddenPreinstalledCatalogIdsForProfile(profileId: String, ids: List<String>) {
        val safeProfileId = profileId.trim().ifBlank { "default" }
        val cleaned = ids.map { it.trim() }.filter { it.isNotBlank() }.distinct()
        context.settingsDataStore.edit { prefs ->
            if (cleaned.isEmpty()) {
                prefs[hiddenPreinstalledKey(safeProfileId)] = ""
            } else {
                prefs[hiddenPreinstalledKey(safeProfileId)] = gson.toJson(cleaned)
            }
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
            if (cleaned.isEmpty()) {
                prefs[hiddenAddonKey(profileId)] = ""
            } else {
                prefs[hiddenAddonKey(profileId)] = gson.toJson(cleaned)
            }
        }
        invalidationBus.markDirty(CloudSyncScope.CATALOGS, profileId, "set hidden addon catalogs")
    }

    suspend fun setHiddenAddonCatalogIdsForProfile(profileId: String, ids: List<String>) {
        val safeProfileId = profileId.trim().ifBlank { "default" }
        val cleaned = ids.map { it.trim() }.filter { it.isNotBlank() }.distinct()
        context.settingsDataStore.edit { prefs ->
            if (cleaned.isEmpty()) {
                prefs[hiddenAddonKey(safeProfileId)] = ""
            } else {
                prefs[hiddenAddonKey(safeProfileId)] = gson.toJson(cleaned)
            }
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
            if (cleaned.isEmpty()) {
                prefs[hiddenHomeServerKey(safeProfileId)] = ""
            } else {
                prefs[hiddenHomeServerKey(safeProfileId)] = gson.toJson(cleaned)
            }
        }
        invalidationBus.markDirty(CloudSyncScope.CATALOGS, safeProfileId, "set hidden home server catalogs")
    }

    private suspend fun saveCatalogs(catalogs: List<CatalogConfig>) {
        val profileId = activeProfileId()
        val sanitized = catalogs
            .distinctBy { it.id }
            .mapNotNull { normalizeCatalogConfig(it) }
        context.settingsDataStore.edit { prefs ->
            prefs[catalogsKey(profileId)] = gson.toJson(sanitized)
        }
        invalidationBus.markDirty(CloudSyncScope.CATALOGS, profileId, "save catalogs")
    }

    suspend fun replaceCatalogsForProfile(profileId: String, catalogs: List<CatalogConfig>) {
        val safeProfileId = profileId.trim().ifBlank { "default" }
        val sanitized = catalogs
            .distinctBy { it.id }
            .mapNotNull { normalizeCatalogConfig(it) }
        context.settingsDataStore.edit { prefs ->
            prefs[catalogsKey(safeProfileId)] = gson.toJson(sanitized)
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
        val existing = getCatalogs().mapNotNull { cfg ->
            if ((cfg.kind == CatalogKind.COLLECTION || cfg.kind == CatalogKind.COLLECTION_RAIL) &&
                !defaultIds.contains(cfg.id)
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
            saveCatalogs(merged)
        }
        return merged
    }

    suspend fun syncAddonCatalogs(addons: List<Addon>): Boolean {
        val profileId = activeProfileId()
        val hiddenAddonIds = context.settingsDataStore.data
            .first()
            .let { prefs -> decodeHiddenAddon(profileId, prefs) }
        val supportedCatalogs = addons
            .asSequence()
            .filter { addon ->
                addon.isInstalled &&
                    addon.isEnabled &&
                    addon.type != AddonType.SUBTITLE &&
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

        val current = getCatalogs().toMutableList()
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
            current.addAll(0, missing)
            changed = true
        }

        if (changed) {
            saveCatalogs(current)
        }
        return changed
    }

    suspend fun syncHomeServerCatalogs(candidates: List<HomeServerCatalogCandidate>): Boolean {
        val profileId = activeProfileId()
        val hiddenHomeServerIds = context.settingsDataStore.data
            .first()
            .let { prefs -> decodeHiddenHomeServer(profileId, prefs) }
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

        val current = getCatalogs().toMutableList()
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

        if (changed) saveCatalogs(current)
        return changed
    }

    private fun buildAddonCatalogConfig(
        addon: Addon,
        catalog: AddonCatalog
    ): CatalogConfig? {
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

    private fun normalizeAddonCatalogType(rawType: String?): String? {
        return when (rawType?.trim()?.lowercase()) {
            "movie" -> "movie"
            "series" -> "series"
            "tv" -> "tv"
            "show" -> "show"
            "shows" -> "shows"
            else -> null
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

    suspend fun addCustomCatalog(rawUrl: String): Result<CatalogConfig> {
        val validation = validateCatalogUrl(rawUrl)
        if (!validation.isValid || validation.normalizedUrl == null || validation.sourceType == null) {
            return Result.failure(IllegalArgumentException(validation.error ?: "Invalid URL"))
        }

        val normalizedUrl = validation.normalizedUrl
        val sourceType = validation.sourceType
        val resolved = resolveMetadata(normalizedUrl, sourceType)
            ?: fallbackMetadata(normalizedUrl, sourceType)
            ?: return Result.failure(IllegalArgumentException("Failed to read catalog metadata"))

        val current = getCatalogs().toMutableList()
        if (current.any { it.sourceUrl.equals(normalizedUrl, ignoreCase = true) }) {
            return Result.failure(IllegalArgumentException("Catalog already added"))
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
        saveCatalogs(current)
        return Result.success(newCatalog)
    }

    suspend fun updateCustomCatalog(catalogId: String, rawUrl: String): Result<CatalogConfig> {
        val current = getCatalogs().toMutableList()
        val index = current.indexOfFirst { it.id == catalogId }
        if (index < 0) return Result.failure(IllegalArgumentException("Catalog not found"))
        val existing = current[index]
        if (existing.isPreinstalled) {
            return Result.failure(IllegalArgumentException("Preinstalled catalogs cannot be edited"))
        }

        val validation = validateCatalogUrl(rawUrl)
        if (!validation.isValid || validation.normalizedUrl == null || validation.sourceType == null) {
            return Result.failure(IllegalArgumentException(validation.error ?: "Invalid URL"))
        }

        val normalizedUrl = validation.normalizedUrl
        if (current.any { it.id != catalogId && it.sourceUrl.equals(normalizedUrl, ignoreCase = true) }) {
            return Result.failure(IllegalArgumentException("Catalog already added"))
        }

        val resolved = resolveMetadata(normalizedUrl, validation.sourceType)
            ?: fallbackMetadata(normalizedUrl, validation.sourceType)
            ?: return Result.failure(IllegalArgumentException("Failed to read catalog metadata"))
        val updated = existing.copy(
            title = resolved.title,
            sourceType = validation.sourceType,
            sourceUrl = normalizedUrl,
            sourceRef = resolved.sourceRef
        )
        current[index] = updated
        saveCatalogs(current)
        return Result.success(updated)
    }

    suspend fun removeCustomCatalog(catalogId: String): Result<Unit> {
        val current = getCatalogs().toMutableList()
        val target = current.firstOrNull { it.id == catalogId }
            ?: return Result.failure(IllegalArgumentException("Catalog not found"))
        val profileId = activeProfileId()
        if (isPreinstalledCatalog(target)) {
            hidePreinstalledCatalog(profileId, catalogId)
        } else if (target.sourceType == CatalogSourceType.ADDON) {
            // Addon catalogs are re-derived from installed addons on every
            // syncAddonCatalogs call, so the deletion won't stick unless we
            // record the id here.
            hideAddonCatalog(profileId, catalogId)
        } else if (target.sourceType == CatalogSourceType.HOME_SERVER) {
            hideHomeServerCatalog(profileId, catalogId)
        }
        current.removeAll { it.id == catalogId }
        saveCatalogs(current)
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
            return CatalogValidationResult(isValid = false, error = "URL is required")
        }
        val uri = runCatching { URI(normalized) }.getOrNull()
            ?: return CatalogValidationResult(isValid = false, error = "Invalid URL format")
        val host = uri.host?.lowercase()
            ?: return CatalogValidationResult(isValid = false, error = "Invalid host")

        return when {
            host == "trakt.tv" || host.endsWith(".trakt.tv") -> {
                val canonical = canonicalizeTraktUrl(normalized)
                val parsed = CatalogUrlParser.parseTrakt(canonical)
                if (parsed == null) {
                    CatalogValidationResult(
                        isValid = false,
                        error = "Use a Trakt list URL: trakt.tv/users/{user}/lists/{list}"
                    )
                } else {
                    CatalogValidationResult(
                        isValid = true,
                        normalizedUrl = canonical,
                        sourceType = CatalogSourceType.TRAKT
                    )
                }
            }
            host == "mdblist.com" || host.endsWith(".mdblist.com") -> {
                CatalogValidationResult(
                    isValid = true,
                    normalizedUrl = normalized,
                    sourceType = CatalogSourceType.MDBLIST
                )
            }
            else -> CatalogValidationResult(
                isValid = false,
                error = "Only Trakt and MDBList URLs are supported"
            )
        }
    }

    private suspend fun resolveMetadata(url: String, sourceType: CatalogSourceType): ResolvedCatalog? {
        return when (sourceType) {
            CatalogSourceType.TRAKT -> resolveTraktMetadata(url)
            CatalogSourceType.MDBLIST -> resolveMdblistMetadata(url)
            CatalogSourceType.PREINSTALLED -> null
            CatalogSourceType.ADDON -> null
            CatalogSourceType.HOME_SERVER -> null
        }
    }

    private fun fallbackMetadata(url: String, sourceType: CatalogSourceType): ResolvedCatalog? {
        return when (sourceType) {
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
                title = "MDBList Catalog",
                sourceRef = "mdblist:$url"
            )
            CatalogSourceType.PREINSTALLED -> null
            CatalogSourceType.ADDON -> null
            CatalogSourceType.HOME_SERVER -> null
        }
    }

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
                runCatching {
                    val summary = traktApi.getUserListSummary(
                        clientId = Constants.TRAKT_CLIENT_ID,
                        username = parsed.username,
                        listId = parsed.listId
                    )
                    ResolvedCatalog(
                        title = summary.name.ifBlank { parsed.listId.replace('-', ' ') },
                        sourceRef = "trakt_user:${parsed.username}:${parsed.listId}"
                    )
                }.getOrNull()
            }
            is ParsedCatalogUrl.TraktList -> {
                runCatching {
                    val summary = traktApi.getListSummary(
                        clientId = Constants.TRAKT_CLIENT_ID,
                        listId = parsed.listId
                    )
                    ResolvedCatalog(
                        title = summary.name.ifBlank { parsed.listId.replace('-', ' ') },
                        sourceRef = "trakt_list:${parsed.listId}"
                    )
                }.getOrNull()
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

        val titleFromMeta = TITLE_FROM_META_REGEX.find(html)?.groupValues?.getOrNull(1)

        val titleFromTag = TITLE_FROM_TAG_REGEX.find(html)?.groupValues?.getOrNull(1)
            ?.replace(" - MDBList", "", ignoreCase = true)

        val titleFromSlug = extractMdblistSlugTitle(url)
        val finalTitle = (titleFromMeta ?: titleFromTag ?: titleFromSlug ?: "MDBList Catalog").trim()
        return ResolvedCatalog(
            title = finalTitle.ifBlank { "MDBList Catalog" },
            sourceRef = "mdblist:$url"
        )
    }

    private fun extractMdblistSlugTitle(url: String): String? {
        val pathSegments = runCatching { URI(url).path.trim('/') }
            .getOrNull()
            ?.split('/')
            ?.filter { it.isNotBlank() }
            .orEmpty()
        if (pathSegments.isEmpty()) return null
        val slug = pathSegments.last()
        if (slug.equals("lists", ignoreCase = true)) return null
        return slug.toDisplayTitle()
    }

    private fun extractTraktUrl(html: String): String? {
        return TRAKT_URL_REGEX.find(html)?.value
    }

    private suspend fun fetchUrl(url: String): String? {
        return withContext(Dispatchers.IO) {
            val request = Request.Builder()
                .url(url)
                .header("User-Agent", "Mozilla/5.0 (Android TV; ARVIO)")
                .build()
            runCatching {
                okHttpClient.newCall(request).execute().use { response ->
                    if (!response.isSuccessful) return@use null
                    response.body?.string()
                }
            }.getOrNull()
        }
    }

    private fun parseCatalogsJson(json: String?): List<CatalogConfig> {
        if (json.isNullOrBlank()) return emptyList()
        val strict = runCatching {
            gson.fromJson<List<CatalogConfig>>(json, listType) ?: emptyList()
        }.getOrElse { emptyList() }
            .mapNotNull { normalizeCatalogConfig(it) }
        if (strict.isNotEmpty()) return strict

        // Legacy/compat parse: recover from older/partial enum values so existing
        // custom catalogs don't disappear after app updates.
        return runCatching {
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
                val collectionSources = runCatching {
                    val jsonValue = gson.toJson(row["collectionSources"])
                    gson.fromJson<List<CollectionSourceConfig>>(
                        jsonValue,
                        TypeToken.getParameterized(List::class.java, CollectionSourceConfig::class.java).type
                    ) ?: emptyList()
                }.getOrDefault(emptyList())
                val requiredAddonUrls = runCatching {
                    val jsonValue = gson.toJson(row["requiredAddonUrls"])
                    gson.fromJson<List<String>>(
                        jsonValue,
                        TypeToken.getParameterized(List::class.java, String::class.java).type
                    )?.map { it.trim() }?.filter { it.isNotBlank() } ?: emptyList()
                }.getOrDefault(emptyList())
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
                        requiredAddonUrls = requiredAddonUrls
                    )
                )
            }
        }.getOrElse { emptyList() }
    }

    private fun normalizeCatalogConfig(config: CatalogConfig): CatalogConfig? {
        if (config.id.isBlank() || config.title.isBlank()) return null
        val normalizedUrl = config.sourceUrl?.trim().takeUnless { it.isNullOrBlank() }
        val normalizedRef = config.sourceRef?.trim().takeUnless { it.isNullOrBlank() }
        val bundledPreinstalled = isBundledPreinstalledCatalogId(config.id)
        val sourceRefAddon = parseAddonSourceRef(normalizedRef)
        val normalizedCollectionTileShape = runCatching {
            CollectionTileShape.valueOf(config.collectionTileShape.name)
        }.getOrDefault(CollectionTileShape.LANDSCAPE)
        val normalizedAddonId = config.addonId?.trim().takeUnless { it.isNullOrBlank() }
            ?: sourceRefAddon?.first
        val normalizedAddonType = normalizeAddonCatalogType(config.addonCatalogType)
            ?: sourceRefAddon?.second
        val normalizedAddonCatalogId = config.addonCatalogId?.trim().takeUnless { it.isNullOrBlank() }
            ?: sourceRefAddon?.third
        val normalizedAddonName = config.addonName?.trim().takeUnless { it.isNullOrBlank() }
        val inferredType = parseSourceTypeCompat(
            raw = config.sourceType.name,
            sourceUrl = normalizedUrl,
            sourceRef = normalizedRef
        )
        val normalizedKind = when {
            config.kind == CatalogKind.COLLECTION_RAIL -> CatalogKind.COLLECTION_RAIL
            config.collectionSources.isNotEmpty() -> CatalogKind.COLLECTION
            else -> config.kind
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
            collectionTileShape = normalizedCollectionTileShape,
            collectionHideTitle = config.collectionHideTitle,
            collectionSources = config.collectionSources,
            requiredAddonUrls = config.requiredAddonUrls.map { it.trim() }.filter { it.isNotBlank() }.distinct()
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
            sourceRef?.startsWith("trakt_", ignoreCase = true) == true -> CatalogSourceType.TRAKT
            sourceRef?.startsWith("mdblist", ignoreCase = true) == true -> CatalogSourceType.MDBLIST
            sourceUrl?.contains("trakt.tv", ignoreCase = true) == true -> CatalogSourceType.TRAKT
            sourceUrl?.contains("mdblist.com", ignoreCase = true) == true -> CatalogSourceType.MDBLIST
            normalized == CatalogSourceType.TRAKT.name -> CatalogSourceType.TRAKT
            normalized == CatalogSourceType.MDBLIST.name -> CatalogSourceType.MDBLIST
            normalized == CatalogSourceType.ADDON.name -> CatalogSourceType.ADDON
            normalized == CatalogSourceType.HOME_SERVER.name -> CatalogSourceType.HOME_SERVER
            normalized == CatalogSourceType.PREINSTALLED.name -> CatalogSourceType.PREINSTALLED
            normalized.contains("HOME_SERVER") || normalized.contains("HOME SERVER") -> CatalogSourceType.HOME_SERVER
            normalized.contains("ADDON") -> CatalogSourceType.ADDON
            normalized.contains("TRAKT") -> CatalogSourceType.TRAKT
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
        if (primary.isNotEmpty()) {
            val base = primary
                .distinctBy { it.id }
                .map { refreshBundledPreinstalledCatalog(it) }
                .filterNot { it.isHidden() }
                .toMutableList()
            val existingKeys = base.map { "${it.id}|${it.sourceUrl.orEmpty()}" }.toMutableSet()

            // Legacy recovery applies only to the default profile to avoid cross-profile leakage.
            if (profileId == "default") {
                val legacyCustom = (
                    parseCatalogsJson(prefs[legacyDefaultKey]) +
                    parseCatalogsJson(prefs[legacyGlobalKey])
                )
                    .filterNot { it.isPreinstalled }
                    .filterNot { it.isHidden() }
                    .distinctBy { "${it.id}|${it.sourceUrl.orEmpty()}" }

                legacyCustom.forEach { cfg ->
                    val key = "${cfg.id}|${cfg.sourceUrl.orEmpty()}"
                    if (!existingKeys.contains(key)) {
                        base.add(cfg)
                        existingKeys.add(key)
                    }
                }
            }
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
