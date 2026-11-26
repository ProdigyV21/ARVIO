package com.arflix.tv.data.repository

import com.arflix.tv.BuildConfig
import com.arflix.tv.data.model.SportsEventArtwork
import com.arflix.tv.data.model.SportsGameDetails
import com.arflix.tv.data.model.parseSportsGameDetails
import com.arflix.tv.data.model.parseSportsMetadata
import com.arflix.tv.util.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton
import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File

@Singleton
class SportsMetadataRepository @Inject constructor(client: OkHttpClient, @ApplicationContext context: Context) {
    private val http = client.newBuilder().callTimeout(8, TimeUnit.SECONDS).retryOnConnectionFailure(false).build()
    private val mutex = Mutex()
    private val diskMutex = Mutex()
    @Volatile private var cached = emptyList<SportsEventArtwork>()
    private var retryAfter = 0L
    @Volatile private var lastSuccess = 0L
    private val disk = File(context.cacheDir, "sports-fixtures.json")

    suspend fun peek(): List<SportsEventArtwork> = withContext(Dispatchers.IO) {
        diskMutex.withLock {
            if (cached.isNotEmpty()) cached else runCatching {
                if (disk.exists() && System.currentTimeMillis() - disk.lastModified() < 86_400_000 && disk.length() <= 6_000_000)
                    parseSportsMetadata(disk.readText()).also { cached = it; lastSuccess = disk.lastModified() } else emptyList()
            }.getOrDefault(emptyList())
        }
    }

    private val dayCache = HashMap<String, Pair<Long, List<SportsEventArtwork>>>()
    private val dayMutex = Mutex()

    /**
     * The fixtures of one UTC day ("2026-11-20") for the Sports day tabs, from a
     * week back to a week ahead. Cached for a while; a failed request returns
     * what was cached, or nothing.
     */
    suspend fun loadDay(day: String): List<SportsEventArtwork> = withContext(Dispatchers.IO) {
        dayMutex.withLock {
            val now = System.currentTimeMillis()
            dayCache[day]?.let { (at, items) -> if (now - at < 20 * 60_000L) return@withLock items }
            val endpoint = BuildConfig.SPORTS_METADATA_URL.ifBlank {
                "${Constants.NETLIFY_BACKEND_URL.ifBlank { "https://auth.arvio.tv/.netlify/functions" }}/sports-metadata"
            }
            val url = endpoint.toHttpUrlOrNull()?.newBuilder()?.addQueryParameter("day", day)?.build()
                ?: return@withLock dayCache[day]?.second.orEmpty()
            val result = runCatching {
                http.newCall(Request.Builder().url(url).get().build()).execute().use { response ->
                    check(response.isSuccessful)
                    val body = response.body ?: error("Empty sports metadata")
                    check(!body.source().request(6_000_001L))
                    parseSportsMetadata(body.source().readUtf8())
                }
            }.getOrNull()
            if (result != null) {
                if (dayCache.size >= 20) dayCache.remove(dayCache.minByOrNull { it.value.first }?.key)
                dayCache[day] = now to result
                result
            } else {
                // Try again soon rather than after the full cache period.
                dayCache[day]?.let { (_, items) -> dayCache[day] = (now - 15 * 60_000L) to items }
                dayCache[day]?.second.orEmpty()
            }
        }
    }

    private val gameCache = HashMap<String, Pair<Long, SportsGameDetails>>()
    private val gameMutex = Mutex()

    /**
     * The details of one game ("123", "espn:nba:401", "mlb:77"), fetched when its
     * page opens. Kept for a minute; a failed request returns the last copy.
     */
    suspend fun loadGame(id: String): SportsGameDetails? = withContext(Dispatchers.IO) {
        gameMutex.withLock {
            val now = System.currentTimeMillis()
            gameCache[id]?.let { (at, details) -> if (now - at < 60_000L) return@withLock details }
            val url = endpoint().toHttpUrlOrNull()?.newBuilder()?.addQueryParameter("game", id)?.build()
                ?: return@withLock gameCache[id]?.second
            val result = runCatching {
                http.newCall(Request.Builder().url(url).get().build()).execute().use { response ->
                    check(response.isSuccessful)
                    val body = response.body ?: error("Empty game details")
                    check(!body.source().request(512_001L))
                    parseSportsGameDetails(body.source().readUtf8())
                }
            }.getOrNull()
            if (result != null) {
                if (gameCache.size >= 30) gameCache.remove(gameCache.minByOrNull { it.value.first }?.key)
                gameCache[id] = now to result
            }
            result ?: gameCache[id]?.second
        }
    }

    /**
     * Live and recent scores of games near now, a small slice of the catalogue,
     * for score alerts. Null when the request fails.
     */
    suspend fun loadScores(): List<SportsEventArtwork>? = withContext(Dispatchers.IO) {
        val url = endpoint().toHttpUrlOrNull()?.newBuilder()?.addQueryParameter("scores", "1")?.build() ?: return@withContext null
        runCatching {
            http.newCall(Request.Builder().url(url).get().build()).execute().use { response ->
                check(response.isSuccessful)
                val body = response.body ?: error("Empty scores")
                check(!body.source().request(2_000_001L))
                parseSportsMetadata(body.source().readUtf8())
            }
        }.getOrNull()
    }

    private fun endpoint() = BuildConfig.SPORTS_METADATA_URL.ifBlank {
        "${Constants.NETLIFY_BACKEND_URL.ifBlank { "https://auth.arvio.tv/.netlify/functions" }}/sports-metadata"
    }

    suspend fun load(): List<SportsEventArtwork> = withContext(Dispatchers.IO) {
        mutex.withLock {
            val now = android.os.SystemClock.elapsedRealtime()
            if (now < retryAfter) return@withLock cached
            val endpoint = BuildConfig.SPORTS_METADATA_URL.ifBlank {
                "${Constants.NETLIFY_BACKEND_URL.ifBlank { "https://auth.arvio.tv/.netlify/functions" }}/sports-metadata"
            }
            val result = runCatching {
                http.newCall(Request.Builder().url(endpoint).get().build()).execute().use { response ->
                    check(response.isSuccessful)
                    val body = response.body ?: error("Empty sports metadata")
                    check(body.contentLength() <= 6_000_000)
                    // Chunked responses have an unknown length. Bound the actual read too.
                    check(!body.source().request(6_000_001L))
                    val json = body.source().readUtf8()
                    val parsed = parseSportsMetadata(json)
                    if (parsed.isNotEmpty()) diskMutex.withLock { runCatching { disk.writeText(json) } }
                    parsed
                }
            }.getOrNull()
            if (result != null) { cached = result; lastSuccess = System.currentTimeMillis() }
            else if (System.currentTimeMillis() - lastSuccess > 86_400_000) cached = emptyList()
            retryAfter = now + if (result.isNullOrEmpty()) 60_000 else 120_000
            cached
        }
    }
}
