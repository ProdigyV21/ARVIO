@file:Suppress("UnsafeOptInUsageError")

package com.arflix.tv.ui.screens.tv

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.derivedStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.MediaItem
import androidx.media3.common.C
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.DefaultLoadControl
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.common.Player
import androidx.media3.common.PlaybackException
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import androidx.compose.ui.viewinterop.AndroidView
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import coil.compose.AsyncImage
import com.arflix.tv.data.model.IptvChannel
import com.arflix.tv.data.model.IptvProgram
import com.arflix.tv.ui.components.LoadingIndicator
import com.arflix.tv.ui.components.Sidebar
import com.arflix.tv.ui.components.SidebarItem
import com.arflix.tv.ui.components.TopBarClock
import com.arflix.tv.ui.theme.ArflixTypography
import com.arflix.tv.ui.theme.BackgroundCard
import com.arflix.tv.ui.theme.BackgroundDark
import com.arflix.tv.ui.theme.Pink
import com.arflix.tv.ui.theme.TextPrimary
import com.arflix.tv.ui.theme.TextSecondary
import android.os.SystemClock
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

private enum class TvFocusZone {
    SIDEBAR,
    GROUPS,
    CHANNELS
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
    var sidebarFocusIndex by rememberSaveable { mutableIntStateOf(if (hasProfile) 4 else 3) } // TV
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
        if (focusZone == TvFocusZone.CHANNELS && channels.isNotEmpty()) {
            smoothScrollTo(channelsListState, safeChannelIndex)
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
            .setBufferDurationsMs(
                12_000,  // min buffer
                60_000,  // max buffer
                2_000,   // start playback buffer
                4_000    // rebuffer playback buffer
            )
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
    val latestPlayingChannel by rememberUpdatedState(playingChannel)

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
                    // Keep live playback stable and avoid aggressive catch-up drift
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
    LaunchedEffect(isFullScreen, playingChannelId) {
        val from = if (isFullScreen) miniPlayerView else fullPlayerView
        val to = if (isFullScreen) fullPlayerView else miniPlayerView
        PlayerView.switchTargetView(exoPlayer, from, to)
    }
    DisposableEffect(exoPlayer) {
        val listener = object : Player.Listener {
            override fun onPlayerError(error: PlaybackException) {
                // Recover quickly from transient surface/decoder issues that can
                // result in audio-only playback on some streams.
                val stream = latestPlayingChannel?.streamUrl ?: return
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
                    if (centerDownAtMs == null) {
                        centerDownAtMs = SystemClock.elapsedRealtime()
                    }
                    return@onPreviewKeyEvent true
                }

                if (event.type == KeyEventType.KeyUp && isSelect) {
                    val pressMs = centerDownAtMs?.let { SystemClock.elapsedRealtime() - it } ?: 0L
                    centerDownAtMs = null
                    if (pressMs >= 550L) {
                        when (focusZone) {
                            TvFocusZone.GROUPS -> {
                                val group = groups.getOrNull(safeGroupIndex)
                                if (group != null) {
                                    viewModel.toggleFavoriteGroup(group)
                                    return@onPreviewKeyEvent true
                                }
                            }
                            TvFocusZone.CHANNELS -> {
                                val channel = channels.getOrNull(safeChannelIndex)
                                if (channel != null) {
                                    viewModel.toggleFavoriteChannel(channel.id)
                                    return@onPreviewKeyEvent true
                                }
                            }
                            TvFocusZone.SIDEBAR -> {}
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
                                    SidebarItem.TV -> {}
                                    SidebarItem.SETTINGS -> onNavigateToSettings()
                                }
                            }
                            return@onPreviewKeyEvent true
                        }
                        TvFocusZone.GROUPS -> {
                            channelIndex = 0
                            focusZone = TvFocusZone.CHANNELS
                            return@onPreviewKeyEvent true
                        }
                        TvFocusZone.CHANNELS -> {
                            val channel = channels.getOrNull(safeChannelIndex)
                            if (channel != null) {
                                if (playingChannelId == channel.id) {
                                    isFullScreen = true
                                } else {
                                    selectedChannelId = channel.id
                                    playingChannelId = channel.id
                                }
                            }
                            return@onPreviewKeyEvent true
                        }
                    }
                }

                if (event.type != KeyEventType.KeyDown) return@onPreviewKeyEvent false
                when (event.key) {
                    Key.Back, Key.Escape -> {
                        when (focusZone) {
                            TvFocusZone.SIDEBAR -> onBack()
                            TvFocusZone.GROUPS -> focusZone = TvFocusZone.SIDEBAR
                            TvFocusZone.CHANNELS -> focusZone = TvFocusZone.GROUPS
                        }
                        true
                    }
                    Key.DirectionLeft -> {
                        when (focusZone) {
                            TvFocusZone.SIDEBAR -> {}
                            TvFocusZone.GROUPS -> focusZone = TvFocusZone.SIDEBAR
                            TvFocusZone.CHANNELS -> focusZone = TvFocusZone.GROUPS
                        }
                        true
                    }
                    Key.DirectionRight -> {
                        when (focusZone) {
                            TvFocusZone.SIDEBAR -> if (groups.isNotEmpty()) focusZone = TvFocusZone.GROUPS
                            TvFocusZone.GROUPS -> if (channels.isNotEmpty()) focusZone = TvFocusZone.CHANNELS
                            TvFocusZone.CHANNELS -> {}
                        }
                        true
                    }
                    Key.DirectionUp -> {
                        when (focusZone) {
                            TvFocusZone.SIDEBAR -> if (sidebarFocusIndex > 0) {
                                sidebarFocusIndex = (sidebarFocusIndex - 1).coerceIn(0, maxSidebarIndex)
                            }
                            TvFocusZone.GROUPS -> {
                                if (groupIndex > 0) groupIndex-- else focusZone = TvFocusZone.SIDEBAR
                            }
                            TvFocusZone.CHANNELS -> if (channelIndex > 0) channelIndex--
                        }
                        true
                    }
                    Key.DirectionDown -> {
                        when (focusZone) {
                            TvFocusZone.SIDEBAR -> if (sidebarFocusIndex < maxSidebarIndex) {
                                sidebarFocusIndex = (sidebarFocusIndex + 1).coerceIn(0, maxSidebarIndex)
                            }
                            TvFocusZone.GROUPS -> if (groupIndex < groups.size - 1) groupIndex++
                            TvFocusZone.CHANNELS -> if (channelIndex < channels.size - 1) channelIndex++
                        }
                        true
                    }
                    Key.Menu, Key.Bookmark -> {
                        val group = groups.getOrNull(safeGroupIndex)
                        if (focusZone == TvFocusZone.GROUPS && group != null) {
                            viewModel.toggleFavoriteGroup(group)
                            true
                        } else {
                            false
                        }
                    }
                    Key.Enter, Key.DirectionCenter -> {
                        true
                    }
                    else -> false
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

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(horizontal = 24.dp, vertical = 24.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "hold select to favorite",
                        style = ArflixTypography.caption,
                        color = TextSecondary.copy(alpha = 0.8f),
                        modifier = Modifier.padding(start = 2.dp)
                    )
                }

                if (uiState.isLoading && !uiState.loadingMessage.isNullOrBlank()) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "${uiState.loadingMessage} (${uiState.loadingPercent.coerceIn(0, 100)}%)",
                        style = ArflixTypography.caption,
                        color = TextSecondary
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                            .background(Color.White.copy(alpha = 0.12f), RoundedCornerShape(999.dp))
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxWidth(uiState.loadingPercent.coerceIn(0, 100) / 100f)
                                .background(Pink, RoundedCornerShape(999.dp))
                        )
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                if (!uiState.isConfigured) {
                    NotConfiguredPanel()
                } else {
                    Row(modifier = Modifier.weight(1f)) {
                        Column(
                            modifier = Modifier
                                .width(230.dp)
                                .fillMaxHeight()
                                .background(BackgroundCard, RoundedCornerShape(14.dp))
                                .padding(12.dp)
                        ) {
                            Text("Groups", style = ArflixTypography.cardTitle, color = TextPrimary)
                            Spacer(modifier = Modifier.height(10.dp))
                            LazyColumn(
                                state = groupsListState,
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                itemsIndexed(groups, key = { _, group -> group }) { index, group ->
                                    val focused = focusZone == TvFocusZone.GROUPS && index == safeGroupIndex
                                    GroupRow(
                                        group = group,
                                        isFocused = focused,
                                        isFavorite = uiState.snapshot.favoriteGroups.contains(group)
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                                    .background(BackgroundCard, RoundedCornerShape(14.dp))
                                    .padding(10.dp)
                            ) {
                                if (playingChannel == null) {
                                    Column(
                                        modifier = Modifier.fillMaxSize(),
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.LiveTv,
                                            contentDescription = null,
                                            tint = TextSecondary,
                                            modifier = Modifier.size(34.dp)
                                        )
                                        Spacer(modifier = Modifier.height(8.dp))
                                        Text("Press OK to play in mini player", style = ArflixTypography.body, color = TextSecondary)
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text("Press OK again for full player", style = ArflixTypography.caption, color = TextSecondary)
                                    }
                                } else if (!isFullScreen) {
                                    AndroidView(
                                        factory = { context ->
                                            PlayerView(context).apply {
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
                                            .clip(RoundedCornerShape(12.dp)),
                                        update = { playerView ->
                                            if (!isFullScreen) {
                                                PlayerView.switchTargetView(exoPlayer, fullPlayerView, playerView)
                                            }
                                        }
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(BackgroundCard, RoundedCornerShape(14.dp))
                                    .padding(12.dp)
                            ) {
                                if (uiState.isLoading) {
                                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                        LoadingIndicator(color = Pink, size = 42.dp)
                                    }
                                } else if (channels.isEmpty()) {
                                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                        Text(
                                            text = "No channels in this group",
                                            style = ArflixTypography.body,
                                            color = TextSecondary
                                        )
                                    }
                                } else {
                                    LazyColumn(
                                        state = channelsListState,
                                        verticalArrangement = Arrangement.spacedBy(6.dp)
                                    ) {
                                        itemsIndexed(channels, key = { _, ch -> ch.id }) { index, channel ->
                                            val focused = focusZone == TvFocusZone.CHANNELS && index == safeChannelIndex
                                            val nowNext = uiState.snapshot.nowNext[channel.id]
                                            ChannelRow(
                                                channel = channel,
                                                nowProgram = nowNext?.now,
                                                nextProgram = nowNext?.next,
                                                isFocused = focused,
                                                isFavoriteGroup = uiState.snapshot.favoriteGroups.contains(channel.group),
                                                isPlaying = channel.id == playingChannelId
                                            )
                                        }
                                    }
                                }
                            }
                        }
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
                        if (isFullScreen) {
                            PlayerView.switchTargetView(exoPlayer, miniPlayerView, playerView)
                        }
                    }
                )
                Text(
                    text = "${playingChannel.name} - Back to close",
                    style = ArflixTypography.caption,
                    color = Color.White.copy(alpha = 0.82f),
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
    val distance = kotlin.math.abs(state.firstVisibleItemIndex - safe)
    if (distance > 12) {
        state.scrollToItem(safe)
    } else {
        state.animateScrollToItem(safe)
    }
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

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun GroupRow(group: String, isFocused: Boolean) {
    GroupRow(group = group, isFocused = isFocused, isFavorite = false)
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun GroupRow(group: String, isFocused: Boolean, isFavorite: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                if (isFocused) Color.White.copy(alpha = 0.1f) else Color.Transparent,
                RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 10.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isFavorite) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = Color(0xFFF5C518),
                modifier = Modifier.size(14.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
        }
        Text(
            text = group,
            style = ArflixTypography.caption.copy(fontWeight = if (isFocused) FontWeight.SemiBold else FontWeight.Normal),
            color = if (isFocused) TextPrimary else TextSecondary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun ChannelRow(
    channel: IptvChannel,
    nowProgram: IptvProgram?,
    nextProgram: IptvProgram?,
    isFocused: Boolean,
    isFavoriteGroup: Boolean,
    isPlaying: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                if (isFocused) Color.White.copy(alpha = 0.1f) else Color.Transparent,
                RoundedCornerShape(10.dp)
            )
            .border(
                width = if (isFocused) 1.dp else 0.dp,
                color = if (isFocused) Pink else Color.Transparent,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(horizontal = 10.dp, vertical = 8.dp),
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
                    .background(Color.Black.copy(alpha = 0.2f))
            )
        } else {
            Box(
                modifier = Modifier
                    .size(46.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.Black.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.LiveTv, contentDescription = null, tint = TextSecondary)
            }
        }

        Spacer(modifier = Modifier.width(8.dp))

        Column(modifier = Modifier.weight(1f)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = channel.name,
                    style = ArflixTypography.cardTitle,
                    color = TextPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                if (isPlaying) {
                    Text(
                        text = "LIVE",
                        style = ArflixTypography.caption,
                        color = Color(0xFF22C55E),
                        modifier = Modifier
                            .background(Color(0xFF22C55E).copy(alpha = 0.18f), RoundedCornerShape(6.dp))
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                }
                Icon(
                    imageVector = if (isFavoriteGroup) Icons.Default.Star else Icons.Outlined.StarOutline,
                    contentDescription = null,
                    tint = if (isFavoriteGroup) Color(0xFFF5C518) else TextSecondary,
                    modifier = Modifier.size(16.dp)
                )
            }
            Text(
                text = channel.group,
                style = ArflixTypography.caption,
                color = TextSecondary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            if (nowProgram != null) {
                Text(
                    text = "Now ${formatProgramTime(nowProgram.startUtcMillis)}-${formatProgramTime(nowProgram.endUtcMillis)}  ${nowProgram.title}",
                    style = ArflixTypography.caption,
                    color = Color(0xFFA7F3D0),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            } else {
                Text(
                    text = "Now: EPG unavailable",
                    style = ArflixTypography.caption,
                    color = TextSecondary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            if (nextProgram != null) {
                Text(
                    text = "Next ${formatProgramTime(nextProgram.startUtcMillis)}-${formatProgramTime(nextProgram.endUtcMillis)}  ${nextProgram.title}",
                    style = ArflixTypography.caption,
                    color = TextSecondary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

private fun formatProgramTime(utcMillis: Long): String {
    val formatter = DateTimeFormatter.ofPattern("HH:mm")
    return formatter.format(Instant.ofEpochMilli(utcMillis).atZone(ZoneId.systemDefault()))
}
