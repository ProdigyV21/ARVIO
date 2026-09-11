@file:Suppress("UnsafeOptInUsageError")

package com.arflix.tv.ui.screens.tv.live

import com.arflix.tv.network.iptvProviderCooldownMs
import com.arflix.tv.network.IptvProviderRequestGuard
import com.arflix.tv.network.isIptvProviderRequestPaused

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.ActivityInfo
import com.arflix.tv.util.findActivity
import android.view.KeyEvent as AndroidKeyEvent
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material3.Text
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusGroup
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
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
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
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.res.stringResource
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
import com.arflix.tv.R
import com.arflix.tv.ui.theme.Pink
import com.arflix.tv.ui.theme.ArflixTypography
import com.arflix.tv.ui.theme.TextSecondary
import com.arflix.tv.data.model.IptvChannel
import com.arflix.tv.data.model.IptvNowNext
import com.arflix.tv.data.model.IptvProgram
import com.arflix.tv.data.model.IptvGuideHistory
import com.arflix.tv.data.model.MediaItem as ArvioMediaItem
import com.arflix.tv.data.model.Profile
import com.arflix.tv.data.model.PlaylistGroupKey
import com.arflix.tv.data.repository.IptvRepository
import com.arflix.tv.data.repository.IptvPlaybackTarget
import com.arflix.tv.data.repository.StalkerPortalSupport
import com.arflix.tv.ui.screens.tv.TvUiState
import com.arflix.tv.ui.screens.tv.TvViewModel
import com.arflix.tv.ui.screens.profile.PinEntryDialog
import com.arflix.tv.network.OkHttpProvider
import com.arflix.tv.ui.components.AppTopBar
import com.arflix.tv.ui.components.KeepScreenOn
import com.arflix.tv.ui.components.SidebarItem
import com.arflix.tv.ui.components.topBarFocusedItem
import com.arflix.tv.ui.components.topBarMaxIndex
import com.arflix.tv.ui.components.topBarSelectedIndex
import com.arflix.tv.ui.focus.mirrorHorizontalForRtl
import com.arflix.tv.util.LocalDeviceType
import com.arflix.tv.util.PinUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.Job
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit


private object LiveTvScreenRegexes {
    val IPTV_URL_REDACT_REGEX = Regex("""(?i)(/(?:live|movie|series|timeshift)/)([^/]+)/([^/]+)(/)""")
    val QUALITY_REMOVAL = Regex("""(?i)\b(?:4k|uhd|fhd|hd|sd|1080p|720p|60fps)\b""")
    val MULTI_SPACE = Regex("""\s+""")
    val QUERY_SECRETS = Regex("""(?i)([?&](?:username|user|uname|password|pass|pwd)=)[^&]+""")
}

private enum class LiveTvFocusZone {
    TOPBAR,
    PROVIDER_SWITCHER,
    CATEGORY_LIST,
    CHANNEL_LIST,
    EPG,
    SPORTS,
}

private sealed interface LockedGroupPinAction {
    data class OpenCategory(val categoryId: String, val groupKey: String) : LockedGroupPinAction
    data class ToggleLock(
        val playlistId: String,
        val groupName: String,
        val wasLocked: Boolean,
    ) : LockedGroupPinAction
}

private const val GuideInitialWindowRows = 18
private const val GuideMaxWindowRows = 42
private const val ChannelInitialLoadedRows = 144
private const val GuidePagedLoadStepRows = 192
private const val GuideVisibleFirstRows = 28
private const val GuideVisibleFirstRowsAllChannels = 18
private const val CatchupSeekStepMs = 30_000L

// Fullscreen zapping talks to the network on every step (stream resolve +
// prepare), so a held or bouncing channel key must not turn into a burst of
// requests. One step per this interval is still far faster than anyone zaps.
private const val MinZapIntervalMs = 150L
private const val CatchupUrlAnchorGranularityMs = 60_000L
private const val IptvPlaybackUserAgent = "VLC/3.0.20 LibVLC/3.0.20"
private const val VisibleGuidePastWindowMs = 48L * 60L * 60_000L
private const val VisibleGuideFutureWindowMs = 12L * 60L * 60_000L
private const val EpgGuideLookupTimeoutMs = 2_500L

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

internal fun selectPagedChannelsInProviderOrder(
    categoryId: String,
    providerWindow: List<IptvChannel>,
    favoriteChannels: List<IptvChannel>,
    recentChannels: List<IptvChannel>,
    limit: Int,
): List<IptvChannel> {
    val source = when (categoryId) {
        "fav" -> favoriteChannels
        "recent" -> recentChannels
        else -> providerWindow
    }
    // Compose may still request an index from the previous lazy-list snapshot
    // while paging replaces the backing list. Never expose a live SubList view:
    // its size can change underneath the item provider and crash older TV ART.
    return source.take(limit.coerceAtLeast(0))
}

private fun resolvePagedGroup(
    categoryId: String,
    groupCounts: List<Triple<String, String, Int>>,
    tree: LiveCategoryTree,
): Pair<String, String>? {
    return groupCounts
        .firstOrNull { (playlistId, groupTitle, _) ->
            playlistGroupCategoryId(playlistId, groupTitle) == categoryId
        }
        ?.let { (playlistId, groupTitle, _) -> playlistId to groupTitle }
        ?: tree.byId(categoryId)
            ?.takeIf { it.playlistId != null && it.playlistGroupName != null }
            ?.let { it.playlistId!! to it.playlistGroupName!! }
}

/**
 * Reads only the channel rows needed for the current viewport. Keeping this out
 * of [LiveTvScreen] also keeps the composable below ART's method-size limit on TV.
 */
internal fun loadPagedChannelWindow(
    repository: IptvRepository,
    categoryId: String,
    pageLimit: Int,
    pagedTotal: Int,
    groupCounts: List<Triple<String, String, Int>>,
    tree: LiveCategoryTree,
    /** Ordered — this is the favourites rail's display order, so a Set would lose it. */
    favorites: List<String>,
    recents: List<String>,
    excludedGroups: Set<String> = emptySet(),
): List<IptvChannel> {
    val favoriteChannels = if (categoryId == "fav") {
        val favoriteRank = favorites.withIndex().associate { (index, id) -> id to index }
        repository.pagedChannelsByIds(favorites)
            .filterNot { isAdultGroup(it.group, it.name) }
            // pagedChannelsByIds returns SQLite row order, which has nothing to do with the
            // user's favourites order — restore it so "move up/down" is actually visible.
            .sortedBy { favoriteRank[it.id] ?: Int.MAX_VALUE }
    } else {
        emptyList()
    }
    val recentChannels = if (categoryId == "recent") {
        repository.pagedChannelsByIds(recents).filterNot { isAdultGroup(it.group, it.name) }
    } else {
        emptyList()
    }

    fun scanCategoryWindow(targetGroupTitle: String?): List<IptvChannel> {
        if (!categoryId.startsWith("grp:")) return emptyList()
        val targetPlaylistId = playlistIdFromGroupCategoryId(categoryId)
        val targetGroupKey = looseIptvGroupKey(targetGroupTitle)
        val targetCompactGroupKey = compactIptvGroupKey(targetGroupTitle)
        val out = ArrayList<IptvChannel>(pageLimit)
        var offset = 0
        val chunkSize = 1_000
        while (out.size < pageLimit && offset < pagedTotal) {
            val chunk = repository.pagedChannelWindow(null, null, offset, chunkSize)
            if (chunk.isEmpty()) break
            chunk.forEach { channel ->
                val rawPlaylistId = channelPlaylistId(channel.id)
                val categoryMatches = playlistGroupCategoryId(rawPlaylistId, channel.group) == categoryId
                val samePlaylist = targetPlaylistId == null || rawPlaylistId == targetPlaylistId
                val looseGroupMatches = samePlaylist && targetGroupKey.isNotBlank() &&
                    looseIptvGroupKey(channel.group) == targetGroupKey
                val compactGroupMatches = samePlaylist && targetCompactGroupKey.isNotBlank() &&
                    compactIptvGroupKey(channel.group) == targetCompactGroupKey
                if (categoryMatches || looseGroupMatches || compactGroupMatches) {
                    out += channel
                    if (out.size >= pageLimit) return out
                }
            }
            offset += chunk.size
        }
        return out
    }

    val providerWindow = when (categoryId) {
        "fav", "recent" -> emptyList()
        "all" -> repository.pagedChannelWindow(
            null,
            null,
            // Pagination grows a prefix. An anchored SQL offset permanently hides earlier rows.
            0,
            pageLimit,
            excludedGroups,
        )
        else -> {
            val resolvedGroup = resolvePagedGroup(categoryId, groupCounts, tree)
                ?: return emptyList()
            val playlistId = resolvedGroup.first
            val groupTitle = resolvedGroup.second
            val exact = repository.pagedChannelWindow(
                playlistId,
                groupTitle,
                0,
                pageLimit,
                excludedGroups,
            )
            val byGroup = if (exact.isEmpty()) {
                repository.pagedChannelWindow(
                    null,
                    groupTitle,
                    0,
                    pageLimit,
                )
            } else {
                exact
            }
            if (byGroup.isEmpty()) scanCategoryWindow(groupTitle) else byGroup
        }
    }
    return selectPagedChannelsInProviderOrder(
        categoryId = categoryId,
        providerWindow = providerWindow,
        favoriteChannels = favoriteChannels,
        recentChannels = recentChannels,
        limit = pageLimit,
    )
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

internal fun nextGuidePageLimit(loaded: Int, requested: Int, total: Int): Int =
    maxOf(requested, loaded + GuidePagedLoadStepRows).coerceAtMost(total)

private fun EnrichedChannel.hasGuideIdentity(): Boolean =
    !source.epgId.isNullOrBlank() || !source.tvgName.isNullOrBlank()

private fun IptvNowNext?.hasGuideData(): Boolean =
    this != null &&
        (now != null || next != null || later != null || upcoming.isNotEmpty() || recent.isNotEmpty())

private fun isSafePlaybackHeader(name: String, value: String): Boolean {
    return name.isNotBlank() &&
        value.isNotBlank() &&
        name.all { ch ->
            ch.code in 33..126 &&
                ch !in setOf('(', ')', '<', '>', '@', ',', ';', ':', '\\', '"', '/', '[', ']', '?', '=', '{', '}')
        } &&
        value.all { ch -> ch == '\t' || ch.code in 32..126 }
}

private fun Map<String, String>.safePlaybackHeaders(): Map<String, String> {
    if (isEmpty()) return emptyMap()
    return filter { (name, value) -> isSafePlaybackHeader(name.trim(), value.trim()) }
        .mapKeys { (name, _) -> name.trim() }
        .mapValues { (_, value) -> value.trim() }
}

private fun EnrichedChannel.guideFallbackKeys(): List<String> {
    val playlistId = StalkerPortalSupport.playlistIdFromChannelId(id)
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
            .replace(LiveTvScreenRegexes.QUALITY_REMOVAL, " ")
            .replace(LiveTvScreenRegexes.MULTI_SPACE, " ")
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
    Quality.UNKNOWN -> 0
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
private fun LiveTvRenderBoundary(content: @Composable () -> Unit) {
    content()
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
    onNavigateToDetails: (com.arflix.tv.data.model.MediaType, Int) -> Unit = { _, _ -> },
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
    // Hebrew/Arabic mirror the layout; the D-pad handlers below are written for
    // LTR, so physical Left/Right are swapped through mirrorHorizontalForRtl.
    val isRtl = LocalLayoutDirection.current == LayoutDirection.Rtl
    val deviceType = LocalDeviceType.current
    val isTouchDevice = deviceType.isTouchDevice()
    val useTouchRail = isTouchDevice && configuration.smallestScreenWidthDp < 600
    val miniPlayerLayout = liveTvMiniPlayerLayout(
        isTouchDevice = isTouchDevice,
        smallestScreenWidthDp = configuration.smallestScreenWidthDp,
        screenWidthDp = configuration.screenWidthDp,
        screenHeightDp = configuration.screenHeightDp,
    )
    val compactTouchLayout = isTouchDevice && configuration.screenWidthDp < 900
    val landscapeCompactMiniPlayer = miniPlayerLayout == LiveTvMiniPlayerLayout.LANDSCAPE_COMPACT
    val showTopBar = !isTouchDevice
    val contentTopPadding = if (showTopBar) LiveDims.ContentTopInset else 0.dp
    val coroutineScope = rememberCoroutineScope()
    val guideClockMillis by produceState(initialValue = System.currentTimeMillis()) {
        while (true) {
            delay(30_000L)
            value = System.currentTimeMillis()
        }
    }
    var selectedCategoryId by rememberSaveable {
        mutableStateOf(state.tvSession.lastGroupName.takeIf { it.isNotBlank() } ?: "all")
    }
    var startupCategoryApplied by rememberSaveable { mutableStateOf(false) }
    var selectedProviderId by rememberSaveable { mutableStateOf("all") }
    val categoryScope = "${currentProfile?.id}|$selectedProviderId|$selectedCategoryId"
    val recents = remember { mutableStateOf<LinkedHashSet<String>>(LinkedHashSet()) }
    val favSet = remember(state.snapshot.favoriteChannels) { state.snapshot.favoriteChannels.toSet() }
    // The ordered list, kept separately from favSet: a Set compares equal after a reorder,
    // so anything keyed on favSet alone never notices "move up/down" and the favourites
    // rail keeps its old order.
    val favoriteOrderIds = state.snapshot.favoriteChannels
    val favoriteRank = remember(favoriteOrderIds) {
        favoriteOrderIds.withIndex().associate { (index, id) -> id to index }
    }
    val hiddenGroupSet = remember(state.snapshot.hiddenGroups) { state.snapshot.hiddenGroups.toSet() }
    val lockedGroupSet = remember(state.lockedGroups) { state.lockedGroups.toSet() }
    var unlockedGroupKeys by remember { mutableStateOf(emptySet<String>()) }
    val restrictedGroupSet = remember(lockedGroupSet, unlockedGroupKeys) {
        lockedGroupSet - unlockedGroupKeys
    }
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
    var pagedLoadedLimit by rememberSaveable { mutableIntStateOf(ChannelInitialLoadedRows) }
    val pagedLimitsByCategory = remember { LinkedHashMap<String, Int>() }
    var pagedLimitScope by rememberSaveable { mutableStateOf("") }
    var lastKnownPagedTotal by rememberSaveable { mutableIntStateOf(0) }
    var lastKnownPlaylistGroupCounts by remember {
        mutableStateOf<List<Triple<String, String, Int>>>(emptyList())
    }
    LaunchedEffect(categoryScope) {
        if (pagedLimitScope != categoryScope) {
            if (pagedLimitScope.isNotEmpty()) pagedLimitsByCategory[pagedLimitScope] = pagedLoadedLimit
            pagedLoadedLimit = pagedLimitsByCategory.remove(categoryScope) ?: ChannelInitialLoadedRows
            pagedLimitScope = categoryScope
            while (pagedLimitsByCategory.size > 16) pagedLimitsByCategory.remove(pagedLimitsByCategory.keys.first())
        }
    }
    LaunchedEffect(
        state.snapshot.channels,
        hiddenGroupSet,
        state.snapshot.groupOrder,
        // Ordered list, not favSet: the body sorts favourites by their stored rank, and a
        // Set key compares equal after a reorder so "move up/down" would never rebuild.
        favoriteOrderIds,
    ) {
        val snapshot = state.snapshot.channels
        var pagedTotal = withContext(Dispatchers.IO) {
            if (viewModel.iptvRepository.pagedChannelsReady()) {
                viewModel.iptvRepository.pagedChannelCount(null)
            } else {
                0
            }
        }
        // Paint the available startup snapshot before waiting for a large paged
        // store to finish indexing. The upgrade poll below used to hold even a
        // complete small playlist for 20 x 250 ms, leaving the guide empty for
        // five seconds despite all channels already being cached.
        if (snapshot.isNotEmpty() && pagedTotal <= 10_000) {
            val fastValue = withContext(Dispatchers.Default) {
                buildFastStartupChannelState(
                    channels = snapshot,
                    favorites = favSet,
                    recents = recents.value,
                    hiddenGroups = hiddenGroupSet,
                    groupOrder = state.snapshot.groupOrder,
                )
            }
            enrichedState.value = fastValue
        }
        if (snapshot.size in 1..500 && pagedTotal <= 10_000) {
            var attempt = 0
            while (attempt < 20 && pagedTotal <= 10_000) {
                delay(250L)
                pagedTotal = withContext(Dispatchers.IO) {
                    if (viewModel.iptvRepository.pagedChannelsReady()) {
                        viewModel.iptvRepository.pagedChannelCount(null)
                    } else {
                        0
                    }
                }
                if (pagedTotal > 10_000) {
                    System.err.println("[IPTV-PagedUI] paged store became ready after ${attempt + 1} checks total=$pagedTotal snapshot=${snapshot.size}")
                }
                attempt++
            }
        }
        if (pagedTotal > 10_000) {
            lastKnownPagedTotal = pagedTotal
        } else if (lastKnownPagedTotal > 10_000) {
            // The paged channel store can briefly report "not ready" during a
            // category switch even though the full IPTV index was already shown.
            // Do not let that transient state collapse the guide back to the
            // tiny startup snapshot; keep serving category windows from the
            // paged path using the last verified total.
            pagedTotal = lastKnownPagedTotal
        }
        System.err.println("[IPTV-PagedUI] snapshot=${snapshot.size} pagedTotal=$pagedTotal loadedLimit=$pagedLoadedLimit")
        if (pagedTotal > 10_000) {
            val storeRevision = withContext(Dispatchers.IO) {
                viewModel.iptvRepository.pagedChannelStoreUpdatedAtMs()
            }
            val signature = buildString {
                append("paged:")
                append(pagedTotal)
                append(":all")
                append(':')
                append(storeRevision)
                append(':')
                append(hiddenGroupSet.hashCode())
                append(':')
                append(state.snapshot.groupOrder.hashCode())
            }
            if (viewModel.cachedChannelsSignature == signature &&
                viewModel.cachedEnrichedChannels is EnrichedChannels
            ) {
                enrichedState.value = viewModel.cachedEnrichedChannels as EnrichedChannels
                return@LaunchedEffect
            }
            // The base shell stays deliberately small and stable. The selected
            // category owns its own growing window below; tying this shell to
            // focus/session/recents caused every channel tune to rebuild it.
            val pageLimit = ChannelInitialLoadedRows
            val freshGroupCounts = withContext(Dispatchers.IO) { viewModel.iptvRepository.pagedPlaylistGroupCounts() }
            val groupCounts = if (freshGroupCounts.isNotEmpty()) {
                lastKnownPlaylistGroupCounts = freshGroupCounts
                freshGroupCounts
            } else {
                lastKnownPlaylistGroupCounts
            }
            if (freshGroupCounts.isEmpty() && groupCounts.isNotEmpty()) {
                System.err.println("[IPTV-PagedUI] using cached group counts while paged store refreshes")
            }
            val window = withContext(Dispatchers.IO) {
                loadPagedChannelWindow(
                    repository = viewModel.iptvRepository,
                    categoryId = "all",
                    pageLimit = pageLimit,
                    pagedTotal = pagedTotal,
                    groupCounts = groupCounts,
                    tree = enrichedState.value.tree,
                    favorites = favoriteOrderIds,
                    recents = recents.value.toList().asReversed(),
                )
            }
            val value = withContext(Dispatchers.Default) {
                buildPagedStartupChannelState(
                    channels = window,
                    totalChannelCount = pagedTotal,
                    playlistGroupCounts = groupCounts,
                    favorites = favSet,
                    recents = recents.value,
                    hiddenGroups = hiddenGroupSet,
                    groupOrder = state.snapshot.groupOrder,
                )
            }
            enrichedState.value = value
            viewModel.cachedEnrichedChannels = value
            viewModel.cachedChannelsSignature = signature
            System.err.println(
                "[IPTV-FirstPaint] rows=${window.size} total=$pagedTotal category=all revision=$storeRevision"
            )
            return@LaunchedEffect
        }
        if (snapshot.isEmpty()) {
            if (state.isConfigured && enrichedState.value !== EnrichedChannels.Empty) {
                System.err.println(
                    "[IPTV-UI] Skipping transient empty snapshot; reusing previous enriched channels"
                )
                return@LaunchedEffect
            }
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
            snapshot.mapIndexed { idx, ch -> ch.enrich(idx + 1) }
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
        val fullAllCount = current.tree.countForCategory("all") ?: current.all.size
        if (fullAllCount > current.all.size) {
            val updatedTop = current.tree.top.map { category ->
                when (category.id) {
                    "fav" -> category.copy(count = favSet.size)
                    "recent" -> category.copy(count = recents.value.size)
                    else -> category
                }
            }
            if (updatedTop != current.tree.top) {
                val updated = current.copy(tree = current.tree.copy(top = updatedTop))
                enrichedState.value = updated
                viewModel.cachedEnrichedChannels = updated
            }
            return@LaunchedEffect
        }
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

    val providerFilters = remember(state.config, enrichedState.value.all, lastKnownPlaylistGroupCounts) {
        buildTvProviderFilters(state.config, enrichedState.value.all, lastKnownPlaylistGroupCounts)
    }
    val playlistCategorySections = remember(state.config, enrichedState.value.tree.global.categories, hiddenGroupSet) {
        buildPlaylistCategorySections(state.config, enrichedState.value.tree.global.categories, hiddenGroupSet)
    }
    LaunchedEffect(playlistCategorySections, selectedProviderId) {
        if (playlistCategorySections.isNotEmpty() && selectedProviderId != "all") {
            selectedProviderId = "all"
        }
    }
    LaunchedEffect(providerFilters, selectedProviderId) {
        if (providerFilters.isEmpty() || providerFilters.none { it.id == selectedProviderId }) {
            selectedProviderId = "all"
        }
    }

    var quickGuideRows by remember(currentProfile?.id, selectedProviderId, hiddenGroupSet, restrictedGroupSet) {
        mutableStateOf(mapOf("fav" to emptyList<EnrichedChannel>(), "recent" to emptyList<EnrichedChannel>()))
    }
    LaunchedEffect(currentProfile?.id, enrichedState.value.all, favoriteOrderIds, recents.value, hiddenGroupSet,
        restrictedGroupSet, selectedProviderId, state.config, lastKnownPagedTotal, state.snapshot.loadedAt) {
        val candidates = if (lastKnownPagedTotal > 10_000) withContext(Dispatchers.IO) {
            viewModel.iptvRepository.pagedChannelsByIds((favoriteOrderIds + recents.value).distinct())
                .mapIndexed { index, channel -> channel.enrichForFastStartup(index + 1) }
        } else enrichedState.value.all
        quickGuideRows = withContext(Dispatchers.Default) {
            quickGuideChannels(candidates.filter(providerMatcher(selectedProviderId, state.config)),
                favoriteOrderIds, recents.value, hiddenGroupSet, restrictedGroupSet, lastKnownPagedTotal <= 8_000)
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
        restrictedGroupSet,
    ) {
        val current = enrichedState.value
        if (current === EnrichedChannels.Empty) {
            visibleEnrichedState.value = EnrichedChannels.Empty
            return@LaunchedEffect
        }
        if (selectedProviderId == "all") {
            val index = withContext(Dispatchers.Default) {
                buildCategoryIndex(current.all, hiddenGroupSet, restrictedGroupSet)
            }
            visibleEnrichedState.value = current.copy(index = index)
            return@LaunchedEffect
        }
        val visibleChannels = withContext(Dispatchers.Default) {
            current.all.filter(providerMatcher(selectedProviderId, state.config))
        }
        val index = withContext(Dispatchers.Default) {
            buildCategoryIndex(visibleChannels, hiddenGroupSet, restrictedGroupSet)
        }
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
    LaunchedEffect(hiddenGroupSet, selectedCategoryId, visibleEnrichedState.value.tree, favSet) {
        val tree = visibleEnrichedState.value.tree
        if (selectedCategoryId == "fav" && favSet.isEmpty()) {
            selectedCategoryId = "all"
        } else if (tree.top.isNotEmpty() && selectedCategoryId != "all" &&
            (tree.byId(selectedCategoryId) == null || tree.hidden.categories.any { it.id == selectedCategoryId })
        ) {
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
    val topBarFocusRequester = remember { FocusRequester() }
    var lastGuideUserNavigationAt by remember { mutableLongStateOf(0L) }
    fun noteGuideUserNavigation() {
        lastGuideUserNavigationAt = System.currentTimeMillis()
    }
    fun isGuideUserNavigating(): Boolean =
        System.currentTimeMillis() - lastGuideUserNavigationAt < 2_500L

    // Category switches are served from prebuilt buckets. Favorites and
    // recents remain ordered dynamic lists, but they are simple id lookups.
    val filteredChannelsState = remember { mutableStateOf<List<EnrichedChannel>>(emptyList()) }
    var filteredChannelsCategoryKey by remember { mutableStateOf<String?>(null) }
    var filteredChannelsScopeKey by remember { mutableStateOf(categoryScope) }
    val recentsFilterKey = if (selectedCategoryId == "recent") recents.value else Unit
    LaunchedEffect(
        visibleEnrichedState.value.index,
        visibleEnrichedState.value.tree,
        selectedCategoryId,
        // Ordered list, not favSet: channelsFor() below emits favourites in the order it
        // is handed, but a Set key compares equal after a reorder, so this effect never
        // re-ran and "move up/down" left the rail visually unchanged.
        favoriteOrderIds,
        recentsFilterKey,
        quickGuideRows,
        pagedLoadedLimit,
        state.snapshot.sortOrder,
        hiddenGroupSet,
        restrictedGroupSet,
        lastKnownPagedTotal,
        lastKnownPlaylistGroupCounts,
    ) {
        val tree = visibleEnrichedState.value.tree
        val categoryCount = tree.countForCategory(selectedCategoryId) ?: 0
        if (selectedCategoryId == "fav" || selectedCategoryId == "recent") {
            filteredChannelsCategoryKey = selectedCategoryId
            filteredChannelsScopeKey = categoryScope
            filteredChannelsState.value = quickGuideRows[selectedCategoryId].orEmpty()
            return@LaunchedEffect
        }
        var result = withContext(Dispatchers.Default) {
            visibleEnrichedState.value.index.channelsFor(
                categoryId = selectedCategoryId,
                favorites = state.snapshot.favoriteChannels,
                recents = recents.value,
            )
        }
        val expectedWindowSize = minOf(categoryCount, pagedLoadedLimit).coerceAtLeast(0)
        val needsPagedWindow = lastKnownPagedTotal > 10_000 &&
            categoryCount > 0 &&
            result.size < expectedWindowSize &&
            (selectedCategoryId == "all" ||
                selectedCategoryId == "fav" ||
                selectedCategoryId == "recent" ||
                selectedCategoryId.startsWith("grp:"))
        if (needsPagedWindow) {
            // Keep the full category tree stable and read only the selected
            // window. Previously the outer channel-state effect and this
            // effect both queried/rebuilt the same category, producing several
            // seconds of main-thread recomposition on a 50k playlist.
            val directChannels = withContext(Dispatchers.IO) {
                loadPagedChannelWindow(
                    repository = viewModel.iptvRepository,
                    categoryId = selectedCategoryId,
                    pageLimit = pagedLoadedLimit,
                    pagedTotal = lastKnownPagedTotal,
                    groupCounts = lastKnownPlaylistGroupCounts,
                    tree = tree,
                    favorites = favoriteOrderIds,
                    recents = recents.value.toList().asReversed(),
                    excludedGroups = hiddenGroupSet + restrictedGroupSet,
                )
            }
            if (directChannels.isNotEmpty()) {
                System.err.println(
                    "[IPTV-PagedWindow] loaded category=$selectedCategoryId " +
                        "rows=${directChannels.size}/$categoryCount"
                )
                result = withContext(Dispatchers.Default) {
                    directChannels.mapIndexed { index, channel -> channel.enrichForFastStartup(index + 1) }
                }
            }
        }
        if (result.isEmpty() &&
            categoryCount > 0 &&
            selectedCategoryId != "fav" && selectedCategoryId != "recent" &&
            filteredChannelsCategoryKey == selectedCategoryId &&
            filteredChannelsState.value.isNotEmpty()
        ) {
            System.err.println(
                "[IPTV-PagedWindow] keeping previous filtered rows while category window rebuilds " +
                    "category=$selectedCategoryId count=$categoryCount"
            )
            result = filteredChannelsState.value
        }
        filteredChannelsCategoryKey = selectedCategoryId
        // Publish the scope with its rows, not with the pending selection:
        // outgoing one-row favorites would clamp the restored All position.
        filteredChannelsScopeKey = categoryScope
        filteredChannelsState.value = prepareGuideChannels(
            result, selectedCategoryId, state.snapshot.sortOrder, hiddenGroupSet, restrictedGroupSet
        )
    }
    val visibleChannels = visibleEnrichedState.value.all
    val accessibleVisibleChannels = remember(visibleChannels, restrictedGroupSet) {
        if (restrictedGroupSet.isEmpty()) visibleChannels
        else visibleChannels.filterNot { isRestrictedPlaylistGroup(it, restrictedGroupSet) }
    }
    // Variant grouping + collapsing + index building are O(channels). Doing them
    // synchronously in composition froze the main thread for ~10s on very large
    // playlists (50k+ channels) — the cause of janky navigation AND live-TV
    // buffering (a frozen main thread starves the player) AND the guide appearing
    // to "not load" (arriving EPG state couldn't be rendered while frozen).
    // Compute them on a background dispatcher and publish via state. Downstream
    // code tolerates an empty map/list for the one frame before this fills.
    // For very large playlists, building variant groups + collapsed copies creates
    // several more full-size (50k+) collections. With the device heap capped at
    // 384MB that tips it into a blocking-GC spiral (multi-second main-thread freezes
    // = janky nav + live-TV buffering + the guide unable to render). Above this
    // threshold we skip variant work entirely and show channels uncollapsed, reusing
    // the existing lists (no extra copies). Smaller lists keep variant collapsing.
    val variantCollapseLimit = 8_000
    val variantGroupsState = remember { mutableStateOf<Map<String, List<EnrichedChannel>>>(emptyMap()) }
    val allDisplayChannelsState = remember { mutableStateOf<List<EnrichedChannel>>(emptyList()) }
    LaunchedEffect(accessibleVisibleChannels) {
        if (accessibleVisibleChannels.isEmpty()) {
            variantGroupsState.value = emptyMap()
            allDisplayChannelsState.value = emptyList()
            return@LaunchedEffect
        }
        if (lastKnownPagedTotal > variantCollapseLimit || accessibleVisibleChannels.size > variantCollapseLimit) {
            variantGroupsState.value = emptyMap()
            allDisplayChannelsState.value = accessibleVisibleChannels
            return@LaunchedEffect
        }
        val groups = withContext(Dispatchers.Default) { buildVariantGroups(accessibleVisibleChannels) }
        val collapsed = withContext(Dispatchers.Default) { collapseChannelVariants(accessibleVisibleChannels, groups) }
        variantGroupsState.value = groups
        allDisplayChannelsState.value = collapsed
    }
    val variantGroups = variantGroupsState.value
    val allDisplayChannels = allDisplayChannelsState.value

    val filteredChannelsCollapsedState = remember { mutableStateOf<List<EnrichedChannel>>(emptyList()) }
    val filteredChannelIndexState = remember { mutableStateOf<Map<String, Int>>(emptyMap()) }
    var displayedChannelsCategoryKey by remember { mutableStateOf<String?>(null) }
    LaunchedEffect(filteredChannelsState.value, filteredChannelsCategoryKey, variantGroups) {
        val categoryKey = filteredChannelsCategoryKey
        val source = filteredChannelsState.value
        // No variant groups (large list) → reuse the source list as-is, no extra copy.
        val collapsed = if (variantGroups.isEmpty() || categoryKey == "fav" || categoryKey == "recent") {
            source
        } else {
            withContext(Dispatchers.Default) { collapseChannelVariants(source, variantGroups) }
        }
        val index = withContext(Dispatchers.Default) {
            HashMap<String, Int>(collapsed.size).apply {
                collapsed.forEachIndexed { idx, channel -> put(channel.id, idx) }
            }
        }
        filteredChannelsCollapsedState.value = collapsed
        filteredChannelIndexState.value = index
        displayedChannelsCategoryKey = categoryKey
    }
    val filteredChannels = if (displayedChannelsCategoryKey == selectedCategoryId) filteredChannelsCollapsedState.value else emptyList()
    val filteredChannelIndexById = if (displayedChannelsCategoryKey == selectedCategoryId) filteredChannelIndexState.value else emptyMap()
    val selectedCategoryTotalCount = remember(visibleEnrichedState.value.tree, selectedCategoryId, filteredChannels.size) {
        if (selectedCategoryId == "fav" || selectedCategoryId == "recent") filteredChannels.size
        else visibleEnrichedState.value.tree.countForCategory(selectedCategoryId)
            ?.takeIf { it > 0 }
            ?: filteredChannels.size
    }
    val baseVisibleChannelsById = visibleEnrichedState.value.index.byId
    val visibleChannelsById = remember(baseVisibleChannelsById, filteredChannels) {
        if (filteredChannels.all { it.id in baseVisibleChannelsById }) {
            baseVisibleChannelsById
        } else {
            LinkedHashMap<String, EnrichedChannel>(baseVisibleChannelsById.size + filteredChannels.size).apply {
                putAll(baseVisibleChannelsById)
                filteredChannels.forEach { channel -> put(channel.id, channel) }
            }
        }
    }
    // Playing channel — default to the one we were navigated to, else the first
    // channel of the first non-empty category.
    val rememberedChannelByCategory = remember { mutableMapOf<String, String>() }
    var playingChannelId by rememberSaveable { mutableStateOf<String?>(initialChannelId) }
    // The channel we were on before the current one. Tracked in a single place
    // on purpose: five different paths change the channel (zapping, number
    // entry, the quick-zap list, the guide and the HUD buttons), and a
    // per-path copy would leave the toggle stale on whichever path was missed.
    var previousChannelId by rememberSaveable { mutableStateOf<String?>(null) }
    var lastTunedChannelId by rememberSaveable { mutableStateOf<String?>(initialChannelId) }
    KeepScreenOn(active = playingChannelId != null)
    // Treat the entire Live TV surface as latency-sensitive. Waiting until the
    // first channel starts leaves a gap where a large XMLTV backfill can claim
    // the database and make the guide appear frozen during initial navigation.
    DisposableEffect(viewModel) {
        viewModel.setLiveTvPlaybackActive(true)
        onDispose { viewModel.setLiveTvPlaybackActive(false) }
    }
    // Open on the channel the user last watched. The session already persists
    // lastChannelId, but nothing consumed it on entry, so Live TV always
    // started at the top of the list. Rules live in LiveTvStartup so they are
    // unit tested rather than only verifiable on a device.
    val startupChannelIds = remember(state.snapshot.channels) {
        LiveTvStartup.channelIds(state.snapshot.channels)
    }
    val resumeChannelId = LiveTvStartup.resumeChannelId(
        explicitChannelId = initialChannelId,
        lastChannelId = state.tvSession.lastChannelId,
        availableChannelIds = startupChannelIds,
    )
    var focusedChannelId by rememberSaveable { mutableStateOf<String?>(resumeChannelId) }
    var focusedProgramme by remember { mutableStateOf<Pair<EnrichedChannel, IptvProgram>?>(null) }
    // The focused row's channel object, reported by the row itself on focus. Not saveable —
    // it is rebuilt on the next focus event, and only the id needs to survive process death.
    // Only event handlers need the current row; keep it separate from settled UI selection.
    val focusedChannelObject = remember { arrayOfNulls<EnrichedChannel>(1) }
    var epgPrefetchAnchorId by rememberSaveable { mutableStateOf<String?>(resumeChannelId) }
    var startupChannelApplied by rememberSaveable(selectedProviderId) { mutableStateOf(false) }
    var playingCatchupProgram by remember { mutableStateOf<IptvProgram?>(null) }
    var catchupPlaybackOffsetMs by remember { mutableLongStateOf(0L) }
    val focusCommitScope = rememberCoroutineScope()
    val pendingFocusCommit = remember { arrayOf<Pair<String, String>?>(null) }
    val focusCommitJob = remember { arrayOf<Job?>(null) }
    fun commitFocusedChannel(channel: EnrichedChannel) {
        // Recorded immediately, ahead of the debounce below: the long-press gesture needs
        // the focused row's actual channel object. Resolving it from
        // visibleEnrichedState.index.byId instead returns null for any favourite outside
        // the paged window, so the menu silently did nothing on those rows while the mouse
        // path — which gets the object straight from the row — still worked.
        focusedChannelObject[0] = channel
        pendingFocusCommit[0] = channel.id to categoryScope
        focusCommitJob[0]?.cancel()
        focusCommitJob[0] = focusCommitScope.launch {
            // Settle window before committing focus. Each commit fans out into the
            // EPG pipeline (per-channel HTTP fetch + JSON parse + guide merges) and a
            // wide recomposition. At 140ms the commit fired on EVERY step of a held
            // d-pad scroll, allocating millions of objects per sweep and dragging the
            // heap toward the cap (measured: 2s+ frames, 273 blocking GCs in 40
            // presses). 450ms skips the intermediate rows during continuous scrolling
            // and only commits where the user actually stops; visible focus highlight
            // still moves instantly (it's driven by Compose focus, not this commit).
            delay(450L)
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
    val playingDisplayChannelId = remember(playingChannelId, visibleChannelsById, variantGroups) {
        displayChannelIdFor(playingChannelId, visibleChannelsById, variantGroups)
    }
    val indexedPlayingChannel = remember(playingChannelId, visibleEnrichedState.value, filteredChannels) {
        playingChannelId?.let { visibleEnrichedState.value.index.byId[it] }
            ?: filteredChannels.firstOrNull { it.id == playingChannelId }
    }
    var retainedPlayingChannel by remember { mutableStateOf<EnrichedChannel?>(null) }
    LaunchedEffect(playingChannelId, indexedPlayingChannel) {
        retainedPlayingChannel = when {
            playingChannelId == null -> null
            indexedPlayingChannel != null -> indexedPlayingChannel
            retainedPlayingChannel?.id == playingChannelId -> retainedPlayingChannel
            else -> retainedPlayingChannel
        }
    }
    // A channel handed to us by id (Home's Favorite TV row, launcher deep links) is
    // usually outside the currently paged category window, so neither the category
    // index nor filteredChannels can resolve it. Without the channel object we have no
    // source to hand IptvRepository, playback falls back to the unresolved raw URL and
    // tunes the wrong stream. Hydrate it straight from the SQLite channel store.
    LaunchedEffect(playingChannelId, indexedPlayingChannel) {
        val id = playingChannelId ?: return@LaunchedEffect
        if (indexedPlayingChannel != null || retainedPlayingChannel?.id == id) return@LaunchedEffect
        val hydrated = withContext(Dispatchers.IO) {
            runCatching {
                viewModel.iptvRepository.pagedChannelsByIds(listOf(id)).firstOrNull()
            }.getOrNull()
        } ?: return@LaunchedEffect
        if (playingChannelId == id && retainedPlayingChannel?.id != id) {
            retainedPlayingChannel = hydrated.enrichForFastStartup(
                hydrated.providerChannelNumber?.trim()?.toIntOrNull() ?: 1
            )
        }
    }
    val playingChannel = indexedPlayingChannel ?: retainedPlayingChannel?.takeIf { it.id == playingChannelId }
    val catchupUrlAnchorOffsetMs = remember(playingChannel?.source, catchupPlaybackOffsetMs) {
        playingChannel?.source?.catchupUrlAnchorOffset(catchupPlaybackOffsetMs) ?: 0L
    }
    val catchupInSegmentSeekMs = remember(playingChannel?.source, catchupPlaybackOffsetMs) {
        playingChannel?.source?.catchupInSegmentSeekOffset(catchupPlaybackOffsetMs) ?: 0L
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
    fun requestGuideWindowAfter() {
        if (filteredChannels.size < selectedCategoryTotalCount) {
            // Repeated keys share the pending page instead of canceling it and
            // growing the request by another page on every key repeat.
            pagedLoadedLimit = nextGuidePageLimit(
                filteredChannels.size, pagedLoadedLimit, selectedCategoryTotalCount
            )
        }
    }
    fun onGuideVisibleRange(first: Int, last: Int) {
        // Rendering owns a continuous list; this small window only owns EPG work.
        val start = (first - 4).coerceAtLeast(0)
        val end = (last + 13).coerceAtMost(filteredChannels.size)
        if (first < guideWindowStart + 2 || last + 4 >= guideWindowEnd) {
            setGuideWindow(start to end)
        }
        if (last >= filteredChannels.size - 16 && filteredChannels.size < selectedCategoryTotalCount) {
            requestGuideWindowAfter()
        }
    }
    val filteredChannelsWindowKey = remember(filteredChannels) {
        listOf(
            filteredChannels.size.toString(),
            filteredChannels.firstOrNull()?.id.orEmpty(),
            filteredChannels.lastOrNull()?.id.orEmpty(),
        ).joinToString("|")
    }
    var guideScopeKey by rememberSaveable { mutableStateOf("") }
    LaunchedEffect(selectedProviderId, selectedCategoryId) {
        guideScopeKey = ""
        guideWindowStart = 0
        guideWindowEnd = GuideInitialWindowRows
    }
    LaunchedEffect(selectedProviderId, selectedCategoryId, filteredChannelsWindowKey) {
        if (filteredChannels.isEmpty()) return@LaunchedEffect
        val nextScopeKey = "$selectedProviderId|$selectedCategoryId"
        if (guideScopeKey != nextScopeKey) {
            val anchorId = rememberedChannelByCategory[categoryScope]
                ?: focusedChannelId
                ?: playingChannelId
                ?: initialChannelId
            val anchorIndex = anchorId?.let(filteredChannelIndexById::get) ?: 0
            setGuideWindow(guideWindowAround(anchorIndex, filteredChannels.size))
            guideScopeKey = nextScopeKey
        } else if (guideWindowStart >= filteredChannels.size) {
            setGuideWindow(guideWindowAround(filteredChannels.lastIndex, filteredChannels.size))
        } else if (guideWindowEnd <= guideWindowStart) {
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
    val normalizedGuideStart = if (filteredChannels.isNotEmpty() && guideWindowStart >= filteredChannels.size) {
        0
    } else {
        guideWindowStart.coerceIn(0, filteredChannels.size)
    }
    val normalizedGuideEnd = guideWindowEnd
        .coerceAtLeast((normalizedGuideStart + GuideInitialWindowRows).coerceAtMost(filteredChannels.size))
        .coerceIn(normalizedGuideStart, filteredChannels.size)
    val guideChannels = remember(filteredChannels, normalizedGuideStart, normalizedGuideEnd) {
        val total = filteredChannels.size
        val start = normalizedGuideStart.coerceIn(0, total)
        val end = normalizedGuideEnd.coerceIn(start, total)
        if (start >= end) {
            emptyList()
        } else {
            filteredChannels.subList(start, end).toList()
        }
    }
    val guideChannelIds = remember(guideChannels) {
        guideChannels.asSequence()
            .map { it.id }
            .filter { it.isNotBlank() }
            .toCollection(LinkedHashSet())
    }
    val guideQueryIds = remember(guideChannelIds, playingChannelId) {
        guideChannelIds + listOfNotNull(playingChannelId)
    }
    LaunchedEffect(selectedCategoryId, filteredChannels.size, guideChannels.size, selectedCategoryTotalCount) {
        if (filteredChannels.isNotEmpty()) {
            System.err.println(
                "[TV-Metrics] category=$selectedCategoryId loaded=${filteredChannels.size}/$selectedCategoryTotalCount " +
                    "guideWindow=${guideChannels.size} start=$normalizedGuideStart"
            )
        }
    }
    val guideChannelIndexById = remember(guideChannels) {
        HashMap<String, Int>(guideChannels.size).apply {
            guideChannels.forEachIndexed { index, channel -> put(channel.id, index) }
        }
    }
    val indexedGuideState = remember(currentProfile?.id) {
        mutableStateOf<Pair<Set<String>, Map<String, IptvNowNext>>>(emptySet<String>() to emptyMap())
    }
    // Keep the visible guide fresh without querying SQLite every 30 seconds.
    // The loaded window is much wider than the grid, so a 15-minute anchor is
    // enough while the lightweight clock tick still updates live progress.
    val guideQueryBucket = guideClockMillis / (15L * 60_000L)
    LaunchedEffect(currentProfile?.id, guideQueryIds, guideQueryBucket) {
        val ids = guideQueryIds
        if (ids.isEmpty()) {
            return@LaunchedEffect
        }
        val queryAnchor = guideQueryBucket * 15L * 60_000L
        val start = queryAnchor - VisibleGuidePastWindowMs
        val end = queryAnchor + VisibleGuideFutureWindowMs
        val startedAt = System.currentTimeMillis()
        val indexed = withContext(Dispatchers.IO) {
            viewModel.iptvRepository.indexedGuideWindow(ids, start, end)
        }
        indexedGuideState.value = ids to retainGuideWindows(indexedGuideState.value.second, indexed, ids)
        System.err.println(
            "[TV-Metrics] indexed guide visible=${indexed.size}/${ids.size} " +
                "rows=${guideChannels.size} in ${System.currentTimeMillis() - startedAt}ms"
        )
    }
    val indexedGuideLoadedIds = indexedGuideState.value.first
    val indexedGuideNowNext = indexedGuideState.value.second
    val effectiveGuideNowNext = remember(state.snapshot.nowNext, indexedGuideNowNext, guideQueryIds, guideClockMillis) {
        HashMap(indexedGuideNowNext).apply {
            guideQueryIds.forEach { id ->
                mergeGuideSlices(
                    state.snapshot.nowNext[id],
                    indexedGuideNowNext[id],
                    guideClockMillis,
                )?.let { put(id, it) }
            }
        }
    }
    val currentNowNext = remember(playingChannelId, playingCatchupProgram, effectiveGuideNowNext, guideClockMillis) {
        playingCatchupProgram?.let { IptvNowNext(now = it) }
            ?: effectiveGuideNowNext[playingChannelId]?.atTime(guideClockMillis)
    }
    val actionGuideNowNext = remember(state.snapshot.nowNext, effectiveGuideNowNext) {
        HashMap(state.snapshot.nowNext).apply { putAll(effectiveGuideNowNext) }
    }
    val guideIdentityKeysByChannelId = remember(enrichedState.value.all) {
        enrichedState.value.all.associate { channel ->
            channel.id to guideIdentityKeys(
                channel.source.epgId,
                channel.source.tvgName,
                channel.source.rawTitle,
                channel.name,
            )
        }
    }
    val currentActionGuideNowNext by rememberUpdatedState(actionGuideNowNext)
    val currentGuideIdentityKeysByChannelId by rememberUpdatedState(guideIdentityKeysByChannelId)
    fun currentProgramForAction(channel: EnrichedChannel): IptvProgram? = guideProgramForAction(
        channelId = channel.id,
        guideIdentityKeys = guideIdentityKeys(
            channel.source.epgId,
            channel.source.tvgName,
            channel.source.rawTitle,
            channel.name,
        ),
        guideByChannelId = currentActionGuideNowNext,
        guideIdentityKeysByChannelId = currentGuideIdentityKeysByChannelId,
    )

    val epgAnchorChannelId = epgPrefetchAnchorId
        ?: selectedDisplayChannelId
        ?: focusedChannelId
        ?: playingChannelId
    val epgPrefetchIds = remember(
        guideChannels,
        guideChannelIndexById,
        selectedCategoryId,
        epgAnchorChannelId,
        selectedDisplayChannelId,
        playingChannelId,
        focusedChannelId,
        favSet,
        filteredChannels,
    ) {
        val maxPrefetch = if (selectedCategoryId == "all") 96 else 180
        val visibleFirstRows = if (selectedCategoryId == "all") GuideVisibleFirstRowsAllChannels else GuideVisibleFirstRows
        val selectedSeedChannelId = epgAnchorChannelId ?: selectedDisplayChannelId ?: focusedChannelId ?: playingChannelId
        val anchorAbsoluteIndex = selectedSeedChannelId?.let(filteredChannelIndexById::get)
            ?: normalizedGuideStart
        val anchorWindowIndex = (anchorAbsoluteIndex - normalizedGuideStart)
            .takeIf { it in guideChannels.indices }
            ?: 0
        buildList<String> {
            fun addChannel(channel: EnrichedChannel?) {
                val id = channel?.id ?: return
                if (!contains(id)) add(id)
            }
            fun addGuideFirst(index: Int) {
                val channel = guideChannels.getOrNull(index) ?: return
                if (channel.hasGuideIdentity()) addChannel(channel)
            }

            // Favorites are the user's explicit fast-start set. They should get
            // guide priority even when the current category is "All" or a large
            // provider group where favorites were prepended into the first window.
            filteredChannels
                .asSequence()
                .filter { it.id in favSet }
                .filter { it.id in visibleChannelsById }
                .take(24)
                .forEach(::addChannel)

            // First paint must target the selected/focused row plus the rows visible
            // below it. These may lack tvg-id but still have an Xtream stream
            // id that can return direct short/full EPG data.
            addChannel(selectedSeedChannelId?.let(visibleChannelsById::get))
            addChannel(guideChannels.getOrNull(anchorWindowIndex))
            var nearIndex = anchorWindowIndex + 1
            var nearCount = 0
            while (nearIndex < guideChannels.size && nearCount < visibleFirstRows && size < maxPrefetch) {
                addChannel(guideChannels[nearIndex])
                nearIndex++
                nearCount++
            }
            var nearBackIndex = anchorWindowIndex - 1
            var nearBackCount = 0
            while (nearBackIndex >= 0 && nearBackCount < 8 && size < maxPrefetch) {
                addChannel(guideChannels[nearBackIndex])
                nearBackIndex--
                nearBackCount++
            }

            var index = anchorWindowIndex + 1
            while (index < guideChannels.size && size < maxPrefetch) {
                addGuideFirst(index)
                index++
            }
            var backIndex = anchorWindowIndex - 1
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
    val isLargePagedGuide = lastKnownPagedTotal > 10_000 || state.snapshot.channels.size > 10_000
    val indexedVisibleGuideReady = remember(isLargePagedGuide, indexedGuideLoadedIds, guideChannelIds) {
        !isLargePagedGuide || indexedGuideLoadedIds.containsAll(guideChannelIds)
    }
    LaunchedEffect(selectedCategoryId, epgPrefetchIds, epgAnchorChannelId, state.iptvPreferencesLoaded, state.tvSessionLoaded, state.tvSession.lastChannelId, guideChannelIndexById, startupChannelApplied, playingChannelId, selectedDisplayChannelId, focusedChannelId, indexedGuideLoadedIds, effectiveGuideNowNext) {
        val startupReady = state.iptvPreferencesLoaded && state.tvSessionLoaded
        if (startupReady && startupChannelApplied && epgPrefetchIds.isNotEmpty()) {
            delay(300L)
            // The indexed visible guide window is the authoritative fast path for a
            // paged playlist. Wait for that single query before scheduling any
            // fallback work; previously three startup effects queried and merged
            // the same rows concurrently, causing wide recompositions and GC.
            if (!indexedVisibleGuideReady) return@LaunchedEffect
            val missingIds = epgPrefetchIds.filterNot { effectiveGuideNowNext[it].hasGuideData() }
            if (missingIds.isEmpty()) return@LaunchedEffect
            val selectedId = epgAnchorChannelId
                ?: selectedDisplayChannelId
                ?: focusedChannelId
                ?: playingChannelId
                ?: missingIds.firstOrNull()
            viewModel.prefetchVisibleCategoryEpg(
                channelIds = missingIds,
                selectedChannelId = selectedId?.takeIf { it in missingIds } ?: missingIds.firstOrNull(),
                eagerLimit = if (selectedCategoryTotalCount > 10_000) 8 else if (selectedCategoryId == "all") 12 else 24,
                backgroundLimit = if (selectedCategoryTotalCount > 10_000) 24 else if (selectedCategoryId == "all") 48 else 96,
                allowFocusedNetworkRefresh = true,
            )
        }
    }
    LaunchedEffect(playingChannelId, selectedDisplayChannelId, focusedChannelId, state.iptvPreferencesLoaded, state.tvSessionLoaded, startupChannelApplied, indexedGuideLoadedIds, effectiveGuideNowNext) {
        val ids = listOfNotNull(playingChannelId, selectedDisplayChannelId, focusedChannelId)
            .filter { it.isNotBlank() }
            .distinct()
        val selectedId = playingChannelId ?: selectedDisplayChannelId ?: focusedChannelId
        if (ids.isEmpty() || selectedId.isNullOrBlank()) return@LaunchedEffect
        if (state.iptvPreferencesLoaded && state.tvSessionLoaded && startupChannelApplied) {
            if (!indexedVisibleGuideReady) return@LaunchedEffect
            if (effectiveGuideNowNext[selectedId].hasGuideData()) return@LaunchedEffect
            System.err.println("[EPG-Current] ids=${ids.take(4)} selected=$selectedId")
            delay(400L)
            viewModel.refreshCurrentChannelEpg(selectedId, forceNetworkForLargeList = true)
            viewModel.prefetchVisibleCategoryEpg(
                channelIds = ids,
                selectedChannelId = selectedId,
                eagerLimit = 1,
                backgroundLimit = 1,
            )
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
    // loaded. The persisted last channel wins over favorites so reopening TV
    // resumes exactly where the user stopped.
    LaunchedEffect(filteredChannelsWindowKey, playingChannelId, initialChannelId, state.tvSession, state.snapshot.favoriteChannels, visibleEnrichedState.value.all.size, state.iptvPreferencesLoaded, state.tvSessionLoaded, selectedProviderId, startupChannelApplied) {
        val startupStateReady = state.iptvPreferencesLoaded && state.tvSessionLoaded
        val playingVisible = playingChannelId?.let { id -> id in visibleEnrichedState.value.index.byId } == true
        if (!startupChannelApplied && filteredChannels.isNotEmpty() && (initialChannelId != null || startupStateReady)) {
            val savedId = state.tvSession.lastChannelId.takeIf { state.tvSession.lastOpenedAt > 0L && it.isNotBlank() }
            val savedChannel = if (savedId != null && savedId !in filteredChannelIndexById) {
                withContext(Dispatchers.IO) {
                    viewModel.iptvRepository.pagedChannelsByIds(listOf(savedId)).firstOrNull()
                }?.enrichForFastStartup(1)?.takeUnless {
                    isRestrictedPlaylistGroup(it, hiddenGroupSet + restrictedGroupSet)
                }
            } else null
            val startupChannelId = LiveTvStartup.chooseStartupChannelId(
                availableChannelIds = filteredChannelIndexById.keys,
                firstAvailableChannelId = filteredChannels.firstOrNull()?.id,
                // Passed through as-is: selectedProviderId is rememberSaveable, so a
                // provider filter left over from an earlier visit used to discard the
                // channel the caller explicitly asked for and fall back to channel #1.
                explicitChannelId = initialChannelId,
                sessionLastChannelId = state.tvSession.lastChannelId,
                hasOpenedBefore = state.tvSession.lastOpenedAt > 0L,
                favoriteChannelIds = state.snapshot.favoriteChannels,
                isFullyLoaded = visibleEnrichedState.value.all.isNotEmpty(),
                resolvedSessionChannelId = savedChannel?.id,
            )
            if (startupChannelId != null) {
                val displayId = displayChannelIdFor(startupChannelId, visibleEnrichedState.value.index.byId, variantGroups)
                    ?: startupChannelId
                playingChannelId = startupChannelId
                focusedChannelId = displayId
                epgPrefetchAnchorId = displayId
                rememberedChannelByCategory[categoryScope] = displayId
                filteredChannelIndexById[displayId]
                    ?.let { setGuideWindow(guideWindowAround(it, filteredChannels.size)) }
                startupChannelApplied = true
                System.err.println("[EPG-Startup] channel=$startupChannelId focus=$displayId")
            }
        } else if (playingChannelId == null && filteredChannels.isNotEmpty() && startupStateReady && !isGuideUserNavigating()) {
            val fallbackChannelId = LiveTvStartup.chooseStartupChannelId(
                availableChannelIds = filteredChannelIndexById.keys,
                firstAvailableChannelId = filteredChannels.firstOrNull()?.id,
                explicitChannelId = null,
                sessionLastChannelId = state.tvSession.lastChannelId,
                hasOpenedBefore = state.tvSession.lastOpenedAt > 0L,
                favoriteChannelIds = state.snapshot.favoriteChannels,
                isFullyLoaded = visibleEnrichedState.value.all.isNotEmpty(),
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

    var categoryDrawerOpen by rememberSaveable { mutableStateOf(true) }
    var sportsSelected by rememberSaveable(currentProfile?.id) { mutableStateOf(false) }
    val sportsScheduleKey = SportsScheduleKey(currentProfile?.id, selectedProviderId, state.snapshot.loadedAt.toEpochMilli(),
        hiddenGroupSet + restrictedGroupSet, state.epgBackfillInProgress, guideClockMillis / 600_000L)
    var sportsFocusSignal by remember { mutableIntStateOf(0) }
    val sportsClockFormat by remember(currentProfile?.id) { viewModel.sportsClockFormat(currentProfile?.id) }.collectAsStateWithLifecycle(initialValue = "24h")
    var sportsEvents by remember(currentProfile?.id, selectedProviderId, hiddenGroupSet, restrictedGroupSet) {
        mutableStateOf(viewModel.cachedSportsSchedule?.takeIf { it.key == sportsScheduleKey }?.events.orEmpty())
    }
    var sportsLoading by remember { mutableStateOf(false) }
    var sportsMetadataLoading by remember { mutableStateOf(false) }
    var sportsBroadcastLoading by remember { mutableStateOf(false) }
    var sportsCatalogueLoading by remember { mutableStateOf(false) }
    var sportsError by remember { mutableStateOf(false) }
    var sportsRefresh by remember { mutableIntStateOf(0) }
    var completedSportsScan by remember(currentProfile?.id, selectedProviderId, hiddenGroupSet, restrictedGroupSet) {
        mutableStateOf<List<Any>?>(null)
    }
    var sportsArtwork by remember(currentProfile?.id) { mutableStateOf(emptyList<com.arflix.tv.data.model.SportsEventArtwork>()) }
    LaunchedEffect(currentProfile?.id, state.snapshot.loadedAt, sportsRefresh, guideClockMillis / 120_000L) {
        if (state.snapshot.loadedAt.toEpochMilli() > 0L) {
            sportsMetadataLoading = true
            try {
            var metadata = viewModel.cachedSportsMetadata()
            var addonArtwork = sportsArtwork.filter { it.source != "TheSportsDB" }
            sportsArtwork = metadata + addonArtwork
            kotlinx.coroutines.coroutineScope {
                launch { metadata = viewModel.loadSportsMetadata(); sportsArtwork = metadata + addonArtwork }
                launch { addonArtwork = viewModel.loadSportsAddonArtwork(); sportsArtwork = metadata + addonArtwork }
            }
            } catch (cancelled: kotlinx.coroutines.CancellationException) { throw cancelled }
            catch (_: Exception) { sportsError = true }
            finally { sportsMetadataLoading = false }
        }
    }
    var broadcastCandidates by remember(currentProfile?.id, selectedProviderId, hiddenGroupSet, restrictedGroupSet) { mutableStateOf(emptyList<IptvChannel>()) }
    var broadcasterKeys by remember { mutableStateOf(emptySet<String>()) }
    LaunchedEffect(sportsArtwork) {
        broadcasterKeys = withContext(Dispatchers.Default) {
            sportsArtwork.asSequence().flatMap { it.fixture?.broadcasters.orEmpty().asSequence() }
                .distinctBy { it.name to it.country }.flatMap { sportsBroadcasterKeys(it.name, it.country).asSequence() }.toSet()
        }
    }
    LaunchedEffect(broadcasterKeys, currentProfile?.id, selectedProviderId, hiddenGroupSet, restrictedGroupSet, state.snapshot.loadedAt) {
        if (broadcasterKeys.isEmpty()) { broadcastCandidates = emptyList(); return@LaunchedEffect }
        sportsBroadcastLoading = true
        try { broadcastCandidates = withContext(Dispatchers.IO) {
            val ids = linkedSetOf<String>()
            val excluded = hiddenGroupSet + restrictedGroupSet
            val scope = kotlinx.coroutines.currentCoroutineContext()
            val names = hashMapOf<String, Boolean>()
            viewModel.iptvRepository.visitStoredChannelLabels(selectedProviderId.takeUnless { it == "all" }) { id, name, group ->
                scope.ensureActive()
                if (PlaylistGroupKey.build(channelPlaylistId(id), group.trim()) !in excluded && group !in excluded &&
                    names.getOrPut(name) { sportsChannelKey(name) in broadcasterKeys }) ids.add(id)
            }
            ids.chunked(128).flatMap { viewModel.iptvRepository.pagedChannelsByIds(it) }.filter { !it.enrichForFastStartup(0).isAdult }
        } } catch (cancelled: kotlinx.coroutines.CancellationException) { throw cancelled }
        catch (_: Exception) { sportsError = true }
        finally { sportsBroadcastLoading = false }
    }
    var illustratedSportsEvents by remember(currentProfile?.id, selectedProviderId, hiddenGroupSet, restrictedGroupSet) { mutableStateOf(emptyList<SportsGuideEvent>()) }
    LaunchedEffect(sportsEvents, sportsArtwork, broadcastCandidates) {
        sportsCatalogueLoading = true
        try { illustratedSportsEvents = withContext(Dispatchers.Default) {
            buildSportsCatalogue(sportsEvents, sportsArtwork, broadcastCandidates, guideClockMillis)
        }
        System.err.println("[Sports-Catalogue] guide=${sportsEvents.size} metadata=${sportsArtwork.size} broadcasters=${broadcastCandidates.size} available=${illustratedSportsEvents.count { it.hasChannels(guideClockMillis) }} illustrated=${illustratedSportsEvents.count { it.hasChannels(guideClockMillis) && it.hasEventArtwork }}")
        } catch (cancelled: kotlinx.coroutines.CancellationException) { throw cancelled }
        catch (_: Exception) { sportsError = true }
        finally { sportsCatalogueLoading = false }
    }
    val sportsProviderNames = remember(state.config.playlists, state.config.stalkerPortals) {
        state.config.playlists.associate { it.id to it.name } + state.config.stalkerPortals.associate { it.id to it.name }
    }
    val sportsSidebarTree = remember(visibleEnrichedState.value.tree, quickGuideRows) {
        val tree = visibleEnrichedState.value.tree.withSportsDestination()
        tree.copy(top = tree.top.map { category ->
            quickGuideRows[category.id]?.let { category.copy(count = it.size) } ?: category
        })
    }
    LaunchedEffect(currentProfile?.id, selectedProviderId, hiddenGroupSet,
        restrictedGroupSet, state.snapshot.loadedAt, state.epgBackfillInProgress, sportsRefresh, guideClockMillis / 600_000L) {
        if (state.snapshot.loadedAt.toEpochMilli() <= 0L) return@LaunchedEffect
        val scanVersion = listOf(state.snapshot.loadedAt, state.epgBackfillInProgress, sportsRefresh, guideClockMillis / 600_000L)
        if (completedSportsScan == scanVersion) return@LaunchedEffect
        viewModel.cachedSportsSchedule?.takeIf { it.key == sportsScheduleKey && sportsRefresh == 0 }?.let {
            sportsEvents = it.events
            completedSportsScan = scanVersion
            return@LaunchedEffect
        }
        sportsLoading = true
        sportsError = false
        try {
            val result = withContext(Dispatchers.IO) {
                val context = kotlinx.coroutines.currentCoroutineContext()
                val candidateIds = linkedSetOf<String>()
                val generalIds = linkedSetOf<String>()
                val indexedIds = viewModel.iptvRepository.cachedGuideChannelIds(guideClockMillis, guideClockMillis + 48 * 60 * 60_000L)
                val groupSports = hashMapOf<String, GuideSport?>()
                val fallbacks = hashMapOf<String, GuideSport?>()
                val excluded = hiddenGroupSet + restrictedGroupSet
                val genericSports = Regex("\\b(sports?|espn|eurosport)\\b", RegexOption.IGNORE_CASE)
                viewModel.iptvRepository.visitStoredChannelLabels(selectedProviderId.takeUnless { it == "all" }) { id, name, group ->
                    context.ensureActive()
                    val key = PlaylistGroupKey.build(channelPlaylistId(id), group.trim())
                    if (key !in excluded && group !in excluded && (id in indexedIds || id in state.snapshot.nowNext)) {
                        if (!groupSports.containsKey(group)) groupSports[group] = GuideSport.fromText(group)
                        val sport = groupSports[group] ?: GuideSport.fromText(name)
                        fallbacks[id] = sport
                        if (sport != null || genericSports.containsMatchIn(group) || genericSports.containsMatchIn(name)) candidateIds.add(id)
                        else if (id in indexedIds) generalIds.add(id)
                    }
                }
                // Include national/general channels whose visible schedule identifies sport.
                allDisplayChannels.filter { !it.isAdult && !isHiddenPlaylistGroup(it, hiddenGroupSet) && !isRestrictedPlaylistGroup(it, restrictedGroupSet) }
                    .forEach { candidateIds.add(it.id) }
                candidateIds.addAll(generalIds)
                val events = SportsEventIndex()
                val programmeResolver = SportsProgrammeResolver()
                val startedAt = android.os.SystemClock.elapsedRealtime()
                for (ids in candidateIds.toList().chunked(1024)) {
                    kotlinx.coroutines.currentCoroutineContext().ensureActive()
                    val indexedIdsInBatch = hashSetOf<String>()
                    val matches = hashMapOf<String, MutableList<Pair<IptvProgram, SportsProgrammeResolver.Metadata>>>()
                    // Classify first; deserialize full channel records only for actual events.
                    viewModel.iptvRepository.visitCachedGuideWindow(ids.toSet(),
                        guideClockMillis, guideClockMillis + 48 * 60 * 60_000L) { id, programme ->
                        context.ensureActive()
                        indexedIdsInBatch.add(id)
                        val meta = programmeResolver.resolve(programme, fallbacks[id])
                        if (meta != null) matches.getOrPut(id) { arrayListOf() }.add(programme to meta)
                    }
                    val uncachedIds = ids.filter { it !in indexedIdsInBatch && it in state.snapshot.nowNext }
                    val batch = viewModel.iptvRepository.pagedChannelsByIds(matches.keys + uncachedIds)
                        .filter { !it.enrichForFastStartup(0).isAdult }
                    batch.forEach { channel -> matches[channel.id].orEmpty().forEach { (programme, meta) ->
                        events.add(meta.sport, meta.identity, programme, channel, meta.competition)
                    } }
                    val uncached = batch.filter { it.id !in indexedIdsInBatch }
                    accumulateSportsGuideEvents(uncached, state.snapshot.nowNext, guideClockMillis, events, resolver = programmeResolver)
                }
                System.err.println("[Sports-Scan] candidates=${candidateIds.size} events=${events.events().size} elapsed=${android.os.SystemClock.elapsedRealtime() - startedAt}ms")
                events.events()
            }
            sportsEvents = retainSportsEventOrder(sportsEvents, result)
            viewModel.cachedSportsSchedule = SportsScheduleSnapshot(sportsScheduleKey, sportsEvents)
            completedSportsScan = scanVersion
        } catch (cancelled: kotlinx.coroutines.CancellationException) {
            throw cancelled
        } catch (_: Exception) {
            sportsError = true
        } finally { sportsLoading = false }
    }
    val sidebarExpanded = !useTouchRail && categoryDrawerOpen
    // Changing this on drawer toggle makes channel labels and the EPG jump before the slide.
    val guideChannelColumnWidth = LiveDims.EpgChannelWideColWidth
    var focusGuideAfterDrawerClose by remember { mutableStateOf(false) }
    var focusCategoryAfterDrawerOpen by remember { mutableStateOf(false) }
    var pendingLockedGroupAction by remember { mutableStateOf<LockedGroupPinAction?>(null) }
    var lockedGroupPinError by remember { mutableStateOf("") }
    var showMissingProfilePinDialog by remember { mutableStateOf(false) }
    LaunchedEffect(currentProfile?.id) {
        unlockedGroupKeys = emptySet()
        pendingLockedGroupAction = null
        lockedGroupPinError = ""
    }
    var searchOpen by rememberSaveable { mutableStateOf(false) }
    var focusSelectedChannelSignal by remember { mutableIntStateOf(0) }
    var focusEpgSignal by remember { mutableIntStateOf(0) }
    // Starts at 0 so opening Live TV does NOT slam focus into the channel
    // search field. It seeded to 1, and the sidebar focuses search for any
    // value > 0, so every entry began with the selector trapped in the search
    // box. focusPlaylistSearch() still bumps it when the user actually asks
    // for search.
    var focusSearchCategorySignal by remember { mutableIntStateOf(0) }
    // Bumped to put the selector on the category list (never on search).
    var focusCategoryRailSignal by remember { mutableIntStateOf(0) }
    // Full-screen playback mode — pressing OK on an EPG row expands the
    // mini-player to cover the whole screen. Back collapses back to the grid.
    //
    // Being handed a specific channel (Home's Favorite TV row, launcher deep links)
    // means "play this now", so open straight into fullscreen. This used to key off
    // initialStreamUrl, which forced callers to pass a raw URL just to get autoplay.
    var isFullScreen by rememberSaveable {
        mutableStateOf(initialChannelId != null || initialStreamUrl != null)
    }
    val fsProgress by animateFloatAsState(
        targetValue = if (isFullScreen) 1f else 0f,
        animationSpec = tween(durationMillis = 220, easing = FastOutSlowInEasing),
        label = "tv-fullscreen-progress",
    )
    val miniPlayerActive = !isFullScreen && fsProgress == 0f
    var pendingFocusAfterFullscreenExit by remember { mutableStateOf<String?>(null) }
    // Set while we are still in that launched-to-play session. Backing out of it should
    // return to whoever launched us (Home), not strand the user in the Live TV guide
    // they never asked for. Cleared on the first exit so later fullscreen sessions
    // collapse to the guide as normal.
    var returnToCallerOnFullscreenExit by rememberSaveable {
        mutableStateOf(initialChannelId != null || initialStreamUrl != null)
    }
    var fullscreenGuideOpen by remember { mutableStateOf(false) }
    var quickZapOpen by remember { mutableStateOf(false) }
    var variantPickerChannel by remember { mutableStateOf<EnrichedChannel?>(null) }
    // Channel long-press menu (favourite, reorder favourites, quality variants).
    var channelMenu by remember { mutableStateOf<ChannelMenuState?>(null) }
    // True once the current OK hold has already opened the menu. Lives on the screen, not
    // on ChannelRow: the row is a LazyColumn item and opening the menu rebuilds the list,
    // so a latch stored per-row was recycled mid-hold — every later repeat re-fired the
    // long press (menu flickering open/closed) and the release still read as a click
    // (channel opened). Screen-level state survives the rebuild.
    // Holding OK does NOT stay one key press. After the first genuine auto-repeat burst
    // (repeat=0,1,2 sharing a downTime) the platform starts emitting a stream of brand new
    // press/release pairs — each with its own downTime and repeat=0, ~33ms apart — which
    // are byte-for-byte identical to deliberate short clicks. Captured from a real 3s hold:
    //   DOWN r=0 down=16034223 / DOWN r=1 / DOWN r=2 / UP        <- the actual press
    //   DOWN r=0 down=16034724 / UP  <- picked a menu item
    //   DOWN r=0 down=16034757 / UP  <- tuned the channel
    val selectKeyGuard = remember { GuideSelectKeyGuard() }
    // A second selection on the currently playing programme offers Watch Live
    // and, only after a confident movie/series match, Stream Now.
    var programActionDialog by remember { mutableStateOf<ProgramActionData?>(null) }
    var programActionVodMatch by remember { mutableStateOf<ArvioMediaItem?>(null) }
    var programActionLookupInProgress by remember { mutableStateOf(false) }
    val programActionLookupGuard = remember { EpgVodLookupGuard() }
    val programActionLookupJob = remember { arrayOf<Job?>(null) }
    fun invalidateProgramActionLookup() {
        programActionLookupJob[0]?.cancel()
        programActionLookupJob[0] = null
        programActionLookupGuard.invalidate()
        programActionDialog = null
        programActionVodMatch = null
        programActionLookupInProgress = false
    }
    LaunchedEffect(
        selectedCategoryId,
        selectedProviderId,
        focusedChannelId,
        searchOpen,
        variantPickerChannel,
        isFullScreen,
        fullscreenGuideOpen,
        quickZapOpen,
    ) {
        invalidateProgramActionLookup()
    }
    LaunchedEffect(isFullScreen) {
        onFullscreenChanged(isFullScreen)
    }
    DisposableEffect(Unit) {
        onDispose {
            programActionLookupJob[0]?.cancel()
            programActionLookupGuard.invalidate()
            onFullscreenChanged(false)
        }
    }
    // Focus requesters for the three regions.
    val sidebarFocus = remember { FocusRequester() }
    val providerFocus = remember { FocusRequester() }
    val epgFocus = remember { FocusRequester() }
    val fsFocus = remember { FocusRequester() }
    val emptyStateButtonFocus = remember { FocusRequester() }
    val sidebarListState = rememberLazyListState()

    var hudPokeSignal by remember { mutableStateOf(0) }
    var isHudVisible by remember { mutableStateOf(false) }
    // Bumped to dismiss the HUD from outside, so Back can close it without
    // leaving fullscreen.
    var hudHideSignal by remember { mutableStateOf(0) }
    // The HUD shows itself for a few seconds after a zap, but only OK hands it
    // the focus. While it is not engaged the arrow keys stay with playback.
    var hudEngaged by remember { mutableStateOf(false) }
    var lastZapAtMs by remember { mutableLongStateOf(0L) }

    // Dismissing the controls takes the focused button out of composition, so
    // hand the focus back to the playback surface — otherwise the next key
    // press lands nowhere and the remote looks dead.
    LaunchedEffect(isHudVisible) {
        if (!isHudVisible && isFullScreen && !fullscreenGuideOpen && !quickZapOpen) {
            runCatching { fsFocus.requestFocus() }
        }
    }
    var guideOpenedFromQuickZap by remember { mutableStateOf(false) }
    var guideChannel by remember { mutableStateOf<EnrichedChannel?>(null) }
    val fullscreenGuideChannelId = (guideChannel ?: playingChannel)?.id
    val fullscreenGuide = remember(
        fullscreenGuideChannelId,
        fullscreenGuideChannelId?.let { state.snapshot.nowNext[it] },
        fullscreenGuideChannelId?.let { effectiveGuideNowNext[it] },
        guideClockMillis,
    ) {
        resolveFullscreenGuide(fullscreenGuideChannelId, state.snapshot.nowNext, effectiveGuideNowNext, guideClockMillis)
    }

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
        playlistCategorySections.forEach { section ->
            section.categories.forEach { cat ->
                if (cat.count > 0) list.add(cat.id)
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

    LaunchedEffect(state.tvSessionLoaded, state.tvSession.lastGroupName, visibleEnrichedState.value.tree, playlistCategorySections, startupCategoryApplied) {
        if (startupCategoryApplied || !state.tvSessionLoaded) return@LaunchedEffect
        val tree = visibleEnrichedState.value.tree
        if (tree.top.isEmpty() && tree.global.categories.isEmpty() && playlistCategorySections.isEmpty()) return@LaunchedEffect
        selectedCategoryId = LiveTvStartup.resumeCategoryId(
            lastGroupName = state.tvSession.lastGroupName,
            availableCategoryIds = getAvailableCategoryIds(tree).toSet(),
        )
        startupCategoryApplied = true
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

    fun openFullscreenGuide(channel: EnrichedChannel? = playingChannel, fromQuickZap: Boolean = false) {
        guideChannel = channel
        guideOpenedFromQuickZap = fromQuickZap
        quickZapOpen = false
        // The view model reads the complete local archive first and owns coverage/backoff.
        // A row count cannot tell whether the guide covers hours or days.
        viewModel.refreshCatchupHistoryForChannel(channel?.id, channel?.source)
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
                activity?.requestedOrientation = previousOrientation
            }
            if (window != null) {
                @Suppress("DEPRECATION")
                window.clearFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN)
                val controller = androidx.core.view.WindowInsetsControllerCompat(window, window.decorView)
                controller.systemBarsBehavior =
                    androidx.core.view.WindowInsetsControllerCompat.BEHAVIOR_DEFAULT
                controller.show(androidx.core.view.WindowInsetsCompat.Type.systemBars())
                controller.isAppearanceLightStatusBars = false
                controller.isAppearanceLightNavigationBars = false
            }
        }
    }

    // Single source for the previous channel: every path that changes the
    // channel ends up writing playingChannelId, so observing it here keeps the
    // toggle correct no matter which one was used.
    LaunchedEffect(playingChannelId) {
        val current = playingChannelId ?: return@LaunchedEffect
        val last = lastTunedChannelId
        if (last != null && last != current) {
            previousChannelId = last
        }
        lastTunedChannelId = current
    }

    // Tune straight to a channel without leaving fullscreen. Shared by zapping
    // and by the previous-channel jump so both leave the same state behind.
    fun tuneToDisplayChannel(channel: EnrichedChannel) {
        noteGuideUserNavigation()
        playingChannelId = channel.id
        focusedChannelId = channel.id
        epgPrefetchAnchorId = channel.id
        rememberedChannelByCategory[categoryScope] = channel.id
        playingCatchupProgram = null
        catchupPlaybackOffsetMs = 0L
        fullscreenGuideOpen = false
    }

    // Prev/next zapping across the full enriched list (not the filtered
    // category) per user spec. Wraps around.
    fun zap(delta: Int) {
        val all = allDisplayChannels
        if (all.isEmpty()) return
        val currentDisplayId = displayChannelIdFor(playingChannelId, visibleEnrichedState.value.index.byId, variantGroups)
        val currentIdx = currentDisplayId?.let { id -> all.indexOfFirst { channel -> channel.id == id } } ?: -1
        val start = if (currentIdx >= 0) currentIdx else 0
        val size = all.size
        tuneToDisplayChannel(all[((start + delta) % size + size) % size])
    }

    // Jump back to the channel that was playing before this one, so the right
    // arrow toggles between the last two. Returns false when there is no
    // previous channel yet or it has dropped out of the visible list.
    fun tunePreviousChannel(): Boolean {
        val target = previousChannelId
            ?.let { id -> allDisplayChannels.firstOrNull { channel -> channel.id == id } }
            ?: return false
        tuneToDisplayChannel(target)
        return true
    }

    fun focusPlaylistSearch() {
        noteGuideUserNavigation()
        categoryDrawerOpen = true
        focusZone = LiveTvFocusZone.CATEGORY_LIST
        focusSearchCategorySignal += 1
        runCatching { sidebarFocus.requestFocus() }
    }

    fun openCategoryDrawer() {
        noteGuideUserNavigation()
        categoryDrawerOpen = true
        focusCategoryAfterDrawerOpen = true
        focusZone = LiveTvFocusZone.CATEGORY_LIST
        runCatching { sidebarFocus.requestFocus() }
    }

    // Keep focus in the sidebar while that zone is active — but NOT while the
    // channel list is still loading. During a load the list is recomposing
    // underneath the focused item, so Compose keeps dropping focus and this
    // effect kept re-grabbing it: pressing a direction key while loading sent
    // the selector jumping in unrelated directions, and it stayed pinned to the
    // search field until everything had finished. Once channels exist the
    // layout is stable and normal focus handling behaves predictably.
    val channelsReady = currentUiState.snapshot.channels.isNotEmpty()
    LaunchedEffect(focusZone, isTouchDevice, channelsReady) {
        if (LiveTvStartup.shouldClaimSidebarFocus(
                isTouchDevice = isTouchDevice,
                isCategoryZoneActive = focusZone == LiveTvFocusZone.CATEGORY_LIST,
                channelsLoaded = channelsReady,
            )
        ) {
            runCatching { sidebarFocus.requestFocus() }
        }
    }

    fun focusProviderSwitcher() {
        noteGuideUserNavigation()
        // Playlist sections replace the standalone provider selector. Route focus
        // straight into the category rail when that selector is not composed.
        if (playlistCategorySections.isNotEmpty() || providerFilters.size <= 1) {
            openCategoryDrawer()
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
            rememberedChannelByCategory[categoryScope] = it
            val index = filteredChannelIndexById[it]
            if (index != null && index !in guideWindowStart until guideWindowEnd) {
                setGuideWindow(guideWindowAround(index, filteredChannels.size))
            }
        }
        focusZone = LiveTvFocusZone.CHANNEL_LIST
        focusSelectedChannelSignal += 1
        if (channelId == null) {
            runCatching { epgFocus.requestFocus() }
        }
    }

    fun focusEpg(channelId: String) {
        noteGuideUserNavigation()
        focusedChannelId = channelId
        epgPrefetchAnchorId = channelId
        rememberedChannelByCategory[categoryScope] = channelId
        val index = filteredChannelIndexById[channelId]
        if (index != null && index !in guideWindowStart until guideWindowEnd) {
            setGuideWindow(guideWindowAround(index, filteredChannels.size))
        }
        focusZone = LiveTvFocusZone.EPG
        focusEpgSignal += 1
    }

    fun enterSelectedCategory(categoryId: String) {
        noteGuideUserNavigation()
        focusCommitJob[0]?.cancel()
        focusedChannelObject[0] = null
        selectedCategoryId = categoryId
        categoryDrawerOpen = false
        focusGuideAfterDrawerClose = true
        viewModel.rememberTvSession(
            lastGroupName = categoryId,
            lastFocusedZone = "CATEGORY",
            markOpened = false,
        )
    }

    LaunchedEffect(selectedCategoryId, startupCategoryApplied) {
        if (startupCategoryApplied && selectedCategoryId.isNotBlank()) {
            viewModel.rememberTvSession(
                lastGroupName = selectedCategoryId,
                markOpened = false,
            )
        }
    }

    fun requestCategorySelection(categoryId: String) {
        if (categoryId == SPORTS_GUIDE_CATEGORY) {
            sportsSelected = true
            // Selection opens the destination; moving right into its cards closes the drawer.
            focusGuideAfterDrawerClose = false
            if (isTouchDevice) categoryDrawerOpen = false
            return
        }
        sportsSelected = false
        val category = visibleEnrichedState.value.tree.byId(categoryId)
        val groupKey = category?.playlistId?.let { playlistId ->
            category.playlistGroupName?.let { groupName -> PlaylistGroupKey.build(playlistId, groupName) }
        }
        if (groupKey != null && groupKey in state.lockedGroups && groupKey !in unlockedGroupKeys) {
            if (currentProfile?.pin.isNullOrBlank()) {
                showMissingProfilePinDialog = true
            } else {
                lockedGroupPinError = ""
                pendingLockedGroupAction = LockedGroupPinAction.OpenCategory(categoryId, groupKey)
            }
            return
        }
        enterSelectedCategory(categoryId)
    }

    fun requestCategoryLockToggle(playlistId: String?, groupName: String, wasLocked: Boolean) {
        val sourceId = playlistId?.trim().orEmpty()
        if (sourceId.isBlank() || groupName.isBlank()) return
        if (currentProfile?.pin.isNullOrBlank()) {
            showMissingProfilePinDialog = true
            return
        }
        lockedGroupPinError = ""
        pendingLockedGroupAction = LockedGroupPinAction.ToggleLock(sourceId, groupName, wasLocked)
    }

    LaunchedEffect(categoryDrawerOpen, focusCategoryAfterDrawerOpen, selectedCategoryId) {
        if (!categoryDrawerOpen || !focusCategoryAfterDrawerOpen || useTouchRail) return@LaunchedEffect
        repeat(4) {
            delay(32L)
            focusCategoryRailSignal += 1
        }
        focusCategoryAfterDrawerOpen = false
    }

    LaunchedEffect(categoryDrawerOpen, focusGuideAfterDrawerClose, categoryScope, filteredChannelsScopeKey, filteredChannelsWindowKey) {
        if (categoryDrawerOpen || !focusGuideAfterDrawerClose || useTouchRail || filteredChannels.isEmpty() ||
            filteredChannelsScopeKey != categoryScope
        ) {
            return@LaunchedEffect
        }
        val target = rememberedChannelByCategory[categoryScope]
            ?.takeIf { it in filteredChannelIndexById }
            ?: playingChannelId?.let { displayChannelIdFor(it, visibleEnrichedState.value.index.byId, variantGroups) }
                ?.takeIf { it in filteredChannelIndexById }
            ?: filteredChannels.firstOrNull()?.id
        delay(16L)
        focusGuideAfterDrawerClose = false
        focusChannelList(target)
    }

    fun exitFullScreenPlayback() {
        // Launched straight into playback from elsewhere — hand control back to that
        // caller instead of dropping the user into the guide. Every fullscreen Back
        // path routes through here, so this covers the key handlers too.
        if (returnToCallerOnFullscreenExit) {
            returnToCallerOnFullscreenExit = false
            fullscreenGuideOpen = false
            isFullScreen = false
            onBack()
            return
        }
        val returnFocusChannelId = playingChannelId ?: focusedChannelId
        fullscreenGuideOpen = false
        isFullScreen = false
        hudPokeSignal++
        pendingFocusAfterFullscreenExit = returnFocusChannelId
    }

    LaunchedEffect(isFullScreen, fsProgress) {
        if (!isFullScreen && fsProgress == 0f) {
            val target = pendingFocusAfterFullscreenExit
            if (target != null) {
                pendingFocusAfterFullscreenExit = null
                if (sportsSelected) {
                    focusZone = LiveTvFocusZone.SPORTS
                    sportsFocusSignal++
                } else focusChannelList(target)
            }
        }
    }

    fun openVariantPicker(channel: EnrichedChannel) {
        noteGuideUserNavigation()
        if (variantCountFor(channel, variantGroups) > 1) {
            variantPickerChannel = channel
        }
    }

    fun openChannelMenu(channel: EnrichedChannel, fromKeyHold: Boolean = false) {
        noteGuideUserNavigation()
        // A held OK keeps auto-repeating after this fires. Suppress every select key
        // until the user actually lets go, otherwise those repeats land on the menu that
        // just opened and immediately pick whatever is focused. Checking "is the menu
        // open?" instead is a race: that guard reads channelMenuActions, a plain captured
        // val that is only correct after recomposition, and repeats arrive sooner.
        if (fromKeyHold) selectKeyGuard.blockCurrentPress(includeSyntheticBurst = true)
        channelMenu = ChannelMenuState(
            channelId = channel.id,
            channelName = channel.name,
            isFavorite = channel.id in favSet,
            hasVariants = variantCountFor(channel, variantGroups) > 1,
        )
    }

    fun playVariant(channel: EnrichedChannel) {
        noteGuideUserNavigation()
        val displayId = displayChannelIdFor(channel.id, visibleEnrichedState.value.index.byId, variantGroups) ?: channel.id
        playingChannelId = channel.id
        focusedChannelId = displayId
        epgPrefetchAnchorId = displayId
        rememberedChannelByCategory[categoryScope] = displayId
        playingCatchupProgram = null
        catchupPlaybackOffsetMs = 0L
        fullscreenGuideOpen = false
        focusChannelList(displayId)
    }

    fun playProgramInMini(channel: EnrichedChannel, program: IptvProgram?) {
        if (program?.catchupAvailable == false) return
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
        rememberedChannelByCategory[categoryScope] = playbackChannel.id
        playingChannelId = playbackChannel.id
        playingCatchupProgram = program
        catchupPlaybackOffsetMs = 0L
        fullscreenGuideOpen = false
        focusChannelList(playbackChannel.id)
    }

    fun isSamePlayingChannel(channel: EnrichedChannel): Boolean {
        val currentDisplayId = displayChannelIdFor(
            playingChannelId,
            visibleEnrichedState.value.index.byId,
            variantGroups,
        )
        return channel.id == playingChannelId || channel.id == currentDisplayId
    }

    fun playLiveFullscreen(channel: EnrichedChannel) {
        invalidateProgramActionLookup()
        noteGuideUserNavigation()
        playingChannelId = channel.id
        focusedChannelId = channel.id
        epgPrefetchAnchorId = channel.id
        rememberedChannelByCategory[categoryScope] = channel.id
        playingCatchupProgram = null
        catchupPlaybackOffsetMs = 0L
        fullscreenGuideOpen = false
        isFullScreen = true
        hudPokeSignal++
    }

    /**
     * Get the current live programme for a channel, using the same guide data
     * the EPG grid is already displaying. This is the direct source — no identity-
     * key aliasing or cross-playlist matching. If the grid shows a programme,
     * this returns it; if the grid shows "guide pending", this returns null.
     */
    fun displayedCurrentProgram(channel: EnrichedChannel): IptvProgram? =
        effectiveGuideNowNext[channel.id]?.now?.takeIf { it.isLive(guideClockMillis) }

    /**
     * When the user second-clicks a playing channel but EPG data hasn't loaded
     * yet (common when switching to a different playlist), trigger an immediate
     * network EPG fetch for that channel, then attempt the VOD resolution.
     * This prevents the feature from silently falling back to fullscreen.
     */
    fun resolveVodWithEagerFetch(channel: EnrichedChannel) {
        invalidateProgramActionLookup()
        if (!epgChannelAllowsVodSearch(channel.name, channel.source.group)) {
            playLiveFullscreen(channel)
            return
        }
        val lookupGeneration = programActionLookupGuard.beginLookup()
        programActionLookupInProgress = true
        programActionLookupJob[0] = coroutineScope.launch {
            try {
                viewModel.refreshCurrentChannelEpg(channel.id, forceNetworkForLargeList = true)
                val program = displayedCurrentProgram(channel)
                    ?: currentProgramForAction(channel)?.takeIf { it.isLive(guideClockMillis) }
                    ?: awaitLiveEpgProgram(
                        programUpdates = viewModel.uiState.map { uiState ->
                            uiState.snapshot.nowNext[channel.id]?.now
                        },
                        timeoutMillis = EpgGuideLookupTimeoutMs,
                    )
                if (!programActionLookupGuard.isCurrent(lookupGeneration)) return@launch
                if (program == null) {
                    playLiveFullscreen(channel)
                    return@launch
                }
                val match = viewModel.findEpgVodMatch(
                    title = program.title,
                    description = program.description,
                    channelName = channel.name,
                    channelGroup = channel.source.group,
                )
                if (!programActionLookupGuard.isCurrent(lookupGeneration)) return@launch
                if (!epgVodLookupCanPublish(
                        selectedProgram = program,
                        currentProgram = viewModel.uiState.value.snapshot.nowNext[channel.id]?.now
                            ?: displayedCurrentProgram(channel)
                            ?: currentProgramForAction(channel),
                        nowMillis = System.currentTimeMillis(),
                    )
                ) return@launch
                when (vodLookupResolution(match != null)) {
                    EpgInteractionAction.ShowVodDialog -> {
                        programActionVodMatch = match
                        programActionDialog = ProgramActionData(channel, program)
                    }
                    EpgInteractionAction.PlayLiveFullscreen -> playLiveFullscreen(channel)
                    else -> Unit
                }
            } finally {
                if (programActionLookupGuard.isCurrent(lookupGeneration)) {
                    programActionLookupInProgress = false
                    programActionLookupJob[0] = null
                }
            }
        }
    }

    fun resolveVodOrPlayFullscreen(channel: EnrichedChannel, program: IptvProgram) {
        invalidateProgramActionLookup()
        if (!epgChannelAllowsVodSearch(channel.name, channel.source.group)) {
            playLiveFullscreen(channel)
            return
        }
        val lookupGeneration = programActionLookupGuard.beginLookup()
        programActionLookupInProgress = true
        programActionLookupJob[0] = coroutineScope.launch {
            try {
                val match = viewModel.findEpgVodMatch(
                    title = program.title,
                    description = program.description,
                    channelName = channel.name,
                    channelGroup = channel.source.group,
                )
                if (!programActionLookupGuard.isCurrent(lookupGeneration)) return@launch
                if (
                    !epgVodLookupCanPublish(
                        selectedProgram = program,
                        currentProgram = viewModel.uiState.value.snapshot.nowNext[channel.id]?.now
                            ?: currentProgramForAction(channel),
                        nowMillis = System.currentTimeMillis(),
                    )
                ) return@launch
                when (vodLookupResolution(match != null)) {
                    EpgInteractionAction.ShowVodDialog -> {
                        programActionVodMatch = match
                        programActionDialog = ProgramActionData(channel, program)
                    }
                    EpgInteractionAction.PlayLiveFullscreen -> playLiveFullscreen(channel)
                    else -> Unit
                }
            } finally {
                if (programActionLookupGuard.isCurrent(lookupGeneration)) {
                    programActionLookupInProgress = false
                    programActionLookupJob[0] = null
                }
            }
        }
    }

    fun selectChannel(channel: EnrichedChannel, currentProgram: IptvProgram? = null) {
        val sameChannel = isSamePlayingChannel(channel)
        when (
            channelRowInteractionAction(
                isSamePlayingChannel = sameChannel,
                hasCurrentProgram = currentProgram != null,
                vodActionsEnabled = state.epgVodActionsEnabled,
            )
        ) {
            EpgInteractionAction.PlayLiveMini -> playProgramInMini(channel, null)
            EpgInteractionAction.ResolveVodOrPlayFullscreen ->
                resolveVodOrPlayFullscreen(channel, currentProgram ?: return)
            EpgInteractionAction.PlayLiveFullscreen -> {
                // Second click on the playing channel but no current EPG programme.
                // Instead of going straight to fullscreen, try an eager EPG fetch
                // so the Watch Live / Stream Now dialog can still appear. This is
                // the key fix for channels on non-first playlists where EPG data
                // hasn't been prefetched yet.
                if (sameChannel && state.epgVodActionsEnabled &&
                    epgChannelAllowsVodSearch(channel.name, channel.source.group)
                ) {
                    resolveVodWithEagerFetch(channel)
                } else {
                    playLiveFullscreen(channel)
                }
            }
            else -> Unit
        }
    }

    fun selectEpgProgram(channel: EnrichedChannel, program: IptvProgram) {
        val temporalState = when {
            program.isLive(guideClockMillis) -> EpgTemporalState.Live
            program.endUtcMillis <= guideClockMillis -> EpgTemporalState.Past
            else -> EpgTemporalState.Future
        }
        val catchupSupported = IptvGuideHistory.canReplay(channel.source, program, guideClockMillis)
        when (
            epgProgramInteractionAction(
                temporalState = temporalState,
                isSamePlayingChannel = isSamePlayingChannel(channel),
                isCatchupSupported = catchupSupported,
                vodActionsEnabled = state.epgVodActionsEnabled,
            )
        ) {
            EpgInteractionAction.PlayLiveMini -> playProgramInMini(channel, null)
            EpgInteractionAction.PlayCatchup -> playProgramInMini(channel, program)
            EpgInteractionAction.ResolveVodOrPlayFullscreen -> resolveVodOrPlayFullscreen(channel, program)
            EpgInteractionAction.PlayLiveFullscreen -> playLiveFullscreen(channel)
            EpgInteractionAction.NoOp,
            EpgInteractionAction.ShowVodDialog -> Unit
        }
    }
    fun playProgramInFullscreen(program: IptvProgram?, targetChannel: EnrichedChannel? = null) {
        if (program?.catchupAvailable == false) return
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
        rememberedChannelByCategory[categoryScope] = channel.id
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
            .addInterceptor(IptvProviderRequestGuard.shared.preflightInterceptor(playback = true))
            .addNetworkInterceptor(IptvProviderRequestGuard.shared.playbackInterceptor())
            .dns(OkHttpProvider.dns)
            .connectTimeout(8, TimeUnit.SECONDS)
            // An idle live socket should fail, not freeze startup for five minutes.
            // No total call timeout: healthy continuous streams may run indefinitely.
            .readTimeout(15, TimeUnit.SECONDS)
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
            .setLoadErrorHandlingPolicy(IptvLoadErrorHandlingPolicy())
    }
    val livePlaybackBufferProfile = remember(context) {
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as? ActivityManager
        buildLiveTvBufferProfile(
            memoryClassMb = activityManager?.memoryClass ?: 384,
            isLowRamDevice = activityManager?.isLowRamDevice == true
        )
    }
    val exoPlayer = remember(livePlaybackBufferProfile) {
        val loadControl = DefaultLoadControl.Builder()
            .setBufferDurationsMs(
                livePlaybackBufferProfile.minBufferMs,
                livePlaybackBufferProfile.maxBufferMs,
                livePlaybackBufferProfile.bufferForPlaybackMs,
                livePlaybackBufferProfile.bufferForPlaybackAfterRebufferMs
            )
            .setTargetBufferBytes(livePlaybackBufferProfile.targetBufferBytes)
            // MUST be false: with time-prioritised thresholds ExoPlayer keeps buffering
            // toward maxBufferMs even past targetBufferBytes. Buffer chunks live on the
            // Java heap, so a 4K live stream could allocate hundreds of MB — pinning the
            // 384MB-capped heap at 0% free. That caused OOM crashes while navigating the
            // Live TV page AND the heavy initial buffering (bandwidth burned prefetching
            // minutes of stream while GC stalls starved the player).
            .setPrioritizeTimeOverSizeThresholds(false)
            .setBackBuffer(livePlaybackBufferProfile.backBufferMs, true)
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

    var playbackQuality by remember(exoPlayer) { mutableStateOf<LivePlaybackQuality?>(null) }
    DisposableEffect(exoPlayer) {
        val listener = LivePlaybackQualityListener(exoPlayer) { playbackQuality = it }
        exoPlayer.addListener(listener)
        onDispose { exoPlayer.removeListener(listener) }
    }
    val playingDisplayChannel = remember(playingChannel, playbackQuality) {
        playingChannel?.let { it.copy(quality = it.displayQuality(playbackQuality)) }
    }

    var playerPositionMs by remember { mutableLongStateOf(0L) }
    var playerDurationMs by remember { mutableLongStateOf(0L) }
    var playerIsPlaying by remember { mutableStateOf(false) }
    var playerPlayWhenReady by remember { mutableStateOf(true) }
    var playerIsBuffering by remember { mutableStateOf(false) }
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
            playerIsBuffering = exoPlayer.playbackState == Player.STATE_BUFFERING
            delay(if (playingCatchupProgram != null) 500L else 1_500L)
        }
    }

    val lifecycleOwner = LocalLifecycleOwner.current
    val sportsHiddenPlayback by rememberUpdatedState(sportsSelected && !isFullScreen)
    var resumeAfterSports by remember(exoPlayer) { mutableStateOf(false) }
    LaunchedEffect(sportsSelected, isFullScreen, exoPlayer) {
        if (sportsSelected && !isFullScreen) {
            resumeAfterSports = resumeAfterSports || exoPlayer.playWhenReady
            exoPlayer.pause()
        } else if (resumeAfterSports) {
            resumeAfterSports = false
            exoPlayer.play()
        }
    }
    DisposableEffect(lifecycleOwner) {
        val obs = LifecycleEventObserver { _, ev ->
            when (ev) {
                Lifecycle.Event.ON_PAUSE -> exoPlayer.pause()
                Lifecycle.Event.ON_RESUME -> {
                    if (playingChannelId != null && !sportsHiddenPlayback) exoPlayer.play()
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
    var lastPreparedIsHls by remember { mutableStateOf(false) }
    var lastPreparedMimeType by remember { mutableStateOf<String?>(null) }
    var lastPreparedHeaders by remember { mutableStateOf<Map<String, String>>(emptyMap()) }
    var lastPreparedCatchupOffsetMs by remember { mutableLongStateOf(-1L) }
    var playerRetryCount by remember { mutableIntStateOf(0) }
    var playbackDiagnostic by remember { mutableStateOf<PlaybackDiagnostic?>(null) }

    fun prepareStream(
        stream: String,
        isHls: Boolean,
        headers: Map<String, String>,
        resetRetry: Boolean,
        initialPositionMs: Long = 0L,
        drmInfo: com.arflix.tv.data.model.DrmInfo? = null,
        forcePrepare: Boolean = false,
        resolvedMimeType: String? = null,
    ) {
        val mergedHeaders = (baseRequestHeaders + headers).safePlaybackHeaders()

        if (!forcePrepare &&
            stream == lastPreparedStreamUrl &&
            exoPlayer.currentMediaItem?.mediaId == playingChannelId.orEmpty() &&
            isHls == lastPreparedIsHls &&
            headers == lastPreparedHeaders &&
            (playingCatchupProgram == null || catchupUrlAnchorOffsetMs == lastPreparedCatchupOffsetMs)
        ) {
            return
        }

        playerIsBuffering = true
        exoPlayer.stop()
        exoPlayer.clearMediaItems()
        val mediaItem = MediaItem.Builder()
            .setUri(stream)
            .setMediaId(playingChannelId.orEmpty())
            .apply {
                if (isHls) {
                    setMimeType(MimeTypes.APPLICATION_M3U8)
                } else if (resolvedMimeType != null) {
                    // What the server actually answered beats anything read off the URL.
                    setMimeType(resolvedMimeType)
                } else if (looksLikeMpegTsUrl(stream)) {
                    setMimeType(MimeTypes.VIDEO_MP2T)
                }
                if (playingCatchupProgram == null) {
                    setLiveConfiguration(buildLiveTvConfiguration())
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
        // Per-source factories keep an old load from inheriting the next channel's headers.
        val sourceHttpFactory = OkHttpDataSource.Factory(iptvHttpClient)
            .setDefaultRequestProperties(mergedHeaders)
        val sourceDataFactory = androidx.media3.datasource.DataSource.Factory {
            val upstream = sourceHttpFactory.createDataSource()
            if (isHls) upstream else IptvHlsDetectingDataSource(upstream, stream)
        }
        val source = DefaultMediaSourceFactory(context).setDataSourceFactory(sourceDataFactory)
            .setLoadErrorHandlingPolicy(IptvLoadErrorHandlingPolicy()).createMediaSource(mediaItem)
        if (initialPositionMs > 0L) exoPlayer.setMediaSource(source, initialPositionMs)
        else exoPlayer.setMediaSource(source)
        exoPlayer.prepare()
        exoPlayer.playWhenReady = true
        exoPlayer.play()
        lastPreparedStreamUrl = stream
        lastPreparedIsHls = isHls
        lastPreparedMimeType = resolvedMimeType
        lastPreparedHeaders = headers
        lastPreparedCatchupOffsetMs = if (playingCatchupProgram != null) catchupUrlAnchorOffsetMs else -1L
        if (resetRetry) playerRetryCount = 0
        if (resetRetry) {
            playbackDiagnostic = PlaybackDiagnostic(
                title = if (playingCatchupProgram != null && initialPositionMs > 0L) context.getString(R.string.live_diag_seeking_catchup) else context.getString(R.string.live_diag_starting_stream),
                detail = playingChannel?.name ?: context.getString(R.string.live_diag_preparing_source),
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

    fun seekToPosition(targetMs: Long) {
        if (playingCatchupProgram != null) {
            val delta = targetMs - playerPositionMs
            seekCatchupBy(delta)
        } else {
            val currentNow = currentNowNext?.now
            val ch = playingChannel
            val currentElapsed = if (currentNow != null && currentNow.startUtcMillis > 0L) {
                (System.currentTimeMillis() - currentNow.startUtcMillis).coerceAtLeast(0L)
            } else {
                playerPositionMs
            }
            val boundedTarget = targetMs.coerceIn(0L, currentElapsed)
            if (boundedTarget >= currentElapsed) {
                hudPokeSignal++
                return
            }
            if (ch != null && currentNow != null && IptvGuideHistory.canReplay(ch.source, currentNow, System.currentTimeMillis())) {
                System.err.println("[IPTV-Catchup] auto-switch catchup program=${currentNow.title} targetMs=$boundedTarget")
                playingCatchupProgram = currentNow
                catchupPlaybackOffsetMs = boundedTarget
                playerPositionMs = boundedTarget
                lastPreparedStreamUrl = null
                playerIsBuffering = true
                hudPokeSignal++
            } else {
                val currentExo = exoPlayer.currentPosition
                val maxExo = exoPlayer.duration.takeIf { it > 0L && it != C.TIME_UNSET } ?: 60_000L
                val delta = boundedTarget - currentElapsed
                val newExo = (currentExo + delta).coerceIn(0L, maxExo)
                exoPlayer.seekTo(newExo)
                hudPokeSignal++
            }
        }
    }

    fun returnCatchupToLive() {
        if (playingCatchupProgram == null) return
        System.err.println("[IPTV-Catchup] return-live channel=${playingChannelId.orEmpty()}")
        playingCatchupProgram = null
        catchupPlaybackOffsetMs = 0L
        fullscreenGuideOpen = false
        lastPreparedStreamUrl = null
        playerIsBuffering = true
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
        // Probing a replacement stream must not run alongside the old stream on
        // subscriptions that allow only one video connection.
        exoPlayer.stop()
        exoPlayer.clearMediaItems()
        lastPreparedStreamUrl = null
        playerIsBuffering = true
        val sourceChannel = playingChannel?.source
        val streamProgram = playingCatchupProgram?.shiftedForCatchup(catchupUrlAnchorOffsetMs)
        val target = runCatching {
            if (sourceChannel != null) {
                viewModel.resolvePlayableStreamUrl(sourceChannel, streamProgram, catchupAttempt = 0)
            } else {
                IptvPlaybackTarget(rawStream)
            }
        }.getOrElse { error ->
            if (error is kotlinx.coroutines.CancellationException) throw error
            playerIsBuffering = false
            playbackDiagnostic = PlaybackDiagnostic(
                title = if (playingCatchupProgram != null) context.getString(R.string.live_diag_catchup_unavailable) else context.getString(R.string.live_diag_playback_failed),
                detail = error.message ?: context.getString(R.string.live_diag_no_playable_stream),
                severity = PlaybackDiagnosticSeverity.Error,
            )
            System.err.println(
                "[IPTV] Failed to resolve playable stream catchup=${playingCatchupProgram != null} " +
                    "channel=${sourceChannel?.id.orEmpty()} reason=${error.message}"
            )
            return@LaunchedEffect
        }
        val headers = sourceChannel?.requestHeaders.orEmpty()
        val initialSeekMs = if (playingCatchupProgram != null) catchupInSegmentSeekMs else 0L

        prepareStream(
            stream = target.url,
            isHls = target.isHls,
            headers = headers,
            resetRetry = true,
            initialPositionMs = initialSeekMs,
            drmInfo = playingChannel?.source?.drmInfo,
            resolvedMimeType = target.mimeType,
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
        lastPreparedIsHls,
        lastPreparedHeaders,
        playingChannel?.id,
        playingCatchupProgram,
        catchupPlaybackOffsetMs
    ) {
        var retryJob: Job? = null
        val liveWindowRecovery = LiveWindowRecovery(android.os.SystemClock::elapsedRealtime)
        val listener = object : Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {
                playerIsBuffering = (playbackState == Player.STATE_BUFFERING)
                if (playbackState == Player.STATE_READY) {
                    playbackDiagnostic = null
                    playerIsBuffering = false
                }
            }

            override fun onIsLoadingChanged(isLoading: Boolean) {
                if (exoPlayer.playbackState == Player.STATE_BUFFERING || (isLoading && !exoPlayer.isPlaying)) {
                    playerIsBuffering = true
                } else if (exoPlayer.playbackState == Player.STATE_READY) {
                    playerIsBuffering = false
                }
            }

            override fun onPlayerError(error: PlaybackException) {
                playerIsBuffering = false
                val prepared = lastPreparedStreamUrl ?: return
                val detectedHls = error.iptvHlsFormatDetected()
                if (detectedHls?.sourceUrl == prepared && !lastPreparedIsHls &&
                    exoPlayer.currentMediaItem?.mediaId == playingChannelId.orEmpty()) {
                    retryJob?.cancel()
                    viewModel.rememberPlaybackHls(currentStreamUrl ?: prepared, lastPreparedHeaders, prepared)
                    prepareStream(prepared, isHls = true, headers = lastPreparedHeaders, resetRetry = false,
                        initialPositionMs = playingChannel?.source?.catchupInSegmentSeekOffset(catchupPlaybackOffsetMs) ?: 0L,
                        drmInfo = playingChannel?.source?.drmInfo, forcePrepare = true)
                    return
                }
                if (error.errorCode == PlaybackException.ERROR_CODE_BEHIND_LIVE_WINDOW &&
                    liveWindowRecovery.claim(isCatchup = playingCatchupProgram != null)
                ) {
                    retryJob?.cancel()
                    playbackDiagnostic = null
                    playerIsBuffering = true
                    // Re-resolving the same URL at position zero leaves us behind
                    // the HLS live window. Reuse the player and jump to its live edge.
                    exoPlayer.seekToDefaultPosition()
                    exoPlayer.prepare()
                    exoPlayer.playWhenReady = true
                    System.err.println("[IPTV] Recovered expired live window at default live position")
                    return
                }
                val preparedIsHls = lastPreparedIsHls
                val unsupportedContainer = error.errorCode == PlaybackException.ERROR_CODE_PARSING_CONTAINER_UNSUPPORTED
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
                } else if (unsupportedContainer) {
                    // One bounded content-type recovery, not repeated identical prepares.
                    1
                } else {
                    3
                }
                val httpCode = httpResponseCode(error)
                if (!shouldRetryLiveTvPlayback(httpCode, nextAttempt, maxRetryCount, retryProgram != null) ||
                    isIptvProviderRequestPaused(error) ||
                    iptvProviderCooldownMs(httpCode ?: 0, null, 0L) > 0L) {
                    playbackDiagnostic = PlaybackDiagnostic(
                        title = context.getString(R.string.live_diag_playback_failed),
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
                retryJob?.cancel()
                retryJob = coroutineScope.launch {
                    delay(1_000L * nextAttempt)
                    val retryTarget = runCatching {
                        if (shouldReusePreparedLiveHls(preparedIsHls, retryProgram != null, unsupportedContainer, httpCode)) {
                            // A playlist reset must not discard the HLS type we
                            // already detected from an extensionless or .ts URL.
                            IptvPlaybackTarget(prepared, isHls = true)
                        } else if (retryChannel != null) {
                            viewModel.resolvePlayableStreamUrl(
                                channel = retryChannel,
                                program = retryStreamProgram ?: retryProgram,
                                forceRefresh = true,
                                catchupAttempt = if (retryProgram != null) nextAttempt else 0,
                                probeKnownUrl = unsupportedContainer || isMissingPlaybackResource(httpCode),
                            )
                        } else {
                            IptvPlaybackTarget(prepared, preparedIsHls)
                        }
                    }.getOrElse { resolveError ->
                        if (resolveError is kotlinx.coroutines.CancellationException) throw resolveError
                        playbackDiagnostic = PlaybackDiagnostic(
                            title = if (retryProgram != null) context.getString(R.string.live_diag_catchup_unavailable) else context.getString(R.string.live_diag_playback_failed),
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
                            "candidates=$catchupCandidateCount url=${redactPlaybackUrl(retryTarget.url)}"
                    )
                    playbackDiagnostic = PlaybackDiagnostic(
                        title = context.getString(R.string.live_diag_retrying_source),
                        detail = "Attempt $nextAttempt/$maxRetryCount after ${classifyPlaybackError(error)}",
                        severity = PlaybackDiagnosticSeverity.Warning,
                    )
                    prepareStream(
                        stream = retryTarget.url,
                        isHls = retryTarget.isHls,
                        headers = retryHeaders,
                        resetRetry = false,
                        initialPositionMs = retryChannel?.catchupInSegmentSeekOffset(catchupPlaybackOffsetMs) ?: 0L,
                        drmInfo = retryChannel?.drmInfo,
                        forcePrepare = true,
                        resolvedMimeType = retryTarget.mimeType,
                    )
                }
            }
        }
        exoPlayer.addListener(listener)
        onDispose {
            retryJob?.cancel()
            exoPlayer.removeListener(listener)
        }
    }

    // Default IPTV entry is the playlist/category rail. It used to land on the
    // Search row, which is what users reported: the selector opened inside the
    // search box and — because "down" from search selects the first category,
    // which does not exist until the playlist has parsed — stayed stuck there
    // through the whole load. Land on the categories instead; search is one
    // press up from there.
    LaunchedEffect(visibleEnrichedState.value !== EnrichedChannels.Empty) {
        val entry = LiveTvStartup.entryFocus(
            isTouchDevice = isTouchDevice,
            hasChannels = visibleEnrichedState.value !== EnrichedChannels.Empty,
        )
        if (entry == LiveTvStartup.EntryFocus.CATEGORY_LIST) {
            noteGuideUserNavigation()
            focusZone = LiveTvFocusZone.CATEGORY_LIST
            focusCategoryRailSignal += 1
        }
    }

    LaunchedEffect(state.isConfigured, visibleEnrichedState.value) {
        if (!isTouchDevice && !state.isConfigured && visibleEnrichedState.value === EnrichedChannels.Empty) {
            delay(100L)
            runCatching { emptyStateButtonFocus.requestFocus() }
        }
    }

    BackHandler(enabled = searchOpen) { searchOpen = false }
    val channelMenuActions = channelMenu?.let { menu ->
        buildChannelMenuActions(
            isFavorite = menu.isFavorite,
            hasVariants = menu.hasVariants,
            onToggleFavorite = {
                channelMenu = null
                viewModel.setFavoriteChannel(menu.channelId, !menu.isFavorite)
            },
            onMoveUp = {
                channelMenu = null
                viewModel.moveFavoriteChannelUp(menu.channelId)
            },
            onMoveDown = {
                channelMenu = null
                viewModel.moveFavoriteChannelDown(menu.channelId)
            },
            onOpenVariants = {
                channelMenu = null
                visibleEnrichedState.value.index.byId[menu.channelId]?.let { openVariantPicker(it) }
            },
        )
    }.orEmpty()

    BackHandler(enabled = channelMenu != null) { channelMenu = null }
    BackHandler(enabled = !searchOpen && variantPickerChannel != null) { variantPickerChannel = null }
    BackHandler(enabled = !searchOpen && isFullScreen && fullscreenGuideOpen) {
        fullscreenGuideOpen = false
    }
    BackHandler(enabled = !searchOpen && isFullScreen && !fullscreenGuideOpen) {
        if (hudEngaged) {
            hudEngaged = false
            hudHideSignal++
        } else if (playingCatchupProgram != null) {
            returnCatchupToLive()
        } else {
            exitFullScreenPlayback()
        }
    }
    BackHandler(enabled = !searchOpen && channelMenu == null && variantPickerChannel == null && !isFullScreen) {
        when (LiveTvStartup.guideBackAction(isTouchDevice, categoryDrawerOpen)) {
            LiveTvStartup.GuideBackAction.OPEN_CATEGORIES -> openCategoryDrawer()
            LiveTvStartup.GuideBackAction.EXIT_TV -> onBack()
        }
    }

    val channelNumberExactName = remember(channelNumberBuffer, allDisplayChannels) {
        allDisplayChannels.firstOrNull { it.number.toString() == channelNumberBuffer }?.name
    }
    val channelNumberMatchCount = remember(channelNumberBuffer, allDisplayChannels) {
        if (channelNumberBuffer.isBlank()) {
            0
        } else {
            allDisplayChannels.count { it.number.toString().startsWith(channelNumberBuffer) }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LiveColors.Bg)
            .then(
                if (!isTouchDevice) {
                    Modifier.onPreviewKeyEvent { event ->
                        // The channel menu is a non-focusable Popup, so the focused channel
                        // row still receives keys. Handle it here at the outermost preview
                        // node and swallow everything — including KeyUp — while it is open,
                        // otherwise the row underneath navigates or opens the channel.
                        val isSelectKey = event.key == Key.DirectionCenter || event.key == Key.Enter
                        if (isSelectKey) {
                            if (selectKeyGuard.consume(
                                    downTime = event.nativeKeyEvent.downTime,
                                    eventTime = event.nativeKeyEvent.eventTime,
                                    isDown = event.type == KeyEventType.KeyDown,
                                    repeatCount = event.nativeKeyEvent.repeatCount,
                                )) return@onPreviewKeyEvent true
                        }
                        val menu = channelMenu
                        if (menu != null && channelMenuActions.isNotEmpty()) {
                            if (event.type != KeyEventType.KeyDown) {
                                return@onPreviewKeyEvent true
                            }
                            if (isSelectKey && event.nativeKeyEvent.repeatCount > 0) {
                                return@onPreviewKeyEvent true
                            }
                            // Keep a held menu action and its synthetic repeats out of
                            // the underlying row, while accepting a fresh deliberate click.
                            if (isSelectKey) selectKeyGuard.blockCurrentPress(includeSyntheticBurst = true)
                            return@onPreviewKeyEvent when (event.key) {
                                Key.DirectionUp -> {
                                    channelMenu = menu.copy(
                                        focusedIndex = (menu.focusedIndex - 1).coerceAtLeast(0)
                                    )
                                    true
                                }
                                Key.DirectionDown -> {
                                    channelMenu = menu.copy(
                                        focusedIndex = (menu.focusedIndex + 1)
                                            .coerceAtMost(channelMenuActions.lastIndex)
                                    )
                                    true
                                }
                                Key.DirectionCenter, Key.Enter -> {
                                    channelMenuActions
                                        .getOrNull(menu.focusedIndex.coerceIn(0, channelMenuActions.lastIndex))
                                        ?.onClick?.invoke()
                                    true
                                }
                                Key.Back, Key.Escape -> {
                                    channelMenu = null
                                    true
                                }
                                else -> true
                            }
                        }
                        // The whole OK gesture for a focused channel row is owned here.
                        // ChannelRow cannot own it: it is a recyclable LazyColumn item, and
                        // the list rebuilds the moment the menu opens, wiping any per-row
                        // latch mid-hold. Press = tune, hold = menu, and every select key of
                        // the gesture is consumed so nothing downstream sees a stray click.
                        if (
                            isSelectKey &&
                            !searchOpen &&
                            !isFullScreen &&
                            channelMenu == null &&
                            focusZone == LiveTvFocusZone.CHANNEL_LIST
                        ) {
                            // Prefer the object the focused row reported; fall back to the
                            // index only when there is none. The index covers just the paged
                            // window, so it cannot resolve an out-of-window favourite.
                            val focusedChannel =
                                focusedChannelObject[0]
                                    ?: focusedChannelId
                                        ?.let { id -> visibleEnrichedState.value.index.byId[id] }
                            when {
                                event.type == KeyEventType.KeyDown &&
                                    event.nativeKeyEvent.repeatCount == 0 -> {
                                    selectKeyGuard.holdHandled = false
                                }
                                event.type == KeyEventType.KeyDown && !selectKeyGuard.holdHandled -> {
                                    // First auto-repeat of a held OK — that is the long press.
                                    selectKeyGuard.holdHandled = true
                                    focusedChannel?.let { openChannelMenu(it, fromKeyHold = true) }
                                }
                                event.type == KeyEventType.KeyUp -> {
                                    val wasHold = selectKeyGuard.holdHandled
                                    selectKeyGuard.holdHandled = false
                                    if (!wasHold && focusedChannel != null) {
                                        selectChannel(focusedChannel, displayedCurrentProgram(focusedChannel))
                                    }
                                }
                            }
                            return@onPreviewKeyEvent true
                        }
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
                                when (event.key.mirrorHorizontalForRtl(isRtl)) {
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
                            LiveTvFocusZone.SPORTS -> false
                        }
                    }
                } else {
                    Modifier
                }
            )
    ) {
        LiveTvRenderBoundary {
            // Content area starts below the translucent top bar so it doesn't get
            // overwritten.
            if (isFullScreen) {
                // Full-screen playback only — no grid rendered so the single
                // PlayerView owns ExoPlayer.
            } else if (!state.isConfigured && state.snapshot.channels.isEmpty()) {
            EmptyStatePane(
                message = stringResource(R.string.live_empty_no_playlist),
                actionLabel = stringResource(R.string.live_btn_open_settings),
                onAction = onNavigateToIptvSettings ?: onNavigateToSettings,
                isFocused = focusZone != LiveTvFocusZone.TOPBAR,
                focusRequester = emptyStateButtonFocus,
                onMoveUp = {
                    focusZone = LiveTvFocusZone.TOPBAR
                    topBarFocusIndex = topBarSelectedIndex(SidebarItem.TV, hasProfile).coerceIn(0, maxTopBarIndex)
                }
            )
        } else {
            // Keep drawer and guide aligned below the unchanged app navigation.
            if (useTouchRail) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = contentTopPadding),
                ) {
                    if (playlistCategorySections.isEmpty()) {
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
                    }
                    if (!sportsSelected) MiniPlayerRow(
                        focusedProgrammeProvider = { focusedProgramme.takeIf { focusZone == LiveTvFocusZone.EPG } },
                        exoPlayer = exoPlayer,
                        channel = playingDisplayChannel,
                        clockTickMillis = guideClockMillis,
                        nowNext = currentNowNext,
                        onFavoriteToggle = { viewModel.toggleFavoriteChannel(it) },
                        favoriteSet = favSet,
                        onFullscreenClick = openFullScreenPlayer,
                        variantCount = playingChannel?.let { variantCountFor(it, variantGroups) } ?: 1,
                        onOpenVariants = playingChannel?.let { channel -> { openVariantPicker(channel) } },
                        compact = true,
                        landscapeCompact = landscapeCompactMiniPlayer,
                        playerActive = miniPlayerActive,
                        modifier = Modifier.fillMaxWidth(),
                    )
                    if (!categoryDrawerOpen && !sportsSelected) Row(Modifier.fillMaxWidth().height(44.dp), verticalAlignment = Alignment.CenterVertically) {
                        androidx.compose.material3.TextButton(onClick = { categoryDrawerOpen = true }) {
                            Text("Categories", color = LiveColors.Fg)
                        }
                        Text(sportsSidebarTree.byId(selectedCategoryId)?.label ?: "All channels", color = LiveColors.FgDim,
                            maxLines = 1, overflow = TextOverflow.Ellipsis)
                    }
                    AnimatedVisibility(visible = categoryDrawerOpen,
                        enter = androidx.compose.animation.expandVertically(animationSpec = tween(200)) + fadeIn(tween(150)),
                        exit = androidx.compose.animation.shrinkVertically(animationSpec = tween(200)) + fadeOut(tween(100))) {
                    TouchCategoryRail(
                        tree = sportsSidebarTree,
                        selectedId = if (sportsSelected) SPORTS_GUIDE_CATEGORY else selectedCategoryId,
                        playlistSections = playlistCategorySections,
                        onSelect = { id ->
                            requestCategorySelection(id)
                            categoryDrawerOpen = false
                        },
                        onOpenSearch = { searchOpen = true },
                        modifier = Modifier.fillMaxWidth(),
                    )
                    }
                    if (sportsSelected) SportsGuidePane(
                        events = illustratedSportsEvents, now = guideClockMillis, loading = sportsLoading || sportsMetadataLoading || sportsBroadcastLoading || sportsCatalogueLoading, clockFormat = sportsClockFormat,
                        failed = sportsError, onRetry = { sportsRefresh++ }, providerNames = sportsProviderNames,
                        focusSignal = sportsFocusSignal,
                        onContentFocused = { focusZone = LiveTvFocusZone.SPORTS },
                        onOpenCategories = { openCategoryDrawer() },
                        onPlay = { channel -> playLiveFullscreen(channel.enrich(0)) },
                        modifier = Modifier.weight(1f),
                    ) else EpgGrid(
                        channels = filteredChannels,
                        playbackQuality = playbackQuality,
                        totalChannelCount = selectedCategoryTotalCount,
                        clockTickMillis = guideClockMillis,
                        nowNext = effectiveGuideNowNext,
                        epgLoadingChannelIds = state.epgLoadingChannelIds,
                        epgAttemptedChannelIds = state.epgAttemptedChannelIds,
                        isGuideBackfillLoading = false,
                        hasGuideSource = state.hasPotentialGuideSource,
                        selectedChannelId = selectedDisplayChannelId,
                        playingChannelId = playingDisplayChannelId ?: playingChannelId,
                        focusSelectedChannelSignal = focusSelectedChannelSignal,
                        focusEpgSignal = focusEpgSignal,
                        focusMode = if (focusZone == LiveTvFocusZone.EPG) {
                            EpgGridFocusMode.Epg
                        } else {
                            EpgGridFocusMode.ChannelList
                        },
                        scrollResetKey = filteredChannelsScopeKey,
                        compact = true,
                        gridFocused = focusZone == LiveTvFocusZone.EPG,
                        backHandlingEnabled = channelMenu == null && !searchOpen && variantPickerChannel == null,
                        onChannelSelect = { channel ->
                            focusZone = LiveTvFocusZone.CHANNEL_LIST
                            val currentProgram = displayedCurrentProgram(channel)
                            selectChannel(channel, currentProgram)
                        },
                        onProgramSelect = { channel, program ->
                            program?.let { selectEpgProgram(channel, it) }
                        },
                        onChannelFocused = { channel -> commitFocusedChannel(channel) },
                        onProgramFocused = { channel, programme -> focusedProgramme = channel to programme },
                        onChannelLongPress = { channel, fromKeyHold -> openChannelMenu(channel, fromKeyHold) },
                        favorites = favSet,
                        variantCountFor = { channel -> variantCountFor(channel, variantGroups) },
                        onMoveLeftFromChannels = { focusPlaylistSearch() },
                        onEnterEpg = { channel -> focusEpg(channel.id) },
                        onExitEpg = { channel -> focusChannelList(channel?.id ?: focusedChannelId ?: playingChannelId) },
                        onRequestNextChannels = ::requestGuideWindowAfter,
                        onVisibleChannelRange = ::onGuideVisibleRange,
                        modifier = Modifier.fillMaxSize(),
                    )
                }
            } else LiveDrawerWorkspace(expanded = sidebarExpanded,
                sidebarWidth = LiveDims.SidebarExpanded,
                contentKey = "${currentProfile?.id}:${if (sportsSelected) "sports" else "guide"}", sidebar = {
                CategorySidebar(
                    tree = sportsSidebarTree,
                    selectedId = if (sportsSelected) SPORTS_GUIDE_CATEGORY else selectedCategoryId,
                    playlistSections = playlistCategorySections,
                    expanded = sidebarExpanded,
                    fixedViewport = true,
                    sidebarWidth = LiveDims.SidebarExpanded,
                    providers = providerFilters,
                    selectedProviderId = selectedProviderId,
                    onProviderSelect = { id ->
                        noteGuideUserNavigation()
                        selectedProviderId = id
                        selectedCategoryId = "all"
                        focusedChannelId = null
                        epgPrefetchAnchorId = null
                    },
                    listState = sidebarListState,
                    focusRequester = sidebarFocus,
                    onSelect = { id ->
                        requestCategorySelection(id)
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
                    onMoveCategoryUp = { playlistId, groupName ->
                        viewModel.moveGroupUp(playlistId, groupName)
                    },
                    onMoveCategoryToTop = { playlistId, groupName ->
                        viewModel.moveGroupToTop(playlistId, groupName)
                    },
                    onMoveCategoryDown = { playlistId, groupName ->
                        viewModel.moveGroupDown(playlistId, groupName)
                    },
                    lockedGroupKeys = lockedGroupSet,
                    onToggleCategoryLock = ::requestCategoryLockToggle,
                    onFocusEnter = {
                        if (focusZone != LiveTvFocusZone.TOPBAR) {
                            focusZone = LiveTvFocusZone.CATEGORY_LIST
                        }
                    },
                    onMoveRight = {
                        categoryDrawerOpen = false
                        focusGuideAfterDrawerClose = false
                        if (sportsSelected) {
                            focusZone = LiveTvFocusZone.SPORTS
                            sportsFocusSignal++
                        } else {
                        val target = rememberedChannelByCategory[categoryScope]
                            ?.takeIf { it in filteredChannelIndexById }
                            ?: playingChannelId?.let { displayChannelIdFor(it, visibleEnrichedState.value.index.byId, variantGroups) }
                                ?.takeIf { it in filteredChannelIndexById }
                            ?: filteredChannels.firstOrNull()?.id
                        focusChannelList(target)
                        }
                    },
                    onMoveUpFromSearch = {
                        topBarFocusIndex = topBarSelectedIndex(SidebarItem.TV, hasProfile)
                            .coerceIn(0, maxTopBarIndex)
                        focusZone = LiveTvFocusZone.TOPBAR
                    },
                    focusSearchSignal = focusSearchCategorySignal,
                    focusCategorySignal = focusCategoryRailSignal,
                    isTouchDevice = isTouchDevice,
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(top = contentTopPadding)
                        .focusGroup(),
                )

            }, content = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = contentTopPadding),
                ) {
                    if (!sportsSelected) MiniPlayerRow(
                        focusedProgrammeProvider = { focusedProgramme.takeIf { focusZone == LiveTvFocusZone.EPG } },
                        exoPlayer = exoPlayer,
                        channel = playingDisplayChannel,
                        clockTickMillis = guideClockMillis,
                        nowNext = currentNowNext,
                        onFavoriteToggle = { viewModel.toggleFavoriteChannel(it) },
                        favoriteSet = favSet,
                        onFullscreenClick = openFullScreenPlayer,
                        onProgrammeGuideClick = { (focusedChannelId ?: playingDisplayChannelId)?.let(::focusEpg) },
                        variantCount = playingChannel?.let { variantCountFor(it, variantGroups) } ?: 1,
                        onOpenVariants = playingChannel?.let { channel -> { openVariantPicker(channel) } },
                        compact = compactTouchLayout,
                        playerActive = miniPlayerActive,
                        modifier = Modifier.fillMaxWidth(),
                    )
                    if (sportsSelected) SportsGuidePane(
                        events = illustratedSportsEvents, now = guideClockMillis, loading = sportsLoading || sportsMetadataLoading || sportsBroadcastLoading || sportsCatalogueLoading, clockFormat = sportsClockFormat,
                        failed = sportsError, onRetry = { sportsRefresh++ }, providerNames = sportsProviderNames,
                        focusSignal = sportsFocusSignal,
                        onContentFocused = { focusZone = LiveTvFocusZone.SPORTS; categoryDrawerOpen = false },
                        onOpenCategories = { openCategoryDrawer() },
                        onPlay = { channel -> playLiveFullscreen(channel.enrich(0)) },
                        modifier = Modifier.weight(1f),
                        sidebarOpen = sidebarExpanded,
                    ) else EpgGrid(
                        channels = filteredChannels,
                        playbackQuality = playbackQuality,
                        categoryTitle = sportsSidebarTree.byId(selectedCategoryId)?.label ?: "All channels",
                        totalChannelCount = selectedCategoryTotalCount,
                        clockTickMillis = guideClockMillis,
                        nowNext = effectiveGuideNowNext,
                        epgLoadingChannelIds = state.epgLoadingChannelIds,
                        epgAttemptedChannelIds = state.epgAttemptedChannelIds,
                        isGuideBackfillLoading = false,
                        hasGuideSource = state.hasPotentialGuideSource,
                        selectedChannelId = selectedDisplayChannelId,
                        playingChannelId = playingDisplayChannelId ?: playingChannelId,
                        focusSelectedChannelSignal = focusSelectedChannelSignal,
                        focusEpgSignal = focusEpgSignal,
                        focusMode = if (focusZone == LiveTvFocusZone.EPG) {
                            EpgGridFocusMode.Epg
                        } else {
                            EpgGridFocusMode.ChannelList
                        },
                        scrollResetKey = filteredChannelsScopeKey,
                        compact = compactTouchLayout,
                        gridFocused = focusZone == LiveTvFocusZone.CHANNEL_LIST || focusZone == LiveTvFocusZone.EPG,
                        backHandlingEnabled = channelMenu == null && !searchOpen && variantPickerChannel == null,
                        onChannelSelect = { channel ->
                            val currentProgram = displayedCurrentProgram(channel)
                            selectChannel(channel, currentProgram)
                        },
                        onProgramSelect = { channel, program ->
                            program?.let { selectEpgProgram(channel, it) }
                        },
                        onChannelFocused = { channel -> commitFocusedChannel(channel) },
                        onProgramFocused = { channel, programme -> focusedProgramme = channel to programme },
                        onChannelLongPress = { channel, fromKeyHold -> openChannelMenu(channel, fromKeyHold) },
                        favorites = favSet,
                        variantCountFor = { channel -> variantCountFor(channel, variantGroups) },
                        // onOpenVariants dropped: quality variants are now an item in the
                        // channel long-press menu, so EpgGrid no longer takes that callback.
                        onMoveLeftFromChannels = { openCategoryDrawer() },
                        onEnterEpg = { channel -> focusEpg(channel.id) },
                        onExitEpg = { channel -> focusChannelList(channel?.id ?: focusedChannelId ?: playingChannelId) },
                        onRequestNextChannels = ::requestGuideWindowAfter,
                        onVisibleChannelRange = ::onGuideVisibleRange,
                        channelColumnWidthOverride = guideChannelColumnWidth,
                        modifier = Modifier
                            .fillMaxSize()
                            .then(if (!isTouchDevice) Modifier.focusRequester(epgFocus) else Modifier),
                    )
                }
            })
            }
        }

        // Full-screen playback: same ExoPlayer, covers the entire screen.
        // Cinematic 3D Depth Push:
        // Video stays anchored at the exact screen center (0.5f, 0.5f).
        // Entering punches forward from depth (0.92 -> 1.0); exiting eases back into depth (1.0 -> 0.92).
        LiveTvRenderBoundary {
            if (fsProgress > 0f && playingChannel != null) {
                val depthScale = 0.92f + 0.08f * fsProgress

                BackHandler(enabled = isFullScreen) {
                    if (fullscreenGuideOpen) {
                        fullscreenGuideOpen = false
                        hudPokeSignal++
                    } else if (!quickZapOpen) {
                        if (hudEngaged) {
                            hudEngaged = false
                            hudHideSignal++
                        } else if (playingCatchupProgram != null) {
                            returnCatchupToLive()
                        } else {
                            exitFullScreenPlayback()
                        }
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer {
                            transformOrigin = TransformOrigin(0.5f, 0.5f)
                            scaleX = depthScale
                            scaleY = depthScale
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
                            if (firstPress && playingCatchupProgram != null) {
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
                            if (firstPress) {
                                digitForTvKeyCode(ev.nativeKeyEvent.keyCode)?.let { digit ->
                                    hudPokeSignal++
                                    return@onPreviewKeyEvent handleChannelNumberDigit(digit)
                                }
                            }

                            // Everything below is the remote's fullscreen key plan.
                            // Touch devices drive playback by tapping and keep the
                            // behaviour they have always had.
                            if (isTouchDevice) return@onPreviewKeyEvent false

                            // Up/Down and the remote's channel keys zap straight
                            // away — no overlay, no extra press. Up and CH+ go to
                            // the next channel, the way a television has always
                            // answered CH+. In catchup the
                            // arrows stay with the HUD instead, because there the
                            // user wants to seek inside the recording rather than
                            // leave it; the channel keys still zap and drop back
                            // to live, which is what leaving a recording means.
                            val zapDelta = when (ev.key) {
                                Key.ChannelUp -> 1
                                Key.ChannelDown -> -1
                                Key.DirectionUp -> if (playingCatchupProgram == null) 1 else 0
                                Key.DirectionDown -> if (playingCatchupProgram == null) -1 else 0
                                else -> 0
                            }
                            if (zapDelta != 0) {
                                if (firstPress) {
                                    val nowMs = System.currentTimeMillis()
                                    if (nowMs - lastZapAtMs >= MinZapIntervalMs) {
                                        lastZapAtMs = nowMs
                                        zap(zapDelta)
                                        hudPokeSignal++
                                    }
                                }
                                return@onPreviewKeyEvent true
                            }

                            // Left, Right and OK belong to playback until the user
                            // hands the HUD the focus with OK; from then on they
                            // drive its button row instead.
                            if (firstPress && !hudEngaged) {
                                when (ev.key) {
                                    Key.DirectionLeft -> {
                                        quickZapOpen = true
                                        return@onPreviewKeyEvent true
                                    }
                                    Key.DirectionRight -> {
                                        if (tunePreviousChannel()) hudPokeSignal++
                                        return@onPreviewKeyEvent true
                                    }
                                    Key.DirectionCenter, Key.Enter -> {
                                        hudEngaged = true
                                        hudPokeSignal++
                                        return@onPreviewKeyEvent true
                                    }
                                    else -> Unit
                                }
                            }

                            when (ev.key) {
                                Key.Back, Key.Escape -> {
                                    if (firstPress) {
                                        if (hudEngaged) {
                                            // Put the controls away and keep watching.
                                            hudEngaged = false
                                            hudHideSignal++
                                        } else if (playingCatchupProgram != null) {
                                            returnCatchupToLive()
                                        } else {
                                            exitFullScreenPlayback()
                                        }
                                    }
                                    true
                                }
                                else -> false
                            }
                        }
                    }
                    .then(
                        if (isTouchDevice) {
                            Modifier.clickable(
                                interactionSource = remember { androidx.compose.foundation.interaction.MutableInteractionSource() },
                                indication = null,
                            ) {
                                hudPokeSignal++
                            }
                        } else if (isFullScreen && !fullscreenGuideOpen && !quickZapOpen) {
                            Modifier.onPreviewKeyEvent { ev ->
                                // Keep the HUD alive while it is on screen, but
                                // never summon it: OK opens it, Back closes it.
                                if (ev.type == KeyEventType.KeyDown && isHudVisible) {
                                    hudPokeSignal++
                                }
                                false
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
                    val categoryTitle = playingChannel?.source?.group?.takeIf { it.isNotBlank() }
                        ?: visibleEnrichedState.value.tree.byId(selectedCategoryId)?.label
                        ?: selectedCategoryId

                    FullscreenHud(
                        channel = playingDisplayChannel,
                        nowNext = currentNowNext,
                        pokeSignal = hudPokeSignal,
                        categoryName = categoryTitle,
                        isCatchupMode = playingCatchupProgram != null,
                        isPlaying = if (playingCatchupProgram != null) playerPlayWhenReady else playerIsPlaying,
                        isBuffering = playerIsBuffering,
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
                        onPlayPauseClick = {
                            if (playingCatchupProgram != null) {
                                toggleCatchupPlayback()
                            } else {
                                if (exoPlayer.isPlaying) {
                                    exoPlayer.pause()
                                    playerPlayWhenReady = false
                                } else {
                                    exoPlayer.playWhenReady = true
                                    exoPlayer.play()
                                    playerPlayWhenReady = true
                                }
                                hudPokeSignal++
                            }
                        },
                        onRewindClick = {
                            val currentNow = currentNowNext?.now
                            val currentElapsed = if (currentNow != null && currentNow.startUtcMillis > 0L) {
                                (System.currentTimeMillis() - currentNow.startUtcMillis).coerceAtLeast(0L)
                            } else {
                                playerPositionMs
                            }
                            seekToPosition((currentElapsed - 10_000L).coerceAtLeast(0L))
                        },
                        onFastForwardClick = {
                            val currentNow = currentNowNext?.now
                            val currentElapsed = if (currentNow != null && currentNow.startUtcMillis > 0L) {
                                (System.currentTimeMillis() - currentNow.startUtcMillis).coerceAtLeast(0L)
                            } else {
                                playerPositionMs
                            }
                            seekToPosition(currentElapsed + 10_000L)
                        },
                        onPreviousCatchupClick = {
                            val curIdx = filteredChannels.indexOfFirst { it.id == playingChannel?.id }
                            if (curIdx > 0) {
                                selectChannel(filteredChannels[curIdx - 1])
                            }
                        },
                        onNextCatchupClick = {
                            val curIdx = filteredChannels.indexOfFirst { it.id == playingChannel?.id }
                            if (curIdx in 0 until filteredChannels.size - 1) {
                                selectChannel(filteredChannels[curIdx + 1])
                            }
                        },
                        onReplayClick = {
                            if (playingCatchupProgram != null) {
                                seekCatchupBy(-playerPositionMs)
                            } else {
                                val preparedStream = lastPreparedStreamUrl
                                if (preparedStream != null) {
                                    prepareStream(
                                        stream = preparedStream,
                                        isHls = lastPreparedIsHls,
                                        headers = lastPreparedHeaders,
                                        resetRetry = true,
                                        drmInfo = playingChannel?.source?.drmInfo,
                                        forcePrepare = true,
                                        resolvedMimeType = lastPreparedMimeType,
                                    )
                                }
                                hudPokeSignal++
                            }
                        },
                        onGoLiveClick = { returnCatchupToLive() },
                        onSeekToPosition = { targetMs ->
                            seekToPosition(targetMs)
                        },
                        onOpenQuickZap = {
                            quickZapOpen = true
                            isHudVisible = false
                        },
                        onVisibilityChanged = { visible ->
                            isHudVisible = visible
                            if (!visible) hudEngaged = false
                        },
                        hideSignal = hudHideSignal,
                        // On a remote the controls wait for OK; on a touch device
                        // a tap is the only way to reach them, so they stay part
                        // of what a tap brings up, exactly as before.
                        showControls = isTouchDevice || hudEngaged,
                        modifier = Modifier,
                    )
                }
                FullscreenGuideOverlay(
                    visible = isFullScreen && fullscreenGuideOpen,
                    channel = (guideChannel ?: playingChannel)?.let { it.copy(quality = it.displayQuality(playbackQuality)) },
                    guide = fullscreenGuide,
                    selectedProgram = playingCatchupProgram,
                    clockTickMillis = guideClockMillis,
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
                        if (program != null && target != null) {
                            when {
                                program.endUtcMillis <= guideClockMillis ->
                                    playProgramInFullscreen(program, target)
                                program.isLive(guideClockMillis) && isSamePlayingChannel(target) && state.epgVodActionsEnabled ->
                                    resolveVodOrPlayFullscreen(target, program)
                                program.isLive(guideClockMillis) && isSamePlayingChannel(target) ->
                                    playProgramInFullscreen(null, target)
                                program.isLive(guideClockMillis) -> {
                                    // First selection of another live channel follows the same
                                    // guide contract: tune it in the mini-player.
                                    fullscreenGuideOpen = false
                                    isFullScreen = false
                                    playProgramInMini(target, null)
                                }
                            }
                        }
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
                    nowNextMap = actionGuideNowNext,
                    categoriesTree = visibleEnrichedState.value.tree,
                    selectedCategoryId = selectedCategoryId,
                    onCategorySelected = { selectedCategoryId = it },
                    onDismiss = {
                        // Back from the list goes straight back to plain
                        // fullscreen — no HUD in the way.
                        quickZapOpen = false
                    },
                    onChannelSelect = { channel ->
                        playingChannelId = channel.id
                        focusedChannelId = channel.id
                        epgPrefetchAnchorId = channel.id
                        playingCatchupProgram = null
                        catchupPlaybackOffsetMs = 0L
                        quickZapOpen = false
                        rememberedChannelByCategory[categoryScope] = channel.id
                        hudPokeSignal++
                    },
                    onRightClick = { channel ->
                        openFullscreenGuide(channel, fromQuickZap = true)
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
        }

        // Top bar only shows when NOT in full-screen playback.
        // Fade with the fullscreen progress so it doesn't pop in/out — looks
        // natural next to the grow animation below.
        LiveTvRenderBoundary {
            if (showTopBar && fsProgress < 1f) {
            Box(modifier = Modifier.graphicsLayer { alpha = 1f - fsProgress }) {
                AppTopBar(
                    selectedItem = SidebarItem.TV,
                    isFocused = focusZone == LiveTvFocusZone.TOPBAR,
                    focusedIndex = if (focusZone == LiveTvFocusZone.TOPBAR) topBarFocusIndex else -1,
                    profile = currentProfile,
                    profileCount = 1,
                    modifier = Modifier.focusRequester(topBarFocusRequester)
                        .focusable(enabled = focusZone == LiveTvFocusZone.TOPBAR),
                )
                LaunchedEffect(focusZone) {
                    if (focusZone == LiveTvFocusZone.TOPBAR) topBarFocusRequester.requestFocus()
                }
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
                searchProvider = { query ->
                    viewModel.iptvRepository
                        .pagedSearchChannels(query, limit = 200)
                        .asSequence()
                        .filterNot { channel -> isAdultGroup(channel.group, channel.name) }
                        .filterNot { channel ->
                            val playlistId = StalkerPortalSupport.playlistIdFromChannelId(channel.id)
                            val groupKey = com.arflix.tv.data.model.PlaylistGroupKey
                                .build(playlistId, channel.group.ifBlank { "Ungrouped" })
                            groupKey in hiddenGroupSet
                        }
                        .filterNot { channel -> isRestrictedPlaylistGroup(channel, restrictedGroupSet) }
                        .mapIndexed { index, channel -> channel.enrichForFastStartup(index + 1) }
                        .toList()
                },
                onDismiss = { searchOpen = false },
                onPick = { channel ->
                    selectedCategoryId = bestCategoryIdForChannel(channel, visibleEnrichedState.value.tree)
                    playingChannelId = channel.id
                    focusedChannelId = channel.id
                    epgPrefetchAnchorId = channel.id
                    searchOpen = false
                    if (!useTouchRail) categoryDrawerOpen = false
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

        channelMenu?.let { menu ->
            if (channelMenuActions.isNotEmpty()) {
                ChannelContextMenu(
                    state = menu,
                    actions = channelMenuActions,
                    onDismiss = { channelMenu = null },
                    onFocusedIndexChange = { index ->
                        channelMenu = menu.copy(
                            focusedIndex = index.coerceIn(0, channelMenuActions.lastIndex)
                        )
                    },
                    onAction = { index -> channelMenuActions.getOrNull(index)?.onClick?.invoke() },
                )
            }
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

        if (programActionLookupInProgress) {
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .background(Color(0xE61A1A1A), RoundedCornerShape(8.dp))
                    .padding(20.dp),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator(
                    color = Pink,
                    strokeWidth = 3.dp,
                )
            }
        }

        val actionData = programActionDialog
        if (actionData != null) {
            val program = actionData.program
            val channel = actionData.channel
            val isNow = program.isLive(guideClockMillis)
            androidx.compose.material3.AlertDialog(
                onDismissRequest = { programActionDialog = null },
                title = {
                    androidx.tv.material3.Text(
                        text = program.title,
                        style = ArflixTypography.cardTitle,
                        color = Color.White,
                        maxLines = 2,
                        overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis,
                    )
                },
                text = {
                    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        androidx.tv.material3.Text(
                            text = channel.name,
                            style = ArflixTypography.caption,
                            color = TextSecondary,
                        )
                        androidx.tv.material3.Text(
                            text = "${formatClock(program.startUtcMillis)} - ${formatClock(program.endUtcMillis)}",
                            style = ArflixTypography.body,
                            color = TextSecondary,
                        )
                        if (isNow) {
                            Badge(stringResource(R.string.live_badge_live), Color.White, LiveColors.LiveRed)
                        }
                    }
                },
                confirmButton = {
                    val vodMatch = programActionVodMatch
                    if (vodMatch != null) {
                        androidx.compose.material3.TextButton(
                            onClick = {
                                invalidateProgramActionLookup()
                                onNavigateToDetails(vodMatch.mediaType, vodMatch.id)
                            },
                        ) {
                            androidx.tv.material3.Text(
                                text = stringResource(R.string.epg_search_sources),
                                style = ArflixTypography.button,
                                color = Pink,
                            )
                        }
                    }
                },
                dismissButton = {
                    androidx.compose.material3.TextButton(
                        onClick = {
                            programActionDialog = null
                            playProgramInMini(channel, epgWatchLivePlaybackProgram(program))
                        },
                    ) {
                        androidx.tv.material3.Text(
                            text = stringResource(R.string.epg_watch_live),
                            style = ArflixTypography.button,
                            color = TextSecondary,
                        )
                    }
                },
                containerColor = Color(0xFF1A1A1A),
                tonalElevation = 8.dp,
            )
        }

        val lockedAction = pendingLockedGroupAction
        if (lockedAction != null) {
            PinEntryDialog(
                title = stringResource(R.string.live_group_enter_pin),
                onPinConfirmed = { enteredPin ->
                    if (PinUtil.verifyPin(enteredPin, currentProfile?.pin)) {
                        pendingLockedGroupAction = null
                        lockedGroupPinError = ""
                        when (lockedAction) {
                            is LockedGroupPinAction.OpenCategory -> {
                                unlockedGroupKeys = unlockedGroupKeys + lockedAction.groupKey
                                enterSelectedCategory(lockedAction.categoryId)
                            }
                            is LockedGroupPinAction.ToggleLock -> {
                                val key = PlaylistGroupKey.build(lockedAction.playlistId, lockedAction.groupName)
                                unlockedGroupKeys = unlockedGroupKeys - key
                                viewModel.toggleLockedGroup(lockedAction.playlistId, lockedAction.groupName)
                            }
                        }
                    } else {
                        lockedGroupPinError = context.getString(R.string.live_group_pin_incorrect)
                    }
                },
                onDismiss = {
                    pendingLockedGroupAction = null
                    lockedGroupPinError = ""
                },
                pinError = lockedGroupPinError,
            )
        }

            if (showMissingProfilePinDialog) {
            androidx.compose.material3.AlertDialog(
                onDismissRequest = { showMissingProfilePinDialog = false },
                title = {
                    androidx.tv.material3.Text(
                        text = stringResource(R.string.live_group_pin_required_title),
                        color = Color.White,
                    )
                },
                text = {
                    androidx.tv.material3.Text(
                        text = stringResource(R.string.live_group_pin_required_message),
                        color = TextSecondary,
                    )
                },
                confirmButton = {
                    androidx.compose.material3.TextButton(
                        onClick = {
                            showMissingProfilePinDialog = false
                            onNavigateToSettings()
                        },
                    ) {
                        androidx.tv.material3.Text(
                            text = stringResource(R.string.settings),
                            color = Pink,
                        )
                    }
                },
                dismissButton = {
                    androidx.compose.material3.TextButton(
                        onClick = { showMissingProfilePinDialog = false },
                    ) {
                        androidx.tv.material3.Text(
                            text = stringResource(R.string.cancel),
                            color = TextSecondary,
                        )
                    }
                },
                containerColor = Color(0xFF1A1A1A),
            )
            }
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

private fun LiveCategoryTree.countForCategory(categoryId: String): Int? {
    fun Sequence<LiveCategory>.findCount(): Int? {
        for (category in this) {
            if (category.id == categoryId) return category.count
            val childCount = category.children.asSequence().findCount()
            if (childCount != null) return childCount
        }
        return null
    }
    return sequenceOf(
        top.asSequence(),
        global.categories.asSequence(),
        countries.categories.asSequence(),
        adult.categories.asSequence(),
        hidden.categories.asSequence(),
    ).flatten().findCount()
}

private val IptvGroupPipeSpacingRegex = Regex("""\s*\|\s*""")
private val IptvGroupWhitespaceRegex = Regex("""\s+""")

private fun looseIptvGroupKey(group: String?): String {
    return group.orEmpty()
        .trim()
        .replace(IptvGroupPipeSpacingRegex, "|")
        .replace(IptvGroupWhitespaceRegex, " ")
        .lowercase()
}

private fun compactIptvGroupKey(group: String?): String {
    return group.orEmpty()
        .lowercase()
        .filter { it.isLetterOrDigit() }
}

private fun classifyPlaybackError(error: PlaybackException): String {
    if (isIptvProviderRequestPaused(error)) return "provider requests paused; please wait before retrying"
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

internal fun buildLiveTvConfiguration(): MediaItem.LiveConfiguration =
    MediaItem.LiveConfiguration.Builder()
        // Respect the manifest's segment/hold-back timing. An arbitrary eight-second
        // offset can start too close to unpublished segments on ordinary IPTV HLS.
        .setMinPlaybackSpeed(1.0f).setMaxPlaybackSpeed(1.0f)
        .build()

internal data class LiveTvBufferProfile(
    val minBufferMs: Int,
    val maxBufferMs: Int,
    val bufferForPlaybackMs: Int,
    val bufferForPlaybackAfterRebufferMs: Int,
    val targetBufferBytes: Int,
    val backBufferMs: Int,
)

internal fun buildLiveTvBufferProfile(
    memoryClassMb: Int,
    isLowRamDevice: Boolean,
): LiveTvBufferProfile {
    // Live-TV-appropriate buffering. The previous profile (96-160MB target,
    // 120-150s max buffer) treated a live stream like a VOD download: ExoPlayer's
    // buffer chunks are Java-heap byte arrays, so on a 384MB-capped TV box the
    // player alone could consume most of the heap — measured at 306-341MB with
    // 0% free, OOM-crashing the Live TV page during navigation. Live playback
    // only ever needs a ~30s safety window; smaller targets also start channels
    // faster and stop the initial bandwidth burn that caused early buffering.
    val heapMb = memoryClassMb.coerceAtLeast(256)
    val constrained = isLowRamDevice || heapMb <= 384
    val targetMb = if (constrained) 32 else 48

    return LiveTvBufferProfile(
        minBufferMs = 15_000,
        maxBufferMs = 30_000,
        bufferForPlaybackMs = 1_000,
        bufferForPlaybackAfterRebufferMs = if (constrained) 2_500 else 3_000,
        targetBufferBytes = targetMb * 1024 * 1024,
        backBufferMs = 5_000,
    )
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
    val withoutQuerySecrets = LiveTvScreenRegexes.QUERY_SECRETS
        .replace(url) { match -> "${match.groupValues[1]}***" }

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

internal fun epgWatchLivePlaybackProgram(
    @Suppress("UNUSED_PARAMETER") selectedProgram: IptvProgram,
): IptvProgram? = null

internal data class ProgramActionData(
    val channel: EnrichedChannel,
    val program: IptvProgram,
)
