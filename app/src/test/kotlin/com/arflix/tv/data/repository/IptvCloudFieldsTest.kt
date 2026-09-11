package com.arflix.tv.data.repository

import android.app.Application
import androidx.datastore.preferences.core.mutablePreferencesOf
import androidx.datastore.preferences.core.stringPreferencesKey
import org.json.JSONArray
import org.json.JSONObject
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.ConscryptMode

@RunWith(RobolectricTestRunner::class)
@Config(application = Application::class, manifest = Config.NONE, sdk = [28])
@ConscryptMode(ConscryptMode.Mode.OFF)
class IptvCloudFieldsTest {
    @Test fun newerDeletionWinsDuringPullAndStaleDevicePush() {
        val deleted = payload(listOf("a"), 200)
        val stale = payload(listOf("a", "b"), 100)
        assertTrue(IptvCloudFields.merge(deleted, stale).isEmpty())
        assertEquals("[\"a\"]", IptvCloudFields.value(deleted, "i:p1:playlists").toString())
        assertEquals(setOf("i:p1:playlists"), IptvCloudFields.merge(stale, deleted))
        assertEquals("[\"a\"]", IptvCloudFields.value(stale, "i:p1:playlists").toString())
    }

    @Test fun removingAllListsIsNotTreatedAsMissingData() {
        val local = payload(emptyList(), 200)
        val legacy = payload(listOf("a", "b"), 0)
        IptvCloudFields.merge(local, legacy)
        IptvCloudFields.merge(legacy, local)
        assertEquals("[]", IptvCloudFields.value(legacy, "i:p1:playlists").toString())
    }

    @Test fun genuineLaterReadditionIsAccepted() {
        val local = payload(emptyList(), 200)
        IptvCloudFields.merge(local, payload(listOf("c"), 300))
        assertEquals("[\"c\"]", IptvCloudFields.value(local, "i:p1:playlists").toString())
    }

    @Test fun anotherProfileAndItsPlaybackSessionAreNotOverwritten() {
        val local = payload(listOf("a"), 200)
        local.getJSONObject("iptvByProfile").put("p2", JSONObject().put("playlists", JSONArray().put("second")))
        local.getJSONObject("iptvByProfile").getJSONObject("p1").put("tvSession", JSONObject().put("lastChannelId", "current"))
        IptvCloudFields.merge(local, payload(emptyList(), 300))
        assertEquals("[\"second\"]", IptvCloudFields.value(local, "i:p2:playlists").toString())
        assertEquals("current", local.getJSONObject("iptvByProfile").getJSONObject("p1").getJSONObject("tvSession").getString("lastChannelId"))
        assertFalse(IptvCloudFields.keys(local).any { it.endsWith(":tvSession") })
    }

    @Test fun firstLocalDeletionIsStampedAtomicallyAndClockDoesNotRegress() {
        val prefs = mutablePreferencesOf()
        val empty = JSONObject().put("playlists", JSONArray())
        IptvCloudFields.stamp(prefs, "p1", empty, 200)
        IptvCloudFields.stamp(prefs, "p1", empty, 100)
        assertEquals(201, JSONObject(prefs[stringPreferencesKey("cloud_sync_field_ts")]!!).getInt("i:p1:playlists"))
        assertEquals("[]", JSONObject(prefs[stringPreferencesKey("cloud_sync_field_base")]!!).getString("i:p1:playlists"))
    }

    @Test fun deletionDuringCloudApplyRejectsTheCapturedStaleSnapshot() {
        val prefs = mutablePreferencesOf()
        IptvCloudFields.stamp(prefs, "p1", JSONObject().put("playlists", JSONArray()), 200)
        assertTrue(IptvCloudFields.hasNewerLocalChange(prefs, "p1", JSONObject().put("i:p1:playlists", 100)))
        assertFalse(IptvCloudFields.hasNewerLocalChange(prefs, "p1", JSONObject().put("i:p1:playlists", 200)))
        assertFalse(IptvCloudFields.hasNewerLocalChange(prefs, "p2", JSONObject()))
    }

    @Test fun deletionDuringSnapshotAssemblyKeepsTheValueWithItsNewTimestamp() {
        val snapshot = payload(listOf("a", "b"), 100)
        val prefs = mutablePreferencesOf()
        IptvCloudFields.stamp(prefs, "p1", JSONObject().put("playlists", JSONArray().put("a")), 200)
        IptvCloudFields.reconcileSnapshot(snapshot,
            JSONObject(prefs[stringPreferencesKey("cloud_sync_field_ts")]!!),
            JSONObject(prefs[stringPreferencesKey("cloud_sync_field_base")]!!),
            snapshot.getJSONObject("fieldUpdatedAt"))
        assertEquals("[\"a\"]", IptvCloudFields.value(snapshot, "i:p1:playlists").toString())
    }

    @Test fun unchangedTimestampDoesNotReplaceANewUnstampedPreference() {
        val snapshot = payload(listOf("a"), 200)
        IptvCloudFields.reconcileSnapshot(snapshot, JSONObject().put("i:p1:playlists", 200),
            JSONObject().put("i:p1:playlists", "[\"a\",\"b\"]"), snapshot.getJSONObject("fieldUpdatedAt"))
        assertEquals("[\"a\"]", IptvCloudFields.value(snapshot, "i:p1:playlists").toString())
    }

    private fun payload(ids: List<String>, time: Long) = JSONObject()
        .put("iptvByProfile", JSONObject().put("p1", JSONObject().put("playlists", JSONArray(ids))))
        .put("fieldUpdatedAt", JSONObject().put("i:p1:playlists", time))
}
