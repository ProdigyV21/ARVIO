package com.arflix.tv.ui.theme

import androidx.compose.ui.graphics.Color

/**
 * ARVIO Color Palette
 * Arctic Fuse 2 Inspired - Minimal Dark Theme
 */

// ============================================
// ARCTIC FUSE 2 MAIN COLORS
// ============================================
val ArcticWhite = Color(0xFFEDEDED)          // Main foreground #ededed
val ArcticWhite90 = Color(0xE7EDEDED)        // 90% opacity
val ArcticWhite70 = Color(0xB3EDEDED)        // 70% opacity
val ArcticWhite50 = Color(0x80EDEDED)        // 50% opacity
val ArcticWhite30 = Color(0x4DEDEDED)        // 30% opacity
val ArcticWhite12 = Color(0x1FEDEDED)        // 12% opacity

val ArcticBlack = Color(0xFF000000)          // Main background #000000
val ArcticBlack90 = Color(0xE7000000)        // 90% opacity
val ArcticBlack70 = Color(0xB3000000)        // 70% opacity
val ArcticBlack50 = Color(0x80000000)        // 50% opacity
val ArcticBlack30 = Color(0x4D000000)        // 30% opacity
val ArcticBlack12 = Color(0x1F000000)        // 12% opacity

val ArcticGray = Color(0xFF4D4D4D)           // Soft gray accent
val ArcticGrayLight = Color(0xFFB3B3B3)      // Logo/subtle elements

// ============================================
// ACCENT COLORS
// ============================================
val AccentWhite = Color(0xFFFFFFFF)          // Pure white for focus
val AccentYellow = Color(0xFFFFCD3C)         // Star ratings
val AccentGreen = Color(0xFF00D588)          // "New episode" badges

// Legacy aliases for compatibility
val PrimeBlue = ArcticWhite
val PrimeBlueDark = ArcticGray
val PrimeBlueLight = AccentWhite
val PrimeBlueGlow = Color(0x33FFFFFF)
val PrimeGreen = AccentGreen
val RankNumberColor = ArcticWhite70

val PurplePrimary = ArcticWhite
val PurpleLight = AccentWhite
val PurpleDark = ArcticGray
val PurpleDeep = ArcticBlack
val PurpleGlow = Color(0x33FFFFFF)
val PurpleSoft = ArcticWhite70

val Cyan = ArcticWhite
val CyanDark = ArcticGray
val CyanGlow = Color(0x33FFFFFF)

val Purple = ArcticWhite
val PurpleAccent = ArcticWhite

val Pink = AccentWhite
val PinkDark = ArcticGray
val PinkGlow = Color(0x33FFFFFF)

// Gradient combinations (minimal)
val GradientStart = ArcticBlack
val GradientMiddle = Color(0xFF0A0A0A)
val GradientEnd = ArcticBlack

// ============================================
// BACKGROUND COLORS (Pure Black)
// ============================================
val BackgroundDark = ArcticBlack              // Pure black
val BackgroundCard = Color(0xFF0D0D0D)        // Slightly elevated
val BackgroundElevated = Color(0xFF1A1A1A)    // Elevated surfaces
val BackgroundOverlay = ArcticBlack90         // 90% black overlay
val BackgroundGlass = Color(0x99000000)       // Glass effect

// Gradient backgrounds
val BackgroundGradientStart = ArcticBlack
val BackgroundGradientCenter = Color(0xFF050505)
val BackgroundGradientMiddle = Color(0xFF050505)
val BackgroundGradientEnd = ArcticBlack

// ============================================
// SURFACE COLORS
// ============================================
val SurfaceDark = ArcticBlack
val SurfaceVariant = Color(0xFF0D0D0D)
val SurfaceGlass = Color(0x4D000000)

// ============================================
// TEXT COLORS (Light Gray #EDEDED)
// ============================================
val TextPrimary = ArcticWhite                 // #EDEDED
val TextSecondary = ArcticWhite70             // 70% opacity
val TextTertiary = ArcticWhite50              // 50% opacity
val TextDisabled = ArcticWhite30              // 30% opacity

// ============================================
// BORDER COLORS
// ============================================
val BorderLight = ArcticWhite12               // 12% white
val BorderMedium = ArcticWhite30              // 30% white
val BorderGradient = ArcticWhite50            // 50% white

// ============================================
// STATUS COLORS
// ============================================
val SuccessGreen = AccentGreen
val ErrorRed = Color(0xFFE74C3C)
val WarningOrange = Color(0xFFF39C12)
val InfoBlue = ArcticWhite
val OngoingBlue = ArcticWhite

// ============================================
// SPECIAL COLORS
// ============================================
val ImdbYellow = AccentYellow                 // Star ratings
val AccentRed = ArcticWhite

// ============================================
// FOCUS & GLOW STATES (Kodi Inspired)
// ============================================
val KodiMagenta = Color(0xFFFC1C8E)           // Pink focus indicator
val KodiPurple = Color(0xFFB64BFF)            // Purple card border
val FocusRing = AccentWhite                   // Arctic Fuse 2 default: white focus
val FocusGlow = AccentWhite.copy(alpha = 0.20f)
val FocusShadowColor = Color(0x40000000)
val FocusGradientStart = AccentWhite
val FocusGradientEnd = ArcticWhite90

// ============================================
// PARTICLE/EFFECT COLORS
// ============================================
val ParticleCyan = ArcticWhite30
val ParticlePurple = ArcticWhite12
val ParticlePink = ArcticWhite30
val ParticlePurpleLight = ArcticWhite50
val ParticlePurpleDark = ArcticBlack50

// ============================================
// LEGACY ALIASES
// ============================================
val ArvioAccent = ArcticWhite
val ArvioPurple = ArcticBlack
val ArvioLight = ArcticWhite70

