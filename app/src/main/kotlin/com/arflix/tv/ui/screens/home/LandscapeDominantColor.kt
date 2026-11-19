package com.arflix.tv.ui.screens.home

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.collection.LruCache
import androidx.compose.ui.graphics.Color
import androidx.palette.graphics.Palette
import coil.imageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import coil.size.Precision
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * In-memory cache of extracted dominant background colors by image URL.
 */
private val dominantColorCache = LruCache<String, Color>(100)

val DefaultLandscapeAppBackground = Color(0xFF0D0D12)

/**
 * Converts any RGB color into a deep, atmospheric dark background color
 * that preserves the dominant hue and saturation while constraining lightness
 * so that text, media cards, and system UI remain high contrast and beautiful.
 */
internal fun rgbToDominantDarkBackground(
    rgb: Int,
    targetLightness: Float = 0.095f,
    minSaturation: Float = 0.35f,
    maxSaturation: Float = 0.70f
): Color {
    val r = ((rgb shr 16) and 0xFF) / 255f
    val g = ((rgb shr 8) and 0xFF) / 255f
    val b = (rgb and 0xFF) / 255f

    val max = maxOf(r, g, b)
    val min = minOf(r, g, b)
    val delta = max - min

    // If the color is essentially grayscale, return a clean neutral dark
    if (delta < 0.05f) {
        val darkGray = (targetLightness).coerceIn(0.06f, 0.12f)
        return Color(
            red = darkGray,
            green = darkGray,
            blue = (darkGray * 1.15f).coerceAtMost(1f), // subtle cool dark tone
            alpha = 1f
        )
    }

    val h = when {
        max == r -> ((g - b) / delta % 6f) * 60f
        max == g -> ((b - r) / delta + 2f) * 60f
        else -> ((r - g) / delta + 4f) * 60f
    }.let { if (it < 0f) it + 360f else it }

    val rawL = (max + min) / 2f
    val rawS = delta / (1f - kotlin.math.abs(2f * rawL - 1f))
    val s = rawS.coerceIn(minSaturation, maxSaturation)
    val l = targetLightness.coerceIn(0.07f, 0.12f)

    val c = (1f - kotlin.math.abs(2f * l - 1f)) * s
    val x = c * (1f - kotlin.math.abs((h / 60f) % 2f - 1f))
    val m = l - c / 2f

    val (r1, g1, b1) = when ((h / 60f).toInt()) {
        0 -> Triple(c, x, 0f)
        1 -> Triple(x, c, 0f)
        2 -> Triple(0f, c, x)
        3 -> Triple(0f, x, c)
        4 -> Triple(x, 0f, c)
        else -> Triple(c, 0f, x)
    }

    return Color(
        red = (r1 + m).coerceIn(0f, 1f),
        green = (g1 + m).coerceIn(0f, 1f),
        blue = (b1 + m).coerceIn(0f, 1f),
        alpha = 1f
    )
}

/**
 * Extracts the dominant background color from a bitmap using Palette.
 */
internal fun extractDominantDarkColor(
    bitmap: Bitmap,
    fallback: Color = DefaultLandscapeAppBackground
): Color {
    val palette = Palette.from(bitmap).generate()
    val dominantSwatch = palette.dominantSwatch
        ?: palette.vibrantSwatch
        ?: palette.darkVibrantSwatch
        ?: palette.mutedSwatch
        ?: palette.darkMutedSwatch
        ?: palette.swatches.maxByOrNull { it.population }?.rgb?.let { palette.dominantSwatch }

    val rgb = dominantSwatch?.rgb
        ?: palette.swatches.maxByOrNull { it.population }?.rgb

    return rgb?.let { rgbToDominantDarkBackground(it) } ?: fallback
}

/**
 * Loads an image thumbnail via Coil and extracts its dominant background color.
 * Uses an in-memory cache to return instantly on subsequent lookups.
 */
suspend fun extractDominantColorFromUrl(
    context: Context,
    imageUrl: String?,
    fallback: Color = DefaultLandscapeAppBackground
): Color {
    if (imageUrl.isNullOrBlank()) return fallback

    dominantColorCache.get(imageUrl)?.let { return it }

    return withContext(Dispatchers.IO) {
        try {
            val loader = context.imageLoader
            val request = ImageRequest.Builder(context)
                .data(imageUrl)
                .size(64, 64)
                .allowHardware(false)
                .precision(Precision.INEXACT)
                .build()

            val result = loader.execute(request)
            val drawable = (result as? SuccessResult)?.drawable
            val bitmap = (drawable as? BitmapDrawable)?.bitmap ?: return@withContext fallback

            val color = extractDominantDarkColor(bitmap, fallback)
            dominantColorCache.put(imageUrl, color)
            color
        } catch (_: Throwable) {
            fallback
        }
    }
}
