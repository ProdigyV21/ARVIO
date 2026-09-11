package com.arflix.tv.ui.screens.watchlist

import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.repository.HomeServerLibrarySort
import java.time.LocalDate

private fun MediaItem.libraryReleaseDate(): LocalDate? {
    val fullDate = releaseDate?.take(10)?.let { runCatching { LocalDate.parse(it) }.getOrNull() }
    if (fullDate != null && fullDate.year > 0) return fullDate
    val releaseYear = year.takeIf { it.matches(Regex("\\d{4}")) }?.toIntOrNull()
        ?: releaseDate?.takeIf { it.matches(Regex("\\d{4}")) }?.toIntOrNull()
    return releaseYear?.takeIf { it > 0 }?.let { LocalDate.of(it, 1, 1) }
}

internal fun sortLibraryItems(items: List<MediaItem>, sort: HomeServerLibrarySort): List<MediaItem> {
    return when (sort) {
        // Preserve the tracker/source's existing explicit ordering by default.
        HomeServerLibrarySort.RECENTLY_ADDED -> items
        HomeServerLibrarySort.TITLE -> items.sortedBy { it.title.lowercase() }
        HomeServerLibrarySort.RATING -> items.sortedByDescending { it.rating.toDoubleOrNull() ?: 0.0 }
        HomeServerLibrarySort.RELEASE_DATE_NEWEST, HomeServerLibrarySort.RELEASE_DATE_OLDEST -> {
            val dates = items.associateWith { it.libraryReleaseDate() }
            items.sortedWith { a, b ->
                val left = dates[a]
                val right = dates[b]
                when {
                    left == null && right != null -> 1
                    left != null && right == null -> -1
                    else -> {
                        val order = if (left != null && right != null) left.compareTo(right) else 0
                        val directed = if (sort == HomeServerLibrarySort.RELEASE_DATE_NEWEST) -order else order
                        directed.takeIf { it != 0 } ?: a.title.compareTo(b.title, ignoreCase = true).takeIf { it != 0 }
                            ?: a.id.compareTo(b.id)
                    }
                }
            }
        }
    }
}
