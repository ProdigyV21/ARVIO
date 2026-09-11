package com.arflix.tv.data.repository

import com.arflix.tv.data.api.StalkerApi
import com.arflix.tv.data.model.IptvChannel
import com.google.common.truth.Truth.assertThat
import java.util.Collections
import kotlinx.coroutines.runBlocking
import org.junit.Test

/**
 * The two cases Prodigy asked for in his review of #677, both driven through
 * the repository rather than through [StalkerChannelListLoader] alone — the
 * loader's own 13 tests were green while both of these were broken one level up.
 *
 * 1. A partial failure must not be remembered as a success. With portals A and
 *    B configured and B down, the merged channel list is not empty (A filled
 *    it), so a freshness check that looks at the merged result calls the load
 *    reusable and stops asking B for the whole window — even after B recovers.
 * 2. The cleanup for "the last portal was removed" must sit on a path that
 *    actually runs. [IptvRepository.loadStalkerChannels] is not one: every
 *    caller returns before reaching it once no portal is enabled, so the
 *    removed portal's channel list and session stayed in memory until the app
 *    was closed.
 */
class IptvRepositoryStalkerPortalStateTest {

    private fun newRepository(): IptvRepository {
        val context = io.mockk.mockk<android.content.Context>(relaxed = true)
        val okHttpClient = io.mockk.mockk<okhttp3.OkHttpClient>(relaxed = true)
        val profileManager = io.mockk.mockk<ProfileManager>(relaxed = true)
        val invalidationBus = io.mockk.mockk<CloudSyncInvalidationBus>(relaxed = true)
        return IptvRepository(context, okHttpClient, profileManager, invalidationBus)
    }

    private val portalA = StalkerPortalEntry(
        id = "portal-a",
        name = "Portal A",
        portalUrl = "http://portal-a.invalid/c/",
        macAddress = "00:1A:79:00:00:01"
    )

    private val portalB = StalkerPortalEntry(
        id = "portal-b",
        name = "Portal B",
        portalUrl = "http://portal-b.invalid/c/",
        macAddress = "00:1A:79:00:00:02"
    )

    /** What a portal that answered returns: its session and its channels. */
    private fun answered(portal: StalkerPortalEntry) = IptvRepository.StalkerPortalChannels(
        portalId = portal.id,
        api = io.mockk.mockk<StalkerApi>(relaxed = true),
        channels = listOf(
            IptvChannel(
                id = "stalker:${portal.id}:1",
                name = "${portal.name} 1",
                streamUrl = "http://${portal.id}.invalid/play/live.php",
                group = "News"
            )
        )
    )

    /** What a failed handshake returns: no session, no channels. */
    private fun unreachable(portal: StalkerPortalEntry) = IptvRepository.StalkerPortalChannels(
        portalId = portal.id,
        api = null,
        channels = emptyList()
    )

    @Test
    fun `a portal that failed is asked again by the next ordinary load`() = runBlocking<Unit> {
        val repository = newRepository()
        val portals = listOf(portalA, portalB)
        // The two portals are downloaded in parallel, so the order of attempts
        // is not fixed — only how many each portal got.
        val attempts = Collections.synchronizedList(mutableListOf<String>())
        var portalBIsUp = false
        val fetch: suspend (StalkerPortalEntry) -> IptvRepository.StalkerPortalChannels = { portal ->
            attempts += portal.id
            if (portal.id == portalB.id && !portalBIsUp) unreachable(portal) else answered(portal)
        }

        val partial = repository.loadStalkerChannels(portals, fetchPortal = fetch)
        assertThat(partial.second.map { it.id }).containsExactly("stalker:portal-a:1")
        assertThat(partial.first.keys).containsExactly("portal-a")

        portalBIsUp = true
        val recovered = repository.loadStalkerChannels(portals, fetchPortal = fetch)

        // B is asked again straight away instead of waiting out the window,
        // and A is not downloaded a second time to make that possible.
        assertThat(attempts.count { it == portalA.id }).isEqualTo(1)
        assertThat(attempts.count { it == portalB.id }).isEqualTo(2)
        assertThat(recovered.second.map { it.id })
            .containsExactly("stalker:portal-a:1", "stalker:portal-b:1").inOrder()
        assertThat(recovered.first.keys).containsExactly("portal-a", "portal-b")
    }

    @Test
    fun `removing the last portal releases its channel list and session`() = runBlocking<Unit> {
        val repository = newRepository()
        val attempts = Collections.synchronizedList(mutableListOf<String>())
        val fetch: suspend (StalkerPortalEntry) -> IptvRepository.StalkerPortalChannels = { portal ->
            attempts += portal.id
            answered(portal)
        }
        val playlist = IptvPlaylistEntry(
            id = "list-1",
            name = "My Provider",
            m3uUrl = "http://example.invalid/list.m3u",
            enabled = true
        )

        repository.loadStalkerChannels(listOf(portalA), fetchPortal = fetch)

        // An unrelated playlist change runs through the very same path. The
        // portal is untouched, so its download has to survive it — dropping it
        // here is what cost a second full channel list on every toggle.
        repository.ensureCacheOwnership(
            "profile-1",
            IptvConfig(stalkerPortals = listOf(portalA), playlists = listOf(playlist))
        )
        repository.loadStalkerChannels(listOf(portalA), fetchPortal = fetch)
        assertThat(attempts).containsExactly("portal-a")

        // The last portal is removed. Nothing asks for a Stalker channel list
        // any more, so this is the only place the cleanup can still happen.
        repository.ensureCacheOwnership("profile-1", IptvConfig(playlists = listOf(playlist)))

        // Nothing of the removed portal is left: re-adding it downloads again.
        repository.loadStalkerChannels(listOf(portalA), fetchPortal = fetch)
        assertThat(attempts).containsExactly("portal-a", "portal-a")
    }

    @Test
    fun `disabling one of two portals leaves the other one's list alone`() = runBlocking<Unit> {
        val repository = newRepository()
        val attempts = Collections.synchronizedList(mutableListOf<String>())
        val fetch: suspend (StalkerPortalEntry) -> IptvRepository.StalkerPortalChannels = { portal ->
            attempts += portal.id
            answered(portal)
        }

        repository.loadStalkerChannels(listOf(portalA, portalB), fetchPortal = fetch)
        repository.ensureCacheOwnership(
            "profile-1",
            IptvConfig(stalkerPortals = listOf(portalA, portalB.copy(enabled = false)))
        )

        repository.loadStalkerChannels(listOf(portalA), fetchPortal = fetch)
        assertThat(attempts.count { it == portalA.id }).isEqualTo(1)

        // B is gone from memory; enabling it again really re-downloads it.
        repository.loadStalkerChannels(listOf(portalA, portalB), fetchPortal = fetch)
        assertThat(attempts.count { it == portalA.id }).isEqualTo(1)
        assertThat(attempts.count { it == portalB.id }).isEqualTo(2)
    }
}
