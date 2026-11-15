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
private const val WATCH_CONFIG_TTL_MS = 6 * 60 * 60_000L
private const val WATCH_CONFIG_RETRY_MS = 60_000L

// Known stable InnerTube API key for Android clients. Used as primary to skip the watch page
// GET (which can take 3-5s). Falls back to scraping the watch page if this ever gets rejected.
private const val FALLBACK_INNERTUBE_KEY = "AIzaSyAO_FJ2SlqU8Q4STEHLGCilw_Y9_11qcW8"
internal const val YOUTUBE_TRAILER_USER_AGENT =
    "Mozilla/5.0 (Linux; Android 12; Android TV) AppleWebKit/537.36 " +
        "(KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36"






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

internal data class StreamCandidate(
    val client: String,
    val priority: Int,
    val url: String,
    val score: Double,
    val hasN: Boolean,
    val itag: String,
    val height: Int,
    val fps: Int,
    val ext: String,
    val mimeType: String = ""
)

/**
 * The Android decoder MIME type a YouTube format needs ("video/mp4; codecs=\"av01.0.08M.08\""
 * needs video/av01), or null when the codecs are not stated or not known.
 */
internal fun youTubeVideoDecoderMime(mimeType: String): String? {
    val codecs = mimeType.substringAfter("codecs=", "").trim().trim('"').lowercase()
    return when {
        codecs.startsWith("avc1") || codecs.startsWith("avc3") -> "video/avc"
        codecs.startsWith("av01") -> "video/av01"
        codecs.startsWith("vp09") || codecs.startsWith("vp9") -> "video/x-vnd.on2.vp9"
        codecs.startsWith("hev1") || codecs.startsWith("hvc1") -> "video/hevc"
        else -> null
    }
}

/**
 * Whether this device has a decoder for [decoderMime]. A separate YouTube video track the device
 * cannot decode plays as sound over a black picture (AV1 on a Mi Box 4), so such formats are
 * never chosen. Answers are kept: the codec list does not change while the app runs.
 */
internal object DeviceVideoDecoders {
    private val answers = java.util.concurrent.ConcurrentHashMap<String, Boolean>()

    fun canDecode(decoderMime: String): Boolean = answers.getOrPut(decoderMime) {
        runCatching {
            android.media.MediaCodecList(android.media.MediaCodecList.REGULAR_CODECS).codecInfos.any { info ->
                !info.isEncoder && info.supportedTypes.any { it.equals(decoderMime, ignoreCase = true) }
            }
        }.getOrDefault(true)
    }
}

/** Try Full HD across clients before settling for HD, without repeating the same URL. */
internal fun trailerVideoProbeOrder(
    candidates: List<StreamCandidate>,
    minimumHeight: Int,
    canDecode: (String) -> Boolean = { true }
): List<StreamCandidate> {
    val sorted = candidates.filter { it.height in maxOf(720, minimumHeight)..1080 && it.ext == "mp4" }
        .filter { candidate -> youTubeVideoDecoderMime(candidate.mimeType)?.let(canDecode) ?: true }
        .distinctBy { it.url }
        .sortedWith(compareByDescending<StreamCandidate> { it.height }
            .thenBy { it.hasN }
            .thenBy { if (it.mimeType.contains("avc1")) 0 else 1 }
            .thenByDescending { it.fps }
            .thenByDescending { it.score }
            .thenBy { it.priority })
    return sorted.distinctBy { it.client to it.height }
}

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
    "user-agent" to YOUTUBE_TRAILER_USER_AGENT
)

private val CLIENTS = listOf(
    YouTubeClient(
        key = "visionos",
        id = "101",
        version = "1.02",
        userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 15_7_3) AppleWebKit/605.1.15 " +
            "(KHTML, like Gecko) Version/26.0 Safari/605.1.15",
        context = mapOf(
            "clientName" to "VISIONOS",
            "clientVersion" to "1.02",
            "deviceMake" to "Apple",
            "deviceModel" to "RealityDevice17,1",
            "osName" to "visionOS",
            "osVersion" to "26.5.23O471",
            "platform" to "MOBILE",
            "hl" to "en",
            "gl" to "US"
        ),
        priority = 0
    ),
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

    // Reuse a logged-out visitor session across previews, not a WebView or account.
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

    private suspend fun extractPlaybackSourceInternal(videoId: String, allowKeyRefresh: Boolean = true): TrailerPlaybackSource? {
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
                            ext = if (mimeType.contains("webm")) "webm" else "mp4", mimeType = mimeType
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
                                ext = if (mimeType.contains("webm")) "webm" else "mp4", mimeType = mimeType
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
        if (noStreams && keyRejected && allowKeyRefresh) {
            Log.w(TAG, "[$videoId] hardcoded key rejected with no streams — retrying with scraped key")
            refreshWatchConfigFromPage(videoId)
            return extractPlaybackSourceInternal(videoId, allowKeyRefresh = false)
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

        val bestProgressive = sortCandidates(progressive.filter { it.height <= 1080 }).firstOrNull()
            ?: sortCandidates(progressive).lastOrNull()
        // Prefer adaptive HLS where it includes Full HD. Keep its master playlist so audio
        // renditions and lower-bandwidth fallback remain available to the player.
        val manifest = bestManifest
        if (manifest != null && manifest.height >= 1080) {
            return TrailerPlaybackSource(manifest.manifestUrl, fallbackVideoUrl = bestProgressive?.url,
                maxVideoHeight = manifest.height)
        }
        if (bestProgressive != null && bestProgressive.height >= 1080) {
            return TrailerPlaybackSource(bestProgressive.url, fallbackVideoUrl = manifest?.manifestUrl,
                maxVideoHeight = bestProgressive.height)
        }

        val fallback = if (manifest != null && manifest.height >= (bestProgressive?.height ?: 0)) {
            TrailerPlaybackSource(manifest.manifestUrl, fallbackVideoUrl = bestProgressive?.url,
                maxVideoHeight = manifest.height)
        } else bestProgressive?.let { TrailerPlaybackSource(it.url, maxVideoHeight = it.height) }

        // Combined streams are often only 360p. Use separate HD only after both URLs
        // actually return media bytes; rejected URLs must not replace a working preview.
        val decodable = { candidate: StreamCandidate ->
            youTubeVideoDecoderMime(candidate.mimeType)?.let(DeviceVideoDecoders::canDecode) ?: true
        }
        val hdVideos = trailerVideoProbeOrder(
            adaptiveVideo, (fallback?.maxVideoHeight ?: 0) + 1, DeviceVideoDecoders::canDecode
        )
        val videosToProbe = if (fallback == null) {
            hdVideos + sortCandidates(adaptiveVideo.filter { it.height in 1..719 && it.ext == "mp4" && decodable(it) })
                .distinctBy { it.client }.take(2)
        } else hdVideos
        val probeDeadline = android.os.SystemClock.elapsedRealtime() + 3500L
        val probeResults = mutableMapOf<String, Boolean>()
        fun playable(url: String): Boolean = probeResults.getOrPut(url) {
            val remaining = probeDeadline - android.os.SystemClock.elapsedRealtime()
            remaining > 0 && isPlayableMediaUrl(url, remaining.coerceAtMost(1200L).toInt())
        }
        for (video in videosToProbe.take(8)) {
            if (android.os.SystemClock.elapsedRealtime() >= probeDeadline) break
            val audio = sortCandidates(adaptiveAudio.filter { it.client == video.client && it.ext == "m4a" }).firstOrNull()
                ?: continue
            if (playable(video.url) && playable(audio.url)) {
                Log.i(TAG, "[$videoId] separate tracks: video itag=${video.itag} ${video.height}p " +
                    "${video.mimeType.substringAfter("codecs=", "?")} via ${video.client}")
                return TrailerPlaybackSource(video.url, audio.url, fallback?.videoUrl, video.height)
            }
        }
        return fallback
    }

    @androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
    private fun isPlayableMediaUrl(url: String, timeoutMs: Int): Boolean = try {
        // Use exactly the player's chunk size, request headers and range handling.
        // A one-byte HTTP Range can succeed even when the actual stream returns 403.
        val source = YoutubeChunkedDataSourceFactory(timeoutMs = timeoutMs.coerceAtLeast(1)).createDataSource()
        try {
            source.open(androidx.media3.datasource.DataSpec(Uri.parse(url)))
            source.read(ByteArray(1), 0, 1) == 1
        } finally {
            source.close()
        }
    } catch (error: Exception) {
        if (error is kotlinx.coroutines.CancellationException) throw error
        false
    }

    private suspend fun getWatchConfig(): WatchConfig {
        return watchConfigMutex.withLock {
            val now = System.currentTimeMillis()
            val cached = cachedWatchConfig
            val ttl = if (cached?.visitorData != null) WATCH_CONFIG_TTL_MS else WATCH_CONFIG_RETRY_MS
            if (cached != null && now - watchConfigFetchedAt < ttl) {
                return@withLock cached
            }
            val session = try {
                val response = performRequest(
                    url = "https://www.youtube.com/sw.js_data?hl=en",
                    method = "GET",
                    headers = DEFAULT_HEADERS + ("referer" to "https://www.youtube.com/sw.js"),
                    timeoutMs = 2000L
                )
                if (response.ok) parseYoutubeGuestSession(response.body) else null
            } catch (error: Exception) {
                if (error is kotlinx.coroutines.CancellationException) throw error
                Log.w(TAG, "Guest session unavailable (${error.javaClass.simpleName}); keeping legacy fallback")
                null
            }
            val config = WatchConfig(apiKey = session?.apiKey ?: FALLBACK_INNERTUBE_KEY,
                visitorData = session?.visitorData)
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
            "context" to mapOf("client" to buildMap {
                putAll(client.context)
                if (!visitorData.isNullOrBlank()) put("visitorData", visitorData)
                put("userAgent", client.userAgent)
            }),
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
            if (height > 1080 || width > 1920) continue
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

    private fun absolutizeUrl(baseUrl: String, maybeRelative: String): String =
        try { URL(URL(baseUrl), maybeRelative).toString() } catch (e: java.net.MalformedURLException) { maybeRelative }

    private fun performRequest(url: String, method: String, headers: Map<String, String>, body: String? = null,
                               timeoutMs: Long? = null): RequestResponse {
        val requestBuilder = Request.Builder().url(url).headers(buildHeaders(headers))
        when (method.uppercase()) {
            "POST" -> requestBuilder.post((body ?: "").toRequestBody())
            "PUT" -> requestBuilder.put((body ?: "").toRequestBody())
            "DELETE" -> requestBuilder.delete()
            else -> requestBuilder.get()
        }
        val call = httpClient.newCall(requestBuilder.build())
        timeoutMs?.let { call.timeout().timeout(it, TimeUnit.MILLISECONDS) }
        call.execute().use { response ->
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
            headers.add("User-Agent", YOUTUBE_TRAILER_USER_AGENT)
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
