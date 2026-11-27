package com.arflix.tv.ui.screens.player

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri

/**
 * Hands a resolved stream to another video app (VLC, MX Player, Just Player, …) when
 * Settings > Playback "External player" is on. Request headers go along in the
 * `headers` extra (key, value, key, value) that those players read.
 */
internal object ExternalPlayerLauncher {
    fun headerExtras(headers: Map<String, String>): Array<String> =
        headers.flatMap { (name, value) -> listOf(name, value) }.toTypedArray()

    /** Returns false when no app on the device can play the stream. */
    fun launch(context: Context, url: String, title: String?, headers: Map<String, String>, startPositionMs: Long): Boolean {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            setDataAndType(Uri.parse(url), "video/*")
            title?.takeIf { it.isNotBlank() }?.let {
                putExtra("title", it)
                putExtra(Intent.EXTRA_TITLE, it)
            }
            if (headers.isNotEmpty()) putExtra("headers", headerExtras(headers))
            if (startPositionMs > 0L) {
                putExtra("position", startPositionMs.coerceAtMost(Int.MAX_VALUE.toLong()).toInt())
            }
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        return try {
            // Android asks which app to use unless the user already picked a default.
            context.startActivity(intent)
            true
        } catch (_: ActivityNotFoundException) {
            false
        } catch (_: SecurityException) {
            false
        }
    }
}
