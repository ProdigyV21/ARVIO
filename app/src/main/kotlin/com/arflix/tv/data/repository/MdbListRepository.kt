package com.arflix.tv.data.repository

import android.content.Context
import com.arflix.tv.data.api.MdbDeviceAuthorizationResponse
import com.arflix.tv.data.api.MdbEpisodeInfo
import com.arflix.tv.data.api.MdbIds
import com.arflix.tv.data.api.MdbIdsItem
import com.arflix.tv.data.api.MdbListApi
import com.arflix.tv.data.api.MdbMovieInfo
import com.arflix.tv.data.api.MdbPlaybackItem
import com.arflix.tv.data.api.MdbRating
import com.arflix.tv.data.api.MdbScrobbleClearBody
import com.arflix.tv.data.api.MdbScrobbleEpisodeNumber
import com.arflix.tv.data.api.MdbScrobbleMovie
import com.arflix.tv.data.api.MdbScrobbleSeason
import com.arflix.tv.data.api.MdbScrobbleShow
import com.arflix.tv.data.api.MdbScrobbleBody
import com.arflix.tv.data.api.MdbShowInfo
import com.arflix.tv.data.api.MdbTmdbRef
import com.arflix.tv.data.api.MdbTokenResponse
import com.arflix.tv.data.api.MdbUserList
import com.arflix.tv.data.api.MdbWatchedBody
import com.arflix.tv.data.api.MdbWatchedEpisodeRef
import com.arflix.tv.data.api.MdbWatchedSeasonRef
import com.arflix.tv.data.api.MdbWatchedShowRef
import com.arflix.tv.data.api.MdbWatchlistItem
import com.arflix.tv.data.api.MdbWatchlistModifyBody
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.repository.sync.MdbListConnection
import com.arflix.tv.data.repository.sync.RemoteWatchlistResult
import com.arflix.tv.data.repository.sync.SyncProviderStore
import com.arflix.tv.util.AppLogger
import com.arflix.tv.util.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import com.google.gson.JsonParser
import java.util.concurrent.ConcurrentHashMap
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.roundToInt

data class MdbShowWatchedProgress(
    val showTmdbId: Int,
    val title: String,
    val year: String,
    val watchedBySeason: Map<Int, Set<Int>>, // season -> Set<episodeNumber>
    val lastWatchedAtMs: Long
)

data class MdbWatchedSnapshot(
    val movies: Set<Int>,
    val episodes: Set<String>
)

data class MdbExternalRating(
    val source: String,
    val label: String,
    val value: String
)

class MdbListAuthUnavailable(val reconnectRequired: Boolean) : IllegalStateException(
    if (reconnectRequired) "Reconnect MDBList to continue syncing" else "MDBList is temporarily unavailable"
)

enum class MdbListDeviceError {
    AUTHORIZATION_PENDING,
    SLOW_DOWN,
    ACCESS_DENIED,
    EXPIRED_TOKEN,
    OTHER
}

fun parseMdbListDeviceError(throwable: Throwable): MdbListDeviceError {
    val httpError = throwable as? retrofit2.HttpException
    val code = httpError?.code() ?: -1

    // Single-read the response body string once to prevent stream consumption / closed errors
    val errorBodyString = runCatching { httpError?.response()?.errorBody()?.string() }.getOrNull()
    val errorCode = if (!errorBodyString.isNullOrBlank()) {
        runCatching {
            JsonParser.parseString(errorBodyString).asJsonObject.get("error")?.asString
        }.getOrNull() ?: runCatching {
            org.json.JSONObject(errorBodyString).optString("error").takeIf { it.isNotBlank() }
        }.getOrNull()
    } else null

    return when {
        errorCode == "authorization_pending" ||
            (code == 400 && (errorBodyString?.contains("authorization_pending", ignoreCase = true) == true ||
                errorBodyString?.contains("pending", ignoreCase = true) == true)) ||
            throwable.message?.contains("pending", ignoreCase = true) == true -> MdbListDeviceError.AUTHORIZATION_PENDING

        errorCode == "slow_down" || code == 429 ||
            (code == 400 && errorBodyString?.contains("slow_down", ignoreCase = true) == true) -> MdbListDeviceError.SLOW_DOWN

        errorCode == "access_denied" || code == 403 ||
            (code == 400 && errorBodyString?.contains("access_denied", ignoreCase = true) == true) -> MdbListDeviceError.ACCESS_DENIED

        errorCode == "expired_token" ||
            (code == 400 && errorBodyString?.contains("expired_token", ignoreCase = true) == true) -> MdbListDeviceError.EXPIRED_TOKEN

        else -> MdbListDeviceError.OTHER
    }
}

@Singleton
class MdbListRepository @Inject constructor(
    private val api: MdbListApi,
    private val store: SyncProviderStore,
    private val profileManager: ProfileManager,
    private val invalidationBus: CloudSyncInvalidationBus = CloudSyncInvalidationBus()
) {
    private val TAG = "MdbListRepository"
    private data class RatingsCacheEntry(val storedAt: Long, val ratings: List<MdbExternalRating>)
    private val ratingsCache = ConcurrentHashMap<String, RatingsCacheEntry>()

    private val tokenRenewalMutex = Mutex()
    private val tokenRenewalBackoffUntilMs = ConcurrentHashMap<String, Long>()
    private val renewalRefusals = ConcurrentHashMap<String, Pair<SyncProviderStore.MdbListCredential.OAuth, Int>>()
    internal var clock: () -> Long = System::currentTimeMillis

    /** The active profile's MDBList connection, and whether MDBList rejected its credential. */
    val connection: Flow<MdbListConnection> get() = store.mdbListConnection

    sealed interface MdbListAuth {
        data class OAuth(val accessToken: String) : MdbListAuth
        data class ApiKey(val apiKey: String) : MdbListAuth
    }

    suspend fun isConnected(profileId: String? = null): Boolean {
        val targetProfile = profileId ?: profileManager.getProfileIdSync()
        return store.getMdbListCredential(targetProfile) != null
    }

    suspend fun resolveAuth(profileId: String? = null, forceRefresh: Boolean = false, rejectedAccessToken: String? = null): MdbListAuth? {
        val targetProfile = profileId ?: profileManager.getProfileIdSync()
        val cred = store.getMdbListCredential(targetProfile) ?: return null
        // A rejected credential stays rejected until a new login or a newer cloud copy replaces it.
        if (store.isMdbListReconnectRequired(targetProfile)) return null
        return when (cred) {
            is SyncProviderStore.MdbListCredential.ApiKey -> MdbListAuth.ApiKey(cred.apiKey)
            is SyncProviderStore.MdbListCredential.OAuth -> {
                val validToken = getValidOAuthAccessToken(
                    profileId = targetProfile,
                    currentOAuth = cred,
                    forceRefresh = forceRefresh && (rejectedAccessToken == null || rejectedAccessToken == cred.accessToken)
                )
                validToken?.let { MdbListAuth.OAuth(it) }
            }
        }
    }

    private suspend fun getValidOAuthAccessToken(
        profileId: String,
        currentOAuth: SyncProviderStore.MdbListCredential.OAuth,
        forceRefresh: Boolean
    ): String? {
        val refreshToken = currentOAuth.refreshToken
        val expiresAt = currentOAuth.expiresAt
        val nowMs = clock()

        val clientId = Constants.MDBLIST_CLIENT_ID.trim()
        if (refreshToken.isNullOrBlank() || clientId.isBlank()) {
            return currentOAuth.accessToken.takeUnless { forceRefresh || (expiresAt != null && nowMs >= expiresAt) }
        }

        val shouldRefresh = forceRefresh || (expiresAt != null && nowMs >= (expiresAt - 60_000L))
        if (!shouldRefresh) {
            return currentOAuth.accessToken
        }

        return tokenRenewalMutex.withLock {
            val lockedCred = store.getMdbListCredential(profileId) as? SyncProviderStore.MdbListCredential.OAuth
                ?: return@withLock null
            val lockedNow = clock()
            val lockedRefreshToken = lockedCred.refreshToken ?: return@withLock lockedCred.accessToken
            val lockedExpiresAt = lockedCred.expiresAt ?: Long.MAX_VALUE

            if (lockedCred.accessToken != currentOAuth.accessToken || (!forceRefresh && lockedNow < (lockedExpiresAt - 60_000L))) {
                return@withLock lockedCred.accessToken
            }

            if (lockedNow < (tokenRenewalBackoffUntilMs[profileId] ?: 0L)) {
                return@withLock if (!forceRefresh && lockedNow < lockedExpiresAt) lockedCred.accessToken else null
            }

            try {
                val response = withContext(Dispatchers.IO) {
                    api.refreshToken(
                        grantType = "refresh_token",
                        refreshToken = lockedRefreshToken,
                        clientId = clientId
                    )
                }
                require(response.accessToken.isNotBlank()) { "MDBList returned an empty access token" }
                tokenRenewalBackoffUntilMs.remove(profileId)
                renewalRefusals.remove(profileId)

                val newRefreshToken = response.refreshToken?.takeIf { it.isNotBlank() } ?: lockedRefreshToken
                val applied = store.setMdbListOAuthTokens(
                    accessToken = response.accessToken,
                    refreshToken = newRefreshToken,
                    expiresInSeconds = response.expiresIn,
                    profileId = profileId,
                    expected = lockedCred
                )
                // Other devices and the web app share this credential; publish the replacement
                // right away so none of them renews with a refresh token that may now be dead.
                if (applied) invalidationBus.markUserDirty(CloudSyncScope.ACCOUNT, profileId, "mdblist token rotated")
                response.accessToken.takeIf { applied }
            } catch (e: retrofit2.HttpException) {
                val code = e.code()
                val oauthError = runCatching {
                    JsonParser.parseString(e.response()?.errorBody()?.string()).asJsonObject.get("error")?.asString
                }.getOrNull()
                AppLogger.w(TAG, "MDBList token renewal failed: http=$code error=$oauthError")
                // A configuration rejection (invalid_client) is not the user's connection. A
                // refresh token refused outright, or again and again, is dead: keeping it left
                // MDBList showing as connected while nothing synced. It is marked, not erased.
                val strikes = if (oauthError != "invalid_client" && (code == 400 || code == 403)) {
                    (renewalRefusals[profileId]?.takeIf { it.first == lockedCred }?.second ?: 0) + 1
                } else {
                    0
                }
                if (oauthError == "invalid_grant" || strikes >= RENEWAL_REFUSALS_BEFORE_RECONNECT) {
                    renewalRefusals.remove(profileId)
                    tokenRenewalBackoffUntilMs.remove(profileId)
                    store.markMdbListReconnectRequired(profileId, lockedCred)
                    return@withLock null
                }
                if (strikes > 0) renewalRefusals[profileId] = lockedCred to strikes else renewalRefusals.remove(profileId)
                tokenRenewalBackoffUntilMs[profileId] = clock() + 30_000L
                if (!forceRefresh && clock() < lockedExpiresAt &&
                    store.getMdbListCredential(profileId) == lockedCred) lockedCred.accessToken else null
            } catch (e: Exception) {
                if (e is kotlinx.coroutines.CancellationException) throw e
                tokenRenewalBackoffUntilMs[profileId] = clock() + 30_000L
                AppLogger.w(TAG, "MDBList token renewal failed")
                if (!forceRefresh && clock() < lockedExpiresAt &&
                    store.getMdbListCredential(profileId) == lockedCred) lockedCred.accessToken else null
            }
        }
    }

    private suspend fun unavailable(profileId: String) =
        MdbListAuthUnavailable(store.isMdbListReconnectRequired(profileId))

    /**
     * MDBList answers a dead credential with 401 ("Invalid API key" / "Invalid OAuth token").
     * The account endpoint must refuse it too before the connection is marked for reconnecting,
     * so a 401 on one resource cannot cost the user their connection.
     */
    private suspend fun markRejected(profileId: String, auth: MdbListAuth): Boolean {
        val refused = try {
            when (auth) {
                is MdbListAuth.OAuth -> api.getUser(authHeader = "Bearer ${auth.accessToken}", apiKey = null)
                is MdbListAuth.ApiKey -> api.getUser(authHeader = null, apiKey = auth.apiKey)
            }
            false
        } catch (e: retrofit2.HttpException) {
            e.code() == 401 || e.code() == 403
        } catch (e: Exception) {
            if (e is kotlinx.coroutines.CancellationException) throw e
            false
        }
        if (!refused) return false
        val current = store.getMdbListCredential(profileId) ?: return false
        val same = when (auth) {
            is MdbListAuth.OAuth -> (current as? SyncProviderStore.MdbListCredential.OAuth)?.accessToken == auth.accessToken
            is MdbListAuth.ApiKey -> (current as? SyncProviderStore.MdbListCredential.ApiKey)?.apiKey == auth.apiKey
        }
        if (!same) return false
        AppLogger.w(TAG, "MDBList rejected the stored credential; reconnect required")
        return store.markMdbListReconnectRequired(profileId, current)
    }

    private suspend inline fun <T> executeWithAuth(
        profileId: String? = null,
        crossinline block: suspend (authHeader: String?, apiKey: String?) -> T
    ): T? {
        val targetProfile = profileId ?: profileManager.getProfileIdSync()
        val auth = resolveAuth(targetProfile, forceRefresh = false)
            ?: throw unavailable(targetProfile)

        try {
            return when (auth) {
                is MdbListAuth.OAuth -> block("Bearer ${auth.accessToken}", null)
                is MdbListAuth.ApiKey -> block(null, auth.apiKey)
            }
        } catch (e: retrofit2.HttpException) {
            if (e.code() != 401) throw e
            if (auth is MdbListAuth.ApiKey) {
                if (markRejected(targetProfile, auth)) throw MdbListAuthUnavailable(reconnectRequired = true)
                throw e
            }
            val rejected = (auth as MdbListAuth.OAuth).accessToken
            val renewedAuth = resolveAuth(targetProfile, forceRefresh = true, rejectedAccessToken = rejected)
                as? MdbListAuth.OAuth ?: throw unavailable(targetProfile)
            try {
                return block("Bearer ${renewedAuth.accessToken}", null)
            } catch (retry: retrofit2.HttpException) {
                if (retry.code() == 401 && markRejected(targetProfile, renewedAuth)) {
                    throw MdbListAuthUnavailable(reconnectRequired = true)
                }
                throw retry
            }
        }
    }

    /** Initiates OAuth 2.0 Device Code flow. */
    suspend fun requestDeviceCode(clientId: String): MdbDeviceAuthorizationResponse = withContext(Dispatchers.IO) {
        api.requestDeviceAuthorization(clientId = clientId.trim())
    }

    /** Polls for OAuth token with user-approved device code. */
    suspend fun pollDeviceToken(deviceCode: String, clientId: String): MdbTokenResponse = withContext(Dispatchers.IO) {
        api.pollDeviceToken(deviceCode = deviceCode.trim(), clientId = clientId.trim())
    }

    /** Saves OAuth access & refresh tokens and notifies SyncProviderStore. */
    suspend fun saveTokens(tokenResponse: MdbTokenResponse, profileId: String? = null) {
        val targetProfile = profileId ?: profileManager.getProfileIdSync()
        store.setMdbListOAuthTokens(
            accessToken = tokenResponse.accessToken,
            refreshToken = tokenResponse.refreshToken,
            expiresInSeconds = tokenResponse.expiresIn,
            profileId = targetProfile
        )
        store.onProviderConnected(com.arflix.tv.data.repository.sync.SyncProvider.MDBLIST, targetProfile)
    }

    /** Disconnects MDBList by clearing both OAuth tokens and legacy API keys. */
    suspend fun disconnect(profileId: String? = null) {
        val targetProfile = profileId ?: profileManager.getProfileIdSync()
        store.clearAllMdbListCredentials(targetProfile)
        store.onProviderDisconnected(com.arflix.tv.data.repository.sync.SyncProvider.MDBLIST, targetProfile)
    }

    /** Validates an MDBList API key directly without creating bearer tokens. */
    suspend fun validateKey(key: String): Boolean = withContext(Dispatchers.IO) {
        val trimmed = key.trim()
        if (trimmed.isEmpty()) return@withContext false
        try {
            api.getUser(authHeader = null, apiKey = trimmed).username != null
        } catch (e: kotlinx.coroutines.CancellationException) {
            throw e
        } catch (_: Exception) {
            false
        }
    }

    /**
     * Fetches the MDBList username for the currently connected API key or token.
     * Returns null gracefully if not connected or if the request fails.
     */
    suspend fun fetchUsername(profileId: String? = null): String? = checkSession(profileId).getOrNull()

    /** The account's username, or the failure that kept MDBList from confirming the credential. */
    suspend fun checkSession(profileId: String? = null): Result<String?> = withContext(Dispatchers.IO) {
        try {
            Result.success(executeWithAuth(profileId) { authHeader, apiKey ->
                api.getUser(authHeader = authHeader, apiKey = apiKey).username
            })
        } catch (e: kotlinx.coroutines.CancellationException) {
            throw e
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Extra title ratings shown on details pages. The 12-hour cache keeps this
     * useful without spending the user's MDBList request allowance on every visit.
     */
    suspend fun getExternalRatings(mediaType: MediaType, tmdbId: Int): List<MdbExternalRating> =
        withContext(Dispatchers.IO) {
            val auth = resolveAuth() ?: return@withContext emptyList()
            val cacheKey = "${auth.hashCode()}:${mediaType.name}:$tmdbId"
            ratingsCache[cacheKey]?.takeIf { System.currentTimeMillis() - it.storedAt < RATINGS_CACHE_TTL_MS }
                ?.let { return@withContext it.ratings }
            try {
                val apiType = if (mediaType == MediaType.MOVIE) "movie" else "show"
                val ratings = executeWithAuth { authHeader, apiKey ->
                    api.getMediaInfo(
                        mediaType = apiType,
                        mediaId = tmdbId,
                        authHeader = authHeader,
                        apiKey = apiKey
                    )
                }?.ratings
                    .orEmpty()
                    .mapNotNull(::normalizeRating)
                    .distinctBy { it.source }
                    .sortedBy { RATING_SOURCE_ORDER.indexOf(it.source).let { index -> if (index < 0) Int.MAX_VALUE else index } }
                    .take(4)
                ratingsCache[cacheKey] = RatingsCacheEntry(System.currentTimeMillis(), ratings)
                ratings
            } catch (e: Exception) {
                if (e is kotlinx.coroutines.CancellationException) throw e
                AppLogger.w(TAG, "MDBList ratings fetch failed: ${e.message}")
                emptyList()
            }
        }

    private fun normalizeRating(rating: MdbRating): MdbExternalRating? {
        val source = rating.source?.lowercase()?.trim().orEmpty()
        if (source == "imdb") return null
        val label = RATING_LABELS[source] ?: return null
        val value = when (source) {
            "tomatoes", "popcorn", "metacritic", "metacriticuser" ->
                rating.score.asRatingNumber()?.let { "${it.roundToInt()}%" }
            "letterboxd", "rogerebert" -> rating.value.asRatingNumber()?.let(::formatDecimalRating)
            else -> rating.value.asRatingNumber()?.let(::formatDecimalRating)
                ?: rating.score.asRatingNumber()?.let { formatDecimalRating(it / 10.0) }
        } ?: return null
        return MdbExternalRating(source = source, label = label, value = value)
    }

    private fun formatDecimalRating(value: Double): String =
        if (value % 1.0 == 0.0) value.roundToInt().toString() else String.format(java.util.Locale.US, "%.1f", value)

    private fun com.google.gson.JsonElement?.asRatingNumber(): Double? =
        this?.takeUnless { it.isJsonNull }?.runCatching { asString.toDoubleOrNull() }?.getOrNull()

    private companion object {
        const val RENEWAL_REFUSALS_BEFORE_RECONNECT = 3
        const val RATINGS_CACHE_TTL_MS = 12 * 60 * 60 * 1000L
        val RATING_SOURCE_ORDER = listOf(
            "tomatoes", "popcorn", "metacritic", "letterboxd", "trakt", "tmdb", "myanimelist", "rogerebert", "metacriticuser"
        )
        val RATING_LABELS = mapOf(
            "tomatoes" to "RT Critics",
            "popcorn" to "RT Audience",
            "metacritic" to "Metacritic",
            "metacriticuser" to "Metacritic Users",
            "letterboxd" to "Letterboxd",
            "trakt" to "Trakt",
            "tmdb" to "TMDB",
            "myanimelist" to "MAL",
            "rogerebert" to "Roger Ebert"
        )
    }

    // ===== Watchlist =====

    suspend fun addToWatchlist(mediaType: MediaType, tmdbId: Int): Boolean =
        modifyWatchlist(mediaType, tmdbId, "add")

    suspend fun removeFromWatchlist(mediaType: MediaType, tmdbId: Int): Boolean =
        modifyWatchlist(mediaType, tmdbId, "remove")

    private suspend fun modifyWatchlist(
        mediaType: MediaType,
        tmdbId: Int,
        action: String
    ): Boolean = withContext(Dispatchers.IO) {
        try {
            val body = if (mediaType == MediaType.MOVIE) {
                MdbWatchlistModifyBody(movies = listOf(MdbTmdbRef(tmdbId)))
            } else {
                MdbWatchlistModifyBody(shows = listOf(MdbTmdbRef(tmdbId)))
            }
            val result = executeWithAuth { authHeader, apiKey ->
                api.modifyWatchlist(action = action, authHeader = authHeader, apiKey = apiKey, body = body)
            }
            result != null
        } catch (e: Exception) {
            if (e is kotlinx.coroutines.CancellationException) throw e
            AppLogger.e(TAG, "watchlist $action failed", e)
            false
        }
    }

    // ===== The user's own lists =====
    // Shapes live-checked 2026-10-21: `lists/user` rows carry id/name/slug/user_name/dynamic;
    // `lists/{id}/items?unified=true` is a flat array of {id, mediatype, title, release_year,
    // ids:{tmdb,...}}. Both are fetched with no-cache (MDBList marks them public for 15 min).

    /**
     * The user's lists. Dynamic (filter-based) lists cannot take items, so "Add to list" asks
     * with [includeDynamic] = false; the Library shows them all.
     */
    suspend fun getUserLists(includeDynamic: Boolean = false): List<MdbUserList> = withContext(Dispatchers.IO) {
        if (!isConnected()) return@withContext emptyList()
        try {
            libraryUserLists(includeDynamic)
        } catch (e: Exception) {
            if (e is kotlinx.coroutines.CancellationException) throw e
            AppLogger.e(TAG, "user lists failed", e)
            emptyList()
        }
    }

    /** [getUserLists] for the Library, which must tell a failed read from having no lists. */
    suspend fun libraryUserLists(includeDynamic: Boolean = true): List<MdbUserList> = withContext(Dispatchers.IO) {
        executeWithAuth { authHeader, apiKey -> api.getUserLists(authHeader = authHeader, apiKey = apiKey) }
            .orEmpty()
            .filter { it.id > 0 && (includeDynamic || it.dynamic != true) }
    }

    /**
     * The titles in one of the user's lists, in list order; empty when it cannot be read.
     * The API returns at most [LIST_ITEMS_PAGE] titles per request, so every page is read: a
     * missing page would leave titles out of the Library and show them unticked in the picker.
     */
    suspend fun listItems(listId: Long): List<MediaItem> = try {
        libraryListItems(listId)
    } catch (e: Exception) {
        if (e is kotlinx.coroutines.CancellationException) throw e
        AppLogger.e(TAG, "list items failed", e)
        emptyList()
    }

    /** [listItems] for the Library: a failed page throws instead of reading as an empty list. */
    suspend fun libraryListItems(listId: Long): List<MediaItem> = withContext(Dispatchers.IO) {
        val rows = fetchAllPages(LIST_ITEMS_PAGE, LIST_ITEMS_MAX_PAGES) { offset ->
            val json = executeWithAuth { authHeader, apiKey ->
                api.getListItems(
                    listId = listId,
                    authHeader = authHeader,
                    apiKey = apiKey,
                    limit = LIST_ITEMS_PAGE,
                    offset = offset
                )
            } ?: return@fetchAllPages null
            when {
                json.isJsonArray -> json.asJsonArray.toList()
                json.isJsonObject -> listOf("items", "movies", "shows")
                    .flatMap { key -> json.asJsonObject.getAsJsonArray(key)?.toList().orEmpty() }
                else -> emptyList()
            }
        } ?: throw IllegalStateException("MDBList list $listId could not be read")
        rows.mapIndexedNotNull { index, row ->
            val obj = row.takeIf { it.isJsonObject }?.asJsonObject ?: return@mapIndexedNotNull null
            fun text(name: String) = obj.get(name)?.takeIf { it.isJsonPrimitive }?.asString
            val tmdb = obj.getAsJsonObject("ids")?.get("tmdb")?.takeIf { it.isJsonPrimitive }?.asString?.toIntOrNull()
                ?: text("tmdb_id")?.toIntOrNull()
                ?: return@mapIndexedNotNull null
            MediaItem(
                id = tmdb,
                title = text("title").orEmpty(),
                year = text("release_year").orEmpty(),
                mediaType = if (text("mediatype") == "movie") MediaType.MOVIE else MediaType.TV,
                sourceOrder = index
            )
        }.distinctBy { it.mediaType to it.id }
    }

    /** Keys ("MOVIE:tmdbId" / "TV:tmdbId") of the titles in one of the user's lists. */
    suspend fun listItemKeys(listId: Long): Set<String> =
        listItems(listId).map { "${it.mediaType.name}:${it.id}" }.toSet()

    /** Adds a title to, or removes it from, one of the user's lists; true only when MDBList applied it. */
    suspend fun modifyList(listId: Long, mediaType: MediaType, tmdbId: Int, add: Boolean): Boolean =
        withContext(Dispatchers.IO) {
            try {
                val body = if (mediaType == MediaType.MOVIE) {
                    MdbWatchlistModifyBody(movies = listOf(MdbTmdbRef(tmdbId)))
                } else {
                    MdbWatchlistModifyBody(shows = listOf(MdbTmdbRef(tmdbId)))
                }
                val response = executeWithAuth { authHeader, apiKey ->
                    api.modifyList(
                        listId = listId,
                        action = if (add) "add" else "remove",
                        authHeader = authHeader,
                        apiKey = apiKey,
                        body = body
                    )
                }
                listChangeAccepted(response, mediaType, add).also { accepted ->
                    if (!accepted) AppLogger.w(TAG, "list ${if (add) "add" else "remove"} not applied: $response")
                }
            } catch (e: Exception) {
                if (e is kotlinx.coroutines.CancellationException) throw e
                AppLogger.e(TAG, "list ${if (add) "add" else "remove"} failed", e)
                false
            }
        }

    suspend fun getWatchlist(): RemoteWatchlistResult = withContext(Dispatchers.IO) {
        if (!isConnected()) return@withContext RemoteWatchlistResult(connected = false, items = null, rawCount = 0)
        try {
            val raw = fetchAllWatchlistItems()
            val items = raw
                .mapIndexedNotNull { index, item -> mapWatchlistItem(item, index) }
                .sortedWith(compareBy<MediaItem> { it.sourceOrder }.thenByDescending { it.addedAt })
            RemoteWatchlistResult(connected = true, items = items, rawCount = raw.size)
        } catch (e: Exception) {
            if (e is kotlinx.coroutines.CancellationException) throw e
            AppLogger.e(TAG, "watchlist fetch failed", e)
            RemoteWatchlistResult(connected = true, items = null, rawCount = 0)
        }
    }

    private suspend fun fetchAllWatchlistItems(): List<MdbWatchlistItem> {
        val targetProfile = profileManager.getProfileIdSync()
        val all = mutableListOf<MdbWatchlistItem>()
        val limit = 1000
        var offset = 0
        while (true) {
            val page = executeWithAuth(targetProfile) { authHeader, apiKey ->
                api.getWatchlistItems(
                    authHeader = authHeader,
                    apiKey = apiKey,
                    limit = limit,
                    offset = offset,
                    unified = "true"
                )
            } ?: break
            all.addAll(page)
            if (page.size < limit) break
            offset += limit
        }
        return all
    }

    private fun mapWatchlistItem(item: MdbWatchlistItem, sourceOrder: Int): MediaItem? {
        val tmdbId = item.ids?.tmdb ?: item.id ?: return null
        val type = if (item.mediatype.equals("show", ignoreCase = true)) MediaType.TV else MediaType.MOVIE
        val year = item.releaseYear?.toString()
            ?: item.releaseDate?.take(4).orEmpty()
        return MediaItem(
            id = tmdbId,
            title = item.title.orEmpty(),
            mediaType = type,
            year = year,
            releaseDate = item.releaseDate,
            addedAt = parseIsoMillis(item.watchlistAt),
            sourceOrder = sourceOrder
        )
    }

    // ===== Scrobble =====

    suspend fun scrobble(
        action: String,
        mediaType: MediaType,
        tmdbId: Int,
        progress: Float,
        season: Int?,
        episode: Int?
    ) = withContext(Dispatchers.IO) {
        val prog = (progress.coerceIn(0f, 100f) * 100f).roundToInt() / 100f
        val body = if (mediaType == MediaType.MOVIE) {
            MdbScrobbleBody(progress = prog, movie = MdbScrobbleMovie(MdbIds(tmdb = tmdbId)))
        } else {
            if (season == null || episode == null) return@withContext
            MdbScrobbleBody(
                progress = prog,
                show = MdbScrobbleShow(
                    ids = MdbIds(tmdb = tmdbId),
                    season = MdbScrobbleSeason(number = season, episode = MdbScrobbleEpisodeNumber(episode))
                )
            )
        }
        try {
            executeWithAuth { authHeader, apiKey ->
                api.scrobble(action = action, authHeader = authHeader, apiKey = apiKey, body = body)
            }
        } catch (e: Exception) {
            if (e is kotlinx.coroutines.CancellationException) throw e
            AppLogger.e(TAG, "scrobble $action failed", e)
        }
    }

    /** Clear a paused session so it drops out of Continue Watching. */
    suspend fun clearPlayback(mediaType: MediaType, tmdbId: Int, season: Int?, episode: Int?) =
        withContext(Dispatchers.IO) {
            val body = if (mediaType == MediaType.MOVIE) {
                MdbScrobbleClearBody(movie = MdbScrobbleMovie(MdbIds(tmdb = tmdbId)))
            } else {
                if (season == null || episode == null) return@withContext
                MdbScrobbleClearBody(
                    show = MdbScrobbleShow(
                        ids = MdbIds(tmdb = tmdbId),
                        season = MdbScrobbleSeason(number = season, episode = MdbScrobbleEpisodeNumber(episode))
                    )
                )
            }
            try {
                executeWithAuth { authHeader, apiKey ->
                    api.scrobbleClear(authHeader = authHeader, apiKey = apiKey, body = body)
                }
            } catch (e: Exception) {
                if (e is kotlinx.coroutines.CancellationException) throw e
                AppLogger.e(TAG, "scrobble clear failed", e)
            }
        }

    // ===== Watched mirror (Trakt-style ids objects) =====

    suspend fun markMovieWatched(tmdbId: Int): Boolean = watchedCall { authHeader, apiKey ->
        api.addWatched(authHeader = authHeader, apiKey = apiKey, body = MdbWatchedBody(movies = listOf(MdbIdsItem(MdbIds(tmdb = tmdbId)))))
    }

    suspend fun markMovieUnwatched(tmdbId: Int): Boolean = watchedCall { authHeader, apiKey ->
        api.removeWatched(authHeader = authHeader, apiKey = apiKey, body = MdbWatchedBody(movies = listOf(MdbIdsItem(MdbIds(tmdb = tmdbId)))))
    }

    suspend fun markEpisodeWatched(showTmdbId: Int, season: Int, episode: Int): Boolean = watchedCall { authHeader, apiKey ->
        api.addWatched(authHeader = authHeader, apiKey = apiKey, body = episodeBody(showTmdbId, season, episode))
    }

    suspend fun markEpisodeUnwatched(showTmdbId: Int, season: Int, episode: Int): Boolean = watchedCall { authHeader, apiKey ->
        api.removeWatched(authHeader = authHeader, apiKey = apiKey, body = episodeBody(showTmdbId, season, episode))
    }

    /** Batch-mark a whole season's episodes watched in one /sync/watched call. */
    suspend fun markSeasonWatched(showTmdbId: Int, season: Int, episodes: List<Int>): Boolean = watchedCall { authHeader, apiKey ->
        api.addWatched(
            authHeader = authHeader,
            apiKey = apiKey,
            body = MdbWatchedBody(
                shows = listOf(
                    MdbWatchedShowRef(
                        ids = MdbIds(tmdb = showTmdbId),
                        seasons = listOf(
                            MdbWatchedSeasonRef(number = season, episodes = episodes.map { n -> MdbWatchedEpisodeRef(n) })
                        )
                    )
                )
            )
        )
    }

    private fun episodeBody(showTmdbId: Int, season: Int, episode: Int) = MdbWatchedBody(
        shows = listOf(
            MdbWatchedShowRef(
                ids = MdbIds(tmdb = showTmdbId),
                seasons = listOf(
                    MdbWatchedSeasonRef(number = season, episodes = listOf(MdbWatchedEpisodeRef(episode)))
                )
            )
        )
    )

    private suspend fun watchedCall(block: suspend (authHeader: String?, apiKey: String?) -> Any): Boolean = withContext(Dispatchers.IO) {
        try {
            val result = executeWithAuth { authHeader, apiKey ->
                block(authHeader, apiKey)
            }
            result != null
        } catch (e: Exception) {
            if (e is kotlinx.coroutines.CancellationException) throw e
            AppLogger.e(TAG, "watched mirror failed", e)
            false
        }
    }

    // ===== Watched reads =====

    suspend fun getWatchedSnapshot(): Result<MdbWatchedSnapshot> = withContext(Dispatchers.IO) {
        val targetProfile = profileManager.getProfileIdSync()
        if (!isConnected(targetProfile)) return@withContext Result.failure(IllegalStateException("MDBList is not connected"))
        try {
            val movies = mutableSetOf<Int>()
            val episodes = mutableSetOf<String>()
            var offset = 0
            val limit = 1000
            while (true) {
                val response = executeWithAuth(targetProfile) { authHeader, apiKey ->
                    api.getWatched(authHeader = authHeader, apiKey = apiKey, limit = limit, offset = offset)
                } ?: break
                response.movies?.forEach { row ->
                    row.movie?.ids?.tmdb?.let(movies::add)
                }
                response.episodes?.forEach { row ->
                    val episode = row.episode ?: return@forEach
                    val showTmdb = episode.show?.ids?.tmdb ?: return@forEach
                    val season = episode.season ?: return@forEach
                    val number = episode.number ?: return@forEach
                    episodes.add("show_tmdb:$showTmdb:$season:$number")
                }
                if (response.pagination?.hasMore != true) break
                offset += limit
            }
            Result.success(MdbWatchedSnapshot(movies, episodes))
        } catch (e: kotlinx.coroutines.CancellationException) {
            throw e
        } catch (e: Exception) {
            AppLogger.e(TAG, "watched snapshot fetch failed", e)
            Result.failure(e)
        }
    }

    suspend fun getWatchedMovies(): Set<Int> = withContext(Dispatchers.IO) {
        val targetProfile = profileManager.getProfileIdSync()
        try {
            val out = mutableSetOf<Int>()
            var offset = 0
            val limit = 1000
            while (true) {
                val resp = executeWithAuth(targetProfile) { authHeader, apiKey ->
                    api.getWatched(authHeader = authHeader, apiKey = apiKey, limit = limit, offset = offset)
                } ?: break
                resp.movies?.forEach { row -> row.movie?.ids?.tmdb?.let { out.add(it) } }
                if (resp.pagination?.hasMore != true) break
                offset += limit
            }
            out
        } catch (e: Exception) {
            if (e is kotlinx.coroutines.CancellationException) throw e
            emptySet()
        }
    }

    /**
     * Per-show watched progress from /sync/watched, grouped by show tmdb id. Powers MDBList's
     * "Now Playing" (up-next) — shows with episodes watched but not finished.
     */
    suspend fun getWatchedShowsProgress(): List<MdbShowWatchedProgress> = withContext(Dispatchers.IO) {
        val targetProfile = profileManager.getProfileIdSync()
        try {
            class Acc(var title: String, var year: String) {
                val eps = mutableMapOf<Int, MutableSet<Int>>()
                var lastMs = 0L
            }
            val byShow = mutableMapOf<Int, Acc>()
            var offset = 0
            val limit = 1000
            while (true) {
                val resp = executeWithAuth(targetProfile) { authHeader, apiKey ->
                    api.getWatched(authHeader = authHeader, apiKey = apiKey, limit = limit, offset = offset)
                } ?: break
                resp.episodes?.forEach { row ->
                    val ep = row.episode ?: return@forEach
                    val showTmdb = ep.show?.ids?.tmdb ?: return@forEach
                    val s = ep.season ?: return@forEach
                    val e = ep.number ?: return@forEach
                    val acc = byShow.getOrPut(showTmdb) {
                        Acc(ep.show?.title.orEmpty(), ep.show?.year?.toString().orEmpty())
                    }
                    if (acc.title.isBlank()) ep.show?.title?.let { acc.title = it }
                    acc.eps.getOrPut(s) { mutableSetOf() }.add(e)
                    val ts = parseIsoMillis(row.lastWatchedAt)
                    if (ts > acc.lastMs) acc.lastMs = ts
                }
                if (resp.pagination?.hasMore != true) break
                offset += limit
            }
            byShow.map { (id, acc) ->
                MdbShowWatchedProgress(
                    showTmdbId = id,
                    title = acc.title,
                    year = acc.year,
                    watchedBySeason = acc.eps.mapValues { it.value.toSet() },
                    lastWatchedAtMs = acc.lastMs
                )
            }
        } catch (e: Exception) {
            if (e is kotlinx.coroutines.CancellationException) throw e
            AppLogger.e(TAG, "watched shows progress fetch failed", e)
            emptyList()
        }
    }

    suspend fun getWatchedEpisodes(): Set<String> = withContext(Dispatchers.IO) {
        val targetProfile = profileManager.getProfileIdSync()
        try {
            val out = mutableSetOf<String>()
            var offset = 0
            val limit = 1000
            while (true) {
                val resp = executeWithAuth(targetProfile) { authHeader, apiKey ->
                    api.getWatched(authHeader = authHeader, apiKey = apiKey, limit = limit, offset = offset)
                } ?: break
                resp.episodes?.forEach { row ->
                    val ep = row.episode ?: return@forEach
                    val showTmdb = ep.show?.ids?.tmdb ?: return@forEach
                    val s = ep.season ?: return@forEach
                    val e = ep.number ?: return@forEach
                    out.add("show_tmdb:$showTmdb:$s:$e")
                }
                if (resp.pagination?.hasMore != true) break
                offset += limit
            }
            out
        } catch (e: Exception) {
            if (e is kotlinx.coroutines.CancellationException) throw e
            emptySet()
        }
    }

    // ===== Continue Watching (paused sessions) =====

    suspend fun getContinueWatching(forceRefresh: Boolean = false): List<ContinueWatchingItem> = withContext(Dispatchers.IO) {
        try {
            val items = executeWithAuth { authHeader, apiKey ->
                api.getPlayback(
                    authHeader = authHeader,
                    apiKey = apiKey,
                    cacheControl = if (forceRefresh) "no-cache" else null
                )
            } ?: return@withContext emptyList()
            items.mapNotNull { mapPlaybackItem(it) }
                .sortedByDescending { it.updatedAtMs }
                .distinctBy { it.mediaType to it.id }
                .take(Constants.MAX_CONTINUE_WATCHING)
        } catch (e: Exception) {
            if (e is kotlinx.coroutines.CancellationException) throw e
            AppLogger.e(TAG, "continue watching fetch failed", e)
            emptyList()
        }
    }

    private fun mapPlaybackItem(item: MdbPlaybackItem): ContinueWatchingItem? {
        val progress = item.progress?.toFloatOrNull()?.roundToInt() ?: return null
        if (progress <= 0 || progress >= Constants.WATCHED_THRESHOLD) return null
        val durationSeconds = (item.runtime ?: 0).toLong() * 60L
        val updatedMs = item.updatedAtTs?.let { it * 1000L } ?: parseIsoMillis(item.updatedAt)

        return if (item.type == "movie") {
            val movie = item.movie ?: return null
            val tmdbId = movie.ids?.tmdb ?: return null
            ContinueWatchingItem(
                id = tmdbId,
                title = movie.title.orEmpty(),
                mediaType = MediaType.MOVIE,
                progress = progress.coerceIn(0, 100),
                durationSeconds = durationSeconds,
                year = movie.year?.toString().orEmpty(),
                updatedAtMs = updatedMs
            )
        } else {
            val show = item.show ?: return null
            val ep = item.episode ?: return null
            val tmdbId = show.ids?.tmdb ?: return null
            val season = ep.season ?: return null
            val number = ep.number ?: return null
            ContinueWatchingItem(
                id = tmdbId,
                title = show.title.orEmpty(),
                mediaType = MediaType.TV,
                progress = progress.coerceIn(0, 100),
                durationSeconds = durationSeconds,
                season = season,
                episode = number,
                episodeTitle = ep.name,
                year = show.year?.toString().orEmpty(),
                updatedAtMs = updatedMs
            )
        }
    }

    private fun parseIsoMillis(iso: String?): Long {
        if (iso.isNullOrBlank()) return 0L
        return try {
            java.time.Instant.parse(iso).toEpochMilli()
        } catch (e: Exception) {
            if (e is kotlinx.coroutines.CancellationException) throw e
            0L
        }
    }
}
