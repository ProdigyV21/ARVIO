package com.arflix.tv.ui.screens.search

import com.arflix.tv.data.model.Category
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class SearchPreviewStateTest {

    @Test
    fun `person result preview uses person name and focused known for item`() {
        val knownFor = MediaItem(
            id = 10,
            title = "Arrival",
            mediaType = MediaType.MOVIE,
            image = "poster.jpg",
            backdrop = "backdrop.jpg"
        )
        val categories = listOf(
            Category(
                id = "person_42",
                title = "Jessica Magnusson",
                items = listOf(knownFor)
            )
        )

        val preview = resolveSearchPreview(
            categories = categories,
            currentRowIndex = 0,
            currentItemIndex = 0
        )

        assertThat(preview?.displayTitle).isEqualTo("Jessica Magnusson")
        assertThat(preview?.isPersonResult).isTrue()
        assertThat(preview?.focusedItem).isEqualTo(knownFor)
        assertThat(preview?.backgroundImageUrl).isEqualTo("backdrop.jpg")
    }

    @Test
    fun `media preview uses focused item title and image when backdrop is missing`() {
        val movie = MediaItem(
            id = 20,
            title = "Blade Runner 2049",
            mediaType = MediaType.MOVIE,
            image = "poster.jpg",
            backdrop = ""
        )

        val preview = resolveSearchPreview(
            categories = listOf(Category("movies", "Movies", listOf(movie))),
            currentRowIndex = 0,
            currentItemIndex = 0
        )

        assertThat(preview?.displayTitle).isEqualTo("Blade Runner 2049")
        assertThat(preview?.isPersonResult).isFalse()
        assertThat(preview?.backgroundImageUrl).isEqualTo("poster.jpg")
    }
}
