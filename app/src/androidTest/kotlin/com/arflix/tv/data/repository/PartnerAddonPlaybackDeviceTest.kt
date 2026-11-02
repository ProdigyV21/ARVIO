package com.arflix.tv.data.repository

import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.okhttp.OkHttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import androidx.media3.ui.PlayerView
import androidx.test.platform.app.InstrumentationRegistry
import com.arflix.tv.BuildConfig
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.di.PartnerTestAccess
import dagger.hilt.android.EntryPointAccessors
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.*
import org.junit.Assume.assumeTrue
import org.junit.Rule
import org.junit.Test
import java.time.Instant
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicInteger

/** Isolated delivery fixture; real add-on HTTP and decoder playback. No production admin mutation. */
@androidx.annotation.OptIn(UnstableApi::class)
class PartnerAddonPlaybackDeviceTest {
    @get:Rule val compose = createComposeRule()

    @Test fun publicAddonDeliveryResolvesAndPlaysWithoutCustomerCredentials() = runBlocking {
        assumeTrue(InstrumentationRegistry.getArguments().getString("partnerAddonPlayback") == "true")
        assertTrue(BuildConfig.PARTNER_MANAGEMENT_ENABLED)
        assertTrue("Emulator only", Build.FINGERPRINT.contains("generic") || Build.MODEL.contains("sdk"))
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val context = instrumentation.targetContext
        val access = EntryPointAccessors.fromApplication(context, PartnerTestAccess::class.java)
        access.auth().checkAuthState()
        assertEquals("Refusing non-QA accounts", "arvio.app@gmail.com",
            (access.auth().authState.value as? AuthState.Authenticated)?.email)
        val streams = access.streams()
        val personalBefore = streams.personalAddons.first()
        val auth = mockk<AuthRepository>()
        every { auth.authState } returns MutableStateFlow<AuthState>(AuthState.Authenticated("isolated-addon-qa", "qa@example.test", null))
        coEvery { auth.getAccessToken() } returns "fixture-only-never-transmitted"
        val client = OkHttpClient.Builder().addInterceptor { chain ->
            // Terminate delivery requests locally: no credentials or fixture state reach production.
            Response.Builder().request(chain.request()).protocol(Protocol.HTTP_1_1).code(200).message("OK")
                .body("""{"managed":true,"active":true,"organizationId":"qa-local","revision":1,
                  "leaseUntil":"${Instant.now().plusSeconds(3600)}",
                  "config":{"catalogs":[],"collectionUrls":[],"settings":{}},
                  "permissions":{"editCatalogs":false,"addConnections":false,"editAppearance":true,"editPlayback":true,"editMetadata":false},
                  "services":[{"kind":"addon","id":"archive","name":"QA Archive","credentials":null,
                    "url":"https://dev.nebulawp.org/stremio/archive.org-addon/manifest.json"}]}""".toResponseBody()).build()
        }.build()
        val partner = PartnerDeliveryRepository(context, auth, client, dagger.Lazy { streams })
        val worker = launch(Dispatchers.Default) { partner.runForegroundSession() }
        var player: ExoPlayer? = null
        try {
            val delivery = withTimeout(60_000) { partner.state.filterNotNull().first() }.delivery
            val addon = delivery.addons.single()
            assertEquals("partner-qa-local-archive", addon.id)
            withTimeout(20_000) { partner.acknowledgedRevision.first { it == 1 } }
            val sources = streams.resolveAddonStreams(addon, MediaType.MOVIE, "tt0063350",
                title = "Night of the Living Dead", timeoutMs = 30_000)
            assertTrue("Add-on returned no playable sources", sources.isNotEmpty())
            val selected = sources.first { it.url?.startsWith("https://") == true }
            val resolved = withTimeout(30_000) { streams.resolveStreamForPlayback(selected) }
                ?: error("Playback URL did not resolve")
            val frames = AtomicInteger()
            val firstFrame = AtomicBoolean()
            val start = SystemClock.elapsedRealtime()
            compose.runOnIdle {
                val sourceFactory = DefaultMediaSourceFactory(context).setDataSourceFactory(
                    OkHttpDataSource.Factory(OkHttpClient()).setDefaultRequestProperties(
                        resolved.behaviorHints?.proxyHeaders?.request.orEmpty()))
                player = ExoPlayer.Builder(context).setMediaSourceFactory(sourceFactory).build().apply {
                    volume = 0f
                    setVideoFrameMetadataListener { _, _, _, _ -> frames.incrementAndGet() }
                    addListener(object : Player.Listener {
                        override fun onRenderedFirstFrame() { firstFrame.set(true) }
                    })
                }
            }
            compose.setContent {
                AndroidView(factory = { PlayerView(it).apply { this.player = player; useController = false } },
                    modifier = Modifier.fillMaxSize())
            }
            compose.runOnIdle {
                player!!.setMediaItem(MediaItem.fromUri(requireNotNull(resolved.url)))
                player!!.prepare()
                player!!.playWhenReady = true
            }
            compose.waitUntil(60_000) { firstFrame.get() || compose.runOnIdle { player!!.playerError != null } }
            compose.runOnIdle { assertNull(player!!.playerError); assertTrue(firstFrame.get()) }
            val firstFrameMs = SystemClock.elapsedRealtime() - start
            val initialFrames = frames.get()
            delay(10_000)
            compose.runOnIdle {
                assertNull(player!!.playerError)
                assertTrue("Video stopped producing frames", frames.get() - initialFrames > 100)
                instrumentation.sendStatus(0, Bundle().apply {
                    putString("stream", "PARTNER_ADDON fixtureDelivery=true realAddon=true firstFrameMs=$firstFrameMs framesIn10s=${frames.get() - initialFrames} video=${player!!.videoSize.width}x${player!!.videoSize.height}\n")
                })
            }
            assertEquals("Managed add-on leaked into personal storage", personalBefore, streams.personalAddons.first())
        } finally {
            compose.runOnIdle { player?.release() }
            worker.cancelAndJoin()
        }
    }
}
