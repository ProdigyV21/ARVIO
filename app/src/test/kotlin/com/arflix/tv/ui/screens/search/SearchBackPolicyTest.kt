package com.arflix.tv.ui.screens.search

import org.junit.Assert.assertEquals
import org.junit.Test

class SearchBackPolicyTest {

    @Test
    fun `back hides keyboard before changing search focus`() {
        assertEquals(
            SearchBackAction.HIDE_KEYBOARD,
            resolveSearchBackAction(
                focusZone = FocusZone.SEARCH_INPUT,
                isEditing = true,
                hasQuery = true,
                showFilters = true,
                hasFilters = true
            )
        )
    }

    @Test
    fun `back clears query before leaving search input`() {
        assertEquals(
            SearchBackAction.CLEAR_QUERY,
            resolveSearchBackAction(
                focusZone = FocusZone.SEARCH_INPUT,
                isEditing = false,
                hasQuery = true,
                showFilters = false,
                hasFilters = false
            )
        )
    }

    @Test
    fun `back moves predictably through search zones before exiting`() {
        assertEquals(
            SearchBackAction.MOVE_TO_FILTERS,
            resolveSearchBackAction(
                focusZone = FocusZone.RESULTS,
                isEditing = false,
                hasQuery = false,
                showFilters = true,
                hasFilters = true
            )
        )
        assertEquals(
            SearchBackAction.MOVE_TO_SEARCH_INPUT,
            resolveSearchBackAction(
                focusZone = FocusZone.FILTERS,
                isEditing = false,
                hasQuery = false,
                showFilters = true,
                hasFilters = true
            )
        )
        assertEquals(
            SearchBackAction.MOVE_TO_SIDEBAR,
            resolveSearchBackAction(
                focusZone = FocusZone.SEARCH_INPUT,
                isEditing = false,
                hasQuery = false,
                showFilters = false,
                hasFilters = false
            )
        )
        assertEquals(
            SearchBackAction.EXIT,
            resolveSearchBackAction(
                focusZone = FocusZone.SIDEBAR,
                isEditing = false,
                hasQuery = false,
                showFilters = false,
                hasFilters = false
            )
        )
    }
}
