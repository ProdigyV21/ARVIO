package com.arflix.tv.data.repository

import android.util.Log
import com.arflix.tv.data.api.SupabaseApi
import com.arflix.tv.data.api.WatchlistRecord
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WatchlistRepository @Inject constructor(
    private val authRepository: AuthRepository,
    private val supabaseApi: SupabaseApi,
    private val mediaRepository: MediaRepository
) {
    private val TAG = "WatchlistRepository"
    private val cache = mutableSetOf<String>()
    private var cacheLoaded = false
    private var cacheUserId: String? = null
    private val cacheMutex = Mutex()

    private fun cacheKey(mediaType: MediaType, tmdbId: Int): String {
        return "${mediaType.name.lowercase()}:$tmdbId"
    }

    suspend fun refreshCache(force: Boolean = false) {
        cacheMutex.withLock {
            val userId = authRepository.getCurrentUserId() ?: return
            if (cacheLoaded && !force && cacheUserId == userId) return
            try {
                val records = executeSupabaseCall("get watchlist") { auth ->
                    supabaseApi.getWatchlist(
                        auth = auth,
                        userId = "eq.$userId",
                        order = "added_at.desc"
                    )
                }
                cache.clear()
                records.forEach { record ->
                    val type = if (record.mediaType == "tv") MediaType.TV else MediaType.MOVIE
                    cache.add(cacheKey(type, record.tmdbId))
                }
                cacheLoaded = true
                cacheUserId = userId
            } catch (e: Exception) {
                Log.e(TAG, "Failed to refresh watchlist cache", e)
            }
        }
    }

    suspend fun isInWatchlist(mediaType: MediaType, tmdbId: Int): Boolean {
        refreshCache()
        return cache.contains(cacheKey(mediaType, tmdbId))
    }

    suspend fun addToWatchlist(mediaType: MediaType, tmdbId: Int) {
        val userId = authRepository.getCurrentUserId() ?: return
        val record = WatchlistRecord(
            userId = userId,
            tmdbId = tmdbId,
            mediaType = if (mediaType == MediaType.TV) "tv" else "movie"
        )
        executeSupabaseCall("add to watchlist") { auth ->
            supabaseApi.upsertWatchlist(auth = auth, record = record)
        }
        cacheMutex.withLock {
            cache.add(cacheKey(mediaType, tmdbId))
            cacheLoaded = true
            cacheUserId = userId
        }
    }

    suspend fun removeFromWatchlist(mediaType: MediaType, tmdbId: Int) {
        val userId = authRepository.getCurrentUserId() ?: return
        executeSupabaseCall("remove from watchlist") { auth ->
            supabaseApi.deleteWatchlist(
                auth = auth,
                userId = "eq.$userId",
                tmdbId = "eq.$tmdbId",
                mediaType = "eq.${if (mediaType == MediaType.TV) "tv" else "movie"}"
            )
        }
        cacheMutex.withLock {
            cache.remove(cacheKey(mediaType, tmdbId))
            cacheLoaded = true
            cacheUserId = userId
        }
    }

    suspend fun getWatchlistItems(): List<MediaItem> {
        val userId = authRepository.getCurrentUserId() ?: return emptyList()
        return try {
            val records = executeSupabaseCall("get watchlist items") { auth ->
                supabaseApi.getWatchlist(
                    auth = auth,
                    userId = "eq.$userId",
                    order = "added_at.desc"
                )
            }

            coroutineScope {
                val tasks = records.map { record ->
                    async {
                        try {
                            if (record.mediaType == "tv") {
                                mediaRepository.getTvDetails(record.tmdbId)
                            } else {
                                mediaRepository.getMovieDetails(record.tmdbId)
                            }
                        } catch (e: Exception) {
                            null
                        }
                    }
                }
                tasks.awaitAll().filterNotNull()
            }
        } catch (e: Exception) {
            Log.e(TAG, "Failed to load watchlist items", e)
            emptyList()
        }
    }

    private suspend fun <T> executeSupabaseCall(
        operation: String,
        block: suspend (String) -> T
    ): T {
        val auth = getSupabaseAuth() ?: throw IllegalStateException("Supabase auth failed")
        return try {
            block(auth)
        } catch (e: HttpException) {
            if (e.code() == 401) {
                Log.w(TAG, "$operation unauthorized, refreshing Supabase session and retrying")
                val refreshed = authRepository.refreshAccessToken()
                if (!refreshed.isNullOrBlank()) {
                    return block("Bearer $refreshed")
                }
            }
            throw e
        }
    }

    private suspend fun getSupabaseAuth(): String? {
        val token = authRepository.getAccessToken()
        if (!token.isNullOrBlank()) return "Bearer $token"
        val refreshed = authRepository.refreshAccessToken()
        return refreshed?.let { "Bearer $it" }
    }
}
