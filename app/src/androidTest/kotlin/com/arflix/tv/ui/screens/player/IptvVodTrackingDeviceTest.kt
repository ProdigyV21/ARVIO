package com.arflix.tv.ui.screens.player

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.viewinterop.AndroidView
import androidx.datastore.preferences.core.*
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.hls.HlsMediaSource
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.media3.ui.PlayerView
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.arflix.tv.data.api.TraktApi
import com.arflix.tv.data.model.*
import com.arflix.tv.data.repository.*
import com.arflix.tv.data.repository.sync.*
import com.arflix.tv.util.Constants
import com.google.gson.JsonParser
import io.mockk.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import okio.Buffer
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Provider

/** Real Media3 playback and real Trakt HTTP serialization, against a local fixture only. */
@RunWith(AndroidJUnit4::class)
@androidx.annotation.OptIn(UnstableApi::class)
class IptvVodTrackingDeviceTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()
    private val instrumentation get() = InstrumentationRegistry.getInstrumentation()
    private val context get() = instrumentation.targetContext
    private val store = ViewModelStore()
    private val requests = CopyOnWriteArrayList<Pair<String, String>>()
    private val history = mockk<WatchHistoryRepository>(relaxed = true)
    private val localTrakt = mockk<TraktRepository>(relaxed = true)
    private lateinit var server: MockWebServer
    private lateinit var model: PlayerViewModel
    private lateinit var client: OkHttpClient
    private val firstFrame = AtomicBoolean(false)

    @Before fun setup(): Unit = runBlocking {
        val bytes = instrumentation.context.assets.open("iptv_vod_unknown_duration.ts").use { it.readBytes() }
        server = MockWebServer().apply {
            dispatcher = object : Dispatcher() {
                override fun dispatch(request: RecordedRequest): MockResponse {
                    val path = request.requestUrl!!.encodedPath
                    if (path == "/episode.ts") return MockResponse().addHeader("Content-Type", "video/mp2t")
                        .setChunkedBody(Buffer().write(bytes), 188 * 4)
                        .throttleBody(188 * 4L, 250, TimeUnit.MILLISECONDS)
                    if (path == "/episode.m3u8") return MockResponse().addHeader("Content-Type", "application/vnd.apple.mpegurl")
                        .setBody("#EXTM3U\n#EXT-X-VERSION:3\n#EXT-X-TARGETDURATION:21\n#EXT-X-MEDIA-SEQUENCE:0\n#EXTINF:20.5,\nepisode.ts\n")
                    if (path.startsWith("/scrobble/")) {
                        assertEquals("Bearer fixture-token", request.getHeader("Authorization"))
                        val body = request.body.readUtf8()
                        requests += path to body
                        val progress = JsonParser.parseString(body).asJsonObject["progress"].asFloat
                        val action = if (path.endsWith("stop") && progress >= 80) "scrobble" else path.substringAfterLast('/')
                        return MockResponse().setResponseCode(201).addHeader("Content-Type", "application/json")
                            .setBody("""{"id":1,"action":"$action","progress":$progress}""")
                    }
                    return MockResponse().setResponseCode(404)
                }
            }
            start()
        }
        client = OkHttpClient()
        val api = Retrofit.Builder().baseUrl(server.url("/")).client(client)
            .addConverterFactory(GsonConverterFactory.create()).build().create(TraktApi::class.java)
        val profiles = mockk<ProfileManager>(relaxed = true) {
            every { getProfileIdSync() } returns "issue893-fixture"
            every { activeProfileId } returns MutableStateFlow("issue893-fixture")
            every { profileStringKey(any()) } answers { stringPreferencesKey("issue893_${firstArg<String>()}") }
            every { profileLongKey(any()) } answers { longPreferencesKey("issue893_${firstArg<String>()}") }
            every { profileBooleanKey(any()) } answers { booleanPreferencesKey("issue893_${firstArg<String>()}") }
        }
        val session = mockk<TraktSession>(relaxed = true) {
            every { currentProfileId() } returns "issue893-fixture"
            coEvery { accessToken(any(), any(), any()) } returns "fixture-token"
        }
        val selections = mockk<SyncProviderStore>(relaxed = true) {
            coEvery { writeProviders() } returns setOf(SyncProvider.TRAKT)
            coEvery { getProvider() } returns SyncProvider.TRAKT
        }
        val networkTrakt = spyk(TraktRepository(context, api, mockk(relaxed = true), client,
            Provider { mockk(relaxed = true) }, profiles, mockk(relaxed = true), selections,
            mockk(relaxed = true), mockk(relaxed = true), ContinueWatchingUpdates(), traktSession = session))
        coEvery { networkTrakt.hasTrakt() } returns true
        coEvery { networkTrakt.markEpisodeWatched(any(), any(), any(), any()) } just runs
        coEvery { networkTrakt.markMovieWatched(any()) } just runs
        coEvery { networkTrakt.deletePlaybackForEpisode(any(), any(), any()) } returns true
        coEvery { networkTrakt.deletePlaybackForContent(any(), any()) } returns true
        val remote = RemoteSyncManager(selections, TraktRemoteProvider(networkTrakt), mockk(relaxed = true), mockk(relaxed = true))
        withContext(Dispatchers.Main) {
            model = PlayerViewModel(
                context, profiles, mockk(relaxed = true), mockk(relaxed = true), localTrakt, remote, history,
                mockk(relaxed = true), mockk(relaxed = true), mockk(relaxed = true), mockk(relaxed = true),
                mockk(relaxed = true), mockk(relaxed = true), mockk(relaxed = true), mockk(relaxed = true),
                mockk(relaxed = true), mockk(relaxed = true),
                IdleReturnCoordinator(mockk(relaxed = true), mockk(relaxed = true))
            )
            store.put("fixture", model)
            field("currentMediaType").set(model, MediaType.TV)
            field("currentMediaId").setInt(model, 1416)
            field("currentSeason").set(model, 22)
            field("currentEpisode").set(model, 15)
        }
    }

    @After fun cleanup(): Unit = runBlocking {
        val job = model.viewModelScope.coroutineContext[Job]
        withContext(Dispatchers.Main) { store.clear() }
        withTimeout(5_000) { job?.join() }
        server.shutdown()
        client.connectionPool.evictAll()
    }

    private fun field(name: String) = PlayerViewModel::class.java.getDeclaredField(name).apply { isAccessible = true }
    @Suppress("UNCHECKED_CAST")
    private fun state() = field("_uiState").get(model) as MutableStateFlow<PlayerUiState>
    private fun select(id: String) {
        state().value = state().value.copy(selectedStream = StreamSource("Episode", "IPTV VOD", id, "", "",
            url = server.url("/episode.ts").toString()), expectedDurationMs = 20_500)
        val manifest = AddonManifest("live-parent", "IPTV", "1", types = listOf("tv"))
        field("currentInstalledAddons").set(model, listOf(Addon(id, "IPTV", "1", "", true,
            type = AddonType.COMMUNITY, manifest = manifest)))
    }

    private fun play(hls: Boolean = false, block: suspend (ExoPlayer) -> Unit): Unit = runBlocking {
        val player = withContext(Dispatchers.Main) { ExoPlayer.Builder(context).build().apply {
            addListener(object : Player.Listener { override fun onRenderedFirstFrame() { firstFrame.set(true) } })
        } }
        compose.setContent { AndroidView(factory = { PlayerView(it).apply { this.player = player; useController = false } }) }
        try {
            withContext(Dispatchers.Main) {
                val factory = DefaultHttpDataSource.Factory()
                val item = MediaItem.fromUri(server.url(if (hls) "/episode.m3u8" else "/episode.ts").toString())
                player.setMediaSource(if (hls) HlsMediaSource.Factory(factory).createMediaSource(item)
                    else ProgressiveMediaSource.Factory(factory).createMediaSource(item))
                player.prepare()
                player.play()
                withTimeout(20_000) {
                    while (!firstFrame.get() || player.currentPosition < 250) {
                        player.playerError?.let { throw AssertionError("Fixture playback failed", it) }
                        delay(25)
                    }
                }
                block(player)
            }
        } finally { withContext(Dispatchers.Main) { player.release() } }
    }

    private suspend fun report(player: ExoPlayer, closing: Boolean = false) {
        val matched = isMatchedIptvVodPlayback(MediaType.TV, 1416, 22, 15, model.uiState.value.selectedStream?.addonId)
        val duration = playbackTrackingDurationMs(player.duration, player.isCurrentMediaItemLive, matched)
        val status = classifyPlaybackVideo(duration, model.uiState.value.expectedDurationMs, model.uiState.value.selectedStream?.url, false,
            player.isCurrentMediaItemLive && !matched, allowUnknownDurationVod = matched && firstFrame.get())
        assertEquals(PlaybackVideoStatus.REAL, status)
        assertTrue(canPersistPlaybackSource("fixture", "fixture", "fixture", status))
        assertTrue(canReportPlaybackProgress(player.currentPosition, duration, matched))
        model.saveProgress(player.currentPosition, duration, 0, player.isPlaying, player.playbackState, closing)?.join()
    }

    @Test fun xtreamUnknownDurationReachesRealTraktHttpEndpoints() = unknownDurationLifecycle(IptvVodSourceIds.XTREAM)
    @Test fun stalkerUnknownDurationReachesRealTraktHttpEndpoints() = unknownDurationLifecycle(IptvVodSourceIds.STALKER)

    private fun unknownDurationLifecycle(id: String) {
        select(id)
        play { player ->
            assertTrue("Chunked TS must reproduce the missing-duration case", player.duration <= 0L)
            report(player)
            player.pause()
            report(player)
            player.play()
            report(player)
            field("lastScrobbleTime").setLong(model, 0)
            report(player)
            player.setPlaybackSpeed(4f)
            withTimeout(15_000) { while (player.playbackState != Player.STATE_ENDED) delay(25) }
            report(player)
            assertEquals(listOf("/scrobble/start", "/scrobble/pause", "/scrobble/start", "/scrobble/pause",
                "/scrobble/pause", "/scrobble/stop"), requests.map { it.first })
            for ((_, raw) in requests) {
                val body = JsonParser.parseString(raw).asJsonObject
                assertEquals(1416, body["show"].asJsonObject["ids"].asJsonObject["tmdb"].asInt)
                assertEquals(22, body["episode"].asJsonObject["season"].asInt)
                assertEquals(15, body["episode"].asJsonObject["number"].asInt)
            }
            assertEquals(100f, JsonParser.parseString(requests.last().second).asJsonObject["progress"].asFloat, 0.01f)
            coVerify { localTrakt.removeFromContinueWatchingCache(1416, 22, 15, MediaType.TV) }
        }
    }

    @Test fun liveHlsTimelineCannotSuppressMatchedVodOrDeleteItsProgress() {
        select(IptvVodSourceIds.XTREAM)
        play(hls = true) { player ->
            assertTrue(player.isCurrentMediaItemLive)
            model.onLivePlaybackDetected()
            assertFalse(field("currentIsLiveStreamPlayback").getBoolean(model))
            report(player)
            player.pause()
            report(player, closing = true)
            assertEquals(listOf("/scrobble/start", "/scrobble/stop"), requests.map { it.first })
            assertTrue(JsonParser.parseString(requests.last().second).asJsonObject["progress"].asFloat < 80f)
            coVerify(exactly = 0) { history.removeFromHistory(any(), any(), any()) }
        }
    }

    @Test fun genuineLiveChannelsStillSendNoTrackingRequests() = runBlocking {
        select("iptv_live_channels")
        withContext(Dispatchers.Main) {
            model.onLivePlaybackDetected()
            model.saveProgress(60_000, 120_000, 50, true, Player.STATE_READY)?.join()
            model.saveProgress(90_000, 120_000, 75, false, Player.STATE_READY, closing = true)?.join()
        }
        assertTrue(requests.isEmpty())
    }
}
