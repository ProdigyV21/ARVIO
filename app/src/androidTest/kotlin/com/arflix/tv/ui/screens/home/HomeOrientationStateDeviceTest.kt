package com.arflix.tv.ui.screens.home

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performScrollToIndex
import androidx.compose.ui.unit.dp
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class HomeOrientationStateDeviceTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()

    @Test fun switchingOrientationRestoresBothIndependentScrollPositions() {
        val landscape = mutableStateOf(false)
        lateinit var activeState: LazyListState
        compose.setContent {
            HomeOrientationState(landscape.value) {
                activeState = rememberLazyListState()
                LazyColumn(Modifier.size(300.dp, 200.dp).testTag("rows"), state = activeState) {
                    items(40) { Box(Modifier.height(60.dp)) }
                }
            }
        }
        compose.onNodeWithTag("rows").performScrollToIndex(12)
        compose.runOnIdle { assertEquals(12, activeState.firstVisibleItemIndex) }
        compose.runOnIdle { landscape.value = true }
        compose.runOnIdle { assertEquals(0, activeState.firstVisibleItemIndex) }
        compose.onNodeWithTag("rows").performScrollToIndex(5)
        compose.runOnIdle { landscape.value = false }
        compose.runOnIdle { assertEquals(12, activeState.firstVisibleItemIndex) }
        compose.runOnIdle { landscape.value = true }
        compose.runOnIdle { assertEquals(5, activeState.firstVisibleItemIndex) }
    }
}
