package com.arflix.tv.ui.screens.player.preview

import java.io.Closeable
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

/** One executing operation and one replaceable target, including during uninterruptible cleanup. */
internal class SeekPreviewExtractionScheduler<T>(private val scope: CoroutineScope) : Closeable {
    private class Request<T>(val operation: suspend () -> T) {
        val result = CompletableDeferred<T>()
        var job: Job? = null
    }

    private val lock = Any()
    private val wakeup = Channel<Unit>(Channel.CONFLATED)
    private var pending: Request<T>? = null
    private var active: Request<T>? = null
    private var closed = false
    private val worker = scope.launch {
        for (ignored in wakeup) {
            while (true) {
                val request = synchronized(lock) {
                    pending?.also { pending = null; active = it }
                } ?: break
                val job = scope.launch(start = CoroutineStart.LAZY) {
                    try {
                        request.result.complete(request.operation())
                    } catch (cancelled: CancellationException) {
                        request.result.cancel(cancelled)
                    } catch (failure: Exception) {
                        request.result.completeExceptionally(failure)
                    }
                }
                synchronized(lock) {
                    request.job = job
                    if (request.result.isCancelled || closed) job.cancel()
                }
                job.start()
                job.join()
                synchronized(lock) { if (active === request) active = null }
            }
        }
    }

    suspend fun submit(background: Boolean = false, operation: suspend () -> T): T? {
        val request = Request(operation)
        synchronized(lock) {
            if (closed || (background && (active != null || pending != null))) return null
            pending?.result?.cancel()
            active?.result?.cancel()
            active?.job?.cancel()
            pending = request
            wakeup.trySend(Unit)
        }
        try {
            return request.result.await()
        } finally {
            if (!request.result.isCompleted) request.result.cancel()
            synchronized(lock) {
                if (pending === request) pending = null
                if (active === request && request.result.isCancelled) request.job?.cancel()
            }
        }
    }

    fun cancel() = synchronized(lock) {
        pending?.result?.cancel()
        pending = null
        active?.result?.cancel()
        active?.job?.cancel()
    }

    override fun close() {
        synchronized(lock) {
            closed = true
            cancel()
            wakeup.close()
        }
        // Let the worker join active cleanup; cancelling it could admit an abandoned decoder.
        worker.invokeOnCompletion { wakeup.cancel() }
    }
}
