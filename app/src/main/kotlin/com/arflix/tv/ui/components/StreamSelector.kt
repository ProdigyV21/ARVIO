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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Cloud
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
import com.arflix.tv.ui.theme.ArflixTypography
import com.arflix.tv.ui.theme.Pink
import com.arflix.tv.ui.theme.TextPrimary
import com.arflix.tv.ui.theme.TextSecondary

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
    onSelect: (StreamSource) -> Unit = {},
    onClose: () -> Unit = {}
) {
    var focusedIndex by remember { mutableIntStateOf(0) }
    var focusedTabIndex by remember { mutableIntStateOf(0) }
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    var focusZone by remember { mutableStateOf("streams") } // "tabs" or "streams"
    val listState = rememberTvLazyListState()
    val focusRequester = remember { FocusRequester() }

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
            val addonId = stream.addonId.ifBlank { baseName }
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

    // Sort streams: cached first, then largest size, then quality
    val sortedStreams = remember(streams) {
        streams.sortedWith(
            compareByDescending<StreamSource> { it.isCached }
                .thenByDescending { it.sizeBytes ?: parseSize(it.size).toLong() }
                .thenByDescending { qualityScore(it.quality) }
        )
    }

    // Filter streams by selected tab
    val filteredStreams = remember(sortedStreams, selectedTabIndex, addonTabs) {
        if (selectedTabIndex == 0) {
            sortedStreams // All sources
        } else {
            val selectedAddonId = addonTabs.getOrNull(selectedTabIndex - 1)?.id ?: ""
            sortedStreams.filter {
                val addonId = it.addonId.ifBlank { it.addonName }
                addonId == selectedAddonId
            }
        }
    }

    // Group streams by addon for display
    val groupedStreams = remember(filteredStreams) {
        val labelById = addonTabs.associateBy({ it.id }, { it.label })
        filteredStreams.groupBy {
            val addonId = it.addonId.ifBlank { it.addonName }
            labelById[addonId] ?: (it.addonName.split(" - ").firstOrNull()?.trim() ?: it.addonName)
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
            Row(modifier = Modifier.fillMaxSize()) {
                // Left Panel - Compact Info Card
                Box(
                    modifier = Modifier
                        .width(380.dp)
                        .fillMaxHeight()
                        .padding(24.dp)
                ) {
                    // Glassy card
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(24.dp))
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        GlassWhite,
                                        Color.White.copy(alpha = 0.04f)
                                    )
                                )
                            )
                            .border(1.dp, GlassBorder, RoundedCornerShape(24.dp))
                            .padding(28.dp)
                    ) {
                        // Header without icon
                        Column(modifier = Modifier.padding(bottom = 20.dp)) {
                            Text(
                                text = "Sources",
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
                                text = "FILTER BY SOURCE",
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
                        text = "Available Sources",
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
                                    text = "Finding sources...",
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
                                Box(
                                    modifier = Modifier
                                        .size(64.dp)
                                        .background(Color.White.copy(alpha = 0.05f), CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Cloud,
                                        contentDescription = null,
                                        tint = TextSecondary.copy(alpha = 0.5f),
                                        modifier = Modifier.size(32.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(
                                    text = "No sources found",
                                    style = ArflixTypography.body.copy(
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Medium
                                    ),
                                    color = TextSecondary
                                )
                                Text(
                                    text = "Try adding more addons",
                                    style = ArflixTypography.caption.copy(fontSize = 12.sp),
                                    color = TextSecondary.copy(alpha = 0.5f)
                                )
                            }
                        }
                    } else {
                        TvLazyColumn(
                            state = listState,
                            contentPadding = PaddingValues(vertical = 4.dp),
                            verticalArrangement = Arrangement.spacedBy(2.dp),
                            modifier = Modifier.fillMaxSize()
                        ) {
                            // Show flat list - no addon headers, sorted by Cached → Size → Quality
                            flatStreams.forEachIndexed { index, stream ->
                                item {
                                    GlassyStreamCard(
                                        stream = stream,
                                        isFocused = index == focusedIndex,
                                        showAddonName = true,
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
    stream: StreamSource,
    isFocused: Boolean,
    showAddonName: Boolean = false,
    onClick: () -> Unit
) {
    val scale by animateFloatAsState(
        targetValue = if (isFocused) 1.02f else 1f,
        animationSpec = tween(150),
        label = "scale"
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp, vertical = 2.dp)
            .scale(scale)
            .clickable { onClick() }
            .background(
                when {
                    isFocused -> Brush.horizontalGradient(
                        colors = listOf(
                            Pink.copy(alpha = 0.2f),
                            Pink.copy(alpha = 0.1f)
                        )
                    )
                    else -> Brush.horizontalGradient(
                        colors = listOf(GlassWhite, GlassWhite)
                    )
                },
                RoundedCornerShape(10.dp)
            )
            .border(
                width = if (isFocused) 1.5.dp else 1.dp,
                color = if (isFocused) Pink.copy(alpha = 0.6f) else GlassBorder,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(horizontal = 12.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Play button - grey when unfocused, white bg with black icon when focused
        Box(
            modifier = Modifier
                .size(36.dp)
                .background(
                    if (isFocused) Color.White else Color.Gray.copy(alpha = 0.3f),
                    RoundedCornerShape(8.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = "Play",
                tint = if (isFocused) Color.Black else Color.Gray,
                modifier = Modifier.size(18.dp)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        // Stream Info
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = stream.source,
                style = ArflixTypography.body.copy(
                    fontSize = 12.sp,
                    fontWeight = if (isFocused) FontWeight.SemiBold else FontWeight.Normal,
                    lineHeight = 16.sp
                ),
                color = if (isFocused) TextPrimary else TextSecondary.copy(alpha = 0.9f),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Tags Row
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Quality badge
                CompactQualityBadge(stream.quality)

                // Cached indicator
                if (stream.isCached) {
                    CachedBadge()
                }

                // Size
                if (stream.size.isNotEmpty()) {
                    Text(
                        text = stream.size,
                        style = ArflixTypography.caption.copy(
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium
                        ),
                        color = TextSecondary.copy(alpha = 0.6f)
                    )
                }
            }
        }

        // Addon name on right side
        if (showAddonName) {
            val addonShortName = stream.addonName.split(" - ").firstOrNull()?.trim() ?: stream.addonName
            Text(
                text = addonShortName,
                style = ArflixTypography.caption.copy(
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Medium
                ),
                color = TextSecondary.copy(alpha = 0.5f),
                maxLines = 1
            )
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

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun CompactQualityBadge(quality: String) {
    val is4K = quality.contains("4K", ignoreCase = true) || quality.contains("2160p")
    val is1080 = quality.contains("1080p")
    val is720 = quality.contains("720p")
    val isHDR = quality.contains("HDR", ignoreCase = true)
    val isDV = quality.contains("DV", ignoreCase = true) || quality.contains("Dolby Vision", ignoreCase = true)

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
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun CachedBadge() {
    Box(
        modifier = Modifier
            .background(AccentGreen.copy(alpha = 0.15f), RoundedCornerShape(4.dp))
            .padding(horizontal = 5.dp, vertical = 2.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            Icon(
                imageVector = Icons.Rounded.Bolt,
                contentDescription = null,
                tint = AccentGreen,
                modifier = Modifier.size(10.dp)
            )
            Text(
                text = "CACHED",
                style = ArflixTypography.caption.copy(
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Black
                ),
                color = AccentGreen
            )
        }
    }
}

// Helper function to parse size string to bytes for sorting
private fun parseSize(sizeStr: String): Long {
    if (sizeStr.isBlank()) return 0L
    val normalized = sizeStr.uppercase().trim()
    val regex = Regex("([\\d.]+)\\s*(GB|MB|KB|TB)?")
    val match = regex.find(normalized) ?: return 0L
    val number = match.groupValues[1].toDoubleOrNull() ?: return 0L
    val unit = match.groupValues[2]
    return when (unit) {
        "TB" -> (number * 1024 * 1024 * 1024 * 1024).toLong()
        "GB" -> (number * 1024 * 1024 * 1024).toLong()
        "MB" -> (number * 1024 * 1024).toLong()
        "KB" -> (number * 1024).toLong()
        else -> number.toLong()
    }
}

// Helper function to get quality score for sorting
private fun qualityScore(quality: String): Int {
    return when {
        quality.contains("4K", ignoreCase = true) || quality.contains("2160p") -> 4
        quality.contains("1080p", ignoreCase = true) -> 3
        quality.contains("720p", ignoreCase = true) -> 2
        quality.contains("480p", ignoreCase = true) -> 1
        else -> 0
    }
}
