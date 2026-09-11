package com.arflix.tv.ui.screens.tv.live

import com.arflix.tv.data.model.IptvChannel
import com.arflix.tv.data.model.PlaylistGroupKey
import org.junit.Assert.*
import org.junit.Test

class QuickGuideChannelsTest {
    private fun channel(id: String, group: String = "General") =
        IptvChannel(id, "Channel $id", "https://example.invalid/$id", group).enrichForFastStartup(1)

    @Test fun recentCountUsesResolvedVisibleChannelsInNewestFirstOrder() {
        val channels = listOf(channel("a"), channel("b"), channel("hidden", "Hidden"),
            channel("locked", "Locked"), channel("adult").copy(isAdult = true))
        val rows = quickGuideChannels(channels, listOf("b", "deleted", "a", "b"),
            linkedSetOf("a", "deleted", "hidden", "b", "locked", "adult"),
            setOf(PlaylistGroupKey.build(channelPlaylistId("hidden"), "Hidden")),
            setOf(PlaylistGroupKey.build(channelPlaylistId("locked"), "Locked")), false)
        assertEquals(listOf("b", "a"), rows.getValue("recent").map { it.id })
        assertEquals(2, rows.getValue("recent").size)
        assertEquals(listOf("b", "a"), rows.getValue("fav").map { it.id })
    }

    @Test fun recentResolutionDoesNotDependOnDatabaseOrder() {
        val channels = (1..12).map { channel(it.toString()) }
        val recents = (1..22).map { it.toString() }.toSet()
        for (collapse in listOf(false, true)) {
            val result = quickGuideChannels(channels, emptyList(), recents, emptySet(), emptySet(), collapse)
            assertEquals((12 downTo 1).map { it.toString() }, result.getValue("recent").map { it.id })
        }
    }
}
