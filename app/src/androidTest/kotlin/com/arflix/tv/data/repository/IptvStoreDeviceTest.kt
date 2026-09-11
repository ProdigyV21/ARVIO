package com.arflix.tv.data.repository

import android.os.SystemClock
import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.arflix.tv.data.model.IptvChannel
import com.arflix.tv.data.model.IptvGuideHistory
import com.arflix.tv.data.model.IptvNowNext
import com.arflix.tv.data.model.IptvProgram
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import java.util.UUID

@RunWith(AndroidJUnit4::class)
class IptvStoreDeviceTest {
    private val context get() = InstrumentationRegistry.getInstrumentation().targetContext

    @Test fun programmeArtworkCategoriesAndAliasesSurviveReopening() {
        val name = "guide-metadata-${UUID.randomUUID()}.db"
        val now = System.currentTimeMillis()
        val programme = IptvProgram("North vs South", startUtcMillis = now - 1_000, endUtcMillis = now + 60_000,
            artworkUrl = "https://example.com/match.webp", category = "Football")
        try {
            IptvEpgIndex(context, name).use { store ->
                store.replaceChannels("test", mapOf("@xml:general" to IptvNowNext(now = programme)), now,
                    aliases = mapOf("@xml:general" to listOf("provider:general")))
            }
            IptvEpgIndex(context, name).use { store ->
                assertEquals(setOf("provider:general"), store.channelIdsInWindow("test", now, now + 60_000))
                assertEquals(programme, store.loadNowNext("test", setOf("provider:general"), now).getValue("provider:general").now)
            }
        } finally { context.deleteDatabase(name) }
    }

    @Test fun metadataMigrationKeepsVersionSevenGuideAndCatchup() {
        val name = "guide-migration-${UUID.randomUUID()}.db"
        val now = System.currentTimeMillis()
        try {
            context.openOrCreateDatabase(name, 0, null).use { db ->
                db.execSQL("CREATE TABLE epg_programs (source_key TEXT, channel_id TEXT, start_ms INTEGER, end_ms INTEGER, title TEXT, description TEXT, catchup_available INTEGER)")
                db.execSQL("CREATE TABLE epg_channel_aliases (source_key TEXT, channel_id TEXT, guide_id TEXT, updated_ms INTEGER)")
                db.execSQL("INSERT INTO epg_programs VALUES (?, ?, ?, ?, ?, ?, ?)", arrayOf<Any>("test", "one", now - 1_000, now + 60_000, "Still here", "Guide", 1))
                db.version = 7
            }
            IptvEpgIndex(context, name).use { store ->
                val p = store.loadNowNext("test", setOf("one"), now).getValue("one").now!!
                assertEquals("Still here", p.title)
                assertEquals(true, p.catchupAvailable)
                assertEquals(null, p.artworkUrl)
                assertEquals(8, store.readableDatabase.version)
            }
        } finally { context.deleteDatabase(name) }
    }

    @Test fun eligibleGuideAliasesRespectSourceAndExclusiveTimeWindow() {
        val name = "guide-identities-${UUID.randomUUID()}.db"
        val now = System.currentTimeMillis()
        val live = IptvProgram("Event", startUtcMillis = now - 1_000, endUtcMillis = now + 60_000)
        try {
            IptvEpgIndex(context, name).use { store ->
                store.replaceChannels("one", mapOf(
                    "direct" to IptvNowNext(now = live),
                    "@xml:live" to IptvNowNext(now = live),
                    "@xml:ended" to IptvNowNext(now = live.copy(endUtcMillis = now)),
                    "@xml:later" to IptvNowNext(next = live.copy(startUtcMillis = now + 60_000, endUtcMillis = now + 120_000))
                ), now, aliases = mapOf("@xml:live" to listOf("hd", "uhd", "direct"),
                    "@xml:ended" to listOf("ended"), "@xml:later" to listOf("later")))
                store.replaceChannels("other", mapOf("@xml:live" to IptvNowNext(now = live)), now,
                    aliases = mapOf("@xml:live" to listOf("other-channel")))
                assertEquals(setOf("direct", "hd", "uhd"), store.channelIdsInWindow("one", now, now + 60_000))
                assertEquals(setOf("other-channel"), store.channelIdsInWindow("other", now, now + 60_000))
                assertEquals(emptySet<String>(), store.channelIdsInWindow("one", now, now))
            }
        } finally { context.deleteDatabase(name) }
    }

    @Test
    fun fiftyThousandChannelsSurviveReloadAndEveryGroupPagesInProviderOrder() {
        val key = "device-regression-${UUID.randomUUID()}"
        var store = IptvChannelStore(context)
        try {
            val all = listOf("first", "second").flatMap { provider ->
                (0 until 5).flatMap { group -> List(5_000) { index ->
                    IptvChannel("$provider:$group:$index", "Channel $index",
                        "https://example.invalid/$index.ts", "Group $group")
                } }
            }
            store.replaceAll(key, all, System.currentTimeMillis())
            store.close()
            store = IptvChannelStore(context)
            val startupAt = SystemClock.elapsedRealtime()
            assertEquals(240, store.loadStartupChannels(key, 10_000, 240).size)
            assertEquals(50_000, store.count(key))
            val labels = mutableListOf<String>()
            store.visitLabels(key, "second") { id, _, _ -> labels.add(id) }
            assertEquals(all.filter { it.id.startsWith("second:") }.map { it.id }, labels)
            Log.i("IptvStoreDeviceTest", "50k cold store startupMs=${SystemClock.elapsedRealtime() - startupAt}")
            val startedAt = SystemClock.elapsedRealtime()
            listOf("first", "second").forEach { provider -> (0 until 5).forEach { group ->
                assertEquals(5_000, store.countForPlaylistGroup(key, provider, "Group $group"))
                assertEquals(-1, store.indexOfId(key, provider, "Group $group", "other-provider:4:4999"))
                val actual = (0 until 5_000 step 144).flatMap { offset ->
                    store.windowForPlaylistGroup(key, provider, "Group $group", offset, 144).map { it.id }
                }
                assertEquals(List(5_000) { "$provider:$group:$it" }, actual)
            } }
            Log.i("IptvStoreDeviceTest", "50k paged rows verified groups=10 providers=2 totalReadMs=${SystemClock.elapsedRealtime() - startedAt}")
        } finally {
            store.deleteSource(key)
            store.close()
        }
    }

    @Test
    fun fullArchiveSurvivesShortGuideRefreshAndProcessStyleDatabaseReopen() {
        val key = "device-regression-${UUID.randomUUID()}"
        val channelId = "second:1"
        val now = System.currentTimeMillis()
        val interval = 15 * 60_000L
        val history = List(288) { i -> IptvProgram("Archive $i",
            startUtcMillis = now - (288 - i) * interval, endUtcMillis = now - (287 - i) * interval) }
        var store = IptvEpgIndex(context)
        try {
            store.replaceChannels(key, mapOf(channelId to IptvNowNext(recent = history)), now)
            store.close()
            store = IptvEpgIndex(context)
            store.replaceChannels(key, mapOf(channelId to IptvNowNext(now = IptvProgram("Live",
                startUtcMillis = now, endUtcMillis = now + interval))), now)
            store.replaceAll(key, mapOf(channelId to IptvNowNext(now = IptvProgram("Live",
                startUtcMillis = now, endUtcMillis = now + interval))), now)
            val startedAt = SystemClock.elapsedRealtime()
            val guide = store.loadNowNext(key, setOf(channelId), now,
                pastWindowMs = IptvGuideHistory.MAX_WINDOW_MS, recentProgramLimit = IptvGuideHistory.MAX_PROGRAMS)
                .getValue(channelId)
            assertEquals(history, guide.recent)
            assertEquals("Live", guide.now?.title)
            Log.i("IptvStoreDeviceTest", "72h archive records=288 readMs=${SystemClock.elapsedRealtime() - startedAt}")
        } finally {
            store.deleteSource(key)
            store.close()
        }
    }
}
