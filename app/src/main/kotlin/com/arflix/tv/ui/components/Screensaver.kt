package com.arflix.tv.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import com.arflix.tv.ui.theme.ArflixTypography
import com.arflix.tv.ui.theme.Pink
import kotlinx.coroutines.delay
import kotlin.random.Random

/**
 * Screensaver component for burn-in prevention on OLED/AMOLED displays.
 * Shows a floating logo that moves around the screen.
 *
 * @param isVisible Whether the screensaver is visible
 * @param idleTimeoutMs Time in ms before screensaver activates (default 5 minutes)
 * @param onDismiss Callback when user interacts to dismiss
 */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun Screensaver(
    isVisible: Boolean,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    // Random position state
    var positionX by remember { mutableStateOf(Random.nextFloat()) }
    var positionY by remember { mutableStateOf(Random.nextFloat()) }
    var directionX by remember { mutableStateOf(if (Random.nextBoolean()) 1f else -1f) }
    var directionY by remember { mutableStateOf(if (Random.nextBoolean()) 1f else -1f) }

    // Animate position
    LaunchedEffect(isVisible) {
        if (isVisible) {
            while (true) {
                delay(50) // Update every 50ms for smooth animation

                // Move position
                positionX += directionX * 0.003f
                positionY += directionY * 0.002f

                // Bounce off edges
                if (positionX <= 0f || positionX >= 1f) {
                    directionX *= -1f
                    positionX = positionX.coerceIn(0f, 1f)
                }
                if (positionY <= 0f || positionY >= 1f) {
                    directionY *= -1f
                    positionY = positionY.coerceIn(0f, 1f)
                }
            }
        }
    }

    // Pulsing animation for the logo
    val infiniteTransition = rememberInfiniteTransition(label = "screensaver")
    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.6f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse"
    )

    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn(animationSpec = tween(500)),
        exit = fadeOut(animationSpec = tween(300))
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(Color.Black)
                .focusable()
                .onKeyEvent {
                    onDismiss()
                    true
                }
        ) {
            // Floating logo/text
            Box(
                modifier = Modifier
                    .offset(
                        x = (screenWidth - 200.dp) * positionX,
                        y = (screenHeight - 100.dp) * positionY
                    )
                    .size(200.dp, 100.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "ARFLIX",
                    style = ArflixTypography.heroTitle,
                    color = Pink.copy(alpha = alpha)
                )
            }

            // Subtle hint at bottom
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .offset(y = (-20).dp)
            ) {
                Text(
                    text = "Press any key to continue",
                    style = ArflixTypography.caption,
                    color = Color.White.copy(alpha = 0.3f)
                )
            }
        }
    }
}

/**
 * Composable that tracks idle time and shows screensaver
 */
@Composable
fun ScreensaverHost(
    idleTimeoutMs: Long = 5 * 60 * 1000, // 5 minutes default
    content: @Composable (resetIdleTimer: () -> Unit) -> Unit
) {
    var isScreensaverActive by remember { mutableStateOf(false) }
    var lastActivityTime by remember { mutableStateOf(System.currentTimeMillis()) }

    // Check for idle timeout
    LaunchedEffect(Unit) {
        while (true) {
            delay(1000) // Check every second
            val currentTime = System.currentTimeMillis()
            if (currentTime - lastActivityTime >= idleTimeoutMs && !isScreensaverActive) {
                isScreensaverActive = true
            }
        }
    }

    val resetIdleTimer: () -> Unit = {
        lastActivityTime = System.currentTimeMillis()
        if (isScreensaverActive) {
            isScreensaverActive = false
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        content(resetIdleTimer)

        Screensaver(
            isVisible = isScreensaverActive,
            onDismiss = resetIdleTimer
        )
    }
}
