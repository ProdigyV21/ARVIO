package com.arflix.tv.recording

import android.content.Context
import android.os.Environment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.io.File

/**
 * The recordings on this device: scheduled, running and finished. Recordings are device-wide,
 * like the files they produce, so every profile on the TV sees the same list.
 */
object LiveRecordingStore {
    private const val PREFS = "live_recordings"
    private const val KEY = "recordings_v1"
    private val gson = Gson()
    private val lock = Any()
    private val state = MutableStateFlow<List<LiveRecording>>(emptyList())
    @Volatile private var loaded = false

    fun recordings(context: Context): StateFlow<List<LiveRecording>> {
        ensureLoaded(context)
        return state.asStateFlow()
    }

    fun snapshot(context: Context): List<LiveRecording> {
        ensureLoaded(context)
        return state.value
    }

    fun update(context: Context, transform: (List<LiveRecording>) -> List<LiveRecording>): List<LiveRecording> {
        ensureLoaded(context)
        val next = synchronized(lock) {
            transform(state.value).trimmedRecordings().also { list ->
                state.value = list
                context.applicationContext.getSharedPreferences(PREFS, Context.MODE_PRIVATE).edit()
                    .putString(KEY, gson.toJson(list)).apply()
            }
        }
        return next
    }

    fun upsert(context: Context, recording: LiveRecording) {
        update(context) { list -> listOf(recording) + list.filterNot { it.id == recording.id } }
    }

    fun modify(context: Context, id: String, change: (LiveRecording) -> LiveRecording) {
        update(context) { list -> list.map { if (it.id == id) change(it) else it } }
    }

    /** Removes the entry and its file. */
    fun delete(context: Context, id: String) {
        val removed = snapshot(context).firstOrNull { it.id == id }
        update(context) { list -> list.filterNot { it.id == id } }
        removed?.filePath?.let { path -> runCatching { File(path).delete() } }
    }

    /** Where recordings go: the app's own Movies folder, which needs no storage permission. */
    fun directory(context: Context): File {
        val external = runCatching { context.getExternalFilesDir(Environment.DIRECTORY_MOVIES) }.getOrNull()
        val base = external ?: File(context.filesDir, "recordings")
        return File(base, "Recordings").apply { mkdirs() }
    }

    private fun ensureLoaded(context: Context) {
        if (loaded) return
        synchronized(lock) {
            if (loaded) return
            val raw = context.applicationContext.getSharedPreferences(PREFS, Context.MODE_PRIVATE).getString(KEY, null)
            val parsed = raw?.let {
                runCatching {
                    gson.fromJson<List<LiveRecording>>(it, object : TypeToken<List<LiveRecording>>() {}.type)
                }.getOrNull()
            }.orEmpty()
            // A process that died mid-recording left entries marked as running.
            state.value = parsed.sanitizedRecordings().afterRestart(System.currentTimeMillis())
            loaded = true
        }
    }
}
