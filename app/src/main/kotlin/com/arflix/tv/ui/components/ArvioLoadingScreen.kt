package com.arflix.tv.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import com.arflix.tv.ui.theme.BackgroundOverlay
import com.arflix.tv.ui.theme.Cyan
import com.arflix.tv.ui.theme.ParticleCyan
import com.arflix.tv.ui.theme.ParticlePurple
import com.arflix.tv.ui.theme.ParticlePink
import com.arflix.tv.ui.theme.ParticlePurpleLight
import com.arflix.tv.ui.theme.ParticlePurpleDark
import com.arflix.tv.ui.theme.Pink
import com.arflix.tv.ui.theme.Purple
import com.arflix.tv.ui.theme.PurpleDark
import com.arflix.tv.ui.theme.PurpleLight
import com.arflix.tv.ui.theme.PurplePrimary
import com.arflix.tv.ui.theme.TextPrimary
import com.arflix.tv.ui.theme.TextSecondary

/**
 * Premium ARVIO Loading Screen - Optimized for TV Performance
 * Removed heavy effects: blur, particles, multiple animated layers
 * Clean, lightweight design that runs smooth on TV hardware
 */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun ArvioLoadingScreen(
    modifier: Modifier = Modifier,
    showText: Boolean = true
) {
    // Single simple animation for logo pulse
    val infiniteTransition = rememberInfiniteTransition(label = "loading")

    val logoAlpha by infiniteTransition.animateFloat(
        initialValue = 0.7f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = EaseInOutCubic),
            repeatMode = RepeatMode.Reverse
        ),
        label = "logoAlpha"
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF0A0A0F))  // Static dark background - no gradient animation
    ) {
        // Center content - no particles for performance
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Simple logo without blur layers
            Text(
                text = "ARVIO",
                fontSize = 72.sp,
                fontWeight = FontWeight.Black,
                letterSpacing = 12.sp,
                color = Color.White.copy(alpha = logoAlpha)
            )

            Spacer(modifier = Modifier.height(60.dp))

            // Simple loading dots - reduced animation complexity
            SimpleLoadingDots(
                dotCount = 4,
                dotSize = 12.dp,
                color = PurplePrimary
            )

            if (showText) {
                Spacer(modifier = Modifier.height(30.dp))

                Text(
                    text = "Loading...",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = TextSecondary,
                    letterSpacing = 1.sp
                )
            }
        }
    }
}

/**
 * Simple loading dots - minimal animation for TV performance
 * Made public for use across the app
 */
@Composable
fun SimpleLoadingDots(
    dotCount: Int = 3,
    dotSize: Dp = 10.dp,
    color: Color = PurplePrimary
) {
    val infiniteTransition = rememberInfiniteTransition(label = "dots")

    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(dotCount) { index ->
            val alpha by infiniteTransition.animateFloat(
                initialValue = 0.3f,
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = 800,
                        delayMillis = index * 200,
                        easing = EaseInOutCubic
                    ),
                    repeatMode = RepeatMode.Reverse
                ),
                label = "dot$index"
            )

            Box(
                modifier = Modifier
                    .size(dotSize)
                    .background(color.copy(alpha = alpha), CircleShape)
            )
        }
    }
}

/**
 * Compact loading indicator for inline use - optimized for TV performance
 */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun CompactLoadingIndicator(
    modifier: Modifier = Modifier,
    text: String? = null
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Use simple dots instead of heavy wave animation
        SimpleLoadingDots(
            dotCount = 3,
            dotSize = 8.dp,
            color = PurplePrimary
        )

        if (text != null) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = text,
                fontSize = 12.sp,
                color = TextSecondary
            )
        }
    }
}

/**
 * Overlay loading screen - optimized for TV performance
 * Removed blur, ring pulse, and sweep line effects
 */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun LoadingOverlay(
    modifier: Modifier = Modifier,
    message: String = "Loading..."
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundOverlay),
        contentAlignment = Alignment.Center
    ) {
        // Simple card without glassmorphic effect
        Box(
            modifier = Modifier
                .padding(48.dp)
                .background(
                    Color(0xFF1A1A2E),
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(20.dp)
                )
                .padding(1.dp)
        ) {
            Column(
                modifier = Modifier.padding(40.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Simple loading dots instead of ring pulse
                SimpleLoadingDots(
                    dotCount = 4,
                    dotSize = 12.dp,
                    color = PurplePrimary
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = message,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = TextPrimary
                )
            }
        }
    }
}

private val EaseInOutCubic = CubicBezierEasing(0.4f, 0f, 0.2f, 1f)
