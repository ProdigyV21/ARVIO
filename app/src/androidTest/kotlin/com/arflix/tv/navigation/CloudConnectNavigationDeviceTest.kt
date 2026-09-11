package com.arflix.tv.navigation

import androidx.activity.ComponentActivity
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.arflix.tv.ui.components.AppBottomBar
import com.arflix.tv.ui.components.bottomBarItems
import com.arflix.tv.ui.components.shouldShowBottomBar
import com.arflix.tv.util.DeviceType
import com.arflix.tv.util.LocalDeviceType
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class CloudConnectNavigationDeviceTest(private val deviceType: DeviceType) {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private lateinit var nav: NavHostController
    private val fullscreen = mutableStateOf(false)

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{0}")
        fun devices() = listOf(DeviceType.PHONE, DeviceType.TABLET, DeviceType.TV)
    }

    @Test fun firstLaunchCloudSettingsShowsWorkingMobileTabs() {
        showGraph()
        compose.onNodeWithTag("bottomBar").assertDoesNotExist()
        compose.runOnIdle { nav.navigate(Screen.Settings.createRoute(autoCloudAuth = true)) }
        compose.runOnIdle {
            assertTrue(nav.currentBackStackEntry!!.arguments!!.getBoolean("autoCloudAuth"))
        }
        if (!deviceType.isTouchDevice()) {
            compose.onNodeWithTag("bottomBar").assertDoesNotExist()
            return
        }
        compose.onNodeWithTag("bottomBar").assertIsDisplayed()
        bottomBarItems.forEach { item ->
            compose.onNodeWithText(compose.activity.getString(item.labelRes)).performClick()
            compose.runOnIdle {
                assertEquals(item.route, nav.currentDestination!!.route!!.substringBefore('?'))
            }
        }
    }

    @Test fun switchingFromFirstLaunchSettingsClearsOldScreens() {
        showGraph()
        compose.runOnIdle { nav.navigate(Screen.Settings.createRoute(autoCloudAuth = true)) }
        switchProfileAndCheckStack()
    }

    @Test fun switchingAfterSelectingProfileClearsHomeEvenWhenStartWasRemoved() {
        showGraph()
        compose.runOnIdle {
            nav.navigate(Screen.Home.route) {
                popUpTo(Screen.ProfileSelection.route) { inclusive = true }
            }
            nav.navigate(Screen.Settings.route)
        }
        switchProfileAndCheckStack()
    }

    @Test fun switchingWhenStartupSkipsProfileSelectionClearsHome() {
        showGraph(Screen.Home.route)
        compose.runOnIdle { nav.navigate(Screen.Settings.route) }
        switchProfileAndCheckStack()
    }

    @Test fun switchingFromNewlyAccessibleTabWithoutHomeClearsCloudSettings() {
        showGraph()
        compose.runOnIdle {
            nav.navigate(Screen.Settings.createRoute(autoCloudAuth = true))
            nav.navigate(Screen.Search.route)
        }
        switchProfileAndCheckStack()
    }

    @Test fun loginProfilesAndFullscreenStillHideBar() {
        showGraph()
        compose.onNodeWithTag("bottomBar").assertDoesNotExist()
        compose.runOnIdle { nav.navigate(Screen.Login.route) }
        compose.onNodeWithTag("bottomBar").assertDoesNotExist()
        compose.runOnIdle {
            nav.navigate("tv")
            fullscreen.value = true
        }
        compose.onNodeWithTag("bottomBar").assertDoesNotExist()
        compose.runOnIdle { fullscreen.value = false }
        if (deviceType.isTouchDevice()) {
            compose.onNodeWithTag("bottomBar").assertIsDisplayed()
        } else {
            compose.onNodeWithTag("bottomBar").assertDoesNotExist()
        }
    }

    private fun switchProfileAndCheckStack() {
        compose.runOnIdle { nav.navigateToProfileSelection() }
        compose.runOnIdle {
            assertEquals(Screen.ProfileSelection.route, nav.currentDestination!!.route)
            assertNull(nav.previousBackStackEntry)
            nav.navigateToProfileSelection()
        }
        compose.runOnIdle {
            assertNull(nav.previousBackStackEntry)
        }
        compose.onNodeWithTag("bottomBar").assertDoesNotExist()
    }

    private fun showGraph(start: String = Screen.ProfileSelection.route) {
        compose.setContent {
            CompositionLocalProvider(LocalDeviceType provides deviceType) {
                nav = rememberNavController()
                val entry by nav.currentBackStackEntryAsState()
                val route = entry?.destination?.route
                Column(Modifier.fillMaxSize()) {
                    Box(Modifier.weight(1f)) {
                        NavHost(
                            navController = nav,
                            startDestination = start,
                            enterTransition = { EnterTransition.None },
                            exitTransition = { ExitTransition.None }
                        ) {
                            listOf("home", "search", "watchlist", "tv", "login", "profile_selection")
                                .forEach { destination ->
                                    composable(destination) { Text("Screen: $destination") }
                                }
                            composable(
                                "settings?autoCloudAuth={autoCloudAuth}",
                                arguments = listOf(navArgument("autoCloudAuth") {
                                    type = NavType.BoolType
                                    defaultValue = false
                                })
                            ) { Text("Screen: settings") }
                        }
                    }
                    if (shouldShowBottomBar(deviceType.isTouchDevice(), route, fullscreen.value)) {
                        AppBottomBar(route, onNavigate = { destination ->
                            nav.navigate(destination) {
                                popUpTo(Screen.Home.route) { inclusive = false }
                                launchSingleTop = true
                            }
                        }, modifier = Modifier.testTag("bottomBar"))
                    }
                }
            }
        }
    }
}
