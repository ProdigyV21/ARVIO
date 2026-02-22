package com.arflix.tv.data.repository

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.arflix.tv.data.api.*
import com.arflix.tv.data.model.Addon
import com.arflix.tv.data.model.AddonManifest
import com.arflix.tv.data.model.AddonResource
import com.arflix.tv.data.model.AddonStreamResult
import com.arflix.tv.data.model.AddonType
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.model.ProxyHeaders as ModelProxyHeaders
import com.arflix.tv.data.model.StreamBehaviorHints as ModelStreamBehaviorHints
import com.arflix.tv.data.model.StreamSource
import com.arflix.tv.data.model.Subtitle
import com.arflix.tv.util.AnimeMapper
import com.arflix.tv.util.Constants
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.withTimeoutOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import java.net.URLDecoder
import java.net.URLEncoder
import java.security.MessageDigest
import java.util.Locale
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

private val Context.streamDataStore: DataStore<Preferences> by preferencesDataStore(name = "stream_prefs")

/**
 * Callback for streaming results as they arrive - like NuvioStreaming
 */
typealias StreamCallback = (streams: List<StreamSource>?, addonId: String, addonName: String, error: Exception?) -> Unit

/**
 * Repository for stream resolution from Stremio addons
 * Enhanced with addon management
 */
@Singleton
class StreamRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val streamApi: StreamApi,
    private val okHttpClient: OkHttpClient,
    private val profileManager: ProfileManager,
    private val animeMapper: AnimeMapper,
    private val iptvRepository: IptvRepository
) {
    private val gson = Gson()
    private val TAG = "StreamRepository"
    private val openSubtitlesUrl = "https://opensubtitles-v3.strem.io/subtitles"
    private data class CachedStreamResult(
        val result: StreamResult,
        val createdAtMs: Long
    )
    private val streamResultCache = mutableMapOf<String, CachedStreamResult>()

    // Profile-scoped preference keys - each profile has its own addons
    private fun addonsKey() = profileManager.profileStringKey("installed_addons")
    private fun addonsKeyFor(profileId: String) = profileManager.profileStringKeyFor(profileId, "installed_addons")
    private fun pendingAddonsKey() = profileManager.profileStringKey("pending_addons")
    private fun pendingAddonsKeyFor(profileId: String) = profileManager.profileStringKeyFor(profileId, "pending_addons")
    private fun hiddenBuiltInAddonsKey() = profileManager.profileStringKey("hidden_builtin_addons_v1")
    private fun torrServerBaseUrlKey() = profileManager.profileStringKey("torrserver_base_url_v1")
    fun observeTorrServerBaseUrl(): Flow<String> =
        profileManager.activeProfileId.combine(context.streamDataStore.data) { _, prefs ->
            prefs[torrServerBaseUrlKey()].orEmpty()
        }

    suspend fun setTorrServerBaseUrl(raw: String) {
        // Allow blank to reset to default autodetect.
        context.streamDataStore.edit { prefs -> prefs[torrServerBaseUrlKey()] = raw.trim() }
    }

    // Default addons - only built-in sources that work without configuration
    // Users must add their own streaming addons via Settings > Addons
    private val defaultAddons = listOf(
        AddonConfig(
            id = "opensubtitles",
            name = "OpenSubtitles v3",
            baseUrl = "https://opensubtitles-v3.strem.io/subtitles",
            type = AddonType.SUBTITLE,
            isEnabled = true
        )
    )

    private fun decodeHiddenBuiltIns(prefs: Preferences): Set<String> {
        val raw = prefs[hiddenBuiltInAddonsKey()].orEmpty()
        if (raw.isBlank()) return emptySet()
        return runCatching {
            val type = object : TypeToken<List<String>>() {}.type
            val items: List<String> = gson.fromJson(raw, type) ?: emptyList()
            items.map { it.trim() }.filter { it.isNotBlank() }.toSet()
        }.getOrDefault(emptySet())
    }

    private suspend fun hideBuiltInAddon(addonId: String) {
        val trimmed = addonId.trim()
        if (trimmed.isBlank()) return
        context.streamDataStore.edit { prefs ->
            val hidden = decodeHiddenBuiltIns(prefs).toMutableSet()
            hidden.add(trimmed)
            prefs[hiddenBuiltInAddonsKey()] = gson.toJson(hidden.toList())
        }
    }

    // ========== Addon Management ==========

    val installedAddons: Flow<List<Addon>> =
        profileManager.activeProfileId.combine(context.streamDataStore.data) { _, prefs ->
        val json = prefs[addonsKey()]
        val pendingJson = prefs[pendingAddonsKey()]
        val addons = parseAddons(json)
            ?: parseAddons(pendingJson)
            ?: run {
                getDefaultAddonList()
            }
        enforceOpenSubtitles(addons)
    }

    private fun getDefaultAddonList(): List<Addon> {
        return defaultAddons.map { config ->
            Addon(
                id = config.id,
                name = config.name,
                version = "1.0.0",
                description = when (config.id) {
                    "opensubtitles" -> "Subtitles from OpenSubtitles"
                    else -> ""
                },
                isInstalled = true,
                isEnabled = true,
                type = config.type,
                url = config.baseUrl,
                transportUrl = getTransportUrl(config.baseUrl)
            )
        }
    }

    private fun canonicalOpenSubtitles(addon: Addon? = null): Addon {
        return (addon ?: Addon(
            id = "opensubtitles",
            name = "OpenSubtitles v3",
            version = "1.0.0",
            description = "Subtitles from OpenSubtitles",
            isInstalled = true,
            isEnabled = true,
            type = AddonType.SUBTITLE,
            url = openSubtitlesUrl,
            transportUrl = openSubtitlesUrl
        )).copy(
            id = "opensubtitles",
            name = "OpenSubtitles v3",
            version = addon?.version ?: "1.0.0",
            description = "Subtitles from OpenSubtitles",
            isInstalled = true,
            isEnabled = true,
            type = AddonType.SUBTITLE,
            url = openSubtitlesUrl,
            transportUrl = openSubtitlesUrl
        )
    }

    private fun enforceOpenSubtitles(addons: List<Addon>): List<Addon> {
        val merged = LinkedHashMap<String, Addon>()
        addons.forEach { addon ->
            if (addon.id == "opensubtitles") {
                merged["opensubtitles"] = canonicalOpenSubtitles(addon)
            } else if (!merged.containsKey(addon.id)) {
                merged[addon.id] = addon
            }
        }
        if (!merged.containsKey("opensubtitles")) {
            merged["opensubtitles"] = canonicalOpenSubtitles()
        }
        return merged.values.toList()
    }

    suspend fun toggleAddon(addonId: String) {
        if (addonId == "opensubtitles") return
        val addons = installedAddons.first().toMutableList()
        val index = addons.indexOfFirst { it.id == addonId }
        if (index >= 0) {
            addons[index] = addons[index].copy(isEnabled = !addons[index].isEnabled)
            saveAddons(addons)
        }
    }

    /**
     * Add a custom Stremio addon from URL - like NuvioStreaming
     * Fetches manifest and stores addon info
     */
    suspend fun addCustomAddon(url: String, customName: String? = null): Result<Addon> = withContext(Dispatchers.IO) {
        try {
            val normalizedUrl = normalizeAddonInputUrl(url)
            if (normalizedUrl.isBlank()) {
                return@withContext Result.failure(IllegalArgumentException("Addon URL is empty"))
            }
            val manifestUrl = getManifestUrl(normalizedUrl)

            val manifest = streamApi.getAddonManifest(manifestUrl)

            val transportUrl = getTransportUrl(normalizedUrl)
            val addonManifest = convertToAddonManifest(manifest)
            val resolvedName = customName?.trim()?.takeIf { it.isNotBlank() } ?: manifest.name
            val addonId = buildAddonInstanceId(manifest.id, normalizedUrl)

            val newAddon = Addon(
                id = addonId,
                name = resolvedName,
                version = manifest.version,
                description = manifest.description ?: "",
                isInstalled = true,
                isEnabled = true,
                type = AddonType.CUSTOM,
                url = normalizedUrl,
                logo = manifest.logo,
                manifest = addonManifest,
                transportUrl = transportUrl
            )

            val addons = installedAddons.first().toMutableList()
            // Remove existing addon with same ID if present
            addons.removeAll { it.id == addonId }
            addons.add(newAddon)
            saveAddons(addons)

            Result.success(newAddon)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun buildAddonInstanceId(manifestId: String, url: String): String {
        val normalized = url.trim().lowercase()
        val digest = MessageDigest.getInstance("SHA-256").digest(normalized.toByteArray())
        val shortHash = digest.take(6).joinToString("") { "%02x".format(it) }
        return "${manifestId}_$shortHash"
    }

    /**
     * Convert API manifest response to our model
     */
    private fun convertToAddonManifest(manifest: StremioManifestResponse): AddonManifest {
        val resources = manifest.resources?.mapNotNull { resource ->
            when (resource) {
                is String -> AddonResource(name = resource)
                is Map<*, *> -> {
                    @Suppress("UNCHECKED_CAST")
                    val map = resource as Map<String, Any?>
                    AddonResource(
                        name = map["name"] as? String ?: return@mapNotNull null,
                        types = (map["types"] as? List<*>)?.filterIsInstance<String>() ?: emptyList(),
                        idPrefixes = (map["idPrefixes"] as? List<*>)?.filterIsInstance<String>()
                    )
                }
                else -> null
            }
        } ?: emptyList()

        val catalogs = manifest.catalogs?.map { catalog ->
            com.arflix.tv.data.model.AddonCatalog(
                type = catalog.type,
                id = catalog.id,
                name = catalog.name ?: "",
                genres = catalog.genres,
                extra = catalog.extra?.map { extra ->
                    com.arflix.tv.data.model.AddonCatalogExtra(
                        name = extra.name,
                        isRequired = extra.isRequired ?: false,
                        options = extra.options
                    )
                }
            )
        } ?: emptyList()

        return AddonManifest(
            id = manifest.id,
            name = manifest.name,
            version = manifest.version,
            description = manifest.description ?: "",
            logo = manifest.logo,
            background = manifest.background,
            types = manifest.types ?: emptyList(),
            resources = resources,
            catalogs = catalogs,
            idPrefixes = manifest.idPrefixes,
            behaviorHints = manifest.behaviorHints?.let {
                com.arflix.tv.data.model.AddonBehaviorHints(
                    adult = it.adult ?: false,
                    p2p = it.p2p ?: false,
                    configurable = it.configurable ?: false,
                    configurationRequired = it.configurationRequired ?: false
                )
            }
        )
    }

    suspend fun removeAddon(addonId: String) {
        if (addonId == "opensubtitles") return
        val current = installedAddons.first()
        val addons = current.filter { it.id != addonId }
        saveAddons(addons)
    }

    suspend fun replaceAddonsFromCloud(addons: List<Addon>) {
        saveAddons(enforceOpenSubtitles(addons))
    }

    suspend fun getAddonsForProfile(profileId: String): List<Addon> {
        val prefs = context.streamDataStore.data.first()
        val stored = parseAddons(prefs[addonsKeyFor(profileId)])
        return enforceOpenSubtitles(stored ?: getDefaultAddonList())
    }

    suspend fun replaceAddonsForProfile(profileId: String, addons: List<Addon>) {
        val resolved = enforceOpenSubtitles(addons)
        context.streamDataStore.edit { prefs ->
            prefs[addonsKeyFor(profileId)] = gson.toJson(resolved)
            prefs.remove(pendingAddonsKeyFor(profileId))
        }
        if (profileManager.getProfileIdSync() == profileId) {
            synchronized(streamResultCache) { streamResultCache.clear() }
        }
    }

    private suspend fun saveAddons(addons: List<Addon>) {
        val json = gson.toJson(addons)

        // Save locally (profile-scoped only).
        context.streamDataStore.edit { prefs ->
            prefs[addonsKey()] = json
            prefs.remove(pendingAddonsKey())
        }
        synchronized(streamResultCache) { streamResultCache.clear() }
    }

    /**
     * Load addons from Supabase profile (called on login)
     * Merges cloud addons with local defaults
     */
    suspend fun syncAddonsFromCloud() {
        // Deprecated path: addons are synced via account_sync_state payload per profile.
    }

    private fun parseAddons(json: String?): List<Addon>? {
        if (json.isNullOrBlank()) return null
        return try {
            val type = TypeToken.getParameterized(List::class.java, Addon::class.java).type
            val parsed: List<Addon> = gson.fromJson(json, type)
            parsed
        } catch (e: Exception) {
            null
        }
    }

    private fun mergeAddonLists(primary: List<Addon>, secondary: List<Addon>): List<Addon> {
        val merged = LinkedHashMap<String, Addon>()
        primary.forEach { addon -> merged[addon.id] = addon }
        secondary.forEach { addon -> merged.putIfAbsent(addon.id, addon) }
        return merged.values.toList()
    }

    /**
     * Get manifest URL from addon URL - like NuvioStreaming getAddonBaseURL
     */
    private fun getManifestUrl(url: String): String {
        var cleanUrl = normalizeAddonInputUrl(url)
        val parts = cleanUrl.split("?", limit = 2)
        val baseUrl = parts[0].trimEnd('/')
        val query = parts.getOrNull(1)
        val manifestBase = if (baseUrl.endsWith("manifest.json")) {
            baseUrl
        } else {
            "$baseUrl/manifest.json"
        }
        return if (query.isNullOrBlank()) {
            manifestBase
        } else {
            "$manifestBase?$query"
        }
    }

    /**
     * Get transport URL (base URL without manifest.json) - like NuvioStreaming
     */
    private fun getTransportUrl(url: String): String {
        var cleanUrl = normalizeAddonInputUrl(url)
        cleanUrl = cleanUrl.trimEnd('/')
        // Remove common suffixes that shouldn't be in the base URL
        cleanUrl = cleanUrl.removeSuffix("/manifest.json")
        cleanUrl = cleanUrl.removeSuffix("/stream")  // Some addons incorrectly include /stream
        cleanUrl = cleanUrl.removeSuffix("/catalog")
        return cleanUrl
    }

    /**
     * Normalize addon install links so we can accept both:
     * - https://host/.../manifest.json
     * - stremio://host/.../manifest.json
     */
    private fun normalizeAddonInputUrl(rawUrl: String): String {
        var cleanUrl = rawUrl.trim()
        if (cleanUrl.isBlank()) return cleanUrl

        // Stremio deep-link format used by addon websites.
        if (cleanUrl.startsWith("stremio://", ignoreCase = true)) {
            val payload = cleanUrl.substringAfter("://", missingDelimiterValue = "").trim()
            cleanUrl = if (payload.startsWith("http://", ignoreCase = true) ||
                payload.startsWith("https://", ignoreCase = true)
            ) {
                payload
            } else {
                "https://$payload"
            }
        }

        if (!cleanUrl.startsWith("http://", ignoreCase = true) &&
            !cleanUrl.startsWith("https://", ignoreCase = true)
        ) {
            cleanUrl = "https://$cleanUrl"
        }

        // Drop fragments (not part of Stremio addon endpoints).
        cleanUrl = cleanUrl.substringBefore('#')
        // Handle common manifest typo variants like manifest.jsonv / manifest.json123.
        cleanUrl = cleanUrl.replace(
            Regex("""(?i)/manifest\.json[a-z0-9_-]+(?=($|[?]))"""),
            "/manifest.json"
        )
        return cleanUrl.trim()
    }

    /**
     * Get base URL with optional query params - like NuvioStreaming getAddonBaseURL
     */
    private fun getAddonBaseUrl(url: String): Pair<String, String?> {
        val parts = url.split("?", limit = 2)
        val baseUrl = getTransportUrl(parts[0])
        val queryParams = parts.getOrNull(1)
        return Pair(baseUrl, queryParams)
    }

    suspend fun getAddonCatalogPage(
        addonId: String,
        catalogType: String,
        catalogId: String,
        skip: Int = 0
    ): StremioCatalogResponse = withContext(Dispatchers.IO) {
        val addon = installedAddons.first().firstOrNull { it.id == addonId }
            ?: throw IllegalArgumentException("Addon not found")
        val addonUrl = addon.url ?: throw IllegalArgumentException("Addon URL missing")
        val (baseUrl, queryParams) = getAddonBaseUrl(addonUrl)

        val queryBase = queryParams?.takeIf { it.isNotBlank() }
        val typeCandidates = catalogTypeAliases(catalogType)
        var firstSuccessful: StremioCatalogResponse? = null

        for (typeCandidate in typeCandidates) {
            val urls = buildCatalogRequestUrls(
                baseUrl = baseUrl,
                catalogType = typeCandidate,
                catalogId = catalogId,
                skip = skip,
                queryBase = queryBase
            )
            for (url in urls) {
                val response = runCatching { streamApi.getAddonCatalog(url) }.getOrNull() ?: continue
                if (firstSuccessful == null) {
                    firstSuccessful = response
                }
                val hasItems = !response.metas.isNullOrEmpty() || !response.items.isNullOrEmpty()
                if (hasItems) {
                    return@withContext response
                }
            }
        }

        firstSuccessful ?: StremioCatalogResponse(metas = emptyList())
    }

    suspend fun getAddonMeta(
        addonId: String,
        mediaType: String,
        mediaId: String
    ): StremioMetaPreview? = withContext(Dispatchers.IO) {
        val addon = installedAddons.first().firstOrNull { it.id == addonId }
            ?: return@withContext null
        val addonUrl = addon.url ?: return@withContext null
        val (baseUrl, queryParams) = getAddonBaseUrl(addonUrl)

        val typeCandidates = catalogTypeAliases(mediaType)
        val encodedId = URLEncoder.encode(mediaId, "UTF-8")
        for (typeCandidate in typeCandidates) {
            val encodedType = URLEncoder.encode(typeCandidate, "UTF-8")
            val query = queryParams?.takeIf { it.isNotBlank() }?.let { "?$it" }.orEmpty()
            val url = "$baseUrl/meta/$encodedType/$encodedId.json$query"
            val meta = runCatching { streamApi.getAddonMeta(url).meta }.getOrNull()
            if (meta != null) return@withContext meta
        }
        null
    }

    private fun catalogTypeAliases(rawType: String): List<String> {
        return when (rawType.trim().lowercase(Locale.US)) {
            "series" -> listOf("series", "tv", "show", "shows")
            "tv" -> listOf("tv", "series", "show", "shows")
            "show" -> listOf("show", "shows", "series", "tv")
            "shows" -> listOf("shows", "show", "series", "tv")
            else -> listOf(rawType.trim())
        }.distinct()
    }

    private fun buildCatalogRequestUrls(
        baseUrl: String,
        catalogType: String,
        catalogId: String,
        skip: Int,
        queryBase: String?
    ): List<String> {
        val encodedType = URLEncoder.encode(catalogType, "UTF-8")
        val encodedCatalogId = URLEncoder.encode(catalogId, "UTF-8")

        val withSkipQuery = mutableListOf<String>()
        if (!queryBase.isNullOrBlank()) {
            withSkipQuery += queryBase
        }
        if (skip > 0) {
            withSkipQuery += "skip=$skip"
        }

        val defaultQuery = if (withSkipQuery.isEmpty()) "" else "?${withSkipQuery.joinToString("&")}"
        val defaultUrl = "$baseUrl/catalog/$encodedType/$encodedCatalogId.json$defaultQuery"
        if (skip <= 0) {
            return listOf(defaultUrl)
        }

        val pathExtra = "skip=$skip"
        val pathExtraUrl = if (queryBase.isNullOrBlank()) {
            "$baseUrl/catalog/$encodedType/$encodedCatalogId/$pathExtra.json"
        } else {
            "$baseUrl/catalog/$encodedType/$encodedCatalogId/$pathExtra.json?$queryBase"
        }
        return listOf(defaultUrl, pathExtraUrl).distinct()
    }

    // ========== Stream Resolution ==========

    /**
     * Filter addons that support streaming for the given content type - like NuvioStreaming
     * More lenient filtering to ensure custom addons work
     */
    private fun getStreamAddons(addons: List<Addon>, type: String, id: String): List<Addon> {
        return addons.filter { addon ->
            // Must be installed and enabled
            if (!addon.isInstalled || !addon.isEnabled) return@filter false

            // Skip subtitle addons
            if (addon.type == AddonType.SUBTITLE) return@filter false

            // Must have a URL to fetch from
            if (addon.url.isNullOrBlank()) return@filter false

            // For custom addons, require stream support when manifest is available.
            // Otherwise we may probe catalog-only addons and add unnecessary latency.
            if (addon.type == AddonType.CUSTOM) {
                val manifest = addon.manifest
                if (manifest != null && manifest.resources.isNotEmpty()) {
                    val supportsStream = manifest.resources.any { resource ->
                        resource.name == "stream" &&
                            (resource.types.isEmpty() ||
                                resource.types.contains(type) ||
                                resource.types.contains("movie") ||
                                resource.types.contains("series"))
                    }
                    return@filter supportsStream
                }
                return@filter true
            }

            // If addon has manifest with resource info, check it
            val manifest = addon.manifest
            if (manifest != null && manifest.resources.isNotEmpty()) {
                val hasStreamResource = manifest.resources.any { resource ->
                    resource.name == "stream" &&
                    (resource.types.isEmpty() || resource.types.contains(type) || resource.types.contains("movie") || resource.types.contains("series")) &&
                    (resource.idPrefixes == null || resource.idPrefixes.isEmpty() || resource.idPrefixes.any { id.startsWith(it) })
                }
                if (hasStreamResource) return@filter true
            }

            // Check global idPrefixes if present (but be lenient)
            val idPrefixes = manifest?.idPrefixes
            if (idPrefixes != null && idPrefixes.isNotEmpty()) {
                if (!idPrefixes.any { id.startsWith(it) }) return@filter false
            }

            // Default: assume addon supports streaming (be lenient for unknown addons)
            true
        }
    }

    // Stream source requests should return quickly to keep player startup responsive.
    // Most addons respond in 1-3 seconds; 5s is enough to catch slow ones without blocking UI.
    private val ADDON_TIMEOUT_MS = 5000L
    // Subtitles should never block source availability.
    private val SUBTITLE_TIMEOUT_MS = 3000L
    // If addons return nothing, allow a short Xtream VOD lookup to recover playback.
    private val VOD_LOOKUP_TIMEOUT_MS = 2500L
    // If addons already returned streams, keep VOD lookup short to avoid UI delay.
    private val VOD_APPEND_TIMEOUT_MS = 1000L
    private val STREAM_RESULT_CACHE_TTL_MS = 120_000L
    private val STREAM_RESULT_CACHE_HTTP_TTL_MS = 30_000L
    private val STREAM_RESULT_CACHE_HTTP_EPHEMERAL_TTL_MS = 10_000L

    private fun streamCacheKey(
        profileId: String,
        type: String,
        imdbId: String,
        season: Int? = null,
        episode: Int? = null
    ): String {
        return "$profileId|$type|$imdbId|${season ?: 0}|${episode ?: 0}"
    }

    private fun cacheTtlMsFor(result: StreamResult): Long {
        val streams = result.streams
        if (streams.isEmpty()) return STREAM_RESULT_CACHE_TTL_MS

        val hasHttp = streams.any { stream ->
            val url = stream.url?.trim().orEmpty()
            url.startsWith("http://", ignoreCase = true) || url.startsWith("https://", ignoreCase = true)
        }
        if (!hasHttp) return STREAM_RESULT_CACHE_TTL_MS

        val hasEphemeralHttp = streams.any { stream ->
            val url = stream.url?.trim().orEmpty().lowercase(Locale.US)
            val tokenizedUrl = url.contains("token=") ||
                url.contains("expires=") ||
                url.contains("signature=") ||
                url.contains("sig=") ||
                url.contains("exp=")
            tokenizedUrl ||
                stream.behaviorHints?.notWebReady == true ||
                !stream.behaviorHints?.proxyHeaders?.request.isNullOrEmpty()
        }

        return if (hasEphemeralHttp) {
            STREAM_RESULT_CACHE_HTTP_EPHEMERAL_TTL_MS
        } else {
            STREAM_RESULT_CACHE_HTTP_TTL_MS
        }
    }

    private fun isStreamCacheFresh(cached: CachedStreamResult): Boolean {
        val ageMs = System.currentTimeMillis() - cached.createdAtMs
        return ageMs < cacheTtlMsFor(cached.result)
    }

    /**
     * Resolve streams for a movie using INSTALLED addons
     * Uses progressive loading - streams appear as each addon responds
     */
    suspend fun resolveMovieStreams(
        imdbId: String,
        title: String = "",
        year: Int? = null,
        forceRefresh: Boolean = false
    ): StreamResult = withContext(Dispatchers.IO) {
        val subtitles = mutableListOf<Subtitle>()
        val allAddons = installedAddons.first()
        val streamAddons = getStreamAddons(allAddons, "movie", imdbId)
        val cacheKey = streamCacheKey(
            profileId = profileManager.getProfileIdSync(),
            type = "movie",
            imdbId = imdbId
        )
        if (!forceRefresh) {
            synchronized(streamResultCache) {
                val cached = streamResultCache[cacheKey]
                if (cached != null && isStreamCacheFresh(cached)) {
                    return@withContext cached.result
                }
            }
        }

        val streamJobs = streamAddons.map { addon ->
            async {
                try {
                    withTimeout(ADDON_TIMEOUT_MS) {
                        val (baseUrl, queryParams) = getAddonBaseUrl(addon.url ?: return@withTimeout emptyList())
                        val url = if (queryParams != null) {
                            "$baseUrl/stream/movie/$imdbId.json?$queryParams"
                        } else {
                            "$baseUrl/stream/movie/$imdbId.json"
                        }
                        val response = streamApi.getAddonStreams(url)
                        processStreams(response.streams ?: emptyList(), addon)
                    }
                } catch (_: TimeoutCancellationException) {
                    emptyList()
                } catch (_: Exception) {
                    emptyList()
                }
            }
        }
        val streams = streamJobs.awaitAll().flatten().toMutableList()

        // Keep core source lookup fully addon-driven and non-blocking.
        // IPTV VOD enrichment is appended separately in ViewModels.

        val result = StreamResult(streams, subtitles)
        synchronized(streamResultCache) {
            streamResultCache[cacheKey] = CachedStreamResult(result = result, createdAtMs = System.currentTimeMillis())
        }
        result
    }

    suspend fun resolveMovieVodOnly(
        imdbId: String?,
        title: String = "",
        year: Int? = null,
        tmdbId: Int? = null,
        timeoutMs: Long = 8_000L
    ): StreamSource? = withContext(Dispatchers.IO) {
        withTimeoutOrNull(timeoutMs.coerceIn(500L, 90_000L)) {
            runCatching {
                iptvRepository.findMovieVodSource(
                    title = title,
                    year = year,
                    imdbId = imdbId,
                    tmdbId = tmdbId,
                    allowNetwork = true
                )
            }.getOrNull()
        }
    }

    /**
     * Process raw streams into StreamSource objects - like NuvioStreaming processStreams
     */
    private fun processStreams(streams: List<StremioStream>, addon: Addon): List<StreamSource> {
        val filtered = streams.filter { stream ->
            stream.hasPlayableLink() && isSupportedPlaybackCandidate(stream)
        }

        return filtered
            .map { stream ->
                val rawStreamUrl = stream.getStreamUrl()
                val streamUrl = when {
                    !rawStreamUrl.isNullOrBlank() -> rawStreamUrl
                    // Some addons use ytId without providing a URL.
                    !stream.ytId.isNullOrBlank() -> "https://www.youtube.com/watch?v=${stream.ytId}"
                    else -> null
                }

                // Extract embedded subtitles from stream
                val embeddedSubs = stream.subtitles?.mapIndexed { index, sub ->
                    Subtitle(
                        id = sub.id ?: "${addon.id}_stream_sub_$index",
                        url = sub.url ?: "",
                        lang = sub.lang ?: "en",
                        label = buildSubtitleLabel(sub.lang, sub.label, addon.name)
                    )
                } ?: emptyList()

                StreamSource(
                    source = stream.getTorrentName(),
                    addonName = addon.name + " - " + stream.getSourceName(),
                    addonId = addon.id,
                    quality = stream.getQuality(),
                    size = stream.getSize(),
                    sizeBytes = parseSizeToBytes(stream.getSize()),
                    url = streamUrl,
                    infoHash = stream.infoHash,
                    fileIdx = stream.fileIdx,
                    behaviorHints = stream.behaviorHints?.let {
                        val requestHeaders = mergeRequestHeaders(
                            base = mergeRequestHeaders(
                                base = sanitizeRequestHeaders(stream.headers),
                                extra = sanitizeRequestHeaders(it.headers)
                            ),
                            extra = sanitizeRequestHeaders(it.proxyHeaders?.request)
                        )
                        ModelStreamBehaviorHints(
                            notWebReady = it.notWebReady ?: false,
                            cached = it.cached,
                            bingeGroup = it.bingeGroup,
                            countryWhitelist = it.countryWhitelist,
                            proxyHeaders = if (requestHeaders.isNotEmpty() || it.proxyHeaders?.response != null) {
                                ModelProxyHeaders(
                                    request = requestHeaders.takeIf { headers -> headers.isNotEmpty() },
                                    response = it.proxyHeaders?.response
                                )
                            } else null,
                            videoHash = it.videoHash,
                            videoSize = it.videoSize,
                            filename = it.filename
                        )
                    } ?: stream.headers?.let { rawHeaders ->
                        val requestHeaders = sanitizeRequestHeaders(rawHeaders)
                        ModelStreamBehaviorHints(
                            notWebReady = false,
                            proxyHeaders = requestHeaders
                                .takeIf { headers -> headers.isNotEmpty() }
                                ?.let { headers -> ModelProxyHeaders(request = headers) }
                        )
                    },
                    subtitles = embeddedSubs,
                    sources = stream.sources ?: emptyList()
                )
            }
    }

    private fun isSupportedPlaybackCandidate(stream: StremioStream): Boolean {
        val url = stream.getStreamUrl()?.trim().orEmpty()
        if (url.isBlank()) return true

        val lower = url.lowercase(Locale.US)
        if (!(lower.startsWith("http://") || lower.startsWith("https://"))) return true

        // Providers occasionally return informational web pages as "streams".
        // Keep these out of the playable source list to avoid guaranteed playback errors.
        val blockedWebTargets = listOf(
            "github.com/",
            "raw.githubusercontent.com/",
            "youtube.com/watch",
            "youtu.be/"
        )
        if (blockedWebTargets.any { lower.contains(it) }) return false

        return true
    }

    /**
     * Resolve streams for a TV episode - with timeouts for faster loading
     */
    suspend fun resolveEpisodeStreams(
        imdbId: String,
        season: Int,
        episode: Int,
        tmdbId: Int? = null,
        tvdbId: Int? = null,
        genreIds: List<Int> = emptyList(),
        originalLanguage: String? = null,
        title: String = "",
        forceRefresh: Boolean = false
    ): StreamResult = withContext(Dispatchers.IO) {
        val subtitles = mutableListOf<Subtitle>()
        // Check if this is anime - use comprehensive detection
        val isAnime = animeMapper.isAnimeContent(tmdbId, genreIds, originalLanguage)
        // Get anime query using 5-tier fallback resolution (reduced timeout for faster startup)
        val animeQuery = if (isAnime) {
            withTimeoutOrNull(600L) {
                animeMapper.resolveAnimeEpisodeQuery(
                    tmdbId = tmdbId,
                    tvdbId = tvdbId,
                    title = title,
                    imdbId = imdbId,
                    season = season,
                    episode = episode
                )
            }
        } else null

        val seriesId = "$imdbId:$season:$episode"
        val allAddons = installedAddons.first()
        val streamAddons = getStreamAddons(allAddons, "series", imdbId)
        val cacheKey = streamCacheKey(
            profileId = profileManager.getProfileIdSync(),
            type = "series",
            imdbId = imdbId,
            season = season,
            episode = episode
        )
        if (!forceRefresh) {
            synchronized(streamResultCache) {
                val cached = streamResultCache[cacheKey]
                if (cached != null && isStreamCacheFresh(cached)) {
                    return@withContext cached.result
                }
            }
        }

        val streamJobs = streamAddons.map { addon ->
            async {
                try {
                    withTimeout(ADDON_TIMEOUT_MS) {
                        val (baseUrl, queryParams) = getAddonBaseUrl(addon.url ?: return@withTimeout emptyList())

                        // Check if addon supports kitsu IDs (Torrentio, AIOStreams, etc.)
                        val supportsKitsu = addon.manifest?.idPrefixes?.contains("kitsu") == true ||
                            addon.url?.contains("torrentio") == true ||
                            addon.url?.contains("aiostreams") == true ||
                            addon.url?.contains("mediafusion") == true ||
                            addon.url?.contains("comet") == true

                        // Use Kitsu ID for anime if addon supports it and we have a mapping
                        val useKitsu = isAnime && supportsKitsu && animeQuery != null
                        val contentId = when {
                            useKitsu -> animeQuery
                            else -> seriesId
                        }

                        val url = if (queryParams != null) {
                            "$baseUrl/stream/series/$contentId.json?$queryParams"
                        } else {
                            "$baseUrl/stream/series/$contentId.json"
                        }

                        val response = streamApi.getAddonStreams(url)
                        var addonStreams = processStreams(response.streams ?: emptyList(), addon)

                        // Quick fallback: if Kitsu query returned zero results, try IMDB format
                        if (addonStreams.isEmpty() && useKitsu && contentId != seriesId) {
                            val fallbackUrl = if (queryParams != null) {
                                "$baseUrl/stream/series/$seriesId.json?$queryParams"
                            } else {
                                "$baseUrl/stream/series/$seriesId.json"
                            }
                            try {
                                val fallbackResponse = streamApi.getAddonStreams(fallbackUrl)
                                addonStreams = processStreams(fallbackResponse.streams ?: emptyList(), addon)
                            } catch (_: Exception) {}
                        }
                        addonStreams
                    }
                } catch (_: TimeoutCancellationException) {
                    emptyList()
                } catch (_: Exception) {
                    emptyList()
                }
            }
        }
        val streams = streamJobs.awaitAll().flatten().toMutableList()

        // Keep core source lookup fully addon-driven and non-blocking.
        // IPTV VOD enrichment is appended separately in ViewModels.

        val result = StreamResult(streams, subtitles)
        synchronized(streamResultCache) {
            streamResultCache[cacheKey] = CachedStreamResult(result = result, createdAtMs = System.currentTimeMillis())
        }
        result
    }

    suspend fun resolveEpisodeVodOnly(
        imdbId: String?,
        season: Int,
        episode: Int,
        title: String = "",
        tmdbId: Int? = null,
        timeoutMs: Long = 12_000L
    ): StreamSource? = withContext(Dispatchers.IO) {
        android.util.Log.d("IPTV_VOD", "resolveEpisodeVodOnly START: title=$title, S${season}E${episode}, timeout=$timeoutMs")
        val result = withTimeoutOrNull(timeoutMs.coerceIn(500L, 90_000L)) {
            runCatching {
                iptvRepository.findEpisodeVodSource(
                    title = title,
                    season = season,
                    episode = episode,
                    imdbId = imdbId,
                    tmdbId = tmdbId,
                    allowNetwork = true
                )
            }.getOrElse { e ->
                android.util.Log.e("IPTV_VOD", "resolveEpisodeVodOnly exception: ${e.message}")
                null
            }
        }
        android.util.Log.d("IPTV_VOD", "resolveEpisodeVodOnly result: ${result?.url?.take(50) ?: "null"}")
        result
    }

    suspend fun prefetchEpisodeVod(
        imdbId: String?,
        season: Int,
        episode: Int,
        title: String = "",
        tmdbId: Int? = null
    ) = withContext(Dispatchers.IO) {
        if (title.isBlank()) return@withContext
        runCatching {
            iptvRepository.prefetchEpisodeVodResolution(
                title = title,
                season = season,
                episode = episode,
                imdbId = imdbId,
                tmdbId = tmdbId
            )
        }
    }

    suspend fun prefetchSeriesVodInfo(
        imdbId: String?,
        title: String = "",
        tmdbId: Int? = null
    ) = withContext(Dispatchers.IO) {
        if (title.isBlank()) return@withContext
        runCatching {
            iptvRepository.prefetchSeriesInfoForShow(
                title = title,
                imdbId = imdbId,
                tmdbId = tmdbId
            )
        }
    }

    /**
     * Fetch subtitles for the currently selected stream (important for OpenSubtitles matching).
     * Many subtitle providers (esp. OpenSubtitles) work best when `videoHash` and `videoSize` are provided.
     */
    suspend fun fetchSubtitlesForSelectedStream(
        mediaType: MediaType,
        imdbId: String,
        season: Int?,
        episode: Int?,
        stream: StreamSource?
    ): List<Subtitle> = withContext(Dispatchers.IO) {
        val allAddons = installedAddons.first()
        val subtitleAddons = allAddons.filter { it.isInstalled && it.isEnabled && it.type == AddonType.SUBTITLE }

        val videoHash = stream?.behaviorHints?.videoHash?.trim().takeUnless { it.isNullOrBlank() }
        val videoSize = stream?.behaviorHints?.videoSize?.takeIf { it != null && it > 0L }

        val contentId = when (mediaType) {
            MediaType.MOVIE -> imdbId
            MediaType.TV -> {
                val s = season ?: return@withContext emptyList()
                val e = episode ?: return@withContext emptyList()
                "$imdbId:$s:$e"
            }
            else -> return@withContext emptyList()
        }
        val type = when (mediaType) {
            MediaType.MOVIE -> "movie"
            MediaType.TV -> "series"
            else -> ""
        }

        subtitleAddons.flatMap { addon ->
            runCatching {
                withTimeout(SUBTITLE_TIMEOUT_MS) {
                    val addonUrl = addon.url ?: return@withTimeout emptyList<Subtitle>()
                    val (baseUrl, queryParams) = getAddonBaseUrl(addonUrl)
                    val url = buildSubtitlesUrl(
                        baseUrl = baseUrl,
                        type = type,
                        id = contentId,
                        addonQueryParams = queryParams,
                        videoHash = videoHash,
                        videoSize = videoSize
                    )
                    val response = streamApi.getSubtitles(url)
                    response.subtitles?.mapIndexed { index, sub ->
                        Subtitle(
                            id = sub.id ?: "${addon.id}_sub_hint_$index",
                            url = sub.url ?: "",
                            lang = sub.lang ?: "en",
                            label = buildSubtitleLabel(sub.lang, sub.label, addon.name)
                        )
                    } ?: emptyList()
                }
            }.getOrDefault(emptyList())
        }
    }

    private fun buildSubtitlesUrl(
        baseUrl: String,
        type: String,
        id: String,
        addonQueryParams: String?,
        videoHash: String?,
        videoSize: Long?
    ): String {
        val base = baseUrl.trimEnd('/')
        val subtitleBase = if (base.endsWith("/subtitles", ignoreCase = true)) {
            base
        } else {
            "$base/subtitles"
        }
        val hints = buildList {
            if (!videoHash.isNullOrBlank()) add("videoHash=${URLEncoder.encode(videoHash, "UTF-8")}")
            if (videoSize != null && videoSize > 0L) add("videoSize=$videoSize")
        }.joinToString("&")

        val mergedQuery = listOfNotNull(
            addonQueryParams?.takeIf { it.isNotBlank() },
            hints.takeIf { it.isNotBlank() }
        ).joinToString("&")

        return if (mergedQuery.isNotBlank()) {
            "$subtitleBase/$type/$id.json?$mergedQuery"
        } else {
            "$subtitleBase/$type/$id.json"
        }
    }

    // Timeout for resolving a single stream (dead streams should fail fast).
    private val STREAM_RESOLUTION_TIMEOUT_MS = 3500L

    /**
     * Resolve a single stream for playback - with timeout to prevent hanging forever
     */
    suspend fun resolveStreamForPlayback(stream: StreamSource): StreamSource? = withContext(Dispatchers.IO) {
        try {
            withTimeout(STREAM_RESOLUTION_TIMEOUT_MS) {
                resolveStreamInternal(stream)
            }
        } catch (e: TimeoutCancellationException) {
            null
        } catch (e: Exception) {
            null
        }
    }

    /**
     * Internal stream resolution without timeout wrapper
     */
    private suspend fun resolveStreamInternal(stream: StreamSource): StreamSource? {
        val url = stream.url?.trim().orEmpty()
        if (url.isBlank()) return null

        // Debrid/direct-only playback path: ignore magnet/infoHash-only P2P streams.
        if (url.startsWith("magnet:", ignoreCase = true)) return null

        val normalizedUrl = when {
            url.startsWith("http://", ignoreCase = true) || url.startsWith("https://", ignoreCase = true) -> url
            url.startsWith("//") -> "https:$url"
            // Some providers return bare host URLs without scheme.
            url.contains("://").not() && url.contains('.') -> "https://$url"
            else -> url
        }

        if (normalizedUrl.startsWith("http://", ignoreCase = true) ||
            normalizedUrl.startsWith("https://", ignoreCase = true)
        ) {
            val (resolvedUrl, urlHeaders) = splitUrlAndHeaders(normalizedUrl)
            val mergedHeaders = mergeRequestHeaders(
                base = stream.behaviorHints?.proxyHeaders?.request.orEmpty(),
                extra = urlHeaders
            )
            val mergedBehaviorHints = when {
                mergedHeaders.isNotEmpty() -> {
                    val current = stream.behaviorHints
                    if (current != null) {
                        current.copy(
                            proxyHeaders = ModelProxyHeaders(
                                request = mergedHeaders,
                                response = current.proxyHeaders?.response
                            )
                        )
                    } else {
                        ModelStreamBehaviorHints(
                            notWebReady = false,
                            proxyHeaders = ModelProxyHeaders(request = mergedHeaders)
                        )
                    }
                }
                else -> stream.behaviorHints
            }
            return stream.copy(
                url = resolvedUrl,
                behaviorHints = mergedBehaviorHints
            )
        } else {
            return null
        }
    }

    suspend fun isHttpStreamReachable(stream: StreamSource, timeoutMs: Long = 4_500L): Boolean =
        withContext(Dispatchers.IO) {
            val resolved = resolveStreamInternal(stream) ?: return@withContext false
            val rawUrl = resolved.url?.trim().orEmpty()
            if (rawUrl.isBlank()) return@withContext false
            if (!(rawUrl.startsWith("http://", true) || rawUrl.startsWith("https://", true))) {
                return@withContext true
            }

            val headers = mergeRequestHeaders(
                base = resolved.behaviorHints?.proxyHeaders?.request.orEmpty(),
                extra = emptyMap()
            ).toMutableMap()

            if (headers.keys.none { it.equals("User-Agent", ignoreCase = true) }) {
                headers["User-Agent"] =
                    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36"
            }
            if (headers.keys.none { it.equals("Accept", ignoreCase = true) }) {
                headers["Accept"] = "*/*"
            }
            if (headers.keys.none { it.equals("Range", ignoreCase = true) }) {
                headers["Range"] = "bytes=0-1"
            }
            val referer = headers.entries.firstOrNull { it.key.equals("Referer", ignoreCase = true) }?.value
            if (!referer.isNullOrBlank() && headers.keys.none { it.equals("Origin", ignoreCase = true) }) {
                deriveOriginFromReferer(referer)?.let { origin ->
                    headers["Origin"] = origin
                }
            }

            val request = Request.Builder()
                .url(rawUrl)
                .get()
                .apply {
                    headers.forEach { (key, value) ->
                        addHeader(key, value)
                    }
                }
                .build()

            return@withContext try {
                withTimeout(timeoutMs.coerceIn(500L, 15_000L)) {
                    okHttpClient.newCall(request).execute().use { response ->
                        val code = response.code
                        if (code == 416) return@use true
                        if (!response.isSuccessful) return@use false

                        val contentType = response.header("Content-Type").orEmpty().lowercase(Locale.US)
                        // HTTP addon sources should not resolve to plain HTML pages.
                        if (contentType.contains("text/html")) {
                            return@use false
                        }
                        true
                    }
                }
            } catch (_: Exception) {
                false
            }
        }

    private fun splitUrlAndHeaders(rawUrl: String): Pair<String, Map<String, String>> {
        val idx = rawUrl.indexOf('|')
        if (idx <= 0) return rawUrl to emptyMap()

        val baseUrl = rawUrl.substring(0, idx).trim()
        val rawHeaders = rawUrl.substring(idx + 1).trim()
        if (baseUrl.isBlank() || rawHeaders.isBlank()) return baseUrl.ifBlank { rawUrl } to emptyMap()

        val parsed = mutableMapOf<String, String>()
        rawHeaders.split('&')
            .map { it.trim() }
            .filter { it.isNotBlank() }
            .forEach { entry ->
                val separator = entry.indexOf('=')
                if (separator <= 0) return@forEach
                val key = decodeHeaderPart(entry.substring(0, separator)).trim()
                val value = decodeHeaderPart(entry.substring(separator + 1)).trim()
                if (key.isBlank() || value.isBlank()) return@forEach
                if (key.any { it == '\r' || it == '\n' } || value.any { it == '\r' || it == '\n' }) return@forEach
                parsed[key] = value
            }
        return baseUrl to parsed
    }

    private fun deriveOriginFromReferer(referer: String): String? {
        return runCatching {
            val parsed = java.net.URI(referer.trim())
            val scheme = parsed.scheme?.lowercase(Locale.US) ?: return@runCatching null
            val host = parsed.host ?: return@runCatching null
            val port = parsed.port
            val defaultPort = when (scheme) {
                "http" -> 80
                "https" -> 443
                else -> -1
            }
            if (port > 0 && port != defaultPort) {
                "$scheme://$host:$port"
            } else {
                "$scheme://$host"
            }
        }.getOrNull()
    }

    private fun mergeRequestHeaders(base: Map<String, String>, extra: Map<String, String>): Map<String, String> {
        val normalizedBase = sanitizeRequestHeaders(base)
        val normalizedExtra = sanitizeRequestHeaders(extra)
        if (normalizedBase.isEmpty()) return normalizedExtra
        if (normalizedExtra.isEmpty()) return normalizedBase
        return LinkedHashMap<String, String>(normalizedBase.size + normalizedExtra.size).apply {
            putAll(normalizedBase)
            putAll(normalizedExtra)
        }
    }

    private fun sanitizeRequestHeaders(headers: Map<String, String>?): Map<String, String> {
        if (headers.isNullOrEmpty()) return emptyMap()
        val sanitized = LinkedHashMap<String, String>(headers.size)
        headers.forEach { (rawKey, rawValue) ->
            val key = rawKey.trim()
            val value = rawValue.trim()
            if (key.isBlank() || value.isBlank()) return@forEach
            if (key.any { it == '\r' || it == '\n' } || value.any { it == '\r' || it == '\n' }) return@forEach
            sanitized[key] = value
        }
        return sanitized
    }

    private fun decodeHeaderPart(value: String): String {
        return runCatching { URLDecoder.decode(value, "UTF-8") }.getOrDefault(value)
    }

    private fun buildMagnetForStream(stream: StreamSource): String? {
        val infoHash = stream.infoHash?.trim().orEmpty()
        if (infoHash.isBlank()) return null

        // Stremio addons usually provide raw 40-char hex infoHash.
        val cleanHash = infoHash.lowercase().removePrefix("urn:btih:").removePrefix("btih:")
        if (cleanHash.isBlank()) return null

        val dn = (stream.behaviorHints?.filename?.trim().takeUnless { it.isNullOrBlank() }
            ?: stream.source.trim().takeUnless { it.isNullOrBlank() }
            ?: "video")

        val trackers = stream.sources
            .map { it.trim() }
            .filter { it.isNotBlank() }
            .map { it.removePrefix("tracker:").trim() }
            .filter { it.startsWith("http://", true) || it.startsWith("https://", true) || it.startsWith("udp://", true) }
            .distinct()

        val sb = StringBuilder()
        sb.append("magnet:?xt=urn:btih:").append(cleanHash)
        sb.append("&dn=").append(URLEncoder.encode(dn, "UTF-8"))
        trackers.forEach { tr ->
            sb.append("&tr=").append(URLEncoder.encode(tr, "UTF-8"))
        }
        return sb.toString()
    }

    private fun normalizeTorrServerBaseUrl(raw: String): String {
        val trimmed = raw.trim().removeSuffix("/")
        if (trimmed.isBlank()) return ""
        return when {
            trimmed.startsWith("http://", true) || trimmed.startsWith("https://", true) -> trimmed
            trimmed.startsWith("//") -> "http:$trimmed"
            else -> "http://$trimmed"
        }
    }

    private suspend fun resolveTorrentViaTorrServer(stream: StreamSource, magnet: String): StreamSource? {
        val configured = normalizeTorrServerBaseUrl(context.streamDataStore.data.first()[torrServerBaseUrlKey()].orEmpty())
        val candidates = buildList {
            if (configured.isNotBlank()) add(configured)
            // Common defaults on Android TV boxes / Fire TV.
            add("http://127.0.0.1:8090")
            add("http://localhost:8090")
        }.distinct()

        val encodedMagnet = URLEncoder.encode(magnet, "UTF-8")

        // Try multiple endpoints because TorrServer versions differ.
        val endpointPaths = listOf(
            "/stream?m3u&link=$encodedMagnet",
            "/torrent/play?m3u=true&link=$encodedMagnet"
        )

        val client = okHttpClient.newBuilder()
            .callTimeout(1500, TimeUnit.MILLISECONDS)
            .connectTimeout(1000, TimeUnit.MILLISECONDS)
            .readTimeout(1500, TimeUnit.MILLISECONDS)
            .build()

        for (base in candidates) {
            for (path in endpointPaths) {
                val url = base + path
                val isM3uEndpoint = path.contains("m3u", ignoreCase = true)

                if (isM3uEndpoint) {
                    val request = Request.Builder().url(url).get().build()
                    val response = runCatching { client.newCall(request).execute() }.getOrNull() ?: continue
                    response.use { resp ->
                        if (!resp.isSuccessful) return@use
                        val body = resp.body?.string().orEmpty()
                        if (body.isBlank()) return@use
                        val resolvedUrl = pickBestM3uUrl(base, body, stream.fileIdx) ?: return@use
                        return stream.copy(url = resolvedUrl)
                    }
                } else {
                    // Direct stream endpoint: don't read the body (it can be the entire video).
                    val request = Request.Builder()
                        .url(url)
                        .header("Range", "bytes=0-1")
                        .get()
                        .build()
                    val response = runCatching { client.newCall(request).execute() }.getOrNull() ?: continue
                    response.use { resp ->
                        if (resp.isSuccessful) {
                            return stream.copy(url = url)
                        }
                    }
                }
            }
        }

        return null
    }

    private fun pickBestM3uUrl(base: String, m3u: String, fileIdx: Int?): String? {
        val entries = m3u.lineSequence()
            .map { it.trim() }
            .filter { it.isNotBlank() }
            .filterNot { it.startsWith("#") }
            .map { line ->
                when {
                    line.startsWith("http://", true) || line.startsWith("https://", true) -> line
                    line.startsWith("/") -> base + line
                    else -> "$base/$line"
                }
            }
            .toList()

        if (entries.isEmpty()) return null

        if (fileIdx != null) {
            val match = entries.firstOrNull { it.contains("index=$fileIdx") || it.contains("file=$fileIdx") }
            if (match != null) return match
        }

        // Otherwise pick the first entry. TorrServer generally orders best match first.
        return entries.first()
    }

    // ========== Helpers ==========

    private fun getQualityScore(quality: String): Int {
        return when {
            quality.contains("4K", ignoreCase = true) ||
            quality.contains("2160p", ignoreCase = true) -> 100
            quality.contains("1080p", ignoreCase = true) -> 80
            quality.contains("720p", ignoreCase = true) -> 60
            quality.contains("480p", ignoreCase = true) -> 40
            else -> 20
        }
    }

    /**
     * Parse size string (e.g., "2.5 GB", "800 MB") to bytes for sorting
     */
    private fun parseSizeToBytes(sizeStr: String): Long {
        if (sizeStr.isBlank()) return 0L

        // Normalize comma decimals (European format: "5,71 GB" -> "5.71 GB")
        val normalized = sizeStr.replace(",", ".")
        val regex = Regex("""([\d.]+)\s*(GB|MB|KB|TB)""", RegexOption.IGNORE_CASE)
        val match = regex.find(normalized) ?: return 0L

        val value = match.groupValues[1].toDoubleOrNull() ?: return 0L
        val unit = match.groupValues[2].uppercase()

        return when (unit) {
            "TB" -> (value * 1024 * 1024 * 1024 * 1024).toLong()
            "GB" -> (value * 1024 * 1024 * 1024).toLong()
            "MB" -> (value * 1024 * 1024).toLong()
            "KB" -> (value * 1024).toLong()
            else -> 0L
        }
    }

    private fun buildSubtitleLabel(
        lang: String?,
        rawLabel: String?,
        provider: String?
    ): String {
        val normalized = normalizeLanguageCode(lang)
        val languageName = languageDisplayName(normalized)
        val label = rawLabel?.trim().orEmpty()
        val providerName = when {
            label.isBlank() -> provider?.trim().orEmpty()
            looksLikeLanguageLabel(label, languageName, normalized) -> provider?.trim().orEmpty()
            label.startsWith("http", ignoreCase = true) -> provider?.trim().orEmpty()
            else -> label
        }
        return if (providerName.isNotBlank() && !providerName.equals(languageName, ignoreCase = true)) {
            "$languageName - $providerName"
        } else {
            languageName
        }
    }

    private fun looksLikeLanguageLabel(label: String, languageName: String, normalized: String): Boolean {
        val lower = label.lowercase()
        return lower == normalized ||
            lower == languageName.lowercase() ||
            lower.startsWith(languageName.lowercase()) ||
            (normalized.isNotBlank() && lower.startsWith(normalized))
    }

    private fun languageDisplayName(lang: String?): String {
        val safe = lang?.takeIf { it.isNotBlank() } ?: "und"
        val locale = Locale.forLanguageTag(safe)
        val display = locale.getDisplayLanguage(Locale.ENGLISH)
        return if (display.isNullOrBlank() || display.equals(safe, ignoreCase = true)) {
            "Unknown"
        } else {
            display
        }
    }

    private fun normalizeLanguageCode(lang: String?): String {
        val lower = lang?.lowercase()?.trim().orEmpty()
        return when (lower) {
            "eng" -> "en"
            "spa" -> "es"
            "fra", "fre" -> "fr"
            "deu", "ger" -> "de"
            "ita" -> "it"
            "por" -> "pt"
            "nld", "dut" -> "nl"
            "rus" -> "ru"
            "zho", "chi" -> "zh"
            "jpn" -> "ja"
            "kor" -> "ko"
            "ara" -> "ar"
            "hin" -> "hi"
            "tur" -> "tr"
            "pol" -> "pl"
            "swe" -> "sv"
            "nor" -> "no"
            "dan" -> "da"
            "fin" -> "fi"
            "ell", "gre" -> "el"
            "ces", "cze" -> "cs"
            "hun" -> "hu"
            "ron", "rum" -> "ro"
            "tha" -> "th"
            "vie" -> "vi"
            "ind" -> "id"
            "heb" -> "he"
            else -> if (lower.length >= 2) lower.take(2) else lower
        }
    }
}

/**
 * Addon configuration
 */
data class AddonConfig(
    val id: String,
    val name: String,
    val baseUrl: String,
    val type: AddonType,
    val isEnabled: Boolean = true
)

/**
 * Stream resolution result
 */
data class StreamResult(
    val streams: List<StreamSource>,
    val subtitles: List<Subtitle>
)
