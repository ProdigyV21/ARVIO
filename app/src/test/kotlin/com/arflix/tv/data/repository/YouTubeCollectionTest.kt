package com.arflix.tv.data.repository

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class YouTubeCollectionTest {
    @Test
    fun `kids additions require the correct parent PIN and adults do not`() {
        val profile = com.arflix.tv.data.model.Profile(name = "Child", isKidsProfile = true,
            parentPin = com.arflix.tv.util.PinUtil.hashPin("1234"))
        assertFalse(YouTubeCollection.canAddToProfile(profile, null))
        assertFalse(YouTubeCollection.canAddToProfile(profile, "4321"))
        assertFalse(YouTubeCollection.canAddToProfile(profile.copy(parentPin = null), "1234"))
        assertTrue(YouTubeCollection.canAddToProfile(profile, "1234"))
        assertTrue(YouTubeCollection.canAddToProfile(profile.copy(isKidsProfile = false), null))
    }

    @Test
    fun `encoding never silently removes an existing video above the addition limit`() {
        val videos = (0..YouTubeCollection.MAX_VIDEOS).map { YouTubeCollection.Video(it.toString().padStart(11, '0'), "Video $it") }
        assertEquals(videos, YouTubeCollection.decode(YouTubeCollection.encode(videos)))
    }

    @Test
    fun `resolved streams retain split audio even without a muxed fallback`() {
        val source = com.arflix.tv.data.api.TrailerPlaybackSource("https://video.test/v", "https://video.test/a")
        val resolved = YouTubeCollection.resolve(YouTubeCollection.stream("abcDEF12_-x"), source)
        assertEquals(source.videoUrl, resolved.url)
        assertEquals(source.audioUrl, resolved.youTubeAudioUrl)
        assertNull(resolved.youTubeFallbackUrl)
        val muxed = YouTubeCollection.resolve(resolved, source.copy(audioUrl = null, fallbackVideoUrl = "https://video.test/fallback"))
        assertNull(muxed.youTubeAudioUrl)
        assertEquals("https://video.test/fallback", muxed.youTubeFallbackUrl)
    }

    @Test
    fun `video id is read from share, watch, shorts and embed links`() {
        assertEquals("abcDEF12_-x", YouTubeCollection.videoIdFromUrl("https://youtu.be/abcDEF12_-x?si=tracking"))
        assertEquals("abcDEF12_-x", YouTubeCollection.videoIdFromUrl("https://www.youtube.com/watch?v=abcDEF12_-x&t=30"))
        assertEquals("abcDEF12_-x", YouTubeCollection.videoIdFromUrl("youtube.com/watch?feature=share&v=abcDEF12_-x"))
        assertEquals("abcDEF12_-x", YouTubeCollection.videoIdFromUrl("https://m.youtube.com/shorts/abcDEF12_-x"))
        assertEquals("abcDEF12_-x", YouTubeCollection.videoIdFromUrl("https://www.youtube.com/embed/abcDEF12_-x"))
    }

    @Test
    fun `non video links are not youtube videos`() {
        assertNull(YouTubeCollection.videoIdFromUrl("https://trakt.tv/users/someone/lists/some-list"))
        assertNull(YouTubeCollection.videoIdFromUrl("https://www.youtube.com/@SomeChannel"))
        assertNull(YouTubeCollection.videoIdFromUrl("https://www.youtube.com/playlist?list=PL0123456789"))
        assertNull(YouTubeCollection.videoIdFromUrl("https://notyoutube.com/watch?v=abcDEF12_-x"))
    }

    @Test
    fun `videos round trip through the catalog source ref`() {
        val videos = listOf(
            YouTubeCollection.Video("abcDEF12_-x", "First"),
            YouTubeCollection.Video("zyx98765432", "Second")
        )
        val ref = YouTubeCollection.encode(videos)
        assertTrue(ref.startsWith(YouTubeCollection.SOURCE_REF_PREFIX))
        assertEquals(videos, YouTubeCollection.decode(ref))
    }

    @Test
    fun `decode drops malformed entries and falls back to the id for a missing title`() {
        val ref = YouTubeCollection.SOURCE_REF_PREFIX +
            """[{"id":"abcDEF12_-x"},{"id":"bad"},{"title":"no id"},null,{"id":"abcDEF12_-x","title":"dup"}]"""
        assertEquals(listOf(YouTubeCollection.Video("abcDEF12_-x", "abcDEF12_-x")), YouTubeCollection.decode(ref))
        assertTrue(YouTubeCollection.decode("not json").isEmpty())
        assertTrue(YouTubeCollection.decode(null).isEmpty())
    }

    @Test
    fun `meta ids and stream markers carry the video id`() {
        assertEquals("abcDEF12_-x", YouTubeCollection.videoIdFromMetaId(YouTubeCollection.metaId("abcDEF12_-x")))
        assertNull(YouTubeCollection.videoIdFromMetaId("tt0111161"))
        val stream = YouTubeCollection.stream("abcDEF12_-x")
        assertTrue(YouTubeCollection.isStreamMarker(stream.url))
        assertEquals("abcDEF12_-x", YouTubeCollection.videoIdFromStreamMarker(stream.url!!))
        assertFalse(YouTubeCollection.isStreamMarker("https://www.youtube.com/watch?v=abcDEF12_-x"))
    }

    @Test
    fun `an addon stream offering a YouTube video is recognised by ytId or link`() {
        assertEquals("abcDEF12_-x", YouTubeCollection.videoIdOfAddonStream("abcDEF12_-x", null))
        assertEquals("abcDEF12_-x", YouTubeCollection.videoIdOfAddonStream(null, "https://www.youtube.com/watch?v=abcDEF12_-x"))
        assertEquals("abcDEF12_-x", YouTubeCollection.videoIdOfAddonStream(null, "https://youtu.be/abcDEF12_-x"))
        assertNull(YouTubeCollection.videoIdOfAddonStream("not-an-id", "https://example.com/video.m3u8"))
        assertNull(YouTubeCollection.videoIdOfAddonStream(null, null))
    }
}
