package com.arflix.tv.ui.components

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.arflix.tv.R

internal fun youtubeTrailerUrl(key: String): String? =
    key.takeIf { it.matches(Regex("[A-Za-z0-9_-]{11}")) }
        ?.let { "https://www.youtube.com/watch?v=$it" }

/** Keep YouTube's own playback surface, including controls and rights restrictions. */
@Composable
fun OpenYouTubeTrailer(youtubeKey: String, onFinished: () -> Unit) {
    val context = LocalContext.current
    LaunchedEffect(youtubeKey) {
        try {
            val url = youtubeTrailerUrl(youtubeKey)
            if (url == null) {
                Toast.makeText(context, R.string.trailer_open_failed, Toast.LENGTH_LONG).show()
            } else {
                context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                })
            }
        } catch (_: ActivityNotFoundException) {
            Toast.makeText(context, R.string.trailer_open_failed, Toast.LENGTH_LONG).show()
        } catch (_: SecurityException) {
            Toast.makeText(context, R.string.trailer_open_failed, Toast.LENGTH_LONG).show()
        } finally {
            // Recomposition after returning from YouTube must not relaunch the trailer.
            onFinished()
        }
    }
}
