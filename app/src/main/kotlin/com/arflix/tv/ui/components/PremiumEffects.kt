package com.arflix.tv.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.arflix.tv.ui.theme.BackgroundDark
import com.arflix.tv.ui.theme.BackgroundElevated
import com.arflix.tv.ui.theme.BackgroundGlass
import com.arflix.tv.ui.theme.BorderLight
import com.arflix.tv.ui.theme.Cyan
import com.arflix.tv.ui.theme.CyanGlow
import com.arflix.tv.ui.theme.Pink
import com.arflix.tv.ui.theme.PinkGlow
import com.arflix.tv.ui.theme.Purple
import com.arflix.tv.ui.theme.PurpleDark
import com.arflix.tv.ui.theme.PurpleGlow
import com.arflix.tv.ui.theme.PurpleLight
import com.arflix.tv.ui.theme.PurplePrimary
import com.arflix.tv.ui.theme.ParticleCyan
import com.arflix.tv.ui.theme.ParticlePurple
import com.arflix.tv.ui.theme.ParticlePink
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

/**
 * Premium visual effects for ARVIO
 * Inspired by NoopyTV's neon dark theme
 */

// ============================================
// ANIMATED GRADIENT BACKGROUND
// ============================================

@Composable
fun AnimatedGradientBackground(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit = {}
) {
    val infiniteTransition = rememberInfiniteTransition(label = "bgGradient")

    val animatedOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(10000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "gradientOffset"
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.radialGradient(
                    colors = listOf(
                        Color(0xFF0D0812),  // Dark purple tinted
                        BackgroundDark,
                        Color(0xFF0A0A0F)
                    ),
                    center = Offset(animatedOffset * 1000f, animatedOffset * 600f),
                    radius = 1500f
                )
            )
    ) {
        // Subtle purple color overlay that shifts
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            PurpleGlow.copy(alpha = 0.04f * animatedOffset),
                            Color.Transparent,
                            PurpleGlow.copy(alpha = 0.03f * (1f - animatedOffset)),
                            Color.Transparent
                        ),
                        start = Offset(0f, 0f),
                        end = Offset(1920f, 1080f)
                    )
                )
        )
        content()
    }
}

// ============================================
// FLOATING PARTICLES EFFECT
// ============================================

data class Particle(
    val x: Float,
    val y: Float,
    val radius: Float,
    val color: Color,
    val speed: Float,
    val angle: Float
)

@Composable
fun FloatingParticles(
    modifier: Modifier = Modifier,
    particleCount: Int = 20,
    colors: List<Color> = listOf(ParticleCyan, ParticlePurple, ParticlePink)
) {
    val particles = remember {
        List(particleCount) {
            Particle(
                x = Random.nextFloat(),
                y = Random.nextFloat(),
                radius = Random.nextFloat() * 3f + 1f,
                color = colors.random(),
                speed = Random.nextFloat() * 0.0003f + 0.0001f,
                angle = Random.nextFloat() * 360f
            )
        }
    }

    val infiniteTransition = rememberInfiniteTransition(label = "particles")
    val time by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(100000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "particleTime"
    )

    Canvas(modifier = modifier.fillMaxSize()) {
        particles.forEach { particle ->
            val newX = (particle.x + cos(particle.angle) * particle.speed * time) % 1f
            val newY = (particle.y + sin(particle.angle) * particle.speed * time * 0.5f) % 1f

            // Draw glow
            drawCircle(
                color = particle.color.copy(alpha = 0.3f),
                radius = particle.radius * 3,
                center = Offset(
                    if (newX < 0) newX + 1f else newX * size.width,
                    if (newY < 0) newY + 1f else newY * size.height
                )
            )

            // Draw particle
            drawCircle(
                color = particle.color,
                radius = particle.radius,
                center = Offset(
                    if (newX < 0) (newX + 1f) * size.width else newX * size.width,
                    if (newY < 0) (newY + 1f) * size.height else newY * size.height
                )
            )
        }
    }
}

// ============================================
// PULSING GLOW EFFECT
// ============================================

@Composable
fun PulsingGlow(
    modifier: Modifier = Modifier,
    color: Color = Cyan,
    pulseScale: Float = 1.3f,
    content: @Composable BoxScope.() -> Unit
) {
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")

    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = pulseScale,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = EaseInOutCubic),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulseScale"
    )

    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.6f,
        targetValue = 0.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = EaseInOutCubic),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulseAlpha"
    )

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        // Glow layer
        Box(
            modifier = Modifier
                .matchParentSize()
                .scale(scale)
                .blur(30.dp)
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            color.copy(alpha = alpha),
                            Color.Transparent
                        )
                    )
                )
        )
        content()
    }
}

// ============================================
// WAVE LOADING DOTS
// ============================================

@Composable
fun WaveLoadingDots(
    modifier: Modifier = Modifier,
    dotCount: Int = 3,
    dotSize: Dp = 10.dp,
    dotSpacing: Dp = 8.dp,
    color: Color = Cyan,
    secondaryColor: Color = Purple
) {
    val infiniteTransition = rememberInfiniteTransition(label = "waveDots")

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(dotSpacing),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(dotCount) { index ->
            val delay = index * 150

            val offsetY by infiniteTransition.animateFloat(
                initialValue = 0f,
                targetValue = -12f,
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = 600,
                        delayMillis = delay,
                        easing = EaseInOutCubic
                    ),
                    repeatMode = RepeatMode.Reverse
                ),
                label = "dotOffset$index"
            )

            val dotAlpha by infiniteTransition.animateFloat(
                initialValue = 0.4f,
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = 600,
                        delayMillis = delay,
                        easing = EaseInOutCubic
                    ),
                    repeatMode = RepeatMode.Reverse
                ),
                label = "dotAlpha$index"
            )

            Box(
                modifier = Modifier
                    .offset(y = offsetY.dp)
                    .size(dotSize)
                    .background(
                        Brush.radialGradient(
                            colors = listOf(
                                color.copy(alpha = dotAlpha),
                                secondaryColor.copy(alpha = dotAlpha * 0.5f)
                            )
                        ),
                        CircleShape
                    )
            )
        }
    }
}

// ============================================
// GRADIENT SWEEP LINE
// ============================================

@Composable
fun GradientSweepLine(
    modifier: Modifier = Modifier,
    height: Dp = 2.dp
) {
    val infiniteTransition = rememberInfiniteTransition(label = "sweep")

    val sweepProgress by infiniteTransition.animateFloat(
        initialValue = -0.3f,
        targetValue = 1.3f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = EaseInOutCubic),
            repeatMode = RepeatMode.Restart
        ),
        label = "sweepProgress"
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .clip(RoundedCornerShape(height / 2))
            .background(BackgroundElevated)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.3f)
                .fillMaxHeight()
                .offset(x = (sweepProgress * 400).dp)
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(
                            Color.Transparent,
                            PurpleDark,
                            Purple,
                            PurpleLight,
                            Color.Transparent
                        )
                    ),
                    RoundedCornerShape(height / 2)
                )
        )
    }
}

// ============================================
// GLASSMORPHIC CARD
// ============================================

@Composable
fun GlassmorphicCard(
    modifier: Modifier = Modifier,
    hasBorder: Boolean = true,
    borderGradient: Boolean = false,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .then(
                if (hasBorder) {
                    if (borderGradient) {
                        Modifier.background(
                            Brush.linearGradient(
                                colors = listOf(PurpleDark.copy(alpha = 0.3f), Purple.copy(alpha = 0.3f), PurpleLight.copy(alpha = 0.3f))
                            ),
                            RoundedCornerShape(20.dp)
                        )
                    } else {
                        Modifier.background(BorderLight, RoundedCornerShape(20.dp))
                    }
                } else Modifier
            )
            .padding(1.dp)
            .clip(RoundedCornerShape(19.dp))
            .background(BackgroundGlass)
            .blur(0.5.dp)
    ) {
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.White.copy(alpha = 0.05f),
                            Color.Transparent
                        ),
                        startY = 0f,
                        endY = 200f
                    )
                )
        )
        content()
    }
}

// ============================================
// GRADIENT TEXT
// ============================================

@Composable
fun gradientTextBrush(): Brush {
    return Brush.linearGradient(
        colors = listOf(PurpleDark, Purple, PurpleLight)
    )
}

// ============================================
// RING PULSE EFFECT
// ============================================

@Composable
fun RingPulseEffect(
    modifier: Modifier = Modifier,
    ringCount: Int = 3,
    color: Color = Cyan
) {
    val infiniteTransition = rememberInfiniteTransition(label = "rings")

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        repeat(ringCount) { index ->
            val delay = index * 400

            val scale by infiniteTransition.animateFloat(
                initialValue = 0.3f,
                targetValue = 2f,
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = 1500,
                        delayMillis = delay,
                        easing = EaseOut
                    ),
                    repeatMode = RepeatMode.Restart
                ),
                label = "ringScale$index"
            )

            val alpha by infiniteTransition.animateFloat(
                initialValue = 0.6f,
                targetValue = 0f,
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = 1500,
                        delayMillis = delay,
                        easing = EaseOut
                    ),
                    repeatMode = RepeatMode.Restart
                ),
                label = "ringAlpha$index"
            )

            Box(
                modifier = Modifier
                    .size(100.dp)
                    .scale(scale)
                    .drawBehind {
                        drawCircle(
                            color = color.copy(alpha = alpha),
                            style = Stroke(width = 2.dp.toPx())
                        )
                    }
            )
        }
    }
}

// ============================================
// SHIMMER EFFECT
// ============================================

@Composable
fun ShimmerEffect(
    modifier: Modifier = Modifier
): Modifier {
    val infiniteTransition = rememberInfiniteTransition(label = "shimmer")

    val shimmerOffset by infiniteTransition.animateFloat(
        initialValue = -1f,
        targetValue = 2f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "shimmerOffset"
    )

    return modifier.drawBehind {
        drawRect(
            brush = Brush.linearGradient(
                colors = listOf(
                    Color.Transparent,
                    Color.White.copy(alpha = 0.1f),
                    Color.Transparent
                ),
                start = Offset(size.width * shimmerOffset, 0f),
                end = Offset(size.width * (shimmerOffset + 0.5f), size.height)
            )
        )
    }
}

// Easing functions
private val EaseInOutCubic = CubicBezierEasing(0.4f, 0f, 0.2f, 1f)
private val EaseOut = CubicBezierEasing(0f, 0f, 0.2f, 1f)
