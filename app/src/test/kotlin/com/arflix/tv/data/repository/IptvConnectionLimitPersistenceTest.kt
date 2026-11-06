package com.arflix.tv.data.repository

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.media3.common.Player
import com.arflix.tv.data.api.StalkerApi
import com.arflix.tv.data.model.IptvChannel
import com.arflix.tv.data.model.IptvVodSourceIds
import com.arflix.tv.data.model.StalkerVodLink
import com.arflix.tv.data.model.StreamSource
import com.arflix.tv.network.IptvAccountAvailability
import com.arflix.tv.network.IptvAccountUsage
import com.arflix.tv.network.IptvLatestAccountUsage
import com.arflix.tv.network.IptvPlaybackConnections
import com.arflix.tv.network.MAX_IPTV_USAGE_AGE_MS
import com.arflix.tv.network.IptvProviderAccount
import com.arflix.tv.network.IptvStreamServerFixture
import com.arflix.tv.network.IptvStreamSessionTable
import com.arflix.tv.ui.screens.tv.live.LiveTvGateResult
import com.arflix.tv.ui.screens.tv.live.LiveTvStreamGate
import com.arflix.tv.util.settingsDataStore
import io.mockk.clearMocks
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import io.mockk.verify
import java.util.concurrent.atomic.AtomicInteger
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import org.robolectric.annotation.ConscryptMode

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28], application = Application::class)
@ConscryptMode(ConscryptMode.Mode.OFF)
class IptvConnectionLimitPersistenceTest {
    private lateinit var profiles: ProfileManager
    private lateinit var state: MutableStateFlow<Preferences>
    private val bus = CloudSyncInvalidationBus()
    private val requests = AtomicInteger()

    private val xtreamUrl = "http://panel.example/get.php?username=user&password=pass&type=m3u_plus&output=ts"
    private val xtream = IptvPlaylistEntry("list_a", "Panel", xtreamUrl)
    private val portal = StalkerPortalEntry("stalker1", "Portal", "http://portal.example/c", "00:1A:79:00:00:01")
    private val xtreamAccount = IptvProviderAccount.xtream("http://panel.example", "user", "pass")!!

    private val countingClient = OkHttpClient.Builder().addInterceptor { chain ->
        requests.incrementAndGet()
        Response.Builder().request(chain.request()).protocol(Protocol.HTTP_1_1).code(200).message("OK")
            .body("""{"user_info":{"auth":1,"status":"Active","exp_date":0,"max_connections":"4"}}""".toResponseBody())
            .build()
    }.build()

    private fun usageClient(body: () -> String) = OkHttpClient.Builder().addInterceptor { chain ->
        requests.incrementAndGet()
        Response.Builder().request(chain.request()).protocol(Protocol.HTTP_1_1).code(200).message("OK")
            .body(body().toResponseBody())
            .build()
    }.build()

    private fun repository(client: OkHttpClient = countingClient) =
        IptvRepository(RuntimeEnvironment.getApplication(), client, profiles, bus)

    private fun xtreamChannel(owner: String = "list_a") =
        IptvChannel(id = "$owner:xtream:1", name = "One", streamUrl = "http://cdn.example/live/1.ts", group = "", xtreamStreamId = 1)

    private fun m3uChannel(owner: String) =
        IptvChannel(id = "$owner:abc", name = "One", streamUrl = "http://cdn.example/live/1.ts", group = "")

    private fun stalkerChannel(portalId: String = "stalker1") =
        IptvChannel(id = "stalker:$portalId:9", name = "One", streamUrl = "ffmpeg http://localhost/ch/9_", group = "")

    private fun xtreamVod(owner: String? = "list_a") = StreamSource(
        source = "Movie", addonName = "IPTV VOD", addonId = IptvVodSourceIds.XTREAM, quality = "", size = "",
        url = "http://cdn.example/movie/1.mp4", iptvSourceId = owner
    )

    private fun stalkerVod(url: String, owner: String? = null) = StreamSource(
        source = "Movie", addonName = "IPTV VOD", addonId = IptvVodSourceIds.STALKER, quality = "", size = "",
        url = url, iptvSourceId = owner
    )

    private suspend fun IptvRepository.limitOf(id: String) = observeConnectionLimits().first().getValue(id)

    private suspend fun IptvRepository.stepTo(id: String, limit: IptvConnectionLimit) {
        while (limitOf(id).setting != limit) stepConnectionLimit(id)
    }

    private suspend fun IptvRepository.storeAllowance(playlist: IptvPlaylistEntry, max: Int?, status: String = IptvAccountInfo.STATUS_ACTIVE) =
        saveAccountInfo(playlist.id, IptvAccountInfo(IptvAccountInfoParser.fingerprint(playlist), 1L, status, maxConnections = max))

    private fun prefsKey(name: String) = stringPreferencesKey("main:$name")

    @Before fun setup() {
        // The panel's latest answer is process-wide, so no test may see another's.
        IptvAccountAvailability.latestUsage.record(xtreamAccount, null)
        state = MutableStateFlow(emptyPreferences())
        val lock = Mutex()
        val store = object : DataStore<Preferences> {
            override val data = state
            override suspend fun updateData(transform: suspend (Preferences) -> Preferences): Preferences =
                lock.withLock { transform(state.value).toPreferences().also { state.value = it } }
        }
        mockkStatic("com.arflix.tv.util.DataStoresKt")
        every { any<Context>().settingsDataStore } returns store
        profiles = mockk()
        every { profiles.activeProfileId } returns MutableStateFlow("main")
        every { profiles.getProfileIdSync() } returns "main"
        every { profiles.profileStringKey(any()) } answers { stringPreferencesKey("main:${firstArg<String>()}") }
        every { profiles.profileStringKeyFor(any(), any()) } answers { stringPreferencesKey("${firstArg<String>()}:${secondArg<String>()}") }
    }

    @After fun cleanup() { unmockkStatic("com.arflix.tv.util.DataStoresKt") }

    @Test fun oldUnreadableOrOtherLoginDataReadsAuto() = runBlocking {
        val repo = repository()
        repo.savePlaylists(listOf(xtream))
        repo.saveStalkerPortals(listOf(portal))
        assertNull(state.value[prefsKey("iptv_connection_limits")])
        assertEquals(IptvConnectionLimitState(IptvConnectionLimit.AUTO, 1), repo.limitOf("list_a"))
        context().settingsDataStore.edit { it[prefsKey("iptv_connection_limits")] = "not json" }
        assertEquals(IptvConnectionLimit.AUTO, repo.limitOf("list_a").setting)
        repo.stepConnectionLimit("stalker1")
        assertEquals(IptvConnectionLimit.ONE, repo.limitOf("stalker1").setting)
        repo.saveStalkerPortals(listOf(portal.copy(macAddress = "00:1A:79:00:00:02")))
        assertEquals("a reused id with another MAC", IptvConnectionLimit.AUTO, repo.limitOf("stalker1").setting)
    }

    @Test fun stepSurvivesRecreationAndWrapsBackToAutoRemovingTheKey() = runBlocking {
        val repo = repository()
        repo.savePlaylists(listOf(xtream))
        val seen = (1..4).map {
            repo.stepConnectionLimit("list_a")
            repository().limitOf("list_a").setting
        }
        assertEquals(
            listOf(IptvConnectionLimit.ONE, IptvConnectionLimit.TWO, IptvConnectionLimit.THREE, IptvConnectionLimit.NO_LIMIT),
            seen
        )
        repo.stepConnectionLimit("list_a")
        assertEquals(IptvConnectionLimit.AUTO, repo.limitOf("list_a").setting)
        assertNull(state.value[prefsKey("iptv_connection_limits")])
    }

    @Test fun limitChangeWritesOnlyItsOwnKeyAndCausesNoCloudWriteOrReload() = runBlocking {
        val repo = repository()
        repo.savePlaylists(listOf(xtream))
        val cloudBefore = repo.exportCloudConfigForProfile("main")
        val configBefore = repo.observeConfig().first()
        val prefsBefore = state.value.asMap()
        val busEventsBefore = bus.events.replayCache.toList()
        requests.set(0)
        repo.stepConnectionLimit("list_a")
        val prefsAfter = state.value.asMap()
        val changed = (prefsBefore.keys + prefsAfter.keys).filter { prefsBefore[it] != prefsAfter[it] }.map { it.name }
        assertEquals(listOf("main:iptv_connection_limits"), changed)
        assertEquals(busEventsBefore, bus.events.replayCache.toList())
        assertEquals(0, requests.get())
        assertEquals(configBefore, repo.observeConfig().first())
        assertEquals(cloudBefore, repo.exportCloudConfigForProfile("main"))
    }

    @Test fun autoTrustsOnlyAMatchingActiveAllowanceAndMakesNoNetworkCall() = runBlocking {
        val repo = repository()
        repo.savePlaylists(listOf(xtream))
        val saved = repo.observeConfig().first().playlists.single()
        requests.set(0)
        repo.storeAllowance(saved, 4)
        val policy = repo.streamPolicyFor(xtreamChannel())!!
        assertEquals("no panel answer yet", 1, policy.effectiveLimit)
        assertTrue(policy.panelCheckAllowed)
        assertEquals(1, repo.limitOf("list_a").effectiveLimit)
        repo.saveAccountInfo("list_a", IptvAccountInfo("someone-else", 1L, IptvAccountInfo.STATUS_ACTIVE, maxConnections = 4))
        val stale = repo.streamPolicyFor(xtreamChannel())!!
        assertEquals(1, stale.effectiveLimit)
        assertFalse(stale.panelCheckAllowed)
        assertTrue(stale.homePreviewAllowed)
        repo.storeAllowance(saved, 4, IptvAccountInfo.STATUS_EXPIRED)
        assertFalse(repo.streamPolicyFor(xtreamChannel())!!.panelCheckAllowed)
        assertEquals(0, requests.get())
    }

    @Test fun autoAllowsTwoOnceTheLatestAnswerShowsTwoFreeAndAsksNothingItself() = runBlocking {
        val repo = repository(usageClient { """{"user_info":{"auth":1,"status":"Active","active_cons":"2","max_connections":"4"}}""" })
        repo.savePlaylists(listOf(xtream))
        repo.storeAllowance(repo.observeConfig().first().playlists.single(), 4)
        assertEquals(xtreamAccount, repo.streamPolicyFor(xtreamChannel())!!.account)
        assertEquals(IptvAccountUsage(2, 4), repo.fetchAccountUsage(xtreamAccount))
        requests.set(0)
        repeat(20) {
            val policy = repo.streamPolicyFor(xtreamChannel())!!
            assertEquals(2, policy.effectiveLimit)
            assertTrue(policy.matroskaIndexAllowed)
        }
        assertEquals("same-account zaps reuse the answer", 0, requests.get())
    }

    @Test fun autoStaysAtOneWithOneFreeNoAnswerOrAnAnswerOlderThanAMinute() = runBlocking {
        var body = """{"user_info":{"auth":1,"status":"Active","active_cons":"3","max_connections":"4"}}"""
        val repo = repository(usageClient { body })
        repo.savePlaylists(listOf(xtream))
        repo.storeAllowance(repo.observeConfig().first().playlists.single(), 4)
        assertEquals(IptvAccountUsage(3, 4), repo.fetchAccountUsage(xtreamAccount))
        assertEquals("one free", 1, repo.streamPolicyFor(xtreamChannel())!!.effectiveLimit)
        body = """{"user_info":{"auth":1,"status":"Active","active_cons":"0","max_connections":"4"}}"""
        repo.fetchAccountUsage(xtreamAccount)
        body = "<html>busy</html>"
        assertNull(repo.fetchAccountUsage(xtreamAccount))
        assertEquals("an unknown answer replaces a roomy one", 1, repo.streamPolicyFor(xtreamChannel())!!.effectiveLimit)
        var now = 0L
        val latest = IptvLatestAccountUsage { now }
        latest.record(xtreamAccount, IptvAccountUsage(0, 4))
        now = MAX_IPTV_USAGE_AGE_MS - 1
        assertEquals(IptvAccountUsage(0, 4), latest.fresh(xtreamAccount))
        now = MAX_IPTV_USAGE_AGE_MS
        assertNull(latest.fresh(xtreamAccount))
        assertEquals("stale", 1, iptvStreamPolicy(xtreamAccount, IptvConnectionLimit.AUTO, 4, latest.fresh(xtreamAccount))!!.effectiveLimit)
    }

    @Test fun duplicateEntriesShareOneAccountAndTheStrictestExplicitValueWins() = runBlocking {
        val repo = repository()
        val duplicate = IptvPlaylistEntry("list_b", "Copy", "https://panel.example/player_api.php?username=user&password=pass")
        repo.savePlaylists(listOf(xtream, duplicate))
        assertEquals(repo.streamPolicyFor(xtreamChannel("list_a"))!!.account, repo.streamPolicyFor(xtreamChannel("list_b"))!!.account)
        repo.stepTo("list_b", IptvConnectionLimit.TWO)
        assertEquals("explicit beats Auto", 2, repo.streamPolicyFor(xtreamChannel("list_a"))!!.effectiveLimit)
        repo.stepTo("list_a", IptvConnectionLimit.THREE)
        repo.stepTo("list_b", IptvConnectionLimit.ONE)
        assertEquals(1, repo.streamPolicyFor(xtreamChannel("list_a"))!!.effectiveLimit)
        assertEquals(1, repo.streamPolicyFor(xtreamChannel("list_b"))!!.effectiveLimit)
        assertEquals("each row keeps its own choice", IptvConnectionLimitState(IptvConnectionLimit.THREE, 1), repo.limitOf("list_a"))
        repo.stepTo("list_b", IptvConnectionLimit.NO_LIMIT)
        assertEquals("No limit never outranks an explicit value", 3, repo.streamPolicyFor(xtreamChannel("list_b"))!!.effectiveLimit)
    }

    @Test fun noLimitAnUnknownOwnerOrALocalFileHasNoPolicy() = runBlocking {
        val repo = repository()
        repo.savePlaylists(listOf(xtream, IptvPlaylistEntry("list_f", "File", "file:///sdcard/list.m3u")))
        assertNull(repo.streamPolicyFor(xtreamChannel("gone")))
        assertNull(repo.streamPolicyFor(xtreamVod(owner = null)))
        assertNull(repo.streamPolicyFor(m3uChannel("list_f")))
        repo.stepTo("list_a", IptvConnectionLimit.NO_LIMIT)
        assertNull(repo.streamPolicyFor(xtreamChannel()))
        assertNull(repo.streamPolicyFor(xtreamVod()))
    }

    @Test fun nonIptvAddonNeverReadsTheConfigOrSettings() = runBlocking {
        val repo = repository()
        repo.savePlaylists(listOf(xtream))
        clearMocks(profiles, answers = false, recordedCalls = true)
        val addon = xtreamVod().copy(addonId = "community_addon")
        assertNull(repo.streamPolicyFor(addon))
        assertFalse(repo.isExplicitlyUnlimited(addon))
        verify(exactly = 0) { profiles.activeProfileId }
        verify(exactly = 0) { profiles.profileStringKey(any()) }
    }

    @Test fun createLinkAndIdentityPickTheSamePortal() = runBlocking {
        val other = StalkerPortalEntry("stalker2", "Other", "http://other.example/c", "00:1A:79:00:00:02")
        val repo = repository()
        repo.saveStalkerPortals(listOf(portal, other))
        val firstApi = mockk<StalkerApi>()
        val secondApi = mockk<StalkerApi>()
        coEvery { firstApi.resolveStreamUrl(any()) } returns "http://first.example/tmp"
        coEvery { secondApi.resolveStreamUrl(any()) } returns "http://second.example/tmp"
        IptvRepository::class.java.getDeclaredField("cachedStalkerApis").apply {
            isAccessible = true
            set(repo, mapOf("stalker1" to firstApi, "stalker2" to secondApi))
        }
        assertEquals("http://second.example/tmp", repo.resolveStalkerStreamUrl("stalker:stalker2:9", "cmd"))
        assertEquals("http://first.example/tmp", repo.resolveStalkerStreamUrl("stalker:gone:9", "cmd"))
        assertEquals("http://first.example/tmp", repo.resolveStalkerStreamUrl(null, "cmd"))
        assertEquals(IptvProviderAccount.stalker(other.portalUrl, other.macAddress), repo.streamPolicyFor(stalkerChannel("stalker2"))!!.account)
        assertEquals(IptvProviderAccount.stalker(portal.portalUrl, portal.macAddress), repo.streamPolicyFor(stalkerChannel("gone"))!!.account)
    }

    @Test fun resolvingAStalkerMarkerKeepsItsPortalAsTheOwner() = runBlocking {
        val iptv = mockk<IptvRepository>()
        val direct = "http://cdn.example/tmp/1.mkv"
        coEvery { iptv.resolveStalkerVodStreamUrl(any()) } returns direct
        val streams = StreamRepository(
            context(), mockk(relaxed = true), OkHttpClient(), mockk(relaxed = true), mockk(relaxed = true), iptv,
            mockk(relaxed = true), mockk(relaxed = true), mockk(relaxed = true), mockk(relaxed = true), mockk(relaxed = true)
        )
        val resolved = streams.resolveStreamForPlayback(stalkerVod(StalkerVodLink.buildMarker("stalker1", "cmd")!!))
        assertEquals(direct, resolved?.url)
        assertEquals("stalker1", resolved?.iptvSourceId)
        val repo = repository()
        repo.saveStalkerPortals(listOf(portal))
        assertEquals(IptvProviderAccount.stalker(portal.portalUrl, portal.macAddress), repo.streamPolicyFor(resolved!!)!!.account)
    }

    private fun gateOver(repo: IptvRepository, table: IptvStreamSessionTable, player: Player = mockk(relaxed = true)) =
        LiveTvStreamGate(player, table, policyFor = { repo.streamPolicyFor(it) }, checkAccount = { repo.fetchAccountUsage(it) })

    @Test fun aStalkerChannelOpensOnItsPortalsAccountForFreshAndCachedLinks() = runBlocking {
        val other = StalkerPortalEntry("stalker2", "Other", "http://other.example/c", "00:1A:79:00:00:02")
        val repo = repository()
        repo.saveStalkerPortals(listOf(portal, other))
        val expected = IptvProviderAccount.stalker(other.portalUrl, other.macAddress)!!
        val table = IptvStreamSessionTable()
        val gate = gateOver(repo, table)
        IptvStreamServerFixture().use { media ->
            val client = OkHttpClient.Builder().addInterceptor(IptvPlaybackConnections()).build()
            // A created link carries no account of its own, and neither does the media host it points at.
            val fresh = stalkerChannel("stalker2")
            val cached = fresh.copy(streamUrl = "${media.baseUrl}/ch/9_?token=cached")
            val responses = listOf(fresh, cached).map { channel ->
                assertEquals(LiveTvGateResult.Ready, gate.tune(channel))
                val session = gate.openSession()!!
                assertEquals(expected, session.account)
                assertEquals(1, table.openCount(expected))
                session.callFactory(client)
                    .newCall(Request.Builder().url("${media.baseUrl}/ch/9_?token=link").build())
                    .execute().also { it.body!!.source().readByte() }
            }
            assertEquals(0, table.openCount(IptvProviderAccount.m3u("${media.baseUrl}/ch/9_")!!))
            assertEquals(1, media.peakOpenSessions)
            gate.close()
            responses.forEach { runCatching { it.close() } }
        }
    }

    @Test fun aSportsAddonChannelHasNoAccountAndStaysOnTodaysPath() = runBlocking {
        val repo = repository()
        repo.savePlaylists(listOf(xtream))
        val table = IptvStreamSessionTable()
        val gate = gateOver(repo, table)
        val sports = IptvChannel(id = "sports-addon:abc:1", name = "Match", streamUrl = "http://addon.example/1.m3u8", group = "")
        assertEquals(LiveTvGateResult.Ready, gate.tune(sports))
        assertFalse(gate.isLimited)
        assertNull(gate.openSession())
        assertEquals(0, table.openCount())
        requests.set(0)
        var ranNow = false
        gate.reopen(this, { true }, { error("must not take the limited path") }, { ranNow = true })
        assertTrue(ranNow)
        assertEquals(0, requests.get())
    }

    @Test fun accountUsageAsksOnlyAnXtreamLoginAndStoresNothing() = runBlocking {
        val asked = ArrayList<okhttp3.HttpUrl>()
        val client = OkHttpClient.Builder().addInterceptor { chain ->
            asked.add(chain.request().url)
            Response.Builder().request(chain.request()).protocol(Protocol.HTTP_1_1).code(200).message("OK")
                .body("""{"user_info":{"auth":1,"status":"Active","active_cons":"1","max_connections":"2"}}""".toResponseBody())
                .build()
        }.build()
        val repo = repository(client)
        repo.savePlaylists(listOf(xtream))
        repo.saveStalkerPortals(listOf(portal))
        val before = state.value.asMap()
        val account = repo.streamPolicyFor(xtreamChannel())!!.account
        assertEquals(IptvAccountUsage(1, 2), repo.fetchAccountUsage(account))
        assertEquals("user", asked.single().queryParameter("username"))
        assertEquals("/player_api.php", asked.single().encodedPath)
        assertEquals(before, state.value.asMap())
        assertNull(repo.fetchAccountUsage(IptvProviderAccount.stalker(portal.portalUrl, portal.macAddress)!!))
        assertNull(repo.fetchAccountUsage(IptvProviderAccount.m3u("http://cdn.example/list.m3u")!!))
        assertEquals("Stalker and plain lists are never asked", 1, asked.size)
    }

    @Test fun bootstrapAsksOnceInTheBackgroundAndNeverForPlainOrStalkerSources() = runBlocking {
        val repo = repository()
        repo.savePlaylists(listOf(xtream, IptvPlaylistEntry("list_p", "Plain", "http://cdn.example/some/path/123.m3u")))
        repo.saveStalkerPortals(listOf(portal))
        requests.set(0)
        repeat(3) { repo.bootstrapAccountAllowance("list_a").join() }
        assertEquals(1, requests.get())
        assertEquals(4, repo.observeAccountInfo().first()["list_a"]?.maxConnections)
        assertTrue(repo.streamPolicyFor(xtreamChannel())!!.panelCheckAllowed)
        listOf("list_p", "stalker1", "gone").forEach { repo.bootstrapAccountAllowance(it).join() }
        assertEquals(1, requests.get())
    }

    private fun context(): Context = RuntimeEnvironment.getApplication()
}
