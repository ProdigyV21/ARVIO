package com.arflix.tv.data.repository

import com.arflix.tv.data.model.MediaType
import com.google.gson.Gson
import org.junit.Assert.*
import org.junit.Test

class ContinueWatchingCacheDecoderTest {
    private val gson = Gson()

    @Test fun invalidEntriesDoNotDiscardValidNeighbours() {
        val items = decodeContinueWatchingCache("""[
            null,
            {"id":1,"title":"Missing type"},
            {"id":2,"title":"Null type","mediaType":null},
            {"id":3,"title":"Unknown type","mediaType":"future-type"},
            {"id":4,"title":null,"mediaType":"MOVIE"},
            {"id":5,"title":"Valid","mediaType":"TV","season":2,"episode":3},
            {"id":"invalid","title":"Bad id","mediaType":"MOVIE"}
        ]""", gson)
        assertEquals(listOf(5), items.map { it.id })
        assertEquals("TV", items.single().mediaType.name)
        assertEquals("", items.single().duration)
        assertEquals("", items.single().overview)
        assertEquals(3, items.single().toMediaItem().nextEpisode?.episodeNumber)
    }

    @Test fun sparseLegacyAndProviderItemsKeepIdentityAndResumePosition() {
        val items = decodeContinueWatchingCache("""[
            {"id":-14,"title":"Provider movie","mediaType":"MOVIE","progress":30,"resumePositionSeconds":300,"streamKey":"iptv:14"},
            {"id":14,"title":"Movie","mediaType":"MOVIE"},
            {"id":14,"title":"Show","mediaType":"TV"},
            {"id":14,"title":"Duplicate movie","mediaType":"MOVIE"}
        ]""", gson)
        assertEquals(3, items.size)
        assertEquals(-14, items.first().id)
        assertEquals("iptv:14", items.first().streamKey)
        assertEquals(300L, items.first().resumePositionSeconds)
        assertEquals(MediaType.TV, items.last().mediaType)
    }

    @Test fun normalEntriesRoundTripUnchanged() {
        val original = ContinueWatchingItem(12, "Movie", MediaType.MOVIE, 20, durationSeconds = 1_000)
        assertEquals(listOf(original), decodeContinueWatchingCache(gson.toJson(listOf(original)), gson))
    }

    @Test fun webCloudAndAndroidUseCompatibleMediaTypes() {
        val items = decodeContinueWatchingCache("""[
            {"id":12,"title":"Web movie","mediaType":"movie","resumePositionSeconds":90},
            {"id":13,"title":"Web show","mediaType":"tv","season":1,"episode":2}
        ]""", gson)
        assertEquals(listOf(MediaType.MOVIE, MediaType.TV), items.map { it.mediaType })
        assertEquals(90L, items.first().resumePositionSeconds)
        assertEquals("\"MOVIE\"", gson.toJson(MediaType.MOVIE))
        assertEquals("\"TV\"", gson.toJson(MediaType.TV))
        // CloudSyncRepository also decodes these objects directly, before persistence.
        assertEquals(MediaType.TV, gson.fromJson("\"tv\"", MediaType::class.java))
    }

    @Test fun invalidDocumentsAreSafe() {
        listOf("", "null", "{}", "[", "[null]", "[{}]").forEach {
            assertTrue(decodeContinueWatchingCache(it, gson).isEmpty())
        }
    }
}
