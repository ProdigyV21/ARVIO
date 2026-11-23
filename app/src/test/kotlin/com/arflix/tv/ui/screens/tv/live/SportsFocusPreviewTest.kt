package com.arflix.tv.ui.screens.tv.live

import android.app.Application
import androidx.activity.ComponentActivity
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performKeyInput
import androidx.compose.ui.test.pressKey
import com.arflix.tv.data.model.IptvChannel
import com.arflix.tv.data.model.IptvProgram
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.ConscryptMode

@RunWith(RobolectricTestRunner::class)
@ConscryptMode(ConscryptMode.Mode.OFF)
@Config(sdk = [33], application = Application::class, qualifiers = "w960dp-h540dp-land-xhdpi")
class SportsFocusPreviewTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    @After fun reset() = SportsPresentationCache.clear()

    private val now = 1_800_000_000_000L
    private fun game(id: String, title: String, startIn: Long) = SportsGuideEvent(id, title, GuideSport.FOOTBALL,
        IptvProgram(title, startUtcMillis = now + startIn, endUtcMillis = now + startIn + 7_200_000L),
        listOf(IptvChannel("p:$id", "Channel $id", "https://example.com/$id.m3u8", "Sports")),
        competition = "Premier League", artwork = "https://example.com/$id.png")

    @OptIn(ExperimentalTestApi::class)
    @Test fun previewFollowsDpadFocusAcrossCards() {
        val events = listOf(game("1", "Arsenal vs Chelsea", -600_000L), game("2", "Liverpool vs Spurs", -300_000L),
            game("3", "Ajax vs PSV", 3_600_000L))
        compose.mainClock.autoAdvance = false
        compose.setContent {
            SportsGuidePane(events = events, now = now, loading = false, focusSignal = 1,
                onContentFocused = {}, onOpenCategories = {}, onPlay = {})
        }
        fun settle() = repeat(15) { compose.mainClock.advanceTimeBy(100); Thread.sleep(30) }
        settle()
        // Before any move the preview shows the first game in the first row.
        compose.onNodeWithTag("sports-focus-preview-title").assertTextEquals("Arsenal vs Chelsea")
        compose.onRoot().performKeyInput { pressKey(Key.DirectionRight) }
        settle()
        compose.onNodeWithTag("sports-focus-preview-title").assertTextEquals("Liverpool vs Spurs")
    }

    @Test fun progressOnlyForProgrammesOnAirNow() {
        val p = IptvProgram("Show", startUtcMillis = 1_000L, endUtcMillis = 61_000L)
        assertEquals(0.5f, liveProgrammeProgress(p, 31_000L), 0.0001f)
        assertEquals(0f, liveProgrammeProgress(p, 500L), 0f)
        assertEquals(0f, liveProgrammeProgress(p, 61_000L), 0f)
        assertEquals(0f, liveProgrammeProgress(IptvProgram("Bad", startUtcMillis = 5L, endUtcMillis = 5L), 5L), 0f)
    }
}
