package com.arflix.tv.data.repository

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.arflix.tv.data.api.*
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.model.NextEpisode
import com.arflix.tv.util.ContinueWatchingSelector
import com.arflix.tv.util.EpisodePointer
import com.arflix.tv.util.EpisodeProgressSnapshot
import com.arflix.tv.util.WatchedEpisodeSnapshot
import com.arflix.tv.util.Constants
import com.arflix.tv.util.settingsDataStore
import com.arflix.tv.util.traktDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.delay
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withPermit
import kotlinx.coroutines.withContext
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.put
import retrofit2.HttpException
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
    private val TAG = "TraktRepository"
    private val gson = Gson()

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
    private fun continueWatchingCacheKey() = profileManager.profileStringKey("trakt_continue_watching_cache_v1")
    // Local Continue Watching for profiles without Trakt - stores progress locally per profile
    private fun localContinueWatchingKey() = profileManager.profileStringKey("local_continue_watching_v1")

    data class CloudTraktToken(
        val accessToken: String,
        val refreshToken: String?,
        val expiresAt: Long?
    )

    private var attemptedProfileTokenLoad = false
    @Volatile private var cachedContinueWatching: List<ContinueWatchingItem> = emptyList()
    @Volatile private var continueWatchingFetching = false
    private var lastContinueWatchingFetch = 0L
    private val CONTINUE_WATCHING_CACHE_MS = 300_000L // 5 minute cache to reduce API calls and improve performance

    @Serializable
    private data class TraktTokenUpdate(
        val trakt_token: JsonObject,
        val updated_at: String
    )

    private val Context.authDataStore: DataStore<Preferences> by preferencesDataStore(name = "auth_prefs")

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
        val prefs = context.traktDataStore.data.first()
        val accessToken = prefs[accessTokenKey()] ?: return null
        val refreshToken = prefs[refreshTokenKey()]
        val expiresAt = prefs[expiresAtKey()]

        // If we don't have refresh metadata (older tokens), use the existing access token
        if (refreshToken == null || expiresAt == null) {
            return accessToken
        }

        // Check if token is expired (with 1 hour buffer)
        val now = System.currentTimeMillis() / 1000
        if (now < expiresAt - 3600) {
            return accessToken
        }

        // Refresh token
        return try {
            val newToken = traktApi.refreshToken(
                RefreshTokenRequest(
                    refreshToken = refreshToken,
                    clientId = clientId,
                    clientSecret = clientSecret
                )
            )
            saveToken(newToken)
            newToken.accessToken
        } catch (e: Exception) {
            accessToken
        }
    }

    private suspend fun saveToken(token: TraktToken) {
        context.traktDataStore.edit { prefs ->
            prefs[accessTokenKey()] = token.accessToken
            prefs[refreshTokenKey()] = token.refreshToken
            prefs[expiresAtKey()] = token.createdAt + token.expiresIn
        }

        // Sync to Supabase profile
        syncTokenToSupabase(token)
    }

    /**
     * Sync Trakt token to Supabase profile
     */
    private suspend fun syncTokenToSupabase(token: TraktToken) {
        try {
            val prefs = context.traktDataStore.data.first()
            val userId = prefs[USER_ID_KEY] ?: return

            // Build token object matching webapp format
            val tokenJson = buildJsonObject {
                put("access_token", token.accessToken)
                put("refresh_token", token.refreshToken)
                put("expires_in", token.expiresIn)
                put("created_at", token.createdAt)
            }

            supabase.postgrest
                .from("profiles")
                .update(TraktTokenUpdate(tokenJson, java.time.Instant.now().toString())) {
                    filter { eq("id", userId) }
                }

        } catch (e: Exception) {
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
        if (traktToken == null) return

        try {
            val accessToken = traktToken["access_token"]?.toString()?.trim('"') ?: return
            val refreshToken = traktToken["refresh_token"]?.toString()?.trim('"') ?: return
            val expiresIn = traktToken["expires_in"]?.toString()?.toLongOrNull() ?: 7776000L
            val createdAt = traktToken["created_at"]?.toString()?.toLongOrNull() ?: (System.currentTimeMillis() / 1000)

            context.traktDataStore.edit { prefs ->
                prefs[accessTokenKey()] = accessToken
                prefs[refreshTokenKey()] = refreshToken
                prefs[expiresAtKey()] = createdAt + expiresIn
            }

        } catch (e: Exception) {
        }
    }

    suspend fun logout() {
        context.traktDataStore.edit { prefs ->
            prefs.remove(accessTokenKey())
            prefs.remove(refreshTokenKey())
            prefs.remove(expiresAtKey())
        }
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
    }

    private suspend fun getAuthHeader(): String? {
        val token = refreshTokenIfNeeded()
        if (token != null) {
            return "Bearer $token"
        }

        // Fallback: load Trakt tokens from Supabase profile if available
        if (!attemptedProfileTokenLoad) {
            attemptedProfileTokenLoad = true
            try {
                val userId = context.traktDataStore.data.first()[USER_ID_KEY]
                    ?: context.authDataStore.data.first()[USER_ID_KEY]
                if (!userId.isNullOrBlank()) {
                    val profile = supabase.postgrest
                        .from("profiles")
                        .select { filter { eq("id", userId) } }
                        .decodeSingleOrNull<JsonObject>()
                    val traktTokenElement = profile?.get("trakt_token")
                    when {
                        traktTokenElement is JsonObject -> {
                            loadTokensFromProfile(traktTokenElement)
                        }
                        traktTokenElement != null -> {
                            val accessToken = traktTokenElement.jsonPrimitive.content
                            if (accessToken.isNotBlank() && accessToken != "null") {
                                context.traktDataStore.edit { prefs ->
                                    prefs[accessTokenKey()] = accessToken
                                }
                            }
                        }
                    }
                }
            } catch (e: Exception) {
            }
        }

        val refreshed = refreshTokenIfNeeded() ?: return null
        return "Bearer $refreshed"
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
        // OPTIMISTIC UPDATE: Update caches immediately so the UI responds instantly
        updateWatchedCache(tmdbId, null, null, true)
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
        // OPTIMISTIC UPDATE: Update cache immediately so the UI responds instantly
        updateWatchedCache(tmdbId, null, null, false)

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
        // OPTIMISTIC UPDATE: Update all caches immediately so the UI responds instantly
        updateWatchedCache(showTmdbId, season, episode, true)
        updateShowWatchedCache(showTmdbId, season, episode, true)
        removeFromContinueWatchingCache(showTmdbId, season, episode)

        // Then sync to backend in background (don't block UI on network)
        try {
            val traktShowId = tmdbToTraktIdCache[showTmdbId]
            syncService.markEpisodeWatched(showTmdbId, season, episode, traktShowId)
        } catch (e: Exception) {
            // Sync failed, but local cache is already updated
            // Could add retry logic here if needed
        }
    }

    /**
     * Mark episode as unwatched - updates local cache immediately (optimistic), then syncs to backend
     */
    suspend fun markEpisodeUnwatched(showTmdbId: Int, season: Int, episode: Int) {
        // OPTIMISTIC UPDATE: Update all caches immediately so the UI responds instantly
        updateWatchedCache(showTmdbId, season, episode, false)
        updateShowWatchedCache(showTmdbId, season, episode, false)

        // Then sync to backend in background
        try {
            syncService.markEpisodeUnwatched(showTmdbId, season, episode)
        } catch (e: Exception) {
            // Sync failed, but local cache is already updated
        }
    }

    // ========== Scrobbling (Like NuvioStreaming) ==========

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
     * Uses queue-based deduplication like NuvioStreaming
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

        // Auto-mark as watched if progress >= threshold (like NuvioStreaming)
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
     * Delete playback progress for specific content (like NuvioStreaming's deletePlaybackForContent)
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
        val auth = getAuthHeader() ?: return emptySet()

        // Check cache first (within cache duration)
        val now = System.currentTimeMillis()
        if (now - showWatchedCacheTime < SHOW_CACHE_DURATION_MS) {
            showWatchedEpisodesCache[tmdbId]?.let { cachedSet ->
                return cachedSet
            }
        }

        val watchedSet = mutableSetOf<String>()

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
                showWatchedEpisodesCache[tmdbId] = emptySet()
                return emptySet()
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
        try {
            val prefs = context.traktDataStore.data.first()
            val accessToken = prefs[accessTokenKey()] ?: return
            val refreshToken = prefs[refreshTokenKey()]
            val expiresAt = prefs[expiresAtKey()]
            val now = System.currentTimeMillis() / 1000
            val computedExpiresIn = expiresAt?.let { (it - now).toInt() } ?: 0
            val expiresIn = if (computedExpiresIn > 0) computedExpiresIn else 7776000
            val createdAt = if (computedExpiresIn > 0 && expiresAt != null) {
                (expiresAt - expiresIn).coerceAtMost(now)
            } else {
                now
            }

            val userId = prefs[USER_ID_KEY]
                ?: context.authDataStore.data.first()[USER_ID_KEY]
                ?: return

            val tokenJson = buildJsonObject {
                put("access_token", accessToken)
                refreshToken?.let { put("refresh_token", it) }
                put("expires_in", expiresIn)
                put("created_at", createdAt)
            }

            supabase.postgrest
                .from("profiles")
                .update(TraktTokenUpdate(tokenJson, java.time.Instant.now().toString())) {
                    filter { eq("id", userId) }
                }

        } catch (e: Exception) {
        }
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

    /**
     * Get items to continue watching - Uses Trakt API directly for accuracy and speed.
     * For profiles without Trakt, falls back to local Continue Watching storage.
     * Refactored to fetch more shows and process in parallel.
     */
    suspend fun getContinueWatching(): List<ContinueWatchingItem> = coroutineScope {
        val auth = getAuthHeader()

        // If no Trakt auth, use local Continue Watching for this profile
        if (auth == null) {
            val localItems = loadLocalContinueWatching()
            cachedContinueWatching = localItems
            return@coroutineScope localItems
        }

        // Return cached data if still fresh
        val now = System.currentTimeMillis()
        if (cachedContinueWatching.isNotEmpty() && now - lastContinueWatchingFetch < CONTINUE_WATCHING_CACHE_MS) {
            return@coroutineScope cachedContinueWatching
        }

        // Prevent duplicate fetches
        if (continueWatchingFetching) {
            while (continueWatchingFetching) { delay(50) }
            return@coroutineScope cachedContinueWatching
        }
        continueWatchingFetching = true

        try {
        val candidates = mutableListOf<ContinueWatchingCandidate>()
        val includeSpecials = context.settingsDataStore.data.first()[includeSpecialsKey()] ?: false

        initializeWatchedCache()

            // Mirror Trakt progress page:
            // 1. Fetch watched shows + hidden shows in parallel
            // 2. Filter out hidden & stale shows
            // 3. Check progress for remaining shows
            val watchedShowsDeferred = async {
                try {
                    traktApi.getWatchedShows(auth, clientId)
                        .sortedByDescending { it.lastWatchedAt }
                } catch (_: Exception) { emptyList() }
            }
            val hiddenShowsDeferred = async {
                try {
                    traktApi.getHiddenProgressShows(auth, clientId)
                } catch (_: Exception) { emptyList() }
            }

            val watchedShows = watchedShowsDeferred.await()
            val hiddenTraktIds = hiddenShowsDeferred.await()
                .mapNotNull { it.show?.ids?.trakt }
                .toSet()

            // Filter: remove hidden shows, remove stale (>6 months), take top 50
            val sixMonthsAgo = System.currentTimeMillis() - (180L * 24 * 60 * 60 * 1000)
            val filteredShows = watchedShows
                .filter { show ->
                    val traktId = show.show.ids.trakt
                    val lastWatchedMs = parseIso8601(show.lastWatchedAt ?: "")
                    traktId != null &&
                        traktId !in hiddenTraktIds &&
                        lastWatchedMs > sixMonthsAgo
                }
                .take(50)

            // Fetch progress for filtered shows in parallel
            val semaphore = Semaphore(5)

            val showTasks = filteredShows.map { show ->
                async {
                    semaphore.withPermit {
                        val tmdbId = show.show.ids.tmdb ?: return@withPermit null
                        val traktId = show.show.ids.trakt ?: return@withPermit null

                        try {
                            val progress = traktApi.getShowProgress(
                                auth, clientId, "2", traktId.toString(),
                                specials = includeSpecials.toString(),
                                countSpecials = includeSpecials.toString()
                            )

                            // Handle reset_at: if user is rewatching, recalculate completed count
                            val effectiveCompleted = if (progress.resetAt != null) {
                                val resetMs = parseIso8601(progress.resetAt)
                                progress.seasons?.sumOf { season ->
                                    season.episodes?.count { ep ->
                                        ep.completed && parseIso8601(ep.lastWatchedAt ?: "") > resetMs
                                    } ?: 0
                                } ?: progress.completed
                            } else {
                                progress.completed
                            }

                            val nextEp = progress.nextEpisode
                            val isIncomplete = effectiveCompleted < progress.aired

                            if (isIncomplete && effectiveCompleted >= 1) {
                                ContinueWatchingCandidate(
                                    item = ContinueWatchingItem(
                                        id = tmdbId,
                                        title = show.show.title,
                                        mediaType = MediaType.TV,
                                        progress = 0,
                                        season = nextEp?.season,
                                        episode = nextEp?.number,
                                        episodeTitle = nextEp?.title,
                                        year = show.show.year?.toString() ?: ""
                                    ),
                                    lastActivityAt = show.lastWatchedAt ?: ""
                                )
                            } else {
                                null
                            }
                        } catch (_: Exception) {
                            null
                        }
                    }
                }
            }

            candidates.addAll(showTasks.awaitAll().filterNotNull())

            // Also include in-progress playback items (movies + episodes)
            try {
                val playbackItems = getAllPlaybackProgress(auth)
                val processedKeys = candidates.map {
                    val item = it.item
                    "${item.mediaType}:${item.id}:${item.season ?: -1}:${item.episode ?: -1}"
                }.toMutableSet()
                for (item in playbackItems) {
                    if (item.progress < Constants.MIN_PROGRESS_THRESHOLD || item.progress > Constants.WATCHED_THRESHOLD) continue

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
                        continue
                    }

                    if (item.type != "episode") continue
                    val episode = item.episode ?: continue
                    val show = item.show ?: continue
                    val tmdbId = show.ids.tmdb ?: continue
                    val season = episode.season
                    val number = episode.number
                    val key = "${MediaType.TV}:$tmdbId:$season:$number"
                    if (key in processedKeys) continue
                    candidates.removeAll { it.item.mediaType == MediaType.TV && it.item.id == tmdbId }
                    processedKeys.removeAll { it.startsWith("${MediaType.TV}:$tmdbId:") }
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
                }
            } catch (_: Exception) { }

            // 3. Hydrate with TMDB Details (Parallel)
            // Only hydrate the top items we will actually display
            val dismissed = loadDismissedContinueWatching()
            val filteredCandidates = if (dismissed.isNotEmpty()) {
                val updatedDismissed = dismissed.toMutableMap()
                val kept = candidates.filter { candidate ->
                    val key = buildContinueWatchingKey(candidate.item)
                    val dismissedAt = key?.let { dismissed[it] }
                    if (dismissedAt == null) {
                        true
                    } else {
                        val activityAt = parseIso8601(candidate.lastActivityAt)
                        if (activityAt > dismissedAt) {
                            updatedDismissed.remove(key)
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
                                duration = details.runtime?.let { formatRuntime(it) } ?: ""
                            )
                        } else {
                            val details = tmdbApi.getTvDetails(item.id, Constants.TMDB_API_KEY)
                            item.copy(
                                backdropPath = details.backdropPath?.let { "${Constants.BACKDROP_BASE_LARGE}$it" },
                                posterPath = details.posterPath?.let { "${Constants.IMAGE_BASE}$it" },
                                overview = details.overview ?: "",
                                imdbRating = String.format("%.1f", details.voteAverage),
                                duration = details.episodeRunTime?.firstOrNull()?.let { "${it}m" } ?: ""
                            )
                        }
                    } catch (e: Exception) {
                        candidate.item
                    }
                }
            }

            val hydratedItems = hydrationTasks.awaitAll()

        val resolvedItems = if (hydratedItems.isNotEmpty()) {
            cachedContinueWatching = hydratedItems
            lastContinueWatchingFetch = System.currentTimeMillis()
            persistContinueWatchingCache(hydratedItems)
            hydratedItems
        } else {
            val cached = if (cachedContinueWatching.isNotEmpty()) {
                cachedContinueWatching
            } else {
                loadContinueWatchingCache().also { cachedContinueWatching = it }
            }
            cached
        }
        return@coroutineScope resolvedItems
        } finally {
            continueWatchingFetching = false
        }
    }

    fun getCachedContinueWatching(): List<ContinueWatchingItem> = cachedContinueWatching

    // Cache for preloaded profile data (keyed by profileId)
    private val preloadedProfileCache = ConcurrentHashMap<String, List<ContinueWatchingItem>>()

    suspend fun preloadContinueWatchingCache(): List<ContinueWatchingItem> {
        // Return existing cache if available
        if (cachedContinueWatching.isNotEmpty()) return cachedContinueWatching

        // Profiles without Trakt should preload local continue watching cache.
        if (refreshTokenIfNeeded() == null) {
            val local = loadLocalContinueWatching()
            cachedContinueWatching = local
            return cachedContinueWatching
        }

        // Check preloaded cache first (from profile focus in ProfileSelectionScreen)
        val currentProfileId = profileManager.getProfileIdSync()
        val preloaded = preloadedProfileCache[currentProfileId]
        if (!preloaded.isNullOrEmpty()) {
            cachedContinueWatching = preloaded
            return cachedContinueWatching
        }

        // Fall back to loading from DataStore
        val cached = loadContinueWatchingCache()
        cachedContinueWatching = cached
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
            // Directly access the cache with the specific profile's key
            val cacheKey = stringPreferencesKey("profile_${profileId}_trakt_continue_watching_cache_v1")
            val prefs = context.traktDataStore.data.first()
            val json = prefs[cacheKey] ?: return

            val type = com.google.gson.reflect.TypeToken
                .getParameterized(MutableList::class.java, ContinueWatchingItem::class.java)
                .type
            val parsed: List<ContinueWatchingItem> = gson.fromJson(json, type)
            preloadedProfileCache[profileId] = parsed

            // If this profile becomes active, pre-populate the main cache
            if (profileManager.getProfileIdSync() == profileId) {
                cachedContinueWatching = parsed
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
        // CRITICAL: Clear existing cache first to prevent profile data leakage
        cachedContinueWatching = emptyList()
        lastContinueWatchingFetch = 0L

        // Then load this profile's preloaded data if available
        val preloaded = preloadedProfileCache[profileId]
        if (!preloaded.isNullOrEmpty()) {
            cachedContinueWatching = preloaded
        }
    }

    /**
     * Clear continue watching cache - call when switching profiles
     */
    fun clearContinueWatchingCache() {
        cachedContinueWatching = emptyList()
        lastContinueWatchingFetch = 0L
    }

    /**
     * Clear ALL profile-specific caches - MUST be called when switching profiles
     * This ensures complete isolation between profiles and prevents data leakage
     */
    fun clearAllProfileCaches() {
        // Watched status caches (prevents Profile 1's Trakt data showing in Profile 2)
        watchedMoviesCache.clear()
        watchedEpisodesCache.clear()
        cacheInitialized = false
        cacheInitializing = false

        // Per-show watched episode caches
        showWatchedEpisodesCache.clear()
        showWatchedCacheTime = 0L
        showCompletionCache.clear()

        // Trakt ID mapping cache (profile-specific Trakt account has different IDs)
        tmdbToTraktIdCache.clear()

        // Continue watching caches
        cachedContinueWatching = emptyList()
        lastContinueWatchingFetch = 0L
        preloadedProfileCache.clear()

        // Scrobble state (prevent cross-profile scrobble deduplication issues)
        lastScrobbleKey = null
        lastScrobbleTime = 0L

        // Token load flag (force fresh token check for new profile)
        attemptedProfileTokenLoad = false
    }

    /**
     * Remove an episode from Continue Watching cache when marked as watched
     */
    suspend fun removeFromContinueWatchingCache(showTmdbId: Int, seasonNum: Int?, episodeNum: Int?) {
        if (cachedContinueWatching.isEmpty()) return

        cachedContinueWatching = cachedContinueWatching.filter { item ->
            // Keep items that don't match the watched episode
            !(item.id == showTmdbId &&
              (seasonNum == null || item.season == seasonNum) &&
              (episodeNum == null || item.episode == episodeNum))
        }
        // Also update persisted cache
        persistContinueWatchingCache(cachedContinueWatching)
        // Also update local Continue Watching for profiles without Trakt
        removeFromLocalContinueWatching(showTmdbId, seasonNum, episodeNum)
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
        // Don't save if progress is too low or too high
        if (progress < Constants.MIN_PROGRESS_THRESHOLD || progress >= Constants.WATCHED_THRESHOLD) {
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
            year = year
        )

        // Load existing items (raw - no enrichment needed when saving)
        val existingItems = loadLocalContinueWatchingRaw().toMutableList()

        // Remove existing entry for this content (will add updated one)
        existingItems.removeAll { existing ->
            existing.id == tmdbId &&
            existing.mediaType == mediaType &&
            (mediaType == MediaType.MOVIE || (existing.season == season && existing.episode == episode))
        }

        // Add to front (most recent)
        existingItems.add(0, item)

        // Keep only top items
        val trimmed = existingItems.take(Constants.MAX_CONTINUE_WATCHING)

        // Persist
        val json = gson.toJson(trimmed)
        context.traktDataStore.edit { prefs ->
            prefs[localContinueWatchingKey()] = json
        }

        // Also update in-memory cache if this profile is active
        if (cachedContinueWatching.isEmpty() || refreshTokenIfNeeded() == null) {
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

    /**
     * Load local Continue Watching items (profile-scoped) - raw data without enrichment
     */
    private suspend fun loadLocalContinueWatchingRaw(): List<ContinueWatchingItem> {
        val prefs = context.traktDataStore.data.first()
        val json = prefs[localContinueWatchingKey()] ?: return emptyList()
        return try {
            val type = com.google.gson.reflect.TypeToken
                .getParameterized(MutableList::class.java, ContinueWatchingItem::class.java)
                .type
            gson.fromJson(json, type)
        } catch (_: Exception) {
            emptyList()
        }
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
     * Load local Continue Watching items enriched with TMDB data (overview, duration, etc.)
     */
    private suspend fun loadLocalContinueWatching(): List<ContinueWatchingItem> = coroutineScope {
        val rawItems = loadLocalContinueWatchingRaw()
        if (rawItems.isEmpty()) return@coroutineScope emptyList()

        // Enrich items with TMDB data in parallel (limited concurrency)
        val semaphore = kotlinx.coroutines.sync.Semaphore(5)
        rawItems.map { item ->
            async {
                semaphore.withPermit {
                    enrichLocalContinueWatchingItem(item)
                }
            }
        }.awaitAll()
    }

    /**
     * Enrich a local Continue Watching item with TMDB data
     * Matches the Trakt enrichment behavior: uses SHOW backdrop/overview, not episode
     */
    private suspend fun enrichLocalContinueWatchingItem(item: ContinueWatchingItem): ContinueWatchingItem {
        // Skip if already enriched (has overview and backdrop with full URL)
        if (item.overview.isNotEmpty() && item.backdropPath?.startsWith("http") == true) return item

        val apiKey = Constants.TMDB_API_KEY
        return try {
            if (item.mediaType == MediaType.TV) {
                val details = try {
                    tmdbApi.getTvDetails(item.id, apiKey)
                } catch (_: Exception) { null }

                // Get episode info for episode title only (not for backdrop/overview)
                val episodeInfo = if (item.season != null && item.episode != null && item.episodeTitle.isNullOrEmpty()) {
                    try {
                        val seasonDetails = tmdbApi.getTvSeason(item.id, item.season, apiKey)
                        seasonDetails.episodes.find { it.episodeNumber == item.episode }
                    } catch (_: Exception) { null }
                } else null

                // Use SHOW's backdrop and overview (like Trakt does), not episode's
                val backdropUrl = details?.backdropPath?.let { "${Constants.BACKDROP_BASE_LARGE}$it" }
                val posterUrl = details?.posterPath?.let { "${Constants.IMAGE_BASE}$it" }

                item.copy(
                    overview = details?.overview ?: "",  // Show overview, not episode
                    backdropPath = backdropUrl ?: item.backdropPath,  // Show backdrop, not episode still
                    posterPath = posterUrl ?: item.posterPath,
                    year = details?.firstAirDate?.take(4) ?: item.year,
                    imdbRating = details?.voteAverage?.let { String.format("%.1f", it) } ?: "",
                    duration = details?.episodeRunTime?.firstOrNull()?.let { "${it}m" } ?: "",
                    episodeTitle = item.episodeTitle ?: episodeInfo?.name
                )
            } else {
                val details = try {
                    tmdbApi.getMovieDetails(item.id, apiKey)
                } catch (_: Exception) { null }

                // Build full URLs for images
                val backdropUrl = details?.backdropPath?.let { "${Constants.BACKDROP_BASE_LARGE}$it" }
                val posterUrl = details?.posterPath?.let { "${Constants.IMAGE_BASE}$it" }

                item.copy(
                    overview = details?.overview ?: "",
                    backdropPath = backdropUrl ?: item.backdropPath,
                    posterPath = posterUrl ?: item.posterPath,
                    year = details?.releaseDate?.take(4) ?: item.year,
                    imdbRating = details?.voteAverage?.let { String.format("%.1f", it) } ?: "",
                    duration = details?.runtime?.let { formatRuntime(it) } ?: ""
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
        val now = System.currentTimeMillis()
        context.settingsDataStore.edit { prefs ->
            val map = parseDismissedMap(prefs[dismissedContinueWatchingKey()])
            map[key] = now
            prefs[dismissedContinueWatchingKey()] = encodeDismissedMap(map)
        }
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
            val type = com.google.gson.reflect.TypeToken
                .getParameterized(MutableList::class.java, ContinueWatchingItem::class.java)
                .type
            val parsed: List<ContinueWatchingItem> = gson.fromJson(json, type)
            parsed
        } catch (_: Exception) {
            emptyList()
        }
    }

    // ========== Watchlist ==========

    suspend fun getWatchlist(): List<MediaItem> {
        val auth = getAuthHeader() ?: return emptyList()
        val items = mutableListOf<MediaItem>()

        try {
            val watchlist = traktApi.getWatchlist(auth, clientId)

            for (item in watchlist) {
                when (item.type) {
                    "movie" -> {
                        val tmdbId = item.movie?.ids?.tmdb ?: continue
                        try {
                            val details = tmdbApi.getMovieDetails(tmdbId, Constants.TMDB_API_KEY)
                            items.add(
                                MediaItem(
                                    id = tmdbId,
                                    title = details.title,
                                    subtitle = "Movie",
                                    overview = details.overview ?: "",
                                    year = details.releaseDate?.take(4) ?: "",
                                    imdbRating = String.format("%.1f", details.voteAverage),
                                    mediaType = MediaType.MOVIE,
                                    image = details.posterPath?.let { "${Constants.IMAGE_BASE}$it" }
                                        ?: details.backdropPath?.let { "${Constants.BACKDROP_BASE}$it" } ?: "",
                                    backdrop = details.backdropPath?.let { "${Constants.BACKDROP_BASE_LARGE}$it" }
                                )
                            )
                        } catch (e: Exception) { }
                    }
                    "show" -> {
                        val tmdbId = item.show?.ids?.tmdb ?: continue
                        try {
                            val details = tmdbApi.getTvDetails(tmdbId, Constants.TMDB_API_KEY)
                            items.add(
                                MediaItem(
                                    id = tmdbId,
                                    title = details.name,
                                    subtitle = "TV Series",
                                    overview = details.overview ?: "",
                                    year = details.firstAirDate?.take(4) ?: "",
                                    imdbRating = String.format("%.1f", details.voteAverage),
                                    mediaType = MediaType.TV,
                                    image = details.posterPath?.let { "${Constants.IMAGE_BASE}$it" }
                                        ?: details.backdropPath?.let { "${Constants.BACKDROP_BASE}$it" } ?: "",
                                    backdrop = details.backdropPath?.let { "${Constants.BACKDROP_BASE_LARGE}$it" }
                                )
                            )
                        } catch (e: Exception) { }
                    }
                }
            }
        } catch (e: Exception) { }

        return items
    }

    suspend fun addToWatchlist(mediaType: MediaType, tmdbId: Int) {
        val auth = getAuthHeader() ?: return
        try {
            val body = if (mediaType == MediaType.MOVIE) {
                TraktWatchlistBody(movies = listOf(TraktMovieId(TraktIds(tmdb = tmdbId))))
            } else {
                TraktWatchlistBody(shows = listOf(TraktShowId(TraktIds(tmdb = tmdbId))))
            }
            traktApi.addToWatchlist(auth, clientId, "2", body)
        } catch (e: Exception) { }
    }

    suspend fun removeFromWatchlist(mediaType: MediaType, tmdbId: Int) {
        val auth = getAuthHeader() ?: return
        try {
            val body = if (mediaType == MediaType.MOVIE) {
                TraktWatchlistBody(movies = listOf(TraktMovieId(TraktIds(tmdb = tmdbId))))
            } else {
                TraktWatchlistBody(shows = listOf(TraktShowId(TraktIds(tmdb = tmdbId))))
            }
            traktApi.removeFromWatchlist(auth, clientId, "2", body)
        } catch (e: Exception) { }
    }

    suspend fun checkInWatchlist(mediaType: MediaType, tmdbId: Int): Boolean {
        val auth = getAuthHeader() ?: return false
        return try {
            val watchlist = traktApi.getWatchlist(auth, clientId)
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

    // ========== Collection Management (Like NuvioStreaming) ==========

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

    // ========== Ratings (Like NuvioStreaming) ==========

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

    // ========== Comments (Like NuvioStreaming) ==========

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

    // ========== Bulk Watch Operations (Like NuvioStreaming) ==========

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

    // ========== Local Watched Status Cache (Like NuvioStreaming) ==========

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
            // PROFILE ISOLATION: Check if this profile has Trakt auth
            // If not, leave caches empty so all content appears unwatched
            val hasAuth = refreshTokenIfNeeded() != null
            if (!hasAuth) {
                // No Trakt for this profile - caches stay empty
                watchedMoviesCache.clear()
                watchedEpisodesCache.clear()
                cacheInitialized = true
                return
            }

            // Try to load from Supabase first (source of truth)
            val supabaseMovies = syncService.getWatchedMovies()
            val supabaseEpisodes = syncService.getWatchedEpisodes()

            val traktMovies = if (supabaseMovies.isEmpty()) getWatchedMovies() else emptySet()
            val traktEpisodes = if (supabaseEpisodes.isEmpty()) getWatchedEpisodes() else emptySet()

            watchedMoviesCache.clear()
            watchedMoviesCache.addAll(if (supabaseMovies.isNotEmpty()) supabaseMovies else traktMovies)

            watchedEpisodesCache.clear()
            watchedEpisodesCache.addAll(if (supabaseEpisodes.isNotEmpty()) supabaseEpisodes else traktEpisodes)

            cacheInitialized = true
        } catch (e: Exception) {
            // If sync service fails, try direct Trakt load
            try {
                watchedMoviesCache.clear()
                watchedMoviesCache.addAll(getWatchedMovies())
                watchedEpisodesCache.clear()
                watchedEpisodesCache.addAll(getWatchedEpisodes())
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
        return watchedMoviesCache.contains(tmdbId)
    }

    /**
     * Check if episode is watched (uses cache)
     */
    fun isEpisodeWatched(tmdbId: Int, season: Int, episode: Int): Boolean {
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
    fun getWatchedMoviesFromCache(): Set<Int> = watchedMoviesCache.toSet()

    /**
     * Get all watched episode keys from cache
     */
    fun getWatchedEpisodesFromCache(): Set<String> = watchedEpisodesCache

    /**
     * Check if show has any watched episodes - optimized to avoid full iteration
     */
    fun hasWatchedEpisodes(showTmdbId: Int): Boolean {
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
    val budget: Long? = null
) {
    fun toMediaItem(): MediaItem {
        val resumeSeconds = when {
            resumePositionSeconds > 0L -> resumePositionSeconds
            durationSeconds > 0L && progress in 1..99 -> ((durationSeconds * progress) / 100L).coerceAtLeast(1L)
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
            nextEpisode = nextEp
        )
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



