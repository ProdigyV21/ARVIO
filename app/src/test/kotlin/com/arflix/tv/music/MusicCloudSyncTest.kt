package com.arflix.tv.music

import com.arflix.tv.data.repository.CloudSyncRepository.CloudProfileSettings
import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNull
import org.junit.Test

class MusicCloudSyncTest {
    private val gson = Gson()

    @Test fun musicTravelsWithTheProfileSettings() {
        val collections = MusicCollections()
            .toggleFavorite(MusicTrack("server~t", "Song", "Band", "LP", "server~a", 200, null, "http://nas/rest/stream?id=t"))
            .createPlaylist("p", "Gym")
        val addons = listOf(MusicAddon("https://a.example/tok", "com.example", "Example", protocol = EclipseAddonSource.PROTOCOL))
        val sent = CloudProfileSettings(musicAddonsJson = gson.toJson(addons), musicCollectionsJson = gson.toJson(collections))
        val received = gson.fromJson(gson.toJson(sent), CloudProfileSettings::class.java)
        assertEquals(collections, gson.fromJson(received.musicCollectionsJson, MusicCollections::class.java).sanitized())
        assertEquals(addons, gson.fromJson(received.musicAddonsJson, Array<MusicAddon>::class.java).toList())
    }

    @Test fun tasteTravelsWithTheProfileSettings() {
        val taste = MusicTaste().recordPlay("Band", 5L).recordSearch("top gym hits", 6L)
        val sent = CloudProfileSettings(musicTasteJson = gson.toJson(taste))
        val received = gson.fromJson(gson.toJson(sent), CloudProfileSettings::class.java)
        assertEquals(taste, gson.fromJson(received.musicTasteJson, MusicTaste::class.java).sanitized())
        assertNull(gson.fromJson("""{"showBudget":true}""", CloudProfileSettings::class.java).musicTasteJson)
        assertEquals("profile_a_music_taste_v1", MusicCloudKeys.taste("a").name)
    }

    @Test fun olderClientsWithoutMusicDoNotClearIt() {
        val old = gson.fromJson("""{"showBudget":true}""", CloudProfileSettings::class.java)
        assertNull(old.musicAddonsJson)
        assertNull(old.musicCollectionsJson)
    }

    @Test fun eachProfileHasItsOwnKeys() {
        assertNotEquals(MusicCloudKeys.collections("a"), MusicCloudKeys.collections("b"))
        assertEquals("profile_a_music_addons_v1", MusicCloudKeys.addons("a").name)
    }
}
