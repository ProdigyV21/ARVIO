package com.arflix.tv.network

import okhttp3.Call
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.OkHttpClient
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull
import okio.Buffer
import okio.ForwardingSource
import okio.buffer
import java.io.IOException
import java.util.concurrent.ConcurrentHashMap

internal const val MAX_IPTV_CLOSE_JOIN_MS = 1_000L
internal const val DEFAULT_IPTV_COLD_START_MS = 30_000L

/** Track streaming bodies, which may outlive their OkHttp dispatcher entries. */
internal class IptvPlaybackConnections : Interceptor {
    private val calls = ConcurrentHashMap.newKeySet<Call>()

    fun cancelAll() {
        val snapshot = ArrayList(calls)
        snapshot.forEach { call ->
            try {
                call.cancel()
            } catch (e: Exception) {
                if (e is kotlinx.coroutines.CancellationException) throw e
            }
        }
    }

    fun cancelAllAsync(client: OkHttpClient, dispatcher: CoroutineDispatcher = Dispatchers.IO): Job {
        // Snapshot before dispatch: a quick resume must not cancel the new channel's calls.
        val snapshot = ArrayList(calls)
        val closing = (snapshot + client.dispatcher.queuedCalls() + client.dispatcher.runningCalls()).distinct()
        return CoroutineScope(dispatcher).launch {
            // TLS socket cancellation may write close_notify, so it must not run on main.
            closing.forEach { call ->
                try {
                    call.cancel()
                } catch (e: Exception) {
                    if (e is kotlinx.coroutines.CancellationException) throw e
                }
            }
            try {
                client.connectionPool.evictAll()
            } catch (e: Exception) {
                if (e is kotlinx.coroutines.CancellationException) throw e
            }
        }
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val call = chain.call()
        val session = chain.request().tag(IptvStreamSession::class.java)
        if (session != null && !session.register(call)) throw IptvStreamSessionClosedException()
        calls.add(call)
        try {
            val response = proceedReusingRedirect(chain, session)
            val body = response.body ?: run {
                calls.remove(call)
                session?.unregister(call)
                return response
            }
            val source = object : ForwardingSource(body.source()) {
                override fun read(sink: Buffer, byteCount: Long): Long = try {
                    super.read(sink, byteCount)
                } catch (error: IOException) {
                    throw session.closedOr(error)
                }

                override fun close() {
                    try {
                        super.close()
                    } finally {
                        calls.remove(call)
                        session?.unregister(call)
                    }
                }
            }.buffer()
            return response.newBuilder().body(object : ResponseBody() {
                override fun contentType() = body.contentType()
                override fun contentLength() = body.contentLength()
                override fun source() = source
            }).build()
        } catch (error: Throwable) {
            calls.remove(call)
            session?.unregister(call)
            throw if (error is IOException) session.closedOr(error) else error
        }
    }

    /** A panel counts a connection for every redirect it issues, so a VOD session's later requests skip it. */
    private fun proceedReusingRedirect(chain: Interceptor.Chain, session: IptvStreamSession?): Response {
        val request = chain.request()
        if (session == null || !session.reusesRedirects || request.method != "GET") return chain.proceed(request)
        val target = session.redirectFor(request.url)
        if (target != null) {
            val reused = try {
                chain.proceed(request.redirectedTo(target))
            } catch (error: IOException) {
                if (session.isClosed || chain.call().isCanceled()) throw error
                null
            }
            if (reused != null && !isStaleRedirectTarget(reused.code)) return reused
            reused?.close()
            // The target's token may have run out: the panel gets one chance to issue a fresh one, never more.
            session.forgetRedirect(request.url, target)
        }
        return chain.proceed(request).also { response ->
            val landed = response.request.url
            if (response.isSuccessful && response.priorResponse != null && landed != request.url) {
                session.rememberRedirect(request.url, landed)
            }
        }
    }

    companion object { val sessions = IptvStreamSessionTable() }
}

internal const val MAX_IPTV_REMEMBERED_REDIRECTS = 4

private fun isStaleRedirectTarget(code: Int): Boolean = code == 401 || code == 403 || code == 404 || code == 410 || code >= 500

/** Mirrors OkHttp's own redirect: credentials meant for the panel never travel to another origin. */
private fun Request.redirectedTo(target: HttpUrl): Request {
    val builder = newBuilder().url(target)
    if (url.scheme != target.scheme || url.host != target.host || url.port != target.port) builder.removeHeader("Authorization")
    return builder.build()
}

/** Limits count sessions, not requests: only close() gives an allowance back, so a body Media3 never closes cannot leak it. */
internal class IptvStreamSession internal constructor(
    val account: IptvProviderAccount,
    private val table: IptvStreamSessionTable,
    /** VOD only: a live redirect may carry a one-shot token. */
    val reusesRedirects: Boolean = false,
) {
    private val lock = Any()
    private val calls = HashSet<Call>()
    private val redirects = LinkedHashMap<HttpUrl, HttpUrl>()
    private var closing: Job? = null

    val isClosed: Boolean get() = synchronized(lock) { closing != null }
    val openCallCount: Int get() = synchronized(lock) { calls.size }

    /** Idempotent: every caller gets the one job that cancels this session's calls. */
    fun close(): Job = synchronized(lock) {
        closing ?: table.release(this, ArrayList(calls)).also {
            closing = it
            calls.clear()
            redirects.clear()
        }
    }

    /** Ends the requests in flight for an in-place re-prepare; the session keeps its allowance. */
    fun cancelCalls(): Job = table.cancelAsync(synchronized(lock) { ArrayList(calls) })

    fun callFactory(client: OkHttpClient): Call.Factory = object : Call.Factory {
        override fun newCall(request: Request): Call = client.newCall(
            request.newBuilder().tag(IptvStreamSession::class.java, this@IptvStreamSession).build()
        )
    }

    internal fun register(call: Call): Boolean = synchronized(lock) {
        if (closing != null) return false
        calls.add(call)
        true
    }

    internal fun unregister(call: Call) {
        synchronized(lock) { calls.remove(call) }
    }

    internal fun redirectFor(url: HttpUrl): HttpUrl? = synchronized(lock) { redirects[url] }

    internal fun rememberRedirect(from: HttpUrl, to: HttpUrl) {
        synchronized(lock) {
            if (!reusesRedirects || closing != null) return
            redirects.remove(from)
            redirects[from] = to
            while (redirects.size > MAX_IPTV_REMEMBERED_REDIRECTS) redirects.remove(redirects.keys.first())
        }
    }

    /** Leaves a newer target alone: a parallel request may already have fetched a fresh redirect. */
    internal fun forgetRedirect(from: HttpUrl, target: HttpUrl) {
        synchronized(lock) { if (redirects[from] == target) redirects.remove(from) }
    }
}

/** The only count of streams per account on this device; waiting happens here before prepare, never in OkHttp. */
internal class IptvStreamSessionTable(
    private val clock: () -> Long = System::currentTimeMillis,
    private val closeDispatcher: CoroutineDispatcher = Dispatchers.IO,
) {
    // Lock order is session then table, so nothing may call into a session while holding this.
    private val lock = Any()
    private val open = HashMap<IptvProviderAccount, MutableList<IptvStreamSession>>()
    private val closing = HashMap<IptvProviderAccount, MutableSet<Job>>()
    private val lastClosedAt = HashMap<IptvProviderAccount, Long>()

    fun open(account: IptvProviderAccount, limit: Int, reusesRedirects: Boolean = false): IptvStreamSession {
        val session = IptvStreamSession(account, this, reusesRedirects)
        val overflow = synchronized(lock) {
            val sessions = open.getOrPut(account) { ArrayList() }
            sessions.add(session)
            sessions.take((sessions.size - limit.coerceAtLeast(1)).coerceAtLeast(0))
        }
        overflow.forEach { it.close() }
        return session
    }

    /** Optional previews may use a free slot but must never close somebody else's stream. */
    fun tryOpenSpare(account: IptvProviderAccount, limit: Int): IptvStreamSession? = synchronized(lock) {
        if (open[account].orEmpty().size >= limit || !closing[account].isNullOrEmpty()) return@synchronized null
        IptvStreamSession(account, this).also { open.getOrPut(account) { ArrayList() }.add(it) }
    }

    /** Closes [previous], then this device's oldest sessions, until a new session fits under [limit]. */
    suspend fun makeRoom(account: IptvProviderAccount, limit: Int, previous: IptvStreamSession? = null) {
        val previousClose = previous?.close()
        val oldest = synchronized(lock) {
            val sessions = open[account].orEmpty()
            sessions.take((sessions.size - limit.coerceAtLeast(1) + 1).coerceAtLeast(0))
        }
        oldest.forEach { it.close() }
        val closes = synchronized(lock) { closing[account].orEmpty().toList() } + listOfNotNull(previousClose)
        withTimeoutOrNull(MAX_IPTV_CLOSE_JOIN_MS) { closes.joinAll() }
        // A join of finished closes never suspends, so a cancelled zap must still not go on to open.
        currentCoroutineContext().ensureActive()
    }

    fun isColdStart(account: IptvProviderAccount): Boolean = synchronized(lock) {
        val closedAt = lastClosedAt[account]
        open[account].isNullOrEmpty() && (closedAt == null || clock() - closedAt >= DEFAULT_IPTV_COLD_START_MS)
    }

    fun openCount(account: IptvProviderAccount): Int = synchronized(lock) { open[account]?.size ?: 0 }

    fun openCount(): Int = synchronized(lock) { open.values.sumOf { it.size } }

    internal fun release(session: IptvStreamSession, calls: List<Call>): Job = synchronized(lock) {
        val account = session.account
        open[account]?.let { sessions ->
            sessions.remove(session)
            if (sessions.isEmpty()) open.remove(account)
        }
        lastClosedAt[account] = clock()
        val job = cancelAsync(calls)
        closing.getOrPut(account) { HashSet() }.add(job)
        job.invokeOnCompletion {
            synchronized(lock) {
                closing[account]?.let { jobs ->
                    jobs.remove(job)
                    if (jobs.isEmpty()) closing.remove(account)
                }
            }
        }
        job
    }

    internal fun cancelAsync(calls: List<Call>): Job =
        // TLS socket cancellation may write close_notify, so it must not run on main.
        CoroutineScope(closeDispatcher).launch {
            calls.forEach { call ->
                try {
                    call.cancel()
                } catch (e: Exception) {
                    if (e is kotlinx.coroutines.CancellationException) throw e
                }
            }
        }
}

internal class IptvStreamSessionClosedException(cause: Throwable? = null) : IOException("IPTV playback session closed", cause)

/** A deliberate close cancels what is in flight, and that must not read as the source failing. */
private fun IptvStreamSession?.closedOr(error: IOException): IOException =
    if (this?.isClosed == true && error !is IptvStreamSessionClosedException) IptvStreamSessionClosedException(error) else error

internal fun isIptvStreamSessionClosed(error: Throwable): Boolean =
    generateSequence(error) { it.cause }.take(16).any { it is IptvStreamSessionClosedException }
