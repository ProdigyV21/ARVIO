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
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Storage
import androidx.compose.material.icons.rounded.Bolt
import androidx.compose.material.icons.rounded.Movie
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.foundation.lazy.list.TvLazyColumn
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
import com.arflix.tv.R

// Modern glassy colors
private val GlassWhite = Color.White.copy(alpha = 0.08f)
private val GlassBorder = Color.White.copy(alpha = 0.12f)
private val GlassHighlight = Color.White.copy(alpha = 0.15f)
private val AccentGreen = Color(0xFF10B981)
private val AccentBlue = Color(0xFF3B82F6)
private val AccentPurple = Color(0xFF8B5CF6)
private val AccentGold = Color(0xFFF59E0B)

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
    onFocusedStream: (StreamSource) -> Unit = {},
    onSelect: (StreamSource) -> Unit = {},
    onClose: () -> Unit = {}
) {
    var focusedIndex by remember { mutableIntStateOf(0) }
    var focusedTabIndex by remember { mutableIntStateOf(0) }
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    var focusZone by remember { mutableStateOf("streams") } // "tabs" or "streams"
    val listState = rememberTvLazyListState()
    val focusRequester = remember { FocusRequester() }
    val isMobile = LocalDeviceType.current.isTouchDevice()

    // Request focus when visible
    LaunchedEffect(isVisible) {
        if (isVisible) {
            focusRequester.requestFocus()
            focusedIndex = 0
            focusedTabIndex = 0
            selectedTabIndex = 0
            focusZone = "streams"
        }
    }

    data class AddonTab(val id: String, val label: String)

    // Build addon tabs using addonId so multiple instances of the same addon are shown separately.
    val addonTabs = remember(streams) {
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
        }
    }

    // Tab labels: "All sources" + addon labels
    val tabLabels = remember(addonTabs) {
        listOf("All sources") + addonTabs.map { it.label }
    }

    val presentations = remember(streams) { streams.map(::presentSource) }

    // Sort streams with richer heuristics:
    // cached/direct first, then resolution, then release type, then size.
    val sortedStreams = remember(presentations) {
        presentations.sortedWith(compareByDescending<SourcePresentation> { it.sortCached }
            .thenByDescending { it.sortDirect }
            .thenByDescending { it.resolutionScore }
            .thenByDescending { it.releaseScore }
            .thenByDescending { it.sizeBytes }
            .thenBy { it.title.lowercase() })
            .map { it.stream }
    }

    // Filter streams by selected tab
    val filteredStreams = remember(sortedStreams, selectedTabIndex, addonTabs) {
        if (selectedTabIndex == 0) {
            sortedStreams // All sources
        } else {
            val selectedAddonId = addonTabs.getOrNull(selectedTabIndex - 1)?.id ?: ""
            sortedStreams.filter {
                sourceTabId(it) == selectedAddonId
            }
        }
    }

    // Group streams by addon for display
    val groupedStreams = remember(filteredStreams) {
        val labelById = addonTabs.associateBy({ it.id }, { it.label })
        filteredStreams.groupBy {
            val tabId = sourceTabId(it)
            labelById[tabId] ?: (it.addonName.split(" - ").firstOrNull()?.trim() ?: it.addonName)
        }
    }

    // Flatten for navigation
    val flatStreams = filteredStreams

    // Scroll to focused item
    LaunchedEffect(focusedIndex) {
        if (flatStreams.isNotEmpty() && focusedIndex < flatStreams.size) {
            listState.animateScrollToItem(focusedIndex)
        }
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
                        when (event.key) {
                            Key.Back, Key.Escape -> {
                                onClose()
                                true
                            }
                            Key.DirectionUp -> {
                                if (focusZone == "tabs") {
                                    if (focusedTabIndex > 0) {
                                        focusedTabIndex--
                                        selectedTabIndex = focusedTabIndex  // Immediately filter on focus
                                        focusedIndex = 0  // Reset stream selection
                                    }
                                } else {
                                    if (focusedIndex > 0) focusedIndex--
                                }
                                true
                            }
                            Key.DirectionDown -> {
                                if (focusZone == "tabs") {
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
                                if (focusZone == "streams" && tabLabels.size > 1) {
                                    focusZone = "tabs"
                                    focusedTabIndex = selectedTabIndex
                                    // Filter already applied, no need to change selectedTabIndex
                                }
                                true
                            }
                            Key.DirectionRight -> {
                                if (focusZone == "tabs") {
                                    focusZone = "streams"
                                    focusedIndex = 0
                                }
                                true
                            }
                            Key.Enter, Key.DirectionCenter -> {
                                if (focusZone == "tabs") {
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
            Row(modifier = Modifier.fillMaxSize()) {
                // Left Panel - Compact Info Card
                Box(
                    modifier = Modifier
                        .width(380.dp)
                        .fillMaxHeight()
                        .padding(24.dp)
                ) {
                    // Flat sheet section — no boxed card border, feels more modern/premium.
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                    ) {
                        // Header without icon
                        Column(modifier = Modifier.padding(bottom = 20.dp)) {
                            Text(
                                text = stringResource(R.string.sources),
                                style = ArflixTypography.label.copy(
                                    fontSize = 12.sp,
                                    letterSpacing = 1.sp
                                ),
                                color = TextSecondary
                            )
                            Text(
                                text = title.ifEmpty { "Select Source" },
                                style = ArflixTypography.body.copy(
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                ),
                                color = TextPrimary,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }

                        if (subtitle.isNotEmpty()) {
                            Text(
                                text = subtitle,
                                style = ArflixTypography.caption.copy(fontSize = 13.sp),
                                color = TextSecondary.copy(alpha = 0.7f),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.padding(bottom = 20.dp)
                            )
                        }

                        // Stats Grid
                        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                                MiniStatCard(
                                    icon = Icons.Default.Storage,
                                    value = streams.size.toString(),
                                    label = "Total",
                                    color = AccentBlue,
                                    modifier = Modifier.weight(1f)
                                )
                                MiniStatCard(
                                    icon = Icons.Default.HighQuality,
                                    value = count4K.toString(),
                                    label = "4K",
                                    color = AccentGold,
                                    modifier = Modifier.weight(1f)
                                )
                            }
                            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                                MiniStatCard(
                                    icon = Icons.Default.Speed,
                                    value = count1080.toString(),
                                    label = "1080p",
                                    color = AccentPurple,
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }

                        // Addon Filter Tabs
                        if (tabLabels.size > 1) {
                            Spacer(modifier = Modifier.height(20.dp))
                            Text(
                                text = stringResource(R.string.sources).uppercase(),
                                style = ArflixTypography.label.copy(
                                    fontSize = 10.sp,
                                    letterSpacing = 1.sp
                                ),
                                color = TextSecondary.copy(alpha = 0.6f),
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                tabLabels.forEachIndexed { index, label ->
                                    FilterTab(
                                        text = label,
                                        isSelected = index == selectedTabIndex,
                                        isFocused = focusZone == "tabs" && index == focusedTabIndex,
                                        onClick = {
                                            selectedTabIndex = index
                                            focusedIndex = 0
                                        }
                                    )
                                }
                            }
                        }
                    }
                }

                // Right Panel - Stream List
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .padding(top = 24.dp, end = 24.dp, bottom = 24.dp)
                ) {
                    // Header
                    Text(
                        text = stringResource(R.string.available_sources),
                        style = ArflixTypography.body.copy(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        ),
                        color = TextPrimary,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 12.dp)
                    )

                    if (isLoading) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                LoadingIndicator(color = Pink, size = 48.dp)
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(
                                    text = stringResource(R.string.finding_sources),
                                    style = ArflixTypography.body.copy(fontSize = 14.sp),
                                    color = TextSecondary
                                )
                            }
                        }
                    } else if (streams.isEmpty()) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .background(GlassWhite, RoundedCornerShape(20.dp))
                                    .border(1.dp, GlassBorder, RoundedCornerShape(20.dp))
                                    .padding(40.dp)
                            ) {
                                val iconColor = if (!hasStreamingAddons) Color(0xFF3B82F6) else TextSecondary.copy(alpha = 0.5f)
                                Box(
                                    modifier = Modifier
                                        .size(64.dp)
                                        .background(iconColor.copy(alpha = 0.1f), CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = if (!hasStreamingAddons) Icons.Default.Settings else Icons.Default.Cloud,
                                        contentDescription = null,
                                        tint = iconColor,
                                        modifier = Modifier.size(32.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(
                                    text = if (!hasStreamingAddons) "No Streaming Addons" else "No sources found",
                                    style = ArflixTypography.body.copy(
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Medium
                                    ),
                                    color = TextSecondary
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = if (!hasStreamingAddons)
                                        "Go to Settings \u2192 Addons to add\na streaming addon"
                                    else
                                        "Try adding more addons",
                                    style = ArflixTypography.caption.copy(fontSize = 12.sp),
                                    color = TextSecondary.copy(alpha = 0.6f),
                                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                                )
                            }
                        }
                    } else {
                        TvLazyColumn(
                            state = listState,
                            contentPadding = PaddingValues(vertical = 4.dp),
                            verticalArrangement = Arrangement.spacedBy(2.dp),
                            modifier = Modifier.fillMaxSize().arvioDpadFocusGroup()
                        ) {
                            // Show flat list - no addon headers, sorted by Cached → Size → Quality
                            flatStreams.forEachIndexed { index, stream ->
                                item {
                                    GlassyStreamCard(
                                        presentation = presentSource(stream),
                                        isFocused = index == focusedIndex,
                                        isSelected = stream == selectedStream,
                                        onClick = { onSelect(stream) }
                                    )
                                }
                            }
                        }
                    }
                }
            }
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
                                text = title.ifEmpty { "Select Source" },
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
                    if (isLoading) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                LoadingIndicator(color = Pink, size = 40.dp)
                                Spacer(modifier = Modifier.height(12.dp))
                                Text(
                                    text = stringResource(R.string.finding_sources),
                                    style = ArflixTypography.body.copy(fontSize = 14.sp),
                                    color = TextSecondary
                                )
                            }
                        }
                    } else if (streams.isEmpty()) {
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
                                    text = if (!hasStreamingAddons) "No Streaming Addons" else "No sources found",
                                    style = ArflixTypography.body.copy(
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Medium
                                    ),
                                    color = TextSecondary
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = if (!hasStreamingAddons)
                                        "Go to Settings \u2192 Addons to add\na streaming addon"
                                    else
                                        "Try adding more addons",
                                    style = ArflixTypography.caption.copy(fontSize = 12.sp),
                                    color = TextSecondary.copy(alpha = 0.6f),
                                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                                )
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
                            items(flatStreams) { stream ->
                                MobileStreamCard(
                                    presentation = presentSource(stream),
                                    isSelected = stream == selectedStream,
                                    onClick = { onSelect(stream) }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

private data class SourcePresentation(
    val stream: StreamSource,
    val title: String,
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
    val statusLabel: String?,
    val chips: List<String>,
    val qualityColor: Color,
    val sizeBytes: Long,
    val sortCached: Boolean,
    val sortDirect: Boolean
)



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
    val CH71 = Regex("""\b7[ .]?1\b""", RegexOption.IGNORE_CASE)
    val CH51 = Regex("""\b5[ .]?1\b""", RegexOption.IGNORE_CASE)
    val MULTI_AUDIO = Regex("""\b(MULTI|DUAL[ .-]?AUDIO|MULTI[ .-]?AUDIO)\b""", RegexOption.IGNORE_CASE)
    val LANGUAGE_HINT = Regex("""\b(ENG|ENGLISH|HIN|HINDI|TAM|TAMIL|TEL|TELUGU|JPN|JAPANESE|KOR|KOREAN|SPA|SPANISH|FRE|FRENCH|GER|GERMAN|ITA|ITALIAN)\b""", RegexOption.IGNORE_CASE)
    val DV = Regex("""\b(DV|DoVi|Dolby[\s._-]*Vision)\b""", RegexOption.IGNORE_CASE)
    val HDR = Regex("""\bHDR(10\+?|10)?\b""", RegexOption.IGNORE_CASE)
    val IMAX = Regex("""\bIMAX\b""", RegexOption.IGNORE_CASE)
    val WHITESPACE = Regex("""\s+""")
    val SIZE_PATTERN_1 = Regex("""(\d+(?:\.\d+)?)\s*(TB|GB|MB|KB)""")
    val SIZE_PATTERN_2 = Regex("""(\d+(?:\.\d+)?)\s*(TIB|GIB|MIB|KIB)""")
    val SIZE_PATTERN_3 = Regex("""^(\d+(?:\.\d+)?)$""")
}

private fun sourceTabId(stream: StreamSource): String {
    val baseName = stream.addonName.split(" - ").firstOrNull()?.trim() ?: stream.addonName
    return if (stream.addonId == "home_server" && baseName.isNotBlank()) {
        "${stream.addonId}:$baseName"
    } else {
        stream.addonId.ifBlank { baseName }
    }
}

private fun presentSource(stream: StreamSource): SourcePresentation {
    val title = stream.behaviorHints?.filename?.takeIf { it.isNotBlank() } ?: stream.source
    val addonLabel = stream.addonName.split(" - ").firstOrNull()?.trim() ?: stream.addonName
    val searchBlob = buildString {
        append(stream.quality)
        append(' ')
        append(stream.source)
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

    val transportLabel = when {
        stream.behaviorHints?.cached == true -> "Cached"
        !stream.infoHash.isNullOrBlank() || stream.sources.isNotEmpty() || isTorrentProvider -> "Torrent"
        isIptvVod && hasDirectHttpUrl -> "VOD"
        else -> null
    }
    val statusLabel = when {
        stream.behaviorHints?.cached == true -> "Best Match"
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
        statusLabel = statusLabel,
        chips = chips.distinct(),
        qualityColor = qualityColor,
        sizeBytes = getSizeBytes(stream),
        sortCached = stream.behaviorHints?.cached == true,
        sortDirect = !stream.url.isNullOrBlank() && stream.url.startsWith("http", true)
    )
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
                if (isSelected) Pink.copy(alpha = 0.12f) else Color.White.copy(alpha = 0.045f)
            )
            .clickable { onClick() }
            .padding(14.dp),
        verticalAlignment = Alignment.Top
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = presentation.title,
                    style = ArflixTypography.body.copy(fontSize = 14.sp, fontWeight = FontWeight.SemiBold),
                    color = TextPrimary,
                    modifier = Modifier.weight(1f),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                PremiumQualityPill(presentation)
            }

            Spacer(modifier = Modifier.height(8.dp))
            SourceMetadataChips(presentation = presentation, compact = true)
        }

        if (isSelected) {
            Spacer(modifier = Modifier.width(10.dp))
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .background(Pink, CircleShape),
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
                        text = presentation.title,
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

