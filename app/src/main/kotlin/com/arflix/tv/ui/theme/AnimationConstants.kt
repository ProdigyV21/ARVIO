package com.arflix.tv.ui.theme

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Spring

/**
 * ARVIO Animation Constants - Premium TV Motion Design
 * Smooth, fluid transitions optimized for TV viewing distance
 */
object AnimationConstants {

    // ============================================
    // DURATION VALUES (Optimized for TV smoothness)
    // ============================================

    /** Fast micro-interactions (focus ring, color changes) */
    const val DURATION_FAST = 150  // Slightly longer for smooth perception on TV

    /** Default transitions (scale, movement) */
    const val DURATION_NORMAL = 200  // Smooth but responsive

    /** Slower emphasis animations (hero changes, page transitions) */
    const val DURATION_EMPHASIS = 300  // Visible smooth transitions

    /** Long decorative animations (Ken Burns, ambient effects) */
    const val DURATION_SLOW = 500

    /** Very long animations for background effects */
    const val DURATION_EXTRA_SLOW = 1000

    /** Ken Burns effect duration for hero backdrops */
    const val DURATION_KEN_BURNS = 20000

    /** Image crossfade duration */
    const val DURATION_IMAGE_CROSSFADE = 250

    /** Backdrop dissolve duration */
    const val DURATION_BACKDROP_DISSOLVE = 200

    // ============================================
    // STAGGER DELAYS
    // ============================================

    /** Delay between sequential card animations */
    const val STAGGER_CARD = 40  // Slightly faster stagger

    /** Delay for section entrance animations */
    const val STAGGER_SECTION = 80

    // ============================================
    // SCALE VALUES (Noticeable on TV viewing distance)
    // ============================================

    /** Default unfocused scale */
    const val SCALE_UNFOCUSED = 1.0f

    /** Focused card scale - noticeable lift for TV */
    const val SCALE_FOCUSED = 1.05f

    /** Pressed/clicked scale */
    const val SCALE_PRESSED = 0.97f

    /** Hero logo pulsing scale */
    const val SCALE_PULSE_MIN = 1.0f
    const val SCALE_PULSE_MAX = 1.02f

    // ============================================
    // SPRING CONFIGURATIONS (Natural feel with slight bounce)
    // ============================================

    /** Focus spring - slight bounce for premium feel */
    const val SPRING_STIFFNESS_FOCUS = 400f  // Custom stiffness for smooth response
    const val SPRING_DAMPING_FOCUS = 0.75f   // Slight bounce

    /** Gentle spring for large movements */
    const val SPRING_STIFFNESS_GENTLE = Spring.StiffnessMediumLow
    const val SPRING_DAMPING_GENTLE = 0.8f   // Minimal bounce

    /** Tight spring for micro-interactions */
    const val SPRING_STIFFNESS_TIGHT = 500f
    const val SPRING_DAMPING_TIGHT = 0.85f

    /** Scroll spring for smooth deceleration */
    const val SPRING_STIFFNESS_SCROLL = 300f
    const val SPRING_DAMPING_SCROLL = 0.9f
    
    // ============================================
    // EASING CURVES
    // ============================================
    
    /** Standard easing - ease out for responsive feel */
    val EaseOut = CubicBezierEasing(0.0f, 0.0f, 0.2f, 1.0f)
    
    /** Fast out, slow in - for emphasis */
    val FastOutSlowIn = CubicBezierEasing(0.4f, 0.0f, 0.2f, 1.0f)
    
    /** Ease in out - for symmetric animations */
    val EaseInOut = CubicBezierEasing(0.42f, 0.0f, 0.58f, 1.0f)
    
    /** Sharp ease - for quick snappy movements */
    val Sharp = CubicBezierEasing(0.4f, 0.0f, 0.6f, 1.0f)
    
    /** Decelerate - for elements coming to rest */
    val Decelerate = CubicBezierEasing(0.0f, 0.0f, 0.2f, 1.0f)

    /** Smooth decelerate - for scroll stop */
    val SmoothDecelerate = CubicBezierEasing(0.1f, 0.0f, 0.3f, 1.0f)

    // ============================================
    // SHADOW & ELEVATION
    // ============================================

    /** Unfocused card elevation */
    const val ELEVATION_CARD_UNFOCUSED = 4

    /** Focused card elevation - more pronounced lift */
    const val ELEVATION_CARD_FOCUSED = 32

    /** Modal/overlay elevation */
    const val ELEVATION_MODAL = 48
    
    // ============================================
    // BORDER & GLOW
    // ============================================
    
    /** Focus ring width */
    const val BORDER_FOCUS_WIDTH = 3
    
    /** Glow blur radius for focus effect */
    const val GLOW_RADIUS_FOCUS = 16
    
    /** Ambient glow radius */
    const val GLOW_RADIUS_AMBIENT = 8
}
