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
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import java.net.URLEncoder
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
 * Enhanced with addon management and debrid integration
 */
@Singleton
class StreamRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val streamApi: StreamApi,
    private val debridRepository: DebridRepository,
    private val authRepository: AuthRepository
) {
    private val gson = Gson()
    private val TAG = "StreamRepository"
    
    // Preference keys
    private val ADDONS_KEY = stringPreferencesKey("installed_addons")
    private val PENDING_ADDONS_KEY = stringPreferencesKey("pending_addons")
    
    // Default addons - only built-in sources that work without configuration
    // Note: Torrentio is NOT included - users must add their own configured version
    // via Settings > Addons > Add Custom Addon with their debrid/quality settings
    private val defaultAddons = listOf(
        AddonConfig(
            id = "opensubtitles",
            name = "OpenSubtitles v3",
            baseUrl = "https://opensubtitles-v3.strem.io/subtitles",
            type = AddonType.SUBTITLE,
            isEnabled = true
        ),
        AddonConfig(
            id = "anime-kitsu",
            name = "Anime Kitsu",
            baseUrl = "${Constants.ANIME_KITSU_URL}/stream",
            type = AddonType.COMMUNITY,
            isEnabled = true
        )
    )
    
    // ========== Addon Management ==========

    val installedAddons: Flow<List<Addon>> = context.streamDataStore.data.map { prefs ->
        val json = prefs[ADDONS_KEY]
        val pendingJson = prefs[PENDING_ADDONS_KEY]
        val addons = parseAddons(json)
            ?: parseAddons(pendingJson)
            ?: run {
                Log.d(TAG, "No addons in storage, using defaults")
                getDefaultAddonList()
            }

        // ALWAYS ensure OpenSubtitles is present and enabled
        val hasOpenSubs = addons.any { it.id == "opensubtitles" && it.type == AddonType.SUBTITLE }
        val finalAddons = if (!hasOpenSubs) {
            Log.d(TAG, "OpenSubtitles addon missing, adding it")
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

        Log.d(TAG, "Returning ${finalAddons.size} addons: ${finalAddons.map { it.name }}")
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
                    "anime-kitsu" -> "Anime torrents with Kitsu metadata"
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
            Log.d(TAG, "Fetching addon manifest from: $manifestUrl")

            val manifest = streamApi.getAddonManifest(manifestUrl)
            Log.d(TAG, "Got manifest: ${manifest.name} v${manifest.version}")

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
            Log.e(TAG, "Failed to add addon: ${e.message}", e)
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
            prefs[ADDONS_KEY] = json
        }

        // Sync to Supabase (non-blocking)
        val result = authRepository.saveAddonsToProfile(json)
        if (result.isSuccess) {
            context.streamDataStore.edit { prefs ->
                prefs.remove(PENDING_ADDONS_KEY)
            }
            Log.d(TAG, "Addons synced to Supabase")
        } else {
            context.streamDataStore.edit { prefs ->
                prefs[PENDING_ADDONS_KEY] = json
            }
            Log.e(TAG, "Failed to sync addons to Supabase (pending retry)", result.exceptionOrNull())
        }
    }

    /**
     * Load addons from Supabase profile (called on login)
     * Merges cloud addons with local defaults
     */
    suspend fun syncAddonsFromCloud() {
        try {
            val pendingJson = context.streamDataStore.data.first()[PENDING_ADDONS_KEY]
            if (!pendingJson.isNullOrEmpty()) {
                val pushResult = authRepository.saveAddonsToProfile(pendingJson)
                if (pushResult.isSuccess) {
                    context.streamDataStore.edit { prefs ->
                        prefs.remove(PENDING_ADDONS_KEY)
                        prefs[ADDONS_KEY] = pendingJson
                    }
                    Log.d(TAG, "Pending addons synced to Supabase")
                } else {
                    Log.e(TAG, "Pending addons sync failed, keeping local", pushResult.exceptionOrNull())
                    return
                }
            }

            val cloudJson = authRepository.getAddonsFromProfile()
            if (!cloudJson.isNullOrEmpty()) {
                val cloudAddons = parseAddons(cloudJson) ?: emptyList()
                val localAddons = parseAddons(context.streamDataStore.data.first()[ADDONS_KEY]) ?: emptyList()

                if (cloudAddons.isNotEmpty()) {
                    // Use cloud addons, but ensure built-in ones are present
                    val builtInIds = setOf("opensubtitles", "anime-kitsu")
                    val defaultBuiltIns = getDefaultAddonList().filter { it.id in builtInIds }

                    // Merge local + cloud (prefer local to avoid losing recent changes)
                    val mergedLocalCloud = mergeAddonLists(localAddons, cloudAddons)
                    val mergedIds = mergedLocalCloud.map { it.id }.toSet()
                    val missingBuiltIns = defaultBuiltIns.filter { it.id !in mergedIds }
                    val mergedAddons = mergedLocalCloud + missingBuiltIns

                    // Save merged list locally
                    context.streamDataStore.edit { prefs ->
                        prefs[ADDONS_KEY] = gson.toJson(mergedAddons)
                    }

                    Log.d(TAG, "Synced ${cloudAddons.size} addons from Supabase")
                }
            } else {
                Log.d(TAG, "No addons in cloud, using local/defaults")
            }
        } catch (e: Exception) {
            Log.e(TAG, "Failed to sync addons from cloud", e)
        }
    }

    private fun parseAddons(json: String?): List<Addon>? {
        if (json.isNullOrBlank()) return null
        return try {
            val type = TypeToken.getParameterized(List::class.java, Addon::class.java).type
            val parsed: List<Addon> = gson.fromJson(json, type)
            Log.d(TAG, "Loaded ${parsed.size} addons from storage")
            parsed
        } catch (e: Exception) {
            Log.e(TAG, "Failed to parse addons from storage", e)
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
        return if (cleanUrl.endsWith("/manifest.json")) {
            cleanUrl.removeSuffix("/manifest.json")
        } else {
            cleanUrl
        }
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
        Log.d(TAG, "getStreamAddons: Filtering ${addons.size} addons for type=$type, id=$id")

        return addons.filter { addon ->
            Log.d(TAG, "getStreamAddons: Checking ${addon.name} (type=${addon.type}, installed=${addon.isInstalled}, enabled=${addon.isEnabled}, url=${addon.url})")

            // Must be installed and enabled
            if (!addon.isInstalled || !addon.isEnabled) {
                Log.d(TAG, "getStreamAddons: ${addon.name} SKIPPED - not installed or not enabled")
                return@filter false
            }

            // Skip subtitle addons
            if (addon.type == AddonType.SUBTITLE) {
                Log.d(TAG, "getStreamAddons: ${addon.name} SKIPPED - subtitle addon")
                return@filter false
            }

            // Must have a URL to fetch from
            if (addon.url.isNullOrBlank()) {
                Log.d(TAG, "getStreamAddons: ${addon.name} SKIPPED - no URL")
                return@filter false
            }

            // Custom addons - always try them (user explicitly added them)
            if (addon.type == AddonType.CUSTOM) {
                Log.d(TAG, "getStreamAddons: ${addon.name} INCLUDED (custom addon)")
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
                Log.d(TAG, "getStreamAddons: ${addon.name} manifest check - hasStreamResource=$hasStreamResource, resources=${manifest.resources.map { it.name }}")
                if (hasStreamResource) return@filter true
                // Don't immediately skip - fall through to default check
            }

            // Check global idPrefixes if present (but be lenient)
            val idPrefixes = manifest?.idPrefixes
            if (idPrefixes != null && idPrefixes.isNotEmpty()) {
                val supportsId = idPrefixes.any { id.startsWith(it) }
                if (!supportsId) {
                    Log.d(TAG, "getStreamAddons: ${addon.name} SKIPPED - idPrefixes don't match: $idPrefixes")
                    return@filter false
                }
            }

            // Default: assume addon supports streaming (be lenient for unknown addons)
            Log.d(TAG, "getStreamAddons: ${addon.name} INCLUDED (default)")
            true
        }
    }

    // Timeout for individual addon requests (5 seconds)
    private val ADDON_TIMEOUT_MS = 5000L

    /**
     * Resolve streams for a movie using INSTALLED addons
     * Uses progressive loading - streams appear as each addon responds
     */
    suspend fun resolveMovieStreams(imdbId: String): StreamResult = withContext(Dispatchers.IO) {
        val allStreams = MutableStateFlow<List<StreamSource>>(emptyList())
        val subtitles = mutableListOf<Subtitle>()

        val allAddons = installedAddons.first()
        val streamAddons = getStreamAddons(allAddons, "movie", imdbId)

        Log.d(TAG, "Fetching from ${streamAddons.size} addons for $imdbId")

        extractAndSetDebridFromAddons(streamAddons)

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
                        Log.d(TAG, "Fetching streams from ${addon.name}: $url")
                        val response = streamApi.getAddonStreams(url)
                        Log.d(TAG, "Got ${response.streams?.size ?: 0} streams from ${addon.name}")
                        processStreams(response.streams ?: emptyList(), addon)
                    }
                } catch (e: TimeoutCancellationException) {
                    Log.w(TAG, "Timeout from ${addon.name} after ${ADDON_TIMEOUT_MS}ms")
                    emptyList()
                } catch (e: Exception) {
                    Log.e(TAG, "Error from ${addon.name}: ${e.message}")
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
                    Log.w(TAG, "Subtitle timeout from ${addon.name}")
                    emptyList()
                } catch (e: Exception) {
                    emptyList()
                }
            }
        }

        val streams = streamJobs.awaitAll().flatten()
        subtitles.addAll(subtitleJobs.awaitAll().flatten())

        Log.d(TAG, "Found ${streams.size} streams, ${subtitles.size} subtitles")

        // INSTANT MODE: Check which TorBox streams are cached for instant playback
        // Use shorter timeout for cache check
        val markedStreams = try {
            withTimeout(3000L) {
                markCachedTorBoxStreams(streams)
            }
        } catch (e: TimeoutCancellationException) {
            Log.w(TAG, "TorBox cache check timed out, showing all streams")
            streams
        }

        // Sort: prioritize cached streams, then by quality
        val sortedStreams = markedStreams.sortedWith(
            compareByDescending<StreamSource> { it.isCached }
                .thenByDescending { it.sizeBytes ?: 0L }
                .thenByDescending { getQualityScore(it.quality) }
                .thenByDescending { it.seeders ?: 0 }
        )

        Log.d(TAG, "INSTANT: ${sortedStreams.count { it.isCached }} cached streams prioritized")

        StreamResult(sortedStreams, subtitles)
    }

    /**
     * Filter to only show cached TorBox streams (instant playback only)
     * Non-cached TorBox streams are removed, direct URLs are kept
     */
    private suspend fun markCachedTorBoxStreams(streams: List<StreamSource>): List<StreamSource> {
        // Separate TorBox streams from others
        val torboxStreams = streams.filter { it.url?.contains("/torbox/") == true }
        val otherStreams = streams.filter { it.url?.contains("/torbox/") != true }

        if (torboxStreams.isEmpty()) return streams

        // Extract hashes from TorBox URLs
        val hashToStreams = mutableMapOf<String, MutableList<StreamSource>>()
        torboxStreams.forEach { stream ->
            val hash = extractHashFromTorboxUrl(stream.url ?: "")?.lowercase()
            if (hash != null) {
                hashToStreams.getOrPut(hash) { mutableListOf() }.add(stream)
            }
        }

        if (hashToStreams.isEmpty()) return otherStreams

        Log.d(TAG, "INSTANT: Checking cache for ${hashToStreams.size} TorBox hashes...")

        // Batch check cache status
        val cachedHashes = debridRepository.checkTorBoxCached(hashToStreams.keys.toList())
        Log.d(TAG, "INSTANT: ${cachedHashes.size}/${hashToStreams.size} hashes are cached - ONLY showing cached!")

        // FILTER: Only keep cached TorBox streams
        val cachedTorboxStreams = hashToStreams
            .filterKeys { it in cachedHashes }
            .values
            .flatten()
            .map { it.copy(isCached = true) }

        Log.d(TAG, "INSTANT: Keeping ${cachedTorboxStreams.size} cached streams, ${otherStreams.size} direct streams")

        // Return cached TorBox streams + other streams (direct URLs)
        return cachedTorboxStreams + otherStreams
    }

    /**
     * Process raw streams into StreamSource objects - like NuvioStreaming processStreams
     */
    private fun processStreams(streams: List<StremioStream>, addon: Addon): List<StreamSource> {
        Log.d(TAG, "processStreams: Processing ${streams.size} streams from ${addon.name}")

        // Debug: Log first few streams to see their structure
        streams.take(3).forEachIndexed { index, stream ->
            Log.d(TAG, "processStreams: Stream $index from ${addon.name}:")
            Log.d(TAG, "  name=${stream.name?.take(100)}")
            Log.d(TAG, "  title=${stream.title?.take(100)}")
            Log.d(TAG, "  description=${stream.description?.take(100)}")
            Log.d(TAG, "  behaviorHints.cached=${stream.behaviorHints?.cached}")
            Log.d(TAG, "  behaviorHints.videoSize=${stream.behaviorHints?.videoSize}")
            Log.d(TAG, "  behaviorHints.filename=${stream.behaviorHints?.filename}")
            Log.d(TAG, "  parsed size=${stream.getSize()}")
            Log.d(TAG, "  parsed quality=${stream.getQuality()}")
        }

        val filtered = streams.filter { stream ->
            val hasLink = stream.hasPlayableLink()
            val hasId = stream.title != null || stream.name != null
            if (!hasLink) Log.d(TAG, "processStreams: Stream filtered - no playable link: ${stream.name ?: stream.title}")
            if (!hasId) Log.d(TAG, "processStreams: Stream filtered - no title/name")
            hasLink && hasId
        }

        Log.d(TAG, "processStreams: ${filtered.size} streams passed filter")

        return filtered
            .map { stream ->
                val streamUrl = stream.getStreamUrl() ?: stream.infoHash?.let { hash ->
                    buildMagnetUrl(hash, stream.getTorrentName())
                }

                // Check if this is a direct streaming URL (no debrid needed)
                val isDirectUrl = stream.isDirectStreamingUrl()

                // Check cached status from behavior hints or name indicators
                // AIOStreams uses [TB⚡] to indicate TorBox cached, [RD⚡] for Real-Debrid cached, etc.
                // Note: [TB+] means TorBox configured (NOT cached) - only ⚡ means cached
                val streamName = stream.name ?: ""
                val isCached = stream.behaviorHints?.cached == true ||
                    streamName.contains("⚡")  // Lightning emoji indicates instant/cached

                // Extract embedded subtitles from stream
                val embeddedSubs = stream.subtitles?.mapIndexed { index, sub ->
                    Subtitle(
                        id = sub.id ?: "${addon.id}_${stream.infoHash ?: index}_sub_$index",
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
                    seeders = stream.getSeeders(),
                    url = streamUrl,
                    infoHash = stream.infoHash,
                    fileIdx = stream.fileIdx,
                    isDebrid = !isDirectUrl && (stream.infoHash != null || streamUrl?.contains("debrid") == true),
                    isCached = isCached,
                    behaviorHints = stream.behaviorHints?.let {
                        ModelStreamBehaviorHints(
                            notWebReady = it.notWebReady ?: false,
                            cached = it.cached,
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
        tmdbId: Int? = null
    ): StreamResult = withContext(Dispatchers.IO) {
        val subtitles = mutableListOf<Subtitle>()

        // Check if this is anime
        val isAnime = tmdbId?.let { AnimeMapper.isAnime(it) } ?: false
        val animeQuery = if (isAnime && tmdbId != null) {
            AnimeMapper.getAnimeQuery(tmdbId, imdbId, season, episode)
        } else null

        val seriesId = "$imdbId:$season:$episode"

        val allAddons = installedAddons.first()
        val streamAddons = getStreamAddons(allAddons, "series", imdbId)

        Log.d(TAG, "Fetching from ${streamAddons.size} addons for $seriesId")

        extractAndSetDebridFromAddons(streamAddons)

        // Fetch from all addons in parallel with timeout
        val streamJobs = streamAddons.map { addon ->
            async {
                try {
                    withTimeout(ADDON_TIMEOUT_MS) {
                        val (baseUrl, queryParams) = getAddonBaseUrl(addon.url ?: return@withTimeout emptyList())

                        val contentId = when {
                            isAnime && addon.id == "anime-kitsu" && animeQuery != null -> animeQuery
                            !isAnime && addon.id == "anime-kitsu" -> return@withTimeout emptyList<StreamSource>()
                            else -> seriesId
                        }

                        val url = if (queryParams != null) {
                            "$baseUrl/stream/series/$contentId.json?$queryParams"
                        } else {
                            "$baseUrl/stream/series/$contentId.json"
                        }

                        Log.d(TAG, "Fetching streams from ${addon.name}: $url")
                        val response = streamApi.getAddonStreams(url)
                        Log.d(TAG, "Got ${response.streams?.size ?: 0} streams from ${addon.name}")
                        processStreams(response.streams ?: emptyList(), addon)
                    }
                } catch (e: TimeoutCancellationException) {
                    Log.w(TAG, "Timeout from ${addon.name} after ${ADDON_TIMEOUT_MS}ms")
                    emptyList()
                } catch (e: Exception) {
                    Log.e(TAG, "Error from ${addon.name}: ${e.message}")
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
                    Log.w(TAG, "Subtitle timeout from ${addon.name}")
                    emptyList()
                } catch (e: Exception) {
                    emptyList()
                }
            }
        }

        val streams = streamJobs.awaitAll().flatten()
        subtitles.addAll(subtitleJobs.awaitAll().flatten())

        Log.d(TAG, "Found ${streams.size} streams, ${subtitles.size} subtitles")

        // INSTANT MODE: Check which TorBox streams are cached for instant playback
        // Use shorter timeout for cache check
        val markedStreams = try {
            withTimeout(3000L) {
                markCachedTorBoxStreams(streams)
            }
        } catch (e: TimeoutCancellationException) {
            Log.w(TAG, "TorBox cache check timed out, showing all streams")
            streams
        }

        // Sort: prioritize cached streams, then by quality
        val sortedStreams = markedStreams.sortedWith(
            compareByDescending<StreamSource> { it.isCached }
                .thenByDescending { it.sizeBytes ?: 0L }
                .thenByDescending { getQualityScore(it.quality) }
                .thenByDescending { it.seeders ?: 0 }
        )

        Log.d(TAG, "INSTANT: ${sortedStreams.count { it.isCached }} cached streams prioritized")

        StreamResult(sortedStreams, subtitles)
    }

    /**
     * Resolve a single stream for playback
     */
    suspend fun resolveStreamForPlayback(stream: StreamSource): StreamSource? = withContext(Dispatchers.IO) {
        val url = stream.url ?: return@withContext null

        Log.d(TAG, "Resolving stream: ${stream.source.take(50)}...")

        try {
            when {
                // TorBox proxy URLs
                url.contains("/torbox/") -> {
                    val resolvedUrl = debridRepository.resolveTorBoxUrl(url)
                    if (resolvedUrl != null) {
                        Log.d(TAG, "Resolved TorBox: $resolvedUrl")
                        stream.copy(url = resolvedUrl, isDebrid = true)
                    } else null
                }
                // Real-Debrid proxy URLs
                url.contains("/realdebrid/") || url.contains("/rd/") -> {
                    val actualLink = extractDebridProxyLink(url, "realdebrid") ?: url
                    val resolvedUrl = debridRepository.unrestrictLink(actualLink)
                    if (resolvedUrl != null) {
                        stream.copy(url = resolvedUrl, isDebrid = true)
                    } else null
                }
                // AllDebrid proxy URLs
                url.contains("/alldebrid/") -> {
                    val actualLink = extractDebridProxyLink(url, "alldebrid") ?: url
                    val resolvedUrl = debridRepository.unrestrictLink(actualLink)
                    if (resolvedUrl != null) {
                        stream.copy(url = resolvedUrl, isDebrid = true)
                    } else null
                }
                // Premiumize proxy URLs
                url.contains("/premiumize/") -> {
                    val actualLink = extractDebridProxyLink(url, "premiumize") ?: url
                    val resolvedUrl = debridRepository.unrestrictLink(actualLink)
                    if (resolvedUrl != null) {
                        stream.copy(url = resolvedUrl, isDebrid = true)
                    } else null
                }
                // Debrid-Link proxy URLs
                url.contains("/debridlink/") -> {
                    val actualLink = extractDebridProxyLink(url, "debridlink") ?: url
                    val resolvedUrl = debridRepository.unrestrictLink(actualLink)
                    if (resolvedUrl != null) {
                        stream.copy(url = resolvedUrl, isDebrid = true)
                    } else null
                }
                // Magnet links - need debrid
                url.startsWith("magnet:") -> {
                    val resolvedUrl = debridRepository.unrestrictLink(url)
                    if (resolvedUrl != null) {
                        stream.copy(url = resolvedUrl, isDebrid = true)
                    } else null
                }
                // Direct HTTP URLs - play immediately
                url.startsWith("http://") || url.startsWith("https://") -> {
                    Log.d(TAG, "Direct URL - instant play")
                    stream
                }
                else -> null
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error resolving: ${e.message}")
            null
        }
    }

    /**
     * Resolve URLs to direct streaming URLs using configured debrid service.
     * CACHED-ONLY MODE: Only returns streams that are actually cached and playable.
     * @deprecated Use resolveStreamForPlayback() for on-demand resolution instead
     * - TorBox/debrid proxy URLs (e.g., .../torbox/API_KEY/HASH/0) need resolution
     * - Magnet URLs need debrid resolution
     * - Real direct URLs (like CDN URLs) can play directly
     *
     * Uses TorBox's batch checkcached API for efficient cache checking.
     */
    private suspend fun resolveStreamsWithDebrid(streams: List<StreamSource>): List<StreamSource> = coroutineScope {
        if (streams.isEmpty()) return@coroutineScope streams

        val activeDebrid = debridRepository.getActiveDebridService()
        val cachedStreams = mutableListOf<StreamSource>()

        // Separate streams by type
        val torboxProxyStreams = mutableListOf<StreamSource>() // /torbox/ URLs need resolution
        val directStreams = mutableListOf<StreamSource>()       // Real direct URLs
        val magnetStreams = mutableListOf<StreamSource>()       // Magnet links

        streams.forEach { stream ->
            val url = stream.url ?: return@forEach
            when {
                // TorBox proxy URLs from Torrentio - need resolution via TorBox API
                url.contains("/torbox/") -> {
                    torboxProxyStreams.add(stream)
                }
                // Real-Debrid proxy URLs - need resolution
                url.contains("/realdebrid/") || url.contains("/rd/") -> {
                    magnetStreams.add(stream) // Treat like magnets for now
                }
                // Direct HTTP URLs (CDN, etc.) - can play directly
                url.startsWith("http://") || url.startsWith("https://") -> {
                    directStreams.add(stream)
                }
                // Magnet links - need debrid
                url.startsWith("magnet:") -> {
                    magnetStreams.add(stream)
                }
            }
        }

        Log.d(TAG, "Found ${directStreams.size} direct, ${torboxProxyStreams.size} TorBox, ${magnetStreams.size} magnet streams")

        // Direct streams are always cached/playable
        cachedStreams.addAll(directStreams)

        // Use TorBox's batch checkcached API for efficient cache checking
        if (torboxProxyStreams.isNotEmpty()) {
            Log.d(TAG, "Checking TorBox cache for ${torboxProxyStreams.size} streams using batch API...")

            // Extract hashes from all TorBox proxy URLs
            val streamsByHash = mutableMapOf<String, MutableList<StreamSource>>()
            torboxProxyStreams.forEach { stream ->
                val hash = extractHashFromTorboxUrl(stream.url ?: "")
                if (hash != null) {
                    streamsByHash.getOrPut(hash.lowercase()) { mutableListOf() }.add(stream)
                }
            }

            val allHashes = streamsByHash.keys.toList()
            Log.d(TAG, "Extracted ${allHashes.size} unique hashes from TorBox streams")

            // Batch check which hashes are cached (single API call!)
            val cachedHashes = debridRepository.checkTorBoxCached(allHashes)
            Log.d(TAG, "TorBox: ${cachedHashes.size}/${allHashes.size} hashes are cached")

            // Get cached streams (those whose hash is in the cached set)
            val cachedTorboxStreams = streamsByHash
                .filterKeys { it in cachedHashes }
                .values
                .flatten()

            Log.d(TAG, "Found ${cachedTorboxStreams.size} cached TorBox streams")

            // Now resolve only the cached streams (this will be fast since they're already cached)
            // Limit to top 20 for performance
            val streamsToResolve = cachedTorboxStreams.take(20)

            // Resolve in parallel with small batches to avoid rate limiting
            val batchSize = 5
            val batches = streamsToResolve.chunked(batchSize)

            for ((batchIndex, batch) in batches.withIndex()) {
                // Add small delay between batches
                if (batchIndex > 0) {
                    kotlinx.coroutines.delay(200)
                }

                val jobs = batch.map { stream ->
                    async {
                        val url = stream.url ?: return@async null
                        try {
                            val resolvedUrl = debridRepository.resolveTorBoxUrl(url)
                            if (resolvedUrl != null) {
                                Log.d(TAG, "RESOLVED: ${stream.source.take(40)}...")
                                stream.copy(url = resolvedUrl, isDebrid = true)
                            } else {
                                null
                            }
                        } catch (e: Exception) {
                            Log.e(TAG, "Error resolving TorBox stream: ${e.message}")
                            null
                        }
                    }
                }

                val resolved = jobs.awaitAll()
                cachedStreams.addAll(resolved.filterNotNull())
            }
        }

        // Only resolve magnets if we have a debrid service
        if (magnetStreams.isNotEmpty() && activeDebrid != DebridService.NONE) {
            Log.d(TAG, "Resolving magnet streams with $activeDebrid")

            // Resolve first few magnet streams (top 5) in parallel
            val magnetsToResolve = magnetStreams.take(5)

            val resolvedJobs = magnetsToResolve.map { stream ->
                async {
                    val url = stream.url ?: return@async null
                    try {
                        val resolvedUrl = debridRepository.unrestrictLink(url)
                        if (resolvedUrl != null) {
                            Log.d(TAG, "Resolved magnet: ${stream.source.take(30)}...")
                            stream.copy(url = resolvedUrl, isDebrid = true)
                        } else {
                            Log.w(TAG, "Failed to resolve magnet: ${stream.source.take(30)}...")
                            null
                        }
                    } catch (e: Exception) {
                        Log.e(TAG, "Error resolving magnet", e)
                        null
                    }
                }
            }

            val resolved = resolvedJobs.awaitAll()
            cachedStreams.addAll(resolved.filterNotNull())
        }

        Log.d(TAG, "CACHED-ONLY: ${cachedStreams.size} playable streams found")

        // Sort by quality, size, seeders
        val sortedStreams = cachedStreams.sortedWith(
            compareByDescending<StreamSource> { getQualityScore(it.quality) }
                .thenByDescending { it.sizeBytes ?: 0L }
                .thenByDescending { it.seeders ?: 0 }
        )

        sortedStreams
    }

    /**
     * Extract hash from TorBox proxy URL
     * URL format: https://torrentio.strem.fun/torbox/API_KEY/HASH/FILE_INDEX
     */
    private fun extractHashFromTorboxUrl(url: String): String? {
        val parts = url.split("/")
        val torboxIndex = parts.indexOf("torbox")
        return if (torboxIndex != -1 && parts.size > torboxIndex + 2) {
            parts[torboxIndex + 2]
        } else {
            null
        }
    }

    /**
     * Extract the actual link or magnet from a debrid proxy URL
     * Torrentio URLs format: .../SERVICE/API_KEY/HASH/FILE_INDEX
     * Returns a magnet link constructed from the hash
     */
    private fun extractDebridProxyLink(url: String, service: String): String? {
        val parts = url.split("/")
        val serviceIndex = parts.indexOfFirst { it.equals(service, ignoreCase = true) || it.equals("rd", ignoreCase = true) }

        if (serviceIndex == -1) return null

        // Format: .../service/api_key/hash/file_index
        val hash = parts.getOrNull(serviceIndex + 2) ?: return null

        // Validate it looks like an info hash (40 hex chars for SHA-1)
        if (hash.length >= 32 && hash.all { it.isLetterOrDigit() }) {
            return buildMagnetUrl(hash, "stream")
        }

        return null
    }

    // Real-Debrid functionality has been moved to DebridRepository
    
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

        val regex = Regex("""([\d.]+)\s*(GB|MB|KB|TB)""", RegexOption.IGNORE_CASE)
        val match = regex.find(sizeStr) ?: return 0L

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

    /**
     * Build magnet URL from infoHash (like webapp does)
     */
    private fun buildMagnetUrl(infoHash: String, displayName: String): String {
        val trackers = listOf(
            "udp://tracker.opentrackr.org:1337/announce",
            "udp://open.stealth.si:80/announce",
            "udp://tracker.torrent.eu.org:451/announce"
        )
        val trParams = trackers.joinToString("") { "&tr=${java.net.URLEncoder.encode(it, "UTF-8")}" }
        val encodedName = java.net.URLEncoder.encode(displayName, "UTF-8")
        return "magnet:?xt=urn:btih:$infoHash&dn=$encodedName$trParams"
    }

    /**
     * Extract debrid API keys from addon URLs and set them in DebridRepository
     * Torrentio URLs contain debrid keys like: torbox=<api-key> or realdebrid=<api-key>
     */
    private suspend fun extractAndSetDebridFromAddons(addons: List<Addon>) {
        for (addon in addons) {
            val url = addon.url ?: continue

            // Extract TorBox API key from URL - API keys can be alphanumeric with dashes
            val torboxRegex = """torbox=([a-zA-Z0-9_-]+)""".toRegex(RegexOption.IGNORE_CASE)
            val torboxMatch = torboxRegex.find(url)
            if (torboxMatch != null) {
                val apiKey = torboxMatch.groupValues[1]
                Log.d(TAG, "Found TorBox API key in addon URL: ${apiKey.take(8)}...")
                debridRepository.setTorBoxApiKey(apiKey)
            }

            // Extract Real-Debrid API key from URL (if present)
            val rdRegex = """realdebrid=([a-zA-Z0-9]+)""".toRegex(RegexOption.IGNORE_CASE)
            val rdMatch = rdRegex.find(url)
            if (rdMatch != null) {
                val apiKey = rdMatch.groupValues[1]
                Log.d(TAG, "Found Real-Debrid API key in addon URL: ${apiKey.take(8)}...")
                // Note: RD uses OAuth tokens, not API keys in URL typically
                // But some users may have configured it this way
            }

            // Extract AllDebrid API key from URL
            val adRegex = """alldebrid=([a-zA-Z0-9]+)""".toRegex(RegexOption.IGNORE_CASE)
            val adMatch = adRegex.find(url)
            if (adMatch != null) {
                val apiKey = adMatch.groupValues[1]
                Log.d(TAG, "Found AllDebrid API key in addon URL: ${apiKey.take(8)}...")
                debridRepository.setAllDebridApiKey(apiKey)
            }

            // Extract Premiumize API key from URL
            val pmRegex = """premiumize=([a-zA-Z0-9]+)""".toRegex(RegexOption.IGNORE_CASE)
            val pmMatch = pmRegex.find(url)
            if (pmMatch != null) {
                val apiKey = pmMatch.groupValues[1]
                Log.d(TAG, "Found Premiumize API key in addon URL: ${apiKey.take(8)}...")
                debridRepository.setPremiumizeApiKey(apiKey)
            }

            // Extract Debrid-Link API key from URL
            val dlRegex = """debridlink=([a-zA-Z0-9]+)""".toRegex(RegexOption.IGNORE_CASE)
            val dlMatch = dlRegex.find(url)
            if (dlMatch != null) {
                val apiKey = dlMatch.groupValues[1]
                Log.d(TAG, "Found Debrid-Link API key in addon URL: ${apiKey.take(8)}...")
                debridRepository.setDebridLinkApiKey(apiKey)
            }
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
