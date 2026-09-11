package com.arflix.tv.ui.screens.tv.live

import android.os.SystemClock
import android.view.KeyEvent as NativeKeyEvent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.arflix.tv.data.model.IptvChannel
import com.arflix.tv.data.repository.favoriteChannelsWithMembership
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GuideFavoriteActionDeviceTest {
    @get:Rule val compose = createComposeRule()

    @Test fun firstRemoteClickAfterLongPressAddsFavoriteOnceWithoutPlayingChannel() {
        val channel = IptvChannel("menu:1", "Test Channel", "https://example.test/live", "News").enrichForFastStartup(1)
        val menu = mutableStateOf<ChannelMenuState?>(null)
        val favorites = mutableStateOf(emptyList<String>())
        val guard = GuideSelectKeyGuard()
        val focus = FocusRequester()
        var additions = 0
        var tunes = 0
        compose.setContent {
            val actions = buildChannelMenuActions(false, false, onToggleFavorite = {
                favorites.value = favoriteChannelsWithMembership(favorites.value, channel.id, true)
                additions++
                menu.value = null
            }, onMoveUp = {}, onMoveDown = {}, onOpenVariants = {})
            Box(Modifier.onPreviewKeyEvent { event ->
                val select = event.key == Key.DirectionCenter || event.key == Key.Enter
                if (select && guard.consume(event.nativeKeyEvent.downTime, event.nativeKeyEvent.eventTime,
                        event.type == KeyEventType.KeyDown, event.nativeKeyEvent.repeatCount)) {
                    true
                } else if (menu.value != null) {
                    if (select && event.type == KeyEventType.KeyDown && event.nativeKeyEvent.repeatCount == 0) {
                        guard.blockCurrentPress(includeSyntheticBurst = true)
                        actions.first().onClick()
                    }
                    true
                } else false
            }) {
                ChannelRow(channel, System.currentTimeMillis(), null, isActive = false,
                    isFavorite = channel.id in favorites.value, onClick = { tunes++ },
                    onLongPress = { fromKeyHold ->
                        if (fromKeyHold) guard.blockCurrentPress(includeSyntheticBurst = true)
                        menu.value = ChannelMenuState(channel.id, channel.name, false, false)
                    }, modifier = Modifier.width(400.dp).focusRequester(focus))
                menu.value?.let { state ->
                    ChannelContextMenu(state, actions, onDismiss = { menu.value = null },
                        onFocusedIndexChange = {}, onAction = { actions[it].onClick() })
                }
            }
            LaunchedEffect(Unit) { focus.requestFocus() }
        }
        compose.waitForIdle()
        val automation = InstrumentationRegistry.getInstrumentation().uiAutomation
        fun event(down: Long, action: Int, repeats: Int = 0) {
            check(automation.injectInputEvent(NativeKeyEvent(down, SystemClock.uptimeMillis(), action,
                NativeKeyEvent.KEYCODE_DPAD_CENTER, repeats), true))
        }
        val hold = SystemClock.uptimeMillis()
        event(hold, NativeKeyEvent.ACTION_DOWN)
        SystemClock.sleep(500)
        event(hold, NativeKeyEvent.ACTION_DOWN, 1)
        compose.waitForIdle()
        compose.runOnIdle { assertNotNull(menu.value) }
        event(hold, NativeKeyEvent.ACTION_UP)
        SystemClock.sleep(120)
        val click = SystemClock.uptimeMillis()
        event(click, NativeKeyEvent.ACTION_DOWN)
        event(click, NativeKeyEvent.ACTION_UP)
        compose.runOnIdle {
            assertEquals(listOf(channel.id), favorites.value)
            assertEquals(1, additions)
            assertEquals(0, tunes)
            assertNull(menu.value)
        }
    }
}
