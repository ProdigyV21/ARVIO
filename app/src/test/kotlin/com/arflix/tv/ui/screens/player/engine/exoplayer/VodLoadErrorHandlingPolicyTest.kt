package com.arflix.tv.ui.screens.player.engine.exoplayer

import android.net.Uri
import androidx.media3.common.C
import androidx.media3.datasource.DataSpec
import androidx.media3.datasource.HttpDataSource
import androidx.media3.exoplayer.source.LoadEventInfo
import androidx.media3.exoplayer.source.MediaLoadData
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy
import com.arflix.tv.data.model.StreamSource
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.ConscryptMode
import java.io.IOException
import java.net.SocketTimeoutException

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE, sdk = [28])
@ConscryptMode(ConscryptMode.Mode.OFF)
class VodLoadErrorHandlingPolicyTest {
    private val spec = DataSpec(Uri.parse("https://vod.test/a.mkv"))
    private fun info(code: Int, count: Int = 1, type: Int = C.DATA_TYPE_MEDIA,
        headers: Map<String, List<String>> = emptyMap(), task: Long = 1L) =
        LoadErrorHandlingPolicy.LoadErrorInfo(LoadEventInfo(task, spec, 0), MediaLoadData(type),
            HttpDataSource.InvalidResponseCodeException(code, "fixture", null, headers, spec, byteArrayOf()), count)

    @Test fun deadFilesAndExpiredAuthSurfaceWithoutRepeatingBadRequests() {
        for (code in listOf(400, 401, 403, 404, 410)) {
            assertEquals(C.TIME_UNSET, VodLoadErrorHandlingPolicy(true).getRetryDelayMsFor(info(code)))
        }
    }
    @Test fun aMissingAdaptiveSegmentStillUsesMedia3Recovery() {
        val policy = VodLoadErrorHandlingPolicy(false)
        assertNotEquals(C.TIME_UNSET, policy.getRetryDelayMsFor(info(404)))
        assertEquals(C.TIME_UNSET, policy.getRetryDelayMsFor(info(404, type = C.DATA_TYPE_MANIFEST)))
    }
    @Test fun rateLimitsRespectRetryAfterAndHaveATotalAndCountBound() {
        var time = 0L
        val policy = VodLoadErrorHandlingPolicy(true, { time }, { 0L })
        assertEquals(5_000L, policy.getRetryDelayMsFor(info(429, headers = mapOf("retry-after" to listOf("5")))))
        assertEquals(8, policy.getMinimumLoadableRetryCount(C.DATA_TYPE_MEDIA))
        time = 31_000L
        assertEquals(C.TIME_UNSET, policy.getRetryDelayMsFor(info(429)))
        policy.onLoadTaskConcluded(1L)
        assertEquals(1_000L, policy.getRetryDelayMsFor(info(503, task = 2L)))
        assertEquals(C.TIME_UNSET, policy.getRetryDelayMsFor(info(429, count = 8, task = 2L)))
    }
    @Test fun aLongServerCooldownIsNotSilentlyShortened() {
        assertEquals(C.TIME_UNSET, VodLoadErrorHandlingPolicy(true, { 0 }, { 0 }).getRetryDelayMsFor(
            info(429, headers = mapOf("Retry-After" to listOf("120")))))
    }
    @Test fun retryAfterSupportsDatesInvalidInputAndOverflow() {
        assertEquals(5_000L, vodRetryAfterMs("Thu, 01 Jan 1970 00:00:05 GMT", 0))
        assertEquals(0L, vodRetryAfterMs("-2", 0))
        assertNull(vodRetryAfterMs("broken", 0))
        assertTrue(vodRetryAfterMs(Long.MAX_VALUE.toString(), 0)!! > 0)
    }
    @Test fun socketTimeoutsCannotKeepALoaderAliveForever() {
        val policy = VodLoadErrorHandlingPolicy(true)
        fun error(count: Int) = LoadErrorHandlingPolicy.LoadErrorInfo(LoadEventInfo(1L, spec, 0),
            MediaLoadData(C.DATA_TYPE_MEDIA), IOException("wrapped", SocketTimeoutException()), count)
        assertEquals(500L, policy.getRetryDelayMsFor(error(1)))
        assertEquals(1_000L, policy.getRetryDelayMsFor(error(2)))
        assertEquals(C.TIME_UNSET, policy.getRetryDelayMsFor(error(3)))
    }
    @Test fun iptvLiveAndLocalProxiesKeepTheirOwnNetworkPolicy() {
        fun stream(addon: String = "addon", provider: String? = null) = StreamSource("s", "a", addon, "HD", "", iptvSourceId = provider)
        assertTrue(useVodPlaybackPolicy(stream(), false, "https://video.test/a.mkv"))
        assertFalse(useVodPlaybackPolicy(stream(), true, "https://video.test/a.mkv"))
        assertFalse(useVodPlaybackPolicy(stream("iptv_xtream_vod"), false, "https://video.test/a.mkv"))
        assertFalse(useVodPlaybackPolicy(stream("iptv_stalker_vod"), false, "https://video.test/a.mkv"))
        assertFalse(useVodPlaybackPolicy(stream(provider = "line"), false, "https://video.test/a.mkv"))
        assertFalse(useVodPlaybackPolicy(stream(), false, "http://127.0.0.1:9000/video"))
        assertFalse(useVodPlaybackPolicy(stream(), false, "http://[::1]:9000/video"))
    }
}
