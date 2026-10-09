package com.arflix.tv.navigation

import com.arflix.tv.data.api.TmdbFindEpisode
import com.arflix.tv.data.api.TmdbFindItem
import com.arflix.tv.data.api.TmdbFindResponse
import com.arflix.tv.data.model.MediaType
import com.google.gson.Gson
import java.io.IOException
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test

class PartnerOpenLinkTest {
    @Test fun `new partner episode opens fresh details instead of restoring old playback target`() {
        val options = partnerOpenNavigationOptions()
        assertFalse(options.shouldLaunchSingleTop())
        assertFalse(options.shouldRestoreState())
        assertEquals("details/tv/1399?initialSeason=0&initialEpisode=3",
            Screen.Details.createRoute(MediaType.TV, 1399, 0, 3))
    }

    @Test fun `canonical IMDb and TMDB links parse and round trip`() {
        listOf(
            "arvio://open?imdb=tt0137523" to PartnerOpenRequest(imdbId = "tt0137523"),
            "arvio://open?type=movie&id=550" to PartnerOpenRequest(mediaType = MediaType.MOVIE, tmdbId = 550),
            "arvio://open?type=tv&id=1399&season=0&episode=1" to
                PartnerOpenRequest(mediaType = MediaType.TV, tmdbId = 1399, season = 0, episode = 1),
            "arvio://open?type=tv&id=2147483647&season=10000&episode=10000" to
                PartnerOpenRequest(mediaType = MediaType.TV, tmdbId = Int.MAX_VALUE, season = 10000, episode = 10000),
            "arvio://open/?imdb=tt0137523&season=&episode=" to PartnerOpenRequest(imdbId = "tt0137523")
        ).forEach { (uri, request) ->
            assertEquals(uri, request, PartnerOpenLinkParser.parse(uri))
            assertEquals(request, PartnerOpenLinkParser.parse(request.canonicalUri()))
        }
    }

    @Test fun `optional blank templates and season only TV links are supported`() {
        assertEquals(PartnerOpenRequest(mediaType = MediaType.TV, tmdbId = 1399),
            PartnerOpenLinkParser.parse("arvio://open?type=tv&id=1399&season=&episode="))
        assertEquals(PartnerOpenRequest(mediaType = MediaType.TV, tmdbId = 1399, season = 2),
            PartnerOpenLinkParser.parse("arvio://open?type=tv&id=1399&season=2"))
        assertEquals(PartnerOpenRequest(mediaType = MediaType.MOVIE, tmdbId = 550),
            PartnerOpenLinkParser.parse("arvio://open?type=movie&id=550&season=&episode="))
    }

    @Test fun `untrusted origins routes actions and duplicate keys are rejected`() {
        listOf(
            "https://arvio.tv/open?imdb=tt0137523", "http://open?imdb=tt0137523",
            "ARVIO://open?imdb=tt0137523", "arvio://OPEN?imdb=tt0137523",
            "arvio://open.evil?imdb=tt0137523", "arvio://evil@open?imdb=tt0137523",
            "arvio://open:80?imdb=tt0137523", "arvio://open/title?imdb=tt0137523",
            "arvio://open?imdb=tt0137523#play", "arvio://open?imdb=tt0137523&autoplay=true",
            "arvio://open?imdb=tt0137523&url=https://evil.test/video", "arvio://open?imdb=tt0137523&utm_source=simkl",
            "arvio://open?imdb=tt0137523&imdb=tt0137523", "arvio://open?imdb=tt0137523&%69mdb=tt1234567",
            "arvio://open?type=movie&id=550&id=551", "arvio://open?imdb=tt0137523&type=movie&id=550",
            "arvio://open?imdb=tt0137523&type=", "arvio://open?imdb=tt0137523&",
            "arvio://open?imdb=tt0137523&season=1&season=2", "arvio://open?imdb=tt0137523&%ZZ=x"
        ).forEach { assertNull(it, PartnerOpenLinkParser.parse(it)) }
    }

    @Test fun `invalid identifiers and episode coordinates are rejected`() {
        listOf(
            "", "arvio://open", "arvio://open?", "arvio://open?imdb=",
            "arvio://open?imdb=TT0137523", "arvio://open?imdb=tt1234", "arvio://open?imdb=tt1234567890123",
            "arvio://open?imdb=tt0137523suffix", "arvio://open?type=series&id=1399", "arvio://open?type=tv",
            "arvio://open?type=movie&id=0", "arvio://open?type=movie&id=-1",
            "arvio://open?type=movie&id=2147483648", "arvio://open?type=movie&id=1.0",
            "arvio://open?type=movie&id=0550", "arvio://open?type=tv&id=1399&season=01",
            "arvio://open?type=tv&id=1399&season=0&episode=01", "arvio://open?type=tv&id=1399&season=%20&episode=",
            "arvio://open?type=tv&id=1399&season=&episode=%20",
            "arvio://open?type=movie&id=+550", "arvio://open?type=movie&id=%20550",
            "arvio://open?type=tv&id=1399&episode=1", "arvio://open?type=tv&id=1399&season=-1",
            "arvio://open?type=tv&id=1399&season=1&episode=0", "arvio://open?type=tv&id=1399&season=10001",
            "arvio://open?type=tv&id=1399&season=1&episode=10001", "arvio://open?type=movie&id=550&season=1",
            "arvio://open?type=movie&id=550&season=1&episode=2"
        ).forEach { assertNull(it, PartnerOpenLinkParser.parse(it)) }
        assertNull(PartnerOpenLinkParser.parse(null))
        assertNull(PartnerOpenLinkParser.parse("arvio://open?imdb=tt0137523&" + "x".repeat(512)))
    }

    @Test fun `TMDB title resolution requires no network call`() = runTest {
        val target = resolvePartnerOpenRequest(request("arvio://open?type=tv&id=1399&season=2&episode=3")) {
            error("TMDB-ID links must not perform an IMDb lookup")
        }
        assertEquals(PartnerOpenTarget(MediaType.TV, 1399, 2, 3), target)
    }

    @Test fun `IMDb movie and show resolve to details and movies reject supplied episodes`() = runTest {
        val movie = TmdbFindResponse(movieResults = listOf(TmdbFindItem(id = 550)))
        assertEquals(PartnerOpenTarget(MediaType.MOVIE, 550),
            resolvePartnerOpenRequest(request("arvio://open?imdb=tt0137523")) { movie })
        assertNull(resolvePartnerOpenRequest(request("arvio://open?imdb=tt0137523&season=1&episode=1")) { movie })
        assertEquals(PartnerOpenTarget(MediaType.TV, 1399, 2, 3),
            resolvePartnerOpenRequest(request("arvio://open?imdb=tt0944947&season=2&episode=3")) {
                TmdbFindResponse(tvResults = listOf(TmdbFindItem(id = 1399)))
            })
    }

    @Test fun `episode IMDb result opens parent series and preserves specials season zero`() = runTest {
        val episode = TmdbFindResponse(tvEpisodeResults = listOf(TmdbFindEpisode(1399, 0, 1)))
        assertEquals(PartnerOpenTarget(MediaType.TV, 1399, 0, 1),
            resolvePartnerOpenRequest(request("arvio://open?imdb=tt1234567")) { episode })
        assertEquals(PartnerOpenTarget(MediaType.TV, 1399, 0, 1),
            resolvePartnerOpenRequest(request("arvio://open?imdb=tt1234567&season=0&episode=1")) { episode })
        assertNull(resolvePartnerOpenRequest(request("arvio://open?imdb=tt1234567&season=1&episode=1")) { episode })
        assertNull(resolvePartnerOpenRequest(request("arvio://open?imdb=tt1234567&season=0&episode=2")) { episode })
    }

    @Test fun `TMDB episode JSON reads parent show rather than episode ID`() {
        val result = Gson().fromJson("""{"movie_results":[],"tv_results":[],"tv_episode_results":[{"id":777,"show_id":1399,"season_number":0,"episode_number":1}]}""", TmdbFindResponse::class.java)
        assertEquals(listOf(TmdbFindEpisode(1399, 0, 1)), result.tvEpisodeResults)
    }

    @Test fun `empty invalid and ambiguous lookups never guess a title`() = runTest {
        listOf(
            TmdbFindResponse(),
            TmdbFindResponse(movieResults = listOf(TmdbFindItem(id = -1))),
            TmdbFindResponse(movieResults = listOf(TmdbFindItem(id = 550), TmdbFindItem(id = 551))),
            TmdbFindResponse(movieResults = listOf(TmdbFindItem(id = 550)), tvResults = listOf(TmdbFindItem(id = 1399))),
            TmdbFindResponse(tvEpisodeResults = listOf(TmdbFindEpisode(showId = 1399, seasonNumber = 0, episodeNumber = 0)))
        ).forEach { response ->
            assertNull(resolvePartnerOpenRequest(request("arvio://open?imdb=tt0137523")) { response })
        }
        assertNull(resolvePartnerOpenRequest(PartnerOpenRequest(mediaType = MediaType.MOVIE, tmdbId = 0)) { error("invalid request") })
    }

    @Test fun `lookup failures and cancellation propagate so caller can retry safely`() = runTest {
        val link = request("arvio://open?imdb=tt0137523")
        assertTrue(runCatching { resolvePartnerOpenRequest(link) { throw IOException("offline") } }.exceptionOrNull() is IOException)
        assertTrue(runCatching { resolvePartnerOpenRequest(link) { throw CancellationException("new intent") } }.exceptionOrNull() is CancellationException)
        val lookupStarted = CompletableDeferred<Unit>()
        var delivered = false
        val job = launch {
            resolvePartnerOpenRequest(link) {
                lookupStarted.complete(Unit)
                CompletableDeferred<TmdbFindResponse>().await()
            }
            delivered = true
        }
        lookupStarted.await()
        job.cancelAndJoin()
        assertFalse(delivered)
    }

    @Test fun `pending request restores through startup and consumed link never replays`() {
        val coldStart = PartnerOpenLinkInbox { "cold" }
        coldStart.replace(request("arvio://open?imdb=tt0137523"))
        val selectedLater = PartnerOpenLinkInbox { "restored" }
        selectedLater.restore(coldStart.pending!!.request.canonicalUri(), coldStart.pending!!.ticket)
        assertEquals(coldStart.pending, selectedLater.pending)
        assertTrue(selectedLater.consume(selectedLater.pending!!))
        assertNull(selectedLater.pending)
        val recreated = PartnerOpenLinkInbox()
        recreated.restore(selectedLater.pending?.request?.canonicalUri(), selectedLater.pending?.ticket)
        assertNull(recreated.pending)
        assertFalse(selectedLater.consume(coldStart.pending!!))
    }

    @Test fun `new warm intent including same title supersedes stale result or retry`() {
        var sequence = 0
        val inbox = PartnerOpenLinkInbox { (++sequence).toString() }
        val title = request("arvio://open?imdb=tt0137523")
        inbox.replace(title)
        val first = inbox.pending!!
        inbox.replace(title)
        val second = inbox.pending!!
        assertNotEquals(first.ticket, second.ticket)
        assertFalse(inbox.consume(first))
        assertEquals(second, inbox.pending)
        inbox.replace(request("arvio://open?type=tv&id=1399"))
        assertFalse(inbox.consume(second))
        val latest = inbox.pending!!
        assertTrue(inbox.consume(latest))
        assertFalse(inbox.consume(latest))
        inbox.replace(title)
        val cancelled = inbox.pending!!
        inbox.replace(null)
        assertFalse(inbox.consume(cancelled))
    }

    private fun request(uri: String) = requireNotNull(PartnerOpenLinkParser.parse(uri))
}
