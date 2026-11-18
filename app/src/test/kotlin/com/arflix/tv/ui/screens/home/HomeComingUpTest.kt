package com.arflix.tv.ui.screens.home

import com.arflix.tv.data.model.CalendarRelease
import com.arflix.tv.data.model.CalendarReleaseKind
import com.arflix.tv.data.model.Category
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import java.time.Instant
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertSame
import org.junit.Test

class HomeComingUpTest {
    private val now = Instant.parse("2026-11-28T12:00:00Z")
    private val today = LocalDate.parse("2026-11-28")

    @Test fun `only the next future episode per show appears across month boundaries`() {
        val sameDay = release(3, 0).copy(releaseInstant = now.plusSeconds(3600))
        val items = upcomingHomeItems(
            listOf(release(1, 4), release(1, 3), release(2, -1), release(4, 0),
                release(5, 15), release(6, 1).copy(kind = CalendarReleaseKind.DIGITAL),
                sameDay, release(7, 0).copy(releaseInstant = now.minusSeconds(1))),
            today, now, DateTimeFormatter.ISO_LOCAL_DATE
        )
        assertEquals(listOf(3, 1), items.map { it.id })
        assertEquals("2026-12-01", items[1].releaseDate)
        assertEquals(0, items[1].progress)
        assertFalse(items[1].showPlaybackProgress)
        assertNull(items[1].nextEpisode)
    }

    @Test fun `the row stays separate from continue watching and can be removed`() {
        val watching = Category("continue_watching", "Continue Watching", emptyList())
        val saved = Category(HOME_WATCHLIST_ROW_ID, "Watchlist", emptyList())
        val trending = Category("trending", "Trending", emptyList())
        val original = listOf(watching, saved, trending)
        assertSame(original, withHomeComingUpRow(original, emptyList()))
        val shown = withHomeComingUpRow(original, listOf(release(1, 2).media))
        assertEquals(listOf("continue_watching", HOME_WATCHLIST_ROW_ID, HOME_COMING_UP_ROW_ID, "trending"), shown.map { it.id })
        assertEquals(original, withHomeComingUpRow(shown, emptyList()))
    }

    private fun release(id: Int, offset: Long) = CalendarRelease(
        id = "$id-$offset", media = MediaItem(id, "Show $id", progress = 19, mediaType = MediaType.TV),
        date = today.plusDays(offset), kind = CalendarReleaseKind.EPISODE
    )
}
