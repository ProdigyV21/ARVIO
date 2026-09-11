package com.arflix.tv.ui.screens.tv.live

import androidx.media3.common.C
import androidx.media3.datasource.HttpDataSource
import androidx.media3.exoplayer.upstream.DefaultLoadErrorHandlingPolicy
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy
import com.arflix.tv.network.iptvProviderCooldownMs
import com.arflix.tv.network.isIptvProviderRequestPaused

internal class IptvLoadErrorHandlingPolicy : DefaultLoadErrorHandlingPolicy(2) {
    override fun getRetryDelayMsFor(errorInfo: LoadErrorHandlingPolicy.LoadErrorInfo): Long {
        if (errorInfo.exception.iptvHlsFormatDetected() != null) return C.TIME_UNSET
        if (isIptvProviderRequestPaused(errorInfo.exception)) return C.TIME_UNSET
        val status = generateSequence<Throwable>(errorInfo.exception) { it.cause }
            .take(16).filterIsInstance<HttpDataSource.InvalidResponseCodeException>()
            .firstOrNull()?.responseCode
        return if (iptvProviderCooldownMs(status ?: 0, null, 0L) > 0L) C.TIME_UNSET
        else super.getRetryDelayMsFor(errorInfo)
    }
}
