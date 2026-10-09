package com.arflix.tv.data.repository

import com.arflix.tv.data.api.StreamApi
import com.arflix.tv.data.model.CommunityAddon
import com.arflix.tv.data.model.CommunityAddons
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

/** The Stremio community addon catalog, kept in memory for [CACHE_TTL_MS]. */
@Singleton
class CommunityAddonRepository @Inject constructor(
    private val streamApi: StreamApi
) {
    private val mutex = Mutex()
    private var cached: List<CommunityAddon>? = null
    private var cachedAtMs = 0L

    suspend fun getCommunityAddons(forceRefresh: Boolean = false): Result<List<CommunityAddon>> =
        withContext(Dispatchers.IO) {
            mutex.withLock {
                val now = System.currentTimeMillis()
                val fresh = cached?.takeIf { !forceRefresh && now - cachedAtMs < CACHE_TTL_MS }
                if (fresh != null) return@withContext Result.success(fresh)
                try {
                    val addons = CommunityAddons.fromResponse(
                        streamApi.getAddonCollection(CommunityAddons.CATALOG_URL)
                    )
                    cached = addons
                    cachedAtMs = now
                    Result.success(addons)
                } catch (e: Exception) {
                    if (e is kotlinx.coroutines.CancellationException) throw e
                    Result.failure(e)
                }
            }
        }

    private companion object {
        const val CACHE_TTL_MS = 6 * 60 * 60 * 1000L
    }
}
