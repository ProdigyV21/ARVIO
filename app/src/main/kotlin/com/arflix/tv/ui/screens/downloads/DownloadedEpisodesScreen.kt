package com.arflix.tv.ui.screens.downloads

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.HourglassEmpty
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import coil.compose.AsyncImage
import com.arflix.tv.data.db.DownloadEntity
import com.arflix.tv.data.db.DownloadStatus
import com.arflix.tv.ui.components.DownloadActionsSheet
import com.arflix.tv.ui.theme.AccentGreen
import com.arflix.tv.ui.theme.ArflixTypography
import com.arflix.tv.ui.theme.TextSecondary
import com.arflix.tv.ui.theme.appBackgroundDark
import com.arflix.tv.util.formatBytes

@OptIn(ExperimentalTvMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun DownloadedEpisodesScreen(
    tmdbId: Int,
    title: String,
    viewModel: DownloadsViewModel = hiltViewModel(),
    onPlayEpisode: (DownloadEntity) -> Unit,
    onBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val episodes = uiState.seriesDownloads[tmdbId] ?: emptyList()
    val completedCount = episodes.count { it.status == DownloadStatus.COMPLETED.name }
    var actionSheetDownload by remember { mutableStateOf<DownloadEntity?>(null) }

    val bySeason = episodes
        .sortedWith(compareBy({ it.season ?: 0 }, { it.episode ?: 0 }))
        .groupBy { it.season ?: 0 }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(appBackgroundDark())
        ) {
            // Header
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 8.dp)
            ) {
                IconButton(onClick = onBack) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                }
                Spacer(Modifier.width(8.dp))
                Column {
                    Text(
                        text = title,
                        style = ArflixTypography.sectionTitle,
                        color = Color.White
                    )
                    Text(
                        text = "$completedCount of ${episodes.size} episodes downloaded",
                        style = ArflixTypography.caption,
                        color = TextSecondary
                    )
                }
            }

            LazyColumn(
                contentPadding = PaddingValues(bottom = 48.dp)
            ) {
                bySeason.forEach { (season, seasonEps) ->
                    stickyHeader(key = "season_$season") {
                        Text(
                            text = if (season == 0) "Specials" else "Season $season",
                            style = ArflixTypography.label,
                            color = TextSecondary,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(appBackgroundDark())
                                .padding(horizontal = 16.dp, vertical = 10.dp)
                        )
                    }
                    seasonEps.forEach { ep ->
                        item(key = ep.id) {
                            EpisodeDownloadCard(
                                download = ep,
                                onClick = {
                                    if (ep.status == DownloadStatus.COMPLETED.name) {
                                        onPlayEpisode(ep)
                                    }
                                },
                                onLongPress = { actionSheetDownload = ep }
                            )
                        }
                    }
                }
            }
        }

        DownloadActionsSheet(
            download = actionSheetDownload,
            onDismiss = { actionSheetDownload = null },
            onPause = { viewModel.pause(it) },
            onResume = { viewModel.resume(it) },
            onCancel = { viewModel.cancel(it) },
            onDelete = { viewModel.delete(it) },
            onRetry = { viewModel.retry(it) }
        )
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalTvMaterial3Api::class)
@Composable
private fun EpisodeDownloadCard(
    download: DownloadEntity,
    onClick: () -> Unit,
    onLongPress: () -> Unit
) {
    val status = DownloadStatus.entries.find { it.name == download.status } ?: DownloadStatus.QUEUED
    val epLabel = buildString {
        download.season?.let { append("S${it.toString().padStart(2, '0')}") }
        download.episode?.let { append("E${it.toString().padStart(2, '0')}") }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .combinedClickable(onClick = onClick, onLongClick = onLongPress)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Thumbnail
        Box(
            modifier = Modifier
                .width(120.dp)
                .height(68.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(Color(0xFF1A1A1A)),
            contentAlignment = Alignment.Center
        ) {
            val imageUrl = download.backdropPath ?: download.posterPath
            if (!imageUrl.isNullOrBlank()) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.25f))
                )
            }
            when (status) {
                DownloadStatus.COMPLETED -> Icon(
                    Icons.Default.PlayArrow,
                    contentDescription = null,
                    tint = Color.White.copy(alpha = 0.9f),
                    modifier = Modifier.size(28.dp)
                )
                DownloadStatus.PAUSED -> Icon(
                    Icons.Default.Pause,
                    contentDescription = null,
                    tint = Color(0xFFFFCD3C),
                    modifier = Modifier.size(28.dp)
                )
                DownloadStatus.QUEUED -> Icon(
                    Icons.Default.HourglassEmpty,
                    contentDescription = null,
                    tint = Color.White.copy(alpha = 0.7f),
                    modifier = Modifier.size(22.dp)
                )
                DownloadStatus.FAILED -> Icon(
                    Icons.Default.Error,
                    contentDescription = null,
                    tint = Color.Red,
                    modifier = Modifier.size(24.dp)
                )
                DownloadStatus.DOWNLOADING -> Unit
            }
        }

        // Info
        Column(modifier = Modifier.weight(1f).padding(end = 4.dp)) {
            if (epLabel.isNotEmpty()) {
                Text(
                    text = epLabel,
                    style = ArflixTypography.caption,
                    color = TextSecondary,
                    fontSize = 11.sp
                )
            }
            download.episodeTitle?.let {
                Text(
                    text = it,
                    style = ArflixTypography.body,
                    color = Color.White,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(Modifier.height(4.dp))
            when (status) {
                DownloadStatus.DOWNLOADING -> {
                    LinearProgressIndicator(
                        progress = { download.progress / 100f },
                        modifier = Modifier.fillMaxWidth().height(2.dp),
                        color = AccentGreen,
                        trackColor = Color.White.copy(alpha = 0.2f)
                    )
                    Spacer(Modifier.height(3.dp))
                    Text(
                        text = "${download.progress}% · ${formatBytes(download.downloadedBytes)}",
                        style = ArflixTypography.caption,
                        color = AccentGreen,
                        fontSize = 10.sp
                    )
                }
                DownloadStatus.COMPLETED -> Text(
                    text = formatBytes(download.fileSize),
                    style = ArflixTypography.caption,
                    color = TextSecondary,
                    fontSize = 10.sp
                )
                DownloadStatus.PAUSED -> {
                    LinearProgressIndicator(
                        progress = { download.progress / 100f },
                        modifier = Modifier.fillMaxWidth().height(2.dp),
                        color = Color(0xFFFFCD3C),
                        trackColor = Color.White.copy(alpha = 0.2f)
                    )
                    Spacer(Modifier.height(3.dp))
                    Text(
                        text = "Paused · ${download.progress}%",
                        style = ArflixTypography.caption,
                        color = Color(0xFFFFCD3C),
                        fontSize = 10.sp
                    )
                }
                DownloadStatus.QUEUED -> Text(
                    text = "Queued",
                    style = ArflixTypography.caption,
                    color = TextSecondary,
                    fontSize = 10.sp
                )
                DownloadStatus.FAILED -> Text(
                    text = "Failed — tap ⋮ to retry",
                    style = ArflixTypography.caption,
                    color = Color.Red,
                    fontSize = 10.sp
                )
            }
        }

        IconButton(onClick = onLongPress) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "Options",
                tint = TextSecondary,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

