package com.arflix.tv.network

import java.io.IOException
import java.net.InetAddress
import java.net.ServerSocket
import java.net.Socket
import java.net.SocketTimeoutException
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

/**
 * A raw socket Xtream panel: streams under /live, /movie, /cdn and /ch never end until the
 * client leaves or [dropStream] runs. Each OPEN first sweeps older streams for a client's
 * close, so a stream the client really closed can never read as overlapping the next. The sweep
 * also waits, so CLOSE before OPEN cannot prove a close finished before the open: tests assert
 * that on the session itself. Socket pacing and reset settling are real time: TCP gives no signal for either.
 */
internal class IptvStreamServerFixture : AutoCloseable {
    /** CLOSE is the client leaving; DROP is this server ending a stream, which says nothing about the client. */
    enum class EventKind { OPEN, CLOSE, DROP }
    enum class Drop { RESET, CLEAN_EOF }
    data class Event(val kind: EventKind, val account: String, val path: String)
    data class Answer(val status: Int, val body: String)
    data class Hit(val path: String, val range: String?)
    private class Stream(val socket: Socket, val account: String, val path: String) {
        @Volatile var drop: Drop? = null
    }
    private class Barrier(parties: Int) { val arrived = CountDownLatch(parties) }

    private val lock = Object()
    private val log = ArrayList<Event>()
    private val hitLog = ArrayList<Hit>()
    private val streams = ArrayList<Stream>()
    private val connections = ArrayList<Socket>()
    private val peakByAccount = HashMap<String, Int>()
    private val redirects = HashMap<String, String>()
    private val answers = HashMap<String, Answer>()
    private var peak = 0
    private var requests = 0
    private var checks = 0
    private var accountAnswer = Answer(200, accountBody(0, 1))
    @Volatile private var barrier: Barrier? = null

    private val server = ServerSocket(0, 64, InetAddress.getByName("127.0.0.1"))
    val port: Int get() = server.localPort
    val baseUrl: String get() = "http://127.0.0.1:$port"

    private val serving = thread(isDaemon = true, name = "iptv-stream-fixture") {
        while (!server.isClosed) {
            val socket = try { server.accept() } catch (_: IOException) { break }
            synchronized(lock) { connections.add(socket) }
            thread(isDaemon = true, name = "iptv-stream-connection") { serve(socket) }
        }
    }

    val events: List<Event> get() = synchronized(lock) { ArrayList(log) }
    val peakOpenSessions: Int get() = synchronized(lock) { peak }
    fun peakOpenSessions(account: String): Int = synchronized(lock) { peakByAccount[account] ?: 0 }
    /** Every request that is not an account check: a browsing path must keep this at zero. */
    val streamRequestCount: Int get() = synchronized(lock) { requests }
    val accountCheckCount: Int get() = synchronized(lock) { checks }
    /** Every request that is not an account check, in arrival order, with the Range it asked for. */
    val hits: List<Hit> get() = synchronized(lock) { ArrayList(hitLog) }

    fun setAccount(active: Int, max: Int) = synchronized(lock) { accountAnswer = Answer(200, accountBody(active, max)) }
    fun setAccountAnswer(status: Int, body: String) = synchronized(lock) { accountAnswer = Answer(status, body) }
    fun answer(path: String, status: Int, body: String = "{}") = synchronized(lock) { answers[path] = Answer(status, body) }
    fun redirect(path: String, location: String) = synchronized(lock) { redirects[path] = location }

    /** Requests under /hold/ answer 200 only once [parties] are in flight together, otherwise 504. */
    fun holdRequests(parties: Int) { barrier = Barrier(parties) }

    /** The stream's writer ends it at its next write, because closing under a blocked write can send nothing at all. */
    fun dropStream(drop: Drop = Drop.RESET) {
        synchronized(lock) { ArrayList(streams) }.forEach { stream ->
            stream.drop = drop
            markClosed(stream, EventKind.DROP)
        }
    }

    /** Returns how many streams are still open after looking for closes by clients. */
    fun openStreamCount(): Int {
        sweep()
        return synchronized(lock) { streams.size }
    }

    private fun serve(socket: Socket) {
        try {
            socket.soTimeout = 5_000
            val head = readHead(socket) ?: return
            val target = head.lineSequence().first().split(' ').getOrNull(1) ?: return
            val range = head.lineSequence().firstOrNull { it.startsWith("Range:", ignoreCase = true) }?.substringAfter(':')?.trim()
            val path = target.substringBefore('?')
            val hold = barrier
            val (answer, location) = synchronized(lock) {
                if (path != "/player_api.php") {
                    requests++
                    hitLog.add(Hit(path, range))
                } else {
                    checks++
                }
                (if (path == "/player_api.php") accountAnswer else answers[path]) to redirects[path]
            }
            when {
                location != null -> respond(socket, 302, "", "Location: $location\r\n")
                answer != null -> respond(socket, answer.status, answer.body)
                hold != null && path.startsWith("/hold/") -> {
                    hold.arrived.countDown()
                    respond(socket, if (hold.arrived.await(HOLD_TIMEOUT_MS, TimeUnit.MILLISECONDS)) 200 else 504, "ok")
                }
                path.substringAfter('/').substringBefore('/') in STREAM_ROOTS -> serveStream(socket, path)
                else -> respond(socket, 404, "")
            }
        } catch (_: IOException) {
        } finally {
            try { socket.close() } catch (_: IOException) { }
        }
    }

    private fun serveStream(socket: Socket, path: String) {
        sweep()
        val segments = path.trim('/').split('/')
        val account = if (segments[0] in setOf("live", "movie") && segments.size >= 4) segments[1] else ""
        val stream = Stream(socket, account, path)
        synchronized(lock) {
            log.add(Event(EventKind.OPEN, account, path))
            streams.add(stream)
            peak = maxOf(peak, streams.size)
            peakByAccount[account] = maxOf(peakByAccount[account] ?: 0, streams.count { it.account == account })
        }
        try {
            val output = socket.getOutputStream()
            output.write("HTTP/1.1 200 OK\r\nContent-Type: video/mp2t\r\nConnection: close\r\n\r\n".toByteArray())
            while (true) {
                val drop = stream.drop
                if (drop != null) {
                    if (drop == Drop.RESET) {
                        // Some TCP stacks ignore a reset that arrives ahead of the client's delayed ACK.
                        Thread.sleep(RESET_SETTLE_MS)
                        socket.setSoLinger(true, 0)
                    }
                    socket.close()
                    return
                }
                output.write(TS_FILL)
                // Paced under a reading client's speed, so a reset never meets a full receive window.
                Thread.sleep(1L)
            }
        } catch (_: IOException) {
        } catch (_: InterruptedException) {
        } finally {
            markClosed(stream)
        }
    }

    private fun sweep() {
        synchronized(lock) { ArrayList(streams) }.forEach { stream ->
            try {
                stream.socket.soTimeout = SWEEP_WAIT_MS
                if (stream.socket.getInputStream().read() == -1) markClosed(stream)
            } catch (_: SocketTimeoutException) {
            } catch (_: IOException) {
                markClosed(stream)
            }
        }
    }

    private fun markClosed(stream: Stream, kind: EventKind = EventKind.CLOSE) = synchronized(lock) {
        if (streams.remove(stream)) log.add(Event(kind, stream.account, stream.path))
    }

    private fun readHead(socket: Socket): String? {
        val head = StringBuilder()
        val input = socket.getInputStream()
        while (!head.endsWith("\r\n\r\n")) {
            val next = input.read()
            if (next == -1) return null
            head.append(next.toChar())
        }
        return head.toString()
    }

    private fun respond(socket: Socket, status: Int, body: String, extraHeaders: String = "") {
        socket.getOutputStream().write(
            "HTTP/1.1 $status Test\r\nContent-Length: ${body.length}\r\nConnection: close\r\n$extraHeaders\r\n$body".toByteArray()
        )
    }

    override fun close() {
        server.close()
        serving.join(5_000)
        synchronized(lock) { ArrayList(connections) }.forEach {
            try { it.close() } catch (_: IOException) { }
        }
    }

    private companion object {
        val STREAM_ROOTS = setOf("live", "movie", "cdn", "ch")
        const val SWEEP_WAIT_MS = 200
        const val RESET_SETTLE_MS = 250L
        const val HOLD_TIMEOUT_MS = 30_000L
        // Looks like MPEG-TS sync bytes, so a real extractor keeps reading rather than rejecting it.
        val TS_FILL = ByteArray(188 * 44).also { fill -> fill.indices.step(188).forEach { fill[it] = 0x47 } }

        fun accountBody(active: Int, max: Int) =
            """{"user_info":{"auth":1,"status":"Active","active_cons":"$active","max_connections":"$max"}}"""
    }
}
