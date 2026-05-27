@file:Suppress("UnsafeOptInUsageError")

package com.arflix.tv.ui.screens.tv.live

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.ActivityInfo
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.datasource.okhttp.OkHttpDataSource
import androidx.media3.exoplayer.DefaultLoadControl
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import com.arflix.tv.data.model.IptvChannel
import com.arflix.tv.data.model.IptvProgram
import com.arflix.tv.data.model.Profile
import com.arflix.tv.ui.screens.tv.TvUiState
import com.arflix.tv.ui.screens.tv.TvViewModel
import com.arflix.tv.network.OkHttpProvider
import com.arflix.tv.ui.components.AppTopBar
import com.arflix.tv.ui.components.AppTopBarHeight
import com.arflix.tv.ui.components.SidebarItem
import com.arflix.tv.ui.components.topBarFocusedItem
import com.arflix.tv.ui.components.topBarMaxIndex
import com.arflix.tv.ui.components.topBarSelectedIndex
import com.arflix.tv.util.LocalDeviceType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

private enum class LiveTvFocusZone {
    TOPBAR,
    CATEGORY_LIST,
    CHANNEL_LIST,
    EPG,
}

private fun chooseStartupChannelId(
    filteredChannels: List<EnrichedChannel>,
    explicitInitialChannelId: String?,
    sessionLastChannelId: String,
    hasOpenedBefore: Boolean,
    favoriteChannelIds: List<String>,
    isFullyEnriched: Boolean,
): String? {
    explicitInitialChannelId
        ?.takeIf { id -> filteredChannels.any { it.id == id } }
        ?.let { return it }
    if (explicitInitialChannelId != null && !isFullyEnriched) return null

    favoriteChannelIds
        .firstOrNull { id -> filteredChannels.any { it.id == id } }
        ?.let { return it }
    if (favoriteChannelIds.isNotEmpty() && !isFullyEnriched) return null

    if (hasOpenedBefore) {
        sessionLastChannelId
            .takeIf { id -> id.isNotBlank() && filteredChannels.any { it.id == id } }
            ?.let { return it }

        if (sessionLastChannelId.isNotBlank() && !isFullyEnriched) return null
    }

    return filteredChannels.first().id
}

/**
 * Live TV screen — Arvio spec §1. Three focus regions: Sidebar ↔ MiniPlayer ↔ EPG.
 * Preserves every IPTV feature from the legacy [com.arflix.tv.ui.screens.tv.TvScreen]
 * (favorites, hidden groups, EPG refresh, cloud sync) — only the UI shell is new.
 */
@Composable
fun LiveTvScreen(
    viewModel: TvViewModel = hiltViewModel(),
    currentProfile: Profile? = null,
    initialChannelId: String? = null,
    initialStreamUrl: String? = null,
    onFullscreenChanged: (Boolean) -> Unit = {},
    onNavigateToHome: () -> Unit = {},
    onNavigateToSearch: () -> Unit = {},
    onNavigateToWatchlist: () -> Unit = {},
    onNavigateToSettings: () -> Unit = {},
    onSwitchProfile: () -> Unit = {},
    onBack: () -> Unit = {},
) {
    // Lifecycle-aware collection so the screen stops draining state updates
    // the instant the user backs out — matters on a long-running IPTV flow
    // where the ViewModel pushes EPG refreshes every few seconds.
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val currentUiState by rememberUpdatedState(state)
    val context = LocalContext.current
    val activity = remember(context) { context.findActivity() }
    val configuration = LocalConfiguration.current
    val deviceType = LocalDeviceType.current
    val isTouchDevice = deviceType.isTouchDevice()
    val useTouchRail = isTouchDevice && configuration.smallestScreenWidthDp < 600
    val compactTouchLayout = isTouchDevice && configuration.screenWidthDp < 900
    val showTopBar = !isTouchDevice
    val contentTopPadding = if (showTopBar) AppTopBarHeight else 0.dp
    val guideClockMillis by produceState(initialValue = System.currentTimeMillis()) {
        while (true) {
            delay(30_000L)
            value = System.currentTimeMillis()
        }
    }
    var selectedCategoryId by rememberSaveable { mutableStateOf("all") }
    val recents = remember { mutableStateOf<LinkedHashSet<String>>(LinkedHashSet()) }
    val favSet = remember(state.snapshot.favoriteChannels) { state.snapshot.favoriteChannels.toSet() }
    val hiddenGroupSet = remember(state.snapshot.hiddenGroups) { state.snapshot.hiddenGroups.toSet() }
    var seededRecentSessionChannel by rememberSaveable { mutableStateOf(false) }
    LaunchedEffect(state.tvSession.lastChannelId) {
        if (!seededRecentSessionChannel && state.tvSession.lastChannelId.isNotBlank()) {
            recents.value = LinkedHashSet<String>().apply { add(state.tvSession.lastChannelId) }
            seededRecentSessionChannel = true
        }
    }

    // Enrichment runs on a background dispatcher and is published through state
    // — avoids blocking recomposition for 10k+ playlists. Result is cached in
    // the ViewModel so re-visits to the TV page are instant (no 2-3s stall).
    val enrichedState = remember {
        mutableStateOf<EnrichedChannels>(
            (viewModel.cachedEnrichedChannels as? EnrichedChannels) ?: EnrichedChannels.Empty
        )
    }
    LaunchedEffect(state.snapshot.channels) {
        val snapshot = state.snapshot.channels
        if (snapshot.isEmpty()) {
            enrichedState.value = EnrichedChannels.Empty
            return@LaunchedEffect
        }
        // Skip re-enrichment if we already have a cache for the same playlist.
        val signature = "${snapshot.size}:${snapshot.firstOrNull()?.id}:${snapshot.lastOrNull()?.id}"
        if (viewModel.cachedChannelsSignature == signature &&
            viewModel.cachedEnrichedChannels is EnrichedChannels
        ) {
            enrichedState.value = viewModel.cachedEnrichedChannels as EnrichedChannels
            return@LaunchedEffect
        }

        val initialChannels = withContext(Dispatchers.Default) {
            buildInitialCategoryChannels(
                channels = snapshot,
                categoryId = selectedCategoryId,
                favorites = favSet,
                recents = recents.value,
                limit = snapshot.size,
            )
        }
        val initialIndex = withContext(Dispatchers.Default) { buildCategoryIndex(initialChannels) }
        val initialTree = withContext(Dispatchers.Default) {
            buildCategoryTree(
                channels = initialChannels,
                favoritesCount = favSet.count { it in initialIndex.byId },
                recentCount = recents.value.count { it in initialIndex.byId },
                hiddenGroups = hiddenGroupSet,
                groupOrder = state.snapshot.groupOrder,
            )
        }
        enrichedState.value = EnrichedChannels(
            all = initialChannels,
            tree = initialTree,
            index = initialIndex,
        )
        val enriched = withContext(Dispatchers.Default) {
            snapshot.mapIndexed { idx, ch -> ch.enrich(100 + idx) }
        }
        val index = withContext(Dispatchers.Default) { buildCategoryIndex(enriched) }
        val tree = withContext(Dispatchers.Default) {
            buildCategoryTree(
                channels = enriched,
                favoritesCount = favSet.count { it in index.byId },
                recentCount = recents.value.count { it in index.byId },
                hiddenGroups = hiddenGroupSet,
                groupOrder = state.snapshot.groupOrder,
            )
        }
        val value = EnrichedChannels(all = enriched, tree = tree, index = index)
        enrichedState.value = value
        viewModel.cachedEnrichedChannels = value
        viewModel.cachedChannelsSignature = signature
    }
    // Re-evaluate only dynamic counts when favorites/recents change.
    LaunchedEffect(favSet, hiddenGroupSet, state.snapshot.groupOrder, recents.value, enrichedState.value.all) {
        val current = enrichedState.value
        if (current === EnrichedChannels.Empty) return@LaunchedEffect
        val byId = current.index.byId
        val tree = withContext(Dispatchers.Default) {
            buildCategoryTree(
                channels = current.all,
                favoritesCount = favSet.count { it in byId },
                recentCount = recents.value.count { it in byId },
                hiddenGroups = hiddenGroupSet,
                groupOrder = state.snapshot.groupOrder,
            )
        }
        enrichedState.value = current.copy(tree = tree)
    }
    LaunchedEffect(hiddenGroupSet, selectedCategoryId, enrichedState.value.tree) {
        if (selectedCategoryId != "all" && enrichedState.value.tree.byId(selectedCategoryId) == null) {
            selectedCategoryId = "all"
        }
    }

    // Selected category (persist across nav). Defaults to "all".
    val hasProfile = currentProfile != null
    val maxTopBarIndex = topBarMaxIndex(hasProfile)
    var focusZone by rememberSaveable { mutableStateOf(LiveTvFocusZone.CATEGORY_LIST) }
    var topBarFocusIndex by rememberSaveable {
        mutableIntStateOf(topBarSelectedIndex(SidebarItem.TV, hasProfile).coerceIn(0, maxTopBarIndex))
    }

    // Category switches are served from prebuilt buckets. Favorites and
    // recents remain ordered dynamic lists, but they are simple id lookups.
    val filteredChannelsState = remember { mutableStateOf<List<EnrichedChannel>>(emptyList()) }
    val recentsFilterKey = if (selectedCategoryId == "recent") recents.value else Unit
    LaunchedEffect(enrichedState.value.index, selectedCategoryId, favSet, recentsFilterKey) {
        val result = withContext(Dispatchers.Default) {
            enrichedState.value.index.channelsFor(
                categoryId = selectedCategoryId,
                favorites = state.snapshot.favoriteChannels,
                recents = recents.value,
            )
        }
        filteredChannelsState.value = result
    }
    val filteredChannels = filteredChannelsState.value

    // Playing channel — default to the one we were navigated to, else the first
    // channel of the first non-empty category.
    var playingChannelId by rememberSaveable { mutableStateOf<String?>(initialChannelId) }
    var focusedChannelId by rememberSaveable { mutableStateOf<String?>(initialChannelId) }
    var playingCatchupProgram by remember { mutableStateOf<IptvProgram?>(null) }
    val playingChannel = remember(playingChannelId, enrichedState.value, filteredChannels) {
        playingChannelId?.let { enrichedState.value.index.byId[it] }
            ?: filteredChannels.firstOrNull { it.id == playingChannelId }
    }
    val currentNowNext = remember(playingChannelId, playingCatchupProgram, state.snapshot.nowNext) {
        val live = playingChannelId?.let { state.snapshot.nowNext[it] }
        val catchup = playingCatchupProgram
        if (catchup != null) {
            com.arflix.tv.data.model.IptvNowNext(
                now = catchup,
                next = null,
                later = null,
                upcoming = emptyList(),
                recent = emptyList()
            )
        } else {
            live
        }
    }

    val epgPrefetchIds = remember(filteredChannels, selectedCategoryId, playingChannelId) {
        val maxPrefetch = if (selectedCategoryId == "all") 96 else 180
        buildList<String> {
            playingChannelId
                ?.takeIf { current -> filteredChannels.any { channel -> channel.id == current } }
                ?.let { add(it) }
            filteredChannels
                .asSequence()
                .map { it.id }
                .filterNot { it == playingChannelId }
                .take((maxPrefetch - size).coerceAtLeast(0))
                .forEach { add(it) }
        }
    }
    LaunchedEffect(selectedCategoryId, epgPrefetchIds, playingChannelId) {
        if (epgPrefetchIds.isNotEmpty()) {
            viewModel.prefetchVisibleCategoryEpg(
                channelIds = epgPrefetchIds,
                selectedChannelId = playingChannelId,
                eagerLimit = if (selectedCategoryId == "all") 32 else 64,
                backgroundLimit = if (selectedCategoryId == "all") 120 else 240,
            )
        }
    }

    // Pick the startup channel only after saved IPTV preferences/session have
    // loaded. Favorites win over a stale recent channel, then we fall back to
    // the persisted recent channel, then the first filtered entry.
    LaunchedEffect(filteredChannels, playingChannelId, initialChannelId, state.tvSession, state.snapshot.favoriteChannels, enrichedState.value.all.size, state.snapshot.channels.size, state.iptvPreferencesLoaded, state.tvSessionLoaded) {
        val startupStateReady = state.iptvPreferencesLoaded && state.tvSessionLoaded
        if (playingChannelId == null && filteredChannels.isNotEmpty() && (initialChannelId != null || startupStateReady)) {
            playingChannelId = chooseStartupChannelId(
                filteredChannels = filteredChannels,
                explicitInitialChannelId = initialChannelId,
                sessionLastChannelId = state.tvSession.lastChannelId,
                hasOpenedBefore = state.tvSession.lastOpenedAt > 0L,
                favoriteChannelIds = state.snapshot.favoriteChannels,
                isFullyEnriched = enrichedState.value.all.size >= state.snapshot.channels.size,
            )
        }
        if (focusedChannelId == null || filteredChannels.none { it.id == focusedChannelId }) {
            focusedChannelId = playingChannelId?.takeIf { id -> filteredChannels.any { it.id == id } }
                ?: filteredChannels.firstOrNull()?.id
        }
    }

    val sidebarExpanded = !useTouchRail
    var searchOpen by rememberSaveable { mutableStateOf(false) }
    var focusSelectedChannelSignal by remember { mutableIntStateOf(0) }
    var focusEpgSignal by remember { mutableIntStateOf(0) }
    var focusSearchCategorySignal by remember { mutableIntStateOf(1) }
    val rememberedChannelByCategory = remember { mutableMapOf<String, String>() }
    // Full-screen playback mode — pressing OK on an EPG row expands the
    // mini-player to cover the whole screen. Back collapses back to the grid.
    var isFullScreen by rememberSaveable { mutableStateOf(initialStreamUrl != null) }
    LaunchedEffect(isFullScreen) {
        onFullscreenChanged(isFullScreen)
    }
    DisposableEffect(Unit) {
        onDispose { onFullscreenChanged(false) }
    }
    // Focus requesters for the three regions.
    val sidebarFocus = remember { FocusRequester() }
    val epgFocus = remember { FocusRequester() }
    val fsFocus = remember { FocusRequester() }

    // Monotonic counter bumped on every DPAD key while in fullscreen —
    // the HUD observes this to re-show and reset its auto-hide timer.
    var hudPokeSignal by remember { mutableStateOf(0) }

    DisposableEffect(activity, isFullScreen, isTouchDevice) {
        if (!isTouchDevice || !isFullScreen) {
            return@DisposableEffect onDispose { }
        }

        val previousOrientation = activity?.requestedOrientation
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
        val window = activity?.window
        if (window != null) {
            val controller = androidx.core.view.WindowInsetsControllerCompat(window, window.decorView)
            controller.systemBarsBehavior =
                androidx.core.view.WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            controller.hide(androidx.core.view.WindowInsetsCompat.Type.systemBars())
        }

        onDispose {
            if (previousOrientation != null) {
                activity.requestedOrientation = previousOrientation
            }
            if (window != null) {
                androidx.core.view.WindowInsetsControllerCompat(window, window.decorView)
                    .show(androidx.core.view.WindowInsetsCompat.Type.systemBars())
            }
        }
    }

    // Prev/next zapping across the full enriched list (not the filtered
    // category) per user spec. Wraps around.
    fun zap(delta: Int) {
        val all = enrichedState.value.all
        if (all.isEmpty()) return
        val currentIdx = all.indexOfFirst { it.id == playingChannelId }
        val start = if (currentIdx >= 0) currentIdx else 0
        val size = all.size
        val nextIdx = ((start + delta) % size + size) % size
        playingChannelId = all[nextIdx].id
        focusedChannelId = all[nextIdx].id
        rememberedChannelByCategory[selectedCategoryId] = all[nextIdx].id
        playingCatchupProgram = null
    }

    fun focusPlaylistSearch() {
        focusZone = LiveTvFocusZone.CATEGORY_LIST
        focusSearchCategorySignal += 1
        runCatching { sidebarFocus.requestFocus() }
    }

    fun focusChannelList(channelId: String? = focusedChannelId ?: playingChannelId) {
        channelId?.let {
            focusedChannelId = it
            rememberedChannelByCategory[selectedCategoryId] = it
        }
        focusZone = LiveTvFocusZone.CHANNEL_LIST
        focusSelectedChannelSignal += 1
        runCatching { epgFocus.requestFocus() }
    }

    fun focusEpg(channelId: String) {
        focusedChannelId = channelId
        rememberedChannelByCategory[selectedCategoryId] = channelId
        focusZone = LiveTvFocusZone.EPG
        focusEpgSignal += 1
        runCatching { epgFocus.requestFocus() }
    }

    fun exitFullScreenPlayback() {
        isFullScreen = false
        focusChannelList(playingChannelId ?: focusedChannelId)
    }

    fun selectChannel(channel: EnrichedChannel) {
        focusedChannelId = channel.id
        rememberedChannelByCategory[selectedCategoryId] = channel.id
        if (channel.id == playingChannelId && !isFullScreen) {
            // Second tap on the already-playing channel → fullscreen
            playingCatchupProgram = null
            isFullScreen = true
            hudPokeSignal++
        } else {
            // First tap or different channel → tune in mini-player
            playingChannelId = channel.id
            playingCatchupProgram = null
        }
    }

    fun playProgramInMini(channel: EnrichedChannel, program: IptvProgram?) {
        focusedChannelId = channel.id
        rememberedChannelByCategory[selectedCategoryId] = channel.id
        playingChannelId = channel.id
        playingCatchupProgram = program
        focusChannelList(channel.id)
    }

    // ExoPlayer lifecycle — mirrors the legacy screen's setup verbatim so live
    // IPTV behaviour (buffer, retries, chunkless HLS) stays identical.
    val iptvHttpClient = remember {
        OkHttpClient.Builder()
            .connectionPool(ConnectionPool(5, 5, TimeUnit.MINUTES))
            .followRedirects(true)
            .followSslRedirects(true)
            .retryOnConnectionFailure(true)
            .dns(OkHttpProvider.dns)
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(300, TimeUnit.SECONDS)
            .build()
    }
    val mediaSourceFactory = remember(iptvHttpClient) {
        DefaultMediaSourceFactory(context)
            .setDataSourceFactory(
                OkHttpDataSource.Factory(iptvHttpClient)
                    .setUserAgent("ARVIO/1.2.0 (Android TV)")
            )
    }
    val exoPlayer = remember {
        val loadControl = DefaultLoadControl.Builder()
            .setBufferDurationsMs(4_000, 20_000, 750, 1_500)
            .setTargetBufferBytes(24 * 1024 * 1024)
            .setPrioritizeTimeOverSizeThresholds(true)
            .setBackBuffer(2_000, false)
            .build()
        ExoPlayer.Builder(context)
            .setMediaSourceFactory(mediaSourceFactory)
            .setLoadControl(loadControl)
            .build().apply {
                playWhenReady = true
                videoScalingMode = C.VIDEO_SCALING_MODE_SCALE_TO_FIT
            }
    }

    DisposableEffect(Unit) { onDispose { exoPlayer.release() } }

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val obs = LifecycleEventObserver { _, ev ->
            when (ev) {
                Lifecycle.Event.ON_PAUSE -> exoPlayer.pause()
                Lifecycle.Event.ON_RESUME -> {
                    if (playingChannelId != null) exoPlayer.play()
                    if (currentUiState.isConfigured &&
                        currentUiState.snapshot.channels.isNotEmpty() &&
                        viewModel.iptvRepository.cachedEpgAgeMs() > 90_000L
                    ) {
                        viewModel.refresh(force = false, showLoading = false, forceEpg = true)
                    }
                }
                else -> {}
            }
        }
        lifecycleOwner.lifecycle.addObserver(obs)
        onDispose { lifecycleOwner.lifecycle.removeObserver(obs) }
    }

    // When the selected channel changes, swap media item.
    val currentStreamUrl = remember(playingChannel, playingCatchupProgram) {
        val ch = playingChannel ?: return@remember initialStreamUrl
        val pr = playingCatchupProgram
        if (pr != null) {
            viewModel.iptvRepository.getCatchupUrl(ch.source, pr)
        } else {
            ch.streamUrl
        }
    }
    val openFullScreenPlayer = remember(playingChannelId, currentStreamUrl) {
        {
            if (playingChannelId != null || currentStreamUrl != null) {
                isFullScreen = true
                hudPokeSignal++
            }
        }
    }
    LaunchedEffect(currentStreamUrl, playingCatchupProgram) {
        val stream = currentStreamUrl ?: return@LaunchedEffect
        delay(90L)
        exoPlayer.setMediaItem(
            MediaItem.Builder()
                .setUri(stream)
                .apply {
                    if (playingCatchupProgram == null) {
                        setLiveConfiguration(
                            MediaItem.LiveConfiguration.Builder()
                                .setMinPlaybackSpeed(1.0f).setMaxPlaybackSpeed(1.0f)
                                .setTargetOffsetMs(4_000).build()
                        )
                    }
                }
                .build()
        )
        exoPlayer.prepare()
        exoPlayer.play()
        // Persist "recent" as soon as playback starts.
        playingChannelId?.let { id ->
            val set = LinkedHashSet(recents.value)
            set.remove(id); set.add(id)
            while (set.size > 40) set.remove(set.first())
            recents.value = set
            viewModel.rememberTvSession(
                lastChannelId = id,
                lastGroupName = selectedCategoryId,
                lastFocusedZone = "GUIDE",
                markOpened = true,
            )
        }
    }

    // Default IPTV entry is the playlist/category rail, focused on Search.
    LaunchedEffect(enrichedState.value !== EnrichedChannels.Empty) {
        if (!isTouchDevice && enrichedState.value !== EnrichedChannels.Empty) {
            focusPlaylistSearch()
        }
    }

    BackHandler(enabled = searchOpen) { searchOpen = false }
    BackHandler(enabled = !searchOpen && isFullScreen) {
        exitFullScreenPlayback()
    }
    BackHandler(enabled = !searchOpen && !isFullScreen) {
        when (focusZone) {
            LiveTvFocusZone.EPG -> focusChannelList(focusedChannelId ?: playingChannelId)
            LiveTvFocusZone.CHANNEL_LIST -> {
                if (isTouchDevice) onBack()
                else focusPlaylistSearch()
            }
            LiveTvFocusZone.CATEGORY_LIST -> {
                if (isTouchDevice) {
                    onBack()
                }
            }
            LiveTvFocusZone.TOPBAR -> onBack()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LiveColors.Bg)
            .then(
                if (!isTouchDevice) {
                    Modifier.onPreviewKeyEvent { event ->
                        if (searchOpen || isFullScreen || event.type != KeyEventType.KeyDown) {
                            return@onPreviewKeyEvent false
                        }
                        when (focusZone) {
                            LiveTvFocusZone.TOPBAR -> {
                                when (event.key) {
                                    Key.DirectionLeft -> {
                                        if (topBarFocusIndex > 0) {
                                            topBarFocusIndex = (topBarFocusIndex - 1).coerceIn(0, maxTopBarIndex)
                                        }
                                        true
                                    }
                                    Key.DirectionRight -> {
                                        if (topBarFocusIndex < maxTopBarIndex) {
                                            topBarFocusIndex = (topBarFocusIndex + 1).coerceIn(0, maxTopBarIndex)
                                        }
                                        true
                                    }
                                    Key.DirectionDown -> {
                                        focusPlaylistSearch()
                                        true
                                    }
                                    Key.DirectionCenter, Key.Enter -> {
                                        if (hasProfile && topBarFocusIndex == 0) {
                                            onSwitchProfile()
                                        } else {
                                            when (topBarFocusedItem(topBarFocusIndex, hasProfile)) {
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
                                    else -> false
                                }
                            }
                            LiveTvFocusZone.CATEGORY_LIST -> false
                            LiveTvFocusZone.CHANNEL_LIST -> false
                            LiveTvFocusZone.EPG -> false
                        }
                    }
                } else {
                    Modifier
                }
            )
    ) {
        // Content area starts below the translucent top bar so it doesn't get
        // overwritten.
        if (isFullScreen) {
            // Full-screen playback only — no grid rendered so the single
            // PlayerView owns ExoPlayer.
        } else if (!state.isConfigured && state.snapshot.channels.isEmpty()) {
            EmptyStatePane(
                message = "No IPTV playlist configured.",
                actionLabel = "Open settings",
                onAction = onNavigateToSettings,
            )
        } else {
            // Content starts right under the pill row — 52 dp puts the first
            // row/search field 4 dp below the pills. The remaining top-bar
            // gradient tail is transparent enough to vanish over our near-
            // black Bg so the two regions read as one surface.
            // Content sits under the top bar (82dp tall with a dark-to-
            // transparent gradient). Starting at 0dp lets the grid/sidebar
            // background bleed up into the transparent tail of the gradient
            // so the two regions read as one surface instead of a hovering
            // chip row. The content itself gets an internal top padding so
            // nothing important renders under the opaque chips.
            if (useTouchRail) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = contentTopPadding),
                ) {
                    MiniPlayerRow(
                        exoPlayer = exoPlayer,
                        channel = playingChannel,
                        clockTickMillis = guideClockMillis,
                        nowNext = currentNowNext,
                        onFavoriteToggle = { viewModel.toggleFavoriteChannel(it) },
                        favoriteSet = favSet,
                        onFullscreenClick = openFullScreenPlayer,
                        compact = true,
                        modifier = Modifier.fillMaxWidth(),
                    )
                    TouchCategoryRail(
                        tree = enrichedState.value.tree,
                        selectedId = selectedCategoryId,
                        onSelect = { id -> selectedCategoryId = id },
                        onOpenSearch = { searchOpen = true },
                        modifier = Modifier.fillMaxWidth(),
                    )
                    EpgGrid(
                        channels = filteredChannels,
                        clockTickMillis = guideClockMillis,
                        nowNext = state.snapshot.nowNext,
                        selectedChannelId = focusedChannelId ?: playingChannelId,
                        focusSelectedChannelSignal = focusSelectedChannelSignal,
                        focusEpgSignal = focusEpgSignal,
                        focusMode = if (focusZone == LiveTvFocusZone.EPG) {
                            EpgGridFocusMode.Epg
                        } else {
                            EpgGridFocusMode.ChannelList
                        },
                        compact = true,
                        gridFocused = focusZone == LiveTvFocusZone.EPG,
                        onChannelSelect = { channel, _ ->
                            focusZone = LiveTvFocusZone.CHANNEL_LIST
                            selectChannel(channel)
                        },
                        onProgramSelect = { channel, program -> playProgramInMini(channel, program) },
                        onChannelFocused = { channel ->
                            focusedChannelId = channel.id
                            rememberedChannelByCategory[selectedCategoryId] = channel.id
                        },
                        onChannelFavoriteToggle = { id -> viewModel.toggleFavoriteChannel(id) },
                        favorites = favSet,
                        onMoveLeftFromChannels = { focusPlaylistSearch() },
                        onEnterEpg = { channel -> focusEpg(channel.id) },
                        onExitEpg = { channel -> focusChannelList(channel?.id ?: focusedChannelId ?: playingChannelId) },
                        modifier = Modifier.fillMaxSize(),
                    )
                }
            } else Row(
                modifier = Modifier.fillMaxSize(),
            ) {
                CategorySidebar(
                    tree = enrichedState.value.tree,
                    selectedId = selectedCategoryId,
                    expanded = sidebarExpanded,
                    onSelect = { id -> selectedCategoryId = id },
                    onOpenSearch = { searchOpen = true },
                    onHideCategory = { groupName ->
                        selectedCategoryId = "all"
                        viewModel.toggleHiddenGroup(groupName)
                    },
                    onUnhideCategory = { groupName ->
                        viewModel.toggleHiddenGroup(groupName)
                    },
                    onMoveCategoryUp = { groupName ->
                        viewModel.moveGroupUp(groupName)
                    },
                    onMoveCategoryToTop = { groupName ->
                        viewModel.moveGroupToTop(groupName)
                    },
                    onMoveCategoryDown = { groupName ->
                        viewModel.moveGroupDown(groupName)
                    },
                    onFocusEnter = {
                        if (focusZone != LiveTvFocusZone.TOPBAR) {
                            focusZone = LiveTvFocusZone.CATEGORY_LIST
                        }
                    },
                    onMoveRight = {
                        val remembered = rememberedChannelByCategory[selectedCategoryId]
                            ?.takeIf { id -> filteredChannels.any { it.id == id } }
                        val target = remembered
                            ?: focusedChannelId?.takeIf { id -> filteredChannels.any { it.id == id } }
                            ?: playingChannelId?.takeIf { id -> filteredChannels.any { it.id == id } }
                            ?: filteredChannels.firstOrNull()?.id
                        focusChannelList(target)
                    },
                    onMoveUpFromSearch = {
                        topBarFocusIndex = topBarSelectedIndex(SidebarItem.TV, hasProfile)
                            .coerceIn(0, maxTopBarIndex)
                        focusZone = LiveTvFocusZone.TOPBAR
                    },
                    focusSearchSignal = focusSearchCategorySignal,
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(top = contentTopPadding)
                        .then(if (!isTouchDevice) Modifier.focusRequester(sidebarFocus) else Modifier),
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = contentTopPadding),
                ) {
                    MiniPlayerRow(
                        exoPlayer = exoPlayer,
                        channel = playingChannel,
                        clockTickMillis = guideClockMillis,
                        nowNext = currentNowNext,
                        onFavoriteToggle = { viewModel.toggleFavoriteChannel(it) },
                        favoriteSet = favSet,
                        onFullscreenClick = openFullScreenPlayer,
                        compact = compactTouchLayout,
                        modifier = Modifier.fillMaxWidth(),
                    )
                    EpgGrid(
                        channels = filteredChannels,
                        clockTickMillis = guideClockMillis,
                        nowNext = state.snapshot.nowNext,
                        selectedChannelId = focusedChannelId ?: playingChannelId,
                        focusSelectedChannelSignal = focusSelectedChannelSignal,
                        focusEpgSignal = focusEpgSignal,
                        focusMode = if (focusZone == LiveTvFocusZone.EPG) {
                            EpgGridFocusMode.Epg
                        } else {
                            EpgGridFocusMode.ChannelList
                        },
                        compact = compactTouchLayout,
                        gridFocused = focusZone == LiveTvFocusZone.CHANNEL_LIST || focusZone == LiveTvFocusZone.EPG,
                        onChannelSelect = { channel, _ -> selectChannel(channel) },
                        onProgramSelect = { channel, program -> playProgramInMini(channel, program) },
                        onChannelFocused = { channel ->
                            focusedChannelId = channel.id
                            rememberedChannelByCategory[selectedCategoryId] = channel.id
                        },
                        onChannelFavoriteToggle = { id -> viewModel.toggleFavoriteChannel(id) },
                        favorites = favSet,
                        onMoveLeftFromChannels = { focusPlaylistSearch() },
                        onEnterEpg = { channel -> focusEpg(channel.id) },
                        onExitEpg = { channel -> focusChannelList(channel?.id ?: focusedChannelId ?: playingChannelId) },
                        modifier = Modifier
                            .fillMaxSize()
                            .onFocusChanged {
                                if (it.hasFocus && focusZone == LiveTvFocusZone.CATEGORY_LIST) {
                                    focusZone = LiveTvFocusZone.CHANNEL_LIST
                                }
                            }
                            .then(if (!isTouchDevice) Modifier.focusRequester(epgFocus) else Modifier),
                    )
                }
            }
        }

        // Full-screen playback: same ExoPlayer, covers the entire screen.
        //
        // The overlay animates a scale+alpha transition so it looks like the
        // mini-player is growing into fullscreen. The transform pivot is
        // roughly the mini-player's center (sidebar ≈ 20% of width, mini-
        // player sits just below the 52dp top bar), which keeps the grow
        // anchored visually to where the user tapped instead of from screen
        // center. fsProgress stays mounted until it reaches 0, so the
        // reverse animation also plays on Back.
        val fsProgress by animateFloatAsState(
            targetValue = if (isFullScreen) 1f else 0f,
            animationSpec = tween(durationMillis = 280, easing = FastOutSlowInEasing),
            label = "tv-fullscreen-progress",
        )
        if (fsProgress > 0f && playingChannel != null) {
            val scale = 0.35f + 0.65f * fsProgress
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        transformOrigin = TransformOrigin(
                            pivotFractionX = 0.22f,
                            pivotFractionY = 0.18f,
                        )
                        scaleX = scale
                        scaleY = scale
                        alpha = fsProgress
                    }
                    .background(Color.Black)
                    .focusRequester(fsFocus)
                    .focusable()
                    .onPreviewKeyEvent { ev ->
                        if (!isFullScreen || ev.type != KeyEventType.KeyDown) return@onPreviewKeyEvent false
                        when (ev.key) {
                            Key.Back, Key.Escape -> { exitFullScreenPlayback(); true }
                            Key.DirectionUp -> { zap(+1); hudPokeSignal++; true }
                            Key.DirectionDown -> { zap(-1); hudPokeSignal++; true }
                            Key.DirectionCenter, Key.Enter -> { hudPokeSignal++; true }
                            Key.DirectionLeft, Key.DirectionRight -> { hudPokeSignal++; false }
                            else -> false
                        }
                    }
                    .then(
                        if (isTouchDevice) {
                            Modifier.clickable(
                                interactionSource = remember { androidx.compose.foundation.interaction.MutableInteractionSource() },
                                indication = null
                            ) {
                                hudPokeSignal++
                            }
                        } else {
                            Modifier
                        }
                    ),
            ) {
                androidx.compose.ui.viewinterop.AndroidView(
                    factory = { ctx ->
                        androidx.media3.ui.PlayerView(ctx).apply {
                            player = exoPlayer
                            useController = false
                            setKeepContentOnPlayerReset(true)
                        }
                    },
                    update = { it.player = exoPlayer },
                    modifier = Modifier.fillMaxSize(),
                )
                if (isFullScreen) {
                    FullscreenHud(
                        channel = playingChannel,
                        nowNext = currentNowNext,
                        pokeSignal = hudPokeSignal,
                        onBackClick = if (isTouchDevice) { { exitFullScreenPlayback() } } else null,
                        modifier = Modifier,
                    )
                }
            }
        }

        LaunchedEffect(isFullScreen) {
            if (isFullScreen) {
                runCatching { fsFocus.requestFocus() }
            }
        }

        // Top bar only shows when NOT in full-screen playback.
        // Fade with the fullscreen progress so it doesn't pop in/out — looks
        // natural next to the grow animation below.
        if (showTopBar && fsProgress < 1f) {
            Box(modifier = Modifier.graphicsLayer { alpha = 1f - fsProgress }) {
                AppTopBar(
                    selectedItem = SidebarItem.TV,
                    isFocused = focusZone == LiveTvFocusZone.TOPBAR,
                    focusedIndex = if (focusZone == LiveTvFocusZone.TOPBAR) topBarFocusIndex else -1,
                    profile = currentProfile,
                    profileCount = 1,
                )
            }
        }

        AnimatedVisibility(
            visible = searchOpen,
            enter = fadeIn(),
            exit = fadeOut(),
            modifier = Modifier.fillMaxSize(),
        ) {
            SearchOverlay(
                channels = enrichedState.value.all,
                onDismiss = { searchOpen = false },
                onPick = { channel ->
                    selectedCategoryId = bestCategoryIdForChannel(channel, enrichedState.value.tree)
                    playingChannelId = channel.id
                    focusedChannelId = channel.id
                    searchOpen = false
                    focusChannelList(channel.id)
                },
            )
        }
    }
}

/** State bundle of the enriched channel list + category tree. */
data class EnrichedChannels(
    val all: List<EnrichedChannel>,
    val tree: LiveCategoryTree,
    val index: LiveCategoryIndex = LiveCategoryIndex.Empty,
) {
    companion object {
        val Empty = EnrichedChannels(
            all = emptyList(),
            tree = LiveCategoryTree(
                top = emptyList(),
                global = LiveSection("global", "GLOBAL", emptyList()),
                countries = LiveSection("countries", "COUNTRIES", emptyList()),
                adult = LiveSection("adult", "ADULT", emptyList()),
            ),
        )
    }
}

private tailrec fun Context.findActivity(): Activity? {
    return when (this) {
        is Activity -> this
        is ContextWrapper -> baseContext.findActivity()
        else -> null
    }
}
