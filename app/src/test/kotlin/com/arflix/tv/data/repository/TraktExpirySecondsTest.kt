package com.arflix.tv.data.repository

import org.junit.Assert.assertEquals
import org.junit.Test

class TraktExpirySecondsTest {
    @Test fun `seconds stay seconds`() {
        assertEquals(1_800_000_000L, traktExpirySeconds(1_800_000_000L))
        assertEquals(0L, traktExpirySeconds(0L))
    }

    @Test fun `milliseconds written by the web become seconds`() {
        assertEquals(1_800_000_000L, traktExpirySeconds(1_800_000_000_000L))
    }

    @Test fun `the boundary sits between seconds in year 5138 and milliseconds in 1973`() {
        assertEquals(99_999_999_999L, traktExpirySeconds(99_999_999_999L))
        assertEquals(100_000_000L, traktExpirySeconds(100_000_000_000L))
    }
}
