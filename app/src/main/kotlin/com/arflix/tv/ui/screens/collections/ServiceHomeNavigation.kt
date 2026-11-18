package com.arflix.tv.ui.screens.collections

import com.arflix.tv.data.model.Category
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.ui.screens.home.HomeFocusState
import com.arflix.tv.ui.screens.home.homeRowSupportsViewAll
import com.arflix.tv.ui.screens.home.homeRowViewAllIndex
import com.arflix.tv.ui.screens.home.isActionableHomeItem
import com.arflix.tv.ui.screens.home.navigableHomeItems

internal enum class ServiceHomeKey { LEFT, RIGHT, UP, DOWN, SELECT, BACK }

internal sealed interface ServiceHomeAction {
    /** The key moved focus, or did nothing worth leaving the page for. */
    data object Moved : ServiceHomeAction
    data class Open(val item: MediaItem, val play: Boolean) : ServiceHomeAction
    data class ViewAll(val rowId: String) : ServiceHomeAction
    data class Filter(val filter: ServiceHome.Filter) : ServiceHomeAction
    data object Retry : ServiceHomeAction
    /** The header's Filter button: the grid, with its genre filter, on the applied filter's tab. */
    data object OpenGrid : ServiceHomeAction
    data object Exit : ServiceHomeAction
}

/** The header's Filter button, after the All / Series / Movies buttons. */
internal val SERVICE_HOME_GRID_BUTTON_INDEX = ServiceHome.Filter.entries.size

/**
 * D-pad on a service page, on the same focus model as the Netflix-style Home: the big card
 * (with Play and More info) above the rows, rows of titles with a trailing "View all" slot.
 * Where Home has its top bar, the page has its All / Series / Movies buttons and a Filter button
 * that opens the grid
 * ([HomeFocusState.isSidebarFocused], the button at [HomeFocusState.sidebarFocusIndex]).
 * Back from the big card or the buttons leaves the page.
 */
internal fun handleServiceHomeKey(
    key: ServiceHomeKey,
    focusState: HomeFocusState,
    rows: List<Category>,
    hasMore: Map<String, Boolean>,
    billboard: MediaItem?,
    isRtl: Boolean,
    now: Long,
    /** The applied filter: Up from the content lands on its button. */
    selectedFilter: ServiceHome.Filter = ServiceHome.Filter.ALL,
    canRetry: Boolean = false,
): ServiceHomeAction {
    focusState.reconcile(rows, hasMore)
    focusState.userHasNavigated = true
    focusState.lastNavEventTime = now

    if (focusState.isSidebarFocused) {
        val filters = ServiceHome.Filter.entries
        return when (key) {
            ServiceHomeKey.LEFT, ServiceHomeKey.RIGHT -> {
                val forward = (key == ServiceHomeKey.RIGHT) != isRtl
                focusState.sidebarFocusIndex = (focusState.sidebarFocusIndex + if (forward) 1 else -1)
                    .coerceIn(0, SERVICE_HOME_GRID_BUTTON_INDEX)
                ServiceHomeAction.Moved
            }
            ServiceHomeKey.SELECT -> {
                val index = focusState.sidebarFocusIndex.coerceIn(0, SERVICE_HOME_GRID_BUTTON_INDEX)
                if (index == SERVICE_HOME_GRID_BUTTON_INDEX) ServiceHomeAction.OpenGrid
                else ServiceHomeAction.Filter(filters[index])
            }
            ServiceHomeKey.DOWN -> {
                focusState.isSidebarFocused = false
                if (billboard == null && rows.isNotEmpty()) {
                    focusState.isHeroFocused = true // enterRow saves no row position from here
                    enterRow(focusState, rows, 0)
                } else {
                    focusState.isHeroFocused = true
                }
                ServiceHomeAction.Moved
            }
            ServiceHomeKey.UP -> ServiceHomeAction.Moved
            ServiceHomeKey.BACK -> ServiceHomeAction.Exit
        }
    }

    val onBillboard = focusState.isHeroFocused || rows.isEmpty()
    if (onBillboard) {
        return when (key) {
            ServiceHomeKey.LEFT, ServiceHomeKey.RIGHT -> {
                val forward = (key == ServiceHomeKey.RIGHT) != isRtl
                focusState.heroButtonIndex = if (forward) 1 else 0
                ServiceHomeAction.Moved
            }
            ServiceHomeKey.UP -> {
                focusState.isSidebarFocused = true
                focusState.sidebarFocusIndex = selectedFilter.ordinal
                ServiceHomeAction.Moved
            }
            ServiceHomeKey.DOWN -> {
                if (rows.isNotEmpty()) enterRow(focusState, rows, 0)
                ServiceHomeAction.Moved
            }
            ServiceHomeKey.SELECT -> if (canRetry && rows.isEmpty()) {
                ServiceHomeAction.Retry
            } else {
                billboard?.takeIf { isActionableHomeItem(it) }
                    ?.let { ServiceHomeAction.Open(it, play = focusState.heroButtonIndex == 0) }
                    ?: ServiceHomeAction.Moved
            }
            ServiceHomeKey.BACK -> ServiceHomeAction.Exit
        }
    }

    val row = rows[focusState.currentRowIndex.coerceIn(0, rows.lastIndex)]
    val rowHasMore = hasMore[row.id] == true
    return when (key) {
        ServiceHomeKey.LEFT, ServiceHomeKey.RIGHT -> {
            val forward = (key == ServiceHomeKey.RIGHT) != isRtl
            val last = navigableHomeItems(row.items).size - 1 + if (homeRowSupportsViewAll(row, rowHasMore)) 1 else 0
            focusState.currentItemIndex = (focusState.currentItemIndex + if (forward) 1 else -1).coerceIn(0, last.coerceAtLeast(0))
            focusState.recordSelection(rows)
            ServiceHomeAction.Moved
        }
        ServiceHomeKey.UP -> {
            if (focusState.currentRowIndex > 0) {
                enterRow(focusState, rows, focusState.currentRowIndex - 1)
            } else if (billboard != null) {
                toBillboard(focusState, rows)
            } else {
                // No big card: the first row's Up reaches the buttons.
                rows.getOrNull(focusState.currentRowIndex)?.let { focusState.rowItemIndicesByCategoryId[it.id] = focusState.currentItemIndex }
                focusState.isSidebarFocused = true
                focusState.sidebarFocusIndex = selectedFilter.ordinal
            }
            ServiceHomeAction.Moved
        }
        ServiceHomeKey.DOWN -> {
            if (focusState.currentRowIndex < rows.lastIndex) enterRow(focusState, rows, focusState.currentRowIndex + 1)
            ServiceHomeAction.Moved
        }
        ServiceHomeKey.SELECT -> {
            if (focusState.currentItemIndex == homeRowViewAllIndex(row, rowHasMore)) {
                ServiceHomeAction.ViewAll(row.id)
            } else {
                navigableHomeItems(row.items).getOrNull(focusState.currentItemIndex)
                    ?.takeIf { isActionableHomeItem(it) }
                    ?.let { ServiceHomeAction.Open(it, play = false) }
                    ?: ServiceHomeAction.Moved
            }
        }
        ServiceHomeKey.BACK -> if (billboard != null) {
            toBillboard(focusState, rows)
            ServiceHomeAction.Moved
        } else {
            ServiceHomeAction.Exit
        }
    }
}

/** Leaves the current row for [target], restoring where focus last rested in that row. */
private fun enterRow(focusState: HomeFocusState, rows: List<Category>, target: Int) {
    if (!focusState.isHeroFocused) {
        rows.getOrNull(focusState.currentRowIndex)?.let { focusState.rowItemIndicesByCategoryId[it.id] = focusState.currentItemIndex }
    }
    val row = rows[target]
    focusState.isHeroFocused = false
    focusState.currentRowIndex = target
    focusState.preferredCategoryId = row.id
    focusState.currentItemIndex = focusState.restoredItemIndex(row, focusState.rowItemIndicesByCategoryId[row.id] ?: 0)
    focusState.recordSelection(rows)
}

private fun toBillboard(focusState: HomeFocusState, rows: List<Category>) {
    rows.getOrNull(focusState.currentRowIndex)?.let { focusState.rowItemIndicesByCategoryId[it.id] = focusState.currentItemIndex }
    focusState.recordSelection(rows)
    focusState.isHeroFocused = true
    focusState.heroButtonIndex = 0
}

/** A new filter shows other rows: focus starts over at the big card, the buttons kept focused. */
internal fun HomeFocusState.startFilteredPage() {
    rowItemIndicesByCategoryId.clear()
    rowItemKeysByCategoryId.clear()
    preferredCategoryId = null
    currentRowIndex = 0
    currentItemIndex = 0
    isHeroFocused = true
    heroButtonIndex = 0
    scrollResetRequest++
}
