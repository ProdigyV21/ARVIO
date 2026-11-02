package com.arflix.tv.data.repository

import org.junit.Assert.*
import org.junit.Test

class IptvPlaylistIdentityTest {
    private fun entry(id: String, n: Int) = IptvPlaylistEntry(id, "Playlist $n", "https://example.invalid/$n.m3u")

    @Test fun repairsOldDeleteThenAddCollisionWithoutLosingEitherProvider() {
        val input = listOf(entry("list_1", 1), entry("list_3", 3), entry("list_3", 4))
        val repaired = repairIptvPlaylistIds(input)
        assertEquals(3, repaired.map { it.id }.distinct().size)
        assertEquals(input.map { it.m3uUrl }, repaired.map { it.m3uUrl })
        assertEquals("list_3", repaired[1].id)
        assertEquals(repaired, repairIptvPlaylistIds(repaired))
    }

    @Test fun repairDoesNotTakeAnExistingIdLaterInTheList() {
        val repaired = repairIptvPlaylistIds(listOf(entry("a", 1), entry("a", 2), entry("list_recovered_2", 3)))
        assertEquals(listOf("a", "list_recovered_2_1", "list_recovered_2"), repaired.map { it.id })
    }

    @Test fun preservesTenDistinctProvidersAndTheirOrder() {
        val input = (1..10).map { entry("list_$it", it) }
        assertEquals(input, repairIptvPlaylistIds(input))
        assertEquals(10, MAX_IPTV_PLAYLISTS)
    }
}
