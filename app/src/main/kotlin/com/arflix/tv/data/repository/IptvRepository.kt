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
import kotlinx.coroutines.delay
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import okhttp3.OkHttpClient
import okhttp3.Request
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import org.xml.sax.Attributes
import org.xml.sax.helpers.DefaultHandler
import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.FileInputStream
import java.io.FilterInputStream
import java.io.InputStream
import java.io.File
import java.nio.charset.StandardCharsets
import java.time.Instant
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.util.Locale
import java.util.concurrent.TimeUnit
import java.util.zip.GZIPInputStream
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
import kotlinx.coroutines.sync.withLock
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import java.lang.reflect.Type
import java.security.KeyStore
import java.security.MessageDigest

data class IptvConfig(
    val m3uUrl: String = "",
    val epgUrl: String = ""
)

data class IptvLoadProgress(
    val message: String,
    val percent: Int? = null
)

@Singleton
class IptvRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val okHttpClient: OkHttpClient,
    private val profileManager: ProfileManager
) {
    private val gson = Gson()
    private val loadMutex = Mutex()

    @Volatile
    private var cachedChannels: List<IptvChannel> = emptyList()

    @Volatile
    private var cachedNowNext: Map<String, IptvNowNext> = emptyMap()

    @Volatile
    private var cachedPlaylistAt: Long = 0L

    @Volatile
    private var cachedEpgAt: Long = 0L

    @Volatile
    private var preferredDerivedEpgUrl: String? = null
    @Volatile
    private var cacheOwnerProfileId: String? = null
    @Volatile
    private var cacheOwnerConfigSig: String? = null
    @Volatile
    private var xtreamVodCacheKey: String? = null
    @Volatile
    private var xtreamVodLoadedAtMs: Long = 0L
    @Volatile
    private var xtreamSeriesLoadedAtMs: Long = 0L
    @Volatile
    private var cachedXtreamVodStreams: List<XtreamVodStream> = emptyList()
    @Volatile
    private var cachedXtreamSeries: List<XtreamSeriesItem> = emptyList()
    @Volatile
    private var cachedXtreamSeriesEpisodes: Map<Int, List<XtreamSeriesEpisode>> = emptyMap()

    private val staleAfterMs = 24 * 60 * 60_000L
    private val playlistCacheMs = staleAfterMs
    private val epgCacheMs = staleAfterMs
    private val epgEmptyRetryMs = 3 * 60_000L
    private val xtreamVodCacheMs = 6 * 60 * 60_000L
    private val iptvHttpClient: OkHttpClient by lazy {
        okHttpClient.newBuilder()
            .connectTimeout(45, TimeUnit.SECONDS)
            .readTimeout(420, TimeUnit.SECONDS)
            .writeTimeout(45, TimeUnit.SECONDS)
            .build()
    }
    private val xtreamLookupHttpClient: OkHttpClient by lazy {
        // Fast-fail client for VOD/source lookups so stream resolution never hangs.
        okHttpClient.newBuilder()
            .connectTimeout(25, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(25, TimeUnit.SECONDS)
            .callTimeout(60, TimeUnit.SECONDS)
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
            IptvConfig(
                m3uUrl = decryptConfigValue(prefs[m3uUrlKey()].orEmpty()),
                epgUrl = decryptConfigValue(prefs[epgUrlKey()].orEmpty())
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

    suspend fun saveConfig(m3uUrl: String, epgUrl: String) {
        val normalizedM3u = normalizeIptvInput(m3uUrl)
        val normalizedEpg = normalizeEpgInput(epgUrl)
        context.settingsDataStore.edit { prefs ->
            prefs[m3uUrlKey()] = encryptConfigValue(normalizedM3u)
            prefs[epgUrlKey()] = encryptConfigValue(normalizedEpg)
        }
        invalidateCache()
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
            if (parsed != null && parsed.encodedPath.endsWith("/get.php")) {
                val username = parsed.queryParameter("username")?.trim().orEmpty()
                val password = parsed.queryParameter("password")?.trim().orEmpty()
                if (username.isNotBlank() && password.isNotBlank()) {
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
            .split(Regex("\\s+"))
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
                val isXtreamPath = parsed.encodedPath.endsWith("/xmltv.php") || parsed.encodedPath.endsWith("/get.php")
                if (isXtreamPath) {
                    val username = parsed.queryParameter("username")?.trim().orEmpty()
                    val password = parsed.queryParameter("password")?.trim().orEmpty()
                    if (username.isNotBlank() && password.isNotBlank()) {
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
            .split(Regex("\\s+"))
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

    private data class XtreamTriplet(
        val host: String,
        val username: String,
        val password: String
    )

    private fun extractXtreamTriplet(raw: String): XtreamTriplet? {
        // Multi-line: host\nuser\npass.
        val partsByLine = raw
            .split('\n', '\r')
            .map { it.trim() }
            .filter { it.isNotBlank() }
        if (partsByLine.size >= 3) {
            return XtreamTriplet(
                host = partsByLine[0],
                username = partsByLine[1],
                password = partsByLine[2]
            )
        }

        // Space-separated: host user pass.
        val partsBySpace = raw
            .split(Regex("\\s+"))
            .map { it.trim() }
            .filter { it.isNotBlank() }
        if (partsBySpace.size >= 3) {
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
            prefs.remove(favoriteGroupsKey())
            prefs.remove(favoriteChannelsKey())
        }
        invalidateCache()
        runCatching { cacheFile().delete() }
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
    }

    suspend fun loadSnapshot(
        forcePlaylistReload: Boolean = false,
        forceEpgReload: Boolean = false,
        onProgress: (IptvLoadProgress) -> Unit = {}
    ): IptvSnapshot {
        return withContext(Dispatchers.IO) {
            loadMutex.withLock {
            onProgress(IptvLoadProgress("Starting IPTV load...", 2))
            val now = System.currentTimeMillis()
            val config = observeConfig().first()
            val profileId = profileManager.getProfileIdSync()
            ensureCacheOwnership(profileId, config)
            if (config.m3uUrl.isBlank()) {
                return@withContext IptvSnapshot(
                    channels = emptyList(),
                    grouped = emptyMap(),
                    nowNext = emptyMap(),
                    favoriteGroups = observeFavoriteGroups().first(),
                    favoriteChannels = observeFavoriteChannels().first(),
                    loadedAt = Instant.now()
                )
            }

            val cachedFromDisk = if (cachedChannels.isEmpty()) readCache(config) else null
            if (cachedFromDisk != null) {
                cachedChannels = cachedFromDisk.channels
                cachedNowNext = cachedFromDisk.nowNext
                cachedPlaylistAt = cachedFromDisk.loadedAtEpochMs
                cachedEpgAt = cachedFromDisk.loadedAtEpochMs
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
                fetchAndParseM3uWithRetries(config.m3uUrl, onProgress).also {
                    cachedChannels = it
                    cachedPlaylistAt = System.currentTimeMillis()
                }
            }

            val epgCandidates = resolveEpgCandidates(config)
            var epgUpdated = false
            val shouldUseCachedEpg = !forceEpgReload && (
                cachedNowNext.isNotEmpty() ||
                    (cachedNowNext.isEmpty() && now - cachedEpgAt < epgEmptyRetryMs)
                )
            var epgFailureMessage: String? = null
            val nowNext = if (epgCandidates.isEmpty()) {
                onProgress(IptvLoadProgress("No EPG URL configured", 90))
                emptyMap()
            } else if (shouldUseCachedEpg) {
                onProgress(IptvLoadProgress("Using cached EPG", 92))
                cachedNowNext
            } else {
                var resolvedNowNext: Map<String, IptvNowNext> = emptyMap()
                var resolved = false
                epgCandidates.forEachIndexed { index, epgUrl ->
                    if (resolved) return@forEachIndexed
                    val pct = (90 + ((index * 8) / epgCandidates.size)).coerceIn(90, 98)
                    onProgress(IptvLoadProgress("Loading EPG (${index + 1}/${epgCandidates.size})...", pct))
                    val attempt = runCatching { fetchAndParseEpg(epgUrl, channels) }
                    if (attempt.isSuccess) {
                        val parsed = attempt.getOrDefault(emptyMap())
                        if (parsed.isNotEmpty() || index == epgCandidates.lastIndex) {
                            resolvedNowNext = parsed
                            cachedNowNext = parsed
                            cachedEpgAt = System.currentTimeMillis()
                            epgUpdated = true
                            preferredDerivedEpgUrl = epgUrl
                            resolved = true
                        }
                    } else {
                        epgFailureMessage = attempt.exceptionOrNull()?.message
                    }
                }
                if (!resolved) {
                    // Throttle repeated failures to avoid refetching every open.
                    cachedNowNext = emptyMap()
                    cachedEpgAt = System.currentTimeMillis()
                    epgUpdated = true
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
            val grouped = channels.groupBy { it.group.ifBlank { "Uncategorized" } }
                .toSortedMap(String.CASE_INSENSITIVE_ORDER)

            val loadedAtMillis = if (cachedPlaylistAt > 0L) cachedPlaylistAt else now
            val loadedAtInstant = Instant.ofEpochMilli(loadedAtMillis)

            IptvSnapshot(
                channels = channels,
                grouped = grouped,
                nowNext = nowNext,
                favoriteGroups = favoriteGroups,
                favoriteChannels = favoriteChannels,
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
                if (config.m3uUrl.isBlank()) return@withLock
                if (cachedChannels.isNotEmpty()) return@withLock

                val cached = readCache(config) ?: return@withLock
                cachedChannels = cached.channels
                cachedNowNext = cached.nowNext
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

                if (config.m3uUrl.isBlank()) {
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
                    cachedNowNext = cached.nowNext
                    cachedPlaylistAt = cached.loadedAtEpochMs
                    cachedEpgAt = cached.loadedAtEpochMs
                }

                val favoriteGroups = observeFavoriteGroups().first()
                val favoriteChannels = observeFavoriteChannels().first()
                val grouped = cachedChannels.groupBy { it.group.ifBlank { "Uncategorized" } }
                    .toSortedMap(String.CASE_INSENSITIVE_ORDER)
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

    fun invalidateCache() {
        cachedChannels = emptyList()
        cachedNowNext = emptyMap()
        cachedPlaylistAt = 0L
        cachedEpgAt = 0L
        xtreamVodCacheKey = null
        xtreamVodLoadedAtMs = 0L
        xtreamSeriesLoadedAtMs = 0L
        cachedXtreamVodStreams = emptyList()
        cachedXtreamSeries = emptyList()
        cachedXtreamSeriesEpisodes = emptyMap()
        cacheOwnerProfileId = null
        cacheOwnerConfigSig = null
    }

    private fun ensureCacheOwnership(profileId: String, config: IptvConfig) {
        val sig = "${config.m3uUrl.trim()}|${config.epgUrl.trim()}"
        val ownerChanged = cacheOwnerProfileId != null && cacheOwnerProfileId != profileId
        val configChanged = cacheOwnerConfigSig != null && cacheOwnerConfigSig != sig
        if (ownerChanged || configChanged) {
            invalidateCache()
        }
        cacheOwnerProfileId = profileId
        cacheOwnerConfigSig = sig
    }

    private fun m3uUrlKey(): Preferences.Key<String> = profileManager.profileStringKey("iptv_m3u_url")
    private fun epgUrlKey(): Preferences.Key<String> = profileManager.profileStringKey("iptv_epg_url")
    private fun favoriteGroupsKey(): Preferences.Key<String> = profileManager.profileStringKey("iptv_favorite_groups")
    private fun favoriteChannelsKey(): Preferences.Key<String> = profileManager.profileStringKey("iptv_favorite_channels")

    private fun decodeFavoriteGroups(prefs: Preferences): List<String> {
        val raw = prefs[favoriteGroupsKey()].orEmpty()
        if (raw.isBlank()) return emptyList()
        return runCatching {
            val type = object : TypeToken<List<String>>() {}.type
            gson.fromJson<List<String>>(raw, type)
                ?.map { it.trim() }
                ?.filter { it.isNotBlank() }
                ?.distinct()
                ?: emptyList()
        }.getOrDefault(emptyList())
    }

    private fun decodeFavoriteChannels(prefs: Preferences): List<String> {
        val raw = prefs[favoriteChannelsKey()].orEmpty()
        if (raw.isBlank()) return emptyList()
        return runCatching {
            val type = object : TypeToken<List<String>>() {}.type
            gson.fromJson<List<String>>(raw, type)
                ?.map { it.trim() }
                ?.filter { it.isNotBlank() }
                ?.distinct()
                ?: emptyList()
        }.getOrDefault(emptyList())
    }

    private suspend fun fetchAndParseM3uWithRetries(
        url: String,
        onProgress: (IptvLoadProgress) -> Unit
    ): List<IptvChannel> {
        resolveXtreamCredentials(url)?.let { creds ->
            onProgress(IptvLoadProgress("Detected Xtream provider. Loading live channels...", 6))
            runCatching { fetchXtreamLiveChannels(creds, onProgress) }
                .onSuccess { channels ->
                    if (channels.isNotEmpty()) {
                        onProgress(IptvLoadProgress("Loaded ${channels.size} live channels from provider API", 95))
                        return channels
                    }
                }
        }

        var lastError: Throwable? = null
        val maxAttempts = 4
        repeat(maxAttempts) { attempt ->
            onProgress(IptvLoadProgress("Connecting to playlist (attempt ${attempt + 1}/$maxAttempts)...", 5))
            runCatching {
                fetchAndParseM3uOnce(url, onProgress)
            }.onSuccess { channels ->
                if (channels.isNotEmpty()) return channels
                lastError = IllegalStateException("Playlist loaded but contains no channels.")
            }.onFailure { error ->
                lastError = error
            }

            if (attempt < maxAttempts - 1) {
                val backoffMs = (2_000L * (attempt + 1)).coerceAtMost(8_000L)
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
        val imdb: String? = null,
        val tmdb: String? = null
    )

    private data class XtreamSeriesItem(
        @SerializedName("series_id") val seriesId: Int? = null,
        val name: String? = null,
        val imdb: String? = null,
        val tmdb: String? = null
    )

    private data class XtreamSeriesEpisode(
        val id: Int,
        val season: Int,
        val episode: Int,
        val title: String,
        val containerExtension: String?
    )

    suspend fun findMovieVodSource(
        title: String,
        year: Int?,
        imdbId: String? = null,
        tmdbId: Int? = null,
        allowNetwork: Boolean = true
    ): StreamSource? {
        val creds = resolveXtreamCredentials(observeConfig().first().m3uUrl) ?: return null
        val vod = getXtreamVodStreams(creds, allowNetwork, fast = true)
        if (vod.isEmpty()) return null

        val normalizedTmdb = normalizeTmdbId(tmdbId)
        if (!normalizedTmdb.isNullOrBlank()) {
            val tmdbMatch = vod.firstOrNull { normalizeTmdbId(it.tmdb) == normalizedTmdb }
            if (tmdbMatch != null) {
                val streamId = tmdbMatch.streamId ?: return null
                val ext = tmdbMatch.containerExtension?.trim()?.ifBlank { null } ?: "mp4"
                val streamUrl = "${creds.baseUrl}/movie/${creds.username}/${creds.password}/$streamId.$ext"
                return StreamSource(
                    source = tmdbMatch.name?.trim().orEmpty().ifBlank { title.ifBlank { normalizedTmdb } },
                    addonName = "IPTV VOD",
                    addonId = "iptv_xtream_vod",
                    quality = inferQuality(tmdbMatch.name.orEmpty()),
                    size = "",
                    url = streamUrl
                )
            }
        }

        val normalizedImdb = normalizeImdbId(imdbId)
        if (!normalizedImdb.isNullOrBlank()) {
            val imdbMatch = vod.firstOrNull { normalizeImdbId(it.imdb) == normalizedImdb }
            if (imdbMatch != null) {
                val streamId = imdbMatch.streamId ?: return null
                val ext = imdbMatch.containerExtension?.trim()?.ifBlank { null } ?: "mp4"
                val streamUrl = "${creds.baseUrl}/movie/${creds.username}/${creds.password}/$streamId.$ext"
                return StreamSource(
                    source = imdbMatch.name?.trim().orEmpty().ifBlank { title.ifBlank { normalizedImdb } },
                    addonName = "IPTV VOD",
                    addonId = "iptv_xtream_vod",
                    quality = inferQuality(imdbMatch.name.orEmpty()),
                    size = "",
                    url = streamUrl
                )
            }
        }

        val normalizedTitle = normalizeLookupText(title)
        if (normalizedTitle.isBlank()) return null
        val inputYear = year ?: parseYear(title)

        val best = vod
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
            .firstOrNull()
            ?.first ?: return null

        val streamId = best.streamId ?: return null
        val ext = best.containerExtension?.trim()?.ifBlank { null } ?: "mp4"
        val streamUrl = "${creds.baseUrl}/movie/${creds.username}/${creds.password}/$streamId.$ext"

        return StreamSource(
            source = best.name?.trim().orEmpty().ifBlank { title },
            addonName = "IPTV VOD",
            addonId = "iptv_xtream_vod",
            quality = inferQuality(best.name.orEmpty()),
            size = "",
            url = streamUrl
        )
    }

    suspend fun findEpisodeVodSource(
        title: String,
        season: Int,
        episode: Int,
        imdbId: String? = null,
        tmdbId: Int? = null,
        allowNetwork: Boolean = true
    ): StreamSource? {
        val creds = resolveXtreamCredentials(observeConfig().first().m3uUrl) ?: return null
        val normalizedTitle = normalizeLookupText(title)
        val normalizedImdb = normalizeImdbId(imdbId)
        val normalizedTmdb = normalizeTmdbId(tmdbId)
        if (normalizedTitle.isBlank() && normalizedImdb.isNullOrBlank() && normalizedTmdb.isNullOrBlank()) return null

        // Fast path: many providers expose episodic VOD directly in movie/vod catalog entries.
        // Try this first so TV/anime VOD can appear as fast as movie VOD.
        findEpisodeVodFromVodCatalogFallback(
            creds = creds,
            title = title,
            season = season,
            episode = episode,
            normalizedImdb = normalizedImdb,
            normalizedTmdb = normalizedTmdb,
            allowNetwork = allowNetwork
        )?.let { return it }

        val seriesList = getXtreamSeriesList(creds, allowNetwork, fast = true)
        if (seriesList.isEmpty()) {
            return findEpisodeVodFromVodCatalogFallback(
                creds = creds,
                title = title,
                season = season,
                episode = episode,
                normalizedImdb = normalizedImdb,
                normalizedTmdb = normalizedTmdb,
                allowNetwork = allowNetwork
            )
        }

        val tmdbCandidates = if (!normalizedTmdb.isNullOrBlank()) {
            seriesList.asSequence()
                .mapNotNull { item ->
                    val id = item.seriesId ?: return@mapNotNull null
                    val name = item.name?.trim().orEmpty().ifBlank { "Series $id" }
                    if (normalizeTmdbId(item.tmdb) == normalizedTmdb) Triple(id, name, 9_500) else null
                }
                .toList()
        } else {
            emptyList()
        }

        val imdbCandidates = if (!normalizedImdb.isNullOrBlank()) {
            seriesList.asSequence()
                .mapNotNull { item ->
                    val id = item.seriesId ?: return@mapNotNull null
                    val name = item.name?.trim().orEmpty().ifBlank { "Series $id" }
                    if (normalizeImdbId(item.imdb) == normalizedImdb) Triple(id, name, 10_000) else null
                }
                .toList()
        } else {
            emptyList()
        }

        val candidates = seriesList
            .asSequence()
            .mapNotNull { item ->
                val name = item.name?.trim().orEmpty()
                val id = item.seriesId ?: return@mapNotNull null
                val score = if (name.isBlank() || normalizedTitle.isBlank()) {
                    0
                } else {
                    maxOf(
                        scoreNameMatch(name, normalizedTitle),
                        looseSeriesTitleScore(name, normalizedTitle)
                    )
                }
                if (score <= 0) return@mapNotNull null
                Triple(id, name, score)
            }
            .sortedByDescending { it.third }
            .take(16)
            .toList()
            .let { titleCandidates ->
                val merged = LinkedHashMap<Int, Triple<Int, String, Int>>()
                tmdbCandidates.forEach { merged[it.first] = it }
                imdbCandidates.forEach { merged[it.first] = it }
                titleCandidates.forEach { if (!merged.containsKey(it.first)) merged[it.first] = it }
                merged.values.toList()
            }
        if (candidates.isEmpty()) {
            return findEpisodeVodFromVodCatalogFallback(
                creds = creds,
                title = title,
                season = season,
                episode = episode,
                normalizedImdb = normalizedImdb,
                normalizedTmdb = normalizedTmdb,
                allowNetwork = allowNetwork
            )
        }

        // Fast ID-first path: if TMDB/IMDB matched a series, resolve those first before broad title probing.
        val idFirstCandidates = buildList {
            tmdbCandidates.forEach { add(it) }
            imdbCandidates.forEach { add(it) }
        }.distinctBy { it.first }.take(4)
        if (idFirstCandidates.isNotEmpty()) {
            idFirstCandidates.forEach { (seriesId, seriesName, _) ->
                val episodes = withTimeoutOrNull(12_000L) {
                    getXtreamSeriesEpisodes(creds, seriesId, allowNetwork = allowNetwork, fast = true)
                }.orEmpty()
                val match = pickEpisodeMatch(episodes, season, episode) ?: return@forEach
                val ext = match.containerExtension?.trim()?.ifBlank { null } ?: "mp4"
                val streamUrl = "${creds.baseUrl}/series/${creds.username}/${creds.password}/${match.id}.$ext"
                return StreamSource(
                    source = match.title.ifBlank { "$seriesName S${season}E${episode}" },
                    addonName = "IPTV Series VOD",
                    addonId = "iptv_xtream_vod",
                    quality = inferQuality(match.title),
                    size = "",
                    url = streamUrl
                )
            }
        }

        if (!allowNetwork) {
            candidates.forEach { (seriesId, seriesName, _) ->
                val episodes = getXtreamSeriesEpisodes(creds, seriesId, allowNetwork = false)
                val match = pickEpisodeMatch(episodes, season, episode) ?: return@forEach
                val ext = match.containerExtension?.trim()?.ifBlank { null } ?: "mp4"
                val streamUrl = "${creds.baseUrl}/series/${creds.username}/${creds.password}/${match.id}.$ext"
                return StreamSource(
                    source = match.title.ifBlank { "$seriesName S${season}E${episode}" },
                    addonName = "IPTV Series VOD",
                    addonId = "iptv_xtream_vod",
                    quality = inferQuality(match.title),
                    size = "",
                    url = streamUrl
                )
            }
            return findEpisodeVodFromVodCatalogFallback(
                creds = creds,
                title = title,
                season = season,
                episode = episode,
                normalizedImdb = normalizedImdb,
                normalizedTmdb = normalizedTmdb,
                allowNetwork = false
            )
        }

        // Fast first-wave probing: test best candidates concurrently and return early.
        val prioritizedCandidates = candidates
            .sortedByDescending { rankSeriesCandidate(it.second, normalizedTitle, it.third) }
        val firstWave = prioritizedCandidates.take(6)
        if (firstWave.isNotEmpty()) {
            val firstWaveMatches = coroutineScope {
                firstWave.map { (seriesId, seriesName, score) ->
                    async {
                        val episodes = withTimeoutOrNull(3_500L) {
                            getXtreamSeriesEpisodes(creds, seriesId, allowNetwork = true, fast = true)
                        } ?: return@async null
                        val match = pickEpisodeMatch(episodes, season, episode) ?: return@async null
                        Triple(score, seriesName, match)
                    }
                }.awaitAll().filterNotNull()
            }
            val bestWave = firstWaveMatches.maxByOrNull { it.first }
            if (bestWave != null) {
                val ext = bestWave.third.containerExtension?.trim()?.ifBlank { null } ?: "mp4"
                val streamUrl = "${creds.baseUrl}/series/${creds.username}/${creds.password}/${bestWave.third.id}.$ext"
                return StreamSource(
                    source = bestWave.third.title.ifBlank { "${bestWave.second} S${season}E${episode}" },
                    addonName = "IPTV Series VOD",
                    addonId = "iptv_xtream_vod",
                    quality = inferQuality(bestWave.third.title),
                    size = "",
                    url = streamUrl
                )
            }
        }

        // Second-wave probing for remaining candidates.
        for ((seriesId, seriesName, _) in prioritizedCandidates.drop(6)) {
            val episodes = withTimeoutOrNull(3_000L) {
                getXtreamSeriesEpisodes(creds, seriesId, allowNetwork = true, fast = true)
            }.orEmpty()
            val match = pickEpisodeMatch(episodes, season, episode) ?: continue
            val ext = match.containerExtension?.trim()?.ifBlank { null } ?: "mp4"
            val streamUrl = "${creds.baseUrl}/series/${creds.username}/${creds.password}/${match.id}.$ext"
            return StreamSource(
                source = match.title.ifBlank { "${seriesName} S${season}E${episode}" },
                addonName = "IPTV Series VOD",
                addonId = "iptv_xtream_vod",
                quality = inferQuality(match.title),
                size = "",
                url = streamUrl
            )
        }

        run {
            // Last-resort provider fallback: if metadata is poor (no title/ids), probe a bounded
            // window of raw series IDs and accept exact season/episode hits.
            val broadCandidates = seriesList.asSequence()
                .mapNotNull { item ->
                    val id = item.seriesId ?: return@mapNotNull null
                    val name = item.name?.trim().orEmpty().ifBlank { "Series $id" }
                    Triple(id, name, 1)
                }
                .take(14)
                .toList()
            if (broadCandidates.isNotEmpty()) {
                for ((seriesId, seriesName, _) in broadCandidates) {
                    val episodes = withTimeoutOrNull(4_000L) {
                        getXtreamSeriesEpisodes(creds, seriesId, allowNetwork = true, fast = true)
                    }.orEmpty()
                    val match = pickEpisodeMatch(episodes, season, episode) ?: continue
                    val ext = match.containerExtension?.trim()?.ifBlank { null } ?: "mp4"
                    val streamUrl = "${creds.baseUrl}/series/${creds.username}/${creds.password}/${match.id}.$ext"
                    return StreamSource(
                        source = match.title.ifBlank { "${seriesName} S${season}E${episode}" },
                        addonName = "IPTV Series VOD",
                        addonId = "iptv_xtream_vod",
                        quality = inferQuality(match.title),
                        size = "",
                        url = streamUrl
                    )
                }
            }
            return findEpisodeVodFromVodCatalogFallback(
                creds = creds,
                title = title,
                season = season,
                episode = episode,
                normalizedImdb = normalizedImdb,
                normalizedTmdb = normalizedTmdb,
                allowNetwork = true
            )
        }
    }

    private suspend fun findEpisodeVodFromVodCatalogFallback(
        creds: XtreamCredentials,
        title: String,
        season: Int,
        episode: Int,
        normalizedImdb: String?,
        normalizedTmdb: String?,
        allowNetwork: Boolean
    ): StreamSource? {
        val normalizedTitle = normalizeLookupText(title)
        val vod = getXtreamVodStreams(creds, allowNetwork = allowNetwork, fast = true)
        if (vod.isEmpty()) return null

        val best = vod.asSequence()
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
            .firstOrNull()
            ?: return null

        val item = best.first
        val streamId = best.second
        val ext = item.containerExtension?.trim()?.ifBlank { null } ?: "mp4"
        val streamUrl = "${creds.baseUrl}/movie/${creds.username}/${creds.password}/$streamId.$ext"
        return StreamSource(
            source = item.name?.trim().orEmpty().ifBlank { "$title S${season}E${episode}" },
            addonName = "IPTV Episode VOD",
            addonId = "iptv_xtream_vod",
            quality = inferQuality(item.name.orEmpty()),
            size = "",
            url = streamUrl
        )
    }

    suspend fun warmXtreamVodCachesIfPossible() {
        val creds = resolveXtreamCredentials(observeConfig().first().m3uUrl) ?: return
        runCatching {
            loadXtreamVodStreams(creds)
            loadXtreamSeriesList(creds)
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
    }

    private suspend fun loadXtreamVodStreams(
        creds: XtreamCredentials,
        fast: Boolean = false
    ): List<XtreamVodStream> {
        return withContext(Dispatchers.IO) {
            loadMutex.withLock {
                ensureXtreamVodCacheOwnership(creds)
                val now = System.currentTimeMillis()
                if (cachedXtreamVodStreams.isNotEmpty() && now - xtreamVodLoadedAtMs < xtreamVodCacheMs) {
                    return@withLock cachedXtreamVodStreams
                }
                val url = "${creds.baseUrl}/player_api.php?username=${creds.username}&password=${creds.password}&action=get_vod_streams"
                val vod: List<XtreamVodStream> =
                    requestJson(
                        url,
                        object : TypeToken<List<XtreamVodStream>>() {}.type,
                        client = if (fast) xtreamLookupHttpClient else iptvHttpClient
                    ) ?: emptyList()
                cachedXtreamVodStreams = vod
                xtreamVodLoadedAtMs = now
                vod
            }
        }
    }

    private suspend fun getXtreamVodStreams(
        creds: XtreamCredentials,
        allowNetwork: Boolean,
        fast: Boolean = false
    ): List<XtreamVodStream> {
        if (allowNetwork) return loadXtreamVodStreams(creds, fast = fast)
        ensureXtreamVodCacheOwnership(creds)
        return cachedXtreamVodStreams
    }

    private suspend fun loadXtreamSeriesList(
        creds: XtreamCredentials,
        fast: Boolean = false
    ): List<XtreamSeriesItem> {
        return withContext(Dispatchers.IO) {
            loadMutex.withLock {
                ensureXtreamVodCacheOwnership(creds)
                val now = System.currentTimeMillis()
                if (cachedXtreamSeries.isNotEmpty() && now - xtreamSeriesLoadedAtMs < xtreamVodCacheMs) {
                    return@withLock cachedXtreamSeries
                }
                val url = "${creds.baseUrl}/player_api.php?username=${creds.username}&password=${creds.password}&action=get_series"
                val series: List<XtreamSeriesItem> =
                    requestJson(
                        url,
                        object : TypeToken<List<XtreamSeriesItem>>() {}.type,
                        client = if (fast) xtreamLookupHttpClient else iptvHttpClient
                    ) ?: emptyList()
                cachedXtreamSeries = series
                xtreamSeriesLoadedAtMs = now
                series
            }
        }
    }

    private suspend fun getXtreamSeriesList(
        creds: XtreamCredentials,
        allowNetwork: Boolean,
        fast: Boolean = false
    ): List<XtreamSeriesItem> {
        if (allowNetwork) return loadXtreamSeriesList(creds, fast = fast)
        ensureXtreamVodCacheOwnership(creds)
        return cachedXtreamSeries
    }

    private suspend fun loadXtreamSeriesEpisodes(
        creds: XtreamCredentials,
        seriesId: Int,
        fast: Boolean = false
    ): List<XtreamSeriesEpisode> {
        val cached = cachedXtreamSeriesEpisodes[seriesId]
        if (!cached.isNullOrEmpty()) return cached

        return withContext(Dispatchers.IO) {
            loadMutex.withLock {
                val alreadyCached = cachedXtreamSeriesEpisodes[seriesId]
                if (!alreadyCached.isNullOrEmpty()) return@withLock alreadyCached

                val url = "${creds.baseUrl}/player_api.php?username=${creds.username}&password=${creds.password}&action=get_series_info&series_id=$seriesId"
                val info: JsonObject = requestJson(
                    url,
                    JsonObject::class.java,
                    client = if (fast) xtreamLookupHttpClient else iptvHttpClient
                ) ?: return@withLock emptyList()
                val parsed = parseXtreamSeriesEpisodes(info)
                val next = cachedXtreamSeriesEpisodes.toMutableMap()
                next[seriesId] = parsed
                cachedXtreamSeriesEpisodes = next
                parsed
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
        val episodesObj = root.getAsJsonObject("episodes") ?: return emptyList()
        val out = mutableListOf<XtreamSeriesEpisode>()

        episodesObj.entrySet().forEach { (seasonKey, value) ->
            val season = parseSeasonKey(seasonKey)
            when {
                value == null || value.isJsonNull -> return@forEach
                value.isJsonArray -> {
                    parseSeasonEpisodes(season, value.asJsonArray).forEach { out += it }
                }
                value.isJsonObject -> {
                    // Some providers return {"1": {...}, "2": {...}} instead of an array.
                    val obj = value.asJsonObject
                    val syntheticArray = JsonArray()
                    obj.entrySet().forEach { (_, epValue) ->
                        if (epValue != null && epValue.isJsonObject) syntheticArray.add(epValue.asJsonObject)
                    }
                    parseSeasonEpisodes(season, syntheticArray).forEach { out += it }
                }
            }
        }

        return out
    }

    private fun parseSeasonKey(raw: String): Int? {
        if (raw.isBlank()) return null
        return raw.toIntOrNull() ?: Regex("""\d{1,2}""").find(raw)?.value?.toIntOrNull()
    }

    private fun parseSeasonEpisodes(seasonKey: Int?, array: JsonArray): List<XtreamSeriesEpisode> {
        val out = mutableListOf<XtreamSeriesEpisode>()
        array.forEachIndexed { index, element ->
            val item = element?.asJsonObject ?: return@forEachIndexed
            val season = seasonKey ?: item.get("season")?.asString?.toIntOrNull() ?: 1
            val infoObj = item.getAsJsonObject("info")
            val rawTitle = item.get("title")?.asString?.trim().orEmpty()
            val episodeNum = item.get("episode_num")?.asString?.toIntOrNull()
                ?: item.get("episode")?.asString?.toIntOrNull()
                ?: item.get("episode_number")?.asString?.toIntOrNull()
                ?: item.get("sort")?.asString?.toIntOrNull()
                ?: infoObj?.get("episode_num")?.asString?.toIntOrNull()
                ?: infoObj?.get("episode")?.asString?.toIntOrNull()
                ?: extractEpisodeOnlyFromName(rawTitle)
                ?: (index + 1)
            val id = item.get("id")?.asString?.toIntOrNull()
                ?: item.get("stream_id")?.asString?.toIntOrNull()
                ?: return@forEachIndexed
            val title = rawTitle.ifBlank {
                "S${season}E${episodeNum}"
            }
            val ext = item.get("container_extension")?.asString?.trim()?.ifBlank { null }
            out += XtreamSeriesEpisode(
                id = id,
                season = season,
                episode = episodeNum,
                title = title,
                containerExtension = ext
            )
        }
        return out
    }

    private fun normalizeLookupText(value: String): String {
        return value
            .lowercase(Locale.US)
            .replace(Regex("\\(\\d{4}\\)"), " ")
            .replace(Regex("[^a-z0-9]+"), " ")
            .trim()
            .replace(Regex("\\s+"), " ")
    }

    private fun normalizeImdbId(value: String?): String? {
        if (value.isNullOrBlank()) return null
        val cleaned = value.trim().lowercase(Locale.US)
        val match = Regex("tt\\d{5,10}").find(cleaned)?.value
        return match ?: cleaned.takeIf { it.startsWith("tt") && it.length >= 7 }
    }

    private fun normalizeTmdbId(value: String?): String? {
        if (value.isNullOrBlank()) return null
        val digits = Regex("\\d{1,10}").find(value.trim())?.value
        return digits?.trimStart('0')?.ifBlank { "0" }
    }

    private fun normalizeTmdbId(value: Int?): String? {
        if (value == null || value <= 0) return null
        return value.toString()
    }

    private fun parseYear(value: String): Int? {
        return Regex("(19|20)\\d{2}")
            .find(value)
            ?.value
            ?.toIntOrNull()
    }

    private fun extractSeasonEpisodeFromName(value: String): Pair<Int, Int>? {
        val normalized = value.lowercase(Locale.US)
        val patterns = listOf(
            Regex("""\bs(\d{1,2})\s*[\.\-_ ]*\s*e(\d{1,3})\b""", RegexOption.IGNORE_CASE),
            Regex("""\b(\d{1,2})x(\d{1,3})\b""", RegexOption.IGNORE_CASE),
            Regex("""\bseason\s*(\d{1,2}).*episode\s*(\d{1,3})\b""", RegexOption.IGNORE_CASE)
        )
        patterns.forEach { regex ->
            val match = regex.find(normalized) ?: return@forEach
            val season = match.groupValues.getOrNull(1)?.toIntOrNull() ?: return@forEach
            val episode = match.groupValues.getOrNull(2)?.toIntOrNull() ?: return@forEach
            return Pair(season, episode)
        }
        return null
    }

    private fun extractEpisodeOnlyFromName(value: String): Int? {
        val normalized = value.lowercase(Locale.US)
        val patterns = listOf(
            Regex("""\bepisode\s*(\d{1,3})\b""", RegexOption.IGNORE_CASE),
            Regex("""\bep\s*(\d{1,3})\b""", RegexOption.IGNORE_CASE),
            Regex("""\be(\d{1,3})\b""", RegexOption.IGNORE_CASE),
            Regex("""[\[\(\- ](\d{1,3})[\]\) ]?$""", RegexOption.IGNORE_CASE)
        )
        patterns.forEach { regex ->
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

    private fun resolveXtreamCredentials(url: String): XtreamCredentials? {
        val parsed = url.toHttpUrlOrNull() ?: return null
        val path = parsed.encodedPath.lowercase(Locale.US)
        if (!(path.endsWith("/get.php") || path.endsWith("/xmltv.php"))) return null
        val username = parsed.queryParameter("username")?.trim().orEmpty()
        val password = parsed.queryParameter("password")?.trim().orEmpty()
        if (username.isBlank() || password.isBlank()) return null
        val defaultPort = when (parsed.scheme.lowercase(Locale.US)) {
            "http" -> 80
            "https" -> 443
            else -> -1
        }
        val portPart = if (parsed.port != defaultPort) ":${parsed.port}" else ""
        val baseUrl = "${parsed.scheme}://${parsed.host}$portPart"
        return XtreamCredentials(baseUrl, username, password)
    }

    private fun resolveEpgCandidates(config: IptvConfig): List<String> {
        val manual = config.epgUrl.takeIf { it.isNotBlank() }
        val creds = resolveXtreamCredentials(config.epgUrl).let { fromEpg ->
            fromEpg ?: resolveXtreamCredentials(config.m3uUrl)
        }
        val derived = if (creds != null) {
            buildList {
            preferredDerivedEpgUrl?.takeIf { it.startsWith(creds.baseUrl) }?.let { add(it) }
            add("${creds.baseUrl}/xmltv.php?username=${creds.username}&password=${creds.password}")
            add("${creds.baseUrl}/get.php?username=${creds.username}&password=${creds.password}&type=xmltv")
            add("${creds.baseUrl}/get.php?username=${creds.username}&password=${creds.password}&type=xml")
            add("${creds.baseUrl}/xmltv.php")
            add("${creds.baseUrl}/get.php?username=${creds.username}&password=${creds.password}")
        }
        } else {
            emptyList()
        }

        return buildList {
            manual?.let { add(it) }
            addAll(derived)
        }.distinct()
    }

    private fun fetchXtreamLiveChannels(
        creds: XtreamCredentials,
        onProgress: (IptvLoadProgress) -> Unit
    ): List<IptvChannel> {
        val categoriesUrl = "${creds.baseUrl}/player_api.php?username=${creds.username}&password=${creds.password}&action=get_live_categories"
        val streamsUrl = "${creds.baseUrl}/player_api.php?username=${creds.username}&password=${creds.password}&action=get_live_streams"

        onProgress(IptvLoadProgress("Loading categories...", 10))
        val categories: List<XtreamLiveCategory> =
            requestJson(categoriesUrl, object : TypeToken<List<XtreamLiveCategory>>() {}.type) ?: emptyList()
        val categoryMap = categories
            .associate { it.categoryId.orEmpty() to (it.categoryName?.trim().orEmpty().ifBlank { "Uncategorized" }) }

        onProgress(IptvLoadProgress("Loading live streams...", 35))
        val streams: List<XtreamLiveStream> =
            requestJson(streamsUrl, object : TypeToken<List<XtreamLiveStream>>() {}.type) ?: emptyList()
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
            val streamUrl = "${creds.baseUrl}/${creds.username}/${creds.password}/$streamId"

            IptvChannel(
                id = "xtream:$streamId",
                name = name,
                streamUrl = streamUrl,
                group = group,
                logo = stream.streamIcon?.takeIf { it.isNotBlank() },
                epgId = stream.epgChannelId?.trim()?.takeIf { it.isNotBlank() },
                rawTitle = name
            )
        }
    }

    private fun <T> requestJson(
        url: String,
        type: Type,
        client: OkHttpClient = iptvHttpClient
    ): T? {
        val request = Request.Builder()
            .url(url)
            .header("User-Agent", "VLC/3.0.20 LibVLC/3.0.20")
            .header("Accept", "application/json,*/*")
            .get()
            .build()
        val response = client.newCall(request).execute()
        response.use {
            if (!it.isSuccessful) return null
            val body = it.body?.string() ?: return null
            if (body.isBlank()) return null
            return runCatching { gson.fromJson<T>(body, type) }.getOrNull()
        }
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
            val stream = BufferedInputStream(progressStream)
            if (!response.isSuccessful && !looksLikeM3u(stream)) {
                val preview = response.peekBody(220).string().replace('\n', ' ').trim()
                val detail = if (preview.isBlank()) "No response body." else preview
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

            // Spool once to disk so we can retry parsing with sanitization without
            // re-downloading a very large EPG payload.
            val tmpFile = File.createTempFile("epg_", ".xml", context.cacheDir)
            runCatching {
                prepared.use { input ->
                    BufferedOutputStream(tmpFile.outputStream()).use { output ->
                        input.copyTo(output, DEFAULT_BUFFER_SIZE)
                    }
                }
            }.getOrElse {
                tmpFile.delete()
                throw it
            }

            try {
                return FileInputStream(tmpFile).use { input ->
                    val sanitized = BackslashEscapeSanitizingInputStream(BufferedInputStream(input))
                    parseXmlTvNowNext(BufferedInputStream(sanitized), channels)
                }
            } catch (firstError: Exception) {
                try {
                    // Retry once without sanitizer in case sanitization changed valid text.
                    return FileInputStream(tmpFile).use { input ->
                        parseXmlTvNowNext(BufferedInputStream(input), channels)
                    }
                } catch (_: Exception) {
                    // Final fallback: SAX parser (different engine than XmlPullParser).
                    return FileInputStream(tmpFile).use { input ->
                        val sanitized = BackslashEscapeSanitizingInputStream(BufferedInputStream(input))
                        parseXmlTvNowNextWithSax(BufferedInputStream(sanitized), channels)
                    }
                }
            } finally {
                tmpFile.delete()
            }
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
        var pendingMetadata: String? = null
        var parsedCount = 0

        input.bufferedReader().useLines { lines ->
            lines.forEach { rawLine ->
                val line = rawLine.trim()
                if (line.isEmpty()) return@forEach

                if (line.startsWith("#EXTINF", ignoreCase = true)) {
                    pendingMetadata = line
                    return@forEach
                }

                if (line.startsWith("#")) return@forEach

                val metadata = pendingMetadata
                pendingMetadata = null

                val channelName = extractChannelName(metadata)
                val groupTitle = extractAttr(metadata, "group-title")?.takeIf { it.isNotBlank() } ?: "Uncategorized"
                val logo = extractAttr(metadata, "tvg-logo")
                val epgId = extractAttr(metadata, "tvg-id")
                val id = buildChannelId(line, epgId)

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
                if (parsedCount % 10000 == 0) {
                    onProgress(IptvLoadProgress("Parsing channels... $parsedCount found", 85))
                }
            }
        }

        onProgress(IptvLoadProgress("Finalizing ${channels.size} channels...", 95))
        return channels.distinctBy { it.id }
    }

    private fun parseXmlTvNowNext(
        input: InputStream,
        channels: List<IptvChannel>
    ): Map<String, IptvNowNext> {
        if (channels.isEmpty()) return emptyMap()

        val keyLookup = buildChannelKeyLookup(channels)

        val nowUtc = System.currentTimeMillis()
        val candidates = mutableMapOf<String, Pair<IptvProgram?, IptvProgram?>>()
        val xmlChannelNameMap = mutableMapOf<String, MutableSet<String>>()

        val parser = XmlPullParserFactory.newInstance().newPullParser().apply {
            // Some IPTV XMLTV feeds are malformed. Relaxed mode keeps parsing instead of failing hard.
            runCatching { setFeature("http://xmlpull.org/v1/doc/features.html#relaxed", true) }
            // Avoid docdecl/DTD edge cases in provider feeds.
            runCatching { setFeature("http://xmlpull.org/v1/doc/features.html#process-docdecl", false) }
            // Use InputStream so XmlPullParser honors XML encoding declarations.
            setInput(input, null)
        }

        var eventType = parser.eventType
        var currentChannelKey: String? = null
        var currentStart = 0L
        var currentStop = 0L
        var currentTitle: String? = null
        var currentDesc: String? = null
        var currentXmlChannelId: String? = null

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
                            currentChannelKey = normalizeChannelKey(parser.getAttributeValue(null, "channel") ?: "")
                            currentStart = parseXmlTvDate(parser.getAttributeValue(null, "start"))
                            currentStop = parseXmlTvDate(parser.getAttributeValue(null, "stop"))
                            currentTitle = null
                            currentDesc = null
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

                            val existing = candidates[channel.id] ?: (null to null)
                            val nowProgram = pickNow(existing.first, program, nowUtc)
                            val nextProgram = pickNext(existing.second, program, nowUtc)
                            candidates[channel.id] = nowProgram to nextProgram
                        }
                        currentChannelKey = null
                    }
                    }
                }
            }
            eventType = parser.next()
        }

        return candidates.mapValues { (_, pair) ->
            IptvNowNext(now = pair.first, next = pair.second)
        }
    }

    private fun parseXmlTvNowNextWithSax(
        input: InputStream,
        channels: List<IptvChannel>
    ): Map<String, IptvNowNext> {
        if (channels.isEmpty()) return emptyMap()

        val keyLookup = buildChannelKeyLookup(channels)
        val xmlChannelNameMap = mutableMapOf<String, MutableSet<String>>()
        val candidates = mutableMapOf<String, Pair<IptvProgram?, IptvProgram?>>()
        val nowUtc = System.currentTimeMillis()

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
                            val existing = candidates[channel.id] ?: (null to null)
                            val nowProgram = pickNow(existing.first, program, nowUtc)
                            val nextProgram = pickNext(existing.second, program, nowUtc)
                            candidates[channel.id] = nowProgram to nextProgram
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

        return candidates.mapValues { (_, pair) ->
            IptvNowNext(now = pair.first, next = pair.second)
        }
    }

    private fun pickNow(existing: IptvProgram?, candidate: IptvProgram, nowUtcMillis: Long): IptvProgram? {
        if (!candidate.isLive(nowUtcMillis)) return existing
        if (existing == null) return candidate
        return if (candidate.startUtcMillis >= existing.startUtcMillis) candidate else existing
    }

    private fun pickNext(existing: IptvProgram?, candidate: IptvProgram, nowUtcMillis: Long): IptvProgram? {
        if (candidate.startUtcMillis <= nowUtcMillis) return existing
        if (existing == null) return candidate
        return if (candidate.startUtcMillis < existing.startUtcMillis) candidate else existing
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
        return if (normalizedEpg.isNotBlank()) {
            "epg:$normalizedEpg"
        } else {
            "url:${streamUrl.trim()}"
        }
    }

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
        return normalizeChannelKey(value).replace(Regex("[^a-z0-9]"), "")
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
            .replace(Regex("\\b(hd|fhd|uhd|sd|4k|hevc|x265|x264|h264|h265)\\b"), "")
            .replace(Regex("\\s+"), " ")
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

    private fun buildConfigSignature(config: IptvConfig): String {
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
            val payload = IptvCachePayload(
                channels = channels,
                nowNext = nowNext,
                loadedAtEpochMs = loadedAtMs,
                configSignature = buildConfigSignature(config)
            )
            cacheFile().writeText(gson.toJson(payload), StandardCharsets.UTF_8)
        }
    }

    private fun readCache(config: IptvConfig): IptvCachePayload? {
        return runCatching {
            val file = cacheFile()
            if (!file.exists()) return null
            val text = file.readText(StandardCharsets.UTF_8)
            if (text.isBlank()) return null
            val payload = gson.fromJson(text, IptvCachePayload::class.java) ?: return null
            if (payload.configSignature != buildConfigSignature(config)) return null
            if (payload.channels.isEmpty()) return null
            payload
        }.getOrNull()
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
