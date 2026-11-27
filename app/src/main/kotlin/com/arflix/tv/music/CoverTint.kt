package com.arflix.tv.music

import kotlin.math.pow

/**
 * Turns an album cover's colours into the player's: a dark background that white text stays
 * readable on, and an accent light enough for the controls. Plain ARGB maths, tested without a device.
 */
object CoverTint {
    private const val MAX_BACKGROUND = 0.06
    private const val MIN_ACCENT = 0.35
    private const val OPAQUE = 0xFF shl 24

    /** Relative luminance (WCAG), 0 for black to 1 for white. */
    fun luminance(argb: Int): Double {
        fun channel(c: Int): Double {
            val v = c / 255.0
            return if (v <= 0.03928) v / 12.92 else ((v + 0.055) / 1.055).pow(2.4)
        }
        return 0.2126 * channel((argb shr 16) and 0xFF) + 0.7152 * channel((argb shr 8) and 0xFF) + 0.0722 * channel(argb and 0xFF)
    }

    fun blend(from: Int, to: Int, t: Double): Int {
        fun mix(shift: Int) = (((from shr shift) and 0xFF) * (1 - t) + ((to shr shift) and 0xFF) * t).toInt().coerceIn(0, 255)
        return OPAQUE or (mix(16) shl 16) or (mix(8) shl 8) or mix(0)
    }

    /** Darkens until white text reads well on it. */
    fun background(argb: Int): Int = shift(argb, OPAQUE) { luminance(it) <= MAX_BACKGROUND }

    /** Lightens until it stands out on a dark background. */
    fun accent(argb: Int): Int = shift(argb, -1) { luminance(it) >= MIN_ACCENT }

    private inline fun shift(argb: Int, toward: Int, done: (Int) -> Boolean): Int {
        var color = argb or OPAQUE
        var t = 0.0
        while (!done(color) && t < 1.0) {
            t += 0.05
            color = blend(argb, toward, t.coerceAtMost(1.0))
        }
        return color
    }

    /** Background and accent from a cover's palette swatches; null when the cover gave none. */
    fun pick(vibrant: Int?, lightVibrant: Int?, dominant: Int?, darkMuted: Int?, muted: Int?): Pair<Int, Int>? {
        val base = darkMuted ?: dominant ?: muted ?: vibrant ?: return null
        val pop = vibrant ?: lightVibrant ?: muted ?: dominant ?: return null
        return background(base) to accent(pop)
    }
}
