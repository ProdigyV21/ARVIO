package com.arflix.tv.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.imageLoader
import coil.request.ImageRequest
import coil.size.Precision
import coil.size.Scale

@Composable
internal fun CardClearLogo(
    url: String,
    modifier: Modifier = Modifier,
    imageLoader: ImageLoader = LocalContext.current.imageLoader,
    onLoadedChanged: ((Boolean) -> Unit)? = null
) {
    val context = LocalContext.current
    val density = LocalDensity.current
    val request = remember(url, context, density) {
        val width = with(density) { 144.dp.roundToPx() }.coerceAtLeast(1)
        val height = with(density) { 40.dp.roundToPx() }.coerceAtLeast(1)
        // Decode for the final slot, without enlarging sources smaller than that slot.
        ImageRequest.Builder(context)
            .data(url)
            .size(width, height)
            .scale(Scale.FIT)
            .precision(Precision.INEXACT)
            .memoryCacheKey("card-clearlogo-v2|$url|${width}x$height")
            .crossfade(false)
            .build()
    }
    AsyncImage(
        model = request,
        imageLoader = imageLoader,
        contentDescription = null,
        contentScale = ContentScale.Inside,
        alignment = Alignment.BottomStart,
        filterQuality = FilterQuality.High,
        modifier = modifier.size(144.dp, 40.dp),
        onLoading = { onLoadedChanged?.invoke(false) },
        onSuccess = { onLoadedChanged?.invoke(true) },
        onError = { onLoadedChanged?.invoke(false) }
    )
}
