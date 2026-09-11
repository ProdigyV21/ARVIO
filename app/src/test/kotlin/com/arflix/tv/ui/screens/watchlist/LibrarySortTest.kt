package com.arflix.tv.ui.screens.watchlist

import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.repository.HomeServerLibrarySort
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
import org.junit.Test

class LibrarySortTest {
    private val newest = HomeServerLibrarySort.RELEASE_DATE_NEWEST
    private val oldest = HomeServerLibrarySort.RELEASE_DATE_OLDEST
    private val items = listOf(
        MediaItem(1, "Old film added today", releaseDate = "1980-12-10", addedAt = 999999),
        MediaItem(2, "New release", releaseDate = "2025-12-10T00:00:00Z"),
        MediaItem(3, "Earlier same year", releaseDate = "2025-02-01"),
        MediaItem(4, "Year only", year = "2020"),
        MediaItem(5, "Unknown"),
        MediaItem(6, "Invalid", releaseDate = "2025-02-30")
    )

    @Test fun `release order uses premiere date not addition time with unknowns last`() {
        assertEquals(listOf(2, 3, 4, 1, 6, 5), sortLibraryItems(items, newest).map { it.id })
        assertEquals(listOf(1, 4, 3, 2, 6, 5), sortLibraryItems(items, oldest).map { it.id })
    }

    @Test fun `bad full dates fall back to a known release year and ties stay deterministic`() {
        val rows = listOf(
            MediaItem(1, "Zulu", year = "2024", releaseDate = "not a date"),
            MediaItem(2, "Alpha", releaseDate = "2024")
        )
        assertEquals(listOf(2, 1), sortLibraryItems(rows, newest).map { it.id })
        assertEquals(listOf(2, 1), sortLibraryItems(rows, oldest).map { it.id })
        assertSame(rows, sortLibraryItems(rows, HomeServerLibrarySort.RECENTLY_ADDED))
    }

    @Test fun `home servers sort release dates before applying pagination`() {
        assertEquals("originallyAvailableAt:desc", newest.plexSort)
        assertEquals("originallyAvailableAt:asc", oldest.plexSort)
        assertEquals("PremiereDate", newest.jellyfinSort)
        assertEquals("PremiereDate", oldest.jellyfinSort)
        assertFalse(newest.ascending)
        assertTrue(oldest.ascending)
        assertEquals("DateCreated", HomeServerLibrarySort.RECENTLY_ADDED.jellyfinSort)
        assertEquals("addedAt:desc", HomeServerLibrarySort.RECENTLY_ADDED.plexSort)
    }
}
