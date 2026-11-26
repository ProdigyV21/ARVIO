package com.arflix.tv.recording

import com.arflix.tv.data.model.IptvChannel
import java.text.Normalizer
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

/**
 * A recording of a game (or programme) from one of the user's own channels. Kept on this
 * device only, like the files themselves.
 */
data class LiveRecording(
    val id: String,
    val title: String,
    /** The channel to record and, best first, the game's other channels to fall back on. */
    val channels: List<IptvChannel>,
    /** When the programme starts and ends; the recording adds a margin on both sides. */
    val programmeStart: Long,
    val programmeEnd: Long,
    val status: String = STATUS_SCHEDULED,
    val filePath: String? = null,
    val bytes: Long = 0L,
    /** The channel that actually got recorded. */
    val recordedChannelName: String? = null,
    val error: String? = null,
    val createdAt: Long = 0L,
    val eventId: String? = null,
    val sport: String? = null,
    /** When the recording actually began writing; 0 until then. */
    val startedAt: Long = 0L,
) {
    val recordFrom: Long get() = recordingWindowStart(programmeStart)
    val recordUntil: Long get() = recordingWindowEnd(programmeStart, programmeEnd)
    val isFinal: Boolean get() = status == STATUS_DONE || status == STATUS_FAILED || status == STATUS_CANCELLED
    val isActive: Boolean get() = status == STATUS_RECORDING || status == STATUS_WAITING

    companion object {
        const val STATUS_SCHEDULED = "scheduled"
        /** The window is open but no connection or stream is available yet. */
        const val STATUS_WAITING = "waiting"
        const val STATUS_RECORDING = "recording"
        const val STATUS_DONE = "done"
        const val STATUS_FAILED = "failed"
        const val STATUS_CANCELLED = "cancelled"
    }
}

/** Starts a little early: kick-off times in a guide are often a few minutes off. */
const val RECORDING_PRE_PADDING_MS = 2 * 60_000L
/** Ends late: extra time, overtime and the closing minutes run past the slot. */
const val RECORDING_POST_PADDING_MS = 15 * 60_000L
/** A guide entry without a sensible end still stops: no recording fills a disk overnight. */
const val RECORDING_MAX_DURATION_MS = 5 * 60 * 60_000L
const val RECORDING_DEFAULT_DURATION_MS = 2 * 60 * 60_000L
/** Below this much free space a recording stops rather than fill the device. */
const val RECORDING_MIN_FREE_BYTES = 500L * 1024 * 1024
/** How many recordings the list keeps (oldest finished ones go first; their files stay until deleted). */
const val RECORDING_LIST_LIMIT = 60

fun recordingWindowStart(programmeStart: Long): Long = programmeStart - RECORDING_PRE_PADDING_MS

fun recordingWindowEnd(programmeStart: Long, programmeEnd: Long): Long {
    val end = if (programmeEnd > programmeStart) programmeEnd else programmeStart + RECORDING_DEFAULT_DURATION_MS
    return minOf(end + RECORDING_POST_PADDING_MS, programmeStart + RECORDING_MAX_DURATION_MS)
}

/** What should happen to a recording at [now]. */
enum class RecordingDue { Wait, Start, Missed, Done }

fun LiveRecording.dueAt(now: Long): RecordingDue = when {
    isFinal -> RecordingDue.Done
    now >= recordUntil -> RecordingDue.Missed
    now >= recordFrom -> RecordingDue.Start
    else -> RecordingDue.Wait
}

/** A file name a TV's file manager and a PC both accept. */
fun recordingFileName(title: String, programmeStart: Long, extension: String, zone: ZoneId = ZoneId.systemDefault()): String {
    val stamp = DateTimeFormatter.ofPattern("yyyy-MM-dd HH.mm", Locale.ROOT)
        .format(Instant.ofEpochMilli(programmeStart).atZone(zone))
    val ascii = Normalizer.normalize(title, Normalizer.Form.NFD).replace(Regex("\\p{Mn}+"), "")
    val safe = ascii.replace(Regex("[^A-Za-z0-9 ._-]+"), " ").replace(Regex("\\s+"), " ").trim().take(80)
        .ifBlank { "Recording" }
    return "$stamp $safe.$extension"
}

/** The list as it should be stored: newest first, unfinished ones never dropped. */
fun List<LiveRecording>.trimmedRecordings(limit: Int = RECORDING_LIST_LIMIT): List<LiveRecording> {
    val sorted = sortedByDescending { it.programmeStart }
    val open = sorted.filterNot { it.isFinal }
    val finished = sorted.filter { it.isFinal }.take((limit - open.size).coerceAtLeast(0))
    return (open + finished).sortedByDescending { it.programmeStart }
}

/** Gson leaves non-null fields null when stored JSON is incomplete; drop entries that cannot run. */
fun List<LiveRecording>.sanitizedRecordings(): List<LiveRecording> = filter { recording ->
    val id: String? = recording.id
    val title: String? = recording.title
    val channels: List<IptvChannel>? = recording.channels
    val status: String? = recording.status
    !id.isNullOrBlank() && title != null && status != null && recording.programmeStart > 0 &&
        (!channels.isNullOrEmpty() || recording.isFinal)
}.map { recording ->
    val channels: List<IptvChannel>? = recording.channels
    if (channels == null) recording.copy(channels = emptyList()) else recording
}.distinctBy { it.id }

/** A recording interrupted by a crash or reboot is not "recording" any more. */
fun List<LiveRecording>.afterRestart(now: Long): List<LiveRecording> = map { recording ->
    when {
        !recording.isActive -> recording
        now < recording.recordUntil -> recording.copy(status = LiveRecording.STATUS_SCHEDULED)
        recording.bytes > 0 -> recording.copy(status = LiveRecording.STATUS_DONE)
        else -> recording.copy(status = LiveRecording.STATUS_FAILED)
    }
}
