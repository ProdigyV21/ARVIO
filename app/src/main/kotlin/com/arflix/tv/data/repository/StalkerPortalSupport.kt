package com.arflix.tv.data.repository

import com.arflix.tv.data.model.IptvChannel
import com.arflix.tv.data.model.PlaylistGroupKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.net.URI
import java.util.Locale

/** Pseudo playlist id for the Stalker/Ministra portal source. */
const val STALKER_PLAYLIST_ID = "stalker"

/** Maximum number of Stalker portals a user can configure. */
const val MAX_STALKER_PORTALS = 3

/**
 * Pure helpers for the Stalker multi-portal model. Kept dependency-free so they
 * can be unit-tested without an Android context.
 */
internal object StalkerPortalSupport {

    private val gson = Gson()

    /** Hosts a portal placeholder points at; none of them can serve a stream to a device. */
    private val UNROUTABLE_STREAM_HOSTS = setOf("localhost", "127.0.0.1", "0.0.0.0", "::1", "[::1]")

    /**
     * Channel ids use the `stalker:<portalId>:<origId>` shape. Returns the
     * portal id segment, or null when the id is not a Stalker channel or the
     * portal segment is missing.
     */
    fun portalIdFromChannelId(channelId: String): String? {
        if (!channelId.startsWith("stalker:")) return null
        val afterPrefix = channelId.removePrefix("stalker:")
        val portalId = afterPrefix.substringBefore(':', missingDelimiterValue = "")
        return portalId.takeIf { it.isNotBlank() }
    }

    /** Returns the owning source id for playlist/group and cache scoping. */
    fun playlistIdFromChannelId(channelId: String): String {
        return portalIdFromChannelId(channelId)
            ?: channelId.substringBefore(':').trim()
    }

    /**
     * Portals that require `create_link` publish an unroutable placeholder as the
     * channel's `cmd` (`ffmpeg http://localhost/ch/1234_`, alongside
     * `use_http_tmp_link: 1`). Such an address can never play, so it must not be used
     * as a fallback when the link call fails. A `cmd` that names a real host is a
     * legitimate direct address and stays usable.
     */
    fun isRoutableStreamAddress(url: String): Boolean {
        val trimmed = url.trim()
        if (trimmed.isBlank()) return false
        val host = runCatching { URI(trimmed).host }.getOrNull().orEmpty().lowercase(Locale.US)
        if (host.isBlank()) return false
        return host !in UNROUTABLE_STREAM_HOSTS
    }

    /**
     * Strips the player command a portal writes in front of an address. Measured
     * portals use `ffmpeg http://…` and `auto http://…`, so the leading word is
     * dropped whatever it says; a bare address (or one whose first word already
     * carries the scheme) is returned untouched.
     */
    fun sanitizePlaybackCommand(command: String?): String {
        val trimmed = command?.trim().orEmpty()
        if (trimmed.isEmpty()) return ""
        val firstWord = trimmed.substringBefore(' ')
        if (firstWord.length == trimmed.length || "://" in firstWord) return trimmed
        return trimmed.substringAfter(' ').trim()
    }

    /**
     * True when an address can go straight to the player: an absolute http(s) URL on a
     * reachable host, with no portal command word in front of it and not shaped like the
     * `create_link` placeholder.
     */
    fun isDirectStreamAddress(url: String): Boolean {
        val trimmed = url.trim()
        if (trimmed.isEmpty() || trimmed.any { it.isWhitespace() }) return false
        val scheme = trimmed.substringBefore("://", missingDelimiterValue = "").lowercase(Locale.US)
        if (scheme != "http" && scheme != "https") return false
        // The placeholder is "http://<host>/ch/<id>_" — a path ending in an underscore.
        // A play_token can end that way too, so only the path is judged, never the query.
        if (trimmed.substringBefore('?').substringBefore('#').endsWith("_")) return false
        return isRoutableStreamAddress(trimmed)
    }

    /**
     * Stalker channels carry either a ready-to-play address or a placeholder that has to
     * be exchanged for a temporary link via `create_link`; the `*_tmp_link` flags say
     * which. Portals that answer `use_http_tmp_link: 0` publish the complete address in
     * `cmd` — asking such a portal for a link is not just a wasted round trip: one
     * measured portal answers it with HTTP 200, `"error": ""` and an address whose
     * `stream=` id is empty, which then fails to play. A full client skips the call there.
     *
     * Returns the playable address when the portal stated that no temporary link is
     * needed and what it published really is one. Null keeps the `create_link` round
     * trip, including when a portal states nothing at all — guessing there would change
     * behaviour for portals nobody has measured.
     */
    fun directLiveStreamUrl(
        cmd: String?,
        useHttpTmpLink: String?,
        wowzaTmpLink: String?,
        flussonicTmpLink: String?,
    ): String? {
        if (statesTemporaryLink(useHttpTmpLink) ||
            statesTemporaryLink(wowzaTmpLink) ||
            statesTemporaryLink(flussonicTmpLink)
        ) {
            return null
        }
        if (useHttpTmpLink?.trim() != "0") return null
        return sanitizePlaybackCommand(cmd).takeIf { isDirectStreamAddress(it) }
    }

    /** Portals send the flags as `1`/`0`, quoted or not; anything else states nothing. */
    private fun statesTemporaryLink(flag: String?): Boolean = flag?.trim() == "1"

    fun canPlayDirectLiveStream(channel: IptvChannel, rawUrl: String, isCatchup: Boolean): Boolean =
        !isCatchup && channel.stalkerDirectStream && isDirectStreamAddress(rawUrl)

    fun streamCacheKey(channelId: String, command: String): String {
        return "${playlistIdFromChannelId(channelId)}|${command.trim()}"
    }

    /** Upgrades the old `stalker:<channelId>` shape to Portal 1. */
    fun migrateLegacyChannelId(channelId: String): String {
        val normalized = channelId.trim()
        if (!normalized.startsWith("stalker:") || portalIdFromChannelId(normalized) != null) {
            return normalized
        }
        val originalId = normalized.removePrefix("stalker:")
        return if (originalId.isBlank()) normalized else "stalker:stalker1:$originalId"
    }

    /** Upgrades old single-portal group keys from `stalker|group` to Portal 1. */
    fun migrateLegacyPlaylistGroupKey(rawKey: String): String {
        val normalized = rawKey.trim()
        if ('|' !in normalized) return normalized
        val key = PlaylistGroupKey(normalized)
        return if (key.playlistId == STALKER_PLAYLIST_ID) {
            PlaylistGroupKey.build("stalker1", key.groupName)
        } else {
            normalized
        }
    }

    fun normalizePlaylistGroupKeys(
        keys: Collection<String>,
        validSourceIds: Set<String> = emptySet(),
    ): List<String> {
        return keys.asSequence()
            .map(::migrateLegacyPlaylistGroupKey)
            .filter { key ->
                key.isNotBlank() &&
                    (validSourceIds.isEmpty() || ('|' in key && PlaylistGroupKey(key).playlistId in validSourceIds))
            }
            .distinct()
            .toList()
    }

    fun nextAvailablePortalId(existingIds: Collection<String>, maxPortals: Int): String? {
        val used = existingIds.mapTo(HashSet()) { it.trim() }
        if (maxPortals <= 0 || used.size >= maxPortals) return null
        var suffix = used.asSequence()
            .mapNotNull { id -> id.removePrefix("stalker").toIntOrNull() }
            .maxOrNull()
            ?.plus(1)
            ?: 1
        while ("stalker$suffix" in used) suffix += 1
        return "stalker$suffix"
    }

    fun normalizeStalkerPortalEntry(
        portal: StalkerPortalEntry,
        index: Int
    ): StalkerPortalEntry? {
        val portalUrl = runCatching { portal.portalUrl }.getOrNull().orEmpty().trim().trimEnd('/')
        val macAddress = runCatching { portal.macAddress }.getOrNull().orEmpty().trim().uppercase()
        if (portalUrl.isBlank() || macAddress.isBlank()) return null
        return StalkerPortalEntry(
            id = runCatching { portal.id }.getOrNull().orEmpty().trim().ifBlank { "stalker${index + 1}" },
            name = runCatching { portal.name }.getOrNull().orEmpty().trim().ifBlank { "Portal ${index + 1}" },
            portalUrl = portalUrl,
            macAddress = macAddress,
            enabled = runCatching { portal.enabled }.getOrDefault(true)
        )
    }

    fun decodeStalkerPortals(raw: String, maxPortals: Int): List<StalkerPortalEntry> {
        if (raw.isBlank()) return emptyList()
        return runCatching {
            val type = TypeToken.getParameterized(List::class.java, StalkerPortalEntry::class.java).type
            normalizeStalkerPortals(
                gson.fromJson<List<StalkerPortalEntry>>(raw, type).orEmpty(),
                maxPortals,
            )
        }.getOrDefault(emptyList())
    }

    fun normalizeStalkerPortals(
        portals: List<StalkerPortalEntry>,
        maxPortals: Int,
    ): List<StalkerPortalEntry> {
        if (maxPortals <= 0) return emptyList()
        val usedIds = LinkedHashSet<String>()
        return buildList {
            portals.forEachIndexed { index, rawPortal ->
                if (size >= maxPortals) return@forEachIndexed
                val portal = normalizeStalkerPortalEntry(rawPortal, index) ?: return@forEachIndexed
                val id = portal.id.takeIf { it !in usedIds }
                    ?: nextAvailablePortalId(usedIds, maxPortals)
                    ?: return@forEachIndexed
                usedIds += id
                add(portal.copy(id = id))
            }
        }
    }

    /**
     * Builds the migrated Portal 1 entry from legacy single-portal fields.
     */
    fun migratedPortalFromLegacy(portalUrl: String, macAddress: String): StalkerPortalEntry? {
        val url = portalUrl.trim().trimEnd('/')
        val mac = macAddress.trim().uppercase()
        if (url.isBlank() || mac.isBlank()) return null
        return StalkerPortalEntry(
            id = "stalker1",
            name = "Portal 1",
            portalUrl = url,
            macAddress = mac
        )
    }
}
