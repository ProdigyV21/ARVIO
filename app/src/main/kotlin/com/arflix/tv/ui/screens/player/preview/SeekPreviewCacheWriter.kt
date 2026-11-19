package com.arflix.tv.ui.screens.player.preview

import java.io.Closeable
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

/** Non-blocking persistence: one writing plus at most a five-frame strip waiting. */
internal class SeekPreviewCacheWriter<T>(
    scope: CoroutineScope,
    write: suspend (T) -> Unit,
) : Closeable {
    // Keep a complete newly decoded strip even when compression is slower than
    // extraction. Beyond that, prefer recent frames without growing memory.
    private val pending = Channel<T>(5, BufferOverflow.DROP_OLDEST)
    private val worker = scope.launch {
        for (entry in pending) {
            try {
                write(entry)
            } catch (cancelled: CancellationException) {
                throw cancelled
            } catch (_: Exception) {
                // Cache storage is optional; a failed write must not discard the displayed frame.
            } catch (_: OutOfMemoryError) {
                // Compression may allocate native memory on constrained devices.
            }
        }
    }

    init {
        worker.invokeOnCompletion { pending.cancel() }
    }

    fun offer(entry: T) {
        pending.trySend(entry)
    }

    override fun close() {
        pending.cancel()
        worker.cancel()
    }
}
