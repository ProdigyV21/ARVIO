package com.arflix.tv.ui.theme

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Spring

/**
 * ARVIO Animation Constants - Arctic Fuse 2 Style Motion Design
 * Snappy, responsive transitions with premium feel
 */
object AnimationConstants {

    // ============================================
    // DURATION VALUES (Arctic Fuse 2: Snappier)
    // ============================================

    /** Fast micro-interactions (focus ring, color changes) */
    const val DURATION_FAST = 120  // Arctic Fuse 2: snappier

    /** Default transitions (scale, movement) */
    const val DURATION_NORMAL = 160  // Arctic Fuse 2: faster

    /** Slower emphasis animations (hero changes, page transitions) */
    const val DURATION_EMPHASIS = 240  // Arctic Fuse 2: faster hero transitions
    
    /** Long decorative animations (Ken Burns, ambient effects) */
    const val DURATION_SLOW = 500
    
    /** Very long animations for background effects */
    const val DURATION_EXTRA_SLOW = 1000
    
    /** Ken Burns effect duration for hero backdrops */
    const val DURATION_KEN_BURNS = 20000
    
    // ============================================
    // STAGGER DELAYS
    // ============================================
    
    /** Delay between sequential card animations */
    const val STAGGER_CARD = 50
    
    /** Delay for section entrance animations */
    const val STAGGER_SECTION = 100
    
    // ============================================
    // SCALE VALUES (Arctic Fuse 2: Subtle)
    // ============================================

    /** Default unfocused scale */
    const val SCALE_UNFOCUSED = 1.0f

    /** Focused card scale - Arctic Fuse 2: subtle lift */
    const val SCALE_FOCUSED = 1.05f

    /** Pressed/clicked scale */
    const val SCALE_PRESSED = 0.97f
    
    /** Hero logo pulsing scale */
    const val SCALE_PULSE_MIN = 1.0f
    const val SCALE_PULSE_MAX = 1.02f
    
    // ============================================
    // SPRING CONFIGURATIONS (Arctic Fuse 2: Less Bouncy)
    // ============================================

    /** Snappy spring for focus transitions - smooth, no bounce for TV performance */
    const val SPRING_STIFFNESS_FOCUS = Spring.StiffnessHigh
    const val SPRING_DAMPING_FOCUS = Spring.DampingRatioNoBouncy

    /** Gentle spring for large movements */
    const val SPRING_STIFFNESS_GENTLE = Spring.StiffnessMediumLow
    const val SPRING_DAMPING_GENTLE = Spring.DampingRatioNoBouncy

    /** Tight spring for micro-interactions - snappy */
    const val SPRING_STIFFNESS_TIGHT = Spring.StiffnessHigh
    const val SPRING_DAMPING_TIGHT = Spring.DampingRatioNoBouncy
    
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
    
    // ============================================
    // SHADOW & ELEVATION
    // ============================================
    
    /** Unfocused card elevation */
    const val ELEVATION_CARD_UNFOCUSED = 4
    
    /** Focused card elevation */
    const val ELEVATION_CARD_FOCUSED = 24
    
    /** Modal/overlay elevation */
    const val ELEVATION_MODAL = 32
    
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
