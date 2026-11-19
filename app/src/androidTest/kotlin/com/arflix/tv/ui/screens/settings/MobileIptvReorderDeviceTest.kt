package com.arflix.tv.ui.screens.settings

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.unit.dp
import com.arflix.tv.R
import com.arflix.tv.data.repository.IptvPlaylistEntry
import com.arflix.tv.data.repository.StalkerPortalEntry
import com.arflix.tv.util.DeviceType
import com.arflix.tv.util.LocalDeviceType
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class MobileIptvReorderDeviceTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private val playlists = mutableStateOf(listOf("a", "b", "c").map {
        IptvPlaylistEntry(it, "Playlist $it", "https://example.invalid/$it.m3u")
    })
    private val portals = mutableStateOf(listOf("a", "b", "c").map {
        StalkerPortalEntry(it, "Portal $it", "https://example.invalid/$it", "00:00:00:00:00:00")
    })
    private val playlistDeletes = mutableListOf<Set<String>>()
    private val portalDeletes = mutableListOf<Set<String>>()

    @Test fun tenPlaylistsRemainReachableAndTheLastCanBeSelected() {
        playlists.value = (1..10).map { IptvPlaylistEntry("list_$it", "Playlist $it", "https://example.invalid/$it.m3u") }
        portals.value = emptyList()
        fixture()
        for (n in 1..10) compose.onNodeWithText("Playlist $n").performScrollTo().assertIsDisplayed()
        compose.onNodeWithText("Playlist 10").performTouchInput { longClick() }
        deleteSelected()
        assertEquals(listOf(setOf("list_10")), playlistDeletes)
    }

    @Test fun selectedPlaylistKeepsItsIdAfterTheReorderSaveReturns() {
        fixture()
        compose.onNodeWithText("Playlist b").performScrollTo().performTouchInput { longClick() }
        compose.runOnIdle { playlists.value = listOf(playlists.value[1], playlists.value[2], playlists.value[0]) }
        deleteSelected()
        assertEquals(listOf(setOf("b")), playlistDeletes)
        assertEquals(emptyList<Set<String>>(), portalDeletes)
    }

    @Test fun selectedPortalKeepsItsIdAfterTheReorderSaveReturns() {
        playlists.value = emptyList()
        fixture()
        compose.onNodeWithText("Portal b").performScrollTo().performTouchInput { longClick() }
        compose.runOnIdle { portals.value = listOf(portals.value[1], portals.value[2], portals.value[0]) }
        deleteSelected()
        assertEquals(listOf(setOf("b")), portalDeletes)
        assertEquals(emptyList<Set<String>>(), playlistDeletes)
    }

    @Test fun bulkDeleteSendsAllSelectedIdsInOneCall() {
        fixture()
        compose.onNodeWithText("Playlist a").performScrollTo().performTouchInput { longClick() }
        compose.onNodeWithText("Playlist c").performScrollTo().performClick()
        compose.runOnIdle { playlists.value = playlists.value.reversed() }
        deleteSelected()
        assertEquals(listOf(setOf("a", "c")), playlistDeletes)
        assertEquals(emptyList<Set<String>>(), portalDeletes)
    }

    @Test fun switchingSectionsDoesNotSelectSameIdInBothLists() {
        fixture()
        compose.onNodeWithText("Playlist b").performScrollTo().performTouchInput { longClick() }
        compose.onNodeWithText("Portal b").performScrollTo().performClick()
        deleteSelected()
        assertEquals(listOf(setOf("b")), portalDeletes)
        assertEquals(emptyList<Set<String>>(), playlistDeletes)
    }

    private fun deleteSelected() {
        compose.onNodeWithContentDescription(compose.activity.getString(R.string.delete))
            .performScrollTo().performClick()
        compose.waitForIdle()
    }

    private fun fixture() {
        compose.setContent {
            CompositionLocalProvider(LocalDeviceType provides DeviceType.PHONE) {
                val scroll = rememberScrollState()
                Column(Modifier.width(360.dp).fillMaxHeight().background(Color.Black).verticalScroll(scroll)) {
                    IptvSettings(
                        playlists = playlists.value,
                        stalkerPortals = portals.value,
                        channelCount = 0,
                        isLoading = false,
                        error = null,
                        statusMessage = null,
                        statusType = ToastType.INFO,
                        progressText = null,
                        progressPercent = 0,
                        focusedIndex = -1,
                        focusedActionIndex = 0,
                        onConfigure = {},
                        onEditPlaylist = {},
                        onTogglePlaylist = {},
                        onMovePlaylistUp = {},
                        onMovePlaylistDown = {},
                        onDeletePlaylist = { error("Mobile deletion must use IDs, not positions") },
                        onDeleteSelectedPlaylists = { playlistDeletes += it },
                        onDeleteSelectedPortals = { portalDeletes += it },
                        onRefresh = {},
                        onDelete = {},
                        pageScrollState = scroll
                    )
                }
            }
        }
    }
}
