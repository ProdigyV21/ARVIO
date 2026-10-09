package com.arflix.tv.data.repository

import android.content.Context
import com.arflix.tv.data.api.StremioMetaPreview
import com.arflix.tv.data.api.StremioMetaVideo
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.model.SportsAddonCapabilities
import com.google.gson.Gson
import io.mockk.every
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

class AddonNativeCatalogTest {
    @get:Rule val folder = TemporaryFolder()
    // A second device: same cloud history, empty native registry.
    @get:Rule val freshDevice = TemporaryFolder()
    private fun context(dir: java.io.File = folder.root): Context = mockk<Context> {
        every { filesDir } returns dir
    }

    private fun savedPodcastEpisode(type: String?): ContinueWatchingItem {
        val id = AddonNativeCatalog(context(), mockk(relaxed = true)).stableId("provider", "provider:pod")
        return ContinueWatchingItem(id = id, title = "Pod", mediaType = MediaType.TV, progress = 20,
            resumePositionSeconds = 300, season = 1, episode = 2, addonNativeId = "provider:pod",
            addonNativeAddonId = "provider", addonNativeType = type)
    }

    @Test fun freshDeviceRestoreKeepsTheAddonsOwnType() = runBlocking {
        val history = decodeContinueWatchingCache(Gson().toJson(listOf(savedPodcastEpisode("Podcasts"))), Gson())
        val streams = mockk<StreamRepository>(relaxed = true)
        coEvery { streams.getAddonMeta(any(), any(), any()) } returns null
        val device = AddonNativeCatalog(context(freshDevice.root), streams)
        val card = history.single().toMediaItem()
        device.restore(card)
        device.details(MediaType.TV, card.id)
        assertEquals("Podcasts", device.addonType(card.id))
        io.mockk.coVerify { streams.getAddonMeta("provider", "Podcasts", "provider:pod") }
        io.mockk.coVerify(exactly = 0) { streams.getAddonMeta(any(), "series", any()) }
    }

    @Test fun historySavedWithoutTheTypeResolvesItFromTheOwningAddon() = runBlocking {
        val streams = mockk<StreamRepository>(relaxed = true)
        coEvery { streams.getAddonMeta("provider", "series", "provider:pod") } returns null
        coEvery { streams.ownMetaTypes("provider", "provider:pod") } returns listOf("Podcasts")
        coEvery { streams.getAddonMeta("provider", "Podcasts", "provider:pod") } returns
            StremioMetaPreview(id = "provider:pod", name = "Pod", videos = listOf(
                StremioMetaVideo(id = "provider:pod:1:2", season = 1, episode = 2)))
        val device = AddonNativeCatalog(context(freshDevice.root), streams)
        val card = savedPodcastEpisode(type = null).toMediaItem()
        device.restore(card)
        assertEquals(1, device.episodes(card.id, 1).size)
        // Remembered, so stream lookup and later opens use it directly.
        assertEquals("Podcasts", device.addonType(card.id))
        assertEquals("Podcasts", AddonNativeCatalog(context(freshDevice.root), streams).addonType(card.id))
    }

    @Test fun episodeLookupKeepsOpaqueIdsAndSeasonCoordinates() {
        val meta = StremioMetaPreview(videos = listOf(
            StremioMetaVideo(id = "provider:episode/abc?part=2", season = 2, episode = 1),
            StremioMetaVideo(id = "other", season = 1, episode = 1)))
        assertEquals("provider:episode/abc?part=2", nativeEpisodeStreamId(meta, 2, 1))
        assertEquals("other", nativeEpisodeStreamId(meta, 1, 1))
        assertNull(nativeEpisodeStreamId(meta, 2, 2))
        assertNull(nativeEpisodeStreamId(null, 1, 1))
    }

    @Test fun cloudWatchHistoryRestoresIdentityWithoutBrowsingCatalog() = runBlocking {
        val original = AddonNativeCatalog(context(), mockk(relaxed = true))
            .register("provider", StremioMetaPreview(id = "provider:show", name = "Show"), MediaType.TV)!!
        val saved = ContinueWatchingItem(id = original.id, title = original.title, mediaType = MediaType.TV,
            progress = 20, resumePositionSeconds = 300, season = 1, episode = 2,
            addonNativeId = original.addonNativeId, addonNativeAddonId = original.addonNativeAddonId)
        val restoredHistory = decodeContinueWatchingCache(Gson().toJson(listOf(saved)), Gson()).single()
        val card = restoredHistory.toMediaItem()
        assertTrue(card.hasOpenableId)
        val streams = mockk<StreamRepository>(relaxed = true)
        coEvery { streams.getAddonMeta(any(), any(), any()) } returns null
        val restored = AddonNativeCatalog(context(), streams)
        restored.restore(card)
        restored.details(MediaType.TV, card.id)
        val restarted = AddonNativeCatalog(context(), mockk(relaxed = true))
        assertEquals("provider:show", restarted.streamId(card.id))
        assertEquals("provider", restarted.card(MediaType.TV, card.id)?.addonNativeAddonId)
    }

    @Test fun customAddonTypeIsUsedForMetaAndSurvivesRestore() = runBlocking {
        val streams = mockk<StreamRepository>(relaxed = true)
        coEvery { streams.getAddonMeta(any(), any(), any()) } returns null
        val catalog = AddonNativeCatalog(context(), streams)
        val card = catalog.register("provider", StremioMetaPreview(id = "provider:pod", name = "Pod"),
            MediaType.TV, addonType = "Podcasts")!!
        catalog.restore(card)
        catalog.details(MediaType.TV, card.id)
        io.mockk.coVerify { streams.getAddonMeta("provider", "Podcasts", "provider:pod") }
    }

    @Test fun onlyTvTypedItemsAreLiveChannels() {
        val catalog = AddonNativeCatalog(context(), mockk(relaxed = true))
        val live = catalog.register("provider", StremioMetaPreview(id = "provider:ch1", name = "Ch"),
            MediaType.MOVIE, addonType = "tv")!!
        val show = catalog.register("provider", StremioMetaPreview(id = "provider:show", name = "Show"), MediaType.TV)!!
        val pod = catalog.register("provider", StremioMetaPreview(id = "provider:pod", name = "Pod"),
            MediaType.TV, addonType = "Podcasts")!!
        assertTrue(catalog.isLiveChannel(live.id))
        assertFalse(catalog.isLiveChannel(show.id))
        assertFalse(catalog.isLiveChannel(pod.id))
        assertFalse(catalog.isLiveChannel(12345))
    }

    @Test fun connectedTrackersDoNotHideLocalNativePlaybackOrUpNext() {
        val native = ContinueWatchingItem(id = -123, title = "Native", mediaType = MediaType.TV,
            progress = 20, resumePositionSeconds = 300, addonNativeId = "provider:show",
            addonNativeAddonId = "provider")
        assertEquals(listOf(native), ContinueWatchingMerge.merge(emptyList(), listOf(native)))
        val next = native.copy(progress = 0, resumePositionSeconds = 0, isUpNext = true)
        assertEquals(listOf(next), ContinueWatchingMerge.merge(emptyList(), listOf(next)))
        assertTrue(ContinueWatchingMerge.merge(emptyList(), listOf(native.copy(progress = 99))).isEmpty())
    }

    @Test fun nativeVodIsNotLiveButExplicitLiveMarkersStillWin() {
        assertFalse(SportsAddonCapabilities.isLiveStreamOrSportsItem(id = -123, isAddonNative = true))
        assertTrue(SportsAddonCapabilities.isLiveStreamOrSportsItem(id = -123))
        assertTrue(SportsAddonCapabilities.isLiveStreamOrSportsItem(id = -123, isAddonNative = true,
            isLiveStream = true))
        assertTrue(SportsAddonCapabilities.isLiveStreamOrSportsItem(id = -123, isAddonNative = true,
            status = "live:channel"))
    }
}
