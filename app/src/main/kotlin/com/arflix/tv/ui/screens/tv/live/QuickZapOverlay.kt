package com.arflix.tv.ui.screens.tv.live

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import com.arflix.tv.R
import com.arflix.tv.data.model.IptvNowNext

/**
 * Helper to flat list categories with items.
 */
private fun getAvailableCategories(tree: LiveCategoryTree): List<LiveCategory> {
    val list = mutableListOf<LiveCategory>()
    tree.top.forEach { cat ->
        if (cat.count > 0 || cat.id == "all") {
            list.add(cat)
            if (cat.id == "all") {
                cat.children.forEach { child ->
                    if (child.count > 0) list.add(child)
                }
            }
        }
    }
    tree.global.categories.forEach { cat ->
        if (cat.count > 0) list.add(cat)
    }
    tree.countries.categories.forEach { country ->
        if (country.count > 0) {
            list.add(country)
            country.children.forEach { child ->
                if (child.count > 0) list.add(child)
            }
        }
    }
    tree.adult.categories.forEach { cat ->
        if (cat.count > 0) list.add(cat)
    }
    return list.distinctBy { it.id }
}

/**
 * Vertical Quick-Channel Navigation Overlay (QuickZapOverlay)
 * Displays a centered list of channels and a navigatable category sidebar
 * over a semi-transparent black background.
 */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun QuickZapOverlay(
    visible: Boolean,
    currentChannel: EnrichedChannel?,
    channels: List<EnrichedChannel>,
    nowNextMap: Map<String, IptvNowNext>,
    categoriesTree: LiveCategoryTree,
    selectedCategoryId: String,
    onCategorySelected: (String) -> Unit,
    onDismiss: () -> Unit,
    onChannelSelect: (EnrichedChannel) -> Unit,
    onRightClick: (EnrichedChannel) -> Unit,
    modifier: Modifier = Modifier,
) {
    val categories = remember(categoriesTree) { getAvailableCategories(categoriesTree) }

    var selectedCategoryIndex by remember(categories, selectedCategoryId) {
        val idx = categories.indexOfFirst { it.id == selectedCategoryId }
        mutableIntStateOf(if (idx >= 0) idx else 0)
    }

    var selectedChannelIndex by remember(channels, currentChannel) {
        val idx = channels.indexOfFirst { it.id == currentChannel?.id }
        mutableIntStateOf(if (idx >= 0) idx else 0)
    }

    var categoryListFocused by remember { mutableStateOf(false) }
    var originalCategoryId by remember { mutableStateOf(selectedCategoryId) }

    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(visible) {
        if (visible) {
            originalCategoryId = selectedCategoryId
            categoryListFocused = false
            runCatching { focusRequester.requestFocus() }
        }
    }

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(tween(300)) + slideInVertically(tween(300)) { it / 6 },
        exit = fadeOut(tween(200)) + slideOutVertically(tween(200)) { it / 6 },
        modifier = modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0.90f),
                            Color.Black.copy(alpha = 0.78f),
                            Color.Black.copy(alpha = 0.90f)
                        )
                    )
                )
                .focusRequester(focusRequester)
                .focusable()
                .onKeyEvent { ev ->
                    if (ev.type != KeyEventType.KeyDown) return@onKeyEvent false
                    if (categoryListFocused) {
                        when (ev.key) {
                            Key.DirectionUp -> {
                                if (categories.isNotEmpty()) {
                                    selectedCategoryIndex = (selectedCategoryIndex - 1 + categories.size) % categories.size
                                    onCategorySelected(categories[selectedCategoryIndex].id)
                                }
                                true
                            }
                            Key.DirectionDown -> {
                                if (categories.isNotEmpty()) {
                                    selectedCategoryIndex = (selectedCategoryIndex + 1) % categories.size
                                    onCategorySelected(categories[selectedCategoryIndex].id)
                                }
                                true
                            }
                            Key.DirectionRight, Key.DirectionCenter, Key.Enter -> {
                                categoryListFocused = false
                                true
                            }
                            Key.DirectionLeft, Key.Back, Key.Escape -> {
                                onCategorySelected(originalCategoryId)
                                categoryListFocused = false
                                true
                            }
                            else -> false
                        }
                    } else {
                        when (ev.key) {
                            Key.DirectionUp -> {
                                if (channels.isNotEmpty()) {
                                    selectedChannelIndex = (selectedChannelIndex - 1 + channels.size) % channels.size
                                }
                                true
                            }
                            Key.DirectionDown -> {
                                if (channels.isNotEmpty()) {
                                    selectedChannelIndex = (selectedChannelIndex + 1) % channels.size
                                }
                                true
                            }
                            Key.DirectionLeft -> {
                                originalCategoryId = selectedCategoryId
                                categoryListFocused = true
                                true
                            }
                            Key.DirectionRight -> {
                                if (channels.isNotEmpty() && selectedChannelIndex in channels.indices) {
                                    onRightClick(channels[selectedChannelIndex])
                                }
                                true
                            }
                            Key.DirectionCenter, Key.Enter -> {
                                if (channels.isNotEmpty() && selectedChannelIndex in channels.indices) {
                                    onChannelSelect(channels[selectedChannelIndex])
                                }
                                true
                            }
                            Key.Back, Key.Escape -> {
                                onDismiss()
                                true
                            }
                            else -> false
                        }
                    }
                }
        ) {
            // Header Info Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
                    .padding(horizontal = 56.dp, vertical = 28.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (categoryListFocused) {
                        stringResource(R.string.select_category).uppercase()
                    } else {
                        "< ${stringResource(R.string.channel_categories).uppercase()}"
                    },
                    style = LiveType.SectionTag.copy(
                        color = if (categoryListFocused) LiveColors.Accent else LiveColors.FgMute,
                        fontSize = 11.sp,
                        letterSpacing = 1.sp,
                        fontWeight = if (categoryListFocused) FontWeight.Bold else FontWeight.Normal
                    )
                )
                Text(
                    text = (categories.getOrNull(selectedCategoryIndex)?.label?.let { liveCategoryLabel(it) } ?: stringResource(R.string.live_label_all_channels)).uppercase(),
                    style = LiveType.ChannelName.copy(
                        color = LiveColors.Fg,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.5.sp
                    ),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "${stringResource(R.string.channel_guide).uppercase()} >",
                    style = LiveType.SectionTag.copy(
                        color = LiveColors.FgMute,
                        fontSize = 11.sp,
                        letterSpacing = 1.sp
                    )
                )
            }

            // Two-column Content Area
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(top = 96.dp, bottom = 48.dp, start = 48.dp, end = 48.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Left Column: Category Sidebar Panel (Always visible, highlighted when focused)
                CategorySidebarPanel(
                    categories = categories,
                    selectedIndex = selectedCategoryIndex,
                    isFocused = categoryListFocused,
                    modifier = Modifier
                        .width(260.dp)
                        .alpha(if (categoryListFocused) 1.0f else 0.45f)
                )

                Spacer(modifier = Modifier.width(48.dp))

                // Right Column: Channel Column Panel
                ChannelColumnPanel(
                    channels = channels,
                    selectedIndex = selectedChannelIndex,
                    isFocused = !categoryListFocused,
                    nowNextMap = nowNextMap,
                    modifier = Modifier
                        .width(640.dp)
                        .alpha(if (!categoryListFocused) 1.0f else 0.55f)
                )
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun CategorySidebarPanel(
    categories: List<LiveCategory>,
    selectedIndex: Int,
    isFocused: Boolean,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        if (categories.isNotEmpty()) {
            // Render 3 slots above focused category
            for (offset in -3..-1) {
                val index = (selectedIndex + offset + categories.size * 10) % categories.size
                categories.getOrNull(index)?.let { cat ->
                    NonFocusedCategoryRow(label = liveCategoryLabel(cat.label))
                }
            }

            // Centered Focused Category
            categories.getOrNull(selectedIndex)?.let { cat ->
                FocusedCategoryRow(label = liveCategoryLabel(cat.label), isFocused = isFocused)
            }

            // Render 3 slots below focused category
            for (offset in 1..3) {
                val index = (selectedIndex + offset) % categories.size
                categories.getOrNull(index)?.let { cat ->
                    NonFocusedCategoryRow(label = liveCategoryLabel(cat.label))
                }
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun NonFocusedCategoryRow(label: String) {
    Text(
        text = label,
        style = LiveType.CellTitle.copy(
            color = LiveColors.FgDim.copy(alpha = 0.65f),
            fontSize = 13.sp
        ),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier
            .fillMaxWidth()
            .height(36.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    )
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun FocusedCategoryRow(label: String, isFocused: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(if (isFocused) LiveColors.PanelRaised else Color.Transparent)
            .border(
                width = if (isFocused) 1.5.dp else 0.dp,
                color = if (isFocused) LiveColors.FocusRing else Color.Transparent,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        if (isFocused) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowUp,
                    contentDescription = null,
                    tint = LiveColors.Accent,
                    modifier = Modifier.size(10.dp)
                )
                Spacer(modifier = Modifier.height(2.dp))
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    tint = LiveColors.Accent,
                    modifier = Modifier.size(10.dp)
                )
            }
        }
        Text(
            text = label,
            style = LiveType.CellTitle.copy(
                color = if (isFocused) LiveColors.Accent else LiveColors.Fg,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun ChannelColumnPanel(
    channels: List<EnrichedChannel>,
    selectedIndex: Int,
    isFocused: Boolean,
    nowNextMap: Map<String, IptvNowNext>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (channels.isNotEmpty()) {
            // Render 3 slots above focused channel
            for (offset in -3..-1) {
                val index = (selectedIndex + offset + channels.size * 10) % channels.size
                channels.getOrNull(index)?.let { ch ->
                    val nowNext = nowNextMap[ch.id]
                    NonFocusedChannelSlot(
                        number = ch.number,
                        channel = ch,
                        nowNext = nowNext
                    )
                }
            }

            // Centered Focused Channel Slot (offset = 0)
            channels.getOrNull(selectedIndex)?.let { ch ->
                val nowNext = nowNextMap[ch.id]
                FocusedChannelSlot(
                    number = ch.number,
                    channel = ch,
                    nowNext = nowNext,
                    isFocused = isFocused
                )
            }

            // Render 4 slots below focused channel
            for (offset in 1..4) {
                val index = (selectedIndex + offset) % channels.size
                channels.getOrNull(index)?.let { ch ->
                    val nowNext = nowNextMap[ch.id]
                    NonFocusedChannelSlot(
                        number = ch.number,
                        channel = ch,
                        nowNext = nowNext
                    )
                }
            }
        } else {
            Text(
                text = stringResource(R.string.live_empty_no_channels_category),
                style = LiveType.CellTitle.copy(color = LiveColors.FgMute),
                modifier = Modifier.padding(vertical = 48.dp)
            )
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun NonFocusedChannelSlot(
    number: Int,
    channel: EnrichedChannel,
    nowNext: IptvNowNext?
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(42.dp)
            .padding(horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = number.toString(),
            style = LiveType.NumberMono.copy(
                color = LiveColors.FgMute,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold
            ),
            modifier = Modifier.width(42.dp),
            textAlign = TextAlign.End
        )

        Box(
            modifier = Modifier
                .width(42.dp)
                .height(28.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(LiveColors.Panel)
        ) {
            ChannelLogo(channel = channel, size = 28.dp)
        }

        Text(
            text = nowNext?.now?.title ?: channel.name,
            style = LiveType.CellTitle.copy(
                color = LiveColors.FgDim.copy(alpha = 0.75f),
                fontSize = 12.sp
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f)
        )
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun FocusedChannelSlot(
    number: Int,
    channel: EnrichedChannel,
    nowNext: IptvNowNext?,
    isFocused: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(96.dp)
            .clip(RoundedCornerShape(LiveDims.CardRadius))
            .border(
                width = if (isFocused) 2.dp else 0.dp,
                color = if (isFocused) LiveColors.FocusRing else Color.Transparent,
                shape = RoundedCornerShape(LiveDims.CardRadius)
            )
            .background(LiveColors.PanelRaised)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.width(42.dp)
        ) {
            if (isFocused) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowUp,
                    contentDescription = null,
                    tint = LiveColors.FgDim,
                    modifier = Modifier.size(16.dp)
                )
            }
            Text(
                text = number.toString(),
                style = LiveType.NumberMono.copy(
                    color = if (isFocused) LiveColors.Accent else LiveColors.FgDim,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            if (isFocused) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    tint = LiveColors.FgDim,
                    modifier = Modifier.size(16.dp)
                )
            }
        }

        Box(
            modifier = Modifier
                .width(64.dp)
                .height(48.dp)
                .clip(RoundedCornerShape(LiveDims.CellRadius))
                .background(LiveColors.Panel)
        ) {
            ChannelLogo(channel = channel, size = 48.dp)
        }

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            val now = nowNext?.now
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = now?.title ?: channel.name,
                    style = LiveType.ProgramTitle.copy(
                        color = LiveColors.Fg,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )
                now?.let {
                    Text(
                        text = formatTimeWindow(it),
                        style = LiveType.TimeMono.copy(
                            color = LiveColors.Accent,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold
                        ),
                        modifier = Modifier.padding(start = 12.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            val progress = progressOf(now)
            LinearProgressIndicator(
                progress = { progress ?: 0f },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(3.dp)
                    .clip(RoundedCornerShape(1.5.dp)),
                color = LiveColors.Accent,
                trackColor = LiveColors.Divider
            )
        }
    }
}
