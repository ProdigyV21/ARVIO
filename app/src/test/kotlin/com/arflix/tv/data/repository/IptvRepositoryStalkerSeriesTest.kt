package com.arflix.tv.data.repository

import com.arflix.tv.data.api.StalkerApi
import com.arflix.tv.data.model.StalkerVodLink
import com.arflix.tv.data.model.isDirectStreamUrl
import com.google.gson.Gson
import com.google.gson.JsonElement
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Tests for the Stalker series/episode resolution on [IptvRepository].
 *
 * Stalker's series model is two-level - show, then season, with the episode as
 * a `create_link` parameter - so the cases here cover the two steps the movie
 * path does not have: picking the right season out of a show, and deciding
 * whether the wanted episode is one the season actually offers.
 */
class IptvRepositoryStalkerSeriesTest {

    private val gson = Gson()

    private fun newRepository(): IptvRepository {
        val context = io.mockk.mockk<android.content.Context>(relaxed = true)
        val okHttpClient = io.mockk.mockk<okhttp3.OkHttpClient>(relaxed = true)
        val profileManager = io.mockk.mockk<ProfileManager>(relaxed = true)
        val invalidationBus = io.mockk.mockk<CloudSyncInvalidationBus>(relaxed = true)
        return IptvRepository(context, okHttpClient, profileManager, invalidationBus)
    }

    private fun show(
        id: String,
        name: String,
        cmd: String = "/media/$id",
        year: String? = null,
        tmdbId: String? = null
    ) = StalkerApi.StalkerSeriesItem(id = id, name = name, cmd = cmd, year = year, tmdbId = tmdbId)

    private fun season(
        id: String,
        name: String,
        episodes: List<Int>? = null,
        cmd: String = "/media/$id"
    ): StalkerApi.StalkerSeriesItem {
        val series: JsonElement? = episodes?.let { gson.toJsonTree(it) }
        return StalkerApi.StalkerSeriesItem(id = id, name = name, cmd = cmd, series = series)
    }

    // ── Show binding ──────────────────────────────────────────────────────

    @Test
    fun `a portal supplied tmdb id binds the show over every title score`() {
        val repository = newRepository()
        val shows = listOf(
            show("1", "Breaking Bad", year = "2008", tmdbId = "1396"),
            show("2", "Breaking Bad", year = "2008", tmdbId = "999")
        )

        val matches = repository.matchStalkerSeriesItems(
            items = shows,
            normalizedTitle = "breaking bad",
            normalizedTmdb = "1396",
            inputYear = 2008
        )

        assertEquals(listOf("1"), matches.map { it.id })
    }

    @Test
    fun `without an id the show is bound by title and year`() {
        val repository = newRepository()
        val shows = listOf(
            show("old", "Battlestar Galactica", year = "1978"),
            show("new", "Battlestar Galactica", year = "2004")
        )

        val matches = repository.matchStalkerSeriesItems(
            items = shows,
            normalizedTitle = IptvTitleNormalizer.normalize("Battlestar Galactica"),
            normalizedTmdb = null,
            inputYear = 2004
        )

        assertEquals(listOf("new"), matches.map { it.id })
    }

    @Test
    fun `a language prefixed show title still binds`() {
        val repository = newRepository()
        val shows = listOf(show("1", "DE: Das Rad der Zeit", year = "2021"))

        val matches = repository.matchStalkerSeriesItems(
            items = shows,
            normalizedTitle = IptvTitleNormalizer.normalize("Das Rad der Zeit"),
            normalizedTmdb = null,
            inputYear = 2021
        )

        assertEquals(listOf("1"), matches.map { it.id })
    }

    @Test
    fun `unrelated shows are dropped instead of guessed`() {
        val repository = newRepository()
        val shows = listOf(show("1", "Some Other Show"), show("2", "Yet Another One"))

        val matches = repository.matchStalkerSeriesItems(
            items = shows,
            normalizedTitle = "breaking bad",
            normalizedTmdb = null,
            inputYear = 2008
        )

        assertTrue(matches.isEmpty())
    }

    @Test
    fun `a show without a cmd never binds`() {
        val repository = newRepository()
        val shows = listOf(StalkerApi.StalkerSeriesItem(id = "1", name = "Breaking Bad", cmd = null))

        val matches = repository.matchStalkerSeriesItems(
            items = shows,
            normalizedTitle = "breaking bad",
            normalizedTmdb = null,
            inputYear = null
        )

        assertTrue(matches.isEmpty())
    }

    // ── Season selection ──────────────────────────────────────────────────

    @Test
    fun `the season is read from its name, whatever the portal calls it`() {
        val repository = newRepository()

        assertEquals(2, repository.parseStalkerSeasonNumber("Season 2"))
        assertEquals(2, repository.parseStalkerSeasonNumber("Staffel 2"))
        assertEquals(2, repository.parseStalkerSeasonNumber("SAISON 2"))
        assertEquals(2, repository.parseStalkerSeasonNumber("Temporada 2"))
        assertEquals(2, repository.parseStalkerSeasonNumber("S02"))
        assertEquals(2, repository.parseStalkerSeasonNumber("2. Staffel"))
        assertEquals(2, repository.parseStalkerSeasonNumber("  2  "))
        assertEquals(12, repository.parseStalkerSeasonNumber("Season 12"))
    }

    @Test
    fun `a name that only looks like a number is not read as a season`() {
        val repository = newRepository()

        // The trailing year must not become a season number - this is why the
        // parser is anchored instead of taking the last number it can find.
        assertNull(repository.parseStalkerSeasonNumber("Stranger Things 1983"))
        assertNull(repository.parseStalkerSeasonNumber("Extras"))
        assertNull(repository.parseStalkerSeasonNumber(""))
        assertNull(repository.parseStalkerSeasonNumber(null))
    }

    @Test
    fun `the named season wins over its position in the list`() {
        val repository = newRepository()
        // A portal that leads with an extras season: taking position 2 would
        // hand back season 1's cmd.
        val seasons = listOf(
            season("s0", "Season 0", listOf(1)),
            season("s1", "Season 1", listOf(1, 2)),
            season("s2", "Season 2", listOf(1, 2, 3))
        )

        assertEquals("s2", repository.selectStalkerSeason(seasons, 2)?.id)
        assertEquals("s1", repository.selectStalkerSeason(seasons, 1)?.id)
    }

    @Test
    fun `position is the fallback when no entry names a season`() {
        val repository = newRepository()
        // Unparseable names, but each entry reports its episodes - so these are
        // seasons in season order and position is safe to count on.
        val unnamed = listOf(
            season("a", "Erste", listOf(1, 2)),
            season("b", "Zweite", listOf(1, 2, 3)),
            season("c", "Dritte", listOf(1))
        )

        assertEquals("b", repository.selectStalkerSeason(unnamed, 2)?.id)
        assertNull(repository.selectStalkerSeason(unnamed, 9))
    }

    @Test
    fun `a portal answering with episodes instead of seasons yields nothing`() {
        val repository = newRepository()
        // No season number in any name and no episode lists either: this is the
        // shape a portal returns when movie_id lists episodes. Counting
        // positions here would play episode 2 for season 2.
        val episodes = listOf(
            season("e1", "Pilot"),
            season("e2", "Cat's in the Bag..."),
            season("e3", "...And the Bag's in the River")
        )

        assertNull(repository.selectStalkerSeason(episodes, 2))
    }

    @Test
    fun `a season the show does not have yields nothing`() {
        val repository = newRepository()
        val seasons = listOf(season("s1", "Season 1", listOf(1, 2)))

        assertNull(repository.selectStalkerSeason(seasons, 4))
        assertNull(repository.selectStalkerSeason(emptyList(), 1))
        assertNull(repository.selectStalkerSeason(seasons, 0))
    }

    // ── Episode availability ──────────────────────────────────────────────

    @Test
    fun `the season reports which episodes it holds`() {
        val entry = season("s2", "Season 2", listOf(1, 2, 3))

        val episodes = StalkerApi.episodeNumbers(entry.series)

        assertTrue(2 in episodes)
        assertFalse(9 in episodes)
    }

    @Test
    fun `a season that reports no episode list is not treated as empty`() {
        // An absent `series` field means "the portal does not say", not "no
        // episodes" - the lookup has to go ahead and let create_link decide.
        assertTrue(StalkerApi.episodeNumbers(season("s1", "Season 1").series).isEmpty())
    }

    // ── Playback markers ──────────────────────────────────────────────────

    @Test
    fun `an episode marker carries the episode number through to playback`() {
        val marker = StalkerVodLink.buildMarker("stalker1", "/media/bb/s2", 5)!!

        assertEquals(
            StalkerVodLink.Target("stalker1", "/media/bb/s2", 5),
            StalkerVodLink.parseMarker(marker)
        )
        // Same season, different episode: two distinct sources, never one.
        assertNotEquals(marker, StalkerVodLink.buildMarker("stalker1", "/media/bb/s2", 6))
    }

    @Test
    fun `a movie marker stays free of an episode number`() {
        val marker = StalkerVodLink.buildMarker("stalker1", "/media/dune.mpg")!!

        assertNull(StalkerVodLink.parseMarker(marker)?.series)
    }

    @Test
    fun `episode markers survive commands with query parts of their own`() {
        val cmd = "/media/Some Show (2021)/s2?token=a/b&x=1"
        val marker = StalkerVodLink.buildMarker("stalker1", cmd, 3)!!

        assertEquals(StalkerVodLink.Target("stalker1", cmd, 3), StalkerVodLink.parseMarker(marker))
    }

    @Test
    fun `two portals holding the same show produce different episode markers`() {
        val first = StalkerVodLink.buildMarker("stalker1", "/media/s2", 5)
        val second = StalkerVodLink.buildMarker("stalker2", "/media/s2", 5)

        assertNotEquals(first, second)
        assertEquals("stalker1", StalkerVodLink.parseMarker(first!!)?.portalId)
        assertEquals("stalker2", StalkerVodLink.parseMarker(second!!)?.portalId)
    }

    @Test
    fun `a malformed episode number invalidates the marker`() {
        assertNull(StalkerVodLink.buildMarker("stalker1", "/media/s2", 0))
        assertNull(StalkerVodLink.buildMarker("stalker1", "/media/s2", -1))
        assertNull(StalkerVodLink.parseMarker("stalker_vod://stalker1/%2Fs2?series=abc"))
        assertNull(StalkerVodLink.parseMarker("stalker_vod://stalker1/%2Fs2?series=0"))
    }

    @Test
    fun `an episode placeholder counts as a direct source url`() {
        // Autoplay and the source ordering both filter on "direct url"; an
        // episode has to pass that check for the same reason a movie does.
        val marker = StalkerVodLink.buildMarker("stalker1", "/media/s2", 5)!!

        assertTrue(isDirectStreamUrl(marker))
    }
}
