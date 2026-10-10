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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.Text
import com.arflix.tv.R
import com.arflix.tv.ui.focus.arvioDpadFocusGroup
import com.arflix.tv.ui.skin.resolveAccentColor
import com.arflix.tv.ui.theme.ArflixTypography

/** One D-pad stop in the recent-searches row: each query, then one stop that clears them all. */
internal sealed interface RecentSearchSlot {
    data class Query(val query: String) : RecentSearchSlot
    data object Clear : RecentSearchSlot
}

internal fun recentSearchSlotCount(queries: List<String>): Int =
    if (queries.isEmpty()) 0 else queries.size + 1

internal fun recentSearchSlot(queries: List<String>, index: Int): RecentSearchSlot? = when {
    index < 0 || index >= recentSearchSlotCount(queries) -> null
    index == queries.size -> RecentSearchSlot.Clear
    else -> RecentSearchSlot.Query(queries[index])
}

private val CHIP_HEIGHT = 36.dp
private val CHIP_RADIUS = 8.dp

/**
 * The profile's recent searches, shown under the search bar while it is empty. Focus is
 * painted from [focusedIndex], like the filter row below it: the screen's D-pad handler owns
 * the row, and the row itself is the only native focus target.
 */
@Composable
internal fun RecentSearchRow(
    queries: List<String>,
    focusedIndex: Int,
    isRowFocused: Boolean,
    isTouchDevice: Boolean,
    onSearch: (String) -> Unit,
    onClear: () -> Unit,
    modifier: Modifier = Modifier,
    focusRequester: FocusRequester? = null
) {
    if (queries.isEmpty()) return
    val rowState = rememberLazyListState()
    // List items: the label, one per query, then the clear chip.
    LaunchedEffect(focusedIndex, isRowFocused) {
        if (!isRowFocused) return@LaunchedEffect
        val item = 1 + focusedIndex.coerceAtMost(queries.size)
        val visible = rowState.layoutInfo.visibleItemsInfo
        val layout = rowState.layoutInfo
        val info = visible.firstOrNull { it.index == item }
        val fullyVisible = info != null && info.offset >= layout.viewportStartOffset &&
            info.offset + info.size <= layout.viewportEndOffset
        if (!fullyVisible) rowState.animateScrollToItem((item - 1).coerceAtLeast(0))
    }
    fun painted(index: Int) = !isTouchDevice && isRowFocused && focusedIndex == index
    LazyRow(
        state = rowState,
        modifier = modifier
            .testTag("search-recent")
            .then(
                if (!isTouchDevice && focusRequester != null) {
                    Modifier.focusRequester(focusRequester).focusable()
                } else Modifier
            )
            .arvioDpadFocusGroup(),
        horizontalArrangement = Arrangement.spacedBy(if (isTouchDevice) 8.dp else 9.dp),
        verticalAlignment = Alignment.CenterVertically,
        contentPadding = PaddingValues(
            start = if (isTouchDevice) 16.dp else 22.dp,
            end = if (isTouchDevice) 16.dp else 22.dp,
            top = 2.dp,
            bottom = 4.dp
        )
    ) {
        item(key = "label") {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.History, contentDescription = null,
                    tint = Color.White.copy(alpha = 0.6f), modifier = Modifier.size(15.dp))
                Spacer(Modifier.width(6.dp))
                Text(
                    stringResource(R.string.search_recent),
                    style = ArflixTypography.caption.copy(fontSize = 12.sp, fontWeight = FontWeight.Medium),
                    color = Color.White.copy(alpha = 0.6f)
                )
            }
        }
        itemsIndexed(queries, key = { _, query -> "q:$query" }) { index, query ->
            ChipBox(
                focused = painted(index),
                isTouchDevice = isTouchDevice,
                onClick = { onSearch(query) },
                modifier = Modifier.testTag("search-recent-$query")
            ) {
                ChipText(query)
            }
        }
        item(key = "clear") {
            val focused = painted(queries.size)
            ChipBox(
                focused = focused,
                isTouchDevice = isTouchDevice,
                onClick = onClear,
                modifier = Modifier.testTag("search-recent-clear")
            ) {
                ChipText(stringResource(R.string.search_recent_clear))
            }
        }
    }
}

@Composable
private fun ChipBox(
    focused: Boolean,
    isTouchDevice: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val shape = RoundedCornerShape(CHIP_RADIUS)
    val accent = resolveAccentColor(fallback = Color.White)
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .semantics(mergeDescendants = true) {
                role = Role.Button
                // Accessibility activation must not add a second native D-pad focus target.
                if (!isTouchDevice) onClick { onClick(); true }
            }
            .padding(vertical = 2.dp)
            .graphicsLayer { if (focused) { scaleX = 1.05f; scaleY = 1.05f } }
            .height(CHIP_HEIGHT)
            .background(if (focused) Color.White.copy(alpha = 0.16f) else Color.White.copy(alpha = 0.075f), shape)
            .border(if (focused) 2.5.dp else 1.dp, if (focused) accent else Color.White.copy(alpha = 0.24f), shape)
            .then(if (isTouchDevice) Modifier.clickable(onClick = onClick) else Modifier)
            .padding(horizontal = 13.dp)
    ) {
        content()
    }
}

@Composable
private fun ChipText(text: String) {
    Text(
        text = text,
        style = ArflixTypography.caption.copy(fontSize = 12.sp, fontWeight = FontWeight.Medium, letterSpacing = 0.3.sp),
        color = Color.White,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier.widthIn(max = 200.dp)
    )
}
