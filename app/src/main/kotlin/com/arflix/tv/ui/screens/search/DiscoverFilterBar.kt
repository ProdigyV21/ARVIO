package com.arflix.tv.ui.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.tv.material3.Text
import com.arflix.tv.ui.focus.arvioDpadFocusGroup
import com.arflix.tv.ui.skin.resolveAccentColor
import com.arflix.tv.ui.theme.ArflixTypography

/**
 * One control in the discover row, in the "icon chips" shape the maintainer picked out of four
 * drafts: the chip keeps the shape it already had, gains a 14 dp icon, and shows its **value**
 * instead of its label once it is set — "Top rated" where it used to say "Sort".
 */
internal data class DiscoverChip(
    val id: DiscoverFilterId,
    val key: String,
    /** What the chip says while untouched. */
    val label: String,
    /** What it says instead once a value is set; `null` keeps the label. */
    val value: String? = null,
    val icon: ImageVector? = null,
    /** A chip that opens a panel carries a chevron; a plain toggle does not. */
    val hasPanel: Boolean = true,
    val isSet: Boolean = false,
    /** A filter TMDB cannot apply here greys out instead of disappearing. */
    val isEnabled: Boolean = true,
    /**
     * The media type is drawn as one segmented switch rather than a chip with a panel, exactly
     * as in the draft: with only three values, showing them beats hiding them behind a list.
     */
    val segments: List<String>? = null,
    val selectedSegment: Int = 0,
    val onSelectSegment: ((Int) -> Unit)? = null,
    val onActivate: () -> Unit
)

/** Height, radius and spacing all come from the approved draft. */
private val CHIP_HEIGHT = 36.dp
private val CHIP_RADIUS = 8.dp
private val CHIP_ICON_SIZE = 14.dp
private val CHIP_CHEVRON_SIZE = 11.dp
private val CHIP_GAP = 7.dp

/**
 * The filter row. Focus is painted, not held: the screen owns one index for the whole row and
 * the chips only render it, exactly as the rows and the grid do — a second native focus target
 * would fight the screen's own D-pad handler.
 */
@Composable
internal fun DiscoverFilterRow(
    chips: List<DiscoverChip>,
    focusedIndex: Int,
    isRowFocused: Boolean,
    isTouchDevice: Boolean,
    modifier: Modifier = Modifier,
    focusRequester: FocusRequester? = null
) {
    if (chips.isEmpty()) return
    val rowState = rememberLazyListState()
    // Ä3: the row follows the focus only when the focused chip is not WHOLLY in view.
    // `firstVisibleItemIndex` counts a chip as visible while half of it hangs over the left
    // edge, which is how the media-type switch stayed cut in half after scrolling to the end
    // and back — it was "visible", so nothing scrolled it back into place.
    LaunchedEffect(focusedIndex, chips.size) {
        val target = focusedIndex.coerceIn(0, (chips.size - 1).coerceAtLeast(0))
        val layout = rowState.layoutInfo
        val item = layout.visibleItemsInfo.firstOrNull { it.index == target }
        val fullyVisible = item != null && isChipFullyVisible(
            itemOffset = item.offset,
            itemSize = item.size,
            contentStart = layout.viewportStartOffset + layout.beforeContentPadding,
            contentEnd = layout.viewportEndOffset - layout.afterContentPadding
        )
        if (!fullyVisible) rowState.animateScrollToItem(target)
    }
    LazyRow(
        state = rowState,
        modifier = modifier
            .padding(bottom = 8.dp)
            // The row, not a chip, is the native focus target: the screen's D-pad handler needs
            // somewhere to send the focus when it hands the row over, and a chip that came and
            // went with the filter set would take that anchor with it.
            .then(
                if (!isTouchDevice && focusRequester != null) {
                    Modifier.focusRequester(focusRequester).focusable()
                } else Modifier
            )
            .arvioDpadFocusGroup(),
        horizontalArrangement = Arrangement.spacedBy(if (isTouchDevice) 8.dp else 9.dp),
        contentPadding = PaddingValues(
            start = if (isTouchDevice) 16.dp else 22.dp,
            end = if (isTouchDevice) 16.dp else 22.dp,
            top = 4.dp,
            bottom = 4.dp
        )
    ) {
        itemsIndexed(chips, key = { _, chip -> chip.key }) { index, chip ->
            val chipModifier = Modifier.testTag("search-filter-${chip.key}")
            val focused = !isTouchDevice && isRowFocused && focusedIndex == index
            if (chip.segments != null) {
                SegmentedTypeControl(chip, focused, isTouchDevice, chipModifier)
            } else {
                IconFilterChip(chip, focused, isTouchDevice, chipModifier)
            }
        }
    }
}

/**
 * A single chip in its four states: untouched, focused, set, unavailable. The colours are the
 * ones the row already used, so a chip that gains an icon still sits in the same row as before.
 */
@Composable
internal fun IconFilterChip(
    chip: DiscoverChip,
    isVisuallyFocused: Boolean,
    isTouchDevice: Boolean,
    modifier: Modifier = Modifier
) {
    val focused = isVisuallyFocused
    val shape = RoundedCornerShape(CHIP_RADIUS)
    val accent = resolveAccentColor(fallback = Color.White)
    val background = when {
        !chip.isEnabled -> Color.White.copy(alpha = 0.03f)
        focused -> Color.White.copy(alpha = 0.16f)
        chip.isSet -> Color.White.copy(alpha = 0.92f)
        else -> Color.White.copy(alpha = 0.075f)
    }
    val border = when {
        !chip.isEnabled -> Color.White.copy(alpha = 0.18f)
        focused -> accent
        chip.isSet -> Color.White.copy(alpha = 0.92f)
        else -> Color.White.copy(alpha = 0.24f)
    }
    val foreground = when {
        !chip.isEnabled -> Color.White.copy(alpha = 0.32f)
        chip.isSet && !focused -> Color.Black
        else -> Color.White
    }
    val text = chip.value?.takeIf { chip.isSet && it.isNotBlank() } ?: chip.label
    Box(
        modifier = modifier
            .semantics(mergeDescendants = true) {
                selected = chip.isSet
                role = Role.Tab
                // Accessibility activation must not add a second native D-pad focus target.
                if (!isTouchDevice && chip.isEnabled) onClick { chip.onActivate(); true }
            }
            .padding(vertical = 2.dp)
            .graphicsLayer { if (focused) { scaleX = 1.05f; scaleY = 1.05f } }
            .height(CHIP_HEIGHT)
            .background(background, shape)
            .border(if (focused) 2.5.dp else 1.dp, border, shape)
            .then(if (isTouchDevice && chip.isEnabled) Modifier.clickable { chip.onActivate() } else Modifier)
            .padding(horizontal = 13.dp)
    ) {
        ChipContent(chip = chip, text = text, foreground = foreground)
    }
}

@Composable
private fun ChipContent(chip: DiscoverChip, text: String, foreground: Color) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.height(CHIP_HEIGHT)) {
        chip.icon?.let { icon ->
            Icon(icon, contentDescription = null, tint = foreground, modifier = Modifier.size(CHIP_ICON_SIZE))
            Spacer(Modifier.width(CHIP_GAP))
        }
        Text(
            text = text,
            style = ArflixTypography.caption.copy(
                fontSize = 12.sp,
                fontWeight = if (chip.isSet) FontWeight.SemiBold else FontWeight.Medium,
                letterSpacing = 0.3.sp
            ),
            color = foreground,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.widthIn(max = 160.dp)
        )
        if (chip.hasPanel) {
            Spacer(Modifier.width(CHIP_GAP))
            Icon(
                Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                tint = foreground,
                modifier = Modifier.size(CHIP_CHEVRON_SIZE).alpha(if (chip.isEnabled) 1f else 0.6f)
            )
        }
    }
}

/**
 * The media-type switch: all values visible side by side, the current one filled in.
 *
 * It is a single stop for the D-pad — pressing OK moves to the next value — so the row keeps
 * one chip, one press, one focus ring, and left/right still mean "next filter" everywhere.
 */
@Composable
internal fun SegmentedTypeControl(
    chip: DiscoverChip,
    isVisuallyFocused: Boolean,
    isTouchDevice: Boolean,
    modifier: Modifier = Modifier
) {
    val segments = chip.segments.orEmpty()
    val shape = RoundedCornerShape(CHIP_RADIUS)
    val accent = resolveAccentColor(fallback = Color.White)
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .then(
                if (!isTouchDevice) {
                    Modifier.semantics(mergeDescendants = true) {
                        role = Role.Tab
                        onClick { chip.onActivate(); true }
                    }
                } else Modifier
            )
            .padding(vertical = 2.dp)
            .graphicsLayer { if (isVisuallyFocused) { scaleX = 1.05f; scaleY = 1.05f } }
            .height(CHIP_HEIGHT)
            .background(Color.White.copy(alpha = 0.06f), shape)
            .border(
                if (isVisuallyFocused) 2.5.dp else 1.dp,
                if (isVisuallyFocused) accent else Color.White.copy(alpha = 0.18f),
                shape
            )
            .padding(3.dp)
    ) {
        segments.forEachIndexed { index, label ->
            val selected = index == chip.selectedSegment
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(
                        if (selected) Color.White.copy(alpha = 0.92f) else Color.Transparent,
                        RoundedCornerShape(8.dp)
                    )
                    .then(
                        if (isTouchDevice) {
                            Modifier.clickable {
                                chip.onSelectSegment?.invoke(index) ?: chip.onActivate()
                            }
                        } else Modifier
                    )
                    .padding(horizontal = 13.dp, vertical = 6.dp)
            ) {
                Text(
                    label,
                    style = ArflixTypography.caption.copy(
                        fontSize = 12.sp,
                        fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Medium,
                        letterSpacing = 0.3.sp
                    ),
                    color = if (selected) Color.Black else Color.White.copy(alpha = 0.7f),
                    maxLines = 1
                )
            }
        }
    }
}
