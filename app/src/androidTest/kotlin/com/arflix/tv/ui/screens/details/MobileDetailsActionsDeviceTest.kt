package com.arflix.tv.ui.screens.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.unit.Density
import com.arflix.tv.data.model.Episode
import com.arflix.tv.data.model.EpisodeIdentity
import com.arflix.tv.ui.components.EpisodeContextMenu
import com.arflix.tv.util.LocalDeviceType
import com.arflix.tv.util.detectDeviceType
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class MobileDetailsActionsDeviceTest {
    @get:Rule val compose = createComposeRule()

    @Test fun pairedButtonsHaveEqualBoundsAndAllActionsWork() {
        val clicks = IntArray(6)
        compose.setContent {
            MobileDetailsActions("Play", true, false, true, Icons.Default.Bookmark, "In watchlist",
                { clicks[0]++ }, { clicks[1]++ }, { clicks[2]++ }, { clicks[3]++ },
                { clicks[4]++ }, { clicks[5]++ })
        }
        val play = compose.onNodeWithTag("details_play").fetchSemanticsNode().boundsInRoot
        val sources = compose.onNodeWithTag("details_sources").fetchSemanticsNode().boundsInRoot
        assertEquals(play.width, sources.width, 1f)
        assertEquals(play.height, sources.height, 1f)
        assertTrue(play.right < sources.left)
        val widthDp = compose.onNodeWithTag("mobile_details_actions").getUnclippedBoundsInRoot()
        assertTrue(widthDp.right.value - widthDp.left.value <= 560.1f)
        listOf("play", "sources", "trailer", "watched", "watchlist", "add_to_list").forEach {
            compose.onNodeWithTag("details_$it").assertIsDisplayed().performClick()
        }
        compose.runOnIdle { clicks.forEach { assertEquals(1, it) } }
        compose.onNodeWithTag("details_watchlist").assertIsSelected()
    }

    @Test fun longContinueLabelAndLargeTextFitWithoutChangingHierarchy() {
        compose.setContent {
            val density = LocalDensity.current
            CompositionLocalProvider(LocalDensity provides Density(density.density, 1.3f)) {
                MobileDetailsActions("Continue S23:E1173", false, false, false, Icons.Default.Bookmark,
                    "Add to watchlist", {}, {}, {}, {}, {})
            }
        }
        compose.onNodeWithText("Continue S23:E1173").assertIsDisplayed()
        compose.onNodeWithTag("details_trailer").assertIsNotEnabled()
        val buttons = compose.onNodeWithTag("details_sources").fetchSemanticsNode().boundsInRoot
        val secondary = compose.onNodeWithTag("details_watched").fetchSemanticsNode().boundsInRoot
        assertTrue(buttons.bottom < secondary.top)
    }

    @Test fun watchedAndWatchlistChangesDoNotShiftTheActionRows() {
        val selected = mutableStateOf(false)
        compose.setContent {
            MobileDetailsActions("Play", true, selected.value, selected.value, Icons.Default.Bookmark,
                if (selected.value) "In watchlist" else "Add to watchlist", {}, {}, {}, {}, {}, {})
        }
        val before = compose.onNodeWithTag("mobile_details_actions").fetchSemanticsNode().boundsInRoot
        compose.runOnIdle { selected.value = true }
        val after = compose.onNodeWithTag("mobile_details_actions").fetchSemanticsNode().boundsInRoot
        assertEquals(before, after)
    }

    @Test fun episodeHoldOpensActionsAndWatchedStateCanBeReversed() {
        val episode = mutableStateOf(Episode(1, 1, 1, "First episode"))
        var clicked = 0
        val held = mutableStateOf<Episode?>(null)
        var sourced: EpisodeIdentity? = null
        compose.setContent {
            CompositionLocalProvider(LocalDeviceType provides detectDeviceType(LocalContext.current)) {
                Box(Modifier.fillMaxSize()) {
                    EpisodeCard(episode.value, isFocused = false, onClick = { clicked++ },
                        onLongClick = { held.value = episode.value })
                    EpisodeContextMenu(held.value != null, "First episode", "S1 E1", episode.value.isWatched,
                        onPlay = { clicked++ }, onSelectSource = { sourced = held.value?.identity },
                        onToggleWatched = { episode.value = episode.value.copy(isWatched = !episode.value.isWatched) },
                        onDismiss = { held.value = null })
                }
            }
        }
        fun hold() = compose.onNodeWithTag("episode_1_1").performTouchInput { longClick() }
        hold()
        compose.onNodeWithTag("context_action_mark_watched").assertIsDisplayed().performClick()
        compose.runOnIdle { assertTrue(episode.value.isWatched); assertEquals(0, clicked) }
        hold()
        compose.onNodeWithTag("context_action_mark_unwatched").performClick()
        compose.runOnIdle { assertFalse(episode.value.isWatched) }
        hold()
        compose.onNodeWithTag("context_action_sources").performClick()
        compose.runOnIdle { assertEquals(episode.value.identity, sourced) }
        compose.onNodeWithTag("episode_1_1").performClick()
        compose.runOnIdle { assertEquals(1, clicked) }
    }

}
