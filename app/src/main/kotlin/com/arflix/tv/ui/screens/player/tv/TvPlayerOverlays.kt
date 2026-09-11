package com.arflix.tv.ui.screens.player.tv

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.VolumeDown
import androidx.compose.material.icons.filled.VolumeMute
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arflix.tv.R
import com.arflix.tv.ui.skin.LocalAccentColorOverride
import com.arflix.tv.ui.theme.ArflixTypography
import com.arflix.tv.ui.theme.TextPrimary
import com.arflix.tv.ui.theme.TextSecondary

@Composable
fun TvVolumeIndicator(
    isVisible: Boolean,
    currentVolume: Int,
    maxVolume: Int,
    isMuted: Boolean,
    playerAccent: Color,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn(androidx.compose.animation.core.tween(150)),
        exit = fadeOut(androidx.compose.animation.core.tween(200)),
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(Color.Black.copy(alpha = 0.7f), RoundedCornerShape(12.dp))
                .padding(16.dp)
        ) {
            Icon(
                imageVector = when {
                    isMuted || currentVolume == 0 -> Icons.Default.VolumeMute
                    currentVolume < maxVolume / 2 -> Icons.Default.VolumeDown
                    else -> Icons.Default.VolumeUp
                },
                contentDescription = stringResource(R.string.player_cd_volume),
                tint = Color.White,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .width(8.dp)
                    .height(100.dp)
                    .background(Color.White.copy(alpha = 0.3f), RoundedCornerShape(4.dp))
            ) {
                val volFrac = if (maxVolume > 0) (currentVolume.toFloat() / maxVolume).coerceIn(0f, 1f) else 0f
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(volFrac)
                        .background(playerAccent, RoundedCornerShape(4.dp))
                        .align(Alignment.BottomCenter)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = if (isMuted) stringResource(R.string.player_muted) else "${if (maxVolume > 0) currentVolume * 100 / maxVolume else 0}%",
                style = ArflixTypography.caption,
                color = Color.White
            )
        }
    }
}

@Composable
fun TvAspectIndicator(
    isVisible: Boolean,
    aspectModeLabel: String,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn(androidx.compose.animation.core.tween(150)),
        exit = fadeOut(androidx.compose.animation.core.tween(200)),
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .background(Color.Black.copy(alpha = 0.65f), RoundedCornerShape(10.dp))
                .padding(horizontal = 24.dp, vertical = 14.dp)
        ) {
            Text(
                text = aspectModeLabel,
                style = ArflixTypography.body.copy(fontSize = 18.sp, fontWeight = FontWeight.Medium),
                color = Color.White
            )
        }
    }
}

@Composable
fun TvSkipOverlay(
    isVisible: Boolean,
    skipAmount: Int,
    currentPositionMs: Long,
    durationMs: Long,
    skipPreviewPositionMs: Long,
    formatTime: (Long) -> String,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn(androidx.compose.animation.core.tween(150)),
        exit = fadeOut(androidx.compose.animation.core.tween(200)),
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth(0.72f)
                .background(Color.Black.copy(alpha = 0.55f), RoundedCornerShape(12.dp))
                .padding(horizontal = 20.dp, vertical = 14.dp)
        ) {
            Text(
                text = if (skipAmount >= 0) "+${skipAmount}s" else "${skipAmount}s",
                style = ArflixTypography.sectionTitle.copy(
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    shadow = Shadow(
                        color = Color.Black,
                        offset = Offset(2f, 2f),
                        blurRadius = 8f
                    )
                ),
                color = Color.White
            )

            if (durationMs > 0L) {
                val previewPos = if (skipPreviewPositionMs > 0L) skipPreviewPositionMs else currentPositionMs
                val previewProgress = (previewPos.toFloat() / durationMs.toFloat()).coerceIn(0f, 1f)

                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(
                        text = formatTime(previewPos),
                        style = ArflixTypography.caption.copy(
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium,
                        ),
                        color = Color.White,
                    )
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(5.dp)
                            .background(Color.White.copy(alpha = 0.25f), RoundedCornerShape(3.dp))
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(previewProgress)
                                .height(5.dp)
                                .background(Color.White, RoundedCornerShape(3.dp))
                        )
                    }
                    Text(
                        text = formatTime(durationMs),
                        style = ArflixTypography.caption.copy(
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium,
                        ),
                        color = Color.White.copy(alpha = 0.75f),
                    )
                }
            }
        }
    }
}

@Composable
fun TvErrorOverlay(
    isVisible: Boolean,
    errorMessage: String?,
    isSetupError: Boolean,
    focusIndex: Int,
    onRetry: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(
        visible = isVisible && errorMessage != null,
        enter = fadeIn(androidx.compose.animation.core.tween(150)),
        exit = fadeOut(androidx.compose.animation.core.tween(200)),
        modifier = modifier
    ) {
        val accentColor = if (isSetupError) Color(0xFF3B82F6) else Color(0xFFEF4444)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.9f)),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .width(480.dp)
                    .background(Color(0xFF1A1A1A), RoundedCornerShape(16.dp))
                    .border(1.dp, accentColor.copy(alpha = 0.3f), RoundedCornerShape(16.dp))
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(72.dp)
                        .background(accentColor.copy(alpha = 0.15f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = if (isSetupError) Icons.Default.Settings else Icons.Default.ErrorOutline,
                        contentDescription = null,
                        tint = accentColor,
                        modifier = Modifier.size(40.dp)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = if (isSetupError) stringResource(R.string.player_addon_setup_required) else stringResource(R.string.player_playback_error),
                    style = ArflixTypography.sectionTitle,
                    color = TextPrimary
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = errorMessage ?: stringResource(R.string.player_error_generic),
                    style = ArflixTypography.body,
                    color = TextSecondary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                if (isSetupError) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = stringResource(R.string.player_addon_setup_hint),
                        style = ArflixTypography.caption,
                        color = TextSecondary.copy(alpha = 0.7f),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    if (!isSetupError) {
                        TvErrorButton(
                            text = stringResource(R.string.retry).uppercase(),
                            icon = Icons.Default.Refresh,
                            isFocused = focusIndex == 0,
                            isPrimary = true,
                            onClick = onRetry
                        )
                    }
                    TvErrorButton(
                        text = stringResource(R.string.back).uppercase(),
                        isFocused = if (isSetupError) focusIndex == 0 else focusIndex == 1,
                        isPrimary = isSetupError,
                        onClick = onBack
                    )
                }
            }
        }
    }
}

@Composable
fun TvErrorButton(
    text: String,
    icon: ImageVector? = null,
    isFocused: Boolean,
    isPrimary: Boolean,
    onClick: () -> Unit
) {
    val btnAccent = LocalAccentColorOverride.current ?: Color.White
    val scale by animateFloatAsState(if (isFocused) 1.05f else 1f, label = "scale")

    Box(
        modifier = Modifier
            .focusable()
            .clickable { onClick() }
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .background(
                color = when {
                    isFocused -> btnAccent
                    isPrimary -> Color(0xFFE50914)
                    else -> Color(0xFF2A2A2A)
                },
                shape = RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 24.dp, vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = if (isFocused) Color.Black else Color.White,
                    modifier = Modifier.size(18.dp)
                )
            }
            Text(
                text = text,
                style = ArflixTypography.button.copy(
                    fontWeight = FontWeight.Bold,
                    color = if (isFocused) Color.Black else Color.White
                )
            )
        }
    }
}
