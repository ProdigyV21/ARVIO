package com.arflix.tv.ui.screens.tv.live

import com.arflix.tv.data.model.IptvChannel
import com.arflix.tv.data.repository.IptvConfig
import com.arflix.tv.data.repository.IptvPlaylistEntry
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class PlaylistCategorySectionsTest {

    @Test fun configuredSourcesRemainVisibleWhileOnlySomeHaveLoaded() {
        val config = IptvConfig(playlists = (1..10).map {
            IptvPlaylistEntry("list_$it", "Playlist $it", "https://example.invalid/$it.m3u")
        })
        val channels = listOf(IptvChannel("list_2:1", "Second", "https://example.invalid/2", "News").enrich(1))
        val filters = buildTvProviderFilters(config, channels, listOf(Triple("list_1", "News", 20)))
        assertThat(filters.map { it.id }).containsExactlyElementsIn(listOf("all") + config.playlists.map { it.id }).inOrder()
        assertThat(filters[0].count).isEqualTo(21)
        assertThat(filters[2].count).isEqualTo(1)
        assertThat(filters[10].count).isEqualTo(0)
        assertThat(buildTvProviderFilters(config, emptyList())).hasSize(11)
    }

    @Test fun disabledAndVodOnlySourcesStayOutOfLivePicker() {
        val config = IptvConfig(playlists = (1..4).map {
            IptvPlaylistEntry("list_$it", "Playlist $it", "https://example.invalid/$it.m3u",
                enabled = it != 3, importLiveTv = it != 4)
        })
        assertThat(buildTvProviderFilters(config, emptyList()).map { it.id })
            .containsExactly("all", "list_1", "list_2").inOrder()
    }

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
    fun hybridPlaylistAndStalkerKeepsUnmatchedCategoriesVisible() {
        val config = IptvConfig(
            playlists = listOf(
                IptvPlaylistEntry(id = "m3u", name = "M3U playlist", m3uUrl = "https://m3u.test/list.m3u"),
            )
        )
        val categories = listOf(
            LiveCategory("m3u:movies", "Movies", 8, CategoryIcon.Movie, playlistId = "m3u"),
            LiveCategory("stalker:news", "News", 6, CategoryIcon.Grid, playlistId = "stalker"),
            LiveCategory("stalker:sports", "Sports", 4, CategoryIcon.Sport, playlistId = "stalker"),
        )

        val sections = buildPlaylistCategorySections(config, categories)

        assertThat(sections.map { it.id }).containsExactly("m3u", "source:stalker").inOrder()
        assertThat(sections[1].label).isEqualTo("Stalker")
        assertThat(sections[1].categories.map { it.id })
            .containsExactly("stalker:news", "stalker:sports")
            .inOrder()
    }

    @Test
    fun multiPortalStalkerSectionsUseTheConfiguredPortalNameNotTheRawId() {
        val config = IptvConfig(
            stalkerPortals = listOf(
                com.arflix.tv.data.repository.StalkerPortalEntry(
                    id = "stalker1",
                    name = "Home Portal",
                    portalUrl = "http://portal1.test",
                    macAddress = "00:1A:79:AA:BB:01",
                ),
                com.arflix.tv.data.repository.StalkerPortalEntry(
                    id = "stalker2",
                    name = "Backup Portal",
                    portalUrl = "http://portal2.test",
                    macAddress = "00:1A:79:AA:BB:02",
                ),
            )
        )
        val categories = listOf(
            LiveCategory("stalker:stalker1:news", "News", 6, CategoryIcon.Grid, playlistId = "stalker1"),
            LiveCategory("stalker:stalker2:sports", "Sports", 4, CategoryIcon.Sport, playlistId = "stalker2"),
        )

        val sections = buildPlaylistCategorySections(config, categories)

        assertThat(sections.map { it.id }).containsExactly("source:stalker1", "source:stalker2").inOrder()
        assertThat(sections.map { it.label }).containsExactly("Home Portal", "Backup Portal").inOrder()
    }

    @Test
    fun singlePlaylistUsesLegacyFlatCategoriesWithoutCollapsedParent() {
        val config = IptvConfig(
            playlists = listOf(
                IptvPlaylistEntry(id = "only", name = "Only playlist", m3uUrl = "https://only.test/list.m3u"),
            )
        )
        val categories = listOf(
            LiveCategory("only:movies", "Movies", 8, CategoryIcon.Movie, playlistId = "only"),
            LiveCategory("only:series", "Series", 12, CategoryIcon.Grid, playlistId = "only"),
        )

        assertThat(buildPlaylistCategorySections(config, categories)).isEmpty()
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

    @Test
    fun sectionsAreEmptyWhenAllGroupsAreHidden() {
        val config = IptvConfig(
            playlists = listOf(
                IptvPlaylistEntry(id = "first", name = "First playlist", m3uUrl = "https://first.test/list.m3u"),
                IptvPlaylistEntry(id = "second", name = "Second playlist", m3uUrl = "https://second.test/list.m3u"),
            )
        )
        // All groups from all categories
        val hiddenGroups = setOf(
            com.arflix.tv.data.model.PlaylistGroupKey.build("first", "Movies"),
            com.arflix.tv.data.model.PlaylistGroupKey.build("first", "Series"),
            com.arflix.tv.data.model.PlaylistGroupKey.build("second", "Sports"),
            com.arflix.tv.data.model.PlaylistGroupKey.build("second", "News")
        )
        val categories = listOf(
            LiveCategory("first:a", "Movies", 8, CategoryIcon.Movie, playlistGroupName = "Movies", playlistId = "first"),
            LiveCategory("first:b", "Series", 10, CategoryIcon.Grid, playlistGroupName = "Series", playlistId = "first"),
            LiveCategory("second:a", "Sports", 6, CategoryIcon.Sport, playlistGroupName = "Sports", playlistId = "second"),
            LiveCategory("second:b", "News", 4, CategoryIcon.Grid, playlistGroupName = "News", playlistId = "second"),
        )
        val sections = buildPlaylistCategorySections(config, categories, hiddenGroups)
        // All sections should be filtered out completely
        assertThat(sections).isEmpty()
    }
}
