package com.arflix.tv.ui.components

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * The two calculations behind press-and-hold reordering. Rows here are deliberately NOT all the
 * same height: the point of reading the real layout is that nothing breaks when they differ.
 */
class DragReorderableListTest {

    private val evenRows = listOf(
        ReorderSlot(index = 0, offset = 0, size = 100),
        ReorderSlot(index = 1, offset = 100, size = 100),
        ReorderSlot(index = 2, offset = 200, size = 100),
        ReorderSlot(index = 3, offset = 300, size = 100)
    )

    @Test
    fun rowStaysPutWhileItsMiddleIsStillInsideItsOwnSlot() {
        assertEquals(1, reorderTargetIndex(evenRows, floatingCenter = 149f, itemCount = 4))
    }

    @Test
    fun rowTakesTheSlotItsMiddleHasMovedInto() {
        assertEquals(2, reorderTargetIndex(evenRows, floatingCenter = 250f, itemCount = 4))
    }

    @Test
    fun rowsOfDifferentHeightsAreReadFromTheLayoutNotAssumed() {
        val unevenRows = listOf(
            ReorderSlot(index = 0, offset = 0, size = 40),
            ReorderSlot(index = 1, offset = 40, size = 160),
            ReorderSlot(index = 2, offset = 200, size = 60)
        )
        assertEquals(1, reorderTargetIndex(unevenRows, floatingCenter = 190f, itemCount = 3))
        assertEquals(2, reorderTargetIndex(unevenRows, floatingCenter = 210f, itemCount = 3))
    }

    @Test
    fun draggingAboveTheFirstRowStopsAtTheFirstRow() {
        assertEquals(0, reorderTargetIndex(evenRows, floatingCenter = -500f, itemCount = 4))
    }

    @Test
    fun draggingBelowTheLastRowStopsAtTheLastRow() {
        assertEquals(3, reorderTargetIndex(evenRows, floatingCenter = 5000f, itemCount = 4))
    }

    @Test
    fun aRowScrolledOutOfSightNeverBecomesATargetBeyondTheList() {
        // Only rows 40..43 are on screen; the answer stays inside the list either way.
        val scrolled = listOf(
            ReorderSlot(index = 40, offset = 0, size = 100),
            ReorderSlot(index = 41, offset = 100, size = 100)
        )
        assertEquals(41, reorderTargetIndex(scrolled, floatingCenter = 9000f, itemCount = 44))
        assertEquals(40, reorderTargetIndex(scrolled, floatingCenter = -9000f, itemCount = 44))
    }

    @Test
    fun emptyListHasNothingToReorder() {
        assertNull(reorderTargetIndex(emptyList(), floatingCenter = 0f, itemCount = 0))
        assertNull(reorderTargetIndex(evenRows, floatingCenter = 0f, itemCount = 0))
    }

    @Test
    fun theHeldRowStaysInsideTheListWhenTheFingerLeavesIt() {
        // Finger above the list, finger below it, finger inside it.
        assertEquals(0f, clampFloatingTop(-800f, viewportStart = 0, viewportEnd = 1000, rowSize = 100), 0f)
        assertEquals(900f, clampFloatingTop(5000f, viewportStart = 0, viewportEnd = 1000, rowSize = 100), 0f)
        assertEquals(420f, clampFloatingTop(420f, viewportStart = 0, viewportEnd = 1000, rowSize = 100), 0f)
    }

    @Test
    fun aRowTallerThanTheViewportIsPinnedToTheTopInsteadOfAbsurdity() {
        assertEquals(0f, clampFloatingTop(50f, viewportStart = 0, viewportEnd = 80, rowSize = 400), 0f)
    }

    // A row 100 tall in a viewport 1000 tall, with an edge strip of 100.
    private fun scrollFor(rowTop: Float) =
        autoScrollDelta(rowTop = rowTop, rowSize = 100, viewportStart = 0f, viewportEnd = 1000f, edgeSize = 100f, maxSpeed = 8f)

    @Test
    fun aRowInTheMiddleOfTheListDoesNotScroll() {
        assertEquals(0f, scrollFor(450f), 0f)
    }

    @Test
    fun aRowLyingAgainstTheTopEdgeScrollsBackwardsAtFullSpeed() {
        assertEquals(-8f, scrollFor(0f), 0.001f)
    }

    @Test
    fun aRowLyingAgainstTheBottomEdgeScrollsForwardsAtFullSpeed() {
        assertEquals(8f, scrollFor(900f), 0.001f)
    }

    @Test
    fun enteringTheEdgeStripOnlyCreeps() {
        // Half way into the top strip, and half way into the bottom one.
        assertEquals(-4f, scrollFor(50f), 0.001f)
        assertEquals(4f, scrollFor(850f), 0.001f)
        assertTrue(scrollFor(90f) > scrollFor(10f))
    }

    @Test
    fun theClimbTakesAStepOnlyOnceAWholeRowHasBeenAskedFor() {
        assertFalse(hasClimbedAWholeRow(progress = 99f, rowSize = 100))
        assertTrue(hasClimbedAWholeRow(progress = 100f, rowSize = 100))
        assertTrue(hasClimbedAWholeRow(progress = 250f, rowSize = 100))
        // A row without a measured height never steps, rather than stepping every frame.
        assertFalse(hasClimbedAWholeRow(progress = 500f, rowSize = 0))
    }

    @Test
    fun aRowAboveEverythingOnScreenIsRecognisedAsOutOfSight() {
        // The list is showing rows 3 and below.
        assertTrue(isAboveViewport(rowIndex = 0, firstVisibleIndex = 3))
        assertTrue(isAboveViewport(rowIndex = 2, firstVisibleIndex = 3))
        assertFalse(isAboveViewport(rowIndex = 3, firstVisibleIndex = 3))
        assertFalse(isAboveViewport(rowIndex = 9, firstVisibleIndex = 3))
    }

    @Test
    fun withTheListAtItsTopNothingIsOutOfSightAbove() {
        assertFalse(isAboveViewport(rowIndex = 0, firstVisibleIndex = 0))
        assertFalse(isAboveViewport(rowIndex = -1, firstVisibleIndex = 3))
    }

    @Test
    fun aViewportWithoutHeightNeverScrolls() {
        assertEquals(0f, autoScrollDelta(10f, 100, 0f, 0f, 100f, 8f), 0f)
        assertEquals(0f, autoScrollDelta(10f, 100, 0f, 1000f, 0f, 8f), 0f)
    }
}
