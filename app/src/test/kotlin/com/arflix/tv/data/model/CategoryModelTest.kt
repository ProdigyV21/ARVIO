package com.arflix.tv.data.model

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class CategoryModelTest {

    @Test
    fun `isPortrait returns false when collection row has empty items list`() {
        val category = Category(
            id = "collection_row_service",
            title = "Services",
            items = emptyList()
        )
        assertFalse(category.isPortrait(globalPosterMode = true))
        assertFalse(category.isPortrait(globalPosterMode = false))
    }

    @Test
    fun `isPortrait returns true when collection row has poster items`() {
        val category = Category(
            id = "collection_row_franchise",
            title = "Franchises",
            items = listOf(
                MediaItem(
                    id = 1,
                    title = "Marvel",
                    collectionTileShape = CollectionTileShape.POSTER
                )
            )
        )
        assertTrue(category.isPortrait(globalPosterMode = true))
        assertTrue(category.isPortrait(globalPosterMode = false))
    }

    @Test
    fun `isPortrait returns false when collection row has landscape items`() {
        val category = Category(
            id = "collection_row_service",
            title = "Services",
            items = listOf(
                MediaItem(
                    id = 2,
                    title = "Netflix",
                    collectionTileShape = CollectionTileShape.LANDSCAPE
                )
            )
        )
        assertFalse(category.isPortrait(globalPosterMode = true))
        assertFalse(category.isPortrait(globalPosterMode = false))
    }

    @Test
    fun `isPortrait respects globalPosterMode for non-collection rows`() {
        val category = Category(
            id = "trending_movies",
            title = "Trending Movies",
            items = listOf(
                MediaItem(
                    id = 3,
                    title = "Some Movie"
                )
            )
        )
        assertTrue(category.isPortrait(globalPosterMode = true))
        assertFalse(category.isPortrait(globalPosterMode = false))
    }
}
