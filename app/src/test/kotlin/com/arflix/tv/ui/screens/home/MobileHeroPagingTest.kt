package com.arflix.tv.ui.screens.home

import com.arflix.tv.data.model.Category
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import org.junit.Assert.*
import org.junit.Test

class MobileHeroPagingTest {
    @Test fun loadingRowsDoNotHideAvailableFeatures() {
        val categories = listOf(Category("pending-one", "Loading", emptyList()),
            Category("pending-two", "Loading", emptyList()),
            Category("ready", "Ready", listOf(MediaItem(1, "Ready"))))
        assertEquals(listOf(1), mobileHeroItems(categories).map { it.id })
    }

    @Test fun rowsRemainInterleavedAndDuplicateTitlesAreNotRepeated() {
        val first = (1..7).map { MediaItem(it, "Title $it") }
        val second = listOf(first[0], MediaItem(8, "Other"))
        val items = mobileHeroItems(listOf(Category("one", "One", first), Category("two", "Two", second)))
        assertEquals(listOf(1, 2, 8, 3, 4, 5), items.map { it.id })
    }

    @Test fun movieAndSeriesWithTheSameNumericIdStayDistinct() {
        val items = mobileHeroItems(listOf(Category("one", "One", listOf(
            MediaItem(1, "Film"), MediaItem(1, "Series", mediaType = MediaType.TV)))))
        assertEquals(2, items.size)
    }

    @Test fun watchHistoryAndCollectionsDoNotBecomePromotedFeatures() {
        val item = MediaItem(1, "Not a feature")
        assertTrue(mobileHeroItems(listOf(Category("continue_watching", "Watching", listOf(item)),
            Category("collection_row_one", "Collections", listOf(item)))).isEmpty())
    }

    @Test fun rebasingKeepsTheSelectedTitleWhenTheListGrowsOrShrinks() {
        for (page in listOf(0, 1, 4999, 5000, 5001, 9998, 9999)) {
            for (count in 1..10) for (index in 0 until count) {
                val rebased = mobileHeroPageForIndex(page, count, index)
                assertTrue(rebased in 0 until MobileHeroPageCount)
                assertEquals(index, rebased % count)
                assertTrue(kotlin.math.abs(rebased - page) <= count)
            }
        }
    }

    @Test fun emptyListsUseASafeCentreAndMissingSelectionsUseTheFirstTitle() {
        assertEquals(MobileHeroPageCount / 2, mobileHeroPageForIndex(0, 0, 0))
        assertEquals(0, mobileHeroPageForIndex(5001, 3, -1) % 3)
    }
}
