package com.arflix.tv.ui.screens.player.preview

import com.google.common.util.concurrent.ListenableFuture
import java.io.Closeable
import java.util.concurrent.Executor
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine

internal interface SeekPreviewDecoder<T> : Closeable {
    fun frameAt(positionMs: Long): ListenableFuture<T>
}

/** Player-thread confined. Cancelling a UI waiter must not cancel Media3's shared queue. */
internal class SeekPreviewDecoderSession<T>(
    private val scope: CoroutineScope,
    private val completionExecutor: Executor,
    private val idleReleaseMs: Long = 1_500L,
) : Closeable {
    private class Pending<T>(val future: ListenableFuture<T>, val onFrame: (T) -> Unit)
    private var decoder: SeekPreviewDecoder<T>? = null
    private var identity: String? = null
    private var pending: Pending<T>? = null
    private var idleRelease: Job? = null
    private var closed = false

    suspend fun frameAt(
        sourceIdentity: String,
        positionMs: Long,
        create: () -> SeekPreviewDecoder<T>,
        onFrame: (T) -> Unit,
    ): T {
        check(!closed)
        idleRelease?.cancel()
        pending?.let { previous ->
            try {
                previous.future.awaitWithoutCancelling()
            } catch (_: Exception) {
                currentCoroutineContext().ensureActive()
                // A failed old target must not fail its replacement as well.
            } finally {
                if (previous.future.isDone) finish(previous)
            }
        }
        currentCoroutineContext().ensureActive()
        check(!closed)
        idleRelease?.cancel()
        if (identity != sourceIdentity || decoder == null) {
            release()
            decoder = create()
            identity = sourceIdentity
        }
        val request = Pending(checkNotNull(decoder).frameAt(positionMs), onFrame)
        pending = request
        request.future.addListener({ finish(request) }, completionExecutor)
        try {
            return request.future.awaitWithoutCancelling()
        } finally {
            if (request.future.isDone) finish(request)
        }
    }

    private fun finish(request: Pending<T>) {
        if (pending !== request || !request.future.isDone) return
        pending = null
        if (!closed) {
            try {
                request.onFrame(request.future.get())
            } catch (_: Exception) {
                // The active waiter receives the original decode failure.
            }
            idleRelease?.cancel()
            idleRelease = scope.launch {
                delay(idleReleaseMs)
                if (pending == null) release()
            }
        }
    }

    fun release() {
        idleRelease?.cancel()
        idleRelease = null
        val old = decoder
        decoder = null
        identity = null
        old?.close()
    }

    override fun close() {
        closed = true
        release()
    }
}

private suspend fun <T> ListenableFuture<T>.awaitWithoutCancelling(): T =
    suspendCancellableCoroutine { continuation ->
        addListener({
            if (continuation.isActive) {
                try { continuation.resume(get()) }
                catch (failure: Exception) { continuation.resumeWithException(failure) }
            }
        }, Executor { it.run() })
    }
