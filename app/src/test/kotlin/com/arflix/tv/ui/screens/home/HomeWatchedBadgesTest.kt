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
    fun `history is indexed into started show ids, keys without a tmdb id are skipped`() {
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

    @Test
    fun `hero keeps its hydrated details and only takes over the tick`() {
        val hero = movie(7).copy(duration = "2h 5m", imdbRating = "8.1", primaryNetworkLogo = "logo")
        val rows = applyWatchedBadges(
            listOf(Category(id = "trending", title = "Trending", items = listOf(movie(7)))),
            watchedMovies = setOf(7),
            startedShows = emptySet()
        )

        val updated = heroWithWatchedBadge(hero, rows)!!

        assertThat(updated.isWatched).isTrue()
        assertThat(updated.duration).isEqualTo("2h 5m")
        assertThat(updated.imdbRating).isEqualTo("8.1")
        assertThat(updated.primaryNetworkLogo).isEqualTo("logo")
        assertThat(heroWithWatchedBadge(updated, rows)).isSameInstanceAs(updated)
        val elsewhere = movie(99)
        assertThat(heroWithWatchedBadge(elsewhere, rows)).isSameInstanceAs(elsewhere)
    }

    @Test
    fun `fresh rows are marked even right after a pass, the marked rows themselves are throttled`() {
        val marked = listOf(Category(id = "trending", title = "Trending", items = listOf(movie(7, watched = true))))
        // A catalog load a few seconds later publishes the same titles again, unmarked.
        val fresh = listOf(Category(id = "trending", title = "Trending", items = listOf(movie(7))))

        assertThat(watchedBadgesPassIsRedundant(fresh, marked, force = false, sinceLastPassMs = 3_000L, throttleMs = 90_000L))
            .isFalse()
        assertThat(watchedBadgesPassIsRedundant(marked, marked, force = false, sinceLastPassMs = 3_000L, throttleMs = 90_000L))
            .isTrue()
        assertThat(watchedBadgesPassIsRedundant(marked, marked, force = false, sinceLastPassMs = 91_000L, throttleMs = 90_000L))
            .isFalse()
        // Back from Details: same rows, but the history may have changed.
        assertThat(watchedBadgesPassIsRedundant(marked, marked, force = true, sinceLastPassMs = 3_000L, throttleMs = 90_000L))
            .isFalse()
        assertThat(watchedBadgesPassIsRedundant(fresh, null, force = false, sinceLastPassMs = 0L, throttleMs = 90_000L))
            .isFalse()
    }

    @Test
    fun `back from details the tick pass is quick, the first pass keeps the startup pause`() {
        // Back from Details after a pass has run: short debounce only.
        assertThat(watchedBadgesPassDelayMs(quickRequested = true, hadPass = true, isLowRamDevice = true)).isEqualTo(300L)
        // Launch also reports a resume, but no pass has run yet: keep the startup pause.
        assertThat(watchedBadgesPassDelayMs(quickRequested = true, hadPass = false, isLowRamDevice = true)).isEqualTo(3_000L)
        assertThat(watchedBadgesPassDelayMs(quickRequested = true, hadPass = false, isLowRamDevice = false)).isEqualTo(1_800L)
        // Newly published rows keep the regular debounce.
        assertThat(watchedBadgesPassDelayMs(quickRequested = false, hadPass = true, isLowRamDevice = true)).isEqualTo(3_000L)
        assertThat(watchedBadgesPassDelayMs(quickRequested = false, hadPass = true, isLowRamDevice = false)).isEqualTo(1_800L)
    }

    @Test
    fun `re-published rows take over the known ticks and the state stays the same when nothing changes`() {
        // A catalog load publishes the same titles again, unmarked; the last pass found film 7 and show 2.
        val state = HomeUiState(
            categories = listOf(Category(id = "trending", title = "Trending", items = listOf(movie(7), show(2), movie(8)))),
            heroItem = movie(7).copy(imdbRating = "8.1")
        )

        val marked = state.withWatchedBadges(watchedMovies = setOf(7), startedShows = setOf(2))

        assertThat(marked.categories.single().items.map { it.isWatched }).containsExactly(true, true, false).inOrder()
        assertThat(marked.heroItem?.isWatched).isTrue()
        assertThat(marked.heroItem?.imdbRating).isEqualTo("8.1")
        assertThat(marked.withWatchedBadges(setOf(7), setOf(2))).isSameInstanceAs(marked)
    }
}
