package com.arflix.tv.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.foundation.focusable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.tv.foundation.ExperimentalTvFoundationApi
import androidx.tv.material3.ExperimentalTvMaterial3Api
import com.arflix.tv.BuildConfig
import com.arflix.tv.R
import com.arflix.tv.util.LocalDeviceType
import com.arflix.tv.ui.theme.ArflixTypography
import com.arflix.tv.ui.theme.BackgroundElevated
import com.arflix.tv.ui.theme.Pink
import com.arflix.tv.ui.theme.TextPrimary
import com.arflix.tv.ui.theme.TextSecondary
import com.arflix.tv.updater.UpdateStatus

private data class ActionButtonConfig(
    val label: String,
    val action: () -> Unit,
    val highlighted: Boolean = false,
    val enabled: Boolean = true
)

@OptIn(ExperimentalTvMaterial3Api::class, ExperimentalTvFoundationApi::class)
@Composable
fun AppUpdateModal(
    status: UpdateStatus,
    onDownload: () -> Unit,
    onCancelDownload: () -> Unit,
    onInstall: () -> Unit,
    onDismiss: () -> Unit,
    onIgnore: () -> Unit
) {
    val buttons = remember(status) {
        when (status) {
            is UpdateStatus.UpdateAvailable -> listOf(
                ActionButtonConfig("Close", onDismiss),
                ActionButtonConfig("Ignore", onIgnore),
                ActionButtonConfig("Download", onDownload, highlighted = true)
            )
            is UpdateStatus.ReadyToInstall -> listOf(
                ActionButtonConfig("Close", onDismiss),
                ActionButtonConfig("Install", onInstall, highlighted = true)
            )
            is UpdateStatus.Installing -> listOf(
                ActionButtonConfig("Hide", onDismiss),
                ActionButtonConfig("Retry Install", onInstall, highlighted = true)
            )
            is UpdateStatus.Downloading -> listOf(
                ActionButtonConfig("Hide", onDismiss),
                ActionButtonConfig("Cancel", onCancelDownload)
            )
            is UpdateStatus.Failure -> listOf(
                ActionButtonConfig("Close", onDismiss),
                ActionButtonConfig("Retry", onDownload, highlighted = true)
            )
            else -> listOf(
                ActionButtonConfig("Close", onDismiss)
            )
        }
    }

    var focusedIndex by remember(buttons) { mutableIntStateOf(buttons.lastIndex) }
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = false
        )
    ) {
        ModalScrim(onDismiss = onDismiss) {
            Column(
                modifier = Modifier
                    .then(
                        if (LocalDeviceType.current.isTouchDevice()) Modifier.fillMaxWidth(0.92f).widthIn(max = 600.dp)
                        else Modifier.width(760.dp)
                    )
                    .background(BackgroundElevated, RoundedCornerShape(18.dp))
                    .padding(if (LocalDeviceType.current.isTouchDevice()) 20.dp else 28.dp)
                    .focusRequester(focusRequester)
                    .focusable()
                    .onPreviewKeyEvent { event ->
                        if (event.type != KeyEventType.KeyDown) return@onPreviewKeyEvent false
                        when (event.key) {
                            Key.Back, Key.Escape -> { onDismiss(); true }
                            Key.DirectionLeft -> {
                                focusedIndex = (focusedIndex - 1).coerceAtLeast(0)
                                true
                            }
                            Key.DirectionRight -> {
                                focusedIndex = (focusedIndex + 1).coerceAtMost(buttons.lastIndex)
                                true
                            }
                            Key.Enter, Key.DirectionCenter -> {
                                buttons.getOrNull(focusedIndex)?.action?.invoke()
                                true
                            }
                            else -> false
                        }
                    }
            ) {
                androidx.compose.material3.Text(
                    text = stringResource(R.string.app_update),
                    style = ArflixTypography.sectionTitle,
                    color = TextPrimary
                )
                Spacer(modifier = Modifier.height(10.dp))

                val subtitle = when (status) {
                    is UpdateStatus.Checking -> "Checking GitHub Releases..."
                    is UpdateStatus.UpdateAvailable -> "Update available: ${status.update.title} (${status.update.tag})"
                    is UpdateStatus.Downloading -> "Downloading update..."
                    is UpdateStatus.ReadyToInstall -> "${status.update.title} is ready to install."
                    is UpdateStatus.Installing -> "Installing update... Please follow the system prompt."
                    is UpdateStatus.Failure -> "Update failed."
                    is UpdateStatus.Success -> "You already have the latest version installed."
                    is UpdateStatus.Idle -> "No release information available."
                }
                androidx.compose.material3.Text(subtitle, style = ArflixTypography.body, color = TextSecondary)

                if (status is UpdateStatus.UpdateAvailable) {
                    Spacer(modifier = Modifier.height(8.dp))
                    androidx.compose.material3.Text(
                        text = "Current version ${BuildConfig.VERSION_NAME} -> latest ${status.update.tag}",
                        style = ArflixTypography.caption,
                        color = TextSecondary.copy(alpha = 0.78f)
                    )
                } else if (status is UpdateStatus.Success) {
                    Spacer(modifier = Modifier.height(8.dp))
                    androidx.compose.material3.Text(
                        text = "Current version ${BuildConfig.VERSION_NAME} is up to date",
                        style = ArflixTypography.caption,
                        color = TextSecondary.copy(alpha = 0.78f)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                if (status is UpdateStatus.Failure) {
                    androidx.compose.material3.Text(status.message, style = ArflixTypography.body, color = Pink)
                    Spacer(modifier = Modifier.height(12.dp))
                }

                when (status) {
                    is UpdateStatus.Downloading -> {
                        LinearProgressIndicator(
                            progress = status.progress ?: 0f,
                            modifier = Modifier.fillMaxWidth(),
                            color = Pink, // Uses ARVIO's Pink accent instead of SuccessGreen
                            trackColor = Color.White.copy(alpha = 0.08f)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        androidx.compose.material3.Text(
                            text = status.progress?.let { "${(it * 100).toInt()}%" } ?: "Preparing...",
                            style = ArflixTypography.caption,
                            color = TextSecondary
                        )
                    }
                    is UpdateStatus.ReadyToInstall -> {
                        androidx.compose.material3.Text("The latest ARVIO update has been downloaded and is ready to install.", style = ArflixTypography.body, color = TextPrimary)
                    }
                    is UpdateStatus.Installing -> {
                        androidx.compose.material3.Text("The Android package installer should appear. If it does not, you can try pressing Install again.", style = ArflixTypography.body, color = TextPrimary)
                    }
                    is UpdateStatus.UpdateAvailable -> {
                        if (status.update.notes.isNotBlank()) {
                            androidx.compose.material3.Text(
                                text = status.update.notes.take(900),
                                style = ArflixTypography.caption.copy(lineHeight = 18.sp),
                                color = TextSecondary,
                                modifier = Modifier.heightIn(max = 260.dp)
                            )
                        }
                    }
                    else -> {}
                }

                Spacer(modifier = Modifier.height(24.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    buttons.forEachIndexed { index, btn ->
                        UpdateActionButton(
                            label = btn.label,
                            isFocused = focusedIndex == index,
                            onClick = btn.action,
                            highlighted = btn.highlighted,
                            enabled = btn.enabled
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ModalScrim(
    onDismiss: () -> Unit,
    content: @Composable BoxScope.() -> Unit
) {
    val scrimInteraction = remember { MutableInteractionSource() }
    val contentInteraction = remember { MutableInteractionSource() }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.72f))
            .clickable(
                interactionSource = scrimInteraction,
                indication = null,
                onClick = onDismiss
            ),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier.clickable(
                interactionSource = contentInteraction,
                indication = null,
                onClick = {}
            ),
            content = content
        )
    }
}

@Composable
private fun UpdateActionButton(
    label: String,
    isFocused: Boolean,
    onClick: () -> Unit,
    highlighted: Boolean = false,
    enabled: Boolean = true
) {
    val background = when {
        !enabled -> Color.White.copy(alpha = 0.06f)
        highlighted && isFocused -> Pink
        isFocused -> Color.White.copy(alpha = 0.16f)
        highlighted -> Pink.copy(alpha = 0.18f)
        else -> Color.White.copy(alpha = 0.08f)
    }
    val textColor = when {
        !enabled -> TextSecondary.copy(alpha = 0.6f)
        highlighted && isFocused -> Color.Black
        highlighted -> Color.White
        isFocused -> TextPrimary
        else -> TextSecondary
    }

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(background)
            .clickable(enabled = enabled, onClick = onClick)
            .padding(horizontal = 20.dp, vertical = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        androidx.compose.material3.Text(
            text = label,
            color = textColor,
            style = ArflixTypography.button,
            fontSize = 13.sp
        )
    }
}
