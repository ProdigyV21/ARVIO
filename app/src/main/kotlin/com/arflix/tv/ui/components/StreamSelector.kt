package com.arflix.tv.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.HighQuality
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Storage
import androidx.compose.material.icons.rounded.Bolt
import androidx.compose.material.icons.rounded.Movie
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.foundation.lazy.list.TvLazyColumn
import androidx.tv.foundation.lazy.list.TvLazyListState
import androidx.tv.foundation.lazy.list.rememberTvLazyListState
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import com.arflix.tv.data.model.StreamSource
import com.arflix.tv.ui.focus.arvioDpadFocusGroup
import com.arflix.tv.ui.theme.ArflixTypography
import com.arflix.tv.util.LocalDeviceType
import com.arflix.tv.ui.theme.Pink
import com.arflix.tv.ui.theme.TextPrimary
import com.arflix.tv.ui.theme.TextSecondary
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import com.arflix.tv.R

// OLED source picker colors. Keep these deliberately monochrome so the sheet
// feels like the rest of ARVIO instead of a separate dashboard.
private val OledPanel = Color.White.copy(alpha = 0.055f)
private val OledPanelStrong = Color.White.copy(alpha = 0.095f)
private val OledBorder = Color.White.copy(alpha = 0.16f)
private val OledMutedBorder = Color.White.copy(alpha = 0.08f)
private val OledMutedText = Color.White.copy(alpha = 0.58f)
private val GlassWhite = OledPanel
private val GlassBorder = OledBorder
private val GlassHighlight = OledPanelStrong
private val AccentGreen = Color.White
private val AccentBlue = Color.White
private val AccentPurple = Color.White.copy(alpha = 0.86f)
private val AccentGold = Color.White

/**
 * Scrolls a TvLazyColumn so the focused item stays visible, leaving a one-item
 * buffer on the approaching edge so its focus border isn't clipped by the
 * viewport or a scroll-indicator overlay.
 */
private suspend fun TvLazyListState.scrollToKeepFocusVisible(focusedIndex: Int, itemCount: Int) {
    if (itemCount == 0) return
    val target = focusedIndex.coerceIn(0, itemCount - 1)
    val visibleItems = layoutInfo.visibleItemsInfo
    if (visibleItems.isEmpty()) {
        scrollToItem(target)
        return
    }
    val firstVisible = visibleItems.first().index
    val lastVisible = visibleItems.last().index
    val visibleCount = (lastVisible - firstVisible + 1).coerceAtLeast(1)
    when {
        target <= firstVisible -> {
            val dest = (target - 1).coerceAtLeast(0)
            if (dest != firstVisible) {
                scrollToItem(dest)
            }
        }
        target >= lastVisible -> {
            val dest = (target - visibleCount + 2).coerceIn(0, target)
            if (dest != firstVisible) {
                scrollToItem(dest)
            }
        }
    }
}

/**
 * Modern glassy stream source selector - compact and sleek
 */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun StreamSelector(
    isVisible: Boolean,
    streams: List<StreamSource>,
    selectedStream: StreamSource?,
    isLoading: Boolean = false,
    title: String = "",
    subtitle: String = "",
    hasStreamingAddons: Boolean = true,
    addonOrderedIds: List<String> = emptyList(),
    completedAddons: Int = 0,
    totalAddons: Int = 0,
    streamSearchStartTime: Long = 0L,
    pluginScrapersLoading: Boolean = false,
    loadingPluginNames: Set<String> = emptySet(),
    onFocusedStream: (StreamSource) -> Unit = {},
    onSelect: (StreamSource) -> Unit = {},
    onClose: () -> Unit = {}
) {
    val isRtlLayoutDirection = androidx.compose.ui.platform.LocalLayoutDirection.current == androidx.compose.ui.unit.LayoutDirection.Rtl
    var focusedIndex by remember { mutableIntStateOf(0) }
    var focusedTabIndex by remember { mutableIntStateOf(0) }
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    var focusedFilterIndex by remember { mutableIntStateOf(0) }
    var selectedFilterIndex by remember { mutableIntStateOf(0) }
    var focusZone by remember { mutableStateOf("streams") } // "streams" or "addons"
    val listState = rememberTvLazyListState()
    val addonListState = rememberTvLazyListState()
    val focusRequester = remember { FocusRequester() }
    val isMobile = LocalDeviceType.current.isTouchDevice()
    val pluginPrefix = stringResource(R.string.plugin_prefix)

    var elapsedSeconds by remember { mutableIntStateOf(0) }
    LaunchedEffect(streamSearchStartTime) {
        if (streamSearchStartTime > 0L) {
            elapsedSeconds = 0
            while (true) {
                kotlinx.coroutines.delay(1000L)
                elapsedSeconds = ((System.currentTimeMillis() - streamSearchStartTime) / 1000).toInt()
            }
        }
    }

    // Request focus when visible
    LaunchedEffect(isVisible) {
        if (isVisible) {
            focusRequester.requestFocus()
            focusedIndex = 0
            focusedTabIndex = 0
            selectedTabIndex = 0
            focusedFilterIndex = 0
            selectedFilterIndex = 0
            focusZone = "streams"
        }
    }

    data class AddonTab(val id: String, val label: String)
    data class SourceFilter(val label: String)
    val sourceFilters = remember { listOf(SourceFilter("All")) }

    // Build addon tabs using addonId so multiple instances of the same addon are shown separately.
    val addonTabs = remember(streams, addonOrderedIds) {
        val baseNameById = LinkedHashMap<String, String>()
        streams.forEach { stream ->
            val baseName = stream.addonName.split(" - ").firstOrNull()?.trim() ?: stream.addonName
            val addonId = sourceTabId(stream)
            baseNameById.putIfAbsent(addonId, baseName)
        }
        val nameCounts = baseNameById.values.groupingBy { it }.eachCount()
        baseNameById.map { (id, baseName) ->
            val label = if ((nameCounts[baseName] ?: 0) > 1) {
                val shortId = id.takeLast(4).uppercase()
                "$baseName #$shortId"
            } else {
                baseName
            }
            AddonTab(id, label)
        }.let { tabs ->
            if (addonOrderedIds.isEmpty()) tabs
            else tabs.sortedBy { tab ->
                val pos = addonOrderedIds.indexOfFirst { tab.id.contains(it) || it.contains(tab.id) }
                if (pos >= 0) pos else Int.MAX_VALUE
            }
        }
    }

    // Tab labels: "All sources" + addon labels
    val allSourcesLabel = stringResource(R.string.stream_tab_all_sources)
    val tabLabels = remember(addonTabs, allSourcesLabel) {
        listOf(allSourcesLabel) + addonTabs.map { it.label }
    }

    val presentations = remember(streams) { streams.map(::presentSource) }

    // Source ordering follows user addon order first. Within each addon, show the
    // largest files first, then the highest resolution/release quality.
    val addonOrder = remember(addonTabs, addonOrderedIds) {
        if (addonOrderedIds.isNotEmpty()) {
            // Use the user's configured addon order: map each stream's addonId to its
            // position in the user's installed-addons list.
            addonTabs.associate { tab ->
                val pos = addonOrderedIds.indexOfFirst { tab.id.contains(it) || it.contains(tab.id) }
                tab.id to if (pos >= 0) pos else Int.MAX_VALUE
            }
        } else {
            addonTabs.mapIndexed { index, tab -> tab.id to index }.toMap()
        }
    }
    // Addon order first so "All Sources" groups by the user's installed-addon order.
    // Within each addon, best quality floats up (resolution → release → size).
    val orderedPresentations = remember(presentations, addonOrder) {
        presentations.sortedWith(
            compareBy<SourcePresentation> { addonOrder[sourceTabId(it.stream)] ?: Int.MAX_VALUE }
                .thenByDescending { it.resolutionScore }
                .thenByDescending { it.releaseScore }
                .thenByDescending { it.sizeBytes }
                .thenBy { it.title.lowercase() }
        )
    }

    // Filter streams by selected tab
    val filteredPresentations = remember(
        orderedPresentations,
        selectedTabIndex,
        selectedFilterIndex,
        addonTabs
    ) {
        val selectedFilter = sourceFilters.getOrNull(selectedFilterIndex)?.label ?: "All"
        val addonFiltered = if (selectedTabIndex == 0) {
            orderedPresentations
        } else {
            val selectedAddonId = addonTabs.getOrNull(selectedTabIndex - 1)?.id ?: ""
            orderedPresentations.filter {
                sourceTabId(it.stream) == selectedAddonId
            }
        }
        addonFiltered.filter { sourceFilterMatches(it, selectedFilter) }
    }

    // Group streams by addon for display
    val groupedStreams = remember(filteredPresentations) {
        val labelById = addonTabs.associateBy({ it.id }, { it.label })
        filteredPresentations.groupBy {
            val tabId = sourceTabId(it.stream)
            labelById[tabId] ?: it.addonLabel
        }
    }

    // Flatten for navigation
    val flatPresentations = filteredPresentations
    val flatStreams = flatPresentations.map { it.stream }

    // Keep D-pad movement calm: only scroll when focus moves past the visible buffer.
    LaunchedEffect(focusedIndex, flatStreams.size) {
        listState.scrollToKeepFocusVisible(focusedIndex, flatStreams.size)
    }

    // Same buffered-scroll behavior for the addon rail's focus highlight.
    LaunchedEffect(focusedTabIndex, tabLabels.size) {
        addonListState.scrollToKeepFocusVisible(focusedTabIndex, tabLabels.size)
    }

    LaunchedEffect(isVisible, focusZone, focusedIndex, flatStreams) {
        if (isVisible && focusZone == "streams") {
            flatStreams.getOrNull(focusedIndex)?.let(onFocusedStream)
        }
    }

    // Count stats
    val count4K = remember(streams) {
        streams.count {
            it.quality.contains("4K", ignoreCase = true) ||
            it.quality.contains("2160p", ignoreCase = true)
        }
    }
    val count1080 = remember(streams) {
        streams.count { it.quality.contains("1080p", ignoreCase = true) }
    }
    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn(tween(200)) + slideInVertically(tween(300)) { it / 4 },
        exit = fadeOut(tween(150)) + slideOutVertically(tween(200)) { it / 4 }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .focusRequester(focusRequester)
                .focusable()
                .background(Color.Black.copy(alpha = 0.95f))
                .onKeyEvent { event ->
                    if (event.type == KeyEventType.KeyDown) {
                        val isRtl = isRtlLayoutDirection
                        val actualKey = event.key
                        val logicalKey = if (isRtl) {
                            when (actualKey) {
                                Key.DirectionLeft -> Key.DirectionRight
                                Key.DirectionRight -> Key.DirectionLeft
                                else -> actualKey
                            }
                        } else actualKey

                        when (logicalKey) {
                            Key.Back, Key.Escape -> {
                                onClose()
                                true
                            }
                            Key.DirectionUp -> {
                                if (focusZone == "addons") {
                                    if (focusedTabIndex > 0) {
                                        focusedTabIndex--
                                        selectedTabIndex = focusedTabIndex  // Immediately filter on focus
                                        focusedIndex = 0  // Reset stream selection
                                    }
                                } else {
                                    if (focusedIndex > 0) {
                                        focusedIndex--
                                    }
                                }
                                true
                            }
                            Key.DirectionDown -> {
                                if (focusZone == "addons") {
                                    if (focusedTabIndex < tabLabels.size - 1) {
                                        focusedTabIndex++
                                        selectedTabIndex = focusedTabIndex  // Immediately filter on focus
                                        focusedIndex = 0  // Reset stream selection
                                    }
                                } else {
                                    if (focusedIndex < flatStreams.size - 1) focusedIndex++
                                }
                                true
                            }
                            Key.DirectionLeft -> {
                                when {
                                    focusZone == "addons" -> {
                                        focusZone = "streams"
                                        focusedIndex = focusedIndex.coerceAtMost((flatStreams.size - 1).coerceAtLeast(0))
                                    }
                                }
                                true
                            }
                            Key.DirectionRight -> {
                                when {
                                    focusZone == "streams" && tabLabels.size > 1 -> {
                                        focusZone = "addons"
                                        focusedTabIndex = selectedTabIndex
                                    }
                                }
                                true
                            }
                            Key.Enter, Key.DirectionCenter -> {
                                if (focusZone == "addons") {
                                    // Tab already selected on focus, just move to streams
                                    focusZone = "streams"
                                    focusedIndex = 0
                                } else {
                                    flatStreams.getOrNull(focusedIndex)?.let { stream ->
                                        onSelect(stream)
                                    }
                                }
                                true
                            }
                            else -> false
                        }
                    } else false
                }
        ) {
            if (!isMobile) {
                OledSourceSelectorTv(
                    title = title,
                    subtitle = subtitle,
                    streams = streams,
                    flatPresentations = flatPresentations,
                    selectedStream = selectedStream,
                    sourceFilters = sourceFilters.map { it.label },
                    selectedFilterIndex = selectedFilterIndex,
                    focusedFilterIndex = focusedFilterIndex,
                    filterFocused = false,
                    tabLabels = tabLabels,
                    selectedTabIndex = selectedTabIndex,
                    focusedTabIndex = focusedTabIndex,
                    addonRailFocused = focusZone == "addons",
                    listState = listState,
                    addonListState = addonListState,
                    focusedIndex = focusedIndex,
                    streamsFocused = focusZone == "streams",
                    count4K = count4K,
                    count1080 = count1080,
                    isLoading = isLoading,
                    hasStreamingAddons = hasStreamingAddons,
                    completedAddons = completedAddons,
                    totalAddons = totalAddons,
                    elapsedSeconds = elapsedSeconds,
                    pluginScrapersLoading = pluginScrapersLoading,
                    loadingPluginNames = loadingPluginNames,
                    onFilterSelected = { index ->
                        selectedFilterIndex = index
                        focusedFilterIndex = index
                        focusedIndex = 0
                    },
                    onAddonSelected = { index ->
                        selectedTabIndex = index
                        focusedTabIndex = index
                        focusedIndex = 0
                    },
                    onSelect = onSelect
                )
            } else {
                // Mobile single-column layout
                val mobileListState = rememberLazyListState()

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                ) {
                    // Header row: title, count, close button
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp, bottom = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = title.ifEmpty { stringResource(R.string.stream_title_select_source) },
                                style = ArflixTypography.body.copy(
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                ),
                                color = TextPrimary,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(
                                text = "${streams.size} ${stringResource(R.string.sources_available)}",
                                style = ArflixTypography.caption.copy(fontSize = 12.sp),
                                color = TextSecondary
                            )
                        }
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(Color.White.copy(alpha = 0.1f))
                                .clickable { onClose() },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = stringResource(R.string.close),
                                tint = TextPrimary,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }

                    // Horizontal scrollable tab row for addon filters
                    if (tabLabels.size > 1) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .horizontalScroll(rememberScrollState())
                                .padding(vertical = 8.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            tabLabels.forEachIndexed { index, label ->
                                val isSelected = index == selectedTabIndex
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(16.dp))
                                        .background(
                                            if (isSelected) Pink.copy(alpha = 0.3f)
                                            else Color.White.copy(alpha = 0.08f)
                                        )
                                        .then(
                                            if (isSelected) Modifier.border(1.dp, Pink.copy(alpha = 0.5f), RoundedCornerShape(16.dp))
                                            else Modifier.border(1.dp, Color.White.copy(alpha = 0.1f), RoundedCornerShape(16.dp))
                                        )
                                        .clickable {
                                            selectedTabIndex = index
                                            focusedIndex = 0
                                        }
                                        .padding(horizontal = 14.dp, vertical = 6.dp)
                                ) {
                                    Text(
                                        text = label,
                                        style = ArflixTypography.caption.copy(
                                            fontSize = 12.sp,
                                            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal
                                        ),
                                        color = if (isSelected) Pink else TextSecondary,
                                        maxLines = 1
                                    )
                                }
                            }
                        }
                    }

                    // Stream list or loading/empty states
                    if (streams.isEmpty()) {
                        val stillSearching = isLoading || (completedAddons < totalAddons && totalAddons > 0) || pluginScrapersLoading
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .background(GlassWhite, RoundedCornerShape(16.dp))
                                    .border(1.dp, GlassBorder, RoundedCornerShape(16.dp))
                                    .padding(32.dp)
                            ) {
                                if (stillSearching) {
                                    LoadingIndicator(color = Pink, size = 40.dp)
                                    Spacer(modifier = Modifier.height(12.dp))
                                    Text(
                                        text = buildString {
                                            if (elapsedSeconds > 0) append("${elapsedSeconds}s \u2022 ")
                                            if (loadingPluginNames.isNotEmpty()) append(stringResource(R.string.plugins_loading, loadingPluginNames.joinToString(", ")))
                                            else if (pluginScrapersLoading) append(stringResource(R.string.plugins_loading, "..."))
                                            else if (totalAddons > 0) append(stringResource(R.string.stream_searching_addons, completedAddons, totalAddons))
                                            else append(stringResource(R.string.finding_sources))
                                        },
                                        style = ArflixTypography.body.copy(
                                            fontSize = 14.sp,
                                            fontWeight = FontWeight.Medium
                                        ),
                                        color = TextSecondary
                                    )
                                } else {
                                    val iconColor = if (!hasStreamingAddons) Color(0xFF3B82F6) else TextSecondary.copy(alpha = 0.5f)
                                    Box(
                                        modifier = Modifier
                                            .size(48.dp)
                                            .background(iconColor.copy(alpha = 0.1f), CircleShape),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            imageVector = if (!hasStreamingAddons) Icons.Default.Settings else Icons.Default.Cloud,
                                            contentDescription = null,
                                            tint = iconColor,
                                            modifier = Modifier.size(24.dp)
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(12.dp))
                                    Text(
                                        text = if (!hasStreamingAddons) stringResource(R.string.stream_no_streaming_addons) else stringResource(R.string.stream_no_sources_found),
                                        style = ArflixTypography.body.copy(
                                            fontSize = 14.sp,
                                            fontWeight = FontWeight.Medium
                                        ),
                                        color = TextSecondary
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = if (!hasStreamingAddons)
                                            stringResource(R.string.stream_no_addons_hint)
                                        else
                                            stringResource(R.string.stream_try_adding_addons),
                                        style = ArflixTypography.caption.copy(fontSize = 12.sp),
                                        color = TextSecondary.copy(alpha = 0.6f),
                                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                                    )
                                }
                            }
                        }
                    } else {
                        LazyColumn(
                            state = mobileListState,
                            contentPadding = PaddingValues(vertical = 8.dp),
                            verticalArrangement = Arrangement.spacedBy(6.dp),
                            modifier = Modifier
                                .fillMaxSize()
                                .weight(1f)
                                .arvioDpadFocusGroup()
                        ) {
                            items(flatPresentations) { presentation ->
                                MobileStreamCard(
                                    presentation = presentation,
                                    isSelected = isSelectedSource(presentation.stream, selectedStream),
                                    onClick = { onSelect(presentation.stream) }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun OledSourceSelectorTv(
    title: String,
    subtitle: String,
    streams: List<StreamSource>,
    flatPresentations: List<SourcePresentation>,
    selectedStream: StreamSource?,
    sourceFilters: List<String>,
    selectedFilterIndex: Int,
    focusedFilterIndex: Int,
    filterFocused: Boolean,
    tabLabels: List<String>,
    selectedTabIndex: Int,
    focusedTabIndex: Int,
    addonRailFocused: Boolean,
    listState: TvLazyListState,
    addonListState: TvLazyListState,
    focusedIndex: Int,
    streamsFocused: Boolean,
    count4K: Int,
    count1080: Int,
    isLoading: Boolean,
    hasStreamingAddons: Boolean,
    completedAddons: Int,
    totalAddons: Int,
    elapsedSeconds: Int = 0,
    pluginScrapersLoading: Boolean,
    loadingPluginNames: Set<String>,
    onFilterSelected: (Int) -> Unit,
    onAddonSelected: (Int) -> Unit,
    onSelect: (StreamSource) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 30.dp, top = 26.dp, end = 30.dp, bottom = 24.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(Color.White.copy(alpha = 0.032f), RoundedCornerShape(24.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .padding(start = 20.dp, top = 18.dp, end = 18.dp, bottom = 18.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = stringResource(R.string.sources),
                        style = ArflixTypography.body.copy(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        color = TextPrimary,
                        maxLines = 1
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        val stillSearchingMore = streams.isNotEmpty() &&
                            (isLoading || pluginScrapersLoading || (totalAddons > 0 && completedAddons < totalAddons))
                        if (stillSearchingMore) {
                            LoadingIndicator(color = OledMutedText, size = 13.dp, strokeWidth = 2.dp)
                            Spacer(modifier = Modifier.width(6.dp))
                        }
                        Text(
                            text = sourceStatusText(
                                sourceCount = streams.size,
                                completedAddons = completedAddons,
                                totalAddons = totalAddons,
                                isLoading = isLoading,
                                elapsedSeconds = elapsedSeconds,
                                pluginScrapersLoading = pluginScrapersLoading
                            ),
                            style = ArflixTypography.caption.copy(fontSize = 13.sp),
                            color = OledMutedText,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }

                if (title.isNotBlank()) {
                    Column(
                        horizontalAlignment = Alignment.End,
                        modifier = Modifier.width(360.dp)
                    ) {
                        Text(
                            text = title,
                            style = ArflixTypography.body.copy(
                                fontSize = 15.sp,
                                fontWeight = FontWeight.SemiBold
                            ),
                            color = TextPrimary.copy(alpha = 0.9f),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        if (subtitle.isNotEmpty()) {
                            Text(
                                text = subtitle,
                                style = ArflixTypography.caption.copy(fontSize = 12.sp),
                                color = OledMutedText,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            if (sourceFilters.size > 1) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    sourceFilters.forEachIndexed { index, filter ->
                        SourceFilterChip(
                            text = filter,
                            isSelected = index == selectedFilterIndex,
                            isFocused = filterFocused && index == focusedFilterIndex,
                            onClick = { onFilterSelected(index) }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
            }

            when {
                streams.isEmpty() -> SourceEmptyState(
                    isLoading = isLoading,
                    completedAddons = completedAddons,
                    totalAddons = totalAddons,
                    hasStreamingAddons = hasStreamingAddons,
                    elapsedSeconds = elapsedSeconds,
                    pluginScrapersLoading = pluginScrapersLoading,
                    loadingPluginNames = loadingPluginNames
                )
                flatPresentations.isEmpty() -> SourceEmptyState(
                    isLoading = false,
                    completedAddons = completedAddons,
                    totalAddons = totalAddons,
                    hasStreamingAddons = hasStreamingAddons,
                    message = stringResource(R.string.stream_no_sources_match)
                )
                else -> Box(modifier = Modifier.fillMaxSize()) {
                    TvLazyColumn(
                        state = listState,
                        contentPadding = PaddingValues(bottom = 10.dp),
                        verticalArrangement = Arrangement.spacedBy(5.dp),
                        modifier = Modifier
                            .fillMaxSize()
                            .arvioDpadFocusGroup()
                    ) {
                        flatPresentations.forEachIndexed { index, presentation ->
                            item(key = presentation.hashCode()) {
                                OledSourceRow(
                                    presentation = presentation,
                                    isFocused = streamsFocused && index == focusedIndex,
                                    isSelected = isSelectedSource(presentation.stream, selectedStream),
                                    onClick = { onSelect(presentation.stream) }
                                )
                            }
                        }
                    }

                    val canScrollUp by remember { derivedStateOf { listState.canScrollBackward } }
                    val canScrollDown by remember { derivedStateOf { listState.canScrollForward } }

                    if (canScrollUp) {
                        Box(
                            modifier = Modifier
                                .align(Alignment.TopCenter)
                                .fillMaxWidth()
                                .height(20.dp)
                                .background(
                                    Brush.verticalGradient(
                                        listOf(Color.Black.copy(alpha = 0.55f), Color.Transparent)
                                    )
                                )
                        )
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowUp,
                            contentDescription = null,
                            tint = OledMutedText,
                            modifier = Modifier
                                .align(Alignment.TopCenter)
                                .padding(top = 2.dp)
                                .size(20.dp)
                        )
                    }
                    if (canScrollDown) {
                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .fillMaxWidth()
                                .height(20.dp)
                                .background(
                                    Brush.verticalGradient(
                                        listOf(Color.Transparent, Color.Black.copy(alpha = 0.55f))
                                    )
                                )
                        )
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowDown,
                            contentDescription = null,
                            tint = OledMutedText,
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .padding(bottom = 2.dp)
                                .size(20.dp)
                        )
                    }
                }
            }
        }

        SourceAddonRail(
            tabLabels = tabLabels,
            selectedTabIndex = selectedTabIndex,
            focusedTabIndex = focusedTabIndex,
            isFocused = addonRailFocused,
            listState = addonListState,
            totalSources = streams.size,
            count4K = count4K,
            count1080 = count1080,
            completedAddons = completedAddons,
            totalAddons = totalAddons,
            onSelect = onAddonSelected
        )
        }
    }
}

@androidx.compose.runtime.Immutable
private data class SourcePresentation(
    val stream: StreamSource,
    val title: String,
    val rawTitle: String,
    val addonLabel: String,
    val resolutionLabel: String,
    val resolutionScore: Int,
    val releaseLabel: String?,
    val releaseScore: Int,
    val codecLabel: String?,
    val audioLabel: String?,
    val transportLabel: String?,
    val multiSourceLabel: String?,
    val languageLabel: String?,
    val chips: List<String>,
    val qualityColor: Color,
    val sizeBytes: Long,
    val sortCached: Boolean,
    val sortDirect: Boolean,
    val description: String? = null
)

@androidx.compose.runtime.Immutable
private data class SourceBadge(
    val text: String,
    val imageUrl: String? = null
)

private object SourceBadgeImages {
    private const val WHITE_TAGS =
        "https://raw.githubusercontent.com/nobnobz/Omni-Template-Bot-Bid-Raiser/main/Other/white%20regex%20tags"

    const val UHD_4K = "$WHITE_TAGS/white_4k.png"
    const val FULL_HD_1080 = "$WHITE_TAGS/white_1080p.png"
    const val HD_720 = "$WHITE_TAGS/white_720p.png"
    const val REMUX = "https://raw.githubusercontent.com/9mousaa/BetterFormatter/main/images/mono-remux.png"
    const val BLURAY = "https://raw.githubusercontent.com/9mousaa/BetterFormatter/main/images/mono-bluray.png"
    const val IMAX = "$WHITE_TAGS/white_imax.png"
    const val DOLBY_VISION = "$WHITE_TAGS/white_DV.png"
    const val HDR10_PLUS = "$WHITE_TAGS/white_HDR10Plus.png"
    const val HDR10 = "$WHITE_TAGS/white_HDR10.png"
    const val HDR = "$WHITE_TAGS/white_HDR.png"
    const val ATMOS = "$WHITE_TAGS/white_Atmos.png"
    const val TRUEHD = "$WHITE_TAGS/white_TrueHD.png"
    const val DOLBY_DIGITAL_PLUS = "$WHITE_TAGS/white_DDPLUS.png"
    const val DOLBY_DIGITAL = "$WHITE_TAGS/white_DD.png"
    const val DTS_X = "$WHITE_TAGS/white_dtsx.png"
    const val DTS_HD_MA = "$WHITE_TAGS/white_dtsHDMA.png"
    const val DTS_HD = "$WHITE_TAGS/white_dtsHD.png"
    const val DTS = "$WHITE_TAGS/white_dts.png"
    const val AUDIO_7_1 = "$WHITE_TAGS/white_71.png"
    const val AUDIO_5_1 = "$WHITE_TAGS/white_51.png"
}



private object StreamRegexes {
    val AV1 = Regex("""\bAV1\b""", RegexOption.IGNORE_CASE)
    val HEVC = Regex("""\b(HEVC|X265|H265)\b""", RegexOption.IGNORE_CASE)
    val H264 = Regex("""\b(H264|X264|AVC)\b""", RegexOption.IGNORE_CASE)
    val REMUX = Regex("""\bREMUX\b""", RegexOption.IGNORE_CASE)
    val BLURAY = Regex("""\b(BLURAY|BDRIP|BDREMUX)\b""", RegexOption.IGNORE_CASE)
    val WEBDL = Regex("""\b(WEB[- .]?DL|WEBDL)\b""", RegexOption.IGNORE_CASE)
    val WEBRIP = Regex("""\bWEB[- .]?RIP\b""", RegexOption.IGNORE_CASE)
    val HDTV = Regex("""\bHDTV\b""", RegexOption.IGNORE_CASE)
    val CAM = Regex("""\b(CAM|TS|TELESYNC|HDCAM)\b""", RegexOption.IGNORE_CASE)
    val ATMOS = Regex("""\bATMOS\b""", RegexOption.IGNORE_CASE)
    val TRUEHD = Regex("""\bTRUEHD\b""", RegexOption.IGNORE_CASE)
    val DTS = Regex("""\b(DTS[- .]?HD|DTS|DDP|EAC3|AC3|AAC)\b""", RegexOption.IGNORE_CASE)
    val DTS_X = Regex("""\bDTS[-_.: ]?X\b""", RegexOption.IGNORE_CASE)
    val DTS_HD_MA = Regex("""\bDTS[-_. ]?(?:HD[-_. ]?)?(?:MA|MASTER)\b""", RegexOption.IGNORE_CASE)
    val DTS_HD_ONLY = Regex("""\bDTS[-_. ]?HD\b""", RegexOption.IGNORE_CASE)
    val DD_PLUS = Regex("""\b(DDP|DD\+|EAC-?3|E-?AC-?3)\b""", RegexOption.IGNORE_CASE)
    val DD = Regex("""\b(AC-?3|DD(?:[ ._-]?5[ ._-]?1)?|DOLBY[ ._-]?DIGITAL)\b""", RegexOption.IGNORE_CASE)
    val CH71 = Regex("""\b7[ .]?1\b""", RegexOption.IGNORE_CASE)
    val CH51 = Regex("""\b5[ .]?1\b""", RegexOption.IGNORE_CASE)
    val MULTI_AUDIO = Regex("""\b(MULTI|DUAL[ .-]?AUDIO|MULTI[ .-]?AUDIO)\b""", RegexOption.IGNORE_CASE)
    val LANGUAGE_HINT = Regex("""\b(ENG|ENGLISH|HIN|HINDI|TAM|TAMIL|TEL|TELUGU|JPN|JAPANESE|KOR|KOREAN|SPA|SPANISH|FRE|FRENCH|GER|GERMAN|ITA|ITALIAN)\b""", RegexOption.IGNORE_CASE)
    val DV = Regex("""\b(DV|DoVi|Dolby[\s._-]*Vision)\b""", RegexOption.IGNORE_CASE)
    val HDR10_PLUS = Regex("""\b(HDR10\+|HDR10\s*PLUS|HDR\s*10\s*\+)\b""", RegexOption.IGNORE_CASE)
    val HDR10 = Regex("""\bHDR10\b""", RegexOption.IGNORE_CASE)
    val HDR = Regex("""\bHDR(10\+?|10)?\b""", RegexOption.IGNORE_CASE)
    val IMAX = Regex("""\bIMAX\b""", RegexOption.IGNORE_CASE)
    val WHITESPACE = Regex("""\s+""")
    val SIZE_PATTERN_1 = Regex("""(\d+(?:\.\d+)?)\s*(TB|GB|MB|KB)""")
    val SIZE_PATTERN_2 = Regex("""(\d+(?:\.\d+)?)\s*(TIB|GIB|MIB|KIB)""")
    val SIZE_PATTERN_3 = Regex("""^(\d+(?:\.\d+)?)$""")
    val EXTENSION_REMOVAL = Regex("""\.(mkv|mp4|avi|mov|ts)$""", RegexOption.IGNORE_CASE)
    val YEAR_REMOVAL = Regex("""\b(19|20)\d{2}\b.*""")
    val SIZE_LINE_PATTERN = Regex("""^[╰└].*\d+(\.\d+)?\s*(GB|MB|KB|TB).*$""", RegexOption.IGNORE_CASE)
    val CHANNEL_TAG_PATTERN = Regex("""^\[.+]$""")
    val MD_NOISE = Regex("""[`*_]{1,4}""")
}

private fun sourceTabId(stream: StreamSource): String {
    val baseName = stream.addonName.split(" - ").firstOrNull()?.trim() ?: stream.addonName
    return if (stream.addonId == "home_server" && baseName.isNotBlank()) {
        "${stream.addonId}:$baseName"
    } else {
        stream.addonId.ifBlank { baseName }
    }
}

private fun isSelectedSource(candidate: StreamSource, selected: StreamSource?): Boolean {
    selected ?: return false
    if (candidate == selected) return true

    val sameAddon = candidate.addonId.isNotBlank() && candidate.addonId == selected.addonId
    val sameSource = candidate.source.isNotBlank() && candidate.source == selected.source
    val sameFile = candidate.behaviorHints?.filename?.takeIf { it.isNotBlank() }?.let { filename ->
        filename == selected.behaviorHints?.filename
    } ?: false
    val sameBingeGroup = candidate.behaviorHints?.bingeGroup?.takeIf { it.isNotBlank() }?.let { group ->
        group == selected.behaviorHints?.bingeGroup
    } ?: false
    val sameUrl = candidate.url?.takeIf { it.isNotBlank() }?.let { url ->
        url == selected.url
    } ?: false

    return sameUrl || (sameAddon && (sameSource || sameFile || sameBingeGroup))
}

private fun isDebridLikeSource(stream: StreamSource, blob: String? = null): Boolean {
    val addonName = stream.addonName
    val text = blob ?: buildString {
        append(stream.source)
        append(' ')
        append(stream.quality)
        append(' ')
        append(stream.addonName)
        append(' ')
        append(stream.behaviorHints?.filename.orEmpty())
        append(' ')
        append(stream.url.orEmpty())
    }
    return addonName.contains("torbox", ignoreCase = true) ||
        addonName.contains("torrentio tb", ignoreCase = true) ||
        addonName.contains("torrentio rd", ignoreCase = true) ||
        addonName.contains("torrentio pm", ignoreCase = true) ||
        addonName.contains("torrentio ad", ignoreCase = true) ||
        text.contains("debrid", ignoreCase = true) ||
        text.contains("real-debrid", ignoreCase = true) ||
        text.contains("realdebrid", ignoreCase = true) ||
        text.contains("premiumize", ignoreCase = true) ||
        text.contains("alldebrid", ignoreCase = true) ||
        text.contains(" RD+", ignoreCase = true) ||
        text.contains("[RD+]", ignoreCase = true) ||
        text.contains(" TB+", ignoreCase = true) ||
        text.contains("[TB+]", ignoreCase = true) ||
        text.contains("torbox", ignoreCase = true)
}

private fun sourceFilterMatches(presentation: SourcePresentation, selectedFilter: String): Boolean {
    val stream = presentation.stream
    val blob = buildString {
        append(stream.source)
        append(' ')
        append(stream.quality)
        append(' ')
        append(stream.addonName)
        append(' ')
        append(stream.behaviorHints?.filename.orEmpty())
        append(' ')
        append(stream.url.orEmpty())
    }
    return when (selectedFilter) {
        "4K" -> presentation.resolutionLabel == "4K"
        "1080p" -> presentation.resolutionLabel == "1080p"
        "Debrid" -> isDebridLikeSource(stream, blob)
        "Direct" -> presentation.sortDirect
        else -> true
    }
}

private fun sourceStatusText(
    sourceCount: Int,
    completedAddons: Int,
    totalAddons: Int,
    isLoading: Boolean,
    elapsedSeconds: Int = 0,
    pluginScrapersLoading: Boolean = false
): String {
    val remaining = (totalAddons - completedAddons).coerceAtLeast(0)
    val elapsed = if (elapsedSeconds > 0 && (isLoading || pluginScrapersLoading)) "${elapsedSeconds}s \u2022 " else ""
    return when {
        isLoading && totalAddons > 0 && remaining > 0 ->
            "${elapsed}$sourceCount found - still checking $remaining ${if (remaining == 1) "addon" else "addons"}"
        isLoading -> "${elapsed}$sourceCount found - searching sources"
        pluginScrapersLoading -> "${elapsed}$sourceCount found - searching for more sources"
        totalAddons > 0 -> "$sourceCount found - $completedAddons/$totalAddons addons checked"
        else -> "$sourceCount found"
    }
}

private fun cleanSourceDisplayTitle(raw: String): String {
    val oneLine = raw
        .replace('\n', ' ')
        .replace('\r', ' ')
        .replace(StreamRegexes.WHITESPACE, " ")
        .trim()

    if (oneLine.length <= 92) return oneLine.ifBlank { "Unknown source" }

    val withoutExtension = oneLine
        .replace(StreamRegexes.EXTENSION_REMOVAL, "")
    val compact = withoutExtension
        .replace(StreamRegexes.YEAR_REMOVAL, "")
        .replace('.', ' ')
        .replace('_', ' ')
        .replace(StreamRegexes.WHITESPACE, " ")
        .trim()
        .takeIf { it.length in 8..70 }

    return compact ?: oneLine.take(92).trimEnd('.', ' ', '-', '_')
}

private fun presentSource(stream: StreamSource): SourcePresentation {
    val rawTitle = stream.behaviorHints?.filename?.takeIf { it.isNotBlank() } ?: stream.source
    val title = cleanSourceDisplayTitle(rawTitle)
    val addonLabel = stream.addonName.split(" - ").firstOrNull()?.trim() ?: stream.addonName
    val searchBlob = buildString {
        append(stream.quality)
        append(' ')
        append(stream.source)
        append(' ')
        append(stream.addonName)
        append(' ')
        append(stream.behaviorHints?.filename.orEmpty())
    }

    val resolutionLabel = when {
        searchBlob.contains("2160p", true) || searchBlob.contains("4K", true) -> "4K"
        searchBlob.contains("1080p", true) -> "1080p"
        searchBlob.contains("720p", true) -> "720p"
        StreamRegexes.CAM.containsMatchIn(searchBlob) -> "CAM"
        else -> stream.quality.split(" ").firstOrNull()?.take(8) ?: "SD"
    }
    val resolutionScore = when (resolutionLabel) {
        "4K" -> 4
        "1080p" -> 3
        "720p" -> 2
        "CAM" -> 0
        else -> 1
    }
    val qualityColor = when (resolutionLabel) {
        "4K" -> AccentGold
        "1080p" -> AccentBlue
        "720p" -> Color(0xFF06B6D4)
        "CAM" -> Color(0xFFEF4444)
        else -> TextSecondary
    }

    val releaseLabel = when {
        StreamRegexes.REMUX.containsMatchIn(searchBlob) -> "REMUX"
        StreamRegexes.BLURAY.containsMatchIn(searchBlob) -> "BluRay"
        StreamRegexes.WEBDL.containsMatchIn(searchBlob) -> "WEB-DL"
        StreamRegexes.WEBRIP.containsMatchIn(searchBlob) -> "WEBRip"
        StreamRegexes.HDTV.containsMatchIn(searchBlob) -> "HDTV"
        StreamRegexes.CAM.containsMatchIn(searchBlob) -> "CAM"
        else -> null
    }
    val releaseScore = when (releaseLabel) {
        "REMUX" -> 5
        "BluRay" -> 4
        "WEB-DL" -> 3
        "WEBRip" -> 2
        "HDTV" -> 1
        else -> 0
    }

    val codecLabel = when {
        StreamRegexes.AV1.containsMatchIn(searchBlob) -> "AV1"
        StreamRegexes.HEVC.containsMatchIn(searchBlob) -> "HEVC"
        StreamRegexes.H264.containsMatchIn(searchBlob) -> "H.264"
        else -> null
    }

    val audioLabel = when {
        StreamRegexes.ATMOS.containsMatchIn(searchBlob) -> "Atmos"
        StreamRegexes.TRUEHD.containsMatchIn(searchBlob) -> "TrueHD"
        StreamRegexes.CH71.containsMatchIn(searchBlob) -> "7.1"
        StreamRegexes.CH51.containsMatchIn(searchBlob) -> "5.1"
        StreamRegexes.DTS.containsMatchIn(searchBlob) -> StreamRegexes.DTS.find(searchBlob)?.value?.uppercase()
        else -> null
    }

    val addonLower = addonLabel.lowercase()
    val isTorrentProvider =
        addonLower.contains("torrentio") ||
        addonLower.contains("torrent") ||
        addonLower.contains("debrid") ||
        addonLower.contains("realdebrid") ||
        addonLower.contains("premiumize") ||
        addonLower.contains("alldebrid") ||
        searchBlob.contains("magnet:", ignoreCase = true)

    val hasDirectHttpUrl = !stream.url.isNullOrBlank() && stream.url.startsWith("http", true)
    val isIptvVod = stream.addonId == "iptv_xtream_vod" || addonLower.contains("iptv vod")
    val isDebridReady = isDebridLikeSource(stream, searchBlob)
    val isReady = stream.behaviorHints?.cached == true || isDebridReady

    val transportLabel = when {
        stream.behaviorHints?.cached == true -> "Cached"
        isDebridReady -> "Debrid"
        !stream.infoHash.isNullOrBlank() || stream.sources.isNotEmpty() || isTorrentProvider -> "Torrent"
        isIptvVod && hasDirectHttpUrl -> "VOD"
        else -> null
    }
    val multiSourceLabel = when {
        stream.sources.size > 1 -> "${stream.sources.size} sources"
        stream.sources.size == 1 -> "1 source"
        else -> null
    }

    val subtitleLangs = stream.subtitles.mapNotNull { sub ->
        sub.lang.takeIf { it.isNotBlank() }
    }.distinct()
    val languageLabel = when {
        StreamRegexes.MULTI_AUDIO.containsMatchIn(searchBlob) -> "Multi-audio"
        subtitleLangs.size > 1 -> "${subtitleLangs.size} langs"
        subtitleLangs.size == 1 -> subtitleLangs.first().uppercase()
        else -> StreamRegexes.LANGUAGE_HINT.find(searchBlob)?.value?.uppercase()
    }

    val chips = buildList {
        add(addonLabel)
        transportLabel?.let(::add)
        multiSourceLabel?.let(::add)
        languageLabel?.let(::add)
        releaseLabel?.let(::add)
        codecLabel?.let(::add)
        if (StreamRegexes.HDR.containsMatchIn(searchBlob)) add("HDR")
        if (StreamRegexes.DV.containsMatchIn(searchBlob)) add("DV")
        if (StreamRegexes.IMAX.containsMatchIn(searchBlob)) add("IMAX")
        audioLabel?.let(::add)
        if (stream.size.isNotBlank()) add(stream.size)
    }

    return SourcePresentation(
        stream = stream,
        title = title,
        rawTitle = rawTitle,
        addonLabel = addonLabel,
        resolutionLabel = resolutionLabel,
        resolutionScore = resolutionScore,
        releaseLabel = releaseLabel,
        releaseScore = releaseScore,
        codecLabel = codecLabel,
        audioLabel = audioLabel,
        transportLabel = transportLabel,
        multiSourceLabel = multiSourceLabel,
        languageLabel = languageLabel,
        chips = chips.distinct(),
        qualityColor = qualityColor,
        sizeBytes = getSizeBytes(stream),
        sortCached = isReady,
        sortDirect = !stream.url.isNullOrBlank() && stream.url.startsWith("http", true),
        description = cleanStreamDescription(stream.description, rawTitle)
    )
}

private fun cleanStreamDescription(raw: String?, title: String): String? {
    if (raw.isNullOrBlank()) return null
    val cleaned = raw.lines()
        .map { it.trim() }
        .filter { line ->
            line.isNotBlank() &&
            line != "None" &&
            !line.equals(title, ignoreCase = true) &&
            !StreamRegexes.SIZE_LINE_PATTERN.matches(line) &&
            !StreamRegexes.CHANNEL_TAG_PATTERN.matches(line)
        }
        .joinToString("\n") { line -> line.replace(StreamRegexes.MD_NOISE, "").trim() }
        .trim()
    return cleaned.takeIf { it.isNotBlank() }
}

private fun StreamSource.multiSourceCountLabel(): String? = when {
    sources.size > 1 -> "${sources.size} sources"
    sources.size == 1 -> "1 source"
    else -> null
}

private fun sourceBadges(presentation: SourcePresentation): List<SourceBadge> = buildList {
    val blob = buildString {
        append(presentation.rawTitle)
        append(' ')
        append(presentation.stream.source)
        append(' ')
        append(presentation.stream.quality)
        append(' ')
        append(presentation.chips.joinToString(" "))
    }

    when (presentation.resolutionLabel) {
        "4K" -> add(SourceBadge("4K", SourceBadgeImages.UHD_4K))
        "1080p" -> add(SourceBadge("1080p", SourceBadgeImages.FULL_HD_1080))
        "720p" -> add(SourceBadge("720p", SourceBadgeImages.HD_720))
        "480p" -> add(SourceBadge("480p"))
        else -> add(SourceBadge(presentation.resolutionLabel))
    }

    when (presentation.releaseLabel) {
        "REMUX" -> add(SourceBadge("REMUX", SourceBadgeImages.REMUX))
        "BluRay" -> add(SourceBadge("BluRay", SourceBadgeImages.BLURAY))
        "WEB-DL" -> add(SourceBadge("WEB-DL"))
        "WEBRip" -> add(SourceBadge("WEBRip"))
        "HDTV" -> add(SourceBadge("HDTV"))
        "CAM" -> add(SourceBadge("CAM"))
    }

    when (presentation.codecLabel) {
        "HEVC" -> add(SourceBadge("HEVC"))
        "H.264" -> add(SourceBadge("AVC"))
        "AV1" -> add(SourceBadge("AV1"))
    }

    when {
        StreamRegexes.DV.containsMatchIn(blob) -> add(SourceBadge("DV", SourceBadgeImages.DOLBY_VISION))
        StreamRegexes.HDR10_PLUS.containsMatchIn(blob) -> add(SourceBadge("HDR10+", SourceBadgeImages.HDR10_PLUS))
        StreamRegexes.HDR10.containsMatchIn(blob) -> add(SourceBadge("HDR10", SourceBadgeImages.HDR10))
        StreamRegexes.HDR.containsMatchIn(blob) -> add(SourceBadge("HDR", SourceBadgeImages.HDR))
    }
    if (StreamRegexes.IMAX.containsMatchIn(blob)) {
        add(SourceBadge("IMAX", SourceBadgeImages.IMAX))
    }

    when {
        presentation.audioLabel.equals("Atmos", ignoreCase = true) -> add(SourceBadge("Atmos", SourceBadgeImages.ATMOS))
        presentation.audioLabel.equals("TrueHD", ignoreCase = true) -> add(SourceBadge("TrueHD", SourceBadgeImages.TRUEHD))
        presentation.audioLabel.equals("7.1", ignoreCase = true) -> add(SourceBadge("7.1", SourceBadgeImages.AUDIO_7_1))
        presentation.audioLabel.equals("5.1", ignoreCase = true) -> add(SourceBadge("5.1", SourceBadgeImages.AUDIO_5_1))
        StreamRegexes.DTS_X.containsMatchIn(blob) -> add(SourceBadge("DTS:X", SourceBadgeImages.DTS_X))
        StreamRegexes.DTS_HD_MA.containsMatchIn(blob) -> add(SourceBadge("DTS-HD MA", SourceBadgeImages.DTS_HD_MA))
        StreamRegexes.DTS_HD_ONLY.containsMatchIn(blob) -> add(SourceBadge("DTS-HD", SourceBadgeImages.DTS_HD))
        presentation.audioLabel?.contains("DTS", ignoreCase = true) == true -> add(SourceBadge("DTS", SourceBadgeImages.DTS))
        StreamRegexes.DD_PLUS.containsMatchIn(blob) -> add(SourceBadge("DD+", SourceBadgeImages.DOLBY_DIGITAL_PLUS))
        StreamRegexes.DD.containsMatchIn(blob) -> add(SourceBadge("DD", SourceBadgeImages.DOLBY_DIGITAL))
    }

}.distinctBy { it.text }

private fun rowSubtitle(presentation: SourcePresentation): String {
    return presentation.addonLabel
}

private fun languageBadgeText(language: String?): String? {
    if (language.isNullOrBlank()) return null
    val normalized = language.trim().uppercase()
    return when {
        normalized.contains("MULTI") || normalized.contains("LANG") -> "🌐 MULTI"
        normalized in setOf("EN", "ENG", "ENGLISH") -> "🇬🇧 EN"
        normalized in setOf("NL", "NLD", "DUT", "DUTCH", "NEDERLANDS") -> "🇳🇱 NL"
        normalized in setOf("JA", "JPN", "JAPANESE") -> "🇯🇵 JA"
        normalized in setOf("KO", "KOR", "KOREAN") -> "🇰🇷 KO"
        normalized in setOf("ES", "SPA", "SPANISH") -> "🇪🇸 ES"
        normalized in setOf("FR", "FRE", "FRA", "FRENCH") -> "🇫🇷 FR"
        normalized in setOf("DE", "GER", "DEU", "GERMAN") -> "🇩🇪 DE"
        normalized in setOf("IT", "ITA", "ITALIAN") -> "🇮🇹 IT"
        normalized in setOf("HI", "HIN", "HINDI") -> "🇮🇳 HI"
        normalized in setOf("TA", "TAM", "TAMIL") -> "🇮🇳 TA"
        normalized in setOf("TE", "TEL", "TELUGU") -> "🇮🇳 TE"
        else -> normalized.take(6)
    }
}

private fun bestMatchReason(presentation: SourcePresentation): String {
    return listOfNotNull(
        presentation.transportLabel?.let {
            if (it == "Cached") "cached" else it.lowercase()
        },
        presentation.resolutionLabel.takeIf { it.isNotBlank() }?.lowercase(),
        presentation.codecLabel?.lowercase(),
        presentation.audioLabel?.lowercase()
    ).take(3).joinToString(" - ").ifBlank { "recommended source" }
}

@Composable
private fun PremiumQualityPill(presentation: SourcePresentation) {
    Box(
        modifier = Modifier
            .background(presentation.qualityColor.copy(alpha = 0.16f), RoundedCornerShape(8.dp))
            .border(1.dp, presentation.qualityColor.copy(alpha = 0.28f), RoundedCornerShape(8.dp))
            .padding(horizontal = 10.dp, vertical = 5.dp)
    ) {
        Text(
            text = presentation.resolutionLabel,
            style = ArflixTypography.caption.copy(fontSize = 10.sp, fontWeight = FontWeight.Black),
            color = presentation.qualityColor
        )
    }
}

@Composable
private fun SourceMetadataChips(
    presentation: SourcePresentation,
    compact: Boolean
) {
    val fontSize = if (compact) 10.sp else 11.sp
    val chipPadH = if (compact) 7.dp else 8.dp
    val chipPadV = if (compact) 3.dp else 4.dp
    Row(
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.horizontalScroll(rememberScrollState())
    ) {
        presentation.chips.take(if (compact) 5 else 7).forEach { chip ->
            val chipColor = when (chip) {
                "Cached", "Best Match" -> AccentGreen
                "VOD" -> AccentBlue
                "REMUX", "BluRay" -> AccentGold
                "DV", "IMAX" -> Pink
                "HDR" -> AccentPurple
                else -> TextSecondary
            }
            Box(
                modifier = Modifier
                    .background(chipColor.copy(alpha = 0.12f), RoundedCornerShape(999.dp))
                    .border(1.dp, chipColor.copy(alpha = 0.18f), RoundedCornerShape(999.dp))
                    .padding(horizontal = chipPadH, vertical = chipPadV)
            ) {
                Text(
                    text = chip,
                    style = ArflixTypography.caption.copy(fontSize = fontSize, fontWeight = FontWeight.Medium),
                    color = chipColor.copy(alpha = if (chipColor == TextSecondary) 0.82f else 1f),
                    maxLines = 1
                )
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun BestMatchStrip(
    presentation: SourcePresentation,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(OledPanelStrong, RoundedCornerShape(16.dp))
            .border(1.dp, OledBorder, RoundedCornerShape(16.dp))
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 13.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(42.dp)
                .background(Color.White, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = stringResource(R.string.play),
                tint = Color.Black,
                modifier = Modifier.size(25.dp)
            )
        }
        Spacer(modifier = Modifier.width(14.dp))
        Column(modifier = Modifier.weight(1f)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = stringResource(R.string.stream_best_match),
                    style = ArflixTypography.caption.copy(
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    ),
                    color = TextPrimary
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = bestMatchReason(presentation),
                    style = ArflixTypography.caption.copy(fontSize = 12.sp),
                    color = OledMutedText,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "${presentation.addonLabel} - ${presentation.rawTitle}",
                style = ArflixTypography.body.copy(fontSize = 15.sp, fontWeight = FontWeight.SemiBold),
                color = TextPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Spacer(modifier = Modifier.width(14.dp))
        OledBadgeRow(presentation = presentation, maxBadges = 6)
    }
}

@Composable
private fun SourceFilterChip(
    text: String,
    isSelected: Boolean,
    isFocused: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(
                when {
                    isFocused -> Color.White
                    isSelected -> Color.White.copy(alpha = 0.12f)
                    else -> OledPanel
                }
            )
            .border(
                1.dp,
                when {
                    isFocused -> Color.White
                    isSelected -> Color.White.copy(alpha = 0.28f)
                    else -> OledMutedBorder
                },
                RoundedCornerShape(999.dp)
            )
            .clickable { onClick() }
            .padding(horizontal = 14.dp, vertical = 6.dp)
    ) {
        Text(
            text = text,
            style = ArflixTypography.caption.copy(
                fontSize = 11.sp,
                fontWeight = if (isSelected || isFocused) FontWeight.Bold else FontWeight.SemiBold
            ),
            color = if (isFocused) Color.Black else TextPrimary.copy(alpha = if (isSelected) 0.96f else 0.82f),
            maxLines = 1
        )
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun SourceAddonRail(
    tabLabels: List<String>,
    selectedTabIndex: Int,
    focusedTabIndex: Int,
    isFocused: Boolean,
    listState: TvLazyListState,
    totalSources: Int,
    count4K: Int,
    count1080: Int,
    completedAddons: Int,
    totalAddons: Int,
    onSelect: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .width(218.dp)
            .fillMaxHeight()
            .padding(start = 4.dp, top = 2.dp, bottom = 2.dp)
    ) {
        Text(
            text = stringResource(R.string.stream_section_addons),
            style = ArflixTypography.caption.copy(
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            ),
            color = OledMutedText
        )
        Spacer(modifier = Modifier.height(10.dp))
        Box(modifier = Modifier.weight(1f)) {
            TvLazyColumn(
                state = listState,
                verticalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                tabLabels.forEachIndexed { index, label ->
                    item(key = label) {
                        AddonRailItem(
                            text = label,
                            isSelected = index == selectedTabIndex,
                            isFocused = isFocused && index == focusedTabIndex,
                            onClick = { onSelect(index) }
                        )
                    }
                }
            }

            val canScrollUp by remember { derivedStateOf { listState.canScrollBackward } }
            val canScrollDown by remember { derivedStateOf { listState.canScrollForward } }

            if (canScrollUp) {
                Box(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .fillMaxWidth()
                        .height(16.dp)
                        .background(
                            Brush.verticalGradient(
                                listOf(Color.Black.copy(alpha = 0.55f), Color.Transparent)
                            )
                        )
                )
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowUp,
                    contentDescription = null,
                    tint = OledMutedText,
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .size(16.dp)
                )
            }
            if (canScrollDown) {
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .height(16.dp)
                        .background(
                            Brush.verticalGradient(
                                listOf(Color.Transparent, Color.Black.copy(alpha = 0.55f))
                            )
                        )
                )
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowDown,
                    contentDescription = null,
                    tint = OledMutedText,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .size(16.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            RailMetric(label = stringResource(R.string.stream_metric_total), value = totalSources.toString())
            RailMetric(label = "4K", value = count4K.toString())
            RailMetric(label = "1080p", value = count1080.toString())
            if (totalAddons > 0) {
                RailMetric(label = stringResource(R.string.stream_metric_checked), value = "$completedAddons/$totalAddons")
            }
        }
    }
}

@Composable
private fun AddonRailItem(
    text: String,
    isSelected: Boolean,
    isFocused: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .focusProperties { canFocus = false }
            .clip(RoundedCornerShape(11.dp))
            .background(
                when {
                    isFocused -> Color.White
                    isSelected -> OledPanelStrong
                    else -> Color.Transparent
                },
                RoundedCornerShape(11.dp)
            )
            .border(
                1.dp,
                when {
                    isFocused -> Color.White
                    else -> Color.Transparent
                },
                RoundedCornerShape(11.dp)
            )
            .clickable { onClick() }
            .padding(horizontal = 11.dp, vertical = 9.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            style = ArflixTypography.caption.copy(
                fontSize = 12.sp,
                fontWeight = if (isSelected || isFocused) FontWeight.Bold else FontWeight.Medium
            ),
            color = if (isFocused) Color.Black else TextPrimary.copy(alpha = if (isSelected) 1f else 0.66f),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun RailMetric(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = ArflixTypography.caption.copy(fontSize = 11.sp),
            color = OledMutedText
        )
        Text(
            text = value,
            style = ArflixTypography.caption.copy(fontSize = 12.sp, fontWeight = FontWeight.Bold),
            color = TextPrimary
        )
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun SourceEmptyState(
    isLoading: Boolean,
    completedAddons: Int,
    totalAddons: Int,
    hasStreamingAddons: Boolean,
    elapsedSeconds: Int = 0,
    pluginScrapersLoading: Boolean = false,
    loadingPluginNames: Set<String> = emptySet(),
    message: String? = null
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(OledPanel, RoundedCornerShape(18.dp))
                .border(1.dp, OledMutedBorder, RoundedCornerShape(18.dp))
                .padding(horizontal = 42.dp, vertical = 34.dp)
        ) {
            val stillSearching = isLoading || (completedAddons < totalAddons && totalAddons > 0) || pluginScrapersLoading
            if (stillSearching) {
                LoadingIndicator(color = Color.White, size = 42.dp)
                Spacer(modifier = Modifier.height(14.dp))
                Text(
                    text = buildString {
                        if (elapsedSeconds > 0) append("${elapsedSeconds}s \u2022 ")
                        if (loadingPluginNames.isNotEmpty()) append(stringResource(R.string.plugins_loading, loadingPluginNames.joinToString(", ")))
                        else if (pluginScrapersLoading) append(stringResource(R.string.plugins_loading, "..."))
                        else if (totalAddons > 0) append(stringResource(R.string.stream_searching_addons, completedAddons, totalAddons))
                        else append(stringResource(R.string.finding_sources))
                    },
                    style = ArflixTypography.body.copy(fontSize = 15.sp, fontWeight = FontWeight.Medium),
                    color = TextSecondary
                )
            } else {
                Icon(
                    imageVector = if (!hasStreamingAddons) Icons.Default.Settings else Icons.Default.Cloud,
                    contentDescription = null,
                    tint = OledMutedText,
                    modifier = Modifier.size(36.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = message ?: if (!hasStreamingAddons) stringResource(R.string.stream_no_streaming_addons) else stringResource(R.string.stream_no_sources_found),
                    style = ArflixTypography.body.copy(fontSize = 15.sp, fontWeight = FontWeight.Medium),
                    color = TextSecondary
                )
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun OledSourceRow(
    presentation: SourcePresentation,
    isFocused: Boolean,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(92.dp)
            .padding(horizontal = 3.dp, vertical = 1.dp)
            .focusProperties { canFocus = false }
            .clip(RoundedCornerShape(15.dp))
            .background(
                when {
                    isFocused -> Color.White.copy(alpha = 0.11f)
                    isSelected -> Color.White.copy(alpha = 0.07f)
                    else -> Color.White.copy(alpha = 0.028f)
                },
                RoundedCornerShape(15.dp)
            )
            .then(
                if (isFocused) {
                    Modifier.border(1.5.dp, Color.White.copy(alpha = 0.96f), RoundedCornerShape(15.dp))
                } else {
                    Modifier
                }
            )
            .clickable { onClick() }
            .padding(horizontal = 11.dp, vertical = 7.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = presentation.rawTitle,
                        style = ArflixTypography.body.copy(
                            fontSize = 12.sp,
                            lineHeight = 15.sp,
                            fontWeight = if (isFocused) FontWeight.Bold else FontWeight.SemiBold
                        ),
                        color = TextPrimary,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )
                    if (isSelected) {
                        Spacer(modifier = Modifier.width(10.dp))
                        Box(
                            modifier = Modifier
                                .size(22.dp)
                                .background(Color.White.copy(alpha = 0.14f), CircleShape)
                                .border(1.dp, Color.White.copy(alpha = 0.9f), CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = stringResource(R.string.selected),
                                tint = Color.White,
                                modifier = Modifier.size(15.dp)
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = rowSubtitle(presentation),
                    style = ArflixTypography.caption.copy(
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    color = OledMutedText,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            SourceBadgeTray(presentation = presentation, maxBadges = 4)
        }
    }
}

@Composable
private fun SourceBadgeTray(
    presentation: SourcePresentation,
    maxBadges: Int,
    compact: Boolean = false,
    inverted: Boolean = false
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(if (compact) 4.dp else 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OledBadgeRow(presentation = presentation, maxBadges = maxBadges, inverted = inverted)
        SourceLanguageBadge(language = presentation.languageLabel, compact = compact, inverted = inverted)
        SourceSizeBadge(size = presentation.stream.size, compact = compact, inverted = inverted)
    }
}

@Composable
private fun SourceLanguageBadge(
    language: String?,
    compact: Boolean = false,
    inverted: Boolean = false
) {
    val display = languageBadgeText(language) ?: return
    Box(
        modifier = Modifier
            .background(
                if (inverted) Color.Transparent else Color.Black.copy(alpha = 0.9f),
                RoundedCornerShape(6.dp)
            )
            .padding(
                horizontal = if (compact) 6.dp else 8.dp,
                vertical = if (compact) 3.dp else 4.dp
            )
    ) {
        Text(
            text = display,
            style = ArflixTypography.caption.copy(
                fontSize = if (compact) 9.sp else 10.sp,
                fontWeight = FontWeight.Bold
            ),
            color = if (inverted) Color.Black else Color.White,
            maxLines = 1
        )
    }
}

@Composable
private fun SourceSizeBadge(
    size: String,
    compact: Boolean = false,
    inverted: Boolean = false
) {
    if (size.isBlank()) return
    Box(
        modifier = Modifier
            .background(
                if (inverted) Color.Transparent else Color.Black.copy(alpha = 0.9f),
                RoundedCornerShape(6.dp)
            )
            .padding(
                horizontal = if (compact) 6.dp else 8.dp,
                vertical = if (compact) 3.dp else 4.dp
            )
    ) {
        Text(
            text = size,
            style = ArflixTypography.caption.copy(
                fontSize = if (compact) 9.sp else 10.sp,
                fontWeight = FontWeight.Bold
            ),
            color = if (inverted) Color.Black else Color.White,
            maxLines = 1
        )
    }
}

@Composable
private fun OledBadgeRow(
    presentation: SourcePresentation,
    maxBadges: Int,
    inverted: Boolean = false
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        sourceBadges(presentation).take(maxBadges).forEach { badge ->
            SourceBadgeView(badge, inverted = inverted)
        }
    }
}

@Composable
private fun SourceBadgeView(
    badge: SourceBadge,
    inverted: Boolean = false
) {
    if (badge.imageUrl != null && !inverted) {
        AsyncImage(
            model = badge.imageUrl,
            contentDescription = badge.text,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .width(sourceBadgeWidth(badge.text))
                .height(20.dp)
        )
    } else {
        OledTextBadge(text = badge.text, inverted = inverted)
    }
}

private fun sourceBadgeWidth(text: String) = when {
    text.equals("4K", ignoreCase = true) -> 42.dp
    text.equals("1080p", ignoreCase = true) -> 56.dp
    text.equals("720p", ignoreCase = true) -> 50.dp
    text.equals("REMUX", ignoreCase = true) -> 62.dp
    text.equals("BluRay", ignoreCase = true) -> 62.dp
    text.equals("Atmos", ignoreCase = true) -> 66.dp
    text.equals("TrueHD", ignoreCase = true) -> 62.dp
    text.equals("DTS-HD MA", ignoreCase = true) -> 78.dp
    text.equals("DTS-HD", ignoreCase = true) -> 64.dp
    text.equals("DTS:X", ignoreCase = true) -> 58.dp
    text.equals("DD+", ignoreCase = true) -> 48.dp
    text.equals("DD", ignoreCase = true) -> 42.dp
    text.equals("DV", ignoreCase = true) -> 76.dp
    text.equals("IMAX", ignoreCase = true) -> 54.dp
    text.equals("7.1", ignoreCase = true) -> 40.dp
    text.equals("5.1", ignoreCase = true) -> 40.dp
    text.equals("HDR10+", ignoreCase = true) -> 64.dp
    text.equals("HDR10", ignoreCase = true) -> 58.dp
    text.equals("HDR", ignoreCase = true) -> 48.dp
    else -> 52.dp
}

@Composable
private fun OledTextBadge(
    text: String,
    inverted: Boolean = false
) {
    Box(
        modifier = Modifier
            .background(
                if (inverted) Color.Transparent else Color.Black.copy(alpha = 0.9f),
                RoundedCornerShape(6.dp)
            )
            .padding(horizontal = 7.dp, vertical = 3.dp)
    ) {
        Text(
            text = text,
            style = ArflixTypography.caption.copy(
                fontSize = 9.sp,
                fontWeight = FontWeight.Bold
            ),
            color = if (inverted) Color.Black else Color.White,
            maxLines = 1
        )
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun MobileStreamCard(
    presentation: SourcePresentation,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(
                if (isSelected) Color.White.copy(alpha = 0.09f) else Color.White.copy(alpha = 0.04f)
            )
            .clickable { onClick() }
            .padding(horizontal = 13.dp, vertical = 12.dp),
        verticalAlignment = Alignment.Top
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = presentation.rawTitle,
                    style = ArflixTypography.body.copy(fontSize = 14.sp, fontWeight = FontWeight.Bold),
                    color = TextPrimary,
                    modifier = Modifier.weight(1f),
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = rowSubtitle(presentation),
                style = ArflixTypography.caption.copy(fontSize = 10.sp),
                color = OledMutedText,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(7.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                sourceBadges(presentation).take(5).forEach { badge ->
                    SourceBadgeView(badge)
                }
                SourceLanguageBadge(language = presentation.languageLabel, compact = true)
                SourceSizeBadge(size = presentation.stream.size, compact = true)
            }
            if (!presentation.description.isNullOrBlank()) {
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = presentation.description,
                    style = ArflixTypography.caption.copy(fontSize = 12.sp),
                    color = TextSecondary.copy(alpha = 0.75f),
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

        if (isSelected) {
            Spacer(modifier = Modifier.width(10.dp))
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .background(Color.White.copy(alpha = 0.14f), CircleShape)
                    .border(1.dp, Color.White.copy(alpha = 0.9f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = stringResource(R.string.selected),
                    tint = Color.White,
                    modifier = Modifier.size(14.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun MiniStatCard(
    icon: ImageVector,
    value: String,
    label: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .background(color.copy(alpha = 0.1f), RoundedCornerShape(12.dp))
            .border(1.dp, color.copy(alpha = 0.2f), RoundedCornerShape(12.dp))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = color,
            modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            Text(
                text = value,
                style = ArflixTypography.body.copy(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = color
            )
            Text(
                text = label,
                style = ArflixTypography.caption.copy(fontSize = 10.sp),
                color = TextSecondary.copy(alpha = 0.6f)
            )
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun GlassyStreamCard(
    presentation: SourcePresentation,
    isFocused: Boolean,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val scale by animateFloatAsState(
        targetValue = if (isFocused) 1.03f else 1f,
        animationSpec = tween(160),
        label = "scale"
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .scale(scale)
            .clickable { onClick() }
            .background(
                when {
                    isFocused -> Brush.horizontalGradient(
                        colors = listOf(
                            Color.White.copy(alpha = 0.14f),
                            Color.White.copy(alpha = 0.06f)
                        )
                    )
                    isSelected -> Brush.horizontalGradient(
                        colors = listOf(Color.White.copy(alpha = 0.08f), Color.White.copy(alpha = 0.04f))
                    )
                    else -> Brush.horizontalGradient(
                        colors = listOf(Color.White.copy(alpha = 0.05f), Color.White.copy(alpha = 0.05f))
                    )
                },
                RoundedCornerShape(14.dp)
            )
            .padding(horizontal = 14.dp, vertical = 12.dp)
    ) {
        // Accent rail on the right for focused/selected source
        if (isFocused || isSelected) {
            Box(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 1.dp)
                    .width(3.dp)
                    .fillMaxHeight(0.75f)
                    .background(
                        if (isFocused) Color.White else Color.White.copy(alpha = 0.45f),
                        RoundedCornerShape(6.dp)
                    )
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(
                        if (isFocused) Color.White else Color.Gray.copy(alpha = 0.25f),
                        RoundedCornerShape(10.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = stringResource(R.string.play),
                    tint = if (isFocused) Color.Black else Color.White.copy(alpha = 0.75f),
                    modifier = Modifier.size(18.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = presentation.rawTitle,
                        style = ArflixTypography.body.copy(
                            fontSize = 13.sp,
                            fontWeight = if (isFocused) FontWeight.Bold else FontWeight.SemiBold,
                            lineHeight = 17.sp
                        ),
                        color = if (isFocused) TextPrimary else TextSecondary.copy(alpha = 0.96f),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    PremiumQualityPill(presentation)
                }

                Spacer(modifier = Modifier.height(8.dp))
                SourceMetadataChips(presentation = presentation, compact = false)
                if (!presentation.description.isNullOrBlank()) {
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = presentation.description,
                        style = ArflixTypography.caption.copy(fontSize = 11.sp),
                        color = TextSecondary.copy(alpha = 0.7f),
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            if (isSelected) {
                Spacer(modifier = Modifier.width(10.dp))
                Box(
                    modifier = Modifier
                        .size(20.dp)
                        .background(Color.White, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = stringResource(R.string.selected),
                        tint = Color.Black,
                        modifier = Modifier.size(14.dp)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun FilterTab(
    text: String,
    isSelected: Boolean,
    isFocused: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .background(
                when {
                    isFocused -> Color.White
                    isSelected -> Color.White.copy(alpha = 0.15f)
                    else -> Color.Transparent
                },
                RoundedCornerShape(8.dp)
            )
            .then(
                if (isSelected && !isFocused) Modifier.border(1.dp, Color.White.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
                else Modifier
            )
            .padding(horizontal = 12.dp, vertical = 10.dp)
    ) {
        Text(
            text = text,
            style = ArflixTypography.body.copy(
                fontSize = 13.sp,
                fontWeight = if (isSelected || isFocused) FontWeight.SemiBold else FontWeight.Normal
            ),
            color = if (isFocused) Color.Black else Color.White,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

// Pre-compiled badge detection regexes. All use word boundaries to avoid matching
// "DV" inside "DVD" or "HDVD" (the long-standing bug that made the DV badge appear
// on SD DVDrip sources). IMAX detection was missing entirely before issue #118.

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun CompactQualityBadge(stream: StreamSource) {
    // IMAX and DV tokens are almost always in the filename / source title, not the
    // pre-extracted `quality` string. Combine all fields we have so detection works
    // regardless of where the token lives. This fixes issue #118.
    val searchBlob = buildString {
        append(stream.quality)
        append(' ')
        append(stream.source)
        append(' ')
        append(stream.behaviorHints?.filename.orEmpty())
    }

    val quality = stream.quality
    val is4K = quality.contains("4K", ignoreCase = true) || quality.contains("2160p")
    val is1080 = quality.contains("1080p")
    val is720 = quality.contains("720p")
    val isHDR = StreamRegexes.HDR.containsMatchIn(searchBlob)
    val isDV = StreamRegexes.DV.containsMatchIn(searchBlob)
    val isIMAX = StreamRegexes.IMAX.containsMatchIn(searchBlob)

    val displayText = when {
        is4K -> "4K"
        is1080 -> "1080p"
        is720 -> "720p"
        else -> quality.split(" ").firstOrNull()?.take(6) ?: "SD"
    }

    val color = when {
        is4K -> AccentGold
        is1080 -> AccentBlue
        is720 -> Color(0xFF06B6D4)
        else -> TextSecondary
    }

    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
        Box(
            modifier = Modifier
                .background(color.copy(alpha = 0.15f), RoundedCornerShape(4.dp))
                .padding(horizontal = 6.dp, vertical = 2.dp)
        ) {
            Text(
                text = displayText,
                style = ArflixTypography.caption.copy(
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Black
                ),
                color = color
            )
        }

        if (isHDR) {
            Box(
                modifier = Modifier
                    .background(AccentPurple.copy(alpha = 0.15f), RoundedCornerShape(4.dp))
                    .padding(horizontal = 5.dp, vertical = 2.dp)
            ) {
                Text(
                    text = "HDR",
                    style = ArflixTypography.caption.copy(
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Black
                    ),
                    color = AccentPurple
                )
            }
        }

        if (isDV) {
            Box(
                modifier = Modifier
                    .background(Color(0xFFEC4899).copy(alpha = 0.15f), RoundedCornerShape(4.dp))
                    .padding(horizontal = 5.dp, vertical = 2.dp)
            ) {
                Text(
                    text = "DV",
                    style = ArflixTypography.caption.copy(
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Black
                    ),
                    color = Color(0xFFEC4899)
                )
            }
        }

        if (isIMAX) {
            // IMAX badge — distinctive cyan/blue to stand out from HDR (purple) and DV (pink).
            // Requested in issue #118 as a premium format that deserves its own badge
            // since users scan the source list for IMAX-tagged releases.
            Box(
                modifier = Modifier
                    .background(Color(0xFF06B6D4).copy(alpha = 0.18f), RoundedCornerShape(4.dp))
                    .padding(horizontal = 5.dp, vertical = 2.dp)
            ) {
                Text(
                    text = "IMAX",
                    style = ArflixTypography.caption.copy(
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Black
                    ),
                    color = Color(0xFF06B6D4)
                )
            }
        }
    }
}

// Helper function to get size in bytes for sorting
// ALWAYS parses the display size string to ensure consistent sorting across all streams
private fun getSizeBytes(stream: StreamSource): Long {
    // ALWAYS parse from display string - don't use sizeBytes field
    // This ensures consistent comparison (some streams have sizeBytes from behaviorHints
    // in actual bytes, others have it parsed with 1024 multiplier - causes inconsistency)
    return parseSizeString(stream.size)
}

// Robust size string parser - handles all common formats
private fun parseSizeString(sizeStr: String): Long {
    if (sizeStr.isBlank()) return 0L

    // Normalize: uppercase, replace comma with dot, remove extra spaces
    val normalized = sizeStr.uppercase()
        .replace(",", ".")
        .replace(StreamRegexes.WHITESPACE, " ")
        .trim()

    // Try multiple regex patterns to catch all formats

    // Pattern 1: "15.2 GB", "6GB", "1.5 TB" etc.
    val pattern1 = StreamRegexes.SIZE_PATTERN_1
    pattern1.find(normalized)?.let { match ->
        val number = match.groupValues[1].toDoubleOrNull() ?: return@let
        val unit = match.groupValues[2]
        return calculateBytes(number, unit)
    }

    // Pattern 2: Numbers with GiB/MiB notation
    val pattern2 = StreamRegexes.SIZE_PATTERN_2
    pattern2.find(normalized)?.let { match ->
        val number = match.groupValues[1].toDoubleOrNull() ?: return@let
        val unit = match.groupValues[2].replace("IB", "B") // Convert TIB->TB, GIB->GB etc.
        return calculateBytes(number, unit)
    }

    // Pattern 3: Just a number (assume bytes) - very rare
    val pattern3 = StreamRegexes.SIZE_PATTERN_3
    pattern3.find(normalized)?.let { match ->
        return match.groupValues[1].toLongOrNull() ?: 0L
    }

    return 0L
}

// Calculate bytes from number and unit
private fun calculateBytes(number: Double, unit: String): Long {
    return when (unit) {
        "TB" -> (number * 1024.0 * 1024.0 * 1024.0 * 1024.0).toLong()
        "GB" -> (number * 1024.0 * 1024.0 * 1024.0).toLong()
        "MB" -> (number * 1024.0 * 1024.0).toLong()
        "KB" -> (number * 1024.0).toLong()
        else -> number.toLong()
    }
}

// Helper function to get quality score for sorting (basic, used for display)
private fun qualityScore(quality: String): Int {
    return when {
        quality.contains("4K", ignoreCase = true) || quality.contains("2160p") -> 4
        quality.contains("1080p", ignoreCase = true) -> 3
        quality.contains("720p", ignoreCase = true) -> 2
        quality.contains("480p", ignoreCase = true) -> 1
        else -> 0
    }
}
