package com.arflix.tv.data.repository

import android.content.Context

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.arflix.tv.data.api.*
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.model.NextEpisode
import com.arflix.tv.util.ContinueWatchingSelector
import com.arflix.tv.util.EpisodePointer
import com.arflix.tv.util.EpisodeProgressSnapshot
import com.arflix.tv.util.WatchedEpisodeSnapshot
import com.arflix.tv.util.Constants
import com.arflix.tv.util.AppLogger
import com.arflix.tv.util.settingsDataStore
import com.arflix.tv.util.traktDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.launch
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.delay
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.sync.withPermit
import kotlinx.coroutines.withContext
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.put
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.HttpException
import java.text.Normalizer
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.ConcurrentHashMap
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

/**
 * Repository for Trakt.tv API interactions
 *
 * This repository now uses TraktSyncService for watched state management,
 * which ensures Supabase is the source of truth for all watched data.
 *
 * Key changes:
 * - Watched state queries Supabase, not local cache
 * - Mark watched/unwatched writes to Supabase first, then syncs to Trakt
 * - Continue Watching uses Supabase data augmented with Trakt progress API
 */
@Singleton
class TraktRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val traktApi: TraktApi,
    private val tmdbApi: TmdbApi,
    private val syncServiceProvider: Provider<TraktSyncService>,
    private val profileManager: ProfileManager
) {
    private val gson = Gson()
    private val watchlistHttpClient by lazy { OkHttpClient() }

    // Lazy sync service to avoid circular dependency
    private val syncService: TraktSyncService by lazy { syncServiceProvider.get() }

    // Supabase client for profile sync (lazy to avoid startup overhead)
    private val supabase: SupabaseClient by lazy {
        createSupabaseClient(
            supabaseUrl = Constants.SUPABASE_URL,
            supabaseKey = Constants.SUPABASE_ANON_KEY
        ) {
            install(Postgrest)
        }
    }

    // User ID key for Supabase sync (shared across profiles)
    private val USER_ID_KEY = stringPreferencesKey("user_id")
    private val clientId = Constants.TRAKT_CLIENT_ID
    private val clientSecret = Constants.TRAKT_CLIENT_SECRET
    // Profile-scoped preference keys - each profile has its own Trakt connection
    private fun accessTokenKey() = profileManager.profileStringKey("trakt_access_token")
    private fun refreshTokenKey() = profileManager.profileStringKey("trakt_refresh_token")
    private fun expiresAtKey() = profileManager.profileLongKey("trakt_expires_at")
    private fun includeSpecialsKey() = profileManager.profileBooleanKey("trakt_include_specials")
    private fun dismissedContinueWatchingKey() = profileManager.profileStringKey("trakt_dismissed_continue_watching_v1")
    private fun continueWatchingCacheKey() = profileManager.profileStringKey("trakt_continue_watching_cache_v4")
    // Local Continue Watching for profiles without Trakt - stores progress locally per profile
    private fun localContinueWatchingKey() = profileManager.profileStringKey("local_continue_watching_v1")
    private fun localWatchedMoviesKey() = profileManager.profileStringKey("local_watched_movies_v1")
    private fun localWatchedEpisodesKey() = profileManager.profileStringKey("local_watched_episodes_v1")

    data class CloudTraktToken(
        val accessToken: String,
        val refreshToken: String?,
        val expiresAt: Long?
    )

    @Volatile private var activeCacheProfileId: String? = null
    @Volatile private var cachedContinueWatching: List<ContinueWatchingItem> = emptyList()
    @Volatile private var cachedContinueWatchingProfileId: String? = null
    @Volatile private var continueWatchingFetching = false
    @Volatile private var continueWatchingFetchingProfileId: String? = null
    private var lastContinueWatchingFetch = 0L
    private val CONTINUE_WATCHING_CACHE_MS = 300_000L // 5 minute cache to reduce API calls and improve performance
    private val TRAKT_UP_NEXT_RECENT_WINDOW_MS = 548L * 24L * 60L * 60L * 1000L // 18 months
    @Volatile private var tokenRefreshBackoffUntilMs: Long = 0L
    private val TOKEN_REFRESH_RETRY_BACKOFF_MS = 5 * 60 * 1000L
    private val tokenRefreshMutex = Mutex()

    private fun currentProfileId(): String = profileManager.getProfileIdSync().ifBlank { "default" }

    private fun ensureProfileCacheScope() {
        val profileId = currentProfileId()
        if (activeCacheProfileId == profileId) return
        activeCacheProfileId = profileId
        clearProfileScopedMemoryCaches(clearPreloaded = false)
    }

    private fun clearProfileScopedMemoryCaches(clearPreloaded: Boolean) {
        watchedMoviesCache.clear()
        watchedEpisodesCache.clear()
        cacheInitialized = false
        cacheInitializing = false
        showWatchedEpisodesCache.clear()
        showWatchedCacheTime = 0L
        showCompletionCache.clear()
        tmdbToTraktIdCache.clear()
        cachedContinueWatching = emptyList()
        cachedContinueWatchingProfileId = null
        lastContinueWatchingFetch = 0L
        continueWatchingFetching = false
        continueWatchingFetchingProfileId = null
        lastScrobbleKey = null
        lastScrobbleTime = 0L
        if (clearPreloaded) {
            preloadedProfileCache.clear()
        }
    }

    // ========== Authentication ==========

    /**
     * Check if current profile is authenticated with Trakt
     */
    val isAuthenticated: Flow<Boolean> = context.traktDataStore.data.map { prefs ->
        prefs[accessTokenKey()] != null
    }

    /**
     * Get token expiration timestamp (seconds since epoch) for current profile
     */
    suspend fun getTokenExpiration(): Long? {
        val prefs = context.traktDataStore.data.first()
        return prefs[expiresAtKey()]
    }

    /**
     * Get formatted token expiration date
     */
    suspend fun getTokenExpirationDate(): String? {
        val expiresAt = getTokenExpiration() ?: return null
        val expirationDate = java.time.Instant.ofEpochSecond(expiresAt)
        val formatter = java.time.format.DateTimeFormatter
            .ofPattern("MMM dd, yyyy")
            .withZone(java.time.ZoneId.systemDefault())
        return formatter.format(expirationDate)
    }

    suspend fun getDeviceCode(): TraktDeviceCode {
        return traktApi.getDeviceCode(DeviceCodeRequest(clientId))
    }

    suspend fun pollForToken(deviceCode: String): TraktToken {
        val token = traktApi.pollToken(
            TokenPollRequest(
                code = deviceCode,
                clientId = clientId,
                clientSecret = clientSecret
            )
        )
        saveToken(token)
        return token
    }

    suspend fun refreshTokenIfNeeded(): String? {
        ensureProfileCacheScope()
        val prefs = context.traktDataStore.data.first()
        val accessToken = prefs[accessTokenKey()] ?: return null
        val refreshToken = prefs[refreshTokenKey()]
        val expiresAt = prefs[expiresAtKey()]

        // If we don't have refresh metadata (older tokens), use the existing access token
        if (refreshToken == null || expiresAt == null) {
            return accessToken
        }

        return tokenRefreshMutex.withLock {
            val lockedPrefs = context.traktDataStore.data.first()
            val lockedAccessToken = lockedPrefs[accessTokenKey()] ?: return@withLock null
            val lockedRefreshToken = lockedPrefs[refreshTokenKey()] ?: return@withLock lockedAccessToken
            val lockedExpiresAt = lockedPrefs[expiresAtKey()] ?: return@withLock lockedAccessToken
            val nowSeconds = System.currentTimeMillis() / 1000

            if (nowSeconds < lockedExpiresAt - 3600) {
                return@withLock lockedAccessToken
            }

            fun usableExistingToken(): String? {
                return if (nowSeconds < lockedExpiresAt) lockedAccessToken else null
            }

            val nowMs = System.currentTimeMillis()
            if (nowMs < tokenRefreshBackoffUntilMs) {
                return@withLock usableExistingToken()
            }

            try {
                val newToken = traktApi.refreshToken(
                    RefreshTokenRequest(
                        refreshToken = lockedRefreshToken,
                        clientId = clientId,
                        clientSecret = clientSecret
                    )
                )
                saveToken(newToken)
                tokenRefreshBackoffUntilMs = 0L
                newToken.accessToken
            } catch (e: HttpException) {
                val code = e.code()
                if (code == 429 || code >= 500) {
                    val retryAfterMs = e.response()
                        ?.headers()
                        ?.get("Retry-After")
                        ?.toLongOrNull()
                        ?.times(1000L)
                        ?.coerceAtLeast(30_000L)
                        ?: TOKEN_REFRESH_RETRY_BACKOFF_MS
                    tokenRefreshBackoffUntilMs = System.currentTimeMillis() + retryAfterMs
                    System.err.println("TraktRepo: token refresh deferred after HTTP $code")
                    usableExistingToken()
                } else {
                    System.err.println("TraktRepo: token refresh failed: HTTP $code")
                    null
                }
            } catch (e: Exception) {
                System.err.println("TraktRepo: token refresh failed: ${e.message}")
                tokenRefreshBackoffUntilMs = System.currentTimeMillis() + TOKEN_REFRESH_RETRY_BACKOFF_MS
                usableExistingToken()
            }
        }
    }

    private suspend fun saveToken(token: TraktToken) {
        ensureProfileCacheScope()
        context.traktDataStore.edit { prefs ->
            prefs[accessTokenKey()] = token.accessToken
            prefs[refreshTokenKey()] = token.refreshToken
            prefs[expiresAtKey()] = token.createdAt + token.expiresIn
        }
    }

    /**
     * Set the user ID for Supabase sync (called after login)
     */
    suspend fun setUserId(userId: String) {
        context.traktDataStore.edit { prefs ->
            prefs[USER_ID_KEY] = userId
        }
    }

    /**
     * Load tokens from Supabase profile
     */
    suspend fun loadTokensFromProfile(traktToken: JsonObject?) {
        // Legacy account-level Supabase trakt_token is intentionally ignored.
        // Per-profile Trakt tokens are restored through importTokensForProfiles.
    }

    suspend fun logout() {
        ensureProfileCacheScope()
        context.traktDataStore.edit { prefs ->
            prefs.remove(accessTokenKey())
            prefs.remove(refreshTokenKey())
            prefs.remove(expiresAtKey())
        }
        clearProfileScopedMemoryCaches(clearPreloaded = false)
    }

    /**
     * Export Trakt tokens for multiple profiles (for cloud backup).
     */
    suspend fun exportTokensForProfiles(profileIds: List<String>): Map<String, CloudTraktToken> {
        val prefs = context.traktDataStore.data.first()
        val out = LinkedHashMap<String, CloudTraktToken>()
        profileIds.forEach { profileId ->
            val access = prefs[profileManager.profileStringKeyFor(profileId, "trakt_access_token")] ?: return@forEach
            val refresh = prefs[profileManager.profileStringKeyFor(profileId, "trakt_refresh_token")]
            val expiresAt = prefs[profileManager.profileLongKeyFor(profileId, "trakt_expires_at")]
            out[profileId] = CloudTraktToken(accessToken = access, refreshToken = refresh, expiresAt = expiresAt)
        }
        return out
    }

    /**
     * Import Trakt tokens for multiple profiles (for cloud restore).
     */
    suspend fun importTokensForProfiles(tokens: Map<String, CloudTraktToken>) {
        if (tokens.isEmpty()) return
        context.traktDataStore.edit { prefs ->
            tokens.forEach { (profileId, token) ->
                prefs[profileManager.profileStringKeyFor(profileId, "trakt_access_token")] = token.accessToken
                token.refreshToken?.let { prefs[profileManager.profileStringKeyFor(profileId, "trakt_refresh_token")] = it }
                token.expiresAt?.let { prefs[profileManager.profileLongKeyFor(profileId, "trakt_expires_at")] = it }
            }
        }
        clearProfileScopedMemoryCaches(clearPreloaded = false)
    }

    suspend fun exportDismissedContinueWatchingForProfiles(profileIds: List<String>): Map<String, String> {
        val prefs = context.settingsDataStore.data.first()
        val out = LinkedHashMap<String, String>()
        profileIds.forEach { profileId ->
            val key = profileManager.profileStringKeyFor(profileId, "trakt_dismissed_continue_watching_v1")
            val raw = prefs[key]?.trim().orEmpty()
            if (raw.isNotEmpty()) {
                out[profileId] = raw
            }
        }
        return out
    }

    suspend fun importDismissedContinueWatchingForProfiles(values: Map<String, String>) {
        context.settingsDataStore.edit { prefs ->
            values.forEach { (profileId, raw) ->
                val key = profileManager.profileStringKeyFor(profileId, "trakt_dismissed_continue_watching_v1")
                val value = raw.trim()
                if (value.isEmpty()) {
                    prefs.remove(key)
                } else {
                    prefs[key] = value
                }
            }
        }
    }

    suspend fun exportLocalContinueWatchingForProfiles(profileIds: List<String>): Map<String, List<ContinueWatchingItem>> {
        val prefs = context.traktDataStore.data.first()
        val out = LinkedHashMap<String, List<ContinueWatchingItem>>()
        profileIds.forEach { profileId ->
            val key = profileManager.profileStringKeyFor(profileId, "local_continue_watching_v1")
            val json = prefs[key]?.trim().orEmpty()
            if (json.isBlank()) return@forEach
            val items = decodeContinueWatchingList(json)
            if (items.isNotEmpty()) {
                out[profileId] = items
            }
        }
        return out
    }

    suspend fun importLocalContinueWatchingForProfiles(values: Map<String, List<ContinueWatchingItem>>) {
        context.traktDataStore.edit { prefs ->
            values.forEach { (profileId, items) ->
                val key = profileManager.profileStringKeyFor(profileId, "local_continue_watching_v1")
                if (items.isEmpty()) {
                    prefs.remove(key)
                } else {
                    prefs[key] = gson.toJson(items.take(Constants.MAX_CONTINUE_WATCHING))
                }
            }
        }
    }

    suspend fun exportLocalWatchedMoviesForProfiles(profileIds: List<String>): Map<String, List<Int>> {
        val prefs = context.traktDataStore.data.first()
        val out = LinkedHashMap<String, List<Int>>()
        profileIds.forEach { profileId ->
            val key = profileManager.profileStringKeyFor(profileId, "local_watched_movies_v1")
            val json = prefs[key]?.trim().orEmpty()
            if (json.isBlank()) return@forEach
            val ids = decodeIntList(json)
            if (ids.isNotEmpty()) {
                out[profileId] = ids
            }
        }
        return out
    }

    suspend fun importLocalWatchedMoviesForProfiles(values: Map<String, List<Int>>) {
        context.traktDataStore.edit { prefs ->
            values.forEach { (profileId, ids) ->
                val key = profileManager.profileStringKeyFor(profileId, "local_watched_movies_v1")
                if (ids.isEmpty()) {
                    prefs.remove(key)
                } else {
                    prefs[key] = gson.toJson(ids.distinct())
                }
            }
        }
    }

    suspend fun exportLocalWatchedEpisodesForProfiles(profileIds: List<String>): Map<String, List<String>> {
        val prefs = context.traktDataStore.data.first()
        val out = LinkedHashMap<String, List<String>>()
        profileIds.forEach { profileId ->
            val key = profileManager.profileStringKeyFor(profileId, "local_watched_episodes_v1")
            val json = prefs[key]?.trim().orEmpty()
            if (json.isBlank()) return@forEach
            val keys = decodeStringList(json)
            if (keys.isNotEmpty()) {
                out[profileId] = keys
            }
        }
        return out
    }

    suspend fun importLocalWatchedEpisodesForProfiles(values: Map<String, List<String>>) {
        context.traktDataStore.edit { prefs ->
            values.forEach { (profileId, keys) ->
                val key = profileManager.profileStringKeyFor(profileId, "local_watched_episodes_v1")
                if (keys.isEmpty()) {
                    prefs.remove(key)
                } else {
                    prefs[key] = gson.toJson(keys.distinct())
                }
            }
        }
    }

    private suspend fun getAuthHeader(): String? {
        ensureProfileCacheScope()
        val token = refreshTokenIfNeeded() ?: return null
        return "Bearer $token"
    }

    private suspend fun hasStoredTraktTokenForCurrentProfile(): Boolean {
        ensureProfileCacheScope()
        val prefs = context.traktDataStore.data.first()
        return !prefs[accessTokenKey()].isNullOrBlank()
    }

    // ========== Watched History ==========

    suspend fun getWatchedMovies(): Set<Int> {
        val auth = getAuthHeader() ?: return emptySet()
        return try {
            val watched = traktApi.getWatchedMovies(auth, clientId)
            watched.mapNotNull { it.movie.ids.tmdb }.toSet()
        } catch (e: Exception) {
            emptySet()
        }
    }

    suspend fun getWatchedEpisodes(): Set<String> {
        val auth = getAuthHeader() ?: return emptySet()
        return try {
            val watched = traktApi.getWatchedShows(auth, clientId)
            val episodes = mutableSetOf<String>()
            watched.forEach { show ->
                val tmdbId = show.show.ids.tmdb ?: return@forEach
                show.seasons?.forEach { season ->
                    season.episodes.forEach { ep ->
                        buildEpisodeKey(
                            traktEpisodeId = null,
                            showTraktId = null,
                            showTmdbId = tmdbId,
                            season = season.number,
                            episode = ep.number
                        )?.let { episodes.add(it) }
                    }
                }
            }
            episodes
        } catch (e: Exception) {
            emptySet()
        }
    }

    /**
     * Mark movie as watched - updates local cache immediately (optimistic), then syncs to backend
     */
    suspend fun markMovieWatched(tmdbId: Int) {
        ensureProfileCacheScope()
        // OPTIMISTIC UPDATE: Update caches immediately so the UI responds instantly
        updateWatchedCache(tmdbId, null, null, true)
        persistLocalWatchedSnapshotForCurrentProfile()
        removeFromContinueWatchingCache(tmdbId, null, null)

        // Then sync to backend in background
        try {
            syncService.markMovieWatched(tmdbId)
        } catch (e: Exception) {
            // Sync failed, but local cache is already updated
        }
    }

    /**
     * Mark movie as unwatched - updates local cache immediately (optimistic), then syncs to backend
     */
    suspend fun markMovieUnwatched(tmdbId: Int) {
        ensureProfileCacheScope()
        // OPTIMISTIC UPDATE: Update cache immediately so the UI responds instantly
        updateWatchedCache(tmdbId, null, null, false)
        persistLocalWatchedSnapshotForCurrentProfile()

        // Then sync to backend in background
        try {
            syncService.markMovieUnwatched(tmdbId)
        } catch (e: Exception) {
            // Sync failed, but local cache is already updated
        }
    }

    /**
     * Mark episode as watched - updates local cache immediately (optimistic), then syncs to backend
     */
    suspend fun markEpisodeWatched(showTmdbId: Int, season: Int, episode: Int) {
        ensureProfileCacheScope()
        // OPTIMISTIC UPDATE: Update all caches immediately so the UI responds instantly
        updateWatchedCache(showTmdbId, season, episode, true)
        updateShowWatchedCache(showTmdbId, season, episode, true)
        persistLocalWatchedSnapshotForCurrentProfile()
        removeFromContinueWatchingCache(showTmdbId, season, episode)

        // Then sync to backend in background (don't block UI on network)
        try {
            val traktShowId = tmdbToTraktIdCache[showTmdbId]
            syncService.markEpisodeWatched(showTmdbId, season, episode, traktShowId)
        } catch (e: Exception) {
            AppLogger.e("TraktRepository", "Failed to mark episode watched", e)
        }
    }

    /**
     * Mark episode watched in local caches and Supabase without sending another Trakt request.
     */
    suspend fun markEpisodeWatchedWithoutTraktSync(showTmdbId: Int, season: Int, episode: Int) {
        ensureProfileCacheScope()
        updateWatchedCache(showTmdbId, season, episode, true)
        updateShowWatchedCache(showTmdbId, season, episode, true)
        persistLocalWatchedSnapshotForCurrentProfile()
        removeFromContinueWatchingCache(showTmdbId, season, episode)

        try {
            val traktShowId = tmdbToTraktIdCache[showTmdbId]
            syncService.markEpisodeWatchedInSupabaseOnly(showTmdbId, season, episode, traktShowId)
        } catch (e: Exception) {
            AppLogger.e("TraktRepository", "Failed to mark episode watched in Supabase", e)
        }
    }

    /**
     * Mark episode as unwatched - updates local cache immediately (optimistic), then syncs to backend
     * @param syncTrakt If true (default), also syncs to Trakt. Set false when batch Trakt removal is already done.
     */
    suspend fun markEpisodeUnwatched(showTmdbId: Int, season: Int, episode: Int, syncTrakt: Boolean = true) {
        ensureProfileCacheScope()
        // OPTIMISTIC UPDATE: Update all caches immediately so the UI responds instantly
        updateWatchedCache(showTmdbId, season, episode, false)
        updateShowWatchedCache(showTmdbId, season, episode, false)
        persistLocalWatchedSnapshotForCurrentProfile()

        // Then sync to backend in background (skip if batch Trakt removal already handled it)
        if (syncTrakt) {
            try {
                syncService.markEpisodeUnwatched(showTmdbId, season, episode)
            } catch (e: Exception) {
                // Sync failed, but local cache is already updated
            }
        }
    }

    // ========== Scrobbling ==========

    // Queue-based scrobbling to prevent duplicate API calls
    private var lastScrobbleKey: String? = null
    private var lastScrobbleTime: Long = 0
    private val SCROBBLE_DEBOUNCE_MS = 5000L // 5 second debounce

    private suspend fun <T> executeWithRetry(
        operation: String,
        maxAttempts: Int = 3,
        initialDelayMs: Long = 1000,
        block: suspend () -> T
    ): T? {
        var attempt = 1
        var delayMs = initialDelayMs
        while (attempt <= maxAttempts) {
            try {
                return block()
            } catch (e: HttpException) {
                val code = e.code()
                val shouldRetry = code == 429 || code >= 500 || code == 401
                if (code == 401) {
                    refreshTokenIfNeeded()
                }
                if (!shouldRetry || attempt == maxAttempts) {
                    return null
                }
                delay(delayMs)
                delayMs = (delayMs * 2).coerceAtMost(10000)
                attempt++
            } catch (e: Exception) {
                if (attempt == maxAttempts) {
                    return null
                }
                delay(delayMs)
                delayMs = (delayMs * 2).coerceAtMost(10000)
                attempt++
            }
        }
        return null
    }

    /**
     * Scrobble Start - Called when playback begins
     */
    suspend fun scrobbleStart(
        mediaType: MediaType,
        tmdbId: Int,
        progress: Float,
        season: Int? = null,
        episode: Int? = null
    ): TraktScrobbleResponse? {
        val body = buildScrobbleBody(mediaType, tmdbId, progress, season, episode)
        return executeWithRetry("Scrobble start") {
            val auth = getAuthHeader() ?: throw IllegalStateException("Missing auth")
            traktApi.scrobbleStart(auth, clientId, "2", body)
        }
    }

    /**
     * Scrobble Pause - Called when playback is paused (saves progress)
     * Uses queue-based deduplication
     */
    suspend fun scrobblePause(
        mediaType: MediaType,
        tmdbId: Int,
        progress: Float,
        season: Int? = null,
        episode: Int? = null
    ): TraktScrobbleResponse? {
        val key = "$tmdbId-$season-$episode"
        val now = System.currentTimeMillis()

        // Debounce duplicate calls
        if (key == lastScrobbleKey && now - lastScrobbleTime < SCROBBLE_DEBOUNCE_MS) {
            return null
        }

        lastScrobbleKey = key
        lastScrobbleTime = now

        val body = buildScrobbleBody(mediaType, tmdbId, progress, season, episode)
        return executeWithRetry("Scrobble pause") {
            val auth = getAuthHeader() ?: throw IllegalStateException("Missing auth")
            traktApi.scrobblePause(auth, clientId, "2", body)
        }
    }

    /**
     * Scrobble Pause Immediate - Bypass queue for instant pause
     */
    suspend fun scrobblePauseImmediate(
        mediaType: MediaType,
        tmdbId: Int,
        progress: Float,
        season: Int? = null,
        episode: Int? = null
    ): TraktScrobbleResponse? {
        val body = buildScrobbleBody(mediaType, tmdbId, progress, season, episode)
        return executeWithRetry("Scrobble pause immediate") {
            val auth = getAuthHeader() ?: throw IllegalStateException("Missing auth")
            traktApi.scrobblePause(auth, clientId, "2", body)
        }
    }

    /**
     * Scrobble Stop - Called when playback ends
     * Auto-marks as watched if progress >= threshold
     */
    suspend fun scrobbleStop(
        mediaType: MediaType,
        tmdbId: Int,
        progress: Float,
        season: Int? = null,
        episode: Int? = null
    ): TraktScrobbleResponse? {
        val body = buildScrobbleBody(mediaType, tmdbId, progress, season, episode)
        val response = executeWithRetry("Scrobble stop") {
            val auth = getAuthHeader() ?: throw IllegalStateException("Missing auth")
            traktApi.scrobbleStop(auth, clientId, "2", body)
        }

        // Auto-mark as watched if progress >= threshold
        if (progress >= Constants.WATCHED_THRESHOLD) {
            if (mediaType == MediaType.MOVIE) {
                markMovieWatched(tmdbId)
                updateWatchedCache(tmdbId, null, null, true)
            } else if (season != null && episode != null) {
                markEpisodeWatched(tmdbId, season, episode)
                updateWatchedCache(tmdbId, season, episode, true)
            }
        }

        return response
    }

    /**
     * Scrobble Stop Immediate - Bypass queue for instant stop
     */
    suspend fun scrobbleStopImmediate(
        mediaType: MediaType,
        tmdbId: Int,
        progress: Float,
        season: Int? = null,
        episode: Int? = null
    ): TraktScrobbleResponse? {
        val body = buildScrobbleBody(mediaType, tmdbId, progress, season, episode)
        return executeWithRetry("Scrobble stop immediate") {
            val auth = getAuthHeader() ?: throw IllegalStateException("Missing auth")
            traktApi.scrobbleStop(auth, clientId, "2", body)
        }
    }

    private fun buildScrobbleBody(
        mediaType: MediaType,
        tmdbId: Int,
        progress: Float,
        season: Int?,
        episode: Int?
    ): TraktScrobbleBody {
        return if (mediaType == MediaType.MOVIE) {
            TraktScrobbleBody(
                movie = TraktMovieId(TraktIds(tmdb = tmdbId)),
                progress = progress
            )
        } else {
            TraktScrobbleBody(
                episode = TraktEpisodeId(season = season, number = episode),
                show = TraktShowId(TraktIds(tmdb = tmdbId)),
                progress = progress
            )
        }
    }

    /**
     * Legacy method - delegates to scrobblePause for backwards compatibility
     */
    suspend fun savePlaybackProgress(
        mediaType: MediaType,
        tmdbId: Int,
        progress: Float,
        season: Int? = null,
        episode: Int? = null
    ) {
        scrobblePause(mediaType, tmdbId, progress, season, episode)
    }

    /**
     * Delete playback progress item by ID
     */
    suspend fun deletePlaybackItem(playbackId: Long): Boolean {
        val auth = getAuthHeader() ?: return false
        return try {
            traktApi.removePlaybackItem(auth, clientId, "2", playbackId)
            true
        } catch (e: Exception) {
            false
        }
    }

    /**
     * Delete playback progress for specific content
     */
    suspend fun deletePlaybackForContent(tmdbId: Int, mediaType: MediaType): Boolean {
        val auth = getAuthHeader() ?: return false
        return try {
            val playback = getAllPlaybackProgress(auth)
            val item = playback.find {
                when (mediaType) {
                    MediaType.MOVIE -> it.movie?.ids?.tmdb == tmdbId
                    MediaType.TV -> it.show?.ids?.tmdb == tmdbId
                }
            }
            if (item != null) {
                traktApi.removePlaybackItem(auth, clientId, "2", item.id)
                true
            } else {
                false
            }
        } catch (e: Exception) {
            false
        }
    }

    // ========== Watched Episodes ==========

    // Cache for TMDB to Trakt ID mapping (populated from watched shows)
    private val tmdbToTraktIdCache = mutableMapOf<Int, Int>()

    // Cache for watched episodes per show (to avoid repeated API calls)
    private val showWatchedEpisodesCache = mutableMapOf<Int, Set<String>>()
    private var showWatchedCacheTime = 0L
    private val SHOW_CACHE_DURATION_MS = 5 * 60 * 1000L // 5 minutes
    private val showCompletionCache = mutableMapOf<Int, Pair<Boolean, Long>>()
    private val SHOW_COMPLETION_CACHE_MS = 10 * 60 * 1000L

    /**
     * Get watched episodes for a specific show (by TMDB ID)
     * Returns a Set of episode keys in format "tmdbId-season-episode"
     * Uses caching to avoid repeated API calls
     */
    suspend fun getWatchedEpisodesForShow(tmdbId: Int): Set<String> {
        val auth = getAuthHeader()
        val prefix = "show_tmdb:$tmdbId:"
        val localOptimistic = watchedEpisodesCache.filter { it.startsWith(prefix) }.toSet()

        // For non-Trakt profiles, use the global watched cache (populated from Supabase)
        if (auth == null) {
            val result = localOptimistic

            // Direct Supabase query to catch any records not yet in cache
            try {
                val directKeys = syncService.getWatchedEpisodesForShow(tmdbId)
                if (directKeys.size > result.size) {
                    watchedEpisodesCache.addAll(directKeys)
                    return watchedEpisodesCache.filter { it.startsWith(prefix) }.toSet()
                }
            } catch (e: Exception) {
                AppLogger.e("TraktRepository", "Failed to resolve show TMDB ID to Trakt ID", e)
            }

            return result
        }

        // Check cache first (within cache duration)
        val now = System.currentTimeMillis()
        if (now - showWatchedCacheTime < SHOW_CACHE_DURATION_MS) {
            showWatchedEpisodesCache[tmdbId]?.let { cachedSet ->
                return (cachedSet + localOptimistic)
            }
        }

        val watchedSet = localOptimistic.toMutableSet()

        try {
            // First try to get Trakt ID from cache
            var traktId = tmdbToTraktIdCache[tmdbId]

            // If not in cache, populate cache from watched shows
            if (traktId == null) {
                populateTmdbToTraktCache()
                traktId = tmdbToTraktIdCache[tmdbId]
            }

            // If still not found, try search API as fallback
            if (traktId == null) {
                traktId = getTraktIdForTmdb(tmdbId, "show")
                if (traktId != null) {
                    tmdbToTraktIdCache[tmdbId] = traktId
                }
            }

            if (traktId == null) {
                // Cache empty result to avoid repeated lookups
                showWatchedEpisodesCache[tmdbId] = watchedSet
                return watchedSet
            }

            // Get show progress which includes per-episode completion status
            val progress = traktApi.getShowProgress(auth, clientId, "2", traktId.toString())

            // Iterate through all seasons and episodes
            progress.seasons?.forEach { season ->
                season.episodes?.forEach { episode ->
                    if (episode.completed) {
                        buildEpisodeKey(
                            traktEpisodeId = null,
                            showTraktId = null,
                            showTmdbId = tmdbId,
                            season = season.number,
                            episode = episode.number
                        )?.let { watchedSet.add(it) }
                    }
                }
            }

            // Cache the result
            showWatchedEpisodesCache[tmdbId] = watchedSet
            showWatchedCacheTime = now

        } catch (e: Exception) {
        }

        return watchedSet
    }

    /**
     * Clear the watched episodes cache (call when user marks episode as watched/unwatched)
     */
    fun clearShowWatchedCache() {
        showWatchedEpisodesCache.clear()
        showWatchedCacheTime = 0L
        showCompletionCache.clear()
    }

    /**
     * Update the per-show watched episodes cache directly
     * This ensures immediate UI updates without needing to re-fetch from API
     */
    private fun updateShowWatchedCache(showTmdbId: Int, season: Int, episode: Int, watched: Boolean) {
        val key = buildEpisodeKey(
            traktEpisodeId = null,
            showTraktId = null,
            showTmdbId = showTmdbId,
            season = season,
            episode = episode
        ) ?: return

        val currentSet = showWatchedEpisodesCache[showTmdbId]?.toMutableSet() ?: mutableSetOf()
        if (watched) {
            currentSet.add(key)
        } else {
            currentSet.remove(key)
        }
        showWatchedEpisodesCache[showTmdbId] = currentSet
        // Keep the cache valid
        showWatchedCacheTime = System.currentTimeMillis()
    }

    suspend fun isShowFullyWatched(tmdbId: Int): Boolean = withContext(Dispatchers.IO) {
        val auth = getAuthHeader() ?: return@withContext false
        val now = System.currentTimeMillis()
        showCompletionCache[tmdbId]?.let { (cached, timestamp) ->
            if (now - timestamp < SHOW_COMPLETION_CACHE_MS) {
                return@withContext cached
            }
        }

        try {
            var traktId = tmdbToTraktIdCache[tmdbId]
            if (traktId == null) {
                populateTmdbToTraktCache()
                traktId = tmdbToTraktIdCache[tmdbId]
            }

            if (traktId == null) {
                traktId = getTraktIdForTmdb(tmdbId, "show")
                if (traktId != null) {
                    tmdbToTraktIdCache[tmdbId] = traktId
                }
            }

            if (traktId == null) {
                showCompletionCache[tmdbId] = false to now
                return@withContext false
            }

            val includeSpecials = context.settingsDataStore.data.first()[includeSpecialsKey()] ?: false
            val progress = traktApi.getShowProgress(
                auth,
                clientId,
                "2",
                traktId.toString(),
                specials = includeSpecials.toString(),
                countSpecials = includeSpecials.toString()
            )
            val complete = progress.aired > 0 && progress.completed >= progress.aired
            showCompletionCache[tmdbId] = complete to now
            complete
        } catch (e: Exception) {
            showCompletionCache[tmdbId] = false to now
            false
        }
    }

    /**
     * Sync locally stored Trakt tokens to Supabase if profile is empty.
     */
    suspend fun syncLocalTokensToProfileIfNeeded() {
        // Account-level Supabase token sync is disabled. Cloud sync stores Trakt
        // tokens in the profile payload so one profile cannot overwrite another.
    }

    /**
     * Delete playback progress for a specific episode
     */
    suspend fun deletePlaybackForEpisode(showTmdbId: Int, season: Int, episode: Int): Boolean {
        val auth = getAuthHeader() ?: return false
        return try {
            val playback = getAllPlaybackProgress(auth)
            val item = playback.find { playbackItem ->
                playbackItem.type == "episode" &&
                    playbackItem.show?.ids?.tmdb == showTmdbId &&
                    playbackItem.episode?.season == season &&
                    playbackItem.episode?.number == episode
            }
            if (item != null) {
                traktApi.removePlaybackItem(auth, clientId, "2", item.id)
                true
            } else {
                false
            }
        } catch (e: Exception) {
            false
        }
    }

    /**
     * Populate the TMDB to Trakt ID cache from watched shows
     */
    private suspend fun populateTmdbToTraktCache() {
        val auth = getAuthHeader() ?: return
        try {
            val watchedShows = traktApi.getWatchedShows(auth, clientId)
            watchedShows.forEach { item ->
                val tmdbId = item.show.ids.tmdb
                val traktId = item.show.ids.trakt
                if (tmdbId != null && traktId != null) {
                    tmdbToTraktIdCache[tmdbId] = traktId
                }
            }
        } catch (e: Exception) {
        }
    }

    /**
     * Get Trakt ID from TMDB ID using search API (fallback)
     */
    private suspend fun getTraktIdForTmdb(tmdbId: Int, type: String): Int? {
        return try {
            val results = traktApi.searchByTmdb(clientId, tmdbId, type)
            val traktId = when (type) {
                "show" -> results.firstOrNull()?.show?.ids?.trakt
                "movie" -> results.firstOrNull()?.movie?.ids?.trakt
                else -> null
            }
            traktId
        } catch (e: Exception) {
            null
        }
    }

    private suspend fun getAllPlaybackProgress(auth: String): List<TraktPlaybackItem> {
        val all = mutableListOf<TraktPlaybackItem>()
        var page = 1
        val limit = 100

        while (true) {
            val pageItems = traktApi.getPlaybackProgress(auth, clientId, "2", null, page, limit)
            if (pageItems.isEmpty()) break
            all.addAll(pageItems)
            page++
        }

        return all
    }

    private suspend fun getAllHiddenProgressShows(auth: String): List<TraktHiddenItem> {
        val all = mutableListOf<TraktHiddenItem>()
        var page = 1
        val limit = 100

        while (true) {
            val pageItems = traktApi.getHiddenProgressShows(auth, clientId, page = page, limit = limit)
            if (pageItems.isEmpty()) break
            all.addAll(pageItems)
            if (pageItems.size < limit) break
            page++
        }

        return all
    }

    private suspend fun getAllHiddenProgressResetShows(auth: String): List<TraktHiddenItem> {
        val all = mutableListOf<TraktHiddenItem>()
        var page = 1
        val limit = 100

        while (true) {
            val pageItems = traktApi.getHiddenProgressResetShows(auth, clientId, page = page, limit = limit)
            if (pageItems.isEmpty()) break
            all.addAll(pageItems)
            if (pageItems.size < limit) break
            page++
        }

        return all
    }

    /**
     * Get items to continue watching - Uses Trakt paused playback directly for accuracy and speed.
     * For profiles without Trakt, falls back to local Continue Watching storage.
     */
    suspend fun getContinueWatching(forceRefresh: Boolean = false): List<ContinueWatchingItem> = coroutineScope {
        ensureProfileCacheScope()
        val requestProfileId = currentProfileId()
        val auth = getAuthHeader()

        // If this profile has Trakt stored but refresh is temporarily unavailable
        // (rate limit/offline), keep the Trakt cache instead of falling back to
        // local non-Trakt progress and polluting the row.
        if (auth == null) {
            if (hasStoredTraktTokenForCurrentProfile()) {
                val cached = loadContinueWatchingCache()
                cachedContinueWatching = cached
                cachedContinueWatchingProfileId = requestProfileId
                return@coroutineScope cached
            }
            val localItems = loadLocalContinueWatching()
            cachedContinueWatching = localItems
            cachedContinueWatchingProfileId = requestProfileId
            return@coroutineScope localItems
        }

        // Return cached data if still fresh (unless forced)
        val now = System.currentTimeMillis()
        if (
            !forceRefresh &&
            cachedContinueWatchingProfileId == requestProfileId &&
            cachedContinueWatching.isNotEmpty() &&
            now - lastContinueWatchingFetch < CONTINUE_WATCHING_CACHE_MS
        ) {
            return@coroutineScope cachedContinueWatching
        }

        // Prevent duplicate fetches
        if (continueWatchingFetching && continueWatchingFetchingProfileId == requestProfileId) {
            while (continueWatchingFetching && continueWatchingFetchingProfileId == requestProfileId) { delay(50) }
            if (cachedContinueWatchingProfileId == requestProfileId && cachedContinueWatching.isNotEmpty()) {
                return@coroutineScope cachedContinueWatching
            }
        }
        continueWatchingFetching = true
        continueWatchingFetchingProfileId = requestProfileId

        try {
        val candidates = mutableListOf<ContinueWatchingCandidate>()

        initializeWatchedCache()

            // Trakt Continue Watching is built from explicit paused playback
            // plus recent watched-show progress. We cannot call Trakt's website
            // progress activity feed from API clients, so keep this bounded and
            // respect both hidden and reset progress sections.
            // Helper: detect HTTP 401/403 from Retrofit exceptions
            fun isAuthError(e: Exception): Boolean {
                val httpEx = e as? retrofit2.HttpException ?: return false
                return httpEx.code() in setOf(401, 403)
            }
            // Re-acquire auth if the original token was stale
            val authHolder = arrayOf(auth)
            suspend fun <T> traktCallWithAuthRetry(
                label: String,
                block: suspend (String) -> T
            ): T {
                var lastErr: Exception? = null
                repeat(2) { attempt ->
                    try {
                        return block(authHolder[0])
                    } catch (e: Exception) {
                        lastErr = e
                        if (attempt == 0 && isAuthError(e)) {
                            // Token may be expired – force-refresh and retry
                            val refreshed = refreshTokenIfNeeded()
                            if (refreshed != null) authHolder[0] = "Bearer $refreshed"
                            delay(200)
                        } else if (attempt == 0) {
                            delay(500)
                        }
                    }
                }
                throw lastErr ?: IllegalStateException("$label failed")
            }
            val hiddenShowsDeferred = async {
                try {
                    traktCallWithAuthRetry("hidden progress shows") { currentAuth ->
                        getAllHiddenProgressShows(currentAuth)
                    }
                } catch (e: Exception) {
                    System.err.println("TraktRepo:getCW: getHiddenShows failed: ${e.message}")
                    emptyList()
                }
            }
            val hiddenResetShowsDeferred = async {
                try {
                    traktCallWithAuthRetry("hidden progress reset shows") { currentAuth ->
                        getAllHiddenProgressResetShows(currentAuth)
                    }
                } catch (e: Exception) {
                    System.err.println("TraktRepo:getCW: getHiddenResetShows failed: ${e.message}")
                    emptyList()
                }
            }
            val playbackDeferred = async {
                traktCallWithAuthRetry("playback progress") { currentAuth ->
                    getAllPlaybackProgress(currentAuth)
                }
            }
            val watchedShowsDeferred = async {
                traktCallWithAuthRetry("watched shows") { currentAuth ->
                    traktApi.getWatchedShows(currentAuth, clientId)
                }
            }

            val hiddenTraktIds = (hiddenShowsDeferred.await() + hiddenResetShowsDeferred.await())
                .mapNotNull { it.show?.ids?.trakt }
                .toSet()
            // Fetch actively paused playback items (sync/playback).
            val processedKeys = mutableSetOf<String>()
            var playbackFetched = false
            var watchedProgressFetched = false
            try {
                val playbackItems = playbackDeferred.await()
                playbackFetched = true
                for (item in playbackItems) {
                    if (item.progress < Constants.MIN_PROGRESS_THRESHOLD || item.progress >= Constants.WATCHED_THRESHOLD) continue

                    if (item.type == "movie") {
                        val movie = item.movie ?: continue
                        val tmdbId = movie.ids.tmdb ?: continue
                        val key = "${MediaType.MOVIE}:$tmdbId:-1:-1"
                        if (key in processedKeys) continue
                        if (isMovieWatched(tmdbId)) continue
                        candidates.add(
                            ContinueWatchingCandidate(
                                item = ContinueWatchingItem(
                                    id = tmdbId,
                                    title = movie.title,
                                    mediaType = MediaType.MOVIE,
                                    progress = item.progress.toInt().coerceIn(0, 100),
                                    resumePositionSeconds = 0L,
                                    durationSeconds = 0L,
                                    year = movie.year?.toString() ?: ""
                                ),
                                lastActivityAt = item.pausedAt ?: ""
                            )
                        )
                        processedKeys.add(key)
                        processedKeys.add("${MediaType.MOVIE}:$tmdbId")
                        continue
                    }

                    if (item.type != "episode") continue
                    val episode = item.episode ?: continue
                    val show = item.show ?: continue
                    val tmdbId = show.ids.tmdb ?: continue
                    // Skip shows hidden from progress (user "dropped" them on Trakt)
                    val showTraktId = show.ids.trakt
                    if (showTraktId != null && showTraktId in hiddenTraktIds) continue
                    val season = episode.season
                    val number = episode.number
                    val key = "${MediaType.TV}:$tmdbId:$season:$number"
                    if (key in processedKeys) continue
                    // Check if this episode is already watched
                    val epWatchedKey = "show_tmdb:$tmdbId:$season:$number"
                    if (watchedEpisodesCache.contains(epWatchedKey)) continue
                    candidates.add(
                        ContinueWatchingCandidate(
                            item = ContinueWatchingItem(
                                id = tmdbId,
                                title = show.title,
                                mediaType = MediaType.TV,
                                progress = item.progress.toInt().coerceIn(0, 100),
                                resumePositionSeconds = 0L,
                                durationSeconds = 0L,
                                season = season,
                                episode = number,
                                episodeTitle = episode.title,
                                year = show.year?.toString() ?: ""
                            ),
                            lastActivityAt = item.pausedAt ?: ""
                        )
                    )
                    processedKeys.add(key)
                    processedKeys.add("${MediaType.TV}:$tmdbId")
                }
            } catch (e: Exception) {
                System.err.println("TraktRepo:getCW: playback progress failed: ${e.message}")
            }

            try {
                val includeSpecials = context.settingsDataStore.data.first()[includeSpecialsKey()] ?: false
                val allWatchedShows = watchedShowsDeferred.await()
                    .asSequence()
                    .filter { watched ->
                        val show = watched.show
                        val traktId = show.ids.trakt
                        show.ids.tmdb != null && traktId != null && traktId !in hiddenTraktIds
                    }
                    .sortedByDescending { it.lastWatchedAt ?: it.lastUpdatedAt ?: "" }
                    .toList()

                val recentCutoffMs = System.currentTimeMillis() - TRAKT_UP_NEXT_RECENT_WINDOW_MS
                val recentWatchedShows = allWatchedShows.filter { watched ->
                    parseIso8601(watched.lastWatchedAt ?: watched.lastUpdatedAt ?: "") >= recentCutoffMs
                }
                val watchedShows = (if (recentWatchedShows.size >= 8) recentWatchedShows else allWatchedShows)
                    .take(Constants.MAX_PROGRESS_ENTRIES)

                val semaphore = Semaphore(8)
                val watchedProgressCandidates = watchedShows.map { watched ->
                    async {
                        semaphore.withPermit {
                            val show = watched.show
                            val traktId = show.ids.trakt ?: return@withPermit null
                            val tmdbId = show.ids.tmdb ?: return@withPermit null
                            val progress = try {
                                traktCallWithAuthRetry("show progress") { currentAuth ->
                                    traktApi.getShowProgress(
                                        currentAuth,
                                        clientId,
                                        "2",
                                        traktId.toString(),
                                        hidden = "false",
                                        specials = includeSpecials.toString(),
                                        countSpecials = includeSpecials.toString()
                                    )
                                }
                            } catch (e: Exception) {
                                System.err.println("TraktRepo:getCW: show progress failed for ${show.title}: ${e.message}")
                                return@withPermit null
                            }

                            val nextEpisode = progress.nextEpisode ?: return@withPermit null
                            if (progress.aired <= 0 || progress.completed >= progress.aired) return@withPermit null
                            if (!includeSpecials && nextEpisode.season == 0) return@withPermit null

                            val exactKey = "${MediaType.TV}:$tmdbId:${nextEpisode.season}:${nextEpisode.number}"
                            val showKey = "${MediaType.TV}:$tmdbId"
                            if (exactKey in processedKeys || showKey in processedKeys) return@withPermit null

                            val completionPercent = ((progress.completed.toFloat() / progress.aired.toFloat()) * 100f)
                                .toInt()
                                .coerceIn(0, 99)
                            ContinueWatchingCandidate(
                                item = ContinueWatchingItem(
                                    id = tmdbId,
                                    title = show.title,
                                    mediaType = MediaType.TV,
                                    progress = completionPercent,
                                    resumePositionSeconds = 0L,
                                    durationSeconds = 0L,
                                    season = nextEpisode.season,
                                    episode = nextEpisode.number,
                                    episodeTitle = nextEpisode.title,
                                    year = show.year?.toString() ?: "",
                                    isUpNext = true,
                                    totalEpisodes = progress.aired.coerceAtLeast(0),
                                    watchedEpisodes = progress.completed.coerceIn(0, progress.aired.coerceAtLeast(0))
                                ),
                                lastActivityAt = progress.lastWatchedAt ?: watched.lastWatchedAt ?: watched.lastUpdatedAt ?: ""
                            )
                        }
                    }
                }.awaitAll().filterNotNull()

                watchedProgressCandidates.forEach { candidate ->
                    val exactKey = "${candidate.item.mediaType}:${candidate.item.id}:${candidate.item.season}:${candidate.item.episode}"
                    val showKey = "${candidate.item.mediaType}:${candidate.item.id}"
                    if (exactKey !in processedKeys && showKey !in processedKeys) {
                        candidates.add(candidate)
                        processedKeys.add(exactKey)
                        processedKeys.add(showKey)
                    }
                }
                watchedProgressFetched = true
            } catch (e: Exception) {
                System.err.println("TraktRepo:getCW: watched progress failed: ${e.message}")
            }

            // Filter out dismissed items
            val dismissed = loadDismissedContinueWatching()
            val filteredCandidates = if (dismissed.isNotEmpty()) {
                val updatedDismissed = dismissed.toMutableMap()
                val kept = candidates.filter { candidate ->
                    val key = buildContinueWatchingKey(candidate.item)
                    val showKey = buildContinueWatchingShowKey(candidate.item.mediaType, candidate.item.id)
                    val dismissedAt = listOfNotNull(
                        key?.let { dismissed[it] },
                        dismissed[showKey]
                    ).maxOrNull()

                    if (dismissedAt == null) {
                        true
                    } else {
                        val activityAt = parseIso8601(candidate.lastActivityAt)
                        if (activityAt > dismissedAt) {
                            key?.let { updatedDismissed.remove(it) }
                            updatedDismissed.remove(showKey)
                            true
                        } else {
                            false
                        }
                    }
                }
                if (updatedDismissed.size != dismissed.size) {
                    persistDismissedContinueWatching(updatedDismissed)
                }
                kept
            } else {
                candidates
            }

            val topCandidates = filteredCandidates.sortedByDescending { it.lastActivityAt }.take(Constants.MAX_CONTINUE_WATCHING)
            if (topCandidates.isEmpty() && playbackFetched && watchedProgressFetched) {
                cachedContinueWatching = emptyList()
                cachedContinueWatchingProfileId = requestProfileId
                lastContinueWatchingFetch = System.currentTimeMillis()
                persistContinueWatchingCache(emptyList())
                return@coroutineScope emptyList()
            }

            // 3. Hydrate with TMDB Details (Parallel)
            val hydratedItems = hydrateTopCandidates(topCandidates)
            // Ensure we never lose items due to TMDB validation failures - prioritize local status
            // If hydration returned empty despite having candidates, fall back to local data
            if (hydratedItems.isEmpty() && topCandidates.isNotEmpty()) {
                // Map candidates back to items without TMDB enrichment
                // Filter out items with null season/episode (already validated at candidate creation)
                val fallbackItems = topCandidates.map { it.item }
                    .filter { it.mediaType != MediaType.TV || (it.season != null && it.episode != null) }
                cachedContinueWatching = fallbackItems
                cachedContinueWatchingProfileId = requestProfileId
                lastContinueWatchingFetch = System.currentTimeMillis()
                persistContinueWatchingCache(fallbackItems)
                return@coroutineScope fallbackItems
            }

        val resolvedItems = if (hydratedItems.isNotEmpty()) {
            cachedContinueWatching = hydratedItems
            cachedContinueWatchingProfileId = requestProfileId
            lastContinueWatchingFetch = System.currentTimeMillis()
            persistContinueWatchingCache(hydratedItems)
            hydratedItems
        } else {
            val cached = if (cachedContinueWatchingProfileId == requestProfileId && cachedContinueWatching.isNotEmpty()) {
                cachedContinueWatching
            } else {
                loadContinueWatchingCache().also {
                    cachedContinueWatching = it
                    cachedContinueWatchingProfileId = requestProfileId
                }
            }
            cached
        }
        return@coroutineScope resolvedItems
        } finally {
            continueWatchingFetching = false
            continueWatchingFetchingProfileId = null
        }
    }

    private suspend fun hydrateTopCandidates(topCandidates: List<ContinueWatchingCandidate>): List<ContinueWatchingItem> = coroutineScope {
        val hydrationTasks = topCandidates.map { candidate ->
            async {
                try {
                    val item = candidate.item
                    if (item.mediaType == MediaType.MOVIE) {
                        val details = tmdbApi.getMovieDetails(item.id, Constants.TMDB_API_KEY)
                        item.copy(
                            backdropPath = details.backdropPath?.let { "${Constants.BACKDROP_BASE_LARGE}$it" },
                            posterPath = details.posterPath?.let { "${Constants.IMAGE_BASE}$it" },
                            overview = details.overview ?: "",
                            imdbRating = String.format("%.1f", details.voteAverage),
                            duration = details.runtime?.let { formatRuntime(it) } ?: item.duration,
                            durationSeconds = maxOf(item.durationSeconds, runtimeMinutesToSeconds(details.runtime))
                        )
                    } else {
                        val details = tmdbApi.getTvDetails(item.id, Constants.TMDB_API_KEY)
                        // Allow items where Trakt says there's a next episode even if
                        // TMDB hasn't updated its season count yet. Trakt's progress
                        // API is authoritative for "what to watch next" — TMDB often
                        // lags by hours or days when a new season premieres. Only drop
                        // items where the season is wildly beyond TMDB's count (likely
                        // a Trakt data error, e.g., a specials season numbered 99).
                        val validatedItem = if (item.season != null && item.season > details.numberOfSeasons + 1) {
                            null
                        } else {
                            item
                        }
                        validatedItem?.copy(
                            backdropPath = details.backdropPath?.let { "${Constants.BACKDROP_BASE_LARGE}$it" },
                            posterPath = details.posterPath?.let { "${Constants.IMAGE_BASE}$it" },
                            overview = details.overview ?: "",
                            imdbRating = String.format("%.1f", details.voteAverage),
                            duration = details.episodeRunTime.firstOrNull()?.let { "${it}m" } ?: item.duration,
                            durationSeconds = maxOf(item.durationSeconds, runtimeMinutesToSeconds(details.episodeRunTime.firstOrNull())),
                            totalEpisodes = item.totalEpisodes,
                            watchedEpisodes = item.watchedEpisodes
                        )
                    }
                } catch (e: Exception) {
                    // Keep local/cached item if TMDB hydration fails - don't lose user's continue watching entry
                    System.err.println("TraktRepo:getCW: TMDB hydration failed for ${candidate.item.title}: ${e.message}")
                    candidate.item
                }
            }
        }

        hydrationTasks.awaitAll().filterNotNull()
    }

    fun getCachedContinueWatching(): List<ContinueWatchingItem> {
        return if (cachedContinueWatchingProfileId == profileManager.getProfileIdSync()) {
            cachedContinueWatching
        } else {
            emptyList()
        }
    }

    // Cache for preloaded profile data (keyed by profileId)
    private val preloadedProfileCache = ConcurrentHashMap<String, List<ContinueWatchingItem>>()

    suspend fun preloadContinueWatchingCache(): List<ContinueWatchingItem> {
        ensureProfileCacheScope()
        val profileId = currentProfileId()
        // Return existing cache if available
        if (cachedContinueWatchingProfileId == profileId && cachedContinueWatching.isNotEmpty()) {
            cachedContinueWatching = filterDismissedContinueWatchingItems(cachedContinueWatching)
            return cachedContinueWatching
        }

        // Check if this profile has Trakt credentials STORED (not whether a
        // network token refresh succeeds). The previous code used
        // refreshTokenIfNeeded() which does a network call — at early startup
        // this returns null because tokens haven't loaded from DataStore yet,
        // causing the code to incorrectly fall back to local CW for Trakt
        // profiles. That loaded cloud-synced non-Trakt items into the CW row.
        val hasTraktToken = runCatching {
            val prefs = context.traktDataStore.data.first()
            val tokenKey = profileManager.profileStringKey("trakt_access_token")
            !prefs[tokenKey].isNullOrBlank()
        }.getOrDefault(false)

        if (!hasTraktToken) {
            // Profile genuinely has no Trakt — use local CW
            val local = filterDismissedContinueWatchingItems(loadLocalContinueWatchingRaw())
            cachedContinueWatching = local
            cachedContinueWatchingProfileId = profileId
            return cachedContinueWatching
        }

        // Trakt profile: check preloaded cache first (from ProfileSelectionScreen)
        val preloaded = preloadedProfileCache[profileId]
        if (!preloaded.isNullOrEmpty()) {
            cachedContinueWatching = filterDismissedContinueWatchingItems(preloaded)
            cachedContinueWatchingProfileId = profileId
            return cachedContinueWatching
        }

        // Fall back to the persisted Trakt CW cache (written by getContinueWatching).
        // This will only contain Trakt-sourced items (after the fix to
        // resolveContinueWatchingItems). Do NOT fall back to local CW here.
        val cached = filterDismissedContinueWatchingItems(loadContinueWatchingCache())
        cachedContinueWatching = cached
        cachedContinueWatchingProfileId = profileId
        return cachedContinueWatching
    }

    /**
     * Preload Continue Watching cache for a specific profile (before it's selected).
     * This allows instant display when the user selects that profile.
     * Called when user focuses on a profile in ProfileSelectionScreen.
     */
    suspend fun preloadContinueWatchingForProfile(profileId: String) {
        // Skip if already preloaded
        if (preloadedProfileCache.containsKey(profileId)) return

        try {
            val tokenKey = stringPreferencesKey("profile_${profileId}_trakt_access_token")
            val prefs = context.traktDataStore.data.first()
            if (!prefs[tokenKey].isNullOrBlank()) {
                // Do not seed profile selection from the persisted Trakt CW cache.
                // That cache can lag behind real progress and causes the Home row
                // to flash stale episodes before the fresh resolver replaces it.
                return
            }

            // Directly access the cache with the specific profile's key
            val cacheKey = stringPreferencesKey("profile_${profileId}_trakt_continue_watching_cache_v1")
            val json = prefs[cacheKey] ?: return

            val type = TypeToken.getParameterized(MutableList::class.java, ContinueWatchingItem::class.java).type
            val parsed: List<ContinueWatchingItem> = gson.fromJson(json, type)
            val filtered = filterDismissedContinueWatchingItems(parsed, profileId)
            preloadedProfileCache[profileId] = filtered

            // If this profile becomes active, pre-populate the main cache
            if (profileManager.getProfileIdSync() == profileId) {
                cachedContinueWatching = filtered
                cachedContinueWatchingProfileId = profileId
            }
        } catch (e: Exception) {
            // Silently ignore preload failures - not critical
        }
    }

    /**
     * Get preloaded cache for a profile, or empty if not preloaded
     */
    fun getPreloadedCacheForProfile(profileId: String): List<ContinueWatchingItem> {
        return preloadedProfileCache[profileId] ?: emptyList()
    }

    /**
     * Activate preloaded cache for a profile - call when profile is selected.
     * This transfers preloaded data to the active cache for immediate use by HomeViewModel.
     * IMPORTANT: Always clears existing cache first to prevent cross-profile data leakage.
     */
    fun activatePreloadedCache(profileId: String) {
        activeCacheProfileId = profileId.ifBlank { "default" }
        // CRITICAL: Clear existing cache first to prevent profile data leakage
        cachedContinueWatching = emptyList()
        cachedContinueWatchingProfileId = null
        lastContinueWatchingFetch = 0L

        // Then load this profile's preloaded data if available
        val preloaded = preloadedProfileCache[profileId]
        if (!preloaded.isNullOrEmpty()) {
            cachedContinueWatching = preloaded
            cachedContinueWatchingProfileId = profileId
        }
    }

    /**
     * Clear continue watching cache - call when switching profiles
     */
    fun clearContinueWatchingCache() {
        ensureProfileCacheScope()
        cachedContinueWatching = emptyList()
        cachedContinueWatchingProfileId = null
        lastContinueWatchingFetch = 0L
    }

    /**
     * One-time cleanup: wipe both the Trakt CW cache and the local CW DataStore
     * entries for the current profile. Called at startup for Trakt-authenticated
     * profiles to flush stale data from before the Trakt-only CW fix was deployed.
     * After this, the next getContinueWatching() call will repopulate the cache
     * with clean Trakt-only data.
     */
    suspend fun purgeLocalContinueWatchingForTraktProfile() {
        cachedContinueWatching = emptyList()
        cachedContinueWatchingProfileId = null
        lastContinueWatchingFetch = 0L
        preloadedProfileCache.clear()
        context.traktDataStore.edit { prefs ->
            prefs.remove(continueWatchingCacheKey())
            prefs.remove(localContinueWatchingKey())
        }
    }

    /**
     * Clear ALL profile-specific caches - MUST be called when switching profiles
     * This ensures complete isolation between profiles and prevents data leakage
     */
    fun clearAllProfileCaches() {
        activeCacheProfileId = currentProfileId()
        clearProfileScopedMemoryCaches(clearPreloaded = true)
    }

    /**
     * Remove an episode from Continue Watching cache when marked as watched
     */
    suspend fun removeFromContinueWatchingCache(showTmdbId: Int, seasonNum: Int?, episodeNum: Int?) {
        ensureProfileCacheScope()
        // Always remove from local CW (for non-Trakt profiles) regardless of Trakt cache state
        removeFromLocalContinueWatching(showTmdbId, seasonNum, episodeNum)

        if (cachedContinueWatching.isEmpty()) {
            cachedContinueWatching = loadContinueWatchingCache()
            cachedContinueWatchingProfileId = profileManager.getProfileIdSync()
        }

        cachedContinueWatching = cachedContinueWatching.filter { item ->
            // Keep items that don't match the watched episode
            !(item.id == showTmdbId &&
              (seasonNum == null || item.season == seasonNum) &&
              (episodeNum == null || item.episode == episodeNum))
        }
        // Also update persisted cache
        persistContinueWatchingCache(cachedContinueWatching)
    }

    // ========== Local Continue Watching (for profiles without Trakt) ==========

    /**
     * Save playback progress to local Continue Watching (profile-scoped).
     * This enables Continue Watching for profiles that don't have Trakt connected.
     * Called from PlayerViewModel when saving progress.
     */
    suspend fun saveLocalContinueWatching(
        mediaType: MediaType,
        tmdbId: Int,
        title: String,
        posterPath: String?,
        backdropPath: String?,
        season: Int?,
        episode: Int?,
        episodeTitle: String?,
        progress: Int, // 0-100
        positionSeconds: Long = 0L,
        durationSeconds: Long = 0L,
        streamKey: String? = null,
        streamAddonId: String? = null,
        streamTitle: String? = null,
        year: String = ""
    ) {
        ensureProfileCacheScope()
        val hasMeaningfulPosition = positionSeconds >= 60L

        // Keep accidental taps out, but still keep real partial sessions on long content
        // where percent can be low while position is already meaningful.
        if ((progress < Constants.MIN_PROGRESS_THRESHOLD && !hasMeaningfulPosition) || progress >= Constants.WATCHED_THRESHOLD) {
            // If watched (>= threshold), remove from Continue Watching
            if (progress >= Constants.WATCHED_THRESHOLD) {
                removeFromLocalContinueWatching(tmdbId, season, episode)
            }
            return
        }

        val item = ContinueWatchingItem(
            id = tmdbId,
            title = title,
            mediaType = mediaType,
            progress = progress,
            resumePositionSeconds = positionSeconds.coerceAtLeast(0L),
            durationSeconds = durationSeconds.coerceAtLeast(0L),
            season = season,
            episode = episode,
            episodeTitle = episodeTitle,
            backdropPath = backdropPath,
            posterPath = posterPath,
            streamKey = streamKey,
            streamAddonId = streamAddonId,
            streamTitle = streamTitle,
            year = year,
            updatedAtMs = System.currentTimeMillis()
        )

        // Load existing items (raw - no enrichment needed when saving)
        val existingItems = loadLocalContinueWatchingRaw().toMutableList()

        // Remove ALL existing entries for this show/movie (will add updated one).
        // For TV: removes any episode of the same show, so CW only has the latest episode per show.
        existingItems.removeAll { existing ->
            existing.id == tmdbId && existing.mediaType == mediaType
        }

        // Add to front (most recent)
        existingItems.add(0, item)

        // Keep only top items
        val trimmed = existingItems.take(Constants.MAX_CONTINUE_WATCHING)

        // Persist
        val json = gson.toJson(trimmed)
        val saveKey = localContinueWatchingKey()
        context.traktDataStore.edit { prefs ->
            prefs[saveKey] = json
        }

        // Only update the in-memory CW cache for non-Trakt profiles.
        // When Trakt is connected, the cache must only be populated by
        // getContinueWatching() which returns Trakt-authoritative data.
        // The previous code used refreshTokenIfNeeded() == null which could
        // incorrectly trigger for Trakt users on network errors, polluting
        // the Trakt CW cache with local-only items.
        val isTraktAuth = runCatching { isAuthenticated.first() }.getOrDefault(false)
        if (!isTraktAuth) {
            cachedContinueWatching = trimmed
        }
    }

    /**
     * Remove item from local Continue Watching
     */
    private suspend fun removeFromLocalContinueWatching(tmdbId: Int, season: Int?, episode: Int?) {
        // Use raw method - no need to enrich items just to remove them
        val existingItems = loadLocalContinueWatchingRaw().toMutableList()
        val sizeBefore = existingItems.size

        existingItems.removeAll { item ->
            item.id == tmdbId &&
            (season == null || item.season == season) &&
            (episode == null || item.episode == episode)
        }

        if (existingItems.size != sizeBefore) {
            val json = gson.toJson(existingItems)
            context.traktDataStore.edit { prefs ->
                prefs[localContinueWatchingKey()] = json
            }
        }
    }

    private suspend fun persistLocalWatchedSnapshotForCurrentProfile() {
        val movieIds = watchedMoviesCache.toList().distinct().sorted()
        val episodeKeys = watchedEpisodesCache.toList().distinct().sorted()
        context.traktDataStore.edit { prefs ->
            if (movieIds.isEmpty()) {
                prefs.remove(localWatchedMoviesKey())
            } else {
                prefs[localWatchedMoviesKey()] = gson.toJson(movieIds)
            }

            if (episodeKeys.isEmpty()) {
                prefs.remove(localWatchedEpisodesKey())
            } else {
                prefs[localWatchedEpisodesKey()] = gson.toJson(episodeKeys)
            }
        }
    }

    private suspend fun loadLocalWatchedSnapshotForCurrentProfile(): Pair<Set<Int>, Set<String>> {
        val prefs = context.traktDataStore.data.first()
        val movies = decodeIntList(prefs[localWatchedMoviesKey()].orEmpty()).toSet()
        val episodes = decodeStringList(prefs[localWatchedEpisodesKey()].orEmpty()).toSet()
        return movies to episodes
    }

    private fun decodeContinueWatchingList(json: String): List<ContinueWatchingItem> {
        if (json.isBlank()) return emptyList()
        return try {
            val type = TypeToken.getParameterized(MutableList::class.java, ContinueWatchingItem::class.java).type
            val items: List<ContinueWatchingItem> = gson.fromJson(json, type)
            items.distinctBy { "${it.mediaType}:${it.id}" }
        } catch (_: Exception) {
            emptyList()
        }
    }

    private fun decodeIntList(json: String): List<Int> {
        if (json.isBlank()) return emptyList()
        return try {
            val type = TypeToken.getParameterized(MutableList::class.java, Int::class.javaObjectType).type
            val items: List<Int> = gson.fromJson(json, type)
            items.distinct()
        } catch (_: Exception) {
            emptyList()
        }
    }

    private fun decodeStringList(json: String): List<String> {
        if (json.isBlank()) return emptyList()
        return try {
            val type = TypeToken.getParameterized(MutableList::class.java, String::class.java).type
            val items: List<String> = gson.fromJson(json, type)
            items.filter { it.isNotBlank() }.distinct()
        } catch (_: Exception) {
            emptyList()
        }
    }

    /**
     * Load local Continue Watching items (profile-scoped) - raw data without enrichment
     */
    private suspend fun loadLocalContinueWatchingRaw(): List<ContinueWatchingItem> {
        val key = localContinueWatchingKey()
        val prefs = context.traktDataStore.data.first()
        val json = prefs[key]
        if (json == null) {
            return emptyList()
        }
        return decodeContinueWatchingList(json)
    }

    /**
     * Get a single local Continue Watching entry (raw, without TMDB enrichment).
     * Used for resume playback when the user isn't signed into Cloud/Trakt.
     */
    suspend fun getLocalContinueWatchingEntry(
        mediaType: MediaType,
        tmdbId: Int,
        season: Int?,
        episode: Int?
    ): ContinueWatchingItem? {
        val items = loadLocalContinueWatchingRaw()
        return items.firstOrNull { item ->
            if (item.id != tmdbId) return@firstOrNull false
            if (item.mediaType != mediaType) return@firstOrNull false
            if (mediaType == MediaType.MOVIE) return@firstOrNull true
            item.season == season && item.episode == episode
        }
    }

    /**
     * Get best local Continue Watching entry for a show/movie regardless of provided episode.
     * Useful when remote history is unavailable but local playback progress exists.
     */
    suspend fun getBestLocalContinueWatchingEntry(
        mediaType: MediaType,
        tmdbId: Int
    ): ContinueWatchingItem? {
        val items = loadLocalContinueWatchingRaw()
            .filter { it.id == tmdbId && it.mediaType == mediaType }
        if (items.isEmpty()) return null
        return items.maxWithOrNull(
            compareBy<ContinueWatchingItem> { it.updatedAtMs }
                .thenBy { it.resumePositionSeconds.coerceAtLeast(0L) }
                .thenBy { it.progress.coerceAtLeast(0) }
        )
    }

    /**
     * Load local Continue Watching items enriched with TMDB data (overview, duration, etc.)
     */
    private suspend fun loadLocalContinueWatching(): List<ContinueWatchingItem> = coroutineScope {
        val rawItems = loadLocalContinueWatchingRaw()
        if (rawItems.isEmpty()) return@coroutineScope emptyList()

        // Enrich items with TMDB data in parallel (limited concurrency)
        val semaphore = kotlinx.coroutines.sync.Semaphore(5)
        val seasonCache = java.util.concurrent.ConcurrentHashMap<Pair<Int, Int>, Deferred<com.arflix.tv.data.api.TmdbSeasonDetails?>>()
        rawItems.map { item ->
            async {
                semaphore.withPermit {
                    enrichLocalContinueWatchingItem(item, seasonCache)
                }
            }
        }.awaitAll()
    }

    /**
     * Enrich arbitrary Continue Watching items with TMDB metadata so non-Trakt
     * and Trakt paths render identical card details.
     */
    suspend fun enrichContinueWatchingItems(items: List<ContinueWatchingItem>): List<ContinueWatchingItem> = coroutineScope {
        if (items.isEmpty()) return@coroutineScope emptyList()
        val semaphore = kotlinx.coroutines.sync.Semaphore(5)
        val seasonCache = java.util.concurrent.ConcurrentHashMap<Pair<Int, Int>, Deferred<com.arflix.tv.data.api.TmdbSeasonDetails?>>()
        items.map { item ->
            async {
                semaphore.withPermit {
                    enrichLocalContinueWatchingItem(item, seasonCache)
                }
            }
        }.awaitAll()
    }

    /**
     * Enrich a local Continue Watching item with TMDB data
     * Matches the Trakt enrichment behavior: uses SHOW backdrop/overview, not episode
     */
    private suspend fun enrichLocalContinueWatchingItem(
        item: ContinueWatchingItem,
        seasonCache: java.util.concurrent.ConcurrentHashMap<Pair<Int, Int>, Deferred<com.arflix.tv.data.api.TmdbSeasonDetails?>> = java.util.concurrent.ConcurrentHashMap()
    ): ContinueWatchingItem = coroutineScope {
        // Skip only when all Continue Watching metrics are already present.
        val needsRuntime = item.durationSeconds <= 0L
        val needsEpisodeCounts = item.mediaType == MediaType.TV && item.totalEpisodes <= 0
        if (!needsRuntime && !needsEpisodeCounts && item.overview.isNotEmpty() && item.backdropPath?.startsWith("http") == true) {
            return@coroutineScope item
        }

        val apiKey = Constants.TMDB_API_KEY
        try {
            return@coroutineScope if (item.mediaType == MediaType.TV) {
                val details = try {
                    tmdbApi.getTvDetails(item.id, apiKey)
                } catch (_: Exception) { null }

                // Get current season info for episode title and aired-episode counts.
                val seasonDetails = if (item.season != null && item.episode != null && (item.episodeTitle.isNullOrEmpty() || needsEpisodeCounts)) {
                    try {
                        val cacheKey = Pair(item.id, item.season)
                        val newDeferred = CompletableDeferred<com.arflix.tv.data.api.TmdbSeasonDetails?>()
                        val existingDeferred = seasonCache.putIfAbsent(cacheKey, newDeferred)

                        val deferredSeason = if (existingDeferred == null) {
                            // We won the insert, do the network call
                            launch {
                                val result = try {
                                    tmdbApi.getTvSeason(item.id, item.season, apiKey)
                                } catch (_: Exception) { null }
                                newDeferred.complete(result)
                            }
                            newDeferred
                        } else {
                            // Another coroutine is already fetching
                            existingDeferred
                        }

                        deferredSeason.await()
                    } catch (_: Exception) { null }
                } else null
                val episodeInfo = seasonDetails?.episodes?.find { it.episodeNumber == item.episode }

                // Use SHOW's backdrop and overview (like Trakt does), not episode's
                val backdropUrl = details?.backdropPath?.let { "${Constants.BACKDROP_BASE_LARGE}$it" }
                val posterUrl = details?.posterPath?.let { "${Constants.IMAGE_BASE}$it" }
                val totalEpisodeCount = if (item.totalEpisodes > 0) {
                    item.totalEpisodes
                } else {
                    estimateAiredEpisodeCount(
                        seasons = details?.seasons.orEmpty(),
                        currentSeason = item.season,
                        currentSeasonEpisodes = seasonDetails?.episodes
                    ) ?: 0
                }
                val watchedEpisodeCount = if (item.watchedEpisodes > 0) {
                    item.watchedEpisodes
                } else {
                    estimateWatchedEpisodesBeforeCurrent(
                        seasons = details?.seasons.orEmpty(),
                        currentSeason = item.season,
                        currentEpisode = item.episode
                    )?.coerceAtMost(totalEpisodeCount.takeIf { it > 0 } ?: Int.MAX_VALUE)
                }
                val runtimeMinutes = episodeInfo?.runtime ?: details?.episodeRunTime?.firstOrNull()

                item.copy(
                    overview = details?.overview ?: item.overview,  // Show overview, not episode
                    backdropPath = backdropUrl ?: item.backdropPath,  // Show backdrop, not episode still
                    posterPath = posterUrl ?: item.posterPath,
                    year = details?.firstAirDate?.take(4) ?: item.year,
                    imdbRating = details?.voteAverage?.let { String.format("%.1f", it) } ?: item.imdbRating,
                    duration = runtimeMinutes?.let { "${it}m" } ?: item.duration,
                    durationSeconds = maxOf(item.durationSeconds, runtimeMinutesToSeconds(runtimeMinutes)),
                    episodeTitle = item.episodeTitle ?: episodeInfo?.name,
                    totalEpisodes = totalEpisodeCount,
                    watchedEpisodes = watchedEpisodeCount ?: item.watchedEpisodes
                )
            } else {
                val details = try {
                    tmdbApi.getMovieDetails(item.id, apiKey)
                } catch (_: Exception) { null }

                // Build full URLs for images
                val backdropUrl = details?.backdropPath?.let { "${Constants.BACKDROP_BASE_LARGE}$it" }
                val posterUrl = details?.posterPath?.let { "${Constants.IMAGE_BASE}$it" }

                item.copy(
                    overview = details?.overview ?: item.overview,
                    backdropPath = backdropUrl ?: item.backdropPath,
                    posterPath = posterUrl ?: item.posterPath,
                    year = details?.releaseDate?.take(4) ?: item.year,
                    imdbRating = details?.voteAverage?.let { String.format("%.1f", it) } ?: item.imdbRating,
                    duration = details?.runtime?.let { formatRuntime(it) } ?: item.duration,
                    durationSeconds = maxOf(item.durationSeconds, runtimeMinutesToSeconds(details?.runtime))
                )
            }
        } catch (_: Exception) {
            item // Return original on error
        }
    }

    /**
     * Get local Continue Watching for profiles without Trakt.
     * Returns items that were saved locally via saveLocalContinueWatching().
     */
    suspend fun getLocalContinueWatching(): List<ContinueWatchingItem> {
        return loadLocalContinueWatching()
    }

    /**
     * Check if current profile has Trakt authentication
     */
    suspend fun hasTrakt(): Boolean {
        return refreshTokenIfNeeded() != null
    }

    private fun formatRuntime(runtime: Int): String {
        val hours = runtime / 60
        val mins = runtime % 60
        return if (hours > 0) "${hours}h ${mins}m" else "${mins}m"
    }

    private fun runtimeMinutesToSeconds(minutes: Int?): Long {
        return minutes
            ?.takeIf { it > 0 }
            ?.toLong()
            ?.times(60L)
            ?: 0L
    }

    private fun parseIso8601(dateString: String): Long {
        return try {
            java.time.Instant.parse(dateString).toEpochMilli()
        } catch (e: Exception) {
            0L
        }
    }

    private suspend fun loadDismissedContinueWatching(): Map<String, Long> {
        val raw = context.settingsDataStore.data.first()[dismissedContinueWatchingKey()]
        return parseDismissedMap(raw)
    }

    private suspend fun persistDismissedContinueWatching(map: Map<String, Long>) {
        context.settingsDataStore.edit { prefs ->
            if (map.isEmpty()) {
                prefs.remove(dismissedContinueWatchingKey())
            } else {
                prefs[dismissedContinueWatchingKey()] = encodeDismissedMap(map)
            }
        }
    }

    suspend fun dismissContinueWatching(item: MediaItem) {
        val key = buildContinueWatchingKey(item) ?: return
        val showKey = buildContinueWatchingShowKey(item.mediaType, item.id)
        val now = System.currentTimeMillis()
        context.settingsDataStore.edit { prefs ->
            val map = parseDismissedMap(prefs[dismissedContinueWatchingKey()])
            map[key] = now
            map[showKey] = now
            prefs[dismissedContinueWatchingKey()] = encodeDismissedMap(map)
        }

        cachedContinueWatching = cachedContinueWatching.filterNot {
            it.id == item.id && it.mediaType == item.mediaType
        }
        val activeProfileId = runCatching { profileManager.getProfileIdSync() }.getOrNull()
        if (!activeProfileId.isNullOrBlank()) {
            preloadedProfileCache[activeProfileId] = preloadedProfileCache[activeProfileId]
                ?.filterNot { it.id == item.id && it.mediaType == item.mediaType }
                .orEmpty()
        }
    }

    suspend fun getDismissedContinueWatchingShowKeys(): Set<String> {
        val dismissed = loadDismissedContinueWatching()
        if (dismissed.isEmpty()) return emptySet()
        return dismissed.keys.mapNotNull { key ->
            when {
                key.startsWith("movie:") -> key.substringAfterLast(':').toIntOrNull()?.let { "MOVIE:$it" }
                key.startsWith("tv:") -> key.split(':').getOrNull(1)?.toIntOrNull()?.let { "TV:$it" }
                else -> null
            }
        }.toSet()
    }

    private fun buildContinueWatchingKey(item: ContinueWatchingItem): String? {
        return buildContinueWatchingKey(item.mediaType, item.id, item.season, item.episode)
    }

    private fun buildContinueWatchingKey(item: MediaItem): String? {
        val season = item.nextEpisode?.seasonNumber
        val episode = item.nextEpisode?.episodeNumber
        return buildContinueWatchingKey(item.mediaType, item.id, season, episode)
    }

    private fun buildContinueWatchingKey(
        mediaType: MediaType,
        tmdbId: Int,
        season: Int?,
        episode: Int?
    ): String {
        return if (mediaType == MediaType.MOVIE) {
            "movie:$tmdbId"
        } else {
            if (season != null && episode != null) {
                "tv:$tmdbId:$season:$episode"
            } else {
                "tv:$tmdbId"
            }
        }
    }

    private fun buildContinueWatchingShowKey(mediaType: MediaType, tmdbId: Int): String {
        return if (mediaType == MediaType.MOVIE) {
            "movie:$tmdbId"
        } else {
            "tv:$tmdbId"
        }
    }

    private fun parseDismissedMap(raw: String?): MutableMap<String, Long> {
        val map = mutableMapOf<String, Long>()
        if (raw.isNullOrBlank()) return map
        raw.split("|").forEach { entry ->
            val idx = entry.lastIndexOf(',')
            if (idx <= 0 || idx >= entry.length - 1) return@forEach
            val key = entry.substring(0, idx)
            val value = entry.substring(idx + 1).toLongOrNull() ?: return@forEach
            map[key] = value
        }
        return map
    }

    private fun encodeDismissedMap(map: Map<String, Long>): String {
        return map.entries.joinToString("|") { (key, value) -> "$key,$value" }
    }

    private suspend fun filterDismissedContinueWatchingItems(
        items: List<ContinueWatchingItem>,
        profileId: String? = null
    ): List<ContinueWatchingItem> {
        if (items.isEmpty()) return emptyList()

        val dismissed = if (profileId.isNullOrBlank()) {
            loadDismissedContinueWatching()
        } else {
            val prefs = context.settingsDataStore.data.first()
            val key = profileManager.profileStringKeyFor(profileId, "trakt_dismissed_continue_watching_v1")
            parseDismissedMap(prefs[key])
        }
        if (dismissed.isEmpty()) return items

        return items.filterNot { item ->
            val exactKey = buildContinueWatchingKey(item)
            val showKey = buildContinueWatchingShowKey(item.mediaType, item.id)
            dismissed.containsKey(showKey) || (exactKey != null && dismissed.containsKey(exactKey))
        }
    }

    private suspend fun persistContinueWatchingCache(items: List<ContinueWatchingItem>) {
        val trimmed = items.take(Constants.MAX_CONTINUE_WATCHING)
        val json = gson.toJson(trimmed)
        context.traktDataStore.edit { prefs ->
            prefs[continueWatchingCacheKey()] = json
        }
    }

    private suspend fun loadContinueWatchingCache(): List<ContinueWatchingItem> {
        val prefs = context.traktDataStore.data.first()
        val json = prefs[continueWatchingCacheKey()] ?: return emptyList()
        return try {
            val type = TypeToken.getParameterized(MutableList::class.java, ContinueWatchingItem::class.java).type
            val parsed: List<ContinueWatchingItem> = gson.fromJson(json, type)
            parsed
        } catch (_: Exception) {
            emptyList()
        }
    }

    // ========== Watchlist ==========

    data class WatchlistSyncResult(
        val items: List<MediaItem>,
        val rawCount: Int
    )

    suspend fun getWatchlist(): List<MediaItem> {
        return getWatchlistWithAuthState().second
    }

    suspend fun getWatchlistWithAuthState(): Pair<Boolean, List<MediaItem>> {
        val result = getWatchlistSyncResultWithAuthState()
        return result.first to result.second?.items.orEmpty()
    }

    suspend fun getWatchlistSyncResultWithAuthState(): Pair<Boolean, WatchlistSyncResult?> {
        val auth = getAuthHeader() ?: return false to null
        val watchlist = fetchAllWatchlistItems(auth)
        val items = hydrateWatchlistItems(watchlist)
        return true to WatchlistSyncResult(items = items, rawCount = watchlist.size)
    }

    private suspend fun getWatchlistFromTrakt(auth: String): List<MediaItem> {
        val watchlist = fetchAllWatchlistItems(auth)
        return hydrateWatchlistItems(watchlist)
    }

    private suspend fun hydrateWatchlistItems(watchlist: List<TraktWatchlistItem>): List<MediaItem> {
        val semaphore = Semaphore(6)
        return coroutineScope {
            watchlist.mapIndexed { index, item ->
                async {
                    semaphore.withPermit {
                        hydrateWatchlistItem(item, sourceOrder = index)
                    }
                }
            }.awaitAll()
                .filterNotNull()
                .sortedWith(compareBy<MediaItem> { it.sourceOrder }.thenByDescending { it.addedAt })
        }
    }

    private suspend fun fetchAllWatchlistItems(auth: String): List<TraktWatchlistItem> {
        val movieItems = fetchWatchlistItemsByType(auth, "movies")
        val showItems = fetchWatchlistItemsByType(auth, "shows")
        val typedItems = (movieItems.items + showItems.items)
            .distinctBy { watchlistIdentity(it) }
            .sortedByDescending { it.listedAt }
        if (movieItems.complete && showItems.complete) {
            if (typedItems.isNotEmpty()) return typedItems
        }

        val fallback = fetchWatchlistItemsFallback(auth)
        if (fallback.complete) {
            return fallback.items
                .sortedByDescending { it.listedAt }
                .distinctBy { watchlistIdentity(it) }
        }

        if (typedItems.isNotEmpty()) return typedItems

        throw IllegalStateException("Incomplete Trakt watchlist fetch")
    }

    private data class WatchlistFetchResult(
        val items: List<TraktWatchlistItem>,
        val complete: Boolean
    )

    private suspend fun fetchWatchlistItemsByType(auth: String, type: String): WatchlistFetchResult {
        val all = mutableListOf<TraktWatchlistItem>()
        val seen = LinkedHashSet<String>()
        val limit = 100
        var page = 1

        while (true) {
            val pageResult = try {
                fetchWatchlistPageRaw(
                    auth = auth,
                    type = type,
                    page = page,
                    limit = limit,
                    sort = "added"
                )
            } catch (_: Exception) {
                return WatchlistFetchResult(all, complete = false)
            }

            if (!pageResult.complete) {
                return WatchlistFetchResult(all, complete = false)
            }

            val pageItems = pageResult.items
            pageItems.forEach { item ->
                val key = watchlistIdentity(item)
                if (seen.add(key)) all.add(item)
            }

            val totalPages = pageResult.totalPages
            val hasMorePages = if (totalPages != null) {
                page < totalPages
            } else {
                pageItems.size >= limit
            }
            if (!hasMorePages) break
            page += 1
        }

        return WatchlistFetchResult(all, complete = true)
    }

    private suspend fun fetchWatchlistItemsFallback(auth: String): WatchlistFetchResult {
        val all = mutableListOf<TraktWatchlistItem>()
        val seen = LinkedHashSet<String>()
        val limit = 100
        var page = 1

        while (true) {
            val pageResult = try {
                fetchWatchlistPageRaw(
                    auth = auth,
                    type = null,
                    page = page,
                    limit = limit,
                    sort = null
                )
            } catch (_: Exception) {
                return WatchlistFetchResult(all, complete = false)
            }

            if (!pageResult.complete) {
                return WatchlistFetchResult(all, complete = false)
            }

            val pageItems = pageResult.items
            pageItems.forEach { item ->
                val key = watchlistIdentity(item)
                if (seen.add(key)) all.add(item)
            }

            val totalPages = pageResult.totalPages
            val hasMorePages = if (totalPages != null) {
                page < totalPages
            } else {
                pageItems.size >= limit
            }
            if (!hasMorePages) break
            page += 1
        }

        return WatchlistFetchResult(all, complete = true)
    }

    private data class WatchlistPageResult(
        val items: List<TraktWatchlistItem>,
        val totalPages: Int?,
        val complete: Boolean
    )

    private suspend fun fetchWatchlistPageRaw(
        auth: String,
        type: String?,
        page: Int,
        limit: Int,
        sort: String?
    ): WatchlistPageResult = withContext(Dispatchers.IO) {
        val urlBuilder = Constants.TRAKT_API_URL.toHttpUrl().newBuilder()
            .addPathSegment("users")
            .addPathSegment("me")
            .addPathSegment("watchlist")
        if (!type.isNullOrBlank()) {
            urlBuilder.addPathSegment(type)
            if (!sort.isNullOrBlank()) {
                urlBuilder.addPathSegment(sort)
            }
        }
        val url = urlBuilder
            .addQueryParameter("extended", "full")
            .addQueryParameter("page", page.toString())
            .addQueryParameter("limit", limit.toString())
            .build()

        val request = Request.Builder()
            .url(url)
            .get()
            .addHeader("Authorization", auth)
            .addHeader("trakt-api-key", clientId)
            .addHeader("trakt-api-version", "2")
            .build()

        watchlistHttpClient.newCall(request).execute().use { response ->
            if (!response.isSuccessful) {
                return@withContext WatchlistPageResult(emptyList(), totalPages = null, complete = false)
            }
            val body = response.body?.string().orEmpty()
            val listType = object : TypeToken<List<TraktWatchlistItem>>() {}.type
            val items: List<TraktWatchlistItem> = runCatching {
                gson.fromJson<List<TraktWatchlistItem>>(body, listType)
            }.getOrNull().orEmpty()
            WatchlistPageResult(
                items = items,
                totalPages = response.header("X-Pagination-Page-Count")?.toIntOrNull(),
                complete = true
            )
        }
    }

    private suspend fun mapWatchlistItemFast(item: TraktWatchlistItem): MediaItem? {
        val listedAtMs = parseTraktListedAtMs(item.listedAt)
        return when (item.type) {
            "movie" -> item.movie?.let { movie ->
                val tmdbId = resolveWatchlistMovieTmdbId(movie) ?: return null
                MediaItem(
                    id = tmdbId,
                    title = movie.title,
                    subtitle = "Movie",
                    overview = "",
                    year = movie.year?.toString().orEmpty(),
                    mediaType = MediaType.MOVIE,
                    image = "",
                    backdrop = null,
                    addedAt = listedAtMs
                )
            }
            "show" -> item.show?.let { show ->
                val tmdbId = resolveWatchlistShowTmdbId(show) ?: return null
                MediaItem(
                    id = tmdbId,
                    title = show.title,
                    subtitle = "TV Series",
                    overview = "",
                    year = show.year?.toString().orEmpty(),
                    mediaType = MediaType.TV,
                    image = "",
                    backdrop = null,
                    addedAt = listedAtMs
                )
            }
            else -> null
        }
    }

    private fun parseTraktListedAtMs(value: String?): Long {
        if (value.isNullOrBlank()) return 0L
        return runCatching { java.time.Instant.parse(value).toEpochMilli() }.getOrDefault(0L)
    }

    private suspend fun resolveWatchlistMovieTmdbId(movie: TraktMovieInfo): Int? {
        resolveWatchlistMovieDetails(movie)?.let { return it.id }
        return null
    }

    private suspend fun resolveWatchlistShowTmdbId(show: TraktShowInfo): Int? {
        resolveWatchlistShowDetails(show)?.let { return it.id }
        return null
    }

    private fun watchlistIdentity(item: TraktWatchlistItem): String {
        val ids = when (item.type) {
            "movie" -> item.movie?.ids
            "show" -> item.show?.ids
            else -> null
        }
        return listOfNotNull(
            item.type,
            ids?.trakt?.let { "trakt:$it" },
            ids?.tmdb?.let { "tmdb:$it" },
            ids?.tvdb?.let { "tvdb:$it" },
            ids?.imdb?.takeIf { it.isNotBlank() }?.let { "imdb:$it" }
        ).joinToString(":").ifBlank { "${item.type}:${item.rank}:${item.listedAt}" }
    }

    private suspend fun hydrateWatchlistItem(item: TraktWatchlistItem, sourceOrder: Int): MediaItem? {
        val listedAtMs = parseTraktListedAtMs(item.listedAt)
        return when (item.type) {
            "movie" -> item.movie?.let { movie ->
                val details = resolveWatchlistMovieDetails(movie)
                if (details != null) {
                    MediaItem(
                        id = details.id,
                        title = details.title,
                        subtitle = "Movie",
                        overview = details.overview ?: "",
                        year = details.releaseDate?.take(4) ?: "",
                        imdbRating = String.format("%.1f", details.voteAverage),
                        mediaType = MediaType.MOVIE,
                        image = details.posterPath?.let { "${Constants.IMAGE_BASE}$it" }
                            ?: details.backdropPath?.let { "${Constants.BACKDROP_BASE}$it" } ?: "",
                        backdrop = details.backdropPath?.let { "${Constants.BACKDROP_BASE_LARGE}$it" },
                        addedAt = listedAtMs,
                        sourceOrder = sourceOrder
                    )
                } else {
                    fallbackWatchlistItem(movie, listedAtMs, sourceOrder)
                }
            }
            "show" -> item.show?.let { show ->
                val details = resolveWatchlistShowDetails(show)
                if (details != null) {
                    MediaItem(
                        id = details.id,
                        title = details.name,
                        subtitle = "TV Series",
                        overview = details.overview ?: "",
                        year = details.firstAirDate?.take(4) ?: "",
                        imdbRating = String.format("%.1f", details.voteAverage),
                        mediaType = MediaType.TV,
                        image = details.posterPath?.let { "${Constants.IMAGE_BASE}$it" }
                            ?: details.backdropPath?.let { "${Constants.BACKDROP_BASE}$it" } ?: "",
                        backdrop = details.backdropPath?.let { "${Constants.BACKDROP_BASE_LARGE}$it" },
                        addedAt = listedAtMs,
                        sourceOrder = sourceOrder
                    )
                } else {
                    fallbackWatchlistItem(show, listedAtMs, sourceOrder)
                }
            }
            else -> null
        }
    }

    private fun fallbackWatchlistItem(
        movie: TraktMovieInfo,
        listedAtMs: Long,
        sourceOrder: Int
    ): MediaItem? {
        val tmdbId = movie.ids.tmdb?.takeIf { it > 0 } ?: return null
        return MediaItem(
            id = tmdbId,
            title = movie.title,
            subtitle = "Movie",
            overview = "",
            year = movie.year?.toString().orEmpty(),
            mediaType = MediaType.MOVIE,
            image = "",
            backdrop = null,
            addedAt = listedAtMs,
            sourceOrder = sourceOrder
        )
    }

    private fun fallbackWatchlistItem(
        show: TraktShowInfo,
        listedAtMs: Long,
        sourceOrder: Int
    ): MediaItem? {
        val tmdbId = show.ids.tmdb?.takeIf { it > 0 } ?: return null
        return MediaItem(
            id = tmdbId,
            title = show.title,
            subtitle = "TV Series",
            overview = "",
            year = show.year?.toString().orEmpty(),
            mediaType = MediaType.TV,
            image = "",
            backdrop = null,
            addedAt = listedAtMs,
            sourceOrder = sourceOrder
        )
    }

    private suspend fun resolveWatchlistMovieDetails(movie: TraktMovieInfo): TmdbMovieDetails? {
        val imdbId = movie.ids.imdb?.trim()?.takeIf { it.isNotEmpty() }
        val ids = buildList {
            imdbId?.let { id ->
                runCatching {
                    tmdbApi.findByExternalId(id, Constants.TMDB_API_KEY).movieResults
                        .mapNotNull { it.id.takeIf { tmdbId -> tmdbId > 0 } }
                }.getOrNull()?.let { addAll(it) }
            }
            movie.ids.tmdb?.takeIf { it > 0 }?.let { add(it) }
        }.distinct()

        val exactIdMatches = mutableListOf<TmdbMovieDetails>()
        for (id in ids) {
            val details = runCatching { tmdbApi.getMovieDetails(id, Constants.TMDB_API_KEY) }.getOrNull() ?: continue
            val sameTitle = isSameWatchlistTitle(movie.title, details.title) ||
                details.originalTitle?.let { isSameWatchlistTitle(movie.title, it) } == true
            val sameYear = yearCompatible(movie.year, details.releaseDate?.take(4)?.toIntOrNull())
            if (id == movie.ids.tmdb && (sameTitle || sameYear)) {
                return details
            }
            if (sameTitle && sameYear) {
                return details
            }
            if (sameTitle) exactIdMatches.add(details)
        }

        if (movie.year == null) {
            exactIdMatches.firstOrNull()?.let { return it }
        }

        val searchMatch = searchTmdbWatchlistMatch(movie.title, movie.year, MediaType.MOVIE)
        if (searchMatch != null) {
            return runCatching { tmdbApi.getMovieDetails(searchMatch, Constants.TMDB_API_KEY) }.getOrNull()
        }

        return if (movie.year == null) {
            exactIdMatches.firstOrNull()
        } else if (normalizeWatchlistTitle(movie.title).isBlank()) {
            ids.firstNotNullOfOrNull { id ->
                runCatching { tmdbApi.getMovieDetails(id, Constants.TMDB_API_KEY) }.getOrNull()
            }
        } else {
            null
        }
    }

    private suspend fun resolveWatchlistShowDetails(show: TraktShowInfo): TmdbTvDetails? {
        val imdbId = show.ids.imdb?.trim()?.takeIf { it.isNotEmpty() }
        val ids = buildList {
            imdbId?.let { id ->
                runCatching {
                    tmdbApi.findByExternalId(id, Constants.TMDB_API_KEY).tvResults
                        .mapNotNull { it.id.takeIf { tmdbId -> tmdbId > 0 } }
                }.getOrNull()?.let { addAll(it) }
            }
            show.ids.tvdb?.takeIf { it > 0 }?.let { tvdbId ->
                runCatching {
                    tmdbApi.findByExternalId(
                        tvdbId.toString(),
                        Constants.TMDB_API_KEY,
                        externalSource = "tvdb_id"
                    ).tvResults.mapNotNull { it.id.takeIf { tmdbId -> tmdbId > 0 } }
                }.getOrNull()?.let { addAll(it) }
            }
            show.ids.tmdb?.takeIf { it > 0 }?.let { add(it) }
        }.distinct()

        val exactIdMatches = mutableListOf<TmdbTvDetails>()
        for (id in ids) {
            val details = runCatching { tmdbApi.getTvDetails(id, Constants.TMDB_API_KEY) }.getOrNull() ?: continue
            val sameTitle = isSameWatchlistTitle(show.title, details.name) ||
                details.originalName?.let { isSameWatchlistTitle(show.title, it) } == true
            val sameYear = yearCompatible(show.year, details.firstAirDate?.take(4)?.toIntOrNull())
            if (id == show.ids.tmdb && (sameTitle || sameYear)) {
                return details
            }
            if (sameTitle && sameYear) {
                return details
            }
            if (sameTitle) exactIdMatches.add(details)
        }

        if (show.year == null) {
            exactIdMatches.firstOrNull()?.let { return it }
        }

        val searchMatch = searchTmdbWatchlistMatch(
            title = show.title,
            year = show.year,
            mediaType = MediaType.TV,
            allowTitleOnly = ids.isEmpty()
        )
        if (searchMatch != null) {
            return runCatching { tmdbApi.getTvDetails(searchMatch, Constants.TMDB_API_KEY) }.getOrNull()
        }

        return if (show.year == null) {
            exactIdMatches.firstOrNull()
        } else if (normalizeWatchlistTitle(show.title).isBlank()) {
            ids.firstNotNullOfOrNull { id ->
                runCatching { tmdbApi.getTvDetails(id, Constants.TMDB_API_KEY) }.getOrNull()
            }
        } else {
            null
        }
    }

    private suspend fun searchTmdbWatchlistMatch(
        title: String,
        year: Int?,
        mediaType: MediaType,
        allowTitleOnly: Boolean = false
    ): Int? {
        val normalizedTitle = normalizeWatchlistTitle(title)
        if (normalizedTitle.isBlank()) return null
        if (year == null && !allowTitleOnly) return null

        return runCatching {
            val results = when (mediaType) {
                MediaType.MOVIE -> tmdbApi.searchMovies(
                    apiKey = Constants.TMDB_API_KEY,
                    query = title,
                    page = 1,
                    primaryReleaseYear = year,
                    year = year
                ).results
                MediaType.TV -> tmdbApi.searchTv(
                    apiKey = Constants.TMDB_API_KEY,
                    query = title,
                    page = 1,
                    firstAirDateYear = year
                ).results
            }
            results
                .asSequence()
                .filter { result ->
                    when (mediaType) {
                        MediaType.MOVIE -> result.title != null
                        MediaType.TV -> result.name != null
                    }
                }
                .mapNotNull { result ->
                    val score = watchlistSearchScore(
                        traktTitle = title,
                        traktYear = year,
                        candidateTitle = result.title ?: result.name ?: "",
                        candidateOriginalTitle = result.originalTitle ?: result.originalName,
                        candidateYear = (result.releaseDate ?: result.firstAirDate)?.take(4)?.toIntOrNull(),
                        popularity = result.popularity,
                        voteCount = result.voteCount,
                        allowTitleOnly = allowTitleOnly
                    )
                    if (score > 0) result to score else null
                }
                .sortedByDescending { it.second }
                .map { it.first }
                .firstOrNull()
                ?.id
                ?.takeIf { it > 0 }
        }.getOrNull()
    }

    private fun isWatchlistMatch(
        traktTitle: String,
        traktYear: Int?,
        tmdbTitle: String,
        tmdbDate: String?
    ): Boolean {
        return isSameWatchlistTitle(traktTitle, tmdbTitle) &&
            yearCompatible(traktYear, tmdbDate?.take(4)?.toIntOrNull())
    }

    private fun isSameWatchlistTitle(first: String, second: String): Boolean {
        return normalizeWatchlistTitle(first) == normalizeWatchlistTitle(second)
    }

    private fun normalizeWatchlistTitle(title: String): String {
        return Normalizer.normalize(title, Normalizer.Form.NFD)
            .replace(DIACRITICS_REGEX, "")
            .lowercase(Locale.US)
            .replace("&", "and")
            .replace(NON_ALPHA_NUM_REGEX, " ")
            .trim()
            .removePrefix("the ")
            .removePrefix("a ")
            .removePrefix("an ")
            .replace(" ", "")
    }

    private fun watchlistSearchScore(
        traktTitle: String,
        traktYear: Int?,
        candidateTitle: String,
        candidateOriginalTitle: String?,
        candidateYear: Int?,
        popularity: Float,
        voteCount: Int,
        allowTitleOnly: Boolean = false
    ): Float {
        val sameTitle = isSameWatchlistTitle(traktTitle, candidateTitle) ||
            candidateOriginalTitle?.let { isSameWatchlistTitle(traktTitle, it) } == true
        if (!sameTitle) return 0f

        var score = 1000f
        if (traktYear != null && candidateYear != null) {
            val diff = if (traktYear > candidateYear) traktYear - candidateYear else candidateYear - traktYear
            if (diff > 1) return 0f
            score += if (diff == 0) 500f else 250f
        } else if (traktYear == null && !allowTitleOnly) {
            return 0f
        } else if (traktYear != null || candidateYear != null) {
            score -= 100f
        }

        score += popularity.coerceAtMost(250f)
        score += (voteCount / 100).coerceAtMost(100)
        return score
    }

    private fun yearCompatible(first: Int?, second: Int?): Boolean {
        if (first == null || second == null) return true
        val diff = if (first > second) first - second else second - first
        return diff <= 1
    }

    private fun searchYearCompatible(traktYear: Int?, tmdbYear: Int?): Boolean {
        if (traktYear == null) return true
        if (tmdbYear == null) return false
        val diff = if (traktYear > tmdbYear) traktYear - tmdbYear else tmdbYear - traktYear
        return diff <= 1
    }

    private fun yearDistance(first: Int?, second: Int?): Int? {
        if (first == null || second == null) return null
        return if (first > second) first - second else second - first
    }

    suspend fun addToWatchlist(mediaType: MediaType, tmdbId: Int): Boolean {
        val auth = getAuthHeader() ?: return false
        return try {
            val body = if (mediaType == MediaType.MOVIE) {
                TraktWatchlistBody(movies = listOf(TraktMovieId(TraktIds(tmdb = tmdbId))))
            } else {
                TraktWatchlistBody(shows = listOf(TraktShowId(TraktIds(tmdb = tmdbId))))
            }
            traktApi.addToWatchlist(auth, clientId, "2", body)
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun removeFromWatchlist(mediaType: MediaType, tmdbId: Int): Boolean {
        val auth = getAuthHeader() ?: return false
        return try {
            val body = if (mediaType == MediaType.MOVIE) {
                TraktWatchlistBody(movies = listOf(TraktMovieId(TraktIds(tmdb = tmdbId))))
            } else {
                TraktWatchlistBody(shows = listOf(TraktShowId(TraktIds(tmdb = tmdbId))))
            }
            traktApi.removeFromWatchlist(auth, clientId, "2", body)
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun checkInWatchlist(mediaType: MediaType, tmdbId: Int): Boolean {
        val auth = getAuthHeader() ?: return false
        return try {
            val watchlist = fetchAllWatchlistItems(auth)
            watchlist.any { item ->
                when (item.type) {
                    "movie" -> item.movie?.ids?.tmdb == tmdbId
                    "show" -> item.show?.ids?.tmdb == tmdbId
                    else -> false
                }
            }
        } catch (e: Exception) {
            false
        }
    }

    // ========== Collection Management ==========

    /**
     * Get user's movie collection
     */
    suspend fun getCollectionMovies(): List<TraktCollectionMovie> {
        val auth = getAuthHeader() ?: return emptyList()
        return try {
            traktApi.getCollectionMovies(auth, clientId)
        } catch (e: Exception) {
            emptyList()
        }
    }

    /**
     * Get user's show collection
     */
    suspend fun getCollectionShows(): List<TraktCollectionShow> {
        val auth = getAuthHeader() ?: return emptyList()
        return try {
            traktApi.getCollectionShows(auth, clientId)
        } catch (e: Exception) {
            emptyList()
        }
    }

    /**
     * Add movie to collection
     */
    suspend fun addMovieToCollection(tmdbId: Int): Boolean {
        val auth = getAuthHeader() ?: return false
        return try {
            traktApi.addToCollection(
                auth, clientId, "2",
                TraktCollectionBody(movies = listOf(TraktMovieId(TraktIds(tmdb = tmdbId))))
            )
            true
        } catch (e: Exception) {
            false
        }
    }

    /**
     * Add show to collection
     */
    suspend fun addShowToCollection(tmdbId: Int): Boolean {
        val auth = getAuthHeader() ?: return false
        return try {
            traktApi.addToCollection(
                auth, clientId, "2",
                TraktCollectionBody(shows = listOf(TraktShowId(TraktIds(tmdb = tmdbId))))
            )
            true
        } catch (e: Exception) {
            false
        }
    }

    /**
     * Remove movie from collection
     */
    suspend fun removeMovieFromCollection(tmdbId: Int): Boolean {
        val auth = getAuthHeader() ?: return false
        return try {
            traktApi.removeFromCollection(
                auth, clientId, "2",
                TraktCollectionBody(movies = listOf(TraktMovieId(TraktIds(tmdb = tmdbId))))
            )
            true
        } catch (e: Exception) {
            false
        }
    }

    /**
     * Remove show from collection
     */
    suspend fun removeShowFromCollection(tmdbId: Int): Boolean {
        val auth = getAuthHeader() ?: return false
        return try {
            traktApi.removeFromCollection(
                auth, clientId, "2",
                TraktCollectionBody(shows = listOf(TraktShowId(TraktIds(tmdb = tmdbId))))
            )
            true
        } catch (e: Exception) {
            false
        }
    }

    /**
     * Check if movie is in collection
     */
    suspend fun isMovieInCollection(tmdbId: Int): Boolean {
        val collection = getCollectionMovies()
        return collection.any { it.movie.ids.tmdb == tmdbId }
    }

    /**
     * Check if show is in collection
     */
    suspend fun isShowInCollection(tmdbId: Int): Boolean {
        val collection = getCollectionShows()
        return collection.any { it.show.ids.tmdb == tmdbId }
    }

    // ========== Ratings ==========

    /**
     * Get user's movie ratings
     */
    suspend fun getRatingsMovies(): List<TraktRatingItem> {
        val auth = getAuthHeader() ?: return emptyList()
        return try {
            traktApi.getRatingsMovies(auth, clientId)
        } catch (e: Exception) {
            emptyList()
        }
    }

    /**
     * Get user's show ratings
     */
    suspend fun getRatingsShows(): List<TraktRatingItem> {
        val auth = getAuthHeader() ?: return emptyList()
        return try {
            traktApi.getRatingsShows(auth, clientId)
        } catch (e: Exception) {
            emptyList()
        }
    }

    /**
     * Get user's episode ratings
     */
    suspend fun getRatingsEpisodes(): List<TraktRatingItem> {
        val auth = getAuthHeader() ?: return emptyList()
        return try {
            traktApi.getRatingsEpisodes(auth, clientId)
        } catch (e: Exception) {
            emptyList()
        }
    }

    /**
     * Rate a movie (1-10)
     */
    suspend fun rateMovie(tmdbId: Int, rating: Int): Boolean {
        val auth = getAuthHeader() ?: return false
        return try {
            traktApi.addRating(
                auth, clientId, "2",
                TraktRatingBody(
                    movies = listOf(TraktRatingMovieItem(rating = rating, ids = TraktIds(tmdb = tmdbId)))
                )
            )
            true
        } catch (e: Exception) {
            false
        }
    }

    /**
     * Rate a show (1-10)
     */
    suspend fun rateShow(tmdbId: Int, rating: Int): Boolean {
        val auth = getAuthHeader() ?: return false
        return try {
            traktApi.addRating(
                auth, clientId, "2",
                TraktRatingBody(
                    shows = listOf(TraktRatingShowItem(rating = rating, ids = TraktIds(tmdb = tmdbId)))
                )
            )
            true
        } catch (e: Exception) {
            false
        }
    }

    /**
     * Rate an episode (1-10)
     */
    suspend fun rateEpisode(showTmdbId: Int, season: Int, episode: Int, rating: Int): Boolean {
        val auth = getAuthHeader() ?: return false
        return try {
            traktApi.addRating(
                auth, clientId, "2",
                TraktRatingBody(
                    episodes = listOf(
                        TraktRatingEpisodeItem(
                            rating = rating,
                            ids = TraktIds(tmdb = showTmdbId),
                            season = season,
                            number = episode
                        )
                    )
                )
            )
            true
        } catch (e: Exception) {
            false
        }
    }

    /**
     * Remove movie rating
     */
    suspend fun removeMovieRating(tmdbId: Int): Boolean {
        val auth = getAuthHeader() ?: return false
        return try {
            traktApi.removeRating(
                auth, clientId, "2",
                TraktRatingBody(
                    movies = listOf(TraktRatingMovieItem(rating = 0, ids = TraktIds(tmdb = tmdbId)))
                )
            )
            true
        } catch (e: Exception) {
            false
        }
    }

    /**
     * Get movie rating (null if not rated)
     */
    suspend fun getMovieRating(tmdbId: Int): Int? {
        val ratings = getRatingsMovies()
        return ratings.find { it.movie?.ids?.tmdb == tmdbId }?.rating
    }

    /**
     * Get show rating (null if not rated)
     */
    suspend fun getShowRating(tmdbId: Int): Int? {
        val ratings = getRatingsShows()
        return ratings.find { it.show?.ids?.tmdb == tmdbId }?.rating
    }

    // ========== Comments ==========

    /**
     * Get movie comments
     */
    suspend fun getMovieComments(tmdbId: Int, page: Int = 1, limit: Int = 10): List<TraktComment> {
        return try {
            traktApi.getMovieComments(clientId, "2", tmdbId.toString(), page, limit)
        } catch (e: Exception) {
            emptyList()
        }
    }

    /**
     * Get show comments
     */
    suspend fun getShowComments(tmdbId: Int, page: Int = 1, limit: Int = 10): List<TraktComment> {
        return try {
            traktApi.getShowComments(clientId, "2", tmdbId.toString(), page, limit)
        } catch (e: Exception) {
            emptyList()
        }
    }

    /**
     * Get season comments
     */
    suspend fun getSeasonComments(showTmdbId: Int, season: Int, page: Int = 1, limit: Int = 10): List<TraktComment> {
        return try {
            traktApi.getSeasonComments(clientId, "2", showTmdbId.toString(), season, page, limit)
        } catch (e: Exception) {
            emptyList()
        }
    }

    /**
     * Get episode comments
     */
    suspend fun getEpisodeComments(showTmdbId: Int, season: Int, episode: Int, page: Int = 1, limit: Int = 10): List<TraktComment> {
        return try {
            traktApi.getEpisodeComments(clientId, "2", showTmdbId.toString(), season, episode, page, limit)
        } catch (e: Exception) {
            emptyList()
        }
    }

    // ========== Bulk Watch Operations ==========

    /**
     * Mark entire season as watched
     */
    suspend fun markSeasonWatched(showTmdbId: Int, seasonNumber: Int, episodes: List<Int>): Boolean {
        val auth = getAuthHeader() ?: return false
        return try {
            val episodeIds = episodes.map {
                TraktEpisodeId(
                    ids = TraktIds(tmdb = showTmdbId),
                    season = seasonNumber,
                    number = it
                )
            }
            traktApi.addToHistory(
                auth, clientId, "2",
                TraktHistoryBody(episodes = episodeIds)
            )
            // Update cache for all episodes
            episodes.forEach { ep ->
                updateWatchedCache(showTmdbId, seasonNumber, ep, true)
            }
            true
        } catch (e: Exception) {
            false
        }
    }

    /**
     * Mark entire show as watched
     */
    suspend fun markShowWatched(tmdbId: Int): Boolean {
        val auth = getAuthHeader() ?: return false
        return try {
            traktApi.addToHistory(
                auth, clientId, "2",
                TraktHistoryBody(shows = listOf(TraktHistoryShowWithSeasons(TraktIds(tmdb = tmdbId))))
            )
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun markShowUnwatched(tmdbId: Int): Boolean {
        val auth = getAuthHeader() ?: return false
        return try {
            traktApi.removeFromHistory(
                auth, clientId, "2",
                TraktHistoryBody(shows = listOf(TraktHistoryShowWithSeasons(TraktIds(tmdb = tmdbId))))
            )
            true
        } catch (e: Exception) {
            false
        }
    }

    /**
     * Mark multiple episodes as watched (batch)
     */
    suspend fun markEpisodesWatched(showTmdbId: Int, episodes: List<Pair<Int, Int>>): Boolean {
        val auth = getAuthHeader() ?: return false
        return try {
            val episodeIds = episodes.map { (season, episode) ->
                TraktEpisodeId(
                    ids = TraktIds(tmdb = showTmdbId),
                    season = season,
                    number = episode
                )
            }
            traktApi.addToHistory(
                auth, clientId, "2",
                TraktHistoryBody(episodes = episodeIds)
            )
            // Update cache
            episodes.forEach { (season, ep) ->
                updateWatchedCache(showTmdbId, season, ep, true)
            }
            true
        } catch (e: Exception) {
            false
        }
    }

    /**
     * Remove season from history
     */
    suspend fun removeSeasonFromHistory(showTmdbId: Int, seasonNumber: Int, episodes: List<Int>): Boolean {
        val auth = getAuthHeader() ?: return false
        return try {
            val episodeIds = episodes.map {
                TraktEpisodeId(
                    ids = TraktIds(tmdb = showTmdbId),
                    season = seasonNumber,
                    number = it
                )
            }
            traktApi.removeFromHistory(
                auth, clientId, "2",
                TraktHistoryBody(episodes = episodeIds)
            )
            // Update cache
            episodes.forEach { ep ->
                updateWatchedCache(showTmdbId, seasonNumber, ep, false)
            }
            true
        } catch (e: Exception) {
            false
        }
    }

    /**
     * Remove show from history
     */
    suspend fun removeShowFromHistory(tmdbId: Int): Boolean {
        val auth = getAuthHeader() ?: return false
        return try {
            traktApi.removeFromHistory(
                auth, clientId, "2",
                TraktHistoryBody(shows = listOf(TraktHistoryShowWithSeasons(TraktIds(tmdb = tmdbId))))
            )
            true
        } catch (e: Exception) {
            false
        }
    }

    /**
     * Remove items from history by history IDs
     */
    suspend fun removeFromHistoryByIds(ids: List<Long>): Boolean {
        val auth = getAuthHeader() ?: return false
        return try {
            traktApi.removeFromHistoryByIds(
                auth, clientId, "2",
                TraktHistoryRemoveBody(ids = ids)
            )
            true
        } catch (e: Exception) {
            false
        }
    }

    // ========== History (Paginated) ==========

    /**
     * Get paginated movie history
     */
    suspend fun getHistoryMovies(page: Int = 1, limit: Int = 20): List<TraktHistoryItem> {
        val auth = getAuthHeader() ?: return emptyList()
        return try {
            traktApi.getHistoryMovies(auth, clientId, "2", page, limit)
        } catch (e: Exception) {
            emptyList()
        }
    }

    /**
     * Get paginated episode history
     */
    suspend fun getHistoryEpisodes(page: Int = 1, limit: Int = 20): List<TraktHistoryItem> {
        val auth = getAuthHeader() ?: return emptyList()
        return try {
            traktApi.getHistoryEpisodes(auth, clientId, "2", page, limit)
        } catch (e: Exception) {
            emptyList()
        }
    }

    // ========== Local Watched Status Cache ==========

    // In-memory cache for watched status (mirrors Supabase data)
    private val watchedMoviesCache = mutableSetOf<Int>()
    private val watchedEpisodesCache = mutableSetOf<String>()
    private var cacheInitialized = false
    @Volatile private var cacheInitializing = false

    /**
     * Invalidate watched cache - forces reload on next access
     * Call this after sync operations to pick up new data
     */
    fun invalidateWatchedCache() {
        ensureProfileCacheScope()
        cacheInitialized = false
        watchedMoviesCache.clear()
        watchedEpisodesCache.clear()
    }

    /**
     * Initialize watched cache from Supabase (source of truth)
     * Falls back to Trakt if Supabase data is not available
     *
     * IMPORTANT: If the current profile has no Trakt auth, caches remain empty
     * so all content appears unwatched (proper profile isolation)
     */
    suspend fun initializeWatchedCache() {
        ensureProfileCacheScope()
        if (cacheInitialized) return
        // Prevent multiple simultaneous initializations
        if (cacheInitializing) {
            // Wait for ongoing initialization to complete
            while (cacheInitializing && !cacheInitialized) {
                delay(50)
            }
            return
        }
        cacheInitializing = true
        try {
            val hasTraktAuth = refreshTokenIfNeeded() != null
            val (localSnapshotMovies, localSnapshotEpisodes) = loadLocalWatchedSnapshotForCurrentProfile()

            // Try to load from Supabase first (works for both Trakt and non-Trakt Cloud profiles)
            val supabaseMovies = syncService.getWatchedMovies()
            val supabaseEpisodes = syncService.getWatchedEpisodes()

            // If no Trakt auth AND no Supabase data, leave caches empty
            if (!hasTraktAuth && supabaseMovies.isEmpty() && supabaseEpisodes.isEmpty()) {
                watchedMoviesCache.clear()
                watchedMoviesCache.addAll(localSnapshotMovies)
                watchedEpisodesCache.clear()
                watchedEpisodesCache.addAll(localSnapshotEpisodes)
                cacheInitialized = true
                return
            }

            // Only fall back to Trakt API if we have Trakt auth and no Supabase data
            val traktMovies = if (supabaseMovies.isEmpty() && hasTraktAuth) getWatchedMovies() else emptySet()
            val traktEpisodes = if (supabaseEpisodes.isEmpty() && hasTraktAuth) getWatchedEpisodes() else emptySet()

            watchedMoviesCache.clear()
            watchedMoviesCache.addAll(localSnapshotMovies)
            watchedMoviesCache.addAll(if (supabaseMovies.isNotEmpty()) supabaseMovies else traktMovies)

            watchedEpisodesCache.clear()
            watchedEpisodesCache.addAll(localSnapshotEpisodes)
            watchedEpisodesCache.addAll(if (supabaseEpisodes.isNotEmpty()) supabaseEpisodes else traktEpisodes)

            cacheInitialized = true
        } catch (e: Exception) {
            // If sync service fails, try direct Trakt load (only if Trakt auth available)
            try {
                val (localSnapshotMovies, localSnapshotEpisodes) = loadLocalWatchedSnapshotForCurrentProfile()
                val hasTraktFallback = refreshTokenIfNeeded() != null
                if (hasTraktFallback) {
                    watchedMoviesCache.clear()
                    watchedMoviesCache.addAll(localSnapshotMovies)
                    watchedMoviesCache.addAll(getWatchedMovies())
                    watchedEpisodesCache.clear()
                    watchedEpisodesCache.addAll(localSnapshotEpisodes)
                    watchedEpisodesCache.addAll(getWatchedEpisodes())
                } else {
                    watchedMoviesCache.clear()
                    watchedMoviesCache.addAll(localSnapshotMovies)
                    watchedEpisodesCache.clear()
                    watchedEpisodesCache.addAll(localSnapshotEpisodes)
                }
                cacheInitialized = true
            } catch (_: Exception) {
                // No data available - mark as initialized with empty caches
                cacheInitialized = true
            }
        } finally {
            cacheInitializing = false
        }
    }

    /**
     * Update watched cache entry
     */
    private fun updateWatchedCache(tmdbId: Int, season: Int?, episode: Int?, watched: Boolean) {
        ensureProfileCacheScope()
        if (season == null || episode == null) {
            // Movie
            if (watched) {
                watchedMoviesCache.add(tmdbId)
            } else {
                watchedMoviesCache.remove(tmdbId)
            }
        } else {
            // Episode
            val key = buildEpisodeKey(
                traktEpisodeId = null,
                showTraktId = null,
                showTmdbId = tmdbId,
                season = season,
                episode = episode
            ) ?: return
            if (watched) {
                watchedEpisodesCache.add(key)
            } else {
                watchedEpisodesCache.remove(key)
            }
        }
    }

    /**
     * Check if movie is watched (uses cache)
     */
    fun isMovieWatched(tmdbId: Int): Boolean {
        ensureProfileCacheScope()
        return watchedMoviesCache.contains(tmdbId)
    }

    /**
     * Check if episode is watched (uses cache)
     */
    fun isEpisodeWatched(tmdbId: Int, season: Int, episode: Int): Boolean {
        ensureProfileCacheScope()
        val key = buildEpisodeKey(
            traktEpisodeId = null,
            showTraktId = null,
            showTmdbId = tmdbId,
            season = season,
            episode = episode
        ) ?: return false
        return watchedEpisodesCache.contains(key)
    }

    /**
     * Get all watched movie IDs from cache
     */
    fun getWatchedMoviesFromCache(): Set<Int> {
        ensureProfileCacheScope()
        return watchedMoviesCache.toSet()
    }

    /**
     * Get all watched episode keys from cache
     */
    fun getWatchedEpisodesFromCache(): Set<String> {
        ensureProfileCacheScope()
        return watchedEpisodesCache.toSet()
    }

    /**
     * Check if show has any watched episodes - optimized to avoid full iteration
     */
    fun hasWatchedEpisodes(showTmdbId: Int): Boolean {
        ensureProfileCacheScope()
        val prefix = "show_tmdb:$showTmdbId:"
        return watchedEpisodesCache.any { it.startsWith(prefix) }
    }

    // ========== Background Sync ==========

    /**
     * Sync watched history from Trakt - used by background worker
     * Pre-fetches and caches watched movies and episodes using the local cache
     */
    suspend fun syncWatchedHistory() {
        if (getAuthHeader() == null) return
        try {
            // Invalidate cache and re-initialize to get fresh data
            invalidateWatchedCache()
            initializeWatchedCache()

        } catch (e: Exception) {
            throw e
        }
    }
}

/**
 * Continue watching item model
 */
data class ContinueWatchingItem(
    val id: Int,
    val title: String,
    val mediaType: MediaType,
    val progress: Int, // 0-100
    val resumePositionSeconds: Long = 0L,
    val durationSeconds: Long = 0L,
    val season: Int? = null,
    val episode: Int? = null,
    val episodeTitle: String? = null,
    val backdropPath: String? = null,
    val posterPath: String? = null,
    val streamKey: String? = null,
    val streamAddonId: String? = null,
    val streamTitle: String? = null,
    val year: String = "",
    val releaseDate: String = "",  // Full formatted date
    val isUpNext: Boolean = false,
    val overview: String = "",
    val imdbRating: String = "",
    val duration: String = "",
    val budget: Long? = null,
    val updatedAtMs: Long = 0L,
    val totalEpisodes: Int = 0,
    val watchedEpisodes: Int = 0
) {
    fun toMediaItem(): MediaItem {
        val effectiveDurationSeconds = durationSeconds.takeIf { it > 0L } ?: parseRuntimeLabelSeconds(duration)
        val showPlaybackProgress = !isUpNext && progress in 1..94
        val resumeSeconds = when {
            resumePositionSeconds > 0L -> resumePositionSeconds
            // Only derive resume position from progress if we have a meaningful duration
            // and progress is above a trivial threshold (>5%) to avoid showing bogus
            // resume times for placeholder "next episode" entries.
            !isUpNext && effectiveDurationSeconds > 0L && progress > 5 ->
                ((effectiveDurationSeconds * progress) / 100L).coerceAtLeast(1L)
            else -> 0L
        }
        val resumeLabel = resumeSeconds.takeIf { it > 0L }?.let { formatResumeClock(it) }

        val subtitle = if (mediaType == MediaType.TV && season != null && episode != null) {
            val base = "Continue S${season}.E${episode}"
            if (!resumeLabel.isNullOrBlank()) "$base from $resumeLabel" else base
        } else {
            if (mediaType == MediaType.MOVIE) {
                if (!resumeLabel.isNullOrBlank()) "Continue from $resumeLabel" else "Continue"
            } else {
                "TV Series"
            }
        }

        val nextEp = if (mediaType == MediaType.TV && season != null && episode != null) {
            NextEpisode(
                id = 0,
                seasonNumber = season,
                episodeNumber = episode,
                name = episodeTitle ?: "Episode $episode"
            )
        } else null

        // Compute remaining time: duration - resume position
        val timeRemainingSeconds = when {
            effectiveDurationSeconds > 0L && resumePositionSeconds > 0L ->
                (effectiveDurationSeconds - resumePositionSeconds).coerceAtLeast(0L)
            !isUpNext && effectiveDurationSeconds > 0L && progress in 1..94 ->
                (effectiveDurationSeconds * (100L - progress) / 100L).coerceAtLeast(0L)
            else -> 0L
        }
        val timeRemainingLabel = if (showPlaybackProgress) {
            formatTimeRemainingCompact(timeRemainingSeconds)
        } else {
            null
        }

        val totalEpisodeCount = totalEpisodes.takeIf { mediaType == MediaType.TV && it > 0 }
        val watchedEpisodeCount = watchedEpisodes
            .takeIf { totalEpisodeCount != null && it > 0 }
            ?.coerceAtMost(totalEpisodeCount ?: 0)

        return MediaItem(
            id = id,
            title = title,
            subtitle = subtitle,
            overview = overview,
            year = year,
            releaseDate = releaseDate,
            imdbRating = imdbRating,
            duration = duration,
            mediaType = mediaType,
            progress = progress,
            image = posterPath ?: backdropPath ?: "",
            backdrop = backdropPath,
            badge = null,
            budget = budget,
            nextEpisode = nextEp,
            totalEpisodes = totalEpisodeCount,
            watchedEpisodes = watchedEpisodeCount,
            timeRemainingLabel = timeRemainingLabel,
            showPlaybackProgress = showPlaybackProgress
        )
    }
}

private fun estimateWatchedEpisodesBeforeCurrent(
    seasons: List<com.arflix.tv.data.api.TmdbTvSeason>,
    currentSeason: Int?,
    currentEpisode: Int?
): Int? {
    if (currentSeason == null || currentEpisode == null) return null
    val previousSeasonCount = seasons
        .asSequence()
        .filter { it.seasonNumber > 0 && it.seasonNumber < currentSeason }
        .sumOf { it.episodeCount.coerceAtLeast(0) }
    return previousSeasonCount + (currentEpisode - 1).coerceAtLeast(0)
}

private fun estimateAiredEpisodeCount(
    seasons: List<com.arflix.tv.data.api.TmdbTvSeason>,
    currentSeason: Int?,
    currentSeasonEpisodes: List<com.arflix.tv.data.api.TmdbEpisode>?
): Int? {
    if (currentSeason == null) return null

    val previousSeasonCount = seasons
        .asSequence()
        .filter { season ->
            season.seasonNumber > 0 &&
                season.seasonNumber < currentSeason &&
                isAlreadyAiredDate(season.airDate)
        }
        .sumOf { it.episodeCount.coerceAtLeast(0) }

    val currentSeasonCount = currentSeasonEpisodes
        ?.count { episode -> isAlreadyAiredDate(episode.airDate) }
        ?: seasons
            .firstOrNull { it.seasonNumber == currentSeason }
            ?.takeIf { isAlreadyAiredDate(it.airDate) }
            ?.episodeCount
            ?.coerceAtLeast(0)
        ?: 0

    return (previousSeasonCount + currentSeasonCount).takeIf { it > 0 }
}

private fun isAlreadyAiredDate(rawDate: String?): Boolean {
    val value = rawDate?.trim().orEmpty()
    if (value.isEmpty()) return false
    return try {
        val parser = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        parser.isLenient = false
        val parsed = parser.parse(value) ?: return false
        parsed.time <= System.currentTimeMillis()
    } catch (_: Exception) {
        false
    }
}

private fun formatResumeClock(totalSeconds: Long): String {
    val safe = totalSeconds.coerceAtLeast(0L)
    val hours = safe / 3600
    val minutes = (safe % 3600) / 60
    val seconds = safe % 60
    return if (hours > 0) {
        "%d:%02d:%02d".format(hours, minutes, seconds)
    } else {
        "%d:%02d".format(minutes, seconds)
    }
}
/**
 * Format seconds to a compact human-readable time remaining string.
 * e.g., "45min left", "1hr 15min left", "2hr left"
 */
private fun formatTimeRemainingCompact(totalSeconds: Long): String? {
    val safe = totalSeconds.coerceAtLeast(0L)
    if (safe < 60) return null // Less than a minute
    val hours = safe / 3600
    val minutes = (safe % 3600) / 60
    return when {
        hours > 0 && minutes > 0 -> "${hours}hr ${minutes}min left"
        hours > 0 -> "${hours}hr left"
        else -> "${minutes}min left"
    }
}

private fun parseRuntimeLabelSeconds(label: String): Long {
    val normalized = label.lowercase(Locale.US)
    if (normalized.isBlank()) return 0L

    var minutes = 0L
    HOURS_REGEX.find(normalized)?.groupValues?.getOrNull(1)?.toLongOrNull()?.let { hours ->
        minutes += hours * 60L
    }
    MINS_REGEX.find(normalized)?.groupValues?.getOrNull(1)?.toLongOrNull()?.let { mins ->
        minutes += mins
    }

    return minutes.takeIf { it > 0L }?.times(60L) ?: 0L
}

private data class ContinueWatchingCandidate(
    val item: ContinueWatchingItem,
    val lastActivityAt: String
)

/**
 * Format date from "yyyy-MM-dd" to "MMMM d, yyyy" (e.g., "December 16, 2025")
 */
private fun formatDateString(dateStr: String?): String {
    if (dateStr.isNullOrEmpty()) return ""
    return try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val outputFormat = SimpleDateFormat("MMMM d, yyyy", Locale.US)
        val date = inputFormat.parse(dateStr)
        date?.let { outputFormat.format(it) } ?: ""
    } catch (e: Exception) {
        ""
    }
}

private fun buildEpisodeKey(
    traktEpisodeId: Int?,
    showTraktId: Int?,
    showTmdbId: Int?,
    season: Int?,
    episode: Int?
): String? {
    return when {
        traktEpisodeId != null -> "trakt:$traktEpisodeId"
        showTraktId != null && season != null && episode != null -> "show_trakt:$showTraktId:$season:$episode"
        showTmdbId != null && season != null && episode != null -> "show_tmdb:$showTmdbId:$season:$episode"
        else -> null
    }

}

private val DIACRITICS_REGEX = Regex("\\p{Mn}+")
private val NON_ALPHA_NUM_REGEX = Regex("[^a-z0-9]+")
private val HOURS_REGEX = Regex("""(\d+)\s*h""")
private val MINS_REGEX = Regex("""(\d+)\s*m""")
