package com.arflix.tv.ui.screens.collections

import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performKeyInput
import androidx.compose.ui.test.pressKey
import androidx.lifecycle.ViewModelStore
import com.arflix.tv.R
import com.arflix.tv.data.api.InAppYouTubeExtractor
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.repository.KidsContentFilter
import com.arflix.tv.data.repository.MediaRepository
import com.arflix.tv.data.repository.ProfileManager
import com.arflix.tv.util.DeviceType
import com.arflix.tv.util.LocalDeviceType
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import java.time.LocalDate
import java.util.concurrent.atomic.AtomicBoolean
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalTestApi::class)
class ServiceHomeDeviceTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()

    private val service = MediaRepository.buildPreinstalledDefaults().first { it.id == "collection_service_netflix" }
    private val rowSpecs = ServiceHome.rows(service, LocalDate.now()).associateBy { it.id }
    private val store = ViewModelStore()

    @After fun disposeViewModel() = compose.runOnUiThread { store.clear() }

    @Test fun aLateEarlierRowDoesNotStealTheVisibleSelection() {
        val selected = MediaItem(301, "Selected card")
        val laterRow = ServiceHomeRowState(rowSpecs.getValue(ServiceHome.TOP_RATED_MOVIES_ROW_ID), listOf(selected))
        val earlierRow = ServiceHomeRowState(rowSpecs.getValue(ServiceHome.TOP10_MOVIES_ROW_ID), listOf(MediaItem(302, "Late card")))
        val rows = mutableStateOf(listOf(laterRow))
        val focused = mutableListOf<Int>()
        var opened: MediaItem? = null
        compose.setContent {
            CompositionLocalProvider(LocalDeviceType provides DeviceType.TV) {
                ServiceHomeContent(
                    service, ServiceHomeUiState(page = ServiceHomePage(rows = rows.value)), rows.value,
                    MediaItem(300, "Billboard"), emptyMap(), ServiceHomeTrailerSettings(autoPlay = false),
                    onFocus = { it?.let { item -> focused.add(item.id) } }, onPreloadLogos = {},
                    onFilter = {}, onRetry = {}, onOpen = { item, _ -> opened = item }, onViewAll = {}, onBack = {}
                )
            }
        }
        awaitRemote()
        press(Key.DirectionDown)
        compose.onNodeWithTag("netflix_morph_MOVIE_301", useUnmergedTree = true).assertIsDisplayed()
        compose.runOnIdle { rows.value = listOf(earlierRow, laterRow) }
        compose.onNodeWithTag("netflix_morph_MOVIE_301", useUnmergedTree = true).assertIsDisplayed()
        press(Key.DirectionCenter)
        compose.runOnIdle {
            assertEquals(selected, opened)
            assertEquals(listOf(300, 301), focused)
        }
    }

    @Test fun viewAllOnAFilteredRowCarriesItsQueryIdentity() {
        val rowId = ServiceHome.TOP_RATED_MOVIES_ROW_ID
        val filter = ServiceHome.Filter.MOVIES
        val row = ServiceHome.rows(service, LocalDate.now(), filter).first { it.id == rowId }
        val rows = listOf(ServiceHomeRowState(row, listOf(MediaItem(401, "Rated card"))))
        var request: ServiceHome.ViewAll? = null
        compose.setContent {
            CompositionLocalProvider(LocalDeviceType provides DeviceType.TV) {
                ServiceHomeContent(
                    service, ServiceHomeUiState(filter = filter), rows, MediaItem(400, "Billboard"),
                    emptyMap(), ServiceHomeTrailerSettings(autoPlay = false),
                    onFocus = {}, onPreloadLogos = {}, onFilter = {}, onRetry = {}, onOpen = { _, _ -> },
                    onViewAll = { request = it }, onBack = {}
                )
            }
        }
        awaitRemote()
        press(Key.DirectionDown)
        press(Key.DirectionRight)
        press(Key.DirectionCenter)
        compose.runOnIdle { assertEquals(ServiceHome.ViewAll(rowId, CollectionTab.MOVIES, filter), request) }
        press(Key.Escape)
        press(Key.DirectionUp)
        press(Key.DirectionRight)
        press(Key.DirectionCenter)
        compose.runOnIdle { assertEquals(ServiceHome.grid(filter), request) }
    }

    @Test fun remoteRetryRecoversAnInitiallyFailedServicePage() {
        val online = AtomicBoolean(false)
        val repository = mockk<MediaRepository>(relaxed = true)
        coEvery { repository.loadCollectionCatalogPage(any(), any(), any(), any(), any()) } answers {
            if (!online.get()) MediaRepository.CategoryPageResult(emptyList(), false, sourceLoadFailed = true)
            else MediaRepository.CategoryPageResult(listOf(MediaItem(501, "Recovered title")), false)
        }
        val kids = mockk<KidsContentFilter> {
            every { state } returns MutableStateFlow(KidsContentFilter.State(null, "US", emptyMap()))
        }
        val profiles = mockk<ProfileManager> {
            every { activeProfileId } returns MutableStateFlow("default")
        }
        val model = ServiceHomeViewModel(
            compose.activity.applicationContext, repository, kids, mockk<InAppYouTubeExtractor>(relaxed = true), profiles
        )
        store.put("service", model)
        compose.setContent {
            CompositionLocalProvider(LocalDeviceType provides DeviceType.TV) {
                ServiceHomeScreen(service, { _, _ -> }, {}, {}, model)
            }
        }
        val failure = compose.activity.getString(R.string.collection_failed_load)
        compose.waitUntil(10_000) { compose.onAllNodesWithText(failure).fetchSemanticsNodes().isNotEmpty() }
        compose.onNodeWithText(compose.activity.getString(R.string.retry)).assertIsDisplayed()
        awaitRemote()
        online.set(true)
        press(Key.DirectionCenter)
        compose.waitUntil(10_000) { model.uiState.value.page.rows.isNotEmpty() }
        compose.onNodeWithTag("netflix_row_${ServiceHome.TOP10_SERIES_ROW_ID}", useUnmergedTree = true).assertIsDisplayed()
    }

    private fun awaitRemote() {
        compose.mainClock.advanceTimeBy(350)
        compose.waitForIdle()
    }

    private fun press(key: Key) {
        compose.onNodeWithTag("service_home").performKeyInput { pressKey(key) }
        compose.waitForIdle()
    }
}
