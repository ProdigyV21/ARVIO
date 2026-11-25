package com.arflix.tv.ui.screens.player

import java.security.MessageDigest

/**
 * File name for a subtitle copy written to the local subtitle cache.
 *
 * The player reads the copy through a disk cache that keys on its URI, so the name has to change
 * whenever the text does. Some addons return a season-pack subtitle with the same id for every
 * episode; keyed on provider and id alone, episode 2 reused episode 1's URI and could be served
 * episode 1's cached lines. The content hash makes each distinct text its own file.
 */
internal fun localSubtitleFileName(provider: String, id: String, raw: String, extension: String): String {
    val sourceKey = "$provider|$id".hashCode().toUInt()
    val digest = MessageDigest.getInstance("SHA-256").digest(raw.toByteArray(Charsets.UTF_8))
    val contentKey = digest.take(8).joinToString("") { "%02x".format(it) }
    return "$sourceKey-$contentKey.$extension"
}
