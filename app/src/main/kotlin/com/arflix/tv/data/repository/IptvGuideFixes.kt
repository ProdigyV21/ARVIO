package com.arflix.tv.data.repository

import com.arflix.tv.data.model.IptvNowNext
import com.arflix.tv.data.model.IptvProgram

/**
 * The user's own guide corrections, synced per profile
 * (`iptvByProfile.<id>.guideSources` / `guideOffsets`, the same keys ARVIO Web writes).
 *
 * - [sources]: channel id -> the channel whose guide it shows instead, or
 *   [GUIDE_SOURCE_NONE] to show no guide at all (for a guide that is plainly wrong).
 *   A channel without an entry keeps the guide the provider matched.
 * - [offsetMinutes]: playlist (or portal) id -> minutes added to every programme time
 *   of that source, for a guide that runs early or late.
 *
 * The raw guide data is never rewritten: corrections are applied where the guide is
 * shown, so changing or removing one takes effect at once and can always be undone.
 */
data class IptvGuideFixes(
    val sources: Map<String, String> = emptyMap(),
    val offsetMinutes: Map<String, Int> = emptyMap(),
) {
    val isEmpty: Boolean get() = sources.isEmpty() && offsetMinutes.isEmpty()

    /** The channel whose guide [channelId] shows, or null when it shows none. */
    fun guideChannelOf(channelId: String): String? = when (val source = sources[channelId]) {
        null -> channelId
        GUIDE_SOURCE_NONE -> null
        else -> source
    }

    fun offsetMsFor(guideChannelId: String): Long {
        if (offsetMinutes.isEmpty()) return 0L
        val playlistId = guidePlaylistIdOf(guideChannelId)
        return (offsetMinutes[playlistId] ?: 0) * 60_000L
    }

    /** The guide [channelId] should show, read from [lookup] by guide channel id. */
    fun resolve(channelId: String, lookup: (String) -> IptvNowNext?): IptvNowNext? {
        val guideId = guideChannelOf(channelId) ?: return null
        val guide = lookup(guideId) ?: return null
        return shiftGuide(guide, offsetMsFor(guideId))
    }

    /** Guide channel ids to load for [channelIds]: overridden channels load their source. */
    fun guideIdsFor(channelIds: Collection<String>): List<String> =
        if (sources.isEmpty()) channelIds.toList()
        else channelIds.mapNotNull(::guideChannelOf).distinct()

    /** Applies the corrections to a whole channel-id keyed guide map. */
    fun applyTo(guide: Map<String, IptvNowNext>, channelIds: Collection<String> = guide.keys): Map<String, IptvNowNext> {
        if (isEmpty) return guide
        val out = HashMap<String, IptvNowNext>(guide)
        sources.keys.forEach { out.remove(it) }
        val ids = if (sources.isEmpty()) channelIds else (channelIds + sources.keys).toSet()
        ids.forEach { id ->
            val resolved = resolve(id) { guide[it] }
            if (resolved != null) out[id] = resolved else out.remove(id)
        }
        return out
    }

    companion object {
        /** Stored as a channel's source when the user turned its guide off. */
        const val GUIDE_SOURCE_NONE = "none"
        /** Twelve hours either way covers every time-zone mix-up a provider can make. */
        const val MAX_OFFSET_MINUTES = 12 * 60
        const val OFFSET_STEP_MINUTES = 30
    }
}

/** M3U/Xtream ids are `<playlistId>:<id>`, Stalker ids `stalker:<portalId>:<id>`. */
internal fun guidePlaylistIdOf(channelId: String): String =
    StalkerPortalSupport.playlistIdFromChannelId(channelId)

/** Moves every programme of [guide] by [offsetMs], keeping its now/next slots by their times. */
internal fun shiftGuide(guide: IptvNowNext, offsetMs: Long): IptvNowNext {
    if (offsetMs == 0L) return guide
    fun IptvProgram.shifted() = copy(startUtcMillis = startUtcMillis + offsetMs, endUtcMillis = endUtcMillis + offsetMs)
    return IptvNowNext(
        now = guide.now?.shifted(),
        next = guide.next?.shifted(),
        later = guide.later?.shifted(),
        upcoming = guide.upcoming.map { it.shifted() },
        recent = guide.recent.map { it.shifted() },
    )
}

/** Drops blank keys/values; tolerates Gson nulls from older payloads. */
internal fun normalizeGuideSources(raw: Map<String, String>?): Map<String, String> {
    if (raw.isNullOrEmpty()) return emptyMap()
    return buildMap {
        raw.forEach { (key, value) ->
            @Suppress("SENSELESS_COMPARISON", "UNNECESSARY_SAFE_CALL")
            val channelId = key?.trim().orEmpty()
            @Suppress("SENSELESS_COMPARISON", "UNNECESSARY_SAFE_CALL")
            val source = value?.trim().orEmpty()
            if (channelId.isNotEmpty() && source.isNotEmpty() && source != channelId) put(channelId, source)
        }
    }
}

/**
 * Whole minutes, in half-hour steps, within ±12 hours; zero is dropped. Values arrive as
 * doubles from JSON (and from ARVIO Web), so anything numeric is accepted.
 */
internal fun normalizeGuideOffsets(raw: Map<String, Any?>?): Map<String, Int> {
    if (raw.isNullOrEmpty()) return emptyMap()
    return buildMap {
        raw.forEach { (key, value) ->
            @Suppress("SENSELESS_COMPARISON", "UNNECESSARY_SAFE_CALL")
            val playlistId = key?.trim().orEmpty()
            val minutes = when (value) {
                is Number -> value.toDouble()
                is String -> value.trim().toDoubleOrNull()
                else -> null
            } ?: return@forEach
            if (playlistId.isEmpty() || minutes.isNaN()) return@forEach
            val step = IptvGuideFixes.OFFSET_STEP_MINUTES
            val rounded = (Math.round(minutes / step) * step).toInt()
                .coerceIn(-IptvGuideFixes.MAX_OFFSET_MINUTES, IptvGuideFixes.MAX_OFFSET_MINUTES)
            if (rounded != 0) put(playlistId, rounded)
        }
    }
}
