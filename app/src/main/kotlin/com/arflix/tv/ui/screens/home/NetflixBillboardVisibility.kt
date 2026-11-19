package com.arflix.tv.ui.screens.home

import com.arflix.tv.data.model.Category
import kotlinx.coroutines.delay

/** Runs in the profile's visibility effect, so a new candidate or refresh cancels the grace timer. */
internal suspend fun updateNetflixBillboardVisibility(
    enabled: Boolean,
    hasCandidate: Boolean,
    isLoading: Boolean,
    wasHidden: Boolean,
    focusState: HomeFocusState,
    categories: () -> List<Category>,
    onHiddenChanged: (Boolean) -> Unit
) {
    if (!enabled) {
        focusState.isHeroFocused = false
        onHiddenChanged(false)
        return
    }
    if (hasCandidate) {
        if (wasHidden && !focusState.userHasNavigated && !focusState.isSidebarFocused) {
            focusState.isHeroFocused = true
            focusState.heroButtonIndex = 0
        }
        onHiddenChanged(false)
        return
    }
    // Loading is not a new title. Keep the existing visibility and focus until one arrives.
    if (isLoading) return

    delay(3_000)
    onHiddenChanged(true)
    if (!focusState.isHeroFocused) return
    focusState.isHeroFocused = false
    if (!focusState.isSidebarFocused) {
        categories().firstOrNull()?.let { firstRow ->
            focusState.currentRowIndex = 0
            focusState.preferredCategoryId = firstRow.id
            focusState.currentItemIndex = focusState.restoredItemIndex(
                firstRow,
                focusState.rowItemIndicesByCategoryId[firstRow.id] ?: 0
            )
        }
    }
}
