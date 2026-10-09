package com.arflix.tv.ui.screens.details

import android.util.Log
import androidx.lifecycle.ViewModelStore
import com.arflix.tv.data.model.StreamIntegrationType
import com.arflix.tv.data.repository.MediaRepository
import com.arflix.tv.data.repository.ProgressiveStreamResult
import com.arflix.tv.data.repository.StreamIntegrationRepository
import com.arflix.tv.data.repository.StreamRepository
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DetailsStreamCompletionTest {
    @Test
    fun `view model stops spinner only after the last empty provider in every order`() = runTest {
        Dispatchers.setMain(StandardTestDispatcher(testScheduler))
        mockkStatic(Log::class)
        every { Log.d(any(), any()) } returns 0
        every { Log.w(any<String>(), any<String>()) } returns 0
        every { Log.e(any(), any(), any()) } returns 0
        try {
            val orders = listOf(
                listOf(0, 1, 2), listOf(0, 2, 1), listOf(1, 0, 2),
                listOf(1, 2, 0), listOf(2, 0, 1), listOf(2, 1, 0)
            )
            for (order in orders) {
                val gates = List(3) { CompletableDeferred<Unit>() }
                val streams = mockk<StreamRepository>(relaxed = true)
                val media = mockk<MediaRepository>(relaxed = true)
                val integrations = mockk<StreamIntegrationRepository>(relaxed = true)
                every { media.episodeRatingsUpdated } returns MutableSharedFlow<Pair<Int, Int>>()
                every { streams.installedAddons } returns flowOf(emptyList())
                every { integrations.getUnifiedSourceOrderedIds() } returns flowOf(emptyList())
                coEvery { integrations.isIntegrationEnabled(any()) } answers {
                    firstArg<StreamIntegrationType>() != StreamIntegrationType.PLUGINS
                }
                coEvery { streams.hasHomeServerConnections() } returns true
                coEvery { streams.hasIptvVodProviders() } returns true
                coEvery { streams.resolveMovieHomeServerSources(any(), any(), any(), any(), any()) } coAnswers {
                    gates[1].await()
                    emptyList()
                }
                coEvery { streams.resolveMovieVodSources(any(), any(), any(), any(), any(), any()) } coAnswers {
                    gates[2].await()
                    emptyList()
                }
                every { streams.resolveMovieStreamsProgressive(any(), any(), any(), any(), any(), any()) } returns flow {
                    gates[0].await()
                    emit(ProgressiveStreamResult(emptyList(), completedAddons = 0, totalAddons = 0, isFinal = true))
                }
                val model = DetailsViewModel(
                    context = mockk(relaxed = true),
                    mediaRepository = media,
                    mdbListRepository = mockk(relaxed = true),
                    pluginManager = mockk(relaxed = true),
                    profileManager = mockk(relaxed = true),
                    traktRepository = mockk(relaxed = true),
                    remoteSyncManager = mockk(relaxed = true),
                    streamRepository = streams,
                    networkMonitor = mockk(relaxed = true),
                    tmdbPriorityDispatcher = mockk(relaxed = true),
                    animeMapper = mockk(relaxed = true),
                    tmdbApi = mockk(relaxed = true),
                    watchHistoryRepository = mockk(relaxed = true),
                    watchlistRepository = mockk(relaxed = true),
                    cloudSyncRepository = mockk(relaxed = true),
                    launcherContinueWatchingRepository = mockk(relaxed = true),
                    streamIntegrationRepository = integrations,
                    recentPlayedSourceRepository = mockk(relaxed = true)
                )
                val store = ViewModelStore().apply { put("details", model) }
                try {
                    model.loadStreams("tt1234567")
                    runCurrent()
                    assertTrue("Order $order must start loading", model.uiState.value.isLoadingStreams)
                    for ((position, slot) in order.withIndex()) {
                        gates[slot].complete(Unit)
                        runCurrent()
                        assertEquals("Order $order after $slot", position < 2, model.uiState.value.isLoadingStreams)
                    }
                    assertTrue(model.uiState.value.streams.isEmpty())
                    assertTrue(model.uiState.value.hasStreamingAddons)
                } finally {
                    store.clear()
                    runCurrent()
                }
            }
        } finally {
            Dispatchers.resetMain()
            unmockkStatic(Log::class)
        }
    }
}
