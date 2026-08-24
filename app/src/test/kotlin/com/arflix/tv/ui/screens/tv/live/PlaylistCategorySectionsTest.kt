package com.arflix.tv.ui.screens.tv.live

import com.arflix.tv.data.model.IptvChannel
import com.arflix.tv.data.repository.IptvConfig
import com.arflix.tv.data.repository.IptvPlaylistEntry
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class PlaylistCategorySectionsTest {

    @Test
    fun sectionsFollowPlaylistOrderAndDoNotMixCategories() {
        val config = IptvConfig(
            playlists = listOf(
                IptvPlaylistEntry(id = "second", name = "Second playlist", m3uUrl = "https://second.test/list.m3u"),
                IptvPlaylistEntry(id = "first", name = "First playlist", m3uUrl = "https://first.test/list.m3u"),
            )
        )
        val categories = listOf(
            LiveCategory("first:a", "Movies", 8, CategoryIcon.Movie, playlistId = "first"),
            LiveCategory("second:a", "Sports", 10, CategoryIcon.Sport, playlistId = "second"),
            LiveCategory("first:b", "Series", 12, CategoryIcon.Grid, playlistId = "first"),
        )

        val sections = buildPlaylistCategorySections(config, categories)

        assertThat(sections.map { it.id }).containsExactly("second", "first").inOrder()
        assertThat(sections[0].categories.map { it.id }).containsExactly("second:a")
        assertThat(sections[1].categories.map { it.id }).containsExactly("first:a", "first:b").inOrder()
    }

    @Test
    fun providerFiltersPreserveSavedPlaylistReorder() {
        val config = IptvConfig(
            playlists = listOf(
                IptvPlaylistEntry(id = "second", name = "Second playlist", m3uUrl = "https://second.test/list.m3u"),
                IptvPlaylistEntry(id = "first", name = "First playlist", m3uUrl = "https://first.test/list.m3u"),
            )
        )
        val channels = listOf(
            IptvChannel("first:1", "First channel", "https://first.test/1", "Movies").enrich(1),
            IptvChannel("second:1", "Second channel", "https://second.test/1", "News").enrich(2),
        )

        val filters = buildTvProviderFilters(config, channels)

        assertThat(filters.map { it.id }).containsExactly("all", "second", "first").inOrder()
    }

    @Test
    fun pagedCountsKeepAllConfiguredPlaylistsWhenLoadedWindowContainsOnlyOne() {
        val config = IptvConfig(
            playlists = listOf(
                IptvPlaylistEntry(id = "second", name = "Second playlist", m3uUrl = "https://second.test/list.m3u"),
                IptvPlaylistEntry(id = "first", name = "First playlist", m3uUrl = "https://first.test/list.m3u"),
            )
        )
        val loadedWindow = listOf(
            IptvChannel("first:1", "First channel", "https://first.test/1", "Movies").enrich(1),
        )
        val pagedCounts = listOf(
            Triple("first", "Movies", 20),
            Triple("second", "News", 10),
        )

        val filters = buildTvProviderFilters(config, loadedWindow, pagedCounts)

        assertThat(filters.map { it.id }).containsExactly("all", "second", "first").inOrder()
        assertThat(filters.map { it.count }).containsExactly(30, 10, 20).inOrder()
    }
}
