package com.arflix.tv.data.api

import com.arflix.tv.data.model.KidsAgeLevel
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException
import java.util.Locale
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

/** One video of a YouTube search answer. */
data class YouTubeSearchVideo(
    val id: String,
    val title: String,
    val channel: String?,
    val duration: String?,
    val views: String?,
    val thumbnailUrl: String
) {
    /** The length in seconds, from YouTube's "1:02:03" / "12:34" text; null when unreadable. */
    val durationSeconds: Int? get() = parseDurationSeconds(duration)

    companion object {
        internal fun parseDurationSeconds(text: String?): Int? {
            val parts = text?.trim()?.split(':')?.takeIf { it.size in 2..3 } ?: return null
            val numbers = parts.map { it.toIntOrNull()?.takeIf { n -> n >= 0 } ?: return null }
            return numbers.fold(0) { total, part -> total * 60 + part }
        }
    }
}

/**
 * The YouTube Kids content tier a kids profile searches in. YouTube Kids filters the corpus on
 * its side, so an adult query on a kids tier simply returns nothing.
 *
 * YouTube Kids' tiers are "Preschool" (4 and under), "Younger" (5-8) and "Older" (9-12, called
 * TWEEN in the API). There is no teen tier, so a teens profile still searches "Older": stricter
 * than the profile asks for, never looser.
 */
enum class YouTubeKidsCorpus(val apiValue: String) {
    PRESCHOOL("KIDS_CORPUS_PREFERENCE_PRESCHOOL"),
    YOUNGER("KIDS_CORPUS_PREFERENCE_YOUNGER"),
    OLDER("KIDS_CORPUS_PREFERENCE_TWEEN");

    companion object {
        fun forLevel(level: KidsAgeLevel): YouTubeKidsCorpus = when (level) {
            KidsAgeLevel.LITTLE_KIDS -> YOUNGER
            KidsAgeLevel.OLDER_KIDS, KidsAgeLevel.TEENS -> OLDER
        }
    }
}

/**
 * YouTube search on demand, through the same logged-out web API the in-app player uses.
 *
 * It only ever runs when the viewer presses the YouTube button on the Search screen — never
 * while typing — so a normal movie search costs YouTube nothing. Answers are kept for a while
 * per query and tier, so pressing the button again (after going back, say) is free too.
 */
@Singleton
class YouTubeSearchApi @Inject constructor() {
    private val httpClient = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(15, TimeUnit.SECONDS)
        .build()

    private val cache = object : LinkedHashMap<String, Pair<Long, List<YouTubeSearchVideo>>>(16, 0.75f, true) {
        override fun removeEldestEntry(eldest: MutableMap.MutableEntry<String, Pair<Long, List<YouTubeSearchVideo>>>) =
            size > CACHE_SIZE
    }

    /** Searches YouTube for [query]; with a [kidsCorpus], YouTube Kids at that tier instead. */
    suspend fun search(query: String, kidsCorpus: YouTubeKidsCorpus?): List<YouTubeSearchVideo> =
        withContext(Dispatchers.IO) {
            val trimmed = query.trim()
            if (trimmed.isEmpty()) return@withContext emptyList()
            val key = "${kidsCorpus?.name ?: "all"}|${trimmed.lowercase(Locale.ROOT)}"
            synchronized(cache) {
                cache[key]?.takeIf { System.currentTimeMillis() - it.first < CACHE_TTL_MS }?.let { return@withContext it.second }
            }
            val locale = Locale.getDefault()
            val body = buildRequestBody(trimmed, kidsCorpus, locale.language, locale.country)
            val endpoint = if (kidsCorpus != null) KIDS_ENDPOINT else WEB_ENDPOINT
            val request = Request.Builder()
                .url(endpoint)
                .header("User-Agent", USER_AGENT)
                .header("Origin", if (kidsCorpus != null) KIDS_ORIGIN else WEB_ORIGIN)
                .post(body.toRequestBody(JSON))
                .build()
            val videos = httpClient.newCall(request).execute().use { response ->
                if (!response.isSuccessful) throw IOException("YouTube search answered HTTP ${response.code}")
                parseSearchResponse(response.body?.string().orEmpty())
            }
            synchronized(cache) { cache[key] = System.currentTimeMillis() to videos }
            videos
        }

    companion object {
        private const val WEB_ORIGIN = "https://www.youtube.com"
        private const val KIDS_ORIGIN = "https://www.youtubekids.com"
        private const val WEB_ENDPOINT = "$WEB_ORIGIN/youtubei/v1/search?prettyPrint=false"
        private const val KIDS_ENDPOINT = "$KIDS_ORIGIN/youtubei/v1/search?prettyPrint=false"
        private const val WEB_CLIENT_VERSION = "2.20261112.10.00"
        private const val KIDS_CLIENT_VERSION = "2.20261112.00.00"
        /** The "Type: Video" search filter: no channels, playlists or movies-for-sale. */
        private const val VIDEOS_ONLY_PARAMS = "EgIQAQ=="
        private const val USER_AGENT =
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36"
        private val JSON = "application/json".toMediaType()
        private const val CACHE_SIZE = 32
        private const val CACHE_TTL_MS = 30 * 60 * 1000L
        private const val MAX_RESULTS = 30
        private val VIDEO_ID = Regex("^[A-Za-z0-9_-]{11}$")

        internal fun buildRequestBody(
            query: String,
            kidsCorpus: YouTubeKidsCorpus?,
            language: String,
            country: String
        ): String {
            val client = JsonObject().apply {
                addProperty("clientName", if (kidsCorpus != null) "WEB_KIDS" else "WEB")
                addProperty("clientVersion", if (kidsCorpus != null) KIDS_CLIENT_VERSION else WEB_CLIENT_VERSION)
                addProperty("hl", language.ifBlank { "en" })
                addProperty("gl", country.takeIf { it.length == 2 }?.uppercase(Locale.ROOT) ?: "US")
                if (kidsCorpus != null) {
                    add("kidsAppInfo", JsonObject().apply {
                        add("contentSettings", JsonObject().apply {
                            addProperty("corpusPreference", kidsCorpus.apiValue)
                            addProperty("kidsNoSearchMode", "YT_KIDS_NO_SEARCH_MODE_OFF")
                        })
                    })
                }
            }
            return JsonObject().apply {
                add("context", JsonObject().apply { add("client", client) })
                addProperty("query", query)
                if (kidsCorpus == null) addProperty("params", VIDEOS_ONLY_PARAMS)
            }.toString()
        }

        /**
         * The playable videos of a search answer, in YouTube's order. The web client lists them
         * as `videoRenderer`, YouTube Kids as `compactVideoRenderer`. Live streams and premieres
         * (no length) are left out: the player plays finished videos.
         */
        internal fun parseSearchResponse(json: String): List<YouTubeSearchVideo> {
            val root = runCatching { JsonParser.parseString(json) }.getOrNull() ?: return emptyList()
            val renderers = mutableListOf<JsonObject>()
            collectRenderers(root, renderers)
            return renderers.mapNotNull(::toVideo).distinctBy { it.id }.take(MAX_RESULTS)
        }

        private fun collectRenderers(element: JsonElement, out: MutableList<JsonObject>) {
            when {
                element.isJsonObject -> for ((key, value) in element.asJsonObject.entrySet()) {
                    if ((key == "videoRenderer" || key == "compactVideoRenderer") && value.isJsonObject) {
                        out += value.asJsonObject
                    } else {
                        collectRenderers(value, out)
                    }
                }
                element.isJsonArray -> element.asJsonArray.forEach { collectRenderers(it, out) }
            }
        }

        private fun toVideo(renderer: JsonObject): YouTubeSearchVideo? {
            val id = renderer.string("videoId")?.takeIf { VIDEO_ID.matches(it) } ?: return null
            val title = text(renderer.obj("title"))?.takeIf { it.isNotBlank() } ?: return null
            val duration = text(renderer.obj("lengthText"))?.takeIf { it.isNotBlank() } ?: return null
            val bylines = listOf("ownerText", "longBylineText", "shortBylineText").mapNotNull { renderer.obj(it) }
            val channel = bylines.firstNotNullOfOrNull(::text)?.takeIf { it.isNotBlank() }
            val views = (text(renderer.obj("shortViewCountText")) ?: text(renderer.obj("viewCountText")))
                ?.takeIf { it.isNotBlank() }
            return YouTubeSearchVideo(
                id = id,
                title = title,
                channel = channel,
                duration = duration,
                views = views,
                thumbnailUrl = largestThumbnail(renderer) ?: "https://i.ytimg.com/vi/$id/mqdefault.jpg"
            )
        }

        private fun largestThumbnail(renderer: JsonObject): String? =
            renderer.obj("thumbnail")?.array("thumbnails")
                ?.mapNotNull { it.takeIf(JsonElement::isJsonObject)?.asJsonObject }
                ?.maxByOrNull { it.int("width") * it.int("height") }
                ?.string("url")
                ?.let { if (it.startsWith("//")) "https:$it" else it }
                ?.takeIf { it.startsWith("https://") }

        /** YouTube text: either `simpleText` or a list of `runs`. */
        private fun text(node: JsonObject?): String? {
            node ?: return null
            node.string("simpleText")?.let { return it.trim() }
            val runs = node.array("runs") ?: return null
            return runs.mapNotNull { it.takeIf(JsonElement::isJsonObject)?.asJsonObject?.string("text") }
                .joinToString("").trim().takeIf { it.isNotEmpty() }
        }

        private fun JsonObject.obj(name: String): JsonObject? = get(name)?.takeIf { it.isJsonObject }?.asJsonObject
        private fun JsonObject.array(name: String): JsonArray? = get(name)?.takeIf { it.isJsonArray }?.asJsonArray
        private fun JsonObject.string(name: String): String? =
            get(name)?.takeIf { it.isJsonPrimitive }?.asString
        private fun JsonObject.int(name: String): Int =
            get(name)?.takeIf { it.isJsonPrimitive }?.let { runCatching { it.asInt }.getOrNull() } ?: 0
    }
}
