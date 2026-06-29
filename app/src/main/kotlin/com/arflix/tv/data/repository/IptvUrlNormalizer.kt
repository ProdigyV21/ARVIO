package com.arflix.tv.data.repository

import android.util.Base64
import java.nio.charset.StandardCharsets

/**
 * Normalizes legacy IPTV URLs that may have been stored before validation was
 * added. Some older cached rows contain base64-encoded http(s) values; passing
 * those raw strings to Coil/OkHttp can crash the TV page with "no scheme".
 */
internal fun normalizeIptvStreamUrl(raw: String): String {
    val trimmed = raw.trim()
    if (trimmed.isBlank()) return ""
    if (trimmed.startsWith("//")) return "https:$trimmed"
    if (trimmed.contains("://", ignoreCase = true)) return trimmed
    decodeLegacyHttpUrl(trimmed)?.let { return it }
    return trimmed
}

internal fun normalizeIptvLogoUrlOrNull(raw: String?): String? {
    val trimmed = raw?.trim().orEmpty()
    if (trimmed.isBlank()) return null
    val normalized = when {
        trimmed.startsWith("http://", ignoreCase = true) ||
            trimmed.startsWith("https://", ignoreCase = true) -> trimmed
        trimmed.startsWith("//") -> "https:$trimmed"
        else -> decodeLegacyHttpUrl(trimmed)
    } ?: return null
    return normalized.takeIf {
        it.startsWith("http://", ignoreCase = true) ||
            it.startsWith("https://", ignoreCase = true)
    }
}

private fun decodeLegacyHttpUrl(value: String): String? {
    if (value.length < 12 || value.any { it.isWhitespace() }) return null
    return listOf(Base64.DEFAULT, Base64.URL_SAFE or Base64.NO_WRAP)
        .asSequence()
        .mapNotNull { flags ->
            runCatching {
                String(Base64.decode(value, flags), StandardCharsets.UTF_8).trim()
            }.getOrNull()
        }
        .firstOrNull { decoded ->
            decoded.startsWith("http://", ignoreCase = true) ||
                decoded.startsWith("https://", ignoreCase = true)
        }
}
