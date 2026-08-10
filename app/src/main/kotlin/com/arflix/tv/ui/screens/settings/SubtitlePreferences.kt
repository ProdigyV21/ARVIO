
package com.arvio.tv.player.subtitle

import android.graphics.Color
import android.graphics.Typeface

enum class SubtitlePosition { BOTTOM, TOP }

enum class SubtitleFontSize(val sp: Float, val label: String) {
    SMALL(14f, "Small"),
    MEDIUM(18f, "Medium"),
    LARGE(22f, "Large"),
    EXTRA_LARGE(28f, "Extra Large")
}

data class SubtitlePreferences(
    val fontSize: Float = SubtitleFontSize.MEDIUM.sp,
    val textColor: Int = Color.WHITE,
    val backgroundColor: Int = Color.parseColor("#80000000"),
    val position: SubtitlePosition = SubtitlePosition.BOTTOM,
    val fontStyle: Int = Typeface.BOLD,
    val useSystemCaptions: Boolean = true   // honour Android TV CaptionManager
)