package com.arflix.tv.ui.screens.home

import com.arflix.tv.data.model.Category
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType

internal object ContinueWatchingRowReducer {
    private const val CATEGORY_ID = "continue_watching"
    private const val CATEGORY_TITLE = "Continue Watching"

    fun upsert(categories: List<Category>, fresh: MediaItem): List<Category> {
        val categoryIndex = categories.indexOfFirst { it.id == CATEGORY_ID }
        val existingCategory = categories.getOrNull(categoryIndex)
        val existing = existingCategory?.items?.firstOrNull {
            it.id == fresh.id && it.mediaType == fresh.mediaType
        }
        val episodeChanged = existing?.nextEpisode?.let { previous ->
            val next = fresh.nextEpisode
            next != null && (
                previous.seasonNumber != next.seasonNumber ||
                    previous.episodeNumber != next.episodeNumber
                )
        } == true
        val merged = existing?.copy(
            title = fresh.title.ifBlank { existing.title },
            subtitle = fresh.subtitle,
            year = fresh.year.ifBlank { existing.year },
            duration = fresh.duration.ifBlank { existing.duration },
            image = fresh.image.ifBlank { existing.image },
            backdrop = fresh.backdrop ?: existing.backdrop,
            episodeStill = fresh.episodeStill ?: existing.episodeStill.takeUnless { episodeChanged },
            progress = fresh.progress,
            nextEpisode = fresh.nextEpisode,
            timeRemainingMinutes = fresh.timeRemainingMinutes,
            showPlaybackProgress = fresh.showPlaybackProgress
        ) ?: fresh

        val items = listOf(merged) + existingCategory.orEmptyItems().filterNot {
            it.id == fresh.id && it.mediaType == fresh.mediaType
        }
        val updatedCategory = (existingCategory ?: Category(CATEGORY_ID, CATEGORY_TITLE, emptyList()))
            .copy(items = items)
        val updated = categories.toMutableList()
        if (categoryIndex >= 0) {
            updated[categoryIndex] = updatedCategory
        } else {
            updated.add(0, updatedCategory)
        }
        return updated
    }

    fun remove(
        categories: List<Category>,
        mediaType: MediaType?,
        tmdbId: Int,
        season: Int?,
        episode: Int?
    ): List<Category> {
        return categories.mapNotNull { category ->
            if (category.id != CATEGORY_ID) return@mapNotNull category
            val remaining = category.items.filterNot { item ->
                if (item.id != tmdbId || (mediaType != null && item.mediaType != mediaType)) {
                    return@filterNot false
                }
                if (season == null && episode == null) return@filterNot true
                val pointer = item.nextEpisode ?: return@filterNot true
                (season == null || pointer.seasonNumber == season) &&
                    (episode == null || pointer.episodeNumber == episode)
            }
            category.copy(items = remaining).takeIf { remaining.isNotEmpty() }
        }
    }

    private fun Category?.orEmptyItems(): List<MediaItem> = this?.items.orEmpty()
}
