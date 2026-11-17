package com.arflix.tv.ui.screens.player.engine.exoplayer

import android.os.SystemClock
import okhttp3.Call
import okhttp3.Connection
import okhttp3.EventListener
import okhttp3.Handshake
import okhttp3.Protocol
import okhttp3.Response
import java.net.InetAddress
import java.net.InetSocketAddress
import java.net.Proxy

/** Only durations and counts are retained: never URLs, headers, IPs or host names. */
internal class PlaybackStartupTrace(private val clock: () -> Long = SystemClock::elapsedRealtime) {
    data class Sample(
        var calls: Int = 0,
        var pooled: Int = 0,
        var dnsMs: Long = 0,
        var connectMs: Long = 0,
        var tlsMs: Long = 0,
        var headersMs: Long = 0,
        var prepareToFrameMs: Long = 0,
    ) {
        fun summary(): String = "calls=$calls pooled=$pooled dnsMs=$dnsMs connectMs=$connectMs " +
            "tlsMs=$tlsMs headersMs=$headersMs prepareToFrameMs=$prepareToFrameMs"
    }

    private var generation = 0L
    private var sample = Sample()
    private var preparedAt: Long? = null
    private var finished = false

    @Synchronized fun begin() {
        generation++
        sample = Sample()
        preparedAt = null
        finished = false
    }

    @Synchronized fun preparing() { preparedAt = clock() }

    @Synchronized fun finish(): Sample {
        if (!finished) {
            sample.prepareToFrameMs = preparedAt?.let { (clock() - it).coerceAtLeast(0L) } ?: 0
            finished = true
        }
        return sample.copy()
    }

    val listenerFactory = EventListener.Factory {
        val epoch = synchronized(this) { generation }
        object : EventListener() {
            private var started = 0L
            private var dns = 0L
            private var connected = 0L
            private var tls = 0L
            private var connectionTls = 0L
            private var requestWritten: Long? = null
            private var freshConnection = false
            private fun update(block: Sample.() -> Unit) = synchronized(this@PlaybackStartupTrace) {
                if (epoch == generation && !finished) sample.block()
            }
            override fun callStart(call: Call) { started = clock(); update { calls++ } }
            override fun dnsStart(call: Call, domainName: String) { dns = clock() }
            override fun dnsEnd(call: Call, domainName: String, inetAddressList: List<InetAddress>) {
                update { dnsMs += (clock() - dns).coerceAtLeast(0) }
            }
            override fun connectStart(call: Call, inetSocketAddress: InetSocketAddress, proxy: Proxy) {
                connected = clock(); connectionTls = 0L; freshConnection = true
            }
            override fun connectEnd(call: Call, inetSocketAddress: InetSocketAddress, proxy: Proxy, protocol: Protocol?) {
                update { connectMs += (clock() - connected - connectionTls).coerceAtLeast(0) }
            }
            override fun secureConnectStart(call: Call) { tls = clock() }
            override fun secureConnectEnd(call: Call, handshake: Handshake?) {
                connectionTls = (clock() - tls).coerceAtLeast(0)
                update { tlsMs += connectionTls }
            }
            override fun connectionAcquired(call: Call, connection: Connection) {
                if (!freshConnection) update { pooled++ }
            }
            override fun responseHeadersEnd(call: Call, response: Response) {
                update { headersMs += (clock() - (requestWritten ?: started)).coerceAtLeast(0) }
            }
            override fun requestHeadersEnd(call: Call, request: okhttp3.Request) { requestWritten = clock() }
        }
    }
}
