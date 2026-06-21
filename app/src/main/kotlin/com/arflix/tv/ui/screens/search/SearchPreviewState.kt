package com.arflix.tv.ui.screens.search

import com.arflix.tv.data.model.Category
import com.arflix.tv.data.model.MediaItem

internal data class SearchPreviewState(
    val displayTitle: String,
    val focusedItem: MediaItem,
    val sourceCategoryTitle: String,
    val isPersonResult: Boolean,
    val backgroundImageUrl: String?
)

internal fun resolveSearchPreview(
    categories: List<Category>,
    currentRowIndex: Int,
    currentItemIndex: Int,
    aiResults: List<MediaItem> = emptyList(),
    hasAiResults: Boolean = false
): SearchPreviewState? {
    if (hasAiResults) {
        val item = aiResults.getOrNull(currentItemIndex.coerceAtLeast(0))
            ?: aiResults.firstOrNull()
            ?: return null
        return item.toSearchPreviewState(
            displayTitle = item.title,
            sourceCategoryTitle = "",
            isPersonResult = false
        )
    }

    val rows = categories.filter { it.items.isNotEmpty() }
    if (rows.isEmpty()) return null

    val rowIndex = currentRowIndex.coerceIn(0, rows.lastIndex)
    val category = rows[rowIndex]
    val itemIndex = currentItemIndex.coerceIn(0, category.items.lastIndex)
    val item = category.items[itemIndex]
    val isPersonResult = category.id.startsWith("person_")

    return item.toSearchPreviewState(
        displayTitle = if (isPersonResult) category.title else item.title,
        sourceCategoryTitle = category.title,
        isPersonResult = isPersonResult
    )
}

private fun MediaItem.toSearchPreviewState(
    displayTitle: String,
    sourceCategoryTitle: String,
    isPersonResult: Boolean
): SearchPreviewState {
    val backgroundImageUrl = backdrop?.takeIf { it.isNotBlank() }
        ?: image.takeIf { it.isNotBlank() }

    return SearchPreviewState(
        displayTitle = displayTitle,
        focusedItem = this,
        sourceCategoryTitle = sourceCategoryTitle,
        isPersonResult = isPersonResult,
        backgroundImageUrl = backgroundImageUrl
    )
}
