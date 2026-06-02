package com.arflix.tv.ui.screens.downloads

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
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
import androidx.compose.ui.zIndex
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import coil.compose.AsyncImage
import com.arflix.tv.data.db.DownloadEntity
import com.arflix.tv.data.db.DownloadStatus
import com.arflix.tv.ui.components.CardLayoutMode
import com.arflix.tv.ui.components.DownloadActionsSheet
import com.arflix.tv.ui.components.rememberCardLayoutMode
import com.arflix.tv.ui.theme.ArflixTypography
import com.arflix.tv.ui.theme.TextSecondary
import com.arflix.tv.util.formatBytes

@OptIn(ExperimentalTvMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun DownloadsTab(
    uiState: DownloadsViewModel.UiState,
    onPlayMovie: (DownloadEntity) -> Unit,
    onSeriesClick: (tmdbId: Int, title: String) -> Unit,
    onPause: (Long) -> Unit,
    onResume: (Long) -> Unit,
    onCancel: (Long) -> Unit,
    onDelete: (Long) -> Unit,
    onRetry: (Long) -> Unit,
    onDeleteAllSeries: (Int) -> Unit = {}
) {
    val isEmpty = uiState.movieDownloads.isEmpty() && uiState.seriesDownloads.isEmpty()
    val layoutMode = rememberCardLayoutMode()
    val usePoster = layoutMode == CardLayoutMode.POSTER
    var actionSheetDownload by remember { mutableStateOf<DownloadEntity?>(null) }
    var seriesActionSheet by remember { mutableStateOf<Pair<Int, String>?>(null) }

    if (isEmpty) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = Icons.Default.Download,
                    contentDescription = null,
                    tint = Color.White.copy(alpha = 0.2f),
                    modifier = Modifier.size(80.dp)
                )
                Spacer(Modifier.height(16.dp))
                Text(
                    text = "No downloads yet",
                    style = ArflixTypography.body,
                    color = Color.White.copy(alpha = 0.5f)
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = "Download movies and episodes for offline watching",
                    style = ArflixTypography.caption,
                    color = Color.White.copy(alpha = 0.3f)
                )
            }
        }
        return
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            if (uiState.movieDownloads.isNotEmpty()) {
                Text(
                    text = "Movies",
                    style = ArflixTypography.sectionTitle,
                    color = Color.White,
                    modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 12.dp)
                )
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp)
                ) {
                    items(uiState.movieDownloads, key = { it.id }) { download ->
                        DownloadPosterCard(
                            download = download,
                            usePoster = usePoster,
                            onClick = {
                                if (download.status == DownloadStatus.COMPLETED.name) {
                                    onPlayMovie(download)
                                }
                            },
                            onLongPress = { actionSheetDownload = download }
                        )
                    }
                }
                Spacer(Modifier.height(24.dp))
            }

            if (uiState.seriesDownloads.isNotEmpty()) {
                Text(
                    text = "Series",
                    style = ArflixTypography.sectionTitle,
                    color = Color.White,
                    modifier = Modifier.padding(start = 16.dp, bottom = 12.dp)
                )
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp)
                ) {
                    items(
                        uiState.seriesMetadata.entries.toList(),
                        key = { it.key }
                    ) { (tmdbId, representative) ->
                        val episodes = uiState.seriesDownloads[tmdbId] ?: emptyList()
                        val completedCount = episodes.count { it.status == DownloadStatus.COMPLETED.name }
                        SeriesPosterCard(
                            representative = representative,
                            episodeCount = completedCount,
                            totalCount = episodes.size,
                            usePoster = usePoster,
                            onClick = { onSeriesClick(tmdbId, representative.title) },
                            onLongPress = { seriesActionSheet = tmdbId to representative.title }
                        )
                    }
                }
                Spacer(Modifier.height(48.dp))
            }
        }

        DownloadActionsSheet(
            download = actionSheetDownload,
            onDismiss = { actionSheetDownload = null },
            onPause = onPause,
            onResume = onResume,
            onCancel = onCancel,
            onDelete = onDelete,
            onRetry = onRetry
        )

        SeriesActionsSheet(
            series = seriesActionSheet,
            onDismiss = { seriesActionSheet = null },
            onDeleteAll = { tmdbId ->
                seriesActionSheet = null
                onDeleteAllSeries(tmdbId)
            }
        )
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalTvMaterial3Api::class)
@Composable
private fun DownloadPosterCard(
    download: DownloadEntity,
    usePoster: Boolean,
    onClick: () -> Unit,
    onLongPress: () -> Unit
) {
    val status = DownloadStatus.entries.find { it.name == download.status } ?: DownloadStatus.QUEUED
    val cardWidth = if (usePoster) 140.dp else 220.dp
    val cardHeight = if (usePoster) 210.dp else 124.dp
    val imageUrl = if (usePoster) download.posterPath
                   else download.backdropPath ?: download.posterPath

    Column(modifier = Modifier.width(cardWidth)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(cardHeight)
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFF1A1A1A))
                .combinedClickable(onClick = onClick, onLongClick = onLongPress)
        ) {
            if (!imageUrl.isNullOrBlank()) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = download.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            when (status) {
                DownloadStatus.DOWNLOADING -> {
                    LinearProgressIndicator(
                        progress = { download.progress / 100f },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(3.dp)
                            .align(Alignment.BottomCenter),
                        color = Color.White,
                        trackColor = Color.White.copy(alpha = 0.2f)
                    )
                    Text(
                        text = "${download.progress}%",
                        style = ArflixTypography.caption,
                        color = Color.White,
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(6.dp)
                    )
                }
                DownloadStatus.QUEUED -> {
                    CircularProgressIndicator(
                        modifier = Modifier.size(32.dp).align(Alignment.Center),
                        color = Color.White,
                        strokeWidth = 2.dp
                    )
                }
                DownloadStatus.PAUSED -> {
                    Icon(
                        Icons.Default.Pause,
                        contentDescription = "Paused",
                        tint = Color.White.copy(alpha = 0.9f),
                        modifier = Modifier.size(40.dp).align(Alignment.Center)
                    )
                }
                DownloadStatus.FAILED -> {
                    Icon(
                        Icons.Default.Error,
                        contentDescription = "Failed",
                        tint = Color.Red,
                        modifier = Modifier.size(40.dp).align(Alignment.Center)
                    )
                }
                DownloadStatus.COMPLETED -> {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .align(Alignment.Center)
                            .background(Color.Black.copy(alpha = 0.4f), RoundedCornerShape(20.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Default.PlayArrow,
                            contentDescription = "Play",
                            tint = Color.White,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                    if (download.fileSize > 0L) {
                        Box(
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .padding(6.dp)
                                .background(Color.Black.copy(alpha = 0.75f), RoundedCornerShape(4.dp))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = formatBytes(download.fileSize),
                                style = ArflixTypography.caption,
                                color = Color.White,
                                fontSize = 10.sp
                            )
                        }
                    }
                }
            }

            // MoreVert overlay
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(4.dp)
                    .background(Color.Black.copy(alpha = 0.5f), RoundedCornerShape(50))
                    .size(28.dp)
                    .clickable(onClick = onLongPress),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Options",
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )
            }
        }

        Spacer(Modifier.height(6.dp))
        Text(
            text = download.title,
            style = ArflixTypography.body,
            color = Color.White,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@OptIn(ExperimentalTvMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
private fun SeriesPosterCard(
    representative: DownloadEntity,
    episodeCount: Int,
    totalCount: Int,
    usePoster: Boolean,
    onClick: () -> Unit,
    onLongPress: () -> Unit
) {
    val cardWidth = if (usePoster) 140.dp else 220.dp
    val cardHeight = if (usePoster) 210.dp else 124.dp
    val imageUrl = if (usePoster) representative.posterPath
                   else representative.backdropPath ?: representative.posterPath

    Column(modifier = Modifier.width(cardWidth)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(cardHeight)
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFF1A1A1A))
                .combinedClickable(onClick = onClick, onLongClick = onLongPress)
        ) {
            if (!imageUrl.isNullOrBlank()) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = representative.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            // Episode count badge
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(6.dp)
                    .background(Color.Black.copy(alpha = 0.75f), RoundedCornerShape(4.dp))
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
                Text(
                    text = "$episodeCount/$totalCount",
                    style = ArflixTypography.caption,
                    color = Color.White,
                    fontSize = 10.sp
                )
            }

            // MoreVert overlay
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(4.dp)
                    .background(Color.Black.copy(alpha = 0.5f), RoundedCornerShape(50))
                    .size(28.dp)
                    .clickable(onClick = onLongPress),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Options",
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )
            }
        }

        Spacer(Modifier.height(6.dp))
        Text(
            text = representative.title,
            style = ArflixTypography.body,
            color = Color.White,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun SeriesActionsSheet(
    series: Pair<Int, String>?,
    onDismiss: () -> Unit,
    onDeleteAll: (Int) -> Unit
) {
    val isVisible = series != null

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
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 12.dp, bottom = 8.dp)
                        .size(width = 40.dp, height = 4.dp)
                        .background(Color.White.copy(alpha = 0.3f), RoundedCornerShape(2.dp))
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = series?.second.orEmpty(),
                        style = ArflixTypography.sectionTitle,
                        color = Color.White,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close",
                        tint = Color.White,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable(onClick = onDismiss)
                    )
                }
                HorizontalDivider(color = Color.White.copy(alpha = 0.1f))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { series?.first?.let { onDeleteAll(it) } }
                        .padding(horizontal = 16.dp, vertical = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                        tint = Color(0xFFFF5252),
                        modifier = Modifier.size(22.dp)
                    )
                    Text(
                        text = "Delete all downloads",
                        style = ArflixTypography.body,
                        color = Color(0xFFFF5252),
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
                HorizontalDivider(color = Color.White.copy(alpha = 0.07f))
            }
        }
    }
}
