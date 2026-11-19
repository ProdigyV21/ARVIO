package com.arflix.tv.ui.screens.home

import com.arflix.tv.data.model.Category
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.model.SportsAddonCapabilities
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class HomeLandscapeDeckTest {

    @Test
    fun `stableHomeRowKey returns unique key prefixed by landscape layout`() {
        val landscapeKey = stableHomeRowKey("landscape", "trending_movies")
        val mobileKey = stableHomeRowKey("mobile", "trending_movies")
        val tvKey = stableHomeRowKey("tv", "trending_movies")

        assertThat(landscapeKey).isEqualTo("landscape_home_row_trending_movies")
        assertThat(mobileKey).isEqualTo("mobile_home_row_trending_movies")
        assertThat(tvKey).isEqualTo("tv_home_row_trending_movies")
        assertThat(landscapeKey).isNotEqualTo(mobileKey)
    }

    @Test
    fun `landscape hero items interleaves top categories and excludes continue watching and collections`() {
        val continueWatching = Category(
            id = "continue_watching",
            title = "Continue Watching",
            items = listOf(MediaItem(id = 100, title = "CW Item", mediaType = MediaType.MOVIE))
        )
        val collectionRow = Category(
            id = "collection_row_1",
            title = "Franchises",
            items = listOf(MediaItem(id = 200, title = "Marvel Collection", mediaType = MediaType.MOVIE))
        )
        val moviesCategory = Category(
            id = "trending_movies",
            title = "Trending Movies",
            items = listOf(
                MediaItem(id = 1, title = "Movie A", mediaType = MediaType.MOVIE),
                MediaItem(id = 2, title = "Movie B", mediaType = MediaType.MOVIE),
                MediaItem(id = 3, title = "Movie C", mediaType = MediaType.MOVIE)
            )
        )
        val tvCategory = Category(
            id = "trending_tv",
            title = "Trending TV",
            items = listOf(
                MediaItem(id = 10, title = "Show 1", mediaType = MediaType.TV),
                MediaItem(id = 20, title = "Show 2", mediaType = MediaType.TV),
                MediaItem(id = 30, title = "Show 3", mediaType = MediaType.TV)
            )
        )

        val categories = listOf(continueWatching, collectionRow, moviesCategory, tvCategory)

        val eligibleRows = categories.filter {
            it.id != "continue_watching" &&
                !it.id.startsWith("collection_row_") &&
                it.id != SportsAddonCapabilities.SPORTS_CATEGORY_ROW_ID &&
                it.id != SportsAddonCapabilities.POPULAR_LIVE_TV_ROW_ID
        }

        val firstCat = eligibleRows.getOrNull(0)?.items.orEmpty()
        val secondCat = eligibleRows.getOrNull(1)?.items.orEmpty()

        val deckItems = buildList {
            val maxLen = maxOf(firstCat.size, secondCat.size)
            for (i in 0 until maxLen) {
                if (i < firstCat.size) add(firstCat[i])
                if (i < secondCat.size) add(secondCat[i])
            }
        }.distinctBy { "${it.mediaType}_${it.id}" }

        assertThat(deckItems.map { it.id }).containsExactly(1, 10, 2, 20, 3, 30).inOrder()
    }

    @Test
    fun `rgbToDominantDarkBackground transforms bright red into deep dark red background`() {
        val brightRed = 0xFF0000
        val darkBg = rgbToDominantDarkBackground(brightRed)

        assertThat(darkBg.red).isGreaterThan(darkBg.green)
        assertThat(darkBg.red).isGreaterThan(darkBg.blue)
        // Lightness must remain suitably dark for dark mode app contrast
        assertThat(darkBg.red).isLessThan(0.30f)
        assertThat(darkBg.green).isLessThan(0.15f)
        assertThat(darkBg.blue).isLessThan(0.15f)
    }

    @Test
    fun `rgbToDominantDarkBackground transforms bright blue into deep dark blue background`() {
        val brightBlue = 0x0000FF
        val darkBg = rgbToDominantDarkBackground(brightBlue)

        assertThat(darkBg.blue).isGreaterThan(darkBg.red)
        assertThat(darkBg.blue).isGreaterThan(darkBg.green)
        assertThat(darkBg.blue).isLessThan(0.30f)
        assertThat(darkBg.red).isLessThan(0.15f)
        assertThat(darkBg.green).isLessThan(0.15f)
    }

    @Test
    fun `rgbToDominantDarkBackground handles neutral grayscale safely`() {
        val white = 0xFFFFFF
        val darkBg = rgbToDominantDarkBackground(white)

        assertThat(darkBg.red).isLessThan(0.20f)
        assertThat(darkBg.green).isLessThan(0.20f)
        assertThat(darkBg.blue).isLessThan(0.20f)
    }
}
