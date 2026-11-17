package com.arflix.tv.ui.screens.home

import com.arflix.tv.data.model.Category
import com.arflix.tv.data.model.CatalogConfig
import com.arflix.tv.data.repository.MediaRepository
import com.arflix.tv.data.repository.wetrakr.WeTrakrLists
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

internal fun isWeTrakrRecommendationRow(id: String): Boolean = WeTrakrLists.recommendationType(id) != null

// Optional account rows appear only after a non-empty result, never as startup skeletons.
internal fun homeCatalogHasSkeleton(catalog: CatalogConfig): Boolean = !isWeTrakrRecommendationRow(catalog.id)

internal suspend fun loadWeTrakrRecommendationRows(
    catalogs: List<CatalogConfig>,
    loadPage: suspend (String, Int) -> MediaRepository.CategoryPageResult
): List<Category> = coroutineScope {
    catalogs.filter { isWeTrakrRecommendationRow(it.id) }.map { catalog ->
        async {
            try {
                val page = loadPage(catalog.id, 1)
                page.items.takeIf { it.isNotEmpty() }?.let { Category(catalog.id, catalog.title, it) }
            } catch (error: CancellationException) {
                throw error
            } catch (_: Exception) {
                null
            }
        }
    }.awaitAll().filterNotNull()
}

internal fun removeEmptyWeTrakrRecommendationRow(categories: List<Category>, id: String): List<Category> =
    if (isWeTrakrRecommendationRow(id)) categories.filterNot { it.id == id } else categories

// A slow initial refresh must not replace pages appended while enrichment was running.
internal fun preserveExtendedCatalogRows(incoming: List<Category>, current: List<Category>): List<Category> {
    val currentById = current.associateBy { it.id }
    val merged = incoming.map { row ->
        val visible = currentById[row.id]
        if (row.id != "continue_watching" && !isWeTrakrRecommendationRow(row.id) &&
            visible != null && visible.items.size > row.items.size &&
            row.items.indices.all { index ->
                val a = row.items[index]
                val b = visible.items[index]
                a.id == b.id && a.mediaType == b.mediaType
            }
        ) visible else row
    }

    // Continue Watching is published by its own coroutine rather than by the
    // catalogue load, so it is simply absent from `incoming`. Mapping over
    // `incoming` alone therefore deleted the row whenever a catalogue refresh
    // landed after it — reliably so on trackers that resolve the row in one
    // fast call, where it won the race almost every time and Home came up with
    // no Continue Watching at all.
    val carriedContinueWatching = currentById[CONTINUE_WATCHING_ROW_ID]
        ?.takeIf { it.items.isNotEmpty() && incoming.none { row -> row.id == CONTINUE_WATCHING_ROW_ID } }
        ?: return merged
    return listOf(carriedContinueWatching) + merged
}

private const val CONTINUE_WATCHING_ROW_ID = "continue_watching"
