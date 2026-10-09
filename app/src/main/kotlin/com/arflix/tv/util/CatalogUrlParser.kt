package com.arflix.tv.util

import com.arflix.tv.data.model.CatalogSourceType
import java.net.URI
import java.util.Locale

sealed class ParsedCatalogUrl {
    data class TraktUserList(val username: String, val listId: String) : ParsedCatalogUrl()
    data class TraktList(val listId: String) : ParsedCatalogUrl()
    data class Mdblist(val url: String) : ParsedCatalogUrl()
    data class SimklList(val id: Long, val ownerId: Long? = null) : ParsedCatalogUrl() {
        val canonicalUrl: String get() = if (ownerId != null) "https://simkl.com/$ownerId/list/$id" else "https://simkl.com/lists/$id"
    }

    /**
     * A TMDB website page used as a catalog: `kind` is list / collection /
     * company / network / person / keyword / genre, and `mediaType` is the
     * optional "movie" or "tv" suffix those pages carry.
     */
    data class Tmdb(
        val kind: String,
        val id: Int,
        val mediaType: String?,
        val slug: String?
    ) : ParsedCatalogUrl()
}

private val LANGUAGE_SEGMENT_REGEX = Regex("^[a-z]{2}(-[A-Za-z]{2})?$")

data object CatalogUrlParser {
    fun normalize(raw: String): String {
        val trimmed = raw.trim()
        if (trimmed.isEmpty()) return trimmed
        val withScheme = if (trimmed.startsWith("http://") || trimmed.startsWith("https://")) {
            trimmed
        } else {
            "https://$trimmed"
        }
        return withScheme.removeSuffix("/")
    }

    fun detectSource(url: String): CatalogSourceType? {
        val normalized = normalize(url)
        return when {
            isTraktHost(normalized) -> CatalogSourceType.TRAKT
            isSimklHost(normalized) -> CatalogSourceType.SIMKL
            isMdblistHost(normalized) -> CatalogSourceType.MDBLIST
            isTmdbHost(normalized) -> CatalogSourceType.TMDB
            else -> null
        }
    }

    fun parse(url: String): ParsedCatalogUrl? {
        val normalized = normalize(url)
        return when (detectSource(normalized)) {
            CatalogSourceType.TRAKT -> parseTrakt(normalized)
            CatalogSourceType.SIMKL -> parseSimkl(normalized)
            CatalogSourceType.MDBLIST -> ParsedCatalogUrl.Mdblist(normalized)
            CatalogSourceType.TMDB -> parseTmdb(normalized)
            else -> null
        }
    }

    fun parseTrakt(url: String): ParsedCatalogUrl? {
        val uri = try { URI(normalize(url)) } catch (_: Exception) { null } ?: return null
        if (!isTraktHost(uri.host ?: return null)) return null
        val parts = uri.path.trim('/').split('/').filter { it.isNotBlank() }
        if (parts.size >= 4 && parts[0] == "users" && parts[2] == "lists") {
            return ParsedCatalogUrl.TraktUserList(parts[1], parts[3])
        }
        if (parts.size >= 2 && parts[0] == "lists") {
            return ParsedCatalogUrl.TraktList(parts[1])
        }
        return null
    }

    fun parseSimkl(url: String): ParsedCatalogUrl.SimklList? {
        val uri = try { URI(normalize(url)) } catch (_: Exception) { null } ?: return null
        if (!isSimklHost(uri.host ?: return null) || uri.userInfo != null || uri.scheme !in setOf("http", "https")) return null
        val parts = uri.path.trim('/').split('/').filter { it.isNotBlank() }
        val owner = parts.firstOrNull()?.toLongOrNull()?.takeIf { it > 0 }
        val id = when {
            owner != null && parts.getOrNull(1) == "list" -> parts.getOrNull(2)
            parts.firstOrNull() == "lists" -> parts.getOrNull(1)
            else -> null
        }?.toLongOrNull()?.takeIf { it > 0 } ?: return null
        return ParsedCatalogUrl.SimklList(id, owner)
    }

    private fun isSimklHost(urlOrHost: String): Boolean {
        val host = if (urlOrHost.contains("://")) {
            try { URI(urlOrHost).host.orEmpty() } catch (_: Exception) { "" }
        } else urlOrHost
        return host.equals("simkl.com", true) || host.equals("www.simkl.com", true)
    }

    /** Media types TMDB pages can be scoped to. */
    private val TMDB_MEDIA_TYPES = setOf("movie", "tv")

    /** Page kinds we can turn into a catalog row. */
    val TMDB_KINDS = setOf("list", "collection", "company", "network", "person", "keyword", "genre")

    /**
     * True for a Stremio addon manifest link. Those carry catalogs too, so users
     * reasonably paste them into "Add catalog"; the caller routes them to the
     * addon installer instead of rejecting them.
     */
    fun isStremioManifestUrl(raw: String): Boolean {
        val trimmed = raw.trim()
        if (trimmed.startsWith("stremio://", ignoreCase = true)) return true
        val path = try {
            URI(normalize(trimmed)).path.orEmpty()
        } catch (_: Exception) {
            return false
        }
        return path.endsWith("/manifest.json", ignoreCase = true) ||
            path.equals("/manifest.json", ignoreCase = true)
    }

    fun parseTmdb(url: String): ParsedCatalogUrl.Tmdb? {
        val uri = try { URI(normalize(url)) } catch (_: Exception) { null } ?: return null
        if (!isTmdbHost(uri.host ?: return null)) return null
        val parts = uri.path.trim('/').split('/').filter { it.isNotBlank() }
        // Some locales prefix the path with a language segment, e.g. /en-US/list/123.
        val offset = if (parts.firstOrNull()?.matches(LANGUAGE_SEGMENT_REGEX) == true) 1 else 0
        val kind = parts.getOrNull(offset)?.lowercase(Locale.US) ?: return null
        if (kind !in TMDB_KINDS) return null
        // The id segment is either "1241" or "1241-harry-potter-collection".
        val idSegment = parts.getOrNull(offset + 1) ?: return null
        val id = idSegment.substringBefore('-').toIntOrNull()?.takeIf { it > 0 } ?: return null
        val slug = idSegment.substringAfter('-', "").takeIf { it.isNotBlank() }
        val mediaType = parts.drop(offset + 2)
            .map { it.lowercase(Locale.US) }
            .firstOrNull { it in TMDB_MEDIA_TYPES }
        return ParsedCatalogUrl.Tmdb(kind = kind, id = id, mediaType = mediaType, slug = slug)
    }

    private fun isTmdbHost(urlOrHost: String): Boolean {
        val host = if (urlOrHost.contains("://")) {
            try { URI(urlOrHost).host.orEmpty() } catch (_: Exception) { "" }
        } else {
            urlOrHost
        }.lowercase()
        return host == "themoviedb.org" || host.endsWith(".themoviedb.org")
    }

    private fun isTraktHost(urlOrHost: String): Boolean {
        val host = if (urlOrHost.contains("://")) {
            try { URI(urlOrHost).host.orEmpty() } catch (_: Exception) { "" }
        } else {
            urlOrHost
        }.lowercase()
        return host == "trakt.tv" || host.endsWith(".trakt.tv")
    }

    private fun isMdblistHost(urlOrHost: String): Boolean {
        val host = if (urlOrHost.contains("://")) {
            try { URI(urlOrHost).host.orEmpty() } catch (_: Exception) { "" }
        } else {
            urlOrHost
        }.lowercase()
        return host == "mdblist.com" || host.endsWith(".mdblist.com")
    }
}
