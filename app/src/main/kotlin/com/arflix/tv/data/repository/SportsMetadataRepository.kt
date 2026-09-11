package com.arflix.tv.data.repository

import com.arflix.tv.BuildConfig
import com.arflix.tv.data.model.SportsEventArtwork
import com.arflix.tv.data.model.parseSportsMetadata
import com.arflix.tv.util.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
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
