package com.arflix.tv.data.model

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RecentPlayedSourceTest {

    @Test
    fun matchesTheSameUrl() {
        val played = stream(url = "https://addon/play/abc")
        val streams = listOf(stream(url = "https://addon/play/other"), stream(url = "https://addon/play/abc"))

        assertThat(findRecentSourceMatch(streams, recordOf(played))).isSameInstanceAs(streams[1])
    }

    @Test
    fun matchesTheSameTorrentFileWhenTheUrlChanged() {
        val played = stream(url = "https://addon/play/old", infoHash = "ABCDEF", fileIdx = 2)
        val streams = listOf(
            stream(url = "https://addon/play/x", infoHash = "abcdef", fileIdx = 1),
            stream(url = "https://addon/play/y", infoHash = "abcdef", fileIdx = 2),
        )

        assertThat(findRecentSourceMatch(streams, recordOf(played))).isSameInstanceAs(streams[1])
    }

    @Test
    fun matchesTheSameFilenameIgnoringCase() {
        val played = stream(url = "https://addon/play/old", filename = "Movie.2024.2160p.REMUX.mkv")
        val streams = listOf(
            stream(url = "https://addon/play/new", filename = "movie.2024.2160p.remux.mkv"),
        )

        assertThat(findRecentSourceMatch(streams, recordOf(played))).isSameInstanceAs(streams[0])
    }

    @Test
    fun fallsBackToTheSourceText() {
        val played = stream(source = "Movie 1080p WEB-DL", url = "https://addon/play/old")
        val streams = listOf(
            stream(source = "Movie 720p", url = "https://addon/play/a"),
            stream(source = "Movie 1080p WEB-DL", url = "https://addon/play/b"),
        )

        assertThat(findRecentSourceMatch(streams, recordOf(played))).isSameInstanceAs(streams[1])
    }

    @Test
    fun prefersTheStrongestMatch() {
        val played = stream(source = "Shared title", url = "https://addon/play/exact")
        val streams = listOf(
            stream(source = "Shared title", url = "https://addon/play/other"),
            stream(source = "Shared title", url = "https://addon/play/exact"),
        )

        assertThat(findRecentSourceMatch(streams, recordOf(played))).isSameInstanceAs(streams[1])
    }

    @Test
    fun theSameFileFromAnotherAddonIsNotTheRecentSource() {
        val played = stream(addonId = "torrentio", url = "https://same/url", filename = "a.mkv")
        val streams = listOf(stream(addonId = "comet", url = "https://same/url", filename = "a.mkv"))

        assertThat(findRecentSourceMatch(streams, recordOf(played))).isNull()
    }

    @Test
    fun noRecordOrNoMatchFindsNothing() {
        val streams = listOf(stream(source = "Something else", url = "https://addon/play/a"))

        assertThat(findRecentSourceMatch(streams, null)).isNull()
        assertThat(findRecentSourceMatch(streams, recordOf(stream(source = "Gone", url = "https://gone")))).isNull()
    }

    @Test
    fun appliesOnlyToTheSameTitleAndEpisode() {
        val record = RecentPlayedSource.of(MediaType.TV, 42, 1, 5, stream(), playedAtMs = 1L)

        assertThat(record.appliesTo(MediaType.TV, 42, 1, 5)).isTrue()
        assertThat(record.appliesTo(MediaType.TV, 42, 1, 6)).isFalse()
        assertThat(record.appliesTo(MediaType.TV, 43, 1, 5)).isFalse()
        assertThat(record.appliesTo(MediaType.MOVIE, 42, null, null)).isFalse()
    }

    private fun recordOf(played: StreamSource) =
        RecentPlayedSource.of(MediaType.MOVIE, 7, null, null, played, playedAtMs = 1L)

    private fun stream(
        addonId: String = "torrentio",
        source: String = "Movie",
        url: String? = null,
        infoHash: String? = null,
        fileIdx: Int? = null,
        filename: String? = null,
    ) = StreamSource(
        source = source,
        addonName = "Torrentio",
        addonId = addonId,
        quality = "1080p",
        size = "4 GB",
        url = url,
        infoHash = infoHash,
        fileIdx = fileIdx,
        behaviorHints = filename?.let { StreamBehaviorHints(filename = it) },
    )
}
