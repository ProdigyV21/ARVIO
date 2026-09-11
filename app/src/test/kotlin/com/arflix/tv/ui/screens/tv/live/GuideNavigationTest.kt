package com.arflix.tv.ui.screens.tv.live

import com.arflix.tv.data.model.IptvChannel
import org.junit.Assert.*
import org.junit.Test

class GuideNavigationTest {
    @Test fun heldDownKeyCoalescesThePendingDatabasePage() {
        var requested = 144
        repeat(100) { requested = nextGuidePageLimit(144, requested, 55_000) }
        assertEquals(336, requested)
        assertEquals(528, nextGuidePageLimit(336, requested, 55_000))
        assertEquals(400, nextGuidePageLimit(336, requested, 400))
    }

    private fun channel(id: String, group: String = "News", name: String = id) =
        IptvChannel(id = id, name = name, group = group, streamUrl = "https://example.test/live")
            .enrichForFastStartup(1)

    @Test fun favoriteMoveIsNotUndoneByNameOrNumberSort() {
        val channels = listOf(channel("p:2", name = "Z"), channel("p:1", name = "A"))
        listOf("name", "number", "provider").forEach { sort ->
            assertEquals(channels, prepareGuideChannels(channels, "fav", sort, emptySet(), emptySet()))
            assertEquals(channels, prepareGuideChannels(channels, "recent", sort, emptySet(), emptySet()))
        }
    }

    @Test fun hiddenAndLockedGroupsNeverLeakThroughPagedFallback() {
        val rows = listOf(channel("p:1"), channel("q:1"), channel("p:2", "Movies"))
        listOf("all", "fav", "recent", "grp:p:News").forEach { category ->
            assertEquals(listOf("q:1"), prepareGuideChannels(rows, category, "provider",
                setOf("p|News"), setOf("p|Movies")).map { it.id })
        }
    }

    @Test fun duplicateIdsCannotCrashLazyRows() {
        val row = channel("p:1")
        assertEquals(listOf(row), prepareGuideChannels(listOf(row, row), "all", "provider", emptySet(), emptySet()))
    }

    @Test fun hidingGroupDoesNotCreateAdultShortcutOrInflateTotal() {
        val state = buildPagedStartupChannelState(
            channels = listOf(channel("p:1").source), totalChannelCount = 55_000,
            playlistGroupCounts = listOf(Triple("p", "News", 54_990), Triple("p", "Hidden", 10)),
            favorites = emptySet(), recents = emptySet(), hiddenGroups = setOf("p|Hidden")
        )
        assertEquals(54_990, state.tree.top.first { it.id == "all" }.count)
        assertTrue(state.tree.global.categories.none { it.playlistGroupName == "Hidden" })
        assertTrue(state.tree.top.first { it.id == "all" }.children.none { it.id == "adult" })
        assertEquals(1, state.tree.hidden.categories.size)
    }
}
