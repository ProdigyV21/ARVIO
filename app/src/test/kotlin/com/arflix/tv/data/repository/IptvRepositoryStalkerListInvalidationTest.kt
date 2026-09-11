package com.arflix.tv.data.repository

import com.arflix.tv.data.api.StalkerApi
import com.arflix.tv.data.model.IptvChannel
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Test

/**
 * The caller side of [StalkerChannelListLoader].
 *
 * Measured on device on 09.09.2026: the loader itself was correct and its own
 * tests were green, yet toggling a playlist still pulled the full 27.67 MB
 * channel list twice. The second download came from one level up —
 * [IptvRepository.invalidateCache] threw the shared download away, and every
 * source change runs through it.
 *
 * So this test checks the caller, not the loader: a change that leaves the
 * configured portals alone must not cost a second download. What discards a
 * list is a portal disappearing from the configuration, and only that — see
 * [IptvRepositoryStalkerPortalStateTest] for that side and
 * [StalkerChannelListLoaderTest] for the loader itself.
 */
class IptvRepositoryStalkerListInvalidationTest {

    private fun newRepository(): IptvRepository {
        val context = io.mockk.mockk<android.content.Context>(relaxed = true)
        val okHttpClient = io.mockk.mockk<okhttp3.OkHttpClient>(relaxed = true)
        val profileManager = io.mockk.mockk<ProfileManager>(relaxed = true)
        val invalidationBus = io.mockk.mockk<CloudSyncInvalidationBus>(relaxed = true)
        return IptvRepository(context, okHttpClient, profileManager, invalidationBus)
    }

    private fun downloaded() = IptvRepository.StalkerPortalChannels(
        portalId = "portal1",
        api = io.mockk.mockk<StalkerApi>(relaxed = true),
        channels = listOf(
            IptvChannel(
                id = "stalker:portal1:1",
                name = "Channel 1",
                streamUrl = "http://portal.invalid/play/live.php",
                group = "News"
            )
        )
    )

    @Test
    fun `invalidateCache keeps the shared Stalker channel list`() = runBlocking {
        val repository = newRepository()
        var downloads = 0

        repository.stalkerChannelListLoader.load("portal-key") { downloads++; downloaded() }
        // A playlist toggled, an EPG URL edited, a profile switched: every one
        // of these lands here, and none of them touches the Stalker portals.
        repository.invalidateCache()
        repository.stalkerChannelListLoader.load("portal-key") { downloads++; downloaded() }

        assertThat(downloads).isEqualTo(1)
    }

    @Test
    fun `purgeAllIptvSourceCaches keeps the shared Stalker channel list`() = runBlocking {
        // The explicit "Refresh IPTV" runs this before loading. Real fresh
        // data comes from the load that follows it (it passes the moment the
        // user asked), not from tearing up a download another entry point may
        // be running right now.
        val repository = newRepository()
        var downloads = 0

        repository.stalkerChannelListLoader.load("portal-key") { downloads++; downloaded() }
        repository.purgeAllIptvSourceCaches(preserveLiveSnapshot = true)
        repository.stalkerChannelListLoader.load("portal-key") { downloads++; downloaded() }

        assertThat(downloads).isEqualTo(1)
    }

    @Test
    fun `a forced load after invalidateCache still reaches the portal`() = runBlocking {
        // The other half of the promise: keeping the list must not make
        // "Refresh IPTV" serve stale channels. A caller that asks for data
        // newer than its own request still downloads.
        val repository = newRepository()
        var downloads = 0

        repository.stalkerChannelListLoader.load("portal-key") { downloads++; downloaded() }
        repository.invalidateCache()
        val askedAtMs = System.currentTimeMillis() + 1
        repository.stalkerChannelListLoader.load("portal-key", freshSinceMs = askedAtMs) {
            downloads++
            downloaded()
        }

        assertThat(downloads).isEqualTo(2)
    }
}
