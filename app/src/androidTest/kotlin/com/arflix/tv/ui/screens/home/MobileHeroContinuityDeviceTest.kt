package com.arflix.tv.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.unit.dp
import com.arflix.tv.data.model.Category
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.ui.components.MobileHeroLayoutSpec
import com.arflix.tv.util.DeviceType
import com.arflix.tv.util.LocalDeviceType
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class MobileHeroContinuityDeviceTest {
    @get:Rule val compose = createComposeRule()

    @Test fun coldLoadAndAnEmptyRefreshCanBothRecover() {
        val rows = mutableStateOf(emptyList<Category>())
        var opened = 0
        compose.setContent {
            CompositionLocalProvider(LocalDeviceType provides DeviceType.PHONE) {
                Box(Modifier.size(320.dp, 580.dp)) {
                    MobileHeroCarousel(rows.value, MobileHeroLayoutSpec(320f, 460f, 0f, false),
                        posterStyle = false, onNavigateToDetails = { _, id, _, _ -> opened = id })
                }
            }
        }
        repeat(2) {
            compose.runOnIdle {
                opened = 0
                rows.value = listOf(Category("one", "One", (1..3).map { MediaItem(it, "Feature $it") }))
            }
            compose.onNodeWithTag("mobile_hero_pager").performTouchInput { click(center) }
            compose.runOnIdle { assertEquals(1, opened); rows.value = emptyList() }
            compose.waitForIdle()
        }
    }

    @Test fun lateCatalogResultsKeepTheManuallySelectedTitle() {
        val first = (1..3).map { MediaItem(it, "Feature $it", year = "2026") }
        val rows = mutableStateOf(listOf(Category("one", "One", first), Category("two", "Two", emptyList())))
        var opened = 0
        compose.setContent {
            CompositionLocalProvider(LocalDeviceType provides DeviceType.PHONE) {
                Box(Modifier.size(320.dp, 580.dp)) {
                    MobileHeroCarousel(rows.value, MobileHeroLayoutSpec(320f, 460f, 0f, false),
                        posterStyle = false, onNavigateToDetails = { _, id, _, _ -> opened = id })
                }
            }
        }
        val pager = compose.onNodeWithTag("mobile_hero_pager")
        pager.performTouchInput { swipeLeft() }
        pager.performTouchInput { click(center) }
        compose.runOnIdle { assertEquals(2, opened) }
        compose.runOnIdle {
            rows.value = rows.value.toMutableList().also {
                it[1] = Category("two", "Two", (4..6).map { id -> MediaItem(id, "New $id", year = "2026") })
            }
        }
        pager.performTouchInput { click(center) }
        compose.runOnIdle { assertEquals("A catalog arriving must not replace the visible feature", 2, opened) }
        compose.runOnIdle { rows.value = listOf(Category("two", "Two", rows.value[1].items)) }
        pager.performTouchInput { click(center) }
        compose.runOnIdle { assertEquals("A removed feature falls back to the first available title", 4, opened) }
    }

    @Test fun autoAdvanceDoesNotTakeOverAHeldSwipeAndStillResumesAfterwards() {
        compose.mainClock.autoAdvance = false
        compose.setContent {
            CompositionLocalProvider(LocalDeviceType provides DeviceType.PHONE) {
                Box(Modifier.size(320.dp, 580.dp)) {
                    MobileHeroCarousel(listOf(Category("one", "One", listOf(
                        MediaItem(1, "First", year = "2026"), MediaItem(2, "Second", year = "2026")))),
                        MobileHeroLayoutSpec(320f, 460f, 0f, false), posterStyle = false,
                        onNavigateToDetails = { _, _, _, _ -> })
                }
            }
        }
        compose.mainClock.advanceTimeBy(9_500)
        val pager = compose.onNodeWithTag("mobile_hero_pager")
        pager.performTouchInput { down(center); moveBy(Offset(-50f, 0f)) }
        compose.mainClock.advanceTimeBy(1_600)
        fun selected(index: Int): Float = compose.onNodeWithTag("mobile_hero_dot_$index")
            .fetchSemanticsNode().boundsInRoot.width
        assertTrue("An auto-advance must never steal an active gesture", selected(0) > selected(1))
        pager.performTouchInput { advanceEventTime(300); up() }
        compose.mainClock.advanceTimeBy(1_500)
        assertTrue(selected(0) > selected(1))
        compose.mainClock.advanceTimeBy(11_500)
        assertTrue("Auto-advance is retained after the user finishes", selected(1) > selected(0))
        compose.mainClock.autoAdvance = true
    }
}
