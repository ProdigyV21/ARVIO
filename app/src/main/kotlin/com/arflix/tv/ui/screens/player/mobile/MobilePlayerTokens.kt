package com.arflix.tv.ui.screens.player.mobile

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.unit.dp

/**
 * Visual design tokens for ARVIO Mobile Player matching the HTML prototype.
 */
object MobilePlayerTokens {
    val InkPrimary = Color(0xFFFFFFFF)
    val InkSecondary = Color(0xB8FFFFFF)   // 72%
    val InkTertiary = Color(0x75FFFFFF)    // 46%

    val TrackBg = Color(0x47FFFFFF)        // 28%
    val TrackBuffered = Color(0x80FFFFFF)  // 50%
    val TrackFill = Color(0xFFFFFFFF)

    val PageBg = Color(0xFF0B0C10)
    val PanelBg = Color(0xFF17181C)
    val PanelBg2 = Color(0xFF1D1F24)
    val PanelBorder = Color(0x17FFFFFF)    // 9%
    val RowHover = Color(0x0DFFFFFF)       // 5%

    val ScrimColor = Color(0x40000000)     // 25%

    val ShapeScreen = RoundedCornerShape(26.dp)
    val ShapeDrawer = RoundedCornerShape(0.dp)
    val ShapeSheet = RoundedCornerShape(topStart = 18.dp, topEnd = 18.dp)
    val ShapePrompt = RoundedCornerShape(9.dp)
    val ShapeCard = RoundedCornerShape(10.dp)
    val ShapeThumb = RoundedCornerShape(7.dp)
    val ShapeBtn = RoundedCornerShape(8.dp)
    val ShapePill = RoundedCornerShape(20.dp)

    val TextShadow = Shadow(
        color = Color(0x99000000),
        offset = Offset(0f, 2f),
        blurRadius = 8f
    )
}
