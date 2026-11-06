@file:androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)

package com.arflix.tv.ui.screens.tv.live.aisubs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.text.Cue
import androidx.media3.ui.SubtitleView
import androidx.tv.material3.Text
import com.arflix.tv.ui.screens.player.applySubtitleAppearance

/** The profile's subtitle look, as the VOD player uses it. */
internal data class LiveSubtitleStyle(
    val sizePreference: String = "Medium",
    val sizePercent: Int = 100,
    val verticalPercent: Int = 2,
    val color: String = "White",
    val style: String = "Bold",
    val font: String = "",
)

/**
 * Fullscreen layer over the Live TV picture: the current AI subtitle (drawn by Media3's
 * SubtitleView with the profile's subtitle style, so right-to-left languages lay out natively),
 * the "Preparing subtitles… N s" note while playback is held.
 */
@Composable
internal fun LiveAiSubtitleOverlay(
    cue: LiveCue?,
    style: LiveSubtitleStyle,
    /** "Preparing subtitles… N s" while playback is held for the delay, or null. */
    preparingText: String?,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.fillMaxSize()) {
        AndroidView(
            factory = { context ->
                SubtitleView(context).apply {
                    isFocusable = false
                    applyStyle(this, style)
                }
            },
            update = { view ->
                applyStyle(view, style)
                view.setCues(cue?.let { listOf(Cue.Builder().setText(it.text).build()) } ?: emptyList())
            },
            modifier = Modifier.fillMaxSize(),
        )
        if (preparingText != null) {
            Text(
                text = preparingText,
                color = Color.White,
                fontSize = 16.sp,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 32.dp)
                    .background(Color.Black.copy(alpha = 0.65f), RoundedCornerShape(18.dp))
                    .padding(horizontal = 18.dp, vertical = 8.dp),
            )
        }
    }
}

private fun applyStyle(view: SubtitleView, style: LiveSubtitleStyle) {
    view.applySubtitleAppearance(
        context = view.context,
        sizePreference = style.sizePreference,
        sizePercent = style.sizePercent,
        verticalPercent = style.verticalPercent,
        colorPreference = style.color,
        stylePreference = style.style,
        fontPreference = style.font,
        preserveEmbeddedStyles = false,
        inPictureInPicture = false,
    )
}
