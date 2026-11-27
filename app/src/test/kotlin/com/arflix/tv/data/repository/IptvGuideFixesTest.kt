package com.arflix.tv.data.repository

import com.arflix.tv.data.model.IptvNowNext
import com.arflix.tv.data.model.IptvProgram
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class IptvGuideFixesTest {
    private val hour = 60 * 60_000L
    private fun program(title: String, start: Long) = IptvProgram(title = title, startUtcMillis = start, endUtcMillis = start + hour)
    private val sourceGuide = IptvNowNext(now = program("News", 10 * hour), next = program("Film", 11 * hour))
    private val wrongGuide = IptvNowNext(now = program("Wrong", 10 * hour))

    @Test
    fun channelShowsItsSourcesGuide() {
        val fixes = IptvGuideFixes(sources = mapOf("list_1:a" to "list_2:b"))
        val guide = mapOf("list_1:a" to wrongGuide, "list_2:b" to sourceGuide)
        assertEquals("News", fixes.resolve("list_1:a") { guide[it] }?.now?.title)
        assertEquals("News", fixes.applyTo(guide)["list_1:a"]?.now?.title)
    }

    @Test
    fun noGuideHidesTheWrongOne() {
        val fixes = IptvGuideFixes(sources = mapOf("list_1:a" to IptvGuideFixes.GUIDE_SOURCE_NONE))
        assertNull(fixes.resolve("list_1:a") { wrongGuide })
        assertNull(fixes.applyTo(mapOf("list_1:a" to wrongGuide))["list_1:a"])
        assertEquals(emptyList<String>(), fixes.guideIdsFor(listOf("list_1:a")))
    }

    @Test
    fun missingSourceLeavesNoStaleGuide() {
        val fixes = IptvGuideFixes(sources = mapOf("list_1:a" to "list_2:b"))
        assertNull(fixes.applyTo(mapOf("list_1:a" to wrongGuide))["list_1:a"])
        assertEquals(listOf("list_2:b", "list_1:c"), fixes.guideIdsFor(listOf("list_1:a", "list_1:c", "list_2:b")))
    }

    @Test
    fun offsetMovesEveryProgrammeOfThatPlaylistOnly() {
        val fixes = IptvGuideFixes(offsetMinutes = mapOf("list_2" to 60))
        val shifted = fixes.resolve("list_2:b") { sourceGuide }!!
        assertEquals(11 * hour, shifted.now!!.startUtcMillis)
        assertEquals(13 * hour, shifted.next!!.endUtcMillis)
        assertEquals(10 * hour, fixes.resolve("list_1:a") { sourceGuide }!!.now!!.startUtcMillis)
    }

    @Test
    fun offsetFollowsThePlaylistTheGuideComesFrom() {
        val fixes = IptvGuideFixes(sources = mapOf("list_1:a" to "list_2:b"), offsetMinutes = mapOf("list_2" to -30))
        assertEquals(10 * hour - 30 * 60_000L, fixes.resolve("list_1:a") { sourceGuide }!!.now!!.startUtcMillis)
    }

    @Test
    fun stalkerChannelsUseTheirPortalId() {
        val fixes = IptvGuideFixes(offsetMinutes = mapOf("stalker2" to 120))
        assertEquals(2 * hour, fixes.offsetMsFor("stalker:stalker2:77"))
    }

    @Test
    fun emptyFixesReturnTheSameMap() {
        val guide = mapOf("list_1:a" to sourceGuide)
        assertTrue(IptvGuideFixes().applyTo(guide) === guide)
    }

    @Test
    fun offsetsAreNormalizedToHalfHoursWithinTwelveHours() {
        val normalized = normalizeGuideOffsets(mapOf("a" to 61.0, "b" to "-89", "c" to 2000, "d" to 0, "e" to "x", " " to 60))
        assertEquals(mapOf("a" to 60, "b" to -90, "c" to 720), normalized)
    }

    @Test
    fun sourcesDropBlankAndSelfReferences() {
        val normalized = normalizeGuideSources(mapOf("a" to "b", "c" to "c", "d" to " ", "" to "e"))
        assertEquals(mapOf("a" to "b"), normalized)
    }
}
