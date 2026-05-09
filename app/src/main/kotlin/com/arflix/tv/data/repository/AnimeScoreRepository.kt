package com.arflix.tv.data.repository

import com.arflix.tv.data.api.ArmApi
import com.arflix.tv.data.api.JikanApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull
import java.util.Collections
import javax.inject.Inject
import javax.inject.Singleton
import java.util.LinkedHashMap

/**
 * Resolves MyAnimeList community scores for anime titles.
 *
 * The flow is:
 *   IMDB id \u2192 ARM API (maps imdb -> mal_id) \u2192 Jikan v4 anime endpoint (returns score).
 *
 * Both hops are cached in-memory (per-process) for the session so repeated
 * navigation to the same anime doesn't re-query either API. Jikan's unofficial
 * rate limit is ~3 req/s, so caching is important if the user is browsing a
 * catalog of many anime.
 *
 * All exceptions (network failures, null values, rate limits, JSON mismatches)
 * are swallowed and return null \u2014 the caller hides the MAL badge on null.
 *
 * Issue #45.
 */
@Singleton
class AnimeScoreRepository @Inject constructor(
    private val armApi: ArmApi,
    private val jikanApi: JikanApi
) {
    // Map<imdbId, malId?> \u2014 nulls cached to avoid re-hitting ARM for negative results.
    private val malIdCache: MutableMap<String, Int?> =
        Collections.synchronizedMap(object : LinkedHashMap<String, Int?>(16, 0.75f, true) {
            override fun removeEldestEntry(eldest: Map.Entry<String, Int?>?): Boolean {
                return size > 256
            }
        })

    // Map<malId, score?> \u2014 same semantics as above.
    private val scoreCache: MutableMap<Int, Double?> =
        Collections.synchronizedMap(object : LinkedHashMap<Int, Double?>(16, 0.75f, true) {
            override fun removeEldestEntry(eldest: Map.Entry<Int, Double?>?): Boolean {
                return size > 256
            }
        })

    /**
     * Look up the MAL community score for an anime by its IMDB id.
     * Returns the raw score (0.0-10.0) or null if not available / not an anime /
     * any network error / API down.
     *
     * Safe to call for non-anime; the ARM lookup will just return null.
     */
    suspend fun getMalScore(imdbId: String?): Double? = withContext(Dispatchers.IO) {
        val trimmed = imdbId?.trim().orEmpty()
        if (trimmed.isEmpty()) return@withContext null

        val malId = resolveMalId(trimmed) ?: return@withContext null
        resolveScore(malId)
    }

    private suspend fun resolveMalId(imdbId: String): Int? {
        // Cache hit (including negative cache)
        val cached = malIdCache[imdbId]
        if (cached != null || malIdCache.containsKey(imdbId)) return cached

        val resolved = withTimeoutOrNull(2_000L) {
            runCatching { armApi.resolve(imdbId).firstOrNull()?.myanimelist }.getOrNull()
        }
        malIdCache[imdbId] = resolved
        return resolved
    }

    private suspend fun resolveScore(malId: Int): Double? {
        val cached = scoreCache[malId]
        if (cached != null || scoreCache.containsKey(malId)) return cached

        val score = withTimeoutOrNull(2_000L) {
            runCatching { jikanApi.getAnime(malId).data?.score }.getOrNull()
        }
        scoreCache[malId] = score
        return score
    }
}
