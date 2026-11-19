package com.arflix.tv.ui.components

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.imageLoader
import coil.request.ImageRequest
import com.arflix.tv.util.TmdbImageSizing

@Composable
internal fun MobileDetailsArtwork(
    source: MobileDetailsArtworkSource?,
    modifier: Modifier = Modifier,
    imageLoader: ImageLoader? = null,
) {
    val context = LocalContext.current
    val density = LocalDensity.current
    BoxWithConstraints(modifier) {
        val width = with(density) { maxWidth.roundToPx() }
        val height = with(density) { maxHeight.roundToPx() }
        val request = remember(source, width, height, context) {
            ImageRequest.Builder(context)
                .data(source?.let { TmdbImageSizing.forSlot(it.url, width, height,
                    if (it.isPoster) TmdbImageSizing.Kind.POSTER else TmdbImageSizing.Kind.BACKDROP) })
                .crossfade(250)
                .build()
        }
        AsyncImage(request, null, imageLoader = imageLoader ?: context.imageLoader,
            modifier = Modifier.fillMaxSize(),
            contentScale = if (source?.isPoster == true) ContentScale.Fit else ContentScale.Crop,
            alignment = Alignment.TopCenter)
    }
}
