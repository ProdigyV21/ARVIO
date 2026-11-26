package com.arflix.tv.ui.components

import android.graphics.Bitmap

/**
 * True when a title logo is so dark that it disappears on ARVIO's dark artwork scrims
 * (e.g. black lettering). Looks only at clearly opaque pixels and averages their luminance.
 */
internal fun logoPixelsAreDark(argbPixels: IntArray, threshold: Float = 0.22f): Boolean {
    var sum = 0.0
    var count = 0
    for (pixel in argbPixels) {
        val alpha = pixel ushr 24
        if (alpha < 160) continue
        val r = (pixel shr 16) and 0xFF
        val g = (pixel shr 8) and 0xFF
        val b = pixel and 0xFF
        sum += (0.2126 * r + 0.7152 * g + 0.0722 * b) / 255.0
        count++
    }
    // Too few opaque pixels to judge (or a fully transparent image): leave the logo as it is.
    if (count < 16) return false
    return sum / count < threshold
}

/** Samples a software bitmap on a small grid; hardware bitmaps can't be read and count as light. */
internal fun logoBitmapIsDark(bitmap: Bitmap): Boolean {
    if (bitmap.config == Bitmap.Config.HARDWARE) return false
    val step = maxOf(1, maxOf(bitmap.width, bitmap.height) / 64)
    val samples = ArrayList<Int>()
    var y = 0
    while (y < bitmap.height) {
        var x = 0
        while (x < bitmap.width) {
            samples += bitmap.getPixel(x, y)
            x += step
        }
        y += step
    }
    return logoPixelsAreDark(samples.toIntArray())
}
