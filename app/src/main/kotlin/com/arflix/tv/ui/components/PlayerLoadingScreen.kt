package com.arflix.tv.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import coil.compose.AsyncImage
import com.arflix.tv.ui.theme.BackgroundDark
import com.arflix.tv.ui.theme.BackgroundElevated
import com.arflix.tv.ui.theme.BackgroundGlass
import com.arflix.tv.ui.theme.BackgroundOverlay
import com.arflix.tv.ui.theme.Cyan
import com.arflix.tv.ui.theme.ParticleCyan
import com.arflix.tv.ui.theme.ParticlePurple
import com.arflix.tv.ui.theme.ParticlePurpleDark
import com.arflix.tv.ui.theme.ParticlePurpleLight
import com.arflix.tv.ui.theme.Pink
import com.arflix.tv.ui.theme.Purple
import com.arflix.tv.ui.theme.PurpleDark
import com.arflix.tv.ui.theme.PurpleLight
import com.arflix.tv.ui.theme.PurplePrimary
import com.arflix.tv.ui.theme.TextPrimary
import com.arflix.tv.ui.theme.TextSecondary

/**
 * Premium Player Loading Screen
 * Shows when loading video sources/buffering
 * Features blurred backdrop, animated play icon, and gradient effects
 */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun PlayerLoadingScreen(
    modifier: Modifier = Modifier,
    backdropUrl: String? = null,
    title: String = "",
    subtitle: String? = null,
    loadingMessage: String = "Loading sources..."
) {
    val infiniteTransition = rememberInfiniteTransition(label = "playerLoading")

    // Pulsing play icon
    val playScale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.15f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = EaseInOutCubic),
            repeatMode = RepeatMode.Reverse
        ),
        label = "playScale"
    )

    val playGlow by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 0.7f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = EaseInOutCubic),
            repeatMode = RepeatMode.Reverse
        ),
        label = "playGlow"
    )

    // Ring animation
    val ringScale by infiniteTransition.animateFloat(
        initialValue = 0.8f,
        targetValue = 1.5f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = EaseOut),
            repeatMode = RepeatMode.Restart
        ),
        label = "ringScale"
    )

    val ringAlpha by infiniteTransition.animateFloat(
        initialValue = 0.6f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = EaseOut),
            repeatMode = RepeatMode.Restart
        ),
        label = "ringAlpha"
    )

    Box(modifier = modifier.fillMaxSize()) {
        // Backdrop image (blurred)
        if (backdropUrl != null) {
            AsyncImage(
                model = backdropUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .blur(20.dp)
            )
        }

        // Dark overlay with gradient - static for performance
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            BackgroundDark.copy(alpha = 0.7f),
                            BackgroundDark.copy(alpha = 0.85f),
                            BackgroundDark.copy(alpha = 0.95f)
                        )
                    )
                )
        )

        // Reduced particles for faster loading
        FloatingParticles(
            particleCount = 6,  // Reduced from 12 for performance
            colors = listOf(ParticlePurple, ParticlePurpleLight)
        )

        // Center content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(48.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Play button with rings - purple theme
            Box(
                modifier = Modifier.size(140.dp),
                contentAlignment = Alignment.Center
            ) {
                // Outer expanding ring
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .scale(ringScale)
                        .clip(CircleShape)
                        .background(
                            Brush.radialGradient(
                                colors = listOf(
                                    PurplePrimary.copy(alpha = ringAlpha),
                                    Color.Transparent
                                )
                            )
                        )
                )

                // Second ring (delayed)
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .scale(ringScale * 0.85f)
                        .clip(CircleShape)
                        .background(
                            Brush.radialGradient(
                                colors = listOf(
                                    PurpleLight.copy(alpha = ringAlpha * 0.7f),
                                    Color.Transparent
                                )
                            )
                        )
                )

                // Glow behind play button
                Box(
                    modifier = Modifier
                        .size(90.dp)
                        .scale(playScale)
                        .blur(25.dp)
                        .background(
                            Brush.radialGradient(
                                colors = listOf(
                                    PurplePrimary.copy(alpha = playGlow),
                                    PurpleLight.copy(alpha = playGlow * 0.5f),
                                    Color.Transparent
                                )
                            ),
                            CircleShape
                        )
                )

                // Play button circle
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .scale(playScale)
                        .clip(CircleShape)
                        .background(
                            Brush.linearGradient(
                                colors = listOf(PurpleDark, PurplePrimary, PurpleLight)
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Loading",
                        tint = Color.White,
                        modifier = Modifier.size(40.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            // Title
            if (title.isNotEmpty()) {
                Text(
                    text = title,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                if (subtitle != null) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = subtitle,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = TextSecondary
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))
            }

            // Wave dots - purple theme
            WaveLoadingDots(
                dotCount = 4,
                dotSize = 12.dp,
                dotSpacing = 12.dp,
                color = PurplePrimary,
                secondaryColor = PurpleLight
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Loading message
            Text(
                text = loadingMessage,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = TextSecondary,
                letterSpacing = 1.sp
            )
        }
    }
}

/**
 * Compact buffering indicator for overlay on video
 */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun BufferingIndicator(
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "buffering")

    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "rotation"
    )

    Box(
        modifier = modifier
            .size(60.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(BackgroundDark.copy(alpha = 0.8f)),
        contentAlignment = Alignment.Center
    ) {
        // Rotating gradient ring
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        ) {
            androidx.compose.foundation.Canvas(
                modifier = Modifier.fillMaxSize()
            ) {
                drawArc(
                    brush = Brush.sweepGradient(
                        colors = listOf(
                            Cyan,
                            Purple,
                            Pink,
                            Color.Transparent
                        )
                    ),
                    startAngle = rotation,
                    sweepAngle = 270f,
                    useCenter = false,
                    style = androidx.compose.ui.graphics.drawscope.Stroke(
                        width = 4.dp.toPx(),
                        cap = androidx.compose.ui.graphics.StrokeCap.Round
                    )
                )
            }
        }
    }
}

/**
 * Source loading screen - shown when resolving stream sources
 */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun SourceLoadingScreen(
    modifier: Modifier = Modifier,
    sourceName: String = "Torrentio",
    progress: Float? = null // Optional progress 0-1
) {
    val infiniteTransition = rememberInfiniteTransition(label = "sourceLoading")

    val iconGlow by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 0.8f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = EaseInOutCubic),
            repeatMode = RepeatMode.Reverse
        ),
        label = "iconGlow"
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundOverlay),
        contentAlignment = Alignment.Center
    ) {
        // Glassmorphic card
        Box(
            modifier = Modifier
                .width(350.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            Cyan.copy(alpha = 0.15f),
                            Purple.copy(alpha = 0.15f)
                        )
                    )
                )
                .padding(1.dp)
                .clip(RoundedCornerShape(19.dp))
                .background(BackgroundGlass)
        ) {
            Column(
                modifier = Modifier.padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Source icon with glow
                Box(
                    modifier = Modifier.size(64.dp),
                    contentAlignment = Alignment.Center
                ) {
                    // Glow
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .blur(15.dp)
                            .background(
                                Brush.radialGradient(
                                    colors = listOf(
                                        Cyan.copy(alpha = iconGlow),
                                        Color.Transparent
                                    )
                                ),
                                CircleShape
                            )
                    )

                    // Icon circle
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(
                                Brush.linearGradient(
                                    colors = listOf(Cyan, Purple)
                                )
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = sourceName.take(1).uppercase(),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Resolving from $sourceName",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TextPrimary
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Finding best quality stream...",
                    fontSize = 13.sp,
                    color = TextSecondary
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Progress or sweep line
                if (progress != null) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(4.dp)
                            .clip(RoundedCornerShape(2.dp))
                            .background(BackgroundElevated)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(progress)
                                .fillMaxHeight()
                                .background(
                                    Brush.horizontalGradient(
                                        colors = listOf(Cyan, Purple, Pink)
                                    ),
                                    RoundedCornerShape(2.dp)
                                )
                        )
                    }
                } else {
                    GradientSweepLine(
                        modifier = Modifier.fillMaxWidth(),
                        height = 3.dp
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                WaveLoadingDots(
                    dotCount = 3,
                    dotSize = 8.dp,
                    dotSpacing = 8.dp,
                    color = Cyan,
                    secondaryColor = Purple
                )
            }
        }
    }
}

private val EaseInOutCubic = CubicBezierEasing(0.4f, 0f, 0.2f, 1f)
private val EaseOut = CubicBezierEasing(0f, 0f, 0.2f, 1f)
