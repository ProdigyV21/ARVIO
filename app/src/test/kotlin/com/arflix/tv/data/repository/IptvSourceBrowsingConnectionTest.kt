package com.arflix.tv.data.repository

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewModelScope
import com.arflix.tv.data.model.IptvVodSourceIds
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.model.StreamIntegrationType
import com.arflix.tv.data.model.StreamSource
import com.arflix.tv.network.IptvStreamServerFixture
import com.arflix.tv.ui.screens.details.DetailsViewModel
import com.arflix.tv.ui.screens.player.PlayerUiState
import com.arflix.tv.ui.screens.player.PlayerViewModel
import com.arflix.tv.util.settingsDataStore
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import java.lang.reflect.InvocationTargetException
import java.util.concurrent.CopyOnWriteArrayList
import kotlin.coroutines.intrinsics.COROUTINE_SUSPENDED
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.withTimeout
import okhttp3.OkHttpClient
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import org.robolectric.annotation.ConscryptMode

/** A full account must see nothing at all from browsing sources, on the repository and on both view models. */
@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28], application = Application::class)
@ConscryptMode(ConscryptMode.Mode.OFF)
class IptvSourceBrowsingConnectionTest {
    private val fixture = IptvStreamServerFixture()
    private val store = ViewModelStore()
    private val models = mutableListOf<androidx.lifecycle.ViewModel>()
    private lateinit var iptv: IptvRepository
    private lateinit var streams: StreamRepository
    private lateinit var state: MutableStateFlow<Preferences>

    /** A view model's repository: everything is inert except warm-ups, which run the real gate against the panel. */
    private val gated = mockk<StreamRepository>(relaxed = true)
    private val warmCalls = CopyOnWriteArrayList<List<StreamSource>>()
    private val warmsFinished = MutableStateFlow(0)

    private val playlist get() = IptvPlaylistEntry(
        "list_a", "Panel", "${fixture.baseUrl}/get.php?username=user&password=pass&type=m3u_plus&output=ts"
    )

    private fun iptvVod(index: Int, owner: String? = "list_a") = StreamSource(
        "Movie $index", "IPTV VOD", IptvVodSourceIds.XTREAM, "1080p", "2 GB",
        url = "${fixture.baseUrl}/movie/user/pass/$index.mp4", iptvSourceId = owner
    )

    private fun addon(name: String) = StreamSource(name, name, "some-addon", "1080p", "2 GB", url = "${fixture.baseUrl}/cdn/$name.mp4")

    private val catalogue get() = (1..6).map { iptvVod(it) }

    @Before fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        state = MutableStateFlow(emptyPreferences())
        val lock = Mutex()
        val dataStore = object : DataStore<Preferences> {
            override val data = state
            override suspend fun updateData(transform: suspend (Preferences) -> Preferences): Preferences =
                lock.withLock { transform(state.value).toPreferences().also { state.value = it } }
        }
        mockkStatic("com.arflix.tv.util.DataStoresKt")
        every { any<Context>().settingsDataStore } returns dataStore
        val profiles = mockk<ProfileManager>()
        every { profiles.activeProfileId } returns MutableStateFlow("main")
        every { profiles.getProfileIdSync() } returns "main"
        every { profiles.profileStringKey(any()) } answers { stringPreferencesKey("main:${firstArg<String>()}") }
        every { profiles.profileStringKeyFor(any(), any()) } answers { stringPreferencesKey("${firstArg<String>()}:${secondArg<String>()}") }
        iptv = IptvRepository(RuntimeEnvironment.getApplication(), OkHttpClient(), profiles, CloudSyncInvalidationBus())
        streams = streamRepository(iptv)
        fixture.setAccount(active = 1, max = 1)
        coEvery { gated.prewarmStreamsForPlayback(any(), any(), any()) } coAnswers {
            warmCalls += firstArg<List<StreamSource>>()
            try {
                streams.prewarmStreamsForPlayback(firstArg(), secondArg(), thirdArg())
            } finally {
                warmsFinished.update { it + 1 }
            }
        }
        coEvery { gated.prewarmStreamForPlayback(any(), any()) } coAnswers { streams.prewarmStreamForPlayback(firstArg(), secondArg()) }
        coEvery { gated.resolveStreamForPlayback(any()) } coAnswers { firstArg() }
    }

    @After fun cleanup() {
        val jobs = models.mapNotNull { it.viewModelScope.coroutineContext[Job] }
        try {
            store.clear()
            runBlocking { withTimeout(15_000) { jobs.forEach { it.join() } } }
        } finally {
            fixture.close()
            unmockkStatic("com.arflix.tv.util.DataStoresKt")
            Dispatchers.resetMain()
        }
    }

    private fun streamRepository(iptvRepository: IptvRepository) = StreamRepository(
        RuntimeEnvironment.getApplication(), mockk(relaxed = true), OkHttpClient(), mockk(relaxed = true),
        mockk(relaxed = true), iptvRepository, mockk(relaxed = true), mockk(relaxed = true), mockk(relaxed = true),
        mockk(relaxed = true), mockk(relaxed = true)
    )

    private suspend fun choose(limit: IptvConnectionLimit, sourceId: String = "list_a") {
        while (iptv.observeConnectionLimits().first().getValue(sourceId).setting != limit) iptv.stepConnectionLimit(sourceId)
    }

    private suspend fun storeAllowance(max: Int) = iptv.saveAccountInfo(
        "list_a", IptvAccountInfo(IptvAccountInfoParser.fingerprint(playlist), 1L, IptvAccountInfo.STATUS_ACTIVE, maxConnections = max)
    )

    /** List open, many focus moves, then the autoplay top 3, as the view models ask for them. */
    private suspend fun browse(sources: List<StreamSource>) {
        streams.prewarmStreamsForPlayback(sources.take(3), limit = 3)
        repeat(20) { move ->
            val at = move % sources.size
            val candidates = listOf(at, at + 1, at + 2).mapNotNull(sources::getOrNull)
            streams.prewarmStreamsForPlayback(candidates, limit = candidates.size)
        }
        streams.prewarmStreamsForPlayback(sources.take(3), limit = 3)
        sources.forEach { streams.prewarmStreamForPlayback(it) }
    }

    private fun assertPanelSawNothing(reason: String) {
        assertEquals("$reason: stream requests", 0, fixture.streamRequestCount)
        assertEquals("$reason: account checks", 0, fixture.accountCheckCount)
    }

    private fun detailsModel(vodSources: List<StreamSource>, addonSources: List<StreamSource> = emptyList()): DetailsViewModel {
        val media = mockk<MediaRepository>(relaxed = true)
        val integrations = mockk<StreamIntegrationRepository>(relaxed = true)
        every { media.episodeRatingsUpdated } returns MutableSharedFlow<Pair<Int, Int>>()
        every { gated.installedAddons } returns flowOf(emptyList())
        every { integrations.getUnifiedSourceOrderedIds() } returns flowOf(emptyList())
        coEvery { integrations.isIntegrationEnabled(any()) } answers { firstArg<StreamIntegrationType>() != StreamIntegrationType.PLUGINS }
        coEvery { gated.hasHomeServerConnections() } returns false
        coEvery { gated.hasIptvVodProviders() } returns true
        coEvery { gated.resolveMovieVodSources(any(), any(), any(), any(), any(), any()) } returns vodSources
        every { gated.resolveMovieStreamsProgressive(any(), any(), any(), any(), any(), any()) } returns flow {
            emit(ProgressiveStreamResult(addonSources, completedAddons = 1, totalAddons = 1, isFinal = true))
        }
        return DetailsViewModel(
            context = mockk(relaxed = true), mediaRepository = media, mdbListRepository = mockk(relaxed = true),
            pluginManager = mockk(relaxed = true), profileManager = mockk(relaxed = true), traktRepository = mockk(relaxed = true),
            remoteSyncManager = mockk(relaxed = true), streamRepository = gated, networkMonitor = mockk(relaxed = true),
            tmdbPriorityDispatcher = mockk(relaxed = true), animeMapper = mockk(relaxed = true), tmdbApi = mockk(relaxed = true),
            watchHistoryRepository = mockk(relaxed = true), watchlistRepository = mockk(relaxed = true),
            cloudSyncRepository = mockk(relaxed = true), launcherContinueWatchingRepository = mockk(relaxed = true),
            streamIntegrationRepository = integrations, recentPlayedSourceRepository = mockk(relaxed = true),
            personalListsRepository = mockk(relaxed = true), kidsContentFilter = mockk(relaxed = true),
            idleReturnCoordinator = IdleReturnCoordinator(mockk(relaxed = true), mockk(relaxed = true))
        ).also { models += it; store.put("details", it) }
    }

    private fun playerModel(vodSources: List<StreamSource>): PlayerViewModel {
        coEvery { gated.resolveMovieVodSources(any(), any(), any(), any(), any(), any()) } returns vodSources
        return PlayerViewModel(
            context = mockk(relaxed = true), profileManager = mockk(relaxed = true), mediaRepository = mockk(relaxed = true),
            streamRepository = gated, traktRepository = mockk(relaxed = true), remoteSyncManager = mockk(relaxed = true),
            watchHistoryRepository = mockk(relaxed = true), cloudSyncRepository = mockk(relaxed = true),
            launcherContinueWatchingRepository = mockk(relaxed = true), animeMapper = mockk(relaxed = true),
            tmdbApi = mockk(relaxed = true), skipIntroRepository = mockk(relaxed = true),
            playbackTelemetryRepository = mockk(relaxed = true), pluginManager = mockk(relaxed = true),
            streamIntegrationRepository = mockk(relaxed = true), recentPlayedSourceRepository = mockk(relaxed = true),
            kidsContentFilter = mockk(relaxed = true),
            idleReturnCoordinator = IdleReturnCoordinator(mockk(relaxed = true), mockk(relaxed = true))
        ).also { models += it; store.put("player", it) }
    }

    private suspend fun finishWarm(owner: Any, jobField: String) {
        val job = owner.javaClass.getDeclaredField(jobField).apply { isAccessible = true }.get(owner) as Job?
        withTimeout(15_000) { job?.join() }
    }

    private suspend fun DetailsViewModel.openSourceList() {
        loadStreams("tt1234567")
        withTimeout(15_000) { warmsFinished.first { it >= 1 } }
        finishWarm(this, "streamListPrewarmJob")
    }

    private suspend fun DetailsViewModel.moveFocusThrough(sources: List<StreamSource>) {
        repeat(20) { move ->
            prewarmStreamsAround(sources[move % sources.size], sources)
            finishWarm(this, "focusedStreamPrewarmJob")
        }
        prewarmStream(sources.first())
        finishWarm(this, "focusedStreamPrewarmJob")
    }

    private suspend fun PlayerViewModel.moveFocusThrough(sources: List<StreamSource>) {
        repeat(20) { move ->
            prewarmStreamsAround(sources[move % sources.size], sources)
            finishWarm(this, "focusedStreamPrewarmJob")
        }
        prewarmStream(sources.first())
        finishWarm(this, "focusedStreamPrewarmJob")
    }

    /** The real path that ends in autoplay: IPTV VOD arrives, the top 3 warm, then the best one is selected. */
    private suspend fun PlayerViewModel.receiveVodAndAutoplay() {
        javaClass.getDeclaredField("currentMediaId").apply { isAccessible = true }.setInt(this, 42)
        @Suppress("UNCHECKED_CAST")
        val ui = javaClass.getDeclaredField("_uiState").apply { isAccessible = true }.get(this) as MutableStateFlow<PlayerUiState>
        ui.value = ui.value.copy(selectedStream = null, selectedStreamUrl = null)
        invokeSuspend(this, "appendVodSourceInBackground", MediaType.MOVIE, "tt42", null, null, 1_000L)
        withTimeout(15_000) { warmsFinished.first { it >= 1 } }
        finishWarm(this, "streamPrewarmJob")
    }

    private suspend fun invokeSuspend(target: Any, name: String, vararg arguments: Any?): Unit = suspendCoroutine { continuation ->
        try {
            val method = target.javaClass.declaredMethods.single { it.name == name }.apply { isAccessible = true }
            val result = method.invoke(target, *(arguments.toList() + continuation).toTypedArray())
            if (result !== COROUTINE_SUSPENDED) continuation.resume(Unit)
        } catch (error: InvocationTargetException) {
            continuation.resumeWithException(error.targetException)
        }
    }

    @Test fun browsingAFullAccountOpensNothingUnderAutoOrAnyExplicitLimit() = runBlocking {
        iptv.savePlaylists(listOf(playlist))
        browse(catalogue)
        assertPanelSawNothing("Auto without an allowance")
        storeAllowance(1)
        browse(catalogue)
        assertPanelSawNothing("Auto with an allowance")
        for (limit in listOf(IptvConnectionLimit.ONE, IptvConnectionLimit.TWO, IptvConnectionLimit.THREE)) {
            choose(limit)
            browse(catalogue)
            assertPanelSawNothing(limit.name)
        }
    }

    @Test fun addonRowsWarmExactlyAsBeforeAndOnlyNoLimitWarmsIptv() = runBlocking {
        iptv.savePlaylists(listOf(playlist))
        val mixed = listOf(iptvVod(1), addon("a"), addon("b"))
        for (limit in IptvConnectionLimit.entries.reversed()) {
            choose(limit)
            val seen = fixture.events.size
            streams.prewarmStreamsForPlayback(mixed, limit = mixed.size)
            val opened = fixture.events.drop(seen).filter { it.kind == IptvStreamServerFixture.EventKind.OPEN }.map { it.path }.toSet()
            val iptvRow = if (limit == IptvConnectionLimit.NO_LIMIT) setOf("/movie/user/pass/1.mp4") else emptySet()
            assertEquals(limit.name, setOf("/cdn/a.mp4", "/cdn/b.mp4") + iptvRow, opened)
        }
        assertEquals(0, fixture.accountCheckCount)
    }

    @Test fun nonIptvRowsAnswerAsBeforeWithoutAnyPolicyLookup() = runBlocking {
        val policyless = mockk<IptvRepository>(relaxed = true)
        val gate = streamRepository(policyless)
        assertTrue(gate.canPrewarmWithoutSideEffects(addon("a")))
        assertFalse("magnet", gate.canPrewarmWithoutSideEffects(addon("a").copy(url = "magnet:?xt=urn:btih:abc")))
        assertFalse("no url", gate.canPrewarmWithoutSideEffects(addon("a").copy(url = " ")))
        assertFalse("token", gate.canPrewarmWithoutSideEffects(addon("a").copy(url = "https://cdn.example/a.mp4?token=1")))
        assertFalse("debrid", gate.canPrewarmWithoutSideEffects(addon("a").copy(url = "https://x.real-debrid.com/a.mp4")))
        assertFalse("info hash", gate.canPrewarmWithoutSideEffects(addon("a").copy(infoHash = "abc")))
        coVerify(exactly = 0) { policyless.isExplicitlyUnlimited(any()) }
        coVerify(exactly = 0) { policyless.streamPolicyFor(any<StreamSource>()) }
    }

    @Test fun detailsViewModelBrowsingAFullAccountOpensNothing() = runBlocking {
        iptv.savePlaylists(listOf(playlist))
        val sources = catalogue
        val model = detailsModel(sources)
        model.openSourceList()
        model.moveFocusThrough(model.uiState.value.streams)
        assertTrue("the list reached the warm-up path", warmCalls.first().all { IptvVodSourceIds.isIptvVodAddonId(it.addonId) })
        assertEquals(3, warmCalls.first().size)
        assertTrue("focus moves reached it too", warmCalls.size >= 21)
        coVerify(exactly = 1) { gated.prewarmStreamForPlayback(any(), any()) }
        assertPanelSawNothing("Details")
    }

    @Test fun playerViewModelBrowsingAndAutoplayOnAFullAccountOpenNothing() = runBlocking {
        iptv.savePlaylists(listOf(playlist))
        val sources = catalogue
        val model = playerModel(sources)
        model.receiveVodAndAutoplay()
        val selected = withTimeout(15_000) { model.uiState.first { it.selectedStreamUrl != null } }
        assertTrue("autoplay picked IPTV VOD", IptvVodSourceIds.isIptvVodAddonId(selected.selectedStream?.addonId))
        assertTrue("the top 3 reached the warm-up path", warmCalls.first().all { IptvVodSourceIds.isIptvVodAddonId(it.addonId) })
        assertEquals(3, warmCalls.first().size)
        model.moveFocusThrough(sources)
        assertTrue("focus moves reached it too", warmCalls.size >= 21)
        coVerify(exactly = 1) { gated.prewarmStreamForPlayback(any(), any()) }
        assertPanelSawNothing("Player")
    }
}
