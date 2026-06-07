package com.arflix.tv.util

import androidx.media3.common.C
import java.util.UUID

/**
 * Utility for DRM scheme resolution and ClearKey JWKS construction.
 *
 * ClearKey licenses are served inline as `data:` URIs containing a JSON Web
 * Key Set (JWKS). This avoids the need for a custom [MediaDrmCallback] — ExoPlayer's
 * [DataSchemeDataSource] reads the response directly from the URI.
 */
object ClearKeyUtil {

    /**
     * Maps a Kodi-style `inputstream.adaptive.license_type` value to the
     * corresponding DRM scheme [UUID] used by Media3/ExoPlayer.
     */
    fun drmSchemeToUuid(scheme: String): UUID = when (scheme.lowercase().trim()) {
        "clearkey", "org.w3.clearkey" -> C.CLEARKEY_UUID
        "widevine", "com.widevine.alpha" -> C.WIDEVINE_UUID
        "playready", "com.microsoft.playready" -> C.PLAYREADY_UUID
        else -> try {
            UUID.fromString(scheme)
        } catch (_: IllegalArgumentException) {
            C.WIDEVINE_UUID // safe default
        }
    }

    /**
     * Normalises a Kodi `license_type` string to a short canonical name.
     */
    fun normalizeScheme(raw: String): String = when (raw.lowercase().trim()) {
        "org.w3.clearkey", "clearkey" -> "clearkey"
        "com.widevine.alpha", "widevine" -> "widevine"
        "com.microsoft.playready", "playready" -> "playready"
        else -> raw.lowercase().trim()
    }

    /**
     * Builds a `data:` URI containing a JSON Web Key Set (JWKS) suitable for
     * ExoPlayer's ClearKey licence acquisition.
     *
     * @param kidKeyHex A colon-separated hex pair `kid:key`, for example
     *   `9eb4050de44b4802932e27d75083e266:166634c675823c235a4a9446fad52e4d`.
     * @return A `data:application/json;base64,...` URI, or `null` if parsing fails.
     *
     * Per the W3C ClearKey spec, the `kid` and `k` values inside the JWK must be
     * Base64Url-encoded **without padding**.
     */
    fun buildClearKeyLicenseUri(kidKeyHex: String): String? {
        val parts = kidKeyHex.split(':')
        if (parts.size != 2) return null
        val kidHex = parts[0].trim()
        val keyHex = parts[1].trim()
        if (kidHex.length != 32 || keyHex.length != 32) return null

        val kidB64 = hexToBase64Url(kidHex) ?: return null
        val keyB64 = hexToBase64Url(keyHex) ?: return null

        // JSON Web Key Set per https://www.w3.org/TR/encrypted-media/#clear-key-license-format
        val jwks = """{"keys":[{"kty":"oct","kid":"$kidB64","k":"$keyB64"}],"type":"temporary"}"""

        val encoded = android.util.Base64.encodeToString(
            jwks.toByteArray(Charsets.UTF_8),
            android.util.Base64.NO_WRAP or android.util.Base64.NO_PADDING
        )
        return "data:application/json;base64,$encoded"
    }

    /**
     * Converts a 32-char hex string (16 bytes) to a Base64Url string without padding.
     */
    private fun hexToBase64Url(hex: String): String? {
        if (hex.length % 2 != 0) return null
        val bytes = try {
            ByteArray(hex.length / 2) { i ->
                hex.substring(i * 2, i * 2 + 2).toInt(16).toByte()
            }
        } catch (_: NumberFormatException) {
            return null
        }
        return android.util.Base64.encodeToString(
            bytes,
            android.util.Base64.URL_SAFE or android.util.Base64.NO_WRAP or android.util.Base64.NO_PADDING
        )
    }
}
