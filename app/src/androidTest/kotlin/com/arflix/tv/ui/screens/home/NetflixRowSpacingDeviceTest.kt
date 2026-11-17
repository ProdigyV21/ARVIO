package com.arflix.tv.ui.screens.home

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertHeightIsEqualTo
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.dp
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.atomic.AtomicInteger

class NetflixRowSpacingDeviceTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()

    @Test fun spacingChangesLayoutWithoutRecomposingItsContent() {
        val bottom = mutableStateOf(10.dp)
        val compositions = AtomicInteger()
        compose.setContent {
            SideEffect { compositions.incrementAndGet() }
            Box(Modifier.testTag("row").netflixRowSpacing(bottom)) {
                Box(Modifier.size(40.dp))
            }
        }
        compose.onNodeWithTag("row").assertHeightIsEqualTo(50.dp)
        val initialCompositions = compositions.get()
        compose.runOnUiThread { bottom.value = 20.dp }
        compose.onNodeWithTag("row").assertHeightIsEqualTo(60.dp)
        assertEquals(initialCompositions, compositions.get())
        compose.runOnUiThread { bottom.value = 10.dp }
        compose.onNodeWithTag("row").assertHeightIsEqualTo(50.dp)
        assertEquals(initialCompositions, compositions.get())
    }
}
