package com.arflix.tv.ui.skin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val LocalArvioSkinTokens = staticCompositionLocalOf { ArvioSkinTokens.defaults() }

/**
 * Optional override for the accent colour, driven by the user's
 * "Accent Color" setting. When non-null every [arvioFocusable]
 * composable uses this colour instead of [ArvioColorTokens.focusOutline].
 */
val LocalAccentColorOverride = staticCompositionLocalOf<Color?> { null }

/**
 * Resolves the effective accent colour for a component that draws its
 * own focus border (for example, settings rows and glow chips) instead of
 * using the [arvioFocusable] modifier. Returns the user's chosen override
 * when set, otherwise the provided fallback color.
 *
 * Call this inside a `@Composable` lambda to read the CompositionLocal.
 */
@Composable
fun resolveAccentColor(fallback: Color): Color {
    return LocalAccentColorOverride.current ?: fallback
}

/**
 * Maps a user-facing colour name to its [Color] value.
 * Used by the accent colour setting and the colour picker.
 */
fun accentColorFromName(name: String): Color = when (name) {
    "Red" -> Color(0xFFFF4444)
    "Orange" -> Color(0xFFFF8800)
    "Yellow" -> Color(0xFFFFDD44)
    "Green" -> Color(0xFF44CC44)
    "Blue" -> Color(0xFF4488FF)
    "Indigo" -> Color(0xFF6644CC)
    "Violet" -> Color(0xFFBB44CC)
    else -> Color(0xFFFFFFFF) // White (default)
}

@Composable
fun ProvideArvioSkin(
    tokens: ArvioSkinTokens = ArvioSkinTokens.defaults(),
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalArvioSkinTokens provides tokens,
        content = content,
    )
}

object ArvioSkin {
    val tokens: ArvioSkinTokens
        @Composable
        @ReadOnlyComposable
        get() = LocalArvioSkinTokens.current

    val colors: ArvioColorTokens
        @Composable
        @ReadOnlyComposable
        get() = tokens.colors

    val spacing: ArvioSpacingTokens
        @Composable
        @ReadOnlyComposable
        get() = tokens.spacing

    val radius: ArvioRadiusTokens
        @Composable
        @ReadOnlyComposable
        get() = tokens.radius

    val typography: ArvioTypographyTokens
        @Composable
        @ReadOnlyComposable
        get() = tokens.typography

    val motion: ArvioMotionTokens
        @Composable
        @ReadOnlyComposable
        get() = tokens.motion

    val focus: ArvioFocusTokens
        @Composable
        @ReadOnlyComposable
        get() = tokens.focus
}

