package com.arflix.tv.data.repository

import android.content.Context
import android.util.Log
import com.arflix.tv.data.api.StremioMetaPreview
import com.arflix.tv.data.model.Episode
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.util.concurrent.ConcurrentHashMap

/**
 * Native addon items: addon catalog entries with no TMDB/IMDb identity whose addon serves
 * its own metadata (see [StreamRepository.addonServesOwnMeta]), such as a broadcaster's
 * VOD catalogue. Matching those to TMDB would hide everything TMDB doesn't list, so they
 * are shown as they are: a stable negative [MediaItem.id], details and episodes from the
 * addon's /meta, and streams through the normal addon stream path keyed by the addon's id.
 *
 * Navigation and watch history only carry the numeric id, so a small registry on disk maps
 * it back to the addon and its id; items opened from Continue Watching still resolve after
 * a restart.
 */
internal class AddonNativeCatalog(
    private val context: Context,
    private val streamRepository: StreamRepository,
    private val gson: Gson = Gson()
) {
    private data class Entry(
        val addonId: String,
        val metaId: String,
        val type: String,
        val title: String,
        val poster: String? = null,
        val background: String? = null,
        val description: String? = null
    )

    private data class CachedMeta(val meta: StremioMetaPreview, val loadedAtMs: Long)

    private val registry = ConcurrentHashMap<Int, Entry>()
    private val metas = ConcurrentHashMap<Int, CachedMeta>()
    @Volatile private var registryLoaded = false

    private val registryFile: File get() = File(context.filesDir, REGISTRY_FILE)

    fun stableId(addonId: String, metaId: String): Int =
        -("native:$addonId:$metaId".hashCode() and Int.MAX_VALUE).coerceAtLeast(1)

    @Volatile private var registryDirty = false

    /**
     * Builds (and remembers) the card for a catalog entry; call [flush] after a batch.
     * [addonType] is the addon's own type for the item ("Podcasts", "tv"), which its /meta
     * is requested with; without one, the type follows [mediaType].
     */
    fun register(
        addonId: String,
        preview: StremioMetaPreview,
        mediaType: MediaType,
        addonType: String? = null
    ): MediaItem? {
        val metaId = preview.id?.trim()?.takeIf { it.isNotBlank() } ?: return null
        val title = preview.name?.trim()?.takeIf { it.isNotBlank() } ?: return null
        val id = stableId(addonId, metaId)
        val entry = Entry(
            addonId = addonId,
            metaId = metaId,
            type = addonType?.trim()?.takeIf { it.isNotBlank() }
                ?: if (mediaType == MediaType.TV) "series" else "movie",
            title = title,
            poster = preview.poster,
            background = preview.background,
            description = preview.description
        )
        ensureRegistryLoaded()
        if (registry.put(id, entry) != entry) registryDirty = true
        return entry.toMediaItem(id, mediaType)
    }

    /** Saves the registry if [register] added or changed anything. */
    suspend fun flush() {
        if (!registryDirty) return
        registryDirty = false
        withContext(Dispatchers.IO) { persistRegistry() }
    }

    fun isNative(mediaId: Int): Boolean = mediaId < 0 && entry(mediaId) != null

    /** A live channel (Stremio's "tv" type): it plays live, so it has no progress to resume. */
    fun isLiveChannel(mediaId: Int): Boolean = entry(mediaId)?.type.equals("tv", ignoreCase = true)

    /** The catalog card, without touching the network (for catalog rows). */
    fun card(mediaType: MediaType, mediaId: Int): MediaItem? = entry(mediaId)?.toMediaItem(mediaId, mediaType)

    /** The addon's own id for this item, used where an IMDb id would go for streams. */
    fun streamId(mediaId: Int): String? = entry(mediaId)?.metaId

    /** Rehydrate a portable watch-history card on devices without this registry yet. */
    fun restore(item: MediaItem) {
        val addonId = item.addonNativeAddonId?.takeIf { it.isNotBlank() } ?: return
        val metaId = item.addonNativeId?.takeIf { it.isNotBlank() } ?: return
        if (item.id != stableId(addonId, metaId)) return
        ensureRegistryLoaded()
        // The card doesn't carry the addon's own type; keep one the catalog already recorded.
        val type = registry[item.id]?.type ?: if (item.mediaType == MediaType.TV) "series" else "movie"
        val restored = Entry(addonId, metaId, type, item.title, item.image, item.backdrop, item.overview)
        if (registry.put(item.id, restored) != restored) registryDirty = true
    }

    /** Full details from the addon's /meta; falls back to the catalog card when that fails. */
    suspend fun details(mediaType: MediaType, mediaId: Int): MediaItem? {
        val entry = entry(mediaId) ?: return null
        val meta = loadMeta(mediaId, entry)
        val base = entry.toMediaItem(mediaId, mediaType)
        if (meta == null) return base
        val seasons = meta.videos.orEmpty().mapNotNull { it.season?.takeIf { s -> s > 0 } }.distinct()
        return base.copy(
            title = meta.name?.takeIf { it.isNotBlank() } ?: base.title,
            overview = meta.description?.takeIf { it.isNotBlank() } ?: base.overview,
            image = meta.poster?.takeIf { it.isNotBlank() } ?: base.image,
            backdrop = meta.background?.takeIf { it.isNotBlank() } ?: base.backdrop,
            year = yearOf(meta.releaseInfo ?: meta.year ?: meta.released)
                ?: yearOf(meta.videos?.mapNotNull { it.released }?.minOrNull())
                ?: base.year,
            // For TV, MediaItem.totalEpisodes carries the season count (see TMDB mapping).
            totalEpisodes = if (mediaType == MediaType.TV) seasons.maxOrNull()?.coerceAtLeast(1) ?: 1 else null
        )
    }

    suspend fun episodes(mediaId: Int, season: Int): List<Episode> {
        val entry = entry(mediaId) ?: return emptyList()
        val meta = loadMeta(mediaId, entry) ?: return emptyList()
        return meta.videos.orEmpty()
            .filter { it.season == season && it.episode != null }
            .sortedBy { it.episode }
            .map { video ->
                Episode(
                    // Episode ids only need to be unique within the show for the UI.
                    id = (video.id ?: "$season:${video.episode}").hashCode(),
                    episodeNumber = video.episode ?: 0,
                    seasonNumber = season,
                    name = video.title?.takeIf { it.isNotBlank() } ?: video.name.orEmpty(),
                    overview = video.overview ?: video.description.orEmpty(),
                    stillPath = video.thumbnail,
                    airDate = video.released?.take(10).orEmpty()
                )
            }
    }

    private fun entry(mediaId: Int): Entry? {
        if (mediaId >= 0) return null
        ensureRegistryLoaded()
        return registry[mediaId]
    }

    private suspend fun loadMeta(mediaId: Int, entry: Entry): StremioMetaPreview? {
        flush()
        metas[mediaId]?.takeIf { System.currentTimeMillis() - it.loadedAtMs < META_TTL_MS }?.let { return it.meta }
        val meta = runCatching {
            streamRepository.getAddonMeta(entry.addonId, entry.type, entry.metaId)
        }.onFailure { Log.w(TAG, "meta for ${entry.metaId} failed: ${it.message}") }.getOrNull()
        if (meta != null) {
            metas[mediaId] = CachedMeta(meta, System.currentTimeMillis())
        }
        return meta ?: metas[mediaId]?.meta
    }

    private fun Entry.toMediaItem(id: Int, mediaType: MediaType) = MediaItem(
        id = id,
        title = title,
        overview = description.orEmpty(),
        mediaType = mediaType,
        image = poster.orEmpty(),
        backdrop = background,
        addonNativeId = metaId,
        addonNativeAddonId = addonId
    )

    private fun ensureRegistryLoaded() {
        if (registryLoaded) return
        synchronized(this) {
            if (registryLoaded) return
            runCatching {
                val file = registryFile
                if (file.exists() && file.length() in 1..MAX_REGISTRY_BYTES) {
                    val type = object : TypeToken<Map<String, Entry>>() {}.type
                    val saved: Map<String, Entry> = gson.fromJson(file.readText(), type) ?: emptyMap()
                    saved.forEach { (key, value) -> key.toIntOrNull()?.let { registry.putIfAbsent(it, value) } }
                }
            }.onFailure { Log.w(TAG, "could not read native item registry: ${it.message}") }
            registryLoaded = true
        }
    }

    @Synchronized
    private fun persistRegistry() {
        runCatching {
            // Keep the file bounded; the oldest-inserted entries go first.
            while (registry.size > MAX_ENTRIES) registry.keys.firstOrNull()?.let { registry.remove(it) } ?: break
            val snapshot = registry.entries.associate { (key, value) -> key.toString() to value }
            val tmp = File(registryFile.parentFile, "$REGISTRY_FILE.tmp")
            tmp.writeText(gson.toJson(snapshot))
            if (!tmp.renameTo(registryFile)) {
                registryFile.writeText(tmp.readText())
                tmp.delete()
            }
        }.onFailure { Log.w(TAG, "could not save native item registry: ${it.message}") }
    }

    private fun yearOf(text: String?): String? = text?.let { YEAR.find(it)?.value }

    companion object {
        private const val TAG = "AddonNativeCatalog"
        private const val REGISTRY_FILE = "addon_native_items.json"
        private const val MAX_ENTRIES = 4000
        private const val MAX_REGISTRY_BYTES = 4_000_000L
        private const val META_TTL_MS = 30 * 60 * 1000L
        private val YEAR = Regex("""(19|20)\d{2}""")
    }
}
