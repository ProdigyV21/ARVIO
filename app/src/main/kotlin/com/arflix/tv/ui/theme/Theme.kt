package com.arflix.tv.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.darkColorScheme
import com.arflix.tv.ui.skin.ProvideArvioSkin

/**
 * ARVIO Color scheme holder - Arctic Fuse 2 inspired
 * Minimal dark theme with light gray (#EDEDED) on pure black (#000000)
 */
data class ArvioColors(
    // Arctic Fuse 2 Main Colors
    val arcticWhite: androidx.compose.ui.graphics.Color = ArcticWhite,
    val arcticWhite90: androidx.compose.ui.graphics.Color = ArcticWhite90,
    val arcticWhite70: androidx.compose.ui.graphics.Color = ArcticWhite70,
    val arcticWhite50: androidx.compose.ui.graphics.Color = ArcticWhite50,
    val arcticBlack: androidx.compose.ui.graphics.Color = ArcticBlack,
    val arcticGray: androidx.compose.ui.graphics.Color = ArcticGray,
    
    // Legacy gradient colors (mapped to Arctic style)
    val cyan: androidx.compose.ui.graphics.Color = ArcticWhite,
    val cyanDark: androidx.compose.ui.graphics.Color = ArcticGray,
    val cyanGlow: androidx.compose.ui.graphics.Color = FocusGlow,
    val purple: androidx.compose.ui.graphics.Color = ArcticWhite,
    val purpleDark: androidx.compose.ui.graphics.Color = ArcticGray,
    val purpleGlow: androidx.compose.ui.graphics.Color = FocusGlow,
    val pink: androidx.compose.ui.graphics.Color = AccentWhite,
    val pinkDark: androidx.compose.ui.graphics.Color = ArcticGray,
    val pinkGlow: androidx.compose.ui.graphics.Color = FocusGlow,

    // Background colors
    val backgroundDark: androidx.compose.ui.graphics.Color = BackgroundDark,
    val backgroundCard: androidx.compose.ui.graphics.Color = BackgroundCard,
    val backgroundElevated: androidx.compose.ui.graphics.Color = BackgroundElevated,
    val backgroundGlass: androidx.compose.ui.graphics.Color = BackgroundGlass,

    // Text colors
    val textPrimary: androidx.compose.ui.graphics.Color = TextPrimary,
    val textSecondary: androidx.compose.ui.graphics.Color = TextSecondary,
    val textTertiary: androidx.compose.ui.graphics.Color = TextTertiary,

    // Border colors
    val borderLight: androidx.compose.ui.graphics.Color = BorderLight,
    val borderGradient: androidx.compose.ui.graphics.Color = BorderGradient,

    // Status colors
    val success: androidx.compose.ui.graphics.Color = SuccessGreen,
    val error: androidx.compose.ui.graphics.Color = ErrorRed,
    val warning: androidx.compose.ui.graphics.Color = WarningOrange,
    val info: androidx.compose.ui.graphics.Color = InfoBlue,

    // Special colors
    val imdbYellow: androidx.compose.ui.graphics.Color = ImdbYellow,
    val accentRed: androidx.compose.ui.graphics.Color = AccentRed,

    // Focus states (White for Arctic Fuse 2)
    val focusRing: androidx.compose.ui.graphics.Color = FocusRing,
    val focusGlow: androidx.compose.ui.graphics.Color = FocusGlow,

    // Particle colors (subtle white)
    val particleCyan: androidx.compose.ui.graphics.Color = ParticleCyan,
    val particlePurple: androidx.compose.ui.graphics.Color = ParticlePurple,
    val particlePink: androidx.compose.ui.graphics.Color = ParticlePink
)

val LocalArvioColors = staticCompositionLocalOf { ArvioColors() }

// Keep legacy aliases for compatibility
val LocalArflixColors = LocalArvioColors

/**
 * Main ARVIO TV theme - Arctic Fuse 2 inspired
 * Pure black background, light gray text, white focus states
 */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun ArvioTvTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = darkColorScheme(
        primary = ArcticWhite,
        onPrimary = ArcticBlack,
        primaryContainer = ArcticGray,
        onPrimaryContainer = ArcticWhite,
        secondary = ArcticWhite70,
        onSecondary = ArcticBlack,
        secondaryContainer = ArcticGray,
        onSecondaryContainer = ArcticWhite,
        tertiary = AccentWhite,
        onTertiary = ArcticBlack,
        tertiaryContainer = ArcticGray,
        onTertiaryContainer = ArcticWhite,
        background = BackgroundDark,
        onBackground = TextPrimary,
        surface = BackgroundCard,
        onSurface = TextPrimary,
        surfaceVariant = SurfaceVariant,
        onSurfaceVariant = TextSecondary,
        error = ErrorRed,
        onError = ArcticWhite,
        border = BorderLight
    )

    val arvioColors = ArvioColors()

    CompositionLocalProvider(
        LocalArvioColors provides arvioColors
    ) {
        ProvideArvioSkin {
            MaterialTheme(
                colorScheme = colorScheme,
                content = content
            )
        }
    }
}

// Legacy alias for compatibility
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun ArflixTvTheme(
    content: @Composable () -> Unit
) = ArvioTvTheme(content)

/**
 * Access custom ARVIO colors
 */
object ArvioTheme {
    val colors: ArvioColors
        @Composable
        get() = LocalArvioColors.current
}

// Legacy alias for compatibility
object ArflixTheme {
    val colors: ArvioColors
        @Composable
        get() = LocalArvioColors.current
}

// Type alias for backward compatibility
typealias ArflixColors = ArvioColors

