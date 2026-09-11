package com.arflix.tv.ui.screens.tv.live

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontVariation
import com.arflix.tv.R
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arflix.tv.ui.theme.InterFontFamily

// ARVIO Live TV — design tokens. OKLCH reference kept in spec.md §2.
// Mapped from handoff/tokens.kt. `InterFontFamily` ships; JetBrains Mono
// falls back to system Monospace (Inter's tabular figures are acceptable
// for the numeric/badge slots; can swap for bundled JBMono later).

object LiveColors {
    // Unified near-black palette so the AppTopBar gradient blends cleanly
    // into the TV page. Each step lifts just a few lumens above the last —
    // enough for panels/cells to pop without creating a visible seam under
    // the top bar.
    val Bg           = Color(0xFF070809)
    val Panel        = Color(0xFF13171A)
    val PanelDeep    = Bg
    val PanelRaised  = Color(0xFF202022)
    val RowStripe    = Color(0xFF0E0E0E)

    val Divider       = Color(0x662C2C2C)
    val DividerStrong = Color(0xFF383838)

    val Fg     = Color(0xFFF5F5F8)
    val FgDim  = Color(0xFFB5B6BE)
    val FgMute = Color(0xFF7D7E86)

    // Time and playback use turquoise; selection stays neutral and focus white.
    val Accent    = Color(0xFF56D8C5)
    val AccentDim = Color(0xFF266B62)
    val FocusBg   = Color(0xFF272727)

    // Focus ring color — always pure white on TV for maximum clarity.
    val FocusRing = Color(0xFFFFFFFF)

    val LiveRed = Color(0xFFFF3B30)
    val Online  = Color(0xFF4ADE80)

    data class Brand(val bg: Color, val fg: Color)
    val BrandNews    = Brand(Color(0xFF8A2F2F), Color(0xFFFDE7D4))
    val BrandSport   = Brand(Color(0xFF0B6131), Color(0xFFEAFFF1))
    val BrandMovies  = Brand(Color(0xFF1A1A2E), Color(0xFFF5C26B))
    val BrandSeries  = Brand(Color(0xFF3A1552), Color(0xFFE9D2FF))
    val BrandKids    = Brand(Color(0xFFF3B13A), Color(0xFF1A1308))
    val BrandMusic   = Brand(Color(0xFF2A2A6E), Color(0xFFC8D4FF))
    val BrandDocs    = Brand(Color(0xFF1D3F3A), Color(0xFFCFE9E3))
    val BrandGeneral = Brand(Color(0xFF1B2B5A), Color(0xFFE8EFFB))
}

val LiveMono: FontFamily = InterFontFamily

// Set the variable font's axis, not just the weight used to select a font entry.
@OptIn(androidx.compose.ui.text.ExperimentalTextApi::class)
internal val LiveFontFamily: FontFamily = if (android.os.Build.VERSION.SDK_INT >= 26) FontFamily(
    listOf(FontWeight.Normal, FontWeight.Medium, FontWeight.SemiBold, FontWeight.Bold).map { weight ->
        Font(R.font.inter_variablefont_opsz_wght, weight = weight,
            variationSettings = FontVariation.Settings(FontVariation.weight(weight.weight)))
    }
) else InterFontFamily

object LiveType {
    // Fixed readable sizes; row geometry does not change with focus.
    val ChannelName  = TextStyle(fontFamily = InterFontFamily, fontSize = 12.sp, fontWeight = FontWeight.W600, letterSpacing = 0.sp, lineHeight = 15.sp)
    val ProgramTitle = TextStyle(fontFamily = InterFontFamily, fontSize = 10.sp, fontWeight = FontWeight.W500, letterSpacing = 0.sp, lineHeight = 13.sp)
    val CellTitle    = TextStyle(fontFamily = InterFontFamily, fontSize = 11.sp, fontWeight = FontWeight.W500, letterSpacing = 0.sp, lineHeight = 14.sp)
    val BodySynopsis = TextStyle(fontFamily = InterFontFamily, fontSize = 10.sp, fontWeight = FontWeight.W400, letterSpacing = 0.sp, lineHeight = 13.sp)
    val CatLabel     = TextStyle(fontFamily = LiveFontFamily, fontSize = 12.sp, fontWeight = FontWeight.W500, letterSpacing = 0.sp, lineHeight = 15.sp)
    val SectionTag   = TextStyle(fontFamily = InterFontFamily, fontSize = 8.sp, fontWeight = FontWeight.W600, letterSpacing = 0.sp, lineHeight = 11.sp)
    val Badge        = TextStyle(fontFamily = InterFontFamily, fontSize = 8.sp, fontWeight = FontWeight.W600, letterSpacing = 0.sp, lineHeight = 11.sp)
    val TimeMono     = TextStyle(fontFamily = InterFontFamily, fontSize = 8.sp, fontWeight = FontWeight.W500, letterSpacing = 0.sp, lineHeight = 11.sp)
    val NumberMono   = TextStyle(fontFamily = InterFontFamily, fontSize = 8.sp, fontWeight = FontWeight.W500, letterSpacing = 0.sp, lineHeight = 11.sp)
}

object LiveDims {
    // Clear the visible topbar controls without reserving its decorative fade area.
    val ContentTopInset = 74.dp
    // Fixed tracks keep focus and drawer transitions from changing row geometry.
    val SidebarExpanded  = 232.dp
    val SidebarCollapsed = 52.dp
    val SidebarRowHeight = 40.dp

    val MiniPlayerWidth  = 304.dp
    val MiniPlayerHeight = 147.dp

    val EpgChannelColWidth = 196.dp
    val EpgChannelWideColWidth = 212.dp
    val EpgRowHeight       = 32.dp
    val EpgHeaderHeight    = 32.dp
    val EpgPxPerMinute     = 4
    val EpgHalfHourWidth   = 120.dp

    val PanelRadius     = 8.dp
    val CardRadius      = 8.dp
    val CellRadius      = 3.dp
    val VideoRadius     = 4.dp
    val FocusBorder     = 2.dp
    val ActiveIndicator = 3.dp
}

val LocalLiveColors = staticCompositionLocalOf { LiveColors }
val LocalLiveType   = staticCompositionLocalOf { LiveType }
val LocalLiveDims   = staticCompositionLocalOf { LiveDims }
