package com.arflix.tv.ui.performance

import android.content.Context
import android.graphics.Bitmap
import coil.request.ImageRequest
import coil.size.Precision
import kotlin.math.roundToInt

internal fun buildTvSizedImageRequest(
    context: Context,
    url: String,
    widthPx: Int,
    heightPx: Int,
    role: TvImageRole,
    rgb565: Boolean = true
): ImageRequest {
    val safeWidth = widthPx.coerceAtLeast(1)
    val safeHeight = heightPx.coerceAtLeast(1)
    val cacheKey = tvMenuImageCacheKey(
        url = url,
        widthPx = safeWidth,
        heightPx = safeHeight,
        role = role,
        rgb565 = rgb565
    )
    return ImageRequest.Builder(context)
        .data(url)
        .size(safeWidth, safeHeight)
        .bitmapConfig(if (rgb565) Bitmap.Config.RGB_565 else Bitmap.Config.ARGB_8888)
        .allowRgb565(rgb565)
        .precision(Precision.INEXACT)
        .allowHardware(true)
        .memoryCacheKey(cacheKey)
        .placeholderMemoryCacheKey(cacheKey)
        .crossfade(false)
        .build()
}

internal fun scaledDecodeSize(
    displayWidthPx: Int,
    displayHeightPx: Int,
    decodeScale: Float
): Pair<Int, Int> {
    val scale = decodeScale.coerceIn(0.1f, 1f)
    return (displayWidthPx.coerceAtLeast(1) * scale).roundToInt().coerceAtLeast(1) to
        (displayHeightPx.coerceAtLeast(1) * scale).roundToInt().coerceAtLeast(1)
}
