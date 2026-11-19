package com.arflix.tv.ui.screens.settings

import org.junit.Assert.*
import org.junit.Test

class IptvSourceSelectionTest {
    @Test fun selectionSurvivesAReorderedSaveAcknowledgment() {
        val selection = IptvSourceSelection().toggle(IptvSourceSection.PLAYLISTS, "b")
        val order = listOf("b", "c", "a")
        val retained = selection.retain(order.toSet(), emptySet())
        assertEquals(setOf("b"), retained.ids)
        assertEquals(listOf("c", "a"), order.filterNot { it in retained.ids })
    }

    @Test fun multiSelectionKeepsIdsInsteadOfRowPositions() {
        val selection = IptvSourceSelection()
            .toggle(IptvSourceSection.PORTALS, "a")
            .toggle(IptvSourceSection.PORTALS, "c")
        val order = listOf("c", "b", "a")
        val retained = selection.retain(emptySet(), order.toSet())
        assertEquals(listOf("b"), order.filterNot { it in retained.ids })
    }

    @Test fun playlistsAndPortalsWithTheSameIdAreNotBothSelected() {
        val selection = IptvSourceSelection().toggle(IptvSourceSection.PLAYLISTS, "same")
        assertTrue(selection.contains(IptvSourceSection.PLAYLISTS, "same"))
        assertFalse(selection.contains(IptvSourceSection.PORTALS, "same"))
    }

    @Test fun selectingTheOtherSectionStartsANewSelection() {
        val selection = IptvSourceSelection()
            .toggle(IptvSourceSection.PLAYLISTS, "a")
            .toggle(IptvSourceSection.PORTALS, "b")
        assertEquals(IptvSourceSection.PORTALS, selection.section)
        assertEquals(setOf("b"), selection.ids)
    }

    @Test fun removingSelectedRowsPrunesOnlyThoseIds() {
        val selection = IptvSourceSelection()
            .toggle(IptvSourceSection.PLAYLISTS, "a")
            .toggle(IptvSourceSection.PLAYLISTS, "b")
        assertEquals(setOf("b"), selection.retain(setOf("b", "c"), setOf("a")).ids)
        assertFalse(selection.retain(emptySet(), setOf("a", "b")).isActive)
    }

    @Test fun deselectingTheLastRowExitsSelectionMode() {
        val selection = IptvSourceSelection()
            .toggle(IptvSourceSection.PLAYLISTS, "a")
            .toggle(IptvSourceSection.PLAYLISTS, "a")
        assertFalse(selection.isActive)
    }
}
