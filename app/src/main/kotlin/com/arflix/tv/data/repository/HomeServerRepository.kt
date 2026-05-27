package com.arflix.tv.data.repository

import android.content.Context
import android.provider.Settings
import androidx.datastore.preferences.core.edit
import com.arflix.tv.BuildConfig
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.model.ProxyHeaders
import com.arflix.tv.data.model.StreamBehaviorHints
import com.arflix.tv.data.model.StreamSource
import com.arflix.tv.util.SecureStorage
import com.arflix.tv.util.settingsDataStore
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.Headers
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.net.URLDecoder
import java.net.URLEncoder
import java.text.Normalizer
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.abs

enum class HomeServerKind {
    UNKNOWN,
    JELLYFIN,
    EMBY,
    PLEX
}
data class HomeServerCollection(
    val id: String = "",
    val name: String = "",
    val type: String = "",
    val enabled: Boolean = true
)

data class HomeServerConnection(
    val enabled: Boolean = true,
    val connectionId: String = "",
    val serverUrl: String = "",
    val displayName: String = "",
    val serverName: String = "",
    val serverKind: HomeServerKind = HomeServerKind.UNKNOWN,
    val serverId: String = "",
    val userId: String = "",
    val userName: String = "",
    val accessToken: String = "",
    val accountToken: String = "",
    val collections: List<HomeServerCollection> = emptyList(),
    val lastConnectedAt: Long = 0L
) {
    val isUsable: Boolean
        get() = enabled && serverUrl.isNotBlank() && accessToken.isNotBlank() &&
            (serverKind == HomeServerKind.PLEX || userId.isNotBlank())
}

private data class HomeServerProfileConfig(
    val connections: List<HomeServerConnection> = emptyList()
)

data class PlexPinAuthSession(
    val id: String = "",
    val code: String = "",
    val verificationUrl: String = "",
    val expiresIn: Int = 600,
    val interval: Int = 5
)

data class HomeServerCatalogCandidate(
    val title: String,
    val sourceRef: String,
    val serverName: String,
    val collectionName: String,
    val collectionType: String
)

data class HomeServerCatalogItem(
    val id: String,
    val title: String,
    val mediaType: MediaType,
    val year: Int?,
    val providerIds: Map<String, String>
)

data class HomeServerCatalogPage(
    val items: List<HomeServerCatalogItem>,
    val hasMore: Boolean
)

internal data class HomeServerCandidateInfo(
    val title: String,
    val productionYear: Int?,
    val providerIds: Map<String, String>
)

internal object HomeServerMatcher {
    fun normalizeTitle(title: String): String {
        val ascii = Normalizer.normalize(title, Normalizer.Form.NFD)
            .replace(HomeServerRepositoryRegexes.DIACRITICS_REGEX, "")
        return ascii
            .lowercase(Locale.US)
            .replace("&", " and ")
            .replace(HomeServerRepositoryRegexes.NON_ALPHA_NUM_REGEX, " ")
            .replace(HomeServerRepositoryRegexes.ARTICLES_REGEX, " ")
            .trim()
            .replace(HomeServerRepositoryRegexes.MULTI_SPACE_REGEX, " ")
    }

    fun score(
        requestedTitle: String,
        requestedYear: Int?,
        imdbId: String?,
        tmdbId: Int?,
        tvdbId: Int?,
        candidate: HomeServerCandidateInfo
    ): Int {
        var score = 0
        val providers = candidate.providerIds.mapKeys { it.key.lowercase(Locale.US) }
        val cleanImdb = imdbId?.trim()?.lowercase(Locale.US)
        if (!cleanImdb.isNullOrBlank() && providers["imdb"]?.lowercase(Locale.US) == cleanImdb) {
            score += 1000
        }
        if (tmdbId != null && providers["tmdb"]?.toIntOrNull() == tmdbId) {
            score += 900
        }
        if (tvdbId != null && providers["tvdb"]?.toIntOrNull() == tvdbId) {
            score += 900
        }

        val requestedNormalized = normalizeTitle(requestedTitle)
        val candidateNormalized = normalizeTitle(candidate.title)
        if (requestedNormalized.isNotBlank() && candidateNormalized.isNotBlank()) {
            when {
                requestedNormalized == candidateNormalized -> score += 140
                requestedNormalized in candidateNormalized || candidateNormalized in requestedNormalized -> score += 65
            }
        }

        val candidateYear = candidate.productionYear
        if (requestedYear != null && candidateYear != null) {
            val delta = abs(requestedYear - candidateYear)
            when {
                delta == 0 -> score += 90
                delta == 1 -> score += 45
                delta <= 2 -> score += 15
                else -> score -= 120
            }
        }
        return score
    }

    fun isAcceptable(score: Int): Boolean = score >= 150 || score >= 900
}

@Singleton
class HomeServerRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val okHttpClient: OkHttpClient,
    private val profileManager: ProfileManager
) {
    companion object {
        const val ADDON_ID = "home_server"
        const val ADDON_NAME = "Home Server"
        const val CONNECTION_KEY_NAME = "home_server_connection_v1"
        const val CATALOG_SOURCE_REF_PREFIX = "home_server_catalog|"
        private const val HOME_SERVER_TOKEN_KEY_ALIAS = "arvio_home_server_tokens_v1"
        private const val SECURE_TOKEN_PREFIX = "enc:v1:"
        private const val SOURCE_CACHE_MAX_ENTRIES = 128
        private const val SOURCE_CACHE_TTL_MS = 30L * 60L * 1000L

        fun catalogServerKey(connection: HomeServerConnection): String {
            return connection.serverId.ifBlank { connection.connectionId }.ifBlank {
                "${connection.serverKind.name}:${connection.serverUrl}"
            }
        }

        fun buildCatalogSourceRef(connection: HomeServerConnection, collection: HomeServerCollection): String {
            return CATALOG_SOURCE_REF_PREFIX +
                listOf(
                    catalogServerKey(connection),
                    collection.id,
                    collection.type
                ).joinToString("|") { urlEncodeStatic(it) }
        }

        fun parseCatalogSourceRef(sourceRef: String?): Triple<String, String, String>? {
            val value = sourceRef?.trim().orEmpty()
            if (!value.startsWith(CATALOG_SOURCE_REF_PREFIX, ignoreCase = true)) return null
            val parts = value.substring(CATALOG_SOURCE_REF_PREFIX.length).split("|")
            if (parts.size < 2) return null
            val serverKey = urlDecodeStatic(parts[0]).trim()
            val collectionId = urlDecodeStatic(parts[1]).trim()
            val collectionType = parts.getOrNull(2)?.let { urlDecodeStatic(it).trim() }.orEmpty()
            if (serverKey.isBlank() || collectionId.isBlank()) return null
            return Triple(serverKey, collectionId, collectionType)
        }

        private fun urlEncodeStatic(value: String): String = URLEncoder.encode(value, "UTF-8")
        private fun urlDecodeStatic(value: String): String = URLDecoder.decode(value, "UTF-8")
    }

    private val gson = Gson()
    private val jsonMediaType = "application/json; charset=utf-8".toMediaType()
    private data class CachedHomeServerSources(
        val sources: List<StreamSource>,
        val createdAtMs: Long
    )
    private data class PlexResourceConnection(
        val uri: String,
        val local: Boolean,
        val relay: Boolean
    )

    private data class PlexResourceDevice(
        val name: String,
        val product: String,
        val provides: String,
        val clientIdentifier: String,
        val accessToken: String,
        val owned: Boolean,
        val connections: List<PlexResourceConnection>
    ) {
        val isServer: Boolean
            get() = provides.split(',').any { it.trim().equals("server", ignoreCase = true) } ||
                product.contains("server", ignoreCase = true)
    }

    private val sourceCacheLock = Any()
    private val sourceCache = object : LinkedHashMap<String, CachedHomeServerSources>(64, 0.75f, true) {
        override fun removeEldestEntry(eldest: MutableMap.MutableEntry<String, CachedHomeServerSources>?): Boolean {
            return size > SOURCE_CACHE_MAX_ENTRIES
        }
    }

    val connections: Flow<List<HomeServerConnection>> = combine(
        profileManager.activeProfileId,
        context.settingsDataStore.data
    ) { profileId, prefs ->
        parseConnections(prefs[connectionKeyFor(profileId)])
    }.distinctUntilChanged()

    val connection: Flow<HomeServerConnection?> = connections
        .map { it.firstOrNull() }
        .distinctUntilChanged()

    suspend fun connect(
        rawUrl: String,
        username: String,
        password: String,
        displayName: String = ""
    ): Result<HomeServerConnection> =
        withContext(Dispatchers.IO) {
            runCatching {
                val serverUrl = normalizeServerUrl(rawUrl)
                val trimmedUsername = username.trim()
                val trimmedDisplayName = displayName.trim()
                require(serverUrl.isNotBlank()) { "Enter a valid server URL" }
                require(password.isNotBlank()) { "Enter a password or token" }

                val publicInfo = fetchPublicInfo(serverUrl)
                val detectedKind = publicInfo.serverKind
                    .takeUnless { it == HomeServerKind.UNKNOWN }
                    ?: detectServerKind(publicInfo.productName, publicInfo.serverName)
                if (detectedKind == HomeServerKind.PLEX) {
                    val connection = buildPlexConnection(
                        accountToken = password,
                        preferredServerUrl = serverUrl,
                        preferredUsername = trimmedUsername,
                        preferredInfo = publicInfo,
                        displayName = trimmedDisplayName
                    )
                    saveConnection(connection)
                    return@runCatching connection
                }

                require(trimmedUsername.isNotBlank()) { "Enter a username" }
                val auth = authenticate(serverUrl, trimmedUsername, password)
                val connectionShell = HomeServerConnection(
                    enabled = true,
                    connectionId = createConnectionId(serverUrl, detectedKind, auth.userId.ifBlank { trimmedUsername }),
                    serverUrl = serverUrl,
                    displayName = trimmedDisplayName,
                    serverName = publicInfo.serverName.ifBlank { auth.serverName }.ifBlank { "Home Server" },
                    serverKind = detectedKind,
                    serverId = auth.serverId.ifBlank { publicInfo.serverId },
                    userId = auth.userId,
                    userName = auth.userName.ifBlank { trimmedUsername },
                    accessToken = auth.accessToken,
                    accountToken = auth.accountToken,
                    lastConnectedAt = System.currentTimeMillis()
                )
                val connection = connectionShell.copy(collections = fetchCollections(connectionShell))
                saveConnection(connection)
                connection
            }
        }

    suspend fun connectPlexAccount(
        accountToken: String,
        preferredServerUrl: String = "",
        displayName: String = ""
    ): Result<HomeServerConnection> =
        withContext(Dispatchers.IO) {
            runCatching {
                val connection = buildPlexConnection(
                    accountToken = accountToken,
                    preferredServerUrl = preferredServerUrl,
                    preferredUsername = "",
                    preferredInfo = null,
                    displayName = displayName.trim()
                )
                saveConnection(connection)
                connection
            }
        }

    suspend fun testConnection(): Result<HomeServerConnection> = withContext(Dispatchers.IO) {
        testConnections().map { it.first() }
    }

    suspend fun testConnections(): Result<List<HomeServerConnection>> = withContext(Dispatchers.IO) {
        runCatching {
            val current = currentConnections()
            require(current.isNotEmpty()) { "No Home Server connected" }
            val refreshed = current.map { refreshConnection(it) }
            saveConnections(refreshed)
            refreshed
        }
    }

    suspend fun disconnect() {
        clearSourceCache()
        val profileId = profileManager.getProfileId()
        context.settingsDataStore.edit { prefs ->
            prefs.remove(connectionKeyFor(profileId))
        }
    }

    suspend fun startPlexPinAuth(): Result<PlexPinAuthSession> = withContext(Dispatchers.IO) {
        runCatching {
            val url = "https://plex.tv/api/v2/pins".toHttpUrlOrNull()
                ?.newBuilder()
                ?.addQueryParameter("strong", "true")
                ?.addQueryParameter("X-Plex-Client-Identifier", deviceId())
                ?.addQueryParameter("X-Plex-Product", "ARVIO")
                ?.build()
                ?.toString()
                ?: error("Invalid code sign-in URL")
            val request = Request.Builder()
                .url(url)
                .post(ByteArray(0).toRequestBody(null))
                .headers(plexPublicHeaders())
                .build()
            okHttpClient.newCall(request).execute().use { response ->
                val body = response.body?.string().orEmpty()
                if (!response.isSuccessful) {
                    error("Code sign in failed (${response.code})")
                }
                val json = JsonParser().parse(body).asJsonObjectOrNull() ?: JsonObject()
                val id = json.string("id").ifBlank { json.string("pinId") }
                val code = json.string("code")
                require(id.isNotBlank() && code.isNotBlank()) { "Server did not return an activation code" }
                PlexPinAuthSession(
                    id = id,
                    code = code,
                    verificationUrl = plexActivationUrl(code),
                    expiresIn = json.int("expiresIn") ?: json.int("expires_in") ?: 600,
                    interval = (json.int("interval") ?: 5).coerceIn(2, 15)
                )
            }
        }
    }

    suspend fun pollPlexPinAuth(pinId: String): Result<String?> = withContext(Dispatchers.IO) {
        runCatching {
            val url = "https://plex.tv/api/v2/pins/$pinId".toHttpUrlOrNull()
                ?.newBuilder()
                ?.addQueryParameter("X-Plex-Client-Identifier", deviceId())
                ?.build()
                ?.toString()
                ?: error("Invalid code sign-in URL")
            val request = Request.Builder()
                .url(url)
                .get()
                .headers(plexPublicHeaders())
                .build()
            okHttpClient.newCall(request).execute().use { response ->
                val body = response.body?.string().orEmpty()
                if (!response.isSuccessful) {
                    error("Code sign in polling failed (${response.code})")
                }
                val json = JsonParser().parse(body).asJsonObjectOrNull() ?: JsonObject()
                json.string("authToken")
                    .ifBlank { json.string("auth_token") }
                    .takeIf { it.isNotBlank() }
            }
        }
    }

    suspend fun currentConnection(): HomeServerConnection? {
        return currentConnections().firstOrNull()
    }

    suspend fun currentConnections(): List<HomeServerConnection> {
        val profileId = profileManager.getProfileId()
        return currentConnectionsForProfile(profileId, migratePlainTokens = true)
    }

    suspend fun hasUsableConnections(): Boolean = currentConnections().any { it.isUsable }

    suspend fun getCatalogCandidates(): List<HomeServerCatalogCandidate> = withContext(Dispatchers.IO) {
        currentConnections()
            .filter { it.isUsable }
            .flatMap { connection ->
                val libraryCandidates = connection.collections
                    .filter { it.enabled && it.id.isNotBlank() }
                    .map { collection -> connection.toCatalogCandidate(collection) }
                val serverCollectionCandidates = runCatching {
                    fetchServerCollectionCatalogs(connection)
                }.getOrDefault(emptyList())
                libraryCandidates + serverCollectionCandidates
            }
            .distinctBy { it.sourceRef }
    }

    suspend fun loadCatalogItems(
        sourceRef: String?,
        offset: Int,
        limit: Int
    ): HomeServerCatalogPage = withContext(Dispatchers.IO) {
        if (limit <= 0 || offset < 0) return@withContext HomeServerCatalogPage(emptyList(), hasMore = false)
        val parsed = parseCatalogSourceRef(sourceRef)
            ?: return@withContext HomeServerCatalogPage(emptyList(), hasMore = false)
        val (serverKey, collectionId, collectionType) = parsed
        val connection = currentConnections()
            .firstOrNull { it.isUsable && catalogServerKey(it) == serverKey }
            ?: return@withContext HomeServerCatalogPage(emptyList(), hasMore = false)
        runCatching {
            loadConnectionCatalogItems(connection, collectionId, collectionType, offset, limit)
        }.getOrDefault(HomeServerCatalogPage(emptyList(), hasMore = false))
    }

    suspend fun resolveMovieSources(
        imdbId: String?,
        title: String,
        year: Int?,
        tmdbId: Int?
    ): List<StreamSource> = withContext(Dispatchers.IO) {
        val connections = currentConnections().filter { it.isUsable }
        if (connections.isEmpty()) return@withContext emptyList()
        val cacheKey = sourceCacheKey(
            type = "movie",
            connections = connections,
            title = title,
            year = year,
            imdbId = imdbId,
            tmdbId = tmdbId,
            tvdbId = null,
            season = null,
            episode = null
        )
        getCachedSources(cacheKey)?.let { return@withContext it }

        val sources = connections
            .flatMap { connection ->
                runCatching {
                    val item = findBestMovie(connection, imdbId, title, year, tmdbId) ?: return@runCatching emptyList()
                    buildStreamSources(connection, item)
                }.getOrDefault(emptyList())
            }
            .distinctBy { "${it.addonId}|${it.source}|${it.url}" }
        putCachedSources(cacheKey, sources)
        sources
    }

    suspend fun resolveEpisodeSources(
        imdbId: String?,
        title: String,
        season: Int,
        episode: Int,
        tmdbId: Int?,
        tvdbId: Int?
    ): List<StreamSource> = withContext(Dispatchers.IO) {
        val connections = currentConnections().filter { it.isUsable }
        if (connections.isEmpty()) return@withContext emptyList()
        val cacheKey = sourceCacheKey(
            type = "episode",
            connections = connections,
            title = title,
            year = null,
            imdbId = imdbId,
            tmdbId = tmdbId,
            tvdbId = tvdbId,
            season = season,
            episode = episode
        )
        getCachedSources(cacheKey)?.let { return@withContext it }

        val sources = connections
            .flatMap { connection ->
                runCatching {
                    val series = findBestSeries(connection, imdbId, title, null, tmdbId, tvdbId)
                        ?: return@runCatching emptyList()
                    val episodeItem = findEpisode(connection, series.id, season, episode)
                        ?: findEpisodeBySearch(connection, title, season, episode, imdbId, tmdbId, tvdbId)
                        ?: return@runCatching emptyList()
                    buildStreamSources(connection, episodeItem)
                }.getOrDefault(emptyList())
            }
            .distinctBy { "${it.addonId}|${it.source}|${it.url}" }
        putCachedSources(cacheKey, sources)
        sources
    }

    private suspend fun saveConnection(connection: HomeServerConnection) {
        clearSourceCache()
        val existing = currentConnections()
        val key = connectionIdentity(connection)
        saveConnections(
            existing
                .filterNot {
                    connectionIdentity(it) == key ||
                        (connection.serverKind == it.serverKind &&
                            connection.serverId.isNotBlank() &&
                            connection.serverId == it.serverId)
                }
                .plus(connection)
        )
    }

    private suspend fun saveConnections(connections: List<HomeServerConnection>) {
        saveConnectionsForProfile(profileManager.getProfileId(), connections)
    }

    private suspend fun saveConnectionsForProfile(profileId: String, connections: List<HomeServerConnection>) {
        context.settingsDataStore.edit { prefs ->
            prefs[connectionKeyFor(profileId)] = gson.toJson(
                HomeServerProfileConfig(connections = connections.map { it.sanitized().withEncryptedTokens() })
            )
        }
    }

    private fun connectionKeyFor(profileId: String) =
        profileManager.profileStringKeyFor(profileId, CONNECTION_KEY_NAME)

    suspend fun exportCloudConnectionsJsonForProfile(profileId: String): String {
        val connections = currentConnectionsForProfile(profileId, migratePlainTokens = true)
        if (connections.isEmpty()) return ""
        return gson.toJson(HomeServerProfileConfig(connections = connections.map { it.sanitized() }))
    }

    suspend fun importCloudConnectionsJsonForProfile(profileId: String, json: String?) {
        if (json.isNullOrBlank()) {
            context.settingsDataStore.edit { prefs -> prefs.remove(connectionKeyFor(profileId)) }
            return
        }
        saveConnectionsForProfile(profileId, parseConnections(json))
    }

    private suspend fun currentConnectionsForProfile(
        profileId: String,
        migratePlainTokens: Boolean
    ): List<HomeServerConnection> {
        val raw = context.settingsDataStore.data.first()[connectionKeyFor(profileId)]
        val connections = parseConnections(raw)
        if (migratePlainTokens && raw.hasPlainHomeServerTokens()) {
            saveConnectionsForProfile(profileId, connections)
        }
        return connections
    }

    private fun parseConnection(json: String?): HomeServerConnection? {
        return parseConnections(json).firstOrNull()
    }

    private fun parseConnections(json: String?): List<HomeServerConnection> {
        if (json.isNullOrBlank()) return emptyList()
        return runCatching {
            val root = JsonParser().parse(json)
            val connections = when {
                root.isJsonObject && root.asJsonObject.has("connections") -> {
                    gson.fromJson(root, HomeServerProfileConfig::class.java).connections
                }
                root.isJsonArray -> {
                    val type = TypeToken.getParameterized(List::class.java, HomeServerConnection::class.java).type
                    gson.fromJson<List<HomeServerConnection>>(root, type)
                }
                root.isJsonObject -> listOf(gson.fromJson(root, HomeServerConnection::class.java))
                else -> emptyList()
            }
            connections
                .map { it.sanitized() }
                .map { it.withDecryptedTokens() }
                .filter { it.serverUrl.isNotBlank() || it.accessToken.isNotBlank() }
                .distinctBy { connectionIdentity(it) }
        }.getOrDefault(emptyList())
    }

    private fun String?.hasPlainHomeServerTokens(): Boolean {
        if (isNullOrBlank()) return false
        return parseConnections(this).any { connection ->
            val rawAccessToken = tokenValueFromRawJson(this, connection.connectionId, "accessToken")
            val rawAccountToken = tokenValueFromRawJson(this, connection.connectionId, "accountToken")
            rawAccessToken.isPlainToken() || rawAccountToken.isPlainToken()
        }
    }

    private fun tokenValueFromRawJson(json: String, connectionId: String, fieldName: String): String {
        return runCatching {
            val root = JsonParser().parse(json)
            val candidates = when {
                root.isJsonObject && root.asJsonObject.has("connections") -> root.asJsonObject
                    .getAsJsonArray("connections")
                    .toList()
                root.isJsonArray -> root.asJsonArray.toList()
                root.isJsonObject -> listOf(root)
                else -> emptyList()
            }
            candidates
                .mapNotNull { it.asJsonObjectOrNull() }
                .firstOrNull { obj ->
                    obj.string("connectionId").ifBlank {
                        createConnectionId(
                            obj.string("serverUrl"),
                            runCatching { HomeServerKind.valueOf(obj.string("serverKind")) }.getOrDefault(HomeServerKind.UNKNOWN),
                            obj.string("userId").ifBlank { obj.string("userName") }
                        )
                    } == connectionId
                }
                ?.string(fieldName)
                .orEmpty()
        }.getOrDefault("")
    }

    private fun String.isPlainToken(): Boolean =
        isNotBlank() && !startsWith(SECURE_TOKEN_PREFIX)

    private fun HomeServerConnection.sanitized(): HomeServerConnection {
        return HomeServerConnection(
            enabled = enabled,
            connectionId = connectionId.orEmpty().ifBlank {
                createConnectionId(serverUrl.orEmpty(), serverKind, userId.orEmpty().ifBlank { userName.orEmpty() })
            },
            serverUrl = normalizeServerUrl(serverUrl.orEmpty()),
            displayName = displayName.orEmpty(),
            serverName = serverName.orEmpty(),
            serverKind = serverKind,
            serverId = serverId.orEmpty(),
            userId = userId.orEmpty(),
            userName = userName.orEmpty(),
            accessToken = accessToken.orEmpty(),
            accountToken = accountToken.orEmpty(),
            collections = collections.orEmpty().map {
                HomeServerCollection(
                    id = it.id.orEmpty(),
                    name = it.name.orEmpty(),
                    type = it.type.orEmpty(),
                    enabled = it.enabled
                )
            },
            lastConnectedAt = lastConnectedAt
        )
    }

    private fun HomeServerConnection.withEncryptedTokens(): HomeServerConnection =
        copy(
            accessToken = encryptToken(accessToken),
            accountToken = encryptToken(accountToken)
        )

    private fun HomeServerConnection.withDecryptedTokens(): HomeServerConnection =
        copy(
            accessToken = decryptToken(accessToken),
            accountToken = decryptToken(accountToken)
        )

    private fun encryptToken(value: String): String {
        val trimmed = value.trim()
        if (trimmed.isBlank() || trimmed.startsWith(SECURE_TOKEN_PREFIX)) return trimmed
        return SecureStorage.encrypt(trimmed, HOME_SERVER_TOKEN_KEY_ALIAS)
    }

    private fun decryptToken(value: String): String {
        val trimmed = value.trim()
        if (trimmed.isBlank()) return ""
        return SecureStorage.decrypt(trimmed, HOME_SERVER_TOKEN_KEY_ALIAS).orEmpty()
    }

    private fun createConnectionId(serverUrl: String, kind: HomeServerKind, userIdentity: String): String {
        return "${kind.name}:${serverUrl.trimEnd('/').lowercase(Locale.US)}:${userIdentity.lowercase(Locale.US)}"
            .replace(HomeServerRepositoryRegexes.CONNECTION_ID_SANITIZER_REGEX, "_")
    }

    private fun connectionIdentity(connection: HomeServerConnection): String {
        return connection.connectionId.ifBlank {
            createConnectionId(
                connection.serverUrl,
                connection.serverKind,
                connection.userId.ifBlank { connection.userName }
            )
        }
    }

    private fun sourceCacheKey(
        type: String,
        connections: List<HomeServerConnection>,
        title: String,
        year: Int?,
        imdbId: String?,
        tmdbId: Int?,
        tvdbId: Int?,
        season: Int?,
        episode: Int?
    ): String {
        val connectionSignature = connections.joinToString("|") { connection ->
            val enabledCollections = connection.collections
                .filter { it.enabled }
                .joinToString(",") { it.id }
            "${connectionIdentity(connection)}:${connection.lastConnectedAt}:$enabledCollections"
        }
        return listOf(
            type,
            connectionSignature,
            imdbId?.trim().orEmpty().lowercase(Locale.US),
            tmdbId?.toString().orEmpty(),
            tvdbId?.toString().orEmpty(),
            HomeServerMatcher.normalizeTitle(title),
            year?.toString().orEmpty(),
            season?.toString().orEmpty(),
            episode?.toString().orEmpty()
        ).joinToString("|")
    }

    private fun getCachedSources(key: String): List<StreamSource>? = synchronized(sourceCacheLock) {
        val cached = sourceCache[key] ?: return@synchronized null
        val isFresh = System.currentTimeMillis() - cached.createdAtMs < SOURCE_CACHE_TTL_MS
        if (isFresh) {
            cached.sources
        } else {
            sourceCache.remove(key)
            null
        }
    }

    private fun putCachedSources(key: String, sources: List<StreamSource>) {
        if (sources.isEmpty()) return
        synchronized(sourceCacheLock) {
            sourceCache[key] = CachedHomeServerSources(sources, System.currentTimeMillis())
        }
    }

    private fun clearSourceCache() {
        synchronized(sourceCacheLock) {
            sourceCache.clear()
        }
    }

    private fun normalizeServerUrl(rawUrl: String): String {
        val trimmed = rawUrl.trim().trimEnd('/')
        if (trimmed.isBlank()) return ""
        val withScheme = if (trimmed.startsWith("http://", true) || trimmed.startsWith("https://", true)) {
            trimmed
        } else {
            "http://$trimmed"
        }
        return withScheme.toHttpUrlOrNull()?.toString()?.trimEnd('/').orEmpty()
    }

    private fun detectServerKind(productName: String, serverName: String): HomeServerKind {
        val text = "$productName $serverName".lowercase(Locale.US)
        return when {
            "plex" in text -> HomeServerKind.PLEX
            "emby" in text -> HomeServerKind.EMBY
            "jellyfin" in text -> HomeServerKind.JELLYFIN
            else -> HomeServerKind.UNKNOWN
        }
    }

    private fun deviceId(): String {
        return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
            ?.takeIf { it.isNotBlank() }
            ?: "arvio-android"
    }

    private fun authHeader(token: String? = null): String {
        val base = "MediaBrowser Client=\"ARVIO\", Device=\"Android\", DeviceId=\"${deviceId()}\", Version=\"${BuildConfig.VERSION_NAME}\""
        return if (token.isNullOrBlank()) base else "$base, Token=\"$token\""
    }

    private fun plexPublicHeaders(): Headers = Headers.Builder()
        .add("Accept", "application/json")
        .add("User-Agent", "ARVIO/${BuildConfig.VERSION_NAME}")
        .add("X-Plex-Client-Identifier", deviceId())
        .add("X-Plex-Product", "ARVIO")
        .add("X-Plex-Version", BuildConfig.VERSION_NAME)
        .add("X-Plex-Device", "Android")
        .add("X-Plex-Platform", "Android")
        .build()

    private fun plexHeaders(token: String? = null): Map<String, String> = buildMap {
        put("Accept", "application/json")
        put("User-Agent", "ARVIO/${BuildConfig.VERSION_NAME}")
        put("X-Plex-Client-Identifier", deviceId())
        put("X-Plex-Product", "ARVIO")
        put("X-Plex-Version", BuildConfig.VERSION_NAME)
        put("X-Plex-Device", "Android")
        put("X-Plex-Platform", "Android")
        token?.takeIf { it.isNotBlank() }?.let { put("X-Plex-Token", it) }
    }

    private fun plexActivationUrl(code: String): String {
        val contextProductKey = "context[device][product]"
        return "https://app.plex.tv/auth".toHttpUrlOrNull()
            ?.newBuilder()
            ?.fragment(
                "!?" + listOf(
                    "clientID=${deviceId()}",
                    "code=$code",
                    "$contextProductKey=ARVIO"
                ).joinToString("&")
            )
            ?.build()
            ?.toString()
            ?: "https://app.plex.tv/auth"
    }

    private fun requestBuilder(url: String, connection: HomeServerConnection? = null): Request.Builder {
        val builder = Request.Builder()
            .url(url)
            .header("Accept", "application/json")
            .header("User-Agent", "ARVIO/${BuildConfig.VERSION_NAME}")
        if (connection?.serverKind == HomeServerKind.PLEX) {
            plexHeaders(connection.accessToken).forEach { (key, value) -> builder.header(key, value) }
        } else {
            builder.header("X-Emby-Authorization", authHeader(connection?.accessToken))
            if (connection != null) {
                builder.header("X-Emby-Token", connection.accessToken)
            }
        }
        return builder
    }

    private fun playbackHeaders(connection: HomeServerConnection): Map<String, String> {
        if (connection.serverKind == HomeServerKind.PLEX) return plexHeaders(connection.accessToken)
        return mapOf(
            "User-Agent" to "ARVIO/${BuildConfig.VERSION_NAME}",
            "X-Emby-Authorization" to authHeader(connection.accessToken),
            "X-Emby-Token" to connection.accessToken
        )
    }

    private fun buildUrl(
        baseUrl: String,
        path: String,
        query: Map<String, String?> = emptyMap()
    ): String {
        val base = baseUrl.toHttpUrlOrNull() ?: error("Invalid server URL")
        val builder = base.newBuilder()
        path.trim('/').split('/').filter { it.isNotBlank() }.forEach { builder.addPathSegment(it) }
        query.forEach { (key, value) ->
            if (!value.isNullOrBlank()) builder.addQueryParameter(key, value)
        }
        return builder.build().toString()
    }

    private fun absoluteUrl(baseUrl: String, pathOrUrl: String): String {
        if (pathOrUrl.startsWith("http://", true) || pathOrUrl.startsWith("https://", true)) return pathOrUrl
        val base = baseUrl.toHttpUrlOrNull() ?: return pathOrUrl
        val builder = base.newBuilder()
        pathOrUrl.substringBefore('?').trim('/').split('/').filter { it.isNotBlank() }.forEach {
            builder.addPathSegment(it)
        }
        val query = pathOrUrl.substringAfter('?', missingDelimiterValue = "")
        if (query.isNotBlank()) {
            query.split('&').forEach { part ->
                val key = part.substringBefore('=')
                val value = part.substringAfter('=', missingDelimiterValue = "")
                if (key.isNotBlank()) builder.addEncodedQueryParameter(key, value)
            }
        }
        return builder.build().toString()
    }

    private fun Request.Builder.headersWith(headers: Headers): Request.Builder = apply {
        for (index in 0 until headers.size) {
            header(headers.name(index), headers.value(index))
        }
    }

    private fun getJson(url: String, connection: HomeServerConnection? = null): JsonObject {
        val request = requestBuilder(url, connection).get().build()
        okHttpClient.newCall(request).execute().use { response ->
            val body = response.body?.string().orEmpty()
            if (!response.isSuccessful) {
                error("Server request failed (${response.code})")
            }
            return JsonParser().parse(body).asJsonObjectOrNull() ?: JsonObject()
        }
    }

    private fun getText(url: String, connection: HomeServerConnection? = null): String {
        val request = requestBuilder(url, connection).get().build()
        okHttpClient.newCall(request).execute().use { response ->
            val body = response.body?.string().orEmpty()
            if (!response.isSuccessful) {
                error("Server request failed (${response.code})")
            }
            return body
        }
    }

    private fun postJson(url: String, bodyJson: JsonObject, connection: HomeServerConnection? = null): JsonObject {
        val request = requestBuilder(url, connection)
            .post(gson.toJson(bodyJson).toRequestBody(jsonMediaType))
            .header("Content-Type", "application/json")
            .build()
        okHttpClient.newCall(request).execute().use { response ->
            val body = response.body?.string().orEmpty()
            if (!response.isSuccessful) {
                error("Server sign in failed (${response.code})")
            }
            return JsonParser().parse(body).asJsonObjectOrNull() ?: JsonObject()
        }
    }

    private fun fetchPublicInfo(serverUrl: String): ServerInfo {
        val info = runCatching { getJson(buildUrl(serverUrl, "/System/Info/Public")) }.getOrNull()
        if (info != null && info.entrySet().isNotEmpty()) {
            return ServerInfo(
                serverName = info.string("ServerName"),
                serverId = info.string("Id"),
                productName = info.string("ProductName"),
                serverKind = detectServerKind(info.string("ProductName"), info.string("ServerName"))
            )
        }

        val plexIdentity = runCatching { getText(buildUrl(serverUrl, "/identity")) }.getOrNull()
        val (plexName, plexId) = parsePlexIdentity(plexIdentity.orEmpty())
        return ServerInfo(
            serverName = plexName,
            serverId = plexId,
            productName = if (plexId.isNotBlank() || plexIdentity?.contains("MediaContainer") == true) "Media Server" else "",
            serverKind = if (plexId.isNotBlank() || plexIdentity?.contains("MediaContainer") == true) HomeServerKind.PLEX else HomeServerKind.UNKNOWN
        )
    }

    private fun fetchSystemInfo(connection: HomeServerConnection): ServerInfo {
        if (connection.serverKind == HomeServerKind.PLEX) {
            val identity = getText(buildUrl(connection.serverUrl, "/identity"), connection)
            val (identityName, identityId) = parsePlexIdentity(identity)
            return ServerInfo(
                serverName = identityName.ifBlank { connection.serverName },
                serverId = identityId.ifBlank { connection.serverId },
                productName = "Media Server",
                serverKind = HomeServerKind.PLEX
            )
        }
        val info = getJson(buildUrl(connection.serverUrl, "/System/Info"), connection)
        return ServerInfo(
            serverName = info.string("ServerName"),
            serverId = info.string("Id"),
            productName = info.string("ProductName"),
            serverKind = detectServerKind(info.string("ProductName"), info.string("ServerName"))
        )
    }

    private fun authenticate(serverUrl: String, username: String, password: String): AuthResponse {
        val body = JsonObject().apply {
            addProperty("Username", username)
            addProperty("Pw", password)
            addProperty("Password", password)
        }
        val response = postJson(buildUrl(serverUrl, "/Users/AuthenticateByName"), body)
        val user = response.obj("User")
        return AuthResponse(
            accessToken = response.string("AccessToken"),
            serverId = response.string("ServerId"),
            serverName = user?.string("ServerName").orEmpty(),
            userId = user?.string("Id").orEmpty(),
            userName = user?.string("Name").orEmpty()
        ).also {
            require(it.accessToken.isNotBlank() && it.userId.isNotBlank()) {
                "Server sign in did not return a playable account"
            }
        }
    }

    private fun validatePlexAccount(token: String): String {
        val request = Request.Builder()
            .url("https://plex.tv/api/v2/user")
            .header("Accept", "application/json")
            .header("X-Plex-Token", token)
            .build()
        return runCatching {
            okHttpClient.newCall(request).execute().use { response ->
                if (!response.isSuccessful) return@use ""
                val body = response.body?.string().orEmpty()
                val json = JsonParser().parse(body).asJsonObjectOrNull() ?: return@use ""
                json.string("friendlyName").ifBlank { json.string("username") }.ifBlank { json.string("title") }
            }
        }.getOrDefault("")
    }

    private fun buildPlexConnection(
        accountToken: String,
        preferredServerUrl: String,
        preferredUsername: String,
        preferredInfo: ServerInfo?,
        displayName: String = ""
    ): HomeServerConnection {
        val trimmedAccountToken = accountToken.trim()
        val trimmedDisplayName = displayName.trim()
        require(trimmedAccountToken.isNotBlank()) { "Missing account token" }

        val normalizedPreferredUrl = normalizeServerUrl(preferredServerUrl)
        val preferredIdentity = preferredInfo?.takeIf { it.serverId.isNotBlank() }
            ?: normalizedPreferredUrl.takeIf { it.isNotBlank() }?.let { url ->
                runCatching {
                    fetchSystemInfo(
                        HomeServerConnection(
                            serverUrl = url,
                            serverKind = HomeServerKind.PLEX,
                            accessToken = trimmedAccountToken,
                            accountToken = trimmedAccountToken,
                            userId = "plex",
                            userName = preferredUsername.ifBlank { "Account" }
                        )
                    )
                }.getOrNull()
            }
        val accountName = validatePlexAccount(trimmedAccountToken)
            .ifBlank { preferredUsername.ifBlank { "Account" } }
        val resources = fetchPlexResources(trimmedAccountToken)
        val targetDevice = selectPlexResourceDevice(
            resources = resources,
            preferredServerId = preferredIdentity?.serverId.orEmpty(),
            preferredServerUrl = normalizedPreferredUrl
        )
        val targetServerId = targetDevice?.clientIdentifier
            ?.ifBlank { preferredIdentity?.serverId.orEmpty() }
            ?: preferredIdentity?.serverId.orEmpty()
        val serverToken = targetDevice?.accessToken
            ?.takeIf { it.isNotBlank() }
            ?: resolvePlexServerToken(trimmedAccountToken, targetServerId)
                .takeIf { it.isNotBlank() }
            ?: trimmedAccountToken
        val candidateUrls = plexCandidateServerUrls(normalizedPreferredUrl, targetDevice)
        require(candidateUrls.isNotEmpty()) { "No reachable server URL found for this account" }

        var lastError: Throwable? = null
        candidateUrls.forEach { candidateUrl ->
            val candidate = HomeServerConnection(
                enabled = true,
                connectionId = "",
                serverUrl = candidateUrl,
                displayName = trimmedDisplayName,
                serverName = targetDevice?.name.orEmpty().ifBlank { preferredIdentity?.serverName.orEmpty() },
                serverKind = HomeServerKind.PLEX,
                serverId = targetServerId,
                userId = "plex",
                userName = accountName,
                accessToken = serverToken,
                accountToken = trimmedAccountToken,
                lastConnectedAt = System.currentTimeMillis()
            )
            val info = runCatching { fetchSystemInfo(candidate) }
                .getOrElse { error ->
                    lastError = error
                    null
                }
                ?: return@forEach
            val expectedServerId = targetServerId.ifBlank { preferredIdentity?.serverId.orEmpty() }
            if (expectedServerId.isNotBlank() && info.serverId.isNotBlank() && expectedServerId != info.serverId) {
                lastError = IllegalStateException("Server identity did not match the selected account server")
                return@forEach
            }
            val shell = candidate.copy(
                connectionId = createConnectionId(
                    candidateUrl,
                    HomeServerKind.PLEX,
                    info.serverId.ifBlank { accountName }
                ),
                serverName = info.serverName.ifBlank { candidate.serverName.ifBlank { "Home Server" } },
                displayName = candidate.displayName,
                serverId = info.serverId.ifBlank { candidate.serverId },
                lastConnectedAt = System.currentTimeMillis()
            )
            val collections = runCatching { fetchCollections(shell) }
                .getOrElse { error ->
                    lastError = error
                    emptyList()
                }
            if (collections.isNotEmpty()) {
                return shell.copy(collections = collections)
            }
        }

        val message = lastError?.message?.takeIf { it.isNotBlank() }
        error(message ?: "No accessible libraries found for this account")
    }

    private fun fetchPlexResources(accountToken: String): List<PlexResourceDevice> {
        val url = "https://plex.tv/api/resources".toHttpUrlOrNull()
            ?.newBuilder()
            ?.addQueryParameter("includeHttps", "1")
            ?.addQueryParameter("includeRelay", "1")
            ?.addQueryParameter("X-Plex-Token", accountToken)
            ?.build()
            ?.toString()
            ?: return emptyList()
        val request = Request.Builder()
            .url(url)
            .headersWith(plexPublicHeaders())
            .header("Accept", "application/xml")
            .header("X-Plex-Token", accountToken)
            .build()
        return runCatching {
            okHttpClient.newCall(request).execute().use { response ->
                if (!response.isSuccessful) return@use emptyList()
                parsePlexResourcesXml(response.body?.string().orEmpty())
            }
        }.getOrDefault(emptyList())
    }

    private fun parsePlexResourcesXml(xml: String): List<PlexResourceDevice> {
        return HomeServerRepositoryRegexes.PLEX_DEVICE_REGEX.findAll(xml)
            .map { match ->
                val attrs = match.groupValues.getOrNull(1).orEmpty()
                    .ifBlank { match.groupValues.getOrNull(3).orEmpty() }
                val body = match.groupValues.getOrNull(2).orEmpty()
                PlexResourceDevice(
                    name = attrs.xmlAttribute("name"),
                    product = attrs.xmlAttribute("product"),
                    provides = attrs.xmlAttribute("provides"),
                    clientIdentifier = attrs.xmlAttribute("clientIdentifier"),
                    accessToken = attrs.xmlAttribute("accessToken"),
                    owned = attrs.xmlBooleanAttribute("owned"),
                    connections = HomeServerRepositoryRegexes.PLEX_CONNECTION_REGEX.findAll(body)
                        .map { connection ->
                            val connectionAttrs = connection.groupValues.getOrNull(1).orEmpty()
                            PlexResourceConnection(
                                uri = normalizeServerUrl(connectionAttrs.xmlAttribute("uri")),
                                local = connectionAttrs.xmlBooleanAttribute("local"),
                                relay = connectionAttrs.xmlBooleanAttribute("relay")
                            )
                        }
                        .filter { it.uri.isNotBlank() }
                        .distinctBy { it.uri.lowercase(Locale.US) }
                        .toList()
                )
            }
            .filter { it.isServer && it.clientIdentifier.isNotBlank() }
            .toList()
    }

    private fun selectPlexResourceDevice(
        resources: List<PlexResourceDevice>,
        preferredServerId: String,
        preferredServerUrl: String
    ): PlexResourceDevice? {
        if (resources.isEmpty()) return null
        preferredServerId.takeIf { it.isNotBlank() }?.let { id ->
            resources.firstOrNull { it.clientIdentifier == id }?.let { return it }
        }
        preferredServerUrl.takeIf { it.isNotBlank() }?.let { url ->
            resources.firstOrNull { device ->
                device.connections.any { sameServerEndpoint(it.uri, url) }
            }?.let { return it }
        }
        resources.singleOrNull { it.accessToken.isNotBlank() }?.let { return it }
        return resources
            .filter { it.accessToken.isNotBlank() }
            .sortedWith(
                compareByDescending<PlexResourceDevice> { it.owned }
                    .thenByDescending { it.connections.isNotEmpty() }
            )
            .firstOrNull()
    }

    private fun plexCandidateServerUrls(
        preferredServerUrl: String,
        device: PlexResourceDevice?
    ): List<String> {
        return buildList {
            preferredServerUrl.takeIf { it.isNotBlank() }?.let { add(it) }
            device?.connections
                ?.sortedWith(
                    compareByDescending<PlexResourceConnection> { it.local && !it.relay }
                        .thenBy { it.relay }
                        .thenByDescending { it.uri.startsWith("https://", ignoreCase = true) }
                )
                ?.forEach { connection -> add(connection.uri) }
        }
            .map { normalizeServerUrl(it) }
            .filter { it.isNotBlank() }
            .distinctBy { it.lowercase(Locale.US) }
    }

    private fun sameServerEndpoint(left: String, right: String): Boolean {
        val leftUrl = left.toHttpUrlOrNull() ?: return false
        val rightUrl = right.toHttpUrlOrNull() ?: return false
        return leftUrl.host.equals(rightUrl.host, ignoreCase = true) && leftUrl.port == rightUrl.port
    }

    private fun resolvePlexServerToken(accountToken: String, serverId: String): String {
        if (serverId.isBlank()) return ""
        return fetchPlexResources(accountToken)
            .firstOrNull { it.clientIdentifier == serverId }
            ?.accessToken
            .orEmpty()
    }

    private fun refreshConnection(connection: HomeServerConnection): HomeServerConnection {
        require(connection.isUsable) { "${connection.serverName.ifBlank { "Home Server" }} is disabled or incomplete" }
        if (connection.serverKind == HomeServerKind.PLEX) {
            val accountToken = connection.accountToken.ifBlank { connection.accessToken }
            val refreshed = buildPlexConnection(
                accountToken = accountToken,
                preferredServerUrl = connection.serverUrl,
                preferredUsername = connection.userName,
                preferredInfo = ServerInfo(
                    serverName = connection.serverName,
                    serverId = connection.serverId,
                    productName = "Media Server",
                    serverKind = HomeServerKind.PLEX
                ),
                displayName = connection.displayName
            )
            return refreshed.copy(
                enabled = connection.enabled,
                connectionId = connection.connectionId.ifBlank { refreshed.connectionId },
                displayName = connection.displayName.ifBlank { refreshed.displayName },
                collections = mergeCollectionStates(refreshed.collections, connection.collections)
            )
        }
        val info = fetchSystemInfo(connection)
        val kind = info.serverKind.takeUnless { it == HomeServerKind.UNKNOWN }
            ?: detectServerKind(info.productName, info.serverName).takeUnless { it == HomeServerKind.UNKNOWN }
            ?: connection.serverKind
        val shell = connection.copy(
            serverName = info.serverName.ifBlank { connection.serverName },
            serverKind = kind,
            serverId = info.serverId.ifBlank { connection.serverId },
            lastConnectedAt = System.currentTimeMillis()
        )
        return shell.copy(collections = fetchCollections(shell).ifEmpty { connection.collections })
    }

    private fun mergeCollectionStates(
        refreshed: List<HomeServerCollection>,
        previous: List<HomeServerCollection>
    ): List<HomeServerCollection> {
        val previousById = previous.associateBy { it.id }
        return refreshed.map { collection ->
            collection.copy(enabled = previousById[collection.id]?.enabled ?: collection.enabled)
        }
    }

    private fun fetchCollections(connection: HomeServerConnection): List<HomeServerCollection> {
        if (connection.serverKind == HomeServerKind.PLEX) {
            val response = getJson(buildUrl(connection.serverUrl, "/library/sections"), connection)
            return response.array("MediaContainer", "Directory")
                .mapNotNull { it.asJsonObjectOrNull() }
                .mapNotNull { directory ->
                    val id = directory.string("key")
                    if (id.isBlank()) return@mapNotNull null
                    HomeServerCollection(
                        id = id,
                        name = directory.string("title").ifBlank { directory.string("name") }.ifBlank { "Library $id" },
                        type = directory.string("type"),
                        enabled = true
                    )
                }
        }

        val response = getJson(buildUrl(connection.serverUrl, "/Users/${connection.userId}/Views"), connection)
        return response.itemsArray()
            .mapNotNull { it.asJsonObjectOrNull() }
            .mapNotNull { item ->
                val id = item.string("Id")
                if (id.isBlank()) return@mapNotNull null
                HomeServerCollection(
                    id = id,
                    name = item.string("Name").ifBlank { "Library" },
                    type = item.string("CollectionType"),
                    enabled = true
                )
            }
    }

    private fun HomeServerConnection.toCatalogCandidate(collection: HomeServerCollection): HomeServerCatalogCandidate {
        val connectionLabel = displayLabel()
        val collectionLabel = collection.name.ifBlank { "Library" }
        val title = if (collectionLabel.contains(connectionLabel, ignoreCase = true)) {
            collectionLabel
        } else {
            "$connectionLabel - $collectionLabel"
        }
        return HomeServerCatalogCandidate(
            title = title,
            sourceRef = buildCatalogSourceRef(this, collection),
            serverName = connectionLabel,
            collectionName = collection.name,
            collectionType = collection.type
        )
    }

    private fun fetchServerCollectionCatalogs(connection: HomeServerConnection): List<HomeServerCatalogCandidate> {
        return when (connection.serverKind) {
            HomeServerKind.PLEX -> fetchPlexCollectionCatalogs(connection)
            HomeServerKind.JELLYFIN,
            HomeServerKind.EMBY -> fetchJellyfinCollectionCatalogs(connection)
            HomeServerKind.UNKNOWN -> emptyList()
        }
    }

    private fun fetchPlexCollectionCatalogs(connection: HomeServerConnection): List<HomeServerCatalogCandidate> {
        return connection.collections
            .filter { it.enabled && it.id.isNotBlank() }
            .flatMap { library ->
                runCatching {
                    getJson(
                        buildUrl(
                            connection.serverUrl,
                            "/library/sections/${library.id}/collections",
                            mapOf(
                                "includeGuids" to "1",
                                "X-Plex-Container-Start" to "0",
                                "X-Plex-Container-Size" to "100"
                            )
                        ),
                        connection
                    ).metadataItems(connection.serverKind)
                        .mapNotNull { item ->
                            val collectionId = item.id.takeIf { it.isNotBlank() } ?: return@mapNotNull null
                            val collection = HomeServerCollection(
                                id = "collection:${library.id}:$collectionId",
                                name = item.name,
                                type = "collection",
                                enabled = true
                            )
                            connection.toCatalogCandidate(collection)
                        }
                }.getOrDefault(emptyList())
            }
    }

    private fun fetchJellyfinCollectionCatalogs(connection: HomeServerConnection): List<HomeServerCatalogCandidate> {
        val response = getJson(
            buildUrl(
                connection.serverUrl,
                "/Users/${connection.userId}/Items",
                mapOf(
                    "Recursive" to "true",
                    "IncludeItemTypes" to "BoxSet",
                    "Fields" to itemFields(),
                    "StartIndex" to "0",
                    "Limit" to "100"
                )
            ),
            connection
        )
        return response.items()
            .mapNotNull { item ->
                val id = item.id.takeIf { it.isNotBlank() } ?: return@mapNotNull null
                val collection = HomeServerCollection(
                    id = "collection:$id",
                    name = item.name,
                    type = "collection",
                    enabled = true
                )
                connection.toCatalogCandidate(collection)
            }
    }

    private fun loadConnectionCatalogItems(
        connection: HomeServerConnection,
        collectionId: String,
        collectionType: String,
        offset: Int,
        limit: Int
    ): HomeServerCatalogPage {
        return if (connection.serverKind == HomeServerKind.PLEX) {
            loadPlexCatalogItems(connection, collectionId, collectionType, offset, limit)
        } else {
            loadJellyfinCatalogItems(connection, collectionId, offset, limit)
        }
    }

    private fun loadPlexCatalogItems(
        connection: HomeServerConnection,
        collectionId: String,
        collectionType: String,
        offset: Int,
        limit: Int
    ): HomeServerCatalogPage {
        val collectionParts = collectionId.split(":")
        val path = if (collectionParts.firstOrNull() == "collection" && collectionParts.size >= 3) {
            "/library/metadata/${collectionParts[2]}/children"
        } else {
            "/library/sections/$collectionId/all"
        }
        val plexType = when (collectionType.lowercase(Locale.US)) {
            "movie", "movies" -> "1"
            "show", "shows", "series", "tvshows" -> "2"
            else -> null
        }
        val response = getJson(
            buildUrl(
                connection.serverUrl,
                path,
                mapOf(
                    "type" to plexType,
                    "includeGuids" to "1",
                    "X-Plex-Container-Start" to offset.toString(),
                    "X-Plex-Container-Size" to limit.toString()
                )
            ),
            connection
        )
        val container = response.obj("MediaContainer")
        val total = container?.int("totalSize")
            ?: container?.int("size")
            ?: response.metadataItems(connection.serverKind).size
        val items = response.metadataItems(connection.serverKind)
            .mapNotNull { it.toCatalogItem() }
        return HomeServerCatalogPage(
            items = items,
            hasMore = offset + items.size < total
        )
    }

    private fun loadJellyfinCatalogItems(
        connection: HomeServerConnection,
        collectionId: String,
        offset: Int,
        limit: Int
    ): HomeServerCatalogPage {
        val parentId = collectionId.removePrefix("collection:").trim()
        val response = getJson(
            buildUrl(
                connection.serverUrl,
                "/Users/${connection.userId}/Items",
                mapOf(
                    "ParentId" to parentId,
                    "Recursive" to "true",
                    "IncludeItemTypes" to "Movie,Series",
                    "Fields" to itemFields(),
                    "SortBy" to "SortName",
                    "SortOrder" to "Ascending",
                    "StartIndex" to offset.toString(),
                    "Limit" to limit.toString()
                )
            ),
            connection
        )
        val total = response.int("TotalRecordCount") ?: response.items().size
        val items = response.items().mapNotNull { it.toCatalogItem() }
        return HomeServerCatalogPage(
            items = items,
            hasMore = offset + items.size < total
        )
    }

    private fun findBestMovie(
        connection: HomeServerConnection,
        imdbId: String?,
        title: String,
        year: Int?,
        tmdbId: Int?
    ): HomeServerItem? {
        val candidates = linkedMapOf<String, HomeServerItem>()
        providerQueries(imdbId, tmdbId, null).forEach { providerId ->
            queryItems(
                connection,
                itemTypes = "Movie",
                query = mapOf("AnyProviderIdEquals" to providerId, "Limit" to "10")
            ).forEach { candidates[it.id] = it }
        }
        val bestById = bestCandidate(candidates.values, title, year, imdbId, tmdbId, null)
        if (bestById != null && HomeServerMatcher.score(title, year, imdbId, tmdbId, null, bestById.info()) >= 900) {
            return bestById
        }

        if (title.isNotBlank()) {
            queryItems(
                connection,
                itemTypes = "Movie",
                query = mapOf("SearchTerm" to title, "Limit" to "25")
            ).forEach { candidates[it.id] = it }
        }
        return bestCandidate(candidates.values, title, year, imdbId, tmdbId, null)
    }

    private fun findBestSeries(
        connection: HomeServerConnection,
        imdbId: String?,
        title: String,
        year: Int?,
        tmdbId: Int?,
        tvdbId: Int?
    ): HomeServerItem? {
        val candidates = linkedMapOf<String, HomeServerItem>()
        providerQueries(imdbId, tmdbId, tvdbId).forEach { providerId ->
            queryItems(
                connection,
                itemTypes = "Series",
                query = mapOf("AnyProviderIdEquals" to providerId, "Limit" to "10")
            ).forEach { candidates[it.id] = it }
        }
        val bestById = bestCandidate(candidates.values, title, year, imdbId, tmdbId, tvdbId)
        if (bestById != null && HomeServerMatcher.score(title, year, imdbId, tmdbId, tvdbId, bestById.info()) >= 900) {
            return bestById
        }

        if (title.isNotBlank()) {
            queryItems(
                connection,
                itemTypes = "Series",
                query = mapOf("SearchTerm" to title, "Limit" to "25")
            ).forEach { candidates[it.id] = it }
        }
        return bestCandidate(candidates.values, title, year, imdbId, tmdbId, tvdbId)
    }

    private fun providerQueries(imdbId: String?, tmdbId: Int?, tvdbId: Int?): List<String> {
        return buildList {
            imdbId?.trim()?.takeIf { it.isNotBlank() }?.let {
                add("imdb.$it")
                add("Imdb.$it")
            }
            tmdbId?.takeIf { it > 0 }?.let {
                add("tmdb.$it")
                add("Tmdb.$it")
            }
            tvdbId?.takeIf { it > 0 }?.let {
                add("tvdb.$it")
                add("Tvdb.$it")
            }
        }.distinct()
    }

    private fun bestCandidate(
        candidates: Collection<HomeServerItem>,
        title: String,
        year: Int?,
        imdbId: String?,
        tmdbId: Int?,
        tvdbId: Int?
    ): HomeServerItem? {
        return candidates
            .map { item -> item to HomeServerMatcher.score(title, year, imdbId, tmdbId, tvdbId, item.info()) }
            .filter { (_, score) -> HomeServerMatcher.isAcceptable(score) }
            .maxByOrNull { (_, score) -> score }
            ?.first
    }

    private fun findEpisode(
        connection: HomeServerConnection,
        seriesId: String,
        season: Int,
        episode: Int
    ): HomeServerItem? {
        if (connection.serverKind == HomeServerKind.PLEX) {
            val leaves = getJson(
                buildUrl(
                    connection.serverUrl,
                    "/library/metadata/$seriesId/allLeaves",
                    mapOf("includeGuids" to "1")
                ),
                connection
            ).metadataItems(connection.serverKind)
            return leaves.firstOrNull { it.parentIndexNumber == season && it.indexNumber == episode }
        }

        val byShowEndpoint = getJson(
            buildUrl(
                connection.serverUrl,
                "/Shows/$seriesId/Episodes",
                mapOf(
                    "UserId" to connection.userId,
                    "Season" to season.toString(),
                    "Fields" to itemFields()
                )
            ),
            connection
        ).items()
        return byShowEndpoint.firstOrNull { it.parentIndexNumber == season && it.indexNumber == episode }
            ?: queryItems(
                connection,
                itemTypes = "Episode",
                query = mapOf(
                    "SeriesId" to seriesId,
                    "ParentIndexNumber" to season.toString(),
                    "IndexNumber" to episode.toString(),
                    "Limit" to "10"
                )
            ).firstOrNull { it.parentIndexNumber == season && it.indexNumber == episode }
    }

    private fun findEpisodeBySearch(
        connection: HomeServerConnection,
        title: String,
        season: Int,
        episode: Int,
        imdbId: String?,
        tmdbId: Int?,
        tvdbId: Int?
    ): HomeServerItem? {
        val candidates = queryItems(
            connection,
            itemTypes = "Episode",
            query = mapOf(
                "SearchTerm" to title,
                "ParentIndexNumber" to season.toString(),
                "IndexNumber" to episode.toString(),
                "Limit" to "25"
            )
        ).filter { it.parentIndexNumber == season && it.indexNumber == episode }
        return bestCandidate(candidates, title, null, imdbId, tmdbId, tvdbId)
    }

    private fun queryItems(
        connection: HomeServerConnection,
        itemTypes: String,
        query: Map<String, String?>
    ): List<HomeServerItem> {
        if (connection.serverKind == HomeServerKind.PLEX) {
            return queryPlexItems(connection, itemTypes, query)
        }
        val response = getJson(
            buildUrl(
                connection.serverUrl,
                "/Users/${connection.userId}/Items",
                mapOf(
                    "Recursive" to "true",
                    "IncludeItemTypes" to itemTypes,
                    "Fields" to itemFields()
                ) + query
            ),
            connection
        )
        return response.items()
    }

    private fun queryPlexItems(
        connection: HomeServerConnection,
        itemTypes: String,
        query: Map<String, String?>
    ): List<HomeServerItem> {
        val plexType = when (itemTypes.lowercase(Locale.US)) {
            "movie" -> "1"
            "series" -> "2"
            "episode" -> "4"
            else -> null
        }
        val limit = query["Limit"]?.takeIf { it.isNotBlank() } ?: "25"
        val collections = eligibleCollections(connection, itemTypes)
        query["AnyProviderIdEquals"]?.takeIf { it.isNotBlank() }?.let { providerId ->
            val guidQueries = plexGuidQueries(providerId)
            if (guidQueries.isNotEmpty()) {
                val providerResults = guidQueries.flatMap { guid ->
                    queryPlexByGuid(connection, collections, plexType, guid, limit)
                }.distinctBy { it.id }
                if (providerResults.isNotEmpty()) return providerResults
            }
            val providerSearchTerm = providerId.substringAfter('.', providerId)
            val providerFallback = queryPlexSearch(connection, providerSearchTerm, plexType, limit)
            if (providerFallback.isNotEmpty()) return providerFallback
        }

        val searchTerm = query["SearchTerm"]?.takeIf { it.isNotBlank() } ?: return emptyList()
        val globalResults = queryPlexSearch(connection, searchTerm, plexType, limit)
            .filter { item -> itemBelongsToEnabledPlexCollection(item, collections) }
        if (globalResults.isNotEmpty()) {
            return filterPlexEpisodeNumbers(globalResults, query)
        }

        val sectionResults = if (collections.isNotEmpty()) {
            collections.flatMap { collection ->
                runCatching {
                    getJson(
                        buildUrl(
                            connection.serverUrl,
                            "/library/sections/${collection.id}/all",
                            mapOf(
                                "type" to plexType,
                                "title" to searchTerm,
                                "includeGuids" to "1",
                                "limit" to limit
                            )
                        ),
                        connection
                    ).metadataItems(connection.serverKind)
                }.getOrDefault(emptyList())
            }
        } else {
            emptyList()
        }
        val results = sectionResults.ifEmpty {
            queryPlexSearch(connection, searchTerm, plexType, limit)
        }

        return filterPlexEpisodeNumbers(results, query).distinctBy { it.id }
    }

    private fun queryPlexByGuid(
        connection: HomeServerConnection,
        collections: List<HomeServerCollection>,
        plexType: String?,
        guid: String,
        limit: String
    ): List<HomeServerItem> {
        val targetCollections = collections.ifEmpty {
            connection.collections.filter { it.enabled }
        }
        return targetCollections.flatMap { collection ->
            runCatching {
                getJson(
                    buildUrl(
                        connection.serverUrl,
                        "/library/sections/${collection.id}/all",
                        mapOf(
                            "type" to plexType,
                            "guid" to guid,
                            "includeGuids" to "1",
                            "limit" to limit
                        )
                    ),
                    connection
                ).metadataItems(connection.serverKind)
            }.getOrDefault(emptyList())
        }
    }

    private fun queryPlexSearch(
        connection: HomeServerConnection,
        searchTerm: String,
        plexType: String?,
        limit: String
    ): List<HomeServerItem> {
        return runCatching {
            getJson(
                buildUrl(
                    connection.serverUrl,
                    "/search",
                    mapOf(
                        "query" to searchTerm,
                        "type" to plexType,
                        "includeGuids" to "1",
                        "limit" to limit
                    )
                ),
                connection
            ).metadataItems(connection.serverKind)
        }.getOrDefault(emptyList())
    }

    private fun filterPlexEpisodeNumbers(
        results: List<HomeServerItem>,
        query: Map<String, String?>
    ): List<HomeServerItem> {
        val requestedSeason = query["ParentIndexNumber"]?.toIntOrNull()
        val requestedEpisode = query["IndexNumber"]?.toIntOrNull()
        return results
            .filter { item ->
                (requestedSeason == null || item.parentIndexNumber == requestedSeason) &&
                    (requestedEpisode == null || item.indexNumber == requestedEpisode)
            }
            .distinctBy { it.id }
    }

    private fun itemBelongsToEnabledPlexCollection(
        item: HomeServerItem,
        collections: List<HomeServerCollection>
    ): Boolean {
        if (collections.isEmpty()) return true
        return item.librarySectionId.isBlank() || collections.any { it.id == item.librarySectionId }
    }

    private fun plexGuidQueries(providerId: String): List<String> {
        val provider = providerId.substringBefore('.', missingDelimiterValue = "")
            .lowercase(Locale.US)
        val id = providerId.substringAfter('.', missingDelimiterValue = "")
            .trim()
        if (provider.isBlank() || id.isBlank()) return emptyList()
        return when (provider) {
            "imdb" -> listOf("imdb://$id")
            "tmdb" -> listOf("tmdb://$id")
            "tvdb" -> listOf("tvdb://$id")
            else -> emptyList()
        }
    }

    private fun eligibleCollections(connection: HomeServerConnection, itemTypes: String): List<HomeServerCollection> {
        val type = itemTypes.lowercase(Locale.US)
        return connection.collections
            .filter { it.enabled }
            .filter { collection ->
                val collectionType = collection.type.lowercase(Locale.US)
                when (type) {
                    "movie" -> collectionType in setOf("movies", "movie")
                    "series", "episode" -> collectionType in setOf("tvshows", "series", "show")
                    else -> true
                }
            }
    }

    private fun itemFields(): String = "ProviderIds,MediaSources,MediaStreams,Path,PremiereDate,ProductionYear,RunTimeTicks"

    private fun buildStreamSources(
        connection: HomeServerConnection,
        item: HomeServerItem
    ): List<StreamSource> {
        val sources = if (connection.serverKind == HomeServerKind.PLEX) {
            val refreshedSources = runCatching {
                getJson(
                    buildUrl(
                        connection.serverUrl,
                        "/library/metadata/${item.id}",
                        mapOf(
                            "includeGuids" to "1",
                            "includeMedia" to "1"
                        )
                    ),
                    connection
                ).metadataItems(connection.serverKind).firstOrNull()?.mediaSources.orEmpty()
            }.getOrDefault(emptyList())
            (refreshedSources + item.mediaSources)
                .distinctBy { it.identityKey() }
        } else {
            val playbackInfoSources = runCatching {
                postJson(
                    buildUrl(
                        connection.serverUrl,
                        "/Items/${item.id}/PlaybackInfo",
                        mapOf(
                            "UserId" to connection.userId,
                            "StartTimeTicks" to "0",
                            "IsPlayback" to "true",
                            "AutoOpenLiveStream" to "true",
                            "MaxStreamingBitrate" to "2147483647"
                        )
                    ),
                    JsonObject(),
                    connection
                ).mediaSources()
            }.getOrDefault(emptyList())
            (playbackInfoSources + item.mediaSources)
                .distinctBy { it.identityKey() }
        }
        return sources
            .mapNotNull { mediaSource ->
                val url = mediaSource.playbackUrl(connection, item.id) ?: return@mapNotNull null
                val quality = qualityLabel(mediaSource)
                val serverLabel = connection.displayLabel()
                val labelParts = listOfNotNull(
                    serverLabel,
                    quality.takeIf { it.isNotBlank() },
                    mediaSource.container.takeIf { it.isNotBlank() }?.uppercase(Locale.US)
                )
                StreamSource(
                    source = labelParts.joinToString(" "),
                    addonName = serverLabel,
                    addonId = ADDON_ID,
                    quality = quality.ifBlank { "Unknown" },
                    size = formatBytes(mediaSource.sizeBytes),
                    sizeBytes = mediaSource.sizeBytes.takeIf { it > 0L },
                    url = url,
                    behaviorHints = StreamBehaviorHints(
                        cached = true,
                        filename = mediaSource.name.ifBlank { item.name },
                        videoSize = mediaSource.sizeBytes.takeIf { it > 0L },
                        proxyHeaders = ProxyHeaders(request = playbackHeaders(connection))
                    )
                )
            }
            .distinctBy { "${it.url?.trim().orEmpty()}|${it.source}" }
            .sortedWith(compareByDescending<StreamSource> { qualityRank(it.quality) }
                .thenByDescending { it.sizeBytes ?: 0L })
    }

    private fun HomeServerMediaSource.identityKey(): String {
        return id.takeIf { it.isNotBlank() }
            ?: key.takeIf { it.isNotBlank() }
            ?: path.takeIf { it.isNotBlank() }
            ?: name.takeIf { it.isNotBlank() }
            ?: "$container|$sizeBytes|$videoWidth|$videoHeight"
    }

    private fun HomeServerMediaSource.playbackUrl(connection: HomeServerConnection, itemId: String): String? {
        if (connection.serverKind == HomeServerKind.PLEX) {
            key.takeIf { it.isNotBlank() }?.let { return plexUrlWithToken(connection, absoluteUrl(connection.serverUrl, it)) }
            path.takeIf { it.startsWith("http://", true) || it.startsWith("https://", true) }?.let {
                return plexUrlWithToken(connection, it)
            }
            id.takeIf { it.isNotBlank() }?.let {
                return buildUrl(
                    connection.serverUrl,
                    "/library/parts/$it/file",
                    mapOf("X-Plex-Token" to connection.accessToken)
                )
            }
            return null
        }
        path.takeIf { it.startsWith("http://", true) || it.startsWith("https://", true) }?.let { return it }
        transcodingUrl.takeIf { it.isNotBlank() }?.let { raw ->
            val absolute = absoluteUrl(connection.serverUrl, raw)
            val parsed = absolute.toHttpUrlOrNull() ?: return absolute
            return parsed.newBuilder()
                .apply {
                    if (parsed.queryParameter("api_key").isNullOrBlank()) {
                        addQueryParameter("api_key", connection.accessToken)
                    }
                }
                .build()
                .toString()
        }

        val extension = streamExtension()
        val streamPath = if (extension != null) "/Videos/$itemId/stream.$extension" else "/Videos/$itemId/stream"
        return buildUrl(
            connection.serverUrl,
            streamPath,
            mapOf(
                "Static" to "true",
                "MediaSourceId" to id,
                "DeviceId" to deviceId(),
                "api_key" to connection.accessToken,
                "Tag" to eTag.takeIf { it.isNotBlank() }
            )
        )
    }

    private fun plexUrlWithToken(connection: HomeServerConnection, rawUrl: String): String {
        val parsed = rawUrl.toHttpUrlOrNull() ?: return rawUrl
        if (!parsed.queryParameter("X-Plex-Token").isNullOrBlank()) return rawUrl
        return parsed.newBuilder()
            .addQueryParameter("X-Plex-Token", connection.accessToken)
            .build()
            .toString()
    }

    private fun homeServerKindLabel(kind: HomeServerKind): String {
        return when (kind) {
            HomeServerKind.PLEX,
            HomeServerKind.JELLYFIN,
            HomeServerKind.EMBY -> "Media Server"
            HomeServerKind.UNKNOWN -> ""
        }
    }

    private fun HomeServerConnection.displayLabel(): String {
        val rawName = displayName.ifBlank { serverName }.ifBlank { serverHostLabel(serverUrl) }.ifBlank { "Home Server" }
        val kind = specificHomeServerKindLabel(serverKind)
        return when {
            kind.isBlank() -> rawName
            rawName.contains(kind, ignoreCase = true) -> rawName
            else -> "$kind $rawName"
        }
    }

    private fun specificHomeServerKindLabel(kind: HomeServerKind): String {
        return when (kind) {
            HomeServerKind.PLEX -> "Plex"
            HomeServerKind.JELLYFIN -> "Jellyfin"
            HomeServerKind.EMBY -> "Emby"
            HomeServerKind.UNKNOWN -> ""
        }
    }

    private fun serverHostLabel(serverUrl: String): String {
        return serverUrl.toHttpUrlOrNull()?.let { url ->
            val defaultPort = if (url.scheme == "https") 443 else 80
            if (url.port != defaultPort) "${url.host}:${url.port}" else url.host
        }.orEmpty()
    }

    private fun HomeServerMediaSource.streamExtension(): String? {
        val normalized = container
            .split(',')
            .firstOrNull()
            ?.trim()
            ?.lowercase(Locale.US)
            ?.replace("matroska", "mkv")
            ?.replace(HomeServerRepositoryRegexes.NON_ALPHA_NUM_STRICT_REGEX, "")
            .orEmpty()
        return normalized.takeIf { it.isNotBlank() && it.length <= 5 }
    }

    private fun qualityLabel(source: HomeServerMediaSource): String {
        val height = source.videoHeight
        val width = source.videoWidth
        return when {
            height >= 2160 || width >= 3800 -> "4K"
            height >= 1440 -> "1440p"
            height >= 1080 -> "1080p"
            height >= 720 -> "720p"
            height >= 576 -> "576p"
            height >= 480 -> "480p"
            height > 0 -> "${height}p"
            else -> source.name.extractQualityLabel()
        }
    }

    private fun qualityRank(quality: String): Int {
        val text = quality.lowercase(Locale.US)
        return when {
            "4k" in text || "2160" in text || "uhd" in text -> 2160
            "1440" in text -> 1440
            "1080" in text -> 1080
            "720" in text -> 720
            "576" in text -> 576
            "480" in text -> 480
            else -> 0
        }
    }

    private fun String.extractQualityLabel(): String {
        val text = lowercase(Locale.US)
        return when {
            "2160" in text || "4k" in text || "uhd" in text -> "4K"
            "1440" in text -> "1440p"
            "1080" in text -> "1080p"
            "720" in text -> "720p"
            "576" in text -> "576p"
            "480" in text -> "480p"
            else -> ""
        }
    }

    private fun formatBytes(bytes: Long): String {
        if (bytes <= 0L) return ""
        val gb = bytes / (1024.0 * 1024.0 * 1024.0)
        return if (gb >= 1.0) {
            String.format(Locale.US, "%.2f GB", gb)
        } else {
            val mb = bytes / (1024.0 * 1024.0)
            String.format(Locale.US, "%.0f MB", mb)
        }
    }

    private fun JsonObject.items(): List<HomeServerItem> {
        return itemsArray().mapNotNull { it.asJsonObjectOrNull()?.toHomeServerItem(HomeServerKind.UNKNOWN) }
    }

    private fun JsonObject.itemsArray(): List<JsonElement> = array("Items")

    private fun JsonObject.metadataItems(kind: HomeServerKind): List<HomeServerItem> {
        return array("MediaContainer", "Metadata").mapNotNull { it.asJsonObjectOrNull()?.toHomeServerItem(kind) }
            .ifEmpty { array("Metadata").mapNotNull { it.asJsonObjectOrNull()?.toHomeServerItem(kind) } }
    }

    private fun JsonObject.mediaSources(): List<HomeServerMediaSource> {
        return array("MediaSources").mapNotNull { it.asJsonObjectOrNull()?.toMediaSource(HomeServerKind.UNKNOWN) }
    }

    private fun JsonObject.toHomeServerItem(kind: HomeServerKind): HomeServerItem {
        if (kind == HomeServerKind.PLEX) {
            val providerIds = array("Guid")
                .mapNotNull { it.asJsonObjectOrNull()?.string("id") }
                .mapNotNull { guid ->
                    val provider = guid.substringBefore("://").lowercase(Locale.US)
                    val id = guid.substringAfter("://", missingDelimiterValue = "").substringBefore("?")
                    provider.takeIf { it.isNotBlank() && id.isNotBlank() }?.let { it to id }
                }
                .toMap()
            return HomeServerItem(
                id = string("ratingKey").ifBlank { string("key") },
                name = string("title"),
                type = string("type"),
                productionYear = int("year") ?: string("originallyAvailableAt").take(4).toIntOrNull(),
                providerIds = providerIds,
                librarySectionId = string("librarySectionID"),
                indexNumber = int("index"),
                parentIndexNumber = int("parentIndex"),
                mediaSources = array("Media")
                    .mapNotNull { it.asJsonObjectOrNull() }
                    .flatMap { media -> media.array("Part").mapNotNull { it.asJsonObjectOrNull()?.toMediaSource(kind, media) } }
            )
        }

        val providerIds = obj("ProviderIds")?.entrySet()
            ?.associate { it.key.lowercase(Locale.US) to it.value.asStringOrNull().orEmpty() }
            .orEmpty()
        val year = int("ProductionYear") ?: string("PremiereDate").take(4).toIntOrNull()
        return HomeServerItem(
            id = string("Id"),
            name = string("Name"),
            type = string("Type"),
            productionYear = year,
            providerIds = providerIds,
            librarySectionId = "",
            indexNumber = int("IndexNumber"),
            parentIndexNumber = int("ParentIndexNumber"),
            mediaSources = mediaSources()
        )
    }

    private fun JsonObject.toMediaSource(kind: HomeServerKind, parentMedia: JsonObject? = null): HomeServerMediaSource {
        if (kind == HomeServerKind.PLEX) {
            val width = parentMedia?.int("width") ?: int("width") ?: 0
            val height = parentMedia?.int("height") ?: int("height") ?: 0
            return HomeServerMediaSource(
                id = string("id"),
                key = string("key"),
                name = string("file").substringAfterLast('/').substringAfterLast('\\').ifBlank {
                    parentMedia?.string("title").orEmpty()
                },
                path = string("file"),
                container = parentMedia?.string("container").orEmpty().ifBlank { string("container") },
                eTag = "",
                sizeBytes = long("size") ?: 0L,
                transcodingUrl = "",
                videoWidth = width,
                videoHeight = height
            )
        }

        val streams = array("MediaStreams").mapNotNull { it.asJsonObjectOrNull() }
        val videoStream = streams.firstOrNull { it.string("Type").equals("Video", ignoreCase = true) }
        return HomeServerMediaSource(
            id = string("Id"),
            key = "",
            name = string("Name"),
            path = string("Path"),
            container = string("Container"),
            eTag = string("ETag").ifBlank { string("Etag") },
            sizeBytes = long("Size") ?: long("RunTimeTicks")?.let { 0L } ?: 0L,
            transcodingUrl = string("TranscodingUrl"),
            videoWidth = videoStream?.int("Width") ?: 0,
            videoHeight = videoStream?.int("Height") ?: 0
        )
    }

    private fun JsonObject.string(name: String): String = get(name)?.asStringOrNull().orEmpty()
    private fun JsonObject.int(name: String): Int? = get(name)?.asStringOrNull()?.toIntOrNull()
    private fun JsonObject.long(name: String): Long? = get(name)?.asStringOrNull()?.toLongOrNull()
    private fun JsonObject.obj(name: String): JsonObject? = get(name)?.asJsonObjectOrNull()
    private fun JsonObject.array(name: String): List<JsonElement> =
        get(name)?.takeIf { it.isJsonArray }?.asJsonArray?.toList().orEmpty()

    private fun JsonObject.array(parent: String, name: String): List<JsonElement> =
        obj(parent)?.array(name).orEmpty()

    private fun JsonElement.asJsonObjectOrNull(): JsonObject? =
        takeIf { it.isJsonObject }?.asJsonObject

    private fun JsonElement.asStringOrNull(): String? =
        runCatching { takeUnless { it.isJsonNull }?.asString }.getOrNull()

    private fun String.xmlAttribute(name: String): String {
        val pattern = Regex("""\b${Regex.escape(name)}=["']([^"']*)["']""")
        return pattern.find(this)?.groupValues?.getOrNull(1).orEmpty().xmlDecoded()
    }

    private fun String.xmlBooleanAttribute(name: String): Boolean {
        val value = xmlAttribute(name)
        return value == "1" || value.equals("true", ignoreCase = true)
    }

    private fun String.xmlDecoded(): String = replace("&amp;", "&")
        .replace("&quot;", "\"")
        .replace("&apos;", "'")
        .replace("&lt;", "<")
        .replace("&gt;", ">")

    private fun parsePlexIdentity(body: String): Pair<String, String> {
        val container = runCatching {
            val json = JsonParser().parse(body).asJsonObjectOrNull()
            json?.obj("MediaContainer") ?: json
        }.getOrNull()
        val name = container?.string("friendlyName").orEmpty()
        val id = container?.string("machineIdentifier").orEmpty()
        if (name.isNotBlank() || id.isNotBlank()) {
            return name to id
        }
        return body.xmlAttribute("friendlyName") to body.xmlAttribute("machineIdentifier")
    }

    private data class ServerInfo(
        val serverName: String,
        val serverId: String,
        val productName: String,
        val serverKind: HomeServerKind = HomeServerKind.UNKNOWN
    )

    private data class AuthResponse(
        val accessToken: String,
        val serverId: String,
        val serverName: String,
        val userId: String,
        val userName: String,
        val accountToken: String = ""
    )

    private data class HomeServerItem(
        val id: String,
        val name: String,
        val type: String,
        val productionYear: Int?,
        val providerIds: Map<String, String>,
        val librarySectionId: String,
        val indexNumber: Int?,
        val parentIndexNumber: Int?,
        val mediaSources: List<HomeServerMediaSource>
    ) {
        fun info() = HomeServerCandidateInfo(
            title = name,
            productionYear = productionYear,
            providerIds = providerIds
        )

        fun toCatalogItem(): HomeServerCatalogItem? {
            val mediaType = when (type.lowercase(Locale.US)) {
                "movie" -> MediaType.MOVIE
                "series", "show", "tv", "tvshow" -> MediaType.TV
                else -> return null
            }
            return HomeServerCatalogItem(
                id = id,
                title = name,
                mediaType = mediaType,
                year = productionYear,
                providerIds = providerIds.mapKeys { it.key.lowercase(Locale.US) }
            )
        }
    }

    private data class HomeServerMediaSource(
        val id: String,
        val key: String,
        val name: String,
        val path: String,
        val container: String,
        val eTag: String,
        val sizeBytes: Long,
        val transcodingUrl: String,
        val videoWidth: Int,
        val videoHeight: Int
    )

}



private object HomeServerRepositoryRegexes {
    val DIACRITICS_REGEX = Regex("\\p{Mn}+")
    val NON_ALPHA_NUM_REGEX = Regex("[^a-z0-9]+")
    val ARTICLES_REGEX = Regex("\\b(the|a|an)\\b")
    val MULTI_SPACE_REGEX = Regex("\\s+")
    val CONNECTION_ID_SANITIZER_REGEX = Regex("[^a-z0-9:._-]+")
    val NON_ALPHA_NUM_STRICT_REGEX = Regex("[^a-z0-9]")
    val PLEX_DEVICE_REGEX = Regex(
        """<Device\b([^>]*)>(.*?)</Device>|<Device\b([^>]*)/>""",
        setOf(RegexOption.IGNORE_CASE, RegexOption.DOT_MATCHES_ALL)
    )
    val PLEX_CONNECTION_REGEX = Regex("""<Connection\b([^>]*)/?\s*>""", RegexOption.IGNORE_CASE)
}
