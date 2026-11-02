package com.arflix.tv.ui.screens.player

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull
import kotlin.coroutines.EmptyCoroutineContext

/** A final completion or player-close transaction may finish after navigation destroys its ViewModel. */
internal fun CoroutineScope.launchPlaybackProgressSave(
    completing: Boolean,
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    stopWhen: (suspend () -> Unit)? = null,
    closing: Boolean = false,
    save: suspend () -> Unit,
): Job {
    val terminal = completing || closing
    val completionLifetime = if (terminal) SupervisorJob() else null
    val job = launch(dispatcher + (completionLifetime ?: EmptyCoroutineContext)) {
        if (terminal) withTimeoutOrNull(30_000L) {
            if (stopWhen == null) save() else coroutineScope {
                val write = launch { save() }
                val guard = launch { stopWhen(); write.cancel() }
                try { write.join() } finally { guard.cancel() }
            }
        } else save()
    }
    job.invokeOnCompletion { completionLifetime?.complete() }
    return job
}
