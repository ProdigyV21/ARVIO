package com.arflix.tv.data.repository

import android.content.Context
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.arflix.tv.data.model.IptvChannel
import com.arflix.tv.data.model.IptvNowNext
import com.arflix.tv.data.model.IptvProgram
import com.arflix.tv.data.model.IptvSnapshot
import com.arflix.tv.data.model.StreamSource
import com.arflix.tv.util.settingsDataStore
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
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
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import java.lang.reflect.Type
import java.security.KeyStore
import java.security.MessageDigest

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
    val enabled: Boolean = true
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
    val lastOpenedAt: Long = 0L
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
    private val maxSeriesEpisodeCacheEntries = 8

    @Volatile
    private var cachedChannels: List<IptvChannel> = emptyList()
    @Volatile
    private var cachedGroupedChannels: Map<String, List<IptvChannel>> = emptyMap()
    @Volatile var cachedStalkerApi: com.arflix.tv.data.api.StalkerApi? = null

    @Volatile
    private var cachedNowNext: ConcurrentHashMap<String, IptvNowNext> = ConcurrentHashMap()

    @Volatile
    private var cachedPlaylistAt: Long = 0L

    @Volatile
    private var cachedEpgAt: Long = 0L
    @Volatile
    private var lastEpgCachePersistAt: Long = 0L

    @Volatile
    private var preferredDerivedEpgUrl: String? = null
    @Volatile
    private var discoveredM3uEpgUrl: String? = null
    @Volatile
    private var cacheOwnerProfileId: String? = null
    @Volatile
    private var cacheOwnerConfigSig: String? = null
    @Volatile
    private var xtreamVodCacheKey: String? = null
    @Volatile
    private var xtreamVodLoadedAtMs: Long = 0L
    private val startupPrefetchInFlight = AtomicBoolean(false)

    private fun buildGroupedChannels(channels: List<IptvChannel>): Map<String, List<IptvChannel>> =
        channels.groupBy { it.group.ifBlank { "Uncategorized" } }
    private fun hasAnyConfiguredSource(config: IptvConfig): Boolean =
        config.m3uUrl.isNotBlank() ||
            config.stalkerPortalUrl.isNotBlank() ||
            config.playlists.any { it.enabled && it.m3uUrl.isNotBlank() }
    private fun activePlaylists(config: IptvConfig): List<IptvPlaylistEntry> =
        config.playlists.filter { it.enabled }.ifEmpty {
            if (config.m3uUrl.isNotBlank()) {
                listOf(IptvPlaylistEntry("list_1", "List 1", config.m3uUrl, config.epgUrl, enabled = true))
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
    private val epgUpcomingProgramLimit = 14
    private val epgRecentProgramLimit = 2
    private val completeEpgCoverageTarget = 0.98f
    private val xtreamShortEpgLimit = 8
    private val startupShortEpgChannelLimit = 1200
    private val xtreamShortEpgBatchSize = 512
    private val xtreamShortEpgConcurrency = 32
    private val cacheUpcomingProgramLimit = 8
    private val cacheRecentProgramLimit = 1
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
        val configSignature: String = ""
    )

    fun observeConfig(): Flow<IptvConfig> =
        profileManager.activeProfileId.combine(context.settingsDataStore.data) { _, prefs ->
            val playlists = decodePlaylists(prefs[playlistsKey()].orEmpty())
            val primary = playlists.firstOrNull()
            IptvConfig(
                m3uUrl = primary?.m3uUrl ?: decryptConfigValue(prefs[m3uUrlKey()].orEmpty()),
                epgUrl = primary?.epgUrl ?: decryptConfigValue(prefs[epgUrlKey()].orEmpty()),
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
                        lastFocusedZone = state.lastFocusedZone.trim().ifBlank { "GUIDE" }
                    )
                )
            }
        }
        invalidationBus.markDirty(CloudSyncScope.IPTV, profileManager.getProfileIdSync(), "save tv session")
    }

    suspend fun saveConfig(m3uUrl: String, epgUrl: String) {
        val normalizedM3u = normalizeIptvInput(m3uUrl)
        val normalizedEpg = normalizeEpgInput(epgUrl)
        val primary = if (normalizedM3u.isNotBlank()) listOf(
            IptvPlaylistEntry(id = "list_1", name = "List 1", m3uUrl = normalizedM3u, epgUrl = normalizedEpg)
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
            IptvPlaylistEntry(
                id = item.id.ifBlank { "list_${index + 1}" },
                name = item.name.ifBlank { "List ${index + 1}" },
                m3uUrl = normalizeIptvInput(item.m3uUrl),
                epgUrl = normalizeEpgInput(item.epgUrl),
                enabled = item.enabled
            )
        }.filter { it.m3uUrl.isNotBlank() }.take(3)

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
        context.settingsDataStore.edit { prefs ->
            if (m3uUrl.isBlank()) {
                prefs.remove(m3uUrlKey())
            } else {
                prefs[m3uUrlKey()] = encryptConfigValue(m3uUrl)
            }
            if (epgUrl.isBlank()) {
                prefs.remove(epgUrlKey())
            } else {
                prefs[epgUrlKey()] = encryptConfigValue(epgUrl)
            }
            prefs[playlistsKey()] = gson.toJson(
                if (m3uUrl.isNotBlank()) listOf(IptvPlaylistEntry("list_1", "List 1", m3uUrl, epgUrl)) else emptyList()
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

    suspend fun toggleHiddenGroup(groupName: String) {
        val trimmed = groupName.trim()
        if (trimmed.isEmpty()) return
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

    suspend fun moveGroupUp(groupName: String, currentGroups: List<String> = emptyList()) {
        val target = groupName.trim()
        if (target.isEmpty()) return
        context.settingsDataStore.edit { prefs ->
            val order = mergedGroupOrder(decodeGroupOrder(prefs), currentGroups)
            if (order.isEmpty()) return@edit
            val idx = order.indexOf(target)
            if (idx > 0) { order.removeAt(idx); order.add(idx - 1, target) }
            prefs[groupOrderKey()] = gson.toJson(order)
        }
        invalidationBus.markDirty(CloudSyncScope.IPTV, profileManager.getProfileIdSync(), "move group up")
    }

    suspend fun moveGroupToTop(groupName: String, currentGroups: List<String> = emptyList()) {
        val target = groupName.trim()
        if (target.isEmpty()) return
        context.settingsDataStore.edit { prefs ->
            val order = mergedGroupOrder(decodeGroupOrder(prefs), currentGroups)
            if (order.isEmpty()) return@edit
            if (target !in order && currentGroups.map { it.trim() }.contains(target)) order.add(target)
            order.remove(target)
            order.add(0, target)
            prefs[groupOrderKey()] = gson.toJson(order)
        }
        invalidationBus.markDirty(CloudSyncScope.IPTV, profileManager.getProfileIdSync(), "move group to top")
    }

    suspend fun moveGroupDown(groupName: String, currentGroups: List<String> = emptyList()) {
        val target = groupName.trim()
        if (target.isEmpty()) return
        context.settingsDataStore.edit { prefs ->
            val order = mergedGroupOrder(decodeGroupOrder(prefs), currentGroups)
            if (order.isEmpty()) return@edit
            val idx = order.indexOf(target)
            if (idx >= 0 && idx < order.size - 1) { order.removeAt(idx); order.add(idx + 1, target) }
            prefs[groupOrderKey()] = gson.toJson(order)
        }
        invalidationBus.markDirty(CloudSyncScope.IPTV, profileManager.getProfileIdSync(), "move group down")
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
            onProgress(IptvLoadProgress("Starting IPTV load...", 2))
            val now = System.currentTimeMillis()
            val config = observeConfig().first()
            val profileId = profileManager.getProfileIdSync()
            ensureCacheOwnership(profileId, config)
            cleanupIptvCacheDirectory()
            val activePlaylists = config.playlists.filter { it.enabled }.ifEmpty {
                if (config.m3uUrl.isNotBlank()) listOf(IptvPlaylistEntry("list_1", "List 1", config.m3uUrl, config.epgUrl, enabled = true)) else emptyList()
            }
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
                onProgress(IptvLoadProgress("Connecting to Stalker portal...", 10))
                val stalker = com.arflix.tv.data.api.StalkerApi(config.stalkerPortalUrl, config.stalkerMacAddress)
                if (!stalker.handshake()) {
                    return@withContext IptvSnapshot(epgWarning = "Stalker handshake failed. Check Portal URL and MAC.", loadedAt = Instant.now())
                }
                onProgress(IptvLoadProgress("Loading channels from portal...", 30))
                stalker.getProfile()
                val channels = stalker.getChannels()
                onProgress(IptvLoadProgress("Loaded ${channels.size} channels", 80))
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
                onProgress(IptvLoadProgress("Done", 100))
                return@withContext snapshot
            }

            val cachedFromDisk = if (cachedChannels.isEmpty()) readCache(config) else null
            if (cachedFromDisk != null) {
                cachedChannels = cachedFromDisk.channels
                cachedGroupedChannels = buildGroupedChannels(cachedFromDisk.channels)
                cachedNowNext = ConcurrentHashMap(cachedFromDisk.nowNext)
                cachedPlaylistAt = cachedFromDisk.loadedAtEpochMs
                cachedEpgAt = cachedFromDisk.loadedAtEpochMs
                reDeriveCachedNowNext(cachedFromDisk.channels.asSequence().map { it.id }.toSet())
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
                    val aggregatedChannels = java.util.Collections.synchronizedList(mutableListOf<IptvChannel>())
                    activePlaylists.map { playlist ->
                        async {
                            val playlistChannels = fetchChannelsForPlaylistWithRetries(playlist, onProgress).map { channel ->
                                channel.copy(
                                    id = "${playlist.id}:${channel.id}",
                                    group = channel.group
                                )
                            }
                            if (playlistChannels.isNotEmpty()) {
                                aggregatedChannels += playlistChannels
                                runCatching { onChannelsReady(aggregatedChannels.toList()) }
                            }
                            playlistChannels
                        }
                    }.awaitAll().flatten()
                }.also {
                    cachedChannels = it
                    cachedGroupedChannels = buildGroupedChannels(it)
                    cachedPlaylistAt = System.currentTimeMillis()
                }
            }

            // Publish channels immediately so the UI can show them while EPG loads.
            // Uses cached nowNext if available to paint initial EPG state.
            runCatching { onChannelsReady(channels) }
            if ((forcePlaylistReload || cachedFromDisk == null) && channels.isNotEmpty()) {
                val immediateNowNext = reDeriveCachedNowNext(channels.asSequence().map { it.id }.toSet()).orEmpty()
                writeCache(
                    config = config,
                    channels = channels,
                    nowNext = immediateNowNext,
                    loadedAtMs = System.currentTimeMillis()
                )
            }

            val epgCandidates = resolveEpgCandidates(config)
            var epgUpdated = false
            val cachedHasPrograms = hasAnyProgramData(cachedNowNext)
            val shouldUseCachedEpg = !forceEpgReload && (
                cachedHasPrograms ||
                    (!cachedHasPrograms && now - cachedEpgAt < epgEmptyRetryMs)
                )
            var epgFailureMessage: String? = null

            // Check if this is an Xtream provider (can use fast short EPG API)
            val xtreamCreds = resolveXtreamCredentials(config)
            val hasXtreamChannels = channels.any { resolveXtreamStreamId(it) != null }
            val shouldFetchBroadShortEpg = allowBroadShortEpg || epgCandidates.isEmpty() || channels.size <= 10_000
            System.err.println("[EPG] loadSnapshot: forceEpgReload=$forceEpgReload shouldUseCachedEpg=$shouldUseCachedEpg cachedHasPrograms=$cachedHasPrograms xtreamCreds=${xtreamCreds != null} hasXtreamChannels=$hasXtreamChannels epgCandidates=${epgCandidates.size} broadShort=$shouldFetchBroadShortEpg")
            val cachedFallbackNowNext =
                reDeriveCachedNowNext(channels.asSequence().map { it.id }.toSet()) ?: cachedNowNext
            val nowNext = if (shouldUseCachedEpg) {
                onProgress(IptvLoadProgress("Using cached EPG", 92))
                System.err.println("[EPG] Using cached EPG (${cachedNowNext.size} channels, age=${(now - cachedEpgAt)/1000}s)")
                cachedFallbackNowNext
            } else if (!allowNetworkEpgFetch) {
                if (cachedFallbackNowNext.isNotEmpty()) {
                    onProgress(IptvLoadProgress("Using cached EPG", 92))
                    System.err.println("[EPG] Skipping broad network EPG fetch and using cached fallback (${cachedFallbackNowNext.size} channels)")
                    cachedFallbackNowNext
                } else {
                    System.err.println("[EPG] Skipping broad network EPG fetch until category-scoped guide request")
                    emptyMap()
                }
            } else if (epgCandidates.isEmpty() && xtreamCreds == null) {
                if (cachedFallbackNowNext.isNotEmpty()) {
                    onProgress(IptvLoadProgress("Using cached EPG", 92))
                    System.err.println("[EPG] No active EPG source, keeping cached EPG fallback (${cachedFallbackNowNext.size} channels)")
                    cachedFallbackNowNext
                } else {
                    onProgress(IptvLoadProgress("No EPG URL configured", 90))
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
                if (xtreamCreds != null && hasXtreamChannels && shouldFetchBroadShortEpg) {
                    System.err.println("[EPG] Attempting Xtream short EPG (baseUrl=${xtreamCreds.baseUrl})")
                    val shortEpgAttempt = runCatching {
                        fetchXtreamShortEpg(xtreamCreds, channels, onProgress)
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
                            epgUpdated = true
                            resolved = true
                            System.err.println("[EPG] Xtream short EPG SUCCESS: ${parsed.size} fresh, ${cachedNowNext.size} total cached")
                        }
                    } else {
                        System.err.println("[EPG] Xtream short EPG FAILED: ${shortEpgAttempt.exceptionOrNull()?.message}")
                    }
                } else if (xtreamCreds != null && hasXtreamChannels) {
                    System.err.println("[EPG] Skipping broad Xtream short EPG so full XMLTV can backfill ${channels.size} channels first")
                }

                // ── Slow path: XMLTV download (always runs to fill remaining channels) ──
                if (epgCandidates.isNotEmpty()) {
                    val epgCandidatesToTry = epgCandidates
                    var bestCoverage = epgCoverageRatio(channels, resolvedNowNext)
                    var bestNowNext = resolvedNowNext
                    epgCandidatesToTry.forEachIndexed { index, epgUrl ->
                        val pct = (90 + ((index * 8) / epgCandidatesToTry.size.coerceAtLeast(1))).coerceIn(90, 98)
                        onProgress(IptvLoadProgress("Loading full EPG (${index + 1}/${epgCandidatesToTry.size})...", pct))
                        val attempt = runCatching {
                            // 300 s: some providers (like TX-4K) serve a 100 MB
                            // XMLTV dump that needs 2-3 min on a TV's WiFi.
                            // 90 s was aborting before the file finished.
                            withTimeoutOrNull(300_000L) { fetchAndParseEpg(epgUrl, channels) }
                                ?: throw java.util.concurrent.TimeoutException("EPG download timed out for ${epgUrl.take(80)}")
                        }
                        if (attempt.isSuccess) {
                            val parsed = attempt.getOrDefault(emptyMap())
                            val parsedHasPrograms = hasAnyProgramData(parsed)
                            if (parsedHasPrograms || index == epgCandidatesToTry.lastIndex) {
                                // Merge: XMLTV as base, then overlay short EPG (fresher per-channel data)
                                val merged = ConcurrentHashMap(parsed)
                                shortEpgResult?.let { merged.putAll(it) } // Short EPG wins for channels it covers
                                val coverage = epgCoverageRatio(channels, merged)
                                if (coverage >= bestCoverage) {
                                    bestCoverage = coverage
                                    bestNowNext = merged
                                    preferredDerivedEpgUrl = epgUrl
                                    resolved = true
                                    System.err.println("[EPG] XMLTV candidate ${index + 1} coverage=${(coverage * 100).toInt()}% (best so far)")
                                }
                            }
                        } else {
                            epgFailureMessage = attempt.exceptionOrNull()?.message
                            System.err.println("[EPG] XMLTV attempt ${index + 1} failed: ${epgFailureMessage}")
                        }
                    }
                    if (resolved) {
                        resolvedNowNext = bestNowNext
                        cachedNowNext = ConcurrentHashMap(bestNowNext)
                        cachedEpgAt = System.currentTimeMillis()
                        epgUpdated = true
                        System.err.println("[EPG] Final best EPG coverage=${(bestCoverage * 100).toInt()}% for ${channels.size} channels")
                    }
                }

                val currentCoverage = epgCoverageRatio(channels, resolvedNowNext)
                if (
                    resolved &&
                    currentCoverage < completeEpgCoverageTarget &&
                    !shouldFetchBroadShortEpg &&
                    xtreamCreds != null &&
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
                            fetchXtreamShortEpg(xtreamCreds, missingXtreamChannels, onProgress)
                        }
                        if (shortEpgAttempt.isSuccess) {
                            val parsed = shortEpgAttempt.getOrNull()
                            if (parsed != null && hasAnyProgramData(parsed)) {
                                val merged = ConcurrentHashMap(resolvedNowNext)
                                merged.putAll(parsed)
                                resolvedNowNext = merged
                                cachedNowNext = ConcurrentHashMap(merged)
                                cachedEpgAt = System.currentTimeMillis()
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

                if (!resolved && !shouldFetchBroadShortEpg && xtreamCreds != null && hasXtreamChannels) {
                    System.err.println("[EPG] XMLTV did not resolve; falling back to broad Xtream short EPG")
                    val shortEpgAttempt = runCatching {
                        fetchXtreamShortEpg(xtreamCreds, channels, onProgress)
                    }
                    if (shortEpgAttempt.isSuccess) {
                        val parsed = shortEpgAttempt.getOrNull()
                        if (parsed != null && hasAnyProgramData(parsed)) {
                            cachedNowNext.putAll(parsed)
                            resolvedNowNext = cachedNowNext
                            cachedEpgAt = System.currentTimeMillis()
                            epgUpdated = true
                            resolved = true
                            System.err.println("[EPG] Broad Xtream fallback SUCCESS: ${parsed.size} fresh, ${cachedNowNext.size} total cached")
                        }
                    } else {
                        epgFailureMessage = shortEpgAttempt.exceptionOrNull()?.message
                        System.err.println("[EPG] Broad Xtream fallback failed: $epgFailureMessage")
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
                if (forcePlaylistReload || forceEpgReload || cachedFromDisk == null || epgUpdated) {
                    writeCache(
                        config = config,
                        channels = channels,
                        nowNext = nowNext,
                        loadedAtMs = System.currentTimeMillis()
                    )
                }
                onProgress(IptvLoadProgress("Loaded ${channels.size} channels", 100))
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

                val cached = readCache(config) ?: return@withLock
                cachedChannels = cached.channels
                cachedGroupedChannels = emptyMap()
                cachedNowNext = ConcurrentHashMap(cached.nowNext)
                cachedPlaylistAt = cached.loadedAtEpochMs
                cachedEpgAt = cached.loadedAtEpochMs
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
                    val cached = readCache(config) ?: return@withLock null
                    cachedChannels = cached.channels
                    cachedGroupedChannels = emptyMap()
                    cachedNowNext = ConcurrentHashMap(cached.nowNext)
                    cachedPlaylistAt = cached.loadedAtEpochMs
                    cachedEpgAt = cached.loadedAtEpochMs
                }

                val favoriteGroups = observeFavoriteGroups().first()
                val favoriteChannels = observeFavoriteChannels().first()
                val grouped = cachedGroupedChannels
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
        val grouped = cachedGroupedChannels
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
        if (cached.isEmpty()) return null
        val nowMs = System.currentTimeMillis()
        val recentCutoff = nowMs - (30L * 60_000L)

        val result = mutableMapOf<String, IptvNowNext>()
        for (channelId in channelIds) {
            val existing = cached[channelId] ?: continue
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

            for (i in 0 until allPrograms.size) {
                val p = allPrograms[i]
                when {
                    p.endUtcMillis <= nowMs && p.endUtcMillis > recentCutoff -> recent.add(p)
                    p.isLive(nowMs) -> now = p
                    p.startUtcMillis > nowMs && next == null -> next = p
                    p.startUtcMillis > nowMs && later == null -> later = p
                    p.startUtcMillis > nowMs && upcoming.size < epgUpcomingProgramLimit -> upcoming.add(p)
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

    /**
     * Refreshes short Xtream EPG data for the specified channel IDs.
     *
     * Updates the repository's in-memory `cachedNowNext` entries for channels that have Xtream stream identifiers.
     *
     * @return A map of channel ID to `IptvNowNext` containing the updated EPG entries for those channels, or `null` if Xtream credentials are not available or no EPG data was retrieved.
     */
    suspend fun refreshEpgForChannels(
        channelIds: Set<String>,
        maxChannels: Int = startupShortEpgChannelLimit
    ): Map<String, IptvNowNext>? {
        if (channelIds.isEmpty()) return null
        return withContext(Dispatchers.IO) {
            val config = observeConfig().first()
            val creds = resolveXtreamCredentials(config)
                ?: return@withContext null

            val requested = if (maxChannels > 0) {
                channelIds.take(maxChannels).toHashSet()
            } else {
                channelIds
            }
            val channels = cachedChannels.filter { it.id in requested }
            if (channels.isEmpty()) return@withContext null

            // Build lookups for these channels only
            val epgIdToChannelIds = mutableMapOf<String, MutableList<String>>()
            val streamIdToChannelIds = mutableMapOf<String, MutableList<String>>()
            for (ch in channels) {
                ch.epgId?.let { eid ->
                    epgIdToChannelIds.getOrPut(eid) { mutableListOf() }.add(ch.id)
                }
                resolveXtreamStreamId(ch)?.let { sid ->
                    streamIdToChannelIds.getOrPut(sid.toString()) { mutableListOf() }.add(ch.id)
                }
            }

            val xtreamChannels = channels.filter { resolveXtreamStreamId(it) != null }
            if (xtreamChannels.isEmpty()) return@withContext null

            System.err.println("[EPG-Refresh] Fetching short EPG for ${xtreamChannels.size} requested channels")

            val streamIds = xtreamChannels.mapNotNull { resolveXtreamStreamId(it) }
            var errors = 0
            val allListings = fetchXtreamEpgListingsAsync(
                creds = creds,
                streamIds = streamIds,
                timeoutMillis = xtreamShortEpgTimeout(streamIds.size)
            ) { _, hadError ->
                if (hadError) errors++
            }
            System.err.println("[EPG-Refresh] Done: ${allListings.size} listings, $errors errors")

            if (allListings.isEmpty()) return@withContext null

            val freshNowNext = buildNowNextFromXtreamListings(allListings, epgIdToChannelIds, streamIdToChannelIds)
            if (freshNowNext.isEmpty()) return@withContext null

            // Merge into cache (in-place, no copy)
            cachedNowNext.putAll(freshNowNext)
            cachedEpgAt = System.currentTimeMillis()
            if (cachedEpgAt - lastEpgCachePersistAt > 30_000L) {
                lastEpgCachePersistAt = cachedEpgAt
                persistCurrentCacheSnapshot(config, cachedEpgAt)
            }

            System.err.println("[EPG-Refresh] Updated ${freshNowNext.size} channels in cache")
            freshNowNext
        }
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
        cachedGroupedChannels = emptyMap()
        cachedNowNext = ConcurrentHashMap()
        cachedPlaylistAt = 0L
        cachedEpgAt = 0L
        discoveredM3uEpgUrl = null
        xtreamVodCacheKey = null
        xtreamVodLoadedAtMs = 0L
        xtreamSeriesLoadedAtMs = 0L
        cachedXtreamVodStreams = emptyList()
        cachedXtreamSeries = emptyList()
        cachedXtreamSeriesEpisodes = emptyMap()
        xtreamSeriesEpisodeInFlight = emptyMap()
        cacheOwnerProfileId = null
        cacheOwnerConfigSig = null
        // Keep disk VOD/series catalogs. They are credential-keyed and TTL checked;
        // deleting them during a generic refresh can race with playback source resolution.
    }

    private fun ensureCacheOwnership(profileId: String, config: IptvConfig) {
        val sig = buildConfigSignature(config)
        val ownerChanged = cacheOwnerProfileId != null && cacheOwnerProfileId != profileId
        val configChanged = cacheOwnerConfigSig != null && cacheOwnerConfigSig != sig
        if (ownerChanged || configChanged) {
            invalidateCache()
        }
        cacheOwnerProfileId = profileId
        cacheOwnerConfigSig = sig
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
                ?.filter { it.m3uUrl.isNotBlank() }
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
            gson.fromJson<List<String>>(raw, type)?.map { it.trim() }?.filter { it.isNotBlank() }?.distinct() ?: emptyList()
        }.getOrDefault(emptyList())
    }

    private fun decodeGroupOrder(prefs: Preferences): List<String> {
        val raw = prefs[groupOrderKey()].orEmpty()
        if (raw.isBlank()) return emptyList()
        return runCatching {
            val type = TypeToken.getParameterized(List::class.java, String::class.java).type
            gson.fromJson<List<String>>(raw, type)?.map { it.trim() }?.filter { it.isNotBlank() }?.distinct() ?: emptyList()
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
                    lastFocusedZone = session.lastFocusedZone.trim().ifBlank { "GUIDE" }
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
        return IptvCloudProfileState(
            m3uUrl = decryptConfigValue(prefs[m3uUrlKeyFor(safeProfileId)].orEmpty()),
            epgUrl = decryptConfigValue(prefs[epgUrlKeyFor(safeProfileId)].orEmpty()),
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
            playlists = if (playlistsRaw.isNotBlank()) {
                runCatching {
                    val type = TypeToken.getParameterized(List::class.java, IptvPlaylistEntry::class.java).type
                    gson.fromJson<List<IptvPlaylistEntry>>(playlistsRaw, type) ?: emptyList()
                }.getOrDefault(emptyList())
            } else emptyList(),
            tvSession = decodeTvSessionState(tvSessionRaw)
        )
    }

    suspend fun importCloudConfigForProfile(profileId: String, state: IptvCloudProfileState) {
        val safeProfileId = profileId.trim().ifBlank { "default" }
        val normalizedM3u = normalizeIptvInput(state.m3uUrl)
        val normalizedEpg = normalizeEpgInput(state.epgUrl)
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
            if (state.playlists.isNotEmpty()) {
                prefs[playlistsKeyFor(safeProfileId)] = gson.toJson(state.playlists)
            }
            if (state.tvSession != IptvTvSessionState()) {
                prefs[tvSessionKeyFor(safeProfileId)] = gson.toJson(
                    state.tvSession.copy(
                        lastChannelId = state.tvSession.lastChannelId.trim(),
                        lastGroupName = state.tvSession.lastGroupName.trim(),
                        lastFocusedZone = state.tvSession.lastFocusedZone.trim().ifBlank { "GUIDE" }
                    )
                )
            } else {
                prefs.remove(tvSessionKeyFor(safeProfileId))
            }
        }
        if (profileManager.getProfileIdSync() == safeProfileId) {
            invalidateCache()
        }
    }

    private suspend fun fetchChannelsForPlaylistWithRetries(
        playlist: IptvPlaylistEntry,
        onProgress: (IptvLoadProgress) -> Unit
    ): List<IptvChannel> {
        resolveXtreamCredentials(playlist)?.let { creds ->
            onProgress(IptvLoadProgress("Detected Xtream provider. Loading live channels...", 6))
            runCatching {
                withTimeoutOrNull(120_000L) {
                    fetchXtreamLiveChannels(creds, onProgress)
                } ?: throw IllegalStateException("Xtream provider timed out while loading live channels.")
            }
                .onSuccess { channels ->
                    if (channels.isNotEmpty()) {
                        onProgress(IptvLoadProgress("Loaded ${channels.size} live channels from provider API", 95))
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
        resolveXtreamCredentials(url)?.let { creds ->
            onProgress(IptvLoadProgress("Detected Xtream provider. Loading live channels...", 6))
            runCatching {
                withTimeoutOrNull(120_000L) {
                    fetchXtreamLiveChannels(creds, onProgress)
                } ?: throw IllegalStateException("Xtream provider timed out while loading live channels.")
            }
                .onSuccess { channels ->
                    if (channels.isNotEmpty()) {
                        onProgress(IptvLoadProgress("Loaded ${channels.size} live channels from provider API", 95))
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
                    fetchAndParseM3uOnce(url, onProgress)
                } ?: throw IllegalStateException("Playlist loading timed out. Try refreshing or using the provider's Xtream credentials.")
            }.onSuccess { channels ->
                if (channels.isNotEmpty()) return channels
                lastError = IllegalStateException("Playlist loaded but contains no channels.")
            }.onFailure { error ->
                lastError = error
            }

            if (attempt < maxAttempts - 1) {
                val backoffMs = (1_000L * (attempt + 1)).coerceAtMost(2_000L)
                onProgress(IptvLoadProgress("Retrying in ${backoffMs / 1000}s...", 5))
                delay(backoffMs)
            }
        }
        throw (lastError ?: IllegalStateException("Failed to load M3U playlist."))
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
        @SerializedName("category_id") val categoryId: String? = null
    )

    private data class XtreamVodStream(
        @SerializedName("stream_id") val streamId: Int? = null,
        val name: String? = null,
        val year: String? = null,
        @SerializedName("container_extension") val containerExtension: String? = null,
        @SerializedName(value = "imdb", alternate = ["imdb_id", "imdbid"]) val imdb: String? = null,
        @SerializedName(value = "tmdb", alternate = ["tmdb_id", "tmdbid"]) val tmdb: String? = null
    )

    private data class XtreamSeriesItem(
        @SerializedName(value = "series_id", alternate = ["seriesid", "id"]) val seriesId: Int? = null,
        val name: String? = null,
        @SerializedName(value = "imdb", alternate = ["imdb_id", "imdbid"]) val imdb: String? = null,
        @SerializedName(value = "tmdb", alternate = ["tmdb_id", "tmdbid"]) val tmdb: String? = null
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
        val items: Map<String, Int> = emptyMap()
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
        private val seriesBindingMemory = object : LinkedHashMap<String, Int>(2048, 0.75f, true) {
            override fun removeEldestEntry(eldest: MutableMap.MutableEntry<String, Int>?): Boolean {
                return size > 2048
            }
        }
        private val seriesBindingLock = Any()
        private val seriesInfoMemory = object : LinkedHashMap<String, List<XtreamSeriesEpisode>>(50, 0.75f, true) {
            override fun removeEldestEntry(eldest: MutableMap.MutableEntry<String, List<XtreamSeriesEpisode>>?): Boolean {
                return size > 50
            }
        }
        private val seriesInfoLock = Any()

        private fun catalogPrefKey(providerKey: String): String = "catalog_${providerKey.hashCode()}"
        private val resolvedPrefKey = "resolved_episode_map"
        private val seriesBindingPrefKey = "series_binding_map"
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
            readSeriesBinding(bindingKeys)?.let { boundSeriesId ->
                System.err.println("[VOD-Resolver] Found series binding: seriesId=$boundSeriesId, loading info...")
                val bindStart = System.currentTimeMillis()
                val boundEpisodes = loadSeriesInfo(
                    providerKey = providerKey,
                    creds = creds,
                    seriesId = boundSeriesId,
                    allowNetwork = allowNetwork
                )
                System.err.println("[VOD-Resolver] loadSeriesInfo for binding took ${System.currentTimeMillis() - bindStart}ms, got ${boundEpisodes.size} episodes")
                val boundHits = matchEpisodes(boundEpisodes, season, episode)
                if (boundHits.isNotEmpty()) {
                    val resolved = boundHits
                        .sortedWith(
                            compareByDescending<ResolverEpisodeHit> { it.score + vodQualityRank(it.episode.title) }
                                .thenBy { it.episode.id }
                        )
                        .map { hit ->
                            ResolverCachedResolvedEpisode(
                                streamId = hit.episode.id,
                                containerExtension = hit.episode.containerExtension,
                                seriesId = boundSeriesId,
                                confidence = 0.995f,
                                method = "series_binding",
                                title = hit.episode.title,
                                savedAtMs = System.currentTimeMillis()
                            )
                        }
                        .distinctBy { it.streamId }
                    val best = resolved.first()
                    writeResolved(cacheKey, best)
                    writeSeriesBinding(bindingKeys, boundSeriesId)
                    System.err.println("[VOD-Resolver] Resolved ${resolved.size} variants via binding in ${System.currentTimeMillis() - resolveStart}ms")
                    if (!allowNetwork) {
                        return resolved
                    }
                    bindingResolved = resolved
                } else {
                    System.err.println("[VOD-Resolver] Binding didn't match S${season}E${episode}")
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
            writeSeriesBinding(bindingKeys, best.seriesId)
            System.err.println("[VOD-Resolver] Resolved ${resolved.size} variants via ${best.method} (conf=${best.confidence}) in ${System.currentTimeMillis() - resolveStart}ms")
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

        private fun readSeriesBinding(keys: List<String>): Int? {
            if (keys.isEmpty()) return null
            synchronized(seriesBindingLock) {
                keys.forEach { key ->
                    seriesBindingMemory[key]?.let { return it }
                }
            }
            val raw = prefs.getString(seriesBindingPrefKey, null) ?: return null
            val persisted = runCatching { gson.fromJson(raw, ResolverPersistedSeriesBindings::class.java) }.getOrNull() ?: return null
            keys.forEach { key ->
                val seriesId = persisted.items[key] ?: return@forEach
                synchronized(seriesBindingLock) {
                    seriesBindingMemory[key] = seriesId
                }
                return seriesId
            }
            return null
        }

        private fun writeSeriesBinding(keys: List<String>, seriesId: Int) {
            if (keys.isEmpty()) return
            synchronized(seriesBindingLock) {
                keys.forEach { key -> seriesBindingMemory[key] = seriesId }
            }
            val existingRaw = prefs.getString(seriesBindingPrefKey, null)
            val existing = runCatching { gson.fromJson(existingRaw, ResolverPersistedSeriesBindings::class.java) }.getOrNull()
                ?: ResolverPersistedSeriesBindings()
            val merged = LinkedHashMap(existing.items)
            keys.forEach { key -> merged[key] = seriesId }
            while (merged.size > 2048) {
                val oldestKey = merged.keys.firstOrNull() ?: break
                merged.remove(oldestKey)
            }
            runCatching {
                prefs.edit().putString(seriesBindingPrefKey, gson.toJson(ResolverPersistedSeriesBindings(merged))).apply()
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
            val vod = getXtreamVodStreams(creds, allowNetwork, fast = true)
            if (vod.isEmpty()) return@withContext emptyList()

            val normalizedTmdb = normalizeTmdbId(tmdbId)
            if (!normalizedTmdb.isNullOrBlank()) {
                val tmdbMatches = vod.filter { normalizeTmdbId(it.tmdb) == normalizedTmdb }
                if (tmdbMatches.isNotEmpty()) {
                    return@withContext sortVodSources(
                        tmdbMatches.mapNotNull { it.toMovieVodSource(creds, title.ifBlank { normalizedTmdb }) }
                    )
                }
            }

            val normalizedImdb = normalizeImdbId(imdbId)
            if (!normalizedImdb.isNullOrBlank()) {
                val imdbMatches = vod.filter { normalizeImdbId(it.imdb) == normalizedImdb }
                if (imdbMatches.isNotEmpty()) {
                    return@withContext sortVodSources(
                        imdbMatches.mapNotNull { it.toMovieVodSource(creds, title.ifBlank { normalizedImdb }) }
                    )
                }
            }

            val normalizedTitle = normalizeLookupText(title)
            if (normalizedTitle.isBlank()) return@withContext emptyList()
            val inputYear = year ?: parseYear(title)

            val scored = vod
                .asSequence()
                .mapNotNull { item ->
                    val name = item.name?.trim().orEmpty()
                    if (name.isBlank()) return@mapNotNull null
                    val score = scoreNameMatch(name, normalizedTitle)
                    if (score <= 0) return@mapNotNull null
                    val providerYear = parseYear(item.year ?: name)
                    val yearDelta = if (inputYear != null && providerYear != null) kotlin.math.abs(providerYear - inputYear) else null
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
            val bestScore = scored.firstOrNull()?.second ?: return@withContext emptyList()
            val minScore = maxOf(65, bestScore - 8)
            sortVodSources(
                scored
                    .asSequence()
                    .takeWhile { it.second >= minScore }
                    .mapNotNull { it.first.toMovieVodSource(creds, title) }
                    .toList()
            )
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
            val activeProfileId = runCatching { profileManager.activeProfileId.first() }.getOrDefault("default")
            val providerKey = "$activeProfileId|${xtreamCacheKey(creds)}"

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

            fun List<ResolverCachedResolvedEpisode>.toSeriesVodSources(): List<StreamSource> {
                return map { resolved ->
                    val resolvedTitle = resolved.title?.trim().orEmpty()
                    val ext = resolved.containerExtension?.trim()?.ifBlank { null } ?: "mp4"
                    val streamUrl = "${creds.baseUrl}/series/${creds.username}/${creds.password}/${resolved.streamId}.$ext"
                    StreamSource(
                        source = resolvedTitle.ifBlank { "$title S${season}E${episode}" },
                        addonName = "IPTV Series VOD",
                        addonId = "iptv_xtream_vod",
                        quality = inferQuality(resolvedTitle.ifBlank { "$title S${season}E${episode}" }),
                        size = "",
                        url = streamUrl
                    )
                }
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

    private fun XtreamVodStream.toMovieVodSource(creds: XtreamCredentials, fallbackTitle: String): StreamSource? {
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

    private fun XtreamVodStream.toEpisodeVodSource(creds: XtreamCredentials, fallbackTitle: String): StreamSource? {
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
                val activeProfileId = runCatching { profileManager.activeProfileId.first() }.getOrDefault("default")
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
            val activeProfileId = runCatching { profileManager.activeProfileId.first() }.getOrDefault("default")
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
            val activeProfileId = runCatching { profileManager.activeProfileId.first() }.getOrDefault("default")
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
                return@withContext cachedXtreamVodStreams
            }

            // 2. Check disk cache (fast — reading a file, not a network call)
            val diskFile = vodDiskCacheFile(creds)
            val diskCache: XtreamDiskCache<XtreamVodStream>? = readDiskCache(diskFile, vodDiskCacheType)
            if (diskCache != null && diskCache.items.isNotEmpty() && now - diskCache.savedAtMs < xtreamVodCacheMs) {
                System.err.println("[VOD-Cache] Loaded ${diskCache.items.size} VOD streams from disk cache (age ${(now - diskCache.savedAtMs) / 1000}s)")
                cachedXtreamVodStreams = diskCache.items
                xtreamVodLoadedAtMs = diskCache.savedAtMs
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
                // 5. Persist to disk in background
                runCatching { writeDiskCache(diskFile, writeTime, vod) }
                System.err.println("[VOD-Cache] Saved VOD streams to disk cache")
            } else if (diskCache != null && diskCache.items.isNotEmpty()) {
                // Network returned empty — use stale disk cache
                System.err.println("[VOD-Cache] Network returned empty, using stale disk cache (${diskCache.items.size} items)")
                cachedXtreamVodStreams = diskCache.items
                xtreamVodLoadedAtMs = diskCache.savedAtMs
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
        val infoObj = obj.getAsJsonObject("info")
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
        val infoObj = item.getAsJsonObject("info")
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
        val parsed = url.toHttpUrlOrNull() ?: return null
        val username = parsed.queryParameter("username")?.trim()?.ifBlank { null }
            ?: parsed.queryParameter("user")?.trim()?.ifBlank { null }
            ?: parsed.queryParameter("uname")?.trim()?.ifBlank { null }
            ?: ""
        val password = parsed.queryParameter("password")?.trim()?.ifBlank { null }
            ?: parsed.queryParameter("pass")?.trim()?.ifBlank { null }
            ?: parsed.queryParameter("pwd")?.trim()?.ifBlank { null }
            ?: ""
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
        return resolveXtreamCredentials(playlist.epgUrl)
            ?: resolveXtreamCredentials(playlist.m3uUrl)
    }

    private fun resolveEpgCandidates(config: IptvConfig): List<String> {
        val allLists = activePlaylists(config)
        return buildList {
            allLists.forEach { list ->
                list.epgUrl.takeIf { it.isNotBlank() }?.let { add(it) }
                val creds = resolveXtreamCredentials(list.epgUrl).let { fromEpg ->
                    fromEpg ?: resolveXtreamCredentials(list.m3uUrl)
                }
                if (creds != null) {
                    preferredDerivedEpgUrl?.takeIf { it.startsWith(creds.baseUrl) }?.let { add(it) }
                    add("${creds.baseUrl}/xmltv.php?username=${creds.username}&password=${creds.password}")
                    add("${creds.baseUrl}/get.php?username=${creds.username}&password=${creds.password}&type=xmltv")
                    add("${creds.baseUrl}/get.php?username=${creds.username}&password=${creds.password}&type=xml")
                    add("${creds.baseUrl}/xmltv.php")
                    add("${creds.baseUrl}/get.php?username=${creds.username}&password=${creds.password}")
                }
            }
            discoveredM3uEpgUrl?.let { add(it) }
        }.distinct()
    }

    private fun resolveXtreamCredentials(config: IptvConfig): XtreamCredentials? {
        activePlaylists(config).forEach { playlist ->
            resolveXtreamCredentials(playlist.epgUrl)?.let { return it }
            resolveXtreamCredentials(playlist.m3uUrl)?.let { return it }
        }
        resolveXtreamCredentials(config.epgUrl)?.let { return it }
        resolveXtreamCredentials(config.m3uUrl)?.let { return it }
        return null
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

        onProgress(IptvLoadProgress("Loading live streams...", 35))
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
                xtreamStreamId = streamId
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
            .header("User-Agent", "VLC/3.0.20 LibVLC/3.0.20")
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
                    val body = it.body?.string()
                    if (body.isNullOrBlank()) {
                        continuation.resume(null)
                        return
                    }
                    val result = runCatching { gson.fromJson<T>(body, type) }.getOrNull()
                    continuation.resume(result)
                }
            }
        })
    }

    private fun fetchAndParseM3uOnce(
        url: String,
        onProgress: (IptvLoadProgress) -> Unit
    ): List<IptvChannel> {
        val request = Request.Builder()
            .url(url)
            .header("User-Agent", "VLC/3.0.20 LibVLC/3.0.20")
            .header("Accept", "*/*")
            .get()
            .build()
        iptvHttpClient.newCall(request).execute().use { response ->
            val raw = response.body?.byteStream() ?: throw IllegalStateException("M3U response was empty.")
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
                throw IllegalStateException("M3U request failed (HTTP ${response.code}). $detail")
            }
            onProgress(IptvLoadProgress("Parsing channels...", 78))
            return parseM3u(stream, onProgress)
        }
    }

    private fun fetchAndParseEpg(url: String, channels: List<IptvChannel>): Map<String, IptvNowNext> {
        fun epgRequest(targetUrl: String, userAgent: String): Request {
            return Request.Builder()
                .url(targetUrl)
                .header("User-Agent", userAgent)
                .header("Accept", "*/*")
                .header("Accept-Language", "en-US,en;q=0.9")
                .header("Cache-Control", "no-cache")
                .get()
                .build()
        }

        var response = iptvHttpClient.newCall(epgRequest(url, "VLC/3.0.20 LibVLC/3.0.20")).execute()
        if (!response.isSuccessful && response.code in setOf(511, 403, 401)) {
            response.close()
            response = iptvHttpClient.newCall(
                epgRequest(
                    url,
                    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 " +
                        "(KHTML, like Gecko) Chrome/124.0.0.0 Safari/537.36"
                )
            ).execute()
        }
        response.use { safeResponse ->
            val stream = safeResponse.body?.byteStream() ?: throw IllegalStateException("Empty EPG response")
            val prepared = BufferedInputStream(prepareInputStream(stream, url))
            if (!safeResponse.isSuccessful && !looksLikeXmlTv(prepared)) {
                val preview = safeResponse.peekBody(220).string().replace('\n', ' ').trim()
                val detail = if (preview.isBlank()) "No response body." else preview
                throw IllegalStateException("EPG request failed (HTTP ${safeResponse.code}). $detail")
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
                        epgRequest(url, "VLC/3.0.20 LibVLC/3.0.20")
                    ).execute()
                    retryResponse.use { rr ->
                        val retryStream = rr.body?.byteStream()
                            ?: throw IllegalStateException("Empty EPG retry response")
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
        @SerializedName("stream_id") val streamId: String? = null
    )

    private data class XtreamEpgResponse(
        @SerializedName("epg_listings") val epgListings: List<XtreamEpgListing>? = null
    )

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
        val path = ch.streamUrl.substringBefore('?').substringBefore('#')
        if (path.contains("/live/", ignoreCase = true)) {
            return path.substringAfterLast('/')
                .substringBefore('.')
                .toIntOrNull()
        }
        return null
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
        onProgress: (IptvLoadProgress) -> Unit
    ): Map<String, IptvNowNext>? {
        if (channels.isEmpty()) return null

        // Build lookups: epgId -> channelIds, streamId -> channelIds
        val epgIdToChannelIds = mutableMapOf<String, MutableList<String>>()
        val streamIdToChannelIds = mutableMapOf<String, MutableList<String>>()
        for (ch in channels) {
            ch.epgId?.let { eid ->
                epgIdToChannelIds.getOrPut(eid) { mutableListOf() }.add(ch.id)
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
        System.err.println("[EPG] Xtream short EPG: fetching ${toFetch.size}/${xtreamChannels.size} channels")
        if (toFetch.isEmpty()) return null

        var errors = 0
        var fetched = 0
        val total = toFetch.size
        val streamIds = toFetch.mapNotNull { resolveXtreamStreamId(it) }

        val allListings = fetchXtreamEpgListingsAsync(
            creds = creds,
            streamIds = streamIds,
            timeoutMillis = xtreamShortEpgTimeout(streamIds.size)
        ) { _, hadError ->
            fetched++
            if (hadError) errors++
            if (fetched % 50 == 0) {
                val pct = (90 + ((fetched.toLong() * 8L) / total.toLong())).toInt().coerceIn(90, 98)
                onProgress(IptvLoadProgress("Loading EPG... $fetched/$total channels", pct))
            }
        }
        System.err.println("[EPG] Xtream short EPG done: ${allListings.size} listings, $fetched fetched, $errors errors")

        if (errors > fetched / 2 && fetched > 20) {
            return null
        }
        if (allListings.isEmpty()) return null

        onProgress(IptvLoadProgress("Parsing EPG data (${allListings.size} listings)...", 98))
        return buildNowNextFromXtreamListings(allListings, epgIdToChannelIds, streamIdToChannelIds)
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
        val completed = withTimeoutOrNull(timeoutMillis) {
            withContext(Dispatchers.IO.limitedParallelism(32)) {
                val sampleLogged = AtomicBoolean(false)
                distinctStreamIds.chunked(xtreamShortEpgBatchSize).forEach { batch ->
                    batch.map { sid ->
                        async {
                            gate.withPermit {
                                var hadError = false
                                val url = "${creds.baseUrl}/player_api.php?username=${creds.username}" +
                                    "&password=${creds.password}&action=get_short_epg&stream_id=$sid&limit=$xtreamShortEpgLimit"
                                var listings: List<XtreamEpgListing>? = null
                                try {
                                    var resp: XtreamEpgResponse? = requestJson(url, XtreamEpgResponse::class.java)
                                    listings = resp?.epgListings
                                    if (listings.isNullOrEmpty()) {
                                        val fallbackUrl = "${creds.baseUrl}/player_api.php?username=${creds.username}" +
                                            "&password=${creds.password}&action=get_short_epg&stream_id=$sid"
                                        resp = requestJson(fallbackUrl, XtreamEpgResponse::class.java)
                                        listings = resp?.epgListings
                                    }
                                    if (!listings.isNullOrEmpty()) {
                                        listingsResult.addAll(listings)
                                        if (sampleLogged.compareAndSet(false, true)) {
                                            val sample = listings.first()
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
        return listingsResult.toList()
    }

    private fun xtreamShortEpgTimeout(streamCount: Int): Long =
        when {
            streamCount > 25_000 -> 1_200_000L
            streamCount > 10_000 -> 900_000L
            streamCount > 4_000 -> 420_000L
            else -> 180_000L
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
        listings: List<XtreamEpgListing>,
        epgIdToChannelIds: Map<String, List<String>>,
        streamIdToChannelIds: Map<String, List<String>>
    ): Map<String, IptvNowNext> {
        val nowMs = System.currentTimeMillis()
        val recentCutoff = nowMs - (30L * 60_000L) // 30 min ago (covers expanded timeline window)

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

            // Skip programs that ended well before now (keep recent ones)
            if (stopMs < recentCutoff) continue

            val title = decodeBase64Field(listing.title).ifBlank { "No Title" }
            val description = decodeBase64Field(listing.description).takeIf { it.isNotBlank() }

            val program = IptvProgram(
                title = title,
                description = description,
                startUtcMillis = startMs,
                endUtcMillis = stopMs
            )

            // Resolve which IptvChannel IDs this listing maps to
            val resolvedChannelIds = mutableSetOf<String>()

            // Match by epg_id / channel_id field
            listing.channelId?.let { cid ->
                epgIdToChannelIds[cid]?.let { resolvedChannelIds.addAll(it) }
            }
            listing.epgId?.let { eid ->
                // epg_id can be the stream_id in some providers
                streamIdToChannelIds[eid]?.let { resolvedChannelIds.addAll(it) }
                epgIdToChannelIds[eid]?.let { resolvedChannelIds.addAll(it) }
            }
            // Match by stream_id
            listing.streamId?.let { sid ->
                streamIdToChannelIds[sid]?.let { resolvedChannelIds.addAll(it) }
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

            for (p in sorted) {
                when {
                    p.endUtcMillis <= nowMs && p.endUtcMillis > recentCutoff -> recent.add(p)
                    p.isLive(nowMs) -> now = p
                    p.startUtcMillis > nowMs && next == null -> next = p
                    p.startUtcMillis > nowMs && later == null -> later = p
                    p.startUtcMillis > nowMs -> upcoming.add(p)
                }
            }

            result[channelId] = IptvNowNext(
                now = now,
                next = next,
                later = later,
                upcoming = upcoming.take(epgUpcomingProgramLimit),
                recent = recent
            )
        }

        val withNow = result.values.count { it.now != null }
        val withNext = result.values.count { it.next != null }
        System.err.println("[EPG] buildNowNext result: ${result.size} channels, $withNow with NOW, $withNext with NEXT")
        return result
    }

    /**
     * Parse Xtream datetime strings like "2024-01-01 12:00:00" to epoch millis.
     */
    private fun parseXtreamDateTime(dateStr: String?): Long? {
        if (dateStr.isNullOrBlank()) return null
        return try {
            val formatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            val local = java.time.LocalDateTime.parse(dateStr, formatter)
            // Xtream times are typically UTC
            local.atZone(java.time.ZoneOffset.UTC).toInstant().toEpochMilli()
        } catch (_: Exception) {
            null
        }
    }

    /**
     * Some providers return malformed XML text that includes JSON-style backslash escapes
     * (for example: \" or \n) inside element values. KXmlParser can fail hard on this.
     * This filter normalizes the most common escapes into plain text so XML parsing can continue.
     */
    private class BackslashEscapeSanitizingInputStream(
        input: InputStream
    ) : FilterInputStream(input) {
        override fun read(): Int {
            val current = super.read()
            if (current == -1) return -1

            val mapped = if (current == '\\'.code) {
                val next = super.read()
                if (next == -1) {
                    current
                } else {
                    when (next.toChar()) {
                        '\\' -> '\\'.code
                        '"' -> '"'.code
                        '\'' -> '\''.code
                        '/' -> '/'.code
                        'n' -> '\n'.code
                        'r' -> '\r'.code
                        't' -> '\t'.code
                        'b' -> '\b'.code
                        'f' -> 0x0C
                        else -> {
                            // Unknown escape (for example \y): drop the slash and keep the char.
                            next
                        }
                    }
                }
            } else {
                current
            }

            // XML 1.0 forbids most control chars; normalize them to space.
            if (mapped in 0x00..0x1F && mapped != '\n'.code && mapped != '\r'.code && mapped != '\t'.code) {
                return ' '.code
            }
            return mapped
        }
    }

    private fun parseM3u(
        input: InputStream,
        onProgress: (IptvLoadProgress) -> Unit
    ): List<IptvChannel> {
        val channels = mutableListOf<IptvChannel>()
        val seenChannelIds = HashSet<String>()
        var pendingMetadata: String? = null
        var parsedCount = 0

        BufferedReader(InputStreamReader(input, StandardCharsets.UTF_8), 256 * 1024).use { reader ->
            while (true) {
                val rawLine = reader.readLine() ?: break
                val line = rawLine.trim()
                if (line.isEmpty()) continue

                if (line.startsWith("#EXTM3U", ignoreCase = true)) {
                    val tvgUrl = extractAttr(line, "url-tvg")
                        ?: extractAttr(line, "x-tvg-url")
                    if (!tvgUrl.isNullOrBlank() && tvgUrl.startsWith("http", ignoreCase = true)) {
                        discoveredM3uEpgUrl = tvgUrl
                    }
                    continue
                }

                if (line.startsWith("#EXTINF", ignoreCase = true)) {
                    pendingMetadata = line
                    continue
                }

                if (line.startsWith("#")) continue

                val metadata = pendingMetadata
                pendingMetadata = null

                val epgId = extractAttr(metadata, "tvg-id")
                val id = buildChannelId(line, epgId)
                if (!seenChannelIds.add(id)) continue

                val channelName = extractChannelName(metadata)
                val groupTitle = extractAttr(metadata, "group-title")?.takeIf { it.isNotBlank() } ?: "Uncategorized"
                val logo = extractAttr(metadata, "tvg-logo")

                channels += IptvChannel(
                    id = id,
                    name = channelName,
                    streamUrl = line,
                    group = groupTitle,
                    logo = logo,
                    epgId = epgId,
                    rawTitle = metadata ?: channelName
                )
                parsedCount++
                if (parsedCount % 5000 == 0) {
                    onProgress(IptvLoadProgress("Parsing channels... $parsedCount found", 85))
                }
            }
        }

        onProgress(IptvLoadProgress("Finalizing ${channels.size} channels...", 95))
        return channels
    }

    private fun parseXmlTvNowNext(
        input: InputStream,
        channels: List<IptvChannel>
    ): Map<String, IptvNowNext> {
        if (channels.isEmpty()) return emptyMap()

        val nowUtc = System.currentTimeMillis()
        val recentCutoff = nowUtc - (30 * 60_000L)  // Keep programs that ended within past 30 min
        val keyLookup = buildChannelKeyLookup(channels)
        val xmlChannelNameMap = mutableMapOf<String, MutableSet<String>>()
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
                                    xmlChannelNameMap.getOrPut(xmlId) { mutableSetOf() }.add(display)
                                }
                            }
                        }
                        "programme" -> {
                            val rawKey = normalizeChannelKey(parser.getAttributeValue(null, "channel") ?: "")
                            val stop = parseXmlTvDate(parser.getAttributeValue(null, "stop"))
                            // Skip programmes that ended before the recent cutoff
                            if (stop > 0L && stop <= recentCutoff) {
                                currentChannelKey = null
                            } else {
                                currentChannelKey = rawKey
                                currentStart = parseXmlTvDate(parser.getAttributeValue(null, "start"))
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
                        val channel = key?.let { resolveXmlTvChannel(it, xmlChannelNameMap, keyLookup) }
                        if (channel != null && currentStop > currentStart) {
                            val program = IptvProgram(
                                title = currentTitle ?: "Unknown program",
                                description = currentDesc,
                                startUtcMillis = currentStart,
                                endUtcMillis = currentStop
                            )

                            val nowProgram = pickNow(nowCandidates[channel.id], program, nowUtc)
                            nowCandidates[channel.id] = nowProgram
                            if (program.startUtcMillis > nowUtc) {
                                val future = upcomingCandidates.getOrPut(channel.id) { mutableListOf() }
                                addUpcomingCandidate(future, program, limit = epgUpcomingProgramLimit)
                            } else if (program.endUtcMillis <= nowUtc && program.endUtcMillis > recentCutoff) {
                                val recent = recentCandidates.getOrPut(channel.id) { mutableListOf() }
                                if (recent.size < epgRecentProgramLimit) recent.add(program)
                            }
                        }
                        currentChannelKey = null
                    }
                    }
                }
            }
            eventType = parser.next()
        }

        return channels.associate { channel ->
            val future = upcomingCandidates[channel.id].orEmpty()
            val recent = recentCandidates[channel.id].orEmpty().sortedBy { it.startUtcMillis }
            channel.id to IptvNowNext(
                now = nowCandidates[channel.id],
                next = future.getOrNull(0),
                later = future.getOrNull(1),
                upcoming = future,
                recent = recent
            )
        }
    }

    private fun parseXmlTvNowNextWithSax(
        input: InputStream,
        channels: List<IptvChannel>
    ): Map<String, IptvNowNext> {
        if (channels.isEmpty()) return emptyMap()

        val keyLookup = buildChannelKeyLookup(channels)
        val xmlChannelNameMap = mutableMapOf<String, MutableSet<String>>()
        val nowCandidates = mutableMapOf<String, IptvProgram?>()
        val upcomingCandidates = mutableMapOf<String, MutableList<IptvProgram>>()
        val recentCandidates = mutableMapOf<String, MutableList<IptvProgram>>()
        val nowUtc = System.currentTimeMillis()
        val recentCutoff = nowUtc - (30 * 60_000L)  // Keep programs that ended within past 30 min

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
                        currentChannelKey = normalizeChannelKey(attributes?.getValue("channel").orEmpty())
                        currentStart = parseXmlTvDate(attributes?.getValue("start"))
                        currentStop = parseXmlTvDate(attributes?.getValue("stop"))
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
                                    xmlChannelNameMap.getOrPut(xmlId) { mutableSetOf() }.add(display)
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
                        val channel = key?.let { resolveXmlTvChannel(it, xmlChannelNameMap, keyLookup) }
                        if (channel != null && currentStop > currentStart) {
                            val program = IptvProgram(
                                title = currentTitle ?: "Unknown program",
                                description = currentDesc,
                                startUtcMillis = currentStart,
                                endUtcMillis = currentStop
                            )
                            val nowProgram = pickNow(nowCandidates[channel.id], program, nowUtc)
                            nowCandidates[channel.id] = nowProgram
                            if (program.startUtcMillis > nowUtc) {
                                val future = upcomingCandidates.getOrPut(channel.id) { mutableListOf() }
                                addUpcomingCandidate(future, program, limit = epgUpcomingProgramLimit)
                            } else if (program.endUtcMillis <= nowUtc && program.endUtcMillis > recentCutoff) {
                                // Recently ended program – keep for the past-window in the EPG guide
                                val recent = recentCandidates.getOrPut(channel.id) { mutableListOf() }
                                if (recent.size < epgRecentProgramLimit) recent.add(program)
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

        val result = channels.associate { channel ->
            val future = upcomingCandidates[channel.id].orEmpty()
            val recent = recentCandidates[channel.id].orEmpty().sortedBy { it.startUtcMillis }
            channel.id to IptvNowNext(
                now = nowCandidates[channel.id],
                next = future.getOrNull(0),
                later = future.getOrNull(1),
                upcoming = future,
                recent = recent
            )
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

    private fun hasAnyProgramData(nowNext: Map<String, IptvNowNext>): Boolean {
        if (nowNext.isEmpty()) return false
        return nowNext.values.any { item -> hasProgramData(item) }
    }

    private fun hasProgramData(item: IptvNowNext?): Boolean {
        return item != null && (item.now != null || item.next != null || item.later != null || item.upcoming.isNotEmpty())
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

    private fun normalizeChannelKey(value: String): String = value.trim().lowercase(Locale.US)

    private fun normalizeLooseKey(value: String): String {
        return normalizeChannelKey(value).replace(NON_ALPHA_NUM_REGEX_INLINE, "")
    }

    private fun buildChannelKeyLookup(channels: List<IptvChannel>): Map<String, IptvChannel> {
        val map = LinkedHashMap<String, IptvChannel>(channels.size * 4)
        channels.forEach { channel ->
            val candidates = mutableSetOf<String>()
            candidates += normalizeChannelKey(channel.name)
            candidates += normalizeLooseKey(channel.name)
            candidates += normalizeLooseKey(stripQualitySuffixes(channel.name))

            channel.epgId?.takeIf { it.isNotBlank() }?.let { epgId ->
                candidates += normalizeChannelKey(epgId)
                candidates += normalizeLooseKey(epgId)
            }

            extractAttr(channel.rawTitle, "tvg-name")?.takeIf { it.isNotBlank() }?.let { tvgName ->
                candidates += normalizeChannelKey(tvgName)
                candidates += normalizeLooseKey(tvgName)
                candidates += normalizeLooseKey(stripQualitySuffixes(tvgName))
            }

            candidates.filter { it.isNotBlank() }.forEach { key ->
                map.putIfAbsent(key, channel)
            }
        }
        return map
    }

    private fun resolveXmlTvChannel(
        xmlChannelKey: String,
        xmlChannelNameMap: Map<String, Set<String>>,
        keyLookup: Map<String, IptvChannel>
    ): IptvChannel? {
        val normalized = normalizeChannelKey(xmlChannelKey)
        val normalizedLoose = normalizeLooseKey(xmlChannelKey)

        keyLookup[normalized]?.let { return it }
        keyLookup[normalizedLoose]?.let { return it }
        keyLookup[normalizeLooseKey(stripQualitySuffixes(xmlChannelKey))]?.let { return it }

        val names = xmlChannelNameMap[normalized].orEmpty()
        names.forEach { display ->
            keyLookup[display]?.let { return it }
            keyLookup[normalizeLooseKey(display)]?.let { return it }
            keyLookup[normalizeLooseKey(stripQualitySuffixes(display))]?.let { return it }
        }
        return null
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
                playlist.enabled.toString()
            ).joinToString("|")
        }
        val raw = listOf(
            "playlist-group-prefix-v2",
            config.m3uUrl.trim(),
            config.epgUrl.trim(),
            config.stalkerPortalUrl.trim(),
            config.stalkerMacAddress.trim(),
            playlistSignature
        ).joinToString("||")
        return digest.digest(raw.toByteArray(StandardCharsets.UTF_8))
            .joinToString("") { "%02x".format(it) }
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
            val compactChannels = channels.map { channel ->
                // Keep cache lean: strip raw EXTINF metadata but preserve logos for cold-start display.
                channel.copy(
                    rawTitle = channel.name
                )
            }
            val compactNowNext = nowNext
                .asSequence()
                .filter { (_, value) -> hasProgramData(value) }
                .associate { (channelId, value) ->
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
                            .takeLast(cacheRecentProgramLimit)
                            .map { it.compactForCache() }
                    )
                }
            val payload = IptvCachePayload(
                channels = compactChannels,
                nowNext = compactNowNext,
                loadedAtEpochMs = loadedAtMs,
                configSignature = buildConfigSignature(config)
            )
            val compressed = gzipBytes(gson.toJson(payload))
            if (compressed.size <= MAX_IPTV_CACHE_BYTES) {
                cacheFile().writeBytes(compressed)
            } else {
                val reducedPayload = payload.copy(
                    nowNext = compactNowNext.mapValues { (_, value) ->
                        value.copy(
                            later = null,
                            upcoming = emptyList(),
                            recent = emptyList()
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
            val currentSignature = buildConfigSignature(config)
            val legacySignature = buildLegacyConfigSignature(config)
            val cacheSignature = payload.configSignature.trim()
            if (
                cacheSignature.isNotBlank() &&
                cacheSignature != currentSignature &&
                cacheSignature != legacySignature
            ) return null
            if (payload.channels.isEmpty()) return null
            payload
        }.getOrNull()
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

    private companion object {
        const val ENC_PREFIX = "encv1:"
        const val ANDROID_KEYSTORE = "AndroidKeyStore"
        const val CONFIG_KEY_ALIAS = "arvio_iptv_config_v1"
        const val MAX_IPTV_CACHE_BYTES = 25L * 1024L * 1024L

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
