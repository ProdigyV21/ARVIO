package com.arflix.tv.ui.screens.tv.live

import com.arflix.tv.data.model.IptvChannel
import com.google.common.truth.Truth.assertThat
import org.junit.Test

/**
 * Covers the Live TV startup behaviour users reported as broken: the selector
 * being trapped in the search field, focus jumping while the list was still
 * loading, and the page always reopening at the top instead of on the channel
 * they left.
 */
class LiveTvStartupTest {

    private fun channel(id: String) = IptvChannel(
        id = id,
        name = id,
        group = "General",
        streamUrl = "http://example.test/$id",
    )

    // ── Resuming the last channel ──────────────────────────────────────────

    @Test
    fun resumesTheChannelTheSessionRemembered() {
        val resumed = LiveTvStartup.resumeChannelId(
            explicitChannelId = null,
            lastChannelId = "chan-42",
            availableChannelIds = setOf("chan-1", "chan-42", "chan-99"),
        )

        assertThat(resumed).isEqualTo("chan-42")
    }

    @Test
    fun explicitChannelWinsOverTheRememberedOne() {
        val resumed = LiveTvStartup.resumeChannelId(
            explicitChannelId = "deep-link",
            lastChannelId = "chan-42",
            availableChannelIds = setOf("chan-42", "deep-link"),
        )

        assertThat(resumed).isEqualTo("deep-link")
    }

    @Test
    fun ignoresARememberedChannelThatIsNoLongerInThePlaylists() {
        val resumed = LiveTvStartup.resumeChannelId(
            explicitChannelId = null,
            lastChannelId = "removed-channel",
            availableChannelIds = setOf("chan-1", "chan-2"),
        )

        assertThat(resumed).isNull()
    }

    @Test
    fun keepsTheRememberedChannelWhileTheListIsStillEmpty() {
        // Channels arrive asynchronously; discarding the id here would defeat
        // the whole feature on a cold start.
        val resumed = LiveTvStartup.resumeChannelId(
            explicitChannelId = null,
            lastChannelId = "chan-42",
            availableChannelIds = emptySet(),
        )

        assertThat(resumed).isEqualTo("chan-42")
    }

    @Test
    fun blankRememberedIdsAreTreatedAsAbsent() {
        assertThat(
            LiveTvStartup.resumeChannelId(
                explicitChannelId = "   ",
                lastChannelId = "  ",
                availableChannelIds = setOf("chan-1"),
            )
        ).isNull()
    }

    @Test
    fun channelIdsAreDerivedFromTheSnapshot() {
        val ids = LiveTvStartup.channelIds(listOf(channel("a"), channel("b")))

        assertThat(ids).containsExactly("a", "b")
    }

    @Test
    fun persistedLastChannelWinsOverFavoriteFallback() {
        val selected = LiveTvStartup.chooseStartupChannelId(
            availableChannelIds = setOf("favorite", "last", "first"),
            firstAvailableChannelId = "first",
            explicitChannelId = null,
            sessionLastChannelId = "last",
            hasOpenedBefore = true,
            favoriteChannelIds = listOf("favorite"),
            isFullyLoaded = true,
        )

        assertThat(selected).isEqualTo("last")
    }

    @Test
    fun staleLastChannelFallsBackToFavoriteAfterFullLoad() {
        val selected = LiveTvStartup.chooseStartupChannelId(
            availableChannelIds = setOf("favorite", "first"),
            firstAvailableChannelId = "first",
            explicitChannelId = null,
            sessionLastChannelId = "removed",
            hasOpenedBefore = true,
            favoriteChannelIds = listOf("favorite"),
            isFullyLoaded = true,
        )

        assertThat(selected).isEqualTo("favorite")
    }

    @Test
    fun rememberedCategoryIsRestoredOnlyWhenItStillExists() {
        assertThat(LiveTvStartup.resumeCategoryId("grp:list_1:news", setOf("grp:list_1:news")))
            .isEqualTo("grp:list_1:news")
        assertThat(LiveTvStartup.resumeCategoryId("removed", setOf("grp:list_1:news")))
            .isEqualTo("all")
    }

    @Test
    fun favoritesComeFirstWhenTheProfileHasAny() {
        assertThat(LiveTvStartup.resumeCategoryId("grp:list_1:news", setOf("grp:list_1:news"), hasFavorites = true))
            .isEqualTo("fav")
        assertThat(LiveTvStartup.resumeCategoryId("grp:list_1:news", setOf("grp:list_1:news"), hasFavorites = false))
            .isEqualTo("grp:list_1:news")
        assertThat(LiveTvStartup.startupCategoryHint("grp:list_1:news", hasFavorites = true)).isEqualTo("fav")
        assertThat(LiveTvStartup.startupCategoryHint("", hasFavorites = false)).isEqualTo("all")
    }

    @Test
    fun backOpensHiddenCategoryDrawerBeforeLeavingTv() {
        assertThat(LiveTvStartup.guideBackAction(isTouchDevice = false, categoryDrawerOpen = false))
            .isEqualTo(LiveTvStartup.GuideBackAction.OPEN_CATEGORIES)
        assertThat(LiveTvStartup.guideBackAction(isTouchDevice = false, categoryDrawerOpen = true))
            .isEqualTo(LiveTvStartup.GuideBackAction.EXIT_TV)
    }

    @Test
    fun largePlaylistWindowIsAnchoredBeforeTheLastChannel() {
        assertThat(LiveTvStartup.anchoredWindowOffset(channelIndex = 20_000, visibleRowsBeforeAnchor = 16))
            .isEqualTo(19_984)
        assertThat(LiveTvStartup.anchoredWindowOffset(channelIndex = 8, visibleRowsBeforeAnchor = 16))
            .isEqualTo(0)
        assertThat(LiveTvStartup.anchoredWindowOffset(channelIndex = -1, visibleRowsBeforeAnchor = 16))
            .isEqualTo(0)
    }

    // ── Focus while loading ────────────────────────────────────────────────

    @Test
    fun sidebarDoesNotClaimFocusWhileChannelsAreStillLoading() {
        // The reported bug: pressing a direction key during load sent the
        // selector jumping, because the effect kept re-grabbing focus from a
        // list that was still recomposing.
        val claims = LiveTvStartup.shouldClaimSidebarFocus(
            isTouchDevice = false,
            isCategoryZoneActive = true,
            channelsLoaded = false,
        )

        assertThat(claims).isFalse()
    }

    @Test
    fun sidebarClaimsFocusOnceChannelsAreLoaded() {
        val claims = LiveTvStartup.shouldClaimSidebarFocus(
            isTouchDevice = false,
            isCategoryZoneActive = true,
            channelsLoaded = true,
        )

        assertThat(claims).isTrue()
    }

    @Test
    fun touchDevicesNeverTakeDpadFocus() {
        val claims = LiveTvStartup.shouldClaimSidebarFocus(
            isTouchDevice = true,
            isCategoryZoneActive = true,
            channelsLoaded = true,
        )

        assertThat(claims).isFalse()
    }

    @Test
    fun otherFocusZonesAreLeftAlone() {
        val claims = LiveTvStartup.shouldClaimSidebarFocus(
            isTouchDevice = false,
            isCategoryZoneActive = false,
            channelsLoaded = true,
        )

        assertThat(claims).isFalse()
    }

    // ── Search focus ───────────────────────────────────────────────────────

    @Test
    fun openingLiveTvDoesNotFocusTheSearchField() {
        // The signal seeds at 0. It used to seed at 1, and the sidebar focuses
        // search for any value > 0, so every entry trapped the selector there.
        assertThat(LiveTvStartup.shouldFocusSearch(0)).isFalse()
    }

    @Test
    fun askingForSearchFocusesIt() {
        assertThat(LiveTvStartup.shouldFocusSearch(1)).isTrue()
        assertThat(LiveTvStartup.shouldFocusSearch(7)).isTrue()
    }

    // ── Keeping search out of the way while the playlist loads ─────────────

    @Test
    fun searchCannotTakeFocusBeforeThereAreCategories() {
        // Not focusing search explicitly was not enough: it is the first
        // focusable row, so Compose handed it the selector anyway, and "down"
        // had no category to move to — the selector sat there, frozen, until
        // the whole playlist had parsed.
        assertThat(LiveTvStartup.searchIsReachable(0)).isFalse()
    }

    @Test
    fun searchBecomesReachableOnceCategoriesExist() {
        assertThat(LiveTvStartup.searchIsReachable(1)).isTrue()
        assertThat(LiveTvStartup.searchIsReachable(42)).isTrue()
    }

    // ── Where the selector lands on entry ──────────────────────────────────

    @Test
    fun openingLiveTvLandsOnTheCategoryList() {
        // The real cause of the reported bug: the screen deliberately focused
        // the search row on entry. Because "down" from search selects the first
        // category — absent until the playlist parses — the selector was stuck
        // in the search box for the whole load.
        val entry = LiveTvStartup.entryFocus(isTouchDevice = false, hasChannels = true)

        assertThat(entry).isEqualTo(LiveTvStartup.EntryFocus.CATEGORY_LIST)
    }

    @Test
    fun nothingIsFocusedBeforeChannelsExist() {
        val entry = LiveTvStartup.entryFocus(isTouchDevice = false, hasChannels = false)

        assertThat(entry).isEqualTo(LiveTvStartup.EntryFocus.NONE)
    }

    @Test
    fun touchDevicesGetNoEntryFocus() {
        val entry = LiveTvStartup.entryFocus(isTouchDevice = true, hasChannels = true)

        assertThat(entry).isEqualTo(LiveTvStartup.EntryFocus.NONE)
    }
}
