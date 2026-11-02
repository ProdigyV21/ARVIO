package com.arflix.tv.ui.screens.home

import androidx.compose.runtime.saveable.SaverScope
import com.arflix.tv.data.model.Category
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import org.junit.Assert.*
import org.junit.Test

class NetflixHomeTest {
    @Test fun asyncCatalogRefreshCannotRepickTheBillboardMidVisit() {
        var picks = 0
        val first = stableNetflixBillboard(null, listOf(item(1), item(2))) { picks++; it.first() }!!
        assertEquals(first, stableNetflixBillboard(first, listOf(item(3), item(4))) { picks++; it.last() })
        val enriched = first.copy(overview = "Loaded details")
        assertEquals(enriched, stableNetflixBillboard(first, listOf(enriched)) { picks++; it.first() })
        assertEquals(1, picks)
    }
    @Test fun billboardPrefersTodaysTopTenRows() {
        val rows = listOf(
            row("continue_watching", 1),
            row("trending_movies", 2),
            row("top10_movies_today", 3),
            row("top10_shows_today", 4, mediaType = MediaType.TV),
        )
        assertEquals(listOf(3, 4), candidateIds(rows))
    }

    @Test fun billboardFallsBackToOtherTopTenThenTrending() {
        val otherTopTen = listOf(row("addon_catalog", 5, title = "Top 10 in Israel"), row("trending_tv", 6))
        assertEquals(listOf(5), candidateIds(otherTopTen))
        assertEquals(listOf(6), candidateIds(listOf(row("popular", 7), row("trending_tv", 6))))
    }

    @Test fun homeWithoutTopTenOrTrendingFallsBackToAnyRegularRow() {
        val rows = listOf(
            row("continue_watching", 1),
            Category("collection_row_featured", "Featured", listOf(item(2).copy(status = "collection:featured"))),
            row("addon_catalog", 3, 4),
        )
        val ids = netflixBillboardCandidates(rows) { it.status?.startsWith("collection:") == true }.map { it.id }
        assertEquals(listOf(3, 4), ids)
        assertTrue(candidateIds(listOf(row("continue_watching", 1))).isEmpty())
    }

    @Test fun kidsProfileBillboardFallsBackToKidsRows() {
        val kidsHome = listOf(row("popular", 7), row("kids_shows", 8, mediaType = MediaType.TV))
        assertEquals(listOf(8), candidateIds(kidsHome))
        // A Top 10 title that passed the age filter still wins.
        assertEquals(listOf(9), candidateIds(kidsHome + row("top10_movies_today", 9)))
    }

    @Test fun billboardSkipsTitlesItCannotShow() {
        val rows = listOf(
            Category(
                "top10_movies_today", "Top 10",
                listOf(
                    item(1, backdrop = null),
                    item(2).copy(isPlaceholder = true),
                    item(3).copy(status = "iptv:channel"),
                    item(4),
                    item(4),
                )
            )
        )
        val ids = netflixBillboardCandidates(rows) { it.status?.startsWith("iptv:") == true }.map { it.id }
        assertEquals(listOf(4), ids)
    }

    @Test fun billboardFocusSurvivesSavedState() {
        val state = HomeFocusState().apply { isHeroFocused = true }
        val saved = with(HomeFocusState.Saver) { SaverScope { true }.save(state)!! }
        assertTrue(HomeFocusState.Saver.restore(saved)!!.isHeroFocused)
    }

    @Test fun stateSavedBeforeTheBillboardRestoresUnfocused() {
        val legacy = listOf<Any>(0, 0, 1, 0, "", HashMap<String, Int>(), HashMap<String, String>(), false)
        assertFalse(HomeFocusState.Saver.restore(legacy)!!.isHeroFocused)
    }

    private fun candidateIds(rows: List<Category>) = netflixBillboardCandidates(rows) { false }.map { it.id }

    private fun row(id: String, vararg ids: Int, title: String = id, mediaType: MediaType = MediaType.MOVIE) =
        Category(id, title, ids.map { item(it, mediaType = mediaType) })

    private fun item(id: Int, backdrop: String? = "https://image.example/$id.jpg", mediaType: MediaType = MediaType.MOVIE) =
        MediaItem(id, "Title $id", mediaType = mediaType, backdrop = backdrop)
}
