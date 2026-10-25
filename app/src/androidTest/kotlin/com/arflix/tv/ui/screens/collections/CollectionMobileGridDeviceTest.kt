package com.arflix.tv.ui.screens.collections

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.unit.dp
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.util.DeviceType
import com.arflix.tv.util.LocalDeviceType
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalTestApi::class)
class CollectionMobileGridDeviceTest {
    @get:Rule val compose = createComposeRule()

    @Test fun cardsFillActualContainerAndReflowWhenItResizes() {
        val containerWidth = mutableStateOf(360.dp)
        val posters = mutableStateOf(false)
        val selectedTab = mutableStateOf(CollectionTab.MOVIES)
        var renderedGridState: LazyGridState? = null
        var opened: MediaItem? = null
        val items = (1..40).map { MediaItem(it, "Title $it") }
        compose.setContent {
            CompositionLocalProvider(LocalDeviceType provides DeviceType.PHONE) {
                val gridState = rememberLazyGridState()
                renderedGridState = gridState
                Box(Modifier.width(containerWidth.value).fillMaxSize()) {
                    CollectionItemsGrid(
                        catalog = null, onBack = {}, items = items, usePosterCards = posters.value,
                        gridState = gridState, pendingFocusIndex = -1,
                        onClearPendingFocus = {}, hasMovies = true, hasSeries = true,
                        cardLogoUrls = emptyMap(), selectedTab = selectedTab.value,
                        moviesTabFocusRequester = remember { FocusRequester() },
                        seriesTabFocusRequester = remember { FocusRequester() },
                        isSportsCollection = false, onTabSelected = { selectedTab.value = it },
                        onItemClick = { opened = it }, onItemFocused = { _, _ -> },
                        onVisibleItemsChanged = {}, onVisibleDetailsChanged = {}, onNearEnd = {},
                        isLoading = false, isLoadingMore = false, emptyMessage = "", topContentPadding = 0.dp
                    )
                }
            }
        }
        fun checkColumns(columns: Int) {
            compose.waitForIdle()
            val grid = compose.onNodeWithTag("collection_grid").getUnclippedBoundsInRoot()
            val first = compose.onNodeWithTag("collection_item_0").getUnclippedBoundsInRoot()
            val last = compose.onNodeWithTag("collection_item_${columns - 1}").getUnclippedBoundsInRoot()
            assertEquals("First row must fill the width, including its right-hand column",
                grid.right.value - 20f, last.right.value, 2f)
            assertEquals(first.top.value, last.top.value, 1f)
            assertEquals(grid.left.value + 20f, first.left.value, 1f)
            val next = compose.onNodeWithTag("collection_item_$columns").getUnclippedBoundsInRoot()
            assertTrue("Following row must be below the first", next.top > first.top)
        }
        checkColumns(2)
        compose.onAllNodes(hasClickAction() and hasAnyAncestor(hasTestTag("collection_item_1"))).onFirst().performClick()
        compose.runOnIdle { assertEquals(2, opened?.id) }
        // Simulates a split-screen pane: LocalConfiguration still describes the wider screen.
        compose.runOnIdle { containerWidth.value = 280.dp }
        checkColumns(1)
        compose.runOnIdle { posters.value = true }
        checkColumns(2)
        compose.runOnIdle { containerWidth.value = 360.dp }
        checkColumns(2)
        compose.onNodeWithTag("collection_grid").performTouchInput { swipeUp() }
        compose.runOnIdle {
            assertTrue("Touch scrolling remains functional", renderedGridState!!.let {
                it.firstVisibleItemIndex > 0 || it.firstVisibleItemScrollOffset > 0
            })
        }
    }
}
