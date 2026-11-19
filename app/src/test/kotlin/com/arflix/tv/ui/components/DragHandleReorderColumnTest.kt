package com.arflix.tv.ui.components

import com.arflix.tv.util.withItemMoved
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

/**
 * The handle reordering used on the phone settings pages. Rows are deliberately of different
 * heights, and the moves are checked to be reported once, on release - never step by step.
 */
class DragHandleReorderColumnTest {

    private fun stateWith(rows: List<Pair<String, Int>>, drops: MutableList<Triple<Any, Int, Int>>): DragHandleReorderState {
        val state = DragHandleReorderState(autoScrollEdgePx = 32f, autoScrollSpeedPx = 8f, onGrab = {})
        var top = 0
        rows.forEach { (key, size) ->
            state.tops[key] = top
            state.sizes[key] = size
            top += size
        }
        state.shownKeys = rows.map { it.first }
        state.onDrop = { key, from, to -> drops += Triple(key, from, to) }
        return state
    }

    @Test
    fun slotsAreStackedFromTheRowHeights() {
        assertEquals(
            listOf(ReorderSlot(0, 0, 60), ReorderSlot(1, 60, 100), ReorderSlot(2, 160, 40)),
            contiguousSlots(listOf(60, 100, 40))
        )
    }

    @Test
    fun itemMovedKeepsEverythingElseInOrder() {
        assertEquals(listOf("b", "c", "a", "d"), listOf("a", "b", "c", "d").withItemMoved(0, 2))
        assertEquals(listOf("d", "a", "b", "c"), listOf("a", "b", "c", "d").withItemMoved(3, 0))
        assertEquals(listOf("a", "b"), listOf("a", "b").withItemMoved(0, 5))
    }

    @Test
    fun aRowDraggedPastTwoOthersIsReportedOnceOnRelease() {
        val drops = mutableListOf<Triple<Any, Int, Int>>()
        val state = stateWith(listOf("a" to 60, "b" to 100, "c" to 40, "d" to 60), drops)
        state.onDragStart("a")
        // The middle of "a" (30) travels to 190: past "b" (60..160) and into "c".
        state.onDrag(80f)
        state.onDrag(80f)
        assertEquals(emptyList<Triple<Any, Int, Int>>(), drops)
        assertEquals(listOf("b", "c", "a", "d"), state.localOrder)
        state.onDragStop()
        assertEquals(listOf(Triple<Any, Int, Int>("a", 0, 2)), drops)
    }

    @Test
    fun aRowPutBackWhereItCameFromStoresNothing() {
        val drops = mutableListOf<Triple<Any, Int, Int>>()
        val state = stateWith(listOf("a" to 60, "b" to 60, "c" to 60), drops)
        state.onDragStart("b")
        state.onDrag(70f)
        state.onDrag(-70f)
        state.onDragStop()
        assertEquals(emptyList<Triple<Any, Int, Int>>(), drops)
        assertNull(state.localOrder)
    }

    @Test
    fun theLocalOrderIsKeptUntilTheStoredOrderChanges() {
        val drops = mutableListOf<Triple<Any, Int, Int>>()
        val state = stateWith(listOf("a" to 60, "b" to 60, "c" to 60), drops)
        state.onDragStart("c")
        state.onDrag(-130f)
        state.onDragStop()
        assertEquals(listOf("c", "a", "b"), state.localOrder)
        // The store has not answered yet: same order as at pick-up.
        state.forgetLocalOrderIfStale(listOf("a", "b", "c"))
        assertEquals(listOf("c", "a", "b"), state.localOrder)
        // It has: from now on the list shows what it is given.
        state.forgetLocalOrderIfStale(listOf("c", "a", "b"))
        assertNull(state.localOrder)
    }

    @Test
    fun noSecondRowCanBePickedUpWhileTheFirstDropIsStillUnanswered() {
        val drops = mutableListOf<Triple<Any, Int, Int>>()
        val state = stateWith(listOf("a" to 60, "b" to 60, "c" to 60), drops)
        state.onDragStart("a")
        state.onDrag(70f)
        state.onDragStop()
        state.onDragStart("c")
        assertNull(state.draggedKey)
    }

    @Test
    fun aGestureCutOffFromOutsideReleasesTheRowWithoutStoringAnything() {
        val drops = mutableListOf<Triple<Any, Int, Int>>()
        val state = stateWith(listOf("a" to 60, "b" to 60, "c" to 60), drops)
        state.onDragStart("a")
        state.onDrag(70f)
        state.cancelDrag("a")
        assertNull(state.draggedKey)
        assertNull(state.localOrder)
        assertEquals(emptyList<Triple<Any, Int, Int>>(), drops)
    }

    @Test
    fun theHeldRowIsDrawnFromTheShownOrderBeforeTheColumnHasPlacedIt() {
        val drops = mutableListOf<Triple<Any, Int, Int>>()
        val state = stateWith(listOf("a" to 60, "b" to 100, "c" to 40), drops)
        state.onDragStart("a")
        state.onDrag(80f)
        // "a" now comes after "b" in the shown order, so it is laid out at 100 - even though the
        // last layout still has it at 0. The finger put its top at 80.
        assertEquals(listOf("b", "a", "c"), state.localOrder)
        assertEquals(-20f, state.translationFor("a"), 0.001f)
    }

    @Test
    fun aSourceReorderWhileHeldCancelsTheStaleMove() {
        assertChangedSourceCancels(listOf("c", "a", "b"))
    }

    @Test
    fun anInsertedSourceWhileHeldCancelsTheStaleMove() {
        assertChangedSourceCancels(listOf("a", "new", "b", "c"))
    }

    @Test
    fun aRemovedSourceWhileHeldCancelsTheStaleMove() {
        assertChangedSourceCancels(listOf("a", "c"))
    }

    @Test
    fun unchangedSourceKeysDoNotCancelAnActiveDrag() {
        val drops = mutableListOf<Triple<Any, Int, Int>>()
        val state = stateWith(listOf("a" to 60, "b" to 60, "c" to 60), drops)
        state.onDragStart("a")
        state.onDrag(70f)
        state.forgetLocalOrderIfStale(listOf("a", "b", "c"))
        assertEquals("a", state.draggedKey)
        state.onDragStop()
        assertEquals(listOf(Triple<Any, Int, Int>("a", 0, 1)), drops)
    }

    @Test
    fun aFreshDragUsesTheNewSourcePositionsAfterCancellation() {
        val drops = mutableListOf<Triple<Any, Int, Int>>()
        val state = stateWith(listOf("a" to 60, "b" to 60, "c" to 60), drops)
        state.onDragStart("a")
        state.onDrag(70f)
        state.forgetLocalOrderIfStale(listOf("c", "a", "b"))
        state.shownKeys = listOf("c", "a", "b")
        state.tops["c"] = 0
        state.tops["a"] = 60
        state.tops["b"] = 120
        state.onDragStart("a")
        state.onDrag(70f)
        state.onDragStop()
        assertEquals(listOf(Triple<Any, Int, Int>("a", 1, 2)), drops)
    }

    private fun assertChangedSourceCancels(keys: List<String>) {
        val drops = mutableListOf<Triple<Any, Int, Int>>()
        val state = stateWith(listOf("a" to 60, "b" to 60, "c" to 60), drops)
        state.onDragStart("a")
        state.onDrag(70f)
        state.forgetLocalOrderIfStale(keys)
        assertNull(state.draggedKey)
        assertNull(state.localOrder)
        // Late pointer events from the canceled gesture must not write anything.
        state.onDrag(70f)
        state.onDragStop()
        assertEquals(emptyList<Triple<Any, Int, Int>>(), drops)
    }
}
