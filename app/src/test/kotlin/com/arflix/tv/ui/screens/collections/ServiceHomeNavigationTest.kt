package com.arflix.tv.ui.screens.collections

import com.arflix.tv.data.model.Category
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.ui.screens.home.HomeFocusState
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ServiceHomeNavigationTest {
    private fun item(id: Int) = MediaItem(id = id, title = "Title $id", mediaType = MediaType.MOVIE)
    private val billboard = item(1)
    private val rows = listOf(
        Category("ranked_top10", "Top", (1..10).map { item(100 + it) }),
        Category("new", "New", (1..20).map { item(200 + it) }),
    )
    private val hasMore = mapOf("ranked_top10" to false, "new" to true)

    private fun state() = HomeFocusState().apply { isHeroFocused = true }

    private fun HomeFocusState.press(key: ServiceHomeKey, rtl: Boolean = false, withBillboard: Boolean = true) =
        handleServiceHomeKey(key, this, rows, hasMore, billboard.takeIf { withBillboard }, rtl, now = 0L)

    @Test fun `the big card plays or opens, and Back leaves the page`() {
        val focus = state()
        assertEquals(ServiceHomeAction.Open(billboard, play = true), focus.press(ServiceHomeKey.SELECT))
        focus.press(ServiceHomeKey.RIGHT)
        assertEquals(ServiceHomeAction.Open(billboard, play = false), focus.press(ServiceHomeKey.SELECT))
        assertEquals(ServiceHomeAction.Exit, focus.press(ServiceHomeKey.BACK))
    }

    @Test fun `Up from the big card reaches the filter buttons, starting on the applied one`() {
        val focus = state()
        handleServiceHomeKey(ServiceHomeKey.UP, focus, rows, hasMore, billboard, false, 0L, ServiceHome.Filter.MOVIES)
        assertTrue(focus.isSidebarFocused)
        assertEquals(ServiceHome.Filter.MOVIES.ordinal, focus.sidebarFocusIndex)
        focus.press(ServiceHomeKey.LEFT)
        assertEquals(ServiceHomeAction.Filter(ServiceHome.Filter.SERIES), focus.press(ServiceHomeKey.SELECT))
        focus.press(ServiceHomeKey.LEFT)
        focus.press(ServiceHomeKey.LEFT)
        assertEquals(ServiceHomeAction.Filter(ServiceHome.Filter.ALL), focus.press(ServiceHomeKey.SELECT))
        focus.press(ServiceHomeKey.DOWN)
        assertFalse(focus.isSidebarFocused)
        assertTrue(focus.isHeroFocused)
    }

    @Test fun `the Filter button after Movies opens the grid, and focus stops there`() {
        val focus = state()
        focus.press(ServiceHomeKey.UP)
        repeat(5) { focus.press(ServiceHomeKey.RIGHT) }
        assertEquals(SERVICE_HOME_GRID_BUTTON_INDEX, focus.sidebarFocusIndex)
        assertEquals(ServiceHomeAction.OpenGrid, focus.press(ServiceHomeKey.SELECT))
        focus.press(ServiceHomeKey.LEFT)
        assertEquals(ServiceHomeAction.Filter(ServiceHome.Filter.MOVIES), focus.press(ServiceHomeKey.SELECT))
    }

    @Test fun `Back from the filter buttons leaves the page`() {
        val focus = state()
        focus.press(ServiceHomeKey.UP)
        assertEquals(ServiceHomeAction.Exit, focus.press(ServiceHomeKey.BACK))
    }

    @Test fun `a new filter starts over at the big card`() {
        val focus = state()
        focus.press(ServiceHomeKey.DOWN)
        focus.press(ServiceHomeKey.DOWN)
        repeat(4) { focus.press(ServiceHomeKey.RIGHT) }
        focus.startFilteredPage()
        assertTrue(focus.isHeroFocused)
        assertEquals(0, focus.currentRowIndex)
        assertEquals(0, focus.currentItemIndex)
        assertTrue(focus.rowItemIndicesByCategoryId.isEmpty())
    }

    @Test fun `Down enters the first row and Select opens the focused title`() {
        val focus = state()
        focus.press(ServiceHomeKey.DOWN)
        assertFalse(focus.isHeroFocused)
        focus.press(ServiceHomeKey.RIGHT)
        focus.press(ServiceHomeKey.RIGHT)
        assertEquals(ServiceHomeAction.Open(item(103), play = false), focus.press(ServiceHomeKey.SELECT))
    }

    @Test fun `right-to-left swaps the horizontal keys`() {
        val focus = state()
        focus.press(ServiceHomeKey.DOWN)
        focus.press(ServiceHomeKey.LEFT, rtl = true)
        assertEquals(1, focus.currentItemIndex)
        focus.press(ServiceHomeKey.RIGHT, rtl = true)
        assertEquals(0, focus.currentItemIndex)
    }

    @Test fun `a Top 10 row ends at ten, a longer row ends on View all`() {
        val focus = state()
        focus.press(ServiceHomeKey.DOWN)
        repeat(15) { focus.press(ServiceHomeKey.RIGHT) }
        assertEquals(9, focus.currentItemIndex)
        focus.press(ServiceHomeKey.DOWN)
        repeat(25) { focus.press(ServiceHomeKey.RIGHT) }
        assertEquals(20, focus.currentItemIndex)
        assertEquals(ServiceHomeAction.ViewAll("new"), focus.press(ServiceHomeKey.SELECT))
    }

    @Test fun `rows remember where focus rested`() {
        val focus = state()
        focus.press(ServiceHomeKey.DOWN)
        repeat(3) { focus.press(ServiceHomeKey.RIGHT) }
        focus.press(ServiceHomeKey.DOWN)
        assertEquals(0, focus.currentItemIndex)
        focus.press(ServiceHomeKey.UP)
        assertEquals(0, focus.currentRowIndex)
        assertEquals(3, focus.currentItemIndex)
    }

    @Test fun `Up from the first row and Back from any row return to the big card`() {
        val focus = state()
        focus.press(ServiceHomeKey.DOWN)
        focus.press(ServiceHomeKey.UP)
        assertTrue(focus.isHeroFocused)
        focus.press(ServiceHomeKey.DOWN)
        focus.press(ServiceHomeKey.DOWN)
        assertEquals(ServiceHomeAction.Moved, focus.press(ServiceHomeKey.BACK))
        assertTrue(focus.isHeroFocused)
        assertEquals(0, focus.heroButtonIndex)
    }

    @Test fun `without a big card Back from a row leaves the page`() {
        val focus = HomeFocusState()
        assertEquals(ServiceHomeAction.Exit, focus.press(ServiceHomeKey.BACK, withBillboard = false))
    }
}
