package com.arflix.tv.data.repository

import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.util.Base64
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.viewinterop.AndroidView
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.okhttp.OkHttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import androidx.media3.ui.PlayerView
import androidx.test.platform.app.InstrumentationRegistry
import com.arflix.tv.BuildConfig
import com.arflix.tv.data.model.IptvGuideHistory
import com.arflix.tv.data.model.IptvProgram
import com.google.gson.JsonParser
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import okio.Buffer
import org.junit.Assert.*
import org.junit.Assume.assumeTrue
import org.junit.Rule
import org.junit.Test
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.UUID
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicInteger

/** Real repository and HTTP playback against an isolated Xtream/M3U contract server. */
@androidx.annotation.OptIn(UnstableApi::class)
class PartnerIptvPlaybackDeviceTest {
    @get:Rule val compose = createComposeRule()
    private val displayPlayer = androidx.compose.runtime.mutableStateOf<ExoPlayer?>(null)
    private var contentMounted = false
    private val instrumentation get() = InstrumentationRegistry.getInstrumentation()
    private val publicLive = "https://dwamdstream102.akamaized.net/hls/live/2015525/dwstream102/master.m3u8"

    private fun guard() {
        assumeTrue(InstrumentationRegistry.getArguments().getString("partnerIptvPlayback") == "true")
        assertTrue(BuildConfig.PARTNER_MANAGEMENT_ENABLED)
        assertTrue("Emulator only", Build.FINGERPRINT.contains("generic") || Build.MODEL.contains("sdk"))
    }

    private inner class Fixture : AutoCloseable {
        val server = MockWebServer()
        val requests = CopyOnWriteArrayList<String>()
        val media = instrumentation.context.assets.open("seek_preview_device_test.mp4").use { it.readBytes() }
        val now = System.currentTimeMillis()
        init {
            server.dispatcher = object : Dispatcher() {
                override fun dispatch(request: RecordedRequest): MockResponse {
                    val url = requireNotNull(request.requestUrl)
                    val path = url.encodedPath
                    val action = url.queryParameter("action").orEmpty()
                    requests += if (action.isNotEmpty()) action else path
                    if (path == "/list.m3u" || path == "/public.m3u" || path == "/get.php") {
                        val stream = if (path == "/public.m3u") publicLive else server.url("/video.mp4")
                        return text("#EXTM3U url-tvg=\"${server.url("/xmltv.php")}\"\n#EXTINF:-1 tvg-id=\"qa-live\" group-title=\"QA\" catchup=\"default\" catchup-days=\"3\",QA Channel\n$stream\n", "audio/x-mpegurl")
                    }
                    if (path == "/player_api.php") {
                        if (url.queryParameter("username") != "qa" || url.queryParameter("password") != "fixture")
                            return MockResponse().setResponseCode(401)
                        val json = when (action) {
                            "get_live_categories" -> """[{"category_id":"1","category_name":"QA Live"}]"""
                            "get_live_streams" -> """[{"stream_id":101,"name":"QA Live","category_id":"1","epg_channel_id":"qa-live","tv_archive":1,"tv_archive_duration":3,"container_extension":"ts"}]"""
                            "get_vod_categories", "get_series_categories" -> """[{"category_id":"2","category_name":"QA VOD"}]"""
                            "get_vod_streams" -> """[{"stream_id":201,"name":"QA Protocol Movie","year":"2026","tmdb":"990001","category_id":"2","container_extension":"mp4"}]"""
                            "get_series" -> """[{"series_id":301,"name":"QA Protocol Series","year":"2026","tmdb":"990002","category_id":"2"}]"""
                            "get_series_info" -> """{"episodes":{"1":[{"id":"401","episode_num":1,"title":"QA Episode","season":1,"container_extension":"mp4","info":{"video":{"width":1920,"height":1080}}}]}}"""
                            "get_short_epg", "get_simple_data_table" -> {
                                fun b64(s: String) = Base64.encodeToString(s.toByteArray(), Base64.NO_WRAP)
                                """{"epg_listings":[{"id":"1","epg_id":"qa-live","channel_id":"qa-live","title":"${b64("QA Past")}","description":"${b64("Archive test")}","start_timestamp":${(now-3600000)/1000},"stop_timestamp":${(now-60000)/1000},"has_archive":1},{"id":"2","epg_id":"qa-live","channel_id":"qa-live","title":"${b64("QA Now")}","start_timestamp":${(now-60000)/1000},"stop_timestamp":${(now+3600000)/1000},"has_archive":1}]}"""
                            }
                            else -> """{"user_info":{"auth":1,"status":"Active","max_connections":"3"},"server_info":{"timestamp_now":${now/1000},"timezone":"UTC"}}"""
                        }
                        return text(json)
                    }
                    if (path == "/xmltv.php") {
                        val f = DateTimeFormatter.ofPattern("yyyyMMddHHmmss Z").withZone(ZoneOffset.UTC)
                        fun time(ms: Long) = f.format(Instant.ofEpochMilli(ms))
                        return text("""<?xml version="1.0"?><tv><channel id="qa-live"><display-name>QA Live</display-name></channel><programme start="${time(now-60000)}" stop="${time(now+3600000)}" channel="qa-live"><title>QA Now</title></programme></tv>""", "application/xml")
                    }
                    if (path.startsWith("/live/") || path.startsWith("/movie/") || path.startsWith("/series/") || path.startsWith("/timeshift/") || path == "/video.mp4") {
                        val range = request.getHeader("Range")?.removePrefix("bytes=")?.split('-')
                        val start = range?.firstOrNull()?.toIntOrNull() ?: 0
                        val end = (range?.getOrNull(1)?.toIntOrNull() ?: media.lastIndex).coerceAtMost(media.lastIndex)
                        if (start > end) return MockResponse().setResponseCode(416)
                        return MockResponse().setResponseCode(if (range == null) 200 else 206)
                            .setHeader("Content-Type", "video/mp4").setHeader("Accept-Ranges", "bytes")
                            .also { if (range != null) it.setHeader("Content-Range", "bytes $start-$end/${media.size}") }
                            .setBody(Buffer().write(media, start, end-start+1))
                    }
                    return MockResponse().setResponseCode(404)
                }
            }
            server.start()
        }
        private fun text(body: String, type: String = "application/json") = MockResponse().setHeader("Content-Type", type).setBody(body)
        fun delivery(mode: String = "xtream", epg: Boolean = true, catchup: Boolean = true, live: Boolean = true, vod: Boolean = true, series: Boolean = true, public: Boolean = false): PartnerIptvDelivery {
            val endpoint = if (mode == "xtream") server.url("/").toString().trimEnd('/') else server.url(if (public) "/public.m3u" else "/list.m3u").toString()
            val credentials = if (mode == "xtream") """{"username":"qa","password":"fixture"}""" else "null"
            return PartnerIptvDelivery.parse(JsonParser.parseString("""{
                "managed":true,"active":true,"organizationId":"protocol-qa","revision":1,"leaseUntil":"${Instant.now().plusSeconds(3600)}",
                "config":{"catalogs":[],"collectionUrls":[],"settings":{}},
                "permissions":{"editCatalogs":false,"addConnections":false,"editAppearance":true,"editPlayback":true,"editMetadata":false},
                "services":[{"id":"iptv","name":"QA Provider","kind":"iptv","url":"$endpoint","credentials":$credentials,
                "iptv":{"mode":"$mode","epg":$epg,"catchup":$catchup,"liveTv":$live,"movies":$vod,"series":$series}}]}""").asJsonObject, System.currentTimeMillis())!!
        }
        fun repository(state: MutableStateFlow<PartnerIptvDelivery?>): IptvRepository {
            val profile = "protocol-qa-${UUID.randomUUID()}"
            val profiles = mockk<ProfileManager>(relaxed = true)
            every { profiles.getProfileIdSync() } returns profile
            every { profiles.activeProfileId } returns MutableStateFlow(profile)
            every { profiles.profileStringKey(any()) } answers { stringPreferencesKey("profile_${profile}_${firstArg<String>()}") }
            every { profiles.profileStringKeyFor(any(), any()) } answers { stringPreferencesKey("profile_${firstArg<String>()}_${secondArg<String>()}") }
            val partner = mockk<PartnerDeliveryRepository>()
            every { partner.deliveries } returns state
            every { partner.activeDelivery() } answers { state.value }
            return IptvRepository(instrumentation.targetContext, OkHttpClient(), profiles, mockk(relaxed = true), dagger.Lazy { partner })
        }
        override fun close() { server.shutdown() }
    }

    private fun play(url: String, label: String, seek: Boolean = false) {
        val frames = AtomicInteger()
        val firstFrame = AtomicBoolean()
        lateinit var player: ExoPlayer
        val start = SystemClock.elapsedRealtime()
        compose.runOnIdle {
            player = ExoPlayer.Builder(instrumentation.targetContext).setMediaSourceFactory(
                DefaultMediaSourceFactory(instrumentation.targetContext).setDataSourceFactory(OkHttpDataSource.Factory(OkHttpClient()))).build().apply {
                volume = 0f
                setVideoFrameMetadataListener { _, _, _, _ -> frames.incrementAndGet() }
                addListener(object : Player.Listener { override fun onRenderedFirstFrame() { firstFrame.set(true) } })
            }
        }
        try {
            if (!contentMounted) {
                compose.setContent { AndroidView(factory = { PlayerView(it).apply { useController = false } },
                    update = { it.player = displayPlayer.value }, modifier = Modifier.fillMaxSize()) }
                contentMounted = true
            }
            compose.runOnIdle { displayPlayer.value = player }
            compose.runOnIdle { player.setMediaItem(MediaItem.fromUri(url)); player.prepare(); player.playWhenReady = true }
            compose.waitUntil(45_000) { firstFrame.get() || compose.runOnIdle { player.playerError != null } }
            compose.runOnIdle { assertNull("$label playback failed", player.playerError); assertTrue(firstFrame.get()) }
            val firstMs = SystemClock.elapsedRealtime() - start
            val initial = frames.get()
            val sustainedStart = SystemClock.elapsedRealtime()
            compose.waitUntil(20_000) {
                SystemClock.elapsedRealtime() - sustainedStart >= 10_000 && frames.get() >= initial + 15
            }
            if (seek) {
                for (target in listOf(20_000L, 5_000L)) {
                    compose.runOnIdle { assertTrue(player.isCurrentMediaItemSeekable); player.seekTo(target) }
                    val before = frames.get()
                    compose.waitUntil(15_000) { frames.get() > before && compose.runOnIdle { player.currentPosition in target..target+3000 } }
                }
            }
            val format = compose.runOnIdle {
                assertNull(player.playerError)
                "${player.videoSize.width}x${player.videoSize.height}"
            }
            instrumentation.sendStatus(0, Bundle().apply { putString("stream", "PARTNER_IPTV_PLAY label=$label firstFrameMs=$firstMs frames=${frames.get()} format=$format sustainedMs=${SystemClock.elapsedRealtime()-sustainedStart} seek=$seek\n") })
        } finally { compose.runOnIdle { displayPlayer.value = null; player.release() } }
    }

    @Test fun xtreamLiveGuideMovieSeriesAndCatchup() = runBlocking {
        guard()
        Fixture().use { fixture ->
            val delivery = MutableStateFlow<PartnerIptvDelivery?>(fixture.delivery())
            val repository = fixture.repository(delivery)
            assertTrue(repository.observePersonalConfig().first().playlists.isEmpty())
            val snapshot = repository.loadSnapshot(allowNetworkEpgFetch = true)
            assertEquals(1, snapshot.channels.size)
            val channel = snapshot.channels.single()
            assertEquals("QA Now", snapshot.nowNext[channel.id]?.now?.title)
            play(channel.streamUrl, "xtream-live")
            val movie = repository.findMovieVodSources("QA Protocol Movie", 2026, tmdbId = 990001).single()
            play(requireNotNull(movie.url), "xtream-movie", seek = true)
            val episode = repository.findEpisodeVodSources("QA Protocol Series", 1, 1, tmdbId = 990002).single()
            play(requireNotNull(episode.url), "xtream-series", seek = true)
            val history = repository.refreshEpgForChannels(setOf(channel.id), preferFullCatchupHistory = true)
            assertTrue("Past programme missing", history?.get(channel.id)?.recent?.any { it.title == "QA Past" } == true)
            val past = IptvProgram("QA Past", startUtcMillis = fixture.now-3600000, endUtcMillis = fixture.now-60000)
            play(repository.resolvePlayableCatchupUrl(channel, past), "xtream-catchup", seek = true)
            assertTrue(fixture.requests.containsAll(listOf("get_live_streams", "get_vod_streams", "get_series", "get_series_info")))
            delivery.value = null
            assertTrue("Revoked playlist remains installed", repository.observeConfig().first().playlists.isEmpty())
            assertTrue("Stale replay survives revocation", repository.getCatchupUrlCandidates(channel, past).isEmpty())
            assertTrue(repository.observePersonalConfig().first().playlists.isEmpty())
        }
    }

    @Test fun capabilityChangesRemoveGuidesAndReplayWithoutProviderRequests() = runBlocking {
        guard()
        Fixture().use { fixture ->
            val state = MutableStateFlow<PartnerIptvDelivery?>(fixture.delivery())
            val repository = fixture.repository(state)
            val original = repository.loadSnapshot(allowNetworkEpgFetch = true).channels.single()
            state.value = fixture.delivery(epg = false, catchup = false)
            fixture.requests.clear()
            val disabled = repository.loadSnapshot(allowNetworkEpgFetch = true)
            assertTrue(disabled.nowNext.values.none { it.now != null || it.recent.isNotEmpty() })
            assertEquals("none", disabled.channels.single().catchupType)
            repository.refreshEpgForChannels(setOf(original.id), preferFullCatchupHistory = true)
            assertFalse(fixture.requests.any { it.contains("epg") || it == "/xmltv.php" || it == "get_simple_data_table" })
            val programme = IptvProgram("Old", startUtcMillis = fixture.now-3600000, endUtcMillis = fixture.now-60000, catchupAvailable = true)
            assertTrue(repository.getCatchupUrlCandidates(original, programme).isEmpty())
            assertFalse(IptvGuideHistory.canReplay(disabled.channels.single(), programme, fixture.now))
            state.value = fixture.delivery(live = false, epg = false, catchup = false)
            fixture.requests.clear()
            assertTrue(repository.loadSnapshot(allowNetworkEpgFetch = true).channels.isEmpty())
            assertEquals(1, repository.findMovieVodSources("QA Protocol Movie", 2026, tmdbId = 990001).size)
            assertEquals(1, repository.findEpisodeVodSources("QA Protocol Series", 1, 1, tmdbId = 990002).size)
            assertFalse("VOD-only package requested live TV or guide", fixture.requests.any {
                it.startsWith("get_live") || it.contains("epg") || it == "/xmltv.php" || it == "get_simple_data_table"
            })
            state.value = fixture.delivery(live = false, epg = false, catchup = false, vod = false, series = false)
            fixture.requests.clear()
            assertTrue(repository.loadSnapshot(allowNetworkEpgFetch = true).channels.isEmpty())
            assertTrue(repository.findMovieVodSources("QA Protocol Movie", 2026).isEmpty())
            assertTrue(repository.findEpisodeVodSources("QA Protocol Series", 1, 1).isEmpty())
            assertTrue("Disabled content still requested from provider", fixture.requests.isEmpty())
        }
    }

    @Test fun publicM3uWithXmlGuide() = runBlocking {
        guard()
        Fixture().use { fixture ->
            val state = MutableStateFlow<PartnerIptvDelivery?>(fixture.delivery(mode = "m3u"))
            val repository = fixture.repository(state)
            val snapshot = repository.loadSnapshot(allowNetworkEpgFetch = true)
            val channel = snapshot.channels.single()
            assertEquals("QA Now", snapshot.nowNext[channel.id]?.now?.title)
            assertTrue(fixture.requests.contains("/xmltv.php"))
            play(channel.streamUrl, "m3u-with-xmltv")
            assertTrue(repository.observePersonalConfig().first().playlists.isEmpty())
        }
    }

    @Test fun publicM3uChannelPlaysRealBroadcaster() = runBlocking {
        guard()
        Fixture().use { fixture ->
            val state = MutableStateFlow<PartnerIptvDelivery?>(fixture.delivery(mode = "m3u", epg = false, catchup = false, public = true))
            val repository = fixture.repository(state)
            val snapshot = repository.loadSnapshot(allowNetworkEpgFetch = true)
            assertEquals(publicLive, snapshot.channels.single().streamUrl)
            assertFalse(fixture.requests.contains("/xmltv.php"))
            play(snapshot.channels.single().streamUrl, "public-dw-live")
        }
    }
}
