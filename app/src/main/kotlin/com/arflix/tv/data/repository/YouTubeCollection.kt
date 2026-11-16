package com.arflix.tv.data.repository

import com.arflix.tv.data.model.StreamSource
import com.arflix.tv.data.model.Profile
import com.arflix.tv.data.api.TrailerPlaybackSource
import com.arflix.tv.util.PinUtil
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.net.URI
import java.util.Locale

/**
 * The user's own YouTube collection: a Home row of YouTube videos, kept in the profile's
 * catalog list (so it rides the existing catalog cloud sync) rather than hosted anywhere.
 *
 * The row is a [com.arflix.tv.data.model.CatalogSourceType.YOUTUBE] catalog whose sourceRef
 * holds the videos as `youtube:[{"id":..,"title":..}]`. Each video becomes a native addon item
 * (see AddonNativeCatalog) whose id is `ytv:<videoId>`; stream lookup answers that id with one
 * [STREAM_MARKER] source, which is exchanged for a playable googlevideo URL only at playback
 * time, because those URLs expire.
 */
internal object YouTubeCollection {
    const val ADDON_ID = "arvio_youtube"
    const val ADDON_NAME = "YouTube"
    const val DEFAULT_TITLE = "YouTube"
    const val SOURCE_REF_PREFIX = "youtube:"
    private const val META_ID_PREFIX = "ytv:"
    private const val STREAM_MARKER = "arvio-youtube://"
    const val MAX_VIDEOS = 500

    data class Video(val id: String, val title: String)

    private val gson = Gson()
    private val VIDEO_ID = Regex("^[A-Za-z0-9_-]{11}$")

    /** The video id of a YouTube watch/share/shorts/embed link, or null for anything else. */
    fun videoIdFromUrl(input: String): String? {
        val trimmed = input.trim()
        val uri = runCatching {
            URI(if (trimmed.contains("://")) trimmed else "https://$trimmed")
        }.getOrNull() ?: return null
        val host = uri.host?.lowercase(Locale.US)?.removePrefix("www.")?.removePrefix("m.") ?: return null
        val segments = uri.path.orEmpty().split('/').filter { it.isNotBlank() }
        val id = when (host) {
            "youtu.be" -> segments.firstOrNull()
            "youtube.com", "music.youtube.com" -> when (segments.firstOrNull()) {
                "watch" -> uri.rawQuery.orEmpty().split('&')
                    .firstOrNull { it.startsWith("v=") }?.substringAfter("v=")
                "shorts", "embed", "live" -> segments.getOrNull(1)
                else -> null
            }
            else -> null
        }
        return id?.takeIf { VIDEO_ID.matches(it) }
    }

    fun encode(videos: List<Video>): String = SOURCE_REF_PREFIX + gson.toJson(videos)

    fun canAddToProfile(profile: Profile?, parentPin: String?): Boolean =
        profile?.isKidsProfile != true || PinUtil.verifyPin(parentPin.orEmpty(), profile.parentPin)

    fun resolve(stream: StreamSource, source: TrailerPlaybackSource): StreamSource = stream.copy(
        url = source.videoUrl,
        youTubeAudioUrl = source.audioUrl?.takeIf { it.isNotBlank() },
        youTubeFallbackUrl = source.fallbackVideoUrl?.takeIf { it.isNotBlank() }
    )

    fun decode(sourceRef: String?): List<Video> {
        val json = sourceRef?.takeIf { it.startsWith(SOURCE_REF_PREFIX) }?.removePrefix(SOURCE_REF_PREFIX)
            ?: return emptyList()
        val type = object : TypeToken<List<Video>>() {}.type
        val parsed: List<Video?> = runCatching { gson.fromJson<List<Video?>>(json, type) }.getOrNull().orEmpty()
        // Gson leaves absent fields null despite the Kotlin types.
        @Suppress("SENSELESS_COMPARISON")
        return parsed.filterNotNull()
            .filter { it.id != null && VIDEO_ID.matches(it.id) }
            .map { it.copy(title = it.title?.takeIf { title -> title.isNotBlank() } ?: it.id) }
            .distinctBy { it.id }
    }

    fun metaId(videoId: String): String = META_ID_PREFIX + videoId

    /**
     * The YouTube video an addon stream offers: Stremio's `ytId`, or a youtube.com / youtu.be
     * link as its url. Null for anything else.
     */
    fun videoIdOfAddonStream(ytId: String?, url: String?): String? =
        ytId?.trim()?.takeIf { VIDEO_ID.matches(it) } ?: url?.let(::videoIdFromUrl)

    fun videoIdFromMetaId(metaId: String?): String? =
        metaId?.takeIf { it.startsWith(META_ID_PREFIX) }?.removePrefix(META_ID_PREFIX)?.takeIf { VIDEO_ID.matches(it) }

    fun posterUrl(videoId: String): String = "https://i.ytimg.com/vi/$videoId/hqdefault.jpg"

    fun backdropUrl(videoId: String): String = "https://i.ytimg.com/vi/$videoId/maxresdefault.jpg"

    fun watchUrl(videoId: String): String = "https://www.youtube.com/watch?v=$videoId"

    /** The single source a collection video offers; [isStreamMarker] URLs resolve at playback. */
    fun stream(videoId: String): StreamSource = StreamSource(
        source = ADDON_NAME,
        addonName = ADDON_NAME,
        addonId = ADDON_ID,
        quality = "YouTube",
        size = "",
        url = STREAM_MARKER + videoId
    )

    fun isStreamMarker(url: String?): Boolean = url?.startsWith(STREAM_MARKER) == true

    fun videoIdFromStreamMarker(url: String): String? =
        url.removePrefix(STREAM_MARKER).takeIf { VIDEO_ID.matches(it) }
}
