package com.arflix.tv.ui.screens.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.unit.dp
import com.arflix.tv.data.repository.MdbExternalRating
import com.arflix.tv.util.DeviceType
import com.arflix.tv.util.LocalDeviceType
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class MdbRatingsLayoutDeviceTest(private val device: DeviceType, private val width: Int) {
    @get:Rule val compose = createComposeRule()

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{0}-{1}dp")
        fun sizes() = listOf(arrayOf<Any>(DeviceType.TV, 420), arrayOf<Any>(DeviceType.PHONE, 280), arrayOf<Any>(DeviceType.TABLET, 500))
    }

    @Test fun allAvailableRatingsFitWithoutHorizontalScrolling() {
        val labels = listOf("Rotten Tomatoes", "Audience", "Metacritic", "Trakt", "TMDB", "Letterboxd", "Roger Ebert", "MyAnimeList")
        val ratings = labels.map { MdbExternalRating(it, it, "82%") }
        compose.setContent {
            CompositionLocalProvider(LocalDeviceType provides device) {
                Box(Modifier.width(width.dp).testTag("ratings")) {
                    MdbExternalRatingsRow(ratings, centered = device.isTouchDevice(), textShadow = Shadow.None)
                }
            }
        }
        val parent = compose.onNodeWithTag("ratings").getUnclippedBoundsInRoot()
        labels.forEach { label ->
            val node = compose.onNodeWithText(label)
            node.assertIsDisplayed()
            val bounds = node.getUnclippedBoundsInRoot()
            assertTrue("$label extends past the right edge", bounds.right <= parent.right)
            assertTrue("$label extends past the left edge", bounds.left >= parent.left)
            assertTrue("$label extends below the ratings", bounds.bottom <= parent.bottom)
        }
        compose.onAllNodes(hasScrollAction()).assertCountEquals(0)
    }
}
