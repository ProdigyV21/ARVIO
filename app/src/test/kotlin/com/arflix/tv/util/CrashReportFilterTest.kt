package com.arflix.tv.util

import com.google.common.truth.Truth.assertThat
import io.sentry.SentryLevel
import java.net.UnknownHostException
import java.util.concurrent.CancellationException
import javax.net.ssl.SSLHandshakeException
import org.junit.Test

class CrashReportFilterTest {
    @Test
    fun `drops coroutine cancellations`() {
        val throwable = CancellationException("Job was cancelled")

        assertThat(CrashReportFilter.shouldReportHandledException(throwable)).isFalse()
        assertThat(CrashReportFilter.shouldSendSentryEvent(throwable, SentryLevel.ERROR)).isFalse()
    }

    @Test
    fun `drops expected playback diagnostics`() {
        val throwable = IllegalStateException("Playback error displayed")

        assertThat(CrashReportFilter.shouldReportHandledException(throwable)).isFalse()
    }

    @Test
    fun `drops expected auth and backend diagnostics`() {
        val throwable = RuntimeException(
            "invalid JWT: unable to parse or verify signature, token has invalid claims: token is expired"
        )

        assertThat(CrashReportFilter.shouldReportHandledException(throwable)).isFalse()
        assertThat(CrashReportFilter.shouldSendSentryEvent(throwable, SentryLevel.ERROR)).isFalse()
    }

    @Test
    fun `drops expected trakt and provider diagnostics`() {
        val throwable = IllegalStateException("Trakt token request failed: HTTP 429 Too Many Requests")

        assertThat(CrashReportFilter.shouldReportHandledException(throwable)).isFalse()
    }

    @Test
    fun `drops expected local rate limit backoff diagnostics`() {
        val throwable = IllegalStateException("Exponential backoff active: wait 300000ms")

        assertThat(CrashReportFilter.shouldReportHandledException(throwable)).isFalse()
    }

    @Test
    fun `drops provider network failures from handled reports`() {
        val throwable = IllegalStateException(
            "Source lookup failed",
            UnknownHostException("Unable to resolve host \"provider.example\"")
        )

        assertThat(CrashReportFilter.shouldReportHandledException(throwable)).isFalse()
    }

    @Test
    fun `drops supabase chain validation failures from handled reports`() {
        val throwable = RuntimeException(
            "HTTP request failed",
            SSLHandshakeException("Chain validation failed")
        )

        assertThat(CrashReportFilter.shouldReportHandledException(throwable)).isFalse()
    }

    @Test
    fun `keeps real fatal crashes even when message resembles provider failure`() {
        val throwable = UnknownHostException("Unable to resolve host \"provider.example\"")

        assertThat(CrashReportFilter.shouldSendSentryEvent(throwable, SentryLevel.FATAL)).isTrue()
    }

    @Test
    fun `keeps fatal crashes with ignored message fragments`() {
        val throwable = IllegalStateException("Trakt token request failed")

        assertThat(CrashReportFilter.shouldSendSentryEvent(throwable, SentryLevel.FATAL)).isTrue()
    }

    @Test
    fun `keeps unexpected app bugs`() {
        val throwable = NullPointerException("items was null")

        assertThat(CrashReportFilter.shouldReportHandledException(throwable)).isTrue()
        assertThat(CrashReportFilter.shouldSendSentryEvent(throwable, SentryLevel.ERROR)).isTrue()
    }
}
