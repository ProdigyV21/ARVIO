package com.arflix.tv.ui.screens.details

import com.arflix.tv.data.model.Episode
import com.arflix.tv.data.model.EpisodeIdentity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class DetailsEpisodeReturnTest {
    private fun season(watched: Boolean) = (1..200).map {
        Episode(id = it, episodeNumber = it, seasonNumber = 1, name = "Episode $it", isWatched = watched)
    }

    @Test fun `return from episode 155 keeps its position even after being marked watched`() {
        for (watched in listOf(false, true)) {
            assertEquals(154, detailEpisodeReturnIndex(season(watched), EpisodeIdentity.canonical(1, 155)))
        }
    }

    @Test fun `final episode stays visible when entire season is watched`() {
        assertEquals(199, detailEpisodeReturnIndex(season(true), EpisodeIdentity.canonical(1, 200)))
    }

    @Test fun `anime display episode and season do not replace canonical coordinates`() {
        val episodes = (1..200).map {
            Episode(id = it, episodeNumber = it, seasonNumber = 2, name = "Episode $it",
                identity = EpisodeIdentity(displaySeason = 2, displayEpisode = it, tmdbSeason = 1, tmdbEpisode = it + 200))
        }
        assertEquals(154, detailEpisodeReturnIndex(episodes, EpisodeIdentity.canonical(1, 355)))
    }

    @Test fun `missing or different season target allows normal initial selection`() {
        assertNull(detailEpisodeReturnIndex(emptyList(), EpisodeIdentity.canonical(1, 155)))
        assertNull(detailEpisodeReturnIndex(season(false), EpisodeIdentity.canonical(2, 155)))
        assertNull(detailEpisodeReturnIndex(season(false), null))
    }
}
