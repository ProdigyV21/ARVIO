package com.arflix.tv.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import coil.compose.AsyncImage
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.ui.skin.ArvioFocusableSurface
import com.arflix.tv.ui.skin.ArvioSkin
import com.arflix.tv.ui.skin.rememberArvioCardShape

/**
 * Continue Watching card with progress bar.
 *
 * Notes:
 * - Focus visuals are handled by `ArvioFocusableSurface` (no layout scaling).
 * - The `isFocused` param is preserved for compatibility with any external focus tracking.
 */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun ContinueWatchingCard(
    item: MediaItem,
    progress: Float = 0f, // 0.0 to 1.0
    episodeInfo: String? = null,
    timeRemaining: String? = null,
    width: Dp = 340.dp,
    isFocused: Boolean = false,
    onClick: () -> Unit = {},
) {
    val shape = rememberArvioCardShape(ArvioSkin.radius.md)

    Column(modifier = Modifier.width(width)) {
        ArvioFocusableSurface(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f),
            shape = shape,
            backgroundColor = ArvioSkin.colors.surface,
            onClick = onClick,
        ) { surfaceFocused ->
            val focused = isFocused || surfaceFocused

            Box(modifier = Modifier.fillMaxSize()) {
                AsyncImage(
                    model = item.backdrop ?: item.image,
                    contentDescription = item.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize(),
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    ArvioSkin.colors.background.copy(alpha = 0.85f),
                                ),
                                startY = 120f,
                            )
                        )
                )

                if (focused) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        Box(
                            modifier = Modifier
                                .size(56.dp)
                                .background(ArvioSkin.colors.accent, CircleShape),
                            contentAlignment = Alignment.Center,
                        ) {
                            Icon(
                                imageVector = Icons.Default.PlayArrow,
                                contentDescription = "Play",
                                tint = ArvioSkin.colors.textPrimary,
                                modifier = Modifier.size(32.dp),
                            )
                        }
                    }
                }

                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .padding(horizontal = ArvioSkin.spacing.x2, vertical = ArvioSkin.spacing.x2),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(3.dp)
                            .background(ArvioSkin.colors.focusOutline.copy(alpha = 0.20f)),
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(progress.coerceIn(0f, 1f))
                            .height(3.dp)
                            .background(ArvioSkin.colors.accent),
                    )
                }

                if (timeRemaining != null) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(ArvioSkin.spacing.x2)
                            .background(
                                color = ArvioSkin.colors.surfaceRaised.copy(alpha = 0.85f),
                                shape = rememberArvioCardShape(ArvioSkin.radius.sm),
                            )
                            .padding(horizontal = ArvioSkin.spacing.x2, vertical = ArvioSkin.spacing.x1),
                    ) {
                        Text(
                            text = timeRemaining,
                            style = ArvioSkin.typography.badge,
                            color = ArvioSkin.colors.textPrimary,
                        )
                    }
                }

                val typeLabel = if (item.mediaType == MediaType.TV) "TV" else "MOVIE"
                Box(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(ArvioSkin.spacing.x2)
                        .background(
                            color = ArvioSkin.colors.surfaceRaised.copy(alpha = 0.85f),
                            shape = rememberArvioCardShape(ArvioSkin.radius.sm),
                        )
                        .padding(horizontal = ArvioSkin.spacing.x2, vertical = ArvioSkin.spacing.x1),
                ) {
                    Text(
                        text = typeLabel,
                        style = ArvioSkin.typography.badge,
                        color = ArvioSkin.colors.textPrimary,
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(ArvioSkin.spacing.x2))

        Text(
            text = item.title,
            style = ArvioSkin.typography.cardTitle,
            color = if (isFocused) ArvioSkin.colors.textPrimary else ArvioSkin.colors.textMuted,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        val meta = episodeInfo ?: item.year.takeIf { it.isNotBlank() }
        if (meta != null) {
            Text(
                text = meta,
                style = ArvioSkin.typography.caption,
                color = ArvioSkin.colors.textMuted.copy(alpha = 0.85f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

/**
 * Compact Continue Watching card.
 */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun ContinueWatchingCardCompact(
    item: MediaItem,
    progress: Float = 0f,
    episodeInfo: String? = null,
    isFocused: Boolean = false,
    onClick: () -> Unit = {},
) {
    val shape = rememberArvioCardShape(ArvioSkin.radius.md)

    ArvioFocusableSurface(
        modifier = Modifier.width(380.dp),
        shape = shape,
        backgroundColor = ArvioSkin.colors.surface,
        onClick = onClick,
    ) { surfaceFocused ->
        val focused = isFocused || surfaceFocused

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(ArvioSkin.spacing.x2),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .width(100.dp)
                    .aspectRatio(16f / 9f)
                    .background(ArvioSkin.colors.surfaceRaised, rememberArvioCardShape(ArvioSkin.radius.sm)),
            ) {
                AsyncImage(
                    model = item.backdrop ?: item.image,
                    contentDescription = item.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize(),
                )
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .height(3.dp)
                        .background(ArvioSkin.colors.focusOutline.copy(alpha = 0.20f)),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(progress.coerceIn(0f, 1f))
                            .fillMaxSize()
                            .background(ArvioSkin.colors.accent),
                    )
                }
            }

            Spacer(modifier = Modifier.width(ArvioSkin.spacing.x3))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.title,
                    style = ArvioSkin.typography.cardTitle,
                    color = ArvioSkin.colors.textPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                if (episodeInfo != null) {
                    Text(
                        text = episodeInfo,
                        style = ArvioSkin.typography.caption,
                        color = ArvioSkin.colors.textMuted,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }

            if (focused) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(ArvioSkin.colors.accent, CircleShape),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Play",
                        tint = ArvioSkin.colors.textPrimary,
                        modifier = Modifier.size(24.dp),
                    )
                }
            }
        }
    }
}

