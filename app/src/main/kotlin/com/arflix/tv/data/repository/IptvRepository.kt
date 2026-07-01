package com.arflix.tv.data.repository

import android.content.Context
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.arflix.tv.data.model.IptvChannel
import com.arflix.tv.data.model.DrmInfo
import com.arflix.tv.data.model.IptvNowNext
import com.arflix.tv.data.model.IptvProgram
import com.arflix.tv.data.model.IptvSnapshot
import com.arflix.tv.data.model.StreamSource
import com.arflix.tv.R
import com.arflix.tv.util.settingsDataStore
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.withContext
import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException
import kotlin.coroutines.resume
import kotlinx.coroutines.delay
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.Deferred
import com.arflix.tv.data.model.PlaylistGroupKey
import kotlinx.coroutines.launch
import com.arflix.tv.network.OkHttpProvider
import okhttp3.OkHttpClient
import okhttp3.Request
import org.xmlpull.v1.XmlPullParser
import org.xml.sax.Attributes
import org.xml.sax.helpers.DefaultHandler
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.BufferedOutputStream
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.FileInputStream
import java.io.FilterInputStream
import java.io.InputStream
import java.io.InputStreamReader
import java.io.File
import java.nio.charset.StandardCharsets
import java.time.Instant
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.util.Locale
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.TimeUnit
import java.util.zip.GZIPInputStream
import java.util.zip.GZIPOutputStream
import javax.xml.XMLConstants
import javax.inject.Inject
import javax.inject.Singleton
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec
import javax.xml.parsers.SAXParserFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withTimeoutOrNull
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.sync.withPermit
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicInteger
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import java.lang.reflect.Type
import java.security.KeyStore
import java.security.MessageDigest

private object IptvRepoDateRegexes {
    val MINUTE_PATTERN: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd:HH-mm")
    val SECOND_PATTERN: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd:HH-mm-ss")
    val SPACE_SECOND_PATTERN: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    val SPACE_MINUTE_PATTERN: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    val YEAR_PATTERN: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy")
    val MONTH_PATTERN: DateTimeFormatter = DateTimeFormatter.ofPattern("MM")
    val DAY_PATTERN: DateTimeFormatter = DateTimeFormatter.ofPattern("dd")
    val HOUR_PATTERN: DateTimeFormatter = DateTimeFormatter.ofPattern("HH")
    val MIN_PATTERN: DateTimeFormatter = DateTimeFormatter.ofPattern("mm")
    val SEC_PATTERN: DateTimeFormatter = DateTimeFormatter.ofPattern("ss")

    private const val MAX_DYNAMIC_FORMATTERS = 256
    val formatterCache = java.util.concurrent.ConcurrentHashMap<String, DateTimeFormatter>()

    fun formatterFor(pattern: String): DateTimeFormatter {
        formatterCache[pattern]?.let { return it }
        if (formatterCache.size >= MAX_DYNAMIC_FORMATTERS) {
            return DateTimeFormatter.ofPattern(pattern)
        }
        return formatterCache.getOrPut(pattern) { DateTimeFormatter.ofPattern(pattern) }
    }
}

private object IptvIdSentinels {
    const val IMDB_NONE: String = "tt0"
    const val TMDB_NONE: Int = 0

    fun normalizeImdb(id: String?): String {
        val trimmed = id?.trim()?.lowercase(Locale.US).orEmpty()
        if (trimmed.isBlank()) return IMDB_NONE
        if (!trimmed.startsWith("tt")) return IMDB_NONE
        if (trimmed.length < 3) return IMDB_NONE
        return trimmed
    }

    fun normalizeTmdb(id: Int?): Int = id?.takeIf { it > 0 } ?: TMDB_NONE

    fun normalizeTmdb(id: String?): Int {
        val raw = id?.trim().orEmpty()
        if (raw.isBlank()) return TMDB_NONE
        return raw.toIntOrNull()?.takeIf { it > 0 } ?: TMDB_NONE
    }

    fun isReal(imdb: String): Boolean = imdb.isNotBlank() && imdb != IMDB_NONE
    fun isReal(tmdb: Int): Boolean = tmdb > 0
}

private const val LargeIptvListChannelCount = 10_000

data class IptvConfig(
    val m3uUrl: String = "",
    val epgUrl: String = "",
    val playlists: List<IptvPlaylistEntry> = emptyList(),
    val stalkerPortalUrl: String = "",
    val stalkerMacAddress: String = ""
)

data class IptvPlaylistEntry(
    val id: String,
    val name: String,
    val m3uUrl: String,
    val epgUrl: String = "",
    val enabled: Boolean = true,
    val epgUrls: List<String> = emptyList()
)

data class IptvLoadProgress(
    val message: String,
    val percent: Int? = null
)

data class IptvCloudProfileState(
    val m3uUrl: String = "",
    val epgUrl: String = "",
    val favoriteGroups: List<String> = emptyList(),
    val favoriteChannels: List<String> = emptyList(),
    val hiddenGroups: List<String> = emptyList(),
    val groupOrder: List<String> = emptyList(),
    val playlists: List<IptvPlaylistEntry> = emptyList(),
    val tvSession: IptvTvSessionState = IptvTvSessionState()
)

data class IptvTvSessionState(
    val lastChannelId: String = "",
    val lastGroupName: String = "",
    val lastFocusedZone: String = "GUIDE",
    val lastOpenedAt: Long = 0L,
    val recentChannelIds: List<String> = emptyList()
)

@Singleton
class IptvRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val okHttpClient: OkHttpClient,
    private val profileManager: ProfileManager,
    private val invalidationBus: CloudSyncInvalidationBus
) {
    private val gson = Gson()
    private val loadMutex = Mutex()
    private val xtreamDataMutex = Mutex()
    private val xtreamSeriesEpisodeCacheMutex = Mutex()
    private val xtreamSeriesEpisodeInFlightMutex = Mutex()
    private val epgIndex by lazy { IptvEpgIndex(context) }
    private val channelStore by lazy { IptvChannelStore(context) }
    private val maxSeriesEpisodeCacheEntries = 8

    @Volatile
    private var cachedChannels: List<IptvChannel> = emptyList()
    @Volatile
    private var cachedChannelsLookupSource: List<IptvChannel>? = null
    @Volatile
    private var cachedChannelsById: Map<String, IptvChannel> = emptyMap()
    @Volatile
    private var cachedGroupedChannels: Map<String, List<IptvChannel>> = emptyMap()
    @Volatile var cachedStalkerApi: com.arflix.tv.data.api.StalkerApi? = null

    @Volatile
    private var groupOrderLocallyDirty = false

    fun isGroupOrderLocallyDirty(): Boolean = groupOrderLocallyDirty

    @Volatile
    private var cachedNowNext: ConcurrentHashMap<String, IptvNowNext> = ConcurrentHashMap()
    private val emptyShortEpgCooldownUntil = ConcurrentHashMap<String, Long>()
    private val visibleXmlEpgCooldownUntil = ConcurrentHashMap<String, Long>()

    private val guideKeyCandidatesCache = java.util.Collections.synchronizedMap(
        object : java.util.LinkedHashMap<String, Set<String>>(512, 0.75f, true) {
            override fun removeEldestEntry(eldest: Map.Entry<String, Set<String>>?): Boolean = size > 4096
        }
    )

    internal class EpgNotModifiedException : Exception("EPG content has not modified (HTTP 304)")

    private fun getEpgHttpCachePrefs() = context.getSharedPreferences("arvio_epg_http_cache", Context.MODE_PRIVATE)

    private fun getEpgCachedEtag(url: String): String? {
        return getEpgHttpCachePrefs().getString("${url}_etag", null)
    }

    private fun getEpgCachedLastModified(url: String): String? {
        return getEpgHttpCachePrefs().getString("${url}_last_modified", null)
    }

    private fun saveEpgHttpCacheHeaders(url: String, etag: String?, lastModified: String?) {
        getEpgHttpCachePrefs().edit().apply {
            if (etag != null) putString("${url}_etag", etag) else remove("${url}_etag")
            if (lastModified != null) putString("${url}_last_modified", lastModified) else remove("${url}_last_modified")
            apply()
        }
    }

    @Volatile
    private var cachedPlaylistAt: Long = 0L

    @Volatile
    private var cachedEpgAt: Long = 0L

    private val discoveredM3uEpgUrls = java.util.concurrent.CopyOnWriteArraySet<String>()
    @Volatile
    private var cacheOwnerProfileId: String? = null
    @Volatile
    private var cacheOwnerConfigSig: String? = null
    @Volatile
    private var currentEpgIndexKey: String = ""
    @Volatile
    private var xtreamVodCacheKey: String? = null
    @Volatile
    private var xtreamVodLoadedAtMs: Long = 0L
    private val startupPrefetchInFlight = AtomicBoolean(false)

    private fun buildGroupedChannels(channels: List<IptvChannel>): Map<String, List<IptvChannel>> =
        channels.groupBy { it.group.ifBlank { "Uncategorized" } }

    private fun cachedChannelLookup(): Map<String, IptvChannel> {
        val source = cachedChannels
        val current = cachedChannelsById
        if (cachedChannelsLookupSource === source && current.size == source.size) {
            return current
        }
        val rebuilt = source.associateBy { it.id }
        cachedChannelsLookupSource = source
        cachedChannelsById = rebuilt
        return rebuilt
    }

    private fun channelsForEpgRefresh(channelIds: Collection<String>): List<IptvChannel> {
        if (channelIds.isEmpty()) return emptyList()
        val lookup = cachedChannelLookup()
        val channels = ArrayList<IptvChannel>(channelIds.size)
        val missing = LinkedHashSet<String>()
        channelIds.forEach { id ->
            val trimmed = id.trim()
            if (trimmed.isBlank()) return@forEach
            val channel = lookup[trimmed]
            if (channel != null) {
                channels += channel
            } else {
                missing += trimmed
            }
        }
        if (missing.isNotEmpty()) {
            val indexed = runCatching { channelStore.getByIds(currentEpgIndexKey, missing) }
                .onFailure { error -> System.err.println("[EPG-Refresh] paged channel lookup failed: ${error.message}") }
                .getOrDefault(emptyList())
            if (indexed.isNotEmpty()) {
                channels += indexed
                System.err.println("[EPG-Refresh] hydrated ${indexed.size}/${missing.size} channels from paged store")
            }
        }
        return channels.distinctBy { it.id }
    }

    private data class ScopedEpgCandidate(
        val url: String,
        val playlistId: String? = null
    )
    private fun hasAnyConfiguredSource(config: IptvConfig): Boolean =
        config.m3uUrl.isNotBlank() ||
            config.stalkerPortalUrl.isNotBlank() ||
            config.playlists.any { it.enabled && it.m3uUrl.isNotBlank() }
    private fun activePlaylists(config: IptvConfig): List<IptvPlaylistEntry> =
        config.playlists.filter { it.enabled }.ifEmpty {
            if (config.m3uUrl.isNotBlank()) {
                val epgUrls = normalizeEpgInputs(config.epgUrl)
                listOf(
                    IptvPlaylistEntry(
                        "list_1",
                        "List 1",
                        config.m3uUrl,
                        epgUrls.firstOrNull().orEmpty(),
                        enabled = true,
                        epgUrls = epgUrls
                    )
                )
            } else {
                emptyList()
            }
        }
    @Volatile
    private var xtreamSeriesLoadedAtMs: Long = 0L
    @Volatile
    private var cachedXtreamVodStreams: List<XtreamVodStream> = emptyList()
    @Volatile
    private var cachedXtreamSeries: List<XtreamSeriesItem> = emptyList()
    @Volatile
    private var cachedXtreamSeriesEpisodes: Map<Int, List<XtreamSeriesEpisode>> = emptyMap()
    @Volatile
    private var xtreamSeriesEpisodeInFlight: Map<Int, Deferred<List<XtreamSeriesEpisode>>> = emptyMap()
    private val seriesResolver by lazy { IptvSeriesResolverService() }

    private val staleAfterMs = 24 * 60 * 60_000L
    private val playlistCacheMs = staleAfterMs
    private val epgCacheMs = staleAfterMs
    private val epgEmptyRetryMs = 30_000L
    private val epgUpcomingProgramLimit = 96
    private val epgRecentProgramLimit = 2
    private val xmlTvPastWindowMs = 48L * 60L * 60_000L
    private val xmlTvFutureWindowMs = 72L * 60L * 60_000L
    private val catchupGuideHistoryWindowMs = 48L * 60L * 60_000L
    private val indexedGuideFutureWarmMs = 6L * 60L * 60_000L
    private val completeEpgCoverageTarget = 0.98f
    private val xtreamShortEpgLimit = 24
    private val xtreamVisibleShortEpgLimit = 96
    private val startupShortEpgChannelLimit = 1200
    private val fullCatchupHistoryChannelLimit = 4
    private val xtreamShortEpgBatchSize = 1024
    private val xtreamShortEpgConcurrency = 64
    private val cacheUpcomingProgramLimit = 48
    private val cacheRecentProgramLimit = 1
    private val cacheCatchupRecentProgramLimit = 96
    private val catchupRecentProgramLimit = 1000
    private val catchupProbeCandidateLimit = 40
    private val xtreamVodCacheMs = 6 * 60 * 60_000L
    private val iptvHttpClient: OkHttpClient by lazy {
        // Used for full playlist/EPG loading – generous timeouts for large
        // Xtream EPG feeds. TX-4K serves a ~100 MB XMLTV dump so the read
        // and call timeouts need to be minutes, not seconds.
        okHttpClient.newBuilder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(180, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .callTimeout(300, TimeUnit.SECONDS)
            .build()
    }
    private val xtreamLookupHttpClient: OkHttpClient by lazy {
        // Fast-fail client for VOD/source lookups - must be quick for instant playback
        okHttpClient.newBuilder()
            .connectTimeout(6, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(6, TimeUnit.SECONDS)
            .callTimeout(12, TimeUnit.SECONDS)
            .build()
    }
    private val xtreamGuideHttpClient: OkHttpClient by lazy {
        okHttpClient.newBuilder()
            .connectTimeout(3, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .writeTimeout(3, TimeUnit.SECONDS)
            .callTimeout(6, TimeUnit.SECONDS)
            .build()
    }
    private val xtreamCatchupGuideHttpClient: OkHttpClient by lazy {
        // Full catchup history (`get_simple_data_table`) is much larger than
        // short now/next EPG. Keep short EPG snappy, but give catchup history
        // enough time on slower TV boxes and large providers.
        okHttpClient.newBuilder()
            .connectTimeout(8, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(8, TimeUnit.SECONDS)
            .callTimeout(45, TimeUnit.SECONDS)
            .build()
    }
    private val iptvCatalogHttpClient: OkHttpClient by lazy {
        // Live catalog payloads can be very large (50k+ streams), so keep them
        // below XMLTV timeouts but long enough to finish on TV WiFi.
        okHttpClient.newBuilder()
            .connectTimeout(12, TimeUnit.SECONDS)
            .readTimeout(90, TimeUnit.SECONDS)
            .writeTimeout(12, TimeUnit.SECONDS)
            .callTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    private data class IptvCachePayload(
        val channels: List<IptvChannel> = emptyList(),
        val nowNext: Map<String, IptvNowNext> = emptyMap(),
        val loadedAtEpochMs: Long = 0L,
        val configSignature: String = "",
        val sourceSignature: String = "",
        val discoveredEpgUrls: List<String> = emptyList()
    )

    private data class IptvChannelCachePayload(
        val channels: List<IptvChannel> = emptyList(),
        val loadedAtEpochMs: Long = 0L,
        val configSignature: String = "",
        val sourceSignature: String = "",
        val discoveredEpgUrls: List<String> = emptyList()
    )

    fun observeConfig(): Flow<IptvConfig> =
        profileManager.activeProfileId.combine(context.settingsDataStore.data) { _, prefs ->
            val playlists = decodePlaylists(prefs[playlistsKey()].orEmpty())
            val primary = playlists.firstOrNull()
            val legacyM3uUrl = normalizeStoredIptvUrl(decryptConfigValue(prefs[m3uUrlKey()].orEmpty()))
            val legacyEpgUrls = normalizeStoredEpgInputs(decryptConfigValue(prefs[epgUrlKey()].orEmpty()))
            IptvConfig(
                m3uUrl = primary?.m3uUrl ?: legacyM3uUrl,
                epgUrl = primary?.epgUrl ?: legacyEpgUrls.firstOrNull().orEmpty(),
                playlists = playlists,
                stalkerPortalUrl = decryptConfigValue(prefs[stalkerPortalUrlKey()].orEmpty()),
                stalkerMacAddress = prefs[stalkerMacAddressKey()].orEmpty()
            )
        }

    fun observeFavoriteGroups(): Flow<List<String>> =
        profileManager.activeProfileId.combine(context.settingsDataStore.data) { _, prefs ->
            decodeFavoriteGroups(prefs)
        }

    fun observeFavoriteChannels(): Flow<List<String>> =
        profileManager.activeProfileId.combine(context.settingsDataStore.data) { _, prefs ->
            decodeFavoriteChannels(prefs)
        }

    fun observeHiddenGroups(): Flow<List<String>> =
        profileManager.activeProfileId.combine(context.settingsDataStore.data) { _, prefs ->
            decodeHiddenGroups(prefs)
        }

    fun observeGroupOrder(): Flow<List<String>> =
        profileManager.activeProfileId.combine(context.settingsDataStore.data) { _, prefs ->
            decodeGroupOrder(prefs)
        }

    fun observeTvSessionState(): Flow<IptvTvSessionState> =
        profileManager.activeProfileId.combine(context.settingsDataStore.data) { _, prefs ->
            decodeTvSessionState(prefs)
        }

    suspend fun saveTvSessionState(state: IptvTvSessionState) {
        context.settingsDataStore.edit { prefs ->
            if (state == IptvTvSessionState()) {
                prefs.remove(tvSessionKey())
            } else {
                prefs[tvSessionKey()] = gson.toJson(
                    state.copy(
                        lastChannelId = state.lastChannelId.trim(),
                        lastGroupName = state.lastGroupName.trim(),
                        lastFocusedZone = state.lastFocusedZone.trim().ifBlank { "GUIDE" },
                        recentChannelIds = state.recentChannelIds
                            .map { it.trim() }
                            .filter { it.isNotBlank() }
                            .distinct()
                            .takeLast(40)
                    )
                )
            }
        }
        invalidationBus.markDirty(CloudSyncScope.IPTV, profileManager.getProfileIdSync(), "save tv session")
    }

    suspend fun saveConfig(m3uUrl: String, epgUrl: String) {
        val normalizedM3u = normalizeStoredIptvUrl(m3uUrl)
        val normalizedEpgUrls = normalizeStoredEpgInputs(epgUrl)
        val normalizedEpg = normalizedEpgUrls.firstOrNull().orEmpty()
        val primary = if (normalizedM3u.isNotBlank()) listOf(
            IptvPlaylistEntry(
                id = "list_1",
                name = "List 1",
                m3uUrl = normalizedM3u,
                epgUrl = normalizedEpg,
                epgUrls = normalizedEpgUrls
            )
        ) else emptyList()
        context.settingsDataStore.edit { prefs ->
            prefs[m3uUrlKey()] = encryptConfigValue(normalizedM3u)
            prefs[epgUrlKey()] = encryptConfigValue(normalizedEpg)
            prefs[playlistsKey()] = gson.toJson(primary)
        }
        invalidateCache()
        invalidationBus.markDirty(CloudSyncScope.IPTV, profileManager.getProfileIdSync(), "save iptv config")
    }

    suspend fun savePlaylists(playlists: List<IptvPlaylistEntry>) {
        val normalized = playlists.mapIndexed { index, item ->
            normalizePlaylistEntry(item, index)
        }.filterNotNull().take(3)

        context.settingsDataStore.edit { prefs ->
            prefs[playlistsKey()] = gson.toJson(normalized)
            val primary = normalized.firstOrNull()
            prefs[m3uUrlKey()] = encryptConfigValue(primary?.m3uUrl.orEmpty())
            prefs[epgUrlKey()] = encryptConfigValue(primary?.epgUrl.orEmpty())
        }
        invalidateCache()
        invalidationBus.markDirty(CloudSyncScope.IPTV, profileManager.getProfileIdSync(), "save iptv playlists")
    }

    suspend fun saveStalkerConfig(portalUrl: String, macAddress: String) {
        val normalizedUrl = portalUrl.trim().trimEnd('/')
        val normalizedMac = macAddress.trim().uppercase().let { mac ->
            if (mac.isNotEmpty() && !mac.startsWith("00:1A:79:")) mac else mac
        }
        context.settingsDataStore.edit { prefs ->
            prefs[stalkerPortalUrlKey()] = encryptConfigValue(normalizedUrl)
            prefs[stalkerMacAddressKey()] = normalizedMac
        }
        invalidateCache()
        invalidationBus.markDirty(CloudSyncScope.IPTV, profileManager.getProfileIdSync(), "save stalker config")
    }

    /**
     * Accept common Xtream Codes inputs and convert to a canonical M3U URL.
     *
     * Supported inputs:
     * - Full m3u/get.php URL: https://host/get.php?username=U&password=P&type=m3u_plus&output=ts
     * - Space-separated: https://host:port U P
     * - Line-separated: host\nuser\npass
     * - Prefix forms: xtream://host user pass (also xstream://)
     */
    private fun normalizeIptvInput(raw: String): String {
        val trimmed = raw.trim()
        if (trimmed.isBlank()) return ""

        // Handle explicit Xtream triplets first (works for hosts with or without scheme).
        extractXtreamTriplet(trimmed)?.let { (host, user, pass) ->
            normalizeXtreamHost(host)?.let { base -> return buildXtreamM3uUrl(base, user, pass) }
        }

        // Already a URL.
        if (trimmed.contains("://")) {
            // If this is an Xtream get.php URL, normalize type/output to a sensible default.
            val parsed = trimmed.toHttpUrlOrNull()
            if (parsed != null && (
                    parsed.encodedPath.endsWith("/get.php") ||
                        parsed.encodedPath.endsWith("/player_api.php")
                    )
            ) {
                extractXtreamCredentialsFromUrl(parsed)?.let { (username, password) ->
                    val base = parsed.toXtreamBaseUrl()
                    return buildXtreamM3uUrl(base, username, password)
                }
            }
            return trimmed
        }

        // Multi-line: host\nuser\npass.
        val partsByLine = trimmed
            .split('\n', '\r')
            .map { it.trim() }
            .filter { it.isNotBlank() }
        if (partsByLine.size >= 3) {
            val host = partsByLine[0]
            val user = partsByLine[1]
            val pass = partsByLine[2]
            normalizeXtreamHost(host)?.let { base -> return buildXtreamM3uUrl(base, user, pass) }
        }

        // Space-separated: host user pass.
        val partsBySpace = trimmed
            .split(MULTI_SPACE_REGEX)
            .map { it.trim() }
            .filter { it.isNotBlank() }
        if (partsBySpace.size >= 3) {
            val host = partsBySpace[0]
            val user = partsBySpace[1]
            val pass = partsBySpace[2]
            normalizeXtreamHost(host)?.let { base -> return buildXtreamM3uUrl(base, user, pass) }
        }

        return trimmed
    }

    private fun normalizeStoredIptvUrl(raw: String): String {
        val trimmed = raw.trim()
        if (trimmed.isBlank()) return ""
        val decoded = decodeLegacyBase64Url(trimmed)
        return normalizeIptvInput(decoded ?: trimmed)
    }

    /**
     * Accept Xtream credentials in the EPG field too.
     *
     * Supported:
     * - Full xmltv.php URL
     * - Full get.php URL (auto-converts to xmltv.php)
     * - host user pass (space-separated)
     * - host\\nuser\\npass (line-separated)
     * - xtream://host user pass
     */
    private fun normalizeEpgInput(raw: String): String {
        val trimmed = raw.trim()
        if (trimmed.isBlank()) return ""

        // Handle explicit Xtream triplets first (works for hosts with or without scheme).
        extractXtreamTriplet(trimmed)?.let { (host, user, pass) ->
            normalizeXtreamHost(host)?.let { base -> return buildXtreamEpgUrl(base, user, pass) }
        }

        if (trimmed.contains("://")) {
            val parsed = trimmed.toHttpUrlOrNull()
            if (parsed != null) {
                val isXtreamPath = parsed.encodedPath.endsWith("/xmltv.php") ||
                    parsed.encodedPath.endsWith("/get.php") ||
                    parsed.encodedPath.endsWith("/player_api.php")
                if (isXtreamPath) {
                    extractXtreamCredentialsFromUrl(parsed)?.let { (username, password) ->
                        val base = parsed.toXtreamBaseUrl()
                        return buildXtreamEpgUrl(base, username, password)
                    }
                }
            }
            return trimmed
        }

        val partsByLine = trimmed
            .split('\n', '\r')
            .map { it.trim() }
            .filter { it.isNotBlank() }
        if (partsByLine.size >= 3) {
            val host = partsByLine[0]
            val user = partsByLine[1]
            val pass = partsByLine[2]
            normalizeXtreamHost(host)?.let { base -> return buildXtreamEpgUrl(base, user, pass) }
        }

        val partsBySpace = trimmed
            .split(MULTI_SPACE_REGEX)
            .map { it.trim() }
            .filter { it.isNotBlank() }
        if (partsBySpace.size >= 3) {
            val host = partsBySpace[0]
            val user = partsBySpace[1]
            val pass = partsBySpace[2]
            normalizeXtreamHost(host)?.let { base -> return buildXtreamEpgUrl(base, user, pass) }
        }

        return trimmed
    }

    private fun normalizeStoredEpgInputs(raw: String): List<String> {
        val trimmed = raw.trim()
        if (trimmed.isBlank()) return emptyList()
        val decoded = decodeLegacyBase64Url(trimmed)
        return normalizeEpgInputs(decoded ?: trimmed)
    }

    private fun normalizeEpgInputs(raw: String): List<String> {
        val trimmed = raw.trim()
        if (trimmed.isBlank()) return emptyList()

        extractXtreamTriplet(trimmed)?.let { (host, user, pass) ->
            normalizeXtreamHost(host)?.let { base ->
                return listOf(buildXtreamEpgUrl(base, user, pass))
            }
        }

        val urls = HTTP_URL_REGEX.findAll(trimmed)
            .map { it.value.trim() }
            .filter { it.isNotBlank() }
            .toList()
        if (urls.size > 1) {
            return urls.map { normalizeEpgInput(it) }
                .filter { it.isNotBlank() }
                .distinct()
        }

        val parts = trimmed
            .split('\n', '\r', ',', ';', '|')
            .map { it.trim() }
            .filter { it.isNotBlank() }
        if (parts.size > 1) {
            return parts.flatMap { normalizeEpgInputs(it) }
                .filter { it.isNotBlank() }
                .distinct()
        }

        return listOf(normalizeEpgInput(trimmed))
            .filter { it.isNotBlank() }
            .distinct()
    }

    private fun normalizePlaylistEpgUrls(playlist: IptvPlaylistEntry): List<String> {
        val epgUrl = runCatching { playlist.epgUrl }.getOrNull().orEmpty()
        val epgUrls = runCatching { playlist.epgUrls }.getOrNull().orEmpty()
        return buildList {
            add(epgUrl)
            addAll(epgUrls)
        }
            .flatMap { normalizeStoredEpgInputs(it) }
            .filter { it.isNotBlank() }
            .distinct()
    }

    private fun normalizePlaylistEntry(playlist: IptvPlaylistEntry, index: Int): IptvPlaylistEntry? {
        val m3uUrl = normalizeStoredIptvUrl(runCatching { playlist.m3uUrl }.getOrNull().orEmpty())
        if (m3uUrl.isBlank()) return null
        val epgUrls = normalizePlaylistEpgUrls(playlist)
        return IptvPlaylistEntry(
            id = runCatching { playlist.id }.getOrNull().orEmpty().trim().ifBlank { "list_${index + 1}" },
            name = runCatching { playlist.name }.getOrNull().orEmpty().trim().ifBlank { "List ${index + 1}" },
            m3uUrl = m3uUrl,
            epgUrl = epgUrls.firstOrNull().orEmpty(),
            enabled = runCatching { playlist.enabled }.getOrDefault(true),
            epgUrls = epgUrls
        )
    }

    private fun decodeLegacyBase64Url(value: String): String? {
        val trimmed = value.trim()
        if (trimmed.length < 12 || trimmed.contains("://") || trimmed.any { it.isWhitespace() }) {
            return null
        }
        return listOf(Base64.DEFAULT, Base64.URL_SAFE or Base64.NO_WRAP)
            .mapNotNull { flags ->
                runCatching { String(Base64.decode(trimmed, flags), StandardCharsets.UTF_8).trim() }.getOrNull()
            }
            .firstOrNull { decoded ->
                decoded.startsWith("http://", ignoreCase = true) ||
                    decoded.startsWith("https://", ignoreCase = true)
            }
    }

    private fun normalizedIptvHttpUrlOrNull(raw: String): okhttp3.HttpUrl? {
        val normalized = normalizeStoredIptvUrl(raw)
        val parsed = normalized.toHttpUrlOrNull() ?: return null
        return parsed.takeIf { it.scheme == "http" || it.scheme == "https" }
    }

    private fun normalizedEpgHttpUrlOrNull(raw: String): okhttp3.HttpUrl? {
        val normalized = normalizeStoredEpgInputs(raw).firstOrNull().orEmpty()
        val parsed = normalized.toHttpUrlOrNull() ?: return null
        return parsed.takeIf { it.scheme == "http" || it.scheme == "https" }
    }

    private fun validatedIptvHttpUrl(raw: String, label: String): okhttp3.HttpUrl =
        normalizedIptvHttpUrlOrNull(raw)
            ?: throw IOException("$label is not a valid http(s) URL.")

    private fun validatedEpgHttpUrl(raw: String, label: String): okhttp3.HttpUrl =
        normalizedEpgHttpUrlOrNull(raw)
            ?: throw IOException("$label is not a valid http(s) URL.")

    private fun IptvPlaylistEntry.allEpgUrls(): List<String> {
        return buildList {
            add(epgUrl)
            addAll(epgUrls.orEmpty())
        }
            .map { it.trim() }
            .filter { it.isNotBlank() }
            .distinct()
    }

    private fun extractXtreamCredentialsFromUrl(parsed: okhttp3.HttpUrl): Pair<String, String>? {
        val username = parsed.queryParameter("username")?.trim()?.ifBlank { null }
            ?: parsed.queryParameter("user")?.trim()?.ifBlank { null }
            ?: parsed.queryParameter("uname")?.trim()?.ifBlank { null }
            ?: ""
        val password = parsed.queryParameter("password")?.trim()?.ifBlank { null }
            ?: parsed.queryParameter("pass")?.trim()?.ifBlank { null }
            ?: parsed.queryParameter("pwd")?.trim()?.ifBlank { null }
            ?: ""
        if (username.isNotBlank() && password.isNotBlank()) {
            return Pair(username, password)
        }
        return null
    }

    private data class XtreamTriplet(
        val host: String,
        val username: String,
        val password: String
    )

    private fun extractXtreamTriplet(raw: String): XtreamTriplet? {
        // Multi-line: host\nuser\npass.
        val partsByLine = raw
            .split('\n', '\r')
            .asSequence()
            .map { it.trim() }
            .filter { it.isNotBlank() }
            .take(3)
            .toList()
        if (partsByLine.size == 3) {
            return XtreamTriplet(
                host = partsByLine[0],
                username = partsByLine[1],
                password = partsByLine[2]
            )
        }

        // Space-separated: host user pass.
        val partsBySpace = raw
            .split(MULTI_SPACE_REGEX)
            .asSequence()
            .map { it.trim() }
            .filter { it.isNotBlank() }
            .take(3)
            .toList()
        if (partsBySpace.size == 3) {
            return XtreamTriplet(
                host = partsBySpace[0],
                username = partsBySpace[1],
                password = partsBySpace[2]
            )
        }

        return null
    }

    private fun okhttp3.HttpUrl.toXtreamBaseUrl(): String {
        val raw = toString().substringBefore('?').trimEnd('/')
        return raw
            .removeSuffix("/get.php")
            .removeSuffix("/xmltv.php")
            .removeSuffix("/player_api.php")
            .trimEnd('/')
    }

    private fun normalizeXtreamHost(host: String): String? {
        val h = host.trim().removeSuffix("/")
        if (h.isBlank()) return null

        val cleaned = h
            .removePrefix("xtream://")
            .removePrefix("xstream://")
            .removePrefix("xtreamcodes://")
            .removePrefix("xc://")
            .let {
                when {
                    it.startsWith("http:/", ignoreCase = true) && !it.startsWith("http://", ignoreCase = true) ->
                        "http://${it.removePrefix("http:/").removePrefix("/")}"
                    it.startsWith("https:/", ignoreCase = true) && !it.startsWith("https://", ignoreCase = true) ->
                        "https://${it.removePrefix("https:/").removePrefix("/")}"
                    else -> it
                }
            }

        // Add scheme if missing.
        return if (cleaned.startsWith("http://", true) || cleaned.startsWith("https://", true)) {
            cleaned.removeSuffix("/")
        } else {
            // Default to http (most providers use http).
            "http://${cleaned.removeSuffix("/")}"
        }
    }

    private fun buildXtreamM3uUrl(baseUrl: String, username: String, password: String): String {
        val safeBase = baseUrl.trim().trimEnd('/')
        val u = username.trim()
        val p = password.trim()
        return "$safeBase/get.php?username=$u&password=$p&type=m3u_plus&output=ts"
    }

    private fun buildXtreamEpgUrl(baseUrl: String, username: String, password: String): String {
        val safeBase = baseUrl.trim().trimEnd('/')
        val u = username.trim()
        val p = password.trim()
        return "$safeBase/xmltv.php?username=$u&password=$p"
    }

    fun getCatchupUrl(channel: IptvChannel, program: IptvProgram): String {
        return getCatchupUrlCandidates(channel, program).firstOrNull() ?: channel.streamUrl
    }

    suspend fun resolvePlayableCatchupUrl(
        channel: IptvChannel,
        program: IptvProgram,
        startAttempt: Int = 0
    ): String {
        val candidates = getCatchupUrlCandidates(channel, program)
        if (candidates.isEmpty()) return channel.streamUrl
        val safeAttempt = startAttempt.coerceAtLeast(0)
        val ordered = candidates.drop(safeAttempt) + candidates.take(safeAttempt)
        return withContext(Dispatchers.IO) {
            ordered.take(catchupProbeCandidateLimit).forEach { candidate ->
                val probe = probePlaybackUrl(candidate, channel.requestHeaders)
                if (probe != null && probe.isPlayable) {
                    System.err.println(
                        "[IPTV-Catchup] selected " +
                            "${if (candidate == ordered.firstOrNull()) "primary" else "fallback"} " +
                            "status=${probe.statusCode} url=${redactIptvUrl(candidate)}"
                    )
                    return@withContext candidate
                }
                if (probe != null) {
                    System.err.println(
                        "[IPTV-Catchup] rejected status=${probe.statusCode} reason=${probe.reason} " +
                            "url=${redactIptvUrl(candidate)}"
                    )
                }
            }
            throw IOException(context.getString(R.string.iptv_no_catchup))
        }
    }

    fun getCatchupUrlCandidates(channel: IptvChannel, program: IptvProgram): List<String> {
        val startUnix = program.startUtcMillis / 1000L
        val endUnix = program.endUtcMillis / 1000L
        val nowUnix = System.currentTimeMillis() / 1000L
        val durationMs = (program.endUtcMillis - program.startUtcMillis).coerceAtLeast(1L)
        val durationMin = ((durationMs + 59_999L) / 60_000L).coerceAtLeast(1L)
        val creds = resolveXtreamCredentials(channel.streamUrl)
        val streamId = channel.xtreamStreamId ?: resolveXtreamStreamId(channel)
        val xtreamCandidates = if (creds != null && streamId != null) {
            buildXtreamCatchupCandidates(creds, streamId, program, durationMin)
        } else {
            emptyList()
        }

        val resolvedType = channel.catchupType?.trim()?.lowercase(Locale.US)?.takeIf { it.isNotBlank() }
            ?: if (xtreamCandidates.isNotEmpty()) "xtream" else "default"

        val sourceTemplate = channel.catchupSource?.takeIf { it.isNotBlank() }
        val serverStartMs = creds?.let { program.startUtcMillis + getServerOffset(it) } ?: program.startUtcMillis
        val candidates = when (resolvedType) {
            "xtream", "xc", "xciptv", "timeshift" -> {
                buildList {
                    sourceTemplate?.let {
                        add(applyCatchupSourceTemplate(channel, it, program, serverStartMs, startUnix, endUnix, nowUnix, durationMin, streamId))
                    }
                    addAll(xtreamCandidates)
                }
            }
            "flussonic", "ts" -> {
                val connector = if (channel.streamUrl.contains("?")) "&" else "?"
                buildList {
                    sourceTemplate?.let {
                        add(applyCatchupSourceTemplate(channel, it, program, program.startUtcMillis, startUnix, endUnix, nowUnix, durationMin, streamId))
                    }
                    add("${channel.streamUrl}${connector}utc=$startUnix")
                    addAll(xtreamCandidates)
                }
            }
            "append", "shift" -> {
                val connector = if (channel.streamUrl.contains("?")) "&" else "?"
                buildList {
                    sourceTemplate?.let {
                        add(applyCatchupSourceTemplate(channel, it, program, program.startUtcMillis, startUnix, endUnix, nowUnix, durationMin, streamId))
                    }
                    add("${channel.streamUrl}${connector}utc=$startUnix&lutc=$nowUnix")
                    addAll(xtreamCandidates)
                }
            }
            "default", "source" -> {
                buildList {
                    sourceTemplate?.let {
                        add(applyCatchupSourceTemplate(channel, it, program, serverStartMs, startUnix, endUnix, nowUnix, durationMin, streamId))
                    }
                    addAll(xtreamCandidates)
                    if (isEmpty()) add(channel.streamUrl)
                }
            }
            else -> {
                // If catchup-source is present but type is unknown, try placeholder replacement anyway
                buildList {
                    sourceTemplate?.let {
                        add(applyCatchupSourceTemplate(channel, it, program, serverStartMs, startUnix, endUnix, nowUnix, durationMin, streamId))
                    }
                    addAll(xtreamCandidates)
                    if (isEmpty()) add(channel.streamUrl)
                }
            }
        }
        return candidates
            .asSequence()
            .map { it.trim() }
            .filter { it.isNotBlank() }
            .distinct()
            .toList()
    }

    private fun buildXtreamCatchupCandidates(
        creds: XtreamCredentials,
        streamId: Int,
        program: IptvProgram,
        durationMin: Long
    ): List<String> {
        val serverStartMs = program.startUtcMillis + getServerOffset(creds)
        val minutePattern = IptvRepoDateRegexes.MINUTE_PATTERN
        val secondPattern = IptvRepoDateRegexes.SECOND_PATTERN
        val spaceSecondPattern = IptvRepoDateRegexes.SPACE_SECOND_PATTERN
        val spaceMinutePattern = IptvRepoDateRegexes.SPACE_MINUTE_PATTERN
        val hasSecondOffset = serverStartMs % 60_000L != 0L || program.startUtcMillis % 60_000L != 0L
        val starts = if (hasSecondOffset) {
            listOf(
                formatUtcDateTime(serverStartMs, secondPattern),
                formatUtcDateTime(program.startUtcMillis, secondPattern),
                formatUtcDateTime(serverStartMs, spaceSecondPattern),
                formatUtcDateTime(program.startUtcMillis, spaceSecondPattern),
                formatUtcDateTime(serverStartMs, minutePattern),
                formatUtcDateTime(program.startUtcMillis, minutePattern),
                formatUtcDateTime(serverStartMs, spaceMinutePattern),
                formatUtcDateTime(program.startUtcMillis, spaceMinutePattern)
            )
        } else {
            listOf(
                formatUtcDateTime(serverStartMs, minutePattern),
                formatUtcDateTime(program.startUtcMillis, minutePattern),
                formatUtcDateTime(serverStartMs, secondPattern),
                formatUtcDateTime(program.startUtcMillis, secondPattern),
                formatUtcDateTime(serverStartMs, spaceSecondPattern),
                formatUtcDateTime(program.startUtcMillis, spaceSecondPattern),
                formatUtcDateTime(serverStartMs, spaceMinutePattern),
                formatUtcDateTime(program.startUtcMillis, spaceMinutePattern)
            )
        }.distinct()

        return buildList {
            starts.forEach { startStr ->
                add(buildXtreamTimeshiftPathUrl(creds, streamId, startStr, durationMin, extension = "ts"))
                add(buildXtreamTimeshiftPathUrl(creds, streamId, startStr, durationMin, extension = null))
                add(buildXtreamTimeshiftQueryUrl(creds, streamId, startStr, durationMin, includeStreamingPrefix = true))
                add(buildXtreamTimeshiftQueryUrl(creds, streamId, startStr, durationMin, includeStreamingPrefix = false))
                add(buildXtreamTimeshiftQueryUrl(creds, streamId, startStr, durationMin, includeStreamingPrefix = true, streamParameter = "stream_id"))
                add(buildXtreamTimeshiftQueryUrl(creds, streamId, startStr, durationMin, includeStreamingPrefix = false, streamParameter = "stream_id"))
                add(buildXtreamTimeshiftPathUrl(creds, streamId, startStr, durationMin, extension = "m3u8"))
            }
        }.distinct()
    }

    private fun formatUtcDateTime(epochMs: Long, formatter: DateTimeFormatter): String {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(epochMs), ZoneId.of("UTC")).format(formatter)
    }

    private fun applyCatchupSourceTemplate(
        channel: IptvChannel,
        source: String,
        program: IptvProgram,
        startForDateMs: Long,
        startUnix: Long,
        endUnix: Long,
        nowUnix: Long,
        durationMin: Long,
        streamId: Int?
    ): String {
        val startDt = LocalDateTime.ofInstant(Instant.ofEpochMilli(startForDateMs), ZoneId.of("UTC"))
        val endDt = LocalDateTime.ofInstant(Instant.ofEpochMilli(program.endUtcMillis), ZoneId.of("UTC"))
        val durationSec = ((program.endUtcMillis - program.startUtcMillis) / 1000L).coerceAtLeast(1L)
        val templated = decodeM3uEntities(source)
            .replaceDurationScalePlaceholders(durationSec)
            .replaceDatePatternPlaceholders("start", startDt)
            .replaceDatePatternPlaceholders("end", endDt)
            .replace("{utc}", startUnix.toString())
            .replace("\${start}", startUnix.toString())
            .replace("{start}", startUnix.toString())
            .replace("\$start", startUnix.toString())
            .replace("{timestamp}", nowUnix.toString())
            .replace("\${timestamp}", nowUnix.toString())
            .replace("\$timestamp", nowUnix.toString())
            .replace("{lutc}", nowUnix.toString())
            .replace("\${lutc}", nowUnix.toString())
            .replace("\$lutc", nowUnix.toString())
            .replace("\${end}", endUnix.toString())
            .replace("{end}", endUnix.toString())
            .replace("\$end", endUnix.toString())
            .replace("{duration}", durationMin.toString())
            .replace("\${duration}", durationMin.toString())
            .replace("\$duration", durationMin.toString())
            .replace("{duration_sec}", durationSec.toString())
            .replace("\${duration_sec}", durationSec.toString())
            .replace("{channel}", streamId?.toString().orEmpty())
            .replace("\${channel}", streamId?.toString().orEmpty())
            .replace("\$channel", streamId?.toString().orEmpty())
            .replace("{channel_id}", streamId?.toString().orEmpty())
            .replace("\${channel_id}", streamId?.toString().orEmpty())
            .replace("\$channel_id", streamId?.toString().orEmpty())
            .replace("{stream}", streamId?.toString().orEmpty())
            .replace("\${stream}", streamId?.toString().orEmpty())
            .replace("\$stream", streamId?.toString().orEmpty())
            .replace("{stream_id}", streamId?.toString().orEmpty())
            .replace("\${stream_id}", streamId?.toString().orEmpty())
            .replace("\$stream_id", streamId?.toString().orEmpty())
            .replace("{Y}", startDt.format(IptvRepoDateRegexes.YEAR_PATTERN))
            .replace("{m}", startDt.format(IptvRepoDateRegexes.MONTH_PATTERN))
            .replace("{d}", startDt.format(IptvRepoDateRegexes.DAY_PATTERN))
            .replace("{H}", startDt.format(IptvRepoDateRegexes.HOUR_PATTERN))
            .replace("{M}", startDt.format(IptvRepoDateRegexes.MIN_PATTERN))
            .replace("{S}", startDt.format(IptvRepoDateRegexes.SEC_PATTERN))
            .replace("{start:Y}", startDt.format(IptvRepoDateRegexes.YEAR_PATTERN))
            .replace("{start:m}", startDt.format(IptvRepoDateRegexes.MONTH_PATTERN))
            .replace("{start:d}", startDt.format(IptvRepoDateRegexes.DAY_PATTERN))
            .replace("{start:H}", startDt.format(IptvRepoDateRegexes.HOUR_PATTERN))
            .replace("{start:M}", startDt.format(IptvRepoDateRegexes.MIN_PATTERN))
            .replace("{start:S}", startDt.format(IptvRepoDateRegexes.SEC_PATTERN))
            .replace("{end:Y}", endDt.format(IptvRepoDateRegexes.YEAR_PATTERN))
            .replace("{end:m}", endDt.format(IptvRepoDateRegexes.MONTH_PATTERN))
            .replace("{end:d}", endDt.format(IptvRepoDateRegexes.DAY_PATTERN))
            .replace("{end:H}", endDt.format(IptvRepoDateRegexes.HOUR_PATTERN))
            .replace("{end:M}", endDt.format(IptvRepoDateRegexes.MIN_PATTERN))
            .replace("{end:S}", endDt.format(IptvRepoDateRegexes.SEC_PATTERN))
        return when {
            templated.startsWith("http://", ignoreCase = true) || templated.startsWith("https://", ignoreCase = true) -> templated
            templated.startsWith("/") -> channel.streamUrl.toHttpUrlOrNull()?.let { parsed ->
                buildString {
                    append(parsed.scheme)
                    append("://")
                    append(parsed.host)
                    if (parsed.port != if (parsed.scheme == "https") 443 else 80) {
                        append(":${parsed.port}")
                    }
                    append(templated)
                }
            } ?: templated
            templated.startsWith("?") -> channel.streamUrl.substringBefore('?') + templated
            templated.startsWith("&") -> channel.streamUrl + templated
            else -> templated
        }
    }

    private fun decodeM3uEntities(value: String): String {
        return value
            .replace("&amp;", "&")
            .replace("&quot;", "\"")
            .replace("&#34;", "\"")
            .replace("&apos;", "'")
            .replace("&#39;", "'")
    }

    private fun String.replaceDurationScalePlaceholders(durationSec: Long): String {
        return DURATION_SCALE_REGEX.replace(this) { match ->
            val divisor = (match.groupValues.getOrNull(1)?.takeIf { it.isNotBlank() }
                ?: match.groupValues.getOrNull(2))
                ?.toLongOrNull()
                ?.takeIf { it > 0L }
                ?: return@replace match.value
            (durationSec / divisor).coerceAtLeast(1L).toString()
        }
    }

    private val datePatternRegexCache = java.util.concurrent.ConcurrentHashMap<String, Regex>()

    private fun String.replaceDatePatternPlaceholders(key: String, dateTime: LocalDateTime): String {
        val regex = datePatternRegexCache.getOrPut(key) { Regex("""\$\{""" + key + """:([^}]+)\}|\{""" + key + """:([^}]+)\}""") }
        return regex.replace(this) { match ->
            val pattern = match.groupValues.getOrNull(1)?.takeIf { it.isNotBlank() }
                ?: match.groupValues.getOrNull(2)
                ?: return@replace match.value
            if (pattern in setOf("Y", "m", "d", "H", "M", "S")) {
                return@replace match.value
            }
            try { Result.success(dateTime.format(IptvRepoDateRegexes.formatterFor(pattern))) } catch (e: Exception) { if (e is kotlinx.coroutines.CancellationException) throw e
 Result.failure<String>(e) }
                .getOrDefault(match.value)
        }
    }

    private fun buildXtreamTimeshiftPathUrl(
        creds: XtreamCredentials,
        streamId: Int,
        startStr: String,
        durationMin: Long,
        extension: String? = "ts"
    ): String {
        val encodedUser = urlEncodePathSegment(creds.username)
        val encodedPass = urlEncodePathSegment(creds.password)
        val suffix = extension?.let { ".$it" }.orEmpty()
        return "${creds.baseUrl}/timeshift/$encodedUser/$encodedPass/$durationMin/${urlEncodeTimeshiftStart(startStr)}/$streamId$suffix"
    }

    private fun buildXtreamTimeshiftQueryUrl(
        creds: XtreamCredentials,
        streamId: Int,
        startStr: String,
        durationMin: Long,
        includeStreamingPrefix: Boolean,
        streamParameter: String = "stream"
    ): String {
        val path = if (includeStreamingPrefix) "streaming/timeshift.php" else "timeshift.php"
        return "${creds.baseUrl}/$path?username=${urlEncodeQuery(creds.username)}" +
            "&password=${urlEncodeQuery(creds.password)}" +
            "&$streamParameter=$streamId&start=${urlEncodeQuery(startStr)}&duration=$durationMin"
    }

    private data class PlaybackProbeResult(
        val statusCode: Int,
        val isPlayable: Boolean,
        val reason: String
    )

    private fun probePlaybackUrl(url: String, headers: Map<String, String>): PlaybackProbeResult? {
        val ranged = executePlaybackProbe(url, headers, useRange = true)
        if (ranged == null || ranged.isPlayable) return ranged
        if (ranged.statusCode in setOf(403, 405, 416, 500, 502, 503, 513)) {
            val normal = executePlaybackProbe(url, headers, useRange = false)
            if (normal?.isPlayable == true) return normal.copy(reason = "ok-no-range")
            return normal ?: ranged
        }
        return ranged
    }

    private fun executePlaybackProbe(
        url: String,
        headers: Map<String, String>,
        useRange: Boolean
    ): PlaybackProbeResult? {
        return runCatching {
            val builder = Request.Builder()
                .url(url)
                .header("User-Agent", headers["User-Agent"] ?: OkHttpProvider.userAgentOr(IPTV_USER_AGENT))
                .header("Accept", "*/*")
                .header("Accept-Encoding", "identity")
                .get()
            if (useRange) {
                builder.header("Range", "bytes=0-0")
            }
            headers.forEach { (name, value) ->
                if (name.isNotBlank() && value.isNotBlank() && !name.equals("Range", ignoreCase = true)) {
                    builder.header(name, value)
                }
            }
            xtreamGuideHttpClient.newCall(builder.build()).execute().use { response ->
                val statusCode = response.code
                if (statusCode !in 200..399) {
                    return@use PlaybackProbeResult(statusCode, isPlayable = false, reason = "http")
                }

                val contentType = response.header("Content-Type").orEmpty().lowercase(Locale.US)
                if (contentType.contains("text/html") || contentType.contains("application/json")) {
                    return@use PlaybackProbeResult(statusCode, isPlayable = false, reason = "content-type:$contentType")
                }

                PlaybackProbeResult(statusCode, isPlayable = true, reason = "ok")
            }
        }.getOrNull()
    }

    private fun redactIptvUrl(url: String): String {
        val withoutQuerySecrets = URL_QUERY_SECRETS_REGEX.replace(url) { match -> "${match.groupValues[1]}***" }

        return URL_PATH_SECRETS_REGEX
            .replace(withoutQuerySecrets) { match ->
                "${match.groupValues[1]}***/***${match.groupValues[4]}"
            }
            .take(260)
    }

    private fun urlEncodeQuery(value: String): String =
        java.net.URLEncoder.encode(value, StandardCharsets.UTF_8.name()).replace("+", "%20")

    private fun urlEncodePathSegment(value: String): String =
        urlEncodeQuery(value).replace("%2F", "%252F")

    private fun urlEncodeTimeshiftStart(value: String): String =
        urlEncodeQuery(value).replace("%3A", ":")

    suspend fun clearConfig() {
        context.settingsDataStore.edit { prefs ->
            prefs.remove(m3uUrlKey())
            prefs.remove(epgUrlKey())
            prefs.remove(playlistsKey())
            prefs.remove(favoriteGroupsKey())
            prefs.remove(favoriteChannelsKey())
            prefs.remove(hiddenGroupsKey())
            prefs.remove(groupOrderKey())
            prefs.remove(tvSessionKey())
        }
        invalidateCache()
        runCatching { cacheFile().delete() }
        invalidationBus.markDirty(CloudSyncScope.IPTV, profileManager.getProfileIdSync(), "clear iptv config")
    }

    suspend fun importCloudConfig(
        m3uUrl: String,
        epgUrl: String,
        favoriteGroups: List<String>,
        favoriteChannels: List<String> = emptyList()
    ) {
        val normalizedM3u = normalizeStoredIptvUrl(m3uUrl)
        val normalizedEpgUrls = normalizeStoredEpgInputs(epgUrl)
        val normalizedEpg = normalizedEpgUrls.firstOrNull().orEmpty()
        context.settingsDataStore.edit { prefs ->
            if (normalizedM3u.isBlank()) {
                prefs.remove(m3uUrlKey())
            } else {
                prefs[m3uUrlKey()] = encryptConfigValue(normalizedM3u)
            }
            if (normalizedEpg.isBlank()) {
                prefs.remove(epgUrlKey())
            } else {
                prefs[epgUrlKey()] = encryptConfigValue(normalizedEpg)
            }
            prefs[playlistsKey()] = gson.toJson(
                if (normalizedM3u.isNotBlank()) {
                    listOf(
                        IptvPlaylistEntry(
                            "list_1",
                            "List 1",
                            normalizedM3u,
                            normalizedEpgUrls.firstOrNull().orEmpty(),
                            epgUrls = normalizedEpgUrls
                        )
                    )
                } else {
                    emptyList()
                }
            )
            val cleanedFavorites = favoriteGroups
                .map { it.trim() }
                .filter { it.isNotBlank() }
                .distinct()
            if (cleanedFavorites.isEmpty()) {
                prefs.remove(favoriteGroupsKey())
            } else {
                prefs[favoriteGroupsKey()] = gson.toJson(cleanedFavorites)
            }

            val cleanedFavoriteChannels = favoriteChannels
                .map { it.trim() }
                .filter { it.isNotBlank() }
                .distinct()
            if (cleanedFavoriteChannels.isEmpty()) {
                prefs.remove(favoriteChannelsKey())
            } else {
                prefs[favoriteChannelsKey()] = gson.toJson(cleanedFavoriteChannels)
            }
        }
        invalidateCache()
        invalidationBus.markDirty(CloudSyncScope.IPTV, profileManager.getProfileIdSync(), "import iptv config")
    }

    suspend fun toggleFavoriteGroup(groupName: String) {
        val trimmed = groupName.trim()
        if (trimmed.isEmpty()) return
        context.settingsDataStore.edit { prefs ->
            val existing = decodeFavoriteGroups(prefs).toMutableList()
            if (existing.contains(trimmed)) {
                existing.remove(trimmed)
            } else {
                existing.remove(trimmed)
                existing.add(0, trimmed) // newest favorite first
            }
            prefs[favoriteGroupsKey()] = gson.toJson(existing)
        }
        invalidationBus.markDirty(CloudSyncScope.IPTV, profileManager.getProfileIdSync(), "toggle favorite group")
    }

    suspend fun toggleHiddenGroup(playlistId: String, groupName: String) {
        val trimmed = PlaylistGroupKey.build(playlistId, groupName.trim())
        if (groupName.trim().isEmpty()) return
        context.settingsDataStore.edit { prefs ->
            val existing = decodeHiddenGroups(prefs).toMutableList()
            if (existing.contains(trimmed)) {
                existing.remove(trimmed)
            } else {
                existing.add(trimmed)
            }
            prefs[hiddenGroupsKey()] = gson.toJson(existing)
        }
        invalidationBus.markDirty(CloudSyncScope.IPTV, profileManager.getProfileIdSync(), "toggle hidden group")
    }

    suspend fun moveGroupUp(playlistId: String, groupName: String, currentGroups: List<String> = emptyList()) {
        val target = PlaylistGroupKey.build(playlistId, groupName.trim())
        if (groupName.trim().isEmpty()) return
        val currentKeys = currentGroups.map { PlaylistGroupKey.build(playlistId, it.trim()) }
        context.settingsDataStore.edit { prefs ->
            val order = mergedGroupOrder(decodeGroupOrder(prefs), currentKeys)
            if (order.isEmpty()) return@edit
            val idx = order.indexOf(target)
            if (idx > 0) { order.removeAt(idx); order.add(idx - 1, target) }
            prefs[groupOrderKey()] = gson.toJson(order)
        }
        groupOrderLocallyDirty = true
        invalidationBus.markDirty(CloudSyncScope.IPTV, profileManager.getProfileIdSync(), "move group up")
    }

    suspend fun moveGroupToTop(playlistId: String, groupName: String, currentGroups: List<String> = emptyList()) {
        val target = PlaylistGroupKey.build(playlistId, groupName.trim())
        if (groupName.trim().isEmpty()) return
        val currentKeys = currentGroups.map { PlaylistGroupKey.build(playlistId, it.trim()) }
        context.settingsDataStore.edit { prefs ->
            val order = mergedGroupOrder(decodeGroupOrder(prefs), currentKeys)
            if (order.isEmpty()) return@edit
            if (target !in order && currentKeys.contains(target)) order.add(target)
            order.remove(target)
            order.add(0, target)
            prefs[groupOrderKey()] = gson.toJson(order)
        }
        groupOrderLocallyDirty = true
        invalidationBus.markDirty(CloudSyncScope.IPTV, profileManager.getProfileIdSync(), "move group to top")
    }

    suspend fun moveGroupDown(playlistId: String, groupName: String, currentGroups: List<String> = emptyList()) {
        val target = PlaylistGroupKey.build(playlistId, groupName.trim())
        if (groupName.trim().isEmpty()) return
        val currentKeys = currentGroups.map { PlaylistGroupKey.build(playlistId, it.trim()) }
        context.settingsDataStore.edit { prefs ->
            val order = mergedGroupOrder(decodeGroupOrder(prefs), currentKeys)
            if (order.isEmpty()) return@edit
            val idx = order.indexOf(target)
            if (idx >= 0 && idx < order.size - 1) { order.removeAt(idx); order.add(idx + 1, target) }
            prefs[groupOrderKey()] = gson.toJson(order)
        }
        groupOrderLocallyDirty = true
        invalidationBus.markDirty(CloudSyncScope.IPTV, profileManager.getProfileIdSync(), "move group down")
    }

    suspend fun resetGroupOrder(playlistId: String) {
        context.settingsDataStore.edit { prefs ->
            val existing = decodeGroupOrder(prefs).toMutableList()
            existing.removeAll { PlaylistGroupKey(it).playlistId == playlistId }
            prefs[groupOrderKey()] = gson.toJson(existing)
        }
        groupOrderLocallyDirty = true
        invalidationBus.markDirty(CloudSyncScope.IPTV, profileManager.getProfileIdSync(), "reset group order")
    }

    suspend fun toggleFavoriteChannel(channelId: String) {
        val trimmed = channelId.trim()
        if (trimmed.isEmpty()) return
        context.settingsDataStore.edit { prefs ->
            val existing = decodeFavoriteChannels(prefs).toMutableList()
            if (existing.contains(trimmed)) {
                existing.remove(trimmed)
            } else {
                existing.remove(trimmed)
                existing.add(0, trimmed)
            }
            prefs[favoriteChannelsKey()] = gson.toJson(existing)
        }
        invalidationBus.markDirty(CloudSyncScope.IPTV, profileManager.getProfileIdSync(), "toggle favorite channel")
    }

    suspend fun loadSnapshot(
        forcePlaylistReload: Boolean = false,
        forceEpgReload: Boolean = false,
        allowNetworkEpgFetch: Boolean = false,
        allowBroadShortEpg: Boolean = true,
        onProgress: (IptvLoadProgress) -> Unit = {},
        onChannelsReady: suspend (List<IptvChannel>) -> Unit = {}
    ): IptvSnapshot {
        return withContext(Dispatchers.IO) {
            loadMutex.withLock {
            cleanupStaleEpgTempFiles()
            onProgress(IptvLoadProgress(context.getString(R.string.iptv_starting_load), 2))
            val now = System.currentTimeMillis()
            val config = observeConfig().first()
            val profileId = profileManager.getProfileIdSync()
            ensureCacheOwnership(profileId, config)
            cleanupIptvCacheDirectory()
            val activePlaylists = activePlaylists(config)
            if (activePlaylists.isEmpty() && config.stalkerPortalUrl.isBlank()) {
                return@withContext IptvSnapshot(
                    channels = emptyList(),
                    grouped = emptyMap(),
                    nowNext = emptyMap(),
                    favoriteGroups = observeFavoriteGroups().first(),
                    favoriteChannels = observeFavoriteChannels().first(),
                    loadedAt = Instant.now()
                )
            }

            // ── Stalker Portal path ──
            if (config.m3uUrl.isBlank() && config.stalkerPortalUrl.isNotBlank()) {
                onProgress(IptvLoadProgress(context.getString(R.string.iptv_connecting_stalker), 10))
                val stalker = com.arflix.tv.data.api.StalkerApi(config.stalkerPortalUrl, config.stalkerMacAddress)
                if (!stalker.handshake()) {
                    return@withContext IptvSnapshot(epgWarning = "Stalker handshake failed. Check Portal URL and MAC.", loadedAt = Instant.now())
                }
                onProgress(IptvLoadProgress(context.getString(R.string.iptv_progress_loading_channels), 30))
                stalker.getProfile()
                val channels = stalker.getChannels()
                onProgress(IptvLoadProgress(context.getString(R.string.iptv_loaded_channels, channels.size), 80))
                val grouped = buildGroupedChannels(channels)
                val favGroups = observeFavoriteGroups().first()
                val favChannels = observeFavoriteChannels().first()
                val hiddenGroups = observeHiddenGroups().first()
                val groupOrder = observeGroupOrder().first()
                cachedChannels = channels
                cachedGroupedChannels = grouped
                cachedStalkerApi = stalker
                val snapshot = IptvSnapshot(
                    channels = channels,
                    grouped = grouped,
                    nowNext = emptyMap(),
                    favoriteGroups = favGroups,
                    favoriteChannels = favChannels,
                    hiddenGroups = hiddenGroups,
                    groupOrder = groupOrder,
                    loadedAt = Instant.now()
                )
                onProgress(IptvLoadProgress(context.getString(R.string.done), 100))
                return@withContext snapshot
            }

            val cachedChannelsFromDisk = if (cachedChannels.isEmpty()) readChannelCache(config) else null
            val cachedGuideFromDisk = if (
                cachedChannelsFromDisk != null &&
                cachedChannelsFromDisk.channels.size <= LargeIptvListChannelCount
            ) {
                readCache(config)
            } else {
                null
            }
            if (cachedChannelsFromDisk != null) {
                cachedChannels = cachedChannelsFromDisk.channels
                cachedGroupedChannels = buildGroupedChannels(cachedChannelsFromDisk.channels)
                cachedNowNext = ConcurrentHashMap(cachedGuideFromDisk?.nowNext.orEmpty())
                cachedPlaylistAt = cachedChannelsFromDisk.loadedAtEpochMs
                cachedEpgAt = cachedGuideFromDisk?.loadedAtEpochMs ?: cachedChannelsFromDisk.loadedAtEpochMs
                if (cachedChannelsFromDisk.channels.size <= LargeIptvListChannelCount) {
                    reDeriveCachedNowNext(cachedChannelsFromDisk.channels.asSequence().map { it.id }.toSet())
                } else {
                    System.err.println("[EPG-Memory] large playlist warm start uses indexed guide only; channels=${cachedChannelsFromDisk.channels.size}")
                }
            }

            val channels = if (!forcePlaylistReload && cachedChannels.isNotEmpty()) {
                val isFresh = now - cachedPlaylistAt < playlistCacheMs
                onProgress(
                    IptvLoadProgress(
                        if (isFresh) {
                            "Using cached playlist (${cachedChannels.size} channels)"
                        } else {
                            "Using cached playlist (${cachedChannels.size} channels, stale)"
                        },
                        80
                    )
                )
                cachedChannels
            } else {
                coroutineScope {
                    val playlistResults = Array<MutableList<IptvChannel>?>(activePlaylists.size) { null }
                    val playlistResultsLock = Any()
                    activePlaylists.mapIndexed { playlistIndex, playlist ->
                        async {
                            val playlistChannels = fetchChannelsForPlaylistWithRetries(playlist, onProgress)
                                .map { channel ->
                                    channel.copy(
                                        id = "${playlist.id}:${channel.id}",
                                        group = channel.group
                                    )
                                }
                            if (playlistChannels.isNotEmpty()) {
                                val currentList = synchronized(playlistResultsLock) {
                                    playlistResults[playlistIndex] = playlistChannels.toMutableList()
                                    ArrayList<IptvChannel>().apply {
                                        playlistResults.forEach { result ->
                                            if (!result.isNullOrEmpty()) addAll(result)
                                        }
                                    }
                                }
                                runCatching { onChannelsReady(currentList) }
                            } else {
                                synchronized(playlistResultsLock) {
                                    playlistResults[playlistIndex] = mutableListOf()
                                }
                            }
                        }
                    }.awaitAll()
                    synchronized(playlistResultsLock) {
                        playlistResults.flatMap { it.orEmpty() }
                    }
                }.also {
                    cachedChannels = it
                    cachedGroupedChannels = buildGroupedChannels(it)
                    cachedPlaylistAt = System.currentTimeMillis()
                }
            }

            // Publish channels immediately so the UI can show them while EPG loads.
            // Uses cached nowNext if available to paint initial EPG state.
            runCatching { onChannelsReady(channels) }
            if ((forcePlaylistReload || cachedChannelsFromDisk == null) && channels.isNotEmpty()) {
                val immediateNowNext = if (channels.size > LargeIptvListChannelCount) {
                    emptyMap()
                } else {
                    reDeriveCachedNowNext(channels.asSequence().map { it.id }.toSet()).orEmpty()
                }
                writeCache(
                    config = config,
                    channels = channels,
                    nowNext = immediateNowNext,
                    loadedAtMs = System.currentTimeMillis()
                )
            }

            if (allowNetworkEpgFetch) {
                discoverEmbeddedEpgSourcesIfNeeded(activePlaylists)
            }
            val epgCandidates = resolveScopedEpgCandidates(config)
            var epgUpdated = false
            val cachedHasPrograms = hasAnyProgramData(cachedNowNext)
            val shouldUseCachedEpg = !forceEpgReload && (
                cachedHasPrograms ||
                    (!cachedHasPrograms && now - cachedEpgAt < epgEmptyRetryMs)
                )
            var epgFailureMessage: String? = null

            // Check if this is an Xtream provider (can use fast short EPG API)
            val xtreamProviderGroups = groupXtreamChannelsByCredentials(config, channels)
            val hasXtreamChannels = xtreamProviderGroups.isNotEmpty()
            val shouldFetchBroadShortEpg = allowBroadShortEpg || epgCandidates.isEmpty() || channels.size <= LargeIptvListChannelCount
            System.err.println("[EPG] loadSnapshot: forceEpgReload=$forceEpgReload shouldUseCachedEpg=$shouldUseCachedEpg cachedHasPrograms=$cachedHasPrograms xtreamProviders=${xtreamProviderGroups.size} hasXtreamChannels=$hasXtreamChannels epgCandidates=${epgCandidates.size} broadShort=$shouldFetchBroadShortEpg")
            val cachedFallbackNowNext = if (channels.size > LargeIptvListChannelCount) {
                emptyMap()
            } else {
                reDeriveCachedNowNext(channels.asSequence().map { it.id }.toSet()) ?: cachedNowNext
            }
            val nowNext = if (shouldUseCachedEpg) {
                onProgress(IptvLoadProgress(context.getString(R.string.epg_using_cached), 92))
                System.err.println("[EPG] Using cached EPG (${cachedNowNext.size} channels, age=${(now - cachedEpgAt)/1000}s)")
                cachedFallbackNowNext
            } else if (!allowNetworkEpgFetch) {
                if (cachedFallbackNowNext.isNotEmpty()) {
                    onProgress(IptvLoadProgress(context.getString(R.string.epg_using_cached), 92))
                    System.err.println("[EPG] Skipping broad network EPG fetch and using cached fallback (${cachedFallbackNowNext.size} channels)")
                    cachedFallbackNowNext
                } else {
                    System.err.println("[EPG] Skipping broad network EPG fetch until category-scoped guide request")
                    emptyMap()
                }
            } else if (epgCandidates.isEmpty() && !hasXtreamChannels) {
                if (cachedFallbackNowNext.isNotEmpty()) {
                    onProgress(IptvLoadProgress(context.getString(R.string.epg_using_cached), 92))
                    System.err.println("[EPG] No active EPG source, keeping cached EPG fallback (${cachedFallbackNowNext.size} channels)")
                    cachedFallbackNowNext
                } else {
                    onProgress(IptvLoadProgress(context.getString(R.string.epg_no_url), 90))
                    System.err.println("[EPG] No EPG URL and no Xtream creds - skipping EPG")
                    emptyMap()
                }
            } else {
                var resolvedNowNext: Map<String, IptvNowNext> = emptyMap()
                var resolved = false

                // Collect EPG from multiple sources and merge them all.
                // Short EPG is fast (~10s) but only covers channels that have data.
                // XMLTV is slow (~60s) but comprehensive. Both run, results are merged.
                var shortEpgResult: Map<String, IptvNowNext>? = null

                // ── Fast path: Xtream short EPG API ──
                if (hasXtreamChannels && shouldFetchBroadShortEpg) {
                    System.err.println("[EPG] Attempting provider-scoped Xtream short EPG (${xtreamProviderGroups.size} providers)")
                    val shortEpgAttempt = runCatching {
                        fetchXtreamShortEpgForActiveProviders(config, channels, onProgress)
                    }
                    if (shortEpgAttempt.isSuccess) {
                        val parsed = shortEpgAttempt.getOrNull()
                        val parsedHasData = parsed != null && hasAnyProgramData(parsed)
                        System.err.println("[EPG] Xtream short EPG result: ${parsed?.size ?: 0} channels, hasData=$parsedHasData")
                        if (parsed != null && parsedHasData) {
                            shortEpgResult = parsed
                            // Provide immediate results: merge short EPG with cached data (no stale removal)
                            cachedNowNext.putAll(parsed) // Short EPG data takes priority (fresher)
                            resolvedNowNext = cachedNowNext
                            cachedEpgAt = System.currentTimeMillis()
                            persistEpgIndexChannels(config, parsed, cachedEpgAt)
                            epgUpdated = true
                            resolved = true
                            System.err.println("[EPG] Xtream short EPG SUCCESS: ${parsed.size} fresh, ${cachedNowNext.size} total cached")
                        }
                    } else {
                        System.err.println("[EPG] Xtream short EPG FAILED: ${shortEpgAttempt.exceptionOrNull()?.message}")
                    }
                } else if (hasXtreamChannels) {
                    System.err.println("[EPG] Skipping broad Xtream short EPG so full XMLTV can backfill ${channels.size} channels first")
                }

                // ── Slow path: XMLTV download. On large lists normal startup keeps first paint fast
                // by skipping this after short EPG; forced/background refreshes still run it.
                val skipXmlTvAfterXtreamShort = hasXtreamChannels &&
                    !forceEpgReload &&
                    channels.size > LargeIptvListChannelCount &&
                    shortEpgResult != null &&
                    hasAnyProgramData(shortEpgResult)
                if (skipXmlTvAfterXtreamShort) {
                    System.err.println(
                        "[EPG] Skipping XMLTV after large-list Xtream sweep; " +
                            "provider short/simple APIs already returned ${shortEpgResult?.size ?: 0} channels"
                    )
                } else if (epgCandidates.isNotEmpty()) {
                    val epgCandidatesToTry = epgCandidates
                    var bestCoverage = epgCoverageRatio(channels, resolvedNowNext)
                    val mergedXmlNowNext = ConcurrentHashMap(resolvedNowNext)
                    var xmltvChanged = false
                    for ((index, candidate) in epgCandidatesToTry.withIndex()) {
                        val epgUrl = candidate.url
                        val candidateChannels = channelsForScopedEpgCandidate(candidate, channels)
                        if (candidateChannels.isEmpty()) continue
                        val pct = (90 + ((index * 8) / epgCandidatesToTry.size.coerceAtLeast(1))).coerceIn(90, 98)
                        onProgress(IptvLoadProgress(context.getString(R.string.iptv_progress_loading_full_epg, index + 1, epgCandidatesToTry.size), pct))
                        val attempt = runCatching {
                            // 300 s: some providers (like TX-4K) serve a 100 MB
                            // XMLTV dump that needs 2-3 min on a TV's WiFi.
                            // 90 s was aborting before the file finished.
                            withTimeoutOrNull(300_000L) { fetchAndParseEpg(epgUrl, candidateChannels) }
                                ?: throw java.util.concurrent.TimeoutException(context.getString(R.string.epg_timeout, epgUrl.take(80)))
                        }
                        if (attempt.isSuccess) {
                            val parsed = attempt.getOrDefault(emptyMap())
                            val parsedHasPrograms = hasAnyProgramData(parsed)
                            if (parsedHasPrograms) {
                                xmltvChanged = true
                                parsed.forEach { (channelId, nowNext) ->
                                    val current = mergedXmlNowNext[channelId]
                                    if (!hasProgramData(current) && hasProgramData(nowNext)) {
                                        mergedXmlNowNext[channelId] = nowNext
                                    } else if (current == null) {
                                        mergedXmlNowNext[channelId] = nowNext
                                    }
                                }
                                val coverage = epgCoverageRatio(channels, mergedXmlNowNext)
                                if (coverage >= bestCoverage) {
                                    bestCoverage = coverage
                                }
                                resolved = true
                                System.err.println("[EPG] XMLTV candidate ${index + 1} merged coverage=${(coverage * 100).toInt()}%")
                                if (coverage >= completeEpgCoverageTarget) {
                                    System.err.println("[EPG] XMLTV coverage target reached; skipping remaining EPG candidates")
                                    break
                                }
                            }
                        } else {
                            val exception = attempt.exceptionOrNull()
                            if (exception is EpgNotModifiedException) {
                                System.err.println("[EPG] XMLTV candidate ${index + 1} is unchanged (HTTP 304). Loading existing index...")
                                val existing = runCatching {
                                    epgIndex.loadNowNext(
                                        sourceKey = currentEpgIndexKey(config),
                                        channelIds = candidateChannels.map { it.id }.toSet()
                                    )
                                }.getOrDefault(emptyMap())
                                existing.forEach { (channelId, nowNext) ->
                                    val current = mergedXmlNowNext[channelId]
                                    if (!hasProgramData(current) && hasProgramData(nowNext)) {
                                        mergedXmlNowNext[channelId] = nowNext
                                    } else if (current == null) {
                                        mergedXmlNowNext[channelId] = nowNext
                                    }
                                }
                                resolved = true
                            } else {
                                epgFailureMessage = exception?.message
                                System.err.println("[EPG] XMLTV attempt ${index + 1} failed: ${epgFailureMessage}")
                            }
                        }
                    }
                    if (resolved) {
                        shortEpgResult?.let { mergedXmlNowNext.putAll(it) } // Short EPG wins for channels it covers
                        resolvedNowNext = mergedXmlNowNext
                        cachedNowNext = mergedXmlNowNext
                        cachedEpgAt = System.currentTimeMillis()
                        if (xmltvChanged) {
                            persistEpgIndexAll(config, mergedXmlNowNext, cachedEpgAt)
                        } else {
                            System.err.println("[EPG] Skipping persistEpgIndexAll because XMLTV index is unchanged")
                        }
                        epgUpdated = true
                        System.err.println("[EPG] Final merged EPG coverage=${(epgCoverageRatio(channels, mergedXmlNowNext) * 100).toInt()}% for ${channels.size} channels")
                    }
                }

                val currentCoverage = epgCoverageRatio(channels, resolvedNowNext)
                if (
                    resolved &&
                    currentCoverage < completeEpgCoverageTarget &&
                    !shouldFetchBroadShortEpg &&
                    hasXtreamChannels
                ) {
                    val missingXtreamChannels = channels.filter { channel ->
                        resolveXtreamStreamId(channel) != null && !hasProgramData(resolvedNowNext[channel.id])
                    }
                    if (missingXtreamChannels.isNotEmpty()) {
                        System.err.println(
                            "[EPG] XMLTV coverage ${(currentCoverage * 100).toInt()}%; " +
                                "backfilling ${missingXtreamChannels.size} missing Xtream channels"
                        )
                        val shortEpgAttempt = runCatching {
                            fetchXtreamShortEpgForActiveProviders(config, missingXtreamChannels, onProgress)
                        }
                        if (shortEpgAttempt.isSuccess) {
                            val parsed = shortEpgAttempt.getOrNull()
                            if (parsed != null && hasAnyProgramData(parsed)) {
                                val merged = ConcurrentHashMap(resolvedNowNext)
                                merged.putAll(parsed)
                                resolvedNowNext = merged
                                cachedNowNext = ConcurrentHashMap(merged)
                                cachedEpgAt = System.currentTimeMillis()
                                persistEpgIndexChannels(config, parsed, cachedEpgAt)
                                epgUpdated = true
                                val backfilledCoverage = epgCoverageRatio(channels, merged)
                                System.err.println(
                                    "[EPG] Missing-channel Xtream backfill added ${parsed.size} channels; " +
                                        "coverage=${(backfilledCoverage * 100).toInt()}%"
                                )
                            }
                        } else {
                            epgFailureMessage = shortEpgAttempt.exceptionOrNull()?.message
                            System.err.println("[EPG] Missing-channel Xtream backfill failed: $epgFailureMessage")
                        }
                    }
                }

                if (!resolved && !shouldFetchBroadShortEpg && hasXtreamChannels) {
                    System.err.println("[EPG] XMLTV did not resolve; falling back to full Xtream guide API")
                    val fullEpgAttempt = runCatching {
                        fetchXtreamFullEpgForActiveProviders(config, channels, onProgress)
                    }
                    if (fullEpgAttempt.isSuccess) {
                        val parsed = fullEpgAttempt.getOrNull()
                        if (parsed != null && hasAnyProgramData(parsed)) {
                            cachedNowNext.putAll(parsed)
                            resolvedNowNext = cachedNowNext
                            cachedEpgAt = System.currentTimeMillis()
                            persistEpgIndexChannels(config, parsed, cachedEpgAt)
                            epgUpdated = true
                            resolved = true
                            System.err.println("[EPG] Full Xtream fallback SUCCESS: ${parsed.size} fresh, ${cachedNowNext.size} total cached")
                        }
                    } else {
                        epgFailureMessage = fullEpgAttempt.exceptionOrNull()?.message
                        System.err.println("[EPG] Full Xtream fallback failed: $epgFailureMessage")
                    }
                    if (!resolved) {
                        System.err.println("[EPG] Full Xtream fallback did not resolve; falling back to broad Xtream short EPG")
                        val shortEpgAttempt = runCatching {
                            fetchXtreamShortEpgForActiveProviders(config, channels, onProgress)
                        }
                        if (shortEpgAttempt.isSuccess) {
                            val parsed = shortEpgAttempt.getOrNull()
                            if (parsed != null && hasAnyProgramData(parsed)) {
                                cachedNowNext.putAll(parsed)
                                resolvedNowNext = cachedNowNext
                                cachedEpgAt = System.currentTimeMillis()
                                persistEpgIndexChannels(config, parsed, cachedEpgAt)
                                epgUpdated = true
                                resolved = true
                                System.err.println("[EPG] Broad Xtream fallback SUCCESS: ${parsed.size} fresh, ${cachedNowNext.size} total cached")
                            }
                        } else {
                            epgFailureMessage = shortEpgAttempt.exceptionOrNull()?.message
                            System.err.println("[EPG] Broad Xtream fallback failed: $epgFailureMessage")
                        }
                    }
                }

                if (!resolved) {
                    if (cachedFallbackNowNext.isNotEmpty()) {
                        resolvedNowNext = cachedFallbackNowNext
                        System.err.println("[EPG] Keeping stale cached EPG fallback after refresh failure (${cachedFallbackNowNext.size} channels)")
                    } else {
                        // Throttle repeated failures to avoid refetching every open.
                        cachedNowNext = ConcurrentHashMap()
                        cachedEpgAt = System.currentTimeMillis()
                        epgUpdated = true
                    }
                }
                resolvedNowNext
            }
            val epgFailure = epgFailureMessage
            val epgWarning = if (epgCandidates.isNotEmpty() && nowNext.isEmpty()) {
                if (!epgFailure.isNullOrBlank()) {
                    "EPG unavailable right now (${epgFailure.take(120)})."
                } else {
                    "EPG unavailable for this source right now."
                }
            } else null

            val favoriteGroups = observeFavoriteGroups().first()
            val favoriteChannels = observeFavoriteChannels().first()
            val grouped = if (cachedChannels === channels && cachedGroupedChannels.isNotEmpty()) {
                cachedGroupedChannels
            } else {
                buildGroupedChannels(channels)
            }

            val loadedAtMillis = if (cachedPlaylistAt > 0L) cachedPlaylistAt else now
            val loadedAtInstant = Instant.ofEpochMilli(loadedAtMillis)

            val hiddenGroups = observeHiddenGroups().first()
            val groupOrder = observeGroupOrder().first()

            IptvSnapshot(
                channels = channels,
                grouped = grouped,
                nowNext = nowNext,
                favoriteGroups = favoriteGroups,
                favoriteChannels = favoriteChannels,
                hiddenGroups = hiddenGroups,
                groupOrder = groupOrder,
                epgWarning = epgWarning,
                loadedAt = loadedAtInstant
            ).also {
                if (forcePlaylistReload || forceEpgReload || cachedChannelsFromDisk == null || epgUpdated) {
                    writeCache(
                        config = config,
                        channels = channels,
                        nowNext = nowNext,
                        loadedAtMs = System.currentTimeMillis()
                    )
                }
                onProgress(IptvLoadProgress(context.getString(R.string.iptv_loaded_channels, channels.size), 100))
            }
            }
        }
    }

    /**
     * Cache-only warmup used at app start.
     * Never performs network calls, so startup cannot get blocked by heavy playlists.
     */
    suspend fun warmupFromCacheOnly() {
        withContext(Dispatchers.IO) {
            loadMutex.withLock {
                val config = observeConfig().first()
                val profileId = profileManager.getProfileIdSync()
                ensureCacheOwnership(profileId, config)
                if (!hasAnyConfiguredSource(config)) return@withLock
                if (cachedChannels.isNotEmpty()) return@withLock

                val cached = readChannelCache(config) ?: return@withLock
                cachedChannels = cached.channels
                cachedGroupedChannels = buildGroupedChannels(cached.channels)
                cachedPlaylistAt = cached.loadedAtEpochMs
                val guideCache = if (cached.channels.size <= LargeIptvListChannelCount) {
                    readCache(config)
                        ?.takeIf { it.nowNext.isNotEmpty() && hasAnyProgramData(it.nowNext) }
                } else {
                    null
                }
                if (guideCache != null) {
                    cachedNowNext = ConcurrentHashMap(guideCache.nowNext)
                    cachedEpgAt = guideCache.loadedAtEpochMs
                    persistEpgIndexChannels(config, guideCache.nowNext, cachedEpgAt)
                    val indexedChannels = countIndexedGuideChannels()
                    val indexedPrograms = countIndexedGuidePrograms()
                    System.err.println(
                        "[EPG] Warm cache loaded ${guideCache.nowNext.size} guide channels from disk; " +
                            "index=$indexedChannels channels/$indexedPrograms programs"
                    )
                } else {
                    cachedNowNext = ConcurrentHashMap()
                    cachedEpgAt = cached.loadedAtEpochMs
                    if (cached.channels.size > LargeIptvListChannelCount) {
                        System.err.println("[EPG-Memory] warm cache skipped full guide hydration for ${cached.channels.size} channels")
                    }
                }
            }
        }
    }

    /**
     * Returns the latest snapshot from memory/disk cache only.
     * Never performs network calls.
     */
    suspend fun getCachedSnapshotOrNull(): IptvSnapshot? {
        return withContext(Dispatchers.IO) {
            loadMutex.withLock {
                val config = observeConfig().first()
                val profileId = profileManager.getProfileIdSync()
                ensureCacheOwnership(profileId, config)

                if (!hasAnyConfiguredSource(config)) {
                    return@withLock IptvSnapshot(
                        channels = emptyList(),
                        grouped = emptyMap(),
                        nowNext = emptyMap(),
                        favoriteGroups = observeFavoriteGroups().first(),
                        favoriteChannels = observeFavoriteChannels().first(),
                        loadedAt = Instant.now()
                    )
                }

                if (cachedChannels.isEmpty()) {
                    val cached = readChannelCache(config) ?: return@withLock null
                    val guideCache = if (cached.channels.size <= LargeIptvListChannelCount) {
                        readCache(config)
                    } else {
                        null
                    }
                    cachedChannels = cached.channels
                    cachedGroupedChannels = buildGroupedChannels(cached.channels)
                    cachedNowNext = ConcurrentHashMap(guideCache?.nowNext.orEmpty())
                    cachedPlaylistAt = cached.loadedAtEpochMs
                    cachedEpgAt = guideCache?.loadedAtEpochMs ?: cached.loadedAtEpochMs
                    if (cached.channels.size > LargeIptvListChannelCount) {
                        System.err.println("[EPG-Memory] cached snapshot skipped full guide hydration for ${cached.channels.size} channels")
                    }
                }

                val favoriteGroups = observeFavoriteGroups().first()
                val favoriteChannels = observeFavoriteChannels().first()
                val grouped = cachedGroupedChannels.ifEmpty {
                    buildGroupedChannels(cachedChannels).also { cachedGroupedChannels = it }
                }
                val loadedAtMillis = if (cachedPlaylistAt > 0L) cachedPlaylistAt else System.currentTimeMillis()

                IptvSnapshot(
                    channels = cachedChannels,
                    grouped = grouped,
                    nowNext = cachedNowNext,
                    favoriteGroups = favoriteGroups,
                    favoriteChannels = favoriteChannels,
                    epgWarning = null,
                    loadedAt = Instant.ofEpochMilli(loadedAtMillis)
                )
            }
        }
    }

    fun isSnapshotStale(snapshot: IptvSnapshot): Boolean {
        val ageMs = System.currentTimeMillis() - snapshot.loadedAt.toEpochMilli()
        return ageMs > staleAfterMs
    }

    /** Age of cached EPG data in milliseconds, or Long.MAX_VALUE if no cache. */
    fun cachedEpgAgeMs(): Long {
        val at = cachedEpgAt
        return if (at <= 0L) Long.MAX_VALUE else System.currentTimeMillis() - at
    }

    /**
     * Non-blocking in-memory snapshot read. Returns null if in-memory cache is empty.
     * Unlike [getCachedSnapshotOrNull], this does NOT acquire [loadMutex] and does NOT
     * fall back to disk — it only reads volatile in-memory fields.
     * Use this when you need a fast, contention-free read (e.g., on navigation).
     */
    suspend fun getMemoryCachedSnapshot(): IptvSnapshot? {
        val channels = cachedChannels
        if (channels.isEmpty()) return null
        val favoriteGroups = observeFavoriteGroups().first()
        val favoriteChannels = observeFavoriteChannels().first()
        val grouped = cachedGroupedChannels.ifEmpty {
            buildGroupedChannels(channels).also { cachedGroupedChannels = it }
        }
        val loadedAtMillis = if (cachedPlaylistAt > 0L) cachedPlaylistAt else System.currentTimeMillis()
        return IptvSnapshot(
            channels = channels,
            grouped = grouped,
            nowNext = cachedNowNext,
            favoriteGroups = favoriteGroups,
            favoriteChannels = favoriteChannels,
            epgWarning = null,
            loadedAt = Instant.ofEpochMilli(loadedAtMillis)
        )
    }

    /**
     * Re-derive now/next from cached EPG program data without any network call.
     * Programs shift: if "now" has ended, "next" becomes "now", etc.
     * Updates cachedNowNext in place so subsequent reads via getCachedSnapshotOrNull()
     * return the re-derived data.
     * Returns updated nowNext map for the given channel IDs, or null if no cached data.
     */
    fun reDeriveCachedNowNext(channelIds: Set<String>): Map<String, IptvNowNext>? {
        val cached = cachedNowNext
        val nowMs = System.currentTimeMillis()
        val channelsById = cachedChannelLookup()
        val missingIndexedIds = channelIds.filterTo(LinkedHashSet()) { channelId ->
            val guide = cached[channelId]
            !hasProgramData(guide) || shouldLoadIndexedGuide(guide, channelsById[channelId], nowMs)
        }
        val indexKey = currentEpgIndexKey
        if (missingIndexedIds.isNotEmpty() && indexKey.isNotBlank()) {
            val indexed = runCatching {
                epgIndex.loadNowNext(indexKey, missingIndexedIds)
            }.onFailure { error ->
                System.err.println("[EPG-Index] Failed to read guide index: ${error.message}")
            }.getOrDefault(emptyMap())
            if (indexed.isNotEmpty()) {
                indexed.forEach { (channelId, indexedGuide) ->
                    cachedNowNext[channelId] = mergeCachedGuideSlice(cachedNowNext[channelId], indexedGuide)
                }
            }
        }
        if (cachedNowNext.isEmpty()) return null

        val result = mutableMapOf<String, IptvNowNext>()
        for (channelId in channelIds) {
            val existing = cachedNowNext[channelId] ?: continue
            val recentCutoff = recentCutoffForChannel(channelsById[channelId], nowMs)
            // Collect all known programs from the cached entry efficiently
            val allPrograms = java.util.ArrayList<IptvProgram>(
                (if (existing.now != null) 1 else 0) +
                    (if (existing.next != null) 1 else 0) +
                    (if (existing.later != null) 1 else 0) +
                    existing.upcoming.size +
                    existing.recent.size
            )
            existing.now?.let { allPrograms.add(it) }
            existing.next?.let { allPrograms.add(it) }
            existing.later?.let { allPrograms.add(it) }
            allPrograms.addAll(existing.upcoming)
            allPrograms.addAll(existing.recent)
            allPrograms.sortBy { it.startUtcMillis }

            var now: IptvProgram? = null
            var next: IptvProgram? = null
            var later: IptvProgram? = null
            val upcoming = java.util.ArrayList<IptvProgram>(epgUpcomingProgramLimit)
            val recent = java.util.ArrayList<IptvProgram>()

            if (allPrograms.isNotEmpty()) {
                var startIndex = allPrograms.binarySearch { it.startUtcMillis.compareTo(recentCutoff) }
                if (startIndex < 0) {
                    startIndex = -(startIndex + 1)
                }

                // Walk backward to include programs starting before recentCutoff but ending after
                while (startIndex > 0 && allPrograms[startIndex - 1].endUtcMillis > recentCutoff) {
                    startIndex--
                }

                for (i in startIndex until allPrograms.size) {
                    val p = allPrograms[i]
                    when {
                        p.endUtcMillis <= nowMs && p.endUtcMillis > recentCutoff -> {
                            addRecentCandidate(recent, p, recentProgramLimitForChannel(channelsById[channelId]))
                        }
                        p.isLive(nowMs) -> now = p
                        p.startUtcMillis > nowMs && next == null -> next = p
                        p.startUtcMillis > nowMs && later == null -> later = p
                        p.startUtcMillis > nowMs -> {
                            upcoming.add(p)
                            if (upcoming.size >= epgUpcomingProgramLimit) {
                                break // We have enough upcoming programs
                            }
                        }
                    }
                }
            }

            result[channelId] = IptvNowNext(
                now = now,
                next = next,
                later = later,
                upcoming = upcoming,
                recent = recent
            )
        }
        if (result.isEmpty()) return null

        // Write back re-derived entries into cachedNowNext (in-place, no copy)
        cachedNowNext.putAll(result)

        return result
    }

    fun indexedGuideChannelCount(): Int = countIndexedGuideChannels()

    fun indexedGuideProgramCount(): Int = countIndexedGuidePrograms()

    // ── Paged channel access (Stage 2) ─────────────────────────────────────────
    // Backed by the SQLite channel store so the Live TV UI can page the channel
    // list per category/window instead of holding all (50k+) channels in memory.
    // Keyed by the active index key (set during loadSnapshot / cache ownership).

    fun pagedChannelsReady(): Boolean =
        currentEpgIndexKey.isNotBlank() && runCatching { channelStore.count(currentEpgIndexKey) }.getOrDefault(0) > 0

    fun pagedChannelCount(groupTitle: String?): Int =
        runCatching { channelStore.countForGroup(currentEpgIndexKey, groupTitle) }.getOrDefault(0)

    fun pagedChannelWindow(groupTitle: String?, offset: Int, limit: Int): List<IptvChannel> =
        runCatching { channelStore.windowForGroup(currentEpgIndexKey, groupTitle, offset, limit) }.getOrDefault(emptyList())

    fun pagedChannelCount(playlistId: String?, groupTitle: String?): Int =
        runCatching { channelStore.countForPlaylistGroup(currentEpgIndexKey, playlistId, groupTitle) }.getOrDefault(0)

    fun pagedChannelWindow(playlistId: String?, groupTitle: String?, offset: Int, limit: Int): List<IptvChannel> =
        runCatching { channelStore.windowForPlaylistGroup(currentEpgIndexKey, playlistId, groupTitle, offset, limit) }.getOrDefault(emptyList())

    fun pagedChannelStoreCount(): Int =
        runCatching { channelStore.count(currentEpgIndexKey) }.getOrDefault(0)

    fun pagedChannelGroupCounts(): List<Pair<String, Int>> =
        runCatching { channelStore.groupCounts(currentEpgIndexKey) }.getOrDefault(emptyList())

    fun pagedPlaylistGroupCounts(): List<Triple<String, String, Int>> =
        runCatching { channelStore.playlistGroupCounts(currentEpgIndexKey) }.getOrDefault(emptyList())

    fun pagedChannelsByIds(ids: Collection<String>): List<IptvChannel> =
        runCatching { channelStore.getByIds(currentEpgIndexKey, ids) }.getOrDefault(emptyList())

    fun pagedChannelIndexOf(groupTitle: String?, channelId: String): Int =
        runCatching { channelStore.indexOfId(currentEpgIndexKey, groupTitle, channelId) }.getOrDefault(-1)

    fun pagedSearchChannels(query: String, limit: Int): List<IptvChannel> =
        runCatching { channelStore.search(currentEpgIndexKey, query, limit) }.getOrDefault(emptyList())

    fun indexedGuideWindow(
        channelIds: Set<String>,
        startMs: Long,
        endMs: Long
    ): Map<String, IptvNowNext> {
        if (channelIds.isEmpty() || startMs >= endMs) return emptyMap()
        val indexKey = currentEpgIndexKey
        if (indexKey.isBlank()) return emptyMap()
        val nowMs = System.currentTimeMillis()
        return runCatching {
            buildMap {
                epgIndex.loadWindow(indexKey, channelIds, startMs, endMs)
                    .forEach { (channelId, programs) ->
                    if (programs.isEmpty()) return@forEach
                    val sorted = programs
                        .asSequence()
                        .filter { it.endUtcMillis > it.startUtcMillis }
                        .distinctBy { "${it.startUtcMillis}|${it.endUtcMillis}|${it.title}" }
                        .sortedBy { it.startUtcMillis }
                        .toList()
                    if (sorted.isEmpty()) return@forEach
                    val now = sorted.lastOrNull { it.isLive(nowMs) }
                    val future = sorted.filter { it.startUtcMillis > nowMs }
                    val recent = sorted.filter { it.endUtcMillis <= nowMs }
                    put(
                        channelId,
                        IptvNowNext(
                            now = now,
                            next = future.getOrNull(0),
                            later = future.getOrNull(1),
                            upcoming = future.take(96),
                            recent = recent.takeLast(96)
                        )
                    )
                }
            }
        }.onFailure { error ->
            System.err.println("[EPG-Index] visible window read failed: ${error.message}")
        }.getOrDefault(emptyMap())
    }

    /**
     * Refreshes short Xtream EPG data for the specified channel IDs.
     *
     * Updates the repository's in-memory `cachedNowNext` entries for channels that have Xtream stream identifiers.
     *
     * @return A map of channel ID to `IptvNowNext` containing the updated EPG entries for those channels, or `null` if Xtream credentials are not available or no EPG data was retrieved.
     */
    suspend fun refreshEpgForChannels(
        channelIds: Set<String>,
        maxChannels: Int = startupShortEpgChannelLimit,
        preferFullCatchupHistory: Boolean = false
    ): Map<String, IptvNowNext>? {
        if (channelIds.isEmpty()) return null
        return withContext(Dispatchers.IO) {
            val config = observeConfig().first()
            val requested = if (maxChannels > 0) {
                channelIds.take(maxChannels).toHashSet()
            } else {
                channelIds
            }
            val channels = channelsForEpgRefresh(requested)
            if (channels.isEmpty()) return@withContext null

            val activePlaylistById = activePlaylists(config).associateBy { it.id }
            val fallbackCreds = resolveXtreamCredentials(config)
            val channelsByCredentials = LinkedHashMap<XtreamCredentials, MutableList<IptvChannel>>()
            for (channel in channels) {
                val playlistId = channel.id.substringBefore(':', missingDelimiterValue = "")
                    .takeIf { it.isNotBlank() }
                val playlistCreds = playlistId
                    ?.let { activePlaylistById[it] }
                    ?.let { resolveXtreamCredentials(it) }
                val creds = playlistCreds ?: fallbackCreds ?: continue
                if (resolveXtreamStreamId(channel) == null) continue
                channelsByCredentials.getOrPut(creds) { mutableListOf() }.add(channel)
            }
            val allChannelsByCredentials = groupXtreamChannelsByCredentials(
                config,
                (cachedChannels + channels).distinctBy { it.id }
            )
            val mergedNowNext = ConcurrentHashMap<String, IptvNowNext>()
            var totalListings = 0
            var totalErrors = 0

            channelsByCredentials.forEach { (creds, providerChannels) ->
                val providerCooldownKey = "${creds.baseUrl}|${creds.username}"
                val nowMs = System.currentTimeMillis()
                val useProviderCooldown = providerChannels.size >= startupShortEpgChannelLimit
                val cooldownUntil = if (useProviderCooldown) emptyShortEpgCooldownUntil[providerCooldownKey] ?: 0L else 0L
                if (cooldownUntil > nowMs && !preferFullCatchupHistory) {
                    System.err.println(
                        "[EPG-Refresh] Skipping empty short EPG provider ${creds.baseUrl} " +
                            "for ${(cooldownUntil - nowMs) / 1000}s"
                    )
                    return@forEach
                }

                // Build lookups for this provider's channels only. Multi-playlist
                // setups may use different Xtream credentials per playlist.
                //
                // Xtream EPG responses are often keyed by epg_channel_id rather
                // than the exact stream_id. When a small visible refresh asks
                // for one variant first (for example NPO 1 4K), fan that guide
                // data out to same-provider channels sharing the same EPG id
                // (for example NPO 1 HD/SD) without collapsing channel rows or
                // mixing playback sources.
                val providerLookupChannels = allChannelsByCredentials[creds]
                    ?.takeIf { it.isNotEmpty() }
                    ?.let { allProviderChannels ->
                        scopedProviderGuideLookupChannels(providerChannels, allProviderChannels)
                    }
                    ?: providerChannels
                val epgIdToChannelIds = mutableMapOf<String, MutableList<String>>()
                val streamIdToChannelIds = mutableMapOf<String, MutableList<String>>()
                for (ch in providerLookupChannels) {
                    ch.epgId?.let { eid ->
                        addChannelIdToLookup(epgIdToChannelIds, eid, ch.id)
                    }
                    ch.tvgName?.let { tvg ->
                        addChannelIdToLookup(epgIdToChannelIds, tvg, ch.id)
                    }
                    ch.variantKey?.let { key ->
                        addChannelIdToLookup(epgIdToChannelIds, key, ch.id)
                    }
                    resolveXtreamStreamId(ch)?.let { sid ->
                        streamIdToChannelIds.getOrPut(sid.toString()) { mutableListOf() }.add(ch.id)
                    }
                }

                System.err.println(
                    "[EPG-Refresh] Fetching short EPG for ${providerChannels.size} " +
                        "requested channels from ${creds.baseUrl}; sample=${describeEpgChannels(providerChannels)}"
                )

                val streamIds = providerChannels.mapNotNull { resolveXtreamStreamId(it) }
                var errors = 0
                val shouldUseFullCatchupHistory = preferFullCatchupHistory &&
                    providerChannels.size <= fullCatchupHistoryChannelLimit
                val fullListings = if (shouldUseFullCatchupHistory) {
                    fetchXtreamFullEpgListingsAsync(
                        creds = creds,
                        streamIds = streamIds,
                        timeoutMillis = xtreamFullCatchupEpgTimeout(streamIds.size)
                    ) { _, hadError ->
                        if (hadError) errors++
                    }
                } else {
                    emptyList()
                }
                val allListings = fullListings.ifEmpty {
                    fetchXtreamEpgListingsAsync(
                        creds = creds,
                        streamIds = streamIds,
                        timeoutMillis = xtreamShortEpgTimeout(streamIds.size),
                        listingLimit = if (providerChannels.size <= 128) {
                            xtreamVisibleShortEpgLimit
                        } else {
                            xtreamShortEpgLimit
                        },
                        allowUnboundedFallback = providerChannels.size > 256 || preferFullCatchupHistory
                    ) { _, hadError ->
                        if (hadError) errors++
                    }
                }
                totalListings += allListings.size
                totalErrors += errors
                System.err.println(
                    "[EPG-Refresh] Provider done: ${allListings.size} listings, $errors errors " +
                        "fullCatchup=$shouldUseFullCatchupHistory"
                )

                if (allListings.isEmpty()) {
                    if (errors == 0 && !preferFullCatchupHistory && useProviderCooldown) {
                        emptyShortEpgCooldownUntil[providerCooldownKey] =
                            System.currentTimeMillis() + 10 * 60_000L
                    }
                    return@forEach
                }
                emptyShortEpgCooldownUntil.remove(providerCooldownKey)

                val freshNowNext = buildNowNextFromXtreamListings(
                    creds = creds,
                    listings = allListings,
                    epgIdToChannelIds = epgIdToChannelIds,
                    streamIdToChannelIds = streamIdToChannelIds,
                    channelsById = providerLookupChannels.associateBy { it.id },
                    forceCatchupHistory = preferFullCatchupHistory
                )
                if (freshNowNext.isNotEmpty()) {
                    mergedNowNext.putAll(freshNowNext)
                }
            }
            val missingXmlChannels = channels.filter { channel ->
                !hasProgramData(mergedNowNext[channel.id])
            }
            val hasXtreamRequestedChannels = channelsByCredentials.isNotEmpty()
            var xmlFallback: Map<String, IptvNowNext> = emptyMap()
            var isXmlCached = false
            if (missingXmlChannels.isNotEmpty() && !preferFullCatchupHistory && !hasXtreamRequestedChannels) {
                val result = fetchVisibleXmlEpgForChannels(config, missingXmlChannels)
                xmlFallback = result.first
                isXmlCached = result.second
                if (xmlFallback.isNotEmpty()) {
                    mergedNowNext.putAll(xmlFallback)
                    System.err.println(
                        "[EPG-Refresh] XMLTV visible fallback added ${xmlFallback.size} channels"
                    )
                }
            } else if (missingXmlChannels.isNotEmpty() && hasXtreamRequestedChannels) {
                System.err.println(
                    "[EPG-Refresh] Skipping XMLTV visible fallback for Xtream channels; " +
                        "missing=${missingXmlChannels.size}"
                )
            }
            if (mergedNowNext.isEmpty()) return@withContext null

            // Merge into cache (in-place, no copy). Visible short EPG refreshes
            // only carry now/next/later slices, so replacing the whole entry
            // would wipe the richer catch-up history loaded for archive rows.
            val mergedForCache = mergedNowNext.mapValues { (channelId, fresh) ->
                mergeCachedGuideSlice(cachedNowNext[channelId], fresh)
            }
            cachedNowNext.putAll(mergedForCache)
            cachedEpgAt = System.currentTimeMillis()

            val toPersist = if (isXmlCached && xmlFallback.isNotEmpty()) {
                mergedForCache.filterKeys { it !in xmlFallback.keys }
            } else {
                mergedForCache
            }
            if (toPersist.isNotEmpty()) {
                persistEpgIndexChannels(config, toPersist, cachedEpgAt)
            }

            System.err.println(
                "[EPG-Refresh] Updated ${mergedForCache.size} channels in cache " +
                    "from $totalListings listings, $totalErrors errors"
            )
            mergedForCache
        }
    }

    private fun describeEpgChannels(channels: List<IptvChannel>, limit: Int = 4): String {
        if (channels.isEmpty()) return "[]"
        return channels
            .asSequence()
            .take(limit)
            .joinToString(prefix = "[", postfix = if (channels.size > limit) ", ...]" else "]") { channel ->
                val streamId = resolveXtreamStreamId(channel)?.toString().orEmpty()
                val epg = channel.epgId.orEmpty().take(32)
                val name = channel.name.replace('\n', ' ').take(36)
                "{id=${channel.id.take(48)}, stream=$streamId, epg=$epg, name=$name}"
            }
    }

    private suspend fun fetchVisibleXmlEpgForChannels(
        config: IptvConfig,
        channels: List<IptvChannel>
    ): Pair<Map<String, IptvNowNext>, Boolean> {
        if (channels.isEmpty()) return Pair(emptyMap(), false)
        val candidates = resolveScopedEpgCandidates(config)
        if (candidates.isEmpty()) return Pair(emptyMap(), false)

        val playlistKey = channels
            .asSequence()
            .map { it.id.substringBefore(':', missingDelimiterValue = "") }
            .filter { it.isNotBlank() }
            .distinct()
            .take(4)
            .joinToString(",")
        val channelKey = channels
            .asSequence()
            .map { it.id }
            .filter { it.isNotBlank() }
            .take(24)
            .joinToString(",")
            .hashCode()
        val cooldownKey = "${currentEpgIndexKey(config)}|$playlistKey|$channelKey|visible_xml"
        val nowMs = System.currentTimeMillis()
        val cooldownUntil = visibleXmlEpgCooldownUntil[cooldownKey] ?: 0L
        if (cooldownUntil > nowMs) {
            System.err.println(
                "[EPG-Refresh] Skipping XMLTV visible fallback for ${(cooldownUntil - nowMs) / 1000}s"
            )
            return Pair(emptyMap(), false)
        }
        visibleXmlEpgCooldownUntil[cooldownKey] = nowMs + 90_000L

        val visibleCandidates = candidates.take(2)
        for ((index, candidate) in visibleCandidates.withIndex()) {
            val candidateChannels = channelsForScopedEpgCandidate(candidate, channels)
            if (candidateChannels.isEmpty()) continue
            System.err.println(
                "[EPG-Refresh] XMLTV visible fallback ${index + 1}/${visibleCandidates.size} " +
                    "for ${candidateChannels.size} channels"
            )
            var isCached = false
            val parsed = runCatching {
                withTimeoutOrNull(12_000L) {
                    fetchAndParseEpg(candidate.url, candidateChannels)
                } ?: emptyMap()
            }.recover { error ->
                if (error is EpgNotModifiedException) {
                    System.err.println("[EPG-Refresh] XMLTV visible fallback candidate is unchanged (HTTP 304). Loading existing index...")
                    isCached = true
                    runCatching {
                        epgIndex.loadNowNext(
                            sourceKey = currentEpgIndexKey(config),
                            channelIds = candidateChannels.map { it.id }.toSet()
                        )
                    }.getOrDefault(emptyMap())
                } else {
                    throw error
                }
            }.onFailure { error ->
                System.err.println("[EPG-Refresh] XMLTV visible fallback failed: ${error.message}")
            }.getOrDefault(emptyMap())

            if (parsed.isNotEmpty() && hasAnyProgramData(parsed)) {
                visibleXmlEpgCooldownUntil[cooldownKey] = System.currentTimeMillis() + 30_000L
                return Pair(parsed, isCached)
            }
        }

        visibleXmlEpgCooldownUntil[cooldownKey] = System.currentTimeMillis() + 5 * 60_000L
        return Pair(emptyMap(), false)
    }

    private fun persistCurrentCacheSnapshot(config: IptvConfig, loadedAtMs: Long = System.currentTimeMillis()) {
        val channels = cachedChannels
        if (channels.isEmpty()) return
        val nowNext = cachedNowNext
        writeCache(
            config = config,
            channels = channels,
            nowNext = nowNext,
            loadedAtMs = loadedAtMs.coerceAtLeast(cachedPlaylistAt.coerceAtLeast(loadedAtMs))
        )
    }

    private fun persistEpgIndexAll(
        config: IptvConfig,
        nowNext: Map<String, IptvNowNext>,
        updatedAtMs: Long = System.currentTimeMillis()
    ) {
        if (!hasAnyProgramData(nowNext)) return
        runCatching {
            epgIndex.replaceAll(currentEpgIndexKey(config), nowNext, updatedAtMs)
        }.onFailure { error ->
            System.err.println("[EPG-Index] Failed to replace guide index: ${error.message}")
        }
    }

    private fun persistEpgIndexChannels(
        config: IptvConfig,
        nowNext: Map<String, IptvNowNext>,
        updatedAtMs: Long = System.currentTimeMillis()
    ) {
        if (!hasAnyProgramData(nowNext)) return
        runCatching {
            epgIndex.replaceChannels(currentEpgIndexKey(config), nowNext, updatedAtMs)
        }.onFailure { error ->
            System.err.println("[EPG-Index] Failed to update guide index: ${error.message}")
        }
    }

    private fun countIndexedGuideChannels(): Int {
        val indexKey = currentEpgIndexKey
        if (indexKey.isBlank()) return 0
        return runCatching { epgIndex.countChannelsWithPrograms(indexKey) }.getOrDefault(0)
    }

    private fun countIndexedGuidePrograms(): Int {
        val indexKey = currentEpgIndexKey
        if (indexKey.isBlank()) return 0
        return runCatching { epgIndex.countPrograms(indexKey) }.getOrDefault(0)
    }

    private suspend fun fetchFreshChannelsForStartup(config: IptvConfig): Pair<List<IptvChannel>, com.arflix.tv.data.api.StalkerApi?>? {
        val activeLists = activePlaylists(config)
        if (activeLists.isEmpty() && config.stalkerPortalUrl.isBlank()) return null

        if (config.m3uUrl.isBlank() && config.stalkerPortalUrl.isNotBlank()) {
            val stalker = com.arflix.tv.data.api.StalkerApi(config.stalkerPortalUrl, config.stalkerMacAddress)
            if (!stalker.handshake()) return null
            stalker.getProfile()
            val channels = stalker.getChannels()
            return if (channels.isNotEmpty()) channels to stalker else null
        }

        val channels = coroutineScope {
            activeLists.map { playlist ->
                async {
                    fetchChannelsForPlaylistWithRetries(playlist) { }
                        .map { channel ->
                            channel.copy(
                                id = "${playlist.id}:${channel.id}",
                                group = channel.group
                            )
                        }
                }
            }.awaitAll().flatten()
        }
        return if (channels.isNotEmpty()) channels to null else null
    }

    private suspend fun storeStartupChannels(
        config: IptvConfig,
        channels: List<IptvChannel>,
        stalkerApi: com.arflix.tv.data.api.StalkerApi?
    ) {
        loadMutex.withLock {
            cachedChannels = channels
            cachedGroupedChannels = buildGroupedChannels(channels)
            cachedPlaylistAt = System.currentTimeMillis()
            if (stalkerApi != null) {
                cachedStalkerApi = stalkerApi
            }
            val validIds = channels.asSequence().map { it.id }.toSet()
            if (cachedNowNext.isNotEmpty()) {
                cachedNowNext.keys.retainAll(validIds)
            }
            persistCurrentCacheSnapshot(config, cachedPlaylistAt)
        }
    }

    suspend fun prefetchFreshStartupData() {
        if (!startupPrefetchInFlight.compareAndSet(false, true)) return
        try {
            warmupFromCacheOnly()
            val config = observeConfig().first()
            if (!hasAnyConfiguredSource(config)) return

            val cached = getMemoryCachedSnapshot() ?: getCachedSnapshotOrNull()
            if (cached == null || cached.channels.isEmpty()) {
                val fresh = withTimeoutOrNull(25_000L) {
                    fetchFreshChannelsForStartup(config)
                }
                if (fresh != null) {
                    storeStartupChannels(config, fresh.first, fresh.second)
                }
                return
            }
        } finally {
            startupPrefetchInFlight.set(false)
        }
    }

    fun invalidateCache() {
        cachedChannels = emptyList()
        cachedChannelsLookupSource = null
        cachedChannelsById = emptyMap()
        cachedGroupedChannels = emptyMap()
        cachedNowNext = ConcurrentHashMap()
        cachedPlaylistAt = 0L
        cachedEpgAt = 0L
        discoveredM3uEpgUrls.clear()
        xtreamVodCacheKey = null
        xtreamVodLoadedAtMs = 0L
        xtreamSeriesLoadedAtMs = 0L
        cachedXtreamVodStreams = emptyList()
        cachedXtreamSeries = emptyList()
        cachedXtreamSeriesEpisodes = emptyMap()
        xtreamSeriesEpisodeInFlight = emptyMap()
        cacheOwnerProfileId = null
        cacheOwnerConfigSig = null
        currentEpgIndexKey = ""
        // Drop derived per-creds caches that depend on the catalogs above.
        cachedXtreamVodCategories = emptyList()
        cachedXtreamSeriesCategories = emptyList()
        xtreamVodCategoriesLoadedAtMs = 0L
        xtreamSeriesCategoriesLoadedAtMs = 0L
        cachedVodIndex = null
        cachedVodIdIndex = null
        clearIptvMovieSourceCache()
        // Keep disk VOD/series catalogs. They are credential-keyed and TTL checked;
        // deleting them during a generic refresh can race with playback source resolution.
    }

    /**
     * Hard reset of every IPTV-side cache: in-memory state, resolver memory,
     * persisted resolver SharedPrefs, persisted movie-source cache, and the
     * on-disk Xtream VOD/series/category catalogs. Use this when the user
     * explicitly asks for a full refresh — the next resolve will go all the
     * way back to the provider.
     *
     * Unlike [invalidateCache], this also deletes the disk catalogs so that
     * [warmXtreamVodCachesIfPossible] is guaranteed to re-fetch from network.
     */
    fun purgeAllIptvSourceCaches() {
        invalidateCache()
        runCatching { seriesResolver.clearAll() }
        runCatching {
            xtreamDiskCacheDir().listFiles()?.forEach { it.delete() }
        }
    }

    private fun ensureCacheOwnership(profileId: String, config: IptvConfig) {
        val sig = buildSourceSignature(config)
        val ownerChanged = cacheOwnerProfileId != null && cacheOwnerProfileId != profileId
        val configChanged = cacheOwnerConfigSig != null && cacheOwnerConfigSig != sig
        if (ownerChanged || configChanged) {
            invalidateCache()
        }
        cacheOwnerProfileId = profileId
        cacheOwnerConfigSig = sig
        currentEpgIndexKey = epgIndexKey(profileId, config)
    }

    private fun m3uUrlKey(): Preferences.Key<String> = profileManager.profileStringKey("iptv_m3u_url")
    private fun m3uUrlKeyFor(profileId: String): Preferences.Key<String> =
        profileManager.profileStringKeyFor(profileId, "iptv_m3u_url")
    private fun epgUrlKey(): Preferences.Key<String> = profileManager.profileStringKey("iptv_epg_url")
    private fun playlistsKey(): Preferences.Key<String> = profileManager.profileStringKey("iptv_playlists_json")
    private fun stalkerPortalUrlKey(): Preferences.Key<String> = profileManager.profileStringKey("iptv_stalker_portal_url")
    private fun stalkerMacAddressKey(): Preferences.Key<String> = profileManager.profileStringKey("iptv_stalker_mac_address")
    private fun epgUrlKeyFor(profileId: String): Preferences.Key<String> =
        profileManager.profileStringKeyFor(profileId, "iptv_epg_url")
    private fun favoriteGroupsKey(): Preferences.Key<String> = profileManager.profileStringKey("iptv_favorite_groups")
    private fun favoriteGroupsKeyFor(profileId: String): Preferences.Key<String> =
        profileManager.profileStringKeyFor(profileId, "iptv_favorite_groups")
    private fun favoriteChannelsKey(): Preferences.Key<String> = profileManager.profileStringKey("iptv_favorite_channels")
    private fun favoriteChannelsKeyFor(profileId: String): Preferences.Key<String> =
        profileManager.profileStringKeyFor(profileId, "iptv_favorite_channels")

    private fun decodeFavoriteGroups(prefs: Preferences): List<String> {
        val raw = prefs[favoriteGroupsKey()].orEmpty()
        if (raw.isBlank()) return emptyList()
        return runCatching {
            val type = TypeToken.getParameterized(List::class.java, String::class.java).type
            gson.fromJson<List<String>>(raw, type)
                ?.map { it.trim() }
                ?.filter { it.isNotBlank() }
                ?.distinct()
                ?: emptyList()
        }.getOrDefault(emptyList())
    }

    private fun decodePlaylists(raw: String): List<IptvPlaylistEntry> {
        if (raw.isBlank()) return emptyList()
        return runCatching {
            val type = TypeToken.getParameterized(List::class.java, IptvPlaylistEntry::class.java).type
            gson.fromJson<List<IptvPlaylistEntry>>(raw, type)
                ?.mapIndexed { index, playlist ->
                    normalizePlaylistEntry(playlist, index)
                }
                ?.filterNotNull()
                ?: emptyList()
        }.getOrDefault(emptyList())
    }

    private fun hiddenGroupsKey(): Preferences.Key<String> = profileManager.profileStringKey("iptv_hidden_groups")
    private fun hiddenGroupsKeyFor(profileId: String): Preferences.Key<String> =
        profileManager.profileStringKeyFor(profileId, "iptv_hidden_groups")
    private fun groupOrderKey(): Preferences.Key<String> = profileManager.profileStringKey("iptv_group_order")
    private fun groupOrderKeyFor(profileId: String): Preferences.Key<String> =
        profileManager.profileStringKeyFor(profileId, "iptv_group_order")
    private fun playlistsKeyFor(profileId: String): Preferences.Key<String> =
        profileManager.profileStringKeyFor(profileId, "iptv_playlists_json")
    private fun tvSessionKey(): Preferences.Key<String> = profileManager.profileStringKey("iptv_tv_session")
    private fun tvSessionKeyFor(profileId: String): Preferences.Key<String> =
        profileManager.profileStringKeyFor(profileId, "iptv_tv_session")

    private fun decodeHiddenGroups(prefs: Preferences): List<String> {
        val raw = prefs[hiddenGroupsKey()].orEmpty()
        if (raw.isBlank()) return emptyList()
        return runCatching {
            val type = TypeToken.getParameterized(List::class.java, String::class.java).type
            val list = gson.fromJson<List<String>>(raw, type)?.map { it.trim() }?.filter { it.isNotBlank() }?.distinct() ?: emptyList()
            if (list.any { !it.contains('|') }) {
                val playlistsRaw = prefs[playlistsKey()].orEmpty()
                if (playlistsRaw.isBlank()) {
                    list
                } else {
                    val playlists = decodePlaylists(playlistsRaw)
                    val firstId = playlists.firstOrNull()?.id
                    if (firstId != null) {
                        list.map { if (it.contains('|')) it else "$firstId|$it" }.distinct()
                    } else list
                }
            } else list
        }.getOrDefault(emptyList())
    }

    private fun decodeGroupOrder(prefs: Preferences): List<String> {
        val raw = prefs[groupOrderKey()].orEmpty()
        if (raw.isBlank()) return emptyList()
        return runCatching {
            val type = TypeToken.getParameterized(List::class.java, String::class.java).type
            val list = gson.fromJson<List<String>>(raw, type)?.map { it.trim() }?.filter { it.isNotBlank() }?.distinct() ?: emptyList()
            if (list.any { !it.contains('|') }) {
                val playlistsRaw = prefs[playlistsKey()].orEmpty()
                if (playlistsRaw.isBlank()) {
                    list
                } else {
                    val playlists = decodePlaylists(playlistsRaw)
                    val firstId = playlists.firstOrNull()?.id
                    if (firstId != null) {
                        list.map { if (it.contains('|')) it else "$firstId|$it" }.distinct()
                    } else list
                }
            } else list
        }.getOrDefault(emptyList())
    }

    private fun mergedGroupOrder(savedOrder: List<String>, currentGroups: List<String>): MutableList<String> {
        val current = currentGroups
            .map { it.trim() }
            .filter { it.isNotBlank() }
            .distinct()
        val currentSet = current.toHashSet()
        val merged = savedOrder
            .map { it.trim() }
            .filter { it.isNotBlank() && (currentSet.isEmpty() || it in currentSet) }
            .distinct()
            .toMutableList()
        current.forEach { group ->
            if (group !in merged) merged.add(group)
        }
        return merged
    }

    private fun decodeFavoriteChannels(prefs: Preferences): List<String> {
        val raw = prefs[favoriteChannelsKey()].orEmpty()
        if (raw.isBlank()) return emptyList()
        return runCatching {
            val type = TypeToken.getParameterized(List::class.java, String::class.java).type
            gson.fromJson<List<String>>(raw, type)
                ?.map { it.trim() }
                ?.filter { it.isNotBlank() }
                ?.distinct()
                ?: emptyList()
        }.getOrDefault(emptyList())
    }

    private fun decodeTvSessionState(prefs: Preferences): IptvTvSessionState =
        decodeTvSessionState(prefs[tvSessionKey()].orEmpty())

    private fun decodeTvSessionState(raw: String): IptvTvSessionState {
        if (raw.isBlank()) return IptvTvSessionState()
        return runCatching {
            gson.fromJson(raw, IptvTvSessionState::class.java)?.let { session ->
                session.copy(
                    lastChannelId = session.lastChannelId.trim(),
                    lastGroupName = session.lastGroupName.trim(),
                    lastFocusedZone = session.lastFocusedZone.trim().ifBlank { "GUIDE" },
                    recentChannelIds = runCatching { session.recentChannelIds }
                        .getOrNull()
                        .orEmpty()
                        .map { it.trim() }
                        .filter { it.isNotBlank() }
                        .distinct()
                        .takeLast(40)
                )
            } ?: IptvTvSessionState()
        }.getOrDefault(IptvTvSessionState())
    }

    private fun decodeFavoriteGroups(raw: String): List<String> {
        if (raw.isBlank()) return emptyList()
        return runCatching {
            val type = TypeToken.getParameterized(List::class.java, String::class.java).type
            gson.fromJson<List<String>>(raw, type)
                ?.map { it.trim() }
                ?.filter { it.isNotBlank() }
                ?.distinct()
                ?: emptyList()
        }.getOrDefault(emptyList())
    }

    private fun decodeFavoriteChannels(raw: String): List<String> {
        if (raw.isBlank()) return emptyList()
        return runCatching {
            val type = TypeToken.getParameterized(List::class.java, String::class.java).type
            gson.fromJson<List<String>>(raw, type)
                ?.map { it.trim() }
                ?.filter { it.isNotBlank() }
                ?.distinct()
                ?: emptyList()
        }.getOrDefault(emptyList())
    }

    suspend fun exportCloudConfigForProfile(profileId: String): IptvCloudProfileState {
        val safeProfileId = profileId.trim().ifBlank { "default" }
        val prefs = context.settingsDataStore.data.first()
        val hiddenRaw = prefs[hiddenGroupsKeyFor(safeProfileId)].orEmpty()
        val orderRaw = prefs[groupOrderKeyFor(safeProfileId)].orEmpty()
        val playlistsRaw = prefs[playlistsKeyFor(safeProfileId)].orEmpty()
        val tvSessionRaw = prefs[tvSessionKeyFor(safeProfileId)].orEmpty()
        val playlists = decodePlaylists(playlistsRaw)
        val primary = playlists.firstOrNull()
        val legacyM3uUrl = normalizeStoredIptvUrl(decryptConfigValue(prefs[m3uUrlKeyFor(safeProfileId)].orEmpty()))
        val legacyEpgUrls = normalizeStoredEpgInputs(decryptConfigValue(prefs[epgUrlKeyFor(safeProfileId)].orEmpty()))
        return IptvCloudProfileState(
            m3uUrl = primary?.m3uUrl ?: legacyM3uUrl,
            epgUrl = primary?.epgUrl ?: legacyEpgUrls.firstOrNull().orEmpty(),
            favoriteGroups = decodeFavoriteGroups(prefs[favoriteGroupsKeyFor(safeProfileId)].orEmpty()),
            favoriteChannels = decodeFavoriteChannels(prefs[favoriteChannelsKeyFor(safeProfileId)].orEmpty()),
            hiddenGroups = if (hiddenRaw.isNotBlank()) {
                runCatching {
                    val type = TypeToken.getParameterized(List::class.java, String::class.java).type
                    gson.fromJson<List<String>>(hiddenRaw, type) ?: emptyList()
                }.getOrDefault(emptyList())
            } else emptyList(),
            groupOrder = if (orderRaw.isNotBlank()) {
                runCatching {
                    val type = TypeToken.getParameterized(List::class.java, String::class.java).type
                    gson.fromJson<List<String>>(orderRaw, type) ?: emptyList()
                }.getOrDefault(emptyList())
            } else emptyList(),
            playlists = playlists,
            tvSession = decodeTvSessionState(tvSessionRaw)
        )
    }

    suspend fun importCloudConfigForProfile(profileId: String, state: IptvCloudProfileState) {
        val safeProfileId = profileId.trim().ifBlank { "default" }
        val normalizedM3u = normalizeStoredIptvUrl(state.m3uUrl)
        val normalizedEpgUrls = normalizeStoredEpgInputs(state.epgUrl)
        val normalizedEpg = normalizedEpgUrls.firstOrNull().orEmpty()
        val normalizedPlaylists = state.playlists.mapIndexed { index, playlist ->
            normalizePlaylistEntry(playlist, index)
        }.filterNotNull().take(3)
        context.settingsDataStore.edit { prefs ->
            prefs[m3uUrlKeyFor(safeProfileId)] = encryptConfigValue(normalizedM3u)
            prefs[epgUrlKeyFor(safeProfileId)] = encryptConfigValue(normalizedEpg)
            prefs[favoriteGroupsKeyFor(safeProfileId)] = gson.toJson(state.favoriteGroups.distinct())
            prefs[favoriteChannelsKeyFor(safeProfileId)] = gson.toJson(state.favoriteChannels.distinct())
            if (state.hiddenGroups.isNotEmpty()) {
                prefs[hiddenGroupsKeyFor(safeProfileId)] = gson.toJson(state.hiddenGroups.distinct())
            }
            if (state.groupOrder.isNotEmpty()) {
                prefs[groupOrderKeyFor(safeProfileId)] = gson.toJson(state.groupOrder.distinct())
            }
            if (normalizedPlaylists.isNotEmpty()) {
                prefs[playlistsKeyFor(safeProfileId)] = gson.toJson(normalizedPlaylists)
            }
            if (state.tvSession != IptvTvSessionState()) {
                prefs[tvSessionKeyFor(safeProfileId)] = gson.toJson(
                    state.tvSession.copy(
                        lastChannelId = state.tvSession.lastChannelId.trim(),
                        lastGroupName = state.tvSession.lastGroupName.trim(),
                        lastFocusedZone = state.tvSession.lastFocusedZone.trim().ifBlank { "GUIDE" },
                        recentChannelIds = state.tvSession.recentChannelIds
                            .map { it.trim() }
                            .filter { it.isNotBlank() }
                            .distinct()
                            .takeLast(40)
                    )
                )
            } else {
                prefs.remove(tvSessionKeyFor(safeProfileId))
            }
        }
        groupOrderLocallyDirty = false
        if (profileManager.getProfileIdSync() == safeProfileId) {
            invalidateCache()
        }
    }

    private suspend fun fetchChannelsForPlaylistWithRetries(
        playlist: IptvPlaylistEntry,
        onProgress: (IptvLoadProgress) -> Unit
    ): List<IptvChannel> {
        resolveXtreamCredentials(playlist)?.let { creds ->
            onProgress(IptvLoadProgress(context.getString(R.string.iptv_xtream_detected), 6))
            runCatching {
                withTimeoutOrNull(120_000L) {
                    fetchXtreamLiveChannels(creds, onProgress)
                } ?: throw IllegalStateException(context.getString(R.string.iptv_xtream_timeout))
            }
                .onSuccess { channels ->
                    if (channels.isNotEmpty()) {
                        onProgress(IptvLoadProgress(context.getString(R.string.iptv_loaded_api, channels.size), 95))
                        return channels
                    }
                }
        }
        return fetchAndParseM3uWithRetries(playlist.m3uUrl, onProgress)
    }

    private suspend fun fetchAndParseM3uWithRetries(
        url: String,
        onProgress: (IptvLoadProgress) -> Unit
    ): List<IptvChannel> {
        val normalizedUrl = normalizeStoredIptvUrl(url)
        resolveXtreamCredentials(normalizedUrl)?.let { creds ->
            onProgress(IptvLoadProgress(context.getString(R.string.iptv_xtream_detected), 6))
            runCatching {
                withTimeoutOrNull(120_000L) {
                    fetchXtreamLiveChannels(creds, onProgress)
                } ?: throw IllegalStateException(context.getString(R.string.iptv_xtream_timeout))
            }
                .onSuccess { channels ->
                    if (channels.isNotEmpty()) {
                        onProgress(IptvLoadProgress(context.getString(R.string.iptv_loaded_api, channels.size), 95))
                        return channels
                    }
                }
        }

        var lastError: Throwable? = null
        val maxAttempts = 2
        repeat(maxAttempts) { attempt ->
            onProgress(IptvLoadProgress("Connecting to playlist (attempt ${attempt + 1}/$maxAttempts)...", 5))
            runCatching {
                withTimeoutOrNull(90_000L) {
                    fetchAndParseM3uOnce(normalizedUrl, onProgress)
                } ?: throw IllegalStateException(context.getString(R.string.iptv_playlist_timeout))
            }.onSuccess { channels ->
                if (channels.isNotEmpty()) return channels
                lastError = IllegalStateException(context.getString(R.string.iptv_no_channels))
            }.onFailure { error ->
                lastError = error
            }

            if (attempt < maxAttempts - 1) {
                val backoffMs = (1_000L * (attempt + 1)).coerceAtMost(2_000L)
                onProgress(IptvLoadProgress("Retrying in ${backoffMs / 1000}s...", 5))
                delay(backoffMs)
            }
        }
        throw (lastError ?: IllegalStateException(context.getString(R.string.iptv_failed_load_m3u)))
    }

    private data class XtreamCredentials(
        val baseUrl: String,
        val username: String,
        val password: String
    )

    private data class XtreamLiveCategory(
        @SerializedName("category_id") val categoryId: String? = null,
        @SerializedName("category_name") val categoryName: String? = null
    )

    private data class XtreamLiveStream(
        @SerializedName("stream_id") val streamId: Int? = null,
        val name: String? = null,
        @SerializedName("stream_icon") val streamIcon: String? = null,
        @SerializedName("epg_channel_id") val epgChannelId: String? = null,
        @SerializedName("category_id") val categoryId: String? = null,
        @SerializedName("tv_archive") val tvArchive: Int? = null,
        @SerializedName("tv_archive_duration") val tvArchiveDuration: Int? = null
    )

    private data class XtreamVodStream(
        @SerializedName("stream_id") val streamId: Int? = null,
        val name: String? = null,
        val year: String? = null,
        @SerializedName("container_extension") val containerExtension: String? = null,
        @SerializedName(value = "imdb", alternate = ["imdb_id", "imdbid"]) val imdb: String? = null,
        @SerializedName(value = "tmdb", alternate = ["tmdb_id", "tmdbid"]) val tmdb: String? = null,
        @SerializedName("category_id") val categoryId: String? = null
    )

    private data class XtreamSeriesItem(
        @SerializedName(value = "series_id", alternate = ["seriesid", "id"]) val seriesId: Int? = null,
        val name: String? = null,
        @SerializedName(value = "imdb", alternate = ["imdb_id", "imdbid"]) val imdb: String? = null,
        @SerializedName(value = "tmdb", alternate = ["tmdb_id", "tmdbid"]) val tmdb: String? = null,
        @SerializedName("category_id") val categoryId: String? = null
    )

    private data class XtreamSeriesEpisode(
        val id: Int,
        val season: Int,
        val episode: Int,
        val title: String,
        val containerExtension: String?
    )

    private data class ResolverSeriesEntry(
        val seriesId: Int,
        val name: String = "",
        val normalizedName: String = "",
        val canonicalTitleKey: String = "",
        val titleTokens: Set<String> = emptySet(),
        val tmdb: String?,
        val imdb: String?,
        val year: Int?
    )

    private data class ResolverCatalogIndex(
        val createdAtMs: Long,
        val entries: List<ResolverSeriesEntry>,
        val tmdbMap: Map<String, List<ResolverSeriesEntry>>,
        val imdbMap: Map<String, List<ResolverSeriesEntry>>,
        val canonicalTitleMap: Map<String, List<ResolverSeriesEntry>>,
        val tokenMap: Map<String, List<ResolverSeriesEntry>>
    )

    private data class ResolverCandidate(
        val entry: ResolverSeriesEntry,
        val confidence: Float,
        val method: String,
        val baseScore: Int
    )

    private data class ResolverEpisodeHit(
        val episode: XtreamSeriesEpisode,
        val score: Int
    )

    private data class ResolverCachedResolvedEpisode(
        val streamId: Int,
        val containerExtension: String?,
        val seriesId: Int,
        val confidence: Float,
        val method: String,
        val title: String? = null,
        val savedAtMs: Long
    )

    private data class ResolverPersistedCatalog(
        val createdAtMs: Long = 0L,
        val entries: List<ResolverSeriesEntry> = emptyList()
    )

    private data class ResolverPersistedResolved(
        val items: Map<String, ResolverCachedResolvedEpisode> = emptyMap()
    )

    private data class ResolverPersistedSeriesInfo(
        val savedAtMs: Long = 0L,
        val episodes: List<XtreamSeriesEpisode> = emptyList()
    )

    private data class ResolverPersistedSeriesBindings(
        val items: Map<String, List<Int>> = emptyMap()
    )

    private inner class IptvSeriesResolverService {
        private val prefs by lazy { context.getSharedPreferences("iptv_series_resolver_cache_v1", Context.MODE_PRIVATE) }
        private val catalogLoadMutex = Mutex()
        private val catalogTtlMs = 24 * 60 * 60_000L
        private val resolvedTtlMs = 24 * 60 * 60_000L
        private val seriesInfoTtlMs = 24 * 60 * 60_000L
        private val catalogMemory = ConcurrentHashMap<String, ResolverCatalogIndex>()
        private val resolvedMemory = object : LinkedHashMap<String, ResolverCachedResolvedEpisode>(512, 0.75f, true) {
            override fun removeEldestEntry(eldest: MutableMap.MutableEntry<String, ResolverCachedResolvedEpisode>?): Boolean {
                return size > 512
            }
        }
        private val resolvedLock = Any()
        // Multi-binding: a single show name/ID can match multiple series entries
        // in the provider catalog (different qualities, dub variants, etc).
        // Storing the full list lets the picker show every resolved variant.
        private val seriesBindingMemory = object : LinkedHashMap<String, List<Int>>(2048, 0.75f, true) {
            override fun removeEldestEntry(eldest: MutableMap.MutableEntry<String, List<Int>>?): Boolean {
                return size > 2048
            }
        }
        private val maxSeriesBindingsPerKey = 8
        private val seriesBindingLock = Any()
        private val seriesInfoMemory = object : LinkedHashMap<String, List<XtreamSeriesEpisode>>(50, 0.75f, true) {
            override fun removeEldestEntry(eldest: MutableMap.MutableEntry<String, List<XtreamSeriesEpisode>>?): Boolean {
                return size > 50
            }
        }
        private val seriesInfoLock = Any()

        private fun catalogPrefKey(providerKey: String): String = "catalog_${providerKey.hashCode()}"
        private val resolvedPrefKey = "resolved_episode_map"
        // v2: stores List<Int> per binding key instead of single Int.
        // Bumping the key avoids parsing failures against the legacy single-id format.
        private val seriesBindingPrefKey = "series_binding_map_v2"
        private fun seriesInfoPrefKey(providerKey: String, seriesId: Int): String =
            "series_info_${(providerKey + "|" + seriesId).hashCode()}"

        suspend fun refreshCatalog(
            providerKey: String,
            creds: XtreamCredentials
        ) {
            loadCatalog(providerKey, creds, allowNetwork = true, forceRefresh = true)
        }

        suspend fun prefetchSeriesInfo(
            providerKey: String,
            creds: XtreamCredentials,
            showTitle: String,
            tmdbId: Int?,
            imdbId: String?,
            year: Int?
        ) {
            val normalizedShow = normalizeLookupText(showTitle)
            val normalizedTmdb = normalizeTmdbId(tmdbId)
            val normalizedImdb = normalizeImdbId(imdbId)
            if (normalizedShow.isBlank() && normalizedTmdb.isNullOrBlank() && normalizedImdb.isNullOrBlank()) return

            val catalog = loadCatalog(providerKey, creds, allowNetwork = true, forceRefresh = false)
            if (catalog.entries.isEmpty()) return
            val candidates = buildCandidates(catalog, normalizedShow, normalizedTmdb, normalizedImdb, year)
            if (candidates.isEmpty()) return

            val probeList = if (candidates.first().confidence >= 0.9f) {
                candidates.take(1)
            } else {
                candidates.take(2)
            }
            coroutineScope {
                probeList.map { candidate ->
                    async {
                        withTimeoutOrNull(5_000L) {
                            loadSeriesInfo(
                                providerKey = providerKey,
                                creds = creds,
                                seriesId = candidate.entry.seriesId,
                                allowNetwork = true
                            )
                        }
                    }
                }.awaitAll()
            }
        }

        suspend fun resolveEpisode(
            providerKey: String,
            creds: XtreamCredentials,
            showTitle: String,
            season: Int,
            episode: Int,
            tmdbId: Int?,
            imdbId: String?,
            year: Int?,
            allowNetwork: Boolean
        ): ResolverCachedResolvedEpisode? = resolveEpisodeVariants(
            providerKey = providerKey,
            creds = creds,
            showTitle = showTitle,
            season = season,
            episode = episode,
            tmdbId = tmdbId,
            imdbId = imdbId,
            year = year,
            allowNetwork = allowNetwork
        ).firstOrNull()

        /**
         * Cache-only fast path used at the top of `findEpisodeVodSources`.
         * Returns one variant per bound series ID whose episode list is
         * already cached (memory or fresh SharedPrefs entry). Empty list
         * means "no fast-path hit — fall through to normal resolution".
         */
        fun tryFastResolveEpisodeFromCache(
            providerKey: String,
            showTitle: String,
            season: Int,
            episode: Int,
            tmdbId: Int?,
            imdbId: String?
        ): List<ResolverCachedResolvedEpisode> {
            val normalizedShow = normalizeLookupText(showTitle)
            val normalizedTmdb = normalizeTmdbId(tmdbId)
            val normalizedImdb = normalizeImdbId(imdbId)
            val bindingKeys = buildSeriesBindingKeys(
                providerKey = providerKey,
                normalizedTmdb = normalizedTmdb,
                normalizedImdb = normalizedImdb,
                normalizedShow = normalizedShow
            )
            val seriesIds = readSeriesBinding(bindingKeys)
            if (seriesIds.isEmpty()) return emptyList()

            val now = System.currentTimeMillis()
            val out = mutableListOf<ResolverCachedResolvedEpisode>()
            for (seriesId in seriesIds) {
                val infoKey = "$providerKey|$seriesId"
                var episodes: List<XtreamSeriesEpisode>? = synchronized(seriesInfoLock) {
                    seriesInfoMemory[infoKey]
                }
                if (episodes.isNullOrEmpty()) {
                    val raw = prefs.getString(seriesInfoPrefKey(providerKey, seriesId), null) ?: continue
                    val persisted = runCatching {
                        gson.fromJson(raw, ResolverPersistedSeriesInfo::class.java)
                    }.getOrNull() ?: continue
                    if (persisted.episodes.isEmpty()) continue
                    if (now - persisted.savedAtMs > seriesInfoTtlMs) continue
                    synchronized(seriesInfoLock) {
                        seriesInfoMemory[infoKey] = persisted.episodes
                    }
                    episodes = persisted.episodes
                }
                val list = episodes ?: continue
                val hits = matchEpisodes(list, season, episode)
                val best = hits.maxByOrNull { it.score } ?: continue
                out += ResolverCachedResolvedEpisode(
                    streamId = best.episode.id,
                    containerExtension = best.episode.containerExtension,
                    seriesId = seriesId,
                    confidence = 0.995f,
                    method = "fast_path_cache",
                    title = best.episode.title,
                    savedAtMs = now
                )
            }
            return out.distinctBy { it.streamId }
        }

        suspend fun resolveEpisodeVariants(
            providerKey: String,
            creds: XtreamCredentials,
            showTitle: String,
            season: Int,
            episode: Int,
            tmdbId: Int?,
            imdbId: String?,
            year: Int?,
            allowNetwork: Boolean
        ): List<ResolverCachedResolvedEpisode> {
            val resolveStart = System.currentTimeMillis()
            System.err.println("[VOD-Resolver] resolveEpisode start: '$showTitle' S${season}E${episode} tmdb=$tmdbId imdb=$imdbId")
            val normalizedShow = normalizeLookupText(showTitle)
            val normalizedTmdb = normalizeTmdbId(tmdbId)
            val normalizedImdb = normalizeImdbId(imdbId)
            if (normalizedShow.isBlank() && normalizedTmdb.isNullOrBlank() && normalizedImdb.isNullOrBlank()) {
                System.err.println("[VOD-Resolver] No identifiers, returning null")
                return emptyList()
            }

            val cacheKey = buildResolvedCacheKey(providerKey, normalizedTmdb, normalizedImdb, normalizedShow, season, episode)
            val cachedResolved = readResolved(cacheKey)?.takeIf { cached ->
                System.currentTimeMillis() - cached.savedAtMs < resolvedTtlMs
            }
            if (!allowNetwork && cachedResolved != null) {
                System.err.println("[VOD-Resolver] Hit resolved cache candidate (method=${cachedResolved.method}, streamId=${cachedResolved.streamId}) in ${System.currentTimeMillis() - resolveStart}ms")
            }
            var bindingResolved = emptyList<ResolverCachedResolvedEpisode>()

            val bindingKeys = buildSeriesBindingKeys(
                providerKey = providerKey,
                normalizedTmdb = normalizedTmdb,
                normalizedImdb = normalizedImdb,
                normalizedShow = normalizedShow
            )
            val boundSeriesIds = readSeriesBinding(bindingKeys)
            if (boundSeriesIds.isNotEmpty()) {
                System.err.println("[VOD-Resolver] Found series bindings: ${boundSeriesIds.size} seriesIds=$boundSeriesIds, loading info in parallel...")
                val bindStart = System.currentTimeMillis()
                val perSeriesEpisodes = coroutineScope {
                    boundSeriesIds.map { seriesId ->
                        async {
                            seriesId to loadSeriesInfo(
                                providerKey = providerKey,
                                creds = creds,
                                seriesId = seriesId,
                                allowNetwork = allowNetwork
                            )
                        }
                    }.awaitAll()
                }
                System.err.println("[VOD-Resolver] loadSeriesInfo for ${boundSeriesIds.size} bindings took ${System.currentTimeMillis() - bindStart}ms")
                val boundHits = perSeriesEpisodes.flatMap { (seriesId, episodes) ->
                    matchEpisodes(episodes, season, episode).map { hit -> seriesId to hit }
                }
                if (boundHits.isNotEmpty()) {
                    val resolved = boundHits
                        .sortedWith(
                            compareByDescending<Pair<Int, ResolverEpisodeHit>> {
                                it.second.score + vodQualityRank(it.second.episode.title)
                            }.thenBy { it.second.episode.id }
                        )
                        .map { (seriesId, hit) ->
                            ResolverCachedResolvedEpisode(
                                streamId = hit.episode.id,
                                containerExtension = hit.episode.containerExtension,
                                seriesId = seriesId,
                                confidence = 0.995f,
                                method = "series_binding",
                                title = hit.episode.title,
                                savedAtMs = System.currentTimeMillis()
                            )
                        }
                        .distinctBy { it.streamId }
                    val best = resolved.first()
                    writeResolved(cacheKey, best)
                    // Intentionally do NOT shrink bindings to only the IDs
                    // that matched this S/E. A series that lacks S01E01 may
                    // still be the right match for S04E01 — keep all known
                    // bindings around so future episode lookups can find
                    // them. Cache already holds the full set from the
                    // initial resolve.
                    val matchedSeriesIds = boundHits.map { it.first }.distinct()
                    System.err.println("[VOD-Resolver] Resolved ${resolved.size} variants from ${matchedSeriesIds.size}/${boundSeriesIds.size} bound series via binding in ${System.currentTimeMillis() - resolveStart}ms")
                    if (!allowNetwork) {
                        return resolved
                    }
                    bindingResolved = resolved
                } else {
                    System.err.println("[VOD-Resolver] Bindings didn't match S${season}E${episode}")
                }
            }

            if (!allowNetwork && cachedResolved != null) {
                return listOf(cachedResolved)
            }

            System.err.println("[VOD-Resolver] Loading catalog...")
            val catalogStart = System.currentTimeMillis()
            val catalog = loadCatalog(providerKey, creds, allowNetwork = allowNetwork, forceRefresh = false)
            System.err.println("[VOD-Resolver] loadCatalog took ${System.currentTimeMillis() - catalogStart}ms, entries=${catalog.entries.size}")
            if (catalog.entries.isEmpty()) {
                System.err.println("[VOD-Resolver] Empty catalog, returning null")
                return bindingResolved.ifEmpty { cachedResolved?.let { listOf(it) } ?: emptyList() }
            }

            val candidateStart = System.currentTimeMillis()
            val candidates = buildCandidates(catalog, normalizedShow, normalizedTmdb, normalizedImdb, year)
            System.err.println("[VOD-Resolver] buildCandidates took ${System.currentTimeMillis() - candidateStart}ms, found ${candidates.size} candidates")
            if (candidates.isEmpty()) {
                System.err.println("[VOD-Resolver] No candidates, returning null after ${System.currentTimeMillis() - resolveStart}ms")
                return bindingResolved.ifEmpty { cachedResolved?.let { listOf(it) } ?: emptyList() }
            }
            fun ResolverCandidate.isStrongIdentityMatch(): Boolean {
                return method == "tmdb_id" || method == "imdb_id" || method == "title_canonical"
            }
            val probeList = if (candidates.first().isStrongIdentityMatch()) {
                candidates
                    .filter { it.isStrongIdentityMatch() && it.confidence >= 0.90f }
                    .take(8)
                    .ifEmpty { candidates.take(1) }
            } else {
                candidates.take(4)
            }
            System.err.println("[VOD-Resolver] Probing ${probeList.size} candidates: ${probeList.map { "${it.entry.name}(${it.method},${it.confidence})" }}")

            val probeStart = System.currentTimeMillis()
            val hits = coroutineScope {
                probeList.map { candidate ->
                    async {
                        val infoStart = System.currentTimeMillis()
                        val episodes = loadSeriesInfo(providerKey, creds, candidate.entry.seriesId, allowNetwork)
                        System.err.println("[VOD-Resolver] loadSeriesInfo(${candidate.entry.seriesId}) took ${System.currentTimeMillis() - infoStart}ms, got ${episodes.size} episodes")
                        matchEpisodes(episodes, season, episode).map { hit ->
                            Triple(candidate, hit.episode, hit.score)
                        }
                    }
                }.awaitAll().flatten()
            }
            System.err.println("[VOD-Resolver] Probing took ${System.currentTimeMillis() - probeStart}ms, hits=${hits.size}")
            if (hits.isEmpty()) {
                System.err.println("[VOD-Resolver] No hits, returning null after ${System.currentTimeMillis() - resolveStart}ms")
                return bindingResolved.ifEmpty { cachedResolved?.let { listOf(it) } ?: emptyList() }
            }

            val probedResolved = hits
                .sortedWith(
                    compareByDescending<Triple<ResolverCandidate, XtreamSeriesEpisode, Int>> {
                        it.first.confidence * 1000f + it.third + vodQualityRank(it.second.title)
                    }.thenBy { it.second.id }
                )
                .distinctBy { it.second.id }
                .map { hit ->
                    ResolverCachedResolvedEpisode(
                        streamId = hit.second.id,
                        containerExtension = hit.second.containerExtension,
                        seriesId = hit.first.entry.seriesId,
                        confidence = hit.first.confidence,
                        method = hit.first.method,
                        title = hit.second.title,
                        savedAtMs = System.currentTimeMillis()
                    )
                }
            val resolved = (probedResolved + bindingResolved)
                .distinctBy { it.streamId }
                .sortedWith(
                    compareByDescending<ResolverCachedResolvedEpisode> {
                        it.confidence * 1000f + vodQualityRank(it.title.orEmpty())
                    }.thenBy { it.streamId }
                )
            val best = resolved.firstOrNull() ?: return cachedResolved?.let { listOf(it) } ?: emptyList()
            writeResolved(cacheKey, best)
            // Persist EVERY probed candidate as a binding, regardless of
            // whether it had this episode. A provider may split a show into
            // multiple catalog entries (e.g. one with only S04, another with
            // S01-S03) — caching every name-matched entry means future
            // episode lookups can still find the right series. Merge with
            // existing bindings so we never lose ones discovered earlier.
            val probedSeriesIds = probeList.map { it.entry.seriesId }
            val mergedBindings = (boundSeriesIds + probedSeriesIds).distinct()
            writeSeriesBinding(bindingKeys, mergedBindings)
            val winnerCount = resolved.map { it.seriesId }.distinct().size
            System.err.println("[VOD-Resolver] Resolved ${resolved.size} variants ($winnerCount/${mergedBindings.size} bound series had this episode) via ${best.method} (conf=${best.confidence}) in ${System.currentTimeMillis() - resolveStart}ms")
            return resolved
        }

        private suspend fun loadCatalog(
            providerKey: String,
            creds: XtreamCredentials,
            allowNetwork: Boolean,
            forceRefresh: Boolean
        ): ResolverCatalogIndex {
            // Fast path: check in-memory cache (no lock needed)
            val now = System.currentTimeMillis()
            val inMem = catalogMemory[providerKey]
            if (!forceRefresh && inMem != null && now - inMem.createdAtMs < catalogTtlMs) return inMem

            if (!allowNetwork) {
                if (inMem != null) return inMem
                // Try SharedPreferences for stale data
                val persistedRaw = runCatching { prefs.getString(catalogPrefKey(providerKey), null) }.getOrNull()
                if (!persistedRaw.isNullOrBlank()) {
                    val persisted = runCatching { gson.fromJson(persistedRaw, ResolverPersistedCatalog::class.java) }.getOrNull()
                    if (persisted != null && persisted.entries.isNotEmpty()) {
                        val built = buildCatalogIndex(persisted.createdAtMs, persisted.entries)
                        catalogMemory[providerKey] = built
                        return built
                    }
                }
                return ResolverCatalogIndex(now, emptyList(), emptyMap(), emptyMap(), emptyMap(), emptyMap())
            }

            // Serialize catalog builds — only one thread does the expensive work,
            // others wait and get the result from memory.
            // Use NonCancellable so a cancelled coroutine doesn't abandon the build
            // while holding the mutex, leaving catalogMemory empty for everyone.
            return withContext(kotlinx.coroutines.NonCancellable) { catalogLoadMutex.withLock {
                // Re-check memory inside lock — another thread may have built it while we waited
                val afterLockMem = catalogMemory[providerKey]
                val lockNow = System.currentTimeMillis()
                if (!forceRefresh && afterLockMem != null && lockNow - afterLockMem.createdAtMs < catalogTtlMs) {
                    System.err.println("[VOD-Resolver] loadCatalog: found in memory after lock wait (${afterLockMem.entries.size} entries)")
                    return@withLock afterLockMem
                }

                // Try SharedPreferences persisted catalog
                var stalePersisted: ResolverPersistedCatalog? = null
                if (!forceRefresh) {
                    val persistedRaw = runCatching { prefs.getString(catalogPrefKey(providerKey), null) }.getOrNull()
                    if (!persistedRaw.isNullOrBlank()) {
                        val persisted = runCatching { gson.fromJson(persistedRaw, ResolverPersistedCatalog::class.java) }.getOrNull()
                        if (persisted != null && persisted.entries.isNotEmpty()) {
                            stalePersisted = persisted
                            if (lockNow - persisted.createdAtMs < catalogTtlMs) {
                                System.err.println("[VOD-Resolver] loadCatalog: building from persisted prefs (${persisted.entries.size} entries)")
                                val built = buildCatalogIndex(persisted.createdAtMs, persisted.entries)
                                catalogMemory[providerKey] = built
                                return@withLock built
                            }
                        }
                    }
                }

                System.err.println("[VOD-Resolver] loadCatalog: fetching series list from network...")
                val fetchStart = System.currentTimeMillis()
                val entries = withTimeoutOrNull(45_000L) {
                    val rawList = getXtreamSeriesList(creds, allowNetwork = true, fast = false)
                    System.err.println("[VOD-Resolver] loadCatalog: got ${rawList.size} raw series in ${System.currentTimeMillis() - fetchStart}ms, building entries...")
                    rawList.mapNotNull { item ->
                        val seriesId = item.seriesId ?: return@mapNotNull null
                        val name = item.name?.trim().orEmpty()
                        if (name.isBlank()) return@mapNotNull null
                        val normalizedName = normalizeLookupText(name)
                        val tokens = extractTitleTokensFromNormalized(normalizedName)
                        ResolverSeriesEntry(
                            seriesId = seriesId,
                            name = name,
                            normalizedName = normalizedName,
                            canonicalTitleKey = toCanonicalTitleKeyFromTokens(tokens),
                            titleTokens = tokens,
                            tmdb = normalizeTmdbId(item.tmdb),
                            imdb = normalizeImdbId(item.imdb),
                            year = parseYear(item.name ?: "")
                        )
                    }
                }.orEmpty()
                System.err.println("[VOD-Resolver] loadCatalog: entries=${entries.size} in ${System.currentTimeMillis() - fetchStart}ms")

                if (entries.isEmpty()) {
                    val stale = stalePersisted
                    if (stale != null && stale.entries.isNotEmpty()) {
                        System.err.println("[VOD-Resolver] loadCatalog: network empty, using stale persisted (${stale.entries.size})")
                        val built = buildCatalogIndex(stale.createdAtMs, stale.entries)
                        catalogMemory[providerKey] = built
                        return@withLock built
                    }
                }

                val buildStart = System.currentTimeMillis()
                val built = buildCatalogIndex(lockNow, entries)
                System.err.println("[VOD-Resolver] loadCatalog: buildCatalogIndex took ${System.currentTimeMillis() - buildStart}ms")
                catalogMemory[providerKey] = built
                // Persist to SharedPreferences in background — don't block resolution
                if (entries.isNotEmpty() && entries.size <= 50_000) {
                    runCatching {
                        prefs.edit().putString(catalogPrefKey(providerKey), gson.toJson(ResolverPersistedCatalog(lockNow, entries))).apply()
                    }
                }
                built
            } }
        }

        private fun buildCatalogIndex(createdAtMs: Long, entries: List<ResolverSeriesEntry>): ResolverCatalogIndex {
            val normalizedEntries = entries.map { entry ->
                val normalizedName = entry.normalizedName.ifBlank { normalizeLookupText(entry.name) }
                val titleTokens = if (entry.titleTokens.isEmpty()) extractTitleTokensFromNormalized(normalizedName) else entry.titleTokens
                val canonicalTitleKey = entry.canonicalTitleKey.ifBlank { toCanonicalTitleKeyFromTokens(titleTokens) }
                entry.copy(
                    normalizedName = normalizedName,
                    canonicalTitleKey = canonicalTitleKey,
                    titleTokens = titleTokens
                )
            }
            // IMPORTANT: Normalize keys so lookup matches correctly
            val tmdbMap = normalizedEntries
                .filter { !it.tmdb.isNullOrBlank() }
                .groupBy { normalizeTmdbId(it.tmdb)!! }
            val imdbMap = normalizedEntries
                .filter { !it.imdb.isNullOrBlank() }
                .groupBy { normalizeImdbId(it.imdb)!! }
            val canonicalTitleMap = normalizedEntries
                .filter { it.canonicalTitleKey.isNotBlank() }
                .groupBy { it.canonicalTitleKey }
            val tokenMap = buildMap<String, List<ResolverSeriesEntry>> {
                val temp = LinkedHashMap<String, MutableList<ResolverSeriesEntry>>()
                normalizedEntries.forEach { entry ->
                    entry.titleTokens.forEach { token ->
                        temp.getOrPut(token) { mutableListOf() }.add(entry)
                    }
                }
                temp.forEach { (token, tokenEntries) ->
                    put(token, tokenEntries.distinctBy { it.seriesId })
                }
            }
            return ResolverCatalogIndex(
                createdAtMs = createdAtMs,
                entries = normalizedEntries,
                tmdbMap = tmdbMap,
                imdbMap = imdbMap,
                canonicalTitleMap = canonicalTitleMap,
                tokenMap = tokenMap
            )
        }

        private fun buildCandidates(
            catalog: ResolverCatalogIndex,
            normalizedShow: String,
            normalizedTmdb: String?,
            normalizedImdb: String?,
            inputYear: Int?
        ): List<ResolverCandidate> {
            val out = LinkedHashMap<Int, ResolverCandidate>()

            if (!normalizedTmdb.isNullOrBlank()) {
                catalog.tmdbMap[normalizedTmdb].orEmpty().forEach { entry ->
                    out[entry.seriesId] = ResolverCandidate(entry, confidence = 0.98f, method = "tmdb_id", baseScore = 20_000)
                }
            }
            if (!normalizedImdb.isNullOrBlank()) {
                catalog.imdbMap[normalizedImdb].orEmpty().forEach { entry ->
                    val prev = out[entry.seriesId]
                    if (prev == null || prev.confidence < 0.99f) {
                        out[entry.seriesId] = ResolverCandidate(entry, confidence = 0.99f, method = "imdb_id", baseScore = 21_000)
                    }
                }
            }

            if (normalizedShow.isNotBlank()) {
                val canonicalShow = toCanonicalTitleKey(normalizedShow)
                if (canonicalShow.isNotBlank()) {
                    catalog.canonicalTitleMap[canonicalShow].orEmpty().forEach { entry ->
                        val yearDelta = when {
                            inputYear == null || entry.year == null -> 0
                            else -> kotlin.math.abs(inputYear - entry.year)
                        }
                        if (yearDelta > 1) return@forEach
                        val total = when (yearDelta) {
                            0 -> 18_000
                            1 -> 17_500
                            else -> 17_200
                        }
                        val confidence = when (yearDelta) {
                            0 -> 0.93f
                            1 -> 0.90f
                            else -> 0.88f
                        }
                        val existing = out[entry.seriesId]
                        if (existing == null || total > existing.baseScore) {
                            out[entry.seriesId] = ResolverCandidate(entry, confidence = confidence, method = "title_canonical", baseScore = total)
                        }
                    }
                }

                val queryTokens = extractTitleTokens(normalizedShow)
                if (queryTokens.isNotEmpty()) {
                    val candidatePool = LinkedHashMap<Int, ResolverSeriesEntry>()
                    queryTokens.forEach { token ->
                        catalog.tokenMap[token].orEmpty().forEach { entry ->
                            candidatePool[entry.seriesId] = entry
                        }
                    }
                    candidatePool.values.forEach { entry ->
                        val overlap = entry.titleTokens.intersect(queryTokens).size
                        if (overlap <= 0) return@forEach
                        val coverage = overlap.toFloat() / queryTokens.size.toFloat()
                        val accepted = if (queryTokens.size == 1) {
                            coverage >= 1f
                        } else {
                            overlap >= 2 || coverage >= 0.6f
                        }
                        if (!accepted) return@forEach
                        val yearDelta = when {
                            inputYear == null || entry.year == null -> 0
                            else -> kotlin.math.abs(inputYear - entry.year)
                        }
                        if (yearDelta > 1) return@forEach
                        val yearScore = when (yearDelta) {
                            0 -> 120
                            1 -> 70
                            else -> 35
                        }
                        val total = (coverage * 1_000f).toInt() + (overlap * 180) + yearScore
                        val confidence = when {
                            coverage >= 1f && overlap >= 2 -> 0.86f
                            coverage >= 0.8f -> 0.82f
                            else -> 0.76f
                        }
                        val existing = out[entry.seriesId]
                        if (existing == null || total > existing.baseScore) {
                            out[entry.seriesId] = ResolverCandidate(entry, confidence = confidence, method = "title_tokens", baseScore = total)
                        }
                    }
                }
            }

            return out.values
                .sortedWith(compareByDescending<ResolverCandidate> { it.confidence }.thenByDescending { it.baseScore })
        }

        private suspend fun loadSeriesInfo(
            providerKey: String,
            creds: XtreamCredentials,
            seriesId: Int,
            allowNetwork: Boolean
        ): List<XtreamSeriesEpisode> {
            val key = "$providerKey|$seriesId"
            synchronized(seriesInfoLock) {
                val cached = seriesInfoMemory[key]
                if (!cached.isNullOrEmpty()) return cached
            }
            val persisted = runCatching {
                gson.fromJson(
                    prefs.getString(seriesInfoPrefKey(providerKey, seriesId), null),
                    ResolverPersistedSeriesInfo::class.java
                )
            }.getOrNull()
            if (persisted != null &&
                persisted.episodes.isNotEmpty() &&
                System.currentTimeMillis() - persisted.savedAtMs < seriesInfoTtlMs
            ) {
                synchronized(seriesInfoLock) {
                    seriesInfoMemory[key] = persisted.episodes
                }
                return persisted.episodes
            }
            val episodes = withTimeoutOrNull(10_000L) {
                getXtreamSeriesEpisodes(creds, seriesId, allowNetwork = allowNetwork, fast = false)
            }.orEmpty()
            if (episodes.isNotEmpty()) {
                synchronized(seriesInfoLock) {
                    seriesInfoMemory[key] = episodes
                }
                runCatching {
                    prefs.edit().putString(
                        seriesInfoPrefKey(providerKey, seriesId),
                        gson.toJson(
                            ResolverPersistedSeriesInfo(
                                savedAtMs = System.currentTimeMillis(),
                                episodes = episodes
                            )
                        )
                    ).apply()
                }
            }
            return episodes
        }

        private fun matchEpisode(
            episodes: List<XtreamSeriesEpisode>,
            requestedSeason: Int,
            requestedEpisode: Int
        ): ResolverEpisodeHit? = matchEpisodes(episodes, requestedSeason, requestedEpisode).firstOrNull()

        private fun matchEpisodes(
            episodes: List<XtreamSeriesEpisode>,
            requestedSeason: Int,
            requestedEpisode: Int
        ): List<ResolverEpisodeHit> {
            if (episodes.isEmpty()) return emptyList()

            // Exact season/episode is the only high-confidence match.
            val exact = episodes.filter { it.season == requestedSeason && it.episode == requestedEpisode }
            if (exact.isNotEmpty()) {
                return exact.map { ResolverEpisodeHit(it, score = 1000) }
            }

            // If provider clearly has the requested season, do not cross-match to another season.
            if (episodes.any { it.season == requestedSeason }) {
                return emptyList()
            }

            // Flattened providers sometimes expose all episodes as season 1 (or 0).
            // Allow this only when there is a single unambiguous episode-number match.
            val sameEpisode = episodes.filter { it.episode == requestedEpisode }
            val flattened = episodes.all { it.season <= 1 }
            if (flattened && sameEpisode.size == 1) {
                return listOf(ResolverEpisodeHit(sameEpisode.first(), score = 640))
            }
            return emptyList()
        }

        private fun buildResolvedCacheKey(
            providerKey: String,
            tmdb: String?,
            imdb: String?,
            normalizedTitle: String,
            season: Int,
            episode: Int
        ): String {
            return listOf(
                providerKey,
                tmdb.orEmpty(),
                imdb.orEmpty(),
                normalizedTitle,
                season.toString(),
                episode.toString()
            ).joinToString("|")
        }

        private fun readResolved(key: String): ResolverCachedResolvedEpisode? {
            synchronized(resolvedLock) {
                resolvedMemory[key]?.let { return it }
            }
            val raw = prefs.getString(resolvedPrefKey, null) ?: return null
            val persisted = runCatching { gson.fromJson(raw, ResolverPersistedResolved::class.java) }.getOrNull() ?: return null
            val hit = persisted.items[key] ?: return null
            if (System.currentTimeMillis() - hit.savedAtMs > resolvedTtlMs) return null
            synchronized(resolvedLock) { resolvedMemory[key] = hit }
            return hit
        }

        private fun writeResolved(key: String, value: ResolverCachedResolvedEpisode) {
            synchronized(resolvedLock) {
                resolvedMemory[key] = value
            }
            val existingRaw = prefs.getString(resolvedPrefKey, null)
            val existing = runCatching { gson.fromJson(existingRaw, ResolverPersistedResolved::class.java) }.getOrNull()
                ?: ResolverPersistedResolved()
            val merged = LinkedHashMap(existing.items)
            merged[key] = value
            while (merged.size > 512) {
                val oldest = merged.entries.minByOrNull { it.value.savedAtMs }?.key ?: break
                merged.remove(oldest)
            }
            runCatching {
                prefs.edit().putString(resolvedPrefKey, gson.toJson(ResolverPersistedResolved(merged))).apply()
            }
        }

        private fun buildSeriesBindingKeys(
            providerKey: String,
            normalizedTmdb: String?,
            normalizedImdb: String?,
            normalizedShow: String
        ): List<String> {
            val keys = mutableListOf<String>()
            if (!normalizedTmdb.isNullOrBlank()) keys += "$providerKey|tmdb:$normalizedTmdb"
            if (!normalizedImdb.isNullOrBlank()) keys += "$providerKey|imdb:$normalizedImdb"
            val canonicalShow = toCanonicalTitleKey(normalizedShow)
            if (canonicalShow.isNotBlank()) keys += "$providerKey|title:$canonicalShow"
            return keys.distinct()
        }

        /**
         * Returns the union of bound series IDs across all binding keys. The
         * order preserves match-confidence priority (TMDB-id keys come before
         * IMDb keys, then title keys — see [buildSeriesBindingKeys]).
         */
        private fun readSeriesBinding(keys: List<String>): List<Int> {
            if (keys.isEmpty()) return emptyList()
            val combined = LinkedHashSet<Int>()
            synchronized(seriesBindingLock) {
                keys.forEach { key ->
                    seriesBindingMemory[key]?.forEach { combined.add(it) }
                }
                if (combined.isNotEmpty()) return combined.toList()
                // Read prefs inside the lock: prevents two concurrent IO threads from
                // racing to populate seriesBindingMemory from the same prefs blob.
                val raw = prefs.getString(seriesBindingPrefKey, null) ?: return emptyList()
                val persisted = runCatching {
                    gson.fromJson(raw, ResolverPersistedSeriesBindings::class.java)
                }.getOrNull() ?: return emptyList()
                keys.forEach { key ->
                    val ids = persisted.items[key].orEmpty()
                    if (ids.isNotEmpty()) {
                        seriesBindingMemory[key] = ids
                        ids.forEach { combined.add(it) }
                    }
                }
                return combined.toList()
            }
        }

        private fun writeSeriesBinding(keys: List<String>, seriesIds: List<Int>) {
            if (keys.isEmpty() || seriesIds.isEmpty()) return
            // Merge new IDs with whatever is already stored per key before capping,
            // so a subsequent call never loses bindings discovered in a prior resolve.
            synchronized(seriesBindingLock) {
                keys.forEach { key ->
                    val existing = seriesBindingMemory[key].orEmpty()
                    seriesBindingMemory[key] = (existing + seriesIds).distinct().take(maxSeriesBindingsPerKey)
                }
            }
            val existingRaw = prefs.getString(seriesBindingPrefKey, null)
            val existing = runCatching {
                gson.fromJson(existingRaw, ResolverPersistedSeriesBindings::class.java)
            }.getOrNull() ?: ResolverPersistedSeriesBindings()
            val persisted = LinkedHashMap(existing.items)
            keys.forEach { key ->
                val existingIds = persisted[key].orEmpty()
                persisted[key] = (existingIds + seriesIds).distinct().take(maxSeriesBindingsPerKey)
            }
            while (persisted.size > 2048) {
                val oldestKey = persisted.keys.firstOrNull() ?: break
                persisted.remove(oldestKey)
            }
            runCatching {
                prefs.edit().putString(seriesBindingPrefKey, gson.toJson(ResolverPersistedSeriesBindings(persisted))).apply()
            }
        }

        /**
         * Wipe every resolver-owned cache: in-memory catalogs / resolved
         * episodes / series bindings / per-series episode lists, plus the
         * backing SharedPreferences file. Used when the user explicitly
         * refreshes IPTV from Settings — everything is rebuilt on the next
         * resolve.
         */
        fun clearAll() {
            catalogMemory.clear()
            synchronized(resolvedLock) { resolvedMemory.clear() }
            synchronized(seriesBindingLock) { seriesBindingMemory.clear() }
            synchronized(seriesInfoLock) { seriesInfoMemory.clear() }
            runCatching {
                val editor = prefs.edit()
                    .remove(resolvedPrefKey)
                    .remove(seriesBindingPrefKey)
                prefs.all.keys
                    .filter { it.startsWith("catalog_") || it.startsWith("series_info_") }
                    .forEach { editor.remove(it) }
                editor.apply()
            }
        }
    }

    suspend fun findMovieVodSource(
        title: String,
        year: Int?,
        imdbId: String? = null,
        tmdbId: Int? = null,
        allowNetwork: Boolean = true
    ): StreamSource? = findMovieVodSources(
        title = title,
        year = year,
        imdbId = imdbId,
        tmdbId = tmdbId,
        allowNetwork = allowNetwork
    ).firstOrNull()

    suspend fun findMovieVodSources(
        title: String,
        year: Int?,
        imdbId: String? = null,
        tmdbId: Int? = null,
        allowNetwork: Boolean = true
    ): List<StreamSource> {
        return withContext(Dispatchers.IO) {
            val config = observeConfig().first()
            val creds = resolveXtreamCredentials(config.epgUrl)
                ?: resolveXtreamCredentials(config.m3uUrl)
                ?: return@withContext emptyList()

            val credsFingerprint = xtreamDiskCacheHash(creds)
            val cacheKey = iptvMovieSourceCacheKey(
                profileIdHash = profileIdHash(),
                imdbId = imdbId,
                tmdbId = tmdbId,
                title = title,
                year = year
            )
            if (cacheKey != null) {
                lookupCachedMovieSources(cacheKey, credsFingerprint)?.let { cached ->
                    return@withContext cached
                }
            }

            val vod = getXtreamVodStreams(creds, allowNetwork, fast = true)
            if (vod.isEmpty()) return@withContext emptyList()

            val normalizedTmdb = normalizeTmdbId(tmdbId)
            val normalizedImdb = normalizeImdbId(imdbId)
                ?.takeIf { it != IptvIdSentinels.IMDB_NONE }

            // Fast path: ID-based linear scan over the catalog. No index build
            // needed — preserves the pre-index hot-path performance for movies
            // whose TMDB/IMDb id is present in the provider catalog (the common
            // case). The full token index is built lazily only if both ID
            // lookups miss and we need to fall back to title-based matching.
            fun finalizeIdMatches(
                matches: List<XtreamVodStream>,
                fallbackTitle: String
            ): List<StreamSource> {
                val sources = sortVodSources(
                    matches.mapNotNull { it.toMovieVodSource(creds, title.ifBlank { fallbackTitle }) }
                )
                if (cacheKey != null && sources.isNotEmpty()) {
                    storeCachedMovieSources(cacheKey, sources, credsFingerprint)
                }
                return sources
            }

            // ID-only index built at catalog-load gives O(1) ID lookup.
            val idIndex = cachedVodIdIndex?.takeIf { it.items === vod }
                ?: buildVodIdIndex(vod).also { cachedVodIdIndex = it }
            if (!normalizedTmdb.isNullOrBlank()) {
                val hits = idIndex.tmdbMap[normalizedTmdb].orEmpty()
                if (hits.isNotEmpty()) {
                    return@withContext finalizeIdMatches(hits.map { idIndex.items[it] }, normalizedTmdb)
                }
            }
            if (!normalizedImdb.isNullOrBlank()) {
                val hits = idIndex.imdbMap[normalizedImdb].orEmpty()
                if (hits.isNotEmpty()) {
                    return@withContext finalizeIdMatches(hits.map { idIndex.items[it] }, normalizedImdb)
                }
            }

            val normalizedTitle = normalizeLookupText(title)
            if (normalizedTitle.isBlank()) return@withContext emptyList()
            val inputYear = year ?: parseYear(title)

            // Title fallback: build (or reuse) the indexed catalog. Expensive
            // first time (~ catalog size × token-extract cost) but reused for
            // every subsequent title-only query in this session.
            val filteredIndex = ensureVodCatalogIndex(vod)
            val matches = findMovieCandidatesIndexed(
                filteredIndex,
                normalizedTitle,
                normalizedTmdb,
                normalizedImdb,
                inputYear
            )

            if (matches.isEmpty()) return@withContext emptyList()

            val sources = sortVodSources(
                matches.mapNotNull {
                    it.toMovieVodSource(
                        creds,
                        title.ifBlank { normalizedTmdb ?: normalizedImdb.orEmpty() }
                    )
                }
            )
            if (cacheKey != null && sources.isNotEmpty()) {
                storeCachedMovieSources(cacheKey, sources, credsFingerprint)
            }
            sources
        }
    }

    suspend fun findEpisodeVodSource(
        title: String,
        season: Int,
        episode: Int,
        imdbId: String? = null,
        tmdbId: Int? = null,
        allowNetwork: Boolean = true
    ): StreamSource? = findEpisodeVodSources(
        title = title,
        season = season,
        episode = episode,
        imdbId = imdbId,
        tmdbId = tmdbId,
        allowNetwork = allowNetwork
    ).firstOrNull()

    suspend fun findEpisodeVodSources(
        title: String,
        season: Int,
        episode: Int,
        imdbId: String? = null,
        tmdbId: Int? = null,
        allowNetwork: Boolean = true
    ): List<StreamSource> {
        return withContext(Dispatchers.IO) {
            val config = observeConfig().first()
            val creds = resolveXtreamCredentials(config.epgUrl)
                ?: resolveXtreamCredentials(config.m3uUrl)
                ?: return@withContext emptyList()
            val normalizedTitle = normalizeLookupText(title)
            val normalizedImdb = normalizeImdbId(imdbId)
            val normalizedTmdb = normalizeTmdbId(tmdbId)
            if (normalizedTitle.isBlank() && normalizedImdb.isNullOrBlank() && normalizedTmdb.isNullOrBlank()) {
                return@withContext emptyList()
            }
            val activeProfileId = runCatching { profileManager.getProfileIdSync() }.getOrDefault("default")
            val providerKey = "$activeProfileId|${xtreamCacheKey(creds)}"

            fun List<ResolverCachedResolvedEpisode>.toSeriesVodSources(): List<StreamSource> {
                return map { resolved ->
                    val resolvedTitle = resolved.title?.trim().orEmpty()
                    val ext = resolved.containerExtension?.trim()?.ifBlank { null } ?: "mp4"
                    val streamUrl = "${creds.baseUrl}/series/${creds.username}/${creds.password}/${resolved.streamId}.$ext"
                    val sourceName = resolvedTitle.ifBlank { "$title S${season}E${episode}" }
                    StreamSource(
                        source = sourceName,
                        addonName = "IPTV Series VOD",
                        addonId = "iptv_xtream_vod",
                        quality = inferQuality(sourceName),
                        size = "",
                        url = streamUrl
                    )
                }
            }

            // FAST PATH: stored series bindings + cached episodes → one source
            // per resolved series, all built without any network roundtrips.
            // Stale episode lists are handled by the seriesInfoTtl on read.
            val fastResolved = seriesResolver.tryFastResolveEpisodeFromCache(
                providerKey = providerKey,
                showTitle = title,
                season = season,
                episode = episode,
                tmdbId = tmdbId,
                imdbId = imdbId
            )
            if (fastResolved.isNotEmpty()) {
                return@withContext sortVodSources(fastResolved.toSeriesVodSources())
            }

            val cachedVodCatalogSources = findEpisodeVodFromVodCatalogFallbackSources(
                creds = creds,
                title = title,
                season = season,
                episode = episode,
                normalizedImdb = normalizedImdb,
                normalizedTmdb = normalizedTmdb,
                allowNetwork = false
            )
            if (cachedVodCatalogSources.isNotEmpty()) {
                return@withContext cachedVodCatalogSources
            }

            val cachedSeriesSources = seriesResolver.resolveEpisodeVariants(
                providerKey = providerKey,
                creds = creds,
                showTitle = title,
                season = season,
                episode = episode,
                tmdbId = tmdbId,
                imdbId = imdbId,
                year = parseYear(title),
                allowNetwork = false
            ).toSeriesVodSources()

            if (!allowNetwork) {
                return@withContext sortVodSources(cachedSeriesSources)
            }

            val networkSeriesSources = withTimeoutOrNull(3_500L) {
                seriesResolver.resolveEpisodeVariants(
                    providerKey = providerKey,
                    creds = creds,
                    showTitle = title,
                    season = season,
                    episode = episode,
                    tmdbId = tmdbId,
                    imdbId = imdbId,
                    year = parseYear(title),
                    allowNetwork = true
                ).toSeriesVodSources()
            }.orEmpty()
            if (networkSeriesSources.isNotEmpty()) {
                return@withContext sortVodSources(networkSeriesSources)
            }

            val vodCatalogSources = withTimeoutOrNull(3_000L) {
                findEpisodeVodFromVodCatalogFallbackSources(
                    creds = creds,
                    title = title,
                    season = season,
                    episode = episode,
                    normalizedImdb = normalizedImdb,
                    normalizedTmdb = normalizedTmdb,
                    allowNetwork = true
                )
            }.orEmpty()
            return@withContext sortVodSources(vodCatalogSources + cachedSeriesSources)
        }
    }

    private suspend fun <T> Deferred<T>.awaitWithin(timeoutMs: Long): T? {
        return withTimeoutOrNull(timeoutMs) {
            join()
            awaitSafely()
        }
    }

    private suspend fun <T> Deferred<T>.awaitSafely(): T? {
        return runCatching { await() }.getOrNull()
    }

    private suspend fun findEpisodeVodFromVodCatalogFallback(
        creds: XtreamCredentials,
        title: String,
        season: Int,
        episode: Int,
        normalizedImdb: String?,
        normalizedTmdb: String?,
        allowNetwork: Boolean
    ): StreamSource? = findEpisodeVodFromVodCatalogFallbackSources(
        creds = creds,
        title = title,
        season = season,
        episode = episode,
        normalizedImdb = normalizedImdb,
        normalizedTmdb = normalizedTmdb,
        allowNetwork = allowNetwork
    ).firstOrNull()

    private suspend fun findEpisodeVodFromVodCatalogFallbackSources(
        creds: XtreamCredentials,
        title: String,
        season: Int,
        episode: Int,
        normalizedImdb: String?,
        normalizedTmdb: String?,
        allowNetwork: Boolean
    ): List<StreamSource> {
        val normalizedTitle = normalizeLookupText(title)
        val vod = getXtreamVodStreams(creds, allowNetwork = allowNetwork, fast = true)
        if (vod.isEmpty()) return emptyList()

        val scored = vod.asSequence()
            .mapNotNull { item ->
                val streamId = item.streamId ?: return@mapNotNull null
                val name = item.name?.trim().orEmpty()
                if (name.isBlank()) return@mapNotNull null
                val parsedEpisode = extractSeasonEpisodeFromName(name)
                val episodeOnly = if (parsedEpisode == null) extractEpisodeOnlyFromName(name) else null
                val hasExactSeasonEpisode = parsedEpisode?.let { it.first == season && it.second == episode } == true
                val hasEpisodeOnlyMatch = episodeOnly == episode
                if (!hasExactSeasonEpisode && !hasEpisodeOnlyMatch) return@mapNotNull null

                val imdbScore = if (!normalizedImdb.isNullOrBlank() && normalizeImdbId(item.imdb) == normalizedImdb) 10_000 else 0
                val tmdbScore = if (!normalizedTmdb.isNullOrBlank() && normalizeTmdbId(item.tmdb) == normalizedTmdb) 9_500 else 0
                val titleScore = if (normalizedTitle.isNotBlank()) {
                    maxOf(scoreNameMatch(name, normalizedTitle), looseSeriesTitleScore(name, normalizedTitle))
                } else {
                    0
                }
                // For episode-only patterns (no season marker), require stronger identity if season > 1.
                if (!hasExactSeasonEpisode && season > 1 && imdbScore == 0 && tmdbScore == 0) return@mapNotNull null
                if (imdbScore == 0 && tmdbScore == 0 && titleScore <= 0) return@mapNotNull null
                Triple(item, streamId, imdbScore + tmdbScore + titleScore)
            }
            .sortedByDescending { it.third }
            .toList()
        val bestScore = scored.firstOrNull()?.third ?: return emptyList()
        val minScore = maxOf(45, bestScore - 120)
        return sortVodSources(
            scored
                .asSequence()
                .takeWhile { it.third >= minScore }
                .mapNotNull { it.first.toEpisodeVodSource(creds, "$title S${season}E${episode}") }
                .toList()
        )
    }

    private fun XtreamVodStream.toMovieVodSource(
        creds: XtreamCredentials,
        fallbackTitle: String
    ): StreamSource? {
        val streamId = streamId ?: return null
        val ext = containerExtension?.trim()?.ifBlank { null } ?: "mp4"
        val streamUrl = "${creds.baseUrl}/movie/${creds.username}/${creds.password}/$streamId.$ext"
        val sourceName = name?.trim().orEmpty().ifBlank { fallbackTitle }
        return StreamSource(
            source = sourceName,
            addonName = "IPTV VOD",
            addonId = "iptv_xtream_vod",
            quality = inferQuality(sourceName),
            size = "",
            url = streamUrl
        )
    }

    private fun XtreamVodStream.toEpisodeVodSource(
        creds: XtreamCredentials,
        fallbackTitle: String
    ): StreamSource? {
        val streamId = streamId ?: return null
        val ext = containerExtension?.trim()?.ifBlank { null } ?: "mp4"
        val streamUrl = "${creds.baseUrl}/movie/${creds.username}/${creds.password}/$streamId.$ext"
        val sourceName = name?.trim().orEmpty().ifBlank { fallbackTitle }
        return StreamSource(
            source = sourceName,
            addonName = "IPTV Episode VOD",
            addonId = "iptv_xtream_vod",
            quality = inferQuality(sourceName),
            size = "",
            url = streamUrl
        )
    }

    private fun sortVodSources(sources: List<StreamSource>): List<StreamSource> {
        return sources
            .filter { !it.url.isNullOrBlank() }
            .distinctBy { "${it.url.orEmpty().trim()}|${it.source.trim()}" }
            .sortedWith(
                compareByDescending<StreamSource> { vodQualityRank(it.quality.ifBlank { it.source }) }
                    .thenByDescending { vodQualityRank(it.source) }
                    .thenBy { it.source.lowercase(Locale.US) }
            )
    }

    suspend fun warmXtreamVodCachesIfPossible() {
        withContext(Dispatchers.IO) {
            val config = observeConfig().first()
            val creds = resolveXtreamCredentials(config.epgUrl)
                ?: resolveXtreamCredentials(config.m3uUrl)
                ?: return@withContext
            runCatching {
                loadXtreamVodStreams(creds)
                loadXtreamSeriesList(creds)
                val activeProfileId = runCatching { profileManager.getProfileIdSync() }.getOrDefault("default")
                val providerKey = "$activeProfileId|${xtreamCacheKey(creds)}"
                seriesResolver.refreshCatalog(providerKey, creds)
            }
        }
    }

    suspend fun prefetchEpisodeVodResolution(
        title: String,
        season: Int,
        episode: Int,
        imdbId: String? = null,
        tmdbId: Int? = null
    ) {
        withContext(Dispatchers.IO) {
            val config = observeConfig().first()
            val creds = resolveXtreamCredentials(config.epgUrl)
                ?: resolveXtreamCredentials(config.m3uUrl)
                ?: return@withContext
            val activeProfileId = runCatching { profileManager.getProfileIdSync() }.getOrDefault("default")
            val providerKey = "$activeProfileId|${xtreamCacheKey(creds)}"
            runCatching {
                seriesResolver.resolveEpisode(
                    providerKey = providerKey,
                    creds = creds,
                    showTitle = title,
                    season = season,
                    episode = episode,
                    tmdbId = tmdbId,
                    imdbId = imdbId,
                    year = parseYear(title),
                    allowNetwork = true
                )
            }
        }
    }

    suspend fun prefetchSeriesInfoForShow(
        title: String,
        imdbId: String? = null,
        tmdbId: Int? = null
    ) {
        withContext(Dispatchers.IO) {
            val config = observeConfig().first()
            val creds = resolveXtreamCredentials(config.epgUrl)
                ?: resolveXtreamCredentials(config.m3uUrl)
                ?: return@withContext
            val activeProfileId = runCatching { profileManager.getProfileIdSync() }.getOrDefault("default")
            val providerKey = "$activeProfileId|${xtreamCacheKey(creds)}"
            runCatching {
                seriesResolver.prefetchSeriesInfo(
                    providerKey = providerKey,
                    creds = creds,
                    showTitle = title,
                    tmdbId = tmdbId,
                    imdbId = imdbId,
                    year = parseYear(title)
                )
            }
        }
    }

    private fun xtreamCacheKey(creds: XtreamCredentials): String {
        return "${creds.baseUrl}|${creds.username}|${creds.password}"
    }

    private fun ensureXtreamVodCacheOwnership(creds: XtreamCredentials) {
        val key = xtreamCacheKey(creds)
        if (xtreamVodCacheKey == key) return
        xtreamVodCacheKey = key
        xtreamVodLoadedAtMs = 0L
        xtreamSeriesLoadedAtMs = 0L
        cachedXtreamVodStreams = emptyList()
        cachedXtreamSeries = emptyList()
        cachedXtreamSeriesEpisodes = emptyMap()
        xtreamSeriesEpisodeInFlight = emptyMap()
    }

    // ── Disk cache helpers for Xtream VOD / Series catalogs ──────────────
    // Persists catalogs to JSON files so they survive app restarts, avoiding
    // 15-28 s re-downloads on cold start for large providers.

    private data class XtreamDiskCache<T>(val savedAtMs: Long, val items: List<T>)

    private fun xtreamDiskCacheDir(): File = File(context.filesDir, "xtream_vod_disk_cache").also { it.mkdirs() }

    private fun xtreamDiskCacheHash(creds: XtreamCredentials): String {
        val raw = "${creds.baseUrl}|${creds.username}|${creds.password}"
        return MessageDigest.getInstance("MD5").digest(raw.toByteArray(StandardCharsets.UTF_8))
            .joinToString("") { "%02x".format(it) }
    }

    private fun vodDiskCacheFile(creds: XtreamCredentials): File =
        File(xtreamDiskCacheDir(), "vod_${xtreamDiskCacheHash(creds)}.json")

    private fun seriesDiskCacheFile(creds: XtreamCredentials): File =
        File(xtreamDiskCacheDir(), "series_${xtreamDiskCacheHash(creds)}.json")

    private fun <T> readDiskCache(file: File, type: Type): XtreamDiskCache<T>? {
        val parentExists = file.parentFile?.exists() == true
        val parentFiles = runCatching { file.parentFile?.listFiles()?.map { it.name } }.getOrNull()
        if (!file.exists()) {
            System.err.println("[VOD-Cache] Disk cache file not found: ${file.absolutePath} (parent exists=$parentExists, parent files=$parentFiles)")
            return null
        }
        val sizeKb = file.length() / 1024
        System.err.println("[VOD-Cache] Reading disk cache: ${file.name} (${sizeKb}KB)")
        return runCatching {
            file.bufferedReader(StandardCharsets.UTF_8).use { reader ->
                gson.fromJson<XtreamDiskCache<T>>(reader, type)
            }
        }.onFailure { e ->
            System.err.println("[VOD-Cache] Failed to read disk cache: ${file.name}: ${e.message}")
        }.getOrNull()
    }

    private fun <T> writeDiskCache(file: File, savedAtMs: Long, items: List<T>) {
        runCatching {
            file.parentFile?.mkdirs()
            val tmpFile = File(file.parentFile, "${file.name}.tmp")
            java.io.FileOutputStream(tmpFile).use { fos ->
                java.io.BufferedWriter(java.io.OutputStreamWriter(fos, StandardCharsets.UTF_8)).use { writer ->
                    gson.toJson(XtreamDiskCache(savedAtMs, items), writer)
                    writer.flush()
                }
                runCatching { fos.fd.sync() }  // Best-effort flush to disk; may fail on emulator
            }
            if (!tmpFile.renameTo(file)) {
                tmpFile.copyTo(file, overwrite = true)
                tmpFile.delete()
            }
            System.err.println("[VOD-Cache] Wrote disk cache: ${file.name} (${file.length() / 1024}KB), exists=${file.exists()}")
        }.onFailure { e ->
            System.err.println("[VOD-Cache] Failed to write disk cache: ${file.name}: ${e.message}")
        }
    }

    private val vodDiskCacheType: Type by lazy {
        TypeToken.getParameterized(XtreamDiskCache::class.java, XtreamVodStream::class.java).type
    }
    private val seriesDiskCacheType: Type by lazy {
        TypeToken.getParameterized(XtreamDiskCache::class.java, XtreamSeriesItem::class.java).type
    }

    // ── Restructured load methods: disk cache + non-blocking network ─────

    private suspend fun loadXtreamVodStreams(
        creds: XtreamCredentials,
        fast: Boolean = false
    ): List<XtreamVodStream> {
        return withContext(Dispatchers.IO) {
            // 1. Fast check: in-memory cache (no lock needed, fields are @Volatile)
            ensureXtreamVodCacheOwnership(creds)
            val now = System.currentTimeMillis()
            if (cachedXtreamVodStreams.isNotEmpty() && now - xtreamVodLoadedAtMs < xtreamVodCacheMs) {
                if (cachedVodIdIndex?.items !== cachedXtreamVodStreams) {
                    cachedVodIdIndex = buildVodIdIndex(cachedXtreamVodStreams)
                }
                return@withContext cachedXtreamVodStreams
            }

            // 2. Check disk cache (fast — reading a file, not a network call)
            val diskFile = vodDiskCacheFile(creds)
            val diskCache: XtreamDiskCache<XtreamVodStream>? = readDiskCache(diskFile, vodDiskCacheType)
            if (diskCache != null && diskCache.items.isNotEmpty() && now - diskCache.savedAtMs < xtreamVodCacheMs) {
                System.err.println("[VOD-Cache] Loaded ${diskCache.items.size} VOD streams from disk cache (age ${(now - diskCache.savedAtMs) / 1000}s)")
                cachedXtreamVodStreams = diskCache.items
                xtreamVodLoadedAtMs = diskCache.savedAtMs
                cachedVodIdIndex = buildVodIdIndex(diskCache.items)
                return@withContext diskCache.items
            }

            // 3. Download from network — NO mutex held during download
            System.err.println("[VOD-Cache] Downloading VOD streams from network...")
            val downloadStart = System.currentTimeMillis()
            val url = "${creds.baseUrl}/player_api.php?username=${creds.username}&password=${creds.password}&action=get_vod_streams"
            val vod: List<XtreamVodStream> =
                requestJson(
                    url,
                    TypeToken.getParameterized(List::class.java, XtreamVodStream::class.java).type,
                    client = if (fast) xtreamLookupHttpClient else iptvHttpClient
                ) ?: emptyList()
            val elapsed = System.currentTimeMillis() - downloadStart
            System.err.println("[VOD-Cache] Downloaded ${vod.size} VOD streams in ${elapsed}ms")

            // 4. Swap into memory cache
            if (vod.isNotEmpty()) {
                val writeTime = System.currentTimeMillis()
                cachedXtreamVodStreams = vod
                xtreamVodLoadedAtMs = writeTime
                cachedVodIdIndex = buildVodIdIndex(vod)
                // 5. Persist to disk in background
                runCatching { writeDiskCache(diskFile, writeTime, vod) }
                System.err.println("[VOD-Cache] Saved VOD streams to disk cache")
            } else if (diskCache != null && diskCache.items.isNotEmpty()) {
                // Network returned empty — use stale disk cache
                System.err.println("[VOD-Cache] Network returned empty, using stale disk cache (${diskCache.items.size} items)")
                cachedXtreamVodStreams = diskCache.items
                xtreamVodLoadedAtMs = diskCache.savedAtMs
                cachedVodIdIndex = buildVodIdIndex(diskCache.items)
                return@withContext diskCache.items
            }

            vod
        }
    }

    private suspend fun getXtreamVodStreams(
        creds: XtreamCredentials,
        allowNetwork: Boolean,
        fast: Boolean = false
    ): List<XtreamVodStream> {
        if (allowNetwork) return loadXtreamVodStreams(creds, fast = fast)
        // If no network allowed, try memory first, then disk
        ensureXtreamVodCacheOwnership(creds)
        if (cachedXtreamVodStreams.isNotEmpty()) return cachedXtreamVodStreams
        // Try disk cache even when network not allowed
        return withContext(Dispatchers.IO) {
            val diskCache: XtreamDiskCache<XtreamVodStream>? = readDiskCache(vodDiskCacheFile(creds), vodDiskCacheType)
            if (diskCache != null && diskCache.items.isNotEmpty()) {
                cachedXtreamVodStreams = diskCache.items
                xtreamVodLoadedAtMs = diskCache.savedAtMs
                cachedVodIdIndex = buildVodIdIndex(diskCache.items)
                diskCache.items
            } else {
                emptyList()
            }
        }
    }

    private suspend fun loadXtreamSeriesList(
        creds: XtreamCredentials,
        fast: Boolean = false
    ): List<XtreamSeriesItem> {
        return withContext(Dispatchers.IO) {
            // 1. Fast check: in-memory cache
            ensureXtreamVodCacheOwnership(creds)
            val now = System.currentTimeMillis()
            if (cachedXtreamSeries.isNotEmpty() && now - xtreamSeriesLoadedAtMs < xtreamVodCacheMs) {
                return@withContext cachedXtreamSeries
            }

            // 2. Check disk cache
            val diskFile = seriesDiskCacheFile(creds)
            val diskCache: XtreamDiskCache<XtreamSeriesItem>? = readDiskCache(diskFile, seriesDiskCacheType)
            if (diskCache != null && diskCache.items.isNotEmpty() && now - diskCache.savedAtMs < xtreamVodCacheMs) {
                System.err.println("[VOD-Cache] Loaded ${diskCache.items.size} series from disk cache (age ${(now - diskCache.savedAtMs) / 1000}s)")
                cachedXtreamSeries = diskCache.items
                xtreamSeriesLoadedAtMs = diskCache.savedAtMs
                return@withContext diskCache.items
            }

            // 3. Download from network — NO mutex held
            System.err.println("[VOD-Cache] Downloading series list from network...")
            val downloadStart = System.currentTimeMillis()
            val url = "${creds.baseUrl}/player_api.php?username=${creds.username}&password=${creds.password}&action=get_series"
            val series: List<XtreamSeriesItem> =
                requestJson(
                    url,
                    TypeToken.getParameterized(List::class.java, XtreamSeriesItem::class.java).type,
                    client = if (fast) xtreamLookupHttpClient else iptvHttpClient
                ) ?: emptyList()
            val elapsed = System.currentTimeMillis() - downloadStart
            System.err.println("[VOD-Cache] Downloaded ${series.size} series in ${elapsed}ms")

            // 4. Swap into memory cache
            if (series.isNotEmpty()) {
                val writeTime = System.currentTimeMillis()
                cachedXtreamSeries = series
                xtreamSeriesLoadedAtMs = writeTime
                // 5. Persist to disk
                runCatching { writeDiskCache(diskFile, writeTime, series) }
                System.err.println("[VOD-Cache] Saved series list to disk cache")
            } else if (diskCache != null && diskCache.items.isNotEmpty()) {
                System.err.println("[VOD-Cache] Network returned empty, using stale disk cache (${diskCache.items.size} items)")
                cachedXtreamSeries = diskCache.items
                xtreamSeriesLoadedAtMs = diskCache.savedAtMs
                return@withContext diskCache.items
            }

            series
        }
    }

    private suspend fun getXtreamSeriesList(
        creds: XtreamCredentials,
        allowNetwork: Boolean,
        fast: Boolean = false
    ): List<XtreamSeriesItem> {
        if (allowNetwork) return loadXtreamSeriesList(creds, fast = fast)
        // If no network allowed, try memory first, then disk
        ensureXtreamVodCacheOwnership(creds)
        if (cachedXtreamSeries.isNotEmpty()) return cachedXtreamSeries
        return withContext(Dispatchers.IO) {
            val diskCache: XtreamDiskCache<XtreamSeriesItem>? = readDiskCache(seriesDiskCacheFile(creds), seriesDiskCacheType)
            if (diskCache != null && diskCache.items.isNotEmpty()) {
                cachedXtreamSeries = diskCache.items
                xtreamSeriesLoadedAtMs = diskCache.savedAtMs
                diskCache.items
            } else {
                emptyList()
            }
        }
    }

    private suspend fun loadXtreamSeriesEpisodes(
        creds: XtreamCredentials,
        seriesId: Int,
        fast: Boolean = false
    ): List<XtreamSeriesEpisode> {
        ensureXtreamVodCacheOwnership(creds)
        val cached = cachedXtreamSeriesEpisodes[seriesId]
        if (!cached.isNullOrEmpty()) return cached

        val existingInFlight = xtreamSeriesEpisodeInFlightMutex.withLock {
            xtreamSeriesEpisodeInFlight[seriesId]
        }
        if (existingInFlight != null) return existingInFlight.await()

        return coroutineScope {
            val created = async(Dispatchers.IO) {
                val url = "${creds.baseUrl}/player_api.php?username=${creds.username}&password=${creds.password}&action=get_series_info&series_id=$seriesId"
                val info: JsonObject = requestJson(
                    url,
                    JsonObject::class.java,
                    client = if (fast) xtreamLookupHttpClient else iptvHttpClient
                ) ?: return@async emptyList()
                val parsed = parseXtreamSeriesEpisodes(info)
                if (!fast && parsed.isNotEmpty()) {
                    xtreamSeriesEpisodeCacheMutex.withLock {
                        val next = LinkedHashMap(cachedXtreamSeriesEpisodes)
                        next[seriesId] = parsed
                        while (next.size > maxSeriesEpisodeCacheEntries) {
                            val oldestKey = next.keys.firstOrNull() ?: break
                            next.remove(oldestKey)
                        }
                        cachedXtreamSeriesEpisodes = next
                    }
                }
                parsed
            }

            val deferred = xtreamSeriesEpisodeInFlightMutex.withLock {
                val race = xtreamSeriesEpisodeInFlight[seriesId]
                if (race != null) {
                    created.cancel()
                    race
                } else {
                    xtreamSeriesEpisodeInFlight = xtreamSeriesEpisodeInFlight + (seriesId to created)
                    created
                }
            }

            try {
                deferred.await()
            } finally {
                xtreamSeriesEpisodeInFlightMutex.withLock {
                    if (xtreamSeriesEpisodeInFlight[seriesId] === deferred) {
                        xtreamSeriesEpisodeInFlight = xtreamSeriesEpisodeInFlight - seriesId
                    }
                }
            }
        }
    }

    private suspend fun getXtreamSeriesEpisodes(
        creds: XtreamCredentials,
        seriesId: Int,
        allowNetwork: Boolean,
        fast: Boolean = false
    ): List<XtreamSeriesEpisode> {
        if (allowNetwork) return loadXtreamSeriesEpisodes(creds, seriesId, fast = fast)
        ensureXtreamVodCacheOwnership(creds)
        return cachedXtreamSeriesEpisodes[seriesId].orEmpty()
    }

    private fun parseXtreamSeriesEpisodes(root: JsonObject): List<XtreamSeriesEpisode> {
        val episodesElement = root.get("episodes") ?: return emptyList()
        val episodeObjects = mutableListOf<Pair<JsonObject, Int?>>()
        collectXtreamEpisodeObjects(episodesElement, seasonHint = null, out = episodeObjects)
        if (episodeObjects.isEmpty()) return emptyList()

        return episodeObjects.mapNotNull { (item, seasonHint) ->
            parseEpisodeObject(item, seasonHint = seasonHint, fallbackIndex = null)
        }
    }

    private fun parseSeasonKey(raw: String): Int? {
        if (raw.isBlank()) return null
        val parsed = raw.toIntOrNull() ?: SEASON_KEY_REGEX.find(raw)?.value?.toIntOrNull()
        return parsed?.takeIf { it in 0..99 }
    }

    private fun parseSeasonEpisodes(seasonKey: Int?, array: JsonArray): List<XtreamSeriesEpisode> {
        val out = mutableListOf<XtreamSeriesEpisode>()
        array.forEachIndexed { index, element ->
            val item = element?.asJsonObject ?: return@forEachIndexed
            parseEpisodeObject(item, seasonHint = seasonKey, fallbackIndex = index)?.let { out += it }
        }
        return out
    }

    private fun collectXtreamEpisodeObjects(
        element: com.google.gson.JsonElement?,
        seasonHint: Int?,
        out: MutableList<Pair<JsonObject, Int?>>
    ) {
        when {
            element == null || element.isJsonNull -> return
            element.isJsonArray -> {
                element.asJsonArray.forEach { child ->
                    collectXtreamEpisodeObjects(child, seasonHint, out)
                }
            }
            !element.isJsonObject -> return
            else -> {
                val obj = element.asJsonObject
                val objectSeasonHint = seasonHint
                    ?: parseFlexibleInt(obj.get("season"))
                    ?: parseFlexibleInt(obj.get("season_number"))
                    ?: parseFlexibleInt(obj.get("season_num"))
                if (looksLikeXtreamEpisodeObject(obj)) {
                    out += obj to objectSeasonHint
                    return
                }

                val nestedEpisodes = obj.get("episodes")
                if (nestedEpisodes != null && !nestedEpisodes.isJsonNull) {
                    collectXtreamEpisodeObjects(nestedEpisodes, objectSeasonHint, out)
                }

                obj.entrySet().forEach { (key, value) ->
                    if (key.equals("episodes", ignoreCase = true)) return@forEach
                    val keyedSeasonHint = parseSeasonKey(key) ?: objectSeasonHint
                    collectXtreamEpisodeObjects(value, keyedSeasonHint, out)
                }
            }
        }
    }

    private fun looksLikeXtreamEpisodeObject(obj: JsonObject): Boolean {
        if (parseFlexibleInt(obj.get("id")) != null) return true
        if (parseFlexibleInt(obj.get("stream_id")) != null) return true
        if (parseFlexibleInt(obj.get("episode_id")) != null) return true
        if (parseFlexibleInt(obj.get("episode_num")) != null) return true
        if (parseFlexibleInt(obj.get("episode_number")) != null) return true
        val infoObj = obj.get("info")?.takeIf { it.isJsonObject }?.asJsonObject
        if (infoObj != null) {
            if (parseFlexibleInt(infoObj.get("id")) != null) return true
            if (parseFlexibleInt(infoObj.get("stream_id")) != null) return true
            if (parseFlexibleInt(infoObj.get("episode_id")) != null) return true
            if (parseFlexibleInt(infoObj.get("episode_num")) != null) return true
            if (parseFlexibleInt(infoObj.get("episode_number")) != null) return true
        }
        return false
    }

    private fun parseEpisodeObject(
        item: JsonObject,
        seasonHint: Int?,
        fallbackIndex: Int?
    ): XtreamSeriesEpisode? {
        val infoObj = item.get("info")?.takeIf { it.isJsonObject }?.asJsonObject
        val rawTitle = item.get("title")?.asString?.trim().orEmpty().ifBlank {
            infoObj?.get("title")?.asString?.trim().orEmpty()
        }
        val parsedSeasonEpisode = extractSeasonEpisodeFromName(rawTitle)
        val resolvedSeason = seasonHint
            ?: parseFlexibleInt(item.get("season"))
            ?: parseFlexibleInt(item.get("season_number"))
            ?: parseFlexibleInt(item.get("season_num"))
            ?: parseFlexibleInt(infoObj?.get("season"))
            ?: parseFlexibleInt(infoObj?.get("season_number"))
            ?: parseFlexibleInt(infoObj?.get("season_num"))
            ?: parsedSeasonEpisode?.first
            ?: 1
        val episodeNum = parseFlexibleInt(item.get("episode_num"))
            ?: parseFlexibleInt(item.get("episode"))
            ?: parseFlexibleInt(item.get("episode_number"))
            ?: parseFlexibleInt(item.get("number"))
            ?: parseFlexibleInt(item.get("sort"))
            ?: parseFlexibleInt(item.get("sort_order"))
            ?: parseFlexibleInt(infoObj?.get("episode_num"))
            ?: parseFlexibleInt(infoObj?.get("episode"))
            ?: parseFlexibleInt(infoObj?.get("episode_number"))
            ?: parseFlexibleInt(infoObj?.get("number"))
            ?: parseFlexibleInt(infoObj?.get("sort"))
            ?: parsedSeasonEpisode?.second
            ?: extractEpisodeOnlyFromName(rawTitle)
            ?: fallbackIndex?.let { it + 1 }
            ?: 1
        val id = parseFlexibleInt(item.get("id"))
            ?: parseFlexibleInt(item.get("stream_id"))
            ?: parseFlexibleInt(item.get("episode_id"))
            ?: parseFlexibleInt(infoObj?.get("id"))
            ?: parseFlexibleInt(infoObj?.get("stream_id"))
            ?: parseFlexibleInt(infoObj?.get("episode_id"))
            ?: return null
        val title = rawTitle.ifBlank { "S${resolvedSeason}E${episodeNum}" }
        val ext = item.get("container_extension")?.asString?.trim()?.ifBlank { null }
            ?: infoObj?.get("container_extension")?.asString?.trim()?.ifBlank { null }
        return XtreamSeriesEpisode(
            id = id,
            season = resolvedSeason,
            episode = episodeNum,
            title = title,
            containerExtension = ext
        )
    }

    private fun parseFlexibleInt(element: com.google.gson.JsonElement?): Int? {
        if (element == null || element.isJsonNull) return null
        return runCatching {
            when {
                element.isJsonPrimitive && element.asJsonPrimitive.isNumber -> {
                    val number = element.asDouble
                    if (number.isFinite()) number.toInt() else null
                }
                element.isJsonPrimitive -> {
                    val raw = element.asString.trim()
                    raw.toIntOrNull()
                        ?: raw.toDoubleOrNull()?.toInt()
                        ?: FLEXIBLE_INT_REGEX.find(raw)?.value?.toIntOrNull()
                }
                else -> null
            }
        }.getOrNull()
    }

    private fun normalizeLookupText(value: String): String {
        if (value.isBlank()) return ""
        return value
            .replace(BRACKET_CONTENT_REGEX, " ")
            .replace(PAREN_CONTENT_REGEX, " ")
            .replace(YEAR_PAREN_REGEX, " ")
            .replace(SEASON_TOKEN_REGEX, " ")
            .replace(EPISODE_TOKEN_REGEX, " ")
            .replace(RELEASE_TAG_REGEX, " ")
            .lowercase(Locale.US)
            .replace(NON_ALPHA_NUM_REGEX, " ")
            .trim()
            .replace(MULTI_SPACE_REGEX, " ")
    }

    private val titleTokenNoise = setOf(
        "the", "a", "an", "and", "of", "to", "in", "on",
        "complete", "series", "tv", "show", "season", "seasons",
        "episode", "episodes", "part", "collection", "pack"
    )

    private fun extractTitleTokens(value: String): Set<String> {
        val normalized = normalizeLookupText(value)
        return extractTitleTokensFromNormalized(normalized)
    }

    /** Fast version: skips redundant normalizeLookupText when input is already normalized. */
    private fun extractTitleTokensFromNormalized(normalized: String): Set<String> {
        if (normalized.isBlank()) return emptySet()
        return normalized
            .split(' ')
            .asSequence()
            .map { it.trim() }
            .filter { it.length >= 3 && it !in titleTokenNoise }
            .toSet()
    }

    private fun toCanonicalTitleKey(value: String): String {
        val tokens = extractTitleTokens(value)
        if (tokens.isEmpty()) return ""
        return tokens.sorted().joinToString(" ")
    }

    /** Fast version: uses pre-computed tokens. */
    private fun toCanonicalTitleKeyFromTokens(tokens: Set<String>): String {
        if (tokens.isEmpty()) return ""
        return tokens.sorted().joinToString(" ")
    }

    private fun normalizeImdbId(value: String?): String? {
        if (value.isNullOrBlank()) return null
        val cleaned = value.trim().lowercase(Locale.US)
        val match = IMDB_ID_REGEX.find(cleaned)?.value
        return match ?: cleaned.takeIf { it.startsWith("tt") && it.length >= 7 }
    }

    private fun normalizeTmdbId(value: String?): String? {
        if (value.isNullOrBlank()) return null
        val digits = TMDB_ID_REGEX.find(value.trim())?.value
        return digits?.trimStart('0')?.ifBlank { "0" }
    }

    private fun normalizeTmdbId(value: Int?): String? {
        if (value == null || value <= 0) return null
        return value.toString()
    }

    private fun parseYear(value: String): Int? {
        return YEAR_REGEX
            .find(value)
            ?.value
            ?.toIntOrNull()
    }

    private fun extractSeasonEpisodeFromName(value: String): Pair<Int, Int>? {
        val normalized = value.lowercase(Locale.US)
        SEASON_EPISODE_PATTERNS.forEach { regex ->
            val match = regex.find(normalized) ?: return@forEach
            val season = match.groupValues.getOrNull(1)?.toIntOrNull() ?: return@forEach
            val episode = match.groupValues.getOrNull(2)?.toIntOrNull() ?: return@forEach
            return Pair(season, episode)
        }
        return null
    }

    private fun extractEpisodeOnlyFromName(value: String): Int? {
        val normalized = value.lowercase(Locale.US)
        EPISODE_ONLY_PATTERNS.forEach { regex ->
            val match = regex.find(normalized) ?: return@forEach
            val episode = match.groupValues.getOrNull(1)?.toIntOrNull() ?: return@forEach
            if (episode > 0) return episode
        }
        return null
    }

    private fun pickEpisodeMatch(
        episodes: List<XtreamSeriesEpisode>,
        season: Int,
        episode: Int
    ): XtreamSeriesEpisode? {
        if (episodes.isEmpty()) return null
        episodes.firstOrNull { it.season == season && it.episode == episode }?.let { return it }

        // Some providers flatten seasoning; if exactly one candidate has the episode number, use it.
        val byEpisode = episodes.filter { it.episode == episode }
        if (byEpisode.size == 1) return byEpisode.first()
        if (byEpisode.isNotEmpty()) {
            return byEpisode.minByOrNull { kotlin.math.abs(it.season - season) }
        }
        return null
    }

    private fun scoreNameMatch(providerName: String, normalizedInput: String): Int {
        val normalizedProvider = normalizeLookupText(providerName)
        if (normalizedProvider.isBlank() || normalizedInput.isBlank()) return 0
        if (normalizedProvider == normalizedInput) return 120
        if (normalizedProvider.contains(normalizedInput)) return 90
        if (normalizedInput.contains(normalizedProvider)) return 70
        if (normalizedProvider.startsWith(normalizedInput) || normalizedInput.startsWith(normalizedProvider)) return 68
        val stopWords = setOf("the", "a", "an", "and", "of", "part", "episode", "season", "movie")
        val providerWords = normalizedProvider
            .split(' ')
            .filter { it.isNotBlank() && it !in stopWords }
            .toSet()
        val inputWords = normalizedInput
            .split(' ')
            .filter { it.isNotBlank() && it !in stopWords }
            .toSet()
        if (providerWords.isEmpty() || inputWords.isEmpty()) return 0
        val overlap = providerWords.intersect(inputWords).size
        val coverage = overlap.toDouble() / inputWords.size.toDouble()
        return when {
            overlap >= 2 && coverage >= 0.75 -> 75 + overlap
            overlap >= 2 -> 55 + overlap
            overlap == 1 && inputWords.size >= 3 && providerWords.size >= 3 -> 42
            overlap == 1 && inputWords.size <= 2 -> 35
            else -> 0
        }
    }

    private fun looseSeriesTitleScore(providerName: String, normalizedInput: String): Int {
        val normalizedProvider = normalizeLookupText(providerName)
        if (normalizedProvider.isBlank() || normalizedInput.isBlank()) return 0
        val providerWords = normalizedProvider.split(' ').filter { it.length >= 3 }.toSet()
        val inputWords = normalizedInput.split(' ').filter { it.length >= 3 }.toSet()
        if (providerWords.isEmpty() || inputWords.isEmpty()) return 0
        val overlap = providerWords.intersect(inputWords).size
        return when {
            overlap >= 2 -> 50 + overlap
            overlap == 1 -> 24
            else -> 0
        }
    }

    private fun rankSeriesCandidate(
        providerName: String,
        normalizedInput: String,
        baseScore: Int
    ): Int {
        val normalizedProvider = normalizeLookupText(providerName)
        if (normalizedProvider.isBlank() || normalizedInput.isBlank()) return baseScore
        var score = baseScore
        if (normalizedProvider == normalizedInput) score += 500
        if (normalizedProvider.contains(normalizedInput)) score += 320
        if (normalizedInput.contains(normalizedProvider)) score += 180
        val providerHead = normalizedProvider.split(' ').take(2).joinToString(" ")
        if (providerHead.isNotBlank() && normalizedInput.startsWith(providerHead)) score += 110
        return score
    }

    private fun inferQuality(value: String): String {
        val lower = value.lowercase(Locale.US)
        return when {
            lower.contains("2160") || lower.contains("4k") -> "4K"
            lower.contains("1080") -> "1080p"
            lower.contains("720") -> "720p"
            lower.contains("480") -> "480p"
            else -> "VOD"
        }
    }

    private fun vodQualityRank(value: String): Int {
        val lower = value.lowercase(Locale.US)
        return when {
            lower.contains("2160") || lower.contains("4k") -> 500
            lower.contains("1080") -> 400
            lower.contains("720") -> 300
            lower.contains("480") -> 200
            lower.contains("360") -> 100
            else -> 0
        }
    }

    private fun resolveXtreamCredentials(url: String): XtreamCredentials? {
        if (url.isBlank()) return null
        val parsed = normalizeStoredIptvUrl(url).toHttpUrlOrNull() ?: return null
        var username = parsed.queryParameter("username")?.trim()?.ifBlank { null }
            ?: parsed.queryParameter("user")?.trim()?.ifBlank { null }
            ?: parsed.queryParameter("uname")?.trim()?.ifBlank { null }
            ?: ""
        var password = parsed.queryParameter("password")?.trim()?.ifBlank { null }
            ?: parsed.queryParameter("pass")?.trim()?.ifBlank { null }
            ?: parsed.queryParameter("pwd")?.trim()?.ifBlank { null }
            ?: ""

        // Try extracting from path if query params are missing.
        if (username.isBlank() || password.isBlank()) {
            val segments = parsed.pathSegments
            val knownPrefix = segments.firstOrNull()?.lowercase(Locale.US)
            if (segments.size >= 4 && knownPrefix in setOf("live", "movie", "series")) {
                username = segments[1]
                password = segments[2]
            } else if (segments.size >= 3 && segments.last().substringBefore('.').toIntOrNull() != null) {
                username = segments[segments.size - 3]
                password = segments[segments.size - 2]
            }
        }

        if (username.isBlank() || password.isBlank()) return null
        // Accept any URL with username/password params; derive baseUrl from scheme+host+port
        val path = parsed.encodedPath.lowercase(Locale.US)
        val knownXtreamPath = path.endsWith("/get.php") || path.endsWith("/xmltv.php") || path.endsWith("/player_api.php")
        val baseUrl = if (knownXtreamPath) {
            parsed.toXtreamBaseUrl()
        } else {
            // Derive from scheme + host + port for non-standard paths
            buildString {
                append(parsed.scheme)
                append("://")
                append(parsed.host)
                if (parsed.port != if (parsed.scheme == "https") 443 else 80) {
                    append(":${parsed.port}")
                }
            }
        }
        return XtreamCredentials(baseUrl, username, password)
    }

    private fun resolveXtreamCredentials(playlist: IptvPlaylistEntry): XtreamCredentials? {
        playlist.allEpgUrls().forEach { epgUrl ->
            resolveXtreamCredentials(epgUrl)?.let { return it }
        }
        return resolveXtreamCredentials(playlist.m3uUrl)
    }

    private fun resolveScopedEpgCandidates(config: IptvConfig): List<ScopedEpgCandidate> {
        val allLists = activePlaylists(config)
        return buildList {
            allLists.forEach { list ->
                list.allEpgUrls().forEach { add(ScopedEpgCandidate(it, list.id)) }
                val creds = resolveXtreamCredentials(list)
                if (creds != null) {
                    add(ScopedEpgCandidate("${creds.baseUrl}/xmltv.php?username=${creds.username}&password=${creds.password}", list.id))
                    add(ScopedEpgCandidate("${creds.baseUrl}/get.php?username=${creds.username}&password=${creds.password}&type=xmltv", list.id))
                    add(ScopedEpgCandidate("${creds.baseUrl}/get.php?username=${creds.username}&password=${creds.password}&type=xml", list.id))
                    add(ScopedEpgCandidate("${creds.baseUrl}/xmltv.php", list.id))
                    add(ScopedEpgCandidate("${creds.baseUrl}/get.php?username=${creds.username}&password=${creds.password}", list.id))
                }
            }
            discoveredM3uEpgUrls.forEach { add(ScopedEpgCandidate(it)) }
        }
            .filter { it.url.isNotBlank() }
            .distinctBy { "${it.playlistId.orEmpty()}|${it.url}" }
    }

    private fun channelsForScopedEpgCandidate(
        candidate: ScopedEpgCandidate,
        channels: List<IptvChannel>
    ): List<IptvChannel> {
        val playlistId = candidate.playlistId?.trim().orEmpty()
        if (playlistId.isBlank()) return channels
        val prefix = "$playlistId:"
        return channels.filter { channel -> channel.id.startsWith(prefix) }
    }

    private fun groupXtreamChannelsByCredentials(
        config: IptvConfig,
        channels: List<IptvChannel>
    ): LinkedHashMap<XtreamCredentials, MutableList<IptvChannel>> {
        val activePlaylistById = activePlaylists(config).associateBy { it.id }
        val fallbackCreds = resolveXtreamCredentials(config)
        val result = LinkedHashMap<XtreamCredentials, MutableList<IptvChannel>>()
        channels.forEach { channel ->
            if (resolveXtreamStreamId(channel) == null) return@forEach
            val playlistId = channel.id.substringBefore(':', missingDelimiterValue = "")
                .takeIf { it.isNotBlank() }
            val playlistCreds = playlistId
                ?.let { activePlaylistById[it] }
                ?.let { resolveXtreamCredentials(it) }
            val creds = playlistCreds ?: fallbackCreds ?: return@forEach
            result.getOrPut(creds) { mutableListOf() }.add(channel)
        }
        return result
    }

    private fun resolveXtreamCredentials(config: IptvConfig): XtreamCredentials? {
        activePlaylists(config).forEach { playlist ->
            resolveXtreamCredentials(playlist)?.let { return it }
        }
        resolveXtreamCredentials(config.epgUrl)?.let { return it }
        resolveXtreamCredentials(config.m3uUrl)?.let { return it }
        return null
    }

    private suspend fun discoverEmbeddedEpgSourcesIfNeeded(playlists: List<IptvPlaylistEntry>) {
        if (discoveredM3uEpgUrls.isNotEmpty()) return
        val candidates = playlists.filter { playlist ->
            playlist.m3uUrl.isNotBlank() &&
                playlist.allEpgUrls().isEmpty() &&
                resolveXtreamCredentials(playlist) == null
        }
        if (candidates.isEmpty()) return

        coroutineScope {
            candidates.map { playlist ->
                async(Dispatchers.IO) {
                    discoverM3uHeaderEpgUrls(playlist.m3uUrl)
                }
            }.awaitAll()
        }
            .flatten()
            .map { it.trim() }
            .filter { it.isNotBlank() }
            .forEach { discoveredM3uEpgUrls.add(it) }
    }

    private fun discoverM3uHeaderEpgUrls(m3uUrl: String): List<String> {
        return runCatching {
            val request = Request.Builder()
                .url(validatedIptvHttpUrl(m3uUrl, "IPTV playlist URL"))
                .build()
            iptvCatalogHttpClient.newCall(request).execute().use { response ->
                if (!response.isSuccessful) return@use emptyList()
                val body = response.body ?: return@use emptyList()
                BufferedReader(InputStreamReader(body.byteStream(), StandardCharsets.UTF_8), 32 * 1024).use { reader ->
                    repeat(32) {
                        val line = reader.readLine() ?: return@use emptyList()
                        val trimmed = line.trim()
                        if (trimmed.startsWith("#EXTM3U", ignoreCase = true)) {
                            return@use extractM3uDeclaredEpgUrls(trimmed)
                        }
                        if (trimmed.isNotEmpty() && !trimmed.startsWith("#")) {
                            return@use emptyList()
                        }
                    }
                    emptyList()
                }
            }
        }.getOrDefault(emptyList())
    }

    private suspend fun fetchXtreamLiveChannels(
        creds: XtreamCredentials,
        onProgress: (IptvLoadProgress) -> Unit
    ): List<IptvChannel> {
        val categoriesUrl = "${creds.baseUrl}/player_api.php?username=${creds.username}&password=${creds.password}&action=get_live_categories"
        val streamsUrl = "${creds.baseUrl}/player_api.php?username=${creds.username}&password=${creds.password}&action=get_live_streams"

        onProgress(IptvLoadProgress("Loading categories...", 10))
        val categories: List<XtreamLiveCategory> =
            requestJson(
                categoriesUrl,
                TypeToken.getParameterized(List::class.java, XtreamLiveCategory::class.java).type,
                client = iptvCatalogHttpClient
            ) ?: emptyList()
        val categoryMap = categories
            .associate { it.categoryId.orEmpty() to (it.categoryName?.trim().orEmpty().ifBlank { "Uncategorized" }) }

        onProgress(IptvLoadProgress(context.getString(R.string.iptv_progress_loading_live), 35))
        val streams: List<XtreamLiveStream> =
            requestJson(
                streamsUrl,
                TypeToken.getParameterized(List::class.java, XtreamLiveStream::class.java).type,
                client = iptvCatalogHttpClient
            ) ?: emptyList()
        if (streams.isEmpty()) return emptyList()

        val total = streams.size.coerceAtLeast(1)
        return streams.mapIndexedNotNull { index, stream ->
            if (index % 500 == 0) {
                val pct = (35 + ((index.toLong() * 55L) / total.toLong())).toInt().coerceIn(35, 90)
                onProgress(IptvLoadProgress("Parsing provider streams... $index/$total", pct))
            }

            val streamId = stream.streamId ?: return@mapIndexedNotNull null
            val name = stream.name?.trim().orEmpty().ifBlank { return@mapIndexedNotNull null }
            val group = categoryMap[stream.categoryId.orEmpty()].orEmpty().ifBlank { "Uncategorized" }
            val streamUrl = "${creds.baseUrl}/live/${creds.username}/${creds.password}/$streamId.ts"

            IptvChannel(
                id = "xtream:$streamId",
                name = name,
                streamUrl = streamUrl,
                group = group,
                logo = stream.streamIcon?.takeIf { it.isNotBlank() },
                epgId = stream.epgChannelId?.trim()?.takeIf { it.isNotBlank() },
                rawTitle = name,
                xtreamStreamId = streamId,
                catchupDays = (stream.tvArchiveDuration ?: stream.tvArchive ?: 0).coerceAtLeast(0),
                catchupType = if ((stream.tvArchive ?: 0) > 0 || (stream.tvArchiveDuration ?: 0) > 0) "xtream" else null
            )
        }
    }

    private suspend fun <T> requestJson(
        url: String,
        type: Type,
        client: OkHttpClient = iptvHttpClient
    ): T? = suspendCancellableCoroutine { continuation ->
        val request = Request.Builder()
            .url(url)
            .header("User-Agent", OkHttpProvider.userAgentOr(IPTV_USER_AGENT))
            .header("Accept", "application/json,*/*")
            .get()
            .build()

        val call = client.newCall(request)

        continuation.invokeOnCancellation {
            call.cancel()
        }

        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                if (continuation.isActive) continuation.resume(null)
            }

            override fun onResponse(call: Call, response: Response) {
                if (!continuation.isActive) {
                    response.close()
                    return
                }
                response.use {
                    if (!it.isSuccessful) {
                        continuation.resume(null)
                        return
                    }
                    val responseBody = it.body
                    if (responseBody == null) {
                        continuation.resume(null)
                        return
                    }
                    try {
                        responseBody.charStream().use { reader ->
                            val result = gson.fromJson<T>(reader, type)
                            if (continuation.isActive) continuation.resume(result)
                        }
                    } catch (error: Throwable) {
                        System.err.println("IptvRepository: JSON request failed for ${redactIptvUrl(url)}: ${error.message}")
                        if (continuation.isActive) continuation.resume(null)
                    }
                }
            }
        })
    }

    private fun fetchAndParseM3uOnce(
        url: String,
        onProgress: (IptvLoadProgress) -> Unit
    ): List<IptvChannel> {
        val request = Request.Builder()
            .url(validatedIptvHttpUrl(url, "IPTV playlist URL"))
            .header("User-Agent", OkHttpProvider.userAgentOr(IPTV_USER_AGENT))
            .header("Accept", "*/*")
            .get()
            .build()
        iptvHttpClient.newCall(request).execute().use { response ->
            val raw = response.body?.byteStream() ?: throw IllegalStateException(context.getString(R.string.iptv_m3u_empty))
            val contentLength = response.body?.contentLength()?.takeIf { it > 0L }
            val progressStream = ProgressInputStream(raw) { bytesRead ->
                if (contentLength != null) {
                    val pct = ((bytesRead * 70L) / contentLength).toInt().coerceIn(8, 74)
                    onProgress(IptvLoadProgress("Downloading playlist... $pct%", pct))
                } else {
                    onProgress(IptvLoadProgress("Downloading playlist...", 15))
                }
            }
            val stream = BufferedInputStream(progressStream, 256 * 1024)
            if (!response.isSuccessful && !looksLikeM3u(stream)) {
                val rawPreview = response.peekBody(512).string().replace('\n', ' ').trim()
                // Strip HTML tags and CSS to produce a clean error message
                val cleanPreview = rawPreview
                    .replace(HTML_STYLE_REGEX, "")
                    .replace(HTML_SCRIPT_REGEX, "")
                    .replace(HTML_TAG_REGEX, " ")
                    .replace(CSS_BRACE_REGEX, "")
                    .replace(MULTI_SPACE_REGEX, " ")
                    .trim()
                    .take(150)
                val detail = when {
                    response.code == 403 -> "Access denied by the server. The IPTV provider may be blocking this request."
                    response.code == 404 -> "Playlist URL not found. Check the M3U URL in settings."
                    response.code in 500..599 -> "Server error (${response.code}). The IPTV provider may be temporarily down."
                    cleanPreview.isBlank() -> "HTTP ${response.code}"
                    else -> cleanPreview
                }
                throw IllegalStateException(context.getString(R.string.iptv_m3u_failed, response.code) + " " + detail)
            }
            onProgress(IptvLoadProgress("Parsing channels...", 78))
            return parseM3u(stream, onProgress)
        }
    }

    private fun fetchAndParseEpg(url: String, channels: List<IptvChannel>): Map<String, IptvNowNext> {
        val hasDbEntries = currentEpgIndexKey.isNotBlank() && runCatching {
            epgIndex.countPrograms(currentEpgIndexKey)
        }.getOrDefault(0) > 0

        fun epgRequest(targetUrl: String, userAgent: String, forceFull: Boolean = false): Request {
            val builder = Request.Builder()
                .url(validatedEpgHttpUrl(targetUrl, "EPG URL"))
                .header("User-Agent", userAgent)
                .header("Accept", "*/*")
                .header("Accept-Language", "en-US,en;q=0.9")
                .header("Cache-Control", "no-cache")

            if (hasDbEntries && !forceFull) {
                getEpgCachedEtag(targetUrl)?.let { etag ->
                    builder.header("If-None-Match", etag)
                }
                getEpgCachedLastModified(targetUrl)?.let { lm ->
                    builder.header("If-Modified-Since", lm)
                }
            }

            return builder.get().build()
        }

        val primaryUserAgent = OkHttpProvider.userAgentOr(IPTV_USER_AGENT)
        val fallbackUserAgent = OkHttpProvider.userAgentOr(BROWSER_USER_AGENT)
        var response = iptvHttpClient.newCall(epgRequest(url, primaryUserAgent)).execute()
        if (response.code == 304) {
            response.close()
            throw EpgNotModifiedException()
        }
        if (!response.isSuccessful && response.code in setOf(511, 403, 401)) {
            response.close()
            response = iptvHttpClient.newCall(
                epgRequest(url, fallbackUserAgent)
            ).execute()
            if (response.code == 304) {
                response.close()
                throw EpgNotModifiedException()
            }
        }
        response.use { safeResponse ->
            if (safeResponse.isSuccessful) {
                val etag = safeResponse.header("ETag")?.trim()
                val lastModified = safeResponse.header("Last-Modified")?.trim()
                if (etag != null || lastModified != null) {
                    saveEpgHttpCacheHeaders(url, etag, lastModified)
                }
            }
            val stream = safeResponse.body?.byteStream() ?: throw IllegalStateException(context.getString(R.string.epg_empty))
            val prepared = BufferedInputStream(prepareInputStream(stream, url))
            if (!safeResponse.isSuccessful && !looksLikeXmlTv(prepared)) {
                val preview = safeResponse.peekBody(220).string().replace('\n', ' ').trim()
                val detail = if (preview.isBlank()) "No response body." else preview
                throw IllegalStateException(context.getString(R.string.epg_failed, safeResponse.code) + " " + detail)
            }

            // Try streaming parse first (avoids disk I/O for the common case).
            // Only spool to disk and retry if the stream parse fails.
            try {
                val sanitized = BackslashEscapeSanitizingInputStream(prepared)
                return parseXmlTvNowNext(BufferedInputStream(sanitized), channels)
            } catch (streamError: Exception) {
                // Streaming parse failed – the network stream is consumed, so we
                // cannot retry from it.  Check if we got a useful partial result
                // or need to re-download.  Re-download and spool to disk for retries.
                val tmpFile = File.createTempFile("epg_", ".xml", context.cacheDir)
                try {
                    // Re-download
                    val retryResponse = iptvHttpClient.newCall(
                        epgRequest(url, primaryUserAgent, forceFull = true)
                    ).execute()
                    retryResponse.use { rr ->
                        val retryStream = rr.body?.byteStream()
                            ?: throw IllegalStateException(context.getString(R.string.epg_retry_empty))
                        BufferedInputStream(prepareInputStream(retryStream, url)).use { input ->
                            BufferedOutputStream(tmpFile.outputStream()).use { output ->
                                input.copyTo(output, DEFAULT_BUFFER_SIZE)
                            }
                        }
                    }

                    try {
                        return FileInputStream(tmpFile).use { input ->
                            parseXmlTvNowNext(BufferedInputStream(input), channels)
                        }
                    } catch (_: Exception) {
                        // Final fallback: SAX parser (different engine).
                        return FileInputStream(tmpFile).use { input ->
                            val sanitized2 = BackslashEscapeSanitizingInputStream(BufferedInputStream(input))
                            parseXmlTvNowNextWithSax(BufferedInputStream(sanitized2), channels)
                        }
                    }
                } finally {
                    tmpFile.delete()
                    cleanupStaleEpgTempFiles(maxAgeMs = 60_000L)
                }
            }
        }
    }

    // ── Xtream Short EPG (instant loading) ─────────────────────────────

    /**
     * Data class for a single EPG listing returned by the Xtream short EPG APIs.
     * Fields like `title` and `description` are base64-encoded by the server.
     */
    private data class XtreamEpgListing(
        val id: String? = null,
        @SerializedName("epg_id") val epgId: String? = null,
        val title: String? = null,
        val lang: String? = null,
        val start: String? = null,
        val end: String? = null,
        val description: String? = null,
        @SerializedName("channel_id") val channelId: String? = null,
        @SerializedName("start_timestamp") val startTimestamp: String? = null,
        @SerializedName("stop_timestamp") val stopTimestamp: String? = null,
        @SerializedName("stream_id") val streamId: String? = null,
        @SerializedName("has_archive") val hasArchive: Int? = null
    )

    private data class XtreamEpgResponse(
        @SerializedName("epg_listings") val epgListings: List<XtreamEpgListing>? = null
    )

    private fun parseXtreamListingsFromJson(response: JsonObject?): List<XtreamEpgListing> {
        val listingsElement = response?.get("epg_listings") ?: return emptyList()
        return when {
            listingsElement.isJsonArray -> listingsElement.asJsonArray.mapNotNull { it.toXtreamEpgListingOrNull() }
            listingsElement.isJsonObject -> listingsElement.asJsonObject.entrySet()
                .asSequence()
                .mapNotNull { it.value.toXtreamEpgListingOrNull() }
                .toList()
            else -> emptyList()
        }
    }

    private fun trimXtreamListingsToGuideWindow(
        listings: List<XtreamEpgListing>,
        nowMs: Long = System.currentTimeMillis(),
        pastWindowMs: Long = xmlTvPastWindowMs,
        futureWindowMs: Long = xmlTvFutureWindowMs
    ): List<XtreamEpgListing> {
        if (listings.isEmpty()) return listings
        val startBound = nowMs - pastWindowMs
        val endBound = nowMs + futureWindowMs
        return listings.filter { listing ->
            val startMs = listing.startTimestamp?.toLongOrNull()?.let { it * 1000L }
                ?: parseXtreamDateTime(listing.start)
                ?: return@filter false
            val stopMs = listing.stopTimestamp?.toLongOrNull()?.let { it * 1000L }
                ?: parseXtreamDateTime(listing.end)
                ?: return@filter false
            stopMs > startBound && startMs < endBound
        }
    }

    private fun JsonElement.toXtreamEpgListingOrNull(): XtreamEpgListing? =
        runCatching { gson.fromJson(this, XtreamEpgListing::class.java) }.getOrNull()

    private fun List<XtreamEpgListing>.withRequestedStreamId(streamId: Int): List<XtreamEpgListing> {
        if (isEmpty()) return this
        val streamIdValue = streamId.toString()
        return map { listing ->
            if (listing.streamId.isNullOrBlank()) {
                listing.copy(streamId = streamIdValue)
            } else {
                listing
            }
        }
    }

    /**
     * Decode a base64-encoded string from the Xtream short EPG API.
     * Returns the decoded text or the original string if decoding fails.
     */
    private fun decodeBase64Field(encoded: String?): String {
        if (encoded.isNullOrBlank()) return ""
        return try {
            String(Base64.decode(encoded, Base64.DEFAULT), StandardCharsets.UTF_8).trim()
        } catch (_: Exception) {
            encoded.trim()  // Not base64; return raw
        }
    }

    /**
     * Fetch EPG data using the Xtream Codes `get_simple_data_table` API.
     * This returns current/next program data for ALL channels in one lightweight
     * JSON response, instead of downloading a 20-150+ MB XMLTV XML file.
     *
     * Returns null if the API is not supported or fails (caller should fall back to XMLTV).
     */
    /**
     * Determine the Xtream numeric stream identifier for a channel.
     *
     * @param ch The channel to inspect; may contain an explicit `xtreamStreamId` or an `id` with the `xtream:{id}` form.
     * @return The numeric Xtream stream id if present, `null` otherwise.
     */
    private fun resolveXtreamStreamId(ch: IptvChannel): Int? {
        ch.xtreamStreamId?.let { return it }
        if (ch.id.startsWith("xtream:")) {
            return ch.id.removePrefix("xtream:").toIntOrNull()
        }
        val parsed = ch.streamUrl.toHttpUrlOrNull()
        if (parsed != null) {
            val segments = parsed.pathSegments
            val knownPrefix = segments.firstOrNull()?.lowercase(Locale.US)
            if (knownPrefix in setOf("live", "movie", "series") && segments.size >= 4) {
                return segments.lastOrNull()
                    ?.substringBefore('.')
                    ?.toIntOrNull()
            }
            if (resolveXtreamCredentials(ch.streamUrl) != null && segments.size >= 3) {
                return segments.lastOrNull()
                    ?.substringBefore('.')
                    ?.toIntOrNull()
            }
        }
        return null
    }

    private suspend fun fetchXtreamShortEpgForActiveProviders(
        config: IptvConfig,
        channels: List<IptvChannel>,
        onProgress: (IptvLoadProgress) -> Unit,
        listingLimit: Int = xtreamShortEpgLimit
    ): Map<String, IptvNowNext>? {
        val groups = groupXtreamChannelsByCredentials(config, channels)
        if (groups.isEmpty()) return null

        val merged = ConcurrentHashMap<String, IptvNowNext>()
        groups.entries.forEachIndexed { index, (creds, providerChannels) ->
            if (providerChannels.isEmpty()) return@forEachIndexed
            onProgress(
                IptvLoadProgress(
                    context.getString(R.string.iptv_progress_loading_epg_provider, index + 1, groups.size),
                    90 + ((index * 6) / groups.size.coerceAtLeast(1))
                )
            )
            val parsed = fetchXtreamShortEpg(creds, providerChannels, onProgress, listingLimit)
            if (!parsed.isNullOrEmpty()) {
                merged.putAll(parsed)
            }
        }
        return merged.takeIf { hasAnyProgramData(it) }
    }

    private suspend fun fetchXtreamFullEpgForActiveProviders(
        config: IptvConfig,
        channels: List<IptvChannel>,
        onProgress: (IptvLoadProgress) -> Unit
    ): Map<String, IptvNowNext>? {
        val groups = groupXtreamChannelsByCredentials(config, channels)
        if (groups.isEmpty()) return null

        val merged = ConcurrentHashMap<String, IptvNowNext>()
        groups.entries.forEachIndexed { index, (creds, providerChannels) ->
            if (providerChannels.isEmpty()) return@forEachIndexed
            onProgress(
                IptvLoadProgress(
                    context.getString(R.string.iptv_progress_loading_xtream_guide, index + 1, groups.size),
                    90 + ((index * 6) / groups.size.coerceAtLeast(1))
                )
            )
            val parsed = fetchXtreamFullEpg(creds, providerChannels, onProgress)
            if (!parsed.isNullOrEmpty()) {
                merged.putAll(parsed)
            }
        }
        return merged.takeIf { hasAnyProgramData(it) }
    }

    private fun addChannelIdToLookup(
        target: MutableMap<String, MutableList<String>>,
        rawKey: String?,
        channelId: String
    ) {
        guideKeyCandidates(rawKey).forEach { key ->
            target.getOrPut(key) { mutableListOf() }.let { ids ->
                if (channelId !in ids) ids += channelId
            }
        }
    }

    private fun resolveChannelIdsFromLookup(
        lookup: Map<String, List<String>>,
        rawKey: String?
    ): List<String> {
        if (rawKey.isNullOrBlank()) return emptyList()
        val resolved = LinkedHashSet<String>()
        guideKeyCandidates(rawKey).forEach { key ->
            lookup[key]?.let { resolved.addAll(it) }
        }
        return resolved.toList()
    }

    /**
     * Fetches short EPG listings from an Xtream provider and converts them into now/next program snapshots per channel.
     *
     * @param creds Xtream credentials used to query the provider's short EPG endpoints.
     * @param channels The channels to resolve short EPG for; only channels with resolvable Xtream stream IDs are queried.
     * @param onProgress Callback invoked with load progress updates.
     * @return A map from IPTV channel ID to its derived IptvNowNext when listings were successfully retrieved and considered reliable, or `null` if no listings were available or the fetch was deemed unreliable (e.g., excessive errors).
     */
    private suspend fun fetchXtreamShortEpg(
        creds: XtreamCredentials,
        channels: List<IptvChannel>,
        onProgress: (IptvLoadProgress) -> Unit,
        listingLimit: Int = xtreamShortEpgLimit
    ): Map<String, IptvNowNext>? {
        if (channels.isEmpty()) return null

        // Build lookups: epgId -> channelIds, streamId -> channelIds
        val epgIdToChannelIds = mutableMapOf<String, MutableList<String>>()
        val streamIdToChannelIds = mutableMapOf<String, MutableList<String>>()
        for (ch in channels) {
            ch.epgId?.let { eid ->
                addChannelIdToLookup(epgIdToChannelIds, eid, ch.id)
            }
            ch.tvgName?.let { tvg ->
                addChannelIdToLookup(epgIdToChannelIds, tvg, ch.id)
            }
            ch.variantKey?.let { key ->
                addChannelIdToLookup(epgIdToChannelIds, key, ch.id)
            }
            resolveXtreamStreamId(ch)?.let { sid ->
                streamIdToChannelIds.getOrPut(sid.toString()) { mutableListOf() }.add(ch.id)
            }
        }

        onProgress(IptvLoadProgress("Loading EPG (fast Xtream API)...", 90))

        // Prioritize: favorite channels first, then favorite groups, then rest.
        // Deduplicate stream IDs so we don't fetch the same channel twice.
        val xtreamChannels = channels.filter { resolveXtreamStreamId(it) != null }
        // We're already inside a suspend fun — runBlocking here was pinning
        // the calling coroutine's dispatcher thread while it waited on the
        // DataStore flow. Direct suspension lets the scheduler pick up other
        // work during the (typically ~10 ms) DataStore read.
        val favoriteChannelIds = runCatching { observeFavoriteChannels().first() }
            .getOrDefault(emptyList()).toSet()
        val favoriteGroupNames = runCatching { observeFavoriteGroups().first() }
            .getOrDefault(emptyList()).toSet()

        val favChannels = xtreamChannels.filter { it.id in favoriteChannelIds }
        val favGroupChannels = xtreamChannels.filter { it.id !in favoriteChannelIds && it.group in favoriteGroupNames }
        val alreadyPrioritized = (favChannels.map { it.id } + favGroupChannels.map { it.id }).toSet()
        val rest = xtreamChannels.filter { it.id !in alreadyPrioritized }
        val prioritized = favChannels + favGroupChannels + rest

        // Fetch up to 25000 channels — well beyond what the provider tends
        // to serve per playlist, so effectively "all available". Combined
        // with the widened concurrency in fetchXtreamEpgListingsAsync this
        // completes within the 60s budget for most providers.
        val toFetch = prioritized
        val includeStreamsWithoutGuideKey = toFetch.size <= xtreamShortEpgBatchSize
        System.err.println(
            "[EPG] Xtream short EPG: preparing stream sweep for ${toFetch.size} channels " +
                "includeNoGuide=$includeStreamsWithoutGuideKey"
        )
        val representatives = representativeXtreamEpgStreamIds(
            channels = toFetch,
            includeStreamsWithoutGuideKey = includeStreamsWithoutGuideKey
        )
        val streamIds = representatives.streamIds
        System.err.println(
            "[EPG] Xtream short EPG: fetching ${streamIds.size} streams " +
                "for ${toFetch.size}/${xtreamChannels.size} channels " +
                "skippedNoGuide=${representatives.skippedWithoutGuideKey}"
        )
        if (toFetch.isEmpty()) return null

        var errors = 0
        var fetched = 0
        val total = streamIds.size.coerceAtLeast(1)

        val allListings = fetchXtreamEpgListingsAsync(
            creds = creds,
            streamIds = streamIds,
            timeoutMillis = xtreamShortEpgTimeout(streamIds.size),
            listingLimit = listingLimit
        ) { _, hadError ->
            fetched++
            if (hadError) errors++
            if (fetched % 50 == 0) {
                val pct = (90 + ((fetched.toLong() * 8L) / total.toLong())).toInt().coerceIn(90, 98)
                onProgress(IptvLoadProgress("Loading EPG... $fetched/$total streams", pct))
            }
        }
        System.err.println("[EPG] Xtream short EPG done: ${allListings.size} listings, $fetched fetched, $errors errors")

        if (errors > fetched / 2 && fetched > 20) {
            return null
        }
        if (allListings.isEmpty()) return null

        onProgress(IptvLoadProgress("Parsing EPG data (${allListings.size} listings)...", 98))
        return buildNowNextFromXtreamListings(
            creds = creds,
            listings = allListings,
            epgIdToChannelIds = epgIdToChannelIds,
            streamIdToChannelIds = streamIdToChannelIds,
            channelsById = channels.associateBy { it.id }
        )
    }

    private suspend fun fetchXtreamFullEpg(
        creds: XtreamCredentials,
        channels: List<IptvChannel>,
        onProgress: (IptvLoadProgress) -> Unit
    ): Map<String, IptvNowNext>? {
        if (channels.isEmpty()) return null

        val epgIdToChannelIds = mutableMapOf<String, MutableList<String>>()
        val streamIdToChannelIds = mutableMapOf<String, MutableList<String>>()
        for (ch in channels) {
            ch.epgId?.let { eid ->
                addChannelIdToLookup(epgIdToChannelIds, eid, ch.id)
            }
            ch.tvgName?.let { tvg ->
                addChannelIdToLookup(epgIdToChannelIds, tvg, ch.id)
            }
            ch.variantKey?.let { key ->
                addChannelIdToLookup(epgIdToChannelIds, key, ch.id)
            }
            resolveXtreamStreamId(ch)?.let { sid ->
                streamIdToChannelIds.getOrPut(sid.toString()) { mutableListOf() }.add(ch.id)
            }
        }

        val xtreamChannels = channels.filter { resolveXtreamStreamId(it) != null }
        val representatives = representativeXtreamEpgStreamIds(
            channels = xtreamChannels,
            includeStreamsWithoutGuideKey = false
        )
        val streamIds = representatives.streamIds
        if (streamIds.isEmpty()) return null

        onProgress(IptvLoadProgress(context.getString(R.string.iptv_progress_loading_xtream_epg), 90))
        System.err.println(
            "[EPG] Xtream full EPG: fetching ${streamIds.size} representative streams " +
                "for ${xtreamChannels.size}/${channels.size} channels skippedNoGuide=${representatives.skippedWithoutGuideKey}"
        )

        var errors = 0
        var fetched = 0
        val total = streamIds.size.coerceAtLeast(1)
        val channelsById = channels.associateBy { it.id }
        val indexKey = currentEpgIndexKey

        // Process the stream set in batches: fetch a batch's listings, build its
        // now/next slice, PERSIST it to the SQLite EPG index, then drop it so the
        // next batch can be GC'd. Accumulating all 10k+ streams' listings at once
        // exhausted the 384MB heap and OOM-crashed the app. Memory now stays bounded
        // to one batch regardless of playlist size, and the guide still reaches full
        // coverage (the index, which the grid reads from, accumulates every batch).
        val batchSize = 600
        var totalIndexed = 0
        var firstBatch: Map<String, IptvNowNext> = emptyMap()
        for (batchStreamIds in streamIds.chunked(batchSize)) {
            val batchListings = fetchXtreamFullEpgListingsAsync(
                creds = creds,
                streamIds = batchStreamIds,
                timeoutMillis = xtreamFullEpgSweepTimeout(batchStreamIds.size),
                parallelism = xtreamFullEpgSweepConcurrency(batchStreamIds.size)
            ) { _, hadError ->
                fetched++
                if (hadError) errors++
                if (fetched % 50 == 0) {
                    val pct = (90 + ((fetched.toLong() * 8L) / total.toLong())).toInt().coerceIn(90, 98)
                    onProgress(IptvLoadProgress(context.getString(R.string.iptv_progress_loading_full_epg_streams, fetched, total), pct))
                }
            }
            if (batchListings.isEmpty()) continue
            val batchNowNext = buildNowNextFromXtreamListings(
                creds = creds,
                listings = batchListings,
                epgIdToChannelIds = epgIdToChannelIds,
                streamIdToChannelIds = streamIdToChannelIds,
                channelsById = channelsById,
                forceCatchupHistory = true
            )
            if (batchNowNext.isNotEmpty()) {
                if (indexKey.isNotBlank()) {
                    runCatching { epgIndex.replaceChannels(indexKey, batchNowNext, System.currentTimeMillis()) }
                }
                totalIndexed += batchNowNext.size
                if (firstBatch.isEmpty()) firstBatch = batchNowNext
            }
            // batchListings + batchNowNext now eligible for GC before the next batch.
        }
        System.err.println("[EPG] Xtream full EPG done (batched): $fetched fetched, $errors errors, indexed=$totalIndexed channels")

        if (totalIndexed == 0) return null
        // Return only the first batch for the in-memory cache: the full guide already
        // lives in the SQLite index (which the UI queries per visible window), so we
        // never hand the whole 10k-channel map back to the caller (that re-OOM'd).
        return firstBatch
    }

    private fun representativeXtreamEpgStreamIds(
        channels: List<IptvChannel>,
        includeStreamsWithoutGuideKey: Boolean
    ): XtreamEpgRepresentativeStreams {
        val withGuideKey = LinkedHashSet<Int>()
        val withoutGuideKey = LinkedHashSet<Int>()
        var skippedWithoutGuideKey = 0

        channels.forEach { channel ->
            val streamId = resolveXtreamStreamId(channel) ?: return@forEach
            val guideKey = representativeGuideKey(channel)
            if (guideKey.isNullOrBlank()) {
                if (includeStreamsWithoutGuideKey) {
                    withoutGuideKey += streamId
                } else {
                    skippedWithoutGuideKey++
                }
            } else {
                withGuideKey += streamId
            }
        }

        val streamIds = buildList {
            addAll(withGuideKey)
            addAll(withoutGuideKey)
        }.distinct()
        return XtreamEpgRepresentativeStreams(streamIds, skippedWithoutGuideKey)
    }

    private data class XtreamEpgRepresentativeStreams(
        val streamIds: List<Int>,
        val skippedWithoutGuideKey: Int
    )

    private fun representativeGuideKey(channel: IptvChannel): String? {
        return sequenceOf(channel.epgId, channel.tvgName)
            .filterNotNull()
            .map { normalizeLooseKey(it) }
            .firstOrNull { it.isNotBlank() }
    }



    /**
     * Fetches short EPG listings for the given Xtream stream IDs in parallel.
     *
 * Requests the Xtream `get_short_epg` endpoint for each stream ID (first with a `limit=24`,
     * then a fallback without `limit` if the first response is empty). Records one sample log
     * for the first non-empty response observed and invokes `onStreamProcessed` for each stream
     * to report whether that stream encountered an error.
     *
     * @param creds Xtream credentials and base URL used to construct API requests.
     * @param streamIds The list of Xtream stream IDs to query.
     * @param onStreamProcessed Callback invoked once per stream ID with `(streamId, hadError)`,
     *   where `hadError` is `true` if the request sequence for that stream failed.
     * @return A flattened list of all `XtreamEpgListing` objects returned by the provider
     *   (empty if no listings were retrieved).
     */
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    private suspend fun fetchXtreamEpgListingsAsync(
        creds: XtreamCredentials,
        streamIds: List<Int>,
        timeoutMillis: Long = 180_000L,
        listingLimit: Int = xtreamShortEpgLimit,
        allowUnboundedFallback: Boolean = true,
        onStreamProcessed: (Int, Boolean) -> Unit = { _, _ -> }
    ): List<XtreamEpgListing> {
        // Concurrency bumped 20 → 32 so a 25k-channel sweep finishes inside
        // the enlarged 180s budget. Providers typically tolerate this; any
        // over-limit request simply fails and the fallback per-channel call
        // handles it silently.
        val distinctStreamIds = streamIds.distinct()
        if (distinctStreamIds.isEmpty()) return emptyList()
        val gate = Semaphore(xtreamShortEpgConcurrency)
        val listingsResult = ConcurrentLinkedQueue<XtreamEpgListing>()
        val simpleFallbacks = AtomicInteger(0)
        val completed = withTimeoutOrNull(timeoutMillis) {
            withContext(Dispatchers.IO.limitedParallelism(xtreamShortEpgConcurrency)) {
                val sampleLogged = AtomicBoolean(false)
                distinctStreamIds.chunked(xtreamShortEpgBatchSize).forEach { batch ->
                    batch.map { sid ->
                        async {
                            gate.withPermit {
                                var hadError = false
                                val url = "${creds.baseUrl}/player_api.php?username=${creds.username}" +
                                    "&password=${creds.password}&action=get_short_epg&stream_id=$sid&limit=$listingLimit"
                                var listings: List<XtreamEpgListing>? = null
                                try {
                                    var resp: XtreamEpgResponse? = requestJson(
                                        url,
                                        XtreamEpgResponse::class.java,
                                        client = xtreamGuideHttpClient
                                    )
                                    listings = resp?.epgListings
                                    if (listings.isNullOrEmpty() && allowUnboundedFallback) {
                                        val fallbackUrl = "${creds.baseUrl}/player_api.php?username=${creds.username}" +
                                            "&password=${creds.password}&action=get_short_epg&stream_id=$sid"
                                        resp = requestJson(
                                            fallbackUrl,
                                            XtreamEpgResponse::class.java,
                                            client = xtreamGuideHttpClient
                                        )
                                        listings = resp?.epgListings
                                    }
                                    if (listings.isNullOrEmpty() && allowUnboundedFallback) {
                                        val simpleUrl = "${creds.baseUrl}/player_api.php?username=${creds.username}" +
                                            "&password=${creds.password}&action=get_simple_data_table&stream_id=$sid"
                                        val simpleResp: JsonObject? = requestJson(
                                            url = simpleUrl,
                                            type = JsonObject::class.java,
                                            client = xtreamCatchupGuideHttpClient
                                        )
                                        if (simpleResp == null) {
                                            hadError = true
                                        }
                                        listings = trimXtreamListingsToGuideWindow(parseXtreamListingsFromJson(simpleResp))
                                        if (!listings.isNullOrEmpty()) {
                                            simpleFallbacks.incrementAndGet()
                                        }
                                    }
                                    if (!listings.isNullOrEmpty()) {
                                        val taggedListings = listings.withRequestedStreamId(sid)
                                        listingsResult.addAll(taggedListings)
                                        if (sampleLogged.compareAndSet(false, true)) {
                                            val sample = taggedListings.first()
                                            System.err.println("[EPG] Sample response for stream_id=$sid: channelId=${sample.channelId} epgId=${sample.epgId} streamId=${sample.streamId} start=${sample.start} startTs=${sample.startTimestamp} title=${sample.title?.take(40)}")
                                        }
                                    }
                                } catch (_: Exception) { hadError = true }
                                onStreamProcessed(sid, hadError)
                            }
                        }
                    }.awaitAll()
                }
            }
        }
        if (completed == null) {
            System.err.println(
                "[EPG] Xtream short EPG timed out after ${timeoutMillis}ms; " +
                    "keeping ${listingsResult.size} fetched listings"
            )
        }
        val fallbackCount = simpleFallbacks.get()
        if (fallbackCount > 0) {
            System.err.println("[EPG] Xtream simple-data fallback filled $fallbackCount short-empty streams")
        }
        return listingsResult.toList()
    }

    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    private suspend fun fetchXtreamFullEpgListingsAsync(
        creds: XtreamCredentials,
        streamIds: List<Int>,
        timeoutMillis: Long = 16_000L,
        parallelism: Int = 4,
        onStreamProcessed: (Int, Boolean) -> Unit = { _, _ -> }
    ): List<XtreamEpgListing> {
        val distinctStreamIds = streamIds.distinct()
        if (distinctStreamIds.isEmpty()) return emptyList()
        val safeParallelism = parallelism.coerceIn(1, 32)
        val gate = Semaphore(safeParallelism)
        val listingsResult = ConcurrentLinkedQueue<XtreamEpgListing>()
        val completed = withTimeoutOrNull(timeoutMillis) {
            withContext(Dispatchers.IO.limitedParallelism(safeParallelism)) {
                distinctStreamIds.map { sid ->
                    async {
                        gate.withPermit {
                            var hadError = false
                            val url = "${creds.baseUrl}/player_api.php?username=${creds.username}" +
                                "&password=${creds.password}&action=get_simple_data_table&stream_id=$sid"
                            try {
                                val resp: JsonObject? = requestJson(
                                    url = url,
                                    type = JsonObject::class.java,
                                    client = xtreamCatchupGuideHttpClient
                                )
                                if (resp == null) {
                                    hadError = true
                                }
                                val listings = trimXtreamListingsToGuideWindow(parseXtreamListingsFromJson(resp))
                                    .withRequestedStreamId(sid)
                                if (listings.isNotEmpty()) {
                                    listingsResult.addAll(listings)
                                }
                            } catch (_: Exception) {
                                hadError = true
                            }
                            onStreamProcessed(sid, hadError)
                        }
                    }
                }.awaitAll()
            }
        }
        if (completed == null) {
            System.err.println(
                "[EPG] Xtream full catchup EPG timed out after ${timeoutMillis}ms; " +
                    "keeping ${listingsResult.size} fetched listings"
            )
        }
        return listingsResult.toList()
    }

    private fun xtreamShortEpgTimeout(streamCount: Int): Long =
        when {
            streamCount > 4_000 -> 90_000L
            streamCount > 1_200 -> 45_000L
            streamCount > 256 -> 18_000L
            streamCount > 64 -> 8_000L
            streamCount > 16 -> 5_000L
            else -> 2_500L
        }

    private fun xtreamFullCatchupEpgTimeout(streamCount: Int): Long =
        when {
            streamCount > 2 -> 30_000L
            streamCount > 1 -> 24_000L
            else -> 18_000L
        }

    private fun xtreamFullEpgSweepTimeout(streamCount: Int): Long =
        when {
            streamCount > 8_000 -> 420_000L
            streamCount > 4_000 -> 300_000L
            streamCount > 1_200 -> 180_000L
            streamCount > 256 -> 90_000L
            else -> 45_000L
        }

    private fun xtreamFullEpgSweepConcurrency(streamCount: Int): Int =
        when {
            streamCount > 4_000 -> 24
            streamCount > 1_200 -> 18
            streamCount > 256 -> 12
            else -> 6
        }

    /**
     * Constructs a mapping of IPTV channel IDs to their current and upcoming program windows from a list of Xtream short EPG listings.
     *
     * The function groups listings by resolved channel (using `epgIdToChannelIds` and `streamIdToChannelIds`), orders programs by start time, and populates `now`, `next`, `later`, `upcoming`, and `recent` slots for each channel.
     *
     * @param listings Xtream short EPG listings to convert into program windows.
     * @param epgIdToChannelIds Map from EPG identifier to the list of IPTV channel IDs that share that EPG id.
     * @param streamIdToChannelIds Map from Xtream stream identifier to the list of IPTV channel IDs that correspond to that stream.
     * @return A map keyed by IPTV channel ID with values of `IptvNowNext`. Each `IptvNowNext` may contain `now`, `next`, `later`, a truncated `upcoming` list (at most 12 items), and a `recent` list of programs that ended within the recent cutoff window.
     */
    private fun buildNowNextFromXtreamListings(
        creds: XtreamCredentials,
        listings: List<XtreamEpgListing>,
        epgIdToChannelIds: Map<String, List<String>>,
        streamIdToChannelIds: Map<String, List<String>>,
        channelsById: Map<String, IptvChannel> = emptyMap(),
        forceCatchupHistory: Boolean = false
    ): Map<String, IptvNowNext> {
        // Detect and save server timezone offset
        val sampleListing = listings.firstOrNull { it.startTimestamp != null && !it.start.isNullOrBlank() }
        if (sampleListing != null) {
            val startMs = sampleListing.startTimestamp?.toLongOrNull()?.let { it * 1000L }
            val parsedMs = parseXtreamDateTime(sampleListing.start)
            if (startMs != null && parsedMs != null) {
                val offset = parsedMs - startMs
                if (Math.abs(offset) <= 18 * 60 * 60 * 1000L) {
                    saveServerOffset(creds, offset)
                    System.err.println("[EPG] Detected Xtream Server timezone offset: ${offset / 3600000.0} hours")
                }
            }
        }

        val nowMs = System.currentTimeMillis()
        val oldestRecentCutoff = oldestRecentCutoff(channelsById.values, nowMs, forceCatchupHistory)

        // Group listings by channel.
        // Try matching by: epg_id (channelId field), then stream_id.
        data class ChannelPrograms(val programs: MutableList<IptvProgram> = mutableListOf())
        val channelProgramsMap = mutableMapOf<String, ChannelPrograms>()

        for (listing in listings) {
            val startMs = listing.startTimestamp?.toLongOrNull()?.let { it * 1000L }
                ?: parseXtreamDateTime(listing.start)
                ?: continue
            val stopMs = listing.stopTimestamp?.toLongOrNull()?.let { it * 1000L }
                ?: parseXtreamDateTime(listing.end)
                ?: continue

            // Skip programs that ended before the oldest possible catchup window.
            if (stopMs < oldestRecentCutoff) continue

            val title = decodeBase64Field(listing.title).ifBlank { context.getString(R.string.program_no_title) }
            val description = decodeBase64Field(listing.description).takeIf { it.isNotBlank() }

            val program = IptvProgram(
                title = title,
                description = description,
                startUtcMillis = startMs,
                endUtcMillis = stopMs,
                catchupAvailable = listing.hasArchive?.let { it > 0 }
            )

            // Resolve which IptvChannel IDs this listing maps to
            val resolvedChannelIds = mutableSetOf<String>()

            val exactStreamIds = listing.streamId
                ?.let { sid -> streamIdToChannelIds[sid] }
                .orEmpty()
            if (forceCatchupHistory && exactStreamIds.isNotEmpty()) {
                resolvedChannelIds.addAll(exactStreamIds)
                // Some providers only expose catch-up on one quality variant
                // while all variants share the same EPG identity. Keep rows
                // separate, but fan the guide history out inside the same
                // provider/EPG family so 4K/FHD/HD/SD rows can all show the
                // same aired programme list.
                exactStreamIds.forEach { exactChannelId ->
                    channelsById[exactChannelId]?.let { exactChannel ->
                        resolvedChannelIds.addAll(resolveChannelIdsFromLookup(epgIdToChannelIds, exactChannel.epgId))
                        resolvedChannelIds.addAll(resolveChannelIdsFromLookup(epgIdToChannelIds, exactChannel.tvgName))
                        exactChannel.variantKey?.let { variantKey ->
                            resolvedChannelIds.addAll(resolveChannelIdsFromLookup(epgIdToChannelIds, variantKey))
                        }
                    }
                }
            } else {
                // Match by epg_id / channel_id field
                listing.channelId?.let { cid ->
                    resolvedChannelIds.addAll(resolveChannelIdsFromLookup(epgIdToChannelIds, cid))
                }
                listing.lang?.let { lang ->
                    resolvedChannelIds.addAll(resolveChannelIdsFromLookup(epgIdToChannelIds, lang))
                }
                listing.epgId?.let { eid ->
                    // epg_id can be the stream_id in some providers
                    streamIdToChannelIds[eid]?.let { resolvedChannelIds.addAll(it) }
                    resolvedChannelIds.addAll(resolveChannelIdsFromLookup(epgIdToChannelIds, eid))
                }
                resolvedChannelIds.addAll(exactStreamIds)
            }

            for (channelId in resolvedChannelIds) {
                channelProgramsMap.getOrPut(channelId) { ChannelPrograms() }.programs.add(program)
            }
        }

        System.err.println("[EPG] buildNowNext: ${listings.size} listings -> ${channelProgramsMap.size} channels mapped (epgIdMap=${epgIdToChannelIds.size}, streamIdMap=${streamIdToChannelIds.size})")
        if (channelProgramsMap.isEmpty() && listings.isNotEmpty()) {
            // Log first few listings to debug mapping issues
            listings.take(3).forEach { l ->
                System.err.println("[EPG]   sample listing: channelId=${l.channelId} epgId=${l.epgId} streamId=${l.streamId} start=${l.start} startTs=${l.startTimestamp} title=${l.title?.take(30)}")
            }
        }

        // Build NowNext from sorted programs
        val result = mutableMapOf<String, IptvNowNext>()
        for ((channelId, cp) in channelProgramsMap) {
            val sorted = cp.programs.sortedBy { it.startUtcMillis }
            var now: IptvProgram? = null
            var next: IptvProgram? = null
            var later: IptvProgram? = null
            val upcoming = mutableListOf<IptvProgram>()
            val recent = mutableListOf<IptvProgram>()

            if (sorted.isNotEmpty()) {
                val recentCutoff = recentCutoffForChannel(channelsById[channelId], nowMs, forceCatchupHistory)
                var startIndex = sorted.binarySearch { it.startUtcMillis.compareTo(recentCutoff) }
                if (startIndex < 0) {
                    startIndex = -(startIndex + 1)
                }

                // Walk backward to include programs starting before recentCutoff but ending after
                while (startIndex > 0 && sorted[startIndex - 1].endUtcMillis > recentCutoff) {
                    startIndex--
                }

                for (i in startIndex until sorted.size) {
                    val p = sorted[i]
                    when {
                        p.endUtcMillis <= nowMs && p.endUtcMillis > recentCutoff -> {
                            addRecentCandidate(
                                recent = recent,
                                candidate = p,
                                limit = recentProgramLimitForChannel(channelsById[channelId], forceCatchupHistory)
                            )
                        }
                        p.isLive(nowMs) -> now = p
                        p.startUtcMillis > nowMs && next == null -> next = p
                        p.startUtcMillis > nowMs && later == null -> later = p
                        p.startUtcMillis > nowMs -> {
                            upcoming.add(p)
                            if (upcoming.size >= epgUpcomingProgramLimit) {
                                break // We have enough upcoming programs
                            }
                        }
                    }
                }
            }

            result[channelId] = IptvNowNext(
                now = now,
                next = next,
                later = later,
                upcoming = upcoming,
                recent = recent
            )
        }

        val withNow = result.values.count { it.now != null }
        val withNext = result.values.count { it.next != null }
        val withRecent = result.values.count { it.recent.isNotEmpty() }
        System.err.println("[EPG] buildNowNext result: ${result.size} channels, $withNow with NOW, $withNext with NEXT, $withRecent with RECENT")
        return result
    }

    /**
     * Parse Xtream datetime strings like "2024-01-01 12:00:00" to epoch millis.
     */
    private fun parseXtreamDateTime(dateStr: String?): Long? {
        if (dateStr.isNullOrBlank()) return null
        return try {
            val formatter = IptvRepoDateRegexes.SPACE_SECOND_PATTERN
            val local = java.time.LocalDateTime.parse(dateStr, formatter)
            // Xtream times are typically UTC
            local.atZone(java.time.ZoneOffset.UTC).toInstant().toEpochMilli()
        } catch (_: Exception) {
            null
        }
    }

    private fun saveServerOffset(creds: XtreamCredentials, offset: Long) {
        runCatching {
            context.getSharedPreferences("arvio_iptv_prefs", android.content.Context.MODE_PRIVATE)
                .edit()
                .putLong(xtreamServerOffsetKey(creds), offset)
                .apply()
        }
    }

    private fun getServerOffset(creds: XtreamCredentials): Long {
        return runCatching {
            context.getSharedPreferences("arvio_iptv_prefs", android.content.Context.MODE_PRIVATE)
                .getLong(xtreamServerOffsetKey(creds), 0L)
        }.getOrDefault(0L)
    }

    private fun xtreamServerOffsetKey(creds: XtreamCredentials): String =
        "xtream_server_offset_${xtreamDiskCacheHash(creds)}"

    /**
     * Some providers return malformed XML text that includes JSON-style backslash escapes
     * (for example: \" or \n) inside element values. KXmlParser can fail hard on this.
     * This filter normalizes the most common escapes into plain text so XML parsing can continue.
     */
    private class BackslashEscapeSanitizingInputStream(
        input: InputStream
    ) : FilterInputStream(input) {
        override fun read(): Int {
            val buf = ByteArray(1)
            val read = read(buf, 0, 1)
            return if (read <= 0) -1 else buf[0].toInt() and 0xFF
        }

        override fun read(b: ByteArray, off: Int, len: Int): Int {
            if (len == 0) return 0

            // Read from the underlying stream
            val rawRead = super.read(b, off, len)
            if (rawRead == -1) return -1

            var writeIdx = off
            var readIdx = off
            val endIdx = off + rawRead

            while (readIdx < endIdx) {
                val current = b[readIdx++].toInt() and 0xFF
                if (current == '\\'.code) {
                    val next = if (readIdx < endIdx) {
                        b[readIdx++].toInt() and 0xFF
                    } else {
                        // The backslash is at the very end of the read chunk.
                        // Fetch the next byte from the underlying stream.
                        val n = super.read()
                        if (n == -1) -1 else n
                    }

                    if (next == -1) {
                        b[writeIdx++] = '\\'.toByte()
                    } else {
                        val mapped = when (next.toChar()) {
                            '\\' -> '\\'.code
                            '"' -> '"'.code
                            '\'' -> '\''.code
                            '/' -> '/'.code
                            'n' -> '\n'.code
                            'r' -> '\r'.code
                            't' -> '\t'.code
                            'b' -> '\b'.code
                            'f' -> 0x0C
                            else -> next
                        }

                        val finalChar = if (mapped in 0x00..0x1F && mapped != '\n'.code && mapped != '\r'.code && mapped != '\t'.code) {
                            ' '.code
                        } else {
                            mapped
                        }
                        b[writeIdx++] = finalChar.toByte()
                    }
                } else {
                    val finalChar = if (current in 0x00..0x1F && current != '\n'.code && current != '\r'.code && current != '\t'.code) {
                        ' '.code
                    } else {
                        current
                    }
                    b[writeIdx++] = finalChar.toByte()
                }
            }

            return writeIdx - off
        }
    }

    private fun parseM3u(
        input: InputStream,
        onProgress: (IptvLoadProgress) -> Unit
    ): List<IptvChannel> {
        val channels = mutableListOf<IptvChannel>()
        val seenChannelIds = HashSet<String>()
        var pendingMetadata: String? = null
        val pendingHeaders = linkedMapOf<String, String>()
        val pendingDrmProps = linkedMapOf<String, String>()
        var parsedCount = 0

        BufferedReader(InputStreamReader(input, StandardCharsets.UTF_8), 256 * 1024).use { reader ->
            while (true) {
                val rawLine = reader.readLine() ?: break
                val line = rawLine.trim()
                if (line.isEmpty()) continue

                if (line.startsWith("#EXTM3U", ignoreCase = true)) {
                    extractM3uDeclaredEpgUrls(line)
                        .forEach { discoveredM3uEpgUrls.add(it) }
                    continue
                }

                if (line.startsWith("#EXTINF", ignoreCase = true)) {
                    pendingMetadata = line
                    pendingHeaders.clear()
                    pendingDrmProps.clear()
                    mergeM3uHeaderOptions(pendingHeaders, pendingDrmProps, line)
                    continue
                }

                if (line.startsWith("#EXTVLCOPT", ignoreCase = true) || line.startsWith("#KODIPROP", ignoreCase = true)) {
                    mergeM3uHeaderOptions(pendingHeaders, pendingDrmProps, line)
                    continue
                }

                if (line.startsWith("#")) continue

                val metadata = pendingMetadata
                pendingMetadata = null

                val streamUrl = normalizeIptvStreamUrl(line)
                val epgId = extractAttr(metadata, "tvg-id")
                val id = buildChannelId(streamUrl, epgId)
                if (!seenChannelIds.add(id)) {
                    pendingHeaders.clear()
                    continue
                }

                val tvgName = extractAttr(metadata, "tvg-name")
                val channelName = tvgName?.takeIf { it.isNotBlank() } ?: extractChannelName(metadata)
                val groupTitle = extractAttr(metadata, "group-title")?.takeIf { it.isNotBlank() } ?: "Uncategorized"
                val logo = normalizeIptvLogoUrlOrNull(extractAttr(metadata, "tvg-logo"))
                val catchupType = extractAttr(metadata, "catchup")
                val catchupDays = extractFirstAttr(metadata, "catchup-days", "timeshift")?.toIntOrNull() ?: 0
                val catchupSource = extractAttr(metadata, "catchup-source")
                val providerChannelNumber = extractFirstAttr(
                    metadata,
                    "tvg-chno",
                    "tvg-ch-number",
                    "channel-number",
                    "ch-number",
                    "number"
                )
                val language = extractFirstAttr(metadata, "tvg-language", "tvg-lang", "language", "lang")
                val country = extractFirstAttr(metadata, "tvg-country", "country")
                val qualityLabel = extractFirstAttr(metadata, "quality", "tvg-quality", "resolution")
                    ?: inferQualityLabel(channelName, groupTitle)
                val inlineHeaders = extractInlineRequestHeaders(metadata)
                val requestHeaders = if (pendingHeaders.isEmpty()) {
                    if (inlineHeaders.isEmpty()) {
                        emptyMap()
                    } else {
                        inlineHeaders.filterValues { it.isNotBlank() }
                    }
                } else {
                    if (inlineHeaders.isEmpty()) {
                        pendingHeaders.filterValues { it.isNotBlank() }
                    } else {
                        (pendingHeaders + inlineHeaders).filterValues { it.isNotBlank() }
                    }
                }

                channels += IptvChannel(
                    id = id,
                    name = channelName,
                    streamUrl = streamUrl,
                    group = groupTitle,
                    logo = logo,
                    epgId = epgId,
                    rawTitle = metadata ?: channelName,
                    catchupDays = catchupDays,
                    catchupType = catchupType,
                    catchupSource = catchupSource,
                    tvgName = tvgName,
                    providerChannelNumber = providerChannelNumber,
                    requestHeaders = requestHeaders,
                    language = language,
                    country = country,
                    qualityLabel = qualityLabel,
                    variantKey = buildChannelVariantKey(tvgName ?: channelName, groupTitle, epgId),
                    drmInfo = buildDrmInfo(pendingDrmProps)
                )
                pendingHeaders.clear()
                pendingDrmProps.clear()
                parsedCount++
                if (parsedCount % 5000 == 0) {
                    onProgress(IptvLoadProgress("Parsing channels... $parsedCount found", 85))
                }
            }
        }

        onProgress(IptvLoadProgress("Finalizing ${channels.size} channels...", 95))
        return channels
    }

    private fun extractM3uDeclaredEpgUrls(line: String): List<String> {
        return listOf("url-tvg", "x-tvg-url", "tvg-url")
            .mapNotNull { attr -> extractAttr(line, attr) }
            .flatMap { normalizeEpgInputs(it) }
            .map { it.trim() }
            .filter { it.startsWith("http", ignoreCase = true) }
            .distinct()
    }

    private fun parseXmlTvNowNext(
        input: InputStream,
        channels: List<IptvChannel>
    ): Map<String, IptvNowNext> {
        if (channels.isEmpty()) return emptyMap()

        val nowUtc = System.currentTimeMillis()
        val recentCutoff = xmlTvRecentCutoff(channels, nowUtc)
        val futureCutoff = nowUtc + xmlTvFutureWindowMs
        val recentCutoffByChannelId = buildRecentCutoffByChannelId(channels, nowUtc)
        val recentLimitByChannelId = buildRecentLimitByChannelId(channels)

        val keyLookup = buildChannelKeyLookup(channels)
        val xmlChannelNameMap = mutableMapOf<String, MutableSet<String>>()
        val xmlChannelResolveCache = mutableMapOf<String, List<IptvChannel>>()
        val nowCandidates = mutableMapOf<String, IptvProgram?>()
        val upcomingCandidates = mutableMapOf<String, MutableList<IptvProgram>>()
        val recentCandidates = mutableMapOf<String, MutableList<IptvProgram>>()

        var currentXmlChannelId: String? = null
        var currentChannelKey: String? = null
        var currentStart = 0L
        var currentStop = 0L
        var currentTitle: String? = null
        var currentDesc: String? = null

        val parser = android.util.Xml.newPullParser()
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
        parser.setInput(input, null)
        var eventType = parser.eventType

        while (eventType != XmlPullParser.END_DOCUMENT) {
            when (eventType) {
                XmlPullParser.START_TAG -> {
                    when (parser.name.lowercase(Locale.US)) {
                        "channel" -> {
                            currentXmlChannelId = normalizeChannelKey(parser.getAttributeValue(null, "id") ?: "")
                        }
                        "display-name" -> {
                            val xmlId = currentXmlChannelId
                            if (!xmlId.isNullOrBlank()) {
                                val display = normalizeChannelKey(parser.nextText().orEmpty())
                                if (display.isNotBlank()) {
                                    val isUseful = guideKeyCandidates(display).any { it in keyLookup }
                                    if (isUseful) {
                                        xmlChannelNameMap.getOrPut(xmlId) { mutableSetOf() }.add(display)
                                    }
                                }
                            }
                        }
                        "programme" -> {
                            val rawKey = normalizeChannelKey(parser.getAttributeValue(null, "channel") ?: "")
                            val start = parseXmlTvDate(parser.getAttributeValue(null, "start"))
                            val stop = parseXmlTvDate(parser.getAttributeValue(null, "stop"))
                            // Skip programmes that ended before the recent cutoff
                            if ((stop > 0L && stop <= recentCutoff) || (start > 0L && start >= futureCutoff)) {
                                currentChannelKey = null
                            } else {
                                currentChannelKey = rawKey
                                currentStart = start
                                currentStop = stop
                                currentTitle = null
                                currentDesc = null
                            }
                        }
                        "title" -> {
                            if (currentChannelKey != null) {
                                currentTitle = parser.nextText().trim().ifBlank { null }
                            }
                        }
                        "desc" -> {
                            if (currentChannelKey != null) {
                                currentDesc = parser.nextText().trim().ifBlank { null }
                            }
                        }
                    }
                }
                XmlPullParser.END_TAG -> {
                    when {
                        parser.name.equals("channel", ignoreCase = true) -> {
                            currentXmlChannelId = null
                        }
                        parser.name.equals("programme", ignoreCase = true) -> {
                        val key = currentChannelKey
                        val resolvedChannels = key?.let {
                            xmlChannelResolveCache[it] ?: resolveXmlTvChannels(it, xmlChannelNameMap, keyLookup)
                                .also { resolved -> xmlChannelResolveCache[it] = resolved }
                        }.orEmpty()
                        if (resolvedChannels.isNotEmpty() && currentStop > currentStart) {
                            val program = IptvProgram(
                                title = currentTitle ?: context.getString(R.string.program_unknown),
                                description = currentDesc,
                                startUtcMillis = currentStart,
                                endUtcMillis = currentStop
                            )

                            resolvedChannels.forEach { channel ->
                                val nowProgram = pickNow(nowCandidates[channel.id], program, nowUtc)
                                nowCandidates[channel.id] = nowProgram
                                if (program.startUtcMillis > nowUtc) {
                                    val future = upcomingCandidates.getOrPut(channel.id) { mutableListOf() }
                                    addUpcomingCandidate(future, program, limit = epgUpcomingProgramLimit)
                                } else if (program.endUtcMillis <= nowUtc && program.endUtcMillis > recentCutoffByChannelId.getValue(channel.id)) {
                                    val recent = recentCandidates.getOrPut(channel.id) { mutableListOf() }
                                    val limit = recentLimitByChannelId.getValue(channel.id)
                                    addRecentCandidate(recent, program, limit)
                                }
                            }
                        }
                        currentChannelKey = null
                    }
                    }
                }
            }
            eventType = parser.next()
        }

        return buildParsedNowNextResult(channels, nowCandidates, upcomingCandidates, recentCandidates)
    }

    private fun parseXmlTvNowNextWithSax(
        input: InputStream,
        channels: List<IptvChannel>
    ): Map<String, IptvNowNext> {
        if (channels.isEmpty()) return emptyMap()

        val nowUtc = System.currentTimeMillis()
        val recentCutoff = xmlTvRecentCutoff(channels, nowUtc)
        val futureCutoff = nowUtc + xmlTvFutureWindowMs
        val recentCutoffByChannelId = buildRecentCutoffByChannelId(channels, nowUtc)
        val recentLimitByChannelId = buildRecentLimitByChannelId(channels)

        val keyLookup = buildChannelKeyLookup(channels)
        val xmlChannelNameMap = mutableMapOf<String, MutableSet<String>>()
        val xmlChannelResolveCache = mutableMapOf<String, List<IptvChannel>>()
        val nowCandidates = mutableMapOf<String, IptvProgram?>()
        val upcomingCandidates = mutableMapOf<String, MutableList<IptvProgram>>()
        val recentCandidates = mutableMapOf<String, MutableList<IptvProgram>>()

        val factory = SAXParserFactory.newInstance().apply {
            isNamespaceAware = false
            isValidating = false
            runCatching { setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true) }
            runCatching { setFeature("http://xml.org/sax/features/validation", false) }
            runCatching { setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false) }
        }
        val parser = factory.newSAXParser()

        var currentXmlChannelId: String? = null
        var currentChannelKey: String? = null
        var currentStart = 0L
        var currentStop = 0L
        var currentTitle: String? = null
        var currentDesc: String? = null
        var readingDisplayName = false
        var readingTitle = false
        var readingDesc = false
        val textBuffer = StringBuilder(128)

        val handler = object : DefaultHandler() {
            override fun startElement(uri: String?, localName: String?, qName: String?, attributes: Attributes?) {
                val name = (localName ?: qName ?: "").lowercase(Locale.US)
                when (name) {
                    "channel" -> {
                        currentXmlChannelId = normalizeChannelKey(attributes?.getValue("id").orEmpty())
                    }
                    "display-name" -> {
                        readingDisplayName = true
                        textBuffer.setLength(0)
                    }
                    "programme" -> {
                        val start = parseXmlTvDate(attributes?.getValue("start"))
                        val stop = parseXmlTvDate(attributes?.getValue("stop"))
                        if ((stop > 0L && stop <= recentCutoff) || (start > 0L && start >= futureCutoff)) {
                            currentChannelKey = null
                            currentStart = 0L
                            currentStop = 0L
                        } else {
                            currentChannelKey = normalizeChannelKey(attributes?.getValue("channel").orEmpty())
                            currentStart = start
                            currentStop = stop
                        }
                        currentTitle = null
                        currentDesc = null
                    }
                    "title" -> {
                        if (!currentChannelKey.isNullOrBlank()) {
                            readingTitle = true
                            textBuffer.setLength(0)
                        }
                    }
                    "desc" -> {
                        if (!currentChannelKey.isNullOrBlank()) {
                            readingDesc = true
                            textBuffer.setLength(0)
                        }
                    }
                }
            }

            override fun characters(ch: CharArray?, start: Int, length: Int) {
                if (ch == null || length <= 0) return
                if (readingDisplayName || readingTitle || readingDesc) {
                    textBuffer.append(ch, start, length)
                }
            }

            override fun endElement(uri: String?, localName: String?, qName: String?) {
                val name = (localName ?: qName ?: "").lowercase(Locale.US)
                when (name) {
                    "display-name" -> {
                        if (readingDisplayName) {
                            val xmlId = currentXmlChannelId
                            if (!xmlId.isNullOrBlank()) {
                                val display = normalizeChannelKey(textBuffer.toString())
                                if (display.isNotBlank()) {
                                    val isUseful = guideKeyCandidates(display).any { it in keyLookup }
                                    if (isUseful) {
                                        xmlChannelNameMap.getOrPut(xmlId) { mutableSetOf() }.add(display)
                                    }
                                }
                            }
                            readingDisplayName = false
                            textBuffer.setLength(0)
                        }
                    }
                    "channel" -> {
                        currentXmlChannelId = null
                    }
                    "title" -> {
                        if (readingTitle) {
                            currentTitle = textBuffer.toString().trim().ifBlank { null }
                            readingTitle = false
                            textBuffer.setLength(0)
                        }
                    }
                    "desc" -> {
                        if (readingDesc) {
                            currentDesc = textBuffer.toString().trim().ifBlank { null }
                            readingDesc = false
                            textBuffer.setLength(0)
                        }
                    }
                    "programme" -> {
                        val key = currentChannelKey
                        val resolvedChannels = key?.let {
                            xmlChannelResolveCache[it] ?: resolveXmlTvChannels(it, xmlChannelNameMap, keyLookup)
                                .also { resolved -> xmlChannelResolveCache[it] = resolved }
                        }.orEmpty()
                        if (resolvedChannels.isNotEmpty() && currentStop > currentStart) {
                            val program = IptvProgram(
                                title = currentTitle ?: context.getString(R.string.program_unknown),
                                description = currentDesc,
                                startUtcMillis = currentStart,
                                endUtcMillis = currentStop
                            )
                            resolvedChannels.forEach { channel ->
                                val nowProgram = pickNow(nowCandidates[channel.id], program, nowUtc)
                                nowCandidates[channel.id] = nowProgram
                            if (program.startUtcMillis > nowUtc) {
                                val future = upcomingCandidates.getOrPut(channel.id) { mutableListOf() }
                                addUpcomingCandidate(future, program, limit = epgUpcomingProgramLimit)
                            } else if (program.endUtcMillis <= nowUtc && program.endUtcMillis > recentCutoffByChannelId.getValue(channel.id)) {
                                // Recently ended program – keep for the past-window in the EPG guide
                                val recent = recentCandidates.getOrPut(channel.id) { mutableListOf() }
                                val limit = recentLimitByChannelId.getValue(channel.id)
                                addRecentCandidate(recent, program, limit)
                            }
                            }
                        }
                        currentChannelKey = null
                        currentStart = 0L
                        currentStop = 0L
                        currentTitle = null
                        currentDesc = null
                    }
                }
            }
        }

        parser.parse(input, handler)

        return buildParsedNowNextResult(channels, nowCandidates, upcomingCandidates, recentCandidates)
    }

    private fun buildParsedNowNextResult(
        channels: List<IptvChannel>,
        nowCandidates: Map<String, IptvProgram?>,
        upcomingCandidates: Map<String, List<IptvProgram>>,
        recentCandidates: Map<String, List<IptvProgram>>
    ): ConcurrentHashMap<String, IptvNowNext> {
        val result = ConcurrentHashMap<String, IptvNowNext>(channels.size)
        channels.forEach { channel ->
            val future = upcomingCandidates[channel.id].orEmpty()
            val recent = recentCandidates[channel.id].orEmpty().sortedBy { it.startUtcMillis }
            val nowNext = IptvNowNext(
                now = nowCandidates[channel.id],
                next = future.getOrNull(0),
                later = future.getOrNull(1),
                upcoming = future,
                recent = recent
            )
            if (hasProgramData(nowNext)) {
                result[channel.id] = nowNext
            }
        }
        return result
    }

    private fun pickNow(existing: IptvProgram?, candidate: IptvProgram, nowUtcMillis: Long): IptvProgram? {
        if (!candidate.isLive(nowUtcMillis)) return existing
        if (existing == null) return candidate
        return if (candidate.startUtcMillis >= existing.startUtcMillis) candidate else existing
    }

    private fun addUpcomingCandidate(
        upcoming: MutableList<IptvProgram>,
        candidate: IptvProgram,
        limit: Int
    ) {
        val duplicate = upcoming.any {
            it.startUtcMillis == candidate.startUtcMillis &&
                it.endUtcMillis == candidate.endUtcMillis &&
                it.title.equals(candidate.title, ignoreCase = true)
        }
        if (duplicate) return

        val insertIndex = upcoming.indexOfFirst {
            candidate.startUtcMillis < it.startUtcMillis ||
                (candidate.startUtcMillis == it.startUtcMillis && candidate.endUtcMillis > it.endUtcMillis)
        }
        if (insertIndex >= 0) {
            upcoming.add(insertIndex, candidate)
        } else {
            upcoming.add(candidate)
        }
        while (upcoming.size > limit) {
            upcoming.removeAt(upcoming.lastIndex)
        }
    }

    private fun addRecentCandidate(
        recent: MutableList<IptvProgram>,
        candidate: IptvProgram,
        limit: Int
    ) {
        val duplicate = recent.any {
            it.startUtcMillis == candidate.startUtcMillis &&
                it.endUtcMillis == candidate.endUtcMillis &&
                it.title.equals(candidate.title, ignoreCase = true)
        }
        if (duplicate) return

        val insertIndex = recent.indexOfFirst {
            candidate.startUtcMillis < it.startUtcMillis ||
                (candidate.startUtcMillis == it.startUtcMillis && candidate.endUtcMillis > it.endUtcMillis)
        }
        if (insertIndex >= 0) {
            recent.add(insertIndex, candidate)
        } else {
            recent.add(candidate)
        }
        while (recent.size > limit) {
            recent.removeAt(0)
        }
    }

    private fun recentProgramLimitForChannel(channel: IptvChannel?, forceCatchupHistory: Boolean = false): Int {
        return if (forceCatchupHistory || effectiveCatchupDays(channel) > 0) {
            catchupRecentProgramLimit
        } else {
            epgRecentProgramLimit
        }
    }

    private fun recentCutoffForChannel(
        channel: IptvChannel?,
        nowUtcMillis: Long,
        forceCatchupHistory: Boolean = false
    ): Long {
        val catchupDays = effectiveCatchupDays(channel, forceCatchupHistory)
        return if (catchupDays > 0) {
            nowUtcMillis - catchupDays * 24L * 60L * 60_000L
        } else {
            nowUtcMillis - 30L * 60_000L
        }
    }

    private fun oldestRecentCutoff(
        channels: Collection<IptvChannel>,
        nowUtcMillis: Long,
        forceCatchupHistory: Boolean = false
    ): Long {
        val maxCatchupDays = channels.maxOfOrNull { effectiveCatchupDays(it, forceCatchupHistory) } ?: 0
        return if (maxCatchupDays > 0) {
            nowUtcMillis - maxCatchupDays * 24L * 60L * 60_000L
        } else {
            nowUtcMillis - 30L * 60_000L
        }
    }

    private fun xmlTvRecentCutoff(channels: Collection<IptvChannel>, nowUtcMillis: Long): Long {
        return maxOf(oldestRecentCutoff(channels, nowUtcMillis), nowUtcMillis - xmlTvPastWindowMs)
    }

    private fun buildRecentCutoffByChannelId(
        channels: Collection<IptvChannel>,
        nowUtcMillis: Long
    ): Map<String, Long> {
        return channels.associate { channel ->
            channel.id to maxOf(recentCutoffForChannel(channel, nowUtcMillis), nowUtcMillis - xmlTvPastWindowMs)
        }
    }

    private fun buildRecentLimitByChannelId(channels: Collection<IptvChannel>): Map<String, Int> {
        return channels.associate { channel -> channel.id to recentProgramLimitForChannel(channel) }
    }

    private fun effectiveCatchupDays(channel: IptvChannel?, forceCatchupHistory: Boolean = false): Int {
        if (channel == null) return 0
        val explicitDays = channel.catchupDays.coerceIn(0, 7)
        if (explicitDays > 0) return explicitDays
        val hasCatchupMetadata = !channel.catchupType.isNullOrBlank() || !channel.catchupSource.isNullOrBlank()
        val hasTimeshiftUrl = channel.streamUrl.contains("/timeshift/", ignoreCase = true)
        if (hasCatchupMetadata || hasTimeshiftUrl) return 7
        if (channel.xtreamStreamId != null || channel.streamUrl.contains("/live/", ignoreCase = true)) return 2
        if (forceCatchupHistory) return 2
        return 0
    }

    private fun shouldLoadIndexedGuide(item: IptvNowNext?, channel: IptvChannel?, nowMs: Long): Boolean {
        if (item == null) return true
        return !hasEnoughFutureGuide(item, nowMs) || !hasEnoughCatchupHistory(item, channel, nowMs)
    }

    private fun hasEnoughFutureGuide(item: IptvNowNext, nowMs: Long): Boolean {
        val future = buildList {
            item.next?.let(::add)
            item.later?.let(::add)
            addAll(item.upcoming)
        }
            .asSequence()
            .filter { it.endUtcMillis > nowMs && it.startUtcMillis > nowMs }
            .distinctBy { programKey(it) }
            .sortedBy { it.startUtcMillis }
            .toList()
        if (future.size >= 6) return true
        val farthestEnd = future.maxOfOrNull { it.endUtcMillis } ?: item.now?.endUtcMillis ?: 0L
        return farthestEnd - nowMs >= indexedGuideFutureWarmMs
    }

    private fun hasEnoughCatchupHistory(item: IptvNowNext, channel: IptvChannel?, nowMs: Long): Boolean {
        val days = effectiveCatchupDays(channel)
        if (days <= 0) return true
        val targetWindowMs = minOf(catchupGuideHistoryWindowMs, days * 24L * 60L * 60_000L)
        val recent = item.recent
            .asSequence()
            .filter { it.endUtcMillis <= nowMs && it.endUtcMillis >= nowMs - targetWindowMs }
            .toList()
        if (recent.size < 6) return false
        val oldestStart = recent.minOfOrNull { it.startUtcMillis } ?: return false
        val coveredMs = nowMs - oldestStart
        return coveredMs >= (targetWindowMs * 3) / 4 || recent.size >= 24
    }

    private fun mergeCachedGuideSlice(existing: IptvNowNext?, fresh: IptvNowNext): IptvNowNext {
        if (existing == null) return fresh
        return IptvNowNext(
            now = fresh.now ?: existing.now,
            next = fresh.next ?: existing.next,
            later = fresh.later ?: existing.later,
            upcoming = mergeCachedPrograms(existing.upcoming, fresh.upcoming)
                .asSequence()
                .filter { it.startUtcMillis > 0L }
                .take(epgUpcomingProgramLimit)
                .toList(),
            recent = mergeCachedPrograms(existing.recent, fresh.recent)
                .takeLast(catchupRecentProgramLimit)
        )
    }

    private fun mergeCachedPrograms(
        existing: List<IptvProgram>,
        fresh: List<IptvProgram>
    ): List<IptvProgram> {
        if (existing.isEmpty()) return fresh
        if (fresh.isEmpty()) return existing
        return (existing.asSequence() + fresh.asSequence())
            .filter { it.title.isNotBlank() && it.endUtcMillis > it.startUtcMillis }
            .distinctBy { programKey(it) }
            .sortedBy { it.startUtcMillis }
            .toList()
    }

    private fun programKey(program: IptvProgram): String {
        return "${program.startUtcMillis}|${program.endUtcMillis}|${program.title}"
    }

    private fun hasAnyProgramData(nowNext: Map<String, IptvNowNext>): Boolean {
        if (nowNext.isEmpty()) return false
        return nowNext.values.any { item -> hasProgramData(item) }
    }

    private fun hasProgramData(item: IptvNowNext?): Boolean {
        return item != null && (
            item.now != null ||
                item.next != null ||
                item.later != null ||
                item.upcoming.isNotEmpty() ||
                item.recent.isNotEmpty()
            )
    }

    private fun epgCoverageRatio(channels: List<IptvChannel>, nowNext: Map<String, IptvNowNext>): Float {
        if (channels.isEmpty() || nowNext.isEmpty()) return 0f
        val covered = channels.count { ch ->
            hasProgramData(nowNext[ch.id])
        }
        return covered.toFloat() / channels.size.toFloat()
    }

    private fun parseXmlTvDate(rawValue: String?): Long {
        if (rawValue.isNullOrBlank()) return 0L
        val value = rawValue.trim()

        return runCatching {
            OffsetDateTime.parse(value, XMLTV_OFFSET_FORMATTER).toInstant().toEpochMilli()
        }.recoverCatching {
            val local = LocalDateTime.parse(value.take(14), XMLTV_LOCAL_FORMATTER)
            local.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
        }.getOrDefault(0L)
    }

    private fun buildChannelId(streamUrl: String, epgId: String?): String {
        val normalizedEpg = normalizeChannelKey(epgId ?: "")
        val streamKey = stableStreamKey(streamUrl)
        return if (normalizedEpg.isNotBlank()) {
            "m3u:$normalizedEpg:$streamKey"
        } else {
            "m3u:$streamKey"
        }
    }

    private fun stableStreamKey(streamUrl: String): String {
        val normalized = streamUrl.trim()
        if (normalized.isEmpty()) return "empty"
        return "${normalized.length}-${sha1Hex(normalized).take(16)}"
    }

    private fun sha1Hex(value: String): String =
        MessageDigest.getInstance("SHA-1")
            .digest(value.toByteArray(StandardCharsets.UTF_8))
            .joinToString("") { "%02x".format(it) }

    private fun extractChannelName(metadata: String?): String {
        if (metadata.isNullOrBlank()) return "Unknown Channel"
        val idx = metadata.indexOf(',')
        return if (idx >= 0 && idx < metadata.lastIndex) {
            metadata.substring(idx + 1).trim().ifBlank { "Unknown Channel" }
        } else {
            "Unknown Channel"
        }
    }

    private fun extractAttr(metadata: String?, attr: String): String? {
        if (metadata.isNullOrBlank()) return null
        val source = metadata
        val key = "$attr="
        val startIndex = source.indexOf(key, ignoreCase = true)
        if (startIndex < 0) return null

        var valueStart = startIndex + key.length
        while (valueStart < source.length && source[valueStart].isWhitespace()) {
            valueStart++
        }
        if (valueStart >= source.length) return null

        val quote = source[valueStart]
        val raw = if (quote == '"' || quote == '\'') {
            var i = valueStart + 1
            while (i < source.length) {
                val ch = source[i]
                val escaped = i > valueStart + 1 && source[i - 1] == '\\'
                if (ch == quote && !escaped) break
                i++
            }
            source.substring(valueStart + 1, i.coerceAtMost(source.length))
        } else {
            var i = valueStart
            while (i < source.length) {
                val ch = source[i]
                if (ch.isWhitespace() || ch == ',') break
                i++
            }
            source.substring(valueStart, i.coerceAtMost(source.length))
        }

        // Handle malformed IPTV provider values such as tvg-name=\'VALUE\'.
        val normalized = raw
            .trim()
            .removePrefix("\\'")
            .removeSuffix("\\'")
            .removePrefix("\\\"")
            .removeSuffix("\\\"")
            .trim('"', '\'')
            .trim()
        return normalized.takeIf { it.isNotBlank() }
    }

    private fun extractFirstAttr(metadata: String?, vararg attrs: String): String? {
        attrs.forEach { attr ->
            extractAttr(metadata, attr)?.takeIf { it.isNotBlank() }?.let { return it }
        }
        return null
    }

    private fun mergeM3uHeaderOptions(
        target: MutableMap<String, String>,
        drmProps: MutableMap<String, String>,
        line: String
    ) {
        val value = line.substringAfter(':', missingDelimiterValue = "").trim()
        if (value.isBlank()) return

        when {
            // ── DRM properties (routed to drmProps, not HTTP headers) ────
            value.startsWith("inputstream.adaptive.license_type=", ignoreCase = true) ->
                drmProps["license_type"] = value.substringAfter('=').trim()
            value.startsWith("inputstream.adaptive.license_key=", ignoreCase = true) ->
                drmProps["license_key"] = value.substringAfter('=').trim()
            value.startsWith("inputstream.adaptive.license_data=", ignoreCase = true) ->
                drmProps["license_data"] = value.substringAfter('=').trim()

            // ── HTTP headers ────────────────────────────────────────────
            value.startsWith("http-user-agent=", ignoreCase = true) ->
                target.putSafeHeader("User-Agent", value.substringAfter('=').trim())
            value.startsWith("user-agent=", ignoreCase = true) ->
                target.putSafeHeader("User-Agent", value.substringAfter('=').trim())
            value.startsWith("http-referrer=", ignoreCase = true) ->
                target.putSafeHeader("Referer", value.substringAfter('=').trim())
            value.startsWith("http-referer=", ignoreCase = true) ->
                target.putSafeHeader("Referer", value.substringAfter('=').trim())
            value.startsWith("referer=", ignoreCase = true) ->
                target.putSafeHeader("Referer", value.substringAfter('=').trim())
            value.startsWith("referrer=", ignoreCase = true) ->
                target.putSafeHeader("Referer", value.substringAfter('=').trim())
            value.startsWith("inputstream.adaptive.stream_headers=", ignoreCase = true) ->
                target.putAll(parseHeaderPairs(value.substringAfter('=')))
            value.startsWith("inputstream.adaptive.manifest_headers=", ignoreCase = true) ->
                target.putAll(parseHeaderPairs(value.substringAfter('=')))
        }
    }

    /**
     * Builds a [DrmInfo] from accumulated `#KODIPROP` DRM properties, or returns
     * `null` if no `license_type` was declared.
     */
    private fun buildDrmInfo(props: Map<String, String>): DrmInfo? {
        val rawScheme = props["license_type"] ?: return null
        return DrmInfo(
            scheme = com.arflix.tv.util.ClearKeyUtil.normalizeScheme(rawScheme),
            licenseUrl = props["license_key"],
            licenseData = props["license_data"]
        )
    }

    private fun extractInlineRequestHeaders(metadata: String?): Map<String, String> {
        if (metadata.isNullOrBlank()) return emptyMap()
        val userAgent = extractFirstAttr(metadata, "http-user-agent", "user-agent")
        val referrer = extractFirstAttr(metadata, "http-referrer", "http-referer", "referrer", "referer")
        if (userAgent == null && referrer == null) return emptyMap()
        val headers = LinkedHashMap<String, String>(2)
        userAgent?.let { headers.putSafeHeader("User-Agent", it) }
        referrer?.let { headers.putSafeHeader("Referer", it) }
        return headers
    }

    private fun parseHeaderPairs(raw: String): Map<String, String> {
        if (raw.isBlank()) return emptyMap()
        return raw
            .split('&', '|')
            .mapNotNull { part ->
                val decoded = runCatching { java.net.URLDecoder.decode(part.trim(), "UTF-8") }
                    .getOrDefault(part.trim())
                val separator = when {
                    decoded.contains("=") -> "="
                    decoded.contains(":") -> ":"
                    else -> return@mapNotNull null
                }
                val name = decoded.substringBefore(separator).trim()
                val value = decoded.substringAfter(separator).trim()
                val headerName = canonicalHeaderName(name)
                if (isSafeHttpHeader(headerName, value)) headerName to value else null
            }
            .toMap()
    }

    private fun MutableMap<String, String>.putSafeHeader(name: String, value: String) {
        if (isSafeHttpHeader(name, value)) {
            put(name, value)
        }
    }

    private fun isSafeHttpHeader(name: String, value: String): Boolean {
        return name.isNotBlank() &&
            value.isNotBlank() &&
            name.all { ch ->
                ch.code in 33..126 &&
                    ch !in setOf('(', ')', '<', '>', '@', ',', ';', ':', '\\', '"', '/', '[', ']', '?', '=', '{', '}')
            } &&
            value.all { ch -> ch == '\t' || ch.code in 32..126 }
    }

    private fun canonicalHeaderName(name: String): String {
        return when (name.trim().lowercase(Locale.US)) {
            "user-agent", "http-user-agent", "useragent" -> "User-Agent"
            "referer", "referrer", "http-referer", "http-referrer" -> "Referer"
            else -> name.trim()
        }
    }

    private fun inferQualityLabel(name: String, group: String): String? {
        val source = "$name $group".uppercase(Locale.US)
        return when {
            "4K" in source || "UHD" in source || "2160" in source -> "4K"
            "FHD" in source || "1080" in source -> "FHD"
            "HD" in source || "720" in source -> "HD"
            "SD" in source || "576" in source || "480" in source -> "SD"
            else -> null
        }
    }

    private fun buildChannelVariantKey(name: String, group: String, epgId: String?): String {
        val base = epgId?.takeIf { it.isNotBlank() } ?: name
        val normalizedBase = normalizeLooseKey(
            base
                .replace(QUALITY_WORDS_REGEX, " ")
                .replace(BRACKET_PAREN_REGEX, " ")
        )
        val normalizedGroup = normalizeLooseKey(group)
        return listOf(normalizedGroup, normalizedBase).filter { it.isNotBlank() }.joinToString(":")
    }

    private fun normalizeChannelKey(value: String): String = value.trim().lowercase(Locale.US)

    private fun normalizeLooseKey(value: String): String {
        return normalizeChannelKey(value).replace(NON_ALPHA_NUM_REGEX_INLINE, "")
    }

    private fun buildChannelKeyLookup(channels: List<IptvChannel>): Map<String, List<IptvChannel>> {
        val map = LinkedHashMap<String, MutableList<IptvChannel>>(channels.size * 8)
        channels.forEach { channel ->
            val candidates = mutableSetOf<String>()
            candidates += guideKeyCandidates(channel.name)

            channel.epgId?.takeIf { it.isNotBlank() }?.let { epgId ->
                candidates += guideKeyCandidates(epgId)
            }

            channel.tvgName?.takeIf { it.isNotBlank() }?.let { tvgName ->
                candidates += guideKeyCandidates(tvgName)
            }
            extractAttr(channel.rawTitle, "tvg-name")?.takeIf { it.isNotBlank() }?.let { tvgName ->
                candidates += guideKeyCandidates(tvgName)
            }

            candidates.filter { it.isNotBlank() }.forEach { key ->
                val bucket = map.getOrPut(key) { mutableListOf() }
                if (bucket.none { it.id == channel.id }) {
                    bucket += channel
                }
            }
        }
        return map
    }

    private fun resolveXmlTvChannels(
        xmlChannelKey: String,
        xmlChannelNameMap: Map<String, Set<String>>,
        keyLookup: Map<String, List<IptvChannel>>
    ): List<IptvChannel> {
        val normalized = normalizeChannelKey(xmlChannelKey)

        guideKeyCandidates(xmlChannelKey).forEach { key ->
            keyLookup[key]?.let { return it }
        }

        val names = xmlChannelNameMap[normalized].orEmpty()
        names.forEach { display ->
            guideKeyCandidates(display).forEach { key ->
                keyLookup[key]?.let { return it }
            }
        }
        return emptyList()
    }

    private fun scopedProviderGuideLookupChannels(
        requestedChannels: List<IptvChannel>,
        allProviderChannels: List<IptvChannel>
    ): List<IptvChannel> {
        if (requestedChannels.isEmpty()) return emptyList()
        if (requestedChannels.size > 256 || allProviderChannels.size <= requestedChannels.size) {
            return allProviderChannels
        }

        val requestedGuideKeys = requestedChannels
            .asSequence()
            .flatMap { channel -> channel.guideLookupKeys().asSequence() }
            .toSet()
        if (requestedGuideKeys.isEmpty()) return requestedChannels

        val scoped = LinkedHashMap<String, IptvChannel>(requestedChannels.size * 4)
        requestedChannels.forEach { channel -> scoped[channel.id] = channel }

        allProviderChannels.forEach { channel ->
            if (channel.id in scoped) return@forEach
            val matchesRequestedGuide = channel.guideLookupKeys().any { it in requestedGuideKeys }
            if (matchesRequestedGuide) {
                scoped[channel.id] = channel
            }
        }

        return scoped.values.toList()
    }

    private fun IptvChannel.guideLookupKeys(): Set<String> {
        val out = LinkedHashSet<String>()
        guideKeyCandidates(epgId).forEach(out::add)
        guideKeyCandidates(tvgName).forEach(out::add)
        guideKeyCandidates(variantKey).forEach(out::add)
        return out
    }

    private fun guideKeyCandidates(value: String?): Set<String> {
        val raw = value?.trim().orEmpty()
        if (raw.isBlank()) return emptySet()
        val cached = guideKeyCandidatesCache[raw]
        if (cached != null) return cached

        val withoutBrackets = raw
            .replace(BRACKET_CONTENT_REGEX, " ")
            .replace(PAREN_CONTENT_REGEX, " ")
            .replace(MULTI_SPACE_REGEX, " ")
            .trim()
        val withoutPrefix = stripGuidePrefix(withoutBrackets)
        val afterPipe = withoutBrackets.substringAfterLast('|').trim()
        val beforeDomain = withoutBrackets
            .takeIf { '.' in it && !it.any(Char::isWhitespace) }
            ?.substringBefore('.')
            ?.trim()

        val rawAliases = buildList {
            add(raw)
            add(withoutBrackets)
            add(stripQualitySuffixes(withoutBrackets))
            add(withoutPrefix)
            add(stripQualitySuffixes(withoutPrefix))
            add(afterPipe)
            add(stripQualitySuffixes(afterPipe))
            beforeDomain?.let {
                add(it)
                add(stripQualitySuffixes(it))
            }
        }

        val result = rawAliases
            .asSequence()
            .map { it.trim() }
            .filter { it.isNotBlank() }
            .flatMap { alias ->
                sequenceOf(
                    normalizeChannelKey(alias),
                    normalizeLooseKey(alias),
                    normalizeLooseKey(stripQualitySuffixes(alias))
                )
            }
            .filter { it.isNotBlank() }
            .toSet()

        guideKeyCandidatesCache[raw] = result
        return result
    }

    private fun stripGuidePrefix(value: String): String {
        return value.replace(GUIDE_PREFIX_REGEX, "").trim()
    }

    private fun stripQualitySuffixes(value: String): String {
        return value
            .lowercase(Locale.US)
            .replace(QUALITY_SUFFIX_REGEX, "")
            .replace(MULTI_SPACE_REGEX, " ")
            .trim()
    }

    private fun prepareInputStream(source: InputStream, url: String): InputStream {
        val buffered = BufferedInputStream(source)
        buffered.mark(4)
        val b1 = buffered.read()
        val b2 = buffered.read()
        buffered.reset()
        val isGzipMagic = b1 == 0x1f && b2 == 0x8b
        return if (isGzipMagic || url.lowercase(Locale.US).endsWith(".gz")) {
            GZIPInputStream(buffered)
        } else {
            buffered
        }
    }

    private fun looksLikeM3u(source: InputStream): Boolean {
        source.mark(1024)
        val bytes = ByteArray(1024)
        val read = source.read(bytes)
        source.reset()
        if (read <= 0) return false
        val text = String(bytes, 0, read, StandardCharsets.UTF_8).trimStart()
        return text.startsWith("#EXTM3U", ignoreCase = true)
    }

    private fun looksLikeXmlTv(source: InputStream): Boolean {
        source.mark(2048)
        val bytes = ByteArray(2048)
        val read = source.read(bytes)
        source.reset()
        if (read <= 0) return false
        val text = String(bytes, 0, read, StandardCharsets.UTF_8).trimStart()
        return text.startsWith("<?xml", ignoreCase = true) || text.startsWith("<tv", ignoreCase = true)
    }

    private fun cacheFile(): File {
        val dir = File(context.filesDir, "iptv_cache")
        if (!dir.exists()) dir.mkdirs()
        return File(dir, "${profileManager.getProfileIdSync()}_iptv_cache.json")
    }

    private fun channelCacheFile(): File {
        val dir = File(context.filesDir, "iptv_cache")
        if (!dir.exists()) dir.mkdirs()
        return File(dir, "${profileManager.getProfileIdSync()}_iptv_channels_cache.json")
    }

    private fun cleanupStaleEpgTempFiles(maxAgeMs: Long = 3 * 60_000L) {
        runCatching {
            val now = System.currentTimeMillis()
            context.cacheDir.listFiles { _, name -> name.startsWith("epg_") && name.endsWith(".xml") }?.forEach { file ->
                val age = now - file.lastModified()
                if (age > maxAgeMs) {
                    runCatching { file.delete() }
                }
            }
        }
    }

    private fun cleanupIptvCacheDirectory() {
        runCatching {
            val dir = File(context.filesDir, "iptv_cache")
            if (!dir.exists()) return
            dir.listFiles { _, name -> name.endsWith("_iptv_cache.json") }?.forEach { file ->
                if (file.length() > MAX_IPTV_CACHE_BYTES * 2) {
                    runCatching { file.delete() }
                }
            }
        }
    }

    private fun pruneOversizedIptvCacheFile() {
        runCatching {
            val file = cacheFile()
            if (!file.exists()) return
            if (file.length() > MAX_IPTV_CACHE_BYTES * 2) {
                file.delete()
            }
        }
    }

    private fun buildConfigSignature(config: IptvConfig): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val playlistSignature = config.playlists.joinToString(separator = "||") { playlist ->
            listOf(
                playlist.id.trim(),
                playlist.name.trim(),
                playlist.m3uUrl.trim(),
                playlist.epgUrl.trim(),
                playlist.epgUrls.orEmpty().joinToString(",") { it.trim() },
                playlist.enabled.toString()
            ).joinToString("|")
        }
        val raw = listOf(
            "playlist-group-prefix-v3-catchup-history-48h",
            config.m3uUrl.trim(),
            config.epgUrl.trim(),
            config.stalkerPortalUrl.trim(),
            config.stalkerMacAddress.trim(),
            playlistSignature
        ).joinToString("||")
        return digest.digest(raw.toByteArray(StandardCharsets.UTF_8))
            .joinToString("") { "%02x".format(it) }
    }

    private fun buildSourceSignature(config: IptvConfig): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val playlistSignature = config.playlists.joinToString(separator = "||") { playlist ->
            listOf(
                playlist.id.trim(),
                playlist.name.trim(),
                playlist.m3uUrl.trim(),
                playlist.enabled.toString()
            ).joinToString("|")
        }
        val raw = listOf(
            "playlist-sources-v2-catchup-history-48h",
            config.m3uUrl.trim(),
            config.stalkerPortalUrl.trim(),
            config.stalkerMacAddress.trim(),
            playlistSignature
        ).joinToString("||")
        return digest.digest(raw.toByteArray(StandardCharsets.UTF_8))
            .joinToString("") { "%02x".format(it) }
    }

    private fun epgIndexKey(profileId: String, config: IptvConfig): String =
        "${profileId.trim().ifBlank { "default" }}|${buildSourceSignature(config)}"

    private fun currentEpgIndexKey(config: IptvConfig): String {
        val existing = currentEpgIndexKey
        if (existing.isNotBlank()) return existing
        return epgIndexKey(profileManager.getProfileIdSync(), config)
    }

    private fun buildLegacyConfigSignature(config: IptvConfig): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val raw = "${config.m3uUrl.trim()}|${config.epgUrl.trim()}"
        return digest.digest(raw.toByteArray(StandardCharsets.UTF_8))
            .joinToString("") { "%02x".format(it) }
    }

    private fun writeCache(
        config: IptvConfig,
        channels: List<IptvChannel>,
        nowNext: Map<String, IptvNowNext>,
        loadedAtMs: Long
    ) {
        runCatching {
            // Channels are persisted to the SQLite channel store (streamed, bounded
            // memory) instead of being serialized to a giant gzipped-JSON blob. This
            // removes the ~50k-channel Gson serialization (plus the 50k channel.copy()
            // allocations) that spiked the 384MB-capped heap into a blocking-GC spiral.
            runCatching {
                val key = currentEpgIndexKey(config)
                val existingCount = channelStore.count(key)
                if (existingCount > LargeIptvListChannelCount && channels.size < existingCount) {
                    System.err.println("[IPTV-Paged] Keeping full channel store ($existingCount); skip partial write ${channels.size}")
                } else {
                    channelStore.replaceAll(key, channels, loadedAtMs)
                }
            }

            val channelsById = channels.associateBy { it.id }
            val compactNowNext = nowNext
                .asSequence()
                .filter { (_, value) -> hasProgramData(value) }
                .associate { (channelId, value) ->
                    val isCatchupChannel = effectiveCatchupDays(channelsById[channelId]) > 0
                    val recentLimit = if (isCatchupChannel) {
                        cacheCatchupRecentProgramLimit
                    } else {
                        cacheRecentProgramLimit
                    }
                    channelId to IptvNowNext(
                        now = value.now?.compactForCache(),
                        next = value.next?.compactForCache(),
                        later = value.later?.compactForCache(),
                        upcoming = value.upcoming
                            .asSequence()
                            .map { it.compactForCache() }
                            .take(cacheUpcomingProgramLimit)
                            .toList(),
                        recent = value.recent
                            .takeLast(recentLimit)
                            .map { it.compactForCache() }
                    )
                }
            val payload = IptvCachePayload(
                // Channels live in the SQLite store now; the guide cache keeps only the
                // now/next slice. (readCache is only consumed for its nowNext.)
                channels = emptyList(),
                nowNext = compactNowNext,
                loadedAtEpochMs = loadedAtMs,
                configSignature = buildConfigSignature(config),
                sourceSignature = buildSourceSignature(config),
                discoveredEpgUrls = discoveredM3uEpgUrls
                    .asSequence()
                    .map { it.trim() }
                    .filter { it.isNotBlank() }
                    .distinct()
                    .toList()
            )
            val compressed = gzipBytes(gson.toJson(payload))
            if (compressed.size <= MAX_IPTV_CACHE_BYTES) {
                cacheFile().writeBytes(compressed)
            } else {
                val reducedPayload = payload.copy(
                    nowNext = compactNowNext.mapValues { (channelId, value) ->
                        val keepCatchupRecent = if (effectiveCatchupDays(channelsById[channelId]) > 0) {
                            value.recent.takeLast(cacheCatchupRecentProgramLimit / 2)
                        } else {
                            emptyList()
                        }
                        value.copy(
                            later = null,
                            upcoming = value.upcoming.take(8),
                            recent = keepCatchupRecent
                        )
                    }
                )
                val reduced = gzipBytes(gson.toJson(reducedPayload))
                if (reduced.size <= MAX_IPTV_CACHE_BYTES) {
                    cacheFile().writeBytes(reduced)
                } else {
                    // Final fallback: keep a fast warm-start playlist even if the guide slice is too large.
                    cacheFile().writeBytes(gzipBytes(gson.toJson(payload.copy(nowNext = emptyMap()))))
                }
            }
        }
    }

    private fun readCache(config: IptvConfig): IptvCachePayload? {
        return runCatching {
            val file = cacheFile()
            if (!file.exists()) return null
            if (file.length() > MAX_IPTV_CACHE_BYTES * 2) {
                runCatching { file.delete() }
                return null
            }
            val text = decodeCacheText(file.readBytes())
            if (text.isBlank()) return null
            val payload = gson.fromJson(text, IptvCachePayload::class.java) ?: return null
            if (!isValidCacheSignature(config, payload.configSignature, payload.sourceSignature)) return null
            // Channels now come from the SQLite store, so the guide cache is channel-less;
            // it's only useful for its nowNext slice.
            if (payload.channels.isEmpty() && payload.nowNext.isEmpty()) return null
            rememberDiscoveredEpgUrls(payload.discoveredEpgUrls.orEmpty())
            payload
        }.getOrNull()
    }

    private fun readChannelCache(config: IptvConfig): IptvChannelCachePayload? {
        readDedicatedChannelCache(config)?.let { return it }
        return readChannelsFromLegacyCache(config)
    }

    private fun readDedicatedChannelCache(config: IptvConfig): IptvChannelCachePayload? {
        // Prefer the SQLite channel store: streamed cursor read, no multi-MB JSON parse.
        // The store is keyed by the config-derived index key, so its contents already
        // correspond to the current playlist/profile.
        runCatching {
            val key = currentEpgIndexKey(config)
            val count = if (key.isNotBlank()) channelStore.count(key) else 0
            if (count in PARTIAL_PAGED_CACHE_REPAIR_COUNTS && hasAnyConfiguredSource(config)) {
                System.err.println("[IPTV-Paged] Repairing partial channel store count=$count; forcing playlist cache reload")
                runCatching { channelStore.deleteSource(key) }
                return@runCatching
            }
            if (count > 0) {
                val channels = if (count > LargeIptvListChannelCount) {
                    channelStore.window(key, offset = 0, limit = 240)
                } else {
                    channelStore.loadAll(key)
                }
                if (channels.isNotEmpty()) {
                    if (count > LargeIptvListChannelCount) {
                        System.err.println("[IPTV-Paged] Large channel cache first paint window=${channels.size}/$count")
                    }
                    return IptvChannelCachePayload(
                        channels = channels,
                        loadedAtEpochMs = channelStore.updatedAtMs(key),
                        configSignature = buildConfigSignature(config),
                        sourceSignature = buildSourceSignature(config),
                        discoveredEpgUrls = discoveredM3uEpgUrls
                            .asSequence().map { it.trim() }.filter { it.isNotBlank() }.distinct().toList()
                    )
                }
            }
        }
        // Legacy Gson channel cache fallback (pre-store builds).
        return runCatching {
            val file = channelCacheFile()
            if (!file.exists()) return null
            if (file.length() > MAX_IPTV_CACHE_BYTES) {
                runCatching { file.delete() }
                return null
            }
            val text = decodeCacheText(file.readBytes())
            if (text.isBlank()) return null
            val payload = gson.fromJson(text, IptvChannelCachePayload::class.java) ?: return null
            payload.copy(channels = sanitizeCachedChannels(payload.channels))
                .takeIf { isValidCacheSignature(config, it.configSignature, it.sourceSignature) && it.channels.isNotEmpty() }
                ?.also { rememberDiscoveredEpgUrls(it.discoveredEpgUrls) }
        }.getOrNull()
    }

    private fun readChannelsFromLegacyCache(config: IptvConfig): IptvChannelCachePayload? {
        return runCatching {
            val file = cacheFile()
            if (!file.exists()) return null
            if (file.length() > MAX_IPTV_CACHE_BYTES * 2) {
                runCatching { file.delete() }
                return null
            }

            var channels: List<IptvChannel> = emptyList()
            var loadedAt = 0L
            var configSignature = ""
            var sourceSignature = ""
            var discoveredUrls: List<String> = emptyList()

            cacheJsonReader(file).use { reader ->
                reader.beginObject()
                while (reader.hasNext()) {
                    when (reader.nextName()) {
                        "channels" -> {
                            val type = TypeToken.getParameterized(List::class.java, IptvChannel::class.java).type
                            channels = gson.fromJson(reader, type) ?: emptyList()
                        }
                        "loadedAtEpochMs" -> loadedAt = runCatching { reader.nextLong() }.getOrDefault(0L)
                        "configSignature" -> configSignature = reader.nextString().orEmpty()
                        "sourceSignature" -> sourceSignature = reader.nextString().orEmpty()
                        "discoveredEpgUrls" -> {
                            val type = TypeToken.getParameterized(List::class.java, String::class.java).type
                            discoveredUrls = gson.fromJson(reader, type) ?: emptyList()
                        }
                        else -> reader.skipValue()
                    }
                }
                reader.endObject()
            }

            IptvChannelCachePayload(
                channels = sanitizeCachedChannels(channels),
                loadedAtEpochMs = loadedAt,
                configSignature = configSignature,
                sourceSignature = sourceSignature,
                discoveredEpgUrls = discoveredUrls
            ).takeIf { isValidCacheSignature(config, it.configSignature, it.sourceSignature) && it.channels.isNotEmpty() }
                ?.also {
                    rememberDiscoveredEpgUrls(it.discoveredEpgUrls)
                    channelCacheFile().writeBytes(gzipBytes(gson.toJson(it)))
                }
        }.getOrNull()
    }

    private fun sanitizeCachedChannels(channels: List<IptvChannel>): List<IptvChannel> {
        if (channels.isEmpty()) return channels
        var changed = false
        val sanitized = channels.map { channel ->
            val streamUrl = normalizeIptvStreamUrl(channel.streamUrl)
            val logo = normalizeIptvLogoUrlOrNull(channel.logo)
            if (streamUrl == channel.streamUrl && logo == channel.logo) {
                channel
            } else {
                changed = true
                channel.copy(streamUrl = streamUrl, logo = logo)
            }
        }
        return if (changed) sanitized else channels
    }

    private fun cacheJsonReader(file: File): JsonReader {
        val input = FileInputStream(file)
        val stream = BufferedInputStream(input, 64 * 1024)
        stream.mark(2)
        val b1 = stream.read()
        val b2 = stream.read()
        stream.reset()
        val source = if (b1 == 0x1f && b2 == 0x8b) GZIPInputStream(stream) else stream
        return JsonReader(InputStreamReader(source, StandardCharsets.UTF_8))
    }

    private fun isValidCacheSignature(config: IptvConfig, cacheSignature: String, cacheSourceSignature: String): Boolean {
        val currentSignature = buildConfigSignature(config)
        val legacySignature = buildLegacyConfigSignature(config)
        val currentSourceSignature = buildSourceSignature(config)
        val signature = cacheSignature.trim()
        val sourceSignature = cacheSourceSignature.trim()
        if (sourceSignature.isNotBlank()) {
            return sourceSignature == currentSourceSignature &&
                (signature.isBlank() || signature == currentSignature || signature == legacySignature)
        }
        return signature.isBlank() ||
            signature == currentSignature ||
            signature == legacySignature
    }

    private fun rememberDiscoveredEpgUrls(urls: List<String>) {
        urls.asSequence()
            .map { it.trim() }
            .filter { it.isNotBlank() }
            .forEach { discoveredM3uEpgUrls.add(it) }
    }

    private fun IptvProgram.compactForCache(): IptvProgram =
        IptvProgram(
            title = title,
            description = null,
            startUtcMillis = startUtcMillis,
            endUtcMillis = endUtcMillis
        )

    private fun gzipBytes(text: String): ByteArray {
        val output = ByteArrayOutputStream()
        GZIPOutputStream(output).bufferedWriter(StandardCharsets.UTF_8).use { writer ->
            writer.write(text)
        }
        return output.toByteArray()
    }

    private fun decodeCacheText(bytes: ByteArray): String {
        if (bytes.isEmpty()) return ""
        val isGzip = bytes.size >= 2 &&
            bytes[0] == 0x1f.toByte() &&
            bytes[1] == 0x8b.toByte()
        return if (isGzip) {
            GZIPInputStream(ByteArrayInputStream(bytes))
                .bufferedReader(StandardCharsets.UTF_8)
                .use { it.readText() }
        } else {
            bytes.toString(StandardCharsets.UTF_8)
        }
    }

    private fun encryptConfigValue(raw: String): String {
        val trimmed = raw.trim()
        if (trimmed.isBlank()) return ""
        if (trimmed.startsWith(ENC_PREFIX)) return trimmed
        return runCatching { ENC_PREFIX + encryptAesGcm(trimmed) }.getOrDefault(trimmed)
    }

    private fun decryptConfigValue(stored: String): String {
        val trimmed = stored.trim()
        if (trimmed.isBlank()) return ""
        if (!trimmed.startsWith(ENC_PREFIX)) return trimmed
        val payload = trimmed.removePrefix(ENC_PREFIX)
        return runCatching { decryptAesGcm(payload) }.getOrElse { "" }
    }

    private fun encryptAesGcm(plainText: String): String {
        val cipher = Cipher.getInstance("AES/GCM/NoPadding")
        cipher.init(Cipher.ENCRYPT_MODE, getOrCreateSecretKey())
        val iv = cipher.iv
        val encrypted = cipher.doFinal(plainText.toByteArray(StandardCharsets.UTF_8))
        val ivPart = Base64.encodeToString(iv, Base64.NO_WRAP)
        val dataPart = Base64.encodeToString(encrypted, Base64.NO_WRAP)
        return "$ivPart:$dataPart"
    }

    private fun decryptAesGcm(payload: String): String {
        val split = payload.split(":", limit = 2)
        require(split.size == 2) { "Invalid encrypted payload" }
        val iv = Base64.decode(split[0], Base64.NO_WRAP)
        val encrypted = Base64.decode(split[1], Base64.NO_WRAP)
        val cipher = Cipher.getInstance("AES/GCM/NoPadding")
        cipher.init(Cipher.DECRYPT_MODE, getOrCreateSecretKey(), GCMParameterSpec(128, iv))
        val plain = cipher.doFinal(encrypted)
        return String(plain, StandardCharsets.UTF_8)
    }

    private fun getOrCreateSecretKey(): SecretKey {
        val keyStore = KeyStore.getInstance(ANDROID_KEYSTORE).apply { load(null) }
        (keyStore.getKey(CONFIG_KEY_ALIAS, null) as? SecretKey)?.let { return it }

        val keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, ANDROID_KEYSTORE)
        val spec = KeyGenParameterSpec.Builder(
            CONFIG_KEY_ALIAS,
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        )
            .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
            .setKeySize(256)
            .build()
        keyGenerator.init(spec)
        return keyGenerator.generateKey()
    }

    private class ProgressInputStream(
        source: InputStream,
        private val onBytesRead: (Long) -> Unit
    ) : FilterInputStream(source) {
        private var totalRead: Long = 0L
        private var lastEmit: Long = 0L
        private val emitStepBytes = 8L * 1024L * 1024L

        override fun read(): Int {
            val value = super.read()
            if (value >= 0) trackRead(1)
            return value
        }

        override fun read(b: ByteArray, off: Int, len: Int): Int {
            val read = super.read(b, off, len)
            if (read > 0) trackRead(read.toLong())
            return read
        }

        private fun trackRead(bytes: Long) {
            totalRead += bytes
            if (totalRead - lastEmit >= emitStepBytes) {
                lastEmit = totalRead
                onBytesRead(totalRead)
            }
        }
    }

    // ════════════════════════════════════════════════════════════════════════
    // IPTV source-cache + language-scoped catalog index
    // (kept as a single contiguous region for easy rebase.)
    // ════════════════════════════════════════════════════════════════════════

    // ── Public types: VOD/series categories ─────────────────────────────────

    data class XtreamVodCategoryInfo(
        val categoryId: String,
        val categoryName: String
    )

    data class XtreamSeriesCategoryInfo(
        val categoryId: String,
        val categoryName: String
    )

    // ── Private wire models for categories ──────────────────────────────────

    private data class XtreamVodCategoryWire(
        @SerializedName("category_id") val categoryId: String? = null,
        @SerializedName("category_name") val categoryName: String? = null
    )

    private data class XtreamSeriesCategoryWire(
        @SerializedName("category_id") val categoryId: String? = null,
        @SerializedName("category_name") val categoryName: String? = null
    )

    @Volatile
    private var cachedXtreamVodCategories: List<XtreamVodCategoryWire> = emptyList()
    @Volatile
    private var cachedXtreamSeriesCategories: List<XtreamSeriesCategoryWire> = emptyList()
    @Volatile
    private var xtreamVodCategoriesLoadedAtMs: Long = 0L
    @Volatile
    private var xtreamSeriesCategoriesLoadedAtMs: Long = 0L

    private val vodCategoriesDiskCacheType: Type by lazy {
        TypeToken.getParameterized(XtreamDiskCache::class.java, XtreamVodCategoryWire::class.java).type
    }
    private val seriesCategoriesDiskCacheType: Type by lazy {
        TypeToken.getParameterized(XtreamDiskCache::class.java, XtreamSeriesCategoryWire::class.java).type
    }

    private fun vodCategoriesDiskCacheFile(creds: XtreamCredentials): File =
        File(xtreamDiskCacheDir(), "categories_vod_${xtreamDiskCacheHash(creds)}.json")

    private fun seriesCategoriesDiskCacheFile(creds: XtreamCredentials): File =
        File(xtreamDiskCacheDir(), "categories_series_${xtreamDiskCacheHash(creds)}.json")

    private suspend fun loadXtreamVodCategoriesInternal(
        creds: XtreamCredentials,
        allowNetwork: Boolean
    ): List<XtreamVodCategoryWire> = withContext(Dispatchers.IO) {
        val now = System.currentTimeMillis()
        if (cachedXtreamVodCategories.isNotEmpty() && now - xtreamVodCategoriesLoadedAtMs < xtreamVodCacheMs) {
            return@withContext cachedXtreamVodCategories
        }
        val diskFile = vodCategoriesDiskCacheFile(creds)
        val diskCache: XtreamDiskCache<XtreamVodCategoryWire>? =
            readDiskCache(diskFile, vodCategoriesDiskCacheType)
        if (diskCache != null && diskCache.items.isNotEmpty() &&
            now - diskCache.savedAtMs < xtreamVodCacheMs
        ) {
            cachedXtreamVodCategories = diskCache.items
            xtreamVodCategoriesLoadedAtMs = diskCache.savedAtMs
            return@withContext diskCache.items
        }
        if (!allowNetwork) {
            val items = diskCache?.items.orEmpty()
            if (items.isNotEmpty() && diskCache != null) {
                cachedXtreamVodCategories = items
                xtreamVodCategoriesLoadedAtMs = diskCache.savedAtMs
            }
            return@withContext items
        }
        val url = "${creds.baseUrl}/player_api.php?username=${creds.username}&password=${creds.password}&action=get_vod_categories"
        val list: List<XtreamVodCategoryWire> = requestJson(
            url,
            TypeToken.getParameterized(List::class.java, XtreamVodCategoryWire::class.java).type,
            client = xtreamLookupHttpClient
        ) ?: emptyList()
        if (list.isNotEmpty()) {
            val ts = System.currentTimeMillis()
            cachedXtreamVodCategories = list
            xtreamVodCategoriesLoadedAtMs = ts
            runCatching { writeDiskCache(diskFile, ts, list) }
        }
        list
    }

    private suspend fun loadXtreamSeriesCategoriesInternal(
        creds: XtreamCredentials,
        allowNetwork: Boolean
    ): List<XtreamSeriesCategoryWire> = withContext(Dispatchers.IO) {
        val now = System.currentTimeMillis()
        if (cachedXtreamSeriesCategories.isNotEmpty() && now - xtreamSeriesCategoriesLoadedAtMs < xtreamVodCacheMs) {
            return@withContext cachedXtreamSeriesCategories
        }
        val diskFile = seriesCategoriesDiskCacheFile(creds)
        val diskCache: XtreamDiskCache<XtreamSeriesCategoryWire>? =
            readDiskCache(diskFile, seriesCategoriesDiskCacheType)
        if (diskCache != null && diskCache.items.isNotEmpty() &&
            now - diskCache.savedAtMs < xtreamVodCacheMs
        ) {
            cachedXtreamSeriesCategories = diskCache.items
            xtreamSeriesCategoriesLoadedAtMs = diskCache.savedAtMs
            return@withContext diskCache.items
        }
        if (!allowNetwork) {
            val items = diskCache?.items.orEmpty()
            if (items.isNotEmpty() && diskCache != null) {
                cachedXtreamSeriesCategories = items
                xtreamSeriesCategoriesLoadedAtMs = diskCache.savedAtMs
            }
            return@withContext items
        }
        val url = "${creds.baseUrl}/player_api.php?username=${creds.username}&password=${creds.password}&action=get_series_categories"
        val list: List<XtreamSeriesCategoryWire> = requestJson(
            url,
            TypeToken.getParameterized(List::class.java, XtreamSeriesCategoryWire::class.java).type,
            client = xtreamLookupHttpClient
        ) ?: emptyList()
        if (list.isNotEmpty()) {
            val ts = System.currentTimeMillis()
            cachedXtreamSeriesCategories = list
            xtreamSeriesCategoriesLoadedAtMs = ts
            runCatching { writeDiskCache(diskFile, ts, list) }
        }
        list
    }

    suspend fun getVodCategories(): List<XtreamVodCategoryInfo> {
        val config = observeConfig().first()
        val creds = resolveXtreamCredentials(config.epgUrl)
            ?: resolveXtreamCredentials(config.m3uUrl)
            ?: return emptyList()
        return loadXtreamVodCategoriesInternal(creds, allowNetwork = true)
            .mapNotNull { it.toInfoOrNull() }
    }

    suspend fun getCachedVodCategories(): List<XtreamVodCategoryInfo> {
        val config = observeConfig().first()
        val creds = resolveXtreamCredentials(config.epgUrl)
            ?: resolveXtreamCredentials(config.m3uUrl)
            ?: return emptyList()
        return loadXtreamVodCategoriesInternal(creds, allowNetwork = false)
            .mapNotNull { it.toInfoOrNull() }
    }

    suspend fun getSeriesCategories(): List<XtreamSeriesCategoryInfo> {
        val config = observeConfig().first()
        val creds = resolveXtreamCredentials(config.epgUrl)
            ?: resolveXtreamCredentials(config.m3uUrl)
            ?: return emptyList()
        return loadXtreamSeriesCategoriesInternal(creds, allowNetwork = true)
            .mapNotNull { it.toInfoOrNull() }
    }

    suspend fun getCachedSeriesCategories(): List<XtreamSeriesCategoryInfo> {
        val config = observeConfig().first()
        val creds = resolveXtreamCredentials(config.epgUrl)
            ?: resolveXtreamCredentials(config.m3uUrl)
            ?: return emptyList()
        return loadXtreamSeriesCategoriesInternal(creds, allowNetwork = false)
            .mapNotNull { it.toInfoOrNull() }
    }

    private fun XtreamVodCategoryWire.toInfoOrNull(): XtreamVodCategoryInfo? {
        val id = categoryId?.trim().orEmpty()
        val name = categoryName?.trim().orEmpty()
        if (id.isBlank()) return null
        return XtreamVodCategoryInfo(id, name)
    }

    private fun XtreamSeriesCategoryWire.toInfoOrNull(): XtreamSeriesCategoryInfo? {
        val id = categoryId?.trim().orEmpty()
        val name = categoryName?.trim().orEmpty()
        if (id.isBlank()) return null
        return XtreamSeriesCategoryInfo(id, name)
    }

    // ── VOD ID-only index (cheap, built at catalog load) ────────────────────

    /**
     * Lightweight TMDB/IMDb → catalog-index map, built inline during
     * `loadXtreamVodStreams` (~100 ms for a 60 k catalog). Lets the common
     * ID-based movie lookup short-circuit to O(1) without paying the
     * full token-index build cost.
     */
    private data class VodIdIndex(
        val items: List<XtreamVodStream>,
        val tmdbMap: Map<String, List<Int>>,
        val imdbMap: Map<String, List<Int>>
    )

    @Volatile
    private var cachedVodIdIndex: VodIdIndex? = null

    private fun buildVodIdIndex(catalog: List<XtreamVodStream>): VodIdIndex {
        if (catalog.isEmpty()) return VodIdIndex(catalog, emptyMap(), emptyMap())
        val tmdbMap = HashMap<String, MutableList<Int>>()
        val imdbMap = HashMap<String, MutableList<Int>>()
        catalog.forEachIndexed { idx, item ->
            val tmdbKey = normalizeTmdbId(item.tmdb)
            if (!tmdbKey.isNullOrBlank()) {
                tmdbMap.getOrPut(tmdbKey) { mutableListOf() }.add(idx)
            }
            val imdbKey = normalizeImdbId(item.imdb)
            if (!imdbKey.isNullOrBlank() && imdbKey != IptvIdSentinels.IMDB_NONE) {
                imdbMap.getOrPut(imdbKey) { mutableListOf() }.add(idx)
            }
        }
        return VodIdIndex(items = catalog, tmdbMap = tmdbMap, imdbMap = imdbMap)
    }

    // ── VOD catalog index (O(1) ID lookup + O(k) token intersection) ────────

    private data class VodCatalogIndex(
        val createdAtMs: Long,
        val items: List<XtreamVodStream>,
        val tmdbMap: Map<String, List<Int>>,
        val imdbMap: Map<String, List<Int>>,
        val canonicalTitleMap: Map<String, List<Int>>,
        val tokenMap: Map<String, List<Int>>
    )

    @Volatile
    private var cachedVodIndex: VodCatalogIndex? = null

    private fun buildVodCatalogIndex(catalog: List<XtreamVodStream>): VodCatalogIndex {
        val tmdbMap = HashMap<String, MutableList<Int>>()
        val imdbMap = HashMap<String, MutableList<Int>>()
        val canonicalTitleMap = HashMap<String, MutableList<Int>>()
        val tokenMap = HashMap<String, MutableList<Int>>()
        catalog.forEachIndexed { idx, item ->
            val tmdbKey = normalizeTmdbId(item.tmdb)
            if (!tmdbKey.isNullOrBlank()) {
                tmdbMap.getOrPut(tmdbKey) { mutableListOf() }.add(idx)
            }
            val imdbKey = normalizeImdbId(item.imdb)
            if (!imdbKey.isNullOrBlank() && imdbKey != IptvIdSentinels.IMDB_NONE) {
                imdbMap.getOrPut(imdbKey) { mutableListOf() }.add(idx)
            }
            val name = item.name?.trim().orEmpty()
            if (name.isNotBlank()) {
                val normalized = normalizeLookupText(name)
                val tokens = extractTitleTokensFromNormalized(normalized)
                if (tokens.isNotEmpty()) {
                    val canonical = toCanonicalTitleKeyFromTokens(tokens)
                    if (canonical.isNotBlank()) {
                        canonicalTitleMap.getOrPut(canonical) { mutableListOf() }.add(idx)
                    }
                    tokens.forEach { token ->
                        tokenMap.getOrPut(token) { mutableListOf() }.add(idx)
                    }
                }
            }
        }
        return VodCatalogIndex(
            createdAtMs = System.currentTimeMillis(),
            items = catalog,
            tmdbMap = tmdbMap,
            imdbMap = imdbMap,
            canonicalTitleMap = canonicalTitleMap,
            tokenMap = tokenMap
        )
    }

    private fun ensureVodCatalogIndex(vod: List<XtreamVodStream>): VodCatalogIndex {
        val existing = cachedVodIndex
        if (existing != null && existing.items === vod) {
            return existing
        }
        return buildVodCatalogIndex(vod).also { cachedVodIndex = it }
    }

    /**
     * Look up movie matches using the indexed catalog. Falls back to a token
     * scan only across a narrow candidate set rather than the whole catalog.
     */
    private fun findMovieCandidatesIndexed(
        index: VodCatalogIndex,
        normalizedTitle: String,
        normalizedTmdb: String?,
        normalizedImdb: String?,
        inputYear: Int?
    ): List<XtreamVodStream> {
        if (index.items.isEmpty()) return emptyList()

        if (!normalizedTmdb.isNullOrBlank()) {
            val hits = index.tmdbMap[normalizedTmdb].orEmpty()
            if (hits.isNotEmpty()) return hits.map { index.items[it] }
        }
        if (!normalizedImdb.isNullOrBlank() && normalizedImdb != IptvIdSentinels.IMDB_NONE) {
            val hits = index.imdbMap[normalizedImdb].orEmpty()
            if (hits.isNotEmpty()) return hits.map { index.items[it] }
        }
        if (normalizedTitle.isBlank()) return emptyList()

        val canonical = toCanonicalTitleKey(normalizedTitle)
        val canonicalHits = if (canonical.isNotBlank()) {
            index.canonicalTitleMap[canonical].orEmpty().map { index.items[it] }
        } else emptyList()

        // Build candidate pool via token intersection: union of items mentioning any token.
        val queryTokens = extractTitleTokensFromNormalized(normalizedTitle)
        val candidatePoolIdx = LinkedHashSet<Int>()
        queryTokens.forEach { token ->
            index.tokenMap[token]?.forEach { idx -> candidatePoolIdx.add(idx) }
        }
        if (candidatePoolIdx.isEmpty() && canonicalHits.isEmpty()) return emptyList()

        // Score on the candidate set (typically < 100 items even on big catalogs).
        val scored = (canonicalHits.indices.map { canonicalHits[it] } + candidatePoolIdx.map { index.items[it] })
            .asSequence()
            .distinctBy { it.streamId ?: System.identityHashCode(it) }
            .mapNotNull { item ->
                val name = item.name?.trim().orEmpty()
                if (name.isBlank()) return@mapNotNull null
                val score = scoreNameMatch(name, normalizedTitle)
                if (score <= 0) return@mapNotNull null
                val providerYear = parseYear(item.year ?: name)
                val yearDelta = if (inputYear != null && providerYear != null) {
                    kotlin.math.abs(providerYear - inputYear)
                } else null
                val yearAdjust = when {
                    inputYear == null || providerYear == null -> 0
                    yearDelta == 0 -> 20
                    yearDelta == 1 -> 8
                    else -> -25
                }
                Pair(item, score + yearAdjust)
            }
            .sortedByDescending { it.second }
            .toList()
        val bestScore = scored.firstOrNull()?.second ?: return emptyList()
        val minScore = maxOf(65, bestScore - 8)
        return scored.takeWhile { it.second >= minScore }.map { it.first }
    }

    // ── Movie source cache (persistent, profile-scoped, 24h TTL) ────────────

    private data class CachedIptvMovieSources(
        val sources: List<StreamSource>,
        val savedAtMs: Long,
        val credsFingerprint: String
    )

    private data class PersistedMovieSourceCache(
        val items: Map<String, CachedIptvMovieSources> = emptyMap()
    )

    private val iptvMovieSourcePrefs by lazy {
        context.getSharedPreferences("iptv_movie_source_cache_v1", Context.MODE_PRIVATE)
    }
    private val iptvMovieSourcePrefsKey = "movie_source_map"

    private val iptvMovieSourceMemory = object : LinkedHashMap<String, CachedIptvMovieSources>(256, 0.75f, true) {
        override fun removeEldestEntry(eldest: MutableMap.MutableEntry<String, CachedIptvMovieSources>?): Boolean {
            return size > 256
        }
    }
    private val iptvMovieSourceLock = Any()

    @Volatile
    private var iptvMovieSourceCacheHydrated = false

    private val iptvMovieSourceTtlMs = 24 * 60 * 60_000L
    @Volatile
    private var cachedProfileIdHashPair: Pair<String, String>? = null
    private val iptvCacheScope = kotlinx.coroutines.CoroutineScope(
        kotlinx.coroutines.SupervisorJob() + Dispatchers.IO
    )
    @Volatile
    private var iptvMovieSourcePersistJob: kotlinx.coroutines.Job? = null
    private val iptvPersistLock = Any()

    private fun hydrateIptvMovieSourceCache() {
        if (iptvMovieSourceCacheHydrated) return
        // Deserialize outside the lock so concurrent callers don't queue on the monitor
        // waiting for a potentially large JSON parse.
        val loaded: Map<String, CachedIptvMovieSources> = runCatching {
            val raw = iptvMovieSourcePrefs.getString(iptvMovieSourcePrefsKey, null)
            if (!raw.isNullOrBlank()) gson.fromJson(raw, PersistedMovieSourceCache::class.java)?.items else null
        }.getOrNull().orEmpty()
        synchronized(iptvMovieSourceLock) {
            if (iptvMovieSourceCacheHydrated) return
            iptvMovieSourceMemory.putAll(loaded)
            iptvMovieSourceCacheHydrated = true
        }
    }

    private fun iptvMovieSourceCacheKey(
        profileIdHash: String,
        imdbId: String?,
        tmdbId: Int?,
        title: String,
        year: Int?
    ): String? {
        val imdb = IptvIdSentinels.normalizeImdb(imdbId)
        val tmdb = IptvIdSentinels.normalizeTmdb(tmdbId)
        val titleKey = if (IptvIdSentinels.isReal(imdb) || IptvIdSentinels.isReal(tmdb)) {
            ""
        } else {
            val canonical = toCanonicalTitleKey(title)
            // For title-only matches, append year to disambiguate remakes
            // (e.g., Dune 1984 vs Dune 2021)
            val resolvedYear = year ?: parseYear(title)
            if (canonical.isNotBlank() && resolvedYear != null) {
                "$canonical|$resolvedYear"
            } else {
                canonical
            }
        }
        // No real id AND no title token → uncacheable (would collide).
        if (!IptvIdSentinels.isReal(imdb) && !IptvIdSentinels.isReal(tmdb) && titleKey.isBlank()) {
            return null
        }
        return "$profileIdHash|$imdb|$tmdb|$titleKey"
    }

    private fun profileIdHash(): String {
        val raw = runCatching { profileManager.getProfileIdSync() }.getOrDefault("default")
        cachedProfileIdHashPair?.let { (cachedRaw, cachedHash) ->
            if (cachedRaw == raw) return cachedHash
        }
        val hash = MessageDigest.getInstance("MD5").digest(raw.toByteArray(StandardCharsets.UTF_8))
            .joinToString("") { "%02x".format(it) }
            .take(12)
        cachedProfileIdHashPair = raw to hash
        return hash
    }

    private fun lookupCachedMovieSources(
        key: String,
        credsFingerprint: String
    ): List<StreamSource>? {
        hydrateIptvMovieSourceCache()
        val entry = synchronized(iptvMovieSourceLock) { iptvMovieSourceMemory[key] } ?: return null
        if (entry.credsFingerprint != credsFingerprint) return null
        if (entry.sources.isEmpty()) return null
        if (System.currentTimeMillis() - entry.savedAtMs > iptvMovieSourceTtlMs) return null
        return entry.sources
    }

    private fun storeCachedMovieSources(
        key: String,
        sources: List<StreamSource>,
        credsFingerprint: String
    ) {
        if (sources.isEmpty()) return
        hydrateIptvMovieSourceCache()
        synchronized(iptvMovieSourceLock) {
            iptvMovieSourceMemory[key] = CachedIptvMovieSources(
                sources = sources,
                savedAtMs = System.currentTimeMillis(),
                credsFingerprint = credsFingerprint
            )
        }
        scheduleIptvMovieSourceCachePersist()
    }

    private fun scheduleIptvMovieSourceCachePersist() {
        synchronized(iptvPersistLock) {
            iptvMovieSourcePersistJob?.cancel()
            iptvMovieSourcePersistJob = iptvCacheScope.launch {
                delay(2_000L)
                val snapshot = synchronized(iptvMovieSourceLock) {
                    PersistedMovieSourceCache(iptvMovieSourceMemory.toMap())
                }
                runCatching {
                    iptvMovieSourcePrefs.edit()
                        .putString(iptvMovieSourcePrefsKey, gson.toJson(snapshot))
                        .apply()
                }
            }
        }
    }

    private fun clearIptvMovieSourceCache() {
        synchronized(iptvMovieSourceLock) {
            iptvMovieSourceMemory.clear()
        }
        runCatching { iptvMovieSourcePrefs.edit().remove(iptvMovieSourcePrefsKey).apply() }
        synchronized(iptvPersistLock) { iptvMovieSourcePersistJob?.cancel() }
    }

    // ════════════════════════════════════════════════════════════════════════

    private companion object {
        private val DURATION_SCALE_REGEX = Regex("""\$\{duration:(\d+)\}|\{duration:(\d+)\}""")
        private val URL_QUERY_SECRETS_REGEX = Regex("""(?i)([?&](?:username|user|uname|password|pass|pwd)=)[^&]+""")
        private val URL_PATH_SECRETS_REGEX = Regex("""(?i)(/(?:live|movie|series|timeshift)/)([^/]+)/([^/]+)(/)""")
        private val QUALITY_WORDS_REGEX = Regex("""\b(4K|UHD|FHD|HD|SD|2160P?|1080P?|720P?|576P?|480P?)\b""", RegexOption.IGNORE_CASE)
        private val BRACKET_PAREN_REGEX = Regex("""\[[^\]]*]|\([^)]*\)""")

        const val ENC_PREFIX = "encv1:"
        const val ANDROID_KEYSTORE = "AndroidKeyStore"
        const val CONFIG_KEY_ALIAS = "arvio_iptv_config_v1"
        const val MAX_IPTV_CACHE_BYTES = 25L * 1024L * 1024L
        val PARTIAL_PAGED_CACHE_REPAIR_COUNTS = setOf(144, 240)
        const val IPTV_USER_AGENT = "VLC/3.0.20 LibVLC/3.0.20"
        const val BROWSER_USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 Safari/537.36"

        val BRACKET_CONTENT_REGEX = Regex("""\[[^\]]*]""")
        val PAREN_CONTENT_REGEX = Regex("""\([^\)]*\)""")
        val YEAR_PAREN_REGEX = Regex("""\((19|20)\d{2}\)""")
        val SEASON_TOKEN_REGEX = Regex("""\b(s|season)\s*\d{1,2}\b""", RegexOption.IGNORE_CASE)
        val EPISODE_TOKEN_REGEX = Regex("""\b(e|ep|episode)\s*\d{1,3}\b""", RegexOption.IGNORE_CASE)
        val RELEASE_TAG_REGEX = Regex(
            """\b(2160p|1080p|720p|480p|4k|uhd|fhd|hdr|dv|dovi|hevc|x265|x264|h264|remux|bluray|bdrip|webrip|web[- ]?dl|proper|repack|multi|dubbed|dual[- ]?audio)\b""",
            RegexOption.IGNORE_CASE
        )
        val NON_ALPHA_NUM_REGEX = Regex("[^a-z0-9]+")
        val MULTI_SPACE_REGEX = Regex("\\s+")
        val HTTP_URL_REGEX = Regex("""https?://[^\s,;|"]+""", RegexOption.IGNORE_CASE)
        val SEASON_KEY_REGEX = Regex("""\d{1,2}""")
        val FLEXIBLE_INT_REGEX = Regex("""\d{1,4}""")
        val IMDB_ID_REGEX = Regex("tt\\d{5,10}")
        val TMDB_ID_REGEX = Regex("\\d{1,10}")
        val YEAR_REGEX = Regex("(19|20)\\d{2}")
        val SEASON_EPISODE_PATTERNS = listOf(
            Regex("""\bs(\d{1,2})\s*[\.\-_ ]*\s*e(\d{1,3})\b""", RegexOption.IGNORE_CASE),
            Regex("""\b(\d{1,2})x(\d{1,3})\b""", RegexOption.IGNORE_CASE),
            Regex("""\bseason\s*(\d{1,2}).*episode\s*(\d{1,3})\b""", RegexOption.IGNORE_CASE),
            Regex("""\bseason\s*(\d{1,2}).*ep(?:isode)?\s*(\d{1,3})\b""", RegexOption.IGNORE_CASE),
            Regex("""\b(\d{1,2})\.(\d{1,3})\b""", RegexOption.IGNORE_CASE),
            Regex("""\b(\d)(\d{2})\b""", RegexOption.IGNORE_CASE)
        )
        val EPISODE_ONLY_PATTERNS = listOf(
            Regex("""\bepisode\s*(\d{1,3})\b""", RegexOption.IGNORE_CASE),
            Regex("""\bep\s*(\d{1,3})\b""", RegexOption.IGNORE_CASE),
            Regex("""\be(\d{1,3})\b""", RegexOption.IGNORE_CASE),
            Regex("""\bpart\s*(\d{1,3})\b""", RegexOption.IGNORE_CASE),
            Regex("""[\[\(\- ](\d{1,3})[\]\) ]?$""", RegexOption.IGNORE_CASE)
        )
        val HTML_STYLE_REGEX = Regex("<style[^>]*>[\\s\\S]*?</style>", RegexOption.IGNORE_CASE)
        val HTML_SCRIPT_REGEX = Regex("<script[^>]*>[\\s\\S]*?</script>", RegexOption.IGNORE_CASE)
        val HTML_TAG_REGEX = Regex("<[^>]+>")
        val CSS_BRACE_REGEX = Regex("\\{[^}]*\\}")
        val NON_ALPHA_NUM_REGEX_INLINE = Regex("[^a-z0-9]")
        val QUALITY_SUFFIX_REGEX = Regex("\\b(hd|fhd|uhd|sd|4k|hevc|x265|x264|h264|h265)\\b")
        val GUIDE_PREFIX_REGEX = Regex("""^\s*[a-z]{2,4}\s*[\|:：/\-]+\s*""", RegexOption.IGNORE_CASE)

        val XMLTV_LOCAL_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss")

        val XMLTV_OFFSET_FORMATTER: DateTimeFormatter = DateTimeFormatterBuilder()
            .appendPattern("yyyyMMddHHmmss")
            .optionalStart()
            .appendLiteral(' ')
            .appendPattern("XX")
            .optionalEnd()
            .toFormatter(Locale.US)

    }
}


private object IptvRepositoryRegexes {
    val DURATION_PLACEHOLDER_REGEX = Regex("""\$\{duration:(\d+)\}|\{duration:(\d+)\}""")
    val IPTV_URL_REDACT_SECRETS_REGEX = Regex("""(?i)([?&](?:username|user|uname|password|pass|pwd)=)[^&]+""")
    val IPTV_URL_REDACT_PATH_REGEX = Regex("""(?i)(/(?:live|movie|series|timeshift)/)([^/]+)/([^/]+)(/)""")
    val RESOLUTION_TAG_REGEX = Regex("""\b(4K|UHD|FHD|HD|SD|2160P?|1080P?|720P?|576P?|480P?)\b""", RegexOption.IGNORE_CASE)
    val BRACKET_PAREN_REGEX = Regex("""\[[^\]]*]|\([^)]*\)""")
}
