package com.arflix.tv.ui.screens.player

import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.PlayerView
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.repository.HomeServerConnection
import com.arflix.tv.data.repository.HomeServerCollection
import com.arflix.tv.data.repository.HomeServerKind
import com.arflix.tv.data.repository.HomeServerRepository
import com.arflix.tv.data.repository.ProfileManager
import com.arflix.tv.ui.theme.ArflixTvTheme
import com.arflix.tv.util.DeviceType
import com.arflix.tv.util.LocalDeviceType
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import java.net.InetAddress
import java.net.ServerSocket
import java.net.URI
import java.net.URLDecoder
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicInteger
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/** Silo-compatible PlaybackInfo -> production source resolution -> real TV player/decoder. */
@androidx.annotation.OptIn(UnstableApi::class)
@RunWith(AndroidJUnit4::class)
class HomeServerSiloPlaybackDeviceTest {
    @get:Rule val compose = createAndroidComposeRule<ComponentActivity>()

    @Test fun authenticatedSiloDirectRoutePlaysWithoutRequestingRestrictedTranscoding() = runBlocking {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val context = instrumentation.targetContext
        val video = instrumentation.context.assets.open("seek_preview_device_test.mp4").use { it.readBytes() }
        PlaybackServer(video).use { server ->
            val profiles = mockk<ProfileManager> {
                every { activeProfileId } returns MutableStateFlow("silo-device-test")
                coEvery { getProfileId() } returns "silo-device-test"
                every { getProfileIdSync() } returns "silo-device-test"
                every { profileStringKeyFor(any(), any()) } answers {
                    stringPreferencesKey("${firstArg<String>()}_${secondArg<String>()}")
                }
            }
            val repository = spyk(HomeServerRepository(context, OkHttpClient(), profiles))
            coEvery { repository.currentConnections() } returns listOf(HomeServerConnection(
                connectionId = "silo-fixture", serverKind = HomeServerKind.JELLYFIN,
                serverUrl = server.url, accessToken = "fixture-token", userId = "member",
                collections = listOf(HomeServerCollection("movies", "Movies", "movies"))
            ))
            val source = repository.resolveMovieSources("tt42", "Silo playback fixture", 2024, 42).single()
            val state = MutableStateFlow(PlayerUiState(isLoading = false, title = "Silo playback fixture",
                selectedStream = source, selectedStreamUrl = source.url, streams = listOf(source),
                subtitlePreloadEnabled = false, autoPlayNext = false))
            val model = mockk<PlayerViewModel>(relaxed = true)
            every { model.uiState } returns state
            every { model.isTranslatingLive } returns MutableStateFlow(false)
            val visible = mutableStateOf(true)
            compose.setContent {
                CompositionLocalProvider(LocalDeviceType provides DeviceType.TV) {
                    ArflixTvTheme {
                        if (visible.value) PlayerScreen(MediaType.MOVIE, 42, streamUrl = source.url,
                            viewModel = model, onBack = {})
                    }
                }
            }
            var error: Int? = null
            compose.waitUntil(25_000) {
                var playing = false
                compose.runOnUiThread {
                    findPlayer(compose.activity.window.decorView)?.let { player ->
                        error = player.playerError?.errorCode
                        playing = player.isPlaying && player.currentPosition > 1_500 && player.videoSize.width > 0
                    }
                }
                playing
            }
            assertNull("Unexpected playback error", error)
            assertTrue("The negotiated route did not deliver video", server.streamRequests.get() > 0)
            assertEquals("ARVIO tried a blocked or unauthenticated route", 0, server.blockedRequests.get())
            compose.runOnIdle { visible.value = false }
        }
    }

    private fun findPlayer(view: View): Player? {
        if (view is PlayerView) return view.player
        if (view is ViewGroup) for (index in 0 until view.childCount) {
            findPlayer(view.getChildAt(index))?.let { return it }
        }
        return null
    }

    private class PlaybackServer(private val video: ByteArray) : AutoCloseable {
        private val socket = ServerSocket(0, 10, InetAddress.getByName("127.0.0.1"))
        private val executor = Executors.newSingleThreadExecutor()
        val url = "http://127.0.0.1:${socket.localPort}/compat"
        val streamRequests = AtomicInteger()
        val blockedRequests = AtomicInteger()
        init {
            executor.submit {
                while (!socket.isClosed) {
                    val client = try { socket.accept() } catch (_: java.io.IOException) { break }
                    client.use {
                        val reader = client.getInputStream().bufferedReader()
                        val target = URI(reader.readLine().split(' ')[1])
                        val headers = generateSequence { reader.readLine()?.takeIf(String::isNotEmpty) }
                            .associate { line -> line.substringBefore(':').lowercase() to line.substringAfter(':').trim() }
                        val bodySize = headers["content-length"]?.toIntOrNull() ?: 0
                        repeat(bodySize) { reader.read() }
                        val query = target.rawQuery.orEmpty().split('&').filter { it.contains('=') }.associate {
                            URLDecoder.decode(it.substringBefore('='), "UTF-8").lowercase() to
                                URLDecoder.decode(it.substringAfter('='), "UTF-8")
                        }
                        val authenticated = headers["x-emby-token"] == "fixture-token"
                        var code = 200
                        var type = "application/json"
                        val body = when {
                            !authenticated -> { code = 401; blockedRequests.incrementAndGet(); "{}".toByteArray() }
                            target.path == "/compat/Users/member/Items" ->
                                """{"Items":[{"Id":"movie","Name":"Silo playback fixture","Type":"Movie","ProductionYear":2024,"ProviderIds":{"Imdb":"tt42","Tmdb":"42"}}]}""".toByteArray()
                            target.path == "/compat/Items/movie/PlaybackInfo" ->
                                """{"PlaySessionId":"session","MediaSources":[{"Id":"version","Container":"mp4","Path":"/storage/movie.mp4","SupportsDirectPlay":true,"SupportsDirectStream":true,"SupportsTranscoding":true,"DirectStreamUrl":"/compat/Videos/movie/stream?static=true&mediaSourceId=version&PlaySessionId=session","TranscodingUrl":"/compat/Videos/movie/master.m3u8?PlaySessionId=session&MediaSourceId=version","RequiredHttpHeaders":{"X-Playback-Key":"route-key"},"MediaStreams":[{"Type":"Video","Codec":"h264","Width":160,"Height":90},{"Type":"Audio","Codec":"aac"}]}]}""".toByteArray()
                            target.path == "/compat/Videos/movie/stream" && query["static"] == "true" &&
                                query["mediasourceid"] == "version" && query["playsessionid"] == "session" &&
                                headers["x-playback-key"] == "route-key" -> {
                                streamRequests.incrementAndGet(); type = "video/mp4"; video
                            }
                            else -> { code = 403; blockedRequests.incrementAndGet(); "{}".toByteArray() }
                        }
                        client.getOutputStream().apply {
                            write("HTTP/1.1 $code Fixture\r\nContent-Type: $type\r\nContent-Length: ${body.size}\r\nConnection: close\r\n\r\n".toByteArray())
                            write(body); flush()
                        }
                    }
                }
            }
        }
        override fun close() {
            socket.close()
            executor.shutdownNow()
        }
    }
}
