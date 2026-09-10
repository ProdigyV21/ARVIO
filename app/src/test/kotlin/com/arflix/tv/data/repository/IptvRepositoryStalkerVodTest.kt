package com.arflix.tv.data.repository

import com.arflix.tv.data.api.StalkerApi
import com.arflix.tv.data.model.StalkerVodLink
import com.arflix.tv.data.model.isDirectStreamUrl
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Tests for the Stalker VOD movie matching on [IptvRepository]. The matching
 * helpers are `internal` so tests can call them directly, the same convention
 * the Stalker EPG helpers already follow.
 */
class IptvRepositoryStalkerVodTest {

    private fun newRepository(): IptvRepository {
        val context = io.mockk.mockk<android.content.Context>(relaxed = true)
        val okHttpClient = io.mockk.mockk<okhttp3.OkHttpClient>(relaxed = true)
        val profileManager = io.mockk.mockk<ProfileManager>(relaxed = true)
        val invalidationBus = io.mockk.mockk<CloudSyncInvalidationBus>(relaxed = true)
        return IptvRepository(context, okHttpClient, profileManager, invalidationBus)
    }

    private fun item(
        id: String,
        name: String,
        cmd: String = "/media/$id.mpg",
        year: String? = null,
        tmdbId: String? = null
    ) = StalkerApi.StalkerVodItem(id = id, name = name, cmd = cmd, year = year, tmdbId = tmdbId)

    // ── ID matching ───────────────────────────────────────────────────────

    @Test
    fun `a portal supplied tmdb id wins over every title score`() {
        val repository = newRepository()
        val items = listOf(
            item("1", "Dune", year = "2021", tmdbId = "438631"),
            item("2", "Dune", year = "1984", tmdbId = "841")
        )

        val matches = repository.matchStalkerVodItems(
            items = items,
            normalizedTitle = "dune",
            normalizedTmdb = "438631",
            inputYear = 2021
        )

        assertEquals(listOf("1"), matches.map { it.id })
    }

    @Test
    fun `an unmatched tmdb id falls through to title scoring`() {
        val repository = newRepository()
        val items = listOf(item("1", "Dune", year = "2021"))

        val matches = repository.matchStalkerVodItems(
            items = items,
            normalizedTitle = "dune",
            normalizedTmdb = "438631",
            inputYear = 2021
        )

        assertEquals(listOf("1"), matches.map { it.id })
    }

    // ── Title + year fallback ─────────────────────────────────────────────

    @Test
    fun `title and year fallback prefers the matching year`() {
        val repository = newRepository()
        val items = listOf(
            item("old", "Dune", year = "1984"),
            item("new", "Dune", year = "2021")
        )

        val matches = repository.matchStalkerVodItems(
            items = items,
            normalizedTitle = "dune",
            normalizedTmdb = null,
            inputYear = 2021
        )

        assertEquals(listOf("new"), matches.map { it.id })
    }

    @Test
    fun `a language prefixed portal title still matches the tmdb title`() {
        val repository = newRepository()
        val items = listOf(item("1", "DE: Der Herr der Ringe", year = "2001"))

        val matches = repository.matchStalkerVodItems(
            items = items,
            normalizedTitle = IptvTitleNormalizer.normalize("Der Herr der Ringe"),
            normalizedTmdb = null,
            inputYear = 2001
        )

        assertEquals(listOf("1"), matches.map { it.id })
    }

    @Test
    fun `unrelated portal results are dropped instead of guessed`() {
        val repository = newRepository()
        val items = listOf(
            item("1", "Completely Different Show"),
            item("2", "Another Unrelated Title")
        )

        val matches = repository.matchStalkerVodItems(
            items = items,
            normalizedTitle = "dune",
            normalizedTmdb = null,
            inputYear = 2021
        )

        assertTrue(matches.isEmpty())
    }

    @Test
    fun `entries without a playable cmd never become a source`() {
        val repository = newRepository()
        val items = listOf(StalkerApi.StalkerVodItem(id = "1", name = "Dune", cmd = null, year = "2021"))

        val matches = repository.matchStalkerVodItems(
            items = items,
            normalizedTitle = "dune",
            normalizedTmdb = null,
            inputYear = 2021
        )

        assertTrue(matches.isEmpty())
    }

    @Test
    fun `an empty portal answer yields no matches`() {
        val repository = newRepository()

        assertTrue(
            repository.matchStalkerVodItems(
                items = emptyList(),
                normalizedTitle = "dune",
                normalizedTmdb = "438631",
                inputYear = 2021
            ).isEmpty()
        )
    }

    // ── Query planning ────────────────────────────────────────────────────

    @Test
    fun `a subtitled title gets one extra fallback query`() {
        val repository = newRepository()

        assertEquals(
            listOf("Dune: Part Two", "Dune"),
            repository.stalkerVodSearchQueries("Dune: Part Two")
        )
        assertEquals(
            listOf("Mission: Impossible - Dead Reckoning", "Mission"),
            repository.stalkerVodSearchQueries("Mission: Impossible - Dead Reckoning")
        )
    }

    @Test
    fun `a plain title stays a single query`() {
        val repository = newRepository()

        assertEquals(listOf("Heat"), repository.stalkerVodSearchQueries("Heat"))
        assertTrue(repository.stalkerVodSearchQueries("   ").isEmpty())
    }

    @Test
    fun `a too short head is not used as a fallback query`() {
        val repository = newRepository()

        assertEquals(listOf("It: Chapter Two"), repository.stalkerVodSearchQueries("It: Chapter Two"))
    }

    // ── Query planning: the original title (10.09.2026) ───────────────────
    //
    // A portal matches `search` literally against its own catalogue name, and
    // that name is not the name TMDB shows the user. Measured against a real
    // portal: TMDB writes "Der Astronaut – Project Hail Mary" with an en dash,
    // the catalogue lists "DE - Der Astronaut: Project Hail Mary (2026)" with a
    // colon, and the search therefore answered with nothing at all.

    @Test
    fun `the original title leads the term list and dash separators are understood`() {
        val repository = newRepository()

        assertEquals(
            listOf(
                "Project Hail Mary",
                "Der Astronaut – Project Hail Mary",
                "Der Astronaut"
            ),
            repository.stalkerVodSearchQueries(
                title = "Der Astronaut – Project Hail Mary",
                originalTitle = "Project Hail Mary"
            )
        )
    }

    @Test
    fun `an em dash subtitle is split like a colon`() {
        val repository = newRepository()

        assertEquals(
            listOf("Wolfsblut — Ruf der Wildnis", "Wolfsblut"),
            repository.stalkerVodSearchQueries("Wolfsblut — Ruf der Wildnis")
        )
    }

    @Test
    fun `a purely localized title keeps both names as terms`() {
        val repository = newRepository()

        // Neither term can be dropped: the original never appears inside the
        // German name, and a catalogue may carry either one alone.
        assertEquals(
            listOf("The Shawshank Redemption", "Die Verurteilten"),
            repository.stalkerVodSearchQueries(
                title = "Die Verurteilten",
                originalTitle = "The Shawshank Redemption"
            )
        )
    }

    @Test
    fun `a title that is its own original still costs a single query`() {
        val repository = newRepository()

        // The common case must not become more expensive than before.
        assertEquals(
            listOf("Heat"),
            repository.stalkerVodSearchQueries(title = "Heat", originalTitle = "Heat")
        )
        // Spelling alone must not buy a second, identical portal request -
        // a portal search is case-insensitive too.
        assertEquals(
            listOf("HEAT"),
            repository.stalkerVodSearchQueries(title = "heat", originalTitle = "HEAT")
        )
    }

    @Test
    fun `an original title alone is still worth asking for`() {
        val repository = newRepository()

        assertEquals(
            listOf("Heat"),
            repository.stalkerVodSearchQueries(title = "   ", originalTitle = "Heat")
        )
        assertTrue(
            repository.stalkerVodSearchQueries(title = "   ", originalTitle = "  ").isEmpty()
        )
    }

    // ── Matching an entry found through the original title ────────────────

    @Test
    fun `an entry listed only under its original name is matched`() {
        val repository = newRepository()
        val items = listOf(item("1", "EN - The Shawshank Redemption (1994)", year = "1994"))

        val matches = repository.matchStalkerVodItems(
            items = items,
            normalizedTitle = "die verurteilten",
            normalizedTmdb = null,
            inputYear = 1994,
            normalizedOriginalTitle = "the shawshank redemption"
        )

        assertEquals(listOf("1"), matches.map { it.id })
    }

    @Test
    fun `without the original name that same entry stays unmatched`() {
        val repository = newRepository()
        val items = listOf(item("1", "EN - The Shawshank Redemption (1994)", year = "1994"))

        // The counter-proof to the test above: searching for the original
        // title only helps if the match is allowed to use it as well.
        assertTrue(
            repository.matchStalkerVodItems(
                items = items,
                normalizedTitle = "die verurteilten",
                normalizedTmdb = null,
                inputYear = 1994
            ).isEmpty()
        )
    }

    @Test
    fun `a show listed only under its original name is matched too`() {
        val repository = newRepository()
        val shows = listOf(
            StalkerApi.StalkerSeriesItem(
                id = "1",
                name = "EN - Money Heist",
                cmd = "/media/1.mpg",
                year = "2017"
            )
        )

        val matches = repository.matchStalkerSeriesItems(
            items = shows,
            normalizedTitle = "haus des geldes",
            normalizedTmdb = null,
            inputYear = 2017,
            normalizedOriginalTitle = "money heist"
        )

        assertEquals(listOf("1"), matches.map { it.id })
    }

    @Test
    fun `a plain show title is unaffected by the extra term`() {
        val repository = newRepository()

        // Series and movies share stalkerVodSearchQueries, so the series path
        // must stay a single request when both names agree.
        assertEquals(
            listOf("Breaking Bad"),
            repository.stalkerVodSearchQueries(
                title = "Breaking Bad",
                originalTitle = "Breaking Bad"
            )
        )
    }

    // ── Portal isolation (C1) ─────────────────────────────────────────────

    @Test
    fun `two portals sharing an internal id produce different playback markers`() {
        val first = StalkerVodLink.buildMarker("stalker1", "/media/file_1.mpg")
        val second = StalkerVodLink.buildMarker("stalker2", "/media/file_1.mpg")

        assertNotEquals(first, second)
        assertEquals(
            StalkerVodLink.Target("stalker1", "/media/file_1.mpg"),
            StalkerVodLink.parseMarker(first!!)
        )
        assertEquals(
            StalkerVodLink.Target("stalker2", "/media/file_1.mpg"),
            StalkerVodLink.parseMarker(second!!)
        )
    }

    @Test
    fun `markers survive commands with slashes spaces and query parts`() {
        val cmd = "/media/Some Movie (2021)/file?token=a/b&x=1"
        val marker = StalkerVodLink.buildMarker("stalker1", cmd)

        assertTrue(StalkerVodLink.isMarker(marker!!))
        assertEquals(StalkerVodLink.Target("stalker1", cmd), StalkerVodLink.parseMarker(marker))
    }

    @Test
    fun `malformed markers and foreign urls are rejected`() {
        assertNull(StalkerVodLink.parseMarker("https://example.com/movie.mp4"))
        assertNull(StalkerVodLink.parseMarker("stalker_vod://stalker1"))
        assertNull(StalkerVodLink.parseMarker("stalker_vod:///cmd"))
        assertNull(StalkerVodLink.parseMarker("stalker_vod://stalker1/"))
        assertNull(StalkerVodLink.buildMarker("stalker1", "   "))
        assertNull(StalkerVodLink.buildMarker("  ", "/media/a.mpg"))
    }

    @Test
    fun `a placeholder counts as a direct source url`() {
        val marker = StalkerVodLink.buildMarker("stalker1", "/media/1.mpg")!!

        assertTrue(isDirectStreamUrl(marker))
        assertTrue(isDirectStreamUrl("https://example.com/a.mp4"))
        assertFalse(isDirectStreamUrl("magnet:?xt=urn:btih:abc"))
        assertFalse(isDirectStreamUrl(null))
        assertFalse(isDirectStreamUrl("   "))
    }
}
