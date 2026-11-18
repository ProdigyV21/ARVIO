package com.arflix.tv.ui.screens.home

import androidx.compose.ui.unit.dp
import com.arflix.tv.data.model.Category
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class NetflixSharedArtTest {
    private val row = Category("trending", "Trending", emptyList())

    private fun item(backdrop: String? = "https://image.example/b.jpg", still: String? = null, status: String? = null) =
        MediaItem(1, "Title", mediaType = MediaType.TV, image = "https://image.example/p.jpg",
            backdrop = backdrop, episodeStill = still, status = status)

    @Test fun `a title with a backdrop shows it in every card shape`() {
        assertTrue(sharesWideArt(row, item(), watching = false))
        assertEquals("https://image.example/b.jpg", netflixWideArt(item(), watching = false))
    }

    @Test fun `continue watching widens the episode still, other rows ignore it`() {
        val watching = item(still = "https://image.example/s.jpg")
        assertEquals("https://image.example/s.jpg", netflixWideArt(watching, watching = true))
        assertEquals("https://image.example/b.jpg", netflixWideArt(watching, watching = false))
        assertTrue(sharesWideArt(row, item(backdrop = null, still = "https://image.example/s.jpg"), watching = true))
    }

    @Test fun `titles without a wide picture, channels and collection tiles keep their own art`() {
        assertNull(netflixWideArt(item(backdrop = " "), watching = false))
        assertFalse(sharesWideArt(row, item(backdrop = null), watching = false))
        assertFalse(sharesWideArt(row, item(status = "iptv:channel"), watching = false))
        assertFalse(sharesWideArt(Category("collection_row_services", "Services", emptyList()), item(), watching = false))
    }

    @Test fun `a portrait card and its expanded card decode the same wide box`() {
        val height = 270.dp
        val expanded = netflixWideBox(height * (16f / 9f), height)
        assertEquals(expanded, netflixWideBox(height * (2f / 3f), height))
        assertEquals(height, expanded.second)
        val resting = netflixWideBox(150.dp, 225.dp)
        assertEquals(400.dp, resting.first)
        assertEquals(225.dp, resting.second)
    }
}
