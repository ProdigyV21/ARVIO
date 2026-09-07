package com.arflix.tv.ui.screens.player.preview

import java.io.Closeable
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

/** Best-effort persistence: at most one item writing and one newer item waiting. */
internal class SeekPreviewCacheWriter<T>(
    scope: CoroutineScope,
    write: suspend (T) -> Unit,
) : Closeable {
    private val pending = Channel<T>(Channel.CONFLATED)
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
