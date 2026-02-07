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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import java.security.MessageDigest
import java.util.Locale
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
    private val authRepository: AuthRepository,
    private val profileManager: ProfileManager,
    private val animeMapper: AnimeMapper
) {
    private val gson = Gson()
    private val TAG = "StreamRepository"

    // Profile-scoped preference keys - each profile has its own addons
    private fun addonsKey() = profileManager.profileStringKey("installed_addons")
    private fun pendingAddonsKey() = profileManager.profileStringKey("pending_addons")

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

    // ========== Addon Management ==========

    val installedAddons: Flow<List<Addon>> = context.streamDataStore.data.map { prefs ->
        val json = prefs[addonsKey()]
        val pendingJson = prefs[pendingAddonsKey()]
        val addons = parseAddons(json)
            ?: parseAddons(pendingJson)
            ?: run {
                getDefaultAddonList()
            }

        // ALWAYS ensure OpenSubtitles is present and enabled
        val hasOpenSubs = addons.any { it.id == "opensubtitles" && it.type == AddonType.SUBTITLE }
        val finalAddons = if (!hasOpenSubs) {
            val openSubsAddon = Addon(
                id = "opensubtitles",
                name = "OpenSubtitles v3",
                version = "1.0.0",
                description = "Subtitles from OpenSubtitles",
                isInstalled = true,
                isEnabled = true,
                type = AddonType.SUBTITLE,
                url = "https://opensubtitles-v3.strem.io/subtitles",
                transportUrl = "https://opensubtitles-v3.strem.io/subtitles"
            )
            addons + openSubsAddon
        } else {
            // Make sure it's enabled
            addons.map { addon ->
                if (addon.id == "opensubtitles") addon.copy(isEnabled = true) else addon
            }
        }

        finalAddons
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

    suspend fun toggleAddon(addonId: String) {
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
            val normalizedUrl = url.trim()
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

        return AddonManifest(
            id = manifest.id,
            name = manifest.name,
            version = manifest.version,
            description = manifest.description ?: "",
            logo = manifest.logo,
            background = manifest.background,
            types = manifest.types ?: emptyList(),
            resources = resources,
            idPrefixes = manifest.idPrefixes
        )
    }

    suspend fun removeAddon(addonId: String) {
        val addons = installedAddons.first().filter { it.id != addonId }
        saveAddons(addons)
    }

    private suspend fun saveAddons(addons: List<Addon>) {
        val json = gson.toJson(addons)

        // Save locally
        context.streamDataStore.edit { prefs ->
            prefs[addonsKey()] = json
        }

        // Sync to Supabase (non-blocking)
        val result = authRepository.saveAddonsToProfile(json)
        if (result.isSuccess) {
            context.streamDataStore.edit { prefs ->
                prefs.remove(pendingAddonsKey())
            }
        } else {
            context.streamDataStore.edit { prefs ->
                prefs[pendingAddonsKey()] = json
            }
        }
    }

    /**
     * Load addons from Supabase profile (called on login)
     * Merges cloud addons with local defaults
     */
    suspend fun syncAddonsFromCloud() {
        try {
            val pendingJson = context.streamDataStore.data.first()[pendingAddonsKey()]
            if (!pendingJson.isNullOrEmpty()) {
                val pushResult = authRepository.saveAddonsToProfile(pendingJson)
                if (pushResult.isSuccess) {
                    context.streamDataStore.edit { prefs ->
                        prefs.remove(pendingAddonsKey())
                        prefs[addonsKey()] = pendingJson
                    }
                } else {
                    return
                }
            }

            val cloudJson = authRepository.getAddonsFromProfile()
            if (!cloudJson.isNullOrEmpty()) {
                val cloudAddons = parseAddons(cloudJson) ?: emptyList()
                val localAddons = parseAddons(context.streamDataStore.data.first()[addonsKey()]) ?: emptyList()

                if (cloudAddons.isNotEmpty()) {
                    // Use cloud addons, but ensure built-in ones are present
                    val builtInIds = setOf("opensubtitles")
                    val defaultBuiltIns = getDefaultAddonList().filter { it.id in builtInIds }

                    // Merge local + cloud (prefer local to avoid losing recent changes)
                    val mergedLocalCloud = mergeAddonLists(localAddons, cloudAddons)
                    val mergedIds = mergedLocalCloud.map { it.id }.toSet()
                    val missingBuiltIns = defaultBuiltIns.filter { it.id !in mergedIds }
                    val mergedAddons = mergedLocalCloud + missingBuiltIns

                    // Save merged list locally
                    context.streamDataStore.edit { prefs ->
                        prefs[addonsKey()] = gson.toJson(mergedAddons)
                    }

                }
            } else {
            }
        } catch (e: Exception) {
        }
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
        var cleanUrl = url.trim()
        if (!cleanUrl.startsWith("http")) {
            cleanUrl = "https://$cleanUrl"
        }
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
        var cleanUrl = url.trim()
        if (!cleanUrl.startsWith("http")) {
            cleanUrl = "https://$cleanUrl"
        }
        cleanUrl = cleanUrl.trimEnd('/')
        // Remove common suffixes that shouldn't be in the base URL
        cleanUrl = cleanUrl.removeSuffix("/manifest.json")
        cleanUrl = cleanUrl.removeSuffix("/stream")  // Some addons incorrectly include /stream
        cleanUrl = cleanUrl.removeSuffix("/catalog")
        return cleanUrl
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

            // Custom addons - always try them (user explicitly added them)
            if (addon.type == AddonType.CUSTOM) return@filter true

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

    // Timeout for individual addon requests (15 seconds - needs time to fetch AND process many streams)
    private val ADDON_TIMEOUT_MS = 15000L

    /**
     * Resolve streams for a movie using INSTALLED addons
     * Uses progressive loading - streams appear as each addon responds
     */
    suspend fun resolveMovieStreams(imdbId: String): StreamResult = withContext(Dispatchers.IO) {
        val allStreams = MutableStateFlow<List<StreamSource>>(emptyList())
        val subtitles = mutableListOf<Subtitle>()

        val allAddons = installedAddons.first()
        val streamAddons = getStreamAddons(allAddons, "movie", imdbId)

        // Fetch from all addons in parallel with timeout
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
                        val streamCount = response.streams?.size ?: 0
                        processStreams(response.streams ?: emptyList(), addon)
                    }
                } catch (e: TimeoutCancellationException) {
                    emptyList()
                } catch (e: Exception) {
                    emptyList()
                }
            }
        }

        // Fetch subtitles with timeout
        val subtitleAddons = allAddons.filter {
            it.isInstalled && it.isEnabled && it.type == AddonType.SUBTITLE
        }

        val subtitleJobs = subtitleAddons.map { addon ->
            async {
                try {
                    withTimeout(ADDON_TIMEOUT_MS) {
                        val addonUrl = addon.url
                        if (addonUrl.isNullOrBlank()) return@withTimeout emptyList<Subtitle>()
                        val (baseUrl, _) = getAddonBaseUrl(addonUrl)
                        val url = "$baseUrl/movie/$imdbId.json"
                        val response = streamApi.getSubtitles(url)
                        response.subtitles?.mapIndexed { index, sub ->
                            Subtitle(
                                id = sub.id ?: "${addon.id}_sub_$index",
                                url = sub.url ?: "",
                                lang = sub.lang ?: "en",
                                label = buildSubtitleLabel(sub.lang, sub.label, addon.name)
                            )
                        } ?: emptyList()
                    }
                } catch (e: TimeoutCancellationException) {
                    emptyList()
                } catch (e: Exception) {
                    emptyList()
                }
            }
        }

        val streams = streamJobs.awaitAll().flatten()
        subtitles.addAll(subtitleJobs.awaitAll().flatten())

        StreamResult(streams, subtitles)
    }

    /**
     * Process raw streams into StreamSource objects - like NuvioStreaming processStreams
     */
    private fun processStreams(streams: List<StremioStream>, addon: Addon): List<StreamSource> {
        val filtered = streams.filter { stream ->
            stream.hasPlayableLink() && (stream.title != null || stream.name != null)
        }

        return filtered
            .mapNotNull { stream ->
                val streamUrl = stream.getStreamUrl() ?: return@mapNotNull null

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
                    behaviorHints = stream.behaviorHints?.let {
                        ModelStreamBehaviorHints(
                            notWebReady = it.notWebReady ?: false,
                            bingeGroup = it.bingeGroup,
                            videoHash = it.videoHash,
                            videoSize = it.videoSize,
                            filename = it.filename
                        )
                    },
                    subtitles = embeddedSubs
                )
            }
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
        title: String = ""
    ): StreamResult = withContext(Dispatchers.IO) {
        val subtitles = mutableListOf<Subtitle>()

        // Check if this is anime - use comprehensive detection
        val isAnime = animeMapper.isAnimeContent(tmdbId, genreIds, originalLanguage)
        Log.d(TAG, "Episode streams: title='$title', imdbId=$imdbId, season=$season, ep=$episode, isAnime=$isAnime, lang=$originalLanguage, genres=$genreIds")

        // Get anime query using 5-tier fallback resolution
        val animeQuery = if (isAnime) {
            animeMapper.resolveAnimeEpisodeQuery(
                tmdbId = tmdbId,
                tvdbId = tvdbId,
                title = title,
                imdbId = imdbId,
                season = season,
                episode = episode
            ).also { Log.d(TAG, "Anime query resolved: $it") }
        } else null

        val seriesId = "$imdbId:$season:$episode"
        Log.d(TAG, "Using seriesId: $seriesId, animeQuery: $animeQuery")

        val allAddons = installedAddons.first()
        val streamAddons = getStreamAddons(allAddons, "series", imdbId)

        // Fetch from all addons in parallel with timeout
        // If Kitsu query returns zero results, retry with IMDB format as fallback
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
                        var streams = processStreams(response.streams ?: emptyList(), addon)

                        // Fallback: if Kitsu query returned zero results, retry with IMDB format
                        if (streams.isEmpty() && useKitsu && contentId != seriesId) {
                            Log.d(TAG, "Kitsu query '$contentId' returned 0 results for ${addon.name}, retrying with IMDB: $seriesId")
                            val fallbackUrl = if (queryParams != null) {
                                "$baseUrl/stream/series/$seriesId.json?$queryParams"
                            } else {
                                "$baseUrl/stream/series/$seriesId.json"
                            }
                            try {
                                val fallbackResponse = streamApi.getAddonStreams(fallbackUrl)
                                streams = processStreams(fallbackResponse.streams ?: emptyList(), addon)
                            } catch (e: Exception) {
                                Log.w(TAG, "IMDB fallback also failed for ${addon.name}: ${e.message}")
                            }
                        }

                        // Fallback 2: For anime, try absolute episode numbering (imdb:0:absoluteEp)
                        // Some anime sources use absolute episode numbering across all seasons
                        if (streams.isEmpty() && isAnime && season > 0) {
                            // Calculate approximate absolute episode number
                            // For season 1, it's just the episode number; for later seasons, add previous season eps
                            val absoluteEpisode = if (season == 1) episode else {
                                // Estimate: assume ~12-24 eps per season (use 12 as conservative)
                                ((season - 1) * 12) + episode
                            }
                            val absoluteId = "$imdbId:0:$absoluteEpisode"
                            Log.d(TAG, "Anime fallback: trying absolute numbering for ${addon.name}: $absoluteId")
                            val absoluteUrl = if (queryParams != null) {
                                "$baseUrl/stream/series/$absoluteId.json?$queryParams"
                            } else {
                                "$baseUrl/stream/series/$absoluteId.json"
                            }
                            try {
                                val absoluteResponse = streamApi.getAddonStreams(absoluteUrl)
                                streams = processStreams(absoluteResponse.streams ?: emptyList(), addon)
                                if (streams.isNotEmpty()) {
                                    Log.d(TAG, "Absolute numbering worked for ${addon.name}: found ${streams.size} streams")
                                }
                            } catch (e: Exception) {
                                Log.w(TAG, "Absolute numbering fallback also failed for ${addon.name}: ${e.message}")
                            }
                        }

                        streams
                    }
                } catch (e: TimeoutCancellationException) {
                    emptyList()
                } catch (e: Exception) {
                    emptyList()
                }
            }
        }

        // Fetch subtitles with timeout
        val subtitleAddons = allAddons.filter {
            it.isInstalled && it.isEnabled && it.type == AddonType.SUBTITLE
        }

        val subtitleJobs = subtitleAddons.map { addon ->
            async {
                try {
                    withTimeout(ADDON_TIMEOUT_MS) {
                        val addonUrl = addon.url
                        if (addonUrl.isNullOrBlank()) return@withTimeout emptyList<Subtitle>()
                        val (baseUrl, _) = getAddonBaseUrl(addonUrl)
                        val url = "$baseUrl/series/$seriesId.json"
                        val response = streamApi.getSubtitles(url)
                        response.subtitles?.mapIndexed { index, sub ->
                            Subtitle(
                                id = sub.id ?: "${addon.id}_sub_$index",
                                url = sub.url ?: "",
                                lang = sub.lang ?: "en",
                                label = buildSubtitleLabel(sub.lang, sub.label, addon.name)
                            )
                        } ?: emptyList()
                    }
                } catch (e: TimeoutCancellationException) {
                    emptyList()
                } catch (e: Exception) {
                    emptyList()
                }
            }
        }

        val streams = streamJobs.awaitAll().flatten()
        subtitles.addAll(subtitleJobs.awaitAll().flatten())

        StreamResult(streams, subtitles)
    }

    // Timeout for resolving a single stream (10 seconds)
    private val STREAM_RESOLUTION_TIMEOUT_MS = 10000L

    /**
     * Resolve a single stream for playback - with timeout to prevent hanging forever
     */
    suspend fun resolveStreamForPlayback(stream: StreamSource): StreamSource? = withContext(Dispatchers.IO) {
        val url = stream.url ?: return@withContext null

        try {
            withTimeout(STREAM_RESOLUTION_TIMEOUT_MS) {
                resolveStreamInternal(stream, url)
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
    private suspend fun resolveStreamInternal(stream: StreamSource, url: String): StreamSource? {
        return if (url.startsWith("http://") || url.startsWith("https://")) {
            stream
        } else {
            null
        }
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
