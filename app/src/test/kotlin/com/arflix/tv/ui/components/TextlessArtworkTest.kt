package com.arflix.tv.ui.components

import com.arflix.tv.data.api.TmdbImage
import com.arflix.tv.data.api.TmdbImagesResponse
import com.arflix.tv.data.api.pickTextlessArtwork
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class TextlessArtworkTest {
    @Test
    fun picksTheBestRatedArtworkWithoutLanguage() {
        val art = pickTextlessArtwork(TmdbImagesResponse(
            posters = listOf(
                TmdbImage("/en.jpg", "en", voteAverage = 9f),
                TmdbImage("/clean-low.jpg", null, voteAverage = 4f),
                TmdbImage("/clean-high.jpg", null, voteAverage = 6f),
            ),
            backdrops = listOf(TmdbImage("/titled.jpg", "en")),
        ))
        assertThat(art.posterPath).isEqualTo("/clean-high.jpg")
        assertThat(art.backdropPath).isNull()
    }

    @Test
    fun cardRatingUsesTheFirstRealScore() {
        assertThat(mobileCardRating("", "7.36", "")).isEqualTo("7.4")
        assertThat(mobileCardRating("8.1/10", "7.0")).isEqualTo("8.1")
        assertThat(mobileCardRating("", "0", "PG-13")).isNull()
    }
}
