package com.arflix.tv.util

import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import com.arflix.tv.ui.screens.home.HOME_MOBILE_LANDSCAPE_CARD_WIDTH_DP
import com.arflix.tv.ui.screens.home.HOME_TV_LANDSCAPE_CARD_WIDTH_DP
import com.arflix.tv.ui.screens.home.landscapeCardPixelSize
import com.arflix.tv.util.TmdbImageSizing.Kind.BACKDROP
import com.arflix.tv.util.TmdbImageSizing.Kind.POSTER
import org.junit.Assert.assertEquals
import org.junit.Test

class TmdbImageSizingTest {

    private val backdrop = "https://image.tmdb.org/t/p/original/kXfqcdQKsToO0OUXHcrrNCHDBzO.jpg"
    private val poster = "https://image.tmdb.org/t/p/w780/q6y0Go1tsGEsmtFryDOJo3dEmqu.jpg"

    private fun size(url: String) = url.substringAfter("/t/p/").substringBefore('/')

    // === width -> size ===

    @Test
    fun `backdrop gets the smallest official width that covers the slot`() {
        val expected = mapOf(
            1 to "w300",
            280 to "w300", // TV card at 720p density
            300 to "w300",
            301 to "w780",
            420 to "w780", // TV card at 1080p density
            551 to "w780", // phone card
            780 to "w780",
            781 to "w1280",
            1280 to "w1280",
        )
        expected.forEach { (widthPx, size) ->
            assertEquals("width $widthPx", size, size(TmdbImageSizing.forSlot(backdrop, widthPx, 0, BACKDROP)))
        }
    }

    @Test
    fun `original stays for slots wider than 1280 px`() {
        assertEquals(backdrop, TmdbImageSizing.forSlot(backdrop, 1281, 0, BACKDROP))
        assertEquals(backdrop, TmdbImageSizing.forSlot(backdrop, 1920, 1080, BACKDROP)) // TV hero
    }

    @Test
    fun `a tall crop slot needs the width the image is scaled to`() {
        // Portrait phone hero: 743 x 990 px card showing a 16:9 backdrop -> 1760 px wide after the crop.
        assertEquals(backdrop, TmdbImageSizing.forSlot(backdrop, 743, 990, BACKDROP))
        // A 16:9 card needs only its own width.
        assertEquals("w780", size(TmdbImageSizing.forSlot(backdrop, 551, 309, BACKDROP)))
    }

    @Test
    fun `poster uses the poster sizes`() {
        assertEquals("w185", size(TmdbImageSizing.forSlot(poster, 184, 276, POSTER)))
        assertEquals("w342", size(TmdbImageSizing.forSlot(poster, 289, 433, POSTER))) // 105 dp at 2.75
        assertEquals("w500", size(TmdbImageSizing.forSlot(poster, 420, 630, POSTER)))
    }

    @Test
    fun `only the size segment changes`() {
        assertEquals(
            "https://image.tmdb.org/t/p/w780/kXfqcdQKsToO0OUXHcrrNCHDBzO.jpg",
            TmdbImageSizing.forSlot(backdrop, 420, 236, BACKDROP)
        )
        assertEquals(
            "http://image.tmdb.org/t/p/w300/a.jpg",
            TmdbImageSizing.forSlot("http://image.tmdb.org/t/p/original/a.jpg", 200, 0, BACKDROP)
        )
    }

    // === which kind of image a card shows ===

    @Test
    fun `a portrait card without a poster is sized for the backdrop it shows`() {
        // MediaRepository.toMediaItem: image = poster ?: BACKDROP_BASE + backdropPath
        val image = "https://image.tmdb.org/t/p/w1280/kXfqcdQKsToO0OUXHcrrNCHDBzO.jpg"
        val kind = TmdbImageSizing.cardArtworkKind(image, backdrop, landscapeSlot = false)
        assertEquals(BACKDROP, kind)
        // 105 dp poster slot on a 1080p TV: 210 x 315 px, a 16:9 crop needs 560 px of width.
        assertEquals("w780", size(TmdbImageSizing.forSlot(image, 210, 315, kind)))
    }

    @Test
    fun `a portrait card with a poster uses the poster sizes`() {
        assertEquals(POSTER, TmdbImageSizing.cardArtworkKind(poster, backdrop, landscapeSlot = false))
        assertEquals(POSTER, TmdbImageSizing.cardArtworkKind(poster, null, landscapeSlot = false))
    }

    @Test
    fun `a landscape card is always sized as a backdrop`() {
        assertEquals(BACKDROP, TmdbImageSizing.cardArtworkKind(poster, null, landscapeSlot = true))
        assertEquals(BACKDROP, TmdbImageSizing.cardArtworkKind(backdrop, backdrop, landscapeSlot = true))
    }

    // === never larger, never foreign ===

    @Test
    fun `a stored url is never rewritten to a larger size`() {
        assertEquals(poster, TmdbImageSizing.forSlot(poster, 1000, 0, POSTER))
        assertEquals(poster, TmdbImageSizing.forSlot(poster, 700, 0, POSTER))
        val still = "https://image.tmdb.org/t/p/w300/still.jpg"
        assertEquals(still, TmdbImageSizing.forSlot(still, 551, 309, BACKDROP))
    }

    @Test
    fun `non TMDB and unusual urls stay untouched`() {
        listOf(
            "https://images.metahub.space/poster/medium/tt0111161/img",
            "https://plex.example/library/metadata/1/thumb/2?X-Plex-Token=x",
            "https://image.tmdb.org/t/p/w1920_and_h800_multi_faces/a.jpg",
            "https://image.tmdb.org/t/p/h632/a.jpg",
            "https://example.org/t/p/original/a.jpg",
            "https://cdn.example/collections/netflix.gif",
            "",
        ).forEach { url ->
            assertEquals(url, TmdbImageSizing.forSlot(url, 420, 236, BACKDROP))
        }
    }

    @Test
    fun `a slot without a width keeps the url`() {
        assertEquals(backdrop, TmdbImageSizing.forSlot(backdrop, 0, 0, BACKDROP))
    }

    // === preload and card agree ===

    @Test
    fun `home backdrop preload asks for the same url and cache key as the card`() {
        val densities = listOf(1.0f, 1.3312501f, 1.5f, 2.0f, 2.625f, 2.75f, 3.0f, 3.5f, 4.0f)
        listOf(HOME_TV_LANDSCAPE_CARD_WIDTH_DP, HOME_MOBILE_LANDSCAPE_CARD_WIDTH_DP).forEach { widthDp ->
            densities.forEach { density ->
                // MediaCard: width.roundToPx(), height = (widthPx / aspect).toInt()
                val cardWidthPx = with(Density(density)) { widthDp.dp.roundToPx() }
                val cardHeightPx = (cardWidthPx / (16f / 9f)).toInt().coerceAtLeast(1)
                val cardUrl = TmdbImageSizing.forSlot(backdrop, cardWidthPx, cardHeightPx, BACKDROP)

                val (preloadWidthPx, preloadHeightPx) = landscapeCardPixelSize(widthDp, density)
                val preloadUrl = TmdbImageSizing.forSlot(backdrop, preloadWidthPx, preloadHeightPx, BACKDROP)

                assertEquals(
                    "$widthDp dp at density $density",
                    "$cardUrl|${cardWidthPx}x$cardHeightPx",
                    "$preloadUrl|${preloadWidthPx}x$preloadHeightPx"
                )
            }
        }
    }
}
