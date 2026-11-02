package com.arflix.tv.ui.screens.player.preview

import android.app.ActivityManager
import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.graphics.Bitmap
import android.os.*
import android.os.Process
import androidx.annotation.Keep
import java.io.Closeable
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicLong
import kotlinx.coroutines.*

@Keep
internal class KeyframeResult(val bitmap: Bitmap, val positionMs: Long)

@Keep
internal object KeyframeNative {
    init { System.loadLibrary("arvio_seek") }
    fun ensureLoaded() = Unit
    external fun frame(url: String, positionMs: Long, request: Long, size: Long, playbackPaused: Boolean): KeyframeResult?
    external fun interrupt(request: Long)
    external fun close()
}

private const val FRAME = 1
private const val CANCEL = 2
private const val RESULT = 3
private const val PREPARE = 4
private const val PREPARED = 5

/** A disposable process: no MediaCodec, audio device, main-player state or remote credentials. */
class KeyframeWorkerService : Service() {
    private val worker = Executors.newSingleThreadExecutor()
    private var current = 0L
    @Volatile private var nativeReady = false
    private val inbox = Messenger(Handler(Looper.getMainLooper()) { message ->
        val id = message.data.getLong("id")
        when (message.what) {
            PREPARE -> {
                val reply = message.replyTo
                worker.execute {
                    Process.setThreadPriority(Process.THREAD_PRIORITY_LOWEST)
                    val memory = ActivityManager.MemoryInfo()
                    (getSystemService(ACTIVITY_SERVICE) as ActivityManager).getMemoryInfo(memory)
                    val ready = !memory.lowMemory && memory.availMem >= 256L * 1024 * 1024 && runCatching {
                        // Load code only. No URL, connection, demuxer or decoder is opened.
                        KeyframeNative.ensureLoaded()
                        nativeReady = true
                    }.isSuccess
                    runCatching { reply?.send(Message.obtain(null, PREPARED).apply {
                        data = Bundle().apply { putLong("id", id); putBoolean("ready", ready) }
                    }) }
                }
            }
            CANCEL -> if (current == id && nativeReady) KeyframeNative.interrupt(-id)
            FRAME -> {
                val reply = message.replyTo
                val url = message.data.getString("url").orEmpty()
                val position = message.data.getLong("position", -1)
                val size = message.data.getLong("size", -1)
                val playbackPaused = message.data.getBoolean("paused", false)
                current = id
                try {
                    KeyframeNative.interrupt(id)
                    nativeReady = true
                    worker.execute {
                        Process.setThreadPriority(if (playbackPaused) Process.THREAD_PRIORITY_DEFAULT else Process.THREAD_PRIORITY_LOWEST)
                        val memory = ActivityManager.MemoryInfo()
                        (getSystemService(ACTIVITY_SERVICE) as ActivityManager).getMemoryInfo(memory)
                        val started = SystemClock.elapsedRealtime()
                        val result = if (!memory.lowMemory && memory.availMem >= 256L * 1024 * 1024 &&
                            url.startsWith("http://127.0.0.1:") && position >= 0 && size > 0) {
                            runCatching { KeyframeNative.frame(url, position, id, size, playbackPaused) }.getOrNull()
                        } else null
                        android.util.Log.println(android.util.Log.INFO, "SeekPreviewWorker", "targetMs=$position elapsedMs=${SystemClock.elapsedRealtime() - started} ready=${result != null} lowMemory=${memory.lowMemory} freeMiB=${memory.availMem / (1024 * 1024)}")
                        sendResult(reply, id, result)
                    }
                } catch (_: LinkageError) { sendResult(reply, id, null) }
            }
        }
        true
    })

    private fun sendResult(reply: Messenger?, id: Long, result: KeyframeResult?) {
        try {
            reply?.send(Message.obtain(null, RESULT).apply {
                data = Bundle().apply {
                    putLong("id", id)
                    result?.let { putParcelable("bitmap", it.bitmap); putLong("position", it.positionMs) }
                }
            })
        } catch (_: RemoteException) {
            // The player may already have closed this preview request.
        } finally { result?.bitmap?.recycle() }
    }

    override fun onBind(intent: Intent): IBinder = inbox.binder

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        if (level == android.content.ComponentCallbacks2.TRIM_MEMORY_RUNNING_LOW ||
            level == android.content.ComponentCallbacks2.TRIM_MEMORY_RUNNING_CRITICAL ||
            level >= android.content.ComponentCallbacks2.TRIM_MEMORY_BACKGROUND) releaseForMemoryPressure()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        releaseForMemoryPressure()
    }

    private fun releaseForMemoryPressure() {
        if (!nativeReady) return
        KeyframeNative.interrupt(-current)
        worker.execute { KeyframeNative.close() }
    }

    override fun onDestroy() {
        if (nativeReady) KeyframeNative.interrupt(Long.MIN_VALUE)
        worker.execute { if (nativeReady) KeyframeNative.close() }
        worker.shutdown()
        super.onDestroy()
    }
}

/** Binder ownership and result delivery live on Main; decoding never does. */
internal class KeyframeWorkerClient(context: Context) : Closeable {
    private val app = context.applicationContext
    private var bound = false
    private var closed = false
    private var remote: Messenger? = null
    private var connected = CompletableDeferred<Messenger>()
    private var prepared = false
    private var preparation: Pair<Long, CompletableDeferred<Boolean>>? = null
    private val pending = mutableMapOf<Long, CompletableDeferred<KeyframeResult?>>()
    private val inbox = Messenger(Handler(Looper.getMainLooper()) { message ->
        if (message.what == PREPARED) {
            preparation?.takeIf { it.first == message.data.getLong("id") }?.let {
                prepared = message.data.getBoolean("ready")
                it.second.complete(prepared)
                preparation = null
            }
        }
        if (message.what == RESULT) {
            val data = message.data.apply { classLoader = Bitmap::class.java.classLoader }
            @Suppress("DEPRECATION") val bitmap = data.getParcelable<Bitmap>("bitmap")
            val waiting = pending.remove(data.getLong("id"))
            val result = bitmap?.let { KeyframeResult(it, data.getLong("position", -1)) }
            if (waiting == null || !waiting.complete(result)) bitmap?.recycle()
        }
        true
    })
    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, binder: IBinder) {
            remote = Messenger(binder)
            connected.complete(remote!!)
        }
        override fun onServiceDisconnected(name: ComponentName) {
            remote = null
            prepared = false
            preparation?.second?.complete(false)
            preparation = null
            pending.values.forEach { it.complete(null) }
            pending.clear()
            connected = CompletableDeferred()
        }
        override fun onNullBinding(name: ComponentName) { connected.completeExceptionally(IllegalStateException("No worker")) }
        override fun onBindingDied(name: ComponentName) {
            onServiceDisconnected(name)
            if (bound) runCatching { app.unbindService(this) }
            bound = false
        }
    }

    private suspend fun connect(): Messenger? {
        if (closed) return null
        if (!bound) {
            connected = CompletableDeferred()
            bound = app.bindService(Intent(app, KeyframeWorkerService::class.java), connection,
                Context.BIND_AUTO_CREATE or Context.BIND_WAIVE_PRIORITY or Context.BIND_NOT_FOREGROUND)
            if (!bound) return null
        }
        return withTimeoutOrNull(2500) { remote ?: connected.await() }
    }

    /** Bind/load once while playback is stable, without touching the media source. */
    suspend fun prepare(): Boolean = withContext(Dispatchers.Main.immediate) {
        if (closed) return@withContext false
        val server = connect() ?: return@withContext false
        if (prepared) return@withContext true
        val waiting = preparation ?: (nextId.incrementAndGet() to CompletableDeferred<Boolean>()).also {
            preparation = it
            try {
                server.send(Message.obtain(null, PREPARE).apply {
                    replyTo = inbox
                    data = Bundle().apply { putLong("id", it.first) }
                })
            } catch (_: RemoteException) { it.second.complete(false) }
        }
        val ready = withTimeoutOrNull(2500) { waiting.second.await() } ?: false
        if (preparation === waiting) preparation = null
        ready
    }

    suspend fun frameAt(url: String, positionMs: Long, size: Long, playbackPaused: Boolean = false): KeyframeResult? = withContext(Dispatchers.Main.immediate) {
        val server = connect() ?: return@withContext null
        val id = nextId.incrementAndGet()
        val result = CompletableDeferred<KeyframeResult?>()
        pending[id] = result
        try {
            server.send(Message.obtain(null, FRAME).apply {
                replyTo = inbox
                data = Bundle().apply { putLong("id", id); putString("url", url); putLong("position", positionMs); putLong("size", size); putBoolean("paused", playbackPaused) }
            })
            result.await()
        } catch (_: RemoteException) { null }
        finally {
            pending.remove(id)
            if (!result.isCompleted) {
                result.cancel()
                runCatching { server.send(Message.obtain(null, CANCEL).apply { data = Bundle().apply { putLong("id", id) } }) }
            }
        }
    }

    override fun close() {
        CoroutineScope(Dispatchers.Main.immediate).launch {
            closed = true
            prepared = false
            preparation?.second?.cancel()
            preparation = null
            pending.values.forEach { it.cancel() }
            pending.clear()
            if (bound) runCatching { app.unbindService(connection) }
            bound = false
            remote = null
        }
    }

    companion object { private val nextId = AtomicLong() }
}
