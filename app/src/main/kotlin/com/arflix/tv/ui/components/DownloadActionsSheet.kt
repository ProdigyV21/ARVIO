package com.arflix.tv.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import com.arflix.tv.data.db.DownloadEntity
import com.arflix.tv.data.db.DownloadStatus
import com.arflix.tv.ui.theme.ArflixTypography
import com.arflix.tv.ui.theme.TextSecondary
import com.arflix.tv.util.formatBytes

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun DownloadActionsSheet(
    download: DownloadEntity?,
    onDismiss: () -> Unit,
    onPause: (Long) -> Unit,
    onResume: (Long) -> Unit,
    onCancel: (Long) -> Unit,
    onDelete: (Long) -> Unit,
    onRetry: (Long) -> Unit
) {
    val isVisible = download != null
    val status = download?.let {
        DownloadStatus.entries.find { e -> e.name == it.status } ?: DownloadStatus.QUEUED
    }

    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.6f))
                .clickable(onClick = onDismiss)
                .zIndex(20f)
        )
    }

    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically { it } + fadeIn(),
        exit = slideOutVertically { it } + fadeOut()
    ) {
        Box(
            modifier = Modifier.fillMaxSize().zIndex(21f),
            contentAlignment = Alignment.BottomCenter
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                    .background(Color(0xFF1A1A1A))
                    .navigationBarsPadding()
                    .padding(bottom = 8.dp)
            ) {
                // Handle
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 12.dp, bottom = 8.dp)
                        .size(width = 40.dp, height = 4.dp)
                        .background(Color.White.copy(alpha = 0.3f), RoundedCornerShape(2.dp))
                )

                // Header
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        val titleLine = download?.title.orEmpty()
                        val subtitleLine = buildString {
                            download?.season?.let { s ->
                                download.episode?.let { e ->
                                    append("S${s.toString().padStart(2, '0')}E${e.toString().padStart(2, '0')}")
                                    download.episodeTitle?.takeIf { it.isNotBlank() }?.let { append(" · $it") }
                                }
                            }
                            if (isEmpty()) {
                                when (status) {
                                    DownloadStatus.COMPLETED -> download?.fileSize?.let { sz ->
                                        if (sz > 0) append(formatBytes(sz))
                                    }
                                    DownloadStatus.DOWNLOADING -> append("${download?.progress ?: 0}%")
                                    DownloadStatus.PAUSED -> append("Paused · ${download?.progress ?: 0}%")
                                    else -> {}
                                }
                            }
                        }
                        Text(
                            text = titleLine,
                            style = ArflixTypography.sectionTitle,
                            color = Color.White,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        if (subtitleLine.isNotBlank()) {
                            Text(
                                text = subtitleLine,
                                style = ArflixTypography.caption,
                                color = TextSecondary,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Default.Close, contentDescription = "Close", tint = Color.White)
                    }
                }

                HorizontalDivider(color = Color.White.copy(alpha = 0.1f))

                if (download != null) {
                    when (status!!) {
                        DownloadStatus.DOWNLOADING -> {
                            ActionRow(Icons.Default.Pause, "Pause") {
                                onDismiss(); onPause(download.id)
                            }
                            ActionRow(Icons.Default.Cancel, "Cancel download") {
                                onDismiss(); onCancel(download.id)
                            }
                        }
                        DownloadStatus.PAUSED -> {
                            ActionRow(Icons.Default.PlayArrow, "Resume") {
                                onDismiss(); onResume(download.id)
                            }
                            ActionRow(Icons.Default.Cancel, "Cancel download") {
                                onDismiss(); onCancel(download.id)
                            }
                        }
                        DownloadStatus.FAILED, DownloadStatus.QUEUED -> {
                            ActionRow(Icons.Default.Refresh, "Retry") {
                                onDismiss(); onRetry(download.id)
                            }
                        }
                        DownloadStatus.COMPLETED -> Unit
                    }
                    ActionRow(
                        icon = Icons.Default.Delete,
                        label = "Delete download",
                        tint = Color(0xFFFF5252)
                    ) {
                        onDismiss(); onDelete(download.id)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun ActionRow(
    icon: ImageVector,
    label: String,
    tint: Color = Color.White,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = tint,
            modifier = Modifier.size(22.dp)
        )
        Text(
            text = label,
            style = ArflixTypography.body,
            color = tint,
            modifier = Modifier.padding(start = 16.dp)
        )
    }
    HorizontalDivider(color = Color.White.copy(alpha = 0.07f))
}

