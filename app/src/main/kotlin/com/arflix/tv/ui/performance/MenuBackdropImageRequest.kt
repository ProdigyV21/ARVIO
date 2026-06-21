package com.arflix.tv.ui.performance

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import coil.request.ImageRequest

@Composable
internal fun rememberTvMenuBackdropRequest(url: String?): ImageRequest? {
    val cleanUrl = url?.takeIf { it.isNotBlank() } ?: return null
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current
    val decodeSize = remember(configuration, density) {
        val screenWidthPx = with(density) { configuration.screenWidthDp.dp.roundToPx() }
        val screenHeightPx = with(density) { configuration.screenHeightDp.dp.roundToPx() }
        tvMenuBackdropDecodeSize(screenWidthPx, screenHeightPx)
    }

    return remember(context, cleanUrl, decodeSize) {
        val (widthPx, heightPx) = decodeSize
        buildTvSizedImageRequest(
            context = context,
            url = cleanUrl,
            widthPx = widthPx,
            heightPx = heightPx,
            role = TvImageRole.MENU_BACKDROP,
            rgb565 = true
        )
    }
}
