package com.arflix.tv.ui.screens.player

/**
 * Profile preference (string, default "Off"): the audio language to use when the
 * preferred one is not on the stream, like the secondary subtitle. Synced to the cloud.
 */
const val SECONDARY_AUDIO_LANGUAGE_PREF = "secondary_audio_language"

/**
 * Profile preference (boolean, default on): when a source fails, move on to the next
 * source automatically. Off keeps the player on that source and shows the failure, so
 * the user can retry it or pick another. Synced to the cloud.
 */
const val AUTO_SOURCE_FAILOVER_PREF = "auto_source_failover"

/**
 * The audio track index to select: the preferred language first, then the secondary
 * one, or null to keep what the stream chose.
 */
internal fun <T> pickAudioTrackByLanguage(
    tracks: List<T>,
    preferredCode: String,
    secondaryCode: String,
    findIndex: (List<T>, String) -> Int?,
): Int? {
    val preferred = preferredCode.trim()
    if (preferred.isNotBlank() && !preferred.equals("none", ignoreCase = true)) {
        findIndex(tracks, preferred)?.let { return it }
    }
    val secondary = secondaryCode.trim()
    if (secondary.isBlank() || secondary.equals(preferred, ignoreCase = true)) return null
    return findIndex(tracks, secondary)
}

/** Languages for the track selector, best first: preferred (unless "none"), then secondary. */
internal fun preferredAudioLanguageList(preferred: String, secondary: String): Array<String> =
    listOf(preferred.trim(), secondary.trim())
        .filter { it.isNotBlank() && !it.equals("none", ignoreCase = true) }
        .distinctBy { it.lowercase() }
        .toTypedArray()
