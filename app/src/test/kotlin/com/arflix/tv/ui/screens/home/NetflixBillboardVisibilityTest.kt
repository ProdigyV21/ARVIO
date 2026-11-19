package com.arflix.tv.ui.screens.home

import com.arflix.tv.data.model.Category
import com.arflix.tv.data.model.MediaItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class NetflixBillboardVisibilityTest {
    private val rows = listOf(Category("collection_row_featured", "Featured", listOf(MediaItem(1, "Collection"))))

    @Test fun missingBillboardWaitsForGraceThenMovesFocusToFirstRow() = runTest {
        val focus = HomeFocusState().apply { isHeroFocused = true }
        var hidden = false
        val job = launch {
            updateNetflixBillboardVisibility(true, false, false, hidden, focus, { rows }) { hidden = it }
        }
        runCurrent()
        advanceTimeBy(2_999)
        runCurrent()
        assertFalse(hidden)
        assertTrue(focus.isHeroFocused)
        advanceTimeBy(1)
        runCurrent()
        assertTrue(hidden)
        assertFalse(focus.isHeroFocused)
        assertEquals(0, focus.currentRowIndex)
        assertEquals(rows.first().id, focus.preferredCategoryId)
        assertFalse(focus.userHasNavigated)
        job.join()
    }

    @Test fun collapseThenRefreshStaysHiddenUntilARealCandidateArrives() = runTest {
        val focus = HomeFocusState().apply { isHeroFocused = true }
        var hidden = false
        updateNetflixBillboardVisibility(true, false, false, hidden, focus, { rows }) { hidden = it }
        assertTrue(hidden)
        assertFalse(focus.isHeroFocused)

        updateNetflixBillboardVisibility(true, false, true, hidden, focus, { rows }) { hidden = it }
        assertTrue(hidden)
        assertFalse(focus.isHeroFocused)

        // A real title can arrive while other Home rows are still loading.
        focus.heroButtonIndex = 1
        updateNetflixBillboardVisibility(true, true, true, hidden, focus, { rows }) { hidden = it }
        assertFalse(hidden)
        assertTrue(focus.isHeroFocused)
        assertEquals(0, focus.heroButtonIndex)
    }

    @Test fun refreshThatFindsNoCandidateDoesNotRevealTheEmptyBillboard() = runTest {
        val focus = HomeFocusState()
        var hidden = true
        updateNetflixBillboardVisibility(true, false, true, hidden, focus, { rows }) { hidden = it }
        updateNetflixBillboardVisibility(true, false, false, hidden, focus, { rows }) { hidden = it }
        assertTrue(hidden)
        assertFalse(focus.isHeroFocused)
    }

    @Test fun lateCandidateDoesNotStealFocusAfterUserNavigation() = runTest {
        val focus = HomeFocusState().apply { userHasNavigated = true }
        var hidden = true
        updateNetflixBillboardVisibility(true, true, false, hidden, focus, { rows }) { hidden = it }
        assertFalse(hidden)
        assertFalse(focus.isHeroFocused)
    }

    @Test fun lateCandidateDoesNotStealTopBarFocus() = runTest {
        val focus = HomeFocusState().apply { isSidebarFocused = true }
        var hidden = true
        updateNetflixBillboardVisibility(true, true, false, hidden, focus, { rows }) { hidden = it }
        assertFalse(hidden)
        assertTrue(focus.isSidebarFocused)
        assertFalse(focus.isHeroFocused)
    }

    @Test fun disablingNetflixStyleCannotRestoreBillboardFocus() = runTest {
        val focus = HomeFocusState().apply { isHeroFocused = true }
        var hidden = true
        updateNetflixBillboardVisibility(false, true, false, hidden, focus, { rows }) { hidden = it }
        assertFalse(hidden)
        assertFalse(focus.isHeroFocused)
    }

    @Test fun candidateArrivalCancelsThePendingCollapse() = runTest {
        val focus = HomeFocusState().apply { isHeroFocused = true }
        var hidden = false
        val job = launch {
            updateNetflixBillboardVisibility(true, false, false, hidden, focus, { rows }) { hidden = it }
        }
        runCurrent()
        advanceTimeBy(1_000)
        job.cancelAndJoin()
        updateNetflixBillboardVisibility(true, true, false, hidden, focus, { rows }) { hidden = it }
        advanceTimeBy(5_000)
        runCurrent()
        assertFalse(hidden)
        assertTrue(focus.isHeroFocused)
    }

    @Test fun refreshCancelsAndRestartsTheGracePeriodWithoutAnEarlyCollapse() = runTest {
        val focus = HomeFocusState().apply { isHeroFocused = true }
        var hidden = false
        val first = launch {
            updateNetflixBillboardVisibility(true, false, false, hidden, focus, { rows }) { hidden = it }
        }
        runCurrent()
        advanceTimeBy(2_000)
        first.cancelAndJoin()
        updateNetflixBillboardVisibility(true, false, true, hidden, focus, { rows }) { hidden = it }
        advanceTimeBy(5_000)
        assertFalse(hidden)
        val retry = launch {
            updateNetflixBillboardVisibility(true, false, false, hidden, focus, { rows }) { hidden = it }
        }
        runCurrent()
        advanceTimeBy(2_999)
        runCurrent()
        assertFalse(hidden)
        advanceTimeBy(1)
        runCurrent()
        assertTrue(hidden)
        retry.join()
    }

    @Test fun delayedCollapseUsesTheCurrentRowsRatherThanAnOldEmptySnapshot() = runTest {
        val focus = HomeFocusState().apply { isHeroFocused = true }
        var currentRows = emptyList<Category>()
        var hidden = false
        val job = launch {
            updateNetflixBillboardVisibility(true, false, false, hidden, focus, { currentRows }) { hidden = it }
        }
        runCurrent()
        advanceTimeBy(1_000)
        currentRows = rows
        advanceTimeBy(2_000)
        runCurrent()
        assertTrue(hidden)
        assertFalse(focus.isHeroFocused)
        assertEquals(rows.first().id, focus.preferredCategoryId)
        job.join()
    }
}
