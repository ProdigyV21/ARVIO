package com.arflix.tv.ui.screens.watchlist

import android.graphics.Bitmap
import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import coil.imageLoader
import coil.request.ImageRequest
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.model.Profile
import com.arflix.tv.data.model.CalendarRelease
import com.arflix.tv.data.model.CalendarReleaseKind
import com.arflix.tv.data.model.ReleaseCalendarSource
import com.arflix.tv.ui.components.AppTopBarContentTopInset
import com.arflix.tv.ui.components.SidebarItem
import com.arflix.tv.ui.screens.watchlist.calendar.ReleaseCalendarUiState
import com.arflix.tv.ui.theme.ArvioTvTheme
import com.arflix.tv.util.DeviceType
import com.arflix.tv.util.LocalDeviceType
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Assume.assumeTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.File
import java.time.LocalDate
import java.time.LocalTime
import java.time.YearMonth
import java.time.ZoneId

/** Synthetic release dates live only in this test; production data and device profiles are untouched. */
@OptIn(ExperimentalTestApi::class)
@RunWith(AndroidJUnit4::class)
class LibraryCalendarDeviceTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private val viewModel = mockk<WatchlistViewModel>(relaxed = true)
    private val timezone = ZoneId.of("Europe/Amsterdam")
    private val referenceDate = LocalDate.of(2026, 10, 16)
    private val fixtureLogos = mutableMapOf<Int, String>()
    private var calendar by mutableStateOf(ReleaseCalendarUiState())
    private var opened: Pair<MediaType, Int>? = null
    private var refreshRequests = 0

    @Test fun referenceMonthShowsCompleteLibraryShellAndReleaseRail() {
        show()
        focusDay(referenceDate)
        try {
            compose.onNodeWithTag("app-topbar").assertIsDisplayed()
            compose.onNodeWithTag("topbar-item-WATCHLIST").assertIsDisplayed()
            compose.onNodeWithText("October 2026").assertIsDisplayed()
            compose.onNodeWithTag("calendar-day-2026-09-28").assertIsDisplayed()
            compose.onNodeWithTag("calendar-day-2026-11-01").assertIsDisplayed()
            compose.onNodeWithTag("calendar-release-selected-episode").assertIsDisplayed()
            compose.onNodeWithTag("calendar-release-selected-cinema").assertIsDisplayed()
            compose.onNodeWithTag("calendar-release-selected-digital").assertIsDisplayed()
            compose.runOnIdle { assertEquals(5, calendar.selectedDayReleases.size) }

            val fullScreen = compose.onNodeWithTag("oled-library").fetchSemanticsNode().boundsInRoot
            val railCard = compose.onNodeWithTag("calendar-release-selected-episode").fetchSemanticsNode().boundsInRoot
            assertTrue("Release artwork and labels must fit below the month grid", railCard.bottom <= fullScreen.bottom)
            val rail = compose.onNodeWithTag("calendar-release-rail").getUnclippedBoundsInRoot()
            val provenance = compose.onNodeWithText("Trakt + SIMKL", useUnmergedTree = true).getUnclippedBoundsInRoot()
            assertTrue("Release provenance must remain fully visible", provenance.bottom <= rail.bottom)
            val day = compose.onNodeWithTag("calendar-day-$referenceDate").getUnclippedBoundsInRoot()
            val overflow = compose.onNodeWithTag("calendar-more-$referenceDate", useUnmergedTree = true).getUnclippedBoundsInRoot()
            assertTrue("The selected day's release count must remain fully visible", overflow.bottom <= day.bottom)
            assertPosterStrip(referenceDate, 3, "+2")
            // Three releases must also be visible on an unfocused date, without overflow.
            assertPosterStrip(LocalDate.of(2026, 10, 9), 3, null)
        } finally {
            capture("calendar-october-2026")
        }
    }

    @Test fun remoteMovesByDayAndWeekAndOpensTheSelectedRelease() {
        show()
        focusDay(referenceDate)
        key(Key.DirectionRight)
        assertDate(referenceDate.plusDays(1))
        key(Key.DirectionLeft)
        assertDate(referenceDate)
        key(Key.DirectionUp)
        assertDate(referenceDate.minusDays(7))
        key(Key.DirectionDown)
        assertDate(referenceDate)
        key(Key.DirectionCenter)
        compose.onNodeWithTag("calendar-release-selected-episode").assertIsFocused()
        compose.runOnIdle { assertEquals("Opening a day must focus its release without activating it", null, opened) }
        key(Key.DirectionCenter)
        compose.runOnIdle { assertEquals(MediaType.TV to 100088, opened) }
        listOf("selected-cinema", "selected-digital", "selected-physical", "selected-movie").forEach { id ->
            key(Key.DirectionRight)
            val card = compose.onNodeWithTag("calendar-release-$id").assertIsFocused()
            val bounds = card.getUnclippedBoundsInRoot()
            val rail = compose.onNodeWithTag("calendar-release-rail").getUnclippedBoundsInRoot()
            assertTrue("Remote-focused release must be fully visible", bounds.left >= rail.left && bounds.right <= rail.right)
        }
        key(Key.DirectionUp)
        compose.onNodeWithTag("calendar-day-$referenceDate").assertIsFocused()
        key(Key.DirectionCenter)
        compose.onNodeWithTag("calendar-release-selected-episode").assertIsFocused()
    }

    @Test fun monthControlsKeepSelectionInTheVisibleMonth() {
        show(LocalDate.of(2026, 10, 31))
        compose.onNodeWithTag("calendar-next-month").performClick()
        assertDate(LocalDate.of(2026, 11, 30))
        compose.onNodeWithText("November 2026").assertIsDisplayed()
        compose.onNodeWithTag("calendar-previous-month").performClick()
        assertDate(LocalDate.of(2026, 10, 30))
        compose.onNodeWithTag("calendar-today").assertDoesNotExist()
    }

    @Test fun calendarKeepsTheNormalTopBarAndOnlyNecessaryControls() {
        show(selectCalendar = false)
        val tags = listOf("app-topbar", "topbar-profile") + SidebarItem.entries.map { "topbar-item-${it.name}" }
        val normalBounds = tags.map { compose.onNodeWithTag(it).getUnclippedBoundsInRoot() }
        val normalTabs = compose.onNodeWithTag("library-section-tabs").getUnclippedBoundsInRoot()
        val normalCalendarTab = compose.onNodeWithText("Calendar").getUnclippedBoundsInRoot()
        val normalTabHeight = normalCalendarTab.bottom - normalCalendarTab.top
        compose.onNodeWithText("Calendar").performClick()
        compose.waitForIdle()
        assertEquals("Switching Library tabs must not resize or move the shared topbar", normalBounds,
            tags.map { compose.onNodeWithTag(it).getUnclippedBoundsInRoot() })
        compose.onNodeWithTag("app-topbar").assertHeightIsEqualTo(AppTopBarContentTopInset)
        compose.onNodeWithTag("library-section-tabs").assertTopPositionInRootIsEqualTo(AppTopBarContentTopInset)
        val tabs = compose.onNodeWithTag("library-section-tabs").getUnclippedBoundsInRoot()
        assertEquals("Calendar uses the same tab position and height as the other Library pages", normalTabs, tabs)
        val calendarTab = compose.onNodeWithText("Calendar").getUnclippedBoundsInRoot()
        assertEquals(normalTabHeight, calendarTab.bottom - calendarTab.top)
        tags.drop(1).forEach { tag ->
            assertTrue("Calendar content must not overlap a topbar control",
                compose.onNodeWithTag(tag).getUnclippedBoundsInRoot().bottom <= tabs.top)
        }
        listOf("calendar-today", "calendar-refresh").forEach { compose.onNodeWithTag(it).assertDoesNotExist() }
        listOf("Navigate days", "Open day", "Local time", "Amsterdam").forEach {
            compose.onAllNodes(hasText(it, substring = true)).assertCountEquals(0)
        }
        listOf(ReleaseCalendarSource.TRAKT, ReleaseCalendarSource.SIMKL, ReleaseCalendarSource.MDBLIST).forEach {
            compose.onNodeWithTag("calendar-brand-${it.id}", useUnmergedTree = true).assertIsDisplayed()
        }
        capture("calendar-clean-toolbar")
        compose.runOnIdle { compose.activity.onBackPressedDispatcher.onBackPressed() }
        compose.waitForIdle()
        tags.drop(1).forEach { tag ->
            assertTrue("Focused topbar controls must still clear the Calendar tabs",
                compose.onNodeWithTag(tag).getUnclippedBoundsInRoot().bottom <= tabs.top)
        }
        capture("calendar-focused-topbar")
    }

    @Test fun sourceFilterUpdatesTheSelectedDayWithoutLosingItsDate() {
        show()
        compose.onNodeWithTag("calendar-source-filter").performClick()
        compose.onNodeWithTag("calendar-source-${ReleaseCalendarSource.TRAKT.id}").performClick()
        compose.runOnIdle {
            assertEquals(referenceDate, calendar.selectedDate)
            assertEquals(ReleaseCalendarSource.TRAKT.id, calendar.selectedSourceId)
            assertEquals(3, calendar.selectedDayReleases.size)
            assertTrue(calendar.selectedDayReleases.all { ReleaseCalendarSource.TRAKT.id in it.sourceIds })
        }
        compose.onNodeWithTag("calendar-release-selected-episode").assertIsDisplayed()
        compose.onNodeWithTag("calendar-release-selected-cinema").assertDoesNotExist()
        capture("calendar-trakt-filter")
    }

    @Test fun phoneCompactLayoutKeepsMonthAndReleasesUsable() {
        assumeTrue("Run this test with a portrait display below 600dp wide",
            compose.activity.resources.configuration.screenWidthDp < 600)
        show(device = DeviceType.PHONE)
        compose.onNodeWithTag("calendar-source-filter").assertIsDisplayed()
        compose.onNodeWithTag("calendar-day-$referenceDate").assertIsDisplayed().performClick()
        capture("calendar-phone-month")
        compose.onNodeWithTag("calendar-release-selected-episode").performScrollTo().assertIsDisplayed()
        scrollCalendarToBottom()
        compose.onNodeWithText("Trakt + SIMKL", useUnmergedTree = true).assertIsDisplayed()
        capture("calendar-phone-releases")
        compose.onNodeWithTag("calendar-release-selected-episode").performClick()
        compose.runOnIdle { assertEquals(MediaType.TV to 100088, opened) }
    }

    @Test fun sixWeekMonthKeepsReleaseCountsVisibleAndFocusStaysWithinMonth() {
        val selected = referenceDate.plusMonths(1)
        show(selectedDate = selected, configure = ::novemberFixture)
        compose.onAllNodes(hasAnyAncestor(hasTestTag("calendar-month-grid")) and hasClickAction()).assertCountEquals(42)
        compose.onNodeWithTag("calendar-day-2026-10-26").assertIsDisplayed()
        compose.onNodeWithTag("calendar-day-2026-12-06").assertIsDisplayed()
        val count = compose.onNodeWithTag("calendar-more-$selected", useUnmergedTree = true)
            .assertIsDisplayed().assertTextEquals("+2").getUnclippedBoundsInRoot()
        val cell = compose.onNodeWithTag("calendar-day-$selected").getUnclippedBoundsInRoot()
        assertTrue("Dense months must show the additional release count within the selected day", count.bottom <= cell.bottom)
        assertPosterStrip(selected, 3, "+2")
        compose.onNodeWithTag("calendar-release-selected-episode").assertIsDisplayed()
        capture("calendar-six-week-month")

        val first = LocalDate.of(2026, 11, 1)
        focusDay(first)
        repeat(4) { key(Key.DirectionLeft) }
        assertDate(first)
        compose.onNodeWithTag("calendar-day-$first").assertIsFocused()
        key(Key.DirectionUp)
        compose.onNodeWithTag("calendar-previous-month").assertIsFocused()
        key(Key.DirectionDown)
        compose.onNodeWithTag("calendar-day-$first").assertIsFocused()
        focusDay(first.withDayOfMonth(30))
        repeat(4) { key(Key.DirectionRight) }
        assertDate(first.withDayOfMonth(30))
        compose.onNodeWithText("November 2026").assertIsDisplayed()
    }

    @Test fun nativeRemoteRepeatsCannotJumpMonthsOrActivateReleases() {
        show(selectedDate = LocalDate.of(2026, 10, 1))
        focusDay(LocalDate.of(2026, 10, 1))
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        repeat(35) { instrumentation.sendKeyDownUpSync(android.view.KeyEvent.KEYCODE_DPAD_RIGHT) }
        compose.waitForIdle()
        assertDate(LocalDate.of(2026, 10, 31))
        repeat(35) { instrumentation.sendKeyDownUpSync(android.view.KeyEvent.KEYCODE_DPAD_LEFT) }
        compose.waitForIdle()
        assertDate(LocalDate.of(2026, 10, 1))
        compose.onNodeWithTag("calendar-day-2026-10-01").assertIsFocused()
        compose.runOnIdle { assertEquals(null, opened) }
    }

    @Test fun emptyDayRemoteShortcutUsesTheSelectedSourceAndReturnsToItsDay() {
        val emptyDate = LocalDate.of(2026, 10, 3)
        show(selectedDate = emptyDate)
        compose.onNodeWithTag("calendar-source-filter").performClick()
        compose.onNodeWithTag("calendar-source-${ReleaseCalendarSource.TRAKT.id}").performClick()
        focusDay(emptyDate)
        key(Key.DirectionCenter)
        compose.onNodeWithTag("calendar-next-release").assertIsFocused()
        assertDate(emptyDate)
        capture("calendar-empty-day")
        key(Key.DirectionUp)
        compose.onNodeWithTag("calendar-day-$emptyDate").assertIsFocused()
        key(Key.DirectionCenter)
        key(Key.DirectionCenter)
        // SIMKL has a release on the 4th, but the active Trakt filter's next date is the 6th.
        assertDate(LocalDate.of(2026, 10, 6))
        compose.onNodeWithTag("calendar-day-2026-10-06").assertIsFocused()
        compose.onNodeWithTag("calendar-release-severance-6").assertIsDisplayed()

        focusDay(LocalDate.of(2026, 10, 29))
        key(Key.DirectionDown)
        compose.onNodeWithTag("calendar-next-release").assertIsFocused()
        key(Key.DirectionCenter)
        assertDate(LocalDate.of(2026, 10, 30))
        compose.onNodeWithTag("calendar-day-2026-10-30").assertIsFocused()
    }

    @Test fun loadingAndRetryPreserveDayFocusWithoutFalseEmptyWatchlistAdvice() {
        var loadedEntries = emptyList<CalendarRelease>()
        show(configure = { state ->
            loadedEntries = state.entries
            state.copy(entries = emptyList(), isLoading = true, watchlistCount = 0)
        })
        focusDay(referenceDate)
        compose.onNodeWithTag("calendar-loading").assertIsDisplayed()
        compose.onNodeWithTag("calendar-refresh").assertDoesNotExist()
        compose.onAllNodes(hasText("Add movies and shows", substring = true)).assertCountEquals(0)
        compose.onNodeWithText("0 releases").assertDoesNotExist()
        capture("calendar-loading")

        compose.runOnIdle { calendar = calendar.copy(entries = loadedEntries, watchlistCount = 8) }
        compose.onNodeWithTag("calendar-day-$referenceDate").assertIsFocused()
        compose.onNodeWithTag("calendar-release-selected-episode").assertIsDisplayed()
        compose.onNodeWithText("5 releases").assertIsDisplayed()
        compose.runOnIdle { calendar = calendar.copy(isLoading = false) }
        compose.runOnIdle { assertEquals(0, refreshRequests) }

        compose.runOnIdle { calendar = calendar.copy(entries = emptyList(), error = "Fixture source unavailable") }
        focusDay(referenceDate)
        key(Key.DirectionCenter)
        compose.onNodeWithTag("calendar-retry").assertIsFocused()
        compose.runOnIdle { assertEquals("Opening an empty day must focus Retry without refreshing", 0, refreshRequests) }
        key(Key.DirectionUp)
        compose.onNodeWithTag("calendar-day-$referenceDate").assertIsFocused()
        key(Key.DirectionCenter)
        key(Key.DirectionCenter)
        compose.runOnIdle { assertEquals(1, refreshRequests) }
    }

    @Test fun todayMarkerRemainsVisibleWhenAnotherDayIsSelected() {
        val today = LocalDate.now(timezone)
        val otherDay = today.withDayOfMonth(if (today.dayOfMonth == 1) 2 else today.dayOfMonth - 1)
        show(selectedDate = otherDay)
        compose.onNodeWithTag("calendar-today-marker-$today", useUnmergedTree = true).assertIsDisplayed()
        compose.onNodeWithTag("calendar-day-$today").assertIsNotSelected()
        compose.onNodeWithTag("calendar-day-$otherDay").assertIsSelected()
        focusDay(today)
        assertDate(today)
        compose.onNodeWithTag("calendar-day-$today").assertIsSelected()
        compose.onNodeWithTag("calendar-today-marker-$today", useUnmergedTree = true).assertIsDisplayed()
    }

    @Test fun phoneSixWeekMonthHasComfortableControlsAndScrollsToReleaseDetails() {
        assumeTrue("Run this test with a portrait display below 600dp wide",
            compose.activity.resources.configuration.screenWidthDp < 600)
        val selected = referenceDate.plusMonths(1)
        show(selectedDate = selected, device = DeviceType.PHONE, configure = ::novemberFixture)
        listOf("calendar-previous-month", "calendar-next-month", "calendar-source-filter")
            .forEach { tag -> compose.onNodeWithTag(tag).assertHeightIsAtLeast(44.dp).assertWidthIsAtLeast(44.dp) }
        compose.onNodeWithTag("calendar-source-filter").performClick()
        ReleaseCalendarSource.entries.forEach { source ->
            compose.onNodeWithTag("calendar-source-${source.id}").assertHeightIsAtLeast(44.dp)
        }
        compose.onNodeWithTag("calendar-source-${ReleaseCalendarSource.ALL.id}").performClick()
        compose.onNodeWithTag("calendar-day-count-$selected", useUnmergedTree = true).assertTextEquals("5")
        capture("calendar-phone-six-weeks-top")
        compose.onNodeWithTag("calendar-day-2026-12-06").performScrollTo().assertIsDisplayed()
        compose.onNodeWithTag("calendar-release-selected-episode").performScrollTo().assertIsDisplayed()
        scrollCalendarToBottom()
        compose.onNodeWithText("Trakt + SIMKL", useUnmergedTree = true).assertIsDisplayed()
        val provenance = compose.onNodeWithText("Trakt + SIMKL", useUnmergedTree = true).getUnclippedBoundsInRoot()
        val viewport = compose.onNodeWithTag("library-calendar").getUnclippedBoundsInRoot()
        assertTrue("The full release metadata must have bottom breathing room after scrolling", provenance.bottom <= viewport.bottom - 8.dp)
        capture("calendar-phone-six-weeks-releases")
        compose.onNodeWithTag("calendar-release-selected-episode").performClick()
        compose.runOnIdle { assertEquals(MediaType.TV to 100088, opened) }
    }

    private fun novemberFixture(state: ReleaseCalendarUiState) = state.copy(entries = state.entries.map { release ->
        release.copy(date = release.date.plusMonths(1),
            releaseInstant = release.releaseInstant?.atZone(timezone)?.plusMonths(1)?.toInstant())
    })

    private fun show(selectedDate: LocalDate = referenceDate, device: DeviceType = DeviceType.TV, selectCalendar: Boolean = true,
                     configure: (ReleaseCalendarUiState) -> ReleaseCalendarUiState = { it }) {
        val fixtureItems = fixtureMedia()
        calendar = configure(ReleaseCalendarUiState(
            month = YearMonth.from(selectedDate),
            selectedDate = selectedDate,
            sources = ReleaseCalendarSource.entries.toList(),
            selectedSourceId = ReleaseCalendarSource.ALL.id,
            entries = fixtureReleases(fixtureItems),
            isLoading = false,
            timezone = timezone,
            watchlistCount = fixtureItems.size
        ))
        every { viewModel.uiState } returns MutableStateFlow(WatchlistUiState(isLoading = false))
        every { viewModel.libraryState } returns MutableStateFlow(HomeLibraryUiState())
        every { viewModel.logoUrls } returns MutableStateFlow(emptyMap())
        compose.setContent {
            ArvioTvTheme(oledBlackBackground = true, accentColorName = "White") {
                CompositionLocalProvider(LocalDeviceType provides device) {
                    WatchlistScreen(
                        viewModel = viewModel,
                        currentProfile = Profile(id = "calendar-fixture", name = "A", avatarId = 1, avatarColor = 0xFF2475BD),
                        onNavigateToDetails = { type, id -> opened = type to id },
                        calendarStateOverride = calendar,
                        onCalendarSelectDate = { date -> calendar = calendar.copy(selectedDate = date, month = YearMonth.from(date)) },
                        onCalendarChangeMonth = { delta ->
                            val nextMonth = calendar.month.plusMonths(delta)
                            calendar = calendar.copy(month = nextMonth,
                                selectedDate = nextMonth.atDay(calendar.selectedDate.dayOfMonth.coerceAtMost(nextMonth.lengthOfMonth())))
                        },
                        onCalendarSelectSource = { sourceId -> calendar = calendar.copy(selectedSourceId = sourceId) },
                        onCalendarRefresh = { refreshRequests++ }
                    )
                }
            }
        }
        if (selectCalendar) compose.onNodeWithText("Calendar").performClick()
        compose.waitForIdle()
    }

    private fun fixtureMedia(): Map<Int, MediaItem> {
        val context = compose.activity
        val assets = InstrumentationRegistry.getInstrumentation().context.assets
        val directory = File(context.cacheDir, "calendar-fixtures").apply { mkdirs() }
        fun assetUri(name: String): String {
            val file = File(directory, name)
            assets.open("library/$name").use { input -> file.outputStream().use { input.copyTo(it) } }
            val uri = file.toURI().toString()
            // Preload local images so screenshot quality does not depend on network or arbitrary sleeps.
            runBlocking { context.imageLoader.execute(ImageRequest.Builder(context).data(uri).build()) }
            return uri
        }
        data class Title(val id: Int, val title: String, val type: MediaType)
        val titles = listOf(
            Title(136315, "The Bear", MediaType.TV),
            Title(95396, "Severance", MediaType.TV),
            Title(100088, "The Last of Us", MediaType.TV),
            Title(126308, "Shōgun", MediaType.TV),
            Title(693134, "Dune: Part Two", MediaType.MOVIE),
            Title(329865, "Arrival", MediaType.MOVIE),
            Title(157336, "Interstellar", MediaType.MOVIE),
            Title(414906, "The Batman", MediaType.MOVIE)
        )
        return titles.associate { title ->
            val uri = assetUri("${title.id}-backdrop.jpg")
            val poster = assetUri("${title.id}-poster.jpg")
            fixtureLogos[title.id] = assetUri("${title.id}-logo.png")
            title.id to MediaItem(id = title.id, title = title.title, mediaType = title.type, image = poster, backdrop = uri)
        }
    }

    private fun fixtureReleases(media: Map<Int, MediaItem>): List<CalendarRelease> {
        fun release(id: String, mediaId: Int, day: Int, kind: CalendarReleaseKind = CalendarReleaseKind.EPISODE,
                    episode: Int? = null, sources: Set<String> = setOf(ReleaseCalendarSource.TRAKT.id), hour: Int? = 9) =
            CalendarRelease(
                id = id,
                media = media.getValue(mediaId),
                date = LocalDate.of(2026, 10, day),
                releaseInstant = hour?.let { LocalDate.of(2026, 10, day).atTime(LocalTime.of(it, 0)).atZone(timezone).toInstant() },
                kind = kind,
                seasonNumber = if (kind == CalendarReleaseKind.EPISODE) 3 else null,
                episodeNumber = episode,
                sourceIds = sources,
                logoUrl = fixtureLogos[mediaId]
            )
        return buildList {
            add(release("bear-2", 136315, 2, episode = 1))
            listOf(4, 11, 18, 25).forEachIndexed { index, day ->
                add(release("shogun-$day", 126308, day, episode = index + 2, sources = setOf(ReleaseCalendarSource.SIMKL.id), hour = 3))
            }
            listOf(6, 13, 20, 27).forEachIndexed { index, day -> add(release("severance-$day", 95396, day, episode = index + 3)) }
            listOf(8, 15, 22).forEachIndexed { index, day -> add(release("last-of-us-$day", 100088, day, episode = index + 4)) }
            add(release("dune-9", 693134, 9, CalendarReleaseKind.CINEMA, hour = null, sources = setOf(ReleaseCalendarSource.MDBLIST.id)))
            add(release("bear-9", 136315, 9, episode = 2))
            add(release("arrival-9", 329865, 9, CalendarReleaseKind.DIGITAL, hour = null))
            add(release("selected-episode", 100088, 16, episode = 6,
                sources = setOf(ReleaseCalendarSource.TRAKT.id, ReleaseCalendarSource.SIMKL.id), hour = 21))
            add(release("selected-cinema", 329865, 16, CalendarReleaseKind.CINEMA,
                sources = setOf(ReleaseCalendarSource.MDBLIST.id), hour = null))
            add(release("selected-digital", 157336, 16, CalendarReleaseKind.DIGITAL, hour = null))
            add(release("selected-physical", 693134, 16, CalendarReleaseKind.PHYSICAL,
                sources = setOf(ReleaseCalendarSource.MDBLIST.id), hour = null))
            add(release("selected-movie", 414906, 16, CalendarReleaseKind.MOVIE, hour = null))
            add(release("batman-23", 414906, 23, CalendarReleaseKind.DIGITAL, hour = null))
            add(release("last-of-us-30", 100088, 30, episode = 8, hour = 21))
        }
    }

    private fun assertPosterStrip(date: LocalDate, shown: Int, overflow: String?) {
        val cell = compose.onNodeWithTag("calendar-day-$date").getUnclippedBoundsInRoot()
        repeat(shown) { index ->
            val poster = compose.onNodeWithTag("calendar-poster-$date-$index", useUnmergedTree = true)
                .assertIsDisplayed().getUnclippedBoundsInRoot()
            assertTrue("Every poster must fit inside its date", poster.left >= cell.left && poster.right <= cell.right
                && poster.top >= cell.top && poster.bottom <= cell.bottom)
        }
        compose.onNodeWithTag("calendar-poster-$date-$shown", useUnmergedTree = true).assertDoesNotExist()
        val more = compose.onNodeWithTag("calendar-more-$date", useUnmergedTree = true)
        if (overflow == null) more.assertDoesNotExist() else more.assertIsDisplayed().assertTextEquals(overflow)
    }

    private fun focusDay(date: LocalDate) {
        compose.onNodeWithTag("calendar-day-$date").performSemanticsAction(SemanticsActions.RequestFocus)
        compose.waitForIdle()
    }

    private fun key(key: Key) {
        compose.onNodeWithTag("oled-library").performKeyInput { pressKey(key) }
        compose.waitForIdle()
    }

    private fun assertDate(expected: LocalDate) {
        compose.runOnIdle {
            assertEquals(expected, calendar.selectedDate)
            assertEquals(YearMonth.from(expected), calendar.month)
        }
        compose.onNodeWithTag("calendar-day-$expected").assertIsDisplayed()
    }

    private fun scrollCalendarToBottom() {
        compose.onNode(SemanticsMatcher.keyIsDefined(SemanticsProperties.VerticalScrollAxisRange)
            and hasAnyAncestor(hasTestTag("library-calendar")))
            .performSemanticsAction(SemanticsActions.ScrollBy) { it(0f, 10_000f) }
        compose.waitForIdle()
    }

    private fun capture(name: String) {
        compose.waitForIdle()
        val bitmap = compose.onNodeWithTag("oled-library").captureToImage().asAndroidBitmap()
        try {
            File(compose.activity.getExternalFilesDir(null), "$name.png").outputStream().use {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)
            }
        } finally {
            bitmap.recycle()
        }
    }
}
