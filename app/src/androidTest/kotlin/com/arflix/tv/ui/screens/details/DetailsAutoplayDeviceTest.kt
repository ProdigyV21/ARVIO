package com.arflix.tv.ui.screens.details

import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.arflix.tv.data.model.EpisodeIdentity
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.model.StreamSource
import com.arflix.tv.data.repository.KidsContentFilter
import com.arflix.tv.ui.theme.ArflixTvTheme
import com.arflix.tv.util.DeviceType
import com.arflix.tv.util.LocalDeviceType
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Rule
import org.junit.Test

class DetailsAutoplayDeviceTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private val model = mockk<DetailsViewModel>(relaxed = true)
    private var plays = 0
    private var handled = 0
    private var playedEpisode: EpisodeIdentity? = null
    private var playedPosition: Long? = null

    @Test fun cachedTvWaitsForResumeThenPlaysTheCorrectEpisodeExactlyOnce() {
        val state = show(MediaType.TV)
        compose.runOnIdle {
            assertEquals(0, plays)
            assertEquals(0, handled)
            state.value = state.value.copy(
                isPlaybackTargetReady = true, playSeason = 2, playEpisode = 4,
                playTmdbSeason = 2, playTmdbEpisode = 4, playPositionMs = 90_000L,
            )
        }
        compose.runOnIdle {
            assertEquals(1, plays)
            assertEquals(1, handled)
            assertEquals(EpisodeIdentity.canonical(2, 4), playedEpisode)
            assertEquals(90_000L, playedPosition)
            state.value = state.value.copy(item = state.value.item?.copy(imdbRating = "8.4"))
        }
        compose.runOnIdle { assertEquals(1, plays); assertEquals(1, handled) }
    }

    @Test fun freshShowPlaysFirstEpisodeOnlyAfterTargetResolution() {
        val state = show(MediaType.TV)
        compose.runOnIdle {
            assertEquals(0, plays)
            state.value = state.value.copy(
                isPlaybackTargetReady = true, playSeason = 1, playEpisode = 1,
                playTmdbSeason = 1, playTmdbEpisode = 1,
            )
        }
        compose.runOnIdle {
            assertEquals(1, plays)
            assertEquals(EpisodeIdentity.canonical(1, 1), playedEpisode)
            assertNull(playedPosition)
        }
    }

    @Test fun autoplayOffOpensSourcesForResolvedEpisodeWithoutStartingPlayback() {
        val state = show(MediaType.TV, autoplay = false)
        verify(exactly = 0) { model.loadStreams(any(), any()) }
        compose.runOnIdle {
            state.value = state.value.copy(
                isPlaybackTargetReady = true, playSeason = 2, playEpisode = 4,
                playTmdbSeason = 2, playTmdbEpisode = 4,
            )
        }
        compose.waitForIdle()
        verify(exactly = 1) { model.loadStreams("tt-fixture", EpisodeIdentity.canonical(2, 4)) }
        compose.runOnIdle { assertEquals(0, plays); assertEquals(1, handled) }
    }

    @Test fun movieWaitsForResumePositionBeforeNavigating() {
        val state = show(MediaType.MOVIE)
        compose.runOnIdle {
            assertEquals(0, plays)
            state.value = state.value.copy(isPlaybackTargetReady = true, playPositionMs = 120_000L)
        }
        compose.runOnIdle {
            assertEquals(1, plays)
            assertNull(playedEpisode)
            assertEquals(120_000L, playedPosition)
        }
    }

    @Test fun idleSyncKeepsAutoplayPendingUntilTheRefreshedTargetIsReady() {
        val state = show(MediaType.MOVIE)
        compose.runOnIdle {
            state.value = state.value.copy(isPlaybackTargetReady = true, isSyncingProgress = true, playPositionMs = 90_000L)
        }
        compose.runOnIdle { assertEquals(0, plays); assertEquals(0, handled) }
        compose.runOnIdle {
            state.value = state.value.copy(isSyncingProgress = false, idleSyncRevision = 1L, playPositionMs = 224_000L)
        }
        compose.runOnIdle {
            assertEquals(1, plays)
            assertEquals(1, handled)
            assertEquals(224_000L, playedPosition)
        }
    }

    private fun show(type: MediaType, autoplay: Boolean = true): MutableStateFlow<DetailsUiState> {
        val state = MutableStateFlow(DetailsUiState(
            isLoading = false, item = MediaItem(42, "Autoplay fixture", mediaType = type),
            imdbId = "tt-fixture", autoPlaySingleSource = autoplay,
            streams = listOf(StreamSource("Fixture", "Fixture addon", "fixture", "1080p", "",
                url = "https://example.invalid/fixture.mp4")),
        ))
        every { model.uiState } returns state
        every { model.canStartPlayback(any()) } answers {
            !state.value.isSyncingProgress && firstArg<Long>() == state.value.idleSyncRevision
        }
        every { model.kidsFilterState } returns MutableStateFlow(KidsContentFilter.State(null, "US", emptyMap()))
        every { model.applyKidsFilter(any(), any()) } answers { firstArg() }
        every { model.listPicker } returns MutableStateFlow(null)
        coEvery { model.hasPersonalLists() } returns false
        every { model.resolveEpisodeIdentity(any(), any(), any(), any()) } answers {
            val season = arg<Int?>(0)
            val episode = arg<Int?>(1)
            val tmdbSeason = arg<Int?>(2)
            val tmdbEpisode = arg<Int?>(3)
            if (season == null || episode == null || tmdbSeason == null || tmdbEpisode == null) null
            else EpisodeIdentity(season, episode, tmdbSeason, tmdbEpisode)
        }
        compose.setContent {
            CompositionLocalProvider(LocalDeviceType provides DeviceType.TV) {
                ArflixTvTheme {
                    DetailsScreen(type, 42, autoPlay = true, onAutoPlayHandled = { handled++ }, viewModel = model,
                        onNavigateToPlayer = { _, _, episode, _, _, _, _, position, _ ->
                            plays++
                            playedEpisode = episode
                            playedPosition = position
                        },
                        onNavigateToDetails = { _, _ -> }, onBack = {})
                }
            }
        }
        compose.waitForIdle()
        return state
    }
}
