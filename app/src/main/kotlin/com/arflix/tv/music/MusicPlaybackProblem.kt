package com.arflix.tv.music

import androidx.media3.common.PlaybackException
import java.io.IOException
import java.io.InterruptedIOException
import java.net.ConnectException
import java.net.NoRouteToHostException
import java.net.UnknownHostException

/**
 * Why a song did not start, in terms the player can put into words. The playback service knows
 * the whole cause; the app's controller only receives the error's message and code, so the
 * service puts the problem into the message ([MusicStreamException]) and [of] reads it back.
 */
data class MusicPlaybackProblem(val kind: Kind, val code: Int? = null, val detail: String? = null) {

    enum class Kind {
        /** The addon did not hand over a stream in time; it may still be fetching the song. */
        ADDON_TIMEOUT,
        /** The addon answered with an error instead of a stream. */
        ADDON_ERROR,
        /** The server that holds the audio refused it or broke off. */
        STREAM_ERROR,
        NO_CONNECTION,
        /** A chart song none of the user's sources has; [detail] is its title. */
        NOT_IN_SOURCES,
        /** The audio arrived but this device cannot play its format. */
        FORMAT,
        OTHER,
    }

    fun encode(): String = PREFIX + listOf(kind.name, code?.toString().orEmpty(), detail.orEmpty()).joinToString(":")

    companion object {
        private const val PREFIX = "arvio-music:"
        private val HTTP_CODE = Regex("HTTP (\\d{3})")

        internal fun decode(message: String?): MusicPlaybackProblem? {
            val text = message?.substringAfter(PREFIX, "")?.takeIf { it.isNotEmpty() } ?: return null
            val parts = text.split(":", limit = 3)
            val kind = runCatching { Kind.valueOf(parts[0]) }.getOrNull() ?: return null
            return MusicPlaybackProblem(kind, parts.getOrNull(1)?.toIntOrNull(), parts.getOrNull(2)?.takeIf { it.isNotEmpty() })
        }

        /** In the service: what went wrong while asking a source for a song's stream. */
        fun classify(error: Throwable): MusicPlaybackProblem {
            val chain = generateSequence(error) { it.cause }.toList()
            chain.firstNotNullOfOrNull { it as? MusicMatchMissingException }?.let { return MusicPlaybackProblem(Kind.NOT_IN_SOURCES, detail = it.title) }
            if (chain.any { it is UnknownHostException || it is ConnectException || it is NoRouteToHostException }) {
                return MusicPlaybackProblem(Kind.NO_CONNECTION)
            }
            // A read timeout (SocketTimeoutException) is an InterruptedIOException too.
            if (chain.any { it is InterruptedIOException }) return MusicPlaybackProblem(Kind.ADDON_TIMEOUT)
            val code = chain.firstNotNullOfOrNull { e -> e.message?.let { HTTP_CODE.find(it) }?.groupValues?.get(1)?.toIntOrNull() }
            return MusicPlaybackProblem(Kind.ADDON_ERROR, code)
        }

        /** In the app: the problem behind a player error, from the service's message or else its code. */
        fun of(error: PlaybackException): MusicPlaybackProblem {
            generateSequence<Throwable>(error) { it.cause }.firstNotNullOfOrNull { decode(it.message) }?.let { return it }
            return when (error.errorCode) {
                PlaybackException.ERROR_CODE_IO_NETWORK_CONNECTION_FAILED -> MusicPlaybackProblem(Kind.NO_CONNECTION)
                PlaybackException.ERROR_CODE_IO_NETWORK_CONNECTION_TIMEOUT,
                PlaybackException.ERROR_CODE_IO_BAD_HTTP_STATUS,
                PlaybackException.ERROR_CODE_IO_FILE_NOT_FOUND,
                PlaybackException.ERROR_CODE_IO_INVALID_HTTP_CONTENT_TYPE -> MusicPlaybackProblem(Kind.STREAM_ERROR)
                in PlaybackException.ERROR_CODE_PARSING_CONTAINER_MALFORMED..PlaybackException.ERROR_CODE_PARSING_MANIFEST_UNSUPPORTED,
                in PlaybackException.ERROR_CODE_DECODER_INIT_FAILED..PlaybackException.ERROR_CODE_DECODING_RESOURCES_RECLAIMED,
                PlaybackException.ERROR_CODE_AUDIO_TRACK_INIT_FAILED -> MusicPlaybackProblem(Kind.FORMAT)
                else -> MusicPlaybackProblem(Kind.OTHER)
            }
        }
    }
}

/** A failure to get a song's stream, carrying its [MusicPlaybackProblem] in its message for the app's controller. */
class MusicStreamException(val problem: MusicPlaybackProblem, cause: Throwable?) : IOException(problem.encode(), cause)
