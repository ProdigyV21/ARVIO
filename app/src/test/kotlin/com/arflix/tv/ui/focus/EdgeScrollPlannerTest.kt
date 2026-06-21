package com.arflix.tv.ui.focus

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class EdgeScrollPlannerTest {
    @Test
    fun `keeps list still while focused item is visible`() {
        assertNull(
            edgeScrollTargetIndex(
                focusedItemIndex = 3,
                firstVisibleItemIndex = 1,
                lastVisibleItemIndex = 5,
                totalItems = 20
            )
        )
    }

    @Test
    fun `moves one slot when focus crosses the right edge`() {
        assertEquals(
            2,
            edgeScrollTargetIndex(
                focusedItemIndex = 6,
                firstVisibleItemIndex = 1,
                lastVisibleItemIndex = 5,
                totalItems = 20
            )
        )
    }

    @Test
    fun `moves back when focus crosses the left edge`() {
        assertEquals(
            3,
            edgeScrollTargetIndex(
                focusedItemIndex = 3,
                firstVisibleItemIndex = 4,
                lastVisibleItemIndex = 8,
                totalItems = 20
            )
        )
    }

    @Test
    fun `clamps target at the end of the list`() {
        assertEquals(
            15,
            edgeScrollTargetIndex(
                focusedItemIndex = 19,
                firstVisibleItemIndex = 14,
                lastVisibleItemIndex = 18,
                totalItems = 20
            )
        )
    }

    @Test
    fun `ignores invalid focus`() {
        assertNull(
            edgeScrollTargetIndex(
                focusedItemIndex = -1,
                firstVisibleItemIndex = 0,
                lastVisibleItemIndex = 4,
                totalItems = 20
            )
        )
    }

    @Test
    fun `keeps manual visible window stable while focus is visible`() {
        assertEquals(
            2,
            edgeVisibleWindowStartIndex(
                focusedItemIndex = 4,
                currentFirstVisibleItemIndex = 2,
                visibleItemCount = 5,
                totalItems = 20
            )
        )
    }

    @Test
    fun `advances manual visible window only after focus crosses right edge`() {
        assertEquals(
            3,
            edgeVisibleWindowStartIndex(
                focusedItemIndex = 7,
                currentFirstVisibleItemIndex = 2,
                visibleItemCount = 5,
                totalItems = 20
            )
        )
    }

    @Test
    fun `moves manual visible window back when focus crosses left edge`() {
        assertEquals(
            3,
            edgeVisibleWindowStartIndex(
                focusedItemIndex = 3,
                currentFirstVisibleItemIndex = 4,
                visibleItemCount = 5,
                totalItems = 20
            )
        )
    }

    @Test
    fun `clamps manual visible window at list end`() {
        assertEquals(
            15,
            edgeVisibleWindowStartIndex(
                focusedItemIndex = 19,
                currentFirstVisibleItemIndex = 14,
                visibleItemCount = 5,
                totalItems = 20
            )
        )
    }

    @Test
    fun `centers item by using negative scroll offset`() {
        assertEquals(
            -69,
            centeredItemScrollOffsetPx(
                itemSizePx = 202,
                viewportSizePx = 340
            )
        )
    }

    @Test
    fun `does not offset item that is taller than viewport`() {
        assertEquals(
            0,
            centeredItemScrollOffsetPx(
                itemSizePx = 400,
                viewportSizePx = 340
            )
        )
    }
}
