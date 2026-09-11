package com.arflix.tv.data.repository

import android.app.Application
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import org.robolectric.annotation.ConscryptMode
import com.arflix.tv.data.model.IptvNowNext
import com.arflix.tv.data.model.IptvProgram

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28], application = Application::class, manifest = Config.NONE)
@ConscryptMode(ConscryptMode.Mode.OFF)
class IptvEpgIndexMigrationTest {
    @Test fun streamingSportsWindowKeepsAliasesCorrectionsAndSourceIsolation() {
        val now = System.currentTimeMillis()
        val program = IptvProgram("North vs South", startUtcMillis = now - 60_000, endUtcMillis = now + 60_000, category = "Football")
        val index = IptvEpgIndex(RuntimeEnvironment.getApplication(), "sports-stream-test.db")
        try {
            index.replaceChannels("sports", mapOf("@xml:one" to IptvNowNext(now = program)), now,
                aliases = mapOf("@xml:one" to listOf("a", "b", "hidden")))
            index.replaceChannels("sports", mapOf("b" to IptvNowNext(now = program.copy(title = "Correction"))), now)
            index.replaceChannels("other", mapOf("a" to IptvNowNext(now = program.copy(title = "Other provider"))), now)
            val streamed = mutableMapOf<String, MutableList<IptvProgram>>()
            index.visitWindow("sports", setOf("a", "b"), now, now + 120_000) { id, item -> streamed.getOrPut(id) { mutableListOf() }.add(item) }
            assertEquals(index.loadWindow("sports", setOf("a", "b"), now, now + 120_000), streamed)
            assertEquals("North vs South", streamed.getValue("a").single().title)
            assertEquals("Correction", streamed.getValue("b").single().title)
            assertFalse(streamed.containsKey("hidden"))
        } finally { index.close() }
    }
    @Test fun archiveAvailabilityMigrationKeepsExistingSchedules() {
        for (oldVersion in 2..6) {
            val db = mockk<SQLiteDatabase>(relaxed = true)
            val index = IptvEpgIndex(RuntimeEnvironment.getApplication())
            index.onUpgrade(db, oldVersion, 7)
            verify(exactly = 1) { db.execSQL("ALTER TABLE epg_programs ADD COLUMN catchup_available INTEGER") }
            verify(exactly = 0) { db.execSQL(match { it.contains("DROP TABLE") }) }
        }
    }

    @Test fun versionSixRowsSurviveMigrationWithUnknownArchiveAvailability() {
        SQLiteDatabase.create(null).use { db ->
            db.execSQL("CREATE TABLE epg_programs (title TEXT)")
            db.execSQL("INSERT INTO epg_programs (title) VALUES ('Retained')")
            IptvEpgIndex(RuntimeEnvironment.getApplication()).onUpgrade(db, 6, 7)
            db.rawQuery("SELECT title, catchup_available FROM epg_programs", null).use { cursor ->
                assertTrue(cursor.moveToFirst())
                assertEquals("Retained", cursor.getString(0))
                assertTrue(cursor.isNull(1))
            }
        }
    }

    @Test fun providerAvailabilitySurvivesDiskRoundTripAndCorrection() {
        val context: Context = RuntimeEnvironment.getApplication()
        val now = System.currentTimeMillis()
        val programmes = listOf(false, true, null).mapIndexed { i, available ->
            IptvProgram("Aired $i", startUtcMillis = now - (4 - i) * 60_000,
                endUtcMillis = now - (3 - i) * 60_000, catchupAvailable = available)
        }
        val writer = IptvEpgIndex(context)
        try {
            writer.replaceChannels("test", mapOf("channel" to IptvNowNext(recent = programmes)), now)
        } finally {
            writer.close()
        }
        val index = IptvEpgIndex(context)
        try {
            val read = index.loadNowNext("test", setOf("channel"))["channel"]
            assertEquals(listOf(false, true, null), read?.recent?.map { it.catchupAvailable })
            index.replaceChannels("test", mapOf("channel" to IptvNowNext(recent = listOf(programmes[1].copy(catchupAvailable = false)))), now)
            assertEquals(listOf(false, false, null), index.loadNowNext("test", setOf("channel"))["channel"]?.recent?.map { it.catchupAvailable })
        } finally {
            index.close()
        }
    }
}
