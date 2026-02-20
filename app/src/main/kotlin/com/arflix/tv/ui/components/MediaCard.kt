package com.arflix.tv.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Precision
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.ui.skin.ArvioFocusableSurface
import com.arflix.tv.ui.skin.ArvioSkin
import com.arflix.tv.ui.skin.rememberArvioCardShape
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.zIndex

/**
 * Media card component for rows/grids.
 * Arctic Fuse 2 style:
 * - Large landscape cards with solid pink/magenta focus border
 * - Transform-based focus (graphicsLayer) via `ArvioFocusableSurface`
 * - No layout size changes on focus (no width/height scaling)
 * - Uses `ArvioSkin` for consistent styling
 */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun MediaCard(
    item: MediaItem,
    width: Dp = 280.dp,  // Arctic Fuse 2: larger cards by default
    isLandscape: Boolean = true,
    logoImageUrl: String? = null,
    showProgress: Boolean = false,
    isFocusedOverride: Boolean = false,
    enableSystemFocus: Boolean = true,
    onFocused: () -> Unit = {},
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    // If this is a placeholder card, show skeleton only
    if (item.isPlaceholder) {
        PlaceholderCard(
            width = width,
            isLandscape = isLandscape,
            modifier = modifier
        )
        return
    }

    var isFocused by remember { mutableStateOf(false) }
    val visualFocused = isFocusedOverride || isFocused

    val aspectRatio = if (isLandscape) 16f / 9f else 2f / 3f
    // Plex-like behavior: landscape cards should prefer wide artwork/backdrops.
    // Poster art is portrait and looks cropped in 16:9 cards, so only use it as fallback.
    val imageUrl = if (isLandscape) {
        item.backdrop ?: item.image
    } else {
        item.image
    }
    val shape = rememberArvioCardShape(ArvioSkin.radius.md)

    val showFocusOutline = visualFocused
    val jumpBorderWidth = if (showFocusOutline) 3.dp else 0.dp

    val context = LocalContext.current
    val density = LocalDensity.current
    val backgroundColor = ArvioSkin.colors.background
    val overlayBrush = remember(backgroundColor) {
        Brush.verticalGradient(
            colors = listOf(
                Color.Transparent,
                Color.Transparent,
                backgroundColor.copy(alpha = 0.55f),
                backgroundColor.copy(alpha = 0.85f),
            )
        )
    }
    val imageRequest = remember(imageUrl, width, aspectRatio, context, density) {
        val widthPx = with(density) { width.roundToPx() }
        val heightPx = (widthPx / aspectRatio).toInt().coerceAtLeast(1)
        ImageRequest.Builder(context)
            .data(imageUrl)
            .size(widthPx, heightPx)
            .precision(Precision.INEXACT)
            .allowHardware(true)
            .crossfade(250)  // Smooth 250ms fade transition
            .build()
    }

    Column(
        modifier = modifier
            .width(width)
            .zIndex(if (visualFocused) 1f else 0f)
    ) {
        ArvioFocusableSurface(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(aspectRatio),
            shape = shape,
            backgroundColor = ArvioSkin.colors.surface,
            outlineColor = ArvioSkin.colors.focusOutline,
            outlineWidth = jumpBorderWidth,
            focusedScale = 1.0f,
            pressedScale = 1f,
            focusedTransformOriginX = 0f,
            enableSystemFocus = enableSystemFocus,
            isFocusedOverride = isFocusedOverride,
            onClick = onClick,
            onFocusChanged = {
                isFocused = it
                if (it) onFocused()
            },
        ) { _ ->
            Box(modifier = Modifier.fillMaxSize()) {
                // Lighter image path for smooth TV scrolling.
                SkeletonBox(modifier = Modifier.fillMaxSize())
                AsyncImage(
                    model = imageRequest,
                    contentDescription = item.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(ArvioSkin.colors.surface)
                        .drawWithCache {
                            onDrawWithContent {
                                drawContent()
                                drawRect(overlayBrush)
                            }
                        },
                )

                // Official logo/art overlay centered on landscape cards.
                if (isLandscape && !logoImageUrl.isNullOrBlank()) {
                    AsyncImage(
                        model = logoImageUrl,
                        contentDescription = "${item.title} logo",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .fillMaxWidth(0.62f)
                            .height(56.dp)
                            .padding(horizontal = 8.dp, vertical = 6.dp)
                    )
                }

                // Subtle green watched badge
                if (item.isWatched) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                        .padding(bottom = 6.dp, end = 6.dp)
                        .size(14.dp)
                        .background(
                            color = ArvioSkin.colors.watchedGreen.copy(alpha = 0.2f),
                            shape = CircleShape
                        )
                        .border(
                            width = 1.dp,
                            color = ArvioSkin.colors.watchedGreen,
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = ArvioSkin.colors.watchedGreen,
                        modifier = Modifier.size(8.dp)
                    )
                }
                }

                // Subtle progress bar for Continue Watching
                if (showProgress && !item.isWatched && item.progress in 1..94) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 6.dp)
                            .height(4.dp)
                            .clip(RoundedCornerShape(999.dp))
                            .background(Color.White.copy(alpha = 0.26f))
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(item.progress / 100f)
                                .fillMaxSize()
                                .background(Color.White.copy(alpha = 0.92f))
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(ArvioSkin.spacing.x2))

        Text(
            text = item.title,
            style = ArvioSkin.typography.cardTitle,
            color = if (visualFocused) {
                ArvioSkin.colors.textPrimary
            } else {
                ArvioSkin.colors.textPrimary.copy(alpha = 0.85f)
            },
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        // Arctic Fuse 2 style: Show media type with genre-like format
        val subtitle = remember(item.subtitle, item.mediaType) {
            item.subtitle.ifBlank {
                when (item.mediaType) {
                    MediaType.TV -> "Drama / TV Series"
                    MediaType.MOVIE -> "Action / Movie"
                    else -> "Media"
                }
            }
        }
        Text(
            text = subtitle,
            style = ArvioSkin.typography.caption,
            color = ArvioSkin.colors.textMuted.copy(alpha = 0.85f),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

/**
 * Placeholder card shown while Continue Watching data loads.
 * Displays a skeleton animation to indicate loading state.
 */
@Composable
private fun PlaceholderCard(
    width: Dp,
    isLandscape: Boolean,
    modifier: Modifier = Modifier
) {
    val aspectRatio = if (isLandscape) 16f / 9f else 2f / 3f
    val shape = rememberArvioCardShape(ArvioSkin.radius.md)

    Column(
        modifier = modifier.width(width)
    ) {
        // Card skeleton
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(aspectRatio)
                .clip(shape)
        ) {
            SkeletonBox(
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.height(ArvioSkin.spacing.x2))

        // Title skeleton
        SkeletonBox(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(14.dp)
                .clip(rememberArvioCardShape(ArvioSkin.radius.sm))
        )

        Spacer(modifier = Modifier.height(4.dp))

        // Subtitle skeleton
        SkeletonBox(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .height(12.dp)
                .clip(rememberArvioCardShape(ArvioSkin.radius.sm))
        )
    }
}

/**
 * Poster-style media card (portrait orientation).
 * Phase 5: Added proper image sizing and shimmer placeholder.
 */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun PosterCard(
    item: MediaItem,
    width: Dp = 140.dp,
    isFocusedOverride: Boolean = false,
    enableSystemFocus: Boolean = true,
    onFocused: () -> Unit = {},
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
    useWhiteBorder: Boolean = true,
) {
    var isFocused by remember { mutableStateOf(false) }
    val visualFocused = isFocusedOverride || isFocused

    val shape = rememberArvioCardShape(ArvioSkin.radius.md)
    val outlineColor = if (useWhiteBorder) ArvioSkin.colors.focusOutline else ArvioSkin.colors.accent

    val context = LocalContext.current
    val density = LocalDensity.current
    val aspectRatio = 2f / 3f
    val imageRequest = remember(item.image, width, context, density) {
        val widthPx = with(density) { width.roundToPx() }
        val heightPx = (widthPx / aspectRatio).toInt().coerceAtLeast(1)
        ImageRequest.Builder(context)
            .data(item.image)
            .size(widthPx, heightPx)
            .precision(Precision.INEXACT)
            .allowHardware(true)
            .crossfade(250)  // Smooth 250ms fade transition
            .build()
    }

    Column(modifier = modifier.width(width)) {
        ArvioFocusableSurface(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(aspectRatio),
            shape = shape,
            backgroundColor = ArvioSkin.colors.surface,
            outlineColor = outlineColor,
            enableSystemFocus = enableSystemFocus,
            isFocusedOverride = isFocusedOverride,
            onClick = onClick,
            onFocusChanged = {
                isFocused = it
                if (it) onFocused()
            },
        ) { _ ->
            SkeletonBox(modifier = Modifier.fillMaxSize())
            AsyncImage(
                model = imageRequest,
                contentDescription = item.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .background(ArvioSkin.colors.surface),
            )
        }

        // Only show title and year when focused
        if (visualFocused) {
            Spacer(modifier = Modifier.height(ArvioSkin.spacing.x1))

            Text(
                text = item.title,
                style = ArvioSkin.typography.caption,
                color = ArvioSkin.colors.textPrimary,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )

            if (item.year.isNotBlank()) {
                Text(
                    text = item.year,
                    style = ArvioSkin.typography.caption,
                    color = ArvioSkin.colors.textMuted.copy(alpha = 0.65f),
                )
            }
        }
    }
}
