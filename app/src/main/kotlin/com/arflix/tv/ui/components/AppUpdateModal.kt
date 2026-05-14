package com.arflix.tv.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material.icons.filled.NewReleases
import androidx.compose.material.icons.filled.SystemUpdate
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import com.arflix.tv.updater.AppUpdate
import com.arflix.tv.updater.UpdateStatus
import com.arflix.tv.ui.skin.ArvioFocusableSurface
import com.arflix.tv.ui.theme.AccentRed
import com.arflix.tv.ui.theme.ArflixTypography
import com.arflix.tv.ui.theme.BackgroundCard
import com.arflix.tv.ui.theme.SuccessGreen

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun AppUpdateModal(
    status: UpdateStatus,
    onDownload: () -> Unit,
    onInstall: () -> Unit,
    onDismiss: () -> Unit,
    onIgnore: () -> Unit
) {
    if (status is UpdateStatus.Idle || status is UpdateStatus.Checking || status is UpdateStatus.Success) {
        return
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.6f)),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .width(480.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFF262626),
                                BackgroundCard
                            )
                        )
                    )
                    .border(
                        width = 1.dp,
                        color = Color.White.copy(alpha = 0.1f),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(32.dp)
                    .animateContentSize(animationSpec = tween(300))
            ) {
                when (status) {
                    is UpdateStatus.UpdateAvailable -> {
                        UpdateAvailableContent(
                            update = status.update,
                            onDownload = onDownload,
                            onDismiss = onDismiss,
                            onIgnore = onIgnore
                        )
                    }
                    is UpdateStatus.Downloading -> {
                        DownloadingContent(
                            update = status.update,
                            progress = status.progress
                        )
                    }
                    is UpdateStatus.ReadyToInstall -> {
                        ReadyToInstallContent(
                            update = status.update,
                            onInstall = onInstall,
                            onDismiss = onDismiss
                        )
                    }
                    is UpdateStatus.Installing -> {
                        InstallingContent(
                            onRetry = onInstall,
                            onDismiss = onDismiss
                        )
                    }
                    is UpdateStatus.Failure -> {
                        FailureContent(
                            message = status.message,
                            onRetry = onDownload, // Retry download/check
                            onDismiss = onDismiss
                        )
                    }
                    else -> {}
                }
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun UpdateAvailableContent(
    update: AppUpdate,
    onDownload: () -> Unit,
    onDismiss: () -> Unit,
    onIgnore: () -> Unit
) {
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(SuccessGreen.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.NewReleases,
                    contentDescription = null,
                    tint = SuccessGreen,
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = "New Update Available",
                    style = ArflixTypography.titleLarge,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Version ${update.title}",
                    style = ArflixTypography.bodyMedium,
                    color = Color.White.copy(alpha = 0.6f)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (update.notes.isNotBlank()) {
            Text(
                text = "What's New:",
                style = ArflixTypography.labelLarge,
                color = Color.White.copy(alpha = 0.8f),
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(8.dp))
            val notes = update.notes.split("\n").filter { it.isNotBlank() }.take(5)
            notes.forEach { note ->
                Row(modifier = Modifier.padding(bottom = 6.dp)) {
                    Text(text = "• ", color = SuccessGreen, fontSize = 14.sp)
                    Text(
                        text = note.removePrefix("- ").removePrefix("* ").trim(),
                        style = ArflixTypography.bodyMedium,
                        color = Color.White.copy(alpha = 0.7f),
                        fontSize = 14.sp,
                        lineHeight = 20.sp
                    )
                }
            }
            if (update.notes.split("\n").size > 5) {
                Text(
                    text = "and more improvements...",
                    style = ArflixTypography.bodySmall,
                    color = Color.White.copy(alpha = 0.5f),
                    modifier = Modifier.padding(start = 12.dp, top = 4.dp)
                )
            }
        } else {
            Text(
                text = "Bug fixes and performance improvements.",
                style = ArflixTypography.bodyMedium,
                color = Color.White.copy(alpha = 0.7f)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            ModalButton(
                text = "Ignore Version",
                icon = null,
                onClick = onIgnore,
                containerColor = Color.Transparent,
                contentColor = Color.White.copy(alpha = 0.5f)
            )
            Spacer(modifier = Modifier.width(12.dp))
            ModalButton(
                text = "Later",
                icon = Icons.Filled.Close,
                onClick = onDismiss,
                containerColor = Color.White.copy(alpha = 0.1f),
                contentColor = Color.White
            )
            Spacer(modifier = Modifier.width(12.dp))
            ModalButton(
                text = "Download",
                icon = Icons.Filled.Download,
                onClick = onDownload,
                containerColor = SuccessGreen,
                contentColor = Color.White
            )
        }
    }
}

@Composable
private fun DownloadingContent(update: AppUpdate, progress: Float?) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Filled.SystemUpdate,
            contentDescription = null,
            tint = Color.White.copy(alpha = 0.8f),
            modifier = Modifier.size(48.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Downloading Update...",
            style = ArflixTypography.titleMedium,
            color = Color.White,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(24.dp))
        
        // Progress Bar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp)
                .clip(RoundedCornerShape(3.dp))
                .background(Color.White.copy(alpha = 0.1f))
        ) {
            if (progress != null) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(progress)
                        .height(6.dp)
                        .clip(RoundedCornerShape(3.dp))
                        .background(SuccessGreen)
                )
            } else {
                // Indeterminate
                CircularProgressIndicator(
                    color = SuccessGreen,
                    strokeWidth = 2.dp,
                    modifier = Modifier.size(6.dp)
                )
            }
        }
        
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = if (progress != null) "${(progress * 100).toInt()}%" else "Connecting...",
            style = ArflixTypography.labelMedium,
            color = Color.White.copy(alpha = 0.5f)
        )
    }
}

@Composable
private fun ReadyToInstallContent(
    update: AppUpdate,
    onInstall: () -> Unit,
    onDismiss: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .background(SuccessGreen.copy(alpha = 0.2f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Filled.Check,
                contentDescription = null,
                tint = SuccessGreen,
                modifier = Modifier.size(32.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Download Complete",
            style = ArflixTypography.titleMedium,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Version ${update.title} is ready to install.",
            style = ArflixTypography.bodyMedium,
            color = Color.White.copy(alpha = 0.7f)
        )
        Spacer(modifier = Modifier.height(32.dp))
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            ModalButton(
                text = "Later",
                icon = Icons.Filled.Close,
                onClick = onDismiss,
                containerColor = Color.White.copy(alpha = 0.1f),
                contentColor = Color.White
            )
            Spacer(modifier = Modifier.width(16.dp))
            ModalButton(
                text = "Install Now",
                icon = Icons.Filled.SystemUpdate,
                onClick = onInstall,
                containerColor = SuccessGreen,
                contentColor = Color.White
            )
        }
    }
}

@Composable
private fun InstallingContent(
    onRetry: () -> Unit,
    onDismiss: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            color = SuccessGreen,
            modifier = Modifier.size(48.dp),
            strokeWidth = 3.dp
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Finalizing Installation...",
            style = ArflixTypography.titleMedium,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Please follow the system prompt to complete the update.\nThe app will restart once finished.",
            style = ArflixTypography.bodyMedium,
            color = Color.White.copy(alpha = 0.7f),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            ModalButton(
                text = "Cancel",
                icon = Icons.Filled.Close,
                onClick = onDismiss,
                containerColor = Color.White.copy(alpha = 0.1f),
                contentColor = Color.White
            )
            Spacer(modifier = Modifier.width(16.dp))
            ModalButton(
                text = "Retry Prompt",
                icon = Icons.Filled.Refresh,
                onClick = onRetry,
                containerColor = Color.White.copy(alpha = 0.15f),
                contentColor = Color.White
            )
        }
    }
}

@Composable
private fun FailureContent(
    message: String,
    onRetry: () -> Unit,
    onDismiss: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .background(AccentRed.copy(alpha = 0.2f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Filled.ErrorOutline,
                contentDescription = null,
                tint = AccentRed,
                modifier = Modifier.size(32.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Update Failed",
            style = ArflixTypography.titleMedium,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = message,
            style = ArflixTypography.bodyMedium,
            color = Color.White.copy(alpha = 0.7f),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )
        Spacer(modifier = Modifier.height(32.dp))
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            ModalButton(
                text = "Close",
                icon = Icons.Filled.Close,
                onClick = onDismiss,
                containerColor = Color.White.copy(alpha = 0.1f),
                contentColor = Color.White
            )
            Spacer(modifier = Modifier.width(16.dp))
            ModalButton(
                text = "Retry",
                icon = Icons.Filled.Refresh,
                onClick = onRetry,
                containerColor = AccentRed,
                contentColor = Color.White
            )
        }
    }
}

@Composable
private fun ModalButton(
    text: String,
    icon: ImageVector?,
    onClick: () -> Unit,
    containerColor: Color,
    contentColor: Color
) {
    ArvioFocusableSurface(
        onClick = onClick,
        shape = RoundedCornerShape(12.dp),
        color = containerColor,
        focusedColor = Color.White,
        modifier = Modifier.height(44.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = contentColor,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            Text(
                text = text,
                style = ArflixTypography.labelLarge,
                color = contentColor,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}
