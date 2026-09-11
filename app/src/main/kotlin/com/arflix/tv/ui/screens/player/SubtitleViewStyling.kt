package com.arflix.tv.ui.screens.player

import android.content.Context
import android.graphics.Color
import android.util.TypedValue
import androidx.media3.ui.CaptionStyleCompat
import androidx.media3.ui.SubtitleView

internal fun SubtitleView.applySubtitleAppearance(
    context: Context,
    sizePreference: String,
    sizePercent: Int,
    verticalPercent: Int,
    colorPreference: String,
    stylePreference: String,
    fontPreference: String,
    preserveEmbeddedStyles: Boolean,
    inPictureInPicture: Boolean,
) {
    val baseSizeSp = 24f
    val foregroundColor = when (colorPreference) {
        "Yellow" -> Color.YELLOW
        "Green" -> Color.GREEN
        "Cyan" -> Color.CYAN
        else -> Color.WHITE
    }
    val edgeType = when (stylePreference) {
        "Normal", "Background" -> CaptionStyleCompat.EDGE_TYPE_NONE
        else -> CaptionStyleCompat.EDGE_TYPE_OUTLINE
    }
    val windowColor = if (stylePreference == "Background") {
        Color.argb(180, 0, 0, 0)
    } else {
        Color.TRANSPARENT
    }
    val typeface = resolveSubtitleTypeface(
        context = context,
        preference = fontPreference,
        bold = stylePreference != "Normal",
    )

    setStyle(
        CaptionStyleCompat(
            foregroundColor,
            Color.TRANSPARENT,
            windowColor,
            edgeType,
            Color.BLACK,
            typeface,
        )
    )
    val pipScale = if (inPictureInPicture) 0.4f else 1f
    setFixedTextSize(
        TypedValue.COMPLEX_UNIT_SP,
        baseSizeSp * (sizePercent.coerceIn(50, 250) / 100f) * pipScale,
    )
    setBottomPaddingFraction((verticalPercent / 100f).coerceIn(0.01f, 0.88f))
    val applyEmbeddedStyles = shouldPreserveEmbeddedSubtitleStyles(
        fontPreference = fontPreference,
        stylizedSubtitlesEnabled = preserveEmbeddedStyles,
    )
    setApplyEmbeddedStyles(applyEmbeddedStyles)
    setApplyEmbeddedFontSizes(applyEmbeddedStyles)
}
