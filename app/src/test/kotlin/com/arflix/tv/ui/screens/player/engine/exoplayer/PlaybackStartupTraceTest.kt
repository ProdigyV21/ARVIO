package com.arflix.tv.ui.screens.player.engine.exoplayer

import io.mockk.mockk
import okhttp3.Call
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import org.junit.Assert.*
import org.junit.Test
import java.net.InetAddress
import java.net.InetSocketAddress
import java.net.Proxy

class PlaybackStartupTraceTest {
    @Test fun stagesAreMeasuredWithoutRecordingRequestSecrets() {
        var time = 0L
        val trace = PlaybackStartupTrace { time }
        trace.begin()
        val call = mockk<Call>()
        val listener = trace.listenerFactory.create(call)
        listener.callStart(call)
        listener.dnsStart(call, "secret.example")
        time = 10
        listener.dnsEnd(call, "secret.example", listOf(InetAddress.getLoopbackAddress()))
        val request = Request.Builder().url("https://secret.example/a?token=password").build()
        time = 20
        listener.requestHeadersEnd(call, request)
        time = 45
        listener.responseHeadersEnd(call, Response.Builder().request(request).protocol(Protocol.HTTP_1_1).code(200).message("OK").build())
        trace.preparing()
        time = 100
        val sample = trace.finish()
        assertEquals(1, sample.calls)
        assertEquals(10L, sample.dnsMs)
        assertEquals(25L, sample.headersMs)
        assertEquals(55L, sample.prepareToFrameMs)
        assertFalse(sample.summary().contains("secret"))
        assertFalse(sample.summary().contains("password"))
        time = 500
        assertEquals(sample, trace.finish())
    }
    @Test fun lateCallbacksCannotPolluteTheNextSourceOrFinishedSample() {
        val trace = PlaybackStartupTrace { 0 }
        trace.begin()
        val call = mockk<Call>()
        val previous = trace.listenerFactory.create(call)
        trace.begin()
        previous.callStart(call)
        val current = trace.listenerFactory.create(call)
        current.callStart(call)
        current.connectionAcquired(call, mockk(relaxed = true))
        assertEquals(1, trace.finish().calls)
        assertEquals(1, trace.finish().pooled)
        current.callStart(call)
        assertEquals(1, trace.finish().calls)
    }
    @Test fun tlsIsNotCountedAgainAsTcpTime() {
        var time = 0L
        val trace = PlaybackStartupTrace { time }
        trace.begin()
        val call = mockk<Call>()
        val events = trace.listenerFactory.create(call)
        val address = InetSocketAddress(InetAddress.getLoopbackAddress(), 443)
        events.callStart(call)
        events.connectStart(call, address, Proxy.NO_PROXY)
        time = 5
        events.secureConnectStart(call)
        time = 25
        events.secureConnectEnd(call, null)
        time = 30
        events.connectEnd(call, address, Proxy.NO_PROXY, Protocol.HTTP_1_1)
        assertEquals(20L, trace.finish().tlsMs)
        assertEquals(10L, trace.finish().connectMs)
    }
}
