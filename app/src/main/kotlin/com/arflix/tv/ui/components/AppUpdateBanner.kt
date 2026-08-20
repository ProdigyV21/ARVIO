package com.arflix.tv.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import com.arflix.tv.R
import com.arflix.tv.ui.theme.ArflixTypography
import com.arflix.tv.ui.theme.BackgroundElevated
import com.arflix.tv.ui.theme.Pink
import com.arflix.tv.ui.theme.TextPrimary
import com.arflix.tv.ui.theme.TextSecondary
import com.arflix.tv.updater.UpdateStatus
import com.arflix.tv.util.LocalDeviceType


@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun AppUpdateBanner(
    status: UpdateStatus,
    onDownload: () -> Unit,
    onCancelDownload: () -> Unit,
    onInstall: () -> Unit,
    onDismiss: () -> Unit,
    onIgnore: () -> Unit
) {
    when (status) {

        // =========================================================
        // UPDATE AVAILABLE
        // =========================================================

        is UpdateStatus.UpdateAvailable -> {
            UpdateAvailableBanner(
                status = status,
                onDownload = onDownload,
                onIgnore = onIgnore,
                onDismiss = onDismiss
            )
        }

        // =========================================================
        // DOWNLOADING
        // =========================================================

        is UpdateStatus.Downloading -> {
            DownloadingBanner(
                status = status,
                onCancelDownload = onCancelDownload,
                onDismiss = onDismiss
            )
        }

        // =========================================================
        // READY TO INSTALL
        // =========================================================

        is UpdateStatus.ReadyToInstall -> {
            ReadyToInstallBanner(
                onInstall = onInstall,
                onDismiss = onDismiss
            )
        }

        // =========================================================
        // INSTALLING
        // =========================================================

        is UpdateStatus.Installing -> {
            InstallingBanner(
                onDismiss = onDismiss
            )
        }

        // =========================================================
        // FAILURE
        // =========================================================

        is UpdateStatus.Failure -> {
            FailureBanner(
                status = status,
                onRetry = onDownload,
                onDismiss = onDismiss
            )
        }

        // No banner for these states.
        is UpdateStatus.Checking,
        is UpdateStatus.Success,
        is UpdateStatus.Idle -> Unit
    }
}


// =================================================================
// UPDATE AVAILABLE
// =================================================================

@Composable
private fun UpdateAvailableBanner(
    status: UpdateStatus.UpdateAvailable,
    onDownload: () -> Unit,
    onIgnore: () -> Unit,
    onDismiss: () -> Unit
) {
    val isTv = !LocalDeviceType.current.isTouchDevice()

    val horizontalPadding =
        if (isTv) 40.dp else 16.dp

    val verticalPadding =
        if (isTv) 18.dp else 10.dp

    val iconSize =
        if (isTv) 58.dp else 42.dp

    val iconFontSize =
        if (isTv) 32.sp else 25.sp

    val versionFontSize =
        if (isTv) 26.sp else 18.sp

    val subtitleFontSize =
        if (isTv) 17.sp else 13.sp

    val infoSize =
        if (isTv) 50.dp else 36.dp

    val infoFontSize =
        if (isTv) 24.sp else 18.sp

    val updateFontSize =
        if (isTv) 19.sp else 15.sp

    val updateHorizontalPadding =
        if (isTv) 30.dp else 20.dp

    val updateVerticalPadding =
        if (isTv) 14.dp else 10.dp

    val closeSize =
        if (isTv) 52.dp else 40.dp

    val closeFontSize =
        if (isTv) 40.sp else 29.sp

    val spacingLarge =
        if (isTv) 18.dp else 12.dp

    val spacingMedium =
        if (isTv) 14.dp else 8.dp

    val sizeText = formatFileSize(
        status.update.assetSizeBytes
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(BackgroundElevated)
            .padding(
                horizontal = horizontalPadding,
                vertical = verticalPadding
            )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // -----------------------------------------------------
            // DOWNLOAD ICON
            // -----------------------------------------------------

            Box(
                modifier = Modifier
                    .size(iconSize)
                    .clip(CircleShape)
                    .background(
                        Color.White.copy(alpha = 0.08f)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "↓",
                    color = TextPrimary,
                    fontSize = iconFontSize
                )
            }

            Spacer(
                modifier = Modifier.width(spacingLarge)
            )

            // -----------------------------------------------------
            // VERSION + MESSAGE
            // -----------------------------------------------------

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = buildString {
                        append(status.update.tag)

                        if (sizeText != null) {
                            append(" • ")
                            append(sizeText)
                        }
                    },
                    color = TextPrimary,
                    fontSize = versionFontSize,
                    lineHeight = versionFontSize * 1.2f,
                    maxLines = 1
                )

                Spacer(
                    modifier = Modifier.height(3.dp)
                )

                Text(
                    text = stringResource(
                        R.string.update_msg_available,
                        status.update.title,
                        status.update.tag
                    ),
                    color = TextSecondary,
                    fontSize = subtitleFontSize,
                    maxLines = 1
                )
            }

            Spacer(
                modifier = Modifier.width(spacingLarge)
            )

            // -----------------------------------------------------
            // INFO
            // -----------------------------------------------------

            Box(
                modifier = Modifier
                    .size(infoSize)
                    .clip(CircleShape)
                    .background(
                        Color.White.copy(alpha = 0.06f)
                    )
                    .focusable(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "i",
                    color = TextPrimary,
                    fontSize = infoFontSize
                )
            }

            Spacer(
                modifier = Modifier.width(spacingMedium)
            )

            // -----------------------------------------------------
            // IGNORE
            // -----------------------------------------------------

            Box(
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(50)
                    )
                    .background(
                        Color.White.copy(alpha = 0.08f)
                    )
                    .clickable(
                        onClick = onIgnore
                    )
                    .focusable()
                    .padding(
                        horizontal =
                            if (isTv) 22.dp else 14.dp,
                        vertical =
                            if (isTv) 12.dp else 9.dp
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(
                        R.string.update_btn_ignore
                    ),
                    color = TextPrimary,
                    fontSize =
                        if (isTv) 17.sp else 13.sp
                )
            }

            Spacer(
                modifier = Modifier.width(spacingMedium)
            )

            // -----------------------------------------------------
            // UPDATE
            // -----------------------------------------------------

            Box(
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(50)
                    )
                    .background(Color.White)
                    .clickable(
                        onClick = onDownload
                    )
                    .focusable()
                    .padding(
                        horizontal =
                            updateHorizontalPadding,
                        vertical =
                            updateVerticalPadding
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(
                        R.string.update_btn_download
                    ),
                    color = Color.Black,
                    fontSize = updateFontSize
                )
            }

            Spacer(
                modifier = Modifier.width(spacingMedium)
            )

            // -----------------------------------------------------
            // CLOSE
            // -----------------------------------------------------

            Box(
                modifier = Modifier
                    .size(closeSize)
                    .clip(CircleShape)
                    .clickable(
                        onClick = onDismiss
                    )
                    .focusable(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "×",
                    color = TextPrimary,
                    fontSize = closeFontSize,
                    lineHeight = closeFontSize
                )
            }
        }
    }
}


// =================================================================
// DOWNLOADING
// =================================================================

@Composable
private fun DownloadingBanner(
    status: UpdateStatus.Downloading,
    onCancelDownload: () -> Unit,
    onDismiss: () -> Unit
) {
    val isTv = !LocalDeviceType.current.isTouchDevice()

    val horizontalPadding =
        if (isTv) 40.dp else 16.dp

    val verticalPadding =
        if (isTv) 16.dp else 10.dp

    val messageSize =
        if (isTv) 20.sp else 15.sp

    val percentageSize =
        if (isTv) 17.sp else 13.sp

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(BackgroundElevated)
            .padding(
                horizontal = horizontalPadding,
                vertical = verticalPadding
            )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "↓",
                    color = Pink,
                    fontSize =
                        if (isTv) 30.sp else 24.sp
                )

                Spacer(
                    modifier = Modifier.width(12.dp)
                )

                Text(
                    text = stringResource(
                        R.string.update_msg_downloading
                    ),
                    color = TextPrimary,
                    fontSize = messageSize,
                    modifier = Modifier.weight(1f)
                )

                Text(
                    text = status.progress?.let {
                        "${(it * 100).toInt()}%"
                    } ?: stringResource(
                        R.string.update_msg_preparing
                    ),
                    color = TextSecondary,
                    fontSize = percentageSize
                )

                Spacer(
                    modifier = Modifier.width(12.dp)
                )

                BannerButton(
                    text = stringResource(R.string.cancel),
                    onClick = onCancelDownload,
                    isTv = isTv
                )

                Spacer(
                    modifier = Modifier.width(8.dp)
                )

                BannerCloseButton(
                    onClick = onDismiss,
                    isTv = isTv
                )
            }

            Spacer(
                modifier = Modifier.height(
                    if (isTv) 12.dp else 8.dp
                )
            )

            LinearProgressIndicator(
                progress = {
                    status.progress ?: 0f
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(
                        if (isTv) 4.dp else 3.dp
                    ),
                color = Pink,
                trackColor = Color.White.copy(alpha = 0.08f)
            )
        }
    }
}


// =================================================================
// READY TO INSTALL
// =================================================================

@Composable
private fun ReadyToInstallBanner(
    onInstall: () -> Unit,
    onDismiss: () -> Unit
) {
    val isTv = !LocalDeviceType.current.isTouchDevice()

    UpdateSimpleBanner(
        icon = "✓",
        message = stringResource(
            R.string.update_msg_ready
        ),
        actionText = stringResource(
            R.string.update_btn_install
        ),
        action = onInstall,
        onDismiss = onDismiss,
        isTv = isTv
    )
}


// =================================================================
// INSTALLING
// =================================================================

@Composable
private fun InstallingBanner(
    onDismiss: () -> Unit
) {
    val isTv = !LocalDeviceType.current.isTouchDevice()

    UpdateSimpleBanner(
        icon = "↓",
        message = stringResource(
            R.string.update_msg_installing
        ),
        actionText = null,
        action = null,
        onDismiss = onDismiss,
        isTv = isTv
    )
}


// =================================================================
// FAILURE
// =================================================================

@Composable
private fun FailureBanner(
    status: UpdateStatus.Failure,
    onRetry: () -> Unit,
    onDismiss: () -> Unit
) {
    val isTv = !LocalDeviceType.current.isTouchDevice()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(BackgroundElevated)
            .padding(
                horizontal =
                    if (isTv) 40.dp else 16.dp,
                vertical =
                    if (isTv) 16.dp else 10.dp
            )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "!",
                color = Pink,
                fontSize =
                    if (isTv) 30.sp else 24.sp
            )

            Spacer(
                modifier = Modifier.width(12.dp)
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = stringResource(
                        R.string.update_msg_failed
                    ),
                    color = TextPrimary,
                    fontSize =
                        if (isTv) 20.sp else 15.sp
                )

                Spacer(
                    modifier = Modifier.height(2.dp)
                )

                Text(
                    text = status.message,
                    color = TextSecondary,
                    fontSize =
                        if (isTv) 15.sp else 12.sp,
                    maxLines = 1
                )
            }

            BannerButton(
                text = stringResource(
                    R.string.retry
                ),
                onClick = onRetry,
                highlighted = true,
                isTv = isTv
            )

            Spacer(
                modifier = Modifier.width(8.dp)
            )

            BannerCloseButton(
                onClick = onDismiss,
                isTv = isTv
            )
        }
    }
}


// =================================================================
// SIMPLE STATUS BANNER
// =================================================================

@Composable
private fun UpdateSimpleBanner(
    icon: String,
    message: String,
    actionText: String?,
    action: (() -> Unit)?,
    onDismiss: () -> Unit,
    isTv: Boolean
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(BackgroundElevated)
            .padding(
                horizontal =
                    if (isTv) 40.dp else 16.dp,
                vertical =
                    if (isTv) 16.dp else 10.dp
            )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = icon,
                color = Pink,
                fontSize =
                    if (isTv) 30.sp else 24.sp
            )

            Spacer(
                modifier = Modifier.width(12.dp)
            )

            Text(
                text = message,
                color = TextPrimary,
                fontSize =
                    if (isTv) 20.sp else 15.sp,
                modifier = Modifier.weight(1f)
            )

            if (
                actionText != null &&
                action != null
            ) {
                BannerButton(
                    text = actionText,
                    onClick = action,
                    highlighted = true,
                    isTv = isTv
                )

                Spacer(
                    modifier = Modifier.width(8.dp)
                )
            }

            BannerCloseButton(
                onClick = onDismiss,
                isTv = isTv
            )
        }
    }
}


// =================================================================
// BUTTON
// =================================================================

@Composable
private fun BannerButton(
    text: String,
    onClick: () -> Unit,
    highlighted: Boolean = false,
    isTv: Boolean = false
) {
    val background =
        if (highlighted) {
            Pink
        } else {
            Color.White.copy(alpha = 0.08f)
        }

    val textColor =
        if (highlighted) {
            Color.Black
        } else {
            TextPrimary
        }

    Box(
        modifier = Modifier
            .clip(
                RoundedCornerShape(
                    if (isTv) 14.dp else 10.dp
                )
            )
            .background(background)
            .clickable(
                onClick = onClick
            )
            .focusable()
            .padding(
                horizontal =
                    if (isTv) 24.dp else 14.dp,
                vertical =
                    if (isTv) 12.dp else 8.dp
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = textColor,
            style = ArflixTypography.button,
            fontSize =
                if (isTv) 16.sp else 12.sp
        )
    }
}


// =================================================================
// CLOSE BUTTON
// =================================================================

@Composable
private fun BannerCloseButton(
    onClick: () -> Unit,
    isTv: Boolean
) {
    Box(
        modifier = Modifier
            .size(
                if (isTv) 52.dp else 40.dp
            )
            .clip(CircleShape)
            .clickable(
                onClick = onClick
            )
            .focusable(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "×",
            color = TextPrimary,
            fontSize =
                if (isTv) 38.sp else 29.sp,
            lineHeight =
                if (isTv) 38.sp else 29.sp
        )
    }
}


// =================================================================
// FILE SIZE
// =================================================================

private fun formatFileSize(
    bytes: Long?
): String? {
    if (bytes == null || bytes <= 0L) {
        return null
    }

    val mb = bytes / (1024.0 * 1024.0)

    return when {
        mb >= 100 -> {
            "${mb.toInt()} MB"
        }

        mb >= 10 -> {
            String.format("%.0f MB", mb)
        }

        else -> {
            String.format("%.1f MB", mb)
        }
    }
}