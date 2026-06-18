package com.arflix.tv.ui.skin

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Easing
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arflix.tv.ui.theme.InterFontFamily

@Immutable
data class ArvioColorTokens(
    val background: Color,
    val surface: Color,
    val surfaceRaised: Color,
    val textPrimary: Color,
    val textMuted: Color,
    val accent: Color,
    val focusOutline: Color,
    val focusGradientStart: Color,
    val focusGradientEnd: Color,
    val tealAccent: Color,
    val watchedGreen: Color,      // Green checkmark for watched items (Arctic Fuse 2 style)
    val inProgressGrey: Color,    // Grey clock for in-progress items
)

@Immutable
data class ArvioSpacingTokens(
    val x1: Dp,
    val x2: Dp,
    val x3: Dp,
    val x4: Dp,
    val x6: Dp,
    val x8: Dp,
    val x12: Dp,
    val x16: Dp,
)

@Immutable
data class ArvioRadiusTokens(
    val sm: Dp,
    val md: Dp,
    val lg: Dp,
)

@Immutable
data class ArvioTypographyTokens(
    val heroTitle: TextStyle,
    val sectionTitle: TextStyle,
    val cardTitle: TextStyle,
    val body: TextStyle,
    val caption: TextStyle,
    val badge: TextStyle,
    val button: TextStyle,
)

@Immutable
data class ArvioMotionTokens(
    val focusDurationMillis: Int,
    val focusEasing: Easing,
    val screenTransitionMillis: Int,
    val heroFadeMillis: Int,
)

@Immutable
data class ArvioFocusTokens(
    val scaleFocused: Float,
    val scalePressed: Float,
    val durationMillis: Int,
    val easing: Easing,
    val outlineWidth: Dp,
    val glowWidth: Dp,
    val glowAlpha: Float,
    val translationZFocused: Dp,
)

@Immutable
data class ArvioSkinTokens(
    val colors: ArvioColorTokens,
    val spacing: ArvioSpacingTokens,
    val radius: ArvioRadiusTokens,
    val typography: ArvioTypographyTokens,
    val motion: ArvioMotionTokens,
    val focus: ArvioFocusTokens,
) {
    companion object {
        fun defaults(): ArvioSkinTokens {
            val easeOut: Easing = CubicBezierEasing(0.0f, 0.0f, 0.2f, 1.0f)

            return ArvioSkinTokens(
                colors = ArvioColorTokens(
                    background = Color(0xFF030405),
                    surface = Color(0xFF101318),
                    surfaceRaised = Color(0xFF1B2028),
                    textPrimary = Color(0xFFF4F5F5),
                    textMuted = Color(0xB8F4F5F5),
                    accent = Color(0xFFFFD166),
                    focusOutline = Color(0xFFF8FAFA),
                    focusGradientStart = Color(0xFF58D6C9),
                    focusGradientEnd = Color(0xFFFFD166),
                    tealAccent = Color(0xFF64E6D5),
                    watchedGreen = Color(0xFF38D98B),
                    inProgressGrey = Color(0xFF8B95A1),
                ),
                spacing = ArvioSpacingTokens(
                    x1 = 4.dp,
                    x2 = 8.dp,
                    x3 = 12.dp,
                    x4 = 16.dp,
                    x6 = 24.dp,
                    x8 = 32.dp,
                    x12 = 48.dp,
                    x16 = 64.dp,
                ),
                radius = ArvioRadiusTokens(
                    sm = 6.dp,
                    md = 8.dp,
                    lg = 12.dp,
                ),
                typography = ArvioTypographyTokens(
                    heroTitle = TextStyle(
                        fontFamily = InterFontFamily,
                        fontWeight = FontWeight.Black,
                        fontSize = 50.sp,
                        letterSpacing = (-0.5).sp,
                        lineHeight = 56.sp,
                    ),
                    sectionTitle = TextStyle(
                        fontFamily = InterFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        letterSpacing = 0.4.sp,
                        lineHeight = 26.sp,
                    ),
                    cardTitle = TextStyle(
                        fontFamily = InterFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 15.sp,
                        letterSpacing = 0.sp,
                        lineHeight = 20.sp,
                    ),
                    body = TextStyle(
                        fontFamily = InterFontFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        letterSpacing = 0.sp,
                        lineHeight = 20.sp,
                    ),
                    caption = TextStyle(
                        fontFamily = InterFontFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 11.sp,
                        letterSpacing = 0.3.sp,
                        lineHeight = 14.sp,
                    ),
                    badge = TextStyle(
                        fontFamily = InterFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 9.sp,
                        letterSpacing = 0.4.sp,
                        lineHeight = 12.sp,
                    ),
                    button = TextStyle(
                        fontFamily = InterFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        letterSpacing = 0.4.sp,
                        lineHeight = 20.sp,
                    ),
                ),
                motion = ArvioMotionTokens(
                    focusDurationMillis = 120,    // Smooth focus transitions
                    focusEasing = easeOut,
                    screenTransitionMillis = 150, // Smooth screen transitions
                    heroFadeMillis = 200,         // Smooth backdrop dissolve
                ),
                focus = ArvioFocusTokens(
                    scaleFocused = 1.045f,
                    scalePressed = 0.97f,
                    durationMillis = 120,
                    easing = easeOut,
                    outlineWidth = 2.dp,
                    glowWidth = 8.dp,
                    glowAlpha = 0.22f,
                    translationZFocused = 8.dp,
                ),
            )
        }
    }
}
