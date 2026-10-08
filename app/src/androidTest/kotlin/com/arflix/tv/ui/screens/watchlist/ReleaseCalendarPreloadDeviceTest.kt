package com.arflix.tv.ui.screens.watchlist

import androidx.lifecycle.ViewModelStore
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.arflix.tv.data.model.*
import com.arflix.tv.data.repository.*
import com.arflix.tv.ui.screens.watchlist.calendar.ReleaseCalendarViewModel
import io.mockk.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import java.time.YearMonth

/** Controlled provider delays exercise the real ViewModel without changing account data. */
@RunWith(AndroidJUnit4::class)
class ReleaseCalendarPreloadDeviceTest {
    private suspend fun fixture(block: suspend (ReleaseCalendarViewModel, ReleaseCalendarRepository) -> Unit) {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val profiles = mockk<ProfileManager>()
        every { profiles.activeProfileId } returns MutableStateFlow("calendar-preload-fixture")
        every { profiles.getProfileIdSync() } returns "calendar-preload-fixture"
        val watchlist = mockk<WatchlistRepository>()
        val items = listOf(MediaItem(1, "Fixture movie"))
        every { watchlist.watchlistItems } returns MutableStateFlow(items)
        coEvery { watchlist.getLocalWatchlistItems() } returns items
        val repository = mockk<ReleaseCalendarRepository>()
        coEvery { repository.loadMonth(any(), any(), any(), any(), any(), any(), any()) } returns CalendarMonthResult(emptyList(), emptyList())
        val directory = java.io.File(context.cacheDir, "calendar-preload-test-${System.nanoTime()}")
        val store = ViewModelStore()
        val viewModel = withContext(Dispatchers.Main) {
            ReleaseCalendarViewModel(context, repository, watchlist, profiles, ReleaseCalendarCache(directory))
                .also { store.put("calendar", it) }
        }
        try { block(viewModel, repository) }
        finally {
            withContext(Dispatchers.Main) { store.clear() }
            directory.deleteRecursively()
        }
    }

    private fun progress(): CalendarLoadProgress {
        val month = YearMonth.now()
        val media = MediaItem(1, "Fixture movie")
        val entry = CalendarRelease("fixture", media, month.atDay(4), kind = CalendarReleaseKind.MOVIE, sourceIds = setOf("arvio"))
        return CalendarLoadProgress(CalendarWatchlists("calendar-preload-fixture", mapOf(ReleaseCalendarSource.ARVIO to listOf(media))),
            CalendarMonthResult(listOf(entry), emptyList(), setOf(MediaType.MOVIE to 1)), true, setOf(MediaType.MOVIE to 1))
    }

    @Test fun doesNotFetchBeforeVisibleOrIdleAndForegroundPromotionSurvivesIdleCancellation() = runBlocking {
        fixture { viewModel, repository ->
            val started = CompletableDeferred<Unit>()
            val finish = CompletableDeferred<Unit>()
            coEvery { repository.loadCalendar(any(), any(), any(), any(), any(), any(), any()) } coAnswers {
                started.complete(Unit)
                arg<suspend (CalendarLoadProgress) -> Unit>(6)(progress())
                finish.await()
                progress().month
            }
            delay(300)
            coVerify(exactly = 0) { repository.loadCalendar(any(), any(), any(), any(), any(), any(), any()) }
            val preload = launch(Dispatchers.Main) { viewModel.preloadIdle() }
            withTimeout(10_000) { started.await() }
            withContext(Dispatchers.Main) { viewModel.onVisible() }
            preload.cancelAndJoin()
            finish.complete(Unit)
            val state = withTimeout(10_000) { viewModel.uiState.first { !it.isLoading } }
            assertEquals(listOf("fixture"), state.entries.map { it.id })
            coVerify(exactly = 1) { repository.loadCalendar(any(), any(), any(), any(), any(), any(), any()) }
        }
    }

    @Test fun cancelledPreloadResumesOnEntryEvenWithAFreshCompletedProviderSnapshot() = runBlocking {
        fixture { viewModel, repository ->
            val started = CompletableDeferred<Unit>()
            val cancelled = CompletableDeferred<Unit>()
            coEvery { repository.loadCalendar(any(), any(), any(), any(), any(), any(), any()) } coAnswers {
                arg<suspend (CalendarLoadProgress) -> Unit>(6)(progress().copy(month = CalendarMonthResult(emptyList(), emptyList())))
                started.complete(Unit)
                try { awaitCancellation() } finally { cancelled.complete(Unit) }
            }
            coEvery { repository.loadMonth(any(), any(), any(), any(), any(), false, any()) } returns progress().month
            val preload = launch(Dispatchers.Main) { viewModel.preloadIdle() }
            withTimeout(10_000) { started.await() }
            preload.cancelAndJoin()
            withTimeout(10_000) { cancelled.await() }
            assertTrue(viewModel.uiState.value.isLoading)
            withContext(Dispatchers.Main) { viewModel.onVisible() }
            val resumed = withTimeout(10_000) { viewModel.uiState.first { !it.isLoading } }
            assertEquals(listOf("fixture"), resumed.entries.map { it.id })
            coVerify(exactly = 1) { repository.loadMonth(any(), any(), any(), any(), any(), false, any()) }
        }
    }
}
