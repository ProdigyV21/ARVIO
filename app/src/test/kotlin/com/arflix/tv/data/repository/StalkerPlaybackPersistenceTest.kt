package com.arflix.tv.data.repository

import android.app.Application
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.arflix.tv.data.model.IptvChannel
import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import org.robolectric.annotation.ConscryptMode

@RunWith(RobolectricTestRunner::class)
@Config(application = Application::class, manifest = Config.NONE, sdk = [28])
@ConscryptMode(ConscryptMode.Mode.OFF)
class StalkerPlaybackPersistenceTest {
    @Test fun directDecisionSurvivesBatchedStorageAndReopen() {
        val context = RuntimeEnvironment.getApplication()
        val channels = List(85) { index ->
            IptvChannel("stalker:stalker1:$index", "Channel $index", "https://portal.test/live/$index",
                "News", stalkerDirectStream = index % 2 == 0)
        }
        val initial = IptvChannelStore(context)
        try {
            initial.replaceAll("portal", channels, 123L)
        } finally {
            initial.close()
        }
        val store = IptvChannelStore(context)
        try {
            assertEquals(channels, store.loadAll("portal"))
            assertEquals(channels.takeLast(3), store.window("portal", 82, 3))
            assertEquals(123L, store.updatedAtMs("portal"))
        } finally {
            store.close()
        }
    }

    @Test fun olderJsonDefaultsToCreateLinkAndNewJsonPreservesDecision() {
        val gson = Gson()
        val legacy = gson.toJsonTree(IptvChannel("stalker:stalker1:1", "News",
            "https://portal.test/live/one", "News")).asJsonObject.apply { remove("stalkerDirectStream") }
        val channel = gson.fromJson(legacy, IptvChannel::class.java)
        assertFalse(channel.stalkerDirectStream)
        val direct = channel.copy(stalkerDirectStream = true)
        assertEquals(direct, gson.fromJson(gson.toJson(direct), IptvChannel::class.java))
    }

    @Test fun versionSixUpgradeKeepsChannelsAndDefaultsToCreateLink() {
        val context = RuntimeEnvironment.getApplication()
        val legacy = object : SQLiteOpenHelper(context, "arvio_iptv_channels.db", null, 6) {
            override fun onCreate(db: SQLiteDatabase) {
                db.execSQL("""CREATE TABLE channels (
                    source_key TEXT NOT NULL, ord INTEGER NOT NULL, id TEXT NOT NULL,
                    name TEXT NOT NULL, stream_url TEXT NOT NULL, group_title TEXT NOT NULL,
                    logo TEXT, epg_id TEXT, raw_title TEXT, xtream_stream_id INTEGER,
                    catchup_days INTEGER NOT NULL DEFAULT 0, catchup_type TEXT, catchup_source TEXT,
                    tvg_name TEXT, provider_channel_number TEXT, request_headers_json TEXT,
                    language TEXT, country TEXT, quality_label TEXT, variant_key TEXT, drm_json TEXT,
                    PRIMARY KEY(source_key, ord))""")
                db.execSQL("""CREATE TABLE channel_sources (source_key TEXT PRIMARY KEY NOT NULL,
                    updated_ms INTEGER NOT NULL, channel_count INTEGER NOT NULL, group_summary_json TEXT)""")
                db.execSQL("CREATE INDEX idx_channels_group ON channels(source_key, group_title, ord)")
                db.execSQL("CREATE INDEX idx_channels_id ON channels(source_key, id)")
            }
            override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) = Unit
        }
        try {
            legacy.writableDatabase.execSQL("""INSERT INTO channels
                (source_key, ord, id, name, stream_url, group_title)
                VALUES ('portal', 0, 'stalker:stalker1:1', 'News', 'https://portal.test/live/one', 'News')""")
            legacy.writableDatabase.execSQL("INSERT INTO channel_sources VALUES ('portal', 123, 1, NULL)")
        } finally {
            legacy.close()
        }
        val store = IptvChannelStore(context)
        try {
            val channel = store.loadAll("portal").single()
            assertEquals("stalker:stalker1:1", channel.id)
            assertFalse(channel.stalkerDirectStream)
            assertEquals(1, store.count("portal"))
            assertEquals(123L, store.updatedAtMs("portal"))
            val direct = channel.copy(stalkerDirectStream = true)
            store.replaceAll("portal", listOf(direct), 456L)
            assertEquals(direct, store.loadAll("portal").single())
        } finally {
            store.close()
        }
    }
}
