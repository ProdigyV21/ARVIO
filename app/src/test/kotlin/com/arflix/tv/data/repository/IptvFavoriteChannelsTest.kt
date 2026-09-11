package com.arflix.tv.data.repository

import org.junit.Assert.*
import org.junit.Test

class IptvFavoriteChannelsTest {
    @Test fun addIsImmediateIdempotentAndPreservesExistingOrder() {
        val first = favoriteChannelsWithMembership(listOf("b", "a"), "c", true)
        assertEquals(listOf("c", "b", "a"), first)
        assertSame(first, favoriteChannelsWithMembership(first, "c", true))
        assertSame(first, favoriteChannelsWithMembership(first, "b", true))
    }

    @Test fun repeatedRemoveCannotAddChannelBack() {
        val removed = favoriteChannelsWithMembership(listOf("c", "b", "a"), "b", false)
        assertEquals(listOf("c", "a"), removed)
        assertSame(removed, favoriteChannelsWithMembership(removed, "b", false))
    }

    @Test fun blankChannelIsIgnored() {
        val initial = listOf("a")
        assertSame(initial, favoriteChannelsWithMembership(initial, " ", true))
    }
}
