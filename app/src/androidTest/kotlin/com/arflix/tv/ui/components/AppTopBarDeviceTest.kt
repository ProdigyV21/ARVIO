package com.arflix.tv.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.test.assertHeightIsEqualTo
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.arflix.tv.data.model.Profile
import com.arflix.tv.ui.theme.ArvioTvTheme
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class AppTopBarDeviceTest {
    @get:Rule val compose = createComposeRule()

    private val profile = Profile(id = "topbar-test", name = "A long test profile name", avatarColor = 0xFF367B72)
    private val tags = SidebarItem.entries.map { "topbar-item-${it.name}" }

    @Test fun navigationBoundsStayTheSameOnEveryPage() {
        val page = mutableStateOf(SidebarItem.HOME)
        val showProfile = mutableStateOf(true)
        compose.setContent {
            ArvioTvTheme {
                Box(Modifier.fillMaxSize().background(Color.Black)) {
                    AppTopBar(page.value, false, 0, profile = profile.takeIf { showProfile.value })
                }
            }
        }
        for (withProfile in listOf(true, false)) {
            compose.runOnIdle { showProfile.value = withProfile; page.value = SidebarItem.HOME }
            compose.waitForIdle()
            val expected = tags.map { compose.onNodeWithTag(it).fetchSemanticsNode().boundsInRoot }
            for (destination in SidebarItem.entries) {
                compose.runOnIdle { page.value = destination }
                compose.waitForIdle()
                compose.onNodeWithTag("app-topbar").assertHeightIsEqualTo(AppTopBarContentTopInset)
                assertEquals("Navigation moved on $destination (profile=$withProfile)", expected,
                    tags.map { compose.onNodeWithTag(it).fetchSemanticsNode().boundsInRoot })
            }
        }
    }

    @Test fun focusedNavigationRendersIdenticallyRegardlessOfCurrentPage() {
        val page = mutableStateOf(SidebarItem.HOME)
        val focused = mutableIntStateOf(0)
        compose.setContent {
            ArvioTvTheme {
                Box(Modifier.fillMaxSize().background(Color.Black)) {
                    AppTopBar(page.value, true, focused.intValue, profile = profile)
                }
            }
        }
        // The current page must not change the focused item's shape, tint or position.
        for (tag in listOf("topbar-profile") + tags) {
            val index = if (tag == "topbar-profile") 0 else
                topBarSelectedIndex(SidebarItem.valueOf(tag.removePrefix("topbar-item-")), true)
            compose.runOnIdle { focused.intValue = index; page.value = SidebarItem.HOME }
            compose.waitForIdle()
            val expected = compose.onNodeWithTag(tag).captureToImage().asAndroidBitmap()
            val bounds = compose.onNodeWithTag(tag).fetchSemanticsNode().boundsInRoot
            try {
                for (destination in SidebarItem.entries) {
                    compose.runOnIdle { page.value = destination }
                    compose.waitForIdle()
                    val actual = compose.onNodeWithTag(tag).captureToImage().asAndroidBitmap()
                    try {
                        assertEquals("Focused $tag moved on $destination", bounds,
                            compose.onNodeWithTag(tag).fetchSemanticsNode().boundsInRoot)
                        assertTrue("Focused $tag changed appearance on $destination", expected.sameAs(actual))
                    } finally { actual.recycle() }
                }
            } finally { expected.recycle() }
        }
    }
}
