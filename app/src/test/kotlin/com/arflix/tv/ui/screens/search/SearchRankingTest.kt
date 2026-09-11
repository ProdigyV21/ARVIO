package com.arflix.tv.ui.screens.search

import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import java.util.Locale
import org.junit.Assert.*
import org.junit.Test

class SearchRankingTest {
    @Test fun exactSeriesBeatsPartialMovieMatch() {
        val film = item(1, "Loki: The Making Of")
        val show = item(2, "Loki", MediaType.TV)
        assertEquals(listOf(show, film), rankSearchResults("loki", listOf(film, show)))
    }

    @Test fun equivalentMatchesKeepProviderRelevanceNotYearOrPopularityHeuristics() {
        val items = listOf(item(1, "The Office").copy(year = "2005", popularity = 2f),
            item(2, "The Office").copy(year = "2024", popularity = 20f))
        assertEquals(items, rankSearchResults("The Office", items))
    }

    @Test fun punctuationAccentsAndArticlesAreHandled() {
        val items = listOf(item(1, "Amelie Returns"), item(2, "Am\u00e9lie"))
        assertEquals(2, rankSearchResults("AMELIE", items).first().id)
        assertEquals(2, rankSearchResults("office", listOf(item(1, "Office Space"), item(2, "The Office"))).first().id)
        assertEquals(2, rankSearchResults("law and order", listOf(item(1, "Order"), item(2, "Law & Order"))).first().id)
        assertEquals(2, rankSearchResults("Schindler's List", listOf(item(1, "List"), item(2, "Schindler\u2019s List"))).first().id)
    }

    @Test fun wordMatchesBeatSubstrings() {
        assertEquals(listOf(3, 2, 1), rankSearchResults("it", listOf(item(1, "Little"), item(2, "It Follows"), item(3, "It"))).map { it.id })
    }

    @Test fun unicodeTitlesAndTurkishLocaleDoNotBreakMatching() {
        val original = Locale.getDefault()
        try {
            Locale.setDefault(Locale.forLanguageTag("tr-TR"))
            assertEquals(2, rankSearchResults("IT", listOf(item(1, "It Follows"), item(2, "It"))).first().id)
            assertEquals(2, rankSearchResults("\u9032\u6483", listOf(item(1, "Other"), item(2, "\u9032\u6483"))).first().id)
        } finally { Locale.setDefault(original) }
    }

    @Test fun deduplicationKeepsMovieAndShowWithSameNumericId() {
        val movie = item(1, "Test")
        val show = item(1, "Test", MediaType.TV)
        assertEquals(listOf(movie, show), rankSearchResults("test", listOf(movie, movie, show)))
    }

    @Test fun titlesDoNotAccidentallyTriggerSmartDiscovery() {
        listOf("Best in Show", "New Police Story", "The New Adventures of Old Christine", "Top Boy", "Popular", "New Girl")
            .forEach { assertFalse(it, isSmartDiscoveryQuery(it)) }
    }

    @Test fun explicitDiscoveryAndSimilarQueriesStillWork() {
        listOf("best comedy movies", "top 10 movies", "latest tv shows", "popular anime", "movies like Alien")
            .forEach { assertTrue(it, isSmartDiscoveryQuery(it)) }
    }

    private fun item(id: Int, title: String, type: MediaType = MediaType.MOVIE) = MediaItem(id = id, title = title, mediaType = type)
}
