package com.arflix.tv.data.repository

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset

class XtreamCatchupTest {
    // 2026-11-26 18:00:00 UTC
    private val startUtc = 1_795_716_000_000L

    @Test
    fun `panel zone comes first, then guide offset, device zone and utc`() {
        val starts = xtreamCatchupStartTimes(
            startUtcMillis = startUtc,
            serverZone = ZoneId.of("Europe/Amsterdam"),
            epgOffsetMs = 2 * 3_600_000L,
            deviceZone = ZoneId.of("America/New_York"),
        )
        assertEquals(
            listOf(
                LocalDateTime.of(2026, 11, 26, 19, 0),
                LocalDateTime.of(2026, 11, 26, 20, 0),
                LocalDateTime.of(2026, 11, 26, 13, 0),
                LocalDateTime.of(2026, 11, 26, 18, 0),
            ),
            starts,
        )
    }

    @Test
    fun `duplicate start times collapse`() {
        val starts = xtreamCatchupStartTimes(startUtc, ZoneOffset.UTC, 0L, ZoneOffset.UTC)
        assertEquals(listOf(LocalDateTime.of(2026, 11, 26, 18, 0)), starts)
    }

    @Test
    fun `first candidates are the ts path for every start time`() {
        val starts = listOf(LocalDateTime.of(2026, 11, 26, 19, 0), LocalDateTime.of(2026, 11, 26, 18, 0))
        val urls = xtreamCatchupUrlCandidates(
            starts,
            pathUrl = { start, ext -> "path/$start" + (ext?.let { ".$it" } ?: "") },
            queryUrl = { start, prefix, param -> "query/$prefix/$param/$start" },
        )
        assertEquals(listOf("path/2026-11-26:19-00.ts", "path/2026-11-26:18-00.ts"), urls.take(2))
        assertEquals(urls.distinct(), urls)
        assertTrue(urls.contains("query/true/stream/2026-11-26:19-00"))
        assertTrue(urls.contains("query/false/stream_id/2026-11-26 18:00:00"))
    }

    @Test
    fun `server zone is read from the account answer`() {
        assertEquals(
            ZoneId.of("Europe/Athens"),
            parseXtreamServerZone("""{"user_info":{"auth":1},"server_info":{"timezone":"Europe/Athens"}}"""),
        )
    }

    @Test
    fun `without a named zone the offset comes from the panel clock`() {
        val zone = parseXtreamServerZone(
            """{"server_info":{"timezone":"Not/AZone","timestamp_now":1795716000,"time_now":"2026-11-26 20:00:07"}}"""
        )
        assertEquals(ZoneOffset.ofHours(2), zone)
    }

    @Test
    fun `unreadable answers give no zone`() {
        assertNull(parseXtreamServerZone("<html>"))
        assertNull(parseXtreamServerZone("""{"user_info":{}}"""))
        assertNull(parseXtreamServerZone(null))
    }
}
