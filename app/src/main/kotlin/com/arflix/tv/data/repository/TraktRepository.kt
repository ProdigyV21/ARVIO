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
    private val syncServiceProvider: Provider<TraktSyncService>
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

    // User ID key for Supabase sync
    private val USER_ID_KEY = stringPreferencesKey("user_id")
    private val clientId = Constants.TRAKT_CLIENT_ID
    private val clientSecret = Constants.TRAKT_CLIENT_SECRET
    
    // Preference keys
    private val ACCESS_TOKEN_KEY = stringPreferencesKey("access_token")
    private val REFRESH_TOKEN_KEY = stringPreferencesKey("refresh_token")
    private val EXPIRES_AT_KEY = longPreferencesKey("expires_at")
    private val INCLUDE_SPECIALS_KEY = booleanPreferencesKey("include_specials")
    private val DISMISSED_CONTINUE_WATCHING_KEY = stringPreferencesKey("dismissed_continue_watching_v1")
    private val CONTINUE_WATCHING_CACHE_KEY = stringPreferencesKey("continue_watching_cache_v1")
    private var attemptedProfileTokenLoad = false
    @Volatile private var cachedContinueWatching: List<ContinueWatchingItem> = emptyList()

    @Serializable
    private data class TraktTokenUpdate(
        val trakt_token: JsonObject,
        val updated_at: String
    )

    private val Context.authDataStore: DataStore<Preferences> by preferencesDataStore(name = "auth_prefs")
    
    // ========== Authentication ==========
    
    val isAuthenticated: Flow<Boolean> = context.traktDataStore.data.map { prefs ->
        prefs[ACCESS_TOKEN_KEY] != null
    }

    /**
     * Get token expiration timestamp (seconds since epoch)
     */
    suspend fun getTokenExpiration(): Long? {
        val prefs = context.traktDataStore.data.first()
        return prefs[EXPIRES_AT_KEY]
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
        val accessToken = prefs[ACCESS_TOKEN_KEY] ?: return null
        val refreshToken = prefs[REFRESH_TOKEN_KEY]
        val expiresAt = prefs[EXPIRES_AT_KEY]

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
            prefs[ACCESS_TOKEN_KEY] = token.accessToken
            prefs[REFRESH_TOKEN_KEY] = token.refreshToken
            prefs[EXPIRES_AT_KEY] = token.createdAt + token.expiresIn
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

            Log.d(TAG, "Trakt token synced to Supabase")
        } catch (e: Exception) {
            Log.e(TAG, "Failed to sync Trakt token to Supabase", e)
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
                prefs[ACCESS_TOKEN_KEY] = accessToken
                prefs[REFRESH_TOKEN_KEY] = refreshToken
                prefs[EXPIRES_AT_KEY] = createdAt + expiresIn
            }

            Log.d(TAG, "Loaded Trakt tokens from Supabase profile")
        } catch (e: Exception) {
            Log.e(TAG, "Failed to load Trakt tokens from profile", e)
        }
    }
    
    suspend fun logout() {
        context.traktDataStore.edit { prefs ->
            prefs.remove(ACCESS_TOKEN_KEY)
            prefs.remove(REFRESH_TOKEN_KEY)
            prefs.remove(EXPIRES_AT_KEY)
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
                                    prefs[ACCESS_TOKEN_KEY] = accessToken
                                }
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "Failed to load Trakt tokens from profile", e)
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
     * Mark movie as watched - writes to Supabase first (source of truth), then syncs to Trakt
     */
    suspend fun markMovieWatched(tmdbId: Int) {
        try {
            // Use sync service which writes to Supabase first, then Trakt
            val success = syncService.markMovieWatched(tmdbId)
            if (success) {
                // Update local cache for immediate UI update
                updateWatchedCache(tmdbId, null, null, true)
                Log.d(TAG, "markMovieWatched: $tmdbId (via SyncService)")
            }
        } catch (e: Exception) {
            Log.e(TAG, "markMovieWatched failed for $tmdbId", e)
        }
    }

    /**
     * Mark movie as unwatched - removes from Supabase first (source of truth), then syncs to Trakt
     */
    suspend fun markMovieUnwatched(tmdbId: Int) {
        try {
            // Use sync service which removes from Supabase first, then Trakt
            val success = syncService.markMovieUnwatched(tmdbId)
            if (success) {
                // Update local cache for immediate UI update
                updateWatchedCache(tmdbId, null, null, false)
                Log.d(TAG, "markMovieUnwatched: $tmdbId (via SyncService)")
            }
        } catch (e: Exception) {
            Log.e(TAG, "markMovieUnwatched failed for $tmdbId", e)
        }
    }
    
    /**
     * Mark episode as watched - writes to Supabase first (source of truth), then syncs to Trakt
     */
    suspend fun markEpisodeWatched(showTmdbId: Int, season: Int, episode: Int) {
        try {
            // Get Trakt show ID if available
            val traktShowId = tmdbToTraktIdCache[showTmdbId]

            // Use sync service which writes to Supabase first, then Trakt
            val success = syncService.markEpisodeWatched(showTmdbId, season, episode, traktShowId)
            if (success) {
                // Update caches so the UI updates immediately
                clearShowWatchedCache()
                updateWatchedCache(showTmdbId, season, episode, true)
                Log.d(TAG, "markEpisodeWatched: $showTmdbId S${season}E${episode} (via SyncService)")
            }
        } catch (e: Exception) {
            Log.e(TAG, "markEpisodeWatched failed for $showTmdbId S${season}E${episode}", e)
        }
    }

    /**
     * Mark episode as unwatched - removes from Supabase first (source of truth), then syncs to Trakt
     */
    suspend fun markEpisodeUnwatched(showTmdbId: Int, season: Int, episode: Int) {
        try {
            // Use sync service which removes from Supabase first, then Trakt
            val success = syncService.markEpisodeUnwatched(showTmdbId, season, episode)
            if (success) {
                // Update caches so the UI updates immediately
                clearShowWatchedCache()
                updateWatchedCache(showTmdbId, season, episode, false)
                Log.d(TAG, "markEpisodeUnwatched: $showTmdbId S${season}E${episode} (via SyncService)")
            }
        } catch (e: Exception) {
            Log.e(TAG, "markEpisodeUnwatched failed for $showTmdbId S${season}E${episode}", e)
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
                    Log.e(TAG, "$operation failed with HTTP $code", e)
                    return null
                }
                Log.w(TAG, "$operation retrying after HTTP $code (attempt $attempt/$maxAttempts)")
                delay(delayMs)
                delayMs = (delayMs * 2).coerceAtMost(10000)
                attempt++
            } catch (e: Exception) {
                if (attempt == maxAttempts) {
                    Log.e(TAG, "$operation failed after $attempt attempts", e)
                    return null
                }
                Log.w(TAG, "$operation retrying after error (attempt $attempt/$maxAttempts)", e)
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
            Log.d(TAG, "Scrobble pause debounced for $key")
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
            Log.d(TAG, "Auto-marking as watched (progress: $progress%)")
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
            Log.e(TAG, "Delete playback item failed", e)
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
            Log.e(TAG, "Delete playback for content failed", e)
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
                Log.d(TAG, "getWatchedEpisodesForShow: Returning ${cachedSet.size} cached episodes for show $tmdbId")
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
                Log.d(TAG, "getWatchedEpisodesForShow: TMDB $tmdbId not in watched shows, trying search API")
                traktId = getTraktIdForTmdb(tmdbId, "show")
                if (traktId != null) {
                    tmdbToTraktIdCache[tmdbId] = traktId
                }
            }

            if (traktId == null) {
                Log.w(TAG, "getWatchedEpisodesForShow: Could not find Trakt ID for TMDB $tmdbId")
                // Cache empty result to avoid repeated lookups
                showWatchedEpisodesCache[tmdbId] = emptySet()
                return emptySet()
            }

            // Get show progress which includes per-episode completion status
            val progress = traktApi.getShowProgress(auth, clientId, "2", traktId.toString())

            Log.d(TAG, "getWatchedEpisodesForShow: Progress for $tmdbId (trakt=$traktId): completed=${progress.completed}/${progress.aired}, seasons=${progress.seasons?.size ?: 0}")

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

            Log.d(TAG, "getWatchedEpisodesForShow: Found ${watchedSet.size} watched episodes for show $tmdbId (traktId=$traktId)")
        } catch (e: Exception) {
            Log.e(TAG, "getWatchedEpisodesForShow failed for $tmdbId: ${e.message}", e)
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
        Log.d(TAG, "Cleared show watched episodes cache")
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

            val includeSpecials = context.settingsDataStore.data.first()[INCLUDE_SPECIALS_KEY] ?: false
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
            Log.w(TAG, "isShowFullyWatched failed for $tmdbId: ${e.message}")
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
            val accessToken = prefs[ACCESS_TOKEN_KEY] ?: return
            val refreshToken = prefs[REFRESH_TOKEN_KEY]
            val expiresAt = prefs[EXPIRES_AT_KEY]
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

            Log.d(TAG, "Synced local Trakt tokens to Supabase")
        } catch (e: Exception) {
            Log.e(TAG, "Failed to sync local Trakt tokens to Supabase", e)
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
            Log.e(TAG, "Delete playback for episode failed", e)
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
            Log.d(TAG, "populateTmdbToTraktCache: Cached ${tmdbToTraktIdCache.size} show IDs")
        } catch (e: Exception) {
            Log.e(TAG, "populateTmdbToTraktCache failed", e)
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
            Log.d(TAG, "getTraktIdForTmdb: TMDB $tmdbId -> Trakt $traktId")
            traktId
        } catch (e: Exception) {
            Log.e(TAG, "getTraktIdForTmdb failed for $tmdbId", e)
            null
        }
    }

    private suspend fun getAllPlaybackProgress(auth: String): List<TraktPlaybackItem> {
        val all = mutableListOf<TraktPlaybackItem>()
        var page = 1
        val limit = 100

        while (true) {
            val pageItems = traktApi.getPlaybackProgress(auth, clientId, "2", null, page, limit)
            Log.d(TAG, "Playback page $page count=${pageItems.size}")
            if (pageItems.isEmpty()) break
            all.addAll(pageItems)
            page++
        }

        Log.d(TAG, "Playback total=${all.size}")
        return all
    }

    /**
     * Get items to continue watching - Uses Trakt API directly for accuracy and speed.
     * Refactored to fetch more shows and process in parallel.
     */
    suspend fun getContinueWatching(): List<ContinueWatchingItem> = coroutineScope {
        val auth = getAuthHeader() ?: return@coroutineScope emptyList()
        val candidates = mutableListOf<ContinueWatchingCandidate>()
        val processedIds = mutableSetOf<Int>() // TMDB IDs
        val includeSpecials = context.settingsDataStore.data.first()[INCLUDE_SPECIALS_KEY] ?: false
        val showProgressCache = mutableMapOf<Int, TraktShowProgress>()

        initializeWatchedCache()
        Log.d(TAG, "Continue Watching: Fetching direct from Trakt (Parallel)...")

            // 1. In-Progress (Paused) items
            val playbackDeferred = async {
                try {
                    val playbackItems = getAllPlaybackProgress(auth)
                    Log.d(TAG, "Continue Watching: Found ${playbackItems.size} playback items")
                    playbackItems
                } catch (e: Exception) {
                    Log.e(TAG, "Failed to fetch playback progress", e)
                    emptyList()
                }
            }

            // 2. Up Next (from Watched Shows summary)
            val watchedShowsDeferred = async {
                try {
                    // Increased limit to 200 to catch shows not watched recently
                    traktApi.getWatchedShows(auth, clientId)
                        .sortedByDescending { it.lastWatchedAt }
                        .take(200) 
                } catch (e: Exception) {
                    Log.e(TAG, "Failed to fetch watched shows", e)
                    emptyList()
                }
            }

            // Process Playback Items (paused mid-episode/movie)
            // NO time filter - include all paused items regardless of when they were paused
            val playbackItems = playbackDeferred.await()
                .sortedByDescending { parseIso8601(it.pausedAt ?: "") }
                .take(Constants.MAX_PROGRESS_ENTRIES)

            for (item in playbackItems) {
                    val tmdbId = item.movie?.ids?.tmdb ?: item.show?.ids?.tmdb ?: continue
                    if (tmdbId in processedIds) continue

                // Skip if progress is above watched threshold
                if (item.progress > Constants.WATCHED_THRESHOLD) {
                    Log.d(TAG, "Continue Watching: Skipping playback item - progress ${item.progress}% > threshold")
                    continue
                }

                if (item.type == "movie") {
                    val movie = item.movie ?: continue
                    // Skip movies already marked as watched
                    if (isMovieWatched(tmdbId)) {
                        Log.d(TAG, "Continue Watching: Skipping ${movie.title} - already watched")
                        continue
                    }
                    Log.d(TAG, "Continue Watching: Adding playback movie ${movie.title} (${item.progress.toInt()}%)")
                    candidates.add(
                        ContinueWatchingCandidate(
                            item = ContinueWatchingItem(
                                id = tmdbId,
                                title = movie.title,
                                mediaType = MediaType.MOVIE,
                                progress = item.progress.toInt().coerceIn(0, 100),
                                year = movie.year?.toString() ?: ""
                            ),
                            lastActivityAt = item.pausedAt ?: ""
                        )
                    )
                    processedIds.add(tmdbId)
                } else if (item.type == "episode") {
                    val show = item.show ?: continue
                    val episode = item.episode ?: continue
                    val showTmdbId = show.ids.tmdb ?: continue
                    val traktId = show.ids.trakt

                    if (isEpisodeWatched(showTmdbId, episode.season, episode.number)) {
                        Log.d(TAG, "Continue Watching: Skipping playback ${show.title} S${episode.season}E${episode.number} - already watched")
                        try {
                            traktApi.removePlaybackItem(auth, clientId, "2", item.id)
                            Log.d(TAG, "Continue Watching: Deleted watched playback item ${item.id} for ${show.title}")
                        } catch (e: Exception) {
                            Log.w(TAG, "Continue Watching: Failed to delete watched playback item", e)
                        }
                        continue
                    }

                    // Check if the show is fully watched before adding
                    if (traktId != null) {
                        try {
                            val showProgress = showProgressCache.getOrPut(traktId) {
                                traktApi.getShowProgress(
                                    auth, clientId, "2", traktId.toString(),
                                    specials = includeSpecials.toString(),
                                    countSpecials = includeSpecials.toString()
                                )
                            }
                            val episodeCompleted = showProgress.seasons
                                ?.firstOrNull { it.number == episode.season }
                                ?.episodes
                                ?.firstOrNull { it.number == episode.number }
                                ?.completed == true

                            if (episodeCompleted) {
                                Log.d(TAG, "Continue Watching: Skipping playback ${show.title} S${episode.season}E${episode.number} - completed in Trakt")
                                try {
                                    traktApi.removePlaybackItem(auth, clientId, "2", item.id)
                                    Log.d(TAG, "Continue Watching: Deleted completed playback item ${item.id} for ${show.title}")
                                } catch (e: Exception) {
                                    Log.w(TAG, "Continue Watching: Failed to delete completed playback item", e)
                                }
                                continue
                            }
                            // Skip if show is fully watched (no unwatched aired episodes)
                            if (showProgress.completed >= showProgress.aired) {
                                Log.d(TAG, "Continue Watching: Skipping playback ${show.title} - fully watched (${showProgress.completed}/${showProgress.aired})")
                                // Delete the stale playback item from Trakt
                                try {
                                    traktApi.removePlaybackItem(auth, clientId, "2", item.id)
                                    Log.d(TAG, "Continue Watching: Deleted stale playback item ${item.id} for ${show.title}")
                                } catch (e: Exception) {
                                    Log.w(TAG, "Continue Watching: Failed to delete stale playback item", e)
                                }
                                continue
                            }
                        } catch (e: Exception) {
                            Log.w(TAG, "Continue Watching: Failed to check progress for ${show.title}", e)
                        }
                    }

                    Log.d(TAG, "Continue Watching: Adding playback episode ${show.title} S${episode.season}E${episode.number} (${item.progress.toInt()}%)")
                    candidates.add(
                        ContinueWatchingCandidate(
                            item = ContinueWatchingItem(
                                id = tmdbId,
                                title = show.title,
                                mediaType = MediaType.TV,
                                progress = item.progress.toInt().coerceIn(0, 100),
                                season = episode.season,
                                episode = episode.number,
                                episodeTitle = episode.title,
                                year = show.year?.toString() ?: ""
                            ),
                            lastActivityAt = item.pausedAt ?: ""
                        )
                    )
                    processedIds.add(tmdbId)
                }
            }

            // Process Watched Shows in Parallel
            val watchedShows = watchedShowsDeferred.await()
            Log.d(TAG, "Continue Watching: Checking ${watchedShows.size} recently watched shows for Up Next")

            // Semaphore to limit concurrent Trakt API calls (Rate limiting protection)
            val semaphore = Semaphore(10)

            val showTasks = watchedShows.map { show ->
                async {
                    semaphore.withPermit {
                        val tmdbId = show.show.ids.tmdb ?: return@withPermit null
                        if (tmdbId in processedIds) return@withPermit null
                        val traktId = show.show.ids.trakt ?: return@withPermit null

                        try {
                            val progress = traktApi.getShowProgress(
                                auth, clientId, "2", traktId.toString(),
                                specials = includeSpecials.toString(),
                                countSpecials = includeSpecials.toString()
                            )

                            // Only include if show is actually incomplete (has unwatched aired episodes)
                            // AND has a next episode to watch
                            val nextEp = progress.nextEpisode
                            val isIncomplete = progress.completed < progress.aired

                            // Filter out shows with minimal progress (likely accidental scrobbles)
                            // Require at least 1 episode watched to appear in Continue Watching
                            val hasMinimumProgress = progress.completed >= 1

                            if (nextEp != null && isIncomplete) {
                                if (!hasMinimumProgress) {
                                    Log.d(TAG, "Continue Watching: Skipping ${show.show.title} - minimal progress (${progress.completed}/${progress.aired}, need 1+ episodes)")
                                    null
                                } else {
                                    Log.d(TAG, "Continue Watching: Adding ${show.show.title} - S${nextEp.season}E${nextEp.number} (${progress.completed}/${progress.aired} watched)")
                                    ContinueWatchingCandidate(
                                        item = ContinueWatchingItem(
                                            id = tmdbId,
                                            title = show.show.title,
                                            mediaType = MediaType.TV,
                                            progress = 0,
                                            season = nextEp.season,
                                            episode = nextEp.number,
                                            episodeTitle = nextEp.title,
                                            year = show.show.year?.toString() ?: ""
                                        ),
                                        lastActivityAt = show.lastWatchedAt ?: ""
                                    )
                                }
                            } else {
                                if (nextEp != null && !isIncomplete) {
                                    Log.d(TAG, "Continue Watching: Skipping ${show.show.title} - fully watched (${progress.completed}/${progress.aired})")
                                }
                                null
                            }
                        } catch (e: Exception) {
                            Log.w(TAG, "Failed to get progress for show $tmdbId: ${e.message}")
                            null
                        }
                    }
                }
            }

            val nextEpisodes = showTasks.awaitAll().filterNotNull()
            
            // Add unique items
            for (candidate in nextEpisodes) {
                if (candidate.item.id !in processedIds) {
                    candidates.add(candidate)
                    processedIds.add(candidate.item.id)
                }
            }

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
        Log.d(TAG, "Continue Watching: Returning ${resolvedItems.size} items")
        return@coroutineScope resolvedItems
    }

    fun getCachedContinueWatching(): List<ContinueWatchingItem> = cachedContinueWatching

    suspend fun preloadContinueWatchingCache(): List<ContinueWatchingItem> {
        if (cachedContinueWatching.isNotEmpty()) return cachedContinueWatching
        val cached = loadContinueWatchingCache()
        cachedContinueWatching = cached
        return cachedContinueWatching
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
        val raw = context.settingsDataStore.data.first()[DISMISSED_CONTINUE_WATCHING_KEY]
        return parseDismissedMap(raw)
    }

    private suspend fun persistDismissedContinueWatching(map: Map<String, Long>) {
        context.settingsDataStore.edit { prefs ->
            if (map.isEmpty()) {
                prefs.remove(DISMISSED_CONTINUE_WATCHING_KEY)
            } else {
                prefs[DISMISSED_CONTINUE_WATCHING_KEY] = encodeDismissedMap(map)
            }
        }
    }

    suspend fun dismissContinueWatching(item: MediaItem) {
        val key = buildContinueWatchingKey(item) ?: return
        val now = System.currentTimeMillis()
        context.settingsDataStore.edit { prefs ->
            val map = parseDismissedMap(prefs[DISMISSED_CONTINUE_WATCHING_KEY])
            map[key] = now
            prefs[DISMISSED_CONTINUE_WATCHING_KEY] = encodeDismissedMap(map)
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
            prefs[CONTINUE_WATCHING_CACHE_KEY] = json
        }
    }

    private suspend fun loadContinueWatchingCache(): List<ContinueWatchingItem> {
        val prefs = context.traktDataStore.data.first()
        val json = prefs[CONTINUE_WATCHING_CACHE_KEY] ?: return emptyList()
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
                                    image = details.backdropPath?.let { "${Constants.BACKDROP_BASE}$it" }
                                        ?: details.posterPath?.let { "${Constants.IMAGE_BASE}$it" } ?: "",
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
                                    image = details.backdropPath?.let { "${Constants.BACKDROP_BASE}$it" }
                                        ?: details.posterPath?.let { "${Constants.IMAGE_BASE}$it" } ?: "",
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
            Log.e(TAG, "Get collection movies failed", e)
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
            Log.e(TAG, "Get collection shows failed", e)
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
            Log.e(TAG, "Add movie to collection failed", e)
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
            Log.e(TAG, "Add show to collection failed", e)
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
            Log.e(TAG, "Remove movie from collection failed", e)
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
            Log.e(TAG, "Remove show from collection failed", e)
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
            Log.e(TAG, "Get movie ratings failed", e)
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
            Log.e(TAG, "Get show ratings failed", e)
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
            Log.e(TAG, "Get episode ratings failed", e)
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
            Log.e(TAG, "Rate movie failed", e)
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
            Log.e(TAG, "Rate show failed", e)
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
            Log.e(TAG, "Rate episode failed", e)
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
            Log.e(TAG, "Remove movie rating failed", e)
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
            Log.e(TAG, "Get movie comments failed", e)
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
            Log.e(TAG, "Get show comments failed", e)
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
            Log.e(TAG, "Get season comments failed", e)
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
            Log.e(TAG, "Get episode comments failed", e)
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
            Log.e(TAG, "Mark season watched failed", e)
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
                TraktHistoryBody(shows = listOf(TraktShowId(TraktIds(tmdb = tmdbId))))
            )
            true
        } catch (e: Exception) {
            Log.e(TAG, "Mark show watched failed", e)
            false
        }
    }

    suspend fun markShowUnwatched(tmdbId: Int): Boolean {
        val auth = getAuthHeader() ?: return false
        return try {
            traktApi.removeFromHistory(
                auth, clientId, "2",
                TraktHistoryBody(shows = listOf(TraktShowId(TraktIds(tmdb = tmdbId))))
            )
            true
        } catch (e: Exception) {
            Log.e(TAG, "Mark show unwatched failed", e)
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
            Log.e(TAG, "Mark episodes watched failed", e)
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
            Log.e(TAG, "Remove season from history failed", e)
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
                TraktHistoryBody(shows = listOf(TraktShowId(TraktIds(tmdb = tmdbId))))
            )
            true
        } catch (e: Exception) {
            Log.e(TAG, "Remove show from history failed", e)
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
            Log.e(TAG, "Remove from history by IDs failed", e)
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
            Log.e(TAG, "Get history movies failed", e)
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
            Log.e(TAG, "Get history episodes failed", e)
            emptyList()
        }
    }

    // ========== Local Watched Status Cache (Like NuvioStreaming) ==========

    // In-memory cache for watched status (mirrors Supabase data)
    private val watchedMoviesCache = mutableSetOf<Int>()
    private val watchedEpisodesCache = mutableSetOf<String>()
    private var cacheInitialized = false

    /**
     * Invalidate watched cache - forces reload on next access
     * Call this after sync operations to pick up new data
     */
    fun invalidateWatchedCache() {
        cacheInitialized = false
        watchedMoviesCache.clear()
        watchedEpisodesCache.clear()
        Log.d(TAG, "Watched cache invalidated")
    }

    /**
     * Initialize watched cache from Supabase (source of truth)
     * Falls back to Trakt if Supabase data is not available
     */
    suspend fun initializeWatchedCache() {
        if (cacheInitialized) return
        try {
            // Try to load from Supabase first (source of truth)
            val supabaseMovies = syncService.getWatchedMovies()
            val supabaseEpisodes = syncService.getWatchedEpisodes()

            val traktMovies = if (supabaseMovies.isEmpty()) getWatchedMovies() else emptySet()
            val traktEpisodes = if (supabaseEpisodes.isEmpty()) getWatchedEpisodes() else emptySet()

            watchedMoviesCache.clear()
            watchedMoviesCache.addAll(if (supabaseMovies.isNotEmpty()) supabaseMovies else traktMovies)

            watchedEpisodesCache.clear()
            watchedEpisodesCache.addAll(if (supabaseEpisodes.isNotEmpty()) supabaseEpisodes else traktEpisodes)

            if (supabaseMovies.isNotEmpty() || supabaseEpisodes.isNotEmpty()) {
                Log.d(
                    TAG,
                    "Watched cache initialized (Supabase preferred): ${watchedMoviesCache.size} movies, ${watchedEpisodesCache.size} episodes"
                )
            } else {
                Log.d(
                    TAG,
                    "Watched cache initialized from Trakt (fallback): ${watchedMoviesCache.size} movies, ${watchedEpisodesCache.size} episodes"
                )
            }

            cacheInitialized = true
        } catch (e: Exception) {
            Log.e(TAG, "Failed to initialize watched cache", e)
            // If sync service fails, try direct Trakt load
            try {
                watchedMoviesCache.clear()
                watchedMoviesCache.addAll(getWatchedMovies())
                watchedEpisodesCache.clear()
                watchedEpisodesCache.addAll(getWatchedEpisodes())
                cacheInitialized = true
                Log.d(TAG, "Watched cache initialized from Trakt after error: ${watchedMoviesCache.size} movies, ${watchedEpisodesCache.size} episodes")
            } catch (e2: Exception) {
                Log.e(TAG, "Failed to initialize from Trakt too", e2)
            }
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
                Log.d(TAG, "updateWatchedCache: Added movie $tmdbId to cache (total: ${watchedMoviesCache.size})")
            } else {
                watchedMoviesCache.remove(tmdbId)
                Log.d(TAG, "updateWatchedCache: Removed movie $tmdbId from cache")
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
                Log.d(TAG, "updateWatchedCache: Added episode $key to cache (total: ${watchedEpisodesCache.size})")
            } else {
                watchedEpisodesCache.remove(key)
                Log.d(TAG, "updateWatchedCache: Removed episode $key from cache")
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
    fun getWatchedEpisodesFromCache(): Set<String> {
        Log.d(TAG, "getWatchedEpisodesFromCache: Returning ${watchedEpisodesCache.size} episodes, cacheInitialized=$cacheInitialized")
        return watchedEpisodesCache.toSet()
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

            Log.d(TAG, "Synced watched history: ${watchedMoviesCache.size} movies, ${watchedEpisodesCache.size} episodes")
        } catch (e: Exception) {
            Log.e(TAG, "Failed to sync watched history", e)
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
    val season: Int? = null,
    val episode: Int? = null,
    val episodeTitle: String? = null,
    val backdropPath: String? = null,
    val posterPath: String? = null,
    val year: String = "",
    val releaseDate: String = "",  // Full formatted date
    val isUpNext: Boolean = false,
    val overview: String = "",
    val imdbRating: String = "",
    val duration: String = "",
    val budget: Long? = null
) {
    fun toMediaItem(): MediaItem {
        val subtitle = if (mediaType == MediaType.TV && season != null && episode != null) {
            "S${season}:E${episode}" + (episodeTitle?.let { " • $it" } ?: "")
        } else {
            if (mediaType == MediaType.MOVIE) "Movie" else "TV Series"
        }

        // Create NextEpisode for TV shows with season/episode info
        val nextEp = if (mediaType == MediaType.TV && season != null && episode != null) {
            NextEpisode(
                id = 0, // Not needed for navigation
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
            image = backdropPath ?: posterPath ?: "",
            backdrop = backdropPath,
            badge = null, // No badge needed
            budget = budget,
            nextEpisode = nextEp
        )
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


