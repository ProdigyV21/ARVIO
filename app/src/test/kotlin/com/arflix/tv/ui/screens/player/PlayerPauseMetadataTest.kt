package com.arflix.tv.ui.screens.player

import com.arflix.tv.data.model.Episode
import com.arflix.tv.data.model.EpisodeIdentity
import com.arflix.tv.data.model.MediaType
import org.junit.Assert.assertEquals
import org.junit.Test

class PlayerPauseMetadataTest {
    private val episode = Episode(
        id = 3, seasonNumber = 3, episodeNumber = 3,
        name = "Episode three", overview = "The episode synopsis."
    )

    @Test fun episodeSynopsisTakesPrecedenceOverShowSynopsis() {
        assertEquals("The episode synopsis.", overview(listOf(episode)))
    }

    @Test fun anotherEpisodeCannotSupplyTheSynopsis() {
        assertEquals("The show synopsis.", overview(listOf(episode.copy(episodeNumber = 4,
            identity = EpisodeIdentity.canonical(3, 4)))))
    }

    @Test fun sameEpisodeNumberInAnotherSeasonCannotSupplyTheSynopsis() {
        assertEquals("The show synopsis.", overview(listOf(episode.copy(seasonNumber = 2,
            identity = EpisodeIdentity.canonical(2, 3)))))
    }

    @Test fun blankEpisodeSynopsisFallsBackToShowSynopsis() {
        assertEquals("The show synopsis.", overview(listOf(episode.copy(overview = "  "))))
    }

    @Test fun missingEpisodeFallsBackToShowSynopsis() {
        assertEquals("The show synopsis.", overview(emptyList()))
    }

    @Test fun mappedAnimeUsesCanonicalPlaybackCoordinates() {
        val mapped = episode.copy(seasonNumber = 1, episodeNumber = 15,
            identity = EpisodeIdentity(1, 15, 3, 3))
        assertEquals("The episode synopsis.", overview(listOf(mapped)))
    }

    @Test fun moviesKeepTheirOwnSynopsis() {
        assertEquals("The movie synopsis.", playerPauseOverview(
            MediaType.MOVIE, 3, 3, listOf(episode), "The movie synopsis."
        ))
    }

    @Test fun absentMetadataProducesNoPlaceholderDescription() {
        assertEquals("", playerPauseOverview(MediaType.TV, 3, 3, emptyList(), null))
    }

    @Test fun unknownEpisodeCoordinatesUseFallback() {
        assertEquals("The show synopsis.", playerPauseOverview(
            MediaType.TV, null, null, listOf(episode), "The show synopsis."
        ))
    }

    @Test fun synopsisWhitespaceIsTrimmed() {
        assertEquals("The episode synopsis.", overview(listOf(episode.copy(overview = " The episode synopsis. \n"))))
    }

    private fun overview(episodes: List<Episode>) = playerPauseOverview(
        MediaType.TV, 3, 3, episodes, "The show synopsis."
    )
}
