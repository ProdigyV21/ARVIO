package com.arflix.tv.ui.screens.settings

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.DragHandle
import androidx.compose.material.icons.filled.Extension
import androidx.compose.material.icons.filled.LiveTv
import androidx.compose.material.icons.filled.Widgets
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.Text
import com.arflix.tv.R
import com.arflix.tv.data.model.StreamIntegrationConfig
import com.arflix.tv.data.model.StreamIntegrationType
import com.arflix.tv.data.model.StreamProviderItem
import com.arflix.tv.data.model.StreamSearchMode
import com.arflix.tv.ui.components.DragHandleReorderColumn
import com.arflix.tv.ui.skin.resolveAccentColor
import com.arflix.tv.ui.theme.ArflixTypography
import com.arflix.tv.ui.theme.BackgroundElevated
import com.arflix.tv.ui.theme.Pink
import com.arflix.tv.ui.theme.SuccessGreen
import com.arflix.tv.ui.theme.TextPrimary
import com.arflix.tv.ui.theme.TextSecondary
import com.arflix.tv.util.LocalDeviceType

@Composable
fun StreamIntegrationsScreen(
    items: List<StreamProviderItem>,
    searchMode: StreamSearchMode = StreamSearchMode.PARALLEL,
    onSearchModeChange: (StreamSearchMode) -> Unit = {},
    focusedIndex: Int = -1,
    focusedActionIndex: Int = 0,
    onToggle: (StreamProviderItem) -> Unit = {},
    onMoveUp: (StreamProviderItem) -> Unit = {},
    onMoveDown: (StreamProviderItem) -> Unit = {},
    onConfigure: (StreamProviderItem) -> Unit = {},
    // Touch only: a row dragged by its handle moves several places in one go, see ProviderRowMobile.
    onMoveBy: (StreamProviderItem, Int) -> Unit = { _, _ -> },
    pageScrollState: ScrollState? = null
) {
    val isMobile = LocalDeviceType.current.isTouchDevice()

    if (isMobile) {
        MobileStreamIntegrationsContent(
            items = items,
            searchMode = searchMode,
            onSearchModeChange = onSearchModeChange,
            onToggle = onToggle,
            onMoveBy = onMoveBy,
            pageScrollState = pageScrollState
        )
    } else {
        TvStreamIntegrationsContent(
            items = items,
            searchMode = searchMode,
            onSearchModeChange = onSearchModeChange,
            focusedIndex = focusedIndex,
            focusedActionIndex = focusedActionIndex,
            onToggle = onToggle,
            onMoveUp = onMoveUp,
            onMoveDown = onMoveDown
        )
    }
}

/**
 * Mobile layout: Clean, compact settings hierarchy without duplicated headings.
 */
@Composable
private fun MobileStreamIntegrationsContent(
    items: List<StreamProviderItem>,
    searchMode: StreamSearchMode,
    onSearchModeChange: (StreamSearchMode) -> Unit,
    onToggle: (StreamProviderItem) -> Unit,
    onMoveBy: (StreamProviderItem, Int) -> Unit,
    pageScrollState: ScrollState?
) {
    val accentColor = resolveAccentColor(fallback = Pink)

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // Search Mode Section
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = stringResource(R.string.settings_stream_search_mode).uppercase(),
                style = ArflixTypography.caption.copy(fontSize = 12.sp, letterSpacing = 1.sp),
                color = TextSecondary,
                modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
            )

            // 2-option segmented control: 48dp height, full width, 12dp corner radius
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(BackgroundElevated)
                    .padding(3.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val isParallel = searchMode == StreamSearchMode.PARALLEL

                // Parallel Option
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(12.dp))
                        .background(
                            if (isParallel) accentColor.copy(alpha = 0.22f) else Color.Transparent
                        )
                        .border(
                            width = if (isParallel) 1.dp else 0.dp,
                            color = if (isParallel) accentColor.copy(alpha = 0.55f) else Color.Transparent,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .clickable { onSearchModeChange(StreamSearchMode.PARALLEL) },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.settings_stream_search_mode_parallel),
                        style = ArflixTypography.cardTitle.copy(
                            fontSize = 14.sp,
                            fontWeight = if (isParallel) FontWeight.SemiBold else FontWeight.Medium
                        ),
                        color = if (isParallel) Color.White else TextSecondary
                    )
                }

                // Sequential Option
                val isSequential = searchMode == StreamSearchMode.SEQUENTIAL
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(12.dp))
                        .background(
                            if (isSequential) accentColor.copy(alpha = 0.22f) else Color.Transparent
                        )
                        .border(
                            width = if (isSequential) 1.dp else 0.dp,
                            color = if (isSequential) accentColor.copy(alpha = 0.55f) else Color.Transparent,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .clickable { onSearchModeChange(StreamSearchMode.SEQUENTIAL) },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.settings_stream_search_mode_sequential),
                        style = ArflixTypography.cardTitle.copy(
                            fontSize = 14.sp,
                            fontWeight = if (isSequential) FontWeight.SemiBold else FontWeight.Medium
                        ),
                        color = if (isSequential) Color.White else TextSecondary
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = if (searchMode == StreamSearchMode.PARALLEL) {
                    stringResource(R.string.settings_stream_search_mode_parallel_desc)
                } else {
                    stringResource(R.string.settings_stream_search_mode_sequential_desc)
                },
                style = ArflixTypography.caption.copy(fontSize = 12.sp),
                color = TextSecondary,
                lineHeight = 16.sp,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        // Integrations Section
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = stringResource(R.string.settings_stream_integrations_list_title).uppercase(),
                style = ArflixTypography.caption.copy(fontSize = 12.sp, letterSpacing = 1.sp),
                color = TextSecondary,
                modifier = Modifier.padding(start = 16.dp, bottom = 4.dp)
            )
            Text(
                text = stringResource(R.string.settings_stream_integrations_helper),
                style = ArflixTypography.caption.copy(fontSize = 12.sp),
                color = TextSecondary.copy(alpha = 0.7f),
                modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
            )

            // Rounded container matching Addons section (BackgroundElevated, 12dp corner radius, borderless)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(BackgroundElevated)
            ) {
                if (items.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(R.string.settings_no_stream_integrations_configured),
                            style = ArflixTypography.caption.copy(fontSize = 13.sp),
                            color = TextSecondary
                        )
                    }
                } else {
                    val showReorder = items.size > 1
                    DragHandleReorderColumn(
                        items = items,
                        key = { it.id },
                        onDrop = { item, from, to -> onMoveBy(item, to - from) },
                        heldBackground = BackgroundElevated,
                        heldAccent = accentColor,
                        pageScrollState = pageScrollState,
                        enabled = showReorder
                    ) { item, index, _, handle, isHeld ->
                        Column {
                            ProviderRowMobile(
                                item = item,
                                showReorder = showReorder,
                                isHeld = isHeld,
                                handle = handle,
                                onToggle = { onToggle(item) }
                            )

                            if (index < items.size - 1 && !isHeld) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(1.dp)
                                        .padding(horizontal = 16.dp)
                                        .background(Color.White.copy(alpha = 0.05f))
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

/**
 * Mobile Provider Row matching Addons section: 16dp horizontal & 14dp vertical padding,
 * 24dp icon tinted TextSecondary, 16sp title, 13sp subtitle,
 * standard 44x24dp toggle switch with 18dp thumb, and a drag handle at the end.
 *
 * The handle replaced a pair of up/down buttons: one tap moved a source one place, so putting the
 * last of ten sources first took nine taps. It is held and dragged like the IPTV categories.
 */
@Composable
private fun ProviderRowMobile(
    item: StreamProviderItem,
    showReorder: Boolean,
    isHeld: Boolean,
    handle: Modifier,
    onToggle: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onToggle() }
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IntegrationIcon(
            type = item.type,
            tint = TextSecondary,
            size = 24.dp
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = item.displayName,
                style = ArflixTypography.cardTitle.copy(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                ),
                color = TextPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            val subtitleText = item.subtitle ?: stringResource(item.type.titleRes)
            Text(
                text = subtitleText,
                style = ArflixTypography.caption.copy(fontSize = 13.sp),
                color = TextSecondary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        // Standard settings toggle switch: 44x24dp with 18dp thumb
        Box(
            modifier = Modifier
                .width(44.dp)
                .height(24.dp)
                .background(
                    color = if (item.isEnabled) SuccessGreen else Color.White.copy(alpha = 0.2f),
                    shape = RoundedCornerShape(16.dp)
                )
                .clickable { onToggle() }
                .padding(3.dp),
            contentAlignment = if (item.isEnabled) Alignment.CenterEnd else Alignment.CenterStart
        ) {
            Box(
                modifier = Modifier
                    .size(18.dp)
                    .background(Color.White, RoundedCornerShape(12.dp))
            )
        }

        if (showReorder) {
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .then(handle),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Default.DragHandle,
                    contentDescription = stringResource(R.string.settings_cd_drag_reorder),
                    tint = if (isHeld) resolveAccentColor(fallback = Pink) else TextSecondary,
                    modifier = Modifier.size(22.dp)
                )
            }
        }
    }
}

/**
 * TV Layout: Spacious, constrained width (~680dp), 64dp segmented control,
 * 76dp rows with 52x32dp switches, 40x40dp up/down buttons, clear D-pad focus states.
 */
@Composable
private fun TvStreamIntegrationsContent(
    items: List<StreamProviderItem>,
    searchMode: StreamSearchMode,
    onSearchModeChange: (StreamSearchMode) -> Unit,
    focusedIndex: Int,
    focusedActionIndex: Int,
    onToggle: (StreamProviderItem) -> Unit,
    onMoveUp: (StreamProviderItem) -> Unit,
    onMoveDown: (StreamProviderItem) -> Unit
) {
    val accentColor = resolveAccentColor(fallback = Pink)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .widthIn(max = 680.dp)
    ) {
        // Search Mode Section (Slot 0)
        Text(
            text = stringResource(R.string.settings_stream_search_mode),
            style = ArflixTypography.caption.copy(
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold
            ),
            color = TextSecondary
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Large 2-option segmented control: 64dp height, 14dp radius, equal-width options
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .settingsFocusSlot(0)
                .background(Color.White.copy(alpha = 0.04f), RoundedCornerShape(16.dp))
                .border(1.dp, Color.White.copy(alpha = 0.08f), RoundedCornerShape(16.dp))
                .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val isParallelSelected = searchMode == StreamSearchMode.PARALLEL
            val isParallelFocused = focusedIndex == 0 && focusedActionIndex == 0

            // Parallel Option
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(12.dp))
                    .background(
                        when {
                            isParallelFocused -> accentColor.copy(alpha = 0.3f)
                            isParallelSelected -> accentColor.copy(alpha = 0.2f)
                            else -> Color.Transparent
                        }
                    )
                    .border(
                        width = if (isParallelFocused) 2.dp else if (isParallelSelected) 1.dp else 0.dp,
                        color = when {
                            isParallelFocused -> accentColor
                            isParallelSelected -> accentColor.copy(alpha = 0.5f)
                            else -> Color.Transparent
                        },
                        shape = RoundedCornerShape(12.dp)
                    )
                    .clickable { onSearchModeChange(StreamSearchMode.PARALLEL) }
                    .padding(horizontal = 14.dp, vertical = 6.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Column(verticalArrangement = Arrangement.Center) {
                    Text(
                        text = stringResource(R.string.settings_stream_search_mode_parallel),
                        style = ArflixTypography.cardTitle.copy(
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold
                        ),
                        color = if (isParallelFocused || isParallelSelected) Color.White else TextPrimary
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = stringResource(R.string.settings_stream_search_mode_parallel_desc),
                        style = ArflixTypography.caption.copy(fontSize = 12.sp),
                        color = if (isParallelFocused) Color.White.copy(alpha = 0.9f) else TextSecondary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Spacer(modifier = Modifier.width(4.dp))

            // Sequential Option
            val isSequentialSelected = searchMode == StreamSearchMode.SEQUENTIAL
            val isSequentialFocused = focusedIndex == 0 && focusedActionIndex == 1

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(12.dp))
                    .background(
                        when {
                            isSequentialFocused -> accentColor.copy(alpha = 0.3f)
                            isSequentialSelected -> accentColor.copy(alpha = 0.2f)
                            else -> Color.Transparent
                        }
                    )
                    .border(
                        width = if (isSequentialFocused) 2.dp else if (isSequentialSelected) 1.dp else 0.dp,
                        color = when {
                            isSequentialFocused -> accentColor
                            isSequentialSelected -> accentColor.copy(alpha = 0.5f)
                            else -> Color.Transparent
                        },
                        shape = RoundedCornerShape(12.dp)
                    )
                    .clickable { onSearchModeChange(StreamSearchMode.SEQUENTIAL) }
                    .padding(horizontal = 14.dp, vertical = 6.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Column(verticalArrangement = Arrangement.Center) {
                    Text(
                        text = stringResource(R.string.settings_stream_search_mode_sequential),
                        style = ArflixTypography.cardTitle.copy(
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold
                        ),
                        color = if (isSequentialFocused || isSequentialSelected) Color.White else TextPrimary
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = stringResource(R.string.settings_stream_search_mode_sequential_desc),
                        style = ArflixTypography.caption.copy(fontSize = 12.sp),
                        color = if (isSequentialFocused) Color.White.copy(alpha = 0.9f) else TextSecondary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Integrations Section
        Text(
            text = stringResource(R.string.settings_stream_integrations_list_title),
            style = ArflixTypography.caption.copy(
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold
            ),
            color = TextSecondary
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = stringResource(R.string.settings_stream_integrations_helper),
            style = ArflixTypography.caption.copy(fontSize = 13.sp),
            color = TextSecondary.copy(alpha = 0.7f)
        )
        Spacer(modifier = Modifier.height(12.dp))

        // Rounded container: 14dp corner radius, 1dp border
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White.copy(alpha = 0.04f))
                .border(1.dp, Color.White.copy(alpha = 0.08f), RoundedCornerShape(16.dp))
        ) {
            if (items.isEmpty()) {
                Text(
                    text = stringResource(R.string.settings_no_stream_integrations_configured),
                    style = ArflixTypography.caption.copy(fontSize = 13.sp),
                    color = TextSecondary.copy(alpha = 0.7f),
                    modifier = Modifier.padding(24.dp)
                )
            } else {
                Column(modifier = Modifier.fillMaxWidth()) {
                    val showReorder = items.size > 1
                    items.forEachIndexed { index, item ->
                        val slotIndex = index + 1
                        val isRowFocused = focusedIndex == slotIndex

                        ProviderRowTV(
                            item = item,
                            showReorder = showReorder,
                            isFocused = isRowFocused,
                            focusedAction = if (isRowFocused) focusedActionIndex else -1,
                            onToggle = { onToggle(item) },
                            onMoveUp = { onMoveUp(item) },
                            onMoveDown = { onMoveDown(item) },
                            modifier = Modifier.settingsFocusSlot(slotIndex)
                        )

                        if (index < items.size - 1) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(1.dp)
                                    .background(Color.White.copy(alpha = 0.06f))
                            )
                        }
                    }
                }
            }
        }
    }
}

/**
 * TV Provider Row: 76dp height, 16dp horizontal padding, 28dp icon, 16dp spacing,
 * 52x32dp toggle switch, and 40x40dp up/down buttons with 8dp gap (hidden when <= 1 item).
 */
@Composable
private fun ProviderRowTV(
    item: StreamProviderItem,
    showReorder: Boolean,
    isFocused: Boolean,
    focusedAction: Int,
    onToggle: () -> Unit,
    onMoveUp: () -> Unit,
    onMoveDown: () -> Unit,
    modifier: Modifier = Modifier
) {
    val isToggleFocused = isFocused && focusedAction == 0
    val isMoveUpFocused = isFocused && focusedAction == 1
    val isMoveDownFocused = isFocused && focusedAction == 2
    val isEnabled = item.isEnabled
    val accentColor = resolveAccentColor(fallback = Pink)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 76.dp)
            .background(
                if (isFocused) Color.White.copy(alpha = 0.08f) else Color.Transparent
            )
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Icon and details
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(accentColor.copy(alpha = 0.15f), RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                IntegrationIcon(type = item.type, tint = accentColor, size = 28.dp)
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = item.displayName,
                    style = ArflixTypography.cardTitle.copy(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    ),
                    color = TextPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                val subtitleText = item.subtitle ?: stringResource(item.type.titleRes)
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = subtitleText,
                    style = ArflixTypography.caption.copy(fontSize = 13.sp),
                    color = TextSecondary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Actions: Toggle switch (52x32dp) + optional Up/Down (40x40dp)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Action 0: Toggle Switch (44x24dp with 18dp thumb to match standard settings toggles)
            Box(
                modifier = Modifier
                    .border(
                        width = if (isToggleFocused) 2.dp else 0.dp,
                        color = if (isToggleFocused) accentColor else Color.Transparent,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(2.dp)
            ) {
                Box(
                    modifier = Modifier
                        .width(44.dp)
                        .height(24.dp)
                        .background(
                            color = if (isEnabled) SuccessGreen else Color.White.copy(alpha = 0.2f),
                            shape = RoundedCornerShape(16.dp)
                        )
                        .clickable { onToggle() }
                        .padding(3.dp),
                    contentAlignment = if (isEnabled) Alignment.CenterEnd else Alignment.CenterStart
                ) {
                    Box(
                        modifier = Modifier
                            .size(18.dp)
                            .background(Color.White, RoundedCornerShape(12.dp))
                    )
                }
            }

            if (showReorder) {
                // Action 1: Move Up
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .border(
                            width = if (isMoveUpFocused) 2.dp else 0.dp,
                            color = if (isMoveUpFocused) accentColor else Color.Transparent,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .clickable(enabled = item.canMoveUp) { onMoveUp() }
                        .background(
                            if (isMoveUpFocused) Color.White.copy(alpha = 0.18f)
                            else Color.White.copy(alpha = if (item.canMoveUp) 0.08f else 0.03f),
                            RoundedCornerShape(8.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Default.ArrowUpward,
                        contentDescription = stringResource(R.string.stream_integration_move_up),
                        tint = if (isMoveUpFocused) Color.White else if (item.canMoveUp) TextPrimary else TextSecondary.copy(alpha = 0.25f),
                        modifier = Modifier.size(18.dp)
                    )
                }

                // Action 2: Move Down
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .border(
                            width = if (isMoveDownFocused) 2.dp else 0.dp,
                            color = if (isMoveDownFocused) accentColor else Color.Transparent,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .clickable(enabled = item.canMoveDown) { onMoveDown() }
                        .background(
                            if (isMoveDownFocused) Color.White.copy(alpha = 0.18f)
                            else Color.White.copy(alpha = if (item.canMoveDown) 0.08f else 0.03f),
                            RoundedCornerShape(8.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Default.ArrowDownward,
                        contentDescription = stringResource(R.string.stream_integration_move_down),
                        tint = if (isMoveDownFocused) Color.White else if (item.canMoveDown) TextPrimary else TextSecondary.copy(alpha = 0.25f),
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}

/**
 * Backward compatibility overload for configs.
 */
@Composable
fun StreamIntegrationsScreen(
    configs: List<StreamIntegrationConfig>,
    homeServerCount: Int = 0,
    stremioAddonsCount: Int = 0,
    pluginsCount: Int = 0,
    isTelegramConnected: Boolean = false,
    iptvPlaylistsCount: Int = 0,
    focusedIndex: Int = -1,
    focusedActionIndex: Int = 0,
    onToggle: (StreamIntegrationType) -> Unit = {},
    onMoveUp: (StreamIntegrationType) -> Unit = {},
    onMoveDown: (StreamIntegrationType) -> Unit = {},
    onConfigure: (StreamIntegrationType) -> Unit = {}
) {
    val items = configs.mapIndexed { index, config ->
        val statusText = getStatusSummary(
            type = config.type,
            homeServerCount = homeServerCount,
            stremioAddonsCount = stremioAddonsCount,
            pluginsCount = pluginsCount,
            isTelegramConnected = isTelegramConnected,
            iptvPlaylistsCount = iptvPlaylistsCount
        )
        StreamProviderItem(
            id = config.type.id,
            rawProviderKeys = emptyList(),
            displayName = stringResource(config.type.titleRes),
            subtitle = statusText,
            type = config.type,
            isEnabled = config.isEnabled,
            priority = index + 1,
            canMoveUp = index > 0,
            canMoveDown = index < configs.lastIndex
        )
    }

    StreamIntegrationsScreen(
        items = items,
        focusedIndex = focusedIndex,
        focusedActionIndex = focusedActionIndex,
        onToggle = { onToggle(it.type) },
        onMoveUp = { onMoveUp(it.type) },
        onMoveDown = { onMoveDown(it.type) },
        onConfigure = { onConfigure(it.type) }
    )
}

@Composable
private fun IntegrationIcon(type: StreamIntegrationType, tint: Color, size: androidx.compose.ui.unit.Dp) {
    when (type) {
        StreamIntegrationType.HOME_SERVER -> Icon(
            imageVector = Icons.Default.Cloud,
            contentDescription = null,
            tint = tint,
            modifier = Modifier.size(size)
        )
        StreamIntegrationType.STREMIO_ADDONS -> Icon(
            imageVector = Icons.Default.Extension,
            contentDescription = null,
            tint = tint,
            modifier = Modifier.size(size)
        )
        StreamIntegrationType.PLUGINS -> Icon(
            imageVector = Icons.Default.Widgets,
            contentDescription = null,
            tint = tint,
            modifier = Modifier.size(size)
        )
        StreamIntegrationType.TELEGRAM -> Icon(
            painter = painterResource(R.drawable.ic_telegram),
            contentDescription = null,
            tint = tint,
            modifier = Modifier.size(size)
        )
        StreamIntegrationType.IPTV_VOD -> Icon(
            imageVector = Icons.Default.LiveTv,
            contentDescription = null,
            tint = tint,
            modifier = Modifier.size(size)
        )
    }
}

@Composable
private fun getStatusSummary(
    type: StreamIntegrationType,
    homeServerCount: Int,
    stremioAddonsCount: Int,
    pluginsCount: Int,
    isTelegramConnected: Boolean,
    iptvPlaylistsCount: Int
): String {
    return when (type) {
        StreamIntegrationType.HOME_SERVER -> {
            if (homeServerCount > 0) {
                stringResource(R.string.settings_pill_connected_count, homeServerCount)
            } else {
                stringResource(R.string.settings_disconnected)
            }
        }
        StreamIntegrationType.STREMIO_ADDONS -> {
            stringResource(R.string.settings_pill_installed, stremioAddonsCount)
        }
        StreamIntegrationType.PLUGINS -> {
            if (pluginsCount > 0) {
                stringResource(R.string.settings_pill_integrations, pluginsCount)
            } else {
                stringResource(R.string.settings_disabled)
            }
        }
        StreamIntegrationType.TELEGRAM -> {
            if (isTelegramConnected) {
                stringResource(R.string.connected)
            } else {
                stringResource(R.string.settings_disconnected)
            }
        }
        StreamIntegrationType.IPTV_VOD -> {
            if (iptvPlaylistsCount > 0) {
                stringResource(R.string.settings_pill_playlists, iptvPlaylistsCount)
            } else {
                stringResource(R.string.settings_disabled)
            }
        }
    }
}
