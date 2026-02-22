
@file:Suppress("UnsafeOptInUsageError")

package com.arflix.tv.ui.screens.tv

import android.os.SystemClock
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LiveTv
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.exoplayer.DefaultLoadControl
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import coil.compose.AsyncImage
import com.arflix.tv.data.model.IptvChannel
import com.arflix.tv.data.model.IptvNowNext
import com.arflix.tv.data.model.IptvProgram
import com.arflix.tv.ui.components.Sidebar
import com.arflix.tv.ui.components.SidebarItem
import com.arflix.tv.ui.components.TopBarClock
import com.arflix.tv.ui.theme.ArflixTypography
import com.arflix.tv.ui.theme.BackgroundCard
import com.arflix.tv.ui.theme.BackgroundDark
import com.arflix.tv.ui.theme.Pink
import com.arflix.tv.ui.theme.TextPrimary
import com.arflix.tv.ui.theme.TextSecondary
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import kotlin.math.abs

private enum class TvFocusZone {
    SIDEBAR,
    GROUPS,
    GUIDE
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun TvScreen(
    viewModel: TvViewModel = hiltViewModel(),
    currentProfile: com.arflix.tv.data.model.Profile? = null,
    onNavigateToHome: () -> Unit = {},
    onNavigateToSearch: () -> Unit = {},
    onNavigateToWatchlist: () -> Unit = {},
    onNavigateToSettings: () -> Unit = {},
    onSwitchProfile: () -> Unit = {},
    onBack: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    var focusZone by rememberSaveable { mutableStateOf(if (uiState.isConfigured) TvFocusZone.GROUPS else TvFocusZone.SIDEBAR) }
    val hasProfile = currentProfile != null
    val maxSidebarIndex = if (hasProfile) SidebarItem.entries.size else SidebarItem.entries.size - 1
    var sidebarFocusIndex by rememberSaveable { mutableIntStateOf(if (hasProfile) 4 else 3) }
    var groupIndex by rememberSaveable { mutableIntStateOf(0) }
    var channelIndex by rememberSaveable { mutableIntStateOf(0) }
    var selectedChannelId by rememberSaveable { mutableStateOf<String?>(null) }
    var playingChannelId by rememberSaveable { mutableStateOf<String?>(null) }
    var isFullScreen by rememberSaveable { mutableStateOf(false) }
    var centerDownAtMs by remember { mutableStateOf<Long?>(null) }

    val groupsListState = rememberLazyListState()
    val channelsListState = rememberLazyListState()

    val groups by remember(uiState.snapshot.grouped, uiState.snapshot.favoriteGroups, uiState.snapshot.favoriteChannels) {
        derivedStateOf { uiState.groups() }
    }
    val safeGroupIndex = groupIndex.coerceIn(0, (groups.size - 1).coerceAtLeast(0))
    val selectedGroup = groups.getOrNull(safeGroupIndex).orEmpty()
    val channels = uiState.filteredChannels(selectedGroup)
    val safeChannelIndex = channelIndex.coerceIn(0, (channels.size - 1).coerceAtLeast(0))
    val selectedChannel = selectedChannelId?.let { uiState.channelLookup[it] }
    val playingChannel = selectedChannel ?: playingChannelId?.let { uiState.channelLookup[it] }

    LaunchedEffect(groups.size) {
        if (groupIndex >= groups.size) groupIndex = 0
    }
    LaunchedEffect(uiState.isConfigured) {
        if (uiState.isConfigured && focusZone == TvFocusZone.SIDEBAR && groups.isNotEmpty()) {
            focusZone = TvFocusZone.GROUPS
        }
    }
    LaunchedEffect(channels.size) {
        if (channelIndex >= channels.size) channelIndex = 0
        if (selectedChannelId != null && uiState.snapshot.channels.none { it.id == selectedChannelId }) {
            selectedChannelId = null
        }
    }
    LaunchedEffect(safeGroupIndex, focusZone, groups.size) {
        if (focusZone == TvFocusZone.GROUPS && groups.isNotEmpty()) {
            smoothScrollTo(groupsListState, safeGroupIndex)
        }
    }
    LaunchedEffect(safeChannelIndex, focusZone, channels.size) {
        if (focusZone == TvFocusZone.GUIDE && channels.isNotEmpty()) {
            smoothScrollTo(channelsListState, safeChannelIndex)
        }
    }
    LaunchedEffect(uiState.isConfigured, uiState.isLoading, uiState.snapshot.channels.size, groups.size) {
        if (uiState.isConfigured && !uiState.isLoading && uiState.snapshot.channels.isEmpty()) {
            viewModel.refresh(force = true, showLoading = true)
        }
    }
    LaunchedEffect(groups, selectedGroup, channels.size) {
        if (selectedGroup == "My Favorites" && channels.isEmpty() && groups.size > 1 && groupIndex == 0) {
            groupIndex = 1
        }
    }

    val exoPlayer = remember {
        val httpDataSourceFactory = DefaultHttpDataSource.Factory()
            .setUserAgent("ARVIO/1.2.0 (Android TV)")
            .setAllowCrossProtocolRedirects(true)
            .setConnectTimeoutMs(20_000)
            .setReadTimeoutMs(30_000)

        val mediaSourceFactory = DefaultMediaSourceFactory(context)
            .setDataSourceFactory(httpDataSourceFactory)

        val loadControl = DefaultLoadControl.Builder()
            .setBufferDurationsMs(12_000, 60_000, 2_000, 4_000)
            .setPrioritizeTimeOverSizeThresholds(true)
            .setBackBuffer(8_000, true)
            .build()

        ExoPlayer.Builder(context)
            .setMediaSourceFactory(mediaSourceFactory)
            .setLoadControl(loadControl)
            .build().apply {
                playWhenReady = true
                videoScalingMode = C.VIDEO_SCALING_MODE_SCALE_TO_FIT
            }
    }

    var miniPlayerView by remember { mutableStateOf<PlayerView?>(null) }
    var fullPlayerView by remember { mutableStateOf<PlayerView?>(null) }

    DisposableEffect(Unit) {
        onDispose { exoPlayer.release() }
    }

    LaunchedEffect(playingChannelId, playingChannel?.streamUrl) {
        val stream = playingChannel?.streamUrl ?: return@LaunchedEffect
        exoPlayer.stop()
        exoPlayer.clearMediaItems()
        val mediaItem = MediaItem.Builder()
            .setUri(stream)
            .setLiveConfiguration(
                MediaItem.LiveConfiguration.Builder()
                    .setMinPlaybackSpeed(1.0f)
                    .setMaxPlaybackSpeed(1.0f)
                    .setTargetOffsetMs(4_000)
                    .build()
            )
            .build()
        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.prepare()
        exoPlayer.playWhenReady = true
    }

    LaunchedEffect(isFullScreen, miniPlayerView, fullPlayerView) {
        if (isFullScreen) {
            miniPlayerView?.player = null
            fullPlayerView?.post {
                fullPlayerView?.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
                fullPlayerView?.player = exoPlayer
            }
        } else {
            fullPlayerView?.player = null
            miniPlayerView?.post {
                miniPlayerView?.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
                miniPlayerView?.player = exoPlayer
            }
        }
    }

    DisposableEffect(exoPlayer) {
        val listener = object : Player.Listener {
            override fun onPlayerError(error: PlaybackException) {
                val stream = playingChannel?.streamUrl ?: return
                exoPlayer.clearMediaItems()
                val mediaItem = MediaItem.Builder()
                    .setUri(stream)
                    .setLiveConfiguration(
                        MediaItem.LiveConfiguration.Builder()
                            .setMinPlaybackSpeed(1.0f)
                            .setMaxPlaybackSpeed(1.0f)
                            .setTargetOffsetMs(4_000)
                            .build()
                    )
                    .build()
                exoPlayer.setMediaItem(mediaItem)
                exoPlayer.prepare()
                exoPlayer.playWhenReady = true
            }
        }
        exoPlayer.addListener(listener)
        onDispose { exoPlayer.removeListener(listener) }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundDark)
            .focusable()
            .onPreviewKeyEvent { event ->
                if (isFullScreen) {
                    if (event.type == KeyEventType.KeyDown && (event.key == Key.Back || event.key == Key.Escape)) {
                        isFullScreen = false
                        return@onPreviewKeyEvent true
                    }
                    return@onPreviewKeyEvent false
                }

                val isSelect = event.key == Key.Enter || event.key == Key.DirectionCenter
                if (event.type == KeyEventType.KeyDown && isSelect) {
                    if (centerDownAtMs == null) centerDownAtMs = SystemClock.elapsedRealtime()
                    return@onPreviewKeyEvent true
                }
                if (event.type == KeyEventType.KeyUp && isSelect) {
                    val pressMs = centerDownAtMs?.let { SystemClock.elapsedRealtime() - it } ?: 0L
                    centerDownAtMs = null
                    if (pressMs >= 550L) {
                        when (focusZone) {
                            TvFocusZone.GROUPS -> groups.getOrNull(safeGroupIndex)?.let {
                                viewModel.toggleFavoriteGroup(it)
                                return@onPreviewKeyEvent true
                            }

                            TvFocusZone.GUIDE -> channels.getOrNull(safeChannelIndex)?.let {
                                viewModel.toggleFavoriteChannel(it.id)
                                return@onPreviewKeyEvent true
                            }

                            TvFocusZone.SIDEBAR -> Unit
                        }
                    }

                    when (focusZone) {
                        TvFocusZone.SIDEBAR -> {
                            if (hasProfile && sidebarFocusIndex == 0) {
                                onSwitchProfile()
                            } else {
                                val itemIndex = if (hasProfile) sidebarFocusIndex - 1 else sidebarFocusIndex
                                when (SidebarItem.entries[itemIndex]) {
                                    SidebarItem.SEARCH -> onNavigateToSearch()
                                    SidebarItem.HOME -> onNavigateToHome()
                                    SidebarItem.WATCHLIST -> onNavigateToWatchlist()
                                    SidebarItem.TV -> Unit
                                    SidebarItem.SETTINGS -> onNavigateToSettings()
                                }
                            }
                            true
                        }

                        TvFocusZone.GROUPS -> {
                            channelIndex = 0
                            focusZone = TvFocusZone.GUIDE
                            true
                        }

                        TvFocusZone.GUIDE -> {
                            channels.getOrNull(safeChannelIndex)?.let { channel ->
                                if (playingChannelId == channel.id) {
                                    isFullScreen = true
                                } else {
                                    selectedChannelId = channel.id
                                    playingChannelId = channel.id
                                }
                            }
                            true
                        }
                    }
                } else if (event.type == KeyEventType.KeyDown) {
                    when (event.key) {
                        Key.Back, Key.Escape -> {
                            when (focusZone) {
                                TvFocusZone.SIDEBAR -> onBack()
                                TvFocusZone.GROUPS -> focusZone = TvFocusZone.SIDEBAR
                                TvFocusZone.GUIDE -> focusZone = TvFocusZone.GROUPS
                            }
                            true
                        }

                        Key.DirectionLeft -> {
                            when (focusZone) {
                                TvFocusZone.SIDEBAR -> Unit
                                TvFocusZone.GROUPS -> focusZone = TvFocusZone.SIDEBAR
                                TvFocusZone.GUIDE -> focusZone = TvFocusZone.GROUPS
                            }
                            true
                        }

                        Key.DirectionRight -> {
                            when (focusZone) {
                                TvFocusZone.SIDEBAR -> if (groups.isNotEmpty()) focusZone = TvFocusZone.GROUPS
                                TvFocusZone.GROUPS -> if (channels.isNotEmpty()) focusZone = TvFocusZone.GUIDE
                                TvFocusZone.GUIDE -> Unit
                            }
                            true
                        }

                        Key.DirectionUp -> {
                            when (focusZone) {
                                TvFocusZone.SIDEBAR -> if (sidebarFocusIndex > 0) {
                                    sidebarFocusIndex = (sidebarFocusIndex - 1).coerceIn(0, maxSidebarIndex)
                                }

                                TvFocusZone.GROUPS -> if (groupIndex > 0) groupIndex-- else focusZone = TvFocusZone.SIDEBAR
                                TvFocusZone.GUIDE -> if (channelIndex > 0) channelIndex--
                            }
                            true
                        }

                        Key.DirectionDown -> {
                            when (focusZone) {
                                TvFocusZone.SIDEBAR -> if (sidebarFocusIndex < maxSidebarIndex) {
                                    sidebarFocusIndex = (sidebarFocusIndex + 1).coerceIn(0, maxSidebarIndex)
                                }

                                TvFocusZone.GROUPS -> if (groupIndex < groups.size - 1) groupIndex++
                                TvFocusZone.GUIDE -> if (channelIndex < channels.size - 1) channelIndex++
                            }
                            true
                        }

                        Key.Menu, Key.Bookmark -> {
                            when (focusZone) {
                                TvFocusZone.GROUPS -> groups.getOrNull(safeGroupIndex)?.let {
                                    viewModel.toggleFavoriteGroup(it)
                                    true
                                } ?: false

                                TvFocusZone.GUIDE -> channels.getOrNull(safeChannelIndex)?.let {
                                    viewModel.toggleFavoriteChannel(it.id)
                                    true
                                } ?: false

                                TvFocusZone.SIDEBAR -> false
                            }
                        }

                        Key.Enter, Key.DirectionCenter -> true
                        else -> false
                    }
                } else {
                    false
                }
            }
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            Sidebar(
                selectedItem = SidebarItem.TV,
                isSidebarFocused = focusZone == TvFocusZone.SIDEBAR,
                focusedIndex = sidebarFocusIndex,
                profile = currentProfile,
                onProfileClick = onSwitchProfile
            )

            if (!uiState.isConfigured) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .padding(horizontal = 24.dp, vertical = 24.dp)
                ) {
                    NotConfiguredPanel()
                }
            } else {
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .padding(start = 16.dp, top = 18.dp, end = 20.dp, bottom = 16.dp)
                ) {
                    CategoryRail(
                        groups = groups,
                        favoriteGroups = uiState.snapshot.favoriteGroups.toSet(),
                        focusedGroupIndex = safeGroupIndex,
                        isFocused = focusZone == TvFocusZone.GROUPS,
                        listState = groupsListState,
                        modifier = Modifier
                            .width(220.dp)
                            .fillMaxHeight()
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                    ) {
                        HeroPreviewPanel(
                            channel = playingChannel,
                            nowProgram = playingChannel?.id?.let { uiState.snapshot.nowNext[it]?.now },
                            miniPlayer = {
                                if (playingChannel != null && !isFullScreen) {
                                    AndroidView(
                                        factory = { ctx ->
                                            PlayerView(ctx).apply {
                                                miniPlayerView = this
                                                player = null
                                                useController = false
                                                resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
                                                setKeepContentOnPlayerReset(true)
                                                setShutterBackgroundColor(android.graphics.Color.TRANSPARENT)
                                            }
                                        },
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .clip(RoundedCornerShape(14.dp)),
                                        update = { playerView ->
                                            miniPlayerView = playerView
                                            if (!isFullScreen) {
                                                playerView.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
                                                if (playerView.player !== exoPlayer) playerView.player = exoPlayer
                                            }
                                        }
                                    )
                                }
                            }
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        GuidePanel(
                            channels = channels,
                            nowNext = uiState.snapshot.nowNext,
                            focusedChannelIndex = safeChannelIndex,
                            guideFocused = focusZone == TvFocusZone.GUIDE,
                            playingChannelId = playingChannelId,
                            favoriteChannels = uiState.snapshot.favoriteChannels.toSet(),
                            listState = channelsListState,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }

        TopBarClock(modifier = Modifier.align(Alignment.TopEnd))

        if (isFullScreen && playingChannel != null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
            ) {
                AndroidView(
                    factory = { ctx ->
                        PlayerView(ctx).apply {
                            fullPlayerView = this
                            player = null
                            useController = true
                            controllerAutoShow = true
                            resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
                            setKeepContentOnPlayerReset(true)
                            setShutterBackgroundColor(android.graphics.Color.TRANSPARENT)
                        }
                    },
                    modifier = Modifier.fillMaxSize(),
                    update = { playerView ->
                        fullPlayerView = playerView
                        if (isFullScreen) {
                            playerView.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
                            if (playerView.player !== exoPlayer) playerView.player = exoPlayer
                        }
                    }
                )
                Text(
                    text = "${playingChannel.name} - Back to close",
                    style = ArflixTypography.caption,
                    color = Color.White.copy(alpha = 0.86f),
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(16.dp)
                )
            }
        }

        uiState.error?.let { err ->
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 24.dp)
                    .background(Color(0xFF4A1D1D), RoundedCornerShape(12.dp))
                    .border(1.dp, Color(0xFFB91C1C), RoundedCornerShape(12.dp))
                    .padding(horizontal = 16.dp, vertical = 10.dp)
            ) {
                Text(text = err, style = ArflixTypography.caption, color = Color(0xFFFECACA))
            }
        }
    }
}

private suspend fun smoothScrollTo(state: LazyListState, targetIndex: Int) {
    val safe = targetIndex.coerceAtLeast(0)
    val distance = abs(state.firstVisibleItemIndex - safe)
    if (distance > 12) {
        state.scrollToItem(safe)
    } else {
        state.animateScrollToItem(safe)
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun CategoryRail(
    groups: List<String>,
    favoriteGroups: Set<String>,
    focusedGroupIndex: Int,
    isFocused: Boolean,
    listState: LazyListState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(Color.White.copy(alpha = 0.035f), RoundedCornerShape(14.dp))
            .border(1.dp, Color.White.copy(alpha = 0.08f), RoundedCornerShape(14.dp))
            .padding(10.dp)
    ) {
        Text(
            text = "Categories",
            style = ArflixTypography.caption,
            color = TextSecondary,
            modifier = Modifier.padding(start = 4.dp, bottom = 8.dp)
        )
        LazyColumn(
            state = listState,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            itemsIndexed(groups, key = { _, group -> group }) { index, group ->
                GroupRailItem(
                    name = group,
                    isFocused = isFocused && index == focusedGroupIndex,
                    isFavorite = favoriteGroups.contains(group)
                )
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun GroupRailItem(name: String, isFocused: Boolean, isFavorite: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(
                if (isFocused) Color.White.copy(alpha = 0.14f) else Color.Transparent
            )
            .border(
                width = if (isFocused) 1.dp else 0.dp,
                color = if (isFocused) Pink.copy(alpha = 0.85f) else Color.Transparent,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(horizontal = 10.dp, vertical = 9.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = if (isFavorite) Icons.Default.Star else Icons.Outlined.StarOutline,
            contentDescription = null,
            tint = if (isFavorite) Color(0xFFF5C518) else TextSecondary.copy(alpha = 0.6f),
            modifier = Modifier.size(14.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = name,
            style = ArflixTypography.caption.copy(fontWeight = if (isFocused) FontWeight.SemiBold else FontWeight.Normal),
            color = if (isFocused) TextPrimary else TextSecondary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun HeroPreviewPanel(
    channel: IptvChannel?,
    nowProgram: IptvProgram?,
    miniPlayer: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(286.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(BackgroundCard)
    ) {
        miniPlayer()

        if (channel == null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.35f)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.LiveTv,
                    contentDescription = null,
                    tint = TextSecondary,
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text("Select a channel to start preview", style = ArflixTypography.body, color = TextSecondary)
                Spacer(modifier = Modifier.height(4.dp))
                Text("OK: play  |  OK again: fullscreen", style = ArflixTypography.caption, color = TextSecondary.copy(alpha = 0.8f))
            }
            return
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0.82f),
                            Color.Black.copy(alpha = 0.55f),
                            Color.Transparent
                        )
                    )
                )
        )

        Column(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(horizontal = 22.dp, vertical = 18.dp)
                .width(460.dp)
        ) {
            Text(
                text = channel.name,
                style = ArflixTypography.sectionTitle,
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = nowProgram?.title ?: "Live program",
                style = ArflixTypography.body,
                color = Color.White.copy(alpha = 0.92f),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "${channel.group}  •  Live TV",
                style = ArflixTypography.caption,
                color = Color.White.copy(alpha = 0.72f)
            )
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun GuidePanel(
    channels: List<IptvChannel>,
    nowNext: Map<String, IptvNowNext>,
    focusedChannelIndex: Int,
    guideFocused: Boolean,
    playingChannelId: String?,
    favoriteChannels: Set<String>,
    listState: LazyListState,
    modifier: Modifier = Modifier
) {
    val now = System.currentTimeMillis()
    val windowStart = now - (15 * 60_000L)
    val windowEnd = now + (180 * 60_000L)
    val nowRatio = ((now - windowStart).toFloat() / (windowEnd - windowStart).toFloat()).coerceIn(0f, 1f)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(Color.White.copy(alpha = 0.035f))
            .border(1.dp, Color.White.copy(alpha = 0.08f), RoundedCornerShape(14.dp))
            .padding(10.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            GuideTimeHeader(windowStart = windowStart, now = now, windowEnd = windowEnd)
            Spacer(modifier = Modifier.height(8.dp))

            if (channels.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("No channels in this group", style = ArflixTypography.body, color = TextSecondary)
                }
            } else {
                LazyColumn(
                    state = listState,
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    itemsIndexed(channels, key = { _, ch -> ch.id }) { index, channel ->
                        val focused = guideFocused && index == focusedChannelIndex
                        val slice = nowNext[channel.id]
                        GuideChannelRow(
                            channel = channel,
                            nowProgram = slice?.now,
                            upcomingPrograms = when {
                                !slice?.upcoming.isNullOrEmpty() -> slice?.upcoming.orEmpty()
                                else -> listOfNotNull(slice?.next, slice?.later)
                            },
                            isFocused = focused,
                            isPlaying = channel.id == playingChannelId,
                            isFavoriteChannel = favoriteChannels.contains(channel.id),
                            windowStart = windowStart,
                            windowEnd = windowEnd,
                            nowRatio = nowRatio
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun GuideTimeHeader(windowStart: Long, now: Long, windowEnd: Long) {
    val fmtDate = DateTimeFormatter.ofPattern("dd MMM")
    val dateText = fmtDate.format(Instant.ofEpochMilli(now).atZone(ZoneId.systemDefault()))
    val hour2 = (windowStart + 60 * 60_000L).coerceAtMost(windowEnd)
    val hour3 = (windowStart + 120 * 60_000L).coerceAtMost(windowEnd)

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.width(300.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = dateText,
                style = ArflixTypography.caption,
                color = TextSecondary
            )
        }

        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(formatProgramTime(windowStart), style = ArflixTypography.caption, color = TextSecondary.copy(alpha = 0.9f))
            Text(formatProgramTime(hour2), style = ArflixTypography.caption, color = TextSecondary.copy(alpha = 0.9f))
            Text(formatProgramTime(hour3), style = ArflixTypography.caption, color = TextPrimary.copy(alpha = 0.95f))
            Text(formatProgramTime(windowEnd), style = ArflixTypography.caption, color = TextSecondary.copy(alpha = 0.9f))
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun GuideChannelRow(
    channel: IptvChannel,
    nowProgram: IptvProgram?,
    upcomingPrograms: List<IptvProgram>,
    isFocused: Boolean,
    isPlaying: Boolean,
    isFavoriteChannel: Boolean,
    windowStart: Long,
    windowEnd: Long,
    nowRatio: Float
) {
    val rowBg = if (isFocused) Color(0xFF1E1E1E) else Color(0xFF171717)
    val primaryText = Color.White.copy(alpha = 0.95f)
    val secondaryText = Color(0xFFB5B5B5)
    val liveText = Color.White
    val liveBg = Color.White.copy(alpha = if (isFocused) 0.26f else 0.18f)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(74.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(rowBg)
            .border(
                width = if (isFocused) 2.dp else 0.dp,
                color = if (isFocused) Color.White.copy(alpha = 0.95f) else Color.Transparent,
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        Row(
            modifier = Modifier
                .width(300.dp)
                .fillMaxHeight()
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (!channel.logo.isNullOrBlank()) {
                AsyncImage(
                    model = channel.logo,
                    contentDescription = channel.name,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(46.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.Black.copy(alpha = 0.25f))
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(46.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.Black.copy(alpha = 0.25f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.LiveTv, contentDescription = null, tint = secondaryText)
                }
            }

            Spacer(modifier = Modifier.width(10.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = channel.name,
                        style = ArflixTypography.cardTitle,
                        color = primaryText,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )
                    if (isPlaying) {
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "LIVE",
                            style = ArflixTypography.caption,
                            color = liveText,
                            modifier = Modifier
                                .background(liveBg, RoundedCornerShape(6.dp))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(6.dp))
                    Icon(
                        imageVector = if (isFavoriteChannel) Icons.Default.Star else Icons.Outlined.StarOutline,
                        contentDescription = null,
                        tint = if (isFavoriteChannel) Color(0xFFF5C518) else secondaryText,
                        modifier = Modifier.size(15.dp)
                    )
                }
                Text(
                    text = channel.group,
                    style = ArflixTypography.caption,
                    color = secondaryText,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

        TimelineProgramLane(
            nowProgram = nowProgram,
            upcomingPrograms = upcomingPrograms,
            windowStart = windowStart,
            windowEnd = windowEnd,
            nowRatio = nowRatio,
            isRowFocused = isFocused,
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(horizontal = 8.dp, vertical = 10.dp)
        )
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun TimelineProgramLane(
    nowProgram: IptvProgram?,
    upcomingPrograms: List<IptvProgram>,
    windowStart: Long,
    windowEnd: Long,
    nowRatio: Float,
    isRowFocused: Boolean,
    modifier: Modifier = Modifier
) {
    val laneBackground = Color.White.copy(alpha = if (isRowFocused) 0.08f else 0.04f)
    val laneTextColor = Color(0xFFF1F1F1)
    Box(modifier = modifier.clip(RoundedCornerShape(8.dp)).background(laneBackground)) {
        Row(modifier = Modifier.fillMaxSize()) {
            val segments = buildProgramSegments(nowProgram, upcomingPrograms, windowStart, windowEnd)
            if (segments.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 10.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text("EPG unavailable", style = ArflixTypography.caption, color = laneTextColor.copy(alpha = 0.75f))
                }
            } else {
                segments.forEach { seg ->
                    val fillColor = if (seg.isFiller) {
                        if (isRowFocused) Color(0xFF171717) else Color(0xFF141414)
                    } else if (seg.isNow) {
                        if (isRowFocused) Color(0xFF323232) else Color(0xFF2A2A2A)
                    } else {
                        if (isRowFocused) Color(0xFF232323) else Color(0xFF1D1D1D)
                    }
                    Box(
                        modifier = Modifier
                            .weight(seg.weight)
                            .fillMaxHeight()
                            .padding(horizontal = 2.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(fillColor)
                            .border(
                                width = if (seg.isNow || seg.isFiller) 1.dp else 0.dp,
                                color = when {
                                    seg.isNow && isRowFocused -> Color.White.copy(alpha = 0.9f)
                                    seg.isFiller -> Color.White.copy(alpha = if (isRowFocused) 0.20f else 0.12f)
                                    else -> Color.Transparent
                                },
                                shape = RoundedCornerShape(6.dp)
                            ),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (seg.label.isNotBlank()) {
                            Text(
                                text = seg.label,
                                style = ArflixTypography.caption,
                                color = laneTextColor.copy(alpha = if (seg.isFiller) 0.78f else 1f),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier
                                    .padding(horizontal = 8.dp)
                                    .wrapContentWidth(Alignment.Start)
                            )
                        }
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(nowRatio)
                .width(2.dp)
                .background(if (isRowFocused) Color.White.copy(alpha = 0.85f) else Color.White.copy(alpha = 0.55f))
                .align(Alignment.CenterStart)
        )
    }
}

private data class ProgramSegment(
    val label: String,
    val weight: Float,
    val isNow: Boolean,
    val isFiller: Boolean = false
)

private fun buildProgramSegments(
    nowProgram: IptvProgram?,
    upcomingPrograms: List<IptvProgram>,
    windowStart: Long,
    windowEnd: Long
): List<ProgramSegment> {
    val totalWindow = (windowEnd - windowStart).coerceAtLeast(1L).toFloat()
    fun weight(start: Long, end: Long): Float {
        val s = start.coerceIn(windowStart, windowEnd)
        val e = end.coerceIn(windowStart, windowEnd)
        val clamped = (e - s).coerceAtLeast(0L)
        return (clamped / totalWindow).coerceIn(0f, 1f)
    }

    val items = mutableListOf<ProgramSegment>()
    nowProgram?.let {
        val w = weight(it.startUtcMillis, it.endUtcMillis)
        if (w > 0.02f) items += ProgramSegment(it.title, w, true)
    }
    upcomingPrograms.forEach { program ->
        val w = weight(program.startUtcMillis, program.endUtcMillis)
        if (w > 0.02f) items += ProgramSegment(program.title, w, false)
    }
    val mergedItems = mergeDuplicateSegments(items)
    val adjustedItems = ensureReadableProgramWidths(mergedItems)
    val used = adjustedItems.sumOf { it.weight.toDouble() }.toFloat()
    if (used < 1f) {
        val remaining = (1f - used).coerceAtLeast(0f)
        if (remaining < 0.08f) return adjustedItems
        val fillerLabel = if (remaining >= 0.22f) "No EPG data" else ""
        return adjustedItems + ProgramSegment(
            label = fillerLabel,
            weight = remaining,
            isNow = false,
            isFiller = true
        )
    }
    return adjustedItems
}

private fun mergeDuplicateSegments(items: List<ProgramSegment>): List<ProgramSegment> {
    if (items.isEmpty()) return items
    val merged = mutableListOf<ProgramSegment>()
    items.forEach { seg ->
        val last = merged.lastOrNull()
        if (
            last != null &&
            last.label.equals(seg.label, ignoreCase = true) &&
            last.isNow == seg.isNow &&
            last.isFiller == seg.isFiller
        ) {
            merged[merged.lastIndex] = last.copy(weight = last.weight + seg.weight)
        } else {
            merged += seg
        }
    }
    return merged
}

private fun ensureReadableProgramWidths(items: List<ProgramSegment>): List<ProgramSegment> {
    if (items.isEmpty()) return items
    val labeled = items.filter { it.label.isNotBlank() }
    if (labeled.isEmpty()) return items

    val minReadable = 0.16f
    val maxTotalForLabeled = 0.94f

    val boosted = items.map { seg ->
        if (seg.label.isNotBlank()) seg.copy(weight = maxOf(seg.weight, minReadable)) else seg
    }.toMutableList()

    val labeledTotal = boosted.filter { it.label.isNotBlank() }.sumOf { it.weight.toDouble() }.toFloat()
    if (labeledTotal > maxTotalForLabeled) {
        val factor = maxTotalForLabeled / labeledTotal
        for (i in boosted.indices) {
            if (boosted[i].label.isNotBlank()) {
                boosted[i] = boosted[i].copy(weight = boosted[i].weight * factor)
            }
        }
    }

    return boosted
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun NotConfiguredPanel() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundCard, RoundedCornerShape(14.dp))
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = Icons.Default.LiveTv,
                contentDescription = null,
                tint = TextSecondary,
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text("IPTV is not configured", style = ArflixTypography.sectionTitle, color = TextPrimary)
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                "Open Settings and add your M3U URL.",
                style = ArflixTypography.body,
                color = TextSecondary
            )
        }
    }
}

private fun formatProgramTime(utcMillis: Long): String {
    val formatter = DateTimeFormatter.ofPattern("h:mm a")
    return formatter.format(Instant.ofEpochMilli(utcMillis).atZone(ZoneId.systemDefault()))
}

