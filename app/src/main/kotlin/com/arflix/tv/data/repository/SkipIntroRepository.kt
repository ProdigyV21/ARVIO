package com.arflix.tv.data.repository

import android.util.Log
import androidx.annotation.Keep
import com.arflix.tv.data.api.AniSkipApi
import com.arflix.tv.data.api.ArmApi
import com.arflix.tv.data.api.IntroDbApi
import retrofit2.HttpException
import java.util.concurrent.ConcurrentHashMap
import javax.inject.Inject
import javax.inject.Singleton

@Keep
data class SkipInterval(
    val startMs: Long,
    val endMs: Long,
    val type: String,      // "intro", "recap", "outro", "op", "ed", "mixed-op", "mixed-ed", ...
    val provider: String   // "introdb" or "aniskip"
)

@Singleton
class SkipIntroRepository @Inject constructor(
    private val introDbApi: IntroDbApi,
    private val aniSkipApi: AniSkipApi,
    private val armApi: ArmApi
) {
    private val cache = ConcurrentHashMap<String, List<SkipInterval>>()
    private val malIdCache = ConcurrentHashMap<String, String>()

    suspend fun getSkipIntervals(imdbId: String?, season: Int, episode: Int): List<SkipInterval> {
        if (imdbId.isNullOrBlank()) return emptyList()

        val cacheKey = "$imdbId:$season:$episode"
        cache[cacheKey]?.let { return it }

        // 1) IntroDB (shows)
        val introDb = fetchFromIntroDb(imdbId, season, episode)
        if (introDb.isNotEmpty()) {
            cache[cacheKey] = introDb
            return introDb
        }

        // 2) AniSkip (anime) via ARM (IMDB -> MAL)
        val malId = resolveMalId(imdbId)
        if (malId != null) {
            val aniSkip = fetchFromAniSkip(malId, episode)
            if (aniSkip.isNotEmpty()) {
                cache[cacheKey] = aniSkip
                return aniSkip
            }
        }

        cache[cacheKey] = emptyList()
        return emptyList()
    }

    private suspend fun fetchFromIntroDb(imdbId: String, season: Int, episode: Int): List<SkipInterval> {
        return try {
            val body = introDbApi.getSegments(imdbId, season, episode)

            val out = mutableListOf<SkipInterval>()

            fun addIfValid(type: String, startMsRaw: Long, endMsRaw: Long, startSec: Double?, endSec: Double?) {
                val startMs = if (startMsRaw > 0L) startMsRaw else ((startSec ?: 0.0) * 1000.0).toLong()
                val endMs = if (endMsRaw > 0L) endMsRaw else ((endSec ?: 0.0) * 1000.0).toLong()
                if (endMs > startMs && startMs >= 0 && endMs >= 0) {
                    out += SkipInterval(
                        startMs = startMs,
                        endMs = endMs,
                        type = type,
                        provider = "introdb"
                    )
                }
            }

            body.recap?.let { addIfValid("recap", it.startMs, it.endMs, it.startSec, it.endSec) }
            body.intro?.let { addIfValid("intro", it.startMs, it.endMs, it.startSec, it.endSec) }
            body.outro?.let { addIfValid("outro", it.startMs, it.endMs, it.startSec, it.endSec) }

            out.sortedBy { it.startMs }
        } catch (e: HttpException) {
            emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    private suspend fun fetchFromAniSkip(malId: String, episode: Int): List<SkipInterval> {
        return try {
            val types = listOf("op", "ed", "recap", "mixed-op", "mixed-ed")
            val body = aniSkipApi.getSkipTimes(malId, episode, types)
            if (!body.found) return emptyList()

            body.results
                .orEmpty()
                .mapNotNull { r ->
                    val startMs = (r.interval.startTime * 1000.0).toLong()
                    val endMs = (r.interval.endTime * 1000.0).toLong()
                    if (endMs > startMs) {
                        SkipInterval(
                            startMs = startMs,
                            endMs = endMs,
                            type = r.skipType,
                            provider = "aniskip"
                        )
                    } else null
                }
                .sortedBy { it.startMs }
        } catch (e: HttpException) {
            emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    private suspend fun resolveMalId(imdbId: String): String? {
        val cached = malIdCache[imdbId]
        if (cached != null) return cached.takeIf { it != NO_MAL_ID }

        val malId = try {
            armApi.resolve(imdbId).firstOrNull()?.myanimelist?.toString()
        } catch (_: HttpException) {
            null
        } catch (_: Exception) {
            null
        }

        malIdCache[imdbId] = malId ?: NO_MAL_ID
        return malId
    }

    private companion object {
        private const val NO_MAL_ID = "__none__"
    }
}
