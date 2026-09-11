package com.arflix.tv.ui.screens.tv.live

import android.net.Uri
import androidx.media3.common.C
import androidx.media3.datasource.DataSpec
import androidx.media3.datasource.HttpDataSource
import androidx.media3.exoplayer.source.LoadEventInfo
import androidx.media3.exoplayer.source.MediaLoadData
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy
import com.arflix.tv.network.IptvProviderRequestDeferredException
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.ConscryptMode
import java.io.IOException

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE, sdk = [28])
@ConscryptMode(ConscryptMode.Mode.OFF)
class IptvLoadErrorHandlingPolicyTest {
    private val spec = DataSpec(Uri.parse("https://provider.test/live/segment.ts"))
    private val policy = IptvLoadErrorHandlingPolicy()
    private fun delay(error: IOException): Long = policy.getRetryDelayMsFor(
        LoadErrorHandlingPolicy.LoadErrorInfo(
            LoadEventInfo(1L, spec, 0L), MediaLoadData(C.DATA_TYPE_MEDIA), error, 2
        )
    )

    @Test fun providerRejectionsDoNotTriggerMedia3InternalRetries() {
        for (status in listOf(401, 403, 429, 451, 500, 503, 513)) {
            val error = HttpDataSource.InvalidResponseCodeException(
                status, "Rejected", null, emptyMap(), spec, byteArrayOf()
            )
            assertEquals("HTTP $status", C.TIME_UNSET, delay(error))
        }
    }

    @Test fun wrappedProviderCooldownCannotBeRetriedByThePlayer() {
        assertEquals(C.TIME_UNSET, delay(IOException("Load failed", IptvProviderRequestDeferredException())))
    }

    @Test fun ordinaryConnectionFailuresKeepBoundedPlayerRecovery() {
        assertEquals(1_000L, delay(IOException("Connection interrupted")))
        assertEquals(2, policy.getMinimumLoadableRetryCount(C.DATA_TYPE_MEDIA))
    }
    @Test fun detectedHlsDoesNotRetryTheWrongExtractor() {
        assertEquals(C.TIME_UNSET, delay(IOException("Open failed", IptvHlsFormatDetected(spec.uri.toString()))))
    }
}
