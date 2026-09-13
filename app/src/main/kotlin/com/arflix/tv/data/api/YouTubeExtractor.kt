package com.arflix.tv.data.api

import android.net.Uri
import android.util.Log
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import okhttp3.Headers
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.net.URL
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

private const val TAG = "InAppYouTubeExtractor"
private const val EXTRACTOR_TIMEOUT_MS = 30_000L
private const val URL_CACHE_TTL_MS = 5 * 60_000L
private const val WATCH_CONFIG_TTL_MS = 24 * 60 * 60_000L  // 24h — key rarely changes

// Known stable InnerTube API key for Android clients. Used as primary to skip the watch page
// GET (which can take 3-5s). Falls back to scraping the watch page if this ever gets rejected.
private const val FALLBACK_INNERTUBE_KEY = "AIzaSyAO_FJ2SlqU8Q4STEHLGCilw_Y9_11qcW8"
private const val DEFAULT_USER_AGENT =
    "Mozilla/5.0 (Linux; Android 12; Android TV) AppleWebKit/537.36 " +
        "(KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36"
private const val PREFERRED_SEPARATE_CLIENT = "android_vr"






private data class YouTubeClient(
    val key: String,
    val id: String,
    val version: String,
    val userAgent: String,
    val context: Map<String, Any>,
    val priority: Int
)

private data class WatchConfig(
    val apiKey: String?,
    val visitorData: String?
)

private data class StreamCandidate(
    val client: String,
    val priority: Int,
    val url: String,
    val score: Double,
    val hasN: Boolean,
    val itag: String,
    val height: Int,
    val fps: Int,
    val ext: String
)

private data class ManifestBestVariant(
    val url: String,
    val width: Int,
    val height: Int,
    val bandwidth: Long
)

private data class ManifestCandidate(
    val client: String,
    val priority: Int,
    val manifestUrl: String,
    val selectedVariantUrl: String,
    val height: Int,
    val bandwidth: Long
)

private val DEFAULT_HEADERS = mapOf(
    "accept-language" to "en-US,en;q=0.9",
    "user-agent" to DEFAULT_USER_AGENT
)

private val CLIENTS = listOf(
    YouTubeClient(
        key = "android_vr",
        id = "28",
        version = "1.56.21",
        userAgent = "com.google.android.apps.youtube.vr.oculus/1.56.21 " +
            "(Linux; U; Android 12; en_US; Quest 3; Build/SQ3A.220605.009.A1) gzip",
        context = mapOf(
            "clientName" to "ANDROID_VR",
            "clientVersion" to "1.56.21",
            "deviceMake" to "Oculus",
            "deviceModel" to "Quest 3",
            "osName" to "Android",
            "osVersion" to "12",
            "platform" to "MOBILE",
            "androidSdkVersion" to 32,
            "hl" to "en",
            "gl" to "US"
        ),
        priority = 0
    ),
    YouTubeClient(
        key = "android",
        id = "3",
        version = "20.10.35",
        userAgent = "com.google.android.youtube/20.10.35 (Linux; U; Android 14; en_US) gzip",
        context = mapOf(
            "clientName" to "ANDROID",
            "clientVersion" to "20.10.35",
            "osName" to "Android",
            "osVersion" to "14",
            "platform" to "MOBILE",
            "androidSdkVersion" to 34,
            "hl" to "en",
            "gl" to "US"
        ),
        priority = 1
    ),
    YouTubeClient(
        key = "ios",
        id = "5",
        version = "20.10.1",
        userAgent = "com.google.ios.youtube/20.10.1 (iPhone16,2; U; CPU iOS 17_4 like Mac OS X)",
        context = mapOf(
            "clientName" to "IOS",
            "clientVersion" to "20.10.1",
            "deviceModel" to "iPhone16,2",
            "osName" to "iPhone",
            "osVersion" to "17.4.0.21E219",
            "platform" to "MOBILE",
            "hl" to "en",
            "gl" to "US"
        ),
        priority = 2
    )
)

@Singleton
class InAppYouTubeExtractor @Inject constructor() {
    private val gson = Gson()

    private val httpClient = OkHttpClient.Builder()
        .connectTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .writeTimeout(20, TimeUnit.SECONDS)
        .followRedirects(true)
        .followSslRedirects(true)
        .build()

    // Result cache: videoId -> (source, fetchedAtMs). Cleared on error so stale 403 URLs don't stick.
    private val urlCache = ConcurrentHashMap<String, Pair<TrailerPlaybackSource, Long>>()

    // WatchConfig cache: INNERTUBE_API_KEY is global and rarely changes
    private val watchConfigMutex = Mutex()
    private var cachedWatchConfig: WatchConfig? = null
    private var watchConfigFetchedAt: Long = 0L

    suspend fun extractPlaybackSource(youtubeUrl: String): TrailerPlaybackSource? = withContext(Dispatchers.IO) {
        if (youtubeUrl.isBlank()) return@withContext null
        val videoId = extractVideoId(youtubeUrl) ?: return@withContext null

        val cached = urlCache[videoId]
        if (cached != null && System.currentTimeMillis() - cached.second < URL_CACHE_TTL_MS) {
            return@withContext cached.first
        }

        val source = try {
            withTimeout(EXTRACTOR_TIMEOUT_MS) {
                extractPlaybackSourceInternal(videoId)
            }
        } catch (e: kotlinx.coroutines.TimeoutCancellationException) {
            Log.w(TAG, "[$videoId] extraction timed out")
            null
        } catch (e: kotlinx.coroutines.CancellationException) {
            throw e
        } catch (e: Exception) {
            if (e is kotlinx.coroutines.CancellationException) throw e

            Log.w(TAG, "[$videoId] extraction failed: ${e.message}")
            null
        }

        if (source != null) {
            urlCache[videoId] = source to System.currentTimeMillis()
        } else {
            Log.w(TAG, "[$videoId] no playable source found")
        }
        source
    }

    private suspend fun extractPlaybackSourceInternal(videoId: String): TrailerPlaybackSource? {
        val watchConfig = getWatchConfig()
        val apiKey = watchConfig.apiKey
            ?: throw IllegalStateException("Unable to extract INNERTUBE_API_KEY")

        val progressive = mutableListOf<StreamCandidate>()
        val adaptiveVideo = mutableListOf<StreamCandidate>()
        val adaptiveAudio = mutableListOf<StreamCandidate>()
        val manifestUrls = mutableListOf<Triple<String, Int, String>>()

        // Parallel client API calls
        var keyRejected = false
        coroutineScope {
            val clientJobs = CLIENTS.map { client ->
                async(Dispatchers.IO) {
                    try {
                        val playerResponse = fetchPlayerResponse(
                            apiKey = apiKey,
                            videoId = videoId,
                            client = client,
                            visitorData = watchConfig.visitorData,
                            cookieHeader = null
                        )
                        client to playerResponse
                    } catch (e: kotlinx.coroutines.CancellationException) {
                        throw e
                    } catch (e: Exception) {
                        if (e is kotlinx.coroutines.CancellationException) throw e

                        val msg = e.message.orEmpty()
                        if (msg.contains("401") || msg.contains("403")) keyRejected = true
                        null
                    }
                }
            }

            clientJobs.awaitAll().filterNotNull().forEach { (client, playerResponse) ->
                val streamingData = playerResponse.mapValue("streamingData") ?: return@forEach
                val hlsManifestUrl = streamingData.stringValue("hlsManifestUrl")
                if (!hlsManifestUrl.isNullOrBlank()) {
                    synchronized(manifestUrls) { manifestUrls += Triple(client.key, client.priority, hlsManifestUrl) }
                }

                for (format in streamingData.listMapValue("formats")) {
                    val url = format.stringValue("url") ?: continue
                    val mimeType = format.stringValue("mimeType").orEmpty()
                    if (!mimeType.contains("video/") && mimeType.isNotBlank()) continue
                    val height = (format.numberValue("height")
                        ?: parseQualityLabel(format.stringValue("qualityLabel"))?.toDouble() ?: 0.0).toInt()
                    val fps = (format.numberValue("fps") ?: 0.0).toInt()
                    val bitrate = format.numberValue("bitrate") ?: format.numberValue("averageBitrate") ?: 0.0
                    synchronized(progressive) {
                        progressive += StreamCandidate(
                            client = client.key, priority = client.priority, url = url,
                            score = videoScore(height, fps, bitrate), hasN = hasNParam(url),
                            itag = format.stringValue("itag").orEmpty(), height = height, fps = fps,
                            ext = if (mimeType.contains("webm")) "webm" else "mp4"
                        )
                    }
                }

                for (format in streamingData.listMapValue("adaptiveFormats")) {
                    val url = format.stringValue("url") ?: continue
                    val mimeType = format.stringValue("mimeType").orEmpty()
                    if (mimeType.contains("video/")) {
                        val height = (format.numberValue("height")
                            ?: parseQualityLabel(format.stringValue("qualityLabel"))?.toDouble() ?: 0.0).toInt()
                        val fps = (format.numberValue("fps") ?: 0.0).toInt()
                        val bitrate = format.numberValue("bitrate") ?: format.numberValue("averageBitrate") ?: 0.0
                        synchronized(adaptiveVideo) {
                            adaptiveVideo += StreamCandidate(
                                client = client.key, priority = client.priority, url = url,
                                score = videoScore(height, fps, bitrate), hasN = hasNParam(url),
                                itag = format.stringValue("itag").orEmpty(), height = height, fps = fps,
                                ext = if (mimeType.contains("webm")) "webm" else "mp4"
                            )
                        }
                    } else if (mimeType.contains("audio/") || mimeType.startsWith("audio/")) {
                        val bitrate = format.numberValue("bitrate") ?: format.numberValue("averageBitrate") ?: 0.0
                        val asr = format.numberValue("audioSampleRate") ?: 0.0
                        synchronized(adaptiveAudio) {
                            adaptiveAudio += StreamCandidate(
                                client = client.key, priority = client.priority, url = url,
                                score = audioScore(bitrate, asr), hasN = hasNParam(url),
                                itag = format.stringValue("itag").orEmpty(), height = 0, fps = 0,
                                ext = if (mimeType.contains("webm")) "webm" else "m4a"
                            )
                        }
                    }
                }
            }
        }

        // If hardcoded key was rejected and we got nothing, scrape a fresh key and retry once
        val noStreams = manifestUrls.isEmpty() && progressive.isEmpty() && adaptiveVideo.isEmpty()
        if (noStreams && keyRejected) {
            Log.w(TAG, "[$videoId] hardcoded key rejected with no streams — retrying with scraped key")
            refreshWatchConfigFromPage(videoId)
            return extractPlaybackSourceInternal(videoId)
        }

        if (manifestUrls.isEmpty() && progressive.isEmpty() && adaptiveVideo.isEmpty() && adaptiveAudio.isEmpty()) {
            return null
        }

        var bestManifest: ManifestCandidate? = null
        if (manifestUrls.isNotEmpty()) {
            coroutineScope {
                val manifestJobs = manifestUrls.map { (clientKey, priority, manifestUrl) ->
                    async(Dispatchers.IO) {
                        try {
                            val variant = parseHlsManifest(manifestUrl) ?: return@async null
                            ManifestCandidate(
                                client = clientKey, priority = priority,
                                manifestUrl = manifestUrl, selectedVariantUrl = variant.url,
                                height = variant.height, bandwidth = variant.bandwidth
                            )
                        } catch (e: kotlinx.coroutines.CancellationException) {
                            throw e
                        } catch (_: Exception) { null }
                    }
                }
                manifestJobs.awaitAll().filterNotNull().forEach { candidate ->
                    if (bestManifest == null || candidate.height > bestManifest!!.height ||
                        (candidate.height == bestManifest!!.height && candidate.bandwidth > bestManifest!!.bandwidth)
                    ) {
                        bestManifest = candidate
                    }
                }
            }
        }

        // Prefer HLS manifest — ExoPlayer handles it natively (adaptive bitrate, no n-param 403).
        // Direct adaptive stream URLs require n-param decryption which we don't do, so they 403.
        if (bestManifest != null) {
            return TrailerPlaybackSource(videoUrl = bestManifest.manifestUrl, audioUrl = null)
        }

        // Fall back to progressive (combined video+audio, also not n-param throttled at lower quality)
        val bestProgressive = sortCandidates(progressive).firstOrNull()
        if (bestProgressive != null) {
            return TrailerPlaybackSource(videoUrl = bestProgressive.url, audioUrl = null)
        }

        // Last resort: adaptive streams (may 403 without n-param decryption)
        val bestVideo = pickBestForClient(adaptiveVideo, PREFERRED_SEPARATE_CLIENT)
        val bestAudio = pickBestForClient(adaptiveAudio, PREFERRED_SEPARATE_CLIENT)
        val videoUrl = bestVideo?.url ?: return null
        return TrailerPlaybackSource(videoUrl = videoUrl, audioUrl = bestAudio?.url)
    }

    private suspend fun getWatchConfig(): WatchConfig {
        return watchConfigMutex.withLock {
            val now = System.currentTimeMillis()
            val cached = cachedWatchConfig
            if (cached != null && now - watchConfigFetchedAt < WATCH_CONFIG_TTL_MS) {
                return@withLock cached
            }
            val config = WatchConfig(apiKey = FALLBACK_INNERTUBE_KEY, visitorData = null)
            cachedWatchConfig = config
            watchConfigFetchedAt = now
            config
        }
    }

    fun evictCache(videoId: String) {
        urlCache.remove(videoId)
    }

    suspend fun refreshWatchConfigFromPage(videoId: String) {
        watchConfigMutex.withLock {
            val watchResponse = performRequest(
                url = "https://www.youtube.com/watch?v=$videoId&hl=en",
                method = "GET", headers = DEFAULT_HEADERS
            )
            if (watchResponse.ok) {
                val fresh = parseWatchConfig(watchResponse.body)
                if (fresh.apiKey != null) {
                    cachedWatchConfig = fresh
                    watchConfigFetchedAt = System.currentTimeMillis()
                }
            }
        }
    }

    private fun parseWatchConfig(html: String): WatchConfig {
        val apiKey = YouTubeExtractorRegexes.API_KEY_REGEX.find(html)?.groupValues?.getOrNull(1)
        val visitorData = YouTubeExtractorRegexes.VISITOR_DATA_REGEX.find(html)?.groupValues?.getOrNull(1)
        return WatchConfig(apiKey = apiKey, visitorData = visitorData)
    }

    private fun extractVideoId(input: String): String? {
        val trimmed = input.trim()
        if (YouTubeExtractorRegexes.VIDEO_ID_REGEX.matches(trimmed)) return trimmed

        val normalized = if (trimmed.startsWith("http://") || trimmed.startsWith("https://")) {
            trimmed
        } else {
            "https://$trimmed"
        }

        return try {
            val uri = Uri.parse(normalized)
            val host = uri.host?.lowercase().orEmpty()
            if (host.endsWith("youtu.be")) {
                val id = uri.pathSegments.firstOrNull()
                if (!id.isNullOrBlank() && YouTubeExtractorRegexes.VIDEO_ID_REGEX.matches(id)) {
                    return id
                }
            }
            val queryId = uri.getQueryParameter("v")
            if (!queryId.isNullOrBlank() && YouTubeExtractorRegexes.VIDEO_ID_REGEX.matches(queryId)) {
                return queryId
            }

            val segments = uri.pathSegments
            if (segments.size >= 2) {
                val first = segments[0]
                val second = segments[1]
                if ((first == "embed" || first == "shorts" || first == "live") && YouTubeExtractorRegexes.VIDEO_ID_REGEX.matches(second)) {
                    return second
                }
            }
            null
        } catch (_: IllegalArgumentException) {
            null
        } catch (_: UnsupportedOperationException) {
            null
        }
    }

    private fun fetchPlayerResponse(
        apiKey: String, videoId: String, client: YouTubeClient,
        visitorData: String?, cookieHeader: String?
    ): Map<*, *> {
        val endpoint = "https://www.youtube.com/youtubei/v1/player?key=${Uri.encode(apiKey)}"
        val headers = buildMap {
            putAll(DEFAULT_HEADERS)
            put("content-type", "application/json")
            put("origin", "https://www.youtube.com")
            put("x-youtube-client-name", client.id)
            put("x-youtube-client-version", client.version)
            put("user-agent", client.userAgent)
            if (!visitorData.isNullOrBlank()) put("x-goog-visitor-id", visitorData)
            if (!cookieHeader.isNullOrBlank()) put("cookie", cookieHeader)
        }
        val payload = mapOf(
            "videoId" to videoId,
            "contentCheckOk" to true,
            "racyCheckOk" to true,
            "context" to mapOf("client" to client.context),
            "playbackContext" to mapOf(
                "contentPlaybackContext" to mapOf("html5Preference" to "HTML5_PREF_WANTS")
            )
        )
        val response = performRequest(url = endpoint, method = "POST", headers = headers, body = gson.toJson(payload))
        if (!response.ok) {
            throw IllegalStateException("player API ${client.key} failed (${response.status}): ${response.body.take(200)}")
        }
        return gson.fromJson(response.body, Map::class.java) ?: emptyMap<String, Any>()
    }

    private fun parseHlsManifest(manifestUrl: String): ManifestBestVariant? {
        val response = performRequest(url = manifestUrl, method = "GET", headers = DEFAULT_HEADERS)
        if (!response.ok) throw IllegalStateException("Failed to fetch HLS manifest (${response.status})")

        val lines = response.body.lineSequence().map { it.trim() }.filter { it.isNotBlank() }.toList()
        var bestVariant: ManifestBestVariant? = null

        for (i in lines.indices) {
            val line = lines[i]
            if (!line.startsWith("#EXT-X-STREAM-INF:")) continue
            val attrs = parseHlsAttributeList(line)
            val nextLine = lines.getOrNull(i + 1) ?: continue
            if (nextLine.startsWith("#")) continue
            val (width, height) = parseResolution(attrs["RESOLUTION"].orEmpty())
            val bandwidth = attrs["BANDWIDTH"]?.toLongOrNull() ?: 0L
            val candidate = ManifestBestVariant(url = absolutizeUrl(manifestUrl, nextLine), width = width, height = height, bandwidth = bandwidth)
            if (bestVariant == null || candidate.height > bestVariant.height ||
                (candidate.height == bestVariant.height && candidate.bandwidth > bestVariant.bandwidth) ||
                (candidate.height == bestVariant.height && candidate.bandwidth == bestVariant.bandwidth && candidate.width > bestVariant.width)
            ) {
                bestVariant = candidate
            }
        }
        return bestVariant
    }

    private fun parseHlsAttributeList(line: String): Map<String, String> {
        val index = line.indexOf(':')
        if (index == -1) return emptyMap()
        val raw = line.substring(index + 1)
        val out = LinkedHashMap<String, String>()
        val key = StringBuilder(); val value = StringBuilder()
        var inKey = true; var inQuote = false
        for (ch in raw) {
            if (inKey) { if (ch == '=') inKey = false else key.append(ch); continue }
            if (ch == '"') { inQuote = !inQuote; continue }
            if (ch == ',' && !inQuote) {
                val k = key.toString().trim()
                if (k.isNotEmpty()) out[k] = value.toString().trim()
                key.clear(); value.clear(); inKey = true; continue
            }
            value.append(ch)
        }
        val lastKey = key.toString().trim()
        if (lastKey.isNotEmpty()) out[lastKey] = value.toString().trim()
        return out
    }

    private fun parseResolution(raw: String): Pair<Int, Int> {
        val parts = raw.split('x')
        if (parts.size != 2) return 0 to 0
        return (parts[0].toIntOrNull() ?: 0) to (parts[1].toIntOrNull() ?: 0)
    }

    private fun parseQualityLabel(label: String?): Int? {
        if (label.isNullOrBlank()) return null
        val match = YouTubeExtractorRegexes.QUALITY_LABEL_REGEX.find(label) ?: return null
        return match.groupValues.getOrNull(1)?.toIntOrNull()
    }

    private fun hasNParam(url: String): Boolean =
        try {
            !Uri.parse(url).getQueryParameter("n").isNullOrBlank()
        } catch (_: IllegalArgumentException) {
            false
        } catch (_: UnsupportedOperationException) {
            false
        }

    private fun videoScore(height: Int, fps: Int, bitrate: Double) =
        height * 1_000_000_000.0 + fps * 1_000_000.0 + bitrate

    private fun audioScore(bitrate: Double, audioSampleRate: Double) =
        bitrate * 1_000_000.0 + audioSampleRate

    private fun sortCandidates(items: List<StreamCandidate>): List<StreamCandidate> =
        items.sortedWith(
            compareByDescending<StreamCandidate> { it.score }
                .thenBy { if (it.hasN) 1 else 0 }
                .thenBy { containerPreference(it.ext) }
                .thenBy { it.priority }
        )

    private fun containerPreference(ext: String) = when (ext.lowercase()) {
        "mp4", "m4a" -> 0; "webm" -> 1; else -> 2
    }

    private fun pickBestForClient(items: List<StreamCandidate>, clientKey: String): StreamCandidate? {
        val sameClient = items.filter { it.client == clientKey }
        return sortCandidates(if (sameClient.isNotEmpty()) sameClient else items).firstOrNull()
    }

    private fun absolutizeUrl(baseUrl: String, maybeRelative: String): String =
        try { URL(URL(baseUrl), maybeRelative).toString() } catch (e: java.net.MalformedURLException) { maybeRelative }

    private fun performRequest(url: String, method: String, headers: Map<String, String>, body: String? = null): RequestResponse {
        val requestBuilder = Request.Builder().url(url).headers(buildHeaders(headers))
        when (method.uppercase()) {
            "POST" -> requestBuilder.post((body ?: "").toRequestBody())
            "PUT" -> requestBuilder.put((body ?: "").toRequestBody())
            "DELETE" -> requestBuilder.delete()
            else -> requestBuilder.get()
        }
        httpClient.newCall(requestBuilder.build()).execute().use { response ->
            return RequestResponse(
                ok = response.isSuccessful, status = response.code,
                statusText = response.message, url = response.request.url.toString(),
                body = response.body?.string().orEmpty()
            )
        }
    }

    private fun buildHeaders(source: Map<String, String>): Headers {
        val headers = Headers.Builder()
        source.forEach { (name, value) ->
            if (!name.equals("Accept-Encoding", ignoreCase = true)) headers.add(name, value)
        }
        if (source.keys.none { it.equals("User-Agent", ignoreCase = true) }) {
            headers.add("User-Agent", DEFAULT_USER_AGENT)
        }
        return headers.build()
    }
}

private data class RequestResponse(
    val ok: Boolean, val status: Int, val statusText: String, val url: String, val body: String
)

private fun Map<*, *>.mapValue(key: String): Map<*, *>? = this[key] as? Map<*, *>

private fun Map<*, *>.listMapValue(key: String): List<Map<*, *>> {
    val raw = this[key] as? List<*> ?: return emptyList()
    return raw.mapNotNull { it as? Map<*, *> }
}

private fun Map<*, *>.stringValue(key: String): String? = this[key]?.toString()

private fun Map<*, *>.numberValue(key: String): Double? = when (val v = this[key]) {
    is Number -> v.toDouble()
    is String -> v.toDoubleOrNull()
    else -> null
}

private object YouTubeExtractorRegexes {
    val VIDEO_ID_REGEX = Regex("^[a-zA-Z0-9_-]{11}$")
    val API_KEY_REGEX = Regex("\"INNERTUBE_API_KEY\":\"([^\"]+)\"")
    val VISITOR_DATA_REGEX = Regex("\"VISITOR_DATA\":\"([^\"]+)\"")
    val QUALITY_LABEL_REGEX = Regex("(\\d{2,4})p")
}
