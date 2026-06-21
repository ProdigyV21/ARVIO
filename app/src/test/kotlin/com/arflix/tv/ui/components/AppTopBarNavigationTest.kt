package com.arflix.tv.ui.components

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class AppTopBarNavigationTest {

    @Test
    fun `top bar focus order excludes dead tv destination without profile`() {
        assertEquals(SidebarItem.SEARCH, topBarFocusedItem(0, hasProfile = false))
        assertEquals(SidebarItem.HOME, topBarFocusedItem(1, hasProfile = false))
        assertEquals(SidebarItem.DISCOVER, topBarFocusedItem(2, hasProfile = false))
        assertEquals(SidebarItem.WATCHLIST, topBarFocusedItem(3, hasProfile = false))
        assertEquals(SidebarItem.SETTINGS, topBarFocusedItem(4, hasProfile = false))
        assertNull(topBarFocusedItem(5, hasProfile = false))

        assertEquals(4, topBarMaxIndex(hasProfile = false))
        assertEquals(4, topBarSelectedIndex(SidebarItem.SETTINGS, hasProfile = false))
    }

    @Test
    fun `top bar focus order excludes dead tv destination with profile slot`() {
        assertNull(topBarFocusedItem(0, hasProfile = true))
        assertEquals(SidebarItem.SEARCH, topBarFocusedItem(1, hasProfile = true))
        assertEquals(SidebarItem.HOME, topBarFocusedItem(2, hasProfile = true))
        assertEquals(SidebarItem.DISCOVER, topBarFocusedItem(3, hasProfile = true))
        assertEquals(SidebarItem.WATCHLIST, topBarFocusedItem(4, hasProfile = true))
        assertEquals(SidebarItem.SETTINGS, topBarFocusedItem(5, hasProfile = true))
        assertNull(topBarFocusedItem(6, hasProfile = true))

        assertEquals(5, topBarMaxIndex(hasProfile = true))
        assertEquals(5, topBarSelectedIndex(SidebarItem.SETTINGS, hasProfile = true))
    }
}
