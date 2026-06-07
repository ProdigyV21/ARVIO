
@file:Suppress("UnsafeOptInUsageError")

package com.arflix.tv.ui.screens.tv

import android.content.Context
import android.os.SystemClock
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.foundation.focusable
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.IconButton
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.ui.res.stringResource
import com.arflix.tv.R
import com.arflix.tv.ui.skin.resolveAccentColor

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LiveTv
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.datasource.okhttp.OkHttpDataSource
import androidx.media3.exoplayer.DefaultLoadControl
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.hls.HlsMediaSource
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Precision
import com.arflix.tv.data.model.IptvChannel
import com.arflix.tv.data.model.IptvNowNext
import com.arflix.tv.data.model.IptvProgram
import com.arflix.tv.network.OkHttpProvider
import com.arflix.tv.ui.components.AppTopBar
import com.arflix.tv.ui.components.KeepScreenOn
import com.arflix.tv.ui.components.AppTopBarContentTopInset
import com.arflix.tv.util.LocalDeviceType
import com.arflix.tv.ui.components.SidebarItem
import com.arflix.tv.ui.components.topBarFocusedItem
import com.arflix.tv.ui.components.topBarMaxIndex
import com.arflix.tv.ui.focus.arvioDpadFocusGroup
import com.arflix.tv.ui.theme.AccentGreen
import com.arflix.tv.ui.theme.ArflixTypography
import com.arflix.tv.ui.theme.BackgroundCard
import com.arflix.tv.ui.theme.appBackgroundDark

import com.arflix.tv.ui.theme.TextPrimary
import com.arflix.tv.ui.theme.TextSecondary
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import kotlinx.coroutines.delay
import kotlin.math.abs


private object TvScreenRegexes {
    val NON_ALPHANUMERIC_REGEX = Regex("""[^a-z0-9]+""")
}

private enum class TvFocusZone {
    SIDEBAR,
    GROUPS,
    GUIDE
}

private fun List<IptvChannel>.preferredIndexFor(
    selectedChannelId: String?,
    playingChannelId: String?
): Int {
    if (isEmpty()) return 0
    val selectedIndex = selectedChannelId?.let { id -> indexOfFirst { it.id == id } } ?: -1
    if (selectedIndex >= 0) return selectedIndex
    val playingIndex = playingChannelId?.let { id -> indexOfFirst { it.id == id } } ?: -1
    if (playingIndex >= 0) return playingIndex
    return 0
}

private fun preferredStartupGroup(
    groups: List<String>,
    channelsByGroup: Map<String, List<IptvChannel>>
): String? {
    return when {
        channelsByGroup[FAVORITES_GROUP_NAME].orEmpty().isNotEmpty() -> FAVORITES_GROUP_NAME
        else -> groups.firstOrNull { channelsByGroup[it].orEmpty().isNotEmpty() }
    }
}

private fun String.isPriorityGuideGroup(): Boolean {
    if (this == FAVORITES_GROUP_NAME) return true
    val tokens = lowercase()
        .split(TvScreenRegexes.NON_ALPHANUMERIC_REGEX)
        .filter { it.isNotBlank() }
        .toSet()
    return "netherlands" in tokens || "nederland" in tokens || "nl" in tokens
}

private fun String.toTvFocusZone(
    hasGroups: Boolean,
    hasChannels: Boolean
): TvFocusZone = when (uppercase()) {
    TvFocusZone.SIDEBAR.name -> if (hasGroups) TvFocusZone.GROUPS else TvFocusZone.SIDEBAR
    TvFocusZone.GROUPS.name -> if (hasGroups) TvFocusZone.GROUPS else TvFocusZone.SIDEBAR
    else -> when {
        hasChannels -> TvFocusZone.GUIDE
        hasGroups -> TvFocusZone.GROUPS
        else -> TvFocusZone.SIDEBAR
    }
}

private fun createTvExoPlayer(
    context: Context,
    mediaSourceFactory: DefaultMediaSourceFactory
): ExoPlayer {
    val loadControl = DefaultLoadControl.Builder()
        .setBufferDurationsMs(
            20_000,
            120_000,
            1_000,
            3_000
        )
        .setTargetBufferBytes(80 * 1024 * 1024)
        .setPrioritizeTimeOverSizeThresholds(true)
        .setBackBuffer(10_000, true)
        .build()

    return ExoPlayer.Builder(context)
        .setMediaSourceFactory(mediaSourceFactory)
        .setLoadControl(loadControl)
        .build()
        .apply {
            playWhenReady = true
            videoScalingMode = C.VIDEO_SCALING_MODE_SCALE_TO_FIT
        }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun TvScreen(
    viewModel: TvViewModel = hiltViewModel(),
    currentProfile: com.arflix.tv.data.model.Profile? = null,
    initialChannelId: String? = null,
    initialStreamUrl: String? = null,
    onNavigateToHome: () -> Unit = {},
    onNavigateToSearch: () -> Unit = {},
    onNavigateToWatchlist: () -> Unit = {},
    onNavigateToSettings: () -> Unit = {},
    onSwitchProfile: () -> Unit = {},
    onBack: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val isMobile = LocalDeviceType.current.isTouchDevice()

    var focusZone by rememberSaveable { mutableStateOf(if (uiState.isConfigured) TvFocusZone.GROUPS else TvFocusZone.SIDEBAR) }
    val hasProfile = currentProfile != null
    val maxSidebarIndex = topBarMaxIndex(hasProfile)
    var sidebarFocusIndex by rememberSaveable { mutableIntStateOf(if (hasProfile) 4 else 3) }
    var groupIndex by rememberSaveable { mutableIntStateOf(0) }
    var channelIndex by rememberSaveable { mutableIntStateOf(0) }
    var selectedChannelId by rememberSaveable { mutableStateOf<String?>(null) }
    var playingChannelId by rememberSaveable { mutableStateOf<String?>(null) }
    KeepScreenOn(active = playingChannelId != null)
    var showGroupContextMenu by remember { mutableStateOf(false) }
    // When launched from Home with a stream URL, start in fullscreen immediately
    // to avoid a flash of the TV page channel list.
    var isFullScreen by rememberSaveable { mutableStateOf(initialStreamUrl != null) }
    var showFullscreenOverlay by remember { mutableStateOf(false) }
    var fullscreenOverlayTrigger by remember { mutableStateOf(0L) } // timestamp to reset auto-hide timer
    var centerDownAtMs by remember { mutableStateOf<Long?>(null) }
    var lastNavigationAt by remember { mutableLongStateOf(0L) }
    var restoredSessionAt by rememberSaveable { mutableLongStateOf(0L) }
    var startupDefaultApplied by remember { mutableStateOf(false) }
    var isFastNavigating by remember { mutableStateOf(false) }
    val rootFocusRequester = remember { FocusRequester() }
    var rootHasFocus by remember { mutableStateOf(false) }
    val focusRecoveryDelayMs = 180L

    LaunchedEffect(Unit) {
        runCatching { rootFocusRequester.requestFocus() }
    }
    LaunchedEffect(focusZone) {
        // Re-anchor Compose focus to the root Box on every zone change so the system focus
        // indicator doesn't linger on the previous zone's items (e.g. category row → channel).
        runCatching { rootFocusRequester.requestFocus() }
    }
    LaunchedEffect(rootHasFocus, showGroupContextMenu) {
        if (rootHasFocus || showGroupContextMenu) return@LaunchedEffect
        delay(focusRecoveryDelayMs)
        if (!rootHasFocus && !showGroupContextMenu) {
            runCatching { rootFocusRequester.requestFocus() }
        }
    }

    LaunchedEffect(lastNavigationAt) {
        val anchor = lastNavigationAt
        if (anchor <= 0L) {
            isFastNavigating = false
            return@LaunchedEffect
        }
        isFastNavigating = true
        delay(180L)
        if (lastNavigationAt == anchor) {
            isFastNavigating = false
        }
    }

    BackHandler(enabled = isMobile && isFullScreen) {
        if (showFullscreenOverlay) {
            showFullscreenOverlay = false
        } else {
            // Always return to EPG guide first, regardless of how we got here
            isFullScreen = false
            showFullscreenOverlay = false
        }
    }

    val groupsListState = rememberLazyListState()
    val channelsListState = rememberLazyListState()
    val contentTopPadding = (AppTopBarContentTopInset - 14.dp).coerceAtLeast(52.dp)
    val markNavigation = remember {
        { lastNavigationAt = SystemClock.elapsedRealtime() }
    }

    val groups = uiState.groups
    val favoriteGroups by remember(uiState.snapshot.favoriteGroups) {
        derivedStateOf { uiState.snapshot.favoriteGroups.toSet() }
    }
    val favoriteChannels by remember(uiState.snapshot.favoriteChannels) {
        derivedStateOf { uiState.snapshot.favoriteChannels.toSet() }
    }
    val safeGroupIndex = groupIndex.coerceIn(0, (groups.size - 1).coerceAtLeast(0))
    val selectedGroup = groups.getOrNull(safeGroupIndex).orEmpty()
    val channels = uiState.channelsByGroup[selectedGroup].orEmpty()
    val selectedGroupChannelIds = remember(selectedGroup, channels) {
        channels.map { it.id }
    }
    val channelIndexById = remember(channels) {
        channels.mapIndexed { index, channel -> channel.id to index }.toMap()
    }
    val safeChannelIndex = channelIndex.coerceIn(0, (channels.size - 1).coerceAtLeast(0))
    val startupGroupName = remember(groups, uiState.channelsByGroup) {
        preferredStartupGroup(groups, uiState.channelsByGroup).orEmpty()
    }
    val selectedChannel = selectedChannelId?.let { uiState.channelLookup[it] }
    // Actual playback should be driven by the currently playing channel first.
    // A stale selectedChannelId (e.g. after group changes) can otherwise override
    // the playing channel and make the UI look like clicking one channel opens another.
    val playingChannel = playingChannelId?.let { uiState.channelLookup[it] } ?: selectedChannel
    val latestEpgAnchorChannelId by rememberUpdatedState(playingChannelId ?: selectedChannelId)

    // Auto-select channel when navigated from Home "Favorite TV" row.
    // If initialStreamUrl was provided, playback already started instantly —
    // this just updates selectedChannelId once the lookup is ready.
    LaunchedEffect(initialChannelId, uiState.snapshot.channels.size) {
        if (initialChannelId != null && uiState.snapshot.channels.isNotEmpty()) {
            val channel = uiState.channelLookup[initialChannelId]
            if (channel != null) {
                val restoredGroupIndex = groups.indexOfFirst { groupName ->
                    uiState.channelsByGroup[groupName].orEmpty().any { it.id == channel.id }
                }
                if (restoredGroupIndex >= 0) {
                    groupIndex = restoredGroupIndex
                    val restoredChannels = uiState.channelsByGroup[groups[restoredGroupIndex]].orEmpty()
                    channelIndex = restoredChannels.indexOfFirst { it.id == channel.id }.coerceAtLeast(0)
                }
                selectedChannelId = channel.id
                // Only set playingChannelId if not already playing (instant start already did it)
                if (playingChannelId != channel.id) {
                    playingChannelId = channel.id
                }
                isFullScreen = true
            }
        }
    }

    LaunchedEffect(uiState.tvSession.lastOpenedAt, uiState.snapshot.channels.size, initialChannelId, initialStreamUrl, startupGroupName, startupDefaultApplied) {
        if (initialChannelId != null || initialStreamUrl != null) return@LaunchedEffect
        if (uiState.snapshot.channels.isEmpty()) {
            return@LaunchedEffect
        }
        if (startupDefaultApplied && restoredSessionAt == uiState.tvSession.lastOpenedAt && restoredSessionAt > 0L) return@LaunchedEffect

        val session = uiState.tvSession
        val startupGroupIndex = startupGroupName
            .takeIf { it.isNotBlank() }
            ?.let(groups::indexOf)
            ?.takeIf { it >= 0 }
        val shouldPreferFavoritesStartup = startupGroupIndex != null &&
            startupGroupName == FAVORITES_GROUP_NAME

        if (!startupDefaultApplied && shouldPreferFavoritesStartup) {
            val favoritesChannels = uiState.channelsByGroup[startupGroupName].orEmpty()
            val startupIndex = 0
            val startupChannel = favoritesChannels.getOrNull(startupIndex)
            if (startupChannel != null) {
                groupIndex = startupGroupIndex
                channelIndex = startupIndex
                selectedChannelId = startupChannel.id
                playingChannelId = startupChannel.id
                focusZone = TvFocusZone.GUIDE
                restoredSessionAt = session.lastOpenedAt.coerceAtLeast(1L)
                startupDefaultApplied = true
                return@LaunchedEffect
            }
        }

        if (!startupDefaultApplied && session.lastOpenedAt <= 0L) {
            val fallbackGroup = startupGroupIndex ?: 0
            val fallbackChannels = groups.getOrNull(fallbackGroup)
                ?.let { uiState.channelsByGroup[it].orEmpty() }
                .orEmpty()
            val fallbackIndex = fallbackChannels.preferredIndexFor(selectedChannelId, playingChannelId)
            fallbackChannels.getOrNull(fallbackIndex)?.let { channel ->
                if (groups.isNotEmpty()) groupIndex = fallbackGroup
                channelIndex = fallbackIndex
                selectedChannelId = channel.id
                playingChannelId = channel.id
                focusZone = TvFocusZone.GUIDE
                restoredSessionAt = 1L
                startupDefaultApplied = true
            }
            return@LaunchedEffect
        }

        val restoredGroupIndex = when {
            session.lastGroupName.isNotBlank() -> groups.indexOf(session.lastGroupName)
            session.lastChannelId.isNotBlank() -> groups.indexOfFirst { groupName ->
                uiState.channelsByGroup[groupName].orEmpty().any { it.id == session.lastChannelId }
            }
            else -> -1
        }.coerceAtLeast(0)

        val effectiveGroupIndex = if (groups.isNotEmpty()) {
            restoredGroupIndex.coerceIn(0, groups.lastIndex)
        } else {
            0
        }
        if (groups.isNotEmpty()) {
            groupIndex = effectiveGroupIndex
        }
        val restoredGroup = groups.getOrNull(effectiveGroupIndex).orEmpty()
        val restoredChannels = uiState.channelsByGroup[restoredGroup].orEmpty()
        val restoredChannelIndex = if (session.lastChannelId.isNotBlank()) {
            restoredChannels.indexOfFirst { it.id == session.lastChannelId }
        } else {
            -1
        }
        if (restoredChannels.isNotEmpty()) {
            channelIndex = restoredChannelIndex.takeIf { it >= 0 } ?: 0
            val targetChannel = restoredChannels.getOrNull(channelIndex) ?: restoredChannels.first()
            selectedChannelId = targetChannel.id
            playingChannelId = targetChannel.id
        }
        focusZone = session.lastFocusedZone.toTvFocusZone(
            hasGroups = groups.isNotEmpty(),
            hasChannels = restoredChannels.isNotEmpty()
        )
        restoredSessionAt = session.lastOpenedAt
        startupDefaultApplied = true
    }

    LaunchedEffect(groups.size) {
        if (groupIndex >= groups.size) groupIndex = 0
    }
    LaunchedEffect(selectedGroup, channels.size, focusZone, playingChannelId) {
        if (selectedGroup.isBlank() || channels.isEmpty()) return@LaunchedEffect
        val preferredIndex = channels.preferredIndexFor(selectedChannelId, playingChannelId)
            .coerceIn(0, channels.lastIndex)
        if (channelIndex != preferredIndex && (focusZone == TvFocusZone.GUIDE || selectedChannelId == null)) {
            channelIndex = preferredIndex
        }
        if (selectedChannelId == null || channels.none { it.id == selectedChannelId }) {
            selectedChannelId = channels[preferredIndex].id
        }
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
    LaunchedEffect(selectedGroup, selectedGroupChannelIds) {
        if (selectedGroup.isBlank() || channels.isEmpty()) return@LaunchedEffect
        delay(260L)
        viewModel.prefetchVisibleCategoryEpg(
            channelIds = selectedGroupChannelIds,
            selectedChannelId = latestEpgAnchorChannelId,
            eagerLimit = if (selectedGroup.isPriorityGuideGroup()) minOf(channels.size, 480) else minOf(channels.size, 140),
            backgroundLimit = if (selectedGroup.isPriorityGuideGroup()) minOf(channels.size, 1200) else minOf(channels.size, 420)
        )
    }
    LaunchedEffect(selectedGroup, focusZone, selectedChannelId, isFastNavigating, isFullScreen) {
        if (isFullScreen || focusZone != TvFocusZone.GROUPS) return@LaunchedEffect
        val targetChannelId = selectedChannelId ?: return@LaunchedEffect
        if (targetChannelId == playingChannelId) return@LaunchedEffect
        if (channels.none { it.id == targetChannelId }) return@LaunchedEffect
        if (isFastNavigating) return@LaunchedEffect
        delay(120L)
        if (
            !isFullScreen &&
            focusZone == TvFocusZone.GROUPS &&
            !isFastNavigating &&
            channels.any { it.id == targetChannelId } &&
            selectedChannelId == targetChannelId &&
            playingChannelId != targetChannelId
        ) {
            playingChannelId = targetChannelId
        }
    }
    LaunchedEffect(playingChannelId) {
        val currentChannelId = playingChannelId ?: return@LaunchedEffect
        viewModel.rememberTvSession(
            lastChannelId = currentChannelId,
            lastGroupName = selectedGroup,
            lastFocusedZone = if (isFullScreen) TvFocusZone.GUIDE.name else focusZone.name,
            markOpened = true
        )
    }
    LaunchedEffect(selectedGroup, focusZone, selectedChannelId, isFullScreen) {
        if (selectedGroup.isBlank() && selectedChannelId == null && playingChannelId == null) return@LaunchedEffect
        viewModel.rememberTvSession(
            lastChannelId = playingChannelId ?: selectedChannelId,
            lastGroupName = selectedGroup,
            lastFocusedZone = if (isFullScreen) TvFocusZone.GUIDE.name else focusZone.name
        )
    }

    // OkHttp with connection pooling for faster channel switching
    val iptvHttpClient = remember {
        OkHttpClient.Builder()
            .connectionPool(ConnectionPool(8, 10, TimeUnit.MINUTES))
            .followRedirects(true)
            .followSslRedirects(true)
            .retryOnConnectionFailure(true)
            .dns(OkHttpProvider.dns)
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(300, TimeUnit.SECONDS) // 5 min — live streams should not timeout during normal playback
            .build()
    }
    val iptvDataSourceFactory = remember(iptvHttpClient) {
        OkHttpDataSource.Factory(iptvHttpClient)
            .setUserAgent("ARVIO/1.2.0 (Android TV)")
    }
    // HLS factory with chunkless preparation (used when stream is detected as HLS)
    val iptvHlsFactory = remember(iptvDataSourceFactory) {
        HlsMediaSource.Factory(iptvDataSourceFactory)
            .setAllowChunklessPreparation(true)
    }
    // Default factory handles all formats (MPEG-TS, HLS, DASH, progressive, etc.)
    val iptvDefaultFactory = remember(iptvDataSourceFactory) {
        DefaultMediaSourceFactory(context)
            .setDataSourceFactory(iptvDataSourceFactory)
    }

    // Track whether ExoPlayer has been released to guard against post-dispose calls
    var isPlayerReleased by remember { mutableStateOf(false) }

    var exoPlayer by remember { mutableStateOf<ExoPlayer?>(null) }

    var miniPlayerView by remember { mutableStateOf<PlayerView?>(null) }
    var fullPlayerView by remember { mutableStateOf<PlayerView?>(null) }

    // Keep an always-current reference to the playing channel's stream URL
    // so the error listener never captures a stale closure.
    val currentStreamUrl by rememberUpdatedState(playingChannel?.streamUrl)

    DisposableEffect(Unit) {
        onDispose {
            isPlayerReleased = true
            exoPlayer?.release()
            exoPlayer = null
        }
    }

    // Pause playback when the activity goes to background, resume when it comes back
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_PAUSE -> exoPlayer?.pause()
                Lifecycle.Event.ON_RESUME -> if (playingChannelId != null) exoPlayer?.play()
                else -> {}
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
    }

    // Helper: prepare ExoPlayer with a stream URL (shared by normal play + error retry)
    fun prepareStream(stream: String, drmInfo: com.arflix.tv.data.model.DrmInfo? = null) {
        val player = exoPlayer ?: return
        if (isPlayerReleased) return
        player.stop()
        player.clearMediaItems()
        val mediaItem = MediaItem.Builder()
            .setUri(stream)
            .setLiveConfiguration(
                MediaItem.LiveConfiguration.Builder()
                    .setMinPlaybackSpeed(1.0f)
                    .setMaxPlaybackSpeed(1.0f)
                    .setTargetOffsetMs(4_000)
                    .build()
            )
            .apply {
                // DRM configuration from #KODIPROP directives
                drmInfo?.let { drm ->
                    val schemeUuid = com.arflix.tv.util.ClearKeyUtil.drmSchemeToUuid(drm.scheme)
                    val drmBuilder = MediaItem.DrmConfiguration.Builder(schemeUuid)
                    if (drm.scheme == "clearkey" && !drm.licenseUrl.isNullOrBlank()) {
                        com.arflix.tv.util.ClearKeyUtil.buildClearKeyLicenseUri(drm.licenseUrl)
                            ?.let { dataUri -> drmBuilder.setLicenseUri(dataUri) }
                    } else if (!drm.licenseUrl.isNullOrBlank()) {
                        drmBuilder.setLicenseUri(drm.licenseUrl.substringBefore("|"))
                    }
                    setDrmConfiguration(drmBuilder.build())
                }
            }
            .build()
        val streamLower = stream.lowercase()
        if (streamLower.contains(".m3u8") || streamLower.contains("/hls") || streamLower.contains("format=hls")) {
            player.setMediaSource(iptvHlsFactory.createMediaSource(mediaItem))
        } else {
            player.setMediaItem(mediaItem)
        }
        player.prepare()
        player.playWhenReady = true
    }

    // Track the last stream URL prepared to avoid redundant prepareStream calls
    var lastPreparedStreamUrl by remember { mutableStateOf<String?>(null) }

    // Instant playback: if we have a stream URL from Home, start playing immediately
    // before the full channel list is loaded.
    LaunchedEffect(Unit) {
        if (initialStreamUrl != null && initialChannelId != null) {
            playingChannelId = initialChannelId
            isFullScreen = true
            lastPreparedStreamUrl = initialStreamUrl
            if (exoPlayer == null) {
                exoPlayer = createTvExoPlayer(context, iptvDefaultFactory)
            }
            prepareStream(initialStreamUrl)
        }
    }

    var playerRetryCount by remember { mutableIntStateOf(0) }

    LaunchedEffect(playingChannelId, playingChannel?.streamUrl) {
        var stream = playingChannel?.streamUrl ?: return@LaunchedEffect
        if (isPlayerReleased) return@LaunchedEffect
        // Resolve Stalker portal cmd to actual stream URL
        if (stream.startsWith("ffmpeg") || (stream.startsWith("/") && !stream.startsWith("//"))) {
            val stalker = viewModel.iptvRepository.cachedStalkerApi
            if (stalker != null) {
                val resolved = stalker.resolveStreamUrl(stream)
                if (resolved != null) stream = resolved else return@LaunchedEffect
            }
        }
        if (stream == lastPreparedStreamUrl) return@LaunchedEffect
        if (exoPlayer == null) {
            exoPlayer = createTvExoPlayer(context, iptvDefaultFactory)
        }
        lastPreparedStreamUrl = stream
        playerRetryCount = 0
        prepareStream(stream, drmInfo = playingChannel?.drmInfo)
    }

    LaunchedEffect(isFullScreen, miniPlayerView, fullPlayerView, exoPlayer) {
        val player = exoPlayer ?: return@LaunchedEffect
        if (isPlayerReleased) return@LaunchedEffect
        if (isFullScreen) {
            miniPlayerView?.player = null
            val targetView = fullPlayerView ?: return@LaunchedEffect
            // Use postDelayed to ensure the view has been laid out after composition
            targetView.postDelayed({
                if (!isPlayerReleased) {
                    targetView.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
                    targetView.player = player
                    targetView.requestLayout()
                    targetView.invalidate()
                }
            }, 50)
        } else {
            fullPlayerView?.player = null
            val targetView = miniPlayerView ?: return@LaunchedEffect
            targetView.postDelayed({
                if (!isPlayerReleased) {
                    targetView.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
                    targetView.player = player
                    targetView.requestLayout()
                    targetView.invalidate()
                }
            }, 50)
        }
    }

    DisposableEffect(exoPlayer) {
        val player = exoPlayer ?: return@DisposableEffect onDispose { }
        val listener = object : Player.Listener {
            override fun onPlayerError(error: PlaybackException) {
                if (isPlayerReleased) return
                val stream = currentStreamUrl ?: return
                playerRetryCount++
                if (playerRetryCount > 3) {
                    // Stop retrying after 3 attempts
                    System.err.println("[IPTV] Playback failed after 3 retries: ${error.message} URL=$stream")
                    return
                }
                player.clearMediaItems()
                val mediaItem = MediaItem.Builder()
                    .setUri(stream)
                    .setLiveConfiguration(
                        MediaItem.LiveConfiguration.Builder()
                            .setMinPlaybackSpeed(1.0f)
                            .setMaxPlaybackSpeed(1.0f)
                            .setTargetOffsetMs(4_000)
                            .build()
                    )
                    .apply {
                        // Preserve DRM config on retry
                        playingChannel?.drmInfo?.let { drm ->
                            val schemeUuid = com.arflix.tv.util.ClearKeyUtil.drmSchemeToUuid(drm.scheme)
                            val drmBuilder = MediaItem.DrmConfiguration.Builder(schemeUuid)
                            if (drm.scheme == "clearkey" && !drm.licenseUrl.isNullOrBlank()) {
                                com.arflix.tv.util.ClearKeyUtil.buildClearKeyLicenseUri(drm.licenseUrl)
                                    ?.let { dataUri -> drmBuilder.setLicenseUri(dataUri) }
                            } else if (!drm.licenseUrl.isNullOrBlank()) {
                                drmBuilder.setLicenseUri(drm.licenseUrl.substringBefore("|"))
                            }
                            setDrmConfiguration(drmBuilder.build())
                        }
                    }
                    .build()
                player.setMediaItem(mediaItem)
                player.prepare()
                player.playWhenReady = true
            }
            override fun onVideoSizeChanged(videoSize: androidx.media3.common.VideoSize) {
                if (isPlayerReleased) return
                // Force PlayerView to re-apply resize mode once real video
                // dimensions are known, preventing the initial stretched frame.
                miniPlayerView?.let { pv ->
                    pv.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
                }
                fullPlayerView?.let { pv ->
                    pv.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
                }
            }
        }
        player.addListener(listener)
        onDispose { player.removeListener(listener) }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(appBackgroundDark())
            .focusRequester(rootFocusRequester)
            .focusable()
            .onFocusChanged {
                rootHasFocus = it.hasFocus
                if (!it.hasFocus) {
                    centerDownAtMs = null
                }
            }
            .onPreviewKeyEvent { event ->
                // When context menu is open, let it handle all key events
                if (showGroupContextMenu) {
                    if (event.type == KeyEventType.KeyDown && (event.key == Key.Back || event.key == Key.Escape)) {
                        showGroupContextMenu = false
                        return@onPreviewKeyEvent true
                    }
                    return@onPreviewKeyEvent false
                }
                if (isFullScreen) {
                    if (event.type == KeyEventType.KeyDown) {
                        when (event.key) {
                            Key.Back, Key.Escape -> {
                                if (showFullscreenOverlay) {
                                    showFullscreenOverlay = false
                                } else {
                                    // Always return to EPG guide first, regardless of launch source
                                    isFullScreen = false
                                    showFullscreenOverlay = false
                                }
                                return@onPreviewKeyEvent true
                            }
                            Key.Enter, Key.DirectionCenter -> {
                                // Toggle EPG info overlay
                                showFullscreenOverlay = !showFullscreenOverlay
                                if (showFullscreenOverlay) {
                                    fullscreenOverlayTrigger = System.currentTimeMillis()
                                }
                                return@onPreviewKeyEvent true
                            }
                            Key.DirectionUp -> {
                                // Switch to next channel (up = next in list)
                                if (channels.isNotEmpty()) {
                                    val currentIdx = playingChannelId?.let { channelIndexById[it] } ?: -1
                                    val nextIdx = if (currentIdx < 0) 0 else (currentIdx + 1) % channels.size
                                    val nextChannel = channels[nextIdx]
                                    channelIndex = nextIdx
                                    selectedChannelId = nextChannel.id
                                    playingChannelId = nextChannel.id
                                    // Show overlay briefly on channel switch
                                    showFullscreenOverlay = true
                                    fullscreenOverlayTrigger = System.currentTimeMillis()
                                }
                                return@onPreviewKeyEvent true
                            }
                            Key.DirectionDown -> {
                                // Switch to previous channel (down = previous in list)
                                if (channels.isNotEmpty()) {
                                    val currentIdx = playingChannelId?.let { channelIndexById[it] } ?: -1
                                    val prevIdx = if (currentIdx <= 0) channels.lastIndex else currentIdx - 1
                                    val prevChannel = channels[prevIdx]
                                    channelIndex = prevIdx
                                    selectedChannelId = prevChannel.id
                                    playingChannelId = prevChannel.id
                                    // Show overlay briefly on channel switch
                                    showFullscreenOverlay = true
                                    fullscreenOverlayTrigger = System.currentTimeMillis()
                                }
                                return@onPreviewKeyEvent true
                            }
                            else -> return@onPreviewKeyEvent false
                        }
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
                                showGroupContextMenu = true
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
                                when (topBarFocusedItem(sidebarFocusIndex, hasProfile)) {
                                    SidebarItem.SEARCH -> onNavigateToSearch()
                                    SidebarItem.HOME -> onNavigateToHome()
                                    SidebarItem.WATCHLIST -> onNavigateToWatchlist()
                                    SidebarItem.TV -> Unit
                                    SidebarItem.SETTINGS -> onNavigateToSettings()
                                    null -> Unit
                                }
                            }
                            true
                        }

                        TvFocusZone.GROUPS -> {
                            if (channels.isNotEmpty()) {
                                val targetIndex = channels.preferredIndexFor(selectedChannelId, playingChannelId)
                                    .coerceIn(0, channels.lastIndex)
                                channelIndex = targetIndex
                                selectedChannelId = channels[targetIndex].id
                                focusZone = TvFocusZone.GUIDE
                            }
                            true
                        }

                        TvFocusZone.GUIDE -> {
                            channels.getOrNull(safeChannelIndex)?.let { channel ->
                                if (playingChannelId == channel.id) {
                                    selectedChannelId = channel.id
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
                    centerDownAtMs = null
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
                            markNavigation()
                            when (focusZone) {
                                TvFocusZone.SIDEBAR -> if (sidebarFocusIndex > 0) {
                                    sidebarFocusIndex = (sidebarFocusIndex - 1).coerceIn(0, maxSidebarIndex)
                                }
                                TvFocusZone.GROUPS -> Unit
                                TvFocusZone.GUIDE -> focusZone = TvFocusZone.GROUPS
                            }
                            true
                        }

                        Key.DirectionRight -> {
                            markNavigation()
                            when (focusZone) {
                                TvFocusZone.SIDEBAR -> if (sidebarFocusIndex < maxSidebarIndex) {
                                    sidebarFocusIndex = (sidebarFocusIndex + 1).coerceIn(0, maxSidebarIndex)
                                }
                                TvFocusZone.GROUPS -> if (channels.isNotEmpty()) {
                                    val targetIndex = channels.preferredIndexFor(selectedChannelId, playingChannelId)
                                        .coerceIn(0, channels.lastIndex)
                                    channelIndex = targetIndex
                                    selectedChannelId = channels[targetIndex].id
                                    focusZone = TvFocusZone.GUIDE
                                }
                                TvFocusZone.GUIDE -> Unit
                            }
                            true
                        }

                        Key.DirectionUp -> {
                            markNavigation()
                            when (focusZone) {
                                TvFocusZone.SIDEBAR -> Unit

                                TvFocusZone.GROUPS -> if (groupIndex > 0) groupIndex-- else focusZone = TvFocusZone.SIDEBAR
                                TvFocusZone.GUIDE -> if (channelIndex > 0) channelIndex-- else focusZone = TvFocusZone.SIDEBAR
                            }
                            true
                        }

                        Key.DirectionDown -> {
                            markNavigation()
                            when (focusZone) {
                                TvFocusZone.SIDEBAR -> if (groups.isNotEmpty()) focusZone = TvFocusZone.GROUPS

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
        if (!LocalDeviceType.current.isTouchDevice()) {
            AppTopBar(
                selectedItem = SidebarItem.TV,
                isFocused = focusZone == TvFocusZone.SIDEBAR,
                focusedIndex = sidebarFocusIndex,
                profile = currentProfile
            )
        }

        if (!uiState.isConfigured) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = AppTopBarContentTopInset)
                    .padding(horizontal = 24.dp, vertical = 24.dp)
            ) {
                NotConfiguredPanel()
            }
        } else {
            // Immersive layout: seamless dark surface, no compartment borders
            val categoryRailAlpha by animateFloatAsState(
                targetValue = if (focusZone == TvFocusZone.GROUPS || isFullScreen) 1f else 0.32f,
                animationSpec = androidx.compose.animation.core.tween(durationMillis = 140),
                label = "tv-category-rail-alpha"
            )
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = contentTopPadding)
            ) {
                Box(
                    modifier = Modifier
                        .width(if (isMobile) 140.dp else 170.dp)
                        .fillMaxHeight()
                        .padding(start = 6.dp, top = 4.dp, bottom = 4.dp, end = 6.dp)
                ) {
                    CategoryRail(
                        groups = groups,
                        favoriteGroups = favoriteGroups,
                        focusedGroupIndex = safeGroupIndex,
                        isFocused = focusZone == TvFocusZone.GROUPS,
                        listState = groupsListState,
                        onGroupClick = { index ->
                            groupIndex = index
                            focusZone = TvFocusZone.GROUPS
                            val nextChannels = uiState.channelsByGroup[groups.getOrNull(index).orEmpty()].orEmpty()
                            val targetIndex = nextChannels.preferredIndexFor(selectedChannelId, playingChannelId)
                            channelIndex = targetIndex.coerceAtLeast(0)
                            selectedChannelId = nextChannels.getOrNull(targetIndex)?.id
                        },
                        onGroupLongPress = { index ->
                            groupIndex = index
                            showGroupContextMenu = true
                        },
                        showMenuForIndex = if (showGroupContextMenu) safeGroupIndex else -1,
                        onDismissMenu = { showGroupContextMenu = false },
                        onToggleFavorite = { viewModel.toggleFavoriteGroup(it) },
                        onToggleHidden = { viewModel.toggleHiddenGroup(it) },
                        onMoveUp = { viewModel.moveGroupUp(it) },
                        onMoveToTop = { viewModel.moveGroupToTop(it) },
                        onMoveDown = { viewModel.moveGroupDown(it) },
                        modifier = Modifier.graphicsLayer { alpha = categoryRailAlpha }
                    )
                }

                // Main content: EPG info + player top, guide bottom
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                ) {
                    // Top section: EPG info left + mini player right
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.36f)
                            .background(appBackgroundDark())
                    ) {
                        // Left: channel EPG info (current / next / after)
                        val epgSlice = playingChannel?.id?.let { uiState.snapshot.nowNext[it] }
                        Column(
                            modifier = Modifier
                                .weight(0.45f)
                                .fillMaxHeight()
                                .padding(start = if (isMobile) 10.dp else 14.dp, top = if (isMobile) 6.dp else 10.dp, bottom = if (isMobile) 6.dp else 8.dp, end = if (isMobile) 6.dp else 10.dp),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            if (playingChannel != null) {
                                // Channel name + logo
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    if (!playingChannel.logo.isNullOrBlank()) {
                                        AsyncImage(
                                            model = playingChannel.logo,
                                            contentDescription = playingChannel.name,
                                            contentScale = ContentScale.Fit,
                                            modifier = Modifier
                                                .size(if (isMobile) 26.dp else 32.dp)
                                                .clip(RoundedCornerShape(5.dp))
                                                .background(Color.White.copy(alpha = 0.04f))
                                        )
                                        Spacer(modifier = Modifier.width(if (isMobile) 6.dp else 8.dp))
                                    }
                                    Column {
                                        Text(
                                            text = playingChannel.name,
                                            style = ArflixTypography.cardTitle.copy(fontSize = if (isMobile) 14.sp else 16.sp),
                                            color = Color.White,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                        Text(
                                            text = playingChannel.group,
                                            style = ArflixTypography.caption.copy(fontSize = if (isMobile) 9.sp else 10.sp),
                                            color = Color.White.copy(alpha = 0.35f),
                                            maxLines = 1
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(if (isMobile) 6.dp else 10.dp))

                                // NOW
                                val nowProg = epgSlice?.now
                                if (nowProg != null) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text(
                                            text = stringResource(R.string.now).uppercase(),
                                            style = ArflixTypography.caption.copy(fontSize = if (isMobile) 8.sp else 9.sp, fontWeight = FontWeight.Bold),
                                            color = Color.Black,
                                            modifier = Modifier
                                                .background(AccentGreen, RoundedCornerShape(3.dp))
                                                .padding(horizontal = 5.dp, vertical = 1.dp)
                                        )
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text(
                                            text = "${formatProgramTime(nowProg.startUtcMillis)} - ${formatProgramTime(nowProg.endUtcMillis)}",
                                            style = ArflixTypography.caption.copy(fontSize = if (isMobile) 9.sp else 10.sp),
                                            color = Color.White.copy(alpha = 0.5f)
                                        )
                                    }
                                    Text(
                                        text = nowProg.title,
                                        style = ArflixTypography.body.copy(fontSize = if (isMobile) 12.sp else 14.sp, fontWeight = FontWeight.Medium),
                                        color = Color.White.copy(alpha = 0.9f),
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }

                                // NEXT
                                val nextProg = epgSlice?.next
                                if (nextProg != null) {
                                    Spacer(modifier = Modifier.height(if (isMobile) 4.dp else 6.dp))
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text(
                                            text = stringResource(R.string.next).uppercase(),
                                            style = ArflixTypography.caption.copy(fontSize = if (isMobile) 8.sp else 9.sp, fontWeight = FontWeight.Bold),
                                            color = Color.White.copy(alpha = 0.7f),
                                            modifier = Modifier
                                                .background(Color.White.copy(alpha = 0.08f), RoundedCornerShape(3.dp))
                                                .padding(horizontal = 5.dp, vertical = 1.dp)
                                        )
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text(
                                            text = formatProgramTime(nextProg.startUtcMillis),
                                            style = ArflixTypography.caption.copy(fontSize = if (isMobile) 9.sp else 10.sp),
                                            color = Color.White.copy(alpha = 0.4f)
                                        )
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text(
                                            text = nextProg.title,
                                            style = ArflixTypography.caption.copy(fontSize = if (isMobile) 10.sp else 11.sp),
                                            color = Color.White.copy(alpha = 0.55f),
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                    }
                                }

                                // LATER
                                val laterProg = epgSlice?.later
                                if (laterProg != null) {
                                    Spacer(modifier = Modifier.height(if (isMobile) 3.dp else 4.dp))
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text(
                                            text = stringResource(R.string.later).uppercase(),
                                            style = ArflixTypography.caption.copy(fontSize = if (isMobile) 8.sp else 9.sp),
                                            color = Color.White.copy(alpha = 0.4f),
                                            modifier = Modifier
                                                .background(Color.White.copy(alpha = 0.04f), RoundedCornerShape(3.dp))
                                                .padding(horizontal = 5.dp, vertical = 1.dp)
                                        )
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text(
                                            text = formatProgramTime(laterProg.startUtcMillis),
                                            style = ArflixTypography.caption.copy(fontSize = if (isMobile) 9.sp else 10.sp),
                                            color = Color.White.copy(alpha = 0.3f)
                                        )
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text(
                                            text = laterProg.title,
                                            style = ArflixTypography.caption.copy(fontSize = if (isMobile) 10.sp else 11.sp),
                                            color = Color.White.copy(alpha = 0.35f),
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                    }
                                }
                            } else {
                                // No channel placeholder
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.CenterStart
                                ) {
                                    Text(
                                        "Select a channel",
                                        style = ArflixTypography.body.copy(fontSize = if (isMobile) 12.sp else 14.sp),
                                        color = Color.White.copy(alpha = 0.2f)
                                    )
                                }
                            }
                        }

                        // Right: mini player
                        Box(
                            modifier = Modifier
                                .weight(0.55f)
                                .fillMaxHeight()
                                .padding(top = 4.dp, bottom = 4.dp, end = 4.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0xFF0A0A0A))
                                .clickable {
                                    if (playingChannel != null) {
                                        selectedChannelId = playingChannel.id
                                        isFullScreen = true
                                    }
                                }
                        ) {
                            if (playingChannel != null && !isFullScreen && exoPlayer != null) {
                                AndroidView(
                                    factory = { ctx ->
                                        PlayerView(ctx).apply {
                                            keepScreenOn = true
                                            miniPlayerView = this
                                            player = null
                                            useController = false
                                            resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
                                            setKeepContentOnPlayerReset(true)
                                            setShutterBackgroundColor(0xFF0A0A0A.toInt())
                                        }
                                    },
                                    modifier = Modifier.fillMaxSize(),
                                    update = { playerView ->
                                        playerView.keepScreenOn = true
                                        miniPlayerView = playerView
                                        if (!isFullScreen) {
                                            playerView.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
                                            if (playerView.player !== exoPlayer) playerView.player = exoPlayer
                                        }
                                    }
                                )
                            }
                        }
                    }

                    // Channel guide - seamless below video
                    GuidePanel(
                        channels = channels,
                        nowNext = uiState.snapshot.nowNext,
                        isLoading = uiState.isLoading,
                        focusedChannelIndex = safeChannelIndex,
                        guideFocused = focusZone == TvFocusZone.GUIDE,
                        fastNavigating = isFastNavigating,
                        playingChannelId = playingChannelId,
                        favoriteChannels = favoriteChannels,
                        listState = channelsListState,
                        onChannelClick = { index ->
                            val channel = channels.getOrNull(index) ?: return@GuidePanel
                            channelIndex = index
                            focusZone = TvFocusZone.GUIDE
                            if (playingChannelId == channel.id) {
                                selectedChannelId = channel.id
                                isFullScreen = true
                            } else {
                                selectedChannelId = channel.id
                                playingChannelId = channel.id
                            }
                        },
                        modifier = Modifier.weight(0.64f)
                    )
                }
            }
        }

        if (isFullScreen) {
            // Show black screen immediately when fullscreen is active.
            // Player and EPG overlay only render once playingChannel resolves.
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
                    .clickable {
                        // Tap toggles EPG info overlay (mirrors D-pad Enter behavior)
                        showFullscreenOverlay = !showFullscreenOverlay
                        if (showFullscreenOverlay) {
                            fullscreenOverlayTrigger = System.currentTimeMillis()
                        }
                    }
            ) {
                if (playingChannel != null) {
                    val fsNowNext = uiState.snapshot.nowNext[playingChannel.id]
                    val fsNow = fsNowNext?.now
                    val fsNext = fsNowNext?.next

                    // Auto-hide overlay after 5 seconds
                    LaunchedEffect(fullscreenOverlayTrigger, showFullscreenOverlay) {
                        if (showFullscreenOverlay && fullscreenOverlayTrigger > 0L) {
                            kotlinx.coroutines.delay(5000L)
                            showFullscreenOverlay = false
                        }
                    }

                    AndroidView(
                        factory = { ctx ->
                            PlayerView(ctx).apply {
                                keepScreenOn = true
                                fullPlayerView = this
                                player = null
                                useController = false
                                resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
                                setKeepContentOnPlayerReset(true)
                                setShutterBackgroundColor(android.graphics.Color.TRANSPARENT)
                            }
                        },
                        modifier = Modifier.fillMaxSize(),
                        update = { playerView ->
                            playerView.keepScreenOn = true
                            fullPlayerView = playerView
                            // Do NOT assign player here; let the LaunchedEffect handle it via postDelayed
                            // to ensure the view has been laid out first (avoids black screen).
                            playerView.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
                        }
                    )

                    // Premium EPG overlay (toggle with OK/tap, auto-hides after 5s)
                    AnimatedVisibility(
                        visible = showFullscreenOverlay,
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        FullscreenEpgOverlay(
                            channel = playingChannel,
                            nowProgram = fsNow,
                            nextProgram = fsNext,
                            isMobile = isMobile
                        )
                    }

                    // Mobile controls: back button + channel prev/next
                    if (isMobile) {
                        AnimatedVisibility(
                            visible = showFullscreenOverlay,
                            enter = fadeIn(),
                            exit = fadeOut()
                        ) {
                            // Back button (top-left)
                            Box(
                                modifier = Modifier.fillMaxSize()
                            ) {
                                IconButton(
                                    onClick = {
                                        isFullScreen = false
                                        showFullscreenOverlay = false
                                    },
                                    modifier = Modifier
                                        .align(Alignment.TopStart)
                                        .padding(start = 8.dp, top = 8.dp)
                                        .size(44.dp)
                                        .background(Color.Black.copy(alpha = 0.5f), RoundedCornerShape(22.dp))
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.ArrowBack,
                                        contentDescription = stringResource(R.string.back),
                                        tint = Color.White,
                                        modifier = Modifier.size(24.dp)
                                    )
                                }

                                // Channel prev/next buttons (right side, vertically centered)
                                Column(
                                    modifier = Modifier
                                        .align(Alignment.CenterEnd)
                                        .padding(end = 12.dp),
                                    verticalArrangement = Arrangement.spacedBy(16.dp)
                                ) {
                                    IconButton(
                                        onClick = {
                                            if (channels.isNotEmpty()) {
                                                val currentIdx = playingChannelId?.let { channelIndexById[it] } ?: -1
                                                val prevIdx = if (currentIdx <= 0) channels.lastIndex else currentIdx - 1
                                                val prevChannel = channels[prevIdx]
                                                channelIndex = prevIdx
                                                selectedChannelId = prevChannel.id
                                                playingChannelId = prevChannel.id
                                                fullscreenOverlayTrigger = System.currentTimeMillis()
                                            }
                                        },
                                        modifier = Modifier
                                            .size(44.dp)
                                            .background(Color.Black.copy(alpha = 0.5f), RoundedCornerShape(22.dp))
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.SkipPrevious,
                                            contentDescription = stringResource(R.string.back),
                                            tint = Color.White,
                                            modifier = Modifier.size(24.dp)
                                        )
                                    }
                                    IconButton(
                                        onClick = {
                                            if (channels.isNotEmpty()) {
                                                val currentIdx = playingChannelId?.let { channelIndexById[it] } ?: -1
                                                val nextIdx = if (currentIdx < 0) 0 else (currentIdx + 1) % channels.size
                                                val nextChannel = channels[nextIdx]
                                                channelIndex = nextIdx
                                                selectedChannelId = nextChannel.id
                                                playingChannelId = nextChannel.id
                                                fullscreenOverlayTrigger = System.currentTimeMillis()
                                            }
                                        },
                                        modifier = Modifier
                                            .size(44.dp)
                                            .background(Color.Black.copy(alpha = 0.5f), RoundedCornerShape(22.dp))
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.SkipNext,
                                            contentDescription = stringResource(R.string.next),
                                            tint = Color.White,
                                            modifier = Modifier.size(24.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
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
    val firstVisible = state.firstVisibleItemIndex
    val lastVisible = state.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: firstVisible
    val visibleSpan = (lastVisible - firstVisible).coerceAtLeast(0) + 1
    val outsideViewport = safe < firstVisible || safe > lastVisible
    val distance = abs(firstVisible - safe)
    if (safe == firstVisible && state.firstVisibleItemScrollOffset == 0) return
    runCatching {
        if (safe == 0 || distance > visibleSpan * 2) {
            state.scrollToItem(safe)
        } else if (distance <= 2 || (!outsideViewport && distance <= visibleSpan) || distance <= visibleSpan + 1) {
            state.animateScrollToItem(safe)
        } else {
            state.scrollToItem(safe)
        }
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
    onGroupClick: (Int) -> Unit = {},
    onGroupLongPress: (Int) -> Unit = {},
    showMenuForIndex: Int = -1,
    onDismissMenu: () -> Unit = {},
    onToggleFavorite: (String) -> Unit = {},
    onToggleHidden: (String) -> Unit = {},
    onMoveUp: (String) -> Unit = {},
    onMoveToTop: (String) -> Unit = {},
    onMoveDown: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    LazyColumn(
        state = listState,
        verticalArrangement = Arrangement.spacedBy(1.dp),
        modifier = modifier.arvioDpadFocusGroup()
    ) {
        itemsIndexed(groups, key = { _, group -> group }, contentType = { _, _ -> "category_group" }) { index, group ->
            GroupRailItem(
                name = group,
                isFocused = isFocused && index == focusedGroupIndex,
                isFavorite = favoriteGroups.contains(group),
                onClick = { onGroupClick(index) },
                onLongPress = { onGroupLongPress(index) },
                showMenu = index == showMenuForIndex,
                onDismissMenu = onDismissMenu,
                onToggleFavorite = { onToggleFavorite(group) },
                onToggleHidden = { onToggleHidden(group) },
                onMoveUp = { onMoveUp(group) },
                onMoveToTop = { onMoveToTop(group) },
                onMoveDown = { onMoveDown(group) }
            )
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
private fun GroupRailItem(
    name: String, isFocused: Boolean, isFavorite: Boolean,
    showMenu: Boolean = false,
    onClick: () -> Unit = {},
    onLongPress: () -> Unit = {},
    onDismissMenu: () -> Unit = {},
    onToggleFavorite: () -> Unit = {},
    onToggleHidden: () -> Unit = {},
    onMoveUp: () -> Unit = {},
    onMoveToTop: () -> Unit = {},
    onMoveDown: () -> Unit = {}
) {
    var ignoreMenuSelectUntilRelease by remember(showMenu) { mutableStateOf(showMenu) }

    val accent = resolveAccentColor(fallback = Color.White)

    Box {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(6.dp))
            .background(if (isFocused) accent.copy(alpha = 0.2f) else Color.Transparent)
            .then(if (isFocused) Modifier.border(1.5.dp, accent, RoundedCornerShape(6.dp)) else Modifier)
            .combinedClickable(onClick = onClick, onLongClick = onLongPress)
            .padding(horizontal = 8.dp, vertical = 7.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isFavorite) {
            Icon(Icons.Default.Star, null, tint = Color(0xFFF5C518).copy(alpha = 0.8f), modifier = Modifier.size(10.dp))
            Spacer(modifier = Modifier.width(5.dp))
        }
        Text(name, style = ArflixTypography.caption.copy(fontSize = 11.sp, fontWeight = if (isFocused) FontWeight.Medium else FontWeight.Normal, lineHeight = 14.sp),
            color = if (isFocused) Color.White else Color.White.copy(alpha = 0.4f), maxLines = 1, overflow = TextOverflow.Ellipsis)
    }
    if (showMenu) {
        DropdownMenu(
            expanded = true,
            onDismissRequest = onDismissMenu,
            modifier = Modifier
                .background(Color.Black.copy(alpha = 0.95f))
                .onPreviewKeyEvent { event ->
                    val isSelect = event.key == Key.DirectionCenter || event.key == Key.Enter
                    if (ignoreMenuSelectUntilRelease && isSelect) {
                        if (event.type == KeyEventType.KeyUp) {
                            ignoreMenuSelectUntilRelease = false
                        }
                        true
                    } else {
                        false
                    }
                }
        ) {
            FocusableMenuItem(if (isFavorite) "Unfavorite" else "Favorite", if (isFavorite) Icons.Default.StarOutline else Icons.Default.Star, Color(0xFFF5C518)) { onDismissMenu(); onToggleFavorite() }
            FocusableMenuItem("Hide", Icons.Default.VisibilityOff) { onDismissMenu(); onToggleHidden() }
            FocusableMenuItem("Move to Top", Icons.Default.KeyboardArrowUp) { onDismissMenu(); onMoveToTop() }
            FocusableMenuItem("Move Up", Icons.Default.KeyboardArrowUp) { onDismissMenu(); onMoveUp() }
            FocusableMenuItem("Move Down", Icons.Default.KeyboardArrowDown) { onDismissMenu(); onMoveDown() }
        }
    }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun FullscreenEpgOverlay(
    channel: IptvChannel,
    nowProgram: IptvProgram?,
    nextProgram: IptvProgram?,
    isMobile: Boolean = false
) {
    val topScrimBrush = remember {
        androidx.compose.ui.graphics.Brush.verticalGradient(
            colorStops = arrayOf(
                0.0f to Color.Black.copy(alpha = 0.8f),
                0.7f to Color.Black.copy(alpha = 0.4f),
                1.0f to Color.Transparent
            )
        )
    }
    val bottomScrimBrush = remember {
        androidx.compose.ui.graphics.Brush.verticalGradient(
            colorStops = arrayOf(
                0.0f to Color.Transparent,
                0.3f to Color.Black.copy(alpha = 0.4f),
                1.0f to Color.Black.copy(alpha = 0.85f)
            )
        )
    }
    Box(modifier = Modifier.fillMaxSize()) {
        // Top scrim: channel info
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .background(topScrimBrush)
                .padding(
                    start = if (isMobile) 16.dp else 32.dp,
                    end = if (isMobile) 16.dp else 32.dp,
                    top = if (isMobile) 16.dp else 24.dp,
                    bottom = if (isMobile) 24.dp else 40.dp
                )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.align(Alignment.TopStart)
            ) {
                if (!channel.logo.isNullOrBlank()) {
                    AsyncImage(
                        model = channel.logo,
                        contentDescription = channel.name,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .size(if (isMobile) 36.dp else 48.dp)
                            .clip(RoundedCornerShape(if (isMobile) 6.dp else 8.dp))
                            .background(Color.White.copy(alpha = 0.1f))
                    )
                    Spacer(modifier = Modifier.width(if (isMobile) 10.dp else 14.dp))
                }
                Column {
                    Text(
                        text = channel.name,
                        style = ArflixTypography.sectionTitle.copy(fontSize = if (isMobile) 14.sp else 22.sp),
                        color = Color.White,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = channel.group,
                        style = ArflixTypography.caption.copy(fontSize = if (isMobile) 11.sp else 12.sp),
                        color = Color.White.copy(alpha = 0.5f),
                        maxLines = 1
                    )
                }
            }
            // Clock on the right
            Text(
                text = programTimeFormatter.format(java.time.LocalTime.now()),
                style = ArflixTypography.sectionTitle.copy(fontSize = if (isMobile) 13.sp else 18.sp),
                color = Color.White.copy(alpha = 0.8f),
                modifier = Modifier.align(Alignment.TopEnd)
            )
        }

        // Bottom scrim: NOW / NEXT program info
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .background(bottomScrimBrush)
                .padding(
                    start = if (isMobile) 16.dp else 32.dp,
                    end = if (isMobile) 16.dp else 32.dp,
                    top = if (isMobile) 24.dp else 40.dp,
                    bottom = if (isMobile) 18.dp else 28.dp
                )
        ) {
            Column(modifier = Modifier.align(Alignment.BottomStart)) {
                // NOW program
                if (nowProgram != null) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = stringResource(R.string.now).uppercase(),
                            style = ArflixTypography.caption.copy(fontWeight = FontWeight.Bold, fontSize = if (isMobile) 10.sp else 12.sp),
                            color = Color.Black,
                            modifier = Modifier
                                .background(AccentGreen, RoundedCornerShape(4.dp))
                                .padding(horizontal = if (isMobile) 6.dp else 8.dp, vertical = if (isMobile) 2.dp else 3.dp)
                        )
                        Spacer(modifier = Modifier.width(if (isMobile) 8.dp else 10.dp))
                        Text(
                            text = "${formatProgramTime(nowProgram.startUtcMillis)} - ${formatProgramTime(nowProgram.endUtcMillis)}",
                            style = ArflixTypography.caption.copy(fontSize = if (isMobile) 11.sp else 14.sp),
                            color = Color.White.copy(alpha = 0.7f)
                        )
                    }
                    Spacer(modifier = Modifier.height(if (isMobile) 3.dp else 4.dp))
                    Text(
                        text = nowProgram.title,
                        style = ArflixTypography.sectionTitle.copy(fontSize = if (isMobile) 14.sp else 20.sp),
                        color = Color.White,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    // Progress bar
                    val progDuration = (nowProgram.endUtcMillis - nowProgram.startUtcMillis).coerceAtLeast(1L)
                    val progElapsed = (System.currentTimeMillis() - nowProgram.startUtcMillis).coerceIn(0, progDuration)
                    val progFraction = (progElapsed.toFloat() / progDuration.toFloat()).coerceIn(0f, 1f)
                    Spacer(modifier = Modifier.height(if (isMobile) 6.dp else 8.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(if (isMobile) 0.6f else 0.4f)
                            .height(3.dp)
                            .clip(RoundedCornerShape(2.dp))
                            .background(Color.White.copy(alpha = 0.15f))
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(progFraction)
                                .fillMaxHeight()
                                .clip(RoundedCornerShape(2.dp))
                                .background(AccentGreen)
                        )
                    }
                    nowProgram.description?.let { desc ->
                        if (desc.isNotBlank()) {
                            Spacer(modifier = Modifier.height(if (isMobile) 4.dp else 6.dp))
                            Text(
                                text = desc,
                                style = ArflixTypography.caption.copy(fontSize = if (isMobile) 11.sp else 13.sp),
                                color = Color.White.copy(alpha = 0.55f),
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.fillMaxWidth(if (isMobile) 0.85f else 0.6f)
                            )
                        }
                    }
                } else {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = stringResource(R.string.live).uppercase(),
                            style = ArflixTypography.caption.copy(fontWeight = FontWeight.Bold, fontSize = if (isMobile) 10.sp else 12.sp),
                            color = Color.Black,
                            modifier = Modifier
                                .background(AccentGreen, RoundedCornerShape(4.dp))
                                .padding(horizontal = if (isMobile) 6.dp else 8.dp, vertical = if (isMobile) 2.dp else 3.dp)
                        )
                        Spacer(modifier = Modifier.width(if (isMobile) 8.dp else 10.dp))
                        Text(
                            text = channel.name,
                            style = ArflixTypography.sectionTitle.copy(fontSize = if (isMobile) 14.sp else 20.sp),
                            color = Color.White
                        )
                    }
                }
                // NEXT program
                if (nextProgram != null) {
                    Spacer(modifier = Modifier.height(if (isMobile) 8.dp else 12.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = stringResource(R.string.next).uppercase(),
                            style = ArflixTypography.caption.copy(fontWeight = FontWeight.Bold, fontSize = if (isMobile) 9.sp else 11.sp),
                            color = Color.White.copy(alpha = 0.8f),
                            modifier = Modifier
                                .background(Color.White.copy(alpha = 0.15f), RoundedCornerShape(4.dp))
                                .padding(horizontal = if (isMobile) 6.dp else 8.dp, vertical = if (isMobile) 2.dp else 3.dp)
                        )
                        Spacer(modifier = Modifier.width(if (isMobile) 6.dp else 10.dp))
                        Text(
                            text = formatProgramTime(nextProgram.startUtcMillis),
                            style = ArflixTypography.caption.copy(fontSize = if (isMobile) 11.sp else 14.sp),
                            color = Color.White.copy(alpha = 0.5f)
                        )
                        Spacer(modifier = Modifier.width(if (isMobile) 6.dp else 8.dp))
                        Text(
                            text = nextProgram.title,
                            style = ArflixTypography.body.copy(fontSize = if (isMobile) 12.sp else 16.sp),
                            color = Color.White.copy(alpha = 0.65f),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun GuidePanel(
    channels: List<IptvChannel>,
    nowNext: Map<String, IptvNowNext>,
    isLoading: Boolean,
    focusedChannelIndex: Int,
    guideFocused: Boolean,
    fastNavigating: Boolean,
    playingChannelId: String?,
    favoriteChannels: Set<String>,
    listState: LazyListState,
    onChannelClick: (Int) -> Unit = {},
    modifier: Modifier = Modifier
) {
    // Refresh the current time every 30 seconds so the now-line and timeline stay accurate.
    var now by remember { mutableStateOf(System.currentTimeMillis()) }
    LaunchedEffect(Unit) {
        while (true) {
            kotlinx.coroutines.delay(30_000L)
            now = System.currentTimeMillis()
        }
    }
    val windowStart = now - (15 * 60_000L)   // 15 min past context
    val windowEnd = now + (180 * 60_000L)    // 3 hours future (fixes missing last-hour)
    val nowRatio = ((now - windowStart).toFloat() / (windowEnd - windowStart).toFloat()).coerceIn(0f, 1f)

    val isMobile = LocalDeviceType.current.isTouchDevice()

    // Seamless guide - no background box
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 2.dp, end = 4.dp)
    ) {
        GuideTimeHeader(windowStart = windowStart, now = now, windowEnd = windowEnd, isMobile = isMobile)

        if (channels.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    text = if (isLoading) "Loading channels..." else "No channels in this group",
                    style = ArflixTypography.body,
                    color = Color.White.copy(alpha = 0.3f)
                )
            }
        } else {
            LazyColumn(
                state = listState,
                verticalArrangement = Arrangement.spacedBy(2.dp),
                modifier = Modifier.fillMaxSize().arvioDpadFocusGroup()
            ) {
                itemsIndexed(
                    channels,
                    key = { _, ch -> ch.id },
                    contentType = { _, _ -> "guide_channel_row" }
                ) { index, channel ->
                    val focused = guideFocused && index == focusedChannelIndex
                    val slice = nowNext[channel.id]
                    val upcoming = remember(slice) {
                        buildList {
                            slice?.next?.let { add(it) }
                            slice?.later?.let { add(it) }
                            slice?.upcoming?.let { addAll(it) }
                        }.distinctBy { "${it.startUtcMillis}-${it.endUtcMillis}" }
                    }
                    GuideChannelRow(
                        channel = channel,
                        recentPrograms = slice?.recent.orEmpty(),
                        nowProgram = slice?.now,
                        upcomingPrograms = upcoming,
                        isFocused = focused,
                        isPlaying = channel.id == playingChannelId,
                        isFavoriteChannel = favoriteChannels.contains(channel.id),
                        showDetailedTimeline = focused || channel.id == playingChannelId,
                        windowStart = windowStart,
                        windowEnd = windowEnd,
                        now = now,
                        nowRatio = nowRatio,
                        isMobile = isMobile,
                        onClick = { onChannelClick(index) }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun GuideTimeHeader(windowStart: Long, now: Long, windowEnd: Long, isMobile: Boolean = false) {
    val timeStyle = ArflixTypography.caption.copy(fontSize = if (isMobile) 9.sp else 11.sp, letterSpacing = 0.2.sp)

    val halfHourMs = 30 * 60_000L
    val firstMark = ((windowStart / halfHourMs) + 1) * halfHourMs
    val hourMarkers = mutableListOf<Long>()
    var h = firstMark
    while (h < windowEnd) {
        hourMarkers.add(h)
        h += halfHourMs
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.width(if (isMobile) 120.dp else 150.dp))

        BoxWithConstraints(modifier = Modifier.weight(1f).height(18.dp)) {
            val totalMs = (windowEnd - windowStart).coerceAtLeast(1L).toFloat()
            val totalWidth = maxWidth
            hourMarkers.forEach { marker ->
                val fraction = ((marker - windowStart).toFloat() / totalMs).coerceIn(0f, 0.95f)
                val isNearNow = abs(marker - now) < 15 * 60_000L
                val isHour = (marker % (60 * 60_000L)) == 0L
                Text(
                    formatProgramTime(marker),
                    style = timeStyle.copy(
                        fontWeight = if (isHour) FontWeight.Medium else FontWeight.Normal
                    ),
                    color = when {
                        isNearNow -> Color.White.copy(alpha = 0.7f)
                        isHour -> Color.White.copy(alpha = 0.35f)
                        else -> Color.White.copy(alpha = 0.2f)
                    },
                    modifier = Modifier
                        .offset(x = totalWidth * fraction)
                )
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun GuideChannelRow(
    channel: IptvChannel,
    recentPrograms: List<IptvProgram>,
    nowProgram: IptvProgram?,
    upcomingPrograms: List<IptvProgram>,
    isFocused: Boolean,
    isPlaying: Boolean,
    isFavoriteChannel: Boolean,
    showDetailedTimeline: Boolean,
    windowStart: Long,
    windowEnd: Long,
    now: Long,
    nowRatio: Float,
    isMobile: Boolean = false,
    onClick: () -> Unit = {}
) {
    val context = LocalContext.current
    // Seamless rows: very subtle background, only visible on focus/playing
    val rowBg = when {
        isFocused -> Color.White.copy(alpha = 0.05f)
        isPlaying -> AccentGreen.copy(alpha = 0.025f)
        else -> Color.Transparent
    }
    val borderColor = when {
        isFocused -> Color.White.copy(alpha = 0.5f)
        isPlaying -> AccentGreen.copy(alpha = 0.2f)
        else -> Color.Transparent
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(if (isMobile) 44.dp else 56.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(rowBg)
            .then(
                if (isFocused || isPlaying) Modifier.border(
                    width = if (isFocused) 1.5.dp else 0.5.dp,
                    color = borderColor,
                    shape = RoundedCornerShape(4.dp)
                ) else Modifier
            )
            .clickable(onClick = onClick)
    ) {
        // Compact channel info
        Row(
            modifier = Modifier
                .width(if (isMobile) 120.dp else 150.dp)
                .fillMaxHeight()
                .padding(start = 8.dp, end = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.size(if (isMobile) 24.dp else 30.dp)) {
                if (!channel.logo.isNullOrBlank()) {
                    val logoRequest = remember(channel.logo) {
                        ImageRequest.Builder(context)
                            .data(channel.logo)
                            .size(64, 64)
                            .precision(Precision.INEXACT)
                            .crossfade(false)
                            .allowHardware(true)
                            .build()
                    }
                    AsyncImage(
                        model = logoRequest,
                        contentDescription = channel.name,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .size(if (isMobile) 24.dp else 30.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(Color.Black.copy(alpha = 0.3f))
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .size(if (isMobile) 24.dp else 30.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(Color.White.copy(alpha = 0.04f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.LiveTv, contentDescription = null, tint = Color.White.copy(alpha = 0.2f), modifier = Modifier.size(14.dp))
                    }
                }
                if (isFavoriteChannel) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = Color(0xFFF5C518).copy(alpha = 0.9f),
                        modifier = Modifier
                            .size(10.dp)
                            .align(Alignment.TopEnd)
                    )
                }
            }

            Spacer(modifier = Modifier.width(6.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = channel.name,
                    style = ArflixTypography.caption.copy(
                        fontSize = if (isMobile) 10.sp else 12.sp,
                        fontWeight = if (isFocused) FontWeight.Medium else FontWeight.Normal
                    ),
                    color = if (isFocused) Color.White else Color.White.copy(alpha = 0.75f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                if (isPlaying) {
                    Text(
                        text = stringResource(R.string.live).uppercase(),
                        style = ArflixTypography.caption.copy(fontSize = if (isMobile) 7.sp else 8.sp, fontWeight = FontWeight.Bold, letterSpacing = 0.5.sp),
                        color = AccentGreen,
                        maxLines = 1
                    )
                }
            }
        }

        if (showDetailedTimeline) {
            TimelineProgramLane(
                recentPrograms = recentPrograms,
                nowProgram = nowProgram,
                upcomingPrograms = upcomingPrograms,
                windowStart = windowStart,
                windowEnd = windowEnd,
                now = now,
                nowRatio = nowRatio,
                isRowFocused = isFocused,
                isRowPlaying = isPlaying,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(start = 2.dp, end = 4.dp, top = 4.dp, bottom = 4.dp)
            )
        } else {
            CompactGuideLane(
                nowProgram = nowProgram,
                nextProgram = upcomingPrograms.firstOrNull(),
                now = now,
                isRowFocused = isFocused,
                isRowPlaying = isPlaying,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(start = 2.dp, end = 4.dp, top = 8.dp, bottom = 8.dp)
            )
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun CompactGuideLane(
    nowProgram: IptvProgram?,
    nextProgram: IptvProgram?,
    now: Long,
    isRowFocused: Boolean,
    isRowPlaying: Boolean,
    modifier: Modifier = Modifier
) {
    val baseColor = when {
        isRowFocused -> Color.White.copy(alpha = 0.08f)
        isRowPlaying -> AccentGreen.copy(alpha = 0.05f)
        else -> Color.White.copy(alpha = 0.03f)
    }
    val accentColor = when {
        isRowPlaying -> AccentGreen.copy(alpha = 0.7f)
        isRowFocused -> Color.White.copy(alpha = 0.55f)
        else -> Color.White.copy(alpha = 0.3f)
    }
    val nowTitle = nowProgram?.title?.takeIf { it.isNotBlank() } ?: "Live program"
    val nextTitle = nextProgram?.title?.takeIf { it.isNotBlank() }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .background(baseColor)
    ) {
        if (nowProgram != null) {
            val duration = (nowProgram.endUtcMillis - nowProgram.startUtcMillis).coerceAtLeast(1L)
            val elapsed = (now - nowProgram.startUtcMillis).coerceIn(0, duration)
            val progress = (elapsed.toFloat() / duration.toFloat()).coerceIn(0f, 1f)
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(progress.coerceAtLeast(0.02f))
                    .background(accentColor.copy(alpha = if (isRowPlaying) 0.16f else 0.09f))
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp, vertical = 6.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = nowTitle,
                style = ArflixTypography.caption.copy(
                    fontSize = 11.sp,
                    fontWeight = if (isRowFocused) FontWeight.Medium else FontWeight.Normal
                ),
                color = Color.White.copy(alpha = 0.86f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            if (nextTitle != null) {
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "${stringResource(R.string.next)}: $nextTitle",
                    style = ArflixTypography.caption.copy(fontSize = 9.sp),
                    color = Color.White.copy(alpha = 0.45f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun TimelineProgramLane(
    recentPrograms: List<IptvProgram>,
    nowProgram: IptvProgram?,
    upcomingPrograms: List<IptvProgram>,
    windowStart: Long,
    windowEnd: Long,
    now: Long,
    nowRatio: Float,
    isRowFocused: Boolean,
    isRowPlaying: Boolean = false,
    modifier: Modifier = Modifier
) {
    val nowAccent = AccentGreen
    BoxWithConstraints(modifier = modifier.clip(RoundedCornerShape(3.dp))) {
        val segments = remember(recentPrograms, nowProgram, upcomingPrograms, windowStart, windowEnd) {
            buildTimelineSegments(recentPrograms, nowProgram, upcomingPrograms, windowStart, windowEnd)
        }
        if (segments.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White.copy(alpha = 0.02f))
                    .padding(horizontal = 8.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    "No EPG",
                    style = ArflixTypography.caption.copy(fontSize = 10.sp),
                    color = Color.White.copy(alpha = 0.15f)
                )
            }
        } else {
            segments.forEach { seg ->
                val segmentWidth = maxWidth * seg.widthRatio
                if (segmentWidth <= 0.dp) return@forEach

                val fillColor = when {
                    seg.isFiller -> Color.White.copy(alpha = 0.01f)
                    seg.isPast -> Color.White.copy(alpha = if (isRowFocused) 0.03f else 0.015f)
                    seg.isNow && isRowPlaying -> nowAccent.copy(alpha = 0.08f)
                    seg.isNow && isRowFocused -> Color.White.copy(alpha = 0.07f)
                    seg.isNow -> Color.White.copy(alpha = 0.04f)
                    isRowFocused -> Color.White.copy(alpha = 0.035f)
                    else -> Color.White.copy(alpha = 0.02f)
                }

                Box(
                    modifier = Modifier
                        .offset(x = maxWidth * seg.startRatio)
                        .width(segmentWidth)
                        .fillMaxHeight()
                        .padding(horizontal = 0.5.dp)
                        .clip(RoundedCornerShape(2.dp))
                        .background(fillColor)
                        .then(
                            if (seg.isNow && (isRowFocused || isRowPlaying)) Modifier.border(
                                width = 0.5.dp,
                                color = if (isRowFocused) Color.White.copy(alpha = 0.2f)
                                else nowAccent.copy(alpha = 0.15f),
                                shape = RoundedCornerShape(2.dp)
                            ) else Modifier
                        )
                ) {
                    if (seg.label.isNotBlank() && seg.widthRatio >= 0.08f) {
                        Text(
                            text = seg.label,
                            style = ArflixTypography.caption.copy(
                                fontSize = 10.sp,
                                fontWeight = if (seg.isNow) FontWeight.Medium else FontWeight.Normal,
                                lineHeight = 12.sp
                            ),
                            color = Color.White.copy(
                                alpha = when {
                                    seg.isFiller -> 0.2f
                                    seg.isPast -> 0.2f
                                    seg.isNow -> 0.85f
                                    isRowFocused -> 0.55f
                                    else -> 0.35f
                                }
                            ),
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .align(Alignment.CenterStart)
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                    if (seg.isNow && nowProgram != null) {
                        val progDuration = (nowProgram.endUtcMillis - nowProgram.startUtcMillis).coerceAtLeast(1L)
                        val progElapsed = (now - nowProgram.startUtcMillis).coerceIn(0, progDuration)
                        val progFraction = (progElapsed.toFloat() / progDuration.toFloat()).coerceIn(0f, 1f)
                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .fillMaxWidth()
                                .height(1.5.dp)
                                .background(Color.White.copy(alpha = 0.04f))
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(progFraction)
                                    .fillMaxHeight()
                                    .background(
                                        if (isRowPlaying) nowAccent.copy(alpha = 0.6f)
                                        else Color.White.copy(alpha = 0.3f)
                                    )
                            )
                        }
                    }
                }
            }
        }

        // Now-line indicator
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(nowRatio)
                .align(Alignment.CenterStart),
            contentAlignment = Alignment.CenterEnd
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.5.dp)
                    .background(
                        if (isRowFocused) nowAccent.copy(alpha = 0.7f)
                        else nowAccent.copy(alpha = 0.3f)
                    )
            )
        }
    }
}

private data class ProgramSegment(
    val label: String,
    val startRatio: Float,
    val endRatio: Float,
    val isNow: Boolean,
    val isFiller: Boolean = false,
    val isPast: Boolean = false
) {
    val widthRatio: Float get() = (endRatio - startRatio).coerceAtLeast(0f)
}

private fun buildTimelineSegments(
    recentPrograms: List<IptvProgram>,
    nowProgram: IptvProgram?,
    upcomingPrograms: List<IptvProgram>,
    windowStart: Long,
    windowEnd: Long
): List<ProgramSegment> {
    val totalWindow = (windowEnd - windowStart).coerceAtLeast(1L).toFloat()
    fun ratio(at: Long): Float {
        val clamped = (at.coerceIn(windowStart, windowEnd) - windowStart).toFloat()
        return (clamped / totalWindow).coerceIn(0f, 1f)
    }
    fun labelWithTime(program: IptvProgram, widthRatio: Float): String {
        return if (widthRatio >= 0.16f) {
            val time = formatProgramTime(program.startUtcMillis)
            "$time  ${program.title}"
        } else {
            program.title
        }
    }

    data class TimedProgram(val start: Long, val end: Long, val program: IptvProgram, val isNow: Boolean, val isPast: Boolean)
    val allPrograms = buildList {
        recentPrograms.forEach { add(TimedProgram(it.startUtcMillis, it.endUtcMillis, it, isNow = false, isPast = true)) }
        nowProgram?.let { add(TimedProgram(it.startUtcMillis, it.endUtcMillis, it, isNow = true, isPast = false)) }
        upcomingPrograms.forEach { add(TimedProgram(it.startUtcMillis, it.endUtcMillis, it, isNow = false, isPast = false)) }
    }
        .distinctBy { timed -> Triple(timed.start, timed.end, timed.program.title) }
        .sortedBy { it.start }

    val items = mutableListOf<ProgramSegment>()
    var cursor = windowStart
    for (tp in allPrograms) {
        val segStart = maxOf(tp.start.coerceIn(windowStart, windowEnd), cursor)
        val segEnd = tp.end.coerceIn(windowStart, windowEnd)
        if (segEnd <= segStart) continue
        if (segStart > cursor) {
            items += ProgramSegment(
                label = "",
                startRatio = ratio(cursor),
                endRatio = ratio(segStart),
                isNow = false,
                isFiller = true
            )
        }
        val widthRatio = ((segEnd - segStart).toFloat() / totalWindow).coerceIn(0f, 1f)
        if (widthRatio > 0.006f) {
            items += ProgramSegment(
                label = labelWithTime(tp.program, widthRatio),
                startRatio = ratio(segStart),
                endRatio = ratio(segEnd),
                isNow = tp.isNow,
                isPast = tp.isPast
            )
        }
        cursor = segEnd.coerceAtLeast(cursor)
    }
    if (cursor < windowEnd) {
        items += ProgramSegment(
            label = "",
            startRatio = ratio(cursor),
            endRatio = ratio(windowEnd),
            isNow = false,
            isFiller = true
        )
    }
    return mergeAdjacentTimelineSegments(items)
}

private fun mergeAdjacentTimelineSegments(items: List<ProgramSegment>): List<ProgramSegment> {
    if (items.isEmpty()) return items
    val merged = mutableListOf<ProgramSegment>()
    items.forEach { seg ->
        val last = merged.lastOrNull()
        if (
            last != null &&
            last.label.equals(seg.label, ignoreCase = true) &&
            last.isNow == seg.isNow &&
            last.isFiller == seg.isFiller &&
            last.isPast == seg.isPast
        ) {
            merged[merged.lastIndex] = last.copy(endRatio = seg.endRatio)
        } else {
            merged += seg
        }
    }
    return merged
}

@Composable
private fun FocusableMenuItem(label: String, icon: ImageVector, iconTint: Color = Color.White.copy(alpha = 0.6f), onClick: () -> Unit) {
    var focused by remember { mutableStateOf(false) }
    DropdownMenuItem(
        text = { Text(label, style = ArflixTypography.caption.copy(fontSize = 12.sp, fontWeight = if (focused) FontWeight.SemiBold else FontWeight.Normal), color = if (focused) Color.White else Color.White.copy(alpha = 0.8f)) },
        leadingIcon = { Icon(icon, null, tint = if (focused) iconTint else iconTint.copy(alpha = 0.5f), modifier = Modifier.size(16.dp)) },
        onClick = onClick,
        modifier = Modifier
            .height(40.dp)
            .onFocusChanged { focused = it.isFocused }
            .then(if (focused) Modifier.border(2.dp, Color.White.copy(alpha = 0.8f), RoundedCornerShape(8.dp)).background(Color.White.copy(alpha = 0.1f), RoundedCornerShape(8.dp)) else Modifier)
    )
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
            Text(stringResource(R.string.iptv_not_configured), style = ArflixTypography.sectionTitle, color = TextPrimary)
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                "Open Settings and add your M3U URL.",
                style = ArflixTypography.body,
                color = TextSecondary
            )
        }
    }
}

private val programTimeFormatter = DateTimeFormatter.ofPattern("h:mm a")

private fun formatProgramTime(utcMillis: Long): String {
    return programTimeFormatter.format(Instant.ofEpochMilli(utcMillis).atZone(ZoneId.systemDefault()))
}
