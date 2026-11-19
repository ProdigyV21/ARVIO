package com.arflix.tv.navigation

import androidx.activity.ComponentActivity
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.arflix.tv.ui.components.LocalBottomBarInset
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class NavigationRailScreenDeviceTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()

    @Test fun homeHasItsFinalWidthOnEntryAndKeepsItWhenRailDisappears() {
        lateinit var nav: NavHostController
        var expectedWidth = 0
        var detailWidth = 0
        val homeWidths = mutableListOf<Int>()
        val globalBarVisible = mutableStateOf(false)
        compose.setContent {
            nav = rememberNavController()
            val density = LocalDensity.current
            expectedWidth = with(density) { 400.dp.roundToPx() - 100.dp.roundToPx() }
            CompositionLocalProvider(LocalBottomBarInset provides if (globalBarVisible.value) 24.dp else 0.dp) {
                Box(Modifier.size(400.dp, 240.dp)) {
                    NavHost(nav, startDestination = "profile",
                        enterTransition = { fadeIn(tween(220)) },
                        exitTransition = { fadeOut(tween(220)) }) {
                        composable("profile") { Box(Modifier.fillMaxSize().testTag("profile")) }
                        composable("home") {
                            NavigationRailScreen(100.dp, bottomInset = 24.dp) {
                                assertEquals(24.dp, LocalBottomBarInset.current)
                                Box(Modifier.fillMaxSize().testTag("home")
                                    .onGloballyPositioned { homeWidths.add(it.size.width) })
                            }
                        }
                        composable("details") {
                            Box(Modifier.fillMaxSize().testTag("details")
                                .onGloballyPositioned { detailWidth = it.size.width })
                        }
                    }
                }
            }
        }
        compose.onNodeWithTag("profile").assertIsDisplayed()
        compose.runOnIdle { nav.navigate("home") }
        compose.waitForIdle()
        compose.onNodeWithTag("home").assertIsDisplayed()
        compose.runOnIdle { globalBarVisible.value = true }
        compose.waitForIdle()
        compose.runOnIdle {
            globalBarVisible.value = false
            nav.navigate("details")
        }
        compose.waitForIdle()
        compose.onNodeWithTag("details").assertIsDisplayed()
        compose.runOnIdle {
            assertTrue(homeWidths.isNotEmpty())
            assertTrue("Home must not reflow as navigation visibility changes: $homeWidths",
                homeWidths.all { it == expectedWidth })
            assertTrue(detailWidth > expectedWidth)
        }
    }
}
