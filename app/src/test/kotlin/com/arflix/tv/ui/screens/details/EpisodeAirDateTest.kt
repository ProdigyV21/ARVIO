package com.arflix.tv.ui.screens.details

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import java.util.TimeZone
import java.time.LocalDate
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue

class EpisodeAirDateTest {

    private fun withDefaultTimeZone(zoneId: String, block: () -> Unit) {
        val original = TimeZone.getDefault()
        try {
            TimeZone.setDefault(TimeZone.getTimeZone(zoneId))
            block()
        } finally {
            TimeZone.setDefault(original)
        }
    }

    @Test
    fun preserveDateOnlyAcrossDifferentTimeZones() {
        val testZones = listOf(
            "UTC",
            "Europe/London",
            "Europe/Paris",
            "Asia/Kolkata",
            "Asia/Tokyo",
            "Australia/Sydney",
            "America/New_York",
            "America/Los_Angeles",
            "Pacific/Auckland"
        )

        for (zone in testZones) {
            withDefaultTimeZone(zone) {
                // Must preserve Jan 1 as Jan 1 (not shifting to Jan 2 in UTC or east-of-UTC)
                assertEquals(
                    "Failed in $zone",
                    "1 Jan 2026",
                    formatEpisodeAirDateLabel("2026-01-01")
                )
                assertEquals(
                    "Failed in $zone",
                    "10 May 2024",
                    formatEpisodeAirDateLabel("2024-05-10")
                )
                assertEquals(
                    "Failed in $zone",
                    "31 Dec 2025",
                    formatEpisodeAirDateLabel("2025-12-31")
                )
            }
        }
    }

    @Test
    fun convertsExplicitUtcMidnightAcrossTimeZones() {
        val expected = mapOf(
            "America/New_York" to "31 Dec 2025", "America/Los_Angeles" to "31 Dec 2025",
            "UTC" to "1 Jan 2026", "Asia/Kolkata" to "1 Jan 2026"
        )
        for ((zone, label) in expected) {
            withDefaultTimeZone(zone) {
                assertEquals(
                    "Failed in $zone",
                    label,
                    formatEpisodeAirDateLabel("2026-01-01T00:00:00.000Z")
                )
            }
        }
    }

    @Test fun dateOnlyDoesNotInventAnAmericanEveningAirTime() {
        for (zone in listOf("America/New_York", "Europe/Paris", "Asia/Tokyo")) {
            withDefaultTimeZone(zone) {
                assertFalse(isFutureEpisodeAirDate(LocalDate.now().toString()))
                assertTrue(isFutureEpisodeAirDate(LocalDate.now().plusDays(1).toString()))
            }
        }
    }

    @Test
    fun convertsExplicitTimedReleasesToDeviceTimeZone() {
        // 23:30 UTC on Jan 1 is 05:00 next day in Asia/Kolkata (UTC+5:30)
        withDefaultTimeZone("Asia/Kolkata") {
            assertEquals("2 Jan 2026", formatEpisodeAirDateLabel("2026-01-01T23:30:00Z"))
        }
        withDefaultTimeZone("UTC") {
            assertEquals("1 Jan 2026", formatEpisodeAirDateLabel("2026-01-01T23:30:00Z"))
        }
    }

    @Test
    fun fallbackAndEdgeCases() {
        assertEquals("2024", formatEpisodeAirDateLabel("2024"))
        assertNull(formatEpisodeAirDateLabel(""))
        assertNull(formatEpisodeAirDateLabel("   "))
    }
}
