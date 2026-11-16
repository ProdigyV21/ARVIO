package com.arflix.tv.data.repository

import com.arflix.tv.data.api.YouTubeKidsCorpus
import com.arflix.tv.data.api.YouTubeSearchVideo
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.model.StreamSource
import java.text.Normalizer
import java.util.Locale

/**
 * YouTube as a source of a movie or an episode, like Telegram (Settings > Sources, where it can be
 * switched off). It searches only when the "Search YouTube" row of the source list is pressed —
 * one YouTube request per press, never one per Details visit.
 *
 * Videos shorter than [MIN_LENGTH_SECONDS] are left out: trailers, clips and teasers are what a
 * title search mostly finds, and none of them is the movie or the episode.
 */
internal object YouTubeSource {
    /** The provider id of the Sources setting row. */
    const val PROVIDER_ID = "youtube"
    const val MIN_LENGTH_SECONDS = 5 * 60

    /** What to ask YouTube for: "Title 2024", or "Title season 1 episode 2" for an episode. */
    fun query(mediaType: MediaType, title: String, year: Int?, season: Int?, episode: Int?): String? {
        val name = title.trim().takeIf { it.isNotEmpty() } ?: return null
        return if (mediaType == MediaType.MOVIE) {
            listOfNotNull(name, year?.takeIf { it > 0 }?.toString()).joinToString(" ")
        } else {
            "$name season ${season ?: 1} episode ${episode ?: 1}"
        }
    }

    /**
     * The sources a search answer offers, in YouTube's order: videos of at least
     * [MIN_LENGTH_SECONDS] whose title names the movie or show — by any of [names] (the title
     * shown in the app, the original title). YouTube pads a search with other shows' episodes
     * (another drama's "episode 1" for a show's search); a video titled without the name is dropped.
     */
    fun streams(videos: List<YouTubeSearchVideo>, names: List<String?>): List<StreamSource> {
        val wanted = names.mapNotNull { name -> name?.let(::normalize)?.takeIf { it.isNotEmpty() } }.distinct()
        return videos.filter { video ->
            (video.durationSeconds ?: 0) >= MIN_LENGTH_SECONDS &&
                (wanted.isEmpty() || normalize(video.title).let { title -> wanted.any { " $it " in " $title " } })
        }.map { video ->
            YouTubeCollection.stream(video.id).copy(
                source = video.title,
                size = video.duration.orEmpty(),
                description = listOfNotNull(video.channel, video.duration, video.views).joinToString(" · ")
            )
        }
    }

    /**
     * Searches YouTube for [query] as the profile may see it — the one place both the Search
     * screen and the source list ask YouTube. Without a [kidsCorpus], one regular search. On a
     * kids profile, YouTube Kids at that tier only: every video it returns is one YouTube Kids
     * itself offers that tier. Regular YouTube is never asked, not even when YouTube Kids fails,
     * since nothing would vouch for its videos.
     */
    suspend fun search(
        query: String,
        kidsCorpus: YouTubeKidsCorpus?,
        searcher: suspend (String, YouTubeKidsCorpus?) -> List<YouTubeSearchVideo>
    ): List<YouTubeSearchVideo> = searcher(query, kidsCorpus)

    /**
     * Lowercase words only, so "Spider-Man: No Way Home" matches "spider man no way home",
     * "Schindler's List" matches "Schindlers List" and "Pokémon" matches "Pokemon". Apostrophes
     * and Hebrew geresh/gershayim are dropped (צה"ל = צהל); every other non-letter, non-digit
     * splits words.
     */
    internal fun normalize(text: String): String =
        // Decompose first: a precomposed "é" is one letter until NFD splits off its accent.
        Normalizer.normalize(text, Normalizer.Form.NFD).lowercase(Locale.ROOT)
            .replace(MARKS, "")
            .replace(APOSTROPHES, "")
            .replace(NON_WORD, " ")
            .trim()

    /** Combining marks (Hebrew vowel points, accents) belong to their letter. */
    private val MARKS = Regex("\\p{M}")
    private val APOSTROPHES = Regex("['’`\"״׳]")
    private val NON_WORD = Regex("[^\\p{L}\\p{N}]+")
}
