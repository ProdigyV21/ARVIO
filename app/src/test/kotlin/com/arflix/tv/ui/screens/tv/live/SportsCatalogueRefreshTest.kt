package com.arflix.tv.ui.screens.tv.live

import com.arflix.tv.data.model.IptvChannel
import com.arflix.tv.data.model.IptvNowNext
import com.arflix.tv.data.model.IptvProgram
import org.junit.Assert.assertEquals
import org.junit.Assert.assertSame
import org.junit.Test
import java.time.Instant

class SportsCatalogueRefreshTest {
    private val now = Instant.parse("2026-11-18T14:00:00Z").toEpochMilli()
    private val channel = IptvChannel("provider:1", "Sports", "https://example.invalid/fixture", "Sports")

    @Test fun partialCatalogueDoesNotDropExistingMatchesAndACompleteEmptyRefreshCanClearThem() {
        val known = event("North vs South", now - 60_000)
        val previous = listOf(known, event("West vs East", now + 120_000))
        val partial = listOf(known)
        assertSame(previous, displaySportsCatalogue(previous, partial, false, now))
        assertSame(partial, displaySportsCatalogue(emptyList(), partial, false, now))
        assertEquals(emptyList<SportsGuideEvent>(), displaySportsCatalogue(previous, emptyList(), true, now))
        assertSame(partial, displaySportsCatalogue(previous, partial, false, now + 600_000))
    }

    @Test fun indexedProgrammesWinAtTheSameStartButMemoryFillsOtherSlots() {
        val indexed = event("Live Football: North vs South", now - 60_000)
        val stale = indexed.programme.copy(title = "Live Football: Wrong opponent")
        val later = event("Live Boxing: First vs Second", now + 240_000)
        val events = SportsEventIndex().apply { add(indexed) }
        accumulateSportsGuideEvents(
            listOf(channel), mapOf(channel.id to IptvNowNext(now = stale, next = later.programme)), now, events,
            indexedStarts = mapOf(channel.id to setOf(indexed.programme.startUtcMillis)),
        )
        assertEquals(setOf(indexed.title, later.title), events.events().map { it.title }.toSet())
        assertEquals(listOf(channel), events.events().single { it.title == later.title }.channels)
    }

    private fun event(title: String, start: Long) =
        SportsGuideEvent(title, title, GuideSport.FOOTBALL,
            IptvProgram(title, startUtcMillis = start, endUtcMillis = start + 120_000), listOf(channel))
}
