package com.arflix.tv.util

import com.google.gson.JsonObject
import com.google.gson.JsonParser

/**
 * Reads the newer inputstream.adaptive DRM properties of an M3U `#KODIPROP` line into the
 * same `license_type` / `license_key` pair the older `license_type=` / `license_key=` lines give:
 *
 * - `inputstream.adaptive.drm_legacy=org.w3.clearkey|<kid>:<key>` (or `<scheme>|<license url>`)
 * - `inputstream.adaptive.drm={"org.w3.clearkey":{"license":{"keyids":{"<kid>":"<key>"}}}}`
 *   or `{"com.widevine.alpha":{"license":{"server_url":"https://…"}}}`
 *
 * Returns null for any other line, or when the value cannot be read.
 */
object KodiDrmProps {
    private const val LEGACY = "inputstream.adaptive.drm_legacy="
    private const val JSON = "inputstream.adaptive.drm="

    fun parse(value: String): Map<String, String>? = when {
        value.startsWith(LEGACY, ignoreCase = true) -> parseLegacy(value.substring(LEGACY.length).trim())
        value.startsWith(JSON, ignoreCase = true) -> parseJson(value.substring(JSON.length).trim())
        else -> null
    }

    private fun parseLegacy(raw: String): Map<String, String>? {
        val parts = raw.split('|')
        val scheme = parts.getOrNull(0)?.trim().orEmpty()
        val license = parts.getOrNull(1)?.trim().orEmpty()
        if (scheme.isBlank()) return null
        return buildMap {
            put("license_type", scheme)
            firstLicense(scheme, license)?.let { put("license_key", it) }
        }
    }

    private fun parseJson(raw: String): Map<String, String>? {
        val root = runCatching { JsonParser.parseString(raw) }.getOrNull()
            ?.takeIf { it.isJsonObject }?.asJsonObject ?: return null
        val (scheme, config) = root.entrySet().firstOrNull { it.value.isJsonObject } ?: return null
        val license = config.asJsonObject.objectOrNull("license")
        val key = if (ClearKeyUtil.normalizeScheme(scheme) == "clearkey") {
            license?.objectOrNull("keyids")?.entrySet()?.firstOrNull { it.value.isJsonPrimitive }
                ?.let { (kid, k) -> "${kid.trim()}:${k.asString.trim()}" }
        } else {
            license?.get("server_url")?.takeIf { it.isJsonPrimitive }?.asString?.trim()
        }
        return buildMap {
            put("license_type", scheme)
            key?.takeIf { it.isNotBlank() }?.let { put("license_key", it) }
        }
    }

    /** ClearKey lines may list several `kid:key` pairs; the player takes one. */
    private fun firstLicense(scheme: String, license: String): String? {
        if (license.isBlank()) return null
        return if (ClearKeyUtil.normalizeScheme(scheme) == "clearkey") {
            license.split(',').map { it.trim() }.firstOrNull { it.isNotBlank() }
        } else license
    }

    private fun JsonObject.objectOrNull(name: String): JsonObject? =
        get(name)?.takeIf { it.isJsonObject }?.asJsonObject
}
