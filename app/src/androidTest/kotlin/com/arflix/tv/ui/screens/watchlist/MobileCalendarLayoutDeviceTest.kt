package com.arflix.tv.ui.screens.watchlist

import androidx.compose.runtime.*
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.arflix.tv.data.model.*
import com.arflix.tv.ui.screens.watchlist.calendar.*
import com.arflix.tv.util.DeviceType
import com.arflix.tv.util.LocalDeviceType
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import java.time.LocalDate
import java.time.YearMonth

class MobileCalendarLayoutDeviceTest {
    @get:Rule val compose = createComposeRule()

    @Test fun allMonthDaysSourceSelectionAndReleaseOpeningWorkWithoutMocks() {
        val day = LocalDate.of(2026, 11, 10)
        val release = CalendarRelease("review", MediaItem(42, "Review release"), day,
            kind = CalendarReleaseKind.DIGITAL, sourceIds = setOf("arvio"))
        val state = mutableStateOf(ReleaseCalendarUiState(month = YearMonth.from(day), selectedDate = day,
            entries = listOf(release), watchlistCount = 1, isLoading = false))
        var opened: Int? = null
        compose.setContent {
            CompositionLocalProvider(LocalDeviceType provides DeviceType.PHONE) {
                LibraryCalendarPane(state.value, onSelectDate = { state.value = state.value.copy(selectedDate = it) },
                    onChangeMonth = { offset -> state.value = state.value.copy(month = state.value.month.plusMonths(offset)) },
                    onSelectSource = { state.value = state.value.copy(selectedSourceId = it) }, onRefresh = {},
                    onOpenDetails = { _, id -> opened = id })
            }
        }
        compose.onNodeWithTag("calendar-day-2026-11-01").assertIsDisplayed()
        compose.onNodeWithTag("calendar-day-2026-11-30").assertIsDisplayed()
        compose.onNodeWithTag("calendar-day-count-$day", useUnmergedTree = true).assertTextEquals("1")
        compose.onNodeWithTag("calendar-source-filter").performClick()
        compose.onNodeWithTag("calendar-sources-dialog").assertIsDisplayed()
        compose.onNodeWithTag("calendar-source-arvio").performClick()
        compose.runOnIdle { assertEquals("arvio", state.value.selectedSourceId) }
        compose.onNodeWithTag("calendar-release-review").performScrollTo().performClick()
        compose.runOnIdle { assertEquals(42, opened) }
        compose.onNodeWithTag("calendar-next-month").performScrollTo().performClick()
        compose.onNodeWithTag("calendar-day-2026-12-01").assertIsDisplayed()
        compose.onNodeWithTag("calendar-previous-month").performScrollTo().performClick()
        compose.onNodeWithTag("calendar-day-2026-11-01").assertIsDisplayed()
    }
}
