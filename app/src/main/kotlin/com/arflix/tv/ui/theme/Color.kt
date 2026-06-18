package com.arflix.tv.ui.theme

import androidx.compose.ui.graphics.Color

/**
 * ARVIO Color Palette
 * Arctic Fuse 2 Inspired - Minimal Dark Theme
 */

// ============================================
// ARCTIC FUSE 2 MAIN COLORS
// ============================================
val ArcticWhite = Color(0xFFF4F5F5)          // Main foreground
val ArcticWhite90 = Color(0xE7F4F5F5)        // 90% opacity
val ArcticWhite70 = Color(0xB3F4F5F5)        // 70% opacity
val ArcticWhite50 = Color(0x80F4F5F5)        // 50% opacity
val ArcticWhite30 = Color(0x4DF4F5F5)        // 30% opacity
val ArcticWhite12 = Color(0x1FF4F5F5)        // 12% opacity

val ArcticBlack = Color(0xFF000000)          // Main background #000000
val ArcticBlack90 = Color(0xE7000000)        // 90% opacity
val ArcticBlack70 = Color(0xB3000000)        // 70% opacity
val ArcticBlack50 = Color(0x80000000)        // 50% opacity
val ArcticBlack30 = Color(0x4D000000)        // 30% opacity
val ArcticBlack12 = Color(0x1F000000)        // 12% opacity

val ArcticGray = Color(0xFF56606B)           // Soft cool-gray accent
val ArcticGrayLight = Color(0xFFC2C8CE)      // Logo/subtle elements

// ============================================
// ACCENT COLORS
// ============================================
val AccentWhite = Color(0xFFFFFFFF)          // Pure white for focus
val AccentYellow = Color(0xFFFFD166)         // Star ratings / selected accent
val AccentGreen = Color(0xFF38D98B)          // "New episode" badges
val AccentTeal = Color(0xFF64E6D5)           // Modern media-center glow
val AccentRose = Color(0xFFFF6B91)           // Rare alert/accent contrast

// Legacy aliases for compatibility
val PrimeBlue = AccentTeal
val PrimeBlueDark = ArcticGray
val PrimeBlueLight = AccentWhite
val PrimeBlueGlow = Color(0x33FFFFFF)
val PrimeGreen = AccentGreen
val RankNumberColor = ArcticWhite70

val PurplePrimary = AccentYellow
val PurpleLight = AccentTeal
val PurpleDark = ArcticGray
val PurpleDeep = ArcticBlack
val PurpleGlow = Color(0x33FFFFFF)
val PurpleSoft = ArcticWhite70

val Cyan = AccentTeal
val CyanDark = ArcticGray
val CyanGlow = Color(0x33FFFFFF)

val Purple = ArcticWhite
val PurpleAccent = ArcticWhite

val Pink = AccentRose
val PinkDark = ArcticGray
val PinkGlow = Color(0x33FFFFFF)

// Gradient combinations (minimal)
val GradientStart = Color(0xFF030405)
val GradientMiddle = Color(0xFF071012)
val GradientEnd = Color(0xFF0C0A08)

// ============================================
// BACKGROUND COLORS (App Background)
// ============================================
val BackgroundDark = Color(0xFF030405)
val BackgroundCard = Color(0xFF101318)
val BackgroundElevated = Color(0xFF1B2028)
val BackgroundOverlay = BackgroundDark.copy(alpha = 0.90f)
val BackgroundGlass = BackgroundDark.copy(alpha = 0.60f)

// Gradient backgrounds
val BackgroundGradientStart = BackgroundDark
val BackgroundGradientCenter = BackgroundDark
val BackgroundGradientMiddle = BackgroundDark
val BackgroundGradientEnd = BackgroundDark

// ============================================
// SURFACE COLORS
// ============================================
val SurfaceDark = BackgroundDark
val SurfaceVariant = Color(0xFF141922)
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
val InfoBlue = AccentTeal
val OngoingBlue = AccentTeal

// ============================================
// SPECIAL COLORS
// ============================================
val ImdbYellow = AccentYellow                 // Star ratings
val AccentRed = Color(0xFFE53935)

// ============================================
// FOCUS & GLOW STATES (Kodi Inspired)
// ============================================
val KodiMagenta = AccentRose
val KodiPurple = AccentTeal
val FocusRing = AccentWhite                   // Arctic Fuse 2 default: white focus
val FocusGlow = AccentTeal.copy(alpha = 0.22f)
val FocusShadowColor = Color(0x40000000)
val FocusGradientStart = AccentTeal
val FocusGradientEnd = AccentYellow

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
