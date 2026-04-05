import re

with open('app/src/main/kotlin/com/arflix/tv/data/repository/IptvRepository.kt', 'r') as f:
    content = f.read()

# 1. Add imports
import_str = """import kotlinx.coroutines.flow.map
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import okhttp3.OkHttpClient"""
content = re.sub(
    r'import kotlinx.coroutines.flow.map\nimport kotlinx.coroutines.sync.Mutex\nimport kotlinx.coroutines.sync.withLock\nimport kotlinx.coroutines.withContext\nimport okhttp3.OkHttpClient',
    import_str,
    content
)

# 2. Add fetchXtreamEpgListingsAsync
fetch_str = """    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    private suspend fun fetchXtreamEpgListingsAsync(
        creds: XtreamCredentials,
        streamIds: List<Int>,
        onStreamProcessed: (Int, Boolean) -> Unit = { _, _ -> }
    ): List<XtreamEpgListing> = withContext(Dispatchers.IO.limitedParallelism(20)) {
        val sampleLogged = java.util.concurrent.atomic.AtomicBoolean(false)
        streamIds.map { sid ->
            async {
                var hadError = false
                val url = "${creds.baseUrl}/player_api.php?username=${creds.username}" +
                    "&password=${creds.password}&action=get_short_epg&stream_id=$sid&limit=12"
                var listings: List<XtreamEpgListing>? = null
                try {
                    var resp: XtreamEpgResponse? = requestJson(url, XtreamEpgResponse::class.java)
                    listings = resp?.epgListings
                    if (listings.isNullOrEmpty()) {
                        val fallbackUrl = "${creds.baseUrl}/player_api.php?username=${creds.username}" +
                            "&password=${creds.password}&action=get_short_epg&stream_id=$sid"
                        resp = requestJson(fallbackUrl, XtreamEpgResponse::class.java)
                        listings = resp?.epgListings
                    }
                    if (!listings.isNullOrEmpty() && sampleLogged.compareAndSet(false, true)) {
                        val sample = listings.first()
                        System.err.println("[EPG] Sample response for stream_id=$sid: channelId=${sample.channelId} epgId=${sample.epgId} streamId=${sample.streamId} start=${sample.start} startTs=${sample.startTimestamp} title=${sample.title?.take(40)}")
                    }
                } catch (_: Exception) { hadError = true }
                onStreamProcessed(sid, hadError)
                listings ?: emptyList()
            }
        }.awaitAll().flatten()
    }

    /**
     * Build IptvNowNext map from Xtream EPG listings.
"""
content = re.sub(r'    /\*\*\n     \* Build IptvNowNext map from Xtream EPG listings.', fetch_str, content)

# 3. Modify refreshEpgForChannels
refresh_old = """            val allListings = java.util.Collections.synchronizedList(mutableListOf<XtreamEpgListing>())
            val errorCount = java.util.concurrent.atomic.AtomicInteger(0)
            // Use a small thread pool — this is just favorites (typically <20 channels)
            val executor = java.util.concurrent.Executors.newFixedThreadPool(10.coerceAtMost(xtreamChannels.size))

            for (ch in xtreamChannels) {
                val sid = resolveXtreamStreamId(ch) ?: continue
                executor.submit {
                    val url = "${creds.baseUrl}/player_api.php?username=${creds.username}" +
                        "&password=${creds.password}&action=get_short_epg&stream_id=$sid&limit=12"
                    try {
                        var resp: XtreamEpgResponse? = requestJson(url, XtreamEpgResponse::class.java)
                        var listings = resp?.epgListings
                        if (listings.isNullOrEmpty()) {
                            val fallbackUrl = "${creds.baseUrl}/player_api.php?username=${creds.username}" +
                                "&password=${creds.password}&action=get_short_epg&stream_id=$sid"
                            resp = requestJson(fallbackUrl, XtreamEpgResponse::class.java)
                            listings = resp?.epgListings
                        }
                        listings?.let { allListings.addAll(it) }
                    } catch (_: Exception) { errorCount.incrementAndGet() }
                }
            }

            try {
                executor.shutdown()
                executor.awaitTermination(20, java.util.concurrent.TimeUnit.SECONDS)
            } catch (_: Exception) {
                executor.shutdownNow()
            }

            val errors = errorCount.get()"""
refresh_new = """            val streamIds = xtreamChannels.mapNotNull { resolveXtreamStreamId(it) }
            var errors = 0
            val allListings = fetchXtreamEpgListingsAsync(creds, streamIds) { _, hadError ->
                if (hadError) errors++
            }"""
content = content.replace(refresh_old, refresh_new)

# 4. Modify fetchXtreamShortEpg
fetch_short_old = """    private fun fetchXtreamShortEpg(
        creds: XtreamCredentials,
        channels: List<IptvChannel>,
        onProgress: (IptvLoadProgress) -> Unit
    ): Map<String, IptvNowNext>? {"""
fetch_short_new = """    private suspend fun fetchXtreamShortEpg(
        creds: XtreamCredentials,
        channels: List<IptvChannel>,
        onProgress: (IptvLoadProgress) -> Unit
    ): Map<String, IptvNowNext>? {"""
content = content.replace(fetch_short_old, fetch_short_new)

fetch_short_body_old = """        // Parallel fetch using a thread pool (20 concurrent connections)
        val allListings = java.util.Collections.synchronizedList(mutableListOf<XtreamEpgListing>())
        val errorCount = java.util.concurrent.atomic.AtomicInteger(0)
        val fetchedCount = java.util.concurrent.atomic.AtomicInteger(0)
        val total = toFetch.size
        val executor = java.util.concurrent.Executors.newFixedThreadPool(20)
        val futures = mutableListOf<java.util.concurrent.Future<*>>()

        val sampleLogged = java.util.concurrent.atomic.AtomicBoolean(false)
        for (ch in toFetch) {
            val sid = resolveXtreamStreamId(ch) ?: continue
            futures.add(executor.submit {
                val url = "${creds.baseUrl}/player_api.php?username=${creds.username}" +
                    "&password=${creds.password}&action=get_short_epg&stream_id=$sid&limit=12"
                try {
                    var resp: XtreamEpgResponse? = requestJson(url, XtreamEpgResponse::class.java)
                    var listings = resp?.epgListings
                    // Fallback: some providers don't support limit param - retry without it
                    if (listings.isNullOrEmpty()) {
                        val fallbackUrl = "${creds.baseUrl}/player_api.php?username=${creds.username}" +
                            "&password=${creds.password}&action=get_short_epg&stream_id=$sid"
                        resp = requestJson(fallbackUrl, XtreamEpgResponse::class.java)
                        listings = resp?.epgListings
                    }
                    if (listings != null) {
                        allListings.addAll(listings)
                        if (listings.isNotEmpty() && sampleLogged.compareAndSet(false, true)) {
                            val sample = listings.first()
                            System.err.println("[EPG] Sample response for stream_id=$sid: channelId=${sample.channelId} epgId=${sample.epgId} streamId=${sample.streamId} start=${sample.start} startTs=${sample.startTimestamp} title=${sample.title?.take(40)}")
                        }
                    }
                } catch (_: Exception) { errorCount.incrementAndGet() }
                val done = fetchedCount.incrementAndGet()
                if (done % 50 == 0) {
                    val pct = (90 + ((done.toLong() * 8L) / total.toLong())).toInt().coerceIn(90, 98)
                    onProgress(IptvLoadProgress("Loading EPG... $done/$total channels", pct))
                }
            })
        }

        // Wait for all to complete (with timeout)
        try {
            executor.shutdown()
            executor.awaitTermination(60, java.util.concurrent.TimeUnit.SECONDS)
        } catch (_: Exception) {
            executor.shutdownNow()
        }

        val errors = errorCount.get()
        val fetched = fetchedCount.get()"""

fetch_short_body_new = """        var errors = 0
        var fetched = 0
        val total = toFetch.size
        val streamIds = toFetch.mapNotNull { resolveXtreamStreamId(it) }

        val allListings = fetchXtreamEpgListingsAsync(creds, streamIds) { _, hadError ->
            fetched++
            if (hadError) errors++
            if (fetched % 50 == 0) {
                val pct = (90 + ((fetched.toLong() * 8L) / total.toLong())).toInt().coerceIn(90, 98)
                onProgress(IptvLoadProgress("Loading EPG... $fetched/$total channels", pct))
            }
        }"""
content = content.replace(fetch_short_body_old, fetch_short_body_new)

with open('app/src/main/kotlin/com/arflix/tv/data/repository/IptvRepository.kt', 'w') as f:
    f.write(content)
