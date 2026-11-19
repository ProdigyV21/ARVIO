package com.arflix.tv.data.repository.wetrakr

import com.arflix.tv.data.model.MediaType
import com.google.gson.JsonParser
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class WeTrakrRemoteProviderTest {
    private fun row(json: String) = JsonParser.parseString(json).asJsonObject
    private val api = mockk<WeTrakrRepository>(relaxed = true)
    private val provider = WeTrakrRemoteProvider(api)

    @Test fun episodeIdentityUsesParentTmdbNotProviderId() {
        assertEquals("show_tmdb:1396:1:2", weTrakrEpisodeKey(row("""{"id":999,"show":{"id":1,"ids":{"tmdb":1396}},"season_number":1,"number":2}""")))
        assertNull(weTrakrEpisodeKey(row("""{"show":{"id":1396},"season_number":1,"number":2}""")))
        assertNull(weTrakrEpisodeKey(row("""{"show":{"ids":{"tmdb":1396}},"season_number":1,"number":0}""")))
    }
    @Test fun episodeWriteDoesNotMarkTheWholeShowWatched() {
        val body = row(com.google.gson.Gson().toJson(weTrakrBody(MediaType.TV, 1396, "watched", 1, 2)))
        val show = body.getAsJsonArray("shows")[0].asJsonObject
        assertEquals(1396, show.obj("ids").int("tmdb"))
        assertFalse(show.has("id"))
        assertEquals(2, show.getAsJsonArray("seasons")[0].asJsonObject.getAsJsonArray("episodes")[0].asJsonObject.int("number"))
        assertFalse(body.get("allow_rewatch").asBoolean)
    }
    @Test fun rejectedItemsAreNotSuccessfulWrites() {
        assertFalse(weTrakrWriteSucceeded(row("""{"notFound":{"movies":[{"ids":{"tmdb":1}}]}}""")))
        assertFalse(weTrakrWriteSucceeded(row("""{"errored":{"episodes":[{"error":"Invalid episode"}]}}""")))
        assertTrue(weTrakrWriteSucceeded(row("""{"errored":{"episodes":[{"error":"Already watched!"}]},"notFound":{"movies":[]}}""")))
    }
    @Test fun watchedResumeIsRemovedAndOnlyReportedAiredNextEpisodeAppears() = runBlocking {
        every { api.profileId() } returns "profile"
        coEvery { api.list(any(), any(), any(), "profile") } returns emptyList()
        coEvery { api.list("watched", "episodes", any(), "profile") } returns listOf(row("""{"show":{"ids":{"tmdb":1396}},"season_number":1,"number":2}"""))
        coEvery { api.list("playing", "all", any(), "profile") } returns listOf(row("""{"type":"episode","show":{"ids":{"tmdb":1396},"title":"Example"},"season_number":1,"number":2,"playback":{"progress_percent":30}}"""))
        coEvery { api.list("watching", "shows", any(), "profile") } returns listOf(
            row("""{"ids":{"tmdb":1396},"title":"Example","next_watching_episode":{"season_number":1,"number":3,"air_date":"2020-01-01"}}"""),
            row("""{"ids":{"tmdb":2},"title":"Finished"}"""),
            row("""{"ids":{"tmdb":3},"next_watching_episode":{"season_number":2,"number":1,"air_date":"2099-01-01"}}"""))
        val result = provider.getContinueWatching(true)
        assertEquals(1, result.size); assertEquals(3, result.single().episode); assertTrue(result.single().isUpNext)
    }
    @Test fun cancellationSpecifiesTheTitleAndNeverClearsAnotherPlaybackSession() = runBlocking {
        coEvery { api.request(any(), any(), any(), any()) } returns (row("""{"action":"cancel"}""") to emptyMap())
        provider.dismissContinueWatching(MediaType.TV, 1396, 1, 2)
        coVerify { api.request("/scrobble/playing", "DELETE", weTrakrPlaybackRef(MediaType.TV, 1396, 1, 2), any()) }
    }
}
