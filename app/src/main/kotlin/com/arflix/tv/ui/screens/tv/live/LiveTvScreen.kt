@file:Suppress("UnsafeOptInUsageError")

package com.arflix.tv.ui.screens.tv.live

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.ActivityInfo
import android.view.KeyEvent as AndroidKeyEvent
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
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
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
import androidx.media3.common.MimeTypes
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.datasource.HttpDataSource
import androidx.media3.datasource.okhttp.OkHttpDataSource
import androidx.media3.exoplayer.DefaultLoadControl
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import com.arflix.tv.data.model.IptvChannel
import com.arflix.tv.data.model.IptvNowNext
import com.arflix.tv.data.model.IptvProgram
import com.arflix.tv.data.model.Profile
import com.arflix.tv.ui.screens.tv.TvUiState
import com.arflix.tv.ui.screens.tv.TvViewModel
import com.arflix.tv.network.OkHttpProvider
import com.arflix.tv.ui.components.AppTopBar
import com.arflix.tv.ui.components.KeepScreenOn
import com.arflix.tv.ui.components.AppTopBarHeight
import com.arflix.tv.ui.components.SidebarItem
import com.arflix.tv.ui.components.topBarFocusedItem
import com.arflix.tv.ui.components.topBarMaxIndex
import com.arflix.tv.ui.components.topBarSelectedIndex
import com.arflix.tv.util.LocalDeviceType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit


private object LiveTvScreenRegexes {
    val IPTV_URL_REDACT_REGEX = Regex("""(?i)(/(?:live|movie|series|timeshift)/)([^/]+)/([^/]+)(/)""")
}

private enum class LiveTvFocusZone {
    TOPBAR,
    PROVIDER_SWITCHER,
    CATEGORY_LIST,
    CHANNEL_LIST,
    EPG,
}

private const val GuideInitialWindowRows = 120
private const val GuidePageRows = 120
private const val GuideMaxWindowRows = 420
private const val GuideVisibleFirstRows = 28
private const val GuideVisibleFirstRowsAllChannels = 18
private const val CatchupSeekStepMs = 30_000L
private const val CatchupUrlAnchorGranularityMs = 60_000L
private const val IptvPlaybackUserAgent = "VLC/3.0.20 LibVLC/3.0.20"

private fun digitForTvKeyCode(keyCode: Int): Int? = when (keyCode) {
    AndroidKeyEvent.KEYCODE_0, AndroidKeyEvent.KEYCODE_NUMPAD_0 -> 0
    AndroidKeyEvent.KEYCODE_1, AndroidKeyEvent.KEYCODE_NUMPAD_1 -> 1
    AndroidKeyEvent.KEYCODE_2, AndroidKeyEvent.KEYCODE_NUMPAD_2 -> 2
    AndroidKeyEvent.KEYCODE_3, AndroidKeyEvent.KEYCODE_NUMPAD_3 -> 3
    AndroidKeyEvent.KEYCODE_4, AndroidKeyEvent.KEYCODE_NUMPAD_4 -> 4
    AndroidKeyEvent.KEYCODE_5, AndroidKeyEvent.KEYCODE_NUMPAD_5 -> 5
    AndroidKeyEvent.KEYCODE_6, AndroidKeyEvent.KEYCODE_NUMPAD_6 -> 6
    AndroidKeyEvent.KEYCODE_7, AndroidKeyEvent.KEYCODE_NUMPAD_7 -> 7
    AndroidKeyEvent.KEYCODE_8, AndroidKeyEvent.KEYCODE_NUMPAD_8 -> 8
    AndroidKeyEvent.KEYCODE_9, AndroidKeyEvent.KEYCODE_NUMPAD_9 -> 9
    else -> null
}

private fun chooseStartupChannelId(
    filteredChannels: List<EnrichedChannel>,
    filteredChannelIds: Set<String>,
    explicitInitialChannelId: String?,
    sessionLastChannelId: String,
    hasOpenedBefore: Boolean,
    favoriteChannelIds: List<String>,
    isFullyEnriched: Boolean,
): String? {
    explicitInitialChannelId
        ?.takeIf { id -> id in filteredChannelIds }
        ?.let { return it }
    if (explicitInitialChannelId != null && !isFullyEnriched) return null

    favoriteChannelIds
        .firstOrNull { id -> id in filteredChannelIds }
        ?.let { return it }
    if (favoriteChannelIds.isNotEmpty() && !isFullyEnriched) return null

    if (hasOpenedBefore) {
        sessionLastChannelId
            .takeIf { id -> id.isNotBlank() && id in filteredChannelIds }
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
private fun guideWindowAround(index: Int, total: Int): Pair<Int, Int> {
    if (total <= 0) return 0 to 0
    val safeIndex = index.coerceIn(0, total - 1)
    val before = 0
    val start = (safeIndex - before).coerceAtLeast(0)
    val end = (start + GuideInitialWindowRows).coerceAtMost(total)
    val balancedStart = (end - GuideInitialWindowRows).coerceAtLeast(0)
    return balancedStart to end
}

private fun expandGuideWindowAfter(start: Int, end: Int, total: Int): Pair<Int, Int> {
    if (end >= total) return start to end
    val nextEnd = (end + GuidePageRows).coerceAtMost(total)
    val overflow = (nextEnd - start - GuideMaxWindowRows).coerceAtLeast(0)
    return (start + overflow).coerceAtMost(nextEnd) to nextEnd
}

private fun expandGuideWindowBefore(start: Int, end: Int): Pair<Int, Int> {
    if (start <= 0) return start to end
    val nextStart = (start - GuidePageRows).coerceAtLeast(0)
    val overflow = (end - nextStart - GuideMaxWindowRows).coerceAtLeast(0)
    return nextStart to (end - overflow).coerceAtLeast(nextStart)
}

private fun EnrichedChannel.hasGuideIdentity(): Boolean =
    !source.epgId.isNullOrBlank() || !source.tvgName.isNullOrBlank()

private fun IptvNowNext?.hasGuideData(): Boolean =
    this != null &&
        (now != null || next != null || later != null || upcoming.isNotEmpty() || recent.isNotEmpty())

private fun mergeProgramLists(
    first: List<IptvProgram>,
    second: List<IptvProgram>,
): List<IptvProgram> {
    if (first.isEmpty()) return second
    if (second.isEmpty()) return first
    return (first + second)
        .distinctBy { "${it.startUtcMillis}:${it.endUtcMillis}:${it.title}" }
        .sortedBy { it.startUtcMillis }
}

private fun mergeGuideSlices(
    primary: IptvNowNext?,
    secondary: IptvNowNext?,
): IptvNowNext? {
    if (!primary.hasGuideData()) return secondary
    if (!secondary.hasGuideData()) return primary
    primary ?: return secondary
    secondary ?: return primary
    return IptvNowNext(
        now = primary.now ?: secondary.now,
        next = primary.next ?: secondary.next,
        later = primary.later ?: secondary.later,
        upcoming = mergeProgramLists(primary.upcoming, secondary.upcoming),
        recent = mergeProgramLists(primary.recent, secondary.recent),
    )
}

private fun EnrichedChannel.guideFallbackKeys(): List<String> {
    val playlistId = id.substringBefore(':', missingDelimiterValue = "").trim()
    val prefix = playlistId.ifBlank { "default" }
    val keys = LinkedHashSet<String>()

    fun addKey(kind: String, value: String?) {
        val normalized = value
            ?.trim()
            ?.lowercase()
            ?.takeIf { it.isNotBlank() }
            ?: return
        keys += "$prefix|$kind:$normalized"
    }

    addKey("epg", source.epgId)
    addKey("tvg", source.tvgName)
    source.variantKey
        ?.takeIf { it != source.id }
        ?.let { addKey("variant", it) }
    addKey(
        "name",
        name
            .substringAfter('|', missingDelimiterValue = name)
            .replace(Regex("""(?i)\b(?:4k|uhd|fhd|hd|sd|1080p|720p|60fps)\b"""), " ")
            .replace(Regex("""\s+"""), " ")
            .trim()
    )

    return keys.toList()
}

private fun looksLikeMpegTsUrl(url: String): Boolean {
    val lower = url.lowercase()
    val path = lower.substringBefore('?')
    if (path.endsWith(".m3u8") || "output=m3u8" in lower) return false
    if (
        path.endsWith(".ts") ||
        path.endsWith("timeshift.php") ||
        "output=ts" in lower ||
        path.contains("/timeshift/")
    ) return true

    val segments = path
        .substringAfter("://", missingDelimiterValue = "")
        .substringAfter('/', missingDelimiterValue = "")
        .trim('/')
        .split('/')
        .filter { it.isNotBlank() }
    return segments.size >= 4 &&
        segments.first().equals("live", ignoreCase = true) &&
        segments.last().substringBefore('.').toIntOrNull() != null
}

private fun IptvProgram.shiftedForCatchup(offsetMs: Long): IptvProgram {
    val latestStartOffset = (endUtcMillis - startUtcMillis - 1_000L).coerceAtLeast(0L)
    val safeOffset = offsetMs.coerceIn(0L, latestStartOffset)
    if (safeOffset <= 0L) return this
    return copy(startUtcMillis = (startUtcMillis + safeOffset).coerceAtMost(endUtcMillis - 1_000L))
}

private fun IptvChannel.catchupUrlAnchorOffset(offsetMs: Long): Long {
    val safeOffset = offsetMs.coerceAtLeast(0L)
    val type = catchupType?.trim()?.lowercase().orEmpty()
    val usesMinuteStart = type in setOf("xtream", "xc", "xciptv", "timeshift") ||
        xtreamStreamId != null ||
        streamUrl.contains("/live/", ignoreCase = true)
    return if (usesMinuteStart) {
        safeOffset - (safeOffset % CatchupUrlAnchorGranularityMs)
    } else {
        safeOffset
    }
}

private fun IptvChannel.catchupInSegmentSeekOffset(offsetMs: Long): Long {
    val safeOffset = offsetMs.coerceAtLeast(0L)
    return (safeOffset - catchupUrlAnchorOffset(safeOffset)).coerceAtLeast(0L)
}

private fun EnrichedChannel?.supportsCatchupHistory(): Boolean {
    val source = this?.source ?: return false
    if (source.catchupDays > 0) return true
    if (!source.catchupType.isNullOrBlank() || !source.catchupSource.isNullOrBlank()) return true
    return source.streamUrl.contains("/timeshift/", ignoreCase = true)
        || source.xtreamStreamId != null
        || source.streamUrl.contains("/live/", ignoreCase = true)
}

private fun EnrichedChannel.hasExplicitCatchupSource(): Boolean {
    val source = this.source
    if (source.catchupDays > 0) return true
    if (!source.catchupType.isNullOrBlank() || !source.catchupSource.isNullOrBlank()) return true
    return source.streamUrl.contains("/timeshift/", ignoreCase = true)
}

private fun catchupQualityRank(channel: EnrichedChannel): Int = when (channel.quality) {
    Quality.K4 -> 4
    Quality.FHD -> 3
    Quality.HD -> 2
    Quality.SD -> 1
}

private fun catchupPlaybackVariant(
    channel: EnrichedChannel,
    channels: List<EnrichedChannel>
): EnrichedChannel {
    if (channel.hasExplicitCatchupSource()) return channel
    val key = variantGroupKey(channel)
    return channels
        .asSequence()
        .filter { it.id != channel.id && variantGroupKey(it) == key }
        .filter { it.hasExplicitCatchupSource() }
        .maxWithOrNull(
            compareBy<EnrichedChannel> { it.source.catchupDays }
                .thenBy { catchupQualityRank(it) }
        )
        ?: channel
}

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
    onNavigateToIptvSettings: (() -> Unit)? = null,
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
    val coroutineScope = rememberCoroutineScope()
    val guideClockMillis by produceState(initialValue = System.currentTimeMillis()) {
        while (true) {
            delay(30_000L)
            value = System.currentTimeMillis()
        }
    }
    var selectedCategoryId by rememberSaveable { mutableStateOf("all") }
    var selectedProviderId by rememberSaveable { mutableStateOf("all") }
    val recents = remember { mutableStateOf<LinkedHashSet<String>>(LinkedHashSet()) }
    val favSet = remember(state.snapshot.favoriteChannels) { state.snapshot.favoriteChannels.toSet() }
    val hiddenGroupSet = remember(state.snapshot.hiddenGroups) { state.snapshot.hiddenGroups.toSet() }
    LaunchedEffect(state.tvSession.recentChannelIds, state.tvSession.lastChannelId) {
        val persistedRecents = state.tvSession.recentChannelIds
            .ifEmpty { listOfNotNull(state.tvSession.lastChannelId.takeIf { it.isNotBlank() }) }
        if (persistedRecents.isNotEmpty()) {
            recents.value = LinkedHashSet<String>().apply {
                persistedRecents.forEach { id ->
                    if (id.isNotBlank()) add(id)
                }
                while (size > 40) remove(first())
            }
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

        val initialValue = withContext(Dispatchers.Default) {
            buildFastStartupChannelState(
                channels = snapshot,
                favorites = favSet,
                recents = recents.value,
                hiddenGroups = hiddenGroupSet,
                groupOrder = state.snapshot.groupOrder,
            )
        }
        enrichedState.value = initialValue
        if (snapshot.size > 10_000) {
            viewModel.cachedEnrichedChannels = initialValue
            viewModel.cachedChannelsSignature = signature
            return@LaunchedEffect
        }
        val enriched = withContext(Dispatchers.Default) {
            snapshot.mapIndexed { idx, ch -> ch.enrich(100 + idx) }
        }
        val index = withContext(Dispatchers.Default) { buildCategoryIndex(enriched, hiddenGroupSet) }
        val tree = withContext(Dispatchers.Default) {
            buildCategoryTree(
                channels = enriched,
                favoritesCount = favSet.count { index.isVisibleNonAdultChannel(it) },
                recentCount = recents.value.count { index.isVisibleNonAdultChannel(it) },
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
        val tree = withContext(Dispatchers.Default) {
            buildCategoryTree(
                channels = current.all,
                favoritesCount = favSet.count { current.index.isVisibleNonAdultChannel(it) },
                recentCount = recents.value.count { current.index.isVisibleNonAdultChannel(it) },
                hiddenGroups = hiddenGroupSet,
                groupOrder = state.snapshot.groupOrder,
            )
        }
        enrichedState.value = current.copy(tree = tree)
    }

    val providerFilters = remember(state.config, enrichedState.value.all) {
        buildTvProviderFilters(state.config, enrichedState.value.all)
    }
    LaunchedEffect(providerFilters, selectedProviderId) {
        if (providerFilters.isEmpty() || providerFilters.none { it.id == selectedProviderId }) {
            selectedProviderId = "all"
        }
    }

    val visibleEnrichedState = remember { mutableStateOf(EnrichedChannels.Empty) }
    LaunchedEffect(
        enrichedState.value,
        selectedProviderId,
        favSet,
        hiddenGroupSet,
        state.snapshot.groupOrder,
        recents.value,
        state.config,
    ) {
        val current = enrichedState.value
        if (current === EnrichedChannels.Empty) {
            visibleEnrichedState.value = EnrichedChannels.Empty
            return@LaunchedEffect
        }
        if (selectedProviderId == "all") {
            visibleEnrichedState.value = current
            return@LaunchedEffect
        }
        val visibleChannels = withContext(Dispatchers.Default) {
            current.all.filter(providerMatcher(selectedProviderId, state.config))
        }
        val index = withContext(Dispatchers.Default) { buildCategoryIndex(visibleChannels, hiddenGroupSet) }
        val tree = withContext(Dispatchers.Default) {
            buildCategoryTree(
                channels = visibleChannels,
                favoritesCount = favSet.count { index.isVisibleNonAdultChannel(it) },
                recentCount = recents.value.count { index.isVisibleNonAdultChannel(it) },
                hiddenGroups = hiddenGroupSet,
                groupOrder = state.snapshot.groupOrder,
            )
        }
        visibleEnrichedState.value = EnrichedChannels(all = visibleChannels, tree = tree, index = index)
    }
    LaunchedEffect(hiddenGroupSet, selectedCategoryId, visibleEnrichedState.value.tree) {
        if (selectedCategoryId != "all" && visibleEnrichedState.value.tree.byId(selectedCategoryId) == null) {
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
    var lastGuideUserNavigationAt by remember { mutableLongStateOf(0L) }
    fun noteGuideUserNavigation() {
        lastGuideUserNavigationAt = System.currentTimeMillis()
    }
    fun isGuideUserNavigating(): Boolean =
        System.currentTimeMillis() - lastGuideUserNavigationAt < 2_500L

    // Category switches are served from prebuilt buckets. Favorites and
    // recents remain ordered dynamic lists, but they are simple id lookups.
    val filteredChannelsState = remember { mutableStateOf<List<EnrichedChannel>>(emptyList()) }
    val recentsFilterKey = if (selectedCategoryId == "recent") recents.value else Unit
    LaunchedEffect(visibleEnrichedState.value.index, selectedCategoryId, favSet, recentsFilterKey) {
        val result = withContext(Dispatchers.Default) {
            visibleEnrichedState.value.index.channelsFor(
                categoryId = selectedCategoryId,
                favorites = state.snapshot.favoriteChannels,
                recents = recents.value,
            )
        }
        filteredChannelsState.value = result
    }
    val visibleChannels = visibleEnrichedState.value.all
    val variantGroups = remember(visibleChannels) { buildVariantGroups(visibleChannels) }
    val allDisplayChannels = remember(visibleChannels, variantGroups) {
        collapseChannelVariants(visibleChannels, variantGroups)
    }
    val filteredChannels = remember(filteredChannelsState.value, variantGroups) {
        collapseChannelVariants(filteredChannelsState.value, variantGroups)
    }
    val filteredChannelIndexById = remember(filteredChannels) {
        HashMap<String, Int>(filteredChannels.size).apply {
            filteredChannels.forEachIndexed { index, channel -> put(channel.id, index) }
        }
    }
    val visibleChannelsById = visibleEnrichedState.value.index.byId
    fun guideForChannel(channel: EnrichedChannel?): IptvNowNext? {
        if (channel == null) return null
        return state.snapshot.nowNext[channel.id]
    }
    // Playing channel — default to the one we were navigated to, else the first
    // channel of the first non-empty category.
    val rememberedChannelByCategory = remember { mutableMapOf<String, String>() }
    var playingChannelId by rememberSaveable { mutableStateOf<String?>(initialChannelId) }
    KeepScreenOn(active = playingChannelId != null)
    LaunchedEffect(playingChannelId) {
        viewModel.setLiveTvPlaybackActive(playingChannelId != null)
    }
    DisposableEffect(Unit) {
        onDispose { viewModel.setLiveTvPlaybackActive(false) }
    }
    var focusedChannelId by rememberSaveable { mutableStateOf<String?>(initialChannelId) }
    var epgPrefetchAnchorId by rememberSaveable { mutableStateOf<String?>(initialChannelId) }
    var startupChannelApplied by rememberSaveable(selectedProviderId) { mutableStateOf(false) }
    var playingCatchupProgram by remember { mutableStateOf<IptvProgram?>(null) }
    var catchupPlaybackOffsetMs by remember { mutableLongStateOf(0L) }
    val focusCommitScope = rememberCoroutineScope()
    val pendingFocusCommit = remember { arrayOf<Pair<String, String>?>(null) }
    val focusCommitJob = remember { arrayOf<Job?>(null) }
    fun commitFocusedChannel(channel: EnrichedChannel) {
        pendingFocusCommit[0] = channel.id to selectedCategoryId
        focusCommitJob[0]?.cancel()
        focusCommitJob[0] = focusCommitScope.launch {
            delay(140L)
            val (channelId, categoryId) = pendingFocusCommit[0] ?: return@launch
            if (focusedChannelId != channelId) {
                focusedChannelId = channelId
            }
            epgPrefetchAnchorId = channelId
            rememberedChannelByCategory[categoryId] = channelId
        }
    }
    DisposableEffect(Unit) {
        onDispose { focusCommitJob[0]?.cancel() }
    }
    val selectedDisplayChannelId = remember(focusedChannelId, playingChannelId, visibleChannelsById, variantGroups) {
        displayChannelIdFor(focusedChannelId ?: playingChannelId, visibleChannelsById, variantGroups)
    }
    val playingChannel = remember(playingChannelId, visibleEnrichedState.value, filteredChannels) {
        playingChannelId?.let { visibleEnrichedState.value.index.byId[it] }
            ?: filteredChannels.firstOrNull { it.id == playingChannelId }
    }
    val catchupUrlAnchorOffsetMs = remember(playingChannel?.source, catchupPlaybackOffsetMs) {
        playingChannel?.source?.catchupUrlAnchorOffset(catchupPlaybackOffsetMs) ?: 0L
    }
    val catchupInSegmentSeekMs = remember(playingChannel?.source, catchupPlaybackOffsetMs) {
        playingChannel?.source?.catchupInSegmentSeekOffset(catchupPlaybackOffsetMs) ?: 0L
    }
    val currentNowNext = remember(playingChannel, playingCatchupProgram, state.snapshot.nowNext) {
        val live = guideForChannel(playingChannel)
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

    var guideWindowStart by rememberSaveable { mutableIntStateOf(0) }
    var guideWindowEnd by rememberSaveable { mutableIntStateOf(GuideInitialWindowRows) }
    fun setGuideWindow(window: Pair<Int, Int>) {
        val total = filteredChannels.size
        val start = window.first.coerceIn(0, total.coerceAtLeast(0))
        val end = window.second.coerceIn(start, total)
        guideWindowStart = start
        guideWindowEnd = end
    }
    fun requestGuideWindowBefore() {
        setGuideWindow(expandGuideWindowBefore(guideWindowStart, guideWindowEnd))
    }
    fun requestGuideWindowAfter() {
        setGuideWindow(expandGuideWindowAfter(guideWindowStart, guideWindowEnd, filteredChannels.size))
    }
    val filteredChannelsWindowKey = remember(filteredChannels) {
        listOf(
            filteredChannels.size.toString(),
            filteredChannels.firstOrNull()?.id.orEmpty(),
            filteredChannels.lastOrNull()?.id.orEmpty(),
        ).joinToString("|")
    }
    var guideScopeKey by rememberSaveable { mutableStateOf("") }
    LaunchedEffect(selectedProviderId, selectedCategoryId, filteredChannelsWindowKey) {
        if (filteredChannels.isEmpty()) return@LaunchedEffect
        val nextScopeKey = "$selectedProviderId|$selectedCategoryId"
        if (guideScopeKey != nextScopeKey) {
            val anchorId = rememberedChannelByCategory[selectedCategoryId]
                ?: focusedChannelId
                ?: playingChannelId
                ?: initialChannelId
            val anchorIndex = anchorId?.let(filteredChannelIndexById::get) ?: 0
            setGuideWindow(guideWindowAround(anchorIndex, filteredChannels.size))
            guideScopeKey = nextScopeKey
        } else if (guideWindowStart >= filteredChannels.size) {
            setGuideWindow(guideWindowAround(filteredChannels.lastIndex, filteredChannels.size))
        } else if (!isGuideUserNavigating() && guideWindowEnd <= guideWindowStart) {
            val anchorIndex = focusedChannelId?.let(filteredChannelIndexById::get)
                ?: playingChannelId?.let(filteredChannelIndexById::get)
                ?: 0
            setGuideWindow(guideWindowAround(anchorIndex, filteredChannels.size))
        }
    }
    LaunchedEffect(playingChannelId, selectedCategoryId, selectedProviderId) {
        if (isGuideUserNavigating() && (focusZone == LiveTvFocusZone.CHANNEL_LIST || focusZone == LiveTvFocusZone.EPG)) {
            return@LaunchedEffect
        }
        val index = playingChannelId?.let(filteredChannelIndexById::get) ?: return@LaunchedEffect
        if (index !in guideWindowStart until guideWindowEnd) {
            setGuideWindow(guideWindowAround(index, filteredChannels.size))
        }
    }
    val normalizedGuideStart = guideWindowStart.coerceIn(0, filteredChannels.size)
    val normalizedGuideEnd = guideWindowEnd.coerceIn(normalizedGuideStart, filteredChannels.size)
    val guideChannels = remember(filteredChannels, normalizedGuideStart, normalizedGuideEnd) {
        if (normalizedGuideStart >= normalizedGuideEnd) emptyList() else filteredChannels.subList(normalizedGuideStart, normalizedGuideEnd)
    }
    val guideChannelIndexById = remember(guideChannels) {
        HashMap<String, Int>(guideChannels.size).apply {
            guideChannels.forEachIndexed { index, channel -> put(channel.id, index) }
        }
    }
    val effectiveGuideNowNext = remember(state.snapshot.nowNext, guideChannels) {
        HashMap<String, IptvNowNext>(guideChannels.size).apply {
            guideChannels.forEach { channel ->
                state.snapshot.nowNext[channel.id]?.let { put(channel.id, it) }
            }
        }
    }

    val epgAnchorChannelId = epgPrefetchAnchorId
        ?: selectedDisplayChannelId
        ?: focusedChannelId
        ?: playingChannelId
    val epgPrefetchIds = remember(guideChannels, guideChannelIndexById, selectedCategoryId, epgAnchorChannelId) {
        val maxPrefetch = if (selectedCategoryId == "all") 96 else 180
        val visibleFirstRows = if (selectedCategoryId == "all") GuideVisibleFirstRowsAllChannels else GuideVisibleFirstRows
        val anchorIndex = epgAnchorChannelId?.let(guideChannelIndexById::get) ?: 0
        buildList<String> {
            fun addChannel(channel: EnrichedChannel?) {
                val id = channel?.id ?: return
                if (!contains(id)) add(id)
            }
            fun addGuideFirst(index: Int) {
                val channel = guideChannels.getOrNull(index) ?: return
                if (channel.hasGuideIdentity()) addChannel(channel)
            }

            // First paint must target the selected row plus the rows visible
            // below it. These may lack tvg-id but still have an Xtream stream
            // id that can return direct short/full EPG data.
            addChannel(guideChannels.getOrNull(anchorIndex))
            var nearIndex = anchorIndex + 1
            var nearCount = 0
            while (nearIndex < guideChannels.size && nearCount < visibleFirstRows && size < maxPrefetch) {
                addChannel(guideChannels[nearIndex])
                nearIndex++
                nearCount++
            }
            var nearBackIndex = anchorIndex - 1
            var nearBackCount = 0
            while (nearBackIndex >= 0 && nearBackCount < 8 && size < maxPrefetch) {
                addChannel(guideChannels[nearBackIndex])
                nearBackIndex--
                nearBackCount++
            }

            var index = anchorIndex + 1
            while (index < guideChannels.size && size < maxPrefetch) {
                addGuideFirst(index)
                index++
            }
            var backIndex = anchorIndex - 1
            var backCount = 0
            while (backIndex >= 0 && backCount < 24 && size < maxPrefetch) {
                addGuideFirst(backIndex)
                backIndex--
                backCount++
            }
            index = 0
            while (index < guideChannels.size && size < maxPrefetch) {
                addGuideFirst(index)
                index++
            }
            index = 0
            while (index < guideChannels.size && size < maxPrefetch) {
                val channel = guideChannels[index]
                if (!channel.hasGuideIdentity()) {
                    addChannel(channel)
                }
                index++
            }
        }
    }
    LaunchedEffect(selectedCategoryId, epgPrefetchIds, epgAnchorChannelId, state.iptvPreferencesLoaded, state.tvSessionLoaded, state.tvSession.lastChannelId, guideChannelIndexById, startupChannelApplied, playingChannelId, selectedDisplayChannelId, focusedChannelId) {
        val startupReady = state.iptvPreferencesLoaded && state.tvSessionLoaded
        if (startupReady && startupChannelApplied && epgPrefetchIds.isNotEmpty()) {
            val selectedId = epgAnchorChannelId
                ?.takeIf { it in guideChannelIndexById }
                ?: selectedDisplayChannelId?.takeIf { it in guideChannelIndexById }
                ?: playingChannelId?.takeIf { it in guideChannelIndexById }
                ?: epgPrefetchIds.firstOrNull()
            viewModel.prefetchVisibleCategoryEpg(
                channelIds = epgPrefetchIds,
                selectedChannelId = selectedId,
                eagerLimit = if (selectedCategoryId == "all") 12 else 24,
                backgroundLimit = if (selectedCategoryId == "all") 48 else 96,
            )
        }
    }
    LaunchedEffect(playingChannelId, selectedDisplayChannelId, focusedChannelId, state.iptvPreferencesLoaded, state.tvSessionLoaded, startupChannelApplied) {
        val ids = listOfNotNull(playingChannelId, selectedDisplayChannelId, focusedChannelId)
            .filter { it.isNotBlank() }
            .distinct()
        val selectedId = playingChannelId ?: selectedDisplayChannelId ?: focusedChannelId
        if (ids.isEmpty() || selectedId.isNullOrBlank()) return@LaunchedEffect
        if (state.iptvPreferencesLoaded && state.tvSessionLoaded && startupChannelApplied) {
            System.err.println("[EPG-Current] ids=${ids.take(4)} selected=$selectedId")
            viewModel.refreshCurrentChannelEpg(selectedId)
            viewModel.prefetchVisibleCategoryEpg(
                channelIds = ids,
                selectedChannelId = selectedId,
                eagerLimit = 1,
                backgroundLimit = 1,
            )
        }
    }
    val catchupHistoryAnchorIds = remember(
        epgAnchorChannelId,
        selectedDisplayChannelId,
        focusedChannelId,
        playingChannelId,
        visibleChannelsById,
        visibleChannels,
    ) {
        buildList {
            listOfNotNull(epgAnchorChannelId, selectedDisplayChannelId, focusedChannelId, playingChannelId)
                .forEach { id ->
                    val channel = visibleChannelsById[id] ?: return@forEach
                    if (channel.supportsCatchupHistory() && channel.id !in this) {
                        add(channel.id)
                    }
                    val archiveVariant = catchupPlaybackVariant(channel, visibleChannels)
                    if (archiveVariant.supportsCatchupHistory() && archiveVariant.id !in this) {
                        add(archiveVariant.id)
                    }
                }
        }.take(3)
    }
    LaunchedEffect(catchupHistoryAnchorIds, state.iptvPreferencesLoaded, state.tvSessionLoaded, startupChannelApplied) {
        if (!state.iptvPreferencesLoaded || !state.tvSessionLoaded || !startupChannelApplied) return@LaunchedEffect
        if (catchupHistoryAnchorIds.isEmpty()) return@LaunchedEffect
        delay(120L)
        catchupHistoryAnchorIds.forEach { id ->
            viewModel.refreshCatchupHistoryForChannel(id)
        }
    }
    val guideStatusIds = remember(epgPrefetchIds, guideChannels, visibleChannelsById, effectiveGuideNowNext) {
        epgPrefetchIds
            .ifEmpty { guideChannels.asSequence().map { it.id }.take(96).toList() }
            .filter { id ->
                visibleChannelsById[id]?.hasGuideIdentity() == true ||
                    effectiveGuideNowNext[id].hasGuideData()
            }
            .toCollection(HashSet())
    }
    val matchedGuideCount = remember(effectiveGuideNowNext, guideStatusIds) {
        guideStatusIds.count { id ->
            effectiveGuideNowNext[id]?.let { guide ->
                guide.now != null || guide.next != null || guide.later != null ||
                    guide.upcoming.isNotEmpty() || guide.recent.isNotEmpty()
            } == true
        }
    }
    val guideLoadingInScope = remember(state.epgLoadingChannelIds, guideStatusIds) {
        state.epgLoadingChannelIds.any { it in guideStatusIds }
    }

    // Pick the startup channel only after saved IPTV preferences/session have
    // loaded. Favorites win over a stale recent channel, then we fall back to
    // the persisted recent channel, then the first filtered entry.
    LaunchedEffect(filteredChannelsWindowKey, playingChannelId, initialChannelId, state.tvSession, state.snapshot.favoriteChannels, visibleEnrichedState.value.all.size, state.iptvPreferencesLoaded, state.tvSessionLoaded, selectedProviderId, startupChannelApplied) {
        val startupStateReady = state.iptvPreferencesLoaded && state.tvSessionLoaded
        val playingVisible = playingChannelId?.let { id -> id in visibleEnrichedState.value.index.byId } == true
        if (!startupChannelApplied && filteredChannels.isNotEmpty() && (initialChannelId != null || startupStateReady)) {
            val startupChannelId = chooseStartupChannelId(
                filteredChannels = filteredChannels,
                filteredChannelIds = filteredChannelIndexById.keys,
                explicitInitialChannelId = initialChannelId?.takeIf { selectedProviderId == "all" || it in visibleEnrichedState.value.index.byId },
                sessionLastChannelId = state.tvSession.lastChannelId,
                hasOpenedBefore = state.tvSession.lastOpenedAt > 0L,
                favoriteChannelIds = state.snapshot.favoriteChannels,
                isFullyEnriched = visibleEnrichedState.value.all.isNotEmpty(),
            )
            if (startupChannelId != null) {
                val displayId = displayChannelIdFor(startupChannelId, visibleEnrichedState.value.index.byId, variantGroups)
                    ?: startupChannelId
                playingChannelId = startupChannelId
                focusedChannelId = displayId
                epgPrefetchAnchorId = displayId
                rememberedChannelByCategory[selectedCategoryId] = displayId
                filteredChannelIndexById[displayId]
                    ?.let { setGuideWindow(guideWindowAround(it, filteredChannels.size)) }
                startupChannelApplied = true
                System.err.println("[EPG-Startup] channel=$startupChannelId focus=$displayId")
            }
        } else if ((!playingVisible || playingChannelId == null) && filteredChannels.isNotEmpty() && startupStateReady && !isGuideUserNavigating()) {
            val fallbackChannelId = chooseStartupChannelId(
                filteredChannels = filteredChannels,
                filteredChannelIds = filteredChannelIndexById.keys,
                explicitInitialChannelId = null,
                sessionLastChannelId = state.tvSession.lastChannelId,
                hasOpenedBefore = state.tvSession.lastOpenedAt > 0L,
                favoriteChannelIds = state.snapshot.favoriteChannels,
                isFullyEnriched = visibleEnrichedState.value.all.isNotEmpty(),
            )
            if (fallbackChannelId != null) {
                playingChannelId = fallbackChannelId
                focusedChannelId = displayChannelIdFor(fallbackChannelId, visibleEnrichedState.value.index.byId, variantGroups)
                    ?: fallbackChannelId
                epgPrefetchAnchorId = focusedChannelId
            }
        }
        if ((focusedChannelId == null || focusedChannelId !in filteredChannelIndexById) && !isGuideUserNavigating()) {
            focusedChannelId = displayChannelIdFor(playingChannelId, visibleEnrichedState.value.index.byId, variantGroups)
                ?.takeIf { id -> id in filteredChannelIndexById }
                ?: filteredChannels.firstOrNull()?.id
            epgPrefetchAnchorId = focusedChannelId
        }
    }

    val sidebarExpanded = !useTouchRail
    var searchOpen by rememberSaveable { mutableStateOf(false) }
    var focusSelectedChannelSignal by remember { mutableIntStateOf(0) }
    var focusEpgSignal by remember { mutableIntStateOf(0) }
    var focusSearchCategorySignal by remember { mutableIntStateOf(1) }
    // Full-screen playback mode — pressing OK on an EPG row expands the
    // mini-player to cover the whole screen. Back collapses back to the grid.
    var isFullScreen by rememberSaveable { mutableStateOf(initialStreamUrl != null) }
    var fullscreenGuideOpen by remember { mutableStateOf(false) }
    var variantPickerChannel by remember { mutableStateOf<EnrichedChannel?>(null) }
    LaunchedEffect(isFullScreen) {
        onFullscreenChanged(isFullScreen)
    }
    DisposableEffect(Unit) {
        onDispose { onFullscreenChanged(false) }
    }
    // Focus requesters for the three regions.
    val sidebarFocus = remember { FocusRequester() }
    val providerFocus = remember { FocusRequester() }
    val epgFocus = remember { FocusRequester() }
    val fsFocus = remember { FocusRequester() }
    val emptyStateButtonFocus = remember { FocusRequester() }

    var hudPokeSignal by remember { mutableStateOf(0) }
    var quickZapOpen by remember { mutableStateOf(false) }
    var isHudVisible by remember { mutableStateOf(false) }
    var guideOpenedFromQuickZap by remember { mutableStateOf(false) }
    var guideChannel by remember { mutableStateOf<EnrichedChannel?>(null) }

    fun getAvailableCategoryIds(tree: LiveCategoryTree): List<String> {
        val list = mutableListOf<String>()
        tree.top.forEach { cat ->
            if (cat.count > 0 || cat.id == "all") {
                list.add(cat.id)
                if (cat.id == "all") {
                    cat.children.forEach { child ->
                        if (child.count > 0) list.add(child.id)
                    }
                }
            }
        }
        tree.global.categories.forEach { cat ->
            if (cat.count > 0) list.add(cat.id)
        }
        tree.countries.categories.forEach { country ->
            if (country.count > 0) {
                list.add(country.id)
                country.children.forEach { child ->
                    if (child.count > 0) list.add(child.id)
                }
            }
        }
        tree.adult.categories.forEach { cat ->
            if (cat.count > 0) list.add(cat.id)
        }
        return list.distinct()
    }

    fun cycleCategory(forward: Boolean) {
        val tree = visibleEnrichedState.value.tree
        val ids = getAvailableCategoryIds(tree)
        if (ids.isEmpty()) return
        val currentIndex = ids.indexOf(selectedCategoryId)
        val nextIndex = if (forward) {
            (currentIndex + 1) % ids.size
        } else {
            (currentIndex - 1 + ids.size) % ids.size
        }
        selectedCategoryId = ids.getOrNull(nextIndex) ?: "all"
    }

    fun openFullscreenGuide() {
        guideChannel = playingChannel
        viewModel.refreshCatchupHistoryForChannel(playingChannelId)
        fullscreenGuideOpen = true
        hudPokeSignal++
    }

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
        noteGuideUserNavigation()
        val all = allDisplayChannels
        if (all.isEmpty()) return
        val currentDisplayId = displayChannelIdFor(playingChannelId, visibleEnrichedState.value.index.byId, variantGroups)
        val currentIdx = currentDisplayId?.let { id -> all.indexOfFirst { channel -> channel.id == id } } ?: -1
        val start = if (currentIdx >= 0) currentIdx else 0
        val size = all.size
        val nextIdx = ((start + delta) % size + size) % size
        playingChannelId = all[nextIdx].id
        focusedChannelId = all[nextIdx].id
        epgPrefetchAnchorId = all[nextIdx].id
        rememberedChannelByCategory[selectedCategoryId] = all[nextIdx].id
        playingCatchupProgram = null
        catchupPlaybackOffsetMs = 0L
        fullscreenGuideOpen = false
    }

    fun focusPlaylistSearch() {
        noteGuideUserNavigation()
        focusZone = LiveTvFocusZone.CATEGORY_LIST
        focusSearchCategorySignal += 1
        runCatching { sidebarFocus.requestFocus() }
    }

    fun focusProviderSwitcher() {
        noteGuideUserNavigation()
        if (providerFilters.size <= 1) {
            focusPlaylistSearch()
            return
        }
        focusZone = LiveTvFocusZone.PROVIDER_SWITCHER
        runCatching { providerFocus.requestFocus() }
    }

    fun focusChannelList(channelId: String? = focusedChannelId ?: playingChannelId) {
        noteGuideUserNavigation()
        channelId?.let {
            focusedChannelId = it
            epgPrefetchAnchorId = it
            rememberedChannelByCategory[selectedCategoryId] = it
            val index = filteredChannelIndexById[it]
            if (index != null && index !in guideWindowStart until guideWindowEnd) {
                setGuideWindow(guideWindowAround(index, filteredChannels.size))
            }
        }
        focusZone = LiveTvFocusZone.CHANNEL_LIST
        focusSelectedChannelSignal += 1
        runCatching { epgFocus.requestFocus() }
    }

    fun focusEpg(channelId: String) {
        noteGuideUserNavigation()
        focusedChannelId = channelId
        epgPrefetchAnchorId = channelId
        rememberedChannelByCategory[selectedCategoryId] = channelId
        val index = filteredChannelIndexById[channelId]
        if (index != null && index !in guideWindowStart until guideWindowEnd) {
            setGuideWindow(guideWindowAround(index, filteredChannels.size))
        }
        focusZone = LiveTvFocusZone.EPG
        focusEpgSignal += 1
        runCatching { epgFocus.requestFocus() }
    }

    fun exitFullScreenPlayback() {
        val returnFocusChannelId = playingChannelId ?: focusedChannelId
        fullscreenGuideOpen = false
        isFullScreen = false
        hudPokeSignal++
        focusCommitScope.launch {
            // Let the fullscreen layer start collapsing before returning focus
            // to the large guide. On big IPTV lists this keeps Back immediate.
            delay(16L)
            focusChannelList(returnFocusChannelId)
        }
    }

    fun selectChannel(channel: EnrichedChannel) {
        noteGuideUserNavigation()
        focusedChannelId = channel.id
        epgPrefetchAnchorId = channel.id
        rememberedChannelByCategory[selectedCategoryId] = channel.id
        val currentDisplayId = displayChannelIdFor(playingChannelId, visibleEnrichedState.value.index.byId, variantGroups)
        val isSamePlayingChannel = channel.id == playingChannelId || channel.id == currentDisplayId
        if (isSamePlayingChannel && !isFullScreen) {
            // Second tap on the already-playing channel → fullscreen
            playingCatchupProgram = null
            catchupPlaybackOffsetMs = 0L
            isFullScreen = true
            hudPokeSignal++
        } else {
            // First tap or different channel → tune in mini-player
            playingChannelId = channel.id
            playingCatchupProgram = null
            catchupPlaybackOffsetMs = 0L
            fullscreenGuideOpen = false
        }
    }

    fun openVariantPicker(channel: EnrichedChannel) {
        noteGuideUserNavigation()
        if (variantCountFor(channel, variantGroups) > 1) {
            variantPickerChannel = channel
        }
    }

    fun playVariant(channel: EnrichedChannel) {
        noteGuideUserNavigation()
        val displayId = displayChannelIdFor(channel.id, visibleEnrichedState.value.index.byId, variantGroups) ?: channel.id
        playingChannelId = channel.id
        focusedChannelId = displayId
        epgPrefetchAnchorId = displayId
        rememberedChannelByCategory[selectedCategoryId] = displayId
        playingCatchupProgram = null
        catchupPlaybackOffsetMs = 0L
        fullscreenGuideOpen = false
        focusChannelList(displayId)
    }

    fun playProgramInMini(channel: EnrichedChannel, program: IptvProgram?) {
        noteGuideUserNavigation()
        val playbackChannel = if (program != null) {
            catchupPlaybackVariant(channel, visibleChannels)
        } else {
            channel
        }
        if (program != null && playbackChannel.id != channel.id) {
            System.err.println(
                "[IPTV-Catchup] using archive variant source=${channel.id} playback=${playbackChannel.id} " +
                    "quality=${playbackChannel.quality.label} days=${playbackChannel.catchupDays}"
            )
        }
        focusedChannelId = playbackChannel.id
        epgPrefetchAnchorId = playbackChannel.id
        rememberedChannelByCategory[selectedCategoryId] = playbackChannel.id
        playingChannelId = playbackChannel.id
        playingCatchupProgram = program
        catchupPlaybackOffsetMs = 0L
        fullscreenGuideOpen = false
        focusChannelList(playbackChannel.id)
    }

    fun playProgramInFullscreen(program: IptvProgram?, targetChannel: EnrichedChannel? = null) {
        val channel = targetChannel ?: playingChannel
        if (program != playingCatchupProgram) {
            catchupPlaybackOffsetMs = 0L
        }
        if (channel != null) {
            val playbackChannel = catchupPlaybackVariant(channel, visibleChannels)
            if (playbackChannel.id != playingChannelId) {
                System.err.println(
                    "[IPTV-Catchup] using fullscreen archive variant source=${channel.id} " +
                        "playback=${playbackChannel.id} quality=${playbackChannel.quality.label} " +
                        "days=${playbackChannel.catchupDays}"
                )
                playingChannelId = playbackChannel.id
                focusedChannelId = playbackChannel.id
                epgPrefetchAnchorId = playbackChannel.id
            }
        }
        playingCatchupProgram = program
        fullscreenGuideOpen = false
        isFullScreen = true
        hudPokeSignal++
    }

    // ExoPlayer lifecycle — mirrors the legacy screen's setup verbatim so live
    // IPTV behaviour (buffer, retries, chunkless HLS) stays identical.
    var channelNumberBuffer by remember { mutableStateOf("") }
    var lastChannelDigitAt by remember { mutableStateOf(0L) }

    fun tuneChannelNumber(channel: EnrichedChannel) {
        noteGuideUserNavigation()
        playingChannelId = channel.id
        focusedChannelId = channel.id
        epgPrefetchAnchorId = channel.id
        playingCatchupProgram = null
        catchupPlaybackOffsetMs = 0L
        fullscreenGuideOpen = false
        rememberedChannelByCategory[selectedCategoryId] = channel.id
        focusChannelList(channel.id)
        hudPokeSignal++
    }

    fun handleChannelNumberDigit(digit: Int): Boolean {
        val now = System.currentTimeMillis()
        val prefix = if (now - lastChannelDigitAt > 1_500L) "" else channelNumberBuffer
        channelNumberBuffer = (prefix + digit.toString()).takeLast(4)
        lastChannelDigitAt = now
        visibleEnrichedState.value.all
            .firstOrNull { it.number.toString() == channelNumberBuffer }
            ?.let {
                tuneChannelNumber(it)
                channelNumberBuffer = ""
            }
        return true
    }

    LaunchedEffect(channelNumberBuffer, visibleEnrichedState.value.all) {
        val query = channelNumberBuffer
        if (query.isBlank()) return@LaunchedEffect
        delay(1_200L)
        if (channelNumberBuffer != query) return@LaunchedEffect
        val target = visibleEnrichedState.value.all
            .filter { it.number.toString().startsWith(query) }
            .take(2)
            .singleOrNull()
        if (target != null) {
            tuneChannelNumber(target)
        }
        channelNumberBuffer = ""
    }

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
    val baseRequestHeaders = remember {
        mapOf(
            "Accept" to "*/*",
            "Accept-Encoding" to "identity",
            "User-Agent" to OkHttpProvider.userAgentOr(IptvPlaybackUserAgent),
            "Connection" to "keep-alive"
        )
    }
    val iptvDataSourceFactory = remember(iptvHttpClient, baseRequestHeaders) {
        OkHttpDataSource.Factory(iptvHttpClient)
            .setUserAgent(OkHttpProvider.userAgentOr(IptvPlaybackUserAgent))
            .setDefaultRequestProperties(baseRequestHeaders)
    }
    val mediaSourceFactory = remember(iptvDataSourceFactory) {
        DefaultMediaSourceFactory(context)
            .setDataSourceFactory(iptvDataSourceFactory)
    }
    val exoPlayer = remember {
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
        ExoPlayer.Builder(context)
            .setMediaSourceFactory(mediaSourceFactory)
            .setLoadControl(loadControl)
            .build().apply {
                playWhenReady = true
                videoScalingMode = C.VIDEO_SCALING_MODE_SCALE_TO_FIT
            }
    }

    DisposableEffect(Unit) { onDispose { exoPlayer.release() } }

    var playerPositionMs by remember { mutableLongStateOf(0L) }
    var playerDurationMs by remember { mutableLongStateOf(0L) }
    var playerIsPlaying by remember { mutableStateOf(false) }
    var playerPlayWhenReady by remember { mutableStateOf(true) }
    LaunchedEffect(exoPlayer, playingCatchupProgram, catchupUrlAnchorOffsetMs) {
        while (true) {
            val programDuration = playingCatchupProgram
                ?.let { (it.endUtcMillis - it.startUtcMillis).coerceAtLeast(0L) }
                ?: 0L
            val exoDuration = exoPlayer.duration
                .takeIf { it > 0L && it != C.TIME_UNSET }
                ?: 0L
            val duration = maxOf(programDuration, exoDuration)
            playerDurationMs = duration
            val streamOffset = if (playingCatchupProgram != null) catchupUrlAnchorOffsetMs else 0L
            playerPositionMs = (streamOffset + exoPlayer.currentPosition)
                .coerceAtLeast(0L)
                .let { position -> if (duration > 0L) position.coerceAtMost(duration) else position }
            playerIsPlaying = exoPlayer.isPlaying
            playerPlayWhenReady = exoPlayer.playWhenReady
            delay(if (playingCatchupProgram != null) 500L else 1_500L)
        }
    }

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val obs = LifecycleEventObserver { _, ev ->
            when (ev) {
                Lifecycle.Event.ON_PAUSE -> exoPlayer.pause()
                Lifecycle.Event.ON_RESUME -> {
                    if (playingChannelId != null) exoPlayer.play()
                    if (currentUiState.isConfigured &&
                        currentUiState.snapshot.channels.isNotEmpty() &&
                        viewModel.iptvRepository.cachedEpgAgeMs() > 6 * 60 * 60_000L
                    ) {
                        viewModel.refresh(force = false, showLoading = false, forceEpg = false)
                    }
                }
                else -> {}
            }
        }
        lifecycleOwner.lifecycle.addObserver(obs)
        onDispose { lifecycleOwner.lifecycle.removeObserver(obs) }
    }

    var lastPreparedStreamUrl by remember { mutableStateOf<String?>(null) }
    var lastPreparedHeaders by remember { mutableStateOf<Map<String, String>>(emptyMap()) }
    var lastPreparedCatchupOffsetMs by remember { mutableLongStateOf(-1L) }
    var playerRetryCount by remember { mutableIntStateOf(0) }
    var playbackDiagnostic by remember { mutableStateOf<PlaybackDiagnostic?>(null) }

    fun prepareStream(
        stream: String,
        headers: Map<String, String>,
        resetRetry: Boolean,
        initialPositionMs: Long = 0L,
        drmInfo: com.arflix.tv.data.model.DrmInfo? = null,
    ) {
        val mergedHeaders = (baseRequestHeaders + headers).filterValues { it.isNotBlank() }
        iptvDataSourceFactory.setDefaultRequestProperties(mergedHeaders)
        exoPlayer.stop()
        exoPlayer.clearMediaItems()
        val mediaItem = MediaItem.Builder()
            .setUri(stream)
            .apply {
                if (looksLikeMpegTsUrl(stream)) {
                    setMimeType(MimeTypes.VIDEO_MP2T)
                }
                if (playingCatchupProgram == null) {
                    setLiveConfiguration(
                        MediaItem.LiveConfiguration.Builder()
                            .setMinPlaybackSpeed(1.0f).setMaxPlaybackSpeed(1.0f)
                            .setTargetOffsetMs(8_000).build()
                    )
                }
                // DRM configuration from #KODIPROP directives
                drmInfo?.let { drm ->
                    val schemeUuid = com.arflix.tv.util.ClearKeyUtil.drmSchemeToUuid(drm.scheme)
                    val drmBuilder = MediaItem.DrmConfiguration.Builder(schemeUuid)
                    if (drm.scheme == "clearkey" && !drm.licenseUrl.isNullOrBlank()) {
                        // ClearKey: build inline JWKS data URI from kid:key hex pair
                        com.arflix.tv.util.ClearKeyUtil.buildClearKeyLicenseUri(drm.licenseUrl)
                            ?.let { dataUri -> drmBuilder.setLicenseUri(dataUri) }
                    } else if (!drm.licenseUrl.isNullOrBlank()) {
                        // Widevine / PlayReady: strip Kodi pipe syntax, use clean URL
                        drmBuilder.setLicenseUri(drm.licenseUrl.substringBefore("|"))
                    }
                    setDrmConfiguration(drmBuilder.build())
                }
            }
            .build()
        if (initialPositionMs > 0L) {
            exoPlayer.setMediaItem(mediaItem, initialPositionMs)
        } else {
            exoPlayer.setMediaItem(mediaItem)
        }
        exoPlayer.prepare()
        exoPlayer.playWhenReady = true
        exoPlayer.play()
        lastPreparedStreamUrl = stream
        lastPreparedHeaders = headers
        lastPreparedCatchupOffsetMs = if (playingCatchupProgram != null) catchupUrlAnchorOffsetMs else -1L
        if (resetRetry) playerRetryCount = 0
        if (resetRetry) {
            playbackDiagnostic = PlaybackDiagnostic(
                title = if (playingCatchupProgram != null && initialPositionMs > 0L) "Seeking catch-up" else "Starting live stream",
                detail = playingChannel?.name ?: "Preparing source",
                severity = PlaybackDiagnosticSeverity.Info,
            )
        }
        System.err.println(
            "[IPTV-Catchup] prepare catchup=${playingCatchupProgram != null} " +
                "anchor=$catchupUrlAnchorOffsetMs inSegment=$initialPositionMs " +
                "target=$catchupPlaybackOffsetMs url=${redactPlaybackUrl(stream)}"
        )
    }

    fun toggleCatchupPlayback() {
        if (playingCatchupProgram == null) return
        if (exoPlayer.isPlaying) {
            exoPlayer.pause()
            playerPlayWhenReady = false
            System.err.println("[IPTV-Catchup] pause position=${exoPlayer.currentPosition}")
        } else {
            exoPlayer.playWhenReady = true
            exoPlayer.play()
            playerPlayWhenReady = true
            System.err.println("[IPTV-Catchup] play position=${exoPlayer.currentPosition}")
        }
        hudPokeSignal++
    }

    fun seekCatchupBy(deltaMs: Long) {
        val program = playingCatchupProgram ?: return
        val duration = (program.endUtcMillis - program.startUtcMillis).coerceAtLeast(0L)
            .takeIf { it > 0L }
            ?: playerDurationMs
        val wasPlayRequested = exoPlayer.playWhenReady
        val maxPosition = if (duration > 1_000L) duration - 1_000L else duration
        val current = (catchupUrlAnchorOffsetMs + exoPlayer.currentPosition.coerceAtLeast(0L))
            .let { if (maxPosition > 0L) it.coerceAtMost(maxPosition) else it }
        val target = (current + deltaMs)
            .coerceAtLeast(0L)
            .let { if (maxPosition > 0L) it.coerceAtMost(maxPosition) else it }
        if (target == catchupPlaybackOffsetMs) {
            hudPokeSignal++
            return
        }
        val source = playingChannel?.source
        val targetAnchor = source?.catchupUrlAnchorOffset(target) ?: 0L
        val targetInSegment = source?.catchupInSegmentSeekOffset(target) ?: target
        val sameAnchor = targetAnchor == catchupUrlAnchorOffsetMs
        catchupPlaybackOffsetMs = target
        playerPositionMs = target
        exoPlayer.playWhenReady = true
        if (sameAnchor) {
            exoPlayer.seekTo(targetInSegment)
        }
        exoPlayer.play()
        playerPlayWhenReady = true
        System.err.println(
            "[IPTV-Catchup] seek delta=$deltaMs current=$current target=$target duration=$duration " +
                "wasPlayRequested=$wasPlayRequested state=${exoPlayer.playbackState} " +
                "anchor=$catchupUrlAnchorOffsetMs targetAnchor=$targetAnchor " +
                "inSegment=$targetInSegment sameAnchor=$sameAnchor exo=${exoPlayer.currentPosition}"
        )
        hudPokeSignal++
    }

    fun returnCatchupToLive() {
        if (playingCatchupProgram == null) return
        System.err.println("[IPTV-Catchup] return-live channel=${playingChannelId.orEmpty()}")
        playingCatchupProgram = null
        catchupPlaybackOffsetMs = 0L
        fullscreenGuideOpen = false
        exoPlayer.play()
        hudPokeSignal++
    }

    // When the selected channel changes, swap media item.
    val currentStreamUrl = remember(playingChannel, playingCatchupProgram, catchupUrlAnchorOffsetMs) {
        val ch = playingChannel ?: return@remember initialStreamUrl
        val pr = playingCatchupProgram
        if (pr != null) {
            viewModel.iptvRepository.getCatchupUrl(ch.source, pr.shiftedForCatchup(catchupUrlAnchorOffsetMs))
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
    LaunchedEffect(currentStreamUrl, playingCatchupProgram, catchupUrlAnchorOffsetMs, playingChannel?.id) {
        val rawStream = currentStreamUrl ?: return@LaunchedEffect
        val sourceChannel = playingChannel?.source
        val streamProgram = playingCatchupProgram?.shiftedForCatchup(catchupUrlAnchorOffsetMs)
        val stream = runCatching {
            if (sourceChannel != null) {
                viewModel.resolvePlayableStreamUrl(sourceChannel, streamProgram, catchupAttempt = 0)
            } else {
                rawStream
            }
        }.getOrElse { error ->
            playbackDiagnostic = PlaybackDiagnostic(
                title = if (playingCatchupProgram != null) "Catch-up unavailable" else "Playback failed",
                detail = error.message ?: "Provider did not return a playable stream.",
                severity = PlaybackDiagnosticSeverity.Error,
            )
            System.err.println(
                "[IPTV] Failed to resolve playable stream catchup=${playingCatchupProgram != null} " +
                    "channel=${sourceChannel?.id.orEmpty()} reason=${error.message}"
            )
            return@LaunchedEffect
        }
        val headers = sourceChannel?.requestHeaders.orEmpty()
        delay(90L)
        if (
            stream == lastPreparedStreamUrl &&
            headers == lastPreparedHeaders &&
            catchupUrlAnchorOffsetMs == lastPreparedCatchupOffsetMs
        ) {
            return@LaunchedEffect
        }
        prepareStream(
            stream = stream,
            headers = headers,
            resetRetry = true,
            initialPositionMs = if (playingCatchupProgram != null) catchupInSegmentSeekMs else 0L,
            drmInfo = playingChannel?.source?.drmInfo,
        )
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

    DisposableEffect(
        exoPlayer,
        lastPreparedStreamUrl,
        lastPreparedHeaders,
        playingChannel?.id,
        playingCatchupProgram,
        catchupPlaybackOffsetMs
    ) {
        val listener = object : Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {
                if (playbackState == Player.STATE_READY) {
                    playbackDiagnostic = null
                }
            }

            override fun onPlayerError(error: PlaybackException) {
                val prepared = lastPreparedStreamUrl ?: return
                val nextAttempt = playerRetryCount + 1
                playerRetryCount = nextAttempt
                val retryChannel = playingChannel?.source
                val retryProgram = playingCatchupProgram
                val retryStreamProgram = retryProgram?.shiftedForCatchup(catchupUrlAnchorOffsetMs)
                val catchupCandidateCount = if (retryChannel != null && retryProgram != null) {
                    viewModel.iptvRepository.getCatchupUrlCandidates(
                        retryChannel,
                        retryStreamProgram ?: retryProgram
                    ).size
                } else {
                    0
                }
                val maxRetryCount = if (retryProgram != null) {
                    (catchupCandidateCount - 1).coerceAtLeast(0).coerceAtMost(2)
                } else {
                    3
                }
                if (nextAttempt > maxRetryCount) {
                    playbackDiagnostic = PlaybackDiagnostic(
                        title = "Playback failed",
                        detail = "${error.errorCodeName}: ${classifyPlaybackError(error)}",
                        severity = PlaybackDiagnosticSeverity.Error,
                    )
                    System.err.println(
                        "[IPTV] Live playback failed after retries code=${error.errorCode} " +
                            "name=${error.errorCodeName} status=${httpResponseCode(error) ?: "-"} " +
                            "attempts=$maxRetryCount candidates=$catchupCandidateCount " +
                            "url=${redactPlaybackUrl(prepared)}"
                    )
                    return
                }
                val retryHeaders = retryChannel?.requestHeaders ?: lastPreparedHeaders
                coroutineScope.launch {
                    delay(350L * nextAttempt)
                    val retryStream = runCatching {
                        if (retryChannel != null) {
                            viewModel.resolvePlayableStreamUrl(
                                channel = retryChannel,
                                program = retryStreamProgram ?: retryProgram,
                                forceRefresh = true,
                                catchupAttempt = if (retryProgram != null) nextAttempt else 0
                            )
                        } else {
                            prepared
                        }
                    }.getOrElse { resolveError ->
                        playbackDiagnostic = PlaybackDiagnostic(
                            title = if (retryProgram != null) "Catch-up unavailable" else "Playback failed",
                            detail = resolveError.message ?: classifyPlaybackError(error),
                            severity = PlaybackDiagnosticSeverity.Error,
                        )
                        System.err.println(
                            "[IPTV] Retry resolve failed catchup=${retryProgram != null} " +
                                "code=${error.errorCodeName} reason=${resolveError.message}"
                        )
                        return@launch
                    }
                    System.err.println(
                        "[IPTV] Retrying live playback attempt=$nextAttempt " +
                            "code=${error.errorCodeName} status=${httpResponseCode(error) ?: "-"} " +
                            "candidates=$catchupCandidateCount url=${redactPlaybackUrl(retryStream)}"
                    )
                    playbackDiagnostic = PlaybackDiagnostic(
                        title = "Retrying source",
                        detail = "Attempt $nextAttempt/$maxRetryCount after ${classifyPlaybackError(error)}",
                        severity = PlaybackDiagnosticSeverity.Warning,
                    )
                    prepareStream(
                        stream = retryStream,
                        headers = retryHeaders,
                        resetRetry = false,
                        initialPositionMs = if (retryProgram != null) catchupInSegmentSeekMs else 0L,
                        drmInfo = retryChannel?.drmInfo,
                    )
                }
            }

        }
        exoPlayer.addListener(listener)
        onDispose { exoPlayer.removeListener(listener) }
    }

    // Default IPTV entry is the playlist/category rail, focused on Search.
    LaunchedEffect(visibleEnrichedState.value !== EnrichedChannels.Empty) {
        if (!isTouchDevice && visibleEnrichedState.value !== EnrichedChannels.Empty) {
            focusPlaylistSearch()
        }
    }

    LaunchedEffect(state.isConfigured, visibleEnrichedState.value) {
        if (!isTouchDevice && !state.isConfigured && visibleEnrichedState.value === EnrichedChannels.Empty) {
            delay(100L)
            runCatching { emptyStateButtonFocus.requestFocus() }
        }
    }

    BackHandler(enabled = searchOpen) { searchOpen = false }
    BackHandler(enabled = !searchOpen && variantPickerChannel != null) { variantPickerChannel = null }
    BackHandler(enabled = !searchOpen && isFullScreen && fullscreenGuideOpen) {
        fullscreenGuideOpen = false
    }
    BackHandler(enabled = !searchOpen && isFullScreen && !fullscreenGuideOpen) {
        if (playingCatchupProgram != null) {
            returnCatchupToLive()
        } else {
            exitFullScreenPlayback()
        }
    }
    BackHandler(enabled = !searchOpen && variantPickerChannel == null && !isFullScreen) {
        onBack()
    }

    val channelNumberExactName = remember(channelNumberBuffer, visibleChannels) {
        visibleChannels.firstOrNull { it.number.toString() == channelNumberBuffer }?.name
    }
    val channelNumberMatchCount = remember(channelNumberBuffer, visibleChannels) {
        if (channelNumberBuffer.isBlank()) {
            0
        } else {
            visibleChannels.count { it.number.toString().startsWith(channelNumberBuffer) }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LiveColors.Bg)
            .then(
                if (!isTouchDevice) {
                    Modifier.onPreviewKeyEvent { event ->
                        if (!searchOpen && event.type == KeyEventType.KeyDown && event.nativeKeyEvent.repeatCount == 0) {
                            digitForTvKeyCode(event.nativeKeyEvent.keyCode)?.let { digit ->
                                return@onPreviewKeyEvent handleChannelNumberDigit(digit)
                            }
                        }
                        if (searchOpen || isFullScreen || event.type != KeyEventType.KeyDown) {
                            return@onPreviewKeyEvent false
                        }
                        noteGuideUserNavigation()
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
                                        if (!state.isConfigured && state.snapshot.channels.isEmpty()) {
                                            focusZone = LiveTvFocusZone.CATEGORY_LIST
                                            runCatching { emptyStateButtonFocus.requestFocus() }
                                        } else {
                                            focusProviderSwitcher()
                                        }
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
                            LiveTvFocusZone.PROVIDER_SWITCHER -> false
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
                onAction = onNavigateToIptvSettings ?: onNavigateToSettings,
                isFocused = focusZone != LiveTvFocusZone.TOPBAR,
                focusRequester = emptyStateButtonFocus,
                onMoveUp = {
                    focusZone = LiveTvFocusZone.TOPBAR
                    topBarFocusIndex = topBarSelectedIndex(SidebarItem.TV, hasProfile).coerceIn(0, maxTopBarIndex)
                }
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
                    ProviderSelector(
                        providers = providerFilters,
                        selectedId = selectedProviderId,
                        onSelect = { id ->
                            noteGuideUserNavigation()
                            selectedProviderId = id
                            selectedCategoryId = "all"
                            focusedChannelId = null
                            epgPrefetchAnchorId = null
                        },
                        onMoveDown = { focusPlaylistSearch() },
                        modifier = Modifier.fillMaxWidth(),
                    )
                    EpgStatusStrip(
                        isLoading = guideLoadingInScope,
                        warning = state.snapshot.epgWarning,
                        matchedCount = matchedGuideCount,
                        totalChannels = guideStatusIds.size,
                        hasGuideSource = state.hasPotentialGuideSource,
                        modifier = Modifier.fillMaxWidth(),
                    )
                    MiniPlayerRow(
                        exoPlayer = exoPlayer,
                        channel = playingChannel,
                        clockTickMillis = guideClockMillis,
                        nowNext = currentNowNext,
                        onFavoriteToggle = { viewModel.toggleFavoriteChannel(it) },
                        favoriteSet = favSet,
                        onFullscreenClick = openFullScreenPlayer,
                        variantCount = playingChannel?.let { variantCountFor(it, variantGroups) } ?: 1,
                        onOpenVariants = playingChannel?.let { channel -> { openVariantPicker(channel) } },
                        compact = true,
                        modifier = Modifier.fillMaxWidth(),
                    )
                    TouchCategoryRail(
                        tree = visibleEnrichedState.value.tree,
                        selectedId = selectedCategoryId,
                        onSelect = { id ->
                            noteGuideUserNavigation()
                            selectedCategoryId = id
                        },
                        onOpenSearch = { searchOpen = true },
                        modifier = Modifier.fillMaxWidth(),
                    )
                    EpgGrid(
                        channels = guideChannels,
                        channelWindowOffset = normalizedGuideStart,
                        totalChannelCount = filteredChannels.size,
                        clockTickMillis = guideClockMillis,
                        nowNext = effectiveGuideNowNext,
                        epgLoadingChannelIds = state.epgLoadingChannelIds,
                        epgAttemptedChannelIds = state.epgAttemptedChannelIds,
                        isGuideBackfillLoading = false,
                        hasGuideSource = state.hasPotentialGuideSource,
                        selectedChannelId = selectedDisplayChannelId,
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
                        onChannelFocused = { channel -> commitFocusedChannel(channel) },
                        onChannelFavoriteToggle = { id -> viewModel.toggleFavoriteChannel(id) },
                        favorites = favSet,
                        variantCountFor = { channel -> variantCountFor(channel, variantGroups) },
                        onOpenVariants = { channel -> openVariantPicker(channel) },
                        onMoveLeftFromChannels = { focusPlaylistSearch() },
                        onEnterEpg = { channel -> focusEpg(channel.id) },
                        onExitEpg = { channel -> focusChannelList(channel?.id ?: focusedChannelId ?: playingChannelId) },
                        onRequestPreviousChannels = ::requestGuideWindowBefore,
                        onRequestNextChannels = ::requestGuideWindowAfter,
                        modifier = Modifier.fillMaxSize(),
                    )
                }
            } else Row(
                modifier = Modifier.fillMaxSize(),
            ) {
                CategorySidebar(
                    tree = visibleEnrichedState.value.tree,
                    selectedId = selectedCategoryId,
                    expanded = sidebarExpanded,
                    onSelect = { id ->
                        noteGuideUserNavigation()
                        selectedCategoryId = id
                    },
                    onOpenSearch = { searchOpen = true },
                    onHideCategory = { playlistId, groupName ->
                        noteGuideUserNavigation()
                        selectedCategoryId = "all"
                        viewModel.toggleHiddenGroup(playlistId, groupName)
                    },
                    onUnhideCategory = { playlistId, groupName ->
                        noteGuideUserNavigation()
                        viewModel.toggleHiddenGroup(playlistId, groupName)
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
                            ?.takeIf { id -> id in filteredChannelIndexById }
                        val target = remembered
                            ?: focusedChannelId?.takeIf { id -> id in filteredChannelIndexById }
                            ?: playingChannelId?.takeIf { id -> id in filteredChannelIndexById }
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
                    ProviderSelector(
                        providers = providerFilters,
                        selectedId = selectedProviderId,
                        onSelect = { id ->
                            noteGuideUserNavigation()
                            selectedProviderId = id
                            selectedCategoryId = "all"
                            focusedChannelId = null
                            epgPrefetchAnchorId = null
                        },
                        focusRequester = providerFocus,
                        onMoveUp = {
                            topBarFocusIndex = topBarSelectedIndex(SidebarItem.TV, hasProfile)
                                .coerceIn(0, maxTopBarIndex)
                            focusZone = LiveTvFocusZone.TOPBAR
                        },
                        onMoveDown = { focusPlaylistSearch() },
                        modifier = Modifier.fillMaxWidth(),
                    )
                    EpgStatusStrip(
                        isLoading = guideLoadingInScope,
                        warning = state.snapshot.epgWarning,
                        matchedCount = matchedGuideCount,
                        totalChannels = guideStatusIds.size,
                        hasGuideSource = state.hasPotentialGuideSource,
                        modifier = Modifier.fillMaxWidth(),
                    )
                    MiniPlayerRow(
                        exoPlayer = exoPlayer,
                        channel = playingChannel,
                        clockTickMillis = guideClockMillis,
                        nowNext = currentNowNext,
                        onFavoriteToggle = { viewModel.toggleFavoriteChannel(it) },
                        favoriteSet = favSet,
                        onFullscreenClick = openFullScreenPlayer,
                        variantCount = playingChannel?.let { variantCountFor(it, variantGroups) } ?: 1,
                        onOpenVariants = playingChannel?.let { channel -> { openVariantPicker(channel) } },
                        compact = compactTouchLayout,
                        modifier = Modifier.fillMaxWidth(),
                    )
                    EpgGrid(
                        channels = guideChannels,
                        channelWindowOffset = normalizedGuideStart,
                        totalChannelCount = filteredChannels.size,
                        clockTickMillis = guideClockMillis,
                        nowNext = effectiveGuideNowNext,
                        epgLoadingChannelIds = state.epgLoadingChannelIds,
                        epgAttemptedChannelIds = state.epgAttemptedChannelIds,
                        isGuideBackfillLoading = false,
                        hasGuideSource = state.hasPotentialGuideSource,
                        selectedChannelId = selectedDisplayChannelId,
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
                        onChannelFocused = { channel -> commitFocusedChannel(channel) },
                        onChannelFavoriteToggle = { id -> viewModel.toggleFavoriteChannel(id) },
                        favorites = favSet,
                        variantCountFor = { channel -> variantCountFor(channel, variantGroups) },
                        onOpenVariants = { channel -> openVariantPicker(channel) },
                        onMoveLeftFromChannels = { focusPlaylistSearch() },
                        onEnterEpg = { channel -> focusEpg(channel.id) },
                        onExitEpg = { channel -> focusChannelList(channel?.id ?: focusedChannelId ?: playingChannelId) },
                        onRequestPreviousChannels = ::requestGuideWindowBefore,
                        onRequestNextChannels = ::requestGuideWindowAfter,
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
                        if (fullscreenGuideOpen) {
                            when (ev.key) {
                                Key.Back, Key.Escape -> {
                                    fullscreenGuideOpen = false
                                    hudPokeSignal++
                                    true
                                }
                                else -> false
                            }
                        } else if (quickZapOpen) {
                            false
                        } else {
                            val firstPress = ev.nativeKeyEvent.repeatCount == 0
                            if (playingCatchupProgram != null) {
                                when (ev.key) {
                                    Key.Back, Key.Escape -> {
                                        if (firstPress) returnCatchupToLive()
                                        return@onPreviewKeyEvent true
                                    }
                                    Key.DirectionCenter, Key.Enter -> {
                                        if (firstPress) toggleCatchupPlayback()
                                        return@onPreviewKeyEvent true
                                    }
                                    Key.DirectionLeft -> {
                                        if (firstPress) seekCatchupBy(-CatchupSeekStepMs)
                                        return@onPreviewKeyEvent true
                                    }
                                    Key.DirectionRight -> {
                                        if (firstPress) seekCatchupBy(CatchupSeekStepMs)
                                        return@onPreviewKeyEvent true
                                    }
                                    else -> Unit
                                }
                                if (firstPress) {
                                    when (ev.nativeKeyEvent.keyCode) {
                                        AndroidKeyEvent.KEYCODE_MEDIA_PLAY_PAUSE,
                                        AndroidKeyEvent.KEYCODE_SPACE -> {
                                            toggleCatchupPlayback()
                                            return@onPreviewKeyEvent true
                                        }
                                        AndroidKeyEvent.KEYCODE_MEDIA_PLAY -> {
                                            exoPlayer.play()
                                            hudPokeSignal++
                                            return@onPreviewKeyEvent true
                                        }
                                        AndroidKeyEvent.KEYCODE_MEDIA_PAUSE -> {
                                            exoPlayer.pause()
                                            hudPokeSignal++
                                            return@onPreviewKeyEvent true
                                        }
                                        AndroidKeyEvent.KEYCODE_MEDIA_REWIND -> {
                                            seekCatchupBy(-CatchupSeekStepMs)
                                            return@onPreviewKeyEvent true
                                        }
                                        AndroidKeyEvent.KEYCODE_MEDIA_FAST_FORWARD -> {
                                            seekCatchupBy(CatchupSeekStepMs)
                                            return@onPreviewKeyEvent true
                                        }
                                    }
                                }
                            }
                            if (firstPress) {
                                digitForTvKeyCode(ev.nativeKeyEvent.keyCode)?.let { digit ->
                                    hudPokeSignal++
                                    return@onPreviewKeyEvent handleChannelNumberDigit(digit)
                                }
                                if (!isHudVisible) {
                                    if (ev.key in listOf(Key.DirectionUp, Key.DirectionDown, Key.DirectionLeft, Key.DirectionRight, Key.DirectionCenter, Key.Enter)) {
                                        hudPokeSignal++
                                        return@onPreviewKeyEvent true
                                    }
                                } else {
                                    when (ev.key) {
                                        Key.DirectionUp, Key.DirectionDown -> {
                                            quickZapOpen = true
                                            isHudVisible = false
                                            return@onPreviewKeyEvent true
                                        }
                                        Key.DirectionCenter, Key.Enter -> {
                                            openFullscreenGuide()
                                            return@onPreviewKeyEvent true
                                        }
                                        else -> Unit
                                    }
                                }
                            }
                            when (ev.key) {
                                Key.Back, Key.Escape -> { exitFullScreenPlayback(); true }
                                Key.DirectionUp -> { zap(+1); hudPokeSignal++; true }
                                Key.DirectionDown -> { zap(-1); hudPokeSignal++; true }
                                Key.DirectionCenter, Key.Enter -> { openFullscreenGuide(); true }
                                Key.DirectionLeft -> { hudPokeSignal++; false }
                                Key.DirectionRight -> { hudPokeSignal++; false }
                                else -> false
                            }
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
                            keepScreenOn = true
                            player = exoPlayer
                            useController = false
                            setKeepContentOnPlayerReset(true)
                        }
                    },
                    update = { view ->
                        view.keepScreenOn = true
                        if (view.player !== exoPlayer) {
                            view.player = exoPlayer
                        }
                    },
                    modifier = Modifier.fillMaxSize(),
                )
                if (isFullScreen && !fullscreenGuideOpen && !quickZapOpen) {
                    FullscreenHud(
                        channel = playingChannel,
                        nowNext = currentNowNext,
                        pokeSignal = hudPokeSignal,
                        isCatchupMode = playingCatchupProgram != null,
                        isPlaying = if (playingCatchupProgram != null) playerPlayWhenReady else playerIsPlaying,
                        playbackPositionMs = playerPositionMs,
                        playbackDurationMs = playerDurationMs,
                        onBackClick = if (isTouchDevice) {
                            {
                                if (playingCatchupProgram != null) {
                                    returnCatchupToLive()
                                } else {
                                    exitFullScreenPlayback()
                                }
                            }
                        } else {
                            null
                        },
                        onGuideClick = { openFullscreenGuide() },
                        onPlayPauseClick = { toggleCatchupPlayback() },
                        onGoLiveClick = { returnCatchupToLive() },
                        onVisibilityChanged = { isHudVisible = it },
                        modifier = Modifier,
                    )
                }
                FullscreenGuideOverlay(
                    visible = isFullScreen && fullscreenGuideOpen,
                    channel = guideChannel ?: playingChannel,
                    guide = guideForChannel(guideChannel ?: playingChannel),
                    selectedProgram = playingCatchupProgram,
                    isTouchDevice = isTouchDevice,
                    onDismiss = {
                        fullscreenGuideOpen = false
                        if (guideOpenedFromQuickZap) {
                            guideOpenedFromQuickZap = false
                            quickZapOpen = true
                        } else {
                            hudPokeSignal++
                        }
                    },
                    onProgramSelect = { program ->
                        val target = guideChannel ?: playingChannel
                        guideOpenedFromQuickZap = false
                        playProgramInFullscreen(program, target)
                    },
                    onLeftClick = {
                        fullscreenGuideOpen = false
                        quickZapOpen = true
                    },
                    modifier = Modifier,
                )
                QuickZapOverlay(
                    visible = isFullScreen && quickZapOpen,
                    currentChannel = playingChannel,
                    channels = filteredChannels,
                    nowNextMap = state.snapshot.nowNext,
                    categoriesTree = visibleEnrichedState.value.tree,
                    selectedCategoryId = selectedCategoryId,
                    onCategorySelected = { selectedCategoryId = it },
                    onDismiss = {
                        quickZapOpen = false
                        hudPokeSignal++
                    },
                    onChannelSelect = { channel ->
                        playingChannelId = channel.id
                        focusedChannelId = channel.id
                        epgPrefetchAnchorId = channel.id
                        playingCatchupProgram = null
                        catchupPlaybackOffsetMs = 0L
                        quickZapOpen = false
                        rememberedChannelByCategory[selectedCategoryId] = channel.id
                        hudPokeSignal++
                    },
                    onRightClick = { channel ->
                        guideChannel = channel
                        quickZapOpen = false
                        guideOpenedFromQuickZap = true
                        fullscreenGuideOpen = true
                    }
                )
            }
        }

        LaunchedEffect(isFullScreen, fullscreenGuideOpen, quickZapOpen, playingCatchupProgram) {
            if (isFullScreen && !fullscreenGuideOpen && !quickZapOpen) {
                delay(50L)
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
                channels = allDisplayChannels,
                nowNext = effectiveGuideNowNext,
                onDismiss = { searchOpen = false },
                onPick = { channel ->
                    selectedCategoryId = bestCategoryIdForChannel(channel, visibleEnrichedState.value.tree)
                    playingChannelId = channel.id
                    focusedChannelId = channel.id
                    epgPrefetchAnchorId = channel.id
                    searchOpen = false
                    focusChannelList(channel.id)
                },
            )
        }

        if (!searchOpen) {
            val pickerChannel = variantPickerChannel
            VariantPickerOverlay(
                channel = pickerChannel,
                variants = pickerChannel?.let { variantGroups[variantGroupKey(it)] }.orEmpty(),
                onDismiss = { variantPickerChannel = null },
                onPick = { playVariant(it) },
            )
        }

        ChannelNumberOverlay(
            buffer = channelNumberBuffer,
            matchCount = channelNumberMatchCount,
            exactChannelName = channelNumberExactName,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = contentTopPadding + 24.dp, end = 32.dp),
        )

        PlaybackDiagnosticBanner(
            diagnostic = playbackDiagnostic,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = if (isFullScreen) 72.dp else 24.dp),
        )
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

private fun classifyPlaybackError(error: PlaybackException): String {
    httpResponseCode(error)?.let { return "provider returned HTTP $it" }
    val name = error.errorCodeName.lowercase()
    return when {
        "timeout" in name -> "network timeout"
        "network" in name || "io" in name -> "network or provider error"
        "parser" in name || "manifest" in name -> "stream format issue"
        "decoder" in name || "audio" in name || "video" in name -> "device codec issue"
        else -> "source did not start"
    }
}

private fun httpResponseCode(error: PlaybackException): Int? {
    var cause: Throwable? = error
    while (cause != null) {
        if (cause is HttpDataSource.InvalidResponseCodeException) {
            return cause.responseCode
        }
        cause = cause.cause
    }
    return null
}

private fun redactPlaybackUrl(url: String): String {
    val withoutQuerySecrets = Regex(
        pattern = """(?i)([?&](?:username|user|uname|password|pass|pwd)=)[^&]+"""
    ).replace(url) { match -> "${match.groupValues[1]}***" }

    return LiveTvScreenRegexes.IPTV_URL_REDACT_REGEX
        .replace(withoutQuerySecrets) { match ->
            "${match.groupValues[1]}***/***${match.groupValues[4]}"
        }
        .take(260)
}

private tailrec fun Context.findActivity(): Activity? {
    return when (this) {
        is Activity -> this
        is ContextWrapper -> baseContext.findActivity()
        else -> null
    }
}
