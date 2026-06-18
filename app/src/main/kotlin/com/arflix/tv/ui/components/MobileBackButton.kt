package com.arflix.tv.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.arflix.tv.R
import com.arflix.tv.util.DeviceType
import com.arflix.tv.util.LocalDeviceType

/**
 * A persistent, visible back button for phone layouts.
 *
 * On Android phones and tablets running gesture navigation, the system nav bar
 * auto-hides after a short idle, leaving users with no visible way to go back
 * until they swipe up to re-reveal it. On TV this is a non-issue (Back key on
 * the remote is always available) but on touch devices it makes deep screens
 * feel trapped \u2014 the exact complaint in issue #43.
 *
 * This composable renders an IconButton-shaped circle in the top-start corner
 * of the screen, inset from the status bar, but only when the device type is
 * [DeviceType.PHONE]. On TV and tablet it returns an empty Box so callers can
 * drop it unconditionally into their UI without per-device branching.
 *
 * Callers should place this inside a `Box` root (or similar) that fills the
 * screen, so the absolute `.align(Alignment.TopStart)` positioning lands above
 * their main content.
 *
 * Usage:
 * ```
 * Box(modifier = Modifier.fillMaxSize()) {
 *     MyScreenContent(...)
 *     MobileBackButton(onBack = onBack)
 * }
 * ```
 */
@Composable
fun MobileBackButton(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val deviceType = LocalDeviceType.current
    if (deviceType != DeviceType.PHONE) return

    Box(
        modifier = modifier
            .statusBarsPadding()
            .padding(start = 8.dp, top = 8.dp)
            .size(48.dp) // Generous touch target
            .clip(CircleShape)
            .clickable(onClick = onBack),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = stringResource(R.string.back),
            tint = Color.White.copy(alpha = 0.9f),
            modifier = Modifier.size(28.dp)
        )
    }
}
