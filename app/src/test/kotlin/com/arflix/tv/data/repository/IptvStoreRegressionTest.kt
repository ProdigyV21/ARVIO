package com.arflix.tv.data.repository

import android.app.Application
import com.arflix.tv.data.model.IptvChannel
import com.arflix.tv.data.model.IptvGuideHistory
import com.arflix.tv.data.model.IptvNowNext
import com.arflix.tv.data.model.IptvProgram
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import org.robolectric.annotation.ConscryptMode

@RunWith(RobolectricTestRunner::class)
@Config(application = Application::class, manifest = Config.NONE, sdk = [28])
@ConscryptMode(ConscryptMode.Mode.OFF)
class IptvStoreRegressionTest {
    private lateinit var channels: IptvChannelStore
    private lateinit var epg: IptvEpgIndex
    private val key = "profile|two-providers"
    private val now = 1_800_000_000_000L
    private val quarterHour = 15 * 60_000L

    @Before
    fun setUp() {
        val context = RuntimeEnvironment.getApplication()
        channels = IptvChannelStore(context)
        epg = IptvEpgIndex(context)
    }

    @After
    fun tearDown() {
        channels.close()
        epg.close()
    }

    @Test
    fun savedChannelInAnotherGroupDoesNotAnchorAtEndOfSelectedGroup() {
        channels.replaceAll(key, sampleChannels(), now)

        assertEquals(-1, channels.indexOfId(key, "first", "News", "first:Sports:50"))
    }

    @Test
    fun partialUpdatesNeverPretendToBeACompletedFullGuideRefresh() {
        val guide = mapOf("channel" to IptvNowNext(now = IptvProgram("Live",
            startUtcMillis = now, endUtcMillis = now + quarterHour)))
        epg.replaceChannels(key, guide, now)
        assertEquals(0L, epg.fullRefreshAtMs(key))
        epg.markFullRefreshComplete(key, now)
        epg.replaceChannels(key, guide, now + 1000)
        epg.replaceAll(key, guide, now + 2000)
        epg.close()
        epg = IptvEpgIndex(RuntimeEnvironment.getApplication())
        assertEquals(now, epg.fullRefreshAtMs(key))
        assertEquals(0L, epg.fullRefreshAtMs("different-provider"))
    }

    @Test
    fun savedChannelInAnotherProviderIsNotAnAnchor() {
        channels.replaceAll(key, sampleChannels(), now)

        assertEquals(-1, channels.indexOfId(key, "first", "News", "second:News:50"))
        assertEquals(-1, channels.indexOfId(key, "second", "Sports", "first:News:50"))
        assertEquals(50, channels.indexOfId(key, "second", "Sports", "second:Sports:50"))
    }

    @Test
    fun smallPlaylistStartupIncludesEveryProvider() {
        val all = sampleChannels()
        channels.replaceAll(key, all, now)
        assertEquals(all, channels.loadStartupChannels(key, 10_000, 240))
    }

    @Test
    fun fiftyThousandChannelsRemainPagedAndEveryProviderGroupIsAvailable() {
        val all = listOf("first", "second").flatMap { provider ->
            (0 until 5).flatMap { group -> List(5_000) { index ->
                IptvChannel(id = "$provider:$group:$index", name = "Channel $index", group = "Group $group",
                    streamUrl = "https://example.invalid/$provider/$group/$index.ts")
            } }
        }
        channels.replaceAll(key, all, now)
        assertEquals(50_000, channels.count(key))
        assertEquals(all.take(240), channels.loadStartupChannels(key, 10_000, 240))
        listOf("first", "second").forEach { provider ->
            (0 until 5).forEach { group ->
                assertEquals(5_000, channels.countForPlaylistGroup(key, provider, "Group $group"))
                assertEquals(List(144) { "$provider:$group:$it" },
                    channels.windowForPlaylistGroup(key, provider, "Group $group", 0, 144).map { it.id })
                assertEquals(List(2) { "$provider:$group:${4_998 + it}" },
                    channels.windowForPlaylistGroup(key, provider, "Group $group", 4_998, 144).map { it.id })
            }
        }
    }

    @Test
    fun shortGuideReadsRemainBoundedForLowMemoryDevices() {
        epg.replaceChannels(key, mapOf("first:News:1" to IptvNowNext(recent = history())), now)
        assertEquals(48, epg.loadNowNext(key, setOf("first:News:1"), now).getValue("first:News:1").recent.size)
    }

    @Test fun sharedXmlScheduleIsStoredOnceForAllQualityVariants() {
        val ids = List(200) { "provider:quality:$it" }
        val canonical = "@xml:feed:ESPN1.nl"
        val program = IptvProgram("Sports", startUtcMillis = now - quarterHour, endUtcMillis = now + quarterHour)
        epg.replaceChannels(key, mapOf(canonical to IptvNowNext(now = program)), now, mapOf(canonical to ids))
        epg.finishStreamingRefresh(key, now)
        assertEquals(1, epg.countPrograms(key))
        assertEquals(200, epg.countChannelsWithPrograms(key))
        assertEquals(ids.toSet(), epg.loadNowNext(key, ids.toSet(), now).keys)
        assertEquals(program, epg.loadNowNext(key, setOf(ids.last()), now).getValue(ids.last()).now)
    }

    @Test fun directApiCorrectionWinsWithoutLosingSharedArchiveOrOtherProviderIsolation() {
        val canonical = "@xml:feed:sports"
        val original = IptvProgram("Old title", startUtcMillis = now, endUtcMillis = now + quarterHour)
        epg.replaceChannels(key, mapOf(canonical to IptvNowNext(now = original, recent = history())), now,
            mapOf(canonical to listOf("first:hd", "first:sd")))
        val correction = original.copy(title = "Corrected")
        epg.replaceChannels(key, mapOf("first:hd" to IptvNowNext(now = correction)), now)
        val rows = epg.loadWindow(key, setOf("first:hd"), now - quarterHour, now + quarterHour).getValue("first:hd")
        assertEquals(2, rows.size)
        assertEquals(correction, rows.last())
        assertEquals(original, epg.loadNowNext(key, setOf("first:sd"), now).getValue("first:sd").now)
        assertEquals(emptyMap<String, IptvNowNext>(), epg.loadNowNext("different-source", setOf("first:sd"), now))
        epg.close()
        epg = IptvEpgIndex(RuntimeEnvironment.getApplication())
        assertEquals(history(), epg.loadNowNext(key, setOf("first:hd"), now,
            pastWindowMs = IptvGuideHistory.MAX_WINDOW_MS, recentProgramLimit = IptvGuideHistory.MAX_PROGRAMS)
            .getValue("first:hd").recent)
    }

    @Test
    fun correctedProgrammeReplacesItsOldTitleWithoutDeletingOtherShows() {
        val old = IptvProgram("Old title", startUtcMillis = now - quarterHour, endUtcMillis = now)
        val corrected = old.copy(title = "Correct title")
        epg.replaceChannels(key, mapOf("first:News:1" to IptvNowNext(recent = listOf(old))), now)
        epg.replaceChannels(key, mapOf("first:News:1" to IptvNowNext(recent = listOf(corrected))), now)
        assertEquals(listOf(corrected), epg.loadNowNext(key, setOf("first:News:1"), now)
            .getValue("first:News:1").recent)
    }

    @Test
    fun archiveUpdatesAreProviderScopedAndExpireOldProgrammes() {
        val expired = IptvProgram("Expired", startUtcMillis = now - 9 * IptvGuideHistory.DAY_MS,
            endUtcMillis = now - 8 * IptvGuideHistory.DAY_MS)
        epg.replaceChannels(key, mapOf("first:News:1" to IptvNowNext(recent = history()),
            "second:News:1" to IptvNowNext(recent = history())), now)
        epg.replaceChannels(key, mapOf("first:News:1" to IptvNowNext(recent = listOf(expired))), now)
        val window = epg.loadWindow(key, setOf("first:News:1", "second:News:1"),
            now - 10 * IptvGuideHistory.DAY_MS, now)
        assertEquals(history(), window["first:News:1"])
        assertEquals(history(), window["second:News:1"])
    }

    @Test
    fun indexedGuideRetainsThreeDaysOfShortProgrammes() {
        val history = history()
        epg.replaceChannels(key, mapOf("first:News:1" to IptvNowNext(recent = history)), now)

        val restored = epg.loadNowNext(key, setOf("first:News:1"), now,
            pastWindowMs = IptvGuideHistory.MAX_WINDOW_MS, recentProgramLimit = IptvGuideHistory.MAX_PROGRAMS)
            .getValue("first:News:1")
        assertEquals(history, restored.recent)
    }

    @Test
    fun shortRefreshDoesNotDeletePreviouslyIndexedArchiveOrFuture() {
        val history = history()
        val future = IptvProgram("Tomorrow", startUtcMillis = now + 24 * 60 * 60_000L,
            endUtcMillis = now + 25 * 60 * 60_000L)
        epg.replaceChannels(key, mapOf("first:News:1" to IptvNowNext(recent = history,
            upcoming = listOf(future))), now)
        val live = IptvProgram("Live", startUtcMillis = now - 60_000L, endUtcMillis = now + quarterHour)
        epg.replaceChannels(key, mapOf("first:News:1" to IptvNowNext(now = live)), now)

        val restored = epg.loadNowNext(key, setOf("first:News:1"), now,
            pastWindowMs = IptvGuideHistory.MAX_WINDOW_MS, recentProgramLimit = IptvGuideHistory.MAX_PROGRAMS)
            .getValue("first:News:1")
        assertEquals(history, restored.recent)
        assertEquals(live, restored.now)
        assertEquals(listOf(future), restored.upcoming)
    }

    @Test
    fun fullGuideRefreshAlsoRetainsHistoryAndAppliesCorrections() {
        val channelId = "first:News:1"
        val history = history()
        val future = IptvProgram("Tomorrow", startUtcMillis = now + IptvGuideHistory.DAY_MS,
            endUtcMillis = now + IptvGuideHistory.DAY_MS + quarterHour)
        epg.replaceAll(key, mapOf(channelId to IptvNowNext(recent = history, upcoming = listOf(future))), now)
        val corrected = history.last().copy(title = "Corrected archive")
        val live = IptvProgram("Live", startUtcMillis = now, endUtcMillis = now + quarterHour)
        epg.replaceAll(key, mapOf(channelId to IptvNowNext(now = live, recent = listOf(corrected))), now)

        val restored = epg.loadNowNext(key, setOf(channelId), now,
            pastWindowMs = IptvGuideHistory.MAX_WINDOW_MS, recentProgramLimit = IptvGuideHistory.MAX_PROGRAMS)
            .getValue(channelId)
        assertEquals(history.dropLast(1) + corrected, restored.recent)
        assertEquals(listOf(future), restored.upcoming)
        assertEquals(1, epg.countChannelsWithPrograms(key))
        assertEquals(290, epg.countPrograms(key))
    }

    @Test
    fun fullRefreshPrunesExpiredHistoryWithoutAffectingOtherProfiles() {
        val channelId = "first:News:1"
        val history = history()
        epg.replaceAll(key, mapOf(channelId to IptvNowNext(recent = history)), now)
        epg.replaceAll("other-profile", mapOf(channelId to IptvNowNext(recent = history)), now)
        val later = now + 8 * IptvGuideHistory.DAY_MS
        val live = IptvProgram("Live", startUtcMillis = later, endUtcMillis = later + quarterHour)
        epg.replaceAll(key, mapOf(channelId to IptvNowNext(now = live)), later)

        assertEquals(1, epg.countPrograms(key))
        assertEquals(288, epg.countPrograms("other-profile"))
        assertEquals(emptyList<IptvProgram>(), epg.loadWindow(key, setOf(channelId),
            now - 10 * IptvGuideHistory.DAY_MS, now)[channelId].orEmpty())
    }

    @Test
    fun interruptedFullRefreshRollsBackArchiveChanges() {
        val channelId = "first:News:1"
        epg.replaceAll(key, mapOf(channelId to IptvNowNext(recent = history())), now)
        var checks = 0
        try {
            epg.replaceAll(key, mapOf(channelId to IptvNowNext(recent = listOf(history().last().copy(title = "Changed")))),
                now, shouldAbort = { ++checks >= 5 })
            org.junit.Assert.fail("Refresh must be cancelled during the transaction")
        } catch (_: kotlinx.coroutines.CancellationException) {
            // The matching-start deletion must roll back along with inserts.
        }
        assertEquals(history(), epg.loadNowNext(key, setOf(channelId), now,
            pastWindowMs = IptvGuideHistory.MAX_WINDOW_MS, recentProgramLimit = IptvGuideHistory.MAX_PROGRAMS)
            .getValue(channelId).recent)
    }

    private fun sampleChannels() = listOf("first", "second").flatMap { provider ->
        listOf("News", "Sports").flatMap { group ->
            List(100) { index -> IptvChannel(id = "$provider:$group:$index", name = "$group $index",
                group = group, streamUrl = "https://example.invalid/$provider/$group/$index.ts") }
        }
    }

    private fun history() = List(288) { index ->
        IptvProgram("Archive $index", startUtcMillis = now - (288 - index) * quarterHour,
            endUtcMillis = now - (287 - index) * quarterHour)
    }
}
