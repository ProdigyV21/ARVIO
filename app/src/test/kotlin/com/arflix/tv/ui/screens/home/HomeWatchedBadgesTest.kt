package com.arflix.tv.ui.screens.home

import com.arflix.tv.data.model.Category
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class HomeWatchedBadgesTest {

    private fun movie(id: Int, watched: Boolean = false) =
        MediaItem(id = id, title = "Movie $id", mediaType = MediaType.MOVIE, isWatched = watched)

    private fun show(id: Int, watched: Boolean = false) =
        MediaItem(id = id, title = "Show $id", mediaType = MediaType.TV, isWatched = watched)

    @Test
    fun `large history is indexed into started show ids`() {
        val history = (1..20_000).mapTo(HashSet()) { "show_tmdb:${it / 10 + 1}:1:$it" } +
            setOf("show_trakt:5000:1:1", "show_tmdb:5000", "show_tmdb:bad:1:1")

        val started = startedShowIds(history)

        assertThat(started).contains(2)
        assertThat(started).contains(2001)
        assertThat(started).doesNotContain(5000)
        assertThat(started).hasSize(2001)
    }

    @Test
    fun `films and started shows are marked, continue watching is left alone`() {
        val continueWatching = Category(
            id = "continue_watching",
            title = "Continue Watching",
            items = listOf(movie(7), show(2))
        )
        val trending = Category(
            id = "trending",
            title = "Trending",
            items = listOf(movie(7), show(2), show(5000), movie(8))
        )

        val result = applyWatchedBadges(
            categories = listOf(continueWatching, trending),
            watchedMovies = setOf(7),
            startedShows = setOf(2)
        )

        assertThat(result[0]).isSameInstanceAs(continueWatching)
        assertThat(result[1].items.map { it.isWatched }).containsExactly(true, true, false, false).inOrder()
    }

    @Test
    fun `a tick disappears when the title leaves the watched history`() {
        val row = Category(id = "trending", title = "Trending", items = listOf(movie(7, watched = true), show(2, watched = true)))

        val result = applyWatchedBadges(listOf(row), watchedMovies = emptySet(), startedShows = emptySet())

        assertThat(result.single().items.map { it.isWatched }).containsExactly(false, false).inOrder()
    }

    @Test
    fun `unchanged rows come back as the same instances`() {
        val marked = Category(id = "a", title = "A", items = listOf(movie(7, watched = true)))
        val unmarked = Category(id = "b", title = "B", items = listOf(show(3)))
        val categories = listOf(marked, unmarked)

        assertThat(applyWatchedBadges(categories, setOf(7), emptySet())).isSameInstanceAs(categories)

        val changed = applyWatchedBadges(categories, setOf(7), setOf(3))
        assertThat(changed[0]).isSameInstanceAs(marked)
        assertThat(changed[1].items.single().isWatched).isTrue()
    }
}
