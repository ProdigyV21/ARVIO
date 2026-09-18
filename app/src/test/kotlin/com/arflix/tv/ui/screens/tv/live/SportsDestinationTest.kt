package com.arflix.tv.ui.screens.tv.live

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class SportsDestinationTest {

    private val emptySection = LiveSection("x", "X", emptyList())

    private fun tree(vararg allChildren: LiveCategory) = LiveCategoryTree(
        top = listOf(
            LiveCategory("fav", "Favorites", 3, CategoryIcon.Favorite),
            LiveCategory("all", "All Channels", 233, CategoryIcon.All, children = allChildren.toList()),
        ),
        global = emptySection,
        countries = emptySection,
        adult = emptySection,
    )

    @Test
    fun matchGuideSitsRightBeforeTheSportsChannelsInsideAllChannels() {
        val result = tree(
            LiveCategory("g-4k", "4K | Ultra HD", 12, CategoryIcon.Grid),
            LiveCategory(SPORTS_CHANNEL_CATEGORY, "Sports · Global", 41, CategoryIcon.Sport),
            LiveCategory("g-news", "News", 8, CategoryIcon.Grid),
        ).withSportsDestination()

        assertThat(result.top.map { it.id }).containsExactly("fav", "all").inOrder()
        assertThat(result.byId("all")!!.children.map { it.id })
            .containsExactly("g-4k", SPORTS_GUIDE_CATEGORY, SPORTS_CHANNEL_CATEGORY, "g-news").inOrder()
    }

    @Test
    fun noSportsChannelsMeansNoMatchGuideRow() {
        val result = tree(LiveCategory("g-news", "News", 8, CategoryIcon.Grid)).withSportsDestination()

        assertThat(result.byId("all")!!.children.map { it.id }).containsExactly("g-news")
    }
}
