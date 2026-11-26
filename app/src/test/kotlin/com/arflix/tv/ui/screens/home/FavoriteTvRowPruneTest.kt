package com.arflix.tv.ui.screens.home

import com.arflix.tv.data.model.Category
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import org.junit.Assert.assertEquals
import org.junit.Assert.assertSame
import org.junit.Test

class FavoriteTvRowPruneTest {
    private fun channel(id: String) = MediaItem(
        id = id.hashCode() and 0x7FFFFFFF,
        title = id,
        mediaType = MediaType.TV,
        status = "${HomeViewModel.IPTV_STATUS_PREFIX}$id",
    )

    private fun favoriteRow(vararg ids: String) =
        Category(HomeViewModel.FAVORITE_TV_CATEGORY_ID, "Favorite TV", ids.map(::channel))

    private val other = Category("trending", "Trending", listOf(MediaItem(id = 1, title = "Film")))

    @Test fun aRemovedFavoriteLeavesTheRow() {
        val result = pruneFavoriteTvRow(listOf(other, favoriteRow("a", "b", "c")), listOf("a", "c"))
        assertEquals(listOf("a", "c"), result[1].items.map { it.title })
        assertSame(other, result[0])
    }

    @Test fun removingTheLastFavoriteRemovesTheRow() {
        val result = pruneFavoriteTvRow(listOf(other, favoriteRow("a")), emptyList())
        assertEquals(listOf("trending"), result.map { it.id })
    }

    @Test fun cardsFollowTheStoredOrder() {
        val result = pruneFavoriteTvRow(listOf(favoriteRow("a", "b")), listOf("b", "a"))
        assertEquals(listOf("b", "a"), result.single().items.map { it.title })
    }

    @Test fun anUpToDateRowIsLeftAlone() {
        val categories = listOf(other, favoriteRow("a", "b"))
        assertSame(categories, pruneFavoriteTvRow(categories, listOf("a", "b", "new")))
    }

    @Test fun noRowMeansNothingChanges() {
        val categories = listOf(other)
        assertSame(categories, pruneFavoriteTvRow(categories, emptyList()))
    }
}
