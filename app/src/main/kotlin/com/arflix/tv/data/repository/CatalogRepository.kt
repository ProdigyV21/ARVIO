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
import com.arflix.tv.data.model.CatalogSourceType
import com.arflix.tv.data.model.CatalogValidationResult
import com.arflix.tv.data.model.Category
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
    private val okHttpClient: OkHttpClient
) {
    private companion object {
        private const val ADDON_SOURCE_REF_PREFIX = "addon_catalog|"
    }

    private val gson = Gson()
    private fun catalogsKey(profileId: String) = stringPreferencesKey("profile_${profileId}_catalogs_v1")
    private fun hiddenPreinstalledKey(profileId: String) = stringPreferencesKey("profile_${profileId}_hidden_preinstalled_catalogs_v1")
    private val legacyDefaultKey = stringPreferencesKey("profile_default_catalogs_v1")
    private val legacyGlobalKey = stringPreferencesKey("catalogs_v1")
    private val listType = object : TypeToken<List<CatalogConfig>>() {}.type
    private val hiddenListType = object : TypeToken<List<String>>() {}.type

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

    private suspend fun hidePreinstalledCatalog(profileId: String, catalogId: String) {
        val trimmed = catalogId.trim()
        if (trimmed.isBlank()) return
        context.settingsDataStore.edit { prefs ->
            val hidden = decodeHiddenPreinstalled(profileId, prefs).toMutableSet()
            hidden.add(trimmed)
            prefs[hiddenPreinstalledKey(profileId)] = gson.toJson(hidden.toList())
        }
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
        val primary = parseCatalogsJson(prefs[catalogsKey(profileId)]).distinctBy { it.id }
        val resolved = readCatalogsFromPrefs(profileId, prefs)
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
        return readCatalogsFromPrefs(safeProfileId, prefs)
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
    }

    private suspend fun saveCatalogs(catalogs: List<CatalogConfig>) {
        val profileId = activeProfileId()
        context.settingsDataStore.edit { prefs ->
            prefs[catalogsKey(profileId)] = gson.toJson(catalogs)
        }
    }

    suspend fun replaceCatalogsForProfile(profileId: String, catalogs: List<CatalogConfig>) {
        val safeProfileId = profileId.trim().ifBlank { "default" }
        val sanitized = catalogs
            .distinctBy { it.id }
            .map { cfg ->
                val looksCustom = cfg.id.startsWith("custom_") ||
                    cfg.sourceType == CatalogSourceType.ADDON ||
                    !cfg.sourceUrl.isNullOrBlank() ||
                    !cfg.sourceRef.isNullOrBlank()
                if (looksCustom) cfg.copy(isPreinstalled = false) else cfg
            }
        context.settingsDataStore.edit { prefs ->
            prefs[catalogsKey(safeProfileId)] = gson.toJson(sanitized)
        }
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

        val existing = getCatalogs().map { cfg ->
            val looksCustom = cfg.id.startsWith("custom_") ||
                !cfg.sourceUrl.isNullOrBlank() ||
                !cfg.sourceRef.isNullOrBlank()
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
                    defaultMap[config.id]
                } else {
                    config
                }
            }.toMutableList()

            val missingPreinstalled = effectiveDefaults.filter { pre ->
                kept.none { it.id == pre.id }
            }
            kept.addAll(missingPreinstalled)
            kept
        }

        if (existing != merged) {
            saveCatalogs(merged)
        }
        return merged
    }

    suspend fun syncAddonCatalogs(addons: List<Addon>): Boolean {
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
        if (target.isPreinstalled) {
            val profileId = activeProfileId()
            hidePreinstalledCatalog(profileId, catalogId)
        }
        current.removeAll { it.id == catalogId }
        saveCatalogs(current)
        return Result.success(Unit)
    }

    suspend fun moveCatalogUp(catalogId: String): Boolean {
        val current = getCatalogs().toMutableList()
        val index = current.indexOfFirst { it.id == catalogId }
        if (index <= 0) return false
        val moved = current.removeAt(index)
        current.add(index - 1, moved)
        saveCatalogs(current)
        return true
    }

    suspend fun moveCatalogDown(catalogId: String): Boolean {
        val current = getCatalogs().toMutableList()
        val index = current.indexOfFirst { it.id == catalogId }
        if (index < 0 || index >= current.lastIndex) return false
        val moved = current.removeAt(index)
        current.add(index + 1, moved)
        saveCatalogs(current)
        return true
    }

    suspend fun replaceCatalogsForActiveProfile(catalogs: List<CatalogConfig>) {
        val sanitized = catalogs
            .distinctBy { it.id }
            .map { cfg ->
                val looksCustom = cfg.id.startsWith("custom_") ||
                    cfg.sourceType == CatalogSourceType.ADDON ||
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

        val titleFromMeta = Regex(
            """<meta\s+property=["']og:title["']\s+content=["']([^"']+)["']""",
            RegexOption.IGNORE_CASE
        ).find(html)?.groupValues?.getOrNull(1)

        val titleFromTag = Regex(
            """<title>([^<]+)</title>""",
            RegexOption.IGNORE_CASE
        ).find(html)?.groupValues?.getOrNull(1)
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
        return Regex(
            """https?://(?:www\.)?trakt\.tv/users/[^"'\s<]+/lists/[^"'\s<]+""",
            RegexOption.IGNORE_CASE
        ).find(html)?.value
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
            val rawType = object : TypeToken<List<Map<String, Any?>>>() {}.type
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
                        addonName = addonName
                    )
                )
            }
        }.getOrElse { emptyList() }
    }

    private fun normalizeCatalogConfig(config: CatalogConfig): CatalogConfig? {
        if (config.id.isBlank() || config.title.isBlank()) return null
        val normalizedUrl = config.sourceUrl?.trim().takeUnless { it.isNullOrBlank() }
        val normalizedRef = config.sourceRef?.trim().takeUnless { it.isNullOrBlank() }
        val sourceRefAddon = parseAddonSourceRef(normalizedRef)
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
        val normalizedPreinstalled = when {
            normalizedUrl != null -> false
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
            addonName = if (inferredType == CatalogSourceType.ADDON) normalizedAddonName else null
        )
    }

    private fun parseSourceTypeCompat(
        raw: String,
        sourceUrl: String?,
        sourceRef: String?
    ): CatalogSourceType {
        val normalized = raw.trim().uppercase()
        return when {
            // URL/ref evidence always wins over stale enum values from older builds.
            sourceRef?.startsWith(ADDON_SOURCE_REF_PREFIX, ignoreCase = true) == true -> CatalogSourceType.ADDON
            sourceRef?.startsWith("trakt_", ignoreCase = true) == true -> CatalogSourceType.TRAKT
            sourceRef?.startsWith("mdblist", ignoreCase = true) == true -> CatalogSourceType.MDBLIST
            sourceUrl?.contains("trakt.tv", ignoreCase = true) == true -> CatalogSourceType.TRAKT
            sourceUrl?.contains("mdblist.com", ignoreCase = true) == true -> CatalogSourceType.MDBLIST
            normalized == CatalogSourceType.TRAKT.name -> CatalogSourceType.TRAKT
            normalized == CatalogSourceType.MDBLIST.name -> CatalogSourceType.MDBLIST
            normalized == CatalogSourceType.ADDON.name -> CatalogSourceType.ADDON
            normalized == CatalogSourceType.PREINSTALLED.name -> CatalogSourceType.PREINSTALLED
            normalized.contains("ADDON") -> CatalogSourceType.ADDON
            normalized.contains("TRAKT") -> CatalogSourceType.TRAKT
            normalized.contains("MDB") || normalized.contains("MDL") -> CatalogSourceType.MDBLIST
            sourceUrl.isNullOrBlank() -> CatalogSourceType.PREINSTALLED
            else -> CatalogSourceType.TRAKT
        }
    }

    private fun readCatalogsFromPrefs(profileId: String, prefs: Preferences): List<CatalogConfig> {
        val hiddenPreinstalled = decodeHiddenPreinstalled(profileId, prefs)

        // Strict profile-first lookup to avoid leaking or prioritizing
        // catalogs from other profiles.
        val primary = parseCatalogsJson(prefs[catalogsKey(profileId)])
        if (primary.isNotEmpty()) {
            val base = primary
                .distinctBy { it.id }
                .filterNot { cfg -> cfg.isPreinstalled && cfg.id in hiddenPreinstalled }
                .toMutableList()
            val existingKeys = base.map { "${it.id}|${it.sourceUrl.orEmpty()}" }.toMutableSet()

            // Legacy recovery applies only to the default profile to avoid cross-profile leakage.
            if (profileId == "default") {
                val legacyCustom = (
                    parseCatalogsJson(prefs[legacyDefaultKey]) +
                    parseCatalogsJson(prefs[legacyGlobalKey])
                )
                    .filterNot { it.isPreinstalled }
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
                .filterNot { cfg -> cfg.isPreinstalled && cfg.id in hiddenPreinstalled }
        }

        val legacyGlobal = parseCatalogsJson(prefs[legacyGlobalKey])
        if (legacyGlobal.isNotEmpty()) {
            return legacyGlobal
                .distinctBy { it.id }
                .filterNot { cfg -> cfg.isPreinstalled && cfg.id in hiddenPreinstalled }
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
