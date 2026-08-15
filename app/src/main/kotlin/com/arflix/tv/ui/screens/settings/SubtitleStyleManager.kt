// app/src/main/java/com/arvio/tv/player/subtitle/SubtitleStyleManager.kt
package com.arvio.tv.player.subtitle

import android.content.Context
import android.util.TypedValue
import android.view.accessibility.CaptioningManager
import androidx.media3.ui.CaptionStyleCompat
import androidx.media3.ui.SubtitleView
import android.graphics.Color

object SubtitleStyleManager {

    /**
     * Reads Android TV system caption preferences (if enabled) and falls back
     * to the app-level [SubtitlePreferences]. Applies the resolved style to
     * the provided [SubtitleView].
     */
    fun applyStyle(context: Context, subtitleView: SubtitleView, prefs: SubtitlePreferences) {
        val captionManager =
            context.getSystemService(Context.CAPTIONING_SERVICE) as? CaptioningManager

        val systemEnabled = captionManager?.isEnabled == true
        val systemStyle = if (prefs.useSystemCaptions && systemEnabled) {
            captionManager?.userStyle
        } else null

        val textColor = systemStyle?.foregroundColor?.takeIf { it != CaptioningManager.CaptionStyle.COLOR_UNSPECIFIED }
            ?: prefs.textColor
        val bgColor = systemStyle?.backgroundColor?.takeIf { it != CaptioningManager.CaptionStyle.COLOR_UNSPECIFIED }
            ?: prefs.backgroundColor
        val edgeType = systemStyle?.edgeType
            ?: CaptionStyleCompat.EDGE_TYPE_OUTLINE

        val captionStyle = CaptionStyleCompat(
            textColor,
            bgColor,
            Color.TRANSPARENT,
            edgeType,
            Color.BLACK,
            null  // default typeface
        )

        subtitleView.setStyle(captionStyle)
        subtitleView.setFixedTextSize(TypedValue.COMPLEX_UNIT_SP, prefs.fontSize)
    }
}