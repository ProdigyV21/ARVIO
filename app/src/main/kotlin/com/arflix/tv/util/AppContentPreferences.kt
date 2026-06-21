package com.arflix.tv.util

import java.util.Locale

object AppContentPreferences {
    const val DEFAULT_LANGUAGE_TAG = "sv-SE"
    const val DEFAULT_WATCH_REGION = "SE"

    fun normalizeLanguageTag(value: String?): String {
        val normalized = value
            ?.trim()
            ?.takeIf { it.isNotBlank() }
            ?.replace('_', '-')
            ?.replace("iw", "he")
            ?: DEFAULT_LANGUAGE_TAG
        val locale = Locale.forLanguageTag(normalized)
        return when {
            locale.language.isBlank() -> DEFAULT_LANGUAGE_TAG
            locale.country.isBlank() -> locale.language
            else -> "${locale.language}-${locale.country.uppercase(Locale.US)}"
        }
    }

    fun normalizeLanguageForTmdb(value: String?): String? {
        val normalized = normalizeLanguageTag(value)
        return normalized.takeUnless { it.equals("en-US", ignoreCase = true) }
    }

    fun normalizeWatchRegion(value: String?): String {
        val normalized = value?.trim()?.uppercase(Locale.US).orEmpty()
        return normalized.takeIf { it.length == 2 } ?: DEFAULT_WATCH_REGION
    }
}
