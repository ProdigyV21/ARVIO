package com.arflix.tv.data.repository

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.arflix.tv.R
import com.arflix.tv.data.api.*
import com.arflix.tv.data.model.Addon
import com.arflix.tv.data.model.AddonInstallSource
import com.arflix.tv.data.model.AddonCatalog
import com.arflix.tv.data.model.AddonManifest
import com.arflix.tv.data.model.AddonResource
import com.arflix.tv.data.model.AddonStreamResult
import com.arflix.tv.data.model.AddonType
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.model.QualityFilterConfig
import com.arflix.tv.data.model.RuntimeKind
import com.arflix.tv.data.model.SportsAddonCapabilities
import com.arflix.tv.data.telegram.TelegramSourceResolver
import com.arflix.tv.data.model.ProxyHeaders as ModelProxyHeaders
import com.arflix.tv.data.model.StreamBehaviorHints as ModelStreamBehaviorHints
import com.arflix.tv.data.model.StreamSource
import com.arflix.tv.data.model.Subtitle
import com.arflix.tv.network.OkHttpProvider
import com.arflix.tv.util.AnimeMapper
import com.arflix.tv.util.AppLogger
import com.arflix.tv.util.Constants
import com.arflix.tv.util.settingsDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.withTimeoutOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.HttpException
import java.net.URLDecoder
import java.net.URLEncoder
import java.security.MessageDigest
import java.util.Locale
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

private val Context.streamDataStore: DataStore<Preferences> by preferencesDataStore(name = "stream_prefs")

/**
 * Callback for streaming results as they arrive -
 */
typealias StreamCallback = (streams: List<StreamSource>?, addonId: String, addonName: String, error: Exception?) -> Unit

internal data class EpisodeIdCandidate(
    val contentId: String,
    val label: String,
    val preferAnimePath: Boolean
)

internal fun buildEpisodeIdCandidates(
    seriesId: String,
    animeQuery: String?,
    tmdbEpisodeId: String?,
    preferNativeAnimeIds: Boolean,
    includeTmdbCandidate: Boolean = true
): List<EpisodeIdCandidate> {
    val animeCandidate = animeQuery
        ?.takeIf { it.isNotBlank() && it != seriesId }
        ?.let { EpisodeIdCandidate(it, "kitsu", preferAnimePath = true) }
    val tmdbCandidate = tmdbEpisodeId
        ?.takeIf { includeTmdbCandidate }
        ?.takeIf { it.isNotBlank() && it != seriesId && it != animeQuery }
        ?.let { EpisodeIdCandidate(it, "tmdb", preferAnimePath = true) }
    val imdbCandidate = EpisodeIdCandidate(seriesId, "imdb", preferAnimePath = false)

    val ordered = if (preferNativeAnimeIds) {
        listOfNotNull(animeCandidate, tmdbCandidate, imdbCandidate)
    } else {
        listOfNotNull(imdbCandidate, animeCandidate, tmdbCandidate)
    }

    return ordered.distinctBy { "${it.contentId}|${it.preferAnimePath}" }
}

internal fun buildNativeAnimeRetryCandidates(
    seriesId: String,
    animeQuery: String?,
    tmdbEpisodeId: String?
): List<EpisodeIdCandidate> {
    return buildEpisodeIdCandidates(
        seriesId = seriesId,
        animeQuery = animeQuery,
        tmdbEpisodeId = tmdbEpisodeId,
        preferNativeAnimeIds = true,
        includeTmdbCandidate = true
    ).filterNot { it.label == "imdb" }
}

internal fun shouldTryNativeAnimeFallback(
    genreIds: List<Int>,
    originalLanguage: String?,
    nativeAnimeAddonAvailable: Boolean
): Boolean {
    if (!nativeAnimeAddonAvailable) return false
    if (!genreIds.contains(16)) return false // TMDB Animation genre
    val language = originalLanguage?.trim()?.lowercase(Locale.US)
    return language.isNullOrBlank() || language == "ja"
}

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
    private val iptvRepository: IptvRepository,
    private val httpLocalScraperRuntime: HttpLocalScraperRuntime,
    private val homeServerRepository: HomeServerRepository,
    private val invalidationBus: CloudSyncInvalidationBus,
    private val telegramSourceResolver: TelegramSourceResolver
) {
    private val gson = Gson()
    private val TAG = "StreamRepository"
    private val openSubtitlesUrl = "https://opensubtitles-v3.strem.io/subtitles"
    private data class CachedStreamResult(
        val result: StreamResult,
        val createdAtMs: Long
    )
    private data class PersistedStreamResultPayload(
        val streams: List<StreamSource>,
        val subtitles: List<Subtitle>,
        val createdAtMs: Long
    )
    private data class CachedResolvedStream(
        val stream: StreamSource,
        val createdAtMs: Long
    )
    data class LastGoodPlaybackPreference(
        val addonId: String = "",
        val source: String = "",
        val bingeGroup: String? = null
    )
    private val streamResultCache = mutableMapOf<String, CachedStreamResult>()
    private val resolvedStreamCache = ConcurrentHashMap<String, CachedResolvedStream>()
    // Cache getStreamAddons() result per content type, invalidated when addon list changes.
    // Avoids re-iterating all addon manifests on every stream resolution call.
    private val streamAddonsCache = mutableMapOf<String, List<Addon>>()
    private var cachedStreamAddonsFingerprint: String? = null
    private val stremioAddonRuntime = StremioAddonRuntime(
        movieResolver = { addon, request ->
            fetchMovieStreamsFromAddon(
                addon = addon,
                imdbId = request.imdbId,
                title = request.title,
                year = request.year
            )
        },
        episodeResolver = { addon, request ->
            fetchEpisodeStreamsFromAddon(
                addon = addon,
                imdbId = request.imdbId,
                season = request.season,
                episode = request.episode,
                tmdbId = request.tmdbId,
                tvdbId = request.tvdbId,
                genreIds = request.genreIds,
                originalLanguage = request.originalLanguage,
                title = request.title,
                airDate = request.airDate
            )
        }
    )
    private val addonRuntimes: Map<RuntimeKind, AddonRuntime> = listOf(
        stremioAddonRuntime
    ).associateBy { it.kind }
    private val addonRuntimeAggregator = AddonRuntimeAggregator(addonRuntimes)
    private data class AddonRuntimeHealth(
        var fetchSuccesses: Int = 0,
        var fetchFailures: Int = 0,
        var playbackStarts: Int = 0,
        var playbackFailures: Int = 0,
        var avgFetchLatencyMs: Long = 0L,
        var avgStartupMs: Long = 0L,
        var consecutiveFailures: Int = 0
    )
    private val addonRuntimeHealth = mutableMapOf<String, AddonRuntimeHealth>()
    private data class PlaybackHostHealth(
        var failures: Int = 0,
        var successes: Int = 0,
        var lastFailureAtMs: Long = 0L
    )
    private val playbackHostHealth = ConcurrentHashMap<String, PlaybackHostHealth>()
    private val repositoryScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    @Volatile private var addonHealthLoadedProfileId: String? = null

    // Precompiled quality filters cached in memory to avoid DataStore reads and regex compilation in hot path
    private data class PrecompiledQualityFilter(
        val regexes: List<Regex>,
        val isEmpty: Boolean = regexes.isEmpty()
    )
    @Volatile private var cachedQualityFilters = PrecompiledQualityFilter(emptyList(), isEmpty = true)

    // Addons are account-level/shared. Profile-specific keys are kept only for
    // migration from older builds and old cloud payloads.
    private val sharedAddonsKey = stringPreferencesKey("shared_installed_addons_v1")
    private val sharedPendingAddonsKey = stringPreferencesKey("shared_pending_addons_v1")
    private fun addonsKey() = profileManager.profileStringKey("installed_addons")
    private fun addonsKeyFor(profileId: String) = profileManager.profileStringKeyFor(profileId, "installed_addons")
    private fun pendingAddonsKey() = profileManager.profileStringKey("pending_addons")
    private fun pendingAddonsKeyFor(profileId: String) = profileManager.profileStringKeyFor(profileId, "pending_addons")
    private fun hiddenBuiltInAddonsKey() = profileManager.profileStringKey("hidden_builtin_addons_v1")
    private fun lastGoodPlaybackKey(
        mediaType: MediaType,
        tmdbId: Int,
        season: Int?,
        episode: Int?
    ) = profileManager.profileStringKey(
        "last_good_playback_${mediaType.name.lowercase(Locale.US)}_${tmdbId}_${season ?: 0}_${episode ?: 0}"
    )
    private fun torrServerBaseUrlKey() = profileManager.profileStringKey("torrserver_base_url_v1")
    private fun addonHealthKeyFor(profileId: String) = profileManager.profileStringKeyFor(profileId, "addon_health_v1")
    private val qualityFiltersKey = stringPreferencesKey("quality_filters")
    private val streamResultCacheBundleType = object : TypeToken<Map<String, PersistedStreamResultPayload>>() {}.type
    private val streamResultCacheDiskTtlMs = 4 * 60 * 60_000L
    private val streamResultCacheDiskStaleGraceMs = 5 * 60_000L
    private val streamResultCacheDiskMaxEntries = 320
    private fun streamResultCacheBundleKey(profileId: String): Preferences.Key<String> =
        stringPreferencesKey("profile_${profileId}_stream_result_cache_v2")
    private fun streamResultCacheEntryKey(cacheKey: String): String {
        val hash = MessageDigest.getInstance("SHA-1")
            .digest(cacheKey.toByteArray(Charsets.UTF_8))
            .joinToString("") { byte -> "%02x".format(byte) }
        return hash.take(16)
    }

    private fun decodeStreamResultCacheBundle(raw: String): Map<String, PersistedStreamResultPayload> {
        if (raw.isBlank()) return emptyMap()
        return runCatching {
            gson.fromJson(raw, streamResultCacheBundleType) as? Map<String, PersistedStreamResultPayload> ?: emptyMap()
        }.getOrElse {
            Log.w(TAG, "[StreamCache][Decode] malformed stream result cache payload")
            emptyMap()
        }
    }

    private fun isStreamCacheFresh(cached: CachedStreamResult, graceMs: Long = 0L): Boolean {
        val ageMs = System.currentTimeMillis() - cached.createdAtMs
        val ttlMs = cacheTtlMsFor(cached.result)
        return ageMs <= (ttlMs + graceMs)
    }

    private suspend fun loadPersistedStreamResult(
        profileId: String,
        cacheKey: String,
        graceMs: Long = 0L
    ): CachedStreamResult? = withContext(Dispatchers.IO) {
        val entryKey = streamResultCacheEntryKey(cacheKey)
        val raw = context.streamDataStore.data.first()[streamResultCacheBundleKey(profileId)].orEmpty()
        val payload = decodeStreamResultCacheBundle(raw)[entryKey] ?: return@withContext null
        val cached = CachedStreamResult(
            result = StreamResult(payload.streams, payload.subtitles),
            createdAtMs = payload.createdAtMs
        )
        if (isStreamCacheFresh(cached, graceMs)) cached else null
    }

    private fun persistStreamResult(profileId: String, cacheKey: String, cached: CachedStreamResult) {
        val entryKey = streamResultCacheEntryKey(cacheKey)
        val payload = PersistedStreamResultPayload(
            streams = cached.result.streams,
            subtitles = cached.result.subtitles,
            createdAtMs = cached.createdAtMs
        )
        repositoryScope.launch {
            runCatching {
                val bundleKey = streamResultCacheBundleKey(profileId)
                val now = System.currentTimeMillis()
                val raw = context.streamDataStore.data.first()[bundleKey].orEmpty()
                val decoded = decodeStreamResultCacheBundle(raw).toMutableMap()
                decoded[entryKey] = payload
                val trimmed = decoded.entries
                    .filter { now - it.value.createdAtMs <= streamResultCacheDiskTtlMs + streamResultCacheDiskStaleGraceMs }
                    .sortedByDescending { it.value.createdAtMs }
                    .take(streamResultCacheDiskMaxEntries)
                    .associate { it.key to it.value }
                context.streamDataStore.edit { prefs ->
                    if (trimmed.isEmpty()) {
                        prefs.remove(bundleKey)
                    } else {
                        prefs[bundleKey] = gson.toJson(trimmed)
                    }
                }
            }.onFailure {
                Log.w(TAG, "[StreamCache][Persist] failed key=$cacheKey reason=${it::class.java.simpleName}")
            }
        }
    }

    init {
        repositoryScope.launch {
            ensureAddonHealthLoaded()
            loadQualityFiltersCache()
        }
    }

    /**
     * Load and cache quality filters on init to avoid DataStore reads in hot path.
     * Filters are precompiled for efficient matching during stream resolution.
     */
    private suspend fun loadQualityFiltersCache() {
        runCatching {
            val json = context.settingsDataStore.data.first()[qualityFiltersKey].orEmpty()
            if (json.isBlank()) {
                cachedQualityFilters = PrecompiledQualityFilter(emptyList(), isEmpty = true)
            } else {
                val filters = gson.fromJson<List<QualityFilterConfig>>(
                    json,
                    TypeToken.getParameterized(List::class.java, QualityFilterConfig::class.java).type
                ).orEmpty()
                    .filter { it.enabled && it.regexPattern.isNotBlank() }
                val regexes = filters.mapNotNull { filter ->
                    runCatching { Regex(filter.regexPattern, RegexOption.IGNORE_CASE) }.getOrNull()
                }
                cachedQualityFilters = PrecompiledQualityFilter(regexes, isEmpty = regexes.isEmpty())
            }
        }.onFailure {
            cachedQualityFilters = PrecompiledQualityFilter(emptyList(), isEmpty = true)
        }
    }

    /**
     * Update cached quality filters after settings change.
     * Called from SettingsViewModel when filters are added/toggled/deleted.
     * Precompiles regexes to avoid compilation cost during stream checks.
     */
    fun updateQualityFiltersCache(filters: List<QualityFilterConfig>) {
        val enabledFilters = filters.filter { it.enabled && it.regexPattern.isNotBlank() }
        val regexes = enabledFilters.mapNotNull { filter ->
            runCatching { Regex(filter.regexPattern, RegexOption.IGNORE_CASE) }.getOrNull()
        }
        cachedQualityFilters = PrecompiledQualityFilter(regexes, isEmpty = regexes.isEmpty())
        synchronized(streamResultCache) { streamResultCache.clear() }
    }
    fun observeTorrServerBaseUrl(): Flow<String> =
        profileManager.activeProfileId.combine(context.streamDataStore.data) { _, prefs ->
            prefs[torrServerBaseUrlKey()].orEmpty()
        }

    suspend fun setTorrServerBaseUrl(raw: String) {
        // Allow blank to reset to default autodetect.
        context.streamDataStore.edit { prefs -> prefs[torrServerBaseUrlKey()] = raw.trim() }
        invalidationBus.markDirty(CloudSyncScope.PROFILE_SETTINGS, profileManager.getProfileIdSync(), "torrserver url")
    }

    suspend fun exportTorrServerBaseUrlForProfile(profileId: String): String {
        val key = profileManager.profileStringKeyFor(profileId, "torrserver_base_url_v1")
        return context.streamDataStore.data.first()[key].orEmpty()
    }

    suspend fun importTorrServerBaseUrlForProfile(profileId: String, raw: String?) {
        val key = profileManager.profileStringKeyFor(profileId, "torrserver_base_url_v1")
        val value = raw.orEmpty().trim()
        context.streamDataStore.edit { prefs ->
            if (value.isBlank()) {
                prefs.remove(key)
            } else {
                prefs[key] = value
            }
        }
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
            val type = TypeToken.getParameterized(List::class.java, String::class.java).type
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
        invalidationBus.markDirty(CloudSyncScope.ADDONS, profileManager.getProfileIdSync(), "hide builtin addon")
    }

    // ========== Addon Management ==========

    val installedAddons: Flow<List<Addon>> =
        context.streamDataStore.data.map { prefs ->
            val addons = readSharedOrLegacyAddons(prefs) ?: getDefaultAddonList()
            enforceOpenSubtitles(addons).map { sanitizeAddonDisplayName(it) }
        }

    private fun readSharedOrLegacyAddons(prefs: Preferences): List<Addon>? {
        parseAddons(prefs[sharedAddonsKey])?.takeIf { it.isNotEmpty() }?.let { return it }
        parseAddons(prefs[sharedPendingAddonsKey])?.takeIf { it.isNotEmpty() }?.let { return it }

        val merged = LinkedHashMap<String, Addon>()
        fun mergeFrom(json: String?) {
            parseAddons(json).orEmpty().forEach { addon -> merged.putIfAbsent(addon.id, addon) }
        }

        val activeProfileId = profileManager.getProfileIdSync()
        mergeFrom(prefs[addonsKeyFor(activeProfileId)])
        mergeFrom(prefs[pendingAddonsKeyFor(activeProfileId)])
        mergeFrom(prefs[addonsKeyFor("default")])
        mergeFrom(prefs[pendingAddonsKeyFor("default")])
        prefs.asMap().forEach { (key, value) ->
            val keyName = key.name
            if (keyName.endsWith("_installed_addons") || keyName.endsWith("_pending_addons")) {
                mergeFrom(value as? String)
            }
        }
        return merged.values.takeIf { it.isNotEmpty() }?.toList()
    }

    private fun getDefaultAddonList(): List<Addon> {
        return defaultAddons.map { config ->
            Addon(
                id = config.id,
                name = config.name,
                version = "1.0.0",
                description = when (config.id) {
                    "opensubtitles" -> context.getString(R.string.addon_opensubtitles_description)
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
        // Preserve the user's enabled/disabled choice. The addon is always kept installed
        // so the toggle remains visible, but users can disable it to turn OpenSubtitles off.
        val preservedEnabled = addon?.isEnabled ?: true
        return (addon ?: Addon(
            id = "opensubtitles",
            name = "OpenSubtitles v3",
            version = "1.0.0",
            description = context.getString(R.string.addon_opensubtitles_description),
            isInstalled = true,
            isEnabled = preservedEnabled,
            type = AddonType.SUBTITLE,
            url = openSubtitlesUrl,
            transportUrl = openSubtitlesUrl
        )).copy(
            id = "opensubtitles",
            name = "OpenSubtitles v3",
            version = addon?.version ?: "1.0.0",
            description = context.getString(R.string.addon_opensubtitles_description),
            isInstalled = true,
            isEnabled = preservedEnabled,
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
        val addons = installedAddons.first().toMutableList()
        val index = addons.indexOfFirst { it.id == addonId }
        if (index >= 0) {
            addons[index] = addons[index].copy(isEnabled = !addons[index].isEnabled)
            saveAddons(addons)
        }
    }

    suspend fun moveAddonUp(addonId: String): Boolean {
        return moveAddon(addonId, direction = -1)
    }

    suspend fun moveAddonDown(addonId: String): Boolean {
        return moveAddon(addonId, direction = 1)
    }

    private suspend fun moveAddon(addonId: String, direction: Int): Boolean {
        if (direction == 0) return false
        val addons = installedAddons.first().toMutableList()
        val currentIndex = addons.indexOfFirst { it.id == addonId }
        if (currentIndex < 0) return false
        val targetIndex = (currentIndex + direction).coerceIn(0, addons.lastIndex)
        if (targetIndex == currentIndex) return false
        val moved = addons.removeAt(currentIndex)
        addons.add(targetIndex, moved)
        saveAddons(addons)
        return true
    }

    /**
     * Add a custom Stremio addon from URL -
     * Fetches manifest and stores addon info
     */
    suspend fun addCustomAddon(url: String, customName: String? = null): Result<Addon> = withContext(Dispatchers.IO) {
        try {
            val normalizedUrl = resolveAddonInstallUrl(url)
            if (normalizedUrl.isBlank()) {
                return@withContext Result.failure(IllegalArgumentException(context.getString(R.string.addon_error_url_empty)))
            }

            httpLocalScraperRuntime.fetchInstallCandidate(
                url = normalizedUrl,
                customName = customName
            )?.let { httpCandidate ->
                return@withContext Result.success(
                    installHttpLocalScraperCandidate(
                        normalizedUrl = normalizedUrl,
                        candidate = httpCandidate
                    )
                )
            }

            val manifestUrl = getManifestUrl(normalizedUrl)

            val manifest = try {
                streamApi.getAddonManifest(manifestUrl)
            } catch (manifestError: Exception) {
                val httpCandidate = httpLocalScraperRuntime.fetchInstallCandidate(
                    url = normalizedUrl,
                    customName = customName
                ) ?: throw manifestError
                return@withContext Result.success(
                    installHttpLocalScraperCandidate(
                        normalizedUrl = normalizedUrl,
                        candidate = httpCandidate
                    )
                )
            }

            val transportUrl = getTransportUrl(normalizedUrl)
            val addonManifest = convertToAddonManifest(manifest)
            val resolvedName = customName?.trim()?.takeIf { it.isNotBlank() } ?: manifest.name
            val addonId = buildAddonInstanceId(manifest.id, normalizedUrl)

            // Classify the addon based on the resources its manifest declares.
            // - If it declares `subtitles` but NOT `stream`, it's a pure subtitle addon
            //   (e.g. Wizdom, Ktuvit) and gets AddonType.SUBTITLE so the subtitle fetcher
            //   picks it up and the stream resolver correctly ignores it.
            // - If it declares `stream` (with or without subtitles), keep it as CUSTOM so
            //   the stream resolver queries it. The subtitle fetcher has been updated
            //   separately to also include CUSTOM addons whose manifest declares a
            //   subtitles resource, so hybrid addons still get queried for both.
            // - Everything else stays CUSTOM, matching the previous default.
            // Fixes issue #80 where Wizdom/Ktuvit were installed but never queried because
            // every user-added addon was hardcoded to CUSTOM regardless of its manifest.
            val resourceNames = addonManifest.resources.map { it.name }.toSet()
            val hasSubtitles = "subtitles" in resourceNames
            val hasStream = "stream" in resourceNames
            val addonType = when {
                hasSubtitles && !hasStream -> AddonType.SUBTITLE
                else -> AddonType.CUSTOM
            }

            val newAddon = Addon(
                id = addonId,
                name = resolvedName,
                version = manifest.version,
                description = manifest.description ?: "",
                isInstalled = true,
                isEnabled = true,
                type = addonType,
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

    private suspend fun installHttpLocalScraperCandidate(
        normalizedUrl: String,
        candidate: HttpLocalScraperInstallCandidate
    ): Addon {
        val addonId = buildAddonInstanceId(candidate.manifest.id, normalizedUrl)
        val newAddon = Addon(
            id = addonId,
            name = candidate.name,
            version = candidate.version,
            description = candidate.description,
            isInstalled = true,
            isEnabled = true,
            type = AddonType.CUSTOM,
            url = normalizedUrl,
            logo = candidate.logo,
            manifest = candidate.manifest,
            transportUrl = candidate.transportUrl
        )
        val addons = installedAddons.first().toMutableList()
        addons.removeAll { it.id == addonId }
        addons.add(newAddon)
        saveAddons(addons)
        return newAddon
    }

    suspend fun ensureCustomAddons(urls: List<String>): List<Result<Addon>> = withContext(Dispatchers.IO) {
        val requested = urls
            .map { resolveAddonInstallUrl(it) }
            .filter { it.isNotBlank() }
            .distinct()
        if (requested.isEmpty()) return@withContext emptyList()

        val installedByUrl = installedAddons.first()
            .mapNotNull { addon ->
                val url = addon.url?.let { normalizeAddonInputUrl(it) } ?: return@mapNotNull null
                url to addon
            }
            .toMap()

        requested.map { normalizedUrl ->
            val existing = installedByUrl[normalizedUrl]
            if (existing != null) {
                Result.success(existing)
            } else {
                addCustomAddon(normalizedUrl)
            }
        }
    }

    suspend fun removeCustomAddonsByUrl(urls: List<String>): Boolean = withContext(Dispatchers.IO) {
        val normalizedUrls = urls
            .mapNotNull { url ->
                runCatching { resolveAddonInstallUrl(url) }
                    .getOrNull()
                    ?.takeIf { it.isNotBlank() }
            }
            .distinct()
            .toSet()
        if (normalizedUrls.isEmpty()) return@withContext false

        val current = installedAddons.first()
        val removableIds = current
            .filter { addon ->
                val addonUrl = addon.url ?: return@filter false
                runCatching { normalizeAddonInputUrl(addonUrl) }.getOrNull() in normalizedUrls
            }
            .map { it.id }
            .toSet()
        if (removableIds.isEmpty()) return@withContext false

        val retained = current.filterNot { it.id in removableIds }
        saveAddons(retained)
        true
    }

    suspend fun findInstalledAddonIdForCatalog(
        catalogType: String,
        catalogId: String,
        preferredAddonId: String? = null
    ): String? {
        val normalizedType = catalogType.trim()
        val normalizedId = catalogId.trim()
        if (normalizedType.isBlank() || normalizedId.isBlank()) return null

        val addons = installedAddons.first()
            .filter { it.isInstalled && it.isEnabled }
        val preferred = preferredAddonId?.trim().takeUnless { it.isNullOrBlank() }

        fun matches(addon: Addon): Boolean {
            return addon.manifest?.catalogs.orEmpty().any { catalog ->
                catalog.id.equals(normalizedId, ignoreCase = true) &&
                    catalog.type.equals(normalizedType, ignoreCase = true)
            }
        }

        return addons.firstOrNull { it.id == preferred && matches(it) }?.id
            ?: addons.firstOrNull { matches(it) }?.id
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
                is String -> AddonResource(name = resource.trim().lowercase(Locale.US))
                is Map<*, *> -> {
                    @Suppress("UNCHECKED_CAST")
                    val map = resource as Map<String, Any?>
                    AddonResource(
                        name = (map["name"] as? String)?.trim()?.lowercase(Locale.US) ?: return@mapNotNull null,
                        types = (map["types"] as? List<*>)?.filterIsInstance<String>()
                            ?.map { it.trim().lowercase(Locale.US) }
                            ?: emptyList(),
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
        saveAddons(enforceOpenSubtitles(addons).filterNot(::isIncompleteExternalAddon))
    }

    suspend fun getAddonsForProfile(profileId: String): List<Addon> {
        val prefs = context.streamDataStore.data.first()
        val stored = readSharedOrLegacyAddons(prefs)
            ?: parseAddons(prefs[addonsKeyFor(profileId)])
        return enforceOpenSubtitles(stored ?: getDefaultAddonList()).map { sanitizeAddonDisplayName(it) }
    }

    suspend fun replaceAddonsForProfile(profileId: String, addons: List<Addon>) {
        val resolved = enforceOpenSubtitles(addons).filterNot(::isIncompleteExternalAddon)
        context.streamDataStore.edit { prefs ->
            prefs[sharedAddonsKey] = gson.toJson(resolved)
            prefs.remove(sharedPendingAddonsKey)
            prefs[addonsKeyFor(profileId)] = gson.toJson(resolved)
            prefs.remove(pendingAddonsKeyFor(profileId))
        }
        synchronized(streamResultCache) { streamResultCache.clear() }
        invalidationBus.markDirty(CloudSyncScope.ADDONS, profileId, "replace addons")
    }

    suspend fun replaceSharedAddonsFromCloud(addons: List<Addon>) {
        val resolved = enforceOpenSubtitles(addons).filterNot(::isIncompleteExternalAddon)
        context.streamDataStore.edit { prefs ->
            prefs[sharedAddonsKey] = gson.toJson(resolved)
            prefs.remove(sharedPendingAddonsKey)
        }
        synchronized(streamResultCache) { streamResultCache.clear() }
        invalidationBus.markDirty(CloudSyncScope.ADDONS, profileManager.getProfileIdSync(), "replace shared addons")
    }

    private suspend fun saveAddons(addons: List<Addon>) {
        val json = gson.toJson(addons.map { sanitizeAddonDisplayName(it) })

        // Save locally to the shared account-level addon list. Mirror to the
        // active profile key so older builds/cloud payloads can still recover it.
        context.streamDataStore.edit { prefs ->
            prefs[sharedAddonsKey] = json
            prefs.remove(sharedPendingAddonsKey)
            prefs[addonsKey()] = json
            prefs.remove(pendingAddonsKey())
        }
        synchronized(streamResultCache) { streamResultCache.clear() }
        invalidationBus.markDirty(CloudSyncScope.ADDONS, profileManager.getProfileIdSync(), "save addons")
    }

    private fun sanitizeAddonDisplayName(addon: Addon): Addon {
        val sanitizedName = sanitizeProviderLabel(addon.name)
        val sanitizedDescription = sanitizeProviderLabel(addon.description)
        val sanitizedManifest = addon.manifest?.let { manifest ->
            val manifestName = sanitizeProviderLabel(manifest.name)
            val manifestDescription = sanitizeProviderLabel(manifest.description)
            if (manifestName == manifest.name && manifestDescription == manifest.description) {
                manifest
            } else {
                manifest.copy(name = manifestName, description = manifestDescription)
            }
        }
        return if (
            sanitizedName == addon.name &&
            sanitizedDescription == addon.description &&
            sanitizedManifest === addon.manifest
        ) {
            addon
        } else {
            addon.copy(
                name = sanitizedName,
                description = sanitizedDescription,
                manifest = sanitizedManifest
            )
        }
    }

    private fun sanitizeProviderLabel(value: String): String {
        return value.replace(StreamRegexes.NUVIO_REGEX, "HTTP").trim()
    }

    private fun isIncompleteExternalAddon(addon: Addon): Boolean {
        return addon.id != "opensubtitles" &&
            addon.url.isNullOrBlank() &&
            addon.transportUrl.isNullOrBlank() &&
            addon.manifest == null
    }

    private suspend fun installedAddonsForSourceResolution(): List<Addon> {
        return installedAddons.first().filterNot(::isIncompleteExternalAddon)
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
            val type = TypeToken.getParameterized(List::class.java, StoredAddonPayload::class.java).type
            val parsed: List<StoredAddonPayload> = gson.fromJson(json, type)
            parsed.mapNotNull { payload ->
                val id = payload.id?.trim().orEmpty()
                val name = payload.name?.trim().orEmpty()
                val version = payload.version?.trim().orEmpty()
                if (id.isBlank() || name.isBlank() || version.isBlank()) return@mapNotNull null
                if (payload.url.isNullOrBlank() &&
                    payload.transportUrl.isNullOrBlank() &&
                    payload.manifest == null &&
                    id != "opensubtitles"
                ) return@mapNotNull null
                Addon(
                    id = id,
                    name = name,
                    version = version,
                    description = payload.description.orEmpty(),
                    isInstalled = payload.isInstalled ?: true,
                    isEnabled = payload.isEnabled ?: true,
                    type = payload.type ?: AddonType.CUSTOM,
                    runtimeKind = payload.runtimeKind ?: RuntimeKind.STREMIO,
                    installSource = payload.installSource ?: AddonInstallSource.DIRECT_URL,
                    url = payload.url,
                    logo = payload.logo,
                    manifest = payload.manifest,
                    transportUrl = payload.transportUrl
                )
            }.fold(LinkedHashMap<String, Addon>()) { acc, addon ->
                acc[addon.id] = addon
                acc
            }.values.toList()
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
     * Get manifest URL from addon URL -  getAddonBaseURL
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
     * Get transport URL (base URL without manifest.json) -
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
            StreamRegexes.MANIFEST_TYPO_REGEX,
            "/manifest.json"
        )
        return cleanUrl.trim()
    }

    /**
     * Get base URL with optional query params -  getAddonBaseURL
     */
    private fun getAddonBaseUrl(url: String): Pair<String, String?> {
        val parts = url.split("?", limit = 2)
        val baseUrl = getTransportUrl(parts[0])
        val queryParams = parts.getOrNull(1)
        return Pair(baseUrl, queryParams)
    }

    private suspend fun resolveAddonInstallUrl(rawUrl: String): String {
        val normalized = normalizeAddonInputUrl(rawUrl)
        if (!normalized.contains("pastebin.com/raw/", ignoreCase = true)) {
            return normalized
        }
        val request = Request.Builder().url(normalized).build()
        val body = runCatching {
            okHttpClient.newCall(request).execute().use { response ->
                if (!response.isSuccessful) return normalized
                response.body?.string().orEmpty()
            }
        }.getOrDefault("")
        val link = body.lineSequence()
            .map { it.trim() }
            .firstOrNull { line ->
                line.startsWith("stremio://", ignoreCase = true) ||
                    line.startsWith("https://", ignoreCase = true) ||
                    line.startsWith("http://", ignoreCase = true)
            }
        return if (link.isNullOrBlank()) normalized else normalizeAddonInputUrl(link)
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
     * Filter addons that support streaming for the given content type -
     * More lenient filtering to ensure custom addons work
     */
    /**
     * Filter addons that support streaming for the given content type -
     * More lenient filtering to ensure custom addons work.
     *
     * Results are cached per content type and invalidated when the installed
     * addon list changes (via fingerprint comparison). The id-dependent
     * idPrefixes check is applied per-call on the cached base result.
     */
    private fun getStreamAddons(addons: List<Addon>, type: String, id: String): List<Addon> {
        val normalizedType = type.trim().lowercase(Locale.US)
        val currentFingerprint = streamAddonsFingerprint(addons)

        val baseCandidates: List<Addon> = synchronized(streamAddonsCache) {
            if (currentFingerprint != cachedStreamAddonsFingerprint) {
                streamAddonsCache.clear()
                cachedStreamAddonsFingerprint = currentFingerprint
            }
            streamAddonsCache.getOrPut(normalizedType) {
                addons.filter { addon ->
                    // Must be installed and enabled
                    if (!addon.isInstalled || !addon.isEnabled) return@filter false

                    if (addon.runtimeKind != RuntimeKind.STREMIO) return@filter false

                    // Skip subtitle addons
                    if (addon.type == AddonType.SUBTITLE) return@filter false

                    // Sports live TV addons are queried only by the sports home rows.
                    // Keeping them out of normal VOD source discovery avoids slowing
                    // movie/series source lookup and autoplay.
                    if (SportsAddonCapabilities.isSportsLiveTvAddon(addon)) return@filter false

                    // Must have a URL to fetch from
                    if (addon.url.isNullOrBlank()) return@filter false

                    // For custom addons, fully evaluate here (no idPrefixes concern).
                    if (addon.type == AddonType.CUSTOM) {
                        val manifest = addon.manifest
                        if (manifest != null && manifest.resources.isNotEmpty()) {
                            val supportsStream = manifest.resources.any { resource ->
                                (resource.name.equals("stream", ignoreCase = true) ||
                                    resource.name.equals("streams", ignoreCase = true)) &&
                                    supportsResourceType(resource.types, normalizedType)
                            }
                            return@filter supportsStream
                        }
                        return@filter true
                    }

                    // For non-custom addons, check stream resource existence WITHOUT
                    // idPrefixes (applied per-call below).
                    val manifest = addon.manifest
                    if (manifest != null && manifest.resources.isNotEmpty()) {
                        return@filter manifest.resources.any { resource ->
                            (resource.name.equals("stream", ignoreCase = true) ||
                                resource.name.equals("streams", ignoreCase = true)) &&
                                supportsResourceType(resource.types, normalizedType)
                        }
                    }

                    // Default: assume addon supports streaming
                    true
                }
            }
        }

        // Apply id-prefix filtering per-call (varies by id, cheap string ops).
        return baseCandidates.filter { addon ->
            if (SportsAddonCapabilities.isSportsLiveTvAddon(addon)) return@filter false
            if (addon.type == AddonType.CUSTOM) return@filter true
            val manifest = addon.manifest
            if (manifest != null && manifest.resources.isNotEmpty()) {
                val hasMatchingResource = manifest.resources.any { resource ->
                    (resource.name.equals("stream", ignoreCase = true) ||
                        resource.name.equals("streams", ignoreCase = true)) &&
                        supportsResourceType(resource.types, normalizedType) &&
                        idMatchesAnyPrefix(id, resource.idPrefixes)
                }
                if (hasMatchingResource) return@filter true
            }
            // Check global idPrefixes if present
            val idPrefixes = manifest?.idPrefixes
            if (!idMatchesAnyPrefix(id, idPrefixes)) return@filter false
            true
        }
    }

    private fun streamAddonsFingerprint(addons: List<Addon>): String {
        return addons.joinToString("\n") { addon ->
            val manifest = addon.manifest
            val resources = manifest?.resources.orEmpty().joinToString(";") { resource ->
                listOf(
                    resource.name,
                    resource.types.orEmpty().joinToString(","),
                    resource.idPrefixes.orEmpty().joinToString(",")
                ).joinToString(":")
            }
            listOf(
                addon.id,
                addon.isInstalled.toString(),
                addon.isEnabled.toString(),
                addon.runtimeKind.name,
                addon.type.name,
                addon.url.orEmpty(),
                addon.transportUrl.orEmpty(),
                manifest?.id.orEmpty(),
                manifest?.version.orEmpty(),
                manifest?.idPrefixes.orEmpty().joinToString(","),
                resources
            ).joinToString("|")
        }
    }

    private fun supportsResourceType(resourceTypes: List<String>?, requestedType: String): Boolean {
        if (resourceTypes.isNullOrEmpty()) return true
        val normalized = resourceTypes.map { it.trim().lowercase(Locale.US) }
        val aliases = when (requestedType) {
            "series", "tv", "show" -> setOf("series", "tv", "show")
            "anime" -> setOf("anime", "series", "tv", "show")
            "movie", "film" -> setOf("movie", "film")
            else -> setOf(requestedType)
        }
        return normalized.any { it in aliases }
    }

    private fun idMatchesAnyPrefix(id: String, prefixes: List<String>?): Boolean {
        if (prefixes.isNullOrEmpty()) return true
        return prefixes.any { prefix ->
            val normalizedPrefix = prefix.trim()
            normalizedPrefix.isBlank() ||
                id.startsWith(normalizedPrefix, ignoreCase = true) ||
                (!normalizedPrefix.endsWith(":") && id.startsWith("$normalizedPrefix:", ignoreCase = true))
        }
    }

    private fun addonSupportsStreamRequest(addon: Addon, type: String, id: String): Boolean {
        if (addon.type == AddonType.CUSTOM) return true
        val manifest = addon.manifest ?: return true
        val hasMatchingResource = manifest.resources.isEmpty() || manifest.resources.any { resource ->
            (resource.name.equals("stream", ignoreCase = true) ||
                resource.name.equals("streams", ignoreCase = true)) &&
                supportsResourceType(resource.types, type.trim().lowercase(Locale.US)) &&
                idMatchesAnyPrefix(id, resource.idPrefixes)
        }
        return hasMatchingResource && idMatchesAnyPrefix(id, manifest.idPrefixes)
    }

    private fun addonSupportsIdFamily(addon: Addon, family: String): Boolean {
        val manifest = addon.manifest ?: return true
        val normalizedFamily = family.trim().trimEnd(':').lowercase(Locale.US)
        if (normalizedFamily.isBlank()) return true
        val prefixes = buildList {
            addAll(manifest.idPrefixes.orEmpty())
            manifest.resources.forEach { resource -> addAll(resource.idPrefixes.orEmpty()) }
        }
        if (prefixes.isEmpty()) return true
        return prefixes.any { prefix ->
            prefix.trim().trimEnd(':').equals(normalizedFamily, ignoreCase = true)
        }
    }

    private fun manifestDeclaresExactType(addon: Addon, type: String): Boolean {
        val manifest = addon.manifest ?: return false
        val normalizedType = type.trim().lowercase(Locale.US)
        if (normalizedType.isBlank()) return false
        // Gson can set non-nullable list fields to null when the manifest JSON contains null;
        // assign to nullable locals so safe-call checks work correctly at runtime.
        val types: List<String>? = manifest.types
        val catalogs: List<AddonCatalog>? = manifest.catalogs
        val resources: List<AddonResource>? = manifest.resources
        if (types?.any { it.trim().equals(normalizedType, ignoreCase = true) } == true) return true
        if (catalogs?.any { it.type.trim().equals(normalizedType, ignoreCase = true) } == true) return true
        return resources?.any { resource ->
            val resTypes: List<String>? = resource.types
            resTypes?.any { it.trim().equals(normalizedType, ignoreCase = true) } == true
        } == true
    }

    private fun shouldPreferNativeAnimeIds(addon: Addon): Boolean {
        val declaresAnime = manifestDeclaresExactType(addon, "anime")
        if (!declaresAnime) return false
        return addonSupportsIdFamily(addon, "kitsu") ||
            addonSupportsIdFamily(addon, "aniways") ||
            addonSupportsIdFamily(addon, "kisskh")
    }

    private fun getEpisodeStreamAddons(
        addons: List<Addon>,
        imdbId: String,
        tmdbId: Int?,
        isAnime: Boolean,
        genreIds: List<Int>,
        originalLanguage: String?
    ): List<Addon> {
        val seriesAddons = getStreamAddons(addons, "series", imdbId)
        val hasNativeAnimeAddon = addons.any(::shouldPreferNativeAnimeIds)
        val shouldIncludeAnimeAddons = isAnime ||
            shouldTryNativeAnimeFallback(
                genreIds = genreIds,
                originalLanguage = originalLanguage,
                nativeAnimeAddonAvailable = hasNativeAnimeAddon
            )
        if (!shouldIncludeAnimeAddons) return seriesAddons

        val animeIds = buildList {
            add(imdbId)
            add("kitsu:")
            if (tmdbId != null) add("tmdb:$tmdbId")
        }
        val animeAddons = animeIds.flatMap { id -> getStreamAddons(addons, "anime", id) }
        return (seriesAddons + animeAddons).distinctBy { it.id }
    }

    // Stream source requests — generous timeouts to accommodate slow wifi and
    // debrid-backed addons (Torrentio, MediaFusion, etc.) that resolve remotely.
    private val ADDON_TIMEOUT_MS = 6_000L
    private val ADDON_EPISODE_TIMEOUT_MS = 10_000L
    private val ADDON_SINGLE_STREAM_REQUEST_TIMEOUT_MS = 4_000L
    private val ADDON_NATIVE_ANIME_EPISODE_TIMEOUT_MS = 24_000L
    private val ADDON_NATIVE_ANIME_SINGLE_STREAM_REQUEST_TIMEOUT_MS = 12_000L
    private val ANIME_ID_LOOKUP_TIMEOUT_MS = 3_000L
    private val NATIVE_ANIME_ID_LOOKUP_TIMEOUT_MS = 8_000L
    // Longer timeouts for providers/requests known to be slower on large IPTV/legacy catalogs.
    private val ADDON_EXTENDED_TIMEOUT_MS = 30_000L
    private val ADDON_EXTENDED_EPISODE_TIMEOUT_MS = 45_000L
    private val ADDON_EXTENDED_SINGLE_STREAM_REQUEST_TIMEOUT_MS = 35_000L
    // Subtitles should not block playback but need enough time on slow connections.
    private val SUBTITLE_TIMEOUT_MS = 6_000L
    // If addons return nothing, allow Xtream VOD lookup to recover playback.
    private val VOD_LOOKUP_TIMEOUT_MS = 6_000L
    // If addons already returned streams, keep VOD lookup shorter to avoid UI delay.
    private val VOD_APPEND_TIMEOUT_MS = 3_000L
    private val STREAM_RESULT_CACHE_TTL_MS = 10 * 60_000L
    private val STREAM_RESULT_CACHE_HTTP_TTL_MS = 90_000L
    private val STREAM_RESULT_CACHE_HTTP_EPHEMERAL_TTL_MS = 30_000L
    private val EPISODE_STREAM_CACHE_TYPE = "series_v3"

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

    private fun sanitizeLogUrl(rawUrl: String): String {
        return runCatching {
            val uri = java.net.URI(rawUrl)
            val host = uri.host ?: return@runCatching rawUrl
            val path = uri.path.orEmpty()
            val rawQuery = uri.rawQuery
            if (rawQuery.isNullOrBlank()) {
                "${uri.scheme}://$host$path"
            } else {
                val redacted = rawQuery.split('&').joinToString("&") { part ->
                    val key = part.substringBefore('=', "").lowercase(Locale.US)
                    val shouldRedact = key.contains("token") ||
                        key.contains("auth") ||
                        key.contains("key") ||
                        key.contains("pass") ||
                        key.contains("jwt") ||
                        key.contains("session")
                    if (!shouldRedact) {
                        part
                    } else {
                        val normalizedKey = part.substringBefore('=', key)
                        "$normalizedKey=***"
                    }
                }
                "${uri.scheme}://$host$path?$redacted"
            }
        }.getOrDefault(rawUrl)
    }

    private fun Throwable.toShortLogMessage(): String {
        val http = this as? HttpException
        return if (http != null) {
            "HttpException(code=${http.code()} message=${http.message()})"
        } else {
            "${this::class.java.simpleName}: ${message ?: "no-message"}"
        }
    }

    private fun addonNeedsExtendedStreamTimeout(addon: Addon): Boolean {
        val manifest = addon.manifest
        val haystack = listOf(
            addon.id,
            addon.name,
            addon.url.orEmpty(),
            addon.transportUrl.orEmpty(),
            manifest?.id.orEmpty(),
            manifest?.name.orEmpty(),
            manifest?.description.orEmpty()
        ).joinToString(" ").lowercase(Locale.US)

        return haystack.contains("flix-stream") ||
            haystack.contains("flix streams") ||
            haystack.contains("flickystream") ||
            haystack.contains("flixnest") ||
            haystack.contains("telegram")
    }

    private fun latencyBucket(ms: Long): String = when {
        ms < 500L -> "lt_500ms"
        ms < 2_000L -> "lt_2s"
        ms < 5_000L -> "lt_5s"
        ms < 15_000L -> "lt_15s"
        else -> "gte_15s"
    }

    private fun sourceKind(stream: StreamSource): String {
        val addonId = stream.addonId.lowercase(Locale.US)
        val url = stream.url?.trim().orEmpty()
        return when {
            addonId == HomeServerRepository.ADDON_ID -> "home_server"
            addonId == "iptv_xtream_vod" -> "iptv_vod"
            url.startsWith("magnet:", ignoreCase = true) || !stream.infoHash.isNullOrBlank() -> "p2p"
            url.startsWith("http://", ignoreCase = true) || url.startsWith("https://", ignoreCase = true) -> "http"
            else -> "unknown"
        }
    }

    private suspend fun fetchMovieStreamsFromAddon(
        addon: Addon,
        imdbId: String,
        title: String = "",
        year: Int? = null
    ): List<StreamSource> {
        val startedAt = System.currentTimeMillis()
        val addonTimeoutMs = if (addonNeedsExtendedStreamTimeout(addon)) {
            ADDON_EXTENDED_TIMEOUT_MS
        } else {
            ADDON_TIMEOUT_MS
        }
        return try {
            withTimeout(addonTimeoutMs) {
                if (httpLocalScraperRuntime.canHandle(addon)) {
                    val streams = httpLocalScraperRuntime.resolveMovieStreams(
                        addon = addon,
                        imdbId = imdbId,
                        title = title,
                        year = year
                    )
                    recordAddonFetchOutcome(
                        addonId = addon.id,
                        success = streams.isNotEmpty(),
                        latencyMs = System.currentTimeMillis() - startedAt
                    )
                    return@withTimeout streams
                }
                val (baseUrl, queryParams) = getAddonBaseUrl(addon.url ?: return@withTimeout emptyList())
                val url = if (queryParams != null) {
                    "$baseUrl/stream/movie/$imdbId.json?$queryParams"
                } else {
                    "$baseUrl/stream/movie/$imdbId.json"
                }
                Log.d(
                    TAG,
                    "[StreamFetch][Movie] request addon=${addon.name} addonId=${addon.id} url=${sanitizeLogUrl(url)}"
                )
                val response = streamApi.getAddonStreams(url)
                val streams = processStreams(response.streams ?: emptyList(), addon)
                Log.d(
                    TAG,
                    "[StreamFetch][Movie] response addon=${addon.name} addonId=${addon.id} streams=${streams.size} elapsedMs=${System.currentTimeMillis() - startedAt}"
                )
                recordAddonFetchOutcome(
                    addonId = addon.id,
                    success = streams.isNotEmpty(),
                    latencyMs = System.currentTimeMillis() - startedAt
                )
                streams
            }
        } catch (timeout: TimeoutCancellationException) {
            Log.w(
                TAG,
                "[StreamFetch][Movie] timeout addon=${addon.name} addonId=${addon.id} timeoutMs=$addonTimeoutMs elapsedMs=${System.currentTimeMillis() - startedAt}"
            )
            AppLogger.breadcrumb(
                tag = "Sources",
                message = "addon_movie_timeout addon=${addon.id} latency=${latencyBucket(System.currentTimeMillis() - startedAt)}",
                severity = "warning"
            )
            recordAddonFetchOutcome(
                addonId = addon.id,
                success = false,
                latencyMs = System.currentTimeMillis() - startedAt
            )
            emptyList()
        } catch (error: Exception) {
            Log.w(
                TAG,
                "[StreamFetch][Movie] failure addon=${addon.name} addonId=${addon.id} elapsedMs=${System.currentTimeMillis() - startedAt} error=${error.toShortLogMessage()}"
            )
            AppLogger.breadcrumb(
                tag = "Sources",
                message = "addon_movie_failed addon=${addon.id} error=${error::class.java.simpleName} latency=${latencyBucket(System.currentTimeMillis() - startedAt)}",
                severity = "warning"
            )
            recordAddonFetchOutcome(
                addonId = addon.id,
                success = false,
                latencyMs = System.currentTimeMillis() - startedAt
            )
            emptyList()
        }
    }

    private suspend fun fetchEpisodeStreamsFromAddon(
        addon: Addon,
        imdbId: String,
        season: Int,
        episode: Int,
        tmdbId: Int? = null,
        tvdbId: Int? = null,
        genreIds: List<Int> = emptyList(),
        originalLanguage: String? = null,
        title: String = "",
        airDate: String? = null
    ): List<StreamSource> {
        val startedAt = System.currentTimeMillis()
        val nativeAnimeAddonHint = shouldPreferNativeAnimeIds(addon)
        val extendedTimeout = addonNeedsExtendedStreamTimeout(addon)
        val addonTimeoutMs = when {
            extendedTimeout -> ADDON_EXTENDED_EPISODE_TIMEOUT_MS
            nativeAnimeAddonHint -> ADDON_NATIVE_ANIME_EPISODE_TIMEOUT_MS
            else -> ADDON_EPISODE_TIMEOUT_MS
        }
        return try {
            withTimeout(addonTimeoutMs) {
                if (httpLocalScraperRuntime.canHandle(addon)) {
                    val streams = httpLocalScraperRuntime.resolveEpisodeStreams(
                        addon = addon,
                        imdbId = imdbId,
                        season = season,
                        episode = episode,
                        tmdbId = tmdbId,
                        title = title
                    )
                    recordAddonFetchOutcome(
                        addonId = addon.id,
                        success = streams.isNotEmpty(),
                        latencyMs = System.currentTimeMillis() - startedAt
                    )
                    return@withTimeout streams
                }
                val (baseUrl, queryParams) = getAddonBaseUrl(addon.url ?: return@withTimeout emptyList())

                val strictAnime = animeMapper.isAnimeContent(tmdbId, genreIds, originalLanguage)
                val nativeAnimeAddon = nativeAnimeAddonHint
                val resolveAsAnime = strictAnime ||
                    shouldTryNativeAnimeFallback(
                        genreIds = genreIds,
                        originalLanguage = originalLanguage,
                        nativeAnimeAddonAvailable = nativeAnimeAddon
                    )
                suspend fun resolveAnimeQuery(timeoutMs: Long): String? {
                    return withTimeoutOrNull(timeoutMs) {
                        animeMapper.resolveAnimeEpisodeQuery(
                            tmdbId = tmdbId,
                            tvdbId = tvdbId,
                            title = title,
                            imdbId = imdbId,
                            season = season,
                            episode = episode
                        )
                    }
                }

                val animeLookupTimeoutMs = if (nativeAnimeAddon) {
                    NATIVE_ANIME_ID_LOOKUP_TIMEOUT_MS
                } else {
                    ANIME_ID_LOOKUP_TIMEOUT_MS
                }
                val animeQuery = if (resolveAsAnime) {
                    resolveAnimeQuery(animeLookupTimeoutMs)
                } else null

                val seriesId = "$imdbId:$season:$episode"
                val supportsKitsu = addonSupportsIdFamily(addon, "kitsu") ||
                    addon.url.contains("torrentio") ||
                    addon.url.contains("aiostreams") ||
                    addon.url.contains("mediafusion") ||
                    addon.url.contains("comet")

                val useKitsuFallback = resolveAsAnime && supportsKitsu && animeQuery != null && animeQuery != seriesId
                val preferNativeAnimeIds = useKitsuFallback && nativeAnimeAddon
                fun streamUrl(type: String, contentId: String): String {
                    return if (queryParams != null) {
                        "$baseUrl/stream/$type/$contentId.json?$queryParams"
                    } else {
                        "$baseUrl/stream/$type/$contentId.json"
                    }
                }

                // For anime debrid addons, prefer the exact app season/episode request first.
                // Kitsu mappings are still useful as fallback, but split cours can remap
                // episodes like TMDB S4E29 to Kitsu E7 and surface the wrong debrid files.
                fun streamRequestTypes(contentId: String, preferAnimePath: Boolean): List<String> {
                    val types = mutableListOf<String>()
                    if (preferAnimePath && addonSupportsStreamRequest(addon, "anime", contentId)) {
                        types += "anime"
                    }
                    if (addonSupportsStreamRequest(addon, "series", contentId)) {
                        types += "series"
                    }
                    if (!preferAnimePath && addonSupportsStreamRequest(addon, "anime", contentId)) {
                        types += "anime"
                    }
                    return types.distinct().ifEmpty { listOf("series") }
                }

                suspend fun requestEpisodeId(
                    contentId: String,
                    label: String,
                    preferAnimePath: Boolean
                ): List<StreamSource> {
                    var lastError: Exception? = null
                    val singleRequestTimeoutMs = when {
                        extendedTimeout -> ADDON_EXTENDED_SINGLE_STREAM_REQUEST_TIMEOUT_MS
                        nativeAnimeAddon -> ADDON_NATIVE_ANIME_SINGLE_STREAM_REQUEST_TIMEOUT_MS
                        else -> ADDON_SINGLE_STREAM_REQUEST_TIMEOUT_MS
                    }
                    for (requestType in streamRequestTypes(contentId, preferAnimePath)) {
                        val requestUrl = streamUrl(requestType, contentId)
                        Log.d(
                            TAG,
                            "[StreamFetch][Episode] $label request addon=${addon.name} addonId=${addon.id} type=$requestType url=${sanitizeLogUrl(requestUrl)}"
                        )
                        try {
                            val response = withTimeoutOrNull(singleRequestTimeoutMs) {
                                streamApi.getAddonStreams(requestUrl)
                            }
                            if (response == null) {
                                Log.w(
                                    TAG,
                                    "[StreamFetch][Episode] $label request timeout addon=${addon.name} addonId=${addon.id} type=$requestType timeoutMs=$singleRequestTimeoutMs"
                                )
                                continue
                            }
                            val streams = processStreams(response.streams ?: emptyList(), addon)
                            Log.d(
                                TAG,
                                "[StreamFetch][Episode] $label response addon=${addon.name} addonId=${addon.id} type=$requestType streams=${streams.size} elapsedMs=${System.currentTimeMillis() - startedAt}"
                            )
                            if (streams.isNotEmpty()) return streams
                        } catch (timeout: TimeoutCancellationException) {
                            throw timeout
                        } catch (error: Exception) {
                            lastError = error
                            Log.w(
                                TAG,
                                "[StreamFetch][Episode] $label failure addon=${addon.name} addonId=${addon.id} type=$requestType error=${error.toShortLogMessage()}"
                            )
                        }
                    }
                    if (lastError != null) {
                        AppLogger.breadcrumb(
                            tag = "Sources",
                            message = "episode_${label}_failed addon=${addon.id} error=${lastError::class.java.simpleName}",
                            severity = "warning"
                        )
                    }
                    return emptyList()
                }

                val tmdbEpisodeId = if (resolveAsAnime && tmdbId != null && addonSupportsIdFamily(addon, "tmdb")) {
                    "tmdb:$tmdbId:$season:$episode"
                } else null

                var addonStreams = emptyList<StreamSource>()
                val attemptedEpisodeCandidateKeys = linkedSetOf<String>()
                for (candidate in buildEpisodeIdCandidates(
                    seriesId = seriesId,
                    animeQuery = if (useKitsuFallback) animeQuery else null,
                    tmdbEpisodeId = tmdbEpisodeId,
                    preferNativeAnimeIds = preferNativeAnimeIds,
                    includeTmdbCandidate = true
                )) {
                    attemptedEpisodeCandidateKeys.add("${candidate.contentId}|${candidate.preferAnimePath}")
                    addonStreams = requestEpisodeId(
                        contentId = candidate.contentId,
                        label = candidate.label,
                        preferAnimePath = candidate.preferAnimePath
                    )
                    if (addonStreams.isNotEmpty()) break
                }

                if (addonStreams.isEmpty() && nativeAnimeAddon) {
                    val retryAnimeQuery = animeQuery ?: resolveAnimeQuery(NATIVE_ANIME_ID_LOOKUP_TIMEOUT_MS)
                    val retryTmdbEpisodeId = if (tmdbId != null && addonSupportsIdFamily(addon, "tmdb")) {
                        "tmdb:$tmdbId:$season:$episode"
                    } else null
                    val retryCandidates = buildNativeAnimeRetryCandidates(
                        seriesId = seriesId,
                        animeQuery = retryAnimeQuery,
                        tmdbEpisodeId = retryTmdbEpisodeId
                    ).filterNot { candidate ->
                        attemptedEpisodeCandidateKeys.contains("${candidate.contentId}|${candidate.preferAnimePath}")
                    }
                    if (retryCandidates.isNotEmpty()) {
                        Log.d(
                            TAG,
                            "[StreamFetch][Episode] native anime retry addon=${addon.name} addonId=${addon.id} candidates=${retryCandidates.joinToString(",") { it.label }}"
                        )
                    }
                    for (candidate in retryCandidates) {
                        attemptedEpisodeCandidateKeys.add("${candidate.contentId}|${candidate.preferAnimePath}")
                        addonStreams = requestEpisodeId(
                            contentId = candidate.contentId,
                            label = "retry_${candidate.label}",
                            preferAnimePath = candidate.preferAnimePath
                        )
                        if (addonStreams.isNotEmpty()) {
                            AppLogger.breadcrumb(
                                tag = "Sources",
                                message = "episode_native_anime_retry_success addon=${addon.id} candidate=${candidate.label}",
                                severity = "info"
                            )
                            break
                        }
                    }
                }

                // Daily show fallback: try air-date-based numbering (S{year}E{dayOfYear})
                // for shows like Jeopardy, talk shows, news where debrid files use
                // date-based episode IDs instead of TMDB sequential numbering.
                if (addonStreams.isEmpty() && airDate != null && airDate.length >= 10) {
                    try {
                        val dateParts = airDate.split("-")
                        if (dateParts.size == 3) {
                            val year = dateParts[0].toIntOrNull()
                            val month = dateParts[1].toIntOrNull()
                            val day = dateParts[2].toIntOrNull()
                            if (year != null && month != null && day != null) {
                                val cal = java.util.Calendar.getInstance().apply {
                                    set(java.util.Calendar.YEAR, year)
                                    set(java.util.Calendar.MONTH, month - 1)
                                    set(java.util.Calendar.DAY_OF_MONTH, day)
                                }
                                val dayOfYear = cal.get(java.util.Calendar.DAY_OF_YEAR)
                                val airDateId = "$imdbId:$year:$dayOfYear"
                                val airDateUrl = streamUrl("series", airDateId)
                                Log.d(
                                    TAG,
                                    "[StreamFetch][Episode] airDate request addon=${addon.name} addonId=${addon.id} url=${sanitizeLogUrl(airDateUrl)}"
                                )
                                val airDateResponse = streamApi.getAddonStreams(airDateUrl)
                                val airDateStreams = processStreams(airDateResponse.streams ?: emptyList(), addon)
                                Log.d(
                                    TAG,
                                    "[StreamFetch][Episode] airDate response addon=${addon.name} addonId=${addon.id} streams=${airDateStreams.size}"
                                )
                                if (airDateStreams.isNotEmpty()) {
                                    addonStreams = airDateStreams
                                }
                            }
                        }
                    } catch (airDateError: Exception) {
                        Log.w(
                            TAG,
                            "[StreamFetch][Episode] airDate failure addon=${addon.name} addonId=${addon.id} error=${airDateError.toShortLogMessage()}"
                        )
                        AppLogger.breadcrumb(
                            tag = "Sources",
                            message = "episode_airdate_fallback_failed addon=${addon.id} error=${airDateError::class.java.simpleName}",
                            severity = "warning"
                        )
                    }
                }

                recordAddonFetchOutcome(
                    addonId = addon.id,
                    success = addonStreams.isNotEmpty(),
                    latencyMs = System.currentTimeMillis() - startedAt
                )
                addonStreams
            }
        } catch (timeout: TimeoutCancellationException) {
            Log.w(
                TAG,
                "[StreamFetch][Episode] timeout addon=${addon.name} addonId=${addon.id} timeoutMs=$addonTimeoutMs elapsedMs=${System.currentTimeMillis() - startedAt}"
            )
            AppLogger.breadcrumb(
                tag = "Sources",
                message = "addon_episode_timeout addon=${addon.id} latency=${latencyBucket(System.currentTimeMillis() - startedAt)}",
                severity = "warning"
            )
            recordAddonFetchOutcome(
                addonId = addon.id,
                success = false,
                latencyMs = System.currentTimeMillis() - startedAt
            )
            emptyList()
        } catch (error: Exception) {
            Log.w(
                TAG,
                "[StreamFetch][Episode] failure addon=${addon.name} addonId=${addon.id} elapsedMs=${System.currentTimeMillis() - startedAt} error=${error.toShortLogMessage()}"
            )
            AppLogger.breadcrumb(
                tag = "Sources",
                message = "addon_episode_failed addon=${addon.id} error=${error::class.java.simpleName} latency=${latencyBucket(System.currentTimeMillis() - startedAt)}",
                severity = "warning"
            )
            recordAddonFetchOutcome(
                addonId = addon.id,
                success = false,
                latencyMs = System.currentTimeMillis() - startedAt
            )
            emptyList()
        }
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
        ensureAddonHealthLoaded()
        val subtitles = mutableListOf<Subtitle>()
        val allAddons = installedAddonsForSourceResolution()
        val streamAddons = getStreamAddons(allAddons, "movie", imdbId)
        val cacheKey = streamCacheKey(
            profileId = profileManager.getProfileIdSync(),
            type = "movie",
            imdbId = imdbId
        )
        val profileId = profileManager.getProfileIdSync()
        if (!forceRefresh) {
            synchronized(streamResultCache) {
                val cached = streamResultCache[cacheKey]
                if (cached != null && isStreamCacheFresh(cached)) {
                    return@withContext cached.result
                }
            }
            loadPersistedStreamResult(profileId, cacheKey)?.let { persisted ->
                synchronized(streamResultCache) { streamResultCache[cacheKey] = persisted }
                return@withContext persisted.result
            }
        }

        val prioritizedAddons = streamAddons.sortedByDescending { getAddonHealthBias(it.id) }
        val movieRequest = MovieRuntimeRequest(imdbId = imdbId, title = title, year = year)
        val streams = addonRuntimeAggregator.resolveMovieStreams(
            stremioAddons = prioritizedAddons,
            request = movieRequest
        )
        val filteredStreams = applyQualityRegexFilters(streams)

        // Keep core source lookup fully addon-driven and non-blocking.
        // IPTV VOD enrichment is appended separately in ViewModels.

        val result = StreamResult(filteredStreams, subtitles)
        val createdAtMs = System.currentTimeMillis()
        synchronized(streamResultCache) {
            streamResultCache[cacheKey] = CachedStreamResult(result = result, createdAtMs = createdAtMs)
        }
        persistStreamResult(
            profileId = profileId,
            cacheKey = cacheKey,
            cached = CachedStreamResult(result = result, createdAtMs = createdAtMs)
        )
        result
    }

    fun resolveMovieStreamsProgressive(
        imdbId: String,
        title: String = "",
        year: Int? = null,
        forceRefresh: Boolean = false
    ): Flow<ProgressiveStreamResult> = callbackFlow {
        repositoryScope.launch {
            ensureAddonHealthLoaded()
            val allAddons = installedAddonsForSourceResolution()
            val streamAddons = getStreamAddons(allAddons, "movie", imdbId)
            val profileId = profileManager.getProfileIdSync()
            val cacheKey = streamCacheKey(
                profileId = profileId,
                type = "movie",
                imdbId = imdbId
            )
            if (!forceRefresh) {
                var warmCache: CachedStreamResult? = null
                synchronized(streamResultCache) {
                    val cached = streamResultCache[cacheKey]
                    if (cached != null) {
                        if (isStreamCacheFresh(cached)) {
                            trySend(ProgressiveStreamResult(cached.result.streams, cached.result.subtitles, 1, 1, true))
                            close()
                            return@launch
                        }
                        warmCache = cached
                    }
                }
                if (warmCache == null) {
                    warmCache = loadPersistedStreamResult(
                        profileId = profileId,
                        cacheKey = cacheKey,
                        graceMs = streamResultCacheDiskStaleGraceMs
                    )
                }
                warmCache?.let { cached ->
                    synchronized(streamResultCache) { streamResultCache[cacheKey] = cached }
                    trySend(
                        ProgressiveStreamResult(
                            streams = cached.result.streams,
                            subtitles = cached.result.subtitles,
                            completedAddons = 0,
                            totalAddons = 1,
                            isFinal = isStreamCacheFresh(cached)
                        )
                    )
                    if (isStreamCacheFresh(cached)) {
                        close()
                        return@launch
                    }
                }
            }

            val prioritizedAddons = streamAddons.sortedByDescending { getAddonHealthBias(it.id) }
            val telegramEnabled = telegramSourceResolver.isEnabled()
            if (prioritizedAddons.isEmpty() && !telegramEnabled) {
                Log.w(
                    TAG,
                    "[StreamFetch][Movie] no enabled streaming addons imdbId=$imdbId"
                )
                AppLogger.breadcrumb(
                    tag = "Sources",
                    message = "movie_no_stream_addons",
                    severity = "warning"
                )
                if (!forceRefresh) {
                    val cached = synchronized(streamResultCache) { streamResultCache[cacheKey] }
                    if (cached != null) {
                        trySend(ProgressiveStreamResult(cached.result.streams, cached.result.subtitles, 1, 1, true))
                        close()
                        return@launch
                    }
                    val persisted = loadPersistedStreamResult(profileId = profileId, cacheKey = cacheKey)
                    if (persisted != null) {
                        synchronized(streamResultCache) { streamResultCache[cacheKey] = persisted }
                        trySend(ProgressiveStreamResult(persisted.result.streams, persisted.result.subtitles, 1, 1, true))
                        close()
                        return@launch
                    }
                }
                trySend(ProgressiveStreamResult(emptyList(), emptyList(), 0, 0, true))
                close()
                return@launch
            }

            Log.d(
                TAG,
                "[StreamFetch][Movie] querying addons imdbId=$imdbId stremio=${prioritizedAddons.size} telegram=$telegramEnabled"
            )

            val mutex = Mutex()
            val aggregatedStreams = mutableListOf<StreamSource>()
            var completed = 0
            val totalAddons = prioritizedAddons.size + (if (telegramEnabled) 1 else 0)

            suspend fun sendProgress() {
                val deduped = aggregatedStreams
                    .filter { stream ->
                        val u = stream.url?.trim().orEmpty()
                        u.isNotBlank() && !u.startsWith("magnet:", ignoreCase = true)
                    }
                    .distinctBy { "${it.url?.trim().orEmpty()}|${it.source}" }
                val filtered = applyQualityRegexFilters(deduped)
                if (completed == totalAddons) {
                    val createdAtMs = System.currentTimeMillis()
                    val finalResult = StreamResult(filtered, emptyList())
                    synchronized(streamResultCache) {
                        streamResultCache[cacheKey] = CachedStreamResult(finalResult, createdAtMs)
                    }
                    persistStreamResult(
                        profileId = profileId,
                        cacheKey = cacheKey,
                        cached = CachedStreamResult(finalResult, createdAtMs)
                    )
                    if (filtered.isEmpty()) {
                        AppLogger.breadcrumb(
                            tag = "Sources",
                            message = "movie_sources_final_empty total_addons=$totalAddons",
                            severity = "warning"
                        )
                    }
                }
                val progressiveResult = ProgressiveStreamResult(
                    filtered,
                    emptyList(),
                    completed,
                    totalAddons,
                    completed == totalAddons
                )
                trySend(progressiveResult)
                if (progressiveResult.isFinal) close()
            }

            prioritizedAddons.forEach { addon ->
                launch {
                    val addonStreams = try {
                        fetchMovieStreamsFromAddon(addon, imdbId)
                    } catch (e: Exception) {
                        Log.e(TAG, "[StreamFetch][Movie] stremio addon ${addon.id} failed", e)
                        AppLogger.recordException(
                            throwable = e,
                            context = mapOf(
                                "error_area" to "StreamRepository",
                                "source_phase" to "movie_addon_parallel",
                                "addon_id" to addon.id
                            )
                        )
                        emptyList()
                    }
                    mutex.withLock {
                        aggregatedStreams.addAll(addonStreams)
                        completed += 1
                        sendProgress()
                    }
                }
            }

            if (telegramEnabled) {
                launch {
                    val telegramStreams = try {
                        telegramSourceResolver.resolve(title = title, year = year, imdbId = imdbId, isMovie = true)
                    } catch (e: Exception) {
                        Log.e(TAG, "[StreamFetch][Movie] telegram resolve failed", e)
                        emptyList()
                    }
                    mutex.withLock {
                        aggregatedStreams.addAll(telegramStreams)
                        completed += 1
                        sendProgress()
                    }
                }
            }
        }
        awaitClose { }
    }

    suspend fun resolveMovieVodOnly(
        imdbId: String?,
        title: String = "",
        year: Int? = null,
        tmdbId: Int? = null,
        timeoutMs: Long = 15_000L
    ): StreamSource? = resolveMovieVodSources(
        imdbId = imdbId,
        title = title,
        year = year,
        tmdbId = tmdbId,
        timeoutMs = timeoutMs
    ).firstOrNull()

    suspend fun hasHomeServerConnections(): Boolean = withContext(Dispatchers.IO) {
        runCatching { homeServerRepository.hasUsableConnections() }.getOrDefault(false)
    }

    suspend fun resolveMovieHomeServerSources(
        imdbId: String?,
        title: String = "",
        year: Int? = null,
        tmdbId: Int? = null,
        timeoutMs: Long = 5_000L
    ): List<StreamSource> = withContext(Dispatchers.IO) {
        withTimeoutOrNull(timeoutMs.coerceIn(250L, 20_000L)) {
            homeServerRepository.resolveMovieSources(
                imdbId = imdbId,
                title = title,
                year = year,
                tmdbId = tmdbId
            )
        }.orEmpty()
    }

    suspend fun resolveMovieVodSources(
        imdbId: String?,
        title: String = "",
        year: Int? = null,
        tmdbId: Int? = null,
        timeoutMs: Long = 15_000L
    ): List<StreamSource> = withContext(Dispatchers.IO) {
        withTimeoutOrNull(timeoutMs.coerceIn(500L, 90_000L)) {
            runCatching {
                iptvRepository.findMovieVodSources(
                    title = title,
                    year = year,
                    imdbId = imdbId,
                    tmdbId = tmdbId,
                    allowNetwork = true
                )
            }.onFailure { e ->
                System.err.println("[VOD] resolveMovieVodSources failed: ${e.message}")
                AppLogger.recordException(
                    throwable = e,
                    context = mapOf(
                        "error_area" to "StreamRepository",
                        "source_phase" to "movie_vod_resolution"
                    )
                )
            }.getOrDefault(emptyList())
        }.orEmpty()
    }

    /**
     * Process raw streams into StreamSource objects -  processStreams
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
                        val normalizedLang = normalizeLanguageCode(sub.lang)
                        // Use the original lang as fallback instead of hardcoding "en",
                        // so non-English subs with unparseable codes aren't mislabeled.
                        val langFallback = sub.lang?.trim()?.lowercase()?.take(2) ?: "und"
                        Subtitle(
                            id = sub.id ?: "${addon.id}_stream_sub_$index",
                            url = sub.url ?: "",
                            lang = normalizedLang.ifBlank { langFallback },
                            label = buildSubtitleLabel(normalizedLang.ifBlank { langFallback }, sub.label, addon.name, sub.id),
                            provider = addon.name
                        )
                    } ?: emptyList()

                val torrentName = stream.getTorrentName()
                val qualityFromTorrent = parseQuality(torrentName)

                StreamSource(
                    source = torrentName,
                    addonName = addon.name + " - " + stream.getSourceName(),
                    addonId = addon.id,
                    quality = if (qualityFromTorrent != "Unknown") qualityFromTorrent else stream.getQuality(),
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
                    sources = stream.sources ?: emptyList(),
                    description = stream.description?.trim()?.takeIf { it.isNotBlank() }
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
        forceRefresh: Boolean = false,
        airDate: String? = null
    ): StreamResult = withContext(Dispatchers.IO) {
        ensureAddonHealthLoaded()
        val subtitles = mutableListOf<Subtitle>()
        val allAddons = installedAddonsForSourceResolution()
        val isAnime = animeMapper.isAnimeContent(tmdbId, genreIds, originalLanguage)
        val streamAddons = getEpisodeStreamAddons(
            addons = allAddons,
            imdbId = imdbId,
            tmdbId = tmdbId,
            isAnime = isAnime,
            genreIds = genreIds,
            originalLanguage = originalLanguage
        )
        val cacheKey = streamCacheKey(
            profileId = profileManager.getProfileIdSync(),
            type = EPISODE_STREAM_CACHE_TYPE,
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

        val prioritizedAddons = streamAddons.sortedByDescending { getAddonHealthBias(it.id) }
        val episodeRequest = EpisodeRuntimeRequest(
            imdbId = imdbId,
            season = season,
            episode = episode,
            tmdbId = tmdbId,
            tvdbId = tvdbId,
            genreIds = genreIds,
            originalLanguage = originalLanguage,
            title = title,
            airDate = airDate
        )
        val streams = addonRuntimeAggregator.resolveEpisodeStreams(
            stremioAddons = prioritizedAddons,
            request = episodeRequest
        )
        val filteredStreams = applyQualityRegexFilters(streams)

        // Keep core source lookup fully addon-driven and non-blocking.
        // IPTV VOD enrichment is appended separately in ViewModels.

        val result = StreamResult(filteredStreams, subtitles)
        synchronized(streamResultCache) {
            streamResultCache[cacheKey] = CachedStreamResult(result = result, createdAtMs = System.currentTimeMillis())
        }
        result
    }

    fun resolveEpisodeStreamsProgressive(
        imdbId: String,
        season: Int,
        episode: Int,
        tmdbId: Int? = null,
        tvdbId: Int? = null,
        genreIds: List<Int> = emptyList(),
        originalLanguage: String? = null,
        title: String = "",
        forceRefresh: Boolean = false,
        airDate: String? = null
    ): Flow<ProgressiveStreamResult> = callbackFlow {
        repositoryScope.launch {
            ensureAddonHealthLoaded()
            val allAddons = installedAddonsForSourceResolution()
            val isAnime = animeMapper.isAnimeContent(tmdbId, genreIds, originalLanguage)
            val streamAddons = getEpisodeStreamAddons(
                addons = allAddons,
                imdbId = imdbId,
                tmdbId = tmdbId,
                isAnime = isAnime,
                genreIds = genreIds,
                originalLanguage = originalLanguage
            )
            val cacheKey = streamCacheKey(
                profileId = profileManager.getProfileIdSync(),
                type = EPISODE_STREAM_CACHE_TYPE,
                imdbId = imdbId,
                season = season,
                episode = episode
            )
            if (!forceRefresh) {
                var staleCache: CachedStreamResult? = null
                synchronized(streamResultCache) {
                    val cached = streamResultCache[cacheKey]
                    if (cached != null) {
                        if (isStreamCacheFresh(cached)) {
                            trySend(ProgressiveStreamResult(cached.result.streams, cached.result.subtitles, 1, 1, true))
                            close()
                            return@launch
                        }
                        staleCache = cached
                    }
                }
                staleCache?.let { cached ->
                    trySend(
                        ProgressiveStreamResult(
                            streams = cached.result.streams,
                            subtitles = cached.result.subtitles,
                            completedAddons = 0,
                            totalAddons = 1,
                            isFinal = false
                        )
                    )
                }
            }

            val prioritizedAddons = streamAddons.sortedByDescending { getAddonHealthBias(it.id) }
            val telegramEnabled = telegramSourceResolver.isEnabled()
            if (prioritizedAddons.isEmpty() && !telegramEnabled) {
                Log.w(
                    TAG,
                    "[StreamFetch][Episode] no enabled streaming addons imdbId=$imdbId season=$season episode=$episode"
                )
                AppLogger.breadcrumb(
                    tag = "Sources",
                    message = "episode_no_stream_addons season_set=${season > 0} episode_set=${episode > 0}",
                    severity = "warning"
                )
                if (!forceRefresh) {
                    val cached = synchronized(streamResultCache) { streamResultCache[cacheKey] }
                    if (cached != null) {
                        trySend(ProgressiveStreamResult(cached.result.streams, cached.result.subtitles, 1, 1, true))
                        close()
                        return@launch
                    }
                }
                trySend(ProgressiveStreamResult(emptyList(), emptyList(), 0, 0, true))
                close()
                return@launch
            }

            Log.d(
                TAG,
                "[StreamFetch][Episode] querying addons imdbId=$imdbId season=$season episode=$episode stremio=${prioritizedAddons.size} telegram=$telegramEnabled"
            )

            val mutex = Mutex()
            val aggregatedStreams = mutableListOf<StreamSource>()
            var completed = 0
            val totalAddons = prioritizedAddons.size + (if (telegramEnabled) 1 else 0)

            suspend fun sendProgress() {
                val deduped = aggregatedStreams
                    .filter { stream ->
                        val u = stream.url?.trim().orEmpty()
                        u.isNotBlank() && !u.startsWith("magnet:", ignoreCase = true)
                    }
                    .distinctBy { "${it.url?.trim().orEmpty()}|${it.source}" }
                val filtered = applyQualityRegexFilters(deduped)
                if (completed == totalAddons) {
                    val finalResult = StreamResult(filtered, emptyList())
                    synchronized(streamResultCache) {
                        streamResultCache[cacheKey] = CachedStreamResult(finalResult, System.currentTimeMillis())
                    }
                    if (filtered.isEmpty()) {
                        AppLogger.breadcrumb(
                            tag = "Sources",
                            message = "episode_sources_final_empty total_addons=$totalAddons season_set=${season > 0} episode_set=${episode > 0}",
                            severity = "warning"
                        )
                    }
                }
                val progressiveResult = ProgressiveStreamResult(
                    filtered,
                    emptyList(),
                    completed,
                    totalAddons,
                    completed == totalAddons
                )
                trySend(progressiveResult)
                if (progressiveResult.isFinal) close()
            }

            prioritizedAddons.forEach { addon ->
                launch {
                    val addonStreams = try {
                        fetchEpisodeStreamsFromAddon(
                            addon = addon,
                            imdbId = imdbId,
                            season = season,
                            episode = episode,
                            tmdbId = tmdbId,
                            tvdbId = tvdbId,
                            genreIds = genreIds,
                            originalLanguage = originalLanguage,
                            title = title,
                            airDate = airDate
                        )
                    } catch (e: Exception) {
                        Log.e(TAG, "[StreamFetch][Episode] stremio addon ${addon.id} failed", e)
                        AppLogger.recordException(
                            throwable = e,
                            context = mapOf(
                                "error_area" to "StreamRepository",
                                "source_phase" to "episode_addon_parallel",
                                "addon_id" to addon.id,
                                "season_set" to (season > 0).toString(),
                                "episode_set" to (episode > 0).toString()
                            )
                        )
                        emptyList()
                    }
                    mutex.withLock {
                        aggregatedStreams.addAll(addonStreams)
                        completed += 1
                        sendProgress()
                    }
                }
            }

            if (telegramEnabled) {
                launch {
                    val telegramStreams = try {
                        telegramSourceResolver.resolve(
                            title = title,
                            year = null,
                            season = season,
                            episode = episode,
                            imdbId = imdbId,
                            isMovie = false
                        )
                    } catch (e: Exception) {
                        Log.e(TAG, "[StreamFetch][Episode] telegram resolve failed", e)
                        emptyList()
                    }
                    mutex.withLock {
                        aggregatedStreams.addAll(telegramStreams)
                        completed += 1
                        sendProgress()
                    }
                }
            }
        }
        awaitClose { }
    }

    suspend fun resolveEpisodeVodOnly(
        imdbId: String?,
        season: Int,
        episode: Int,
        title: String = "",
        tmdbId: Int? = null,
        tvdbId: Int? = null,
        timeoutMs: Long = 45_000L
    ): StreamSource? = resolveEpisodeVodSources(
        imdbId = imdbId,
        season = season,
        episode = episode,
        title = title,
        tmdbId = tmdbId,
        tvdbId = tvdbId,
        timeoutMs = timeoutMs
    ).firstOrNull()

    suspend fun resolveEpisodeHomeServerSources(
        imdbId: String?,
        season: Int,
        episode: Int,
        title: String = "",
        tmdbId: Int? = null,
        tvdbId: Int? = null,
        timeoutMs: Long = 5_000L
    ): List<StreamSource> = withContext(Dispatchers.IO) {
        withTimeoutOrNull(timeoutMs.coerceIn(250L, 20_000L)) {
            homeServerRepository.resolveEpisodeSources(
                imdbId = imdbId,
                title = title,
                season = season,
                episode = episode,
                tmdbId = tmdbId,
                tvdbId = tvdbId
            )
        }.orEmpty()
    }

    suspend fun resolveEpisodeVodSources(
        imdbId: String?,
        season: Int,
        episode: Int,
        title: String = "",
        tmdbId: Int? = null,
        tvdbId: Int? = null,
        timeoutMs: Long = 45_000L
    ): List<StreamSource> = withContext(Dispatchers.IO) {
        withTimeoutOrNull(timeoutMs.coerceIn(500L, 90_000L)) {
            runCatching {
                iptvRepository.findEpisodeVodSources(
                    title = title,
                    season = season,
                    episode = episode,
                    imdbId = imdbId,
                    tmdbId = tmdbId,
                    allowNetwork = true
                )
            }.onFailure { e ->
                System.err.println("[VOD] resolveEpisodeVodSources failed: ${e.message}")
                AppLogger.recordException(
                    throwable = e,
                    context = mapOf(
                        "error_area" to "StreamRepository",
                        "source_phase" to "episode_vod_resolution",
                        "season_set" to (season > 0).toString(),
                        "episode_set" to (episode > 0).toString()
                    )
                )
            }.getOrDefault(emptyList())
        }.orEmpty()
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
        // Include:
        // - Addons classified as AddonType.SUBTITLE (OpenSubtitles and, going forward,
        //   any user-added pure-subtitle addon like Wizdom/Ktuvit now that addCustomAddon
        //   classifies them correctly).
        // - Addons classified as CUSTOM whose manifest declares a `subtitles` resource.
        //   This covers two cases: (a) addons installed before the classification fix
        //   landed, which are still stored as CUSTOM; (b) hybrid addons that provide
        //   both streams and subtitles. Both should be queried for subtitles.
        // Fixes issue #80.
        val subtitleAddons = allAddons.filter { addon ->
            if (!addon.isInstalled || !addon.isEnabled) return@filter false
            if (addon.type == AddonType.SUBTITLE) return@filter true
            if (addon.type == AddonType.CUSTOM) {
                val declaresSubtitles = addon.manifest?.resources?.any { res ->
                    res.name.equals("subtitles", ignoreCase = true)
                } == true
                return@filter declaresSubtitles
            }
            false
        }

        val videoHash = stream?.behaviorHints?.videoHash?.trim().takeUnless { it.isNullOrBlank() }
        val videoSize = stream?.behaviorHints?.videoSize?.takeIf { it > 0L }

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
                            val normalizedLang = normalizeLanguageCode(sub.lang)
                            val langFallback = sub.lang?.trim()?.lowercase()?.take(2) ?: "und"
                            Subtitle(
                                id = sub.id ?: "${addon.id}_sub_hint_$index",
                                url = sub.url ?: "",
                                lang = normalizedLang.ifBlank { langFallback },
                                label = buildSubtitleLabel(normalizedLang.ifBlank { langFallback }, sub.label, addon.name, sub.id),
                                provider = addon.name
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

    // Timeout for resolving a single stream URL (redirect chains, debrid resolvers).
    private val STREAM_RESOLUTION_TIMEOUT_MS = 8_000L
    private val STREAM_PREWARM_TTL_MS = 90_000L
    private val STREAM_PREWARM_EPHEMERAL_TTL_MS = 25_000L
    private val STREAM_PREWARM_NETWORK_TIMEOUT_MS = 700L
    private val STREAM_REDIRECT_RESOLUTION_TIMEOUT_MS = 1_800L
    private val PLAYBACK_HOST_BAD_TTL_MS = 5 * 60_000L
    private val SIDE_EFFECT_PRONE_PREWARM_HOST_MARKERS = setOf(
        "torrentio",
        "torbox",
        "stremthru",
        "comet",
        "mediafusion",
        "jackettio",
        "annatar",
        "knightcrawler",
        "debrid",
        "real-debrid",
        "realdebrid",
        "rdb.so",
        "rdeb.io",
        "alldebrid",
        "premiumize",
        "easydebrid",
        "offcloud"
    )
    private val SIDE_EFFECT_PRONE_PREWARM_TEXT_MARKERS = setOf(
        "torrentio",
        "torbox",
        "stremthru",
        "comet",
        "mediafusion",
        "jackettio",
        "annatar",
        "knightcrawler",
        "debrid",
        "real-debrid",
        "realdebrid",
        "all-debrid",
        "alldebrid",
        "premiumize",
        "easydebrid",
        "offcloud",
        "rd+",
        "ad+",
        "pm+",
        "tb+"
    )

    private fun playbackHostKey(url: String?): String {
        val host = runCatching { java.net.URI(url?.trim().orEmpty()).host?.lowercase(Locale.US) }
            .getOrNull()
            .orEmpty()
            .removePrefix("www.")
        return host
    }

    fun isPlaybackHostTemporarilyBad(stream: StreamSource): Boolean {
        val host = playbackHostKey(stream.url)
        if (host.isBlank()) return false
        val health = playbackHostHealth[host] ?: return false
        val ageMs = System.currentTimeMillis() - health.lastFailureAtMs
        if (ageMs > PLAYBACK_HOST_BAD_TTL_MS) {
            playbackHostHealth.remove(host)
            return false
        }
        return health.failures >= 2 && health.failures > health.successes
    }

    fun getPlaybackHostHealthPenalty(stream: StreamSource): Int {
        return if (isPlaybackHostTemporarilyBad(stream)) 1 else 0
    }

    fun notePlaybackHostFailure(stream: StreamSource?, reason: String = "") {
        val host = playbackHostKey(stream?.url)
        if (host.isBlank()) return
        val health = playbackHostHealth.getOrPut(host) { PlaybackHostHealth() }
        health.failures += 1
        health.lastFailureAtMs = System.currentTimeMillis()
        Log.w(TAG, "Playback host failure host=$host failures=${health.failures} reason=$reason")
    }

    fun notePlaybackHostSuccess(stream: StreamSource?) {
        val host = playbackHostKey(stream?.url)
        if (host.isBlank()) return
        val health = playbackHostHealth.getOrPut(host) { PlaybackHostHealth() }
        health.successes += 1
        if (health.successes >= health.failures) {
            playbackHostHealth.remove(host)
        }
    }

    private fun resolvedStreamCacheKey(stream: StreamSource): String {
        val infoHash = stream.infoHash?.trim()?.lowercase(Locale.US).orEmpty()
        if (infoHash.isNotBlank()) {
            return "ih:${stream.addonId}|$infoHash:${stream.fileIdx ?: -1}"
        }
        val url = stream.url?.trim().orEmpty()
        val urlKey = if (url.isNotBlank()) {
            url.substringBefore('|').substringBefore('#')
        } else {
            stream.source
        }
        return "${stream.addonId}|${stream.source}|$urlKey"
    }

    private fun isLikelyEphemeralPlaybackUrl(url: String, stream: StreamSource): Boolean {
        val lower = url.lowercase(Locale.US)
        return lower.contains("token=") ||
            lower.contains("expires=") ||
            lower.contains("signature=") ||
            lower.contains("sig=") ||
            lower.contains("exp=") ||
            lower.contains("auth=") ||
            stream.behaviorHints?.notWebReady == true ||
            !stream.behaviorHints?.proxyHeaders?.request.isNullOrEmpty()
    }

    private fun resolvedStreamCacheTtlMs(stream: StreamSource): Long {
        val url = stream.url?.trim().orEmpty()
        return if (isLikelyEphemeralPlaybackUrl(url, stream)) {
            STREAM_PREWARM_EPHEMERAL_TTL_MS
        } else {
            STREAM_PREWARM_TTL_MS
        }
    }

    private fun cachedResolvedStream(stream: StreamSource): StreamSource? {
        val cached = resolvedStreamCache[resolvedStreamCacheKey(stream)] ?: return null
        val ageMs = System.currentTimeMillis() - cached.createdAtMs
        return if (ageMs <= resolvedStreamCacheTtlMs(cached.stream)) {
            cached.stream
        } else {
            resolvedStreamCache.remove(resolvedStreamCacheKey(stream))
            null
        }
    }

    private fun shouldAvoidPlaybackProbe(url: String, stream: StreamSource): Boolean {
        if (isLikelyEphemeralPlaybackUrl(url, stream)) return true
        val host = runCatching { java.net.URI(url).host?.lowercase(Locale.US) }.getOrNull().orEmpty()
        if (host.isBlank()) return true
        return false
    }

    private fun shouldResolveRedirectBeforePlayback(url: String, stream: StreamSource): Boolean {
        val host = runCatching { java.net.URI(url).host?.lowercase(Locale.US) }.getOrNull().orEmpty()
        if (host.isBlank()) return false
        if (!stream.behaviorHints?.proxyHeaders?.request.isNullOrEmpty()) return false
        if (url.contains(".m3u8", ignoreCase = true) || url.contains(".mpd", ignoreCase = true)) return false

        return host.contains("torrentio", ignoreCase = true) ||
            host.contains("strem", ignoreCase = true) ||
            host.contains("comet", ignoreCase = true) ||
            host.contains("mediafusion", ignoreCase = true) ||
            host.contains("stremthru", ignoreCase = true) ||
            host.contains("jackettio", ignoreCase = true)
    }

    private suspend fun resolveRedirectedPlaybackUrl(
        url: String,
        headers: Map<String, String>
    ): String = withContext(Dispatchers.IO) {
        runCatching {
            withTimeout(STREAM_REDIRECT_RESOLUTION_TIMEOUT_MS) {
                val requestHeaders = headers.toMutableMap()
                if (requestHeaders.keys.none { it.equals("User-Agent", ignoreCase = true) }) {
                    requestHeaders["User-Agent"] = OkHttpProvider.userAgent
                }
                if (requestHeaders.keys.none { it.equals("Accept", ignoreCase = true) }) {
                    requestHeaders["Accept"] = "*/*"
                }
                if (requestHeaders.keys.none { it.equals("Range", ignoreCase = true) }) {
                    requestHeaders["Range"] = "bytes=0-1"
                }

                val request = Request.Builder()
                    .url(url)
                    .get()
                    .apply { requestHeaders.forEach { (key, value) -> addHeader(key, value) } }
                    .build()

                OkHttpProvider.playbackClient.newCall(request).execute().use { response ->
                    response.request.url.toString().takeIf { finalUrl ->
                        finalUrl.isNotBlank() && !finalUrl.equals(url, ignoreCase = true)
                    } ?: url
                }
            }
        }.getOrElse { error ->
            notePlaybackHostFailure(StreamSource("", "", "", "", "", url = url), error::class.java.simpleName)
            url
        }
    }

    private fun hostContainsAny(host: String, markers: Set<String>): Boolean {
        val normalized = host.lowercase(Locale.US).removePrefix("www.")
        return markers.any { marker -> normalized.contains(marker) }
    }

    private fun textContainsAny(text: String, markers: Set<String>): Boolean {
        val normalized = text.lowercase(Locale.US)
        return markers.any { marker -> normalized.contains(marker) }
    }

    private fun isSideEffectPronePrewarmSource(url: String, stream: StreamSource): Boolean {
        if (!stream.infoHash.isNullOrBlank()) return true
        if (stream.behaviorHints?.notWebReady == true) return true
        if (!stream.behaviorHints?.proxyHeaders?.request.isNullOrEmpty()) return true
        if (isLikelyEphemeralPlaybackUrl(url, stream)) return true
        if (shouldResolveRedirectBeforePlayback(url, stream)) return true

        val host = runCatching { java.net.URI(url).host?.lowercase(Locale.US) }.getOrNull().orEmpty()
        if (host.isBlank()) return true
        if (hostContainsAny(host, SIDE_EFFECT_PRONE_PREWARM_HOST_MARKERS)) return true

        val descriptor = buildString {
            append(stream.addonId).append(' ')
            append(stream.addonName).append(' ')
            append(stream.source).append(' ')
            append(url)
        }
        return textContainsAny(descriptor, SIDE_EFFECT_PRONE_PREWARM_TEXT_MARKERS)
    }

    fun canPrewarmWithoutSideEffects(stream: StreamSource): Boolean {
        val rawUrl = stream.url?.trim().orEmpty()
        if (rawUrl.isBlank()) return false
        if (rawUrl.startsWith("magnet:", ignoreCase = true)) return false
        if (!rawUrl.startsWith("http://", ignoreCase = true) && !rawUrl.startsWith("https://", ignoreCase = true)) {
            return false
        }
        return !isSideEffectPronePrewarmSource(rawUrl, stream)
    }

    private suspend fun warmHttpConnection(stream: StreamSource) {
        val rawUrl = stream.url?.trim().orEmpty()
        if (!rawUrl.startsWith("http://", true) && !rawUrl.startsWith("https://", true)) return
        if (shouldAvoidPlaybackProbe(rawUrl, stream)) return

        val headers = mergeRequestHeaders(
            base = stream.behaviorHints?.proxyHeaders?.request.orEmpty(),
            extra = emptyMap()
        ).toMutableMap()
        if (headers.keys.none { it.equals("User-Agent", ignoreCase = true) }) {
            headers["User-Agent"] = OkHttpProvider.userAgent
        }
        if (headers.keys.none { it.equals("Accept", ignoreCase = true) }) {
            headers["Accept"] = "*/*"
        }
        if (headers.keys.none { it.equals("Range", ignoreCase = true) }) {
            headers["Range"] = "bytes=0-1"
        }
        val referer = headers.entries.firstOrNull { it.key.equals("Referer", ignoreCase = true) }?.value
        if (!referer.isNullOrBlank() && headers.keys.none { it.equals("Origin", ignoreCase = true) }) {
            deriveOriginFromReferer(referer)?.let { origin -> headers["Origin"] = origin }
        }

        runCatching {
            withTimeout(STREAM_PREWARM_NETWORK_TIMEOUT_MS) {
                val request = Request.Builder()
                    .url(rawUrl)
                    .get()
                    .apply { headers.forEach { (key, value) -> addHeader(key, value) } }
                    .build()
                OkHttpProvider.playbackClient.newCall(request).execute().use { response ->
                    if (response.isSuccessful || response.code == 206 || response.code == 416) {
                        notePlaybackHostSuccess(stream)
                    } else {
                        notePlaybackHostFailure(stream, "prewarm_http_${response.code}")
                    }
                }
            }
        }.onFailure { error ->
            notePlaybackHostFailure(stream, error::class.java.simpleName)
        }
    }

    suspend fun saveLastGoodPlaybackPreference(
        mediaType: MediaType,
        tmdbId: Int,
        season: Int?,
        episode: Int?,
        stream: StreamSource
    ) = withContext(Dispatchers.IO) {
        val addonId = stream.addonId.trim()
        val source = stream.source.trim()
        val bingeGroup = stream.behaviorHints?.bingeGroup?.trim()?.takeIf { it.isNotBlank() }
        if (addonId.isBlank() && source.isBlank() && bingeGroup.isNullOrBlank()) return@withContext
        val preference = LastGoodPlaybackPreference(
            addonId = addonId,
            source = source,
            bingeGroup = bingeGroup
        )
        context.streamDataStore.edit { prefs ->
            prefs[lastGoodPlaybackKey(mediaType, tmdbId, season, episode)] = gson.toJson(preference)
        }
    }

    suspend fun getLastGoodPlaybackPreference(
        mediaType: MediaType,
        tmdbId: Int,
        season: Int?,
        episode: Int?
    ): LastGoodPlaybackPreference? = withContext(Dispatchers.IO) {
        val prefs = context.streamDataStore.data.first()
        val raw = prefs[lastGoodPlaybackKey(mediaType, tmdbId, season, episode)].orEmpty()
        if (raw.isBlank()) return@withContext null
        runCatching {
            gson.fromJson(raw, LastGoodPlaybackPreference::class.java)
        }.getOrNull()?.takeIf { preference ->
            preference.addonId.isNotBlank() || preference.source.isNotBlank() || !preference.bingeGroup.isNullOrBlank()
        }
    }

    /**
     * Resolve a single stream for playback - with timeout to prevent hanging forever
     */
    suspend fun resolveStreamForPlayback(stream: StreamSource): StreamSource? = withContext(Dispatchers.IO) {
        cachedResolvedStream(stream)?.let { return@withContext it }
        try {
            withTimeout(STREAM_RESOLUTION_TIMEOUT_MS) {
                resolveStreamInternal(stream)?.also { resolved ->
                    resolvedStreamCache[resolvedStreamCacheKey(stream)] = CachedResolvedStream(
                        stream = resolved,
                        createdAtMs = System.currentTimeMillis()
                    )
                }
            }
        } catch (e: TimeoutCancellationException) {
            AppLogger.breadcrumb(
                tag = "Sources",
                message = "playback_resolve_timeout kind=${sourceKind(stream)} addon=${stream.addonId.ifBlank { "unknown" }} quality=${stream.quality.ifBlank { "unknown" }}",
                severity = "warning"
            )
            null
        } catch (e: Exception) {
            AppLogger.breadcrumb(
                tag = "Sources",
                message = "playback_resolve_exception kind=${sourceKind(stream)} addon=${stream.addonId.ifBlank { "unknown" }} error=${e::class.java.simpleName}",
                severity = "warning"
            )
            null
        }
    }

    suspend fun prewarmStreamForPlayback(
        stream: StreamSource,
        allowNetworkWarmup: Boolean = true
    ): StreamSource? = withContext(Dispatchers.IO) {
        if (!canPrewarmWithoutSideEffects(stream)) return@withContext null
        val resolved = resolveStreamForPlayback(stream) ?: return@withContext null
        if (allowNetworkWarmup && canPrewarmWithoutSideEffects(resolved)) {
            warmHttpConnection(resolved)
        }
        resolved
    }

    suspend fun prewarmStreamsForPlayback(
        streams: List<StreamSource>,
        limit: Int = 3,
        allowNetworkWarmup: Boolean = true
    ) = withContext(Dispatchers.IO) {
        streams.asSequence()
            .filter { !it.url.isNullOrBlank() }
            .filter { canPrewarmWithoutSideEffects(it) }
            .take(limit.coerceAtLeast(0))
            .map { stream ->
                async {
                    runCatching {
                        prewarmStreamForPlayback(stream, allowNetworkWarmup)
                    }
                }
            }
            .toList()
            .awaitAll()
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
            val playbackUrl = if (shouldResolveRedirectBeforePlayback(resolvedUrl, stream)) {
                resolveRedirectedPlaybackUrl(resolvedUrl, mergedHeaders)
            } else {
                resolvedUrl
            }
            return stream.copy(
                url = playbackUrl,
                behaviorHints = mergedBehaviorHints
            )
        } else {
            return null
        }
    }

    suspend fun isHttpStreamReachable(stream: StreamSource, timeoutMs: Long = 2_500L): Boolean =
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
                headers["User-Agent"] = OkHttpProvider.userAgent
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
                    OkHttpProvider.playbackClient.newCall(request).execute().use { response ->
                        val code = response.code
                        if (code == 416) {
                            notePlaybackHostSuccess(resolved)
                            return@use true
                        }
                        if (!response.isSuccessful) {
                            notePlaybackHostFailure(resolved, "reachable_http_$code")
                            return@use false
                        }

                        val contentType = response.header("Content-Type").orEmpty().lowercase(Locale.US)
                        // HTTP addon sources should not resolve to plain HTML pages.
                        if (contentType.contains("text/html")) {
                            notePlaybackHostFailure(resolved, "reachable_html")
                            return@use false
                        }
                        notePlaybackHostSuccess(resolved)
                        true
                    }
                }
            } catch (error: Exception) {
                notePlaybackHostFailure(resolved, error::class.java.simpleName)
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

    private fun parseQuality(text: String): String {
        return when {
            text.contains("2160p", ignoreCase = true) || text.contains("4K", ignoreCase = true) -> "4K"
            text.contains("1080p", ignoreCase = true) -> "1080p"
            text.contains("720p", ignoreCase = true) -> "720p"
            text.contains("480p", ignoreCase = true) -> "480p"
            else -> "Unknown"
        }
    }

    fun getAddonHealthBias(addonId: String): Int {
        if (addonId.isBlank()) return 0
        if (addonHealthLoadedProfileId == null) {
            repositoryScope.launch { ensureAddonHealthLoaded() }
        }
        val stats = synchronized(addonRuntimeHealth) { addonRuntimeHealth[addonId] } ?: return 0
        val fetchNet = (stats.fetchSuccesses - stats.fetchFailures) * 12
        val playbackNet = (stats.playbackStarts - stats.playbackFailures) * 28
        val reliabilityPenalty = stats.consecutiveFailures * 15
        val latencyBonus = when {
            stats.avgFetchLatencyMs in 1..1_500L -> 20
            stats.avgFetchLatencyMs in 1_501L..3_000L -> 8
            stats.avgFetchLatencyMs > 8_000L -> -20
            else -> 0
        }
        return (fetchNet + playbackNet + latencyBonus - reliabilityPenalty).coerceIn(-220, 220)
    }

    fun noteAddonPlaybackStarted(addonId: String, startupMs: Long) {
        if (addonId.isBlank()) return
        if (addonHealthLoadedProfileId == null) {
            repositoryScope.launch { ensureAddonHealthLoaded() }
        }
        synchronized(addonRuntimeHealth) {
            val stats = addonRuntimeHealth.getOrPut(addonId) { AddonRuntimeHealth() }
            stats.playbackStarts += 1
            stats.consecutiveFailures = 0
            stats.avgStartupMs = rollingAverage(stats.avgStartupMs, startupMs)
        }
        persistAddonHealthAsync()
    }

    fun noteAddonPlaybackFailure(addonId: String) {
        if (addonId.isBlank()) return
        if (addonHealthLoadedProfileId == null) {
            repositoryScope.launch { ensureAddonHealthLoaded() }
        }
        synchronized(addonRuntimeHealth) {
            val stats = addonRuntimeHealth.getOrPut(addonId) { AddonRuntimeHealth() }
            stats.playbackFailures += 1
            stats.consecutiveFailures += 1
        }
        persistAddonHealthAsync()
    }

    private fun recordAddonFetchOutcome(addonId: String, success: Boolean, latencyMs: Long) {
        if (addonId.isBlank()) return
        synchronized(addonRuntimeHealth) {
            val stats = addonRuntimeHealth.getOrPut(addonId) { AddonRuntimeHealth() }
            if (success) {
                stats.fetchSuccesses += 1
                stats.consecutiveFailures = 0
            } else {
                stats.fetchFailures += 1
                stats.consecutiveFailures += 1
            }
            if (latencyMs > 0L) {
                stats.avgFetchLatencyMs = rollingAverage(stats.avgFetchLatencyMs, latencyMs)
            }
        }
        persistAddonHealthAsync()
    }

    private suspend fun ensureAddonHealthLoaded() {
        val profileId = profileManager.getProfileIdSync().ifBlank { "default" }
        if (addonHealthLoadedProfileId == profileId) return

        val raw = context.streamDataStore.data.first()[addonHealthKeyFor(profileId)].orEmpty()
        val parsed: Map<String, AddonRuntimeHealth> = if (raw.isBlank()) {
            emptyMap()
        } else {
            runCatching {
                val type = TypeToken.getParameterized(Map::class.java, String::class.java, AddonRuntimeHealth::class.java).type
                gson.fromJson<Map<String, AddonRuntimeHealth>>(raw, type)
            }.getOrNull().orEmpty()
        }

        synchronized(addonRuntimeHealth) {
            addonRuntimeHealth.clear()
            addonRuntimeHealth.putAll(parsed)
        }
        addonHealthLoadedProfileId = profileId
    }

    private fun persistAddonHealthAsync() {
        val loadedProfile = addonHealthLoadedProfileId ?: return
        val snapshot = synchronized(addonRuntimeHealth) { LinkedHashMap(addonRuntimeHealth) }
        repositoryScope.launch {
            runCatching {
                context.streamDataStore.edit { prefs ->
                    prefs[addonHealthKeyFor(loadedProfile)] = gson.toJson(snapshot)
                }
            }
        }
    }

    private fun rollingAverage(current: Long, sample: Long): Long {
        if (sample <= 0L) return current
        return if (current <= 0L) sample else ((current * 3L) + sample) / 4L
    }

    /**
     * Parse size string (e.g., "2.5 GB", "800 MB") to bytes for sorting
     */
    private fun parseSizeToBytes(sizeStr: String): Long {
        if (sizeStr.isBlank()) return 0L

        // Normalize comma decimals (European format: "5,71 GB" -> "5.71 GB")
        val normalized = sizeStr.replace(",", ".")
        val match = StreamRegexes.SIZE_REGEX.find(normalized) ?: return 0L

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
        provider: String?,
        id: String? = null
    ): String {
        val normalized = normalizeLanguageCode(lang)
        val languageName = languageDisplayName(normalized)
        val label = rawLabel?.trim().orEmpty()
        val displayName = when {
            id.isNullOrBlank() -> {
                when {
                    label.isBlank() -> provider?.trim().orEmpty()
                    looksLikeLanguageLabel(label, languageName, normalized) -> provider?.trim().orEmpty()
                    label.startsWith("http", ignoreCase = true) -> provider?.trim().orEmpty()
                    else -> label
                }
            }
            else -> id.trim()
        }
        return if (displayName.isNotBlank() && !displayName.equals(languageName, ignoreCase = true)) {
            "$languageName - $displayName"
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
        val raw = lang?.trim().orEmpty()
        // Handle hyphenated codes like "pt-BR", "zh-CN", "en-US" → take the base language
        val lower = raw.lowercase().split("-", "_").first()
        return when (lower) {
            "eng", "english" -> "en"
            "spa", "spanish", "español" -> "es"
            "fra", "fre", "french", "français" -> "fr"
            "deu", "ger", "german", "deutsch" -> "de"
            "ita", "italian", "italiano" -> "it"
            "por", "portuguese", "português" -> "pt"
            "nld", "dut", "dutch", "nederlands" -> "nl"
            "rus", "russian", "русский" -> "ru"
            "zho", "chi", "chinese" -> "zh"
            "jpn", "japanese" -> "ja"
            "kor", "korean" -> "ko"
            "ara", "arabic" -> "ar"
            "hin", "hindi" -> "hi"
            "tur", "turkish" -> "tr"
            "pol", "polish" -> "pl"
            "swe", "swedish" -> "sv"
            "nor", "nob", "nno", "norwegian" -> "no"
            "dan", "danish" -> "da"
            "fin", "finnish" -> "fi"
            "ell", "gre", "greek" -> "el"
            "ces", "cze", "czech" -> "cs"
            "hun", "hungarian" -> "hu"
            "ron", "rum", "romanian" -> "ro"
            "tha", "thai" -> "th"
            "vie", "vietnamese" -> "vi"
            "ind", "indonesian" -> "id"
            "heb", "hebrew" -> "he"
            "bul", "bulgarian" -> "bg"
            "hrv", "croatian" -> "hr"
            "srp", "serbian" -> "sr"
            "slk", "slo", "slovak" -> "sk"
            "slv", "slovenian" -> "sl"
            "ukr", "ukrainian" -> "uk"
            "cat", "catalan" -> "ca"
            "glg", "galician" -> "gl"
            "eus", "baq", "basque" -> "eu"
            "msa", "may", "malay" -> "ms"
            "fil", "tgl", "tagalog", "filipino" -> "tl"
            "per", "fas", "persian", "farsi" -> "fa"
            "ben", "bengali" -> "bn"
            "tam", "tamil" -> "ta"
            "tel", "telugu" -> "te"
            "urd", "urdu" -> "ur"
            "pob" -> "pt"  // "Portuguese (BR)" used by OpenSubtitles
            else -> if (lower.length >= 2) lower.take(2) else lower
        }
    }

    private suspend fun applyQualityRegexFilters(streams: List<StreamSource>): List<StreamSource> {
        if (streams.isEmpty()) return streams
        if (cachedQualityFilters.isEmpty) return streams

        // Use precompiled regexes from cache (no DataStore reads, no recompilation)
        return streams.filter { stream ->
            val qualityText = buildString {
                append(stream.quality)
                if (stream.source.isNotBlank()) {
                    append(' ')
                    append(stream.source)
                }
            }
            cachedQualityFilters.regexes.none { regex -> regex.containsMatchIn(qualityText) }
        }
    }
}

    /**
     * Filter streams based on active quality regex filters.
     * Enabled filters exclude matching quality patterns from the stream list.
     */
    fun filterStreamsByQualityRegex(
        streams: List<StreamSource>,
        qualityFilters: List<com.arflix.tv.data.model.QualityFilterConfig>
    ): List<StreamSource> {
        val enabledFilters = qualityFilters.filter { it.enabled && it.regexPattern.isNotBlank() }
        if (enabledFilters.isEmpty()) return streams

        // Assuming qualityFilters are matching cached filters logic; to optimize, we rely on the caller maintaining cache or do a fast safe-compile.
        val compiledRegexes = enabledFilters.mapNotNull { filter ->
            try {
                Regex(filter.regexPattern, RegexOption.IGNORE_CASE)
            } catch (e: java.util.regex.PatternSyntaxException) {
                null
            }
        }

        if (compiledRegexes.isEmpty()) return streams

        return streams.filter { stream ->
            val qualityText = buildString {
                append(stream.quality)
                if (stream.source.isNotBlank()) {
                    append(' ')
                    append(stream.source)
                }
            }
            // Check if this stream matches any exclusion filter regex
            compiledRegexes.none { regex ->
                regex.containsMatchIn(qualityText)
            }
        }
    }

private object StreamRegexes {
    val MANIFEST_TYPO_REGEX = Regex("""(?i)/manifest\.json[a-z0-9_-]+(?=($|[?]))""")
    val NUVIO_REGEX = Regex("nu" + "vio", RegexOption.IGNORE_CASE)
    val SIZE_REGEX = Regex("""([\d.]+)\s*(GB|MB|KB|TB)""", RegexOption.IGNORE_CASE)
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

private data class StoredAddonPayload(
    val id: String? = null,
    val name: String? = null,
    val version: String? = null,
    val description: String? = null,
    val isInstalled: Boolean? = null,
    val isEnabled: Boolean? = null,
    val type: AddonType? = null,
    val runtimeKind: RuntimeKind? = null,
    val installSource: AddonInstallSource? = null,
    val url: String? = null,
    val logo: String? = null,
    val manifest: AddonManifest? = null,
    val transportUrl: String? = null
)

/**
 * Stream resolution result
 */
data class StreamResult(
    val streams: List<StreamSource>,
    val subtitles: List<Subtitle>
)

data class ProgressiveStreamResult(
    val streams: List<StreamSource>,
    val subtitles: List<Subtitle> = emptyList(),
    val completedAddons: Int,
    val totalAddons: Int,
    val isFinal: Boolean
)
