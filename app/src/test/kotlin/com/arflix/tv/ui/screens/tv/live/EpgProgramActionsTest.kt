package com.arflix.tv.ui.screens.tv.live

import com.arflix.tv.data.model.IptvProgram
import com.arflix.tv.data.model.IptvNowNext
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runTest
import org.junit.Test

class EpgProgramActionsTest {

    @Test
    fun stalledVodLookupStopsAtDeadline() = runTest {
        val result = runEpgLookupWithTimeout(timeoutMillis = 100L) {
            delay(1_000L)
            "late result"
        }

        assertThat(result).isNull()
    }

    @Test
    fun eagerGuideLookupWaitsForLiveProgrammeUpdate() = runTest {
        val updates = MutableStateFlow<IptvProgram?>(null)
        val expected = IptvProgram(
            title = "Live Movie",
            startUtcMillis = 1_000L,
            endUtcMillis = 2_000L,
        )
        backgroundScope.launch {
            delay(250L)
            updates.value = expected
        }

        val result = awaitLiveEpgProgram(
            programUpdates = updates,
            timeoutMillis = 1_000L,
            nowMillis = { 1_500L },
        )

        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun eagerGuideLookupStopsWhenNoProgrammeArrives() = runTest {
        val result = awaitLiveEpgProgram(
            programUpdates = MutableStateFlow<IptvProgram?>(null),
            timeoutMillis = 100L,
            nowMillis = { 1_500L },
        )

        assertThat(result).isNull()
    }

    @Test
    fun watchLiveFromVodDialogUsesFullScreenLivePlayback() {
        assertThat(epgDialogWatchLiveAction())
            .isEqualTo(EpgInteractionAction.PlayLiveFullscreen)
    }


    @Test
    fun sportsChannelDoesNotOfferVodSearch() {
        assertThat(
            epgChannelAllowsVodSearch(
                channelName = "Sky Sports Main Event",
                channelGroup = "UK Sports",
            )
        ).isFalse()
    }

    @Test
    fun onlyMatchingMovieOrSeriesTitleIsEligibleForStreaming() {
        val unrelated = MediaItem(id = 1, title = "Football Highlights", mediaType = MediaType.TV)
        val exactMovie = MediaItem(id = 2, title = "The Lord of the Rings", year = "2001", mediaType = MediaType.MOVIE)

        assertThat(
            selectConfidentEpgVodMatch(
                programTitle = "The Lord of the Rings (2001)",
                results = listOf(unrelated, exactMovie),
            )
        ).isEqualTo(exactMovie)
    }

    @Test
    fun spidermanDoesNotSelectBrandNewDay() {
        val brandNewDay = MediaItem(
            id = 969681,
            title = "Spider-Man: Brand New Day",
            year = "2026",
            mediaType = MediaType.MOVIE,
        )
        val original = MediaItem(
            id = 557,
            title = "Spider-Man",
            year = "2002",
            mediaType = MediaType.MOVIE,
        )

        assertThat(
            selectConfidentEpgVodMatch(
                programTitle = "Spiderman",
                results = listOf(brandNewDay, original),
            )
        ).isEqualTo(original)
    }

    @Test
    fun descriptionYearDisambiguatesSameTitleRemakes() {
        val animatedSeries = MediaItem(
            id = 888,
            title = "Spider-Man",
            year = "1994",
            mediaType = MediaType.TV,
        )
        val originalMovie = MediaItem(
            id = 557,
            title = "Spider-Man",
            year = "2002",
            mediaType = MediaType.MOVIE,
        )

        assertThat(
            selectConfidentEpgVodMatch(
                programTitle = "Spider-Man",
                programDescription = "The 2002 superhero film starring Tobey Maguire.",
                results = listOf(animatedSeries, originalMovie),
            )
        ).isEqualTo(originalMovie)
    }

    @Test
    fun conflictingYearRejectsOtherwiseExactTitle() {
        val remake = MediaItem(
            id = 609,
            title = "The Thing",
            year = "2011",
            mediaType = MediaType.MOVIE,
        )

        assertThat(
            selectConfidentEpgVodMatch(
                programTitle = "The Thing (1982)",
                results = listOf(remake),
            )
        ).isNull()
    }


    @Test
    fun broadPlaylistGroupDoesNotBlockMovieChannelVodLookup() {
        assertThat(
            epgChannelAllowsVodSearch(
                channelName = "Lifetime Movies",
                channelGroup = "News & Entertainment",
            )
        ).isTrue()
    }

    @Test
    fun dedicatedSportsGroupBlocksAcronymChannelVodLookup() {
        assertThat(
            epgChannelAllowsVodSearch(
                channelName = "ESPN",
                channelGroup = "US Sports",
            )
        ).isFalse()
    }

    @Test
    fun fatalAttractionUsesDescriptionYearToSelectMovie() {
        val olderTvShow = MediaItem(id = 1, title = "Fatal Attraction", year = "2013", mediaType = MediaType.TV)
        val movie = MediaItem(id = 2, title = "Fatal Attraction", year = "1987", mediaType = MediaType.MOVIE)
        val newerTvShow = MediaItem(id = 3, title = "Fatal Attraction", year = "2023", mediaType = MediaType.TV)

        assertThat(
            selectConfidentEpgVodMatch(
                programTitle = "Fatal Attraction",
                programDescription = "Michael Douglas and Glenn Close star in the 1987 thriller.",
                results = listOf(olderTvShow, movie, newerTvShow),
            )
        ).isEqualTo(movie)
    }

    @Test
    fun ambiguousExactTitlesWithoutYearAreRejected() {
        val movie = MediaItem(id = 1, title = "Fatal Attraction", year = "1987", mediaType = MediaType.MOVIE)
        val series = MediaItem(id = 2, title = "Fatal Attraction", year = "2023", mediaType = MediaType.TV)

        assertThat(
            selectConfidentEpgVodMatch(
                programTitle = "Fatal Attraction",
                results = listOf(movie, series),
            )
        ).isNull()
    }

    @Test
    fun channelRowFirstClickTunesLiveMiniPlayer() {
        assertThat(
            channelRowInteractionAction(
                isSamePlayingChannel = false,
            )
        ).isEqualTo(EpgInteractionAction.PlayLiveMini)
    }

    @Test
    fun channelRowSecondClickOpensFullscreenWithoutProgrammeLookup() {
        assertThat(
            channelRowInteractionAction(
                isSamePlayingChannel = true,
            )
        ).isEqualTo(EpgInteractionAction.PlayLiveFullscreen)
    }

    @Test
    fun liveEpgCellFirstClickTunesLiveMiniPlayer() {
        assertThat(
            epgProgramInteractionAction(
                temporalState = EpgTemporalState.Live,
                isSamePlayingChannel = false,
                isCatchupSupported = false,
                vodActionsEnabled = true,
            )
        ).isEqualTo(EpgInteractionAction.PlayLiveMini)
    }

    @Test
    fun liveEpgCellSecondClickResolvesVod() {
        assertThat(
            epgProgramInteractionAction(
                temporalState = EpgTemporalState.Live,
                isSamePlayingChannel = true,
                isCatchupSupported = false,
                vodActionsEnabled = true,
            )
        ).isEqualTo(EpgInteractionAction.ResolveVodOrPlayFullscreen)
    }

    @Test
    fun pastEpgCellWithCatchupStartsCatchup() {
        assertThat(
            epgProgramInteractionAction(
                temporalState = EpgTemporalState.Past,
                isSamePlayingChannel = true,
                isCatchupSupported = true,
                vodActionsEnabled = true,
            )
        ).isEqualTo(EpgInteractionAction.PlayCatchup)
    }

    @Test
    fun pastEpgCellWithoutCatchupShowsItsDetails() {
        assertThat(
            epgProgramInteractionAction(
                temporalState = EpgTemporalState.Past,
                isSamePlayingChannel = false,
                isCatchupSupported = false,
                vodActionsEnabled = true,
            )
        ).isEqualTo(EpgInteractionAction.ShowProgrammeInfo)
    }

    @Test
    fun futureEpgCellShowsDetailsWithRemindMe() {
        assertThat(
            epgProgramInteractionAction(
                temporalState = EpgTemporalState.Future,
                isSamePlayingChannel = false,
                isCatchupSupported = false,
                vodActionsEnabled = true,
            )
        ).isEqualTo(EpgInteractionAction.ShowProgrammeInfo)
    }

    @Test
    fun vodLookupOnlyShowsDialogForConfidentMatch() {
        assertThat(vodLookupResolution(hasVodMatch = true))
            .isEqualTo(EpgInteractionAction.ShowVodDialog)
        assertThat(vodLookupResolution(hasVodMatch = false))
            .isEqualTo(EpgInteractionAction.PlayLiveFullscreen)
    }

    @Test
    fun invalidatedVodLookupCannotPublishItsResult() {
        val guard = EpgVodLookupGuard()
        val staleLookup = guard.beginLookup()

        guard.invalidate()

        assertThat(guard.isCurrent(staleLookup)).isFalse()
        assertThat(guard.isCurrent(guard.beginLookup())).isTrue()
    }

    @Test
    fun vodLookupPublishesOnlyWhileSelectedProgrammeIsStillCurrentAndLive() {
        val selected = IptvProgram(
            title = "Live Movie",
            startUtcMillis = 1_000L,
            endUtcMillis = 2_000L,
        )
        val refreshedSameProgramme = selected.copy(description = "Updated description")

        assertThat(epgVodLookupCanPublish(selected, refreshedSameProgramme, nowMillis = 1_500L)).isTrue()
        assertThat(epgVodLookupCanPublish(selected, refreshedSameProgramme, nowMillis = 2_000L)).isFalse()
    }

    @Test
    fun vodLookupCannotPublishAfterGuideRollsToNextProgramme() {
        val selected = IptvProgram(
            title = "Live Movie",
            startUtcMillis = 1_000L,
            endUtcMillis = 2_000L,
        )
        val next = IptvProgram(
            title = "Next Movie",
            startUtcMillis = 2_000L,
            endUtcMillis = 3_000L,
        )

        assertThat(epgVodLookupCanPublish(selected, next, nowMillis = 2_100L)).isFalse()
        assertThat(epgVodLookupCanPublish(selected, currentProgram = null, nowMillis = 1_500L)).isFalse()
    }

    @Test
    fun secondPlaylistReusesCurrentProgrammeFromMatchingGuideIdentity() {
        val movie = IptvProgram(
            title = "Live Movie",
            startUtcMillis = 1_000L,
            endUtcMillis = 2_000L,
        )
        val identities = mapOf(
            "playlist-a:101" to guideIdentityKeys("movie-channel", "Movie Channel"),
            "playlist-b:202" to guideIdentityKeys("other-epg-id", "Movie Channel"),
        )

        assertThat(
            guideProgramForAction(
                channelId = "playlist-b:202",
                guideIdentityKeys = identities.getValue("playlist-b:202"),
                guideByChannelId = mapOf("playlist-a:101" to IptvNowNext(now = movie)),
                guideIdentityKeysByChannelId = identities,
            )
        ).isEqualTo(movie)
    }

    @Test
    fun unrelatedPlaylistChannelCannotBorrowProgramme() {
        val movie = IptvProgram(
            title = "Live Movie",
            startUtcMillis = 1_000L,
            endUtcMillis = 2_000L,
        )

        assertThat(
            guideProgramForAction(
                channelId = "playlist-b:news",
                guideIdentityKeys = guideIdentityKeys("News Channel"),
                guideByChannelId = mapOf("playlist-a:movie" to IptvNowNext(now = movie)),
                guideIdentityKeysByChannelId = mapOf(
                    "playlist-a:movie" to guideIdentityKeys("Movie Channel"),
                    "playlist-b:news" to guideIdentityKeys("News Channel"),
                ),
            )
        ).isNull()
    }
}
