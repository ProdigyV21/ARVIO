package com.arflix.tv.network

import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import java.util.Locale

/** One budget across default HTTP/HTTPS ports, for accounts and the request guard alike. */
internal fun iptvProviderHostKey(url: HttpUrl): String =
    url.host + if (url.port in setOf(80, 443)) "" else ":${url.port}"

private val STALKER_PORTAL_SUFFIXES =
    setOf("c", "stalker_portal", "portal", "ministra", "server", "load.php", "portal.php", "index.html")

private fun parseIptvUrl(url: String): HttpUrl? {
    val trimmed = url.trim()
    // A local file or content URI has a scheme of its own and no provider behind it.
    return if ("://" in trimmed) trimmed.toHttpUrlOrNull() else "http://$trimmed".toHttpUrlOrNull()
}

/**
 * Who a stream counts against. Built from config at session start and never from the URL
 * that plays, so redirects and temporary links keep their identity; scheme is excluded.
 */
@ConsistentCopyVisibility
internal data class IptvProviderAccount private constructor(val kind: Kind, private val key: String) {
    enum class Kind { XTREAM, STALKER, M3U }

    // Accounts are for in-memory comparison, never diagnostics: the key holds credentials.
    override fun toString(): String = "IptvProviderAccount(redacted)"

    companion object {
        fun xtream(baseUrl: String, username: String, password: String): IptvProviderAccount? {
            val host = parseIptvUrl(baseUrl) ?: return null
            if (username.isBlank() || password.isBlank()) return null
            return IptvProviderAccount(Kind.XTREAM, "${iptvProviderHostKey(host)}\u0000$username\u0000$password")
        }

        fun stalker(portalUrl: String, macAddress: String): IptvProviderAccount? {
            val portal = parseIptvUrl(portalUrl) ?: return null
            val mac = macAddress.filter { it.isLetterOrDigit() }.uppercase(Locale.ROOT)
            if (mac.isEmpty()) return null
            val path = portal.pathSegments.filter { it.isNotEmpty() }
                .dropLastWhile { it.lowercase(Locale.ROOT) in STALKER_PORTAL_SUFFIXES }
                .joinToString("/")
            return IptvProviderAccount(Kind.STALKER, "${iptvProviderHostKey(portal)}/$path\u0000$mac")
        }

        fun m3u(playlistUrl: String): IptvProviderAccount? {
            val playlist = parseIptvUrl(playlistUrl) ?: return null
            val query = playlist.encodedQuery.orEmpty().split('&').filter { it.isNotEmpty() }.sorted().joinToString("&")
            return IptvProviderAccount(
                Kind.M3U,
                "${iptvProviderHostKey(playlist)}${playlist.encodedPath.trimEnd('/')}?$query"
            )
        }
    }
}
