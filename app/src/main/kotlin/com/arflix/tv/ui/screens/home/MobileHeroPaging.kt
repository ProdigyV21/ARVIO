package com.arflix.tv.ui.screens.home

import com.arflix.tv.data.model.Category
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.SportsAddonCapabilities
import kotlin.math.abs

internal const val MobileHeroPageCount = 10_000

internal fun mobileHeroItems(categories: List<Category>): List<MediaItem> {
    val rows = categories.asSequence().filter {
        it.id != "continue_watching" && !it.id.startsWith("collection_row_") &&
            it.id != SportsAddonCapabilities.SPORTS_CATEGORY_ROW_ID &&
            it.id != SportsAddonCapabilities.POPULAR_LIVE_TV_ROW_ID
    }.map { category ->
        category.items.filter {
            !it.isPlaceholder && it.id > 0 && !SportsAddonCapabilities.isSportsHomeStatus(it.status) &&
                !SportsAddonCapabilities.isSportsLockedStatus(it.status)
        }.take(5)
    }.filter { it.isNotEmpty() }.take(2).toList()
    return buildList {
        for (index in 0 until (rows.maxOfOrNull { it.size } ?: 0)) {
            rows.forEach { row -> row.getOrNull(index)?.let { add(it) } }
        }
    }.distinctBy { it.mediaType to it.id }
}

// Preserve the visible title when asynchronous rows change the circular list's modulus.
internal fun mobileHeroPageForIndex(currentPage: Int, itemCount: Int, itemIndex: Int): Int {
    if (itemCount <= 0) return MobileHeroPageCount / 2
    val index = itemIndex.coerceIn(0, itemCount - 1)
    val base = currentPage - currentPage.mod(itemCount) + index
    return listOf(base - itemCount, base, base + itemCount)
        .filter { it in 0 until MobileHeroPageCount }
        .minByOrNull { abs(it - currentPage) } ?: index
}
